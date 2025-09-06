package kotlinx.android.extensions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lkotlinx/android/extensions/CacheImplementation;", "", "(Ljava/lang/String;I)V", "SPARSE_ARRAY", "HASH_MAP", "NO_CACHE", "Companion", "kotlin-android-extensions-runtime"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public enum CacheImplementation {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/android/extensions/CacheImplementation$Companion;", "", "()V", "DEFAULT", "Lkotlinx/android/extensions/CacheImplementation;", "getDEFAULT", "()Lkotlinx/android/extensions/CacheImplementation;", "kotlin-android-extensions-runtime"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final CacheImplementation getDEFAULT() {
            return CacheImplementation.DEFAULT;
        }
    }

    SPARSE_ARRAY,
    HASH_MAP,
    NO_CACHE;

    private static final EnumEntries $ENTRIES;
    private static final CacheImplementation[] $VALUES;
    public static final Companion Companion;
    private static final CacheImplementation DEFAULT;

    private static final CacheImplementation[] $values() [...] // Inlined contents

    static {
        CacheImplementation.$VALUES = arr_cacheImplementation;
        CacheImplementation.Companion = new Companion(null);
        CacheImplementation.DEFAULT = CacheImplementation.HASH_MAP;
        Intrinsics.checkNotNullParameter(arr_cacheImplementation, "entries");
        CacheImplementation.$ENTRIES = new EnumEntriesList(arr_cacheImplementation);
    }

    public static EnumEntries getEntries() {
        return CacheImplementation.$ENTRIES;
    }
}

