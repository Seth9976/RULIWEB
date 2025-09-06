package okhttp3.internal.http1;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers.Builder;
import okhttp3.Headers;
import okio.BufferedSource;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001A\u00020\nJ\u0006\u0010\u000B\u001A\u00020\fR\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\u000E"}, d2 = {"Lokhttp3/internal/http1/HeadersReader;", "", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "headerLimit", "", "getSource", "()Lokio/BufferedSource;", "readHeaders", "Lokhttp3/Headers;", "readLine", "", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class HeadersReader {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/http1/HeadersReader$Companion;", "", "()V", "HEADER_LIMIT", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    private static final int HEADER_LIMIT = 0x40000;
    private long headerLimit;
    private final BufferedSource source;

    static {
        HeadersReader.Companion = new Companion(null);
    }

    public HeadersReader(BufferedSource bufferedSource0) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        super();
        this.source = bufferedSource0;
        this.headerLimit = 0x40000L;
    }

    public final BufferedSource getSource() {
        return this.source;
    }

    public final Headers readHeaders() {
        Builder headers$Builder0 = new Builder();
        while(true) {
            String s = this.readLine();
            if(s.length() == 0) {
                break;
            }
            headers$Builder0.addLenient$okhttp(s);
        }
        return headers$Builder0.build();
    }

    public final String readLine() {
        String s = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= (long)s.length();
        return s;
    }
}

