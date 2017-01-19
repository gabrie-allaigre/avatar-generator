package com.talanlabs.avatargenerator.smiley;

import com.talanlabs.avatargenerator.AvatarBuilder;
import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;
import com.talanlabs.avatargenerator.functions.RandomColorizeFunction;
import com.talanlabs.avatargenerator.layers.backgrounds.RandomColorPaintBackgroundLayer;
import com.talanlabs.avatargenerator.layers.masks.RoundRectMaskLayer;
import com.talanlabs.avatargenerator.layers.others.RatioLayer;
import com.talanlabs.avatargenerator.layers.others.ShadowLayer;
import com.talanlabs.avatargenerator.layers.shadows.ScoreShadowLayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collections;

public class SmileyAvatar {

    public static AvatarBuilder newDefaultAvatarBuilder() {
        return AvatarBuilder.newBuilder().size(128, 128).elementRegistry(newDefaultElementRegistry()).colorizeFunction(new RandomColorizeFunction()).padding(8)
                .layers(new ShadowLayer(), new ScoreShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer(), new ShadowLayer());
    }

    /**
     * New smiley element registry
     */
    public static ElementRegistry newDefaultElementRegistry() {
        ElementRegistry elementRegistry = new ElementRegistry();
        for (SmileyElementType smileyElementType : SmileyElementType.values()) {
            elementRegistry.putElement(smileyElementType.name(), ElementRegistry.lsPng(smileyElementType.path));
        }

        elementRegistry.putGroup(ElementInfo.of(SmileyElementType.beard.name()), ElementInfo.of(SmileyElementType.hat.name()));
        elementRegistry.putGroup(ElementInfo.of(SmileyElementType.beard.name()), ElementInfo.of(SmileyElementType.glass.name()));
        elementRegistry.putGroup(ElementInfo.of(SmileyElementType.glass.name()), ElementInfo.of(SmileyElementType.hat.name()));
        elementRegistry.putGroup(ElementInfo.of(SmileyElementType.beard.name()), ElementInfo.of(SmileyElementType.glass.name()), ElementInfo.of(SmileyElementType.hat.name()));
        elementRegistry.putGroup(ElementInfo.of(SmileyElementType.mask.name()));

        return elementRegistry;
    }

    public static AvatarBuilder newEyeMouthAvatarBuilder() {
        return AvatarBuilder.newBuilder().size(128, 128).elementRegistry(newEyeMouthElementRegistry()).colorizeFunction(null).padding(0)
                .layers(new RatioLayer(1.0), new ShadowLayer(), new ScoreShadowLayer(), new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer(), new ShadowLayer());
    }

    /**
     * New smiley element registry
     */
    public static ElementRegistry newEyeMouthElementRegistry() {
        ElementRegistry elementRegistry = new ElementRegistry();
        elementRegistry.putElement(SmileyElementType.eyeBig.name(), ElementRegistry.lsPng(SmileyElementType.eyeBig.path));
        elementRegistry.putElement(SmileyElementType.happyMouth.name(), ElementRegistry.lsPng(SmileyElementType.happyMouth.path));
        elementRegistry.putGroup(ElementInfo.of(SmileyElementType.eyeBig.name(), 0, -10), ElementInfo.of(SmileyElementType.happyMouth.name(), 0, 10));
        return elementRegistry;
    }

    public static AvatarBuilder newGhostAvatarBuilder() {
        return AvatarBuilder.newBuilder().size(128, 128).padding(0).elementRegistry(newGhostElementRegistry()).colorizeFunction((code, element) -> {
            if (SmileyElementType.moreShape.name().equals(element)) {
                Color color = AvatarUtils.defaultColors.get((int) (code % AvatarUtils.defaultColors.size()));
                return new Color(color.getRed(), color.getGreen(), color.getBlue(), 196);
            }
            return null;
        }).layers(new ShadowLayer());
    }

    /**
     * New smiley element registry
     */
    public static ElementRegistry newGhostElementRegistry() {
        ElementRegistry elementRegistry = new ElementRegistry();
        try {
            elementRegistry.putElement(SmileyElementType.moreShape.name(),
                    Collections.singletonList(Paths.get(AvatarBuilder.class.getResource("/com/talanlabs/avatargenerator/smiley/images/MoreShape/30-2.png").toURI())));
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to load ghost", e);
        }
        elementRegistry.putElement(SmileyElementType.eyeBig.name(), ElementRegistry.lsPng(SmileyElementType.eyeBig.path));
        elementRegistry.putElement(SmileyElementType.happyMouth.name(), ElementRegistry.lsPng(SmileyElementType.happyMouth.path));
        elementRegistry.putGroup(ElementInfo.of(SmileyElementType.moreShape.name()), ElementInfo.of(SmileyElementType.eyeBig.name(), 0, -5), ElementInfo.of(SmileyElementType.happyMouth.name(), 0, 5));
        return elementRegistry;
    }
}
