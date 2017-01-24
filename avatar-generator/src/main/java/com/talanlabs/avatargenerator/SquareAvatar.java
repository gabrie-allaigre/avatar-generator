package com.talanlabs.avatargenerator;

import com.talanlabs.avatargenerator.element.SquareElementRegistry;

import java.awt.Color;
import java.util.Arrays;

public class SquareAvatar {

	/**
	 * New avatar builder with squares
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder() {
		return Avatar.newBuilder().elementRegistry(new SquareElementRegistry());
	}

	/**
	 * New avatar builder with squares
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder(Color... colors) {
		return Avatar.newBuilder().elementRegistry(new SquareElementRegistry(3, Arrays.asList(colors)));
	}
}
