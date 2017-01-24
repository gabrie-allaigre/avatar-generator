package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;

public class SquareElementRegistry extends ElementRegistry {

	private final int precision;
	private final List<Color> colors;

	public SquareElementRegistry() {
		this(3, AvatarUtils.defaultColors);
	}

	public SquareElementRegistry(int precision, List<Color> colors) {
		super();

		this.precision = precision;
		this.colors = colors;
	}

	@Override
	public int getElementCount(IAvatarInfo avatarInfo, String name) {
		return 1;
	}

	@Override
	public Path getElement(IAvatarInfo avatarInfo, String name, int index) {
		return buildImage(avatarInfo);
	}

	@Override
	public int getGroupCount() {
		return 1;
	}

	@Override
	public ElementInfo[] getGroup(IAvatarInfo avatarInfo, int index) {
		return new ElementInfo[]{ElementInfo.of("github")};
	}

	private Path buildImage(IAvatarInfo avatarInfo) {
		int size = Math.min(avatarInfo.getWidth() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2, avatarInfo
				.getHeight() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2) * precision;
		int d = size / (precision * 5);

		BufferedImage dst = new BufferedImage(size + d * 2, size + d * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dst.createGraphics();
		AvatarUtils.activeAntialiasing(g2);

		int index = avatarInfo.getRandom().nextInt(colors.size());

		Color fillColor = colors.get(index);
		Color backgroundColor = colors.get((index + 1) % colors.size());

		g2.setColor(fillColor);
		g2.fillRect(0, 0, size + d * 2, size + d * 2);

		g2.setColor(backgroundColor);
		g2.fillRect(d, d, size, size);

		g2.setColor(fillColor);
		int mult = size / precision;
		for (int x = 0; x < precision; x++) {
			for (int y = 0; y < precision; y++) {
				if (avatarInfo.getRandom().nextDouble() < 0.5) {
					g2.fillRect(x * mult + d, y * mult + d, mult, mult);
				}
			}
		}

		g2.dispose();

		return AvatarUtils.saveImageInTemp(dst);
	}
}
