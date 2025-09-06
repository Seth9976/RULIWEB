package kotlin.reflect.jvm.internal;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClassifiers;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\b\u0010\u0011\u001A\u00020\u0012H\u0000\u001A6\u0010\u0013\u001A\u00020\u0002\"\b\b\u0000\u0010\u0014*\u00020\r2\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00140\u00162\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0018\u001A\u00020\bH\u0000\u001A6\u0010\u0019\u001A\u00020\u0002\"\b\b\u0000\u0010\u0014*\u00020\r2\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00140\u00162\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0018\u001A\u00020\bH\u0002\u001A&\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00140\f\"\b\b\u0000\u0010\u0014*\u00020\r2\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00140\u0016H\u0000\u001A \u0010\u001B\u001A\u00020\u001C\"\b\b\u0000\u0010\u0014*\u00020\r2\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00140\u0016H\u0000\"\u0014\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"6\u0010\u0003\u001A*\u0012&\u0012$\u0012\u001A\u0012\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005j\u0002`\t\u0012\u0004\u0012\u00020\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u000B\u001A\u0018\u0012\u0014\u0012\u0012\u0012\u000E\b\u0001\u0012\n \u000E*\u0004\u0018\u00010\r0\r0\f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00100\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*0\b\u0002\u0010\u001D\"\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u00052\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005¨\u0006\u001E"}, d2 = {"CACHE_FOR_BASE_CLASSIFIERS", "Lkotlin/reflect/jvm/internal/CacheByClass;", "Lkotlin/reflect/KType;", "CACHE_FOR_GENERIC_CLASSIFIERS", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlin/Pair;", "", "Lkotlin/reflect/KTypeProjection;", "", "Lkotlin/reflect/jvm/internal/Key;", "CACHE_FOR_NULLABLE_BASE_CLASSIFIERS", "K_CLASS_CACHE", "Lkotlin/reflect/jvm/internal/KClassImpl;", "", "kotlin.jvm.PlatformType", "K_PACKAGE_CACHE", "Lkotlin/reflect/jvm/internal/KPackageImpl;", "clearCaches", "", "getOrCreateKType", "T", "jClass", "Ljava/lang/Class;", "arguments", "isMarkedNullable", "getOrCreateKTypeWithTypeArguments", "getOrCreateKotlinClass", "getOrCreateKotlinPackage", "Lkotlin/reflect/KDeclarationContainer;", "Key", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CachesKt {
    private static final CacheByClass CACHE_FOR_BASE_CLASSIFIERS;
    private static final CacheByClass CACHE_FOR_GENERIC_CLASSIFIERS;
    private static final CacheByClass CACHE_FOR_NULLABLE_BASE_CLASSIFIERS;
    private static final CacheByClass K_CLASS_CACHE;
    private static final CacheByClass K_PACKAGE_CACHE;

    static {
        CachesKt.K_CLASS_CACHE = CacheByClassKt.createCache(CachesKt.K_CLASS_CACHE.1.INSTANCE);
        CachesKt.K_PACKAGE_CACHE = CacheByClassKt.createCache(CachesKt.K_PACKAGE_CACHE.1.INSTANCE);
        CachesKt.CACHE_FOR_BASE_CLASSIFIERS = CacheByClassKt.createCache(CachesKt.CACHE_FOR_BASE_CLASSIFIERS.1.INSTANCE);
        CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS = CacheByClassKt.createCache(CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.1.INSTANCE);
        CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS = CacheByClassKt.createCache(CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.1.INSTANCE);
    }

    public static final void clearCaches() {
        CachesKt.K_CLASS_CACHE.clear();
        CachesKt.K_PACKAGE_CACHE.clear();
        CachesKt.CACHE_FOR_BASE_CLASSIFIERS.clear();
        CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.clear();
        CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.clear();
    }

    public static final KType getOrCreateKType(Class class0, List list0, boolean z) {
        Intrinsics.checkNotNullParameter(class0, "jClass");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        if(list0.isEmpty()) {
            return z ? ((KType)CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.get(class0)) : ((KType)CachesKt.CACHE_FOR_BASE_CLASSIFIERS.get(class0));
        }
        return CachesKt.getOrCreateKTypeWithTypeArguments(class0, list0, z);
    }

    private static final KType getOrCreateKTypeWithTypeArguments(Class class0, List list0, boolean z) {
        ConcurrentMap concurrentMap0 = (ConcurrentHashMap)CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.get(class0);
        Pair pair0 = TuplesKt.to(list0, Boolean.valueOf(z));
        KType kType0 = concurrentMap0.get(pair0);
        if(kType0 == null) {
            KType kType1 = KClassifiers.createType(CachesKt.getOrCreateKotlinClass(class0), list0, z, CollectionsKt.emptyList());
            Object object0 = concurrentMap0.putIfAbsent(pair0, kType1);
            kType0 = object0 == null ? kType1 : object0;
        }
        Intrinsics.checkNotNullExpressionValue(kType0, "cache.getOrPut(arguments…lable, emptyList())\n    }");
        return kType0;
    }

    public static final KClassImpl getOrCreateKotlinClass(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "jClass");
        Object object0 = CachesKt.K_CLASS_CACHE.get(class0);
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<T of kotlin.reflect.jvm.internal.CachesKt.getOrCreateKotlinClass>");
        return (KClassImpl)object0;
    }

    public static final KDeclarationContainer getOrCreateKotlinPackage(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "jClass");
        return (KDeclarationContainer)CachesKt.K_PACKAGE_CACHE.get(class0);
    }
}

