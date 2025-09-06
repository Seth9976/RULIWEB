package kotlin.reflect.jvm.internal.impl.metadata.builtins;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;

public final class BuiltInsBinaryVersion extends BinaryVersion {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final BuiltInsBinaryVersion readFrom(InputStream inputStream0) {
            Intrinsics.checkNotNullParameter(inputStream0, "stream");
            DataInputStream dataInputStream0 = new DataInputStream(inputStream0);
            IntRange intRange0 = new IntRange(1, dataInputStream0.readInt());
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange0, 10));
            Iterator iterator0 = intRange0.iterator();
            while(iterator0.hasNext()) {
                ((IntIterator)iterator0).nextInt();
                arrayList0.add(dataInputStream0.readInt());
            }
            int[] arr_v = CollectionsKt.toIntArray(arrayList0);
            return new BuiltInsBinaryVersion(Arrays.copyOf(arr_v, arr_v.length));
        }
    }

    public static final Companion Companion;
    public static final BuiltInsBinaryVersion INSTANCE;
    public static final BuiltInsBinaryVersion INVALID_VERSION;

    static {
        BuiltInsBinaryVersion.Companion = new Companion(null);
        BuiltInsBinaryVersion.INSTANCE = new BuiltInsBinaryVersion(new int[]{1, 0, 7});
        BuiltInsBinaryVersion.INVALID_VERSION = new BuiltInsBinaryVersion(new int[0]);
    }

    public BuiltInsBinaryVersion(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "numbers");
        super(Arrays.copyOf(arr_v, arr_v.length));
    }

    public boolean isCompatibleWithCurrentCompilerVersion() {
        return this.isCompatibleTo(BuiltInsBinaryVersion.INSTANCE);
    }
}

