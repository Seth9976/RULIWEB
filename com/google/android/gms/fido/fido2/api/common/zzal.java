package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzal implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        byte[] arr_b = null;
        AuthenticatorAttestationResponse authenticatorAttestationResponse0 = null;
        AuthenticatorAssertionResponse authenticatorAssertionResponse0 = null;
        AuthenticatorErrorResponse authenticatorErrorResponse0 = null;
        AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs0 = null;
        String s2 = null;
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
                    arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 4: {
                    authenticatorAttestationResponse0 = (AuthenticatorAttestationResponse)SafeParcelReader.createParcelable(parcel0, v1, AuthenticatorAttestationResponse.CREATOR);
                    break;
                }
                case 5: {
                    authenticatorAssertionResponse0 = (AuthenticatorAssertionResponse)SafeParcelReader.createParcelable(parcel0, v1, AuthenticatorAssertionResponse.CREATOR);
                    break;
                }
                case 6: {
                    authenticatorErrorResponse0 = (AuthenticatorErrorResponse)SafeParcelReader.createParcelable(parcel0, v1, AuthenticatorErrorResponse.CREATOR);
                    break;
                }
                case 7: {
                    authenticationExtensionsClientOutputs0 = (AuthenticationExtensionsClientOutputs)SafeParcelReader.createParcelable(parcel0, v1, AuthenticationExtensionsClientOutputs.CREATOR);
                    break;
                }
                case 8: {
                    s2 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new PublicKeyCredential(s, s1, arr_b, authenticatorAttestationResponse0, authenticatorAssertionResponse0, authenticatorErrorResponse0, authenticationExtensionsClientOutputs0, s2);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new PublicKeyCredential[v];
    }
}

