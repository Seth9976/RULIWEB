package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        FidoAppIdExtension fidoAppIdExtension0 = null;
        zzs zzs0 = null;
        UserVerificationMethodExtension userVerificationMethodExtension0 = null;
        zzz zzz0 = null;
        zzab zzab0 = null;
        zzad zzad0 = null;
        zzu zzu0 = null;
        zzag zzag0 = null;
        GoogleThirdPartyPaymentExtension googleThirdPartyPaymentExtension0 = null;
        zzai zzai0 = null;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 2: {
                    fidoAppIdExtension0 = (FidoAppIdExtension)SafeParcelReader.createParcelable(parcel0, v1, FidoAppIdExtension.CREATOR);
                    break;
                }
                case 3: {
                    zzs0 = (zzs)SafeParcelReader.createParcelable(parcel0, v1, zzs.CREATOR);
                    break;
                }
                case 4: {
                    userVerificationMethodExtension0 = (UserVerificationMethodExtension)SafeParcelReader.createParcelable(parcel0, v1, UserVerificationMethodExtension.CREATOR);
                    break;
                }
                case 5: {
                    zzz0 = (zzz)SafeParcelReader.createParcelable(parcel0, v1, zzz.CREATOR);
                    break;
                }
                case 6: {
                    zzab0 = (zzab)SafeParcelReader.createParcelable(parcel0, v1, zzab.CREATOR);
                    break;
                }
                case 7: {
                    zzad0 = (zzad)SafeParcelReader.createParcelable(parcel0, v1, zzad.CREATOR);
                    break;
                }
                case 8: {
                    zzu0 = (zzu)SafeParcelReader.createParcelable(parcel0, v1, zzu.CREATOR);
                    break;
                }
                case 9: {
                    zzag0 = (zzag)SafeParcelReader.createParcelable(parcel0, v1, zzag.CREATOR);
                    break;
                }
                case 10: {
                    googleThirdPartyPaymentExtension0 = (GoogleThirdPartyPaymentExtension)SafeParcelReader.createParcelable(parcel0, v1, GoogleThirdPartyPaymentExtension.CREATOR);
                    break;
                }
                case 11: {
                    zzai0 = (zzai)SafeParcelReader.createParcelable(parcel0, v1, zzai.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new AuthenticationExtensions(fidoAppIdExtension0, zzs0, userVerificationMethodExtension0, zzz0, zzab0, zzad0, zzu0, zzag0, googleThirdPartyPaymentExtension0, zzai0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new AuthenticationExtensions[v];
    }
}

