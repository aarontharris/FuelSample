package com.ath.fuelsample.data;

import android.graphics.Color;

public class LittleBlueBox implements BoxColored {

    @Override public int getColor() {
        return Color.BLUE;
    }

    @Override public float getDimension() {
        return 1.0f;
    }

    // I'd make this a default implementation on the BoxColored interface if I had Java8 :(
    @Override public String toString() {
        return String.format( "%s[%x] color='%s', dimension='%s'",
                getClass().getSimpleName(),
                hashCode(),
                getColor(),
                getDimension()
        );
    }
}
