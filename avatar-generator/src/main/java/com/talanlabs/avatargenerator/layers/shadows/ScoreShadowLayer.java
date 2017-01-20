package com.talanlabs.avatargenerator.layers.shadows;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ScoreShadowLayer extends AbstractShadowLayer {

	private Color shadowColor;

	public ScoreShadowLayer() {
		this(new Color(0, 0, 0, 24));
	}

	public ScoreShadowLayer(Color shadowColor) {
		super(false);

		this.shadowColor = shadowColor;
	}

	public Color getShadowColor() {
		return shadowColor;
	}

	public void setShadowColor(Color shadowColor) {
		this.shadowColor = shadowColor;
	}

	@Override
	protected BufferedImage buildShadow(IAvatarInfo avatarInfo, BufferedImage src) {
		int width = src.getWidth();
		int height = src.getHeight();

		int[] colors = new int[]{shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), shadowColor.getAlpha()};

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < height / 2; y++) {
			for (int x = 0; x < width; x++) {
				dest.getRaster().setPixel(x, y, colors);
			}
		}
		return dest;
	}
}
