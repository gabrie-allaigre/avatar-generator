package com.talanlabs.avatargenerator.eightbit;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;
import com.talanlabs.avatargenerator.element.URLElementRegistry;

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
		URLElementRegistry elementRegistry = new URLElementRegistry();
		for (EightMaleElementType eightMaleElementType : EightMaleElementType.values()) {
			elementRegistry.putElement(eightMaleElementType.name(), URLElementRegistry
					.lsPngURLs(EightBitAvatar.class.getClassLoader(), eightMaleElementType.path));
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
		URLElementRegistry elementRegistry = new URLElementRegistry();
		for (EightFemaleElementType eightFemaleElementType : EightFemaleElementType.values()) {
			elementRegistry.putElement(eightFemaleElementType.name(), URLElementRegistry
					.lsPngURLs(EightBitAvatar.class.getClassLoader(), eightFemaleElementType.path));
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
