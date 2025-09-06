package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IAccountAccessor extends IInterface {
    public static abstract class Stub extends zzb implements IAccountAccessor {
        public Stub() {
            super("com.google.android.gms.common.internal.IAccountAccessor");
        }

        public static IAccountAccessor asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            return iInterface0 instanceof IAccountAccessor ? ((IAccountAccessor)iInterface0) : new zzw(iBinder0);
        }

        @Override  // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v == 2) {
                Account account0 = this.zzb();
                parcel1.writeNoException();
                zzc.zzd(parcel1, account0);
                return true;
            }
            return false;
        }
    }

    Account zzb() throws RemoteException;
}

