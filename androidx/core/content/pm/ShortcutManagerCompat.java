package androidx.core.content.pm;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorKt..ExternalSyntheticApiModelOutline0;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.util.Preconditions;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ShortcutManagerCompat {
    static class Api25Impl {
        static String getShortcutInfoWithLowestRank(List list0) {
            int v = -1;
            String s = null;
            for(Object object0: list0) {
                ShortcutInfo shortcutInfo0 = (ShortcutInfo)object0;
                if(shortcutInfo0.getRank() > v) {
                    s = shortcutInfo0.getId();
                    v = shortcutInfo0.getRank();
                }
            }
            return s;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShortcutMatchFlags {
    }

    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private static final int DEFAULT_MAX_ICON_DIMENSION_DP = 0x60;
    private static final int DEFAULT_MAX_ICON_DIMENSION_LOWRAM_DP = 0x30;
    public static final String EXTRA_SHORTCUT_ID = "android.intent.extra.shortcut.ID";
    public static final int FLAG_MATCH_CACHED = 8;
    public static final int FLAG_MATCH_DYNAMIC = 2;
    public static final int FLAG_MATCH_MANIFEST = 1;
    public static final int FLAG_MATCH_PINNED = 4;
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
    private static final String SHORTCUT_LISTENER_INTENT_FILTER_ACTION = "androidx.core.content.pm.SHORTCUT_LISTENER";
    private static final String SHORTCUT_LISTENER_META_DATA_KEY = "androidx.core.content.pm.shortcut_listener_impl";
    private static volatile List sShortcutInfoChangeListeners;
    private static volatile ShortcutInfoCompatSaver sShortcutInfoCompatSaver;

    static {
    }

    public static boolean addDynamicShortcuts(Context context0, List list0) {
        List list1 = ShortcutManagerCompat.removeShortcutsExcludedFromSurface(list0, 1);
        if(Build.VERSION.SDK_INT <= 29) {
            ShortcutManagerCompat.convertUriIconsToBitmapIcons(context0, list1);
        }
        if(Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: list1) {
                arrayList0.add(((ShortcutInfoCompat)object0).toShortcutInfo());
            }
            if(!ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).addDynamicShortcuts(arrayList0)) {
                return false;
            }
        }
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).addShortcuts(list1);
        Iterator iterator1 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator1.hasNext()) {
            iterator1.next();
        }
        return true;
    }

    static boolean convertUriIconToBitmapIcon(Context context0, ShortcutInfoCompat shortcutInfoCompat0) {
        if(shortcutInfoCompat0.mIcon == null) {
            return false;
        }
        int v = shortcutInfoCompat0.mIcon.mType;
        if(v != 4 && v != 6) {
            return true;
        }
        InputStream inputStream0 = shortcutInfoCompat0.mIcon.getUriInputStream(context0);
        if(inputStream0 == null) {
            return false;
        }
        Bitmap bitmap0 = BitmapFactory.decodeStream(inputStream0);
        if(bitmap0 == null) {
            return false;
        }
        shortcutInfoCompat0.mIcon = v == 6 ? IconCompat.createWithAdaptiveBitmap(bitmap0) : IconCompat.createWithBitmap(bitmap0);
        return true;
    }

    static void convertUriIconsToBitmapIcons(Context context0, List list0) {
        for(Object object0: new ArrayList(list0)) {
            ShortcutInfoCompat shortcutInfoCompat0 = (ShortcutInfoCompat)object0;
            if(!ShortcutManagerCompat.convertUriIconToBitmapIcon(context0, shortcutInfoCompat0)) {
                list0.remove(shortcutInfoCompat0);
            }
        }
    }

    public static Intent createShortcutResultIntent(Context context0, ShortcutInfoCompat shortcutInfoCompat0) {
        Intent intent0 = Build.VERSION.SDK_INT < 26 ? null : ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcutInfoCompat0.toShortcutInfo());
        if(intent0 == null) {
            intent0 = new Intent();
        }
        return shortcutInfoCompat0.addToIntent(intent0);
    }

    public static void disableShortcuts(Context context0, List list0, CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 25) {
            ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).disableShortcuts(list0, charSequence0);
        }
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).removeShortcuts(list0);
        Iterator iterator0 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    public static void enableShortcuts(Context context0, List list0) {
        List list1 = ShortcutManagerCompat.removeShortcutsExcludedFromSurface(list0, 1);
        if(Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList0 = new ArrayList(list0.size());
            for(Object object0: list1) {
                arrayList0.add(((ShortcutInfoCompat)object0).mId);
            }
            ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).enableShortcuts(arrayList0);
        }
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).addShortcuts(list1);
        Iterator iterator1 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator1.hasNext()) {
            iterator1.next();
        }
    }

    public static List getDynamicShortcuts(Context context0) {
        if(Build.VERSION.SDK_INT >= 25) {
            List list0 = ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).getDynamicShortcuts();
            List list1 = new ArrayList(list0.size());
            for(Object object0: list0) {
                list1.add(new Builder(context0, ((ShortcutInfo)object0)).build());
            }
            return list1;
        }
        try {
            return ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).getShortcuts();
        }
        catch(Exception unused_ex) {
            return new ArrayList();
        }
    }

    private static int getIconDimensionInternal(Context context0, boolean z) {
        ActivityManager activityManager0 = (ActivityManager)context0.getSystemService("activity");
        int v = Math.max(1, (activityManager0 == null || activityManager0.isLowRamDevice() ? 0x30 : 0x60));
        DisplayMetrics displayMetrics0 = context0.getResources().getDisplayMetrics();
        return z ? ((int)(((float)v) * (displayMetrics0.xdpi / 160.0f))) : ((int)(((float)v) * (displayMetrics0.ydpi / 160.0f)));
    }

    public static int getIconMaxHeight(Context context0) {
        Preconditions.checkNotNull(context0);
        return Build.VERSION.SDK_INT < 25 ? ShortcutManagerCompat.getIconDimensionInternal(context0, false) : ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).getIconMaxHeight();
    }

    public static int getIconMaxWidth(Context context0) {
        Preconditions.checkNotNull(context0);
        return Build.VERSION.SDK_INT < 25 ? ShortcutManagerCompat.getIconDimensionInternal(context0, true) : ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).getIconMaxWidth();
    }

    public static int getMaxShortcutCountPerActivity(Context context0) {
        Preconditions.checkNotNull(context0);
        return Build.VERSION.SDK_INT < 25 ? 5 : ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).getMaxShortcutCountPerActivity();
    }

    static List getShortcutInfoChangeListeners() {
        return ShortcutManagerCompat.sShortcutInfoChangeListeners;
    }

    private static String getShortcutInfoCompatWithLowestRank(List list0) {
        int v = -1;
        String s = null;
        for(Object object0: list0) {
            ShortcutInfoCompat shortcutInfoCompat0 = (ShortcutInfoCompat)object0;
            if(shortcutInfoCompat0.getRank() > v) {
                s = shortcutInfoCompat0.getId();
                v = shortcutInfoCompat0.getRank();
            }
        }
        return s;
    }

    private static List getShortcutInfoListeners(Context context0) {
        if(ShortcutManagerCompat.sShortcutInfoChangeListeners == null) {
            ArrayList arrayList0 = new ArrayList();
            PackageManager packageManager0 = context0.getPackageManager();
            Intent intent0 = new Intent("androidx.core.content.pm.SHORTCUT_LISTENER");
            intent0.setPackage("com.ruliweb.www.ruliapp");
            for(Object object0: packageManager0.queryIntentActivities(intent0, 0x80)) {
                ActivityInfo activityInfo0 = ((ResolveInfo)object0).activityInfo;
                if(activityInfo0 != null) {
                    Bundle bundle0 = activityInfo0.metaData;
                    if(bundle0 != null) {
                        String s = bundle0.getString("androidx.core.content.pm.shortcut_listener_impl");
                        if(s != null) {
                            try {
                                arrayList0.add(((ShortcutInfoChangeListener)Class.forName(s, false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context0)));
                            }
                            catch(Exception unused_ex) {
                            }
                        }
                    }
                }
            }
            if(ShortcutManagerCompat.sShortcutInfoChangeListeners == null) {
                ShortcutManagerCompat.sShortcutInfoChangeListeners = arrayList0;
            }
        }
        return ShortcutManagerCompat.sShortcutInfoChangeListeners;
    }

    private static ShortcutInfoCompatSaver getShortcutInfoSaverInstance(Context context0) {
        if(ShortcutManagerCompat.sShortcutInfoCompatSaver == null) {
            if(Build.VERSION.SDK_INT >= 23) {
                try {
                    ShortcutManagerCompat.sShortcutInfoCompatSaver = (ShortcutInfoCompatSaver)Class.forName("androidx.sharetarget.ShortcutInfoCompatSaverImpl", false, ShortcutManagerCompat.class.getClassLoader()).getMethod("getInstance", Context.class).invoke(null, context0);
                }
                catch(Exception unused_ex) {
                }
            }
            if(ShortcutManagerCompat.sShortcutInfoCompatSaver == null) {
                ShortcutManagerCompat.sShortcutInfoCompatSaver = new NoopImpl();
            }
        }
        return ShortcutManagerCompat.sShortcutInfoCompatSaver;
    }

    public static List getShortcuts(Context context0, int v) {
        if(Build.VERSION.SDK_INT >= 30) {
            return ShortcutInfoCompat.fromShortcuts(context0, ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).getShortcuts(v));
        }
        if(Build.VERSION.SDK_INT >= 25) {
            ShortcutManager shortcutManager0 = ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class));
            ArrayList arrayList0 = new ArrayList();
            if((v & 1) != 0) {
                arrayList0.addAll(shortcutManager0.getManifestShortcuts());
            }
            if((v & 2) != 0) {
                arrayList0.addAll(shortcutManager0.getDynamicShortcuts());
            }
            if((v & 4) != 0) {
                arrayList0.addAll(shortcutManager0.getPinnedShortcuts());
            }
            return ShortcutInfoCompat.fromShortcuts(context0, arrayList0);
        }
        if((v & 2) != 0) {
            try {
                return ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).getShortcuts();
            }
            catch(Exception unused_ex) {
            }
        }
        return Collections.EMPTY_LIST;
    }

    public static boolean isRateLimitingActive(Context context0) {
        Preconditions.checkNotNull(context0);
        return Build.VERSION.SDK_INT < 25 ? ShortcutManagerCompat.getShortcuts(context0, 3).size() == ShortcutManagerCompat.getMaxShortcutCountPerActivity(context0) : ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).isRateLimitingActive();
    }

    public static boolean isRequestPinShortcutSupported(Context context0) {
        if(Build.VERSION.SDK_INT >= 26) {
            return ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
        }
        if(ContextCompat.checkSelfPermission(context0, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0) {
            return false;
        }
        for(Object object0: context0.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0)) {
            String s = ((ResolveInfo)object0).activityInfo.permission;
            if(TextUtils.isEmpty(s) || "com.android.launcher.permission.INSTALL_SHORTCUT".equals(s)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static boolean pushDynamicShortcut(Context context0, ShortcutInfoCompat shortcutInfoCompat0) {
        Preconditions.checkNotNull(context0);
        Preconditions.checkNotNull(shortcutInfoCompat0);
        if(Build.VERSION.SDK_INT <= 0x20 && shortcutInfoCompat0.isExcludedFromSurfaces(1)) {
            for(Object object0: ShortcutManagerCompat.getShortcutInfoListeners(context0)) {
                ((ShortcutInfoChangeListener)object0).onShortcutAdded(Collections.singletonList(shortcutInfoCompat0));
            }
            return true;
        }
        int v = ShortcutManagerCompat.getMaxShortcutCountPerActivity(context0);
        if(v == 0) {
            return false;
        }
        if(Build.VERSION.SDK_INT <= 29) {
            ShortcutManagerCompat.convertUriIconToBitmapIcon(context0, shortcutInfoCompat0);
        }
        if(Build.VERSION.SDK_INT >= 30) {
            ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).pushDynamicShortcut(shortcutInfoCompat0.toShortcutInfo());
        }
        else if(Build.VERSION.SDK_INT >= 25) {
            ShortcutManager shortcutManager0 = ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class));
            if(shortcutManager0.isRateLimitingActive()) {
                return false;
            }
            List list0 = shortcutManager0.getDynamicShortcuts();
            if(list0.size() >= v) {
                shortcutManager0.removeDynamicShortcuts(Arrays.asList(new String[]{Api25Impl.getShortcutInfoWithLowestRank(list0)}));
            }
            shortcutManager0.addDynamicShortcuts(Arrays.asList(new ShortcutInfo[]{shortcutInfoCompat0.toShortcutInfo()}));
        }
        ShortcutInfoCompatSaver shortcutInfoCompatSaver0 = ShortcutManagerCompat.getShortcutInfoSaverInstance(context0);
        try {
            List list1 = shortcutInfoCompatSaver0.getShortcuts();
            if(list1.size() >= v) {
                shortcutInfoCompatSaver0.removeShortcuts(Arrays.asList(new String[]{ShortcutManagerCompat.getShortcutInfoCompatWithLowestRank(list1)}));
            }
            shortcutInfoCompatSaver0.addShortcuts(Arrays.asList(new ShortcutInfoCompat[]{shortcutInfoCompat0}));
            return true;
        }
        catch(Exception unused_ex) {
            for(Object object1: ShortcutManagerCompat.getShortcutInfoListeners(context0)) {
                ((ShortcutInfoChangeListener)object1).onShortcutAdded(Collections.singletonList(shortcutInfoCompat0));
            }
            ShortcutManagerCompat.reportShortcutUsed(context0, shortcutInfoCompat0.getId());
            return false;
        }
        finally {
            for(Object object2: ShortcutManagerCompat.getShortcutInfoListeners(context0)) {
                ((ShortcutInfoChangeListener)object2).onShortcutAdded(Collections.singletonList(shortcutInfoCompat0));
            }
            ShortcutManagerCompat.reportShortcutUsed(context0, shortcutInfoCompat0.getId());
        }
    }

    public static void removeAllDynamicShortcuts(Context context0) {
        if(Build.VERSION.SDK_INT >= 25) {
            ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).removeAllDynamicShortcuts();
        }
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).removeAllShortcuts();
        Iterator iterator0 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    public static void removeDynamicShortcuts(Context context0, List list0) {
        if(Build.VERSION.SDK_INT >= 25) {
            ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).removeDynamicShortcuts(list0);
        }
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).removeShortcuts(list0);
        Iterator iterator0 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    public static void removeLongLivedShortcuts(Context context0, List list0) {
        if(Build.VERSION.SDK_INT < 30) {
            ShortcutManagerCompat.removeDynamicShortcuts(context0, list0);
            return;
        }
        ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).removeLongLivedShortcuts(list0);
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).removeShortcuts(list0);
        Iterator iterator0 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    private static List removeShortcutsExcludedFromSurface(List list0, int v) {
        Objects.requireNonNull(list0);
        if(Build.VERSION.SDK_INT > 0x20) {
            return list0;
        }
        List list1 = new ArrayList(list0);
        for(Object object0: list0) {
            ShortcutInfoCompat shortcutInfoCompat0 = (ShortcutInfoCompat)object0;
            if(shortcutInfoCompat0.isExcludedFromSurfaces(v)) {
                list1.remove(shortcutInfoCompat0);
            }
        }
        return list1;
    }

    public static void reportShortcutUsed(Context context0, String s) {
        Preconditions.checkNotNull(context0);
        Preconditions.checkNotNull(s);
        if(Build.VERSION.SDK_INT >= 25) {
            ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).reportShortcutUsed(s);
        }
        for(Object object0: ShortcutManagerCompat.getShortcutInfoListeners(context0)) {
            ((ShortcutInfoChangeListener)object0).onShortcutUsageReported(Collections.singletonList(s));
        }
    }

    public static boolean requestPinShortcut(Context context0, ShortcutInfoCompat shortcutInfoCompat0, IntentSender intentSender0) {
        if(Build.VERSION.SDK_INT <= 0x20 && shortcutInfoCompat0.isExcludedFromSurfaces(1)) {
            return false;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            return ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcutInfoCompat0.toShortcutInfo(), intentSender0);
        }
        if(!ShortcutManagerCompat.isRequestPinShortcutSupported(context0)) {
            return false;
        }
        Intent intent0 = shortcutInfoCompat0.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
        if(intentSender0 == null) {
            context0.sendBroadcast(intent0);
            return true;
        }
        context0.sendOrderedBroadcast(intent0, null, new BroadcastReceiver() {
            @Override  // android.content.BroadcastReceiver
            public void onReceive(Context context0, Intent intent0) {
                try {
                    intentSender0.sendIntent(context0, 0, null, null, null);
                }
                catch(IntentSender.SendIntentException unused_ex) {
                }
            }
        }, null, -1, null, null);
        return true;
    }

    public static boolean setDynamicShortcuts(Context context0, List list0) {
        Preconditions.checkNotNull(context0);
        Preconditions.checkNotNull(list0);
        List list1 = ShortcutManagerCompat.removeShortcutsExcludedFromSurface(list0, 1);
        if(Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList0 = new ArrayList(list1.size());
            for(Object object0: list1) {
                arrayList0.add(((ShortcutInfoCompat)object0).toShortcutInfo());
            }
            if(!ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).setDynamicShortcuts(arrayList0)) {
                return false;
            }
        }
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).removeAllShortcuts();
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).addShortcuts(list1);
        Iterator iterator1 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator1.hasNext()) {
            iterator1.next();
        }
        return true;
    }

    static void setShortcutInfoChangeListeners(List list0) {
        ShortcutManagerCompat.sShortcutInfoChangeListeners = list0;
    }

    static void setShortcutInfoCompatSaver(ShortcutInfoCompatSaver shortcutInfoCompatSaver0) {
        ShortcutManagerCompat.sShortcutInfoCompatSaver = shortcutInfoCompatSaver0;
    }

    public static boolean updateShortcuts(Context context0, List list0) {
        List list1 = ShortcutManagerCompat.removeShortcutsExcludedFromSurface(list0, 1);
        if(Build.VERSION.SDK_INT <= 29) {
            ShortcutManagerCompat.convertUriIconsToBitmapIcons(context0, list1);
        }
        if(Build.VERSION.SDK_INT >= 25) {
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: list1) {
                arrayList0.add(((ShortcutInfoCompat)object0).toShortcutInfo());
            }
            if(!ColorKt..ExternalSyntheticApiModelOutline0.m(context0.getSystemService(ShortcutManager.class)).updateShortcuts(arrayList0)) {
                return false;
            }
        }
        ShortcutManagerCompat.getShortcutInfoSaverInstance(context0).addShortcuts(list1);
        Iterator iterator1 = ShortcutManagerCompat.getShortcutInfoListeners(context0).iterator();
        while(iterator1.hasNext()) {
            iterator1.next();
        }
        return true;
    }
}

