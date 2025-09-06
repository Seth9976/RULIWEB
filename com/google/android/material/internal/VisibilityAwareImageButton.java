package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class VisibilityAwareImageButton extends ImageButton {
    private int userSetVisibility;

    public VisibilityAwareImageButton(Context context0) {
        this(context0, null);
    }

    public VisibilityAwareImageButton(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public VisibilityAwareImageButton(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.userSetVisibility = this.getVisibility();
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    public final void internalSetVisibility(int v, boolean z) {
        super.setVisibility(v);
        if(z) {
            this.userSetVisibility = v;
        }
    }

    @Override  // android.widget.ImageView
    public void setVisibility(int v) {
        this.internalSetVisibility(v, true);
    }
}

