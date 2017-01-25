package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.element.identicon.NineBlockIdenticonRenderer;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class IdenticonElementRegistry extends AbstractImageElementRegistry {

    private final NineBlockIdenticonRenderer nineBlockIdenticonRenderer;

    public IdenticonElementRegistry() {
        this(new NineBlockIdenticonRenderer());
    }

    public IdenticonElementRegistry(NineBlockIdenticonRenderer nineBlockIdenticonRenderer) {
        super();

        this.nineBlockIdenticonRenderer = nineBlockIdenticonRenderer;
    }

    @Override
    protected BufferedImage buildImage(IAvatarInfo avatarInfo) {
        int code = new BigDecimal(avatarInfo.getCode()).remainder(new BigDecimal(2).pow(32)).intValue();
        int size = Math.min(avatarInfo.getWidth() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2, avatarInfo.getHeight() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2);
        return nineBlockIdenticonRenderer.render(code, size);
    }
}
