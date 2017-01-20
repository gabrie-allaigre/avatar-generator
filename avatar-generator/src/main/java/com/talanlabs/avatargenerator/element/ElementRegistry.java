package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ElementRegistry {

	private final Map<String, List<Path>> elementMap = new HashMap<>();
	private final List<ElementInfo[]> groups = new ArrayList<>();

	public ElementRegistry() {
		super();
	}

	/**
	 * Load all png
	 *
	 * @param path who is png files
	 * @return list of path
	 */
	public static List<Path> lsPng(String path) {
		try {
			URI uri = ElementRegistry.class.getResource(path).toURI();
			Path myPath;
			if (uri.getScheme().equals("jar")) {
				FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
				myPath = fileSystem.getPath(path);
			} else {
				myPath = Paths.get(uri);
			}

			PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.png");
			return Files.walk(myPath, 1).filter(matcher::matches).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("Failed to loading resources", e);
		}
	}

	/**
	 * Add element
	 *
	 * @param name  name of element
	 * @param paths list of path
	 */
	public void putElement(String name, List<Path> paths) {
		elementMap.put(name, paths);
	}

	/**
	 * @param name name of element
	 * @return size of element
	 */
	public int getElementCount(IAvatarInfo avatarInfo, String name) {
		return elementMap.get(name) != null ? elementMap.get(name).size() : 0;
	}

	/**
	 * @param name  name of element
	 * @param index position in element
	 * @return a path of element
	 */
	public Path getElement(IAvatarInfo avatarInfo, String name, int index) {
		return elementMap.get(name).get(index);
	}

	public void putGroup(ElementInfo... elementInfos) {
		groups.add(elementInfos);
	}

	public int getGroupCount() {
		return groups.size();
	}

	public ElementInfo[] getGroup(IAvatarInfo avatarInfo, int index) {
		return groups.get(index);
	}

}
