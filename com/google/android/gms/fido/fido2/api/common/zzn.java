package com.google.android.gms.fido.fido2.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzn implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions0 = null;
        Uri uri0 = null;
        byte[] arr_b = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 2: {
                    publicKeyCredentialCreationOptions0 = (PublicKeyCredentialCreationOptions)SafeParcelReader.createParcelable(parcel0, v1, PublicKeyCredentialCreationOptions.CREATOR);
                    break;
                }
                case 3: {
                    uri0 = (Uri)SafeParcelReader.createParcelable(parcel0, v1, Uri.CREATOR);
                    break;
                }
                case 4: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new BrowserPublicKeyCredentialCreationOptions(publicKeyCredentialCreationOptions0, uri0, arr_b);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new BrowserPublicKeyCredentialCreationOptions[v];
    }
}

