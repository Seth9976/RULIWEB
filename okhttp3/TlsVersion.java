package okhttp3;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000E\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0002\u001A\u00020\u0003H\u0007¢\u0006\u0002\b\u0006R\u0013\u0010\u0002\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0005j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000B¨\u0006\r"}, d2 = {"Lokhttp3/TlsVersion;", "", "javaName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "()Ljava/lang/String;", "-deprecated_javaName", "TLS_1_3", "TLS_1_2", "TLS_1_1", "TLS_1_0", "SSL_3_0", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public enum TlsVersion {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lokhttp3/TlsVersion$Companion;", "", "()V", "forJavaName", "Lokhttp3/TlsVersion;", "javaName", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final TlsVersion forJavaName(String s) {
            Intrinsics.checkNotNullParameter(s, "javaName");
            switch(s) {
                case "SSLv3": {
                    return TlsVersion.SSL_3_0;
                }
                case "TLSv1": {
                    return TlsVersion.TLS_1_0;
                }
                case "TLSv1.1": {
                    return TlsVersion.TLS_1_1;
                }
                case "TLSv1.2": {
                    return TlsVersion.TLS_1_2;
                }
                case "TLSv1.3": {
                    return TlsVersion.TLS_1_3;
                }
                default: {
                    throw new IllegalArgumentException("Unexpected TLS version: " + s);
                }
            }
        }
    }

    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");

    public static final Companion Companion;
    private final String javaName;

    private static final TlsVersion[] $values() [...] // Inlined contents

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "javaName", imports = {}))
    public final String -deprecated_javaName() {
        return this.javaName;
    }

    static {
        TlsVersion.Companion = new Companion(null);
    }

    private TlsVersion(String s1) {
        this.javaName = s1;
    }

    @JvmStatic
    public static final TlsVersion forJavaName(String s) {
        return TlsVersion.Companion.forJavaName(s);
    }

    public final String javaName() {
        return this.javaName;
    }
}

