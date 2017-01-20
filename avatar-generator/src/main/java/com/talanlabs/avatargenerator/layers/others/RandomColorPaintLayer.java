package com.talanlabs.avatargenerator.layers.others;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class RandomColorPaintLayer implements ILayer {

	private List<Color> colors;
	private Color whiteColor;
	private Color blackColor;

	public RandomColorPaintLayer() {
		this(AvatarUtils.defaultColors, Color.BLACK, Color.WHITE);
	}

	public RandomColorPaintLayer(List<Color> colors, Color blackColor, Color whiteColor) {
		super();

		this.colors = colors;
		this.whiteColor = whiteColor;
		this.blackColor = blackColor;
	}

	@Override
	public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
		Color backColor = colors.get((int) (avatarInfo.getCode() % colors.size()));
		Color foreColor = AvatarUtils.getComplementColor(backColor, blackColor, whiteColor);

		int width = src.getWidth();
		int height = src.getHeight();

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		g2.setPaint(backColor);
		g2.fillRect(0, 0, width, height);
		g2.drawImage(AvatarUtils.fillColorImage(src, foreColor), 0, 0, null);
		g2.dispose();
		return dest;
	}
}
