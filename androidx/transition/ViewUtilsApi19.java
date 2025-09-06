package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewUtilsApi19 {
    static class Api29Impl {
        static float getTransitionAlpha(View view0) {
            return view0.getTransitionAlpha();
        }

        static void setTransitionAlpha(View view0, float f) {
            view0.setTransitionAlpha(f);
        }
    }

    private static final String TAG = "ViewUtilsApi19";
    private static final int VISIBILITY_MASK = 12;
    private float[] mMatrixValues;
    private static boolean sSetFrameFetched = false;
    private static Method sSetFrameMethod = null;
    private static boolean sTryHiddenTransitionAlpha = true;
    private static Field sViewFlagsField;
    private static boolean sViewFlagsFieldFetched;

    static {
    }

    public void clearNonTransitionAlpha(View view0) {
    }

    private void fetchSetFrame() {
        if(!ViewUtilsApi19.sSetFrameFetched) {
            try {
                Method method0 = View.class.getDeclaredMethod("setFrame", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
                ViewUtilsApi19.sSetFrameMethod = method0;
                method0.setAccessible(true);
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.i("ViewUtilsApi19", "Failed to retrieve setFrame method", noSuchMethodException0);
            }
            ViewUtilsApi19.sSetFrameFetched = true;
        }
    }

    public float getTransitionAlpha(View view0) {
        if(ViewUtilsApi19.sTryHiddenTransitionAlpha) {
            try {
                return Api29Impl.getTransitionAlpha(view0);
            }
            catch(NoSuchMethodError unused_ex) {
                ViewUtilsApi19.sTryHiddenTransitionAlpha = false;
            }
        }
        return view0.getAlpha();
    }

    public void saveNonTransitionAlpha(View view0) {
    }

    public void setAnimationMatrix(View view0, Matrix matrix0) {
        if(matrix0 != null && !matrix0.isIdentity()) {
            float[] arr_f = this.mMatrixValues;
            if(arr_f == null) {
                arr_f = new float[9];
                this.mMatrixValues = arr_f;
            }
            matrix0.getValues(arr_f);
            float f = arr_f[3];
            float f1 = ((float)Math.sqrt(1.0f - f * f)) * ((float)(arr_f[0] < 0.0f ? -1 : 1));
            float f2 = arr_f[0] / f1;
            float f3 = arr_f[4] / f1;
            float f4 = arr_f[2];
            float f5 = arr_f[5];
            view0.setPivotX(0.0f);
            view0.setPivotY(0.0f);
            view0.setTranslationX(f4);
            view0.setTranslationY(f5);
            view0.setRotation(((float)Math.toDegrees(Math.atan2(f, f1))));
            view0.setScaleX(f2);
            view0.setScaleY(f3);
            return;
        }
        view0.setPivotX(((float)(view0.getWidth() / 2)));
        view0.setPivotY(((float)(view0.getHeight() / 2)));
        view0.setTranslationX(0.0f);
        view0.setTranslationY(0.0f);
        view0.setScaleX(1.0f);
        view0.setScaleY(1.0f);
        view0.setRotation(0.0f);
    }

    public void setLeftTopRightBottom(View view0, int v, int v1, int v2, int v3) {
        this.fetchSetFrame();
        Method method0 = ViewUtilsApi19.sSetFrameMethod;
        if(method0 != null) {
            try {
                method0.invoke(view0, v, v1, v2, v3);
                return;
            }
            catch(IllegalAccessException invocationTargetException0) {
            }
            catch(InvocationTargetException unused_ex) {
                return;
            }
            throw new RuntimeException(invocationTargetException0.getCause());
        }
    }

    public void setTransitionAlpha(View view0, float f) {
        if(ViewUtilsApi19.sTryHiddenTransitionAlpha) {
            try {
                Api29Impl.setTransitionAlpha(view0, f);
                return;
            }
            catch(NoSuchMethodError unused_ex) {
                ViewUtilsApi19.sTryHiddenTransitionAlpha = false;
            }
        }
        view0.setAlpha(f);
    }

    public void setTransitionVisibility(View view0, int v) {
        if(!ViewUtilsApi19.sViewFlagsFieldFetched) {
            try {
                Field field0 = View.class.getDeclaredField("mViewFlags");
                ViewUtilsApi19.sViewFlagsField = field0;
                field0.setAccessible(true);
            }
            catch(NoSuchFieldException unused_ex) {
                Log.i("ViewUtilsApi19", "fetchViewFlagsField: ");
            }
            ViewUtilsApi19.sViewFlagsFieldFetched = true;
        }
        Field field1 = ViewUtilsApi19.sViewFlagsField;
        if(field1 != null) {
            try {
                int v1 = field1.getInt(view0);
                ViewUtilsApi19.sViewFlagsField.setInt(view0, v | v1 & -13);
            }
            catch(IllegalAccessException unused_ex) {
            }
        }
    }

    public void transformMatrixToGlobal(View view0, Matrix matrix0) {
        ViewParent viewParent0 = view0.getParent();
        if(viewParent0 instanceof View) {
            this.transformMatrixToGlobal(((View)viewParent0), matrix0);
            matrix0.preTranslate(((float)(-((View)viewParent0).getScrollX())), ((float)(-((View)viewParent0).getScrollY())));
        }
        matrix0.preTranslate(((float)view0.getLeft()), ((float)view0.getTop()));
        Matrix matrix1 = view0.getMatrix();
        if(!matrix1.isIdentity()) {
            matrix0.preConcat(matrix1);
        }
    }

    public void transformMatrixToLocal(View view0, Matrix matrix0) {
        ViewParent viewParent0 = view0.getParent();
        if(viewParent0 instanceof View) {
            this.transformMatrixToLocal(((View)viewParent0), matrix0);
            matrix0.postTranslate(((float)((View)viewParent0).getScrollX()), ((float)((View)viewParent0).getScrollY()));
        }
        matrix0.postTranslate(((float)(-view0.getLeft())), ((float)(-view0.getTop())));
        Matrix matrix1 = view0.getMatrix();
        if(!matrix1.isIdentity()) {
            Matrix matrix2 = new Matrix();
            if(matrix1.invert(matrix2)) {
                matrix0.postConcat(matrix2);
            }
        }
    }
}

