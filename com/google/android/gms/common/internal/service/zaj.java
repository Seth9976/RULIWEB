package com.google.android.gms.common.internal.service;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public abstract class zaj extends zab implements zak {
    public zaj() {
        super("com.google.android.gms.common.internal.service.ICommonCallbacks");
    }

    @Override  // com.google.android.gms.internal.base.zab
    protected final boolean zaa(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            int v2 = parcel0.readInt();
            zac.zab(parcel0);
            this.zab(v2);
            return true;
        }
        return false;
    }
}

