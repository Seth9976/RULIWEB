package androidx.lifecycle;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001A\u00020\bJ\u0013\u0010\t\u001A\u0004\u0018\u00010\u00062\u0006\u0010\n\u001A\u00020\u0005H\u0087\u0002J\u000E\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\u00050\fH\u0007J\u0018\u0010\r\u001A\u00020\b2\u0006\u0010\n\u001A\u00020\u00052\u0006\u0010\u000E\u001A\u00020\u0006H\u0007R\u001A\u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/lifecycle/ViewModelStore;", "", "()V", "map", "", "", "Landroidx/lifecycle/ViewModel;", "clear", "", "get", "key", "keys", "", "put", "viewModel", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ViewModelStore {
    private final Map map;

    public ViewModelStore() {
        this.map = new LinkedHashMap();
    }

    public final void clear() {
        for(Object object0: this.map.values()) {
            ((ViewModel)object0).clear();
        }
        this.map.clear();
    }

    public final ViewModel get(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        return (ViewModel)this.map.get(s);
    }

    public final Set keys() {
        return new HashSet(this.map.keySet());
    }

    public final void put(String s, ViewModel viewModel0) {
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(viewModel0, "viewModel");
        ViewModel viewModel1 = (ViewModel)this.map.put(s, viewModel0);
        if(viewModel1 != null) {
            viewModel1.onCleared();
        }
    }
}

