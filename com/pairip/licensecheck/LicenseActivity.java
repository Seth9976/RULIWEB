package com.pairip.licensecheck;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent.CanceledException;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.util.Log;

public class LicenseActivity extends Activity {
    public static enum ActivityType {
        PAYWALL,
        ERROR_DIALOG;

        private static ActivityType[] $values() [...] // Inlined contents
    }

    public static final String ACTIVITY_TYPE_ARG_NAME = "activitytype";
    public static final String PAYWALL_INTENT_ARG_NAME = "paywallintent";
    private static final String TAG = "LicenseActivity";

    // 检测为 Lambda 实现
    public static void $r8$lambda$N5_Pzpb-eSKmOONXn3Kn0QvMbys(LicenseActivity licenseActivity0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$fE_XZ7S0hhHsxQNTfy8mxeJ7kEU(LicenseActivity licenseActivity0, DialogInterface dialogInterface0, int v) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$x-JmBIDmuVzGN23Wk7Dd1TBpzO0(LicenseActivity licenseActivity0, PendingIntent pendingIntent0) [...]

    protected void closeApp() {
        this.finishAndRemoveTask();
        System.exit(0);
    }

    private void lambda$showErrorDialog$0() {
        try {
            new AlertDialog.Builder(this).setTitle("Something went wrong").setMessage("Check that Google Play is enabled on your device and that you\'re using an up-to-date version before opening the app. If the problem persists try reinstalling the app.").setPositiveButton("Close", (DialogInterface dialogInterface0, int v) -> this.lambda$showErrorDialog$1(dialogInterface0, v)).setCancelable(false).show();
        }
        catch(RuntimeException runtimeException0) {
            Log.d("LicenseActivity", "Couldn\'t show the error dialog. " + Log.getStackTraceString(runtimeException0));
        }
    }

    private void lambda$showErrorDialog$1(DialogInterface dialogInterface0, int v) {
        this.closeApp();
    }

    private void lambda$showPaywallAndCloseApp$0(PendingIntent pendingIntent0) {
        try {
            if(Build.VERSION.SDK_INT >= 34) {
                pendingIntent0.send(ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle());
            }
            else {
                pendingIntent0.send();
            }
            this.closeApp();
        }
        catch(PendingIntent.CanceledException pendingIntent$CanceledException0) {
            this.logAndShowErrorDialog("Paywall intent unexpectedly cancelled.", pendingIntent$CanceledException0);
        }
    }

    private void logAndShowErrorDialog(String s) {
        Log.e("LicenseActivity", s);
        this.showErrorDialog();
    }

    private void logAndShowErrorDialog(String s, Exception exception0) {
        this.logAndShowErrorDialog(s + " " + Log.getStackTraceString(exception0));
    }

    @Override  // android.app.Activity
    public void onStart() {
        super.onStart();
        try {
            switch(((ActivityType)this.getIntent().getSerializableExtra("activitytype")).ordinal()) {
                case 0: {
                    this.showPaywallAndCloseApp();
                    return;
                }
                case 1: {
                    this.showErrorDialog();
                }
            }
        }
        catch(Exception exception0) {
            this.logAndShowErrorDialog("Couldn\'t process license activity correctly.", exception0);
        }
    }

    private void showErrorDialog() {
        this.runOnUiThread(() -> this.lambda$showErrorDialog$0());
    }

    private void showPaywallAndCloseApp() {
        PendingIntent pendingIntent0 = (PendingIntent)this.getIntent().getParcelableExtra("paywallintent");
        if(pendingIntent0 == null) {
            this.logAndShowErrorDialog("Paywall intent is not provided.");
            return;
        }
        this.runOnUiThread(() -> this.lambda$showPaywallAndCloseApp$0(pendingIntent0));
    }
}

