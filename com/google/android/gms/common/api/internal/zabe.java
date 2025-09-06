package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.internal.zaj;
import com.google.android.gms.common.internal.zak;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import jeb.synthetic.FIN;

public final class zabe extends GoogleApiClient implements zabz {
    final Queue zaa;
    zabx zab;
    final Map zac;
    Set zad;
    final ClientSettings zae;
    final Map zaf;
    final AbstractClientBuilder zag;
    Set zah;
    final zadc zai;
    private final Lock zaj;
    private final zak zak;
    private zaca zal;
    private final int zam;
    private final Context zan;
    private final Looper zao;
    private volatile boolean zap;
    private long zaq;
    private long zar;
    private final zabc zas;
    private final GoogleApiAvailability zat;
    private final ListenerHolders zau;
    private final ArrayList zav;
    private Integer zaw;
    private final zaj zax;

    public zabe(Context context0, Lock lock0, Looper looper0, ClientSettings clientSettings0, GoogleApiAvailability googleApiAvailability0, AbstractClientBuilder api$AbstractClientBuilder0, Map map0, List list0, List list1, Map map1, int v, int v1, ArrayList arrayList0) {
        this.zal = null;
        this.zaa = new LinkedList();
        this.zaq = 120000L;
        this.zar = 5000L;
        this.zad = new HashSet();
        this.zau = new ListenerHolders();
        this.zaw = null;
        this.zah = null;
        zaay zaay0 = new zaay(this);
        this.zax = zaay0;
        this.zan = context0;
        this.zaj = lock0;
        this.zak = new zak(looper0, zaay0);
        this.zao = looper0;
        this.zas = new zabc(this, looper0);
        this.zat = googleApiAvailability0;
        this.zam = v;
        if(v >= 0) {
            this.zaw = v1;
        }
        this.zaf = map0;
        this.zac = map1;
        this.zav = arrayList0;
        this.zai = new zadc();
        for(Object object0: list0) {
            this.zak.zaf(((ConnectionCallbacks)object0));
        }
        for(Object object1: list1) {
            this.zak.zag(((OnConnectionFailedListener)object1));
        }
        this.zae = clientSettings0;
        this.zag = api$AbstractClientBuilder0;
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    @ResultIgnorabilityUnspecified
    public final ConnectionResult blockingConnect() {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaj.lock();
        try {
            if(this.zam >= 0) {
                if(this.zaw == null) {
                    z = false;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
                this.zal(((int)(((Integer)Preconditions.checkNotNull(this.zaw)))));
                this.zak.zab();
                return ((zaca)Preconditions.checkNotNull(this.zal)).zab();
            }
            Integer integer0 = this.zaw;
            boolean z1 = false;
            if(integer0 == null) {
                z1 = true;
                this.zaw = zabe.zad(this.zac.values(), false);
            }
            else if(((int)integer0) != 2) {
                z1 = true;
            }
            if(z1) {
                this.zal(((int)(((Integer)Preconditions.checkNotNull(this.zaw)))));
                this.zak.zab();
                return ((zaca)Preconditions.checkNotNull(this.zal)).zab();
            }
        }
        finally {
            this.zaj.unlock();
        }
        throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult blockingConnect(long v, TimeUnit timeUnit0) {
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit0, "TimeUnit must not be null");
        this.zaj.lock();
        try {
            Integer integer0 = this.zaw;
            boolean z = false;
            if(integer0 == null) {
                z = true;
                this.zaw = zabe.zad(this.zac.values(), false);
            }
            else if(((int)integer0) != 2) {
                z = true;
            }
            if(z) {
                this.zal(((int)(((Integer)Preconditions.checkNotNull(this.zaw)))));
                this.zak.zab();
                return ((zaca)Preconditions.checkNotNull(this.zal)).zac(v, timeUnit0);
            }
        }
        finally {
            this.zaj.unlock();
        }
        throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final PendingResult clearDefaultAccountAndReconnect() {
        Preconditions.checkState(this.isConnected(), "GoogleApiClient is not connected yet.");
        Preconditions.checkState(this.zaw == null || ((int)this.zaw) != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        PendingResult pendingResult0 = new StatusPendingResult(this);
        if(this.zac.containsKey(Common.CLIENT_KEY)) {
            this.zam(this, ((StatusPendingResult)pendingResult0), false);
            return pendingResult0;
        }
        AtomicReference atomicReference0 = new AtomicReference();
        zaaz zaaz0 = new zaaz(this, atomicReference0, ((StatusPendingResult)pendingResult0));
        zaba zaba0 = new zaba(this, ((StatusPendingResult)pendingResult0));
        Builder googleApiClient$Builder0 = new Builder(this.zan);
        googleApiClient$Builder0.addApi(Common.API);
        googleApiClient$Builder0.addConnectionCallbacks(zaaz0);
        googleApiClient$Builder0.addOnConnectionFailedListener(zaba0);
        googleApiClient$Builder0.setHandler(this.zas);
        GoogleApiClient googleApiClient0 = googleApiClient$Builder0.build();
        atomicReference0.set(googleApiClient0);
        googleApiClient0.connect();
        return pendingResult0;
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void connect() {
        this.zaj.lock();
        try {
            int v1 = 2;
            boolean z = false;
            if(this.zam >= 0) {
                Preconditions.checkState(this.zaw != null, "Sign-in mode should have been set explicitly by auto-manage.");
                goto label_12;
            }
            Integer integer0 = this.zaw;
            boolean z1 = false;
            if(integer0 == null) {
                z1 = true;
                this.zaw = zabe.zad(this.zac.values(), false);
            }
            else if(((int)integer0) != 2) {
                z1 = true;
            }
            if(z1) {
            label_12:
                int v2 = (int)(((Integer)Preconditions.checkNotNull(this.zaw)));
                this.zaj.lock();
                switch(v2) {
                    case 2: {
                        z = true;
                        break;
                    }
                    case 1: 
                    case 3: {
                        v1 = v2;
                        z = true;
                        break;
                    }
                    default: {
                        v1 = v2;
                    }
                }
                try {
                    Preconditions.checkArgument(z, "Illegal sign-in mode: " + v1);
                    this.zal(v1);
                    this.zan();
                }
                finally {
                    this.zaj.unlock();
                }
                return;
            }
        }
        finally {
            this.zaj.unlock();
        }
        throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void connect(int v) {
        this.zaj.lock();
        boolean z = true;
        if(v != 1 && v != 3) {
            if(v == 2) {
                v = 2;
            }
            else {
                z = false;
            }
        }
        try {
            Preconditions.checkArgument(z, "Illegal sign-in mode: " + v);
            this.zal(v);
            this.zan();
        }
        finally {
            this.zaj.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void disconnect() {
        this.zaj.lock();
        try {
            this.zai.zab();
            zaca zaca0 = this.zal;
            if(zaca0 != null) {
                zaca0.zar();
            }
            this.zau.zab();
            for(Object object0: this.zaa) {
                ((ApiMethodImpl)object0).zan(null);
                ((ApiMethodImpl)object0).cancel();
            }
            this.zaa.clear();
            if(this.zal != null) {
                this.zak();
                this.zak.zaa();
            }
        }
        finally {
            this.zaj.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        printWriter0.append(s).append("mContext=").println(this.zan);
        printWriter0.append(s).append("mResuming=").print(this.zap);
        printWriter0.append(" mWorkQueue.size()=").print(this.zaa.size());
        printWriter0.append(" mUnconsumedApiCalls.size()=").println(this.zai.zab.size());
        zaca zaca0 = this.zal;
        if(zaca0 != null) {
            zaca0.zas(s, fileDescriptor0, printWriter0, arr_s);
        }
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    @ResultIgnorabilityUnspecified
    public final ApiMethodImpl enqueue(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        Api api0 = baseImplementation$ApiMethodImpl0.getApi();
        Preconditions.checkArgument(this.zac.containsKey(baseImplementation$ApiMethodImpl0.getClientKey()), "GoogleApiClient is not configured to use " + (api0 == null ? "the API" : api0.zad()) + " required for this call.");
        this.zaj.lock();
        try {
            zaca zaca0 = this.zal;
            if(zaca0 == null) {
                this.zaa.add(baseImplementation$ApiMethodImpl0);
            }
            else {
                baseImplementation$ApiMethodImpl0 = zaca0.zae(baseImplementation$ApiMethodImpl0);
            }
            return baseImplementation$ApiMethodImpl0;
        }
        finally {
            this.zaj.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    @ResultIgnorabilityUnspecified
    public final ApiMethodImpl execute(ApiMethodImpl baseImplementation$ApiMethodImpl0) {
        Api api0 = baseImplementation$ApiMethodImpl0.getApi();
        Preconditions.checkArgument(this.zac.containsKey(baseImplementation$ApiMethodImpl0.getClientKey()), "GoogleApiClient is not configured to use " + (api0 == null ? "the API" : api0.zad()) + " required for this call.");
        this.zaj.lock();
        try {
            zaca zaca0 = this.zal;
            if(zaca0 != null) {
                if(this.zap) {
                    this.zaa.add(baseImplementation$ApiMethodImpl0);
                    while(!this.zaa.isEmpty()) {
                        ApiMethodImpl baseImplementation$ApiMethodImpl1 = (ApiMethodImpl)this.zaa.remove();
                        this.zai.zaa(baseImplementation$ApiMethodImpl1);
                        baseImplementation$ApiMethodImpl1.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                    }
                }
                else {
                    baseImplementation$ApiMethodImpl0 = zaca0.zaf(baseImplementation$ApiMethodImpl0);
                }
                return baseImplementation$ApiMethodImpl0;
            }
        }
        finally {
            this.zaj.unlock();
        }
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final Client getClient(AnyClientKey api$AnyClientKey0) {
        Client api$Client0 = (Client)this.zac.get(api$AnyClientKey0);
        Preconditions.checkNotNull(api$Client0, "Appropriate Api was not requested.");
        return api$Client0;
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final ConnectionResult getConnectionResult(Api api0) {
        ConnectionResult connectionResult1;
        this.zaj.lock();
        try {
            if(!this.isConnected() && !this.zap) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
            if(this.zac.containsKey(api0.zab())) {
                ConnectionResult connectionResult0 = ((zaca)Preconditions.checkNotNull(this.zal)).zad(api0);
                if(connectionResult0 == null) {
                    if(this.zap) {
                        connectionResult1 = ConnectionResult.RESULT_SUCCESS;
                    }
                    else {
                        Log.w("GoogleApiClientImpl", this.zaf());
                        Log.wtf("GoogleApiClientImpl", api0.zad() + " requested in getConnectionResult is not connected but is not present in the failed  connections map", new Exception());
                        connectionResult1 = new ConnectionResult(8, null);
                    }
                    return connectionResult1;
                }
                return connectionResult0;
            }
        }
        finally {
            this.zaj.unlock();
        }
        throw new IllegalArgumentException(api0.zad() + " was never registered with GoogleApiClient");
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final Context getContext() {
        return this.zan;
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final Looper getLooper() {
        return this.zao;
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasApi(Api api0) {
        return this.zac.containsKey(api0.zab());
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final boolean hasConnectedApi(Api api0) {
        if(!this.isConnected()) {
            return false;
        }
        Client api$Client0 = (Client)this.zac.get(api0.zab());
        return api$Client0 != null && api$Client0.isConnected();
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnected() {
        return this.zal != null && this.zal.zaw();
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnecting() {
        return this.zal != null && this.zal.zax();
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionCallbacksRegistered(ConnectionCallbacks googleApiClient$ConnectionCallbacks0) {
        return this.zak.zaj(googleApiClient$ConnectionCallbacks0);
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final boolean isConnectionFailedListenerRegistered(OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        return this.zak.zak(googleApiClient$OnConnectionFailedListener0);
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener0) {
        return this.zal != null && this.zal.zay(signInConnectionListener0);
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void maybeSignOut() {
        zaca zaca0 = this.zal;
        if(zaca0 != null) {
            zaca0.zau();
        }
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void reconnect() {
        this.disconnect();
        this.connect();
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionCallbacks(ConnectionCallbacks googleApiClient$ConnectionCallbacks0) {
        this.zak.zaf(googleApiClient$ConnectionCallbacks0);
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void registerConnectionFailedListener(OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        this.zak.zag(googleApiClient$OnConnectionFailedListener0);
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final ListenerHolder registerListener(Object object0) {
        this.zaj.lock();
        try {
            return this.zau.zaa(object0, this.zao, "NO_TYPE");
        }
        finally {
            this.zaj.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void stopAutoManage(FragmentActivity fragmentActivity0) {
        LifecycleActivity lifecycleActivity0 = new LifecycleActivity(fragmentActivity0);
        if(this.zam < 0) {
            throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        }
        com.google.android.gms.common.api.internal.zak.zaa(lifecycleActivity0).zae(this.zam);
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionCallbacks(ConnectionCallbacks googleApiClient$ConnectionCallbacks0) {
        this.zak.zah(googleApiClient$ConnectionCallbacks0);
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void unregisterConnectionFailedListener(OnConnectionFailedListener googleApiClient$OnConnectionFailedListener0) {
        this.zak.zai(googleApiClient$OnConnectionFailedListener0);
    }

    @Override  // com.google.android.gms.common.api.internal.zabz
    public final void zaa(ConnectionResult connectionResult0) {
        if(!this.zat.isPlayServicesPossiblyUpdating(this.zan, connectionResult0.getErrorCode())) {
            this.zak();
        }
        if(!this.zap) {
            this.zak.zac(connectionResult0);
            this.zak.zaa();
        }
    }

    @Override  // com.google.android.gms.common.api.internal.zabz
    public final void zab(Bundle bundle0) {
        while(!this.zaa.isEmpty()) {
            this.execute(((ApiMethodImpl)this.zaa.remove()));
        }
        this.zak.zad(bundle0);
    }

    @Override  // com.google.android.gms.common.api.internal.zabz
    public final void zac(int v, boolean z) {
        if(v == 1) {
            if(!z && !this.zap) {
                this.zap = true;
                if(this.zab == null) {
                    try {
                        Context context0 = this.zan.getApplicationContext();
                        zabd zabd0 = new zabd(this);
                        this.zab = this.zat.zac(context0, zabd0);
                    }
                    catch(SecurityException unused_ex) {
                    }
                }
                Message message0 = this.zas.obtainMessage(1);
                this.zas.sendMessageDelayed(message0, this.zaq);
                Message message1 = this.zas.obtainMessage(2);
                this.zas.sendMessageDelayed(message1, this.zar);
            }
            v = 1;
        }
        BasePendingResult[] arr_basePendingResult = (BasePendingResult[])this.zai.zab.toArray(new BasePendingResult[0]);
        for(int v1 = 0; v1 < arr_basePendingResult.length; ++v1) {
            arr_basePendingResult[v1].forceFailureUnlessReady(zadc.zaa);
        }
        this.zak.zae(v);
        this.zak.zaa();
        if(v == 2) {
            this.zan();
        }
    }

    public static int zad(Iterable iterable0, boolean z) {
        boolean z1 = false;
        boolean z2 = false;
        for(Object object0: iterable0) {
            z1 |= ((Client)object0).requiresSignIn();
            z2 |= ((Client)object0).providesSignIn();
        }
        if(z1) {
            return !z2 || !z ? 1 : 2;
        }
        return 3;
    }

    static Context zae(zabe zabe0) {
        return zabe0.zan;
    }

    final String zaf() {
        StringWriter stringWriter0 = new StringWriter();
        this.dump("", null, new PrintWriter(stringWriter0), null);
        return stringWriter0.toString();
    }

    static String zag(int v) {
        switch(v) {
            case 1: {
                return "SIGN_IN_MODE_REQUIRED";
            }
            case 2: {
                return "SIGN_IN_MODE_OPTIONAL";
            }
            case 3: {
                return "SIGN_IN_MODE_NONE";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }

    static void zah(zabe zabe0, GoogleApiClient googleApiClient0, StatusPendingResult statusPendingResult0, boolean z) {
        zabe0.zam(googleApiClient0, statusPendingResult0, true);
    }

    static void zai(zabe zabe0) {
        zabe0.zaj.lock();
        try {
            if(zabe0.zap) {
                zabe0.zan();
            }
        }
        finally {
            zabe0.zaj.unlock();
        }
    }

    static void zaj(zabe zabe0) {
        zabe0.zaj.lock();
        try {
            if(zabe0.zak()) {
                zabe0.zan();
            }
        }
        finally {
            zabe0.zaj.unlock();
        }
    }

    @ResultIgnorabilityUnspecified
    final boolean zak() {
        if(!this.zap) {
            return false;
        }
        this.zap = false;
        this.zas.removeMessages(2);
        this.zas.removeMessages(1);
        zabx zabx0 = this.zab;
        if(zabx0 != null) {
            zabx0.zab();
            this.zab = null;
        }
        return true;
    }

    private final void zal(int v) {
        Integer integer0 = this.zaw;
        if(integer0 == null) {
            this.zaw = v;
        }
        else if(((int)integer0) == v) {
        }
        else {
            throw new IllegalStateException("Cannot use sign-in mode: " + zabe.zag(v) + ". Mode was already set to " + zabe.zag(((int)this.zaw)));
        }
        if(this.zal != null) {
            return;
        }
        boolean z = false;
        boolean z1 = false;
        for(Object object0: this.zac.values()) {
            z |= ((Client)object0).requiresSignIn();
            z1 |= ((Client)object0).providesSignIn();
        }
        switch(((int)this.zaw)) {
            case 1: {
                if(!z) {
                    throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                }
                if(z1) {
                    throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
                break;
            }
            case 2: {
                if(z) {
                    this.zal = zaaa.zag(this.zan, this, this.zaj, this.zao, this.zat, this.zac, this.zae, this.zaf, this.zag, this.zav);
                    return;
                }
            }
        }
        this.zal = new zabi(this.zan, this, this.zaj, this.zao, this.zat, this.zac, this.zae, this.zaf, this.zag, this.zav, this);
    }

    private final void zam(GoogleApiClient googleApiClient0, StatusPendingResult statusPendingResult0, boolean z) {
        Common.zaa.zaa(googleApiClient0).setResultCallback(new zabb(this, statusPendingResult0, z, googleApiClient0));
    }

    private final void zan() {
        this.zak.zab();
        ((zaca)Preconditions.checkNotNull(this.zal)).zaq();
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void zao(zada zada0) {
        this.zaj.lock();
        try {
            if(this.zah == null) {
                this.zah = new HashSet();
            }
            this.zah.add(zada0);
        }
        finally {
            this.zaj.unlock();
        }
    }

    @Override  // com.google.android.gms.common.api.GoogleApiClient
    public final void zap(zada zada0) {
        this.zaj.lock();
        try {
            Set set0 = this.zah;
            if(set0 == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            }
            else if(set0.remove(zada0)) {
                this.zaj.lock();
                int v1 = FIN.finallyOpen$NT();
                Set set1 = this.zah;
                if(set1 == null) {
                    FIN.finallyCodeBegin$NT(v1);
                    this.zaj.unlock();
                    FIN.finallyCodeEnd$NT(v1);
                    goto label_19;
                }
                else {
                    FIN.finallyExec$NT(v1);
                    if(set1.isEmpty()) {
                    label_19:
                        zaca zaca0 = this.zal;
                        if(zaca0 != null) {
                            zaca0.zat();
                        }
                    }
                }
            }
            else {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            }
        }
        finally {
            this.zaj.unlock();
        }
    }
}

