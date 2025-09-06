package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilCommonKt;
import okio.Buffer;
import okio.BufferedSink;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 :2\u00020\u0001:\u0001:B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\u000E\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013J\b\u0010\u0014\u001A\u00020\u0011H\u0016J\u0006\u0010\u0015\u001A\u00020\u0011J(\u0010\u0016\u001A\u00020\u00112\u0006\u0010\u0017\u001A\u00020\u00052\u0006\u0010\u0018\u001A\u00020\u000F2\b\u0010\u0019\u001A\u0004\u0018\u00010\t2\u0006\u0010\u001A\u001A\u00020\u000FJ(\u0010\u001B\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u000F2\u0006\u0010\u001C\u001A\u00020\u000F2\b\u0010\u001D\u001A\u0004\u0018\u00010\t2\u0006\u0010\u001A\u001A\u00020\u000FJ\u0006\u0010\u001E\u001A\u00020\u0011J&\u0010\u001F\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u000F2\u0006\u0010 \u001A\u00020\u000F2\u0006\u0010!\u001A\u00020\u000F2\u0006\u0010\u001C\u001A\u00020\u000FJ\u001E\u0010\"\u001A\u00020\u00112\u0006\u0010#\u001A\u00020\u000F2\u0006\u0010$\u001A\u00020%2\u0006\u0010&\u001A\u00020\'J$\u0010(\u001A\u00020\u00112\u0006\u0010\u0017\u001A\u00020\u00052\u0006\u0010\u0018\u001A\u00020\u000F2\f\u0010)\u001A\b\u0012\u0004\u0012\u00020+0*J\u0006\u0010,\u001A\u00020\u000FJ\u001E\u0010-\u001A\u00020\u00112\u0006\u0010.\u001A\u00020\u00052\u0006\u0010/\u001A\u00020\u000F2\u0006\u00100\u001A\u00020\u000FJ$\u00101\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u000F2\u0006\u00102\u001A\u00020\u000F2\f\u00103\u001A\b\u0012\u0004\u0012\u00020+0*J\u0016\u00104\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u000F2\u0006\u0010$\u001A\u00020%J\u000E\u00105\u001A\u00020\u00112\u0006\u00105\u001A\u00020\u0013J\u0016\u00106\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u000F2\u0006\u00107\u001A\u000208J\u0018\u00109\u001A\u00020\u00112\u0006\u0010\u0018\u001A\u00020\u000F2\u0006\u0010\u001A\u001A\u000208H\u0002R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\n\u001A\u00020\u000B\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u000E\u0010\u000E\u001A\u00020\u000FX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006;"}, d2 = {"Lokhttp3/internal/http2/Http2Writer;", "Ljava/io/Closeable;", "sink", "Lokio/BufferedSink;", "client", "", "(Lokio/BufferedSink;Z)V", "closed", "hpackBuffer", "Lokio/Buffer;", "hpackWriter", "Lokhttp3/internal/http2/Hpack$Writer;", "getHpackWriter", "()Lokhttp3/internal/http2/Hpack$Writer;", "maxFrameSize", "", "applyAndAckSettings", "", "peerSettings", "Lokhttp3/internal/http2/Settings;", "close", "connectionPreface", "data", "outFinished", "streamId", "source", "byteCount", "dataFrame", "flags", "buffer", "flush", "frameHeader", "length", "type", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "", "headers", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "maxDataLength", "ping", "ack", "payload1", "payload2", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "settings", "windowUpdate", "windowSizeIncrement", "", "writeContinuationFrames", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http2Writer implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/http2/Http2Writer$Companion;", "", "()V", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    private final boolean client;
    private boolean closed;
    private final Buffer hpackBuffer;
    private final Writer hpackWriter;
    private static final Logger logger;
    private int maxFrameSize;
    private final BufferedSink sink;

    static {
        Http2Writer.Companion = new Companion(null);
        Http2Writer.logger = Logger.getLogger("okhttp3.internal.http2.Http2");
    }

    public Http2Writer(BufferedSink bufferedSink0, boolean z) {
        Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
        super();
        this.sink = bufferedSink0;
        this.client = z;
        Buffer buffer0 = new Buffer();
        this.hpackBuffer = buffer0;
        this.maxFrameSize = 0x4000;
        this.hpackWriter = new Writer(0, false, buffer0, 3, null);
    }

    public final void applyAndAckSettings(Settings settings0) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(settings0, "peerSettings");
            if(!this.closed) {
                this.maxFrameSize = settings0.getMaxFrameSize(this.maxFrameSize);
                if(settings0.getHeaderTableSize() != -1) {
                    int v1 = settings0.getHeaderTableSize();
                    this.hpackWriter.resizeHeaderTable(v1);
                }
                this.frameHeader(0, 0, 4, 1);
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    @Override
    public void close() throws IOException {
        synchronized(this) {
            this.closed = true;
            this.sink.close();
        }
    }

    public final void connectionPreface() throws IOException {
        synchronized(this) {
            if(!this.closed) {
                if(!this.client) {
                    return;
                }
                Logger logger0 = Http2Writer.logger;
                if(logger0.isLoggable(Level.FINE)) {
                    logger0.fine(">> CONNECTION 505249202a20485454502f322e300d0a0d0a534d0d0a0d0a");
                }
                this.sink.write(Http2.CONNECTION_PREFACE);
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void data(boolean z, int v, Buffer buffer0, int v1) throws IOException {
        synchronized(this) {
            if(!this.closed) {
                this.dataFrame(v, ((int)z), buffer0, v1);
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void dataFrame(int v, int v1, Buffer buffer0, int v2) throws IOException {
        this.frameHeader(v, v2, 0, v1);
        if(v2 > 0) {
            Intrinsics.checkNotNull(buffer0);
            this.sink.write(buffer0, ((long)v2));
        }
    }

    public final void flush() throws IOException {
        synchronized(this) {
            if(!this.closed) {
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void frameHeader(int v, int v1, int v2, int v3) throws IOException {
        if(v2 != 8) {
            Logger logger0 = Http2Writer.logger;
            if(logger0.isLoggable(Level.FINE)) {
                logger0.fine(Http2.INSTANCE.frameLog(false, v, v1, v2, v3));
            }
        }
        if(v1 > this.maxFrameSize) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.maxFrameSize + ": " + v1).toString());
        }
        if((0x80000000 & v) != 0) {
            throw new IllegalArgumentException(("reserved bit set: " + v).toString());
        }
        _UtilCommonKt.writeMedium(this.sink, v1);
        this.sink.writeByte(v2 & 0xFF);
        this.sink.writeByte(v3 & 0xFF);
        this.sink.writeInt(0x7FFFFFFF & v);
    }

    public final Writer getHpackWriter() {
        return this.hpackWriter;
    }

    public final void goAway(int v, ErrorCode errorCode0, byte[] arr_b) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
            Intrinsics.checkNotNullParameter(arr_b, "debugData");
            if(!this.closed) {
                if(errorCode0.getHttpCode() == -1) {
                    throw new IllegalArgumentException("errorCode.httpCode == -1");
                }
                this.frameHeader(0, arr_b.length + 8, 7, 0);
                this.sink.writeInt(v);
                this.sink.writeInt(errorCode0.getHttpCode());
                if(arr_b.length != 0) {
                    this.sink.write(arr_b);
                }
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void headers(boolean z, int v, List list0) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(list0, "headerBlock");
            if(!this.closed) {
                this.hpackWriter.writeHeaders(list0);
                long v2 = this.hpackBuffer.size();
                long v3 = Math.min(this.maxFrameSize, v2);
                int v4 = Long.compare(v2, v3);
                int v5 = v4 == 0 ? 4 : 0;
                if(z) {
                    v5 |= 1;
                }
                this.frameHeader(v, ((int)v3), 1, v5);
                this.sink.write(this.hpackBuffer, v3);
                if(v4 > 0) {
                    this.writeContinuationFrames(v, v2 - v3);
                }
                return;
            }
        }
        throw new IOException("closed");
    }

    public final int maxDataLength() {
        return this.maxFrameSize;
    }

    public final void ping(boolean z, int v, int v1) throws IOException {
        synchronized(this) {
            if(!this.closed) {
                this.frameHeader(0, 8, 6, ((int)z));
                this.sink.writeInt(v);
                this.sink.writeInt(v1);
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void pushPromise(int v, int v1, List list0) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(list0, "requestHeaders");
            if(!this.closed) {
                this.hpackWriter.writeHeaders(list0);
                long v3 = this.hpackBuffer.size();
                int v4 = (int)Math.min(((long)this.maxFrameSize) - 4L, v3);
                int v5 = Long.compare(v3, v4);
                this.frameHeader(v, v4 + 4, 5, (v5 == 0 ? 4 : 0));
                this.sink.writeInt(v1 & 0x7FFFFFFF);
                this.sink.write(this.hpackBuffer, ((long)v4));
                if(v5 > 0) {
                    this.writeContinuationFrames(v, v3 - ((long)v4));
                }
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void rstStream(int v, ErrorCode errorCode0) throws IOException {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
            if(!this.closed) {
                if(errorCode0.getHttpCode() == -1) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                this.frameHeader(v, 4, 3, 0);
                this.sink.writeInt(errorCode0.getHttpCode());
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void settings(Settings settings0) throws IOException {
        int v2;
        synchronized(this) {
            Intrinsics.checkNotNullParameter(settings0, "settings");
            if(!this.closed) {
                this.frameHeader(0, settings0.size() * 6, 4, 0);
                for(int v1 = 0; v1 < 10; ++v1) {
                    if(settings0.isSet(v1)) {
                        switch(v1) {
                            case 4: {
                                v2 = 3;
                                break;
                            }
                            case 7: {
                                v2 = 4;
                                break;
                            }
                            default: {
                                v2 = v1;
                            }
                        }
                        this.sink.writeShort(v2);
                        int v3 = settings0.get(v1);
                        this.sink.writeInt(v3);
                    }
                }
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    public final void windowUpdate(int v, long v1) throws IOException {
        long v3;
        synchronized(this) {
            if(!this.closed) {
                if(v1 == 0L || v1 > 0x7FFFFFFFL) {
                    throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + v1).toString());
                }
                Logger logger0 = Http2Writer.logger;
                if(logger0.isLoggable(Level.FINE)) {
                    v3 = v1;
                    logger0.fine(Http2.INSTANCE.frameLogWindowUpdate(false, v, 4, v3));
                }
                else {
                    v3 = v1;
                }
                this.frameHeader(v, 4, 8, 0);
                this.sink.writeInt(((int)v3));
                this.sink.flush();
                return;
            }
        }
        throw new IOException("closed");
    }

    private final void writeContinuationFrames(int v, long v1) throws IOException {
        while(v1 > 0L) {
            long v2 = Math.min(this.maxFrameSize, v1);
            v1 -= v2;
            this.frameHeader(v, ((int)v2), 9, (v1 == 0L ? 4 : 0));
            this.sink.write(this.hpackBuffer, v2);
        }
    }
}

