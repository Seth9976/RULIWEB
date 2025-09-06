package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CheckReturnValue
final class CodedInputStreamReader implements Reader {
    private static final int FIXED32_MULTIPLE_MASK = 3;
    private static final int FIXED64_MULTIPLE_MASK = 7;
    private static final int NEXT_TAG_UNSET;
    private int endGroupTag;
    private final CodedInputStream input;
    private int nextTag;
    private int tag;

    private CodedInputStreamReader(CodedInputStream codedInputStream0) {
        this.nextTag = 0;
        CodedInputStream codedInputStream1 = (CodedInputStream)Internal.checkNotNull(codedInputStream0, "input");
        this.input = codedInputStream1;
        codedInputStream1.wrapper = this;
    }

    public static CodedInputStreamReader forCodedInput(CodedInputStream codedInputStream0) {
        return codedInputStream0.wrapper == null ? new CodedInputStreamReader(codedInputStream0) : codedInputStream0.wrapper;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int getFieldNumber() throws IOException {
        int v = this.nextTag;
        if(v != 0) {
            this.tag = v;
            this.nextTag = 0;
            return this.tag == 0 || this.tag == this.endGroupTag ? 0x7FFFFFFF : this.tag >>> 3;
        }
        this.tag = this.input.readTag();
        return this.tag == 0 || this.tag == this.endGroupTag ? 0x7FFFFFFF : this.tag >>> 3;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int getTag() {
        return this.tag;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void mergeGroupField(Object object0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        this.requireWireType(3);
        this.mergeGroupFieldInternal(object0, schema0, extensionRegistryLite0);
    }

    private void mergeGroupFieldInternal(Object object0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
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
        this.requireWireType(2);
        this.mergeMessageFieldInternal(object0, schema0, extensionRegistryLite0);
    }

    private void mergeMessageFieldInternal(Object object0, Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        int v = this.input.readUInt32();
        if(this.input.recursionDepth >= this.input.recursionLimit) {
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }
        int v1 = this.input.pushLimit(v);
        ++this.input.recursionDepth;
        schema0.mergeFrom(object0, this, extensionRegistryLite0);
        this.input.checkLastTagWas(0);
        --this.input.recursionDepth;
        this.input.popLimit(v1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean readBool() throws IOException {
        this.requireWireType(0);
        return this.input.readBool();
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
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((BooleanArrayList)list0).addBoolean(this.input.readBool());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((BooleanArrayList)list0).addBoolean(this.input.readBool());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(Boolean.valueOf(this.input.readBool()));
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(Boolean.valueOf(this.input.readBool()));
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public ByteString readBytes() throws IOException {
        this.requireWireType(2);
        return this.input.readBytes();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readBytesList(List list0) throws IOException {
        int v;
        if((this.tag & 7) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        do {
            list0.add(this.readBytes());
            if(this.input.isAtEnd()) {
                return;
            }
            v = this.input.readTag();
        }
        while(v == this.tag);
        this.nextTag = v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public double readDouble() throws IOException {
        this.requireWireType(1);
        return this.input.readDouble();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readDoubleList(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof DoubleArrayList) {
            switch(this.tag & 7) {
                case 1: {
                    break;
                }
                case 2: {
                    int v = this.input.readUInt32();
                    this.verifyPackedFixed64Length(v);
                    int v1 = this.input.getTotalBytesRead();
                    do {
                        ((DoubleArrayList)list0).addDouble(this.input.readDouble());
                    }
                    while(this.input.getTotalBytesRead() < v1 + v);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((DoubleArrayList)list0).addDouble(this.input.readDouble());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 1: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                this.verifyPackedFixed64Length(v3);
                int v4 = this.input.getTotalBytesRead();
                do {
                    list0.add(this.input.readDouble());
                }
                while(this.input.getTotalBytesRead() < v4 + v3);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readDouble());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int readEnum() throws IOException {
        this.requireWireType(0);
        return this.input.readEnum();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readEnumList(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof IntArrayList) {
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((IntArrayList)list0).addInt(this.input.readEnum());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((IntArrayList)list0).addInt(this.input.readEnum());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(this.input.readEnum());
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readEnum());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    private Object readField(FieldType wireFormat$FieldType0, Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        switch(com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[wireFormat$FieldType0.ordinal()]) {
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
                throw new IllegalArgumentException("unsupported field type.");
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int readFixed32() throws IOException {
        this.requireWireType(5);
        return this.input.readFixed32();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFixed32List(List list0) throws IOException {
        int v2;
        if(list0 instanceof IntArrayList) {
            switch(this.tag & 7) {
                case 2: {
                    int v = this.input.readUInt32();
                    this.verifyPackedFixed32Length(v);
                    int v1 = this.input.getTotalBytesRead();
                    do {
                        ((IntArrayList)list0).addInt(this.input.readFixed32());
                    }
                    while(this.input.getTotalBytesRead() < v1 + v);
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
                ((IntArrayList)list0).addInt(this.input.readFixed32());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
        }
        else {
            switch(this.tag & 7) {
                case 2: {
                    int v3 = this.input.readUInt32();
                    this.verifyPackedFixed32Length(v3);
                    int v4 = this.input.getTotalBytesRead();
                    do {
                        list0.add(this.input.readFixed32());
                    }
                    while(this.input.getTotalBytesRead() < v4 + v3);
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
                list0.add(this.input.readFixed32());
                if(this.input.isAtEnd()) {
                    break;
                }
                int v5 = this.input.readTag();
                if(v5 != this.tag) {
                    this.nextTag = v5;
                    return;
                }
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public long readFixed64() throws IOException {
        this.requireWireType(1);
        return this.input.readFixed64();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFixed64List(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof LongArrayList) {
            switch(this.tag & 7) {
                case 1: {
                    break;
                }
                case 2: {
                    int v = this.input.readUInt32();
                    this.verifyPackedFixed64Length(v);
                    int v1 = this.input.getTotalBytesRead();
                    do {
                        ((LongArrayList)list0).addLong(this.input.readFixed64());
                    }
                    while(this.input.getTotalBytesRead() < v1 + v);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((LongArrayList)list0).addLong(this.input.readFixed64());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 1: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                this.verifyPackedFixed64Length(v3);
                int v4 = this.input.getTotalBytesRead();
                do {
                    list0.add(this.input.readFixed64());
                }
                while(this.input.getTotalBytesRead() < v4 + v3);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readFixed64());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public float readFloat() throws IOException {
        this.requireWireType(5);
        return this.input.readFloat();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readFloatList(List list0) throws IOException {
        int v2;
        if(list0 instanceof FloatArrayList) {
            switch(this.tag & 7) {
                case 2: {
                    int v = this.input.readUInt32();
                    this.verifyPackedFixed32Length(v);
                    int v1 = this.input.getTotalBytesRead();
                    do {
                        ((FloatArrayList)list0).addFloat(this.input.readFloat());
                    }
                    while(this.input.getTotalBytesRead() < v1 + v);
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
                ((FloatArrayList)list0).addFloat(this.input.readFloat());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
        }
        else {
            switch(this.tag & 7) {
                case 2: {
                    int v3 = this.input.readUInt32();
                    this.verifyPackedFixed32Length(v3);
                    int v4 = this.input.getTotalBytesRead();
                    do {
                        list0.add(this.input.readFloat());
                    }
                    while(this.input.getTotalBytesRead() < v4 + v3);
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
                list0.add(this.input.readFloat());
                if(this.input.isAtEnd()) {
                    break;
                }
                int v5 = this.input.readTag();
                if(v5 != this.tag) {
                    this.nextTag = v5;
                    return;
                }
            }
        }
    }

    private Object readGroup(Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        Object object0 = schema0.newInstance();
        this.mergeGroupFieldInternal(object0, schema0, extensionRegistryLite0);
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
        if((this.tag & 7) != 3) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int v = this.tag;
        while(true) {
            list0.add(this.readGroup(schema0, extensionRegistryLite0));
            if(this.input.isAtEnd() || this.nextTag != 0) {
                break;
            }
            int v1 = this.input.readTag();
            if(v1 != v) {
                this.nextTag = v1;
                return;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    @Deprecated
    public void readGroupList(List list0, Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        this.readGroupList(list0, Protobuf.getInstance().schemaFor(class0), extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int readInt32() throws IOException {
        this.requireWireType(0);
        return this.input.readInt32();
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
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((IntArrayList)list0).addInt(this.input.readInt32());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((IntArrayList)list0).addInt(this.input.readInt32());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(this.input.readInt32());
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readInt32());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public long readInt64() throws IOException {
        this.requireWireType(0);
        return this.input.readInt64();
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
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((LongArrayList)list0).addLong(this.input.readInt64());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((LongArrayList)list0).addLong(this.input.readInt64());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(this.input.readInt64());
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readInt64());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readMap(Map map0, Metadata mapEntryLite$Metadata0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        this.requireWireType(2);
        int v = this.input.readUInt32();
        int v1 = this.input.pushLimit(v);
        Object object0 = mapEntryLite$Metadata0.defaultKey;
        Object object1 = mapEntryLite$Metadata0.defaultValue;
        try {
        alab1:
            while(true) {
                while(true) {
                    int v3 = this.getFieldNumber();
                    if(v3 == 0x7FFFFFFF || this.input.isAtEnd()) {
                        break alab1;
                    }
                    try {
                        switch(v3) {
                            case 1: {
                                object0 = this.readField(mapEntryLite$Metadata0.keyType, null, null);
                                continue;
                            }
                            case 2: {
                                goto label_14;
                            }
                            default: {
                                if(this.skipField()) {
                                    continue;
                                }
                            }
                        }
                        throw new InvalidProtocolBufferException("Unable to parse map entry.");
                    label_14:
                        Class class0 = mapEntryLite$Metadata0.defaultValue.getClass();
                        object1 = this.readField(mapEntryLite$Metadata0.valueType, class0, extensionRegistryLite0);
                        continue;
                    }
                    catch(InvalidWireTypeException unused_ex) {
                    }
                    break;
                }
                if(!this.skipField()) {
                    throw new InvalidProtocolBufferException("Unable to parse map entry.");
                }
            }
            map0.put(object0, object1);
        }
        finally {
            this.input.popLimit(v1);
        }
    }

    private Object readMessage(Schema schema0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        Object object0 = schema0.newInstance();
        this.mergeMessageFieldInternal(object0, schema0, extensionRegistryLite0);
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
        if((this.tag & 7) != 2) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
        int v = this.tag;
        while(true) {
            list0.add(this.readMessage(schema0, extensionRegistryLite0));
            if(this.input.isAtEnd() || this.nextTag != 0) {
                break;
            }
            int v1 = this.input.readTag();
            if(v1 != v) {
                this.nextTag = v1;
                return;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readMessageList(List list0, Class class0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
        this.readMessageList(list0, Protobuf.getInstance().schemaFor(class0), extensionRegistryLite0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int readSFixed32() throws IOException {
        this.requireWireType(5);
        return this.input.readSFixed32();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSFixed32List(List list0) throws IOException {
        int v2;
        if(list0 instanceof IntArrayList) {
            switch(this.tag & 7) {
                case 2: {
                    int v = this.input.readUInt32();
                    this.verifyPackedFixed32Length(v);
                    int v1 = this.input.getTotalBytesRead();
                    do {
                        ((IntArrayList)list0).addInt(this.input.readSFixed32());
                    }
                    while(this.input.getTotalBytesRead() < v1 + v);
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
                ((IntArrayList)list0).addInt(this.input.readSFixed32());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
        }
        else {
            switch(this.tag & 7) {
                case 2: {
                    int v3 = this.input.readUInt32();
                    this.verifyPackedFixed32Length(v3);
                    int v4 = this.input.getTotalBytesRead();
                    do {
                        list0.add(this.input.readSFixed32());
                    }
                    while(this.input.getTotalBytesRead() < v4 + v3);
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
                list0.add(this.input.readSFixed32());
                if(this.input.isAtEnd()) {
                    break;
                }
                int v5 = this.input.readTag();
                if(v5 != this.tag) {
                    this.nextTag = v5;
                    return;
                }
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public long readSFixed64() throws IOException {
        this.requireWireType(1);
        return this.input.readSFixed64();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSFixed64List(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof LongArrayList) {
            switch(this.tag & 7) {
                case 1: {
                    break;
                }
                case 2: {
                    int v = this.input.readUInt32();
                    this.verifyPackedFixed64Length(v);
                    int v1 = this.input.getTotalBytesRead();
                    do {
                        ((LongArrayList)list0).addLong(this.input.readSFixed64());
                    }
                    while(this.input.getTotalBytesRead() < v1 + v);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((LongArrayList)list0).addLong(this.input.readSFixed64());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 1: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                this.verifyPackedFixed64Length(v3);
                int v4 = this.input.getTotalBytesRead();
                do {
                    list0.add(this.input.readSFixed64());
                }
                while(this.input.getTotalBytesRead() < v4 + v3);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readSFixed64());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int readSInt32() throws IOException {
        this.requireWireType(0);
        return this.input.readSInt32();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSInt32List(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof IntArrayList) {
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((IntArrayList)list0).addInt(this.input.readSInt32());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((IntArrayList)list0).addInt(this.input.readSInt32());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(this.input.readSInt32());
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readSInt32());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public long readSInt64() throws IOException {
        this.requireWireType(0);
        return this.input.readSInt64();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readSInt64List(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof LongArrayList) {
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((LongArrayList)list0).addLong(this.input.readSInt64());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((LongArrayList)list0).addLong(this.input.readSInt64());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(this.input.readSInt64());
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readSInt64());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public String readString() throws IOException {
        this.requireWireType(2);
        return this.input.readString();
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
                if(this.input.isAtEnd()) {
                    return;
                }
                v = this.input.readTag();
            }
            while(v == this.tag);
            this.nextTag = v;
            return;
        }
        do {
            list0.add((z ? this.readStringRequireUtf8() : this.readString()));
            if(this.input.isAtEnd()) {
                return;
            }
            v1 = this.input.readTag();
        }
        while(v1 == this.tag);
        this.nextTag = v1;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readStringListRequireUtf8(List list0) throws IOException {
        this.readStringListInternal(list0, true);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public String readStringRequireUtf8() throws IOException {
        this.requireWireType(2);
        return this.input.readStringRequireUtf8();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public int readUInt32() throws IOException {
        this.requireWireType(0);
        return this.input.readUInt32();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public void readUInt32List(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof IntArrayList) {
            switch(this.tag & 7) {
                case 0: {
                    break;
                }
                case 2: {
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((IntArrayList)list0).addInt(this.input.readUInt32());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((IntArrayList)list0).addInt(this.input.readUInt32());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(this.input.readUInt32());
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readUInt32());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public long readUInt64() throws IOException {
        this.requireWireType(0);
        return this.input.readUInt64();
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
                    int v = this.input.readUInt32();
                    int v1 = this.input.getTotalBytesRead() + v;
                    do {
                        ((LongArrayList)list0).addLong(this.input.readUInt64());
                    }
                    while(this.input.getTotalBytesRead() < v1);
                    this.requirePosition(v1);
                    return;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
            do {
                ((LongArrayList)list0).addLong(this.input.readUInt64());
                if(this.input.isAtEnd()) {
                    return;
                }
                v2 = this.input.readTag();
            }
            while(v2 == this.tag);
            this.nextTag = v2;
            return;
        }
        switch(this.tag & 7) {
            case 0: {
                break;
            }
            case 2: {
                int v3 = this.input.readUInt32();
                int v4 = this.input.getTotalBytesRead() + v3;
                do {
                    list0.add(this.input.readUInt64());
                }
                while(this.input.getTotalBytesRead() < v4);
                this.requirePosition(v4);
                return;
            }
            default: {
                throw InvalidProtocolBufferException.invalidWireType();
            }
        }
        do {
            list0.add(this.input.readUInt64());
            if(this.input.isAtEnd()) {
                return;
            }
            v5 = this.input.readTag();
        }
        while(v5 == this.tag);
        this.nextTag = v5;
    }

    private void requirePosition(int v) throws IOException {
        if(this.input.getTotalBytesRead() != v) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private void requireWireType(int v) throws IOException {
        if((this.tag & 7) != v) {
            throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean shouldDiscardUnknownFields() {
        return this.input.shouldDiscardUnknownFields();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.Reader
    public boolean skipField() throws IOException {
        if(!this.input.isAtEnd()) {
            return this.tag == this.endGroupTag ? false : this.input.skipField(this.tag);
        }
        return false;
    }

    private void verifyPackedFixed32Length(int v) throws IOException {
        if((v & 3) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    private void verifyPackedFixed64Length(int v) throws IOException {
        if((v & 7) != 0) {
            throw InvalidProtocolBufferException.parseFailure();
        }
    }

    class com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1 {
        static final int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType;

        static {
            int[] arr_v = new int[FieldType.values().length];
            com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType = arr_v;
            try {
                arr_v[FieldType.BOOL.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.BYTES.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.DOUBLE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.ENUM.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED32.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FIXED64.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.FLOAT.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT32.ordinal()] = 8;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.INT64.ordinal()] = 9;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.MESSAGE.ordinal()] = 10;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED32.ordinal()] = 11;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SFIXED64.ordinal()] = 12;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT32.ordinal()] = 13;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.SINT64.ordinal()] = 14;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.STRING.ordinal()] = 15;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT32.ordinal()] = 16;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.shaded.protobuf.CodedInputStreamReader.1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[FieldType.UINT64.ordinal()] = 17;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

