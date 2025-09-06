package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class ModuleInstallResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final int zaa;
    private final boolean zab;

    static {
        ModuleInstallResponse.CREATOR = new zad();
    }

    public ModuleInstallResponse(int v) {
        this(v, false);
    }

    public ModuleInstallResponse(int v, boolean z) {
        this.zaa = v;
        this.zab = z;
    }

    public boolean areModulesAlreadyInstalled() {
        return this.zaa == 0;
    }

    public int getSessionId() {
        return this.zaa;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.getSessionId());
        SafeParcelWriter.writeBoolean(parcel0, 2, this.zab);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final boolean zaa() {
        return this.zab;
    }
}

