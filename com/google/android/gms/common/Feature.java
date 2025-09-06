package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final String zza;
    @Deprecated
    private final int zzb;
    private final long zzc;

    static {
        Feature.CREATOR = new zzc();
    }

    public Feature(String s, int v, long v1) {
        this.zza = s;
        this.zzb = v;
        this.zzc = v1;
    }

    public Feature(String s, long v) {
        this.zza = s;
        this.zzc = v;
        this.zzb = -1;
    }

    // 去混淆评级： 低(30)
    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof Feature && (this.getName() != null && this.getName().equals(((Feature)object0).getName()) || this.getName() == null && ((Feature)object0).getName() == null) && this.getVersion() == ((Feature)object0).getVersion();
    }

    public String getName() {
        return this.zza;
    }

    public long getVersion() {
        return this.zzc == -1L ? ((long)this.zzb) : this.zzc;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.getName(), this.getVersion()});
    }

    @Override
    public final String toString() {
        ToStringHelper objects$ToStringHelper0 = Objects.toStringHelper(this);
        objects$ToStringHelper0.add("name", this.getName());
        objects$ToStringHelper0.add("version", this.getVersion());
        return objects$ToStringHelper0.toString();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.getName(), false);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel0, 3, this.getVersion());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

