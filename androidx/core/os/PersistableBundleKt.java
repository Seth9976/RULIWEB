package androidx.core.os;

import android.os.PersistableBundle;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\u001A\b\u0010\u0000\u001A\u00020\u0001H\u0007\u001A=\u0010\u0000\u001A\u00020\u00012.\u0010\u0002\u001A\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0007¢\u0006\u0002\u0010\u0007\u001A\u001A\u0010\b\u001A\u00020\u0001*\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\tH\u0007¨\u0006\n"}, d2 = {"persistableBundleOf", "Landroid/os/PersistableBundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/PersistableBundle;", "toPersistableBundle", "", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class PersistableBundleKt {
    public static final PersistableBundle persistableBundleOf() {
        return PersistableBundleApi21ImplKt.createPersistableBundle(0);
    }

    public static final PersistableBundle persistableBundleOf(Pair[] arr_pair) {
        PersistableBundle persistableBundle0 = PersistableBundleApi21ImplKt.createPersistableBundle(arr_pair.length);
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            PersistableBundleApi21ImplKt.putValue(persistableBundle0, ((String)pair0.component1()), pair0.component2());
        }
        return persistableBundle0;
    }

    public static final PersistableBundle toPersistableBundle(Map map0) {
        PersistableBundle persistableBundle0 = PersistableBundleApi21ImplKt.createPersistableBundle(map0.size());
        for(Object object0: map0.entrySet()) {
            PersistableBundleApi21ImplKt.putValue(persistableBundle0, ((String)((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue());
        }
        return persistableBundle0;
    }
}

