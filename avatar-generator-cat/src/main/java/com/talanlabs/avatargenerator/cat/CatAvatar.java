package com.talanlabs.avatargenerator.cat;

import com.talanlabs.avatargenerator.AvatarBuilder;
import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;

public class CatAvatar {

    /**
     * New avatar builder with cat
     */
    public static AvatarBuilder newAvatarBuilder() {
        return AvatarBuilder.newBuilder().size(128, 128).elementRegistry(newElementRegistry()).colorizeFunction(null).padding(0);
    }

    /**
     * New cat element resgistry
     */
    public static ElementRegistry newElementRegistry() {
        ElementRegistry elementRegistry = new ElementRegistry();
        for (CatElementType catElementType : CatElementType.values()) {
            elementRegistry.putElement(catElementType.name(), ElementRegistry.lsPng(catElementType.path));
        }

        elementRegistry.putGroup(ElementInfo.of(CatElementType.body.name()), ElementInfo.of(CatElementType.fur.name()), ElementInfo.of(CatElementType.eyes.name()),
                ElementInfo.of(CatElementType.mouth.name()));
        elementRegistry.putGroup(ElementInfo.of(CatElementType.body.name()), ElementInfo.of(CatElementType.fur.name()), ElementInfo.of(CatElementType.eyes.name()),
                ElementInfo.of(CatElementType.mouth.name()), ElementInfo.of(CatElementType.accessorie.name()));
        elementRegistry.putGroup(ElementInfo.of(CatElementType.body.name()), ElementInfo.of(CatElementType.fur.name()), ElementInfo.of(CatElementType.eyes.name()),
                ElementInfo.of(CatElementType.mouth.name()), ElementInfo.of(CatElementType.zz.name()));

        return elementRegistry;
    }

}
