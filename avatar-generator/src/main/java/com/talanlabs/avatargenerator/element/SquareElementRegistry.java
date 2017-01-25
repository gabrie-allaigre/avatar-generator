package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class SquareElementRegistry extends AbstractImageElementRegistry {

    private final int precision;
    private final List<Color> colors;

    public SquareElementRegistry() {
        this(3, AvatarUtils.defaultColors);
    }

    public SquareElementRegistry(int precision, List<Color> colors) {
        super();

        this.precision = precision;
        this.colors = colors;
    }

    @Override
    protected BufferedImage buildImage(IAvatarInfo avatarInfo) {
        int size =
                Math.min(avatarInfo.getWidth() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2, avatarInfo.getHeight() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2) * precision;
        int d = size / (precision * 5);

        BufferedImage dst = new BufferedImage(size + d * 2, size + d * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dst.createGraphics();
        AvatarUtils.activeAntialiasing(g2);

        int index = avatarInfo.getRandom().nextInt(colors.size());

        Color fillColor = colors.get(index);
        Color backgroundColor = colors.get((index + 1) % colors.size());

        g2.setColor(fillColor);
        g2.fillRect(0, 0, size + d * 2, size + d * 2);

        g2.setColor(backgroundColor);
        g2.fillRect(d, d, size, size);

        g2.setColor(fillColor);
        int mult = size / precision;
        for (int x = 0; x < precision; x++) {
            for (int y = 0; y < precision; y++) {
                if (avatarInfo.getRandom().nextDouble() < 0.5) {
                    g2.fillRect(x * mult + d, y * mult + d, mult, mult);
                }
            }
        }

        g2.dispose();

        return dst;
    }
}
