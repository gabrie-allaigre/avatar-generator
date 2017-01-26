package com.talanlabs.avatargenerator.eightbit;

public enum EightMaleElementType {

	background("com.talanlabs.avatargenerator.eightbit.images.background"),
	clothes("com.talanlabs.avatargenerator.eightbit.images.male.clothes"),
	eye("com.talanlabs.avatargenerator.eightbit.images.male.eye"),
	face("com.talanlabs.avatargenerator.eightbit.images.male.face"),
	hair("com.talanlabs.avatargenerator.eightbit.images.male.hair"),
	mouth("com.talanlabs.avatargenerator.eightbit.images.male.mouth");

	public final String path;

	EightMaleElementType(String path) {
		this.path = path;
	}
}
