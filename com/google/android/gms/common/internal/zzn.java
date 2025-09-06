package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzn implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        Bundle bundle0 = new Bundle();
        Scope[] arr_scope = GetServiceRequest.zza;
        Bundle bundle1 = bundle0;
        Feature[] arr_feature = GetServiceRequest.zzb;
        Feature[] arr_feature1 = arr_feature;
        String s = null;
        IBinder iBinder0 = null;
        Account account0 = null;
        String s1 = null;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        boolean z = false;
        int v4 = 0;
        boolean z1 = false;
        while(parcel0.dataPosition() < v) {
            int v5 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v5)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 2: {
                    v2 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 3: {
                    v3 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 4: {
                    s = SafeParcelReader.createString(parcel0, v5);
                    break;
                }
                case 5: {
                    iBinder0 = SafeParcelReader.readIBinder(parcel0, v5);
                    break;
                }
                case 6: {
                    arr_scope = (Scope[])SafeParcelReader.createTypedArray(parcel0, v5, Scope.CREATOR);
                    break;
                }
                case 7: {
                    bundle1 = SafeParcelReader.createBundle(parcel0, v5);
                    break;
                }
                case 8: {
                    account0 = (Account)SafeParcelReader.createParcelable(parcel0, v5, Account.CREATOR);
                    break;
                }
                case 10: {
                    arr_feature = (Feature[])SafeParcelReader.createTypedArray(parcel0, v5, Feature.CREATOR);
                    break;
                }
                case 11: {
                    arr_feature1 = (Feature[])SafeParcelReader.createTypedArray(parcel0, v5, Feature.CREATOR);
                    break;
                }
                case 12: {
                    z = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 13: {
                    v4 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 14: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 15: {
                    s1 = SafeParcelReader.createString(parcel0, v5);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v5);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new GetServiceRequest(v1, v2, v3, s, iBinder0, arr_scope, bundle1, account0, arr_feature, arr_feature1, z, v4, z1, s1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new GetServiceRequest[v];
    }

    static void zza(GetServiceRequest getServiceRequest0, Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, getServiceRequest0.zzc);
        SafeParcelWriter.writeInt(parcel0, 2, getServiceRequest0.zzd);
        SafeParcelWriter.writeInt(parcel0, 3, getServiceRequest0.zze);
        SafeParcelWriter.writeString(parcel0, 4, getServiceRequest0.zzf, false);
        SafeParcelWriter.writeIBinder(parcel0, 5, getServiceRequest0.zzg, false);
        SafeParcelWriter.writeTypedArray(parcel0, 6, getServiceRequest0.zzh, v, false);
        SafeParcelWriter.writeBundle(parcel0, 7, getServiceRequest0.zzi, false);
        SafeParcelWriter.writeParcelable(parcel0, 8, getServiceRequest0.zzj, v, false);
        SafeParcelWriter.writeTypedArray(parcel0, 10, getServiceRequest0.zzk, v, false);
        SafeParcelWriter.writeTypedArray(parcel0, 11, getServiceRequest0.zzl, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 12, getServiceRequest0.zzm);
        SafeParcelWriter.writeInt(parcel0, 13, getServiceRequest0.zzn);
        SafeParcelWriter.writeBoolean(parcel0, 14, getServiceRequest0.zzo);
        SafeParcelWriter.writeString(parcel0, 15, getServiceRequest0.zza(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

