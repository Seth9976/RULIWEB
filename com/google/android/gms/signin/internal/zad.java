package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zab;
import com.google.android.gms.internal.base.zac;

public abstract class zad extends zab implements zae {
    public zad() {
        super("com.google.android.gms.signin.internal.ISignInCallbacks");
    }

    @Override  // com.google.android.gms.internal.base.zab
    protected final boolean zaa(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 3: {
                ConnectionResult connectionResult0 = (ConnectionResult)zac.zaa(parcel0, ConnectionResult.CREATOR);
                zaa zaa0 = (zaa)zac.zaa(parcel0, zaa.CREATOR);
                zac.zab(parcel0);
                break;
            }
            case 4: {
                Status status0 = (Status)zac.zaa(parcel0, Status.CREATOR);
                zac.zab(parcel0);
                break;
            }
            case 6: {
                Status status1 = (Status)zac.zaa(parcel0, Status.CREATOR);
                zac.zab(parcel0);
                break;
            }
            case 7: {
                Status status2 = (Status)zac.zaa(parcel0, Status.CREATOR);
                GoogleSignInAccount googleSignInAccount0 = (GoogleSignInAccount)zac.zaa(parcel0, GoogleSignInAccount.CREATOR);
                zac.zab(parcel0);
                break;
            }
            case 8: {
                zak zak0 = (zak)zac.zaa(parcel0, zak.CREATOR);
                zac.zab(parcel0);
                this.zab(zak0);
                break;
            }
            case 9: {
                zag zag0 = (zag)zac.zaa(parcel0, zag.CREATOR);
                zac.zab(parcel0);
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

