package com.talanlabs.avatargenerator.element;

import com.jhlabs.image.ShadowFilter;
import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

public class TriangleElementRegistry extends AbstractImageElementRegistry {

    private final int precision;
    private final List<Color> colors;

    public TriangleElementRegistry() {
        this(8, AvatarUtils.defaultColors);
    }

    public TriangleElementRegistry(int precision, List<Color> colors) {
        super();

        this.precision = precision;
        this.colors = colors;
    }

    @Override
    protected BufferedImage buildImage(IAvatarInfo avatarInfo) {
        int size = Math.min(avatarInfo.getWidth() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2, avatarInfo.getHeight() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2);

        BufferedImage dst = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dst.createGraphics();
        AvatarUtils.activeAntialiasing(g2);

        Color color = colors.get(avatarInfo.getRandom().nextInt(colors.size()));

        float ss = Math.max(1f, size / 20f);
        ShadowFilter shadowFilter = new ShadowFilter(ss, 0f, 0f, 0.75f);

        Random random = avatarInfo.getRandom();
        int s = random.nextInt(4);
        int n = s + precision;
        int p = size;
        int d = size / n;
        int i = s;
        while (p > 0) {
            Polygon polygon = null;
            switch (i % 4) {
            case 0:
                polygon = new Polygon(new int[] { 0, p - 1, 0 }, new int[] { 0, 0, p - 1 }, 3);
                break;
            case 1:
                polygon = new Polygon(new int[] { size - 1, size - 1, size - p - 1 }, new int[] { 0, p - 1, 0 }, 3);
                break;
            case 2:
                polygon = new Polygon(new int[] { size - 1, size - p - 1, size - 1 }, new int[] { size - 1, size - 1, size - p - 1 }, 3);
                break;
            case 3:
                polygon = new Polygon(new int[] { 0, 0, p - 1 }, new int[] { size - 1, size - p - 1, size - 1 }, 3);
                break;
            }

            if (polygon != null) {
                g2.drawImage(drawTriangle(size, color, polygon), shadowFilter, 0, 0);
            }

            p -= random.nextInt(d / 2) + d / 2;
            i++;
        }

        g2.dispose();

        return AvatarUtils.planImage(dst, size - (int) ss, size - (int) ss);
    }

    private BufferedImage drawTriangle(int size, Color color, Polygon polygon) {
        BufferedImage bi = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = bi.createGraphics();
        AvatarUtils.activeAntialiasing(g2);

        g2.setColor(color);
        g2.fill(polygon);

        g2.dispose();

        return bi;
    }
}
