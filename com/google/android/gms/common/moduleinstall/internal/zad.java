package com.google.android.gms.common.moduleinstall.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public abstract class zad extends zab implements zae {
    public zad() {
        super("com.google.android.gms.common.moduleinstall.internal.IModuleInstallCallbacks");
    }

    @Override  // com.google.android.gms.internal.base.zab
    protected final boolean zaa(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                Status status0 = (Status)zac.zaa(parcel0, Status.CREATOR);
                ModuleAvailabilityResponse moduleAvailabilityResponse0 = (ModuleAvailabilityResponse)zac.zaa(parcel0, ModuleAvailabilityResponse.CREATOR);
                zac.zab(parcel0);
                this.zae(status0, moduleAvailabilityResponse0);
                return true;
            }
            case 2: {
                Status status1 = (Status)zac.zaa(parcel0, Status.CREATOR);
                ModuleInstallResponse moduleInstallResponse0 = (ModuleInstallResponse)zac.zaa(parcel0, ModuleInstallResponse.CREATOR);
                zac.zab(parcel0);
                this.zad(status1, moduleInstallResponse0);
                return true;
            }
            case 3: {
                Status status2 = (Status)zac.zaa(parcel0, Status.CREATOR);
                ModuleInstallIntentResponse moduleInstallIntentResponse0 = (ModuleInstallIntentResponse)zac.zaa(parcel0, ModuleInstallIntentResponse.CREATOR);
                zac.zab(parcel0);
                this.zac(status2, moduleInstallIntentResponse0);
                return true;
            }
            case 4: {
                Status status3 = (Status)zac.zaa(parcel0, Status.CREATOR);
                zac.zab(parcel0);
                this.zab(status3);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

