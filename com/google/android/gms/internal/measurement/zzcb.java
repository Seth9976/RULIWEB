package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.HashMap;

public abstract class zzcb extends zzbn implements zzcc {
    public zzcb() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzcc asInterface(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        return iInterface0 instanceof zzcc ? ((zzcc)iInterface0) : new zzca(iBinder0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzbn
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzcf zzcf0 = null;
        switch(v) {
            case 1: {
                IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                zzcl zzcl0 = (zzcl)zzbo.zza(parcel0, zzcl.CREATOR);
                long v2 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.initialize(iObjectWrapper0, zzcl0, v2);
                break;
            }
            case 2: {
                String s = parcel0.readString();
                String s1 = parcel0.readString();
                Parcelable parcelable0 = zzbo.zza(parcel0, Bundle.CREATOR);
                boolean z = zzbo.zzf(parcel0);
                boolean z1 = zzbo.zzf(parcel0);
                long v3 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.logEvent(s, s1, ((Bundle)parcelable0), z, z1, v3);
                break;
            }
            case 3: {
                String s2 = parcel0.readString();
                String s3 = parcel0.readString();
                Bundle bundle0 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 != null) {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface0 instanceof zzcf ? ((zzcf)iInterface0) : new zzcd(iBinder0);
                }
                long v4 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.logEventAndBundle(s2, s3, bundle0, zzcf0, v4);
                break;
            }
            case 4: {
                String s4 = parcel0.readString();
                String s5 = parcel0.readString();
                IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel0.readStrongBinder());
                boolean z2 = zzbo.zzf(parcel0);
                long v5 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setUserProperty(s4, s5, iObjectWrapper1, z2, v5);
                break;
            }
            case 5: {
                String s6 = parcel0.readString();
                String s7 = parcel0.readString();
                boolean z3 = zzbo.zzf(parcel0);
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 != null) {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface1 instanceof zzcf ? ((zzcf)iInterface1) : new zzcd(iBinder1);
                }
                zzbo.zzc(parcel0);
                this.getUserProperties(s6, s7, z3, zzcf0);
                break;
            }
            case 6: {
                String s8 = parcel0.readString();
                IBinder iBinder2 = parcel0.readStrongBinder();
                if(iBinder2 != null) {
                    IInterface iInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface2 instanceof zzcf ? ((zzcf)iInterface2) : new zzcd(iBinder2);
                }
                zzbo.zzc(parcel0);
                this.getMaxUserProperties(s8, zzcf0);
                break;
            }
            case 7: {
                String s9 = parcel0.readString();
                long v6 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setUserId(s9, v6);
                break;
            }
            case 8: {
                Bundle bundle1 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                long v7 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setConditionalUserProperty(bundle1, v7);
                break;
            }
            case 9: {
                String s10 = parcel0.readString();
                String s11 = parcel0.readString();
                Bundle bundle2 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                zzbo.zzc(parcel0);
                this.clearConditionalUserProperty(s10, s11, bundle2);
                break;
            }
            case 10: {
                String s12 = parcel0.readString();
                String s13 = parcel0.readString();
                IBinder iBinder3 = parcel0.readStrongBinder();
                if(iBinder3 != null) {
                    IInterface iInterface3 = iBinder3.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface3 instanceof zzcf ? ((zzcf)iInterface3) : new zzcd(iBinder3);
                }
                zzbo.zzc(parcel0);
                this.getConditionalUserProperties(s12, s13, zzcf0);
                break;
            }
            case 11: {
                boolean z4 = zzbo.zzf(parcel0);
                long v8 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setMeasurementEnabled(z4, v8);
                break;
            }
            case 12: {
                long v9 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.resetAnalyticsData(v9);
                break;
            }
            case 13: {
                long v10 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setMinimumSessionDuration(v10);
                break;
            }
            case 14: {
                long v11 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setSessionTimeoutDuration(v11);
                break;
            }
            case 15: {
                IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel0.readStrongBinder());
                String s14 = parcel0.readString();
                String s15 = parcel0.readString();
                long v12 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setCurrentScreen(iObjectWrapper2, s14, s15, v12);
                break;
            }
            case 16: {
                IBinder iBinder4 = parcel0.readStrongBinder();
                if(iBinder4 != null) {
                    IInterface iInterface4 = iBinder4.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface4 instanceof zzcf ? ((zzcf)iInterface4) : new zzcd(iBinder4);
                }
                zzbo.zzc(parcel0);
                this.getCurrentScreenName(zzcf0);
                break;
            }
            case 17: {
                IBinder iBinder5 = parcel0.readStrongBinder();
                if(iBinder5 != null) {
                    IInterface iInterface5 = iBinder5.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface5 instanceof zzcf ? ((zzcf)iInterface5) : new zzcd(iBinder5);
                }
                zzbo.zzc(parcel0);
                this.getCurrentScreenClass(zzcf0);
                break;
            }
            case 18: {
                IBinder iBinder6 = parcel0.readStrongBinder();
                if(iBinder6 != null) {
                    IInterface iInterface6 = iBinder6.queryLocalInterface("com.google.android.gms.measurement.api.internal.IStringProvider");
                    zzcf0 = iInterface6 instanceof zzck ? ((zzck)iInterface6) : new zzcj(iBinder6);
                }
                zzbo.zzc(parcel0);
                this.setInstanceIdProvider(((zzck)zzcf0));
                break;
            }
            case 19: {
                IBinder iBinder7 = parcel0.readStrongBinder();
                if(iBinder7 != null) {
                    IInterface iInterface7 = iBinder7.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface7 instanceof zzcf ? ((zzcf)iInterface7) : new zzcd(iBinder7);
                }
                zzbo.zzc(parcel0);
                this.getCachedAppInstanceId(zzcf0);
                break;
            }
            case 20: {
                IBinder iBinder8 = parcel0.readStrongBinder();
                if(iBinder8 != null) {
                    IInterface iInterface8 = iBinder8.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface8 instanceof zzcf ? ((zzcf)iInterface8) : new zzcd(iBinder8);
                }
                zzbo.zzc(parcel0);
                this.getAppInstanceId(zzcf0);
                break;
            }
            case 21: {
                IBinder iBinder9 = parcel0.readStrongBinder();
                if(iBinder9 != null) {
                    IInterface iInterface9 = iBinder9.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface9 instanceof zzcf ? ((zzcf)iInterface9) : new zzcd(iBinder9);
                }
                zzbo.zzc(parcel0);
                this.getGmpAppId(zzcf0);
                break;
            }
            case 22: {
                IBinder iBinder10 = parcel0.readStrongBinder();
                if(iBinder10 != null) {
                    IInterface iInterface10 = iBinder10.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface10 instanceof zzcf ? ((zzcf)iInterface10) : new zzcd(iBinder10);
                }
                zzbo.zzc(parcel0);
                this.generateEventId(zzcf0);
                break;
            }
            case 23: {
                String s16 = parcel0.readString();
                long v13 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.beginAdUnitExposure(s16, v13);
                break;
            }
            case 24: {
                String s17 = parcel0.readString();
                long v14 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.endAdUnitExposure(s17, v14);
                break;
            }
            case 25: {
                IObjectWrapper iObjectWrapper3 = Stub.asInterface(parcel0.readStrongBinder());
                long v15 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.onActivityStarted(iObjectWrapper3, v15);
                break;
            }
            case 26: {
                IObjectWrapper iObjectWrapper4 = Stub.asInterface(parcel0.readStrongBinder());
                long v16 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.onActivityStopped(iObjectWrapper4, v16);
                break;
            }
            case 27: {
                IObjectWrapper iObjectWrapper5 = Stub.asInterface(parcel0.readStrongBinder());
                Bundle bundle3 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                long v17 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.onActivityCreated(iObjectWrapper5, bundle3, v17);
                break;
            }
            case 28: {
                IObjectWrapper iObjectWrapper6 = Stub.asInterface(parcel0.readStrongBinder());
                long v18 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.onActivityDestroyed(iObjectWrapper6, v18);
                break;
            }
            case 29: {
                IObjectWrapper iObjectWrapper7 = Stub.asInterface(parcel0.readStrongBinder());
                long v19 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.onActivityPaused(iObjectWrapper7, v19);
                break;
            }
            case 30: {
                IObjectWrapper iObjectWrapper8 = Stub.asInterface(parcel0.readStrongBinder());
                long v20 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.onActivityResumed(iObjectWrapper8, v20);
                break;
            }
            case 0x1F: {
                IObjectWrapper iObjectWrapper9 = Stub.asInterface(parcel0.readStrongBinder());
                IBinder iBinder11 = parcel0.readStrongBinder();
                if(iBinder11 != null) {
                    IInterface iInterface11 = iBinder11.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface11 instanceof zzcf ? ((zzcf)iInterface11) : new zzcd(iBinder11);
                }
                long v21 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.onActivitySaveInstanceState(iObjectWrapper9, zzcf0, v21);
                break;
            }
            case 0x20: {
                Bundle bundle4 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                IBinder iBinder12 = parcel0.readStrongBinder();
                if(iBinder12 != null) {
                    IInterface iInterface12 = iBinder12.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface12 instanceof zzcf ? ((zzcf)iInterface12) : new zzcd(iBinder12);
                }
                long v22 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.performAction(bundle4, zzcf0, v22);
                break;
            }
            case 33: {
                int v23 = parcel0.readInt();
                String s18 = parcel0.readString();
                IObjectWrapper iObjectWrapper10 = Stub.asInterface(parcel0.readStrongBinder());
                IObjectWrapper iObjectWrapper11 = Stub.asInterface(parcel0.readStrongBinder());
                IObjectWrapper iObjectWrapper12 = Stub.asInterface(parcel0.readStrongBinder());
                zzbo.zzc(parcel0);
                this.logHealthData(v23, s18, iObjectWrapper10, iObjectWrapper11, iObjectWrapper12);
                break;
            }
            case 34: {
                IBinder iBinder13 = parcel0.readStrongBinder();
                if(iBinder13 != null) {
                    IInterface iInterface13 = iBinder13.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzcf0 = iInterface13 instanceof zzci ? ((zzci)iInterface13) : new zzcg(iBinder13);
                }
                zzbo.zzc(parcel0);
                this.setEventInterceptor(((zzci)zzcf0));
                break;
            }
            case 35: {
                IBinder iBinder14 = parcel0.readStrongBinder();
                if(iBinder14 != null) {
                    IInterface iInterface14 = iBinder14.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzcf0 = iInterface14 instanceof zzci ? ((zzci)iInterface14) : new zzcg(iBinder14);
                }
                zzbo.zzc(parcel0);
                this.registerOnMeasurementEventListener(((zzci)zzcf0));
                break;
            }
            case 36: {
                IBinder iBinder15 = parcel0.readStrongBinder();
                if(iBinder15 != null) {
                    IInterface iInterface15 = iBinder15.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzcf0 = iInterface15 instanceof zzci ? ((zzci)iInterface15) : new zzcg(iBinder15);
                }
                zzbo.zzc(parcel0);
                this.unregisterOnMeasurementEventListener(((zzci)zzcf0));
                break;
            }
            case 37: {
                HashMap hashMap0 = zzbo.zzb(parcel0);
                zzbo.zzc(parcel0);
                this.initForTests(hashMap0);
                break;
            }
            case 38: {
                IBinder iBinder16 = parcel0.readStrongBinder();
                if(iBinder16 != null) {
                    IInterface iInterface16 = iBinder16.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface16 instanceof zzcf ? ((zzcf)iInterface16) : new zzcd(iBinder16);
                }
                int v24 = parcel0.readInt();
                zzbo.zzc(parcel0);
                this.getTestFlag(zzcf0, v24);
                break;
            }
            case 39: {
                boolean z5 = zzbo.zzf(parcel0);
                zzbo.zzc(parcel0);
                this.setDataCollectionEnabled(z5);
                break;
            }
            case 40: {
                IBinder iBinder17 = parcel0.readStrongBinder();
                if(iBinder17 != null) {
                    IInterface iInterface17 = iBinder17.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface17 instanceof zzcf ? ((zzcf)iInterface17) : new zzcd(iBinder17);
                }
                zzbo.zzc(parcel0);
                this.isDataCollectionEnabled(zzcf0);
                break;
            }
            case 42: {
                Bundle bundle5 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                zzbo.zzc(parcel0);
                this.setDefaultEventParameters(bundle5);
                break;
            }
            case 43: {
                long v25 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.clearMeasurementEnabled(v25);
                break;
            }
            case 44: {
                Bundle bundle6 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                long v26 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setConsent(bundle6, v26);
                break;
            }
            case 45: {
                Bundle bundle7 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
                long v27 = parcel0.readLong();
                zzbo.zzc(parcel0);
                this.setConsentThirdParty(bundle7, v27);
                break;
            }
            case 46: {
                IBinder iBinder18 = parcel0.readStrongBinder();
                if(iBinder18 != null) {
                    IInterface iInterface18 = iBinder18.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzcf0 = iInterface18 instanceof zzcf ? ((zzcf)iInterface18) : new zzcd(iBinder18);
                }
                zzbo.zzc(parcel0);
                this.getSessionId(zzcf0);
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }
}

