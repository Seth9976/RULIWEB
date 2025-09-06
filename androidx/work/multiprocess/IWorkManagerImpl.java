package androidx.work.multiprocess;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWorkManagerImpl extends IInterface {
    public static class Default implements IWorkManagerImpl {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelAllWork(IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelAllWorkByTag(String tag, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelUniqueWork(String name, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void cancelWorkById(String id, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void enqueueContinuation(byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void enqueueWorkRequests(byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void queryWorkInfo(byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void setForegroundAsync(byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void setProgress(byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IWorkManagerImpl
        public void updateUniquePeriodicWorkRequest(String name, byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IWorkManagerImpl {
        static class Proxy implements IWorkManagerImpl {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelAllWork(IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(7, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelAllWorkByTag(String s, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(5, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelUniqueWork(String s, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(6, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void cancelWorkById(String s, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(4, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void enqueueContinuation(byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeByteArray(arr_b);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(3, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void enqueueWorkRequests(byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeByteArray(arr_b);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(1, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Proxy.DESCRIPTOR;
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void queryWorkInfo(byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeByteArray(arr_b);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(8, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void setForegroundAsync(byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeByteArray(arr_b);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(10, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void setProgress(byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeByteArray(arr_b);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(9, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IWorkManagerImpl
            public void updateUniquePeriodicWorkRequest(String s, byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    parcel0.writeByteArray(arr_b);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(2, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }
        }

        static final int TRANSACTION_cancelAllWork = 7;
        static final int TRANSACTION_cancelAllWorkByTag = 5;
        static final int TRANSACTION_cancelUniqueWork = 6;
        static final int TRANSACTION_cancelWorkById = 4;
        static final int TRANSACTION_enqueueContinuation = 3;
        static final int TRANSACTION_enqueueWorkRequests = 1;
        static final int TRANSACTION_queryWorkInfo = 8;
        static final int TRANSACTION_setForegroundAsync = 10;
        static final int TRANSACTION_setProgress = 9;
        static final int TRANSACTION_updateUniquePeriodicWorkRequest = 2;

        public Stub() {
            this.attachInterface(this, Stub.DESCRIPTOR);
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static IWorkManagerImpl asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface(Stub.DESCRIPTOR);
            return iInterface0 != null && iInterface0 instanceof IWorkManagerImpl ? ((IWorkManagerImpl)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            String s = Stub.DESCRIPTOR;
            if(v >= 1 && v <= 0xFFFFFF) {
                parcel0.enforceInterface(s);
            }
            if(v == 0x5F4E5446) {
                parcel1.writeString(s);
                return true;
            }
            switch(v) {
                case 1: {
                    this.enqueueWorkRequests(parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 2: {
                    this.updateUniquePeriodicWorkRequest(parcel0.readString(), parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 3: {
                    this.enqueueContinuation(parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 4: {
                    this.cancelWorkById(parcel0.readString(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 5: {
                    this.cancelAllWorkByTag(parcel0.readString(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 6: {
                    this.cancelUniqueWork(parcel0.readString(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 7: {
                    this.cancelAllWork(androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 8: {
                    this.queryWorkInfo(parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 9: {
                    this.setProgress(parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 10: {
                    this.setForegroundAsync(parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
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
        IWorkManagerImpl.DESCRIPTOR = "androidx.work.multiprocess.IWorkManagerImpl";
    }

    void cancelAllWork(IWorkManagerImplCallback arg1) throws RemoteException;

    void cancelAllWorkByTag(String arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void cancelUniqueWork(String arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void cancelWorkById(String arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void enqueueContinuation(byte[] arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void enqueueWorkRequests(byte[] arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void queryWorkInfo(byte[] arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void setForegroundAsync(byte[] arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void setProgress(byte[] arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void updateUniquePeriodicWorkRequest(String arg1, byte[] arg2, IWorkManagerImplCallback arg3) throws RemoteException;
}

