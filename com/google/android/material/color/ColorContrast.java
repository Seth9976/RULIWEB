package com.google.android.material.color;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.app.UiModeManager.ContrastChangeListener;
import android.app.UiModeManager;
import android.content.Context;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import java.util.LinkedHashSet;
import java.util.Set;

public class ColorContrast {
    static class ColorContrastActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private final Set activitiesInStack;
        private final ColorContrastOptions colorContrastOptions;
        private UiModeManager.ContrastChangeListener contrastChangeListener;

        ColorContrastActivityLifecycleCallbacks(ColorContrastOptions colorContrastOptions0) {
            this.activitiesInStack = new LinkedHashSet();
            this.colorContrastOptions = colorContrastOptions0;
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity0, Bundle bundle0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity0) {
            this.activitiesInStack.remove(activity0);
            UiModeManager uiModeManager0 = (UiModeManager)activity0.getSystemService("uimode");
            if(uiModeManager0 != null && this.contrastChangeListener != null && this.activitiesInStack.isEmpty()) {
                uiModeManager0.removeContrastChangeListener(this.contrastChangeListener);
                this.contrastChangeListener = null;
            }
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity0) {
        }

        @Override  // android.app.Application$ActivityLifecycleCallbacks
        public void onActivityPreCreated(Activity activity0, Bundle bundle0) {
            UiModeManager uiModeManager0 = (UiModeManager)activity0.getSystemService("uimode");
            if(uiModeManager0 != null && this.activitiesInStack.isEmpty() && this.contrastChangeListener == null) {
                this.contrastChangeListener = new UiModeManager.ContrastChangeListener() {
                    @Override  // android.app.UiModeManager$ContrastChangeListener
                    public void onContrastChanged(float f) {
                        for(Object object0: ColorContrastActivityLifecycleCallbacks.this.activitiesInStack) {
                            ((Activity)object0).recreate();
                        }
                    }
                };
                uiModeManager0.addContrastChangeListener(ContextCompat.getMainExecutor(activity0.getApplicationContext()), this.contrastChangeListener);
            }
            this.activitiesInStack.add(activity0);
            if(uiModeManager0 != null) {
                ColorContrast.applyToActivityIfAvailable(activity0, this.colorContrastOptions);
            }
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

    private static final float HIGH_CONTRAST_THRESHOLD = 0.666667f;
    private static final float MEDIUM_CONTRAST_THRESHOLD = 0.333333f;

    // 去混淆评级： 低(30)
    public static void applyToActivitiesIfAvailable(Application application0, ColorContrastOptions colorContrastOptions0) {
    }

    // 去混淆评级： 低(30)
    public static void applyToActivityIfAvailable(Activity activity0, ColorContrastOptions colorContrastOptions0) {
    }

    private static int getContrastThemeOverlayResourceId(Context context0, ColorContrastOptions colorContrastOptions0) {
        UiModeManager uiModeManager0 = (UiModeManager)context0.getSystemService("uimode");
        return 0;
    }

    public static boolean isContrastAvailable() [...] // 潜在的解密器

    // 去混淆评级： 低(30)
    public static Context wrapContextIfAvailable(Context context0, ColorContrastOptions colorContrastOptions0) {
        return context0;
    }
}

