package com.magic.klaus.vsa.presenter;

/**
 * creater: klaus
 * create time： 2017-10-15 下午11:40
 * describe： a test for Opencv and jni. Delete or refactor it if needed.
 */

public class PreprocessPresenter {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("opencv_java3");
    }

    public static native int[] getGrayImage(int[] pixels, int w, int h);
    public static native String stringFromJNI();
}
