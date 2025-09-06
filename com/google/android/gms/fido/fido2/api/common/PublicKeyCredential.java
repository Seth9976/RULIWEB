package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;

public class PublicKeyCredential extends AbstractSafeParcelable {
    public static class Builder {
        private String zza;
        private byte[] zzb;
        private AuthenticatorResponse zzc;
        private AuthenticationExtensionsClientOutputs zzd;
        private String zze;

        // 去混淆评级： 中等(80)
        public PublicKeyCredential build() {
            return new PublicKeyCredential(this.zza, "public-key", this.zzb, (this.zzc instanceof AuthenticatorAttestationResponse ? ((AuthenticatorAttestationResponse)this.zzc) : null), (this.zzc instanceof AuthenticatorAssertionResponse ? ((AuthenticatorAssertionResponse)this.zzc) : null), (this.zzc instanceof AuthenticatorErrorResponse ? ((AuthenticatorErrorResponse)this.zzc) : null), this.zzd, this.zze);
        }

        public Builder setAuthenticationExtensionsClientOutputs(AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs0) {
            this.zzd = authenticationExtensionsClientOutputs0;
            return this;
        }

        public Builder setAuthenticatorAttachment(String s) {
            this.zze = s;
            return this;
        }

        public Builder setId(String s) {
            this.zza = s;
            return this;
        }

        public Builder setRawId(byte[] arr_b) {
            this.zzb = arr_b;
            return this;
        }

        public Builder setResponse(AuthenticatorResponse authenticatorResponse0) {
            this.zzc = authenticatorResponse0;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final String zza;
    private final String zzb;
    private final byte[] zzc;
    private final AuthenticatorAttestationResponse zzd;
    private final AuthenticatorAssertionResponse zze;
    private final AuthenticatorErrorResponse zzf;
    private final AuthenticationExtensionsClientOutputs zzg;
    private final String zzh;

    static {
        PublicKeyCredential.CREATOR = new zzal();
    }

    PublicKeyCredential(String s, String s1, byte[] arr_b, AuthenticatorAttestationResponse authenticatorAttestationResponse0, AuthenticatorAssertionResponse authenticatorAssertionResponse0, AuthenticatorErrorResponse authenticatorErrorResponse0, AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs0, String s2) {
        Preconditions.checkArgument(authenticatorAttestationResponse0 != null && authenticatorAssertionResponse0 == null && authenticatorErrorResponse0 == null || authenticatorAttestationResponse0 == null && authenticatorAssertionResponse0 != null && authenticatorErrorResponse0 == null || authenticatorAttestationResponse0 == null && authenticatorAssertionResponse0 == null && authenticatorErrorResponse0 != null);
        this.zza = s;
        this.zzb = s1;
        this.zzc = arr_b;
        this.zzd = authenticatorAttestationResponse0;
        this.zze = authenticatorAssertionResponse0;
        this.zzf = authenticatorErrorResponse0;
        this.zzg = authenticationExtensionsClientOutputs0;
        this.zzh = s2;
    }

    public static PublicKeyCredential deserializeFromBytes(byte[] arr_b) {
        return (PublicKeyCredential)SafeParcelableSerializer.deserializeFromBytes(arr_b, PublicKeyCredential.CREATOR);
    }

    // 去混淆评级： 中等(90)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof PublicKeyCredential ? Objects.equal(this.zza, ((PublicKeyCredential)object0).zza) && Objects.equal(this.zzb, ((PublicKeyCredential)object0).zzb) && Arrays.equals(this.zzc, ((PublicKeyCredential)object0).zzc) && Objects.equal(this.zzd, ((PublicKeyCredential)object0).zzd) && Objects.equal(this.zze, ((PublicKeyCredential)object0).zze) && Objects.equal(this.zzf, ((PublicKeyCredential)object0).zzf) && Objects.equal(this.zzg, ((PublicKeyCredential)object0).zzg) && Objects.equal(this.zzh, ((PublicKeyCredential)object0).zzh) : false;
    }

    public String getAuthenticatorAttachment() {
        return this.zzh;
    }

    public AuthenticationExtensionsClientOutputs getClientExtensionResults() {
        return this.zzg;
    }

    public String getId() {
        return this.zza;
    }

    public byte[] getRawId() {
        return this.zzc;
    }

    public AuthenticatorResponse getResponse() {
        AuthenticatorResponse authenticatorResponse0 = this.zzd;
        if(authenticatorResponse0 != null) {
            return authenticatorResponse0;
        }
        AuthenticatorResponse authenticatorResponse1 = this.zze;
        if(authenticatorResponse1 != null) {
            return authenticatorResponse1;
        }
        AuthenticatorResponse authenticatorResponse2 = this.zzf;
        if(authenticatorResponse2 == null) {
            throw new IllegalStateException("No response set.");
        }
        return authenticatorResponse2;
    }

    public String getType() {
        return this.zzb;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zze, this.zzd, this.zzf, this.zzg, this.zzh});
    }

    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.getId(), false);
        SafeParcelWriter.writeString(parcel0, 2, this.getType(), false);
        SafeParcelWriter.writeByteArray(parcel0, 3, this.getRawId(), false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zzd, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 5, this.zze, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 6, this.zzf, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 7, this.getClientExtensionResults(), v, false);
        SafeParcelWriter.writeString(parcel0, 8, this.getAuthenticatorAttachment(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

