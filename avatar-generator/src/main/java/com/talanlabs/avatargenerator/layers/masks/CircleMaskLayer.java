package com.talanlabs.avatargenerator.layers.masks;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class CircleMaskLayer extends AbstractMaskLayer {

    @Override
    protected BufferedImage buildMask(IAvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.setColor(Color.white);
        g2.fillOval(0, 0, width, height);
        g2.dispose();
        return mask;
    }
}
