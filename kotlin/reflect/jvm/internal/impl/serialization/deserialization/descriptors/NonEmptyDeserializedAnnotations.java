package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class NonEmptyDeserializedAnnotations extends DeserializedAnnotations {
    public NonEmptyDeserializedAnnotations(StorageManager storageManager0, Function0 function00) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(function00, "compute");
        super(storageManager0, function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedAnnotations
    public boolean isEmpty() {
        return false;
    }
}

