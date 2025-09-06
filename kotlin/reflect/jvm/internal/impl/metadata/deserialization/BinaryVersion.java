package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public abstract class BinaryVersion {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private final int major;
    private final int minor;
    private final int[] numbers;
    private final int patch;
    private final List rest;

    static {
        BinaryVersion.Companion = new Companion(null);
    }

    public BinaryVersion(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "numbers");
        List list0;
        super();
        this.numbers = arr_v;
        Integer integer0 = ArraysKt.getOrNull(arr_v, 0);
        int v = -1;
        this.major = integer0 == null ? -1 : ((int)integer0);
        Integer integer1 = ArraysKt.getOrNull(arr_v, 1);
        this.minor = integer1 == null ? -1 : ((int)integer1);
        Integer integer2 = ArraysKt.getOrNull(arr_v, 2);
        if(integer2 != null) {
            v = (int)integer2;
        }
        this.patch = v;
        if(arr_v.length > 3) {
            if(arr_v.length > 0x400) {
                throw new IllegalArgumentException("BinaryVersion with length more than 1024 are not supported. Provided length " + arr_v.length + '.');
            }
            list0 = CollectionsKt.toList(ArraysKt.asList(arr_v).subList(3, arr_v.length));
        }
        else {
            list0 = CollectionsKt.emptyList();
        }
        this.rest = list0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 != null && Intrinsics.areEqual(this.getClass(), object0.getClass()) && this.major == ((BinaryVersion)object0).major && this.minor == ((BinaryVersion)object0).minor && this.patch == ((BinaryVersion)object0).patch && Intrinsics.areEqual(this.rest, ((BinaryVersion)object0).rest);
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    // 去混淆评级： 低(40)
    @Override
    public int hashCode() {
        return this.rest.hashCode() + 0x20 * this.patch + 0x8000 * this.major + 0x400 * this.minor;
    }

    public final boolean isAtLeast(int v, int v1, int v2) {
        int v3 = this.major;
        if(v3 > v) {
            return true;
        }
        if(v3 < v) {
            return false;
        }
        int v4 = this.minor;
        if(v4 > v1) {
            return true;
        }
        return v4 >= v1 ? this.patch >= v2 : false;
    }

    public final boolean isAtLeast(BinaryVersion binaryVersion0) {
        Intrinsics.checkNotNullParameter(binaryVersion0, "version");
        return this.isAtLeast(binaryVersion0.major, binaryVersion0.minor, binaryVersion0.patch);
    }

    public final boolean isAtMost(int v, int v1, int v2) {
        int v3 = this.major;
        if(v3 < v) {
            return true;
        }
        if(v3 > v) {
            return false;
        }
        int v4 = this.minor;
        if(v4 < v1) {
            return true;
        }
        return v4 <= v1 ? this.patch <= v2 : false;
    }

    protected final boolean isCompatibleTo(BinaryVersion binaryVersion0) {
        Intrinsics.checkNotNullParameter(binaryVersion0, "ourVersion");
        return this.major == 0 ? binaryVersion0.major == 0 && this.minor == binaryVersion0.minor : this.major == binaryVersion0.major && this.minor <= binaryVersion0.minor;
    }

    public final int[] toArray() {
        return this.numbers;
    }

    @Override
    public String toString() {
        int[] arr_v = this.toArray();
        ArrayList arrayList0 = new ArrayList();
        for(int v = 0; v < arr_v.length; ++v) {
            int v1 = arr_v[v];
            if(v1 == -1) {
                break;
            }
            arrayList0.add(v1);
        }
        return arrayList0.isEmpty() ? "unknown" : CollectionsKt.joinToString$default(arrayList0, ".", null, null, 0, null, null, 62, null);
    }
}

