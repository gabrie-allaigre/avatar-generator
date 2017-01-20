package com.talanlabs.avatargenerator.layers.others;

import com.jhlabs.image.ShadowFilter;
import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ShadowLayer implements ILayer {

	private int size;
	private ShadowFilter shadowFilter;

	public ShadowLayer() {
		this(10, new ShadowFilter(5f, 2.5f, -2.5f, 0.75f));
	}

	public ShadowLayer(int size, ShadowFilter shadowFilter) {
		super();

		this.size = size;
		this.shadowFilter = shadowFilter;
	}

	@Override
	public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
		int width = src.getWidth() + size;
		int height = src.getHeight() + size;

		BufferedImage tmp = AvatarUtils.planImage(src, width, height);

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		g2.drawImage(tmp, shadowFilter, 0, 0);
		g2.dispose();
		return dest;
	}
}
