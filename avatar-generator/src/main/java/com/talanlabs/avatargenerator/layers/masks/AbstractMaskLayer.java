package com.talanlabs.avatargenerator.layers.masks;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class AbstractMaskLayer implements ILayer {

	@Override
	public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
		BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = dest.createGraphics();
		AvatarUtils.activeAntialiasing(g2);
		g2.drawImage(src, 0, 0, null);
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.DST_IN, 1.0F);
		g2.setComposite(ac);
		g2.drawImage(buildMask(avatarInfo, src), 0, 0, null);
		g2.dispose();
		return dest;
	}

	/**
	 * Get a image mask
	 */
	protected abstract BufferedImage buildMask(IAvatarInfo avatarInfo, BufferedImage src);

}
