package androidx.collection;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001A\u0019\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0003H\u0080\b\u001A\u0019\u0010\u0005\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00062\u0006\u0010\u0004\u001A\u00020\u0006H\u0080\b¨\u0006\u0007"}, d2 = {"packFloats", "", "val1", "", "val2", "packInts", "", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class PackingUtilsKt {
    public static final long packFloats(float f, float f1) {
        return ((long)Float.floatToRawIntBits(f1)) & 0xFFFFFFFFL | ((long)Float.floatToRawIntBits(f)) << 0x20;
    }

    public static final long packInts(int v, int v1) {
        return ((long)v1) & 0xFFFFFFFFL | ((long)v) << 0x20;
    }
}

