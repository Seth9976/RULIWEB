package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal.OnCancelListener;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R.id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

abstract class SpecialEffectsController {
    static class FragmentStateManagerOperation extends Operation {
        private final FragmentStateManager mFragmentStateManager;

        FragmentStateManagerOperation(State specialEffectsController$Operation$State0, LifecycleImpact specialEffectsController$Operation$LifecycleImpact0, FragmentStateManager fragmentStateManager0, CancellationSignal cancellationSignal0) {
            super(specialEffectsController$Operation$State0, specialEffectsController$Operation$LifecycleImpact0, fragmentStateManager0.getFragment(), cancellationSignal0);
            this.mFragmentStateManager = fragmentStateManager0;
        }

        @Override  // androidx.fragment.app.SpecialEffectsController$Operation
        public void complete() {
            super.complete();
            this.mFragmentStateManager.moveToExpectedState();
        }

        @Override  // androidx.fragment.app.SpecialEffectsController$Operation
        void onStart() {
            if(this.getLifecycleImpact() == LifecycleImpact.ADDING) {
                Fragment fragment0 = this.mFragmentStateManager.getFragment();
                View view0 = fragment0.mView.findFocus();
                if(view0 != null) {
                    fragment0.setFocusedView(view0);
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + view0 + " for Fragment " + fragment0);
                    }
                }
                View view1 = this.getFragment().requireView();
                if(view1.getParent() == null) {
                    this.mFragmentStateManager.addViewToContainer();
                    view1.setAlpha(0.0f);
                }
                if(view1.getAlpha() == 0.0f && view1.getVisibility() == 0) {
                    view1.setVisibility(4);
                }
                view1.setAlpha(fragment0.getPostOnViewCreatedAlpha());
                return;
            }
            if(this.getLifecycleImpact() == LifecycleImpact.REMOVING) {
                Fragment fragment1 = this.mFragmentStateManager.getFragment();
                View view2 = fragment1.requireView();
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Clearing focus " + view2.findFocus() + " on view " + view2 + " for Fragment " + fragment1);
                }
                view2.clearFocus();
            }
        }
    }

    static class Operation {
        static enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING;

        }

        static enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            void applyState(View view0) {
                switch(this) {
                    case 1: {
                        ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
                        if(viewGroup0 != null) {
                            if(FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view0 + " from container " + viewGroup0);
                            }
                            viewGroup0.removeView(view0);
                        }
                        return;
                    }
                    case 2: {
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view0 + " to VISIBLE");
                        }
                        view0.setVisibility(0);
                        return;
                    }
                    case 3: {
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view0 + " to GONE");
                        }
                        view0.setVisibility(8);
                        return;
                    }
                    case 4: {
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view0 + " to INVISIBLE");
                        }
                        view0.setVisibility(4);
                    }
                }
            }

            static State from(int v) {
                switch(v) {
                    case 0: {
                        return State.VISIBLE;
                    }
                    case 4: {
                        return State.INVISIBLE;
                    }
                    case 8: {
                        return State.GONE;
                    }
                    default: {
                        throw new IllegalArgumentException("Unknown visibility " + v);
                    }
                }
            }

            static State from(View view0) {
                return view0.getAlpha() != 0.0f || view0.getVisibility() != 0 ? State.from(view0.getVisibility()) : State.INVISIBLE;
            }
        }

        private final List mCompletionListeners;
        private State mFinalState;
        private final Fragment mFragment;
        private boolean mIsCanceled;
        private boolean mIsComplete;
        private LifecycleImpact mLifecycleImpact;
        private final HashSet mSpecialEffectsSignals;

        Operation(State specialEffectsController$Operation$State0, LifecycleImpact specialEffectsController$Operation$LifecycleImpact0, Fragment fragment0, CancellationSignal cancellationSignal0) {
            this.mCompletionListeners = new ArrayList();
            this.mSpecialEffectsSignals = new HashSet();
            this.mIsCanceled = false;
            this.mIsComplete = false;
            this.mFinalState = specialEffectsController$Operation$State0;
            this.mLifecycleImpact = specialEffectsController$Operation$LifecycleImpact0;
            this.mFragment = fragment0;
            cancellationSignal0.setOnCancelListener(() -> if(!Operation.this.isCanceled()) {
                Operation.this.mIsCanceled = true;
                if(Operation.this.mSpecialEffectsSignals.isEmpty()) {
                    Operation.this.complete();
                    return;
                }
                for(Object object0: new ArrayList(Operation.this.mSpecialEffectsSignals)) {
                    ((CancellationSignal)object0).cancel();
                }
            });

            class androidx.fragment.app.SpecialEffectsController.Operation.1 implements OnCancelListener {
                @Override  // androidx.core.os.CancellationSignal$OnCancelListener
                public void onCancel() {
                    Operation.this.cancel();
                }
            }

        }

        final void addCompletionListener(Runnable runnable0) {
            this.mCompletionListeners.add(runnable0);
        }

        // 检测为 Lambda 实现
        final void cancel() [...]

        public void complete() {
            if(!this.mIsComplete) {
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
                }
                this.mIsComplete = true;
                for(Object object0: this.mCompletionListeners) {
                    ((Runnable)object0).run();
                }
            }
        }

        public final void completeSpecialEffect(CancellationSignal cancellationSignal0) {
            if(this.mSpecialEffectsSignals.remove(cancellationSignal0) && this.mSpecialEffectsSignals.isEmpty()) {
                this.complete();
            }
        }

        public State getFinalState() {
            return this.mFinalState;
        }

        public final Fragment getFragment() {
            return this.mFragment;
        }

        LifecycleImpact getLifecycleImpact() {
            return this.mLifecycleImpact;
        }

        final boolean isCanceled() {
            return this.mIsCanceled;
        }

        final boolean isComplete() {
            return this.mIsComplete;
        }

        public final void markStartedSpecialEffect(CancellationSignal cancellationSignal0) {
            this.onStart();
            this.mSpecialEffectsSignals.add(cancellationSignal0);
        }

        final void mergeWith(State specialEffectsController$Operation$State0, LifecycleImpact specialEffectsController$Operation$LifecycleImpact0) {
            switch(specialEffectsController$Operation$LifecycleImpact0) {
                case 1: {
                    if(this.mFinalState == State.REMOVED) {
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.mLifecycleImpact + " to ADDING.");
                        }
                        this.mFinalState = State.VISIBLE;
                        this.mLifecycleImpact = LifecycleImpact.ADDING;
                        return;
                    }
                    break;
                }
                case 2: {
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + " -> REMOVED. mLifecycleImpact  = " + this.mLifecycleImpact + " to REMOVING.");
                    }
                    this.mFinalState = State.REMOVED;
                    this.mLifecycleImpact = LifecycleImpact.REMOVING;
                    return;
                }
                case 3: {
                    if(this.mFinalState != State.REMOVED) {
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.mFragment + " mFinalState = " + this.mFinalState + " -> " + specialEffectsController$Operation$State0 + ". ");
                        }
                        this.mFinalState = specialEffectsController$Operation$State0;
                        return;
                    }
                    break;
                }
            }
        }

        void onStart() {
        }

        @Override
        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.mFinalState + "} {mLifecycleImpact = " + this.mLifecycleImpact + "} {mFragment = " + this.mFragment + "}";
        }
    }

    private final ViewGroup mContainer;
    boolean mIsContainerPostponed;
    boolean mOperationDirectionIsPop;
    final ArrayList mPendingOperations;
    final ArrayList mRunningOperations;

    SpecialEffectsController(ViewGroup viewGroup0) {
        this.mPendingOperations = new ArrayList();
        this.mRunningOperations = new ArrayList();
        this.mOperationDirectionIsPop = false;
        this.mIsContainerPostponed = false;
        this.mContainer = viewGroup0;
    }

    private void enqueue(State specialEffectsController$Operation$State0, LifecycleImpact specialEffectsController$Operation$LifecycleImpact0, FragmentStateManager fragmentStateManager0) {
        synchronized(this.mPendingOperations) {
            CancellationSignal cancellationSignal0 = new CancellationSignal();
            Operation specialEffectsController$Operation0 = this.findPendingOperation(fragmentStateManager0.getFragment());
            if(specialEffectsController$Operation0 != null) {
                specialEffectsController$Operation0.mergeWith(specialEffectsController$Operation$State0, specialEffectsController$Operation$LifecycleImpact0);
                return;
            }
            FragmentStateManagerOperation specialEffectsController$FragmentStateManagerOperation0 = new FragmentStateManagerOperation(specialEffectsController$Operation$State0, specialEffectsController$Operation$LifecycleImpact0, fragmentStateManager0, cancellationSignal0);
            this.mPendingOperations.add(specialEffectsController$FragmentStateManagerOperation0);
            specialEffectsController$FragmentStateManagerOperation0.addCompletionListener(new Runnable() {
                @Override
                public void run() {
                    if(SpecialEffectsController.this.mPendingOperations.contains(specialEffectsController$FragmentStateManagerOperation0)) {
                        specialEffectsController$FragmentStateManagerOperation0.getFinalState().applyState(specialEffectsController$FragmentStateManagerOperation0.getFragment().mView);
                    }
                }
            });
            specialEffectsController$FragmentStateManagerOperation0.addCompletionListener(new Runnable() {
                @Override
                public void run() {
                    SpecialEffectsController.this.mPendingOperations.remove(specialEffectsController$FragmentStateManagerOperation0);
                    SpecialEffectsController.this.mRunningOperations.remove(specialEffectsController$FragmentStateManagerOperation0);
                }
            });
        }
    }

    void enqueueAdd(State specialEffectsController$Operation$State0, FragmentStateManager fragmentStateManager0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + fragmentStateManager0.getFragment());
        }
        this.enqueue(specialEffectsController$Operation$State0, LifecycleImpact.ADDING, fragmentStateManager0);
    }

    void enqueueHide(FragmentStateManager fragmentStateManager0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + fragmentStateManager0.getFragment());
        }
        this.enqueue(State.GONE, LifecycleImpact.NONE, fragmentStateManager0);
    }

    void enqueueRemove(FragmentStateManager fragmentStateManager0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + fragmentStateManager0.getFragment());
        }
        this.enqueue(State.REMOVED, LifecycleImpact.REMOVING, fragmentStateManager0);
    }

    void enqueueShow(FragmentStateManager fragmentStateManager0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + fragmentStateManager0.getFragment());
        }
        this.enqueue(State.VISIBLE, LifecycleImpact.NONE, fragmentStateManager0);
    }

    abstract void executeOperations(List arg1, boolean arg2);

    void executePendingOperations() {
        if(this.mIsContainerPostponed) {
            return;
        }
        if(!ViewCompat.isAttachedToWindow(this.mContainer)) {
            this.forceCompleteAllOperations();
            this.mOperationDirectionIsPop = false;
            return;
        }
        synchronized(this.mPendingOperations) {
            if(!this.mPendingOperations.isEmpty()) {
                ArrayList arrayList1 = new ArrayList(this.mRunningOperations);
                this.mRunningOperations.clear();
                for(Object object0: arrayList1) {
                    Operation specialEffectsController$Operation0 = (Operation)object0;
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + specialEffectsController$Operation0);
                    }
                    specialEffectsController$Operation0.cancel();
                    if(!specialEffectsController$Operation0.isComplete()) {
                        this.mRunningOperations.add(specialEffectsController$Operation0);
                    }
                }
                this.updateFinalState();
                ArrayList arrayList2 = new ArrayList(this.mPendingOperations);
                this.mPendingOperations.clear();
                this.mRunningOperations.addAll(arrayList2);
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Executing pending operations");
                }
                for(Object object1: arrayList2) {
                    ((Operation)object1).onStart();
                }
                this.executeOperations(arrayList2, this.mOperationDirectionIsPop);
                this.mOperationDirectionIsPop = false;
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Finished executing pending operations");
                }
            }
        }
    }

    private Operation findPendingOperation(Fragment fragment0) {
        for(Object object0: this.mPendingOperations) {
            Operation specialEffectsController$Operation0 = (Operation)object0;
            if(specialEffectsController$Operation0.getFragment().equals(fragment0) && !specialEffectsController$Operation0.isCanceled()) {
                return specialEffectsController$Operation0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    private Operation findRunningOperation(Fragment fragment0) {
        for(Object object0: this.mRunningOperations) {
            Operation specialEffectsController$Operation0 = (Operation)object0;
            if(specialEffectsController$Operation0.getFragment().equals(fragment0) && !specialEffectsController$Operation0.isCanceled()) {
                return specialEffectsController$Operation0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    void forceCompleteAllOperations() {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
        }
        boolean z = ViewCompat.isAttachedToWindow(this.mContainer);
        synchronized(this.mPendingOperations) {
            this.updateFinalState();
            for(Object object0: this.mPendingOperations) {
                ((Operation)object0).onStart();
            }
            for(Object object1: new ArrayList(this.mRunningOperations)) {
                Operation specialEffectsController$Operation0 = (Operation)object1;
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: " + (z ? "" : "Container " + this.mContainer + " is not attached to window. ") + "Cancelling running operation " + specialEffectsController$Operation0);
                }
                specialEffectsController$Operation0.cancel();
            }
            for(Object object2: new ArrayList(this.mPendingOperations)) {
                Operation specialEffectsController$Operation1 = (Operation)object2;
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: " + (z ? "" : "Container " + this.mContainer + " is not attached to window. ") + "Cancelling pending operation " + specialEffectsController$Operation1);
                }
                specialEffectsController$Operation1.cancel();
            }
        }
    }

    void forcePostponedExecutePendingOperations() {
        if(this.mIsContainerPostponed) {
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Forcing postponed operations");
            }
            this.mIsContainerPostponed = false;
            this.executePendingOperations();
        }
    }

    LifecycleImpact getAwaitingCompletionLifecycleImpact(FragmentStateManager fragmentStateManager0) {
        Operation specialEffectsController$Operation0 = this.findPendingOperation(fragmentStateManager0.getFragment());
        LifecycleImpact specialEffectsController$Operation$LifecycleImpact0 = specialEffectsController$Operation0 == null ? null : specialEffectsController$Operation0.getLifecycleImpact();
        Operation specialEffectsController$Operation1 = this.findRunningOperation(fragmentStateManager0.getFragment());
        return specialEffectsController$Operation1 == null || specialEffectsController$Operation$LifecycleImpact0 != null && specialEffectsController$Operation$LifecycleImpact0 != LifecycleImpact.NONE ? specialEffectsController$Operation$LifecycleImpact0 : specialEffectsController$Operation1.getLifecycleImpact();
    }

    public ViewGroup getContainer() {
        return this.mContainer;
    }

    static SpecialEffectsController getOrCreateController(ViewGroup viewGroup0, FragmentManager fragmentManager0) {
        return SpecialEffectsController.getOrCreateController(viewGroup0, fragmentManager0.getSpecialEffectsControllerFactory());
    }

    static SpecialEffectsController getOrCreateController(ViewGroup viewGroup0, SpecialEffectsControllerFactory specialEffectsControllerFactory0) {
        Object object0 = viewGroup0.getTag(id.special_effects_controller_view_tag);
        if(object0 instanceof SpecialEffectsController) {
            return (SpecialEffectsController)object0;
        }
        SpecialEffectsController specialEffectsController0 = specialEffectsControllerFactory0.createController(viewGroup0);
        viewGroup0.setTag(id.special_effects_controller_view_tag, specialEffectsController0);
        return specialEffectsController0;
    }

    void markPostponedState() {
        synchronized(this.mPendingOperations) {
            this.updateFinalState();
            this.mIsContainerPostponed = false;
            for(int v1 = this.mPendingOperations.size() - 1; v1 >= 0; --v1) {
                Operation specialEffectsController$Operation0 = (Operation)this.mPendingOperations.get(v1);
                State specialEffectsController$Operation$State0 = State.from(specialEffectsController$Operation0.getFragment().mView);
                if(specialEffectsController$Operation0.getFinalState() == State.VISIBLE && specialEffectsController$Operation$State0 != State.VISIBLE) {
                    this.mIsContainerPostponed = specialEffectsController$Operation0.getFragment().isPostponed();
                    break;
                }
            }
        }
    }

    private void updateFinalState() {
        for(Object object0: this.mPendingOperations) {
            Operation specialEffectsController$Operation0 = (Operation)object0;
            if(specialEffectsController$Operation0.getLifecycleImpact() == LifecycleImpact.ADDING) {
                specialEffectsController$Operation0.mergeWith(State.from(specialEffectsController$Operation0.getFragment().requireView().getVisibility()), LifecycleImpact.NONE);
            }
        }
    }

    void updateOperationDirection(boolean z) {
        this.mOperationDirectionIsPop = z;
    }
}

