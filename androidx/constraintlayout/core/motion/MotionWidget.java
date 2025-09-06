package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.TypedValues.AttributesType.-CC;
import androidx.constraintlayout.core.motion.utils.TypedValues.MotionType.-CC;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.state.WidgetFrame;
import java.util.Set;

public class MotionWidget implements TypedValues {
    public static class Motion {
        private static final int INTERPOLATOR_REFERENCE_ID = -2;
        private static final int INTERPOLATOR_UNDEFINED = -3;
        private static final int SPLINE_STRING = -1;
        public int mAnimateCircleAngleTo;
        public String mAnimateRelativeTo;
        public int mDrawPath;
        public float mMotionStagger;
        public int mPathMotionArc;
        public float mPathRotate;
        public int mPolarRelativeTo;
        public int mQuantizeInterpolatorID;
        public String mQuantizeInterpolatorString;
        public int mQuantizeInterpolatorType;
        public float mQuantizeMotionPhase;
        public int mQuantizeMotionSteps;
        public String mTransitionEasing;

        public Motion() {
            this.mAnimateRelativeTo = null;
            this.mAnimateCircleAngleTo = 0;
            this.mTransitionEasing = null;
            this.mPathMotionArc = -1;
            this.mDrawPath = 0;
            this.mMotionStagger = NaNf;
            this.mPolarRelativeTo = -1;
            this.mPathRotate = NaNf;
            this.mQuantizeMotionPhase = NaNf;
            this.mQuantizeMotionSteps = -1;
            this.mQuantizeInterpolatorString = null;
            this.mQuantizeInterpolatorType = -3;
            this.mQuantizeInterpolatorID = -1;
        }
    }

    public static class PropertySet {
        public float alpha;
        public float mProgress;
        public int mVisibilityMode;
        public int visibility;

        public PropertySet() {
            this.visibility = 4;
            this.mVisibilityMode = 0;
            this.alpha = 1.0f;
            this.mProgress = NaNf;
        }
    }

    public static final int FILL_PARENT = -1;
    public static final int GONE_UNSET = 0x80000000;
    private static final int INTERNAL_MATCH_CONSTRAINT = -3;
    private static final int INTERNAL_MATCH_PARENT = -1;
    private static final int INTERNAL_WRAP_CONTENT = -2;
    private static final int INTERNAL_WRAP_CONTENT_CONSTRAINED = -4;
    public static final int INVISIBLE = 0;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int MATCH_PARENT = -1;
    public static final int PARENT_ID = 0;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    public static final int UNSET = -1;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 4;
    public static final int WRAP_CONTENT = -2;
    Motion mMotion;
    private float mProgress;
    PropertySet mPropertySet;
    float mTransitionPathRotate;
    WidgetFrame mWidgetFrame;

    public MotionWidget() {
        this.mWidgetFrame = new WidgetFrame();
        this.mMotion = new Motion();
        this.mPropertySet = new PropertySet();
    }

    public MotionWidget(WidgetFrame widgetFrame0) {
        this.mWidgetFrame = new WidgetFrame();
        this.mMotion = new Motion();
        this.mPropertySet = new PropertySet();
        this.mWidgetFrame = widgetFrame0;
    }

    public MotionWidget findViewById(int v) [...] // Inlined contents

    public float getAlpha() {
        return this.mWidgetFrame.alpha;
    }

    public int getBottom() {
        return this.mWidgetFrame.bottom;
    }

    public CustomVariable getCustomAttribute(String s) {
        return this.mWidgetFrame.getCustomAttribute(s);
    }

    public Set getCustomAttributeNames() {
        return this.mWidgetFrame.getCustomAttributeNames();
    }

    public int getHeight() {
        return this.mWidgetFrame.bottom - this.mWidgetFrame.top;
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String s) {
        int v = TypedValues.AttributesType.-CC.getId(s);
        return v == -1 ? TypedValues.MotionType.-CC.getId(s) : v;
    }

    public int getLeft() {
        return this.mWidgetFrame.left;
    }

    // 去混淆评级： 低(20)
    public String getName() [...] // 潜在的解密器

    public MotionWidget getParent() [...] // Inlined contents

    public float getPivotX() {
        return this.mWidgetFrame.pivotX;
    }

    public float getPivotY() {
        return this.mWidgetFrame.pivotY;
    }

    public int getRight() {
        return this.mWidgetFrame.right;
    }

    public float getRotationX() {
        return this.mWidgetFrame.rotationX;
    }

    public float getRotationY() {
        return this.mWidgetFrame.rotationY;
    }

    public float getRotationZ() {
        return this.mWidgetFrame.rotationZ;
    }

    public float getScaleX() {
        return this.mWidgetFrame.scaleX;
    }

    public float getScaleY() {
        return this.mWidgetFrame.scaleY;
    }

    public int getTop() {
        return this.mWidgetFrame.top;
    }

    public float getTranslationX() {
        return this.mWidgetFrame.translationX;
    }

    public float getTranslationY() {
        return this.mWidgetFrame.translationY;
    }

    public float getTranslationZ() {
        return this.mWidgetFrame.translationZ;
    }

    public float getValueAttributes(int v) {
        switch(v) {
            case 303: {
                return this.mWidgetFrame.alpha;
            }
            case 304: {
                return this.mWidgetFrame.translationX;
            }
            case 305: {
                return this.mWidgetFrame.translationY;
            }
            case 306: {
                return this.mWidgetFrame.translationZ;
            }
            case 308: {
                return this.mWidgetFrame.rotationX;
            }
            case 309: {
                return this.mWidgetFrame.rotationY;
            }
            case 310: {
                return this.mWidgetFrame.rotationZ;
            }
            case 311: {
                return this.mWidgetFrame.scaleX;
            }
            case 312: {
                return this.mWidgetFrame.scaleY;
            }
            case 313: {
                return this.mWidgetFrame.pivotX;
            }
            case 314: {
                return this.mWidgetFrame.pivotY;
            }
            case 315: {
                return this.mProgress;
            }
            case 316: {
                return this.mTransitionPathRotate;
            }
            default: {
                return NaNf;
            }
        }
    }

    public int getVisibility() {
        return this.mPropertySet.visibility;
    }

    public WidgetFrame getWidgetFrame() {
        return this.mWidgetFrame;
    }

    public int getWidth() {
        return this.mWidgetFrame.right - this.mWidgetFrame.left;
    }

    public int getX() {
        return this.mWidgetFrame.left;
    }

    public int getY() {
        return this.mWidgetFrame.top;
    }

    public void layout(int v, int v1, int v2, int v3) {
        this.setBounds(v, v1, v2, v3);
    }

    public void setBounds(int v, int v1, int v2, int v3) {
        if(this.mWidgetFrame == null) {
            this.mWidgetFrame = new WidgetFrame(null);
        }
        this.mWidgetFrame.top = v1;
        this.mWidgetFrame.left = v;
        this.mWidgetFrame.right = v2;
        this.mWidgetFrame.bottom = v3;
    }

    public void setCustomAttribute(String s, int v, float f) {
        this.mWidgetFrame.setCustomAttribute(s, v, f);
    }

    public void setCustomAttribute(String s, int v, int v1) {
        this.mWidgetFrame.setCustomAttribute(s, v, v1);
    }

    public void setCustomAttribute(String s, int v, String s1) {
        this.mWidgetFrame.setCustomAttribute(s, v, s1);
    }

    public void setCustomAttribute(String s, int v, boolean z) {
        this.mWidgetFrame.setCustomAttribute(s, v, z);
    }

    public void setInterpolatedValue(CustomAttribute customAttribute0, float[] arr_f) {
        this.mWidgetFrame.setCustomAttribute(customAttribute0.mName, 901, arr_f[0]);
    }

    public void setPivotX(float f) {
        this.mWidgetFrame.pivotX = f;
    }

    public void setPivotY(float f) {
        this.mWidgetFrame.pivotY = f;
    }

    public void setRotationX(float f) {
        this.mWidgetFrame.rotationX = f;
    }

    public void setRotationY(float f) {
        this.mWidgetFrame.rotationY = f;
    }

    public void setRotationZ(float f) {
        this.mWidgetFrame.rotationZ = f;
    }

    public void setScaleX(float f) {
        this.mWidgetFrame.scaleX = f;
    }

    public void setScaleY(float f) {
        this.mWidgetFrame.scaleY = f;
    }

    public void setTranslationX(float f) {
        this.mWidgetFrame.translationX = f;
    }

    public void setTranslationY(float f) {
        this.mWidgetFrame.translationY = f;
    }

    public void setTranslationZ(float f) {
        this.mWidgetFrame.translationZ = f;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, float f) {
        return this.setValueAttributes(v, f) ? true : this.setValueMotion(v, f);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, int v1) {
        return this.setValueAttributes(v, ((float)v1)) ? true : this.setValueMotion(v, v1);
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, String s) {
        if(v == 605) {
            this.mMotion.mAnimateRelativeTo = s;
            return true;
        }
        return this.setValueMotion(v, s);
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public boolean setValue(int v, boolean z) {
        return false;
    }

    public boolean setValueAttributes(int v, float f) {
        switch(v) {
            case 303: {
                this.mWidgetFrame.alpha = f;
                return true;
            }
            case 304: {
                this.mWidgetFrame.translationX = f;
                return true;
            }
            case 305: {
                this.mWidgetFrame.translationY = f;
                return true;
            }
            case 306: {
                this.mWidgetFrame.translationZ = f;
                return true;
            }
            case 308: {
                this.mWidgetFrame.rotationX = f;
                return true;
            }
            case 309: {
                this.mWidgetFrame.rotationY = f;
                return true;
            }
            case 310: {
                this.mWidgetFrame.rotationZ = f;
                return true;
            }
            case 311: {
                this.mWidgetFrame.scaleX = f;
                return true;
            }
            case 312: {
                this.mWidgetFrame.scaleY = f;
                return true;
            }
            case 313: {
                this.mWidgetFrame.pivotX = f;
                return true;
            }
            case 314: {
                this.mWidgetFrame.pivotY = f;
                return true;
            }
            case 315: {
                this.mProgress = f;
                return true;
            }
            case 316: {
                this.mTransitionPathRotate = f;
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public boolean setValueMotion(int v, float f) {
        switch(v) {
            case 600: {
                this.mMotion.mMotionStagger = f;
                return true;
            }
            case 601: {
                this.mMotion.mPathRotate = f;
                return true;
            }
            case 602: {
                this.mMotion.mQuantizeMotionPhase = f;
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public boolean setValueMotion(int v, int v1) {
        switch(v) {
            case 606: {
                this.mMotion.mAnimateCircleAngleTo = v1;
                return true;
            }
            case 607: {
                this.mMotion.mPathMotionArc = v1;
                return true;
            }
            case 608: {
                this.mMotion.mDrawPath = v1;
                return true;
            }
            case 609: {
                this.mMotion.mPolarRelativeTo = v1;
                return true;
            }
            case 610: {
                this.mMotion.mQuantizeMotionSteps = v1;
                return true;
            }
            case 611: {
                this.mMotion.mQuantizeInterpolatorType = v1;
                return true;
            }
            case 612: {
                this.mMotion.mQuantizeInterpolatorID = v1;
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public boolean setValueMotion(int v, String s) {
        switch(v) {
            case 603: {
                this.mMotion.mTransitionEasing = s;
                return true;
            }
            case 604: {
                this.mMotion.mQuantizeInterpolatorString = s;
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public void setVisibility(int v) {
        this.mPropertySet.visibility = v;
    }

    @Override
    public String toString() {
        return this.mWidgetFrame.left + ", " + this.mWidgetFrame.top + ", " + this.mWidgetFrame.right + ", " + this.mWidgetFrame.bottom;
    }

    public void updateMotion(TypedValues typedValues0) {
        if(this.mWidgetFrame.getMotionProperties() != null) {
            this.mWidgetFrame.getMotionProperties().applyDelta(typedValues0);
        }
    }
}

