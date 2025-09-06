package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;

public class ScrimInsetsFrameLayout extends FrameLayout {
    private boolean drawBottomInsetForeground;
    private boolean drawLeftInsetForeground;
    private boolean drawRightInsetForeground;
    private boolean drawTopInsetForeground;
    Drawable insetForeground;
    Rect insets;
    private Rect tempRect;

    public ScrimInsetsFrameLayout(Context context0) {
        this(context0, null);
    }

    public ScrimInsetsFrameLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ScrimInsetsFrameLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.tempRect = new Rect();
        this.drawTopInsetForeground = true;
        this.drawBottomInsetForeground = true;
        this.drawLeftInsetForeground = true;
        this.drawRightInsetForeground = true;
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.ScrimInsetsFrameLayout, v, style.Widget_Design_ScrimInsetsFrameLayout, new int[0]);
        this.insetForeground = typedArray0.getDrawable(styleable.ScrimInsetsFrameLayout_insetForeground);
        typedArray0.recycle();
        this.setWillNotDraw(true);
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            @Override  // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                if(ScrimInsetsFrameLayout.this.insets == null) {
                    ScrimInsetsFrameLayout.this.insets = new Rect();
                }
                ScrimInsetsFrameLayout.this.insets.set(windowInsetsCompat0.getSystemWindowInsetLeft(), windowInsetsCompat0.getSystemWindowInsetTop(), windowInsetsCompat0.getSystemWindowInsetRight(), windowInsetsCompat0.getSystemWindowInsetBottom());
                ScrimInsetsFrameLayout.this.onInsetsChanged(windowInsetsCompat0);
                boolean z = !windowInsetsCompat0.hasSystemWindowInsets() || ScrimInsetsFrameLayout.this.insetForeground == null;
                ScrimInsetsFrameLayout.this.setWillNotDraw(z);
                ViewCompat.postInvalidateOnAnimation(ScrimInsetsFrameLayout.this);
                return windowInsetsCompat0.consumeSystemWindowInsets();
            }
        });
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
        int v = this.getWidth();
        int v1 = this.getHeight();
        if(this.insets != null && this.insetForeground != null) {
            int v2 = canvas0.save();
            canvas0.translate(((float)this.getScrollX()), ((float)this.getScrollY()));
            if(this.drawTopInsetForeground) {
                this.tempRect.set(0, 0, v, this.insets.top);
                this.insetForeground.setBounds(this.tempRect);
                this.insetForeground.draw(canvas0);
            }
            if(this.drawBottomInsetForeground) {
                this.tempRect.set(0, v1 - this.insets.bottom, v, v1);
                this.insetForeground.setBounds(this.tempRect);
                this.insetForeground.draw(canvas0);
            }
            if(this.drawLeftInsetForeground) {
                this.tempRect.set(0, this.insets.top, this.insets.left, v1 - this.insets.bottom);
                this.insetForeground.setBounds(this.tempRect);
                this.insetForeground.draw(canvas0);
            }
            if(this.drawRightInsetForeground) {
                this.tempRect.set(v - this.insets.right, this.insets.top, v, v1 - this.insets.bottom);
                this.insetForeground.setBounds(this.tempRect);
                this.insetForeground.draw(canvas0);
            }
            canvas0.restoreToCount(v2);
        }
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable drawable0 = this.insetForeground;
        if(drawable0 != null) {
            drawable0.setCallback(this);
        }
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Drawable drawable0 = this.insetForeground;
        if(drawable0 != null) {
            drawable0.setCallback(null);
        }
    }

    protected void onInsetsChanged(WindowInsetsCompat windowInsetsCompat0) {
    }

    public void setDrawBottomInsetForeground(boolean z) {
        this.drawBottomInsetForeground = z;
    }

    public void setDrawLeftInsetForeground(boolean z) {
        this.drawLeftInsetForeground = z;
    }

    public void setDrawRightInsetForeground(boolean z) {
        this.drawRightInsetForeground = z;
    }

    public void setDrawTopInsetForeground(boolean z) {
        this.drawTopInsetForeground = z;
    }

    public void setScrimInsetForeground(Drawable drawable0) {
        this.insetForeground = drawable0;
    }
}

