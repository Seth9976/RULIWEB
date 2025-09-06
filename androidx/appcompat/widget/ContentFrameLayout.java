package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;

public class ContentFrameLayout extends FrameLayout {
    public interface OnAttachListener {
        void onAttachedFromWindow();

        void onDetachedFromWindow();
    }

    private OnAttachListener mAttachListener;
    private final Rect mDecorPadding;
    private TypedValue mFixedHeightMajor;
    private TypedValue mFixedHeightMinor;
    private TypedValue mFixedWidthMajor;
    private TypedValue mFixedWidthMinor;
    private TypedValue mMinWidthMajor;
    private TypedValue mMinWidthMinor;

    public ContentFrameLayout(Context context0) {
        this(context0, null);
    }

    public ContentFrameLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ContentFrameLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mDecorPadding = new Rect();
    }

    public void dispatchFitSystemWindows(Rect rect0) {
        this.fitSystemWindows(rect0);
    }

    public TypedValue getFixedHeightMajor() {
        if(this.mFixedHeightMajor == null) {
            this.mFixedHeightMajor = new TypedValue();
        }
        return this.mFixedHeightMajor;
    }

    public TypedValue getFixedHeightMinor() {
        if(this.mFixedHeightMinor == null) {
            this.mFixedHeightMinor = new TypedValue();
        }
        return this.mFixedHeightMinor;
    }

    public TypedValue getFixedWidthMajor() {
        if(this.mFixedWidthMajor == null) {
            this.mFixedWidthMajor = new TypedValue();
        }
        return this.mFixedWidthMajor;
    }

    public TypedValue getFixedWidthMinor() {
        if(this.mFixedWidthMinor == null) {
            this.mFixedWidthMinor = new TypedValue();
        }
        return this.mFixedWidthMinor;
    }

    public TypedValue getMinWidthMajor() {
        if(this.mMinWidthMajor == null) {
            this.mMinWidthMajor = new TypedValue();
        }
        return this.mMinWidthMajor;
    }

    public TypedValue getMinWidthMinor() {
        if(this.mMinWidthMinor == null) {
            this.mMinWidthMinor = new TypedValue();
        }
        return this.mMinWidthMinor;
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        OnAttachListener contentFrameLayout$OnAttachListener0 = this.mAttachListener;
        if(contentFrameLayout$OnAttachListener0 != null) {
            contentFrameLayout$OnAttachListener0.onAttachedFromWindow();
        }
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        OnAttachListener contentFrameLayout$OnAttachListener0 = this.mAttachListener;
        if(contentFrameLayout$OnAttachListener0 != null) {
            contentFrameLayout$OnAttachListener0.onDetachedFromWindow();
        }
    }

    @Override  // android.widget.FrameLayout
    protected void onMeasure(int v, int v1) {
        int v8;
        int v5;
        int v4;
        boolean z2;
        DisplayMetrics displayMetrics0 = this.getContext().getResources().getDisplayMetrics();
        boolean z = true;
        boolean z1 = displayMetrics0.widthPixels < displayMetrics0.heightPixels;
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getMode(v1);
        if(v2 == 0x80000000) {
            TypedValue typedValue0 = z1 ? this.mFixedWidthMinor : this.mFixedWidthMajor;
            if(typedValue0 == null) {
                z2 = false;
            }
            else {
                switch(typedValue0.type) {
                    case 0: {
                        z2 = false;
                        break;
                    }
                    case 5: {
                        v4 = (int)typedValue0.getDimension(displayMetrics0);
                        goto label_16;
                    }
                    case 6: {
                        v4 = (int)typedValue0.getFraction(((float)displayMetrics0.widthPixels), ((float)displayMetrics0.widthPixels));
                    label_16:
                        if(v4 > 0) {
                            v = View.MeasureSpec.makeMeasureSpec(Math.min(v4 - (this.mDecorPadding.left + this.mDecorPadding.right), View.MeasureSpec.getSize(v)), 0x40000000);
                            z2 = true;
                        }
                        else {
                            z2 = false;
                        }
                        break;
                    }
                    default: {
                        v4 = 0;
                        goto label_16;
                    }
                }
            }
        }
        else {
            z2 = false;
        }
        if(v3 == 0x80000000) {
            TypedValue typedValue1 = z1 ? this.mFixedHeightMajor : this.mFixedHeightMinor;
            if(typedValue1 != null) {
                switch(typedValue1.type) {
                    case 0: {
                        break;
                    }
                    case 5: {
                        v5 = (int)typedValue1.getDimension(displayMetrics0);
                        goto label_34;
                    }
                    case 6: {
                        v5 = (int)typedValue1.getFraction(((float)displayMetrics0.heightPixels), ((float)displayMetrics0.heightPixels));
                    label_34:
                        if(v5 > 0) {
                            v1 = View.MeasureSpec.makeMeasureSpec(Math.min(v5 - (this.mDecorPadding.top + this.mDecorPadding.bottom), View.MeasureSpec.getSize(v1)), 0x40000000);
                        }
                        break;
                    }
                    default: {
                        v5 = 0;
                        goto label_34;
                    }
                }
            }
        }
        super.onMeasure(v, v1);
        int v6 = this.getMeasuredWidth();
        int v7 = View.MeasureSpec.makeMeasureSpec(v6, 0x40000000);
        if(z2 || v2 != 0x80000000) {
            z = false;
        }
        else {
            TypedValue typedValue2 = z1 ? this.mMinWidthMinor : this.mMinWidthMajor;
            if(typedValue2 == null) {
                z = false;
            }
            else {
                switch(typedValue2.type) {
                    case 0: {
                        z = false;
                        goto label_60;
                    }
                    case 5: {
                        v8 = (int)typedValue2.getDimension(displayMetrics0);
                        break;
                    }
                    case 6: {
                        v8 = (int)typedValue2.getFraction(((float)displayMetrics0.widthPixels), ((float)displayMetrics0.widthPixels));
                        break;
                    }
                    default: {
                        v8 = 0;
                    }
                }
                if(v8 > 0) {
                    v8 -= this.mDecorPadding.left + this.mDecorPadding.right;
                }
                if(v6 < v8) {
                    v7 = View.MeasureSpec.makeMeasureSpec(v8, 0x40000000);
                }
                else {
                    z = false;
                }
            }
        }
    label_60:
        if(z) {
            super.onMeasure(v7, v1);
        }
    }

    public void setAttachListener(OnAttachListener contentFrameLayout$OnAttachListener0) {
        this.mAttachListener = contentFrameLayout$OnAttachListener0;
    }

    public void setDecorPadding(int v, int v1, int v2, int v3) {
        this.mDecorPadding.set(v, v1, v2, v3);
        if(this.isLaidOut()) {
            this.requestLayout();
        }
    }
}

