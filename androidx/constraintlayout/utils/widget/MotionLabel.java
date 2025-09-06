package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.R.attr;
import androidx.constraintlayout.motion.widget.FloatLayout;
import androidx.constraintlayout.widget.R.styleable;
import java.util.Objects;

public class MotionLabel extends View implements FloatLayout {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    static final String TAG = "MotionLabel";
    private boolean mAutoSize;
    private int mAutoSizeTextType;
    float mBackgroundPanX;
    float mBackgroundPanY;
    private float mBaseTextSize;
    private float mDeltaLeft;
    private float mFloatHeight;
    private float mFloatWidth;
    private String mFontFamily;
    private int mGravity;
    private Layout mLayout;
    boolean mNotBuilt;
    Matrix mOutlinePositionMatrix;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    TextPaint mPaint;
    Paint mPaintCache;
    float mPaintTextSize;
    Path mPath;
    RectF mRect;
    float mRotate;
    private float mRound;
    private float mRoundPercent;
    private int mStyleIndex;
    Paint mTempPaint;
    Rect mTempRect;
    private String mText;
    private Drawable mTextBackground;
    private Bitmap mTextBackgroundBitmap;
    private Rect mTextBounds;
    private int mTextFillColor;
    private int mTextOutlineColor;
    private float mTextOutlineThickness;
    private float mTextPanX;
    private float mTextPanY;
    private BitmapShader mTextShader;
    private Matrix mTextShaderMatrix;
    private float mTextSize;
    private int mTextureEffect;
    private float mTextureHeight;
    private float mTextureWidth;
    private int mTypefaceIndex;
    private boolean mUseOutline;
    ViewOutlineProvider mViewOutlineProvider;
    float mZoom;

    public MotionLabel(Context context0) {
        super(context0);
        this.mPaint = new TextPaint();
        this.mPath = new Path();
        this.mTextFillColor = 0xFFFF;
        this.mTextOutlineColor = 0xFFFF;
        this.mUseOutline = false;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mTextSize = 48.0f;
        this.mBaseTextSize = NaNf;
        this.mTextOutlineThickness = 0.0f;
        this.mText = "Hello World";
        this.mNotBuilt = true;
        this.mTextBounds = new Rect();
        this.mPaddingLeft = 1;
        this.mPaddingRight = 1;
        this.mPaddingTop = 1;
        this.mPaddingBottom = 1;
        this.mGravity = 0x800033;
        this.mAutoSizeTextType = 0;
        this.mAutoSize = false;
        this.mTextureHeight = NaNf;
        this.mTextureWidth = NaNf;
        this.mTextPanX = 0.0f;
        this.mTextPanY = 0.0f;
        this.mPaintCache = new Paint();
        this.mTextureEffect = 0;
        this.mBackgroundPanX = NaNf;
        this.mBackgroundPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, null);
    }

    public MotionLabel(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mPaint = new TextPaint();
        this.mPath = new Path();
        this.mTextFillColor = 0xFFFF;
        this.mTextOutlineColor = 0xFFFF;
        this.mUseOutline = false;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mTextSize = 48.0f;
        this.mBaseTextSize = NaNf;
        this.mTextOutlineThickness = 0.0f;
        this.mText = "Hello World";
        this.mNotBuilt = true;
        this.mTextBounds = new Rect();
        this.mPaddingLeft = 1;
        this.mPaddingRight = 1;
        this.mPaddingTop = 1;
        this.mPaddingBottom = 1;
        this.mGravity = 0x800033;
        this.mAutoSizeTextType = 0;
        this.mAutoSize = false;
        this.mTextureHeight = NaNf;
        this.mTextureWidth = NaNf;
        this.mTextPanX = 0.0f;
        this.mTextPanY = 0.0f;
        this.mPaintCache = new Paint();
        this.mTextureEffect = 0;
        this.mBackgroundPanX = NaNf;
        this.mBackgroundPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, attributeSet0);
    }

    public MotionLabel(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mPaint = new TextPaint();
        this.mPath = new Path();
        this.mTextFillColor = 0xFFFF;
        this.mTextOutlineColor = 0xFFFF;
        this.mUseOutline = false;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mTextSize = 48.0f;
        this.mBaseTextSize = NaNf;
        this.mTextOutlineThickness = 0.0f;
        this.mText = "Hello World";
        this.mNotBuilt = true;
        this.mTextBounds = new Rect();
        this.mPaddingLeft = 1;
        this.mPaddingRight = 1;
        this.mPaddingTop = 1;
        this.mPaddingBottom = 1;
        this.mGravity = 0x800033;
        this.mAutoSizeTextType = 0;
        this.mAutoSize = false;
        this.mTextureHeight = NaNf;
        this.mTextureWidth = NaNf;
        this.mTextPanX = 0.0f;
        this.mTextPanY = 0.0f;
        this.mPaintCache = new Paint();
        this.mTextureEffect = 0;
        this.mBackgroundPanX = NaNf;
        this.mBackgroundPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, attributeSet0);
    }

    private void adjustTexture(float f, float f1, float f2, float f3) {
        if(this.mTextShaderMatrix == null) {
            return;
        }
        this.mFloatWidth = f2 - f;
        this.mFloatHeight = f3 - f1;
        this.updateShaderMatrix();
    }

    Bitmap blur(Bitmap bitmap0, int v) {
        int v1 = bitmap0.getWidth() / 2;
        int v2 = bitmap0.getHeight() / 2;
        Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap0, v1, v2, true);
        for(int v3 = 0; v3 < v && v1 >= 0x20 && v2 >= 0x20; ++v3) {
            v1 /= 2;
            v2 /= 2;
            bitmap1 = Bitmap.createScaledBitmap(bitmap1, v1, v2, true);
        }
        return bitmap1;
    }

    void buildShape(float f) {
        if(!this.mUseOutline && f == 1.0f) {
            return;
        }
        this.mPath.reset();
        String s = this.mText;
        int v = s.length();
        this.mPaint.getTextBounds(s, 0, v, this.mTextBounds);
        this.mPaint.getTextPath(s, 0, v, 0.0f, 0.0f, this.mPath);
        if(f != 1.0f) {
            Log.v("MotionLabel", ".(null:-1) buildShape() scale " + f);
            Matrix matrix0 = new Matrix();
            matrix0.postScale(f, f);
            this.mPath.transform(matrix0);
        }
        --this.mTextBounds.right;
        ++this.mTextBounds.left;
        ++this.mTextBounds.bottom;
        --this.mTextBounds.top;
        RectF rectF0 = new RectF();
        rectF0.bottom = (float)this.getHeight();
        rectF0.right = (float)this.getWidth();
        this.mNotBuilt = false;
    }

    private float getHorizontalOffset() {
        float f = Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize;
        float f1 = this.mPaint.measureText(this.mText, 0, this.mText.length());
        return Float.isNaN(this.mFloatWidth) ? (((float)this.getMeasuredWidth()) - ((float)this.getPaddingLeft()) - ((float)this.getPaddingRight()) - f * f1) * (this.mTextPanX + 1.0f) / 2.0f : (this.mFloatWidth - ((float)this.getPaddingLeft()) - ((float)this.getPaddingRight()) - f * f1) * (this.mTextPanX + 1.0f) / 2.0f;
    }

    public float getRound() {
        return this.mRound;
    }

    public float getRoundPercent() {
        return this.mRoundPercent;
    }

    public float getScaleFromTextSize() {
        return this.mBaseTextSize;
    }

    public float getTextBackgroundPanX() {
        return this.mBackgroundPanX;
    }

    public float getTextBackgroundPanY() {
        return this.mBackgroundPanY;
    }

    public float getTextBackgroundRotate() {
        return this.mRotate;
    }

    public float getTextBackgroundZoom() {
        return this.mZoom;
    }

    public int getTextOutlineColor() {
        return this.mTextOutlineColor;
    }

    public float getTextPanX() {
        return this.mTextPanX;
    }

    public float getTextPanY() {
        return this.mTextPanY;
    }

    public float getTextureHeight() {
        return this.mTextureHeight;
    }

    public float getTextureWidth() {
        return this.mTextureWidth;
    }

    public Typeface getTypeface() {
        return this.mPaint.getTypeface();
    }

    private float getVerticalOffset() {
        float f = Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize;
        Paint.FontMetrics paint$FontMetrics0 = this.mPaint.getFontMetrics();
        return Float.isNaN(this.mFloatHeight) ? (((float)this.getMeasuredHeight()) - ((float)this.getPaddingTop()) - ((float)this.getPaddingBottom()) - (paint$FontMetrics0.descent - paint$FontMetrics0.ascent) * f) * (1.0f - this.mTextPanY) / 2.0f - f * paint$FontMetrics0.ascent : (this.mFloatHeight - ((float)this.getPaddingTop()) - ((float)this.getPaddingBottom()) - (paint$FontMetrics0.descent - paint$FontMetrics0.ascent) * f) * (1.0f - this.mTextPanY) / 2.0f - f * paint$FontMetrics0.ascent;
    }

    private void init(Context context0, AttributeSet attributeSet0) {
        this.setUpTheme(context0);
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.MotionLabel);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.MotionLabel_android_text) {
                    this.setText(typedArray0.getText(v2));
                }
                else if(v2 == styleable.MotionLabel_android_fontFamily) {
                    this.mFontFamily = typedArray0.getString(v2);
                }
                else if(v2 == styleable.MotionLabel_scaleFromTextSize) {
                    this.mBaseTextSize = (float)typedArray0.getDimensionPixelSize(v2, ((int)this.mBaseTextSize));
                }
                else if(v2 == styleable.MotionLabel_android_textSize) {
                    this.mTextSize = (float)typedArray0.getDimensionPixelSize(v2, ((int)this.mTextSize));
                }
                else if(v2 == styleable.MotionLabel_android_textStyle) {
                    this.mStyleIndex = typedArray0.getInt(v2, this.mStyleIndex);
                }
                else if(v2 == styleable.MotionLabel_android_typeface) {
                    this.mTypefaceIndex = typedArray0.getInt(v2, this.mTypefaceIndex);
                }
                else if(v2 == styleable.MotionLabel_android_textColor) {
                    this.mTextFillColor = typedArray0.getColor(v2, this.mTextFillColor);
                }
                else if(v2 == styleable.MotionLabel_borderRound) {
                    this.mRound = typedArray0.getDimension(v2, this.mRound);
                    this.setRound(this.mRound);
                }
                else if(v2 == styleable.MotionLabel_borderRoundPercent) {
                    this.mRoundPercent = typedArray0.getFloat(v2, this.mRoundPercent);
                    this.setRoundPercent(this.mRoundPercent);
                }
                else if(v2 == styleable.MotionLabel_android_gravity) {
                    this.setGravity(typedArray0.getInt(v2, -1));
                }
                else if(v2 == styleable.MotionLabel_android_autoSizeTextType) {
                    this.mAutoSizeTextType = typedArray0.getInt(v2, 0);
                }
                else if(v2 == styleable.MotionLabel_textOutlineColor) {
                    this.mTextOutlineColor = typedArray0.getInt(v2, this.mTextOutlineColor);
                    this.mUseOutline = true;
                }
                else if(v2 == styleable.MotionLabel_textOutlineThickness) {
                    this.mTextOutlineThickness = typedArray0.getDimension(v2, this.mTextOutlineThickness);
                    this.mUseOutline = true;
                }
                else if(v2 == styleable.MotionLabel_textBackground) {
                    this.mTextBackground = typedArray0.getDrawable(v2);
                    this.mUseOutline = true;
                }
                else if(v2 == styleable.MotionLabel_textBackgroundPanX) {
                    this.mBackgroundPanX = typedArray0.getFloat(v2, this.mBackgroundPanX);
                }
                else if(v2 == styleable.MotionLabel_textBackgroundPanY) {
                    this.mBackgroundPanY = typedArray0.getFloat(v2, this.mBackgroundPanY);
                }
                else if(v2 == styleable.MotionLabel_textPanX) {
                    this.mTextPanX = typedArray0.getFloat(v2, this.mTextPanX);
                }
                else if(v2 == styleable.MotionLabel_textPanY) {
                    this.mTextPanY = typedArray0.getFloat(v2, this.mTextPanY);
                }
                else if(v2 == styleable.MotionLabel_textBackgroundRotate) {
                    this.mRotate = typedArray0.getFloat(v2, this.mRotate);
                }
                else if(v2 == styleable.MotionLabel_textBackgroundZoom) {
                    this.mZoom = typedArray0.getFloat(v2, this.mZoom);
                }
                else if(v2 == styleable.MotionLabel_textureHeight) {
                    this.mTextureHeight = typedArray0.getDimension(v2, this.mTextureHeight);
                }
                else if(v2 == styleable.MotionLabel_textureWidth) {
                    this.mTextureWidth = typedArray0.getDimension(v2, this.mTextureWidth);
                }
                else if(v2 == styleable.MotionLabel_textureEffect) {
                    this.mTextureEffect = typedArray0.getInt(v2, this.mTextureEffect);
                }
            }
            typedArray0.recycle();
        }
        this.setupTexture();
        this.setupPath();
    }

    @Override  // androidx.constraintlayout.motion.widget.FloatLayout
    public void layout(float f, float f1, float f2, float f3) {
        this.mDeltaLeft = f - ((float)(((int)(f + 0.5f))));
        int v = ((int)(f2 + 0.5f)) - ((int)(f + 0.5f));
        int v1 = ((int)(f3 + 0.5f)) - ((int)(f1 + 0.5f));
        float f4 = f2 - f;
        this.mFloatWidth = f4;
        float f5 = f3 - f1;
        this.mFloatHeight = f5;
        this.adjustTexture(f, f1, f2, f3);
        if(this.getMeasuredHeight() != v1 || this.getMeasuredWidth() != v) {
            this.measure(View.MeasureSpec.makeMeasureSpec(v, 0x40000000), View.MeasureSpec.makeMeasureSpec(v1, 0x40000000));
        }
        super.layout(((int)(f + 0.5f)), ((int)(f1 + 0.5f)), ((int)(f2 + 0.5f)), ((int)(f3 + 0.5f)));
        if(this.mAutoSize) {
            if(this.mTempRect == null) {
                this.mTempPaint = new Paint();
                this.mTempRect = new Rect();
                this.mTempPaint.set(this.mPaint);
                this.mPaintTextSize = this.mTempPaint.getTextSize();
            }
            this.mFloatWidth = f4;
            this.mFloatHeight = f5;
            this.mTempPaint.getTextBounds(this.mText, 0, this.mText.length(), this.mTempRect);
            int v2 = this.mTempRect.width();
            float f6 = ((float)this.mTempRect.height()) * 1.3f;
            float f7 = f4 - ((float)this.mPaddingRight) - ((float)this.mPaddingLeft);
            float f8 = f5 - ((float)this.mPaddingBottom) - ((float)this.mPaddingTop);
            if(((float)v2) * f8 > f6 * f7) {
                this.mPaint.setTextSize(this.mPaintTextSize * f7 / ((float)v2));
            }
            else {
                this.mPaint.setTextSize(this.mPaintTextSize * f8 / f6);
            }
            if(this.mUseOutline || !Float.isNaN(this.mBaseTextSize)) {
                this.buildShape((Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize));
            }
        }
    }

    @Override  // android.view.View
    public void layout(int v, int v1, int v2, int v3) {
        super.layout(v, v1, v2, v3);
        boolean z = Float.isNaN(this.mBaseTextSize);
        float f = z ? 1.0f : this.mTextSize / this.mBaseTextSize;
        this.mFloatWidth = (float)(v2 - v);
        this.mFloatHeight = (float)(v3 - v1);
        if(this.mAutoSize) {
            if(this.mTempRect == null) {
                this.mTempPaint = new Paint();
                this.mTempRect = new Rect();
                this.mTempPaint.set(this.mPaint);
                this.mPaintTextSize = this.mTempPaint.getTextSize();
            }
            this.mTempPaint.getTextBounds(this.mText, 0, this.mText.length(), this.mTempRect);
            int v4 = this.mTempRect.width();
            int v5 = (int)(((float)this.mTempRect.height()) * 1.3f);
            float f1 = this.mFloatWidth - ((float)this.mPaddingRight) - ((float)this.mPaddingLeft);
            float f2 = this.mFloatHeight - ((float)this.mPaddingBottom) - ((float)this.mPaddingTop);
            if(!z) {
                f = ((float)v4) * f2 > ((float)v5) * f1 ? f1 / ((float)v4) : f2 / ((float)v5);
            }
            else if(((float)v4) * f2 > ((float)v5) * f1) {
                this.mPaint.setTextSize(this.mPaintTextSize * f1 / ((float)v4));
            }
            else {
                this.mPaint.setTextSize(this.mPaintTextSize * f2 / ((float)v5));
            }
        }
        if(!this.mUseOutline && z) {
            return;
        }
        this.adjustTexture(((float)v), ((float)v1), ((float)v2), ((float)v3));
        this.buildShape(f);
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        float f = Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize;
        super.onDraw(canvas0);
        if(!this.mUseOutline && f == 1.0f) {
            float f1 = (float)this.mPaddingLeft;
            float f2 = this.getHorizontalOffset();
            float f3 = (float)this.mPaddingTop;
            float f4 = this.getVerticalOffset();
            canvas0.drawText(this.mText, this.mDeltaLeft + (f1 + f2), f3 + f4, this.mPaint);
            return;
        }
        if(this.mNotBuilt) {
            this.buildShape(f);
        }
        if(this.mOutlinePositionMatrix == null) {
            this.mOutlinePositionMatrix = new Matrix();
        }
        if(this.mUseOutline) {
            this.mPaintCache.set(this.mPaint);
            this.mOutlinePositionMatrix.reset();
            float f5 = ((float)this.mPaddingLeft) + this.getHorizontalOffset();
            float f6 = ((float)this.mPaddingTop) + this.getVerticalOffset();
            this.mOutlinePositionMatrix.postTranslate(f5, f6);
            this.mOutlinePositionMatrix.preScale(f, f);
            this.mPath.transform(this.mOutlinePositionMatrix);
            if(this.mTextShader == null) {
                this.mPaint.setColor(this.mTextFillColor);
            }
            else {
                this.mPaint.setFilterBitmap(true);
                this.mPaint.setShader(this.mTextShader);
            }
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setStrokeWidth(this.mTextOutlineThickness);
            canvas0.drawPath(this.mPath, this.mPaint);
            if(this.mTextShader != null) {
                this.mPaint.setShader(null);
            }
            this.mPaint.setColor(this.mTextOutlineColor);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mTextOutlineThickness);
            canvas0.drawPath(this.mPath, this.mPaint);
            this.mOutlinePositionMatrix.reset();
            this.mOutlinePositionMatrix.postTranslate(-f5, -f6);
            this.mPath.transform(this.mOutlinePositionMatrix);
            this.mPaint.set(this.mPaintCache);
            return;
        }
        float f7 = ((float)this.mPaddingLeft) + this.getHorizontalOffset();
        float f8 = ((float)this.mPaddingTop) + this.getVerticalOffset();
        this.mOutlinePositionMatrix.reset();
        this.mOutlinePositionMatrix.preTranslate(f7, f8);
        this.mPath.transform(this.mOutlinePositionMatrix);
        this.mPaint.setColor(this.mTextFillColor);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setStrokeWidth(this.mTextOutlineThickness);
        canvas0.drawPath(this.mPath, this.mPaint);
        this.mOutlinePositionMatrix.reset();
        this.mOutlinePositionMatrix.preTranslate(-f7, -f8);
        this.mPath.transform(this.mOutlinePositionMatrix);
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getMode(v1);
        int v4 = View.MeasureSpec.getSize(v);
        int v5 = View.MeasureSpec.getSize(v1);
        this.mAutoSize = false;
        this.mPaddingLeft = this.getPaddingLeft();
        this.mPaddingRight = this.getPaddingRight();
        this.mPaddingTop = this.getPaddingTop();
        this.mPaddingBottom = this.getPaddingBottom();
        if(v2 != 0x40000000 || v3 != 0x40000000) {
            this.mPaint.getTextBounds(this.mText, 0, this.mText.length(), this.mTextBounds);
            if(v2 != 0x40000000) {
                v4 = (int)(((float)this.mTextBounds.width()) + 0.99999f);
            }
            v4 += this.mPaddingLeft + this.mPaddingRight;
            if(v3 != 0x40000000) {
                int v6 = (int)(((float)this.mPaint.getFontMetricsInt(null)) + 0.99999f);
                if(v3 == 0x80000000) {
                    v6 = Math.min(v5, v6);
                }
                v5 = this.mPaddingTop + this.mPaddingBottom + v6;
            }
        }
        else if(this.mAutoSizeTextType != 0) {
            this.mAutoSize = true;
        }
        this.setMeasuredDimension(v4, v5);
    }

    public void setGravity(int v) {
        if((v & 0x800007) == 0) {
            v |= 0x800003;
        }
        if((v & 0x70) == 0) {
            v |= 0x30;
        }
        if(v != this.mGravity) {
            this.invalidate();
        }
        this.mGravity = v;
        switch(v & 0x70) {
            case 0x30: {
                this.mTextPanY = -1.0f;
                break;
            }
            case 80: {
                this.mTextPanY = 1.0f;
                break;
            }
            default: {
                this.mTextPanY = 0.0f;
            }
        }
        if((v & 0x800007) != 3) {
            switch(v & 0x800007) {
                case 5: {
                    this.mTextPanX = 1.0f;
                    return;
                }
                case 0x800003: {
                    break;
                }
                default: {
                    if((v & 0x800007) != 0x800005) {
                        this.mTextPanX = 0.0f;
                        return;
                    }
                    this.mTextPanX = 1.0f;
                    return;
                }
            }
        }
        this.mTextPanX = -1.0f;
    }

    public void setRound(float f) {
        if(Float.isNaN(f)) {
            this.mRound = f;
            float f1 = this.mRoundPercent;
            this.mRoundPercent = -1.0f;
            this.setRoundPercent(f1);
            return;
        }
        boolean z = this.mRound != f;
        this.mRound = f;
        if(f == 0.0f) {
            this.setClipToOutline(false);
        }
        else {
            if(this.mPath == null) {
                this.mPath = new Path();
            }
            if(this.mRect == null) {
                this.mRect = new RectF();
            }
            if(this.mViewOutlineProvider == null) {
                androidx.constraintlayout.utils.widget.MotionLabel.2 motionLabel$20 = new ViewOutlineProvider() {
                    @Override  // android.view.ViewOutlineProvider
                    public void getOutline(View view0, Outline outline0) {
                        outline0.setRoundRect(0, 0, MotionLabel.this.getWidth(), MotionLabel.this.getHeight(), MotionLabel.this.mRound);
                    }
                };
                this.mViewOutlineProvider = motionLabel$20;
                this.setOutlineProvider(motionLabel$20);
            }
            this.setClipToOutline(true);
            int v = this.getWidth();
            int v1 = this.getHeight();
            this.mRect.set(0.0f, 0.0f, ((float)v), ((float)v1));
            this.mPath.reset();
            this.mPath.addRoundRect(this.mRect, this.mRound, this.mRound, Path.Direction.CW);
        }
        if(z) {
            this.invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.mRoundPercent != f;
        this.mRoundPercent = f;
        if(f == 0.0f) {
            this.setClipToOutline(false);
        }
        else {
            if(this.mPath == null) {
                this.mPath = new Path();
            }
            if(this.mRect == null) {
                this.mRect = new RectF();
            }
            if(this.mViewOutlineProvider == null) {
                androidx.constraintlayout.utils.widget.MotionLabel.1 motionLabel$10 = new ViewOutlineProvider() {
                    @Override  // android.view.ViewOutlineProvider
                    public void getOutline(View view0, Outline outline0) {
                        int v = MotionLabel.this.getWidth();
                        int v1 = MotionLabel.this.getHeight();
                        outline0.setRoundRect(0, 0, v, v1, ((float)Math.min(v, v1)) * MotionLabel.this.mRoundPercent / 2.0f);
                    }
                };
                this.mViewOutlineProvider = motionLabel$10;
                this.setOutlineProvider(motionLabel$10);
            }
            this.setClipToOutline(true);
            int v = this.getWidth();
            int v1 = this.getHeight();
            float f1 = ((float)Math.min(v, v1)) * this.mRoundPercent / 2.0f;
            this.mRect.set(0.0f, 0.0f, ((float)v), ((float)v1));
            this.mPath.reset();
            this.mPath.addRoundRect(this.mRect, f1, f1, Path.Direction.CW);
        }
        if(z) {
            this.invalidateOutline();
        }
    }

    public void setScaleFromTextSize(float f) {
        this.mBaseTextSize = f;
    }

    public void setText(CharSequence charSequence0) {
        this.mText = charSequence0.toString();
        this.invalidate();
    }

    public void setTextBackgroundPanX(float f) {
        this.mBackgroundPanX = f;
        this.updateShaderMatrix();
        this.invalidate();
    }

    public void setTextBackgroundPanY(float f) {
        this.mBackgroundPanY = f;
        this.updateShaderMatrix();
        this.invalidate();
    }

    public void setTextBackgroundRotate(float f) {
        this.mRotate = f;
        this.updateShaderMatrix();
        this.invalidate();
    }

    public void setTextBackgroundZoom(float f) {
        this.mZoom = f;
        this.updateShaderMatrix();
        this.invalidate();
    }

    public void setTextFillColor(int v) {
        this.mTextFillColor = v;
        this.invalidate();
    }

    public void setTextOutlineColor(int v) {
        this.mTextOutlineColor = v;
        this.mUseOutline = true;
        this.invalidate();
    }

    public void setTextOutlineThickness(float f) {
        this.mTextOutlineThickness = f;
        this.mUseOutline = true;
        if(Float.isNaN(f)) {
            this.mTextOutlineThickness = 1.0f;
            this.mUseOutline = false;
        }
        this.invalidate();
    }

    public void setTextPanX(float f) {
        this.mTextPanX = f;
        this.invalidate();
    }

    public void setTextPanY(float f) {
        this.mTextPanY = f;
        this.invalidate();
    }

    public void setTextSize(float f) {
        this.mTextSize = f;
        TextPaint textPaint0 = this.mPaint;
        if(!Float.isNaN(this.mBaseTextSize)) {
            f = this.mBaseTextSize;
        }
        textPaint0.setTextSize(f);
        this.buildShape((Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize));
        this.requestLayout();
        this.invalidate();
    }

    public void setTextureHeight(float f) {
        this.mTextureHeight = f;
        this.updateShaderMatrix();
        this.invalidate();
    }

    public void setTextureWidth(float f) {
        this.mTextureWidth = f;
        this.updateShaderMatrix();
        this.invalidate();
    }

    public void setTypeface(Typeface typeface0) {
        if(!Objects.equals(this.mPaint.getTypeface(), typeface0)) {
            this.mPaint.setTypeface(typeface0);
            if(this.mLayout != null) {
                this.mLayout = null;
                this.requestLayout();
                this.invalidate();
            }
        }
    }

    private void setTypefaceFromAttrs(String s, int v, int v1) {
        Typeface typeface0;
        if(s == null) {
            typeface0 = null;
        }
        else {
            typeface0 = Typeface.create(s, v1);
            if(typeface0 != null) {
                this.setTypeface(typeface0);
                return;
            }
        }
        boolean z = true;
        switch(v) {
            case 1: {
                typeface0 = Typeface.SANS_SERIF;
                break;
            }
            case 2: {
                typeface0 = Typeface.SERIF;
                break;
            }
            default: {
                if(v == 3) {
                    typeface0 = Typeface.MONOSPACE;
                }
            }
        }
        float f = 0.0f;
        if(v1 > 0) {
            Typeface typeface1 = typeface0 == null ? Typeface.defaultFromStyle(v1) : Typeface.create(typeface0, v1);
            this.setTypeface(typeface1);
            int v2 = ~(typeface1 == null ? 0 : typeface1.getStyle()) & v1;
            TextPaint textPaint0 = this.mPaint;
            if((v2 & 1) == 0) {
                z = false;
            }
            textPaint0.setFakeBoldText(z);
            TextPaint textPaint1 = this.mPaint;
            if((v2 & 2) != 0) {
                f = -0.25f;
            }
            textPaint1.setTextSkewX(f);
            return;
        }
        this.mPaint.setFakeBoldText(false);
        this.mPaint.setTextSkewX(0.0f);
        this.setTypeface(typeface0);
    }

    private void setUpTheme(Context context0) {
        TypedValue typedValue0 = new TypedValue();
        context0.getTheme().resolveAttribute(attr.colorPrimary, typedValue0, true);
        this.mTextFillColor = typedValue0.data;
        this.mPaint.setColor(typedValue0.data);
    }

    void setupPath() {
        this.mPaddingLeft = this.getPaddingLeft();
        this.mPaddingRight = this.getPaddingRight();
        this.mPaddingTop = this.getPaddingTop();
        this.mPaddingBottom = this.getPaddingBottom();
        this.setTypefaceFromAttrs(this.mFontFamily, this.mTypefaceIndex, this.mStyleIndex);
        this.mPaint.setColor(this.mTextFillColor);
        this.mPaint.setStrokeWidth(this.mTextOutlineThickness);
        this.mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setFlags(0x80);
        this.setTextSize(this.mTextSize);
        this.mPaint.setAntiAlias(true);
    }

    private void setupTexture() {
        if(this.mTextBackground != null) {
            this.mTextShaderMatrix = new Matrix();
            int v = this.mTextBackground.getIntrinsicWidth();
            int v1 = this.mTextBackground.getIntrinsicHeight();
            int v2 = 0x80;
            if(v <= 0) {
                v = this.getWidth();
                if(v == 0) {
                    v = Float.isNaN(this.mTextureWidth) ? 0x80 : ((int)this.mTextureWidth);
                }
            }
            if(v1 <= 0) {
                v1 = this.getHeight();
                if(v1 == 0) {
                    if(!Float.isNaN(this.mTextureHeight)) {
                        v2 = (int)this.mTextureHeight;
                    }
                    v1 = v2;
                }
            }
            if(this.mTextureEffect != 0) {
                v /= 2;
                v1 /= 2;
            }
            this.mTextBackgroundBitmap = Bitmap.createBitmap(v, v1, Bitmap.Config.ARGB_8888);
            Canvas canvas0 = new Canvas(this.mTextBackgroundBitmap);
            this.mTextBackground.setBounds(0, 0, canvas0.getWidth(), canvas0.getHeight());
            this.mTextBackground.setFilterBitmap(true);
            this.mTextBackground.draw(canvas0);
            if(this.mTextureEffect != 0) {
                this.mTextBackgroundBitmap = this.blur(this.mTextBackgroundBitmap, 4);
            }
            this.mTextShader = new BitmapShader(this.mTextBackgroundBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        }
    }

    private void updateShaderMatrix() {
        float f = 0.0f;
        float f1 = Float.isNaN(this.mBackgroundPanX) ? 0.0f : this.mBackgroundPanX;
        float f2 = Float.isNaN(this.mBackgroundPanY) ? 0.0f : this.mBackgroundPanY;
        float f3 = Float.isNaN(this.mZoom) ? 1.0f : this.mZoom;
        if(!Float.isNaN(this.mRotate)) {
            f = this.mRotate;
        }
        this.mTextShaderMatrix.reset();
        float f4 = (float)this.mTextBackgroundBitmap.getWidth();
        float f5 = (float)this.mTextBackgroundBitmap.getHeight();
        float f6 = Float.isNaN(this.mTextureWidth) ? this.mFloatWidth : this.mTextureWidth;
        float f7 = Float.isNaN(this.mTextureHeight) ? this.mFloatHeight : this.mTextureHeight;
        float f8 = f3 * (f4 * f7 < f5 * f6 ? f6 / f4 : f7 / f5);
        this.mTextShaderMatrix.postScale(f8, f8);
        float f9 = f4 * f8;
        float f10 = f6 - f9;
        float f11 = f8 * f5;
        float f12 = Float.isNaN(this.mTextureHeight) ? f7 - f11 : this.mTextureHeight / 2.0f;
        if(!Float.isNaN(this.mTextureWidth)) {
            f10 = this.mTextureWidth / 2.0f;
        }
        this.mTextShaderMatrix.postTranslate((f1 * f10 + f6 - f9) * 0.5f, (f2 * f12 + f7 - f11) * 0.5f);
        this.mTextShaderMatrix.postRotate(f, f6 / 2.0f, f7 / 2.0f);
        this.mTextShader.setLocalMatrix(this.mTextShaderMatrix);
    }
}

