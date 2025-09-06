package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
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
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R.id;
import androidx.constraintlayout.widget.R.styleable;
import androidx.constraintlayout.widget.StateSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MotionScene {
    public static class Transition {
        public static class TransitionOnClick implements View.OnClickListener {
            public static final int ANIM_TOGGLE = 17;
            public static final int ANIM_TO_END = 1;
            public static final int ANIM_TO_START = 16;
            public static final int JUMP_TO_END = 0x100;
            public static final int JUMP_TO_START = 0x1000;
            int mMode;
            int mTargetId;
            private final Transition mTransition;

            public TransitionOnClick(Context context0, Transition motionScene$Transition0, XmlPullParser xmlPullParser0) {
                this.mTargetId = -1;
                this.mMode = 17;
                this.mTransition = motionScene$Transition0;
                TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.OnClick);
                int v = typedArray0.getIndexCount();
                for(int v1 = 0; v1 < v; ++v1) {
                    int v2 = typedArray0.getIndex(v1);
                    if(v2 == styleable.OnClick_targetId) {
                        this.mTargetId = typedArray0.getResourceId(v2, this.mTargetId);
                    }
                    else if(v2 == styleable.OnClick_clickAction) {
                        this.mMode = typedArray0.getInt(v2, this.mMode);
                    }
                }
                typedArray0.recycle();
            }

            public TransitionOnClick(Transition motionScene$Transition0, int v, int v1) {
                this.mTransition = motionScene$Transition0;
                this.mTargetId = v;
                this.mMode = v1;
            }

            public void addOnClickListeners(MotionLayout motionLayout0, int v, Transition motionScene$Transition0) {
                int v1 = this.mTargetId;
                if(v1 != -1) {
                    motionLayout0 = motionLayout0.findViewById(v1);
                }
                if(motionLayout0 == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.mTargetId);
                    return;
                }
                int v2 = motionScene$Transition0.mConstraintSetStart;
                int v3 = motionScene$Transition0.mConstraintSetEnd;
                if(v2 == -1) {
                    motionLayout0.setOnClickListener(this);
                    return;
                }
                if((((this.mMode & 1) == 0 || v != v2 ? 0 : 1) | ((this.mMode & 0x100) == 0 || v != v2 ? 0 : 1) | ((this.mMode & 16) == 0 || v != v3 ? 0 : 1) | ((this.mMode & 0x1000) == 0 || v != v3 ? 0 : 1)) != 0) {
                    motionLayout0.setOnClickListener(this);
                }
            }

            boolean isTransitionViable(Transition motionScene$Transition0, MotionLayout motionLayout0) {
                Transition motionScene$Transition1 = this.mTransition;
                if(motionScene$Transition1 == motionScene$Transition0) {
                    return true;
                }
                int v = motionScene$Transition1.mConstraintSetEnd;
                int v1 = this.mTransition.mConstraintSetStart;
                return v1 == -1 ? motionLayout0.mCurrentState != v : motionLayout0.mCurrentState == v1 || motionLayout0.mCurrentState == v;
            }

            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                MotionLayout motionLayout0 = this.mTransition.mMotionScene.mMotionLayout;
                if(motionLayout0.isInteractionEnabled()) {
                    if(this.mTransition.mConstraintSetStart == -1) {
                        int v = motionLayout0.getCurrentState();
                        if(v == -1) {
                            motionLayout0.transitionToState(this.mTransition.mConstraintSetEnd);
                            return;
                        }
                        Transition motionScene$Transition0 = new Transition(this.mTransition.mMotionScene, this.mTransition);
                        motionScene$Transition0.mConstraintSetStart = v;
                        motionScene$Transition0.mConstraintSetEnd = this.mTransition.mConstraintSetEnd;
                        motionLayout0.setTransition(motionScene$Transition0);
                        motionLayout0.transitionToEnd();
                        return;
                    }
                    Transition motionScene$Transition1 = this.mTransition.mMotionScene.mCurrentTransition;
                    int v1 = 0;
                    int v2 = (this.mMode & 1) != 0 || (this.mMode & 0x100) != 0 ? 1 : 0;
                    boolean z = (this.mMode & 16) != 0 || (this.mMode & 0x1000) != 0;
                    if(v2 == 0 || !z) {
                        v1 = v2;
                    }
                    else {
                        Transition motionScene$Transition2 = this.mTransition;
                        if(this.mTransition.mMotionScene.mCurrentTransition != motionScene$Transition2) {
                            motionLayout0.setTransition(motionScene$Transition2);
                        }
                        if(motionLayout0.getCurrentState() != motionLayout0.getEndState() && motionLayout0.getProgress() <= 0.5f) {
                            v1 = v2;
                            z = false;
                        }
                    }
                    if(this.isTransitionViable(motionScene$Transition1, motionLayout0)) {
                        if(v1 != 0 && (this.mMode & 1) != 0) {
                            motionLayout0.setTransition(this.mTransition);
                            motionLayout0.transitionToEnd();
                            return;
                        }
                        if(z && (this.mMode & 16) != 0) {
                            motionLayout0.setTransition(this.mTransition);
                            motionLayout0.transitionToStart();
                            return;
                        }
                        if(v1 != 0 && (this.mMode & 0x100) != 0) {
                            motionLayout0.setTransition(this.mTransition);
                            motionLayout0.setProgress(1.0f);
                            return;
                        }
                        if(z && (this.mMode & 0x1000) != 0) {
                            motionLayout0.setTransition(this.mTransition);
                            motionLayout0.setProgress(0.0f);
                        }
                    }
                }
            }

            public void removeOnClickListeners(MotionLayout motionLayout0) {
                int v = this.mTargetId;
                if(v == -1) {
                    return;
                }
                View view0 = motionLayout0.findViewById(v);
                if(view0 == null) {
                    Log.e("MotionScene", " (*)  could not find id " + this.mTargetId);
                    return;
                }
                view0.setOnClickListener(null);
            }
        }

        public static final int AUTO_ANIMATE_TO_END = 4;
        public static final int AUTO_ANIMATE_TO_START = 3;
        public static final int AUTO_JUMP_TO_END = 2;
        public static final int AUTO_JUMP_TO_START = 1;
        public static final int AUTO_NONE = 0;
        public static final int INTERPOLATE_ANTICIPATE = 6;
        public static final int INTERPOLATE_BOUNCE = 4;
        public static final int INTERPOLATE_EASE_IN = 1;
        public static final int INTERPOLATE_EASE_IN_OUT = 0;
        public static final int INTERPOLATE_EASE_OUT = 2;
        public static final int INTERPOLATE_LINEAR = 3;
        public static final int INTERPOLATE_OVERSHOOT = 5;
        public static final int INTERPOLATE_REFERENCE_ID = -2;
        public static final int INTERPOLATE_SPLINE_STRING = -1;
        static final int TRANSITION_FLAG_FIRST_DRAW = 1;
        static final int TRANSITION_FLAG_INTERCEPT_TOUCH = 4;
        static final int TRANSITION_FLAG_INTRA_AUTO = 2;
        private int mAutoTransition;
        private int mConstraintSetEnd;
        private int mConstraintSetStart;
        private int mDefaultInterpolator;
        private int mDefaultInterpolatorID;
        private String mDefaultInterpolatorString;
        private boolean mDisable;
        private int mDuration;
        private int mId;
        private boolean mIsAbstract;
        private ArrayList mKeyFramesList;
        private int mLayoutDuringTransition;
        private final MotionScene mMotionScene;
        private ArrayList mOnClicks;
        private int mPathMotionArc;
        private float mStagger;
        private TouchResponse mTouchResponse;
        private int mTransitionFlags;

        public Transition(int v, MotionScene motionScene0, int v1, int v2) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mTransitionFlags = 0;
            this.mId = v;
            this.mMotionScene = motionScene0;
            this.mConstraintSetStart = v1;
            this.mConstraintSetEnd = v2;
            this.mDuration = motionScene0.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene0.mLayoutDuringTransition;
        }

        Transition(MotionScene motionScene0, Context context0, XmlPullParser xmlPullParser0) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mTransitionFlags = 0;
            this.mDuration = motionScene0.mDefaultDuration;
            this.mLayoutDuringTransition = motionScene0.mLayoutDuringTransition;
            this.mMotionScene = motionScene0;
            this.fillFromAttributeList(motionScene0, context0, Xml.asAttributeSet(xmlPullParser0));
        }

        Transition(MotionScene motionScene0, Transition motionScene$Transition0) {
            this.mId = -1;
            this.mIsAbstract = false;
            this.mConstraintSetEnd = -1;
            this.mConstraintSetStart = -1;
            this.mDefaultInterpolator = 0;
            this.mDefaultInterpolatorString = null;
            this.mDefaultInterpolatorID = -1;
            this.mDuration = 400;
            this.mStagger = 0.0f;
            this.mKeyFramesList = new ArrayList();
            this.mTouchResponse = null;
            this.mOnClicks = new ArrayList();
            this.mAutoTransition = 0;
            this.mDisable = false;
            this.mPathMotionArc = -1;
            this.mLayoutDuringTransition = 0;
            this.mTransitionFlags = 0;
            this.mMotionScene = motionScene0;
            this.mDuration = motionScene0.mDefaultDuration;
            if(motionScene$Transition0 != null) {
                this.mPathMotionArc = motionScene$Transition0.mPathMotionArc;
                this.mDefaultInterpolator = motionScene$Transition0.mDefaultInterpolator;
                this.mDefaultInterpolatorString = motionScene$Transition0.mDefaultInterpolatorString;
                this.mDefaultInterpolatorID = motionScene$Transition0.mDefaultInterpolatorID;
                this.mDuration = motionScene$Transition0.mDuration;
                this.mKeyFramesList = motionScene$Transition0.mKeyFramesList;
                this.mStagger = motionScene$Transition0.mStagger;
                this.mLayoutDuringTransition = motionScene$Transition0.mLayoutDuringTransition;
            }
        }

        public void addKeyFrame(KeyFrames keyFrames0) {
            this.mKeyFramesList.add(keyFrames0);
        }

        public void addOnClick(int v, int v1) {
            for(Object object0: this.mOnClicks) {
                TransitionOnClick motionScene$Transition$TransitionOnClick0 = (TransitionOnClick)object0;
                if(motionScene$Transition$TransitionOnClick0.mTargetId == v) {
                    motionScene$Transition$TransitionOnClick0.mMode = v1;
                    return;
                }
                if(false) {
                    break;
                }
            }
            TransitionOnClick motionScene$Transition$TransitionOnClick1 = new TransitionOnClick(this, v, v1);
            this.mOnClicks.add(motionScene$Transition$TransitionOnClick1);
        }

        public void addOnClick(Context context0, XmlPullParser xmlPullParser0) {
            this.mOnClicks.add(new TransitionOnClick(context0, this, xmlPullParser0));
        }

        public String debugString(Context context0) {
            String s = this.mConstraintSetStart == -1 ? "null" : context0.getResources().getResourceEntryName(this.mConstraintSetStart);
            return this.mConstraintSetEnd == -1 ? s + " -> null" : s + " -> " + context0.getResources().getResourceEntryName(this.mConstraintSetEnd);
        }

        private void fill(MotionScene motionScene0, Context context0, TypedArray typedArray0) {
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.Transition_constraintSetEnd) {
                    this.mConstraintSetEnd = typedArray0.getResourceId(v2, -1);
                    String s = context0.getResources().getResourceTypeName(this.mConstraintSetEnd);
                    if("layout".equals(s)) {
                        ConstraintSet constraintSet0 = new ConstraintSet();
                        constraintSet0.load(context0, this.mConstraintSetEnd);
                        motionScene0.mConstraintSetMap.append(this.mConstraintSetEnd, constraintSet0);
                    }
                    else if("xml".equals(s)) {
                        this.mConstraintSetEnd = motionScene0.parseInclude(context0, this.mConstraintSetEnd);
                    }
                }
                else if(v2 == styleable.Transition_constraintSetStart) {
                    this.mConstraintSetStart = typedArray0.getResourceId(v2, this.mConstraintSetStart);
                    String s1 = context0.getResources().getResourceTypeName(this.mConstraintSetStart);
                    if("layout".equals(s1)) {
                        ConstraintSet constraintSet1 = new ConstraintSet();
                        constraintSet1.load(context0, this.mConstraintSetStart);
                        motionScene0.mConstraintSetMap.append(this.mConstraintSetStart, constraintSet1);
                    }
                    else if("xml".equals(s1)) {
                        this.mConstraintSetStart = motionScene0.parseInclude(context0, this.mConstraintSetStart);
                    }
                }
                else if(v2 == styleable.Transition_motionInterpolator) {
                    TypedValue typedValue0 = typedArray0.peekValue(v2);
                    if(typedValue0.type == 1) {
                        int v3 = typedArray0.getResourceId(v2, -1);
                        this.mDefaultInterpolatorID = v3;
                        if(v3 != -1) {
                            this.mDefaultInterpolator = -2;
                        }
                    }
                    else if(typedValue0.type == 3) {
                        String s2 = typedArray0.getString(v2);
                        this.mDefaultInterpolatorString = s2;
                        if(s2 != null) {
                            if(s2.indexOf("/") > 0) {
                                this.mDefaultInterpolatorID = typedArray0.getResourceId(v2, -1);
                                this.mDefaultInterpolator = -2;
                            }
                            else {
                                this.mDefaultInterpolator = -1;
                            }
                        }
                    }
                    else {
                        this.mDefaultInterpolator = typedArray0.getInteger(v2, this.mDefaultInterpolator);
                    }
                }
                else if(v2 == styleable.Transition_duration) {
                    int v4 = typedArray0.getInt(v2, this.mDuration);
                    this.mDuration = v4;
                    if(v4 < 8) {
                        this.mDuration = 8;
                    }
                }
                else if(v2 == styleable.Transition_staggered) {
                    this.mStagger = typedArray0.getFloat(v2, this.mStagger);
                }
                else if(v2 == styleable.Transition_autoTransition) {
                    this.mAutoTransition = typedArray0.getInteger(v2, this.mAutoTransition);
                }
                else if(v2 == styleable.Transition_android_id) {
                    this.mId = typedArray0.getResourceId(v2, this.mId);
                }
                else if(v2 == styleable.Transition_transitionDisable) {
                    this.mDisable = typedArray0.getBoolean(v2, this.mDisable);
                }
                else if(v2 == styleable.Transition_pathMotionArc) {
                    this.mPathMotionArc = typedArray0.getInteger(v2, -1);
                }
                else if(v2 == styleable.Transition_layoutDuringTransition) {
                    this.mLayoutDuringTransition = typedArray0.getInteger(v2, 0);
                }
                else if(v2 == styleable.Transition_transitionFlags) {
                    this.mTransitionFlags = typedArray0.getInteger(v2, 0);
                }
            }
            if(this.mConstraintSetStart == -1) {
                this.mIsAbstract = true;
            }
        }

        private void fillFromAttributeList(MotionScene motionScene0, Context context0, AttributeSet attributeSet0) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Transition);
            this.fill(motionScene0, context0, typedArray0);
            typedArray0.recycle();
        }

        public int getAutoTransition() {
            return this.mAutoTransition;
        }

        public int getDuration() {
            return this.mDuration;
        }

        public int getEndConstraintSetId() {
            return this.mConstraintSetEnd;
        }

        public int getId() {
            return this.mId;
        }

        public List getKeyFrameList() {
            return this.mKeyFramesList;
        }

        public int getLayoutDuringTransition() {
            return this.mLayoutDuringTransition;
        }

        public List getOnClickList() {
            return this.mOnClicks;
        }

        public int getPathMotionArc() {
            return this.mPathMotionArc;
        }

        public float getStagger() {
            return this.mStagger;
        }

        public int getStartConstraintSetId() {
            return this.mConstraintSetStart;
        }

        public TouchResponse getTouchResponse() {
            return this.mTouchResponse;
        }

        public boolean isEnabled() {
            return !this.mDisable;
        }

        public boolean isTransitionFlag(int v) {
            return (v & this.mTransitionFlags) != 0;
        }

        public void removeOnClick(int v) {
            TransitionOnClick motionScene$Transition$TransitionOnClick0 = null;
            for(Object object0: this.mOnClicks) {
                TransitionOnClick motionScene$Transition$TransitionOnClick1 = (TransitionOnClick)object0;
                if(motionScene$Transition$TransitionOnClick1.mTargetId == v) {
                    motionScene$Transition$TransitionOnClick0 = motionScene$Transition$TransitionOnClick1;
                    break;
                }
            }
            if(motionScene$Transition$TransitionOnClick0 != null) {
                this.mOnClicks.remove(motionScene$Transition$TransitionOnClick0);
            }
        }

        public void setAutoTransition(int v) {
            this.mAutoTransition = v;
        }

        public void setDuration(int v) {
            this.mDuration = Math.max(v, 8);
        }

        public void setEnabled(boolean z) {
            this.mDisable = !z;
        }

        public void setInterpolatorInfo(int v, String s, int v1) {
            this.mDefaultInterpolator = v;
            this.mDefaultInterpolatorString = s;
            this.mDefaultInterpolatorID = v1;
        }

        public void setLayoutDuringTransition(int v) {
            this.mLayoutDuringTransition = v;
        }

        public void setOnSwipe(OnSwipe onSwipe0) {
            this.mTouchResponse = onSwipe0 == null ? null : new TouchResponse(this.mMotionScene.mMotionLayout, onSwipe0);
        }

        public void setOnTouchUp(int v) {
            TouchResponse touchResponse0 = this.getTouchResponse();
            if(touchResponse0 != null) {
                touchResponse0.setTouchUpMode(v);
            }
        }

        public void setPathMotionArc(int v) {
            this.mPathMotionArc = v;
        }

        public void setStagger(float f) {
            this.mStagger = f;
        }

        public void setTransitionFlag(int v) {
            this.mTransitionFlags = v;
        }
    }

    static final int ANTICIPATE = 6;
    static final int BOUNCE = 4;
    private static final String CONSTRAINTSET_TAG = "ConstraintSet";
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DESKTOP = false;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final String INCLUDE_TAG = "include";
    private static final String INCLUDE_TAG_UC = "Include";
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final String KEYFRAMESET_TAG = "KeyFrameSet";
    public static final int LAYOUT_CALL_MEASURE = 2;
    public static final int LAYOUT_HONOR_REQUEST = 1;
    public static final int LAYOUT_IGNORE_REQUEST = 0;
    static final int LINEAR = 3;
    private static final int MIN_DURATION = 8;
    private static final String MOTIONSCENE_TAG = "MotionScene";
    private static final String ONCLICK_TAG = "OnClick";
    private static final String ONSWIPE_TAG = "OnSwipe";
    static final int OVERSHOOT = 5;
    private static final int SPLINE_STRING = -1;
    private static final String STATESET_TAG = "StateSet";
    private static final String TAG = "MotionScene";
    static final int TRANSITION_BACKWARD = 0;
    static final int TRANSITION_FORWARD = 1;
    private static final String TRANSITION_TAG = "Transition";
    public static final int UNSET = -1;
    private static final String VIEW_TRANSITION = "ViewTransition";
    private ArrayList mAbstractTransitionList;
    private HashMap mConstraintSetIdMap;
    private SparseArray mConstraintSetMap;
    Transition mCurrentTransition;
    private int mDefaultDuration;
    private Transition mDefaultTransition;
    private SparseIntArray mDeriveMap;
    private boolean mDisableAutoTransition;
    private boolean mIgnoreTouch;
    private MotionEvent mLastTouchDown;
    float mLastTouchX;
    float mLastTouchY;
    private int mLayoutDuringTransition;
    private final MotionLayout mMotionLayout;
    private boolean mMotionOutsideRegion;
    private boolean mRtl;
    StateSet mStateSet;
    private ArrayList mTransitionList;
    private MotionTracker mVelocityTracker;
    final ViewTransitionController mViewTransitionController;

    MotionScene(Context context0, MotionLayout motionLayout0, int v) {
        this.mStateSet = null;
        this.mCurrentTransition = null;
        this.mDisableAutoTransition = false;
        this.mTransitionList = new ArrayList();
        this.mDefaultTransition = null;
        this.mAbstractTransitionList = new ArrayList();
        this.mConstraintSetMap = new SparseArray();
        this.mConstraintSetIdMap = new HashMap();
        this.mDeriveMap = new SparseIntArray();
        this.mDefaultDuration = 400;
        this.mLayoutDuringTransition = 0;
        this.mIgnoreTouch = false;
        this.mMotionOutsideRegion = false;
        this.mMotionLayout = motionLayout0;
        this.mViewTransitionController = new ViewTransitionController(motionLayout0);
        this.load(context0, v);
        SparseArray sparseArray0 = this.mConstraintSetMap;
        ConstraintSet constraintSet0 = new ConstraintSet();
        sparseArray0.put(id.motion_base, constraintSet0);
        this.mConstraintSetIdMap.put("motion_base", id.motion_base);
    }

    public MotionScene(MotionLayout motionLayout0) {
        this.mStateSet = null;
        this.mCurrentTransition = null;
        this.mDisableAutoTransition = false;
        this.mTransitionList = new ArrayList();
        this.mDefaultTransition = null;
        this.mAbstractTransitionList = new ArrayList();
        this.mConstraintSetMap = new SparseArray();
        this.mConstraintSetIdMap = new HashMap();
        this.mDeriveMap = new SparseIntArray();
        this.mDefaultDuration = 400;
        this.mLayoutDuringTransition = 0;
        this.mIgnoreTouch = false;
        this.mMotionOutsideRegion = false;
        this.mMotionLayout = motionLayout0;
        this.mViewTransitionController = new ViewTransitionController(motionLayout0);
    }

    public void addOnClickListeners(MotionLayout motionLayout0, int v) {
        for(Object object0: this.mTransitionList) {
            Transition motionScene$Transition0 = (Transition)object0;
            if(motionScene$Transition0.mOnClicks.size() > 0) {
                for(Object object1: motionScene$Transition0.mOnClicks) {
                    ((TransitionOnClick)object1).removeOnClickListeners(motionLayout0);
                }
            }
        }
        for(Object object2: this.mAbstractTransitionList) {
            Transition motionScene$Transition1 = (Transition)object2;
            if(motionScene$Transition1.mOnClicks.size() > 0) {
                for(Object object3: motionScene$Transition1.mOnClicks) {
                    ((TransitionOnClick)object3).removeOnClickListeners(motionLayout0);
                }
            }
        }
        for(Object object4: this.mTransitionList) {
            Transition motionScene$Transition2 = (Transition)object4;
            if(motionScene$Transition2.mOnClicks.size() > 0) {
                for(Object object5: motionScene$Transition2.mOnClicks) {
                    ((TransitionOnClick)object5).addOnClickListeners(motionLayout0, v, motionScene$Transition2);
                }
            }
        }
        for(Object object6: this.mAbstractTransitionList) {
            Transition motionScene$Transition3 = (Transition)object6;
            if(motionScene$Transition3.mOnClicks.size() > 0) {
                for(Object object7: motionScene$Transition3.mOnClicks) {
                    ((TransitionOnClick)object7).addOnClickListeners(motionLayout0, v, motionScene$Transition3);
                }
            }
        }
    }

    public void addTransition(Transition motionScene$Transition0) {
        int v = this.getIndex(motionScene$Transition0);
        if(v == -1) {
            this.mTransitionList.add(motionScene$Transition0);
            return;
        }
        this.mTransitionList.set(v, motionScene$Transition0);
    }

    public boolean applyViewTransition(int v, MotionController motionController0) {
        return this.mViewTransitionController.applyViewTransition(v, motionController0);
    }

    boolean autoTransition(MotionLayout motionLayout0, int v) {
        if(this.isProcessingTouch()) {
            return false;
        }
        if(this.mDisableAutoTransition) {
            return false;
        }
        for(Object object0: this.mTransitionList) {
            Transition motionScene$Transition0 = (Transition)object0;
            if(motionScene$Transition0.mAutoTransition != 0 && (this.mCurrentTransition != motionScene$Transition0 || !this.mCurrentTransition.isTransitionFlag(2))) {
                if(v == motionScene$Transition0.mConstraintSetStart && (motionScene$Transition0.mAutoTransition == 2 || motionScene$Transition0.mAutoTransition == 4)) {
                    motionLayout0.setState(TransitionState.FINISHED);
                    motionLayout0.setTransition(motionScene$Transition0);
                    if(motionScene$Transition0.mAutoTransition == 4) {
                        motionLayout0.transitionToEnd();
                        motionLayout0.setState(TransitionState.SETUP);
                        motionLayout0.setState(TransitionState.MOVING);
                        return true;
                    }
                    motionLayout0.setProgress(1.0f);
                    motionLayout0.evaluate(true);
                    motionLayout0.setState(TransitionState.SETUP);
                    motionLayout0.setState(TransitionState.MOVING);
                    motionLayout0.setState(TransitionState.FINISHED);
                    motionLayout0.onNewStateAttachHandlers();
                    return true;
                }
                if(v == motionScene$Transition0.mConstraintSetEnd && (motionScene$Transition0.mAutoTransition == 1 || motionScene$Transition0.mAutoTransition == 3)) {
                    motionLayout0.setState(TransitionState.FINISHED);
                    motionLayout0.setTransition(motionScene$Transition0);
                    if(motionScene$Transition0.mAutoTransition == 3) {
                        motionLayout0.transitionToStart();
                        motionLayout0.setState(TransitionState.SETUP);
                        motionLayout0.setState(TransitionState.MOVING);
                        return true;
                    }
                    motionLayout0.setProgress(0.0f);
                    motionLayout0.evaluate(true);
                    motionLayout0.setState(TransitionState.SETUP);
                    motionLayout0.setState(TransitionState.MOVING);
                    motionLayout0.setState(TransitionState.FINISHED);
                    motionLayout0.onNewStateAttachHandlers();
                    return true;
                }
                if(false) {
                    break;
                }
            }
        }
        return false;
    }

    public Transition bestTransitionFor(int v, float f, float f1, MotionEvent motionEvent0) {
        if(v != -1) {
            List list0 = this.getTransitionsWithState(v);
            RectF rectF0 = new RectF();
            float f2 = 0.0f;
            Transition motionScene$Transition0 = null;
            for(Object object0: list0) {
                Transition motionScene$Transition1 = (Transition)object0;
                if(!motionScene$Transition1.mDisable && motionScene$Transition1.mTouchResponse != null) {
                    motionScene$Transition1.mTouchResponse.setRTL(this.mRtl);
                    RectF rectF1 = motionScene$Transition1.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF0);
                    if(rectF1 == null || motionEvent0 == null || rectF1.contains(motionEvent0.getX(), motionEvent0.getY())) {
                        RectF rectF2 = motionScene$Transition1.mTouchResponse.getLimitBoundsTo(this.mMotionLayout, rectF0);
                        if(rectF2 == null || motionEvent0 == null || rectF2.contains(motionEvent0.getX(), motionEvent0.getY())) {
                            float f3 = motionScene$Transition1.mTouchResponse.dot(f, f1);
                            if(motionScene$Transition1.mTouchResponse.mIsRotateMode && motionEvent0 != null) {
                                float f4 = motionEvent0.getX() - motionScene$Transition1.mTouchResponse.mRotateCenterX;
                                float f5 = motionEvent0.getY() - motionScene$Transition1.mTouchResponse.mRotateCenterY;
                                f3 = ((float)(Math.atan2(f1 + f5, f + f4) - Math.atan2(f4, f5))) * 10.0f;
                            }
                            float f6 = f3 * (motionScene$Transition1.mConstraintSetEnd == v ? -1.0f : 1.1f);
                            if(f6 > f2) {
                                motionScene$Transition0 = motionScene$Transition1;
                                f2 = f6;
                            }
                        }
                    }
                }
            }
            return motionScene$Transition0;
        }
        return this.mCurrentTransition;
    }

    public void disableAutoTransition(boolean z) {
        this.mDisableAutoTransition = z;
    }

    public void enableViewTransition(int v, boolean z) {
        this.mViewTransitionController.enableViewTransition(v, z);
    }

    public int gatPathMotionArc() {
        return this.mCurrentTransition == null ? -1 : this.mCurrentTransition.mPathMotionArc;
    }

    int getAutoCompleteMode() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0 : this.mCurrentTransition.mTouchResponse.getAutoCompleteMode();
    }

    ConstraintSet getConstraintSet(int v) {
        return this.getConstraintSet(v, -1, -1);
    }

    ConstraintSet getConstraintSet(int v, int v1, int v2) {
        StateSet stateSet0 = this.mStateSet;
        if(stateSet0 != null) {
            int v3 = stateSet0.stateGetConstraintID(v, v1, v2);
            if(v3 != -1) {
                v = v3;
            }
        }
        if(this.mConstraintSetMap.get(v) == null) {
            Log.e("MotionScene", "Warning could not find ConstraintSet id/" + Debug.getName(this.mMotionLayout.getContext(), v) + " In MotionScene");
            return (ConstraintSet)this.mConstraintSetMap.get(this.mConstraintSetMap.keyAt(0));
        }
        return (ConstraintSet)this.mConstraintSetMap.get(v);
    }

    public ConstraintSet getConstraintSet(Context context0, String s) {
        for(int v = 0; v < this.mConstraintSetMap.size(); ++v) {
            int v1 = this.mConstraintSetMap.keyAt(v);
            if(s.equals(context0.getResources().getResourceName(v1))) {
                return (ConstraintSet)this.mConstraintSetMap.get(v1);
            }
        }
        return null;
    }

    public int[] getConstraintSetIds() {
        int v = this.mConstraintSetMap.size();
        int[] arr_v = new int[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_v[v1] = this.mConstraintSetMap.keyAt(v1);
        }
        return arr_v;
    }

    public ArrayList getDefinedTransitions() {
        return this.mTransitionList;
    }

    public int getDuration() {
        return this.mCurrentTransition == null ? this.mDefaultDuration : this.mCurrentTransition.mDuration;
    }

    int getEndId() {
        return this.mCurrentTransition == null ? -1 : this.mCurrentTransition.mConstraintSetEnd;
    }

    private int getId(Context context0, String s) {
        int v = s.contains("/") ? context0.getResources().getIdentifier(s.substring(s.indexOf(0x2F) + 1), "id", "com.ruliweb.www.ruliapp") : -1;
        if(v == -1) {
            if(s.length() > 1) {
                return Integer.parseInt(s.substring(1));
            }
            Log.e("MotionScene", "error in parsing id");
        }
        return v;
    }

    private int getIndex(Transition motionScene$Transition0) {
        int v = motionScene$Transition0.mId;
        if(v == -1) {
            throw new IllegalArgumentException("The transition must have an id");
        }
        for(int v1 = 0; v1 < this.mTransitionList.size(); ++v1) {
            if(((Transition)this.mTransitionList.get(v1)).mId == v) {
                return v1;
            }
        }
        return -1;
    }

    public Interpolator getInterpolator() {
        int v = this.mCurrentTransition.mDefaultInterpolator;
        if(v != -2) {
            switch(v) {
                case -1: {
                    return new Interpolator() {
                        @Override  // android.animation.TimeInterpolator
                        public float getInterpolation(float f) {
                            return (float)Easing.getInterpolator(this.mCurrentTransition.mDefaultInterpolatorString).get(((double)f));
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
        return AnimationUtils.loadInterpolator(this.mMotionLayout.getContext(), this.mCurrentTransition.mDefaultInterpolatorID);
    }

    Key getKeyFrame(Context context0, int v, int v1, int v2) {
        Transition motionScene$Transition0 = this.mCurrentTransition;
        if(motionScene$Transition0 == null) {
            return null;
        }
        for(Object object0: motionScene$Transition0.mKeyFramesList) {
            KeyFrames keyFrames0 = (KeyFrames)object0;
            for(Object object1: keyFrames0.getKeys()) {
                Integer integer0 = (Integer)object1;
                if(v1 == ((int)integer0)) {
                    for(Object object2: keyFrames0.getKeyFramesForView(((int)integer0))) {
                        Key key0 = (Key)object2;
                        if(key0.mFramePosition == v2 && key0.mType == v) {
                            return key0;
                        }
                        if(false) {
                            break;
                        }
                    }
                    if(false) {
                        break;
                    }
                }
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public void getKeyFrames(MotionController motionController0) {
        Transition motionScene$Transition0 = this.mCurrentTransition;
        if(motionScene$Transition0 == null) {
            Transition motionScene$Transition1 = this.mDefaultTransition;
            if(motionScene$Transition1 != null) {
                for(Object object0: motionScene$Transition1.mKeyFramesList) {
                    ((KeyFrames)object0).addFrames(motionController0);
                }
            }
        }
        else {
            for(Object object1: motionScene$Transition0.mKeyFramesList) {
                ((KeyFrames)object1).addFrames(motionController0);
            }
        }
    }

    static String getLine(Context context0, int v, XmlPullParser xmlPullParser0) {
        return ".(" + Debug.getName(context0, v) + ".xml:" + xmlPullParser0.getLineNumber() + ") \"" + xmlPullParser0.getName() + "\"";
    }

    public int[] getMatchingStateLabels(String[] arr_s) {
        int v = this.mConstraintSetMap.size();
        int[] arr_v = new int[v];
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintSet constraintSet0 = (ConstraintSet)this.mConstraintSetMap.valueAt(v1);
            int v3 = this.mConstraintSetMap.keyAt(v1);
            if(constraintSet0.matchesLabels(arr_s)) {
                constraintSet0.getStateLabels();
                arr_v[v2] = v3;
                ++v2;
            }
        }
        return Arrays.copyOf(arr_v, v2);
    }

    float getMaxAcceleration() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0.0f : this.mCurrentTransition.mTouchResponse.getMaxAcceleration();
    }

    float getMaxVelocity() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0.0f : this.mCurrentTransition.mTouchResponse.getMaxVelocity();
    }

    boolean getMoveWhenScrollAtTop() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? false : this.mCurrentTransition.mTouchResponse.getMoveWhenScrollAtTop();
    }

    public float getPathPercent(View view0, int v) {
        return 0.0f;
    }

    float getProgressDirection(float f, float f1) {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0.0f : this.mCurrentTransition.mTouchResponse.getProgressDirection(f, f1);
    }

    private int getRealID(int v) {
        StateSet stateSet0 = this.mStateSet;
        if(stateSet0 != null) {
            int v1 = stateSet0.stateGetConstraintID(v, -1, -1);
            return v1 == -1 ? v : v1;
        }
        return v;
    }

    int getSpringBoundary() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0 : this.mCurrentTransition.mTouchResponse.getSpringBoundary();
    }

    float getSpringDamping() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0.0f : this.mCurrentTransition.mTouchResponse.getSpringDamping();
    }

    float getSpringMass() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0.0f : this.mCurrentTransition.mTouchResponse.getSpringMass();
    }

    float getSpringStiffiness() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0.0f : this.mCurrentTransition.mTouchResponse.getSpringStiffness();
    }

    float getSpringStopThreshold() {
        return this.mCurrentTransition == null || this.mCurrentTransition.mTouchResponse == null ? 0.0f : this.mCurrentTransition.mTouchResponse.getSpringStopThreshold();
    }

    public float getStaggered() {
        return this.mCurrentTransition == null ? 0.0f : this.mCurrentTransition.mStagger;
    }

    int getStartId() {
        return this.mCurrentTransition == null ? -1 : this.mCurrentTransition.mConstraintSetStart;
    }

    public Transition getTransitionById(int v) {
        for(Object object0: this.mTransitionList) {
            Transition motionScene$Transition0 = (Transition)object0;
            if(motionScene$Transition0.mId == v) {
                return motionScene$Transition0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    int getTransitionDirection(int v) {
        for(Object object0: this.mTransitionList) {
            if(((Transition)object0).mConstraintSetStart == v) {
                return 0;
            }
            if(false) {
                break;
            }
        }
        return 1;
    }

    public List getTransitionsWithState(int v) {
        int v1 = this.getRealID(v);
        List list0 = new ArrayList();
        for(Object object0: this.mTransitionList) {
            Transition motionScene$Transition0 = (Transition)object0;
            if(motionScene$Transition0.mConstraintSetStart == v1 || motionScene$Transition0.mConstraintSetEnd == v1) {
                ((ArrayList)list0).add(motionScene$Transition0);
            }
        }
        return list0;
    }

    private boolean hasCycleDependency(int v) {
        int v1 = this.mDeriveMap.get(v);
        for(int v2 = this.mDeriveMap.size(); v1 > 0; --v2) {
            if(v1 == v) {
                return true;
            }
            if(v2 < 0) {
                return true;
            }
            v1 = this.mDeriveMap.get(v1);
        }
        return false;
    }

    boolean hasKeyFramePosition(View view0, int v) {
        Transition motionScene$Transition0 = this.mCurrentTransition;
        if(motionScene$Transition0 == null) {
            return false;
        }
        for(Object object0: motionScene$Transition0.mKeyFramesList) {
            for(Object object1: ((KeyFrames)object0).getKeyFramesForView(view0.getId())) {
                if(((Key)object1).mFramePosition == v) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private boolean isProcessingTouch() {
        return this.mVelocityTracker != null;
    }

    public boolean isViewTransitionEnabled(int v) {
        return this.mViewTransitionController.isViewTransitionEnabled(v);
    }

    private void load(Context context0, int v) {
        XmlResourceParser xmlResourceParser0 = context0.getResources().getXml(v);
        try {
            int v1 = xmlResourceParser0.getEventType();
            Transition motionScene$Transition0 = null;
            while(true) {
                switch(v1) {
                    case 1: {
                        return;
                    }
                    case 2: {
                        switch(xmlResourceParser0.getName()) {
                            case "ConstraintSet": {
                                this.parseConstraintSet(context0, xmlResourceParser0);
                                break;
                            }
                            case "Include": {
                                this.parseInclude(context0, xmlResourceParser0);
                                break;
                            }
                            case "KeyFrameSet": {
                                KeyFrames keyFrames0 = new KeyFrames(context0, xmlResourceParser0);
                                if(motionScene$Transition0 != null) {
                                    motionScene$Transition0.mKeyFramesList.add(keyFrames0);
                                }
                                break;
                            }
                            case "MotionScene": {
                                this.parseMotionSceneTags(context0, xmlResourceParser0);
                                break;
                            }
                            case "OnClick": {
                                if(motionScene$Transition0 != null && !this.mMotionLayout.isInEditMode()) {
                                    motionScene$Transition0.addOnClick(context0, xmlResourceParser0);
                                }
                                break;
                            }
                            case "OnSwipe": {
                                if(motionScene$Transition0 == null) {
                                    Log.v("MotionScene", " OnSwipe (" + context0.getResources().getResourceEntryName(v) + ".xml:" + xmlResourceParser0.getLineNumber() + ")");
                                }
                                if(motionScene$Transition0 != null) {
                                    motionScene$Transition0.mTouchResponse = new TouchResponse(context0, this.mMotionLayout, xmlResourceParser0);
                                }
                                break;
                            }
                            case "StateSet": {
                                this.mStateSet = new StateSet(context0, xmlResourceParser0);
                                break;
                            }
                            case "Transition": {
                                ArrayList arrayList0 = this.mTransitionList;
                                motionScene$Transition0 = new Transition(this, context0, xmlResourceParser0);
                                arrayList0.add(motionScene$Transition0);
                                if(this.mCurrentTransition == null && !motionScene$Transition0.mIsAbstract) {
                                    this.mCurrentTransition = motionScene$Transition0;
                                    if(motionScene$Transition0.mTouchResponse != null) {
                                        this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
                                    }
                                }
                                if(motionScene$Transition0.mIsAbstract) {
                                    if(motionScene$Transition0.mConstraintSetEnd == -1) {
                                        this.mDefaultTransition = motionScene$Transition0;
                                    }
                                    else {
                                        this.mAbstractTransitionList.add(motionScene$Transition0);
                                    }
                                    this.mTransitionList.remove(motionScene$Transition0);
                                }
                                break;
                            }
                            case "ViewTransition": {
                                ViewTransition viewTransition0 = new ViewTransition(context0, xmlResourceParser0);
                                this.mViewTransitionController.add(viewTransition0);
                                break;
                            }
                            case "include": {
                                this.parseInclude(context0, xmlResourceParser0);
                            }
                        }
                        v1 = xmlResourceParser0.next();
                        break;
                    }
                    default: {
                        v1 = xmlResourceParser0.next();
                        break;
                    }
                }
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("MotionScene", "Error parsing resource: " + v, xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("MotionScene", "Error parsing resource: " + v, iOException0);
        }
    }

    public int lookUpConstraintId(String s) {
        Integer integer0 = (Integer)this.mConstraintSetIdMap.get(s);
        return integer0 == null ? 0 : ((int)integer0);
    }

    public String lookUpConstraintName(int v) {
        for(Object object0: this.mConstraintSetIdMap.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            Integer integer0 = (Integer)map$Entry0.getValue();
            if(integer0 != null && ((int)integer0) == v) {
                return (String)map$Entry0.getKey();
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
    }

    // This method was un-flattened
    private int parseConstraintSet(Context context0, XmlPullParser xmlPullParser0) {
        ConstraintSet constraintSet0 = new ConstraintSet();
        constraintSet0.setForceId(false);
        int v = xmlPullParser0.getAttributeCount();
        int v2 = -1;
        int v3 = -1;
        for(int v1 = 0; v1 < v; ++v1) {
            String s = xmlPullParser0.getAttributeName(v1);
            String s1 = xmlPullParser0.getAttributeValue(v1);
            s.hashCode();
            switch(s) {
                case "constraintRotate": {
                    try {
                        constraintSet0.mRotate = Integer.parseInt(s1);
                    }
                    catch(NumberFormatException unused_ex) {
                        s1.hashCode();
                        switch(s1) {
                            case "left": {
                                constraintSet0.mRotate = 2;
                                break;
                            }
                            case "none": {
                                constraintSet0.mRotate = 0;
                                break;
                            }
                            case "right": {
                                constraintSet0.mRotate = 1;
                                break;
                            }
                            case "x_left": {
                                constraintSet0.mRotate = 4;
                                break;
                            }
                            case "x_right": {
                                constraintSet0.mRotate = 3;
                            }
                        }
                    }
                    break;
                }
                case "deriveConstraintsFrom": {
                    v3 = this.getId(context0, s1);
                    break;
                }
                case "id": {
                    v2 = this.getId(context0, s1);
                    this.mConstraintSetIdMap.put(MotionScene.stripID(s1), v2);
                    constraintSet0.mIdString = Debug.getName(context0, v2);
                    break;
                }
                case "stateLabels": {
                    constraintSet0.setStateLabels(s1);
                }
            }
        }
        if(v2 != -1) {
            if(this.mMotionLayout.mDebugPath != 0) {
                constraintSet0.setValidateOnParse(true);
            }
            constraintSet0.load(context0, xmlPullParser0);
            if(v3 != -1) {
                this.mDeriveMap.put(v2, v3);
            }
            this.mConstraintSetMap.put(v2, constraintSet0);
        }
        return v2;
    }

    private int parseInclude(Context context0, int v) {
        XmlResourceParser xmlResourceParser0 = context0.getResources().getXml(v);
        try {
            for(int v1 = xmlResourceParser0.getEventType(); v1 != 1; v1 = xmlResourceParser0.next()) {
                if(2 == v1 && "ConstraintSet".equals(xmlResourceParser0.getName())) {
                    return this.parseConstraintSet(context0, xmlResourceParser0);
                }
            }
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("MotionScene", "Error parsing resource: " + v, xmlPullParserException0);
        }
        catch(IOException iOException0) {
            Log.e("MotionScene", "Error parsing resource: " + v, iOException0);
        }
        return -1;
    }

    private void parseInclude(Context context0, XmlPullParser xmlPullParser0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.include);
        int v = typedArray0.getIndexCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            if(v2 == styleable.include_constraintSet) {
                this.parseInclude(context0, typedArray0.getResourceId(v2, -1));
            }
        }
        typedArray0.recycle();
    }

    private void parseMotionSceneTags(Context context0, XmlPullParser xmlPullParser0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.MotionScene);
        int v = typedArray0.getIndexCount();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = typedArray0.getIndex(v1);
            if(v2 == styleable.MotionScene_defaultDuration) {
                int v3 = typedArray0.getInt(v2, this.mDefaultDuration);
                this.mDefaultDuration = v3;
                if(v3 < 8) {
                    this.mDefaultDuration = 8;
                }
            }
            else if(v2 == styleable.MotionScene_layoutDuringTransition) {
                this.mLayoutDuringTransition = typedArray0.getInteger(v2, 0);
            }
        }
        typedArray0.recycle();
    }

    void processScrollMove(float f, float f1) {
        if(this.mCurrentTransition != null && this.mCurrentTransition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollMove(f, f1);
        }
    }

    void processScrollUp(float f, float f1) {
        if(this.mCurrentTransition != null && this.mCurrentTransition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.scrollUp(f, f1);
        }
    }

    void processTouchEvent(MotionEvent motionEvent0, int v, MotionLayout motionLayout0) {
        RectF rectF0 = new RectF();
        if(this.mVelocityTracker == null) {
            this.mVelocityTracker = this.mMotionLayout.obtainVelocityTracker();
        }
        this.mVelocityTracker.addMovement(motionEvent0);
        if(v == -1) {
        label_39:
            if(!this.mIgnoreTouch) {
                if(this.mCurrentTransition != null && this.mCurrentTransition.mTouchResponse != null && !this.mMotionOutsideRegion) {
                    this.mCurrentTransition.mTouchResponse.processTouchEvent(motionEvent0, this.mVelocityTracker, v, this);
                }
                this.mLastTouchX = motionEvent0.getRawX();
                this.mLastTouchY = motionEvent0.getRawY();
                if(motionEvent0.getAction() == 1) {
                    MotionTracker motionLayout$MotionTracker0 = this.mVelocityTracker;
                    if(motionLayout$MotionTracker0 != null) {
                        motionLayout$MotionTracker0.recycle();
                        this.mVelocityTracker = null;
                        if(motionLayout0.mCurrentState != -1) {
                            this.autoTransition(motionLayout0, motionLayout0.mCurrentState);
                        }
                    }
                }
            }
        }
        else {
            boolean z = false;
            switch(motionEvent0.getAction()) {
                case 0: {
                    this.mLastTouchX = motionEvent0.getRawX();
                    this.mLastTouchY = motionEvent0.getRawY();
                    this.mLastTouchDown = motionEvent0;
                    this.mIgnoreTouch = false;
                    if(this.mCurrentTransition.mTouchResponse != null) {
                        RectF rectF1 = this.mCurrentTransition.mTouchResponse.getLimitBoundsTo(this.mMotionLayout, rectF0);
                        if(rectF1 != null && !rectF1.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                            this.mLastTouchDown = null;
                            this.mIgnoreTouch = true;
                            return;
                        }
                        RectF rectF2 = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF0);
                        this.mMotionOutsideRegion = rectF2 == null || rectF2.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY()) ? false : true;
                        this.mCurrentTransition.mTouchResponse.setDown(this.mLastTouchX, this.mLastTouchY);
                        return;
                    }
                    break;
                }
                case 2: {
                    if(this.mIgnoreTouch) {
                        goto label_39;
                    }
                    else {
                        float f = motionEvent0.getRawY() - this.mLastTouchY;
                        float f1 = motionEvent0.getRawX() - this.mLastTouchX;
                        if(((double)f1) != 0.0 || ((double)f) != 0.0) {
                            MotionEvent motionEvent1 = this.mLastTouchDown;
                            if(motionEvent1 != null) {
                                Transition motionScene$Transition0 = this.bestTransitionFor(v, f1, f, motionEvent1);
                                if(motionScene$Transition0 != null) {
                                    motionLayout0.setTransition(motionScene$Transition0);
                                    RectF rectF3 = this.mCurrentTransition.mTouchResponse.getTouchRegion(this.mMotionLayout, rectF0);
                                    if(rectF3 != null && !rectF3.contains(this.mLastTouchDown.getX(), this.mLastTouchDown.getY())) {
                                        z = true;
                                    }
                                    this.mMotionOutsideRegion = z;
                                    this.mCurrentTransition.mTouchResponse.setUpTouchEvent(this.mLastTouchX, this.mLastTouchY);
                                }
                                goto label_39;
                            }
                        }
                    }
                    break;
                }
                default: {
                    goto label_39;
                }
            }
        }
    }

    private void readConstraintChain(int v, MotionLayout motionLayout0) {
        ConstraintSet constraintSet0 = (ConstraintSet)this.mConstraintSetMap.get(v);
        constraintSet0.derivedState = constraintSet0.mIdString;
        int v1 = this.mDeriveMap.get(v);
        if(v1 > 0) {
            this.readConstraintChain(v1, motionLayout0);
            ConstraintSet constraintSet1 = (ConstraintSet)this.mConstraintSetMap.get(v1);
            if(constraintSet1 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + Debug.getName(this.mMotionLayout.getContext(), v1));
                return;
            }
            constraintSet0.derivedState = constraintSet0.derivedState + "/" + constraintSet1.derivedState;
            constraintSet0.readFallback(constraintSet1);
        }
        else {
            constraintSet0.derivedState = constraintSet0.derivedState + "  layout";
            constraintSet0.readFallback(motionLayout0);
        }
        constraintSet0.applyDeltaFrom(constraintSet0);
    }

    void readFallback(MotionLayout motionLayout0) {
        for(int v = 0; v < this.mConstraintSetMap.size(); ++v) {
            int v1 = this.mConstraintSetMap.keyAt(v);
            if(this.hasCycleDependency(v1)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            this.readConstraintChain(v1, motionLayout0);
        }
    }

    public void removeTransition(Transition motionScene$Transition0) {
        int v = this.getIndex(motionScene$Transition0);
        if(v != -1) {
            this.mTransitionList.remove(v);
        }
    }

    public void setConstraintSet(int v, ConstraintSet constraintSet0) {
        this.mConstraintSetMap.put(v, constraintSet0);
    }

    public void setDuration(int v) {
        Transition motionScene$Transition0 = this.mCurrentTransition;
        if(motionScene$Transition0 != null) {
            motionScene$Transition0.setDuration(v);
            return;
        }
        this.mDefaultDuration = v;
    }

    public void setKeyframe(View view0, int v, String s, Object object0) {
        Transition motionScene$Transition0 = this.mCurrentTransition;
        if(motionScene$Transition0 != null) {
            for(Object object1: motionScene$Transition0.mKeyFramesList) {
                for(Object object2: ((KeyFrames)object1).getKeyFramesForView(view0.getId())) {
                    if(((Key)object2).mFramePosition == v && object0 != null) {
                        ((Float)object0).floatValue();
                    }
                }
            }
        }
    }

    public void setRtl(boolean z) {
        this.mRtl = z;
        if(this.mCurrentTransition != null && this.mCurrentTransition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
        }
    }

    void setTransition(int v, int v1) {
        int v3;
        int v2;
        StateSet stateSet0 = this.mStateSet;
        if(stateSet0 == null) {
            v2 = v;
            v3 = v1;
        }
        else {
            v2 = stateSet0.stateGetConstraintID(v, -1, -1);
            if(v2 == -1) {
                v2 = v;
            }
            v3 = this.mStateSet.stateGetConstraintID(v1, -1, -1);
            if(v3 == -1) {
                v3 = v1;
            }
        }
        if(this.mCurrentTransition == null || this.mCurrentTransition.mConstraintSetEnd != v1 || this.mCurrentTransition.mConstraintSetStart != v) {
            for(Object object0: this.mTransitionList) {
                Transition motionScene$Transition0 = (Transition)object0;
                if((motionScene$Transition0.mConstraintSetEnd != v3 || motionScene$Transition0.mConstraintSetStart != v2) && (motionScene$Transition0.mConstraintSetEnd != v1 || motionScene$Transition0.mConstraintSetStart != v)) {
                    continue;
                }
                this.mCurrentTransition = motionScene$Transition0;
                if(motionScene$Transition0 != null && motionScene$Transition0.mTouchResponse != null) {
                    this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
                    return;
                }
                return;
            }
            Transition motionScene$Transition1 = this.mDefaultTransition;
            for(Object object1: this.mAbstractTransitionList) {
                Transition motionScene$Transition2 = (Transition)object1;
                if(motionScene$Transition2.mConstraintSetEnd == v1) {
                    motionScene$Transition1 = motionScene$Transition2;
                }
            }
            Transition motionScene$Transition3 = new Transition(this, motionScene$Transition1);
            motionScene$Transition3.mConstraintSetStart = v2;
            motionScene$Transition3.mConstraintSetEnd = v3;
            if(v2 != -1) {
                this.mTransitionList.add(motionScene$Transition3);
            }
            this.mCurrentTransition = motionScene$Transition3;
        }
    }

    public void setTransition(Transition motionScene$Transition0) {
        this.mCurrentTransition = motionScene$Transition0;
        if(motionScene$Transition0 != null && motionScene$Transition0.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setRTL(this.mRtl);
        }
    }

    void setupTouch() {
        if(this.mCurrentTransition != null && this.mCurrentTransition.mTouchResponse != null) {
            this.mCurrentTransition.mTouchResponse.setupTouch();
        }
    }

    public static String stripID(String s) {
        if(s == null) {
            return "";
        }
        int v = s.indexOf(0x2F);
        return v >= 0 ? s.substring(v + 1) : s;
    }

    boolean supportTouch() {
        for(Object object0: this.mTransitionList) {
            if(((Transition)object0).mTouchResponse != null) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return this.mCurrentTransition != null && this.mCurrentTransition.mTouchResponse != null;
    }

    public boolean validateLayout(MotionLayout motionLayout0) {
        return motionLayout0 == this.mMotionLayout && motionLayout0.mScene == this;
    }

    public void viewTransition(int v, View[] arr_view) {
        this.mViewTransitionController.viewTransition(v, arr_view);
    }
}

