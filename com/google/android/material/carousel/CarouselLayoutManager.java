package com.google.android.material.carousel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import androidx.recyclerview.widget.RecyclerView.Recycler;
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R.dimen;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CarouselLayoutManager extends LayoutManager implements ScrollVectorProvider, Carousel {
    static final class ChildCalculations {
        final float center;
        final View child;
        final float offsetCenter;
        final KeylineRange range;

        ChildCalculations(View view0, float f, float f1, KeylineRange carouselLayoutManager$KeylineRange0) {
            this.child = view0;
            this.center = f;
            this.offsetCenter = f1;
            this.range = carouselLayoutManager$KeylineRange0;
        }
    }

    static class DebugItemDecoration extends ItemDecoration {
        private List keylines;
        private final Paint linePaint;

        DebugItemDecoration() {
            Paint paint0 = new Paint();
            this.linePaint = paint0;
            this.keylines = Collections.unmodifiableList(new ArrayList());
            paint0.setStrokeWidth(5.0f);
            paint0.setColor(0xFFFF00FF);
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
        public void onDrawOver(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
            super.onDrawOver(canvas0, recyclerView0, recyclerView$State0);
            float f = recyclerView0.getResources().getDimension(dimen.m3_carousel_debug_keyline_width);
            this.linePaint.setStrokeWidth(f);
            for(Object object0: this.keylines) {
                Keyline keylineState$Keyline0 = (Keyline)object0;
                int v = ColorUtils.blendARGB(0xFFFF00FF, 0xFF0000FF, keylineState$Keyline0.mask);
                this.linePaint.setColor(v);
                if(((CarouselLayoutManager)recyclerView0.getLayoutManager()).isHorizontal()) {
                    float f1 = (float)((CarouselLayoutManager)recyclerView0.getLayoutManager()).getParentTop();
                    float f2 = (float)((CarouselLayoutManager)recyclerView0.getLayoutManager()).getParentBottom();
                    canvas0.drawLine(keylineState$Keyline0.locOffset, f1, keylineState$Keyline0.locOffset, f2, this.linePaint);
                }
                else {
                    float f3 = (float)((CarouselLayoutManager)recyclerView0.getLayoutManager()).getParentLeft();
                    float f4 = (float)((CarouselLayoutManager)recyclerView0.getLayoutManager()).getParentRight();
                    canvas0.drawLine(f3, keylineState$Keyline0.locOffset, f4, keylineState$Keyline0.locOffset, this.linePaint);
                }
            }
        }

        void setKeylines(List list0) {
            this.keylines = Collections.unmodifiableList(list0);
        }
    }

    static class KeylineRange {
        final Keyline leftOrTop;
        final Keyline rightOrBottom;

        KeylineRange(Keyline keylineState$Keyline0, Keyline keylineState$Keyline1) {
            Preconditions.checkArgument(keylineState$Keyline0.loc <= keylineState$Keyline1.loc);
            this.leftOrTop = keylineState$Keyline0;
            this.rightOrBottom = keylineState$Keyline1;
        }
    }

    static class LayoutDirection {
        private static final int INVALID_LAYOUT = 0x80000000;
        private static final int LAYOUT_END = 1;
        private static final int LAYOUT_START = -1;

    }

    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_START = 0;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "CarouselLayoutManager";
    public static final int VERTICAL = 1;
    private int carouselAlignment;
    private CarouselStrategy carouselStrategy;
    private int currentEstimatedPosition;
    private int currentFillStartPosition;
    private KeylineState currentKeylineState;
    private final DebugItemDecoration debugItemDecoration;
    private boolean isDebuggingEnabled;
    private KeylineStateList keylineStateList;
    private Map keylineStatePositionMap;
    private int lastItemCount;
    int maxScroll;
    int minScroll;
    private CarouselOrientationHelper orientationHelper;
    private final View.OnLayoutChangeListener recyclerViewSizeChangeListener;
    int scrollOffset;

    // 检测为 Lambda 实现
    public static void $r8$lambda$EVyYoVT11DpL60suE9bxns8qbCM(CarouselLayoutManager carouselLayoutManager0) [...]

    public CarouselLayoutManager() {
        this(new MultiBrowseCarouselStrategy());
    }

    public CarouselLayoutManager(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = (View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) -> {
            if(v == v4 && v1 == v5 && v2 == v6 && v3 == v7) {
                return;
            }
            view0.post(() -> this.refreshKeylineState());
        };
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        this.setCarouselStrategy(new MultiBrowseCarouselStrategy());
        this.setCarouselAttributes(context0, attributeSet0);
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy0) {
        this(carouselStrategy0, 0);
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy0, int v) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = (View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) -> {
            if(v == v4 && v1 == v5 && v2 == v6 && v3 == v7) {
                return;
            }
            view0.post(() -> this.refreshKeylineState());
        };
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        this.setCarouselStrategy(carouselStrategy0);
        this.setOrientation(v);
    }

    private void addAndLayoutView(View view0, int v, ChildCalculations carouselLayoutManager$ChildCalculations0) {
        float f = this.currentKeylineState.getItemSize();
        this.addView(view0, v);
        this.orientationHelper.layoutDecoratedWithMargins(view0, ((int)(carouselLayoutManager$ChildCalculations0.offsetCenter - f / 2.0f)), ((int)(carouselLayoutManager$ChildCalculations0.offsetCenter + f / 2.0f)));
        this.updateChildMaskForLocation(view0, carouselLayoutManager$ChildCalculations0.center, carouselLayoutManager$ChildCalculations0.range);
    }

    // 去混淆评级： 低(20)
    private float addEnd(float f, float f1) {
        return this.isLayoutRtl() ? f - f1 : f + f1;
    }

    // 去混淆评级： 低(20)
    private float addStart(float f, float f1) {
        return this.isLayoutRtl() ? f + f1 : f - f1;
    }

    private void addViewAtPosition(Recycler recyclerView$Recycler0, int v, int v1) {
        if(v >= 0 && v < this.getItemCount()) {
            ChildCalculations carouselLayoutManager$ChildCalculations0 = this.makeChildCalculations(recyclerView$Recycler0, this.calculateChildStartForFill(v), v);
            this.addAndLayoutView(carouselLayoutManager$ChildCalculations0.child, v1, carouselLayoutManager$ChildCalculations0);
        }
    }

    private void addViewsEnd(Recycler recyclerView$Recycler0, State recyclerView$State0, int v) {
        float f = this.calculateChildStartForFill(v);
        while(v < recyclerView$State0.getItemCount()) {
            ChildCalculations carouselLayoutManager$ChildCalculations0 = this.makeChildCalculations(recyclerView$Recycler0, f, v);
            if(this.isLocOffsetOutOfFillBoundsEnd(carouselLayoutManager$ChildCalculations0.offsetCenter, carouselLayoutManager$ChildCalculations0.range)) {
                break;
            }
            f = this.addEnd(f, this.currentKeylineState.getItemSize());
            if(!this.isLocOffsetOutOfFillBoundsStart(carouselLayoutManager$ChildCalculations0.offsetCenter, carouselLayoutManager$ChildCalculations0.range)) {
                this.addAndLayoutView(carouselLayoutManager$ChildCalculations0.child, -1, carouselLayoutManager$ChildCalculations0);
            }
            ++v;
        }
    }

    private void addViewsStart(Recycler recyclerView$Recycler0, int v) {
        float f = this.calculateChildStartForFill(v);
        while(v >= 0) {
            ChildCalculations carouselLayoutManager$ChildCalculations0 = this.makeChildCalculations(recyclerView$Recycler0, f, v);
            if(this.isLocOffsetOutOfFillBoundsStart(carouselLayoutManager$ChildCalculations0.offsetCenter, carouselLayoutManager$ChildCalculations0.range)) {
                break;
            }
            f = this.addStart(f, this.currentKeylineState.getItemSize());
            if(!this.isLocOffsetOutOfFillBoundsEnd(carouselLayoutManager$ChildCalculations0.offsetCenter, carouselLayoutManager$ChildCalculations0.range)) {
                this.addAndLayoutView(carouselLayoutManager$ChildCalculations0.child, 0, carouselLayoutManager$ChildCalculations0);
            }
            --v;
        }
    }

    private float calculateChildOffsetCenterForLocation(View view0, float f, KeylineRange carouselLayoutManager$KeylineRange0) {
        float f1 = AnimationUtils.lerp(carouselLayoutManager$KeylineRange0.leftOrTop.locOffset, carouselLayoutManager$KeylineRange0.rightOrBottom.locOffset, carouselLayoutManager$KeylineRange0.leftOrTop.loc, carouselLayoutManager$KeylineRange0.rightOrBottom.loc, f);
        Keyline keylineState$Keyline0 = this.currentKeylineState.getFirstKeyline();
        if(carouselLayoutManager$KeylineRange0.rightOrBottom != keylineState$Keyline0) {
            Keyline keylineState$Keyline1 = this.currentKeylineState.getLastKeyline();
            if(carouselLayoutManager$KeylineRange0.leftOrTop != keylineState$Keyline1) {
                return f1;
            }
        }
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        float f2 = this.orientationHelper.getMaskMargins(recyclerView$LayoutParams0);
        return f1 + (f - carouselLayoutManager$KeylineRange0.rightOrBottom.loc) * (1.0f - carouselLayoutManager$KeylineRange0.rightOrBottom.mask + f2 / this.currentKeylineState.getItemSize());
    }

    private float calculateChildStartForFill(int v) {
        return this.addEnd(((float)(this.getParentStart() - this.scrollOffset)), this.currentKeylineState.getItemSize() * ((float)v));
    }

    private int calculateEndScroll(State recyclerView$State0, KeylineStateList keylineStateList0) {
        boolean z = this.isLayoutRtl();
        KeylineState keylineState0 = z ? keylineStateList0.getStartState() : keylineStateList0.getEndState();
        Keyline keylineState$Keyline0 = z ? keylineState0.getFirstFocalKeyline() : keylineState0.getLastFocalKeyline();
        int v = recyclerView$State0.getItemCount();
        float f = (float)this.getParentStart();
        float f1 = (float)this.getParentEnd();
        int v1 = (int)(((float)(v - 1)) * keylineState0.getItemSize() * (z ? -1.0f : 1.0f) - (keylineState$Keyline0.loc - f) + (f1 - keylineState$Keyline0.loc) + (z ? -keylineState$Keyline0.leftOrTopPaddingShift : keylineState$Keyline0.rightOrBottomPaddingShift));
        return z ? Math.min(0, v1) : Math.max(0, v1);
    }

    int calculateScrollDeltaToMakePositionVisible(int v) {
        float f = (float)this.getScrollOffsetForPosition(v, this.getKeylineStateForPosition(v));
        return (int)(((float)this.scrollOffset) - f);
    }

    private static int calculateShouldScrollBy(int v, int v1, int v2, int v3) {
        int v4 = v1 + v;
        if(v4 < v2) {
            return v2 - v1;
        }
        return v4 <= v3 ? v : v3 - v1;
    }

    private int calculateStartScroll(KeylineStateList keylineStateList0) {
        boolean z = this.isLayoutRtl();
        KeylineState keylineState0 = z ? keylineStateList0.getEndState() : keylineStateList0.getStartState();
        float f = this.addStart((z ? keylineState0.getLastFocalKeyline() : keylineState0.getFirstFocalKeyline()).loc, keylineState0.getItemSize() / 2.0f);
        return (int)(((float)this.getParentStart()) - f);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean canScrollHorizontally() {
        return this.isHorizontal();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean canScrollVertically() {
        return !this.isHorizontal();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeHorizontalScrollExtent(State recyclerView$State0) {
        if(this.getChildCount() != 0 && this.keylineStateList != null && this.getItemCount() > 1) {
            float f = this.keylineStateList.getDefaultState().getItemSize() / ((float)this.computeHorizontalScrollRange(recyclerView$State0));
            return (int)(((float)this.getWidth()) * f);
        }
        return 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeHorizontalScrollOffset(State recyclerView$State0) {
        return this.scrollOffset;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeHorizontalScrollRange(State recyclerView$State0) {
        return this.maxScroll - this.minScroll;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller$ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int v) {
        if(this.keylineStateList == null) {
            return null;
        }
        int v1 = this.getOffsetToScrollToPosition(v, this.getKeylineStateForPosition(v));
        return this.isHorizontal() ? new PointF(((float)v1), 0.0f) : new PointF(0.0f, ((float)v1));
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeVerticalScrollExtent(State recyclerView$State0) {
        if(this.getChildCount() != 0 && this.keylineStateList != null && this.getItemCount() > 1) {
            float f = this.keylineStateList.getDefaultState().getItemSize() / ((float)this.computeVerticalScrollRange(recyclerView$State0));
            return (int)(((float)this.getHeight()) * f);
        }
        return 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeVerticalScrollOffset(State recyclerView$State0) {
        return this.scrollOffset;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int computeVerticalScrollRange(State recyclerView$State0) {
        return this.maxScroll - this.minScroll;
    }

    private int convertFocusDirectionToLayoutDirection(int v) {
        int v1 = this.getOrientation();
        switch(v) {
            case 1: {
                return -1;
            }
            case 2: {
                return 1;
            }
            case 17: {
                if(v1 == 0) {
                    return this.isLayoutRtl() ? 1 : -1;
                }
                return 0x80000000;
            }
            case 33: {
                return v1 == 1 ? -1 : 0x80000000;
            }
            case 66: {
                if(v1 == 0) {
                    return this.isLayoutRtl() ? -1 : 1;
                }
                return 0x80000000;
            }
            case 130: {
                return v1 == 1 ? 1 : 0x80000000;
            }
            default: {
                Log.d("CarouselLayoutManager", "Unknown focus request:" + v);
                return 0x80000000;
            }
        }
    }

    private void fill(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        this.removeAndRecycleOutOfBoundsViews(recyclerView$Recycler0);
        if(this.getChildCount() == 0) {
            this.addViewsStart(recyclerView$Recycler0, this.currentFillStartPosition - 1);
            this.addViewsEnd(recyclerView$Recycler0, recyclerView$State0, this.currentFillStartPosition);
        }
        else {
            int v = this.getPosition(this.getChildAt(0));
            int v1 = this.getPosition(this.getChildAt(this.getChildCount() - 1));
            this.addViewsStart(recyclerView$Recycler0, v - 1);
            this.addViewsEnd(recyclerView$Recycler0, recyclerView$State0, v1 + 1);
        }
        this.validateChildOrderIfDebugging();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override  // com.google.android.material.carousel.Carousel
    public int getCarouselAlignment() {
        return this.carouselAlignment;
    }

    // 去混淆评级： 低(20)
    private View getChildClosestToEnd() {
        return this.isLayoutRtl() ? this.getChildAt(0) : this.getChildAt(this.getChildCount() - 1);
    }

    // 去混淆评级： 低(20)
    private View getChildClosestToStart() {
        return this.isLayoutRtl() ? this.getChildAt(this.getChildCount() - 1) : this.getChildAt(0);
    }

    @Override  // com.google.android.material.carousel.Carousel
    public int getContainerHeight() {
        return this.getHeight();
    }

    // 去混淆评级： 低(20)
    private int getContainerSize() {
        return this.isHorizontal() ? this.getContainerWidth() : this.getContainerHeight();
    }

    @Override  // com.google.android.material.carousel.Carousel
    public int getContainerWidth() {
        return this.getWidth();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void getDecoratedBoundsWithMargins(View view0, Rect rect0) {
        super.getDecoratedBoundsWithMargins(view0, rect0);
        float f = (float)rect0.centerY();
        if(this.isHorizontal()) {
            f = (float)rect0.centerX();
        }
        float f1 = this.getMaskedItemSizeForLocOffset(f, CarouselLayoutManager.getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), f, true));
        float f2 = 0.0f;
        float f3 = this.isHorizontal() ? (((float)rect0.width()) - f1) / 2.0f : 0.0f;
        if(!this.isHorizontal()) {
            f2 = (((float)rect0.height()) - f1) / 2.0f;
        }
        rect0.set(((int)(((float)rect0.left) + f3)), ((int)(((float)rect0.top) + f2)), ((int)(((float)rect0.right) - f3)), ((int)(((float)rect0.bottom) - f2)));
    }

    private float getDecoratedCenterWithMargins(View view0) {
        Rect rect0 = new Rect();
        super.getDecoratedBoundsWithMargins(view0, rect0);
        return this.isHorizontal() ? ((float)rect0.centerX()) : ((float)rect0.centerY());
    }

    private int getItemMargins() {
        if(this.getChildCount() > 0) {
            LayoutParams recyclerView$LayoutParams0 = (LayoutParams)this.getChildAt(0).getLayoutParams();
            return this.orientationHelper.orientation == 0 ? recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin : recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin;
        }
        return 0;
    }

    private KeylineState getKeylineStateForPosition(int v) {
        Map map0 = this.keylineStatePositionMap;
        if(map0 != null) {
            KeylineState keylineState0 = (KeylineState)map0.get(MathUtils.clamp(v, 0, Math.max(0, this.getItemCount() - 1)));
            return keylineState0 == null ? this.keylineStateList.getDefaultState() : keylineState0;
        }
        return this.keylineStateList.getDefaultState();
    }

    private int getLeftOrTopPaddingForKeylineShift() {
        if(!this.getClipToPadding() && this.carouselStrategy.isContained()) {
            return this.getOrientation() == 1 ? this.getPaddingTop() : this.getPaddingLeft();
        }
        return 0;
    }

    private float getMaskedItemSizeForLocOffset(float f, KeylineRange carouselLayoutManager$KeylineRange0) {
        return AnimationUtils.lerp(carouselLayoutManager$KeylineRange0.leftOrTop.maskedItemSize, carouselLayoutManager$KeylineRange0.rightOrBottom.maskedItemSize, carouselLayoutManager$KeylineRange0.leftOrTop.locOffset, carouselLayoutManager$KeylineRange0.rightOrBottom.locOffset, f);
    }

    int getOffsetToScrollToPosition(int v, KeylineState keylineState0) {
        return this.getScrollOffsetForPosition(v, keylineState0) - this.scrollOffset;
    }

    int getOffsetToScrollToPositionForSnap(int v, boolean z) {
        int v1 = this.getOffsetToScrollToPosition(v, this.keylineStateList.getShiftedState(((float)this.scrollOffset), ((float)this.minScroll), ((float)this.maxScroll), true));
        int v2 = this.keylineStatePositionMap == null ? v1 : this.getOffsetToScrollToPosition(v, this.getKeylineStateForPosition(v));
        return !z || Math.abs(v2) >= Math.abs(v1) ? v1 : v2;
    }

    public int getOrientation() {
        return this.orientationHelper.orientation;
    }

    private int getParentBottom() {
        return this.orientationHelper.getParentBottom();
    }

    private int getParentEnd() {
        return this.orientationHelper.getParentEnd();
    }

    private int getParentLeft() {
        return this.orientationHelper.getParentLeft();
    }

    private int getParentRight() {
        return this.orientationHelper.getParentRight();
    }

    private int getParentStart() {
        return this.orientationHelper.getParentStart();
    }

    private int getParentTop() {
        return this.orientationHelper.getParentTop();
    }

    private int getRightOrBottomPaddingForKeylineShift() {
        if(!this.getClipToPadding() && this.carouselStrategy.isContained()) {
            return this.getOrientation() == 1 ? this.getPaddingBottom() : this.getPaddingRight();
        }
        return 0;
    }

    // 去混淆评级： 低(20)
    private int getScrollOffsetForPosition(int v, KeylineState keylineState0) {
        return this.isLayoutRtl() ? ((int)(((float)this.getContainerSize()) - keylineState0.getLastFocalKeyline().loc - ((float)v) * keylineState0.getItemSize() - keylineState0.getItemSize() / 2.0f)) : ((int)(((float)v) * keylineState0.getItemSize() - keylineState0.getFirstFocalKeyline().loc + keylineState0.getItemSize() / 2.0f));
    }

    private int getSmallestScrollOffsetToFocalKeyline(int v, KeylineState keylineState0) {
        int v1 = 0x7FFFFFFF;
        for(Object object0: keylineState0.getFocalKeylines()) {
            Keyline keylineState$Keyline0 = (Keyline)object0;
            float f = ((float)v) * keylineState0.getItemSize() + keylineState0.getItemSize() / 2.0f;
            int v2 = (this.isLayoutRtl() ? ((int)(((float)this.getContainerSize()) - keylineState$Keyline0.loc - f)) : ((int)(f - keylineState$Keyline0.loc))) - this.scrollOffset;
            if(Math.abs(v1) > Math.abs(v2)) {
                v1 = v2;
            }
        }
        return v1;
    }

    private static KeylineRange getSurroundingKeylineRange(List list0, float f, boolean z) {
        float f1 = 3.402823E+38f;
        float f2 = 3.402823E+38f;
        float f3 = 3.402823E+38f;
        float f4 = -3.402823E+38f;
        int v1 = -1;
        int v2 = -1;
        int v3 = -1;
        int v4 = -1;
        for(int v = 0; v < list0.size(); ++v) {
            Keyline keylineState$Keyline0 = (Keyline)list0.get(v);
            float f5 = z ? keylineState$Keyline0.locOffset : keylineState$Keyline0.loc;
            float f6 = Math.abs(f5 - f);
            if(f5 <= f && f6 <= f1) {
                v1 = v;
                f1 = f6;
            }
            if(f5 > f && f6 <= f2) {
                v3 = v;
                f2 = f6;
            }
            if(f5 <= f3) {
                v2 = v;
                f3 = f5;
            }
            if(f5 > f4) {
                v4 = v;
                f4 = f5;
            }
        }
        if(v1 == -1) {
            v1 = v2;
        }
        if(v3 == -1) {
            v3 = v4;
        }
        return new KeylineRange(((Keyline)list0.get(v1)), ((Keyline)list0.get(v3)));
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override  // com.google.android.material.carousel.Carousel
    public boolean isHorizontal() {
        return this.orientationHelper.orientation == 0;
    }

    boolean isLayoutRtl() {
        return this.isHorizontal() && this.getLayoutDirection() == 1;
    }

    private boolean isLocOffsetOutOfFillBoundsEnd(float f, KeylineRange carouselLayoutManager$KeylineRange0) {
        float f1 = this.addStart(f, this.getMaskedItemSizeForLocOffset(f, carouselLayoutManager$KeylineRange0) / 2.0f);
        return this.isLayoutRtl() ? f1 < 0.0f : f1 > ((float)this.getContainerSize());
    }

    private boolean isLocOffsetOutOfFillBoundsStart(float f, KeylineRange carouselLayoutManager$KeylineRange0) {
        float f1 = this.addEnd(f, this.getMaskedItemSizeForLocOffset(f, carouselLayoutManager$KeylineRange0) / 2.0f);
        return this.isLayoutRtl() ? f1 > ((float)this.getContainerSize()) : f1 < 0.0f;
    }

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-carousel-CarouselLayoutManager(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) [...]

    private void logChildrenIfDebugging() {
        if(this.isDebuggingEnabled && Log.isLoggable("CarouselLayoutManager", 3)) {
            Log.d("CarouselLayoutManager", "internal representation of views on the screen");
            for(int v = 0; v < this.getChildCount(); ++v) {
                View view0 = this.getChildAt(v);
                float f = this.getDecoratedCenterWithMargins(view0);
                Log.d("CarouselLayoutManager", "item position " + this.getPosition(view0) + ", center:" + f + ", child index:" + v);
            }
            Log.d("CarouselLayoutManager", "==============");
        }
    }

    private ChildCalculations makeChildCalculations(Recycler recyclerView$Recycler0, float f, int v) {
        View view0 = recyclerView$Recycler0.getViewForPosition(v);
        this.measureChildWithMargins(view0, 0, 0);
        float f1 = this.addEnd(f, this.currentKeylineState.getItemSize() / 2.0f);
        KeylineRange carouselLayoutManager$KeylineRange0 = CarouselLayoutManager.getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), f1, false);
        return new ChildCalculations(view0, f1, this.calculateChildOffsetCenterForLocation(view0, f1, carouselLayoutManager$KeylineRange0), carouselLayoutManager$KeylineRange0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void measureChildWithMargins(View view0, int v, int v1) {
        if(!(view0 instanceof Maskable)) {
            throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
        }
        LayoutParams recyclerView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        Rect rect0 = new Rect();
        this.calculateItemDecorationsForChild(view0, rect0);
        int v2 = v + (rect0.left + rect0.right);
        int v3 = v1 + (rect0.top + rect0.bottom);
        float f = this.keylineStateList == null || this.orientationHelper.orientation != 0 ? ((float)recyclerView$LayoutParams0.width) : this.keylineStateList.getDefaultState().getItemSize();
        float f1 = this.keylineStateList == null || this.orientationHelper.orientation != 1 ? ((float)recyclerView$LayoutParams0.height) : this.keylineStateList.getDefaultState().getItemSize();
        view0.measure(CarouselLayoutManager.getChildMeasureSpec(this.getWidth(), this.getWidthMode(), this.getPaddingLeft() + this.getPaddingRight() + recyclerView$LayoutParams0.leftMargin + recyclerView$LayoutParams0.rightMargin + v2, ((int)f), this.canScrollHorizontally()), CarouselLayoutManager.getChildMeasureSpec(this.getHeight(), this.getHeightMode(), this.getPaddingTop() + this.getPaddingBottom() + recyclerView$LayoutParams0.topMargin + recyclerView$LayoutParams0.bottomMargin + v3, ((int)f1), this.canScrollVertically()));
    }

    private float offsetChild(View view0, float f, float f1, Rect rect0) {
        float f2 = this.addEnd(f, f1);
        KeylineRange carouselLayoutManager$KeylineRange0 = CarouselLayoutManager.getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), f2, false);
        float f3 = this.calculateChildOffsetCenterForLocation(view0, f2, carouselLayoutManager$KeylineRange0);
        super.getDecoratedBoundsWithMargins(view0, rect0);
        this.updateChildMaskForLocation(view0, f2, carouselLayoutManager$KeylineRange0);
        this.orientationHelper.offsetChild(view0, rect0, f1, f3);
        return f3;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView0) {
        super.onAttachedToWindow(recyclerView0);
        this.carouselStrategy.initialize(recyclerView0.getContext());
        this.refreshKeylineState();
        recyclerView0.addOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView0, Recycler recyclerView$Recycler0) {
        super.onDetachedFromWindow(recyclerView0, recyclerView$Recycler0);
        recyclerView0.removeOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public View onFocusSearchFailed(View view0, int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(this.getChildCount() == 0) {
            return null;
        }
        int v1 = this.convertFocusDirectionToLayoutDirection(v);
        if(v1 == 0x80000000) {
            return null;
        }
        if(v1 == -1) {
            if(this.getPosition(view0) == 0) {
                return null;
            }
            this.addViewAtPosition(recyclerView$Recycler0, this.getPosition(this.getChildAt(0)) - 1, 0);
            return this.getChildClosestToStart();
        }
        if(this.getPosition(view0) == this.getItemCount() - 1) {
            return null;
        }
        this.addViewAtPosition(recyclerView$Recycler0, this.getPosition(this.getChildAt(this.getChildCount() - 1)) + 1, -1);
        return this.getChildClosestToEnd();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        if(this.getChildCount() > 0) {
            accessibilityEvent0.setFromIndex(this.getPosition(this.getChildAt(0)));
            accessibilityEvent0.setToIndex(this.getPosition(this.getChildAt(this.getChildCount() - 1)));
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsAdded(RecyclerView recyclerView0, int v, int v1) {
        super.onItemsAdded(recyclerView0, v, v1);
        this.updateItemCount();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView0, int v, int v1) {
        super.onItemsRemoved(recyclerView0, v, v1);
        this.updateItemCount();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onLayoutChildren(Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(recyclerView$State0.getItemCount() > 0 && ((float)this.getContainerSize()) > 0.0f) {
            boolean z = this.isLayoutRtl();
            boolean z1 = this.keylineStateList == null;
            if(z1) {
                this.recalculateKeylineStateList(recyclerView$Recycler0);
            }
            int v = this.calculateStartScroll(this.keylineStateList);
            int v1 = this.calculateEndScroll(recyclerView$State0, this.keylineStateList);
            this.minScroll = z ? v1 : v;
            if(z) {
                v1 = v;
            }
            this.maxScroll = v1;
            if(z1) {
                this.scrollOffset = v;
                this.keylineStatePositionMap = this.keylineStateList.getKeylineStateForPositionMap(this.getItemCount(), this.minScroll, this.maxScroll, this.isLayoutRtl());
                int v2 = this.currentEstimatedPosition;
                if(v2 != -1) {
                    this.scrollOffset = this.getScrollOffsetForPosition(v2, this.getKeylineStateForPosition(v2));
                }
            }
            this.scrollOffset += CarouselLayoutManager.calculateShouldScrollBy(0, this.scrollOffset, this.minScroll, this.maxScroll);
            this.currentFillStartPosition = MathUtils.clamp(this.currentFillStartPosition, 0, recyclerView$State0.getItemCount());
            this.updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
            this.detachAndScrapAttachedViews(recyclerView$Recycler0);
            this.fill(recyclerView$Recycler0, recyclerView$State0);
            this.lastItemCount = this.getItemCount();
            return;
        }
        this.removeAndRecycleAllViews(recyclerView$Recycler0);
        this.currentFillStartPosition = 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void onLayoutCompleted(State recyclerView$State0) {
        super.onLayoutCompleted(recyclerView$State0);
        this.currentFillStartPosition = this.getChildCount() == 0 ? 0 : this.getPosition(this.getChildAt(0));
        this.validateChildOrderIfDebugging();
    }

    private void recalculateKeylineStateList(Recycler recyclerView$Recycler0) {
        View view0 = recyclerView$Recycler0.getViewForPosition(0);
        this.measureChildWithMargins(view0, 0, 0);
        KeylineState keylineState0 = this.carouselStrategy.onFirstChildMeasuredWithMargins(this, view0);
        if(this.isLayoutRtl()) {
            keylineState0 = KeylineState.reverse(keylineState0, ((float)this.getContainerSize()));
        }
        this.keylineStateList = KeylineStateList.from(this, keylineState0, ((float)this.getItemMargins()), ((float)this.getLeftOrTopPaddingForKeylineShift()), ((float)this.getRightOrBottomPaddingForKeylineShift()));
    }

    private void refreshKeylineState() {
        this.keylineStateList = null;
        this.requestLayout();
    }

    private void removeAndRecycleOutOfBoundsViews(Recycler recyclerView$Recycler0) {
        while(this.getChildCount() > 0) {
            View view0 = this.getChildAt(0);
            float f = this.getDecoratedCenterWithMargins(view0);
            if(!this.isLocOffsetOutOfFillBoundsStart(f, CarouselLayoutManager.getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), f, true))) {
                break;
            }
            this.removeAndRecycleView(view0, recyclerView$Recycler0);
        }
        while(this.getChildCount() - 1 >= 0) {
            View view1 = this.getChildAt(this.getChildCount() - 1);
            float f1 = this.getDecoratedCenterWithMargins(view1);
            if(!this.isLocOffsetOutOfFillBoundsEnd(f1, CarouselLayoutManager.getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), f1, true))) {
                break;
            }
            this.removeAndRecycleView(view1, recyclerView$Recycler0);
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView0, View view0, Rect rect0, boolean z, boolean z1) {
        if(this.keylineStateList == null) {
            return false;
        }
        int v = this.getSmallestScrollOffsetToFocalKeyline(this.getPosition(view0), this.getKeylineStateForPosition(this.getPosition(view0)));
        if(v == 0) {
            return false;
        }
        KeylineState keylineState0 = this.keylineStateList.getShiftedState(((float)(this.scrollOffset + CarouselLayoutManager.calculateShouldScrollBy(v, this.scrollOffset, this.minScroll, this.maxScroll))), ((float)this.minScroll), ((float)this.maxScroll));
        this.scrollBy(recyclerView0, this.getSmallestScrollOffsetToFocalKeyline(this.getPosition(view0), keylineState0));
        return true;
    }

    private int scrollBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        if(this.getChildCount() != 0 && v != 0) {
            if(this.keylineStateList == null) {
                this.recalculateKeylineStateList(recyclerView$Recycler0);
            }
            int v2 = CarouselLayoutManager.calculateShouldScrollBy(v, this.scrollOffset, this.minScroll, this.maxScroll);
            this.scrollOffset += v2;
            this.updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
            float f = this.currentKeylineState.getItemSize();
            float f1 = this.calculateChildStartForFill(this.getPosition(this.getChildAt(0)));
            Rect rect0 = new Rect();
            float f2 = this.isLayoutRtl() ? this.currentKeylineState.getLastFocalKeyline().locOffset : this.currentKeylineState.getFirstFocalKeyline().locOffset;
            float f3 = 3.402823E+38f;
            for(int v1 = 0; v1 < this.getChildCount(); ++v1) {
                View view0 = this.getChildAt(v1);
                float f4 = Math.abs(f2 - this.offsetChild(view0, f1, f / 2.0f, rect0));
                if(view0 != null && f4 < f3) {
                    this.currentEstimatedPosition = this.getPosition(view0);
                    f3 = f4;
                }
                f1 = this.addEnd(f1, this.currentKeylineState.getItemSize());
            }
            this.fill(recyclerView$Recycler0, recyclerView$State0);
            return v2;
        }
        return 0;
    }

    private void scrollBy(RecyclerView recyclerView0, int v) {
        if(this.isHorizontal()) {
            recyclerView0.scrollBy(v, 0);
            return;
        }
        recyclerView0.scrollBy(0, v);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int scrollHorizontallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        return this.canScrollHorizontally() ? this.scrollBy(v, recyclerView$Recycler0, recyclerView$State0) : 0;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void scrollToPosition(int v) {
        this.currentEstimatedPosition = v;
        if(this.keylineStateList == null) {
            return;
        }
        this.scrollOffset = this.getScrollOffsetForPosition(v, this.getKeylineStateForPosition(v));
        this.currentFillStartPosition = MathUtils.clamp(v, 0, Math.max(0, this.getItemCount() - 1));
        this.updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        this.requestLayout();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public int scrollVerticallyBy(int v, Recycler recyclerView$Recycler0, State recyclerView$State0) {
        return this.canScrollVertically() ? this.scrollBy(v, recyclerView$Recycler0, recyclerView$State0) : 0;
    }

    public void setCarouselAlignment(int v) {
        this.carouselAlignment = v;
        this.refreshKeylineState();
    }

    private void setCarouselAttributes(Context context0, AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Carousel);
            this.setCarouselAlignment(typedArray0.getInt(styleable.Carousel_carousel_alignment, 0));
            this.setOrientation(typedArray0.getInt(styleable.RecyclerView_android_orientation, 0));
            typedArray0.recycle();
        }
    }

    public void setCarouselStrategy(CarouselStrategy carouselStrategy0) {
        this.carouselStrategy = carouselStrategy0;
        this.refreshKeylineState();
    }

    public void setDebuggingEnabled(RecyclerView recyclerView0, boolean z) {
        this.isDebuggingEnabled = z;
        recyclerView0.removeItemDecoration(this.debugItemDecoration);
        if(z) {
            recyclerView0.addItemDecoration(this.debugItemDecoration);
        }
        recyclerView0.invalidateItemDecorations();
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1) {
            throw new IllegalArgumentException("invalid orientation:" + v);
        }
        this.assertNotInLayoutOrScroll(null);
        if(this.orientationHelper != null && v == this.orientationHelper.orientation) {
            return;
        }
        this.orientationHelper = CarouselOrientationHelper.createOrientationHelper(this, v);
        this.refreshKeylineState();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView0, State recyclerView$State0, int v) {
        com.google.android.material.carousel.CarouselLayoutManager.1 carouselLayoutManager$10 = new LinearSmoothScroller(recyclerView0.getContext()) {
            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDxToMakeVisible(View view0, int v) {
                if(CarouselLayoutManager.this.keylineStateList != null && CarouselLayoutManager.this.isHorizontal()) {
                    int v1 = CarouselLayoutManager.this.getPosition(view0);
                    return CarouselLayoutManager.this.calculateScrollDeltaToMakePositionVisible(v1);
                }
                return 0;
            }

            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View view0, int v) {
                if(CarouselLayoutManager.this.keylineStateList != null && !CarouselLayoutManager.this.isHorizontal()) {
                    int v1 = CarouselLayoutManager.this.getPosition(view0);
                    return CarouselLayoutManager.this.calculateScrollDeltaToMakePositionVisible(v1);
                }
                return 0;
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$SmoothScroller
            public PointF computeScrollVectorForPosition(int v) {
                return CarouselLayoutManager.this.computeScrollVectorForPosition(v);
            }
        };
        carouselLayoutManager$10.setTargetPosition(v);
        this.startSmoothScroll(carouselLayoutManager$10);
    }

    private void updateChildMaskForLocation(View view0, float f, KeylineRange carouselLayoutManager$KeylineRange0) {
        if(!(view0 instanceof Maskable)) {
            return;
        }
        float f1 = AnimationUtils.lerp(carouselLayoutManager$KeylineRange0.leftOrTop.mask, carouselLayoutManager$KeylineRange0.rightOrBottom.mask, carouselLayoutManager$KeylineRange0.leftOrTop.loc, carouselLayoutManager$KeylineRange0.rightOrBottom.loc, f);
        float f2 = (float)view0.getHeight();
        float f3 = (float)view0.getWidth();
        RectF rectF0 = this.orientationHelper.getMaskRect(f2, f3, AnimationUtils.lerp(0.0f, f2 / 2.0f, 0.0f, 1.0f, f1), AnimationUtils.lerp(0.0f, f3 / 2.0f, 0.0f, 1.0f, f1));
        float f4 = this.calculateChildOffsetCenterForLocation(view0, f, carouselLayoutManager$KeylineRange0);
        float f5 = rectF0.height();
        float f6 = rectF0.height();
        RectF rectF1 = new RectF(f4 - rectF0.width() / 2.0f, f4 - f5 / 2.0f, f4 + rectF0.width() / 2.0f, f6 / 2.0f + f4);
        RectF rectF2 = new RectF(((float)this.getParentLeft()), ((float)this.getParentTop()), ((float)this.getParentRight()), ((float)this.getParentBottom()));
        if(this.carouselStrategy.isContained()) {
            this.orientationHelper.containMaskWithinBounds(rectF0, rectF1, rectF2);
        }
        this.orientationHelper.moveMaskOnEdgeOutsideBounds(rectF0, rectF1, rectF2);
        ((Maskable)view0).setMaskRectF(rectF0);
    }

    private void updateCurrentKeylineStateForScrollOffset(KeylineStateList keylineStateList0) {
        int v = this.maxScroll;
        int v1 = this.minScroll;
        if(v <= v1) {
            this.currentKeylineState = this.isLayoutRtl() ? keylineStateList0.getEndState() : keylineStateList0.getStartState();
        }
        else {
            this.currentKeylineState = keylineStateList0.getShiftedState(((float)this.scrollOffset), ((float)v1), ((float)v));
        }
        this.debugItemDecoration.setKeylines(this.currentKeylineState.getKeylines());
    }

    private void updateItemCount() {
        int v = this.getItemCount();
        int v1 = this.lastItemCount;
        if(v != v1 && this.keylineStateList != null) {
            if(this.carouselStrategy.shouldRefreshKeylineState(this, v1)) {
                this.refreshKeylineState();
            }
            this.lastItemCount = v;
        }
    }

    private void validateChildOrderIfDebugging() {
        if(this.isDebuggingEnabled && this.getChildCount() >= 1) {
            int v = 0;
            while(v < this.getChildCount() - 1) {
                int v1 = this.getPosition(this.getChildAt(v));
                int v2 = this.getPosition(this.getChildAt(v + 1));
                if(v1 <= v2) {
                    ++v;
                    continue;
                }
                this.logChildrenIfDebugging();
                throw new IllegalStateException("Detected invalid child order. Child at index [" + v + "] had adapter position [" + v1 + "] and child at index [" + (v + 1) + "] had adapter position [" + v2 + "].");
            }
        }
    }
}

