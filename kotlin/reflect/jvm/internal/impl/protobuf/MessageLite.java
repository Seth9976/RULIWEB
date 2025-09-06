package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;

public interface MessageLite extends MessageLiteOrBuilder {
    public interface Builder extends Cloneable, MessageLiteOrBuilder {
        MessageLite build();

        Builder mergeFrom(CodedInputStream arg1, ExtensionRegistryLite arg2) throws IOException;
    }

    Parser getParserForType();

    int getSerializedSize();

    Builder newBuilderForType();

    Builder toBuilder();

    void writeTo(CodedOutputStream arg1) throws IOException;
}

