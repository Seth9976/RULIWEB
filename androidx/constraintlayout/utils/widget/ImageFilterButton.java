package androidx.constraintlayout.utils.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.R.styleable;

public class ImageFilterButton extends AppCompatImageButton {
    private Drawable mAltDrawable;
    private float mCrossfade;
    private Drawable mDrawable;
    private ImageMatrix mImageMatrix;
    LayerDrawable mLayer;
    Drawable[] mLayers;
    private boolean mOverlay;
    private float mPanX;
    private float mPanY;
    private Path mPath;
    RectF mRect;
    private float mRotate;
    private float mRound;
    private float mRoundPercent;
    ViewOutlineProvider mViewOutlineProvider;
    private float mZoom;

    public ImageFilterButton(Context context0) {
        super(context0);
        this.mImageMatrix = new ImageMatrix();
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mLayers = new Drawable[2];
        this.mOverlay = true;
        this.mAltDrawable = null;
        this.mDrawable = null;
        this.mPanX = NaNf;
        this.mPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, null);
    }

    public ImageFilterButton(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mImageMatrix = new ImageMatrix();
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mLayers = new Drawable[2];
        this.mOverlay = true;
        this.mAltDrawable = null;
        this.mDrawable = null;
        this.mPanX = NaNf;
        this.mPanY = NaNf;
        this.mZoom = NaNf;
        this.mRotate = NaNf;
        this.init(context0, attributeSet0);
    }

    public ImageFilterButton(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mImageMatrix = new ImageMatrix();
        this.mCrossfade = 0.0f;
        this.mRoundPercent = 0.0f;
        this.mRound = NaNf;
        this.mLayers = new Drawable[2];
        this.mOverlay = true;
        this.mAltDrawable = null;
        this.mDrawable = null;
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
        this.setPadding(0, 0, 0, 0);
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

    public void setAltImageResource(int v) {
        Drawable drawable0 = AppCompatResources.getDrawable(this.getContext(), v).mutate();
        this.mAltDrawable = drawable0;
        Drawable[] arr_drawable = this.mLayers;
        arr_drawable[0] = this.mDrawable;
        arr_drawable[1] = drawable0;
        LayerDrawable layerDrawable0 = new LayerDrawable(this.mLayers);
        this.mLayer = layerDrawable0;
        super.setImageDrawable(layerDrawable0);
        this.setCrossfade(this.mCrossfade);
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

    @Override  // androidx.appcompat.widget.AppCompatImageButton
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

    @Override  // androidx.appcompat.widget.AppCompatImageButton
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
                androidx.constraintlayout.utils.widget.ImageFilterButton.2 imageFilterButton$20 = new ViewOutlineProvider() {
                    @Override  // android.view.ViewOutlineProvider
                    public void getOutline(View view0, Outline outline0) {
                        outline0.setRoundRect(0, 0, ImageFilterButton.this.getWidth(), ImageFilterButton.this.getHeight(), ImageFilterButton.this.mRound);
                    }
                };
                this.mViewOutlineProvider = imageFilterButton$20;
                this.setOutlineProvider(imageFilterButton$20);
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
                androidx.constraintlayout.utils.widget.ImageFilterButton.1 imageFilterButton$10 = new ViewOutlineProvider() {
                    @Override  // android.view.ViewOutlineProvider
                    public void getOutline(View view0, Outline outline0) {
                        int v = ImageFilterButton.this.getWidth();
                        int v1 = ImageFilterButton.this.getHeight();
                        outline0.setRoundRect(0, 0, v, v1, ((float)Math.min(v, v1)) * ImageFilterButton.this.mRoundPercent / 2.0f);
                    }
                };
                this.mViewOutlineProvider = imageFilterButton$10;
                this.setOutlineProvider(imageFilterButton$10);
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

