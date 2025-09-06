package androidx.core.view;

import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.LifecycleOwner;

public interface MenuHost {
    void addMenuProvider(MenuProvider arg1);

    void addMenuProvider(MenuProvider arg1, LifecycleOwner arg2);

    void addMenuProvider(MenuProvider arg1, LifecycleOwner arg2, State arg3);

    void invalidateMenu();

    void removeMenuProvider(MenuProvider arg1);
}

