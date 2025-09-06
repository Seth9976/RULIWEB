package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;

public final class JvmMetadataVersion extends BinaryVersion {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    public static final JvmMetadataVersion INSTANCE;
    public static final JvmMetadataVersion INSTANCE_NEXT;
    public static final JvmMetadataVersion INVALID_VERSION;
    private final boolean isStrictSemantics;

    static {
        JvmMetadataVersion.Companion = new Companion(null);
        JvmMetadataVersion jvmMetadataVersion0 = new JvmMetadataVersion(new int[]{1, 8, 0});
        JvmMetadataVersion.INSTANCE = jvmMetadataVersion0;
        JvmMetadataVersion.INSTANCE_NEXT = jvmMetadataVersion0.next();
        JvmMetadataVersion.INVALID_VERSION = new JvmMetadataVersion(new int[0]);
    }

    public JvmMetadataVersion(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "numbers");
        this(arr_v, false);
    }

    public JvmMetadataVersion(int[] arr_v, boolean z) {
        Intrinsics.checkNotNullParameter(arr_v, "versionArray");
        super(Arrays.copyOf(arr_v, arr_v.length));
        this.isStrictSemantics = z;
    }

    public final boolean isCompatible(JvmMetadataVersion jvmMetadataVersion0) {
        Intrinsics.checkNotNullParameter(jvmMetadataVersion0, "metadataVersionFromLanguageVersion");
        return this.getMajor() != 2 || this.getMinor() != 0 || (JvmMetadataVersion.INSTANCE.getMajor() != 1 || JvmMetadataVersion.INSTANCE.getMinor() != 8) ? this.isCompatibleInternal(jvmMetadataVersion0.lastSupportedVersionWithThisLanguageVersion(this.isStrictSemantics)) : true;
    }

    private final boolean isCompatibleInternal(JvmMetadataVersion jvmMetadataVersion0) {
        if(this.getMajor() == 1 && this.getMinor() == 0) {
            return false;
        }
        return this.getMajor() == 0 ? false : !this.newerThan(jvmMetadataVersion0);
    }

    public final boolean isStrictSemantics() {
        return this.isStrictSemantics;
    }

    // 去混淆评级： 低(20)
    public final JvmMetadataVersion lastSupportedVersionWithThisLanguageVersion(boolean z) {
        JvmMetadataVersion jvmMetadataVersion0 = z ? JvmMetadataVersion.INSTANCE : JvmMetadataVersion.INSTANCE_NEXT;
        return jvmMetadataVersion0.newerThan(this) ? jvmMetadataVersion0 : this;
    }

    private final boolean newerThan(JvmMetadataVersion jvmMetadataVersion0) {
        if(this.getMajor() > jvmMetadataVersion0.getMajor()) {
            return true;
        }
        return this.getMajor() >= jvmMetadataVersion0.getMajor() ? this.getMinor() > jvmMetadataVersion0.getMinor() : false;
    }

    public final JvmMetadataVersion next() {
        return this.getMajor() != 1 || this.getMinor() != 9 ? new JvmMetadataVersion(new int[]{this.getMajor(), this.getMinor() + 1, 0}) : new JvmMetadataVersion(new int[]{2, 0, 0});
    }
}

