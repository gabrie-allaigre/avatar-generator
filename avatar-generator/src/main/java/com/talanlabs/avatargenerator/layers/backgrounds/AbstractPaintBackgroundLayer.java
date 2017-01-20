package com.talanlabs.avatargenerator.layers.backgrounds;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;

public abstract class AbstractPaintBackgroundLayer implements ILayer {

	@Override
	public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
		int width = src.getWidth();
		int height = src.getHeight();

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		g2.setPaint(buildPaint(avatarInfo, src));
		g2.fillRect(0, 0, width, height);
		g2.drawImage(src, 0, 0, null);
		g2.dispose();
		return dest;
	}

	/**
	 * Get a paint background
	 */
	protected abstract Paint buildPaint(IAvatarInfo avatarInfo, BufferedImage bufferedImage);

}
