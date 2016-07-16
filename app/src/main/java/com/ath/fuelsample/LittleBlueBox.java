package com.ath.fuelsample;

import android.graphics.Color;

public class LittleBlueBox implements BoxColored {

	@Override public int getColor() {
		return Color.BLUE;
	}

	@Override public float getDimension() {
		return 1.0f;
	}

}
