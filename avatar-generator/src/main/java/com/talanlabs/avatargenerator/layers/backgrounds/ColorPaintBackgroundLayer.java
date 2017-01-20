package com.talanlabs.avatargenerator.layers.backgrounds;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.Color;
import java.awt.Paint;
import java.awt.image.BufferedImage;

public class ColorPaintBackgroundLayer extends AbstractPaintBackgroundLayer {

    private Color color;

    public ColorPaintBackgroundLayer() {
        this(new Color(0xE2A6FF));
    }

    public ColorPaintBackgroundLayer(Color color) {
        super();

        this.color = color;
    }

    @Override
    protected Paint buildPaint(IAvatarInfo avatarInfo, BufferedImage bufferedImage) {
        return color;
    }
}
