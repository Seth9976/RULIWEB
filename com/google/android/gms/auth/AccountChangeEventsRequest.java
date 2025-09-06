package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    int zzb;
    @Deprecated
    String zzc;
    Account zzd;

    static {
        AccountChangeEventsRequest.CREATOR = new zzb();
    }

    public AccountChangeEventsRequest() {
        this.zza = 1;
    }

    AccountChangeEventsRequest(int v, int v1, String s, Account account0) {
        this.zza = v;
        this.zzb = v1;
        this.zzc = s;
        if(account0 == null && !TextUtils.isEmpty(s)) {
            this.zzd = new Account(s, "com.google");
            return;
        }
        this.zzd = account0;
    }

    public Account getAccount() {
        return this.zzd;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzc;
    }

    public int getEventIndex() {
        return this.zzb;
    }

    public AccountChangeEventsRequest setAccount(Account account0) {
        this.zzd = account0;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String s) {
        this.zzc = s;
        return this;
    }

    public AccountChangeEventsRequest setEventIndex(int v) {
        this.zzb = v;
        return this;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzb);
        SafeParcelWriter.writeString(parcel0, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zzd, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

