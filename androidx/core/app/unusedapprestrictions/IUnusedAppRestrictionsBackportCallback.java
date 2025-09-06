package androidx.core.app.unusedapprestrictions;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IUnusedAppRestrictionsBackportCallback extends IInterface {
    public static class Default implements IUnusedAppRestrictionsBackportCallback {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback
        public void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z1) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IUnusedAppRestrictionsBackportCallback {
        static class Proxy implements IUnusedAppRestrictionsBackportCallback {
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

            @Override  // androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback
            public void onIsPermissionRevocationEnabledForAppResult(boolean z, boolean z1) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken(Proxy.DESCRIPTOR);
                    parcel0.writeInt(((int)z));
                    parcel0.writeInt(((int)z1));
                    this.mRemote.transact(1, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }
        }

        static final int TRANSACTION_onIsPermissionRevocationEnabledForAppResult = 1;

        public Stub() {
            this.attachInterface(this, Stub.DESCRIPTOR);
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static IUnusedAppRestrictionsBackportCallback asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface(Stub.DESCRIPTOR);
            return iInterface0 != null && iInterface0 instanceof IUnusedAppRestrictionsBackportCallback ? ((IUnusedAppRestrictionsBackportCallback)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            String s = Stub.DESCRIPTOR;
            if(v >= 1 && v <= 0xFFFFFF) {
                parcel0.enforceInterface(s);
            }
            boolean z = false;
            if(v == 0x5F4E5446) {
                parcel1.writeString(s);
                return true;
            }
            if(v != 1) {
                return super.onTransact(v, parcel0, parcel1, v1);
            }
            boolean z1 = parcel0.readInt() != 0;
            if(parcel0.readInt() != 0) {
                z = true;
            }
            this.onIsPermissionRevocationEnabledForAppResult(z1, z);
            return true;
        }
    }

    public static final String DESCRIPTOR;

    static {
        IUnusedAppRestrictionsBackportCallback.DESCRIPTOR = "androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportCallback";
    }

    void onIsPermissionRevocationEnabledForAppResult(boolean arg1, boolean arg2) throws RemoteException;
}

