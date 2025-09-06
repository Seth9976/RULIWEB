package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.HashMap;
import java.util.HashSet;

public abstract class MotionKey implements TypedValues {
    public static final String ALPHA = "alpha";
    public static final String CUSTOM = "CUSTOM";
    public static final String ELEVATION = "elevation";
    public static final String ROTATION = "rotationZ";
    public static final String ROTATION_X = "rotationX";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSITION_PATH_ROTATE = "transitionPathRotate";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";
    public static int UNSET = -1;
    public static final String VISIBILITY = "visibility";
    public HashMap mCustom;
    public int mFramePosition;
    int mTargetId;
    String mTargetString;
    public int mType;

    static {
    }

    public MotionKey() {
        this.mFramePosition = MotionKey.UNSET;
        this.mTargetId = MotionKey.UNSET;
        this.mTargetString = null;
    }

    public abstract void addValues(HashMap arg1);

    public abstract MotionKey clone();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public MotionKey copy(MotionKey motionKey0) {
        this.mFramePosition = motionKey0.mFramePosition;
        this.mTargetId = motionKey0.mTargetId;
        this.mTargetString = motionKey0.mTargetString;
        this.mType = motionKey0.mType;
        return this;
    }

    public abstract void getAttributeNames(HashSet arg1);

    public int getFramePosition() {
        return this.mFramePosition;
    }

    boolean matches(String s) {
        return this.mTargetString == null || s == null ? false : s.matches(this.mTargetString);
    }

    public void setCustomAttribute(String s, int v, float f) {
        this.mCustom.put(s, new CustomVariable(s, v, f));
    }

    public void setCustomAttribute(String s, int v, int v1) {
        this.mCustom.put(s, new CustomVariable(s, v, v1));
    }

    public void setCustomAttribute(String s, int v, String s1) {
        this.mCustom.put(s, new CustomVariable(s, v, s1));
    }

    public void setCustomAttribute(String s, int v, boolean z) {
        this.mCustom.put(s, new CustomVariable(s, v, z));
    }

    public void setFramePosition(int v) {
        this.mFramePosition = v;
    }

    public void setInterpolation(HashMap hashMap0) {
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, float f) {
        return false;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, int v1) {
        if(v != 100) {
            return false;
        }
        this.mFramePosition = v1;
        return true;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, String s) {
        if(v != 101) {
            return false;
        }
        this.mTargetString = s;
        return true;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, boolean z) {
        return false;
    }

    public MotionKey setViewId(int v) {
        this.mTargetId = v;
        return this;
    }

    // 去混淆评级： 低(20)
    boolean toBoolean(Object object0) {
        return object0 instanceof Boolean ? ((Boolean)object0).booleanValue() : Boolean.parseBoolean(object0.toString());
    }

    // 去混淆评级： 低(20)
    float toFloat(Object object0) {
        return object0 instanceof Float ? ((float)(((Float)object0))) : Float.parseFloat(object0.toString());
    }

    // 去混淆评级： 低(20)
    int toInt(Object object0) {
        return object0 instanceof Integer ? ((int)(((Integer)object0))) : Integer.parseInt(object0.toString());
    }
}

