package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

public class COSEAlgorithmIdentifier implements Parcelable {
    public static class UnsupportedAlgorithmIdentifierException extends Exception {
        public UnsupportedAlgorithmIdentifierException(int v) {
            super("Algorithm with COSE value " + v + " not supported");
        }
    }

    public static final Parcelable.Creator CREATOR;
    private final Algorithm zza;

    static {
        COSEAlgorithmIdentifier.CREATOR = new zzp();
    }

    COSEAlgorithmIdentifier(Algorithm algorithm0) {
        this.zza = (Algorithm)Preconditions.checkNotNull(algorithm0);
    }

    @Override  // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof COSEAlgorithmIdentifier ? this.zza.getAlgoValue() == ((COSEAlgorithmIdentifier)object0).zza.getAlgoValue() : false;
    }

    public static COSEAlgorithmIdentifier fromCoseValue(int v) throws UnsupportedAlgorithmIdentifierException {
        if(v == RSAAlgorithm.LEGACY_RS1.getAlgoValue()) {
            return new COSEAlgorithmIdentifier(RSAAlgorithm.RS1);
        }
        RSAAlgorithm[] arr_rSAAlgorithm = RSAAlgorithm.values();
        for(int v2 = 0; v2 < arr_rSAAlgorithm.length; ++v2) {
            RSAAlgorithm rSAAlgorithm0 = arr_rSAAlgorithm[v2];
            if(rSAAlgorithm0.getAlgoValue() == v) {
                return new COSEAlgorithmIdentifier(rSAAlgorithm0);
            }
        }
        EC2Algorithm[] arr_eC2Algorithm = EC2Algorithm.values();
        for(int v1 = 0; v1 < arr_eC2Algorithm.length; ++v1) {
            EC2Algorithm eC2Algorithm0 = arr_eC2Algorithm[v1];
            if(eC2Algorithm0.getAlgoValue() == v) {
                return new COSEAlgorithmIdentifier(eC2Algorithm0);
            }
        }
        throw new UnsupportedAlgorithmIdentifierException(v);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    public int toCoseValue() {
        return this.zza.getAlgoValue();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeInt(this.zza.getAlgoValue());
    }
}

