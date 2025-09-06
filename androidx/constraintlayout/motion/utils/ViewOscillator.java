package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ViewOscillator extends KeyCycleOscillator {
    static class AlphaSet extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setAlpha(this.get(f));
        }
    }

    static class CustomSet extends ViewOscillator {
        protected ConstraintAttribute mCustom;
        float[] mValue;

        CustomSet() {
            this.mValue = new float[1];
        }

        @Override  // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        protected void setCustom(Object object0) {
            this.mCustom = (ConstraintAttribute)object0;
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            float[] arr_f = this.mValue;
            arr_f[0] = this.get(f);
            CustomSupport.setInterpolatedValue(this.mCustom, view0, this.mValue);
        }
    }

    static class ElevationSet extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setElevation(this.get(f));
        }
    }

    public static class PathRotateSet extends ViewOscillator {
        public void setPathRotate(View view0, float f, double f1, double f2) {
            view0.setRotation(this.get(f) + ((float)Math.toDegrees(Math.atan2(f2, f1))));
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
        }
    }

    static class ProgressSet extends ViewOscillator {
        boolean mNoMethod;

        ProgressSet() {
            this.mNoMethod = false;
        }

        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
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
                        Log.e("ViewOscillator", "unable to setProgress", illegalAccessException0);
                    }
                    catch(InvocationTargetException invocationTargetException0) {
                        Log.e("ViewOscillator", "unable to setProgress", invocationTargetException0);
                    }
                }
            }
        }
    }

    static class RotationSet extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setRotation(this.get(f));
        }
    }

    static class RotationXset extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setRotationX(this.get(f));
        }
    }

    static class RotationYset extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setRotationY(this.get(f));
        }
    }

    static class ScaleXset extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setScaleX(this.get(f));
        }
    }

    static class ScaleYset extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setScaleY(this.get(f));
        }
    }

    static class TranslationXset extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setTranslationX(this.get(f));
        }
    }

    static class TranslationYset extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setTranslationY(this.get(f));
        }
    }

    static class TranslationZset extends ViewOscillator {
        @Override  // androidx.constraintlayout.motion.utils.ViewOscillator
        public void setProperty(View view0, float f) {
            view0.setTranslationZ(this.get(f));
        }
    }

    private static final String TAG = "ViewOscillator";

    public static ViewOscillator makeSpline(String s) {
        if(s.startsWith("CUSTOM")) {
            return new CustomSet();
        }
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
            case "transitionPathRotate": {
                return new PathRotateSet();
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

