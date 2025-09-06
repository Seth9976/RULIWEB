package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ViewSpline extends SplineSet {
    static class AlphaSet extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setAlpha(this.get(f));
        }
    }

    public static class CustomSet extends ViewSpline {
        String mAttributeName;
        SparseArray mConstraintAttributeList;
        float[] mTempValues;

        public CustomSet(String s, SparseArray sparseArray0) {
            this.mAttributeName = s.split(",")[1];
            this.mConstraintAttributeList = sparseArray0;
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setPoint(int v, float f) {
            throw new RuntimeException("call of custom attribute setPoint");
        }

        public void setPoint(int v, ConstraintAttribute constraintAttribute0) {
            this.mConstraintAttributeList.append(v, constraintAttribute0);
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            this.mCurveFit.getPos(((double)f), this.mTempValues);
            CustomSupport.setInterpolatedValue(((ConstraintAttribute)this.mConstraintAttributeList.valueAt(0)), view0, this.mTempValues);
        }

        @Override  // androidx.constraintlayout.core.motion.utils.SplineSet
        public void setup(int v) {
            int v1 = this.mConstraintAttributeList.size();
            int v2 = ((ConstraintAttribute)this.mConstraintAttributeList.valueAt(0)).numberOfInterpolatedValues();
            double[] arr_f = new double[v1];
            this.mTempValues = new float[v2];
            double[][] arr2_f = new double[v1][v2];
            for(int v3 = 0; v3 < v1; ++v3) {
                int v4 = this.mConstraintAttributeList.keyAt(v3);
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mConstraintAttributeList.valueAt(v3);
                arr_f[v3] = ((double)v4) * 0.01;
                constraintAttribute0.getValuesToInterpolate(this.mTempValues);
                for(int v5 = 0; true; ++v5) {
                    float[] arr_f1 = this.mTempValues;
                    if(v5 >= arr_f1.length) {
                        break;
                    }
                    arr2_f[v3][v5] = (double)arr_f1[v5];
                }
            }
            this.mCurveFit = CurveFit.get(v, arr_f, arr2_f);
        }
    }

    static class ElevationSet extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setElevation(this.get(f));
        }
    }

    public static class PathRotate extends ViewSpline {
        public void setPathRotate(View view0, float f, double f1, double f2) {
            view0.setRotation(this.get(f) + ((float)Math.toDegrees(Math.atan2(f2, f1))));
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
        }
    }

    static class PivotXset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setPivotX(this.get(f));
        }
    }

    static class PivotYset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setPivotY(this.get(f));
        }
    }

    static class ProgressSet extends ViewSpline {
        boolean mNoMethod;

        ProgressSet() {
            this.mNoMethod = false;
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            Method method0;
            if(view0 instanceof MotionLayout) {
                ((MotionLayout)view0).setProgress(this.get(f));
                return;
            }
            if(!this.mNoMethod) {
                try {
                    method0 = view0.getClass().getMethod("setProgress", Float.TYPE);
                }
                catch(NoSuchMethodException unused_ex) {
                    this.mNoMethod = true;
                    method0 = null;
                }
                if(method0 != null) {
                    try {
                        method0.invoke(view0, this.get(f));
                    }
                    catch(IllegalAccessException illegalAccessException0) {
                        Log.e("ViewSpline", "unable to setProgress", illegalAccessException0);
                    }
                    catch(InvocationTargetException invocationTargetException0) {
                        Log.e("ViewSpline", "unable to setProgress", invocationTargetException0);
                    }
                }
            }
        }
    }

    static class RotationSet extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setRotation(this.get(f));
        }
    }

    static class RotationXset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setRotationX(this.get(f));
        }
    }

    static class RotationYset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setRotationY(this.get(f));
        }
    }

    static class ScaleXset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setScaleX(this.get(f));
        }
    }

    static class ScaleYset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setScaleY(this.get(f));
        }
    }

    static class TranslationXset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setTranslationX(this.get(f));
        }
    }

    static class TranslationYset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setTranslationY(this.get(f));
        }
    }

    static class TranslationZset extends ViewSpline {
        @Override  // androidx.constraintlayout.motion.utils.ViewSpline
        public void setProperty(View view0, float f) {
            view0.setTranslationZ(this.get(f));
        }
    }

    private static final String TAG = "ViewSpline";

    public static ViewSpline makeCustomSpline(String s, SparseArray sparseArray0) {
        return new CustomSet(s, sparseArray0);
    }

    public static ViewSpline makeSpline(String s) {
        s.hashCode();
        switch(s) {
            case "alpha": {
                return new AlphaSet();
            }
            case "elevation": {
                return new ElevationSet();
            }
            case "progress": {
                return new ProgressSet();
            }
            case "rotation": {
                return new RotationSet();
            }
            case "rotationX": {
                return new RotationXset();
            }
            case "rotationY": {
                return new RotationYset();
            }
            case "scaleX": {
                return new ScaleXset();
            }
            case "scaleY": {
                return new ScaleYset();
            }
            case "transformPivotX": {
                return new PivotXset();
            }
            case "transformPivotY": {
                return new PivotYset();
            }
            case "transitionPathRotate": {
                return new PathRotate();
            }
            case "translationX": {
                return new TranslationXset();
            }
            case "translationY": {
                return new TranslationYset();
            }
            case "translationZ": {
                return new TranslationZset();
            }
            case "waveOffset": {
                return new AlphaSet();
            }
            case "waveVariesBy": {
                return new AlphaSet();
            }
            default: {
                return null;
            }
        }
    }

    public abstract void setProperty(View arg1, float arg2);
}

