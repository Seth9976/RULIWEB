package androidx.emoji2.text;

import android.content.res.AssetManager;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class MetadataListReader {
    static class ByteBufferReader implements OpenTypeReader {
        private final ByteBuffer mByteBuffer;

        ByteBufferReader(ByteBuffer byteBuffer0) {
            this.mByteBuffer = byteBuffer0;
            byteBuffer0.order(ByteOrder.BIG_ENDIAN);
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public long getPosition() {
            return (long)this.mByteBuffer.position();
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public int readTag() throws IOException {
            return this.mByteBuffer.getInt();
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public long readUnsignedInt() throws IOException {
            return MetadataListReader.toUnsignedInt(this.mByteBuffer.getInt());
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public int readUnsignedShort() throws IOException {
            return MetadataListReader.toUnsignedShort(this.mByteBuffer.getShort());
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public void skip(int v) throws IOException {
            this.mByteBuffer.position(this.mByteBuffer.position() + v);
        }
    }

    static class InputStreamOpenTypeReader implements OpenTypeReader {
        private final byte[] mByteArray;
        private final ByteBuffer mByteBuffer;
        private final InputStream mInputStream;
        private long mPosition;

        InputStreamOpenTypeReader(InputStream inputStream0) {
            this.mPosition = 0L;
            this.mInputStream = inputStream0;
            byte[] arr_b = new byte[4];
            this.mByteArray = arr_b;
            ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
            this.mByteBuffer = byteBuffer0;
            byteBuffer0.order(ByteOrder.BIG_ENDIAN);
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public long getPosition() {
            return this.mPosition;
        }

        private void read(int v) throws IOException {
            if(this.mInputStream.read(this.mByteArray, 0, v) != v) {
                throw new IOException("read failed");
            }
            this.mPosition += (long)v;
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public int readTag() throws IOException {
            this.mByteBuffer.position(0);
            this.read(4);
            return this.mByteBuffer.getInt();
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public long readUnsignedInt() throws IOException {
            this.mByteBuffer.position(0);
            this.read(4);
            return MetadataListReader.toUnsignedInt(this.mByteBuffer.getInt());
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public int readUnsignedShort() throws IOException {
            this.mByteBuffer.position(0);
            this.read(2);
            return MetadataListReader.toUnsignedShort(this.mByteBuffer.getShort());
        }

        @Override  // androidx.emoji2.text.MetadataListReader$OpenTypeReader
        public void skip(int v) throws IOException {
            while(v > 0) {
                int v1 = (int)this.mInputStream.skip(((long)v));
                if(v1 < 1) {
                    throw new IOException("Skip didn\'t move at least 1 byte forward");
                }
                v -= v1;
                this.mPosition += (long)v1;
            }
        }
    }

    static class OffsetInfo {
        private final long mLength;
        private final long mStartOffset;

        OffsetInfo(long v, long v1) {
            this.mStartOffset = v;
            this.mLength = v1;
        }

        long getLength() {
            return this.mLength;
        }

        long getStartOffset() {
            return this.mStartOffset;
        }
    }

    interface OpenTypeReader {
        public static final int UINT16_BYTE_COUNT = 2;
        public static final int UINT32_BYTE_COUNT = 4;

        long getPosition();

        int readTag() throws IOException;

        long readUnsignedInt() throws IOException;

        int readUnsignedShort() throws IOException;

        void skip(int arg1) throws IOException;
    }

    private static final int EMJI_TAG = 0x456D6A69;
    private static final int EMJI_TAG_DEPRECATED = 1701669481;
    private static final int META_TABLE_NAME = 0x6D657461;

    private static OffsetInfo findOffsetInfo(OpenTypeReader metadataListReader$OpenTypeReader0) throws IOException {
        long v3;
        metadataListReader$OpenTypeReader0.skip(4);
        int v = metadataListReader$OpenTypeReader0.readUnsignedShort();
        if(v > 100) {
            throw new IOException("Cannot read metadata.");
        }
        metadataListReader$OpenTypeReader0.skip(6);
        int v1 = 0;
        for(int v2 = 0; true; ++v2) {
            v3 = -1L;
            if(v2 >= v) {
                break;
            }
            int v4 = metadataListReader$OpenTypeReader0.readTag();
            metadataListReader$OpenTypeReader0.skip(4);
            v3 = metadataListReader$OpenTypeReader0.readUnsignedInt();
            metadataListReader$OpenTypeReader0.skip(4);
            if(0x6D657461 == v4) {
                break;
            }
        }
        if(v3 != -1L) {
            metadataListReader$OpenTypeReader0.skip(((int)(v3 - metadataListReader$OpenTypeReader0.getPosition())));
            metadataListReader$OpenTypeReader0.skip(12);
            long v5 = metadataListReader$OpenTypeReader0.readUnsignedInt();
            while(((long)v1) < v5) {
                int v6 = metadataListReader$OpenTypeReader0.readTag();
                long v7 = metadataListReader$OpenTypeReader0.readUnsignedInt();
                long v8 = metadataListReader$OpenTypeReader0.readUnsignedInt();
                if(0x456D6A69 != v6 && 1701669481 != v6) {
                    ++v1;
                    continue;
                }
                return new OffsetInfo(v7 + v3, v8);
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    static MetadataList read(AssetManager assetManager0, String s) throws IOException {
        try(InputStream inputStream0 = assetManager0.open(s)) {
            return MetadataListReader.read(inputStream0);
        }
    }

    static MetadataList read(InputStream inputStream0) throws IOException {
        InputStreamOpenTypeReader metadataListReader$InputStreamOpenTypeReader0 = new InputStreamOpenTypeReader(inputStream0);
        OffsetInfo metadataListReader$OffsetInfo0 = MetadataListReader.findOffsetInfo(metadataListReader$InputStreamOpenTypeReader0);
        metadataListReader$InputStreamOpenTypeReader0.skip(((int)(metadataListReader$OffsetInfo0.getStartOffset() - metadataListReader$InputStreamOpenTypeReader0.getPosition())));
        ByteBuffer byteBuffer0 = ByteBuffer.allocate(((int)metadataListReader$OffsetInfo0.getLength()));
        int v = inputStream0.read(byteBuffer0.array());
        if(((long)v) != metadataListReader$OffsetInfo0.getLength()) {
            throw new IOException("Needed " + metadataListReader$OffsetInfo0.getLength() + " bytes, got " + v);
        }
        return MetadataList.getRootAsMetadataList(byteBuffer0);
    }

    static MetadataList read(ByteBuffer byteBuffer0) throws IOException {
        ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
        byteBuffer1.position(((int)MetadataListReader.findOffsetInfo(new ByteBufferReader(byteBuffer1)).getStartOffset()));
        return MetadataList.getRootAsMetadataList(byteBuffer1);
    }

    static long toUnsignedInt(int v) {
        return ((long)v) & 0xFFFFFFFFL;
    }

    static int toUnsignedShort(short v) {
        return v & 0xFFFF;
    }
}

