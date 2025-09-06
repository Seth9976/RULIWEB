package kotlin.text;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "", "Lkotlin/text/CharDirectionality;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
final class CharDirectionality.Companion.directionalityMap.2 extends Lambda implements Function0 {
    public static final CharDirectionality.Companion.directionalityMap.2 INSTANCE;

    static {
        CharDirectionality.Companion.directionalityMap.2.INSTANCE = new CharDirectionality.Companion.directionalityMap.2();
    }

    CharDirectionality.Companion.directionalityMap.2() {
        super(0);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }

    public final Map invoke() {
        CharDirectionality[] arr_charDirectionality = CharDirectionality.values();
        Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(arr_charDirectionality.length), 16));
        for(int v = 0; v < arr_charDirectionality.length; ++v) {
            CharDirectionality charDirectionality0 = arr_charDirectionality[v];
            map0.put(charDirectionality0.getValue(), charDirectionality0);
        }
        return map0;
    }
}

