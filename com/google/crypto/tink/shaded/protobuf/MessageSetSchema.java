package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.Map.Entry;

@CheckReturnValue
final class MessageSetSchema implements Schema {
    private final MessageLite defaultInstance;
    private final ExtensionSchema extensionSchema;
    private final boolean hasExtensions;
    private final UnknownFieldSchema unknownFieldSchema;

    private MessageSetSchema(UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, MessageLite messageLite0) {
        this.unknownFieldSchema = unknownFieldSchema0;
        this.hasExtensions = extensionSchema0.hasExtensions(messageLite0);
        this.extensionSchema = extensionSchema0;
        this.defaultInstance = messageLite0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(Object object0, Object object1) {
        if(!this.unknownFieldSchema.getFromMessage(object0).equals(this.unknownFieldSchema.getFromMessage(object1))) {
            return false;
        }
        return this.hasExtensions ? this.extensionSchema.getExtensions(object0).equals(this.extensionSchema.getExtensions(object1)) : true;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(Object object0) {
        int v = this.getUnknownFieldsSerializedSize(this.unknownFieldSchema, object0);
        return this.hasExtensions ? v + this.extensionSchema.getExtensions(object0).getMessageSetSerializedSize() : v;
    }

    private int getUnknownFieldsSerializedSize(UnknownFieldSchema unknownFieldSchema0, Object object0) {
        return unknownFieldSchema0.getSerializedSizeAsMessageSet(unknownFieldSchema0.getFromMessage(object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(Object object0) {
        int v = this.unknownFieldSchema.getFromMessage(object0).hashCode();
        return this.hasExtensions ? v * 53 + this.extensionSchema.getExtensions(object0).hashCode() : v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(Object object0) {
        return this.extensionSchema.getExtensions(object0).isInitialized();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(Object object0) {
        this.unknownFieldSchema.makeImmutable(object0);
        this.extensionSchema.makeImmutable(object0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(Object object0, Reader reader0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        this.mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, object0, reader0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(Object object0, Object object1) {
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, object0, object1);
        if(this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, object0, object1);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(Object object0, byte[] arr_b, int v, int v1, Registers arrayDecoders$Registers0) throws IOException {
        int v5;
        UnknownFieldSetLite unknownFieldSetLite0 = ((GeneratedMessageLite)object0).unknownFields;
        if(unknownFieldSetLite0 == UnknownFieldSetLite.getDefaultInstance()) {
            unknownFieldSetLite0 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite)object0).unknownFields = unknownFieldSetLite0;
        }
        FieldSet fieldSet0 = ((ExtendableMessage)object0).ensureExtensionsAreMutable();
        GeneratedExtension generatedMessageLite$GeneratedExtension0 = null;
        while(v < v1) {
            int v2 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
            int v3 = arrayDecoders$Registers0.int1;
            if(v3 == WireFormat.MESSAGE_SET_ITEM_TAG) {
                int v4 = 0;
                ByteString byteString0 = null;
                while(true) {
                    if(v2 < v1) {
                        v5 = ArrayDecoders.decodeVarint32(arr_b, v2, arrayDecoders$Registers0);
                        int v6 = arrayDecoders$Registers0.int1;
                        int v7 = v6 & 7;
                        switch(v6 >>> 3) {
                            case 2: {
                                if(v7 == 0) {
                                    v2 = ArrayDecoders.decodeVarint32(arr_b, v5, arrayDecoders$Registers0);
                                    v4 = arrayDecoders$Registers0.int1;
                                    generatedMessageLite$GeneratedExtension0 = (GeneratedExtension)this.extensionSchema.findExtensionByNumber(arrayDecoders$Registers0.extensionRegistry, this.defaultInstance, v4);
                                    continue;
                                }
                                break;
                            }
                            case 3: {
                                if(generatedMessageLite$GeneratedExtension0 != null) {
                                    v2 = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(generatedMessageLite$GeneratedExtension0.getMessageDefaultInstance().getClass()), arr_b, v5, v1, arrayDecoders$Registers0);
                                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, arrayDecoders$Registers0.object1);
                                    continue;
                                }
                                else if(v7 == 2) {
                                    v2 = ArrayDecoders.decodeBytes(arr_b, v5, arrayDecoders$Registers0);
                                    byteString0 = (ByteString)arrayDecoders$Registers0.object1;
                                    continue;
                                }
                            }
                        }
                        if(v6 == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                            break;
                        }
                        else {
                            v2 = ArrayDecoders.skipField(v6, arr_b, v5, v1, arrayDecoders$Registers0);
                            continue;
                        }
                    }
                    v5 = v2;
                    break;
                }
                if(byteString0 != null) {
                    unknownFieldSetLite0.storeField(v4 << 3 | 2, byteString0);
                }
                v = v5;
            }
            else if((v3 & 7) == 2) {
                generatedMessageLite$GeneratedExtension0 = (GeneratedExtension)this.extensionSchema.findExtensionByNumber(arrayDecoders$Registers0.extensionRegistry, this.defaultInstance, v3 >>> 3);
                if(generatedMessageLite$GeneratedExtension0 == null) {
                    v = ArrayDecoders.decodeUnknownField(v3, arr_b, v2, v1, unknownFieldSetLite0, arrayDecoders$Registers0);
                }
                else {
                    v = ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(generatedMessageLite$GeneratedExtension0.getMessageDefaultInstance().getClass()), arr_b, v2, v1, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, arrayDecoders$Registers0.object1);
                }
            }
            else {
                v = ArrayDecoders.skipField(v3, arr_b, v2, v1, arrayDecoders$Registers0);
            }
        }
        if(v != v1) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    private void mergeFromHelper(UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, Object object0, Reader reader0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        Object object1 = unknownFieldSchema0.getBuilderFromMessage(object0);
        FieldSet fieldSet0 = extensionSchema0.getMutableExtensions(object0);
        try {
            do {
                if(reader0.getFieldNumber() == 0x7FFFFFFF) {
                    return;
                }
            }
            while(this.parseMessageSetItemOrUnknownField(reader0, extensionRegistryLite0, extensionSchema0, fieldSet0, unknownFieldSchema0, object1));
        }
        finally {
            unknownFieldSchema0.setBuilderToMessage(object0, object1);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public Object newInstance() {
        MessageLite messageLite0 = this.defaultInstance;
        return messageLite0 instanceof GeneratedMessageLite ? ((GeneratedMessageLite)messageLite0).newMutableInstance() : messageLite0.newBuilderForType().buildPartial();
    }

    static MessageSetSchema newSchema(UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, MessageLite messageLite0) {
        return new MessageSetSchema(unknownFieldSchema0, extensionSchema0, messageLite0);
    }

    private boolean parseMessageSetItemOrUnknownField(Reader reader0, ExtensionRegistryLite extensionRegistryLite0, ExtensionSchema extensionSchema0, FieldSet fieldSet0, UnknownFieldSchema unknownFieldSchema0, Object object0) throws IOException {
        int v = reader0.getTag();
        if(v != WireFormat.MESSAGE_SET_ITEM_TAG) {
            if((v & 7) == 2) {
                Object object1 = extensionSchema0.findExtensionByNumber(extensionRegistryLite0, this.defaultInstance, v >>> 3);
                if(object1 != null) {
                    extensionSchema0.parseLengthPrefixedMessageSetItem(reader0, object1, extensionRegistryLite0, fieldSet0);
                    return true;
                }
                return unknownFieldSchema0.mergeOneFieldFrom(object0, reader0);
            }
            return reader0.skipField();
        }
        Object object2 = null;
        ByteString byteString0 = null;
        int v1 = 0;
        while(reader0.getFieldNumber() != 0x7FFFFFFF) {
            int v2 = reader0.getTag();
            if(v2 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                v1 = reader0.readUInt32();
                object2 = extensionSchema0.findExtensionByNumber(extensionRegistryLite0, this.defaultInstance, v1);
            }
            else if(v2 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if(object2 == null) {
                    byteString0 = reader0.readBytes();
                }
                else {
                    extensionSchema0.parseLengthPrefixedMessageSetItem(reader0, object2, extensionRegistryLite0, fieldSet0);
                }
            }
            else if(!reader0.skipField()) {
                break;
            }
        }
        if(reader0.getTag() != WireFormat.MESSAGE_SET_ITEM_END_TAG) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
        if(byteString0 != null) {
            if(object2 != null) {
                extensionSchema0.parseMessageSetItem(byteString0, object2, extensionRegistryLite0, fieldSet0);
                return true;
            }
            unknownFieldSchema0.addLengthDelimited(object0, v1, byteString0);
        }
        return true;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(Object object0, Writer writer0) throws IOException {
        for(Object object1: this.extensionSchema.getExtensions(object0)) {
            Map.Entry map$Entry0 = (Map.Entry)object1;
            FieldDescriptorLite fieldSet$FieldDescriptorLite0 = (FieldDescriptorLite)map$Entry0.getKey();
            if(fieldSet$FieldDescriptorLite0.getLiteJavaType() != JavaType.MESSAGE || fieldSet$FieldDescriptorLite0.isRepeated() || fieldSet$FieldDescriptorLite0.isPacked()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
            if(map$Entry0 instanceof LazyEntry) {
                writer0.writeMessageSetItem(fieldSet$FieldDescriptorLite0.getNumber(), ((LazyEntry)map$Entry0).getField().toByteString());
            }
            else {
                writer0.writeMessageSetItem(fieldSet$FieldDescriptorLite0.getNumber(), map$Entry0.getValue());
            }
        }
        this.writeUnknownFieldsHelper(this.unknownFieldSchema, object0, writer0);
    }

    private void writeUnknownFieldsHelper(UnknownFieldSchema unknownFieldSchema0, Object object0, Writer writer0) throws IOException {
        unknownFieldSchema0.writeAsMessageSetTo(unknownFieldSchema0.getFromMessage(object0), writer0);
    }
}

