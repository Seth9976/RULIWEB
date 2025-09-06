package androidx.appcompat.graphics.drawable;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.Xml;
import androidx.appcompat.resources.Compatibility.Api21Impl;
import androidx.appcompat.resources.R.styleable;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatedStateListDrawableCompat extends StateListDrawableCompat implements TintAwareDrawable {
    static class AnimatableTransition extends Transition {
        private final Animatable mA;

        AnimatableTransition(Animatable animatable0) {
            super(null);
            this.mA = animatable0;
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public void start() {
            this.mA.start();
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public void stop() {
            this.mA.stop();
        }
    }

    static class AnimatedStateListState extends StateListState {
        private static final long REVERSED_BIT = 0x100000000L;
        private static final long REVERSIBLE_FLAG_BIT = 0x200000000L;
        SparseArrayCompat mStateIds;
        LongSparseArray mTransitions;

        AnimatedStateListState(AnimatedStateListState animatedStateListDrawableCompat$AnimatedStateListState0, AnimatedStateListDrawableCompat animatedStateListDrawableCompat0, Resources resources0) {
            super(animatedStateListDrawableCompat$AnimatedStateListState0, animatedStateListDrawableCompat0, resources0);
            if(animatedStateListDrawableCompat$AnimatedStateListState0 != null) {
                this.mTransitions = animatedStateListDrawableCompat$AnimatedStateListState0.mTransitions;
                this.mStateIds = animatedStateListDrawableCompat$AnimatedStateListState0.mStateIds;
                return;
            }
            this.mTransitions = new LongSparseArray();
            this.mStateIds = new SparseArrayCompat();
        }

        int addStateSet(int[] arr_v, Drawable drawable0, int v) {
            int v1 = super.addStateSet(arr_v, drawable0);
            this.mStateIds.put(v1, v);
            return v1;
        }

        int addTransition(int v, int v1, Drawable drawable0, boolean z) {
            int v2 = super.addChild(drawable0);
            this.mTransitions.append(((long)v1) | ((long)v) << 0x20, ((long)(((long)v2) | (z ? 0x200000000L : 0L))));
            if(z) {
                this.mTransitions.append(((long)v) | ((long)v1) << 0x20, ((long)(0x100000000L | ((long)v2) | 0x200000000L)));
            }
            return v2;
        }

        private static long generateTransitionKey(int v, int v1) [...] // Inlined contents

        int getKeyframeIdAt(int v) {
            return v >= 0 ? ((int)(((Integer)this.mStateIds.get(v, 0)))) : 0;
        }

        int indexOfKeyframe(int[] arr_v) {
            int v = super.indexOfStateSet(arr_v);
            return v < 0 ? super.indexOfStateSet(StateSet.WILD_CARD) : v;
        }

        int indexOfTransition(int v, int v1) {
            return (int)(((long)(((Long)this.mTransitions.get(((long)v1) | ((long)v) << 0x20, -1L)))));
        }

        boolean isTransitionReversed(int v, int v1) {
            return (((long)(((Long)this.mTransitions.get(((long)v1) | ((long)v) << 0x20, -1L)))) & 0x100000000L) != 0L;
        }

        @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat$StateListState
        void mutate() {
            this.mTransitions = this.mTransitions.clone();
            this.mStateIds = this.mStateIds.clone();
        }

        @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat$StateListState
        public Drawable newDrawable() {
            return new AnimatedStateListDrawableCompat(this, null);
        }

        @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat$StateListState
        public Drawable newDrawable(Resources resources0) {
            return new AnimatedStateListDrawableCompat(this, resources0);
        }

        boolean transitionHasReversibleFlag(int v, int v1) {
            return (((long)(((Long)this.mTransitions.get(((long)v1) | ((long)v) << 0x20, -1L)))) & 0x200000000L) != 0L;
        }
    }

    static class AnimatedVectorDrawableTransition extends Transition {
        private final AnimatedVectorDrawableCompat mAvd;

        AnimatedVectorDrawableTransition(AnimatedVectorDrawableCompat animatedVectorDrawableCompat0) {
            super(null);
            this.mAvd = animatedVectorDrawableCompat0;
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public void start() {
            this.mAvd.start();
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public void stop() {
            this.mAvd.stop();
        }
    }

    static class AnimationDrawableTransition extends Transition {
        private final ObjectAnimator mAnim;
        private final boolean mHasReversibleFlag;

        AnimationDrawableTransition(AnimationDrawable animationDrawable0, boolean z, boolean z1) {
            super(null);
            int v = animationDrawable0.getNumberOfFrames();
            FrameInterpolator animatedStateListDrawableCompat$FrameInterpolator0 = new FrameInterpolator(animationDrawable0, z);
            ObjectAnimator objectAnimator0 = ObjectAnimator.ofInt(animationDrawable0, "currentIndex", new int[]{(z ? v - 1 : 0), (z ? 0 : v - 1)});
            objectAnimator0.setAutoCancel(true);
            objectAnimator0.setDuration(((long)animatedStateListDrawableCompat$FrameInterpolator0.getTotalDuration()));
            objectAnimator0.setInterpolator(animatedStateListDrawableCompat$FrameInterpolator0);
            this.mHasReversibleFlag = z1;
            this.mAnim = objectAnimator0;
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public boolean canReverse() {
            return this.mHasReversibleFlag;
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public void reverse() {
            this.mAnim.reverse();
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public void start() {
            this.mAnim.start();
        }

        @Override  // androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat$Transition
        public void stop() {
            this.mAnim.cancel();
        }
    }

    static class FrameInterpolator implements TimeInterpolator {
        private int[] mFrameTimes;
        private int mFrames;
        private int mTotalDuration;

        FrameInterpolator(AnimationDrawable animationDrawable0, boolean z) {
            this.updateFrames(animationDrawable0, z);
        }

        @Override  // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            int v = (int)(f * ((float)this.mTotalDuration) + 0.5f);
            int v1 = this.mFrames;
            int[] arr_v = this.mFrameTimes;
            int v2;
            for(v2 = 0; v2 < v1; ++v2) {
                int v3 = arr_v[v2];
                if(v < v3) {
                    break;
                }
                v -= v3;
            }
            return v2 >= v1 ? ((float)v2) / ((float)v1) + 0.0f : ((float)v2) / ((float)v1) + ((float)v) / ((float)this.mTotalDuration);
        }

        int getTotalDuration() {
            return this.mTotalDuration;
        }

        int updateFrames(AnimationDrawable animationDrawable0, boolean z) {
            int v = animationDrawable0.getNumberOfFrames();
            this.mFrames = v;
            if(this.mFrameTimes == null || this.mFrameTimes.length < v) {
                this.mFrameTimes = new int[v];
            }
            int[] arr_v = this.mFrameTimes;
            int v2 = 0;
            for(int v1 = 0; v1 < v; ++v1) {
                int v3 = animationDrawable0.getDuration((z ? v - v1 - 1 : v1));
                arr_v[v1] = v3;
                v2 += v3;
            }
            this.mTotalDuration = v2;
            return v2;
        }
    }

    static abstract class Transition {
        private Transition() {
        }

        Transition(androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.1 animatedStateListDrawableCompat$10) {
        }

        public boolean canReverse() {
            return false;
        }

        public void reverse() {
        }

        public abstract void start();

        public abstract void stop();
    }

    private static final String ELEMENT_ITEM = "item";
    private static final String ELEMENT_TRANSITION = "transition";
    private static final String ITEM_MISSING_DRAWABLE_ERROR = ": <item> tag requires a \'drawable\' attribute or child tag defining a drawable";
    private static final String LOGTAG = "AnimatedStateListDrawableCompat";
    private static final String TRANSITION_MISSING_DRAWABLE_ERROR = ": <transition> tag requires a \'drawable\' attribute or child tag defining a drawable";
    private static final String TRANSITION_MISSING_FROM_TO_ID = ": <transition> tag requires \'fromId\' & \'toId\' attributes";
    private boolean mMutated;
    private AnimatedStateListState mState;
    private Transition mTransition;
    private int mTransitionFromIndex;
    private int mTransitionToIndex;

    static {
    }

    public AnimatedStateListDrawableCompat() {
        this(null, null);
    }

    AnimatedStateListDrawableCompat(AnimatedStateListState animatedStateListDrawableCompat$AnimatedStateListState0, Resources resources0) {
        super(null);
        this.mTransitionToIndex = -1;
        this.mTransitionFromIndex = -1;
        this.setConstantState(new AnimatedStateListState(animatedStateListDrawableCompat$AnimatedStateListState0, this, resources0));
        this.onStateChange(this.getState());
        this.jumpToCurrentState();
    }

    public void addState(int[] arr_v, Drawable drawable0, int v) {
        ObjectsCompat.requireNonNull(drawable0);
        this.mState.addStateSet(arr_v, drawable0, v);
        this.onStateChange(this.getState());
    }

    public void addTransition(int v, int v1, Drawable drawable0, boolean z) {
        ObjectsCompat.requireNonNull(drawable0);
        this.mState.addTransition(v, v1, drawable0, z);
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    AnimatedStateListState cloneConstantState() {
        return new AnimatedStateListState(this.mState, this, null);
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    DrawableContainerState cloneConstantState() {
        return this.cloneConstantState();
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    StateListState cloneConstantState() {
        return this.cloneConstantState();
    }

    public static AnimatedStateListDrawableCompat create(Context context0, int v, Resources.Theme resources$Theme0) {
        try {
            Resources resources0 = context0.getResources();
            XmlResourceParser xmlResourceParser0 = resources0.getXml(v);
            AttributeSet attributeSet0 = Xml.asAttributeSet(xmlResourceParser0);
            do {
                int v1 = xmlResourceParser0.next();
            }
            while(v1 != 1 && v1 != 2);
            if(v1 != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            return AnimatedStateListDrawableCompat.createFromXmlInner(context0, resources0, xmlResourceParser0, attributeSet0, resources$Theme0);
        }
        catch(XmlPullParserException xmlPullParserException0) {
            Log.e("AnimatedStateListDrawableCompat", "parser error", xmlPullParserException0);
            return null;
        }
        catch(IOException iOException0) {
            Log.e("AnimatedStateListDrawableCompat", "parser error", iOException0);
            return null;
        }
    }

    public static AnimatedStateListDrawableCompat createFromXmlInner(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws IOException, XmlPullParserException {
        String s = xmlPullParser0.getName();
        if(!s.equals("animated-selector")) {
            throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": invalid animated-selector tag " + s);
        }
        AnimatedStateListDrawableCompat animatedStateListDrawableCompat0 = new AnimatedStateListDrawableCompat();
        animatedStateListDrawableCompat0.inflate(context0, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        return animatedStateListDrawableCompat0;
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    public void inflate(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.AnimatedStateListDrawableCompat);
        this.setVisible(typedArray0.getBoolean(styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        this.updateStateFromTypedArray(typedArray0);
        this.updateDensity(resources0);
        typedArray0.recycle();
        this.inflateChildElements(context0, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
        this.init();
    }

    private void inflateChildElements(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        int v = xmlPullParser0.getDepth();
        int v1;
        while((v1 = xmlPullParser0.next()) != 1) {
            int v2 = xmlPullParser0.getDepth();
            if(v2 < v + 1 && v1 == 3) {
                break;
            }
            if(v1 != 2 || v2 > v + 1) {
            }
            else if(xmlPullParser0.getName().equals("item")) {
                this.parseItem(context0, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
            }
            else if(xmlPullParser0.getName().equals("transition")) {
                this.parseTransition(context0, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
            }
        }
    }

    private void init() {
        this.onStateChange(this.getState());
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    public boolean isStateful() {
        return true;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        Transition animatedStateListDrawableCompat$Transition0 = this.mTransition;
        if(animatedStateListDrawableCompat$Transition0 != null) {
            animatedStateListDrawableCompat$Transition0.stop();
            this.mTransition = null;
            this.selectDrawable(this.mTransitionToIndex);
            this.mTransitionToIndex = -1;
            this.mTransitionFromIndex = -1;
        }
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    public Drawable mutate() {
        if(!this.mMutated && super.mutate() == this) {
            this.mState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    protected boolean onStateChange(int[] arr_v) {
        int v = this.mState.indexOfKeyframe(arr_v);
        boolean z = v != this.getCurrentIndex() && (this.selectTransition(v) || this.selectDrawable(v));
        Drawable drawable0 = this.getCurrent();
        return drawable0 != null ? drawable0.setState(arr_v) | z : z;
    }

    private int parseItem(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.AnimatedStateListDrawableItem);
        int v = typedArray0.getResourceId(styleable.AnimatedStateListDrawableItem_android_id, 0);
        int v1 = typedArray0.getResourceId(styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable drawable0 = v1 <= 0 ? null : ResourceManagerInternal.get().getDrawable(context0, v1);
        typedArray0.recycle();
        int[] arr_v = this.extractStateSet(attributeSet0);
        if(drawable0 == null) {
        alab1:
            while(true) {
                switch(xmlPullParser0.next()) {
                    case 2: {
                        if(xmlPullParser0.getName().equals("vector")) {
                            drawable0 = VectorDrawableCompat.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
                            break alab1;
                        }
                        drawable0 = Api21Impl.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
                        break alab1;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": <item> tag requires a \'drawable\' attribute or child tag defining a drawable");
                    }
                }
            }
        }
        if(drawable0 == null) {
            throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": <item> tag requires a \'drawable\' attribute or child tag defining a drawable");
        }
        return this.mState.addStateSet(arr_v, drawable0, v);
    }

    private int parseTransition(Context context0, Resources resources0, XmlPullParser xmlPullParser0, AttributeSet attributeSet0, Resources.Theme resources$Theme0) throws XmlPullParserException, IOException {
        TypedArray typedArray0 = TypedArrayUtils.obtainAttributes(resources0, resources$Theme0, attributeSet0, styleable.AnimatedStateListDrawableTransition);
        int v = typedArray0.getResourceId(styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int v1 = typedArray0.getResourceId(styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int v2 = typedArray0.getResourceId(styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable drawable0 = v2 <= 0 ? null : ResourceManagerInternal.get().getDrawable(context0, v2);
        boolean z = typedArray0.getBoolean(styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        typedArray0.recycle();
        if(drawable0 == null) {
        alab1:
            while(true) {
                switch(xmlPullParser0.next()) {
                    case 2: {
                        if(xmlPullParser0.getName().equals("animated-vector")) {
                            drawable0 = AnimatedVectorDrawableCompat.createFromXmlInner(context0, resources0, xmlPullParser0, attributeSet0, resources$Theme0);
                            break alab1;
                        }
                        drawable0 = Api21Impl.createFromXmlInner(resources0, xmlPullParser0, attributeSet0, resources$Theme0);
                        break alab1;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": <transition> tag requires a \'drawable\' attribute or child tag defining a drawable");
                    }
                }
            }
        }
        if(drawable0 == null) {
            throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": <transition> tag requires a \'drawable\' attribute or child tag defining a drawable");
        }
        if(v == -1 || v1 == -1) {
            throw new XmlPullParserException(xmlPullParser0.getPositionDescription() + ": <transition> tag requires \'fromId\' & \'toId\' attributes");
        }
        return this.mState.addTransition(v, v1, drawable0, z);
    }

    private boolean selectTransition(int v) {
        AnimationDrawableTransition animatedStateListDrawableCompat$AnimationDrawableTransition0;
        int v1;
        Transition animatedStateListDrawableCompat$Transition0 = this.mTransition;
        if(animatedStateListDrawableCompat$Transition0 == null) {
            v1 = this.getCurrentIndex();
        }
        else {
            if(v == this.mTransitionToIndex) {
                return true;
            }
            if(v == this.mTransitionFromIndex && animatedStateListDrawableCompat$Transition0.canReverse()) {
                animatedStateListDrawableCompat$Transition0.reverse();
                this.mTransitionToIndex = this.mTransitionFromIndex;
                this.mTransitionFromIndex = v;
                return true;
            }
            v1 = this.mTransitionToIndex;
            animatedStateListDrawableCompat$Transition0.stop();
        }
        this.mTransition = null;
        this.mTransitionFromIndex = -1;
        this.mTransitionToIndex = -1;
        AnimatedStateListState animatedStateListDrawableCompat$AnimatedStateListState0 = this.mState;
        int v2 = animatedStateListDrawableCompat$AnimatedStateListState0.getKeyframeIdAt(v1);
        int v3 = animatedStateListDrawableCompat$AnimatedStateListState0.getKeyframeIdAt(v);
        if(v3 != 0 && v2 != 0) {
            int v4 = animatedStateListDrawableCompat$AnimatedStateListState0.indexOfTransition(v2, v3);
            if(v4 < 0) {
                return false;
            }
            boolean z = animatedStateListDrawableCompat$AnimatedStateListState0.transitionHasReversibleFlag(v2, v3);
            this.selectDrawable(v4);
            Drawable drawable0 = this.getCurrent();
            if(drawable0 instanceof AnimationDrawable) {
                animatedStateListDrawableCompat$AnimationDrawableTransition0 = new AnimationDrawableTransition(((AnimationDrawable)drawable0), animatedStateListDrawableCompat$AnimatedStateListState0.isTransitionReversed(v2, v3), z);
                goto label_34;
            }
            boolean z1 = false;
            if(drawable0 instanceof AnimatedVectorDrawableCompat) {
                z1 = true;
                animatedStateListDrawableCompat$AnimationDrawableTransition0 = new AnimatedVectorDrawableTransition(((AnimatedVectorDrawableCompat)drawable0));
            }
            else if(drawable0 instanceof Animatable) {
                z1 = true;
                animatedStateListDrawableCompat$AnimationDrawableTransition0 = new AnimatableTransition(((Animatable)drawable0));
            }
            if(z1) {
            label_34:
                animatedStateListDrawableCompat$AnimationDrawableTransition0.start();
                this.mTransition = animatedStateListDrawableCompat$AnimationDrawableTransition0;
                this.mTransitionFromIndex = v1;
                this.mTransitionToIndex = v;
                return true;
            }
        }
        return false;
    }

    @Override  // androidx.appcompat.graphics.drawable.StateListDrawableCompat
    void setConstantState(DrawableContainerState drawableContainerCompat$DrawableContainerState0) {
        super.setConstantState(drawableContainerCompat$DrawableContainerState0);
        if(drawableContainerCompat$DrawableContainerState0 instanceof AnimatedStateListState) {
            this.mState = (AnimatedStateListState)drawableContainerCompat$DrawableContainerState0;
        }
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableContainerCompat
    public boolean setVisible(boolean z, boolean z1) {
        boolean z2 = super.setVisible(z, z1);
        Transition animatedStateListDrawableCompat$Transition0 = this.mTransition;
        if(animatedStateListDrawableCompat$Transition0 != null && (z2 || z1)) {
            if(z) {
                animatedStateListDrawableCompat$Transition0.start();
                return z2;
            }
            this.jumpToCurrentState();
        }
        return z2;
    }

    private void updateStateFromTypedArray(TypedArray typedArray0) {
        AnimatedStateListState animatedStateListDrawableCompat$AnimatedStateListState0 = this.mState;
        animatedStateListDrawableCompat$AnimatedStateListState0.mChangingConfigurations |= Api21Impl.getChangingConfigurations(typedArray0);
        animatedStateListDrawableCompat$AnimatedStateListState0.setVariablePadding(typedArray0.getBoolean(styleable.AnimatedStateListDrawableCompat_android_variablePadding, animatedStateListDrawableCompat$AnimatedStateListState0.mVariablePadding));
        animatedStateListDrawableCompat$AnimatedStateListState0.setConstantSize(typedArray0.getBoolean(styleable.AnimatedStateListDrawableCompat_android_constantSize, animatedStateListDrawableCompat$AnimatedStateListState0.mConstantSize));
        animatedStateListDrawableCompat$AnimatedStateListState0.setEnterFadeDuration(typedArray0.getInt(styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, animatedStateListDrawableCompat$AnimatedStateListState0.mEnterFadeDuration));
        animatedStateListDrawableCompat$AnimatedStateListState0.setExitFadeDuration(typedArray0.getInt(styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, animatedStateListDrawableCompat$AnimatedStateListState0.mExitFadeDuration));
        this.setDither(typedArray0.getBoolean(styleable.AnimatedStateListDrawableCompat_android_dither, animatedStateListDrawableCompat$AnimatedStateListState0.mDither));
    }

    class androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat.1 {
    }

}

