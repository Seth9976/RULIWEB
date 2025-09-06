package kotlin.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u0000.\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001AK\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0001*\u0002H\u0002*\u0015\u0012\u0006\b\u0000\u0012\u00020\u0004\u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u00050\u00032\b\u0010\u0006\u001A\u0004\u0018\u00010\u00072\n\u0010\b\u001A\u0006\u0012\u0002\b\u00030\tH\u0087\n¢\u0006\u0002\u0010\n\u001AO\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0001*\u0002H\u0002*\u0017\u0012\u0006\b\u0000\u0012\u00020\u0004\u0012\u000B\b\u0001\u0012\u0007H\u0002¢\u0006\u0002\b\u00050\u000B2\b\u0010\u0006\u001A\u0004\u0018\u00010\u00072\n\u0010\b\u001A\u0006\u0012\u0002\b\u00030\tH\u0087\n¢\u0006\u0004\b\f\u0010\n\u001AF\u0010\r\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0006\b\u0000\u0012\u00020\u0004\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000B2\b\u0010\u0006\u001A\u0004\u0018\u00010\u00072\n\u0010\b\u001A\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u000F\u001A\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"getValue", "V1", "V", "", "", "Lkotlin/internal/Exact;", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "", "getVar", "setValue", "", "value", "(Ljava/util/Map;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class MapAccessorsKt {
    private static final Object getValue(Map map0, Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        return MapsKt.getOrImplicitDefaultNullable(map0, kProperty0.getName());
    }

    private static final Object getVar(Map map0, Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        return MapsKt.getOrImplicitDefaultNullable(map0, kProperty0.getName());
    }

    private static final void setValue(Map map0, Object object0, KProperty kProperty0, Object object1) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        map0.put(kProperty0.getName(), object1);
    }
}

