package androidx.collection;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u001A\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u0006\u0010\u0004\u001A\u00020\u0003\u001A\u0016\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b\u001A&\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b\u001A6\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\b\u001AF\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\b\u001AV\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u00020\u00062\u0006\u0010\u0010\u001A\u00020\b\u001A\u0006\u0010\u0011\u001A\u00020\u0001\u001A\u0016\u0010\u0011\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b\u001A&\u0010\u0011\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b\u001A6\u0010\u0011\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\b\u001AF\u0010\u0011\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\b\u001AV\u0010\u0011\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u00020\u00062\u0006\u0010\u0010\u001A\u00020\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u0012"}, d2 = {"EmptyLongIntMap", "Landroidx/collection/MutableLongIntMap;", "emptyLongIntMap", "Landroidx/collection/LongIntMap;", "longIntMapOf", "key1", "", "value1", "", "key2", "value2", "key3", "value3", "key4", "value4", "key5", "value5", "mutableLongIntMapOf", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LongIntMapKt {
    private static final MutableLongIntMap EmptyLongIntMap;

    static {
        LongIntMapKt.EmptyLongIntMap = new MutableLongIntMap(0);
    }

    public static final LongIntMap emptyLongIntMap() {
        return LongIntMapKt.EmptyLongIntMap;
    }

    public static final LongIntMap longIntMapOf() {
        return LongIntMapKt.EmptyLongIntMap;
    }

    public static final LongIntMap longIntMapOf(long v, int v1) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        return mutableLongIntMap0;
    }

    public static final LongIntMap longIntMapOf(long v, int v1, long v2, int v3) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        return mutableLongIntMap0;
    }

    public static final LongIntMap longIntMapOf(long v, int v1, long v2, int v3, long v4, int v5) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        mutableLongIntMap0.set(v4, v5);
        return mutableLongIntMap0;
    }

    public static final LongIntMap longIntMapOf(long v, int v1, long v2, int v3, long v4, int v5, long v6, int v7) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        mutableLongIntMap0.set(v4, v5);
        mutableLongIntMap0.set(v6, v7);
        return mutableLongIntMap0;
    }

    public static final LongIntMap longIntMapOf(long v, int v1, long v2, int v3, long v4, int v5, long v6, int v7, long v8, int v9) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        mutableLongIntMap0.set(v4, v5);
        mutableLongIntMap0.set(v6, v7);
        mutableLongIntMap0.set(v8, v9);
        return mutableLongIntMap0;
    }

    public static final MutableLongIntMap mutableLongIntMapOf() {
        return new MutableLongIntMap(0, 1, null);
    }

    public static final MutableLongIntMap mutableLongIntMapOf(long v, int v1) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        return mutableLongIntMap0;
    }

    public static final MutableLongIntMap mutableLongIntMapOf(long v, int v1, long v2, int v3) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        return mutableLongIntMap0;
    }

    public static final MutableLongIntMap mutableLongIntMapOf(long v, int v1, long v2, int v3, long v4, int v5) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        mutableLongIntMap0.set(v4, v5);
        return mutableLongIntMap0;
    }

    public static final MutableLongIntMap mutableLongIntMapOf(long v, int v1, long v2, int v3, long v4, int v5, long v6, int v7) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        mutableLongIntMap0.set(v4, v5);
        mutableLongIntMap0.set(v6, v7);
        return mutableLongIntMap0;
    }

    public static final MutableLongIntMap mutableLongIntMapOf(long v, int v1, long v2, int v3, long v4, int v5, long v6, int v7, long v8, int v9) {
        MutableLongIntMap mutableLongIntMap0 = new MutableLongIntMap(0, 1, null);
        mutableLongIntMap0.set(v, v1);
        mutableLongIntMap0.set(v2, v3);
        mutableLongIntMap0.set(v4, v5);
        mutableLongIntMap0.set(v6, v7);
        mutableLongIntMap0.set(v8, v9);
        return mutableLongIntMap0;
    }
}

