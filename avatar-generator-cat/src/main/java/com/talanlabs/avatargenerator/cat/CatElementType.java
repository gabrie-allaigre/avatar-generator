package com.talanlabs.avatargenerator.cat;

public enum CatElementType {

	accessorie("com.talanlabs.avatargenerator.cat.images.accessories"),
	body("com.talanlabs.avatargenerator.cat.images.bodies"),
	eyes("com.talanlabs.avatargenerator.cat.images.eyes"),
	fur("com.talanlabs.avatargenerator.cat.images.furs"),
	mouth("com.talanlabs.avatargenerator.cat.images.mouths"),
	zz("com.talanlabs.avatargenerator.cat.images.zzs");

	public final String path;

	CatElementType(String path) {
		this.path = path;
	}
}
