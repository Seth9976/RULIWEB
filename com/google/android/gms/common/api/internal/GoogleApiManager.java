package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.zal;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.internal.base.zau;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

public class GoogleApiManager implements Handler.Callback {
    public static final Status zaa;
    private static final Status zab;
    private static final Object zac;
    private static GoogleApiManager zad;
    private long zae;
    private boolean zaf;
    private TelemetryData zag;
    private TelemetryLoggingClient zah;
    private final Context zai;
    private final GoogleApiAvailability zaj;
    private final zal zak;
    private final AtomicInteger zal;
    private final AtomicInteger zam;
    private final Map zan;
    private zaae zao;
    private final Set zap;
    private final Set zaq;
    @NotOnlyInitialized
    private final Handler zar;
    private volatile boolean zas;

    static {
        GoogleApiManager.zaa = new Status(4, "Sign-out occurred while this API call was in progress.");
        GoogleApiManager.zab = new Status(4, "The user must be signed in to make this API call.");
        GoogleApiManager.zac = new Object();
    }

    private GoogleApiManager(Context context0, Looper looper0, GoogleApiAvailability googleApiAvailability0) {
        this.zae = 10000L;
        this.zaf = false;
        this.zal = new AtomicInteger(1);
        this.zam = new AtomicInteger(0);
        this.zan = new ConcurrentHashMap(5, 0.75f, 1);
        this.zao = null;
        this.zap = new ArraySet();
        this.zaq = new ArraySet();
        this.zas = true;
        this.zai = context0;
        zau zau0 = new zau(looper0, this);
        this.zar = zau0;
        this.zaj = googleApiAvailability0;
        this.zak = new zal(googleApiAvailability0);
        if(DeviceProperties.isAuto(context0)) {
            this.zas = false;
        }
        zau0.sendMessage(zau0.obtainMessage(6));
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        long v = 300000L;
        zabq zabq0 = null;
        switch(message0.what) {
            case 1: {
                if(((Boolean)message0.obj).booleanValue()) {
                    v = 10000L;
                }
                this.zae = v;
                this.zar.removeMessages(12);
                for(Object object0: this.zan.keySet()) {
                    Message message1 = this.zar.obtainMessage(12, ((ApiKey)object0));
                    this.zar.sendMessageDelayed(message1, this.zae);
                }
                return true;
            }
            case 2: {
                com.google.android.gms.common.api.internal.zal zal0 = (com.google.android.gms.common.api.internal.zal)message0.obj;
                for(Object object1: zal0.zab()) {
                    ApiKey apiKey0 = (ApiKey)object1;
                    zabq zabq1 = (zabq)this.zan.get(apiKey0);
                    if(zabq1 == null) {
                        zal0.zac(apiKey0, new ConnectionResult(13), null);
                        return true;
                    }
                    if(zabq1.zaz()) {
                        String s = zabq1.zaf().getEndpointPackageName();
                        zal0.zac(apiKey0, ConnectionResult.RESULT_SUCCESS, s);
                    }
                    else {
                        ConnectionResult connectionResult0 = zabq1.zad();
                        if(connectionResult0 == null) {
                            zabq1.zat(zal0);
                            zabq1.zao();
                        }
                        else {
                            zal0.zac(apiKey0, connectionResult0, null);
                        }
                    }
                }
                return true;
            }
            case 3: {
                for(Object object2: this.zan.values()) {
                    ((zabq)object2).zan();
                    ((zabq)object2).zao();
                }
                return true;
            }
            case 5: {
                int v1 = message0.arg1;
                ConnectionResult connectionResult1 = (ConnectionResult)message0.obj;
                for(Object object3: this.zan.values()) {
                    zabq zabq3 = (zabq)object3;
                    if(zabq3.zab() == v1) {
                        zabq0 = zabq3;
                        break;
                    }
                    if(false) {
                        break;
                    }
                }
                if(zabq0 != null) {
                    if(connectionResult1.getErrorCode() == 13) {
                        zabq.zai(zabq0, new Status(17, "Error resolution was canceled by the user, original error message: " + this.zaj.getErrorString(connectionResult1.getErrorCode()) + ": " + connectionResult1.getErrorMessage()));
                        return true;
                    }
                    zabq.zai(zabq0, GoogleApiManager.zaF(zabq.zag(zabq0), connectionResult1));
                    return true;
                }
                Log.wtf("GoogleApiManager", "Could not find API instance " + v1 + " while trying to fail enqueued calls.", new Exception());
                return true;
            }
            case 6: {
                if(this.zai.getApplicationContext() instanceof Application) {
                    BackgroundDetector.initialize(((Application)this.zai.getApplicationContext()));
                    BackgroundDetector.getInstance().addListener(new zabl(this));
                    if(!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zae = 300000L;
                        return true;
                    }
                }
                break;
            }
            case 7: {
                this.zaG(((GoogleApi)message0.obj));
                return true;
            }
            case 9: {
                if(this.zan.containsKey(message0.obj)) {
                    ((zabq)this.zan.get(message0.obj)).zau();
                    return true;
                }
                break;
            }
            case 10: {
                for(Object object4: this.zaq) {
                    zabq zabq4 = (zabq)this.zan.remove(((ApiKey)object4));
                    if(zabq4 != null) {
                        zabq4.zav();
                    }
                }
                this.zaq.clear();
                return true;
            }
            case 11: {
                if(this.zan.containsKey(message0.obj)) {
                    ((zabq)this.zan.get(message0.obj)).zaw();
                    return true;
                }
                break;
            }
            case 12: {
                if(this.zan.containsKey(message0.obj)) {
                    ((zabq)this.zan.get(message0.obj)).zaB();
                    return true;
                }
                break;
            }
            case 4: 
            case 8: 
            case 13: {
                zach zach0 = (zach)message0.obj;
                zabq zabq2 = (zabq)this.zan.get(zach0.zac.getApiKey());
                if(zabq2 == null) {
                    zabq2 = this.zaG(zach0.zac);
                }
                if(zabq2.zaA() && this.zam.get() != zach0.zab) {
                    zach0.zaa.zad(GoogleApiManager.zaa);
                    zabq2.zav();
                    return true;
                }
                zabq2.zap(zach0.zaa);
                return true;
            }
            case 14: {
                zaaf zaaf0 = (zaaf)message0.obj;
                ApiKey apiKey1 = zaaf0.zaa();
                if(!this.zan.containsKey(apiKey1)) {
                    zaaf0.zab().setResult(Boolean.FALSE);
                    return true;
                }
                zaaf0.zab().setResult(Boolean.valueOf(zabq.zay(((zabq)this.zan.get(apiKey1)), false)));
                return true;
            }
            case 15: {
                zabs zabs0 = (zabs)message0.obj;
                if(this.zan.containsKey(zabs.zab(zabs0))) {
                    zabq.zal(((zabq)this.zan.get(zabs.zab(zabs0))), zabs0);
                    return true;
                }
                break;
            }
            case 16: {
                zabs zabs1 = (zabs)message0.obj;
                if(this.zan.containsKey(zabs.zab(zabs1))) {
                    zabq.zam(((zabq)this.zan.get(zabs.zab(zabs1))), zabs1);
                    return true;
                }
                break;
            }
            case 17: {
                this.zaI();
                return true;
            }
            case 18: {
                zace zace0 = (zace)message0.obj;
                if(zace0.zac == 0L) {
                    List list0 = Arrays.asList(new MethodInvocation[]{zace0.zaa});
                    TelemetryData telemetryData0 = new TelemetryData(zace0.zab, list0);
                    this.zaH().log(telemetryData0);
                    return true;
                }
                TelemetryData telemetryData1 = this.zag;
                if(telemetryData1 != null) {
                    List list1 = telemetryData1.zab();
                    if(telemetryData1.zaa() != zace0.zab || list1 != null && list1.size() >= zace0.zad) {
                        this.zar.removeMessages(17);
                        this.zaI();
                    }
                    else {
                        this.zag.zac(zace0.zaa);
                    }
                }
                if(this.zag == null) {
                    ArrayList arrayList0 = new ArrayList();
                    arrayList0.add(zace0.zaa);
                    this.zag = new TelemetryData(zace0.zab, arrayList0);
                    Message message2 = this.zar.obtainMessage(17);
                    this.zar.sendMessageDelayed(message2, zace0.zac);
                    return true;
                }
                break;
            }
            case 19: {
                this.zaf = false;
                return true;
            }
            default: {
                Log.w("GoogleApiManager", "Unknown message id: " + message0.what);
                return false;
            }
        }
        return true;
    }

    public static void reportSignOut() {
        synchronized(GoogleApiManager.zac) {
            GoogleApiManager googleApiManager0 = GoogleApiManager.zad;
            if(googleApiManager0 != null) {
                googleApiManager0.zam.incrementAndGet();
                Message message0 = googleApiManager0.zar.obtainMessage(10);
                googleApiManager0.zar.sendMessageAtFrontOfQueue(message0);
            }
        }
    }

    public final void zaA(zaae zaae0) {
        synchronized(GoogleApiManager.zac) {
            if(this.zao != zaae0) {
                this.zao = zaae0;
                this.zap.clear();
            }
            this.zap.addAll(zaae0.zaa());
        }
    }

    final void zaB(zaae zaae0) {
        synchronized(GoogleApiManager.zac) {
            if(this.zao == zaae0) {
                this.zao = null;
                this.zap.clear();
            }
        }
    }

    final boolean zaD() {
        if(this.zaf) {
            return false;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration0 = RootTelemetryConfigManager.getInstance().getConfig();
        if(rootTelemetryConfiguration0 != null && !rootTelemetryConfiguration0.getMethodInvocationTelemetryEnabled()) {
            return false;
        }
        switch(this.zak.zaa(this.zai, 203400000)) {
            case -1: 
            case 0: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    @ResultIgnorabilityUnspecified
    final boolean zaE(ConnectionResult connectionResult0, int v) {
        return this.zaj.zah(this.zai, connectionResult0, v);
    }

    private static Status zaF(ApiKey apiKey0, ConnectionResult connectionResult0) {
        return new Status(connectionResult0, "API: " + apiKey0.zaa() + " is not available on this device. Connection failed with: " + connectionResult0);
    }

    @ResultIgnorabilityUnspecified
    private final zabq zaG(GoogleApi googleApi0) {
        ApiKey apiKey0 = googleApi0.getApiKey();
        zabq zabq0 = (zabq)this.zan.get(apiKey0);
        if(zabq0 == null) {
            zabq0 = new zabq(this, googleApi0);
            this.zan.put(apiKey0, zabq0);
        }
        if(zabq0.zaA()) {
            this.zaq.add(apiKey0);
        }
        zabq0.zao();
        return zabq0;
    }

    private final TelemetryLoggingClient zaH() {
        if(this.zah == null) {
            this.zah = TelemetryLogging.getClient(this.zai);
        }
        return this.zah;
    }

    private final void zaI() {
        TelemetryData telemetryData0 = this.zag;
        if(telemetryData0 != null) {
            if(telemetryData0.zaa() > 0 || this.zaD()) {
                this.zaH().log(telemetryData0);
            }
            this.zag = null;
        }
    }

    private final void zaJ(TaskCompletionSource taskCompletionSource0, int v, GoogleApi googleApi0) {
        if(v != 0) {
            zacd zacd0 = zacd.zaa(this, v, googleApi0.getApiKey());
            if(zacd0 != null) {
                this.zar.getClass();
                taskCompletionSource0.getTask().addOnCompleteListener(new zabk(this.zar), zacd0);
            }
        }
    }

    public final int zaa() {
        return this.zal.getAndIncrement();
    }

    final zabq zai(ApiKey apiKey0) {
        return (zabq)this.zan.get(apiKey0);
    }

    public static GoogleApiManager zaj() {
        synchronized(GoogleApiManager.zac) {
            Preconditions.checkNotNull(GoogleApiManager.zad, "Must guarantee manager is non-null before using getInstance");
            return GoogleApiManager.zad;
        }
    }

    @ResultIgnorabilityUnspecified
    public static GoogleApiManager zak(Context context0) {
        synchronized(GoogleApiManager.zac) {
            if(GoogleApiManager.zad == null) {
                Looper looper0 = GmsClientSupervisor.getOrStartHandlerThread().getLooper();
                GoogleApiManager.zad = new GoogleApiManager(context0.getApplicationContext(), looper0, GoogleApiAvailability.getInstance());
            }
            return GoogleApiManager.zad;
        }
    }

    public final Task zam(Iterable iterable0) {
        com.google.android.gms.common.api.internal.zal zal0 = new com.google.android.gms.common.api.internal.zal(iterable0);
        Message message0 = this.zar.obtainMessage(2, zal0);
        this.zar.sendMessage(message0);
        return zal0.zaa();
    }

    @ResultIgnorabilityUnspecified
    public final Task zan(GoogleApi googleApi0) {
        zaaf zaaf0 = new zaaf(googleApi0.getApiKey());
        Message message0 = this.zar.obtainMessage(14, zaaf0);
        this.zar.sendMessage(message0);
        return zaaf0.zab().getTask();
    }

    public final Task zao(GoogleApi googleApi0, RegisterListenerMethod registerListenerMethod0, UnregisterListenerMethod unregisterListenerMethod0, Runnable runnable0) {
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        this.zaJ(taskCompletionSource0, registerListenerMethod0.zaa(), googleApi0);
        zach zach0 = new zach(new zaf(new zaci(registerListenerMethod0, unregisterListenerMethod0, runnable0), taskCompletionSource0), this.zam.get(), googleApi0);
        Message message0 = this.zar.obtainMessage(8, zach0);
        this.zar.sendMessage(message0);
        return taskCompletionSource0.getTask();
    }

    public final Task zap(GoogleApi googleApi0, ListenerKey listenerHolder$ListenerKey0, int v) {
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        this.zaJ(taskCompletionSource0, v, googleApi0);
        zach zach0 = new zach(new zah(listenerHolder$ListenerKey0, taskCompletionSource0), this.zam.get(), googleApi0);
        Message message0 = this.zar.obtainMessage(13, zach0);
        this.zar.sendMessage(message0);
        return taskCompletionSource0.getTask();
    }

    static void zat(GoogleApiManager googleApiManager0, boolean z) {
        googleApiManager0.zaf = true;
    }

    public final void zau(GoogleApi googleApi0, int v, ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        zach zach0 = new zach(new zae(v, baseImplementation$ApiMethodImpl0), this.zam.get(), googleApi0);
        Message message0 = this.zar.obtainMessage(4, zach0);
        this.zar.sendMessage(message0);
    }

    public final void zav(GoogleApi googleApi0, int v, TaskApiCall taskApiCall0, TaskCompletionSource taskCompletionSource0, StatusExceptionMapper statusExceptionMapper0) {
        this.zaJ(taskCompletionSource0, taskApiCall0.zaa(), googleApi0);
        zach zach0 = new zach(new zag(v, taskApiCall0, taskCompletionSource0, statusExceptionMapper0), this.zam.get(), googleApi0);
        Message message0 = this.zar.obtainMessage(4, zach0);
        this.zar.sendMessage(message0);
    }

    final void zaw(MethodInvocation methodInvocation0, int v, long v1, int v2) {
        zace zace0 = new zace(methodInvocation0, v, v1, v2);
        Message message0 = this.zar.obtainMessage(18, zace0);
        this.zar.sendMessage(message0);
    }

    public final void zax(ConnectionResult connectionResult0, int v) {
        if(!this.zaE(connectionResult0, v)) {
            Message message0 = this.zar.obtainMessage(5, v, 0, connectionResult0);
            this.zar.sendMessage(message0);
        }
    }

    public final void zay() {
        Message message0 = this.zar.obtainMessage(3);
        this.zar.sendMessage(message0);
    }

    public final void zaz(GoogleApi googleApi0) {
        Message message0 = this.zar.obtainMessage(7, googleApi0);
        this.zar.sendMessage(message0);
    }
}

