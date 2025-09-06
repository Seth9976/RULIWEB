package okhttp3;

import java.io.IOException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u000B\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001A\u00020\u0003H\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000Bj\u0002\b\f¨\u0006\u000E"}, d2 = {"Lokhttp3/Protocol;", "", "protocol", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "HTTP_1_0", "HTTP_1_1", "SPDY_3", "HTTP_2", "H2_PRIOR_KNOWLEDGE", "QUIC", "HTTP_3", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public enum Protocol {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lokhttp3/Protocol$Companion;", "", "()V", "get", "Lokhttp3/Protocol;", "protocol", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final Protocol get(String s) throws IOException {
            Intrinsics.checkNotNullParameter(s, "protocol");
            if(Intrinsics.areEqual(s, "http/1.0")) {
                return Protocol.HTTP_1_0;
            }
            if(Intrinsics.areEqual(s, "http/1.1")) {
                return Protocol.HTTP_1_1;
            }
            if(Intrinsics.areEqual(s, "h2_prior_knowledge")) {
                return Protocol.H2_PRIOR_KNOWLEDGE;
            }
            if(Intrinsics.areEqual(s, "h2")) {
                return Protocol.HTTP_2;
            }
            if(Intrinsics.areEqual(s, "spdy/3.1")) {
                return Protocol.SPDY_3;
            }
            if(Intrinsics.areEqual(s, "quic")) {
                return Protocol.QUIC;
            }
            if(!StringsKt.startsWith$default(s, "h3", false, 2, null)) {
                throw new IOException("Unexpected protocol: " + s);
            }
            return Protocol.HTTP_3;
        }
    }

    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    @Deprecated(message = "OkHttp has dropped support for SPDY. Prefer {@link #HTTP_2}.")
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic"),
    HTTP_3("h3");

    public static final Companion Companion;
    private final String protocol;

    private static final Protocol[] $values() [...] // Inlined contents

    static {
        Protocol.Companion = new Companion(null);
    }

    private Protocol(String s1) {
        this.protocol = s1;
    }

    public static final String access$getProtocol$p(Protocol protocol0) [...] // 潜在的解密器

    @JvmStatic
    public static final Protocol get(String s) throws IOException {
        return Protocol.Companion.get(s);
    }

    @Override
    public String toString() {
        return this.protocol;
    }
}

