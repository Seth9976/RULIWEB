package androidx.transition;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransitionImpl;
import java.util.ArrayList;
import java.util.List;

public class FragmentTransitionSupport extends FragmentTransitionImpl {
    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void addTarget(Object object0, View view0) {
        if(object0 != null) {
            ((Transition)object0).addTarget(view0);
        }
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void addTargets(Object object0, ArrayList arrayList0) {
        int v = 0;
        if(((Transition)object0) != null) {
            if(((Transition)object0) instanceof TransitionSet) {
                int v1 = ((TransitionSet)(((Transition)object0))).getTransitionCount();
                while(v < v1) {
                    this.addTargets(((TransitionSet)(((Transition)object0))).getTransitionAt(v), arrayList0);
                    ++v;
                }
                return;
            }
            if(!FragmentTransitionSupport.hasSimpleTarget(((Transition)object0)) && FragmentTransitionSupport.isNullOrEmpty(((Transition)object0).getTargets())) {
                int v2 = arrayList0.size();
                while(v < v2) {
                    ((Transition)object0).addTarget(((View)arrayList0.get(v)));
                    ++v;
                }
            }
        }
    }

    public void animateToEnd(Object object0) {
        ((TransitionSeekController)object0).animateToEnd();
    }

    public void animateToStart(Object object0, Runnable runnable0) {
        ((TransitionSeekController)object0).animateToStart(runnable0);
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void beginDelayedTransition(ViewGroup viewGroup0, Object object0) {
        TransitionManager.beginDelayedTransition(viewGroup0, ((Transition)object0));
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public boolean canHandle(Object object0) {
        return object0 instanceof Transition;
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public Object cloneTransition(Object object0) {
        return object0 != null ? ((Transition)object0).clone() : null;
    }

    public Object controlDelayedTransition(ViewGroup viewGroup0, Object object0) {
        return TransitionManager.controlDelayedTransition(viewGroup0, ((Transition)object0));
    }

    // 去混淆评级： 低(30)
    private static boolean hasSimpleTarget(Transition transition0) {
        return !FragmentTransitionSupport.isNullOrEmpty(transition0.getTargetIds()) || !FragmentTransitionSupport.isNullOrEmpty(transition0.getTargetNames()) || !FragmentTransitionSupport.isNullOrEmpty(transition0.getTargetTypes());
    }

    public boolean isSeekingSupported() {
        return true;
    }

    public boolean isSeekingSupported(Object object0) {
        boolean z = ((Transition)object0).isSeekingSupported();
        if(!z) {
            Log.v("FragmentManager", "Predictive back not available for AndroidX Transition " + object0 + ". Please enable seeking support for the designated transition by overriding isSeekingSupported().");
        }
        return z;
    }

    // 检测为 Lambda 实现
    static void lambda$setListenerForTransitionEnd$0(Runnable runnable0, Transition transition0, Runnable runnable1) [...]

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public Object mergeTransitionsInSequence(Object object0, Object object1, Object object2) {
        Transition transition0 = (Transition)object0;
        if(transition0 != null && ((Transition)object1) != null) {
            transition0 = new TransitionSet().addTransition(transition0).addTransition(((Transition)object1)).setOrdering(1);
        }
        else if(transition0 == null) {
            transition0 = ((Transition)object1) == null ? null : ((Transition)object1);
        }
        if(((Transition)object2) != null) {
            TransitionSet transitionSet0 = new TransitionSet();
            if(transition0 != null) {
                transitionSet0.addTransition(transition0);
            }
            transitionSet0.addTransition(((Transition)object2));
            return transitionSet0;
        }
        return transition0;
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public Object mergeTransitionsTogether(Object object0, Object object1, Object object2) {
        TransitionSet transitionSet0 = new TransitionSet();
        if(object0 != null) {
            transitionSet0.addTransition(((Transition)object0));
        }
        if(object1 != null) {
            transitionSet0.addTransition(((Transition)object1));
        }
        if(object2 != null) {
            transitionSet0.addTransition(((Transition)object2));
        }
        return transitionSet0;
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void removeTarget(Object object0, View view0) {
        if(object0 != null) {
            ((Transition)object0).removeTarget(view0);
        }
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void replaceTargets(Object object0, ArrayList arrayList0, ArrayList arrayList1) {
        int v = 0;
        if(((Transition)object0) instanceof TransitionSet) {
            int v1 = ((TransitionSet)(((Transition)object0))).getTransitionCount();
            while(v < v1) {
                this.replaceTargets(((TransitionSet)(((Transition)object0))).getTransitionAt(v), arrayList0, arrayList1);
                ++v;
            }
            return;
        }
        if(!FragmentTransitionSupport.hasSimpleTarget(((Transition)object0))) {
            List list0 = ((Transition)object0).getTargets();
            if(list0.size() == arrayList0.size() && list0.containsAll(arrayList0)) {
                int v2 = arrayList1 == null ? 0 : arrayList1.size();
                while(v < v2) {
                    ((Transition)object0).addTarget(((View)arrayList1.get(v)));
                    ++v;
                }
                for(int v3 = arrayList0.size() - 1; v3 >= 0; --v3) {
                    ((Transition)object0).removeTarget(((View)arrayList0.get(v3)));
                }
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void scheduleHideFragmentView(Object object0, View view0, ArrayList arrayList0) {
        ((Transition)object0).addListener(new TransitionListener() {
            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionCancel(Transition transition0) {
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionEnd(Transition transition0) {
                transition0.removeListener(this);
                view0.setVisibility(8);
                int v = arrayList0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    ((View)arrayList0.get(v1)).setVisibility(0);
                }
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionEnd(Transition transition0, boolean z) {
                Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionPause(Transition transition0) {
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionResume(Transition transition0) {
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionStart(Transition transition0) {
                transition0.removeListener(this);
                transition0.addListener(this);
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionStart(Transition transition0, boolean z) {
                Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
            }
        });
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void scheduleRemoveTargets(Object object0, Object object1, ArrayList arrayList0, Object object2, ArrayList arrayList1, Object object3, ArrayList arrayList2) {
        ((Transition)object0).addListener(new TransitionListenerAdapter() {
            @Override  // androidx.transition.TransitionListenerAdapter
            public void onTransitionEnd(Transition transition0) {
                transition0.removeListener(this);
            }

            @Override  // androidx.transition.TransitionListenerAdapter
            public void onTransitionStart(Transition transition0) {
                Object object0 = object1;
                if(object0 != null) {
                    FragmentTransitionSupport.this.replaceTargets(object0, arrayList0, null);
                }
                Object object1 = object2;
                if(object1 != null) {
                    FragmentTransitionSupport.this.replaceTargets(object1, arrayList1, null);
                }
                Object object2 = object3;
                if(object2 != null) {
                    FragmentTransitionSupport.this.replaceTargets(object2, arrayList2, null);
                }
            }
        });
    }

    public void setCurrentPlayTime(Object object0, float f) {
        if(((TransitionSeekController)object0).isReady()) {
            long v = (long)(f * ((float)((TransitionSeekController)object0).getDurationMillis()));
            if(v == 0L) {
                v = 1L;
            }
            if(v == ((TransitionSeekController)object0).getDurationMillis()) {
                v = ((TransitionSeekController)object0).getDurationMillis() - 1L;
            }
            ((TransitionSeekController)object0).setCurrentPlayTimeMillis(v);
        }
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void setEpicenter(Object object0, Rect rect0) {
        if(object0 != null) {
            ((Transition)object0).setEpicenterCallback(new EpicenterCallback() {
                @Override  // androidx.transition.Transition$EpicenterCallback
                public Rect onGetEpicenter(Transition transition0) {
                    return rect0 == null || rect0.isEmpty() ? null : rect0;
                }
            });
        }
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void setEpicenter(Object object0, View view0) {
        if(view0 != null) {
            Rect rect0 = new Rect();
            this.getBoundsOnScreen(view0, rect0);
            ((Transition)object0).setEpicenterCallback(new EpicenterCallback() {
                @Override  // androidx.transition.Transition$EpicenterCallback
                public Rect onGetEpicenter(Transition transition0) {
                    return rect0;
                }
            });
        }
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void setListenerForTransitionEnd(Fragment fragment0, Object object0, CancellationSignal cancellationSignal0, Runnable runnable0) {
        this.setListenerForTransitionEnd(fragment0, object0, cancellationSignal0, null, runnable0);
    }

    public void setListenerForTransitionEnd(Fragment fragment0, Object object0, CancellationSignal cancellationSignal0, Runnable runnable0, Runnable runnable1) {
        cancellationSignal0.setOnCancelListener(() -> {
            if(runnable0 == null) {
                ((Transition)object0).cancel();
                runnable1.run();
                return;
            }
            runnable0.run();
        });
        ((Transition)object0).addListener(new TransitionListener() {
            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionCancel(Transition transition0) {
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionEnd(Transition transition0) {
                runnable1.run();
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionEnd(Transition transition0, boolean z) {
                Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionPause(Transition transition0) {
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionResume(Transition transition0) {
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionStart(Transition transition0) {
            }

            @Override  // androidx.transition.Transition$TransitionListener
            public void onTransitionStart(Transition transition0, boolean z) {
                Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
            }
        });
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void setSharedElementTargets(Object object0, View view0, ArrayList arrayList0) {
        List list0 = ((TransitionSet)object0).getTargets();
        list0.clear();
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            FragmentTransitionSupport.bfsAddViewChildren(list0, ((View)arrayList0.get(v1)));
        }
        list0.add(view0);
        arrayList0.add(view0);
        this.addTargets(((TransitionSet)object0), arrayList0);
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public void swapSharedElementTargets(Object object0, ArrayList arrayList0, ArrayList arrayList1) {
        if(((TransitionSet)object0) != null) {
            ((TransitionSet)object0).getTargets().clear();
            ((TransitionSet)object0).getTargets().addAll(arrayList1);
            this.replaceTargets(((TransitionSet)object0), arrayList0, arrayList1);
        }
    }

    @Override  // androidx.fragment.app.FragmentTransitionImpl
    public Object wrapTransitionInSet(Object object0) {
        if(object0 == null) {
            return null;
        }
        TransitionSet transitionSet0 = new TransitionSet();
        transitionSet0.addTransition(((Transition)object0));
        return transitionSet0;
    }
}

