package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.R.styleable;
import java.util.HashMap;

public class KeyPosition extends KeyPositionBase {
    static class Loader {
        private static final int CURVE_FIT = 4;
        private static final int DRAW_PATH = 5;
        private static final int FRAME_POSITION = 2;
        private static final int PATH_MOTION_ARC = 10;
        private static final int PERCENT_HEIGHT = 12;
        private static final int PERCENT_WIDTH = 11;
        private static final int PERCENT_X = 6;
        private static final int PERCENT_Y = 7;
        private static final int SIZE_PERCENT = 8;
        private static final int TARGET_ID = 1;
        private static final int TRANSITION_EASING = 3;
        private static final int TYPE = 9;
        private static SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Loader.sAttrMap = sparseIntArray0;
            sparseIntArray0.append(styleable.KeyPosition_motionTarget, 1);
            Loader.sAttrMap.append(styleable.KeyPosition_framePosition, 2);
            Loader.sAttrMap.append(styleable.KeyPosition_transitionEasing, 3);
            Loader.sAttrMap.append(styleable.KeyPosition_curveFit, 4);
            Loader.sAttrMap.append(styleable.KeyPosition_drawPath, 5);
            Loader.sAttrMap.append(styleable.KeyPosition_percentX, 6);
            Loader.sAttrMap.append(styleable.KeyPosition_percentY, 7);
            Loader.sAttrMap.append(styleable.KeyPosition_keyPositionType, 9);
            Loader.sAttrMap.append(styleable.KeyPosition_sizePercent, 8);
            Loader.sAttrMap.append(styleable.KeyPosition_percentWidth, 11);
            Loader.sAttrMap.append(styleable.KeyPosition_percentHeight, 12);
            Loader.sAttrMap.append(styleable.KeyPosition_pathMotionArc, 10);
        }

        private static void read(KeyPosition keyPosition0, TypedArray typedArray0) {
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Loader.sAttrMap.get(v2)) {
                    case 1: {
                        if(MotionLayout.IS_IN_EDIT_MODE) {
                            keyPosition0.mTargetId = typedArray0.getResourceId(v2, keyPosition0.mTargetId);
                            if(keyPosition0.mTargetId == -1) {
                                keyPosition0.mTargetString = typedArray0.getString(v2);
                            }
                        }
                        else if(typedArray0.peekValue(v2).type == 3) {
                            keyPosition0.mTargetString = typedArray0.getString(v2);
                        }
                        else {
                            keyPosition0.mTargetId = typedArray0.getResourceId(v2, keyPosition0.mTargetId);
                        }
                        break;
                    }
                    case 2: {
                        keyPosition0.mFramePosition = typedArray0.getInt(v2, keyPosition0.mFramePosition);
                        break;
                    }
                    case 3: {
                        if(typedArray0.peekValue(v2).type == 3) {
                            keyPosition0.mTransitionEasing = typedArray0.getString(v2);
                        }
                        else {
                            String[] arr_s = Easing.NAMED_EASING;
                            keyPosition0.mTransitionEasing = arr_s[typedArray0.getInteger(v2, 0)];
                        }
                        break;
                    }
                    case 4: {
                        keyPosition0.mCurveFit = typedArray0.getInteger(v2, keyPosition0.mCurveFit);
                        break;
                    }
                    case 5: {
                        keyPosition0.mDrawPath = typedArray0.getInt(v2, keyPosition0.mDrawPath);
                        break;
                    }
                    case 6: {
                        keyPosition0.mPercentX = typedArray0.getFloat(v2, keyPosition0.mPercentX);
                        break;
                    }
                    case 7: {
                        keyPosition0.mPercentY = typedArray0.getFloat(v2, keyPosition0.mPercentY);
                        break;
                    }
                    case 8: {
                        float f = typedArray0.getFloat(v2, keyPosition0.mPercentHeight);
                        keyPosition0.mPercentWidth = f;
                        keyPosition0.mPercentHeight = f;
                        break;
                    }
                    case 9: {
                        keyPosition0.mPositionType = typedArray0.getInt(v2, keyPosition0.mPositionType);
                        break;
                    }
                    case 10: {
                        keyPosition0.mPathMotionArc = typedArray0.getInt(v2, keyPosition0.mPathMotionArc);
                        break;
                    }
                    case 11: {
                        keyPosition0.mPercentWidth = typedArray0.getFloat(v2, keyPosition0.mPercentWidth);
                        break;
                    }
                    case 12: {
                        keyPosition0.mPercentHeight = typedArray0.getFloat(v2, keyPosition0.mPercentHeight);
                        break;
                    }
                    default: {
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(v2) + "   " + Loader.sAttrMap.get(v2));
                    }
                }
            }
            if(keyPosition0.mFramePosition == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public static final String DRAWPATH = "drawPath";
    static final int KEY_TYPE = 2;
    static final String NAME = "KeyPosition";
    public static final String PERCENT_HEIGHT = "percentHeight";
    public static final String PERCENT_WIDTH = "percentWidth";
    public static final String PERCENT_X = "percentX";
    public static final String PERCENT_Y = "percentY";
    public static final String SIZE_PERCENT = "sizePercent";
    private static final String TAG = "KeyPosition";
    public static final String TRANSITION_EASING = "transitionEasing";
    public static final int TYPE_AXIS = 3;
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;
    float mAltPercentX;
    float mAltPercentY;
    private float mCalculatedPositionX;
    private float mCalculatedPositionY;
    int mDrawPath;
    int mPathMotionArc;
    float mPercentHeight;
    float mPercentWidth;
    float mPercentX;
    float mPercentY;
    int mPositionType;
    String mTransitionEasing;

    public KeyPosition() {
        this.mTransitionEasing = null;
        this.mPathMotionArc = KeyPosition.UNSET;
        this.mDrawPath = 0;
        this.mPercentWidth = NaNf;
        this.mPercentHeight = NaNf;
        this.mPercentX = NaNf;
        this.mPercentY = NaNf;
        this.mAltPercentX = NaNf;
        this.mAltPercentY = NaNf;
        this.mPositionType = 0;
        this.mCalculatedPositionX = NaNf;
        this.mCalculatedPositionY = NaNf;
        this.mType = 2;
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap hashMap0) {
    }

    private void calcCartesianPosition(float f, float f1, float f2, float f3) {
        float f4 = f2 - f;
        float f5 = f3 - f1;
        float f6 = 0.0f;
        float f7 = Float.isNaN(this.mPercentX) ? 0.0f : this.mPercentX;
        float f8 = Float.isNaN(this.mAltPercentY) ? 0.0f : this.mAltPercentY;
        float f9 = Float.isNaN(this.mPercentY) ? 0.0f : this.mPercentY;
        if(!Float.isNaN(this.mAltPercentX)) {
            f6 = this.mAltPercentX;
        }
        this.mCalculatedPositionX = (float)(((int)(f + f7 * f4 + f6 * f5)));
        this.mCalculatedPositionY = (float)(((int)(f1 + f4 * f8 + f5 * f9)));
    }

    private void calcPathPosition(float f, float f1, float f2, float f3) {
        float f4 = f2 - f;
        float f5 = f3 - f1;
        this.mCalculatedPositionX = f + f4 * this.mPercentX + -f5 * this.mPercentY;
        this.mCalculatedPositionY = f1 + f5 * this.mPercentX + f4 * this.mPercentY;
    }

    @Override  // androidx.constraintlayout.motion.widget.KeyPositionBase
    void calcPosition(int v, int v1, float f, float f1, float f2, float f3) {
        switch(this.mPositionType) {
            case 1: {
                this.calcPathPosition(f, f1, f2, f3);
                return;
            }
            case 2: {
                this.calcScreenPosition(v, v1);
                return;
            }
            default: {
                this.calcCartesianPosition(f, f1, f2, f3);
            }
        }
    }

    private void calcScreenPosition(int v, int v1) {
        this.mCalculatedPositionX = ((float)v) * this.mPercentX + 0.0f;
        this.mCalculatedPositionY = ((float)v1) * this.mPercentX + 0.0f;
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key clone() {
        return new KeyPosition().copy(this);
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key0) {
        super.copy(key0);
        this.mTransitionEasing = ((KeyPosition)key0).mTransitionEasing;
        this.mPathMotionArc = ((KeyPosition)key0).mPathMotionArc;
        this.mDrawPath = ((KeyPosition)key0).mDrawPath;
        this.mPercentWidth = ((KeyPosition)key0).mPercentWidth;
        this.mPercentHeight = NaNf;
        this.mPercentX = ((KeyPosition)key0).mPercentX;
        this.mPercentY = ((KeyPosition)key0).mPercentY;
        this.mAltPercentX = ((KeyPosition)key0).mAltPercentX;
        this.mAltPercentY = ((KeyPosition)key0).mAltPercentY;
        this.mCalculatedPositionX = ((KeyPosition)key0).mCalculatedPositionX;
        this.mCalculatedPositionY = ((KeyPosition)key0).mCalculatedPositionY;
        return this;
    }

    @Override  // androidx.constraintlayout.motion.widget.KeyPositionBase
    float getPositionX() {
        return this.mCalculatedPositionX;
    }

    @Override  // androidx.constraintlayout.motion.widget.KeyPositionBase
    float getPositionY() {
        return this.mCalculatedPositionY;
    }

    @Override  // androidx.constraintlayout.motion.widget.KeyPositionBase
    public boolean intersects(int v, int v1, RectF rectF0, RectF rectF1, float f, float f1) {
        this.calcPosition(v, v1, rectF0.centerX(), rectF0.centerY(), rectF1.centerX(), rectF1.centerY());
        return Math.abs(f - this.mCalculatedPositionX) < 20.0f && Math.abs(f1 - this.mCalculatedPositionY) < 20.0f;
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void load(Context context0, AttributeSet attributeSet0) {
        Loader.read(this, context0.obtainStyledAttributes(attributeSet0, styleable.KeyPosition));
    }

    @Override  // androidx.constraintlayout.motion.widget.KeyPositionBase
    public void positionAttributes(View view0, RectF rectF0, RectF rectF1, float f, float f1, String[] arr_s, float[] arr_f) {
        switch(this.mPositionType) {
            case 1: {
                this.positionPathAttributes(rectF0, rectF1, f, f1, arr_s, arr_f);
                return;
            }
            case 2: {
                this.positionScreenAttributes(view0, rectF0, rectF1, f, f1, arr_s, arr_f);
                return;
            }
            case 3: {
                this.positionAxisAttributes(rectF0, rectF1, f, f1, arr_s, arr_f);
                return;
            }
            default: {
                this.positionCartAttributes(rectF0, rectF1, f, f1, arr_s, arr_f);
            }
        }
    }

    void positionAxisAttributes(RectF rectF0, RectF rectF1, float f, float f1, String[] arr_s, float[] arr_f) {
        float f2 = rectF0.centerX();
        float f3 = rectF0.centerY();
        float f4 = rectF1.centerX();
        float f5 = rectF1.centerY();
        if(f2 > f4) {
            float f6 = f4;
            f4 = f2;
            f2 = f6;
        }
        if(f3 <= f5) {
            float f7 = f5;
            f5 = f3;
            f3 = f7;
        }
        float f8 = f4 - f2;
        float f9 = f3 - f5;
        String s = arr_s[0];
        if(s != null) {
            if("percentX".equals(s)) {
                arr_f[0] = (f - f2) / f8;
                arr_f[1] = (f1 - f5) / f9;
                return;
            }
            arr_f[1] = (f - f2) / f8;
            arr_f[0] = (f1 - f5) / f9;
            return;
        }
        arr_s[0] = "percentX";
        arr_f[0] = (f - f2) / f8;
        arr_s[1] = "percentY";
        arr_f[1] = (f1 - f5) / f9;
    }

    void positionCartAttributes(RectF rectF0, RectF rectF1, float f, float f1, String[] arr_s, float[] arr_f) {
        float f2 = rectF0.centerX();
        float f3 = rectF0.centerY();
        float f4 = rectF1.centerX() - f2;
        float f5 = rectF1.centerY() - f3;
        String s = arr_s[0];
        if(s != null) {
            if("percentX".equals(s)) {
                arr_f[0] = (f - f2) / f4;
                arr_f[1] = (f1 - f3) / f5;
                return;
            }
            arr_f[1] = (f - f2) / f4;
            arr_f[0] = (f1 - f3) / f5;
            return;
        }
        arr_s[0] = "percentX";
        arr_f[0] = (f - f2) / f4;
        arr_s[1] = "percentY";
        arr_f[1] = (f1 - f3) / f5;
    }

    void positionPathAttributes(RectF rectF0, RectF rectF1, float f, float f1, String[] arr_s, float[] arr_f) {
        float f2 = rectF0.centerX();
        float f3 = rectF0.centerY();
        float f4 = rectF1.centerX() - f2;
        float f5 = rectF1.centerY() - f3;
        float f6 = (float)Math.hypot(f4, f5);
        if(((double)f6) < 0.0001) {
            System.out.println("distance ~ 0");
            arr_f[0] = 0.0f;
            arr_f[1] = 0.0f;
            return;
        }
        float f7 = f4 / f6;
        float f8 = f5 / f6;
        float f9 = f1 - f3;
        float f10 = f - f2;
        float f11 = (f7 * f9 - f10 * f8) / f6;
        float f12 = (f7 * f10 + f8 * f9) / f6;
        String s = arr_s[0];
        if(s != null) {
            if("percentX".equals(s)) {
                arr_f[0] = f12;
                arr_f[1] = f11;
            }
            return;
        }
        arr_s[0] = "percentX";
        arr_s[1] = "percentY";
        arr_f[0] = f12;
        arr_f[1] = f11;
    }

    void positionScreenAttributes(View view0, RectF rectF0, RectF rectF1, float f, float f1, String[] arr_s, float[] arr_f) {
        rectF0.centerX();
        rectF0.centerY();
        rectF1.centerX();
        rectF1.centerY();
        ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
        int v = viewGroup0.getWidth();
        int v1 = viewGroup0.getHeight();
        String s = arr_s[0];
        if(s != null) {
            if("percentX".equals(s)) {
                arr_f[0] = f / ((float)v);
                arr_f[1] = f1 / ((float)v1);
                return;
            }
            arr_f[1] = f / ((float)v);
            arr_f[0] = f1 / ((float)v1);
            return;
        }
        arr_s[0] = "percentX";
        arr_f[0] = f / ((float)v);
        arr_s[1] = "percentY";
        arr_f[1] = f1 / ((float)v1);
    }

    public void setType(int v) {
        this.mPositionType = v;
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void setValue(String s, Object object0) {
        s.hashCode();
        switch(s) {
            case "drawPath": {
                this.mDrawPath = this.toInt(object0);
                return;
            }
            case "percentHeight": {
                this.mPercentHeight = this.toFloat(object0);
                return;
            }
            case "percentWidth": {
                this.mPercentWidth = this.toFloat(object0);
                return;
            }
            case "percentX": {
                this.mPercentX = this.toFloat(object0);
                return;
            }
            case "percentY": {
                this.mPercentY = this.toFloat(object0);
                return;
            }
            case "sizePercent": {
                float f = this.toFloat(object0);
                this.mPercentWidth = f;
                this.mPercentHeight = f;
                return;
            }
            case "transitionEasing": {
                this.mTransitionEasing = object0.toString();
            }
        }
    }
}

