package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class AuthenticatorSelectionCriteria extends AbstractSafeParcelable {
    public static class Builder {
        private Attachment zza;
        private Boolean zzb;
        private ResidentKeyRequirement zzc;

        public AuthenticatorSelectionCriteria build() {
            String s = this.zza == null ? null : this.zza.toString();
            Boolean boolean0 = this.zzb;
            return this.zzc == null ? new AuthenticatorSelectionCriteria(s, boolean0, null, null) : new AuthenticatorSelectionCriteria(s, boolean0, null, this.zzc.toString());
        }

        public Builder setAttachment(Attachment attachment0) {
            this.zza = attachment0;
            return this;
        }

        public Builder setRequireResidentKey(Boolean boolean0) {
            this.zzb = boolean0;
            return this;
        }

        public Builder setResidentKeyRequirement(ResidentKeyRequirement residentKeyRequirement0) {
            this.zzc = residentKeyRequirement0;
            return this;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final Attachment zza;
    private final Boolean zzb;
    private final zzay zzc;
    private final ResidentKeyRequirement zzd;

    static {
        AuthenticatorSelectionCriteria.CREATOR = new zzm();
    }

    AuthenticatorSelectionCriteria(String s, Boolean boolean0, String s1, String s2) {
        Attachment attachment0;
        ResidentKeyRequirement residentKeyRequirement0 = null;
        if(s == null) {
            attachment0 = null;
        }
        else {
            try {
                attachment0 = Attachment.fromString(s);
            label_6:
                this.zza = attachment0;
                this.zzb = boolean0;
                this.zzc = s1 == null ? null : zzay.zza(s1);
                if(s2 != null) {
                    residentKeyRequirement0 = ResidentKeyRequirement.fromString(s2);
                }
                this.zzd = residentKeyRequirement0;
                return;
            }
            catch(UnsupportedAttachmentException | zzax | UnsupportedResidentKeyRequirementException attachment$UnsupportedAttachmentException0) {
                throw new IllegalArgumentException(attachment$UnsupportedAttachmentException0);
            }
        }
        goto label_6;
    }

    // 去混淆评级： 中等(50)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AuthenticatorSelectionCriteria ? Objects.equal(this.zza, ((AuthenticatorSelectionCriteria)object0).zza) && Objects.equal(this.zzb, ((AuthenticatorSelectionCriteria)object0).zzb) && Objects.equal(this.zzc, ((AuthenticatorSelectionCriteria)object0).zzc) && Objects.equal(this.zzd, ((AuthenticatorSelectionCriteria)object0).zzd) : false;
    }

    public Attachment getAttachment() {
        return this.zza;
    }

    public String getAttachmentAsString() {
        return this.zza == null ? null : this.zza.toString();
    }

    public Boolean getRequireResidentKey() {
        return this.zzb;
    }

    public ResidentKeyRequirement getResidentKeyRequirement() {
        return this.zzd;
    }

    public String getResidentKeyRequirementAsString() {
        return this.zzd == null ? null : this.zzd.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.getAttachmentAsString(), false);
        SafeParcelWriter.writeBooleanObject(parcel0, 3, this.getRequireResidentKey(), false);
        SafeParcelWriter.writeString(parcel0, 4, (this.zzc == null ? null : this.zzc.toString()), false);
        SafeParcelWriter.writeString(parcel0, 5, this.getResidentKeyRequirementAsString(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

