package com.google.android.gms.common.moduleinstall;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class ModuleInstallIntentResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final PendingIntent zaa;

    static {
        ModuleInstallIntentResponse.CREATOR = new zab();
    }

    public ModuleInstallIntentResponse(PendingIntent pendingIntent0) {
        this.zaa = pendingIntent0;
    }

    public PendingIntent getPendingIntent() {
        return this.zaa;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.getPendingIntent(), v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

