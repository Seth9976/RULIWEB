package com.navercorp.nid.progress;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatTextView;
import com.airbnb.lottie.LottieAnimationView;
import com.nhn.android.oauth.R.id;
import com.nhn.android.oauth.R.layout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000B\u001A\u00020\fJ\u0012\u0010\r\u001A\u00020\f2\b\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0002J\u000E\u0010\u0010\u001A\u00020\f2\u0006\u0010\u0011\u001A\u00020\u0012J\u001A\u0010\u0010\u001A\u00020\f2\u0006\u0010\u0011\u001A\u00020\u00122\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\u000FJ\u000E\u0010\u0010\u001A\u00020\f2\u0006\u0010\u0013\u001A\u00020\u0014J\u001A\u0010\u0010\u001A\u00020\f2\u0006\u0010\u0013\u001A\u00020\u00142\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\u000FR\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/navercorp/nid/progress/NidProgressDialog;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "animation", "Lcom/airbnb/lottie/LottieAnimationView;", "dialog", "Landroidx/appcompat/app/AppCompatDialog;", "message", "Landroidx/appcompat/widget/AppCompatTextView;", "hideProgress", "", "init", "cancelListener", "Landroid/content/DialogInterface$OnCancelListener;", "showProgress", "resourceId", "", "msg", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidProgressDialog {
    private LottieAnimationView animation;
    private Context context;
    private AppCompatDialog dialog;
    private AppCompatTextView message;

    public NidProgressDialog(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super();
        this.context = context0;
        this.dialog = new AppCompatDialog(context0);
        this.init(null);
    }

    public final void hideProgress() {
        if(!NidProgressDialogKt.isFinishing(this.context) && this.dialog.isShowing()) {
            LottieAnimationView lottieAnimationView0 = this.animation;
            if(lottieAnimationView0 != null) {
                lottieAnimationView0.pauseAnimation();
            }
            this.dialog.dismiss();
        }
    }

    private final void init(DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        this.dialog.setCancelable(true);
        Window window0 = this.dialog.getWindow();
        if(window0 != null) {
            window0.setBackgroundDrawable(new ColorDrawable(0));
        }
        this.dialog.setContentView(layout.nid_progress_dialog);
        this.message = (AppCompatTextView)this.dialog.findViewById(id.nid_progress_dialog_message);
        this.animation = (LottieAnimationView)this.dialog.findViewById(id.nid_progress_dialog_animation);
    }

    public final void showProgress(int v) {
        String s = this.context.getResources().getString(v);
        Intrinsics.checkNotNullExpressionValue(s, "context.resources.getString(resourceId)");
        this.showProgress(s);
    }

    public final void showProgress(int v, DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        String s = this.context.getResources().getString(v);
        Intrinsics.checkNotNullExpressionValue(s, "context.resources.getString(resourceId)");
        this.showProgress(s, dialogInterface$OnCancelListener0);
    }

    public final void showProgress(String s) {
        Intrinsics.checkNotNullParameter(s, "msg");
        this.showProgress(s, null);
    }

    public final void showProgress(String s, DialogInterface.OnCancelListener dialogInterface$OnCancelListener0) {
        Intrinsics.checkNotNullParameter(s, "msg");
        if(NidProgressDialogKt.isFinishing(this.context)) {
            return;
        }
        AppCompatTextView appCompatTextView0 = this.message;
        if(appCompatTextView0 != null) {
            appCompatTextView0.setText(s);
        }
        if(dialogInterface$OnCancelListener0 != null) {
            this.dialog.setOnCancelListener(dialogInterface$OnCancelListener0);
        }
        LottieAnimationView lottieAnimationView0 = this.animation;
        if(lottieAnimationView0 != null) {
            lottieAnimationView0.playAnimation();
        }
        this.dialog.show();
    }

    public static void showProgress$default(NidProgressDialog nidProgressDialog0, int v, DialogInterface.OnCancelListener dialogInterface$OnCancelListener0, int v1, Object object0) {
        if((v1 & 2) != 0) {
            dialogInterface$OnCancelListener0 = null;
        }
        nidProgressDialog0.showProgress(v, dialogInterface$OnCancelListener0);
    }

    public static void showProgress$default(NidProgressDialog nidProgressDialog0, String s, DialogInterface.OnCancelListener dialogInterface$OnCancelListener0, int v, Object object0) {
        if((v & 2) != 0) {
            dialogInterface$OnCancelListener0 = null;
        }
        nidProgressDialog0.showProgress(s, dialogInterface$OnCancelListener0);
    }
}

