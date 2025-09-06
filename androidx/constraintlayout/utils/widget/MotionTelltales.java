package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R.styleable;

public class MotionTelltales extends MockView {
    private static final String TAG = "MotionTelltales";
    Matrix mInvertMatrix;
    MotionLayout mMotionLayout;
    private Paint mPaintTelltales;
    int mTailColor;
    float mTailScale;
    float[] mVelocity;
    int mVelocityMode;

    public MotionTelltales(Context context0) {
        super(context0);
        this.mPaintTelltales = new Paint();
        this.mVelocity = new float[2];
        this.mInvertMatrix = new Matrix();
        this.mVelocityMode = 0;
        this.mTailColor = 0xFFFF00FF;
        this.mTailScale = 0.25f;
        this.init(context0, null);
    }

    public MotionTelltales(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mPaintTelltales = new Paint();
        this.mVelocity = new float[2];
        this.mInvertMatrix = new Matrix();
        this.mVelocityMode = 0;
        this.mTailColor = 0xFFFF00FF;
        this.mTailScale = 0.25f;
        this.init(context0, attributeSet0);
    }

    public MotionTelltales(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mPaintTelltales = new Paint();
        this.mVelocity = new float[2];
        this.mInvertMatrix = new Matrix();
        this.mVelocityMode = 0;
        this.mTailColor = 0xFFFF00FF;
        this.mTailScale = 0.25f;
        this.init(context0, attributeSet0);
    }

    private void init(Context context0, AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.MotionTelltales);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.MotionTelltales_telltales_tailColor) {
                    this.mTailColor = typedArray0.getColor(v2, this.mTailColor);
                }
                else if(v2 == styleable.MotionTelltales_telltales_velocityMode) {
                    this.mVelocityMode = typedArray0.getInt(v2, this.mVelocityMode);
                }
                else if(v2 == styleable.MotionTelltales_telltales_tailScale) {
                    this.mTailScale = typedArray0.getFloat(v2, this.mTailScale);
                }
            }
            typedArray0.recycle();
        }
        this.mPaintTelltales.setColor(this.mTailColor);
        this.mPaintTelltales.setStrokeWidth(5.0f);
    }

    @Override  // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override  // androidx.constraintlayout.utils.widget.MockView
    public void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        this.getMatrix().invert(this.mInvertMatrix);
        if(this.mMotionLayout == null) {
            ViewParent viewParent0 = this.getParent();
            if(viewParent0 instanceof MotionLayout) {
                this.mMotionLayout = (MotionLayout)viewParent0;
            }
        }
        else {
            int v = this.getWidth();
            int v1 = this.getHeight();
            float[] arr_f = {0.1f, 0.25f, 0.5f, 0.75f, 0.9f};
            for(int v2 = 0; v2 < 5; ++v2) {
                float f = arr_f[v2];
                for(int v3 = 0; v3 < 5; ++v3) {
                    float f1 = arr_f[v3];
                    this.mMotionLayout.getViewVelocity(this, f1, f, this.mVelocity, this.mVelocityMode);
                    this.mInvertMatrix.mapVectors(this.mVelocity);
                    float f2 = ((float)v) * f1;
                    float f3 = ((float)v1) * f;
                    float[] arr_f1 = this.mVelocity;
                    float f4 = this.mTailScale;
                    float f5 = f2 - arr_f1[0] * f4;
                    float f6 = f3 - arr_f1[1] * f4;
                    this.mInvertMatrix.mapVectors(arr_f1);
                    canvas0.drawLine(f2, f3, f5, f6, this.mPaintTelltales);
                }
            }
        }
    }

    @Override  // android.view.View
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        this.postInvalidate();
    }

    public void setText(CharSequence charSequence0) {
        this.mText = charSequence0.toString();
        this.requestLayout();
    }
}

