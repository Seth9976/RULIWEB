package com.ruliweb.www.ruliapp;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DownloadManager.Request;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import com.android.volley.VolleyError;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.Account;
import com.kakao.sdk.user.model.User;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.NidOAuthLogin;
import com.navercorp.nid.profile.NidProfileCallback;
import com.navercorp.nid.profile.data.NidProfile;
import com.navercorp.nid.profile.data.NidProfileResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u00A6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001PB\u0005\u00A2\u0006\u0002\u0010\u0002J\b\u0010.\u001A\u00020/H\u0003J\b\u00100\u001A\u00020/H\u0002J\b\u00101\u001A\u0004\u0018\u000102J\u0010\u00103\u001A\u00020/2\u0006\u00104\u001A\u000205H\u0016J\u0012\u00106\u001A\u00020/2\b\u00107\u001A\u0004\u0018\u000108H\u0015J&\u00109\u001A\u00020/2\b\u0010:\u001A\u0004\u0018\u00010;2\b\u0010<\u001A\u0004\u0018\u00010=2\b\u0010>\u001A\u0004\u0018\u00010?H\u0016J\u001A\u0010@\u001A\u00020\u00132\u0006\u0010A\u001A\u00020 2\b\u0010B\u001A\u0004\u0018\u00010CH\u0016J\u0010\u0010D\u001A\u00020/2\u0006\u0010E\u001A\u00020FH\u0014J\b\u0010G\u001A\u00020/H\u0014J\b\u0010H\u001A\u00020/H\u0014J$\u0010I\u001A\u00020/2\u0006\u0010J\u001A\u00020\u00052\u0012\u0010K\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050LH\u0002J\u0006\u0010M\u001A\u00020/J\u0010\u0010N\u001A\u00020\u00132\u0006\u0010O\u001A\u00020\u0005H\u0002R\u000E\u0010\u0003\u001A\u00020\u0000X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082D\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0005X\u0082.\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082.\u00A2\u0006\u0002\n\u0000R\u001A\u0010\t\u001A\u00020\u0005X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\n\u0010\u000B\"\u0004\b\f\u0010\rR\u0016\u0010\u000E\u001A\n \u0010*\u0004\u0018\u00010\u000F0\u000FX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0013X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\u0005X\u0082D\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0016X\u0082.\u00A2\u0006\u0002\n\u0000R(\u0010\u0017\u001A\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001A0\u0019\u0018\u00010\u0018X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001B\u0010\u001C\"\u0004\b\u001D\u0010\u001ER\u000E\u0010\u001F\u001A\u00020 X\u0082D\u00A2\u0006\u0002\n\u0000R\u000E\u0010!\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\"\u001A\b\u0012\u0004\u0012\u00020\u00050#X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010$\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001A\u0010%\u001A\u00020&X\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001C\u0010+\u001A\u0004\u0018\u00010&X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b,\u0010(\"\u0004\b-\u0010*\u00A8\u0006Q"}, d2 = {"Lcom/ruliweb/www/ruliapp/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "_self", "agentKey", "", "agentString", "btnContainerFragment", "Lcom/ruliweb/www/ruliapp/ButtonContainer;", "cameraPath", "getCameraPath", "()Ljava/lang/String;", "setCameraPath", "(Ljava/lang/String;)V", "cookieManager", "Landroid/webkit/CookieManager;", "kotlin.jvm.PlatformType", "currentLoginType", "darkModeSettings", "", "homeUrl", "mGSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "mWebViewImageUpload", "Landroid/webkit/ValueCallback;", "", "Landroid/net/Uri;", "getMWebViewImageUpload", "()Landroid/webkit/ValueCallback;", "setMWebViewImageUpload", "(Landroid/webkit/ValueCallback;)V", "permissonReqCode", "", "redirectUrl", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "returnUrl", "ruliWebView", "Landroid/webkit/WebView;", "getRuliWebView", "()Landroid/webkit/WebView;", "setRuliWebView", "(Landroid/webkit/WebView;)V", "subWebView", "getSubWebView", "setSubWebView", "changeView", "", "checkPermission", "createImageFile", "Ljava/io/File;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateContextMenu", "menu", "Landroid/view/ContextMenu;", "v", "Landroid/view/View;", "menuInfo", "Landroid/view/ContextMenu$ContextMenuInfo;", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onNewIntent", "intent", "Landroid/content/Intent;", "onPause", "onResume", "socialLoginProcessor", "type", "data", "", "startFind", "urlChecker", "url", "RulijsInterface", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class MainActivity extends AppCompatActivity {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0007R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Lcom/ruliweb/www/ruliapp/MainActivity$RulijsInterface;", "", "nLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "gLauncher", "(Lcom/ruliweb/www/ruliapp/MainActivity;Landroidx/activity/result/ActivityResultLauncher;Landroidx/activity/result/ActivityResultLauncher;)V", "snsLoginHook", "", "type", "", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    final class RulijsInterface {
        private final ActivityResultLauncher gLauncher;
        private final ActivityResultLauncher nLauncher;

        public RulijsInterface(ActivityResultLauncher activityResultLauncher0, ActivityResultLauncher activityResultLauncher1) {
            Intrinsics.checkNotNullParameter(activityResultLauncher0, "nLauncher");
            Intrinsics.checkNotNullParameter(activityResultLauncher1, "gLauncher");
            MainActivity.this = mainActivity0;
            super();
            this.nLauncher = activityResultLauncher0;
            this.gLauncher = activityResultLauncher1;
        }

        @JavascriptInterface
        public final void snsLoginHook(String s) {
            Intrinsics.checkNotNullParameter(s, "type");
            MainActivity.this.currentLoginType = s;
            switch(s) {
                case "google": {
                    GoogleSignInAccount googleSignInAccount0 = GoogleSignIn.getLastSignedInAccount(MainActivity.this._self);
                    if(googleSignInAccount0 != null) {
                        if(googleSignInAccount0.getId() != null && googleSignInAccount0.getEmail() != null) {
                            Pair[] arr_pair = new Pair[3];
                            arr_pair[0] = new Pair("type", MainActivity.this.currentLoginType);
                            String s1 = googleSignInAccount0.getEmail();
                            Intrinsics.checkNotNull(s1);
                            arr_pair[1] = new Pair("email", s1);
                            String s2 = googleSignInAccount0.getId();
                            Intrinsics.checkNotNull(s2);
                            arr_pair[2] = new Pair("id", s2);
                            Map map0 = MapsKt.mapOf(arr_pair);
                            MainActivity.this.socialLoginProcessor("google", map0);
                        }
                        return;
                    }
                    ActivityResultLauncher activityResultLauncher0 = this.gLauncher;
                    GoogleSignInClient googleSignInClient0 = MainActivity.this.mGSignInClient;
                    if(googleSignInClient0 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mGSignInClient");
                        googleSignInClient0 = null;
                    }
                    Intent intent0 = googleSignInClient0.getSignInIntent();
                    Intrinsics.checkNotNullExpressionValue(intent0, "getSignInIntent(...)");
                    activityResultLauncher0.launch(intent0);
                    return;
                }
                case "kakao": {
                    UserApiClient userApiClient0 = UserApiClient.Companion.getInstance();
                    Context context0 = MainActivity.this.getBaseContext();
                    Intrinsics.checkNotNullExpressionValue(context0, "getBaseContext(...)");
                    if(userApiClient0.isKakaoTalkLoginAvailable(context0)) {
                        Context context1 = MainActivity.this.getRuliWebView().getContext();
                        Intrinsics.checkNotNullExpressionValue(context1, "getContext(...)");
                        UserApiClient.loginWithKakaoTalk$default(userApiClient0, context1, 0, null, null, null, new Function2(MainActivity.this) {
                            final UserApiClient $kakaoinst;

                            {
                                this.$kakaoinst = userApiClient0;
                                MainActivity.this = mainActivity0;
                                super(2);
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                this.invoke(((OAuthToken)object0), ((Throwable)object1));
                                return Unit.INSTANCE;
                            }

                            public final void invoke(OAuthToken oAuthToken0, Throwable throwable0) {
                                if(throwable0 != null) {
                                    Log.e("SNSLOGINERROR", "카카오 로그인 실패", throwable0);
                                    return;
                                }
                                if(oAuthToken0 != null) {
                                    com.ruliweb.www.ruliapp.MainActivity.RulijsInterface.snsLoginHook.1.1 mainActivity$RulijsInterface$snsLoginHook$1$10 = new Function2() {
                                        {
                                            MainActivity.this = mainActivity0;
                                            super(2);
                                        }

                                        @Override  // kotlin.jvm.functions.Function2
                                        public Object invoke(Object object0, Object object1) {
                                            this.invoke(((User)object0), ((Throwable)object1));
                                            return Unit.INSTANCE;
                                        }

                                        public final void invoke(User user0, Throwable throwable0) {
                                            if(throwable0 != null) {
                                                Log.e("SNSLOGINERROR", "카카오 로그인 정보 확인 실패", throwable0);
                                                return;
                                            }
                                            if(user0 != null) {
                                                Pair[] arr_pair = {new Pair("type", MainActivity.this.currentLoginType), null, null};
                                                Account account0 = user0.getKakaoAccount();
                                                Intrinsics.checkNotNull(account0);
                                                String s = account0.getEmail();
                                                if(s == null) {
                                                    s = "";
                                                }
                                                arr_pair[1] = new Pair("email", s);
                                                arr_pair[2] = new Pair("id", String.valueOf(user0.getId()));
                                                Map map0 = MapsKt.mapOf(arr_pair);
                                                MainActivity.this.socialLoginProcessor("kakao", map0);
                                            }
                                        }
                                    };
                                    UserApiClient.me$default(this.$kakaoinst, false, mainActivity$RulijsInterface$snsLoginHook$1$10, 1, null);
                                }
                            }
                        }, 30, null);
                        return;
                    }
                    Context context2 = MainActivity.this.getBaseContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getBaseContext(...)");
                    UserApiClient.loginWithKakaoAccount$default(userApiClient0, context2, null, null, null, null, null, new Function2(MainActivity.this) {
                        final UserApiClient $kakaoinst;

                        {
                            this.$kakaoinst = userApiClient0;
                            MainActivity.this = mainActivity0;
                            super(2);
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            this.invoke(((OAuthToken)object0), ((Throwable)object1));
                            return Unit.INSTANCE;
                        }

                        public final void invoke(OAuthToken oAuthToken0, Throwable throwable0) {
                            if(throwable0 != null) {
                                Log.e("SNSLOGINERROR", "카카오 로그인 실패", throwable0);
                                return;
                            }
                            if(oAuthToken0 != null) {
                                com.ruliweb.www.ruliapp.MainActivity.RulijsInterface.snsLoginHook.2.1 mainActivity$RulijsInterface$snsLoginHook$2$10 = new Function2() {
                                    {
                                        MainActivity.this = mainActivity0;
                                        super(2);
                                    }

                                    @Override  // kotlin.jvm.functions.Function2
                                    public Object invoke(Object object0, Object object1) {
                                        this.invoke(((User)object0), ((Throwable)object1));
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(User user0, Throwable throwable0) {
                                        if(throwable0 != null) {
                                            Log.e("SNSLOGINERROR", "카카오 로그인 정보 확인 실패", throwable0);
                                            return;
                                        }
                                        if(user0 != null) {
                                            Pair[] arr_pair = {new Pair("type", MainActivity.this.currentLoginType), null, null};
                                            Account account0 = user0.getKakaoAccount();
                                            Intrinsics.checkNotNull(account0);
                                            String s = account0.getEmail();
                                            if(s == null) {
                                                s = "";
                                            }
                                            arr_pair[1] = new Pair("email", s);
                                            arr_pair[2] = new Pair("id", String.valueOf(user0.getId()));
                                            Map map0 = MapsKt.mapOf(arr_pair);
                                            MainActivity.this.socialLoginProcessor("kakao", map0);
                                        }
                                    }
                                };
                                UserApiClient.me$default(this.$kakaoinst, false, mainActivity$RulijsInterface$snsLoginHook$2$10, 1, null);
                            }
                        }
                    }, 62, null);
                    return;
                }
                case "naver": {
                    Context context3 = MainActivity.this.getBaseContext();
                    Intrinsics.checkNotNullExpressionValue(context3, "getBaseContext(...)");
                    NaverIdLoginSDK.INSTANCE.authenticate(context3, this.nLauncher);
                }
            }
        }
    }

    private final MainActivity _self;
    private final String agentKey;
    private String agentString;
    private ButtonContainer btnContainerFragment;
    private String cameraPath;
    private final CookieManager cookieManager;
    private String currentLoginType;
    private boolean darkModeSettings;
    private final String homeUrl;
    private GoogleSignInClient mGSignInClient;
    private ValueCallback mWebViewImageUpload;
    private final int permissonReqCode;
    private String redirectUrl;
    private final ActivityResultLauncher requestPermissionLauncher;
    private String returnUrl;
    public WebView ruliWebView;
    private WebView subWebView;

    // 检测为 Lambda 实现
    public static boolean $r8$lambda$4qmZxBQCEi96KL1JB6eYn9paHWE(String s, String s1, MainActivity mainActivity0, MenuItem menuItem0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$5ELOKPUQre9XHbL3vfz4iHcnmT4(SharedPreferences.Editor sharedPreferences$Editor0, DialogInterface dialogInterface0, int v) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$GUacPZQ-2I_T6iIRG9VqhdGx68E(MainActivity mainActivity0, ActivityResult activityResult0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$NBmP48anI1emx_CITLU1lr3psaM(MainActivity mainActivity0, Task task0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$S1ko5icMumJCRFyxrBpyyqmbc6E(MainActivity mainActivity0, DialogInterface dialogInterface0, int v) [...]

    // 检测为 Lambda 实现
    public static boolean $r8$lambda$_jlRpbrfh7VQa1vjCIzz008u9vs(View view0, View view1, int v, KeyEvent keyEvent0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$hQfSEAFmRJaejB0twX2bLhSAcYc(SharedPreferences.Editor sharedPreferences$Editor0, DialogInterface dialogInterface0, int v) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$liKX8_sTTJQRcNZnt-SNC831174(MainActivity mainActivity0, String s, String s1, String s2, String s3, long v) [...]

    public static void $r8$lambda$mAjFrKLwHAPZddAvccGU4siL09M(boolean z) {
    }

    // 检测为 Lambda 实现
    public static void $r8$lambda$miTvuyed4UXgMcb0XSp5ayJTGec(MainActivity mainActivity0, ActivityResult activityResult0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$o8rtD3VuRHZgDcSHQkHiSZgHsNE(MainActivity mainActivity0, ActivityResult activityResult0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$sZQlEjh6caiLloqeLZuWphhz6Og(MainActivity mainActivity0, EditText editText0, View view0, View view1) [...]

    public MainActivity() {
        this.cookieManager = CookieManager.getInstance();
        this.homeUrl = "https://m.ruliweb.com";
        this.agentKey = " ruliweb_android_app";
        this.returnUrl = "";
        this.redirectUrl = "https://m.ruliweb.com";
        this.cameraPath = "";
        this.currentLoginType = "";
        this.permissonReqCode = 99;
        this._self = this;
        this.requestPermissionLauncher = this.registerForActivityResult(new RequestPermission(), new MainActivity..ExternalSyntheticLambda10());
    }

    private final void changeView() {
        View view0 = this.findViewById(0x7F090047);  // id:activity_main
        Intrinsics.checkNotNullExpressionValue(view0, "findViewById(...)");
        ((ImageView)view0.findViewById(0x7F0900F5)).setVisibility(4);  // id:imageView
        ((WebView)view0.findViewById(0x7F090237)).setVisibility(0);  // id:wv_main
        this.overridePendingTransition(0x10A0000, 0x10A0001);
        ButtonContainer buttonContainer0 = this.btnContainerFragment;
        if(buttonContainer0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnContainerFragment");
            buttonContainer0 = null;
        }
        LinearLayout linearLayout0 = buttonContainer0.getWrapper();
        if(linearLayout0.getVisibility() == 8) {
            linearLayout0.setVisibility(0);
        }
    }

    private final void checkPermission() {
        if(ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != 0 || Build.VERSION.SDK_INT < 30 && ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            AlertDialog.Builder alertDialog$Builder0 = new AlertDialog.Builder(this);
            alertDialog$Builder0.setTitle("루리웹닷컴 앱 이용을 위한 권한 안내");
            alertDialog$Builder0.setMessage("필수 접근 권한\n- 카메라 : 게시물 작성시 카메라 촬영 후 이미지 첨부\n- 저장용량: 게시물 작성시 기기에 저장된 이미지 읽기\n- 네트워크: 인터넷 연결\n- 알림: 사용자 활동에 따른 댓글 및 알림 수신\n\n* 휴대폰 설정 > 애플리케이션 관리 > 루리웹닷컴 > [앱 권한] 에서도 설정 변경 하실수 있습니다.").setCancelable(false).setPositiveButton("확인", (DialogInterface dialogInterface0, int v) -> MainActivity.checkPermission$lambda$12(this, dialogInterface0, v));
            alertDialog$Builder0.create().show();
        }
        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        if(sharedPreferences0.contains("darkModeSettings")) {
            this.darkModeSettings = sharedPreferences0.getBoolean("darkModeSettings", false);
        }
        if(!sharedPreferences0.contains("allow_push")) {
            SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
            AlertDialog.Builder alertDialog$Builder1 = new AlertDialog.Builder(this);
            alertDialog$Builder1.setTitle("루리웹닷컴 앱 이용을 위한 권한 안내");
            alertDialog$Builder1.setMessage("- 알림: 사용자 활동에 따른 댓글 및 알림 수신\n\n* 휴대폰 설정 > 애플리케이션 관리 > 루리웹닷컴 > [앱 권한] 에서도 설정 변경 하실수 있습니다.").setCancelable(false).setPositiveButton("동의", (DialogInterface dialogInterface0, int v) -> MainActivity.checkPermission$lambda$13(sharedPreferences$Editor0, dialogInterface0, v)).setNegativeButton("취소", (DialogInterface dialogInterface0, int v) -> MainActivity.checkPermission$lambda$14(sharedPreferences$Editor0, dialogInterface0, v));
            alertDialog$Builder1.create().show();
            if(Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") != 0 && !this.shouldShowRequestPermissionRationale("android.permission.POST_NOTIFICATIONS")) {
                this.requestPermissionLauncher.launch("android.permission.POST_NOTIFICATIONS");
            }
        }
    }

    private static final void checkPermission$lambda$12(MainActivity mainActivity0, DialogInterface dialogInterface0, int v) {
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        dialogInterface0.cancel();
        ActivityCompat.requestPermissions(mainActivity0, new String[]{"android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE"}, mainActivity0.permissonReqCode);
    }

    private static final void checkPermission$lambda$13(SharedPreferences.Editor sharedPreferences$Editor0, DialogInterface dialogInterface0, int v) {
        dialogInterface0.cancel();
        sharedPreferences$Editor0.putBoolean("allow_push", true);
        sharedPreferences$Editor0.apply();
    }

    private static final void checkPermission$lambda$14(SharedPreferences.Editor sharedPreferences$Editor0, DialogInterface dialogInterface0, int v) {
        dialogInterface0.cancel();
        sharedPreferences$Editor0.putBoolean("allow_push", false);
        sharedPreferences$Editor0.apply();
    }

    public final File createImageFile() {
        File file0 = File.createTempFile("JPEG_20250906_044501_", ".jpg", this.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        String s = file0.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(s, "getAbsolutePath(...)");
        this.cameraPath = s;
        return file0;
    }

    public final String getCameraPath() {
        return this.cameraPath;
    }

    public final ValueCallback getMWebViewImageUpload() {
        return this.mWebViewImageUpload;
    }

    public final WebView getRuliWebView() {
        WebView webView0 = this.ruliWebView;
        if(webView0 != null) {
            return webView0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("ruliWebView");
        return null;
    }

    public final WebView getSubWebView() {
        return this.subWebView;
    }

    @Override  // androidx.appcompat.app.AppCompatActivity
    public void onConfigurationChanged(Configuration configuration0) {
        Intrinsics.checkNotNullParameter(configuration0, "newConfig");
        super.onConfigurationChanged(configuration0);
        this.getRuliWebView().reload();
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onCreate(Bundle bundle0) {
        String s1;
        super.onCreate(bundle0);
        EdgeToEdge.enable$default(this, null, null, 3, null);
        int v = this.getApplicationContext().getResources().getConfiguration().uiMode & 0x30;
        if(v == 0) {
            this.setTheme(0x7F130125);  // style:LightTheme
        }
        else {
            switch(v) {
                case 16: {
                    this.setTheme(0x7F130125);  // style:LightTheme
                    break;
                }
                case 0x20: {
                    this.setTheme(0x7F130124);  // style:DarkTheme
                }
            }
        }
        this.setContentView(0x7F0C001C);  // layout:activity_main
        Window window0 = this.getWindow();
        Intrinsics.checkNotNullExpressionValue(window0, "getWindow(...)");
        Utils.Companion.setUpViewLayout(window0);
        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
        if(sharedPreferences0.contains("darkModeSettings")) {
            this.darkModeSettings = sharedPreferences0.getBoolean("darkModeSettings", false);
        }
        if(v == 0x20) {
            ((ImageView)this.findViewById(0x7F0900F5)).setImageResource(0x7F080115);  // id:imageView
        }
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener((Task task0) -> MainActivity.onCreate$lambda$1(this, task0));
        Object object0 = this.getSystemService("notification");
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.app.NotificationManager");
        Intrinsics.checkNotNullExpressionValue("ruli_noti_channel", "getString(...)");
        if(Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager)object0).createNotificationChannel(MainActivity..ExternalSyntheticApiModelOutline0.m("ruli_noti_channel", "RuliappNotiChannel", 4));
        }
        this.startService(new Intent(this, RuliMessagingService.class));
        View view0 = this.findViewById(0x7F090237);  // id:wv_main
        Intrinsics.checkNotNullExpressionValue(view0, "findViewById(...)");
        this.setRuliWebView(((WebView)view0));
        this.getRuliWebView().getSettings().setJavaScriptEnabled(true);
        this.getRuliWebView().getSettings().setLoadsImagesAutomatically(true);
        this.getRuliWebView().getSettings().setLoadWithOverviewMode(true);
        this.getRuliWebView().getSettings().setUseWideViewPort(true);
        this.getRuliWebView().getSettings().setDomStorageEnabled(true);
        this.getRuliWebView().getSettings().setSupportZoom(true);
        this.getRuliWebView().getSettings().setBuiltInZoomControls(true);
        String s = (String)Utils.Companion.checkDeviceType(this).get("Type");
        if(s == null) {
            s = "Smartphone";
        }
        this.agentString = Intrinsics.areEqual(s, "Smartphone") ? this.getRuliWebView().getSettings().getUserAgentString() + this.agentKey + "_smartphone" : this.getRuliWebView().getSettings().getUserAgentString() + this.agentKey + "_tablet";
        this.getRuliWebView().getSettings().setSupportMultipleWindows(true);
        this.getRuliWebView().getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.getRuliWebView().getSettings().setCacheMode(-1);
        WebView.setWebContentsDebuggingEnabled(true);
        Timer timer0 = new Timer();
        timer0.schedule(new TimerTask() {
            // 检测为 Lambda 实现
            public static void $r8$lambda$VFsS-_Z0nh7i3ZIGnkYGyMKdSNA(MainActivity mainActivity0) [...]

            @Override
            public void run() {
                MainActivity.onCreate.timerTask.1..ExternalSyntheticLambda0 mainActivity$onCreate$timerTask$1$$ExternalSyntheticLambda00 = () -> com.ruliweb.www.ruliapp.MainActivity.onCreate.timerTask.1.run$lambda$0(MainActivity.this);
                MainActivity.this.runOnUiThread(mainActivity$onCreate$timerTask$1$$ExternalSyntheticLambda00);
            }

            private static final void run$lambda$0(MainActivity mainActivity0) {
                Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
                Log.d("timer end", "timer call changeView");
                ButtonContainer buttonContainer0 = mainActivity0.btnContainerFragment;
                if(buttonContainer0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("btnContainerFragment");
                    buttonContainer0 = null;
                }
                if(buttonContainer0.isAdded()) {
                    mainActivity0.changeView();
                }
            }
        }, 3000L);
        Context context0 = this.getBaseContext();
        Intrinsics.checkNotNullExpressionValue(context0, "getBaseContext(...)");
        NaverIdLoginSDK.INSTANCE.initialize(context0, "B0BVTymrSDRdQNSycodn", "QOp6yzeQqA", "루리웹");
        ActivityResultLauncher activityResultLauncher0 = this.registerForActivityResult(new StartActivityForResult(), (ActivityResult activityResult0) -> MainActivity.onCreate$lambda$3(this, activityResult0));
        KakaoSdk.init$default(this, "297f0417c95dd3ea3dd26bf6e5989ba6", null, null, null, null, null, 0x7C, null);
        GoogleSignInOptions googleSignInOptions0 = new Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        Intrinsics.checkNotNullExpressionValue(googleSignInOptions0, "build(...)");
        GoogleSignInClient googleSignInClient0 = GoogleSignIn.getClient(this, googleSignInOptions0);
        Intrinsics.checkNotNullExpressionValue(googleSignInClient0, "getClient(...)");
        this.mGSignInClient = googleSignInClient0;
        ActivityResultLauncher activityResultLauncher1 = this.registerForActivityResult(new StartActivityForResult(), (ActivityResult activityResult0) -> MainActivity.onCreate$lambda$4(this, activityResult0));
        this.cookieManager.setAcceptCookie(true);
        this.getRuliWebView().addJavascriptInterface(new RulijsInterface(this, activityResultLauncher0, activityResultLauncher1), "RuliAppInterface");
        this.getRuliWebView().setWebViewClient(new WebViewClient() {
            public static void $r8$lambda$HEgPR_iCqTrvce65qneB97SrRbg(String s) {
            }

            // 检测为 Lambda 实现
            public static void $r8$lambda$neYwySKbvJ3rZm4s52pCAgFsVUw(SharedPreferences sharedPreferences0, MainActivity mainActivity0, String s) [...]

            // 检测为 Lambda 实现
            public static void $r8$lambda$pd_akyISdFCPrGGPi7KlJbk9HO4(SharedPreferences sharedPreferences0, MainActivity mainActivity0, String s) [...]

            public static void $r8$lambda$rQBc5hefqvLIOv5XxTWTvPk9xDY(String s) {
            }

            @Override  // android.webkit.WebViewClient
            public void onPageCommitVisible(WebView webView0, String s) {
                super.onPageCommitVisible(webView0, s);
                if((timer0.getApplicationContext().getResources().getConfiguration().uiMode & 0x30) == 0x20) {
                    Intrinsics.checkNotNull(webView0);
                    webView0.evaluateJavascript("document.body.classList.add(\'dark_mode\')", new MainActivity.onCreate.3..ExternalSyntheticLambda3());
                }
                Intrinsics.checkNotNull(webView0);
                webView0.evaluateJavascript("if(document.querySelector(\'#is_logged\')) {document.querySelector(\'#is_logged\').value} else {\'\'}", (String s) -> com.ruliweb.www.ruliapp.MainActivity.onCreate.3.onPageCommitVisible$lambda$2(this.$sharedPreferences, timer0, s));
            }

            private static final void onPageCommitVisible$lambda$1(String s) {
            }

            private static final void onPageCommitVisible$lambda$2(SharedPreferences sharedPreferences0, MainActivity mainActivity0, String s) {
                Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
                Intrinsics.checkNotNull(s);
                String s1 = StringsKt.replace$default(s, "\"", "", false, 4, null);
                try {
                    String s2 = sharedPreferences0.getString("current_member_srl", "");
                    Intrinsics.checkNotNull(s2);
                }
                catch(ClassCastException unused_ex) {
                    if(!Intrinsics.areEqual("0", s1)) {
                        sharedPreferences0.edit().putString("current_member_srl", s1).apply();
                        Context context0 = mainActivity0.getBaseContext();
                        Intrinsics.checkNotNullExpressionValue(context0, "getBaseContext(...)");
                        boolean z = Intrinsics.areEqual(s1, "0");
                        Utils.Companion.setAlarmOptions(context0, !z);
                        return;
                    }
                    return;
                }
                catch(Throwable throwable0) {
                    if(!Intrinsics.areEqual("0", s1)) {
                        sharedPreferences0.edit().putString("current_member_srl", s1).apply();
                        Context context1 = mainActivity0.getBaseContext();
                        Intrinsics.checkNotNullExpressionValue(context1, "getBaseContext(...)");
                        boolean z1 = Intrinsics.areEqual(s1, "0");
                        Utils.Companion.setAlarmOptions(context1, !z1);
                    }
                    throw throwable0;
                }
                if(!Intrinsics.areEqual(s2, s1)) {
                    sharedPreferences0.edit().putString("current_member_srl", s1).apply();
                    Context context2 = mainActivity0.getBaseContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getBaseContext(...)");
                    boolean z2 = Intrinsics.areEqual(s1, "0");
                    Utils.Companion.setAlarmOptions(context2, !z2);
                }
            }

            @Override  // android.webkit.WebViewClient
            public void onPageFinished(WebView webView0, String s) {
                super.onPageFinished(webView0, s);
                ButtonContainer buttonContainer0 = null;
                if(s != null && StringsKt.contains$default(s, "member/login", false, 2, null)) {
                    Intrinsics.checkNotNull(webView0);
                    webView0.evaluateJavascript("const naverLogin = document.getElementById(\"login_naver\")\nconst kakaoLogin = document.getElementById(\"login_kakao\")\nconst googleLogin = document.getElementById(\"login_google\")\n\nnaverLogin.href = \'javascript:RuliAppInterface.snsLoginHook(\"naver\")\'\nkakaoLogin.href = \'javascript:RuliAppInterface.snsLoginHook(\"kakao\")\'\ngoogleLogin.href = \'javascript:RuliAppInterface.snsLoginHook(\"google\")\'", new MainActivity.onCreate.3..ExternalSyntheticLambda1());
                }
                if(webView0 != null && webView0.getSettings().getCacheMode() != -1) {
                    timer0.getRuliWebView().getSettings().setCacheMode(-1);
                }
                timer0.cookieManager.flush();
                ButtonContainer buttonContainer1 = timer0.btnContainerFragment;
                if(buttonContainer1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("btnContainerFragment");
                }
                else {
                    buttonContainer0 = buttonContainer1;
                }
                if(buttonContainer0.isAdded()) {
                    Log.d("load end", "page load end and call changeView");
                    timer0.changeView();
                    sharedPreferences0.cancel();
                }
            }

            private static final void onPageFinished$lambda$0(String s) {
            }

            @Override  // android.webkit.WebViewClient
            public void onReceivedError(WebView webView0, WebResourceRequest webResourceRequest0, WebResourceError webResourceError0) {
                String s;
                super.onReceivedError(webView0, webResourceRequest0, webResourceError0);
                if(webResourceError0 == null) {
                    s = "don\'t know";
                }
                else {
                    CharSequence charSequence0 = webResourceError0.getDescription();
                    if(charSequence0 == null) {
                        s = "don\'t know";
                    }
                    else {
                        s = charSequence0.toString();
                        if(s == null) {
                            s = "don\'t know";
                        }
                    }
                }
                Log.e("webViewError", s);
            }

            private final boolean overrideUrlLoading(WebView webView0, String s) {
                String s1 = null;
                if(webView0 != null && s != null) {
                    if(StringsKt.contains$default(s, "http://", false, 2, null)) {
                        s = StringsKt.replace$default(s, "http://", "https://", false, 4, null);
                    }
                    if(StringsKt.startsWith$default(s, "intent:", false, 2, null)) {
                        Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse(StringsKt.replace$default(s, "intent:", "", false, 4, null)));
                        String s2 = intent0.getPackage();
                        if(s2 != null && timer0.getPackageManager().getLaunchIntentForPackage(s2) == null) {
                            intent0 = new Intent("android.intent.action.VIEW");
                            intent0.setData(Uri.parse(("market://details?id=" + s2)));
                        }
                        try {
                            intent0.addCategory("android.intent.category.BROWSABLE");
                            intent0.setComponent(null);
                            intent0.setSelector(null);
                            timer0.startActivity(intent0);
                        }
                        catch(ActivityNotFoundException activityNotFoundException0) {
                            Log.e("activity error: ", activityNotFoundException0.toString());
                        }
                        return true;
                    }
                    if(timer0.urlChecker(s)) {
                        if(StringsKt.contains$default(s, "accounts.google.com", false, 2, null)) {
                            timer0.getRuliWebView().getSettings().setUserAgentString(timer0.agentKey);
                        }
                        else {
                            WebSettings webSettings0 = timer0.getRuliWebView().getSettings();
                            String s3 = timer0.agentString;
                            if(s3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("agentString");
                            }
                            else {
                                s1 = s3;
                            }
                            webSettings0.setUserAgentString(s1);
                        }
                        webView0.evaluateJavascript("if(document.querySelector(\'#is_logged\')) {document.querySelector(\'#is_logged\').value} else {\'0\'}", (String s) -> com.ruliweb.www.ruliapp.MainActivity.onCreate.3.overrideUrlLoading$lambda$3(this.$sharedPreferences, timer0, s));
                        webView0.loadUrl(s);
                        return true;
                    }
                    Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse(s));
                    intent1.addCategory("android.intent.category.BROWSABLE");
                    intent1.setComponent(null);
                    intent1.setSelector(null);
                    if(intent1.resolveActivity(timer0.getPackageManager()) != null) {
                        webView0.getContext().startActivity(intent1);
                    }
                    return true;
                }
                return false;
            }

            private static final void overrideUrlLoading$lambda$3(SharedPreferences sharedPreferences0, MainActivity mainActivity0, String s) {
                Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
                Intrinsics.checkNotNull(s);
                String s1 = StringsKt.replace$default(s, "\"", "", false, 4, null);
                try {
                    String s2 = sharedPreferences0.getString("current_member_srl", "");
                    Intrinsics.checkNotNull(s2);
                }
                catch(ClassCastException unused_ex) {
                    if(!Intrinsics.areEqual("0", s1)) {
                        sharedPreferences0.edit().putString("current_member_srl", s1).apply();
                        Context context0 = mainActivity0.getBaseContext();
                        Intrinsics.checkNotNullExpressionValue(context0, "getBaseContext(...)");
                        boolean z = Intrinsics.areEqual(s1, "0");
                        Utils.Companion.setAlarmOptions(context0, !z);
                        return;
                    }
                    return;
                }
                catch(Throwable throwable0) {
                    if(!Intrinsics.areEqual("0", s1)) {
                        sharedPreferences0.edit().putString("current_member_srl", s1).apply();
                        Context context1 = mainActivity0.getBaseContext();
                        Intrinsics.checkNotNullExpressionValue(context1, "getBaseContext(...)");
                        boolean z1 = Intrinsics.areEqual(s1, "0");
                        Utils.Companion.setAlarmOptions(context1, !z1);
                    }
                    throw throwable0;
                }
                if(!Intrinsics.areEqual(s2, s1)) {
                    sharedPreferences0.edit().putString("current_member_srl", s1).apply();
                    Context context2 = mainActivity0.getBaseContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getBaseContext(...)");
                    boolean z2 = Intrinsics.areEqual(s1, "0");
                    Utils.Companion.setAlarmOptions(context2, !z2);
                }
            }

            @Override  // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView0, WebResourceRequest webResourceRequest0) {
                String s3;
                Intrinsics.checkNotNullParameter(webResourceRequest0, "request");
                String s = webResourceRequest0.getUrl().toString();
                Intrinsics.checkNotNullExpressionValue(s, "toString(...)");
                String s1 = "https://m.ruliweb.com";
                if(new Regex("(user|bbs)\\.ruliweb\\.com/member/login\\?").containsMatchIn(s)) {
                    Log.d("view? test", String.valueOf((webView0 == null ? null : webView0.getUrl())));
                    MainActivity mainActivity0 = timer0;
                    String s2 = webView0 == null ? null : webView0.getUrl();
                    if(s2 == null) {
                        s2 = "https://m.ruliweb.com";
                    }
                    mainActivity0.returnUrl = s2;
                    CookieManager cookieManager0 = timer0.cookieManager;
                    if(webView0 == null) {
                        s3 = "https://m.ruliweb.com";
                    }
                    else {
                        s3 = webView0.getUrl();
                        if(s3 == null) {
                            s3 = "https://m.ruliweb.com";
                        }
                    }
                    cookieManager0.setCookie("login_redirect_url", s3);
                }
                if(StringsKt.contains$default(s, "ruliweb.com/member/logout", false, 2, null)) {
                    timer0.getRuliWebView().clearCache(true);
                }
                if(!StringsKt.contains$default(s, "member", false, 2, null)) {
                    CookieManager cookieManager1 = timer0.cookieManager;
                    if(webView0 != null) {
                        String s4 = webView0.getUrl();
                        if(s4 != null) {
                            s1 = s4;
                        }
                    }
                    cookieManager1.setCookie("login_redirect_url", s1);
                }
                return this.overrideUrlLoading(webView0, s);
            }
        });
        ActivityResultLauncher activityResultLauncher2 = this.registerForActivityResult(new StartActivityForResult(), (ActivityResult activityResult0) -> MainActivity.onCreate$lambda$7(this, activityResult0));
        this.getRuliWebView().setDownloadListener((String s, String s1, String s2, String s3, long v) -> MainActivity.onCreate$lambda$8(this, s, s1, s2, s3, v));
        this.getRuliWebView().setWebChromeClient(new WebChromeClient() {
            @Override  // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage0) {
                return true;
            }

            @Override  // android.webkit.WebChromeClient
            public boolean onCreateWindow(WebView webView0, boolean z, boolean z1, Message message0) {
                Intrinsics.checkNotNullParameter(webView0, "view");
                Intrinsics.checkNotNullParameter(message0, "resultMsg");
                Message message1 = webView0.getHandler().obtainMessage();
                Intrinsics.checkNotNullExpressionValue(message1, "obtainMessage(...)");
                webView0.requestFocusNodeHref(message1);
                String s = message1.getData().getString("url");
                String s1 = s == null || !StringsKt.contains$default(s, "http://", false, 2, null) ? s : StringsKt.replace$default(s, "http://", "https://", false, 4, null);
                if(s1 != null && activityResultLauncher2.urlChecker(s1)) {
                    activityResultLauncher2.getRuliWebView().loadUrl(s1);
                    return false;
                }
                if(s != null && !StringsKt.contains$default(s, "ok-name.co.kr", false, 2, null)) {
                    Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse(s));
                    activityResultLauncher2.startActivity(intent0);
                    return false;
                }
                WebView webView1 = new WebView(activityResultLauncher2);
                activityResultLauncher2.setSubWebView(webView1);
                WebView webView2 = activityResultLauncher2.getSubWebView();
                Intrinsics.checkNotNull(webView2);
                WebSettings webSettings0 = webView2.getSettings();
                Intrinsics.checkNotNullExpressionValue(webSettings0, "getSettings(...)");
                webSettings0.setJavaScriptEnabled(true);
                webSettings0.setLoadWithOverviewMode(true);
                webSettings0.setUseWideViewPort(true);
                WebView webView3 = activityResultLauncher2.getSubWebView();
                Intrinsics.checkNotNull(webView3);
                webView3.setInitialScale(90);
                webSettings0.setSupportZoom(true);
                webSettings0.setBuiltInZoomControls(true);
                Map map0 = Utils.Companion.checkDeviceType(activityResultLauncher2);
                String s2 = (String)map0.get("Width");
                Double double0 = s2 == null ? 10 : Double.parseDouble(s2);
                int v = double0.intValue();
                String s3 = (String)map0.get("Height");
                Double double1 = s3 == null ? 10 : Double.parseDouble(s3);
                FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(v - 10, double1.intValue() - 10);
                WebView webView4 = activityResultLauncher2.getSubWebView();
                Intrinsics.checkNotNull(webView4);
                webView4.setLayoutParams(frameLayout$LayoutParams0);
                Dialog dialog0 = new Dialog(activityResultLauncher2);
                WebView webView5 = activityResultLauncher2.getSubWebView();
                Intrinsics.checkNotNull(webView5);
                dialog0.setContentView(webView5);
                Window window0 = dialog0.getWindow();
                Intrinsics.checkNotNull(window0);
                WindowManager.LayoutParams windowManager$LayoutParams0 = window0.getAttributes();
                Intrinsics.checkNotNullExpressionValue(windowManager$LayoutParams0, "getAttributes(...)");
                windowManager$LayoutParams0.width = -1;
                windowManager$LayoutParams0.height = -1;
                Window window1 = dialog0.getWindow();
                Intrinsics.checkNotNull(window1);
                window1.setAttributes(windowManager$LayoutParams0);
                activityResultLauncher2.registerForContextMenu(activityResultLauncher2.getSubWebView());
                dialog0.show();
                WebView webView6 = activityResultLauncher2.getSubWebView();
                Intrinsics.checkNotNull(webView6);
                webView6.setWebChromeClient(new WebChromeClient() {
                    @Override  // android.webkit.WebChromeClient
                    public void onCloseWindow(WebView webView0) {
                        Intrinsics.checkNotNullParameter(webView0, "window");
                        MainActivity.this.dismiss();
                        MainActivity.this.unregisterForContextMenu(MainActivity.this.getSubWebView());
                    }
                });
                WebView webView7 = activityResultLauncher2.getSubWebView();
                Intrinsics.checkNotNull(webView7);
                webView7.setWebViewClient(new com.ruliweb.www.ruliapp.MainActivity.onCreate.5.onCreateWindow.2());
                Object object0 = message0.obj;
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.webkit.WebView.WebViewTransport");
                ((WebView.WebViewTransport)object0).setWebView(activityResultLauncher2.getSubWebView());
                message0.sendToTarget();
                return true;

                @Metadata(d1 = {"\u0000\u001D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/ruliweb/www/ruliapp/MainActivity$onCreate$5$onCreateWindow$2", "Landroid/webkit/WebViewClient;", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "request", "Landroid/webkit/WebResourceRequest;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
                public final class com.ruliweb.www.ruliapp.MainActivity.onCreate.5.onCreateWindow.2 extends WebViewClient {
                    @Override  // android.webkit.WebViewClient
                    public boolean shouldOverrideUrlLoading(WebView webView0, WebResourceRequest webResourceRequest0) {
                        Intrinsics.checkNotNullParameter(webView0, "view");
                        Intrinsics.checkNotNullParameter(webResourceRequest0, "request");
                        return false;
                    }
                }

            }

            @Override  // android.webkit.WebChromeClient
            public boolean onShowFileChooser(WebView webView0, ValueCallback valueCallback0, WebChromeClient.FileChooserParams webChromeClient$FileChooserParams0) {
                Intrinsics.checkNotNullParameter(webView0, "webView");
                Intrinsics.checkNotNullParameter(valueCallback0, "filePathCallback");
                Intrinsics.checkNotNullParameter(webChromeClient$FileChooserParams0, "fileChooserParams");
                super.onShowFileChooser(webView0, valueCallback0, webChromeClient$FileChooserParams0);
                try {
                    activityResultLauncher2.setMWebViewImageUpload(valueCallback0);
                    Intent intent0 = new Intent("android.media.action.IMAGE_CAPTURE");
                    Intent[] arr_intent = null;
                    if(intent0.resolveActivity(activityResultLauncher2.getPackageManager()) != null) {
                        File file0 = activityResultLauncher2.createImageFile();
                        intent0.putExtra("PhotoPath", activityResultLauncher2.getCameraPath());
                        if(file0 == null) {
                            intent0 = null;
                        }
                        else {
                            String s = file0.getAbsolutePath();
                            activityResultLauncher2.setCameraPath("file:" + s);
                            intent0.putExtra("output", FileProvider.getUriForFile(activityResultLauncher2.getBaseContext(), "com.ruliweb.www.ruliapp", file0));
                        }
                    }
                    Intent intent1 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent1.setType("image/* video/*");
                    String s1 = activityResultLauncher2.getRuliWebView().getUrl();
                    Intrinsics.checkNotNull(s1);
                    if(StringsKt.contains$default(s1, "write", false, 2, null)) {
                        intent1.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                    }
                    else {
                        String s2 = activityResultLauncher2.getRuliWebView().getUrl();
                        Intrinsics.checkNotNull(s2);
                        if(StringsKt.contains$default(s2, "modify", false, 2, null)) {
                            intent1.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
                        }
                    }
                    if(intent0 == null) {
                        Intrinsics.checkNotNull(null);
                    }
                    else {
                        arr_intent = new Intent[]{intent0};
                    }
                    Intent intent2 = new Intent("android.intent.action.CHOOSER");
                    intent2.putExtra("android.intent.extra.INTENT", intent1);
                    intent2.putExtra("android.intent.extra.TITLE", "사용할 앱을 선택해주세요.");
                    intent2.putExtra("android.intent.extra.INITIAL_INTENTS", arr_intent);
                    this.$launcher.launch(intent2);
                }
                catch(Exception exception0) {
                    String s3 = exception0.getMessage();
                    if(s3 == null) {
                        s3 = "????";
                    }
                    Log.e("onshowfilechooserERROR", s3, exception0);
                }
                return true;
            }
        });
        Fragment fragment0 = this.getSupportFragmentManager().findFragmentById(0x7F090070);  // id:btnContainerFragment
        Intrinsics.checkNotNull(fragment0, "null cannot be cast to non-null type com.ruliweb.www.ruliapp.ButtonContainer");
        this.btnContainerFragment = (ButtonContainer)fragment0;
        this.registerForContextMenu(this.getRuliWebView());
        if(this.getIntent() != null) {
            if(this.getIntent().hasExtra("linkTo")) {
                s1 = this.getIntent().getStringExtra("linkTo");
                if(s1 == null) {
                    s1 = this.homeUrl;
                }
            }
            else if(this.getIntent().hasExtra("link")) {
                s1 = this.getIntent().getStringExtra("link");
                if(s1 == null) {
                    s1 = this.homeUrl;
                }
            }
            else {
                s1 = this.homeUrl;
            }
            if(this.urlChecker(s1)) {
                this.getRuliWebView().loadUrl(s1);
            }
            return;
        }
        this.getRuliWebView().loadUrl(this.homeUrl);
        this.checkPermission();

        @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0018\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0010\u0010\u000B\u001A\u00020\u00042\u0006\u0010\f\u001A\u00020\u0002H\u0016¨\u0006\r"}, d2 = {"com/ruliweb/www/ruliapp/MainActivity$onCreate$nlauncher$1$1", "Lcom/navercorp/nid/profile/NidProfileCallback;", "Lcom/navercorp/nid/profile/data/NidProfileResponse;", "onError", "", "errorCode", "", "message", "", "onFailure", "httpStatus", "onSuccess", "result", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public final class com.ruliweb.www.ruliapp.MainActivity.onCreate.nlauncher.1.1 implements NidProfileCallback {
            com.ruliweb.www.ruliapp.MainActivity.onCreate.nlauncher.1.1(MainActivity mainActivity0) {
            }

            @Override  // com.navercorp.nid.profile.NidProfileCallback
            public void onError(int v, String s) {
                Intrinsics.checkNotNullParameter(s, "message");
                this.onFailure(v, s);
            }

            @Override  // com.navercorp.nid.profile.NidProfileCallback
            public void onFailure(int v, String s) {
                Intrinsics.checkNotNullParameter(s, "message");
                String s1 = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                String s2 = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                Toast.makeText(MainActivity.this.getBaseContext(), "errorCode: " + s1 + ", errorDesc: " + s2, 0).show();
            }

            public void onSuccess(NidProfileResponse nidProfileResponse0) {
                Intrinsics.checkNotNullParameter(nidProfileResponse0, "result");
                if(nidProfileResponse0.getProfile() != null) {
                    Pair[] arr_pair = new Pair[4];
                    arr_pair[0] = new Pair("type", MainActivity.this.currentLoginType);
                    NidProfile nidProfile0 = nidProfileResponse0.getProfile();
                    Intrinsics.checkNotNull(nidProfile0);
                    String s = nidProfile0.getEmail();
                    String s1 = "";
                    if(s == null) {
                        s = "";
                    }
                    arr_pair[1] = new Pair("email", s);
                    NidProfile nidProfile1 = nidProfileResponse0.getProfile();
                    Intrinsics.checkNotNull(nidProfile1);
                    String s2 = nidProfile1.getId();
                    if(s2 == null) {
                        s2 = "";
                    }
                    arr_pair[2] = new Pair("id", s2);
                    NidProfile nidProfile2 = nidProfileResponse0.getProfile();
                    Intrinsics.checkNotNull(nidProfile2);
                    String s3 = nidProfile2.getEncId();
                    if(s3 != null) {
                        s1 = s3;
                    }
                    arr_pair[3] = new Pair("enc_id", s1);
                    Map map0 = MapsKt.mapOf(arr_pair);
                    MainActivity.this.socialLoginProcessor("naver", map0);
                }
            }

            @Override  // com.navercorp.nid.profile.NidProfileCallback
            public void onSuccess(Object object0) {
                this.onSuccess(((NidProfileResponse)object0));
            }
        }

    }

    private static final void onCreate$lambda$1(MainActivity mainActivity0, Task task0) {
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        Intrinsics.checkNotNullParameter(task0, "task");
        if(!task0.isSuccessful()) {
            Log.w("FireBase-warn", "Fetching FCM registration token failed", task0.getException());
            return;
        }
        String s = (String)task0.getResult();
        if(s == null) {
            s = "none";
        }
        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(mainActivity0);
        if(!Intrinsics.areEqual(sharedPreferences0.getString("gcm_token", ""), s)) {
            SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
            sharedPreferences$Editor0.putString("gcm_token", s);
            sharedPreferences$Editor0.apply();
        }
    }

    private static final void onCreate$lambda$3(MainActivity mainActivity0, ActivityResult activityResult0) {
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        Intrinsics.checkNotNullParameter(activityResult0, "result");
        switch(activityResult0.getResultCode()) {
            case -1: {
                new NidOAuthLogin().callProfileApi(new com.ruliweb.www.ruliapp.MainActivity.onCreate.nlauncher.1.1(mainActivity0));
                return;
            }
            case 0: {
                String s = NaverIdLoginSDK.INSTANCE.getLastErrorCode().getCode();
                String s1 = NaverIdLoginSDK.INSTANCE.getLastErrorDescription();
                Toast.makeText(mainActivity0.getBaseContext(), "errorCode:" + s + ", errorDesc:" + s1, 0).show();
            }
        }
    }

    private static final void onCreate$lambda$4(MainActivity mainActivity0, ActivityResult activityResult0) {
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        Intrinsics.checkNotNullParameter(activityResult0, "res");
        if(activityResult0.getData() != null) {
            Task task0 = GoogleSignIn.getSignedInAccountFromIntent(activityResult0.getData());
            Intrinsics.checkNotNullExpressionValue(task0, "getSignedInAccountFromIntent(...)");
            try {
                Object object0 = task0.getResult(ApiException.class);
                Intrinsics.checkNotNullExpressionValue(object0, "getResult(...)");
                if(((GoogleSignInAccount)object0).getId() != null && ((GoogleSignInAccount)object0).getEmail() != null) {
                    Pair[] arr_pair = new Pair[3];
                    arr_pair[0] = new Pair("type", mainActivity0.currentLoginType);
                    String s = ((GoogleSignInAccount)object0).getEmail();
                    Intrinsics.checkNotNull(s);
                    arr_pair[1] = new Pair("email", s);
                    String s1 = ((GoogleSignInAccount)object0).getId();
                    Intrinsics.checkNotNull(s1);
                    arr_pair[2] = new Pair("id", s1);
                    mainActivity0.socialLoginProcessor("google", MapsKt.mapOf(arr_pair));
                }
            }
            catch(ApiException apiException0) {
                Log.w("gSIGNIN checker", "signInResult:failed code=" + apiException0.getStatusCode());
            }
        }
    }

    private static final void onCreate$lambda$7(MainActivity mainActivity0, ActivityResult activityResult0) {
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        Intrinsics.checkNotNullParameter(activityResult0, "res");
        Intent intent0 = activityResult0.getData();
        List list0 = null;
        if(activityResult0.getResultCode() == -1) {
            if(intent0 == null) {
                ValueCallback valueCallback0 = mainActivity0.mWebViewImageUpload;
                Intrinsics.checkNotNull(valueCallback0);
                Uri uri0 = Uri.parse(mainActivity0.cameraPath);
                Intrinsics.checkNotNullExpressionValue(uri0, "parse(...)");
                valueCallback0.onReceiveValue(new Uri[]{uri0});
                return;
            }
            Uri uri1 = intent0.getData();
            ClipData clipData0 = intent0.getClipData();
            if(clipData0 != null) {
                Iterable iterable0 = RangesKt.until(0, clipData0.getItemCount());
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                Iterator iterator0 = iterable0.iterator();
                while(iterator0.hasNext()) {
                    arrayList0.add(clipData0.getItemAt(((IntIterator)iterator0).nextInt()).getUri());
                }
                list0 = arrayList0;
            }
            if(uri1 != null) {
                ValueCallback valueCallback1 = mainActivity0.mWebViewImageUpload;
                Intrinsics.checkNotNull(valueCallback1);
                valueCallback1.onReceiveValue(new Uri[]{uri1});
                return;
            }
            if(list0 != null) {
                if(list0.size() > 10) {
                    Toast.makeText(mainActivity0.getApplicationContext(), "사진은 최대 10장까지 선택 가능합니다.", 1).show();
                    return;
                }
                ValueCallback valueCallback2 = mainActivity0.mWebViewImageUpload;
                Intrinsics.checkNotNull(valueCallback2);
                valueCallback2.onReceiveValue(list0.toArray(new Uri[0]));
            }
            return;
        }
        ValueCallback valueCallback3 = mainActivity0.mWebViewImageUpload;
        Intrinsics.checkNotNull(valueCallback3);
        valueCallback3.onReceiveValue(null);
        mainActivity0.mWebViewImageUpload = null;
    }

    private static final void onCreate$lambda$8(MainActivity mainActivity0, String s, String s1, String s2, String s3, long v) {
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        DownloadManager.Request downloadManager$Request0 = new DownloadManager.Request(Uri.parse(s));
        downloadManager$Request0.setNotificationVisibility(1);
        downloadManager$Request0.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "download");
        Object object0 = mainActivity0.getSystemService("download");
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.app.DownloadManager");
        ((DownloadManager)object0).enqueue(downloadManager$Request0);
    }

    @Override  // android.app.Activity
    public void onCreateContextMenu(ContextMenu contextMenu0, View view0, ContextMenu.ContextMenuInfo contextMenu$ContextMenuInfo0) {
        super.onCreateContextMenu(contextMenu0, view0, contextMenu$ContextMenuInfo0);
        WebView.HitTestResult webView$HitTestResult0 = this.getRuliWebView().getHitTestResult();
        Intrinsics.checkNotNullExpressionValue(webView$HitTestResult0, "getHitTestResult(...)");
        String s = webView$HitTestResult0.getExtra();
        if(s != null) {
            String s1 = new Regex("\\S+/([a-zA-Z0-9_]+.(jpg|jpeg|gif|png|webp))").replace(s, "$1");
            MainActivity..ExternalSyntheticLambda13 mainActivity$$ExternalSyntheticLambda130 = (MenuItem menuItem0) -> MainActivity.onCreateContextMenu$lambda$9(s, s1, this, menuItem0);
            switch(webView$HitTestResult0.getType()) {
                case 5: 
                case 8: {
                    Intrinsics.checkNotNull(contextMenu0);
                    contextMenu0.setHeaderTitle(s1);
                    contextMenu0.add(0, 666, 0, "이미지 저장").setOnMenuItemClickListener(mainActivity$$ExternalSyntheticLambda130);
                    break;
                }
            }
        }
    }

    private static final boolean onCreateContextMenu$lambda$9(String s, String s1, MainActivity mainActivity0, MenuItem menuItem0) {
        Intrinsics.checkNotNullParameter(s1, "$fileName");
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        Intrinsics.checkNotNullParameter(menuItem0, "it");
        if(URLUtil.isValidUrl(s)) {
            DownloadManager.Request downloadManager$Request0 = new DownloadManager.Request(Uri.parse(s));
            downloadManager$Request0.setNotificationVisibility(1);
            downloadManager$Request0.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, s1);
            Object object0 = mainActivity0.getSystemService("download");
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.app.DownloadManager");
            ((DownloadManager)object0).enqueue(downloadManager$Request0);
        }
        return true;
    }

    @Override  // androidx.appcompat.app.AppCompatActivity
    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        Intrinsics.checkNotNull(keyEvent0);
        if(keyEvent0.getAction() == 0 && v == 4) {
            if(this.getRuliWebView().canGoBack()) {
                this.getRuliWebView().getSettings().setCacheMode(1);
                this.getRuliWebView().goBack();
                return true;
            }
            this.finish();
            return true;
        }
        return super.onKeyDown(v, keyEvent0);
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onNewIntent(Intent intent0) {
        String s;
        Intrinsics.checkNotNullParameter(intent0, "intent");
        super.onNewIntent(intent0);
        if(intent0.hasExtra("linkTo") || intent0.hasExtra("link")) {
            if(intent0.hasExtra("linkTo")) {
                s = intent0.getStringExtra("linkTo");
                if(s == null) {
                    s = this.homeUrl;
                }
            }
            else if(intent0.hasExtra("link")) {
                s = intent0.getStringExtra("link");
                if(s == null) {
                    s = this.homeUrl;
                }
            }
            else {
                s = "";
            }
            if(this.urlChecker(s)) {
                this.getRuliWebView().loadUrl(s);
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onPause() {
        super.onPause();
        this.getRuliWebView().onPause();
        this.getRuliWebView().pauseTimers();
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onResume() {
        String s;
        super.onResume();
        this.getRuliWebView().onResume();
        this.getRuliWebView().resumeTimers();
        if(this.getIntent().getExtras() != null) {
            if(this.getIntent().hasExtra("linkTo")) {
                s = this.getIntent().getStringExtra("linkTo");
                if(s == null) {
                    s = this.homeUrl;
                }
            }
            else if(this.getIntent().hasExtra("link")) {
                s = this.getIntent().getStringExtra("link");
                if(s == null) {
                    s = this.homeUrl;
                }
            }
            else {
                s = this.homeUrl;
            }
            if(this.urlChecker(s)) {
                this.getRuliWebView().loadUrl(s);
            }
        }
    }

    private static final void requestPermissionLauncher$lambda$0(boolean z) {
    }

    public final void setCameraPath(String s) {
        Intrinsics.checkNotNullParameter(s, "<set-?>");
        this.cameraPath = s;
    }

    public final void setMWebViewImageUpload(ValueCallback valueCallback0) {
        this.mWebViewImageUpload = valueCallback0;
    }

    public final void setRuliWebView(WebView webView0) {
        Intrinsics.checkNotNullParameter(webView0, "<set-?>");
        this.ruliWebView = webView0;
    }

    public final void setSubWebView(WebView webView0) {
        this.subWebView = webView0;
    }

    private final void socialLoginProcessor(String s, Map map0) {
        String s1;
        switch(s) {
            case "google": {
                s1 = "구글";
                break;
            }
            case "kakao": {
                s1 = "카카오";
                break;
            }
            case "naver": {
                s1 = "네이버";
                break;
            }
            default: {
                s1 = "";
            }
        }
        RestModule restModule0 = RestModule.Companion.getInstance();
        restModule0.setTarget("external_login");
        restModule0.setMethod("POST");
        restModule0.setData(map0);
        Context context0 = this.getBaseContext();
        Intrinsics.checkNotNullExpressionValue(context0, "getBaseContext(...)");
        restModule0.request(context0, new Function1(s, s1) {
            final String $msg;
            final String $type;

            {
                MainActivity.this = mainActivity0;
                this.$type = s;
                this.$msg = s1;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((String)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "res");
                JSONObject jSONObject0 = new JSONObject(s);
                Log.d("json_test", jSONObject0.toString());
                if(jSONObject0.get("success") != "false" && !Intrinsics.areEqual(jSONObject0.get("success"), Boolean.FALSE)) {
                    Object object0 = jSONObject0.get("session_key");
                    MainActivity.this.cookieManager.setCookie(".ruliweb.com", "s_token=" + object0);
                    MainActivity.this.cookieManager.flush();
                }
                else if(Intrinsics.areEqual(jSONObject0.get("2fa_required").toString(), "true")) {
                    MainActivity.this.cookieManager.setCookie(".ruliweb.com", "login_redirect_url=" + MainActivity.this.returnUrl);
                    Object object1 = jSONObject0.get("member_srl");
                    MainActivity.this.returnUrl = "https://m.ruliweb.com/member/app_2fa_login?type=" + this.$type + "&member_srl=" + object1;
                }
                else {
                    Toast.makeText(MainActivity.this.getBaseContext(), "회원정보가 없습니다.", 0).show();
                }
                if(Intrinsics.areEqual(this.$type, "kakao")) {
                    UserApiClient.Companion.getInstance().logout(new Function1() {
                        final String $msg;

                        {
                            this.$msg = s;
                            super(1);
                        }

                        @Override  // kotlin.jvm.functions.Function1
                        public Object invoke(Object object0) {
                            this.invoke(((Throwable)object0));
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Throwable throwable0) {
                            if(throwable0 != null) {
                                Log.e("kakaoLogoutError", this.$msg + " 로그아웃 실패!", throwable0);
                            }
                        }
                    });
                }
                MainActivity.this.getRuliWebView().clearCache(true);
                Log.d("testsss", MainActivity.this.returnUrl);
                MainActivity.this.getRuliWebView().loadUrl(MainActivity.this.returnUrl);
            }
        }, com.ruliweb.www.ruliapp.MainActivity.socialLoginProcessor.2.INSTANCE);

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "err", "Lcom/android/volley/VolleyError;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class com.ruliweb.www.ruliapp.MainActivity.socialLoginProcessor.2 extends Lambda implements Function1 {
            public static final com.ruliweb.www.ruliapp.MainActivity.socialLoginProcessor.2 INSTANCE;

            static {
                com.ruliweb.www.ruliapp.MainActivity.socialLoginProcessor.2.INSTANCE = new com.ruliweb.www.ruliapp.MainActivity.socialLoginProcessor.2();
            }

            com.ruliweb.www.ruliapp.MainActivity.socialLoginProcessor.2() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((VolleyError)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(VolleyError volleyError0) {
                Intrinsics.checkNotNullParameter(volleyError0, "err");
            }
        }

    }

    public final void startFind() {
        View view0 = this.findViewById(0x7F0900CF);  // id:findToolbar
        Intrinsics.checkNotNullExpressionValue(view0, "findViewById(...)");
        View view1 = this.findViewById(0x7F0901A8);  // id:searchText
        Intrinsics.checkNotNullExpressionValue(view1, "findViewById(...)");
        View view2 = this.findViewById(0x7F0901A9);  // id:searchUp
        Intrinsics.checkNotNullExpressionValue(view2, "findViewById(...)");
        View view3 = this.findViewById(0x7F0901A6);  // id:searchDown
        Intrinsics.checkNotNullExpressionValue(view3, "findViewById(...)");
        View view4 = this.findViewById(0x7F0901A7);  // id:searchEnd
        Intrinsics.checkNotNullExpressionValue(view4, "findViewById(...)");
        if(view0.getVisibility() == 0) {
            view0.setVisibility(8);
            ((EditText)view1).setText("");
            this.getRuliWebView().findAllAsync("");
            return;
        }
        MainActivity..ExternalSyntheticLambda11 mainActivity$$ExternalSyntheticLambda110 = (View view1) -> MainActivity.startFind$lambda$10(this, ((EditText)view1), view0, view1);
        view0.setVisibility(0);
        ((EditText)view1).addTextChangedListener(new TextWatcher() {
            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
                MainActivity.this.getRuliWebView().findAllAsync(String.valueOf(editable0));
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }
        });
        ((EditText)view1).setOnKeyListener((View view1, int v, KeyEvent keyEvent0) -> MainActivity.startFind$lambda$11(view3, view1, v, keyEvent0));
        view3.setOnClickListener(mainActivity$$ExternalSyntheticLambda110);
        view2.setOnClickListener(mainActivity$$ExternalSyntheticLambda110);
        view4.setOnClickListener(mainActivity$$ExternalSyntheticLambda110);
        ((EditText)view1).requestFocus();
        WindowCompat.getInsetsController(this.getWindow(), ((EditText)view1)).show(8);
    }

    private static final void startFind$lambda$10(MainActivity mainActivity0, EditText editText0, View view0, View view1) {
        Intrinsics.checkNotNullParameter(mainActivity0, "this$0");
        Intrinsics.checkNotNullParameter(editText0, "$searchText");
        Intrinsics.checkNotNullParameter(view0, "$findToolbar");
        Object object0 = mainActivity0.getApplicationContext().getSystemService("input_method");
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager)object0).hideSoftInputFromWindow(editText0.getWindowToken(), 0);
        switch(view1.getId()) {
            case 0x7F0901A6: {  // id:searchDown
                mainActivity0.getRuliWebView().findNext(true);
                return;
            }
            case 0x7F0901A7: {  // id:searchEnd
                view0.setVisibility(8);
                editText0.setText("");
                mainActivity0.getRuliWebView().findAllAsync("");
                return;
            }
            case 0x7F0901A9: {  // id:searchUp
                mainActivity0.getRuliWebView().findNext(false);
            }
        }
    }

    private static final boolean startFind$lambda$11(View view0, View view1, int v, KeyEvent keyEvent0) {
        Intrinsics.checkNotNullParameter(view0, "$searchDown");
        if(keyEvent0.getKeyCode() == 66) {
            view0.callOnClick();
        }
        return false;
    }

    private final boolean urlChecker(String s) {
        for(int v = 0; v < 25; ++v) {
            if(StringsKt.contains$default(s, new String[]{"https://m.ruliweb.com", "https://mdev.ruliweb.com", "https://mdev1.ruliweb.com", "https://mdev2.ruliweb.com", "https://mdev3.ruliweb.com", "https://mdev5.ruliweb.com", "https://bbs.ruliweb.com", "https://dev.ruliweb.com", "https://dev1.ruliweb.com", "https://dev2.ruliweb.com", "https://dev3.ruliweb.com", "https://dev5.ruliweb.com", "https://user.ruliweb.com", "https://userdev.ruliweb.com", "https://mypi.ruliweb.com", "https://cert.kcp.co.kr", "https://card.ok-name.co.kr", "https://ipin.ok-name.co.kr", "https://safe.ok-name.co.kr", "https://nid.naver.com", "https://logins.daum.net", "https://apis.daum.net", "https://accounts.kakao.com", "https://accounts.google.com", "https://api.dable.io"}[v], false, 2, null)) {
                return true;
            }
        }
        return false;
    }
}

