package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElementRegistry implements ElementRegistry {

    private final List<ElementInfo[]> groups = new ArrayList<>();

    public AbstractElementRegistry() {
        super();
    }

    public void putGroup(ElementInfo... elementInfos) {
        groups.add(elementInfos);
    }

    @Override
    public int getGroupCount(IAvatarInfo avatarInfo) {
        return groups.size();
    }

    @Override
    public ElementInfo[] getGroup(IAvatarInfo avatarInfo, int index) {
        return groups.get(index);
    }

}
