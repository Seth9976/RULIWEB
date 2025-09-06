package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.styleable;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

final class CalendarItemStyle {
    private final ColorStateList backgroundColor;
    private final Rect insets;
    private final ShapeAppearanceModel itemShape;
    private final ColorStateList strokeColor;
    private final int strokeWidth;
    private final ColorStateList textColor;

    private CalendarItemStyle(ColorStateList colorStateList0, ColorStateList colorStateList1, ColorStateList colorStateList2, int v, ShapeAppearanceModel shapeAppearanceModel0, Rect rect0) {
        Preconditions.checkArgumentNonnegative(rect0.left);
        Preconditions.checkArgumentNonnegative(rect0.top);
        Preconditions.checkArgumentNonnegative(rect0.right);
        Preconditions.checkArgumentNonnegative(rect0.bottom);
        this.insets = rect0;
        this.textColor = colorStateList1;
        this.backgroundColor = colorStateList0;
        this.strokeColor = colorStateList2;
        this.strokeWidth = v;
        this.itemShape = shapeAppearanceModel0;
    }

    static CalendarItemStyle create(Context context0, int v) {
        Preconditions.checkArgument(v != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArray0 = context0.obtainStyledAttributes(v, styleable.MaterialCalendarItem);
        Rect rect0 = new Rect(typedArray0.getDimensionPixelOffset(styleable.MaterialCalendarItem_android_insetLeft, 0), typedArray0.getDimensionPixelOffset(styleable.MaterialCalendarItem_android_insetTop, 0), typedArray0.getDimensionPixelOffset(styleable.MaterialCalendarItem_android_insetRight, 0), typedArray0.getDimensionPixelOffset(styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(context0, typedArray0, styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateList1 = MaterialResources.getColorStateList(context0, typedArray0, styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context0, typedArray0, styleable.MaterialCalendarItem_itemStrokeColor);
        int v1 = typedArray0.getDimensionPixelSize(styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        ShapeAppearanceModel shapeAppearanceModel0 = ShapeAppearanceModel.builder(context0, typedArray0.getResourceId(styleable.MaterialCalendarItem_itemShapeAppearance, 0), typedArray0.getResourceId(styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).build();
        typedArray0.recycle();
        return new CalendarItemStyle(colorStateList0, colorStateList1, colorStateList2, v1, shapeAppearanceModel0, rect0);
    }

    int getBottomInset() {
        return this.insets.bottom;
    }

    int getLeftInset() {
        return this.insets.left;
    }

    int getRightInset() {
        return this.insets.right;
    }

    int getTopInset() {
        return this.insets.top;
    }

    void styleItem(TextView textView0) {
        this.styleItem(textView0, null, null);
    }

    void styleItem(TextView textView0, ColorStateList colorStateList0, ColorStateList colorStateList1) {
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
        MaterialShapeDrawable materialShapeDrawable1 = new MaterialShapeDrawable();
        materialShapeDrawable0.setShapeAppearanceModel(this.itemShape);
        materialShapeDrawable1.setShapeAppearanceModel(this.itemShape);
        if(colorStateList0 == null) {
            colorStateList0 = this.backgroundColor;
        }
        materialShapeDrawable0.setFillColor(colorStateList0);
        materialShapeDrawable0.setStroke(((float)this.strokeWidth), this.strokeColor);
        if(colorStateList1 == null) {
            colorStateList1 = this.textColor;
        }
        textView0.setTextColor(colorStateList1);
        ViewCompat.setBackground(textView0, new InsetDrawable(new RippleDrawable(this.textColor.withAlpha(30), materialShapeDrawable0, materialShapeDrawable1), this.insets.left, this.insets.top, this.insets.right, this.insets.bottom));
    }
}

