package com.ath.fuelsample.things;

import com.ath.fuel.AppSingleton;
import com.ath.fuelsample.Log;

@AppSingleton
public class MyClass {

    public MyClass() {
        Log.d( "MyClass Constructed" );
    }

    public MyClass( SomeThing someThing ) {
        Log.d( "MyClass Constructed with %s", someThing );
    }

}
