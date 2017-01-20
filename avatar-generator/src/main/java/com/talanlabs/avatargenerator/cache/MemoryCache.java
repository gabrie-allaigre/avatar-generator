package com.talanlabs.avatargenerator.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutionException;

public class MemoryCache implements ICache {

	private Cache<String, BufferedImage> cache = CacheBuilder.newBuilder().softValues().build();

	@Override
	public BufferedImage get(IAvatarInfo avatarInfo, ILoader loader) {
		String key = avatarInfo.getWidth() + "-" + avatarInfo.getHeight() + "-" + avatarInfo
				.getMargin() + "-" + avatarInfo.getPadding() + "-" + avatarInfo.getCode();
		try {
			return cache.get(key, () -> loader.load(avatarInfo));
		} catch (ExecutionException e) {
			throw new RuntimeException("Failed to get image from " + key, e);
		}
	}
}
