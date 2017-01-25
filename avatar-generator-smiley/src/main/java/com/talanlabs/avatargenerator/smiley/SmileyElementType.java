package com.talanlabs.avatargenerator.smiley;

public enum SmileyElementType {

	beard("com.talanlabs.avatargenerator.smiley.images.Beard"),
	eyebrow("com.talanlabs.avatargenerator.smiley.images.Eyebrows"),
	eye("com.talanlabs.avatargenerator.smiley.images.Eyes"),
	eyeBig(
			"com.talanlabs.avatargenerator.smiley.images.EyesBig"),
	glass("com.talanlabs.avatargenerator.smiley.images.Glasses"),
	hair("com.talanlabs.avatargenerator.smiley.images.Hair"),
	hand(
			"com.talanlabs.avatargenerator.smiley.images.Hands"),
	happyMouth("com.talanlabs.avatargenerator.smiley.images.HappyMouth"),
	hat(
			"com.talanlabs.avatargenerator.smiley.images.Hats"),
	mask("com.talanlabs.avatargenerator.smiley.images.Mask"),
	misc("com.talanlabs.avatargenerator.smiley.images.Misc"),
	moreShape(
			"com.talanlabs.avatargenerator.smiley.images.MoreShape"),
	nose("com.talanlabs.avatargenerator.smiley.images.Nose"),
	sadMouth(
			"com.talanlabs.avatargenerator.smiley.images.SadMouth"),
	shape("com.talanlabs.avatargenerator.smiley.images.Shape"),
	stache("com.talanlabs.avatargenerator.smiley.images.Stache");

    public final String path;

    SmileyElementType(String path) {
        this.path = path;
    }
}
