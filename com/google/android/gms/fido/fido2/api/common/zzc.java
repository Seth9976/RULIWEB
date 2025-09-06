package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzc implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        UvmEntries uvmEntries0 = null;
        zzf zzf0 = null;
        AuthenticationExtensionsCredPropsOutputs authenticationExtensionsCredPropsOutputs0 = null;
        zzh zzh0 = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 1: {
                    uvmEntries0 = (UvmEntries)SafeParcelReader.createParcelable(parcel0, v1, UvmEntries.CREATOR);
                    break;
                }
                case 2: {
                    zzf0 = (zzf)SafeParcelReader.createParcelable(parcel0, v1, zzf.CREATOR);
                    break;
                }
                case 3: {
                    authenticationExtensionsCredPropsOutputs0 = (AuthenticationExtensionsCredPropsOutputs)SafeParcelReader.createParcelable(parcel0, v1, AuthenticationExtensionsCredPropsOutputs.CREATOR);
                    break;
                }
                case 4: {
                    zzh0 = (zzh)SafeParcelReader.createParcelable(parcel0, v1, zzh.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new AuthenticationExtensionsClientOutputs(uvmEntries0, zzf0, authenticationExtensionsCredPropsOutputs0, zzh0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new AuthenticationExtensionsClientOutputs[v];
    }
}

