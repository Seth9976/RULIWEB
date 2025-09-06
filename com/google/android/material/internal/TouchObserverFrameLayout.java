package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

public class TouchObserverFrameLayout extends FrameLayout {
    private View.OnTouchListener onTouchListener;

    public TouchObserverFrameLayout(Context context0) {
        super(context0);
    }

    public TouchObserverFrameLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public TouchObserverFrameLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        View.OnTouchListener view$OnTouchListener0 = this.onTouchListener;
        if(view$OnTouchListener0 != null) {
            view$OnTouchListener0.onTouch(this, motionEvent0);
        }
        return super.onInterceptTouchEvent(motionEvent0);
    }

    @Override  // android.view.View
    public void setOnTouchListener(View.OnTouchListener view$OnTouchListener0) {
        this.onTouchListener = view$OnTouchListener0;
    }
}

