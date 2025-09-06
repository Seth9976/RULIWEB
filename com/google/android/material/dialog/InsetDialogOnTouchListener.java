package com.google.android.material.dialog;

import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewConfiguration;

public class InsetDialogOnTouchListener implements View.OnTouchListener {
    private final Dialog dialog;
    private final int leftInset;
    private final int prePieSlop;
    private final int topInset;

    public InsetDialogOnTouchListener(Dialog dialog0, Rect rect0) {
        this.dialog = dialog0;
        this.leftInset = rect0.left;
        this.topInset = rect0.top;
        this.prePieSlop = ViewConfiguration.get(dialog0.getContext()).getScaledWindowTouchSlop();
    }

    @Override  // android.view.View$OnTouchListener
    public boolean onTouch(View view0, MotionEvent motionEvent0) {
        View view1 = view0.findViewById(0x1020002);
        int v = view1.getLeft();
        int v1 = this.leftInset + v;
        int v2 = view1.getWidth();
        int v3 = view1.getTop();
        int v4 = this.topInset + v3;
        if(new RectF(((float)v1), ((float)v4), ((float)(v2 + v1)), ((float)(view1.getHeight() + v4))).contains(motionEvent0.getX(), motionEvent0.getY())) {
            return false;
        }
        MotionEvent motionEvent1 = MotionEvent.obtain(motionEvent0);
        if(motionEvent0.getAction() == 1) {
            motionEvent1.setAction(4);
        }
        if(Build.VERSION.SDK_INT < 28) {
            motionEvent1.setAction(0);
            motionEvent1.setLocation(((float)(-this.prePieSlop - 1)), ((float)(-this.prePieSlop - 1)));
        }
        view0.performClick();
        return this.dialog.onTouchEvent(motionEvent1);
    }
}

