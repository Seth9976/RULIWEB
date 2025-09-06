package kotlin.reflect.jvm.internal.impl.utils;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationConfiguration;

public final class DeserializationHelpersKt {
    public static final JvmMetadataVersion jvmMetadataVersionOrDefault(DeserializationConfiguration deserializationConfiguration0) {
        Intrinsics.checkNotNullParameter(deserializationConfiguration0, "<this>");
        BinaryVersion binaryVersion0 = deserializationConfiguration0.getBinaryVersion();
        JvmMetadataVersion jvmMetadataVersion0 = binaryVersion0 instanceof JvmMetadataVersion ? ((JvmMetadataVersion)binaryVersion0) : null;
        return jvmMetadataVersion0 == null ? JvmMetadataVersion.INSTANCE : jvmMetadataVersion0;
    }
}

