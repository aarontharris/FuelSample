package com.ath.fuelsample;

import android.graphics.Color;

public class LittleBlackBox implements BoxColored {

	@Override public int getColor() {
		return Color.BLACK;
	}

	@Override public float getDimension() {
		return 1.0f;
	}

}
