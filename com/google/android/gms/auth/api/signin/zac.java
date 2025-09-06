package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final class zac implements Comparator {
    @Override
    public final int compare(Object object0, Object object1) {
        return ((Scope)object0).getScopeUri().compareTo(((Scope)object1).getScopeUri());
    }
}

