package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.ImageView;
import java.util.Map;

public class ChangeImageTransform extends Transition {
    static class Listener extends AnimatorListenerAdapter implements TransitionListener {
        private final Matrix mEndMatrix;
        private final ImageView mImageView;
        private boolean mIsBeforeAnimator;
        private final Matrix mStartMatrix;

        Listener(ImageView imageView0, Matrix matrix0, Matrix matrix1) {
            this.mIsBeforeAnimator = true;
            this.mImageView = imageView0;
            this.mStartMatrix = matrix0;
            this.mEndMatrix = matrix1;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.mIsBeforeAnimator = false;
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0, boolean z) {
            this.mIsBeforeAnimator = z;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationPause(Animator animator0) {
            this.saveMatrix(((Matrix)((ObjectAnimator)animator0).getAnimatedValue()));
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationResume(Animator animator0) {
            this.restoreMatrix();
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationStart(Animator animator0) {
            this.mIsBeforeAnimator = false;
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationStart(Animator animator0, boolean z) {
            this.mIsBeforeAnimator = false;
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionCancel(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionPause(Transition transition0) {
            if(this.mIsBeforeAnimator) {
                this.saveMatrix(this.mStartMatrix);
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionResume(Transition transition0) {
            this.restoreMatrix();
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
        }

        private void restoreMatrix() {
            Matrix matrix0 = (Matrix)this.mImageView.getTag(id.transition_image_transform);
            if(matrix0 != null) {
                ImageViewUtils.animateTransform(this.mImageView, matrix0);
                this.mImageView.setTag(id.transition_image_transform, null);
            }
        }

        private void saveMatrix(Matrix matrix0) {
            this.mImageView.setTag(id.transition_image_transform, matrix0);
            ImageViewUtils.animateTransform(this.mImageView, this.mEndMatrix);
        }
    }

    private static final Property ANIMATED_TRANSFORM_PROPERTY = null;
    private static final TypeEvaluator NULL_MATRIX_EVALUATOR = null;
    private static final String PROPNAME_BOUNDS = "android:changeImageTransform:bounds";
    private static final String PROPNAME_MATRIX = "android:changeImageTransform:matrix";
    private static final String[] sTransitionProperties;

    static {
        ChangeImageTransform.sTransitionProperties = new String[]{"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};
        ChangeImageTransform.NULL_MATRIX_EVALUATOR = new TypeEvaluator() {
            public Matrix evaluate(float f, Matrix matrix0, Matrix matrix1) {
                return null;
            }

            @Override  // android.animation.TypeEvaluator
            public Object evaluate(float f, Object object0, Object object1) {
                return this.evaluate(f, ((Matrix)object0), ((Matrix)object1));
            }
        };
        ChangeImageTransform.ANIMATED_TRANSFORM_PROPERTY = new Property(Matrix.class, "animatedTransform") {
            public Matrix get(ImageView imageView0) {
                return null;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((ImageView)object0));
            }

            public void set(ImageView imageView0, Matrix matrix0) {
                ImageViewUtils.animateTransform(imageView0, matrix0);
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((ImageView)object0), ((Matrix)object1));
            }
        };
    }

    public ChangeImageTransform() {
    }

    public ChangeImageTransform(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0, false);
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0, true);
    }

    private void captureValues(TransitionValues transitionValues0, boolean z) {
        View view0 = transitionValues0.view;
        if(view0 instanceof ImageView && view0.getVisibility() == 0 && ((ImageView)view0).getDrawable() != null) {
            Map map0 = transitionValues0.values;
            map0.put("android:changeImageTransform:bounds", new Rect(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom()));
            Matrix matrix0 = z ? ((Matrix)((ImageView)view0).getTag(id.transition_image_transform)) : null;
            if(matrix0 == null) {
                matrix0 = ChangeImageTransform.copyImageMatrix(((ImageView)view0));
            }
            map0.put("android:changeImageTransform:matrix", matrix0);
        }
    }

    private static Matrix centerCropMatrix(ImageView imageView0) {
        Drawable drawable0 = imageView0.getDrawable();
        int v = drawable0.getIntrinsicWidth();
        float f = (float)imageView0.getWidth();
        int v1 = drawable0.getIntrinsicHeight();
        float f1 = (float)imageView0.getHeight();
        float f2 = Math.max(f / ((float)v), f1 / ((float)v1));
        Matrix matrix0 = new Matrix();
        matrix0.postScale(f2, f2);
        matrix0.postTranslate(((float)Math.round((f - ((float)v) * f2) / 2.0f)), ((float)Math.round((f1 - ((float)v1) * f2) / 2.0f)));
        return matrix0;
    }

    private static Matrix copyImageMatrix(ImageView imageView0) {
        Drawable drawable0 = imageView0.getDrawable();
        if(drawable0.getIntrinsicWidth() > 0 && drawable0.getIntrinsicHeight() > 0) {
            switch(androidx.transition.ChangeImageTransform.3.$SwitchMap$android$widget$ImageView$ScaleType[imageView0.getScaleType().ordinal()]) {
                case 1: {
                    return ChangeImageTransform.fitXYMatrix(imageView0);
                }
                case 2: {
                    return ChangeImageTransform.centerCropMatrix(imageView0);
                }
                default: {
                    return new Matrix(imageView0.getImageMatrix());
                }
            }
        }
        return new Matrix(imageView0.getImageMatrix());
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        if(transitionValues0 != null && transitionValues1 != null) {
            Rect rect0 = (Rect)transitionValues0.values.get("android:changeImageTransform:bounds");
            Rect rect1 = (Rect)transitionValues1.values.get("android:changeImageTransform:bounds");
            if(rect0 != null && rect1 != null) {
                Matrix matrix0 = (Matrix)transitionValues0.values.get("android:changeImageTransform:matrix");
                Matrix matrix1 = (Matrix)transitionValues1.values.get("android:changeImageTransform:matrix");
                boolean z = matrix0 == null && matrix1 == null || matrix0 != null && matrix0.equals(matrix1);
                if(rect0.equals(rect1) && z) {
                    return null;
                }
                ImageView imageView0 = (ImageView)transitionValues1.view;
                Drawable drawable0 = imageView0.getDrawable();
                if(drawable0.getIntrinsicWidth() > 0 && drawable0.getIntrinsicHeight() > 0) {
                    if(matrix0 == null) {
                        matrix0 = MatrixUtils.IDENTITY_MATRIX;
                    }
                    if(matrix1 == null) {
                        matrix1 = MatrixUtils.IDENTITY_MATRIX;
                    }
                    ChangeImageTransform.ANIMATED_TRANSFORM_PROPERTY.set(imageView0, matrix0);
                    Animator animator0 = this.createMatrixAnimator(imageView0, matrix0, matrix1);
                    Listener changeImageTransform$Listener0 = new Listener(imageView0, matrix0, matrix1);
                    ((ObjectAnimator)animator0).addListener(changeImageTransform$Listener0);
                    ((ObjectAnimator)animator0).addPauseListener(changeImageTransform$Listener0);
                    this.addListener(changeImageTransform$Listener0);
                    return animator0;
                }
                return this.createNullAnimator(imageView0);
            }
        }
        return null;
    }

    private ObjectAnimator createMatrixAnimator(ImageView imageView0, Matrix matrix0, Matrix matrix1) {
        MatrixEvaluator transitionUtils$MatrixEvaluator0 = new MatrixEvaluator();
        return ObjectAnimator.ofObject(imageView0, ChangeImageTransform.ANIMATED_TRANSFORM_PROPERTY, transitionUtils$MatrixEvaluator0, new Matrix[]{matrix0, matrix1});
    }

    private ObjectAnimator createNullAnimator(ImageView imageView0) {
        return ObjectAnimator.ofObject(imageView0, ChangeImageTransform.ANIMATED_TRANSFORM_PROPERTY, ChangeImageTransform.NULL_MATRIX_EVALUATOR, new Matrix[]{MatrixUtils.IDENTITY_MATRIX, MatrixUtils.IDENTITY_MATRIX});
    }

    private static Matrix fitXYMatrix(ImageView imageView0) {
        Drawable drawable0 = imageView0.getDrawable();
        Matrix matrix0 = new Matrix();
        matrix0.postScale(((float)imageView0.getWidth()) / ((float)drawable0.getIntrinsicWidth()), ((float)imageView0.getHeight()) / ((float)drawable0.getIntrinsicHeight()));
        return matrix0;
    }

    @Override  // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return ChangeImageTransform.sTransitionProperties;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    class androidx.transition.ChangeImageTransform.3 {
        static final int[] $SwitchMap$android$widget$ImageView$ScaleType;

        static {
            int[] arr_v = new int[ImageView.ScaleType.values().length];
            androidx.transition.ChangeImageTransform.3.$SwitchMap$android$widget$ImageView$ScaleType = arr_v;
            try {
                arr_v[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                androidx.transition.ChangeImageTransform.3.$SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

