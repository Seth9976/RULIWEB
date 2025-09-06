package com.google.crypto.tink.internal;

import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import java.util.concurrent.atomic.AtomicReference;

public final class MutableMonitoringRegistry {
    static class DoNothingClient implements MonitoringClient {
        private DoNothingClient() {
        }

        DoNothingClient(com.google.crypto.tink.internal.MutableMonitoringRegistry.1 mutableMonitoringRegistry$10) {
        }

        @Override  // com.google.crypto.tink.monitoring.MonitoringClient
        public Logger createLogger(MonitoringKeysetInfo monitoringKeysetInfo0, String s, String s1) {
            return MonitoringUtil.DO_NOTHING_LOGGER;
        }
    }

    private static final DoNothingClient DO_NOTHING_CLIENT;
    private static final MutableMonitoringRegistry GLOBAL_INSTANCE;
    private final AtomicReference monitoringClient;

    static {
        MutableMonitoringRegistry.GLOBAL_INSTANCE = new MutableMonitoringRegistry();
        MutableMonitoringRegistry.DO_NOTHING_CLIENT = new DoNothingClient(null);
    }

    public MutableMonitoringRegistry() {
        this.monitoringClient = new AtomicReference();
    }

    public void clear() {
        synchronized(this) {
            this.monitoringClient.set(null);
        }
    }

    public MonitoringClient getMonitoringClient() {
        MonitoringClient monitoringClient0 = (MonitoringClient)this.monitoringClient.get();
        return monitoringClient0 == null ? MutableMonitoringRegistry.DO_NOTHING_CLIENT : monitoringClient0;
    }

    public static MutableMonitoringRegistry globalInstance() {
        return MutableMonitoringRegistry.GLOBAL_INSTANCE;
    }

    public void registerMonitoringClient(MonitoringClient monitoringClient0) {
        synchronized(this) {
            if(this.monitoringClient.get() == null) {
                this.monitoringClient.set(monitoringClient0);
                return;
            }
        }
        throw new IllegalStateException("a monitoring client has already been registered");
    }

    class com.google.crypto.tink.internal.MutableMonitoringRegistry.1 {
    }

}

