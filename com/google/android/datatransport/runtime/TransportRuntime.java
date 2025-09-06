package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TransportRuntime implements TransportInternal {
    private final Clock eventClock;
    private static volatile TransportRuntimeComponent instance;
    private final Scheduler scheduler;
    private final Uploader uploader;
    private final Clock uptimeClock;

    static {
    }

    @Inject
    TransportRuntime(Clock clock0, Clock clock1, Scheduler scheduler0, Uploader uploader0, WorkInitializer workInitializer0) {
        this.eventClock = clock0;
        this.uptimeClock = clock1;
        this.scheduler = scheduler0;
        this.uploader = uploader0;
        workInitializer0.ensureContextsScheduled();
    }

    private EventInternal convert(SendRequest sendRequest0) {
        return EventInternal.builder().setEventMillis(this.eventClock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName(sendRequest0.getTransportName()).setEncodedPayload(new EncodedPayload(sendRequest0.getEncoding(), sendRequest0.getPayload())).setCode(sendRequest0.getEvent().getCode()).build();
    }

    public static TransportRuntime getInstance() {
        TransportRuntimeComponent transportRuntimeComponent0 = TransportRuntime.instance;
        if(transportRuntimeComponent0 == null) {
            throw new IllegalStateException("Not initialized!");
        }
        return transportRuntimeComponent0.getTransportRuntime();
    }

    // 去混淆评级： 低(20)
    private static Set getSupportedEncodings(Destination destination0) {
        return destination0 instanceof EncodedDestination ? Collections.unmodifiableSet(((EncodedDestination)destination0).getSupportedEncodings()) : Collections.singleton(Encoding.of("proto"));
    }

    public Uploader getUploader() {
        return this.uploader;
    }

    public static void initialize(Context context0) {
        if(TransportRuntime.instance == null) {
            Class class0 = TransportRuntime.class;
            synchronized(class0) {
                if(TransportRuntime.instance == null) {
                    TransportRuntime.instance = DaggerTransportRuntimeComponent.builder().setApplicationContext(context0).build();
                }
            }
        }
    }

    public TransportFactory newFactory(Destination destination0) {
        return new TransportFactoryImpl(TransportRuntime.getSupportedEncodings(destination0), TransportContext.builder().setBackendName(destination0.getName()).setExtras(destination0.getExtras()).build(), this);
    }

    @Deprecated
    public TransportFactory newFactory(String s) {
        return new TransportFactoryImpl(TransportRuntime.getSupportedEncodings(null), TransportContext.builder().setBackendName(s).build(), this);
    }

    @Override  // com.google.android.datatransport.runtime.TransportInternal
    public void send(SendRequest sendRequest0, TransportScheduleCallback transportScheduleCallback0) {
        TransportContext transportContext0 = sendRequest0.getTransportContext().withPriority(sendRequest0.getEvent().getPriority());
        EventInternal eventInternal0 = this.convert(sendRequest0);
        this.scheduler.schedule(transportContext0, eventInternal0, transportScheduleCallback0);
    }

    static void withInstance(TransportRuntimeComponent transportRuntimeComponent0, Callable callable0) throws Throwable {
        TransportRuntimeComponent transportRuntimeComponent1;
        synchronized(TransportRuntime.class) {
            transportRuntimeComponent1 = TransportRuntime.instance;
            TransportRuntime.instance = transportRuntimeComponent0;
        }
        try {
            callable0.call();
        }
        finally {
            synchronized(TransportRuntime.class) {
                TransportRuntime.instance = transportRuntimeComponent1;
            }
        }
    }
}

