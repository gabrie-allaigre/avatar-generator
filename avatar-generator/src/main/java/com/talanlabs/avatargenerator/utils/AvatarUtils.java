package com.talanlabs.avatargenerator.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class AvatarUtils {

	public static List<Color> defaultColors = Arrays
			.asList(new Color(0x6e1e78), new Color(0x82be00), new Color(0xa1006b), new Color(0x009aa6), new Color(0xcd0037), new Color(0x0088ce), new Color(0xe05206), new Color(0xd52b1e),
					new Color(0xffb612), new Color(0xd2e100));

	private AvatarUtils() {
		super();
	}

	/**
	 * Active antialiasing
	 */
	public static void activeAntialiasing(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	}

	/**
	 * Resize Image with aspect ratio
	 *
	 * @param src    image source
	 * @param width  new width
	 * @param height new height
	 * @return new image
	 */
	public static BufferedImage resizeImage(BufferedImage src, int width, int height) {
		int original_width = src.getWidth(null);
		int original_height = src.getHeight(null);

		if (original_width == width && original_height == height) {
			return src;
		}

		double diffComponent = width / (double) height;
		double diffImage = original_width / (double) original_height;

		double diff = diffImage / diffComponent;
		int w = width;
		int h = height;
		if (diff >= 1.0) {
			h = (int) (h / diff);
		} else {
			w = (int) (w * diff);
		}

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		g2.drawImage(src.getScaledInstance(w, h, Image.SCALE_SMOOTH), (width - w) / 2, (height - h) / 2, null);
		g2.dispose();
		return dest;
	}

	/**
	 * Colorize image
	 *
	 * @param src   image source
	 * @param color color
	 * @return image with color
	 */
	public static BufferedImage fillColorImage(Image src, Color color) {
		BufferedImage dest = new BufferedImage(src.getWidth(null), src.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.setColor(color);
		g2.fillRect(0, 0, src.getWidth(null), src.getHeight(null));
		g2.setComposite(AlphaComposite.DstIn);
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}

	public static BufferedImage tintImage(Image src, Color color) {
		int w = src.getWidth(null);
		int h = src.getHeight(null);
		BufferedImage dest = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = dest.createGraphics();
		g.drawImage(src, 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(color);
		g.fillRect(0, 0, w, h);
		g.dispose();
		return dest;
	}

	/**
	 * Resize planImage
	 *
	 * @param src    image source
	 * @param width  new width
	 * @param height new height
	 * @return new image
	 */
	public static BufferedImage planImage(BufferedImage src, int width, int height) {
		int original_width = src.getWidth(null);
		int original_height = src.getHeight(null);
		if (original_width == width && original_height == height) {
			return src;
		}

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(src, (width - original_width) / 2, (height - original_height) / 2, null);
		g2.dispose();
		return dest;
	}

	/**
	 * Complement color
	 *
	 * @param color      current color
	 * @param blackColor color dark
	 * @param whiteColor color light
	 * @return blackColor or whiteColor
	 */
	public static Color getComplementColor(Color color, Color blackColor, Color whiteColor) {
		float[] rgba1 = color.getComponents(null);
		double l = 0.2126 * rgba1[0] + 0.7152 * rgba1[1] + 0.0722 * rgba1[2];
		double ratio = (l + 0.05) / 0.05;
		return ratio > 7 ? blackColor : whiteColor;
	}

	/**
	 * Convert image to argb image
	 *
	 * @param src image
	 * @return new image type argb
	 */
	public static BufferedImage toARGBImage(Image src) {
		BufferedImage dest = new BufferedImage(src.getWidth(null), src.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}
}
