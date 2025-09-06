package androidx.core.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AppLaunchChecker {
    private static final String KEY_STARTED_FROM_LAUNCHER = "startedFromLauncher";
    private static final String SHARED_PREFS_NAME = "android.support.AppLaunchChecker";

    public static boolean hasStartedFromLauncher(Context context0) {
        return context0.getSharedPreferences("android.support.AppLaunchChecker", 0).getBoolean("startedFromLauncher", false);
    }

    public static void onActivityCreate(Activity activity0) {
        SharedPreferences sharedPreferences0 = activity0.getSharedPreferences("android.support.AppLaunchChecker", 0);
        if(!sharedPreferences0.getBoolean("startedFromLauncher", false)) {
            Intent intent0 = activity0.getIntent();
            if(intent0 != null && "android.intent.action.MAIN".equals(intent0.getAction()) && (intent0.hasCategory("android.intent.category.LAUNCHER") || intent0.hasCategory("android.intent.category.LEANBACK_LAUNCHER"))) {
                sharedPreferences0.edit().putBoolean("startedFromLauncher", true).apply();
            }
        }
    }
}

