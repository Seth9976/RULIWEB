package com.google.android.gms.common.util;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Set;

public final class ScopeUtil {
    public static String[] toScopeString(Set set0) {
        Preconditions.checkNotNull(set0, "scopes can\'t be null.");
        Scope[] arr_scope = (Scope[])set0.toArray(new Scope[set0.size()]);
        Preconditions.checkNotNull(arr_scope, "scopes can\'t be null.");
        String[] arr_s = new String[arr_scope.length];
        for(int v = 0; v < arr_scope.length; ++v) {
            arr_s[v] = arr_scope[v].getScopeUri();
        }
        return arr_s;
    }
}

