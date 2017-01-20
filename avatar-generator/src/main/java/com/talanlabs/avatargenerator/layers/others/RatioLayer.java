package com.talanlabs.avatargenerator.layers.others;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.layers.ILayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.image.BufferedImage;

public class RatioLayer implements ILayer {

    private double ratio;

    public RatioLayer(double ratio) {
        super();

        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    @Override
    public BufferedImage apply(IAvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        if (width / height == ratio) {
            return src;
        }

        if (ratio >= 1) {
            if (width > height) {
                height = (int) (width / ratio);
            } else {
                width = (int) (height / ratio);
            }
        } else {

        }

        return AvatarUtils.planImage(src, width, height);
    }
}
