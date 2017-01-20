package com.talanlabs.avatargenerator;

import java.util.Random;

public interface IAvatarInfo {

	/**
	 * @return current code
	 */
	long getCode();

	/**
	 * @return current random
	 */
	Random getRandom();

	/**
	 * @return avatar witdh
	 */
	int getWidth();

	/**
	 * @return avatar height
	 */
	int getHeight();

	/**
	 * @return avatar padding
	 */
	int getPadding();

	/**
	 * @return avatar margin
	 */
	int getMargin();

}
