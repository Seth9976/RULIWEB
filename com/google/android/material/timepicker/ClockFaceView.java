package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.color;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.resources.MaterialResources;
import java.util.Arrays;

class ClockFaceView extends RadialViewGroup implements OnRotateListener {
    private static final float EPSILON = 0.001f;
    private static final int INITIAL_CAPACITY = 12;
    private static final String VALUE_PLACEHOLDER = "";
    private final int clockHandPadding;
    private final ClockHandView clockHandView;
    private final int clockSize;
    private float currentHandRotation;
    private final int[] gradientColors;
    private final float[] gradientPositions;
    private final int minimumHeight;
    private final int minimumWidth;
    private final RectF scratch;
    private final Rect scratchLineBounds;
    private final ColorStateList textColor;
    private final SparseArray textViewPool;
    private final Rect textViewRect;
    private final AccessibilityDelegateCompat valueAccessibilityDelegate;
    private String[] values;

    public ClockFaceView(Context context0) {
        this(context0, null);
    }

    public ClockFaceView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialClockStyle);
    }

    public ClockFaceView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        this.scratchLineBounds = new Rect();
        this.textViewPool = new SparseArray();
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ClockFaceView, v, style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources0 = this.getResources();
        ColorStateList colorStateList0 = MaterialResources.getColorStateList(context0, typedArray0, styleable.ClockFaceView_clockNumberTextColor);
        this.textColor = colorStateList0;
        LayoutInflater.from(context0).inflate(layout.material_clockface_view, this, true);
        ClockHandView clockHandView0 = (ClockHandView)this.findViewById(id.material_clock_hand);
        this.clockHandView = clockHandView0;
        this.clockHandPadding = resources0.getDimensionPixelSize(dimen.material_clock_hand_padding);
        int v1 = colorStateList0.getDefaultColor();
        int v2 = colorStateList0.getColorForState(new int[]{0x10100A1}, v1);
        this.gradientColors = new int[]{v2, v2, colorStateList0.getDefaultColor()};
        clockHandView0.addOnRotateListener(this);
        int v3 = AppCompatResources.getColorStateList(context0, color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateList1 = MaterialResources.getColorStateList(context0, typedArray0, styleable.ClockFaceView_clockFaceBackgroundColor);
        if(colorStateList1 != null) {
            v3 = colorStateList1.getDefaultColor();
        }
        this.setBackgroundColor(v3);
        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override  // android.view.ViewTreeObserver$OnPreDrawListener
            public boolean onPreDraw() {
                if(!ClockFaceView.this.isShown()) {
                    return true;
                }
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                int v = ClockFaceView.this.getHeight();
                ClockFaceView.this.setRadius(v / 2 - ClockFaceView.this.clockHandView.getSelectorRadius() - ClockFaceView.this.clockHandPadding);
                return true;
            }
        });
        this.setFocusable(true);
        typedArray0.recycle();
        this.valueAccessibilityDelegate = new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                int v = (int)(((Integer)view0.getTag(id.material_value_index)));
                if(v > 0) {
                    accessibilityNodeInfoCompat0.setTraversalAfter(((View)ClockFaceView.this.textViewPool.get(v - 1)));
                }
                accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(0, 1, v, 1, false, view0.isSelected()));
                accessibilityNodeInfoCompat0.setClickable(true);
                accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_CLICK);
            }

            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
                if(v == 16) {
                    long v1 = SystemClock.uptimeMillis();
                    view0.getHitRect(ClockFaceView.this.textViewRect);
                    float f = (float)ClockFaceView.this.textViewRect.centerX();
                    float f1 = (float)ClockFaceView.this.textViewRect.centerY();
                    MotionEvent motionEvent0 = MotionEvent.obtain(v1, v1, 0, f, f1, 0);
                    ClockFaceView.this.clockHandView.onTouchEvent(motionEvent0);
                    MotionEvent motionEvent1 = MotionEvent.obtain(v1, v1, 1, f, f1, 0);
                    ClockFaceView.this.clockHandView.onTouchEvent(motionEvent1);
                    return true;
                }
                return super.performAccessibilityAction(view0, v, bundle0);
            }
        };
        String[] arr_s = new String[12];
        Arrays.fill(arr_s, "");
        this.setValues(arr_s, 0);
        this.minimumHeight = resources0.getDimensionPixelSize(dimen.material_time_picker_minimum_screen_height);
        this.minimumWidth = resources0.getDimensionPixelSize(dimen.material_time_picker_minimum_screen_width);
        this.clockSize = resources0.getDimensionPixelSize(dimen.material_clock_size);
    }

    private void findIntersectingTextView() {
        RectF rectF0 = this.clockHandView.getCurrentSelectorBox();
        TextView textView0 = this.getSelectedTextView(rectF0);
        for(int v = 0; v < this.textViewPool.size(); ++v) {
            TextView textView1 = (TextView)this.textViewPool.get(v);
            if(textView1 != null) {
                textView1.setSelected(textView1 == textView0);
                RadialGradient radialGradient0 = this.getGradientForTextView(rectF0, textView1);
                textView1.getPaint().setShader(radialGradient0);
                textView1.invalidate();
            }
        }
    }

    int getCurrentLevel() {
        return this.clockHandView.getCurrentLevel();
    }

    private RadialGradient getGradientForTextView(RectF rectF0, TextView textView0) {
        textView0.getHitRect(this.textViewRect);
        this.scratch.set(this.textViewRect);
        textView0.getLineBounds(0, this.scratchLineBounds);
        this.scratch.inset(((float)this.scratchLineBounds.left), ((float)this.scratchLineBounds.top));
        return RectF.intersects(rectF0, this.scratch) ? new RadialGradient(rectF0.centerX() - this.scratch.left, rectF0.centerY() - this.scratch.top, rectF0.width() * 0.5f, this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP) : null;
    }

    private TextView getSelectedTextView(RectF rectF0) {
        float f = 3.402823E+38f;
        TextView textView0 = null;
        for(int v = 0; v < this.textViewPool.size(); ++v) {
            TextView textView1 = (TextView)this.textViewPool.get(v);
            if(textView1 != null) {
                textView1.getHitRect(this.textViewRect);
                this.scratch.set(this.textViewRect);
                this.scratch.union(rectF0);
                float f1 = this.scratch.width() * this.scratch.height();
                if(f1 < f) {
                    textView0 = textView1;
                    f = f1;
                }
            }
        }
        return textView0;
    }

    private static float max3(float f, float f1, float f2) {
        return Math.max(Math.max(f, f1), f2);
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0).setCollectionInfo(CollectionInfoCompat.obtain(1, this.values.length, false, 1));
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        this.findIntersectingTextView();
    }

    @Override  // androidx.constraintlayout.widget.ConstraintLayout
    protected void onMeasure(int v, int v1) {
        DisplayMetrics displayMetrics0 = this.getResources().getDisplayMetrics();
        int v2 = (int)(((float)this.clockSize) / ClockFaceView.max3(((float)this.minimumHeight) / ((float)displayMetrics0.heightPixels), ((float)this.minimumWidth) / ((float)displayMetrics0.widthPixels), 1.0f));
        int v3 = View.MeasureSpec.makeMeasureSpec(v2, 0x40000000);
        this.setMeasuredDimension(v2, v2);
        super.onMeasure(v3, v3);
    }

    @Override  // com.google.android.material.timepicker.ClockHandView$OnRotateListener
    public void onRotate(float f, boolean z) {
        if(Math.abs(this.currentHandRotation - f) > 0.001f) {
            this.currentHandRotation = f;
            this.findIntersectingTextView();
        }
    }

    void setCurrentLevel(int v) {
        this.clockHandView.setCurrentLevel(v);
    }

    public void setHandRotation(float f) {
        this.clockHandView.setHandRotation(f);
        this.findIntersectingTextView();
    }

    @Override  // com.google.android.material.timepicker.RadialViewGroup
    public void setRadius(int v) {
        if(v != this.getRadius()) {
            super.setRadius(v);
            int v1 = this.getRadius();
            this.clockHandView.setCircleRadius(v1);
        }
    }

    public void setValues(String[] arr_s, int v) {
        this.values = arr_s;
        this.updateTextViews(v);
    }

    @Override  // com.google.android.material.timepicker.RadialViewGroup
    protected void updateLayoutParams() {
        super.updateLayoutParams();
        for(int v = 0; v < this.textViewPool.size(); ++v) {
            ((TextView)this.textViewPool.get(v)).setVisibility(0);
        }
    }

    private void updateTextViews(int v) {
        LayoutInflater layoutInflater0 = LayoutInflater.from(this.getContext());
        int v1 = this.textViewPool.size();
        boolean z = false;
        for(int v2 = 0; v2 < Math.max(this.values.length, v1); ++v2) {
            TextView textView0 = (TextView)this.textViewPool.get(v2);
            if(v2 >= this.values.length) {
                this.removeView(textView0);
                this.textViewPool.remove(v2);
            }
            else {
                if(textView0 == null) {
                    textView0 = (TextView)layoutInflater0.inflate(layout.material_clockface_textview, this, false);
                    this.textViewPool.put(v2, textView0);
                    this.addView(textView0);
                }
                textView0.setText(this.values[v2]);
                textView0.setTag(id.material_value_index, v2);
                int v3 = v2 / 12 + 1;
                textView0.setTag(id.material_clock_level, v3);
                if(v3 > 1) {
                    z = true;
                }
                ViewCompat.setAccessibilityDelegate(textView0, this.valueAccessibilityDelegate);
                textView0.setTextColor(this.textColor);
                if(v != 0) {
                    textView0.setContentDescription(this.getResources().getString(v, new Object[]{this.values[v2]}));
                }
            }
        }
        this.clockHandView.setMultiLevel(z);
    }
}

