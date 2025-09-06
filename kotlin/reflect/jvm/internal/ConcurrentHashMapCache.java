package kotlin.reflect.jvm.internal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001D\u0012\u0016\u0010\u0003\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001A\u00020\nH\u0016J\u0019\u0010\u000B\u001A\u00028\u00002\n\u0010\f\u001A\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0002\u0010\rR\u001E\u0010\u0007\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00028\u00000\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001E\u0010\u0003\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lkotlin/reflect/jvm/internal/ConcurrentHashMapCache;", "V", "Lkotlin/reflect/jvm/internal/CacheByClass;", "compute", "Lkotlin/Function1;", "Ljava/lang/Class;", "(Lkotlin/jvm/functions/Function1;)V", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "clear", "", "get", "key", "(Ljava/lang/Class;)Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ConcurrentHashMapCache extends CacheByClass {
    private final ConcurrentHashMap cache;
    private final Function1 compute;

    public ConcurrentHashMapCache(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "compute");
        super();
        this.compute = function10;
        this.cache = new ConcurrentHashMap();
    }

    @Override  // kotlin.reflect.jvm.internal.CacheByClass
    public void clear() {
        this.cache.clear();
    }

    @Override  // kotlin.reflect.jvm.internal.CacheByClass
    public Object get(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "key");
        ConcurrentMap concurrentMap0 = this.cache;
        Object object0 = concurrentMap0.get(class0);
        if(object0 == null) {
            Object object1 = this.compute.invoke(class0);
            Object object2 = concurrentMap0.putIfAbsent(class0, object1);
            return object2 == null ? object1 : object2;
        }
        return object0;
    }
}

