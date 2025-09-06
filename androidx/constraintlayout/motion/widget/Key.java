package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Key {
    public static final String ALPHA = "alpha";
    public static final String CURVEFIT = "curveFit";
    public static final String CUSTOM = "CUSTOM";
    public static final String ELEVATION = "elevation";
    public static final String MOTIONPROGRESS = "motionProgress";
    public static final String PIVOT_X = "transformPivotX";
    public static final String PIVOT_Y = "transformPivotY";
    public static final String PROGRESS = "progress";
    public static final String ROTATION = "rotation";
    public static final String ROTATION_X = "rotationX";
    public static final String ROTATION_Y = "rotationY";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSITIONEASING = "transitionEasing";
    public static final String TRANSITION_PATH_ROTATE = "transitionPathRotate";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";
    public static final String TRANSLATION_Z = "translationZ";
    public static int UNSET = -1;
    public static final String VISIBILITY = "visibility";
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_PHASE = "wavePhase";
    public static final String WAVE_VARIES_BY = "waveVariesBy";
    HashMap mCustomConstraints;
    int mFramePosition;
    int mTargetId;
    String mTargetString;
    protected int mType;

    static {
    }

    public Key() {
        this.mFramePosition = Key.UNSET;
        this.mTargetId = Key.UNSET;
        this.mTargetString = null;
    }

    public abstract void addValues(HashMap arg1);

    public abstract Key clone();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public Key copy(Key key0) {
        this.mFramePosition = key0.mFramePosition;
        this.mTargetId = key0.mTargetId;
        this.mTargetString = key0.mTargetString;
        this.mType = key0.mType;
        this.mCustomConstraints = key0.mCustomConstraints;
        return this;
    }

    abstract void getAttributeNames(HashSet arg1);

    public int getFramePosition() {
        return this.mFramePosition;
    }

    abstract void load(Context arg1, AttributeSet arg2);

    boolean matches(String s) {
        return this.mTargetString == null || s == null ? false : s.matches(this.mTargetString);
    }

    public void setFramePosition(int v) {
        this.mFramePosition = v;
    }

    public void setInterpolation(HashMap hashMap0) {
    }

    public abstract void setValue(String arg1, Object arg2);

    public Key setViewId(int v) {
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

