package com.talanlabs.avatargenerator.layers.backgrounds;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.Color;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class RadialPaintBackgroundLayer extends AbstractPaintBackgroundLayer {

    private Color inColor;
    private Color outColor;

    public RadialPaintBackgroundLayer() {
        this(new Color(0xE2A6FF), new Color(0xC58BFF));
    }

    public RadialPaintBackgroundLayer(Color inColor, Color outColor) {
        this.inColor = inColor;
        this.outColor = outColor;
    }

    @Override
    protected Paint buildPaint(IAvatarInfo avatarInfo, BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        return new RadialGradientPaint(width / 2, height / 2, (int) Math.sqrt(width * width + height * height) / 2, new float[] { 0.0f, 0.75f }, new Color[] { inColor, outColor });
    }
}
