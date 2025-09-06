package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;

public class AuthenticatorErrorResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator CREATOR;
    private final ErrorCode zza;
    private final String zzb;
    private final int zzc;

    static {
        AuthenticatorErrorResponse.CREATOR = new zzl();
    }

    AuthenticatorErrorResponse(int v, String s, int v1) {
        try {
            this.zza = ErrorCode.toErrorCode(v);
            this.zzb = s;
            this.zzc = v1;
        }
        catch(UnsupportedErrorCodeException errorCode$UnsupportedErrorCodeException0) {
            throw new IllegalArgumentException(errorCode$UnsupportedErrorCodeException0);
        }
    }

    public static AuthenticatorErrorResponse deserializeFromBytes(byte[] arr_b) {
        return (AuthenticatorErrorResponse)SafeParcelableSerializer.deserializeFromBytes(arr_b, AuthenticatorErrorResponse.CREATOR);
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AuthenticatorErrorResponse ? Objects.equal(this.zza, ((AuthenticatorErrorResponse)object0).zza) && Objects.equal(this.zzb, ((AuthenticatorErrorResponse)object0).zzb) && Objects.equal(this.zzc, ((AuthenticatorErrorResponse)object0).zzc) : false;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    public byte[] getClientDataJSON() {
        throw new UnsupportedOperationException();
    }

    public ErrorCode getErrorCode() {
        return this.zza;
    }

    public int getErrorCodeAsInt() {
        return this.zza.getCode();
    }

    public String getErrorMessage() {
        return this.zzb;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc});
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override
    public String toString() {
        zzaj zzaj0 = zzak.zza(this);
        zzaj0.zza("errorCode", this.zza.getCode());
        String s = this.zzb;
        if(s != null) {
            zzaj0.zzb("errorMessage", s);
        }
        return zzaj0.toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 2, this.getErrorCodeAsInt());
        SafeParcelWriter.writeString(parcel0, 3, this.getErrorMessage(), false);
        SafeParcelWriter.writeInt(parcel0, 4, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

