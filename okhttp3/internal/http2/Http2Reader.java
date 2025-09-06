package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 #2\u00020\u0001:\u0003#$%B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\b\u0010\u000B\u001A\u00020\fH\u0016J\u0016\u0010\r\u001A\u00020\u00052\u0006\u0010\u000E\u001A\u00020\u00052\u0006\u0010\u000F\u001A\u00020\u0010J\u000E\u0010\u0011\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u0010J(\u0010\u0012\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010\u0017\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J.\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u001A0\u00192\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u001B\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010\u001C\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010\u001D\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J\u0018\u0010\u001E\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010\u001E\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010\u001F\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010 \u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010!\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002J(\u0010\"\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00142\u0006\u0010\u0016\u001A\u00020\u0014H\u0002R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006&"}, d2 = {"Lokhttp3/internal/http2/Http2Reader;", "Ljava/io/Closeable;", "source", "Lokio/BufferedSource;", "client", "", "(Lokio/BufferedSource;Z)V", "continuation", "Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "hpackReader", "Lokhttp3/internal/http2/Hpack$Reader;", "close", "", "nextFrame", "requireSettings", "handler", "Lokhttp3/internal/http2/Http2Reader$Handler;", "readConnectionPreface", "readData", "length", "", "flags", "streamId", "readGoAway", "readHeaderBlock", "", "Lokhttp3/internal/http2/Header;", "padding", "readHeaders", "readPing", "readPriority", "readPushPromise", "readRstStream", "readSettings", "readWindowUpdate", "Companion", "ContinuationSource", "Handler", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http2Reader implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001E\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\b2\u0006\u0010\n\u001A\u00020\b2\u0006\u0010\u000B\u001A\u00020\bR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Companion;", "", "()V", "logger", "Ljava/util/logging/Logger;", "getLogger", "()Ljava/util/logging/Logger;", "lengthWithoutPadding", "", "length", "flags", "padding", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Logger getLogger() {
            return Http2Reader.logger;
        }

        public final int lengthWithoutPadding(int v, int v1, int v2) throws IOException {
            if((v1 & 8) != 0) {
                --v;
            }
            if(v2 > v) {
                throw new IOException("PROTOCOL_ERROR padding " + v2 + " > remaining length " + v);
            }
            return v - v2;
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001A\u00020\u0018H\u0016J\u0018\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u001D\u001A\u00020\u001AH\u0016J\b\u0010\u001E\u001A\u00020\u0018H\u0002J\b\u0010\u001F\u001A\u00020 H\u0016R\u001A\u0010\u0005\u001A\u00020\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001A\u0010\u000B\u001A\u00020\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001A\u0010\u000E\u001A\u00020\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\b\"\u0004\b\u0010\u0010\nR\u001A\u0010\u0011\u001A\u00020\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0014\u001A\u00020\u0006X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0015\u0010\b\"\u0004\b\u0016\u0010\n¨\u0006!"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$ContinuationSource;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "flags", "", "getFlags", "()I", "setFlags", "(I)V", "left", "getLeft", "setLeft", "length", "getLength", "setLength", "padding", "getPadding", "setPadding", "streamId", "getStreamId", "setStreamId", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "readContinuationHeader", "timeout", "Lokio/Timeout;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class ContinuationSource implements AutoCloseable, Source {
        private int flags;
        private int left;
        private int length;
        private int padding;
        private final BufferedSource source;
        private int streamId;

        public ContinuationSource(BufferedSource bufferedSource0) {
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            super();
            this.source = bufferedSource0;
        }

        @Override  // okio.Source
        public void close() throws IOException {
        }

        public final int getFlags() {
            return this.flags;
        }

        public final int getLeft() {
            return this.left;
        }

        public final int getLength() {
            return this.length;
        }

        public final int getPadding() {
            return this.padding;
        }

        public final int getStreamId() {
            return this.streamId;
        }

        @Override  // okio.Source
        public long read(Buffer buffer0, long v) throws IOException {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            int v1;
            while((v1 = this.left) == 0) {
                this.source.skip(((long)this.padding));
                this.padding = 0;
                if((this.flags & 4) != 0) {
                    return -1L;
                }
                this.readContinuationHeader();
            }
            long v2 = this.source.read(buffer0, Math.min(v, v1));
            if(v2 == -1L) {
                return -1L;
            }
            this.left -= (int)v2;
            return v2;
        }

        private final void readContinuationHeader() throws IOException {
            int v = this.streamId;
            int v1 = _UtilCommonKt.readMedium(this.source);
            this.left = v1;
            this.length = v1;
            int v2 = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
            this.flags = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
            if(Http2Reader.Companion.getLogger().isLoggable(Level.FINE)) {
                Http2Reader.Companion.getLogger().fine(Http2.INSTANCE.frameLog(true, this.streamId, this.length, v2, this.flags));
            }
            int v3 = this.source.readInt();
            this.streamId = v3 & 0x7FFFFFFF;
            if(v2 != 9) {
                throw new IOException(v2 + " != TYPE_CONTINUATION");
            }
            if((v3 & 0x7FFFFFFF) != v) {
                throw new IOException("TYPE_CONTINUATION streamId changed");
            }
        }

        public final void setFlags(int v) {
            this.flags = v;
        }

        public final void setLeft(int v) {
            this.left = v;
        }

        public final void setLength(int v) {
            this.length = v;
        }

        public final void setPadding(int v) {
            this.padding = v;
        }

        public final void setStreamId(int v) {
            this.streamId = v;
        }

        @Override  // okio.Source
        public Timeout timeout() {
            return this.source.timeout();
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&J8\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\u00062\u0006\u0010\r\u001A\u00020\u000EH&J(\u0010\u000F\u001A\u00020\u00032\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u0006H&J \u0010\u0015\u001A\u00020\u00032\u0006\u0010\u0016\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\nH&J.\u0010\u001A\u001A\u00020\u00032\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u001B\u001A\u00020\u00062\f\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u001E0\u001DH&J \u0010\u001F\u001A\u00020\u00032\u0006\u0010 \u001A\u00020\u00112\u0006\u0010!\u001A\u00020\u00062\u0006\u0010\"\u001A\u00020\u0006H&J(\u0010#\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010$\u001A\u00020\u00062\u0006\u0010%\u001A\u00020\u00062\u0006\u0010&\u001A\u00020\u0011H&J&\u0010\'\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010(\u001A\u00020\u00062\f\u0010)\u001A\b\u0012\u0004\u0012\u00020\u001E0\u001DH&J\u0018\u0010*\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0018H&J\u0018\u0010+\u001A\u00020\u00032\u0006\u0010,\u001A\u00020\u00112\u0006\u0010+\u001A\u00020-H&J\u0018\u0010.\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010/\u001A\u00020\u000EH&\u00F8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00A8\u00060\u00C0\u0006\u0001"}, d2 = {"Lokhttp3/internal/http2/Http2Reader$Handler;", "", "ackSettings", "", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "data", "inFinished", "", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "settings", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "windowUpdate", "windowSizeIncrement", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Handler {
        void ackSettings();

        void alternateService(int arg1, String arg2, ByteString arg3, String arg4, int arg5, long arg6);

        void data(boolean arg1, int arg2, BufferedSource arg3, int arg4) throws IOException;

        void goAway(int arg1, ErrorCode arg2, ByteString arg3);

        void headers(boolean arg1, int arg2, int arg3, List arg4);

        void ping(boolean arg1, int arg2, int arg3);

        void priority(int arg1, int arg2, int arg3, boolean arg4);

        void pushPromise(int arg1, int arg2, List arg3) throws IOException;

        void rstStream(int arg1, ErrorCode arg2);

        void settings(boolean arg1, Settings arg2);

        void windowUpdate(int arg1, long arg2);
    }

    public static final Companion Companion;
    private final boolean client;
    private final ContinuationSource continuation;
    private final Reader hpackReader;
    private static final Logger logger;
    private final BufferedSource source;

    static {
        Http2Reader.Companion = new Companion(null);
        Logger logger0 = Logger.getLogger("okhttp3.internal.http2.Http2");
        Intrinsics.checkNotNullExpressionValue(logger0, "getLogger(Http2::class.java.name)");
        Http2Reader.logger = logger0;
    }

    public Http2Reader(BufferedSource bufferedSource0, boolean z) {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        super();
        this.source = bufferedSource0;
        this.client = z;
        ContinuationSource http2Reader$ContinuationSource0 = new ContinuationSource(bufferedSource0);
        this.continuation = http2Reader$ContinuationSource0;
        this.hpackReader = new Reader(http2Reader$ContinuationSource0, 0x1000, 0, 4, null);
    }

    @Override
    public void close() throws IOException {
        this.source.close();
    }

    public final boolean nextFrame(boolean z, Handler http2Reader$Handler0) throws IOException {
        Intrinsics.checkNotNullParameter(http2Reader$Handler0, "handler");
        try {
            this.source.require(9L);
        }
        catch(EOFException unused_ex) {
            return false;
        }
        int v = _UtilCommonKt.readMedium(this.source);
        if(v <= 0x4000) {
            int v1 = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
            int v2 = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
            int v3 = this.source.readInt();
            if(v1 != 8) {
                Logger logger0 = Http2Reader.logger;
                if(logger0.isLoggable(Level.FINE)) {
                    logger0.fine(Http2.INSTANCE.frameLog(true, v3 & 0x7FFFFFFF, v, v1, v2));
                }
            }
            if(z && v1 != 4) {
                throw new IOException("Expected a SETTINGS frame but was " + Http2.INSTANCE.formattedType$okhttp(v1));
            }
            switch(v1) {
                case 0: {
                    this.readData(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 1: {
                    this.readHeaders(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 2: {
                    this.readPriority(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 3: {
                    this.readRstStream(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 4: {
                    this.readSettings(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 5: {
                    this.readPushPromise(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 6: {
                    this.readPing(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 7: {
                    this.readGoAway(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                case 8: {
                    this.readWindowUpdate(http2Reader$Handler0, v, v2, v3 & 0x7FFFFFFF);
                    return true;
                }
                default: {
                    this.source.skip(((long)v));
                    return true;
                }
            }
        }
        throw new IOException("FRAME_SIZE_ERROR: " + v);
    }

    public final void readConnectionPreface(Handler http2Reader$Handler0) throws IOException {
        Intrinsics.checkNotNullParameter(http2Reader$Handler0, "handler");
        if(this.client) {
            if(!this.nextFrame(true, http2Reader$Handler0)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        ByteString byteString0 = this.source.readByteString(((long)Http2.CONNECTION_PREFACE.size()));
        Logger logger0 = Http2Reader.logger;
        if(logger0.isLoggable(Level.FINE)) {
            logger0.fine(_UtilJvmKt.format(("<< CONNECTION " + byteString0.hex()), new Object[0]));
        }
        if(!Intrinsics.areEqual(Http2.CONNECTION_PREFACE, byteString0)) {
            throw new IOException("Expected a connection header but was " + byteString0.utf8());
        }
    }

    private final void readData(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        int v3 = 0;
        if(v2 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        if((v1 & 0x20) != 0) {
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        if((v1 & 8) != 0) {
            v3 = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
        }
        int v4 = Http2Reader.Companion.lengthWithoutPadding(v, v1, v3);
        http2Reader$Handler0.data((v1 & 1) != 0, v2, this.source, v4);
        this.source.skip(((long)v3));
    }

    private final void readGoAway(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        if(v < 8) {
            throw new IOException("TYPE_GOAWAY length < 8: " + v);
        }
        if(v2 != 0) {
            throw new IOException("TYPE_GOAWAY streamId != 0");
        }
        int v3 = this.source.readInt();
        int v4 = this.source.readInt();
        ErrorCode errorCode0 = ErrorCode.Companion.fromHttp2(v4);
        if(errorCode0 == null) {
            throw new IOException("TYPE_GOAWAY unexpected error code: " + v4);
        }
        http2Reader$Handler0.goAway(v3, errorCode0, (v - 8 <= 0 ? ByteString.EMPTY : this.source.readByteString(((long)(v - 8)))));
    }

    private final List readHeaderBlock(int v, int v1, int v2, int v3) throws IOException {
        this.continuation.setLeft(v);
        int v4 = this.continuation.getLeft();
        this.continuation.setLength(v4);
        this.continuation.setPadding(v1);
        this.continuation.setFlags(v2);
        this.continuation.setStreamId(v3);
        this.hpackReader.readHeaders();
        return this.hpackReader.getAndResetHeaderList();
    }

    private final void readHeaders(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        int v3 = 0;
        if(v2 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        if((v1 & 8) != 0) {
            v3 = _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
        }
        if((v1 & 0x20) != 0) {
            this.readPriority(http2Reader$Handler0, v2);
            v -= 5;
        }
        http2Reader$Handler0.headers((v1 & 1) != 0, v2, -1, this.readHeaderBlock(Http2Reader.Companion.lengthWithoutPadding(v, v1, v3), v3, v1, v2));
    }

    private final void readPing(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        if(v != 8) {
            throw new IOException("TYPE_PING length != 8: " + v);
        }
        if(v2 != 0) {
            throw new IOException("TYPE_PING streamId != 0");
        }
        http2Reader$Handler0.ping((v1 & 1) != 0, this.source.readInt(), this.source.readInt());
    }

    private final void readPriority(Handler http2Reader$Handler0, int v) throws IOException {
        int v1 = this.source.readInt();
        http2Reader$Handler0.priority(v, v1 & 0x7FFFFFFF, _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF)) + 1, (0x80000000 & v1) != 0);
    }

    private final void readPriority(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        if(v != 5) {
            throw new IOException("TYPE_PRIORITY length: " + v + " != 5");
        }
        if(v2 == 0) {
            throw new IOException("TYPE_PRIORITY streamId == 0");
        }
        this.readPriority(http2Reader$Handler0, v2);
    }

    private final void readPushPromise(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        if(v2 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        int v3 = (v1 & 8) == 0 ? 0 : _UtilCommonKt.and(this.source.readByte(), ((byte)0xFF));
        http2Reader$Handler0.pushPromise(v2, this.source.readInt() & 0x7FFFFFFF, this.readHeaderBlock(Http2Reader.Companion.lengthWithoutPadding(v - 4, v1, v3), v3, v1, v2));
    }

    private final void readRstStream(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        if(v != 4) {
            throw new IOException("TYPE_RST_STREAM length: " + v + " != 4");
        }
        if(v2 == 0) {
            throw new IOException("TYPE_RST_STREAM streamId == 0");
        }
        int v3 = this.source.readInt();
        ErrorCode errorCode0 = ErrorCode.Companion.fromHttp2(v3);
        if(errorCode0 == null) {
            throw new IOException("TYPE_RST_STREAM unexpected error code: " + v3);
        }
        http2Reader$Handler0.rstStream(v2, errorCode0);
    }

    private final void readSettings(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        if(v2 != 0) {
            throw new IOException("TYPE_SETTINGS streamId != 0");
        }
        if((v1 & 1) != 0) {
            if(v != 0) {
                throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
            }
            http2Reader$Handler0.ackSettings();
            return;
        }
        if(v % 6 != 0) {
            throw new IOException("TYPE_SETTINGS length % 6 != 0: " + v);
        }
        Settings settings0 = new Settings();
        IntProgression intProgression0 = RangesKt.step(RangesKt.until(0, v), 6);
        int v3 = intProgression0.getFirst();
        int v4 = intProgression0.getLast();
        int v5 = intProgression0.getStep();
        if(v5 > 0 && v3 <= v4 || v5 < 0 && v4 <= v3) {
            while(true) {
                int v6 = _UtilCommonKt.and(this.source.readShort(), ((short)0xFFFF));
                int v7 = this.source.readInt();
                if(v6 != 2) {
                    switch(v6) {
                        case 3: {
                            v6 = 4;
                            break;
                        }
                        case 4: {
                            if(v7 < 0) {
                                throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                            }
                            v6 = 7;
                            break;
                        }
                        default: {
                            if(v6 == 5 && (v7 < 0x4000 || v7 > 0xFFFFFF)) {
                                throw new IOException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: " + v7);
                            }
                        }
                    }
                }
                else if(v7 != 0 && v7 != 1) {
                    throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                }
                settings0.set(v6, v7);
                if(v3 == v4) {
                    break;
                }
                v3 += v5;
            }
        }
        http2Reader$Handler0.settings(false, settings0);
    }

    private final void readWindowUpdate(Handler http2Reader$Handler0, int v, int v1, int v2) throws IOException {
        long v3;
        try {
            if(v != 4) {
                throw new IOException("TYPE_WINDOW_UPDATE length !=4: " + v);
            }
            v3 = _UtilCommonKt.and(this.source.readInt(), 0x7FFFFFFFL);
            if(v3 == 0L) {
                throw new IOException("windowSizeIncrement was 0");
            }
        }
        catch(Exception exception0) {
            String s = Http2.INSTANCE.frameLog(true, v2, v, 8, v1);
            Http2Reader.logger.fine(s);
            throw exception0;
        }
        Logger logger0 = Http2Reader.logger;
        if(logger0.isLoggable(Level.FINE)) {
            logger0.fine(Http2.INSTANCE.frameLogWindowUpdate(true, v2, 4, v3));
        }
        http2Reader$Handler0.windowUpdate(v2, v3);
    }
}

