package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class AbstractMessageLite implements MessageLite {
    public static abstract class Builder implements kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder {
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
                long v1 = super.skip(Math.min(v, this.limit));
                if(v1 >= 0L) {
                    this.limit = (int)(((long)this.limit) - v1);
                }
                return v1;
            }
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return this.clone();
        }

        public abstract Builder clone();

        public abstract Builder mergeFrom(CodedInputStream arg1, ExtensionRegistryLite arg2) throws IOException;

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.MessageLite$Builder
        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedInputStream0, ExtensionRegistryLite extensionRegistryLite0) throws IOException {
            return this.mergeFrom(codedInputStream0, extensionRegistryLite0);
        }

        protected static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite0) {
            return new UninitializedMessageException(messageLite0);
        }
    }

    protected int memoizedHashCode;

    public AbstractMessageLite() {
        this.memoizedHashCode = 0;
    }

    UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException(this);
    }

    public void writeDelimitedTo(OutputStream outputStream0) throws IOException {
        int v = this.getSerializedSize();
        CodedOutputStream codedOutputStream0 = CodedOutputStream.newInstance(outputStream0, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(v) + v));
        codedOutputStream0.writeRawVarint32(v);
        this.writeTo(codedOutputStream0);
        codedOutputStream0.flush();
    }
}

