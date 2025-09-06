package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class JavaFlexibleTypeDeserializer implements FlexibleTypeDeserializer {
    public static final JavaFlexibleTypeDeserializer INSTANCE;

    static {
        JavaFlexibleTypeDeserializer.INSTANCE = new JavaFlexibleTypeDeserializer();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer
    public KotlinType create(Type protoBuf$Type0, String s, SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(protoBuf$Type0, "proto");
        Intrinsics.checkNotNullParameter(s, "flexibleId");
        Intrinsics.checkNotNullParameter(simpleType0, "lowerBound");
        Intrinsics.checkNotNullParameter(simpleType1, "upperBound");
        if(!Intrinsics.areEqual(s, "kotlin.jvm.PlatformType")) {
            return ErrorUtils.createErrorType(ErrorTypeKind.ERROR_FLEXIBLE_TYPE, new String[]{s, simpleType0.toString(), simpleType1.toString()});
        }
        return protoBuf$Type0.hasExtension(JvmProtoBuf.isRaw) ? new RawTypeImpl(simpleType0, simpleType1) : KotlinTypeFactory.flexibleType(simpleType0, simpleType1);
    }
}

