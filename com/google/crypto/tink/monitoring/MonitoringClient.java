package com.google.crypto.tink.monitoring;

public interface MonitoringClient {
    public interface Logger {
        void log(int arg1, long arg2);

        void logFailure();
    }

    Logger createLogger(MonitoringKeysetInfo arg1, String arg2, String arg3);
}

