package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public abstract class zag extends zab implements zah {
    public zag() {
        super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallStatusListener");
    }

    @Override  // com.google.android.gms.internal.base.zab
    protected final boolean zaa(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            ModuleInstallStatusUpdate moduleInstallStatusUpdate0 = (ModuleInstallStatusUpdate)zac.zaa(parcel0, ModuleInstallStatusUpdate.CREATOR);
            zac.zab(parcel0);
            this.zab(moduleInstallStatusUpdate0);
            return true;
        }
        return false;
    }
}

