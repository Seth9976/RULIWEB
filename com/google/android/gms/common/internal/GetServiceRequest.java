package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    static final Scope[] zza;
    static final Feature[] zzb;
    final int zzc;
    final int zzd;
    final int zze;
    String zzf;
    IBinder zzg;
    Scope[] zzh;
    Bundle zzi;
    Account zzj;
    Feature[] zzk;
    Feature[] zzl;
    final boolean zzm;
    final int zzn;
    boolean zzo;
    private final String zzp;

    static {
        GetServiceRequest.CREATOR = new zzn();
        GetServiceRequest.zza = new Scope[0];
        GetServiceRequest.zzb = new Feature[0];
    }

    GetServiceRequest(int v, int v1, int v2, String s, IBinder iBinder0, Scope[] arr_scope, Bundle bundle0, Account account0, Feature[] arr_feature, Feature[] arr_feature1, boolean z, int v3, boolean z1, String s1) {
        if(arr_scope == null) {
            arr_scope = GetServiceRequest.zza;
        }
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        if(arr_feature == null) {
            arr_feature = GetServiceRequest.zzb;
        }
        if(arr_feature1 == null) {
            arr_feature1 = GetServiceRequest.zzb;
        }
        this.zzc = v;
        this.zzd = v1;
        this.zze = v2;
        this.zzf = "com.google.android.gms".equals(s) ? "com.google.android.gms" : s;
        if(v < 2) {
            this.zzj = iBinder0 == null ? null : AccountAccessor.getAccountBinderSafe(Stub.asInterface(iBinder0));
        }
        else {
            this.zzg = iBinder0;
            this.zzj = account0;
        }
        this.zzh = arr_scope;
        this.zzi = bundle0;
        this.zzk = arr_feature;
        this.zzl = arr_feature1;
        this.zzm = z;
        this.zzn = v3;
        this.zzo = z1;
        this.zzp = s1;
    }

    public Bundle getExtraArgs() {
        return this.zzi;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        zzn.zza(this, parcel0, v);
    }

    public final String zza() {
        return this.zzp;
    }
}

