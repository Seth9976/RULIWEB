package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.util.Arrays;

public final class UnknownFieldSetLite {
    private static final UnknownFieldSetLite DEFAULT_INSTANCE = null;
    private static final int MIN_CAPACITY = 8;
    private int count;
    private boolean isMutable;
    private int memoizedSerializedSize;
    private Object[] objects;
    private int[] tags;

    static {
        UnknownFieldSetLite.DEFAULT_INSTANCE = new UnknownFieldSetLite(0, new int[0], new Object[0], false);
    }

    private UnknownFieldSetLite() {
        this(0, new int[8], new Object[8], true);
    }

    private UnknownFieldSetLite(int v, int[] arr_v, Object[] arr_object, boolean z) {
        this.memoizedSerializedSize = -1;
        this.count = v;
        this.tags = arr_v;
        this.objects = arr_object;
        this.isMutable = z;
    }

    void checkMutable() {
        if(!this.isMutable) {
            throw new UnsupportedOperationException();
        }
    }

    private void ensureCapacity(int v) {
        int[] arr_v = this.tags;
        if(v > arr_v.length) {
            int v1 = this.count + this.count / 2;
            if(v1 >= v) {
                v = v1;
            }
            if(v < 8) {
                v = 8;
            }
            this.tags = Arrays.copyOf(arr_v, v);
            this.objects = Arrays.copyOf(this.objects, v);
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof UnknownFieldSetLite ? this.count == ((UnknownFieldSetLite)object0).count && UnknownFieldSetLite.tagsEquals(this.tags, ((UnknownFieldSetLite)object0).tags, this.count) && UnknownFieldSetLite.objectsEquals(this.objects, ((UnknownFieldSetLite)object0).objects, this.count) : false;
    }

    public static UnknownFieldSetLite getDefaultInstance() {
        return UnknownFieldSetLite.DEFAULT_INSTANCE;
    }

    public int getSerializedSize() {
        int v5;
        int v = this.memoizedSerializedSize;
        if(v != -1) {
            return v;
        }
        int v2 = 0;
        for(int v1 = 0; v1 < this.count; ++v1) {
            int v3 = this.tags[v1];
            int v4 = v3 >>> 3;
            switch(v3 & 7) {
                case 0: {
                    v5 = CodedOutputStream.computeUInt64Size(v4, ((long)(((Long)this.objects[v1]))));
                    break;
                }
                case 1: {
                    v5 = CodedOutputStream.computeFixed64Size(v4, ((long)(((Long)this.objects[v1]))));
                    break;
                }
                case 2: {
                    v5 = CodedOutputStream.computeBytesSize(v4, ((ByteString)this.objects[v1]));
                    break;
                }
                case 3: {
                    v5 = CodedOutputStream.computeTagSize(v4) * 2 + ((UnknownFieldSetLite)this.objects[v1]).getSerializedSize();
                    break;
                }
                case 5: {
                    v5 = CodedOutputStream.computeFixed32Size(v4, ((int)(((Integer)this.objects[v1]))));
                    break;
                }
                default: {
                    throw new IllegalStateException(InvalidProtocolBufferException.invalidWireType());
                }
            }
            v2 += v5;
        }
        this.memoizedSerializedSize = v2;
        return v2;
    }

    public int getSerializedSizeAsMessageSet() {
        int v = this.memoizedSerializedSize;
        if(v != -1) {
            return v;
        }
        int v2 = 0;
        for(int v1 = 0; v1 < this.count; ++v1) {
            v2 += CodedOutputStream.computeRawMessageSetExtensionSize(WireFormat.getTagFieldNumber(this.tags[v1]), ((ByteString)this.objects[v1]));
        }
        this.memoizedSerializedSize = v2;
        return v2;
    }

    private static int hashCode(int[] arr_v, int v) {
        int v1 = 17;
        for(int v2 = 0; v2 < v; ++v2) {
            v1 = v1 * 0x1F + arr_v[v2];
        }
        return v1;
    }

    private static int hashCode(Object[] arr_object, int v) {
        int v1 = 17;
        for(int v2 = 0; v2 < v; ++v2) {
            v1 = v1 * 0x1F + arr_object[v2].hashCode();
        }
        return v1;
    }

    @Override
    public int hashCode() {
        return ((this.count + 0x20F) * 0x1F + UnknownFieldSetLite.hashCode(this.tags, this.count)) * 0x1F + UnknownFieldSetLite.hashCode(this.objects, this.count);
    }

    public void makeImmutable() {
        this.isMutable = false;
    }

    boolean mergeFieldFrom(int v, CodedInputStream codedInputStream0) throws IOException {
        this.checkMutable();
        switch(v & 7) {
            case 0: {
                this.storeField(v, codedInputStream0.readInt64());
                return true;
            }
            case 1: {
                this.storeField(v, codedInputStream0.readFixed64());
                return true;
            }
            default: {
                if((v & 7) != 2) {
                    switch(v & 7) {
                        case 3: {
                            UnknownFieldSetLite unknownFieldSetLite0 = new UnknownFieldSetLite();
                            unknownFieldSetLite0.mergeFrom(codedInputStream0);
                            codedInputStream0.checkLastTagWas(v >>> 3 << 3 | 4);
                            this.storeField(v, unknownFieldSetLite0);
                            return true;
                        }
                        case 4: {
                            return false;
                        }
                        default: {
                            if((v & 7) != 5) {
                                throw InvalidProtocolBufferException.invalidWireType();
                            }
                            this.storeField(v, codedInputStream0.readFixed32());
                            return true;
                        }
                    }
                }
                this.storeField(v, codedInputStream0.readBytes());
                return true;
            }
        }
    }

    private UnknownFieldSetLite mergeFrom(CodedInputStream codedInputStream0) throws IOException {
        do {
            int v = codedInputStream0.readTag();
        }
        while(v != 0 && this.mergeFieldFrom(v, codedInputStream0));
        return this;
    }

    UnknownFieldSetLite mergeFrom(UnknownFieldSetLite unknownFieldSetLite0) {
        if(unknownFieldSetLite0.equals(UnknownFieldSetLite.getDefaultInstance())) {
            return this;
        }
        this.checkMutable();
        int v = this.count + unknownFieldSetLite0.count;
        this.ensureCapacity(v);
        System.arraycopy(unknownFieldSetLite0.tags, 0, this.tags, this.count, unknownFieldSetLite0.count);
        System.arraycopy(unknownFieldSetLite0.objects, 0, this.objects, this.count, unknownFieldSetLite0.count);
        this.count = v;
        return this;
    }

    UnknownFieldSetLite mergeLengthDelimitedField(int v, ByteString byteString0) {
        this.checkMutable();
        if(v == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        this.storeField(v << 3 | 2, byteString0);
        return this;
    }

    UnknownFieldSetLite mergeVarintField(int v, int v1) {
        this.checkMutable();
        if(v == 0) {
            throw new IllegalArgumentException("Zero is not a valid field number.");
        }
        this.storeField(v << 3, ((long)v1));
        return this;
    }

    static UnknownFieldSetLite mutableCopyOf(UnknownFieldSetLite unknownFieldSetLite0, UnknownFieldSetLite unknownFieldSetLite1) {
        int v = unknownFieldSetLite0.count + unknownFieldSetLite1.count;
        int[] arr_v = Arrays.copyOf(unknownFieldSetLite0.tags, v);
        System.arraycopy(unknownFieldSetLite1.tags, 0, arr_v, unknownFieldSetLite0.count, unknownFieldSetLite1.count);
        Object[] arr_object = Arrays.copyOf(unknownFieldSetLite0.objects, v);
        System.arraycopy(unknownFieldSetLite1.objects, 0, arr_object, unknownFieldSetLite0.count, unknownFieldSetLite1.count);
        return new UnknownFieldSetLite(v, arr_v, arr_object, true);
    }

    static UnknownFieldSetLite newInstance() {
        return new UnknownFieldSetLite();
    }

    private static boolean objectsEquals(Object[] arr_object, Object[] arr_object1, int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            if(!arr_object[v1].equals(arr_object1[v1])) {
                return false;
            }
        }
        return true;
    }

    final void printWithIndent(StringBuilder stringBuilder0, int v) {
        for(int v1 = 0; v1 < this.count; ++v1) {
            MessageLiteToString.printField(stringBuilder0, v, String.valueOf(WireFormat.getTagFieldNumber(this.tags[v1])), this.objects[v1]);
        }
    }

    void storeField(int v, Object object0) {
        this.checkMutable();
        this.ensureCapacity(this.count + 1);
        int v1 = this.count;
        this.tags[v1] = v;
        this.objects[v1] = object0;
        this.count = v1 + 1;
    }

    private static boolean tagsEquals(int[] arr_v, int[] arr_v1, int v) {
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_v[v1] != arr_v1[v1]) {
                return false;
            }
        }
        return true;
    }

    public void writeAsMessageSetTo(CodedOutputStream codedOutputStream0) throws IOException {
        for(int v = 0; v < this.count; ++v) {
            codedOutputStream0.writeRawMessageSetExtension(WireFormat.getTagFieldNumber(this.tags[v]), ((ByteString)this.objects[v]));
        }
    }

    void writeAsMessageSetTo(Writer writer0) throws IOException {
        if(writer0.fieldOrder() == FieldOrder.DESCENDING) {
            for(int v = this.count - 1; v >= 0; --v) {
                writer0.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[v]), this.objects[v]);
            }
            return;
        }
        for(int v1 = 0; v1 < this.count; ++v1) {
            writer0.writeMessageSetItem(WireFormat.getTagFieldNumber(this.tags[v1]), this.objects[v1]);
        }
    }

    private static void writeField(int v, Object object0, Writer writer0) throws IOException {
        switch(v & 7) {
            case 0: {
                writer0.writeInt64(v >>> 3, ((long)(((Long)object0))));
                return;
            }
            case 1: {
                writer0.writeFixed64(v >>> 3, ((long)(((Long)object0))));
                return;
            }
            case 2: {
                writer0.writeBytes(v >>> 3, ((ByteString)object0));
                return;
            }
            case 3: {
                if(writer0.fieldOrder() == FieldOrder.ASCENDING) {
                    writer0.writeStartGroup(v >>> 3);
                    ((UnknownFieldSetLite)object0).writeTo(writer0);
                    writer0.writeEndGroup(v >>> 3);
                    return;
                }
                writer0.writeEndGroup(v >>> 3);
                ((UnknownFieldSetLite)object0).writeTo(writer0);
                writer0.writeStartGroup(v >>> 3);
                return;
            }
            case 5: {
                writer0.writeFixed32(v >>> 3, ((int)(((Integer)object0))));
                return;
            }
            default: {
                throw new RuntimeException(InvalidProtocolBufferException.invalidWireType());
            }
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream0) throws IOException {
        for(int v = 0; v < this.count; ++v) {
            int v1 = this.tags[v];
            int v2 = v1 >>> 3;
            switch(v1 & 7) {
                case 0: {
                    codedOutputStream0.writeUInt64(v2, ((long)(((Long)this.objects[v]))));
                    break;
                }
                case 1: {
                    codedOutputStream0.writeFixed64(v2, ((long)(((Long)this.objects[v]))));
                    break;
                }
                case 2: {
                    codedOutputStream0.writeBytes(v2, ((ByteString)this.objects[v]));
                    break;
                }
                case 3: {
                    codedOutputStream0.writeTag(v2, 3);
                    ((UnknownFieldSetLite)this.objects[v]).writeTo(codedOutputStream0);
                    codedOutputStream0.writeTag(v2, 4);
                    break;
                }
                case 5: {
                    codedOutputStream0.writeFixed32(v2, ((int)(((Integer)this.objects[v]))));
                    break;
                }
                default: {
                    throw InvalidProtocolBufferException.invalidWireType();
                }
            }
        }
    }

    public void writeTo(Writer writer0) throws IOException {
        if(this.count != 0) {
            if(writer0.fieldOrder() == FieldOrder.ASCENDING) {
                for(int v = 0; v < this.count; ++v) {
                    UnknownFieldSetLite.writeField(this.tags[v], this.objects[v], writer0);
                }
                return;
            }
            for(int v1 = this.count - 1; v1 >= 0; --v1) {
                UnknownFieldSetLite.writeField(this.tags[v1], this.objects[v1], writer0);
            }
        }
    }
}

