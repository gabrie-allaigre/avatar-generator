package com.talanlabs.avatargenerator;

import com.talanlabs.avatargenerator.element.GitHubElementRegistry;

public class GitHubAvatar {

	/**
	 * New avatar builder same GitHub
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder() {
		return Avatar.newBuilder().elementRegistry(new GitHubElementRegistry());
	}

	/**
	 * New avatar builder same GitHub
	 */
	public static Avatar.AvatarBuilder newAvatarBuilder(int size, int precision) {
		return Avatar.newBuilder().elementRegistry(new GitHubElementRegistry(size, precision));
	}
}
