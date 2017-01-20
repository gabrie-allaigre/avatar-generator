package com.talanlabs.avatargenerator.layers.masks;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class RoundRectMaskLayer extends AbstractMaskLayer {

    private double percent = 0.5;

    public RoundRectMaskLayer() {
        this(0.5);
    }

    public RoundRectMaskLayer(double percent) {
        super();

        this.percent = percent;
    }

    @Override
    protected BufferedImage buildMask(IAvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.setColor(Color.white);
        g2.fillRoundRect(0, 0, width, height, (int) (width * percent), (int) (height * percent));
        g2.dispose();
        return mask;
    }
}
