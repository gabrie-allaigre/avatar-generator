package com.talanlabs.avatargenerator.layers;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.image.BufferedImage;

public interface ILayer {

	/**
	 * Apply tranforme with src image
	 *
	 * @param avatarInfo avatar information
	 * @param src        current image
	 * @return new image
	 */
	BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src);

}
