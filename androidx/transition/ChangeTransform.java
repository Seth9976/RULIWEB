package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.view.ViewCompat;
import org.xmlpull.v1.XmlPullParser;

public class ChangeTransform extends Transition {
    static class GhostListener extends TransitionListenerAdapter {
        private GhostView mGhostView;
        private View mView;

        GhostListener(View view0, GhostView ghostView0) {
            this.mView = view0;
            this.mGhostView = ghostView0;
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionEnd(Transition transition0) {
            transition0.removeListener(this);
            GhostViewUtils.removeGhost(this.mView);
            this.mView.setTag(id.transition_transform, null);
            this.mView.setTag(id.parent_matrix, null);
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionPause(Transition transition0) {
            this.mGhostView.setVisibility(4);
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionResume(Transition transition0) {
            this.mGhostView.setVisibility(0);
        }
    }

    static class Listener extends AnimatorListenerAdapter {
        private final Matrix mEndMatrix;
        private final boolean mHandleParentChange;
        private boolean mIsCanceled;
        private final PathAnimatorMatrix mPathAnimatorMatrix;
        private final Matrix mTempMatrix;
        private final Transforms mTransforms;
        private final boolean mUseOverlay;
        private final View mView;

        Listener(View view0, Transforms changeTransform$Transforms0, PathAnimatorMatrix changeTransform$PathAnimatorMatrix0, Matrix matrix0, boolean z, boolean z1) {
            this.mTempMatrix = new Matrix();
            this.mHandleParentChange = z;
            this.mUseOverlay = z1;
            this.mView = view0;
            this.mTransforms = changeTransform$Transforms0;
            this.mPathAnimatorMatrix = changeTransform$PathAnimatorMatrix0;
            this.mEndMatrix = matrix0;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationCancel(Animator animator0) {
            this.mIsCanceled = true;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            if(!this.mIsCanceled) {
                if(!this.mHandleParentChange || !this.mUseOverlay) {
                    this.mView.setTag(id.transition_transform, null);
                    this.mView.setTag(id.parent_matrix, null);
                }
                else {
                    this.setCurrentMatrix(this.mEndMatrix);
                }
            }
            ViewUtils.setAnimationMatrix(this.mView, null);
            this.mTransforms.restore(this.mView);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationPause(Animator animator0) {
            this.setCurrentMatrix(this.mPathAnimatorMatrix.getMatrix());
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationResume(Animator animator0) {
            ChangeTransform.setIdentityTransforms(this.mView);
        }

        private void setCurrentMatrix(Matrix matrix0) {
            this.mTempMatrix.set(matrix0);
            this.mView.setTag(id.transition_transform, this.mTempMatrix);
            this.mTransforms.restore(this.mView);
        }
    }

    static class PathAnimatorMatrix {
        private final Matrix mMatrix;
        private float mTranslationX;
        private float mTranslationY;
        private final float[] mValues;
        private final View mView;

        PathAnimatorMatrix(View view0, float[] arr_f) {
            this.mMatrix = new Matrix();
            this.mView = view0;
            float[] arr_f1 = (float[])arr_f.clone();
            this.mValues = arr_f1;
            this.mTranslationX = arr_f1[2];
            this.mTranslationY = arr_f1[5];
            this.setAnimationMatrix();
        }

        Matrix getMatrix() {
            return this.mMatrix;
        }

        private void setAnimationMatrix() {
            this.mValues[2] = this.mTranslationX;
            this.mValues[5] = this.mTranslationY;
            this.mMatrix.setValues(this.mValues);
            ViewUtils.setAnimationMatrix(this.mView, this.mMatrix);
        }

        void setTranslation(PointF pointF0) {
            this.mTranslationX = pointF0.x;
            this.mTranslationY = pointF0.y;
            this.setAnimationMatrix();
        }

        void setValues(float[] arr_f) {
            System.arraycopy(arr_f, 0, this.mValues, 0, arr_f.length);
            this.setAnimationMatrix();
        }
    }

    static class Transforms {
        final float mRotationX;
        final float mRotationY;
        final float mRotationZ;
        final float mScaleX;
        final float mScaleY;
        final float mTranslationX;
        final float mTranslationY;
        final float mTranslationZ;

        Transforms(View view0) {
            this.mTranslationX = view0.getTranslationX();
            this.mTranslationY = view0.getTranslationY();
            this.mTranslationZ = ViewCompat.getTranslationZ(view0);
            this.mScaleX = view0.getScaleX();
            this.mScaleY = view0.getScaleY();
            this.mRotationX = view0.getRotationX();
            this.mRotationY = view0.getRotationY();
            this.mRotationZ = view0.getRotation();
        }

        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Transforms ? ((Transforms)object0).mTranslationX == this.mTranslationX && ((Transforms)object0).mTranslationY == this.mTranslationY && ((Transforms)object0).mTranslationZ == this.mTranslationZ && ((Transforms)object0).mScaleX == this.mScaleX && ((Transforms)object0).mScaleY == this.mScaleY && ((Transforms)object0).mRotationX == this.mRotationX && ((Transforms)object0).mRotationY == this.mRotationY && ((Transforms)object0).mRotationZ == this.mRotationZ : false;
        }

        @Override
        public int hashCode() {
            int v = 0;
            int v1 = this.mTranslationX == 0.0f ? 0 : Float.floatToIntBits(this.mTranslationX);
            int v2 = this.mTranslationY == 0.0f ? 0 : Float.floatToIntBits(this.mTranslationY);
            int v3 = this.mTranslationZ == 0.0f ? 0 : Float.floatToIntBits(this.mTranslationZ);
            int v4 = this.mScaleX == 0.0f ? 0 : Float.floatToIntBits(this.mScaleX);
            int v5 = this.mScaleY == 0.0f ? 0 : Float.floatToIntBits(this.mScaleY);
            int v6 = this.mRotationX == 0.0f ? 0 : Float.floatToIntBits(this.mRotationX);
            int v7 = this.mRotationY == 0.0f ? 0 : Float.floatToIntBits(this.mRotationY);
            float f = this.mRotationZ;
            if(f != 0.0f) {
                v = Float.floatToIntBits(f);
            }
            return ((((((v1 * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + v5) * 0x1F + v6) * 0x1F + v7) * 0x1F + v;
        }

        public void restore(View view0) {
            ChangeTransform.setTransforms(view0, this.mTranslationX, this.mTranslationY, this.mTranslationZ, this.mScaleX, this.mScaleY, this.mRotationX, this.mRotationY, this.mRotationZ);
        }
    }

    private static final Property NON_TRANSLATIONS_PROPERTY = null;
    private static final String PROPNAME_INTERMEDIATE_MATRIX = "android:changeTransform:intermediateMatrix";
    private static final String PROPNAME_INTERMEDIATE_PARENT_MATRIX = "android:changeTransform:intermediateParentMatrix";
    private static final String PROPNAME_MATRIX = "android:changeTransform:matrix";
    private static final String PROPNAME_PARENT = "android:changeTransform:parent";
    private static final String PROPNAME_PARENT_MATRIX = "android:changeTransform:parentMatrix";
    private static final String PROPNAME_TRANSFORMS = "android:changeTransform:transforms";
    private static final boolean SUPPORTS_VIEW_REMOVAL_SUPPRESSION;
    private static final Property TRANSLATIONS_PROPERTY;
    private boolean mReparent;
    private Matrix mTempMatrix;
    boolean mUseOverlay;
    private static final String[] sTransitionProperties;

    static {
        ChangeTransform.sTransitionProperties = new String[]{"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
        ChangeTransform.NON_TRANSLATIONS_PROPERTY = new Property(float[].class, "nonTranslations") {
            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((PathAnimatorMatrix)object0));
            }

            public float[] get(PathAnimatorMatrix changeTransform$PathAnimatorMatrix0) {
                return null;
            }

            public void set(PathAnimatorMatrix changeTransform$PathAnimatorMatrix0, float[] arr_f) {
                changeTransform$PathAnimatorMatrix0.setValues(arr_f);
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((PathAnimatorMatrix)object0), ((float[])object1));
            }
        };
        ChangeTransform.TRANSLATIONS_PROPERTY = new Property(PointF.class, "translations") {
            public PointF get(PathAnimatorMatrix changeTransform$PathAnimatorMatrix0) {
                return null;
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((PathAnimatorMatrix)object0));
            }

            public void set(PathAnimatorMatrix changeTransform$PathAnimatorMatrix0, PointF pointF0) {
                changeTransform$PathAnimatorMatrix0.setTranslation(pointF0);
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((PathAnimatorMatrix)object0), ((PointF)object1));
            }
        };
        ChangeTransform.SUPPORTS_VIEW_REMOVAL_SUPPRESSION = true;
    }

    public ChangeTransform() {
        this.mUseOverlay = true;
        this.mReparent = true;
        this.mTempMatrix = new Matrix();
    }

    public ChangeTransform(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mUseOverlay = true;
        this.mReparent = true;
        this.mTempMatrix = new Matrix();
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.CHANGE_TRANSFORM);
        this.mUseOverlay = TypedArrayUtils.getNamedBoolean(typedArray0, ((XmlPullParser)attributeSet0), "reparentWithOverlay", 1, true);
        this.mReparent = TypedArrayUtils.getNamedBoolean(typedArray0, ((XmlPullParser)attributeSet0), "reparent", 0, true);
        typedArray0.recycle();
    }

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
        if(!ChangeTransform.SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
            ((ViewGroup)transitionValues0.view.getParent()).startViewTransition(transitionValues0.view);
        }
    }

    private void captureValues(TransitionValues transitionValues0) {
        View view0 = transitionValues0.view;
        if(view0.getVisibility() != 8) {
            ViewParent viewParent0 = view0.getParent();
            transitionValues0.values.put("android:changeTransform:parent", viewParent0);
            Transforms changeTransform$Transforms0 = new Transforms(view0);
            transitionValues0.values.put("android:changeTransform:transforms", changeTransform$Transforms0);
            Matrix matrix0 = view0.getMatrix();
            Matrix matrix1 = matrix0 == null || matrix0.isIdentity() ? null : new Matrix(matrix0);
            transitionValues0.values.put("android:changeTransform:matrix", matrix1);
            if(this.mReparent) {
                Matrix matrix2 = new Matrix();
                ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
                ViewUtils.transformMatrixToGlobal(viewGroup0, matrix2);
                matrix2.preTranslate(((float)(-viewGroup0.getScrollX())), ((float)(-viewGroup0.getScrollY())));
                transitionValues0.values.put("android:changeTransform:parentMatrix", matrix2);
                Object object0 = view0.getTag(id.transition_transform);
                transitionValues0.values.put("android:changeTransform:intermediateMatrix", object0);
                Object object1 = view0.getTag(id.parent_matrix);
                transitionValues0.values.put("android:changeTransform:intermediateParentMatrix", object1);
            }
        }
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        if(transitionValues0 != null && transitionValues1 != null && transitionValues0.values.containsKey("android:changeTransform:parent") && transitionValues1.values.containsKey("android:changeTransform:parent")) {
            ViewGroup viewGroup1 = (ViewGroup)transitionValues0.values.get("android:changeTransform:parent");
            ViewGroup viewGroup2 = (ViewGroup)transitionValues1.values.get("android:changeTransform:parent");
            boolean z = this.mReparent && !this.parentsMatch(viewGroup1, viewGroup2);
            Matrix matrix0 = (Matrix)transitionValues0.values.get("android:changeTransform:intermediateMatrix");
            if(matrix0 != null) {
                transitionValues0.values.put("android:changeTransform:matrix", matrix0);
            }
            Matrix matrix1 = (Matrix)transitionValues0.values.get("android:changeTransform:intermediateParentMatrix");
            if(matrix1 != null) {
                transitionValues0.values.put("android:changeTransform:parentMatrix", matrix1);
            }
            if(z) {
                this.setMatricesForParent(transitionValues0, transitionValues1);
            }
            Animator animator0 = this.createTransformAnimator(transitionValues0, transitionValues1, z);
            if(z && animator0 != null && this.mUseOverlay) {
                this.createGhostView(viewGroup0, transitionValues0, transitionValues1);
                return animator0;
            }
            if(!ChangeTransform.SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
                viewGroup1.endViewTransition(transitionValues0.view);
            }
            return animator0;
        }
        return null;
    }

    private void createGhostView(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        View view0 = transitionValues1.view;
        Matrix matrix0 = new Matrix(((Matrix)transitionValues1.values.get("android:changeTransform:parentMatrix")));
        ViewUtils.transformMatrixToLocal(viewGroup0, matrix0);
        GhostView ghostView0 = GhostViewUtils.addGhost(view0, viewGroup0, matrix0);
        if(ghostView0 != null) {
            ghostView0.reserveEndViewTransition(((ViewGroup)transitionValues0.values.get("android:changeTransform:parent")), transitionValues0.view);
            ChangeTransform changeTransform0;
            for(changeTransform0 = this; changeTransform0.mParent != null; changeTransform0 = changeTransform0.mParent) {
            }
            changeTransform0.addListener(new GhostListener(view0, ghostView0));
            if(ChangeTransform.SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
                if(transitionValues0.view != transitionValues1.view) {
                    ViewUtils.setTransitionAlpha(transitionValues0.view, 0.0f);
                }
                ViewUtils.setTransitionAlpha(view0, 1.0f);
            }
        }
    }

    private ObjectAnimator createTransformAnimator(TransitionValues transitionValues0, TransitionValues transitionValues1, boolean z) {
        Matrix matrix0 = (Matrix)transitionValues0.values.get("android:changeTransform:matrix");
        Matrix matrix1 = (Matrix)transitionValues1.values.get("android:changeTransform:matrix");
        if(matrix0 == null) {
            matrix0 = MatrixUtils.IDENTITY_MATRIX;
        }
        if(matrix1 == null) {
            matrix1 = MatrixUtils.IDENTITY_MATRIX;
        }
        if(matrix0.equals(matrix1)) {
            return null;
        }
        Object object0 = transitionValues1.values.get("android:changeTransform:transforms");
        View view0 = transitionValues1.view;
        ChangeTransform.setIdentityTransforms(view0);
        float[] arr_f = new float[9];
        matrix0.getValues(arr_f);
        float[] arr_f1 = new float[9];
        matrix1.getValues(arr_f1);
        PathAnimatorMatrix changeTransform$PathAnimatorMatrix0 = new PathAnimatorMatrix(view0, arr_f);
        FloatArrayEvaluator floatArrayEvaluator0 = new FloatArrayEvaluator(new float[9]);
        PropertyValuesHolder propertyValuesHolder0 = PropertyValuesHolder.ofObject(ChangeTransform.NON_TRANSLATIONS_PROPERTY, floatArrayEvaluator0, new float[][]{arr_f, arr_f1});
        Path path0 = this.getPathMotion().getPath(arr_f[2], arr_f[5], arr_f1[2], arr_f1[5]);
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofPropertyValuesHolder(changeTransform$PathAnimatorMatrix0, new PropertyValuesHolder[]{propertyValuesHolder0, PropertyValuesHolderUtils.ofPointF(ChangeTransform.TRANSLATIONS_PROPERTY, path0)});
        Listener changeTransform$Listener0 = new Listener(view0, ((Transforms)object0), changeTransform$PathAnimatorMatrix0, matrix1, z, this.mUseOverlay);
        objectAnimator0.addListener(changeTransform$Listener0);
        objectAnimator0.addPauseListener(changeTransform$Listener0);
        return objectAnimator0;
    }

    public boolean getReparent() {
        return this.mReparent;
    }

    public boolean getReparentWithOverlay() {
        return this.mUseOverlay;
    }

    @Override  // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return ChangeTransform.sTransitionProperties;
    }

    private boolean parentsMatch(ViewGroup viewGroup0, ViewGroup viewGroup1) {
        if(this.isValidTarget(viewGroup0) && this.isValidTarget(viewGroup1)) {
            TransitionValues transitionValues0 = this.getMatchedTransitionValues(viewGroup0, true);
            return transitionValues0 != null && viewGroup1 == transitionValues0.view;
        }
        return viewGroup0 == viewGroup1;
    }

    static void setIdentityTransforms(View view0) {
        ChangeTransform.setTransforms(view0, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    private void setMatricesForParent(TransitionValues transitionValues0, TransitionValues transitionValues1) {
        Matrix matrix0 = (Matrix)transitionValues1.values.get("android:changeTransform:parentMatrix");
        transitionValues1.view.setTag(id.parent_matrix, matrix0);
        Matrix matrix1 = this.mTempMatrix;
        matrix1.reset();
        matrix0.invert(matrix1);
        Matrix matrix2 = (Matrix)transitionValues0.values.get("android:changeTransform:matrix");
        if(matrix2 == null) {
            matrix2 = new Matrix();
            transitionValues0.values.put("android:changeTransform:matrix", matrix2);
        }
        matrix2.postConcat(((Matrix)transitionValues0.values.get("android:changeTransform:parentMatrix")));
        matrix2.postConcat(matrix1);
    }

    public void setReparent(boolean z) {
        this.mReparent = z;
    }

    public void setReparentWithOverlay(boolean z) {
        this.mUseOverlay = z;
    }

    static void setTransforms(View view0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        view0.setTranslationX(f);
        view0.setTranslationY(f1);
        ViewCompat.setTranslationZ(view0, f2);
        view0.setScaleX(f3);
        view0.setScaleY(f4);
        view0.setRotationX(f5);
        view0.setRotationY(f6);
        view0.setRotation(f7);
    }
}

