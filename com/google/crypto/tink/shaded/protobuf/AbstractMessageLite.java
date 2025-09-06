package com.google.crypto.tink.shaded.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractMessageLite implements MessageLite {
    public static abstract class Builder implements com.google.crypto.tink.shaded.protobuf.MessageLite.Builder {
        static final class LimitedInputStream extends FilterInputStream {
            private int limit;

            LimitedInputStream(InputStream inputStream0, int v) {
                super(inputStream0);
                this.limit = v;
            }

            @Override
            public int available() throws IOException {
                return Math.min(super.available(), this.limit);
            }

            @Override
            public int read() throws IOException {
                if(this.limit <= 0) {
                    return -1;
                }
                int v = super.read();
                if(v >= 0) {
                    --this.limit;
                }
                return v;
            }

            @Override
            public int read(byte[] arr_b, int v, int v1) throws IOException {
                int v2 = this.limit;
                if(v2 <= 0) {
                    return -1;
                }
                int v3 = super.read(arr_b, v, Math.min(v1, v2));
                if(v3 >= 0) {
                    this.limit -= v3;
                }
                return v3;
            }

            @Override
            public long skip(long v) throws IOException {
                int v1 = (int)super.skip(Math.min(v, this.limit));
                if(v1 >= 0) {
                    this.limit -= v1;
                }
                return (long)v1;
            }
        }

        @Deprecated
        protected static void addAll(Iterable iterable0, Collection collection0) {
            Builder.addAll(iterable0, ((List)collection0));
        }

        protected static void addAll(Iterable iterable0, List list0) {
            Internal.checkNotNull(iterable0);
            if(iterable0 instanceof LazyStringList) {
                List list1 = ((LazyStringList)iterable0).getUnderlyingElements();
                int v = list0.size();
                for(Object object0: list1) {
                    if(object0 == null) {
                        String s = "Element at index " + (((LazyStringList)list0).size() - v) + " is null.";
                        for(int v1 = ((LazyStringList)list0).size() - 1; v1 >= v; --v1) {
                            ((LazyStringList)list0).remove(v1);
                        }
                        throw new NullPointerException(s);
                    }
                    if(object0 instanceof ByteString) {
                        ((LazyStringList)list0).add(((ByteString)object0));
                    }
                    else {
                        ((LazyStringList)list0).add(((String)object0));
                    }
                }
                return;
            }
            if(iterable0 instanceof PrimitiveNonBoxingCollection) {
                list0.addAll(((Collection)iterable0));
                return;
            }
            Builder.addAllCheckingNulls(iterable0, list0);
        }

        private static void addAllCheckingNulls(Iterable iterable0, List list0) {
            if(list0 instanceof ArrayList && iterable0 instanceof Collection) {
                ((ArrayList)list0).ensureCapacity(list0.size() + ((Collection)iterable0).size());
            }
            int v = list0.size();
            for(Object object0: iterable0) {
                if(object0 == null) {
                    String s = "Element at index " + (list0.size() - v) + " is null.";
                    for(int v1 = list0.size() - 1; v1 >= v; --v1) {
                        list0.remove(v1);
                    }
                    throw new NullPointerException(s);
                }
                list0.add(object0);
            }
        }

        public abstract Builder clone();

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder clone() {
            return this.clone();
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        private String getReadingExceptionMessage(String s) [...] // 潜在的解密器

        protected abstract Builder internalMergeFrom(AbstractMessageLite arg1);

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public boolean mergeDelimitedFrom(InputStream inputStream0) throws IOException {
            return this.mergeDelimitedFrom(inputStream0, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public boolean mergeDelimitedFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            int v = inputStream0.read();
            if(v == -1) {
                return false;
            }
            this.mergeFrom(new LimitedInputStream(inputStream0, CodedInputStream.readRawVarint32(v, inputStream0)), extensionRegistryLite0);
            return true;
        }

        public Builder mergeFrom(ByteString byteString0) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStream0 = byteString0.newCodedInput();
                this.mergeFrom(codedInputStream0);
                codedInputStream0.checkLastTagWas(0);
                return this;
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                throw invalidProtocolBufferException0;
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Reading com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder from a ByteString threw an IOException (should never happen).", iOException0);
            }
        }

        public Builder mergeFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStream0 = byteString0.newCodedInput();
                this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                codedInputStream0.checkLastTagWas(0);
                return this;
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                throw invalidProtocolBufferException0;
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Reading com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder from a ByteString threw an IOException (should never happen).", iOException0);
            }
        }

        public Builder mergeFrom(CodedInputStream codedInputStream0) throws IOException {
            return this.mergeFrom(codedInputStream0, ExtensionRegistryLite.getEmptyRegistry());
        }

        public abstract Builder mergeFrom(CodedInputStream arg1, ExtensionRegistryLite arg2) throws IOException;

        public Builder mergeFrom(MessageLite messageLite0) {
            if(!this.getDefaultInstanceForType().getClass().isInstance(messageLite0)) {
                throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
            }
            return this.internalMergeFrom(((AbstractMessageLite)messageLite0));
        }

        public Builder mergeFrom(InputStream inputStream0) throws IOException {
            CodedInputStream codedInputStream0 = CodedInputStream.newInstance(inputStream0);
            this.mergeFrom(codedInputStream0);
            codedInputStream0.checkLastTagWas(0);
            return this;
        }

        public Builder mergeFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            CodedInputStream codedInputStream0 = CodedInputStream.newInstance(inputStream0);
            this.mergeFrom(codedInputStream0, extensionRegistryLite0);
            codedInputStream0.checkLastTagWas(0);
            return this;
        }

        public Builder mergeFrom(byte[] arr_b) throws InvalidProtocolBufferException {
            return this.mergeFrom(arr_b, 0, arr_b.length);
        }

        public Builder mergeFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStream0 = CodedInputStream.newInstance(arr_b, v, v1);
                this.mergeFrom(codedInputStream0);
                codedInputStream0.checkLastTagWas(0);
                return this;
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                throw invalidProtocolBufferException0;
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Reading com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder from a byte array threw an IOException (should never happen).", iOException0);
            }
        }

        public Builder mergeFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            try {
                CodedInputStream codedInputStream0 = CodedInputStream.newInstance(arr_b, v, v1);
                this.mergeFrom(codedInputStream0, extensionRegistryLite0);
                codedInputStream0.checkLastTagWas(0);
                return this;
            }
            catch(InvalidProtocolBufferException invalidProtocolBufferException0) {
                throw invalidProtocolBufferException0;
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Reading com.google.crypto.tink.shaded.protobuf.AbstractMessageLite$Builder from a byte array threw an IOException (should never happen).", iOException0);
            }
        }

        public Builder mergeFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return this.mergeFrom(arr_b, 0, arr_b.length, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(ByteString byteString0) throws InvalidProtocolBufferException {
            return this.mergeFrom(byteString0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(ByteString byteString0, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return this.mergeFrom(byteString0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0) throws IOException {
            return this.mergeFrom(codedInputStream0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(MessageLite messageLite0) {
            return this.mergeFrom(messageLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(InputStream inputStream0) throws IOException {
            return this.mergeFrom(inputStream0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(InputStream inputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return this.mergeFrom(inputStream0, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b) throws InvalidProtocolBufferException {
            return this.mergeFrom(arr_b);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1) throws InvalidProtocolBufferException {
            return this.mergeFrom(arr_b, v, v1);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, int v, int v1, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return this.mergeFrom(arr_b, v, v1, extensionRegistryLite0);
        }

        @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite$Builder
        public com.google.crypto.tink.shaded.protobuf.MessageLite.Builder mergeFrom(byte[] arr_b, ExtensionRegistryLite extensionRegistryLite0) throws InvalidProtocolBufferException {
            return this.mergeFrom(arr_b, extensionRegistryLite0);
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite0) {
            return new UninitializedMessageException(messageLite0);
        }
    }

    public interface InternalOneOfEnum {
        int getNumber();
    }

    protected int memoizedHashCode;

    public AbstractMessageLite() {
        this.memoizedHashCode = 0;
    }

    @Deprecated
    protected static void addAll(Iterable iterable0, Collection collection0) {
        Builder.addAll(iterable0, ((List)collection0));
    }

    protected static void addAll(Iterable iterable0, List list0) {
        Builder.addAll(iterable0, list0);
    }

    protected static void checkByteStringIsUtf8(ByteString byteString0) throws IllegalArgumentException {
        if(!byteString0.isValidUtf8()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    int getMemoizedSerializedSize() {
        throw new UnsupportedOperationException();
    }

    int getSerializedSize(Schema schema0) {
        int v = this.getMemoizedSerializedSize();
        if(v == -1) {
            int v1 = schema0.getSerializedSize(this);
            this.setMemoizedSerializedSize(v1);
            return v1;
        }
        return v;
    }

    private String getSerializingExceptionMessage(String s) [...] // 潜在的解密器

    UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    void setMemoizedSerializedSize(int v) {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite
    public byte[] toByteArray() {
        try {
            byte[] arr_b = new byte[this.getSerializedSize()];
            CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(arr_b);
            this.writeTo(codedOutputStream0);
            codedOutputStream0.checkNoSpaceLeft();
            return arr_b;
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing com.google.crypto.tink.shaded.protobuf.AbstractMessageLite to a byte array threw an IOException (should never happen).", iOException0);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite
    public ByteString toByteString() {
        try {
            CodedBuilder byteString$CodedBuilder0 = ByteString.newCodedBuilder(this.getSerializedSize());
            this.writeTo(byteString$CodedBuilder0.getCodedOutput());
            return byteString$CodedBuilder0.build();
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Serializing com.google.crypto.tink.shaded.protobuf.AbstractMessageLite to a ByteString threw an IOException (should never happen).", iOException0);
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite
    public void writeDelimitedTo(OutputStream outputStream0) throws IOException {
        int v = this.getSerializedSize();
        CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(outputStream0, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeUInt32SizeNoTag(v) + v));
        codedOutputStream0.writeUInt32NoTag(v);
        this.writeTo(codedOutputStream0);
        codedOutputStream0.flush();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.MessageLite
    public void writeTo(OutputStream outputStream0) throws IOException {
        CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(outputStream0, CodedOutputStream.computePreferredBufferSize(this.getSerializedSize()));
        this.writeTo(codedOutputStream0);
        codedOutputStream0.flush();
    }
}

