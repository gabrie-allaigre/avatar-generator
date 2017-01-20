package com.talanlabs.avatargenerator.layers.backgrounds;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class RandomColorPaintBackgroundLayer implements ILayer {

	private List<Color> colors;

	public RandomColorPaintBackgroundLayer() {
		this(AvatarUtils.defaultColors);
	}

	public RandomColorPaintBackgroundLayer(List<Color> colors) {
		super();

		this.colors = colors;
	}

	@Override
	public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
		Color backColor = colors.get((int) (avatarInfo.getCode() % colors.size()));

		int width = src.getWidth();
		int height = src.getHeight();

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		g2.setPaint(backColor);
		g2.fillRect(0, 0, width, height);
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}
}
