package com.talanlabs.avatargenerator.eightbit;

public enum EightFemaleElementType {

	background("com.talanlabs.avatargenerator.eightbit.images.background"),
	clothes("com.talanlabs.avatargenerator.eightbit.images.female.clothes"),
	eye("com.talanlabs.avatargenerator.eightbit.images.female.eye"),
	face("com.talanlabs.avatargenerator.eightbit.images.female.face"),
	hair("com.talanlabs.avatargenerator.eightbit.images.female.hair"),
	mouth("com.talanlabs.avatargenerator.eightbit.images.female.mouth");

	public final String path;

	EightFemaleElementType(String path) {
		this.path = path;
	}
}
