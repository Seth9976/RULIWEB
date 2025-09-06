package androidx.browser.trusted;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.customtabs.trusted.ITrustedWebActivityService.Stub;
import androidx.core.app.NotificationManagerCompat;
import java.util.Locale;

public abstract class TrustedWebActivityService extends Service {
    public static final String ACTION_TRUSTED_WEB_ACTIVITY_SERVICE = "android.support.customtabs.trusted.TRUSTED_WEB_ACTIVITY_SERVICE";
    public static final String KEY_SMALL_ICON_BITMAP = "android.support.customtabs.trusted.SMALL_ICON_BITMAP";
    public static final String KEY_SUCCESS = "androidx.browser.trusted.SUCCESS";
    public static final String META_DATA_NAME_SMALL_ICON = "android.support.customtabs.trusted.SMALL_ICON";
    public static final int SMALL_ICON_NOT_SET = -1;
    private final Stub mBinder;
    private NotificationManager mNotificationManager;
    int mVerifiedUid;

    public TrustedWebActivityService() {
        this.mVerifiedUid = -1;
        this.mBinder = new Stub() {
            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle areNotificationsEnabled(Bundle bundle0) {
                this.checkCaller();
                NotificationsEnabledArgs trustedWebActivityServiceConnection$NotificationsEnabledArgs0 = NotificationsEnabledArgs.fromBundle(bundle0);
                return new ResultArgs(TrustedWebActivityService.this.onAreNotificationsEnabled(trustedWebActivityServiceConnection$NotificationsEnabledArgs0.channelName)).toBundle();
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public void cancelNotification(Bundle bundle0) {
                this.checkCaller();
                CancelNotificationArgs trustedWebActivityServiceConnection$CancelNotificationArgs0 = CancelNotificationArgs.fromBundle(bundle0);
                TrustedWebActivityService.this.onCancelNotification(trustedWebActivityServiceConnection$CancelNotificationArgs0.platformTag, trustedWebActivityServiceConnection$CancelNotificationArgs0.platformId);
            }

            private void checkCaller() {
                if(TrustedWebActivityService.this.mVerifiedUid == -1) {
                    String[] arr_s = TrustedWebActivityService.this.getPackageManager().getPackagesForUid(androidx.browser.trusted.TrustedWebActivityService.1.getCallingUid());
                    if(arr_s == null) {
                        arr_s = new String[0];
                    }
                    Token token0 = TrustedWebActivityService.this.getTokenStore().load();
                    PackageManager packageManager0 = TrustedWebActivityService.this.getPackageManager();
                    if(token0 != null) {
                        for(int v = 0; v < arr_s.length; ++v) {
                            if(token0.matches(arr_s[v], packageManager0)) {
                                TrustedWebActivityService.this.mVerifiedUid = androidx.browser.trusted.TrustedWebActivityService.1.getCallingUid();
                                break;
                            }
                        }
                    }
                }
                if(TrustedWebActivityService.this.mVerifiedUid != androidx.browser.trusted.TrustedWebActivityService.1.getCallingUid()) {
                    throw new SecurityException("Caller is not verified as Trusted Web Activity provider.");
                }
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle extraCommand(String s, Bundle bundle0, IBinder iBinder0) {
                this.checkCaller();
                TrustedWebActivityCallbackRemote.fromBinder(iBinder0);
                return null;
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle getActiveNotifications() {
                this.checkCaller();
                return new ActiveNotificationsArgs(TrustedWebActivityService.this.onGetActiveNotifications()).toBundle();
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle getSmallIconBitmap() {
                this.checkCaller();
                return TrustedWebActivityService.this.onGetSmallIconBitmap();
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public int getSmallIconId() {
                this.checkCaller();
                return TrustedWebActivityService.this.onGetSmallIconId();
            }

            @Override  // android.support.customtabs.trusted.ITrustedWebActivityService
            public Bundle notifyNotificationWithChannel(Bundle bundle0) {
                this.checkCaller();
                NotifyNotificationArgs trustedWebActivityServiceConnection$NotifyNotificationArgs0 = NotifyNotificationArgs.fromBundle(bundle0);
                return new ResultArgs(TrustedWebActivityService.this.onNotifyNotificationWithChannel(trustedWebActivityServiceConnection$NotifyNotificationArgs0.platformTag, trustedWebActivityServiceConnection$NotifyNotificationArgs0.platformId, trustedWebActivityServiceConnection$NotifyNotificationArgs0.notification, trustedWebActivityServiceConnection$NotifyNotificationArgs0.channelName)).toBundle();
            }
        };
    }

    private static String channelNameToId(String s) {
        return s.toLowerCase(Locale.ROOT).replace(' ', '_') + "_channel_id";
    }

    private void ensureOnCreateCalled() {
        if(this.mNotificationManager == null) {
            throw new IllegalStateException("TrustedWebActivityService has not been properly initialized. Did onCreate() call super.onCreate()?");
        }
    }

    public abstract TokenStore getTokenStore();

    public boolean onAreNotificationsEnabled(String s) {
        this.ensureOnCreateCalled();
        if(!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            return false;
        }
        return Build.VERSION.SDK_INT >= 26 ? NotificationApiHelperForO.isChannelEnabled(this.mNotificationManager, TrustedWebActivityService.channelNameToId(s)) : true;
    }

    @Override  // android.app.Service
    public final IBinder onBind(Intent intent0) {
        return this.mBinder;
    }

    public void onCancelNotification(String s, int v) {
        this.ensureOnCreateCalled();
        this.mNotificationManager.cancel(s, v);
    }

    @Override  // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mNotificationManager = (NotificationManager)this.getSystemService("notification");
    }

    public Bundle onExtraCommand(String s, Bundle bundle0, TrustedWebActivityCallbackRemote trustedWebActivityCallbackRemote0) [...] // Inlined contents

    public Parcelable[] onGetActiveNotifications() {
        this.ensureOnCreateCalled();
        if(Build.VERSION.SDK_INT < 23) {
            throw new IllegalStateException("onGetActiveNotifications cannot be called pre-M.");
        }
        return NotificationApiHelperForM.getActiveNotifications(this.mNotificationManager);
    }

    public Bundle onGetSmallIconBitmap() {
        int v = this.onGetSmallIconId();
        Bundle bundle0 = new Bundle();
        if(v == -1) {
            return bundle0;
        }
        bundle0.putParcelable("android.support.customtabs.trusted.SMALL_ICON_BITMAP", BitmapFactory.decodeResource(this.getResources(), v));
        return bundle0;
    }

    public int onGetSmallIconId() {
        try {
            ServiceInfo serviceInfo0 = this.getPackageManager().getServiceInfo(new ComponentName(this, this.getClass()), 0x80);
            return serviceInfo0.metaData == null ? -1 : serviceInfo0.metaData.getInt("android.support.customtabs.trusted.SMALL_ICON", -1);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return -1;
        }
    }

    public boolean onNotifyNotificationWithChannel(String s, int v, Notification notification0, String s1) {
        this.ensureOnCreateCalled();
        if(!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            return false;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            String s2 = TrustedWebActivityService.channelNameToId(s1);
            notification0 = NotificationApiHelperForO.copyNotificationOntoChannel(this, this.mNotificationManager, notification0, s2, s1);
            if(!NotificationApiHelperForO.isChannelEnabled(this.mNotificationManager, s2)) {
                return false;
            }
        }
        this.mNotificationManager.notify(s, v, notification0);
        return true;
    }

    @Override  // android.app.Service
    public final boolean onUnbind(Intent intent0) {
        this.mVerifiedUid = -1;
        return super.onUnbind(intent0);
    }
}

