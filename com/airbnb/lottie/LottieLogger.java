package com.airbnb.lottie;

public interface LottieLogger {
    void debug(String arg1);

    void debug(String arg1, Throwable arg2);

    void warning(String arg1);

    void warning(String arg1, Throwable arg2);
}

