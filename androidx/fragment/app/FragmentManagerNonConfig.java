package androidx.fragment.app;

import java.util.Collection;
import java.util.Map;

@Deprecated
public class FragmentManagerNonConfig {
    private final Map mChildNonConfigs;
    private final Collection mFragments;
    private final Map mViewModelStores;

    FragmentManagerNonConfig(Collection collection0, Map map0, Map map1) {
        this.mFragments = collection0;
        this.mChildNonConfigs = map0;
        this.mViewModelStores = map1;
    }

    Map getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    Collection getFragments() {
        return this.mFragments;
    }

    Map getViewModelStores() {
        return this.mViewModelStores;
    }

    boolean isRetaining(Fragment fragment0) {
        return this.mFragments == null ? false : this.mFragments.contains(fragment0);
    }
}

