package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\u001A3\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0004\u001A\u0002H\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001AQ\u0010\u0007\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u00032!\u0010\b\u001A\u001D\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\t\u001AX\u0010\u0007\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0001*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\f2!\u0010\b\u001A\u001D\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u0002H\u00010\tH\u0007¢\u0006\u0002\b\r¨\u0006\u000E"}, d2 = {"getOrImplicitDefault", "V", "K", "", "key", "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "withDefaultMutable", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
class MapsKt__MapWithDefaultKt {
    public static final Object getOrImplicitDefaultNullable(Map map0, Object object0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        if(map0 instanceof MapWithDefault) {
            return ((MapWithDefault)map0).getOrImplicitDefault(object0);
        }
        Object object1 = map0.get(object0);
        if(object1 == null && !map0.containsKey(object0)) {
            throw new NoSuchElementException("Key " + object0 + " is missing in the map.");
        }
        return object1;
    }

    public static final Map withDefault(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "defaultValue");
        return map0 instanceof MapWithDefault ? MapsKt.withDefault(((MapWithDefault)map0).getMap(), function10) : new MapWithDefaultImpl(map0, function10);
    }

    public static final Map withDefaultMutable(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "defaultValue");
        return map0 instanceof MutableMapWithDefault ? MapsKt.withDefaultMutable(((MutableMapWithDefault)map0).getMap(), function10) : new MutableMapWithDefaultImpl(map0, function10);
    }
}

