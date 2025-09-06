package com.airbnb.lottie.utils;

import android.util.Log;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieLogger;
import java.util.HashSet;
import java.util.Set;

public class LogcatLogger implements LottieLogger {
    private static final Set loggedMessages;

    static {
        LogcatLogger.loggedMessages = new HashSet();
    }

    @Override  // com.airbnb.lottie.LottieLogger
    public void debug(String s) {
        this.debug(s, null);
    }

    @Override  // com.airbnb.lottie.LottieLogger
    public void debug(String s, Throwable throwable0) {
        if(L.DBG) {
            Log.d("LOTTIE", s, throwable0);
        }
    }

    @Override  // com.airbnb.lottie.LottieLogger
    public void warning(String s) {
        this.warning(s, null);
    }

    @Override  // com.airbnb.lottie.LottieLogger
    public void warning(String s, Throwable throwable0) {
        Set set0 = LogcatLogger.loggedMessages;
        if(set0.contains(s)) {
            return;
        }
        Log.w("LOTTIE", s, throwable0);
        set0.add(s);
    }
}

