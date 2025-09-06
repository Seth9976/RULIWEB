package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R.styleable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class KeyTrigger extends Key {
    static class Loader {
        private static final int COLLISION = 9;
        private static final int CROSS = 4;
        private static final int FRAME_POS = 8;
        private static final int NEGATIVE_CROSS = 1;
        private static final int POSITIVE_CROSS = 2;
        private static final int POST_LAYOUT = 10;
        private static final int TARGET_ID = 7;
        private static final int TRIGGER_ID = 6;
        private static final int TRIGGER_RECEIVER = 11;
        private static final int TRIGGER_SLACK = 5;
        private static final int VT_CROSS = 12;
        private static final int VT_NEGATIVE_CROSS = 13;
        private static final int VT_POSITIVE_CROSS = 14;
        private static SparseIntArray sAttrMap;

        static {
            SparseIntArray sparseIntArray0 = new SparseIntArray();
            Loader.sAttrMap = sparseIntArray0;
            sparseIntArray0.append(styleable.KeyTrigger_framePosition, 8);
            Loader.sAttrMap.append(styleable.KeyTrigger_onCross, 4);
            Loader.sAttrMap.append(styleable.KeyTrigger_onNegativeCross, 1);
            Loader.sAttrMap.append(styleable.KeyTrigger_onPositiveCross, 2);
            Loader.sAttrMap.append(styleable.KeyTrigger_motionTarget, 7);
            Loader.sAttrMap.append(styleable.KeyTrigger_triggerId, 6);
            Loader.sAttrMap.append(styleable.KeyTrigger_triggerSlack, 5);
            Loader.sAttrMap.append(styleable.KeyTrigger_motion_triggerOnCollision, 9);
            Loader.sAttrMap.append(styleable.KeyTrigger_motion_postLayoutCollision, 10);
            Loader.sAttrMap.append(styleable.KeyTrigger_triggerReceiver, 11);
            Loader.sAttrMap.append(styleable.KeyTrigger_viewTransitionOnCross, 12);
            Loader.sAttrMap.append(styleable.KeyTrigger_viewTransitionOnNegativeCross, 13);
            Loader.sAttrMap.append(styleable.KeyTrigger_viewTransitionOnPositiveCross, 14);
        }

        public static void read(KeyTrigger keyTrigger0, TypedArray typedArray0, Context context0) {
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                switch(Loader.sAttrMap.get(v2)) {
                    case 1: {
                        keyTrigger0.mNegativeCross = typedArray0.getString(v2);
                        break;
                    }
                    case 2: {
                        keyTrigger0.mPositiveCross = typedArray0.getString(v2);
                        break;
                    }
                    case 4: {
                        keyTrigger0.mCross = typedArray0.getString(v2);
                        break;
                    }
                    case 5: {
                        keyTrigger0.mTriggerSlack = typedArray0.getFloat(v2, keyTrigger0.mTriggerSlack);
                        break;
                    }
                    case 6: {
                        keyTrigger0.mTriggerID = typedArray0.getResourceId(v2, keyTrigger0.mTriggerID);
                        break;
                    }
                    case 7: {
                        if(MotionLayout.IS_IN_EDIT_MODE) {
                            keyTrigger0.mTargetId = typedArray0.getResourceId(v2, keyTrigger0.mTargetId);
                            if(keyTrigger0.mTargetId == -1) {
                                keyTrigger0.mTargetString = typedArray0.getString(v2);
                            }
                        }
                        else if(typedArray0.peekValue(v2).type == 3) {
                            keyTrigger0.mTargetString = typedArray0.getString(v2);
                        }
                        else {
                            keyTrigger0.mTargetId = typedArray0.getResourceId(v2, keyTrigger0.mTargetId);
                        }
                        break;
                    }
                    case 8: {
                        keyTrigger0.mFramePosition = typedArray0.getInteger(v2, keyTrigger0.mFramePosition);
                        keyTrigger0.mFireThreshold = (((float)keyTrigger0.mFramePosition) + 0.5f) / 100.0f;
                        break;
                    }
                    case 9: {
                        keyTrigger0.mTriggerCollisionId = typedArray0.getResourceId(v2, keyTrigger0.mTriggerCollisionId);
                        break;
                    }
                    case 10: {
                        keyTrigger0.mPostLayout = typedArray0.getBoolean(v2, keyTrigger0.mPostLayout);
                        break;
                    }
                    case 11: {
                        keyTrigger0.mTriggerReceiver = typedArray0.getResourceId(v2, keyTrigger0.mTriggerReceiver);
                        break;
                    }
                    case 12: {
                        keyTrigger0.mViewTransitionOnCross = typedArray0.getResourceId(v2, keyTrigger0.mViewTransitionOnCross);
                        break;
                    }
                    case 13: {
                        keyTrigger0.mViewTransitionOnNegativeCross = typedArray0.getResourceId(v2, keyTrigger0.mViewTransitionOnNegativeCross);
                        break;
                    }
                    case 14: {
                        keyTrigger0.mViewTransitionOnPositiveCross = typedArray0.getResourceId(v2, keyTrigger0.mViewTransitionOnPositiveCross);
                        break;
                    }
                    default: {
                        Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(v2) + "   " + Loader.sAttrMap.get(v2));
                    }
                }
            }
        }
    }

    public static final String CROSS = "CROSS";
    public static final int KEY_TYPE = 5;
    static final String NAME = "KeyTrigger";
    public static final String NEGATIVE_CROSS = "negativeCross";
    public static final String POSITIVE_CROSS = "positiveCross";
    public static final String POST_LAYOUT = "postLayout";
    private static final String TAG = "KeyTrigger";
    public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
    public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
    public static final String TRIGGER_ID = "triggerID";
    public static final String TRIGGER_RECEIVER = "triggerReceiver";
    public static final String TRIGGER_SLACK = "triggerSlack";
    public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
    public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
    public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";
    RectF mCollisionRect;
    private String mCross;
    private int mCurveFit;
    private boolean mFireCrossReset;
    private float mFireLastPos;
    private boolean mFireNegativeReset;
    private boolean mFirePositiveReset;
    private float mFireThreshold;
    HashMap mMethodHashMap;
    private String mNegativeCross;
    private String mPositiveCross;
    private boolean mPostLayout;
    RectF mTargetRect;
    private int mTriggerCollisionId;
    private View mTriggerCollisionView;
    private int mTriggerID;
    private int mTriggerReceiver;
    float mTriggerSlack;
    int mViewTransitionOnCross;
    int mViewTransitionOnNegativeCross;
    int mViewTransitionOnPositiveCross;

    public KeyTrigger() {
        this.mTriggerSlack = 0.1f;
        this.mViewTransitionOnNegativeCross = KeyTrigger.UNSET;
        this.mViewTransitionOnPositiveCross = KeyTrigger.UNSET;
        this.mViewTransitionOnCross = KeyTrigger.UNSET;
        this.mCollisionRect = new RectF();
        this.mTargetRect = new RectF();
        this.mMethodHashMap = new HashMap();
        this.mCurveFit = -1;
        this.mCross = null;
        this.mTriggerReceiver = KeyTrigger.UNSET;
        this.mNegativeCross = null;
        this.mPositiveCross = null;
        this.mTriggerID = KeyTrigger.UNSET;
        this.mTriggerCollisionId = KeyTrigger.UNSET;
        this.mTriggerCollisionView = null;
        this.mFireCrossReset = true;
        this.mFireNegativeReset = true;
        this.mFirePositiveReset = true;
        this.mFireThreshold = NaNf;
        this.mPostLayout = false;
        this.mType = 5;
        this.mCustomConstraints = new HashMap();
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void addValues(HashMap hashMap0) {
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key clone() {
        return new KeyTrigger().copy(this);
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public void conditionallyFire(float f, View view0) {
        int v;
        boolean z2;
        boolean z1;
        boolean z;
        boolean z3;
        int v1;
        if(this.mTriggerCollisionId == KeyTrigger.UNSET) {
            if(!this.mFireCrossReset) {
                if(Math.abs(f - this.mFireThreshold) > this.mTriggerSlack) {
                    this.mFireCrossReset = true;
                }
                z = false;
            }
            else if((f - this.mFireThreshold) * (this.mFireLastPos - this.mFireThreshold) < 0.0f) {
                this.mFireCrossReset = false;
                z = true;
            }
            else {
                z = false;
            }
            if(this.mFireNegativeReset) {
                float f1 = f - this.mFireThreshold;
                if((this.mFireLastPos - this.mFireThreshold) * f1 >= 0.0f || f1 >= 0.0f) {
                    v1 = 0;
                }
                else {
                    this.mFireNegativeReset = false;
                    v1 = 1;
                }
            }
            else {
                if(Math.abs(f - this.mFireThreshold) > this.mTriggerSlack) {
                    this.mFireNegativeReset = true;
                }
                v1 = 0;
            }
            if(this.mFirePositiveReset) {
                float f2 = f - this.mFireThreshold;
                if((this.mFireLastPos - this.mFireThreshold) * f2 >= 0.0f || f2 <= 0.0f) {
                    z3 = false;
                }
                else {
                    this.mFirePositiveReset = false;
                    z3 = true;
                }
                z2 = z3;
                v = v1;
            }
            else {
                if(Math.abs(f - this.mFireThreshold) > this.mTriggerSlack) {
                    this.mFirePositiveReset = true;
                }
                v = v1;
                z2 = false;
            }
        }
        else {
            if(this.mTriggerCollisionView == null) {
                this.mTriggerCollisionView = ((ViewGroup)view0.getParent()).findViewById(this.mTriggerCollisionId);
            }
            this.setUpRect(this.mCollisionRect, this.mTriggerCollisionView, this.mPostLayout);
            this.setUpRect(this.mTargetRect, view0, this.mPostLayout);
            if(this.mCollisionRect.intersect(this.mTargetRect)) {
                if(this.mFireCrossReset) {
                    this.mFireCrossReset = false;
                    z = true;
                }
                else {
                    z = false;
                }
                if(this.mFirePositiveReset) {
                    this.mFirePositiveReset = false;
                    z1 = true;
                }
                else {
                    z1 = false;
                }
                this.mFireNegativeReset = true;
                z2 = z1;
                v = 0;
            }
            else {
                if(this.mFireCrossReset) {
                    z = false;
                }
                else {
                    this.mFireCrossReset = true;
                    z = true;
                }
                if(this.mFireNegativeReset) {
                    this.mFireNegativeReset = false;
                    v = 1;
                }
                else {
                    v = 0;
                }
                this.mFirePositiveReset = true;
                z2 = false;
            }
        }
        this.mFireLastPos = f;
        if(v != 0 || z || z2) {
            ((MotionLayout)view0.getParent()).fireTrigger(this.mTriggerID, z2, f);
        }
        View view1 = this.mTriggerReceiver == KeyTrigger.UNSET ? view0 : ((MotionLayout)view0.getParent()).findViewById(this.mTriggerReceiver);
        if(v != 0) {
            String s = this.mNegativeCross;
            if(s != null) {
                this.fire(s, view1);
            }
            if(this.mViewTransitionOnNegativeCross != KeyTrigger.UNSET) {
                ((MotionLayout)view0.getParent()).viewTransition(this.mViewTransitionOnNegativeCross, new View[]{view1});
            }
        }
        if(z2) {
            String s1 = this.mPositiveCross;
            if(s1 != null) {
                this.fire(s1, view1);
            }
            if(this.mViewTransitionOnPositiveCross != KeyTrigger.UNSET) {
                ((MotionLayout)view0.getParent()).viewTransition(this.mViewTransitionOnPositiveCross, new View[]{view1});
            }
        }
        if(z) {
            String s2 = this.mCross;
            if(s2 != null) {
                this.fire(s2, view1);
            }
            if(this.mViewTransitionOnCross != KeyTrigger.UNSET) {
                ((MotionLayout)view0.getParent()).viewTransition(this.mViewTransitionOnCross, new View[]{view1});
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public Key copy(Key key0) {
        super.copy(key0);
        this.mCurveFit = ((KeyTrigger)key0).mCurveFit;
        this.mCross = ((KeyTrigger)key0).mCross;
        this.mTriggerReceiver = ((KeyTrigger)key0).mTriggerReceiver;
        this.mNegativeCross = ((KeyTrigger)key0).mNegativeCross;
        this.mPositiveCross = ((KeyTrigger)key0).mPositiveCross;
        this.mTriggerID = ((KeyTrigger)key0).mTriggerID;
        this.mTriggerCollisionId = ((KeyTrigger)key0).mTriggerCollisionId;
        this.mTriggerCollisionView = ((KeyTrigger)key0).mTriggerCollisionView;
        this.mTriggerSlack = ((KeyTrigger)key0).mTriggerSlack;
        this.mFireCrossReset = ((KeyTrigger)key0).mFireCrossReset;
        this.mFireNegativeReset = ((KeyTrigger)key0).mFireNegativeReset;
        this.mFirePositiveReset = ((KeyTrigger)key0).mFirePositiveReset;
        this.mFireThreshold = ((KeyTrigger)key0).mFireThreshold;
        this.mFireLastPos = ((KeyTrigger)key0).mFireLastPos;
        this.mPostLayout = ((KeyTrigger)key0).mPostLayout;
        this.mCollisionRect = ((KeyTrigger)key0).mCollisionRect;
        this.mTargetRect = ((KeyTrigger)key0).mTargetRect;
        this.mMethodHashMap = ((KeyTrigger)key0).mMethodHashMap;
        return this;
    }

    private void fire(String s, View view0) {
        Method method0;
        if(s != null) {
            if(s.startsWith(".")) {
                this.fireCustom(s, view0);
                return;
            }
            if(this.mMethodHashMap.containsKey(s)) {
                method0 = (Method)this.mMethodHashMap.get(s);
                if(method0 == null) {
                    return;
                }
            }
            else {
                method0 = null;
            }
            if(method0 == null) {
                try {
                    method0 = view0.getClass().getMethod(s, null);
                    this.mMethodHashMap.put(s, method0);
                }
                catch(NoSuchMethodException unused_ex) {
                    this.mMethodHashMap.put(s, null);
                    Log.e("KeyTrigger", "Could not find method \"" + s + "\"on class " + view0.getClass().getSimpleName() + " " + Debug.getName(view0));
                    return;
                }
            }
            try {
                method0.invoke(view0, null);
            }
            catch(Exception unused_ex) {
                Log.e("KeyTrigger", "Exception in call \"" + this.mCross + "\"on class " + view0.getClass().getSimpleName() + " " + Debug.getName(view0));
            }
        }
    }

    private void fireCustom(String s, View view0) {
        boolean z = s.length() == 1;
        if(!z) {
            s = s.substring(1).toLowerCase(Locale.ROOT);
        }
        for(Object object0: this.mCustomConstraints.keySet()) {
            String s1 = (String)object0;
            if(z || s1.toLowerCase(Locale.ROOT).matches(s)) {
                ConstraintAttribute constraintAttribute0 = (ConstraintAttribute)this.mCustomConstraints.get(s1);
                if(constraintAttribute0 != null) {
                    constraintAttribute0.applyCustom(view0);
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void getAttributeNames(HashSet hashSet0) {
    }

    int getCurveFit() {
        return this.mCurveFit;
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void load(Context context0, AttributeSet attributeSet0) {
        Loader.read(this, context0.obtainStyledAttributes(attributeSet0, styleable.KeyTrigger), context0);
    }

    private void setUpRect(RectF rectF0, View view0, boolean z) {
        rectF0.top = (float)view0.getTop();
        rectF0.bottom = (float)view0.getBottom();
        rectF0.left = (float)view0.getLeft();
        rectF0.right = (float)view0.getRight();
        if(z) {
            view0.getMatrix().mapRect(rectF0);
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.Key
    public void setValue(String s, Object object0) {
        s.hashCode();
        switch(s) {
            case "CROSS": {
                this.mCross = object0.toString();
                return;
            }
            case "negativeCross": {
                this.mNegativeCross = object0.toString();
                return;
            }
            case "positiveCross": {
                this.mPositiveCross = object0.toString();
                return;
            }
            case "postLayout": {
                this.mPostLayout = this.toBoolean(object0);
                return;
            }
            case "triggerCollisionId": {
                this.mTriggerCollisionId = this.toInt(object0);
                return;
            }
            case "triggerCollisionView": {
                this.mTriggerCollisionView = (View)object0;
                return;
            }
            case "triggerID": {
                this.mTriggerID = this.toInt(object0);
                return;
            }
            case "triggerReceiver": {
                this.mTriggerReceiver = this.toInt(object0);
                return;
            }
            case "triggerSlack": {
                this.mTriggerSlack = this.toFloat(object0);
                return;
            }
            case "viewTransitionOnCross": {
                this.mViewTransitionOnCross = this.toInt(object0);
                return;
            }
            case "viewTransitionOnNegativeCross": {
                this.mViewTransitionOnNegativeCross = this.toInt(object0);
                return;
            }
            case "viewTransitionOnPositiveCross": {
                this.mViewTransitionOnPositiveCross = this.toInt(object0);
            }
        }
    }
}

