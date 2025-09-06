package com.kakao.sdk.auth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fJ>\u0010\r\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\n2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0011\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u000B\u001A\u00020\fJ\u0006\u0010\u0014\u001A\u00020\u0004¨\u0006\u0015"}, d2 = {"Lcom/kakao/sdk/auth/AuthCodeIntentFactory;", "", "()V", "account", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "fullUri", "Landroid/net/Uri;", "redirectUri", "", "resultReceiver", "Landroid/os/ResultReceiver;", "talk", "requestCode", "", "clientId", "kaHeader", "extras", "Landroid/os/Bundle;", "talkBase", "auth_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public final class AuthCodeIntentFactory {
    public static final AuthCodeIntentFactory INSTANCE;

    static {
        AuthCodeIntentFactory.INSTANCE = new AuthCodeIntentFactory();
    }

    public final Intent account(Context context0, Uri uri0, String s, ResultReceiver resultReceiver0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(uri0, "fullUri");
        Intrinsics.checkNotNullParameter(s, "redirectUri");
        Intrinsics.checkNotNullParameter(resultReceiver0, "resultReceiver");
        Intent intent0 = new Intent(context0, AuthCodeHandlerActivity.class);
        Bundle bundle0 = new Bundle();
        bundle0.putParcelable("key.result.receiver", resultReceiver0);
        bundle0.putParcelable("key.full_authorize_uri", uri0);
        bundle0.putString("key.redirect_uri", s);
        Intent intent1 = intent0.putExtra("key.bundle", bundle0).addFlags(0x10000000);
        Intrinsics.checkNotNullExpressionValue(intent1, "Intent(context, cls)\n            .putExtra(Constants.KEY_BUNDLE, Bundle().apply {\n                putParcelable(Constants.KEY_RESULT_RECEIVER, resultReceiver)\n                putParcelable(Constants.KEY_FULL_URI, fullUri)\n                putString(Constants.KEY_REDIRECT_URI, redirectUri)\n            })\n            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)");
        return intent1;
    }

    public final Intent talk(Context context0, int v, String s, String s1, String s2, Bundle bundle0, ResultReceiver resultReceiver0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "clientId");
        Intrinsics.checkNotNullParameter(s1, "redirectUri");
        Intrinsics.checkNotNullParameter(s2, "kaHeader");
        Intrinsics.checkNotNullParameter(bundle0, "extras");
        Intrinsics.checkNotNullParameter(resultReceiver0, "resultReceiver");
        Intent intent0 = new Intent(context0, TalkAuthCodeActivity.class).putExtra("key.login.intent", this.talkBase().putExtra("com.kakao.sdk.talk.appKey", s).putExtra("com.kakao.sdk.talk.redirectUri", s1).putExtra("com.kakao.sdk.talk.kaHeader", s2).putExtra("com.kakao.sdk.talk.extraparams", bundle0)).putExtra("key.request.code", v);
        Bundle bundle1 = new Bundle();
        bundle1.putParcelable("key.result.receiver", resultReceiver0);
        Intent intent1 = intent0.putExtra("key.bundle", bundle1);
        Intrinsics.checkNotNullExpressionValue(intent1, "Intent(context, TalkAuthCodeActivity::class.java)\n            .putExtra(\n                Constants.KEY_LOGIN_INTENT,\n                talkBase()\n                    .putExtra(Constants.EXTRA_APPLICATION_KEY, clientId)\n                    .putExtra(Constants.EXTRA_REDIRECT_URI, redirectUri)\n                    .putExtra(Constants.EXTRA_KA_HEADER, kaHeader)\n                    .putExtra(Constants.EXTRA_EXTRAPARAMS, extras)\n            )\n            .putExtra(Constants.KEY_REQUEST_CODE, requestCode)\n            .putExtra(Constants.KEY_BUNDLE, Bundle().apply {\n                putParcelable(Constants.KEY_RESULT_RECEIVER, resultReceiver)\n            })");
        return intent1;
    }

    public final Intent talkBase() {
        Intent intent0 = new Intent("com.kakao.talk.intent.action.CAPRI_LOGGED_IN_ACTIVITY").addCategory("android.intent.category.DEFAULT");
        Intrinsics.checkNotNullExpressionValue(intent0, "Intent(Constants.CAPRI_LOGGED_IN_ACTIVITY).addCategory(Intent.CATEGORY_DEFAULT)");
        return intent0;
    }
}

