package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.AvatarException;
import com.talanlabs.avatargenerator.IAvatarInfo;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class URLElementRegistry extends AbstractElementRegistry {

	private static final Pattern PNG_PATTERN = Pattern.compile(".*.png");

	private Map<String, List<URL>> elementMap = new HashMap<>();

	/**
	 * Load all urls with png
	 *
	 * @param path who is png files
	 * @return list of url
	 */
	public static List<URL> lsPngURLs(ClassLoader classLoader, String path) {
		Reflections reflections = new Reflections(path, new ResourcesScanner(), classLoader);
		Set<String> ss = reflections.getResources(PNG_PATTERN);
		return ss != null ?
				ss.stream().sorted(String::compareTo).map(classLoader::getResource).collect(Collectors.toList()) :
				Collections.emptyList();
	}

	public void putElement(String name, List<URL> urls) {
		elementMap.put(name, urls != null ? new ArrayList<>(urls) : null);
	}

	@Override
	public int getElementCount(IAvatarInfo avatarInfo, String name) {
		return elementMap.containsKey(name) ? elementMap.get(name).size() : 0;
	}

	@Override
	public Image getElement(IAvatarInfo avatarInfo, String name, int index) {
		URL url = elementMap.get(name).get(index);
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			throw new AvatarException("Failed to load image " + url, e);
		}
	}
}
