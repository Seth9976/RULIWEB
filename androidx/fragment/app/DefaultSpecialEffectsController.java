package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal.OnCancelListener;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

class DefaultSpecialEffectsController extends SpecialEffectsController {
    static class AnimationInfo extends SpecialEffectsInfo {
        private AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim;

        AnimationInfo(Operation specialEffectsController$Operation0, CancellationSignal cancellationSignal0, boolean z) {
            super(specialEffectsController$Operation0, cancellationSignal0);
            this.mLoadedAnim = false;
            this.mIsPop = z;
        }

        AnimationOrAnimator getAnimation(Context context0) {
            if(this.mLoadedAnim) {
                return this.mAnimation;
            }
            AnimationOrAnimator fragmentAnim$AnimationOrAnimator0 = FragmentAnim.loadAnimation(context0, this.getOperation().getFragment(), this.getOperation().getFinalState() == State.VISIBLE, this.mIsPop);
            this.mAnimation = fragmentAnim$AnimationOrAnimator0;
            this.mLoadedAnim = true;
            return fragmentAnim$AnimationOrAnimator0;
        }
    }

    static class SpecialEffectsInfo {
        private final Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(Operation specialEffectsController$Operation0, CancellationSignal cancellationSignal0) {
            this.mOperation = specialEffectsController$Operation0;
            this.mSignal = cancellationSignal0;
        }

        void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }

        Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            State specialEffectsController$Operation$State0 = State.from(this.mOperation.getFragment().mView);
            State specialEffectsController$Operation$State1 = this.mOperation.getFinalState();
            return specialEffectsController$Operation$State0 == specialEffectsController$Operation$State1 || specialEffectsController$Operation$State0 != State.VISIBLE && specialEffectsController$Operation$State1 != State.VISIBLE;
        }
    }

    static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(Operation specialEffectsController$Operation0, CancellationSignal cancellationSignal0, boolean z, boolean z1) {
            super(specialEffectsController$Operation0, cancellationSignal0);
            if(specialEffectsController$Operation0.getFinalState() == State.VISIBLE) {
                this.mTransition = z ? specialEffectsController$Operation0.getFragment().getReenterTransition() : specialEffectsController$Operation0.getFragment().getEnterTransition();
                this.mOverlapAllowed = z ? specialEffectsController$Operation0.getFragment().getAllowReturnTransitionOverlap() : specialEffectsController$Operation0.getFragment().getAllowEnterTransitionOverlap();
            }
            else {
                this.mTransition = z ? specialEffectsController$Operation0.getFragment().getReturnTransition() : specialEffectsController$Operation0.getFragment().getExitTransition();
                this.mOverlapAllowed = true;
            }
            if(z1) {
                if(z) {
                    this.mSharedElementTransition = specialEffectsController$Operation0.getFragment().getSharedElementReturnTransition();
                    return;
                }
                this.mSharedElementTransition = specialEffectsController$Operation0.getFragment().getSharedElementEnterTransition();
                return;
            }
            this.mSharedElementTransition = null;
        }

        private FragmentTransitionImpl getHandlingImpl(Object object0) {
            if(object0 == null) {
                return null;
            }
            if(FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(object0)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if(FragmentTransition.SUPPORT_IMPL == null || !FragmentTransition.SUPPORT_IMPL.canHandle(object0)) {
                throw new IllegalArgumentException("Transition " + object0 + " for fragment " + this.getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
            }
            return FragmentTransition.SUPPORT_IMPL;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl fragmentTransitionImpl0 = this.getHandlingImpl(this.mTransition);
            FragmentTransitionImpl fragmentTransitionImpl1 = this.getHandlingImpl(this.mSharedElementTransition);
            if(fragmentTransitionImpl0 != null && fragmentTransitionImpl1 != null && fragmentTransitionImpl0 != fragmentTransitionImpl1) {
                throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
            }
            return fragmentTransitionImpl0 == null ? fragmentTransitionImpl1 : fragmentTransitionImpl0;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        Object getTransition() {
            return this.mTransition;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }
    }

    DefaultSpecialEffectsController(ViewGroup viewGroup0) {
        super(viewGroup0);
    }

    void applyContainerChanges(Operation specialEffectsController$Operation0) {
        specialEffectsController$Operation0.getFinalState().applyState(specialEffectsController$Operation0.getFragment().mView);
    }

    void captureTransitioningViews(ArrayList arrayList0, View view0) {
        if(view0 instanceof ViewGroup) {
            if(!ViewGroupCompat.isTransitionGroup(((ViewGroup)view0))) {
                int v = ((ViewGroup)view0).getChildCount();
                for(int v1 = 0; v1 < v; ++v1) {
                    View view1 = ((ViewGroup)view0).getChildAt(v1);
                    if(view1.getVisibility() == 0) {
                        this.captureTransitioningViews(arrayList0, view1);
                    }
                }
            }
            else if(!arrayList0.contains(view0)) {
                arrayList0.add(((ViewGroup)view0));
            }
        }
        else if(!arrayList0.contains(view0)) {
            arrayList0.add(view0);
        }
    }

    @Override  // androidx.fragment.app.SpecialEffectsController
    void executeOperations(List list0, boolean z) {
        Operation specialEffectsController$Operation0 = null;
        Operation specialEffectsController$Operation1 = null;
        for(Object object0: list0) {
            Operation specialEffectsController$Operation2 = (Operation)object0;
            State specialEffectsController$Operation$State0 = State.from(specialEffectsController$Operation2.getFragment().mView);
            int v = androidx.fragment.app.DefaultSpecialEffectsController.10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[specialEffectsController$Operation2.getFinalState().ordinal()];
            if(v != 1 && v != 2 && v != 3) {
                if(v != 4 || specialEffectsController$Operation$State0 == State.VISIBLE) {
                    continue;
                }
                specialEffectsController$Operation1 = specialEffectsController$Operation2;
            }
            else if(specialEffectsController$Operation$State0 == State.VISIBLE && specialEffectsController$Operation0 == null) {
                specialEffectsController$Operation0 = specialEffectsController$Operation2;
            }
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Executing operations from " + specialEffectsController$Operation0 + " to " + specialEffectsController$Operation1);
        }
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list0);
        this.syncAnimations(list0);
        for(Object object1: list0) {
            Operation specialEffectsController$Operation3 = (Operation)object1;
            CancellationSignal cancellationSignal0 = new CancellationSignal();
            specialEffectsController$Operation3.markStartedSpecialEffect(cancellationSignal0);
            arrayList0.add(new AnimationInfo(specialEffectsController$Operation3, cancellationSignal0, z));
            CancellationSignal cancellationSignal1 = new CancellationSignal();
            specialEffectsController$Operation3.markStartedSpecialEffect(cancellationSignal1);
            boolean z1 = false;
            if(!z) {
                if(specialEffectsController$Operation3 == specialEffectsController$Operation1) {
                    z1 = true;
                }
            }
            else if(specialEffectsController$Operation3 == specialEffectsController$Operation0) {
                z1 = true;
            }
            arrayList1.add(new TransitionInfo(specialEffectsController$Operation3, cancellationSignal1, z, z1));
            specialEffectsController$Operation3.addCompletionListener(new Runnable() {
                @Override
                public void run() {
                    if(arrayList2.contains(specialEffectsController$Operation3)) {
                        arrayList2.remove(specialEffectsController$Operation3);
                        DefaultSpecialEffectsController.this.applyContainerChanges(specialEffectsController$Operation3);
                    }
                }
            });
        }
        Map map0 = this.startTransitions(arrayList1, arrayList2, z, specialEffectsController$Operation0, specialEffectsController$Operation1);
        this.startAnimations(arrayList0, arrayList2, map0.containsValue(Boolean.TRUE), map0);
        for(Object object2: arrayList2) {
            this.applyContainerChanges(((Operation)object2));
        }
        arrayList2.clear();
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Completed executing operations from " + specialEffectsController$Operation0 + " to " + specialEffectsController$Operation1);
        }
    }

    void findNamedViews(Map map0, View view0) {
        String s = ViewCompat.getTransitionName(view0);
        if(s != null) {
            map0.put(s, view0);
        }
        if(view0 instanceof ViewGroup) {
            int v = ((ViewGroup)view0).getChildCount();
            for(int v1 = 0; v1 < v; ++v1) {
                View view1 = ((ViewGroup)view0).getChildAt(v1);
                if(view1.getVisibility() == 0) {
                    this.findNamedViews(map0, view1);
                }
            }
        }
    }

    void retainMatchingViews(ArrayMap arrayMap0, Collection collection0) {
        Iterator iterator0 = arrayMap0.entrySet().iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            if(!collection0.contains(ViewCompat.getTransitionName(((View)((Map.Entry)object0).getValue())))) {
                iterator0.remove();
            }
        }
    }

    private void startAnimations(List list0, List list1, boolean z, Map map0) {
        View view2;
        AnimationInfo defaultSpecialEffectsController$AnimationInfo2;
        Operation specialEffectsController$Operation2;
        ViewGroup viewGroup0 = this.getContainer();
        Context context0 = viewGroup0.getContext();
        ArrayList arrayList0 = new ArrayList();
        boolean z1 = false;
        for(Object object0: list0) {
            AnimationInfo defaultSpecialEffectsController$AnimationInfo0 = (AnimationInfo)object0;
            if(defaultSpecialEffectsController$AnimationInfo0.isVisibilityUnchanged()) {
                defaultSpecialEffectsController$AnimationInfo0.completeSpecialEffect();
            }
            else {
                AnimationOrAnimator fragmentAnim$AnimationOrAnimator0 = defaultSpecialEffectsController$AnimationInfo0.getAnimation(context0);
                if(fragmentAnim$AnimationOrAnimator0 == null) {
                    defaultSpecialEffectsController$AnimationInfo0.completeSpecialEffect();
                }
                else {
                    Animator animator0 = fragmentAnim$AnimationOrAnimator0.animator;
                    if(animator0 == null) {
                        arrayList0.add(defaultSpecialEffectsController$AnimationInfo0);
                    }
                    else {
                        Operation specialEffectsController$Operation0 = defaultSpecialEffectsController$AnimationInfo0.getOperation();
                        Fragment fragment0 = specialEffectsController$Operation0.getFragment();
                        Object object1 = map0.get(specialEffectsController$Operation0);
                        if(Boolean.TRUE.equals(object1)) {
                            if(FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment0 + " as this Fragment was involved in a Transition.");
                            }
                            defaultSpecialEffectsController$AnimationInfo0.completeSpecialEffect();
                        }
                        else {
                            boolean z2 = specialEffectsController$Operation0.getFinalState() == State.GONE;
                            if(z2) {
                                list1.remove(specialEffectsController$Operation0);
                            }
                            View view0 = fragment0.mView;
                            viewGroup0.startViewTransition(view0);
                            animator0.addListener(new AnimatorListenerAdapter() {
                                @Override  // android.animation.AnimatorListenerAdapter
                                public void onAnimationEnd(Animator animator0) {
                                    viewGroup0.endViewTransition(view0);
                                    if(z2) {
                                        specialEffectsController$Operation0.getFinalState().applyState(view0);
                                    }
                                    defaultSpecialEffectsController$AnimationInfo0.completeSpecialEffect();
                                    if(FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + specialEffectsController$Operation0 + " has ended.");
                                    }
                                }
                            });
                            animator0.setTarget(view0);
                            animator0.start();
                            if(FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animator from operation " + specialEffectsController$Operation0 + " has started.");
                            }
                            defaultSpecialEffectsController$AnimationInfo0.getSignal().setOnCancelListener(new OnCancelListener() {
                                @Override  // androidx.core.os.CancellationSignal$OnCancelListener
                                public void onCancel() {
                                    animator0.end();
                                    if(FragmentManager.isLoggingEnabled(2)) {
                                        Log.v("FragmentManager", "Animator from operation " + specialEffectsController$Operation0 + " has been canceled.");
                                    }
                                }
                            });
                            z1 = true;
                        }
                    }
                }
            }
        }
        for(Object object2: arrayList0) {
            AnimationInfo defaultSpecialEffectsController$AnimationInfo1 = (AnimationInfo)object2;
            Operation specialEffectsController$Operation1 = defaultSpecialEffectsController$AnimationInfo1.getOperation();
            Fragment fragment1 = specialEffectsController$Operation1.getFragment();
            if(z) {
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment1 + " as Animations cannot run alongside Transitions.");
                }
                defaultSpecialEffectsController$AnimationInfo1.completeSpecialEffect();
            }
            else if(z1) {
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment1 + " as Animations cannot run alongside Animators.");
                }
                defaultSpecialEffectsController$AnimationInfo1.completeSpecialEffect();
            }
            else {
                View view1 = fragment1.mView;
                Animation animation0 = (Animation)Preconditions.checkNotNull(((AnimationOrAnimator)Preconditions.checkNotNull(defaultSpecialEffectsController$AnimationInfo1.getAnimation(context0))).animation);
                if(specialEffectsController$Operation1.getFinalState() == State.REMOVED) {
                    viewGroup0.startViewTransition(view1);
                    EndViewTransitionAnimation fragmentAnim$EndViewTransitionAnimation0 = new EndViewTransitionAnimation(animation0, viewGroup0, view1);
                    specialEffectsController$Operation2 = specialEffectsController$Operation1;
                    defaultSpecialEffectsController$AnimationInfo2 = defaultSpecialEffectsController$AnimationInfo1;
                    view2 = view1;
                    fragmentAnim$EndViewTransitionAnimation0.setAnimationListener(new Animation.AnimationListener() {
                        @Override  // android.view.animation.Animation$AnimationListener
                        public void onAnimationEnd(Animation animation0) {
                            androidx.fragment.app.DefaultSpecialEffectsController.4.1 defaultSpecialEffectsController$4$10 = new Runnable() {
                                @Override
                                public void run() {
                                    androidx.fragment.app.DefaultSpecialEffectsController.4.this.val$container.endViewTransition(androidx.fragment.app.DefaultSpecialEffectsController.4.this.val$viewToAnimate);
                                    androidx.fragment.app.DefaultSpecialEffectsController.4.this.val$animationInfo.completeSpecialEffect();
                                }
                            };
                            viewGroup0.post(defaultSpecialEffectsController$4$10);
                            if(FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + specialEffectsController$Operation2 + " has ended.");
                            }
                        }

                        @Override  // android.view.animation.Animation$AnimationListener
                        public void onAnimationRepeat(Animation animation0) {
                        }

                        @Override  // android.view.animation.Animation$AnimationListener
                        public void onAnimationStart(Animation animation0) {
                            if(FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Animation from operation " + specialEffectsController$Operation2 + " has reached onAnimationStart.");
                            }
                        }
                    });
                    view2.startAnimation(fragmentAnim$EndViewTransitionAnimation0);
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Animation from operation " + specialEffectsController$Operation2 + " has started.");
                    }
                }
                else {
                    view1.startAnimation(animation0);
                    defaultSpecialEffectsController$AnimationInfo1.completeSpecialEffect();
                    specialEffectsController$Operation2 = specialEffectsController$Operation1;
                    defaultSpecialEffectsController$AnimationInfo2 = defaultSpecialEffectsController$AnimationInfo1;
                    view2 = view1;
                }
                defaultSpecialEffectsController$AnimationInfo2.getSignal().setOnCancelListener(new OnCancelListener() {
                    @Override  // androidx.core.os.CancellationSignal$OnCancelListener
                    public void onCancel() {
                        view2.clearAnimation();
                        viewGroup0.endViewTransition(view2);
                        defaultSpecialEffectsController$AnimationInfo2.completeSpecialEffect();
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Animation from operation " + specialEffectsController$Operation2 + " has been cancelled.");
                        }
                    }
                });
            }
        }
    }

    private Map startTransitions(List list0, List list1, boolean z, Operation specialEffectsController$Operation0, Operation specialEffectsController$Operation1) {
        Operation specialEffectsController$Operation3;
        Object object14;
        Object object13;
        Object object12;
        ArrayList arrayList13;
        ArrayList arrayList12;
        View view10;
        HashMap hashMap3;
        ArrayList arrayList11;
        View view9;
        Rect rect2;
        View view5;
        HashMap hashMap1;
        ArrayList arrayList7;
        ArrayMap arrayMap3;
        ArrayList arrayList6;
        int v5;
        Rect rect1;
        SharedElementCallback sharedElementCallback1;
        SharedElementCallback sharedElementCallback0;
        View view2;
        HashMap hashMap0 = new HashMap();
        FragmentTransitionImpl fragmentTransitionImpl0 = null;
        for(Object object0: list0) {
            TransitionInfo defaultSpecialEffectsController$TransitionInfo0 = (TransitionInfo)object0;
            if(!defaultSpecialEffectsController$TransitionInfo0.isVisibilityUnchanged()) {
                FragmentTransitionImpl fragmentTransitionImpl1 = defaultSpecialEffectsController$TransitionInfo0.getHandlingImpl();
                if(fragmentTransitionImpl0 == null) {
                    fragmentTransitionImpl0 = fragmentTransitionImpl1;
                }
                else {
                    if(fragmentTransitionImpl1 != null && fragmentTransitionImpl0 != fragmentTransitionImpl1) {
                        throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + defaultSpecialEffectsController$TransitionInfo0.getOperation().getFragment() + " returned Transition " + defaultSpecialEffectsController$TransitionInfo0.getTransition() + " which uses a different Transition  type than other Fragments.");
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        if(fragmentTransitionImpl0 == null) {
            for(Object object1: list0) {
                hashMap0.put(((TransitionInfo)object1).getOperation(), Boolean.FALSE);
                ((TransitionInfo)object1).completeSpecialEffect();
            }
            return hashMap0;
        }
        View view0 = new View(this.getContainer().getContext());
        Rect rect0 = new Rect();
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        ArrayMap arrayMap0 = new ArrayMap();
        Iterator iterator2 = list0.iterator();
        Object object2 = null;
        View view1 = null;
        boolean z1 = false;
        while(true) {
            view2 = view1;
            if(!iterator2.hasNext()) {
                break;
            }
            Object object3 = iterator2.next();
            TransitionInfo defaultSpecialEffectsController$TransitionInfo1 = (TransitionInfo)object3;
            if(!defaultSpecialEffectsController$TransitionInfo1.hasSharedElementTransition() || specialEffectsController$Operation0 == null || specialEffectsController$Operation1 == null) {
            label_164:
                view5 = view0;
                rect2 = rect0;
                arrayMap3 = arrayMap0;
                arrayList7 = arrayList1;
                hashMap1 = hashMap0;
                arrayList6 = arrayList0;
            }
            else {
                Object object4 = fragmentTransitionImpl0.wrapTransitionInSet(fragmentTransitionImpl0.cloneTransition(defaultSpecialEffectsController$TransitionInfo1.getSharedElementTransition()));
                ArrayList arrayList2 = specialEffectsController$Operation1.getFragment().getSharedElementSourceNames();
                ArrayList arrayList3 = specialEffectsController$Operation0.getFragment().getSharedElementSourceNames();
                ArrayList arrayList4 = specialEffectsController$Operation0.getFragment().getSharedElementTargetNames();
                Object object5 = object4;
                for(int v = 0; v < arrayList4.size(); ++v) {
                    int v1 = arrayList2.indexOf(arrayList4.get(v));
                    if(v1 != -1) {
                        arrayList2.set(v1, ((String)arrayList3.get(v)));
                    }
                }
                ArrayList arrayList5 = specialEffectsController$Operation1.getFragment().getSharedElementTargetNames();
                if(z) {
                    sharedElementCallback0 = specialEffectsController$Operation0.getFragment().getEnterTransitionCallback();
                    sharedElementCallback1 = specialEffectsController$Operation1.getFragment().getExitTransitionCallback();
                }
                else {
                    sharedElementCallback0 = specialEffectsController$Operation0.getFragment().getExitTransitionCallback();
                    sharedElementCallback1 = specialEffectsController$Operation1.getFragment().getEnterTransitionCallback();
                }
                int v2 = arrayList2.size();
                for(int v3 = 0; v3 < v2; ++v3) {
                    arrayMap0.put(((String)arrayList2.get(v3)), ((String)arrayList5.get(v3)));
                }
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", ">>> entering view names <<<");
                    for(Object object6: arrayList5) {
                        Log.v("FragmentManager", "Name: " + ((String)object6));
                    }
                    rect1 = rect0;
                    Log.v("FragmentManager", ">>> exiting view names <<<");
                    for(Object object7: arrayList2) {
                        Log.v("FragmentManager", "Name: " + ((String)object7));
                    }
                }
                else {
                    rect1 = rect0;
                }
                ArrayMap arrayMap1 = new ArrayMap();
                this.findNamedViews(arrayMap1, specialEffectsController$Operation0.getFragment().mView);
                arrayMap1.retainAll(arrayList2);
                if(sharedElementCallback0 == null) {
                    arrayMap0.retainAll(arrayMap1.keySet());
                }
                else {
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Executing exit callback for operation " + specialEffectsController$Operation0);
                    }
                    for(int v4 = arrayList2.size() - 1; v4 >= 0; v4 = v5 - 1) {
                        String s = (String)arrayList2.get(v4);
                        View view3 = (View)arrayMap1.get(s);
                        if(view3 == null) {
                            arrayMap0.remove(s);
                            v5 = v4;
                        }
                        else {
                            v5 = v4;
                            if(!s.equals(ViewCompat.getTransitionName(view3))) {
                                String s1 = (String)arrayMap0.remove(s);
                                arrayMap0.put(ViewCompat.getTransitionName(view3), s1);
                            }
                        }
                    }
                }
                ArrayMap arrayMap2 = new ArrayMap();
                this.findNamedViews(arrayMap2, specialEffectsController$Operation1.getFragment().mView);
                arrayMap2.retainAll(arrayList5);
                arrayMap2.retainAll(arrayMap0.values());
                if(sharedElementCallback1 == null) {
                    FragmentTransition.retainValues(arrayMap0, arrayMap2);
                }
                else {
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Executing enter callback for operation " + specialEffectsController$Operation1);
                    }
                    for(int v6 = arrayList5.size() - 1; v6 >= 0; --v6) {
                        String s2 = (String)arrayList5.get(v6);
                        View view4 = (View)arrayMap2.get(s2);
                        if(view4 == null) {
                            String s3 = FragmentTransition.findKeyForValue(arrayMap0, s2);
                            if(s3 != null) {
                                arrayMap0.remove(s3);
                            }
                        }
                        else if(!s2.equals(ViewCompat.getTransitionName(view4))) {
                            String s4 = FragmentTransition.findKeyForValue(arrayMap0, s2);
                            if(s4 != null) {
                                arrayMap0.put(s4, ViewCompat.getTransitionName(view4));
                            }
                        }
                    }
                }
                this.retainMatchingViews(arrayMap1, arrayMap0.keySet());
                this.retainMatchingViews(arrayMap2, arrayMap0.values());
                if(arrayMap0.isEmpty()) {
                    arrayList0.clear();
                    arrayList1.clear();
                    arrayList6 = arrayList0;
                    arrayMap3 = arrayMap0;
                    arrayList7 = arrayList1;
                    view1 = view2;
                    hashMap1 = hashMap0;
                    view5 = view0;
                    rect2 = rect1;
                    object2 = null;
                    goto label_171;
                }
                else {
                    FragmentTransition.callSharedElementStartEnd(specialEffectsController$Operation1.getFragment(), specialEffectsController$Operation0.getFragment(), z, arrayMap1, true);
                    OneShotPreDrawListener.add(this.getContainer(), new Runnable() {
                        @Override
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(specialEffectsController$Operation1.getFragment(), specialEffectsController$Operation0.getFragment(), z, arrayMap2, false);
                        }
                    });
                    arrayList0.addAll(arrayMap1.values());
                    if(!arrayList2.isEmpty()) {
                        View view6 = (View)arrayMap1.get(((String)arrayList2.get(0)));
                        fragmentTransitionImpl0.setEpicenter(object5, view6);
                        view2 = view6;
                    }
                    arrayList1.addAll(arrayMap2.values());
                    if(arrayList5.isEmpty()) {
                        rect2 = rect1;
                    }
                    else {
                        View view7 = (View)arrayMap2.get(((String)arrayList5.get(0)));
                        if(view7 != null) {
                            rect2 = rect1;
                            OneShotPreDrawListener.add(this.getContainer(), new Runnable() {
                                @Override
                                public void run() {
                                    fragmentTransitionImpl0.getBoundsOnScreen(view7, rect2);
                                }
                            });
                            z1 = true;
                        }
                    }
                    fragmentTransitionImpl0.setSharedElementTargets(object5, view0, arrayList0);
                    arrayMap3 = arrayMap0;
                    arrayList6 = arrayList0;
                    view5 = view0;
                    fragmentTransitionImpl0.scheduleRemoveTargets(object5, null, null, null, null, object5, arrayList1);
                    arrayList7 = arrayList1;
                    hashMap1 = hashMap0;
                    hashMap1.put(specialEffectsController$Operation0, Boolean.TRUE);
                    hashMap1.put(specialEffectsController$Operation1, Boolean.TRUE);
                    object2 = object5;
                    goto label_170;
                }
                goto label_164;
            }
        label_170:
            view1 = view2;
        label_171:
            view0 = view5;
            arrayList0 = arrayList6;
            arrayList1 = arrayList7;
            hashMap0 = hashMap1;
            arrayMap0 = arrayMap3;
            rect0 = rect2;
        }
        View view8 = view0;
        ArrayList arrayList8 = arrayList1;
        HashMap hashMap2 = hashMap0;
        ArrayList arrayList9 = arrayList0;
        ArrayList arrayList10 = new ArrayList();
        Object object8 = null;
        Object object9 = null;
        for(Object object10: list0) {
            TransitionInfo defaultSpecialEffectsController$TransitionInfo2 = (TransitionInfo)object10;
            if(defaultSpecialEffectsController$TransitionInfo2.isVisibilityUnchanged()) {
                hashMap2.put(defaultSpecialEffectsController$TransitionInfo2.getOperation(), Boolean.FALSE);
                defaultSpecialEffectsController$TransitionInfo2.completeSpecialEffect();
            }
            else {
                Object object11 = fragmentTransitionImpl0.cloneTransition(defaultSpecialEffectsController$TransitionInfo2.getTransition());
                Operation specialEffectsController$Operation2 = defaultSpecialEffectsController$TransitionInfo2.getOperation();
                boolean z2 = object2 != null && (specialEffectsController$Operation2 == specialEffectsController$Operation0 || specialEffectsController$Operation2 == specialEffectsController$Operation1);
                if(object11 == null) {
                    if(!z2) {
                        hashMap2.put(specialEffectsController$Operation2, Boolean.FALSE);
                        defaultSpecialEffectsController$TransitionInfo2.completeSpecialEffect();
                    }
                    view9 = view8;
                    arrayList11 = arrayList8;
                    hashMap3 = hashMap2;
                    view10 = view2;
                    arrayList12 = arrayList9;
                    arrayList13 = arrayList10;
                }
                else {
                    ArrayList arrayList14 = new ArrayList();
                    this.captureTransitioningViews(arrayList14, specialEffectsController$Operation2.getFragment().mView);
                    if(z2) {
                        if(specialEffectsController$Operation2 == specialEffectsController$Operation0) {
                            arrayList14.removeAll(arrayList9);
                        }
                        else {
                            arrayList14.removeAll(arrayList8);
                        }
                    }
                    if(arrayList14.isEmpty()) {
                        fragmentTransitionImpl0.addTarget(object11, view8);
                        object12 = object11;
                        hashMap3 = hashMap2;
                        arrayList11 = arrayList8;
                        view9 = view8;
                        object13 = object8;
                        object14 = object9;
                        specialEffectsController$Operation3 = specialEffectsController$Operation2;
                        view10 = view2;
                        arrayList12 = arrayList9;
                        arrayList13 = arrayList10;
                    }
                    else {
                        fragmentTransitionImpl0.addTargets(object11, arrayList14);
                        object12 = object11;
                        hashMap3 = hashMap2;
                        arrayList11 = arrayList8;
                        view10 = view2;
                        object14 = object9;
                        arrayList12 = arrayList9;
                        arrayList13 = arrayList10;
                        view9 = view8;
                        object13 = object8;
                        fragmentTransitionImpl0.scheduleRemoveTargets(object12, object11, arrayList14, null, null, null, null);
                        if(specialEffectsController$Operation2.getFinalState() == State.GONE) {
                            specialEffectsController$Operation3 = specialEffectsController$Operation2;
                            list1.remove(specialEffectsController$Operation3);
                            ArrayList arrayList15 = new ArrayList(arrayList14);
                            arrayList15.remove(specialEffectsController$Operation3.getFragment().mView);
                            fragmentTransitionImpl0.scheduleHideFragmentView(object12, specialEffectsController$Operation3.getFragment().mView, arrayList15);
                            OneShotPreDrawListener.add(this.getContainer(), new Runnable() {
                                @Override
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList14, 4);
                                }
                            });
                        }
                        else {
                            specialEffectsController$Operation3 = specialEffectsController$Operation2;
                        }
                    }
                    if(specialEffectsController$Operation3.getFinalState() == State.VISIBLE) {
                        arrayList13.addAll(arrayList14);
                        if(z1) {
                            fragmentTransitionImpl0.setEpicenter(object12, rect0);
                        }
                    }
                    else {
                        fragmentTransitionImpl0.setEpicenter(object12, view10);
                    }
                    hashMap3.put(specialEffectsController$Operation3, Boolean.TRUE);
                    if(defaultSpecialEffectsController$TransitionInfo2.isOverlapAllowed()) {
                        object13 = fragmentTransitionImpl0.mergeTransitionsTogether(object13, object12, null);
                    }
                    else {
                        object14 = fragmentTransitionImpl0.mergeTransitionsTogether(object14, object12, null);
                    }
                    object9 = object14;
                    object8 = object13;
                }
                hashMap2 = hashMap3;
                arrayList10 = arrayList13;
                arrayList9 = arrayList12;
                view8 = view9;
                arrayList8 = arrayList11;
                view2 = view10;
            }
        }
        Object object15 = fragmentTransitionImpl0.mergeTransitionsInSequence(object8, object9, object2);
        if(object15 != null) {
            for(Object object16: list0) {
                TransitionInfo defaultSpecialEffectsController$TransitionInfo3 = (TransitionInfo)object16;
                if(!defaultSpecialEffectsController$TransitionInfo3.isVisibilityUnchanged()) {
                    Operation specialEffectsController$Operation4 = defaultSpecialEffectsController$TransitionInfo3.getOperation();
                    if(defaultSpecialEffectsController$TransitionInfo3.getTransition() == null && (object2 == null || specialEffectsController$Operation4 != specialEffectsController$Operation0 && specialEffectsController$Operation4 != specialEffectsController$Operation1)) {
                    }
                    else if(ViewCompat.isLaidOut(this.getContainer())) {
                        fragmentTransitionImpl0.setListenerForTransitionEnd(defaultSpecialEffectsController$TransitionInfo3.getOperation().getFragment(), object15, defaultSpecialEffectsController$TransitionInfo3.getSignal(), new Runnable() {
                            @Override
                            public void run() {
                                defaultSpecialEffectsController$TransitionInfo3.completeSpecialEffect();
                                if(FragmentManager.isLoggingEnabled(2)) {
                                    Log.v("FragmentManager", "Transition for operation " + specialEffectsController$Operation4 + "has completed");
                                }
                            }
                        });
                    }
                    else {
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Container " + this.getContainer() + " has not been laid out. Completing operation " + specialEffectsController$Operation4);
                        }
                        defaultSpecialEffectsController$TransitionInfo3.completeSpecialEffect();
                    }
                }
            }
            if(ViewCompat.isLaidOut(this.getContainer())) {
                FragmentTransition.setViewVisibility(arrayList10, 4);
                ArrayList arrayList16 = fragmentTransitionImpl0.prepareSetNameOverridesReordered(arrayList8);
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", ">>>>> Beginning transition <<<<<");
                    Log.v("FragmentManager", ">>>>> SharedElementFirstOutViews <<<<<");
                    for(Object object17: arrayList9) {
                        Log.v("FragmentManager", "View: " + ((View)object17) + " Name: " + ViewCompat.getTransitionName(((View)object17)));
                    }
                    Log.v("FragmentManager", ">>>>> SharedElementLastInViews <<<<<");
                    for(Object object18: arrayList8) {
                        Log.v("FragmentManager", "View: " + ((View)object18) + " Name: " + ViewCompat.getTransitionName(((View)object18)));
                    }
                }
                fragmentTransitionImpl0.beginDelayedTransition(this.getContainer(), object15);
                fragmentTransitionImpl0.setNameOverridesReordered(this.getContainer(), arrayList9, arrayList8, arrayList16, arrayMap0);
                FragmentTransition.setViewVisibility(arrayList10, 0);
                fragmentTransitionImpl0.swapSharedElementTargets(object2, arrayList9, arrayList8);
                return hashMap2;
            }
        }
        return hashMap2;
    }

    private void syncAnimations(List list0) {
        Fragment fragment0 = ((Operation)list0.get(list0.size() - 1)).getFragment();
        for(Object object0: list0) {
            ((Operation)object0).getFragment().mAnimationInfo.mEnterAnim = fragment0.mAnimationInfo.mEnterAnim;
            ((Operation)object0).getFragment().mAnimationInfo.mExitAnim = fragment0.mAnimationInfo.mExitAnim;
            ((Operation)object0).getFragment().mAnimationInfo.mPopEnterAnim = fragment0.mAnimationInfo.mPopEnterAnim;
            ((Operation)object0).getFragment().mAnimationInfo.mPopExitAnim = fragment0.mAnimationInfo.mPopExitAnim;
        }
    }
}

