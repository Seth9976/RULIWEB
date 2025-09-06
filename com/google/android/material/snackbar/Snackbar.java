package com.google.android.material.snackbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R.attr;
import com.google.android.material.R.layout;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public class Snackbar extends BaseTransientBottomBar {
    public static class Callback extends BaseCallback {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        public void onDismissed(Snackbar snackbar0, int v) {
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$BaseCallback
        public void onDismissed(Object object0, int v) {
        }

        public void onShown(Snackbar snackbar0) {
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$BaseCallback
        public void onShown(Object object0) {
        }
    }

    public static final class SnackbarLayout extends SnackbarBaseLayout {
        public SnackbarLayout(Context context0) {
            super(context0);
        }

        public SnackbarLayout(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout
        protected void onMeasure(int v, int v1) {
            super.onMeasure(v, v1);
            int v2 = this.getChildCount();
            int v3 = this.getMeasuredWidth();
            int v4 = this.getPaddingLeft();
            int v5 = this.getPaddingRight();
            for(int v6 = 0; v6 < v2; ++v6) {
                View view0 = this.getChildAt(v6);
                if(view0.getLayoutParams().width == -1) {
                    view0.measure(View.MeasureSpec.makeMeasureSpec(v3 - v4 - v5, 0x40000000), View.MeasureSpec.makeMeasureSpec(view0.getMeasuredHeight(), 0x40000000));
                }
            }
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout
        public void setBackground(Drawable drawable0) {
            super.setBackground(drawable0);
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout
        public void setBackgroundDrawable(Drawable drawable0) {
            super.setBackgroundDrawable(drawable0);
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout
        public void setBackgroundTintList(ColorStateList colorStateList0) {
            super.setBackgroundTintList(colorStateList0);
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout
        public void setBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
            super.setBackgroundTintMode(porterDuff$Mode0);
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout
        public void setLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super.setLayoutParams(viewGroup$LayoutParams0);
        }

        @Override  // com.google.android.material.snackbar.BaseTransientBottomBar$SnackbarBaseLayout
        public void setOnClickListener(View.OnClickListener view$OnClickListener0) {
            super.setOnClickListener(view$OnClickListener0);
        }
    }

    private static final int[] SNACKBAR_BUTTON_STYLE_ATTR;
    private static final int[] SNACKBAR_CONTENT_STYLE_ATTRS;
    private final AccessibilityManager accessibilityManager;
    private BaseCallback callback;
    private boolean hasAction;

    static {
        Snackbar.SNACKBAR_BUTTON_STYLE_ATTR = new int[]{attr.snackbarButtonStyle};
        Snackbar.SNACKBAR_CONTENT_STYLE_ATTRS = new int[]{attr.snackbarButtonStyle, attr.snackbarTextViewStyle};
    }

    private Snackbar(Context context0, ViewGroup viewGroup0, View view0, ContentViewCallback contentViewCallback0) {
        super(context0, viewGroup0, view0, contentViewCallback0);
        this.accessibilityManager = (AccessibilityManager)viewGroup0.getContext().getSystemService("accessibility");
    }

    @Override  // com.google.android.material.snackbar.BaseTransientBottomBar
    public void dismiss() {
        super.dismiss();
    }

    private static ViewGroup findSuitableParent(View view0) {
        ViewGroup viewGroup0 = null;
        do {
            if(view0 instanceof CoordinatorLayout) {
                return (ViewGroup)view0;
            }
            if(view0 instanceof FrameLayout) {
                if(view0.getId() == 0x1020002) {
                    return (ViewGroup)view0;
                }
                viewGroup0 = (ViewGroup)view0;
            }
            if(view0 != null) {
                ViewParent viewParent0 = view0.getParent();
                view0 = viewParent0 instanceof View ? ((View)viewParent0) : null;
            }
        }
        while(view0 != null);
        return viewGroup0;
    }

    private Button getActionView() {
        return this.getContentLayout().getActionView();
    }

    private SnackbarContentLayout getContentLayout() {
        return (SnackbarContentLayout)this.view.getChildAt(0);
    }

    @Override  // com.google.android.material.snackbar.BaseTransientBottomBar
    public int getDuration() {
        int v = super.getDuration();
        if(v == -2) {
            return -2;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            return this.hasAction ? LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.accessibilityManager, v, 7) : LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.accessibilityManager, v, 3);
        }
        return !this.hasAction || !this.accessibilityManager.isTouchExplorationEnabled() ? v : -2;
    }

    private TextView getMessageView() {
        return this.getContentLayout().getMessageView();
    }

    @Deprecated
    protected static boolean hasSnackbarButtonStyleAttr(Context context0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(Snackbar.SNACKBAR_BUTTON_STYLE_ATTR);
        int v = typedArray0.getResourceId(0, -1);
        typedArray0.recycle();
        return v != -1;
    }

    private static boolean hasSnackbarContentStyleAttrs(Context context0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(Snackbar.SNACKBAR_CONTENT_STYLE_ATTRS);
        int v = typedArray0.getResourceId(0, -1);
        int v1 = typedArray0.getResourceId(1, -1);
        typedArray0.recycle();
        return v != -1 && v1 != -1;
    }

    @Override  // com.google.android.material.snackbar.BaseTransientBottomBar
    public boolean isShown() {
        return super.isShown();
    }

    // 检测为 Lambda 实现
    void lambda$setAction$0$com-google-android-material-snackbar-Snackbar(View.OnClickListener view$OnClickListener0, View view0) [...]

    public static Snackbar make(Context context0, View view0, CharSequence charSequence0, int v) {
        return Snackbar.makeInternal(context0, view0, charSequence0, v);
    }

    public static Snackbar make(View view0, int v, int v1) {
        return Snackbar.make(view0, view0.getResources().getText(v), v1);
    }

    public static Snackbar make(View view0, CharSequence charSequence0, int v) {
        return Snackbar.makeInternal(null, view0, charSequence0, v);
    }

    private static Snackbar makeInternal(Context context0, View view0, CharSequence charSequence0, int v) {
        ViewGroup viewGroup0 = Snackbar.findSuitableParent(view0);
        if(viewGroup0 == null) {
            throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
        }
        if(context0 == null) {
            context0 = viewGroup0.getContext();
        }
        SnackbarContentLayout snackbarContentLayout0 = (SnackbarContentLayout)LayoutInflater.from(context0).inflate((Snackbar.hasSnackbarContentStyleAttrs(context0) ? layout.mtrl_layout_snackbar_include : layout.design_layout_snackbar_include), viewGroup0, false);
        Snackbar snackbar0 = new Snackbar(context0, viewGroup0, snackbarContentLayout0, snackbarContentLayout0);
        snackbar0.setText(charSequence0);
        snackbar0.setDuration(v);
        return snackbar0;
    }

    public Snackbar setAction(int v, View.OnClickListener view$OnClickListener0) {
        return this.setAction(this.getContext().getText(v), view$OnClickListener0);
    }

    public Snackbar setAction(CharSequence charSequence0, View.OnClickListener view$OnClickListener0) {
        Button button0 = this.getActionView();
        if(!TextUtils.isEmpty(charSequence0) && view$OnClickListener0 != null) {
            this.hasAction = true;
            button0.setVisibility(0);
            button0.setText(charSequence0);
            button0.setOnClickListener((View view0) -> {
                view$OnClickListener0.onClick(view0);
                this.dispatchDismiss(1);
            });
            return this;
        }
        button0.setVisibility(8);
        button0.setOnClickListener(null);
        this.hasAction = false;
        return this;
    }

    public Snackbar setActionTextColor(int v) {
        this.getActionView().setTextColor(v);
        return this;
    }

    public Snackbar setActionTextColor(ColorStateList colorStateList0) {
        this.getActionView().setTextColor(colorStateList0);
        return this;
    }

    public Snackbar setBackgroundTint(int v) {
        return this.setBackgroundTintList(ColorStateList.valueOf(v));
    }

    public Snackbar setBackgroundTintList(ColorStateList colorStateList0) {
        this.view.setBackgroundTintList(colorStateList0);
        return this;
    }

    public Snackbar setBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.view.setBackgroundTintMode(porterDuff$Mode0);
        return this;
    }

    @Deprecated
    public Snackbar setCallback(Callback snackbar$Callback0) {
        BaseCallback baseTransientBottomBar$BaseCallback0 = this.callback;
        if(baseTransientBottomBar$BaseCallback0 != null) {
            this.removeCallback(baseTransientBottomBar$BaseCallback0);
        }
        if(snackbar$Callback0 != null) {
            this.addCallback(snackbar$Callback0);
        }
        this.callback = snackbar$Callback0;
        return this;
    }

    public Snackbar setMaxInlineActionWidth(int v) {
        this.getContentLayout().setMaxInlineActionWidth(v);
        return this;
    }

    public Snackbar setText(int v) {
        return this.setText(this.getContext().getText(v));
    }

    public Snackbar setText(CharSequence charSequence0) {
        this.getMessageView().setText(charSequence0);
        return this;
    }

    public Snackbar setTextColor(int v) {
        this.getMessageView().setTextColor(v);
        return this;
    }

    public Snackbar setTextColor(ColorStateList colorStateList0) {
        this.getMessageView().setTextColor(colorStateList0);
        return this;
    }

    public Snackbar setTextMaxLines(int v) {
        this.getMessageView().setMaxLines(v);
        return this;
    }

    @Override  // com.google.android.material.snackbar.BaseTransientBottomBar
    public void show() {
        super.show();
    }
}

