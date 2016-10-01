package com.ath.fuelsample;

import com.ath.fuel.FLog;

public class Log {
    private static final String LOG_TAG = "FUELSAMPLE";

    public static void d( String format, Object... args ) {
        android.util.Log.d( LOG_TAG, format( format, args ) );
    }

    public static void e( Exception e, String format, Object... args ) {
        android.util.Log.e( LOG_TAG, format( format, args ), e );
    }

    private static String format( String format, Object... args ) {
        return defaultPrefix() + String.format( format, args );
    }

    private static String defaultPrefix() {
        StackTraceElement elem = FLog.findStackElem( Log.class );
        String callingClassName = FLog.getSimpleName( elem );
        int lineNo = elem.getLineNumber();
        return callingClassName + "[" + lineNo + "]: ";
    }
}
