package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ModuleAvailabilityResponse extends AbstractSafeParcelable {
    @Retention(RetentionPolicy.CLASS)
    public @interface AvailabilityStatus {
        public static final int STATUS_ALREADY_AVAILABLE = 0;
        public static final int STATUS_READY_TO_DOWNLOAD = 1;
        public static final int STATUS_UNKNOWN_MODULE = 2;

    }

    public static final Parcelable.Creator CREATOR;
    private final boolean zaa;
    private final int zab;

    static {
        ModuleAvailabilityResponse.CREATOR = new zaa();
    }

    public ModuleAvailabilityResponse(boolean z, int v) {
        this.zaa = z;
        this.zab = v;
    }

    public boolean areModulesAvailable() {
        return this.zaa;
    }

    public int getAvailabilityStatus() {
        return this.zab;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 1, this.areModulesAvailable());
        SafeParcelWriter.writeInt(parcel0, 2, this.getAvailabilityStatus());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

