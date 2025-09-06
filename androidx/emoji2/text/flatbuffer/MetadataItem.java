package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class MetadataItem extends Table {
    public static final class Vector extends BaseVector {
        public Vector __assign(int v, int v1, ByteBuffer byteBuffer0) {
            this.__reset(v, v1, byteBuffer0);
            return this;
        }

        public MetadataItem get(int v) {
            return this.get(new MetadataItem(), v);
        }

        public MetadataItem get(MetadataItem metadataItem0, int v) {
            return metadataItem0.__assign(MetadataItem.__indirect(this.__element(v), this.bb), this.bb);
        }
    }

    public static void ValidateVersion() {
    }

    public MetadataItem __assign(int v, ByteBuffer byteBuffer0) {
        this.__init(v, byteBuffer0);
        return this;
    }

    public void __init(int v, ByteBuffer byteBuffer0) {
        this.__reset(v, byteBuffer0);
    }

    public static void addCodepoints(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.addOffset(6, v, 0);
    }

    public static void addCompatAdded(FlatBufferBuilder flatBufferBuilder0, short v) {
        flatBufferBuilder0.addShort(3, v, 0);
    }

    public static void addEmojiStyle(FlatBufferBuilder flatBufferBuilder0, boolean z) {
        flatBufferBuilder0.addBoolean(1, z, false);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder0, short v) {
        flatBufferBuilder0.addShort(5, v, 0);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.addInt(0, v, 0);
    }

    public static void addSdkAdded(FlatBufferBuilder flatBufferBuilder0, short v) {
        flatBufferBuilder0.addShort(2, v, 0);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder0, short v) {
        flatBufferBuilder0.addShort(4, v, 0);
    }

    public int codepoints(int v) {
        int v1 = this.__offset(16);
        return v1 == 0 ? 0 : this.bb.getInt(this.__vector(v1) + v * 4);
    }

    public ByteBuffer codepointsAsByteBuffer() {
        return this.__vector_as_bytebuffer(16, 4);
    }

    public ByteBuffer codepointsInByteBuffer(ByteBuffer byteBuffer0) {
        return this.__vector_in_bytebuffer(byteBuffer0, 16, 4);
    }

    public int codepointsLength() {
        int v = this.__offset(16);
        return v == 0 ? 0 : this.__vector_len(v);
    }

    public IntVector codepointsVector() {
        return this.codepointsVector(new IntVector());
    }

    public IntVector codepointsVector(IntVector intVector0) {
        int v = this.__offset(16);
        return v == 0 ? null : intVector0.__assign(this.__vector(v), this.bb);
    }

    public short compatAdded() {
        int v = this.__offset(10);
        return v == 0 ? 0 : this.bb.getShort(v + this.bb_pos);
    }

    public static int createCodepointsVector(FlatBufferBuilder flatBufferBuilder0, int[] arr_v) {
        flatBufferBuilder0.startVector(4, arr_v.length, 4);
        for(int v = arr_v.length - 1; v >= 0; --v) {
            flatBufferBuilder0.addInt(arr_v[v]);
        }
        return flatBufferBuilder0.endVector();
    }

    public static int createMetadataItem(FlatBufferBuilder flatBufferBuilder0, int v, boolean z, short v1, short v2, short v3, short v4, int v5) {
        flatBufferBuilder0.startTable(7);
        MetadataItem.addCodepoints(flatBufferBuilder0, v5);
        MetadataItem.addId(flatBufferBuilder0, v);
        MetadataItem.addHeight(flatBufferBuilder0, v4);
        MetadataItem.addWidth(flatBufferBuilder0, v3);
        MetadataItem.addCompatAdded(flatBufferBuilder0, v2);
        MetadataItem.addSdkAdded(flatBufferBuilder0, v1);
        MetadataItem.addEmojiStyle(flatBufferBuilder0, z);
        return MetadataItem.endMetadataItem(flatBufferBuilder0);
    }

    public boolean emojiStyle() {
        int v = this.__offset(6);
        return v != 0 && this.bb.get(v + this.bb_pos) != 0;
    }

    public static int endMetadataItem(FlatBufferBuilder flatBufferBuilder0) {
        return flatBufferBuilder0.endTable();
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer0) {
        return MetadataItem.getRootAsMetadataItem(byteBuffer0, new MetadataItem());
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer0, MetadataItem metadataItem0) {
        byteBuffer0.order(ByteOrder.LITTLE_ENDIAN);
        return metadataItem0.__assign(byteBuffer0.getInt(byteBuffer0.position()) + byteBuffer0.position(), byteBuffer0);
    }

    public short height() {
        int v = this.__offset(14);
        return v == 0 ? 0 : this.bb.getShort(v + this.bb_pos);
    }

    public int id() {
        int v = this.__offset(4);
        return v == 0 ? 0 : this.bb.getInt(v + this.bb_pos);
    }

    public short sdkAdded() {
        int v = this.__offset(8);
        return v == 0 ? 0 : this.bb.getShort(v + this.bb_pos);
    }

    public static void startCodepointsVector(FlatBufferBuilder flatBufferBuilder0, int v) {
        flatBufferBuilder0.startVector(4, v, 4);
    }

    public static void startMetadataItem(FlatBufferBuilder flatBufferBuilder0) {
        flatBufferBuilder0.startTable(7);
    }

    public short width() {
        int v = this.__offset(12);
        return v == 0 ? 0 : this.bb.getShort(v + this.bb_pos);
    }
}

