package androidx.browser.trusted;

import android.app.Notification;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.customtabs.trusted.ITrustedWebActivityCallback.Stub;
import android.support.customtabs.trusted.ITrustedWebActivityCallback;
import android.support.customtabs.trusted.ITrustedWebActivityService;

public final class TrustedWebActivityServiceConnection {
    static class ActiveNotificationsArgs {
        public final Parcelable[] notifications;

        ActiveNotificationsArgs(Parcelable[] arr_parcelable) {
            this.notifications = arr_parcelable;
        }

        public static ActiveNotificationsArgs fromBundle(Bundle bundle0) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.ACTIVE_NOTIFICATIONS");
            return new ActiveNotificationsArgs(bundle0.getParcelableArray("android.support.customtabs.trusted.ACTIVE_NOTIFICATIONS"));
        }

        public Bundle toBundle() {
            Bundle bundle0 = new Bundle();
            bundle0.putParcelableArray("android.support.customtabs.trusted.ACTIVE_NOTIFICATIONS", this.notifications);
            return bundle0;
        }
    }

    static class CancelNotificationArgs {
        public final int platformId;
        public final String platformTag;

        CancelNotificationArgs(String s, int v) {
            this.platformTag = s;
            this.platformId = v;
        }

        public static CancelNotificationArgs fromBundle(Bundle bundle0) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.PLATFORM_TAG");
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.PLATFORM_ID");
            return new CancelNotificationArgs(bundle0.getString("android.support.customtabs.trusted.PLATFORM_TAG"), bundle0.getInt("android.support.customtabs.trusted.PLATFORM_ID"));
        }

        public Bundle toBundle() {
            Bundle bundle0 = new Bundle();
            bundle0.putString("android.support.customtabs.trusted.PLATFORM_TAG", this.platformTag);
            bundle0.putInt("android.support.customtabs.trusted.PLATFORM_ID", this.platformId);
            return bundle0;
        }
    }

    static class NotificationsEnabledArgs {
        public final String channelName;

        NotificationsEnabledArgs(String s) {
            this.channelName = s;
        }

        public static NotificationsEnabledArgs fromBundle(Bundle bundle0) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.CHANNEL_NAME");
            return new NotificationsEnabledArgs(bundle0.getString("android.support.customtabs.trusted.CHANNEL_NAME"));
        }

        public Bundle toBundle() {
            Bundle bundle0 = new Bundle();
            bundle0.putString("android.support.customtabs.trusted.CHANNEL_NAME", this.channelName);
            return bundle0;
        }
    }

    static class NotifyNotificationArgs {
        public final String channelName;
        public final Notification notification;
        public final int platformId;
        public final String platformTag;

        NotifyNotificationArgs(String s, int v, Notification notification0, String s1) {
            this.platformTag = s;
            this.platformId = v;
            this.notification = notification0;
            this.channelName = s1;
        }

        public static NotifyNotificationArgs fromBundle(Bundle bundle0) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.PLATFORM_TAG");
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.PLATFORM_ID");
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.NOTIFICATION");
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.CHANNEL_NAME");
            return new NotifyNotificationArgs(bundle0.getString("android.support.customtabs.trusted.PLATFORM_TAG"), bundle0.getInt("android.support.customtabs.trusted.PLATFORM_ID"), ((Notification)bundle0.getParcelable("android.support.customtabs.trusted.NOTIFICATION")), bundle0.getString("android.support.customtabs.trusted.CHANNEL_NAME"));
        }

        public Bundle toBundle() {
            Bundle bundle0 = new Bundle();
            bundle0.putString("android.support.customtabs.trusted.PLATFORM_TAG", this.platformTag);
            bundle0.putInt("android.support.customtabs.trusted.PLATFORM_ID", this.platformId);
            bundle0.putParcelable("android.support.customtabs.trusted.NOTIFICATION", this.notification);
            bundle0.putString("android.support.customtabs.trusted.CHANNEL_NAME", this.channelName);
            return bundle0;
        }
    }

    static class ResultArgs {
        public final boolean success;

        ResultArgs(boolean z) {
            this.success = z;
        }

        public static ResultArgs fromBundle(Bundle bundle0) {
            TrustedWebActivityServiceConnection.ensureBundleContains(bundle0, "android.support.customtabs.trusted.NOTIFICATION_SUCCESS");
            return new ResultArgs(bundle0.getBoolean("android.support.customtabs.trusted.NOTIFICATION_SUCCESS"));
        }

        public Bundle toBundle() {
            Bundle bundle0 = new Bundle();
            bundle0.putBoolean("android.support.customtabs.trusted.NOTIFICATION_SUCCESS", this.success);
            return bundle0;
        }
    }

    private static final String KEY_ACTIVE_NOTIFICATIONS = "android.support.customtabs.trusted.ACTIVE_NOTIFICATIONS";
    private static final String KEY_CHANNEL_NAME = "android.support.customtabs.trusted.CHANNEL_NAME";
    private static final String KEY_NOTIFICATION = "android.support.customtabs.trusted.NOTIFICATION";
    private static final String KEY_NOTIFICATION_SUCCESS = "android.support.customtabs.trusted.NOTIFICATION_SUCCESS";
    private static final String KEY_PLATFORM_ID = "android.support.customtabs.trusted.PLATFORM_ID";
    private static final String KEY_PLATFORM_TAG = "android.support.customtabs.trusted.PLATFORM_TAG";
    private final ComponentName mComponentName;
    private final ITrustedWebActivityService mService;

    TrustedWebActivityServiceConnection(ITrustedWebActivityService iTrustedWebActivityService0, ComponentName componentName0) {
        this.mService = iTrustedWebActivityService0;
        this.mComponentName = componentName0;
    }

    public boolean areNotificationsEnabled(String s) throws RemoteException {
        Bundle bundle0 = new NotificationsEnabledArgs(s).toBundle();
        return ResultArgs.fromBundle(this.mService.areNotificationsEnabled(bundle0)).success;
    }

    public void cancel(String s, int v) throws RemoteException {
        Bundle bundle0 = new CancelNotificationArgs(s, v).toBundle();
        this.mService.cancelNotification(bundle0);
    }

    static void ensureBundleContains(Bundle bundle0, String s) {
        if(!bundle0.containsKey(s)) {
            throw new IllegalArgumentException("Bundle must contain " + s);
        }
    }

    public Parcelable[] getActiveNotifications() throws RemoteException {
        return ActiveNotificationsArgs.fromBundle(this.mService.getActiveNotifications()).notifications;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public Bitmap getSmallIconBitmap() throws RemoteException {
        return (Bitmap)this.mService.getSmallIconBitmap().getParcelable("android.support.customtabs.trusted.SMALL_ICON_BITMAP");
    }

    public int getSmallIconId() throws RemoteException {
        return this.mService.getSmallIconId();
    }

    public boolean notify(String s, int v, Notification notification0, String s1) throws RemoteException {
        Bundle bundle0 = new NotifyNotificationArgs(s, v, notification0, s1).toBundle();
        return ResultArgs.fromBundle(this.mService.notifyNotificationWithChannel(bundle0)).success;
    }

    public Bundle sendExtraCommand(String s, Bundle bundle0, TrustedWebActivityCallback trustedWebActivityCallback0) throws RemoteException {
        ITrustedWebActivityCallback iTrustedWebActivityCallback0 = TrustedWebActivityServiceConnection.wrapCallback(trustedWebActivityCallback0);
        if(iTrustedWebActivityCallback0 == null) {
            return this.mService.extraCommand(s, bundle0, null);
        }
        IBinder iBinder0 = iTrustedWebActivityCallback0.asBinder();
        return this.mService.extraCommand(s, bundle0, iBinder0);
    }

    private static ITrustedWebActivityCallback wrapCallback(TrustedWebActivityCallback trustedWebActivityCallback0) {
        return trustedWebActivityCallback0 == null ? null : new Stub() {
            @Override  // android.support.customtabs.trusted.ITrustedWebActivityCallback
            public void onExtraCallback(String s, Bundle bundle0) throws RemoteException {
                trustedWebActivityCallback0.onExtraCallback(s, bundle0);
            }
        };
    }
}

