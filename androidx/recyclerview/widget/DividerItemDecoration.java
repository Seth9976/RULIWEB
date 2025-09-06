package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

public class DividerItemDecoration extends ItemDecoration {
    private static final int[] ATTRS = null;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "DividerItem";
    public static final int VERTICAL = 1;
    private final Rect mBounds;
    private Drawable mDivider;
    private int mOrientation;

    static {
        DividerItemDecoration.ATTRS = new int[]{0x1010214};
    }

    public DividerItemDecoration(Context context0, int v) {
        this.mBounds = new Rect();
        TypedArray typedArray0 = context0.obtainStyledAttributes(DividerItemDecoration.ATTRS);
        Drawable drawable0 = typedArray0.getDrawable(0);
        this.mDivider = drawable0;
        if(drawable0 == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        typedArray0.recycle();
        this.setOrientation(v);
    }

    private void drawHorizontal(Canvas canvas0, RecyclerView recyclerView0) {
        int v2;
        int v1;
        canvas0.save();
        if(recyclerView0.getClipToPadding()) {
            v1 = recyclerView0.getPaddingTop();
            v2 = recyclerView0.getHeight() - recyclerView0.getPaddingBottom();
            canvas0.clipRect(recyclerView0.getPaddingLeft(), v1, recyclerView0.getWidth() - recyclerView0.getPaddingRight(), v2);
        }
        else {
            v2 = recyclerView0.getHeight();
            v1 = 0;
        }
        int v3 = recyclerView0.getChildCount();
        for(int v = 0; v < v3; ++v) {
            View view0 = recyclerView0.getChildAt(v);
            recyclerView0.getLayoutManager().getDecoratedBoundsWithMargins(view0, this.mBounds);
            int v4 = this.mBounds.right;
            int v5 = Math.round(view0.getTranslationX());
            int v6 = this.mDivider.getIntrinsicWidth();
            this.mDivider.setBounds(v4 + v5 - v6, v1, v4 + v5, v2);
            this.mDivider.draw(canvas0);
        }
        canvas0.restore();
    }

    private void drawVertical(Canvas canvas0, RecyclerView recyclerView0) {
        int v2;
        int v1;
        canvas0.save();
        if(recyclerView0.getClipToPadding()) {
            v1 = recyclerView0.getPaddingLeft();
            v2 = recyclerView0.getWidth() - recyclerView0.getPaddingRight();
            canvas0.clipRect(v1, recyclerView0.getPaddingTop(), v2, recyclerView0.getHeight() - recyclerView0.getPaddingBottom());
        }
        else {
            v2 = recyclerView0.getWidth();
            v1 = 0;
        }
        int v3 = recyclerView0.getChildCount();
        for(int v = 0; v < v3; ++v) {
            View view0 = recyclerView0.getChildAt(v);
            recyclerView0.getDecoratedBoundsWithMargins(view0, this.mBounds);
            int v4 = this.mBounds.bottom;
            int v5 = Math.round(view0.getTranslationY());
            int v6 = this.mDivider.getIntrinsicHeight();
            this.mDivider.setBounds(v1, v4 + v5 - v6, v2, v4 + v5);
            this.mDivider.draw(canvas0);
        }
        canvas0.restore();
    }

    public Drawable getDrawable() {
        return this.mDivider;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void getItemOffsets(Rect rect0, View view0, RecyclerView recyclerView0, State recyclerView$State0) {
        Drawable drawable0 = this.mDivider;
        if(drawable0 == null) {
            rect0.set(0, 0, 0, 0);
            return;
        }
        if(this.mOrientation == 1) {
            rect0.set(0, 0, 0, drawable0.getIntrinsicHeight());
            return;
        }
        rect0.set(0, 0, drawable0.getIntrinsicWidth(), 0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void onDraw(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
        if(recyclerView0.getLayoutManager() != null && this.mDivider != null) {
            if(this.mOrientation == 1) {
                this.drawVertical(canvas0, recyclerView0);
                return;
            }
            this.drawHorizontal(canvas0, recyclerView0);
        }
    }

    public void setDrawable(Drawable drawable0) {
        if(drawable0 == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.mDivider = drawable0;
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.mOrientation = v;
    }
}

