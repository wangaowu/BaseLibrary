package com.unistrong.demo;

public class Sample1 {

    static {
        System.loadLibrary("cpp-lib");
    }

    public static   native String stringMethod();

//    public static native int intMethod();
}
