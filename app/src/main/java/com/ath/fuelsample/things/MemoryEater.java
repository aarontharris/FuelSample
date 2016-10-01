package com.ath.fuelsample.things;

import com.ath.fuelsample.Log;

public class MemoryEater {

    private byte[] data = null;

    public void eat( int numBytes ) {
        Log.d( "Allocating %s bytes", numBytes );
        data = new byte[numBytes];
    }

}
