package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http1.HeadersReader;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001C2\u00020\u0001:\u0003\u001C\u001D\u001EB\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0015\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0015\u001A\u00020\u0016H\u0016J\u0010\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u0018H\u0002J\b\u0010\u001A\u001A\u0004\u0018\u00010\u001BR\u0013\u0010\u0007\u001A\u00020\b8\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\nR\u000E\u0010\u000B\u001A\u00020\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000F\u001A\b\u0018\u00010\u0010R\u00020\u0000X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u000EX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0014X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Lokhttp3/MultipartReader;", "Ljava/io/Closeable;", "response", "Lokhttp3/ResponseBody;", "(Lokhttp3/ResponseBody;)V", "source", "Lokio/BufferedSource;", "boundary", "", "(Lokio/BufferedSource;Ljava/lang/String;)V", "()Ljava/lang/String;", "closed", "", "crlfDashDashBoundary", "Lokio/ByteString;", "currentPart", "Lokhttp3/MultipartReader$PartSource;", "dashDashBoundary", "noMoreParts", "partCount", "", "close", "", "currentPartBytesRemaining", "", "maxResult", "nextPart", "Lokhttp3/MultipartReader$Part;", "Companion", "Part", "PartSource", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class MultipartReader implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/MultipartReader$Companion;", "", "()V", "afterBoundaryOptions", "Lokio/Options;", "getAfterBoundaryOptions", "()Lokio/Options;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Options getAfterBoundaryOptions() {
            return MultipartReader.afterBoundaryOptions;
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001A\u00020\nH\u0096\u0001R\u0013\u0010\u0004\u001A\u00020\u00058\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0007R\u0013\u0010\u0002\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\b¨\u0006\u000B"}, d2 = {"Lokhttp3/MultipartReader$Part;", "Ljava/io/Closeable;", "headers", "Lokhttp3/Headers;", "body", "Lokio/BufferedSource;", "(Lokhttp3/Headers;Lokio/BufferedSource;)V", "()Lokio/BufferedSource;", "()Lokhttp3/Headers;", "close", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Part implements Closeable, AutoCloseable {
        private final BufferedSource body;
        private final Headers headers;

        public Part(Headers headers0, BufferedSource bufferedSource0) {
            Intrinsics.checkNotNullParameter(headers0, "headers");
            Intrinsics.checkNotNullParameter(bufferedSource0, "body");
            super();
            this.headers = headers0;
            this.body = bufferedSource0;
        }

        public final BufferedSource body() {
            return this.body;
        }

        @Override
        public void close() {
            this.body.close();
        }

        public final Headers headers() {
            return this.headers;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\bH\u0016J\b\u0010\u0003\u001A\u00020\u0004H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/MultipartReader$PartSource;", "Lokio/Source;", "(Lokhttp3/MultipartReader;)V", "timeout", "Lokio/Timeout;", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class PartSource implements AutoCloseable, Source {
        private final Timeout timeout;

        public PartSource() {
            this.timeout = new Timeout();
        }

        @Override  // okio.Source
        public void close() {
            if(Intrinsics.areEqual(MultipartReader.this.currentPart, this)) {
                MultipartReader.this.currentPart = null;
            }
        }

        @Override  // okio.Source
        public long read(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(v < 0L) {
                throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
            }
            if(Intrinsics.areEqual(MultipartReader.this.currentPart, this)) {
                Timeout timeout0 = MultipartReader.this.source.timeout();
                Timeout timeout1 = this.timeout;
                MultipartReader multipartReader0 = MultipartReader.this;
                long v1 = timeout0.timeoutNanos();
                long v2 = timeout1.timeoutNanos();
                long v3 = timeout0.timeoutNanos();
                timeout0.timeout(Timeout.Companion.minTimeout(v2, v3), TimeUnit.NANOSECONDS);
                if(timeout0.hasDeadline()) {
                    long v4 = timeout0.deadlineNanoTime();
                    if(timeout1.hasDeadline()) {
                        timeout0.deadlineNanoTime(Math.min(timeout0.deadlineNanoTime(), timeout1.deadlineNanoTime()));
                    }
                    try {
                        long v6 = multipartReader0.currentPartBytesRemaining(v);
                        return v6 == 0L ? -1L : multipartReader0.source.read(buffer0, v6);
                    }
                    finally {
                        timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                        if(timeout1.hasDeadline()) {
                            timeout0.deadlineNanoTime(v4);
                        }
                    }
                }
                if(timeout1.hasDeadline()) {
                    timeout0.deadlineNanoTime(timeout1.deadlineNanoTime());
                }
                try {
                    long v9 = multipartReader0.currentPartBytesRemaining(v);
                    return v9 == 0L ? -1L : multipartReader0.source.read(buffer0, v9);
                }
                finally {
                    timeout0.timeout(v1, TimeUnit.NANOSECONDS);
                    if(timeout1.hasDeadline()) {
                        timeout0.clearDeadline();
                    }
                }
            }
            throw new IllegalStateException("closed");
        }

        @Override  // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    public static final Companion Companion;
    private static final Options afterBoundaryOptions;
    private final String boundary;
    private boolean closed;
    private final ByteString crlfDashDashBoundary;
    private PartSource currentPart;
    private final ByteString dashDashBoundary;
    private boolean noMoreParts;
    private int partCount;
    private final BufferedSource source;

    static {
        MultipartReader.Companion = new Companion(null);
        ByteString[] arr_byteString = {ByteString.Companion.encodeUtf8("\r\n"), ByteString.Companion.encodeUtf8("--"), ByteString.Companion.encodeUtf8(" "), ByteString.Companion.encodeUtf8("\t")};
        MultipartReader.afterBoundaryOptions = Options.Companion.of(arr_byteString);
    }

    public MultipartReader(ResponseBody responseBody0) throws IOException {
        Intrinsics.checkNotNullParameter(responseBody0, "response");
        BufferedSource bufferedSource0 = responseBody0.source();
        MediaType mediaType0 = responseBody0.contentType();
        if(mediaType0 != null) {
            String s = mediaType0.parameter("boundary");
            if(s != null) {
                this(bufferedSource0, s);
                return;
            }
        }
        throw new ProtocolException("expected the Content-Type to have a boundary parameter");
    }

    public MultipartReader(BufferedSource bufferedSource0, String s) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        Intrinsics.checkNotNullParameter(s, "boundary");
        super();
        this.source = bufferedSource0;
        this.boundary = s;
        this.dashDashBoundary = new Buffer().writeUtf8("--").writeUtf8(s).readByteString();
        this.crlfDashDashBoundary = new Buffer().writeUtf8("\r\n--").writeUtf8(s).readByteString();
    }

    public final String boundary() {
        return this.boundary;
    }

    @Override
    public void close() throws IOException {
        if(this.closed) {
            return;
        }
        this.closed = true;
        this.currentPart = null;
        this.source.close();
    }

    private final long currentPartBytesRemaining(long v) {
        this.source.require(((long)this.crlfDashDashBoundary.size()));
        long v1 = this.source.getBuffer().indexOf(this.crlfDashDashBoundary);
        return v1 == -1L ? Math.min(v, this.source.getBuffer().size() - ((long)this.crlfDashDashBoundary.size()) + 1L) : Math.min(v, v1);
    }

    public final Part nextPart() throws IOException {
        if(this.closed) {
            throw new IllegalStateException("closed");
        }
        if(this.noMoreParts) {
            return null;
        }
        if(this.partCount != 0 || !this.source.rangeEquals(0L, this.dashDashBoundary)) {
            long v;
            while((v = this.currentPartBytesRemaining(0x2000L)) != 0L) {
                this.source.skip(v);
            }
            this.source.skip(((long)this.crlfDashDashBoundary.size()));
        }
        else {
            this.source.skip(((long)this.dashDashBoundary.size()));
        }
        boolean z = false;
        int v1;
        while((v1 = this.source.select(MultipartReader.afterBoundaryOptions)) != -1) {
            switch(v1) {
                case 0: {
                    ++this.partCount;
                    Headers headers0 = new HeadersReader(this.source).readHeaders();
                    PartSource multipartReader$PartSource0 = new PartSource(this);
                    this.currentPart = multipartReader$PartSource0;
                    return new Part(headers0, Okio.buffer(multipartReader$PartSource0));
                }
                case 1: {
                    if(z) {
                        throw new ProtocolException("unexpected characters after boundary");
                    }
                    if(this.partCount == 0) {
                        throw new ProtocolException("expected at least 1 part");
                    }
                    this.noMoreParts = true;
                    return null;
                label_27:
                    if(v1 == 2 || v1 == 3) {
                        break;
                    }
                    continue;
                }
                default: {
                    goto label_27;
                }
            }
            z = true;
        }
        throw new ProtocolException("unexpected characters after boundary");
    }
}

