package androidx.webkit;

import java.util.ArrayList;
import java.util.List;

public class NoVarySearchHeader {
    public final List consideredQueryParameters;
    public final boolean ignoreDifferencesInParameters;
    public final List ignoredQueryParameters;
    public final boolean varyOnKeyOrder;

    private NoVarySearchHeader(boolean z, boolean z1, List list0, List list1) {
        this.varyOnKeyOrder = z;
        this.ignoreDifferencesInParameters = z1;
        this.ignoredQueryParameters = list0;
        this.consideredQueryParameters = list1;
    }

    public static NoVarySearchHeader alwaysVaryHeader() {
        return new NoVarySearchHeader(true, false, new ArrayList(), new ArrayList());
    }

    public static NoVarySearchHeader neverVaryExcept(boolean z, List list0) {
        return new NoVarySearchHeader(z, true, new ArrayList(), list0);
    }

    public static NoVarySearchHeader neverVaryHeader() {
        return new NoVarySearchHeader(false, true, new ArrayList(), new ArrayList());
    }

    public static NoVarySearchHeader varyExcept(boolean z, List list0) {
        return new NoVarySearchHeader(z, false, list0, new ArrayList());
    }
}

