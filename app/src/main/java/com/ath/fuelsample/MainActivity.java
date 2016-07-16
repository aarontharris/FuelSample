package com.ath.fuelsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ath.fuel.FuelInjector;
import com.ath.fuel.Lazy;

public class MainActivity extends AppCompatActivity {
    // MainActivity is the parent
    // mAppSingleton and mActivitySingleton are the dependencies
    // parents are scoped by the context they use ignite themselves
    // Activities are a context, so naturally they are Activity Scoped.
    // Activity Scope is finer than App Scope and App Scope is broader than Activity Scope.
    // Finer scope has visibility to broader scope, but broader scope does not have visibility to finer scope.
    // That means App Scoped Singletons cannot inject Activity Scoped Singletons
    // However Activity Scoped Singletons can inject App Scoped Singletons.
    //
    // In this case, MainActivity is the parent and scoped as Activity.
    // Therefore it is a finer scope than App and able to inject Activity and App Scoped Singletons.
    // FuelSampleApp will not be able to inject Activity Scoped Singletons
    // As you can imagine, it would not know which activity too choose from
    // since an App has a one-to-many relationship with Activities.

    private final Lazy<SampleAppSingleton> mAppSingleton = Lazy.attain( this, SampleAppSingleton.class );
    private final Lazy<SampleActivitySingleton> mActivitySingleton = Lazy.attain( this, SampleActivitySingleton.class );
    private final Lazy<SampleExternalPojo> mPojo = Lazy.attain( this, SampleExternalPojo.class );

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        FuelInjector.ignite( this, this );

        Log.d( mAppSingleton.get().getHelloWorld() );
        Log.d( mActivitySingleton.get().getHelloWorld() );
        Log.d( mPojo.get().doStuff() );
    }
}
