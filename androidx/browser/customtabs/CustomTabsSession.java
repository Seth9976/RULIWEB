package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.ICustomTabsService.Stub;
import android.support.customtabs.ICustomTabsService;
import android.widget.RemoteViews;
import java.util.List;

public final class CustomTabsSession {
    static class MockSession extends Stub {
        @Override  // android.support.customtabs.ICustomTabsService
        public Bundle extraCommand(String s, Bundle bundle0) throws RemoteException {
            return null;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean mayLaunchUrl(ICustomTabsCallback iCustomTabsCallback0, Uri uri0, Bundle bundle0, List list0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean newSession(ICustomTabsCallback iCustomTabsCallback0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean newSessionWithExtras(ICustomTabsCallback iCustomTabsCallback0, Bundle bundle0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public int postMessage(ICustomTabsCallback iCustomTabsCallback0, String s, Bundle bundle0) throws RemoteException {
            return 0;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean receiveFile(ICustomTabsCallback iCustomTabsCallback0, Uri uri0, int v, Bundle bundle0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean requestPostMessageChannel(ICustomTabsCallback iCustomTabsCallback0, Uri uri0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean requestPostMessageChannelWithExtras(ICustomTabsCallback iCustomTabsCallback0, Uri uri0, Bundle bundle0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean updateVisuals(ICustomTabsCallback iCustomTabsCallback0, Bundle bundle0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean validateRelationship(ICustomTabsCallback iCustomTabsCallback0, int v, Uri uri0, Bundle bundle0) throws RemoteException {
            return false;
        }

        @Override  // android.support.customtabs.ICustomTabsService
        public boolean warmup(long v) throws RemoteException {
            return false;
        }
    }

    public static class PendingSession {
        private final CustomTabsCallback mCallback;
        private final PendingIntent mId;

        PendingSession(CustomTabsCallback customTabsCallback0, PendingIntent pendingIntent0) {
            this.mCallback = customTabsCallback0;
            this.mId = pendingIntent0;
        }

        CustomTabsCallback getCallback() {
            return this.mCallback;
        }

        PendingIntent getId() {
            return this.mId;
        }
    }

    private static final String TAG = "CustomTabsSession";
    private final ICustomTabsCallback mCallback;
    private final ComponentName mComponentName;
    private final PendingIntent mId;
    private final Object mLock;
    private final ICustomTabsService mService;

    CustomTabsSession(ICustomTabsService iCustomTabsService0, ICustomTabsCallback iCustomTabsCallback0, ComponentName componentName0, PendingIntent pendingIntent0) {
        this.mLock = new Object();
        this.mService = iCustomTabsService0;
        this.mCallback = iCustomTabsCallback0;
        this.mComponentName = componentName0;
        this.mId = pendingIntent0;
    }

    private void addIdToBundle(Bundle bundle0) {
        PendingIntent pendingIntent0 = this.mId;
        if(pendingIntent0 != null) {
            bundle0.putParcelable("android.support.customtabs.extra.SESSION_ID", pendingIntent0);
        }
    }

    private Bundle createBundleWithId(Bundle bundle0) {
        Bundle bundle1 = new Bundle();
        if(bundle0 != null) {
            bundle1.putAll(bundle0);
        }
        this.addIdToBundle(bundle1);
        return bundle1;
    }

    public static CustomTabsSession createMockSessionForTesting(ComponentName componentName0) {
        return new CustomTabsSession(new MockSession(), new MockCallback(), componentName0, null);
    }

    IBinder getBinder() {
        return this.mCallback.asBinder();
    }

    ComponentName getComponentName() {
        return this.mComponentName;
    }

    PendingIntent getId() {
        return this.mId;
    }

    public boolean mayLaunchUrl(Uri uri0, Bundle bundle0, List list0) {
        Bundle bundle1 = this.createBundleWithId(bundle0);
        try {
            return this.mService.mayLaunchUrl(this.mCallback, uri0, bundle1, list0);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public int postMessage(String s, Bundle bundle0) {
        Bundle bundle1 = this.createBundleWithId(bundle0);
        synchronized(this.mLock) {
            try {
                return this.mService.postMessage(this.mCallback, s, bundle1);
            }
            catch(RemoteException unused_ex) {
                return -2;
            }
        }
    }

    public boolean receiveFile(Uri uri0, int v, Bundle bundle0) {
        Bundle bundle1 = this.createBundleWithId(bundle0);
        try {
            return this.mService.receiveFile(this.mCallback, uri0, v, bundle1);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public boolean requestPostMessageChannel(Uri uri0) {
        try {
            if(this.mId != null) {
                Bundle bundle0 = this.createBundleWithId(null);
                return this.mService.requestPostMessageChannelWithExtras(this.mCallback, uri0, bundle0);
            }
            return this.mService.requestPostMessageChannel(this.mCallback, uri0);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public boolean setActionButton(Bitmap bitmap0, String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("android.support.customtabs.customaction.ICON", bitmap0);
        bundle0.putString("android.support.customtabs.customaction.DESCRIPTION", s);
        Bundle bundle1 = new Bundle();
        bundle1.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle0);
        this.addIdToBundle(bundle0);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle1);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public boolean setSecondaryToolbarViews(RemoteViews remoteViews0, int[] arr_v, PendingIntent pendingIntent0) {
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", remoteViews0);
        bundle0.putIntArray("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", arr_v);
        bundle0.putParcelable("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", pendingIntent0);
        this.addIdToBundle(bundle0);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle0);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    @Deprecated
    public boolean setToolbarItem(int v, Bitmap bitmap0, String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putInt("android.support.customtabs.customaction.ID", v);
        bundle0.putParcelable("android.support.customtabs.customaction.ICON", bitmap0);
        bundle0.putString("android.support.customtabs.customaction.DESCRIPTION", s);
        Bundle bundle1 = new Bundle();
        bundle1.putBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle0);
        this.addIdToBundle(bundle1);
        try {
            return this.mService.updateVisuals(this.mCallback, bundle1);
        }
        catch(RemoteException unused_ex) {
            return false;
        }
    }

    public boolean validateRelationship(int v, Uri uri0, Bundle bundle0) {
        if(v >= 1 && v <= 2) {
            Bundle bundle1 = this.createBundleWithId(bundle0);
            try {
                return this.mService.validateRelationship(this.mCallback, v, uri0, bundle1);
            }
            catch(RemoteException unused_ex) {
            }
        }
        return false;
    }
}

