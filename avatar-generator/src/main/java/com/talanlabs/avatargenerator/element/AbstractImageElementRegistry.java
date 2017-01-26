package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class AbstractImageElementRegistry implements ElementRegistry {

    protected AbstractImageElementRegistry() {
        super();
    }

    @Override
    public int getElementCount(IAvatarInfo avatarInfo, String name) {
        return 1;
    }

    @Override
    public Image getElement(IAvatarInfo avatarInfo, String name, int index) {
        return buildImage(avatarInfo);
    }

    @Override
    public int getGroupCount(IAvatarInfo avatarInfo) {
        return 1;
    }

    @Override
    public ElementInfo[] getGroup(IAvatarInfo avatarInfo, int index) {
        return new ElementInfo[] { ElementInfo.of("github") };
    }

    protected abstract BufferedImage buildImage(IAvatarInfo avatarInfo);

}
