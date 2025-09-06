package com.google.android.gms.internal.auth;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.auth.zze;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

final class zzi extends GmsClient {
    zzi(Context context0, Looper looper0, ClientSettings clientSettings0, ConnectionCallbacks connectionCallbacks0, OnConnectionFailedListener onConnectionFailedListener0) {
        super(context0, looper0, 0xE0, clientSettings0, connectionCallbacks0, onConnectionFailedListener0);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final IInterface createServiceInterface(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.auth.account.data.IGoogleAuthService");
        return iInterface0 instanceof zzp ? ((zzp)iInterface0) : new zzp(iBinder0);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final void disconnect(String s) {
        Log.w("GoogleAuthSvcClientImpl", "GoogleAuthServiceClientImpl disconnected with reason: " + s);
        super.disconnect(s);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return new Feature[]{zze.zzl, zze.zzk, zze.zza};
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final int getMinApkVersion() {
        return 17895000;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.auth.account.data.IGoogleAuthService";
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.auth.account.authapi.START";
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final boolean getUseDynamicLookup() {
        return true;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }
}

