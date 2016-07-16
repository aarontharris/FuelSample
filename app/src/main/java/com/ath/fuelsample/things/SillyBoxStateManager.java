package com.ath.fuelsample.things;

import android.graphics.Color;

import com.ath.fuel.AppSingleton;

/**
 * A silly Singleton that satisfies a need to manage some state somewhere
 * for the purposes of our examples.
 */
@AppSingleton
public class SillyBoxStateManager {
	private int mColor = Color.BLACK;

	public void setColor( int color ) {
		mColor = color;
	}

	public int getColor() {
		return mColor;
	}

}
