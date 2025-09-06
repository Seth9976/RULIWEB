package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
import java.util.List;

public class PublicKeyCredentialCreationOptions extends RequestOptions {
    public static final class Builder {
        private PublicKeyCredentialRpEntity zza;
        private PublicKeyCredentialUserEntity zzb;
        private byte[] zzc;
        private List zzd;
        private Double zze;
        private List zzf;
        private AuthenticatorSelectionCriteria zzg;
        private Integer zzh;
        private TokenBinding zzi;
        private AttestationConveyancePreference zzj;
        private AuthenticationExtensions zzk;

        public PublicKeyCredentialCreationOptions build() {
            PublicKeyCredentialRpEntity publicKeyCredentialRpEntity0 = this.zza;
            PublicKeyCredentialUserEntity publicKeyCredentialUserEntity0 = this.zzb;
            byte[] arr_b = this.zzc;
            List list0 = this.zzd;
            Double double0 = this.zze;
            List list1 = this.zzf;
            AuthenticatorSelectionCriteria authenticatorSelectionCriteria0 = this.zzg;
            Integer integer0 = this.zzh;
            TokenBinding tokenBinding0 = this.zzi;
            return this.zzj == null ? new PublicKeyCredentialCreationOptions(publicKeyCredentialRpEntity0, publicKeyCredentialUserEntity0, arr_b, list0, double0, list1, authenticatorSelectionCriteria0, integer0, tokenBinding0, null, this.zzk) : new PublicKeyCredentialCreationOptions(publicKeyCredentialRpEntity0, publicKeyCredentialUserEntity0, arr_b, list0, double0, list1, authenticatorSelectionCriteria0, integer0, tokenBinding0, this.zzj.toString(), this.zzk);
        }

        public Builder setAttestationConveyancePreference(AttestationConveyancePreference attestationConveyancePreference0) {
            this.zzj = attestationConveyancePreference0;
            return this;
        }

        public Builder setAuthenticationExtensions(AuthenticationExtensions authenticationExtensions0) {
            this.zzk = authenticationExtensions0;
            return this;
        }

        public Builder setAuthenticatorSelection(AuthenticatorSelectionCriteria authenticatorSelectionCriteria0) {
            this.zzg = authenticatorSelectionCriteria0;
            return this;
        }

        public Builder setChallenge(byte[] arr_b) {
            this.zzc = (byte[])Preconditions.checkNotNull(arr_b);
            return this;
        }

        public Builder setExcludeList(List list0) {
            this.zzf = list0;
            return this;
        }

        public Builder setParameters(List list0) {
            this.zzd = (List)Preconditions.checkNotNull(list0);
            return this;
        }

        public Builder setRequestId(Integer integer0) {
            this.zzh = integer0;
            return this;
        }

        public Builder setRp(PublicKeyCredentialRpEntity publicKeyCredentialRpEntity0) {
            this.zza = (PublicKeyCredentialRpEntity)Preconditions.checkNotNull(publicKeyCredentialRpEntity0);
            return this;
        }

        public Builder setTimeoutSeconds(Double double0) {
            this.zze = double0;
            return this;
        }

        public Builder setTokenBinding(TokenBinding tokenBinding0) {
            this.zzi = tokenBinding0;
            return this;
        }

        public Builder setUser(PublicKeyCredentialUserEntity publicKeyCredentialUserEntity0) {
            this.zzb = (PublicKeyCredentialUserEntity)Preconditions.checkNotNull(publicKeyCredentialUserEntity0);
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final PublicKeyCredentialRpEntity zza;
    private final PublicKeyCredentialUserEntity zzb;
    private final byte[] zzc;
    private final List zzd;
    private final Double zze;
    private final List zzf;
    private final AuthenticatorSelectionCriteria zzg;
    private final Integer zzh;
    private final TokenBinding zzi;
    private final AttestationConveyancePreference zzj;
    private final AuthenticationExtensions zzk;

    static {
        PublicKeyCredentialCreationOptions.CREATOR = new zzak();
    }

    PublicKeyCredentialCreationOptions(PublicKeyCredentialRpEntity publicKeyCredentialRpEntity0, PublicKeyCredentialUserEntity publicKeyCredentialUserEntity0, byte[] arr_b, List list0, Double double0, List list1, AuthenticatorSelectionCriteria authenticatorSelectionCriteria0, Integer integer0, TokenBinding tokenBinding0, String s, AuthenticationExtensions authenticationExtensions0) {
        this.zza = (PublicKeyCredentialRpEntity)Preconditions.checkNotNull(publicKeyCredentialRpEntity0);
        this.zzb = (PublicKeyCredentialUserEntity)Preconditions.checkNotNull(publicKeyCredentialUserEntity0);
        this.zzc = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzd = (List)Preconditions.checkNotNull(list0);
        this.zze = double0;
        this.zzf = list1;
        this.zzg = authenticatorSelectionCriteria0;
        this.zzh = integer0;
        this.zzi = tokenBinding0;
        if(s == null) {
            this.zzj = null;
        }
        else {
            try {
                this.zzj = AttestationConveyancePreference.fromString(s);
            }
            catch(UnsupportedAttestationConveyancePreferenceException attestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException0) {
                throw new IllegalArgumentException(attestationConveyancePreference$UnsupportedAttestationConveyancePreferenceException0);
            }
        }
        this.zzk = authenticationExtensions0;
    }

    public static PublicKeyCredentialCreationOptions deserializeFromBytes(byte[] arr_b) {
        return (PublicKeyCredentialCreationOptions)SafeParcelableSerializer.deserializeFromBytes(arr_b, PublicKeyCredentialCreationOptions.CREATOR);
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof PublicKeyCredentialCreationOptions)) {
            return false;
        }
        if(Objects.equal(this.zza, ((PublicKeyCredentialCreationOptions)object0).zza) && Objects.equal(this.zzb, ((PublicKeyCredentialCreationOptions)object0).zzb) && Arrays.equals(this.zzc, ((PublicKeyCredentialCreationOptions)object0).zzc) && Objects.equal(this.zze, ((PublicKeyCredentialCreationOptions)object0).zze) && this.zzd.containsAll(((PublicKeyCredentialCreationOptions)object0).zzd) && ((PublicKeyCredentialCreationOptions)object0).zzd.containsAll(this.zzd)) {
            List list0 = this.zzf;
            if(list0 == null && ((PublicKeyCredentialCreationOptions)object0).zzf == null) {
                return Objects.equal(this.zzg, ((PublicKeyCredentialCreationOptions)object0).zzg) && Objects.equal(this.zzh, ((PublicKeyCredentialCreationOptions)object0).zzh) && Objects.equal(this.zzi, ((PublicKeyCredentialCreationOptions)object0).zzi) && Objects.equal(this.zzj, ((PublicKeyCredentialCreationOptions)object0).zzj) && Objects.equal(this.zzk, ((PublicKeyCredentialCreationOptions)object0).zzk);
            }
            else if(list0 != null) {
                List list1 = ((PublicKeyCredentialCreationOptions)object0).zzf;
                return list1 == null || !list0.containsAll(list1) || !((PublicKeyCredentialCreationOptions)object0).zzf.containsAll(this.zzf) ? false : Objects.equal(this.zzg, ((PublicKeyCredentialCreationOptions)object0).zzg) && Objects.equal(this.zzh, ((PublicKeyCredentialCreationOptions)object0).zzh) && Objects.equal(this.zzi, ((PublicKeyCredentialCreationOptions)object0).zzi) && Objects.equal(this.zzj, ((PublicKeyCredentialCreationOptions)object0).zzj) && Objects.equal(this.zzk, ((PublicKeyCredentialCreationOptions)object0).zzk);
            }
        }
        return false;
    }

    public AttestationConveyancePreference getAttestationConveyancePreference() {
        return this.zzj;
    }

    public String getAttestationConveyancePreferenceAsString() {
        return this.zzj == null ? null : this.zzj.toString();
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public AuthenticationExtensions getAuthenticationExtensions() {
        return this.zzk;
    }

    public AuthenticatorSelectionCriteria getAuthenticatorSelection() {
        return this.zzg;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public byte[] getChallenge() {
        return this.zzc;
    }

    public List getExcludeList() {
        return this.zzf;
    }

    public List getParameters() {
        return this.zzd;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public Integer getRequestId() {
        return this.zzh;
    }

    public PublicKeyCredentialRpEntity getRp() {
        return this.zza;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public Double getTimeoutSeconds() {
        return this.zze;
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public TokenBinding getTokenBinding() {
        return this.zzi;
    }

    public PublicKeyCredentialUserEntity getUser() {
        return this.zzb;
    }

    @Override
    public int hashCode() {
        Integer integer0 = Arrays.hashCode(this.zzc);
        return Objects.hashCode(new Object[]{this.zza, this.zzb, integer0, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk});
    }

    @Override  // com.google.android.gms.fido.fido2.api.common.RequestOptions
    public byte[] serializeToBytes() {
        return SafeParcelableSerializer.serializeToBytes(this);
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.getRp(), v, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.getUser(), v, false);
        SafeParcelWriter.writeByteArray(parcel0, 4, this.getChallenge(), false);
        SafeParcelWriter.writeTypedList(parcel0, 5, this.getParameters(), false);
        SafeParcelWriter.writeDoubleObject(parcel0, 6, this.getTimeoutSeconds(), false);
        SafeParcelWriter.writeTypedList(parcel0, 7, this.getExcludeList(), false);
        SafeParcelWriter.writeParcelable(parcel0, 8, this.getAuthenticatorSelection(), v, false);
        SafeParcelWriter.writeIntegerObject(parcel0, 9, this.getRequestId(), false);
        SafeParcelWriter.writeParcelable(parcel0, 10, this.getTokenBinding(), v, false);
        SafeParcelWriter.writeString(parcel0, 11, this.getAttestationConveyancePreferenceAsString(), false);
        SafeParcelWriter.writeParcelable(parcel0, 12, this.getAuthenticationExtensions(), v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

