package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Menu;
import android.view.View.MeasureSpec;
import android.view.View;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class MaterialToolbar extends Toolbar {
    private static final int DEF_STYLE_RES;
    private static final ImageView.ScaleType[] LOGO_SCALE_TYPE_ARRAY;
    private Boolean logoAdjustViewBounds;
    private ImageView.ScaleType logoScaleType;
    private Integer navigationIconTint;
    private boolean subtitleCentered;
    private boolean titleCentered;

    static {
        MaterialToolbar.DEF_STYLE_RES = style.Widget_MaterialComponents_Toolbar;
        MaterialToolbar.LOGO_SCALE_TYPE_ARRAY = new ImageView.ScaleType[]{ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    }

    public MaterialToolbar(Context context0) {
        this(context0, null);
    }

    public MaterialToolbar(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.toolbarStyle);
    }

    public MaterialToolbar(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, MaterialToolbar.DEF_STYLE_RES), attributeSet0, v);
        Context context1 = this.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.MaterialToolbar, v, MaterialToolbar.DEF_STYLE_RES, new int[0]);
        if(typedArray0.hasValue(styleable.MaterialToolbar_navigationIconTint)) {
            this.setNavigationIconTint(typedArray0.getColor(styleable.MaterialToolbar_navigationIconTint, -1));
        }
        this.titleCentered = typedArray0.getBoolean(styleable.MaterialToolbar_titleCentered, false);
        this.subtitleCentered = typedArray0.getBoolean(styleable.MaterialToolbar_subtitleCentered, false);
        int v1 = typedArray0.getInt(styleable.MaterialToolbar_logoScaleType, -1);
        if(v1 >= 0) {
            ImageView.ScaleType[] arr_imageView$ScaleType = MaterialToolbar.LOGO_SCALE_TYPE_ARRAY;
            if(v1 < arr_imageView$ScaleType.length) {
                this.logoScaleType = arr_imageView$ScaleType[v1];
            }
        }
        if(typedArray0.hasValue(styleable.MaterialToolbar_logoAdjustViewBounds)) {
            this.logoAdjustViewBounds = Boolean.valueOf(typedArray0.getBoolean(styleable.MaterialToolbar_logoAdjustViewBounds, false));
        }
        typedArray0.recycle();
        this.initBackground(context1);
    }

    private Pair calculateTitleBoundLimits(TextView textView0, TextView textView1) {
        int v = this.getMeasuredWidth();
        int v1 = this.getPaddingLeft();
        int v2 = v - this.getPaddingRight();
        for(int v3 = 0; v3 < this.getChildCount(); ++v3) {
            View view0 = this.getChildAt(v3);
            if(view0.getVisibility() != 8 && view0 != textView0 && view0 != textView1) {
                if(view0.getRight() < v / 2 && view0.getRight() > v1) {
                    v1 = view0.getRight();
                }
                if(view0.getLeft() > v / 2 && view0.getLeft() < v2) {
                    v2 = view0.getLeft();
                }
            }
        }
        return new Pair(v1, v2);
    }

    public void clearNavigationIconTint() {
        this.navigationIconTint = null;
        Drawable drawable0 = this.getNavigationIcon();
        if(drawable0 != null) {
            DrawableCompat.setTintList(DrawableCompat.wrap(drawable0.mutate()), null);
            this.setNavigationIcon(drawable0);
        }
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.logoScaleType;
    }

    public Integer getNavigationIconTint() {
        return this.navigationIconTint;
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void inflateMenu(int v) {
        Menu menu0 = this.getMenu();
        if(menu0 instanceof MenuBuilder) {
            ((MenuBuilder)menu0).stopDispatchingItemsChanged();
        }
        super.inflateMenu(v);
        if(menu0 instanceof MenuBuilder) {
            ((MenuBuilder)menu0).startDispatchingItemsChanged();
        }
    }

    private void initBackground(Context context0) {
        Drawable drawable0 = this.getBackground();
        ColorStateList colorStateList0 = drawable0 == null ? ColorStateList.valueOf(0) : DrawableUtils.getColorStateListOrNull(drawable0);
        if(colorStateList0 != null) {
            MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
            materialShapeDrawable0.setFillColor(colorStateList0);
            materialShapeDrawable0.initializeElevationOverlay(context0);
            materialShapeDrawable0.setElevation(ViewCompat.getElevation(this));
            ViewCompat.setBackground(this, materialShapeDrawable0);
        }
    }

    public boolean isLogoAdjustViewBounds() {
        return this.logoAdjustViewBounds != null && this.logoAdjustViewBounds.booleanValue();
    }

    public boolean isSubtitleCentered() {
        return this.subtitleCentered;
    }

    public boolean isTitleCentered() {
        return this.titleCentered;
    }

    private void layoutTitleCenteredHorizontally(View view0, Pair pair0) {
        int v = this.getMeasuredWidth();
        int v1 = view0.getMeasuredWidth();
        int v2 = v / 2 - v1 / 2;
        int v3 = v1 + v2;
        int v4 = Math.max(Math.max(((int)(((Integer)pair0.first))) - v2, 0), Math.max(v3 - ((int)(((Integer)pair0.second))), 0));
        if(v4 > 0) {
            v2 += v4;
            v3 -= v4;
            view0.measure(View.MeasureSpec.makeMeasureSpec(v3 - v2, 0x40000000), view0.getMeasuredHeightAndState());
        }
        view0.layout(v2, view0.getTop(), v3, view0.getBottom());
    }

    private void maybeCenterTitleViews() {
        if(this.titleCentered || this.subtitleCentered) {
            TextView textView0 = ToolbarUtils.getTitleTextView(this);
            TextView textView1 = ToolbarUtils.getSubtitleTextView(this);
            if(textView0 != null || textView1 != null) {
                Pair pair0 = this.calculateTitleBoundLimits(textView0, textView1);
                if(this.titleCentered && textView0 != null) {
                    this.layoutTitleCenteredHorizontally(textView0, pair0);
                }
                if(this.subtitleCentered && textView1 != null) {
                    this.layoutTitleCenteredHorizontally(textView1, pair0);
                }
            }
        }
    }

    private Drawable maybeTintNavigationIcon(Drawable drawable0) {
        if(drawable0 != null && this.navigationIconTint != null) {
            drawable0 = DrawableCompat.wrap(drawable0.mutate());
            DrawableCompat.setTint(drawable0, ((int)this.navigationIconTint));
        }
        return drawable0;
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        this.maybeCenterTitleViews();
        this.updateLogoImageView();
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public void setLogoAdjustViewBounds(boolean z) {
        if(this.logoAdjustViewBounds != null && this.logoAdjustViewBounds.booleanValue() == z) {
            return;
        }
        this.logoAdjustViewBounds = Boolean.valueOf(z);
        this.requestLayout();
    }

    public void setLogoScaleType(ImageView.ScaleType imageView$ScaleType0) {
        if(this.logoScaleType != imageView$ScaleType0) {
            this.logoScaleType = imageView$ScaleType0;
            this.requestLayout();
        }
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable0) {
        super.setNavigationIcon(this.maybeTintNavigationIcon(drawable0));
    }

    public void setNavigationIconTint(int v) {
        this.navigationIconTint = v;
        Drawable drawable0 = this.getNavigationIcon();
        if(drawable0 != null) {
            this.setNavigationIcon(drawable0);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if(this.subtitleCentered != z) {
            this.subtitleCentered = z;
            this.requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if(this.titleCentered != z) {
            this.titleCentered = z;
            this.requestLayout();
        }
    }

    private void updateLogoImageView() {
        ImageView imageView0 = ToolbarUtils.getLogoImageView(this);
        if(imageView0 != null) {
            Boolean boolean0 = this.logoAdjustViewBounds;
            if(boolean0 != null) {
                imageView0.setAdjustViewBounds(boolean0.booleanValue());
            }
            ImageView.ScaleType imageView$ScaleType0 = this.logoScaleType;
            if(imageView$ScaleType0 != null) {
                imageView0.setScaleType(imageView$ScaleType0);
            }
        }
    }
}

