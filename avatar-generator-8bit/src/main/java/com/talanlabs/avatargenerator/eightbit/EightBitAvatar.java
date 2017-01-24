package com.talanlabs.avatargenerator.eightbit;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;

public class EightBitAvatar {

	/**
	 * New male avatar builder with 8 bit
	 */
	public static Avatar.AvatarBuilder newMaleAvatarBuilder() {
		return Avatar.newBuilder().elementRegistry(newMaleElementRegistry());
	}

	/**
	 * New female avatar builder with 8 bit
	 */
	public static Avatar.AvatarBuilder newFemaleAvatarBuilder() {
		return Avatar.newBuilder().elementRegistry(newFemaleElementRegistry());
	}

	/**
	 * New cat element resgistry
	 */
	public static ElementRegistry newMaleElementRegistry() {
		ElementRegistry elementRegistry = new ElementRegistry();
		for (EightMaleElementType catElementType : EightMaleElementType.values()) {
			elementRegistry.putElement(catElementType.name(), ElementRegistry.lsPng(catElementType.path));
		}

		elementRegistry.putGroup(ElementInfo.of(EightMaleElementType.background.name()),
				ElementInfo.of(EightMaleElementType.face.name()),
				ElementInfo.of(EightMaleElementType.clothes.name()),
				ElementInfo.of(EightMaleElementType.hair.name()),
				ElementInfo.of(EightMaleElementType.eye.name()),
				ElementInfo.of(EightMaleElementType.mouth.name()));

		return elementRegistry;
	}

	/**
	 * New cat element resgistry
	 */
	public static ElementRegistry newFemaleElementRegistry() {
		ElementRegistry elementRegistry = new ElementRegistry();
		for (EightFemaleElementType catElementType : EightFemaleElementType.values()) {
			elementRegistry.putElement(catElementType.name(), ElementRegistry.lsPng(catElementType.path));
		}

		elementRegistry.putGroup(ElementInfo.of(EightFemaleElementType.background.name()),
				ElementInfo.of(EightFemaleElementType.face.name()),
				ElementInfo.of(EightFemaleElementType.clothes.name()),
				ElementInfo.of(EightFemaleElementType.hair.name()),
				ElementInfo.of(EightFemaleElementType.eye.name()),
				ElementInfo.of(EightFemaleElementType.mouth.name()));

		return elementRegistry;
	}
}
