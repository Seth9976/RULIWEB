package androidx.collection;

import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap.Entry;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\'\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B-\u0012\u000E\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u000E\u0010\u0007\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u0015\u0010\u0019\u001A\u00028\u00012\u0006\u0010\u001A\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001BR\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u001A\u0010\r\u001A\u00028\u00008VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u000E\u0010\u000F\u001A\u0004\b\u0010\u0010\u0011R\u001B\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\n\n\u0002\u0010\u0014\u001A\u0004\b\u0012\u0010\u0013R\u001A\u0010\u0015\u001A\u00028\u00018VX\u0096\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\u000F\u001A\u0004\b\u0017\u0010\u0011R\u001B\u0010\u0007\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005¢\u0006\n\n\u0002\u0010\u0014\u001A\u0004\b\u0018\u0010\u0013¨\u0006\u001C"}, d2 = {"Landroidx/collection/MutableMapEntry;", "K", "V", "", "keys", "", "", "values", "index", "", "([Ljava/lang/Object;[Ljava/lang/Object;I)V", "getIndex", "()I", "key", "getKey$annotations", "()V", "getKey", "()Ljava/lang/Object;", "getKeys", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "value", "getValue$annotations", "getValue", "getValues", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class MutableMapEntry implements Map.Entry, Entry {
    private final int index;
    private final Object[] keys;
    private final Object[] values;

    public MutableMapEntry(Object[] arr_object, Object[] arr_object1, int v) {
        Intrinsics.checkNotNullParameter(arr_object, "keys");
        Intrinsics.checkNotNullParameter(arr_object1, "values");
        super();
        this.keys = arr_object;
        this.values = arr_object1;
        this.index = v;
    }

    public final int getIndex() {
        return this.index;
    }

    @Override
    public Object getKey() {
        return this.keys[this.index];
    }

    public static void getKey$annotations() {
    }

    public final Object[] getKeys() {
        return this.keys;
    }

    @Override
    public Object getValue() {
        return this.values[this.index];
    }

    public static void getValue$annotations() {
    }

    public final Object[] getValues() {
        return this.values;
    }

    @Override
    public Object setValue(Object object0) {
        Object object1 = this.values[this.index];
        this.values[this.index] = object0;
        return object1;
    }
}

