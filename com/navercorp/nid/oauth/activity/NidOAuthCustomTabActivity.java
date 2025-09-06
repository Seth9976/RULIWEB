package com.navercorp.nid.oauth.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.log.NidLog;
import com.navercorp.nid.oauth.NidOAuthQuery.Builder;
import com.navercorp.nid.oauth.NidOAuthQuery.Method;
import java.net.URLDecoder;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000F\u0018\u0000 \u001D2\u00020\u0001:\u0001\u001DB\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001A\u0004\u0018\u00010\u00072\b\u0010\b\u001A\u0004\u0018\u00010\u0007H\u0002J\u0012\u0010\t\u001A\u00020\n2\b\u0010\u000B\u001A\u0004\u0018\u00010\fH\u0014J\u0012\u0010\r\u001A\u00020\n2\b\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0014J\u0010\u0010\u0010\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0014J\b\u0010\u0011\u001A\u00020\nH\u0014J\u0010\u0010\u0012\u001A\u00020\n2\u0006\u0010\u0013\u001A\u00020\fH\u0014J\b\u0010\u0014\u001A\u00020\nH\u0002J&\u0010\u0015\u001A\u00020\n2\b\u0010\u0016\u001A\u0004\u0018\u00010\u00072\b\u0010\u0017\u001A\u0004\u0018\u00010\u00072\b\u0010\u0018\u001A\u0004\u0018\u00010\u0007H\u0002J0\u0010\u0019\u001A\u00020\n2\b\u0010\u0016\u001A\u0004\u0018\u00010\u00072\b\u0010\u001A\u001A\u0004\u0018\u00010\u00072\b\u0010\u0017\u001A\u0004\u0018\u00010\u00072\b\u0010\u0018\u001A\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010\u001B\u001A\u00020\n2\u0006\u0010\u001C\u001A\u00020\u000FH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u001E"}, d2 = {"Lcom/navercorp/nid/oauth/activity/NidOAuthCustomTabActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "isCalledNewIntent", "", "isCustomTabOpen", "getDecodedString", "", "str", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onRestoreInstanceState", "onResume", "onSaveInstanceState", "outState", "openCustomTab", "responseError", "state", "error", "errorDescription", "responseResult", "code", "returnResult", "data", "Companion", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthCustomTabActivity extends AppCompatActivity {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/navercorp/nid/oauth/activity/NidOAuthCustomTabActivity$Companion;", "", "()V", "ACTION_NAVER_CUSTOM_TAB", "", "SAVE_CUSTOM_TAB_OPEN", "TAG", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final String ACTION_NAVER_CUSTOM_TAB = "ACTION_NAVER_3RDPARTY_CUSTOM_TAB";
    public static final Companion Companion = null;
    public static final String SAVE_CUSTOM_TAB_OPEN = "isCustomTabOpen";
    public static final String TAG = "NidOAuthCustomTabActivity";
    private boolean isCalledNewIntent;
    private boolean isCustomTabOpen;

    static {
        NidOAuthCustomTabActivity.Companion = new Companion(null);
    }

    private final String getDecodedString(String s) {
        NidLog.d("NidOAuthCustomTabActivity", "called getDecodedString()");
        NidLog.d("NidOAuthCustomTabActivity", "getDecodedString() | str : " + s);
        if(s != null && s.length() != 0) {
            String s1 = URLDecoder.decode(s, "UTF-8");
            return s1 == null || s1.length() == 0 || StringsKt.equals(s1, s, true) ? s : s1;
        }
        return s;
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        NidLog.d("NidOAuthCustomTabActivity", "called onCreate()");
        NaverIdLoginSDK.INSTANCE.init(this);
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onNewIntent(Intent intent0) {
        super.onNewIntent(intent0);
        this.isCalledNewIntent = true;
        if(intent0 == null) {
            return;
        }
        String s = intent0.getStringExtra("code");
        String s1 = intent0.getStringExtra("state");
        String s2 = intent0.getStringExtra("error");
        String s3 = this.getDecodedString(intent0.getStringExtra("error_description"));
        if(s != null && s.length() != 0 || s2 != null && s2.length() != 0) {
            this.responseResult(s1, s, s2, s3);
            return;
        }
        this.responseError(s1, s2, s3);
    }

    @Override  // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "savedInstanceState");
        super.onRestoreInstanceState(bundle0);
        this.isCustomTabOpen = bundle0.getBoolean("isCustomTabOpen", false);
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onResume() {
        super.onResume();
        if(this.isCustomTabOpen) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new Function2(null) {
                int label;

                {
                    NidOAuthCustomTabActivity.this = nidOAuthCustomTabActivity0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    return new com.navercorp.nid.oauth.activity.NidOAuthCustomTabActivity.onResume.1(NidOAuthCustomTabActivity.this, continuation0);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                }

                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                    return ((com.navercorp.nid.oauth.activity.NidOAuthCustomTabActivity.onResume.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch(this.label) {
                        case 0: {
                            ResultKt.throwOnFailure(object0);
                            this.label = 1;
                            if(DelayKt.delay(500L, this) == object1) {
                                return object1;
                            }
                            break;
                        }
                        case 1: {
                            ResultKt.throwOnFailure(object0);
                            break;
                        }
                        default: {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        }
                    }
                    if(!NidOAuthCustomTabActivity.this.isCalledNewIntent) {
                        NidOAuthCustomTabActivity.this.responseError(null, "user_cancel", "user_cancel");
                    }
                    return Unit.INSTANCE;
                }
            }, 3, null);
            return;
        }
        this.openCustomTab();
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onSaveInstanceState(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outState");
        super.onSaveInstanceState(bundle0);
        bundle0.putBoolean("isCustomTabOpen", this.isCustomTabOpen);
    }

    private final void openCustomTab() {
        this.isCustomTabOpen = true;
        this.responseError(null, "sdk_is_not_initialized", "sdk_is_not_initialized");
        String s = new Builder().setMethod(Method.CUSTOM_TABS).setAuthType(this.getIntent().getStringExtra("auth_type")).build();
        CustomTabsIntent customTabsIntent0 = new androidx.browser.customtabs.CustomTabsIntent.Builder().setShowTitle(true).build();
        Intrinsics.checkNotNullExpressionValue(customTabsIntent0, "Builder()\n            .s…rue)\n            .build()");
        customTabsIntent0.launchUrl(this, Uri.parse(s));
    }

    private final void responseError(String s, String s1, String s2) {
        Intent intent0 = new Intent();
        intent0.putExtra("oauth_state", s);
        intent0.putExtra("oauth_error_code", s1);
        intent0.putExtra("oauth_error_desc", s2);
        this.returnResult(intent0);
    }

    private final void responseResult(String s, String s1, String s2, String s3) {
        Intent intent0 = new Intent();
        intent0.putExtra("oauth_state", s);
        intent0.putExtra("oauth_code", s1);
        intent0.putExtra("oauth_error_code", s2);
        intent0.putExtra("oauth_error_desc", s3);
        this.returnResult(intent0);
    }

    private final void returnResult(Intent intent0) {
        intent0.setAction("ACTION_NAVER_3RDPARTY_CUSTOM_TAB");
        this.setResult(-1, intent0);
        this.finish();
    }
}

