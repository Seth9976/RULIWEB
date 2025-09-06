package com.google.android.material.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Shader;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import androidx.core.graphics.PathParser;
import androidx.transition.PathMotion;
import androidx.transition.PatternPathMotion;
import androidx.transition.Transition;
import androidx.transition.TransitionSet;
import com.google.android.material.canvas.CanvasCompat.CanvasOperation;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;

class TransitionUtils {
    interface CornerSizeBinaryOperator {
        CornerSize apply(CornerSize arg1, CornerSize arg2);
    }

    static final int NO_ATTR_RES_ID = 0;
    static final int NO_DURATION = -1;
    private static final int PATH_TYPE_ARC = 1;
    private static final int PATH_TYPE_LINEAR;
    private static final RectF transformAlphaRectF;

    static {
        TransitionUtils.transformAlphaRectF = new RectF();
    }

    static float calculateArea(RectF rectF0) {
        return rectF0.width() * rectF0.height();
    }

    static ShapeAppearanceModel convertToRelativeCornerSizes(ShapeAppearanceModel shapeAppearanceModel0, RectF rectF0) {
        return shapeAppearanceModel0.withTransformedCornerSizes((CornerSize cornerSize0) -> RelativeCornerSize.createFromCornerSize(rectF0, cornerSize0));
    }

    static Shader createColorShader(int v) {
        return new LinearGradient(0.0f, 0.0f, 0.0f, 0.0f, v, v, Shader.TileMode.CLAMP);
    }

    static Object defaultIfNull(Object object0, Object object1) {
        return object0 == null ? object1 : object0;
    }

    static View findAncestorById(View view0, int v) {
        String s = view0.getResources().getResourceName(v);
        while(view0 != null) {
            if(view0.getId() == v) {
                return view0;
            }
            ViewParent viewParent0 = view0.getParent();
            if(!(viewParent0 instanceof View)) {
                break;
            }
            view0 = (View)viewParent0;
        }
        throw new IllegalArgumentException(s + " is not a valid ancestor");
    }

    static View findDescendantOrAncestorById(View view0, int v) {
        View view1 = view0.findViewById(v);
        return view1 == null ? TransitionUtils.findAncestorById(view0, v) : view1;
    }

    static RectF getLocationOnScreen(View view0) {
        int[] arr_v = new int[2];
        view0.getLocationOnScreen(arr_v);
        int v = arr_v[0];
        int v1 = arr_v[1];
        return new RectF(((float)v), ((float)v1), ((float)(view0.getWidth() + v)), ((float)(view0.getHeight() + v1)));
    }

    static RectF getRelativeBounds(View view0) {
        return new RectF(((float)view0.getLeft()), ((float)view0.getTop()), ((float)view0.getRight()), ((float)view0.getBottom()));
    }

    static Rect getRelativeBoundsRect(View view0) {
        return new Rect(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
    }

    private static boolean isShapeAppearanceSignificant(ShapeAppearanceModel shapeAppearanceModel0, RectF rectF0) {
        return shapeAppearanceModel0.getTopLeftCornerSize().getCornerSize(rectF0) != 0.0f || shapeAppearanceModel0.getTopRightCornerSize().getCornerSize(rectF0) != 0.0f || shapeAppearanceModel0.getBottomRightCornerSize().getCornerSize(rectF0) != 0.0f || shapeAppearanceModel0.getBottomLeftCornerSize().getCornerSize(rectF0) != 0.0f;
    }

    // 检测为 Lambda 实现
    static CornerSize lambda$convertToRelativeCornerSizes$0(RectF rectF0, CornerSize cornerSize0) [...]

    static float lerp(float f, float f1, float f2) [...] // Inlined contents

    static float lerp(float f, float f1, float f2, float f3, float f4) {
        return TransitionUtils.lerp(f, f1, f2, f3, f4, false);
    }

    static float lerp(float f, float f1, float f2, float f3, float f4, boolean z) {
        if(z && (f4 < 0.0f || f4 > 1.0f)) {
            return f + f4 * (f1 - f);
        }
        if(f4 < f2) {
            return f;
        }
        return f4 > f3 ? f1 : TransitionUtils.lerp(f, f1, (f4 - f2) / (f3 - f2));
    }

    static int lerp(int v, int v1, float f, float f1, float f2) {
        if(f2 < f) {
            return v;
        }
        return f2 > f1 ? v1 : ((int)TransitionUtils.lerp(v, v1, (f2 - f) / (f1 - f)));
    }

    static ShapeAppearanceModel lerp(ShapeAppearanceModel shapeAppearanceModel0, ShapeAppearanceModel shapeAppearanceModel1, RectF rectF0, RectF rectF1, float f, float f1, float f2) {
        if(f2 < f) {
            return shapeAppearanceModel0;
        }
        return f2 > f1 ? shapeAppearanceModel1 : TransitionUtils.transformCornerSizes(shapeAppearanceModel0, shapeAppearanceModel1, rectF0, new CornerSizeBinaryOperator() {
            @Override  // com.google.android.material.transition.TransitionUtils$CornerSizeBinaryOperator
            public CornerSize apply(CornerSize cornerSize0, CornerSize cornerSize1) {
                return new AbsoluteCornerSize(TransitionUtils.lerp(cornerSize0.getCornerSize(rectF0), cornerSize1.getCornerSize(rectF1), f, f1, f2));
            }
        });
    }

    static void maybeAddTransition(TransitionSet transitionSet0, Transition transition0) {
        if(transition0 != null) {
            transitionSet0.addTransition(transition0);
        }
    }

    static boolean maybeApplyThemeDuration(Transition transition0, Context context0, int v) {
        if(v != 0 && transition0.getDuration() == -1L) {
            int v1 = MotionUtils.resolveThemeDuration(context0, v, -1);
            if(v1 != -1) {
                transition0.setDuration(((long)v1));
                return true;
            }
        }
        return false;
    }

    static boolean maybeApplyThemeInterpolator(Transition transition0, Context context0, int v, TimeInterpolator timeInterpolator0) {
        if(v != 0 && transition0.getInterpolator() == null) {
            transition0.setInterpolator(MotionUtils.resolveThemeInterpolator(context0, v, timeInterpolator0));
            return true;
        }
        return false;
    }

    static boolean maybeApplyThemePath(Transition transition0, Context context0, int v) {
        if(v != 0) {
            PathMotion pathMotion0 = TransitionUtils.resolveThemePath(context0, v);
            if(pathMotion0 != null) {
                transition0.setPathMotion(pathMotion0);
                return true;
            }
        }
        return false;
    }

    static void maybeRemoveTransition(TransitionSet transitionSet0, Transition transition0) {
        if(transition0 != null) {
            transitionSet0.removeTransition(transition0);
        }
    }

    static PathMotion resolveThemePath(Context context0, int v) {
        TypedValue typedValue0 = new TypedValue();
        if(context0.getTheme().resolveAttribute(v, typedValue0, true)) {
            switch(typedValue0.type) {
                case 3: {
                    return new PatternPathMotion(PathParser.createPathFromPathData(String.valueOf(typedValue0.string)));
                }
                case 16: {
                    int v1 = typedValue0.data;
                    if(v1 == 0) {
                        return null;
                    }
                    if(v1 != 1) {
                        throw new IllegalArgumentException("Invalid motion path type: " + v1);
                    }
                    return new MaterialArcMotion();
                }
                default: {
                    throw new IllegalArgumentException("Motion path theme attribute must either be an enum value or path data string");
                }
            }
        }
        return null;
    }

    private static int saveLayerAlphaCompat(Canvas canvas0, Rect rect0, int v) {
        TransitionUtils.transformAlphaRectF.set(rect0);
        return canvas0.saveLayerAlpha(TransitionUtils.transformAlphaRectF, v);
    }

    static void transform(Canvas canvas0, Rect rect0, float f, float f1, float f2, int v, CanvasOperation canvasCompat$CanvasOperation0) {
        if(v <= 0) {
            return;
        }
        int v1 = canvas0.save();
        canvas0.translate(f, f1);
        canvas0.scale(f2, f2);
        if(v < 0xFF) {
            TransitionUtils.saveLayerAlphaCompat(canvas0, rect0, v);
        }
        canvasCompat$CanvasOperation0.run(canvas0);
        canvas0.restoreToCount(v1);
    }

    // 去混淆评级： 低(20)
    static ShapeAppearanceModel transformCornerSizes(ShapeAppearanceModel shapeAppearanceModel0, ShapeAppearanceModel shapeAppearanceModel1, RectF rectF0, CornerSizeBinaryOperator transitionUtils$CornerSizeBinaryOperator0) {
        return TransitionUtils.isShapeAppearanceSignificant(shapeAppearanceModel0, rectF0) ? shapeAppearanceModel0.toBuilder().setTopLeftCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getTopLeftCornerSize(), shapeAppearanceModel1.getTopLeftCornerSize())).setTopRightCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getTopRightCornerSize(), shapeAppearanceModel1.getTopRightCornerSize())).setBottomLeftCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getBottomLeftCornerSize(), shapeAppearanceModel1.getBottomLeftCornerSize())).setBottomRightCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getBottomRightCornerSize(), shapeAppearanceModel1.getBottomRightCornerSize())).build() : shapeAppearanceModel1.toBuilder().setTopLeftCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getTopLeftCornerSize(), shapeAppearanceModel1.getTopLeftCornerSize())).setTopRightCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getTopRightCornerSize(), shapeAppearanceModel1.getTopRightCornerSize())).setBottomLeftCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getBottomLeftCornerSize(), shapeAppearanceModel1.getBottomLeftCornerSize())).setBottomRightCornerSize(transitionUtils$CornerSizeBinaryOperator0.apply(shapeAppearanceModel0.getBottomRightCornerSize(), shapeAppearanceModel1.getBottomRightCornerSize())).build();
    }
}

