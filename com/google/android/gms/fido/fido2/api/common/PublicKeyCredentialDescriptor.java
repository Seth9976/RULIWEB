package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzau;
import com.google.android.gms.internal.fido.zzh;
import java.util.Arrays;
import java.util.List;

public class PublicKeyCredentialDescriptor extends AbstractSafeParcelable {
    public static class UnsupportedPubKeyCredDescriptorException extends Exception {
        public UnsupportedPubKeyCredDescriptorException(String s) {
            super(s);
        }

        public UnsupportedPubKeyCredDescriptorException(String s, Throwable throwable0) {
            super(s, throwable0);
        }
    }

    public static final Parcelable.Creator CREATOR;
    private static final zzau zza;
    private final PublicKeyCredentialType zzb;
    private final byte[] zzc;
    private final List zzd;

    static {
        PublicKeyCredentialDescriptor.zza = zzau.zzi(zzh.zza, zzh.zzb);
        PublicKeyCredentialDescriptor.CREATOR = new zzam();
    }

    public PublicKeyCredentialDescriptor(String s, byte[] arr_b, List list0) {
        Preconditions.checkNotNull(s);
        try {
            this.zzb = PublicKeyCredentialType.fromString(s);
        }
        catch(UnsupportedPublicKeyCredTypeException publicKeyCredentialType$UnsupportedPublicKeyCredTypeException0) {
            throw new IllegalArgumentException(publicKeyCredentialType$UnsupportedPublicKeyCredTypeException0);
        }
        this.zzc = (byte[])Preconditions.checkNotNull(arr_b);
        this.zzd = list0;
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof PublicKeyCredentialDescriptor)) {
            return false;
        }
        if(!this.zzb.equals(((PublicKeyCredentialDescriptor)object0).zzb)) {
            return false;
        }
        if(!Arrays.equals(this.zzc, ((PublicKeyCredentialDescriptor)object0).zzc)) {
            return false;
        }
        List list0 = this.zzd;
        if(list0 == null && ((PublicKeyCredentialDescriptor)object0).zzd == null) {
            return true;
        }
        if(list0 != null) {
            List list1 = ((PublicKeyCredentialDescriptor)object0).zzd;
            return list1 != null && list0.containsAll(list1) && ((PublicKeyCredentialDescriptor)object0).zzd.containsAll(this.zzd);
        }
        return false;
    }

    public byte[] getId() {
        return this.zzc;
    }

    public List getTransports() {
        return this.zzd;
    }

    public PublicKeyCredentialType getType() {
        return this.zzb;
    }

    public String getTypeAsString() {
        return this.zzb.toString();
    }

    @Override
    public int hashCode() {
        Integer integer0 = Arrays.hashCode(this.zzc);
        return Objects.hashCode(new Object[]{this.zzb, integer0, this.zzd});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.getTypeAsString(), false);
        SafeParcelWriter.writeByteArray(parcel0, 3, this.getId(), false);
        SafeParcelWriter.writeTypedList(parcel0, 4, this.getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

