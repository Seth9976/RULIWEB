package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

@CheckReturnValue
final class ExtensionSchemaLite extends ExtensionSchema {
    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    int extensionNumber(Map.Entry map$Entry0) {
        return ((ExtensionDescriptor)map$Entry0.getKey()).getNumber();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    Object findExtensionByNumber(ExtensionRegistryLite extensionRegistryLite0, MessageLite messageLite0, int v) {
        return extensionRegistryLite0.findLiteExtensionByNumber(messageLite0, v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    FieldSet getExtensions(Object object0) {
        return ((ExtendableMessage)object0).extensions;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    FieldSet getMutableExtensions(Object object0) {
        return ((ExtendableMessage)object0).ensureExtensionsAreMutable();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    boolean hasExtensions(MessageLite messageLite0) {
        return messageLite0 instanceof ExtendableMessage;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void makeImmutable(Object object0) {
        this.getExtensions(object0).makeImmutable();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    Object parseExtension(Object object0, Reader reader0, Object object1, ExtensionRegistryLite extensionRegistryLite0, FieldSet fieldSet0, Object object2, UnknownFieldSchema unknownFieldSchema0) throws IOException {
        Object object3;
        ArrayList arrayList0;
        GeneratedExtension generatedMessageLite$GeneratedExtension0 = (GeneratedExtension)object1;
        int v = generatedMessageLite$GeneratedExtension0.getNumber();
        if(generatedMessageLite$GeneratedExtension0.descriptor.isRepeated() && generatedMessageLite$GeneratedExtension0.descriptor.isPacked()) {
            switch(com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedMessageLite$GeneratedExtension0.getLiteType().ordinal()]) {
                case 1: {
                    arrayList0 = new ArrayList();
                    reader0.readDoubleList(arrayList0);
                    break;
                }
                case 2: {
                    arrayList0 = new ArrayList();
                    reader0.readFloatList(arrayList0);
                    break;
                }
                case 3: {
                    arrayList0 = new ArrayList();
                    reader0.readInt64List(arrayList0);
                    break;
                }
                case 4: {
                    arrayList0 = new ArrayList();
                    reader0.readUInt64List(arrayList0);
                    break;
                }
                case 5: {
                    arrayList0 = new ArrayList();
                    reader0.readInt32List(arrayList0);
                    break;
                }
                case 6: {
                    arrayList0 = new ArrayList();
                    reader0.readFixed64List(arrayList0);
                    break;
                }
                case 7: {
                    arrayList0 = new ArrayList();
                    reader0.readFixed32List(arrayList0);
                    break;
                }
                case 8: {
                    arrayList0 = new ArrayList();
                    reader0.readBoolList(arrayList0);
                    break;
                }
                case 9: {
                    arrayList0 = new ArrayList();
                    reader0.readUInt32List(arrayList0);
                    break;
                }
                case 10: {
                    arrayList0 = new ArrayList();
                    reader0.readSFixed32List(arrayList0);
                    break;
                }
                case 11: {
                    arrayList0 = new ArrayList();
                    reader0.readSFixed64List(arrayList0);
                    break;
                }
                case 12: {
                    arrayList0 = new ArrayList();
                    reader0.readSInt32List(arrayList0);
                    break;
                }
                case 13: {
                    arrayList0 = new ArrayList();
                    reader0.readSInt64List(arrayList0);
                    break;
                }
                case 14: {
                    arrayList0 = new ArrayList();
                    reader0.readEnumList(arrayList0);
                    object2 = SchemaUtil.filterUnknownEnumList(object0, v, arrayList0, generatedMessageLite$GeneratedExtension0.descriptor.getEnumType(), object2, unknownFieldSchema0);
                    break;
                }
                default: {
                    throw new IllegalStateException("Type cannot be packed: " + generatedMessageLite$GeneratedExtension0.descriptor.getLiteType());
                }
            }
            fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, arrayList0);
            return object2;
        }
        if(generatedMessageLite$GeneratedExtension0.getLiteType() == FieldType.ENUM) {
            int v1 = reader0.readInt32();
            if(generatedMessageLite$GeneratedExtension0.descriptor.getEnumType().findValueByNumber(v1) == null) {
                return SchemaUtil.storeUnknownEnum(object0, v, v1, object2, unknownFieldSchema0);
            }
            object3 = v1;
        }
        else {
            switch(com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedMessageLite$GeneratedExtension0.getLiteType().ordinal()]) {
                case 1: {
                    object3 = reader0.readDouble();
                    break;
                }
                case 2: {
                    object3 = reader0.readFloat();
                    break;
                }
                case 3: {
                    object3 = reader0.readInt64();
                    break;
                }
                case 4: {
                    object3 = reader0.readUInt64();
                    break;
                }
                case 5: {
                    object3 = reader0.readInt32();
                    break;
                }
                case 6: {
                    object3 = reader0.readFixed64();
                    break;
                }
                case 7: {
                    object3 = reader0.readFixed32();
                    break;
                }
                case 8: {
                    object3 = Boolean.valueOf(reader0.readBool());
                    break;
                }
                case 9: {
                    object3 = reader0.readUInt32();
                    break;
                }
                case 10: {
                    object3 = reader0.readSFixed32();
                    break;
                }
                case 11: {
                    object3 = reader0.readSFixed64();
                    break;
                }
                case 12: {
                    object3 = reader0.readSInt32();
                    break;
                }
                case 13: {
                    object3 = reader0.readSInt64();
                    break;
                }
                case 14: {
                    throw new IllegalStateException("Shouldn\'t reach here.");
                }
                case 15: {
                    object3 = reader0.readBytes();
                    break;
                }
                case 16: {
                    object3 = reader0.readString();
                    break;
                }
                case 17: {
                    if(!generatedMessageLite$GeneratedExtension0.isRepeated()) {
                        Object object4 = fieldSet0.getField(generatedMessageLite$GeneratedExtension0.descriptor);
                        if(object4 instanceof GeneratedMessageLite) {
                            Schema schema0 = Protobuf.getInstance().schemaFor(object4);
                            if(!((GeneratedMessageLite)object4).isMutable()) {
                                Object object5 = schema0.newInstance();
                                schema0.mergeFrom(object5, object4);
                                fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, object5);
                                object4 = object5;
                            }
                            reader0.mergeGroupField(object4, schema0, extensionRegistryLite0);
                            return object2;
                        }
                    }
                    object3 = reader0.readGroup(generatedMessageLite$GeneratedExtension0.getMessageDefaultInstance().getClass(), extensionRegistryLite0);
                    break;
                }
                case 18: {
                    if(!generatedMessageLite$GeneratedExtension0.isRepeated()) {
                        Object object6 = fieldSet0.getField(generatedMessageLite$GeneratedExtension0.descriptor);
                        if(object6 instanceof GeneratedMessageLite) {
                            Schema schema1 = Protobuf.getInstance().schemaFor(object6);
                            if(!((GeneratedMessageLite)object6).isMutable()) {
                                Object object7 = schema1.newInstance();
                                schema1.mergeFrom(object7, object6);
                                fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, object7);
                                object6 = object7;
                            }
                            reader0.mergeMessageField(object6, schema1, extensionRegistryLite0);
                            return object2;
                        }
                    }
                    object3 = reader0.readMessage(generatedMessageLite$GeneratedExtension0.getMessageDefaultInstance().getClass(), extensionRegistryLite0);
                    break;
                }
                default: {
                    object3 = null;
                    break;
                }
            }
        }
        if(generatedMessageLite$GeneratedExtension0.isRepeated()) {
            fieldSet0.addRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, object3);
            return object2;
        }
        switch(com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedMessageLite$GeneratedExtension0.getLiteType().ordinal()]) {
            case 17: 
            case 18: {
                Object object8 = fieldSet0.getField(generatedMessageLite$GeneratedExtension0.descriptor);
                if(object8 != null) {
                    object3 = Internal.mergeMessage(object8, object3);
                }
            }
        }
        fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, object3);
        return object2;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void parseLengthPrefixedMessageSetItem(Reader reader0, Object object0, ExtensionRegistryLite extensionRegistryLite0, FieldSet fieldSet0) throws IOException {
        Object object1 = reader0.readMessage(((GeneratedExtension)object0).getMessageDefaultInstance().getClass(), extensionRegistryLite0);
        fieldSet0.setField(((GeneratedExtension)object0).descriptor, object1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void parseMessageSetItem(ByteString byteString0, Object object0, ExtensionRegistryLite extensionRegistryLite0, FieldSet fieldSet0) throws IOException {
        Builder messageLite$Builder0 = ((GeneratedExtension)object0).getMessageDefaultInstance().newBuilderForType();
        CodedInputStream codedInputStream0 = byteString0.newCodedInput();
        messageLite$Builder0.mergeFrom(codedInputStream0, extensionRegistryLite0);
        MessageLite messageLite0 = messageLite$Builder0.buildPartial();
        fieldSet0.setField(((GeneratedExtension)object0).descriptor, messageLite0);
        codedInputStream0.checkLastTagWas(0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void serializeExtension(Writer writer0, Map.Entry map$Entry0) throws IOException {
        ExtensionDescriptor generatedMessageLite$ExtensionDescriptor0 = (ExtensionDescriptor)map$Entry0.getKey();
        if(generatedMessageLite$ExtensionDescriptor0.isRepeated()) {
            switch(com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedMessageLite$ExtensionDescriptor0.getLiteType().ordinal()]) {
                case 1: {
                    SchemaUtil.writeDoubleList(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 2: {
                    SchemaUtil.writeFloatList(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 3: {
                    SchemaUtil.writeInt64List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 4: {
                    SchemaUtil.writeUInt64List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 5: {
                    SchemaUtil.writeInt32List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 6: {
                    SchemaUtil.writeFixed64List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 7: {
                    SchemaUtil.writeFixed32List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 8: {
                    SchemaUtil.writeBoolList(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 9: {
                    SchemaUtil.writeUInt32List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 10: {
                    SchemaUtil.writeSFixed32List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 11: {
                    SchemaUtil.writeSFixed64List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 12: {
                    SchemaUtil.writeSInt32List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 13: {
                    SchemaUtil.writeSInt64List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 14: {
                    SchemaUtil.writeInt32List(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, generatedMessageLite$ExtensionDescriptor0.isPacked());
                    return;
                }
                case 15: {
                    SchemaUtil.writeBytesList(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0);
                    return;
                }
                case 16: {
                    SchemaUtil.writeStringList(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0);
                    return;
                }
                case 17: {
                    List list0 = (List)map$Entry0.getValue();
                    if(list0 != null && !list0.isEmpty()) {
                        SchemaUtil.writeGroupList(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, Protobuf.getInstance().schemaFor(list0.get(0).getClass()));
                        return;
                    }
                    break;
                }
                case 18: {
                    List list1 = (List)map$Entry0.getValue();
                    if(list1 != null && !list1.isEmpty()) {
                        SchemaUtil.writeMessageList(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((List)map$Entry0.getValue()), writer0, Protobuf.getInstance().schemaFor(list1.get(0).getClass()));
                        return;
                    }
                    break;
                }
            }
        }
        else {
            switch(com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedMessageLite$ExtensionDescriptor0.getLiteType().ordinal()]) {
                case 1: {
                    writer0.writeDouble(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((double)(((Double)map$Entry0.getValue()))));
                    break;
                }
                case 2: {
                    writer0.writeFloat(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((float)(((Float)map$Entry0.getValue()))));
                    return;
                }
                case 3: {
                    writer0.writeInt64(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((long)(((Long)map$Entry0.getValue()))));
                    return;
                }
                case 4: {
                    writer0.writeUInt64(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((long)(((Long)map$Entry0.getValue()))));
                    return;
                }
                case 5: {
                    writer0.writeInt32(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((int)(((Integer)map$Entry0.getValue()))));
                    return;
                }
                case 6: {
                    writer0.writeFixed64(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((long)(((Long)map$Entry0.getValue()))));
                    return;
                }
                case 7: {
                    writer0.writeFixed32(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((int)(((Integer)map$Entry0.getValue()))));
                    return;
                }
                case 8: {
                    writer0.writeBool(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((Boolean)map$Entry0.getValue()).booleanValue());
                    return;
                }
                case 9: {
                    writer0.writeUInt32(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((int)(((Integer)map$Entry0.getValue()))));
                    return;
                }
                case 10: {
                    writer0.writeSFixed32(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((int)(((Integer)map$Entry0.getValue()))));
                    return;
                }
                case 11: {
                    writer0.writeSFixed64(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((long)(((Long)map$Entry0.getValue()))));
                    return;
                }
                case 12: {
                    writer0.writeSInt32(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((int)(((Integer)map$Entry0.getValue()))));
                    return;
                }
                case 13: {
                    writer0.writeSInt64(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((long)(((Long)map$Entry0.getValue()))));
                    return;
                }
                case 14: {
                    writer0.writeInt32(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((int)(((Integer)map$Entry0.getValue()))));
                    return;
                }
                case 15: {
                    writer0.writeBytes(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((ByteString)map$Entry0.getValue()));
                    return;
                }
                case 16: {
                    writer0.writeString(generatedMessageLite$ExtensionDescriptor0.getNumber(), ((String)map$Entry0.getValue()));
                    return;
                }
                case 17: {
                    writer0.writeGroup(generatedMessageLite$ExtensionDescriptor0.getNumber(), map$Entry0.getValue(), Protobuf.getInstance().schemaFor(map$Entry0.getValue().getClass()));
                    return;
                }
                case 18: {
                    writer0.writeMessage(generatedMessageLite$ExtensionDescriptor0.getNumber(), map$Entry0.getValue(), Protobuf.getInstance().schemaFor(map$Entry0.getValue().getClass()));
                }
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ExtensionSchema
    void setExtensions(Object object0, FieldSet fieldSet0) {
        ((ExtendableMessage)object0).extensions = fieldSet0;
    }

    class com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.DOUBLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BOOL.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.GROUP.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ExtensionSchemaLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 18;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

