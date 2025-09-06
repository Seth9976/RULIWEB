package com.google.android.gms.common.internal.service;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.internal.base.zaf;

public final class zap extends GmsClient {
    private final TelemetryLoggingOptions zaa;

    public zap(Context context0, Looper looper0, ClientSettings clientSettings0, TelemetryLoggingOptions telemetryLoggingOptions0, ConnectionCallbacks connectionCallbacks0, OnConnectionFailedListener onConnectionFailedListener0) {
        super(context0, looper0, 270, clientSettings0, connectionCallbacks0, onConnectionFailedListener0);
        this.zaa = telemetryLoggingOptions0;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final IInterface createServiceInterface(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.service.IClientTelemetryService");
        return iInterface0 instanceof zai ? ((zai)iInterface0) : new zai(iBinder0);
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return zaf.zab;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        return this.zaa.zaa();
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final int getMinApkVersion() {
        return 203400000;
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.common.internal.service.IClientTelemetryService";
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.common.telemetry.service.START";
    }

    @Override  // com.google.android.gms.common.internal.BaseGmsClient
    protected final boolean getUseDynamicLookup() {
        return true;
    }
}

