package com.ath.fuelsample.things;

import com.ath.fuel.Lazy;
import com.ath.fuel.RequiresInjection;
import com.ath.fuelsample.Log;

@RequiresInjection
public class CyclicalObject1 {
    private final Lazy<CyclicalObject2> mCyclicalObject = Lazy.attain( this, CyclicalObject2.class );
    private final Lazy<MemoryEater> mMemoryEater = Lazy.attain( this, MemoryEater.class );

    public CyclicalObject1() {
        // Assumes it will be injected so FuelInject.ignite is not necessary
        // However this object will by dynamically scoped by whatever
        // parent injected it and that could be different for each parent
        // so if you want to rely on a specific scope you should require it
        // maybe in documentation or placing an activity in the constructor
        // which also serves as code-doc and Fuel will auto provide the activity or run-time-fail
    }

    public void doStuff() {
        Log.d( "doStuff 1 %s", this );
        mCyclicalObject.get().doStuff();
        mMemoryEater.get();
    }

    public void doCycleTermination() {
        Log.d( "the end %s", this );
    }
}
