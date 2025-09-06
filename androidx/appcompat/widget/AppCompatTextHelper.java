package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.R.styleable;
import androidx.core.content.res.ResourcesCompat.FontCallback;
import androidx.core.view.ViewCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.core.widget.TextViewCompat;
import java.lang.ref.WeakReference;
import java.util.Locale;

class AppCompatTextHelper {
    static class Api21Impl {
        static Locale forLanguageTag(String s) {
            return Locale.forLanguageTag(s);
        }
    }

    static class Api24Impl {
        static LocaleList forLanguageTags(String s) {
            return LocaleList.forLanguageTags(s);
        }

        static void setTextLocales(TextView textView0, LocaleList localeList0) {
            textView0.setTextLocales(localeList0);
        }
    }

    static class Api26Impl {
        static int getAutoSizeStepGranularity(TextView textView0) {
            return textView0.getAutoSizeStepGranularity();
        }

        static void setAutoSizeTextTypeUniformWithConfiguration(TextView textView0, int v, int v1, int v2, int v3) {
            textView0.setAutoSizeTextTypeUniformWithConfiguration(v, v1, v2, v3);
        }

        static void setAutoSizeTextTypeUniformWithPresetSizes(TextView textView0, int[] arr_v, int v) {
            textView0.setAutoSizeTextTypeUniformWithPresetSizes(arr_v, v);
        }

        static boolean setFontVariationSettings(TextView textView0, String s) {
            return textView0.setFontVariationSettings(s);
        }
    }

    static class Api28Impl {
        static Typeface create(Typeface typeface0, int v, boolean z) {
            return Typeface.create(typeface0, v, z);
        }
    }

    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int TEXT_FONT_WEIGHT_UNSPECIFIED = -1;
    private boolean mAsyncFontPending;
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    private int mFontWeight;
    private int mStyle;
    private final TextView mView;

    AppCompatTextHelper(TextView textView0) {
        this.mStyle = 0;
        this.mFontWeight = -1;
        this.mView = textView0;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView0);
    }

    private void applyCompoundDrawableTint(Drawable drawable0, TintInfo tintInfo0) {
        if(drawable0 != null && tintInfo0 != null) {
            AppCompatDrawableManager.tintDrawable(drawable0, tintInfo0, this.mView.getDrawableState());
        }
    }

    void applyCompoundDrawablesTints() {
        if(this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
            Drawable[] arr_drawable = this.mView.getCompoundDrawables();
            this.applyCompoundDrawableTint(arr_drawable[0], this.mDrawableLeftTint);
            this.applyCompoundDrawableTint(arr_drawable[1], this.mDrawableTopTint);
            this.applyCompoundDrawableTint(arr_drawable[2], this.mDrawableRightTint);
            this.applyCompoundDrawableTint(arr_drawable[3], this.mDrawableBottomTint);
        }
        if(this.mDrawableStartTint == null && this.mDrawableEndTint == null) {
            return;
        }
        Drawable[] arr_drawable1 = this.mView.getCompoundDrawablesRelative();
        this.applyCompoundDrawableTint(arr_drawable1[0], this.mDrawableStartTint);
        this.applyCompoundDrawableTint(arr_drawable1[2], this.mDrawableEndTint);
    }

    void autoSizeText() {
        this.mAutoSizeTextHelper.autoSizeText();
    }

    private static TintInfo createTintInfo(Context context0, AppCompatDrawableManager appCompatDrawableManager0, int v) {
        ColorStateList colorStateList0 = appCompatDrawableManager0.getTintList(context0, v);
        if(colorStateList0 != null) {
            TintInfo tintInfo0 = new TintInfo();
            tintInfo0.mHasTintList = true;
            tintInfo0.mTintList = colorStateList0;
            return tintInfo0;
        }
        return null;
    }

    int getAutoSizeMaxTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
    }

    int getAutoSizeMinTextSize() {
        return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
    }

    int getAutoSizeStepGranularity() {
        return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
    }

    int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
    }

    int getAutoSizeTextType() {
        return this.mAutoSizeTextHelper.getAutoSizeTextType();
    }

    ColorStateList getCompoundDrawableTintList() {
        return this.mDrawableTint == null ? null : this.mDrawableTint.mTintList;
    }

    PorterDuff.Mode getCompoundDrawableTintMode() {
        return this.mDrawableTint == null ? null : this.mDrawableTint.mTintMode;
    }

    boolean isAutoSizeEnabled() {
        return this.mAutoSizeTextHelper.isAutoSizeEnabled();
    }

    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        int v11;
        float f;
        Drawable drawable9;
        Drawable drawable8;
        Drawable drawable7;
        Drawable drawable6;
        Drawable drawable5;
        boolean z2;
        String s1;
        String s;
        ColorStateList colorStateList2;
        ColorStateList colorStateList1;
        ColorStateList colorStateList0;
        boolean z1;
        boolean z;
        Context context0 = this.mView.getContext();
        AppCompatDrawableManager appCompatDrawableManager0 = AppCompatDrawableManager.get();
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.AppCompatTextHelper, v, 0);
        Context context1 = this.mView.getContext();
        ViewCompat.saveAttributeDataForStyleable(this.mView, context1, styleable.AppCompatTextHelper, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        int v1 = tintTypedArray0.getResourceId(styleable.AppCompatTextHelper_android_textAppearance, -1);
        if(tintTypedArray0.hasValue(styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.mDrawableLeftTint = AppCompatTextHelper.createTintInfo(context0, appCompatDrawableManager0, tintTypedArray0.getResourceId(styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if(tintTypedArray0.hasValue(styleable.AppCompatTextHelper_android_drawableTop)) {
            this.mDrawableTopTint = AppCompatTextHelper.createTintInfo(context0, appCompatDrawableManager0, tintTypedArray0.getResourceId(styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if(tintTypedArray0.hasValue(styleable.AppCompatTextHelper_android_drawableRight)) {
            this.mDrawableRightTint = AppCompatTextHelper.createTintInfo(context0, appCompatDrawableManager0, tintTypedArray0.getResourceId(styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if(tintTypedArray0.hasValue(styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.mDrawableBottomTint = AppCompatTextHelper.createTintInfo(context0, appCompatDrawableManager0, tintTypedArray0.getResourceId(styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if(tintTypedArray0.hasValue(styleable.AppCompatTextHelper_android_drawableStart)) {
            this.mDrawableStartTint = AppCompatTextHelper.createTintInfo(context0, appCompatDrawableManager0, tintTypedArray0.getResourceId(styleable.AppCompatTextHelper_android_drawableStart, 0));
        }
        if(tintTypedArray0.hasValue(styleable.AppCompatTextHelper_android_drawableEnd)) {
            this.mDrawableEndTint = AppCompatTextHelper.createTintInfo(context0, appCompatDrawableManager0, tintTypedArray0.getResourceId(styleable.AppCompatTextHelper_android_drawableEnd, 0));
        }
        tintTypedArray0.recycle();
        TransformationMethod transformationMethod0 = this.mView.getTransformationMethod();
        if(v1 == -1) {
            colorStateList1 = null;
            s = null;
            s1 = null;
            colorStateList2 = null;
            z = false;
            z1 = false;
            colorStateList0 = null;
        }
        else {
            TintTypedArray tintTypedArray1 = TintTypedArray.obtainStyledAttributes(context0, v1, styleable.TextAppearance);
            if(transformationMethod0 instanceof PasswordTransformationMethod || !tintTypedArray1.hasValue(styleable.TextAppearance_textAllCaps)) {
                z = false;
                z1 = false;
            }
            else {
                z = tintTypedArray1.getBoolean(styleable.TextAppearance_textAllCaps, false);
                z1 = true;
            }
            this.updateTypefaceAndStyle(context0, tintTypedArray1);
            if(Build.VERSION.SDK_INT < 23) {
                colorStateList0 = tintTypedArray1.hasValue(styleable.TextAppearance_android_textColor) ? tintTypedArray1.getColorStateList(styleable.TextAppearance_android_textColor) : null;
                colorStateList1 = tintTypedArray1.hasValue(styleable.TextAppearance_android_textColorHint) ? tintTypedArray1.getColorStateList(styleable.TextAppearance_android_textColorHint) : null;
                colorStateList2 = tintTypedArray1.hasValue(styleable.TextAppearance_android_textColorLink) ? tintTypedArray1.getColorStateList(styleable.TextAppearance_android_textColorLink) : null;
            }
            else {
                colorStateList1 = null;
                colorStateList2 = null;
                colorStateList0 = null;
            }
            s = tintTypedArray1.hasValue(styleable.TextAppearance_textLocale) ? tintTypedArray1.getString(styleable.TextAppearance_textLocale) : null;
            s1 = Build.VERSION.SDK_INT < 26 || !tintTypedArray1.hasValue(styleable.TextAppearance_fontVariationSettings) ? null : tintTypedArray1.getString(styleable.TextAppearance_fontVariationSettings);
            tintTypedArray1.recycle();
        }
        TintTypedArray tintTypedArray2 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.TextAppearance, v, 0);
        if(!(transformationMethod0 instanceof PasswordTransformationMethod) && tintTypedArray2.hasValue(styleable.TextAppearance_textAllCaps)) {
            z = tintTypedArray2.getBoolean(styleable.TextAppearance_textAllCaps, false);
            z1 = true;
        }
        if(Build.VERSION.SDK_INT < 23) {
            if(tintTypedArray2.hasValue(styleable.TextAppearance_android_textColor)) {
                colorStateList0 = tintTypedArray2.getColorStateList(styleable.TextAppearance_android_textColor);
            }
            if(tintTypedArray2.hasValue(styleable.TextAppearance_android_textColorHint)) {
                colorStateList1 = tintTypedArray2.getColorStateList(styleable.TextAppearance_android_textColorHint);
            }
            if(tintTypedArray2.hasValue(styleable.TextAppearance_android_textColorLink)) {
                colorStateList2 = tintTypedArray2.getColorStateList(styleable.TextAppearance_android_textColorLink);
            }
        }
        if(tintTypedArray2.hasValue(styleable.TextAppearance_textLocale)) {
            s = tintTypedArray2.getString(styleable.TextAppearance_textLocale);
        }
        if(Build.VERSION.SDK_INT >= 26 && tintTypedArray2.hasValue(styleable.TextAppearance_fontVariationSettings)) {
            s1 = tintTypedArray2.getString(styleable.TextAppearance_fontVariationSettings);
        }
        if(Build.VERSION.SDK_INT < 28 || !tintTypedArray2.hasValue(styleable.TextAppearance_android_textSize) || tintTypedArray2.getDimensionPixelSize(styleable.TextAppearance_android_textSize, -1) != 0) {
            z2 = transformationMethod0 instanceof PasswordTransformationMethod;
        }
        else {
            z2 = transformationMethod0 instanceof PasswordTransformationMethod;
            this.mView.setTextSize(0, 0.0f);
        }
        this.updateTypefaceAndStyle(context0, tintTypedArray2);
        tintTypedArray2.recycle();
        if(colorStateList0 != null) {
            this.mView.setTextColor(colorStateList0);
        }
        if(colorStateList1 != null) {
            this.mView.setHintTextColor(colorStateList1);
        }
        if(colorStateList2 != null) {
            this.mView.setLinkTextColor(colorStateList2);
        }
        if(!z2 && z1) {
            this.setAllCaps(z);
        }
        Typeface typeface0 = this.mFontTypeface;
        if(typeface0 != null) {
            if(this.mFontWeight == -1) {
                this.mView.setTypeface(typeface0, this.mStyle);
            }
            else {
                this.mView.setTypeface(typeface0);
            }
        }
        if(s1 != null) {
            Api26Impl.setFontVariationSettings(this.mView, s1);
        }
        if(s != null) {
            if(Build.VERSION.SDK_INT >= 24) {
                LocaleList localeList0 = Api24Impl.forLanguageTags(s);
                Api24Impl.setTextLocales(this.mView, localeList0);
            }
            else {
                Locale locale0 = Api21Impl.forLanguageTag(s.split(",")[0]);
                this.mView.setTextLocale(locale0);
            }
        }
        this.mAutoSizeTextHelper.loadFromAttributes(attributeSet0, v);
        if(ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE && this.mAutoSizeTextHelper.getAutoSizeTextType() != 0) {
            int[] arr_v = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
            if(arr_v.length > 0) {
                if(((float)Api26Impl.getAutoSizeStepGranularity(this.mView)) == -1.0f) {
                    Api26Impl.setAutoSizeTextTypeUniformWithPresetSizes(this.mView, arr_v, 0);
                }
                else {
                    Api26Impl.setAutoSizeTextTypeUniformWithConfiguration(this.mView, this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
                }
            }
        }
        TintTypedArray tintTypedArray3 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.AppCompatTextView);
        int v2 = tintTypedArray3.getResourceId(styleable.AppCompatTextView_drawableLeftCompat, -1);
        Drawable drawable0 = v2 == -1 ? null : appCompatDrawableManager0.getDrawable(context0, v2);
        int v3 = tintTypedArray3.getResourceId(styleable.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawable1 = v3 == -1 ? null : appCompatDrawableManager0.getDrawable(context0, v3);
        int v4 = tintTypedArray3.getResourceId(styleable.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawable2 = v4 == -1 ? null : appCompatDrawableManager0.getDrawable(context0, v4);
        int v5 = tintTypedArray3.getResourceId(styleable.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawable3 = v5 == -1 ? null : appCompatDrawableManager0.getDrawable(context0, v5);
        int v6 = tintTypedArray3.getResourceId(styleable.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawable4 = v6 == -1 ? null : appCompatDrawableManager0.getDrawable(context0, v6);
        int v7 = tintTypedArray3.getResourceId(styleable.AppCompatTextView_drawableEndCompat, -1);
        if(v7 == -1) {
            drawable6 = drawable1;
            drawable7 = drawable2;
            drawable8 = drawable3;
            drawable9 = drawable4;
            drawable5 = null;
        }
        else {
            drawable5 = appCompatDrawableManager0.getDrawable(context0, v7);
            drawable6 = drawable1;
            drawable7 = drawable2;
            drawable8 = drawable3;
            drawable9 = drawable4;
        }
        this.setCompoundDrawables(drawable0, drawable6, drawable7, drawable8, drawable9, drawable5);
        if(tintTypedArray3.hasValue(styleable.AppCompatTextView_drawableTint)) {
            ColorStateList colorStateList3 = tintTypedArray3.getColorStateList(styleable.AppCompatTextView_drawableTint);
            TextViewCompat.setCompoundDrawableTintList(this.mView, colorStateList3);
        }
        if(tintTypedArray3.hasValue(styleable.AppCompatTextView_drawableTintMode)) {
            PorterDuff.Mode porterDuff$Mode0 = DrawableUtils.parseTintMode(tintTypedArray3.getInt(styleable.AppCompatTextView_drawableTintMode, -1), null);
            TextViewCompat.setCompoundDrawableTintMode(this.mView, porterDuff$Mode0);
        }
        int v8 = tintTypedArray3.getDimensionPixelSize(styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
        int v9 = tintTypedArray3.getDimensionPixelSize(styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
        if(tintTypedArray3.hasValue(styleable.AppCompatTextView_lineHeight)) {
            TypedValue typedValue0 = tintTypedArray3.peekValue(styleable.AppCompatTextView_lineHeight);
            if(typedValue0 == null || typedValue0.type != 5) {
                f = (float)tintTypedArray3.getDimensionPixelSize(styleable.AppCompatTextView_lineHeight, -1);
                v11 = -1;
            }
            else {
                int v10 = typedValue0.data & 15;
                f = TypedValue.complexToFloat(typedValue0.data);
                v11 = v10;
            }
        }
        else {
            f = -1.0f;
            v11 = -1;
        }
        tintTypedArray3.recycle();
        if(v8 != -1) {
            TextViewCompat.setFirstBaselineToTopHeight(this.mView, v8);
        }
        if(v9 != -1) {
            TextViewCompat.setLastBaselineToBottomHeight(this.mView, v9);
        }
        if(f != -1.0f) {
            if(v11 == -1) {
                TextViewCompat.setLineHeight(this.mView, ((int)f));
                return;
            }
            TextViewCompat.setLineHeight(this.mView, v11, f);
        }
    }

    void onAsyncTypefaceReceived(WeakReference weakReference0, Typeface typeface0) {
        if(this.mAsyncFontPending) {
            this.mFontTypeface = typeface0;
            TextView textView0 = (TextView)weakReference0.get();
            if(textView0 != null) {
                if(textView0.isAttachedToWindow()) {
                    textView0.post(new Runnable() {
                        @Override
                        public void run() {
                            textView0.setTypeface(typeface0, this.mStyle);
                        }
                    });
                    return;
                }
                textView0.setTypeface(typeface0, this.mStyle);
            }
        }
    }

    void onLayout(boolean z, int v, int v1, int v2, int v3) {
        if(!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE) {
            this.autoSizeText();
        }
    }

    void onSetCompoundDrawables() {
        this.applyCompoundDrawablesTints();
    }

    void onSetTextAppearance(Context context0, int v) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, v, styleable.TextAppearance);
        if(tintTypedArray0.hasValue(styleable.TextAppearance_textAllCaps)) {
            this.setAllCaps(tintTypedArray0.getBoolean(styleable.TextAppearance_textAllCaps, false));
        }
        if(Build.VERSION.SDK_INT < 23) {
            if(tintTypedArray0.hasValue(styleable.TextAppearance_android_textColor)) {
                ColorStateList colorStateList0 = tintTypedArray0.getColorStateList(styleable.TextAppearance_android_textColor);
                if(colorStateList0 != null) {
                    this.mView.setTextColor(colorStateList0);
                }
            }
            if(tintTypedArray0.hasValue(styleable.TextAppearance_android_textColorLink)) {
                ColorStateList colorStateList1 = tintTypedArray0.getColorStateList(styleable.TextAppearance_android_textColorLink);
                if(colorStateList1 != null) {
                    this.mView.setLinkTextColor(colorStateList1);
                }
            }
            if(tintTypedArray0.hasValue(styleable.TextAppearance_android_textColorHint)) {
                ColorStateList colorStateList2 = tintTypedArray0.getColorStateList(styleable.TextAppearance_android_textColorHint);
                if(colorStateList2 != null) {
                    this.mView.setHintTextColor(colorStateList2);
                }
            }
        }
        if(tintTypedArray0.hasValue(styleable.TextAppearance_android_textSize) && tintTypedArray0.getDimensionPixelSize(styleable.TextAppearance_android_textSize, -1) == 0) {
            this.mView.setTextSize(0, 0.0f);
        }
        this.updateTypefaceAndStyle(context0, tintTypedArray0);
        if(Build.VERSION.SDK_INT >= 26 && tintTypedArray0.hasValue(styleable.TextAppearance_fontVariationSettings)) {
            String s = tintTypedArray0.getString(styleable.TextAppearance_fontVariationSettings);
            if(s != null) {
                Api26Impl.setFontVariationSettings(this.mView, s);
            }
        }
        tintTypedArray0.recycle();
        Typeface typeface0 = this.mFontTypeface;
        if(typeface0 != null) {
            this.mView.setTypeface(typeface0, this.mStyle);
        }
    }

    void populateSurroundingTextIfNeeded(TextView textView0, InputConnection inputConnection0, EditorInfo editorInfo0) {
        if(Build.VERSION.SDK_INT < 30 && inputConnection0 != null) {
            EditorInfoCompat.setInitialSurroundingText(editorInfo0, textView0.getText());
        }
    }

    void setAllCaps(boolean z) {
        this.mView.setAllCaps(z);
    }

    void setAutoSizeTextTypeUniformWithConfiguration(int v, int v1, int v2, int v3) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(v, v1, v2, v3);
    }

    void setAutoSizeTextTypeUniformWithPresetSizes(int[] arr_v, int v) throws IllegalArgumentException {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(arr_v, v);
    }

    void setAutoSizeTextTypeWithDefaults(int v) {
        this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(v);
    }

    void setCompoundDrawableTintList(ColorStateList colorStateList0) {
        if(this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        this.mDrawableTint.mTintList = colorStateList0;
        this.mDrawableTint.mHasTintList = colorStateList0 != null;
        this.setCompoundTints();
    }

    void setCompoundDrawableTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.mDrawableTint == null) {
            this.mDrawableTint = new TintInfo();
        }
        this.mDrawableTint.mTintMode = porterDuff$Mode0;
        this.mDrawableTint.mHasTintMode = porterDuff$Mode0 != null;
        this.setCompoundTints();
    }

    private void setCompoundDrawables(Drawable drawable0, Drawable drawable1, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5) {
        if(drawable4 == null && drawable5 == null) {
            if(drawable0 == null && drawable1 == null && drawable2 == null && drawable3 == null) {
                return;
            }
            Drawable[] arr_drawable = this.mView.getCompoundDrawablesRelative();
            Drawable drawable6 = arr_drawable[0];
            if(drawable6 == null && arr_drawable[2] == null) {
                Drawable[] arr_drawable1 = this.mView.getCompoundDrawables();
                TextView textView0 = this.mView;
                if(drawable0 == null) {
                    drawable0 = arr_drawable1[0];
                }
                if(drawable1 == null) {
                    drawable1 = arr_drawable1[1];
                }
                if(drawable2 == null) {
                    drawable2 = arr_drawable1[2];
                }
                if(drawable3 == null) {
                    drawable3 = arr_drawable1[3];
                }
                textView0.setCompoundDrawablesWithIntrinsicBounds(drawable0, drawable1, drawable2, drawable3);
                return;
            }
            if(drawable1 == null) {
                drawable1 = arr_drawable[1];
            }
            if(drawable3 == null) {
                drawable3 = arr_drawable[3];
            }
            this.mView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable6, drawable1, arr_drawable[2], drawable3);
            return;
        }
        Drawable[] arr_drawable2 = this.mView.getCompoundDrawablesRelative();
        if(drawable4 == null) {
            drawable4 = arr_drawable2[0];
        }
        if(drawable1 == null) {
            drawable1 = arr_drawable2[1];
        }
        if(drawable5 == null) {
            drawable5 = arr_drawable2[2];
        }
        TextView textView1 = this.mView;
        if(drawable3 == null) {
            drawable3 = arr_drawable2[3];
        }
        textView1.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable4, drawable1, drawable5, drawable3);
    }

    private void setCompoundTints() {
        this.mDrawableLeftTint = this.mDrawableTint;
        this.mDrawableTopTint = this.mDrawableTint;
        this.mDrawableRightTint = this.mDrawableTint;
        this.mDrawableBottomTint = this.mDrawableTint;
        this.mDrawableStartTint = this.mDrawableTint;
        this.mDrawableEndTint = this.mDrawableTint;
    }

    void setTextSize(int v, float f) {
        if(!ViewUtils.SDK_LEVEL_SUPPORTS_AUTOSIZE && !this.isAutoSizeEnabled()) {
            this.setTextSizeInternal(v, f);
        }
    }

    private void setTextSizeInternal(int v, float f) {
        this.mAutoSizeTextHelper.setTextSizeInternal(v, f);
    }

    private void updateTypefaceAndStyle(Context context0, TintTypedArray tintTypedArray0) {
        this.mStyle = tintTypedArray0.getInt(styleable.TextAppearance_android_textStyle, this.mStyle);
        if(Build.VERSION.SDK_INT >= 28) {
            int v = tintTypedArray0.getInt(styleable.TextAppearance_android_textFontWeight, -1);
            this.mFontWeight = v;
            if(v != -1) {
                this.mStyle &= 2;
            }
        }
        boolean z = true;
        if(tintTypedArray0.hasValue(styleable.TextAppearance_android_fontFamily) || tintTypedArray0.hasValue(styleable.TextAppearance_fontFamily)) {
            this.mFontTypeface = null;
            int v2 = tintTypedArray0.hasValue(styleable.TextAppearance_fontFamily) ? styleable.TextAppearance_fontFamily : styleable.TextAppearance_android_fontFamily;
            int v3 = this.mFontWeight;
            int v4 = this.mStyle;
            if(!context0.isRestricted()) {
                androidx.appcompat.widget.AppCompatTextHelper.1 appCompatTextHelper$10 = new FontCallback() {
                    @Override  // androidx.core.content.res.ResourcesCompat$FontCallback
                    public void onFontRetrievalFailed(int v) {
                    }

                    @Override  // androidx.core.content.res.ResourcesCompat$FontCallback
                    public void onFontRetrieved(Typeface typeface0) {
                        if(Build.VERSION.SDK_INT >= 28) {
                            int v = v3;
                            if(v != -1) {
                                typeface0 = Api28Impl.create(typeface0, v, (v4 & 2) != 0);
                            }
                        }
                        AppCompatTextHelper.this.onAsyncTypefaceReceived(new WeakReference(this.mView), typeface0);
                    }
                };
                try {
                    Typeface typeface0 = tintTypedArray0.getFont(v2, this.mStyle, appCompatTextHelper$10);
                    if(typeface0 != null) {
                        this.mFontTypeface = Build.VERSION.SDK_INT < 28 || this.mFontWeight == -1 ? typeface0 : Api28Impl.create(Typeface.create(typeface0, 0), this.mFontWeight, (this.mStyle & 2) != 0);
                    }
                    this.mAsyncFontPending = this.mFontTypeface == null;
                }
                catch(UnsupportedOperationException | Resources.NotFoundException unused_ex) {
                }
            }
            if(this.mFontTypeface == null) {
                String s = tintTypedArray0.getString(v2);
                if(s != null) {
                    if(Build.VERSION.SDK_INT >= 28 && this.mFontWeight != -1) {
                        Typeface typeface1 = Typeface.create(s, 0);
                        int v5 = this.mFontWeight;
                        if((this.mStyle & 2) == 0) {
                            z = false;
                        }
                        this.mFontTypeface = Api28Impl.create(typeface1, v5, z);
                        return;
                    }
                    this.mFontTypeface = Typeface.create(s, this.mStyle);
                }
            }
        }
        else if(tintTypedArray0.hasValue(styleable.TextAppearance_android_typeface)) {
            this.mAsyncFontPending = false;
            int v1 = tintTypedArray0.getInt(styleable.TextAppearance_android_typeface, 1);
            switch(v1) {
                case 1: {
                    this.mFontTypeface = Typeface.SANS_SERIF;
                    return;
                }
                case 2: {
                    this.mFontTypeface = Typeface.SERIF;
                    return;
                }
            }
            if(v1 == 3) {
                this.mFontTypeface = Typeface.MONOSPACE;
            }
        }
    }
}

