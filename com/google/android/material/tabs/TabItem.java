package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R.styleable;

public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public final CharSequence text;

    public TabItem(Context context0) {
        this(context0, null);
    }

    public TabItem(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.TabItem);
        this.text = tintTypedArray0.getText(styleable.TabItem_android_text);
        this.icon = tintTypedArray0.getDrawable(styleable.TabItem_android_icon);
        this.customLayout = tintTypedArray0.getResourceId(styleable.TabItem_android_layout, 0);
        tintTypedArray0.recycle();
    }
}

