package com.google.android.material.datepicker;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView;

class SmoothCalendarLayoutManager extends LinearLayoutManager {
    private static final float MILLISECONDS_PER_INCH = 100.0f;

    SmoothCalendarLayoutManager(Context context0, int v, boolean z) {
        super(context0, v, z);
    }

    @Override  // androidx.recyclerview.widget.LinearLayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView0, State recyclerView$State0, int v) {
        com.google.android.material.datepicker.SmoothCalendarLayoutManager.1 smoothCalendarLayoutManager$10 = new LinearSmoothScroller(recyclerView0.getContext()) {
            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics0) {
                return 100.0f / ((float)displayMetrics0.densityDpi);
            }
        };
        smoothCalendarLayoutManager$10.setTargetPosition(v);
        this.startSmoothScroll(smoothCalendarLayoutManager$10);
    }
}

