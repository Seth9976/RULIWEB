package com.pairip.licensecheck;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public interface ILicenseV2ResultListener extends IInterface {
    public static abstract class Stub extends Binder implements ILicenseV2ResultListener {
        static final int TRANSACTION_VERIFY_LICENSE = 1;

        public Stub() {
            this.attachInterface(this, "com.android.vending.licensing.ILicenseV2ResultListener");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v >= 1 && v <= 0xFFFFFF) {
                parcel0.enforceInterface("com.android.vending.licensing.ILicenseV2ResultListener");
            }
            switch(v) {
                case 1: {
                    this.verifyLicense(parcel0.readInt(), ((Bundle)Stub.readTypedObject(parcel0, Bundle.CREATOR)));
                    return true;
                }
                case 0x5F4E5446: {
                    parcel1.writeString("com.android.vending.licensing.ILicenseV2ResultListener");
                    return true;
                }
                default: {
                    return super.onTransact(v, parcel0, parcel1, v1);
                }
            }
        }

        private static Object readTypedObject(Parcel parcel0, Parcelable.Creator parcelable$Creator0) {
            return parcel0.readInt() == 0 ? null : parcelable$Creator0.createFromParcel(parcel0);
        }
    }

    public static final String DESCRIPTOR = "com.android.vending.licensing.ILicenseV2ResultListener";

    void verifyLicense(int arg1, Bundle arg2) throws RemoteException;
}

