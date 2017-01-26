package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.Image;

public interface ElementRegistry {

    /**
     * @param avatarInfo avarie
     * @param name       name of element
     * @return size of element
     */
    int getElementCount(IAvatarInfo avatarInfo, String name);

    /**
     * @param avatarInfo avarie
     * @param name       name of element
     * @param index      position in element
     * @return a path of element
     */
    Image getElement(IAvatarInfo avatarInfo, String name, int index);

    /**
     * @param avatarInfo avarie
     * @return size of group
     */
    int getGroupCount(IAvatarInfo avatarInfo);

    /**
     * @param avatarInfo avarie
     * @param index      position in group
     * @return array of element information
     */
    ElementInfo[] getGroup(IAvatarInfo avatarInfo, int index);

}
