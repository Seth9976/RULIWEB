package com.navercorp.nid.oauth.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.widget.AppCompatImageView;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.oauth.OAuthLoginCallback;
import com.nhn.android.oauth.R.drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000B\u001A\u00020\fJ\u0014\u0010\r\u001A\u00020\f2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000FJ\u000E\u0010\r\u001A\u00020\f2\u0006\u0010\u0011\u001A\u00020\u0012¨\u0006\u0014"}, d2 = {"Lcom/navercorp/nid/oauth/view/NidOAuthLoginButton;", "Landroidx/appcompat/widget/AppCompatImageView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "init", "", "setOAuthLogin", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "oauthLoginCallback", "Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "Companion", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthLoginButton extends AppCompatImageView {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000BR\u001C\u0010\f\u001A\u0004\u0018\u00010\rX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u000F\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/navercorp/nid/oauth/view/NidOAuthLoginButton$Companion;", "", "()V", "TAG", "", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "getLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "setLauncher", "(Landroidx/activity/result/ActivityResultLauncher;)V", "oauthLoginCallback", "Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "getOauthLoginCallback", "()Lcom/navercorp/nid/oauth/OAuthLoginCallback;", "setOauthLoginCallback", "(Lcom/navercorp/nid/oauth/OAuthLoginCallback;)V", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ActivityResultLauncher getLauncher() {
            return NidOAuthLoginButton.launcher;
        }

        public final OAuthLoginCallback getOauthLoginCallback() {
            return NidOAuthLoginButton.oauthLoginCallback;
        }

        public final void setLauncher(ActivityResultLauncher activityResultLauncher0) {
            NidOAuthLoginButton.launcher = activityResultLauncher0;
        }

        public final void setOauthLoginCallback(OAuthLoginCallback oAuthLoginCallback0) {
            NidOAuthLoginButton.oauthLoginCallback = oAuthLoginCallback0;
        }
    }

    public static final Companion Companion = null;
    public static final String TAG = "NidOAuthLoginButton";
    private static ActivityResultLauncher launcher;
    private static OAuthLoginCallback oauthLoginCallback;

    // 检测为 Lambda 实现
    public static void $r8$lambda$q48NCehsu2qKM5UbdJy9LmeWaC8(NidOAuthLoginButton nidOAuthLoginButton0, View view0) [...]

    static {
        NidOAuthLoginButton.Companion = new Companion(null);
    }

    public NidOAuthLoginButton(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super(context0);
        this.init();
    }

    public NidOAuthLoginButton(Context context0, AttributeSet attributeSet0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super(context0, attributeSet0);
        this.init();
    }

    public NidOAuthLoginButton(Context context0, AttributeSet attributeSet0, int v) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super(context0, attributeSet0, v);
        this.init();
    }

    public final void init() {
        this.setImageDrawable(this.getContext().getDrawable(drawable.login_btn_img));
        this.setOnClickListener((View view0) -> NidOAuthLoginButton.init$lambda-2(this, view0));
    }

    private static final void init$lambda-2(NidOAuthLoginButton nidOAuthLoginButton0, View view0) {
        Intrinsics.checkNotNullParameter(nidOAuthLoginButton0, "this$0");
        ActivityResultLauncher activityResultLauncher0 = NidOAuthLoginButton.launcher;
        if(activityResultLauncher0 != null) {
            Context context0 = nidOAuthLoginButton0.getContext();
            Intrinsics.checkNotNullExpressionValue(context0, "context");
            NaverIdLoginSDK.INSTANCE.authenticate(context0, activityResultLauncher0);
            return;
        }
        OAuthLoginCallback oAuthLoginCallback0 = NidOAuthLoginButton.oauthLoginCallback;
        if(oAuthLoginCallback0 != null) {
            Context context1 = nidOAuthLoginButton0.getContext();
            Intrinsics.checkNotNullExpressionValue(context1, "context");
            NaverIdLoginSDK.INSTANCE.authenticate(context1, oAuthLoginCallback0);
        }
    }

    public final void setOAuthLogin(ActivityResultLauncher activityResultLauncher0) {
        Intrinsics.checkNotNullParameter(activityResultLauncher0, "launcher");
        NidOAuthLoginButton.launcher = activityResultLauncher0;
    }

    public final void setOAuthLogin(OAuthLoginCallback oAuthLoginCallback0) {
        Intrinsics.checkNotNullParameter(oAuthLoginCallback0, "oauthLoginCallback");
        NidOAuthLoginButton.oauthLoginCallback = oAuthLoginCallback0;
    }
}

