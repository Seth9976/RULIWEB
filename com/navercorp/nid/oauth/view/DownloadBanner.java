package com.navercorp.nid.oauth.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nhn.android.oauth.R.drawable;
import com.nhn.android.oauth.R.string;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001A\u00020\rH\u0002J\b\u0010\u000E\u001A\u00020\u0001H\u0002J\b\u0010\u000F\u001A\u00020\u0010H\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0002J\b\u0010\u0013\u001A\u00020\tH\u0002J\b\u0010\u0014\u001A\u00020\u0012H\u0002J\u0010\u0010\u0015\u001A\u00020\u00162\u0006\u0010\n\u001A\u00020\u000BH\u0002J\u0010\u0010\u0017\u001A\u00020\u00162\u0006\u0010\n\u001A\u00020\u000BH\u0002J\u0010\u0010\u0018\u001A\u00020\u00162\u0006\u0010\n\u001A\u00020\u000BH\u0002J\f\u0010\u0019\u001A\u00020\u000B*\u00020\u000BH\u0002R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Lcom/navercorp/nid/oauth/view/DownloadBanner;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "density", "", "densityDpi", "", "createCloseButton", "Landroid/widget/RelativeLayout;", "createDescription", "createIcon", "Landroid/widget/ImageView;", "downloadNaverapp", "", "getTextSizeUpper", "init", "isHdpi", "", "isMdpi", "isXhdpi", "toDp", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class DownloadBanner extends LinearLayout {
    private float density;
    private int densityDpi;

    // 检测为 Lambda 实现
    public static void $r8$lambda$18UfSMpo2LeNMvQqzCyvI_-_P74(DownloadBanner downloadBanner0, View view0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$AtaKyaqxkuFhCZu8IkEPz5fuVUA(DownloadBanner downloadBanner0, View view0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$LpVio2tdAiaO3l58WNAwOmCX5Pw(DownloadBanner downloadBanner0, View view0) [...]

    public DownloadBanner(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super(context0);
        this.density = this.getContext().getResources().getDisplayMetrics().density;
        this.densityDpi = this.getContext().getResources().getDisplayMetrics().densityDpi;
        this.init();
    }

    public DownloadBanner(Context context0, AttributeSet attributeSet0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        super(context0, attributeSet0);
        this.density = this.getContext().getResources().getDisplayMetrics().density;
        this.densityDpi = this.getContext().getResources().getDisplayMetrics().densityDpi;
        this.init();
    }

    private final RelativeLayout createCloseButton() {
        ImageView imageView0 = new ImageView(this.getContext());
        int v = this.toDp(10);
        imageView0.setLayoutParams(new RelativeLayout.LayoutParams(-2, -1));
        imageView0.setPadding(v, v, v, v);
        imageView0.setImageDrawable(imageView0.getContext().getDrawable(drawable.close_btn_img_black));
        imageView0.setScaleType(ImageView.ScaleType.FIT_START);
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = imageView0.getLayoutParams();
        if(viewGroup$LayoutParams0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        }
        ((RelativeLayout.LayoutParams)viewGroup$LayoutParams0).addRule(11, -1);
        imageView0.setLayoutParams(((RelativeLayout.LayoutParams)viewGroup$LayoutParams0));
        imageView0.setOnClickListener((View view0) -> DownloadBanner.createCloseButton$lambda-7(this, view0));
        RelativeLayout relativeLayout0 = new RelativeLayout(this.getContext());
        relativeLayout0.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1.0f));
        relativeLayout0.addView(imageView0);
        return relativeLayout0;
    }

    private static final void createCloseButton$lambda-7(DownloadBanner downloadBanner0, View view0) {
        Intrinsics.checkNotNullParameter(downloadBanner0, "this$0");
        downloadBanner0.setVisibility(8);
    }

    private final LinearLayout createDescription() {
        LinearLayout linearLayout0 = new LinearLayout(this.getContext());
        int v = this.toDp(10);
        linearLayout0.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout0.setPadding(v, v, 0, v);
        linearLayout0.setOrientation(1);
        linearLayout0.setGravity(16);
        linearLayout0.setClickable(true);
        linearLayout0.setOnClickListener((View view0) -> DownloadBanner.createDescription$lambda-3$lambda-2(this, view0));
        TextView textView0 = new TextView(this.getContext());
        int v1 = this.toDp(4);
        textView0.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView0.setPadding(0, v1, 0, v1);
        textView0.setTextColor(Color.rgb(51, 51, 51));
        textView0.setText(textView0.getContext().getString(string.naveroauthlogin_string_msg_naverapp_download_desc));
        textView0.setTypeface(null, 1);
        textView0.setTextSize(this.getTextSizeUpper());
        linearLayout0.addView(textView0);
        TextView textView1 = new TextView(this.getContext());
        textView1.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        textView1.setPadding(0, 0, 0, v1);
        textView1.setText(textView1.getContext().getString(string.naveroauthlogin_string_msg_naverapp_download_link));
        textView1.setTextColor(Color.rgb(45, 100, 0));
        textView1.setPaintFlags(textView1.getPaintFlags() | 8);
        textView1.setTextSize(this.getTextSizeUpper());
        linearLayout0.addView(textView1);
        return linearLayout0;
    }

    private static final void createDescription$lambda-3$lambda-2(DownloadBanner downloadBanner0, View view0) {
        Intrinsics.checkNotNullParameter(downloadBanner0, "this$0");
        downloadBanner0.downloadNaverapp();
    }

    private final ImageView createIcon() {
        ImageView imageView0 = new ImageView(this.getContext());
        int v = this.toDp(10);
        imageView0.setLayoutParams(new LinearLayout.LayoutParams(this.toDp(70), this.toDp(70)));
        imageView0.setPadding(v, v, 0, v);
        imageView0.setImageDrawable(imageView0.getContext().getDrawable(drawable.naver_icon));
        imageView0.setOnClickListener((View view0) -> DownloadBanner.createIcon$lambda-1$lambda-0(this, view0));
        return imageView0;
    }

    private static final void createIcon$lambda-1$lambda-0(DownloadBanner downloadBanner0, View view0) {
        Intrinsics.checkNotNullParameter(downloadBanner0, "this$0");
        downloadBanner0.downloadNaverapp();
    }

    private final void downloadNaverapp() {
        Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.nhn.android.search"));
        Context context0 = this.getContext();
        if(context0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        }
        ((Activity)context0).startActivity(intent0);
        Context context1 = this.getContext();
        if(context1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        }
        ((Activity)context1).finish();
    }

    private final float getTextSizeUpper() {
        if(this.isXhdpi(this.densityDpi)) {
            return 14.0f;
        }
        return this.isHdpi(this.densityDpi) ? 13.0f : 12.0f;
    }

    private final void init() {
        this.setBackgroundColor(Color.rgb(0xFE, 0xFC, 0xE3));
        this.setOrientation(0);
        this.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.addView(this.createIcon());
        this.addView(this.createDescription());
        this.addView(this.createCloseButton());
    }

    // 去混淆评级： 低(20)
    private final boolean isHdpi(int v) {
        return this.isMdpi(v) ? false : v <= 0xF0;
    }

    private final boolean isMdpi(int v) {
        return v <= 0xA0;
    }

    // 去混淆评级： 低(30)
    private final boolean isXhdpi(int v) {
        return this.isMdpi(v) ? false : !this.isHdpi(v);
    }

    private final int toDp(int v) {
        return (int)(((float)v) * this.density);
    }
}

