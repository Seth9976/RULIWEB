package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;

class TransitionUtils {
    static class Api28Impl {
        static Bitmap createBitmap(Picture picture0) {
            return Bitmap.createBitmap(picture0);
        }
    }

    static class MatrixEvaluator implements TypeEvaluator {
        final float[] mTempEndValues;
        final Matrix mTempMatrix;
        final float[] mTempStartValues;

        MatrixEvaluator() {
            this.mTempStartValues = new float[9];
            this.mTempEndValues = new float[9];
            this.mTempMatrix = new Matrix();
        }

        public Matrix evaluate(float f, Matrix matrix0, Matrix matrix1) {
            matrix0.getValues(this.mTempStartValues);
            matrix1.getValues(this.mTempEndValues);
            for(int v = 0; v < 9; ++v) {
                float f1 = this.mTempEndValues[v];
                float f2 = this.mTempStartValues[v];
                this.mTempEndValues[v] = f2 + (f1 - f2) * f;
            }
            this.mTempMatrix.setValues(this.mTempEndValues);
            return this.mTempMatrix;
        }

        @Override  // android.animation.TypeEvaluator
        public Object evaluate(float f, Object object0, Object object1) {
            return this.evaluate(f, ((Matrix)object0), ((Matrix)object1));
        }
    }

    private static final boolean HAS_PICTURE_BITMAP = false;
    private static final int MAX_IMAGE_SIZE = 0x100000;

    static {
        TransitionUtils.HAS_PICTURE_BITMAP = Build.VERSION.SDK_INT >= 28;
    }

    static View copyViewImage(ViewGroup viewGroup0, View view0, View view1) {
        Matrix matrix0 = new Matrix();
        matrix0.setTranslate(((float)(-view1.getScrollX())), ((float)(-view1.getScrollY())));
        ViewUtils.transformMatrixToGlobal(view0, matrix0);
        ViewUtils.transformMatrixToLocal(viewGroup0, matrix0);
        RectF rectF0 = new RectF(0.0f, 0.0f, ((float)view0.getWidth()), ((float)view0.getHeight()));
        matrix0.mapRect(rectF0);
        int v = Math.round(rectF0.left);
        int v1 = Math.round(rectF0.top);
        int v2 = Math.round(rectF0.right);
        int v3 = Math.round(rectF0.bottom);
        View view2 = new ImageView(view0.getContext());
        ((ImageView)view2).setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap bitmap0 = TransitionUtils.createViewBitmap(view0, matrix0, rectF0, viewGroup0);
        if(bitmap0 != null) {
            ((ImageView)view2).setImageBitmap(bitmap0);
        }
        ((ImageView)view2).measure(View.MeasureSpec.makeMeasureSpec(v2 - v, 0x40000000), View.MeasureSpec.makeMeasureSpec(v3 - v1, 0x40000000));
        ((ImageView)view2).layout(v, v1, v2, v3);
        return view2;
    }

    private static Bitmap createViewBitmap(View view0, Matrix matrix0, RectF rectF0, ViewGroup viewGroup0) {
        int v;
        ViewGroup viewGroup1;
        boolean z = view0.isAttachedToWindow();
        boolean z1 = viewGroup0 != null && viewGroup0.isAttachedToWindow();
        Bitmap bitmap0 = null;
        if(z) {
            viewGroup1 = null;
            v = 0;
        }
        else {
            if(!z1) {
                return null;
            }
            viewGroup1 = (ViewGroup)view0.getParent();
            v = viewGroup1.indexOfChild(view0);
            viewGroup0.getOverlay().add(view0);
        }
        int v1 = Math.round(rectF0.width());
        int v2 = Math.round(rectF0.height());
        if(v1 > 0 && v2 > 0) {
            float f = Math.min(1.0f, 1048576.0f / ((float)(v1 * v2)));
            int v3 = Math.round(((float)v1) * f);
            int v4 = Math.round(((float)v2) * f);
            matrix0.postTranslate(-rectF0.left, -rectF0.top);
            matrix0.postScale(f, f);
            if(TransitionUtils.HAS_PICTURE_BITMAP) {
                Picture picture0 = new Picture();
                Canvas canvas0 = picture0.beginRecording(v3, v4);
                canvas0.concat(matrix0);
                view0.draw(canvas0);
                picture0.endRecording();
                bitmap0 = Api28Impl.createBitmap(picture0);
            }
            else {
                bitmap0 = Bitmap.createBitmap(v3, v4, Bitmap.Config.ARGB_8888);
                Canvas canvas1 = new Canvas(bitmap0);
                canvas1.concat(matrix0);
                view0.draw(canvas1);
            }
        }
        if(!z) {
            viewGroup0.getOverlay().remove(view0);
            viewGroup1.addView(view0, v);
        }
        return bitmap0;
    }

    static Animator mergeAnimators(Animator animator0, Animator animator1) {
        if(animator0 == null) {
            return animator1;
        }
        if(animator1 == null) {
            return animator0;
        }
        Animator animator2 = new AnimatorSet();
        ((AnimatorSet)animator2).playTogether(new Animator[]{animator0, animator1});
        return animator2;
    }
}

