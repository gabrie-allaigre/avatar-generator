package com.talanlabs.avatargenerator.element;

import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.element.identicon.NineBlockIdenticonRenderer;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.nio.file.Path;

public class IdenticonElementRegistry extends ElementRegistry {

	private final NineBlockIdenticonRenderer nineBlockIdenticonRenderer;

	public IdenticonElementRegistry() {
		this(new NineBlockIdenticonRenderer());
	}

	public IdenticonElementRegistry(NineBlockIdenticonRenderer nineBlockIdenticonRenderer) {
		super();

		this.nineBlockIdenticonRenderer = nineBlockIdenticonRenderer;
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
		return new ElementInfo[]{ElementInfo.of("identicon")};
	}

	private Path buildImage(IAvatarInfo avatarInfo) {
		int code = (int) (avatarInfo.getCode() / Math.pow(2, 32));
		int size = Math.min(avatarInfo.getWidth() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2, avatarInfo
				.getHeight() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2);
		return AvatarUtils.saveImageInTemp(nineBlockIdenticonRenderer.render(code, size));
	}
}
