package com.talanlabs.avatargenerator;

import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvatarBuilder {

	private ElementRegistry elementRegistry = null;
	private int width = 128;
	private int height = 128;
	private int padding = 8;
	private int margin = 0;
	private IColorizeFunction colorizeFunction = null;
	private ILayer[] layers;

	private AvatarBuilder() {
		super();
	}

	public static AvatarBuilder newBuilder() {
		return new AvatarBuilder();
	}

	/**
	 * Element loader
	 */
	public AvatarBuilder elementRegistry(ElementRegistry elementRegistry) {
		this.elementRegistry = elementRegistry;
		return this;
	}

	/**
	 * Set size of avatar
	 * Default is 96x96
	 */
	public AvatarBuilder size(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/**
	 * Set space with border
	 * Default is 8
	 */
	public AvatarBuilder padding(int padding) {
		this.padding = padding;
		return this;
	}

	/**
	 * Set space out border
	 * Default is 8
	 */
	public AvatarBuilder margin(int margin) {
		this.margin = margin;
		return this;
	}

	/**
	 * Apply layers after
	 */
	public AvatarBuilder layers(ILayer... layers) {
		this.layers = layers;
		return this;
	}

	/**
	 * Color of element
	 */
	public AvatarBuilder color(Color color) {
		return colorizeFunction((c, e) -> color);
	}

	/**
	 * Color of element
	 */
	public AvatarBuilder colorizeFunction(IColorizeFunction colorizeFunction) {
		this.colorizeFunction = colorizeFunction;
		return this;
	}

	/**
	 * Build image
	 */
	public BufferedImage build(long code) {
		try {
			Random random = new Random(code);

			IAvatarInfo avatarInfo = new MyAvatarInfo(code, random);

			BufferedImage bufferedImage = buildAvatarImage(avatarInfo);

			int wm = width - margin * 2;
			int hm = height - margin * 2;
			int wmp = wm - padding * 2;
			int hmp = hm - padding * 2;

			bufferedImage = AvatarUtils
					.resizeImage(bufferedImage, wmp, hmp);
			bufferedImage = AvatarUtils.planImage(bufferedImage, wm, hm);

			if (layers != null && layers.length > 0) {
				for (ILayer layer : layers) {
					bufferedImage = layer.apply(avatarInfo, bufferedImage);
				}
			}

			bufferedImage = AvatarUtils.resizeImage(bufferedImage, wm, hm);
			bufferedImage = AvatarUtils.planImage(bufferedImage, width, height);
			return bufferedImage;
		} catch (Exception e) {
			throw new RuntimeException("Failed to build avatar", e);
		}
	}

	private BufferedImage buildAvatarImage(IAvatarInfo avatarInfo) throws IOException {
		Random random = avatarInfo.getRandom();

		int xmin = Integer.MAX_VALUE, ymin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE, ymax = Integer.MIN_VALUE;
		List<ImageInfo> imageInfos = new ArrayList<>();
		int groupCount = elementRegistry.getGroupCount();
		if (groupCount > 0) {
			int d = random.nextInt(groupCount);
			ElementInfo[] elements = elementRegistry.getGroup(d);
			if (elements != null && elements.length > 0) {
				for (ElementInfo element : elements) {
					int elementCount = elementRegistry.getElementCount(element.name);
					if (elementCount > 0) {
						int index = random.nextInt(elementCount);
						//System.out.println(elementRegistry.getElement(element.name, index));
						Image image = ImageIO
								.read(Files.newInputStream(elementRegistry.getElement(element.name, index)));

						xmin = Math.min(xmin, -image.getWidth(null) / 2 + element.offsetX);
						xmax = Math.max(xmax, image.getWidth(null) / 2 + element.offsetX);
						ymin = Math.min(ymin, -image.getHeight(null) / 2 + element.offsetY);
						ymax = Math.max(ymax, image.getHeight(null) / 2 + element.offsetY);

						imageInfos.add(new ImageInfo(element.name, AvatarUtils
								.toARGBImage(image), element.offsetX, element.offsetY));
					}
				}
			}
		}
		int w = xmax - xmin;
		int h = ymax - ymin;

		BufferedImage dest = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);

		for (ImageInfo imageInfo : imageInfos) {
			copyImage(g2, avatarInfo, imageInfo, w, h);
		}

		g2.dispose();

		return dest;
	}

	private void copyImage(Graphics2D g2, IAvatarInfo avatarInfo, ImageInfo imageInfo, int width, int height) throws IOException {
		Image img = imageInfo.image;
		if (colorizeFunction != null) {
			Color color = colorizeFunction.colorize(avatarInfo, imageInfo.element);
			if (color != null) {
				img = AvatarUtils.tintImage(img, color);
			}
		}
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		int x = (width - w) / 2 + imageInfo.offsetX;
		int y = (height - h) / 2 + imageInfo.offsetY;
		g2.drawImage(img, x, y, w, h, null);
	}

	public interface IColorizeFunction {

		/**
		 * Get color for element
		 *
		 * @param avatarInfo current avatarInfo
		 * @param element    name of element
		 * @return color
		 */
		Color colorize(IAvatarInfo avatarInfo, String element);

	}

	private static class ImageInfo {

		public final String element;
		public final Image image;
		public final int offsetX;
		public final int offsetY;

		ImageInfo(String element, Image image, int offsetX, int offsetY) {
			super();

			this.element = element;
			this.image = image;
			this.offsetX = offsetX;
			this.offsetY = offsetY;
		}
	}

	private class MyAvatarInfo implements IAvatarInfo {

		private long code;
		private Random random;

		public MyAvatarInfo(long code, Random random) {
			this.code = code;
			this.random = random;
		}

		@Override
		public long getCode() {
			return code;
		}

		@Override
		public Random getRandom() {
			return random;
		}

		@Override
		public int getWidth() {
			return width;
		}

		@Override
		public int getHeight() {
			return height;
		}

		@Override
		public int getPadding() {
			return padding;
		}

		@Override
		public int getMargin() {
			return margin;
		}
	}
}
