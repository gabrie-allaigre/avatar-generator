package com.talanlabs.avatargenerator.layers.others;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.image.BufferedImage;

public class ResizeLayer implements ILayer {

    private int width;
    private int height;

    public ResizeLayer(int width, int height) {
        super();

        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
        return AvatarUtils.resizeImage(src, width, height);
    }
}
