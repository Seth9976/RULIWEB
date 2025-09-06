package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class MotionKeyTrigger extends MotionKey {
    public static final String CROSS = "CROSS";
    public static final int KEY_TYPE = 5;
    public static final String NEGATIVE_CROSS = "negativeCross";
    public static final String POSITIVE_CROSS = "positiveCross";
    public static final String POST_LAYOUT = "postLayout";
    private static final String TAG = "KeyTrigger";
    public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
    public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
    public static final String TRIGGER_ID = "triggerID";
    public static final String TRIGGER_RECEIVER = "triggerReceiver";
    public static final String TRIGGER_SLACK = "triggerSlack";
    public static final int TYPE_CROSS = 312;
    public static final int TYPE_NEGATIVE_CROSS = 310;
    public static final int TYPE_POSITIVE_CROSS = 309;
    public static final int TYPE_POST_LAYOUT = 304;
    public static final int TYPE_TRIGGER_COLLISION_ID = 307;
    public static final int TYPE_TRIGGER_COLLISION_VIEW = 306;
    public static final int TYPE_TRIGGER_ID = 308;
    public static final int TYPE_TRIGGER_RECEIVER = 311;
    public static final int TYPE_TRIGGER_SLACK = 305;
    public static final int TYPE_VIEW_TRANSITION_ON_CROSS = 301;
    public static final int TYPE_VIEW_TRANSITION_ON_NEGATIVE_CROSS = 303;
    public static final int TYPE_VIEW_TRANSITION_ON_POSITIVE_CROSS = 302;
    public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
    public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
    public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";
    FloatRect mCollisionRect;
    private String mCross;
    private int mCurveFit;
    private boolean mFireCrossReset;
    private float mFireLastPos;
    private boolean mFireNegativeReset;
    private boolean mFirePositiveReset;
    private float mFireThreshold;
    private String mNegativeCross;
    private String mPositiveCross;
    private boolean mPostLayout;
    FloatRect mTargetRect;
    private int mTriggerCollisionId;
    private int mTriggerID;
    private int mTriggerReceiver;
    float mTriggerSlack;
    int mViewTransitionOnCross;
    int mViewTransitionOnNegativeCross;
    int mViewTransitionOnPositiveCross;

    public MotionKeyTrigger() {
        this.mCurveFit = -1;
        this.mCross = null;
        this.mTriggerReceiver = MotionKeyTrigger.UNSET;
        this.mNegativeCross = null;
        this.mPositiveCross = null;
        this.mTriggerID = MotionKeyTrigger.UNSET;
        this.mTriggerCollisionId = MotionKeyTrigger.UNSET;
        this.mTriggerSlack = 0.1f;
        this.mFireCrossReset = true;
        this.mFireNegativeReset = true;
        this.mFirePositiveReset = true;
        this.mFireThreshold = NaNf;
        this.mPostLayout = false;
        this.mViewTransitionOnNegativeCross = MotionKeyTrigger.UNSET;
        this.mViewTransitionOnPositiveCross = MotionKeyTrigger.UNSET;
        this.mViewTransitionOnCross = MotionKeyTrigger.UNSET;
        this.mCollisionRect = new FloatRect();
        this.mTargetRect = new FloatRect();
        this.mType = 5;
        this.mCustom = new HashMap();
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void addValues(HashMap hashMap0) {
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey clone() {
        return new MotionKeyTrigger().copy(this);
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public void conditionallyFire(float f, MotionWidget motionWidget0) {
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public MotionKey copy(MotionKey motionKey0) {
        return this.copy(motionKey0);
    }

    public MotionKeyTrigger copy(MotionKey motionKey0) {
        super.copy(motionKey0);
        this.mCurveFit = ((MotionKeyTrigger)motionKey0).mCurveFit;
        this.mCross = ((MotionKeyTrigger)motionKey0).mCross;
        this.mTriggerReceiver = ((MotionKeyTrigger)motionKey0).mTriggerReceiver;
        this.mNegativeCross = ((MotionKeyTrigger)motionKey0).mNegativeCross;
        this.mPositiveCross = ((MotionKeyTrigger)motionKey0).mPositiveCross;
        this.mTriggerID = ((MotionKeyTrigger)motionKey0).mTriggerID;
        this.mTriggerCollisionId = ((MotionKeyTrigger)motionKey0).mTriggerCollisionId;
        this.mTriggerSlack = ((MotionKeyTrigger)motionKey0).mTriggerSlack;
        this.mFireCrossReset = ((MotionKeyTrigger)motionKey0).mFireCrossReset;
        this.mFireNegativeReset = ((MotionKeyTrigger)motionKey0).mFireNegativeReset;
        this.mFirePositiveReset = ((MotionKeyTrigger)motionKey0).mFirePositiveReset;
        this.mFireThreshold = ((MotionKeyTrigger)motionKey0).mFireThreshold;
        this.mFireLastPos = ((MotionKeyTrigger)motionKey0).mFireLastPos;
        this.mPostLayout = ((MotionKeyTrigger)motionKey0).mPostLayout;
        this.mCollisionRect = ((MotionKeyTrigger)motionKey0).mCollisionRect;
        this.mTargetRect = ((MotionKeyTrigger)motionKey0).mTargetRect;
        return this;
    }

    private void fireCustom(String s, MotionWidget motionWidget0) {
        boolean z = s.length() == 1;
        if(!z) {
            s = s.substring(1).toLowerCase(Locale.ROOT);
        }
        for(Object object0: this.mCustom.keySet()) {
            String s1 = (String)object0;
            if(z || s1.toLowerCase(Locale.ROOT).matches(s)) {
                CustomVariable customVariable0 = (CustomVariable)this.mCustom.get(s1);
                if(customVariable0 != null) {
                    customVariable0.applyToWidget(motionWidget0);
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public void getAttributeNames(HashSet hashSet0) {
    }

    @Override  // androidx.constraintlayout.core.motion.utils.TypedValues
    public int getId(String s) {
        s.hashCode();
        switch(s) {
            case "negativeCross": {
                return 310;
            }
            case "positiveCross": {
                return 309;
            }
            case "postLayout": {
                return 304;
            }
            case "triggerCollisionId": {
                return 307;
            }
            case "triggerCollisionView": {
                return 306;
            }
            case "triggerID": {
                return 308;
            }
            case "triggerReceiver": {
                return 311;
            }
            case "triggerSlack": {
                return 305;
            }
            case "viewTransitionOnCross": {
                return 301;
            }
            case "viewTransitionOnNegativeCross": {
                return 303;
            }
            case "viewTransitionOnPositiveCross": {
                return 302;
            }
            default: {
                return -1;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, float f) {
        if(v != 305) {
            return super.setValue(v, f);
        }
        this.mTriggerSlack = f;
        return true;
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, int v1) {
        switch(v) {
            case 301: {
                this.mViewTransitionOnCross = v1;
                return true;
            }
            case 302: {
                this.mViewTransitionOnPositiveCross = v1;
                return true;
            }
            case 303: {
                this.mViewTransitionOnNegativeCross = v1;
                return true;
            }
            case 307: {
                this.mTriggerCollisionId = v1;
                return true;
            }
            case 308: {
                this.mTriggerID = this.toInt(v1);
                return true;
            }
            case 311: {
                this.mTriggerReceiver = v1;
                return true;
            }
            default: {
                return super.setValue(v, v1);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, String s) {
        switch(v) {
            case 309: {
                this.mPositiveCross = s;
                return true;
            }
            case 310: {
                this.mNegativeCross = s;
                return true;
            }
            case 312: {
                this.mCross = s;
                return true;
            }
            default: {
                return super.setValue(v, s);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.motion.key.MotionKey
    public boolean setValue(int v, boolean z) {
        if(v != 304) {
            return super.setValue(v, z);
        }
        this.mPostLayout = z;
        return true;
    }
}

