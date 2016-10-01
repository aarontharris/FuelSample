package com.ath.fuelsample.things;

import com.ath.fuel.AppSingleton;
import com.ath.fuelsample.Log;

@AppSingleton
public class SimpleAppSingleton {

    public void helloWorld() {
        Log.d( "Hello World" );
    }

}
