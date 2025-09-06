package androidx.work;

import android.util.Log;

public abstract class Logger {
    public static class LogcatLogger extends Logger {
        private final int mLoggingLevel;

        public LogcatLogger(int v) {
            super(v);
            this.mLoggingLevel = v;
        }

        @Override  // androidx.work.Logger
        public void debug(String s, String s1) {
            if(this.mLoggingLevel <= 3) {
                Log.d(s, s1);
            }
        }

        @Override  // androidx.work.Logger
        public void debug(String s, String s1, Throwable throwable0) {
            if(this.mLoggingLevel <= 3) {
                Log.d(s, s1, throwable0);
            }
        }

        @Override  // androidx.work.Logger
        public void error(String s, String s1) {
            if(this.mLoggingLevel <= 6) {
                Log.e(s, s1);
            }
        }

        @Override  // androidx.work.Logger
        public void error(String s, String s1, Throwable throwable0) {
            if(this.mLoggingLevel <= 6) {
                Log.e(s, s1, throwable0);
            }
        }

        @Override  // androidx.work.Logger
        public void info(String s, String s1) {
            if(this.mLoggingLevel <= 4) {
                Log.i(s, s1);
            }
        }

        @Override  // androidx.work.Logger
        public void info(String s, String s1, Throwable throwable0) {
            if(this.mLoggingLevel <= 4) {
                Log.i(s, s1, throwable0);
            }
        }

        @Override  // androidx.work.Logger
        public void verbose(String s, String s1) {
            if(this.mLoggingLevel <= 2) {
                Log.v(s, s1);
            }
        }

        @Override  // androidx.work.Logger
        public void verbose(String s, String s1, Throwable throwable0) {
            if(this.mLoggingLevel <= 2) {
                Log.v(s, s1, throwable0);
            }
        }

        @Override  // androidx.work.Logger
        public void warning(String s, String s1) {
            if(this.mLoggingLevel <= 5) {
                Log.w(s, s1);
            }
        }

        @Override  // androidx.work.Logger
        public void warning(String s, String s1, Throwable throwable0) {
            if(this.mLoggingLevel <= 5) {
                Log.w(s, s1, throwable0);
            }
        }
    }

    private static final int MAX_PREFIXED_TAG_LENGTH = 20;
    private static final int MAX_TAG_LENGTH = 23;
    private static final String TAG_PREFIX = "WM-";
    private static final Object sLock;
    private static volatile Logger sLogger;

    static {
        Logger.sLock = new Object();
    }

    public Logger(int v) {
    }

    public abstract void debug(String arg1, String arg2);

    public abstract void debug(String arg1, String arg2, Throwable arg3);

    public abstract void error(String arg1, String arg2);

    public abstract void error(String arg1, String arg2, Throwable arg3);

    public static Logger get() {
        synchronized(Logger.sLock) {
            if(Logger.sLogger == null) {
                Logger.sLogger = new LogcatLogger(3);
            }
            return Logger.sLogger;
        }
    }

    public abstract void info(String arg1, String arg2);

    public abstract void info(String arg1, String arg2, Throwable arg3);

    public static void setLogger(Logger logger0) {
        synchronized(Logger.sLock) {
            if(Logger.sLogger == null) {
                Logger.sLogger = logger0;
            }
        }
    }

    public static String tagWithPrefix(String s) [...] // 潜在的解密器

    public abstract void verbose(String arg1, String arg2);

    public abstract void verbose(String arg1, String arg2, Throwable arg3);

    public abstract void warning(String arg1, String arg2);

    public abstract void warning(String arg1, String arg2, Throwable arg3);
}

