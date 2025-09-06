package com.airbnb.lottie.utils;

import com.airbnb.lottie.LottieLogger;

public class Logger {
    private static LottieLogger INSTANCE;

    static {
        Logger.INSTANCE = new LogcatLogger();
    }

    public static void debug(String s) {
        Logger.INSTANCE.debug(s);
    }

    public static void debug(String s, Throwable throwable0) {
        Logger.INSTANCE.debug(s, throwable0);
    }

    public static void setInstance(LottieLogger lottieLogger0) {
        Logger.INSTANCE = lottieLogger0;
    }

    public static void warning(String s) {
        Logger.INSTANCE.warning(s);
    }

    public static void warning(String s, Throwable throwable0) {
        Logger.INSTANCE.warning(s, throwable0);
    }
}

