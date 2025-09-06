package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzak implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        PublicKeyCredentialRpEntity publicKeyCredentialRpEntity0 = null;
        PublicKeyCredentialUserEntity publicKeyCredentialUserEntity0 = null;
        byte[] arr_b = null;
        List list0 = null;
        Double double0 = null;
        List list1 = null;
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria0 = null;
        Integer integer0 = null;
        TokenBinding tokenBinding0 = null;
        String s = null;
        AuthenticationExtensions authenticationExtensions0 = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 2: {
                    publicKeyCredentialRpEntity0 = (PublicKeyCredentialRpEntity)SafeParcelReader.createParcelable(parcel0, v1, PublicKeyCredentialRpEntity.CREATOR);
                    break;
                }
                case 3: {
                    publicKeyCredentialUserEntity0 = (PublicKeyCredentialUserEntity)SafeParcelReader.createParcelable(parcel0, v1, PublicKeyCredentialUserEntity.CREATOR);
                    break;
                }
                case 4: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v1);
                    break;
                }
                case 5: {
                    list0 = SafeParcelReader.createTypedList(parcel0, v1, PublicKeyCredentialParameters.CREATOR);
                    break;
                }
                case 6: {
                    double0 = SafeParcelReader.readDoubleObject(parcel0, v1);
                    break;
                }
                case 7: {
                    list1 = SafeParcelReader.createTypedList(parcel0, v1, PublicKeyCredentialDescriptor.CREATOR);
                    break;
                }
                case 8: {
                    authenticatorSelectionCriteria0 = (AuthenticatorSelectionCriteria)SafeParcelReader.createParcelable(parcel0, v1, AuthenticatorSelectionCriteria.CREATOR);
                    break;
                }
                case 9: {
                    integer0 = SafeParcelReader.readIntegerObject(parcel0, v1);
                    break;
                }
                case 10: {
                    tokenBinding0 = (TokenBinding)SafeParcelReader.createParcelable(parcel0, v1, TokenBinding.CREATOR);
                    break;
                }
                case 11: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 12: {
                    authenticationExtensions0 = (AuthenticationExtensions)SafeParcelReader.createParcelable(parcel0, v1, AuthenticationExtensions.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new PublicKeyCredentialCreationOptions(publicKeyCredentialRpEntity0, publicKeyCredentialUserEntity0, arr_b, list0, double0, list1, authenticatorSelectionCriteria0, integer0, tokenBinding0, s, authenticationExtensions0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new PublicKeyCredentialCreationOptions[v];
    }
}

