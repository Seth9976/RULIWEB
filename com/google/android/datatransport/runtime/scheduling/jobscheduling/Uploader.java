package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse.Status;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped.Reason;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.inject.Inject;

public class Uploader {
    private static final String CLIENT_HEALTH_METRICS_LOG_SOURCE = "GDT_CLIENT_METRICS";
    private static final String LOG_TAG = "Uploader";
    private final BackendRegistry backendRegistry;
    private final ClientHealthMetricsStore clientHealthMetricsStore;
    private final Clock clock;
    private final Context context;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final Clock uptimeClock;
    private final WorkScheduler workScheduler;

    @Inject
    public Uploader(Context context0, BackendRegistry backendRegistry0, EventStore eventStore0, WorkScheduler workScheduler0, Executor executor0, SynchronizationGuard synchronizationGuard0, Clock clock0, Clock clock1, ClientHealthMetricsStore clientHealthMetricsStore0) {
        this.context = context0;
        this.backendRegistry = backendRegistry0;
        this.eventStore = eventStore0;
        this.workScheduler = workScheduler0;
        this.executor = executor0;
        this.guard = synchronizationGuard0;
        this.clock = clock0;
        this.uptimeClock = clock1;
        this.clientHealthMetricsStore = clientHealthMetricsStore0;
    }

    public EventInternal createMetricsEvent(TransportBackend transportBackend0) {
        Objects.requireNonNull(this.clientHealthMetricsStore);
        Uploader..ExternalSyntheticLambda4 uploader$$ExternalSyntheticLambda40 = new Uploader..ExternalSyntheticLambda4(this.clientHealthMetricsStore);
        ClientMetrics clientMetrics0 = (ClientMetrics)this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda40);
        return transportBackend0.decorate(EventInternal.builder().setEventMillis(this.clock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName("GDT_CLIENT_METRICS").setEncodedPayload(new EncodedPayload(Encoding.of("proto"), new byte[0])).build());
    }

    boolean isNetworkAvailable() {
        NetworkInfo networkInfo0 = ((ConnectivityManager)this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkInfo0 != null && networkInfo0.isConnected();
    }

    // 检测为 Lambda 实现
    Boolean lambda$logAndUpdateState$2$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(TransportContext transportContext0) [...]

    // 检测为 Lambda 实现
    Iterable lambda$logAndUpdateState$3$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(TransportContext transportContext0) [...]

    // 检测为 Lambda 实现
    Object lambda$logAndUpdateState$4$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(Iterable iterable0, TransportContext transportContext0, long v) [...]

    // 检测为 Lambda 实现
    Object lambda$logAndUpdateState$5$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(Iterable iterable0) [...]

    // 检测为 Lambda 实现
    Object lambda$logAndUpdateState$6$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader() [...]

    // 检测为 Lambda 实现
    Object lambda$logAndUpdateState$7$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(Map map0) [...]

    // 检测为 Lambda 实现
    Object lambda$logAndUpdateState$8$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(TransportContext transportContext0, long v) [...]

    // 检测为 Lambda 实现
    Object lambda$upload$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(TransportContext transportContext0, int v) [...]

    // 检测为 Lambda 实现
    void lambda$upload$1$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(TransportContext transportContext0, int v, Runnable runnable0) [...]

    public BackendResponse logAndUpdateState(TransportContext transportContext0, int v) {
        BackendResponse backendResponse1;
        String s = transportContext0.getBackendName();
        TransportBackend transportBackend0 = this.backendRegistry.get(s);
        BackendResponse backendResponse0 = BackendResponse.ok(0L);
        long v1 = 0L;
        while(true) {
            Uploader..ExternalSyntheticLambda5 uploader$$ExternalSyntheticLambda50 = () -> Boolean.valueOf(this.eventStore.hasPendingEventsFor(transportContext0));
            if(!((Boolean)this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda50)).booleanValue()) {
                break;
            }
            Uploader..ExternalSyntheticLambda6 uploader$$ExternalSyntheticLambda60 = () -> this.eventStore.loadBatch(transportContext0);
            Iterable iterable0 = (Iterable)this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda60);
            if(!iterable0.iterator().hasNext()) {
                return backendResponse0;
            }
            if(transportBackend0 == null) {
                Logging.d("Uploader", "Unknown backend for %s, deleting event batch for it...", transportContext0);
                backendResponse1 = BackendResponse.fatalError();
            }
            else {
                ArrayList arrayList0 = new ArrayList();
                for(Object object0: iterable0) {
                    arrayList0.add(((PersistedEvent)object0).getEvent());
                }
                if(transportContext0.shouldUploadClientHealthMetrics()) {
                    arrayList0.add(this.createMetricsEvent(transportBackend0));
                }
                backendResponse1 = transportBackend0.send(BackendRequest.builder().setEvents(arrayList0).setExtras(transportContext0.getExtras()).build());
            }
            backendResponse0 = backendResponse1;
            if(backendResponse0.getStatus() == Status.TRANSIENT_ERROR) {
                Uploader..ExternalSyntheticLambda7 uploader$$ExternalSyntheticLambda70 = () -> {
                    this.eventStore.recordFailure(iterable0);
                    long v1 = this.clock.getTime();
                    this.eventStore.recordNextCallTime(transportContext0, v1 + v1);
                    return null;
                };
                this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda70);
                this.workScheduler.schedule(transportContext0, v + 1, true);
                return backendResponse0;
            }
            Uploader..ExternalSyntheticLambda8 uploader$$ExternalSyntheticLambda80 = () -> {
                this.eventStore.recordSuccess(iterable0);
                return null;
            };
            this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda80);
            if(backendResponse0.getStatus() == Status.OK) {
                long v2 = Math.max(v1, backendResponse0.getNextRequestWaitMillis());
                if(transportContext0.shouldUploadClientHealthMetrics()) {
                    Uploader..ExternalSyntheticLambda9 uploader$$ExternalSyntheticLambda90 = () -> {
                        this.clientHealthMetricsStore.resetClientMetrics();
                        return null;
                    };
                    this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda90);
                }
                v1 = v2;
            }
            else if(backendResponse0.getStatus() == Status.INVALID_PAYLOAD) {
                HashMap hashMap0 = new HashMap();
                for(Object object1: iterable0) {
                    String s1 = ((PersistedEvent)object1).getEvent().getTransportName();
                    if(hashMap0.containsKey(s1)) {
                        hashMap0.put(s1, ((int)(((int)(((Integer)hashMap0.get(s1)))) + 1)));
                    }
                    else {
                        hashMap0.put(s1, 1);
                    }
                }
                Uploader..ExternalSyntheticLambda10 uploader$$ExternalSyntheticLambda100 = () -> {
                    for(Object object0: hashMap0.entrySet()) {
                        long v = (long)(((int)(((Integer)((Map.Entry)object0).getValue()))));
                        String s = (String)((Map.Entry)object0).getKey();
                        this.clientHealthMetricsStore.recordLogEventDropped(v, Reason.INVALID_PAYLOD, s);
                    }
                    return null;
                };
                this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda100);
            }
        }
        Uploader..ExternalSyntheticLambda1 uploader$$ExternalSyntheticLambda10 = () -> {
            long v1 = this.clock.getTime();
            this.eventStore.recordNextCallTime(transportContext0, v1 + v1);
            return null;
        };
        this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda10);
        return backendResponse0;
    }

    public void upload(TransportContext transportContext0, int v, Runnable runnable0) {
        Uploader..ExternalSyntheticLambda3 uploader$$ExternalSyntheticLambda30 = () -> try {
            Objects.requireNonNull(this.eventStore);
            Uploader..ExternalSyntheticLambda0 uploader$$ExternalSyntheticLambda00 = new Uploader..ExternalSyntheticLambda0(this.eventStore);
            this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda00);
            if(this.isNetworkAvailable()) {
                this.logAndUpdateState(transportContext0, v);
            }
            else {
                Uploader..ExternalSyntheticLambda2 uploader$$ExternalSyntheticLambda20 = () -> {
                    this.workScheduler.schedule(transportContext0, v + 1);
                    return null;
                };
                this.guard.runCriticalSection(uploader$$ExternalSyntheticLambda20);
            }
        }
        catch(SynchronizationException unused_ex) {
            this.workScheduler.schedule(transportContext0, v + 1);
        }
        finally {
            runnable0.run();
        };
        this.executor.execute(uploader$$ExternalSyntheticLambda30);
    }
}

