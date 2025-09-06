package android.support.customtabs.trusted;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITrustedWebActivityCallback extends IInterface {
    public static class Default implements ITrustedWebActivityCallback {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // android.support.customtabs.trusted.ITrustedWebActivityCallback
        public void onExtraCallback(String s, Bundle bundle0) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements ITrustedWebActivityCallback {
        static class Proxy implements ITrustedWebActivityCallback {
            private IBinder mRemote;
            public static ITrustedWebActivityCallback sDefaultImpl;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.trusted.ITrustedWebActivityCallback";
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityCallback
            public void onExtraCallback(String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityCallback");
                    parcel0.writeString(s);
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(2, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                    }
                    else {
                        Stub.getDefaultImpl().onExtraCallback(s, bundle0);
                    }
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.trusted.ITrustedWebActivityCallback";
        static final int TRANSACTION_onExtraCallback = 2;

        public Stub() {
            this.attachInterface(this, "android.support.customtabs.trusted.ITrustedWebActivityCallback");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static ITrustedWebActivityCallback asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("android.support.customtabs.trusted.ITrustedWebActivityCallback");
            return iInterface0 != null && iInterface0 instanceof ITrustedWebActivityCallback ? ((ITrustedWebActivityCallback)iInterface0) : new Proxy(iBinder0);
        }

        public static ITrustedWebActivityCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            switch(v) {
                case 2: {
                    parcel0.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityCallback");
                    this.onExtraCallback(parcel0.readString(), (parcel0.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel0))));
                    parcel1.writeNoException();
                    return true;
                }
                case 0x5F4E5446: {
                    parcel1.writeString("android.support.customtabs.trusted.ITrustedWebActivityCallback");
                    return true;
                }
                default: {
                    return super.onTransact(v, parcel0, parcel1, v1);
                }
            }
        }

        public static boolean setDefaultImpl(ITrustedWebActivityCallback iTrustedWebActivityCallback0) {
            if(Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if(iTrustedWebActivityCallback0 != null) {
                Proxy.sDefaultImpl = iTrustedWebActivityCallback0;
                return true;
            }
            return false;
        }
    }

    void onExtraCallback(String arg1, Bundle arg2) throws RemoteException;
}

