package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth-api.zbb;
import com.google.android.gms.internal.auth-api.zbc;

public abstract class zbq extends zbb implements zbr {
    public zbq() {
        super("com.google.android.gms.auth.api.signin.internal.ISignInCallbacks");
    }

    @Override  // com.google.android.gms.internal.auth-api.zbb
    protected final boolean zba(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 101: {
                GoogleSignInAccount googleSignInAccount0 = (GoogleSignInAccount)zbc.zba(parcel0, GoogleSignInAccount.CREATOR);
                Status status0 = (Status)zbc.zba(parcel0, Status.CREATOR);
                zbc.zbb(parcel0);
                this.zbd(googleSignInAccount0, status0);
                break;
            }
            case 102: {
                Status status1 = (Status)zbc.zba(parcel0, Status.CREATOR);
                zbc.zbb(parcel0);
                this.zbc(status1);
                break;
            }
            case 103: {
                Status status2 = (Status)zbc.zba(parcel0, Status.CREATOR);
                zbc.zbb(parcel0);
                this.zbb(status2);
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }
}

