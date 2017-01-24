package com.talanlabs.avatargenerator;

import com.talanlabs.avatargenerator.element.IdenticonElementRegistry;
import com.talanlabs.avatargenerator.element.identicon.NineBlockIdenticonRenderer;

public class IdenticonAvatar {

	/**
	 * New avatar builder same Identicon
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder() {
		return Avatar.newBuilder().elementRegistry(new IdenticonElementRegistry());
	}

	/**
	 * New avatar builder same Identicon
	 *
	 * @param nineBlockIdenticonRenderer special renderer
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder(NineBlockIdenticonRenderer nineBlockIdenticonRenderer) {
		return Avatar.newBuilder().elementRegistry(new IdenticonElementRegistry(nineBlockIdenticonRenderer));
	}
}
