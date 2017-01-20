package com.talanlabs.avatargenerator.functions;

import com.talanlabs.avatargenerator.Avatar;
import com.talanlabs.avatargenerator.IAvatarInfo;
import com.talanlabs.avatargenerator.utils.AvatarUtils;

import java.awt.Color;
import java.util.List;

public class RandomColorizeFunction implements Avatar.IColorizeFunction {

	private List<Color> colors;
	private Color whiteColor;
	private Color blackColor;

	public RandomColorizeFunction() {
		this(AvatarUtils.defaultColors, Color.BLACK, Color.WHITE);
	}

	public RandomColorizeFunction(List<Color> colors, Color blackColor, Color whiteColor) {
		super();

		this.colors = colors;
		this.whiteColor = whiteColor;
		this.blackColor = blackColor;
	}

	@Override
	public Color colorize(IAvatarInfo avatarInfo, String element) {
		Color backColor = colors.get((int) (avatarInfo.getCode() % colors.size()));
		return AvatarUtils.getComplementColor(backColor, blackColor, whiteColor);
	}
}
