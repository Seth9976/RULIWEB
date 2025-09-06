package com.google.android.gms.common.api.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zau;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;

public final class NonGmsServiceBrokerClient implements ServiceConnection, Client {
    private static final String zaa = "NonGmsServiceBrokerClient";
    private final String zab;
    private final String zac;
    private final ComponentName zad;
    private final Context zae;
    private final ConnectionCallbacks zaf;
    private final Handler zag;
    private final OnConnectionFailedListener zah;
    private IBinder zai;
    private boolean zaj;
    private String zak;
    private String zal;

    static {
    }

    public NonGmsServiceBrokerClient(Context context0, Looper looper0, ComponentName componentName0, ConnectionCallbacks connectionCallbacks0, OnConnectionFailedListener onConnectionFailedListener0) {
        this(context0, looper0, null, null, componentName0, connectionCallbacks0, onConnectionFailedListener0);
    }

    private NonGmsServiceBrokerClient(Context context0, Looper looper0, String s, String s1, ComponentName componentName0, ConnectionCallbacks connectionCallbacks0, OnConnectionFailedListener onConnectionFailedListener0) {
        this.zaj = false;
        this.zak = null;
        this.zae = context0;
        this.zag = new zau(looper0);
        this.zaf = connectionCallbacks0;
        this.zah = onConnectionFailedListener0;
        if(s == null || s1 == null) {
            if(componentName0 != null) {
                this.zab = s;
                this.zac = s1;
                this.zad = componentName0;
                return;
            }
        }
        else if(componentName0 == null) {
            componentName0 = null;
            this.zab = s;
            this.zac = s1;
            this.zad = componentName0;
            return;
        }
        throw new AssertionError("Must specify either package or component, but not both");
    }

    public NonGmsServiceBrokerClient(Context context0, Looper looper0, String s, String s1, ConnectionCallbacks connectionCallbacks0, OnConnectionFailedListener onConnectionFailedListener0) {
        this(context0, looper0, s, s1, null, connectionCallbacks0, onConnectionFailedListener0);
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final void connect(ConnectionProgressReportCallbacks baseGmsClient$ConnectionProgressReportCallbacks0) {
        this.zad();
        if(this.isConnected()) {
            try {
                this.disconnect("connect() called when already connected");
            }
            catch(Exception unused_ex) {
            }
        }
        try {
            Intent intent0 = new Intent();
            ComponentName componentName0 = this.zad;
            if(componentName0 == null) {
                intent0.setPackage(this.zab).setAction(this.zac);
            }
            else {
                intent0.setComponent(componentName0);
            }
            boolean z = this.zae.bindService(intent0, this, 0x1081);
            this.zaj = z;
        }
        catch(SecurityException securityException0) {
            this.zaj = false;
            this.zai = null;
            throw securityException0;
        }
        if(!z) {
            this.zai = null;
            ConnectionResult connectionResult0 = new ConnectionResult(16);
            this.zah.onConnectionFailed(connectionResult0);
        }
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final void disconnect() {
        this.zad();
        try {
            this.zae.unbindService(this);
        }
        catch(IllegalArgumentException unused_ex) {
        }
        this.zaj = false;
        this.zai = null;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final void disconnect(String s) {
        this.zad();
        this.zak = s;
        this.disconnect();
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final Feature[] getAvailableFeatures() {
        return new Feature[0];
    }

    public IBinder getBinder() {
        this.zad();
        return this.zai;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final String getEndpointPackageName() {
        String s = this.zab;
        if(s != null) {
            return s;
        }
        Preconditions.checkNotNull(this.zad);
        return this.zad.getPackageName();
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final String getLastDisconnectMessage() {
        return this.zak;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final int getMinApkVersion() {
        return 0;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final void getRemoteService(IAccountAccessor iAccountAccessor0, Set set0) {
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final Set getScopesForConnectionlessNonSignIn() {
        return Collections.EMPTY_SET;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final IBinder getServiceBrokerBinder() {
        return null;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final Intent getSignInIntent() {
        return new Intent();
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final boolean isConnected() {
        this.zad();
        return this.zai != null;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final boolean isConnecting() {
        this.zad();
        return this.zaj;
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        zacg zacg0 = new zacg(this, iBinder0);
        this.zag.post(zacg0);
    }

    @Override  // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName0) {
        zacf zacf0 = new zacf(this);
        this.zag.post(zacf0);
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final void onUserSignOut(SignOutCallbacks baseGmsClient$SignOutCallbacks0) {
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final boolean providesSignIn() {
        return false;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final boolean requiresAccount() {
        return false;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final boolean requiresGooglePlayServices() {
        return false;
    }

    @Override  // com.google.android.gms.common.api.Api$Client
    public final boolean requiresSignIn() {
        return false;
    }

    final void zaa(IBinder iBinder0) {
        this.zaj = false;
        this.zai = iBinder0;
        Bundle bundle0 = new Bundle();
        this.zaf.onConnected(bundle0);
    }

    final void zab() {
        this.zaj = false;
        this.zai = null;
        this.zaf.onConnectionSuspended(1);
    }

    public final void zac(String s) {
        this.zal = s;
    }

    private final void zad() {
        if(Thread.currentThread() != this.zag.getLooper().getThread()) {
            throw new IllegalStateException("This method should only run on the NonGmsServiceBrokerClient\'s handler thread.");
        }
    }
}

