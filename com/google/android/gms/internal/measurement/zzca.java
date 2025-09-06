package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public final class zzca extends zzbm implements zzcc {
    zzca(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void beginAdUnitExposure(String s, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeLong(v);
        this.zzc(23, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void clearConditionalUserProperty(String s, String s1, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzbo.zzd(parcel0, bundle0);
        this.zzc(9, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void clearMeasurementEnabled(long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeLong(v);
        this.zzc(43, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void endAdUnitExposure(String s, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeLong(v);
        this.zzc(24, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void generateEventId(zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        this.zzc(22, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getAppInstanceId(zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        this.zzc(20, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getCachedAppInstanceId(zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        this.zzc(19, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getConditionalUserProperties(String s, String s1, zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzbo.zze(parcel0, zzcf0);
        this.zzc(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getCurrentScreenClass(zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        this.zzc(17, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getCurrentScreenName(zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        this.zzc(16, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getGmpAppId(zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        this.zzc(21, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getMaxUserProperties(String s, zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        zzbo.zze(parcel0, zzcf0);
        this.zzc(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getSessionId(zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        this.zzc(46, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getTestFlag(zzcf zzcf0, int v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzcf0);
        parcel0.writeInt(v);
        this.zzc(38, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void getUserProperties(String s, String s1, boolean z, zzcf zzcf0) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeInt(((int)z));
        zzbo.zze(parcel0, zzcf0);
        this.zzc(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void initForTests(Map map0) throws RemoteException {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void initialize(IObjectWrapper iObjectWrapper0, zzcl zzcl0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        zzbo.zzd(parcel0, zzcl0);
        parcel0.writeLong(v);
        this.zzc(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void isDataCollectionEnabled(zzcf zzcf0) throws RemoteException {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void logEvent(String s, String s1, Bundle bundle0, boolean z, boolean z1, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzbo.zzd(parcel0, bundle0);
        parcel0.writeInt(((int)z));
        parcel0.writeInt(((int)z1));
        parcel0.writeLong(v);
        this.zzc(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void logEventAndBundle(String s, String s1, Bundle bundle0, zzcf zzcf0, long v) throws RemoteException {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void logHealthData(int v, String s, IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeInt(5);
        parcel0.writeString(s);
        zzbo.zze(parcel0, iObjectWrapper0);
        zzbo.zze(parcel0, iObjectWrapper1);
        zzbo.zze(parcel0, iObjectWrapper2);
        this.zzc(33, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityCreated(IObjectWrapper iObjectWrapper0, Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        zzbo.zzd(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzc(27, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzc(28, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityPaused(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzc(29, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityResumed(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzc(30, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper0, zzcf zzcf0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        zzbo.zze(parcel0, zzcf0);
        parcel0.writeLong(v);
        this.zzc(0x1F, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityStarted(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzc(25, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void onActivityStopped(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzc(26, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void performAction(Bundle bundle0, zzcf zzcf0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        zzbo.zze(parcel0, zzcf0);
        parcel0.writeLong(v);
        this.zzc(0x20, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void registerOnMeasurementEventListener(zzci zzci0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzci0);
        this.zzc(35, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void resetAnalyticsData(long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeLong(v);
        this.zzc(12, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setConditionalUserProperty(Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzc(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setConsent(Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzc(44, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setConsentThirdParty(Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzc(45, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setCurrentScreen(IObjectWrapper iObjectWrapper0, String s, String s1, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeLong(v);
        this.zzc(15, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeInt(((int)z));
        this.zzc(39, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setDefaultEventParameters(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        this.zzc(42, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setEventInterceptor(zzci zzci0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzci0);
        this.zzc(34, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setInstanceIdProvider(zzck zzck0) throws RemoteException {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setMeasurementEnabled(boolean z, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeInt(((int)z));
        parcel0.writeLong(v);
        this.zzc(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setMinimumSessionDuration(long v) throws RemoteException {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setSessionTimeoutDuration(long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeLong(v);
        this.zzc(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setUserId(String s, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeLong(v);
        this.zzc(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void setUserProperty(String s, String s1, IObjectWrapper iObjectWrapper0, boolean z, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzbo.zze(parcel0, iObjectWrapper0);
        parcel0.writeInt(((int)z));
        parcel0.writeLong(v);
        this.zzc(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public final void unregisterOnMeasurementEventListener(zzci zzci0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zze(parcel0, zzci0);
        this.zzc(36, parcel0);
    }
}

