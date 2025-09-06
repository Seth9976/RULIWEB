package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public abstract class FragmentContainer {
    @Deprecated
    public Fragment instantiate(Context context0, String s, Bundle bundle0) {
        return Fragment.instantiate(context0, s, bundle0);
    }

    public abstract View onFindViewById(int arg1);

    public abstract boolean onHasView();
}

