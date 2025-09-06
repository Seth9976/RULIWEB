package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class UvmEntry extends AbstractSafeParcelable {
    public static final class Builder {
        private int zza;
        private short zzb;
        private short zzc;

        public UvmEntry build() {
            return new UvmEntry(this.zza, this.zzb, this.zzc);
        }

        public Builder setKeyProtectionType(short v) {
            this.zzb = v;
            return this;
        }

        public Builder setMatcherProtectionType(short v) {
            this.zzc = v;
            return this;
        }

        public Builder setUserVerificationMethod(int v) {
            this.zza = v;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final int zza;
    private final short zzb;
    private final short zzc;

    static {
        UvmEntry.CREATOR = new zzba();
    }

    UvmEntry(int v, short v1, short v2) {
        this.zza = v;
        this.zzb = v1;
        this.zzc = v2;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof UvmEntry ? this.zza == ((UvmEntry)object0).zza && this.zzb == ((UvmEntry)object0).zzb && this.zzc == ((UvmEntry)object0).zzc : false;
    }

    public short getKeyProtectionType() {
        return this.zzb;
    }

    public short getMatcherProtectionType() {
        return this.zzc;
    }

    public int getUserVerificationMethod() {
        return this.zza;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.getUserVerificationMethod());
        SafeParcelWriter.writeShort(parcel0, 2, this.getKeyProtectionType());
        SafeParcelWriter.writeShort(parcel0, 3, this.getMatcherProtectionType());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

