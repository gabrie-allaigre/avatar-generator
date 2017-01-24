package com.talanlabs.avatargenerator;

import com.talanlabs.avatargenerator.element.TriangleElementRegistry;

import java.awt.Color;
import java.util.Arrays;

public class TriangleAvatar {

	/**
	 * New avatar builder with triangles
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder() {
		return Avatar.newBuilder().elementRegistry(new TriangleElementRegistry());
	}

	/**
	 * New avatar builder with triangles
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder(Color... colors) {
		return Avatar.newBuilder().elementRegistry(new TriangleElementRegistry(8, Arrays.asList(colors)));
	}
}
