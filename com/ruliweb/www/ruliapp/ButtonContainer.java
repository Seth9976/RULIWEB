package com.ruliweb.www.ruliapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 J2\u00020\u0001:\u0001JB\u0005\u00A2\u0006\u0002\u0010\u0002J\u0006\u00109\u001A\u00020\u0014J\u0006\u0010:\u001A\u00020\u0014J\u0010\u0010;\u001A\u00020\u00142\u0006\u0010<\u001A\u00020=H\u0016J$\u0010>\u001A\u00020?2\u0006\u0010@\u001A\u00020A2\b\u0010B\u001A\u0004\u0018\u00010C2\b\u0010D\u001A\u0004\u0018\u00010EH\u0016J\b\u0010F\u001A\u00020\u0014H\u0016J\u001A\u0010G\u001A\u00020\u00142\u0006\u0010H\u001A\u00020?2\b\u0010D\u001A\u0004\u0018\u00010EH\u0016J\u0006\u0010I\u001A\u00020\u0014R\"\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000E\u00A2\u0006\u0010\n\u0002\u0010\n\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\u0004X\u0086\u000E\u00A2\u0006\u0010\n\u0002\u0010\u0011\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R \u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\u001A0\u0004X\u0086\u000E\u00A2\u0006\u0010\n\u0002\u0010\u001F\u001A\u0004\b\u001B\u0010\u001C\"\u0004\b\u001D\u0010\u001ER\u001A\u0010 \u001A\u00020\u0005X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001A\u0010%\u001A\u00020&X\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u0010\u0010+\u001A\u0004\u0018\u00010,X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001C\u0010-\u001A\u0004\u0018\u00010.X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b/\u00100\"\u0004\b1\u00102R\u001A\u00103\u001A\u000204X\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b5\u00106\"\u0004\b7\u00108\u00A8\u0006K"}, d2 = {"Lcom/ruliweb/www/ruliapp/ButtonContainer;", "Landroidx/fragment/app/Fragment;", "()V", "buttonList", "", "", "getButtonList", "()[Ljava/lang/Integer;", "setButtonList", "([Ljava/lang/Integer;)V", "[Ljava/lang/Integer;", "buttons", "Landroid/widget/Button;", "getButtons", "()[Landroid/widget/Button;", "setButtons", "([Landroid/widget/Button;)V", "[Landroid/widget/Button;", "findCallBack", "Lkotlin/Function0;", "", "getFindCallBack", "()Lkotlin/jvm/functions/Function0;", "setFindCallBack", "(Lkotlin/jvm/functions/Function0;)V", "functionList", "", "getFunctionList", "()[Ljava/lang/String;", "setFunctionList", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "moreIdx", "getMoreIdx", "()I", "setMoreIdx", "(I)V", "parent", "Landroidx/core/app/ActivityCompat;", "getParent", "()Landroidx/core/app/ActivityCompat;", "setParent", "(Landroidx/core/app/ActivityCompat;)V", "popup", "Landroid/widget/PopupMenu;", "ruliWebView", "Landroid/webkit/WebView;", "getRuliWebView", "()Landroid/webkit/WebView;", "setRuliWebView", "(Landroid/webkit/WebView;)V", "wrapper", "Landroid/widget/LinearLayout;", "getWrapper", "()Landroid/widget/LinearLayout;", "setWrapper", "(Landroid/widget/LinearLayout;)V", "initContainerFunc", "initContainerView", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onViewCreated", "view", "refreshView", "Companion", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class ButtonContainer extends Fragment {
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0010\u001A\u00020\u0011R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R(\u0010\u0005\u001A\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000BR\u000E\u0010\f\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ruliweb/www/ruliapp/ButtonContainer$Companion;", "", "()V", "bgColor", "", "device", "", "", "getDevice", "()Ljava/util/Map;", "setDevice", "(Ljava/util/Map;)V", "textColor", "makeButtonView", "Landroid/widget/Button;", "res", "ctx", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Map getDevice() {
            return ButtonContainer.device;
        }

        public final Button makeButtonView(int v, Context context0) {
            int v4;
            int v1;
            Integer integer0;
            int v3;
            int v2;
            Double double0;
            Integer integer1;
            Double double1;
            Intrinsics.checkNotNullParameter(context0, "ctx");
            Button button0 = new Button(context0);
            Map map0 = this.getDevice();
            String s = map0 == null ? null : ((String)map0.get("Type"));
            if(s == null) {
            label_42:
                Map map4 = this.getDevice();
                if(map4 == null) {
                    double1 = 3.5;
                }
                else {
                    String s4 = (String)map4.get("Density");
                    double1 = s4 == null ? 3.5 : Float.parseFloat(s4);
                }
                v2 = double1.intValue();
                v3 = 50;
            label_53:
                v4 = v3 * v2;
            }
            else {
                switch(s) {
                    case "Foldable": {
                        Map map3 = this.getDevice();
                        if(map3 == null) {
                            integer1 = 2;
                        }
                        else {
                            String s3 = (String)map3.get("Density");
                            integer1 = s3 == null ? 2 : Float.parseFloat(s3);
                        }
                        v1 = integer1.intValue();
                        break;
                    }
                    case "SmartPhone": {
                        Map map2 = this.getDevice();
                        if(map2 == null) {
                            double0 = 3.5;
                        }
                        else {
                            String s2 = (String)map2.get("Density");
                            double0 = s2 == null ? 3.5 : Float.parseFloat(s2);
                        }
                        v2 = double0.intValue();
                        v3 = 40;
                        goto label_53;
                    }
                    case "Tablet": {
                        Map map1 = this.getDevice();
                        if(map1 == null) {
                            integer0 = 2;
                        }
                        else {
                            String s1 = (String)map1.get("Density");
                            integer0 = s1 == null ? 2 : Float.parseFloat(s1);
                        }
                        v1 = integer0.intValue();
                        break;
                    }
                    default: {
                        goto label_42;
                    }
                }
                v4 = 30 * v1;
            }
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = new LinearLayout.LayoutParams(v4, v4);
            Map map5 = this.getDevice();
            String s5 = map5 == null ? null : ((String)map5.get("Width"));
            Intrinsics.checkNotNull(s5);
            float f = Float.parseFloat(s5);
            Map map6 = this.getDevice();
            String s6 = map6 == null ? null : ((String)map6.get("Density"));
            Intrinsics.checkNotNull(s6);
            int v5 = (int)((f * Float.parseFloat(s6) / 8.0f - ((float)v4)) / 2.0f);
            button0.setLayoutParams(linearLayout$LayoutParams0);
            button0.setBackgroundColor(0);
            linearLayout$LayoutParams0.leftMargin = v5;
            linearLayout$LayoutParams0.rightMargin = v5;
            String s7 = context0.getResources().getResourceTypeName(v);
            if(Intrinsics.areEqual(s7, "drawable")) {
                button0.setBackgroundResource(v);
                button0.getBackground().setColorFilter(new PorterDuffColorFilter(0, PorterDuff.Mode.SRC_ATOP));
                return button0;
            }
            if(Intrinsics.areEqual(s7, "string")) {
                linearLayout$LayoutParams0.width = v4 + 10;
                button0.setStateListAnimator(null);
                button0.setText(context0.getResources().getString(v));
                button0.setPadding(0, 0, 0, 0);
                button0.setTextSize(17.0f);
                button0.setTypeface(null, 1);
            }
            return button0;
        }

        public final void setDevice(Map map0) {
            ButtonContainer.device = map0;
        }
    }

    public static final Companion Companion;
    private static int bgColor;
    private Integer[] buttonList;
    private Button[] buttons;
    private static Map device;
    private Function0 findCallBack;
    private String[] functionList;
    private int moreIdx;
    public ActivityCompat parent;
    private PopupMenu popup;
    private WebView ruliWebView;
    private static int textColor;
    public LinearLayout wrapper;

    // 检测为 Lambda 实现
    public static boolean $r8$lambda$V249qzSF-7G0nLHiUrvVYk6la3Y(ButtonContainer buttonContainer0, MenuItem menuItem0) [...]

    static {
        ButtonContainer.Companion = new Companion(null);
        ButtonContainer.bgColor = Color.parseColor("#FFFFFF");
        ButtonContainer.textColor = Color.parseColor("#000000");
    }

    public ButtonContainer() {
        this.buttons = new Button[0];
        this.functionList = new String[]{"home", "back", "front", "share", "refresh", "comment", "notification", "more", "find", "gotop", "gobottom"};
        this.buttonList = new Integer[0];
        this.findCallBack = com.ruliweb.www.ruliapp.ButtonContainer.findCallBack.1.INSTANCE;
        this.moreIdx = -1;

        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class com.ruliweb.www.ruliapp.ButtonContainer.findCallBack.1 extends Lambda implements Function0 {
            public static final com.ruliweb.www.ruliapp.ButtonContainer.findCallBack.1 INSTANCE;

            static {
                com.ruliweb.www.ruliapp.ButtonContainer.findCallBack.1.INSTANCE = new com.ruliweb.www.ruliapp.ButtonContainer.findCallBack.1();
            }

            com.ruliweb.www.ruliapp.ButtonContainer.findCallBack.1() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return Unit.INSTANCE;
            }

            public final void invoke() {
            }
        }

    }

    public static final int access$getBgColor$cp() [...] // 潜在的解密器

    public static final int access$getTextColor$cp() [...] // 潜在的解密器

    public final Integer[] getButtonList() {
        return this.buttonList;
    }

    public final Button[] getButtons() {
        return this.buttons;
    }

    public final Function0 getFindCallBack() {
        return this.findCallBack;
    }

    public final String[] getFunctionList() {
        return this.functionList;
    }

    public final int getMoreIdx() {
        return this.moreIdx;
    }

    public final ActivityCompat getParent() {
        ActivityCompat activityCompat0 = this.parent;
        if(activityCompat0 != null) {
            return activityCompat0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("parent");
        return null;
    }

    public final WebView getRuliWebView() {
        return this.ruliWebView;
    }

    public final LinearLayout getWrapper() {
        LinearLayout linearLayout0 = this.wrapper;
        if(linearLayout0 != null) {
            return linearLayout0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("wrapper");
        return null;
    }

    public final void initContainerFunc() {
        if(this.ruliWebView == null) {
            Log.e("BtnContainerError", "webview initialize require!");
            return;
        }
        PopupMenu popupMenu0 = this.popup;
        Intrinsics.checkNotNull(popupMenu0);
        Menu menu0 = popupMenu0.getMenu();
        if(Build.VERSION.SDK_INT >= 29) {
            PopupMenu popupMenu1 = this.popup;
            Intrinsics.checkNotNull(popupMenu1);
            popupMenu1.setForceShowIcon(true);
        }
        menu0.clear();
        String[] arr_s = this.functionList;
        int v = 0;
        for(int v1 = 0; v1 < arr_s.length; ++v1) {
            switch(arr_s[v1]) {
                case "back": {
                    menu0.add(0, RuliAppFunction.BACK.getFunctionId(), RuliAppFunction.BACK.getFunctionId(), "뒤로 가기");
                    break;
                }
                case "comment": {
                    menu0.add(0, RuliAppFunction.COMMENT.getFunctionId(), RuliAppFunction.COMMENT.getFunctionId(), "댓글 작성");
                    break;
                }
                case "copyURL": {
                    menu0.add(0, RuliAppFunction.URLCOPY.getFunctionId(), RuliAppFunction.URLCOPY.getFunctionId(), "URL 복사");
                    break;
                }
                case "find": {
                    menu0.add(0, RuliAppFunction.FIND.getFunctionId(), RuliAppFunction.FIND.getFunctionId(), "페이지에서 찾기");
                    MenuItem menuItem0 = menu0.findItem(RuliAppFunction.FIND.getFunctionId());
                    Drawable drawable0 = ResourcesCompat.getDrawable(this.requireActivity().getResources(), 0x7F0800A8, null);  // drawable:find_btn
                    if(drawable0 != null) {
                        Bitmap bitmap0 = DrawableKt.toBitmap$default(drawable0, 30, 30, null, 4, null);
                        Resources resources0 = this.requireActivity().getResources();
                        Intrinsics.checkNotNullExpressionValue(resources0, "getResources(...)");
                        menuItem0.setIcon(new BitmapDrawable(resources0, bitmap0));
                        Drawable drawable1 = menuItem0.getIcon();
                        Intrinsics.checkNotNull(drawable1);
                        drawable1.setColorFilter(new PorterDuffColorFilter(ButtonContainer.textColor, PorterDuff.Mode.SRC_ATOP));
                    }
                    break;
                }
                case "front": {
                    menu0.add(0, RuliAppFunction.FRONT.getFunctionId(), RuliAppFunction.FRONT.getFunctionId(), "앞으로 가기");
                    break;
                }
                case "gobottom": {
                    menu0.add(0, RuliAppFunction.GOBOTTOM.getFunctionId(), RuliAppFunction.GOBOTTOM.getFunctionId(), "아래로 가기");
                    MenuItem menuItem2 = menu0.findItem(RuliAppFunction.GOBOTTOM.getFunctionId());
                    Drawable drawable4 = ResourcesCompat.getDrawable(this.requireActivity().getResources(), 0x7F0800AA, null);
                    if(drawable4 != null) {
                        Bitmap bitmap2 = DrawableKt.toBitmap$default(drawable4, 50, 50, null, 4, null);
                        Resources resources2 = this.requireActivity().getResources();
                        Intrinsics.checkNotNullExpressionValue(resources2, "getResources(...)");
                        menuItem2.setIcon(new BitmapDrawable(resources2, bitmap2));
                        Drawable drawable5 = menuItem2.getIcon();
                        Intrinsics.checkNotNull(drawable5);
                        drawable5.setColorFilter(new PorterDuffColorFilter(ButtonContainer.textColor, PorterDuff.Mode.SRC_ATOP));
                    }
                    break;
                }
                case "gotop": {
                    menu0.add(0, RuliAppFunction.GOTOP.getFunctionId(), RuliAppFunction.GOTOP.getFunctionId(), "위로 가기");
                    MenuItem menuItem1 = menu0.findItem(RuliAppFunction.GOTOP.getFunctionId());
                    Drawable drawable2 = ResourcesCompat.getDrawable(this.requireActivity().getResources(), 0x7F0800AD, null);
                    if(drawable2 != null) {
                        Bitmap bitmap1 = DrawableKt.toBitmap$default(drawable2, 50, 50, null, 4, null);
                        Resources resources1 = this.requireActivity().getResources();
                        Intrinsics.checkNotNullExpressionValue(resources1, "getResources(...)");
                        menuItem1.setIcon(new BitmapDrawable(resources1, bitmap1));
                        Drawable drawable3 = menuItem1.getIcon();
                        Intrinsics.checkNotNull(drawable3);
                        drawable3.setColorFilter(new PorterDuffColorFilter(ButtonContainer.textColor, PorterDuff.Mode.SRC_ATOP));
                    }
                    break;
                }
                case "more": {
                    menu0.add(0, RuliAppFunction.MORE.getFunctionId(), RuliAppFunction.MORE.getFunctionId(), "더 보기");
                    break;
                }
                case "refresh": {
                    menu0.add(0, RuliAppFunction.REFRESH.getFunctionId(), RuliAppFunction.REFRESH.getFunctionId(), "새로고침");
                    break;
                }
                case "settings": {
                    menu0.add(0, RuliAppFunction.SETTINGS.getFunctionId(), RuliAppFunction.SETTINGS.getFunctionId(), "설정");
                }
            }
        }
        PopupMenu popupMenu2 = this.popup;
        Intrinsics.checkNotNull(popupMenu2);
        popupMenu2.setOnMenuItemClickListener((MenuItem menuItem0) -> ButtonContainer.initContainerFunc$lambda$4(this, menuItem0));
        Function1 function10 = new Function1() {
            // 检测为 Lambda 实现
            public static void $r8$lambda$QhaaOEfMH7K20zle0y4M9v1frO4(ButtonContainer buttonContainer0, int v, View view0) [...]

            {
                ButtonContainer.this = buttonContainer0;
                super(1);
            }

            public final View.OnClickListener invoke(int v) {
                return (View view0) -> com.ruliweb.www.ruliapp.ButtonContainer.initContainerFunc.onClickListener.1.invoke$lambda$1(ButtonContainer.this, v, view0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Number)object0).intValue());
            }

            private static final void invoke$lambda$1(ButtonContainer buttonContainer0, int v, View view0) {
                Intrinsics.checkNotNullParameter(buttonContainer0, "this$0");
                switch(((int)buttonContainer0.getButtonList()[v])) {
                    case 0x7F08007B: {  // drawable:baseline_chevron_left_24
                        WebView webView0 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView0);
                        webView0.goBack();
                        return;
                    }
                    case 0x7F08007C: {  // drawable:baseline_chevron_right_24
                        WebView webView1 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView1);
                        webView1.goForward();
                        return;
                    }
                    case 0x7F08007D: {  // drawable:baseline_circle_notifications_24
                        Intent intent0 = new Intent(buttonContainer0.requireActivity(), SettingsActivity.class).putExtra("target", "alarm");
                        Intrinsics.checkNotNullExpressionValue(intent0, "putExtra(...)");
                        buttonContainer0.startActivity(intent0);
                        return;
                    }
                    case 0x7F08007E: {  // drawable:baseline_home_24
                        WebView webView2 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView2);
                        webView2.loadUrl("https://m.ruliweb.com");
                        return;
                    }
                    case 0x7F08007F: {  // drawable:baseline_more_horiz_24
                        PopupMenu popupMenu0 = buttonContainer0.popup;
                        Intrinsics.checkNotNull(popupMenu0);
                        popupMenu0.show();
                        return;
                    }
                    case 0x7F080080: {  // drawable:baseline_refresh_24
                        WebView webView3 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView3);
                        webView3.reload();
                        return;
                    }
                    case 0x7F080081: {  // drawable:baseline_share_24
                        WebView webView4 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView4);
                        String s = webView4.getUrl();
                        Intent intent1 = new Intent();
                        intent1.setAction("android.intent.action.SEND");
                        intent1.putExtra("android.intent.extra.TEXT", s);
                        intent1.setType("text/plain");
                        buttonContainer0.startActivity(Intent.createChooser(intent1, null));
                        return;
                    }
                    case 0x7F0800A8: {  // drawable:find_btn
                        buttonContainer0.getFindCallBack().invoke();
                        return;
                    }
                    case 0x7F0800AA: {
                        WebView webView5 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView5);
                        webView5.pageDown(true);
                        return;
                    }
                    case 0x7F0800AD: {
                        WebView webView6 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView6);
                        webView6.pageUp(true);
                        return;
                    }
                    case 0x7F120035: {  // string:comment_btn "댓글"
                        WebView webView7 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView7);
                        WebView webView8 = buttonContainer0.getRuliWebView();
                        Intrinsics.checkNotNull(webView8);
                        String s1 = webView8.getUrl();
                        Intrinsics.checkNotNull(s1);
                        webView7.loadUrl(s1 + "#cmt");
                    }
                }
            }
        };
        Button[] arr_button = this.buttons;
        for(int v2 = 0; v < arr_button.length; ++v2) {
            arr_button[v].setOnClickListener(((View.OnClickListener)function10.invoke(v2)));
            ++v;
        }
    }

    private static final boolean initContainerFunc$lambda$4(ButtonContainer buttonContainer0, MenuItem menuItem0) {
        Intrinsics.checkNotNullParameter(buttonContainer0, "this$0");
        int v = menuItem0.getItemId();
        if(v == RuliAppFunction.BACK.getFunctionId()) {
            WebView webView0 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView0);
            webView0.goBack();
            return true;
        }
        if(v == RuliAppFunction.FRONT.getFunctionId()) {
            WebView webView1 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView1);
            webView1.goForward();
            return true;
        }
        if(v == RuliAppFunction.REFRESH.getFunctionId()) {
            WebView webView2 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView2);
            webView2.clearCache(true);
            WebView webView3 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView3);
            webView3.reload();
            return true;
        }
        if(v == RuliAppFunction.GOBOTTOM.getFunctionId()) {
            WebView webView4 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView4);
            webView4.pageDown(true);
            return true;
        }
        if(v == RuliAppFunction.GOTOP.getFunctionId()) {
            WebView webView5 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView5);
            webView5.pageUp(true);
            return true;
        }
        if(v == RuliAppFunction.COMMENT.getFunctionId()) {
            WebView webView6 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView6);
            WebView webView7 = buttonContainer0.ruliWebView;
            Intrinsics.checkNotNull(webView7);
            String s = webView7.getUrl();
            Intrinsics.checkNotNull(s);
            webView6.loadUrl(s + "#cmt");
            return true;
        }
        if(v != RuliAppFunction.MORE.getFunctionId()) {
            if(v == RuliAppFunction.FIND.getFunctionId()) {
                buttonContainer0.findCallBack.invoke();
                return true;
            }
            if(v == RuliAppFunction.SETTINGS.getFunctionId()) {
                Intent intent0 = new Intent(buttonContainer0.requireActivity(), SettingsActivity.class).putExtra("target", "alarm");
                Intrinsics.checkNotNullExpressionValue(intent0, "putExtra(...)");
                buttonContainer0.startActivity(intent0);
                return true;
            }
            if(v == RuliAppFunction.URLCOPY.getFunctionId()) {
                WebView webView8 = buttonContainer0.ruliWebView;
                if(webView8 != null) {
                    Intrinsics.checkNotNull(webView8);
                    String s1 = webView8.getUrl();
                    FragmentActivity fragmentActivity0 = buttonContainer0.getActivity();
                    Object object0 = fragmentActivity0 == null ? null : fragmentActivity0.getSystemService("clipboard");
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.content.ClipboardManager");
                    ((ClipboardManager)object0).setPrimaryClip(ClipData.newPlainText("url_from_ruliapp", s1));
                }
            }
        }
        return true;
    }

    public final void initContainerView() {
        FragmentActivity fragmentActivity0 = this.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivity0, "requireActivity(...)");
        SharedPreferences sharedPreferences0 = PreferenceManager.getDefaultSharedPreferences(fragmentActivity0.getBaseContext());
        sharedPreferences0.getString("BtnContainerSettings", "");
        List list0 = CollectionsKt.emptyList();
        View view0 = fragmentActivity0.findViewById(0x7F090071);  // id:btnContainerLayout
        Intrinsics.checkNotNullExpressionValue(view0, "findViewById(...)");
        this.setWrapper(((LinearLayout)view0));
        this.getWrapper().setGravity(16);
        this.getWrapper().setBackgroundColor(ButtonContainer.bgColor);
        if(list0.isEmpty()) {
            this.buttonList = new Integer[]{0x7F08007E, 0x7F08007B, 0x7F08007C, 0x7F080081, 0x7F080080, 0x7F120035, 0x7F08007D, 0x7F08007F};  // drawable:baseline_home_24
            List list1 = CollectionsKt.listOf(new String[]{"home", "back", "front", "share", "refresh", "comment", "notification", "more"});
            String[] arr_s = this.functionList;
            Collection collection0 = new ArrayList();
            for(int v = 0; v < arr_s.length; ++v) {
                String s = arr_s[v];
                if(!Intrinsics.areEqual(s, "home") && !Intrinsics.areEqual(s, "back") && !Intrinsics.areEqual(s, "front") && !Intrinsics.areEqual(s, "share") && !Intrinsics.areEqual(s, "refresh") && !Intrinsics.areEqual(s, "notification") && !Intrinsics.areEqual(s, "comment") && !Intrinsics.areEqual(s, "more")) {
                    collection0.add(s);
                }
            }
            this.functionList = (String[])((List)collection0).toArray(new String[0]);
            SharedPreferences.Editor sharedPreferences$Editor0 = sharedPreferences0.edit();
            sharedPreferences$Editor0.putString("BtnContainerSettings", CollectionsKt.joinToString$default(list1, ",", null, null, 0, null, null, 62, null));
            sharedPreferences$Editor0.apply();
            this.moreIdx = 7;
        }
        else {
            Integer[] arr_integer = new Integer[0];
            for(Object object0: list0) {
                String s1 = (String)object0;
                switch(s1) {
                    case "back": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F08007B);  // drawable:baseline_chevron_left_24
                        break;
                    }
                    case "comment": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F120035);  // string:comment_btn "댓글"
                        break;
                    }
                    case "find": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F0800A8);  // drawable:find_btn
                        break;
                    }
                    case "front": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F08007C);  // drawable:baseline_chevron_right_24
                        break;
                    }
                    case "gobottom": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F0800AA);
                        break;
                    }
                    case "gotop": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F0800AD);
                        break;
                    }
                    case "home": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F08007E);  // drawable:baseline_home_24
                        break;
                    }
                    case "more": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F08007F);  // drawable:baseline_more_horiz_24
                        this.moreIdx = arr_integer.length - 1;
                        break;
                    }
                    case "notification": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F08007D);  // drawable:baseline_circle_notifications_24
                        break;
                    }
                    case "refresh": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F080080);  // drawable:baseline_refresh_24
                        break;
                    }
                    case "settings": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F080117);  // drawable:settings
                        break;
                    }
                    case "share": {
                        arr_integer = ArraysKt.plus(arr_integer, 0x7F080081);  // drawable:baseline_share_24
                    }
                }
                this.buttonList = arr_integer;
                String[] arr_s1 = this.functionList;
                Collection collection1 = new ArrayList();
                String[] arr_s2 = arr_s1;
                for(int v1 = 0; v1 < arr_s1.length; ++v1) {
                    String s2 = arr_s2[v1];
                    if(!Intrinsics.areEqual(s2, s1)) {
                        collection1.add(s2);
                    }
                }
                this.functionList = (String[])((List)collection1).toArray(new String[0]);
            }
        }
        this.buttons = new Button[0];
        this.getWrapper().removeAllViews();
        Integer[] arr_integer1 = this.buttonList;
        for(int v2 = 0; v2 < arr_integer1.length; ++v2) {
            int v3 = (int)arr_integer1[v2];
            FragmentActivity fragmentActivity1 = this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivity1, "requireActivity(...)");
            Button button0 = ButtonContainer.Companion.makeButtonView(v3, fragmentActivity1);
            this.buttons = (Button[])ArraysKt.plus(this.buttons, button0);
            this.getWrapper().addView(button0);
        }
        this.popup = new PopupMenu(this.requireContext(), this.buttons[this.moreIdx]);
        this.getWrapper().requestLayout();
    }

    @Override  // androidx.fragment.app.Fragment
    public void onConfigurationChanged(Configuration configuration0) {
        Intrinsics.checkNotNullParameter(configuration0, "newConfig");
        super.onConfigurationChanged(configuration0);
        if(0x20 == (configuration0.uiMode & 0x30)) {
            ButtonContainer.bgColor = Color.parseColor("#0F0F0F");
            ButtonContainer.textColor = Color.parseColor("#f7f7f7");
        }
        else {
            ButtonContainer.bgColor = Color.parseColor("#f7f7f7");
            ButtonContainer.textColor = Color.parseColor("#0F0F0F");
        }
        this.refreshView();
    }

    @Override  // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(layoutInflater0, "inflater");
        if(this.requireActivity() instanceof MainActivity) {
            FragmentActivity fragmentActivity0 = this.requireActivity();
            Intrinsics.checkNotNull(fragmentActivity0, "null cannot be cast to non-null type com.ruliweb.www.ruliapp.MainActivity");
            this.ruliWebView = ((MainActivity)fragmentActivity0).getRuliWebView();
            this.findCallBack = new Function0() {
                final MainActivity $parent;

                {
                    this.$parent = mainActivity0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    this.invoke();
                    return Unit.INSTANCE;
                }

                public final void invoke() {
                    this.$parent.startFind();
                }
            };
        }
        if(ButtonContainer.device == null) {
            FragmentActivity fragmentActivity1 = this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivity1, "requireActivity(...)");
            ButtonContainer.device = Utils.Companion.checkDeviceType(fragmentActivity1);
        }
        View view0 = layoutInflater0.inflate(0x7F0C0022, viewGroup0, false);  // layout:button_container
        Intrinsics.checkNotNullExpressionValue(view0, "inflate(...)");
        return view0;
    }

    @Override  // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.initContainerFunc();
    }

    @Override  // androidx.fragment.app.Fragment
    public void onViewCreated(View view0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(view0, "view");
        super.onViewCreated(view0, bundle0);
        if((this.getResources().getConfiguration().uiMode & 0x30) == 0x20) {
            ButtonContainer.bgColor = Color.parseColor("#0F0F0F");
            ButtonContainer.textColor = Color.parseColor("#f7f7f7");
        }
        else {
            ButtonContainer.bgColor = Color.parseColor("#f7f7f7");
            ButtonContainer.textColor = Color.parseColor("#0F0F0F");
        }
        this.initContainerView();
    }

    public final void refreshView() {
        this.getWrapper().setBackgroundColor(ButtonContainer.bgColor);
        for(Object object0: ViewGroupKt.getChildren(this.getWrapper())) {
            View view0 = (View)object0;
            Intrinsics.checkNotNull(view0, "null cannot be cast to non-null type android.widget.Button");
            Button button0 = (Button)view0;
            CharSequence charSequence0 = button0.getText();
            Intrinsics.checkNotNullExpressionValue(charSequence0, "getText(...)");
            if(charSequence0.length() == 0) {
                button0.getBackground().setColorFilter(new PorterDuffColorFilter(ButtonContainer.textColor, PorterDuff.Mode.SRC_ATOP));
            }
            else {
                view0.setBackgroundColor(ButtonContainer.bgColor);
                button0.setTextColor(ButtonContainer.textColor);
            }
        }
    }

    public final void setButtonList(Integer[] arr_integer) {
        Intrinsics.checkNotNullParameter(arr_integer, "<set-?>");
        this.buttonList = arr_integer;
    }

    public final void setButtons(Button[] arr_button) {
        Intrinsics.checkNotNullParameter(arr_button, "<set-?>");
        this.buttons = arr_button;
    }

    public final void setFindCallBack(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "<set-?>");
        this.findCallBack = function00;
    }

    public final void setFunctionList(String[] arr_s) {
        Intrinsics.checkNotNullParameter(arr_s, "<set-?>");
        this.functionList = arr_s;
    }

    public final void setMoreIdx(int v) {
        this.moreIdx = v;
    }

    public final void setParent(ActivityCompat activityCompat0) {
        Intrinsics.checkNotNullParameter(activityCompat0, "<set-?>");
        this.parent = activityCompat0;
    }

    public final void setRuliWebView(WebView webView0) {
        this.ruliWebView = webView0;
    }

    public final void setWrapper(LinearLayout linearLayout0) {
        Intrinsics.checkNotNullParameter(linearLayout0, "<set-?>");
        this.wrapper = linearLayout0;
    }
}

