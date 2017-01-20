package com.talanlabs.avatargenerator.cache;

import com.talanlabs.avatargenerator.IAvatarInfo;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCache implements ICache {

	private final Path rootPathDir;

	public FileCache() {
		super();

		try {
			this.rootPathDir = Files.createTempDirectory("avatar");
		} catch (IOException e) {
			throw new RuntimeException("Failed to create a temp directory", e);
		}
	}

	public FileCache(Path rootPathDir) {
		super();

		this.rootPathDir = rootPathDir;
	}

	@Override
	public BufferedImage get(IAvatarInfo avatarInfo, ILoader loader) {
		String dir = avatarInfo.getWidth() + "-" + avatarInfo.getHeight() + "-" + avatarInfo
				.getMargin() + "-" + avatarInfo.getPadding();
		Path pathDir = Paths.get(rootPathDir.toString(), dir);

		if (!Files.exists(pathDir)) {
			try {
				Files.createDirectories(pathDir);
			} catch (IOException e) {
				throw new RuntimeException("Failed to create directories for " + pathDir.toString(), e);
			}
		}

		BufferedImage bufferedImage;

		String imageName = avatarInfo.getCode() + ".png";
		Path imagePath = Paths.get(pathDir.toString(), imageName);
		if (Files.exists(imagePath)) {
			try {
				bufferedImage = ImageIO.read(imagePath.toFile());
			} catch (IOException e) {
				throw new RuntimeException("Failed to read image for " + imagePath.toString(), e);
			}
		} else {
			bufferedImage = loader.load(avatarInfo);
			try {
				ImageIO.write(bufferedImage, "png", imagePath.toFile());
			} catch (IOException e) {
				throw new RuntimeException("Failed to write image for " + imagePath.toString(), e);
			}
		}
		return bufferedImage;
	}
}
