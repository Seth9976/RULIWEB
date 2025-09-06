package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;
import com.google.android.gms.internal.fido.zzbf;
import java.util.Arrays;

public class AuthenticatorAttestationResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator CREATOR;
    private final byte[] zza;
    private final byte[] zzb;
    private final byte[] zzc;
    private final String[] zzd;

    static {
        AuthenticatorAttestationResponse.CREATOR = new zzk();
    }

    AuthenticatorAttestationResponse(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, String[] arr_s) {
        this.zza = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzb = (byte[])Preconditions.checkNotNull(arr_b1);
        this.zzc = (byte[])Preconditions.checkNotNull(arr_b2);
        this.zzd = (String[])Preconditions.checkNotNull(arr_s);
    }

    public static AuthenticatorAttestationResponse deserializeFromBytes(byte[] arr_b) {
        return (AuthenticatorAttestationResponse)SafeParcelableSerializer.deserializeFromBytes(arr_b, AuthenticatorAttestationResponse.CREATOR);
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AuthenticatorAttestationResponse ? Arrays.equals(this.zza, ((AuthenticatorAttestationResponse)object0).zza) && Arrays.equals(this.zzb, ((AuthenticatorAttestationResponse)object0).zzb) && Arrays.equals(this.zzc, ((AuthenticatorAttestationResponse)object0).zzc) : false;
    }

    public byte[] getAttestationObject() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    public byte[] getClientDataJSON() {
        return this.zzb;
    }

    @Deprecated
    public byte[] getKeyHandle() {
        return this.zza;
    }

    public String[] getTransports() {
        return this.zzd;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{Arrays.hashCode(this.zza), Arrays.hashCode(this.zzb), Arrays.hashCode(this.zzc)});
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override
    public String toString() {
        zzaj zzaj0 = zzak.zza(this);
        zzaj0.zzb("keyHandle", zzbf.zzd().zze(this.zza, 0, this.zza.length));
        zzaj0.zzb("clientDataJSON", zzbf.zzd().zze(this.zzb, 0, this.zzb.length));
        zzaj0.zzb("attestationObject", zzbf.zzd().zze(this.zzc, 0, this.zzc.length));
        zzaj0.zzb("transports", Arrays.toString(this.zzd));
        return zzaj0.toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.getKeyHandle(), false);
        SafeParcelWriter.writeByteArray(parcel0, 3, this.getClientDataJSON(), false);
        SafeParcelWriter.writeByteArray(parcel0, 4, this.getAttestationObject(), false);
        SafeParcelWriter.writeStringArray(parcel0, 5, this.getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

