// IMyAidlInterface.aidl
package com.example.client;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

/** Request the process ID of this service, to do evil things with it. */
    int getPid();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
