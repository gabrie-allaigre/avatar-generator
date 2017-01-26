package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GitHubElementRegistry extends AbstractImageElementRegistry {

    private final int size;
    private final int precision;

    public GitHubElementRegistry() {
        this(400, 3);
    }

    public GitHubElementRegistry(int size, int precision) {
        super();

        this.size = size;
        this.precision = precision;
    }

    @Override
    protected BufferedImage buildImage(IAvatarInfo avatarInfo) {
        BufferedImage dst = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = dst.createGraphics();
        AvatarUtils.activeAntialiasing(g2);

        g2.setColor(AvatarUtils.extractColor(avatarInfo.getCode()));

        int mult = size / ((precision * 2) - 1);
        for (int x = 0; x < precision; x++) {
            for (int y = 0; y < precision * 2; y++) {
                if (avatarInfo.getRandom().nextDouble() < 0.5) {
                    g2.fillRect(x * mult, y * mult, mult, mult);
                    g2.fillRect(size - (x + 1) * mult, y * mult, mult, mult);
                }
            }
        }

        g2.dispose();

        return dst;
    }
}
