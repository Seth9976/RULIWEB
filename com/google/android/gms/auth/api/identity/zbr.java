package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;

public final class zbr implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        Uri uri0 = null;
        String s4 = null;
        String s5 = null;
        String s6 = null;
        PublicKeyCredential publicKeyCredential0 = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 1: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 2: {
                    s1 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 3: {
                    s2 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 4: {
                    s3 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 5: {
                    uri0 = (Uri)SafeParcelReader.createParcelable(parcel0, v1, Uri.CREATOR);
                    break;
                }
                case 6: {
                    s4 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 7: {
                    s5 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 8: {
                    s6 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 9: {
                    publicKeyCredential0 = (PublicKeyCredential)SafeParcelReader.createParcelable(parcel0, v1, PublicKeyCredential.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new SignInCredential(s, s1, s2, s3, uri0, s4, s5, s6, publicKeyCredential0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new SignInCredential[v];
    }
}

