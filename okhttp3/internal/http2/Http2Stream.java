package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import jeb.synthetic.FIN;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okhttp3.internal._UtilJvmKt;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000\u008A\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 a2\u00020\u0001:\u0004abcdB1\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\u0007\u0012\b\u0010\t\u001A\u0004\u0018\u00010\n\u00A2\u0006\u0002\u0010\u000BJ\u000E\u0010@\u001A\u00020A2\u0006\u0010B\u001A\u00020#J\r\u0010C\u001A\u00020AH\u0000\u00A2\u0006\u0002\bDJ\r\u0010E\u001A\u00020AH\u0000\u00A2\u0006\u0002\bFJ\u0018\u0010G\u001A\u00020A2\u0006\u0010H\u001A\u00020\u000F2\b\u0010\u0014\u001A\u0004\u0018\u00010\u0015J\u001A\u0010I\u001A\u00020\u00072\u0006\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0014\u001A\u0004\u0018\u00010\u0015H\u0002J\u000E\u0010J\u001A\u00020A2\u0006\u0010\u000E\u001A\u00020\u000FJ\b\u0010K\u001A\u00020\u0007H\u0002J\u000E\u0010L\u001A\u00020A2\u0006\u0010M\u001A\u00020\nJ\u0006\u0010N\u001A\u00020OJ\u0006\u0010P\u001A\u00020QJ\u0006\u0010,\u001A\u00020RJ\u0016\u0010S\u001A\u00020A2\u0006\u00104\u001A\u00020T2\u0006\u0010U\u001A\u00020\u0003J\u0016\u0010V\u001A\u00020A2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\b\u001A\u00020\u0007J\u000E\u0010W\u001A\u00020A2\u0006\u0010\u000E\u001A\u00020\u000FJ\u0010\u0010X\u001A\u00020\n2\b\b\u0002\u0010Y\u001A\u00020\u0007J\u0006\u0010M\u001A\u00020\nJ\r\u0010Z\u001A\u00020AH\u0000\u00A2\u0006\u0002\b[J$\u0010\\\u001A\u00020A2\f\u0010]\u001A\b\u0012\u0004\u0012\u00020_0^2\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010`\u001A\u00020\u0007J\u0006\u0010>\u001A\u00020RR\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u001E\u0010\u000E\u001A\u0004\u0018\u00010\u000F8@X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001C\u0010\u0014\u001A\u0004\u0018\u00010\u0015X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000E\u0010\u001A\u001A\u00020\u0007X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001B\u001A\b\u0012\u0004\u0012\u00020\n0\u001CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u001ER\u0011\u0010\u001F\u001A\u00020\u00078F\u00A2\u0006\u0006\u001A\u0004\b\u001F\u0010 R\u0011\u0010!\u001A\u00020\u00078F\u00A2\u0006\u0006\u001A\u0004\b!\u0010 R$\u0010$\u001A\u00020#2\u0006\u0010\"\u001A\u00020#@@X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b%\u0010&\"\u0004\b\'\u0010(R$\u0010)\u001A\u00020#2\u0006\u0010\"\u001A\u00020#@@X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b*\u0010&\"\u0004\b+\u0010(R\u0018\u0010,\u001A\u00060-R\u00020\u0000X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b.\u0010/R\u0018\u00100\u001A\u000601R\u00020\u0000X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b2\u00103R\u0018\u00104\u001A\u000605R\u00020\u0000X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b6\u00107R$\u00108\u001A\u00020#2\u0006\u0010\"\u001A\u00020#@@X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b9\u0010&\"\u0004\b:\u0010(R$\u0010;\u001A\u00020#2\u0006\u0010\"\u001A\u00020#@@X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b<\u0010&\"\u0004\b=\u0010(R\u0018\u0010>\u001A\u00060-R\u00020\u0000X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b?\u0010/\u00A8\u0006e"}, d2 = {"Lokhttp3/internal/http2/Http2Stream;", "", "id", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "outFinished", "", "inFinished", "headers", "Lokhttp3/Headers;", "(ILokhttp3/internal/http2/Http2Connection;ZZLokhttp3/Headers;)V", "getConnection", "()Lokhttp3/internal/http2/Http2Connection;", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "getErrorCode$okhttp", "()Lokhttp3/internal/http2/ErrorCode;", "setErrorCode$okhttp", "(Lokhttp3/internal/http2/ErrorCode;)V", "errorException", "Ljava/io/IOException;", "getErrorException$okhttp", "()Ljava/io/IOException;", "setErrorException$okhttp", "(Ljava/io/IOException;)V", "hasResponseHeaders", "headersQueue", "Ljava/util/ArrayDeque;", "getId", "()I", "isLocallyInitiated", "()Z", "isOpen", "<set-?>", "", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "setReadBytesAcknowledged$okhttp", "(J)V", "readBytesTotal", "getReadBytesTotal", "setReadBytesTotal$okhttp", "readTimeout", "Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "getReadTimeout$okhttp", "()Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "sink", "Lokhttp3/internal/http2/Http2Stream$FramingSink;", "getSink$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSink;", "source", "Lokhttp3/internal/http2/Http2Stream$FramingSource;", "getSource$okhttp", "()Lokhttp3/internal/http2/Http2Stream$FramingSource;", "writeBytesMaximum", "getWriteBytesMaximum", "setWriteBytesMaximum$okhttp", "writeBytesTotal", "getWriteBytesTotal", "setWriteBytesTotal$okhttp", "writeTimeout", "getWriteTimeout$okhttp", "addBytesToWriteWindow", "", "delta", "cancelStreamIfNecessary", "cancelStreamIfNecessary$okhttp", "checkOutNotClosed", "checkOutNotClosed$okhttp", "close", "rstStatusCode", "closeInternal", "closeLater", "doReadTimeout", "enqueueTrailers", "trailers", "getSink", "Lokio/Sink;", "getSource", "Lokio/Source;", "Lokio/Timeout;", "receiveData", "Lokio/BufferedSource;", "length", "receiveHeaders", "receiveRstStream", "takeHeaders", "callerIsIdle", "waitForIo", "waitForIo$okhttp", "writeHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "flushHeaders", "Companion", "FramingSink", "FramingSource", "StreamTimeout", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http2Stream {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$Companion;", "", "()V", "EMIT_BUFFER_SIZE", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0080\u0004\u0018\u00002\u00020\u0001B\u000F\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\u0010\u0010\u0016\u001A\u00020\u00152\u0006\u0010\u0017\u001A\u00020\u0003H\u0002J\b\u0010\u0018\u001A\u00020\u0015H\u0016J\b\u0010\u0019\u001A\u00020\u001AH\u0016J\u0018\u0010\u001B\u001A\u00020\u00152\u0006\u0010\u001C\u001A\u00020\r2\u0006\u0010\u001D\u001A\u00020\u001EH\u0016R\u001A\u0010\u0005\u001A\u00020\u0003X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001A\u0010\u0002\u001A\u00020\u0003X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\n\u0010\u0007\"\u0004\b\u000B\u0010\tR\u000E\u0010\f\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\u000E\u001A\u0004\u0018\u00010\u000FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001F"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSink;", "Lokio/Sink;", "finished", "", "(Lokhttp3/internal/http2/Http2Stream;Z)V", "closed", "getClosed", "()Z", "setClosed", "(Z)V", "getFinished", "setFinished", "sendBuffer", "Lokio/Buffer;", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "close", "", "emitFrame", "outFinishedOnLastFrame", "flush", "timeout", "Lokio/Timeout;", "write", "source", "byteCount", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class FramingSink implements AutoCloseable, Sink {
        private boolean closed;
        private boolean finished;
        private final Buffer sendBuffer;
        private Headers trailers;

        public FramingSink(boolean z) {
            this.finished = z;
            this.sendBuffer = new Buffer();
        }

        public FramingSink(boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v & 1) != 0) {
                z = false;
            }
            this(z);
        }

        @Override  // okio.Sink
        public void close() throws IOException {
            boolean z;
            Http2Stream http2Stream0 = Http2Stream.this;
            if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(http2Stream0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13465 MUST NOT hold lock on " + http2Stream0);
            }
            Http2Stream http2Stream1 = Http2Stream.this;
            synchronized(http2Stream1) {
                if(this.closed) {
                    return;
                }
                z = http2Stream1.getErrorCode$okhttp() == null;
            }
            if(!Http2Stream.this.getSink$okhttp().finished) {
                boolean z1 = this.sendBuffer.size() > 0L;
                if(this.trailers != null) {
                    while(this.sendBuffer.size() > 0L) {
                        this.emitFrame(false);
                    }
                    Headers headers0 = this.trailers;
                    Intrinsics.checkNotNull(headers0);
                    List list0 = _UtilJvmKt.toHeaderList(headers0);
                    Http2Stream.this.getConnection().writeHeaders$okhttp(Http2Stream.this.getId(), z, list0);
                }
                else if(z1) {
                    while(this.sendBuffer.size() > 0L) {
                        this.emitFrame(true);
                    }
                }
                else if(z) {
                    Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), true, null, 0L);
                }
            }
            synchronized(Http2Stream.this) {
                this.closed = true;
                Intrinsics.checkNotNull(Http2Stream.this, "null cannot be cast to non-null type java.lang.Object");
                Http2Stream.this.notifyAll();
            }
            Http2Stream.this.getConnection().flush();
            Http2Stream.this.cancelStreamIfNecessary$okhttp();
        }

        private final void emitFrame(boolean z) throws IOException {
            long v2;
            Http2Stream http2Stream0 = Http2Stream.this;
            synchronized(http2Stream0) {
                http2Stream0.getWriteTimeout$okhttp().enter();
                try {
                    while(http2Stream0.getWriteBytesTotal() >= http2Stream0.getWriteBytesMaximum() && !this.finished && !this.closed && http2Stream0.getErrorCode$okhttp() == null) {
                        http2Stream0.waitForIo$okhttp();
                    }
                }
                finally {
                    http2Stream0.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
                }
                http2Stream0.checkOutNotClosed$okhttp();
                v2 = Math.min(http2Stream0.getWriteBytesMaximum() - http2Stream0.getWriteBytesTotal(), this.sendBuffer.size());
                http2Stream0.setWriteBytesTotal$okhttp(http2Stream0.getWriteBytesTotal() + v2);
            }
            Http2Stream.this.getWriteTimeout$okhttp().enter();
            try {
                Http2Stream.this.getConnection().writeData(Http2Stream.this.getId(), z && v2 == this.sendBuffer.size(), this.sendBuffer, v2);
            }
            finally {
                Http2Stream.this.getWriteTimeout$okhttp().exitAndThrowIfTimedOut();
            }
        }

        @Override  // okio.Sink
        public void flush() throws IOException {
            Http2Stream http2Stream0 = Http2Stream.this;
            if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(http2Stream0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13538 MUST NOT hold lock on " + http2Stream0);
            }
            synchronized(Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed$okhttp();
            }
            while(this.sendBuffer.size() > 0L) {
                this.emitFrame(false);
                Http2Stream.this.getConnection().flush();
            }
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final boolean getFinished() {
            return this.finished;
        }

        public final Headers getTrailers() {
            return this.trailers;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public final void setFinished(boolean z) {
            this.finished = z;
        }

        public final void setTrailers(Headers headers0) {
            this.trailers = headers0;
        }

        @Override  // okio.Sink
        public Timeout timeout() {
            return Http2Stream.this.getWriteTimeout$okhttp();
        }

        @Override  // okio.Sink
        public void write(Buffer buffer0, long v) throws IOException {
            Intrinsics.checkNotNullParameter(buffer0, "source");
            Http2Stream http2Stream0 = Http2Stream.this;
            if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(http2Stream0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13448 MUST NOT hold lock on " + http2Stream0);
            }
            this.sendBuffer.write(buffer0, v);
            while(this.sendBuffer.size() >= 0x4000L) {
                this.emitFrame(false);
            }
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001A\u001A\u00020\u001BH\u0016J\u0018\u0010\u001C\u001A\u00020\u00032\u0006\u0010\u001D\u001A\u00020\u000F2\u0006\u0010\u001E\u001A\u00020\u0003H\u0016J\u001D\u0010\u001F\u001A\u00020\u001B2\u0006\u0010 \u001A\u00020!2\u0006\u0010\u001E\u001A\u00020\u0003H\u0000¢\u0006\u0002\b\"J\b\u0010#\u001A\u00020$H\u0016J\u0010\u0010%\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0003H\u0002R\u001A\u0010\u0007\u001A\u00020\u0005X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000BR\u001A\u0010\u0004\u001A\u00020\u0005X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000BR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000E\u001A\u00020\u000F¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001A\u00020\u000F¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0011R\u001C\u0010\u0014\u001A\u0004\u0018\u00010\u0015X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006&"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$FramingSource;", "Lokio/Source;", "maxByteCount", "", "finished", "", "(Lokhttp3/internal/http2/Http2Stream;JZ)V", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getFinished$okhttp", "setFinished$okhttp", "readBuffer", "Lokio/Buffer;", "getReadBuffer", "()Lokio/Buffer;", "receiveBuffer", "getReceiveBuffer", "trailers", "Lokhttp3/Headers;", "getTrailers", "()Lokhttp3/Headers;", "setTrailers", "(Lokhttp3/Headers;)V", "close", "", "read", "sink", "byteCount", "receive", "source", "Lokio/BufferedSource;", "receive$okhttp", "timeout", "Lokio/Timeout;", "updateConnectionFlowControl", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class FramingSource implements AutoCloseable, Source {
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;
        private Headers trailers;

        public FramingSource(long v, boolean z) {
            this.maxByteCount = v;
            this.finished = z;
            this.receiveBuffer = new Buffer();
            this.readBuffer = new Buffer();
        }

        @Override  // okio.Source
        public void close() throws IOException {
            long v1;
            synchronized(Http2Stream.this) {
                this.closed = true;
                v1 = this.readBuffer.size();
                this.readBuffer.clear();
                Intrinsics.checkNotNull(Http2Stream.this, "null cannot be cast to non-null type java.lang.Object");
                Http2Stream.this.notifyAll();
            }
            if(v1 > 0L) {
                this.updateConnectionFlowControl(v1);
            }
            Http2Stream.this.cancelStreamIfNecessary$okhttp();
        }

        public final boolean getClosed$okhttp() {
            return this.closed;
        }

        public final boolean getFinished$okhttp() {
            return this.finished;
        }

        public final Buffer getReadBuffer() {
            return this.readBuffer;
        }

        public final Buffer getReceiveBuffer() {
            return this.receiveBuffer;
        }

        public final Headers getTrailers() {
            return this.trailers;
        }

        @Override  // okio.Source
        public long read(Buffer buffer0, long v) throws IOException {
            long v3;
            IOException iOException1;
            int v2;
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(v < 0L) {
                throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
            }
            while(true) {
                Http2Stream http2Stream0 = Http2Stream.this;
                synchronized(http2Stream0) {
                    boolean z = http2Stream0.doReadTimeout();
                    if(z) {
                        http2Stream0.getReadTimeout$okhttp().enter();
                    }
                    v2 = FIN.finallyOpen$NT();
                    if(http2Stream0.getErrorCode$okhttp() == null || this.finished) {
                        iOException1 = null;
                    }
                    else {
                        IOException iOException0 = http2Stream0.getErrorException$okhttp();
                        if(iOException0 == null) {
                            ErrorCode errorCode0 = http2Stream0.getErrorCode$okhttp();
                            Intrinsics.checkNotNull(errorCode0);
                            iOException1 = new StreamResetException(errorCode0);
                        }
                        else {
                            iOException1 = iOException0;
                        }
                    }
                    boolean z1 = false;
                    if(this.closed) {
                        break;
                    }
                    if(this.readBuffer.size() > 0L) {
                        v3 = this.readBuffer.read(buffer0, Math.min(v, this.readBuffer.size()));
                        http2Stream0.setReadBytesTotal$okhttp(http2Stream0.getReadBytesTotal() + v3);
                        long v4 = http2Stream0.getReadBytesTotal() - http2Stream0.getReadBytesAcknowledged();
                        if(iOException1 == null && v4 >= ((long)(http2Stream0.getConnection().getOkHttpSettings().getInitialWindowSize() / 2))) {
                            http2Stream0.getConnection().writeWindowUpdateLater$okhttp(http2Stream0.getId(), v4);
                            http2Stream0.setReadBytesAcknowledged$okhttp(http2Stream0.getReadBytesTotal());
                        }
                    }
                    else {
                        if(!this.finished && iOException1 == null) {
                            http2Stream0.waitForIo$okhttp();
                            z1 = true;
                        }
                        v3 = -1L;
                    }
                    FIN.finallyCodeBegin$NT(v2);
                    if(z) {
                        http2Stream0.getReadTimeout$okhttp().exitAndThrowIfTimedOut();
                    }
                    FIN.finallyCodeEnd$NT(v2);
                }
                if(!z1) {
                    if(v3 != -1L) {
                        this.updateConnectionFlowControl(v3);
                        return v3;
                    }
                    if(iOException1 != null) {
                        throw iOException1;
                    }
                    return -1L;
                }
            }
            FIN.finallyExec$NT(v2);
            throw new IOException("stream closed");
        }

        public final void receive$okhttp(BufferedSource bufferedSource0, long v) throws IOException {
            boolean z1;
            long v4;
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            Http2Stream http2Stream0 = Http2Stream.this;
            if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(http2Stream0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13543 MUST NOT hold lock on " + http2Stream0);
            }
            while(v > 0L) {
                synchronized(Http2Stream.this) {
                    boolean z = this.finished;
                    z1 = true;
                }
                if(this.readBuffer.size() + v > this.maxByteCount) {
                    bufferedSource0.skip(v);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                }
                if(z) {
                    bufferedSource0.skip(v);
                    return;
                }
                long v2 = bufferedSource0.read(this.receiveBuffer, v);
                if(v2 == -1L) {
                    throw new EOFException();
                }
                v -= v2;
                Http2Stream http2Stream1 = Http2Stream.this;
                synchronized(http2Stream1) {
                    if(this.closed) {
                        v4 = this.receiveBuffer.size();
                        this.receiveBuffer.clear();
                    }
                    else {
                        if(this.readBuffer.size() != 0L) {
                            z1 = false;
                        }
                        this.readBuffer.writeAll(this.receiveBuffer);
                        if(z1) {
                            Intrinsics.checkNotNull(http2Stream1, "null cannot be cast to non-null type java.lang.Object");
                            http2Stream1.notifyAll();
                        }
                        v4 = 0L;
                    }
                }
                if(v4 > 0L) {
                    this.updateConnectionFlowControl(v4);
                }
            }
        }

        public final void setClosed$okhttp(boolean z) {
            this.closed = z;
        }

        public final void setFinished$okhttp(boolean z) {
            this.finished = z;
        }

        public final void setTrailers(Headers headers0) {
            this.trailers = headers0;
        }

        @Override  // okio.Source
        public Timeout timeout() {
            return Http2Stream.this.getReadTimeout$okhttp();
        }

        private final void updateConnectionFlowControl(long v) {
            Http2Stream http2Stream0 = Http2Stream.this;
            if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(http2Stream0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13480 MUST NOT hold lock on " + http2Stream0);
            }
            Http2Stream.this.getConnection().updateConnectionFlowControl$okhttp(v);
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001A\u00020\u0004J\u0012\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\u0006H\u0014J\b\u0010\b\u001A\u00020\u0004H\u0014¨\u0006\t"}, d2 = {"Lokhttp3/internal/http2/Http2Stream$StreamTimeout;", "Lokio/AsyncTimeout;", "(Lokhttp3/internal/http2/Http2Stream;)V", "exitAndThrowIfTimedOut", "", "newTimeoutException", "Ljava/io/IOException;", "cause", "timedOut", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class StreamTimeout extends AsyncTimeout {
        public final void exitAndThrowIfTimedOut() throws IOException {
            if(this.exit()) {
                throw this.newTimeoutException(null);
            }
        }

        @Override  // okio.AsyncTimeout
        protected IOException newTimeoutException(IOException iOException0) {
            SocketTimeoutException socketTimeoutException0 = new SocketTimeoutException("timeout");
            if(iOException0 != null) {
                socketTimeoutException0.initCause(iOException0);
            }
            return socketTimeoutException0;
        }

        @Override  // okio.AsyncTimeout
        protected void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
            Http2Stream.this.getConnection().sendDegradedPingLater$okhttp();
        }
    }

    public static final Companion Companion = null;
    public static final long EMIT_BUFFER_SIZE = 0x4000L;
    private final Http2Connection connection;
    private ErrorCode errorCode;
    private IOException errorException;
    private boolean hasResponseHeaders;
    private final ArrayDeque headersQueue;
    private final int id;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final StreamTimeout readTimeout;
    private final FramingSink sink;
    private final FramingSource source;
    private long writeBytesMaximum;
    private long writeBytesTotal;
    private final StreamTimeout writeTimeout;

    static {
        Http2Stream.Companion = new Companion(null);
    }

    public Http2Stream(int v, Http2Connection http2Connection0, boolean z, boolean z1, Headers headers0) {
        Intrinsics.checkNotNullParameter(http2Connection0, "connection");
        super();
        this.id = v;
        this.connection = http2Connection0;
        this.writeBytesMaximum = (long)http2Connection0.getPeerSettings().getInitialWindowSize();
        ArrayDeque arrayDeque0 = new ArrayDeque();
        this.headersQueue = arrayDeque0;
        this.source = new FramingSource(this, ((long)http2Connection0.getOkHttpSettings().getInitialWindowSize()), z1);
        this.sink = new FramingSink(this, z);
        this.readTimeout = new StreamTimeout(this);
        this.writeTimeout = new StreamTimeout(this);
        if(headers0 != null) {
            if(this.isLocallyInitiated()) {
                throw new IllegalStateException("locally-initiated streams shouldn\'t have headers yet");
            }
            arrayDeque0.add(headers0);
            return;
        }
        if(!this.isLocallyInitiated()) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    public final void addBytesToWriteWindow(long v) {
        this.writeBytesMaximum += v;
        if(v > 0L) {
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
            this.notifyAll();
        }
    }

    public final void cancelStreamIfNecessary$okhttp() throws IOException {
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13435 MUST NOT hold lock on " + this);
        }
        synchronized(this) {
            boolean z = this.isOpen();
        }
        if(!this.source.getFinished$okhttp() && this.source.getClosed$okhttp() && (this.sink.getFinished() || this.sink.getClosed())) {
            this.close(ErrorCode.CANCEL, null);
            return;
        }
        if(!z) {
            this.connection.removeStream$okhttp(this.id);
        }
    }

    public final void checkOutNotClosed$okhttp() throws IOException {
        if(this.sink.getClosed()) {
            throw new IOException("stream closed");
        }
        if(this.sink.getFinished()) {
            throw new IOException("stream finished");
        }
        if(this.errorCode != null) {
            IOException iOException0 = this.errorException;
            if(iOException0 == null) {
                ErrorCode errorCode0 = this.errorCode;
                Intrinsics.checkNotNull(errorCode0);
                iOException0 = new StreamResetException(errorCode0);
            }
            throw iOException0;
        }
    }

    public final void close(ErrorCode errorCode0, IOException iOException0) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode0, "rstStatusCode");
        if(!this.closeInternal(errorCode0, iOException0)) {
            return;
        }
        this.connection.writeSynReset$okhttp(this.id, errorCode0);
    }

    private final boolean closeInternal(ErrorCode errorCode0, IOException iOException0) {
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13442 MUST NOT hold lock on " + this);
        }
        synchronized(this) {
            if(this.errorCode != null) {
                return false;
            }
            if(this.source.getFinished$okhttp() && this.sink.getFinished()) {
                return false;
            }
            this.errorCode = errorCode0;
            this.errorException = iOException0;
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
            this.notifyAll();
        }
        this.connection.removeStream$okhttp(this.id);
        return true;
    }

    public final void closeLater(ErrorCode errorCode0) {
        Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
        if(!this.closeInternal(errorCode0, null)) {
            return;
        }
        this.connection.writeSynResetLater$okhttp(this.id, errorCode0);
    }

    // 去混淆评级： 低(30)
    private final boolean doReadTimeout() {
        return !this.connection.getClient$okhttp() || this.sink.getClosed() || this.sink.getFinished();
    }

    public final void enqueueTrailers(Headers headers0) {
        Intrinsics.checkNotNullParameter(headers0, "trailers");
        synchronized(this) {
            if(!this.sink.getFinished()) {
                if(headers0.size() == 0) {
                    throw new IllegalArgumentException("trailers.size() == 0");
                }
                this.sink.setTrailers(headers0);
                return;
            }
        }
        throw new IllegalStateException("already finished");
    }

    public final Http2Connection getConnection() {
        return this.connection;
    }

    public final ErrorCode getErrorCode$okhttp() {
        synchronized(this) {
        }
        return this.errorCode;
    }

    public final IOException getErrorException$okhttp() {
        return this.errorException;
    }

    public final int getId() {
        return this.id;
    }

    public final long getReadBytesAcknowledged() {
        return this.readBytesAcknowledged;
    }

    public final long getReadBytesTotal() {
        return this.readBytesTotal;
    }

    public final StreamTimeout getReadTimeout$okhttp() {
        return this.readTimeout;
    }

    public final Sink getSink() {
        synchronized(this) {
            if(!this.hasResponseHeaders && !this.isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
            return this.sink;
        }
    }

    public final FramingSink getSink$okhttp() {
        return this.sink;
    }

    public final Source getSource() {
        return this.source;
    }

    public final FramingSource getSource$okhttp() {
        return this.source;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final StreamTimeout getWriteTimeout$okhttp() {
        return this.writeTimeout;
    }

    public final boolean isLocallyInitiated() {
        return this.connection.getClient$okhttp() == ((this.id & 1) == 1);
    }

    public final boolean isOpen() {
        synchronized(this) {
            if(this.errorCode != null) {
                return false;
            }
            return (this.source.getFinished$okhttp() || this.source.getClosed$okhttp()) && (this.sink.getFinished() || this.sink.getClosed()) && this.hasResponseHeaders ? false : true;
        }
    }

    public final Timeout readTimeout() {
        return this.readTimeout;
    }

    public final void receiveData(BufferedSource bufferedSource0, int v) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13506 MUST NOT hold lock on " + this);
        }
        this.source.receive$okhttp(bufferedSource0, ((long)v));
    }

    public final void receiveHeaders(Headers headers0, boolean z) {
        Intrinsics.checkNotNullParameter(headers0, "headers");
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13445 MUST NOT hold lock on " + this);
        }
        synchronized(this) {
            if(!this.hasResponseHeaders || headers0.get(":status") != null || headers0.get(":method") != null) {
                this.hasResponseHeaders = true;
                this.headersQueue.add(headers0);
            }
            else {
                this.source.setTrailers(headers0);
            }
            if(z) {
                this.source.setFinished$okhttp(true);
            }
            boolean z1 = this.isOpen();
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
            this.notifyAll();
        }
        if(!z1) {
            this.connection.removeStream$okhttp(this.id);
        }
    }

    public final void receiveRstStream(ErrorCode errorCode0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
            if(this.errorCode == null) {
                this.errorCode = errorCode0;
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                this.notifyAll();
            }
        }
    }

    public final void setErrorCode$okhttp(ErrorCode errorCode0) {
        this.errorCode = errorCode0;
    }

    public final void setErrorException$okhttp(IOException iOException0) {
        this.errorException = iOException0;
    }

    public final void setReadBytesAcknowledged$okhttp(long v) {
        this.readBytesAcknowledged = v;
    }

    public final void setReadBytesTotal$okhttp(long v) {
        this.readBytesTotal = v;
    }

    public final void setWriteBytesMaximum$okhttp(long v) {
        this.writeBytesMaximum = v;
    }

    public final void setWriteBytesTotal$okhttp(long v) {
        this.writeBytesTotal = v;
    }

    public final Headers takeHeaders(boolean z) throws IOException {
        IOException iOException0;
        synchronized(this) {
            while(this.headersQueue.isEmpty() && this.errorCode == null) {
                boolean z1 = z || this.doReadTimeout();
                if(z1) {
                    this.readTimeout.enter();
                }
                try {
                    this.waitForIo$okhttp();
                    if(!z1) {
                        continue;
                    }
                }
                catch(Throwable throwable0) {
                    if(z1) {
                        this.readTimeout.exitAndThrowIfTimedOut();
                    }
                    throw throwable0;
                }
                this.readTimeout.exitAndThrowIfTimedOut();
            }
            if(!this.headersQueue.isEmpty()) {
                Object object0 = this.headersQueue.removeFirst();
                Intrinsics.checkNotNullExpressionValue(object0, "headersQueue.removeFirst()");
                return (Headers)object0;
            }
            iOException0 = this.errorException;
            if(iOException0 == null) {
                ErrorCode errorCode0 = this.errorCode;
                Intrinsics.checkNotNull(errorCode0);
                iOException0 = new StreamResetException(errorCode0);
            }
        }
        throw iOException0;
    }

    public static Headers takeHeaders$default(Http2Stream http2Stream0, boolean z, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            z = false;
        }
        return http2Stream0.takeHeaders(z);
    }

    public final Headers trailers() throws IOException {
        synchronized(this) {
            if(this.source.getFinished$okhttp() && this.source.getReceiveBuffer().exhausted() && this.source.getReadBuffer().exhausted()) {
                Headers headers0 = this.source.getTrailers();
                if(headers0 == null) {
                    headers0 = _UtilJvmKt.EMPTY_HEADERS;
                }
                return headers0;
            }
            if(this.errorCode != null) {
                IOException iOException0 = this.errorException;
                if(iOException0 == null) {
                    ErrorCode errorCode0 = this.errorCode;
                    Intrinsics.checkNotNull(errorCode0);
                    iOException0 = new StreamResetException(errorCode0);
                }
                throw iOException0;
            }
        }
        throw new IllegalStateException("too early; can\'t read the trailers yet");
    }

    public final void waitForIo$okhttp() throws InterruptedIOException {
        try {
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
            this.wait();
        }
        catch(InterruptedException unused_ex) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public final void writeHeaders(List list0, boolean z, boolean z1) throws IOException {
        boolean z2;
        Intrinsics.checkNotNullParameter(list0, "responseHeaders");
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13450 MUST NOT hold lock on " + this);
        }
        synchronized(this) {
            z2 = true;
            this.hasResponseHeaders = true;
            if(z) {
                this.sink.setFinished(true);
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                this.notifyAll();
            }
        }
        if(!z1) {
            synchronized(this.connection) {
                if(this.connection.getWriteBytesTotal() < this.connection.getWriteBytesMaximum()) {
                    z2 = false;
                }
            }
            z1 = z2;
        }
        this.connection.writeHeaders$okhttp(this.id, z, list0);
        if(z1) {
            this.connection.flush();
        }
    }

    public final Timeout writeTimeout() {
        return this.writeTimeout;
    }
}

