package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.zap;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

public final class zabq implements ConnectionCallbacks, OnConnectionFailedListener, zau {
    final GoogleApiManager zaa;
    private final Queue zab;
    @NotOnlyInitialized
    private final Client zac;
    private final ApiKey zad;
    private final zaad zae;
    private final Set zaf;
    private final Map zag;
    private final int zah;
    private final zact zai;
    private boolean zaj;
    private final List zak;
    private ConnectionResult zal;
    private int zam;

    public zabq(GoogleApiManager googleApiManager0, GoogleApi googleApi0) {
        this.zaa = googleApiManager0;
        super();
        this.zab = new LinkedList();
        this.zaf = new HashSet();
        this.zag = new HashMap();
        this.zak = new ArrayList();
        this.zal = null;
        this.zam = 0;
        Client api$Client0 = googleApi0.zab(googleApiManager0.zar.getLooper(), this);
        this.zac = api$Client0;
        this.zad = googleApi0.getApiKey();
        this.zae = new zaad();
        this.zah = googleApi0.zaa();
        if(api$Client0.requiresSignIn()) {
            this.zai = googleApi0.zac(googleApiManager0.zai, googleApiManager0.zar);
            return;
        }
        this.zai = null;
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle0) {
        if(Looper.myLooper() == this.zaa.zar.getLooper()) {
            this.zaH();
            return;
        }
        zabm zabm0 = new zabm(this);
        this.zaa.zar.post(zabm0);
    }

    @Override  // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult0) {
        this.zar(connectionResult0, null);
    }

    @Override  // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int v) {
        if(Looper.myLooper() == this.zaa.zar.getLooper()) {
            this.zaI(v);
            return;
        }
        zabn zabn0 = new zabn(this, v);
        this.zaa.zar.post(zabn0);
    }

    public final boolean zaA() {
        return this.zac.requiresSignIn();
    }

    @ResultIgnorabilityUnspecified
    public final boolean zaB() {
        return this.zaO(true);
    }

    private final Feature zaC(Feature[] arr_feature) {
        if(arr_feature != null && arr_feature.length != 0) {
            Feature[] arr_feature1 = this.zac.getAvailableFeatures();
            int v = 0;
            if(arr_feature1 == null) {
                arr_feature1 = new Feature[0];
            }
            ArrayMap arrayMap0 = new ArrayMap(arr_feature1.length);
            for(int v1 = 0; v1 < arr_feature1.length; ++v1) {
                Feature feature0 = arr_feature1[v1];
                arrayMap0.put(feature0.getName(), feature0.getVersion());
            }
            while(v < arr_feature.length) {
                Feature feature1 = arr_feature[v];
                Long long0 = (Long)arrayMap0.get(feature1.getName());
                if(long0 != null && ((long)long0) >= feature1.getVersion()) {
                    ++v;
                    continue;
                }
                return feature1;
            }
        }
        return null;
    }

    private final void zaD(ConnectionResult connectionResult0) {
        for(Object object0: this.zaf) {
            String s = Objects.equal(connectionResult0, ConnectionResult.RESULT_SUCCESS) ? this.zac.getEndpointPackageName() : null;
            ((zal)object0).zac(this.zad, connectionResult0, s);
        }
        this.zaf.clear();
    }

    private final void zaE(Status status0) {
        Preconditions.checkHandlerThread(this.zaa.zar);
        this.zaF(status0, null, false);
    }

    private final void zaF(Status status0, Exception exception0, boolean z) {
        Preconditions.checkHandlerThread(this.zaa.zar);
        if((status0 == null ? 1 : 0) == (exception0 == null ? 1 : 0)) {
            throw new IllegalArgumentException("Status XOR exception should be null");
        }
        Iterator iterator0 = this.zab.iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            zai zai0 = (zai)object0;
            if(!z || zai0.zac == 2) {
                if(status0 == null) {
                    zai0.zae(exception0);
                }
                else {
                    zai0.zad(status0);
                }
                iterator0.remove();
            }
        }
    }

    private final void zaG() {
        ArrayList arrayList0 = new ArrayList(this.zab);
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            zai zai0 = (zai)arrayList0.get(v1);
            if(!this.zac.isConnected()) {
                break;
            }
            if(this.zaM(zai0)) {
                this.zab.remove(zai0);
            }
        }
    }

    private final void zaH() {
        this.zan();
        this.zaD(ConnectionResult.RESULT_SUCCESS);
        this.zaL();
        Iterator iterator0 = this.zag.values().iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            zaci zaci0 = (zaci)object0;
            if(this.zaC(zaci0.zaa.getRequiredFeatures()) == null) {
                try {
                    TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
                    zaci0.zaa.registerListener(this.zac, taskCompletionSource0);
                }
                catch(DeadObjectException unused_ex) {
                    this.onConnectionSuspended(3);
                    this.zac.disconnect("DeadObjectException thrown while calling register listener method.");
                    break;
                }
                catch(RemoteException unused_ex) {
                    iterator0.remove();
                }
            }
            else {
                iterator0.remove();
            }
        }
        this.zaG();
        this.zaJ();
    }

    private final void zaI(int v) {
        this.zan();
        this.zaj = true;
        String s = this.zac.getLastDisconnectMessage();
        this.zae.zae(v, s);
        Message message0 = Message.obtain(this.zaa.zar, 9, this.zad);
        this.zaa.zar.sendMessageDelayed(message0, 5000L);
        Message message1 = Message.obtain(this.zaa.zar, 11, this.zad);
        this.zaa.zar.sendMessageDelayed(message1, 120000L);
        this.zaa.zak.zac();
        for(Object object0: this.zag.values()) {
            ((zaci)object0).zac.run();
        }
    }

    private final void zaJ() {
        this.zaa.zar.removeMessages(12, this.zad);
        Message message0 = this.zaa.zar.obtainMessage(12, this.zad);
        long v = this.zaa.zae;
        this.zaa.zar.sendMessageDelayed(message0, v);
    }

    private final void zaK(zai zai0) {
        boolean z = this.zaA();
        zai0.zag(this.zae, z);
        try {
            zai0.zaf(this);
        }
        catch(DeadObjectException unused_ex) {
            this.onConnectionSuspended(1);
            this.zac.disconnect("DeadObjectException thrown while running ApiCallRunner.");
        }
    }

    private final void zaL() {
        if(this.zaj) {
            this.zaa.zar.removeMessages(11, this.zad);
            this.zaa.zar.removeMessages(9, this.zad);
            this.zaj = false;
        }
    }

    private final boolean zaM(zai zai0) {
        if(!(zai0 instanceof zac)) {
            this.zaK(zai0);
            return true;
        }
        Feature feature0 = this.zaC(((zac)zai0).zab(this));
        if(feature0 == null) {
            this.zaK(zai0);
            return true;
        }
        Log.w("GoogleApiManager", this.zac.getClass().getName() + " could not execute call because it requires feature (" + feature0.getName() + ", " + feature0.getVersion() + ").");
        if(this.zaa.zas && ((zac)zai0).zaa(this)) {
            zabs zabs0 = new zabs(this.zad, feature0, null);
            int v = this.zak.indexOf(zabs0);
            if(v >= 0) {
                zabs zabs1 = (zabs)this.zak.get(v);
                this.zaa.zar.removeMessages(15, zabs1);
                Message message0 = Message.obtain(this.zaa.zar, 15, zabs1);
                this.zaa.zar.sendMessageDelayed(message0, 5000L);
                return false;
            }
            this.zak.add(zabs0);
            Message message1 = Message.obtain(this.zaa.zar, 15, zabs0);
            this.zaa.zar.sendMessageDelayed(message1, 5000L);
            Message message2 = Message.obtain(this.zaa.zar, 16, zabs0);
            this.zaa.zar.sendMessageDelayed(message2, 120000L);
            ConnectionResult connectionResult0 = new ConnectionResult(2, null);
            if(!this.zaN(connectionResult0)) {
                this.zaa.zaE(connectionResult0, this.zah);
            }
            return false;
        }
        ((zac)zai0).zae(new UnsupportedApiCallException(feature0));
        return true;
    }

    private final boolean zaN(ConnectionResult connectionResult0) {
        synchronized(GoogleApiManager.zac) {
            if(this.zaa.zao != null && this.zaa.zap.contains(this.zad)) {
                this.zaa.zao.zah(connectionResult0, this.zah);
                return true;
            }
            return false;
        }
    }

    private final boolean zaO(boolean z) {
        Preconditions.checkHandlerThread(this.zaa.zar);
        if(this.zac.isConnected() && this.zag.isEmpty()) {
            if(!this.zae.zag()) {
                this.zac.disconnect("Timing out service connection.");
                return true;
            }
            else if(z) {
                this.zaJ();
                return false;
            }
        }
        return false;
    }

    @Override  // com.google.android.gms.common.api.internal.zau
    public final void zaa(ConnectionResult connectionResult0, Api api0, boolean z) {
        throw null;
    }

    public final int zab() {
        return this.zah;
    }

    final int zac() {
        return this.zam;
    }

    public final ConnectionResult zad() {
        Preconditions.checkHandlerThread(this.zaa.zar);
        return this.zal;
    }

    static Client zae(zabq zabq0) {
        return zabq0.zac;
    }

    public final Client zaf() {
        return this.zac;
    }

    static ApiKey zag(zabq zabq0) {
        return zabq0.zad;
    }

    public final Map zah() {
        return this.zag;
    }

    static void zai(zabq zabq0, Status status0) {
        zabq0.zaE(status0);
    }

    static void zaj(zabq zabq0) {
        zabq0.zaH();
    }

    static void zak(zabq zabq0, int v) {
        zabq0.zaI(v);
    }

    static void zal(zabq zabq0, zabs zabs0) {
        if(zabq0.zak.contains(zabs0) && !zabq0.zaj) {
            if(!zabq0.zac.isConnected()) {
                zabq0.zao();
                return;
            }
            zabq0.zaG();
        }
    }

    static void zam(zabq zabq0, zabs zabs0) {
        if(zabq0.zak.remove(zabs0)) {
            zabq0.zaa.zar.removeMessages(15, zabs0);
            zabq0.zaa.zar.removeMessages(16, zabs0);
            Feature feature0 = zabs0.zab;
            ArrayList arrayList0 = new ArrayList(zabq0.zab.size());
            for(Object object0: zabq0.zab) {
                zai zai0 = (zai)object0;
                if(zai0 instanceof zac) {
                    Feature[] arr_feature = ((zac)zai0).zab(zabq0);
                    if(arr_feature != null && ArrayUtils.contains(arr_feature, feature0)) {
                        arrayList0.add(zai0);
                    }
                }
            }
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                zai zai1 = (zai)arrayList0.get(v1);
                zabq0.zab.remove(zai1);
                zai1.zae(new UnsupportedApiCallException(feature0));
            }
        }
    }

    public final void zan() {
        Preconditions.checkHandlerThread(this.zaa.zar);
        this.zal = null;
    }

    public final void zao() {
        Preconditions.checkHandlerThread(this.zaa.zar);
        if(!this.zac.isConnected() && !this.zac.isConnecting()) {
            try {
                int v = this.zaa.zak.zab(this.zaa.zai, this.zac);
                if(v != 0) {
                    ConnectionResult connectionResult0 = new ConnectionResult(v, null);
                    Log.w("GoogleApiManager", "The service for " + this.zac.getClass().getName() + " is not available: " + connectionResult0.toString());
                    this.zar(connectionResult0, null);
                    return;
                }
            }
            catch(IllegalStateException illegalStateException0) {
                this.zar(new ConnectionResult(10), illegalStateException0);
                return;
            }
            zabu zabu0 = new zabu(this.zaa, this.zac, this.zad);
            if(this.zac.requiresSignIn()) {
                ((zact)Preconditions.checkNotNull(this.zai)).zae(zabu0);
            }
            try {
                this.zac.connect(zabu0);
            }
            catch(SecurityException securityException0) {
                this.zar(new ConnectionResult(10), securityException0);
            }
        }
    }

    public final void zap(zai zai0) {
        Preconditions.checkHandlerThread(this.zaa.zar);
        if(this.zac.isConnected()) {
            if(this.zaM(zai0)) {
                this.zaJ();
                return;
            }
            this.zab.add(zai0);
            return;
        }
        this.zab.add(zai0);
        if(this.zal != null && this.zal.hasResolution()) {
            this.zar(this.zal, null);
            return;
        }
        this.zao();
    }

    final void zaq() {
        ++this.zam;
    }

    public final void zar(ConnectionResult connectionResult0, Exception exception0) {
        Preconditions.checkHandlerThread(this.zaa.zar);
        zact zact0 = this.zai;
        if(zact0 != null) {
            zact0.zaf();
        }
        this.zan();
        this.zaa.zak.zac();
        this.zaD(connectionResult0);
        if(this.zac instanceof zap && connectionResult0.getErrorCode() != 24) {
            GoogleApiManager.zat(this.zaa, true);
            Message message0 = this.zaa.zar.obtainMessage(19);
            this.zaa.zar.sendMessageDelayed(message0, 300000L);
        }
        if(connectionResult0.getErrorCode() == 4) {
            this.zaE(GoogleApiManager.zab);
            return;
        }
        if(this.zab.isEmpty()) {
            this.zal = connectionResult0;
            return;
        }
        if(exception0 != null) {
            Preconditions.checkHandlerThread(this.zaa.zar);
            this.zaF(null, exception0, false);
            return;
        }
        if(this.zaa.zas) {
            this.zaF(GoogleApiManager.zaF(this.zad, connectionResult0), null, true);
            if(!this.zab.isEmpty() && !this.zaN(connectionResult0) && !this.zaa.zaE(connectionResult0, this.zah)) {
                if(connectionResult0.getErrorCode() == 18) {
                    this.zaj = true;
                }
                if(this.zaj) {
                    Message message1 = Message.obtain(this.zaa.zar, 9, this.zad);
                    this.zaa.zar.sendMessageDelayed(message1, 5000L);
                    return;
                }
                this.zaE(GoogleApiManager.zaF(this.zad, connectionResult0));
            }
            return;
        }
        this.zaE(GoogleApiManager.zaF(this.zad, connectionResult0));
    }

    public final void zas(ConnectionResult connectionResult0) {
        Preconditions.checkHandlerThread(this.zaa.zar);
        String s = this.zac.getClass().getName();
        this.zac.disconnect("onSignInFailed for " + s + " with " + connectionResult0);
        this.zar(connectionResult0, null);
    }

    public final void zat(zal zal0) {
        Preconditions.checkHandlerThread(this.zaa.zar);
        this.zaf.add(zal0);
    }

    public final void zau() {
        Preconditions.checkHandlerThread(this.zaa.zar);
        if(this.zaj) {
            this.zao();
        }
    }

    public final void zav() {
        Preconditions.checkHandlerThread(this.zaa.zar);
        this.zaE(GoogleApiManager.zaa);
        this.zae.zaf();
        ListenerKey[] arr_listenerHolder$ListenerKey = (ListenerKey[])this.zag.keySet().toArray(new ListenerKey[0]);
        for(int v = 0; v < arr_listenerHolder$ListenerKey.length; ++v) {
            this.zap(new zah(arr_listenerHolder$ListenerKey[v], new TaskCompletionSource()));
        }
        this.zaD(new ConnectionResult(4));
        if(this.zac.isConnected()) {
            zabp zabp0 = new zabp(this);
            this.zac.onUserSignOut(zabp0);
        }
    }

    public final void zaw() {
        Preconditions.checkHandlerThread(this.zaa.zar);
        if(this.zaj) {
            this.zaL();
            this.zaE((this.zaa.zaj.isGooglePlayServicesAvailable(this.zaa.zai) == 18 ? new Status(21, "Connection timed out waiting for Google Play services update to complete.") : new Status(22, "API failed to connect while resuming due to an unknown error.")));
            this.zac.disconnect("Timing out connection while resuming.");
        }
    }

    static boolean zay(zabq zabq0, boolean z) {
        return zabq0.zaO(false);
    }

    final boolean zaz() {
        return this.zac.isConnected();
    }
}

