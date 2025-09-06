package kotlin.reflect.jvm.internal.impl.load.kotlin.header;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;

public final class KotlinClassHeader {
    public static enum Kind {
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            @JvmStatic
            public final Kind getById(int v) {
                Kind kotlinClassHeader$Kind0 = (Kind)Kind.entryById.get(v);
                return kotlinClassHeader$Kind0 == null ? Kind.UNKNOWN : kotlinClassHeader$Kind0;
            }
        }

        UNKNOWN(0),
        CLASS(1),
        FILE_FACADE(2),
        SYNTHETIC_CLASS(3),
        MULTIFILE_CLASS(4),
        MULTIFILE_CLASS_PART(5);

        public static final Companion Companion;
        private static final Map entryById;
        private final int id;

        private static final Kind[] $values() [...] // Inlined contents

        static {
            Kind.Companion = new Companion(null);
            Kind[] arr_kotlinClassHeader$Kind = (Kind[])Kind.$VALUES.clone();
            Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(arr_kotlinClassHeader$Kind.length), 16));
            for(int v = 0; v < arr_kotlinClassHeader$Kind.length; ++v) {
                Kind kotlinClassHeader$Kind0 = arr_kotlinClassHeader$Kind[v];
                map0.put(kotlinClassHeader$Kind0.id, kotlinClassHeader$Kind0);
            }
            Kind.entryById = map0;
        }

        private Kind(int v1) {
            this.id = v1;
        }

        @JvmStatic
        public static final Kind getById(int v) {
            return Kind.Companion.getById(v);
        }
    }

    private final String[] data;
    private final int extraInt;
    private final String extraString;
    private final String[] incompatibleData;
    private final Kind kind;
    private final JvmMetadataVersion metadataVersion;
    private final String packageName;
    private final byte[] serializedIr;
    private final String[] strings;

    public KotlinClassHeader(Kind kotlinClassHeader$Kind0, JvmMetadataVersion jvmMetadataVersion0, String[] arr_s, String[] arr_s1, String[] arr_s2, String s, int v, String s1, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(kotlinClassHeader$Kind0, "kind");
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "metadataVersion");
        super();
        this.kind = kotlinClassHeader$Kind0;
        this.metadataVersion = jvmMetadataVersion0;
        this.data = arr_s;
        this.incompatibleData = arr_s1;
        this.strings = arr_s2;
        this.extraString = s;
        this.extraInt = v;
        this.packageName = s1;
        this.serializedIr = arr_b;
    }

    public final String[] getData() {
        return this.data;
    }

    public final String[] getIncompatibleData() {
        return this.incompatibleData;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final JvmMetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public final String getMultifileClassName() {
        return this.kind == Kind.MULTIFILE_CLASS_PART ? this.extraString : null;
    }

    public final List getMultifilePartNames() {
        String[] arr_s = this.data;
        List list0 = null;
        if(this.kind != Kind.MULTIFILE_CLASS) {
            arr_s = null;
        }
        if(arr_s != null) {
            list0 = ArraysKt.asList(arr_s);
        }
        return list0 == null ? CollectionsKt.emptyList() : list0;
    }

    public final String[] getStrings() {
        return this.strings;
    }

    private final boolean has(int v, int v1) {
        return (v & v1) != 0;
    }

    public final boolean isPreRelease() {
        return this.has(this.extraInt, 2);
    }

    // 去混淆评级： 低(20)
    public final boolean isUnstableFirBinary() {
        return this.has(this.extraInt, 0x40) && !this.has(this.extraInt, 0x20);
    }

    // 去混淆评级： 低(20)
    public final boolean isUnstableJvmIrBinary() {
        return this.has(this.extraInt, 16) && !this.has(this.extraInt, 0x20);
    }

    @Override
    public String toString() {
        return this.kind + " version=" + this.metadataVersion;
    }
}

