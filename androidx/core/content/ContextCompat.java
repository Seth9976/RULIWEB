package androidx.core.content;

import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import androidx.annotation.ReplaceWith;
import androidx.core.app.LocaleManagerCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.ExecutorCompat;
import androidx.core.os.LocaleListCompat;
import androidx.core.util.ObjectsCompat;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.concurrent.Executor;

public class ContextCompat {
    static class Api21Impl {
        static File getCodeCacheDir(Context context0) {
            return context0.getCodeCacheDir();
        }

        static Drawable getDrawable(Context context0, int v) {
            return context0.getDrawable(v);
        }

        static File getNoBackupFilesDir(Context context0) {
            return context0.getNoBackupFilesDir();
        }
    }

    static class Api23Impl {
        static int getColor(Context context0, int v) {
            return context0.getColor(v);
        }

        static Object getSystemService(Context context0, Class class0) {
            return context0.getSystemService(class0);
        }

        static String getSystemServiceName(Context context0, Class class0) {
            return context0.getSystemServiceName(class0);
        }
    }

    static class Api24Impl {
        static Context createDeviceProtectedStorageContext(Context context0) {
            return context0.createDeviceProtectedStorageContext();
        }

        static File getDataDir(Context context0) {
            return context0.getDataDir();
        }

        static boolean isDeviceProtectedStorage(Context context0) {
            return context0.isDeviceProtectedStorage();
        }
    }

    static class Api26Impl {
        static Intent registerReceiver(Context context0, BroadcastReceiver broadcastReceiver0, IntentFilter intentFilter0, String s, Handler handler0, int v) {
            return (v & 4) == 0 || s != null ? context0.registerReceiver(broadcastReceiver0, intentFilter0, s, handler0, v & 1) : context0.registerReceiver(broadcastReceiver0, intentFilter0, ContextCompat.obtainAndCheckReceiverPermission(context0), handler0);
        }

        static ComponentName startForegroundService(Context context0, Intent intent0) {
            return context0.startForegroundService(intent0);
        }
    }

    static class Api28Impl {
        static Executor getMainExecutor(Context context0) {
            return context0.getMainExecutor();
        }
    }

    static class Api30Impl {
        static Context createAttributionContext(Context context0, String s) {
            return context0.createAttributionContext(s);
        }

        static String getAttributionTag(Context context0) {
            return context0.getAttributionTag();
        }

        static Display getDisplayOrDefault(Context context0) {
            try {
                return context0.getDisplay();
            }
            catch(UnsupportedOperationException unused_ex) {
                Log.w("ContextCompat", "The context:" + context0 + " is not associated with any display. Return a fallback display instead.");
                return ((DisplayManager)context0.getSystemService(DisplayManager.class)).getDisplay(0);
            }
        }
    }

    static class Api33Impl {
        static Intent registerReceiver(Context context0, BroadcastReceiver broadcastReceiver0, IntentFilter intentFilter0, String s, Handler handler0, int v) {
            return context0.registerReceiver(broadcastReceiver0, intentFilter0, s, handler0, v);
        }
    }

    static final class LegacyServiceMapHolder {
        static final HashMap SERVICES;

        static {
            HashMap hashMap0 = new HashMap();
            LegacyServiceMapHolder.SERVICES = hashMap0;
            if(Build.VERSION.SDK_INT >= 22) {
                hashMap0.put(SubscriptionManager.class, "telephony_subscription_service");
                hashMap0.put(UsageStatsManager.class, "usagestats");
            }
            hashMap0.put(AppWidgetManager.class, "appwidget");
            hashMap0.put(BatteryManager.class, "batterymanager");
            hashMap0.put(CameraManager.class, "camera");
            hashMap0.put(JobScheduler.class, "jobscheduler");
            hashMap0.put(LauncherApps.class, "launcherapps");
            hashMap0.put(MediaProjectionManager.class, "media_projection");
            hashMap0.put(MediaSessionManager.class, "media_session");
            hashMap0.put(RestrictionsManager.class, "restrictions");
            hashMap0.put(TelecomManager.class, "telecom");
            hashMap0.put(TvInputManager.class, "tv_input");
            hashMap0.put(AppOpsManager.class, "appops");
            hashMap0.put(CaptioningManager.class, "captioning");
            hashMap0.put(ConsumerIrManager.class, "consumer_ir");
            hashMap0.put(PrintManager.class, "print");
            hashMap0.put(BluetoothManager.class, "bluetooth");
            hashMap0.put(DisplayManager.class, "display");
            hashMap0.put(UserManager.class, "user");
            hashMap0.put(InputManager.class, "input");
            hashMap0.put(MediaRouter.class, "media_router");
            hashMap0.put(NsdManager.class, "servicediscovery");
            hashMap0.put(AccessibilityManager.class, "accessibility");
            hashMap0.put(AccountManager.class, "account");
            hashMap0.put(ActivityManager.class, "activity");
            hashMap0.put(AlarmManager.class, "alarm");
            hashMap0.put(AudioManager.class, "audio");
            hashMap0.put(ClipboardManager.class, "clipboard");
            hashMap0.put(ConnectivityManager.class, "connectivity");
            hashMap0.put(DevicePolicyManager.class, "device_policy");
            hashMap0.put(DownloadManager.class, "download");
            hashMap0.put(DropBoxManager.class, "dropbox");
            hashMap0.put(InputMethodManager.class, "input_method");
            hashMap0.put(KeyguardManager.class, "keyguard");
            hashMap0.put(LayoutInflater.class, "layout_inflater");
            hashMap0.put(LocationManager.class, "location");
            hashMap0.put(NfcManager.class, "nfc");
            hashMap0.put(NotificationManager.class, "notification");
            hashMap0.put(PowerManager.class, "power");
            hashMap0.put(SearchManager.class, "search");
            hashMap0.put(SensorManager.class, "sensor");
            hashMap0.put(StorageManager.class, "storage");
            hashMap0.put(TelephonyManager.class, "phone");
            hashMap0.put(TextServicesManager.class, "textservices");
            hashMap0.put(UiModeManager.class, "uimode");
            hashMap0.put(UsbManager.class, "usb");
            hashMap0.put(Vibrator.class, "vibrator");
            hashMap0.put(WallpaperManager.class, "wallpaper");
            hashMap0.put(WifiP2pManager.class, "wifip2p");
            hashMap0.put(WifiManager.class, "wifi");
            hashMap0.put(WindowManager.class, "window");
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RegisterReceiverFlags {
    }

    private static final String DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION_SUFFIX = ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
    public static final int RECEIVER_EXPORTED = 2;
    public static final int RECEIVER_NOT_EXPORTED = 4;
    public static final int RECEIVER_VISIBLE_TO_INSTANT_APPS = 1;
    private static final String TAG = "ContextCompat";
    private static final Object sSync;

    static {
        ContextCompat.sSync = new Object();
    }

    public static int checkSelfPermission(Context context0, String s) {
        ObjectsCompat.requireNonNull(s, "permission must be non-null");
        if(Build.VERSION.SDK_INT < 33 && TextUtils.equals("android.permission.POST_NOTIFICATIONS", s)) {
            return NotificationManagerCompat.from(context0).areNotificationsEnabled() ? 0 : -1;
        }
        return context0.checkPermission(s, Process.myPid(), Process.myUid());
    }

    public static Context createAttributionContext(Context context0, String s) {
        return Build.VERSION.SDK_INT < 30 ? context0 : Api30Impl.createAttributionContext(context0, s);
    }

    public static Context createDeviceProtectedStorageContext(Context context0) {
        return Build.VERSION.SDK_INT < 24 ? null : Api24Impl.createDeviceProtectedStorageContext(context0);
    }

    private static File createFilesDir(File file0) {
        synchronized(ContextCompat.sSync) {
            if(!file0.exists()) {
                if(file0.mkdirs()) {
                    return file0;
                }
                Log.w("ContextCompat", "Unable to create files subdir " + file0.getPath());
            }
            return file0;
        }
    }

    public static String getAttributionTag(Context context0) {
        return Build.VERSION.SDK_INT < 30 ? null : Api30Impl.getAttributionTag(context0);
    }

    public static File getCodeCacheDir(Context context0) {
        return Api21Impl.getCodeCacheDir(context0);
    }

    public static int getColor(Context context0, int v) {
        return Build.VERSION.SDK_INT < 23 ? context0.getResources().getColor(v) : Api23Impl.getColor(context0, v);
    }

    public static ColorStateList getColorStateList(Context context0, int v) {
        return ResourcesCompat.getColorStateList(context0.getResources(), v, context0.getTheme());
    }

    public static Context getContextForLanguage(Context context0) {
        LocaleListCompat localeListCompat0 = LocaleManagerCompat.getApplicationLocales(context0);
        if(Build.VERSION.SDK_INT <= 0x20 && !localeListCompat0.isEmpty()) {
            Configuration configuration0 = new Configuration(context0.getResources().getConfiguration());
            ConfigurationCompat.setLocales(configuration0, localeListCompat0);
            return context0.createConfigurationContext(configuration0);
        }
        return context0;
    }

    public static File getDataDir(Context context0) {
        if(Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.getDataDir(context0);
        }
        String s = context0.getApplicationInfo().dataDir;
        return s == null ? null : new File(s);
    }

    public static Display getDisplayOrDefault(Context context0) {
        return Build.VERSION.SDK_INT < 30 ? ((WindowManager)context0.getSystemService("window")).getDefaultDisplay() : Api30Impl.getDisplayOrDefault(context0);
    }

    public static Drawable getDrawable(Context context0, int v) {
        return Api21Impl.getDrawable(context0, v);
    }

    @ReplaceWith(expression = "context.getExternalCacheDirs()")
    @Deprecated
    public static File[] getExternalCacheDirs(Context context0) {
        return context0.getExternalCacheDirs();
    }

    @ReplaceWith(expression = "context.getExternalFilesDirs(type)")
    @Deprecated
    public static File[] getExternalFilesDirs(Context context0, String s) {
        return context0.getExternalFilesDirs(s);
    }

    public static Executor getMainExecutor(Context context0) {
        return Build.VERSION.SDK_INT < 28 ? ExecutorCompat.create(new Handler(context0.getMainLooper())) : Api28Impl.getMainExecutor(context0);
    }

    public static File getNoBackupFilesDir(Context context0) {
        return Api21Impl.getNoBackupFilesDir(context0);
    }

    @ReplaceWith(expression = "context.getObbDirs()")
    @Deprecated
    public static File[] getObbDirs(Context context0) {
        return context0.getObbDirs();
    }

    public static String getString(Context context0, int v) {
        return ContextCompat.getContextForLanguage(context0).getString(v);
    }

    public static Object getSystemService(Context context0, Class class0) {
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getSystemService(context0, class0);
        }
        String s = ContextCompat.getSystemServiceName(context0, class0);
        return s == null ? null : context0.getSystemService(s);
    }

    public static String getSystemServiceName(Context context0, Class class0) {
        return Build.VERSION.SDK_INT < 23 ? ((String)LegacyServiceMapHolder.SERVICES.get(class0)) : Api23Impl.getSystemServiceName(context0, class0);
    }

    public static boolean isDeviceProtectedStorage(Context context0) {
        return Build.VERSION.SDK_INT < 24 ? false : Api24Impl.isDeviceProtectedStorage(context0);
    }

    static String obtainAndCheckReceiverPermission(Context context0) {
        String s = "com.ruliweb.www.ruliapp.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        if(PermissionChecker.checkSelfPermission(context0, "com.ruliweb.www.ruliapp.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION") != 0) {
            if(Build.VERSION.SDK_INT >= 29) {
                s = context0.getOpPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
                if(PermissionChecker.checkSelfPermission(context0, s) == 0) {
                    return s;
                }
            }
            throw new RuntimeException("Permission " + s + " is required by your application to receive broadcasts, please add it to your manifest");
        }
        return "com.ruliweb.www.ruliapp.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
    }

    public static Intent registerReceiver(Context context0, BroadcastReceiver broadcastReceiver0, IntentFilter intentFilter0, int v) {
        return ContextCompat.registerReceiver(context0, broadcastReceiver0, intentFilter0, null, null, v);
    }

    public static Intent registerReceiver(Context context0, BroadcastReceiver broadcastReceiver0, IntentFilter intentFilter0, String s, Handler handler0, int v) {
        if((v & 1) != 0 && (v & 4) != 0) {
            throw new IllegalArgumentException("Cannot specify both RECEIVER_VISIBLE_TO_INSTANT_APPS and RECEIVER_NOT_EXPORTED");
        }
        if((v & 1) != 0) {
            v |= 2;
        }
        if((v & 2) == 0 && (v & 4) == 0) {
            throw new IllegalArgumentException("One of either RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED is required");
        }
        if((v & 2) != 0 && (v & 4) != 0) {
            throw new IllegalArgumentException("Cannot specify both RECEIVER_EXPORTED and RECEIVER_NOT_EXPORTED");
        }
        if(Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.registerReceiver(context0, broadcastReceiver0, intentFilter0, s, handler0, v);
        }
        if(Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.registerReceiver(context0, broadcastReceiver0, intentFilter0, s, handler0, v);
        }
        return (v & 4) == 0 || s != null ? context0.registerReceiver(broadcastReceiver0, intentFilter0, s, handler0) : context0.registerReceiver(broadcastReceiver0, intentFilter0, ContextCompat.obtainAndCheckReceiverPermission(context0), handler0);
    }

    public static boolean startActivities(Context context0, Intent[] arr_intent) {
        return ContextCompat.startActivities(context0, arr_intent, null);
    }

    public static boolean startActivities(Context context0, Intent[] arr_intent, Bundle bundle0) {
        context0.startActivities(arr_intent, bundle0);
        return true;
    }

    @ReplaceWith(expression = "context.startActivity(intent, options)")
    @Deprecated
    public static void startActivity(Context context0, Intent intent0, Bundle bundle0) {
        context0.startActivity(intent0, bundle0);
    }

    public static void startForegroundService(Context context0, Intent intent0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.startForegroundService(context0, intent0);
            return;
        }
        context0.startService(intent0);
    }
}

