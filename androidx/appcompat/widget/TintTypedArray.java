package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.content.res.ResourcesCompat;

public class TintTypedArray {
    static class Api21Impl {
        static int getChangingConfigurations(TypedArray typedArray0) {
            return typedArray0.getChangingConfigurations();
        }

        static int getType(TypedArray typedArray0, int v) {
            return typedArray0.getType(v);
        }
    }

    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;

    private TintTypedArray(Context context0, TypedArray typedArray0) {
        this.mContext = context0;
        this.mWrapped = typedArray0;
    }

    public boolean getBoolean(int v, boolean z) {
        return this.mWrapped.getBoolean(v, z);
    }

    public int getChangingConfigurations() {
        return Api21Impl.getChangingConfigurations(this.mWrapped);
    }

    public int getColor(int v, int v1) {
        return this.mWrapped.getColor(v, v1);
    }

    public ColorStateList getColorStateList(int v) {
        if(this.mWrapped.hasValue(v)) {
            int v1 = this.mWrapped.getResourceId(v, 0);
            if(v1 != 0) {
                ColorStateList colorStateList0 = AppCompatResources.getColorStateList(this.mContext, v1);
                return colorStateList0 == null ? this.mWrapped.getColorStateList(v) : colorStateList0;
            }
        }
        return this.mWrapped.getColorStateList(v);
    }

    public float getDimension(int v, float f) {
        return this.mWrapped.getDimension(v, f);
    }

    public int getDimensionPixelOffset(int v, int v1) {
        return this.mWrapped.getDimensionPixelOffset(v, v1);
    }

    public int getDimensionPixelSize(int v, int v1) {
        return this.mWrapped.getDimensionPixelSize(v, v1);
    }

    public Drawable getDrawable(int v) {
        if(this.mWrapped.hasValue(v)) {
            int v1 = this.mWrapped.getResourceId(v, 0);
            return v1 == 0 ? this.mWrapped.getDrawable(v) : AppCompatResources.getDrawable(this.mContext, v1);
        }
        return this.mWrapped.getDrawable(v);
    }

    public Drawable getDrawableIfKnown(int v) {
        if(this.mWrapped.hasValue(v)) {
            int v1 = this.mWrapped.getResourceId(v, 0);
            return v1 == 0 ? null : AppCompatDrawableManager.get().getDrawable(this.mContext, v1, true);
        }
        return null;
    }

    public float getFloat(int v, float f) {
        return this.mWrapped.getFloat(v, f);
    }

    public Typeface getFont(int v, int v1, FontCallback resourcesCompat$FontCallback0) {
        int v2 = this.mWrapped.getResourceId(v, 0);
        if(v2 == 0) {
            return null;
        }
        if(this.mTypedValue == null) {
            this.mTypedValue = new TypedValue();
        }
        return ResourcesCompat.getFont(this.mContext, v2, this.mTypedValue, v1, resourcesCompat$FontCallback0);
    }

    public float getFraction(int v, int v1, int v2, float f) {
        return this.mWrapped.getFraction(v, v1, v2, f);
    }

    public int getIndex(int v) {
        return this.mWrapped.getIndex(v);
    }

    public int getIndexCount() {
        return this.mWrapped.getIndexCount();
    }

    public int getInt(int v, int v1) {
        return this.mWrapped.getInt(v, v1);
    }

    public int getInteger(int v, int v1) {
        return this.mWrapped.getInteger(v, v1);
    }

    public int getLayoutDimension(int v, int v1) {
        return this.mWrapped.getLayoutDimension(v, v1);
    }

    public int getLayoutDimension(int v, String s) {
        return this.mWrapped.getLayoutDimension(v, s);
    }

    public String getNonResourceString(int v) {
        return this.mWrapped.getNonResourceString(v);
    }

    public String getPositionDescription() {
        return this.mWrapped.getPositionDescription();
    }

    public int getResourceId(int v, int v1) {
        return this.mWrapped.getResourceId(v, v1);
    }

    public Resources getResources() {
        return this.mWrapped.getResources();
    }

    public String getString(int v) {
        return this.mWrapped.getString(v);
    }

    public CharSequence getText(int v) {
        return this.mWrapped.getText(v);
    }

    public CharSequence[] getTextArray(int v) {
        return this.mWrapped.getTextArray(v);
    }

    public int getType(int v) {
        return Api21Impl.getType(this.mWrapped, v);
    }

    public boolean getValue(int v, TypedValue typedValue0) {
        return this.mWrapped.getValue(v, typedValue0);
    }

    public TypedArray getWrappedTypeArray() {
        return this.mWrapped;
    }

    public boolean hasValue(int v) {
        return this.mWrapped.hasValue(v);
    }

    public int length() {
        return this.mWrapped.length();
    }

    public static TintTypedArray obtainStyledAttributes(Context context0, int v, int[] arr_v) {
        return new TintTypedArray(context0, context0.obtainStyledAttributes(v, arr_v));
    }

    public static TintTypedArray obtainStyledAttributes(Context context0, AttributeSet attributeSet0, int[] arr_v) {
        return new TintTypedArray(context0, context0.obtainStyledAttributes(attributeSet0, arr_v));
    }

    public static TintTypedArray obtainStyledAttributes(Context context0, AttributeSet attributeSet0, int[] arr_v, int v, int v1) {
        return new TintTypedArray(context0, context0.obtainStyledAttributes(attributeSet0, arr_v, v, v1));
    }

    public TypedValue peekValue(int v) {
        return this.mWrapped.peekValue(v);
    }

    public void recycle() {
        this.mWrapped.recycle();
    }
}

