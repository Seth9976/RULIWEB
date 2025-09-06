package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.io.ByteArrayInputStream;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.protobuf.ExtensionRegistryLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;

public final class DeserializedMemberScope.OptimizedImplementation.computeDescriptors.1.1 extends Lambda implements Function0 {
    final ByteArrayInputStream $inputStream;
    final Parser $parser;

    public DeserializedMemberScope.OptimizedImplementation.computeDescriptors.1.1(Parser parser0, ByteArrayInputStream byteArrayInputStream0, DeserializedMemberScope deserializedMemberScope0) {
        this.$parser = parser0;
        this.$inputStream = byteArrayInputStream0;
        DeserializedMemberScope.this = deserializedMemberScope0;
        super(0);
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return this.invoke();
    }

    public final MessageLite invoke() {
        ExtensionRegistryLite extensionRegistryLite0 = DeserializedMemberScope.this.getC().getComponents().getExtensionRegistryLite();
        return (MessageLite)this.$parser.parseDelimitedFrom(this.$inputStream, extensionRegistryLite0);
    }
}

