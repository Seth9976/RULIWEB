package com.pairip.licensecheck;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class LicenseClient implements ServiceConnection {
    static final class DelayedTaskExecutor {
        private final Handler handler;

        private DelayedTaskExecutor() {
            this.handler = new Handler(Looper.getMainLooper());
        }

        DelayedTaskExecutor(LicenseClient-IA licenseClient-IA0) {
        }

        public void schedule(Runnable runnable0, long v) {
            this.handler.postDelayed(runnable0, v);
        }
    }

    public static enum LicenseCheckState {
        CHECK_REQUIRED,
        OK;

        private static LicenseCheckState[] $values() [...] // Inlined contents
    }

    private static final int ERROR_INVALID_PACKAGE_NAME = 3;
    private static final int FLAG_RPC_CALL = 0;
    private static final int LICENSED = 0;
    private static final int MAX_RETRIES = 3;
    private static final int MILLIS_PER_SEC = 1000;
    private static final int NOT_LICENSED = 2;
    private static final String PAYLOAD_PAYWALL = "PAYWALL_INTENT";
    private static final int RETRY_DELAY_MILLIS = 1000;
    private static final String SERVICE_INTERFACE_CLASS_NAME = "com.android.vending.licensing.ILicensingService";
    private static final String SERVICE_PACKAGE = "com.android.vending";
    private static final String TAG = "LicenseClient";
    private static final int TRANSACTION_CHECK_LICENSE_V2 = 2;
    private final Context context;
    private final DelayedTaskExecutor delayedTaskExecutor;
    protected static Runnable exitAction = null;
    protected static LicenseCheckState licenseCheckState = null;
    protected static String licensePubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgCeJoZ40GO2pMtiNCQuGvPJGJsFTeLdwz8hgT5jVdkQ1r3L9i1YppjjNssjregCL5YfVGPuYW+FosbGafizawbFXEoJUO6zs9mwobh69yl91ivnYCePFeXmWjexiJemnFm+VGsTWi0UuUgh+i2FXCCXk0qQQv9hIdlO7p1h0VqLsjyipscMaEEFd2x3NHozpcYGHBtC2GTVBdYtdMuj85j7B5S8shrjDdZwpkiqNiSzjTSNEczLbiyMqp+QRgm5hdyYE+hF8jst9RcZzolqWTdm/dylfm0qIp++Q0mjS1Qi5mztOv1+Wup0sidLzA/8mjWrfax9DbWspZZrDX+W4XwIDAQAB";
    protected static String packageName = "com.ruliweb.www.ruliapp";
    private static Bundle responsePayload;
    private int retryNum;

    // 检测为 Lambda 实现
    public static void $r8$lambda$tTRuJInP7s484yRu-m6AsnoI1z4(LicenseClient licenseClient0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$xzrAfByzooHDT9oIsgTdQvzthuE(LicenseClient licenseClient0, IBinder iBinder0) [...]

    static {
        LicenseClient.exitAction = new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        LicenseClient.licenseCheckState = LicenseCheckState.CHECK_REQUIRED;
    }

    public LicenseClient(Context context0) {
        this.delayedTaskExecutor = new DelayedTaskExecutor(null);
        this.retryNum = 0;
        this.context = context0;
    }

    public void checkLicenseInternal(IBinder iBinder0) throws LicenseCheckException {
        if(iBinder0 == null) {
            this.retryOrThrow(new LicenseCheckException("Received a null binder."));
            return;
        }
        Log.d("LicenseClient", "Sending request to licensing service...");
        Parcel parcel0 = Parcel.obtain();
        Parcel parcel1 = Parcel.obtain();
        try {
            this.populateInputData(parcel0, iBinder0);
            if(!iBinder0.transact(2, parcel0, parcel1, 0)) {
                this.handleError(new LicenseCheckException("Licensing service could not process request."));
            }
        }
        catch(DeadObjectException deadObjectException0) {
            this.retryOrThrow(new LicenseCheckException("Licensing service process died.", deadObjectException0));
        }
        catch(RemoteException remoteException0) {
            this.handleError(new LicenseCheckException("Error when calling licensing service.", remoteException0));
        }
        finally {
            parcel0.recycle();
            parcel1.recycle();
            Log.d("LicenseClient", "Request to licensing service sent.");
        }
    }

    private void connectToLicensingService() {
        Log.d("LicenseClient", "Connecting to the licensing service...");
        Intent intent0 = new Intent("com.android.vending.licensing.ILicensingService").setPackage("com.android.vending").setAction("com.android.vending.licensing.ILicensingService");
        try {
            if(!this.context.bindService(intent0, this, 1)) {
                goto label_7;
            }
        }
        catch(SecurityException securityException0) {
            this.retryOrThrow(new LicenseCheckException("Not allowed to bind with the licensing service.", securityException0));
        }
        return;
    label_7:
        this.retryOrThrow(new LicenseCheckException("Could not bind with the licensing service."));
    }

    private Intent createCloseAppIntentOrExitIfAppInBackground() {
        if(!this.isForeground()) {
            LicenseClient.exitAction.run();
        }
        Intent intent0 = new Intent(this.context, LicenseActivity.class);
        intent0.addFlags(0x4000000);
        intent0.addFlags(0x8000);
        intent0.addFlags(0x10000000);
        return intent0;
    }

    private static ILicenseV2ResultListener createResultListener(LicenseClient licenseClient0) {
        return new Stub() {
            final LicenseClient val$client;

            @Override  // com.pairip.licensecheck.ILicenseV2ResultListener
            public void verifyLicense(int v, Bundle bundle0) {
                LicenseClient.this.processResponse(v, bundle0);
            }
        };
    }

    // 去混淆评级： 低(20)
    public static String getLicensePubKey() [...] // 潜在的解密器

    private void handleError(LicenseCheckException licenseCheckException0) {
        Log.e("LicenseClient", "Error while checking license: " + Log.getStackTraceString(licenseCheckException0));
        if(LicenseClient.licenseCheckState.equals(LicenseCheckState.OK)) {
            return;
        }
        this.startErrorDialogActivity();
    }

    public void initializeLicenseCheck() {
        switch(LicenseClient.licenseCheckState.ordinal()) {
            case 0: {
                this.connectToLicensingService();
                return;
            }
            case 1: {
                try {
                    ResponseValidator.validateResponse(LicenseClient.responsePayload, "com.ruliweb.www.ruliapp");
                }
                catch(LicenseCheckException licenseCheckException0) {
                    this.handleError(licenseCheckException0);
                }
            }
        }
    }

    private boolean isForeground() {
        ActivityManager.RunningAppProcessInfo activityManager$RunningAppProcessInfo0 = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(activityManager$RunningAppProcessInfo0);
        return activityManager$RunningAppProcessInfo0.importance <= 100;
    }

    private void lambda$onServiceConnected$0(IBinder iBinder0) {
        try {
            this.checkLicenseInternal(iBinder0);
        }
        catch(LicenseCheckException licenseCheckException0) {
            this.handleError(licenseCheckException0);
        }
    }

    @Override  // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        Log.d("LicenseClient", "Connected to the licensing service.");
        if(LicenseClient.licenseCheckState.equals(LicenseCheckState.OK)) {
            return;
        }
        new Thread(() -> this.lambda$onServiceConnected$0(iBinder0)).start();
    }

    @Override  // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName0) {
        Log.w("LicenseClient", "Unexpectedly disconnected from the licensing service.");
        this.retryOrThrow(new LicenseCheckException("Licensing service unexpectedly disconnected."));
    }

    private void populateInputData(Parcel parcel0, IBinder iBinder0) throws RemoteException {
        parcel0.writeInterfaceToken(iBinder0.getInterfaceDescriptor());
        parcel0.writeString("com.ruliweb.www.ruliapp");
        parcel0.writeStrongBinder(LicenseClient.createResultListener(this).asBinder());
        parcel0.writeInt(0);
    }

    private void processResponse(int v, Bundle bundle0) {
        try {
            switch(v) {
                case 0: {
                    ResponseValidator.validateResponse(bundle0, "com.ruliweb.www.ruliapp");
                    Log.i("LicenseClient", "License check succeeded.");
                    LicenseClient.licenseCheckState = LicenseCheckState.OK;
                    LicenseClient.responsePayload = bundle0;
                    return;
                }
                case 3: {
                    throw new LicenseCheckException("Request package name invalid.");
                }
                default: {
                    if(v != 2) {
                        throw new LicenseCheckException(String.format("Unexpected response code %d received.", v));
                    }
                    this.startPaywallActivity(((PendingIntent)bundle0.getParcelable("PAYWALL_INTENT")));
                }
            }
        }
        catch(LicenseCheckException licenseCheckException0) {
            this.handleError(licenseCheckException0);
        }
    }

    private void retryOrThrow(LicenseCheckException licenseCheckException0) {
        int v = this.retryNum;
        if(v < 3) {
            this.retryNum = v + 1;
            LicenseClient..ExternalSyntheticLambda0 licenseClient$$ExternalSyntheticLambda00 = () -> this.connectToLicensingService();
            this.delayedTaskExecutor.schedule(licenseClient$$ExternalSyntheticLambda00, 1000L);
            Log.d("LicenseClient", String.format("Retry #%d. License check failed with error \'%s\'. Next try in %ds...", this.retryNum, licenseCheckException0.getMessage(), 1L));
            return;
        }
        this.handleError(licenseCheckException0);
    }

    private void startErrorDialogActivity() {
        Intent intent0 = this.createCloseAppIntentOrExitIfAppInBackground();
        intent0.putExtra("activitytype", ActivityType.ERROR_DIALOG);
        this.context.startActivity(intent0);
    }

    private void startPaywallActivity(PendingIntent pendingIntent0) {
        Intent intent0 = this.createCloseAppIntentOrExitIfAppInBackground();
        intent0.putExtra("paywallintent", pendingIntent0);
        intent0.putExtra("activitytype", ActivityType.PAYWALL);
        this.context.startActivity(intent0);
    }
}

