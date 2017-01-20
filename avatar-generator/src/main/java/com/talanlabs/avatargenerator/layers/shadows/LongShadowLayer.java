package com.talanlabs.avatargenerator.layers.shadows;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class LongShadowLayer extends AbstractShadowLayer {

    private Color shadowColor;

    public LongShadowLayer() {
        this(new Color(0, 0, 0, 64));
    }

    public LongShadowLayer(Color shadowColor) {
        super(true);

        this.shadowColor = shadowColor;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    @Override
    protected BufferedImage buildShadow(IAvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        double n = shadowColor.getAlpha();
        double step = n / (width + height);

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isInShade(src, x, y)) {
                    int alpha = (int) (n - ((x + y) * step));
                    int[] colors = new int[] { shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), alpha };

                    dest.getRaster().setPixel(x, y, colors);
                }
            }
        }
        return dest;
    }

    private boolean isInShade(BufferedImage src, int x, int y) {
        int tx = x;
        int ty = y;
        int[] colors = new int[4];
        while (true) {
            tx -= 1;
            ty -= 1;
            if (tx < 0 || ty < 0) {
                return false;
            } else {
                src.getRaster().getPixel(tx, ty, colors);
                if (colors[3] > 0) {
                    return true;
                }
            }
        }
    }
}
