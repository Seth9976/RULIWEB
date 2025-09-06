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

public class AuthenticatorAssertionResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator CREATOR;
    private final byte[] zza;
    private final byte[] zzb;
    private final byte[] zzc;
    private final byte[] zzd;
    private final byte[] zze;

    static {
        AuthenticatorAssertionResponse.CREATOR = new zzj();
    }

    AuthenticatorAssertionResponse(byte[] arr_b, byte[] arr_b1, byte[] arr_b2, byte[] arr_b3, byte[] arr_b4) {
        this.zza = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzb = (byte[])Preconditions.checkNotNull(arr_b1);
        this.zzc = (byte[])Preconditions.checkNotNull(arr_b2);
        this.zzd = (byte[])Preconditions.checkNotNull(arr_b3);
        this.zze = arr_b4;
    }

    public static AuthenticatorAssertionResponse deserializeFromBytes(byte[] arr_b) {
        return (AuthenticatorAssertionResponse)SafeParcelableSerializer.deserializeFromBytes(arr_b, AuthenticatorAssertionResponse.CREATOR);
    }

    // 去混淆评级： 中等(60)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AuthenticatorAssertionResponse ? Arrays.equals(this.zza, ((AuthenticatorAssertionResponse)object0).zza) && Arrays.equals(this.zzb, ((AuthenticatorAssertionResponse)object0).zzb) && Arrays.equals(this.zzc, ((AuthenticatorAssertionResponse)object0).zzc) && Arrays.equals(this.zzd, ((AuthenticatorAssertionResponse)object0).zzd) && Arrays.equals(this.zze, ((AuthenticatorAssertionResponse)object0).zze) : false;
    }

    public byte[] getAuthenticatorData() {
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

    public byte[] getSignature() {
        return this.zzd;
    }

    public byte[] getUserHandle() {
        return this.zze;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{Arrays.hashCode(this.zza), Arrays.hashCode(this.zzb), Arrays.hashCode(this.zzc), Arrays.hashCode(this.zzd), Arrays.hashCode(this.zze)});
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
        zzaj0.zzb("authenticatorData", zzbf.zzd().zze(this.zzc, 0, this.zzc.length));
        zzaj0.zzb("signature", zzbf.zzd().zze(this.zzd, 0, this.zzd.length));
        byte[] arr_b = this.zze;
        if(arr_b != null) {
            zzaj0.zzb("userHandle", zzbf.zzd().zze(arr_b, 0, arr_b.length));
        }
        return zzaj0.toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.getKeyHandle(), false);
        SafeParcelWriter.writeByteArray(parcel0, 3, this.getClientDataJSON(), false);
        SafeParcelWriter.writeByteArray(parcel0, 4, this.getAuthenticatorData(), false);
        SafeParcelWriter.writeByteArray(parcel0, 5, this.getSignature(), false);
        SafeParcelWriter.writeByteArray(parcel0, 6, this.getUserHandle(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

