package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001A\u00020\tH\u0016J\b\u0010\u0005\u001A\u00020\u0006H\u0016J\n\u0010\n\u001A\u0004\u0018\u00010\u0004H\u0016J\u0018\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0006H\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0011\u001A\u00020\u0012H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokhttp3/internal/UnreadableResponseBody;", "Lokhttp3/ResponseBody;", "Lokio/Source;", "mediaType", "Lokhttp3/MediaType;", "contentLength", "", "(Lokhttp3/MediaType;J)V", "close", "", "contentType", "read", "sink", "Lokio/Buffer;", "byteCount", "source", "Lokio/BufferedSource;", "timeout", "Lokio/Timeout;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class UnreadableResponseBody extends ResponseBody implements AutoCloseable, Source {
    private final long contentLength;
    private final MediaType mediaType;

    public UnreadableResponseBody(MediaType mediaType0, long v) {
        this.mediaType = mediaType0;
        this.contentLength = v;
    }

    @Override  // okhttp3.ResponseBody, okio.Source
    public void close() {
    }

    @Override  // okhttp3.ResponseBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override  // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.mediaType;
    }

    @Override  // okio.Source
    public long read(Buffer buffer0, long v) {
        Intrinsics.checkNotNullParameter(buffer0, "sink");
        throw new IllegalStateException("Unreadable ResponseBody! These Response objects have bodies that are stripped:\n * Response.cacheResponse\n * Response.networkResponse\n * Response.priorResponse\n * EventSourceListener\n * WebSocketListener\n(It is safe to call contentType() and contentLength() on these response bodies.)");
    }

    @Override  // okhttp3.ResponseBody
    public BufferedSource source() {
        return Okio.buffer(this);
    }

    @Override  // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }
}

