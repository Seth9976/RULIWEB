package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICustomTabsCallback extends IInterface {
    public static class Default implements ICustomTabsCallback {
        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void extraCallback(String s, Bundle bundle0) throws RemoteException {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public Bundle extraCallbackWithResult(String s, Bundle bundle0) throws RemoteException {
            return null;
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onMessageChannelReady(Bundle bundle0) throws RemoteException {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onNavigationEvent(int v, Bundle bundle0) throws RemoteException {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onPostMessage(String s, Bundle bundle0) throws RemoteException {
        }

        @Override  // android.support.customtabs.ICustomTabsCallback
        public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements ICustomTabsCallback {
        static class Proxy implements ICustomTabsCallback {
            private IBinder mRemote;
            public static ICustomTabsCallback sDefaultImpl;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void extraCallback(String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeString(s);
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
                        Stub.getDefaultImpl().extraCallback(s, bundle0);
                    }
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public Bundle extraCallbackWithResult(String s, Bundle bundle0) throws RemoteException {
                Bundle bundle1;
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeString(s);
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(7, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                        bundle1 = parcel1.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel1));
                    }
                    else {
                        bundle1 = Stub.getDefaultImpl().extraCallbackWithResult(s, bundle0);
                    }
                    return bundle1;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.ICustomTabsCallback";
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onMessageChannelReady(Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(4, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                    }
                    else {
                        Stub.getDefaultImpl().onMessageChannelReady(bundle0);
                    }
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onNavigationEvent(int v, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeInt(v);
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
                        Stub.getDefaultImpl().onNavigationEvent(v, bundle0);
                    }
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onPostMessage(String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeString(s);
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(5, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                    }
                    else {
                        Stub.getDefaultImpl().onPostMessage(s, bundle0);
                    }
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeInt(v);
                    if(uri0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        uri0.writeToParcel(parcel0, 0);
                    }
                    parcel0.writeInt(((int)z));
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    if(this.mRemote.transact(6, parcel0, parcel1, 0) || Stub.getDefaultImpl() == null) {
                        parcel1.readException();
                    }
                    else {
                        Stub.getDefaultImpl().onRelationshipValidationResult(v, uri0, z, bundle0);
                    }
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsCallback";
        static final int TRANSACTION_extraCallback = 3;
        static final int TRANSACTION_extraCallbackWithResult = 7;
        static final int TRANSACTION_onMessageChannelReady = 4;
        static final int TRANSACTION_onNavigationEvent = 2;
        static final int TRANSACTION_onPostMessage = 5;
        static final int TRANSACTION_onRelationshipValidationResult = 6;

        public Stub() {
            this.attachInterface(this, "android.support.customtabs.ICustomTabsCallback");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static ICustomTabsCallback asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("android.support.customtabs.ICustomTabsCallback");
            return iInterface0 != null && iInterface0 instanceof ICustomTabsCallback ? ((ICustomTabsCallback)iInterface0) : new Proxy(iBinder0);
        }

        public static ICustomTabsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v != 0x5F4E5446) {
                boolean z = false;
                Bundle bundle0 = null;
                switch(v) {
                    case 2: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        int v2 = parcel0.readInt();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onNavigationEvent(v2, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 3: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        String s = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.extraCallback(s, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 4: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onMessageChannelReady(bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 5: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        String s1 = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onPostMessage(s1, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 6: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        int v3 = parcel0.readInt();
                        Uri uri0 = parcel0.readInt() == 0 ? null : ((Uri)Uri.CREATOR.createFromParcel(parcel0));
                        if(parcel0.readInt() != 0) {
                            z = true;
                        }
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onRelationshipValidationResult(v3, uri0, z, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 7: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        String s2 = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        Bundle bundle1 = this.extraCallbackWithResult(s2, bundle0);
                        parcel1.writeNoException();
                        if(bundle1 != null) {
                            parcel1.writeInt(1);
                            bundle1.writeToParcel(parcel1, 1);
                            return true;
                        }
                        parcel1.writeInt(0);
                        return true;
                    }
                    default: {
                        return super.onTransact(v, parcel0, parcel1, v1);
                    }
                }
            }
            parcel1.writeString("android.support.customtabs.ICustomTabsCallback");
            return true;
        }

        public static boolean setDefaultImpl(ICustomTabsCallback iCustomTabsCallback0) {
            if(Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if(iCustomTabsCallback0 != null) {
                Proxy.sDefaultImpl = iCustomTabsCallback0;
                return true;
            }
            return false;
        }
    }

    void extraCallback(String arg1, Bundle arg2) throws RemoteException;

    Bundle extraCallbackWithResult(String arg1, Bundle arg2) throws RemoteException;

    void onMessageChannelReady(Bundle arg1) throws RemoteException;

    void onNavigationEvent(int arg1, Bundle arg2) throws RemoteException;

    void onPostMessage(String arg1, Bundle arg2) throws RemoteException;

    void onRelationshipValidationResult(int arg1, Uri arg2, boolean arg3, Bundle arg4) throws RemoteException;
}

