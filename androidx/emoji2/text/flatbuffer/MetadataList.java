package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class MetadataList extends Table {
    public static final class Vector extends BaseVector {
        public Vector __assign(int v, int v1, ByteBuffer byteBuffer0) {
            this.__reset(v, v1, byteBuffer0);
            return this;
        }

        public MetadataList get(int v) {
            return this.get(new MetadataList(), v);
        }

        public MetadataList get(MetadataList metadataList0, int v) {
            return metadataList0.__assign(MetadataList.__indirect(this.__element(v), this.bb), this.bb);
        }
    }

    public static void ValidateVersion() {
    }

    public MetadataList __assign(int v, ByteBuffer byteBuffer0) {
        this.__init(v, byteBuffer0);
        return this;
    }

    public void __init(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, byteBuffer0);
    }

    public static void addList(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.addOffset(1, v, 0);
    }

    public static void addSourceSha(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.addOffset(2, v, 0);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.addInt(0, v, 0);
    }

    public static int createListVector(FlatBufferBuilder flatBufferBuilder0, int[] arr_v) {
        flatBufferBuilder0.startVector(4, arr_v.length, 4);
        for(int v = arr_v.length - 1; v >= 0; --v) {
            flatBufferBuilder0.addOffset(arr_v[v]);
        }
        return flatBufferBuilder0.endVector();
    }

    public static int createMetadataList(FlatBufferBuilder flatBufferBuilder0, int v, int v1, int v2) {
        flatBufferBuilder0.startTable(3);
        MetadataList.addSourceSha(flatBufferBuilder0, v2);
        MetadataList.addList(flatBufferBuilder0, v1);
        MetadataList.addVersion(flatBufferBuilder0, v);
        return MetadataList.endMetadataList(flatBufferBuilder0);
    }

    public static int endMetadataList(FlatBufferBuilder flatBufferBuilder0) {
        return flatBufferBuilder0.endTable();
    }

    public static void finishMetadataListBuffer(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.finish(v);
    }

    public static void finishSizePrefixedMetadataListBuffer(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.finishSizePrefixed(v);
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer0) {
        return MetadataList.getRootAsMetadataList(byteBuffer0, new MetadataList());
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer byteBuffer0, MetadataList metadataList0) {
        byteBuffer0.order(ByteOrder.LITTLE_ENDIAN);
        return metadataList0.__assign(byteBuffer0.getInt(byteBuffer0.position()) + byteBuffer0.position(), byteBuffer0);
    }

    public MetadataItem list(int v) {
        return this.list(new MetadataItem(), v);
    }

    public MetadataItem list(MetadataItem metadataItem0, int v) {
        int v1 = this.__offset(6);
        return v1 == 0 ? null : metadataItem0.__assign(this.__indirect(this.__vector(v1) + v * 4), this.bb);
    }

    public int listLength() {
        int v = this.__offset(6);
        return v == 0 ? 0 : this.__vector_len(v);
    }

    public androidx.emoji2.text.flatbuffer.MetadataItem.Vector listVector() {
        return this.listVector(new androidx.emoji2.text.flatbuffer.MetadataItem.Vector());
    }

    public androidx.emoji2.text.flatbuffer.MetadataItem.Vector listVector(androidx.emoji2.text.flatbuffer.MetadataItem.Vector metadataItem$Vector0) {
        int v = this.__offset(6);
        return v == 0 ? null : metadataItem$Vector0.__assign(this.__vector(v), 4, this.bb);
    }

    public String sourceSha() {
        int v = this.__offset(8);
        return v == 0 ? null : this.__string(v + this.bb_pos);
    }

    public ByteBuffer sourceShaAsByteBuffer() {
        return this.__vector_as_bytebuffer(8, 1);
    }

    public ByteBuffer sourceShaInByteBuffer(ByteBuffer byteBuffer0) {
        return this.__vector_in_bytebuffer(byteBuffer0, 8, 1);
    }

    public static void startListVector(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.startVector(4, v, 4);
    }

    public static void startMetadataList(FlatBufferBuilder flatBufferBuilder0) {
        flatBufferBuilder0.startTable(3);
    }

    public int version() {
        int v = this.__offset(4);
        return v == 0 ? 0 : this.bb.getInt(v + this.bb_pos);
    }
}

