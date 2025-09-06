package androidx.appcompat.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.core.graphics.drawable.WrappedDrawable;

class AppCompatProgressBarHelper {
    static class Api23Impl {
        public static void transferLayerProperties(LayerDrawable layerDrawable0, LayerDrawable layerDrawable1, int v) {
            layerDrawable1.setLayerGravity(v, layerDrawable0.getLayerGravity(v));
            layerDrawable1.setLayerWidth(v, layerDrawable0.getLayerWidth(v));
            layerDrawable1.setLayerHeight(v, layerDrawable0.getLayerHeight(v));
            layerDrawable1.setLayerInsetLeft(v, layerDrawable0.getLayerInsetLeft(v));
            layerDrawable1.setLayerInsetRight(v, layerDrawable0.getLayerInsetRight(v));
            layerDrawable1.setLayerInsetTop(v, layerDrawable0.getLayerInsetTop(v));
            layerDrawable1.setLayerInsetBottom(v, layerDrawable0.getLayerInsetBottom(v));
            layerDrawable1.setLayerInsetStart(v, layerDrawable0.getLayerInsetStart(v));
            layerDrawable1.setLayerInsetEnd(v, layerDrawable0.getLayerInsetEnd(v));
        }
    }

    private static final int[] TINT_ATTRS;
    private Bitmap mSampleTile;
    private final ProgressBar mView;

    static {
        AppCompatProgressBarHelper.TINT_ATTRS = new int[]{0x101013B, 0x101013C};
    }

    AppCompatProgressBarHelper(ProgressBar progressBar0) {
        this.mView = progressBar0;
    }

    private Shape getDrawableShape() {
        return new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null);
    }

    Bitmap getSampleTile() {
        return this.mSampleTile;
    }

    void loadFromAttributes(AttributeSet attributeSet0, int v) {
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet0, AppCompatProgressBarHelper.TINT_ATTRS, v, 0);
        Drawable drawable0 = tintTypedArray0.getDrawableIfKnown(0);
        if(drawable0 != null) {
            Drawable drawable1 = this.tileifyIndeterminate(drawable0);
            this.mView.setIndeterminateDrawable(drawable1);
        }
        Drawable drawable2 = tintTypedArray0.getDrawableIfKnown(1);
        if(drawable2 != null) {
            Drawable drawable3 = this.tileify(drawable2, false);
            this.mView.setProgressDrawable(drawable3);
        }
        tintTypedArray0.recycle();
    }

    Drawable tileify(Drawable drawable0, boolean z) {
        if(drawable0 instanceof WrappedDrawable) {
            Drawable drawable1 = ((WrappedDrawable)drawable0).getWrappedDrawable();
            if(drawable1 != null) {
                ((WrappedDrawable)drawable0).setWrappedDrawable(this.tileify(drawable1, z));
                return drawable0;
            }
        }
        else {
            if(drawable0 instanceof LayerDrawable) {
                int v = ((LayerDrawable)drawable0).getNumberOfLayers();
                Drawable[] arr_drawable = new Drawable[v];
                for(int v2 = 0; v2 < v; ++v2) {
                    int v3 = ((LayerDrawable)drawable0).getId(v2);
                    arr_drawable[v2] = this.tileify(((LayerDrawable)drawable0).getDrawable(v2), v3 == 0x102000D || v3 == 0x102000F);
                }
                Drawable drawable2 = new LayerDrawable(arr_drawable);
                for(int v1 = 0; v1 < v; ++v1) {
                    ((LayerDrawable)drawable2).setId(v1, ((LayerDrawable)drawable0).getId(v1));
                    if(Build.VERSION.SDK_INT >= 23) {
                        Api23Impl.transferLayerProperties(((LayerDrawable)drawable0), ((LayerDrawable)drawable2), v1);
                    }
                }
                return drawable2;
            }
            if(drawable0 instanceof BitmapDrawable) {
                Bitmap bitmap0 = ((BitmapDrawable)drawable0).getBitmap();
                if(this.mSampleTile == null) {
                    this.mSampleTile = bitmap0;
                }
                Drawable drawable3 = new ShapeDrawable(this.getDrawableShape());
                BitmapShader bitmapShader0 = new BitmapShader(bitmap0, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
                ((ShapeDrawable)drawable3).getPaint().setShader(bitmapShader0);
                ((ShapeDrawable)drawable3).getPaint().setColorFilter(((BitmapDrawable)drawable0).getPaint().getColorFilter());
                return z ? new ClipDrawable(drawable3, 3, 1) : drawable3;
            }
        }
        return drawable0;
    }

    private Drawable tileifyIndeterminate(Drawable drawable0) {
        if(drawable0 instanceof AnimationDrawable) {
            int v = ((AnimationDrawable)drawable0).getNumberOfFrames();
            Drawable drawable1 = new AnimationDrawable();
            ((AnimationDrawable)drawable1).setOneShot(((AnimationDrawable)drawable0).isOneShot());
            for(int v1 = 0; v1 < v; ++v1) {
                Drawable drawable2 = this.tileify(((AnimationDrawable)drawable0).getFrame(v1), true);
                drawable2.setLevel(10000);
                ((AnimationDrawable)drawable1).addFrame(drawable2, ((AnimationDrawable)drawable0).getDuration(v1));
            }
            ((AnimationDrawable)drawable1).setLevel(10000);
            return drawable1;
        }
        return drawable0;
    }
}

