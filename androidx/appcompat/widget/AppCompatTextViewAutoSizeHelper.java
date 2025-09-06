package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout.Builder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.R.styleable;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

class AppCompatTextViewAutoSizeHelper {
    static final class Api23Impl {
        static StaticLayout createStaticLayoutForMeasuring(CharSequence charSequence0, Layout.Alignment layout$Alignment0, int v, int v1, TextView textView0, TextPaint textPaint0, Impl appCompatTextViewAutoSizeHelper$Impl0) {
            StaticLayout.Builder staticLayout$Builder0 = StaticLayout.Builder.obtain(charSequence0, 0, charSequence0.length(), textPaint0, v);
            StaticLayout.Builder staticLayout$Builder1 = staticLayout$Builder0.setAlignment(layout$Alignment0).setLineSpacing(textView0.getLineSpacingExtra(), textView0.getLineSpacingMultiplier()).setIncludePad(textView0.getIncludeFontPadding()).setBreakStrategy(textView0.getBreakStrategy()).setHyphenationFrequency(textView0.getHyphenationFrequency());
            if(v1 == -1) {
                v1 = 0x7FFFFFFF;
            }
            staticLayout$Builder1.setMaxLines(v1);
            try {
                appCompatTextViewAutoSizeHelper$Impl0.computeAndSetTextDirection(staticLayout$Builder0, textView0);
                return staticLayout$Builder0.build();
            }
            catch(ClassCastException unused_ex) {
                Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
                return staticLayout$Builder0.build();
            }
        }
    }

    static class Impl23 extends Impl {
        @Override  // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper$Impl
        void computeAndSetTextDirection(StaticLayout.Builder staticLayout$Builder0, TextView textView0) {
            staticLayout$Builder0.setTextDirection(((TextDirectionHeuristic)AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(textView0, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR)));
        }
    }

    static class Impl29 extends Impl23 {
        @Override  // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper$Impl23
        void computeAndSetTextDirection(StaticLayout.Builder staticLayout$Builder0, TextView textView0) {
            staticLayout$Builder0.setTextDirection(textView0.getTextDirectionHeuristic());
        }

        @Override  // androidx.appcompat.widget.AppCompatTextViewAutoSizeHelper$Impl
        boolean isHorizontallyScrollable(TextView textView0) {
            return textView0.isHorizontallyScrollable();
        }
    }

    static class Impl {
        void computeAndSetTextDirection(StaticLayout.Builder staticLayout$Builder0, TextView textView0) {
        }

        boolean isHorizontallyScrollable(TextView textView0) {
            return ((Boolean)AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(textView0, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
        }
    }

    private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
    private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 0x70;
    private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
    private static final String TAG = "ACTVAutoSizeHelper";
    private static final RectF TEMP_RECTF = null;
    static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0f;
    private static final int VERY_WIDE = 0x100000;
    private float mAutoSizeMaxTextSizeInPx;
    private float mAutoSizeMinTextSizeInPx;
    private float mAutoSizeStepGranularityInPx;
    private int[] mAutoSizeTextSizesInPx;
    private int mAutoSizeTextType;
    private final Context mContext;
    private boolean mHasPresetAutoSizeValues;
    private final Impl mImpl;
    private boolean mNeedsAutoSizeText;
    private TextPaint mTempTextPaint;
    private final TextView mTextView;
    private static ConcurrentHashMap sTextViewMethodByNameCache;

    static {
        AppCompatTextViewAutoSizeHelper.TEMP_RECTF = new RectF();
        AppCompatTextViewAutoSizeHelper.sTextViewMethodByNameCache = new ConcurrentHashMap();
    }

    AppCompatTextViewAutoSizeHelper(TextView textView0) {
        this.mAutoSizeTextType = 0;
        this.mNeedsAutoSizeText = false;
        this.mAutoSizeStepGranularityInPx = -1.0f;
        this.mAutoSizeMinTextSizeInPx = -1.0f;
        this.mAutoSizeMaxTextSizeInPx = -1.0f;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mHasPresetAutoSizeValues = false;
        this.mTextView = textView0;
        this.mContext = textView0.getContext();
        if(Build.VERSION.SDK_INT >= 29) {
            this.mImpl = new Impl29();
            return;
        }
        if(Build.VERSION.SDK_INT >= 23) {
            this.mImpl = new Impl23();
            return;
        }
        this.mImpl = new Impl();
    }

    void autoSizeText() {
        if(this.isAutoSizeEnabled()) {
            if(!this.mNeedsAutoSizeText) {
                this.mNeedsAutoSizeText = true;
                return;
            }
            if(this.mTextView.getMeasuredHeight() > 0 && this.mTextView.getMeasuredWidth() > 0) {
                int v = this.mImpl.isHorizontallyScrollable(this.mTextView) ? 0x100000 : this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft() - this.mTextView.getTotalPaddingRight();
                int v1 = this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom() - this.mTextView.getCompoundPaddingTop();
                if(v > 0 && v1 > 0) {
                    RectF rectF0 = AppCompatTextViewAutoSizeHelper.TEMP_RECTF;
                    synchronized(rectF0) {
                        rectF0.setEmpty();
                        rectF0.right = (float)v;
                        rectF0.bottom = (float)v1;
                        float f = (float)this.findLargestTextSizeWhichFits(rectF0);
                        if(f != this.mTextView.getTextSize()) {
                            this.setTextSizeInternal(0, f);
                        }
                    }
                    this.mNeedsAutoSizeText = true;
                }
            }
        }
    }

    private int[] cleanupAutoSizePresetSizes(int[] arr_v) {
        if(arr_v.length != 0) {
            Arrays.sort(arr_v);
            ArrayList arrayList0 = new ArrayList();
            for(int v1 = 0; v1 < arr_v.length; ++v1) {
                int v2 = arr_v[v1];
                if(v2 > 0 && Collections.binarySearch(arrayList0, v2) < 0) {
                    arrayList0.add(v2);
                }
            }
            if(arr_v.length != arrayList0.size()) {
                int v3 = arrayList0.size();
                int[] arr_v1 = new int[v3];
                for(int v = 0; v < v3; ++v) {
                    arr_v1[v] = (int)(((Integer)arrayList0.get(v)));
                }
                return arr_v1;
            }
        }
        return arr_v;
    }

    private void clearAutoSizeConfiguration() {
        this.mAutoSizeTextType = 0;
        this.mAutoSizeMinTextSizeInPx = -1.0f;
        this.mAutoSizeMaxTextSizeInPx = -1.0f;
        this.mAutoSizeStepGranularityInPx = -1.0f;
        this.mAutoSizeTextSizesInPx = new int[0];
        this.mNeedsAutoSizeText = false;
    }

    StaticLayout createLayout(CharSequence charSequence0, Layout.Alignment layout$Alignment0, int v, int v1) {
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.createStaticLayoutForMeasuring(charSequence0, layout$Alignment0, v, v1, this.mTextView, this.mTempTextPaint, this.mImpl);
        }
        float f = this.mTextView.getLineSpacingMultiplier();
        float f1 = this.mTextView.getLineSpacingExtra();
        boolean z = this.mTextView.getIncludeFontPadding();
        return new StaticLayout(charSequence0, this.mTempTextPaint, v, layout$Alignment0, f, f1, z);
    }

    private int findLargestTextSizeWhichFits(RectF rectF0) {
        int v = this.mAutoSizeTextSizesInPx.length;
        if(v == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int v1 = 1;
        int v2 = v - 1;
        int v3 = 0;
        while(v1 <= v2) {
            int v4 = (v1 + v2) / 2;
            if(this.suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[v4], rectF0)) {
                v3 = v1;
                v1 = v4 + 1;
            }
            else {
                v3 = v4 - 1;
                v2 = v3;
            }
        }
        return this.mAutoSizeTextSizesInPx[v3];
    }

    int getAutoSizeMaxTextSize() {
        return Math.round(this.mAutoSizeMaxTextSizeInPx);
    }

    int getAutoSizeMinTextSize() {
        return Math.round(this.mAutoSizeMinTextSizeInPx);
    }

    int getAutoSizeStepGranularity() {
        return Math.round(this.mAutoSizeStepGranularityInPx);
    }

    int[] getAutoSizeTextAvailableSizes() {
        return this.mAutoSizeTextSizesInPx;
    }

    int getAutoSizeTextType() {
        return this.mAutoSizeTextType;
    }

    private static Method getTextViewMethod(String s) {
        try {
            Method method0 = (Method)AppCompatTextViewAutoSizeHelper.sTextViewMethodByNameCache.get(s);
            if(method0 == null) {
                method0 = TextView.class.getDeclaredMethod(s, null);
                if(method0 != null) {
                    method0.setAccessible(true);
                    AppCompatTextViewAutoSizeHelper.sTextViewMethodByNameCache.put(s, method0);
                }
            }
            return method0;
        }
        catch(Exception exception0) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + s + "() method", exception0);
            return null;
        }
    }

    void initTempTextPaint(int v) {
        TextPaint textPaint0 = this.mTempTextPaint;
        if(textPaint0 == null) {
            this.mTempTextPaint = new TextPaint();
        }
        else {
            textPaint0.reset();
        }
        this.mTempTextPaint.set(this.mTextView.getPaint());
        this.mTempTextPaint.setTextSize(((float)v));
    }

    static Object invokeAndReturnWithDefault(Object object0, String s, Object object1) {
        try {
            return AppCompatTextViewAutoSizeHelper.getTextViewMethod(s).invoke(object0, null);
        }
        catch(Exception exception0) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + s + "() method", exception0);
            return object1;
        }
    }

    boolean isAutoSizeEnabled() {
        return this.supportsAutoSizeText() && this.mAutoSizeTextType != 0;
    }

    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        TypedArray typedArray0 = this.mContext.obtainStyledAttributes(attributeSet0, styleable.AppCompatTextView, v, 0);
        Context context0 = this.mTextView.getContext();
        ViewCompat.saveAttributeDataForStyleable(this.mTextView, context0, styleable.AppCompatTextView, attributeSet0, typedArray0, v, 0);
        if(typedArray0.hasValue(styleable.AppCompatTextView_autoSizeTextType)) {
            this.mAutoSizeTextType = typedArray0.getInt(styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        float f = typedArray0.hasValue(styleable.AppCompatTextView_autoSizeStepGranularity) ? typedArray0.getDimension(styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float f1 = typedArray0.hasValue(styleable.AppCompatTextView_autoSizeMinTextSize) ? typedArray0.getDimension(styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float f2 = typedArray0.hasValue(styleable.AppCompatTextView_autoSizeMaxTextSize) ? typedArray0.getDimension(styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if(typedArray0.hasValue(styleable.AppCompatTextView_autoSizePresetSizes)) {
            int v1 = typedArray0.getResourceId(styleable.AppCompatTextView_autoSizePresetSizes, 0);
            if(v1 > 0) {
                TypedArray typedArray1 = typedArray0.getResources().obtainTypedArray(v1);
                this.setupAutoSizeUniformPresetSizes(typedArray1);
                typedArray1.recycle();
            }
        }
        typedArray0.recycle();
        if(this.supportsAutoSizeText()) {
            if(this.mAutoSizeTextType == 1) {
                if(!this.mHasPresetAutoSizeValues) {
                    DisplayMetrics displayMetrics0 = this.mContext.getResources().getDisplayMetrics();
                    if(f1 == -1.0f) {
                        f1 = TypedValue.applyDimension(2, 12.0f, displayMetrics0);
                    }
                    if(f2 == -1.0f) {
                        f2 = TypedValue.applyDimension(2, 112.0f, displayMetrics0);
                    }
                    if(f == -1.0f) {
                        f = 1.0f;
                    }
                    this.validateAndSetAutoSizeTextTypeUniformConfiguration(f1, f2, f);
                }
                this.setupAutoSizeText();
            }
            return;
        }
        this.mAutoSizeTextType = 0;
    }

    void setAutoSizeTextTypeUniformWithConfiguration(int v, int v1, int v2, int v3) throws IllegalArgumentException {
        if(this.supportsAutoSizeText()) {
            DisplayMetrics displayMetrics0 = this.mContext.getResources().getDisplayMetrics();
            this.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(v3, ((float)v), displayMetrics0), TypedValue.applyDimension(v3, ((float)v1), displayMetrics0), TypedValue.applyDimension(v3, ((float)v2), displayMetrics0));
            if(this.setupAutoSizeText()) {
                this.autoSizeText();
            }
        }
    }

    void setAutoSizeTextTypeUniformWithPresetSizes(int[] arr_v, int v) throws IllegalArgumentException {
        if(this.supportsAutoSizeText()) {
            if(arr_v.length > 0) {
                int[] arr_v1 = new int[arr_v.length];
                if(v == 0) {
                    arr_v1 = Arrays.copyOf(arr_v, arr_v.length);
                }
                else {
                    DisplayMetrics displayMetrics0 = this.mContext.getResources().getDisplayMetrics();
                    for(int v1 = 0; v1 < arr_v.length; ++v1) {
                        arr_v1[v1] = Math.round(TypedValue.applyDimension(v, ((float)arr_v[v1]), displayMetrics0));
                    }
                }
                this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(arr_v1);
                if(!this.setupAutoSizeUniformPresetSizesConfiguration()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(arr_v));
                }
            }
            else {
                this.mHasPresetAutoSizeValues = false;
            }
            if(this.setupAutoSizeText()) {
                this.autoSizeText();
            }
        }
    }

    void setAutoSizeTextTypeWithDefaults(int v) {
        if(this.supportsAutoSizeText()) {
            switch(v) {
                case 0: {
                    this.clearAutoSizeConfiguration();
                    return;
                }
                case 1: {
                    DisplayMetrics displayMetrics0 = this.mContext.getResources().getDisplayMetrics();
                    this.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0f, displayMetrics0), TypedValue.applyDimension(2, 112.0f, displayMetrics0), 1.0f);
                    if(this.setupAutoSizeText()) {
                        this.autoSizeText();
                        return;
                    }
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown auto-size text type: " + v);
                }
            }
        }
    }

    private void setRawTextSize(float f) {
        if(f != this.mTextView.getPaint().getTextSize()) {
            this.mTextView.getPaint().setTextSize(f);
            boolean z = this.mTextView.isInLayout();
            if(this.mTextView.getLayout() != null) {
                try {
                    this.mNeedsAutoSizeText = false;
                    Method method0 = AppCompatTextViewAutoSizeHelper.getTextViewMethod("nullLayouts");
                    if(method0 != null) {
                        method0.invoke(this.mTextView, null);
                    }
                }
                catch(Exception exception0) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", exception0);
                }
                if(z) {
                    this.mTextView.forceLayout();
                }
                else {
                    this.mTextView.requestLayout();
                }
                this.mTextView.invalidate();
            }
        }
    }

    void setTextSizeInternal(int v, float f) {
        this.setRawTextSize(TypedValue.applyDimension(v, f, (this.mContext == null ? Resources.getSystem() : this.mContext.getResources()).getDisplayMetrics()));
    }

    private boolean setupAutoSizeText() {
        if(this.supportsAutoSizeText() && this.mAutoSizeTextType == 1) {
            if(!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
                int v1 = ((int)Math.floor((this.mAutoSizeMaxTextSizeInPx - this.mAutoSizeMinTextSizeInPx) / this.mAutoSizeStepGranularityInPx)) + 1;
                int[] arr_v = new int[v1];
                for(int v = 0; v < v1; ++v) {
                    arr_v[v] = Math.round(this.mAutoSizeMinTextSizeInPx + ((float)v) * this.mAutoSizeStepGranularityInPx);
                }
                this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(arr_v);
            }
            this.mNeedsAutoSizeText = true;
            return true;
        }
        this.mNeedsAutoSizeText = false;
        return false;
    }

    private void setupAutoSizeUniformPresetSizes(TypedArray typedArray0) {
        int v = typedArray0.length();
        int[] arr_v = new int[v];
        if(v > 0) {
            for(int v1 = 0; v1 < v; ++v1) {
                arr_v[v1] = typedArray0.getDimensionPixelSize(v1, -1);
            }
            this.mAutoSizeTextSizesInPx = this.cleanupAutoSizePresetSizes(arr_v);
            this.setupAutoSizeUniformPresetSizesConfiguration();
        }
    }

    private boolean setupAutoSizeUniformPresetSizesConfiguration() {
        int[] arr_v = this.mAutoSizeTextSizesInPx;
        boolean z = arr_v.length > 0;
        this.mHasPresetAutoSizeValues = z;
        if(z) {
            this.mAutoSizeTextType = 1;
            this.mAutoSizeMinTextSizeInPx = (float)arr_v[0];
            this.mAutoSizeMaxTextSizeInPx = (float)arr_v[arr_v.length - 1];
            this.mAutoSizeStepGranularityInPx = -1.0f;
        }
        return z;
    }

    private boolean suggestedSizeFitsInSpace(int v, RectF rectF0) {
        CharSequence charSequence0 = this.mTextView.getText();
        TransformationMethod transformationMethod0 = this.mTextView.getTransformationMethod();
        if(transformationMethod0 != null) {
            CharSequence charSequence1 = transformationMethod0.getTransformation(charSequence0, this.mTextView);
            if(charSequence1 != null) {
                charSequence0 = charSequence1;
            }
        }
        int v1 = this.mTextView.getMaxLines();
        this.initTempTextPaint(v);
        StaticLayout staticLayout0 = this.createLayout(charSequence0, ((Layout.Alignment)AppCompatTextViewAutoSizeHelper.invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL)), Math.round(rectF0.right), v1);
        return v1 == -1 || staticLayout0.getLineCount() <= v1 && staticLayout0.getLineEnd(staticLayout0.getLineCount() - 1) == charSequence0.length() ? ((float)staticLayout0.getHeight()) <= rectF0.bottom : false;
    }

    private boolean supportsAutoSizeText() {
        return !(this.mTextView instanceof AppCompatEditText);
    }

    private void validateAndSetAutoSizeTextTypeUniformConfiguration(float f, float f1, float f2) throws IllegalArgumentException {
        if(f <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
        }
        if(f1 <= f) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f1 + "px) is less or equal to minimum auto-size text size (" + f + "px)");
        }
        if(f2 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f2 + "px) is less or equal to (0px)");
        }
        this.mAutoSizeTextType = 1;
        this.mAutoSizeMinTextSizeInPx = f;
        this.mAutoSizeMaxTextSizeInPx = f1;
        this.mAutoSizeStepGranularityInPx = f2;
        this.mHasPresetAutoSizeValues = false;
    }
}

