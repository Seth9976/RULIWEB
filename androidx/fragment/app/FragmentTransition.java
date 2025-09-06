package androidx.fragment.app;

import android.view.View;
import androidx.collection.ArrayMap;
import androidx.transition.FragmentTransitionSupport;
import java.util.ArrayList;

class FragmentTransition {
    static final FragmentTransitionImpl PLATFORM_IMPL;
    static final FragmentTransitionImpl SUPPORT_IMPL;

    static {
        FragmentTransition.PLATFORM_IMPL = new FragmentTransitionCompat21();
        FragmentTransition.SUPPORT_IMPL = FragmentTransition.resolveSupportImpl();
    }

    static void callSharedElementStartEnd(Fragment fragment0, Fragment fragment1, boolean z, ArrayMap arrayMap0, boolean z1) {
        if((z ? fragment1.getEnterTransitionCallback() : fragment0.getEnterTransitionCallback()) != null) {
            ArrayList arrayList0 = new ArrayList();
            ArrayList arrayList1 = new ArrayList();
            int v1 = arrayMap0 == null ? 0 : arrayMap0.size();
            for(int v = 0; v < v1; ++v) {
                arrayList1.add(((String)arrayMap0.keyAt(v)));
                arrayList0.add(((View)arrayMap0.valueAt(v)));
            }
            if(z1) {
            }
        }
    }

    static String findKeyForValue(ArrayMap arrayMap0, String s) {
        int v = arrayMap0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(s.equals(arrayMap0.valueAt(v1))) {
                return (String)arrayMap0.keyAt(v1);
            }
        }
        return null;
    }

    // 去混淆评级： 低(20)
    private static FragmentTransitionImpl resolveSupportImpl() {
        try {
            return new FragmentTransitionSupport();
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    static void retainValues(ArrayMap arrayMap0, ArrayMap arrayMap1) {
        for(int v = arrayMap0.size() - 1; v >= 0; --v) {
            if(!arrayMap1.containsKey(((String)arrayMap0.valueAt(v)))) {
                arrayMap0.removeAt(v);
            }
        }
    }

    static void setViewVisibility(ArrayList arrayList0, int v) {
        if(arrayList0 != null) {
            for(int v1 = arrayList0.size() - 1; v1 >= 0; --v1) {
                ((View)arrayList0.get(v1)).setVisibility(v);
            }
        }
    }

    static boolean supportsTransition() [...] // 潜在的解密器
}

