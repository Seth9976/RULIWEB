package android.support.customtabs.trusted;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITrustedWebActivityService extends IInterface {
    public static class Default implements ITrustedWebActivityService {
        @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
        public Bundle areNotificationsEnabled(Bundle bundle0) throws RemoteException {
            return null;
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
        public void cancelNotification(Bundle bundle0) throws RemoteException {
        }

        @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
        public Bundle extraCommand(String s, Bundle bundle0, IBinder iBinder0) throws RemoteException {
            return null;
        }

        @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
        public Bundle getActiveNotifications() throws RemoteException {
            return null;
        }

        @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
        public Bundle getSmallIconBitmap() throws RemoteException {
            return null;
        }

        @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
        public int getSmallIconId() throws RemoteException {
            return 0;
        }

        @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
        public Bundle notifyNotificationWithChannel(Bundle bundle0) throws RemoteException {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITrustedWebActivityService {
        static class Proxy implements ITrustedWebActivityService {
            private IBinder mRemote;
            public static ITrustedWebActivityService sDefaultImpl;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle areNotificationsEnabled(Bundle bundle0) throws RemoteException {
                Bundle bundle1;
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(6, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                        bundle1 = parcel1.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel1));
                    }
                    else {
                        bundle1 = Stub.getDefaultImpl().areNotificationsEnabled(bundle0);
                    }
                    return bundle1;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public void cancelNotification(Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(3, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                    }
                    else {
                        Stub.getDefaultImpl().cancelNotification(bundle0);
                    }
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle extraCommand(String s, Bundle bundle0, IBinder iBinder0) throws RemoteException {
                Bundle bundle1;
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
                    parcel0.writeString(s);
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    parcel0.writeStrongBinder(iBinder0);
                    if(this.mRemote.transact(9, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                        bundle1 = parcel1.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel1));
                    }
                    else {
                        bundle1 = Stub.getDefaultImpl().extraCommand(s, bundle0, iBinder0);
                    }
                    return bundle1;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle getActiveNotifications() throws RemoteException {
                Bundle bundle0;
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(this.mRemote.transact(5, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                        bundle0 = parcel1.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel1));
                    }
                    else {
                        bundle0 = Stub.getDefaultImpl().getActiveNotifications();
                    }
                    return bundle0;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.trusted.ITrustedWebActivityService";
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle getSmallIconBitmap() throws RemoteException {
                Bundle bundle0;
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(this.mRemote.transact(7, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                        bundle0 = parcel1.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel1));
                    }
                    else {
                        bundle0 = Stub.getDefaultImpl().getSmallIconBitmap();
                    }
                    return bundle0;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public int getSmallIconId() throws RemoteException {
                int v1;
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(this.mRemote.transact(4, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                        v1 = parcel1.readInt();
                    }
                    else {
                        v1 = Stub.getDefaultImpl().getSmallIconId();
                    }
                    return v1;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle notifyNotificationWithChannel(Bundle bundle0) throws RemoteException {
                Bundle bundle1;
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(2, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                        bundle1 = parcel1.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel1));
                    }
                    else {
                        bundle1 = Stub.getDefaultImpl().notifyNotificationWithChannel(bundle0);
                    }
                    return bundle1;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.trusted.ITrustedWebActivityService";
        static final int TRANSACTION_areNotificationsEnabled = 6;
        static final int TRANSACTION_cancelNotification = 3;
        static final int TRANSACTION_extraCommand = 9;
        static final int TRANSACTION_getActiveNotifications = 5;
        static final int TRANSACTION_getSmallIconBitmap = 7;
        static final int TRANSACTION_getSmallIconId = 4;
        static final int TRANSACTION_notifyNotificationWithChannel = 2;

        public Stub() {
            this.attachInterface(this, "android.support.customtabs.trusted.ITrustedWebActivityService");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static ITrustedWebActivityService asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
            return iInterface0 != null && iInterface0 instanceof ITrustedWebActivityService ? ((ITrustedWebActivityService)iInterface0) : new Proxy(iBinder0);
        }

        public static ITrustedWebActivityService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            Bundle bundle0 = null;
            switch(v) {
                case 2: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(parcel0.readInt() != 0) {
                        bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                    }
                    Bundle bundle1 = this.notifyNotificationWithChannel(bundle0);
                    parcel1.writeNoException();
                    if(bundle1 != null) {
                        parcel1.writeInt(1);
                        bundle1.writeToParcel(parcel1, 1);
                        return true;
                    }
                    parcel1.writeInt(0);
                    return true;
                }
                case 3: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(parcel0.readInt() != 0) {
                        bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                    }
                    this.cancelNotification(bundle0);
                    parcel1.writeNoException();
                    return true;
                }
                case 4: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
                    int v2 = this.getSmallIconId();
                    parcel1.writeNoException();
                    parcel1.writeInt(v2);
                    return true;
                }
                case 5: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
                    Bundle bundle2 = this.getActiveNotifications();
                    parcel1.writeNoException();
                    if(bundle2 != null) {
                        parcel1.writeInt(1);
                        bundle2.writeToParcel(parcel1, 1);
                        return true;
                    }
                    parcel1.writeInt(0);
                    return true;
                }
                case 6: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
                    if(parcel0.readInt() != 0) {
                        bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                    }
                    Bundle bundle3 = this.areNotificationsEnabled(bundle0);
                    parcel1.writeNoException();
                    if(bundle3 != null) {
                        parcel1.writeInt(1);
                        bundle3.writeToParcel(parcel1, 1);
                        return true;
                    }
                    parcel1.writeInt(0);
                    return true;
                }
                case 7: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
                    Bundle bundle4 = this.getSmallIconBitmap();
                    parcel1.writeNoException();
                    if(bundle4 != null) {
                        parcel1.writeInt(1);
                        bundle4.writeToParcel(parcel1, 1);
                        return true;
                    }
                    parcel1.writeInt(0);
                    return true;
                }
                case 9: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityService");
                    String s = parcel0.readString();
                    if(parcel0.readInt() != 0) {
                        bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                    }
                    Bundle bundle5 = this.extraCommand(s, bundle0, parcel0.readStrongBinder());
                    parcel1.writeNoException();
                    if(bundle5 != null) {
                        parcel1.writeInt(1);
                        bundle5.writeToParcel(parcel1, 1);
                        return true;
                    }
                    parcel1.writeInt(0);
                    return true;
                }
                case 0x5F4E5446: {
                    parcel1.writeString("android.support.customtabs.trusted.ITrustedWebActivityService");
                    return true;
                }
                default: {
                    return super.onTransact(v, parcel0, parcel1, v1);
                }
            }
        }

        public static boolean setDefaultImpl(ITrustedWebActivityService iTrustedWebActivityService0) {
            if(Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if(iTrustedWebActivityService0 != null) {
                Proxy.sDefaultImpl = iTrustedWebActivityService0;
                return true;
            }
            return false;
        }
    }

    Bundle areNotificationsEnabled(Bundle arg1) throws RemoteException;

    void cancelNotification(Bundle arg1) throws RemoteException;

    Bundle extraCommand(String arg1, Bundle arg2, IBinder arg3) throws RemoteException;

    Bundle getActiveNotifications() throws RemoteException;

    Bundle getSmallIconBitmap() throws RemoteException;

    int getSmallIconId() throws RemoteException;

    Bundle notifyNotificationWithChannel(Bundle arg1) throws RemoteException;
}

