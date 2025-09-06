package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

public class MaterialDividerItemDecoration extends ItemDecoration {
    private static final int DEF_STYLE_RES = 0;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int color;
    private Drawable dividerDrawable;
    private int insetEnd;
    private int insetStart;
    private boolean lastItemDecorated;
    private int orientation;
    private final Rect tempRect;
    private int thickness;

    static {
        MaterialDividerItemDecoration.DEF_STYLE_RES = style.Widget_MaterialComponents_MaterialDivider;
    }

    public MaterialDividerItemDecoration(Context context0, int v) {
        this(context0, null, v);
    }

    public MaterialDividerItemDecoration(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, attr.materialDividerStyle, v);
    }

    public MaterialDividerItemDecoration(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this.tempRect = new Rect();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.MaterialDivider, v, MaterialDividerItemDecoration.DEF_STYLE_RES, new int[0]);
        this.color = MaterialResources.getColorStateList(context0, typedArray0, styleable.MaterialDivider_dividerColor).getDefaultColor();
        int v2 = context0.getResources().getDimensionPixelSize(dimen.material_divider_thickness);
        this.thickness = typedArray0.getDimensionPixelSize(styleable.MaterialDivider_dividerThickness, v2);
        this.insetStart = typedArray0.getDimensionPixelOffset(styleable.MaterialDivider_dividerInsetStart, 0);
        this.insetEnd = typedArray0.getDimensionPixelOffset(styleable.MaterialDivider_dividerInsetEnd, 0);
        this.lastItemDecorated = typedArray0.getBoolean(styleable.MaterialDivider_lastItemDecorated, true);
        typedArray0.recycle();
        this.dividerDrawable = new ShapeDrawable();
        this.setDividerColor(this.color);
        this.setOrientation(v1);
    }

    private void drawForHorizontalOrientation(Canvas canvas0, RecyclerView recyclerView0) {
        int v8;
        int v7;
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
        int v3 = v1 + this.insetStart;
        int v4 = v2 - this.insetEnd;
        boolean z = ViewUtils.isLayoutRtl(recyclerView0);
        int v5 = recyclerView0.getChildCount();
        for(int v = 0; v < v5; ++v) {
            View view0 = recyclerView0.getChildAt(v);
            if(this.shouldDrawDivider(recyclerView0, view0)) {
                recyclerView0.getLayoutManager().getDecoratedBoundsWithMargins(view0, this.tempRect);
                int v6 = Math.round(view0.getTranslationX());
                if(z) {
                    v7 = this.tempRect.left + v6;
                    v8 = this.thickness + v7;
                }
                else {
                    v8 = v6 + this.tempRect.right;
                    v7 = v8 - this.thickness;
                }
                this.dividerDrawable.setBounds(v7, v3, v8, v4);
                int v9 = Math.round(view0.getAlpha() * 255.0f);
                this.dividerDrawable.setAlpha(v9);
                this.dividerDrawable.draw(canvas0);
            }
        }
        canvas0.restore();
    }

    private void drawForVerticalOrientation(Canvas canvas0, RecyclerView recyclerView0) {
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
        boolean z = ViewUtils.isLayoutRtl(recyclerView0);
        int v3 = z ? this.insetEnd : this.insetStart;
        int v4 = z ? this.insetStart : this.insetEnd;
        int v5 = recyclerView0.getChildCount();
        for(int v = 0; v < v5; ++v) {
            View view0 = recyclerView0.getChildAt(v);
            if(this.shouldDrawDivider(recyclerView0, view0)) {
                recyclerView0.getLayoutManager().getDecoratedBoundsWithMargins(view0, this.tempRect);
                int v6 = this.tempRect.bottom;
                int v7 = Math.round(view0.getTranslationY());
                this.dividerDrawable.setBounds(v1 + v3, v6 + v7 - this.thickness, v2 - v4, v6 + v7);
                int v8 = Math.round(view0.getAlpha() * 255.0f);
                this.dividerDrawable.setAlpha(v8);
                this.dividerDrawable.draw(canvas0);
            }
        }
        canvas0.restore();
    }

    public int getDividerColor() {
        return this.color;
    }

    public int getDividerInsetEnd() {
        return this.insetEnd;
    }

    public int getDividerInsetStart() {
        return this.insetStart;
    }

    public int getDividerThickness() {
        return this.thickness;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void getItemOffsets(Rect rect0, View view0, RecyclerView recyclerView0, State recyclerView$State0) {
        rect0.set(0, 0, 0, 0);
        if(this.shouldDrawDivider(recyclerView0, view0)) {
            if(this.orientation == 1) {
                rect0.bottom = this.thickness;
                return;
            }
            if(ViewUtils.isLayoutRtl(recyclerView0)) {
                rect0.left = this.thickness;
                return;
            }
            rect0.right = this.thickness;
        }
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean isLastItemDecorated() {
        return this.lastItemDecorated;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void onDraw(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
        if(recyclerView0.getLayoutManager() == null) {
            return;
        }
        if(this.orientation == 1) {
            this.drawForVerticalOrientation(canvas0, recyclerView0);
            return;
        }
        this.drawForHorizontalOrientation(canvas0, recyclerView0);
    }

    public void setDividerColor(int v) {
        this.color = v;
        Drawable drawable0 = DrawableCompat.wrap(this.dividerDrawable);
        this.dividerDrawable = drawable0;
        DrawableCompat.setTint(drawable0, v);
    }

    public void setDividerColorResource(Context context0, int v) {
        this.setDividerColor(ContextCompat.getColor(context0, v));
    }

    public void setDividerInsetEnd(int v) {
        this.insetEnd = v;
    }

    public void setDividerInsetEndResource(Context context0, int v) {
        this.setDividerInsetEnd(context0.getResources().getDimensionPixelOffset(v));
    }

    public void setDividerInsetStart(int v) {
        this.insetStart = v;
    }

    public void setDividerInsetStartResource(Context context0, int v) {
        this.setDividerInsetStart(context0.getResources().getDimensionPixelOffset(v));
    }

    public void setDividerThickness(int v) {
        this.thickness = v;
    }

    public void setDividerThicknessResource(Context context0, int v) {
        this.setDividerThickness(context0.getResources().getDimensionPixelSize(v));
    }

    public void setLastItemDecorated(boolean z) {
        this.lastItemDecorated = z;
    }

    public void setOrientation(int v) {
        if(v != 0 && v != 1) {
            throw new IllegalArgumentException("Invalid orientation: " + v + ". It should be either HORIZONTAL or VERTICAL");
        }
        this.orientation = v;
    }

    private boolean shouldDrawDivider(RecyclerView recyclerView0, View view0) {
        int v = recyclerView0.getChildAdapterPosition(view0);
        Adapter recyclerView$Adapter0 = recyclerView0.getAdapter();
        return v != -1 && (recyclerView$Adapter0 == null || v != recyclerView$Adapter0.getItemCount() - 1 || this.lastItemDecorated);
    }

    protected boolean shouldDrawDivider(int v, Adapter recyclerView$Adapter0) [...] // Inlined contents
}

