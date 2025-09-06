package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.id;
import com.google.android.material.internal.ViewUtils;
import java.util.Calendar;

final class MaterialCalendarGridView extends GridView {
    private final Calendar dayCompute;
    private final boolean nestedScrollable;

    public MaterialCalendarGridView(Context context0) {
        this(context0, null);
    }

    public MaterialCalendarGridView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public MaterialCalendarGridView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.dayCompute = UtcDates.getUtcCalendar();
        if(MaterialDatePicker.isFullscreen(this.getContext())) {
            this.setNextFocusLeftId(id.cancel_button);
            this.setNextFocusRightId(id.confirm_button);
        }
        this.nestedScrollable = MaterialDatePicker.isNestedScrollable(this.getContext());
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setCollectionInfo(null);
            }
        });
    }

    private void gainFocus(int v, Rect rect0) {
        switch(v) {
            case 33: {
                this.setSelection(this.getAdapter().lastPositionInMonth());
                return;
            }
            case 130: {
                this.setSelection(this.getAdapter().firstPositionInMonth());
                return;
            }
            default: {
                super.onFocusChanged(true, v, rect0);
            }
        }
    }

    @Override  // android.widget.GridView
    public Adapter getAdapter() {
        return this.getAdapter();
    }

    @Override  // android.widget.GridView
    public ListAdapter getAdapter() {
        return this.getAdapter();
    }

    public MonthAdapter getAdapter() {
        return (MonthAdapter)super.getAdapter();
    }

    private View getChildAtPosition(int v) {
        return this.getChildAt(v - this.getFirstVisiblePosition());
    }

    private static int horizontalMidPoint(View view0) {
        return view0.getLeft() + view0.getWidth() / 2;
    }

    @Override  // android.widget.AbsListView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.getAdapter().notifyDataSetChanged();
    }

    @Override  // android.view.View
    protected final void onDraw(Canvas canvas0) {
        int v19;
        int v18;
        int v9;
        int v8;
        int v7;
        int v6;
        int v5;
        int v4;
        super.onDraw(canvas0);
        MonthAdapter monthAdapter0 = this.getAdapter();
        CalendarStyle calendarStyle0 = monthAdapter0.calendarStyle;
        int v = Math.max(monthAdapter0.firstPositionInMonth(), this.getFirstVisiblePosition());
        int v1 = Math.min(monthAdapter0.lastPositionInMonth(), this.getLastVisiblePosition());
        Long long0 = monthAdapter0.getItem(v);
        Long long1 = monthAdapter0.getItem(v1);
        for(Object object0: monthAdapter0.dateSelector.getSelectedRanges()) {
            Pair pair0 = (Pair)object0;
            if(pair0.first != null && pair0.second != null) {
                long v2 = (long)(((Long)pair0.first));
                long v3 = (long)(((Long)pair0.second));
                if(!MaterialCalendarGridView.skipMonth(long0, long1, ((Long)pair0.first), ((Long)pair0.second))) {
                    boolean z = ViewUtils.isLayoutRtl(this);
                    if(v2 < ((long)long0)) {
                        if(monthAdapter0.isFirstInRow(v)) {
                            v4 = 0;
                        }
                        else {
                            v4 = z ? this.getChildAtPosition(v - 1).getLeft() : this.getChildAtPosition(v - 1).getRight();
                        }
                        v5 = v4;
                        v6 = v;
                    }
                    else {
                        this.dayCompute.setTimeInMillis(v2);
                        v6 = monthAdapter0.dayToPosition(this.dayCompute.get(5));
                        v5 = MaterialCalendarGridView.horizontalMidPoint(this.getChildAtPosition(v6));
                    }
                    if(v3 > ((long)long1)) {
                        if(monthAdapter0.isLastInRow(v1)) {
                            v7 = this.getWidth();
                        }
                        else {
                            v7 = z ? this.getChildAtPosition(v1).getLeft() : this.getChildAtPosition(v1).getRight();
                        }
                        v8 = v7;
                        v9 = v1;
                    }
                    else {
                        this.dayCompute.setTimeInMillis(v3);
                        v9 = monthAdapter0.dayToPosition(this.dayCompute.get(5));
                        v8 = MaterialCalendarGridView.horizontalMidPoint(this.getChildAtPosition(v9));
                    }
                    int v10 = (int)monthAdapter0.getItemId(v6);
                    int v11 = (int)monthAdapter0.getItemId(v9);
                    while(v10 <= v11) {
                        int v12 = this.getNumColumns() * v10;
                        int v13 = v12 + this.getNumColumns() - 1;
                        View view0 = this.getChildAtPosition(v12);
                        int v14 = view0.getTop();
                        int v15 = calendarStyle0.day.getTopInset();
                        int v16 = view0.getBottom();
                        int v17 = calendarStyle0.day.getBottomInset();
                        if(z) {
                            v18 = v9 <= v13 ? v8 : 0;
                            v19 = v12 <= v6 ? v5 : this.getWidth();
                        }
                        else {
                            v18 = v12 <= v6 ? v5 : 0;
                            v19 = v9 > v13 ? this.getWidth() : v8;
                        }
                        canvas0.drawRect(((float)v18), ((float)(v14 + v15)), ((float)v19), ((float)(v16 - v17)), calendarStyle0.rangeFill);
                        ++v10;
                    }
                }
            }
        }
    }

    @Override  // android.widget.GridView
    protected void onFocusChanged(boolean z, int v, Rect rect0) {
        if(z) {
            this.gainFocus(v, rect0);
            return;
        }
        super.onFocusChanged(false, v, rect0);
    }

    @Override  // android.widget.GridView
    public boolean onKeyDown(int v, KeyEvent keyEvent0) {
        if(!super.onKeyDown(v, keyEvent0)) {
            return false;
        }
        if(this.getSelectedItemPosition() != -1 && this.getSelectedItemPosition() < this.getAdapter().firstPositionInMonth()) {
            if(19 == v) {
                this.setSelection(this.getAdapter().firstPositionInMonth());
                return true;
            }
            return false;
        }
        return true;
    }

    @Override  // android.widget.GridView
    public void onMeasure(int v, int v1) {
        if(this.nestedScrollable) {
            super.onMeasure(v, View.MeasureSpec.makeMeasureSpec(0xFFFFFF, 0x80000000));
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.getLayoutParams();
            viewGroup$LayoutParams0.height = this.getMeasuredHeight();
            return;
        }
        super.onMeasure(v, v1);
    }

    @Override  // android.widget.GridView
    public void setAdapter(Adapter adapter0) {
        this.setAdapter(((ListAdapter)adapter0));
    }

    @Override  // android.widget.GridView
    public final void setAdapter(ListAdapter listAdapter0) {
        if(!(listAdapter0 instanceof MonthAdapter)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter0);
    }

    @Override  // android.widget.GridView
    public void setSelection(int v) {
        if(v < this.getAdapter().firstPositionInMonth()) {
            super.setSelection(this.getAdapter().firstPositionInMonth());
            return;
        }
        super.setSelection(v);
    }

    private static boolean skipMonth(Long long0, Long long1, Long long2, Long long3) {
        return long0 == null || long1 == null || long2 == null || long3 == null || ((long)long2) > ((long)long1) || ((long)long3) < ((long)long0);
    }
}

