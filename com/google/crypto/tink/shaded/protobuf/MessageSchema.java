package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import sun.misc.Unsafe;

@CheckReturnValue
final class MessageSchema implements Schema {
    private static final int[] EMPTY_INT_ARRAY = null;
    private static final int ENFORCE_UTF8_MASK = 0x20000000;
    private static final int FIELD_TYPE_MASK = 0xFF00000;
    private static final int INTS_PER_FIELD = 3;
    private static final int NO_PRESENCE_SENTINEL = 0xFFFFF;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 0xFFFFF;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 0x10000000;
    private static final Unsafe UNSAFE;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema unknownFieldSchema;
    private final boolean useCachedSizeField;

    static {
        MessageSchema.EMPTY_INT_ARRAY = new int[0];
        MessageSchema.UNSAFE = UnsafeUtil.getUnsafe();
    }

    private MessageSchema(int[] arr_v, Object[] arr_object, int v, int v1, MessageLite messageLite0, boolean z, boolean z1, int[] arr_v1, int v2, int v3, NewInstanceSchema newInstanceSchema0, ListFieldSchema listFieldSchema0, UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, MapFieldSchema mapFieldSchema0) {
        this.buffer = arr_v;
        this.objects = arr_object;
        this.minFieldNumber = v;
        this.maxFieldNumber = v1;
        this.lite = messageLite0 instanceof GeneratedMessageLite;
        this.proto3 = z;
        this.hasExtensions = extensionSchema0 != null && extensionSchema0.hasExtensions(messageLite0);
        this.useCachedSizeField = z1;
        this.intArray = arr_v1;
        this.checkInitializedCount = v2;
        this.repeatedFieldOffsetStart = v3;
        this.newInstanceSchema = newInstanceSchema0;
        this.listFieldSchema = listFieldSchema0;
        this.unknownFieldSchema = unknownFieldSchema0;
        this.extensionSchema = extensionSchema0;
        this.defaultInstance = messageLite0;
        this.mapFieldSchema = mapFieldSchema0;
    }

    private boolean arePresentForEquals(Object object0, Object object1, int v) {
        return this.isFieldPresent(object0, v) == this.isFieldPresent(object1, v);
    }

    private static boolean booleanAt(Object object0, long v) {
        return UnsafeUtil.getBoolean(object0, v);
    }

    private static void checkMutable(Object object0) {
        if(!MessageSchema.isMutable(object0)) {
            throw new IllegalArgumentException("Mutating immutable message: " + object0);
        }
    }

    private int decodeMapEntry(byte[] arr_b, int v, int v1, Metadata mapEntryLite$Metadata0, Map map0, Registers arrayDecoders$Registers0) throws IOException {
        int v2 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
        int v3 = arrayDecoders$Registers0.int1;
        if(v3 < 0 || v3 > v1 - v2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int v4 = v2 + v3;
        Object object0 = mapEntryLite$Metadata0.defaultKey;
        Object object1 = mapEntryLite$Metadata0.defaultValue;
        while(v2 < v4) {
            int v5 = v2 + 1;
            int v6 = arr_b[v2];
            if(v6 < 0) {
                v5 = ArrayDecoders.decodeVarint32(v6, arr_b, v5, arrayDecoders$Registers0);
                v6 = arrayDecoders$Registers0.int1;
            }
            switch(v6 >>> 3) {
                case 1: {
                    if((v6 & 7) == mapEntryLite$Metadata0.keyType.getWireType()) {
                        v2 = this.decodeMapEntryValue(arr_b, v5, v1, mapEntryLite$Metadata0.keyType, null, arrayDecoders$Registers0);
                        object0 = arrayDecoders$Registers0.object1;
                        continue;
                    }
                    break;
                }
                case 2: {
                    if((v6 & 7) == mapEntryLite$Metadata0.valueType.getWireType()) {
                        Class class0 = mapEntryLite$Metadata0.defaultValue.getClass();
                        v2 = this.decodeMapEntryValue(arr_b, v5, v1, mapEntryLite$Metadata0.valueType, class0, arrayDecoders$Registers0);
                        object1 = arrayDecoders$Registers0.object1;
                        continue;
                    }
                }
            }
            v2 = ArrayDecoders.skipField(v6, arr_b, v5, v1, arrayDecoders$Registers0);
        }
        if(v2 != v4) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        map0.put(object0, object1);
        return v4;
    }

    private int decodeMapEntryValue(byte[] arr_b, int v, int v1, FieldType wireFormat$FieldType0, Class class0, Registers arrayDecoders$Registers0) throws IOException {
        switch(com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
            case 1: {
                int v2 = ArrayDecoders.decodeVarint64(arr_b, v, arrayDecoders$Registers0);
                arrayDecoders$Registers0.object1 = Boolean.valueOf(arrayDecoders$Registers0.long1 != 0L);
                return v2;
            }
            case 2: {
                return ArrayDecoders.decodeBytes(arr_b, v, arrayDecoders$Registers0);
            }
            case 3: {
                arrayDecoders$Registers0.object1 = ArrayDecoders.decodeDouble(arr_b, v);
                return v + 8;
            }
            case 4: 
            case 5: {
                arrayDecoders$Registers0.object1 = ArrayDecoders.decodeFixed32(arr_b, v);
                return v + 4;
            }
            case 6: 
            case 7: {
                arrayDecoders$Registers0.object1 = ArrayDecoders.decodeFixed64(arr_b, v);
                return v + 8;
            }
            case 8: {
                arrayDecoders$Registers0.object1 = ArrayDecoders.decodeFloat(arr_b, v);
                return v + 4;
            }
            case 9: 
            case 10: 
            case 11: {
                int v3 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
                arrayDecoders$Registers0.object1 = arrayDecoders$Registers0.int1;
                return v3;
            }
            case 12: 
            case 13: {
                int v4 = ArrayDecoders.decodeVarint64(arr_b, v, arrayDecoders$Registers0);
                arrayDecoders$Registers0.object1 = arrayDecoders$Registers0.long1;
                return v4;
            }
            case 14: {
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor(class0), arr_b, v, v1, arrayDecoders$Registers0);
            }
            case 15: {
                int v5 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
                arrayDecoders$Registers0.object1 = (int)(-(arrayDecoders$Registers0.int1 & 1) ^ arrayDecoders$Registers0.int1 >>> 1);
                return v5;
            }
            case 16: {
                int v6 = ArrayDecoders.decodeVarint64(arr_b, v, arrayDecoders$Registers0);
                arrayDecoders$Registers0.object1 = (long)(-(arrayDecoders$Registers0.long1 & 1L) ^ arrayDecoders$Registers0.long1 >>> 1);
                return v6;
            }
            case 17: {
                return ArrayDecoders.decodeStringRequireUtf8(arr_b, v, arrayDecoders$Registers0);
            }
            default: {
                throw new RuntimeException("unsupported field type.");
            }
        }
    }

    private static double doubleAt(Object object0, long v) {
        return UnsafeUtil.getDouble(object0, v);
    }

    private boolean equals(Object object0, Object object1, int v) {
        int v1 = this.typeAndOffsetAt(v);
        long v2 = (long)(v1 & 0xFFFFF);
        switch((v1 & 0xFF00000) >>> 20) {
            case 0: {
                return this.arePresentForEquals(object0, object1, v) && Double.doubleToLongBits(UnsafeUtil.getDouble(object0, v2)) == Double.doubleToLongBits(UnsafeUtil.getDouble(object1, v2));
            }
            case 1: {
                return this.arePresentForEquals(object0, object1, v) && Float.floatToIntBits(UnsafeUtil.getFloat(object0, v2)) == Float.floatToIntBits(UnsafeUtil.getFloat(object1, v2));
            }
            case 2: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getLong(object0, v2) == UnsafeUtil.getLong(object1, v2);
            }
            case 3: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getLong(object0, v2) == UnsafeUtil.getLong(object1, v2);
            }
            case 4: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getInt(object0, v2) == UnsafeUtil.getInt(object1, v2);
            }
            case 5: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getLong(object0, v2) == UnsafeUtil.getLong(object1, v2);
            }
            case 6: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getInt(object0, v2) == UnsafeUtil.getInt(object1, v2);
            }
            case 7: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getBoolean(object0, v2) == UnsafeUtil.getBoolean(object1, v2);
            }
            case 8: {
                return this.arePresentForEquals(object0, object1, v) && SchemaUtil.safeEquals(UnsafeUtil.getObject(object0, v2), UnsafeUtil.getObject(object1, v2));
            }
            case 9: {
                return this.arePresentForEquals(object0, object1, v) && SchemaUtil.safeEquals(UnsafeUtil.getObject(object0, v2), UnsafeUtil.getObject(object1, v2));
            }
            case 10: {
                return this.arePresentForEquals(object0, object1, v) && SchemaUtil.safeEquals(UnsafeUtil.getObject(object0, v2), UnsafeUtil.getObject(object1, v2));
            }
            case 11: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getInt(object0, v2) == UnsafeUtil.getInt(object1, v2);
            }
            case 12: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getInt(object0, v2) == UnsafeUtil.getInt(object1, v2);
            }
            case 13: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getInt(object0, v2) == UnsafeUtil.getInt(object1, v2);
            }
            case 14: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getLong(object0, v2) == UnsafeUtil.getLong(object1, v2);
            }
            case 15: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getInt(object0, v2) == UnsafeUtil.getInt(object1, v2);
            }
            case 16: {
                return this.arePresentForEquals(object0, object1, v) && UnsafeUtil.getLong(object0, v2) == UnsafeUtil.getLong(object1, v2);
            }
            case 17: {
                return this.arePresentForEquals(object0, object1, v) && SchemaUtil.safeEquals(UnsafeUtil.getObject(object0, v2), UnsafeUtil.getObject(object1, v2));
            }
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 30: 
            case 0x1F: 
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 40: 
            case 41: 
            case 42: 
            case 43: 
            case 44: 
            case 45: 
            case 46: 
            case 0x2F: 
            case 0x30: 
            case 49: {
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(object0, v2), UnsafeUtil.getObject(object1, v2));
            }
            case 50: {
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(object0, v2), UnsafeUtil.getObject(object1, v2));
            }
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: 
            case 58: 
            case 59: 
            case 60: 
            case 61: 
            case 62: 
            case 0x3F: 
            case 0x40: 
            case 65: 
            case 66: 
            case 67: 
            case 68: {
                return this.isOneofCaseEqual(object0, object1, v) && SchemaUtil.safeEquals(UnsafeUtil.getObject(object0, v2), UnsafeUtil.getObject(object1, v2));
            }
            default: {
                return true;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public boolean equals(Object object0, Object object1) {
        for(int v = 0; v < this.buffer.length; v += 3) {
            if(!this.equals(object0, object1, v)) {
                return false;
            }
        }
        if(!this.unknownFieldSchema.getFromMessage(object0).equals(this.unknownFieldSchema.getFromMessage(object1))) {
            return false;
        }
        return this.hasExtensions ? this.extensionSchema.getExtensions(object0).equals(this.extensionSchema.getExtensions(object1)) : true;
    }

    private Object filterMapUnknownEnumValues(Object object0, int v, Object object1, UnknownFieldSchema unknownFieldSchema0, Object object2) {
        int v1 = this.numberAt(v);
        Object object3 = UnsafeUtil.getObject(object0, MessageSchema.offset(this.typeAndOffsetAt(v)));
        if(object3 != null) {
            EnumVerifier internal$EnumVerifier0 = this.getEnumFieldVerifier(v);
            return internal$EnumVerifier0 == null ? object1 : this.filterUnknownEnumMap(v, v1, this.mapFieldSchema.forMutableMapData(object3), internal$EnumVerifier0, object1, unknownFieldSchema0, object2);
        }
        return object1;
    }

    private Object filterUnknownEnumMap(int v, int v1, Map map0, EnumVerifier internal$EnumVerifier0, Object object0, UnknownFieldSchema unknownFieldSchema0, Object object1) {
        Object object2 = this.getMapFieldDefaultEntry(v);
        Metadata mapEntryLite$Metadata0 = this.mapFieldSchema.forMapMetadata(object2);
        Iterator iterator0 = map0.entrySet().iterator();
        while(iterator0.hasNext()) {
            Object object3 = iterator0.next();
            Map.Entry map$Entry0 = (Map.Entry)object3;
            if(!internal$EnumVerifier0.isInRange(((int)(((Integer)map$Entry0.getValue()))))) {
                if(object0 == null) {
                    object0 = unknownFieldSchema0.getBuilderFromMessage(object1);
                }
                CodedBuilder byteString$CodedBuilder0 = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(mapEntryLite$Metadata0, map$Entry0.getKey(), map$Entry0.getValue()));
                CodedOutputStream codedOutputStream0 = byteString$CodedBuilder0.getCodedOutput();
                try {
                    MapEntryLite.writeTo(codedOutputStream0, mapEntryLite$Metadata0, map$Entry0.getKey(), map$Entry0.getValue());
                }
                catch(IOException iOException0) {
                    throw new RuntimeException(iOException0);
                }
                unknownFieldSchema0.addLengthDelimited(object0, v1, byteString$CodedBuilder0.build());
                iterator0.remove();
            }
        }
        return object0;
    }

    private static float floatAt(Object object0, long v) {
        return UnsafeUtil.getFloat(object0, v);
    }

    private EnumVerifier getEnumFieldVerifier(int v) {
        return (EnumVerifier)this.objects[v / 3 * 2 + 1];
    }

    private Object getMapFieldDefaultEntry(int v) {
        return this.objects[v / 3 * 2];
    }

    private Schema getMessageFieldSchema(int v) {
        int v1 = v / 3 * 2;
        Schema schema0 = (Schema)this.objects[v1];
        if(schema0 != null) {
            return schema0;
        }
        Schema schema1 = Protobuf.getInstance().schemaFor(((Class)this.objects[v1 + 1]));
        this.objects[v1] = schema1;
        return schema1;
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object object0) {
        UnknownFieldSetLite unknownFieldSetLite0 = ((GeneratedMessageLite)object0).unknownFields;
        if(unknownFieldSetLite0 == UnknownFieldSetLite.getDefaultInstance()) {
            unknownFieldSetLite0 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite)object0).unknownFields = unknownFieldSetLite0;
        }
        return unknownFieldSetLite0;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public int getSerializedSize(Object object0) {
        return this.proto3 ? this.getSerializedSizeProto3(object0) : this.getSerializedSizeProto2(object0);
    }

    private int getSerializedSizeProto2(Object object0) {
        int v16;
        int v15;
        int v14;
        int v13;
        int v12;
        int v11;
        int v9;
        int v7;
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        int v1 = 0;
        int v2 = 0xFFFFF;
        int v3 = 0;
        for(int v = 0; v < this.buffer.length; v += 3) {
            int v4 = this.typeAndOffsetAt(v);
            int v5 = this.numberAt(v);
            int v6 = (v4 & 0xFF00000) >>> 20;
            if(v6 <= 17) {
                v7 = this.buffer[v + 2];
                int v8 = v7 & 0xFFFFF;
                v9 = 1 << (v7 >>> 20);
                if(v8 != v2) {
                    v3 = unsafe0.getInt(object0, ((long)v8));
                    v2 = v8;
                }
            }
            else {
                v7 = !this.useCachedSizeField || v6 < com.google.crypto.tink.shaded.protobuf.FieldType.DOUBLE_LIST_PACKED.id() || v6 > com.google.crypto.tink.shaded.protobuf.FieldType.SINT64_LIST_PACKED.id() ? 0 : this.buffer[v + 2] & 0xFFFFF;
                v9 = 0;
            }
            long v10 = (long)(v4 & 0xFFFFF);
            switch(v6) {
                case 0: {
                    if((v3 & v9) != 0) {
                        v11 = CodedOutputStream.computeDoubleSize(v5, 0.0);
                        v1 += v11;
                        break;
                    }
                    break;
                }
                case 1: {
                    if((v3 & v9) != 0) {
                        v11 = CodedOutputStream.computeFloatSize(v5, 0.0f);
                        v1 += v11;
                        break;
                    }
                    break;
                }
                case 2: {
                    if((v3 & v9) != 0) {
                        v11 = CodedOutputStream.computeInt64Size(v5, unsafe0.getLong(object0, v10));
                        v1 += v11;
                        break;
                    }
                    break;
                }
                case 3: {
                    if((v3 & v9) != 0) {
                        v11 = CodedOutputStream.computeUInt64Size(v5, unsafe0.getLong(object0, v10));
                        v1 += v11;
                        break;
                    }
                    break;
                }
                case 4: {
                    if((v3 & v9) != 0) {
                        v11 = CodedOutputStream.computeInt32Size(v5, unsafe0.getInt(object0, v10));
                        v1 += v11;
                        break;
                    }
                    break;
                }
                case 5: {
                    if((v3 & v9) != 0) {
                        v11 = CodedOutputStream.computeFixed64Size(v5, 0L);
                        v1 += v11;
                    }
                    break;
                }
                case 6: {
                    if((v3 & v9) != 0) {
                        v1 += CodedOutputStream.computeFixed32Size(v5, 0);
                    }
                    break;
                }
                case 7: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeBoolSize(v5, true);
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 8: {
                    if((v3 & v9) != 0) {
                        Object object1 = unsafe0.getObject(object0, v10);
                        v12 = object1 instanceof ByteString ? CodedOutputStream.computeBytesSize(v5, ((ByteString)object1)) : CodedOutputStream.computeStringSize(v5, ((String)object1));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 9: {
                    if((v3 & v9) != 0) {
                        v12 = SchemaUtil.computeSizeMessage(v5, unsafe0.getObject(object0, v10), this.getMessageFieldSchema(v));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 10: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeBytesSize(v5, ((ByteString)unsafe0.getObject(object0, v10)));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 11: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeUInt32Size(v5, unsafe0.getInt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 12: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeEnumSize(v5, unsafe0.getInt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 13: {
                    if((v3 & v9) != 0) {
                        v13 = CodedOutputStream.computeSFixed32Size(v5, 0);
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 14: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeSFixed64Size(v5, 0L);
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 15: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeSInt32Size(v5, unsafe0.getInt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 16: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeSInt64Size(v5, unsafe0.getLong(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 17: {
                    if((v3 & v9) != 0) {
                        v12 = CodedOutputStream.computeGroupSize(v5, ((MessageLite)unsafe0.getObject(object0, v10)), this.getMessageFieldSchema(v));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 18: {
                    v12 = SchemaUtil.computeSizeFixed64List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 19: {
                    v12 = SchemaUtil.computeSizeFixed32List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 20: {
                    v12 = SchemaUtil.computeSizeInt64List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 21: {
                    v12 = SchemaUtil.computeSizeUInt64List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 22: {
                    v12 = SchemaUtil.computeSizeInt32List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 23: {
                    v12 = SchemaUtil.computeSizeFixed64List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 24: {
                    v12 = SchemaUtil.computeSizeFixed32List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 25: {
                    v12 = SchemaUtil.computeSizeBoolList(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 26: {
                    v12 = SchemaUtil.computeSizeStringList(v5, ((List)unsafe0.getObject(object0, v10)));
                    v1 += v12;
                    break;
                }
                case 27: {
                    v12 = SchemaUtil.computeSizeMessageList(v5, ((List)unsafe0.getObject(object0, v10)), this.getMessageFieldSchema(v));
                    v1 += v12;
                    break;
                }
                case 28: {
                    v12 = SchemaUtil.computeSizeByteStringList(v5, ((List)unsafe0.getObject(object0, v10)));
                    v1 += v12;
                    break;
                }
                case 29: {
                    v12 = SchemaUtil.computeSizeUInt32List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 30: {
                    v12 = SchemaUtil.computeSizeEnumList(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 0x1F: {
                    v12 = SchemaUtil.computeSizeFixed32List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 0x20: {
                    v12 = SchemaUtil.computeSizeFixed64List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 33: {
                    v12 = SchemaUtil.computeSizeSInt32List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 34: {
                    v12 = SchemaUtil.computeSizeSInt64List(v5, ((List)unsafe0.getObject(object0, v10)), false);
                    v1 += v12;
                    break;
                }
                case 35: {
                    v14 = SchemaUtil.computeSizeFixed64ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 36: {
                    v14 = SchemaUtil.computeSizeFixed32ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 37: {
                    v14 = SchemaUtil.computeSizeInt64ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 38: {
                    v14 = SchemaUtil.computeSizeUInt64ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 39: {
                    v14 = SchemaUtil.computeSizeInt32ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 40: {
                    v14 = SchemaUtil.computeSizeFixed64ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 41: {
                    v14 = SchemaUtil.computeSizeFixed32ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 42: {
                    v14 = SchemaUtil.computeSizeBoolListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 43: {
                    v14 = SchemaUtil.computeSizeUInt32ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 44: {
                    v14 = SchemaUtil.computeSizeEnumListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 45: {
                    v14 = SchemaUtil.computeSizeFixed32ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 46: {
                    v14 = SchemaUtil.computeSizeFixed64ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 0x2F: {
                    v14 = SchemaUtil.computeSizeSInt32ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v15 = CodedOutputStream.computeTagSize(v5);
                        v16 = CodedOutputStream.computeUInt32SizeNoTag(v14);
                        v13 = v15 + v16 + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 0x30: {
                    v14 = SchemaUtil.computeSizeSInt64ListNoTag(((List)unsafe0.getObject(object0, v10)));
                    if(v14 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v7), v14);
                        }
                        v13 = CodedOutputStream.computeTagSize(v5) + CodedOutputStream.computeUInt32SizeNoTag(v14) + v14;
                        v1 += v13;
                        break;
                    }
                    break;
                }
                case 49: {
                    v12 = SchemaUtil.computeSizeGroupList(v5, ((List)unsafe0.getObject(object0, v10)), this.getMessageFieldSchema(v));
                    v1 += v12;
                    break;
                }
                case 50: {
                    Object object2 = unsafe0.getObject(object0, v10);
                    Object object3 = this.getMapFieldDefaultEntry(v);
                    v12 = this.mapFieldSchema.getSerializedSize(v5, object2, object3);
                    v1 += v12;
                    break;
                }
                case 51: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeDoubleSize(v5, 0.0);
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 52: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeFloatSize(v5, 0.0f);
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 53: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeInt64Size(v5, MessageSchema.oneofLongAt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 54: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeUInt64Size(v5, MessageSchema.oneofLongAt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 55: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeInt32Size(v5, MessageSchema.oneofIntAt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 56: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeFixed64Size(v5, 0L);
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 57: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v13 = CodedOutputStream.computeFixed32Size(v5, 0);
                        v1 += v13;
                    }
                    break;
                }
                case 58: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeBoolSize(v5, true);
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 59: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        Object object4 = unsafe0.getObject(object0, v10);
                        v12 = object4 instanceof ByteString ? CodedOutputStream.computeBytesSize(v5, ((ByteString)object4)) : CodedOutputStream.computeStringSize(v5, ((String)object4));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 60: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = SchemaUtil.computeSizeMessage(v5, unsafe0.getObject(object0, v10), this.getMessageFieldSchema(v));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 61: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeBytesSize(v5, ((ByteString)unsafe0.getObject(object0, v10)));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 62: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeUInt32Size(v5, MessageSchema.oneofIntAt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 0x3F: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeEnumSize(v5, MessageSchema.oneofIntAt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 0x40: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v1 += CodedOutputStream.computeSFixed32Size(v5, 0);
                    }
                    break;
                }
                case 65: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeSFixed64Size(v5, 0L);
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 66: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeSInt32Size(v5, MessageSchema.oneofIntAt(object0, v10));
                        v1 += v12;
                        break;
                    }
                    break;
                }
                case 67: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v12 = CodedOutputStream.computeSInt64Size(v5, MessageSchema.oneofLongAt(object0, v10));
                        v1 += v12;
                    }
                    break;
                }
                case 68: {
                    if(this.isOneofPresent(object0, v5, v)) {
                        v1 += CodedOutputStream.computeGroupSize(v5, ((MessageLite)unsafe0.getObject(object0, v10)), this.getMessageFieldSchema(v));
                    }
                }
            }
        }
        int v17 = v1 + this.getUnknownFieldsSerializedSize(this.unknownFieldSchema, object0);
        return this.hasExtensions ? v17 + this.extensionSchema.getExtensions(object0).getSerializedSize() : v17;
    }

    private int getSerializedSizeProto3(Object object0) {
        int v10;
        int v9;
        int v8;
        int v7;
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        int v1 = 0;
        for(int v = 0; v < this.buffer.length; v += 3) {
            int v2 = this.typeAndOffsetAt(v);
            int v3 = (v2 & 0xFF00000) >>> 20;
            int v4 = this.numberAt(v);
            long v5 = (long)(v2 & 0xFFFFF);
            int v6 = v3 < com.google.crypto.tink.shaded.protobuf.FieldType.DOUBLE_LIST_PACKED.id() || v3 > com.google.crypto.tink.shaded.protobuf.FieldType.SINT64_LIST_PACKED.id() ? 0 : this.buffer[v + 2] & 0xFFFFF;
            switch(v3) {
                case 0: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeDoubleSize(v4, 0.0);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 1: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeFloatSize(v4, 0.0f);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 2: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeInt64Size(v4, UnsafeUtil.getLong(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 3: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeUInt64Size(v4, UnsafeUtil.getLong(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 4: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeInt32Size(v4, UnsafeUtil.getInt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 5: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeFixed64Size(v4, 0L);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 6: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeFixed32Size(v4, 0);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 7: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeBoolSize(v4, true);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 8: {
                    if(this.isFieldPresent(object0, v)) {
                        Object object1 = UnsafeUtil.getObject(object0, v5);
                        v7 = object1 instanceof ByteString ? CodedOutputStream.computeBytesSize(v4, ((ByteString)object1)) : CodedOutputStream.computeStringSize(v4, ((String)object1));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 9: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = SchemaUtil.computeSizeMessage(v4, UnsafeUtil.getObject(object0, v5), this.getMessageFieldSchema(v));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 10: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeBytesSize(v4, ((ByteString)UnsafeUtil.getObject(object0, v5)));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 11: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeUInt32Size(v4, UnsafeUtil.getInt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 12: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeEnumSize(v4, UnsafeUtil.getInt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 13: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeSFixed32Size(v4, 0);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 14: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeSFixed64Size(v4, 0L);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 15: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeSInt32Size(v4, UnsafeUtil.getInt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 16: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeSInt64Size(v4, UnsafeUtil.getLong(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 17: {
                    if(this.isFieldPresent(object0, v)) {
                        v7 = CodedOutputStream.computeGroupSize(v4, ((MessageLite)UnsafeUtil.getObject(object0, v5)), this.getMessageFieldSchema(v));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 18: {
                    v7 = SchemaUtil.computeSizeFixed64List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 19: {
                    v7 = SchemaUtil.computeSizeFixed32List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 20: {
                    v7 = SchemaUtil.computeSizeInt64List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 21: {
                    v7 = SchemaUtil.computeSizeUInt64List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 22: {
                    v7 = SchemaUtil.computeSizeInt32List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 23: {
                    v7 = SchemaUtil.computeSizeFixed64List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 24: {
                    v7 = SchemaUtil.computeSizeFixed32List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 25: {
                    v7 = SchemaUtil.computeSizeBoolList(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 26: {
                    v7 = SchemaUtil.computeSizeStringList(v4, MessageSchema.listAt(object0, v5));
                    v1 += v7;
                    break;
                }
                case 27: {
                    v7 = SchemaUtil.computeSizeMessageList(v4, MessageSchema.listAt(object0, v5), this.getMessageFieldSchema(v));
                    v1 += v7;
                    break;
                }
                case 28: {
                    v7 = SchemaUtil.computeSizeByteStringList(v4, MessageSchema.listAt(object0, v5));
                    v1 += v7;
                    break;
                }
                case 29: {
                    v7 = SchemaUtil.computeSizeUInt32List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 30: {
                    v7 = SchemaUtil.computeSizeEnumList(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 0x1F: {
                    v7 = SchemaUtil.computeSizeFixed32List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 0x20: {
                    v7 = SchemaUtil.computeSizeFixed64List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 33: {
                    v7 = SchemaUtil.computeSizeSInt32List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 34: {
                    v7 = SchemaUtil.computeSizeSInt64List(v4, MessageSchema.listAt(object0, v5), false);
                    v1 += v7;
                    break;
                }
                case 35: {
                    v8 = SchemaUtil.computeSizeFixed64ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 36: {
                    v8 = SchemaUtil.computeSizeFixed32ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 37: {
                    v8 = SchemaUtil.computeSizeInt64ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 38: {
                    v8 = SchemaUtil.computeSizeUInt64ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 39: {
                    v8 = SchemaUtil.computeSizeInt32ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 40: {
                    v8 = SchemaUtil.computeSizeFixed64ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 41: {
                    v8 = SchemaUtil.computeSizeFixed32ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 42: {
                    v8 = SchemaUtil.computeSizeBoolListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 43: {
                    v8 = SchemaUtil.computeSizeUInt32ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 44: {
                    v8 = SchemaUtil.computeSizeEnumListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 45: {
                    v8 = SchemaUtil.computeSizeFixed32ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 46: {
                    v8 = SchemaUtil.computeSizeFixed64ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 0x2F: {
                    v8 = SchemaUtil.computeSizeSInt32ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v9 = CodedOutputStream.computeTagSize(v4);
                        v10 = CodedOutputStream.computeUInt32SizeNoTag(v8);
                        v7 = v9 + v10 + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 0x30: {
                    v8 = SchemaUtil.computeSizeSInt64ListNoTag(((List)unsafe0.getObject(object0, v5)));
                    if(v8 > 0) {
                        if(this.useCachedSizeField) {
                            unsafe0.putInt(object0, ((long)v6), v8);
                        }
                        v7 = CodedOutputStream.computeTagSize(v4) + CodedOutputStream.computeUInt32SizeNoTag(v8) + v8;
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 49: {
                    v7 = SchemaUtil.computeSizeGroupList(v4, MessageSchema.listAt(object0, v5), this.getMessageFieldSchema(v));
                    v1 += v7;
                    break;
                }
                case 50: {
                    Object object2 = UnsafeUtil.getObject(object0, v5);
                    Object object3 = this.getMapFieldDefaultEntry(v);
                    v7 = this.mapFieldSchema.getSerializedSize(v4, object2, object3);
                    v1 += v7;
                    break;
                }
                case 51: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeDoubleSize(v4, 0.0);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 52: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeFloatSize(v4, 0.0f);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 53: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeInt64Size(v4, MessageSchema.oneofLongAt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 54: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeUInt64Size(v4, MessageSchema.oneofLongAt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 55: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeInt32Size(v4, MessageSchema.oneofIntAt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 56: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeFixed64Size(v4, 0L);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 57: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeFixed32Size(v4, 0);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 58: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeBoolSize(v4, true);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 59: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        Object object4 = UnsafeUtil.getObject(object0, v5);
                        v7 = object4 instanceof ByteString ? CodedOutputStream.computeBytesSize(v4, ((ByteString)object4)) : CodedOutputStream.computeStringSize(v4, ((String)object4));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 60: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = SchemaUtil.computeSizeMessage(v4, UnsafeUtil.getObject(object0, v5), this.getMessageFieldSchema(v));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 61: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeBytesSize(v4, ((ByteString)UnsafeUtil.getObject(object0, v5)));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 62: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeUInt32Size(v4, MessageSchema.oneofIntAt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 0x3F: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeEnumSize(v4, MessageSchema.oneofIntAt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 0x40: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeSFixed32Size(v4, 0);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 65: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeSFixed64Size(v4, 0L);
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 66: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeSInt32Size(v4, MessageSchema.oneofIntAt(object0, v5));
                        v1 += v7;
                        break;
                    }
                    break;
                }
                case 67: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v7 = CodedOutputStream.computeSInt64Size(v4, MessageSchema.oneofLongAt(object0, v5));
                        v1 += v7;
                    }
                    break;
                }
                case 68: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        v1 += CodedOutputStream.computeGroupSize(v4, ((MessageLite)UnsafeUtil.getObject(object0, v5)), this.getMessageFieldSchema(v));
                    }
                }
            }
        }
        return v1 + this.getUnknownFieldsSerializedSize(this.unknownFieldSchema, object0);
    }

    private int getUnknownFieldsSerializedSize(UnknownFieldSchema unknownFieldSchema0, Object object0) {
        return unknownFieldSchema0.getSerializedSize(unknownFieldSchema0.getFromMessage(object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public int hashCode(Object object0) {
        int v7;
        int v6;
        int v1 = 0;
        for(int v = 0; v < this.buffer.length; v += 3) {
            int v2 = this.typeAndOffsetAt(v);
            int v3 = this.numberAt(v);
            long v4 = (long)(v2 & 0xFFFFF);
            int v5 = 37;
            switch((v2 & 0xFF00000) >>> 20) {
                case 0: {
                    v6 = v1 * 53;
                    v7 = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(object0, v4)));
                    v1 = v6 + v7;
                    break;
                }
                case 1: {
                    v6 = v1 * 53;
                    v7 = Float.floatToIntBits(UnsafeUtil.getFloat(object0, v4));
                    v1 = v6 + v7;
                    break;
                }
                case 2: {
                    v1 = v1 * 53 + Internal.hashLong(UnsafeUtil.getLong(object0, v4));
                    break;
                }
                case 3: {
                    v1 = v1 * 53 + Internal.hashLong(UnsafeUtil.getLong(object0, v4));
                    break;
                }
                case 4: {
                    v1 = v1 * 53 + UnsafeUtil.getInt(object0, v4);
                    break;
                }
                case 5: {
                    v1 = v1 * 53 + Internal.hashLong(UnsafeUtil.getLong(object0, v4));
                    break;
                }
                case 6: {
                    v1 = v1 * 53 + UnsafeUtil.getInt(object0, v4);
                    break;
                }
                case 7: {
                    v1 = v1 * 53 + Internal.hashBoolean(UnsafeUtil.getBoolean(object0, v4));
                    break;
                }
                case 8: {
                    v1 = v1 * 53 + ((String)UnsafeUtil.getObject(object0, v4)).hashCode();
                    break;
                }
                case 9: {
                    Object object1 = UnsafeUtil.getObject(object0, v4);
                    if(object1 != null) {
                        v5 = object1.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 10: {
                    v1 = v1 * 53 + UnsafeUtil.getObject(object0, v4).hashCode();
                    break;
                }
                case 11: {
                    v1 = v1 * 53 + UnsafeUtil.getInt(object0, v4);
                    break;
                }
                case 12: {
                    v1 = v1 * 53 + UnsafeUtil.getInt(object0, v4);
                    break;
                }
                case 13: {
                    v1 = v1 * 53 + UnsafeUtil.getInt(object0, v4);
                    break;
                }
                case 14: {
                    v1 = v1 * 53 + Internal.hashLong(UnsafeUtil.getLong(object0, v4));
                    break;
                }
                case 15: {
                    v1 = v1 * 53 + UnsafeUtil.getInt(object0, v4);
                    break;
                }
                case 16: {
                    v1 = v1 * 53 + Internal.hashLong(UnsafeUtil.getLong(object0, v4));
                    break;
                }
                case 17: {
                    Object object2 = UnsafeUtil.getObject(object0, v4);
                    if(object2 != null) {
                        v5 = object2.hashCode();
                    }
                    v1 = v1 * 53 + v5;
                    break;
                }
                case 18: 
                case 19: 
                case 20: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 0x2F: 
                case 0x30: 
                case 49: {
                    v1 = v1 * 53 + UnsafeUtil.getObject(object0, v4).hashCode();
                    break;
                }
                case 50: {
                    v1 = v1 * 53 + UnsafeUtil.getObject(object0, v4).hashCode();
                    break;
                }
                case 51: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Internal.hashLong(Double.doubleToLongBits(MessageSchema.oneofDoubleAt(object0, v4)));
                    }
                    break;
                }
                case 52: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Float.floatToIntBits(MessageSchema.oneofFloatAt(object0, v4));
                    }
                    break;
                }
                case 53: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Internal.hashLong(MessageSchema.oneofLongAt(object0, v4));
                    }
                    break;
                }
                case 54: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Internal.hashLong(MessageSchema.oneofLongAt(object0, v4));
                    }
                    break;
                }
                case 55: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + MessageSchema.oneofIntAt(object0, v4);
                    }
                    break;
                }
                case 56: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Internal.hashLong(MessageSchema.oneofLongAt(object0, v4));
                    }
                    break;
                }
                case 57: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + MessageSchema.oneofIntAt(object0, v4);
                    }
                    break;
                }
                case 58: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Internal.hashBoolean(MessageSchema.oneofBooleanAt(object0, v4));
                    }
                    break;
                }
                case 59: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + ((String)UnsafeUtil.getObject(object0, v4)).hashCode();
                    }
                    break;
                }
                case 60: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + UnsafeUtil.getObject(object0, v4).hashCode();
                    }
                    break;
                }
                case 61: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + UnsafeUtil.getObject(object0, v4).hashCode();
                    }
                    break;
                }
                case 62: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + MessageSchema.oneofIntAt(object0, v4);
                    }
                    break;
                }
                case 0x3F: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + MessageSchema.oneofIntAt(object0, v4);
                    }
                    break;
                }
                case 0x40: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + MessageSchema.oneofIntAt(object0, v4);
                    }
                    break;
                }
                case 65: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Internal.hashLong(MessageSchema.oneofLongAt(object0, v4));
                    }
                    break;
                }
                case 66: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + MessageSchema.oneofIntAt(object0, v4);
                    }
                    break;
                }
                case 67: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + Internal.hashLong(MessageSchema.oneofLongAt(object0, v4));
                    }
                    break;
                }
                case 68: {
                    if(this.isOneofPresent(object0, v3, v)) {
                        v1 = v1 * 53 + UnsafeUtil.getObject(object0, v4).hashCode();
                    }
                }
            }
        }
        int v8 = v1 * 53 + this.unknownFieldSchema.getFromMessage(object0).hashCode();
        return this.hasExtensions ? v8 * 53 + this.extensionSchema.getExtensions(object0).hashCode() : v8;
    }

    private static int intAt(Object object0, long v) {
        return UnsafeUtil.getInt(object0, v);
    }

    private static boolean isEnforceUtf8(int value) {
        return (value & 0x20000000) != 0;
    }

    private boolean isFieldPresent(Object object0, int v) {
        int v1 = this.presenceMaskAndOffsetAt(v);
        if(((long)(0xFFFFF & v1)) == 0xFFFFFL) {
            int v2 = this.typeAndOffsetAt(v);
            switch((v2 & 0xFF00000) >>> 20) {
                case 0: {
                    return Double.doubleToRawLongBits(UnsafeUtil.getDouble(object0, ((long)(v2 & 0xFFFFF)))) != 0L;
                }
                case 1: {
                    return Float.floatToRawIntBits(UnsafeUtil.getFloat(object0, ((long)(v2 & 0xFFFFF)))) != 0;
                }
                case 2: {
                    return UnsafeUtil.getLong(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 3: {
                    return UnsafeUtil.getLong(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 4: {
                    return UnsafeUtil.getInt(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 5: {
                    return UnsafeUtil.getLong(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 6: {
                    return UnsafeUtil.getInt(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 7: {
                    return UnsafeUtil.getBoolean(object0, ((long)(v2 & 0xFFFFF)));
                }
                case 8: {
                    Object object1 = UnsafeUtil.getObject(object0, ((long)(v2 & 0xFFFFF)));
                    if(object1 instanceof String) {
                        return !((String)object1).isEmpty();
                    }
                    if(!(object1 instanceof ByteString)) {
                        throw new IllegalArgumentException();
                    }
                    return !ByteString.EMPTY.equals(object1);
                }
                case 9: {
                    return UnsafeUtil.getObject(object0, ((long)(v2 & 0xFFFFF))) != null;
                }
                case 10: {
                    Object object2 = UnsafeUtil.getObject(object0, ((long)(v2 & 0xFFFFF)));
                    return !ByteString.EMPTY.equals(object2);
                }
                case 11: {
                    return UnsafeUtil.getInt(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 12: {
                    return UnsafeUtil.getInt(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 13: {
                    return UnsafeUtil.getInt(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 14: {
                    return UnsafeUtil.getLong(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 15: {
                    return UnsafeUtil.getInt(object0, ((long)(v2 & 0xFFFFF))) != 0;
                }
                case 16: {
                    return UnsafeUtil.getLong(object0, ((long)(v2 & 0xFFFFF))) != 0L;
                }
                case 17: {
                    return UnsafeUtil.getObject(object0, ((long)(v2 & 0xFFFFF))) != null;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
        return (UnsafeUtil.getInt(object0, ((long)(0xFFFFF & v1))) & 1 << (v1 >>> 20)) != 0;
    }

    private boolean isFieldPresent(Object object0, int v, int v1, int v2, int v3) {
        return v1 == 0xFFFFF ? this.isFieldPresent(object0, v) : (v2 & v3) != 0;
    }

    private static boolean isInitialized(Object object0, int v, Schema schema0) {
        return schema0.isInitialized(UnsafeUtil.getObject(object0, ((long)(v & 0xFFFFF))));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public final boolean isInitialized(Object object0) {
        int v10;
        int v9;
        int v = 0;
        int v1 = 0xFFFFF;
        for(int v2 = 0; v < this.checkInitializedCount; v2 = v9) {
            int v3 = this.intArray[v];
            int v4 = this.numberAt(v3);
            int v5 = this.typeAndOffsetAt(v3);
            int v6 = this.buffer[v3 + 2];
            int v7 = v6 & 0xFFFFF;
            int v8 = 1 << (v6 >>> 20);
            if(v7 == v1) {
                v10 = v1;
                v9 = v2;
            }
            else {
                if(v7 != 0xFFFFF) {
                    v2 = MessageSchema.UNSAFE.getInt(object0, ((long)v7));
                }
                v9 = v2;
                v10 = v7;
            }
            if(MessageSchema.isRequired(v5) && !this.isFieldPresent(object0, v3, v10, v9, v8)) {
                return false;
            }
            switch((v5 & 0xFF00000) >>> 20) {
                case 9: 
                case 17: {
                    if(this.isFieldPresent(object0, v3, v10, v9, v8) && !MessageSchema.isInitialized(object0, v5, this.getMessageFieldSchema(v3))) {
                        return false;
                    }
                    break;
                }
                case 27: 
                case 49: {
                    if(!this.isListInitialized(object0, v5, v3)) {
                        return false;
                    }
                    break;
                }
                case 50: {
                    if(!this.isMapInitialized(object0, v5, v3)) {
                        return false;
                    }
                    break;
                }
                case 60: 
                case 68: {
                    if(this.isOneofPresent(object0, v4, v3) && !MessageSchema.isInitialized(object0, v5, this.getMessageFieldSchema(v3))) {
                        return false;
                    }
                }
            }
            ++v;
            v1 = v10;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(object0).isInitialized();
    }

    private boolean isListInitialized(Object object0, int v, int v1) {
        List list0 = (List)UnsafeUtil.getObject(object0, ((long)(v & 0xFFFFF)));
        if(list0.isEmpty()) {
            return true;
        }
        Schema schema0 = this.getMessageFieldSchema(v1);
        for(int v2 = 0; v2 < list0.size(); ++v2) {
            if(!schema0.isInitialized(list0.get(v2))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapInitialized(Object object0, int v, int v1) {
        Object object1 = UnsafeUtil.getObject(object0, ((long)(v & 0xFFFFF)));
        Map map0 = this.mapFieldSchema.forMapData(object1);
        if(map0.isEmpty()) {
            return true;
        }
        Object object2 = this.getMapFieldDefaultEntry(v1);
        if(this.mapFieldSchema.forMapMetadata(object2).valueType.getJavaType() != JavaType.MESSAGE) {
            return true;
        }
        Schema schema0 = null;
        for(Object object3: map0.values()) {
            if(schema0 == null) {
                schema0 = Protobuf.getInstance().schemaFor(object3.getClass());
            }
            if(!schema0.isInitialized(object3)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    private static boolean isMutable(Object object0) {
        if(object0 == null) {
            return false;
        }
        return object0 instanceof GeneratedMessageLite ? ((GeneratedMessageLite)object0).isMutable() : true;
    }

    private boolean isOneofCaseEqual(Object object0, Object object1, int v) {
        int v1 = this.presenceMaskAndOffsetAt(v);
        return UnsafeUtil.getInt(object0, ((long)(v1 & 0xFFFFF))) == UnsafeUtil.getInt(object1, ((long)(v1 & 0xFFFFF)));
    }

    private boolean isOneofPresent(Object object0, int v, int v1) {
        return UnsafeUtil.getInt(object0, ((long)(this.presenceMaskAndOffsetAt(v1) & 0xFFFFF))) == v;
    }

    private static boolean isRequired(int value) {
        return (value & 0x10000000) != 0;
    }

    private static List listAt(Object object0, long v) {
        return (List)UnsafeUtil.getObject(object0, v);
    }

    private static long longAt(Object object0, long v) {
        return UnsafeUtil.getLong(object0, v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void makeImmutable(Object object0) {
        if(MessageSchema.isMutable(object0)) {
            if(object0 instanceof GeneratedMessageLite) {
                ((GeneratedMessageLite)object0).clearMemoizedSerializedSize();
                ((GeneratedMessageLite)object0).clearMemoizedHashCode();
                ((GeneratedMessageLite)object0).markImmutable();
            }
            int v = 0;
            while(v < this.buffer.length) {
                int v1 = this.typeAndOffsetAt(v);
                long v2 = (long)(v1 & 0xFFFFF);
                int v3 = (v1 & 0xFF00000) >>> 20;
                if(v3 == 9) {
                label_20:
                    if(this.isFieldPresent(object0, v)) {
                        this.getMessageFieldSchema(v).makeImmutable(MessageSchema.UNSAFE.getObject(object0, v2));
                    }
                }
                else {
                    switch(v3) {
                        case 17: {
                            goto label_20;
                        }
                        case 18: 
                        case 19: 
                        case 20: 
                        case 21: 
                        case 22: 
                        case 23: 
                        case 24: 
                        case 25: 
                        case 26: 
                        case 27: 
                        case 28: 
                        case 29: 
                        case 30: 
                        case 0x1F: 
                        case 0x20: 
                        case 33: 
                        case 34: 
                        case 35: 
                        case 36: 
                        case 37: 
                        case 38: 
                        case 39: 
                        case 40: 
                        case 41: 
                        case 42: 
                        case 43: 
                        case 44: 
                        case 45: 
                        case 46: 
                        case 0x2F: 
                        case 0x30: 
                        case 49: {
                            this.listFieldSchema.makeImmutableListAt(object0, v2);
                            break;
                        }
                        case 50: {
                            Unsafe unsafe0 = MessageSchema.UNSAFE;
                            Object object1 = unsafe0.getObject(object0, v2);
                            if(object1 != null) {
                                unsafe0.putObject(object0, v2, this.mapFieldSchema.toImmutable(object1));
                            }
                        }
                    }
                }
                v += 3;
            }
            this.unknownFieldSchema.makeImmutable(object0);
            if(this.hasExtensions) {
                this.extensionSchema.makeImmutable(object0);
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(Object object0, Reader reader0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        extensionRegistryLite0.getClass();
        MessageSchema.checkMutable(object0);
        this.mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, object0, reader0, extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(Object object0, Object object1) {
        MessageSchema.checkMutable(object0);
        object1.getClass();
        for(int v = 0; v < this.buffer.length; v += 3) {
            this.mergeSingleField(object0, object1, v);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, object0, object1);
        if(this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, object0, object1);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void mergeFrom(Object object0, byte[] arr_b, int v, int v1, Registers arrayDecoders$Registers0) throws IOException {
        if(this.proto3) {
            this.parseProto3Message(object0, arr_b, v, v1, arrayDecoders$Registers0);
            return;
        }
        this.parseProto2Message(object0, arr_b, v, v1, 0, arrayDecoders$Registers0);
    }

    private void mergeFromHelper(UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, Object object0, Reader reader0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        Schema schema1;
        EnumVerifier internal$EnumVerifier2;
        List list3;
        EnumVerifier internal$EnumVerifier1;
        List list1;
        Reader reader2;
        MessageSchema messageSchema1;
        Schema schema0;
        int v4;
        Reader reader1;
        ExtensionRegistryLite extensionRegistryLite2;
        Object object5;
        int v3;
        Object object4;
        Object object3;
        int v1;
        int v;
        MessageSchema messageSchema0 = this;
        ExtensionRegistryLite extensionRegistryLite1 = extensionRegistryLite0;
        FieldSet fieldSet0 = null;
        Object object1 = null;
        while(true) {
            try {
            label_4:
                v = reader0.getFieldNumber();
                v1 = messageSchema0.positionForFieldNumber(v);
                if(v1 < 0) {
                    goto label_7;
                }
                goto label_49;
            }
            catch(Throwable throwable0) {
                goto label_408;
            }
        label_7:
            if(v == 0x7FFFFFFF) {
                int v2 = messageSchema0.checkInitializedCount;
                Object object2 = object1;
                while(v2 < messageSchema0.repeatedFieldOffsetStart) {
                    object2 = messageSchema0.filterMapUnknownEnumValues(object0, messageSchema0.intArray[v2], object2, unknownFieldSchema0, object0);
                    ++v2;
                }
                if(object2 == null) {
                    return;
                }
                unknownFieldSchema0.setBuilderToMessage(object0, object2);
                return;
            }
            try {
                object3 = messageSchema0.hasExtensions ? extensionSchema0.findExtensionByNumber(extensionRegistryLite1, messageSchema0.defaultInstance, v) : null;
            }
            catch(Throwable throwable0) {
                object4 = object1;
                goto label_407;
            }
            if(object3 == null) {
                try {
                    object4 = object1;
                    if(!unknownFieldSchema0.shouldDiscardUnknownFields(reader0)) {
                        if(object4 == null) {
                            object4 = unknownFieldSchema0.getBuilderFromMessage(object0);
                        }
                        if(unknownFieldSchema0.mergeOneFieldFrom(object4, reader0)) {
                            object1 = object4;
                            goto label_4;
                        }
                    }
                    else if(reader0.skipField()) {
                        object1 = object4;
                        goto label_4;
                    }
                    v3 = messageSchema0.checkInitializedCount;
                    object5 = object4;
                }
                catch(Throwable throwable0) {
                    goto label_407;
                }
                while(v3 < messageSchema0.repeatedFieldOffsetStart) {
                    object5 = messageSchema0.filterMapUnknownEnumValues(object0, messageSchema0.intArray[v3], object5, unknownFieldSchema0, object0);
                    ++v3;
                }
                if(object5 == null) {
                    return;
                }
                unknownFieldSchema0.setBuilderToMessage(object0, object5);
                return;
            }
            else {
                if(fieldSet0 == null) {
                    try {
                        fieldSet0 = extensionSchema0.getMutableExtensions(object0);
                    }
                    catch(Throwable throwable0) {
                        goto label_408;
                    }
                }
                try {
                    object1 = extensionSchema0.parseExtension(object0, reader0, object3, extensionRegistryLite1, fieldSet0, object1, unknownFieldSchema0);
                    goto label_4;
                }
                catch(Throwable throwable0) {
                    object4 = object1;
                    goto label_407;
                }
            }
            try {
            label_49:
                extensionRegistryLite2 = extensionRegistryLite1;
                object4 = object1;
                reader1 = reader0;
                v4 = messageSchema0.typeAndOffsetAt(v1);
                switch((v4 & 0xFF00000) >>> 20) {
                    case 0: {
                        goto label_67;
                    }
                    case 1: {
                        goto label_70;
                    }
                    case 2: {
                        goto label_73;
                    }
                    case 3: {
                        goto label_76;
                    }
                    case 4: {
                        goto label_79;
                    }
                    case 5: {
                        goto label_82;
                    }
                    case 6: {
                        goto label_85;
                    }
                    case 7: {
                        goto label_88;
                    }
                    case 8: {
                        goto label_91;
                    }
                    case 9: {
                        goto label_94;
                    }
                    case 10: {
                        goto label_98;
                    }
                    case 11: {
                        goto label_101;
                    }
                    case 12: {
                        goto label_104;
                    }
                    case 13: {
                        goto label_112;
                    }
                    case 14: {
                        goto label_115;
                    }
                    case 15: {
                        goto label_118;
                    }
                    case 16: {
                        goto label_121;
                    }
                    case 17: {
                        goto label_124;
                    }
                    case 18: {
                        goto label_128;
                    }
                    case 19: {
                        goto label_130;
                    }
                    case 20: {
                        goto label_132;
                    }
                    case 21: {
                        goto label_134;
                    }
                    case 22: {
                        goto label_136;
                    }
                    case 23: {
                        goto label_138;
                    }
                    case 24: {
                        goto label_140;
                    }
                    case 25: {
                        goto label_142;
                    }
                    case 26: {
                        goto label_144;
                    }
                    case 27: {
                        extensionRegistryLite2 = extensionRegistryLite0;
                        schema0 = messageSchema0.getMessageFieldSchema(v1);
                        extensionRegistryLite2 = extensionRegistryLite0;
                        goto label_152;
                    }
                    case 28: {
                        goto label_154;
                    }
                    case 29: {
                        goto label_158;
                    }
                    case 30: {
                        goto label_165;
                    }
                    case 0x1F: {
                        goto label_178;
                    }
                    case 0x20: {
                        goto label_183;
                    }
                    case 33: {
                        goto label_188;
                    }
                    case 34: {
                        goto label_193;
                    }
                    case 35: {
                        goto label_198;
                    }
                    case 36: {
                        goto label_203;
                    }
                    case 37: {
                        goto label_208;
                    }
                    case 38: {
                        goto label_213;
                    }
                    case 39: {
                        goto label_218;
                    }
                    case 40: {
                        goto label_223;
                    }
                    case 41: {
                        goto label_228;
                    }
                    case 42: {
                        goto label_233;
                    }
                    case 43: {
                        goto label_238;
                    }
                    case 44: {
                        goto label_248;
                    }
                    case 45: {
                        goto label_271;
                    }
                    case 46: {
                        goto label_275;
                    }
                    case 0x2F: {
                        goto label_279;
                    }
                    case 0x30: {
                        goto label_283;
                    }
                    case 49: {
                        schema1 = messageSchema0.getMessageFieldSchema(v1);
                        goto label_300;
                    }
                    case 50: {
                        goto label_312;
                    }
                    case 51: {
                        goto label_318;
                    }
                    case 52: {
                        goto label_321;
                    }
                    case 53: {
                        goto label_324;
                    }
                    case 54: {
                        goto label_327;
                    }
                    case 55: {
                        goto label_330;
                    }
                    case 56: {
                        goto label_333;
                    }
                    case 57: {
                        goto label_336;
                    }
                    case 58: {
                        goto label_339;
                    }
                    case 59: {
                        goto label_342;
                    }
                    case 60: {
                        goto label_345;
                    }
                    case 61: {
                        goto label_349;
                    }
                    case 62: {
                        goto label_352;
                    }
                    case 0x3F: {
                        goto label_355;
                    }
                    case 0x40: {
                        goto label_363;
                    }
                    case 65: {
                        goto label_366;
                    }
                    case 66: {
                        goto label_369;
                    }
                    case 67: {
                        goto label_372;
                    }
                    case 68: {
                        goto label_375;
                    }
                }
            }
            catch(Throwable throwable0) {
                goto label_407;
            }
            if(object4 == null) {
                try {
                    object1 = unknownFieldSchema0.getBuilderFromMessage(object0);
                }
                catch(InvalidWireTypeException unused_ex) {
                    goto label_379;
                }
                catch(Throwable throwable0) {
                    goto label_407;
                }
            }
            else {
                object1 = object4;
            }
            try {
                if(!unknownFieldSchema0.mergeOneFieldFrom(object1, reader1)) {
                    goto label_59;
                }
                goto label_419;
            }
            catch(InvalidWireTypeException unused_ex) {
                goto label_380;
            }
            catch(Throwable throwable0) {
                goto label_408;
            }
        label_59:
            int v5 = messageSchema0.checkInitializedCount;
            Object object6 = object1;
            while(v5 < messageSchema0.repeatedFieldOffsetStart) {
                object6 = messageSchema0.filterMapUnknownEnumValues(object0, messageSchema0.intArray[v5], object6, unknownFieldSchema0, object0);
                ++v5;
            }
            if(object6 == null) {
                return;
            }
            unknownFieldSchema0.setBuilderToMessage(object0, object6);
            return;
            try {
                try {
                label_67:
                    UnsafeUtil.putDouble(object0, ((long)(v4 & 0xFFFFF)), reader1.readDouble());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_70:
                    UnsafeUtil.putFloat(object0, ((long)(v4 & 0xFFFFF)), reader1.readFloat());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_73:
                    UnsafeUtil.putLong(object0, ((long)(v4 & 0xFFFFF)), reader1.readInt64());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_76:
                    UnsafeUtil.putLong(object0, ((long)(v4 & 0xFFFFF)), reader1.readUInt64());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_79:
                    UnsafeUtil.putInt(object0, ((long)(v4 & 0xFFFFF)), reader1.readInt32());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_82:
                    UnsafeUtil.putLong(object0, ((long)(v4 & 0xFFFFF)), reader1.readFixed64());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_85:
                    UnsafeUtil.putInt(object0, ((long)(v4 & 0xFFFFF)), reader1.readFixed32());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_88:
                    UnsafeUtil.putBoolean(object0, ((long)(v4 & 0xFFFFF)), reader1.readBool());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_91:
                    messageSchema0.readString(object0, v4, reader1);
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_94:
                    MessageLite messageLite0 = (MessageLite)messageSchema0.mutableMessageFieldForMerge(object0, v1);
                    reader1.mergeMessageField(messageLite0, messageSchema0.getMessageFieldSchema(v1), extensionRegistryLite2);
                    messageSchema0.storeMessageField(object0, v1, messageLite0);
                    goto label_418;
                label_98:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readBytes());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_101:
                    UnsafeUtil.putInt(object0, ((long)(v4 & 0xFFFFF)), reader1.readUInt32());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_104:
                    int v6 = reader1.readEnum();
                    EnumVerifier internal$EnumVerifier0 = messageSchema0.getEnumFieldVerifier(v1);
                    if(internal$EnumVerifier0 == null || internal$EnumVerifier0.isInRange(v6)) {
                        UnsafeUtil.putInt(object0, ((long)(v4 & 0xFFFFF)), v6);
                        messageSchema0.setFieldPresent(object0, v1);
                        goto label_418;
                    }
                    else {
                        object1 = SchemaUtil.storeUnknownEnum(object0, v, v6, object4, unknownFieldSchema0);
                        goto label_419;
                    }
                label_112:
                    UnsafeUtil.putInt(object0, ((long)(v4 & 0xFFFFF)), reader1.readSFixed32());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_115:
                    UnsafeUtil.putLong(object0, ((long)(v4 & 0xFFFFF)), reader1.readSFixed64());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_118:
                    UnsafeUtil.putInt(object0, ((long)(v4 & 0xFFFFF)), reader1.readSInt32());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_121:
                    UnsafeUtil.putLong(object0, ((long)(v4 & 0xFFFFF)), reader1.readSInt64());
                    messageSchema0.setFieldPresent(object0, v1);
                    goto label_418;
                label_124:
                    MessageLite messageLite1 = (MessageLite)messageSchema0.mutableMessageFieldForMerge(object0, v1);
                    reader1.mergeGroupField(messageLite1, messageSchema0.getMessageFieldSchema(v1), extensionRegistryLite2);
                    messageSchema0.storeMessageField(object0, v1, messageLite1);
                    goto label_418;
                label_128:
                    reader1.readDoubleList(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_130:
                    reader1.readFloatList(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_132:
                    reader1.readInt64List(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_134:
                    reader1.readUInt64List(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_136:
                    reader1.readInt32List(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_138:
                    reader1.readFixed64List(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_140:
                    reader1.readFixed32List(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_142:
                    reader1.readBoolList(messageSchema0.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    goto label_418;
                label_144:
                    messageSchema0.readStringList(object0, v4, reader1);
                    goto label_418;
                }
                catch(InvalidWireTypeException unused_ex) {
                    goto label_379;
                }
                try {
                    extensionRegistryLite2 = extensionRegistryLite0;
                    schema0 = messageSchema0.getMessageFieldSchema(v1);
                    extensionRegistryLite2 = extensionRegistryLite0;
                    goto label_152;
                }
                catch(InvalidWireTypeException unused_ex) {
                    try {
                        object1 = object4;
                        goto label_380;
                    label_152:
                        messageSchema0.readMessageList(object0, v4, reader1, schema0, extensionRegistryLite2);
                        goto label_418;
                    }
                    catch(InvalidWireTypeException unused_ex) {
                        goto label_379;
                    }
                }
            }
            catch(Throwable throwable0) {
                goto label_407;
            }
            try {
            label_154:
                messageSchema1 = messageSchema0;
                reader2 = reader1;
                reader2.readBytesList(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                extensionRegistryLite2 = extensionRegistryLite0;
                messageSchema0 = messageSchema1;
                goto label_418;
            label_158:
                messageSchema1 = messageSchema0;
                reader2 = reader1;
                reader2.readUInt32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                extensionRegistryLite2 = extensionRegistryLite0;
                messageSchema0 = messageSchema1;
                goto label_418;
            }
            catch(InvalidWireTypeException unused_ex) {
                extensionRegistryLite2 = extensionRegistryLite0;
                object1 = object4;
                messageSchema0 = messageSchema1;
                reader1 = reader2;
                goto label_380;
                try {
                    try {
                    label_165:
                        messageSchema1 = messageSchema0;
                        reader2 = reader1;
                        object1 = object4;
                        List list0 = messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF)));
                        reader2.readEnumList(list0);
                        list1 = list0;
                        internal$EnumVerifier1 = messageSchema1.getEnumFieldVerifier(v1);
                    }
                    catch(InvalidWireTypeException unused_ex) {
                        extensionRegistryLite2 = extensionRegistryLite0;
                        messageSchema0 = messageSchema1;
                        reader1 = reader2;
                        goto label_380;
                    }
                    try {
                        object1 = SchemaUtil.filterUnknownEnumList(object0, v, list1, internal$EnumVerifier1, object1, unknownFieldSchema0);
                        extensionRegistryLite2 = extensionRegistryLite0;
                        messageSchema0 = messageSchema1;
                        goto label_419;
                    }
                    catch(InvalidWireTypeException unused_ex) {
                    }
                }
                catch(Throwable throwable0) {
                    messageSchema0 = messageSchema1;
                    goto label_408;
                }
                messageSchema0 = messageSchema1;
                reader1 = reader2;
                extensionRegistryLite2 = extensionRegistryLite0;
                goto label_380;
                try {
                label_178:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readSFixed32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_183:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readSFixed64List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_188:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readSInt32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_193:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readSInt64List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_198:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readDoubleList(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_203:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readFloatList(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_208:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readInt64List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_213:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readUInt64List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_218:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readInt32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_223:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readFixed64List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_228:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readFixed32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_233:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readBoolList(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_238:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    object1 = object4;
                    reader2.readUInt32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    object4 = object1;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                }
                catch(InvalidWireTypeException unused_ex) {
                    extensionRegistryLite2 = extensionRegistryLite0;
                    messageSchema0 = messageSchema1;
                    reader1 = reader2;
                    goto label_380;
                    try {
                    label_248:
                        messageSchema1 = messageSchema0;
                        reader2 = reader1;
                        List list2 = messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF)));
                        reader2.readEnumList(list2);
                        list3 = list2;
                        object1 = object4;
                        internal$EnumVerifier2 = messageSchema1.getEnumFieldVerifier(v1);
                        object1 = object4;
                        object1 = SchemaUtil.filterUnknownEnumList(object0, v, list3, internal$EnumVerifier2, object1, unknownFieldSchema0);
                        extensionRegistryLite2 = extensionRegistryLite0;
                        messageSchema0 = messageSchema1;
                        goto label_419;
                    }
                    catch(InvalidWireTypeException unused_ex) {
                    }
                    catch(Throwable throwable0) {
                        messageSchema0 = messageSchema1;
                        goto label_408;
                    }
                    object1 = object4;
                    extensionRegistryLite2 = extensionRegistryLite0;
                    messageSchema0 = messageSchema1;
                    reader1 = reader2;
                    goto label_380;
                }
                catch(Throwable throwable0) {
                    messageSchema0 = messageSchema1;
                    goto label_408;
                }
                extensionRegistryLite2 = extensionRegistryLite0;
                messageSchema0 = messageSchema1;
                goto label_419;
                try {
                label_271:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    reader2.readSFixed32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_275:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    reader2.readSFixed64List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_279:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    reader2.readSInt32List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                label_283:
                    messageSchema1 = messageSchema0;
                    reader2 = reader1;
                    reader2.readSInt64List(messageSchema1.listFieldSchema.mutableListAt(object0, ((long)(v4 & 0xFFFFF))));
                    extensionRegistryLite2 = extensionRegistryLite0;
                    messageSchema0 = messageSchema1;
                    goto label_418;
                }
                catch(InvalidWireTypeException unused_ex) {
                }
                catch(Throwable throwable0) {
                    object1 = object4;
                    messageSchema0 = messageSchema1;
                    goto label_408;
                }
                extensionRegistryLite2 = extensionRegistryLite0;
                object1 = object4;
                messageSchema0 = messageSchema1;
                reader1 = reader2;
                goto label_380;
                try {
                    schema1 = messageSchema0.getMessageFieldSchema(v1);
                    goto label_300;
                }
                catch(InvalidWireTypeException unused_ex) {
                    reader1 = reader0;
                    extensionRegistryLite2 = extensionRegistryLite0;
                    goto label_379;
                }
                catch(Throwable throwable0) {
                }
                messageSchema1 = messageSchema0;
                object1 = object4;
                messageSchema0 = messageSchema1;
                goto label_408;
                try {
                label_300:
                    messageSchema1 = messageSchema0;
                    messageSchema0.readGroupList(object0, ((long)(v4 & 0xFFFFF)), reader0, schema1, extensionRegistryLite0);
                    extensionRegistryLite2 = extensionRegistryLite0;
                    goto label_418;
                }
                catch(InvalidWireTypeException unused_ex) {
                }
                catch(Throwable throwable0) {
                    object1 = object4;
                    messageSchema0 = messageSchema1;
                    goto label_408;
                }
                extensionRegistryLite2 = extensionRegistryLite0;
                reader1 = reader0;
                goto label_379;
            }
            catch(Throwable throwable0) {
            }
            object1 = object4;
            messageSchema0 = messageSchema1;
            goto label_408;
            try {
            label_312:
                messageSchema0.mergeMap(object0, v1, messageSchema0.getMapFieldDefaultEntry(v1), extensionRegistryLite2, reader0);
                extensionRegistryLite2 = extensionRegistryLite0;
                goto label_418;
            }
            catch(InvalidWireTypeException unused_ex) {
                reader1 = reader0;
                extensionRegistryLite2 = extensionRegistryLite0;
                goto label_379;
                try {
                label_318:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readDouble());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_321:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readFloat());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_324:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readInt64());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_327:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readUInt64());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_330:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readInt32());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_333:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readFixed64());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_336:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readFixed32());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_339:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), Boolean.valueOf(reader1.readBool()));
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_342:
                    messageSchema0.readString(object0, v4, reader1);
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_345:
                    MessageLite messageLite2 = (MessageLite)messageSchema0.mutableOneofMessageFieldForMerge(object0, v, v1);
                    reader1.mergeMessageField(messageLite2, messageSchema0.getMessageFieldSchema(v1), extensionRegistryLite2);
                    messageSchema0.storeOneofMessageField(object0, v, v1, messageLite2);
                    goto label_418;
                label_349:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readBytes());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_352:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readUInt32());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_355:
                    int v7 = reader1.readEnum();
                    EnumVerifier internal$EnumVerifier3 = messageSchema0.getEnumFieldVerifier(v1);
                    if(internal$EnumVerifier3 == null || internal$EnumVerifier3.isInRange(v7)) {
                        UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), v7);
                        messageSchema0.setOneofPresent(object0, v, v1);
                        goto label_418;
                    }
                    else {
                        object1 = SchemaUtil.storeUnknownEnum(object0, v, v7, object4, unknownFieldSchema0);
                        goto label_419;
                    }
                label_363:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readSFixed32());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_366:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readSFixed64());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_369:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readSInt32());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_372:
                    UnsafeUtil.putObject(object0, ((long)(v4 & 0xFFFFF)), reader1.readSInt64());
                    messageSchema0.setOneofPresent(object0, v, v1);
                    goto label_418;
                label_375:
                    MessageLite messageLite3 = (MessageLite)messageSchema0.mutableOneofMessageFieldForMerge(object0, v, v1);
                    reader1.mergeGroupField(messageLite3, messageSchema0.getMessageFieldSchema(v1), extensionRegistryLite2);
                    messageSchema0.storeOneofMessageField(object0, v, v1, messageLite3);
                    goto label_418;
                }
                catch(InvalidWireTypeException unused_ex) {
                }
                catch(Throwable throwable0) {
                    goto label_407;
                }
            label_379:
                object1 = object4;
                try {
                label_380:
                    if(unknownFieldSchema0.shouldDiscardUnknownFields(reader1)) {
                        goto label_392;
                    }
                    else {
                        if(object1 == null) {
                            object1 = unknownFieldSchema0.getBuilderFromMessage(object0);
                        }
                        if(!unknownFieldSchema0.mergeOneFieldFrom(object1, reader1)) {
                            goto label_384;
                        }
                    }
                    goto label_419;
                }
                catch(Throwable throwable0) {
                    goto label_408;
                }
            label_384:
                int v8 = messageSchema0.checkInitializedCount;
                object6 = object1;
                while(v8 < messageSchema0.repeatedFieldOffsetStart) {
                    object6 = messageSchema0.filterMapUnknownEnumValues(object0, messageSchema0.intArray[v8], object6, unknownFieldSchema0, object0);
                    ++v8;
                }
                if(object6 != null) {
                    unknownFieldSchema0.setBuilderToMessage(object0, object6);
                    return;
                }
                return;
                try {
                label_392:
                    if(reader1.skipField()) {
                        goto label_419;
                    }
                    else {
                        goto label_396;
                    }
                    return;
                }
                catch(Throwable throwable0) {
                    goto label_408;
                }
            label_396:
                int v9 = messageSchema0.checkInitializedCount;
                object6 = object1;
                while(v9 < messageSchema0.repeatedFieldOffsetStart) {
                    object6 = messageSchema0.filterMapUnknownEnumValues(object0, messageSchema0.intArray[v9], object6, unknownFieldSchema0, object0);
                    ++v9;
                }
                if(object6 != null) {
                    unknownFieldSchema0.setBuilderToMessage(object0, object6);
                    return;
                }
                return;
            }
            catch(Throwable throwable0) {
            }
        label_407:
            object1 = object4;
        label_408:
            int v10 = messageSchema0.checkInitializedCount;
            Object object7 = object1;
            while(v10 < messageSchema0.repeatedFieldOffsetStart) {
                object7 = messageSchema0.filterMapUnknownEnumValues(object0, messageSchema0.intArray[v10], object7, unknownFieldSchema0, object0);
                ++v10;
                messageSchema0 = this;
            }
            if(object7 != null) {
                unknownFieldSchema0.setBuilderToMessage(object0, object7);
            }
            throw throwable0;
        label_418:
            object1 = object4;
        label_419:
            extensionRegistryLite1 = extensionRegistryLite2;
        }
    }

    private final void mergeMap(Object object0, int v, Object object1, ExtensionRegistryLite extensionRegistryLite0, Reader reader0) throws IOException {
        long v1 = MessageSchema.offset(this.typeAndOffsetAt(v));
        Object object2 = UnsafeUtil.getObject(object0, v1);
        if(object2 == null) {
            object2 = this.mapFieldSchema.newMapField(object1);
            UnsafeUtil.putObject(object0, v1, object2);
        }
        else if(this.mapFieldSchema.isImmutable(object2)) {
            Object object3 = this.mapFieldSchema.newMapField(object1);
            this.mapFieldSchema.mergeFrom(object3, object2);
            UnsafeUtil.putObject(object0, v1, object3);
            object2 = object3;
        }
        reader0.readMap(this.mapFieldSchema.forMutableMapData(object2), this.mapFieldSchema.forMapMetadata(object1), extensionRegistryLite0);
    }

    private void mergeMessage(Object object0, Object object1, int v) {
        if(!this.isFieldPresent(object1, v)) {
            return;
        }
        long v1 = MessageSchema.offset(this.typeAndOffsetAt(v));
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        Object object2 = unsafe0.getObject(object1, v1);
        if(object2 == null) {
            throw new IllegalStateException("Source subfield " + this.numberAt(v) + " is present but null: " + object1);
        }
        Schema schema0 = this.getMessageFieldSchema(v);
        if(!this.isFieldPresent(object0, v)) {
            if(MessageSchema.isMutable(object2)) {
                Object object3 = schema0.newInstance();
                schema0.mergeFrom(object3, object2);
                unsafe0.putObject(object0, v1, object3);
            }
            else {
                unsafe0.putObject(object0, v1, object2);
            }
            this.setFieldPresent(object0, v);
            return;
        }
        Object object4 = unsafe0.getObject(object0, v1);
        if(!MessageSchema.isMutable(object4)) {
            Object object5 = schema0.newInstance();
            schema0.mergeFrom(object5, object4);
            unsafe0.putObject(object0, v1, object5);
            object4 = object5;
        }
        schema0.mergeFrom(object4, object2);
    }

    private void mergeOneofMessage(Object object0, Object object1, int v) {
        int v1 = this.numberAt(v);
        if(!this.isOneofPresent(object1, v1, v)) {
            return;
        }
        long v2 = MessageSchema.offset(this.typeAndOffsetAt(v));
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        Object object2 = unsafe0.getObject(object1, v2);
        if(object2 == null) {
            throw new IllegalStateException("Source subfield " + this.numberAt(v) + " is present but null: " + object1);
        }
        Schema schema0 = this.getMessageFieldSchema(v);
        if(!this.isOneofPresent(object0, v1, v)) {
            if(MessageSchema.isMutable(object2)) {
                Object object3 = schema0.newInstance();
                schema0.mergeFrom(object3, object2);
                unsafe0.putObject(object0, v2, object3);
            }
            else {
                unsafe0.putObject(object0, v2, object2);
            }
            this.setOneofPresent(object0, v1, v);
            return;
        }
        Object object4 = unsafe0.getObject(object0, v2);
        if(!MessageSchema.isMutable(object4)) {
            Object object5 = schema0.newInstance();
            schema0.mergeFrom(object5, object4);
            unsafe0.putObject(object0, v2, object5);
            object4 = object5;
        }
        schema0.mergeFrom(object4, object2);
    }

    private void mergeSingleField(Object object0, Object object1, int v) {
        int v1 = this.typeAndOffsetAt(v);
        long v2 = (long)(v1 & 0xFFFFF);
        int v3 = this.numberAt(v);
        switch((v1 & 0xFF00000) >>> 20) {
            case 0: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putDouble(object0, v2, UnsafeUtil.getDouble(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 1: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putFloat(object0, v2, UnsafeUtil.getFloat(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 2: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putLong(object0, v2, UnsafeUtil.getLong(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 3: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putLong(object0, v2, UnsafeUtil.getLong(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 4: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putInt(object0, v2, UnsafeUtil.getInt(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 5: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putLong(object0, v2, UnsafeUtil.getLong(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 6: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putInt(object0, v2, UnsafeUtil.getInt(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 7: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putBoolean(object0, v2, UnsafeUtil.getBoolean(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 8: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putObject(object0, v2, UnsafeUtil.getObject(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 9: {
                this.mergeMessage(object0, object1, v);
                return;
            }
            case 10: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putObject(object0, v2, UnsafeUtil.getObject(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 11: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putInt(object0, v2, UnsafeUtil.getInt(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 12: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putInt(object0, v2, UnsafeUtil.getInt(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 13: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putInt(object0, v2, UnsafeUtil.getInt(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 14: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putLong(object0, v2, UnsafeUtil.getLong(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 15: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putInt(object0, v2, UnsafeUtil.getInt(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 16: {
                if(this.isFieldPresent(object1, v)) {
                    UnsafeUtil.putLong(object0, v2, UnsafeUtil.getLong(object1, v2));
                    this.setFieldPresent(object0, v);
                    return;
                }
                break;
            }
            case 17: {
                this.mergeMessage(object0, object1, v);
                return;
            }
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: 
            case 26: 
            case 27: 
            case 28: 
            case 29: 
            case 30: 
            case 0x1F: 
            case 0x20: 
            case 33: 
            case 34: 
            case 35: 
            case 36: 
            case 37: 
            case 38: 
            case 39: 
            case 40: 
            case 41: 
            case 42: 
            case 43: 
            case 44: 
            case 45: 
            case 46: 
            case 0x2F: 
            case 0x30: 
            case 49: {
                this.listFieldSchema.mergeListsAt(object0, object1, v2);
                return;
            }
            case 50: {
                SchemaUtil.mergeMap(this.mapFieldSchema, object0, object1, v2);
                return;
            }
            case 51: 
            case 52: 
            case 53: 
            case 54: 
            case 55: 
            case 56: 
            case 57: 
            case 58: 
            case 59: {
                if(this.isOneofPresent(object1, v3, v)) {
                    UnsafeUtil.putObject(object0, v2, UnsafeUtil.getObject(object1, v2));
                    this.setOneofPresent(object0, v3, v);
                    return;
                }
                break;
            }
            case 60: {
                this.mergeOneofMessage(object0, object1, v);
                return;
            }
            case 61: 
            case 62: 
            case 0x3F: 
            case 0x40: 
            case 65: 
            case 66: 
            case 67: {
                if(this.isOneofPresent(object1, v3, v)) {
                    UnsafeUtil.putObject(object0, v2, UnsafeUtil.getObject(object1, v2));
                    this.setOneofPresent(object0, v3, v);
                    return;
                }
                break;
            }
            case 68: {
                this.mergeOneofMessage(object0, object1, v);
            }
        }
    }

    private Object mutableMessageFieldForMerge(Object object0, int v) {
        Schema schema0 = this.getMessageFieldSchema(v);
        long v1 = MessageSchema.offset(this.typeAndOffsetAt(v));
        if(!this.isFieldPresent(object0, v)) {
            return schema0.newInstance();
        }
        Object object1 = MessageSchema.UNSAFE.getObject(object0, v1);
        if(MessageSchema.isMutable(object1)) {
            return object1;
        }
        Object object2 = schema0.newInstance();
        if(object1 != null) {
            schema0.mergeFrom(object2, object1);
        }
        return object2;
    }

    private Object mutableOneofMessageFieldForMerge(Object object0, int v, int v1) {
        Schema schema0 = this.getMessageFieldSchema(v1);
        if(!this.isOneofPresent(object0, v, v1)) {
            return schema0.newInstance();
        }
        long v2 = MessageSchema.offset(this.typeAndOffsetAt(v1));
        Object object1 = MessageSchema.UNSAFE.getObject(object0, v2);
        if(MessageSchema.isMutable(object1)) {
            return object1;
        }
        Object object2 = schema0.newInstance();
        if(object1 != null) {
            schema0.mergeFrom(object2, object1);
        }
        return object2;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public Object newInstance() {
        return this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    // 去混淆评级： 低(20)
    static MessageSchema newSchema(Class class0, MessageInfo messageInfo0, NewInstanceSchema newInstanceSchema0, ListFieldSchema listFieldSchema0, UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, MapFieldSchema mapFieldSchema0) {
        return messageInfo0 instanceof RawMessageInfo ? MessageSchema.newSchemaForRawMessageInfo(((RawMessageInfo)messageInfo0), newInstanceSchema0, listFieldSchema0, unknownFieldSchema0, extensionSchema0, mapFieldSchema0) : MessageSchema.newSchemaForMessageInfo(((StructuralMessageInfo)messageInfo0), newInstanceSchema0, listFieldSchema0, unknownFieldSchema0, extensionSchema0, mapFieldSchema0);
    }

    static MessageSchema newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo0, NewInstanceSchema newInstanceSchema0, ListFieldSchema listFieldSchema0, UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, MapFieldSchema mapFieldSchema0) {
        int v10;
        int[] arr_v1;
        int v1;
        int v;
        boolean z = structuralMessageInfo0.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] arr_fieldInfo = structuralMessageInfo0.getFields();
        if(arr_fieldInfo.length == 0) {
            v = 0;
            v1 = 0;
        }
        else {
            v = arr_fieldInfo[0].getFieldNumber();
            v1 = arr_fieldInfo[arr_fieldInfo.length - 1].getFieldNumber();
        }
        int[] arr_v = new int[arr_fieldInfo.length * 3];
        Object[] arr_object = new Object[arr_fieldInfo.length * 2];
        int v3 = 0;
        int v4 = 0;
        for(int v2 = 0; true; ++v2) {
            arr_v1 = null;
            if(v2 >= arr_fieldInfo.length) {
                break;
            }
            FieldInfo fieldInfo0 = arr_fieldInfo[v2];
            if(fieldInfo0.getType() == com.google.crypto.tink.shaded.protobuf.FieldType.MAP) {
                ++v3;
            }
            else if(fieldInfo0.getType().id() >= 18 && fieldInfo0.getType().id() <= 49) {
                ++v4;
            }
        }
        int[] arr_v2 = v3 <= 0 ? null : new int[v3];
        if(v4 > 0) {
            arr_v1 = new int[v4];
        }
        int[] arr_v3 = structuralMessageInfo0.getCheckInitialized() == null ? MessageSchema.EMPTY_INT_ARRAY : structuralMessageInfo0.getCheckInitialized();
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        int v8 = 0;
        int v9 = 0;
        while(v5 < arr_fieldInfo.length) {
            FieldInfo fieldInfo1 = arr_fieldInfo[v5];
            MessageSchema.storeFieldData(fieldInfo1, arr_v, v6, arr_object);
            if(v7 < arr_v3.length && arr_v3[v7] == fieldInfo1.getFieldNumber()) {
                arr_v3[v7] = v6;
                ++v7;
            }
            if(fieldInfo1.getType() == com.google.crypto.tink.shaded.protobuf.FieldType.MAP) {
                arr_v2[v8] = v6;
                ++v8;
            }
            else if(fieldInfo1.getType().id() >= 18 && fieldInfo1.getType().id() <= 49) {
                v10 = v6;
                arr_v1[v9] = (int)UnsafeUtil.objectFieldOffset(fieldInfo1.getField());
                ++v9;
                goto label_48;
            }
            v10 = v6;
        label_48:
            ++v5;
            v6 = v10 + 3;
        }
        if(arr_v2 == null) {
            arr_v2 = MessageSchema.EMPTY_INT_ARRAY;
        }
        if(arr_v1 == null) {
            arr_v1 = MessageSchema.EMPTY_INT_ARRAY;
        }
        int[] arr_v4 = new int[arr_v3.length + arr_v2.length + arr_v1.length];
        System.arraycopy(arr_v3, 0, arr_v4, 0, arr_v3.length);
        System.arraycopy(arr_v2, 0, arr_v4, arr_v3.length, arr_v2.length);
        System.arraycopy(arr_v1, 0, arr_v4, arr_v3.length + arr_v2.length, arr_v1.length);
        return new MessageSchema(arr_v, arr_object, v, v1, structuralMessageInfo0.getDefaultInstance(), z, true, arr_v4, arr_v3.length, arr_v3.length + arr_v2.length, newInstanceSchema0, listFieldSchema0, unknownFieldSchema0, extensionSchema0, mapFieldSchema0);
    }

    static MessageSchema newSchemaForRawMessageInfo(RawMessageInfo rawMessageInfo0, NewInstanceSchema newInstanceSchema0, ListFieldSchema listFieldSchema0, UnknownFieldSchema unknownFieldSchema0, ExtensionSchema extensionSchema0, MapFieldSchema mapFieldSchema0) {
        Field field3;
        int v87;
        int v79;
        int v78;
        int v77;
        int v76;
        Field field1;
        Field field0;
        int v67;
        int v61;
        int v14;
        int v13;
        int v12;
        int v11;
        int v10;
        int v9;
        int v8;
        int[] arr_v;
        int v2;
        boolean z = rawMessageInfo0.getSyntax() == ProtoSyntax.PROTO3;
        String s = rawMessageInfo0.getStringInfo();
        int v = s.length();
        if(s.charAt(0) >= 0xD800) {
            for(int v1 = 1; true; v1 = v2) {
                v2 = v1 + 1;
                if(s.charAt(v1) < 0xD800) {
                    break;
                }
            }
        }
        else {
            v2 = 1;
        }
        int v3 = v2 + 1;
        int v4 = s.charAt(v2);
        if(v4 >= 0xD800) {
            int v5 = v4 & 0x1FFF;
            int v6 = 13;
            int v7;
            while((v7 = s.charAt(v3)) >= 0xD800) {
                v5 |= (v7 & 0x1FFF) << v6;
                v6 += 13;
                ++v3;
            }
            v4 = v5 | v7 << v6;
            ++v3;
        }
        if(v4 == 0) {
            arr_v = MessageSchema.EMPTY_INT_ARRAY;
            v8 = 0;
            v9 = 0;
            v10 = 0;
            v11 = 0;
            v12 = 0;
            v13 = 0;
            v14 = 0;
        }
        else {
            int v15 = v3 + 1;
            int v16 = s.charAt(v3);
            if(v16 >= 0xD800) {
                int v17 = v16 & 0x1FFF;
                int v18 = 13;
                int v19;
                while((v19 = s.charAt(v15)) >= 0xD800) {
                    v17 |= (v19 & 0x1FFF) << v18;
                    v18 += 13;
                    ++v15;
                }
                v16 = v17 | v19 << v18;
                ++v15;
            }
            int v20 = v15 + 1;
            int v21 = s.charAt(v15);
            if(v21 >= 0xD800) {
                int v22 = v21 & 0x1FFF;
                int v23 = 13;
                int v24;
                while((v24 = s.charAt(v20)) >= 0xD800) {
                    v22 |= (v24 & 0x1FFF) << v23;
                    v23 += 13;
                    ++v20;
                }
                v21 = v22 | v24 << v23;
                ++v20;
            }
            int v25 = v20 + 1;
            v9 = s.charAt(v20);
            if(v9 >= 0xD800) {
                int v26 = v9 & 0x1FFF;
                int v27 = 13;
                int v28;
                while((v28 = s.charAt(v25)) >= 0xD800) {
                    v26 |= (v28 & 0x1FFF) << v27;
                    v27 += 13;
                    ++v25;
                }
                v9 = v26 | v28 << v27;
                ++v25;
            }
            int v29 = v25 + 1;
            v10 = s.charAt(v25);
            if(v10 >= 0xD800) {
                int v30 = v10 & 0x1FFF;
                int v31 = 13;
                int v32;
                while((v32 = s.charAt(v29)) >= 0xD800) {
                    v30 |= (v32 & 0x1FFF) << v31;
                    v31 += 13;
                    ++v29;
                }
                v10 = v30 | v32 << v31;
                ++v29;
            }
            int v33 = v29 + 1;
            v11 = s.charAt(v29);
            if(v11 >= 0xD800) {
                int v34 = v11 & 0x1FFF;
                int v35 = 13;
                int v36;
                while((v36 = s.charAt(v33)) >= 0xD800) {
                    v34 |= (v36 & 0x1FFF) << v35;
                    v35 += 13;
                    ++v33;
                }
                v11 = v34 | v36 << v35;
                ++v33;
            }
            int v37 = v33 + 1;
            int v38 = s.charAt(v33);
            if(v38 >= 0xD800) {
                int v39 = v38 & 0x1FFF;
                int v40 = 13;
                int v41;
                while((v41 = s.charAt(v37)) >= 0xD800) {
                    v39 |= (v41 & 0x1FFF) << v40;
                    v40 += 13;
                    ++v37;
                }
                v38 = v39 | v41 << v40;
                ++v37;
            }
            int v42 = v37 + 1;
            int v43 = s.charAt(v37);
            if(v43 >= 0xD800) {
                int v44 = v43 & 0x1FFF;
                int v45 = 13;
                int v46;
                while((v46 = s.charAt(v42)) >= 0xD800) {
                    v44 |= (v46 & 0x1FFF) << v45;
                    v45 += 13;
                    ++v42;
                }
                v43 = v44 | v46 << v45;
                ++v42;
            }
            int v47 = v42 + 1;
            v13 = s.charAt(v42);
            if(v13 >= 0xD800) {
                int v48 = v13 & 0x1FFF;
                int v49 = 13;
                int v50;
                while((v50 = s.charAt(v47)) >= 0xD800) {
                    v48 |= (v50 & 0x1FFF) << v49;
                    v49 += 13;
                    ++v47;
                }
                v13 = v48 | v50 << v49;
                ++v47;
            }
            v14 = v16 * 2 + v21;
            v12 = v38;
            arr_v = new int[v13 + v38 + v43];
            v8 = v16;
            v3 = v47;
        }
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        Object[] arr_object = rawMessageInfo0.getObjects();
        Class class0 = rawMessageInfo0.getDefaultInstance().getClass();
        int[] arr_v1 = new int[v11 * 3];
        Object[] arr_object1 = new Object[v11 * 2];
        int v51 = v12 + v13;
        int v52 = v51;
        int v53 = v13;
        int v54 = 0;
        int v55 = 0;
        while(v3 < v) {
            int v56 = s.charAt(v3);
            if(v56 >= 0xD800) {
                int v57 = v56 & 0x1FFF;
                int v58 = v3 + 1;
                int v59 = 13;
                int v60;
                while((v60 = s.charAt(v58)) >= 0xD800) {
                    v57 |= (v60 & 0x1FFF) << v59;
                    v59 += 13;
                    ++v58;
                }
                v56 = v57 | v60 << v59;
                v61 = v58 + 1;
            }
            else {
                v61 = v3 + 1;
            }
            int v62 = s.charAt(v61);
            if(v62 >= 0xD800) {
                int v63 = v62 & 0x1FFF;
                int v64 = v61 + 1;
                int v65 = 13;
                int v66;
                while((v66 = s.charAt(v64)) >= 0xD800) {
                    v63 |= (v66 & 0x1FFF) << v65;
                    v65 += 13;
                    ++v64;
                }
                v62 = v63 | v66 << v65;
                v67 = v64 + 1;
            }
            else {
                v67 = v61 + 1;
            }
            if((v62 & 0x400) != 0) {
                arr_v[v54] = v55;
                ++v54;
            }
            if((v62 & 0xFF) >= 51) {
                int v68 = v67 + 1;
                int v69 = s.charAt(v67);
                if(v69 >= 0xD800) {
                    int v70 = v69 & 0x1FFF;
                    int v71 = 13;
                    int v72;
                    while((v72 = s.charAt(v68)) >= 0xD800) {
                        v70 |= (v72 & 0x1FFF) << v71;
                        v71 += 13;
                        ++v68;
                    }
                    v69 = v70 | v72 << v71;
                    ++v68;
                }
                switch((v62 & 0xFF) - 51) {
                    case 12: {
                        if(!z) {
                            arr_object1[v55 / 3 * 2 + 1] = arr_object[v14];
                            ++v14;
                        }
                        break;
                    }
                    case 9: 
                    case 17: {
                        arr_object1[v55 / 3 * 2 + 1] = arr_object[v14];
                        ++v14;
                    }
                }
                Object object0 = arr_object[v69 * 2];
                if(object0 instanceof Field) {
                    field0 = (Field)object0;
                }
                else {
                    field0 = MessageSchema.reflectField(class0, ((String)object0));
                    arr_object[v69 * 2] = field0;
                }
                int v73 = (int)unsafe0.objectFieldOffset(field0);
                int v74 = v69 * 2 + 1;
                Object object1 = arr_object[v74];
                int v75 = v74;
                if(object1 instanceof Field) {
                    field1 = (Field)object1;
                }
                else {
                    field1 = MessageSchema.reflectField(class0, ((String)object1));
                    arr_object[v75] = field1;
                }
                v76 = (int)unsafe0.objectFieldOffset(field1);
                v77 = v73;
                v78 = 0;
                v79 = v68;
            }
            else {
                Field field2 = MessageSchema.reflectField(class0, ((String)arr_object[v14]));
                int v80 = v14 + 1;
                switch(v62 & 0xFF) {
                    case 9: 
                    case 17: {
                        arr_object1[v55 / 3 * 2 + 1] = field2.getType();
                        v14 = v80;
                        break;
                    }
                    case 12: 
                    case 30: 
                    case 44: {
                        if(!z) {
                            v14 += 2;
                            arr_object1[v55 / 3 * 2 + 1] = arr_object[v80];
                            break;
                        }
                        v14 = v80;
                        break;
                    }
                    case 27: 
                    case 49: {
                        v14 += 2;
                        arr_object1[v55 / 3 * 2 + 1] = arr_object[v80];
                        break;
                    }
                    case 50: {
                        arr_v[v53] = v55;
                        int v81 = v55 / 3 * 2;
                        arr_object1[v81] = arr_object[v80];
                        if((v62 & 0x800) == 0) {
                            v14 += 2;
                        }
                        else {
                            arr_object1[v81 + 1] = arr_object[v14 + 2];
                            v14 += 3;
                        }
                        ++v53;
                        break;
                    }
                    default: {
                        v14 = v80;
                        break;
                    }
                }
                int v82 = (int)unsafe0.objectFieldOffset(field2);
                if((v62 & 0x1000) != 0x1000 || (v62 & 0xFF) > 17) {
                    v76 = 0xFFFFF;
                    v79 = v67;
                    v78 = 0;
                }
                else {
                    int v83 = v67 + 1;
                    int v84 = s.charAt(v67);
                    if(v84 >= 0xD800) {
                        int v85 = v84 & 0x1FFF;
                        int v86 = 13;
                        while(true) {
                            v79 = v83 + 1;
                            v87 = s.charAt(v83);
                            if(v87 < 0xD800) {
                                break;
                            }
                            v85 |= (v87 & 0x1FFF) << v86;
                            v86 += 13;
                            v83 = v79;
                        }
                        v84 = v85 | v87 << v86;
                    }
                    else {
                        v79 = v83;
                    }
                    int v88 = v8 * 2 + v84 / 0x20;
                    Object object2 = arr_object[v88];
                    if(object2 instanceof Field) {
                        field3 = (Field)object2;
                    }
                    else {
                        field3 = MessageSchema.reflectField(class0, ((String)object2));
                        arr_object[v88] = field3;
                    }
                    v76 = (int)unsafe0.objectFieldOffset(field3);
                    v78 = v84 % 0x20;
                }
                if((v62 & 0xFF) >= 18 && (v62 & 0xFF) <= 49) {
                    arr_v[v52] = v82;
                    ++v52;
                }
                v77 = v82;
            }
            arr_v1[v55] = v56;
            int v89 = v55 + 2;
            arr_v1[v55 + 1] = ((v62 & 0x200) == 0 ? 0 : 0x20000000) | ((v62 & 0x100) == 0 ? 0 : 0x10000000) | (v62 & 0xFF) << 20 | v77;
            v55 += 3;
            arr_v1[v89] = v78 << 20 | v76;
            v3 = v79;
        }
        return new MessageSchema(arr_v1, arr_object1, v9, v10, rawMessageInfo0.getDefaultInstance(), z, false, arr_v, v13, v51, newInstanceSchema0, listFieldSchema0, unknownFieldSchema0, extensionSchema0, mapFieldSchema0);
    }

    private int numberAt(int v) {
        return this.buffer[v];
    }

    private static long offset(int value) [...] // Inlined contents

    private static boolean oneofBooleanAt(Object object0, long v) {
        return ((Boolean)UnsafeUtil.getObject(object0, v)).booleanValue();
    }

    private static double oneofDoubleAt(Object object0, long v) {
        return (double)(((Double)UnsafeUtil.getObject(object0, v)));
    }

    private static float oneofFloatAt(Object object0, long v) {
        return (float)(((Float)UnsafeUtil.getObject(object0, v)));
    }

    private static int oneofIntAt(Object object0, long v) {
        return (int)(((Integer)UnsafeUtil.getObject(object0, v)));
    }

    private static long oneofLongAt(Object object0, long v) {
        return (long)(((Long)UnsafeUtil.getObject(object0, v)));
    }

    private int parseMapField(Object object0, byte[] arr_b, int v, int v1, int v2, long v3, Registers arrayDecoders$Registers0) throws IOException {
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        Object object1 = this.getMapFieldDefaultEntry(v2);
        Object object2 = unsafe0.getObject(object0, v3);
        if(this.mapFieldSchema.isImmutable(object2)) {
            Object object3 = this.mapFieldSchema.newMapField(object1);
            this.mapFieldSchema.mergeFrom(object3, object2);
            unsafe0.putObject(object0, v3, object3);
            object2 = object3;
        }
        return this.decodeMapEntry(arr_b, v, v1, this.mapFieldSchema.forMapMetadata(object1), this.mapFieldSchema.forMutableMapData(object2), arrayDecoders$Registers0);
    }

    private int parseOneofField(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, int v6, long v7, int v8, Registers arrayDecoders$Registers0) throws IOException {
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        long v9 = (long)(this.buffer[v8 + 2] & 0xFFFFF);
        boolean z = true;
        switch(v6) {
            case 51: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, ArrayDecoders.decodeDouble(arr_b, v));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 8;
                }
                break;
            }
            case 52: {
                if(v4 == 5) {
                    unsafe0.putObject(object0, v7, ArrayDecoders.decodeFloat(arr_b, v));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 4;
                }
                break;
            }
            case 53: 
            case 54: {
                if(v4 == 0) {
                    int v11 = ArrayDecoders.decodeVarint64(arr_b, v, arrayDecoders$Registers0);
                    unsafe0.putObject(object0, v7, arrayDecoders$Registers0.long1);
                    unsafe0.putInt(object0, v9, v3);
                    return v11;
                }
                break;
            }
            case 58: {
                if(v4 == 0) {
                    int v13 = ArrayDecoders.decodeVarint64(arr_b, v, arrayDecoders$Registers0);
                    if(arrayDecoders$Registers0.long1 == 0L) {
                        z = false;
                    }
                    unsafe0.putObject(object0, v7, Boolean.valueOf(z));
                    unsafe0.putInt(object0, v9, v3);
                    return v13;
                }
                break;
            }
            case 59: {
                if(v4 == 2) {
                    int v14 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
                    int v15 = arrayDecoders$Registers0.int1;
                    if(v15 == 0) {
                        unsafe0.putObject(object0, v7, "");
                    }
                    else {
                        if((v5 & 0x20000000) != 0 && !Utf8.isValidUtf8(arr_b, v14, v14 + v15)) {
                            throw InvalidProtocolBufferException.invalidUtf8();
                        }
                        unsafe0.putObject(object0, v7, new String(arr_b, v14, v15, Internal.UTF_8));
                        v14 += v15;
                    }
                    unsafe0.putInt(object0, v9, v3);
                    return v14;
                }
                break;
            }
            case 60: {
                if(v4 == 2) {
                    Object object2 = this.mutableOneofMessageFieldForMerge(object0, v3, v8);
                    int v16 = ArrayDecoders.mergeMessageField(object2, this.getMessageFieldSchema(v8), arr_b, v, v1, arrayDecoders$Registers0);
                    this.storeOneofMessageField(object0, v3, v8, object2);
                    return v16;
                }
                break;
            }
            case 61: {
                if(v4 == 2) {
                    int v17 = ArrayDecoders.decodeBytes(arr_b, v, arrayDecoders$Registers0);
                    unsafe0.putObject(object0, v7, arrayDecoders$Registers0.object1);
                    unsafe0.putInt(object0, v9, v3);
                    return v17;
                }
                break;
            }
            case 55: 
            case 62: {
                if(v4 == 0) {
                    int v12 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
                    unsafe0.putObject(object0, v7, arrayDecoders$Registers0.int1);
                    unsafe0.putInt(object0, v9, v3);
                    return v12;
                }
                break;
            }
            case 0x3F: {
                if(v4 == 0) {
                    int v18 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
                    int v19 = arrayDecoders$Registers0.int1;
                    EnumVerifier internal$EnumVerifier0 = this.getEnumFieldVerifier(v8);
                    if(internal$EnumVerifier0 != null && !internal$EnumVerifier0.isInRange(v19)) {
                        MessageSchema.getMutableUnknownFields(object0).storeField(v2, ((long)v19));
                        return v18;
                    }
                    unsafe0.putObject(object0, v7, v19);
                    unsafe0.putInt(object0, v9, v3);
                    return v18;
                }
                break;
            }
            case 57: 
            case 0x40: {
                if(v4 == 5) {
                    unsafe0.putObject(object0, v7, ArrayDecoders.decodeFixed32(arr_b, v));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 4;
                }
                break;
            }
            case 56: 
            case 65: {
                if(v4 == 1) {
                    unsafe0.putObject(object0, v7, ArrayDecoders.decodeFixed64(arr_b, v));
                    unsafe0.putInt(object0, v9, v3);
                    return v + 8;
                }
                break;
            }
            case 66: {
                if(v4 == 0) {
                    int v20 = ArrayDecoders.decodeVarint32(arr_b, v, arrayDecoders$Registers0);
                    unsafe0.putObject(object0, v7, ((int)(-(arrayDecoders$Registers0.int1 & 1) ^ arrayDecoders$Registers0.int1 >>> 1)));
                    unsafe0.putInt(object0, v9, v3);
                    return v20;
                }
                break;
            }
            case 67: {
                if(v4 == 0) {
                    int v21 = ArrayDecoders.decodeVarint64(arr_b, v, arrayDecoders$Registers0);
                    unsafe0.putObject(object0, v7, ((long)(-(arrayDecoders$Registers0.long1 & 1L) ^ arrayDecoders$Registers0.long1 >>> 1)));
                    unsafe0.putInt(object0, v9, v3);
                    return v21;
                }
                break;
            }
            case 68: {
                if(v4 == 3) {
                    Object object1 = this.mutableOneofMessageFieldForMerge(object0, v3, v8);
                    int v10 = ArrayDecoders.mergeGroupField(object1, this.getMessageFieldSchema(v8), arr_b, v, v1, v2 & -8 | 4, arrayDecoders$Registers0);
                    this.storeOneofMessageField(object0, v3, v8, object1);
                    return v10;
                }
                return v;
            }
            default: {
                return v;
            }
        }
        return v;
    }

    int parseProto2Message(Object object0, byte[] arr_b, int v, int v1, int v2, Registers arrayDecoders$Registers0) throws IOException {
        int v44;
        int v43;
        int v42;
        Object object6;
        int v37;
        int v26;
        Unsafe unsafe2;
        int v25;
        Object object2;
        Unsafe unsafe4;
        int v34;
        Object object4;
        int v33;
        int v31;
        int v30;
        Unsafe unsafe3;
        int v27;
        MessageSchema messageSchema1;
        int v18;
        int v17;
        int v16;
        Unsafe unsafe1;
        int v15;
        int v14;
        MessageSchema messageSchema0 = this;
        Object object1 = object0;
        MessageSchema.checkMutable(object1);
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        int v3 = v;
        int v4 = -1;
        int v5 = 0;
        int v6 = 0xFFFFF;
        int v7 = 0;
        int v8 = 0;
        while(true) {
            if(v3 >= v1) {
                object6 = object1;
                unsafe1 = unsafe0;
                messageSchema1 = messageSchema0;
                v42 = v3;
                v43 = v6;
                break;
            }
            int v9 = v3 + 1;
            int v10 = arr_b[v3];
            if(v10 < 0) {
                v9 = ArrayDecoders.decodeVarint32(v10, arr_b, v9, arrayDecoders$Registers0);
                v10 = arrayDecoders$Registers0.int1;
            }
            v8 = v10;
            int v11 = v8 >>> 3;
            int v12 = v8 & 7;
            int v13 = v11 <= v4 ? messageSchema0.positionForFieldNumber(v11) : messageSchema0.positionForFieldNumber(v11, v5 / 3);
            if(v13 == -1) {
                v14 = v9;
                v15 = v6;
                unsafe1 = unsafe0;
                v16 = v7;
                v17 = v11;
                v18 = 0;
                messageSchema1 = messageSchema0;
                goto label_286;
            }
            else {
                int v19 = messageSchema0.buffer[v13 + 1];
                int v20 = (v19 & 0xFF00000) >>> 20;
                long v21 = (long)(v19 & 0xFFFFF);
                if(v20 <= 17) {
                    int v22 = messageSchema0.buffer[v13 + 2];
                    boolean z = true;
                    int v23 = 1 << (v22 >>> 20);
                    int v24 = v22 & 0xFFFFF;
                    v17 = v11;
                    if(v24 == v6) {
                        v24 = v6;
                    }
                    else {
                        if(v6 != 0xFFFFF) {
                            unsafe0.putInt(object1, ((long)v6), v7);
                        }
                        v7 = unsafe0.getInt(object1, ((long)v24));
                    }
                    switch(v20) {
                        case 0: {
                            v25 = v13;
                            v26 = v9;
                            unsafe2 = unsafe0;
                            if(v12 == 1) {
                                UnsafeUtil.putDouble(object1, v21, ArrayDecoders.decodeDouble(arr_b, v26));
                                v27 = v26 + 8;
                                goto label_62;
                            }
                            break;
                        }
                        case 1: {
                            v25 = v13;
                            v26 = v9;
                            unsafe2 = unsafe0;
                            if(v12 == 5) {
                                UnsafeUtil.putFloat(object1, v21, ArrayDecoders.decodeFloat(arr_b, v26));
                                v27 = v26 + 4;
                            label_62:
                                v7 |= v23;
                                v6 = v24;
                                v5 = v25;
                                v4 = v17;
                                unsafe0 = unsafe2;
                                v3 = v27;
                                continue;
                            }
                            break;
                        }
                        case 2: 
                        case 3: {
                            v25 = v13;
                            unsafe3 = unsafe0;
                            v26 = v9;
                            if(v12 == 0) {
                                int v28 = ArrayDecoders.decodeVarint64(arr_b, v26, arrayDecoders$Registers0);
                                unsafe3.putLong(object1, v21, arrayDecoders$Registers0.long1);
                                v7 |= v23;
                                unsafe0 = unsafe3;
                                v3 = v28;
                                v6 = v24;
                                v5 = v25;
                                v4 = v17;
                                continue;
                            }
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 7: {
                        label_116:
                            v25 = v13;
                            unsafe4 = unsafe0;
                            v30 = v9;
                            object2 = object1;
                            if(v12 == 0) {
                                v31 = ArrayDecoders.decodeVarint64(arr_b, v30, arrayDecoders$Registers0);
                                if(arrayDecoders$Registers0.long1 == 0L) {
                                    z = false;
                                }
                                UnsafeUtil.putBoolean(object2, v21, z);
                                v7 |= v23;
                                v3 = v31;
                                object1 = object2;
                                v6 = v24;
                                v5 = v25;
                                unsafe0 = unsafe4;
                                v4 = v17;
                                continue;
                            }
                            unsafe2 = unsafe4;
                            v26 = v30;
                            break;
                        }
                        case 8: {
                            v25 = v13;
                            unsafe4 = unsafe0;
                            v30 = v9;
                            object2 = object1;
                            if(v12 == 2) {
                                v31 = (0x20000000 & v19) == 0 ? ArrayDecoders.decodeString(arr_b, v30, arrayDecoders$Registers0) : ArrayDecoders.decodeStringRequireUtf8(arr_b, v30, arrayDecoders$Registers0);
                                unsafe4.putObject(object2, v21, arrayDecoders$Registers0.object1);
                                v7 |= v23;
                                v3 = v31;
                                object1 = object2;
                                v6 = v24;
                                v5 = v25;
                                unsafe0 = unsafe4;
                                v4 = v17;
                                continue;
                            }
                            unsafe2 = unsafe4;
                            v26 = v30;
                            break;
                        }
                        case 9: {
                            v25 = v13;
                            unsafe4 = unsafe0;
                            if(v12 == 2) {
                                Object object3 = messageSchema0.mutableMessageFieldForMerge(object1, v25);
                                int v32 = ArrayDecoders.mergeMessageField(object3, messageSchema0.getMessageFieldSchema(v25), arr_b, v9, v1, arrayDecoders$Registers0);
                                messageSchema0.storeMessageField(object1, v25, object3);
                                v7 |= v23;
                                v3 = v32;
                                v6 = v24;
                                v5 = v25;
                                unsafe0 = unsafe4;
                                v4 = v17;
                                continue;
                            }
                            else {
                                unsafe2 = unsafe4;
                                v26 = v9;
                                break;
                            }
                            goto label_156;
                        }
                        case 10: {
                        label_156:
                            v25 = v13;
                            unsafe4 = unsafe0;
                            v33 = v9;
                            object4 = object1;
                            if(v12 == 2) {
                                v34 = ArrayDecoders.decodeBytes(arr_b, v33, arrayDecoders$Registers0);
                                unsafe4.putObject(object4, v21, arrayDecoders$Registers0.object1);
                                v7 |= v23;
                                v3 = v34;
                                goto label_190;
                            }
                            unsafe2 = unsafe4;
                            v26 = v33;
                            break;
                        }
                        case 4: 
                        case 11: {
                            v25 = v13;
                            unsafe3 = unsafe0;
                            v26 = v9;
                            if(v12 == 0) {
                                int v29 = ArrayDecoders.decodeVarint32(arr_b, v26, arrayDecoders$Registers0);
                                unsafe3.putInt(object1, v21, arrayDecoders$Registers0.int1);
                                v7 |= v23;
                                v3 = v29;
                                goto label_96;
                            }
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 12: {
                            v25 = v13;
                            unsafe4 = unsafe0;
                            v33 = v9;
                            object4 = object1;
                            if(v12 == 0) {
                                v34 = ArrayDecoders.decodeVarint32(arr_b, v33, arrayDecoders$Registers0);
                                int v35 = arrayDecoders$Registers0.int1;
                                EnumVerifier internal$EnumVerifier0 = messageSchema0.getEnumFieldVerifier(v25);
                                if(internal$EnumVerifier0 == null || internal$EnumVerifier0.isInRange(v35)) {
                                    unsafe4.putInt(object4, v21, v35);
                                }
                                else {
                                    MessageSchema.getMutableUnknownFields(object4).storeField(v8, ((long)v35));
                                    v3 = v34;
                                    goto label_190;
                                }
                                v7 |= v23;
                                v3 = v34;
                                goto label_190;
                            }
                            unsafe2 = unsafe4;
                            v26 = v33;
                            break;
                        }
                        case 6: 
                        case 13: {
                            v25 = v13;
                            unsafe4 = unsafe0;
                            object2 = object1;
                            if(v12 == 5) {
                                unsafe4.putInt(object2, v21, ArrayDecoders.decodeFixed32(arr_b, v9));
                                v3 = v9 + 4;
                                v7 |= v23;
                                object1 = object2;
                                v6 = v24;
                                v5 = v25;
                                unsafe0 = unsafe4;
                                v4 = v17;
                                continue;
                            }
                            else {
                                unsafe2 = unsafe4;
                                v26 = v9;
                                break;
                            }
                            goto label_116;
                        }
                        case 5: 
                        case 14: {
                            v25 = v13;
                            if(v12 == 1) {
                                unsafe0.putLong(object1, v21, ArrayDecoders.decodeFixed64(arr_b, v9));
                                unsafe3 = unsafe0;
                                v3 = v9 + 8;
                                v7 |= v23;
                            label_96:
                                v6 = v24;
                                v5 = v25;
                                v4 = v17;
                                unsafe0 = unsafe3;
                                continue;
                            }
                            else {
                                unsafe3 = unsafe0;
                                v26 = v9;
                            }
                            unsafe2 = unsafe3;
                            break;
                        }
                        case 15: {
                            v25 = v13;
                            unsafe4 = unsafe0;
                            object4 = object1;
                            if(v12 == 0) {
                                v34 = ArrayDecoders.decodeVarint32(arr_b, v9, arrayDecoders$Registers0);
                                unsafe4.putInt(object4, v21, -(arrayDecoders$Registers0.int1 & 1) ^ arrayDecoders$Registers0.int1 >>> 1);
                                v7 |= v23;
                                v3 = v34;
                            label_190:
                                object1 = object4;
                                v6 = v24;
                                v5 = v25;
                                unsafe0 = unsafe4;
                                v4 = v17;
                                continue;
                            }
                            else {
                                unsafe2 = unsafe4;
                                v26 = v9;
                                break;
                            }
                            goto label_198;
                        }
                        case 16: {
                        label_198:
                            v25 = v13;
                            if(v12 == 0) {
                                int v36 = ArrayDecoders.decodeVarint64(arr_b, v9, arrayDecoders$Registers0);
                                unsafe0.putLong(object1, v21, -(arrayDecoders$Registers0.long1 & 1L) ^ arrayDecoders$Registers0.long1 >>> 1);
                                v7 |= v23;
                                v3 = v36;
                                v6 = v24;
                                v5 = v25;
                                v4 = v17;
                                continue;
                            }
                            else {
                                v26 = v9;
                                unsafe2 = unsafe0;
                                break;
                            }
                            goto label_210;
                        }
                        case 17: {
                        label_210:
                            if(v12 == 3) {
                                Object object5 = messageSchema0.mutableMessageFieldForMerge(object1, v13);
                                v3 = ArrayDecoders.mergeGroupField(object5, messageSchema0.getMessageFieldSchema(v13), arr_b, v9, v1, v17 << 3 | 4, arrayDecoders$Registers0);
                                messageSchema0.storeMessageField(object1, v13, object5);
                                v7 |= v23;
                                v6 = v24;
                                v5 = v13;
                                v4 = v17;
                                continue;
                            }
                            else {
                                v25 = v13;
                                unsafe2 = unsafe0;
                                v26 = v9;
                            }
                            break;
                        }
                        default: {
                            v25 = v13;
                            unsafe2 = unsafe0;
                            v26 = v9;
                        }
                    }
                    messageSchema1 = messageSchema0;
                    unsafe1 = unsafe2;
                    v15 = v24;
                    v16 = v7;
                    v37 = v8;
                    v18 = v25;
                    v14 = v26;
                }
                else {
                    v17 = v11;
                    if(v20 == 27) {
                        if(v12 == 2) {
                            ProtobufList internal$ProtobufList0 = (ProtobufList)unsafe0.getObject(object1, v21);
                            if(!internal$ProtobufList0.isModifiable()) {
                                int v38 = internal$ProtobufList0.size();
                                internal$ProtobufList0 = internal$ProtobufList0.mutableCopyWithCapacity((v38 == 0 ? 10 : v38 * 2));
                                unsafe0.putObject(object1, v21, internal$ProtobufList0);
                            }
                            v3 = ArrayDecoders.decodeMessageList(messageSchema0.getMessageFieldSchema(v13), v8, arr_b, v9, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                            v5 = v13;
                            v4 = v17;
                            object1 = object0;
                            continue;
                        }
                        else {
                            v15 = v6;
                            v16 = v7;
                            v18 = v13;
                            unsafe1 = unsafe0;
                            messageSchema1 = this;
                            v14 = v9;
                            goto label_286;
                        }
                        goto label_250;
                    }
                    else {
                    label_250:
                        if(v20 <= 49) {
                            v15 = v6;
                            v16 = v7;
                            unsafe1 = unsafe0;
                            int v39 = messageSchema0.parseRepeatedField(object0, arr_b, v9, v1, v8, v17, v12, v13, ((long)v19), v20, v21, arrayDecoders$Registers0);
                            v18 = v13;
                            if(v39 == v9) {
                                messageSchema1 = this;
                                v14 = v39;
                                goto label_286;
                            }
                            else {
                                messageSchema0 = this;
                                object1 = object0;
                                v3 = v39;
                                goto label_320;
                            }
                            goto label_264;
                        }
                        else {
                        label_264:
                            v15 = v6;
                            v16 = v7;
                            v18 = v13;
                            unsafe1 = unsafe0;
                            if(v20 == 50) {
                                if(v12 == 2) {
                                    int v40 = this.parseMapField(object0, arr_b, v9, v1, v18, v21, arrayDecoders$Registers0);
                                    if(v40 == v9) {
                                        messageSchema1 = this;
                                        v14 = v40;
                                        goto label_286;
                                    }
                                    else {
                                        messageSchema0 = this;
                                        object1 = object0;
                                        v3 = v40;
                                        v5 = v18;
                                        v6 = v15;
                                        v7 = v16;
                                        unsafe0 = unsafe1;
                                        v4 = v17;
                                        continue;
                                    }
                                    goto label_284;
                                }
                                else {
                                label_284:
                                    messageSchema1 = this;
                                    v14 = v9;
                                }
                            label_286:
                                v37 = v8;
                            }
                            else {
                                int v41 = this.parseOneofField(object0, arr_b, v9, v1, v8, v17, v12, v19, v20, v21, v18, arrayDecoders$Registers0);
                                messageSchema1 = this;
                                v37 = v8;
                                if(v41 == v9) {
                                    v14 = v41;
                                }
                                else {
                                    object1 = object0;
                                    v8 = v37;
                                    v3 = v41;
                                    messageSchema0 = messageSchema1;
                                    v5 = v18;
                                    v6 = v15;
                                    v7 = v16;
                                    unsafe0 = unsafe1;
                                    v4 = v17;
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
            if(v37 == v2 && v2 != 0) {
                object6 = object0;
                v8 = v37;
                v42 = v14;
                v43 = v15;
                v7 = v16;
                break;
            }
            if(messageSchema1.hasExtensions) {
                ExtensionRegistryLite extensionRegistryLite0 = ExtensionRegistryLite.getEmptyRegistry();
                if(arrayDecoders$Registers0.extensionRegistry != extensionRegistryLite0) {
                    v44 = ArrayDecoders.decodeExtensionOrUnknownField(v37, arr_b, v14, v1, object0, messageSchema1.defaultInstance, messageSchema1.unknownFieldSchema, arrayDecoders$Registers0);
                }
            }
            else {
                v44 = ArrayDecoders.decodeUnknownField(v37, arr_b, v14, v1, MessageSchema.getMutableUnknownFields(object0), arrayDecoders$Registers0);
            }
            v3 = v44;
            v8 = v37;
            object1 = object0;
            messageSchema0 = messageSchema1;
        label_320:
            v5 = v18;
            v6 = v15;
            v7 = v16;
            unsafe0 = unsafe1;
            v4 = v17;
        }
        if(v43 != 0xFFFFF) {
            unsafe1.putInt(object6, ((long)v43), v7);
        }
        int v45 = messageSchema1.checkInitializedCount;
        UnknownFieldSetLite unknownFieldSetLite0 = null;
        while(v45 < messageSchema1.repeatedFieldOffsetStart) {
            unknownFieldSetLite0 = (UnknownFieldSetLite)messageSchema1.filterMapUnknownEnumValues(object6, messageSchema1.intArray[v45], unknownFieldSetLite0, messageSchema1.unknownFieldSchema, object0);
            ++v45;
        }
        if(unknownFieldSetLite0 != null) {
            messageSchema1.unknownFieldSchema.setBuilderToMessage(object6, unknownFieldSetLite0);
        }
        if(v2 == 0) {
            if(v42 != v1) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            return v42;
        }
        if(v42 > v1 || v8 != v2) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return v42;
    }

    private int parseProto3Message(Object object0, byte[] arr_b, int v, int v1, Registers arrayDecoders$Registers0) throws IOException {
        int v31;
        int v30;
        int v22;
        int v21;
        int v25;
        Unsafe unsafe2;
        Object object3;
        int v24;
        int v26;
        byte[] arr_b3;
        Registers arrayDecoders$Registers3;
        Registers arrayDecoders$Registers2;
        byte[] arr_b2;
        int v20;
        int v13;
        int v12;
        int v11;
        int v10;
        Unsafe unsafe1;
        Object object2;
        Object object1 = object0;
        byte[] arr_b1 = arr_b;
        Registers arrayDecoders$Registers1 = arrayDecoders$Registers0;
        MessageSchema.checkMutable(object1);
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        int v2 = v;
        int v3 = -1;
        int v4 = 0;
        int v5 = 0xFFFFF;
        int v6 = 0;
        while(v2 < v1) {
            int v7 = v2 + 1;
            int v8 = arr_b1[v2];
            if(v8 < 0) {
                v7 = ArrayDecoders.decodeVarint32(v8, arr_b1, v7, arrayDecoders$Registers1);
                v8 = arrayDecoders$Registers1.int1;
            }
            int v9 = v8 >>> 3 <= v3 ? this.positionForFieldNumber(v8 >>> 3) : this.positionForFieldNumber(v8 >>> 3, v4 / 3);
            if(v9 == -1) {
                object2 = object1;
                unsafe1 = unsafe0;
                v10 = v8;
                v11 = v7;
                v12 = v8 >>> 3;
                v13 = 0;
            }
            else {
                int v14 = this.buffer[v9 + 1];
                int v15 = (v14 & 0xFF00000) >>> 20;
                v10 = v8;
                long v16 = (long)(v14 & 0xFFFFF);
                if(v15 <= 17) {
                    int v17 = this.buffer[v9 + 2];
                    int v18 = 1 << (v17 >>> 20);
                    int v19 = v17 & 0xFFFFF;
                    if(v19 != v5) {
                        if(v5 != 0xFFFFF) {
                            unsafe0.putInt(object1, ((long)v5), v6);
                        }
                        v20 = v19;
                        if(v20 != 0xFFFFF) {
                            v6 = unsafe0.getInt(object1, ((long)v20));
                        }
                        v5 = v20;
                    }
                    switch(v15) {
                        case 0: {
                            v21 = v7;
                            v22 = v9;
                            if((v8 & 7) == 1) {
                                UnsafeUtil.putDouble(object1, v16, ArrayDecoders.decodeDouble(arr_b1, v21));
                                v2 = v21 + 8;
                                v6 |= v18;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                arrayDecoders$Registers1 = arrayDecoders$Registers0;
                                continue;
                            }
                            break;
                        }
                        case 1: {
                            arr_b2 = arr_b1;
                            arrayDecoders$Registers2 = arrayDecoders$Registers1;
                            v21 = v7;
                            v22 = v9;
                            if((v8 & 7) == 5) {
                                UnsafeUtil.putFloat(object1, v16, ArrayDecoders.decodeFloat(arr_b2, v21));
                                v2 = v21 + 4;
                                v6 |= v18;
                                arrayDecoders$Registers1 = arrayDecoders$Registers2;
                                arr_b1 = arr_b2;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            break;
                        }
                        case 2: 
                        case 3: {
                            v21 = v7;
                            v22 = v9;
                            if((v8 & 7) == 0) {
                                int v23 = ArrayDecoders.decodeVarint64(arr_b1, v21, arrayDecoders$Registers1);
                                unsafe0.putLong(object1, v16, arrayDecoders$Registers1.long1);
                                v6 |= v18;
                                v2 = v23;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            break;
                        }
                        case 7: {
                            object3 = object1;
                            arrayDecoders$Registers3 = arrayDecoders$Registers1;
                            arr_b3 = arr_b1;
                            unsafe2 = unsafe0;
                            v25 = v7;
                            v22 = v9;
                            if((v8 & 7) == 0) {
                                v26 = ArrayDecoders.decodeVarint64(arr_b3, v25, arrayDecoders$Registers3);
                                UnsafeUtil.putBoolean(object3, v16, arrayDecoders$Registers3.long1 != 0L);
                                goto label_135;
                            }
                            v21 = v25;
                            unsafe0 = unsafe2;
                            object1 = object3;
                            break;
                        }
                        case 8: {
                            object3 = object1;
                            arrayDecoders$Registers3 = arrayDecoders$Registers1;
                            arr_b3 = arr_b1;
                            unsafe2 = unsafe0;
                            v25 = v7;
                            v22 = v9;
                            if((v8 & 7) == 2) {
                                v26 = (v14 & 0x20000000) == 0 ? ArrayDecoders.decodeString(arr_b3, v25, arrayDecoders$Registers3) : ArrayDecoders.decodeStringRequireUtf8(arr_b3, v25, arrayDecoders$Registers3);
                                unsafe2.putObject(object3, v16, arrayDecoders$Registers3.object1);
                                goto label_135;
                            }
                            v21 = v25;
                            unsafe0 = unsafe2;
                            object1 = object3;
                            break;
                        }
                        case 9: {
                            v22 = v9;
                            if((v8 & 7) == 2) {
                                Object object4 = this.mutableMessageFieldForMerge(object1, v22);
                                object3 = object1;
                                unsafe2 = unsafe0;
                                v26 = ArrayDecoders.mergeMessageField(object4, this.getMessageFieldSchema(v22), arr_b1, v7, v1, arrayDecoders$Registers1);
                                arr_b3 = arr_b1;
                                arrayDecoders$Registers3 = arrayDecoders$Registers1;
                                this.storeMessageField(object3, v22, object4);
                            label_135:
                                v6 |= v18;
                                arrayDecoders$Registers1 = arrayDecoders$Registers3;
                                object1 = object3;
                                arr_b1 = arr_b3;
                                v2 = v26;
                                unsafe0 = unsafe2;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            else {
                                v21 = v7;
                                break;
                            }
                            goto label_146;
                        }
                        case 10: {
                        label_146:
                            v22 = v9;
                            if((v8 & 7) == 2) {
                                v24 = ArrayDecoders.decodeBytes(arr_b1, v7, arrayDecoders$Registers1);
                                unsafe0.putObject(object1, v16, arrayDecoders$Registers1.object1);
                                v6 |= v18;
                                v2 = v24;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            else {
                                v21 = v7;
                                break;
                            }
                            goto label_157;
                        }
                        case 4: 
                        case 11: {
                            v21 = v7;
                            v22 = v9;
                            if((v8 & 7) == 0) {
                                v24 = ArrayDecoders.decodeVarint32(arr_b1, v21, arrayDecoders$Registers1);
                                unsafe0.putInt(object1, v16, arrayDecoders$Registers1.int1);
                                v6 |= v18;
                                v2 = v24;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            break;
                        }
                        case 12: {
                        label_157:
                            v22 = v9;
                            if((v8 & 7) == 0) {
                                v24 = ArrayDecoders.decodeVarint32(arr_b1, v7, arrayDecoders$Registers1);
                                unsafe0.putInt(object1, v16, arrayDecoders$Registers1.int1);
                                v6 |= v18;
                                v2 = v24;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            else {
                                v21 = v7;
                                break;
                            }
                            goto label_168;
                        }
                        case 6: 
                        case 13: {
                        label_92:
                            object3 = object1;
                            unsafe2 = unsafe0;
                            v25 = v7;
                            v22 = v9;
                            if((v8 & 7) == 5) {
                                unsafe2.putInt(object3, v16, ArrayDecoders.decodeFixed32(arr_b1, v25));
                                v2 = v25 + 4;
                                v6 |= v18;
                                object1 = object3;
                                unsafe0 = unsafe2;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            v21 = v25;
                            unsafe0 = unsafe2;
                            object1 = object3;
                            break;
                        }
                        case 5: 
                        case 14: {
                            arr_b2 = arr_b1;
                            v22 = v9;
                            if((v8 & 7) == 1) {
                                arrayDecoders$Registers2 = arrayDecoders$Registers1;
                                unsafe0.putLong(object1, v16, ArrayDecoders.decodeFixed64(arr_b2, v7));
                                v2 = v7 + 8;
                                v6 |= v18;
                                arrayDecoders$Registers1 = arrayDecoders$Registers2;
                                arr_b1 = arr_b2;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            else {
                                v21 = v7;
                                break;
                            }
                            goto label_92;
                        }
                        case 15: {
                        label_168:
                            v22 = v9;
                            if((v8 & 7) == 0) {
                                v24 = ArrayDecoders.decodeVarint32(arr_b1, v7, arrayDecoders$Registers1);
                                unsafe0.putInt(object1, v16, -(arrayDecoders$Registers1.int1 & 1) ^ arrayDecoders$Registers1.int1 >>> 1);
                                v6 |= v18;
                                v2 = v24;
                                v4 = v22;
                                v3 = v8 >>> 3;
                                continue;
                            }
                            else {
                                v21 = v7;
                                break;
                            }
                            goto label_179;
                        }
                        case 16: {
                        label_179:
                            if((v8 & 7) == 0) {
                                int v27 = ArrayDecoders.decodeVarint64(arr_b1, v7, arrayDecoders$Registers1);
                                unsafe0.putLong(object1, v16, -(arrayDecoders$Registers1.long1 & 1L) ^ arrayDecoders$Registers1.long1 >>> 1);
                                v6 |= v18;
                                v2 = v27;
                                v3 = v8 >>> 3;
                                v4 = v9;
                                continue;
                            }
                            else {
                                v21 = v7;
                                v22 = v9;
                            }
                            break;
                        }
                        default: {
                            v21 = v7;
                            v22 = v9;
                        }
                    }
                    unsafe1 = unsafe0;
                    v11 = v21;
                    v13 = v22;
                    v12 = v8 >>> 3;
                    object2 = object1;
                }
                else {
                    if(v15 == 27) {
                        if((v8 & 7) == 2) {
                            ProtobufList internal$ProtobufList0 = (ProtobufList)unsafe0.getObject(object1, v16);
                            if(!internal$ProtobufList0.isModifiable()) {
                                int v28 = internal$ProtobufList0.size();
                                internal$ProtobufList0 = internal$ProtobufList0.mutableCopyWithCapacity((v28 == 0 ? 10 : v28 * 2));
                                unsafe0.putObject(object1, v16, internal$ProtobufList0);
                            }
                            int v29 = ArrayDecoders.decodeMessageList(this.getMessageFieldSchema(v9), v10, arr_b1, v7, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                            arr_b1 = arr_b;
                            arrayDecoders$Registers1 = arrayDecoders$Registers0;
                            v2 = v29;
                            v3 = v8 >>> 3;
                            v4 = v9;
                            object1 = object0;
                            continue;
                        }
                        else {
                            unsafe1 = unsafe0;
                            v30 = v6;
                            v13 = v9;
                            v31 = v5;
                            object2 = object0;
                            v11 = v7;
                            v12 = v8 >>> 3;
                            goto label_279;
                        }
                        goto label_218;
                    }
                    else {
                    label_218:
                        if(v15 <= 49) {
                            v30 = v6;
                            unsafe1 = unsafe0;
                            v31 = v5;
                            int v32 = this.parseRepeatedField(object0, arr_b, v7, v1, v10, v8 >>> 3, v8 & 7, v9, ((long)v14), v15, v16, arrayDecoders$Registers0);
                            v13 = v9;
                            if(v32 == v7) {
                                object2 = object0;
                                v11 = v32;
                                v12 = v8 >>> 3;
                                goto label_279;
                            }
                            else {
                                object1 = object0;
                                arrayDecoders$Registers1 = arrayDecoders$Registers0;
                                v2 = v32;
                                v3 = v8 >>> 3;
                                v5 = v31;
                                v4 = v13;
                                v6 = v30;
                                unsafe0 = unsafe1;
                                arr_b1 = arr_b;
                                continue;
                            }
                            goto label_239;
                        }
                        else {
                        label_239:
                            unsafe1 = unsafe0;
                            v30 = v6;
                            v13 = v9;
                            v31 = v5;
                            if(v15 == 50) {
                                if((v8 & 7) == 2) {
                                    int v33 = this.parseMapField(object0, arr_b, v7, v1, v13, v16, arrayDecoders$Registers0);
                                    if(v33 == v7) {
                                        object2 = object0;
                                        v11 = v33;
                                        goto label_262;
                                    }
                                    else {
                                        object1 = object0;
                                        arr_b1 = arr_b;
                                        arrayDecoders$Registers1 = arrayDecoders$Registers0;
                                        v2 = v33;
                                        v3 = v8 >>> 3;
                                        v5 = v31;
                                        v4 = v13;
                                        v6 = v30;
                                        unsafe0 = unsafe1;
                                        continue;
                                    }
                                    goto label_260;
                                }
                                else {
                                label_260:
                                    object2 = object0;
                                    v11 = v7;
                                }
                            label_262:
                                v12 = v8 >>> 3;
                            }
                            else {
                                v12 = v8 >>> 3;
                                int v34 = this.parseOneofField(object0, arr_b, v7, v1, v10, v12, v8 & 7, v14, v15, v16, v13, arrayDecoders$Registers0);
                                object2 = object0;
                                if(v34 == v7) {
                                    v11 = v34;
                                }
                                else {
                                    arrayDecoders$Registers1 = arrayDecoders$Registers0;
                                    v3 = v12;
                                    v2 = v34;
                                    object1 = object2;
                                    v5 = v31;
                                    v4 = v13;
                                    v6 = v30;
                                    unsafe0 = unsafe1;
                                    arr_b1 = arr_b;
                                    continue;
                                }
                            }
                        }
                    }
                label_279:
                    v5 = v31;
                    v6 = v30;
                }
            }
            arr_b1 = arr_b;
            arrayDecoders$Registers1 = arrayDecoders$Registers0;
            v3 = v12;
            object1 = object2;
            v4 = v13;
            unsafe0 = unsafe1;
            v2 = ArrayDecoders.decodeUnknownField(v10, arr_b, v11, v1, MessageSchema.getMutableUnknownFields(object2), arrayDecoders$Registers0);
        }
        if(v5 != 0xFFFFF) {
            unsafe0.putInt(object1, ((long)v5), v6);
        }
        if(v2 != v1) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return v2;
    }

    private int parseRepeatedField(Object object0, byte[] arr_b, int v, int v1, int v2, int v3, int v4, int v5, long v6, int v7, long v8, Registers arrayDecoders$Registers0) throws IOException {
        int v10;
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        ProtobufList internal$ProtobufList0 = (ProtobufList)unsafe0.getObject(object0, v8);
        if(!internal$ProtobufList0.isModifiable()) {
            int v9 = internal$ProtobufList0.size();
            internal$ProtobufList0 = internal$ProtobufList0.mutableCopyWithCapacity((v9 == 0 ? 10 : v9 * 2));
            unsafe0.putObject(object0, v8, internal$ProtobufList0);
        }
        switch(v7) {
            case 26: {
                if(v4 == 2) {
                    return (v6 & 0x20000000L) == 0L ? ArrayDecoders.decodeStringList(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0) : ArrayDecoders.decodeStringListRequireUtf8(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                }
                return v;
            }
            case 27: {
                return v4 == 2 ? ArrayDecoders.decodeMessageList(this.getMessageFieldSchema(v5), v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0) : v;
            }
            case 28: {
                return v4 == 2 ? ArrayDecoders.decodeBytesList(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0) : v;
            }
            case 18: 
            case 35: {
                switch(v4) {
                    case 1: {
                        return ArrayDecoders.decodeDoubleList(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 2: {
                        return ArrayDecoders.decodePackedDoubleList(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 19: 
            case 36: {
                switch(v4) {
                    case 2: {
                        return ArrayDecoders.decodePackedFloatList(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 5: {
                        return ArrayDecoders.decodeFloatList(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 20: 
            case 21: 
            case 37: 
            case 38: {
                switch(v4) {
                    case 0: {
                        return ArrayDecoders.decodeVarint64List(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 2: {
                        return ArrayDecoders.decodePackedVarint64List(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 25: 
            case 42: {
                switch(v4) {
                    case 0: {
                        return ArrayDecoders.decodeBoolList(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 2: {
                        return ArrayDecoders.decodePackedBoolList(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 22: 
            case 29: 
            case 39: 
            case 43: {
                switch(v4) {
                    case 0: {
                        return ArrayDecoders.decodeVarint32List(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 2: {
                        return ArrayDecoders.decodePackedVarint32List(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 30: 
            case 44: {
                switch(v4) {
                    case 0: {
                        v10 = ArrayDecoders.decodeVarint32List(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                        break;
                    }
                    case 2: {
                        v10 = ArrayDecoders.decodePackedVarint32List(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                        break;
                    }
                    default: {
                        return v;
                    }
                }
                SchemaUtil.filterUnknownEnumList(object0, v3, internal$ProtobufList0, this.getEnumFieldVerifier(v5), null, this.unknownFieldSchema);
                return v10;
            }
            case 24: 
            case 0x1F: 
            case 41: 
            case 45: {
                switch(v4) {
                    case 2: {
                        return ArrayDecoders.decodePackedFixed32List(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 5: {
                        return ArrayDecoders.decodeFixed32List(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 23: 
            case 0x20: 
            case 40: 
            case 46: {
                switch(v4) {
                    case 1: {
                        return ArrayDecoders.decodeFixed64List(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 2: {
                        return ArrayDecoders.decodePackedFixed64List(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 33: 
            case 0x2F: {
                switch(v4) {
                    case 0: {
                        return ArrayDecoders.decodeSInt32List(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 2: {
                        return ArrayDecoders.decodePackedSInt32List(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 34: 
            case 0x30: {
                switch(v4) {
                    case 0: {
                        return ArrayDecoders.decodeSInt64List(v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    case 2: {
                        return ArrayDecoders.decodePackedSInt64List(arr_b, v, internal$ProtobufList0, arrayDecoders$Registers0);
                    }
                    default: {
                        return v;
                    }
                }
            }
            case 49: {
                return v4 == 3 ? ArrayDecoders.decodeGroupList(this.getMessageFieldSchema(v5), v2, arr_b, v, v1, internal$ProtobufList0, arrayDecoders$Registers0) : v;
            }
            default: {
                return v;
            }
        }
    }

    private int positionForFieldNumber(int v) {
        return v < this.minFieldNumber || v > this.maxFieldNumber ? -1 : this.slowPositionForFieldNumber(v, 0);
    }

    private int positionForFieldNumber(int v, int v1) {
        return v < this.minFieldNumber || v > this.maxFieldNumber ? -1 : this.slowPositionForFieldNumber(v, v1);
    }

    private int presenceMaskAndOffsetAt(int v) {
        return this.buffer[v + 2];
    }

    private void readGroupList(Object object0, long v, Reader reader0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        reader0.readGroupList(this.listFieldSchema.mutableListAt(object0, v), schema0, extensionRegistryLite0);
    }

    private void readMessageList(Object object0, int v, Reader reader0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        reader0.readMessageList(this.listFieldSchema.mutableListAt(object0, MessageSchema.offset(v)), schema0, extensionRegistryLite0);
    }

    private void readString(Object object0, int v, Reader reader0) throws IOException {
        if(MessageSchema.isEnforceUtf8(v)) {
            UnsafeUtil.putObject(object0, ((long)(v & 0xFFFFF)), reader0.readStringRequireUtf8());
            return;
        }
        if(this.lite) {
            UnsafeUtil.putObject(object0, ((long)(v & 0xFFFFF)), reader0.readString());
            return;
        }
        UnsafeUtil.putObject(object0, ((long)(v & 0xFFFFF)), reader0.readBytes());
    }

    private void readStringList(Object object0, int v, Reader reader0) throws IOException {
        if(MessageSchema.isEnforceUtf8(v)) {
            reader0.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(object0, ((long)(v & 0xFFFFF))));
            return;
        }
        reader0.readStringList(this.listFieldSchema.mutableListAt(object0, ((long)(v & 0xFFFFF))));
    }

    private static Field reflectField(Class class0, String s) {
        try {
            return class0.getDeclaredField(s);
        }
        catch(NoSuchFieldException unused_ex) {
            Field[] arr_field = class0.getDeclaredFields();
            for(int v = 0; v < arr_field.length; ++v) {
                Field field0 = arr_field[v];
                if(s.equals(field0.getName())) {
                    return field0;
                }
            }
            throw new RuntimeException("Field " + s + " for " + class0.getName() + " not found. Known fields are " + Arrays.toString(arr_field));
        }
    }

    private void setFieldPresent(Object object0, int v) {
        int v1 = this.presenceMaskAndOffsetAt(v);
        if(((long)(0xFFFFF & v1)) == 0xFFFFFL) {
            return;
        }
        UnsafeUtil.putInt(object0, ((long)(0xFFFFF & v1)), 1 << (v1 >>> 20) | UnsafeUtil.getInt(object0, ((long)(0xFFFFF & v1))));
    }

    private void setOneofPresent(Object object0, int v, int v1) {
        UnsafeUtil.putInt(object0, ((long)(this.presenceMaskAndOffsetAt(v1) & 0xFFFFF)), v);
    }

    private int slowPositionForFieldNumber(int v, int v1) {
        int v2 = this.buffer.length / 3 - 1;
        while(v1 <= v2) {
            int v3 = v2 + v1 >>> 1;
            int v4 = v3 * 3;
            int v5 = this.numberAt(v4);
            if(v == v5) {
                return v4;
            }
            if(v < v5) {
                v2 = v3 - 1;
            }
            else {
                v1 = v3 + 1;
            }
        }
        return -1;
    }

    private static void storeFieldData(FieldInfo fieldInfo0, int[] arr_v, int v, Object[] arr_object) {
        int v5;
        int v4;
        int v3;
        int v2;
        OneofInfo oneofInfo0 = fieldInfo0.getOneof();
        int v1 = 0;
        if(oneofInfo0 == null) {
            com.google.crypto.tink.shaded.protobuf.FieldType fieldType0 = fieldInfo0.getType();
            v3 = (int)UnsafeUtil.objectFieldOffset(fieldInfo0.getField());
            v2 = fieldType0.id();
            if(fieldType0.isList() || fieldType0.isMap()) {
                v4 = fieldInfo0.getCachedSizeField() == null ? 0 : ((int)UnsafeUtil.objectFieldOffset(fieldInfo0.getCachedSizeField()));
                v5 = 0;
            }
            else {
                Field field0 = fieldInfo0.getPresenceField();
                v4 = field0 == null ? 0xFFFFF : ((int)UnsafeUtil.objectFieldOffset(field0));
                v5 = Integer.numberOfTrailingZeros(fieldInfo0.getPresenceMask());
            }
        }
        else {
            v2 = fieldInfo0.getType().id() + 51;
            v3 = (int)UnsafeUtil.objectFieldOffset(oneofInfo0.getValueField());
            v4 = (int)UnsafeUtil.objectFieldOffset(oneofInfo0.getCaseField());
            v5 = 0;
        }
        arr_v[v] = fieldInfo0.getFieldNumber();
        int v6 = fieldInfo0.isEnforceUtf8() ? 0x20000000 : 0;
        if(fieldInfo0.isRequired()) {
            v1 = 0x10000000;
        }
        arr_v[v + 1] = v1 | v6 | v2 << 20 | v3;
        arr_v[v + 2] = v4 | v5 << 20;
        Class class0 = fieldInfo0.getMessageFieldClass();
        if(fieldInfo0.getMapDefaultEntry() == null) {
            if(class0 != null) {
                arr_object[v / 3 * 2 + 1] = class0;
                return;
            }
            if(fieldInfo0.getEnumVerifier() != null) {
                arr_object[v / 3 * 2 + 1] = fieldInfo0.getEnumVerifier();
            }
        }
        else {
            int v7 = v / 3 * 2;
            arr_object[v7] = fieldInfo0.getMapDefaultEntry();
            if(class0 != null) {
                arr_object[v7 + 1] = class0;
                return;
            }
            if(fieldInfo0.getEnumVerifier() != null) {
                arr_object[v7 + 1] = fieldInfo0.getEnumVerifier();
            }
        }
    }

    private void storeMessageField(Object object0, int v, Object object1) {
        long v1 = MessageSchema.offset(this.typeAndOffsetAt(v));
        MessageSchema.UNSAFE.putObject(object0, v1, object1);
        this.setFieldPresent(object0, v);
    }

    private void storeOneofMessageField(Object object0, int v, int v1, Object object1) {
        long v2 = MessageSchema.offset(this.typeAndOffsetAt(v1));
        MessageSchema.UNSAFE.putObject(object0, v2, object1);
        this.setOneofPresent(object0, v, v1);
    }

    private static int type(int value) [...] // Inlined contents

    private int typeAndOffsetAt(int v) {
        return this.buffer[v + 1];
    }

    private void writeFieldsInAscendingOrderProto2(Object object0, Writer writer0) throws IOException {
        int v8;
        Map.Entry map$Entry0;
        Iterator iterator0;
        if(this.hasExtensions) {
            FieldSet fieldSet0 = this.extensionSchema.getExtensions(object0);
            if(fieldSet0.isEmpty()) {
                iterator0 = null;
                map$Entry0 = null;
            }
            else {
                iterator0 = fieldSet0.iterator();
                Object object1 = iterator0.next();
                map$Entry0 = (Map.Entry)object1;
            }
        }
        else {
            iterator0 = null;
            map$Entry0 = null;
        }
        Unsafe unsafe0 = MessageSchema.UNSAFE;
        int v1 = 0xFFFFF;
        int v2 = 0;
        for(int v = 0; v < this.buffer.length; v += 3) {
            int v3 = this.typeAndOffsetAt(v);
            int v4 = this.numberAt(v);
            int v5 = (v3 & 0xFF00000) >>> 20;
            if(v5 <= 17) {
                int v6 = this.buffer[v + 2];
                int v7 = v6 & 0xFFFFF;
                if(v7 != v1) {
                    v2 = unsafe0.getInt(object0, ((long)v7));
                    v1 = v7;
                }
                v8 = 1 << (v6 >>> 20);
            }
            else {
                v8 = 0;
            }
            while(map$Entry0 != null && this.extensionSchema.extensionNumber(map$Entry0) <= v4) {
                this.extensionSchema.serializeExtension(writer0, map$Entry0);
                if(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    map$Entry0 = (Map.Entry)object2;
                }
                else {
                    map$Entry0 = null;
                }
            }
            long v9 = (long)(v3 & 0xFFFFF);
            switch(v5) {
                case 0: {
                    if((v8 & v2) != 0) {
                        writer0.writeDouble(v4, MessageSchema.doubleAt(object0, v9));
                    }
                    break;
                }
                case 1: {
                    if((v8 & v2) != 0) {
                        writer0.writeFloat(v4, MessageSchema.floatAt(object0, v9));
                    }
                    break;
                }
                case 2: {
                    if((v8 & v2) != 0) {
                        writer0.writeInt64(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 3: {
                    if((v8 & v2) != 0) {
                        writer0.writeUInt64(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 4: {
                    if((v8 & v2) != 0) {
                        writer0.writeInt32(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 5: {
                    if((v8 & v2) != 0) {
                        writer0.writeFixed64(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 6: {
                    if((v8 & v2) != 0) {
                        writer0.writeFixed32(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 7: {
                    if((v8 & v2) != 0) {
                        writer0.writeBool(v4, MessageSchema.booleanAt(object0, v9));
                    }
                    break;
                }
                case 8: {
                    if((v8 & v2) != 0) {
                        this.writeString(v4, unsafe0.getObject(object0, v9), writer0);
                    }
                    break;
                }
                case 9: {
                    if((v8 & v2) != 0) {
                        writer0.writeMessage(v4, unsafe0.getObject(object0, v9), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 10: {
                    if((v8 & v2) != 0) {
                        writer0.writeBytes(v4, ((ByteString)unsafe0.getObject(object0, v9)));
                    }
                    break;
                }
                case 11: {
                    if((v8 & v2) != 0) {
                        writer0.writeUInt32(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 12: {
                    if((v8 & v2) != 0) {
                        writer0.writeEnum(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 13: {
                    if((v8 & v2) != 0) {
                        writer0.writeSFixed32(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 14: {
                    if((v8 & v2) != 0) {
                        writer0.writeSFixed64(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 15: {
                    if((v8 & v2) != 0) {
                        writer0.writeSInt32(v4, unsafe0.getInt(object0, v9));
                    }
                    break;
                }
                case 16: {
                    if((v8 & v2) != 0) {
                        writer0.writeSInt64(v4, unsafe0.getLong(object0, v9));
                    }
                    break;
                }
                case 17: {
                    if((v8 & v2) != 0) {
                        writer0.writeGroup(v4, unsafe0.getObject(object0, v9), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 18: {
                    SchemaUtil.writeDoubleList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 19: {
                    SchemaUtil.writeFloatList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 20: {
                    SchemaUtil.writeInt64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 21: {
                    SchemaUtil.writeUInt64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 22: {
                    SchemaUtil.writeInt32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 23: {
                    SchemaUtil.writeFixed64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 24: {
                    SchemaUtil.writeFixed32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 25: {
                    SchemaUtil.writeBoolList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 26: {
                    SchemaUtil.writeStringList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0);
                    break;
                }
                case 27: {
                    SchemaUtil.writeMessageList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, this.getMessageFieldSchema(v));
                    break;
                }
                case 28: {
                    SchemaUtil.writeBytesList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0);
                    break;
                }
                case 29: {
                    SchemaUtil.writeUInt32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 30: {
                    SchemaUtil.writeEnumList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 0x1F: {
                    SchemaUtil.writeSFixed32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 0x20: {
                    SchemaUtil.writeSFixed64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 33: {
                    SchemaUtil.writeSInt32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 34: {
                    SchemaUtil.writeSInt64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, false);
                    break;
                }
                case 35: {
                    SchemaUtil.writeDoubleList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 36: {
                    SchemaUtil.writeFloatList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 37: {
                    SchemaUtil.writeInt64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 38: {
                    SchemaUtil.writeUInt64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 39: {
                    SchemaUtil.writeInt32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 40: {
                    SchemaUtil.writeFixed64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 41: {
                    SchemaUtil.writeFixed32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 42: {
                    SchemaUtil.writeBoolList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 43: {
                    SchemaUtil.writeUInt32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 44: {
                    SchemaUtil.writeEnumList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 45: {
                    SchemaUtil.writeSFixed32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 46: {
                    SchemaUtil.writeSFixed64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 0x2F: {
                    SchemaUtil.writeSInt32List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 0x30: {
                    SchemaUtil.writeSInt64List(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, true);
                    break;
                }
                case 49: {
                    SchemaUtil.writeGroupList(this.numberAt(v), ((List)unsafe0.getObject(object0, v9)), writer0, this.getMessageFieldSchema(v));
                    break;
                }
                case 50: {
                    this.writeMapHelper(writer0, v4, unsafe0.getObject(object0, v9), v);
                    break;
                }
                case 51: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeDouble(v4, MessageSchema.oneofDoubleAt(object0, v9));
                    }
                    break;
                }
                case 52: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeFloat(v4, MessageSchema.oneofFloatAt(object0, v9));
                    }
                    break;
                }
                case 53: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeInt64(v4, MessageSchema.oneofLongAt(object0, v9));
                    }
                    break;
                }
                case 54: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeUInt64(v4, MessageSchema.oneofLongAt(object0, v9));
                    }
                    break;
                }
                case 55: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeInt32(v4, MessageSchema.oneofIntAt(object0, v9));
                    }
                    break;
                }
                case 56: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeFixed64(v4, MessageSchema.oneofLongAt(object0, v9));
                    }
                    break;
                }
                case 57: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeFixed32(v4, MessageSchema.oneofIntAt(object0, v9));
                    }
                    break;
                }
                case 58: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeBool(v4, MessageSchema.oneofBooleanAt(object0, v9));
                    }
                    break;
                }
                case 59: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        this.writeString(v4, unsafe0.getObject(object0, v9), writer0);
                    }
                    break;
                }
                case 60: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeMessage(v4, unsafe0.getObject(object0, v9), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 61: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeBytes(v4, ((ByteString)unsafe0.getObject(object0, v9)));
                    }
                    break;
                }
                case 62: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeUInt32(v4, MessageSchema.oneofIntAt(object0, v9));
                    }
                    break;
                }
                case 0x3F: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeEnum(v4, MessageSchema.oneofIntAt(object0, v9));
                    }
                    break;
                }
                case 0x40: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeSFixed32(v4, MessageSchema.oneofIntAt(object0, v9));
                    }
                    break;
                }
                case 65: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeSFixed64(v4, MessageSchema.oneofLongAt(object0, v9));
                    }
                    break;
                }
                case 66: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeSInt32(v4, MessageSchema.oneofIntAt(object0, v9));
                    }
                    break;
                }
                case 67: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeSInt64(v4, MessageSchema.oneofLongAt(object0, v9));
                    }
                    break;
                }
                case 68: {
                    if(this.isOneofPresent(object0, v4, v)) {
                        writer0.writeGroup(v4, unsafe0.getObject(object0, v9), this.getMessageFieldSchema(v));
                    }
                }
            }
        }
        while(map$Entry0 != null) {
            this.extensionSchema.serializeExtension(writer0, map$Entry0);
            if(iterator0.hasNext()) {
                Object object3 = iterator0.next();
                map$Entry0 = (Map.Entry)object3;
            }
            else {
                map$Entry0 = null;
            }
        }
        this.writeUnknownInMessageTo(this.unknownFieldSchema, object0, writer0);
    }

    private void writeFieldsInAscendingOrderProto3(Object object0, Writer writer0) throws IOException {
        Map.Entry map$Entry0;
        Iterator iterator0;
        if(this.hasExtensions) {
            FieldSet fieldSet0 = this.extensionSchema.getExtensions(object0);
            if(fieldSet0.isEmpty()) {
                iterator0 = null;
                map$Entry0 = null;
            }
            else {
                iterator0 = fieldSet0.iterator();
                Object object1 = iterator0.next();
                map$Entry0 = (Map.Entry)object1;
            }
        }
        else {
            iterator0 = null;
            map$Entry0 = null;
        }
        for(int v = 0; v < this.buffer.length; v += 3) {
            int v1 = this.typeAndOffsetAt(v);
            int v2 = this.numberAt(v);
            while(map$Entry0 != null && this.extensionSchema.extensionNumber(map$Entry0) <= v2) {
                this.extensionSchema.serializeExtension(writer0, map$Entry0);
                if(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    map$Entry0 = (Map.Entry)object2;
                }
                else {
                    map$Entry0 = null;
                }
            }
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeDouble(v2, MessageSchema.doubleAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 1: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeFloat(v2, MessageSchema.floatAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 2: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeInt64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 3: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeUInt64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 4: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeInt32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 5: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeFixed64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 6: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeFixed32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 7: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeBool(v2, MessageSchema.booleanAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 8: {
                    if(this.isFieldPresent(object0, v)) {
                        this.writeString(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), writer0);
                    }
                    break;
                }
                case 9: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeMessage(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 10: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeBytes(v2, ((ByteString)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))));
                    }
                    break;
                }
                case 11: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeUInt32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 12: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeEnum(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 13: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSFixed32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 14: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSFixed64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 15: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSInt32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 16: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSInt64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 17: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeGroup(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 18: {
                    SchemaUtil.writeDoubleList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 19: {
                    SchemaUtil.writeFloatList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 20: {
                    SchemaUtil.writeInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 21: {
                    SchemaUtil.writeUInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 22: {
                    SchemaUtil.writeInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 23: {
                    SchemaUtil.writeFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 24: {
                    SchemaUtil.writeFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 25: {
                    SchemaUtil.writeBoolList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 26: {
                    SchemaUtil.writeStringList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0);
                    break;
                }
                case 27: {
                    SchemaUtil.writeMessageList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, this.getMessageFieldSchema(v));
                    break;
                }
                case 28: {
                    SchemaUtil.writeBytesList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0);
                    break;
                }
                case 29: {
                    SchemaUtil.writeUInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 30: {
                    SchemaUtil.writeEnumList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 0x1F: {
                    SchemaUtil.writeSFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 0x20: {
                    SchemaUtil.writeSFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 33: {
                    SchemaUtil.writeSInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 34: {
                    SchemaUtil.writeSInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 35: {
                    SchemaUtil.writeDoubleList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 36: {
                    SchemaUtil.writeFloatList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 37: {
                    SchemaUtil.writeInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 38: {
                    SchemaUtil.writeUInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 39: {
                    SchemaUtil.writeInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 40: {
                    SchemaUtil.writeFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 41: {
                    SchemaUtil.writeFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 42: {
                    SchemaUtil.writeBoolList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 43: {
                    SchemaUtil.writeUInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 44: {
                    SchemaUtil.writeEnumList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 45: {
                    SchemaUtil.writeSFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 46: {
                    SchemaUtil.writeSFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 0x2F: {
                    SchemaUtil.writeSInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 0x30: {
                    SchemaUtil.writeSInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 49: {
                    SchemaUtil.writeGroupList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, this.getMessageFieldSchema(v));
                    break;
                }
                case 50: {
                    this.writeMapHelper(writer0, v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), v);
                    break;
                }
                case 51: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeDouble(v2, MessageSchema.oneofDoubleAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 52: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeFloat(v2, MessageSchema.oneofFloatAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 53: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeInt64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 54: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeUInt64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 55: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeInt32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 56: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeFixed64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 57: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeFixed32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 58: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeBool(v2, MessageSchema.oneofBooleanAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 59: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        this.writeString(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), writer0);
                    }
                    break;
                }
                case 60: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeMessage(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 61: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeBytes(v2, ((ByteString)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))));
                    }
                    break;
                }
                case 62: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeUInt32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 0x3F: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeEnum(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 0x40: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSFixed32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 65: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSFixed64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 66: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSInt32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 67: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSInt64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 68: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeGroup(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                }
            }
        }
        while(map$Entry0 != null) {
            this.extensionSchema.serializeExtension(writer0, map$Entry0);
            if(iterator0.hasNext()) {
                Object object3 = iterator0.next();
                map$Entry0 = (Map.Entry)object3;
            }
            else {
                map$Entry0 = null;
            }
        }
        this.writeUnknownInMessageTo(this.unknownFieldSchema, object0, writer0);
    }

    private void writeFieldsInDescendingOrder(Object object0, Writer writer0) throws IOException {
        Map.Entry map$Entry0;
        Iterator iterator0;
        this.writeUnknownInMessageTo(this.unknownFieldSchema, object0, writer0);
        if(this.hasExtensions) {
            FieldSet fieldSet0 = this.extensionSchema.getExtensions(object0);
            if(fieldSet0.isEmpty()) {
                iterator0 = null;
                map$Entry0 = null;
            }
            else {
                iterator0 = fieldSet0.descendingIterator();
                Object object1 = iterator0.next();
                map$Entry0 = (Map.Entry)object1;
            }
        }
        else {
            iterator0 = null;
            map$Entry0 = null;
        }
        for(int v = this.buffer.length - 3; v >= 0; v -= 3) {
            int v1 = this.typeAndOffsetAt(v);
            int v2 = this.numberAt(v);
            while(map$Entry0 != null && this.extensionSchema.extensionNumber(map$Entry0) > v2) {
                this.extensionSchema.serializeExtension(writer0, map$Entry0);
                if(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    map$Entry0 = (Map.Entry)object2;
                }
                else {
                    map$Entry0 = null;
                }
            }
            switch((v1 & 0xFF00000) >>> 20) {
                case 0: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeDouble(v2, MessageSchema.doubleAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 1: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeFloat(v2, MessageSchema.floatAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 2: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeInt64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 3: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeUInt64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 4: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeInt32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 5: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeFixed64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 6: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeFixed32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 7: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeBool(v2, MessageSchema.booleanAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 8: {
                    if(this.isFieldPresent(object0, v)) {
                        this.writeString(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), writer0);
                    }
                    break;
                }
                case 9: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeMessage(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 10: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeBytes(v2, ((ByteString)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))));
                    }
                    break;
                }
                case 11: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeUInt32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 12: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeEnum(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 13: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSFixed32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 14: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSFixed64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 15: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSInt32(v2, MessageSchema.intAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 16: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeSInt64(v2, MessageSchema.longAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 17: {
                    if(this.isFieldPresent(object0, v)) {
                        writer0.writeGroup(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 18: {
                    SchemaUtil.writeDoubleList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 19: {
                    SchemaUtil.writeFloatList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 20: {
                    SchemaUtil.writeInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 21: {
                    SchemaUtil.writeUInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 22: {
                    SchemaUtil.writeInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 23: {
                    SchemaUtil.writeFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 24: {
                    SchemaUtil.writeFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 25: {
                    SchemaUtil.writeBoolList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 26: {
                    SchemaUtil.writeStringList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0);
                    break;
                }
                case 27: {
                    SchemaUtil.writeMessageList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, this.getMessageFieldSchema(v));
                    break;
                }
                case 28: {
                    SchemaUtil.writeBytesList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0);
                    break;
                }
                case 29: {
                    SchemaUtil.writeUInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 30: {
                    SchemaUtil.writeEnumList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 0x1F: {
                    SchemaUtil.writeSFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 0x20: {
                    SchemaUtil.writeSFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 33: {
                    SchemaUtil.writeSInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 34: {
                    SchemaUtil.writeSInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, false);
                    break;
                }
                case 35: {
                    SchemaUtil.writeDoubleList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 36: {
                    SchemaUtil.writeFloatList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 37: {
                    SchemaUtil.writeInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 38: {
                    SchemaUtil.writeUInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 39: {
                    SchemaUtil.writeInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 40: {
                    SchemaUtil.writeFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 41: {
                    SchemaUtil.writeFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 42: {
                    SchemaUtil.writeBoolList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 43: {
                    SchemaUtil.writeUInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 44: {
                    SchemaUtil.writeEnumList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 45: {
                    SchemaUtil.writeSFixed32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 46: {
                    SchemaUtil.writeSFixed64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 0x2F: {
                    SchemaUtil.writeSInt32List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 0x30: {
                    SchemaUtil.writeSInt64List(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, true);
                    break;
                }
                case 49: {
                    SchemaUtil.writeGroupList(this.numberAt(v), ((List)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))), writer0, this.getMessageFieldSchema(v));
                    break;
                }
                case 50: {
                    this.writeMapHelper(writer0, v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), v);
                    break;
                }
                case 51: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeDouble(v2, MessageSchema.oneofDoubleAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 52: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeFloat(v2, MessageSchema.oneofFloatAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 53: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeInt64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 54: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeUInt64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 55: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeInt32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 56: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeFixed64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 57: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeFixed32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 58: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeBool(v2, MessageSchema.oneofBooleanAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 59: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        this.writeString(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), writer0);
                    }
                    break;
                }
                case 60: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeMessage(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                    break;
                }
                case 61: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeBytes(v2, ((ByteString)UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF)))));
                    }
                    break;
                }
                case 62: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeUInt32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 0x3F: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeEnum(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 0x40: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSFixed32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 65: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSFixed64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 66: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSInt32(v2, MessageSchema.oneofIntAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 67: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeSInt64(v2, MessageSchema.oneofLongAt(object0, ((long)(v1 & 0xFFFFF))));
                    }
                    break;
                }
                case 68: {
                    if(this.isOneofPresent(object0, v2, v)) {
                        writer0.writeGroup(v2, UnsafeUtil.getObject(object0, ((long)(v1 & 0xFFFFF))), this.getMessageFieldSchema(v));
                    }
                }
            }
        }
        while(map$Entry0 != null) {
            this.extensionSchema.serializeExtension(writer0, map$Entry0);
            if(iterator0.hasNext()) {
                Object object3 = iterator0.next();
                map$Entry0 = (Map.Entry)object3;
            }
            else {
                map$Entry0 = null;
            }
        }
    }

    private void writeMapHelper(Writer writer0, int v, Object object0, int v1) throws IOException {
        if(object0 != null) {
            Object object1 = this.getMapFieldDefaultEntry(v1);
            writer0.writeMap(v, this.mapFieldSchema.forMapMetadata(object1), this.mapFieldSchema.forMapData(object0));
        }
    }

    private void writeString(int v, Object object0, Writer writer0) throws IOException {
        if(object0 instanceof String) {
            writer0.writeString(v, ((String)object0));
            return;
        }
        writer0.writeBytes(v, ((ByteString)object0));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Schema
    public void writeTo(Object object0, Writer writer0) throws IOException {
        if(writer0.fieldOrder() == FieldOrder.DESCENDING) {
            this.writeFieldsInDescendingOrder(object0, writer0);
            return;
        }
        if(this.proto3) {
            this.writeFieldsInAscendingOrderProto3(object0, writer0);
            return;
        }
        this.writeFieldsInAscendingOrderProto2(object0, writer0);
    }

    private void writeUnknownInMessageTo(UnknownFieldSchema unknownFieldSchema0, Object object0, Writer writer0) throws IOException {
        unknownFieldSchema0.writeTo(unknownFieldSchema0.getFromMessage(object0), writer0);
    }

    class com.google.crypto.tink.shaded.protobuf.MessageSchema.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.BOOL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.DOUBLE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MessageSchema.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

