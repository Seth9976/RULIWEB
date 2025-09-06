package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

public class AuthenticationExtensionsClientOutputs extends AbstractSafeParcelable {
    public static final class Builder {
        private UvmEntries zza;
        private AuthenticationExtensionsCredPropsOutputs zzb;

        public AuthenticationExtensionsClientOutputs build() {
            return new AuthenticationExtensionsClientOutputs(this.zza, null, this.zzb, null);
        }

        public Builder setCredProps(AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs0) {
            this.zzb = authenticationExtensionsCredPropsOutputs0;
            return this;
        }

        public Builder setUserVerificationMethodEntries(UvmEntries uvmEntries0) {
            this.zza = uvmEntries0;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final UvmEntries zza;
    private final zzf zzb;
    private final AuthenticationExtensionsCredPropsOutputs zzc;
    private final zzh zzd;

    static {
        AuthenticationExtensionsClientOutputs.CREATOR = new zzc();
    }

    AuthenticationExtensionsClientOutputs(UvmEntries uvmEntries0, zzf zzf0, AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs0, zzh zzh0) {
        this.zza = uvmEntries0;
        this.zzb = zzf0;
        this.zzc = authenticationExtensionsCredPropsOutputs0;
        this.zzd = zzh0;
    }

    public static AuthenticationExtensionsClientOutputs deserializeFromBytes(byte[] arr_b) {
        return (AuthenticationExtensionsClientOutputs)SafeParcelableSerializer.deserializeFromBytes(arr_b, AuthenticationExtensionsClientOutputs.CREATOR);
    }

    // 去混淆评级： 中等(50)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AuthenticationExtensionsClientOutputs ? Objects.equal(this.zza, ((AuthenticationExtensionsClientOutputs)object0).zza) && Objects.equal(this.zzb, ((AuthenticationExtensionsClientOutputs)object0).zzb) && Objects.equal(this.zzc, ((AuthenticationExtensionsClientOutputs)object0).zzc) && Objects.equal(this.zzd, ((AuthenticationExtensionsClientOutputs)object0).zzd) : false;
    }

    public AuthenticationExtensionsCredPropsOutputs getCredProps() {
        return this.zzc;
    }

    public UvmEntries getUvmEntries() {
        return this.zza;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd});
    }

    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.getUvmEntries(), v, false);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zzb, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.getCredProps(), v, false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zzd, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

