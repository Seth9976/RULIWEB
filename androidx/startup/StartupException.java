package androidx.startup;

public final class StartupException extends RuntimeException {
    public StartupException(String s) {
        super(s);
    }

    public StartupException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public StartupException(Throwable throwable0) {
        super(throwable0);
    }
}

