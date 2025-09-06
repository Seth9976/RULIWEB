package com.google.android.material.color;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.app.UiModeManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import androidx.core.os.BuildCompat;
import com.google.android.material.R.attr;
import com.google.android.material.color.utilities.Hct;
import com.google.android.material.color.utilities.SchemeContent;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DynamicColors {
    interface DeviceSupportCondition {
        boolean isSupported();
    }

    static class DynamicColorsActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private final DynamicColorsOptions dynamicColorsOptions;

        DynamicColorsActivityLifecycleCallbacks(DynamicColorsOptions dynamicColorsOptions0) {
            this.dynamicColorsOptions = dynamicColorsOptions0;
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity0, Bundle bundle0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity activity0, Bundle bundle0) {
            DynamicColors.applyToActivityIfAvailable(activity0, this.dynamicColorsOptions);
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity0) {
        }
    }

    public interface OnAppliedCallback {
        void onApplied(Activity arg1);
    }

    public interface Precondition {
        boolean shouldApplyDynamicColors(Activity arg1, int arg2);
    }

    private static final DeviceSupportCondition DEFAULT_DEVICE_SUPPORT_CONDITION;
    private static final Map DYNAMIC_COLOR_SUPPORTED_BRANDS;
    private static final Map DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS;
    private static final int[] DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE;
    private static final DeviceSupportCondition SAMSUNG_DEVICE_SUPPORT_CONDITION;
    private static final String TAG;
    private static final int USE_DEFAULT_THEME_OVERLAY;

    static {
        DynamicColors.DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE = new int[]{attr.dynamicColorThemeOverlay};
        com.google.android.material.color.DynamicColors.1 dynamicColors$10 = new DeviceSupportCondition() {
            @Override  // com.google.android.material.color.DynamicColors$DeviceSupportCondition
            public boolean isSupported() {
                return true;
            }
        };
        DynamicColors.DEFAULT_DEVICE_SUPPORT_CONDITION = dynamicColors$10;
        com.google.android.material.color.DynamicColors.2 dynamicColors$20 = new DeviceSupportCondition() {
            private Long version;

            @Override  // com.google.android.material.color.DynamicColors$DeviceSupportCondition
            public boolean isSupported() {
                if(this.version == null) {
                    try {
                        Method method0 = Build.class.getDeclaredMethod("getLong", String.class);
                        method0.setAccessible(true);
                        Long long0 = (Long)method0.invoke(null, "ro.build.version.oneui");
                        long0.longValue();
                        this.version = long0;
                        return ((long)this.version) >= 40100L;
                    }
                    catch(Exception unused_ex) {
                        this.version = -1L;
                    }
                }
                return ((long)this.version) >= 40100L;
            }
        };
        DynamicColors.SAMSUNG_DEVICE_SUPPORT_CONDITION = dynamicColors$20;
        HashMap hashMap0 = new HashMap();
        hashMap0.put("fcnt", dynamicColors$10);
        hashMap0.put("google", dynamicColors$10);
        hashMap0.put("hmd global", dynamicColors$10);
        hashMap0.put("infinix", dynamicColors$10);
        hashMap0.put("infinix mobility limited", dynamicColors$10);
        hashMap0.put("itel", dynamicColors$10);
        hashMap0.put("kyocera", dynamicColors$10);
        hashMap0.put("lenovo", dynamicColors$10);
        hashMap0.put("lge", dynamicColors$10);
        hashMap0.put("meizu", dynamicColors$10);
        hashMap0.put("motorola", dynamicColors$10);
        hashMap0.put("nothing", dynamicColors$10);
        hashMap0.put("oneplus", dynamicColors$10);
        hashMap0.put("oppo", dynamicColors$10);
        hashMap0.put("realme", dynamicColors$10);
        hashMap0.put("robolectric", dynamicColors$10);
        hashMap0.put("samsung", dynamicColors$20);
        hashMap0.put("sharp", dynamicColors$10);
        hashMap0.put("shift", dynamicColors$10);
        hashMap0.put("sony", dynamicColors$10);
        hashMap0.put("tcl", dynamicColors$10);
        hashMap0.put("tecno", dynamicColors$10);
        hashMap0.put("tecno mobile limited", dynamicColors$10);
        hashMap0.put("vivo", dynamicColors$10);
        hashMap0.put("wingtech", dynamicColors$10);
        hashMap0.put("xiaomi", dynamicColors$10);
        DynamicColors.DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS = Collections.unmodifiableMap(hashMap0);
        HashMap hashMap1 = new HashMap();
        hashMap1.put("asus", dynamicColors$10);
        hashMap1.put("jio", dynamicColors$10);
        DynamicColors.DYNAMIC_COLOR_SUPPORTED_BRANDS = Collections.unmodifiableMap(hashMap1);
        DynamicColors.TAG = "DynamicColors";
    }

    @Deprecated
    public static void applyIfAvailable(Activity activity0) {
        DynamicColors.applyToActivityIfAvailable(activity0);
    }

    @Deprecated
    public static void applyIfAvailable(Activity activity0, int v) {
        DynamicColors.applyToActivityIfAvailable(activity0, new Builder().setThemeOverlay(v).build());
    }

    @Deprecated
    public static void applyIfAvailable(Activity activity0, Precondition dynamicColors$Precondition0) {
        DynamicColors.applyToActivityIfAvailable(activity0, new Builder().setPrecondition(dynamicColors$Precondition0).build());
    }

    public static void applyToActivitiesIfAvailable(Application application0) {
        DynamicColors.applyToActivitiesIfAvailable(application0, new Builder().build());
    }

    @Deprecated
    public static void applyToActivitiesIfAvailable(Application application0, int v) {
        DynamicColors.applyToActivitiesIfAvailable(application0, new Builder().setThemeOverlay(v).build());
    }

    @Deprecated
    public static void applyToActivitiesIfAvailable(Application application0, int v, Precondition dynamicColors$Precondition0) {
        DynamicColors.applyToActivitiesIfAvailable(application0, new Builder().setThemeOverlay(v).setPrecondition(dynamicColors$Precondition0).build());
    }

    @Deprecated
    public static void applyToActivitiesIfAvailable(Application application0, Precondition dynamicColors$Precondition0) {
        DynamicColors.applyToActivitiesIfAvailable(application0, new Builder().setPrecondition(dynamicColors$Precondition0).build());
    }

    public static void applyToActivitiesIfAvailable(Application application0, DynamicColorsOptions dynamicColorsOptions0) {
        application0.registerActivityLifecycleCallbacks(new DynamicColorsActivityLifecycleCallbacks(dynamicColorsOptions0));
    }

    public static void applyToActivityIfAvailable(Activity activity0) {
        DynamicColors.applyToActivityIfAvailable(activity0, new Builder().build());
    }

    public static void applyToActivityIfAvailable(Activity activity0, DynamicColorsOptions dynamicColorsOptions0) {
        int v;
        if(DynamicColors.isDynamicColorAvailable()) {
            if(dynamicColorsOptions0.getContentBasedSeedColor() != null) {
                v = 0;
            }
            else if(dynamicColorsOptions0.getThemeOverlay() == 0) {
                v = DynamicColors.getDefaultThemeOverlay(activity0, DynamicColors.DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE);
            }
            else {
                v = dynamicColorsOptions0.getThemeOverlay();
            }
            if(dynamicColorsOptions0.getPrecondition().shouldApplyDynamicColors(activity0, v)) {
                if(dynamicColorsOptions0.getContentBasedSeedColor() == null) {
                    ThemeUtils.applyThemeOverlay(activity0, v);
                }
                else {
                    SchemeContent schemeContent0 = new SchemeContent(Hct.fromInt(((int)dynamicColorsOptions0.getContentBasedSeedColor())), !MaterialColors.isLightTheme(activity0), ((double)DynamicColors.getSystemContrast(activity0)));
                    ColorResourcesOverride colorResourcesOverride0 = ColorResourcesOverride.-CC.getInstance();
                    if(colorResourcesOverride0 == null || !colorResourcesOverride0.applyIfPossible(activity0, MaterialColorUtilitiesHelper.createColorResourcesIdsToColorValues(schemeContent0))) {
                        return;
                    }
                }
                dynamicColorsOptions0.getOnAppliedCallback().onApplied(activity0);
            }
        }
    }

    private static int getDefaultThemeOverlay(Context context0, int[] arr_v) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(arr_v);
        int v = typedArray0.getResourceId(0, 0);
        typedArray0.recycle();
        return v;
    }

    private static float getSystemContrast(Context context0) {
        UiModeManager uiModeManager0 = (UiModeManager)context0.getSystemService("uimode");
        return uiModeManager0 == null || Build.VERSION.SDK_INT < 34 ? 0.0f : uiModeManager0.getContrast();
    }

    public static boolean isDynamicColorAvailable() {
        if(Build.VERSION.SDK_INT < 0x1F) {
            return false;
        }
        if(BuildCompat.isAtLeastT()) {
            return true;
        }
        DeviceSupportCondition dynamicColors$DeviceSupportCondition0 = (DeviceSupportCondition)DynamicColors.DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS.get(Build.MANUFACTURER.toLowerCase(Locale.ROOT));
        if(dynamicColors$DeviceSupportCondition0 == null) {
            dynamicColors$DeviceSupportCondition0 = (DeviceSupportCondition)DynamicColors.DYNAMIC_COLOR_SUPPORTED_BRANDS.get(Build.BRAND.toLowerCase(Locale.ROOT));
        }
        return dynamicColors$DeviceSupportCondition0 != null && dynamicColors$DeviceSupportCondition0.isSupported();
    }

    public static Context wrapContextIfAvailable(Context context0) {
        return DynamicColors.wrapContextIfAvailable(context0, 0);
    }

    public static Context wrapContextIfAvailable(Context context0, int v) {
        return DynamicColors.wrapContextIfAvailable(context0, new Builder().setThemeOverlay(v).build());
    }

    public static Context wrapContextIfAvailable(Context context0, DynamicColorsOptions dynamicColorsOptions0) {
        if(DynamicColors.isDynamicColorAvailable()) {
            int v = dynamicColorsOptions0.getThemeOverlay();
            if(v == 0) {
                v = DynamicColors.getDefaultThemeOverlay(context0, DynamicColors.DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE);
            }
            if(v != 0) {
                if(dynamicColorsOptions0.getContentBasedSeedColor() != null) {
                    SchemeContent schemeContent0 = new SchemeContent(Hct.fromInt(((int)dynamicColorsOptions0.getContentBasedSeedColor())), !MaterialColors.isLightTheme(context0), ((double)DynamicColors.getSystemContrast(context0)));
                    ColorResourcesOverride colorResourcesOverride0 = ColorResourcesOverride.-CC.getInstance();
                    if(colorResourcesOverride0 != null) {
                        return colorResourcesOverride0.wrapContextIfPossible(context0, MaterialColorUtilitiesHelper.createColorResourcesIdsToColorValues(schemeContent0));
                    }
                }
                return new ContextThemeWrapper(context0, v);
            }
        }
        return context0;
    }
}

