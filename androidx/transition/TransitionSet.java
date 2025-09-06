package androidx.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import java.util.ArrayList;

public class TransitionSet extends Transition {
    static class TransitionSetListener extends TransitionListenerAdapter {
        TransitionSet mTransitionSet;

        TransitionSetListener(TransitionSet transitionSet0) {
            this.mTransitionSet = transitionSet0;
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionEnd(Transition transition0) {
            --this.mTransitionSet.mCurrentListeners;
            if(this.mTransitionSet.mCurrentListeners == 0) {
                this.mTransitionSet.mStarted = false;
                this.mTransitionSet.end();
            }
            transition0.removeListener(this);
        }

        @Override  // androidx.transition.TransitionListenerAdapter
        public void onTransitionStart(Transition transition0) {
            if(!this.mTransitionSet.mStarted) {
                this.mTransitionSet.start();
                this.mTransitionSet.mStarted = true;
            }
        }
    }

    private static final int FLAG_CHANGE_EPICENTER = 8;
    private static final int FLAG_CHANGE_INTERPOLATOR = 1;
    private static final int FLAG_CHANGE_PATH_MOTION = 4;
    private static final int FLAG_CHANGE_PROPAGATION = 2;
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER;
    private int mChangeFlags;
    int mCurrentListeners;
    private boolean mPlayTogether;
    boolean mStarted;
    ArrayList mTransitions;

    public TransitionSet() {
        this.mTransitions = new ArrayList();
        this.mPlayTogether = true;
        this.mStarted = false;
        this.mChangeFlags = 0;
    }

    public TransitionSet(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mTransitions = new ArrayList();
        this.mPlayTogether = true;
        this.mStarted = false;
        this.mChangeFlags = 0;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.TRANSITION_SET);
        this.setOrdering(TypedArrayUtils.getNamedInt(typedArray0, ((XmlResourceParser)attributeSet0), "transitionOrdering", 0, 0));
        typedArray0.recycle();
    }

    @Override  // androidx.transition.Transition
    public Transition addListener(TransitionListener transition$TransitionListener0) {
        return this.addListener(transition$TransitionListener0);
    }

    public TransitionSet addListener(TransitionListener transition$TransitionListener0) {
        return (TransitionSet)super.addListener(transition$TransitionListener0);
    }

    @Override  // androidx.transition.Transition
    public Transition addTarget(int v) {
        return this.addTarget(v);
    }

    @Override  // androidx.transition.Transition
    public Transition addTarget(View view0) {
        return this.addTarget(view0);
    }

    @Override  // androidx.transition.Transition
    public Transition addTarget(Class class0) {
        return this.addTarget(class0);
    }

    @Override  // androidx.transition.Transition
    public Transition addTarget(String s) {
        return this.addTarget(s);
    }

    public TransitionSet addTarget(int v) {
        for(int v1 = 0; v1 < this.mTransitions.size(); ++v1) {
            ((Transition)this.mTransitions.get(v1)).addTarget(v);
        }
        return (TransitionSet)super.addTarget(v);
    }

    public TransitionSet addTarget(View view0) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).addTarget(view0);
        }
        return (TransitionSet)super.addTarget(view0);
    }

    public TransitionSet addTarget(Class class0) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).addTarget(class0);
        }
        return (TransitionSet)super.addTarget(class0);
    }

    public TransitionSet addTarget(String s) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).addTarget(s);
        }
        return (TransitionSet)super.addTarget(s);
    }

    public TransitionSet addTransition(Transition transition0) {
        this.addTransitionInternal(transition0);
        if(this.mDuration >= 0L) {
            transition0.setDuration(this.mDuration);
        }
        if((this.mChangeFlags & 1) != 0) {
            transition0.setInterpolator(this.getInterpolator());
        }
        if((this.mChangeFlags & 2) != 0) {
            transition0.setPropagation(this.getPropagation());
        }
        if((this.mChangeFlags & 4) != 0) {
            transition0.setPathMotion(this.getPathMotion());
        }
        if((this.mChangeFlags & 8) != 0) {
            transition0.setEpicenterCallback(this.getEpicenterCallback());
        }
        return this;
    }

    private void addTransitionInternal(Transition transition0) {
        this.mTransitions.add(transition0);
        transition0.mParent = this;
    }

    @Override  // androidx.transition.Transition
    protected void cancel() {
        super.cancel();
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).cancel();
        }
    }

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        if(this.isValidTarget(transitionValues0.view)) {
            for(Object object0: this.mTransitions) {
                Transition transition0 = (Transition)object0;
                if(transition0.isValidTarget(transitionValues0.view)) {
                    transition0.captureEndValues(transitionValues0);
                    transitionValues0.mTargetedTransitions.add(transition0);
                }
            }
        }
    }

    @Override  // androidx.transition.Transition
    void capturePropagationValues(TransitionValues transitionValues0) {
        super.capturePropagationValues(transitionValues0);
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).capturePropagationValues(transitionValues0);
        }
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        if(this.isValidTarget(transitionValues0.view)) {
            for(Object object0: this.mTransitions) {
                Transition transition0 = (Transition)object0;
                if(transition0.isValidTarget(transitionValues0.view)) {
                    transition0.captureStartValues(transitionValues0);
                    transitionValues0.mTargetedTransitions.add(transition0);
                }
            }
        }
    }

    @Override  // androidx.transition.Transition
    public Transition clone() {
        Transition transition0 = (TransitionSet)super.clone();
        transition0.mTransitions = new ArrayList();
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((TransitionSet)transition0).addTransitionInternal(((Transition)this.mTransitions.get(v1)).clone());
        }
        return transition0;
    }

    @Override  // androidx.transition.Transition
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.transition.Transition
    void createAnimators(ViewGroup viewGroup0, TransitionValuesMaps transitionValuesMaps0, TransitionValuesMaps transitionValuesMaps1, ArrayList arrayList0, ArrayList arrayList1) {
        long v = this.getStartDelay();
        int v1 = this.mTransitions.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            Transition transition0 = (Transition)this.mTransitions.get(v2);
            if(v > 0L && (this.mPlayTogether || v2 == 0)) {
                long v3 = transition0.getStartDelay();
                if(v3 > 0L) {
                    transition0.setStartDelay(v3 + v);
                }
                else {
                    transition0.setStartDelay(v);
                }
            }
            transition0.createAnimators(viewGroup0, transitionValuesMaps0, transitionValuesMaps1, arrayList0, arrayList1);
        }
    }

    @Override  // androidx.transition.Transition
    public Transition excludeTarget(int v, boolean z) {
        for(int v1 = 0; v1 < this.mTransitions.size(); ++v1) {
            ((Transition)this.mTransitions.get(v1)).excludeTarget(v, z);
        }
        return super.excludeTarget(v, z);
    }

    @Override  // androidx.transition.Transition
    public Transition excludeTarget(View view0, boolean z) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).excludeTarget(view0, z);
        }
        return super.excludeTarget(view0, z);
    }

    @Override  // androidx.transition.Transition
    public Transition excludeTarget(Class class0, boolean z) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).excludeTarget(class0, z);
        }
        return super.excludeTarget(class0, z);
    }

    @Override  // androidx.transition.Transition
    public Transition excludeTarget(String s, boolean z) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).excludeTarget(s, z);
        }
        return super.excludeTarget(s, z);
    }

    @Override  // androidx.transition.Transition
    void forceToEnd(ViewGroup viewGroup0) {
        super.forceToEnd(viewGroup0);
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).forceToEnd(viewGroup0);
        }
    }

    public int getOrdering() {
        return !this.mPlayTogether;
    }

    public Transition getTransitionAt(int v) {
        return v < 0 || v >= this.mTransitions.size() ? null : ((Transition)this.mTransitions.get(v));
    }

    public int getTransitionCount() {
        return this.mTransitions.size();
    }

    @Override  // androidx.transition.Transition
    boolean hasAnimators() {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            if(((Transition)this.mTransitions.get(v)).hasAnimators()) {
                return true;
            }
        }
        return false;
    }

    private int indexOfTransition(long v) {
        for(int v1 = 1; v1 < this.mTransitions.size(); ++v1) {
            if(((Transition)this.mTransitions.get(v1)).mSeekOffsetInParent > v) {
                return v1 - 1;
            }
        }
        return this.mTransitions.size() - 1;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(!((Transition)this.mTransitions.get(v1)).isSeekingSupported()) {
                return false;
            }
        }
        return true;
    }

    @Override  // androidx.transition.Transition
    public void pause(View view0) {
        super.pause(view0);
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).pause(view0);
        }
    }

    @Override  // androidx.transition.Transition
    void prepareAnimatorsForSeeking() {
        this.mTotalDuration = 0L;
        androidx.transition.TransitionSet.2 transitionSet$20 = new TransitionListenerAdapter() {
            @Override  // androidx.transition.TransitionListenerAdapter
            public void onTransitionCancel(Transition transition0) {
                TransitionSet.this.mTransitions.remove(transition0);
                if(!TransitionSet.this.hasAnimators()) {
                    TransitionSet.this.notifyListeners(TransitionNotification.ON_CANCEL, false);
                    TransitionSet.this.mEnded = true;
                    TransitionSet.this.notifyListeners(TransitionNotification.ON_END, false);
                }
            }
        };
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            Transition transition0 = (Transition)this.mTransitions.get(v);
            transition0.addListener(transitionSet$20);
            transition0.prepareAnimatorsForSeeking();
            long v1 = transition0.getTotalDurationMillis();
            if(this.mPlayTogether) {
                this.mTotalDuration = Math.max(this.mTotalDuration, v1);
            }
            else {
                transition0.mSeekOffsetInParent = this.mTotalDuration;
                this.mTotalDuration += v1;
            }
        }
    }

    @Override  // androidx.transition.Transition
    public Transition removeListener(TransitionListener transition$TransitionListener0) {
        return this.removeListener(transition$TransitionListener0);
    }

    public TransitionSet removeListener(TransitionListener transition$TransitionListener0) {
        return (TransitionSet)super.removeListener(transition$TransitionListener0);
    }

    @Override  // androidx.transition.Transition
    public Transition removeTarget(int v) {
        return this.removeTarget(v);
    }

    @Override  // androidx.transition.Transition
    public Transition removeTarget(View view0) {
        return this.removeTarget(view0);
    }

    @Override  // androidx.transition.Transition
    public Transition removeTarget(Class class0) {
        return this.removeTarget(class0);
    }

    @Override  // androidx.transition.Transition
    public Transition removeTarget(String s) {
        return this.removeTarget(s);
    }

    public TransitionSet removeTarget(int v) {
        for(int v1 = 0; v1 < this.mTransitions.size(); ++v1) {
            ((Transition)this.mTransitions.get(v1)).removeTarget(v);
        }
        return (TransitionSet)super.removeTarget(v);
    }

    public TransitionSet removeTarget(View view0) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).removeTarget(view0);
        }
        return (TransitionSet)super.removeTarget(view0);
    }

    public TransitionSet removeTarget(Class class0) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).removeTarget(class0);
        }
        return (TransitionSet)super.removeTarget(class0);
    }

    public TransitionSet removeTarget(String s) {
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            ((Transition)this.mTransitions.get(v)).removeTarget(s);
        }
        return (TransitionSet)super.removeTarget(s);
    }

    public TransitionSet removeTransition(Transition transition0) {
        this.mTransitions.remove(transition0);
        transition0.mParent = null;
        return this;
    }

    @Override  // androidx.transition.Transition
    public void resume(View view0) {
        super.resume(view0);
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).resume(view0);
        }
    }

    @Override  // androidx.transition.Transition
    protected void runAnimators() {
        if(this.mTransitions.isEmpty()) {
            this.start();
            this.end();
            return;
        }
        this.setupStartEndListeners();
        if(this.mPlayTogether) {
            for(Object object0: this.mTransitions) {
                ((Transition)object0).runAnimators();
            }
        }
        else {
            for(int v = 1; v < this.mTransitions.size(); ++v) {
                ((Transition)this.mTransitions.get(v - 1)).addListener(new TransitionListenerAdapter() {
                    @Override  // androidx.transition.TransitionListenerAdapter
                    public void onTransitionEnd(Transition transition0) {
                        ((Transition)this.mTransitions.get(v)).runAnimators();
                        transition0.removeListener(this);
                    }
                });
            }
            Transition transition0 = (Transition)this.mTransitions.get(0);
            if(transition0 != null) {
                transition0.runAnimators();
            }
        }
    }

    @Override  // androidx.transition.Transition
    void setCanRemoveViews(boolean z) {
        super.setCanRemoveViews(z);
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).setCanRemoveViews(z);
        }
    }

    @Override  // androidx.transition.Transition
    void setCurrentPlayTimeMillis(long v, long v1) {
        long v2 = this.getTotalDurationMillis();
        if(this.mParent == null || (v >= 0L || v1 >= 0L) && (v <= v2 || v1 <= v2)) {
            int v4 = Long.compare(v, v1);
            int v5 = Long.compare(v, 0L);
            if(v5 >= 0 && v1 < 0L || v <= v2 && v1 > v2) {
                this.mEnded = false;
                this.notifyListeners(TransitionNotification.ON_START, v4 < 0);
            }
            if(this.mPlayTogether) {
                for(int v3 = 0; v3 < this.mTransitions.size(); ++v3) {
                    ((Transition)this.mTransitions.get(v3)).setCurrentPlayTimeMillis(v, v1);
                }
            }
            else {
                int v6 = this.indexOfTransition(v1);
                if(v4 >= 0) {
                    while(v6 < this.mTransitions.size()) {
                        Transition transition0 = (Transition)this.mTransitions.get(v6);
                        long v7 = transition0.mSeekOffsetInParent;
                        long v8 = v - v7;
                        if(v8 < 0L) {
                            break;
                        }
                        transition0.setCurrentPlayTimeMillis(v8, v1 - v7);
                        ++v6;
                    }
                }
                else {
                    while(v6 >= 0) {
                        Transition transition1 = (Transition)this.mTransitions.get(v6);
                        long v9 = v - transition1.mSeekOffsetInParent;
                        transition1.setCurrentPlayTimeMillis(v9, v1 - transition1.mSeekOffsetInParent);
                        if(v9 >= 0L) {
                            break;
                        }
                        --v6;
                    }
                }
            }
            if(this.mParent != null) {
                int v10 = Long.compare(v, v2);
                if(v10 > 0 && v1 <= v2 || v5 < 0 && v1 >= 0L) {
                    if(v10 > 0) {
                        this.mEnded = true;
                    }
                    this.notifyListeners(TransitionNotification.ON_END, v4 < 0);
                }
            }
        }
    }

    @Override  // androidx.transition.Transition
    public Transition setDuration(long v) {
        return this.setDuration(v);
    }

    public TransitionSet setDuration(long v) {
        super.setDuration(v);
        if(this.mDuration >= 0L) {
            ArrayList arrayList0 = this.mTransitions;
            if(arrayList0 != null) {
                int v1 = arrayList0.size();
                for(int v2 = 0; v2 < v1; ++v2) {
                    ((Transition)this.mTransitions.get(v2)).setDuration(v);
                }
            }
        }
        return this;
    }

    @Override  // androidx.transition.Transition
    public void setEpicenterCallback(EpicenterCallback transition$EpicenterCallback0) {
        super.setEpicenterCallback(transition$EpicenterCallback0);
        this.mChangeFlags |= 8;
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).setEpicenterCallback(transition$EpicenterCallback0);
        }
    }

    @Override  // androidx.transition.Transition
    public Transition setInterpolator(TimeInterpolator timeInterpolator0) {
        return this.setInterpolator(timeInterpolator0);
    }

    public TransitionSet setInterpolator(TimeInterpolator timeInterpolator0) {
        this.mChangeFlags |= 1;
        ArrayList arrayList0 = this.mTransitions;
        if(arrayList0 != null) {
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((Transition)this.mTransitions.get(v1)).setInterpolator(timeInterpolator0);
            }
        }
        return (TransitionSet)super.setInterpolator(timeInterpolator0);
    }

    public TransitionSet setOrdering(int v) {
        switch(v) {
            case 0: {
                this.mPlayTogether = true;
                return this;
            }
            case 1: {
                this.mPlayTogether = false;
                return this;
            }
            default: {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + v);
            }
        }
    }

    @Override  // androidx.transition.Transition
    public void setPathMotion(PathMotion pathMotion0) {
        super.setPathMotion(pathMotion0);
        this.mChangeFlags |= 4;
        if(this.mTransitions != null) {
            for(int v = 0; v < this.mTransitions.size(); ++v) {
                ((Transition)this.mTransitions.get(v)).setPathMotion(pathMotion0);
            }
        }
    }

    @Override  // androidx.transition.Transition
    public void setPropagation(TransitionPropagation transitionPropagation0) {
        super.setPropagation(transitionPropagation0);
        this.mChangeFlags |= 2;
        int v = this.mTransitions.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((Transition)this.mTransitions.get(v1)).setPropagation(transitionPropagation0);
        }
    }

    @Override  // androidx.transition.Transition
    public Transition setStartDelay(long v) {
        return this.setStartDelay(v);
    }

    public TransitionSet setStartDelay(long v) {
        return (TransitionSet)super.setStartDelay(v);
    }

    private void setupStartEndListeners() {
        TransitionSetListener transitionSet$TransitionSetListener0 = new TransitionSetListener(this);
        for(Object object0: this.mTransitions) {
            ((Transition)object0).addListener(transitionSet$TransitionSetListener0);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    @Override  // androidx.transition.Transition
    String toString(String s) {
        String s1 = super.toString(s);
        for(int v = 0; v < this.mTransitions.size(); ++v) {
            s1 = s1 + "\n" + ((Transition)this.mTransitions.get(v)).toString(s + "  ");
        }
        return s1;
    }
}

