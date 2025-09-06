package com.google.android.material.textfield;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView.ScaleType;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.ripple.RippleUtils;
import java.util.Arrays;

class IconHelper {
    static void applyIconTint(TextInputLayout textInputLayout0, CheckableImageButton checkableImageButton0, ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0) {
        Drawable drawable0 = checkableImageButton0.getDrawable();
        if(drawable0 != null) {
            drawable0 = DrawableCompat.wrap(drawable0).mutate();
            if(colorStateList0 == null || !colorStateList0.isStateful()) {
                DrawableCompat.setTintList(drawable0, colorStateList0);
            }
            else {
                DrawableCompat.setTintList(drawable0, ColorStateList.valueOf(colorStateList0.getColorForState(IconHelper.mergeIconState(textInputLayout0, checkableImageButton0), colorStateList0.getDefaultColor())));
            }
            if(porterDuff$Mode0 != null) {
                DrawableCompat.setTintMode(drawable0, porterDuff$Mode0);
            }
        }
        if(checkableImageButton0.getDrawable() != drawable0) {
            checkableImageButton0.setImageDrawable(drawable0);
        }
    }

    static ImageView.ScaleType convertScaleType(int v) {
        switch(v) {
            case 0: {
                return ImageView.ScaleType.FIT_XY;
            }
            case 1: {
                return ImageView.ScaleType.FIT_START;
            }
            case 2: {
                return ImageView.ScaleType.FIT_CENTER;
            }
            case 3: {
                return ImageView.ScaleType.FIT_END;
            }
            case 5: {
                return ImageView.ScaleType.CENTER_CROP;
            }
            case 6: {
                return ImageView.ScaleType.CENTER_INSIDE;
            }
            default: {
                return ImageView.ScaleType.CENTER;
            }
        }
    }

    private static int[] mergeIconState(TextInputLayout textInputLayout0, CheckableImageButton checkableImageButton0) {
        int[] arr_v = textInputLayout0.getDrawableState();
        int[] arr_v1 = checkableImageButton0.getDrawableState();
        int[] arr_v2 = Arrays.copyOf(arr_v, arr_v.length + arr_v1.length);
        System.arraycopy(arr_v1, 0, arr_v2, arr_v.length, arr_v1.length);
        return arr_v2;
    }

    static void refreshIconDrawableState(TextInputLayout textInputLayout0, CheckableImageButton checkableImageButton0, ColorStateList colorStateList0) {
        Drawable drawable0 = checkableImageButton0.getDrawable();
        if(checkableImageButton0.getDrawable() != null && colorStateList0 != null && colorStateList0.isStateful()) {
            int v = colorStateList0.getColorForState(IconHelper.mergeIconState(textInputLayout0, checkableImageButton0), colorStateList0.getDefaultColor());
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            DrawableCompat.setTintList(drawable1, ColorStateList.valueOf(v));
            checkableImageButton0.setImageDrawable(drawable1);
        }
    }

    static void setCompatRippleBackgroundIfNeeded(CheckableImageButton checkableImageButton0) {
        if(Build.VERSION.SDK_INT <= 22) {
            checkableImageButton0.setBackground(RippleUtils.createOvalRippleLollipop(checkableImageButton0.getContext(), ((int)ViewUtils.dpToPx(checkableImageButton0.getContext(), 4))));
        }
    }

    private static void setIconClickable(CheckableImageButton checkableImageButton0, View.OnLongClickListener view$OnLongClickListener0) {
        boolean z = ViewCompat.hasOnClickListeners(checkableImageButton0);
        boolean z1 = false;
        int v = 1;
        if(z || view$OnLongClickListener0 != null) {
            z1 = true;
        }
        checkableImageButton0.setFocusable(z1);
        checkableImageButton0.setClickable(z);
        checkableImageButton0.setPressable(z);
        checkableImageButton0.setLongClickable(view$OnLongClickListener0 != null);
        if(!z1) {
            v = 2;
        }
        ViewCompat.setImportantForAccessibility(checkableImageButton0, v);
    }

    static void setIconMinSize(CheckableImageButton checkableImageButton0, int v) {
        checkableImageButton0.setMinimumWidth(v);
        checkableImageButton0.setMinimumHeight(v);
    }

    static void setIconOnClickListener(CheckableImageButton checkableImageButton0, View.OnClickListener view$OnClickListener0, View.OnLongClickListener view$OnLongClickListener0) {
        checkableImageButton0.setOnClickListener(view$OnClickListener0);
        IconHelper.setIconClickable(checkableImageButton0, view$OnLongClickListener0);
    }

    static void setIconOnLongClickListener(CheckableImageButton checkableImageButton0, View.OnLongClickListener view$OnLongClickListener0) {
        checkableImageButton0.setOnLongClickListener(view$OnLongClickListener0);
        IconHelper.setIconClickable(checkableImageButton0, view$OnLongClickListener0);
    }

    static void setIconScaleType(CheckableImageButton checkableImageButton0, ImageView.ScaleType imageView$ScaleType0) {
        checkableImageButton0.setScaleType(imageView$ScaleType0);
    }
}

