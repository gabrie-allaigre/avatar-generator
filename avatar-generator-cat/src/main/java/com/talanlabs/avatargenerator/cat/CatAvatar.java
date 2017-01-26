package com.talanlabs.avatargenerator.cat;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.element.ElementInfo;
import com.talanlabs.avatargenerator.element.ElementRegistry;
import com.talanlabs.avatargenerator.element.URLElementRegistry;

public class CatAvatar {

	/**
	 * New avatar builder with cat
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder() {
		return Avatar.newBuilder().elementRegistry(newElementRegistry());
	}

	/**
	 * New cat element resgistry
	 */
	public static ElementRegistry newElementRegistry() {
		URLElementRegistry elementRegistry = new URLElementRegistry();
		for (CatElementType catElementType : CatElementType.values()) {
			elementRegistry.putElement(catElementType.name(), URLElementRegistry
					.lsPngURLs(CatAvatar.class.getClassLoader(), catElementType.path));
		}

		elementRegistry.putGroup(ElementInfo.of(CatElementType.body.name()), ElementInfo.of(CatElementType.fur.name()),
		                         ElementInfo.of(CatElementType.eyes.name()),
		                         ElementInfo.of(CatElementType.mouth.name()));
		elementRegistry.putGroup(ElementInfo.of(CatElementType.body.name()), ElementInfo.of(CatElementType.fur.name()),
		                         ElementInfo.of(CatElementType.eyes.name()),
		                         ElementInfo.of(CatElementType.mouth.name()),
		                         ElementInfo.of(CatElementType.accessorie.name()));
		elementRegistry.putGroup(ElementInfo.of(CatElementType.body.name()), ElementInfo.of(CatElementType.fur.name()),
		                         ElementInfo.of(CatElementType.eyes.name()),
		                         ElementInfo.of(CatElementType.mouth.name()), ElementInfo.of(CatElementType.zz.name()));

		return elementRegistry;
	}

}
