package kotlin.reflect.jvm.internal.impl.resolve.sam;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNullableValues;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class SamConversionResolverImpl implements SamConversionResolver {
    private final CacheWithNullableValues functionTypesForSamInterfaces;
    private final Iterable samWithReceiverResolvers;

    public SamConversionResolverImpl(StorageManager storageManager0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(iterable0, "samWithReceiverResolvers");
        super();
        this.samWithReceiverResolvers = iterable0;
        this.functionTypesForSamInterfaces = storageManager0.createCacheWithNullableValues();
    }
}

