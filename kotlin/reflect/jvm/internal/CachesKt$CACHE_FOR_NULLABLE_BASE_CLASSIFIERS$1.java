package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClassifiers;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlin/reflect/KType;", "it", "Ljava/lang/Class;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
final class CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.1 extends Lambda implements Function1 {
    public static final CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.1 INSTANCE;

    static {
        CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.1.INSTANCE = new CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.1();
    }

    CachesKt.CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.1() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((Class)object0));
    }

    public final KType invoke(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "it");
        return KClassifiers.createType(CachesKt.getOrCreateKotlinClass(class0), CollectionsKt.emptyList(), true, CollectionsKt.emptyList());
    }
}

