package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.TypedValues.PositionType.-CC;
import java.util.HashMap;
import java.util.HashSet;

public class MotionKeyPosition extends MotionKey {
    static final int KEY_TYPE = 2;
    static final String NAME = "KeyPosition";
    protected static final float SELECTION_SLOPE = 20.0f;
    public static final int TYPE_CARTESIAN = 0;
    public static final int TYPE_PATH = 1;
    public static final int TYPE_SCREEN = 2;
    public float mAltPercentX;
    public float mAltPercentY;
    private float mCalculatedPositionX;
    private float mCalculatedPositionY;
    public int mCurveFit;
    public int mDrawPath;
    public int mPathMotionArc;
    public float mPercentHeight;
    public float mPercentWidth;
    public float mPercentX;
    public float mPercentY;
    public int mPositionType;
    public String mTransitionEasing;

    public MotionKeyPosition() {
        this.mCurveFit = MotionKeyPosition.UNSET;
        this.mTransitionEasing = null;
        this.mPathMotionArc = MotionKeyPosition.UNSET;
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

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
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

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey clone() {
        return new MotionKeyPosition().copy(this);
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey copy(MotionKey motionKey0) {
        super.copy(motionKey0);
        this.mTransitionEasing = ((MotionKeyPosition)motionKey0).mTransitionEasing;
        this.mPathMotionArc = ((MotionKeyPosition)motionKey0).mPathMotionArc;
        this.mDrawPath = ((MotionKeyPosition)motionKey0).mDrawPath;
        this.mPercentWidth = ((MotionKeyPosition)motionKey0).mPercentWidth;
        this.mPercentHeight = NaNf;
        this.mPercentX = ((MotionKeyPosition)motionKey0).mPercentX;
        this.mPercentY = ((MotionKeyPosition)motionKey0).mPercentY;
        this.mAltPercentX = ((MotionKeyPosition)motionKey0).mAltPercentX;
        this.mAltPercentY = ((MotionKeyPosition)motionKey0).mAltPercentY;
        this.mCalculatedPositionX = ((MotionKeyPosition)motionKey0).mCalculatedPositionX;
        this.mCalculatedPositionY = ((MotionKeyPosition)motionKey0).mCalculatedPositionY;
        return this;
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet hashSet0) {
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String s) {
        return TypedValues.PositionType.-CC.getId(s);
    }

    float getPositionX() {
        return this.mCalculatedPositionX;
    }

    float getPositionY() {
        return this.mCalculatedPositionY;
    }

    public boolean intersects(int v, int v1, FloatRect floatRect0, FloatRect floatRect1, float f, float f1) {
        this.calcPosition(v, v1, floatRect0.centerX(), floatRect0.centerY(), floatRect1.centerX(), floatRect1.centerY());
        return Math.abs(f - this.mCalculatedPositionX) < 20.0f && Math.abs(f1 - this.mCalculatedPositionY) < 20.0f;
    }

    public void positionAttributes(MotionWidget motionWidget0, FloatRect floatRect0, FloatRect floatRect1, float f, float f1, String[] arr_s, float[] arr_f) {
        switch(this.mPositionType) {
            case 1: {
                this.positionPathAttributes(floatRect0, floatRect1, f, f1, arr_s, arr_f);
                return;
            }
            case 2: {
                this.positionScreenAttributes(motionWidget0, floatRect0, floatRect1, f, f1, arr_s, arr_f);
                return;
            }
            default: {
                this.positionCartAttributes(floatRect0, floatRect1, f, f1, arr_s, arr_f);
            }
        }
    }

    void positionCartAttributes(FloatRect floatRect0, FloatRect floatRect1, float f, float f1, String[] arr_s, float[] arr_f) {
        float f2 = floatRect0.centerX();
        float f3 = floatRect0.centerY();
        float f4 = floatRect1.centerX() - f2;
        float f5 = floatRect1.centerY() - f3;
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

    void positionPathAttributes(FloatRect floatRect0, FloatRect floatRect1, float f, float f1, String[] arr_s, float[] arr_f) {
        float f2 = floatRect0.centerX();
        float f3 = floatRect0.centerY();
        float f4 = floatRect1.centerX() - f2;
        float f5 = floatRect1.centerY() - f3;
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

    // 去混淆评级： 低(20)
    void positionScreenAttributes(MotionWidget motionWidget0, FloatRect floatRect0, FloatRect floatRect1, float f, float f1, String[] arr_s, float[] arr_f) {
        throw new NullPointerException();
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, float f) {
        switch(v) {
            case 503: {
                this.mPercentWidth = f;
                return true;
            }
            case 504: {
                this.mPercentHeight = f;
                return true;
            }
            case 505: {
                this.mPercentWidth = f;
                this.mPercentHeight = f;
                return true;
            }
            case 506: {
                this.mPercentX = f;
                return true;
            }
            case 507: {
                this.mPercentY = f;
                return true;
            }
            default: {
                return super.setValue(v, f);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, int v1) {
        switch(v) {
            case 100: {
                this.mFramePosition = v1;
                return true;
            }
            case 508: {
                this.mCurveFit = v1;
                return true;
            }
            case 510: {
                this.mPositionType = v1;
                return true;
            }
            default: {
                return super.setValue(v, v1);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, String s) {
        if(v != 501) {
            return super.setValue(v, s);
        }
        this.mTransitionEasing = s.toString();
        return true;
    }
}

