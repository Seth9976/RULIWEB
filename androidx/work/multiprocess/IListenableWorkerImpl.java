package androidx.work.multiprocess;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IListenableWorkerImpl extends IInterface {
    public static class Default implements IListenableWorkerImpl {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // androidx.work.multiprocess.IListenableWorkerImpl
        public void interrupt(byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }

        @Override  // androidx.work.multiprocess.IListenableWorkerImpl
        public void startWork(byte[] request, IWorkManagerImplCallback callback) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IListenableWorkerImpl {
        static class Proxy implements IListenableWorkerImpl {
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

            @Override  // androidx.work.multiprocess.IListenableWorkerImpl
            public void interrupt(byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeByteArray(arr_b);
                    parcel0.writeStrongInterface(iWorkManagerImplCallback0);
                    this.mRemote.transact(2, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // androidx.work.multiprocess.IListenableWorkerImpl
            public void startWork(byte[] arr_b, IWorkManagerImplCallback iWorkManagerImplCallback0) throws RemoteException {
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
        }

        static final int TRANSACTION_interrupt = 2;
        static final int TRANSACTION_startWork = 1;

        public Stub() {
            this.attachInterface(this, Stub.DESCRIPTOR);
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static IListenableWorkerImpl asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface(Stub.DESCRIPTOR);
            return iInterface0 != null && iInterface0 instanceof IListenableWorkerImpl ? ((IListenableWorkerImpl)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            String s = Stub.DESCRIPTOR;
            if(v >= 1 && v <= 0xFFFFFF) {
                parcel0.enforceInterface(s);
            }
            switch(v) {
                case 1: {
                    this.startWork(parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
                    return true;
                }
                case 2: {
                    this.interrupt(parcel0.createByteArray(), androidx.work.multiprocess.IWorkManagerImplCallback.Stub.asInterface(parcel0.readStrongBinder()));
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
        IListenableWorkerImpl.DESCRIPTOR = "androidx.work.multiprocess.IListenableWorkerImpl";
    }

    void interrupt(byte[] arg1, IWorkManagerImplCallback arg2) throws RemoteException;

    void startWork(byte[] arg1, IWorkManagerImplCallback arg2) throws RemoteException;
}

