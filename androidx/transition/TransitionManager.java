package androidx.transition;

import android.os.Build.VERSION;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import androidx.collection.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TransitionManager {
    static class MultiListener implements View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
        ViewGroup mSceneRoot;
        Transition mTransition;

        MultiListener(Transition transition0, ViewGroup viewGroup0) {
            this.mTransition = transition0;
            this.mSceneRoot = viewGroup0;
        }

        @Override  // android.view.ViewTreeObserver$OnPreDrawListener
        public boolean onPreDraw() {
            this.removeListeners();
            if(!TransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
                return true;
            }
            ArrayMap arrayMap0 = TransitionManager.getRunningTransitions();
            ArrayList arrayList0 = (ArrayList)arrayMap0.get(this.mSceneRoot);
            ArrayList arrayList1 = null;
            if(arrayList0 == null) {
                arrayList0 = new ArrayList();
                arrayMap0.put(this.mSceneRoot, arrayList0);
            }
            else if(arrayList0.size() > 0) {
                arrayList1 = new ArrayList(arrayList0);
            }
            arrayList0.add(this.mTransition);
            this.mTransition.addListener(new TransitionListenerAdapter() {
                @Override  // androidx.transition.TransitionListenerAdapter
                public void onTransitionEnd(Transition transition0) {
                    ((ArrayList)arrayMap0.get(MultiListener.this.mSceneRoot)).remove(transition0);
                    transition0.removeListener(this);
                }
            });
            this.mTransition.captureValues(this.mSceneRoot, false);
            if(arrayList1 != null) {
                for(Object object0: arrayList1) {
                    ((Transition)object0).resume(this.mSceneRoot);
                }
            }
            this.mTransition.playTransition(this.mSceneRoot);
            return true;
        }

        @Override  // android.view.View$OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view0) {
        }

        @Override  // android.view.View$OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view0) {
            this.removeListeners();
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList arrayList0 = (ArrayList)TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if(arrayList0 != null && arrayList0.size() > 0) {
                for(Object object0: arrayList0) {
                    ((Transition)object0).resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }
    }

    private static final String LOG_TAG = "TransitionManager";
    private ArrayMap mScenePairTransitions;
    private ArrayMap mSceneTransitions;
    private static Transition sDefaultTransition;
    static ArrayList sPendingTransitions;
    private static ThreadLocal sRunningTransitions;

    static {
        TransitionManager.sDefaultTransition = new AutoTransition();
        TransitionManager.sRunningTransitions = new ThreadLocal();
        TransitionManager.sPendingTransitions = new ArrayList();
    }

    public TransitionManager() {
        this.mSceneTransitions = new ArrayMap();
        this.mScenePairTransitions = new ArrayMap();
    }

    public static void beginDelayedTransition(ViewGroup viewGroup0) {
        TransitionManager.beginDelayedTransition(viewGroup0, null);
    }

    public static void beginDelayedTransition(ViewGroup viewGroup0, Transition transition0) {
        if(!TransitionManager.sPendingTransitions.contains(viewGroup0) && viewGroup0.isLaidOut()) {
            TransitionManager.sPendingTransitions.add(viewGroup0);
            if(transition0 == null) {
                transition0 = TransitionManager.sDefaultTransition;
            }
            Transition transition1 = transition0.clone();
            TransitionManager.sceneChangeSetup(viewGroup0, transition1);
            Scene.setCurrentScene(viewGroup0, null);
            TransitionManager.sceneChangeRunTransition(viewGroup0, transition1);
        }
    }

    private static void changeScene(Scene scene0, Transition transition0) {
        ViewGroup viewGroup0 = scene0.getSceneRoot();
        if(!TransitionManager.sPendingTransitions.contains(viewGroup0)) {
            Scene scene1 = Scene.getCurrentScene(viewGroup0);
            if(transition0 == null) {
                if(scene1 != null) {
                    scene1.exit();
                }
                scene0.enter();
                return;
            }
            TransitionManager.sPendingTransitions.add(viewGroup0);
            Transition transition1 = transition0.clone();
            if(scene1 != null && scene1.isCreatedFromLayoutResource()) {
                transition1.setCanRemoveViews(true);
            }
            TransitionManager.sceneChangeSetup(viewGroup0, transition1);
            scene0.enter();
            TransitionManager.sceneChangeRunTransition(viewGroup0, transition1);
        }
    }

    public static TransitionSeekController controlDelayedTransition(ViewGroup viewGroup0, Transition transition0) {
        if(!TransitionManager.sPendingTransitions.contains(viewGroup0) && viewGroup0.isLaidOut() && Build.VERSION.SDK_INT >= 34) {
            if(!transition0.isSeekingSupported()) {
                throw new IllegalArgumentException("The Transition must support seeking.");
            }
            TransitionManager.sPendingTransitions.add(viewGroup0);
            Transition transition1 = transition0.clone();
            TransitionSet transitionSet0 = new TransitionSet();
            transitionSet0.addTransition(transition1);
            TransitionManager.sceneChangeSetup(viewGroup0, transitionSet0);
            Scene.setCurrentScene(viewGroup0, null);
            TransitionManager.sceneChangeRunTransition(viewGroup0, transitionSet0);
            viewGroup0.invalidate();
            return transitionSet0.createSeekController();
        }
        return null;
    }

    public static TransitionSeekController createSeekController(Scene scene0, Transition transition0) {
        ViewGroup viewGroup0 = scene0.getSceneRoot();
        if(!transition0.isSeekingSupported()) {
            throw new IllegalArgumentException("The Transition must support seeking.");
        }
        if(TransitionManager.sPendingTransitions.contains(viewGroup0)) {
            return null;
        }
        Scene scene1 = Scene.getCurrentScene(viewGroup0);
        if(viewGroup0.isLaidOut() && Build.VERSION.SDK_INT >= 34) {
            TransitionManager.sPendingTransitions.add(viewGroup0);
            Transition transition1 = transition0.clone();
            TransitionSet transitionSet0 = new TransitionSet();
            transitionSet0.addTransition(transition1);
            if(scene1 != null && scene1.isCreatedFromLayoutResource()) {
                transitionSet0.setCanRemoveViews(true);
            }
            TransitionManager.sceneChangeSetup(viewGroup0, transitionSet0);
            scene0.enter();
            TransitionManager.sceneChangeRunTransition(viewGroup0, transitionSet0);
            return transitionSet0.createSeekController();
        }
        if(scene1 != null) {
            scene1.exit();
        }
        scene0.enter();
        return null;
    }

    public static void endTransitions(ViewGroup viewGroup0) {
        TransitionManager.sPendingTransitions.remove(viewGroup0);
        ArrayList arrayList0 = (ArrayList)TransitionManager.getRunningTransitions().get(viewGroup0);
        if(arrayList0 != null && !arrayList0.isEmpty()) {
            ArrayList arrayList1 = new ArrayList(arrayList0);
            for(int v = arrayList1.size() - 1; v >= 0; --v) {
                ((Transition)arrayList1.get(v)).forceToEnd(viewGroup0);
            }
        }
    }

    static ArrayMap getRunningTransitions() {
        WeakReference weakReference0 = (WeakReference)TransitionManager.sRunningTransitions.get();
        if(weakReference0 != null) {
            ArrayMap arrayMap0 = (ArrayMap)weakReference0.get();
            if(arrayMap0 != null) {
                return arrayMap0;
            }
        }
        ArrayMap arrayMap1 = new ArrayMap();
        WeakReference weakReference1 = new WeakReference(arrayMap1);
        TransitionManager.sRunningTransitions.set(weakReference1);
        return arrayMap1;
    }

    private Transition getTransition(Scene scene0) {
        Scene scene1 = Scene.getCurrentScene(scene0.getSceneRoot());
        if(scene1 != null) {
            ArrayMap arrayMap0 = (ArrayMap)this.mScenePairTransitions.get(scene0);
            if(arrayMap0 != null) {
                Transition transition0 = (Transition)arrayMap0.get(scene1);
                if(transition0 != null) {
                    return transition0;
                }
            }
        }
        Transition transition1 = (Transition)this.mSceneTransitions.get(scene0);
        return transition1 == null ? TransitionManager.sDefaultTransition : transition1;
    }

    public static void go(Scene scene0) {
        TransitionManager.changeScene(scene0, TransitionManager.sDefaultTransition);
    }

    public static void go(Scene scene0, Transition transition0) {
        TransitionManager.changeScene(scene0, transition0);
    }

    private static void sceneChangeRunTransition(ViewGroup viewGroup0, Transition transition0) {
        if(transition0 != null && viewGroup0 != null) {
            MultiListener transitionManager$MultiListener0 = new MultiListener(transition0, viewGroup0);
            viewGroup0.addOnAttachStateChangeListener(transitionManager$MultiListener0);
            viewGroup0.getViewTreeObserver().addOnPreDrawListener(transitionManager$MultiListener0);
        }
    }

    private static void sceneChangeSetup(ViewGroup viewGroup0, Transition transition0) {
        ArrayList arrayList0 = (ArrayList)TransitionManager.getRunningTransitions().get(viewGroup0);
        if(arrayList0 != null && arrayList0.size() > 0) {
            for(Object object0: arrayList0) {
                ((Transition)object0).pause(viewGroup0);
            }
        }
        if(transition0 != null) {
            transition0.captureValues(viewGroup0, true);
        }
        Scene scene0 = Scene.getCurrentScene(viewGroup0);
        if(scene0 != null) {
            scene0.exit();
        }
    }

    public void setTransition(Scene scene0, Scene scene1, Transition transition0) {
        ArrayMap arrayMap0 = (ArrayMap)this.mScenePairTransitions.get(scene1);
        if(arrayMap0 == null) {
            arrayMap0 = new ArrayMap();
            this.mScenePairTransitions.put(scene1, arrayMap0);
        }
        arrayMap0.put(scene0, transition0);
    }

    public void setTransition(Scene scene0, Transition transition0) {
        this.mSceneTransitions.put(scene0, transition0);
    }

    public void transitionTo(Scene scene0) {
        TransitionManager.changeScene(scene0, this.getTransition(scene0));
    }
}

