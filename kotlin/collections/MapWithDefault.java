package kotlin.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0007\bb\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003J\u0015\u0010\u0007\u001A\u00028\u00012\u0006\u0010\b\u001A\u00028\u0000H&¢\u0006\u0002\u0010\tR\u001E\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lkotlin/collections/MapWithDefault;", "K", "V", "", "map", "getMap", "()Ljava/util/Map;", "getOrImplicitDefault", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
interface MapWithDefault extends Map, KMappedMarker {
    Map getMap();

    Object getOrImplicitDefault(Object arg1);
}

