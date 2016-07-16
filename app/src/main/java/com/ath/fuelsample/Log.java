package com.ath.fuelsample;

public class Log {
    private static final String LOG_TAG = "FUELSAMPLE";

    public static void d( String format, Object...args ) {
        android.util.Log.d( LOG_TAG, String.format( format, args ) );
    }

    public static void e( Exception e, String format, Object...args ) {
        android.util.Log.e( LOG_TAG, String.format( format, args ), e );
    }

}
