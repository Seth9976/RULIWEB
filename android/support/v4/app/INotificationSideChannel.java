package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.os.RemoteException;

public interface INotificationSideChannel extends IInterface {
    public static class Default implements INotificationSideChannel {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // android.support.v4.app.INotificationSideChannel
        public void cancel(String s, int v, String s1) throws RemoteException {
        }

        @Override  // android.support.v4.app.INotificationSideChannel
        public void cancelAll(String s) throws RemoteException {
        }

        @Override  // android.support.v4.app.INotificationSideChannel
        public void notify(String s, int v, String s1, Notification notification0) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements INotificationSideChannel {
        static class Proxy implements INotificationSideChannel {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // android.support.v4.app.INotificationSideChannel
            public void cancel(String s, int v, String s1) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    parcel0.writeInt(v);
                    parcel0.writeString(s1);
                    this.mRemote.transact(2, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // android.support.v4.app.INotificationSideChannel
            public void cancelAll(String s) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    this.mRemote.transact(3, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Proxy.DESCRIPTOR;
            }

            @Override  // android.support.v4.app.INotificationSideChannel
            public void notify(String s, int v, String s1, Notification notification0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeString(s);
                    parcel0.writeInt(v);
                    parcel0.writeString(s1);
                    _Parcel.writeTypedObject(parcel0, notification0, 0);
                    this.mRemote.transact(1, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }
        }

        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        public Stub() {
            this.attachInterface(this, Stub.DESCRIPTOR);
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static INotificationSideChannel asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface(Stub.DESCRIPTOR);
            return iInterface0 != null && iInterface0 instanceof INotificationSideChannel ? ((INotificationSideChannel)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            String s = Stub.DESCRIPTOR;
            if(v >= 1 && v <= 0xFFFFFF) {
                parcel0.enforceInterface(s);
            }
            switch(v) {
                case 1: {
                    this.notify(parcel0.readString(), parcel0.readInt(), parcel0.readString(), ((Notification)_Parcel.readTypedObject(parcel0, Notification.CREATOR)));
                    return true;
                }
                case 2: {
                    this.cancel(parcel0.readString(), parcel0.readInt(), parcel0.readString());
                    return true;
                }
                case 3: {
                    this.cancelAll(parcel0.readString());
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

    public static class _Parcel {
        private static Object readTypedObject(Parcel parcel0, Parcelable.Creator parcelable$Creator0) {
            return parcel0.readInt() == 0 ? null : parcelable$Creator0.createFromParcel(parcel0);
        }

        private static void writeTypedObject(Parcel parcel0, Parcelable parcelable0, int v) {
            if(parcelable0 != null) {
                parcel0.writeInt(1);
                parcelable0.writeToParcel(parcel0, v);
                return;
            }
            parcel0.writeInt(0);
        }
    }

    public static final String DESCRIPTOR;

    static {
        INotificationSideChannel.DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
    }

    void cancel(String arg1, int arg2, String arg3) throws RemoteException;

    void cancelAll(String arg1) throws RemoteException;

    void notify(String arg1, int arg2, String arg3, Notification arg4) throws RemoteException;
}

