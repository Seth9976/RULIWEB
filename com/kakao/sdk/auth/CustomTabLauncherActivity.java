package com.kakao.sdk.auth;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.appcompat.app.AppCompatActivity;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import com.kakao.sdk.common.model.KakaoSdkError;
import com.kakao.sdk.common.util.KakaoCustomTabsClient;
import com.kakao.sdk.common.util.SdkLog;
import kotlin.Metadata;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016J\u0012\u0010\u0011\u001A\u00020\u000E2\b\u0010\u0012\u001A\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001A\u00020\u000EH\u0014J\u0012\u0010\u0015\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0016\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\u0013H\u0014J\b\u0010\u0017\u001A\u00020\u000EH\u0014J\u0010\u0010\u0018\u001A\u00020\u000E2\u0006\u0010\u0019\u001A\u00020\u0013H\u0014J\u0010\u0010\u001A\u001A\u00020\u000E2\u0006\u0010\u001B\u001A\u00020\bH\u0002J\u0010\u0010\u001C\u001A\u00020\u000E2\u0006\u0010\u001B\u001A\u00020\bH\u0002J\u0010\u0010\u001D\u001A\u00020\u000E2\u0006\u0010\u001E\u001A\u00020\u001FH\u0002J\u0010\u0010 \u001A\u00020\u000E2\u0006\u0010\u001B\u001A\u00020\bH\u0002R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/kakao/sdk/auth/CustomTabLauncherActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "customTabsConnection", "Landroid/content/ServiceConnection;", "customTabsOpened", "", "fullUri", "Landroid/net/Uri;", "internalHandler", "Landroid/os/Handler;", "resultReceiver", "Landroid/os/ResultReceiver;", "loadData", "", "intent", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "onRestoreInstanceState", "onResume", "onSaveInstanceState", "outState", "openBrowserWithoutBinding", "uri", "openChromeCustomTab", "sendError", "exception", "Lcom/kakao/sdk/common/model/KakaoSdkError;", "sendOK", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public class CustomTabLauncherActivity extends AppCompatActivity {
    private ServiceConnection customTabsConnection;
    private boolean customTabsOpened;
    private Uri fullUri;
    private Handler internalHandler;
    private ResultReceiver resultReceiver;

    public void loadData(Intent intent0) {
        Uri uri0;
        ResultReceiver resultReceiver0;
        Intrinsics.checkNotNullParameter(intent0, "intent");
        try {
            Bundle bundle0 = intent0.getExtras();
            if(bundle0 != null) {
                Bundle bundle1 = bundle0.getBundle("key.bundle");
                if(bundle1 != null) {
                    if(Build.VERSION.SDK_INT >= 33) {
                        resultReceiver0 = (ResultReceiver)LinkFollowing..ExternalSyntheticApiModelOutline0.m(bundle1, "key.result.receiver", ResultReceiver.class);
                    }
                    else {
                        Parcelable parcelable0 = bundle1.getParcelable("key.result.receiver");
                        if(parcelable0 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type android.os.ResultReceiver");
                        }
                        resultReceiver0 = (ResultReceiver)parcelable0;
                    }
                    if(resultReceiver0 == null) {
                        throw new IllegalStateException();
                    }
                    this.resultReceiver = resultReceiver0;
                    if(Build.VERSION.SDK_INT >= 33) {
                        uri0 = (Uri)LinkFollowing..ExternalSyntheticApiModelOutline0.m(bundle1, "key.full_authorize_uri", Uri.class);
                        goto label_19;
                    }
                    else {
                        Parcelable parcelable1 = bundle1.getParcelable("key.full_authorize_uri");
                        if(parcelable1 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type android.net.Uri");
                        }
                        uri0 = (Uri)parcelable1;
                    label_19:
                        if(uri0 == null) {
                            throw new IllegalStateException();
                        }
                        this.fullUri = uri0;
                    }
                }
            }
            this.internalHandler = new Handler(Looper.getMainLooper(), new CustomTabLauncherActivity..ExternalSyntheticLambda0(this));
        }
        catch(Throwable throwable0) {
            SdkLog.Companion.e(throwable0);
            ClientError clientError0 = new ClientError(ClientErrorCause.Unknown, null, 2, null);
            clientError0.initCause(throwable0);
            this.sendError(clientError0);
        }
    }

    private static final boolean loadData$lambda-1(CustomTabLauncherActivity customTabLauncherActivity0, Message message0) {
        Intrinsics.checkNotNullParameter(customTabLauncherActivity0, "this$0");
        Intrinsics.checkNotNullParameter(message0, "it");
        SdkLog.Companion.i("handle delay message");
        customTabLauncherActivity0.sendError(new ClientError(ClientErrorCause.Cancelled, null, 2, null));
        return true;
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        Intent intent0 = this.getIntent();
        Intrinsics.checkNotNullExpressionValue(intent0, "intent");
        this.loadData(intent0);
    }

    @Override  // androidx.appcompat.app.AppCompatActivity
    protected void onDestroy() {
        super.onDestroy();
        ServiceConnection serviceConnection0 = this.customTabsConnection;
        if(serviceConnection0 == null) {
            return;
        }
        this.unbindService(serviceConnection0);
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onNewIntent(Intent intent0) {
        super.onNewIntent(intent0);
        SdkLog.Companion.i("onNewIntent");
        this.setIntent(intent0);
        if(Intrinsics.areEqual((this.internalHandler == null ? null : Boolean.valueOf(this.internalHandler.hasMessages(0))), Boolean.TRUE)) {
            Handler handler0 = this.internalHandler;
            if(handler0 != null) {
                handler0.removeMessages(0);
            }
        }
        this.internalHandler = null;
        if(intent0 != null) {
            Uri uri0 = intent0.getData();
            if(uri0 != null) {
                this.sendOK(uri0);
            }
        }
        this.finish();
    }

    @Override  // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "savedInstanceState");
        super.onRestoreInstanceState(bundle0);
        this.customTabsOpened = bundle0.getBoolean("key.customtabs.opened", this.customTabsOpened);
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onResume() {
        super.onResume();
        Boolean boolean0 = null;
        if(!this.customTabsOpened) {
            this.customTabsOpened = true;
            Uri uri0 = this.fullUri;
            if(uri0 != null) {
                this.openChromeCustomTab(uri0);
                return;
            }
            this.sendError(new ClientError(ClientErrorCause.IllegalState, "url has been not initialized."));
            return;
        }
        SdkLog.Companion.i("trigger delay message");
        Handler handler0 = this.internalHandler;
        if(handler0 != null) {
            boolean0 = Boolean.valueOf(handler0.hasMessages(0));
        }
        if(Intrinsics.areEqual(boolean0, Boolean.FALSE)) {
            Handler handler1 = this.internalHandler;
            if(handler1 != null) {
                handler1.sendEmptyMessageDelayed(0, 100L);
            }
        }
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onSaveInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outState");
        super.onSaveInstanceState(bundle0);
        bundle0.putBoolean("key.customtabs.opened", this.customTabsOpened);
    }

    private final void openBrowserWithoutBinding(Uri uri0) {
        try {
            KakaoCustomTabsClient.INSTANCE.open(this, uri0);
        }
        catch(ActivityNotFoundException activityNotFoundException0) {
            SdkLog.Companion.w(activityNotFoundException0);
            this.sendError(new ClientError(ClientErrorCause.NotSupported, "No browser has been installed on a device."));
        }
    }

    private final void openChromeCustomTab(Uri uri0) {
        SdkLog.Companion.i("Authorize Uri: " + uri0);
        try {
            ServiceConnection serviceConnection0 = KakaoCustomTabsClient.INSTANCE.openWithDefault(this, uri0);
            this.customTabsConnection = serviceConnection0;
            if(serviceConnection0 == null) {
                SdkLog.Companion.i("try to open chrome without service binding");
                this.openBrowserWithoutBinding(uri0);
            }
        }
        catch(UnsupportedOperationException unsupportedOperationException0) {
            SdkLog.Companion.w(unsupportedOperationException0);
            this.openBrowserWithoutBinding(uri0);
        }
    }

    private final void sendError(KakaoSdkError kakaoSdkError0) {
        ResultReceiver resultReceiver0 = this.resultReceiver;
        if(resultReceiver0 != null) {
            Bundle bundle0 = new Bundle();
            bundle0.putSerializable("key.exception", kakaoSdkError0);
            resultReceiver0.send(0, bundle0);
        }
        this.finish();
    }

    private final void sendOK(Uri uri0) {
        ResultReceiver resultReceiver0 = this.resultReceiver;
        if(resultReceiver0 != null) {
            Bundle bundle0 = new Bundle();
            bundle0.putParcelable("key.url", uri0);
            resultReceiver0.send(-1, bundle0);
        }
        this.finish();
    }
}

