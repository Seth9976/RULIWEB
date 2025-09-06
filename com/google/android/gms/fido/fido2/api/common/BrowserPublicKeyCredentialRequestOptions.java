package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

public class BrowserPublicKeyCredentialRequestOptions extends BrowserRequestOptions {
    public static final class Builder {
        private PublicKeyCredentialRequestOptions zza;
        private Uri zzb;
        private byte[] zzc;

        public BrowserPublicKeyCredentialRequestOptions build() {
            return new BrowserPublicKeyCredentialRequestOptions(this.zza, this.zzb, this.zzc);
        }

        public Builder setClientDataHash(byte[] arr_b) {
            BrowserPublicKeyCredentialRequestOptions.zzb(arr_b);
            this.zzc = arr_b;
            return this;
        }

        public Builder setOrigin(Uri uri0) {
            BrowserPublicKeyCredentialRequestOptions.zza(uri0);
            this.zzb = uri0;
            return this;
        }

        public Builder setPublicKeyCredentialRequestOptions(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions0) {
            this.zza = (PublicKeyCredentialRequestOptions)Preconditions.checkNotNull(publicKeyCredentialRequestOptions0);
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final PublicKeyCredentialRequestOptions zza;
    private final Uri zzb;
    private final byte[] zzc;

    static {
        BrowserPublicKeyCredentialRequestOptions.CREATOR = new zzo();
    }

    BrowserPublicKeyCredentialRequestOptions(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions0, Uri uri0, byte[] arr_b) {
        this.zza = (PublicKeyCredentialRequestOptions)Preconditions.checkNotNull(publicKeyCredentialRequestOptions0);
        BrowserPublicKeyCredentialRequestOptions.zzc(uri0);
        this.zzb = uri0;
        BrowserPublicKeyCredentialRequestOptions.zzd(arr_b);
        this.zzc = arr_b;
    }

    public static BrowserPublicKeyCredentialRequestOptions deserializeFromBytes(byte[] arr_b) {
        return (BrowserPublicKeyCredentialRequestOptions)SafeParcelableSerializer.deserializeFromBytes(arr_b, BrowserPublicKeyCredentialRequestOptions.CREATOR);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof BrowserPublicKeyCredentialRequestOptions ? Objects.equal(this.zza, ((BrowserPublicKeyCredentialRequestOptions)object0).zza) && Objects.equal(this.zzb, ((BrowserPublicKeyCredentialRequestOptions)object0).zzb) : false;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zza.getAuthenticationExtensions();
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public byte[] getChallenge() {
        return this.zza.getChallenge();
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.BrowserRequestOptions
    public byte[] getClientDataHash() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.BrowserRequestOptions
    public Uri getOrigin() {
        return this.zzb;
    }

    public PublicKeyCredentialRequestOptions getPublicKeyCredentialRequestOptions() {
        return this.zza;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public Integer getRequestId() {
        return this.zza.getRequestId();
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public Double getTimeoutSeconds() {
        return this.zza.getTimeoutSeconds();
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public TokenBinding getTokenBinding() {
        return this.zza.getTokenBinding();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.getPublicKeyCredentialRequestOptions(), v, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.getOrigin(), v, false);
        SafeParcelWriter.writeByteArray(parcel0, 4, this.getClientDataHash(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    static Uri zza(Uri uri0) {
        BrowserPublicKeyCredentialRequestOptions.zzc(uri0);
        return uri0;
    }

    static byte[] zzb(byte[] arr_b) {
        BrowserPublicKeyCredentialRequestOptions.zzd(arr_b);
        return arr_b;
    }

    private static Uri zzc(Uri uri0) {
        Preconditions.checkNotNull(uri0);
        boolean z = true;
        Preconditions.checkArgument(uri0.getScheme() != null, "origin scheme must be non-empty");
        if(uri0.getAuthority() == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "origin authority must be non-empty");
        return uri0;
    }

    private static byte[] zzd(byte[] arr_b) {
        Preconditions.checkArgument(arr_b == null || arr_b.length == 0x20, "clientDataHash must be 32 bytes long");
        return arr_b;
    }
}

