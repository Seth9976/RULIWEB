package com.google.android.gms.common.moduleinstall.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zaf extends zaa implements IInterface {
    zaf(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.moduleinstall.internal.IModuleInstallService");
    }

    public final void zae(zae zae0, ApiFeatureRequest apiFeatureRequest0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zad(parcel0, zae0);
        zac.zac(parcel0, apiFeatureRequest0);
        this.zac(1, parcel0);
    }

    public final void zaf(zae zae0, ApiFeatureRequest apiFeatureRequest0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zad(parcel0, zae0);
        zac.zac(parcel0, apiFeatureRequest0);
        this.zac(3, parcel0);
    }

    public final void zag(zae zae0, ApiFeatureRequest apiFeatureRequest0, zah zah0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zad(parcel0, zae0);
        zac.zac(parcel0, apiFeatureRequest0);
        zac.zad(parcel0, zah0);
        this.zac(2, parcel0);
    }

    public final void zah(IStatusCallback iStatusCallback0, ApiFeatureRequest apiFeatureRequest0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zad(parcel0, iStatusCallback0);
        zac.zac(parcel0, apiFeatureRequest0);
        this.zac(4, parcel0);
    }

    public final void zai(IStatusCallback iStatusCallback0, zah zah0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zad(parcel0, iStatusCallback0);
        zac.zad(parcel0, zah0);
        this.zac(6, parcel0);
    }
}

