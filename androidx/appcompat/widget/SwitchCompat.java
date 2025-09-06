package androidx.appcompat.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.InputFilter;
import android.text.Layout.Alignment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.Property;
import android.view.ActionMode.Callback;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.string;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.text.AllCapsTransformationMethod;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.emoji2.text.EmojiCompat.InitCallback;
import androidx.emoji2.text.EmojiCompat;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class SwitchCompat extends CompoundButton implements EmojiCompatConfigurationView {
    static class EmojiCompatInitCallback extends InitCallback {
        private final Reference mOuterWeakRef;

        EmojiCompatInitCallback(SwitchCompat switchCompat0) {
            this.mOuterWeakRef = new WeakReference(switchCompat0);
        }

        @Override  // androidx.emoji2.text.EmojiCompat$InitCallback
        public void onFailed(Throwable throwable0) {
            SwitchCompat switchCompat0 = (SwitchCompat)this.mOuterWeakRef.get();
            if(switchCompat0 != null) {
                switchCompat0.onEmojiCompatInitializedForSwitchText();
            }
        }

        @Override  // androidx.emoji2.text.EmojiCompat$InitCallback
        public void onInitialized() {
            SwitchCompat switchCompat0 = (SwitchCompat)this.mOuterWeakRef.get();
            if(switchCompat0 != null) {
                switchCompat0.onEmojiCompatInitializedForSwitchText();
            }
        }
    }

    private static final String ACCESSIBILITY_EVENT_CLASS_NAME = "android.widget.Switch";
    private static final int[] CHECKED_STATE_SET = null;
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int THUMB_ANIMATION_DURATION = 0xFA;
    private static final Property THUMB_POS = null;
    private static final int TOUCH_MODE_DOWN = 1;
    private static final int TOUCH_MODE_DRAGGING = 2;
    private static final int TOUCH_MODE_IDLE;
    private AppCompatEmojiTextHelper mAppCompatEmojiTextHelper;
    private EmojiCompatInitCallback mEmojiCompatInitCallback;
    private boolean mEnforceSwitchWidth;
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    ObjectAnimator mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    private final AppCompatTextHelper mTextHelper;
    private CharSequence mTextOff;
    private CharSequence mTextOffTransformed;
    private CharSequence mTextOn;
    private CharSequence mTextOnTransformed;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private PorterDuff.Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private PorterDuff.Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

    static {
        SwitchCompat.THUMB_POS = new Property(Float.class, "thumbPos") {
            public Float get(SwitchCompat switchCompat0) {
                return switchCompat0.mThumbPosition;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((SwitchCompat)object0));
            }

            public void set(SwitchCompat switchCompat0, Float float0) {
                switchCompat0.setThumbPosition(((float)float0));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((SwitchCompat)object0), ((Float)object1));
            }
        };
        SwitchCompat.CHECKED_STATE_SET = new int[]{0x10100A0};
    }

    public SwitchCompat(Context context0) {
        this(context0, null);
    }

    public SwitchCompat(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.switchStyle);
    }

    public SwitchCompat(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mThumbTintList = null;
        this.mThumbTintMode = null;
        this.mHasThumbTint = false;
        this.mHasThumbTintMode = false;
        this.mTrackTintList = null;
        this.mTrackTintMode = null;
        this.mHasTrackTint = false;
        this.mHasTrackTintMode = false;
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mEnforceSwitchWidth = true;
        this.mTempRect = new Rect();
        ThemeUtils.checkAppCompatTheme(this, this.getContext());
        TextPaint textPaint0 = new TextPaint(1);
        this.mTextPaint = textPaint0;
        textPaint0.density = this.getResources().getDisplayMetrics().density;
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.SwitchCompat, v, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.SwitchCompat, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        Drawable drawable0 = tintTypedArray0.getDrawable(styleable.SwitchCompat_android_thumb);
        this.mThumbDrawable = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
        }
        Drawable drawable1 = tintTypedArray0.getDrawable(styleable.SwitchCompat_track);
        this.mTrackDrawable = drawable1;
        if(drawable1 != null) {
            drawable1.setCallback(this);
        }
        this.setTextOnInternal(tintTypedArray0.getText(styleable.SwitchCompat_android_textOn));
        this.setTextOffInternal(tintTypedArray0.getText(styleable.SwitchCompat_android_textOff));
        this.mShowText = tintTypedArray0.getBoolean(styleable.SwitchCompat_showText, true);
        this.mThumbTextPadding = tintTypedArray0.getDimensionPixelSize(styleable.SwitchCompat_thumbTextPadding, 0);
        this.mSwitchMinWidth = tintTypedArray0.getDimensionPixelSize(styleable.SwitchCompat_switchMinWidth, 0);
        this.mSwitchPadding = tintTypedArray0.getDimensionPixelSize(styleable.SwitchCompat_switchPadding, 0);
        this.mSplitTrack = tintTypedArray0.getBoolean(styleable.SwitchCompat_splitTrack, false);
        ColorStateList colorStateList0 = tintTypedArray0.getColorStateList(styleable.SwitchCompat_thumbTint);
        if(colorStateList0 != null) {
            this.mThumbTintList = colorStateList0;
            this.mHasThumbTint = true;
        }
        PorterDuff.Mode porterDuff$Mode0 = DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.SwitchCompat_thumbTintMode, -1), null);
        if(this.mThumbTintMode != porterDuff$Mode0) {
            this.mThumbTintMode = porterDuff$Mode0;
            this.mHasThumbTintMode = true;
        }
        if(this.mHasThumbTint || this.mHasThumbTintMode) {
            this.applyThumbTint();
        }
        ColorStateList colorStateList1 = tintTypedArray0.getColorStateList(styleable.SwitchCompat_trackTint);
        if(colorStateList1 != null) {
            this.mTrackTintList = colorStateList1;
            this.mHasTrackTint = true;
        }
        PorterDuff.Mode porterDuff$Mode1 = DrawableUtils.parseTintMode(tintTypedArray0.getInt(styleable.SwitchCompat_trackTintMode, -1), null);
        if(this.mTrackTintMode != porterDuff$Mode1) {
            this.mTrackTintMode = porterDuff$Mode1;
            this.mHasTrackTintMode = true;
        }
        if(this.mHasTrackTint || this.mHasTrackTintMode) {
            this.applyTrackTint();
        }
        int v1 = tintTypedArray0.getResourceId(styleable.SwitchCompat_switchTextAppearance, 0);
        if(v1 != 0) {
            this.setSwitchTextAppearance(context0, v1);
        }
        AppCompatTextHelper appCompatTextHelper0 = new AppCompatTextHelper(this);
        this.mTextHelper = appCompatTextHelper0;
        appCompatTextHelper0.loadFromAttributes(attributeSet0, v);
        tintTypedArray0.recycle();
        ViewConfiguration viewConfiguration0 = ViewConfiguration.get(context0);
        this.mTouchSlop = viewConfiguration0.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration0.getScaledMinimumFlingVelocity();
        this.getEmojiTextViewHelper().loadFromAttributes(attributeSet0, v);
        this.refreshDrawableState();
        this.setChecked(this.isChecked());
    }

    private void animateThumbToCheckedState(boolean z) {
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this, SwitchCompat.THUMB_POS, new float[]{(z ? 1.0f : 0.0f)});
        this.mPositionAnimator = objectAnimator0;
        objectAnimator0.setDuration(0xFAL);
        this.mPositionAnimator.setAutoCancel(true);
        this.mPositionAnimator.start();
    }

    private void applyThumbTint() {
        Drawable drawable0 = this.mThumbDrawable;
        if(drawable0 != null && (this.mHasThumbTint || this.mHasThumbTintMode)) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            this.mThumbDrawable = drawable1;
            if(this.mHasThumbTint) {
                DrawableCompat.setTintList(drawable1, this.mThumbTintList);
            }
            if(this.mHasThumbTintMode) {
                DrawableCompat.setTintMode(this.mThumbDrawable, this.mThumbTintMode);
            }
            if(this.mThumbDrawable.isStateful()) {
                this.mThumbDrawable.setState(this.getDrawableState());
            }
        }
    }

    private void applyTrackTint() {
        Drawable drawable0 = this.mTrackDrawable;
        if(drawable0 != null && (this.mHasTrackTint || this.mHasTrackTintMode)) {
            Drawable drawable1 = DrawableCompat.wrap(drawable0).mutate();
            this.mTrackDrawable = drawable1;
            if(this.mHasTrackTint) {
                DrawableCompat.setTintList(drawable1, this.mTrackTintList);
            }
            if(this.mHasTrackTintMode) {
                DrawableCompat.setTintMode(this.mTrackDrawable, this.mTrackTintMode);
            }
            if(this.mTrackDrawable.isStateful()) {
                this.mTrackDrawable.setState(this.getDrawableState());
            }
        }
    }

    private void cancelPositionAnimator() {
        ObjectAnimator objectAnimator0 = this.mPositionAnimator;
        if(objectAnimator0 != null) {
            objectAnimator0.cancel();
        }
    }

    private void cancelSuperTouch(MotionEvent motionEvent0) {
        MotionEvent motionEvent1 = MotionEvent.obtain(motionEvent0);
        motionEvent1.setAction(3);
        super.onTouchEvent(motionEvent1);
        motionEvent1.recycle();
    }

    private static float constrain(float f, float f1, float f2) {
        if(f < f1) {
            return f1;
        }
        return f > f2 ? f2 : f;
    }

    private CharSequence doTransformForOnOffText(CharSequence charSequence0) {
        TransformationMethod transformationMethod0 = this.getEmojiTextViewHelper().wrapTransformationMethod(this.mSwitchTransformationMethod);
        return transformationMethod0 == null ? charSequence0 : transformationMethod0.getTransformation(charSequence0, this);
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        int v6;
        int v5;
        Rect rect0 = this.mTempRect;
        int v = this.mSwitchLeft;
        int v1 = this.mSwitchTop;
        int v2 = this.mSwitchRight;
        int v3 = this.mSwitchBottom;
        int v4 = this.getThumbOffset() + v;
        Rect rect1 = this.mThumbDrawable == null ? DrawableUtils.INSETS_NONE : DrawableUtils.getOpticalBounds(this.mThumbDrawable);
        Drawable drawable0 = this.mTrackDrawable;
        if(drawable0 != null) {
            drawable0.getPadding(rect0);
            v4 += rect0.left;
            if(rect1 == null) {
                v5 = v1;
                v6 = v3;
            }
            else {
                if(rect1.left > rect0.left) {
                    v += rect1.left - rect0.left;
                }
                v5 = rect1.top <= rect0.top ? v1 : rect1.top - rect0.top + v1;
                if(rect1.right > rect0.right) {
                    v2 -= rect1.right - rect0.right;
                }
                v6 = rect1.bottom > rect0.bottom ? v3 - (rect1.bottom - rect0.bottom) : v3;
            }
            this.mTrackDrawable.setBounds(v, v5, v2, v6);
        }
        Drawable drawable1 = this.mThumbDrawable;
        if(drawable1 != null) {
            drawable1.getPadding(rect0);
            int v7 = v4 - rect0.left;
            int v8 = v4 + this.mThumbWidth + rect0.right;
            this.mThumbDrawable.setBounds(v7, v1, v8, v3);
            Drawable drawable2 = this.getBackground();
            if(drawable2 != null) {
                DrawableCompat.setHotspotBounds(drawable2, v7, v1, v8, v3);
            }
        }
        super.draw(canvas0);
    }

    @Override  // android.widget.CompoundButton
    public void drawableHotspotChanged(float f, float f1) {
        super.drawableHotspotChanged(f, f1);
        Drawable drawable0 = this.mThumbDrawable;
        if(drawable0 != null) {
            DrawableCompat.setHotspot(drawable0, f, f1);
        }
        Drawable drawable1 = this.mTrackDrawable;
        if(drawable1 != null) {
            DrawableCompat.setHotspot(drawable1, f, f1);
        }
    }

    @Override  // android.widget.CompoundButton
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] arr_v = this.getDrawableState();
        Drawable drawable0 = this.mThumbDrawable;
        boolean z = drawable0 == null || !drawable0.isStateful() ? false : drawable0.setState(arr_v);
        Drawable drawable1 = this.mTrackDrawable;
        if(drawable1 != null && drawable1.isStateful()) {
            z |= drawable1.setState(arr_v);
        }
        if(z) {
            this.invalidate();
        }
    }

    @Override  // android.widget.CompoundButton
    public int getCompoundPaddingLeft() {
        if(!ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingLeft();
        }
        int v = super.getCompoundPaddingLeft() + this.mSwitchWidth;
        return TextUtils.isEmpty(this.getText()) ? v : v + this.mSwitchPadding;
    }

    @Override  // android.widget.CompoundButton
    public int getCompoundPaddingRight() {
        if(ViewUtils.isLayoutRtl(this)) {
            return super.getCompoundPaddingRight();
        }
        int v = super.getCompoundPaddingRight() + this.mSwitchWidth;
        return TextUtils.isEmpty(this.getText()) ? v : v + this.mSwitchPadding;
    }

    @Override  // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return TextViewCompat.unwrapCustomSelectionActionModeCallback(super.getCustomSelectionActionModeCallback());
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if(this.mAppCompatEmojiTextHelper == null) {
            this.mAppCompatEmojiTextHelper = new AppCompatEmojiTextHelper(this);
        }
        return this.mAppCompatEmojiTextHelper;
    }

    public boolean getShowText() {
        return this.mShowText;
    }

    public boolean getSplitTrack() {
        return this.mSplitTrack;
    }

    public int getSwitchMinWidth() {
        return this.mSwitchMinWidth;
    }

    public int getSwitchPadding() {
        return this.mSwitchPadding;
    }

    private boolean getTargetCheckedState() {
        return this.mThumbPosition > 0.5f;
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    // 去混淆评级： 低(20)
    private int getThumbOffset() {
        return ViewUtils.isLayoutRtl(this) ? ((int)((1.0f - this.mThumbPosition) * ((float)this.getThumbScrollRange()) + 0.5f)) : ((int)(this.mThumbPosition * ((float)this.getThumbScrollRange()) + 0.5f));
    }

    protected final float getThumbPosition() {
        return this.mThumbPosition;
    }

    private int getThumbScrollRange() {
        Drawable drawable0 = this.mTrackDrawable;
        if(drawable0 != null) {
            Rect rect0 = this.mTempRect;
            drawable0.getPadding(rect0);
            Drawable drawable1 = this.mThumbDrawable;
            if(drawable1 != null) {
                Rect rect1 = DrawableUtils.getOpticalBounds(drawable1);
                return this.mSwitchWidth - this.mThumbWidth - rect0.left - rect0.right - rect1.left - rect1.right;
            }
            return this.mSwitchWidth - this.mThumbWidth - rect0.left - rect0.right - DrawableUtils.INSETS_NONE.left - DrawableUtils.INSETS_NONE.right;
        }
        return 0;
    }

    public int getThumbTextPadding() {
        return this.mThumbTextPadding;
    }

    public ColorStateList getThumbTintList() {
        return this.mThumbTintList;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.mThumbTintMode;
    }

    public Drawable getTrackDrawable() {
        return this.mTrackDrawable;
    }

    public ColorStateList getTrackTintList() {
        return this.mTrackTintList;
    }

    public PorterDuff.Mode getTrackTintMode() {
        return this.mTrackTintMode;
    }

    private boolean hitThumb(float f, float f1) {
        if(this.mThumbDrawable == null) {
            return false;
        }
        int v = this.getThumbOffset();
        this.mThumbDrawable.getPadding(this.mTempRect);
        int v1 = this.mSwitchLeft + v - this.mTouchSlop;
        return f > ((float)v1) && f < ((float)(this.mThumbWidth + v1 + this.mTempRect.left + this.mTempRect.right + this.mTouchSlop)) && f1 > ((float)(this.mSwitchTop - this.mTouchSlop)) && f1 < ((float)(this.mSwitchBottom + this.mTouchSlop));
    }

    @Override  // androidx.appcompat.widget.EmojiCompatConfigurationView
    public boolean isEmojiCompatEnabled() {
        return this.getEmojiTextViewHelper().isEnabled();
    }

    @Override  // android.widget.CompoundButton
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable0 = this.mThumbDrawable;
        if(drawable0 != null) {
            drawable0.jumpToCurrentState();
        }
        Drawable drawable1 = this.mTrackDrawable;
        if(drawable1 != null) {
            drawable1.jumpToCurrentState();
        }
        if(this.mPositionAnimator != null && this.mPositionAnimator.isStarted()) {
            this.mPositionAnimator.end();
            this.mPositionAnimator = null;
        }
    }

    private Layout makeLayout(CharSequence charSequence0) {
        return charSequence0 == null ? new StaticLayout(null, this.mTextPaint, 0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true) : new StaticLayout(charSequence0, this.mTextPaint, ((int)Math.ceil(Layout.getDesiredWidth(charSequence0, this.mTextPaint))), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
    }

    @Override  // android.widget.CompoundButton
    protected int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 1);
        if(this.isChecked()) {
            SwitchCompat.mergeDrawableStates(arr_v, SwitchCompat.CHECKED_STATE_SET);
        }
        return arr_v;
    }

    @Override  // android.widget.CompoundButton
    protected void onDraw(Canvas canvas0) {
        int v5;
        super.onDraw(canvas0);
        Rect rect0 = this.mTempRect;
        Drawable drawable0 = this.mTrackDrawable;
        if(drawable0 == null) {
            rect0.setEmpty();
        }
        else {
            drawable0.getPadding(rect0);
        }
        int v = this.mSwitchTop + rect0.top;
        int v1 = this.mSwitchBottom - rect0.bottom;
        Drawable drawable1 = this.mThumbDrawable;
        if(drawable0 != null) {
            if(!this.mSplitTrack || drawable1 == null) {
                drawable0.draw(canvas0);
            }
            else {
                Rect rect1 = DrawableUtils.getOpticalBounds(drawable1);
                drawable1.copyBounds(rect0);
                rect0.left += rect1.left;
                rect0.right -= rect1.right;
                int v2 = canvas0.save();
                canvas0.clipRect(rect0, Region.Op.DIFFERENCE);
                drawable0.draw(canvas0);
                canvas0.restoreToCount(v2);
            }
        }
        int v3 = canvas0.save();
        if(drawable1 != null) {
            drawable1.draw(canvas0);
        }
        Layout layout0 = this.getTargetCheckedState() ? this.mOnLayout : this.mOffLayout;
        if(layout0 != null) {
            int[] arr_v = this.getDrawableState();
            ColorStateList colorStateList0 = this.mTextColors;
            if(colorStateList0 != null) {
                int v4 = colorStateList0.getColorForState(arr_v, 0);
                this.mTextPaint.setColor(v4);
            }
            this.mTextPaint.drawableState = arr_v;
            if(drawable1 == null) {
                v5 = this.getWidth();
            }
            else {
                Rect rect2 = drawable1.getBounds();
                v5 = rect2.left + rect2.right;
            }
            canvas0.translate(((float)(v5 / 2 - layout0.getWidth() / 2)), ((float)((v + v1) / 2 - layout0.getHeight() / 2)));
            layout0.draw(canvas0);
        }
        canvas0.restoreToCount(v3);
    }

    void onEmojiCompatInitializedForSwitchText() {
        this.setTextOnInternal(this.mTextOn);
        this.setTextOffInternal(this.mTextOff);
        this.requestLayout();
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        accessibilityEvent0.setClassName("android.widget.Switch");
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        accessibilityNodeInfo0.setClassName("android.widget.Switch");
        if(Build.VERSION.SDK_INT < 30) {
            CharSequence charSequence0 = this.isChecked() ? this.mTextOn : this.mTextOff;
            if(!TextUtils.isEmpty(charSequence0)) {
                CharSequence charSequence1 = accessibilityNodeInfo0.getText();
                if(TextUtils.isEmpty(charSequence1)) {
                    accessibilityNodeInfo0.setText(charSequence0);
                    return;
                }
                StringBuilder stringBuilder0 = new StringBuilder();
                stringBuilder0.append(charSequence1);
                stringBuilder0.append(' ');
                stringBuilder0.append(charSequence0);
                accessibilityNodeInfo0.setText(stringBuilder0);
            }
        }
    }

    @Override  // android.widget.TextView
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v9;
        int v8;
        int v7;
        int v6;
        int v5;
        super.onLayout(z, v, v1, v2, v3);
        int v4 = 0;
        if(this.mThumbDrawable == null) {
            v5 = 0;
        }
        else {
            Rect rect0 = this.mTempRect;
            Drawable drawable0 = this.mTrackDrawable;
            if(drawable0 == null) {
                rect0.setEmpty();
            }
            else {
                drawable0.getPadding(rect0);
            }
            Rect rect1 = DrawableUtils.getOpticalBounds(this.mThumbDrawable);
            v5 = Math.max(0, rect1.left - rect0.left);
            v4 = Math.max(0, rect1.right - rect0.right);
        }
        if(ViewUtils.isLayoutRtl(this)) {
            v6 = this.getPaddingLeft() + v5;
            v7 = this.mSwitchWidth + v6 - v5 - v4;
        }
        else {
            v7 = this.getWidth() - this.getPaddingRight() - v4;
            v6 = v7 - this.mSwitchWidth + v5 + v4;
        }
        switch(this.getGravity() & 0x70) {
            case 16: {
                v8 = (this.getPaddingTop() + this.getHeight() - this.getPaddingBottom()) / 2 - this.mSwitchHeight / 2;
                v9 = this.mSwitchHeight + v8;
                break;
            }
            case 80: {
                v9 = this.getHeight() - this.getPaddingBottom();
                v8 = v9 - this.mSwitchHeight;
                break;
            }
            default: {
                v8 = this.getPaddingTop();
                v9 = this.mSwitchHeight + v8;
            }
        }
        this.mSwitchLeft = v6;
        this.mSwitchTop = v8;
        this.mSwitchBottom = v9;
        this.mSwitchRight = v7;
    }

    @Override  // android.widget.TextView
    public void onMeasure(int v, int v1) {
        int v4;
        int v3;
        if(this.mShowText) {
            if(this.mOnLayout == null) {
                this.mOnLayout = this.makeLayout(this.mTextOnTransformed);
            }
            if(this.mOffLayout == null) {
                this.mOffLayout = this.makeLayout(this.mTextOffTransformed);
            }
        }
        Rect rect0 = this.mTempRect;
        Drawable drawable0 = this.mThumbDrawable;
        int v2 = 0;
        if(drawable0 == null) {
            v3 = 0;
            v4 = 0;
        }
        else {
            drawable0.getPadding(rect0);
            v3 = this.mThumbDrawable.getIntrinsicWidth() - rect0.left - rect0.right;
            v4 = this.mThumbDrawable.getIntrinsicHeight();
        }
        this.mThumbWidth = Math.max((this.mShowText ? Math.max(this.mOnLayout.getWidth(), this.mOffLayout.getWidth()) + this.mThumbTextPadding * 2 : 0), v3);
        Drawable drawable1 = this.mTrackDrawable;
        if(drawable1 == null) {
            rect0.setEmpty();
        }
        else {
            drawable1.getPadding(rect0);
            v2 = this.mTrackDrawable.getIntrinsicHeight();
        }
        int v5 = rect0.left;
        int v6 = rect0.right;
        Drawable drawable2 = this.mThumbDrawable;
        if(drawable2 != null) {
            Rect rect1 = DrawableUtils.getOpticalBounds(drawable2);
            v5 = Math.max(v5, rect1.left);
            v6 = Math.max(v6, rect1.right);
        }
        int v7 = Math.max(v2, v4);
        this.mSwitchWidth = this.mEnforceSwitchWidth ? Math.max(this.mSwitchMinWidth, this.mThumbWidth * 2 + v5 + v6) : this.mSwitchMinWidth;
        this.mSwitchHeight = v7;
        super.onMeasure(v, v1);
        if(this.getMeasuredHeight() < v7) {
            this.setMeasuredDimension(this.getMeasuredWidthAndState(), v7);
        }
    }

    @Override  // android.view.View
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onPopulateAccessibilityEvent(accessibilityEvent0);
        CharSequence charSequence0 = this.isChecked() ? this.mTextOn : this.mTextOff;
        if(charSequence0 != null) {
            accessibilityEvent0.getText().add(charSequence0);
        }
    }

    @Override  // android.widget.TextView
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        float f2;
        this.mVelocityTracker.addMovement(motionEvent0);
        int v = motionEvent0.getActionMasked();
        if(v == 0) {
            float f6 = motionEvent0.getX();
            float f7 = motionEvent0.getY();
            if(this.isEnabled() && this.hitThumb(f6, f7)) {
                this.mTouchMode = 1;
                this.mTouchX = f6;
                this.mTouchY = f7;
            }
        }
        else {
            switch(v) {
                case 1: {
                label_6:
                    if(this.mTouchMode == 2) {
                        this.stopDrag(motionEvent0);
                        super.onTouchEvent(motionEvent0);
                        return true;
                    }
                    this.mTouchMode = 0;
                    this.mVelocityTracker.clear();
                    return super.onTouchEvent(motionEvent0);
                }
                case 2: {
                    break;
                }
                default: {
                    if(v != 3) {
                        return super.onTouchEvent(motionEvent0);
                    }
                    goto label_6;
                }
            }
            switch(this.mTouchMode) {
                case 1: {
                    goto label_29;
                }
                case 2: {
                    goto label_15;
                }
            }
            return super.onTouchEvent(motionEvent0);
        label_15:
            float f = motionEvent0.getX();
            int v1 = this.getThumbScrollRange();
            float f1 = f - this.mTouchX;
            if(v1 == 0) {
                f2 = f1 > 0.0f ? 1.0f : -1.0f;
            }
            else {
                f2 = f1 / ((float)v1);
            }
            if(ViewUtils.isLayoutRtl(this)) {
                f2 = -f2;
            }
            float f3 = SwitchCompat.constrain(this.mThumbPosition + f2, 0.0f, 1.0f);
            if(f3 != this.mThumbPosition) {
                this.mTouchX = f;
                this.setThumbPosition(f3);
            }
            return true;
        label_29:
            float f4 = motionEvent0.getX();
            float f5 = motionEvent0.getY();
            if(Math.abs(f4 - this.mTouchX) > ((float)this.mTouchSlop) || Math.abs(f5 - this.mTouchY) > ((float)this.mTouchSlop)) {
                this.mTouchMode = 2;
                this.getParent().requestDisallowInterceptTouchEvent(true);
                this.mTouchX = f4;
                this.mTouchY = f5;
                return true;
            }
        }
        return super.onTouchEvent(motionEvent0);
    }

    @Override  // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        this.getEmojiTextViewHelper().setAllCaps(z);
    }

    @Override  // android.widget.CompoundButton
    public void setChecked(boolean z) {
        super.setChecked(z);
        boolean z1 = this.isChecked();
        if(z1) {
            this.setOnStateDescriptionOnRAndAbove();
        }
        else {
            this.setOffStateDescriptionOnRAndAbove();
        }
        if(this.getWindowToken() != null && this.isLaidOut()) {
            this.animateThumbToCheckedState(z1);
            return;
        }
        this.cancelPositionAnimator();
        this.setThumbPosition((z1 ? 1.0f : 0.0f));
    }

    @Override  // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionMode$Callback0) {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, actionMode$Callback0));
    }

    @Override  // androidx.appcompat.widget.EmojiCompatConfigurationView
    public void setEmojiCompatEnabled(boolean z) {
        this.getEmojiTextViewHelper().setEnabled(z);
        this.setTextOnInternal(this.mTextOn);
        this.setTextOffInternal(this.mTextOff);
        this.requestLayout();
    }

    protected final void setEnforceSwitchWidth(boolean z) {
        this.mEnforceSwitchWidth = z;
        this.invalidate();
    }

    @Override  // android.widget.TextView
    public void setFilters(InputFilter[] arr_inputFilter) {
        super.setFilters(this.getEmojiTextViewHelper().getFilters(arr_inputFilter));
    }

    private void setOffStateDescriptionOnRAndAbove() {
        if(Build.VERSION.SDK_INT >= 30) {
            CharSequence charSequence0 = this.mTextOff;
            if(charSequence0 == null) {
                charSequence0 = this.getResources().getString(string.abc_capital_off);
            }
            ViewCompat.setStateDescription(this, charSequence0);
        }
    }

    private void setOnStateDescriptionOnRAndAbove() {
        if(Build.VERSION.SDK_INT >= 30) {
            CharSequence charSequence0 = this.mTextOn;
            if(charSequence0 == null) {
                charSequence0 = this.getResources().getString(string.abc_capital_on);
            }
            ViewCompat.setStateDescription(this, charSequence0);
        }
    }

    public void setShowText(boolean z) {
        if(this.mShowText != z) {
            this.mShowText = z;
            this.requestLayout();
            if(z) {
                this.setupEmojiCompatLoadCallback();
            }
        }
    }

    public void setSplitTrack(boolean z) {
        this.mSplitTrack = z;
        this.invalidate();
    }

    public void setSwitchMinWidth(int v) {
        this.mSwitchMinWidth = v;
        this.requestLayout();
    }

    public void setSwitchPadding(int v) {
        this.mSwitchPadding = v;
        this.requestLayout();
    }

    public void setSwitchTextAppearance(Context context0, int v) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, v, styleable.TextAppearance);
        ColorStateList colorStateList0 = tintTypedArray0.getColorStateList(styleable.TextAppearance_android_textColor);
        this.mTextColors = colorStateList0 == null ? this.getTextColors() : colorStateList0;
        int v1 = tintTypedArray0.getDimensionPixelSize(styleable.TextAppearance_android_textSize, 0);
        if(v1 != 0 && ((float)v1) != this.mTextPaint.getTextSize()) {
            this.mTextPaint.setTextSize(((float)v1));
            this.requestLayout();
        }
        this.setSwitchTypefaceByIndex(tintTypedArray0.getInt(styleable.TextAppearance_android_typeface, -1), tintTypedArray0.getInt(styleable.TextAppearance_android_textStyle, -1));
        this.mSwitchTransformationMethod = tintTypedArray0.getBoolean(styleable.TextAppearance_textAllCaps, false) ? new AllCapsTransformationMethod(this.getContext()) : null;
        this.setTextOnInternal(this.mTextOn);
        this.setTextOffInternal(this.mTextOff);
        tintTypedArray0.recycle();
    }

    public void setSwitchTypeface(Typeface typeface0) {
        if(this.mTextPaint.getTypeface() != null && !this.mTextPaint.getTypeface().equals(typeface0) || this.mTextPaint.getTypeface() == null && typeface0 != null) {
            this.mTextPaint.setTypeface(typeface0);
            this.requestLayout();
            this.invalidate();
        }
    }

    public void setSwitchTypeface(Typeface typeface0, int v) {
        float f = 0.0f;
        boolean z = false;
        if(v > 0) {
            Typeface typeface1 = typeface0 == null ? Typeface.defaultFromStyle(v) : Typeface.create(typeface0, v);
            this.setSwitchTypeface(typeface1);
            int v1 = ~(typeface1 == null ? 0 : typeface1.getStyle()) & v;
            TextPaint textPaint0 = this.mTextPaint;
            if((v1 & 1) != 0) {
                z = true;
            }
            textPaint0.setFakeBoldText(z);
            TextPaint textPaint1 = this.mTextPaint;
            if((v1 & 2) != 0) {
                f = -0.25f;
            }
            textPaint1.setTextSkewX(f);
            return;
        }
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        this.setSwitchTypeface(typeface0);
    }

    private void setSwitchTypefaceByIndex(int v, int v1) {
        Typeface typeface0;
        switch(v) {
            case 1: {
                typeface0 = Typeface.SANS_SERIF;
                break;
            }
            case 2: {
                typeface0 = Typeface.SERIF;
                break;
            }
            case 3: {
                typeface0 = Typeface.MONOSPACE;
                break;
            }
            default: {
                typeface0 = null;
            }
        }
        this.setSwitchTypeface(typeface0, v1);
    }

    public void setTextOff(CharSequence charSequence0) {
        this.setTextOffInternal(charSequence0);
        this.requestLayout();
        if(!this.isChecked()) {
            this.setOffStateDescriptionOnRAndAbove();
        }
    }

    private void setTextOffInternal(CharSequence charSequence0) {
        this.mTextOff = charSequence0;
        this.mTextOffTransformed = this.doTransformForOnOffText(charSequence0);
        this.mOffLayout = null;
        if(this.mShowText) {
            this.setupEmojiCompatLoadCallback();
        }
    }

    public void setTextOn(CharSequence charSequence0) {
        this.setTextOnInternal(charSequence0);
        this.requestLayout();
        if(this.isChecked()) {
            this.setOnStateDescriptionOnRAndAbove();
        }
    }

    private void setTextOnInternal(CharSequence charSequence0) {
        this.mTextOn = charSequence0;
        this.mTextOnTransformed = this.doTransformForOnOffText(charSequence0);
        this.mOnLayout = null;
        if(this.mShowText) {
            this.setupEmojiCompatLoadCallback();
        }
    }

    public void setThumbDrawable(Drawable drawable0) {
        Drawable drawable1 = this.mThumbDrawable;
        if(drawable1 != null) {
            drawable1.setCallback(null);
        }
        this.mThumbDrawable = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
        }
        this.requestLayout();
    }

    void setThumbPosition(float f) {
        this.mThumbPosition = f;
        this.invalidate();
    }

    public void setThumbResource(int v) {
        this.setThumbDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setThumbTextPadding(int v) {
        this.mThumbTextPadding = v;
        this.requestLayout();
    }

    public void setThumbTintList(ColorStateList colorStateList0) {
        this.mThumbTintList = colorStateList0;
        this.mHasThumbTint = true;
        this.applyThumbTint();
    }

    public void setThumbTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mThumbTintMode = porterDuff$Mode0;
        this.mHasThumbTintMode = true;
        this.applyThumbTint();
    }

    public void setTrackDrawable(Drawable drawable0) {
        Drawable drawable1 = this.mTrackDrawable;
        if(drawable1 != null) {
            drawable1.setCallback(null);
        }
        this.mTrackDrawable = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
        }
        this.requestLayout();
    }

    public void setTrackResource(int v) {
        this.setTrackDrawable(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setTrackTintList(ColorStateList colorStateList0) {
        this.mTrackTintList = colorStateList0;
        this.mHasTrackTint = true;
        this.applyTrackTint();
    }

    public void setTrackTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mTrackTintMode = porterDuff$Mode0;
        this.mHasTrackTintMode = true;
        this.applyTrackTint();
    }

    private void setupEmojiCompatLoadCallback() {
        if(this.mEmojiCompatInitCallback == null && this.mAppCompatEmojiTextHelper.isEnabled() && false) {
            EmojiCompat emojiCompat0 = EmojiCompat.get();
            switch(emojiCompat0.getLoadState()) {
                case 0: 
                case 3: {
                    EmojiCompatInitCallback switchCompat$EmojiCompatInitCallback0 = new EmojiCompatInitCallback(this);
                    this.mEmojiCompatInitCallback = switchCompat$EmojiCompatInitCallback0;
                    emojiCompat0.registerInitCallback(switchCompat$EmojiCompatInitCallback0);
                    break;
                }
            }
        }
    }

    private void stopDrag(MotionEvent motionEvent0) {
        this.mTouchMode = 0;
        boolean z = true;
        boolean z1 = motionEvent0.getAction() == 1 && this.isEnabled();
        boolean z2 = this.isChecked();
        if(z1) {
            this.mVelocityTracker.computeCurrentVelocity(1000);
            float f = this.mVelocityTracker.getXVelocity();
            if(Math.abs(f) <= ((float)this.mMinFlingVelocity)) {
                z = this.getTargetCheckedState();
            }
            else if(!ViewUtils.isLayoutRtl(this)) {
                if(f <= 0.0f) {
                    z = false;
                }
            }
            else if(f >= 0.0f) {
                z = false;
            }
        }
        else {
            z = z2;
        }
        if(z != z2) {
            this.playSoundEffect(0);
        }
        this.setChecked(z);
        this.cancelSuperTouch(motionEvent0);
    }

    @Override  // android.widget.CompoundButton
    public void toggle() {
        this.setChecked(!this.isChecked());
    }

    @Override  // android.widget.CompoundButton
    protected boolean verifyDrawable(Drawable drawable0) {
        return super.verifyDrawable(drawable0) || drawable0 == this.mThumbDrawable || drawable0 == this.mTrackDrawable;
    }
}

