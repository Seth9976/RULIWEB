package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R.id;
import androidx.constraintlayout.widget.R.styleable;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ViewTransition {
    static class Animate {
        KeyCache mCache;
        private final int mClearsTag;
        float mDpositionDt;
        int mDuration;
        boolean mHoldAt100;
        Interpolator mInterpolator;
        long mLastRender;
        MotionController mMC;
        float mPosition;
        boolean mReverse;
        private final int mSetsTag;
        long mStart;
        Rect mTempRec;
        int mUpDuration;
        ViewTransitionController mVtController;

        Animate(ViewTransitionController viewTransitionController0, MotionController motionController0, int v, int v1, int v2, Interpolator interpolator0, int v3, int v4) {
            this.mCache = new KeyCache();
            this.mReverse = false;
            this.mTempRec = new Rect();
            this.mHoldAt100 = false;
            this.mVtController = viewTransitionController0;
            this.mMC = motionController0;
            this.mDuration = v;
            this.mUpDuration = v1;
            long v5 = System.nanoTime();
            this.mStart = v5;
            this.mLastRender = v5;
            this.mVtController.addAnimation(this);
            this.mInterpolator = interpolator0;
            this.mSetsTag = v3;
            this.mClearsTag = v4;
            if(v2 == 3) {
                this.mHoldAt100 = true;
            }
            this.mDpositionDt = v == 0 ? 3.402823E+38f : 1.0f / ((float)v);
            this.mutate();
        }

        void mutate() {
            if(this.mReverse) {
                this.mutateReverse();
                return;
            }
            this.mutateForward();
        }

        void mutateForward() {
            long v = System.nanoTime();
            long v1 = v - this.mLastRender;
            this.mLastRender = v;
            float f = this.mPosition + ((float)(((double)v1) * 0.000001)) * this.mDpositionDt;
            this.mPosition = f;
            if(f >= 1.0f) {
                this.mPosition = 1.0f;
            }
            float f1 = this.mInterpolator == null ? this.mPosition : this.mInterpolator.getInterpolation(this.mPosition);
            boolean z = this.mMC.interpolate(this.mMC.mView, f1, v, this.mCache);
            if(this.mPosition >= 1.0f) {
                if(this.mSetsTag != -1) {
                    this.mMC.getView().setTag(this.mSetsTag, System.nanoTime());
                }
                if(this.mClearsTag != -1) {
                    this.mMC.getView().setTag(this.mClearsTag, null);
                }
                if(!this.mHoldAt100) {
                    this.mVtController.removeAnimation(this);
                }
            }
            if(this.mPosition >= 1.0f && !z) {
                return;
            }
            this.mVtController.invalidate();
        }

        void mutateReverse() {
            long v = System.nanoTime();
            long v1 = v - this.mLastRender;
            this.mLastRender = v;
            float f = this.mPosition - ((float)(((double)v1) * 0.000001)) * this.mDpositionDt;
            this.mPosition = f;
            if(f < 0.0f) {
                this.mPosition = 0.0f;
            }
            float f1 = this.mInterpolator == null ? this.mPosition : this.mInterpolator.getInterpolation(this.mPosition);
            boolean z = this.mMC.interpolate(this.mMC.mView, f1, v, this.mCache);
            if(this.mPosition <= 0.0f) {
                if(this.mSetsTag != -1) {
                    this.mMC.getView().setTag(this.mSetsTag, System.nanoTime());
                }
                if(this.mClearsTag != -1) {
                    this.mMC.getView().setTag(this.mClearsTag, null);
                }
                this.mVtController.removeAnimation(this);
            }
            if(this.mPosition <= 0.0f && !z) {
                return;
            }
            this.mVtController.invalidate();
        }

        public void reactTo(int v, float f, float f1) {
            switch(v) {
                case 1: {
                    if(!this.mReverse) {
                        this.reverse(true);
                        return;
                    }
                    break;
                }
                case 2: {
                    this.mMC.getView().getHitRect(this.mTempRec);
                    if(!this.mTempRec.contains(((int)f), ((int)f1)) && !this.mReverse) {
                        this.reverse(true);
                        return;
                    }
                    break;
                }
            }
        }

        void reverse(boolean z) {
            this.mReverse = z;
            if(z) {
                int v = this.mUpDuration;
                if(v != -1) {
                    this.mDpositionDt = v == 0 ? 3.402823E+38f : 1.0f / ((float)v);
                }
            }
            this.mVtController.invalidate();
            this.mLastRender = System.nanoTime();
        }
    }

    static final int ANTICIPATE = 6;
    static final int BOUNCE = 4;
    public static final String CONSTRAINT_OVERRIDE = "ConstraintOverride";
    public static final String CUSTOM_ATTRIBUTE = "CustomAttribute";
    public static final String CUSTOM_METHOD = "CustomMethod";
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    public static final String KEY_FRAME_SET_TAG = "KeyFrameSet";
    static final int LINEAR = 3;
    public static final int ONSTATE_ACTION_DOWN = 1;
    public static final int ONSTATE_ACTION_DOWN_UP = 3;
    public static final int ONSTATE_ACTION_UP = 2;
    public static final int ONSTATE_SHARED_VALUE_SET = 4;
    public static final int ONSTATE_SHARED_VALUE_UNSET = 5;
    static final int OVERSHOOT = 5;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "ViewTransition";
    private static final int UNSET = -1;
    static final int VIEWTRANSITIONMODE_ALLSTATES = 1;
    static final int VIEWTRANSITIONMODE_CURRENTSTATE = 0;
    static final int VIEWTRANSITIONMODE_NOSTATE = 2;
    public static final String VIEW_TRANSITION_TAG = "ViewTransition";
    private int mClearsTag;
    Constraint mConstraintDelta;
    Context mContext;
    private int mDefaultInterpolator;
    private int mDefaultInterpolatorID;
    private String mDefaultInterpolatorString;
    private boolean mDisabled;
    private int mDuration;
    private int mId;
    private int mIfTagNotSet;
    private int mIfTagSet;
    KeyFrames mKeyFrames;
    private int mOnStateTransition;
    private int mPathMotionArc;
    ConstraintSet mSet;
    private int mSetsTag;
    private int mSharedValueCurrent;
    private int mSharedValueID;
    private int mSharedValueTarget;
    private int mTargetId;
    private String mTargetString;
    private int mUpDuration;
    int mViewTransitionMode;

    // This method was un-flattened
    ViewTransition(Context context0, XmlPullParser xmlPullParser0) {
        this.mOnStateTransition = -1;
        this.mDisabled = false;
        this.mPathMotionArc = 0;
        this.mDuration = -1;
        this.mUpDuration = -1;
        this.mDefaultInterpolator = 0;
        this.mDefaultInterpolatorString = null;
        this.mDefaultInterpolatorID = -1;
        this.mSetsTag = -1;
        this.mClearsTag = -1;
        this.mIfTagSet = -1;
        this.mIfTagNotSet = -1;
        this.mSharedValueTarget = -1;
        this.mSharedValueID = -1;
        this.mSharedValueCurrent = -1;
        this.mContext = context0;
        try {
            for(int v = xmlPullParser0.getEventType(); v != 1; v = xmlPullParser0.next()) {
                switch(v) {
                    case 2: {
                        String s = xmlPullParser0.getName();
                        switch(s) {
                            case "ConstraintOverride": {
                                this.mConstraintDelta = ConstraintSet.buildDelta(context0, xmlPullParser0);
                                break;
                            }
                            case "CustomAttribute": {
                                ConstraintAttribute.parse(context0, xmlPullParser0, this.mConstraintDelta.mCustomConstraints);
                                break;
                            }
                            case "CustomMethod": {
                                ConstraintAttribute.parse(context0, xmlPullParser0, this.mConstraintDelta.mCustomConstraints);
                                break;
                            }
                            case "KeyFrameSet": {
                                this.mKeyFrames = new KeyFrames(context0, xmlPullParser0);
                                break;
                            }
                            case "ViewTransition": {
                                this.parseViewTransitionTags(context0, xmlPullParser0);
                                break;
                            }
                            default: {
                                Log.e("ViewTransition", ".(null:-1) <init>() unknown tag " + s);
                                Log.e("ViewTransition", ".xml:" + xmlPullParser0.getLineNumber());
                            }
                        }
                        break;
                    }
                    case 3: {
                        if("ViewTransition".equals(xmlPullParser0.getName())) {
                            return;
                        }
                    }
                }
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("ViewTransition", "Error parsing XML resource", xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("ViewTransition", "Error parsing XML resource", iOException0);
        }
    }

    void applyIndependentTransition(ViewTransitionController viewTransitionController0, MotionLayout motionLayout0, View view0) {
        MotionController motionController0 = new MotionController(view0);
        motionController0.setBothStates(view0);
        this.mKeyFrames.addAllFrames(motionController0);
        motionController0.setup(motionLayout0.getWidth(), motionLayout0.getHeight(), ((float)this.mDuration), System.nanoTime());
        new Animate(viewTransitionController0, motionController0, this.mDuration, this.mUpDuration, this.mOnStateTransition, this.getInterpolator(motionLayout0.getContext()), this.mSetsTag, this.mClearsTag);
    }

    void applyTransition(ViewTransitionController viewTransitionController0, MotionLayout motionLayout0, int v, ConstraintSet constraintSet0, View[] arr_view) {
        if(this.mDisabled) {
            return;
        }
        int v1 = this.mViewTransitionMode;
        if(v1 == 2) {
            this.applyIndependentTransition(viewTransitionController0, motionLayout0, arr_view[0]);
            return;
        }
        if(v1 == 1) {
            int[] arr_v = motionLayout0.getConstraintSetIds();
            for(int v3 = 0; v3 < arr_v.length; ++v3) {
                int v4 = arr_v[v3];
                if(v4 != v) {
                    ConstraintSet constraintSet1 = motionLayout0.getConstraintSet(v4);
                    for(int v5 = 0; v5 < arr_view.length; ++v5) {
                        Constraint constraintSet$Constraint0 = constraintSet1.getConstraint(arr_view[v5].getId());
                        Constraint constraintSet$Constraint1 = this.mConstraintDelta;
                        if(constraintSet$Constraint1 != null) {
                            constraintSet$Constraint1.applyDelta(constraintSet$Constraint0);
                            constraintSet$Constraint0.mCustomConstraints.putAll(this.mConstraintDelta.mCustomConstraints);
                        }
                    }
                }
            }
        }
        ConstraintSet constraintSet2 = new ConstraintSet();
        constraintSet2.clone(constraintSet0);
        for(int v6 = 0; v6 < arr_view.length; ++v6) {
            Constraint constraintSet$Constraint2 = constraintSet2.getConstraint(arr_view[v6].getId());
            Constraint constraintSet$Constraint3 = this.mConstraintDelta;
            if(constraintSet$Constraint3 != null) {
                constraintSet$Constraint3.applyDelta(constraintSet$Constraint2);
                constraintSet$Constraint2.mCustomConstraints.putAll(this.mConstraintDelta.mCustomConstraints);
            }
        }
        motionLayout0.updateState(v, constraintSet2);
        motionLayout0.updateState(id.view_transition, constraintSet0);
        motionLayout0.setState(id.view_transition, -1, -1);
        Transition motionScene$Transition0 = new Transition(-1, motionLayout0.mScene, id.view_transition, v);
        for(int v2 = 0; v2 < arr_view.length; ++v2) {
            this.updateTransition(motionScene$Transition0, arr_view[v2]);
        }
        motionLayout0.setTransition(motionScene$Transition0);
        motionLayout0.transitionToEnd(() -> {
            if(this.mSetsTag != -1) {
                for(int v1 = 0; v1 < arr_view.length; ++v1) {
                    arr_view[v1].setTag(this.mSetsTag, System.nanoTime());
                }
            }
            if(this.mClearsTag != -1) {
                for(int v = 0; v < arr_view.length; ++v) {
                    arr_view[v].setTag(this.mClearsTag, null);
                }
            }
        });
    }

    boolean checkTags(View view0) {
        boolean z;
        int v = this.mIfTagSet;
        if(v == -1) {
            z = true;
        }
        else if(view0.getTag(v) == null) {
            z = false;
        }
        else {
            z = true;
        }
        int v1 = this.mIfTagNotSet;
        if(v1 == -1) {
            return z;
        }
        return view0.getTag(v1) == null ? z : false;
    }

    int getId() {
        return this.mId;
    }

    Interpolator getInterpolator(Context context0) {
        int v = this.mDefaultInterpolator;
        if(v != -2) {
            switch(v) {
                case -1: {
                    return new Interpolator() {
                        @Override  // android.animation.TimeInterpolator
                        public float getInterpolation(float f) {
                            return (float)Easing.getInterpolator(this.mDefaultInterpolatorString).get(((double)f));
                        }
                    };
                }
                case 0: {
                    return new AccelerateDecelerateInterpolator();
                }
                case 1: {
                    return new AccelerateInterpolator();
                }
                case 2: {
                    return new DecelerateInterpolator();
                }
                case 4: {
                    return new BounceInterpolator();
                }
                case 5: {
                    return new OvershootInterpolator();
                }
                case 6: {
                    return new AnticipateInterpolator();
                }
                default: {
                    return null;
                }
            }
        }
        return AnimationUtils.loadInterpolator(context0, this.mDefaultInterpolatorID);
    }

    public int getSharedValue() {
        return this.mSharedValueTarget;
    }

    public int getSharedValueCurrent() {
        return this.mSharedValueCurrent;
    }

    public int getSharedValueID() {
        return this.mSharedValueID;
    }

    public int getStateTransition() {
        return this.mOnStateTransition;
    }

    boolean isEnabled() {
        return !this.mDisabled;
    }

    // 检测为 Lambda 实现
    void lambda$applyTransition$0$androidx-constraintlayout-motion-widget-ViewTransition(View[] arr_view) [...]

    boolean matchesView(View view0) {
        if(view0 == null) {
            return false;
        }
        if(this.mTargetId == -1 && this.mTargetString == null) {
            return false;
        }
        if(!this.checkTags(view0)) {
            return false;
        }
        if(view0.getId() == this.mTargetId) {
            return true;
        }
        if(this.mTargetString == null) {
            return false;
        }
        if(view0.getLayoutParams() instanceof LayoutParams) {
            String s = ((LayoutParams)view0.getLayoutParams()).constraintTag;
            return s != null && s.matches(this.mTargetString);
        }
        return false;
    }

    private void parseViewTransitionTags(Context context0, XmlPullParser xmlPullParser0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.ViewTransition);
        int v = typedArray0.getIndexCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            if(v2 == styleable.ViewTransition_android_id) {
                this.mId = typedArray0.getResourceId(v2, this.mId);
            }
            else if(v2 != styleable.ViewTransition_motionTarget) {
                if(v2 == styleable.ViewTransition_onStateTransition) {
                    this.mOnStateTransition = typedArray0.getInt(v2, this.mOnStateTransition);
                }
                else if(v2 == styleable.ViewTransition_transitionDisable) {
                    this.mDisabled = typedArray0.getBoolean(v2, this.mDisabled);
                }
                else if(v2 == styleable.ViewTransition_pathMotionArc) {
                    this.mPathMotionArc = typedArray0.getInt(v2, this.mPathMotionArc);
                }
                else if(v2 == styleable.ViewTransition_duration) {
                    this.mDuration = typedArray0.getInt(v2, this.mDuration);
                }
                else if(v2 == styleable.ViewTransition_upDuration) {
                    this.mUpDuration = typedArray0.getInt(v2, this.mUpDuration);
                }
                else if(v2 == styleable.ViewTransition_viewTransitionMode) {
                    this.mViewTransitionMode = typedArray0.getInt(v2, this.mViewTransitionMode);
                }
                else if(v2 == styleable.ViewTransition_motionInterpolator) {
                    TypedValue typedValue0 = typedArray0.peekValue(v2);
                    if(typedValue0.type == 1) {
                        int v4 = typedArray0.getResourceId(v2, -1);
                        this.mDefaultInterpolatorID = v4;
                        if(v4 != -1) {
                            this.mDefaultInterpolator = -2;
                        }
                    }
                    else if(typedValue0.type == 3) {
                        String s = typedArray0.getString(v2);
                        this.mDefaultInterpolatorString = s;
                        if(s == null || s.indexOf("/") <= 0) {
                            this.mDefaultInterpolator = -1;
                        }
                        else {
                            this.mDefaultInterpolatorID = typedArray0.getResourceId(v2, -1);
                            this.mDefaultInterpolator = -2;
                        }
                    }
                    else {
                        this.mDefaultInterpolator = typedArray0.getInteger(v2, this.mDefaultInterpolator);
                    }
                }
                else if(v2 == styleable.ViewTransition_setsTag) {
                    this.mSetsTag = typedArray0.getResourceId(v2, this.mSetsTag);
                }
                else if(v2 == styleable.ViewTransition_clearsTag) {
                    this.mClearsTag = typedArray0.getResourceId(v2, this.mClearsTag);
                }
                else if(v2 == styleable.ViewTransition_ifTagSet) {
                    this.mIfTagSet = typedArray0.getResourceId(v2, this.mIfTagSet);
                }
                else if(v2 == styleable.ViewTransition_ifTagNotSet) {
                    this.mIfTagNotSet = typedArray0.getResourceId(v2, this.mIfTagNotSet);
                }
                else if(v2 == styleable.ViewTransition_SharedValueId) {
                    this.mSharedValueID = typedArray0.getResourceId(v2, this.mSharedValueID);
                }
                else if(v2 == styleable.ViewTransition_SharedValue) {
                    this.mSharedValueTarget = typedArray0.getInteger(v2, this.mSharedValueTarget);
                }
            }
            else if(MotionLayout.IS_IN_EDIT_MODE) {
                int v3 = typedArray0.getResourceId(v2, this.mTargetId);
                this.mTargetId = v3;
                if(v3 == -1) {
                    this.mTargetString = typedArray0.getString(v2);
                }
            }
            else if(typedArray0.peekValue(v2).type == 3) {
                this.mTargetString = typedArray0.getString(v2);
            }
            else {
                this.mTargetId = typedArray0.getResourceId(v2, this.mTargetId);
            }
        }
        typedArray0.recycle();
    }

    void setEnabled(boolean z) {
        this.mDisabled = !z;
    }

    void setId(int v) {
        this.mId = v;
    }

    public void setSharedValue(int v) {
        this.mSharedValueTarget = v;
    }

    public void setSharedValueCurrent(int v) {
        this.mSharedValueCurrent = v;
    }

    public void setSharedValueID(int v) {
        this.mSharedValueID = v;
    }

    public void setStateTransition(int v) {
        this.mOnStateTransition = v;
    }

    boolean supports(int v) {
        int v1 = this.mOnStateTransition;
        if(v1 == 1) {
            return v == 0;
        }
        switch(v1) {
            case 2: {
                return v == 1;
            }
            case 3: {
                return v == 0;
            }
            default: {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "ViewTransition(" + Debug.getName(this.mContext, this.mId) + ")";
    }

    private void updateTransition(Transition motionScene$Transition0, View view0) {
        int v = this.mDuration;
        if(v != -1) {
            motionScene$Transition0.setDuration(v);
        }
        motionScene$Transition0.setPathMotionArc(this.mPathMotionArc);
        motionScene$Transition0.setInterpolatorInfo(this.mDefaultInterpolator, this.mDefaultInterpolatorString, this.mDefaultInterpolatorID);
        int v1 = view0.getId();
        KeyFrames keyFrames0 = this.mKeyFrames;
        if(keyFrames0 != null) {
            ArrayList arrayList0 = keyFrames0.getKeyFramesForView(-1);
            KeyFrames keyFrames1 = new KeyFrames();
            for(Object object0: arrayList0) {
                keyFrames1.addKey(((Key)object0).clone().setViewId(v1));
            }
            motionScene$Transition0.addKeyFrame(keyFrames1);
        }
    }
}

