package androidx.core.view;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuHostHelper {
    static class LifecycleContainer {
        final Lifecycle mLifecycle;
        private LifecycleEventObserver mObserver;

        LifecycleContainer(Lifecycle lifecycle0, LifecycleEventObserver lifecycleEventObserver0) {
            this.mLifecycle = lifecycle0;
            this.mObserver = lifecycleEventObserver0;
            lifecycle0.addObserver(lifecycleEventObserver0);
        }

        void clearObservers() {
            this.mLifecycle.removeObserver(this.mObserver);
            this.mObserver = null;
        }
    }

    private final CopyOnWriteArrayList mMenuProviders;
    private final Runnable mOnInvalidateMenuCallback;
    private final Map mProviderToLifecycleContainers;

    public MenuHostHelper(Runnable runnable0) {
        this.mMenuProviders = new CopyOnWriteArrayList();
        this.mProviderToLifecycleContainers = new HashMap();
        this.mOnInvalidateMenuCallback = runnable0;
    }

    public void addMenuProvider(MenuProvider menuProvider0) {
        this.mMenuProviders.add(menuProvider0);
        this.mOnInvalidateMenuCallback.run();
    }

    public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0) {
        this.addMenuProvider(menuProvider0);
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        LifecycleContainer menuHostHelper$LifecycleContainer0 = (LifecycleContainer)this.mProviderToLifecycleContainers.remove(menuProvider0);
        if(menuHostHelper$LifecycleContainer0 != null) {
            menuHostHelper$LifecycleContainer0.clearObservers();
        }
        LifecycleContainer menuHostHelper$LifecycleContainer1 = new LifecycleContainer(lifecycle0, (LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> if(lifecycle$Event0 == Event.ON_DESTROY) {
            this.removeMenuProvider(menuProvider0);
        });
        this.mProviderToLifecycleContainers.put(menuProvider0, menuHostHelper$LifecycleContainer1);
    }

    public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0, State lifecycle$State0) {
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        LifecycleContainer menuHostHelper$LifecycleContainer0 = (LifecycleContainer)this.mProviderToLifecycleContainers.remove(menuProvider0);
        if(menuHostHelper$LifecycleContainer0 != null) {
            menuHostHelper$LifecycleContainer0.clearObservers();
        }
        LifecycleContainer menuHostHelper$LifecycleContainer1 = new LifecycleContainer(lifecycle0, (LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> {
            if(lifecycle$Event0 == Event.upTo(lifecycle$State0)) {
                this.addMenuProvider(menuProvider0);
                return;
            }
            if(lifecycle$Event0 == Event.ON_DESTROY) {
                this.removeMenuProvider(menuProvider0);
                return;
            }
            if(lifecycle$Event0 == Event.downFrom(lifecycle$State0)) {
                this.mMenuProviders.remove(menuProvider0);
                this.mOnInvalidateMenuCallback.run();
            }
        });
        this.mProviderToLifecycleContainers.put(menuProvider0, menuHostHelper$LifecycleContainer1);
    }

    // 检测为 Lambda 实现
    void lambda$addMenuProvider$0$androidx-core-view-MenuHostHelper(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]

    // 检测为 Lambda 实现
    void lambda$addMenuProvider$1$androidx-core-view-MenuHostHelper(State lifecycle$State0, MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]

    public void onCreateMenu(Menu menu0, MenuInflater menuInflater0) {
        for(Object object0: this.mMenuProviders) {
            ((MenuProvider)object0).onCreateMenu(menu0, menuInflater0);
        }
    }

    public void onMenuClosed(Menu menu0) {
        for(Object object0: this.mMenuProviders) {
            ((MenuProvider)object0).onMenuClosed(menu0);
        }
    }

    public boolean onMenuItemSelected(MenuItem menuItem0) {
        for(Object object0: this.mMenuProviders) {
            if(((MenuProvider)object0).onMenuItemSelected(menuItem0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public void onPrepareMenu(Menu menu0) {
        for(Object object0: this.mMenuProviders) {
            ((MenuProvider)object0).onPrepareMenu(menu0);
        }
    }

    public void removeMenuProvider(MenuProvider menuProvider0) {
        this.mMenuProviders.remove(menuProvider0);
        LifecycleContainer menuHostHelper$LifecycleContainer0 = (LifecycleContainer)this.mProviderToLifecycleContainers.remove(menuProvider0);
        if(menuHostHelper$LifecycleContainer0 != null) {
            menuHostHelper$LifecycleContainer0.clearObservers();
        }
        this.mOnInvalidateMenuCallback.run();
    }
}

