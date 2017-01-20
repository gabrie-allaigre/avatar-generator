package com.talanlabs.avatargenerator.layers.shadows;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class AbstractShadowLayer implements ILayer {

	private boolean first;

	protected AbstractShadowLayer(boolean first) {
		super();
		this.first = first;
	}

	@Override
	public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
		BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		if (first) {
			g2.drawImage(buildShadow(avatarInfo, src), 0, 0, null);
		}
		g2.drawImage(src, 0, 0, null);
		if (!first) {
			g2.drawImage(buildShadow(avatarInfo, src), 0, 0, null);
		}
		g2.dispose();
		return dest;
	}

	/**
	 * Get a image shadow
	 */
	protected abstract BufferedImage buildShadow(IAvatarInfo avatarInfo, BufferedImage src);

}
