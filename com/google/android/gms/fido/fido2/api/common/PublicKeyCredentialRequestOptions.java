package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
import java.util.List;

public class PublicKeyCredentialRequestOptions extends RequestOptions {
    public static final class Builder {
        private byte[] zza;
        private Double zzb;
        private String zzc;
        private List zzd;
        private Integer zze;
        private TokenBinding zzf;
        private zzay zzg;
        private AuthenticationExtensions zzh;

        public Builder() {
        }

        public Builder(PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions0) {
            if(publicKeyCredentialRequestOptions0 != null) {
                this.zza = publicKeyCredentialRequestOptions0.getChallenge();
                this.zzb = publicKeyCredentialRequestOptions0.getTimeoutSeconds();
                this.zzc = publicKeyCredentialRequestOptions0.getRpId();
                this.zzd = publicKeyCredentialRequestOptions0.getAllowList();
                this.zze = publicKeyCredentialRequestOptions0.getRequestId();
                this.zzf = publicKeyCredentialRequestOptions0.getTokenBinding();
                this.zzg = publicKeyCredentialRequestOptions0.zza();
                this.zzh = publicKeyCredentialRequestOptions0.getAuthenticationExtensions();
            }
        }

        public PublicKeyCredentialRequestOptions build() {
            byte[] arr_b = this.zza;
            Double double0 = this.zzb;
            String s = this.zzc;
            List list0 = this.zzd;
            Integer integer0 = this.zze;
            TokenBinding tokenBinding0 = this.zzf;
            return this.zzg == null ? new PublicKeyCredentialRequestOptions(arr_b, double0, s, list0, integer0, tokenBinding0, null, this.zzh, null) : new PublicKeyCredentialRequestOptions(arr_b, double0, s, list0, integer0, tokenBinding0, this.zzg.toString(), this.zzh, null);
        }

        public Builder setAllowList(List list0) {
            this.zzd = list0;
            return this;
        }

        public Builder setAuthenticationExtensions(AuthenticationExtensions authenticationExtensions0) {
            this.zzh = authenticationExtensions0;
            return this;
        }

        public Builder setChallenge(byte[] arr_b) {
            this.zza = (byte[])Preconditions.checkNotNull(arr_b);
            return this;
        }

        public Builder setRequestId(Integer integer0) {
            this.zze = integer0;
            return this;
        }

        public Builder setRpId(String s) {
            this.zzc = (String)Preconditions.checkNotNull(s);
            return this;
        }

        public Builder setTimeoutSeconds(Double double0) {
            this.zzb = double0;
            return this;
        }

        public Builder setTokenBinding(TokenBinding tokenBinding0) {
            this.zzf = tokenBinding0;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final byte[] zza;
    private final Double zzb;
    private final String zzc;
    private final List zzd;
    private final Integer zze;
    private final TokenBinding zzf;
    private final zzay zzg;
    private final AuthenticationExtensions zzh;
    private final Long zzi;

    static {
        PublicKeyCredentialRequestOptions.CREATOR = new zzao();
    }

    PublicKeyCredentialRequestOptions(byte[] arr_b, Double double0, String s, List list0, Integer integer0, TokenBinding tokenBinding0, String s1, AuthenticationExtensions authenticationExtensions0, Long long0) {
        this.zza = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzb = double0;
        this.zzc = (String)Preconditions.checkNotNull(s);
        this.zzd = list0;
        this.zze = integer0;
        this.zzf = tokenBinding0;
        this.zzi = long0;
        if(s1 == null) {
            this.zzg = null;
        }
        else {
            try {
                this.zzg = zzay.zza(s1);
            }
            catch(zzax zzax0) {
                throw new IllegalArgumentException(zzax0);
            }
        }
        this.zzh = authenticationExtensions0;
    }

    public static PublicKeyCredentialRequestOptions deserializeFromBytes(byte[] arr_b) {
        return (PublicKeyCredentialRequestOptions)SafeParcelableSerializer.deserializeFromBytes(arr_b, PublicKeyCredentialRequestOptions.CREATOR);
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof PublicKeyCredentialRequestOptions)) {
            return false;
        }
        if(Arrays.equals(this.zza, ((PublicKeyCredentialRequestOptions)object0).zza) && Objects.equal(this.zzb, ((PublicKeyCredentialRequestOptions)object0).zzb) && Objects.equal(this.zzc, ((PublicKeyCredentialRequestOptions)object0).zzc)) {
            List list0 = this.zzd;
            if(list0 == null && ((PublicKeyCredentialRequestOptions)object0).zzd == null) {
                return Objects.equal(this.zze, ((PublicKeyCredentialRequestOptions)object0).zze) && Objects.equal(this.zzf, ((PublicKeyCredentialRequestOptions)object0).zzf) && Objects.equal(this.zzg, ((PublicKeyCredentialRequestOptions)object0).zzg) && Objects.equal(this.zzh, ((PublicKeyCredentialRequestOptions)object0).zzh) && Objects.equal(this.zzi, ((PublicKeyCredentialRequestOptions)object0).zzi);
            }
            else if(list0 != null) {
                List list1 = ((PublicKeyCredentialRequestOptions)object0).zzd;
                return list1 == null || !list0.containsAll(list1) || !((PublicKeyCredentialRequestOptions)object0).zzd.containsAll(this.zzd) ? false : Objects.equal(this.zze, ((PublicKeyCredentialRequestOptions)object0).zze) && Objects.equal(this.zzf, ((PublicKeyCredentialRequestOptions)object0).zzf) && Objects.equal(this.zzg, ((PublicKeyCredentialRequestOptions)object0).zzg) && Objects.equal(this.zzh, ((PublicKeyCredentialRequestOptions)object0).zzh) && Objects.equal(this.zzi, ((PublicKeyCredentialRequestOptions)object0).zzi);
            }
        }
        return false;
    }

    public List getAllowList() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zzh;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public byte[] getChallenge() {
        return this.zza;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public Integer getRequestId() {
        return this.zze;
    }

    public String getRpId() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public Double getTimeoutSeconds() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public TokenBinding getTokenBinding() {
        return this.zzf;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{Arrays.hashCode(this.zza), this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi});
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.getChallenge(), false);
        SafeParcelWriter.writeDoubleObject(parcel0, 3, this.getTimeoutSeconds(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getRpId(), false);
        SafeParcelWriter.writeTypedList(parcel0, 5, this.getAllowList(), false);
        SafeParcelWriter.writeIntegerObject(parcel0, 6, this.getRequestId(), false);
        SafeParcelWriter.writeParcelable(parcel0, 7, this.getTokenBinding(), v, false);
        SafeParcelWriter.writeString(parcel0, 8, (this.zzg == null ? null : this.zzg.toString()), false);
        SafeParcelWriter.writeParcelable(parcel0, 9, this.getAuthenticationExtensions(), v, false);
        SafeParcelWriter.writeLongObject(parcel0, 10, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final zzay zza() {
        return this.zzg;
    }
}

