package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R.styleable;
import androidx.core.widget.PopupWindowCompat;

class AppCompatPopupWindow extends PopupWindow {
    private static final boolean COMPAT_OVERLAP_ANCHOR;
    private boolean mOverlapAnchor;

    static {
        AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR = false;
    }

    public AppCompatPopupWindow(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.init(context0, attributeSet0, v, 0);
    }

    public AppCompatPopupWindow(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.init(context0, attributeSet0, v, v1);
    }

    private void init(Context context0, AttributeSet attributeSet0, int v, int v1) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.PopupWindow, v, v1);
        if(tintTypedArray0.hasValue(styleable.PopupWindow_overlapAnchor)) {
            this.setSupportOverlapAnchor(tintTypedArray0.getBoolean(styleable.PopupWindow_overlapAnchor, false));
        }
        this.setBackgroundDrawable(tintTypedArray0.getDrawable(styleable.PopupWindow_android_popupBackground));
        tintTypedArray0.recycle();
    }

    private void setSupportOverlapAnchor(boolean z) {
        if(AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR) {
            this.mOverlapAnchor = z;
            return;
        }
        PopupWindowCompat.setOverlapAnchor(this, z);
    }

    @Override  // android.widget.PopupWindow
    public void showAsDropDown(View view0, int v, int v1) {
        if(AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            v1 -= view0.getHeight();
        }
        super.showAsDropDown(view0, v, v1);
    }

    @Override  // android.widget.PopupWindow
    public void showAsDropDown(View view0, int v, int v1, int v2) {
        if(AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            v1 -= view0.getHeight();
        }
        super.showAsDropDown(view0, v, v1, v2);
    }

    @Override  // android.widget.PopupWindow
    public void update(View view0, int v, int v1, int v2, int v3) {
        if(AppCompatPopupWindow.COMPAT_OVERLAP_ANCHOR && this.mOverlapAnchor) {
            v1 -= view0.getHeight();
        }
        super.update(view0, v, v1, v2, v3);
    }
}

