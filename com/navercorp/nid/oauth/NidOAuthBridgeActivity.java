package com.navercorp.nid.oauth;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.log.NidLog;
import com.navercorp.nid.oauth.viewModel.NidOAuthBridgeViewModel;
import com.navercorp.nid.progress.NidProgressDialog;
import com.navercorp.nid.util.NidApplicationUtil;
import com.nhn.android.oauth.R.string;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 12\u00020\u0001:\u00011B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0007H\u0002J\u0010\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0018\u001A\u00020\u0019H\u0002J\b\u0010\u001A\u001A\u00020\u001BH\u0002J \u0010\u001C\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u00072\u0006\u0010\u001D\u001A\u00020\u00192\u0006\u0010\u001E\u001A\u00020\u0004H\u0002J\u0010\u0010\u001F\u001A\u00020\u00162\u0006\u0010 \u001A\u00020!H\u0016J\u0012\u0010\"\u001A\u00020\u00162\b\u0010#\u001A\u0004\u0018\u00010$H\u0014J\b\u0010%\u001A\u00020\u0016H\u0014J\b\u0010&\u001A\u00020\u0016H\u0014J\b\u0010\'\u001A\u00020\u0016H\u0014J\u0012\u0010(\u001A\u00020\u00162\b\u0010)\u001A\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010*\u001A\u00020\u00162\u0006\u0010+\u001A\u00020,H\u0016J\b\u0010-\u001A\u00020\u0016H\u0002J\b\u0010.\u001A\u00020\u0016H\u0003J\b\u0010/\u001A\u00020\u001BH\u0002J\b\u00100\u001A\u00020\u001BH\u0002R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u001C\u0010\u0005\u001A\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\t\u001A\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001B\u0010\n\u001A\u00020\u000B8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000E\u0010\u000F\u001A\u0004\b\f\u0010\rR\u001B\u0010\u0010\u001A\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000F\u001A\u0004\b\u0012\u0010\u0013¨\u00062"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthBridgeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authType", "", "customTabLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "naverappLauncher", "progress", "Lcom/navercorp/nid/progress/NidProgressDialog;", "getProgress", "()Lcom/navercorp/nid/progress/NidProgressDialog;", "progress$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/navercorp/nid/oauth/viewModel/NidOAuthBridgeViewModel;", "getViewModel", "()Lcom/navercorp/nid/oauth/viewModel/NidOAuthBridgeViewModel;", "viewModel$delegate", "finishWithErrorResult", "", "intent", "errCode", "Lcom/navercorp/nid/oauth/NidOAuthErrorCode;", "initData", "", "oauthFinish", "errorCode", "errorDescription", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "requestAccessTokenWithData", "data", "setRequestedOrientation", "requestedOrientation", "", "startLoginActivity", "startLoginWebviewActivity", "tryOAuthByCustomTab", "tryOAuthByNaverapp", "Companion", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthBridgeActivity extends AppCompatActivity {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthBridgeActivity$Companion;", "", "()V", "TAG", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[NidOAuthBehavior.values().length];
            arr_v[NidOAuthBehavior.NAVERAPP.ordinal()] = 1;
            arr_v[NidOAuthBehavior.CUSTOMTABS.ordinal()] = 2;
            arr_v[NidOAuthBehavior.WEBVIEW.ordinal()] = 3;
            arr_v[NidOAuthBehavior.DEFAULT.ordinal()] = 4;
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Companion Companion = null;
    public static final String TAG = "NidOAuthBridgeActivity";
    private String authType;
    private final ActivityResultLauncher customTabLauncher;
    private final ActivityResultLauncher naverappLauncher;
    private final Lazy progress$delegate;
    private final Lazy viewModel$delegate;

    // 检测为 Lambda 实现
    public static void $r8$lambda$4E0H_hDR6_d8yVbOv2tnCDHjnv0(NidOAuthBridgeActivity nidOAuthBridgeActivity0, ActivityResult activityResult0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$GpP8_jT1sgX6Y0VahaGHTARqrlA(NidOAuthBridgeActivity nidOAuthBridgeActivity0, Boolean boolean0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$N71bN_ZVODTxvvnLgVvfxkEmFFc(NidOAuthBridgeActivity nidOAuthBridgeActivity0, ActivityResult activityResult0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$fdczyn7XZK91xMheHSlEWgUcX3I(NidOAuthBridgeActivity nidOAuthBridgeActivity0, Boolean boolean0) [...]

    static {
        NidOAuthBridgeActivity.Companion = new Companion(null);
    }

    public NidOAuthBridgeActivity() {
        Function0 function00 = new Function0() {
            final ComponentActivity $this_viewModels;

            {
                this.$this_viewModels = componentActivity0;
                super(0);
            }

            public final Factory invoke() {
                return this.$this_viewModels.getDefaultViewModelProviderFactory();
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        };
        this.viewModel$delegate = new ViewModelLazy(Reflection.getOrCreateKotlinClass(NidOAuthBridgeViewModel.class), new Function0() {
            final ComponentActivity $this_viewModels;

            {
                this.$this_viewModels = componentActivity0;
                super(0);
            }

            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore0 = this.$this_viewModels.getViewModelStore();
                Intrinsics.checkNotNullExpressionValue(viewModelStore0, "viewModelStore");
                return viewModelStore0;
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        }, function00);
        this.progress$delegate = LazyKt.lazy(new Function0() {
            {
                NidOAuthBridgeActivity.this = nidOAuthBridgeActivity0;
                super(0);
            }

            public final NidProgressDialog invoke() {
                return new NidProgressDialog(NidOAuthBridgeActivity.this);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
        ActivityResultLauncher activityResultLauncher0 = this.registerForActivityResult(new StartActivityForResult(), (ActivityResult activityResult0) -> NidOAuthBridgeActivity.naverappLauncher$lambda-0(this, activityResult0));
        Intrinsics.checkNotNullExpressionValue(activityResultLauncher0, "registerForActivityResul…thData(result.data)\n    }");
        this.naverappLauncher = activityResultLauncher0;
        ActivityResultLauncher activityResultLauncher1 = this.registerForActivityResult(new StartActivityForResult(), (ActivityResult activityResult0) -> NidOAuthBridgeActivity.customTabLauncher$lambda-1(this, activityResult0));
        Intrinsics.checkNotNullExpressionValue(activityResultLauncher1, "registerForActivityResul…thData(result.data)\n    }");
        this.customTabLauncher = activityResultLauncher1;
    }

    private static final void customTabLauncher$lambda-1(NidOAuthBridgeActivity nidOAuthBridgeActivity0, ActivityResult activityResult0) {
        Intrinsics.checkNotNullParameter(nidOAuthBridgeActivity0, "this$0");
        nidOAuthBridgeActivity0.getViewModel().isNotForcedFinish();
        nidOAuthBridgeActivity0.requestAccessTokenWithData(activityResult0.getData());
    }

    private final void finishWithErrorResult(Intent intent0) {
        String s = intent0.getStringExtra("oauth_error_code");
        String s1 = intent0.getStringExtra("oauth_error_desc");
        if(s1 == null) {
            s1 = "";
        }
        this.oauthFinish(intent0, NidOAuthErrorCode.INSTANCE.fromString(s), s1);
    }

    private final void finishWithErrorResult(NidOAuthErrorCode nidOAuthErrorCode0) {
        Intent intent0 = new Intent();
        intent0.putExtra("oauth_error_code", nidOAuthErrorCode0.getCode());
        intent0.putExtra("oauth_error_desc", nidOAuthErrorCode0.getDescription());
        this.oauthFinish(intent0, nidOAuthErrorCode0, nidOAuthErrorCode0.getDescription());
    }

    private final NidProgressDialog getProgress() {
        return (NidProgressDialog)this.progress$delegate.getValue();
    }

    private final NidOAuthBridgeViewModel getViewModel() {
        return (NidOAuthBridgeViewModel)this.viewModel$delegate.getValue();
    }

    private final boolean initData() {
        this.finishWithErrorResult(NidOAuthErrorCode.SDK_IS_NOT_INITIALIZED);
        return false;
    }

    private static final void naverappLauncher$lambda-0(NidOAuthBridgeActivity nidOAuthBridgeActivity0, ActivityResult activityResult0) {
        Intrinsics.checkNotNullParameter(nidOAuthBridgeActivity0, "this$0");
        nidOAuthBridgeActivity0.getViewModel().isNotForcedFinish();
        nidOAuthBridgeActivity0.requestAccessTokenWithData(activityResult0.getData());
    }

    private final void oauthFinish(Intent intent0, NidOAuthErrorCode nidOAuthErrorCode0, String s) {
        NidOAuthPreferencesManager.setLastErrorCode(nidOAuthErrorCode0);
        NidOAuthPreferencesManager.setLastErrorDesc(s);
        this.getViewModel().isNotForcedFinish();
        OAuthLoginCallback oAuthLoginCallback0 = NaverIdLoginSDK.INSTANCE.getOauthLoginCallback();
        if(oAuthLoginCallback0 != null) {
            oAuthLoginCallback0.onError(-1, s);
        }
        this.setResult(0, intent0);
        this.finish();
    }

    @Override  // androidx.appcompat.app.AppCompatActivity
    public void onConfigurationChanged(Configuration configuration0) {
        Intrinsics.checkNotNullParameter(configuration0, "newConfig");
        super.onConfigurationChanged(configuration0);
        NidLog.d("NidOAuthBridgeActivity", "called onConfigurationChanged()");
        this.getViewModel().setIsRotated(true);
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        NidLog.d("NidOAuthBridgeActivity", "called onCreate()");
        NaverIdLoginSDK.INSTANCE.init(this);
        if(!this.initData()) {
            return;
        }
        NidLog.d("NidOAuthBridgeActivity", "onCreate() | isLoginActivityStarted : " + this.getViewModel().getIsLoginActivityStarted());
        this.getViewModel().setIsRotated(false);
        if(!this.getViewModel().getIsLoginActivityStarted()) {
            this.getViewModel().startLoginActivity();
            NidLog.d("NidOAuthBridgeActivity", "onCreate() first init.");
            CharSequence charSequence0 = NaverIdLoginSDK.INSTANCE.getRefreshToken();
            if(charSequence0 == null || charSequence0.length() == 0 || this.authType != null && this.authType.length() != 0) {
                this.startLoginActivity();
            }
            else {
                this.getViewModel().refreshToken();
            }
        }
        this.getViewModel().isSuccessRefreshToken().observe(this, (Boolean boolean0) -> NidOAuthBridgeActivity.onCreate$lambda-3(this, boolean0));
        this.getViewModel().isShowProgress().observe(this, (Boolean boolean0) -> NidOAuthBridgeActivity.onCreate$lambda-4(this, boolean0));
    }

    private static final void onCreate$lambda-3(NidOAuthBridgeActivity nidOAuthBridgeActivity0, Boolean boolean0) {
        Intrinsics.checkNotNullParameter(nidOAuthBridgeActivity0, "this$0");
        NidLog.d("NidOAuthBridgeActivity", "isSuccessRefreshToken : " + boolean0);
        Intrinsics.checkNotNullExpressionValue(boolean0, "isSuccess");
        if(boolean0.booleanValue()) {
            nidOAuthBridgeActivity0.getViewModel().isNotForcedFinish();
            OAuthLoginCallback oAuthLoginCallback0 = NaverIdLoginSDK.INSTANCE.getOauthLoginCallback();
            if(oAuthLoginCallback0 != null) {
                oAuthLoginCallback0.onSuccess();
            }
            nidOAuthBridgeActivity0.setResult(-1);
            nidOAuthBridgeActivity0.finish();
            nidOAuthBridgeActivity0.overridePendingTransition(0, 0);
            return;
        }
        nidOAuthBridgeActivity0.startLoginActivity();
    }

    private static final void onCreate$lambda-4(NidOAuthBridgeActivity nidOAuthBridgeActivity0, Boolean boolean0) {
        Intrinsics.checkNotNullParameter(nidOAuthBridgeActivity0, "this$0");
        Intrinsics.checkNotNullExpressionValue(boolean0, "isShowProgress");
        if(boolean0.booleanValue()) {
            nidOAuthBridgeActivity0.getProgress().showProgress(string.naveroauthlogin_string_getting_token);
            return;
        }
        nidOAuthBridgeActivity0.getProgress().hideProgress();
    }

    @Override  // androidx.appcompat.app.AppCompatActivity
    protected void onDestroy() {
        super.onDestroy();
        NidLog.d("NidOAuthBridgeActivity", "called onDestroy()");
        if(this.getViewModel().getIsForceDestroyed() && !this.getViewModel().getIsRotated()) {
            NidOAuthPreferencesManager.setLastErrorCode(NidOAuthErrorCode.ACTIVITY_IS_SINGLE_TASK);
            NidOAuthPreferencesManager.setLastErrorDesc("OAuthLoginActivity is destroyed.");
            OAuthLoginCallback oAuthLoginCallback0 = NaverIdLoginSDK.INSTANCE.getOauthLoginCallback();
            if(oAuthLoginCallback0 != null) {
                oAuthLoginCallback0.onError(-1, "OAuthLoginActivity is destroyed.");
            }
            this.setResult(0);
        }
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onPause() {
        super.onPause();
        NidLog.d("NidOAuthBridgeActivity", "called onPause()");
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onResume() {
        super.onResume();
        NidLog.d("NidOAuthBridgeActivity", "called onResume()");
    }

    private final void requestAccessTokenWithData(Intent intent0) {
        if(intent0 == null) {
            this.finishWithErrorResult(NidOAuthErrorCode.CLIENT_USER_CANCEL);
            return;
        }
        String s = intent0.getStringExtra("oauth_state");
        String s1 = intent0.getStringExtra("oauth_code");
        String s2 = intent0.getStringExtra("oauth_error_code");
        String s3 = intent0.getStringExtra("oauth_error_desc");
        NidOAuthPreferencesManager.INSTANCE.setCode(s1);
        NidOAuthPreferencesManager.INSTANCE.setState(s);
        NidOAuthPreferencesManager.INSTANCE.setErrorCode(s2);
        NidOAuthPreferencesManager.INSTANCE.setErrorDescription(s3);
        if(s1 != null && s1.length() != 0) {
            new NidOAuthLogin().accessToken(this, NaverIdLoginSDK.INSTANCE.getOauthLoginCallback());
            return;
        }
        this.finishWithErrorResult(intent0);
    }

    @Override  // android.app.Activity
    public void setRequestedOrientation(int v) {
        if(Build.VERSION.SDK_INT != 26) {
            super.setRequestedOrientation(v);
        }
    }

    private final void startLoginActivity() {
        NidLog.d("NidOAuthBridgeActivity", "startLoginActivity()");
        switch(WhenMappings.$EnumSwitchMapping$0[NaverIdLoginSDK.INSTANCE.getBehavior().ordinal()]) {
            case 1: {
                if(!this.tryOAuthByNaverapp()) {
                    this.oauthFinish(new Intent(), NidOAuthErrorCode.NO_APP_FOR_AUTHENTICATION, "기기에 네이버앱이 없습니다.");
                    return;
                }
                break;
            }
            case 2: {
                if(!this.tryOAuthByCustomTab() && (!NidApplicationUtil.INSTANCE.isNotCustomTabsAvailable(this) || !NidApplicationUtil.INSTANCE.isExistNaverApp(this) || !this.tryOAuthByNaverapp())) {
                    this.oauthFinish(new Intent(), NidOAuthErrorCode.NO_APP_FOR_AUTHENTICATION, "커스텀탭을 실행할 수 없습니다.");
                    return;
                }
                break;
            }
            case 3: {
                this.startLoginWebviewActivity();
                return;
            }
            case 4: {
                if(!this.tryOAuthByNaverapp() && !this.tryOAuthByCustomTab()) {
                    this.getViewModel().isNotForcedFinish();
                    this.oauthFinish(new Intent(), NidOAuthErrorCode.NO_APP_FOR_AUTHENTICATION, "인증을 진행할 수 있는 앱이 없습니다.");
                    return;
                }
                break;
            }
        }
    }

    @Deprecated(message = "WebView is deprecated")
    private final void startLoginWebviewActivity() {
        Toast.makeText(this, "더이상 인앱브라우저(웹뷰)는 사용할 수 없습니다.(WebView is deprecated)", 0).show();
        this.getViewModel().isNotForcedFinish();
        this.oauthFinish(new Intent(), NidOAuthErrorCode.WEB_VIEW_IS_DEPRECATED, "webView is deprecated");
    }

    private final boolean tryOAuthByCustomTab() {
        Intent intent0 = new Builder(this).setType(Type.CUSTOM_TABS).setAuthType(this.authType).build();
        if(intent0 == null) {
            return false;
        }
        this.customTabLauncher.launch(intent0);
        return true;
    }

    private final boolean tryOAuthByNaverapp() {
        Intent intent0 = new Builder(this).setType(Type.NAVER_APP).setAuthType(this.authType).build();
        if(intent0 == null) {
            return false;
        }
        if(intent0.getData() != null) {
            try {
                this.startActivity(intent0);
                this.getViewModel().isNotForcedFinish();
                OAuthLoginCallback oAuthLoginCallback0 = NaverIdLoginSDK.INSTANCE.getOauthLoginCallback();
                if(oAuthLoginCallback0 != null) {
                    oAuthLoginCallback0.onError(-1, "네이버앱 업데이트가 필요합니다.");
                }
                this.setResult(0);
                this.finish();
                return true;
            }
            catch(ActivityNotFoundException unused_ex) {
                return false;
            }
        }
        this.naverappLauncher.launch(intent0);
        return true;
    }
}

