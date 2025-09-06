package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
import androidx.annotation.ReplaceWith;

public final class CheckedTextViewCompat {
    static class Api21Impl {
        static ColorStateList getCheckMarkTintList(CheckedTextView checkedTextView0) {
            return checkedTextView0.getCheckMarkTintList();
        }

        static PorterDuff.Mode getCheckMarkTintMode(CheckedTextView checkedTextView0) {
            return checkedTextView0.getCheckMarkTintMode();
        }

        static void setCheckMarkTintList(CheckedTextView checkedTextView0, ColorStateList colorStateList0) {
            checkedTextView0.setCheckMarkTintList(colorStateList0);
        }

        static void setCheckMarkTintMode(CheckedTextView checkedTextView0, PorterDuff.Mode porterDuff$Mode0) {
            checkedTextView0.setCheckMarkTintMode(porterDuff$Mode0);
        }
    }

    @ReplaceWith(expression = "textView.getCheckMarkDrawable()")
    @Deprecated
    public static Drawable getCheckMarkDrawable(CheckedTextView checkedTextView0) {
        return checkedTextView0.getCheckMarkDrawable();
    }

    public static ColorStateList getCheckMarkTintList(CheckedTextView checkedTextView0) {
        return Api21Impl.getCheckMarkTintList(checkedTextView0);
    }

    public static PorterDuff.Mode getCheckMarkTintMode(CheckedTextView checkedTextView0) {
        return Api21Impl.getCheckMarkTintMode(checkedTextView0);
    }

    public static void setCheckMarkTintList(CheckedTextView checkedTextView0, ColorStateList colorStateList0) {
        Api21Impl.setCheckMarkTintList(checkedTextView0, colorStateList0);
    }

    public static void setCheckMarkTintMode(CheckedTextView checkedTextView0, PorterDuff.Mode porterDuff$Mode0) {
        Api21Impl.setCheckMarkTintMode(checkedTextView0, porterDuff$Mode0);
    }
}

