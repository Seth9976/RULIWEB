package androidx.window.core;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001C2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001CB\'\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0015\u001A\u00020\u00032\u0006\u0010\u0016\u001A\u00020\u0000H\u0096\u0002J\u0013\u0010\u0017\u001A\u00020\u00182\b\u0010\u0016\u001A\u0004\u0018\u00010\u0019H\u0096\u0002J\b\u0010\u001A\u001A\u00020\u0003H\u0016J\b\u0010\u001B\u001A\u00020\u0007H\u0016R\u001B\u0010\t\u001A\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000E\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0012¨\u0006\u001D"}, d2 = {"Landroidx/window/core/Version;", "", "major", "", "minor", "patch", "description", "", "(IIILjava/lang/String;)V", "bigInteger", "Ljava/math/BigInteger;", "getBigInteger", "()Ljava/math/BigInteger;", "bigInteger$delegate", "Lkotlin/Lazy;", "getDescription", "()Ljava/lang/String;", "getMajor", "()I", "getMinor", "getPatch", "compareTo", "other", "equals", "", "", "hashCode", "toString", "Companion", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Version implements Comparable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000F\u001A\u0004\u0018\u00010\u00042\b\u0010\u0010\u001A\u0004\u0018\u00010\u000EH\u0007R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u0006R\u0011\u0010\u000B\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\u0006R\u000E\u0010\r\u001A\u00020\u000EX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/window/core/Version$Companion;", "", "()V", "CURRENT", "Landroidx/window/core/Version;", "getCURRENT", "()Landroidx/window/core/Version;", "UNKNOWN", "getUNKNOWN", "VERSION_0_1", "getVERSION_0_1", "VERSION_1_0", "getVERSION_1_0", "VERSION_PATTERN_STRING", "", "parse", "versionString", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Version getCURRENT() {
            return Version.CURRENT;
        }

        public final Version getUNKNOWN() {
            return Version.UNKNOWN;
        }

        public final Version getVERSION_0_1() {
            return Version.VERSION_0_1;
        }

        public final Version getVERSION_1_0() {
            return Version.VERSION_1_0;
        }

        @JvmStatic
        public final Version parse(String s) {
            if(s != null && !StringsKt.isBlank(s)) {
                Matcher matcher0 = Pattern.compile("(\\d+)(?:\\.(\\d+))(?:\\.(\\d+))(?:-(.+))?").matcher(s);
                if(!matcher0.matches()) {
                    return null;
                }
                String s1 = matcher0.group(1);
                Integer integer0 = s1 == null ? null : Integer.parseInt(s1);
                if(integer0 == null) {
                    return null;
                }
                int v = (int)integer0;
                String s2 = matcher0.group(2);
                Integer integer1 = s2 == null ? null : Integer.parseInt(s2);
                if(integer1 == null) {
                    return null;
                }
                int v1 = (int)integer1;
                String s3 = matcher0.group(3);
                Integer integer2 = s3 == null ? null : Integer.parseInt(s3);
                if(integer2 == null) {
                    return null;
                }
                String s4 = matcher0.group(4) == null ? "" : matcher0.group(4);
                Intrinsics.checkNotNullExpressionValue(s4, "description");
                return new Version(v, v1, ((int)integer2), s4, null);
            }
            return null;
        }
    }

    private static final Version CURRENT = null;
    public static final Companion Companion = null;
    private static final Version UNKNOWN = null;
    private static final Version VERSION_0_1 = null;
    private static final Version VERSION_1_0 = null;
    private static final String VERSION_PATTERN_STRING = "(\\d+)(?:\\.(\\d+))(?:\\.(\\d+))(?:-(.+))?";
    private final Lazy bigInteger$delegate;
    private final String description;
    private final int major;
    private final int minor;
    private final int patch;

    static {
        Version.Companion = new Companion(null);
        Version.UNKNOWN = new Version(0, 0, 0, "");
        Version.VERSION_0_1 = new Version(0, 1, 0, "");
        Version version0 = new Version(1, 0, 0, "");
        Version.VERSION_1_0 = version0;
        Version.CURRENT = version0;
    }

    private Version(int v, int v1, int v2, String s) {
        this.major = v;
        this.minor = v1;
        this.patch = v2;
        this.description = s;
        this.bigInteger$delegate = LazyKt.lazy(new Function0() {
            {
                Version.this = version0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final BigInteger invoke() {
                return BigInteger.valueOf(Version.this.getMajor()).shiftLeft(0x20).or(BigInteger.valueOf(Version.this.getMinor())).shiftLeft(0x20).or(BigInteger.valueOf(Version.this.getPatch()));
            }
        });
    }

    public Version(int v, int v1, int v2, String s, DefaultConstructorMarker defaultConstructorMarker0) {
        this(v, v1, v2, s);
    }

    public int compareTo(Version version0) {
        Intrinsics.checkNotNullParameter(version0, "other");
        return this.getBigInteger().compareTo(version0.getBigInteger());
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Version)object0));
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Version ? this.major == ((Version)object0).major && this.minor == ((Version)object0).minor && this.patch == ((Version)object0).patch : false;
    }

    private final BigInteger getBigInteger() {
        Object object0 = this.bigInteger$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(object0, "<get-bigInteger>(...)");
        return (BigInteger)object0;
    }

    public final String getDescription() {
        return this.description;
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
        return ((this.major + 0x20F) * 0x1F + this.minor) * 0x1F + this.patch;
    }

    @JvmStatic
    public static final Version parse(String s) {
        return Version.Companion.parse(s);
    }

    @Override
    public String toString() {
        return StringsKt.isBlank(this.description) ? this.major + '.' + this.minor + '.' + this.patch + "" : this.major + '.' + this.minor + '.' + this.patch + ("-" + this.description);
    }
}

