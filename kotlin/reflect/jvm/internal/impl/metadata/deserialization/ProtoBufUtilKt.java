package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.GeneratedExtension;

public final class ProtoBufUtilKt {
    public static final Object getExtensionOrNull(ExtendableMessage generatedMessageLite$ExtendableMessage0, GeneratedExtension generatedMessageLite$GeneratedExtension0) {
        Intrinsics.checkNotNullParameter(generatedMessageLite$ExtendableMessage0, "<this>");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension0, "extension");
        return generatedMessageLite$ExtendableMessage0.hasExtension(generatedMessageLite$GeneratedExtension0) ? generatedMessageLite$ExtendableMessage0.getExtension(generatedMessageLite$GeneratedExtension0) : null;
    }

    public static final Object getExtensionOrNull(ExtendableMessage generatedMessageLite$ExtendableMessage0, GeneratedExtension generatedMessageLite$GeneratedExtension0, int v) {
        Intrinsics.checkNotNullParameter(generatedMessageLite$ExtendableMessage0, "<this>");
        Intrinsics.checkNotNullParameter(generatedMessageLite$GeneratedExtension0, "extension");
        return v >= generatedMessageLite$ExtendableMessage0.getExtensionCount(generatedMessageLite$GeneratedExtension0) ? null : generatedMessageLite$ExtendableMessage0.getExtension(generatedMessageLite$GeneratedExtension0, v);
    }
}

