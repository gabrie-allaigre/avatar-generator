package com.talanlabs.avatargenerator.smiley;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;
import com.talanlabs.avatargenerator.element.URLElementRegistry;
import com.talanlabs.avatargenerator.functions.RandomColorizeFunction;
import com.talanlabs.avatargenerator.layers.backgrounds.RandomColorPaintBackgroundLayer;
import com.talanlabs.avatargenerator.layers.masks.RoundRectMaskLayer;
import com.talanlabs.avatargenerator.layers.others.RatioLayer;
import com.talanlabs.avatargenerator.layers.others.ShadowLayer;
import com.talanlabs.avatargenerator.layers.shadows.ScoreShadowLayer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.util.Collections;

public class SmileyAvatar {

	private static URLElementRegistry newAllElementRegistry() {
		URLElementRegistry elementRegistry = new URLElementRegistry();
		for (SmileyElementType smileyElementType : SmileyElementType.values()) {
			elementRegistry.putElement(smileyElementType.name(), URLElementRegistry
					.lsPngURLs(SmileyAvatar.class.getClassLoader(), smileyElementType.path));
		}
		return elementRegistry;
	}

	/**
	 * Create a default smiley avatar
	 */
	public static Avatar.AvatarBuilder newDefaultAvatarBuilder() {
		return Avatar.newBuilder().size(82, 82).elementRegistry(newDefaultElementRegistry());
	}

	/**
	 * New default element registry
	 */
	public static ElementRegistry newDefaultElementRegistry() {
		URLElementRegistry elementRegistry = newAllElementRegistry();

		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.shape.name()),
		                         ElementInfo.of(SmileyElementType.eyeBig.name()),
		                         ElementInfo.of(SmileyElementType.happyMouth.name()));
		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.shape.name()),
		                         ElementInfo.of(SmileyElementType.eyeBig.name()),
		                         ElementInfo.of(SmileyElementType.sadMouth.name()));
		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.moreShape.name()),
		                         ElementInfo.of(SmileyElementType.eyeBig.name()),
		                         ElementInfo.of(SmileyElementType.happyMouth.name()));
		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.moreShape.name()),
		                         ElementInfo.of(SmileyElementType.eyeBig.name()),
		                         ElementInfo.of(SmileyElementType.sadMouth.name()));
		elementRegistry
				.putGroup(ElementInfo.of(SmileyElementType.shape.name()), ElementInfo.of(SmileyElementType.eye.name()),
				          ElementInfo.of(SmileyElementType.eyebrow.name()),
				          ElementInfo.of(SmileyElementType.beard.name()));
		elementRegistry
				.putGroup(ElementInfo.of(SmileyElementType.shape.name()), ElementInfo.of(SmileyElementType.eye.name()),
				          ElementInfo.of(SmileyElementType.eyebrow.name()),
				          ElementInfo.of(SmileyElementType.glass.name()),
				          ElementInfo.of(SmileyElementType.happyMouth.name()));

		return elementRegistry;
	}

	/**
	 * Create a avatar with only accessories
	 */
	public static Avatar.AvatarBuilder newAccessoriesAvatarBuilder() {
		return Avatar.newBuilder().size(98, 98).elementRegistry(newAccessoriesElementRegistry())
				.colorizeFunction(new RandomColorizeFunction()).padding(8)
				.layers(new ShadowLayer(), new ScoreShadowLayer(), new RandomColorPaintBackgroundLayer(),
				        new RoundRectMaskLayer(), new ShadowLayer());
	}

	/**
	 * New accessories element registry
	 */
	public static ElementRegistry newAccessoriesElementRegistry() {
		URLElementRegistry elementRegistry = newAllElementRegistry();

		elementRegistry
				.putGroup(ElementInfo.of(SmileyElementType.beard.name()), ElementInfo.of(SmileyElementType.hat.name()));
		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.beard.name()),
		                         ElementInfo.of(SmileyElementType.glass.name()));
		elementRegistry
				.putGroup(ElementInfo.of(SmileyElementType.glass.name()), ElementInfo.of(SmileyElementType.hat.name()));
		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.beard.name()),
		                         ElementInfo.of(SmileyElementType.glass.name()),
		                         ElementInfo.of(SmileyElementType.hat.name()));
		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.mask.name()));

		return elementRegistry;
	}

	/**
	 * Create eye/mouth avatar
	 */
	public static Avatar.AvatarBuilder newEyeMouthAvatarBuilder() {
		return Avatar.newBuilder().size(82, 82).elementRegistry(newEyeMouthElementRegistry()).colorizeFunction(null)
				.padding(0)
				.layers(new RatioLayer(1.0), new ShadowLayer(), new ScoreShadowLayer(),
				        new RandomColorPaintBackgroundLayer(), new RoundRectMaskLayer(), new ShadowLayer());
	}

	/**
	 * New smiley element registry
	 */
	public static ElementRegistry newEyeMouthElementRegistry() {
		URLElementRegistry elementRegistry = newAllElementRegistry();
		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.eyeBig.name(), 0, -10),
		                         ElementInfo.of(SmileyElementType.happyMouth.name(), 0, 10));
		return elementRegistry;
	}

	/**
	 * Create a ghost avatar
	 */
	public static Avatar.AvatarBuilder newGhostAvatarBuilder() {
		return Avatar.newBuilder().size(82, 82).elementRegistry(newGhostElementRegistry())
				.colorizeFunction((avatarInfo, element) -> {
					if (SmileyElementType.moreShape.name().equals(element)) {
						Color color = AvatarUtils.defaultColors
								.get((int) (avatarInfo.getCode() % AvatarUtils.defaultColors.size()));
						return new Color(color.getRed(), color.getGreen(), color.getBlue(), 196);
					}
					return null;
				}).layers(new ShadowLayer());
	}

	/**
	 * New smiley element registry
	 */
	public static ElementRegistry newGhostElementRegistry() {
		URLElementRegistry elementRegistry = newAllElementRegistry();
		elementRegistry.putElement(SmileyElementType.moreShape.name(), Collections.singletonList(
				SmileyAvatar.class.getResource("/com/talanlabs/avatargenerator/smiley/images/MoreShape/30-2.png")));

		elementRegistry.putGroup(ElementInfo.of(SmileyElementType.moreShape.name()),
		                         ElementInfo.of(SmileyElementType.eyeBig.name(), 0, -5),
		                         ElementInfo.of(SmileyElementType.happyMouth.name(), 0, 5));
		return elementRegistry;
	}
}
