package com.ath.fuelsample.things;

import com.ath.fuel.Lazy;
import com.ath.fuelsample.Log;

public class CyclicalObject2 {

    // Why doesn't this break?
    // CyclicalObject1 injects CyclicalObject2
    // and
    // CyclicalObject2 injects CyclicalObject1
    //
    // Its because we only instantiate on lazy.get()
    // so its only infinite if you create an infinite loop of lazy.get()
    // For example:
    // Cyc1.doStuff() called Cyc2.doStuff()
    // and
    // Cyc2.doStuff() called Cyc1.doStuff()
    // this is obviously a infinite loop and no fault of Fuel's
    private final Lazy<CyclicalObject1> mCyclicalObject = Lazy.attain( this, CyclicalObject1.class );
    private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

    public CyclicalObject2() {
        // Assumes it will be injected so FuelInject.ignite is not necessary
        // However this object will by dynamically scoped by whatever
        // parent injected it and that could be different for each parent
        // so if you want to rely on a specific scope you should require it
        // maybe in documentation or placing an activity in the constructor
        // which also serves as code-doc and Fuel will auto provide the activity or run-time-fail
    }

    public void doStuff() {
        Log.d( "doStuff 2 %s", this );
        mCyclicalObject.get().doCycleTermination();
        mMemoryEater.get();
    }

}
