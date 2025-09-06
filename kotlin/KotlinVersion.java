package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0006\u001A\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001A\u00020\u00032\u0006\u0010\u000E\u001A\u00020\u0000H\u0096\u0002J\u0013\u0010\u000F\u001A\u00020\u00102\b\u0010\u000E\u001A\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001A\u00020\u0003H\u0016J\u0016\u0010\u0013\u001A\u00020\u00102\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0003J\u001E\u0010\u0013\u001A\u00020\u00102\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u0003J\b\u0010\u0014\u001A\u00020\u0015H\u0016J \u0010\u0016\u001A\u00020\u00032\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u0003H\u0002R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\tR\u000E\u0010\f\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lkotlin/KotlinVersion;", "", "major", "", "minor", "(II)V", "patch", "(III)V", "getMajor", "()I", "getMinor", "getPatch", "version", "compareTo", "other", "equals", "", "", "hashCode", "isAtLeast", "toString", "", "versionOf", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class KotlinVersion implements Comparable {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/KotlinVersion$Companion;", "", "()V", "CURRENT", "Lkotlin/KotlinVersion;", "MAX_COMPONENT_VALUE", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final KotlinVersion CURRENT = null;
    public static final Companion Companion = null;
    public static final int MAX_COMPONENT_VALUE = 0xFF;
    private final int major;
    private final int minor;
    private final int patch;
    private final int version;

    static {
        KotlinVersion.Companion = new Companion(null);
        KotlinVersion.CURRENT = KotlinVersionCurrentValue.get();
    }

    public KotlinVersion(int v, int v1) {
        this(v, v1, 0);
    }

    public KotlinVersion(int v, int v1, int v2) {
        this.major = v;
        this.minor = v1;
        this.patch = v2;
        this.version = this.versionOf(v, v1, v2);
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((KotlinVersion)object0));
    }

    public int compareTo(KotlinVersion kotlinVersion0) {
        Intrinsics.checkNotNullParameter(kotlinVersion0, "other");
        return this.version - kotlinVersion0.version;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        KotlinVersion kotlinVersion0 = object0 instanceof KotlinVersion ? ((KotlinVersion)object0) : null;
        return kotlinVersion0 == null ? false : this.version == kotlinVersion0.version;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    @Override
    public int hashCode() {
        return this.version;
    }

    public final boolean isAtLeast(int v, int v1) {
        return this.major > v || this.major == v && this.minor >= v1;
    }

    // 去混淆评级： 低(20)
    public final boolean isAtLeast(int v, int v1, int v2) {
        return this.major > v || this.major == v && (this.minor > v1 || this.minor == v1 && this.patch >= v2);
    }

    @Override
    public String toString() {
        return this.major + '.' + this.minor + '.' + this.patch;
    }

    private final int versionOf(int v, int v1, int v2) {
        if(!new IntRange(0, 0xFF).contains(v) || !new IntRange(0, 0xFF).contains(v1) || !new IntRange(0, 0xFF).contains(v2)) {
            throw new IllegalArgumentException(("Version components are out of range: " + v + '.' + v1 + '.' + v2).toString());
        }
        return (v << 16) + (v1 << 8) + v2;
    }
}

