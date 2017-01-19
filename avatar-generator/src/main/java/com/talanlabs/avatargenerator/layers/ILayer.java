package com.talanlabs.avatargenerator.layers;

import java.awt.image.BufferedImage;

public interface ILayer {

    /**
     * Apply tranforme with src image
     *
     * @param code current code
     * @param src  current image
     * @return new image
     */
    BufferedImage apply(long code, BufferedImage src);

}
