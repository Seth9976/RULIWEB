package com.google.android.material.snackbar;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.motion.MotionUtils;

public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
    private Button actionView;
    private final TimeInterpolator contentInterpolator;
    private int maxInlineActionWidth;
    private TextView messageView;

    public SnackbarContentLayout(Context context0) {
        this(context0, null);
    }

    public SnackbarContentLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.contentInterpolator = MotionUtils.resolveThemeInterpolator(context0, attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    }

    @Override  // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentIn(int v, int v1) {
        this.messageView.setAlpha(0.0f);
        this.messageView.animate().alpha(1.0f).setDuration(((long)v1)).setInterpolator(this.contentInterpolator).setStartDelay(((long)v)).start();
        if(this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(0.0f);
            this.actionView.animate().alpha(1.0f).setDuration(((long)v1)).setInterpolator(this.contentInterpolator).setStartDelay(((long)v)).start();
        }
    }

    @Override  // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentOut(int v, int v1) {
        this.messageView.setAlpha(1.0f);
        this.messageView.animate().alpha(0.0f).setDuration(((long)v1)).setInterpolator(this.contentInterpolator).setStartDelay(((long)v)).start();
        if(this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(1.0f);
            this.actionView.animate().alpha(0.0f).setDuration(((long)v1)).setInterpolator(this.contentInterpolator).setStartDelay(((long)v)).start();
        }
    }

    public Button getActionView() {
        return this.actionView;
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    @Override  // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView)this.findViewById(id.snackbar_text);
        this.actionView = (Button)this.findViewById(id.snackbar_action);
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        if(this.getOrientation() != 1) {
            int v2 = this.getResources().getDimensionPixelSize(dimen.design_snackbar_padding_vertical_2lines);
            int v3 = this.getResources().getDimensionPixelSize(dimen.design_snackbar_padding_vertical);
            Layout layout0 = this.messageView.getLayout();
            boolean z = layout0 != null && layout0.getLineCount() > 1;
            if(!z || this.maxInlineActionWidth <= 0 || this.actionView.getMeasuredWidth() <= this.maxInlineActionWidth) {
                if(!z) {
                    v2 = v3;
                }
                if(this.updateViewsWithinLayout(0, v2, v2)) {
                    super.onMeasure(v, v1);
                }
            }
            else if(this.updateViewsWithinLayout(1, v2, v2 - v3)) {
                super.onMeasure(v, v1);
            }
        }
    }

    public void setMaxInlineActionWidth(int v) {
        this.maxInlineActionWidth = v;
    }

    void updateActionTextColorAlphaIfNeeded(float f) {
        if(f != 1.0f) {
            int v = this.actionView.getCurrentTextColor();
            int v1 = MaterialColors.layer(MaterialColors.getColor(this, attr.colorSurface), v, f);
            this.actionView.setTextColor(v1);
        }
    }

    private static void updateTopBottomPadding(View view0, int v, int v1) {
        if(ViewCompat.isPaddingRelative(view0)) {
            ViewCompat.setPaddingRelative(view0, ViewCompat.getPaddingStart(view0), v, ViewCompat.getPaddingEnd(view0), v1);
            return;
        }
        view0.setPadding(view0.getPaddingLeft(), v, view0.getPaddingRight(), v1);
    }

    private boolean updateViewsWithinLayout(int v, int v1, int v2) {
        boolean z;
        if(v == this.getOrientation()) {
            z = false;
        }
        else {
            this.setOrientation(v);
            z = true;
        }
        if(this.messageView.getPaddingTop() == v1 && this.messageView.getPaddingBottom() == v2) {
            return z;
        }
        SnackbarContentLayout.updateTopBottomPadding(this.messageView, v1, v2);
        return true;
    }
}

