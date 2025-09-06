package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Property;
import android.view.View;

class ViewUtils {
    static final Property CLIP_BOUNDS = null;
    private static final ViewUtilsApi19 IMPL = null;
    private static final String TAG = "ViewUtils";
    static final Property TRANSITION_ALPHA;

    static {
        if(Build.VERSION.SDK_INT >= 29) {
            ViewUtils.IMPL = new ViewUtilsApi29();
        }
        else if(Build.VERSION.SDK_INT >= 23) {
            ViewUtils.IMPL = new ViewUtilsApi23();
        }
        else if(Build.VERSION.SDK_INT >= 22) {
            ViewUtils.IMPL = new ViewUtilsApi22();
        }
        else {
            ViewUtils.IMPL = new ViewUtilsApi21();
        }
        ViewUtils.TRANSITION_ALPHA = new Property(Float.class, "translationAlpha") {
            public Float get(View view0) {
                return ViewUtils.getTransitionAlpha(view0);
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, Float float0) {
                ViewUtils.setTransitionAlpha(view0, ((float)float0));
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((Float)object1));
            }
        };
        ViewUtils.CLIP_BOUNDS = new Property(Rect.class, "clipBounds") {
            public Rect get(View view0) {
                return view0.getClipBounds();
            }

            @Override  // android.util.Property
            public Object get(Object object0) {
                return this.get(((View)object0));
            }

            public void set(View view0, Rect rect0) {
                view0.setClipBounds(rect0);
            }

            @Override  // android.util.Property
            public void set(Object object0, Object object1) {
                this.set(((View)object0), ((Rect)object1));
            }
        };
    }

    static void clearNonTransitionAlpha(View view0) {
    }

    static float getTransitionAlpha(View view0) {
        return ViewUtils.IMPL.getTransitionAlpha(view0);
    }

    static void saveNonTransitionAlpha(View view0) {
    }

    static void setAnimationMatrix(View view0, Matrix matrix0) {
        ViewUtils.IMPL.setAnimationMatrix(view0, matrix0);
    }

    static void setLeftTopRightBottom(View view0, int v, int v1, int v2, int v3) {
        ViewUtils.IMPL.setLeftTopRightBottom(view0, v, v1, v2, v3);
    }

    static void setTransitionAlpha(View view0, float f) {
        ViewUtils.IMPL.setTransitionAlpha(view0, f);
    }

    static void setTransitionVisibility(View view0, int v) {
        ViewUtils.IMPL.setTransitionVisibility(view0, v);
    }

    static void transformMatrixToGlobal(View view0, Matrix matrix0) {
        ViewUtils.IMPL.transformMatrixToGlobal(view0, matrix0);
    }

    static void transformMatrixToLocal(View view0, Matrix matrix0) {
        ViewUtils.IMPL.transformMatrixToLocal(view0, matrix0);
    }
}

