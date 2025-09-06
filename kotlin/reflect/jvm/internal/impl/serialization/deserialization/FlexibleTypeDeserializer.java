package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;

public interface FlexibleTypeDeserializer {
    public static final class ThrowException implements FlexibleTypeDeserializer {
        public static final ThrowException INSTANCE;

        static {
            ThrowException.INSTANCE = new ThrowException();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer
        public KotlinType create(Type protoBuf$Type0, String s, SimpleType simpleType0, SimpleType simpleType1) {
            Intrinsics.checkNotNullParameter(protoBuf$Type0, "proto");
            Intrinsics.checkNotNullParameter(s, "flexibleId");
            Intrinsics.checkNotNullParameter(simpleType0, "lowerBound");
            Intrinsics.checkNotNullParameter(simpleType1, "upperBound");
            throw new IllegalArgumentException("This method should not be used.");
        }
    }

    KotlinType create(Type arg1, String arg2, SimpleType arg3, SimpleType arg4);
}

