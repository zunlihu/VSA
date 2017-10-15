package com.magic.klaus.vsa.presenter;

/**
 * 创建者: chengfeng
 * 创建时间： 2017-10-15 下午11:40
 * 任务号：
 * 描述：
 */

public class PreprocessPresenter {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("opencv_java3");
    }

    public static native int[] getGrayImage(int[] pixels, int w, int h);
    public static native String stringFromJNI();
}
