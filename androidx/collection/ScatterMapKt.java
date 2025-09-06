package androidx.collection;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0001\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001A\u001E\u0010\u0016\u001A\u000E\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00190\u0017\"\u0004\b\u0000\u0010\u0018\"\u0004\b\u0001\u0010\u0019\u001A\u001D\u0010\u001A\u001A\u00060\u0001j\u0002`\u001B2\u0006\u0010\u001C\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\bH\u0080\b\u001A\u0011\u0010\u001E\u001A\u00020\b2\u0006\u0010\u001F\u001A\u00020\bH\u0080\b\u001A\u0011\u0010 \u001A\u00020\b2\u0006\u0010\u001F\u001A\u00020\bH\u0080\b\u001A\u0013\u0010\u001F\u001A\u00020\b2\b\u0010!\u001A\u0004\u0018\u00010\u0010H\u0080\b\u001A\u0019\u0010\"\u001A\u00020#2\u0006\u0010\u001C\u001A\u00020\r2\u0006\u0010$\u001A\u00020\bH\u0080\b\u001A\u0019\u0010%\u001A\u00020#2\u0006\u0010\u001C\u001A\u00020\r2\u0006\u0010$\u001A\u00020\bH\u0080\b\u001A\u0011\u0010&\u001A\u00020#2\u0006\u0010\'\u001A\u00020\u0001H\u0081\b\u001A\u0019\u0010&\u001A\u00020#2\u0006\u0010\u001C\u001A\u00020\r2\u0006\u0010$\u001A\u00020\bH\u0080\b\u001A\u0010\u0010(\u001A\u00020\b2\u0006\u0010)\u001A\u00020\bH\u0000\u001A\u001E\u0010*\u001A\u000E\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00190\u000F\"\u0004\b\u0000\u0010\u0018\"\u0004\b\u0001\u0010\u0019\u001AO\u0010*\u001A\u000E\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00190\u000F\"\u0004\b\u0000\u0010\u0018\"\u0004\b\u0001\u0010\u00192*\u0010+\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00190-0,\"\u000E\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00190-\u00A2\u0006\u0002\u0010.\u001A\u0010\u0010/\u001A\u00020\b2\u0006\u0010)\u001A\u00020\bH\u0000\u001A\u0010\u00100\u001A\u00020\b2\u0006\u00101\u001A\u00020\bH\u0000\u001A\u0019\u00102\u001A\u00020\u00012\u0006\u00103\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\bH\u0081\b\u001A\u0010\u00104\u001A\u00020\b2\u0006\u0010)\u001A\u00020\bH\u0000\u001A!\u00105\u001A\u0002062\u0006\u00103\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\b2\u0006\u0010\'\u001A\u00020\u0001H\u0080\b\u001A\u0011\u00107\u001A\u00020\b*\u00060\u0001j\u0002`8H\u0080\b\u001A\u0011\u00109\u001A\u00020#*\u00060\u0001j\u0002`8H\u0080\b\u001A\u0011\u0010:\u001A\u00020\b*\u00060\u0001j\u0002`;H\u0081\b\u001A\u0015\u0010<\u001A\u00060\u0001j\u0002`8*\u00060\u0001j\u0002`\u001BH\u0080\b\u001A\u0015\u0010=\u001A\u00060\u0001j\u0002`8*\u00060\u0001j\u0002`\u001BH\u0081\b\u001A\u001D\u0010>\u001A\u00060\u0001j\u0002`8*\u00060\u0001j\u0002`\u001B2\u0006\u0010?\u001A\u00020\bH\u0081\b\u001A\u0011\u0010@\u001A\u00020\u0001*\u00060\u0001j\u0002`8H\u0080\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T\u00A2\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001A\u00020\u00018\u0000X\u0081T\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0005\u001A\u00020\u00018\u0000X\u0081T\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0004\"\u000E\u0010\u0007\u001A\u00020\bX\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\bX\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\n\u001A\u00020\u0001X\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u000B\u001A\u00020\u0001X\u0080T\u00A2\u0006\u0002\n\u0000\"\u0010\u0010\f\u001A\u00020\r8\u0000X\u0081\u0004\u00A2\u0006\u0002\n\u0000\"\u001C\u0010\u000E\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00110\u000FX\u0082\u0004\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0012\u001A\u00020\bX\u0080T\u00A2\u0006\u0002\n\u0000\"\u000E\u0010\u0013\u001A\u00020\bX\u0080T\u00A2\u0006\u0002\n\u0000\"\u0016\u0010\u0014\u001A\u00020\u00018\u0000X\u0081T\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0004*\f\b\u0000\u0010A\"\u00020\u00012\u00020\u0001*\f\b\u0000\u0010B\"\u00020\u00012\u00020\u0001*\f\b\u0000\u0010C\"\u00020\u00012\u00020\u0001\u00A8\u0006D"}, d2 = {"AllEmpty", "", "BitmaskLsb", "getBitmaskLsb$annotations", "()V", "BitmaskMsb", "getBitmaskMsb$annotations", "ClonedMetadataCount", "", "DefaultScatterCapacity", "Deleted", "Empty", "EmptyGroup", "", "EmptyScatterMap", "Landroidx/collection/MutableScatterMap;", "", "", "GroupWidth", "MurmurHashC1", "Sentinel", "getSentinel$annotations", "emptyScatterMap", "Landroidx/collection/ScatterMap;", "K", "V", "group", "Landroidx/collection/Group;", "metadata", "offset", "h1", "hash", "h2", "k", "isDeleted", "", "index", "isEmpty", "isFull", "value", "loadedCapacity", "capacity", "mutableScatterMapOf", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Landroidx/collection/MutableScatterMap;", "nextCapacity", "normalizeCapacity", "n", "readRawMetadata", "data", "unloadedCapacity", "writeRawMetadata", "", "get", "Landroidx/collection/Bitmask;", "hasNext", "lowestBitSet", "Landroidx/collection/StaticBitmask;", "maskEmpty", "maskEmptyOrDeleted", "match", "m", "next", "Bitmask", "Group", "StaticBitmask", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ScatterMapKt {
    public static final long AllEmpty = 0x8080808080808080L;
    public static final long BitmaskLsb = 0x101010101010101L;
    public static final long BitmaskMsb = 0x8080808080808080L;
    public static final int ClonedMetadataCount = 7;
    public static final int DefaultScatterCapacity = 6;
    public static final long Deleted = 0xFEL;
    public static final long Empty = 0x80L;
    public static final long[] EmptyGroup = null;
    private static final MutableScatterMap EmptyScatterMap = null;
    public static final int GroupWidth = 8;
    public static final int MurmurHashC1 = 0xCC9E2D51;
    public static final long Sentinel = 0xFFL;

    static {
        ScatterMapKt.EmptyGroup = new long[]{0x80808080808080FFL, -1L};
        ScatterMapKt.EmptyScatterMap = new MutableScatterMap(0);
    }

    public static final ScatterMap emptyScatterMap() {
        Intrinsics.checkNotNull(ScatterMapKt.EmptyScatterMap, "null cannot be cast to non-null type androidx.collection.ScatterMap<K of androidx.collection.ScatterMapKt.emptyScatterMap, V of androidx.collection.ScatterMapKt.emptyScatterMap>");
        return ScatterMapKt.EmptyScatterMap;
    }

    public static final int get(long v) {
        return Long.numberOfTrailingZeros(v) >> 3;
    }

    public static void getBitmaskLsb$annotations() {
    }

    public static void getBitmaskMsb$annotations() {
    }

    public static void getSentinel$annotations() {
    }

    public static final long group(long[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "metadata");
        int v1 = (v & 7) << 3;
        return -((long)v1) >> 0x3F & arr_v[(v >> 3) + 1] << 0x40 - v1 | arr_v[v >> 3] >>> v1;
    }

    public static final int h1(int v) {
        return v >>> 7;
    }

    public static final int h2(int v) {
        return v & 0x7F;
    }

    public static final boolean hasNext(long v) {
        return v != 0L;
    }

    public static final int hash(Object object0) {
        if(object0 != null) {
            int v = object0.hashCode();
            return v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
        }
        return 0;
    }

    public static final boolean isDeleted(long[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "metadata");
        return (arr_v[v >> 3] >> ((v & 7) << 3) & 0xFFL) == 0xFEL;
    }

    public static final boolean isEmpty(long[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "metadata");
        return (arr_v[v >> 3] >> ((v & 7) << 3) & 0xFFL) == 0x80L;
    }

    public static final boolean isFull(long v) {
        return v < 0x80L;
    }

    public static final boolean isFull(long[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "metadata");
        return (arr_v[v >> 3] >> ((v & 7) << 3) & 0xFFL) < 0x80L;
    }

    public static final int loadedCapacity(int v) {
        return v == 7 ? 6 : v - v / 8;
    }

    public static final int lowestBitSet(long v) {
        return Long.numberOfTrailingZeros(v) >> 3;
    }

    public static final long maskEmpty(long v) {
        return v & ~v << 6 & 0x8080808080808080L;
    }

    public static final long maskEmptyOrDeleted(long v) {
        return v & ~v << 7 & 0x8080808080808080L;
    }

    public static final long match(long v, int v1) {
        long v2 = v ^ ((long)v1) * 0x101010101010101L;
        return ~v2 & v2 - 0x101010101010101L & 0x8080808080808080L;
    }

    public static final MutableScatterMap mutableScatterMapOf() {
        return new MutableScatterMap(0, 1, null);
    }

    public static final MutableScatterMap mutableScatterMapOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        MutableScatterMap mutableScatterMap0 = new MutableScatterMap(arr_pair.length);
        mutableScatterMap0.putAll(arr_pair);
        return mutableScatterMap0;
    }

    public static final long next(long v) {
        return v & v - 1L;
    }

    public static final int nextCapacity(int v) {
        return v == 0 ? 6 : v * 2 + 1;
    }

    public static final int normalizeCapacity(int v) {
        return v <= 0 ? 0 : -1 >>> Integer.numberOfLeadingZeros(v);
    }

    public static final long readRawMetadata(long[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "data");
        return arr_v[v >> 3] >> ((v & 7) << 3) & 0xFFL;
    }

    public static final int unloadedCapacity(int v) {
        return v == 7 ? 8 : v + (v - 1) / 7;
    }

    public static final void writeRawMetadata(long[] arr_v, int v, long v1) {
        Intrinsics.checkNotNullParameter(arr_v, "data");
        int v2 = (v & 7) << 3;
        arr_v[v >> 3] = v1 << v2 | arr_v[v >> 3] & ~(0xFFL << v2);
    }
}

