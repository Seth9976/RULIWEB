package com.google.android.material.textfield;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.accessibility.AccessibilityEvent;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.text.BidiFormatter;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import com.google.android.material.R.attr;
import com.google.android.material.R.color;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.CornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel.Builder;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedHashSet;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    public static class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout0) {
            this.layout = textInputLayout0;
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
            EditText editText0 = this.layout.getEditText();
            Editable editable0 = editText0 == null ? null : editText0.getText();
            CharSequence charSequence0 = this.layout.getHint();
            CharSequence charSequence1 = this.layout.getError();
            CharSequence charSequence2 = this.layout.getPlaceholderText();
            int v = this.layout.getCounterMaxLength();
            CharSequence charSequence3 = this.layout.getCounterOverflowDescription();
            boolean z = TextUtils.isEmpty(editable0);
            boolean z1 = TextUtils.isEmpty(charSequence0);
            boolean z2 = this.layout.isHintExpanded();
            boolean z3 = TextUtils.isEmpty(charSequence1);
            boolean z4 = !z3 || !TextUtils.isEmpty(charSequence3);
            String s = z1 ? "" : charSequence0.toString();
            this.layout.startLayout.setupAccessibilityNodeInfo(accessibilityNodeInfoCompat0);
            if(!z) {
                accessibilityNodeInfoCompat0.setText(editable0);
            }
            else if(!TextUtils.isEmpty(s)) {
                accessibilityNodeInfoCompat0.setText(s);
                if(!z2 && charSequence2 != null) {
                    accessibilityNodeInfoCompat0.setText(s + ", " + charSequence2);
                }
            }
            else if(charSequence2 != null) {
                accessibilityNodeInfoCompat0.setText(charSequence2);
            }
            if(!TextUtils.isEmpty(s)) {
                if(Build.VERSION.SDK_INT >= 26) {
                    accessibilityNodeInfoCompat0.setHintText(s);
                }
                else {
                    if(!z) {
                        s = editable0 + ", " + s;
                    }
                    accessibilityNodeInfoCompat0.setText(s);
                }
                accessibilityNodeInfoCompat0.setShowingHintText(z);
            }
            if(editable0 == null || editable0.length() != v) {
                v = -1;
            }
            accessibilityNodeInfoCompat0.setMaxTextLength(v);
            if(z4) {
                if(z3) {
                    charSequence1 = charSequence3;
                }
                accessibilityNodeInfoCompat0.setError(charSequence1);
            }
            View view1 = this.layout.indicatorViewController.getHelperTextView();
            if(view1 != null) {
                accessibilityNodeInfoCompat0.setLabelFor(view1);
            }
            this.layout.endLayout.getEndIconDelegate().onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
        }

        @Override  // androidx.core.view.AccessibilityDelegateCompat
        public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            super.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
            this.layout.endLayout.getEndIconDelegate().onPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoxBackgroundMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EndIconMode {
    }

    public interface LengthCounter {
        int countLength(Editable arg1);
    }

    public interface OnEditTextAttachedListener {
        void onEditTextAttached(TextInputLayout arg1);
    }

    public interface OnEndIconChangedListener {
        void onEndIconChanged(TextInputLayout arg1, int arg2);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        CharSequence error;
        boolean isEndIconChecked;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel0);
            this.isEndIconChecked = parcel0.readInt() == 1;
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override
        public String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + this.error + "}";
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            TextUtils.writeToParcel(this.error, parcel0, v);
            parcel0.writeInt(((int)this.isEndIconChecked));
        }
    }

    public static final int BOX_BACKGROUND_FILLED = 1;
    public static final int BOX_BACKGROUND_NONE = 0;
    public static final int BOX_BACKGROUND_OUTLINE = 2;
    private static final int DEFAULT_PLACEHOLDER_FADE_DURATION = 87;
    private static final int DEF_STYLE_RES = 0;
    private static final int[][] EDIT_TEXT_BACKGROUND_RIPPLE_STATE = null;
    public static final int END_ICON_CLEAR_TEXT = 2;
    public static final int END_ICON_CUSTOM = -1;
    public static final int END_ICON_DROPDOWN_MENU = 3;
    public static final int END_ICON_NONE = 0;
    public static final int END_ICON_PASSWORD_TOGGLE = 1;
    private static final int INVALID_MAX_LENGTH = -1;
    private static final int LABEL_SCALE_ANIMATION_DURATION = 0xA7;
    private static final String LOG_TAG = "TextInputLayout";
    private static final int NO_WIDTH = -1;
    private static final int PLACEHOLDER_START_DELAY = 67;
    private ValueAnimator animator;
    private boolean areCornerRadiiRtl;
    private MaterialShapeDrawable boxBackground;
    private boolean boxBackgroundApplied;
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private int boxCollapsedPaddingTopPx;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private int boxStrokeWidthDefaultPx;
    private int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    private MaterialShapeDrawable boxUnderlineDefault;
    private MaterialShapeDrawable boxUnderlineFocused;
    final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    private int counterMaxLength;
    private int counterOverflowTextAppearance;
    private ColorStateList counterOverflowTextColor;
    private boolean counterOverflowed;
    private int counterTextAppearance;
    private ColorStateList counterTextColor;
    private TextView counterView;
    private ColorStateList cursorColor;
    private ColorStateList cursorErrorColor;
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;
    private int defaultStrokeColor;
    private int disabledColor;
    private int disabledFilledBackgroundColor;
    EditText editText;
    private final LinkedHashSet editTextAttachedListeners;
    private Drawable endDummyDrawable;
    private int endDummyDrawableWidth;
    private final EndCompoundLayout endLayout;
    private boolean expandedHintEnabled;
    private StateListDrawable filledDropDownMenuBackground;
    private int focusedFilledBackgroundColor;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean globalLayoutListenerAdded;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    private boolean hintExpanded;
    private int hoveredFilledBackgroundColor;
    private int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    private final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private LengthCounter lengthCounter;
    private int maxEms;
    private int maxWidth;
    private int minEms;
    private int minWidth;
    private Drawable originalEditTextEndDrawable;
    int originalEditTextMinimumHeight;
    private CharSequence originalHint;
    private MaterialShapeDrawable outlinedDropDownMenuBackground;
    private boolean placeholderEnabled;
    private Fade placeholderFadeIn;
    private Fade placeholderFadeOut;
    private CharSequence placeholderText;
    private int placeholderTextAppearance;
    private ColorStateList placeholderTextColor;
    private TextView placeholderTextView;
    private boolean restoringSavedState;
    private ShapeAppearanceModel shapeAppearanceModel;
    private Drawable startDummyDrawable;
    private int startDummyDrawableWidth;
    private final StartCompoundLayout startLayout;
    private ColorStateList strokeErrorColor;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;
    private Typeface typeface;

    static {
        TextInputLayout.DEF_STYLE_RES = style.Widget_Design_TextInputLayout;
        TextInputLayout.EDIT_TEXT_BACKGROUND_RIPPLE_STATE = new int[][]{new int[]{0x10100A7}, new int[0]};
    }

    public TextInputLayout(Context context0) {
        this(context0, null);
    }

    public TextInputLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.textInputStyle);
    }

    public TextInputLayout(Context context0, AttributeSet attributeSet0, int v) {
        int v1 = TextInputLayout.DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, v1), attributeSet0, v);
        this.minEms = -1;
        this.maxEms = -1;
        this.minWidth = -1;
        this.maxWidth = -1;
        this.indicatorViewController = new IndicatorViewController(this);
        this.lengthCounter = (Editable editable0) -> (editable0 == null ? 0 : editable0.length());
        this.tmpRect = new Rect();
        this.tmpBoundsRect = new Rect();
        this.tmpRectF = new RectF();
        this.editTextAttachedListeners = new LinkedHashSet();
        CollapsingTextHelper collapsingTextHelper0 = new CollapsingTextHelper(this);
        this.collapsingTextHelper = collapsingTextHelper0;
        this.globalLayoutListenerAdded = false;
        Context context1 = this.getContext();
        this.setOrientation(1);
        this.setWillNotDraw(false);
        this.setAddStatesFromChildren(true);
        FrameLayout frameLayout0 = new FrameLayout(context1);
        this.inputFrame = frameLayout0;
        frameLayout0.setAddStatesFromChildren(true);
        collapsingTextHelper0.setTextSizeInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        collapsingTextHelper0.setPositionInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        collapsingTextHelper0.setCollapsedTextGravity(0x800033);
        TintTypedArray tintTypedArray0 = ThemeEnforcement.obtainTintedStyledAttributes(context1, attributeSet0, styleable.TextInputLayout, v, v1, new int[]{styleable.TextInputLayout_counterTextAppearance, styleable.TextInputLayout_counterOverflowTextAppearance, styleable.TextInputLayout_errorTextAppearance, styleable.TextInputLayout_helperTextTextAppearance, styleable.TextInputLayout_hintTextAppearance});
        StartCompoundLayout startCompoundLayout0 = new StartCompoundLayout(this, tintTypedArray0);
        this.startLayout = startCompoundLayout0;
        this.hintEnabled = tintTypedArray0.getBoolean(styleable.TextInputLayout_hintEnabled, true);
        this.setHint(tintTypedArray0.getText(styleable.TextInputLayout_android_hint));
        this.hintAnimationEnabled = tintTypedArray0.getBoolean(styleable.TextInputLayout_hintAnimationEnabled, true);
        this.expandedHintEnabled = tintTypedArray0.getBoolean(styleable.TextInputLayout_expandedHintEnabled, true);
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_android_minEms)) {
            this.setMinEms(tintTypedArray0.getInt(styleable.TextInputLayout_android_minEms, -1));
        }
        else if(tintTypedArray0.hasValue(styleable.TextInputLayout_android_minWidth)) {
            this.setMinWidth(tintTypedArray0.getDimensionPixelSize(styleable.TextInputLayout_android_minWidth, -1));
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_android_maxEms)) {
            this.setMaxEms(tintTypedArray0.getInt(styleable.TextInputLayout_android_maxEms, -1));
        }
        else if(tintTypedArray0.hasValue(styleable.TextInputLayout_android_maxWidth)) {
            this.setMaxWidth(tintTypedArray0.getDimensionPixelSize(styleable.TextInputLayout_android_maxWidth, -1));
        }
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(context1, attributeSet0, v, v1).build();
        this.boxLabelCutoutPaddingPx = context1.getResources().getDimensionPixelOffset(dimen.mtrl_textinput_box_label_cutout_padding);
        this.boxCollapsedPaddingTopPx = tintTypedArray0.getDimensionPixelOffset(styleable.TextInputLayout_boxCollapsedPaddingTop, 0);
        int v2 = context1.getResources().getDimensionPixelSize(dimen.mtrl_textinput_box_stroke_width_default);
        this.boxStrokeWidthDefaultPx = tintTypedArray0.getDimensionPixelSize(styleable.TextInputLayout_boxStrokeWidth, v2);
        int v3 = context1.getResources().getDimensionPixelSize(dimen.mtrl_textinput_box_stroke_width_focused);
        this.boxStrokeWidthFocusedPx = tintTypedArray0.getDimensionPixelSize(styleable.TextInputLayout_boxStrokeWidthFocused, v3);
        this.boxStrokeWidthPx = this.boxStrokeWidthDefaultPx;
        float f = tintTypedArray0.getDimension(styleable.TextInputLayout_boxCornerRadiusTopStart, -1.0f);
        float f1 = tintTypedArray0.getDimension(styleable.TextInputLayout_boxCornerRadiusTopEnd, -1.0f);
        float f2 = tintTypedArray0.getDimension(styleable.TextInputLayout_boxCornerRadiusBottomEnd, -1.0f);
        float f3 = tintTypedArray0.getDimension(styleable.TextInputLayout_boxCornerRadiusBottomStart, -1.0f);
        Builder shapeAppearanceModel$Builder0 = this.shapeAppearanceModel.toBuilder();
        if(f >= 0.0f) {
            shapeAppearanceModel$Builder0.setTopLeftCornerSize(f);
        }
        if(f1 >= 0.0f) {
            shapeAppearanceModel$Builder0.setTopRightCornerSize(f1);
        }
        if(f2 >= 0.0f) {
            shapeAppearanceModel$Builder0.setBottomRightCornerSize(f2);
        }
        if(f3 >= 0.0f) {
            shapeAppearanceModel$Builder0.setBottomLeftCornerSize(f3);
        }
        this.shapeAppearanceModel = shapeAppearanceModel$Builder0.build();
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(context1, tintTypedArray0, styleable.TextInputLayout_boxBackgroundColor);
        if(colorStateList0 == null) {
            this.boxBackgroundColor = 0;
            this.defaultFilledBackgroundColor = 0;
            this.disabledFilledBackgroundColor = 0;
            this.focusedFilledBackgroundColor = 0;
            this.hoveredFilledBackgroundColor = 0;
        }
        else {
            int v4 = colorStateList0.getDefaultColor();
            this.defaultFilledBackgroundColor = v4;
            this.boxBackgroundColor = v4;
            if(colorStateList0.isStateful()) {
                this.disabledFilledBackgroundColor = colorStateList0.getColorForState(new int[]{0xFEFEFF62}, -1);
                this.focusedFilledBackgroundColor = colorStateList0.getColorForState(new int[]{0x101009C, 0x101009E}, -1);
                this.hoveredFilledBackgroundColor = colorStateList0.getColorForState(new int[]{0x1010367, 0x101009E}, -1);
            }
            else {
                this.focusedFilledBackgroundColor = this.defaultFilledBackgroundColor;
                ColorStateList colorStateList1 = AppCompatResources.getColorStateList(context1, color.mtrl_filled_background_color);
                this.disabledFilledBackgroundColor = colorStateList1.getColorForState(new int[]{0xFEFEFF62}, -1);
                this.hoveredFilledBackgroundColor = colorStateList1.getColorForState(new int[]{0x1010367}, -1);
            }
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_android_textColorHint)) {
            ColorStateList colorStateList2 = tintTypedArray0.getColorStateList(styleable.TextInputLayout_android_textColorHint);
            this.focusedTextColor = colorStateList2;
            this.defaultHintTextColor = colorStateList2;
        }
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context1, tintTypedArray0, styleable.TextInputLayout_boxStrokeColor);
        this.focusedStrokeColor = tintTypedArray0.getColor(styleable.TextInputLayout_boxStrokeColor, 0);
        this.defaultStrokeColor = ContextCompat.getColor(context1, color.mtrl_textinput_default_box_stroke_color);
        this.disabledColor = ContextCompat.getColor(context1, color.mtrl_textinput_disabled_color);
        this.hoveredStrokeColor = ContextCompat.getColor(context1, color.mtrl_textinput_hovered_box_stroke_color);
        if(colorStateList3 != null) {
            this.setBoxStrokeColorStateList(colorStateList3);
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_boxStrokeErrorColor)) {
            this.setBoxStrokeErrorColor(MaterialResources.getColorStateList(context1, tintTypedArray0, styleable.TextInputLayout_boxStrokeErrorColor));
        }
        if(tintTypedArray0.getResourceId(styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
            this.setHintTextAppearance(tintTypedArray0.getResourceId(styleable.TextInputLayout_hintTextAppearance, 0));
        }
        this.cursorColor = tintTypedArray0.getColorStateList(styleable.TextInputLayout_cursorColor);
        this.cursorErrorColor = tintTypedArray0.getColorStateList(styleable.TextInputLayout_cursorErrorColor);
        int v5 = tintTypedArray0.getResourceId(styleable.TextInputLayout_errorTextAppearance, 0);
        CharSequence charSequence0 = tintTypedArray0.getText(styleable.TextInputLayout_errorContentDescription);
        int v6 = tintTypedArray0.getInt(styleable.TextInputLayout_errorAccessibilityLiveRegion, 1);
        boolean z = tintTypedArray0.getBoolean(styleable.TextInputLayout_errorEnabled, false);
        int v7 = tintTypedArray0.getResourceId(styleable.TextInputLayout_helperTextTextAppearance, 0);
        boolean z1 = tintTypedArray0.getBoolean(styleable.TextInputLayout_helperTextEnabled, false);
        CharSequence charSequence1 = tintTypedArray0.getText(styleable.TextInputLayout_helperText);
        int v8 = tintTypedArray0.getResourceId(styleable.TextInputLayout_placeholderTextAppearance, 0);
        CharSequence charSequence2 = tintTypedArray0.getText(styleable.TextInputLayout_placeholderText);
        boolean z2 = tintTypedArray0.getBoolean(styleable.TextInputLayout_counterEnabled, false);
        this.setCounterMaxLength(tintTypedArray0.getInt(styleable.TextInputLayout_counterMaxLength, -1));
        this.counterTextAppearance = tintTypedArray0.getResourceId(styleable.TextInputLayout_counterTextAppearance, 0);
        this.counterOverflowTextAppearance = tintTypedArray0.getResourceId(styleable.TextInputLayout_counterOverflowTextAppearance, 0);
        this.setBoxBackgroundMode(tintTypedArray0.getInt(styleable.TextInputLayout_boxBackgroundMode, 0));
        this.setErrorContentDescription(charSequence0);
        this.setErrorAccessibilityLiveRegion(v6);
        this.setCounterOverflowTextAppearance(this.counterOverflowTextAppearance);
        this.setHelperTextTextAppearance(v7);
        this.setErrorTextAppearance(v5);
        this.setCounterTextAppearance(this.counterTextAppearance);
        this.setPlaceholderText(charSequence2);
        this.setPlaceholderTextAppearance(v8);
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_errorTextColor)) {
            this.setErrorTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_errorTextColor));
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_helperTextTextColor)) {
            this.setHelperTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_helperTextTextColor));
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_hintTextColor)) {
            this.setHintTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_hintTextColor));
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_counterTextColor)) {
            this.setCounterTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_counterTextColor));
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_counterOverflowTextColor)) {
            this.setCounterOverflowTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_counterOverflowTextColor));
        }
        if(tintTypedArray0.hasValue(styleable.TextInputLayout_placeholderTextColor)) {
            this.setPlaceholderTextColor(tintTypedArray0.getColorStateList(styleable.TextInputLayout_placeholderTextColor));
        }
        EndCompoundLayout endCompoundLayout0 = new EndCompoundLayout(this, tintTypedArray0);
        this.endLayout = endCompoundLayout0;
        boolean z3 = tintTypedArray0.getBoolean(styleable.TextInputLayout_android_enabled, true);
        tintTypedArray0.recycle();
        ViewCompat.setImportantForAccessibility(this, 2);
        if(Build.VERSION.SDK_INT >= 26) {
            ViewCompat.setImportantForAutofill(this, 1);
        }
        frameLayout0.addView(startCompoundLayout0);
        frameLayout0.addView(endCompoundLayout0);
        this.addView(frameLayout0);
        this.setEnabled(z3);
        this.setHelperTextEnabled(z1);
        this.setErrorEnabled(z);
        this.setCounterEnabled(z2);
        this.setHelperText(charSequence1);
    }

    public void addOnEditTextAttachedListener(OnEditTextAttachedListener textInputLayout$OnEditTextAttachedListener0) {
        this.editTextAttachedListeners.add(textInputLayout$OnEditTextAttachedListener0);
        if(this.editText != null) {
            textInputLayout$OnEditTextAttachedListener0.onEditTextAttached(this);
        }
    }

    public void addOnEndIconChangedListener(OnEndIconChangedListener textInputLayout$OnEndIconChangedListener0) {
        this.endLayout.addOnEndIconChangedListener(textInputLayout$OnEndIconChangedListener0);
    }

    private void addPlaceholderTextView() {
        TextView textView0 = this.placeholderTextView;
        if(textView0 != null) {
            this.inputFrame.addView(textView0);
            this.placeholderTextView.setVisibility(0);
        }
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(view0 instanceof EditText) {
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(viewGroup$LayoutParams0);
            frameLayout$LayoutParams0.gravity = frameLayout$LayoutParams0.gravity & 0xFFFFFF8F | 16;
            this.inputFrame.addView(view0, frameLayout$LayoutParams0);
            this.inputFrame.setLayoutParams(viewGroup$LayoutParams0);
            this.updateInputLayoutMargins();
            this.setEditText(((EditText)view0));
            return;
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    private void adjustFilledEditTextPaddingForLargeFont() {
        if(this.editText != null && this.boxBackgroundMode == 1) {
            if(MaterialResources.isFontScaleAtLeast2_0(this.getContext())) {
                ViewCompat.setPaddingRelative(this.editText, ViewCompat.getPaddingStart(this.editText), this.getResources().getDimensionPixelSize(dimen.material_filled_edittext_font_2_0_padding_top), ViewCompat.getPaddingEnd(this.editText), this.getResources().getDimensionPixelSize(dimen.material_filled_edittext_font_2_0_padding_bottom));
                return;
            }
            if(MaterialResources.isFontScaleAtLeast1_3(this.getContext())) {
                ViewCompat.setPaddingRelative(this.editText, ViewCompat.getPaddingStart(this.editText), this.getResources().getDimensionPixelSize(dimen.material_filled_edittext_font_1_3_padding_top), ViewCompat.getPaddingEnd(this.editText), this.getResources().getDimensionPixelSize(dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
    }

    void animateToExpansionFraction(float f) {
        if(this.collapsingTextHelper.getExpansionFraction() == f) {
            return;
        }
        if(this.animator == null) {
            ValueAnimator valueAnimator0 = new ValueAnimator();
            this.animator = valueAnimator0;
            valueAnimator0.setInterpolator(MotionUtils.resolveThemeInterpolator(this.getContext(), attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            this.animator.setDuration(((long)MotionUtils.resolveThemeDuration(this.getContext(), attr.motionDurationMedium4, 0xA7)));
            this.animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                    float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                    TextInputLayout.this.collapsingTextHelper.setExpansionFraction(f);
                }
            });
        }
        this.animator.setFloatValues(new float[]{this.collapsingTextHelper.getExpansionFraction(), f});
        this.animator.start();
    }

    private void applyBoxAttributes() {
        MaterialShapeDrawable materialShapeDrawable0 = this.boxBackground;
        if(materialShapeDrawable0 == null) {
            return;
        }
        ShapeAppearanceModel shapeAppearanceModel0 = this.shapeAppearanceModel;
        if(materialShapeDrawable0.getShapeAppearanceModel() != shapeAppearanceModel0) {
            this.boxBackground.setShapeAppearanceModel(shapeAppearanceModel0);
        }
        if(this.canDrawOutlineStroke()) {
            this.boxBackground.setStroke(((float)this.boxStrokeWidthPx), this.boxStrokeColor);
        }
        int v = this.calculateBoxBackgroundColor();
        this.boxBackgroundColor = v;
        this.boxBackground.setFillColor(ColorStateList.valueOf(v));
        this.applyBoxUnderlineAttributes();
        this.updateEditTextBoxBackgroundIfNeeded();
    }

    private void applyBoxUnderlineAttributes() {
        if(this.boxUnderlineDefault != null && this.boxUnderlineFocused != null) {
            if(this.canDrawStroke()) {
                this.boxUnderlineDefault.setFillColor((this.editText.isFocused() ? ColorStateList.valueOf(this.defaultStrokeColor) : ColorStateList.valueOf(this.boxStrokeColor)));
                this.boxUnderlineFocused.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
            }
            this.invalidate();
        }
    }

    private void applyCutoutPadding(RectF rectF0) {
        rectF0.left -= (float)this.boxLabelCutoutPaddingPx;
        rectF0.right += (float)this.boxLabelCutoutPaddingPx;
    }

    private void assignBoxBackgroundByMode() {
        switch(this.boxBackgroundMode) {
            case 0: {
                this.boxBackground = null;
                this.boxUnderlineDefault = null;
                this.boxUnderlineFocused = null;
                return;
            }
            case 1: {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
                this.boxUnderlineDefault = new MaterialShapeDrawable();
                this.boxUnderlineFocused = new MaterialShapeDrawable();
                return;
            }
            case 2: {
                this.boxBackground = !this.hintEnabled || this.boxBackground instanceof CutoutDrawable ? new MaterialShapeDrawable(this.shapeAppearanceModel) : CutoutDrawable.create(this.shapeAppearanceModel);
                this.boxUnderlineDefault = null;
                this.boxUnderlineFocused = null;
                return;
            }
            default: {
                throw new IllegalArgumentException(this.boxBackgroundMode + " is illegal; only @BoxBackgroundMode constants are supported.");
            }
        }
    }

    private int calculateBoxBackgroundColor() {
        return this.boxBackgroundMode == 1 ? MaterialColors.layer(MaterialColors.getColor(this, attr.colorSurface, 0), this.boxBackgroundColor) : this.boxBackgroundColor;
    }

    private Rect calculateCollapsedTextBounds(Rect rect0) {
        if(this.editText != null) {
            Rect rect1 = this.tmpBoundsRect;
            boolean z = ViewUtils.isLayoutRtl(this);
            rect1.bottom = rect0.bottom;
            switch(this.boxBackgroundMode) {
                case 1: {
                    rect1.left = this.getLabelLeftBoundAlignedWithPrefixAndSuffix(rect0.left, z);
                    rect1.top = rect0.top + this.boxCollapsedPaddingTopPx;
                    rect1.right = this.getLabelRightBoundAlignedWithPrefixAndSuffix(rect0.right, z);
                    return rect1;
                }
                case 2: {
                    rect1.left = rect0.left + this.editText.getPaddingLeft();
                    rect1.top = rect0.top - this.calculateLabelMarginTop();
                    rect1.right = rect0.right - this.editText.getPaddingRight();
                    return rect1;
                }
                default: {
                    rect1.left = this.getLabelLeftBoundAlignedWithPrefixAndSuffix(rect0.left, z);
                    rect1.top = this.getPaddingTop();
                    rect1.right = this.getLabelRightBoundAlignedWithPrefixAndSuffix(rect0.right, z);
                    return rect1;
                }
            }
        }
        throw new IllegalStateException();
    }

    // 去混淆评级： 低(20)
    private int calculateExpandedLabelBottom(Rect rect0, Rect rect1, float f) {
        return this.isSingleLineFilledTextField() ? ((int)(((float)rect1.top) + f)) : rect0.bottom - this.editText.getCompoundPaddingBottom();
    }

    // 去混淆评级： 低(20)
    private int calculateExpandedLabelTop(Rect rect0, float f) {
        return this.isSingleLineFilledTextField() ? ((int)(((float)rect0.centerY()) - f / 2.0f)) : rect0.top + this.editText.getCompoundPaddingTop();
    }

    private Rect calculateExpandedTextBounds(Rect rect0) {
        if(this.editText == null) {
            throw new IllegalStateException();
        }
        float f = this.collapsingTextHelper.getExpandedTextHeight();
        this.tmpBoundsRect.left = rect0.left + this.editText.getCompoundPaddingLeft();
        this.tmpBoundsRect.top = this.calculateExpandedLabelTop(rect0, f);
        this.tmpBoundsRect.right = rect0.right - this.editText.getCompoundPaddingRight();
        this.tmpBoundsRect.bottom = this.calculateExpandedLabelBottom(rect0, this.tmpBoundsRect, f);
        return this.tmpBoundsRect;
    }

    private int calculateLabelMarginTop() {
        if(!this.hintEnabled) {
            return 0;
        }
        switch(this.boxBackgroundMode) {
            case 0: {
                return (int)this.collapsingTextHelper.getCollapsedTextHeight();
            }
            case 2: {
                return (int)(this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f);
            }
            default: {
                return 0;
            }
        }
    }

    private boolean canDrawOutlineStroke() {
        return this.boxBackgroundMode == 2 && this.canDrawStroke();
    }

    private boolean canDrawStroke() {
        return this.boxStrokeWidthPx > -1 && this.boxStrokeColor != 0;
    }

    public void clearOnEditTextAttachedListeners() {
        this.editTextAttachedListeners.clear();
    }

    public void clearOnEndIconChangedListeners() {
        this.endLayout.clearOnEndIconChangedListeners();
    }

    private void closeCutout() {
        if(this.cutoutEnabled()) {
            ((CutoutDrawable)this.boxBackground).removeCutout();
        }
    }

    private void collapseHint(boolean z) {
        if(this.animator != null && this.animator.isRunning()) {
            this.animator.cancel();
        }
        if(!z || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(1.0f);
        }
        else {
            this.animateToExpansionFraction(1.0f);
        }
        this.hintExpanded = false;
        if(this.cutoutEnabled()) {
            this.openCutout();
        }
        this.updatePlaceholderText();
        this.startLayout.onHintStateChanged(false);
        this.endLayout.onHintStateChanged(false);
    }

    private Fade createPlaceholderFadeTransition() {
        Fade fade0 = new Fade();
        fade0.setDuration(((long)MotionUtils.resolveThemeDuration(this.getContext(), attr.motionDurationShort2, 87)));
        fade0.setInterpolator(MotionUtils.resolveThemeInterpolator(this.getContext(), attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR));
        return fade0;
    }

    // 去混淆评级： 低(30)
    private boolean cutoutEnabled() {
        return this.hintEnabled && !TextUtils.isEmpty(this.hint) && this.boxBackground instanceof CutoutDrawable;
    }

    // 去混淆评级： 低(20)
    boolean cutoutIsOpen() {
        return this.cutoutEnabled() && ((CutoutDrawable)this.boxBackground).hasCutout();
    }

    private void dispatchOnEditTextAttached() {
        for(Object object0: this.editTextAttachedListeners) {
            ((OnEditTextAttachedListener)object0).onEditTextAttached(this);
        }
    }

    @Override  // android.view.ViewGroup
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure0, int v) {
        EditText editText0 = this.editText;
        if(editText0 == null) {
            super.dispatchProvideAutofillStructure(viewStructure0, v);
            return;
        }
        if(this.originalHint != null) {
            boolean z = this.isProvidingHint;
            this.isProvidingHint = false;
            CharSequence charSequence0 = editText0.getHint();
            this.editText.setHint(this.originalHint);
            try {
                super.dispatchProvideAutofillStructure(viewStructure0, v);
            }
            finally {
                this.editText.setHint(charSequence0);
                this.isProvidingHint = z;
            }
            return;
        }
        LinkFollowing..ExternalSyntheticApiModelOutline0.m(viewStructure0, LinkFollowing..ExternalSyntheticApiModelOutline0.m(this));
        LinkFollowing..ExternalSyntheticApiModelOutline0.m(this, viewStructure0, v);
        LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(this, viewStructure0, v);
        LinkFollowing..ExternalSyntheticApiModelOutline0.m(viewStructure0, this.inputFrame.getChildCount());
        for(int v1 = 0; v1 < this.inputFrame.getChildCount(); ++v1) {
            View view0 = this.inputFrame.getChildAt(v1);
            ViewStructure viewStructure1 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(viewStructure0, v1);
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(view0, viewStructure1, v);
            if(view0 == this.editText) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(viewStructure1, this.getHint());
            }
        }
    }

    @Override  // android.view.ViewGroup
    protected void dispatchRestoreInstanceState(SparseArray sparseArray0) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray0);
        this.restoringSavedState = false;
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
        this.drawHint(canvas0);
        this.drawBoxUnderline(canvas0);
    }

    private void drawBoxUnderline(Canvas canvas0) {
        if(this.boxUnderlineFocused != null) {
            MaterialShapeDrawable materialShapeDrawable0 = this.boxUnderlineDefault;
            if(materialShapeDrawable0 != null) {
                materialShapeDrawable0.draw(canvas0);
                if(this.editText.isFocused()) {
                    Rect rect0 = this.boxUnderlineFocused.getBounds();
                    Rect rect1 = this.boxUnderlineDefault.getBounds();
                    float f = this.collapsingTextHelper.getExpansionFraction();
                    int v = rect1.centerX();
                    rect0.left = AnimationUtils.lerp(v, rect1.left, f);
                    rect0.right = AnimationUtils.lerp(v, rect1.right, f);
                    this.boxUnderlineFocused.draw(canvas0);
                }
            }
        }
    }

    private void drawHint(Canvas canvas0) {
        if(this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas0);
        }
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        if(this.inDrawableStateChanged) {
            return;
        }
        boolean z = true;
        this.inDrawableStateChanged = true;
        super.drawableStateChanged();
        int[] arr_v = this.getDrawableState();
        boolean z1 = this.collapsingTextHelper == null ? false : this.collapsingTextHelper.setState(arr_v);
        if(this.editText != null) {
            if(!ViewCompat.isLaidOut(this) || !this.isEnabled()) {
                z = false;
            }
            this.updateLabelState(z);
        }
        this.updateEditTextBackground();
        this.updateTextInputBoxState();
        if(z1) {
            this.invalidate();
        }
        this.inDrawableStateChanged = false;
    }

    private void expandHint(boolean z) {
        if(this.animator != null && this.animator.isRunning()) {
            this.animator.cancel();
        }
        if(!z || !this.hintAnimationEnabled) {
            this.collapsingTextHelper.setExpansionFraction(0.0f);
        }
        else {
            this.animateToExpansionFraction(0.0f);
        }
        if(this.cutoutEnabled() && ((CutoutDrawable)this.boxBackground).hasCutout()) {
            this.closeCutout();
        }
        this.hintExpanded = true;
        this.hidePlaceholderText();
        this.startLayout.onHintStateChanged(true);
        this.endLayout.onHintStateChanged(true);
    }

    @Override  // android.widget.LinearLayout
    public int getBaseline() {
        EditText editText0 = this.editText;
        return editText0 == null ? super.getBaseline() : editText0.getBaseline() + this.getPaddingTop() + this.calculateLabelMarginTop();
    }

    MaterialShapeDrawable getBoxBackground() {
        if(this.boxBackgroundMode != 1 && this.boxBackgroundMode != 2) {
            throw new IllegalStateException();
        }
        return this.boxBackground;
    }

    public int getBoxBackgroundColor() {
        return this.boxBackgroundColor;
    }

    public int getBoxBackgroundMode() {
        return this.boxBackgroundMode;
    }

    public int getBoxCollapsedPaddingTop() {
        return this.boxCollapsedPaddingTopPx;
    }

    // 去混淆评级： 低(20)
    public float getBoxCornerRadiusBottomEnd() {
        return ViewUtils.isLayoutRtl(this) ? this.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(this.tmpRectF) : this.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(this.tmpRectF);
    }

    // 去混淆评级： 低(20)
    public float getBoxCornerRadiusBottomStart() {
        return ViewUtils.isLayoutRtl(this) ? this.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(this.tmpRectF) : this.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(this.tmpRectF);
    }

    // 去混淆评级： 低(20)
    public float getBoxCornerRadiusTopEnd() {
        return ViewUtils.isLayoutRtl(this) ? this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.tmpRectF) : this.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(this.tmpRectF);
    }

    // 去混淆评级： 低(20)
    public float getBoxCornerRadiusTopStart() {
        return ViewUtils.isLayoutRtl(this) ? this.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(this.tmpRectF) : this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.tmpRectF);
    }

    public int getBoxStrokeColor() {
        return this.focusedStrokeColor;
    }

    public ColorStateList getBoxStrokeErrorColor() {
        return this.strokeErrorColor;
    }

    public int getBoxStrokeWidth() {
        return this.boxStrokeWidthDefaultPx;
    }

    public int getBoxStrokeWidthFocused() {
        return this.boxStrokeWidthFocusedPx;
    }

    public int getCounterMaxLength() {
        return this.counterMaxLength;
    }

    CharSequence getCounterOverflowDescription() {
        if(this.counterEnabled && this.counterOverflowed) {
            return this.counterView == null ? null : this.counterView.getContentDescription();
        }
        return null;
    }

    public ColorStateList getCounterOverflowTextColor() {
        return this.counterOverflowTextColor;
    }

    public ColorStateList getCounterTextColor() {
        return this.counterTextColor;
    }

    public ColorStateList getCursorColor() {
        return this.cursorColor;
    }

    public ColorStateList getCursorErrorColor() {
        return this.cursorErrorColor;
    }

    public ColorStateList getDefaultHintTextColor() {
        return this.defaultHintTextColor;
    }

    private MaterialShapeDrawable getDropDownMaterialShapeDrawable(boolean z) {
        float f = (float)this.getResources().getDimensionPixelOffset(dimen.mtrl_shape_corner_size_small_component);
        float f1 = this.editText instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView)this.editText).getPopupElevation() : ((float)this.getResources().getDimensionPixelOffset(dimen.m3_comp_outlined_autocomplete_menu_container_elevation));
        int v = this.getResources().getDimensionPixelOffset(dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        ShapeAppearanceModel shapeAppearanceModel0 = ShapeAppearanceModel.builder().setTopLeftCornerSize((z ? f : 0.0f)).setTopRightCornerSize((z ? f : 0.0f)).setBottomLeftCornerSize(f).setBottomRightCornerSize(f).build();
        ColorStateList colorStateList0 = this.editText instanceof MaterialAutoCompleteTextView ? ((MaterialAutoCompleteTextView)this.editText).getDropDownBackgroundTintList() : null;
        MaterialShapeDrawable materialShapeDrawable0 = MaterialShapeDrawable.createWithElevationOverlay(this.getContext(), f1, colorStateList0);
        materialShapeDrawable0.setShapeAppearanceModel(shapeAppearanceModel0);
        materialShapeDrawable0.setPadding(0, v, 0, v);
        return materialShapeDrawable0;
    }

    public EditText getEditText() {
        return this.editText;
    }

    private Drawable getEditTextBoxBackground() {
        if(this.editText instanceof AutoCompleteTextView && !EditTextUtils.isEditable(this.editText)) {
            int v = MaterialColors.getColor(this.editText, attr.colorControlHighlight);
            int v1 = this.boxBackgroundMode;
            if(v1 == 2) {
                return TextInputLayout.getOutlinedBoxBackgroundWithRipple(this.getContext(), this.boxBackground, v, TextInputLayout.EDIT_TEXT_BACKGROUND_RIPPLE_STATE);
            }
            return v1 == 1 ? TextInputLayout.getFilledBoxBackgroundWithRipple(this.boxBackground, this.boxBackgroundColor, v, TextInputLayout.EDIT_TEXT_BACKGROUND_RIPPLE_STATE) : null;
        }
        return this.boxBackground;
    }

    public CharSequence getEndIconContentDescription() {
        return this.endLayout.getEndIconContentDescription();
    }

    public Drawable getEndIconDrawable() {
        return this.endLayout.getEndIconDrawable();
    }

    public int getEndIconMinSize() {
        return this.endLayout.getEndIconMinSize();
    }

    public int getEndIconMode() {
        return this.endLayout.getEndIconMode();
    }

    public ImageView.ScaleType getEndIconScaleType() {
        return this.endLayout.getEndIconScaleType();
    }

    CheckableImageButton getEndIconView() {
        return this.endLayout.getEndIconView();
    }

    // 去混淆评级： 低(20)
    public CharSequence getError() {
        return this.indicatorViewController.isErrorEnabled() ? this.indicatorViewController.getErrorText() : null;
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.indicatorViewController.getErrorAccessibilityLiveRegion();
    }

    public CharSequence getErrorContentDescription() {
        return this.indicatorViewController.getErrorContentDescription();
    }

    public int getErrorCurrentTextColors() {
        return this.indicatorViewController.getErrorViewCurrentTextColor();
    }

    public Drawable getErrorIconDrawable() {
        return this.endLayout.getErrorIconDrawable();
    }

    private static Drawable getFilledBoxBackgroundWithRipple(MaterialShapeDrawable materialShapeDrawable0, int v, int v1, int[][] arr2_v) {
        return new RippleDrawable(new ColorStateList(arr2_v, new int[]{MaterialColors.layer(v1, v, 0.1f), v}), materialShapeDrawable0, materialShapeDrawable0);
    }

    // 去混淆评级： 低(20)
    public CharSequence getHelperText() {
        return this.indicatorViewController.isHelperTextEnabled() ? this.indicatorViewController.getHelperText() : null;
    }

    public int getHelperTextCurrentTextColor() {
        return this.indicatorViewController.getHelperTextViewCurrentTextColor();
    }

    // 去混淆评级： 低(20)
    public CharSequence getHint() {
        return this.hintEnabled ? this.hint : null;
    }

    final float getHintCollapsedTextHeight() {
        return this.collapsingTextHelper.getCollapsedTextHeight();
    }

    final int getHintCurrentCollapsedTextColor() {
        return this.collapsingTextHelper.getCurrentCollapsedTextColor();
    }

    public ColorStateList getHintTextColor() {
        return this.focusedTextColor;
    }

    private int getLabelLeftBoundAlignedWithPrefixAndSuffix(int v, boolean z) {
        if(!z && this.getPrefixText() != null) {
            return v + this.startLayout.getPrefixTextStartOffset();
        }
        return !z || this.getSuffixText() == null ? v + this.editText.getCompoundPaddingLeft() : v + this.endLayout.getSuffixTextEndOffset();
    }

    private int getLabelRightBoundAlignedWithPrefixAndSuffix(int v, boolean z) {
        if(!z && this.getSuffixText() != null) {
            return v - this.endLayout.getSuffixTextEndOffset();
        }
        return !z || this.getPrefixText() == null ? v - this.editText.getCompoundPaddingRight() : v - this.startLayout.getPrefixTextStartOffset();
    }

    public LengthCounter getLengthCounter() {
        return this.lengthCounter;
    }

    public int getMaxEms() {
        return this.maxEms;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getMinEms() {
        return this.minEms;
    }

    public int getMinWidth() {
        return this.minWidth;
    }

    private Drawable getOrCreateFilledDropDownMenuBackground() {
        if(this.filledDropDownMenuBackground == null) {
            StateListDrawable stateListDrawable0 = new StateListDrawable();
            this.filledDropDownMenuBackground = stateListDrawable0;
            Drawable drawable0 = this.getOrCreateOutlinedDropDownMenuBackground();
            stateListDrawable0.addState(new int[]{0x10100AA}, drawable0);
            StateListDrawable stateListDrawable1 = this.filledDropDownMenuBackground;
            MaterialShapeDrawable materialShapeDrawable0 = this.getDropDownMaterialShapeDrawable(false);
            stateListDrawable1.addState(new int[0], materialShapeDrawable0);
        }
        return this.filledDropDownMenuBackground;
    }

    private Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if(this.outlinedDropDownMenuBackground == null) {
            this.outlinedDropDownMenuBackground = this.getDropDownMaterialShapeDrawable(true);
        }
        return this.outlinedDropDownMenuBackground;
    }

    private static Drawable getOutlinedBoxBackgroundWithRipple(Context context0, MaterialShapeDrawable materialShapeDrawable0, int v, int[][] arr2_v) {
        int v1 = MaterialColors.getColor(context0, attr.colorSurface, "TextInputLayout");
        MaterialShapeDrawable materialShapeDrawable1 = new MaterialShapeDrawable(materialShapeDrawable0.getShapeAppearanceModel());
        int v2 = MaterialColors.layer(v, v1, 0.1f);
        materialShapeDrawable1.setFillColor(new ColorStateList(arr2_v, new int[]{v2, 0}));
        materialShapeDrawable1.setTint(v1);
        ColorStateList colorStateList0 = new ColorStateList(arr2_v, new int[]{v2, v1});
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable0.getShapeAppearanceModel());
        materialShapeDrawable2.setTint(-1);
        return new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList0, materialShapeDrawable1, materialShapeDrawable2), materialShapeDrawable0});
    }

    @Deprecated
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endLayout.getPasswordVisibilityToggleContentDescription();
    }

    @Deprecated
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endLayout.getPasswordVisibilityToggleDrawable();
    }

    // 去混淆评级： 低(20)
    public CharSequence getPlaceholderText() {
        return this.placeholderEnabled ? this.placeholderText : null;
    }

    public int getPlaceholderTextAppearance() {
        return this.placeholderTextAppearance;
    }

    public ColorStateList getPlaceholderTextColor() {
        return this.placeholderTextColor;
    }

    public CharSequence getPrefixText() {
        return this.startLayout.getPrefixText();
    }

    public ColorStateList getPrefixTextColor() {
        return this.startLayout.getPrefixTextColor();
    }

    public TextView getPrefixTextView() {
        return this.startLayout.getPrefixTextView();
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    public CharSequence getStartIconContentDescription() {
        return this.startLayout.getStartIconContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.startLayout.getStartIconDrawable();
    }

    public int getStartIconMinSize() {
        return this.startLayout.getStartIconMinSize();
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.startLayout.getStartIconScaleType();
    }

    public CharSequence getSuffixText() {
        return this.endLayout.getSuffixText();
    }

    public ColorStateList getSuffixTextColor() {
        return this.endLayout.getSuffixTextColor();
    }

    public TextView getSuffixTextView() {
        return this.endLayout.getSuffixTextView();
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    private void hidePlaceholderText() {
        TextView textView0 = this.placeholderTextView;
        if(textView0 != null && this.placeholderEnabled) {
            textView0.setText(null);
            TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeOut);
            this.placeholderTextView.setVisibility(4);
        }
    }

    public boolean isCounterEnabled() {
        return this.counterEnabled;
    }

    public boolean isEndIconCheckable() {
        return this.endLayout.isEndIconCheckable();
    }

    public boolean isEndIconVisible() {
        return this.endLayout.isEndIconVisible();
    }

    public boolean isErrorEnabled() {
        return this.indicatorViewController.isErrorEnabled();
    }

    public boolean isExpandedHintEnabled() {
        return this.expandedHintEnabled;
    }

    final boolean isHelperTextDisplayed() {
        return this.indicatorViewController.helperTextIsDisplayed();
    }

    public boolean isHelperTextEnabled() {
        return this.indicatorViewController.isHelperTextEnabled();
    }

    public boolean isHintAnimationEnabled() {
        return this.hintAnimationEnabled;
    }

    public boolean isHintEnabled() {
        return this.hintEnabled;
    }

    final boolean isHintExpanded() {
        return this.hintExpanded;
    }

    // 去混淆评级： 低(30)
    private boolean isOnError() {
        return this.shouldShowError() || this.counterView != null && this.counterOverflowed;
    }

    @Deprecated
    public boolean isPasswordVisibilityToggleEnabled() {
        return this.endLayout.isPasswordVisibilityToggleEnabled();
    }

    public boolean isProvidingHint() {
        return this.isProvidingHint;
    }

    private boolean isSingleLineFilledTextField() {
        return this.boxBackgroundMode == 1 && this.editText.getMinLines() <= 1;
    }

    public boolean isStartIconCheckable() {
        return this.startLayout.isStartIconCheckable();
    }

    public boolean isStartIconVisible() {
        return this.startLayout.isStartIconVisible();
    }

    // 检测为 Lambda 实现
    static int lambda$new$0(Editable editable0) [...]

    // 检测为 Lambda 实现
    void lambda$onGlobalLayout$1$com-google-android-material-textfield-TextInputLayout() [...]

    private void onApplyBoxBackgroundMode() {
        this.assignBoxBackgroundByMode();
        this.updateEditTextBoxBackgroundIfNeeded();
        this.updateTextInputBoxState();
        this.updateBoxCollapsedPaddingTop();
        this.adjustFilledEditTextPaddingForLargeFont();
        if(this.boxBackgroundMode != 0) {
            this.updateInputLayoutMargins();
        }
        this.setDropDownMenuBackgroundIfNeeded();
    }

    @Override  // android.view.View
    protected void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(configuration0);
    }

    @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
    public void onGlobalLayout() {
        this.endLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.globalLayoutListenerAdded = false;
        if(!this.updateEditTextHeightBasedOnIcon() && !this.updateDummyDrawables()) {
            return;
        }
        this.editText.post(() -> this.editText.requestLayout());
    }

    @Override  // android.widget.LinearLayout
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        EditText editText0 = this.editText;
        if(editText0 != null) {
            Rect rect0 = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText0, rect0);
            this.updateBoxUnderlineBounds(rect0);
            if(this.hintEnabled) {
                float f = this.editText.getTextSize();
                this.collapsingTextHelper.setExpandedTextSize(f);
                int v4 = this.editText.getGravity();
                this.collapsingTextHelper.setCollapsedTextGravity(v4 & 0xFFFFFF8F | 0x30);
                this.collapsingTextHelper.setExpandedTextGravity(v4);
                Rect rect1 = this.calculateCollapsedTextBounds(rect0);
                this.collapsingTextHelper.setCollapsedBounds(rect1);
                Rect rect2 = this.calculateExpandedTextBounds(rect0);
                this.collapsingTextHelper.setExpandedBounds(rect2);
                this.collapsingTextHelper.recalculate();
                if(this.cutoutEnabled() && !this.hintExpanded) {
                    this.openCutout();
                }
            }
        }
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        if(!this.globalLayoutListenerAdded) {
            this.endLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.globalLayoutListenerAdded = true;
        }
        this.updatePlaceholderMeasurementsBasedOnEditText();
        this.endLayout.updateSuffixTextViewPadding();
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.setError(((SavedState)parcelable0).error);
        if(((SavedState)parcelable0).isEndIconChecked) {
            this.post(new Runnable() {
                @Override
                public void run() {
                    TextInputLayout.this.endLayout.checkEndIcon();
                }
            });
        }
        this.requestLayout();
    }

    @Override  // android.widget.LinearLayout
    public void onRtlPropertiesChanged(int v) {
        super.onRtlPropertiesChanged(v);
        if(v == 1 != this.areCornerRadiiRtl) {
            float f = this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.tmpRectF);
            float f1 = this.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(this.tmpRectF);
            float f2 = this.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(this.tmpRectF);
            float f3 = this.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(this.tmpRectF);
            CornerTreatment cornerTreatment0 = this.shapeAppearanceModel.getTopLeftCorner();
            CornerTreatment cornerTreatment1 = this.shapeAppearanceModel.getTopRightCorner();
            CornerTreatment cornerTreatment2 = this.shapeAppearanceModel.getBottomLeftCorner();
            CornerTreatment cornerTreatment3 = this.shapeAppearanceModel.getBottomRightCorner();
            ShapeAppearanceModel shapeAppearanceModel0 = ShapeAppearanceModel.builder().setTopLeftCorner(cornerTreatment1).setTopRightCorner(cornerTreatment0).setBottomLeftCorner(cornerTreatment3).setBottomRightCorner(cornerTreatment2).setTopLeftCornerSize(f1).setTopRightCornerSize(f).setBottomLeftCornerSize(f3).setBottomRightCornerSize(f2).build();
            this.areCornerRadiiRtl = v == 1;
            this.setShapeAppearanceModel(shapeAppearanceModel0);
        }
    }

    @Override  // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        if(this.shouldShowError()) {
            parcelable0.error = this.getError();
        }
        parcelable0.isEndIconChecked = this.endLayout.isEndIconChecked();
        return parcelable0;
    }

    private void openCutout() {
        if(this.cutoutEnabled()) {
            RectF rectF0 = this.tmpRectF;
            int v = this.editText.getWidth();
            int v1 = this.editText.getGravity();
            this.collapsingTextHelper.getCollapsedTextActualBounds(rectF0, v, v1);
            if(rectF0.width() > 0.0f && rectF0.height() > 0.0f) {
                this.applyCutoutPadding(rectF0);
                rectF0.offset(((float)(-this.getPaddingLeft())), ((float)(-this.getPaddingTop())) - rectF0.height() / 2.0f + ((float)this.boxStrokeWidthPx));
                ((CutoutDrawable)this.boxBackground).setCutout(rectF0);
            }
        }
    }

    @Deprecated
    public void passwordVisibilityToggleRequested(boolean z) {
        this.endLayout.togglePasswordVisibilityToggle(z);
    }

    private void recalculateCutout() {
        if(this.cutoutEnabled() && !this.hintExpanded) {
            this.closeCutout();
            this.openCutout();
        }
    }

    private static void recursiveSetEnabled(ViewGroup viewGroup0, boolean z) {
        int v = viewGroup0.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = viewGroup0.getChildAt(v1);
            view0.setEnabled(z);
            if(view0 instanceof ViewGroup) {
                TextInputLayout.recursiveSetEnabled(((ViewGroup)view0), z);
            }
        }
    }

    public void refreshEndIconDrawableState() {
        this.endLayout.refreshEndIconDrawableState();
    }

    public void refreshErrorIconDrawableState() {
        this.endLayout.refreshErrorIconDrawableState();
    }

    public void refreshStartIconDrawableState() {
        this.startLayout.refreshStartIconDrawableState();
    }

    public void removeOnEditTextAttachedListener(OnEditTextAttachedListener textInputLayout$OnEditTextAttachedListener0) {
        this.editTextAttachedListeners.remove(textInputLayout$OnEditTextAttachedListener0);
    }

    public void removeOnEndIconChangedListener(OnEndIconChangedListener textInputLayout$OnEndIconChangedListener0) {
        this.endLayout.removeOnEndIconChangedListener(textInputLayout$OnEndIconChangedListener0);
    }

    private void removePlaceholderTextView() {
        TextView textView0 = this.placeholderTextView;
        if(textView0 != null) {
            textView0.setVisibility(8);
        }
    }

    public void setBoxBackgroundColor(int v) {
        if(this.boxBackgroundColor != v) {
            this.boxBackgroundColor = v;
            this.defaultFilledBackgroundColor = v;
            this.focusedFilledBackgroundColor = v;
            this.hoveredFilledBackgroundColor = v;
            this.applyBoxAttributes();
        }
    }

    public void setBoxBackgroundColorResource(int v) {
        this.setBoxBackgroundColor(ContextCompat.getColor(this.getContext(), v));
    }

    public void setBoxBackgroundColorStateList(ColorStateList colorStateList0) {
        int v = colorStateList0.getDefaultColor();
        this.defaultFilledBackgroundColor = v;
        this.boxBackgroundColor = v;
        this.disabledFilledBackgroundColor = colorStateList0.getColorForState(new int[]{0xFEFEFF62}, -1);
        this.focusedFilledBackgroundColor = colorStateList0.getColorForState(new int[]{0x101009C, 0x101009E}, -1);
        this.hoveredFilledBackgroundColor = colorStateList0.getColorForState(new int[]{0x1010367, 0x101009E}, -1);
        this.applyBoxAttributes();
    }

    public void setBoxBackgroundMode(int v) {
        if(v != this.boxBackgroundMode) {
            this.boxBackgroundMode = v;
            if(this.editText != null) {
                this.onApplyBoxBackgroundMode();
            }
        }
    }

    public void setBoxCollapsedPaddingTop(int v) {
        this.boxCollapsedPaddingTopPx = v;
    }

    public void setBoxCornerFamily(int v) {
        this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setTopLeftCorner(v, this.shapeAppearanceModel.getTopLeftCornerSize()).setTopRightCorner(v, this.shapeAppearanceModel.getTopRightCornerSize()).setBottomLeftCorner(v, this.shapeAppearanceModel.getBottomLeftCornerSize()).setBottomRightCorner(v, this.shapeAppearanceModel.getBottomRightCornerSize()).build();
        this.applyBoxAttributes();
    }

    public void setBoxCornerRadii(float f, float f1, float f2, float f3) {
        boolean z = ViewUtils.isLayoutRtl(this);
        this.areCornerRadiiRtl = z;
        float f4 = z ? f1 : f;
        if(!z) {
            f = f1;
        }
        float f5 = z ? f3 : f2;
        if(!z) {
            f2 = f3;
        }
        if(this.boxBackground != null && this.boxBackground.getTopLeftCornerResolvedSize() == f4 && this.boxBackground.getTopRightCornerResolvedSize() == f && this.boxBackground.getBottomLeftCornerResolvedSize() == f5 && this.boxBackground.getBottomRightCornerResolvedSize() == f2) {
            return;
        }
        this.shapeAppearanceModel = this.shapeAppearanceModel.toBuilder().setTopLeftCornerSize(f4).setTopRightCornerSize(f).setBottomLeftCornerSize(f5).setBottomRightCornerSize(f2).build();
        this.applyBoxAttributes();
    }

    public void setBoxCornerRadiiResources(int v, int v1, int v2, int v3) {
        this.setBoxCornerRadii(this.getContext().getResources().getDimension(v), this.getContext().getResources().getDimension(v1), this.getContext().getResources().getDimension(v3), this.getContext().getResources().getDimension(v2));
    }

    public void setBoxStrokeColor(int v) {
        if(this.focusedStrokeColor != v) {
            this.focusedStrokeColor = v;
            this.updateTextInputBoxState();
        }
    }

    public void setBoxStrokeColorStateList(ColorStateList colorStateList0) {
        if(colorStateList0.isStateful()) {
            this.defaultStrokeColor = colorStateList0.getDefaultColor();
            this.disabledColor = colorStateList0.getColorForState(new int[]{0xFEFEFF62}, -1);
            this.hoveredStrokeColor = colorStateList0.getColorForState(new int[]{0x1010367, 0x101009E}, -1);
            this.focusedStrokeColor = colorStateList0.getColorForState(new int[]{0x101009C, 0x101009E}, -1);
        }
        else if(this.focusedStrokeColor != colorStateList0.getDefaultColor()) {
            this.focusedStrokeColor = colorStateList0.getDefaultColor();
        }
        this.updateTextInputBoxState();
    }

    public void setBoxStrokeErrorColor(ColorStateList colorStateList0) {
        if(this.strokeErrorColor != colorStateList0) {
            this.strokeErrorColor = colorStateList0;
            this.updateTextInputBoxState();
        }
    }

    public void setBoxStrokeWidth(int v) {
        this.boxStrokeWidthDefaultPx = v;
        this.updateTextInputBoxState();
    }

    public void setBoxStrokeWidthFocused(int v) {
        this.boxStrokeWidthFocusedPx = v;
        this.updateTextInputBoxState();
    }

    public void setBoxStrokeWidthFocusedResource(int v) {
        this.setBoxStrokeWidthFocused(this.getResources().getDimensionPixelSize(v));
    }

    public void setBoxStrokeWidthResource(int v) {
        this.setBoxStrokeWidth(this.getResources().getDimensionPixelSize(v));
    }

    public void setCounterEnabled(boolean z) {
        if(this.counterEnabled != z) {
            if(z) {
                AppCompatTextView appCompatTextView0 = new AppCompatTextView(this.getContext());
                this.counterView = appCompatTextView0;
                appCompatTextView0.setId(id.textinput_counter);
                Typeface typeface0 = this.typeface;
                if(typeface0 != null) {
                    this.counterView.setTypeface(typeface0);
                }
                this.counterView.setMaxLines(1);
                this.indicatorViewController.addIndicator(this.counterView, 2);
                MarginLayoutParamsCompat.setMarginStart(((ViewGroup.MarginLayoutParams)this.counterView.getLayoutParams()), this.getResources().getDimensionPixelOffset(dimen.mtrl_textinput_counter_margin_start));
                this.updateCounterTextAppearanceAndColor();
                this.updateCounter();
            }
            else {
                this.indicatorViewController.removeIndicator(this.counterView, 2);
                this.counterView = null;
            }
            this.counterEnabled = z;
        }
    }

    public void setCounterMaxLength(int v) {
        if(this.counterMaxLength != v) {
            this.counterMaxLength = v > 0 ? v : -1;
            if(this.counterEnabled) {
                this.updateCounter();
            }
        }
    }

    public void setCounterOverflowTextAppearance(int v) {
        if(this.counterOverflowTextAppearance != v) {
            this.counterOverflowTextAppearance = v;
            this.updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterOverflowTextColor(ColorStateList colorStateList0) {
        if(this.counterOverflowTextColor != colorStateList0) {
            this.counterOverflowTextColor = colorStateList0;
            this.updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextAppearance(int v) {
        if(this.counterTextAppearance != v) {
            this.counterTextAppearance = v;
            this.updateCounterTextAppearanceAndColor();
        }
    }

    public void setCounterTextColor(ColorStateList colorStateList0) {
        if(this.counterTextColor != colorStateList0) {
            this.counterTextColor = colorStateList0;
            this.updateCounterTextAppearanceAndColor();
        }
    }

    public void setCursorColor(ColorStateList colorStateList0) {
        if(this.cursorColor != colorStateList0) {
            this.cursorColor = colorStateList0;
            this.updateCursorColor();
        }
    }

    public void setCursorErrorColor(ColorStateList colorStateList0) {
        if(this.cursorErrorColor != colorStateList0) {
            this.cursorErrorColor = colorStateList0;
            if(this.isOnError()) {
                this.updateCursorColor();
            }
        }
    }

    public void setDefaultHintTextColor(ColorStateList colorStateList0) {
        this.defaultHintTextColor = colorStateList0;
        this.focusedTextColor = colorStateList0;
        if(this.editText != null) {
            this.updateLabelState(false);
        }
    }

    private void setDropDownMenuBackgroundIfNeeded() {
        EditText editText0 = this.editText;
        if(editText0 instanceof AutoCompleteTextView && ((AutoCompleteTextView)editText0).getDropDownBackground() == null) {
            int v = this.boxBackgroundMode;
            if(v == 2) {
                ((AutoCompleteTextView)editText0).setDropDownBackgroundDrawable(this.getOrCreateOutlinedDropDownMenuBackground());
                return;
            }
            if(v == 1) {
                ((AutoCompleteTextView)editText0).setDropDownBackgroundDrawable(this.getOrCreateFilledDropDownMenuBackground());
            }
        }
    }

    private void setEditText(EditText editText0) {
        if(this.editText != null) {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        if(this.getEndIconMode() != 3 && !(editText0 instanceof TextInputEditText)) {
            Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
        }
        this.editText = editText0;
        int v = this.minEms;
        if(v == -1) {
            this.setMinWidth(this.minWidth);
        }
        else {
            this.setMinEms(v);
        }
        int v1 = this.maxEms;
        if(v1 == -1) {
            this.setMaxWidth(this.maxWidth);
        }
        else {
            this.setMaxEms(v1);
        }
        this.boxBackgroundApplied = false;
        this.onApplyBoxBackgroundMode();
        this.setTextInputAccessibilityDelegate(new AccessibilityDelegate(this));
        Typeface typeface0 = this.editText.getTypeface();
        this.collapsingTextHelper.setTypefaces(typeface0);
        float f = this.editText.getTextSize();
        this.collapsingTextHelper.setExpandedTextSize(f);
        float f1 = this.editText.getLetterSpacing();
        this.collapsingTextHelper.setExpandedLetterSpacing(f1);
        int v2 = this.editText.getGravity();
        this.collapsingTextHelper.setCollapsedTextGravity(v2 & 0xFFFFFF8F | 0x30);
        this.collapsingTextHelper.setExpandedTextGravity(v2);
        this.originalEditTextMinimumHeight = ViewCompat.getMinimumHeight(editText0);
        this.editText.addTextChangedListener(new TextWatcher() {
            int previousLineCount;

            {
                EditText editText0 = editText0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.previousLineCount = editText0.getLineCount();
            }

            @Override  // android.text.TextWatcher
            public void afterTextChanged(Editable editable0) {
                TextInputLayout.this.updateLabelState(!TextInputLayout.this.restoringSavedState);
                if(TextInputLayout.this.counterEnabled) {
                    TextInputLayout.this.updateCounter(editable0);
                }
                if(TextInputLayout.this.placeholderEnabled) {
                    TextInputLayout.this.updatePlaceholderText(editable0);
                }
                int v = editText0.getLineCount();
                int v1 = this.previousLineCount;
                if(v != v1) {
                    if(v < v1 && ViewCompat.getMinimumHeight(editText0) != TextInputLayout.this.originalEditTextMinimumHeight) {
                        editText0.setMinimumHeight(TextInputLayout.this.originalEditTextMinimumHeight);
                    }
                    this.previousLineCount = v;
                }
            }

            @Override  // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }

            @Override  // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence0, int v, int v1, int v2) {
            }
        });
        if(this.defaultHintTextColor == null) {
            this.defaultHintTextColor = this.editText.getHintTextColors();
        }
        if(this.hintEnabled) {
            if(TextUtils.isEmpty(this.hint)) {
                CharSequence charSequence0 = this.editText.getHint();
                this.originalHint = charSequence0;
                this.setHint(charSequence0);
                this.editText.setHint(null);
            }
            this.isProvidingHint = true;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            this.updateCursorColor();
        }
        if(this.counterView != null) {
            this.updateCounter(this.editText.getText());
        }
        this.updateEditTextBackground();
        this.indicatorViewController.adjustIndicatorPadding();
        this.startLayout.bringToFront();
        this.endLayout.bringToFront();
        this.dispatchOnEditTextAttached();
        this.endLayout.updateSuffixTextViewPadding();
        if(!this.isEnabled()) {
            editText0.setEnabled(false);
        }
        this.updateLabelState(false, true);
    }

    @Override  // android.view.View
    public void setEnabled(boolean z) {
        TextInputLayout.recursiveSetEnabled(this, z);
        super.setEnabled(z);
    }

    public void setEndIconActivated(boolean z) {
        this.endLayout.setEndIconActivated(z);
    }

    public void setEndIconCheckable(boolean z) {
        this.endLayout.setEndIconCheckable(z);
    }

    public void setEndIconContentDescription(int v) {
        this.endLayout.setEndIconContentDescription(v);
    }

    public void setEndIconContentDescription(CharSequence charSequence0) {
        this.endLayout.setEndIconContentDescription(charSequence0);
    }

    public void setEndIconDrawable(int v) {
        this.endLayout.setEndIconDrawable(v);
    }

    public void setEndIconDrawable(Drawable drawable0) {
        this.endLayout.setEndIconDrawable(drawable0);
    }

    public void setEndIconMinSize(int v) {
        this.endLayout.setEndIconMinSize(v);
    }

    public void setEndIconMode(int v) {
        this.endLayout.setEndIconMode(v);
    }

    public void setEndIconOnClickListener(View.OnClickListener view$OnClickListener0) {
        this.endLayout.setEndIconOnClickListener(view$OnClickListener0);
    }

    public void setEndIconOnLongClickListener(View.OnLongClickListener view$OnLongClickListener0) {
        this.endLayout.setEndIconOnLongClickListener(view$OnLongClickListener0);
    }

    public void setEndIconScaleType(ImageView.ScaleType imageView$ScaleType0) {
        this.endLayout.setEndIconScaleType(imageView$ScaleType0);
    }

    public void setEndIconTintList(ColorStateList colorStateList0) {
        this.endLayout.setEndIconTintList(colorStateList0);
    }

    public void setEndIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.endLayout.setEndIconTintMode(porterDuff$Mode0);
    }

    public void setEndIconVisible(boolean z) {
        this.endLayout.setEndIconVisible(z);
    }

    public void setError(CharSequence charSequence0) {
        if(!this.indicatorViewController.isErrorEnabled()) {
            if(TextUtils.isEmpty(charSequence0)) {
                return;
            }
            this.setErrorEnabled(true);
        }
        if(!TextUtils.isEmpty(charSequence0)) {
            this.indicatorViewController.showError(charSequence0);
            return;
        }
        this.indicatorViewController.hideError();
    }

    public void setErrorAccessibilityLiveRegion(int v) {
        this.indicatorViewController.setErrorAccessibilityLiveRegion(v);
    }

    public void setErrorContentDescription(CharSequence charSequence0) {
        this.indicatorViewController.setErrorContentDescription(charSequence0);
    }

    public void setErrorEnabled(boolean z) {
        this.indicatorViewController.setErrorEnabled(z);
    }

    public void setErrorIconDrawable(int v) {
        this.endLayout.setErrorIconDrawable(v);
    }

    public void setErrorIconDrawable(Drawable drawable0) {
        this.endLayout.setErrorIconDrawable(drawable0);
    }

    public void setErrorIconOnClickListener(View.OnClickListener view$OnClickListener0) {
        this.endLayout.setErrorIconOnClickListener(view$OnClickListener0);
    }

    public void setErrorIconOnLongClickListener(View.OnLongClickListener view$OnLongClickListener0) {
        this.endLayout.setErrorIconOnLongClickListener(view$OnLongClickListener0);
    }

    public void setErrorIconTintList(ColorStateList colorStateList0) {
        this.endLayout.setErrorIconTintList(colorStateList0);
    }

    public void setErrorIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.endLayout.setErrorIconTintMode(porterDuff$Mode0);
    }

    public void setErrorTextAppearance(int v) {
        this.indicatorViewController.setErrorTextAppearance(v);
    }

    public void setErrorTextColor(ColorStateList colorStateList0) {
        this.indicatorViewController.setErrorViewTextColor(colorStateList0);
    }

    public void setExpandedHintEnabled(boolean z) {
        if(this.expandedHintEnabled != z) {
            this.expandedHintEnabled = z;
            this.updateLabelState(false);
        }
    }

    public void setHelperText(CharSequence charSequence0) {
        if(TextUtils.isEmpty(charSequence0)) {
            if(this.isHelperTextEnabled()) {
                this.setHelperTextEnabled(false);
            }
            return;
        }
        if(!this.isHelperTextEnabled()) {
            this.setHelperTextEnabled(true);
        }
        this.indicatorViewController.showHelper(charSequence0);
    }

    public void setHelperTextColor(ColorStateList colorStateList0) {
        this.indicatorViewController.setHelperTextViewTextColor(colorStateList0);
    }

    public void setHelperTextEnabled(boolean z) {
        this.indicatorViewController.setHelperTextEnabled(z);
    }

    public void setHelperTextTextAppearance(int v) {
        this.indicatorViewController.setHelperTextAppearance(v);
    }

    public void setHint(int v) {
        this.setHint((v == 0 ? null : this.getResources().getText(v)));
    }

    public void setHint(CharSequence charSequence0) {
        if(this.hintEnabled) {
            this.setHintInternal(charSequence0);
            this.sendAccessibilityEvent(0x800);
        }
    }

    public void setHintAnimationEnabled(boolean z) {
        this.hintAnimationEnabled = z;
    }

    public void setHintEnabled(boolean z) {
        if(z != this.hintEnabled) {
            this.hintEnabled = z;
            if(z) {
                CharSequence charSequence0 = this.editText.getHint();
                if(!TextUtils.isEmpty(charSequence0)) {
                    if(TextUtils.isEmpty(this.hint)) {
                        this.setHint(charSequence0);
                    }
                    this.editText.setHint(null);
                }
                this.isProvidingHint = true;
            }
            else {
                this.isProvidingHint = false;
                if(!TextUtils.isEmpty(this.hint) && TextUtils.isEmpty(this.editText.getHint())) {
                    this.editText.setHint(this.hint);
                }
                this.setHintInternal(null);
            }
            if(this.editText != null) {
                this.updateInputLayoutMargins();
            }
        }
    }

    private void setHintInternal(CharSequence charSequence0) {
        if(!TextUtils.equals(charSequence0, this.hint)) {
            this.hint = charSequence0;
            this.collapsingTextHelper.setText(charSequence0);
            if(!this.hintExpanded) {
                this.openCutout();
            }
        }
    }

    public void setHintTextAppearance(int v) {
        this.collapsingTextHelper.setCollapsedTextAppearance(v);
        this.focusedTextColor = this.collapsingTextHelper.getCollapsedTextColor();
        if(this.editText != null) {
            this.updateLabelState(false);
            this.updateInputLayoutMargins();
        }
    }

    public void setHintTextColor(ColorStateList colorStateList0) {
        if(this.focusedTextColor != colorStateList0) {
            if(this.defaultHintTextColor == null) {
                this.collapsingTextHelper.setCollapsedTextColor(colorStateList0);
            }
            this.focusedTextColor = colorStateList0;
            if(this.editText != null) {
                this.updateLabelState(false);
            }
        }
    }

    public void setLengthCounter(LengthCounter textInputLayout$LengthCounter0) {
        this.lengthCounter = textInputLayout$LengthCounter0;
    }

    public void setMaxEms(int v) {
        this.maxEms = v;
        EditText editText0 = this.editText;
        if(editText0 != null && v != -1) {
            editText0.setMaxEms(v);
        }
    }

    public void setMaxWidth(int v) {
        this.maxWidth = v;
        EditText editText0 = this.editText;
        if(editText0 != null && v != -1) {
            editText0.setMaxWidth(v);
        }
    }

    public void setMaxWidthResource(int v) {
        this.setMaxWidth(this.getContext().getResources().getDimensionPixelSize(v));
    }

    public void setMinEms(int v) {
        this.minEms = v;
        EditText editText0 = this.editText;
        if(editText0 != null && v != -1) {
            editText0.setMinEms(v);
        }
    }

    public void setMinWidth(int v) {
        this.minWidth = v;
        EditText editText0 = this.editText;
        if(editText0 != null && v != -1) {
            editText0.setMinWidth(v);
        }
    }

    public void setMinWidthResource(int v) {
        this.setMinWidth(this.getContext().getResources().getDimensionPixelSize(v));
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(int v) {
        this.endLayout.setPasswordVisibilityToggleContentDescription(v);
    }

    @Deprecated
    public void setPasswordVisibilityToggleContentDescription(CharSequence charSequence0) {
        this.endLayout.setPasswordVisibilityToggleContentDescription(charSequence0);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(int v) {
        this.endLayout.setPasswordVisibilityToggleDrawable(v);
    }

    @Deprecated
    public void setPasswordVisibilityToggleDrawable(Drawable drawable0) {
        this.endLayout.setPasswordVisibilityToggleDrawable(drawable0);
    }

    @Deprecated
    public void setPasswordVisibilityToggleEnabled(boolean z) {
        this.endLayout.setPasswordVisibilityToggleEnabled(z);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintList(ColorStateList colorStateList0) {
        this.endLayout.setPasswordVisibilityToggleTintList(colorStateList0);
    }

    @Deprecated
    public void setPasswordVisibilityToggleTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.endLayout.setPasswordVisibilityToggleTintMode(porterDuff$Mode0);
    }

    public void setPlaceholderText(CharSequence charSequence0) {
        if(this.placeholderTextView == null) {
            AppCompatTextView appCompatTextView0 = new AppCompatTextView(this.getContext());
            this.placeholderTextView = appCompatTextView0;
            appCompatTextView0.setId(id.textinput_placeholder);
            ViewCompat.setImportantForAccessibility(this.placeholderTextView, 2);
            Fade fade0 = this.createPlaceholderFadeTransition();
            this.placeholderFadeIn = fade0;
            fade0.setStartDelay(67L);
            this.placeholderFadeOut = this.createPlaceholderFadeTransition();
            this.setPlaceholderTextAppearance(this.placeholderTextAppearance);
            this.setPlaceholderTextColor(this.placeholderTextColor);
        }
        if(TextUtils.isEmpty(charSequence0)) {
            this.setPlaceholderTextEnabled(false);
        }
        else {
            if(!this.placeholderEnabled) {
                this.setPlaceholderTextEnabled(true);
            }
            this.placeholderText = charSequence0;
        }
        this.updatePlaceholderText();
    }

    public void setPlaceholderTextAppearance(int v) {
        this.placeholderTextAppearance = v;
        TextView textView0 = this.placeholderTextView;
        if(textView0 != null) {
            TextViewCompat.setTextAppearance(textView0, v);
        }
    }

    public void setPlaceholderTextColor(ColorStateList colorStateList0) {
        if(this.placeholderTextColor != colorStateList0) {
            this.placeholderTextColor = colorStateList0;
            TextView textView0 = this.placeholderTextView;
            if(textView0 != null && colorStateList0 != null) {
                textView0.setTextColor(colorStateList0);
            }
        }
    }

    private void setPlaceholderTextEnabled(boolean z) {
        if(this.placeholderEnabled == z) {
            return;
        }
        if(z) {
            this.addPlaceholderTextView();
        }
        else {
            this.removePlaceholderTextView();
            this.placeholderTextView = null;
        }
        this.placeholderEnabled = z;
    }

    public void setPrefixText(CharSequence charSequence0) {
        this.startLayout.setPrefixText(charSequence0);
    }

    public void setPrefixTextAppearance(int v) {
        this.startLayout.setPrefixTextAppearance(v);
    }

    public void setPrefixTextColor(ColorStateList colorStateList0) {
        this.startLayout.setPrefixTextColor(colorStateList0);
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        if(this.boxBackground != null && this.boxBackground.getShapeAppearanceModel() != shapeAppearanceModel0) {
            this.shapeAppearanceModel = shapeAppearanceModel0;
            this.applyBoxAttributes();
        }
    }

    public void setStartIconCheckable(boolean z) {
        this.startLayout.setStartIconCheckable(z);
    }

    public void setStartIconContentDescription(int v) {
        this.setStartIconContentDescription((v == 0 ? null : this.getResources().getText(v)));
    }

    public void setStartIconContentDescription(CharSequence charSequence0) {
        this.startLayout.setStartIconContentDescription(charSequence0);
    }

    public void setStartIconDrawable(int v) {
        this.setStartIconDrawable((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    public void setStartIconDrawable(Drawable drawable0) {
        this.startLayout.setStartIconDrawable(drawable0);
    }

    public void setStartIconMinSize(int v) {
        this.startLayout.setStartIconMinSize(v);
    }

    public void setStartIconOnClickListener(View.OnClickListener view$OnClickListener0) {
        this.startLayout.setStartIconOnClickListener(view$OnClickListener0);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener view$OnLongClickListener0) {
        this.startLayout.setStartIconOnLongClickListener(view$OnLongClickListener0);
    }

    public void setStartIconScaleType(ImageView.ScaleType imageView$ScaleType0) {
        this.startLayout.setStartIconScaleType(imageView$ScaleType0);
    }

    public void setStartIconTintList(ColorStateList colorStateList0) {
        this.startLayout.setStartIconTintList(colorStateList0);
    }

    public void setStartIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.startLayout.setStartIconTintMode(porterDuff$Mode0);
    }

    public void setStartIconVisible(boolean z) {
        this.startLayout.setStartIconVisible(z);
    }

    public void setSuffixText(CharSequence charSequence0) {
        this.endLayout.setSuffixText(charSequence0);
    }

    public void setSuffixTextAppearance(int v) {
        this.endLayout.setSuffixTextAppearance(v);
    }

    public void setSuffixTextColor(ColorStateList colorStateList0) {
        this.endLayout.setSuffixTextColor(colorStateList0);
    }

    void setTextAppearanceCompatWithErrorFallback(TextView textView0, int v) {
        try {
            TextViewCompat.setTextAppearance(textView0, v);
            if(Build.VERSION.SDK_INT >= 23 && textView0.getTextColors().getDefaultColor() == 0xFFFF00FF) {
                goto label_3;
            }
            return;
        }
        catch(Exception unused_ex) {
        }
    label_3:
        TextViewCompat.setTextAppearance(textView0, style.TextAppearance_AppCompat_Caption);
        textView0.setTextColor(ContextCompat.getColor(this.getContext(), color.design_error));
    }

    public void setTextInputAccessibilityDelegate(AccessibilityDelegate textInputLayout$AccessibilityDelegate0) {
        EditText editText0 = this.editText;
        if(editText0 != null) {
            ViewCompat.setAccessibilityDelegate(editText0, textInputLayout$AccessibilityDelegate0);
        }
    }

    public void setTypeface(Typeface typeface0) {
        if(typeface0 != this.typeface) {
            this.typeface = typeface0;
            this.collapsingTextHelper.setTypefaces(typeface0);
            this.indicatorViewController.setTypefaces(typeface0);
            TextView textView0 = this.counterView;
            if(textView0 != null) {
                textView0.setTypeface(typeface0);
            }
        }
    }

    boolean shouldShowError() {
        return this.indicatorViewController.errorShouldBeShown();
    }

    // 去混淆评级： 低(30)
    private boolean shouldUpdateEndDummyDrawable() {
        return (this.endLayout.isErrorIconVisible() || this.endLayout.hasEndIcon() && this.isEndIconVisible() || this.endLayout.getSuffixText() != null) && this.endLayout.getMeasuredWidth() > 0;
    }

    private boolean shouldUpdateStartDummyDrawable() {
        return (this.getStartIconDrawable() != null || this.getPrefixText() != null && this.getPrefixTextView().getVisibility() == 0) && this.startLayout.getMeasuredWidth() > 0;
    }

    private void showPlaceholderText() {
        if(this.placeholderTextView != null && this.placeholderEnabled && !TextUtils.isEmpty(this.placeholderText)) {
            this.placeholderTextView.setText(this.placeholderText);
            TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeIn);
            this.placeholderTextView.setVisibility(0);
            this.placeholderTextView.bringToFront();
            this.announceForAccessibility(this.placeholderText);
        }
    }

    private void updateBoxCollapsedPaddingTop() {
        if(this.boxBackgroundMode == 1) {
            if(MaterialResources.isFontScaleAtLeast2_0(this.getContext())) {
                this.boxCollapsedPaddingTopPx = this.getResources().getDimensionPixelSize(dimen.material_font_2_0_box_collapsed_padding_top);
                return;
            }
            if(MaterialResources.isFontScaleAtLeast1_3(this.getContext())) {
                this.boxCollapsedPaddingTopPx = this.getResources().getDimensionPixelSize(dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
    }

    private void updateBoxUnderlineBounds(Rect rect0) {
        if(this.boxUnderlineDefault != null) {
            this.boxUnderlineDefault.setBounds(rect0.left, rect0.bottom - this.boxStrokeWidthDefaultPx, rect0.right, rect0.bottom);
        }
        if(this.boxUnderlineFocused != null) {
            this.boxUnderlineFocused.setBounds(rect0.left, rect0.bottom - this.boxStrokeWidthFocusedPx, rect0.right, rect0.bottom);
        }
    }

    private void updateCounter() {
        if(this.counterView != null) {
            this.updateCounter((this.editText == null ? null : this.editText.getText()));
        }
    }

    void updateCounter(Editable editable0) {
        int v = this.lengthCounter.countLength(editable0);
        boolean z = this.counterOverflowed;
        int v1 = this.counterMaxLength;
        if(v1 == -1) {
            this.counterView.setText(String.valueOf(v));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        }
        else {
            this.counterOverflowed = v > v1;
            TextInputLayout.updateCounterContentDescription(this.getContext(), this.counterView, v, this.counterMaxLength, this.counterOverflowed);
            if(z != this.counterOverflowed) {
                this.updateCounterTextAppearanceAndColor();
            }
            BidiFormatter bidiFormatter0 = BidiFormatter.getInstance();
            this.counterView.setText(bidiFormatter0.unicodeWrap(this.getContext().getString(string.character_counter_pattern, new Object[]{v, this.counterMaxLength})));
        }
        if(this.editText != null && z != this.counterOverflowed) {
            this.updateLabelState(false);
            this.updateTextInputBoxState();
            this.updateEditTextBackground();
        }
    }

    private static void updateCounterContentDescription(Context context0, TextView textView0, int v, int v1, boolean z) {
        textView0.setContentDescription(context0.getString((z ? string.character_counter_overflowed_content_description : string.character_counter_content_description), new Object[]{v, v1}));
    }

    private void updateCounterTextAppearanceAndColor() {
        TextView textView0 = this.counterView;
        if(textView0 != null) {
            this.setTextAppearanceCompatWithErrorFallback(textView0, (this.counterOverflowed ? this.counterOverflowTextAppearance : this.counterTextAppearance));
            if(!this.counterOverflowed) {
                ColorStateList colorStateList0 = this.counterTextColor;
                if(colorStateList0 != null) {
                    this.counterView.setTextColor(colorStateList0);
                }
            }
            if(this.counterOverflowed) {
                ColorStateList colorStateList1 = this.counterOverflowTextColor;
                if(colorStateList1 != null) {
                    this.counterView.setTextColor(colorStateList1);
                }
            }
        }
    }

    private void updateCursorColor() {
        ColorStateList colorStateList0 = this.cursorColor == null ? MaterialColors.getColorStateListOrNull(this.getContext(), attr.colorControlActivated) : this.cursorColor;
        if(this.editText != null && LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.editText) != null) {
            Drawable drawable0 = DrawableCompat.wrap(LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.editText)).mutate();
            if(this.isOnError()) {
                ColorStateList colorStateList1 = this.cursorErrorColor;
                if(colorStateList1 != null) {
                    colorStateList0 = colorStateList1;
                }
            }
            DrawableCompat.setTintList(drawable0, colorStateList0);
        }
    }

    boolean updateDummyDrawables() {
        boolean z1;
        boolean z = true;
        if(this.editText == null) {
            return false;
        }
        if(this.shouldUpdateStartDummyDrawable()) {
            int v = this.startLayout.getMeasuredWidth() - this.editText.getPaddingLeft();
            if(this.startDummyDrawable == null || this.startDummyDrawableWidth != v) {
                ColorDrawable colorDrawable0 = new ColorDrawable();
                this.startDummyDrawable = colorDrawable0;
                this.startDummyDrawableWidth = v;
                colorDrawable0.setBounds(0, 0, v, 1);
            }
            Drawable[] arr_drawable = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            Drawable drawable0 = this.startDummyDrawable;
            if(arr_drawable[0] == drawable0) {
                z1 = false;
            }
            else {
                TextViewCompat.setCompoundDrawablesRelative(this.editText, drawable0, arr_drawable[1], arr_drawable[2], arr_drawable[3]);
                z1 = true;
            }
        }
        else if(this.startDummyDrawable == null) {
            z1 = false;
        }
        else {
            Drawable[] arr_drawable1 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            TextViewCompat.setCompoundDrawablesRelative(this.editText, null, arr_drawable1[1], arr_drawable1[2], arr_drawable1[3]);
            this.startDummyDrawable = null;
            z1 = true;
        }
        if(this.shouldUpdateEndDummyDrawable()) {
            int v1 = this.endLayout.getSuffixTextView().getMeasuredWidth() - this.editText.getPaddingRight();
            CheckableImageButton checkableImageButton0 = this.endLayout.getCurrentEndIconView();
            if(checkableImageButton0 != null) {
                v1 = v1 + checkableImageButton0.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginStart(((ViewGroup.MarginLayoutParams)checkableImageButton0.getLayoutParams()));
            }
            Drawable[] arr_drawable2 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            Drawable drawable1 = this.endDummyDrawable;
            if(drawable1 != null && this.endDummyDrawableWidth != v1) {
                this.endDummyDrawableWidth = v1;
                drawable1.setBounds(0, 0, v1, 1);
                TextViewCompat.setCompoundDrawablesRelative(this.editText, arr_drawable2[0], arr_drawable2[1], this.endDummyDrawable, arr_drawable2[3]);
                return true;
            }
            if(drawable1 == null) {
                ColorDrawable colorDrawable1 = new ColorDrawable();
                this.endDummyDrawable = colorDrawable1;
                this.endDummyDrawableWidth = v1;
                colorDrawable1.setBounds(0, 0, v1, 1);
            }
            Drawable drawable2 = arr_drawable2[2];
            Drawable drawable3 = this.endDummyDrawable;
            if(drawable2 != drawable3) {
                this.originalEditTextEndDrawable = drawable2;
                TextViewCompat.setCompoundDrawablesRelative(this.editText, arr_drawable2[0], arr_drawable2[1], drawable3, arr_drawable2[3]);
                return true;
            }
        }
        else if(this.endDummyDrawable != null) {
            Drawable[] arr_drawable3 = TextViewCompat.getCompoundDrawablesRelative(this.editText);
            if(arr_drawable3[2] == this.endDummyDrawable) {
                TextViewCompat.setCompoundDrawablesRelative(this.editText, arr_drawable3[0], arr_drawable3[1], this.originalEditTextEndDrawable, arr_drawable3[3]);
            }
            else {
                z = z1;
            }
            this.endDummyDrawable = null;
            return z;
        }
        return z1;
    }

    void updateEditTextBackground() {
        EditText editText0 = this.editText;
        if(editText0 != null && this.boxBackgroundMode == 0) {
            Drawable drawable0 = editText0.getBackground();
            if(drawable0 != null) {
                drawable0 = drawable0.mutate();
                if(this.shouldShowError()) {
                    drawable0.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(this.getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
                    return;
                }
                if(this.counterOverflowed) {
                    TextView textView0 = this.counterView;
                    if(textView0 != null) {
                        drawable0.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView0.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
                        return;
                    }
                }
                DrawableCompat.clearColorFilter(drawable0);
                this.editText.refreshDrawableState();
            }
        }
    }

    private void updateEditTextBoxBackground() {
        Drawable drawable0 = this.getEditTextBoxBackground();
        ViewCompat.setBackground(this.editText, drawable0);
    }

    void updateEditTextBoxBackgroundIfNeeded() {
        if(this.editText != null && this.boxBackground != null && (this.boxBackgroundApplied || this.editText.getBackground() == null) && this.boxBackgroundMode != 0) {
            this.updateEditTextBoxBackground();
            this.boxBackgroundApplied = true;
        }
    }

    private boolean updateEditTextHeightBasedOnIcon() {
        if(this.editText == null) {
            return false;
        }
        int v = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight());
        if(this.editText.getMeasuredHeight() < v) {
            this.editText.setMinimumHeight(v);
            return true;
        }
        return false;
    }

    private void updateInputLayoutMargins() {
        if(this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = (LinearLayout.LayoutParams)this.inputFrame.getLayoutParams();
            int v = this.calculateLabelMarginTop();
            if(v != linearLayout$LayoutParams0.topMargin) {
                linearLayout$LayoutParams0.topMargin = v;
                this.inputFrame.requestLayout();
            }
        }
    }

    private void updateLabelState(boolean z, boolean z1) {
        boolean z2 = this.isEnabled();
        boolean z3 = true;
        boolean z4 = this.editText != null && !TextUtils.isEmpty(this.editText.getText());
        if(this.editText == null || !this.editText.hasFocus()) {
            z3 = false;
        }
        ColorStateList colorStateList0 = this.defaultHintTextColor;
        if(colorStateList0 != null) {
            this.collapsingTextHelper.setCollapsedAndExpandedTextColor(colorStateList0);
        }
        if(!z2) {
            ColorStateList colorStateList1 = ColorStateList.valueOf((this.defaultHintTextColor == null ? this.disabledColor : this.defaultHintTextColor.getColorForState(new int[]{0xFEFEFF62}, this.disabledColor)));
            this.collapsingTextHelper.setCollapsedAndExpandedTextColor(colorStateList1);
        }
        else if(this.shouldShowError()) {
            ColorStateList colorStateList2 = this.indicatorViewController.getErrorViewTextColors();
            this.collapsingTextHelper.setCollapsedAndExpandedTextColor(colorStateList2);
        }
        else if(this.counterOverflowed) {
            TextView textView0 = this.counterView;
            if(textView0 != null) {
                ColorStateList colorStateList3 = textView0.getTextColors();
                this.collapsingTextHelper.setCollapsedAndExpandedTextColor(colorStateList3);
                goto label_26;
            }
            goto label_22;
        }
        else {
        label_22:
            if(z3) {
                ColorStateList colorStateList4 = this.focusedTextColor;
                if(colorStateList4 != null) {
                    this.collapsingTextHelper.setCollapsedTextColor(colorStateList4);
                }
            }
        }
    label_26:
        if(!z4 && this.expandedHintEnabled && (!this.isEnabled() || !z3)) {
            if(!z1 && this.hintExpanded) {
                return;
            }
            this.expandHint(z);
            return;
        }
        if(!z1 && !this.hintExpanded) {
            return;
        }
        this.collapseHint(z);
    }

    void updateLabelState(boolean z) {
        this.updateLabelState(z, false);
    }

    private void updatePlaceholderMeasurementsBasedOnEditText() {
        if(this.placeholderTextView != null) {
            EditText editText0 = this.editText;
            if(editText0 != null) {
                int v = editText0.getGravity();
                this.placeholderTextView.setGravity(v);
                this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
            }
        }
    }

    private void updatePlaceholderText() {
        this.updatePlaceholderText((this.editText == null ? null : this.editText.getText()));
    }

    private void updatePlaceholderText(Editable editable0) {
        if(this.lengthCounter.countLength(editable0) == 0 && !this.hintExpanded) {
            this.showPlaceholderText();
            return;
        }
        this.hidePlaceholderText();
    }

    private void updateStrokeErrorColor(boolean z, boolean z1) {
        int v = this.strokeErrorColor.getDefaultColor();
        int v1 = this.strokeErrorColor.getColorForState(new int[]{0x1010367, 0x101009E}, v);
        int v2 = this.strokeErrorColor.getColorForState(new int[]{0x10102FE, 0x101009E}, v);
        if(z) {
            this.boxStrokeColor = v2;
            return;
        }
        if(z1) {
            this.boxStrokeColor = v1;
            return;
        }
        this.boxStrokeColor = v;
    }

    void updateTextInputBoxState() {
        boolean z = false;
        if(this.boxBackground != null && this.boxBackgroundMode != 0) {
            boolean z1 = this.isFocused() || this.editText != null && this.editText.hasFocus();
            if(this.isHovered() || this.editText != null && this.editText.isHovered()) {
                z = true;
            }
            if(!this.isEnabled()) {
                this.boxStrokeColor = this.disabledColor;
            }
            else if(!this.shouldShowError()) {
                if(this.counterOverflowed) {
                    TextView textView0 = this.counterView;
                    if(textView0 == null) {
                        goto label_22;
                    }
                    else if(this.strokeErrorColor != null) {
                        this.updateStrokeErrorColor(z1, z);
                    }
                    else {
                        this.boxStrokeColor = textView0.getCurrentTextColor();
                    }
                }
                else {
                label_22:
                    if(z1) {
                        this.boxStrokeColor = this.focusedStrokeColor;
                    }
                    else if(z) {
                        this.boxStrokeColor = this.hoveredStrokeColor;
                    }
                    else {
                        this.boxStrokeColor = this.defaultStrokeColor;
                    }
                }
            }
            else if(this.strokeErrorColor != null) {
                this.updateStrokeErrorColor(z1, z);
            }
            else {
                this.boxStrokeColor = this.getErrorCurrentTextColors();
            }
            if(Build.VERSION.SDK_INT >= 29) {
                this.updateCursorColor();
            }
            this.endLayout.onTextInputBoxStateUpdated();
            this.refreshStartIconDrawableState();
            if(this.boxBackgroundMode == 2) {
                int v = this.boxStrokeWidthPx;
                this.boxStrokeWidthPx = !z1 || !this.isEnabled() ? this.boxStrokeWidthDefaultPx : this.boxStrokeWidthFocusedPx;
                if(this.boxStrokeWidthPx != v) {
                    this.recalculateCutout();
                }
            }
            if(this.boxBackgroundMode == 1) {
                if(!this.isEnabled()) {
                    this.boxBackgroundColor = this.disabledFilledBackgroundColor;
                }
                else if(z && !z1) {
                    this.boxBackgroundColor = this.hoveredFilledBackgroundColor;
                }
                else if(z1) {
                    this.boxBackgroundColor = this.focusedFilledBackgroundColor;
                }
                else {
                    this.boxBackgroundColor = this.defaultFilledBackgroundColor;
                }
            }
            this.applyBoxAttributes();
        }
    }
}

