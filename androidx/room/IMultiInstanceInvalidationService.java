package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMultiInstanceInvalidationService extends IInterface {
    public static class Default implements IMultiInstanceInvalidationService {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // androidx.room.IMultiInstanceInvalidationService
        public void broadcastInvalidation(int v, String[] arr_s) throws RemoteException {
        }

        @Override  // androidx.room.IMultiInstanceInvalidationService
        public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback0, String s) throws RemoteException {
            return 0;
        }

        @Override  // androidx.room.IMultiInstanceInvalidationService
        public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback0, int v) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationService {
        static class Proxy implements IMultiInstanceInvalidationService {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // androidx.room.IMultiInstanceInvalidationService
            public void broadcastInvalidation(int v, String[] arr_s) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeInt(v);
                    parcel0.writeStringArray(arr_s);
                    this.mRemote.transact(3, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Proxy.DESCRIPTOR;
            }

            @Override  // androidx.room.IMultiInstanceInvalidationService
            public int registerCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback0, String s) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeStrongInterface(iMultiInstanceInvalidationCallback0);
                    parcel0.writeString(s);
                    this.mRemote.transact(1, parcel0, parcel1, 0);
                    parcel1.readException();
                    return parcel1.readInt();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // androidx.room.IMultiInstanceInvalidationService
            public void unregisterCallback(IMultiInstanceInvalidationCallback iMultiInstanceInvalidationCallback0, int v) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeStrongInterface(iMultiInstanceInvalidationCallback0);
                    parcel0.writeInt(v);
                    this.mRemote.transact(2, parcel0, parcel1, 0);
                    parcel1.readException();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        static final int TRANSACTION_broadcastInvalidation = 3;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_unregisterCallback = 2;

        public Stub() {
            this.attachInterface(this, Stub.DESCRIPTOR);
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static IMultiInstanceInvalidationService asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface(Stub.DESCRIPTOR);
            return iInterface0 != null && iInterface0 instanceof IMultiInstanceInvalidationService ? ((IMultiInstanceInvalidationService)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            String s = Stub.DESCRIPTOR;
            if(v >= 1 && v <= 0xFFFFFF) {
                parcel0.enforceInterface(s);
            }
            switch(v) {
                case 1: {
                    int v2 = this.registerCallback(androidx.room.IMultiInstanceInvalidationCallback.Stub.asInterface(parcel0.readStrongBinder()), parcel0.readString());
                    parcel1.writeNoException();
                    parcel1.writeInt(v2);
                    return true;
                }
                case 2: {
                    this.unregisterCallback(androidx.room.IMultiInstanceInvalidationCallback.Stub.asInterface(parcel0.readStrongBinder()), parcel0.readInt());
                    parcel1.writeNoException();
                    return true;
                }
                case 3: {
                    this.broadcastInvalidation(parcel0.readInt(), parcel0.createStringArray());
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
        IMultiInstanceInvalidationService.DESCRIPTOR = "androidx.room.IMultiInstanceInvalidationService";
    }

    void broadcastInvalidation(int arg1, String[] arg2) throws RemoteException;

    int registerCallback(IMultiInstanceInvalidationCallback arg1, String arg2) throws RemoteException;

    void unregisterCallback(IMultiInstanceInvalidationCallback arg1, int arg2) throws RemoteException;
}

