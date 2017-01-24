package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class GitHubElementRegistry extends ElementRegistry {

	private final int size;
	private final int precision;

	public GitHubElementRegistry() {
		this(400, 3);
	}

	public GitHubElementRegistry(int size, int precision) {
		super();

		this.size = size;
		this.precision = precision;
	}

	@Override
	public int getElementCount(IAvatarInfo avatarInfo, String name) {
		return 1;
	}

	@Override
	public Path getElement(IAvatarInfo avatarInfo, String name, int index) {
		return buildImage(avatarInfo);
	}

	@Override
	public int getGroupCount() {
		return 1;
	}

	@Override
	public ElementInfo[] getGroup(IAvatarInfo avatarInfo, int index) {
		return new ElementInfo[]{ElementInfo.of("github")};
	}

	private String addZeroes(String ns, int nd) {
		for (int i = 0; i < nd - ns.length(); i++) {
			ns = "0" + ns;
		}
		return ns;
	}

	private Path buildImage(IAvatarInfo avatarInfo) {
		BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = bufferedImage.createGraphics();
		AvatarUtils.activeAntialiasing(g2);

		String n = addZeroes(Long.toHexString(avatarInfo.getCode()), 6);
		g2.setColor(new Color(Integer.parseInt(n.substring(0, 6), 16)));

		int mult = size / ((precision * 2) - 1);
		for (int x = 0; x < precision; x++) {
			for (int y = 0; y < precision * 2; y++) {
				if (avatarInfo.getRandom().nextDouble() < 0.5) {
					g2.fillRect(x * mult, y * mult, mult, mult);
					g2.fillRect(size - (x + 1) * mult, y * mult, mult, mult);
				}
			}
		}

		g2.dispose();

		return AvatarUtils.saveImageInTemp(bufferedImage);
	}
}
