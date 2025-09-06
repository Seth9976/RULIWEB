package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

public class MapEntryLite {
    static class Metadata {
        public final Object defaultKey;
        public final Object defaultValue;
        public final FieldType keyType;
        public final FieldType valueType;

        public Metadata(FieldType wireFormat$FieldType0, Object object0, FieldType wireFormat$FieldType1, Object object1) {
            this.keyType = wireFormat$FieldType0;
            this.defaultKey = object0;
            this.valueType = wireFormat$FieldType1;
            this.defaultValue = object1;
        }
    }

    private static final int KEY_FIELD_NUMBER = 1;
    private static final int VALUE_FIELD_NUMBER = 2;
    private final Object key;
    private final Metadata metadata;
    private final Object value;

    private MapEntryLite(Metadata mapEntryLite$Metadata0, Object object0, Object object1) {
        this.metadata = mapEntryLite$Metadata0;
        this.key = object0;
        this.value = object1;
    }

    private MapEntryLite(FieldType wireFormat$FieldType0, Object object0, FieldType wireFormat$FieldType1, Object object1) {
        this.metadata = new Metadata(wireFormat$FieldType0, object0, wireFormat$FieldType1, object1);
        this.key = object0;
        this.value = object1;
    }

    public int computeMessageSize(int v, Object object0, Object object1) {
        return CodedOutputStream.computeTagSize(v) + CodedOutputStream.computeLengthDelimitedFieldSize(MapEntryLite.computeSerializedSize(this.metadata, object0, object1));
    }

    static int computeSerializedSize(Metadata mapEntryLite$Metadata0, Object object0, Object object1) {
        return FieldSet.computeElementSize(mapEntryLite$Metadata0.keyType, 1, object0) + FieldSet.computeElementSize(mapEntryLite$Metadata0.valueType, 2, object1);
    }

    public Object getKey() {
        return this.key;
    }

    Metadata getMetadata() {
        return this.metadata;
    }

    public Object getValue() {
        return this.value;
    }

    public static MapEntryLite newDefaultInstance(FieldType wireFormat$FieldType0, Object object0, FieldType wireFormat$FieldType1, Object object1) {
        return new MapEntryLite(wireFormat$FieldType0, object0, wireFormat$FieldType1, object1);
    }

    static Map.Entry parseEntry(CodedInputStream codedInputStream0, Metadata mapEntryLite$Metadata0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        Object object0 = mapEntryLite$Metadata0.defaultKey;
        Object object1 = mapEntryLite$Metadata0.defaultValue;
        int v;
        while((v = codedInputStream0.readTag()) != 0) {
            if(v == WireFormat.makeTag(1, mapEntryLite$Metadata0.keyType.getWireType())) {
                object0 = MapEntryLite.parseField(codedInputStream0, extensionRegistryLite0, mapEntryLite$Metadata0.keyType, object0);
            }
            else if(v == WireFormat.makeTag(2, mapEntryLite$Metadata0.valueType.getWireType())) {
                object1 = MapEntryLite.parseField(codedInputStream0, extensionRegistryLite0, mapEntryLite$Metadata0.valueType, object1);
            }
            else if(!codedInputStream0.skipField(v)) {
                break;
            }
        }
        return new AbstractMap.SimpleImmutableEntry(object0, object1);
    }

    public Map.Entry parseEntry(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        return MapEntryLite.parseEntry(byteString0.newCodedInput(), this.metadata, extensionRegistryLite0);
    }

    static Object parseField(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0, FieldType wireFormat$FieldType0, Object object0) throws IOException {
        switch(com.google.crypto.tink.shaded.protobuf.MapEntryLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
            case 1: {
                Builder messageLite$Builder0 = ((MessageLite)object0).toBuilder();
                codedInputStream0.readMessage(messageLite$Builder0, extensionRegistryLite0);
                return messageLite$Builder0.buildPartial();
            }
            case 2: {
                return codedInputStream0.readEnum();
            }
            case 3: {
                throw new RuntimeException("Groups are not allowed in maps.");
            }
            default: {
                return FieldSet.readPrimitiveField(codedInputStream0, wireFormat$FieldType0, true);
            }
        }
    }

    public void parseInto(MapFieldLite mapFieldLite0, CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        int v = codedInputStream0.pushLimit(codedInputStream0.readRawVarint32());
        Object object0 = this.metadata.defaultKey;
        Object object1 = this.metadata.defaultValue;
        int v1;
        while((v1 = codedInputStream0.readTag()) != 0) {
            if(v1 == WireFormat.makeTag(1, this.metadata.keyType.getWireType())) {
                object0 = MapEntryLite.parseField(codedInputStream0, extensionRegistryLite0, this.metadata.keyType, object0);
            }
            else if(v1 == WireFormat.makeTag(2, this.metadata.valueType.getWireType())) {
                object1 = MapEntryLite.parseField(codedInputStream0, extensionRegistryLite0, this.metadata.valueType, object1);
            }
            else if(!codedInputStream0.skipField(v1)) {
                break;
            }
        }
        codedInputStream0.checkLastTagWas(0);
        codedInputStream0.popLimit(v);
        mapFieldLite0.put(object0, object1);
    }

    public void serializeTo(CodedOutputStream codedOutputStream0, int v, Object object0, Object object1) throws IOException {
        codedOutputStream0.writeTag(v, 2);
        codedOutputStream0.writeUInt32NoTag(MapEntryLite.computeSerializedSize(this.metadata, object0, object1));
        MapEntryLite.writeTo(codedOutputStream0, this.metadata, object0, object1);
    }

    static void writeTo(CodedOutputStream codedOutputStream0, Metadata mapEntryLite$Metadata0, Object object0, Object object1) throws IOException {
        FieldSet.writeElement(codedOutputStream0, mapEntryLite$Metadata0.keyType, 1, object0);
        FieldSet.writeElement(codedOutputStream0, mapEntryLite$Metadata0.valueType, 2, object1);
    }

    class com.google.crypto.tink.shaded.protobuf.MapEntryLite.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.MapEntryLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.MESSAGE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MapEntryLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.MapEntryLite.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.GROUP.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

