package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;

@CheckReturnValue
final class ArrayDecoders {
    static final class Registers {
        public final ExtensionRegistryLite extensionRegistry;
        public int int1;
        public long long1;
        public Object object1;

        Registers() {
            this.extensionRegistry = ExtensionRegistryLite.getEmptyRegistry();
        }

        Registers(ExtensionRegistryLite extensionRegistryLite0) {
            extensionRegistryLite0.getClass();
            this.extensionRegistry = extensionRegistryLite0;
        }
    }

    static int decodeBoolList(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        int v3 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
        ((BooleanArrayList)internal$ProtobufList0).addBoolean(arrayDecoders$Registers0.long1 != 0L);
        while(v3 < v2) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            v3 = ArrayDecoders.decodeVarint64(arr_b, v4, arrayDecoders$Registers0);
            ((BooleanArrayList)internal$ProtobufList0).addBoolean(arrayDecoders$Registers0.long1 != 0L);
        }
        return v3;
    }

    static int decodeBytes(byte[] arr_b, int v, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1;
        if(v2 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if(v2 > arr_b.length - v1) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        if(v2 == 0) {
            arrayDecoders$Registers0.object1 = ByteString.EMPTY;
            return v1;
        }
        arrayDecoders$Registers0.object1 = ByteString.copyFrom(arr_b, v1, v2);
        return v1 + v2;
    }

    static int decodeBytesList(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        int v3 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
        int v4 = arrayDecoders$Registers0.int1;
        if(v4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if(v4 > arr_b.length - v3) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        if(v4 == 0) {
            internal$ProtobufList0.add(ByteString.EMPTY);
            goto label_9;
        }
        else {
            internal$ProtobufList0.add(ByteString.copyFrom(arr_b, v3, v4));
        }
    alab1:
        while(true) {
            v3 += v4;
            while(true) {
            label_9:
                if(v3 >= v2) {
                    return v3;
                }
                int v5 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
                if(v != arrayDecoders$Registers0.int1) {
                    return v3;
                }
                v3 = ArrayDecoders.decodeVarint32(arr_b, v5, arrayDecoders$Registers0);
                v4 = arrayDecoders$Registers0.int1;
                if(v4 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                if(v4 > arr_b.length - v3) {
                    break alab1;
                }
                if(v4 != 0) {
                    internal$ProtobufList0.add(ByteString.copyFrom(arr_b, v3, v4));
                    break;
                }
                internal$ProtobufList0.add(ByteString.EMPTY);
            }
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    static double decodeDouble(byte[] arr_b, int v) {
        return Double.longBitsToDouble(ArrayDecoders.decodeFixed64(arr_b, v));
    }

    static int decodeDoubleList(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        ((DoubleArrayList)internal$ProtobufList0).addDouble(ArrayDecoders.decodeDouble(arr_b, v1));
        int v3;
        for(v3 = v1 + 8; v3 < v2; v3 = v4 + 8) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            ((DoubleArrayList)internal$ProtobufList0).addDouble(ArrayDecoders.decodeDouble(arr_b, v4));
        }
        return v3;
    }

    static int decodeExtension(int v, byte[] arr_b, int v1, int v2, ExtendableMessage generatedMessageLite$ExtendableMessage0, GeneratedExtension generatedMessageLite$GeneratedExtension0, UnknownFieldSchema unknownFieldSchema0, Registers arrayDecoders$Registers0) throws IOException {
        Object object0 = null;
        FieldSet fieldSet0 = generatedMessageLite$ExtendableMessage0.extensions;
        if(generatedMessageLite$GeneratedExtension0.descriptor.isRepeated() && generatedMessageLite$GeneratedExtension0.descriptor.isPacked()) {
            switch(com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedMessageLite$GeneratedExtension0.getLiteType().ordinal()]) {
                case 1: {
                    DoubleArrayList doubleArrayList0 = new DoubleArrayList();
                    int v3 = ArrayDecoders.decodePackedDoubleList(arr_b, v1, doubleArrayList0, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, doubleArrayList0);
                    return v3;
                }
                case 2: {
                    FloatArrayList floatArrayList0 = new FloatArrayList();
                    int v4 = ArrayDecoders.decodePackedFloatList(arr_b, v1, floatArrayList0, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, floatArrayList0);
                    return v4;
                }
                case 3: 
                case 4: {
                    LongArrayList longArrayList0 = new LongArrayList();
                    int v5 = ArrayDecoders.decodePackedVarint64List(arr_b, v1, longArrayList0, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, longArrayList0);
                    return v5;
                }
                case 5: 
                case 6: {
                    IntArrayList intArrayList0 = new IntArrayList();
                    int v6 = ArrayDecoders.decodePackedVarint32List(arr_b, v1, intArrayList0, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, intArrayList0);
                    return v6;
                }
                case 7: 
                case 8: {
                    LongArrayList longArrayList1 = new LongArrayList();
                    int v7 = ArrayDecoders.decodePackedFixed64List(arr_b, v1, longArrayList1, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, longArrayList1);
                    return v7;
                }
                case 9: 
                case 10: {
                    IntArrayList intArrayList1 = new IntArrayList();
                    int v8 = ArrayDecoders.decodePackedFixed32List(arr_b, v1, intArrayList1, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, intArrayList1);
                    return v8;
                }
                case 11: {
                    BooleanArrayList booleanArrayList0 = new BooleanArrayList();
                    int v9 = ArrayDecoders.decodePackedBoolList(arr_b, v1, booleanArrayList0, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, booleanArrayList0);
                    return v9;
                }
                case 12: {
                    IntArrayList intArrayList2 = new IntArrayList();
                    int v10 = ArrayDecoders.decodePackedSInt32List(arr_b, v1, intArrayList2, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, intArrayList2);
                    return v10;
                }
                case 13: {
                    LongArrayList longArrayList2 = new LongArrayList();
                    int v11 = ArrayDecoders.decodePackedSInt64List(arr_b, v1, longArrayList2, arrayDecoders$Registers0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, longArrayList2);
                    return v11;
                }
                case 14: {
                    IntArrayList intArrayList3 = new IntArrayList();
                    int v12 = ArrayDecoders.decodePackedVarint32List(arr_b, v1, intArrayList3, arrayDecoders$Registers0);
                    SchemaUtil.filterUnknownEnumList(generatedMessageLite$ExtendableMessage0, v >>> 3, intArrayList3, generatedMessageLite$GeneratedExtension0.descriptor.getEnumType(), null, unknownFieldSchema0);
                    fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, intArrayList3);
                    return v12;
                }
                default: {
                    throw new IllegalStateException("Type cannot be packed: " + generatedMessageLite$GeneratedExtension0.descriptor.getLiteType());
                }
            }
        }
        if(generatedMessageLite$GeneratedExtension0.getLiteType() == FieldType.ENUM) {
            v1 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
            if(generatedMessageLite$GeneratedExtension0.descriptor.getEnumType().findValueByNumber(arrayDecoders$Registers0.int1) == null) {
                SchemaUtil.storeUnknownEnum(generatedMessageLite$ExtendableMessage0, v >>> 3, arrayDecoders$Registers0.int1, null, unknownFieldSchema0);
                return v1;
            }
            object0 = arrayDecoders$Registers0.int1;
        }
        else {
            switch(com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[generatedMessageLite$GeneratedExtension0.getLiteType().ordinal()]) {
                case 1: {
                    object0 = ArrayDecoders.decodeDouble(arr_b, v1);
                    v1 += 8;
                    break;
                }
                case 2: {
                    object0 = ArrayDecoders.decodeFloat(arr_b, v1);
                    v1 += 4;
                    break;
                }
                case 3: 
                case 4: {
                    v1 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
                    object0 = arrayDecoders$Registers0.long1;
                    break;
                }
                case 5: 
                case 6: {
                    v1 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
                    object0 = arrayDecoders$Registers0.int1;
                    break;
                }
                case 7: 
                case 8: {
                    object0 = ArrayDecoders.decodeFixed64(arr_b, v1);
                    v1 += 8;
                    break;
                }
                case 9: 
                case 10: {
                    object0 = ArrayDecoders.decodeFixed32(arr_b, v1);
                    v1 += 4;
                    break;
                }
                case 11: {
                    v1 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
                    object0 = Boolean.valueOf(arrayDecoders$Registers0.long1 != 0L);
                    break;
                }
                case 12: {
                    v1 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
                    object0 = (int)(-(arrayDecoders$Registers0.int1 & 1) ^ arrayDecoders$Registers0.int1 >>> 1);
                    break;
                }
                case 13: {
                    v1 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
                    object0 = (long)(-(arrayDecoders$Registers0.long1 & 1L) ^ arrayDecoders$Registers0.long1 >>> 1);
                    break;
                }
                case 14: {
                    throw new IllegalStateException("Shouldn\'t reach here.");
                }
                case 15: {
                    v1 = ArrayDecoders.decodeBytes(arr_b, v1, arrayDecoders$Registers0);
                    object0 = arrayDecoders$Registers0.object1;
                    break;
                }
                case 16: {
                    v1 = ArrayDecoders.decodeString(arr_b, v1, arrayDecoders$Registers0);
                    object0 = arrayDecoders$Registers0.object1;
                    break;
                }
                case 17: {
                    int v13 = v >>> 3 << 3 | 4;
                    Schema schema0 = Protobuf.getInstance().schemaFor(generatedMessageLite$GeneratedExtension0.getMessageDefaultInstance().getClass());
                    if(generatedMessageLite$GeneratedExtension0.isRepeated()) {
                        int v14 = ArrayDecoders.decodeGroupField(schema0, arr_b, v1, v2, v13, arrayDecoders$Registers0);
                        fieldSet0.addRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, arrayDecoders$Registers0.object1);
                        return v14;
                    }
                    Object object1 = fieldSet0.getField(generatedMessageLite$GeneratedExtension0.descriptor);
                    if(object1 == null) {
                        object1 = schema0.newInstance();
                        fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, object1);
                    }
                    return ArrayDecoders.mergeGroupField(object1, schema0, arr_b, v1, v2, v13, arrayDecoders$Registers0);
                }
                case 18: {
                    Schema schema1 = Protobuf.getInstance().schemaFor(generatedMessageLite$GeneratedExtension0.getMessageDefaultInstance().getClass());
                    if(generatedMessageLite$GeneratedExtension0.isRepeated()) {
                        int v15 = ArrayDecoders.decodeMessageField(schema1, arr_b, v1, v2, arrayDecoders$Registers0);
                        fieldSet0.addRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, arrayDecoders$Registers0.object1);
                        return v15;
                    }
                    Object object2 = fieldSet0.getField(generatedMessageLite$GeneratedExtension0.descriptor);
                    if(object2 == null) {
                        object2 = schema1.newInstance();
                        fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, object2);
                    }
                    return ArrayDecoders.mergeMessageField(object2, schema1, arr_b, v1, v2, arrayDecoders$Registers0);
                }
            }
        }
        if(generatedMessageLite$GeneratedExtension0.isRepeated()) {
            fieldSet0.addRepeatedField(generatedMessageLite$GeneratedExtension0.descriptor, object0);
            return v1;
        }
        fieldSet0.setField(generatedMessageLite$GeneratedExtension0.descriptor, object0);
        return v1;
    }

    static int decodeExtensionOrUnknownField(int v, byte[] arr_b, int v1, int v2, Object object0, MessageLite messageLite0, UnknownFieldSchema unknownFieldSchema0, Registers arrayDecoders$Registers0) throws IOException {
        GeneratedExtension generatedMessageLite$GeneratedExtension0 = arrayDecoders$Registers0.extensionRegistry.findLiteExtensionByNumber(messageLite0, v >>> 3);
        if(generatedMessageLite$GeneratedExtension0 == null) {
            return ArrayDecoders.decodeUnknownField(v, arr_b, v1, v2, MessageSchema.getMutableUnknownFields(object0), arrayDecoders$Registers0);
        }
        ((ExtendableMessage)object0).ensureExtensionsAreMutable();
        return ArrayDecoders.decodeExtension(v, arr_b, v1, v2, ((ExtendableMessage)object0), generatedMessageLite$GeneratedExtension0, unknownFieldSchema0, arrayDecoders$Registers0);
    }

    static int decodeFixed32(byte[] arr_b, int v) {
        return (arr_b[v + 3] & 0xFF) << 24 | (arr_b[v] & 0xFF | (arr_b[v + 1] & 0xFF) << 8 | (arr_b[v + 2] & 0xFF) << 16);
    }

    static int decodeFixed32List(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        ((IntArrayList)internal$ProtobufList0).addInt(ArrayDecoders.decodeFixed32(arr_b, v1));
        int v3;
        for(v3 = v1 + 4; v3 < v2; v3 = v4 + 4) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            ((IntArrayList)internal$ProtobufList0).addInt(ArrayDecoders.decodeFixed32(arr_b, v4));
        }
        return v3;
    }

    static long decodeFixed64(byte[] arr_b, int v) {
        return (((long)arr_b[v + 7]) & 0xFFL) << 56 | (((long)arr_b[v]) & 0xFFL | (((long)arr_b[v + 1]) & 0xFFL) << 8 | (((long)arr_b[v + 2]) & 0xFFL) << 16 | (((long)arr_b[v + 3]) & 0xFFL) << 24 | (((long)arr_b[v + 4]) & 0xFFL) << 0x20 | (((long)arr_b[v + 5]) & 0xFFL) << 40 | (((long)arr_b[v + 6]) & 0xFFL) << 0x30);
    }

    static int decodeFixed64List(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        ((LongArrayList)internal$ProtobufList0).addLong(ArrayDecoders.decodeFixed64(arr_b, v1));
        int v3;
        for(v3 = v1 + 8; v3 < v2; v3 = v4 + 8) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            ((LongArrayList)internal$ProtobufList0).addLong(ArrayDecoders.decodeFixed64(arr_b, v4));
        }
        return v3;
    }

    static float decodeFloat(byte[] arr_b, int v) {
        return Float.intBitsToFloat(ArrayDecoders.decodeFixed32(arr_b, v));
    }

    static int decodeFloatList(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        ((FloatArrayList)internal$ProtobufList0).addFloat(ArrayDecoders.decodeFloat(arr_b, v1));
        int v3;
        for(v3 = v1 + 4; v3 < v2; v3 = v4 + 4) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            ((FloatArrayList)internal$ProtobufList0).addFloat(ArrayDecoders.decodeFloat(arr_b, v4));
        }
        return v3;
    }

    static int decodeGroupField(Schema schema0, byte[] arr_b, int v, int v1, int v2, Registers arrayDecoders$Registers0) throws IOException {
        Object object0 = schema0.newInstance();
        int v3 = ArrayDecoders.mergeGroupField(object0, schema0, arr_b, v, v1, v2, arrayDecoders$Registers0);
        schema0.makeImmutable(object0);
        arrayDecoders$Registers0.object1 = object0;
        return v3;
    }

    static int decodeGroupList(Schema schema0, int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v3 = v & -8 | 4;
        int v4 = ArrayDecoders.decodeGroupField(schema0, arr_b, v1, v2, v3, arrayDecoders$Registers0);
        internal$ProtobufList0.add(arrayDecoders$Registers0.object1);
        while(v4 < v2) {
            int v5 = ArrayDecoders.decodeVarint32(arr_b, v4, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            v4 = ArrayDecoders.decodeGroupField(schema0, arr_b, v5, v2, v3, arrayDecoders$Registers0);
            internal$ProtobufList0.add(arrayDecoders$Registers0.object1);
        }
        return v4;
    }

    static int decodeMessageField(Schema schema0, byte[] arr_b, int v, int v1, Registers arrayDecoders$Registers0) throws IOException {
        Object object0 = schema0.newInstance();
        int v2 = ArrayDecoders.mergeMessageField(object0, schema0, arr_b, v, v1, arrayDecoders$Registers0);
        schema0.makeImmutable(object0);
        arrayDecoders$Registers0.object1 = object0;
        return v2;
    }

    static int decodeMessageList(Schema schema0, int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v3 = ArrayDecoders.decodeMessageField(schema0, arr_b, v1, v2, arrayDecoders$Registers0);
        internal$ProtobufList0.add(arrayDecoders$Registers0.object1);
        while(v3 < v2) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            v3 = ArrayDecoders.decodeMessageField(schema0, arr_b, v4, v2, arrayDecoders$Registers0);
            internal$ProtobufList0.add(arrayDecoders$Registers0.object1);
        }
        return v3;
    }

    static int decodePackedBoolList(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            v1 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
            ((BooleanArrayList)internal$ProtobufList0).addBoolean(arrayDecoders$Registers0.long1 != 0L);
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedDoubleList(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            ((DoubleArrayList)internal$ProtobufList0).addDouble(ArrayDecoders.decodeDouble(arr_b, v1));
            v1 += 8;
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedFixed32List(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            ((IntArrayList)internal$ProtobufList0).addInt(ArrayDecoders.decodeFixed32(arr_b, v1));
            v1 += 4;
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedFixed64List(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            ((LongArrayList)internal$ProtobufList0).addLong(ArrayDecoders.decodeFixed64(arr_b, v1));
            v1 += 8;
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedFloatList(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            ((FloatArrayList)internal$ProtobufList0).addFloat(ArrayDecoders.decodeFloat(arr_b, v1));
            v1 += 4;
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedSInt32List(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            v1 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
            ((IntArrayList)internal$ProtobufList0).addInt(-(arrayDecoders$Registers0.int1 & 1) ^ arrayDecoders$Registers0.int1 >>> 1);
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedSInt64List(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            v1 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
            ((LongArrayList)internal$ProtobufList0).addLong(-(arrayDecoders$Registers0.long1 & 1L) ^ arrayDecoders$Registers0.long1 >>> 1);
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedVarint32List(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            v1 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
            ((IntArrayList)internal$ProtobufList0).addInt(arrayDecoders$Registers0.int1);
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodePackedVarint64List(byte[] arr_b, int v, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws IOException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1 + v1;
        while(v1 < v2) {
            v1 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
            ((LongArrayList)internal$ProtobufList0).addLong(arrayDecoders$Registers0.long1);
        }
        if(v1 != v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return v1;
    }

    static int decodeSInt32List(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        int v3 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
        ((IntArrayList)internal$ProtobufList0).addInt(-(arrayDecoders$Registers0.int1 & 1) ^ arrayDecoders$Registers0.int1 >>> 1);
        while(v3 < v2) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            v3 = ArrayDecoders.decodeVarint32(arr_b, v4, arrayDecoders$Registers0);
            ((IntArrayList)internal$ProtobufList0).addInt(-(arrayDecoders$Registers0.int1 & 1) ^ arrayDecoders$Registers0.int1 >>> 1);
        }
        return v3;
    }

    static int decodeSInt64List(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        int v3 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
        ((LongArrayList)internal$ProtobufList0).addLong(-(arrayDecoders$Registers0.long1 & 1L) ^ arrayDecoders$Registers0.long1 >>> 1);
        while(v3 < v2) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            v3 = ArrayDecoders.decodeVarint64(arr_b, v4, arrayDecoders$Registers0);
            ((LongArrayList)internal$ProtobufList0).addLong(-(arrayDecoders$Registers0.long1 & 1L) ^ arrayDecoders$Registers0.long1 >>> 1);
        }
        return v3;
    }

    static int decodeString(byte[] arr_b, int v, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1;
        if(v2 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if(v2 == 0) {
            arrayDecoders$Registers0.object1 = "";
            return v1;
        }
        arrayDecoders$Registers0.object1 = new String(arr_b, v1, v2, Internal.UTF_8);
        return v1 + v2;
    }

    static int decodeStringList(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        int v3 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
        int v4 = arrayDecoders$Registers0.int1;
        if(v4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if(v4 == 0) {
            internal$ProtobufList0.add("");
            goto label_8;
        }
        else {
            internal$ProtobufList0.add(new String(arr_b, v3, v4, Internal.UTF_8));
        }
    alab1:
        while(true) {
            v3 += v4;
            while(true) {
            label_8:
                if(v3 >= v2) {
                    break alab1;
                }
                int v5 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
                if(v != arrayDecoders$Registers0.int1) {
                    break alab1;
                }
                v3 = ArrayDecoders.decodeVarint32(arr_b, v5, arrayDecoders$Registers0);
                v4 = arrayDecoders$Registers0.int1;
                if(v4 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                if(v4 != 0) {
                    internal$ProtobufList0.add(new String(arr_b, v3, v4, Internal.UTF_8));
                    break;
                }
                internal$ProtobufList0.add("");
            }
        }
        return v3;
    }

    static int decodeStringListRequireUtf8(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        int v3 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
        int v4 = arrayDecoders$Registers0.int1;
        if(v4 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if(v4 == 0) {
            internal$ProtobufList0.add("");
            goto label_10;
        }
        int v5 = v3 + v4;
        if(!Utf8.isValidUtf8(arr_b, v3, v5)) {
            throw InvalidProtocolBufferException.invalidUtf8();
        }
        internal$ProtobufList0.add(new String(arr_b, v3, v4, Internal.UTF_8));
    alab1:
        while(true) {
            v3 = v5;
            while(true) {
            label_10:
                if(v3 >= v2) {
                    return v3;
                }
                int v6 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
                if(v != arrayDecoders$Registers0.int1) {
                    return v3;
                }
                v3 = ArrayDecoders.decodeVarint32(arr_b, v6, arrayDecoders$Registers0);
                int v7 = arrayDecoders$Registers0.int1;
                if(v7 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                if(v7 != 0) {
                    v5 = v3 + v7;
                    if(!Utf8.isValidUtf8(arr_b, v3, v5)) {
                        break alab1;
                    }
                    internal$ProtobufList0.add(new String(arr_b, v3, v7, Internal.UTF_8));
                    break;
                }
                internal$ProtobufList0.add("");
            }
        }
        throw InvalidProtocolBufferException.invalidUtf8();
    }

    static int decodeStringRequireUtf8(byte[] arr_b, int v, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        int v1 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v2 = arrayDecoders$Registers0.int1;
        if(v2 < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        }
        if(v2 == 0) {
            arrayDecoders$Registers0.object1 = "";
            return v1;
        }
        arrayDecoders$Registers0.object1 = Utf8.decodeUtf8(arr_b, v1, v2);
        return v1 + v2;
    }

    static int decodeUnknownField(int v, byte[] arr_b, int v1, int v2, UnknownFieldSetLite unknownFieldSetLite0, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        if(v >>> 3 != 0) {
            switch(v & 7) {
                case 0: {
                    int v8 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
                    unknownFieldSetLite0.storeField(v, arrayDecoders$Registers0.long1);
                    return v8;
                }
                case 1: {
                    unknownFieldSetLite0.storeField(v, ArrayDecoders.decodeFixed64(arr_b, v1));
                    return v1 + 8;
                }
                case 2: {
                    int v6 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
                    int v7 = arrayDecoders$Registers0.int1;
                    if(v7 < 0) {
                        throw InvalidProtocolBufferException.negativeSize();
                    }
                    if(v7 > arr_b.length - v6) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    if(v7 == 0) {
                        unknownFieldSetLite0.storeField(v, ByteString.EMPTY);
                        return v6;
                    }
                    unknownFieldSetLite0.storeField(v, ByteString.copyFrom(arr_b, v6, v7));
                    return v6 + v7;
                }
                case 3: {
                    UnknownFieldSetLite unknownFieldSetLite1 = UnknownFieldSetLite.newInstance();
                    int v3 = v & -8 | 4;
                    int v4 = 0;
                    while(v1 < v2) {
                        int v5 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
                        v4 = arrayDecoders$Registers0.int1;
                        if(v4 == v3) {
                            v1 = v5;
                            break;
                        }
                        v1 = ArrayDecoders.decodeUnknownField(v4, arr_b, v5, v2, unknownFieldSetLite1, arrayDecoders$Registers0);
                    }
                    if(v1 > v2 || v4 != v3) {
                        throw InvalidProtocolBufferException.parseFailure();
                    }
                    unknownFieldSetLite0.storeField(v, unknownFieldSetLite1);
                    return v1;
                }
                case 5: {
                    unknownFieldSetLite0.storeField(v, ArrayDecoders.decodeFixed32(arr_b, v1));
                    return v1 + 4;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidTag();
                }
            }
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    static int decodeVarint32(int v, byte[] arr_b, int v1, Registers arrayDecoders$Registers0) {
        int v2 = arr_b[v1];
        if(v2 >= 0) {
            arrayDecoders$Registers0.int1 = v & 0x7F | v2 << 7;
            return v1 + 1;
        }
        int v3 = v & 0x7F | (v2 & 0x7F) << 7;
        int v4 = arr_b[v1 + 1];
        if(v4 >= 0) {
            arrayDecoders$Registers0.int1 = v3 | v4 << 14;
            return v1 + 2;
        }
        int v5 = v3 | (v4 & 0x7F) << 14;
        int v6 = arr_b[v1 + 2];
        if(v6 >= 0) {
            arrayDecoders$Registers0.int1 = v5 | v6 << 21;
            return v1 + 3;
        }
        int v7 = v5 | (v6 & 0x7F) << 21;
        int v8 = v1 + 4;
        int v9 = arr_b[v1 + 3];
        if(v9 >= 0) {
            arrayDecoders$Registers0.int1 = v7 | v9 << 28;
            return v8;
        }
        while(arr_b[v8] < 0) {
            ++v8;
        }
        arrayDecoders$Registers0.int1 = v7 | (v9 & 0x7F) << 28;
        return v8 + 1;
    }

    static int decodeVarint32(byte[] arr_b, int v, Registers arrayDecoders$Registers0) {
        int v1 = arr_b[v];
        if(v1 >= 0) {
            arrayDecoders$Registers0.int1 = v1;
            return v + 1;
        }
        return ArrayDecoders.decodeVarint32(v1, arr_b, v + 1, arrayDecoders$Registers0);
    }

    static int decodeVarint32List(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        int v3 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
        ((IntArrayList)internal$ProtobufList0).addInt(arrayDecoders$Registers0.int1);
        while(v3 < v2) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            v3 = ArrayDecoders.decodeVarint32(arr_b, v4, arrayDecoders$Registers0);
            ((IntArrayList)internal$ProtobufList0).addInt(arrayDecoders$Registers0.int1);
        }
        return v3;
    }

    static int decodeVarint64(long v, byte[] arr_b, int v1, Registers arrayDecoders$Registers0) {
        int v2 = v1 + 1;
        int v3 = arr_b[v1];
        long v4 = v & 0x7FL | ((long)(v3 & 0x7F)) << 7;
        int v5 = 7;
        while(v3 < 0) {
            int v6 = arr_b[v2];
            v5 += 7;
            v4 |= ((long)(v6 & 0x7F)) << v5;
            ++v2;
            v3 = v6;
        }
        arrayDecoders$Registers0.long1 = v4;
        return v2;
    }

    static int decodeVarint64(byte[] arr_b, int v, Registers arrayDecoders$Registers0) {
        long v1 = (long)arr_b[v];
        if(v1 >= 0L) {
            arrayDecoders$Registers0.long1 = v1;
            return v + 1;
        }
        return ArrayDecoders.decodeVarint64(v1, arr_b, v + 1, arrayDecoders$Registers0);
    }

    static int decodeVarint64List(int v, byte[] arr_b, int v1, int v2, ProtobufList internal$ProtobufList0, Registers arrayDecoders$Registers0) {
        int v3 = ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
        ((LongArrayList)internal$ProtobufList0).addLong(arrayDecoders$Registers0.long1);
        while(v3 < v2) {
            int v4 = ArrayDecoders.decodeVarint32(arr_b, v3, arrayDecoders$Registers0);
            if(v != arrayDecoders$Registers0.int1) {
                break;
            }
            v3 = ArrayDecoders.decodeVarint64(arr_b, v4, arrayDecoders$Registers0);
            ((LongArrayList)internal$ProtobufList0).addLong(arrayDecoders$Registers0.long1);
        }
        return v3;
    }

    static int mergeGroupField(Object object0, Schema schema0, byte[] arr_b, int v, int v1, int v2, Registers arrayDecoders$Registers0) throws IOException {
        int v3 = ((MessageSchema)schema0).parseProto2Message(object0, arr_b, v, v1, v2, arrayDecoders$Registers0);
        arrayDecoders$Registers0.object1 = object0;
        return v3;
    }

    static int mergeMessageField(Object object0, Schema schema0, byte[] arr_b, int v, int v1, Registers arrayDecoders$Registers0) throws IOException {
        int v2 = v + 1;
        int v3 = arr_b[v];
        if(v3 < 0) {
            v2 = ArrayDecoders.decodeVarint32(v3, arr_b, v2, arrayDecoders$Registers0);
            v3 = arrayDecoders$Registers0.int1;
        }
        if(v3 < 0 || v3 > v1 - v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int v4 = v2 + v3;
        schema0.mergeFrom(object0, arr_b, v2, v4, arrayDecoders$Registers0);
        arrayDecoders$Registers0.object1 = object0;
        return v4;
    }

    static int skipField(int v, byte[] arr_b, int v1, int v2, Registers arrayDecoders$Registers0) throws InvalidProtocolBufferException {
        if(v >>> 3 != 0) {
            switch(v & 7) {
                case 0: {
                    return ArrayDecoders.decodeVarint64(arr_b, v1, arrayDecoders$Registers0);
                }
                case 1: {
                    return v1 + 8;
                }
                case 2: {
                    return ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0) + arrayDecoders$Registers0.int1;
                }
                case 3: {
                    int v3 = v & -8 | 4;
                    int v4 = 0;
                    while(v1 < v2) {
                        v1 = ArrayDecoders.decodeVarint32(arr_b, v1, arrayDecoders$Registers0);
                        v4 = arrayDecoders$Registers0.int1;
                        if(v4 == v3) {
                            break;
                        }
                        v1 = ArrayDecoders.skipField(v4, arr_b, v1, v2, arrayDecoders$Registers0);
                    }
                    if(v1 > v2 || v4 != v3) {
                        throw InvalidProtocolBufferException.parseFailure();
                    }
                    return v1;
                }
                case 5: {
                    return v1 + 4;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidTag();
                }
            }
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    class com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.DOUBLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BOOL.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.GROUP.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.ArrayDecoders.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 18;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

