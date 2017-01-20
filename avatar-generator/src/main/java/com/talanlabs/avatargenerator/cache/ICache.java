package com.talanlabs.avatargenerator.cache;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public interface ICache {

	BufferedImage get(IAvatarInfo avatarInfo, ILoader loader);

	interface ILoader {

		BufferedImage load(IAvatarInfo avatarInfo);

	}

	/**
	 * Use temp dir for cache
	 */
	static ICache tempCache() {
		MemoryCache mc = new MemoryCache();
		FileCache fc = new FileCache();
		return (a, l) -> mc.get(a, a1 -> fc.get(a1, l));
	}

	/**
	 * Use root dir for cache
	 */
	static ICache defaultCache(Path rootPath) {
		MemoryCache mc = new MemoryCache();
		FileCache fc = new FileCache(rootPath);
		return (a, l) -> mc.get(a, a1 -> fc.get(a1, l));
	}
}
