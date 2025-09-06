package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

@CheckReturnValue
abstract class BinaryReader implements Reader {
    static final class SafeHeapReader extends BinaryReader {
        private final byte[] buffer;
        private final boolean bufferIsImmutable;
        private int endGroupTag;
        private final int initialPos;
        private int limit;
        private int pos;
        private int tag;

        public SafeHeapReader(ByteBuffer byteBuffer0, boolean z) {
            super(null);
            this.bufferIsImmutable = z;
            this.buffer = byteBuffer0.array();
            int v = byteBuffer0.arrayOffset() + byteBuffer0.position();
            this.pos = v;
            this.initialPos = v;
            this.limit = byteBuffer0.arrayOffset() + byteBuffer0.limit();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int getFieldNumber() throws IOException {
            if(this.isAtEnd()) {
                return 0x7FFFFFFF;
            }
            int v = this.readVarint32();
            this.tag = v;
            return v == this.endGroupTag ? 0x7FFFFFFF : v >>> 3;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int getTag() {
            return this.tag;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.BinaryReader
        public int getTotalBytesRead() {
            return this.pos - this.initialPos;
        }

        private boolean isAtEnd() {
            return this.pos == this.limit;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void mergeGroupField(Object object0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v1;
            try {
                v1 = this.endGroupTag;
                this.endGroupTag = this.tag >>> 3 << 3 | 4;
                schema0.mergeFrom(object0, this, extensionRegistryLite0);
                if(this.tag == this.endGroupTag) {
                    return;
                }
            }
            finally {
                this.endGroupTag = v1;
            }
            throw InvalidProtocolBufferException.parseFailure();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void mergeMessageField(Object object0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = this.readVarint32();
            this.requireBytes(v);
            int v1 = this.limit;
            int v2 = this.pos + v;
            this.limit = v2;
            try {
                schema0.mergeFrom(object0, this, extensionRegistryLite0);
                if(this.pos == v2) {
                    return;
                }
            }
            finally {
                this.limit = v1;
            }
            throw InvalidProtocolBufferException.parseFailure();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public boolean readBool() throws IOException {
            this.requireWireType(0);
            return this.readVarint32() != 0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readBoolList(List list0) throws IOException {
            int v5;
            int v2;
            if(list0 instanceof BooleanArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((BooleanArrayList)list0).addBoolean(this.readVarint32() != 0);
                        }
                        this.requirePosition(v1);
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((BooleanArrayList)list0).addBoolean(this.readBool());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
                return;
            }
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v3 = this.readVarint32();
                    int v4 = this.pos + v3;
                    while(this.pos < v4) {
                        list0.add(Boolean.valueOf(this.readVarint32() != 0));
                    }
                    this.requirePosition(v4);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                list0.add(Boolean.valueOf(this.readBool()));
                if(this.isAtEnd()) {
                    return;
                }
                v5 = this.pos;
            }
            while(this.readVarint32() == this.tag);
            this.pos = v5;
        }

        private byte readByte() throws IOException {
            int v = this.pos;
            if(v == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = v + 1;
            return this.buffer[v];
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public ByteString readBytes() throws IOException {
            this.requireWireType(2);
            int v = this.readVarint32();
            if(v == 0) {
                return ByteString.EMPTY;
            }
            this.requireBytes(v);
            ByteString byteString0 = this.bufferIsImmutable ? ByteString.wrap(this.buffer, this.pos, v) : ByteString.copyFrom(this.buffer, this.pos, v);
            this.pos += v;
            return byteString0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readBytesList(List list0) throws IOException {
            int v;
            if((this.tag & 7) != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            do {
                list0.add(this.readBytes());
                if(this.isAtEnd()) {
                    return;
                }
                v = this.pos;
            }
            while(this.readVarint32() == this.tag);
            this.pos = v;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public double readDouble() throws IOException {
            this.requireWireType(1);
            return Double.longBitsToDouble(this.readLittleEndian64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readDoubleList(List list0) throws IOException {
            int v3;
            int v2;
            if(list0 instanceof DoubleArrayList) {
                switch(this.tag & 7) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        this.verifyPackedFixed64Length(v);
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((DoubleArrayList)list0).addDouble(Double.longBitsToDouble(this.readLittleEndian64_NoCheck()));
                        }
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((DoubleArrayList)list0).addDouble(this.readDouble());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
            alab1:
                switch(this.tag & 7) {
                    case 1: {
                        do {
                            list0.add(this.readDouble());
                            if(this.isAtEnd()) {
                                break alab1;
                            }
                            v3 = this.pos;
                        }
                        while(this.readVarint32() == this.tag);
                        this.pos = v3;
                        return;
                    }
                    case 2: {
                        int v4 = this.readVarint32();
                        this.verifyPackedFixed64Length(v4);
                        int v5 = this.pos + v4;
                        while(true) {
                            if(this.pos >= v5) {
                                break alab1;
                            }
                            list0.add(Double.longBitsToDouble(this.readLittleEndian64_NoCheck()));
                        }
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int readEnum() throws IOException {
            this.requireWireType(0);
            return this.readVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readEnumList(List list0) throws IOException {
            int v3;
            int v2;
            if(list0 instanceof IntArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((IntArrayList)list0).addInt(this.readVarint32());
                        }
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((IntArrayList)list0).addInt(this.readEnum());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
            alab1:
                switch(this.tag & 7) {
                    case 0: {
                        do {
                            list0.add(this.readEnum());
                            if(this.isAtEnd()) {
                                break alab1;
                            }
                            v3 = this.pos;
                        }
                        while(this.readVarint32() == this.tag);
                        this.pos = v3;
                        return;
                    }
                    case 2: {
                        int v4 = this.readVarint32();
                        int v5 = this.pos + v4;
                        while(true) {
                            if(this.pos >= v5) {
                                break alab1;
                            }
                            list0.add(this.readVarint32());
                        }
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
            }
        }

        private Object readField(FieldType wireFormat$FieldType0, Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            switch(com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
                case 1: {
                    return Boolean.valueOf(this.readBool());
                }
                case 2: {
                    return this.readBytes();
                }
                case 3: {
                    return this.readDouble();
                }
                case 4: {
                    return this.readEnum();
                }
                case 5: {
                    return this.readFixed32();
                }
                case 6: {
                    return this.readFixed64();
                }
                case 7: {
                    return this.readFloat();
                }
                case 8: {
                    return this.readInt32();
                }
                case 9: {
                    return this.readInt64();
                }
                case 10: {
                    return this.readMessage(class0, extensionRegistryLite0);
                }
                case 11: {
                    return this.readSFixed32();
                }
                case 12: {
                    return this.readSFixed64();
                }
                case 13: {
                    return this.readSInt32();
                }
                case 14: {
                    return this.readSInt64();
                }
                case 15: {
                    return this.readStringRequireUtf8();
                }
                case 16: {
                    return this.readUInt32();
                }
                case 17: {
                    return this.readUInt64();
                }
                default: {
                    throw new RuntimeException("unsupported field type.");
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int readFixed32() throws IOException {
            this.requireWireType(5);
            return this.readLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readFixed32List(List list0) throws IOException {
            int v2;
            if(list0 instanceof IntArrayList) {
                switch(this.tag & 7) {
                    case 2: {
                        int v = this.readVarint32();
                        this.verifyPackedFixed32Length(v);
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((IntArrayList)list0).addInt(this.readLittleEndian32_NoCheck());
                        }
                        return;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((IntArrayList)list0).addInt(this.readFixed32());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
                switch(this.tag & 7) {
                    case 2: {
                        int v3 = this.readVarint32();
                        this.verifyPackedFixed32Length(v3);
                        int v4 = this.pos + v3;
                        while(this.pos < v4) {
                            list0.add(this.readLittleEndian32_NoCheck());
                        }
                        return;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                while(true) {
                    list0.add(this.readFixed32());
                    if(this.isAtEnd()) {
                        break;
                    }
                    int v5 = this.pos;
                    if(this.readVarint32() != this.tag) {
                        this.pos = v5;
                        return;
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public long readFixed64() throws IOException {
            this.requireWireType(1);
            return this.readLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readFixed64List(List list0) throws IOException {
            int v3;
            int v2;
            if(list0 instanceof LongArrayList) {
                switch(this.tag & 7) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        this.verifyPackedFixed64Length(v);
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((LongArrayList)list0).addLong(this.readLittleEndian64_NoCheck());
                        }
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((LongArrayList)list0).addLong(this.readFixed64());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
            alab1:
                switch(this.tag & 7) {
                    case 1: {
                        do {
                            list0.add(this.readFixed64());
                            if(this.isAtEnd()) {
                                break alab1;
                            }
                            v3 = this.pos;
                        }
                        while(this.readVarint32() == this.tag);
                        this.pos = v3;
                        return;
                    }
                    case 2: {
                        int v4 = this.readVarint32();
                        this.verifyPackedFixed64Length(v4);
                        int v5 = this.pos + v4;
                        while(true) {
                            if(this.pos >= v5) {
                                break alab1;
                            }
                            list0.add(this.readLittleEndian64_NoCheck());
                        }
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public float readFloat() throws IOException {
            this.requireWireType(5);
            return Float.intBitsToFloat(this.readLittleEndian32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readFloatList(List list0) throws IOException {
            int v2;
            if(list0 instanceof FloatArrayList) {
                switch(this.tag & 7) {
                    case 2: {
                        int v = this.readVarint32();
                        this.verifyPackedFixed32Length(v);
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((FloatArrayList)list0).addFloat(Float.intBitsToFloat(this.readLittleEndian32_NoCheck()));
                        }
                        return;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((FloatArrayList)list0).addFloat(this.readFloat());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
                switch(this.tag & 7) {
                    case 2: {
                        int v3 = this.readVarint32();
                        this.verifyPackedFixed32Length(v3);
                        int v4 = this.pos + v3;
                        while(this.pos < v4) {
                            list0.add(Float.intBitsToFloat(this.readLittleEndian32_NoCheck()));
                        }
                        return;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                while(true) {
                    list0.add(this.readFloat());
                    if(this.isAtEnd()) {
                        break;
                    }
                    int v5 = this.pos;
                    if(this.readVarint32() != this.tag) {
                        this.pos = v5;
                        return;
                    }
                }
            }
        }

        private Object readGroup(Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            Object object0 = schema0.newInstance();
            this.mergeGroupField(object0, schema0, extensionRegistryLite0);
            schema0.makeImmutable(object0);
            return object0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        @Deprecated
        public Object readGroup(Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.requireWireType(3);
            return this.readGroup(Protobuf.getInstance().schemaFor(class0), extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        @Deprecated
        public Object readGroupBySchemaWithCheck(Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.requireWireType(3);
            return this.readGroup(schema0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        @Deprecated
        public void readGroupList(List list0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v1;
            if((this.tag & 7) != 3) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int v = this.tag;
            do {
                list0.add(this.readGroup(schema0, extensionRegistryLite0));
                if(this.isAtEnd()) {
                    return;
                }
                v1 = this.pos;
            }
            while(this.readVarint32() == v);
            this.pos = v1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        @Deprecated
        public void readGroupList(List list0, Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.readGroupList(list0, Protobuf.getInstance().schemaFor(class0), extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int readInt32() throws IOException {
            this.requireWireType(0);
            return this.readVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readInt32List(List list0) throws IOException {
            int v5;
            int v2;
            if(list0 instanceof IntArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((IntArrayList)list0).addInt(this.readVarint32());
                        }
                        this.requirePosition(v1);
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((IntArrayList)list0).addInt(this.readInt32());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
                return;
            }
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v3 = this.readVarint32();
                    int v4 = this.pos + v3;
                    while(this.pos < v4) {
                        list0.add(this.readVarint32());
                    }
                    this.requirePosition(v4);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                list0.add(this.readInt32());
                if(this.isAtEnd()) {
                    return;
                }
                v5 = this.pos;
            }
            while(this.readVarint32() == this.tag);
            this.pos = v5;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public long readInt64() throws IOException {
            this.requireWireType(0);
            return this.readVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readInt64List(List list0) throws IOException {
            int v5;
            int v2;
            if(list0 instanceof LongArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((LongArrayList)list0).addLong(this.readVarint64());
                        }
                        this.requirePosition(v1);
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((LongArrayList)list0).addLong(this.readInt64());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
                return;
            }
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v3 = this.readVarint32();
                    int v4 = this.pos + v3;
                    while(this.pos < v4) {
                        list0.add(this.readVarint64());
                    }
                    this.requirePosition(v4);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                list0.add(this.readInt64());
                if(this.isAtEnd()) {
                    return;
                }
                v5 = this.pos;
            }
            while(this.readVarint32() == this.tag);
            this.pos = v5;
        }

        private int readLittleEndian32() throws IOException {
            this.requireBytes(4);
            return this.readLittleEndian32_NoCheck();
        }

        private int readLittleEndian32_NoCheck() {
            int v = this.pos;
            this.pos = v + 4;
            return (this.buffer[v + 3] & 0xFF) << 24 | (this.buffer[v] & 0xFF | (this.buffer[v + 1] & 0xFF) << 8 | (this.buffer[v + 2] & 0xFF) << 16);
        }

        private long readLittleEndian64() throws IOException {
            this.requireBytes(8);
            return this.readLittleEndian64_NoCheck();
        }

        private long readLittleEndian64_NoCheck() {
            int v = this.pos;
            this.pos = v + 8;
            return (((long)this.buffer[v + 7]) & 0xFFL) << 56 | (((long)this.buffer[v]) & 0xFFL | (((long)this.buffer[v + 1]) & 0xFFL) << 8 | (((long)this.buffer[v + 2]) & 0xFFL) << 16 | (((long)this.buffer[v + 3]) & 0xFFL) << 24 | (((long)this.buffer[v + 4]) & 0xFFL) << 0x20 | (((long)this.buffer[v + 5]) & 0xFFL) << 40 | (((long)this.buffer[v + 6]) & 0xFFL) << 0x30);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readMap(Map map0, Metadata mapEntryLite$Metadata0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.requireWireType(2);
            int v = this.readVarint32();
            this.requireBytes(v);
            int v1 = this.limit;
            this.limit = this.pos + v;
            try {
                Object object0 = mapEntryLite$Metadata0.defaultKey;
                Object object1 = mapEntryLite$Metadata0.defaultValue;
                while(true) {
                    while(true) {
                    label_7:
                        int v2 = this.getFieldNumber();
                        if(v2 == 0x7FFFFFFF) {
                            map0.put(object0, object1);
                            this.limit = v1;
                            return;
                        }
                        try {
                            switch(v2) {
                                case 1: {
                                    object0 = this.readField(mapEntryLite$Metadata0.keyType, null, null);
                                    continue;
                                }
                                case 2: {
                                    goto label_17;
                                }
                                default: {
                                    if(this.skipField()) {
                                        continue;
                                    }
                                }
                            }
                            throw new InvalidProtocolBufferException("Unable to parse map entry.");
                        label_17:
                            Class class0 = mapEntryLite$Metadata0.defaultValue.getClass();
                            object1 = this.readField(mapEntryLite$Metadata0.valueType, class0, extensionRegistryLite0);
                            continue;
                        }
                        catch(InvalidWireTypeException unused_ex) {
                        }
                        break;
                    }
                    if(this.skipField()) {
                        goto label_7;
                    }
                    break;
                }
                throw new InvalidProtocolBufferException("Unable to parse map entry.");
            }
            catch(Throwable throwable0) {
                this.limit = v1;
                throw throwable0;
            }
        }

        private Object readMessage(Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            Object object0 = schema0.newInstance();
            this.mergeMessageField(object0, schema0, extensionRegistryLite0);
            schema0.makeImmutable(object0);
            return object0;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public Object readMessage(Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.requireWireType(2);
            return this.readMessage(Protobuf.getInstance().schemaFor(class0), extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public Object readMessageBySchemaWithCheck(Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.requireWireType(2);
            return this.readMessage(schema0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readMessageList(List list0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v1;
            if((this.tag & 7) != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            int v = this.tag;
            do {
                list0.add(this.readMessage(schema0, extensionRegistryLite0));
                if(this.isAtEnd()) {
                    return;
                }
                v1 = this.pos;
            }
            while(this.readVarint32() == v);
            this.pos = v1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readMessageList(List list0, Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            this.readMessageList(list0, Protobuf.getInstance().schemaFor(class0), extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int readSFixed32() throws IOException {
            this.requireWireType(5);
            return this.readLittleEndian32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSFixed32List(List list0) throws IOException {
            int v2;
            if(list0 instanceof IntArrayList) {
                switch(this.tag & 7) {
                    case 2: {
                        int v = this.readVarint32();
                        this.verifyPackedFixed32Length(v);
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((IntArrayList)list0).addInt(this.readLittleEndian32_NoCheck());
                        }
                        return;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((IntArrayList)list0).addInt(this.readSFixed32());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
                switch(this.tag & 7) {
                    case 2: {
                        int v3 = this.readVarint32();
                        this.verifyPackedFixed32Length(v3);
                        int v4 = this.pos + v3;
                        while(this.pos < v4) {
                            list0.add(this.readLittleEndian32_NoCheck());
                        }
                        return;
                    }
                    case 5: {
                        break;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                while(true) {
                    list0.add(this.readSFixed32());
                    if(this.isAtEnd()) {
                        break;
                    }
                    int v5 = this.pos;
                    if(this.readVarint32() != this.tag) {
                        this.pos = v5;
                        return;
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public long readSFixed64() throws IOException {
            this.requireWireType(1);
            return this.readLittleEndian64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSFixed64List(List list0) throws IOException {
            int v3;
            int v2;
            if(list0 instanceof LongArrayList) {
                switch(this.tag & 7) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        this.verifyPackedFixed64Length(v);
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((LongArrayList)list0).addLong(this.readLittleEndian64_NoCheck());
                        }
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((LongArrayList)list0).addLong(this.readSFixed64());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
            alab1:
                switch(this.tag & 7) {
                    case 1: {
                        do {
                            list0.add(this.readSFixed64());
                            if(this.isAtEnd()) {
                                break alab1;
                            }
                            v3 = this.pos;
                        }
                        while(this.readVarint32() == this.tag);
                        this.pos = v3;
                        return;
                    }
                    case 2: {
                        int v4 = this.readVarint32();
                        this.verifyPackedFixed64Length(v4);
                        int v5 = this.pos + v4;
                        while(true) {
                            if(this.pos >= v5) {
                                break alab1;
                            }
                            list0.add(this.readLittleEndian64_NoCheck());
                        }
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int readSInt32() throws IOException {
            this.requireWireType(0);
            return CodedInputStream.decodeZigZag32(this.readVarint32());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSInt32List(List list0) throws IOException {
            int v3;
            int v2;
            if(list0 instanceof IntArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((IntArrayList)list0).addInt(CodedInputStream.decodeZigZag32(this.readVarint32()));
                        }
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((IntArrayList)list0).addInt(this.readSInt32());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
            alab1:
                switch(this.tag & 7) {
                    case 0: {
                        do {
                            list0.add(this.readSInt32());
                            if(this.isAtEnd()) {
                                break alab1;
                            }
                            v3 = this.pos;
                        }
                        while(this.readVarint32() == this.tag);
                        this.pos = v3;
                        return;
                    }
                    case 2: {
                        int v4 = this.readVarint32();
                        int v5 = this.pos + v4;
                        while(true) {
                            if(this.pos >= v5) {
                                break alab1;
                            }
                            list0.add(CodedInputStream.decodeZigZag32(this.readVarint32()));
                        }
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public long readSInt64() throws IOException {
            this.requireWireType(0);
            return CodedInputStream.decodeZigZag64(this.readVarint64());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readSInt64List(List list0) throws IOException {
            int v3;
            int v2;
            if(list0 instanceof LongArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((LongArrayList)list0).addLong(CodedInputStream.decodeZigZag64(this.readVarint64()));
                        }
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((LongArrayList)list0).addLong(this.readSInt64());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
            alab1:
                switch(this.tag & 7) {
                    case 0: {
                        do {
                            list0.add(this.readSInt64());
                            if(this.isAtEnd()) {
                                break alab1;
                            }
                            v3 = this.pos;
                        }
                        while(this.readVarint32() == this.tag);
                        this.pos = v3;
                        return;
                    }
                    case 2: {
                        int v4 = this.readVarint32();
                        int v5 = this.pos + v4;
                        while(true) {
                            if(this.pos >= v5) {
                                break alab1;
                            }
                            list0.add(CodedInputStream.decodeZigZag64(this.readVarint64()));
                        }
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public String readString() throws IOException {
            return this.readStringInternal(false);
        }

        public String readStringInternal(boolean z) throws IOException {
            this.requireWireType(2);
            int v = this.readVarint32();
            if(v == 0) {
                return "";
            }
            this.requireBytes(v);
            if(z && !Utf8.isValidUtf8(this.buffer, this.pos, this.pos + v)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            String s = new String(this.buffer, this.pos, v, Internal.UTF_8);
            this.pos += v;
            return s;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readStringList(List list0) throws IOException {
            this.readStringListInternal(list0, false);
        }

        public void readStringListInternal(List list0, boolean z) throws IOException {
            int v1;
            int v;
            if((this.tag & 7) != 2) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
            if(list0 instanceof LazyStringList && !z) {
                do {
                    ((LazyStringList)list0).add(this.readBytes());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v;
                return;
            }
            do {
                list0.add(this.readStringInternal(z));
                if(this.isAtEnd()) {
                    return;
                }
                v1 = this.pos;
            }
            while(this.readVarint32() == this.tag);
            this.pos = v1;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readStringListRequireUtf8(List list0) throws IOException {
            this.readStringListInternal(list0, true);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public String readStringRequireUtf8() throws IOException {
            return this.readStringInternal(true);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public int readUInt32() throws IOException {
            this.requireWireType(0);
            return this.readVarint32();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readUInt32List(List list0) throws IOException {
            int v3;
            int v2;
            if(list0 instanceof IntArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((IntArrayList)list0).addInt(this.readVarint32());
                        }
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((IntArrayList)list0).addInt(this.readUInt32());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
            }
            else {
            alab1:
                switch(this.tag & 7) {
                    case 0: {
                        do {
                            list0.add(this.readUInt32());
                            if(this.isAtEnd()) {
                                break alab1;
                            }
                            v3 = this.pos;
                        }
                        while(this.readVarint32() == this.tag);
                        this.pos = v3;
                        return;
                    }
                    case 2: {
                        int v4 = this.readVarint32();
                        int v5 = this.pos + v4;
                        while(true) {
                            if(this.pos >= v5) {
                                break alab1;
                            }
                            list0.add(this.readVarint32());
                        }
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
            }
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public long readUInt64() throws IOException {
            this.requireWireType(0);
            return this.readVarint64();
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public void readUInt64List(List list0) throws IOException {
            int v5;
            int v2;
            if(list0 instanceof LongArrayList) {
                switch(this.tag & 7) {
                    case 0: {
                        break;
                    }
                    case 2: {
                        int v = this.readVarint32();
                        int v1 = this.pos + v;
                        while(this.pos < v1) {
                            ((LongArrayList)list0).addLong(this.readVarint64());
                        }
                        this.requirePosition(v1);
                        return;
                    }
                    default: {
                        throw InvalidProtocolBufferException.invalidWireType();
                    }
                }
                do {
                    ((LongArrayList)list0).addLong(this.readUInt64());
                    if(this.isAtEnd()) {
                        return;
                    }
                    v2 = this.pos;
                }
                while(this.readVarint32() == this.tag);
                this.pos = v2;
                return;
            }
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v3 = this.readVarint32();
                    int v4 = this.pos + v3;
                    while(this.pos < v4) {
                        list0.add(this.readVarint64());
                    }
                    this.requirePosition(v4);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                list0.add(this.readUInt64());
                if(this.isAtEnd()) {
                    return;
                }
                v5 = this.pos;
            }
            while(this.readVarint32() == this.tag);
            this.pos = v5;
        }

        private int readVarint32() throws IOException {
            int v5;
            int v = this.pos;
            int v1 = this.limit;
            if(v1 == v) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] arr_b = this.buffer;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.pos = v + 1;
                return v2;
            }
            if(v1 - (v + 1) < 9) {
                return (int)this.readVarint64SlowPath();
            }
            int v3 = v + 2;
            int v4 = arr_b[v + 1] << 7 ^ v2;
            if(v4 < 0) {
                v5 = v4 ^ 0xFFFFFF80;
            }
            else {
                int v6 = arr_b[v3] << 14 ^ v4;
                if(v6 >= 0) {
                    v5 = v6 ^ 0x3F80;
                    v3 = v + 3;
                }
                else {
                    int v7 = v6 ^ arr_b[v + 3] << 21;
                    if(v7 < 0) {
                        v5 = 0xFFE03F80 ^ v7;
                        v3 = v + 4;
                    }
                    else {
                        int v8 = arr_b[v + 4];
                        int v9 = v7 ^ v8 << 28 ^ 0xFE03F80;
                        if(v8 >= 0) {
                            v5 = v9;
                            v3 = v + 5;
                        }
                        else if(arr_b[v + 5] >= 0) {
                            v5 = v9;
                            v3 = v + 6;
                        }
                        else if(arr_b[v + 6] >= 0) {
                            v5 = v9;
                            v3 = v + 7;
                        }
                        else if(arr_b[v + 7] >= 0) {
                            v5 = v9;
                            v3 = v + 8;
                        }
                        else if(arr_b[v + 8] < 0) {
                            if(arr_b[v + 9] < 0) {
                                throw InvalidProtocolBufferException.malformedVarint();
                            }
                            v3 = v + 10;
                            v5 = v9;
                        }
                        else {
                            v5 = v9;
                            v3 = v + 9;
                        }
                    }
                }
            }
            this.pos = v3;
            return v5;
        }

        public long readVarint64() throws IOException {
            long v5;
            int v = this.pos;
            int v1 = this.limit;
            if(v1 == v) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] arr_b = this.buffer;
            int v2 = arr_b[v];
            if(v2 >= 0) {
                this.pos = v + 1;
                return (long)v2;
            }
            if(v1 - (v + 1) < 9) {
                return this.readVarint64SlowPath();
            }
            int v3 = v + 2;
            int v4 = arr_b[v + 1] << 7 ^ v2;
            if(v4 < 0) {
                v5 = (long)(v4 ^ 0xFFFFFF80);
            }
            else {
                int v6 = arr_b[v3] << 14 ^ v4;
                if(v6 >= 0) {
                    v5 = (long)(v6 ^ 0x3F80);
                    v3 = v + 3;
                }
                else {
                    int v7 = v6 ^ arr_b[v + 3] << 21;
                    if(v7 < 0) {
                        v3 = v + 4;
                        v5 = (long)(0xFFE03F80 ^ v7);
                    }
                    else {
                        v3 = v + 5;
                        long v8 = ((long)v7) ^ ((long)arr_b[v + 4]) << 28;
                        if(v8 >= 0L) {
                            v5 = 0xFE03F80L ^ v8;
                        }
                        else {
                            long v9 = v8 ^ ((long)arr_b[v3]) << 35;
                            if(v9 < 0L) {
                                v3 = v + 6;
                                v5 = 0xFFFFFFF80FE03F80L ^ v9;
                            }
                            else {
                                v3 = v + 7;
                                v8 = v9 ^ ((long)arr_b[v + 6]) << 42;
                                if(v8 >= 0L) {
                                    v5 = 0x3F80FE03F80L ^ v8;
                                }
                                else {
                                    v9 = v8 ^ ((long)arr_b[v3]) << 49;
                                    if(v9 < 0L) {
                                        v3 = v + 8;
                                        v5 = 0xFFFE03F80FE03F80L ^ v9;
                                    }
                                    else {
                                        v3 = v + 9;
                                        long v10 = v9 ^ ((long)arr_b[v + 8]) << 56 ^ 0xFE03F80FE03F80L;
                                        if(v10 < 0L) {
                                            if(((long)arr_b[v3]) < 0L) {
                                                throw InvalidProtocolBufferException.malformedVarint();
                                            }
                                            v3 = v + 10;
                                        }
                                        v5 = v10;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.pos = v3;
            return v5;
        }

        private long readVarint64SlowPath() throws IOException {
            long v = 0L;
            for(int v1 = 0; v1 < 0x40; v1 += 7) {
                int v2 = this.readByte();
                v |= ((long)(v2 & 0x7F)) << v1;
                if((v2 & 0x80) == 0) {
                    return v;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void requireBytes(int v) throws IOException {
            if(v < 0 || v > this.limit - this.pos) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void requirePosition(int v) throws IOException {
            if(this.pos != v) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void requireWireType(int v) throws IOException {
            if((this.tag & 7) != v) {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        private void skipBytes(int v) throws IOException {
            this.requireBytes(v);
            this.pos += v;
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.Reader
        public boolean skipField() throws IOException {
            if(!this.isAtEnd()) {
                int v = this.tag;
                if(v != this.endGroupTag) {
                    switch(v & 7) {
                        case 0: {
                            this.skipVarint();
                            return true;
                        }
                        case 1: {
                            this.skipBytes(8);
                            return true;
                        }
                        case 2: {
                            this.skipBytes(this.readVarint32());
                            return true;
                        }
                        case 3: {
                            this.skipGroup();
                            return true;
                        }
                        case 5: {
                            this.skipBytes(4);
                            return true;
                        }
                        default: {
                            throw InvalidProtocolBufferException.invalidWireType();
                        }
                    }
                }
            }
            return false;
        }

        private void skipGroup() throws IOException {
            int v = this.endGroupTag;
            this.endGroupTag = this.tag >>> 3 << 3 | 4;
            while(this.getFieldNumber() != 0x7FFFFFFF && this.skipField()) {
            }
            if(this.tag != this.endGroupTag) {
                throw InvalidProtocolBufferException.parseFailure();
            }
            this.endGroupTag = v;
        }

        private void skipVarint() throws IOException {
            int v = this.pos;
            if(this.limit - v >= 10) {
                byte[] arr_b = this.buffer;
                int v1 = 0;
                while(v1 < 10) {
                    if(arr_b[v] >= 0) {
                        this.pos = v + 1;
                        return;
                    }
                    ++v1;
                    ++v;
                }
            }
            this.skipVarintSlowPath();
        }

        private void skipVarintSlowPath() throws IOException {
            for(int v = 0; v < 10; ++v) {
                if(this.readByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void verifyPackedFixed32Length(int v) throws IOException {
            this.requireBytes(v);
            if((v & 3) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }

        private void verifyPackedFixed64Length(int v) throws IOException {
            this.requireBytes(v);
            if((v & 7) != 0) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        }
    }

    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;

    private BinaryReader() {
    }

    BinaryReader(com.google.crypto.tink.shaded.protobuf.BinaryReader.1 binaryReader$10) {
    }

    public abstract int getTotalBytesRead();

    public static BinaryReader newInstance(ByteBuffer byteBuffer0, boolean z) {
        if(!byteBuffer0.hasArray()) {
            throw new IllegalArgumentException("Direct buffers not yet supported");
        }
        return new SafeHeapReader(byteBuffer0, z);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean shouldDiscardUnknownFields() {
        return false;
    }

    class com.google.crypto.tink.shaded.protobuf.BinaryReader.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.BOOL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.DOUBLE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.BinaryReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

