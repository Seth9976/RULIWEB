package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.R.styleable;

public class ImageFilterView extends AppCompatImageView {
    static class ImageMatrix {
        float mBrightness;
        ColorMatrix mColorMatrix;
        float mContrast;
        float[] mMatrix;
        float mSaturation;
        ColorMatrix mTmpColorMatrix;
        float mWarmth;

        ImageMatrix() {
            this.mMatrix = new float[20];
            this.mColorMatrix = new ColorMatrix();
            this.mTmpColorMatrix = new ColorMatrix();
            this.mBrightness = 1.0f;
            this.mSaturation = 1.0f;
            this.mContrast = 1.0f;
            this.mWarmth = 1.0f;
        }

        private void brightness(float f) {
            float[] arr_f = this.mMatrix;
            arr_f[0] = f;
            arr_f[1] = 0.0f;
            arr_f[2] = 0.0f;
            arr_f[3] = 0.0f;
            arr_f[4] = 0.0f;
            arr_f[5] = 0.0f;
            arr_f[6] = f;
            arr_f[7] = 0.0f;
            arr_f[8] = 0.0f;
            arr_f[9] = 0.0f;
            arr_f[10] = 0.0f;
            arr_f[11] = 0.0f;
            arr_f[12] = f;
            arr_f[13] = 0.0f;
            arr_f[14] = 0.0f;
            arr_f[15] = 0.0f;
            arr_f[16] = 0.0f;
            arr_f[17] = 0.0f;
            arr_f[18] = 1.0f;
            arr_f[19] = 0.0f;
        }

        private void saturation(float f) {
            float f1 = 0.2999f * (1.0f - f);
            float f2 = 0.587f * (1.0f - f);
            float f3 = (1.0f - f) * 0.114f;
            float[] arr_f = this.mMatrix;
            arr_f[0] = f1 + f;
            arr_f[1] = f2;
            arr_f[2] = f3;
            arr_f[3] = 0.0f;
            arr_f[4] = 0.0f;
            arr_f[5] = f1;
            arr_f[6] = f2 + f;
            arr_f[7] = f3;
            arr_f[8] = 0.0f;
            arr_f[9] = 0.0f;
            arr_f[10] = f1;
            arr_f[11] = f2;
            arr_f[12] = f3 + f;
            arr_f[13] = 0.0f;
            arr_f[14] = 0.0f;
            arr_f[15] = 0.0f;
            arr_f[16] = 0.0f;
            arr_f[17] = 0.0f;
            arr_f[18] = 1.0f;
            arr_f[19] = 0.0f;
        }

        void updateMatrix(ImageView imageView0) {
            int v1;
            this.mColorMatrix.reset();
            float f = this.mSaturation;
            int v = 1;
            if(f == 1.0f) {
                v1 = 0;
            }
            else {
                this.saturation(f);
                this.mColorMatrix.set(this.mMatrix);
                v1 = 1;
            }
            float f1 = this.mContrast;
            if(f1 != 1.0f) {
                this.mTmpColorMatrix.setScale(f1, f1, f1, 1.0f);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                v1 = 1;
            }
            float f2 = this.mWarmth;
            if(f2 != 1.0f) {
                this.warmth(f2);
                this.mTmpColorMatrix.set(this.mMatrix);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
                v1 = 1;
            }
            float f3 = this.mBrightness;
            if(f3 == 1.0f) {
                v = v1;
            }
            else {
                this.brightness(f3);
                this.mTmpColorMatrix.set(this.mMatrix);
                this.mColorMatrix.postConcat(this.mTmpColorMatrix);
            }
            if(v != 0) {
                imageView0.setColorFilter(new ColorMatrixColorFilter(this.mColorMatrix));
                return;
            }
            imageView0.clearColorFilter();
        }

        private void warmth(float f) {
            float f4;
            float f3;
            float f2;
            if(f <= 0.0f) {
                f = 0.01f;
            }
            float f1 = 5000.0f / f / 100.0f;
            if(f1 > 66.0f) {
                f2 = ((float)Math.pow(f1 - 60.0f, -0.133205)) * 329.69873f;
                f3 = ((float)Math.pow(f1 - 60.0f, 0.075515)) * 288.122162f;
            }
            else {
                f3 = ((float)Math.log(f1)) * 99.470802f - 161.119568f;
                f2 = 255.0f;
            }
            if(f1 >= 66.0f) {
                f4 = 255.0f;
            }
            else if(f1 > 19.0f) {
                f4 = ((float)Math.log(f1 - 10.0f)) * 138.517731f - 305.0448f;
            }
            else {
                f4 = 0.0f;
            }
            float[] arr_f = this.mMatrix;
            arr_f[0] = Math.min(255.0f, Math.max(f2, 0.0f)) / 255.0f;
            arr_f[1] = 0.0f;
            arr_f[2] = 0.0f;
            arr_f[3] = 0.0f;
            arr_f[4] = 0.0f;
            arr_f[5] = 0.0f;
            arr_f[6] = Math.min(255.0f, Math.max(f3, 0.0f)) / 228.012512f;
            arr_f[7] = 0.0f;
            arr_f[8] = 0.0f;
            arr_f[9] = 0.0f;
            arr_f[10] = 0.0f;
            arr_f[11] = 0.0f;
            arr_f[12] = Math.min(255.0f, Math.max(f4, 0.0f)) / 205.93042f;
            arr_f[13] = 0.0f;
            arr_f[14] = 0.0f;
            arr_f[15] = 0.0f;
            arr_f[16] = 0.0f;
            arr_f[17] = 0.0f;
            arr_f[18] = 1.0f;
            arr_f[19] = 0.0f;
        }
    }

    private Drawable mAltDrawable;
    private float mCrossfade;
    private Drawable mDrawable;
    private ImageMatrix mImageMatrix;
    LayerDrawable mLayer;
    Drawable[] mLayers;
    private boolean mOverlay;
    float mPanX;
    float mPanY;
    private Path mPath;
    RectF mRect;
    float mRotate;
    private float mRound;
    private float mRoundPercent;
    ViewOutlineProvider mViewOutlineProvider;
    float mZoom;

    public ImageFilterView(Context context0) {
        super(context0);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mAltDrawable = null;
        this.mDrawable = null;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mLayers = new Drawable[2];
        this.mPanX = NaNf;
        this.mPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, null);
    }

    public ImageFilterView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mAltDrawable = null;
        this.mDrawable = null;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mLayers = new Drawable[2];
        this.mPanX = NaNf;
        this.mPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, attributeSet0);
    }

    public ImageFilterView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mImageMatrix = new ImageMatrix();
        this.mOverlay = true;
        this.mAltDrawable = null;
        this.mDrawable = null;
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mLayers = new Drawable[2];
        this.mPanX = NaNf;
        this.mPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, attributeSet0);
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
    }

    public float getBrightness() {
        return this.mImageMatrix.mBrightness;
    }

    public float getContrast() {
        return this.mImageMatrix.mContrast;
    }

    public float getCrossfade() {
        return this.mCrossfade;
    }

    public float getImagePanX() {
        return this.mPanX;
    }

    public float getImagePanY() {
        return this.mPanY;
    }

    public float getImageRotate() {
        return this.mRotate;
    }

    public float getImageZoom() {
        return this.mZoom;
    }

    public float getRound() {
        return this.mRound;
    }

    public float getRoundPercent() {
        return this.mRoundPercent;
    }

    public float getSaturation() {
        return this.mImageMatrix.mSaturation;
    }

    public float getWarmth() {
        return this.mImageMatrix.mWarmth;
    }

    private void init(Context context0, AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ImageFilterView);
            int v = typedArray0.getIndexCount();
            this.mAltDrawable = typedArray0.getDrawable(styleable.ImageFilterView_altSrc);
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ImageFilterView_crossfade) {
                    this.mCrossfade = typedArray0.getFloat(v2, 0.0f);
                }
                else if(v2 == styleable.ImageFilterView_warmth) {
                    this.setWarmth(typedArray0.getFloat(v2, 0.0f));
                }
                else if(v2 == styleable.ImageFilterView_saturation) {
                    this.setSaturation(typedArray0.getFloat(v2, 0.0f));
                }
                else if(v2 == styleable.ImageFilterView_contrast) {
                    this.setContrast(typedArray0.getFloat(v2, 0.0f));
                }
                else if(v2 == styleable.ImageFilterView_brightness) {
                    this.setBrightness(typedArray0.getFloat(v2, 0.0f));
                }
                else if(v2 == styleable.ImageFilterView_round) {
                    this.setRound(typedArray0.getDimension(v2, 0.0f));
                }
                else if(v2 == styleable.ImageFilterView_roundPercent) {
                    this.setRoundPercent(typedArray0.getFloat(v2, 0.0f));
                }
                else if(v2 == styleable.ImageFilterView_overlay) {
                    this.setOverlay(typedArray0.getBoolean(v2, this.mOverlay));
                }
                else if(v2 == styleable.ImageFilterView_imagePanX) {
                    this.setImagePanX(typedArray0.getFloat(v2, this.mPanX));
                }
                else if(v2 == styleable.ImageFilterView_imagePanY) {
                    this.setImagePanY(typedArray0.getFloat(v2, this.mPanY));
                }
                else if(v2 == styleable.ImageFilterView_imageRotate) {
                    this.setImageRotate(typedArray0.getFloat(v2, this.mRotate));
                }
                else if(v2 == styleable.ImageFilterView_imageZoom) {
                    this.setImageZoom(typedArray0.getFloat(v2, this.mZoom));
                }
            }
            typedArray0.recycle();
            Drawable drawable0 = this.getDrawable();
            this.mDrawable = drawable0;
            if(this.mAltDrawable != null && drawable0 != null) {
                Drawable[] arr_drawable = this.mLayers;
                Drawable drawable1 = this.getDrawable().mutate();
                this.mDrawable = drawable1;
                arr_drawable[0] = drawable1;
                Drawable[] arr_drawable1 = this.mLayers;
                arr_drawable1[1] = this.mAltDrawable.mutate();
                LayerDrawable layerDrawable0 = new LayerDrawable(this.mLayers);
                this.mLayer = layerDrawable0;
                layerDrawable0.getDrawable(1).setAlpha(((int)(this.mCrossfade * 255.0f)));
                if(!this.mOverlay) {
                    this.mLayer.getDrawable(0).setAlpha(((int)((1.0f - this.mCrossfade) * 255.0f)));
                }
                super.setImageDrawable(this.mLayer);
                return;
            }
            Drawable drawable2 = this.getDrawable();
            this.mDrawable = drawable2;
            if(drawable2 != null) {
                Drawable[] arr_drawable2 = this.mLayers;
                Drawable drawable3 = drawable2.mutate();
                this.mDrawable = drawable3;
                arr_drawable2[0] = drawable3;
            }
        }
    }

    @Override  // android.view.View
    public void layout(int v, int v1, int v2, int v3) {
        super.layout(v, v1, v2, v3);
        this.setMatrix();
    }

    public void setAltImageDrawable(Drawable drawable0) {
        Drawable drawable1 = drawable0.mutate();
        this.mAltDrawable = drawable1;
        Drawable[] arr_drawable = this.mLayers;
        arr_drawable[0] = this.mDrawable;
        arr_drawable[1] = drawable1;
        LayerDrawable layerDrawable0 = new LayerDrawable(this.mLayers);
        this.mLayer = layerDrawable0;
        super.setImageDrawable(layerDrawable0);
        this.setCrossfade(this.mCrossfade);
    }

    public void setAltImageResource(int v) {
        Drawable drawable0 = AppCompatResources.getDrawable(this.getContext(), v);
        this.mAltDrawable = drawable0;
        this.setAltImageDrawable(drawable0);
    }

    public void setBrightness(float f) {
        this.mImageMatrix.mBrightness = f;
        this.mImageMatrix.updateMatrix(this);
    }

    public void setContrast(float f) {
        this.mImageMatrix.mContrast = f;
        this.mImageMatrix.updateMatrix(this);
    }

    public void setCrossfade(float f) {
        this.mCrossfade = f;
        if(this.mLayers != null) {
            if(!this.mOverlay) {
                this.mLayer.getDrawable(0).setAlpha(((int)((1.0f - this.mCrossfade) * 255.0f)));
            }
            this.mLayer.getDrawable(1).setAlpha(((int)(this.mCrossfade * 255.0f)));
            super.setImageDrawable(this.mLayer);
        }
    }

    @Override  // androidx.appcompat.widget.AppCompatImageView
    public void setImageDrawable(Drawable drawable0) {
        if(this.mAltDrawable != null && drawable0 != null) {
            Drawable drawable1 = drawable0.mutate();
            this.mDrawable = drawable1;
            Drawable[] arr_drawable = this.mLayers;
            arr_drawable[0] = drawable1;
            arr_drawable[1] = this.mAltDrawable;
            LayerDrawable layerDrawable0 = new LayerDrawable(this.mLayers);
            this.mLayer = layerDrawable0;
            super.setImageDrawable(layerDrawable0);
            this.setCrossfade(this.mCrossfade);
            return;
        }
        super.setImageDrawable(drawable0);
    }

    public void setImagePanX(float f) {
        this.mPanX = f;
        this.updateViewMatrix();
    }

    public void setImagePanY(float f) {
        this.mPanY = f;
        this.updateViewMatrix();
    }

    @Override  // androidx.appcompat.widget.AppCompatImageView
    public void setImageResource(int v) {
        if(this.mAltDrawable != null) {
            Drawable drawable0 = AppCompatResources.getDrawable(this.getContext(), v).mutate();
            this.mDrawable = drawable0;
            Drawable[] arr_drawable = this.mLayers;
            arr_drawable[0] = drawable0;
            arr_drawable[1] = this.mAltDrawable;
            LayerDrawable layerDrawable0 = new LayerDrawable(this.mLayers);
            this.mLayer = layerDrawable0;
            super.setImageDrawable(layerDrawable0);
            this.setCrossfade(this.mCrossfade);
            return;
        }
        super.setImageResource(v);
    }

    public void setImageRotate(float f) {
        this.mRotate = f;
        this.updateViewMatrix();
    }

    public void setImageZoom(float f) {
        this.mZoom = f;
        this.updateViewMatrix();
    }

    private void setMatrix() {
        float f = 0.0f;
        if(Float.isNaN(this.mPanX) && Float.isNaN(this.mPanY) && Float.isNaN(this.mZoom) && Float.isNaN(this.mRotate)) {
            return;
        }
        float f1 = Float.isNaN(this.mPanX) ? 0.0f : this.mPanX;
        float f2 = Float.isNaN(this.mPanY) ? 0.0f : this.mPanY;
        float f3 = Float.isNaN(this.mZoom) ? 1.0f : this.mZoom;
        if(!Float.isNaN(this.mRotate)) {
            f = this.mRotate;
        }
        Matrix matrix0 = new Matrix();
        matrix0.reset();
        float f4 = (float)this.getDrawable().getIntrinsicWidth();
        float f5 = (float)this.getDrawable().getIntrinsicHeight();
        float f6 = (float)this.getWidth();
        float f7 = (float)this.getHeight();
        float f8 = f3 * (f4 * f7 < f5 * f6 ? f6 / f4 : f7 / f5);
        matrix0.postScale(f8, f8);
        matrix0.postTranslate((f1 * (f6 - f4 * f8) + f6 - f4 * f8) * 0.5f, (f2 * (f7 - f8 * f5) + f7 - f8 * f5) * 0.5f);
        matrix0.postRotate(f, f6 / 2.0f, f7 / 2.0f);
        this.setImageMatrix(matrix0);
        this.setScaleType(ImageView.ScaleType.MATRIX);
    }

    private void setOverlay(boolean z) {
        this.mOverlay = z;
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
                androidx.constraintlayout.utils.widget.ImageFilterView.2 imageFilterView$20 = new ViewOutlineProvider() {
                    @Override  // android.view.ViewOutlineProvider
                    public void getOutline(View view0, Outline outline0) {
                        outline0.setRoundRect(0, 0, ImageFilterView.this.getWidth(), ImageFilterView.this.getHeight(), ImageFilterView.this.mRound);
                    }
                };
                this.mViewOutlineProvider = imageFilterView$20;
                this.setOutlineProvider(imageFilterView$20);
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
                androidx.constraintlayout.utils.widget.ImageFilterView.1 imageFilterView$10 = new ViewOutlineProvider() {
                    @Override  // android.view.ViewOutlineProvider
                    public void getOutline(View view0, Outline outline0) {
                        int v = ImageFilterView.this.getWidth();
                        int v1 = ImageFilterView.this.getHeight();
                        outline0.setRoundRect(0, 0, v, v1, ((float)Math.min(v, v1)) * ImageFilterView.this.mRoundPercent / 2.0f);
                    }
                };
                this.mViewOutlineProvider = imageFilterView$10;
                this.setOutlineProvider(imageFilterView$10);
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

    public void setSaturation(float f) {
        this.mImageMatrix.mSaturation = f;
        this.mImageMatrix.updateMatrix(this);
    }

    public void setWarmth(float f) {
        this.mImageMatrix.mWarmth = f;
        this.mImageMatrix.updateMatrix(this);
    }

    private void updateViewMatrix() {
        if(Float.isNaN(this.mPanX) && Float.isNaN(this.mPanY) && Float.isNaN(this.mZoom) && Float.isNaN(this.mRotate)) {
            this.setScaleType(ImageView.ScaleType.FIT_CENTER);
            return;
        }
        this.setMatrix();
    }
}

