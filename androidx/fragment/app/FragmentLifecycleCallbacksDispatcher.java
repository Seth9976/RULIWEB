package androidx.fragment.app;

import android.os.Bundle;
import android.view.View;
import java.util.concurrent.CopyOnWriteArrayList;

class FragmentLifecycleCallbacksDispatcher {
    static final class FragmentLifecycleCallbacksHolder {
        final FragmentLifecycleCallbacks mCallback;
        final boolean mRecursive;

        FragmentLifecycleCallbacksHolder(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0, boolean z) {
            this.mCallback = fragmentManager$FragmentLifecycleCallbacks0;
            this.mRecursive = z;
        }
    }

    private final FragmentManager mFragmentManager;
    private final CopyOnWriteArrayList mLifecycleCallbacks;

    FragmentLifecycleCallbacksDispatcher(FragmentManager fragmentManager0) {
        this.mLifecycleCallbacks = new CopyOnWriteArrayList();
        this.mFragmentManager = fragmentManager0;
    }

    void dispatchOnFragmentActivityCreated(Fragment fragment0, Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentActivityCreated(fragment0, bundle0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentAttached(Fragment fragment0, boolean z) {
        this.mFragmentManager.getHost().getContext();
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentAttached(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentCreated(Fragment fragment0, Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentCreated(fragment0, bundle0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentDestroyed(Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentDestroyed(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentDetached(Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentDetached(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentPaused(Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentPaused(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentPreAttached(Fragment fragment0, boolean z) {
        this.mFragmentManager.getHost().getContext();
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentPreAttached(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentPreCreated(Fragment fragment0, Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentPreCreated(fragment0, bundle0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentResumed(Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentResumed(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentSaveInstanceState(Fragment fragment0, Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentSaveInstanceState(fragment0, bundle0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentStarted(Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentStarted(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentStopped(Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentStopped(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    void dispatchOnFragmentViewCreated(Fragment fragment0, View view0, Bundle bundle0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentViewCreated(fragment0, view0, bundle0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksDispatcher$FragmentLifecycleCallbacksHolder0 = (FragmentLifecycleCallbacksHolder)object0;
            if(!z || fragmentLifecycleCallbacksDispatcher$FragmentLifecycleCallbacksHolder0.mRecursive) {
                fragmentLifecycleCallbacksDispatcher$FragmentLifecycleCallbacksHolder0.mCallback.onFragmentViewCreated(this.mFragmentManager, fragment0, view0, bundle0);
            }
        }
    }

    void dispatchOnFragmentViewDestroyed(Fragment fragment0, boolean z) {
        Fragment fragment1 = this.mFragmentManager.getParent();
        if(fragment1 != null) {
            fragment1.getParentFragmentManager().getLifecycleCallbacksDispatcher().dispatchOnFragmentViewDestroyed(fragment0, true);
        }
        for(Object object0: this.mLifecycleCallbacks) {
            if(!z || ((FragmentLifecycleCallbacksHolder)object0).mRecursive) {
            }
        }
    }

    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0, boolean z) {
        FragmentLifecycleCallbacksHolder fragmentLifecycleCallbacksDispatcher$FragmentLifecycleCallbacksHolder0 = new FragmentLifecycleCallbacksHolder(fragmentManager$FragmentLifecycleCallbacks0, z);
        this.mLifecycleCallbacks.add(fragmentLifecycleCallbacksDispatcher$FragmentLifecycleCallbacksHolder0);
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0) {
        synchronized(this.mLifecycleCallbacks) {
            int v1 = this.mLifecycleCallbacks.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                if(((FragmentLifecycleCallbacksHolder)this.mLifecycleCallbacks.get(v2)).mCallback == fragmentManager$FragmentLifecycleCallbacks0) {
                    this.mLifecycleCallbacks.remove(v2);
                    break;
                }
            }
        }
    }
}

