package androidx.work.multiprocess;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWorkManagerImplCallback extends IInterface {
    public static class Default implements IWorkManagerImplCallback {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImplCallback
        public void onFailure(String error) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImplCallback
        public void onSuccess(byte[] response) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IWorkManagerImplCallback {
        static class Proxy implements IWorkManagerImplCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Proxy.DESCRIPTOR;
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImplCallback
            public void onFailure(String s) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    this.mRemote.transact(2, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImplCallback
            public void onSuccess(byte[] arr_b) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeByteArray(arr_b);
                    this.mRemote.transact(1, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }
        }

        static final int TRANSACTION_onFailure = 2;
        static final int TRANSACTION_onSuccess = 1;

        public Stub() {
            this.attachInterface(this, Stub.DESCRIPTOR);
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static IWorkManagerImplCallback asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface(Stub.DESCRIPTOR);
            return iInterface0 != null && iInterface0 instanceof IWorkManagerImplCallback ? ((IWorkManagerImplCallback)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            String s = Stub.DESCRIPTOR;
            if(v >= 1 && v <= 0xFFFFFF) {
                parcel0.enforceInterface(s);
            }
            switch(v) {
                case 1: {
                    this.onSuccess(parcel0.createByteArray());
                    return true;
                }
                case 2: {
                    this.onFailure(parcel0.readString());
                    return true;
                }
                case 0x5F4E5446: {
                    parcel1.writeString(s);
                    return true;
                }
                default: {
                    return super.onTransact(v, parcel0, parcel1, v1);
                }
            }
        }
    }

    public static final String DESCRIPTOR;

    static {
        IWorkManagerImplCallback.DESCRIPTOR = "androidx.work.multiprocess.IWorkManagerImplCallback";
    }

    void onFailure(String arg1) throws RemoteException;

    void onSuccess(byte[] arg1) throws RemoteException;
}

