package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzb;
import com.google.android.gms.internal.auth.zzc;

public abstract class zza extends zzb implements com.google.android.gms.auth.account.zzb {
    public zza() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    @Override  // com.google.android.gms.internal.auth.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        boolean z = false;
        switch(v) {
            case 1: {
                Account account0 = (Account)zzc.zza(parcel0, Account.CREATOR);
                zzc.zzb(parcel0);
                this.zzb(account0);
                return true;
            }
            case 2: {
                int v2 = parcel0.readInt();
                zzc.zzb(parcel0);
                if(v2 != 0) {
                    z = true;
                }
                this.zzc(z);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

