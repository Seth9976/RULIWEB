package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public abstract class Transition implements Cloneable {
    static class AnimationInfo {
        Animator mAnimator;
        String mName;
        Transition mTransition;
        TransitionValues mValues;
        View mView;
        WindowId mWindowId;

        AnimationInfo(View view0, String s, Transition transition0, WindowId windowId0, TransitionValues transitionValues0, Animator animator0) {
            this.mView = view0;
            this.mName = s;
            this.mValues = transitionValues0;
            this.mWindowId = windowId0;
            this.mTransition = transition0;
            this.mAnimator = animator0;
        }
    }

    static class ArrayListManager {
        static ArrayList add(ArrayList arrayList0, Object object0) {
            if(arrayList0 == null) {
                arrayList0 = new ArrayList();
            }
            if(!arrayList0.contains(object0)) {
                arrayList0.add(object0);
            }
            return arrayList0;
        }

        static ArrayList remove(ArrayList arrayList0, Object object0) {
            if(arrayList0 != null) {
                arrayList0.remove(object0);
                return arrayList0.isEmpty() ? null : arrayList0;
            }
            return null;
        }
    }

    public static abstract class EpicenterCallback {
        public abstract Rect onGetEpicenter(Transition arg1);
    }

    static class Impl26 {
        static long getTotalDuration(Animator animator0) {
            return animator0.getTotalDuration();
        }

        static void setCurrentPlayTime(Animator animator0, long v) {
            ((AnimatorSet)animator0).setCurrentPlayTime(v);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MatchOrder {
    }

    class SeekController extends TransitionListenerAdapter implements OnAnimationUpdateListener, TransitionSeekController {
        private long mCurrentPlayTime;
        private boolean mIsCanceled;
        private boolean mIsReady;
        private Consumer[] mListenerCache;
        private ArrayList mOnProgressListeners;
        private ArrayList mOnReadyListeners;
        private Runnable mResetToStartState;
        private SpringAnimation mSpringAnimation;
        private final VelocityTracker1D mVelocityTracker;

        SeekController() {
            this.mCurrentPlayTime = -1L;
            this.mOnReadyListeners = null;
            this.mOnProgressListeners = null;
            this.mListenerCache = null;
            this.mVelocityTracker = new VelocityTracker1D();
        }

        @Override  // androidx.transition.TransitionSeekController
        public void addOnProgressChangedListener(Consumer consumer0) {
            if(this.mOnProgressListeners == null) {
                this.mOnProgressListeners = new ArrayList();
            }
            this.mOnProgressListeners.add(consumer0);
        }

        @Override  // androidx.transition.TransitionSeekController
        public void addOnReadyListener(Consumer consumer0) {
            if(this.isReady()) {
                consumer0.accept(this);
                return;
            }
            if(this.mOnReadyListeners == null) {
                this.mOnReadyListeners = new ArrayList();
            }
            this.mOnReadyListeners.add(consumer0);
        }

        @Override  // androidx.transition.TransitionSeekController
        public void animateToEnd() {
            this.ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition(((float)(this.getDurationMillis() + 1L)));
        }

        @Override  // androidx.transition.TransitionSeekController
        public void animateToStart(Runnable runnable0) {
            this.mResetToStartState = runnable0;
            this.ensureAnimation();
            this.mSpringAnimation.animateToFinalPosition(0.0f);
        }

        private void callProgressListeners() {
            if(this.mOnProgressListeners != null && !this.mOnProgressListeners.isEmpty()) {
                int v = this.mOnProgressListeners.size();
                if(this.mListenerCache == null) {
                    this.mListenerCache = new Consumer[v];
                }
                Consumer[] arr_consumer = (Consumer[])this.mOnProgressListeners.toArray(this.mListenerCache);
                this.mListenerCache = null;
                for(int v1 = 0; v1 < v; ++v1) {
                    arr_consumer[v1].accept(this);
                    arr_consumer[v1] = null;
                }
                this.mListenerCache = arr_consumer;
            }
        }

        private void ensureAnimation() {
            if(this.mSpringAnimation != null) {
                return;
            }
            long v = AnimationUtils.currentAnimationTimeMillis();
            this.mVelocityTracker.addDataPoint(v, ((float)this.mCurrentPlayTime));
            this.mSpringAnimation = new SpringAnimation(new FloatValueHolder());
            SpringForce springForce0 = new SpringForce();
            springForce0.setDampingRatio(1.0f);
            springForce0.setStiffness(200.0f);
            this.mSpringAnimation.setSpring(springForce0);
            this.mSpringAnimation.setStartValue(((float)this.mCurrentPlayTime));
            this.mSpringAnimation.addUpdateListener(this);
            this.mSpringAnimation.setStartVelocity(this.mVelocityTracker.calculateVelocity());
            this.mSpringAnimation.setMaxValue(((float)(this.getDurationMillis() + 1L)));
            this.mSpringAnimation.setMinValue(-1.0f);
            this.mSpringAnimation.setMinimumVisibleChange(4.0f);
            this.mSpringAnimation.addEndListener((DynamicAnimation dynamicAnimation0, boolean z, float f, float f1) -> if(!z) {
                if(f < 1.0f) {
                    long v = this.getDurationMillis();
                    Transition transition0 = ((TransitionSet)Transition.this).getTransitionAt(0);
                    Transition transition1 = transition0.mCloneParent;
                    transition0.mCloneParent = null;
                    Transition.this.setCurrentPlayTimeMillis(-1L, this.mCurrentPlayTime);
                    Transition.this.setCurrentPlayTimeMillis(v, -1L);
                    this.mCurrentPlayTime = v;
                    Runnable runnable0 = this.mResetToStartState;
                    if(runnable0 != null) {
                        runnable0.run();
                    }
                    Transition.this.mAnimators.clear();
                    if(transition1 != null) {
                        transition1.notifyListeners(TransitionNotification.ON_END, true);
                    }
                }
                else {
                    Transition.this.notifyListeners(TransitionNotification.ON_END, false);
                }
            });
        }

        @Override  // androidx.transition.TransitionSeekController
        public float getCurrentFraction() {
            return ((float)this.getCurrentPlayTimeMillis()) / ((float)this.getDurationMillis());
        }

        @Override  // androidx.transition.TransitionSeekController
        public long getCurrentPlayTimeMillis() {
            return Math.min(this.getDurationMillis(), Math.max(0L, this.mCurrentPlayTime));
        }

        @Override  // androidx.transition.TransitionSeekController
        public long getDurationMillis() {
            return Transition.this.getTotalDurationMillis();
        }

        void initPlayTime() {
            long v = this.getDurationMillis() == 0L ? 1L : 0L;
            Transition.this.setCurrentPlayTimeMillis(v, this.mCurrentPlayTime);
            this.mCurrentPlayTime = v;
        }

        @Override  // androidx.transition.TransitionSeekController
        public boolean isReady() {
            return this.mIsReady;
        }

        // 检测为 Lambda 实现
        void lambda$ensureAnimation$0$androidx-transition-Transition$SeekController(DynamicAnimation dynamicAnimation0, boolean z, float f, float f1) [...]

        @Override  // androidx.dynamicanimation.animation.DynamicAnimation$OnAnimationUpdateListener
        public void onAnimationUpdate(DynamicAnimation dynamicAnimation0, float f, float f1) {
            long v = Math.max(-1L, Math.min(this.getDurationMillis() + 1L, Math.round(f)));
            Transition.this.setCurrentPlayTimeMillis(v, this.mCurrentPlayTime);
            this.mCurrentPlayTime = v;
            this.callProgressListeners();
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionCancel(Transition transition0) {
            this.mIsCanceled = true;
        }

        public void ready() {
            this.mIsReady = true;
            ArrayList arrayList0 = this.mOnReadyListeners;
            if(arrayList0 != null) {
                this.mOnReadyListeners = null;
                for(int v = 0; v < arrayList0.size(); ++v) {
                    ((Consumer)arrayList0.get(v)).accept(this);
                }
            }
            this.callProgressListeners();
        }

        @Override  // androidx.transition.TransitionSeekController
        public void removeOnProgressChangedListener(Consumer consumer0) {
            ArrayList arrayList0 = this.mOnProgressListeners;
            if(arrayList0 != null) {
                arrayList0.remove(consumer0);
            }
        }

        @Override  // androidx.transition.TransitionSeekController
        public void removeOnReadyListener(Consumer consumer0) {
            ArrayList arrayList0 = this.mOnReadyListeners;
            if(arrayList0 != null) {
                arrayList0.remove(consumer0);
                if(this.mOnReadyListeners.isEmpty()) {
                    this.mOnReadyListeners = null;
                }
            }
        }

        @Override  // androidx.transition.TransitionSeekController
        public void setCurrentFraction(float f) {
            if(this.mSpringAnimation != null) {
                throw new IllegalStateException("setCurrentFraction() called after animation has been started");
            }
            this.setCurrentPlayTimeMillis(((long)(f * ((float)this.getDurationMillis()))));
        }

        @Override  // androidx.transition.TransitionSeekController
        public void setCurrentPlayTimeMillis(long v) {
            if(this.mSpringAnimation != null) {
                throw new IllegalStateException("setCurrentPlayTimeMillis() called after animation has been started");
            }
            if(v != this.mCurrentPlayTime && this.isReady()) {
                if(!this.mIsCanceled) {
                    if(v != 0L || this.mCurrentPlayTime <= 0L) {
                        long v1 = this.getDurationMillis();
                        if(v == v1 && this.mCurrentPlayTime < v1) {
                            v = v1 + 1L;
                        }
                    }
                    else {
                        v = -1L;
                    }
                    long v2 = this.mCurrentPlayTime;
                    if(v != v2) {
                        Transition.this.setCurrentPlayTimeMillis(v, v2);
                        this.mCurrentPlayTime = v;
                    }
                }
                this.callProgressListeners();
                long v3 = AnimationUtils.currentAnimationTimeMillis();
                this.mVelocityTracker.addDataPoint(v3, ((float)v));
            }
        }
    }

    public interface TransitionListener {
        void onTransitionCancel(Transition arg1);

        void onTransitionEnd(Transition arg1);

        void onTransitionEnd(Transition arg1, boolean arg2);

        void onTransitionPause(Transition arg1);

        void onTransitionResume(Transition arg1);

        void onTransitionStart(Transition arg1);

        void onTransitionStart(Transition arg1, boolean arg2);
    }

    interface TransitionNotification {
        public static final TransitionNotification ON_CANCEL;
        public static final TransitionNotification ON_END;
        public static final TransitionNotification ON_PAUSE;
        public static final TransitionNotification ON_RESUME;
        public static final TransitionNotification ON_START;

        static {
            TransitionNotification.ON_START = new Transition.TransitionNotification..ExternalSyntheticLambda0();
            TransitionNotification.ON_END = new Transition.TransitionNotification..ExternalSyntheticLambda1();
            TransitionNotification.ON_CANCEL = new Transition.TransitionNotification..ExternalSyntheticLambda2();
            TransitionNotification.ON_PAUSE = new Transition.TransitionNotification..ExternalSyntheticLambda3();
            TransitionNotification.ON_RESUME = new Transition.TransitionNotification..ExternalSyntheticLambda4();
        }

        void notifyListener(TransitionListener arg1, Transition arg2, boolean arg3);
    }

    static final boolean DBG = false;
    private static final int[] DEFAULT_MATCH_ORDER = null;
    private static final Animator[] EMPTY_ANIMATOR_ARRAY = null;
    private static final String LOG_TAG = "Transition";
    private static final int MATCH_FIRST = 1;
    public static final int MATCH_ID = 3;
    private static final String MATCH_ID_STR = "id";
    public static final int MATCH_INSTANCE = 1;
    private static final String MATCH_INSTANCE_STR = "instance";
    public static final int MATCH_ITEM_ID = 4;
    private static final String MATCH_ITEM_ID_STR = "itemId";
    private static final int MATCH_LAST = 4;
    public static final int MATCH_NAME = 2;
    private static final String MATCH_NAME_STR = "name";
    private static final PathMotion STRAIGHT_PATH_MOTION;
    private Animator[] mAnimatorCache;
    ArrayList mAnimators;
    boolean mCanRemoveViews;
    private Transition mCloneParent;
    ArrayList mCurrentAnimators;
    long mDuration;
    private TransitionValuesMaps mEndValues;
    private ArrayList mEndValuesList;
    boolean mEnded;
    private EpicenterCallback mEpicenterCallback;
    private TimeInterpolator mInterpolator;
    private ArrayList mListeners;
    private TransitionListener[] mListenersCache;
    private int[] mMatchOrder;
    private String mName;
    private ArrayMap mNameOverrides;
    int mNumInstances;
    TransitionSet mParent;
    private PathMotion mPathMotion;
    private boolean mPaused;
    TransitionPropagation mPropagation;
    SeekController mSeekController;
    long mSeekOffsetInParent;
    private long mStartDelay;
    private TransitionValuesMaps mStartValues;
    private ArrayList mStartValuesList;
    private ArrayList mTargetChildExcludes;
    private ArrayList mTargetExcludes;
    private ArrayList mTargetIdChildExcludes;
    private ArrayList mTargetIdExcludes;
    ArrayList mTargetIds;
    private ArrayList mTargetNameExcludes;
    private ArrayList mTargetNames;
    private ArrayList mTargetTypeChildExcludes;
    private ArrayList mTargetTypeExcludes;
    private ArrayList mTargetTypes;
    ArrayList mTargets;
    long mTotalDuration;
    private static ThreadLocal sRunningAnimators;

    static {
        Transition.EMPTY_ANIMATOR_ARRAY = new Animator[0];
        Transition.DEFAULT_MATCH_ORDER = new int[]{2, 1, 3, 4};
        Transition.STRAIGHT_PATH_MOTION = new PathMotion() {
            @Override  // androidx.transition.PathMotion
            public Path getPath(float f, float f1, float f2, float f3) {
                Path path0 = new Path();
                path0.moveTo(f, f1);
                path0.lineTo(f2, f3);
                return path0;
            }
        };
        Transition.sRunningAnimators = new ThreadLocal();
    }

    public Transition() {
        this.mName = this.getClass().getName();
        this.mStartDelay = -1L;
        this.mDuration = -1L;
        this.mInterpolator = null;
        this.mTargetIds = new ArrayList();
        this.mTargets = new ArrayList();
        this.mTargetNames = null;
        this.mTargetTypes = null;
        this.mTargetIdExcludes = null;
        this.mTargetExcludes = null;
        this.mTargetTypeExcludes = null;
        this.mTargetNameExcludes = null;
        this.mTargetIdChildExcludes = null;
        this.mTargetChildExcludes = null;
        this.mTargetTypeChildExcludes = null;
        this.mStartValues = new TransitionValuesMaps();
        this.mEndValues = new TransitionValuesMaps();
        this.mParent = null;
        this.mMatchOrder = Transition.DEFAULT_MATCH_ORDER;
        this.mCanRemoveViews = false;
        this.mCurrentAnimators = new ArrayList();
        this.mAnimatorCache = Transition.EMPTY_ANIMATOR_ARRAY;
        this.mNumInstances = 0;
        this.mPaused = false;
        this.mEnded = false;
        this.mCloneParent = null;
        this.mListeners = null;
        this.mAnimators = new ArrayList();
        this.mPathMotion = Transition.STRAIGHT_PATH_MOTION;
    }

    public Transition(Context context0, AttributeSet attributeSet0) {
        this.mName = this.getClass().getName();
        this.mStartDelay = -1L;
        this.mDuration = -1L;
        this.mInterpolator = null;
        this.mTargetIds = new ArrayList();
        this.mTargets = new ArrayList();
        this.mTargetNames = null;
        this.mTargetTypes = null;
        this.mTargetIdExcludes = null;
        this.mTargetExcludes = null;
        this.mTargetTypeExcludes = null;
        this.mTargetNameExcludes = null;
        this.mTargetIdChildExcludes = null;
        this.mTargetChildExcludes = null;
        this.mTargetTypeChildExcludes = null;
        this.mStartValues = new TransitionValuesMaps();
        this.mEndValues = new TransitionValuesMaps();
        this.mParent = null;
        this.mMatchOrder = Transition.DEFAULT_MATCH_ORDER;
        this.mCanRemoveViews = false;
        this.mCurrentAnimators = new ArrayList();
        this.mAnimatorCache = Transition.EMPTY_ANIMATOR_ARRAY;
        this.mNumInstances = 0;
        this.mPaused = false;
        this.mEnded = false;
        this.mCloneParent = null;
        this.mListeners = null;
        this.mAnimators = new ArrayList();
        this.mPathMotion = Transition.STRAIGHT_PATH_MOTION;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.TRANSITION);
        long v = (long)TypedArrayUtils.getNamedInt(typedArray0, ((XmlResourceParser)attributeSet0), "duration", 1, -1);
        if(v >= 0L) {
            this.setDuration(v);
        }
        long v1 = (long)TypedArrayUtils.getNamedInt(typedArray0, ((XmlResourceParser)attributeSet0), "startDelay", 2, -1);
        if(v1 > 0L) {
            this.setStartDelay(v1);
        }
        int v2 = TypedArrayUtils.getNamedResourceId(typedArray0, ((XmlResourceParser)attributeSet0), "interpolator", 0, 0);
        if(v2 > 0) {
            this.setInterpolator(AnimationUtils.loadInterpolator(context0, v2));
        }
        String s = TypedArrayUtils.getNamedString(typedArray0, ((XmlResourceParser)attributeSet0), "matchOrder", 3);
        if(s != null) {
            this.setMatchOrder(Transition.parseMatchOrder(s));
        }
        typedArray0.recycle();
    }

    public Transition addListener(TransitionListener transition$TransitionListener0) {
        if(this.mListeners == null) {
            this.mListeners = new ArrayList();
        }
        this.mListeners.add(transition$TransitionListener0);
        return this;
    }

    public Transition addTarget(int v) {
        if(v != 0) {
            this.mTargetIds.add(v);
        }
        return this;
    }

    public Transition addTarget(View view0) {
        this.mTargets.add(view0);
        return this;
    }

    public Transition addTarget(Class class0) {
        if(this.mTargetTypes == null) {
            this.mTargetTypes = new ArrayList();
        }
        this.mTargetTypes.add(class0);
        return this;
    }

    public Transition addTarget(String s) {
        if(this.mTargetNames == null) {
            this.mTargetNames = new ArrayList();
        }
        this.mTargetNames.add(s);
        return this;
    }

    private void addUnmatched(ArrayMap arrayMap0, ArrayMap arrayMap1) {
        for(int v1 = 0; v1 < arrayMap0.size(); ++v1) {
            TransitionValues transitionValues0 = (TransitionValues)arrayMap0.valueAt(v1);
            if(this.isValidTarget(transitionValues0.view)) {
                this.mStartValuesList.add(transitionValues0);
                this.mEndValuesList.add(null);
            }
        }
        for(int v = 0; v < arrayMap1.size(); ++v) {
            TransitionValues transitionValues1 = (TransitionValues)arrayMap1.valueAt(v);
            if(this.isValidTarget(transitionValues1.view)) {
                this.mEndValuesList.add(transitionValues1);
                this.mStartValuesList.add(null);
            }
        }
    }

    private static void addViewValues(TransitionValuesMaps transitionValuesMaps0, View view0, TransitionValues transitionValues0) {
        transitionValuesMaps0.mViewValues.put(view0, transitionValues0);
        int v = view0.getId();
        if(v >= 0) {
            if(transitionValuesMaps0.mIdValues.indexOfKey(v) >= 0) {
                transitionValuesMaps0.mIdValues.put(v, null);
            }
            else {
                transitionValuesMaps0.mIdValues.put(v, view0);
            }
        }
        String s = ViewCompat.getTransitionName(view0);
        if(s != null) {
            if(transitionValuesMaps0.mNameValues.containsKey(s)) {
                transitionValuesMaps0.mNameValues.put(s, null);
            }
            else {
                transitionValuesMaps0.mNameValues.put(s, view0);
            }
        }
        if(view0.getParent() instanceof ListView) {
            ListView listView0 = (ListView)view0.getParent();
            if(listView0.getAdapter().hasStableIds()) {
                long v1 = listView0.getItemIdAtPosition(listView0.getPositionForView(view0));
                if(transitionValuesMaps0.mItemIdValues.indexOfKey(v1) >= 0) {
                    View view1 = (View)transitionValuesMaps0.mItemIdValues.get(v1);
                    if(view1 != null) {
                        view1.setHasTransientState(false);
                        transitionValuesMaps0.mItemIdValues.put(v1, null);
                    }
                }
                else {
                    view0.setHasTransientState(true);
                    transitionValuesMaps0.mItemIdValues.put(v1, view0);
                }
            }
        }
    }

    private static boolean alreadyContains(int[] arr_v, int v) {
        int v1 = arr_v[v];
        for(int v2 = 0; v2 < v; ++v2) {
            if(arr_v[v2] == v1) {
                return true;
            }
        }
        return false;
    }

    protected void animate(Animator animator0) {
        if(animator0 == null) {
            this.end();
            return;
        }
        if(this.getDuration() >= 0L) {
            animator0.setDuration(this.getDuration());
        }
        if(this.getStartDelay() >= 0L) {
            animator0.setStartDelay(this.getStartDelay() + animator0.getStartDelay());
        }
        if(this.getInterpolator() != null) {
            animator0.setInterpolator(this.getInterpolator());
        }
        animator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                Transition.this.end();
                animator0.removeListener(this);
            }
        });
        animator0.start();
    }

    protected void cancel() {
        int v = this.mCurrentAnimators.size();
        Animator[] arr_animator = (Animator[])this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = Transition.EMPTY_ANIMATOR_ARRAY;
        for(int v1 = v - 1; v1 >= 0; --v1) {
            Animator animator0 = arr_animator[v1];
            arr_animator[v1] = null;
            animator0.cancel();
        }
        this.mAnimatorCache = arr_animator;
        this.notifyListeners(TransitionNotification.ON_CANCEL, false);
    }

    public abstract void captureEndValues(TransitionValues arg1);

    private void captureHierarchy(View view0, boolean z) {
        if(view0 != null) {
            int v = view0.getId();
            if((this.mTargetIdExcludes == null || !this.mTargetIdExcludes.contains(v)) && (this.mTargetExcludes == null || !this.mTargetExcludes.contains(view0))) {
                ArrayList arrayList0 = this.mTargetTypeExcludes;
                if(arrayList0 != null) {
                    int v2 = arrayList0.size();
                    for(int v3 = 0; v3 < v2; ++v3) {
                        if(((Class)this.mTargetTypeExcludes.get(v3)).isInstance(view0)) {
                            return;
                        }
                    }
                }
                if(view0.getParent() instanceof ViewGroup) {
                    TransitionValues transitionValues0 = new TransitionValues(view0);
                    if(z) {
                        this.captureStartValues(transitionValues0);
                    }
                    else {
                        this.captureEndValues(transitionValues0);
                    }
                    transitionValues0.mTargetedTransitions.add(this);
                    this.capturePropagationValues(transitionValues0);
                    if(z) {
                        Transition.addViewValues(this.mStartValues, view0, transitionValues0);
                    }
                    else {
                        Transition.addViewValues(this.mEndValues, view0, transitionValues0);
                    }
                }
                if(view0 instanceof ViewGroup && (this.mTargetIdChildExcludes == null || !this.mTargetIdChildExcludes.contains(v)) && (this.mTargetChildExcludes == null || !this.mTargetChildExcludes.contains(view0))) {
                    ArrayList arrayList1 = this.mTargetTypeChildExcludes;
                    if(arrayList1 != null) {
                        int v4 = arrayList1.size();
                        for(int v5 = 0; v5 < v4; ++v5) {
                            if(((Class)this.mTargetTypeChildExcludes.get(v5)).isInstance(view0)) {
                                return;
                            }
                        }
                    }
                    for(int v1 = 0; v1 < ((ViewGroup)view0).getChildCount(); ++v1) {
                        this.captureHierarchy(((ViewGroup)view0).getChildAt(v1), z);
                    }
                }
            }
        }
    }

    void capturePropagationValues(TransitionValues transitionValues0) {
        if(this.mPropagation != null && !transitionValues0.values.isEmpty()) {
            String[] arr_s = this.mPropagation.getPropagationProperties();
            if(arr_s != null) {
                for(int v = 0; v < arr_s.length; ++v) {
                    if(!transitionValues0.values.containsKey(arr_s[v])) {
                        this.mPropagation.captureValues(transitionValues0);
                        return;
                    }
                }
            }
        }
    }

    public abstract void captureStartValues(TransitionValues arg1);

    void captureValues(ViewGroup viewGroup0, boolean z) {
        this.clearValues(z);
        if((this.mTargetIds.size() > 0 || this.mTargets.size() > 0) && (this.mTargetNames == null || this.mTargetNames.isEmpty()) && (this.mTargetTypes == null || this.mTargetTypes.isEmpty())) {
            for(int v1 = 0; v1 < this.mTargetIds.size(); ++v1) {
                View view0 = viewGroup0.findViewById(((int)(((Integer)this.mTargetIds.get(v1)))));
                if(view0 != null) {
                    TransitionValues transitionValues0 = new TransitionValues(view0);
                    if(z) {
                        this.captureStartValues(transitionValues0);
                    }
                    else {
                        this.captureEndValues(transitionValues0);
                    }
                    transitionValues0.mTargetedTransitions.add(this);
                    this.capturePropagationValues(transitionValues0);
                    if(z) {
                        Transition.addViewValues(this.mStartValues, view0, transitionValues0);
                    }
                    else {
                        Transition.addViewValues(this.mEndValues, view0, transitionValues0);
                    }
                }
            }
            for(int v2 = 0; v2 < this.mTargets.size(); ++v2) {
                View view1 = (View)this.mTargets.get(v2);
                TransitionValues transitionValues1 = new TransitionValues(view1);
                if(z) {
                    this.captureStartValues(transitionValues1);
                }
                else {
                    this.captureEndValues(transitionValues1);
                }
                transitionValues1.mTargetedTransitions.add(this);
                this.capturePropagationValues(transitionValues1);
                if(z) {
                    Transition.addViewValues(this.mStartValues, view1, transitionValues1);
                }
                else {
                    Transition.addViewValues(this.mEndValues, view1, transitionValues1);
                }
            }
        }
        else {
            this.captureHierarchy(viewGroup0, z);
        }
        if(!z) {
            ArrayMap arrayMap0 = this.mNameOverrides;
            if(arrayMap0 != null) {
                int v3 = arrayMap0.size();
                ArrayList arrayList0 = new ArrayList(v3);
                for(int v4 = 0; v4 < v3; ++v4) {
                    String s = (String)this.mNameOverrides.keyAt(v4);
                    arrayList0.add(((View)this.mStartValues.mNameValues.remove(s)));
                }
                for(int v = 0; v < v3; ++v) {
                    View view2 = (View)arrayList0.get(v);
                    if(view2 != null) {
                        String s1 = (String)this.mNameOverrides.valueAt(v);
                        this.mStartValues.mNameValues.put(s1, view2);
                    }
                }
            }
        }
    }

    void clearValues(boolean z) {
        if(z) {
            this.mStartValues.mViewValues.clear();
            this.mStartValues.mIdValues.clear();
            this.mStartValues.mItemIdValues.clear();
            return;
        }
        this.mEndValues.mViewValues.clear();
        this.mEndValues.mIdValues.clear();
        this.mEndValues.mItemIdValues.clear();
    }

    public Transition clone() {
        try {
            Transition transition0 = (Transition)super.clone();
            transition0.mAnimators = new ArrayList();
            transition0.mStartValues = new TransitionValuesMaps();
            transition0.mEndValues = new TransitionValuesMaps();
            transition0.mStartValuesList = null;
            transition0.mEndValuesList = null;
            transition0.mSeekController = null;
            transition0.mCloneParent = this;
            transition0.mListeners = null;
            return transition0;
        }
        catch(CloneNotSupportedException cloneNotSupportedException0) {
            throw new RuntimeException(cloneNotSupportedException0);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return null;
    }

    void createAnimators(ViewGroup viewGroup0, TransitionValuesMaps transitionValuesMaps0, TransitionValuesMaps transitionValuesMaps1, ArrayList arrayList0, ArrayList arrayList1) {
        Animator animator3;
        View view2;
        Animator animator2;
        Animator animator1;
        View view1;
        int v4;
        TransitionValues transitionValues2;
        ArrayMap arrayMap0 = Transition.getRunningAnimators();
        SparseIntArray sparseIntArray0 = new SparseIntArray();
        int v = arrayList0.size();
        int v1 = this.getRootTransition().mSeekController == null ? 0 : 1;
        long v2 = 0x7FFFFFFFFFFFFFFFL;
        int v3 = 0;
        while(v3 < v) {
            TransitionValues transitionValues0 = (TransitionValues)arrayList0.get(v3);
            TransitionValues transitionValues1 = (TransitionValues)arrayList1.get(v3);
            if(transitionValues0 != null && !transitionValues0.mTargetedTransitions.contains(this)) {
                transitionValues0 = null;
            }
            if(transitionValues1 != null && !transitionValues1.mTargetedTransitions.contains(this)) {
                transitionValues1 = null;
            }
            if(transitionValues0 == null && transitionValues1 == null) {
                v4 = v3;
            }
            else if(transitionValues0 == null || transitionValues1 == null || this.isTransitionRequired(transitionValues0, transitionValues1)) {
                Animator animator0 = this.createAnimator(viewGroup0, transitionValues0, transitionValues1);
                if(animator0 == null) {
                    v4 = v3;
                }
                else {
                    if(transitionValues1 == null) {
                        animator2 = animator0;
                        v4 = v3;
                        view2 = transitionValues0.view;
                        transitionValues2 = null;
                    }
                    else {
                        View view0 = transitionValues1.view;
                        String[] arr_s = this.getTransitionProperties();
                        if(arr_s == null || arr_s.length <= 0) {
                            view1 = view0;
                            v4 = v3;
                            animator1 = animator0;
                            transitionValues2 = null;
                        }
                        else {
                            transitionValues2 = new TransitionValues(view0);
                            TransitionValues transitionValues3 = (TransitionValues)transitionValuesMaps1.mViewValues.get(view0);
                            v4 = v3;
                            if(transitionValues3 != null) {
                                for(int v5 = 0; v5 < arr_s.length; ++v5) {
                                    String s = arr_s[v5];
                                    Object object0 = transitionValues3.values.get(arr_s[v5]);
                                    transitionValues2.values.put(s, object0);
                                }
                            }
                            int v6 = arrayMap0.size();
                            int v7 = 0;
                            while(v7 < v6) {
                                AnimationInfo transition$AnimationInfo0 = (AnimationInfo)arrayMap0.get(((Animator)arrayMap0.keyAt(v7)));
                                if(transition$AnimationInfo0.mValues == null || transition$AnimationInfo0.mView != view0) {
                                    view1 = view0;
                                }
                                else {
                                    view1 = view0;
                                    if(transition$AnimationInfo0.mName.equals("androidx.transition.Transition") && transition$AnimationInfo0.mValues.equals(transitionValues2)) {
                                        animator1 = null;
                                        goto label_52;
                                    }
                                }
                                ++v7;
                                view0 = view1;
                            }
                            view1 = view0;
                            animator1 = animator0;
                        }
                    label_52:
                        animator2 = animator1;
                        view2 = view1;
                    }
                    if(animator2 != null) {
                        TransitionPropagation transitionPropagation0 = this.mPropagation;
                        if(transitionPropagation0 != null) {
                            long v8 = transitionPropagation0.getStartDelay(viewGroup0, this, transitionValues0, transitionValues1);
                            sparseIntArray0.put(this.mAnimators.size(), ((int)v8));
                            v2 = Math.min(v8, v2);
                        }
                        AnimationInfo transition$AnimationInfo1 = new AnimationInfo(view2, "androidx.transition.Transition", this, viewGroup0.getWindowId(), transitionValues2, animator2);
                        if(v1 == 0) {
                            animator3 = animator2;
                        }
                        else {
                            animator3 = new AnimatorSet();
                            ((AnimatorSet)animator3).play(animator2);
                        }
                        arrayMap0.put(animator3, transition$AnimationInfo1);
                        this.mAnimators.add(animator3);
                    }
                }
            }
            else {
                v4 = v3;
            }
            v3 = v4 + 1;
        }
        if(sparseIntArray0.size() != 0) {
            for(int v9 = 0; v9 < sparseIntArray0.size(); ++v9) {
                int v10 = sparseIntArray0.keyAt(v9);
                AnimationInfo transition$AnimationInfo2 = (AnimationInfo)arrayMap0.get(((Animator)this.mAnimators.get(v10)));
                long v11 = ((long)sparseIntArray0.valueAt(v9)) - v2;
                long v12 = transition$AnimationInfo2.mAnimator.getStartDelay();
                transition$AnimationInfo2.mAnimator.setStartDelay(v11 + v12);
            }
        }
    }

    TransitionSeekController createSeekController() {
        SeekController transition$SeekController0 = new SeekController(this);
        this.mSeekController = transition$SeekController0;
        this.addListener(transition$SeekController0);
        return this.mSeekController;
    }

    protected void end() {
        int v = this.mNumInstances - 1;
        this.mNumInstances = v;
        if(v == 0) {
            this.notifyListeners(TransitionNotification.ON_END, false);
            for(int v1 = 0; v1 < this.mStartValues.mItemIdValues.size(); ++v1) {
                View view0 = (View)this.mStartValues.mItemIdValues.valueAt(v1);
                if(view0 != null) {
                    view0.setHasTransientState(false);
                }
            }
            for(int v2 = 0; v2 < this.mEndValues.mItemIdValues.size(); ++v2) {
                View view1 = (View)this.mEndValues.mItemIdValues.valueAt(v2);
                if(view1 != null) {
                    view1.setHasTransientState(false);
                }
            }
            this.mEnded = true;
        }
    }

    public Transition excludeChildren(int v, boolean z) {
        this.mTargetIdChildExcludes = this.excludeId(this.mTargetIdChildExcludes, v, z);
        return this;
    }

    public Transition excludeChildren(View view0, boolean z) {
        this.mTargetChildExcludes = this.excludeView(this.mTargetChildExcludes, view0, z);
        return this;
    }

    public Transition excludeChildren(Class class0, boolean z) {
        this.mTargetTypeChildExcludes = this.excludeType(this.mTargetTypeChildExcludes, class0, z);
        return this;
    }

    private ArrayList excludeId(ArrayList arrayList0, int v, boolean z) {
        if(v > 0) {
            return z ? ArrayListManager.add(arrayList0, v) : ArrayListManager.remove(arrayList0, v);
        }
        return arrayList0;
    }

    private static ArrayList excludeObject(ArrayList arrayList0, Object object0, boolean z) {
        if(object0 != null) {
            return z ? ArrayListManager.add(arrayList0, object0) : ArrayListManager.remove(arrayList0, object0);
        }
        return arrayList0;
    }

    public Transition excludeTarget(int v, boolean z) {
        this.mTargetIdExcludes = this.excludeId(this.mTargetIdExcludes, v, z);
        return this;
    }

    public Transition excludeTarget(View view0, boolean z) {
        this.mTargetExcludes = this.excludeView(this.mTargetExcludes, view0, z);
        return this;
    }

    public Transition excludeTarget(Class class0, boolean z) {
        this.mTargetTypeExcludes = this.excludeType(this.mTargetTypeExcludes, class0, z);
        return this;
    }

    public Transition excludeTarget(String s, boolean z) {
        this.mTargetNameExcludes = Transition.excludeObject(this.mTargetNameExcludes, s, z);
        return this;
    }

    private ArrayList excludeType(ArrayList arrayList0, Class class0, boolean z) {
        if(class0 != null) {
            return z ? ArrayListManager.add(arrayList0, class0) : ArrayListManager.remove(arrayList0, class0);
        }
        return arrayList0;
    }

    private ArrayList excludeView(ArrayList arrayList0, View view0, boolean z) {
        if(view0 != null) {
            return z ? ArrayListManager.add(arrayList0, view0) : ArrayListManager.remove(arrayList0, view0);
        }
        return arrayList0;
    }

    void forceToEnd(ViewGroup viewGroup0) {
        ArrayMap arrayMap0 = Transition.getRunningAnimators();
        int v = arrayMap0.size();
        if(viewGroup0 != null && v != 0) {
            WindowId windowId0 = viewGroup0.getWindowId();
            ArrayMap arrayMap1 = new ArrayMap(arrayMap0);
            arrayMap0.clear();
            for(int v1 = v - 1; v1 >= 0; --v1) {
                AnimationInfo transition$AnimationInfo0 = (AnimationInfo)arrayMap1.valueAt(v1);
                if(transition$AnimationInfo0.mView != null && windowId0.equals(transition$AnimationInfo0.mWindowId)) {
                    ((Animator)arrayMap1.keyAt(v1)).end();
                }
            }
        }
    }

    public long getDuration() {
        return this.mDuration;
    }

    public Rect getEpicenter() {
        return this.mEpicenterCallback == null ? null : this.mEpicenterCallback.onGetEpicenter(this);
    }

    public EpicenterCallback getEpicenterCallback() {
        return this.mEpicenterCallback;
    }

    public TimeInterpolator getInterpolator() {
        return this.mInterpolator;
    }

    TransitionValues getMatchedTransitionValues(View view0, boolean z) {
        TransitionSet transitionSet0 = this.mParent;
        if(transitionSet0 != null) {
            return transitionSet0.getMatchedTransitionValues(view0, z);
        }
        ArrayList arrayList0 = z ? this.mStartValuesList : this.mEndValuesList;
        if(arrayList0 == null) {
            return null;
        }
        int v = arrayList0.size();
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 >= v) {
                v1 = -1;
                break;
            }
            TransitionValues transitionValues0 = (TransitionValues)arrayList0.get(v1);
            if(transitionValues0 == null) {
                return null;
            }
            if(transitionValues0.view == view0) {
                break;
            }
        }
        if(v1 >= 0) {
            return z ? ((TransitionValues)this.mEndValuesList.get(v1)) : ((TransitionValues)this.mStartValuesList.get(v1));
        }
        return null;
    }

    public String getName() [...] // 潜在的解密器

    public PathMotion getPathMotion() {
        return this.mPathMotion;
    }

    public TransitionPropagation getPropagation() {
        return this.mPropagation;
    }

    public final Transition getRootTransition() {
        return this.mParent == null ? this : this.mParent.getRootTransition();
    }

    private static ArrayMap getRunningAnimators() {
        ArrayMap arrayMap0 = (ArrayMap)Transition.sRunningAnimators.get();
        if(arrayMap0 == null) {
            arrayMap0 = new ArrayMap();
            Transition.sRunningAnimators.set(arrayMap0);
        }
        return arrayMap0;
    }

    public long getStartDelay() {
        return this.mStartDelay;
    }

    public List getTargetIds() {
        return this.mTargetIds;
    }

    public List getTargetNames() {
        return this.mTargetNames;
    }

    public List getTargetTypes() {
        return this.mTargetTypes;
    }

    public List getTargets() {
        return this.mTargets;
    }

    final long getTotalDurationMillis() {
        return this.mTotalDuration;
    }

    public String[] getTransitionProperties() {
        return null;
    }

    public TransitionValues getTransitionValues(View view0, boolean z) {
        TransitionSet transitionSet0 = this.mParent;
        if(transitionSet0 != null) {
            return transitionSet0.getTransitionValues(view0, z);
        }
        return z ? ((TransitionValues)this.mStartValues.mViewValues.get(view0)) : ((TransitionValues)this.mEndValues.mViewValues.get(view0));
    }

    boolean hasAnimators() {
        return !this.mCurrentAnimators.isEmpty();
    }

    public boolean isSeekingSupported() {
        return false;
    }

    public boolean isTransitionRequired(TransitionValues transitionValues0, TransitionValues transitionValues1) {
        if(transitionValues0 != null && transitionValues1 != null) {
            String[] arr_s = this.getTransitionProperties();
            if(arr_s != null) {
                for(int v = 0; v < arr_s.length; ++v) {
                    if(Transition.isValueChanged(transitionValues0, transitionValues1, arr_s[v])) {
                        return true;
                    }
                }
                return false;
            }
            for(Object object0: transitionValues0.values.keySet()) {
                if(Transition.isValueChanged(transitionValues0, transitionValues1, ((String)object0))) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
        }
        return false;
    }

    private static boolean isValidMatch(int v) {
        return v >= 1 && v <= 4;
    }

    boolean isValidTarget(View view0) {
        int v = view0.getId();
        if(this.mTargetIdExcludes != null && this.mTargetIdExcludes.contains(v)) {
            return false;
        }
        if(this.mTargetExcludes != null && this.mTargetExcludes.contains(view0)) {
            return false;
        }
        ArrayList arrayList0 = this.mTargetTypeExcludes;
        if(arrayList0 != null) {
            int v1 = arrayList0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                if(((Class)this.mTargetTypeExcludes.get(v2)).isInstance(view0)) {
                    return false;
                }
            }
        }
        if(this.mTargetNameExcludes != null && ViewCompat.getTransitionName(view0) != null && this.mTargetNameExcludes.contains(ViewCompat.getTransitionName(view0))) {
            return false;
        }
        if(this.mTargetIds.size() == 0 && this.mTargets.size() == 0 && (this.mTargetTypes == null || this.mTargetTypes.isEmpty()) && (this.mTargetNames == null || this.mTargetNames.isEmpty())) {
            return true;
        }
        if(this.mTargetIds.contains(v) || this.mTargets.contains(view0) || this.mTargetNames != null && this.mTargetNames.contains(ViewCompat.getTransitionName(view0))) {
            return true;
        }
        if(this.mTargetTypes != null) {
            for(int v3 = 0; v3 < this.mTargetTypes.size(); ++v3) {
                if(((Class)this.mTargetTypes.get(v3)).isInstance(view0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValueChanged(TransitionValues transitionValues0, TransitionValues transitionValues1, String s) {
        Object object0 = transitionValues0.values.get(s);
        Object object1 = transitionValues1.values.get(s);
        if(object0 == null && object1 == null) {
            return false;
        }
        return object0 == null || object1 == null ? true : !object0.equals(object1);
    }

    private void matchIds(ArrayMap arrayMap0, ArrayMap arrayMap1, SparseArray sparseArray0, SparseArray sparseArray1) {
        int v = sparseArray0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = (View)sparseArray0.valueAt(v1);
            if(view0 != null && this.isValidTarget(view0)) {
                View view1 = (View)sparseArray1.get(sparseArray0.keyAt(v1));
                if(view1 != null && this.isValidTarget(view1)) {
                    TransitionValues transitionValues0 = (TransitionValues)arrayMap0.get(view0);
                    TransitionValues transitionValues1 = (TransitionValues)arrayMap1.get(view1);
                    if(transitionValues0 != null && transitionValues1 != null) {
                        this.mStartValuesList.add(transitionValues0);
                        this.mEndValuesList.add(transitionValues1);
                        arrayMap0.remove(view0);
                        arrayMap1.remove(view1);
                    }
                }
            }
        }
    }

    private void matchInstances(ArrayMap arrayMap0, ArrayMap arrayMap1) {
        for(int v = arrayMap0.size() - 1; v >= 0; --v) {
            View view0 = (View)arrayMap0.keyAt(v);
            if(view0 != null && this.isValidTarget(view0)) {
                TransitionValues transitionValues0 = (TransitionValues)arrayMap1.remove(view0);
                if(transitionValues0 != null && this.isValidTarget(transitionValues0.view)) {
                    TransitionValues transitionValues1 = (TransitionValues)arrayMap0.removeAt(v);
                    this.mStartValuesList.add(transitionValues1);
                    this.mEndValuesList.add(transitionValues0);
                }
            }
        }
    }

    private void matchItemIds(ArrayMap arrayMap0, ArrayMap arrayMap1, LongSparseArray longSparseArray0, LongSparseArray longSparseArray1) {
        int v = longSparseArray0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = (View)longSparseArray0.valueAt(v1);
            if(view0 != null && this.isValidTarget(view0)) {
                View view1 = (View)longSparseArray1.get(longSparseArray0.keyAt(v1));
                if(view1 != null && this.isValidTarget(view1)) {
                    TransitionValues transitionValues0 = (TransitionValues)arrayMap0.get(view0);
                    TransitionValues transitionValues1 = (TransitionValues)arrayMap1.get(view1);
                    if(transitionValues0 != null && transitionValues1 != null) {
                        this.mStartValuesList.add(transitionValues0);
                        this.mEndValuesList.add(transitionValues1);
                        arrayMap0.remove(view0);
                        arrayMap1.remove(view1);
                    }
                }
            }
        }
    }

    private void matchNames(ArrayMap arrayMap0, ArrayMap arrayMap1, ArrayMap arrayMap2, ArrayMap arrayMap3) {
        int v = arrayMap2.size();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = (View)arrayMap2.valueAt(v1);
            if(view0 != null && this.isValidTarget(view0)) {
                View view1 = (View)arrayMap3.get(arrayMap2.keyAt(v1));
                if(view1 != null && this.isValidTarget(view1)) {
                    TransitionValues transitionValues0 = (TransitionValues)arrayMap0.get(view0);
                    TransitionValues transitionValues1 = (TransitionValues)arrayMap1.get(view1);
                    if(transitionValues0 != null && transitionValues1 != null) {
                        this.mStartValuesList.add(transitionValues0);
                        this.mEndValuesList.add(transitionValues1);
                        arrayMap0.remove(view0);
                        arrayMap1.remove(view1);
                    }
                }
            }
        }
    }

    private void matchStartAndEnd(TransitionValuesMaps transitionValuesMaps0, TransitionValuesMaps transitionValuesMaps1) {
        ArrayMap arrayMap0 = new ArrayMap(transitionValuesMaps0.mViewValues);
        ArrayMap arrayMap1 = new ArrayMap(transitionValuesMaps1.mViewValues);
        for(int v = 0; true; ++v) {
            int[] arr_v = this.mMatchOrder;
            if(v >= arr_v.length) {
                break;
            }
            switch(arr_v[v]) {
                case 1: {
                    this.matchInstances(arrayMap0, arrayMap1);
                    break;
                }
                case 2: {
                    this.matchNames(arrayMap0, arrayMap1, transitionValuesMaps0.mNameValues, transitionValuesMaps1.mNameValues);
                    break;
                }
                case 3: {
                    this.matchIds(arrayMap0, arrayMap1, transitionValuesMaps0.mIdValues, transitionValuesMaps1.mIdValues);
                    break;
                }
                case 4: {
                    this.matchItemIds(arrayMap0, arrayMap1, transitionValuesMaps0.mItemIdValues, transitionValuesMaps1.mItemIdValues);
                }
            }
        }
        this.addUnmatched(arrayMap0, arrayMap1);
    }

    private void notifyFromTransition(Transition transition0, TransitionNotification transition$TransitionNotification0, boolean z) {
        Transition transition1 = this.mCloneParent;
        if(transition1 != null) {
            transition1.notifyFromTransition(transition0, transition$TransitionNotification0, z);
        }
        if(this.mListeners != null && !this.mListeners.isEmpty()) {
            int v = this.mListeners.size();
            TransitionListener[] arr_transition$TransitionListener = this.mListenersCache == null ? new TransitionListener[v] : this.mListenersCache;
            this.mListenersCache = null;
            TransitionListener[] arr_transition$TransitionListener1 = (TransitionListener[])this.mListeners.toArray(arr_transition$TransitionListener);
            for(int v1 = 0; v1 < v; ++v1) {
                transition$TransitionNotification0.notifyListener(arr_transition$TransitionListener1[v1], transition0, z);
                arr_transition$TransitionListener1[v1] = null;
            }
            this.mListenersCache = arr_transition$TransitionListener1;
        }
    }

    void notifyListeners(TransitionNotification transition$TransitionNotification0, boolean z) {
        this.notifyFromTransition(this, transition$TransitionNotification0, z);
    }

    private static int[] parseMatchOrder(String s) {
        StringTokenizer stringTokenizer0 = new StringTokenizer(s, ",");
        int[] arr_v = new int[stringTokenizer0.countTokens()];
        for(int v = 0; stringTokenizer0.hasMoreTokens(); ++v) {
            String s1 = stringTokenizer0.nextToken().trim();
            if("id".equalsIgnoreCase(s1)) {
                arr_v[v] = 3;
            }
            else if("instance".equalsIgnoreCase(s1)) {
                arr_v[v] = 1;
            }
            else if("name".equalsIgnoreCase(s1)) {
                arr_v[v] = 2;
            }
            else if("itemId".equalsIgnoreCase(s1)) {
                arr_v[v] = 4;
            }
            else {
                if(!s1.isEmpty()) {
                    throw new InflateException("Unknown match type in matchOrder: \'" + s1 + "\'");
                }
                int[] arr_v1 = new int[arr_v.length - 1];
                System.arraycopy(arr_v, 0, arr_v1, 0, v);
                --v;
                arr_v = arr_v1;
            }
        }
        return arr_v;
    }

    public void pause(View view0) {
        if(!this.mEnded) {
            int v = this.mCurrentAnimators.size();
            Animator[] arr_animator = (Animator[])this.mCurrentAnimators.toArray(this.mAnimatorCache);
            this.mAnimatorCache = Transition.EMPTY_ANIMATOR_ARRAY;
            for(int v1 = v - 1; v1 >= 0; --v1) {
                Animator animator0 = arr_animator[v1];
                arr_animator[v1] = null;
                animator0.pause();
            }
            this.mAnimatorCache = arr_animator;
            this.notifyListeners(TransitionNotification.ON_PAUSE, false);
            this.mPaused = true;
        }
    }

    void playTransition(ViewGroup viewGroup0) {
        this.mStartValuesList = new ArrayList();
        this.mEndValuesList = new ArrayList();
        this.matchStartAndEnd(this.mStartValues, this.mEndValues);
        ArrayMap arrayMap0 = Transition.getRunningAnimators();
        WindowId windowId0 = viewGroup0.getWindowId();
        for(int v = arrayMap0.size() - 1; v >= 0; --v) {
            Animator animator0 = (Animator)arrayMap0.keyAt(v);
            if(animator0 != null) {
                AnimationInfo transition$AnimationInfo0 = (AnimationInfo)arrayMap0.get(animator0);
                if(transition$AnimationInfo0 != null && transition$AnimationInfo0.mView != null && windowId0.equals(transition$AnimationInfo0.mWindowId)) {
                    TransitionValues transitionValues0 = transition$AnimationInfo0.mValues;
                    View view0 = transition$AnimationInfo0.mView;
                    TransitionValues transitionValues1 = this.getTransitionValues(view0, true);
                    TransitionValues transitionValues2 = this.getMatchedTransitionValues(view0, true);
                    if(transitionValues1 == null && transitionValues2 == null) {
                        transitionValues2 = (TransitionValues)this.mEndValues.mViewValues.get(view0);
                    }
                    if((transitionValues1 != null || transitionValues2 != null) && transition$AnimationInfo0.mTransition.isTransitionRequired(transitionValues0, transitionValues2)) {
                        Transition transition0 = transition$AnimationInfo0.mTransition;
                        if(transition0.getRootTransition().mSeekController != null) {
                            animator0.cancel();
                            transition0.mCurrentAnimators.remove(animator0);
                            arrayMap0.remove(animator0);
                            if(transition0.mCurrentAnimators.size() == 0) {
                                transition0.notifyListeners(TransitionNotification.ON_CANCEL, false);
                                if(!transition0.mEnded) {
                                    transition0.mEnded = true;
                                    transition0.notifyListeners(TransitionNotification.ON_END, false);
                                }
                            }
                        }
                        else if(animator0.isRunning() || animator0.isStarted()) {
                            animator0.cancel();
                        }
                        else {
                            arrayMap0.remove(animator0);
                        }
                    }
                }
            }
        }
        this.createAnimators(viewGroup0, this.mStartValues, this.mEndValues, this.mStartValuesList, this.mEndValuesList);
        if(this.mSeekController == null) {
            this.runAnimators();
            return;
        }
        if(Build.VERSION.SDK_INT >= 34) {
            this.prepareAnimatorsForSeeking();
            this.mSeekController.initPlayTime();
            this.mSeekController.ready();
        }
    }

    void prepareAnimatorsForSeeking() {
        ArrayMap arrayMap0 = Transition.getRunningAnimators();
        this.mTotalDuration = 0L;
        for(int v = 0; v < this.mAnimators.size(); ++v) {
            Animator animator0 = (Animator)this.mAnimators.get(v);
            AnimationInfo transition$AnimationInfo0 = (AnimationInfo)arrayMap0.get(animator0);
            if(animator0 != null && transition$AnimationInfo0 != null) {
                if(this.getDuration() >= 0L) {
                    transition$AnimationInfo0.mAnimator.setDuration(this.getDuration());
                }
                if(this.getStartDelay() >= 0L) {
                    transition$AnimationInfo0.mAnimator.setStartDelay(this.getStartDelay() + transition$AnimationInfo0.mAnimator.getStartDelay());
                }
                if(this.getInterpolator() != null) {
                    transition$AnimationInfo0.mAnimator.setInterpolator(this.getInterpolator());
                }
                this.mCurrentAnimators.add(animator0);
                this.mTotalDuration = Math.max(this.mTotalDuration, Impl26.getTotalDuration(animator0));
            }
        }
        this.mAnimators.clear();
    }

    public Transition removeListener(TransitionListener transition$TransitionListener0) {
        ArrayList arrayList0 = this.mListeners;
        if(arrayList0 != null) {
            if(!arrayList0.remove(transition$TransitionListener0)) {
                Transition transition0 = this.mCloneParent;
                if(transition0 != null) {
                    transition0.removeListener(transition$TransitionListener0);
                }
            }
            if(this.mListeners.size() == 0) {
                this.mListeners = null;
            }
        }
        return this;
    }

    public Transition removeTarget(int v) {
        if(v != 0) {
            this.mTargetIds.remove(Integer.valueOf(v));
        }
        return this;
    }

    public Transition removeTarget(View view0) {
        this.mTargets.remove(view0);
        return this;
    }

    public Transition removeTarget(Class class0) {
        ArrayList arrayList0 = this.mTargetTypes;
        if(arrayList0 != null) {
            arrayList0.remove(class0);
        }
        return this;
    }

    public Transition removeTarget(String s) {
        ArrayList arrayList0 = this.mTargetNames;
        if(arrayList0 != null) {
            arrayList0.remove(s);
        }
        return this;
    }

    public void resume(View view0) {
        if(this.mPaused) {
            if(!this.mEnded) {
                int v = this.mCurrentAnimators.size();
                Animator[] arr_animator = (Animator[])this.mCurrentAnimators.toArray(this.mAnimatorCache);
                this.mAnimatorCache = Transition.EMPTY_ANIMATOR_ARRAY;
                for(int v1 = v - 1; v1 >= 0; --v1) {
                    Animator animator0 = arr_animator[v1];
                    arr_animator[v1] = null;
                    animator0.resume();
                }
                this.mAnimatorCache = arr_animator;
                this.notifyListeners(TransitionNotification.ON_RESUME, false);
            }
            this.mPaused = false;
        }
    }

    private void runAnimator(Animator animator0, ArrayMap arrayMap0) {
        if(animator0 != null) {
            animator0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    arrayMap0.remove(animator0);
                    Transition.this.mCurrentAnimators.remove(animator0);
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                    Transition.this.mCurrentAnimators.add(animator0);
                }
            });
            this.animate(animator0);
        }
    }

    protected void runAnimators() {
        this.start();
        ArrayMap arrayMap0 = Transition.getRunningAnimators();
        for(Object object0: this.mAnimators) {
            Animator animator0 = (Animator)object0;
            if(arrayMap0.containsKey(animator0)) {
                this.start();
                this.runAnimator(animator0, arrayMap0);
            }
        }
        this.mAnimators.clear();
        this.end();
    }

    void setCanRemoveViews(boolean z) {
        this.mCanRemoveViews = z;
    }

    void setCurrentPlayTimeMillis(long v, long v1) {
        long v2 = this.getTotalDurationMillis();
        boolean z = v < v1;
        int v4 = Long.compare(v1, 0L);
        if(v4 < 0 && v >= 0L || v1 > v2 && v <= v2) {
            this.mEnded = false;
            this.notifyListeners(TransitionNotification.ON_START, z);
        }
        int v5 = this.mCurrentAnimators.size();
        Animator[] arr_animator = (Animator[])this.mCurrentAnimators.toArray(this.mAnimatorCache);
        this.mAnimatorCache = Transition.EMPTY_ANIMATOR_ARRAY;
        for(int v3 = 0; v3 < v5; ++v3) {
            Animator animator0 = arr_animator[v3];
            arr_animator[v3] = null;
            Impl26.setCurrentPlayTime(animator0, Math.min(Math.max(0L, v), Impl26.getTotalDuration(animator0)));
        }
        this.mAnimatorCache = arr_animator;
        int v6 = Long.compare(v, v2);
        if(v6 > 0 && v1 <= v2 || v < 0L && v4 >= 0) {
            if(v6 > 0) {
                this.mEnded = true;
            }
            this.notifyListeners(TransitionNotification.ON_END, z);
        }
    }

    public Transition setDuration(long v) {
        this.mDuration = v;
        return this;
    }

    public void setEpicenterCallback(EpicenterCallback transition$EpicenterCallback0) {
        this.mEpicenterCallback = transition$EpicenterCallback0;
    }

    public Transition setInterpolator(TimeInterpolator timeInterpolator0) {
        this.mInterpolator = timeInterpolator0;
        return this;
    }

    public void setMatchOrder(int[] arr_v) {
        if(arr_v != null && arr_v.length != 0) {
            for(int v = 0; v < arr_v.length; ++v) {
                if(!Transition.isValidMatch(arr_v[v])) {
                    throw new IllegalArgumentException("matches contains invalid value");
                }
                if(Transition.alreadyContains(arr_v, v)) {
                    throw new IllegalArgumentException("matches contains a duplicate value");
                }
            }
            this.mMatchOrder = (int[])arr_v.clone();
            return;
        }
        this.mMatchOrder = Transition.DEFAULT_MATCH_ORDER;
    }

    public void setPathMotion(PathMotion pathMotion0) {
        if(pathMotion0 == null) {
            this.mPathMotion = Transition.STRAIGHT_PATH_MOTION;
            return;
        }
        this.mPathMotion = pathMotion0;
    }

    public void setPropagation(TransitionPropagation transitionPropagation0) {
        this.mPropagation = transitionPropagation0;
    }

    public Transition setStartDelay(long v) {
        this.mStartDelay = v;
        return this;
    }

    protected void start() {
        if(this.mNumInstances == 0) {
            this.notifyListeners(TransitionNotification.ON_START, false);
            this.mEnded = false;
        }
        ++this.mNumInstances;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return "Transition@4db3e577: ";
    }

    String toString(String s) [...] // 潜在的解密器
}

