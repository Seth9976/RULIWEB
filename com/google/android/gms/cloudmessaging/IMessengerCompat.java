package com.google.android.gms.cloudmessaging;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

interface IMessengerCompat extends IInterface {
    public static class Impl extends Binder implements IMessengerCompat {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            throw null;
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            throw null;
        }

        @Override  // com.google.android.gms.cloudmessaging.IMessengerCompat
        public void send(Message message0) throws RemoteException {
            throw null;
        }
    }

    public static class Proxy implements IMessengerCompat {
        private final IBinder zza;

        Proxy(IBinder iBinder0) {
            this.zza = iBinder0;
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this.zza;
        }

        @Override  // com.google.android.gms.cloudmessaging.IMessengerCompat
        public void send(Message message0) throws RemoteException {
            Parcel parcel0 = Parcel.obtain();
            parcel0.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
            parcel0.writeInt(1);
            message0.writeToParcel(parcel0, 0);
            try {
                this.zza.transact(1, parcel0, null, 1);
            }
            finally {
                parcel0.recycle();
            }
        }
    }

    public static final String DESCRIPTOR = "com.google.android.gms.iid.IMessengerCompat";
    public static final int TRANSACTION_SEND = 1;

    void send(Message arg1) throws RemoteException;
}

