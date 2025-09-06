package com.kakao.sdk.auth;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.appcompat.app.AppCompatActivity;
import com.kakao.sdk.common.model.AuthError;
import com.kakao.sdk.common.model.AuthErrorCause;
import com.kakao.sdk.common.model.AuthErrorResponse;
import com.kakao.sdk.common.model.ClientError;
import com.kakao.sdk.common.model.ClientErrorCause;
import com.kakao.sdk.common.model.KakaoSdkError;
import com.kakao.sdk.common.util.KakaoJson;
import com.kakao.sdk.common.util.SdkLog;
import com.kakao.sdk.v2.auth.R.layout;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000E\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u001C0\u001BH\u0002J\u0012\u0010\u001D\u001A\u00020\u001E2\b\u0010\u001F\u001A\u0004\u0018\u00010 H\u0014J\u0010\u0010!\u001A\u00020\u001E2\u0006\u0010\"\u001A\u00020#H\u0002R\u0014\u0010\u0003\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u0006R\u0014\u0010\u000B\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u0006R\u0014\u0010\u000F\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001A\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/kakao/sdk/auth/TalkAuthCodeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "APPLICATION_ERROR", "", "getAPPLICATION_ERROR", "()Ljava/lang/String;", "AUTH_CODE_ERROR", "getAUTH_CODE_ERROR", "CLIENT_INFO_ERROR", "getCLIENT_INFO_ERROR", "EXTRA_ERROR_DESCRIPTION", "getEXTRA_ERROR_DESCRIPTION", "EXTRA_ERROR_TYPE", "getEXTRA_ERROR_TYPE", "NOT_SUPPORT_ERROR", "getNOT_SUPPORT_ERROR", "PROTOCOL_ERROR", "getPROTOCOL_ERROR", "UNKNOWN_ERROR", "getUNKNOWN_ERROR", "activityResultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "resultReceiver", "Landroid/os/ResultReceiver;", "activityResultCallback", "Landroidx/activity/result/ActivityResultCallback;", "Landroidx/activity/result/ActivityResult;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "sendError", "exception", "Lcom/kakao/sdk/common/model/KakaoSdkError;", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class TalkAuthCodeActivity extends AppCompatActivity {
    private final String APPLICATION_ERROR;
    private final String AUTH_CODE_ERROR;
    private final String CLIENT_INFO_ERROR;
    private final String EXTRA_ERROR_DESCRIPTION;
    private final String EXTRA_ERROR_TYPE;
    private final String NOT_SUPPORT_ERROR;
    private final String PROTOCOL_ERROR;
    private final String UNKNOWN_ERROR;
    private final ActivityResultLauncher activityResultLauncher;
    private ResultReceiver resultReceiver;

    // 检测为 Lambda 实现
    public static void $r8$lambda$dCGdqLMjRutHlh8EY9Vi8LC1RD0(TalkAuthCodeActivity talkAuthCodeActivity0, ActivityResult activityResult0) [...]

    public TalkAuthCodeActivity() {
        ActivityResultLauncher activityResultLauncher0 = this.registerForActivityResult(new StartActivityForResult(), this.activityResultCallback());
        Intrinsics.checkNotNullExpressionValue(activityResultLauncher0, "registerForActivityResult(\n        ActivityResultContracts.StartActivityForResult(),\n        activityResultCallback()\n    )");
        this.activityResultLauncher = activityResultLauncher0;
        this.EXTRA_ERROR_TYPE = "com.kakao.sdk.talk.error.type";
        this.EXTRA_ERROR_DESCRIPTION = "com.kakao.sdk.talk.error.description";
        this.NOT_SUPPORT_ERROR = "NotSupportError";
        this.UNKNOWN_ERROR = "UnknownError";
        this.PROTOCOL_ERROR = "ProtocolError";
        this.APPLICATION_ERROR = "ApplicationError";
        this.AUTH_CODE_ERROR = "AuthCodeError";
        this.CLIENT_INFO_ERROR = "ClientInfoError";
    }

    private final ActivityResultCallback activityResultCallback() {
        return (ActivityResult activityResult0) -> TalkAuthCodeActivity.activityResultCallback$lambda-7(this, activityResult0);
    }

    private static final void activityResultCallback$lambda-7(TalkAuthCodeActivity talkAuthCodeActivity0, ActivityResult activityResult0) {
        Intrinsics.checkNotNullParameter(talkAuthCodeActivity0, "this$0");
        Bundle bundle0 = new Bundle();
        if(activityResult0.getData() != null) {
            switch(activityResult0.getResultCode()) {
                case -1: {
                    Intent intent0 = activityResult0.getData();
                    Bundle bundle1 = intent0 == null ? null : intent0.getExtras();
                    if(bundle1 == null) {
                        talkAuthCodeActivity0.sendError(new ClientError(ClientErrorCause.Unknown, "No result from KakaoTalk."));
                        return;
                    }
                    String s = bundle1.getString(talkAuthCodeActivity0.getEXTRA_ERROR_TYPE());
                    String s1 = bundle1.getString(talkAuthCodeActivity0.getEXTRA_ERROR_DESCRIPTION());
                    if(Intrinsics.areEqual(s, "access_denied")) {
                        talkAuthCodeActivity0.sendError(new ClientError(ClientErrorCause.Cancelled, null, 2, null));
                        return;
                    }
                    if(s != null) {
                        AuthErrorCause authErrorCause0 = (AuthErrorCause)KakaoJson.INSTANCE.fromJson(s, AuthErrorCause.class);
                        if(authErrorCause0 == null) {
                            authErrorCause0 = AuthErrorCause.Unknown;
                        }
                        if(s1 == null) {
                            s1 = "no error description";
                        }
                        talkAuthCodeActivity0.sendError(new AuthError(302, authErrorCause0, new AuthErrorResponse(s, s1)));
                        return;
                    }
                    bundle0.putParcelable("key.url", Uri.parse(bundle1.getString("com.kakao.sdk.talk.redirectUrl")));
                    ResultReceiver resultReceiver0 = talkAuthCodeActivity0.resultReceiver;
                    if(resultReceiver0 != null) {
                        resultReceiver0.send(-1, bundle0);
                        talkAuthCodeActivity0.finish();
                        talkAuthCodeActivity0.overridePendingTransition(0, 0);
                        return;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("resultReceiver");
                    throw null;
                }
                case 0: {
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        talkAuthCodeActivity0.sendError(new ClientError(ClientErrorCause.Cancelled, null, 2, null));
    }

    public final String getAPPLICATION_ERROR() {
        return this.APPLICATION_ERROR;
    }

    public final String getAUTH_CODE_ERROR() {
        return this.AUTH_CODE_ERROR;
    }

    public final String getCLIENT_INFO_ERROR() {
        return this.CLIENT_INFO_ERROR;
    }

    public final String getEXTRA_ERROR_DESCRIPTION() {
        return this.EXTRA_ERROR_DESCRIPTION;
    }

    public final String getEXTRA_ERROR_TYPE() {
        return this.EXTRA_ERROR_TYPE;
    }

    public final String getNOT_SUPPORT_ERROR() {
        return this.NOT_SUPPORT_ERROR;
    }

    public final String getPROTOCOL_ERROR() {
        return this.PROTOCOL_ERROR;
    }

    public final String getUNKNOWN_ERROR() {
        return this.UNKNOWN_ERROR;
    }

    @Override  // androidx.fragment.app.FragmentActivity
    protected void onCreate(Bundle bundle0) {
        ResultReceiver resultReceiver0;
        super.onCreate(bundle0);
        this.setContentView(layout.activity_talk_auth_code);
        try {
            Bundle bundle1 = this.getIntent().getExtras();
            if(bundle1 == null) {
                throw new IllegalArgumentException("no extras.");
            }
            Bundle bundle2 = bundle1.getBundle("key.bundle");
            if(bundle2 != null) {
                if(Build.VERSION.SDK_INT >= 33) {
                    resultReceiver0 = (ResultReceiver)LinkFollowing..ExternalSyntheticApiModelOutline0.m(bundle2, "key.result.receiver", ResultReceiver.class);
                    goto label_13;
                }
                else {
                    Parcelable parcelable0 = bundle2.getParcelable("key.result.receiver");
                    if(parcelable0 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type android.os.ResultReceiver");
                    }
                    resultReceiver0 = (ResultReceiver)parcelable0;
                label_13:
                    if(resultReceiver0 == null) {
                        throw new IllegalStateException();
                    }
                    this.resultReceiver = resultReceiver0;
                }
            }
            String s = Intrinsics.stringPlus("requestCode: ", bundle1.getInt("key.request.code"));
            SdkLog.Companion.i(s);
            Intent intent0 = Build.VERSION.SDK_INT < 33 ? ((Intent)bundle1.getParcelable("key.login.intent")) : ((Intent)LinkFollowing..ExternalSyntheticApiModelOutline0.m(bundle1, "key.login.intent", Intent.class));
            SdkLog.Companion.i("loginIntent:");
            if(intent0 != null) {
                Bundle bundle3 = intent0.getExtras();
                if(bundle3 != null) {
                    String s1 = Intrinsics.stringPlus("\tcom.kakao.sdk.talk.appKey : ", bundle3.getString("com.kakao.sdk.talk.appKey"));
                    SdkLog.Companion.i(s1);
                    String s2 = Intrinsics.stringPlus("\tcom.kakao.sdk.talk.redirectUri : ", bundle3.getString("com.kakao.sdk.talk.redirectUri"));
                    SdkLog.Companion.i(s2);
                    String s3 = Intrinsics.stringPlus("\tcom.kakao.sdk.talk.kaHeader : ", bundle3.getString("com.kakao.sdk.talk.kaHeader"));
                    SdkLog.Companion.i(s3);
                    Bundle bundle4 = bundle3.getBundle("com.kakao.sdk.talk.extraparams");
                    if(bundle4 != null) {
                        SdkLog.Companion.i("\tcom.kakao.sdk.talk.extraparams");
                        Set set0 = bundle4.keySet();
                        Intrinsics.checkNotNullExpressionValue(set0, "keySet()");
                        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set0, 10));
                        for(Object object0: set0) {
                            arrayList0.add("\t\t" + ((String)object0) + " : " + bundle4.getString(((String)object0)));
                        }
                        for(Object object1: arrayList0) {
                            SdkLog.Companion.i(((String)object1));
                        }
                    }
                }
            }
            this.activityResultLauncher.launch(intent0);
        }
        catch(Throwable throwable0) {
            SdkLog.Companion.e(throwable0);
            ClientError clientError0 = new ClientError(ClientErrorCause.Unknown, null, 2, null);
            clientError0.initCause(throwable0);
            this.sendError(clientError0);
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
}

