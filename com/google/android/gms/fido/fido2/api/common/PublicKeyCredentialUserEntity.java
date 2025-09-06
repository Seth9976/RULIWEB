package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

public class PublicKeyCredentialUserEntity extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final byte[] zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    static {
        PublicKeyCredentialUserEntity.CREATOR = new zzar();
    }

    public PublicKeyCredentialUserEntity(byte[] arr_b, String s, String s1, String s2) {
        this.zza = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzb = (String)Preconditions.checkNotNull(s);
        this.zzc = s1;
        this.zzd = (String)Preconditions.checkNotNull(s2);
    }

    // 去混淆评级： 中等(50)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof PublicKeyCredentialUserEntity ? Arrays.equals(this.zza, ((PublicKeyCredentialUserEntity)object0).zza) && Objects.equal(this.zzb, ((PublicKeyCredentialUserEntity)object0).zzb) && Objects.equal(this.zzc, ((PublicKeyCredentialUserEntity)object0).zzc) && Objects.equal(this.zzd, ((PublicKeyCredentialUserEntity)object0).zzd) : false;
    }

    public String getDisplayName() {
        return this.zzd;
    }

    public String getIcon() {
        return this.zzc;
    }

    public byte[] getId() {
        return this.zza;
    }

    public String getName() {
        return this.zzb;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zzd});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.getId(), false);
        SafeParcelWriter.writeString(parcel0, 3, this.getName(), false);
        SafeParcelWriter.writeString(parcel0, 4, this.getIcon(), false);
        SafeParcelWriter.writeString(parcel0, 5, this.getDisplayName(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

