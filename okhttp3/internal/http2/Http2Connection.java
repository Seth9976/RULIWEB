package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import jeb.synthetic.FIN;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

@Metadata(d1 = {"\u0000\u00B4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001D\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 \u0099\u00012\u00020\u0001:\b\u0098\u0001\u0099\u0001\u009A\u0001\u009B\u0001B\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u0006\u0010P\u001A\u00020QJ\b\u0010R\u001A\u00020QH\u0016J\'\u0010R\u001A\u00020Q2\u0006\u0010S\u001A\u00020T2\u0006\u0010U\u001A\u00020T2\b\u0010V\u001A\u0004\u0018\u00010WH\u0000\u00A2\u0006\u0002\bXJ\u0012\u0010Y\u001A\u00020Q2\b\u0010Z\u001A\u0004\u0018\u00010WH\u0002J\u0006\u0010[\u001A\u00020QJ\u0010\u0010\\\u001A\u0004\u0018\u00010B2\u0006\u0010]\u001A\u00020\u0012J\u000E\u0010^\u001A\u00020\t2\u0006\u0010_\u001A\u00020\u0006J&\u0010`\u001A\u00020B2\u0006\u0010a\u001A\u00020\u00122\f\u0010b\u001A\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001A\u00020\tH\u0002J\u001C\u0010`\u001A\u00020B2\f\u0010b\u001A\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001A\u00020\tJ\u0006\u0010f\u001A\u00020\u0012J-\u0010g\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\u0006\u0010i\u001A\u00020j2\u0006\u0010k\u001A\u00020\u00122\u0006\u0010l\u001A\u00020\tH\u0000\u00A2\u0006\u0002\bmJ+\u0010n\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\f\u0010b\u001A\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010l\u001A\u00020\tH\u0000\u00A2\u0006\u0002\boJ#\u0010p\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\f\u0010b\u001A\b\u0012\u0004\u0012\u00020d0cH\u0000\u00A2\u0006\u0002\bqJ\u001D\u0010r\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\u0006\u0010s\u001A\u00020TH\u0000\u00A2\u0006\u0002\btJ$\u0010u\u001A\u00020B2\u0006\u0010a\u001A\u00020\u00122\f\u0010b\u001A\b\u0012\u0004\u0012\u00020d0c2\u0006\u0010e\u001A\u00020\tJ\u0015\u0010v\u001A\u00020\t2\u0006\u0010h\u001A\u00020\u0012H\u0000\u00A2\u0006\u0002\bwJ\u0017\u0010x\u001A\u0004\u0018\u00010B2\u0006\u0010h\u001A\u00020\u0012H\u0000\u00A2\u0006\u0002\byJ\r\u0010z\u001A\u00020QH\u0000\u00A2\u0006\u0002\b{J\u000E\u0010|\u001A\u00020Q2\u0006\u0010}\u001A\u00020&J\u000E\u0010~\u001A\u00020Q2\u0006\u0010\u007F\u001A\u00020TJ\u0014\u0010\u0080\u0001\u001A\u00020Q2\t\b\u0002\u0010\u0081\u0001\u001A\u00020\tH\u0007J\u0018\u0010\u0082\u0001\u001A\u00020Q2\u0007\u0010\u0083\u0001\u001A\u00020\u0006H\u0000\u00A2\u0006\u0003\b\u0084\u0001J,\u0010\u0085\u0001\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\u0007\u0010\u0086\u0001\u001A\u00020\t2\n\u0010\u0087\u0001\u001A\u0005\u0018\u00010\u0088\u00012\u0006\u0010k\u001A\u00020\u0006J/\u0010\u0089\u0001\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\u0007\u0010\u0086\u0001\u001A\u00020\t2\r\u0010\u008A\u0001\u001A\b\u0012\u0004\u0012\u00020d0cH\u0000\u00A2\u0006\u0003\b\u008B\u0001J\u0007\u0010\u008C\u0001\u001A\u00020QJ\"\u0010\u008C\u0001\u001A\u00020Q2\u0007\u0010\u008D\u0001\u001A\u00020\t2\u0007\u0010\u008E\u0001\u001A\u00020\u00122\u0007\u0010\u008F\u0001\u001A\u00020\u0012J\u0007\u0010\u0090\u0001\u001A\u00020QJ\u001F\u0010\u0091\u0001\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\u0006\u0010\u007F\u001A\u00020TH\u0000\u00A2\u0006\u0003\b\u0092\u0001J\u001F\u0010\u0093\u0001\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\u0006\u0010s\u001A\u00020TH\u0000\u00A2\u0006\u0003\b\u0094\u0001J \u0010\u0095\u0001\u001A\u00020Q2\u0006\u0010h\u001A\u00020\u00122\u0007\u0010\u0096\u0001\u001A\u00020\u0006H\u0000\u00A2\u0006\u0003\b\u0097\u0001R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\tX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\f\u001A\u00020\rX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0014\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0017\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0018\u001A\u00020\tX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0019\u001A\u00020\u0012X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001A\u0010\u001B\"\u0004\b\u001C\u0010\u001DR\u0014\u0010\u001E\u001A\u00020\u001FX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b \u0010!R\u001A\u0010\"\u001A\u00020\u0012X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b#\u0010\u001B\"\u0004\b$\u0010\u001DR\u0011\u0010%\u001A\u00020&\u00A2\u0006\b\n\u0000\u001A\u0004\b\'\u0010(R\u001A\u0010)\u001A\u00020&X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b*\u0010(\"\u0004\b+\u0010,R\u000E\u0010-\u001A\u00020.X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010/\u001A\u000200X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001E\u00102\u001A\u00020\u00062\u0006\u00101\u001A\u00020\u0006@BX\u0086\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b3\u00104R\u001E\u00105\u001A\u00020\u00062\u0006\u00101\u001A\u00020\u0006@BX\u0086\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b6\u00104R\u0015\u00107\u001A\u000608R\u00020\u0000\u00A2\u0006\b\n\u0000\u001A\u0004\b9\u0010:R\u000E\u0010;\u001A\u000200X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010<\u001A\u00020=X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b>\u0010?R \u0010@\u001A\u000E\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020B0AX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\bC\u0010DR\u000E\u0010E\u001A\u00020FX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001E\u0010G\u001A\u00020\u00062\u0006\u00101\u001A\u00020\u0006@BX\u0086\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\bH\u00104R\u001E\u0010I\u001A\u00020\u00062\u0006\u00101\u001A\u00020\u0006@BX\u0086\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\bJ\u00104R\u0011\u0010K\u001A\u00020L\u00A2\u0006\b\n\u0000\u001A\u0004\bM\u0010NR\u000E\u0010O\u001A\u000200X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u009C\u0001"}, d2 = {"Lokhttp3/internal/http2/Http2Connection;", "Ljava/io/Closeable;", "builder", "Lokhttp3/internal/http2/Http2Connection$Builder;", "(Lokhttp3/internal/http2/Http2Connection$Builder;)V", "awaitPingsSent", "", "awaitPongsReceived", "client", "", "getClient$okhttp", "()Z", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "currentPushRequests", "", "", "degradedPingsSent", "degradedPongDeadlineNs", "degradedPongsReceived", "intervalPingsSent", "intervalPongsReceived", "isShutdown", "lastGoodStreamId", "getLastGoodStreamId$okhttp", "()I", "setLastGoodStreamId$okhttp", "(I)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "nextStreamId", "getNextStreamId$okhttp", "setNextStreamId$okhttp", "okHttpSettings", "Lokhttp3/internal/http2/Settings;", "getOkHttpSettings", "()Lokhttp3/internal/http2/Settings;", "peerSettings", "getPeerSettings", "setPeerSettings", "(Lokhttp3/internal/http2/Settings;)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "pushQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "<set-?>", "readBytesAcknowledged", "getReadBytesAcknowledged", "()J", "readBytesTotal", "getReadBytesTotal", "readerRunnable", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "getReaderRunnable", "()Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "settingsListenerQueue", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "streams", "", "Lokhttp3/internal/http2/Http2Stream;", "getStreams$okhttp", "()Ljava/util/Map;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "writeBytesMaximum", "getWriteBytesMaximum", "writeBytesTotal", "getWriteBytesTotal", "writer", "Lokhttp3/internal/http2/Http2Writer;", "getWriter", "()Lokhttp3/internal/http2/Http2Writer;", "writerQueue", "awaitPong", "", "close", "connectionCode", "Lokhttp3/internal/http2/ErrorCode;", "streamCode", "cause", "Ljava/io/IOException;", "close$okhttp", "failConnection", "e", "flush", "getStream", "id", "isHealthy", "nowNs", "newStream", "associatedStreamId", "requestHeaders", "", "Lokhttp3/internal/http2/Header;", "out", "openStreamCount", "pushDataLater", "streamId", "source", "Lokio/BufferedSource;", "byteCount", "inFinished", "pushDataLater$okhttp", "pushHeadersLater", "pushHeadersLater$okhttp", "pushRequestLater", "pushRequestLater$okhttp", "pushResetLater", "errorCode", "pushResetLater$okhttp", "pushStream", "pushedStream", "pushedStream$okhttp", "removeStream", "removeStream$okhttp", "sendDegradedPingLater", "sendDegradedPingLater$okhttp", "setSettings", "settings", "shutdown", "statusCode", "start", "sendConnectionPreface", "updateConnectionFlowControl", "read", "updateConnectionFlowControl$okhttp", "writeData", "outFinished", "buffer", "Lokio/Buffer;", "writeHeaders", "alternating", "writeHeaders$okhttp", "writePing", "reply", "payload1", "payload2", "writePingAndAwaitPong", "writeSynReset", "writeSynReset$okhttp", "writeSynResetLater", "writeSynResetLater$okhttp", "writeWindowUpdateLater", "unacknowledgedBytesRead", "writeWindowUpdateLater$okhttp", "Builder", "Companion", "Listener", "ReaderRunnable", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http2Connection implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u00107\u001A\u000208J\u000E\u0010\u0011\u001A\u00020\u00002\u0006\u0010\u0011\u001A\u00020\u0012J\u000E\u0010\u0017\u001A\u00020\u00002\u0006\u0010\u0017\u001A\u00020\u0018J\u000E\u0010\u001D\u001A\u00020\u00002\u0006\u0010\u001D\u001A\u00020\u001EJ.\u0010)\u001A\u00020\u00002\u0006\u0010)\u001A\u00020*2\b\b\u0002\u00109\u001A\u00020\f2\b\b\u0002\u0010/\u001A\u0002002\b\b\u0002\u0010#\u001A\u00020$H\u0007R\u001A\u0010\u0002\u001A\u00020\u0003X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001A\u0010\u000B\u001A\u00020\fX\u0080.¢\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R\u001A\u0010\u0011\u001A\u00020\u0012X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001A\u0010\u0017\u001A\u00020\u0018X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0019\u0010\u001A\"\u0004\b\u001B\u0010\u001CR\u001A\u0010\u001D\u001A\u00020\u001EX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001F\u0010 \"\u0004\b!\u0010\"R\u001A\u0010#\u001A\u00020$X\u0080.¢\u0006\u000E\n\u0000\u001A\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u001A\u0010)\u001A\u00020*X\u0080.¢\u0006\u000E\n\u0000\u001A\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001A\u0010/\u001A\u000200X\u0080.¢\u0006\u000E\n\u0000\u001A\u0004\b1\u00102\"\u0004\b3\u00104R\u0014\u0010\u0004\u001A\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b5\u00106¨\u0006:"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Builder;", "", "client", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(ZLokhttp3/internal/concurrent/TaskRunner;)V", "getClient$okhttp", "()Z", "setClient$okhttp", "(Z)V", "connectionName", "", "getConnectionName$okhttp", "()Ljava/lang/String;", "setConnectionName$okhttp", "(Ljava/lang/String;)V", "listener", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "setListener$okhttp", "(Lokhttp3/internal/http2/Http2Connection$Listener;)V", "pingIntervalMillis", "", "getPingIntervalMillis$okhttp", "()I", "setPingIntervalMillis$okhttp", "(I)V", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "getPushObserver$okhttp", "()Lokhttp3/internal/http2/PushObserver;", "setPushObserver$okhttp", "(Lokhttp3/internal/http2/PushObserver;)V", "sink", "Lokio/BufferedSink;", "getSink$okhttp", "()Lokio/BufferedSink;", "setSink$okhttp", "(Lokio/BufferedSink;)V", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "setSocket$okhttp", "(Ljava/net/Socket;)V", "source", "Lokio/BufferedSource;", "getSource$okhttp", "()Lokio/BufferedSource;", "setSource$okhttp", "(Lokio/BufferedSource;)V", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "build", "Lokhttp3/internal/http2/Http2Connection;", "peerName", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private boolean client;
        public String connectionName;
        private Listener listener;
        private int pingIntervalMillis;
        private PushObserver pushObserver;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;
        private final TaskRunner taskRunner;

        public Builder(boolean z, TaskRunner taskRunner0) {
            Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
            super();
            this.client = z;
            this.taskRunner = taskRunner0;
            this.listener = Listener.REFUSE_INCOMING_STREAMS;
            this.pushObserver = PushObserver.CANCEL;
        }

        public final Http2Connection build() {
            return new Http2Connection(this);
        }

        public final boolean getClient$okhttp() {
            return this.client;
        }

        public final String getConnectionName$okhttp() {
            String s = this.connectionName;
            if(s != null) {
                return s;
            }
            Intrinsics.throwUninitializedPropertyAccessException("connectionName");
            return null;
        }

        public final Listener getListener$okhttp() {
            return this.listener;
        }

        public final int getPingIntervalMillis$okhttp() {
            return this.pingIntervalMillis;
        }

        public final PushObserver getPushObserver$okhttp() {
            return this.pushObserver;
        }

        public final BufferedSink getSink$okhttp() {
            BufferedSink bufferedSink0 = this.sink;
            if(bufferedSink0 != null) {
                return bufferedSink0;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sink");
            return null;
        }

        public final Socket getSocket$okhttp() {
            Socket socket0 = this.socket;
            if(socket0 != null) {
                return socket0;
            }
            Intrinsics.throwUninitializedPropertyAccessException("socket");
            return null;
        }

        public final BufferedSource getSource$okhttp() {
            BufferedSource bufferedSource0 = this.source;
            if(bufferedSource0 != null) {
                return bufferedSource0;
            }
            Intrinsics.throwUninitializedPropertyAccessException("source");
            return null;
        }

        public final TaskRunner getTaskRunner$okhttp() {
            return this.taskRunner;
        }

        public final Builder listener(Listener http2Connection$Listener0) {
            Intrinsics.checkNotNullParameter(http2Connection$Listener0, "listener");
            this.listener = http2Connection$Listener0;
            return this;
        }

        public final Builder pingIntervalMillis(int v) {
            this.pingIntervalMillis = v;
            return this;
        }

        public final Builder pushObserver(PushObserver pushObserver0) {
            Intrinsics.checkNotNullParameter(pushObserver0, "pushObserver");
            this.pushObserver = pushObserver0;
            return this;
        }

        public final void setClient$okhttp(boolean z) {
            this.client = z;
        }

        public final void setConnectionName$okhttp(String s) {
            Intrinsics.checkNotNullParameter(s, "<set-?>");
            this.connectionName = s;
        }

        public final void setListener$okhttp(Listener http2Connection$Listener0) {
            Intrinsics.checkNotNullParameter(http2Connection$Listener0, "<set-?>");
            this.listener = http2Connection$Listener0;
        }

        public final void setPingIntervalMillis$okhttp(int v) {
            this.pingIntervalMillis = v;
        }

        public final void setPushObserver$okhttp(PushObserver pushObserver0) {
            Intrinsics.checkNotNullParameter(pushObserver0, "<set-?>");
            this.pushObserver = pushObserver0;
        }

        public final void setSink$okhttp(BufferedSink bufferedSink0) {
            Intrinsics.checkNotNullParameter(bufferedSink0, "<set-?>");
            this.sink = bufferedSink0;
        }

        public final void setSocket$okhttp(Socket socket0) {
            Intrinsics.checkNotNullParameter(socket0, "<set-?>");
            this.socket = socket0;
        }

        public final void setSource$okhttp(BufferedSource bufferedSource0) {
            Intrinsics.checkNotNullParameter(bufferedSource0, "<set-?>");
            this.source = bufferedSource0;
        }

        public final Builder socket(Socket socket0) throws IOException {
            Intrinsics.checkNotNullParameter(socket0, "socket");
            return Builder.socket$default(this, socket0, null, null, null, 14, null);
        }

        public final Builder socket(Socket socket0, String s) throws IOException {
            Intrinsics.checkNotNullParameter(socket0, "socket");
            Intrinsics.checkNotNullParameter(s, "peerName");
            return Builder.socket$default(this, socket0, s, null, null, 12, null);
        }

        public final Builder socket(Socket socket0, String s, BufferedSource bufferedSource0) throws IOException {
            Intrinsics.checkNotNullParameter(socket0, "socket");
            Intrinsics.checkNotNullParameter(s, "peerName");
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            return Builder.socket$default(this, socket0, s, bufferedSource0, null, 8, null);
        }

        public final Builder socket(Socket socket0, String s, BufferedSource bufferedSource0, BufferedSink bufferedSink0) throws IOException {
            Intrinsics.checkNotNullParameter(socket0, "socket");
            Intrinsics.checkNotNullParameter(s, "peerName");
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
            this.setSocket$okhttp(socket0);
            this.setConnectionName$okhttp((this.client ? _UtilJvmKt.okHttpName + ' ' + s : "MockWebServer " + s));
            this.setSource$okhttp(bufferedSource0);
            this.setSink$okhttp(bufferedSink0);
            return this;
        }

        public static Builder socket$default(Builder http2Connection$Builder0, Socket socket0, String s, BufferedSource bufferedSource0, BufferedSink bufferedSink0, int v, Object object0) throws IOException {
            if((v & 2) != 0) {
                s = _UtilJvmKt.peerName(socket0);
            }
            if((v & 4) != 0) {
                bufferedSource0 = Okio.buffer(Okio.source(socket0));
            }
            if((v & 8) != 0) {
                bufferedSink0 = Okio.buffer(Okio.sink(socket0));
            }
            return http2Connection$Builder0.socket(socket0, s, bufferedSource0, bufferedSink0);
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u000E\u0010\t\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Companion;", "", "()V", "AWAIT_PING", "", "DEFAULT_SETTINGS", "Lokhttp3/internal/http2/Settings;", "getDEFAULT_SETTINGS", "()Lokhttp3/internal/http2/Settings;", "DEGRADED_PING", "DEGRADED_PONG_TIMEOUT_NS", "INTERVAL_PING", "OKHTTP_CLIENT_WINDOW_SIZE", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Settings getDEFAULT_SETTINGS() {
            return Http2Connection.DEFAULT_SETTINGS;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0010\u0010\t\u001A\u00020\u00042\u0006\u0010\n\u001A\u00020\u000BH&¨\u0006\r"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener;", "", "()V", "onSettings", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "settings", "Lokhttp3/internal/http2/Settings;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static abstract class Listener {
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener$Companion;", "", "()V", "REFUSE_INCOMING_STREAMS", "Lokhttp3/internal/http2/Http2Connection$Listener;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
        public static final class okhttp3.internal.http2.Http2Connection.Listener.Companion {
            private okhttp3.internal.http2.Http2Connection.Listener.Companion() {
            }

            public okhttp3.internal.http2.Http2Connection.Listener.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final okhttp3.internal.http2.Http2Connection.Listener.Companion Companion;
        public static final Listener REFUSE_INCOMING_STREAMS;

        static {
            Listener.Companion = new okhttp3.internal.http2.Http2Connection.Listener.Companion(null);
            Listener.REFUSE_INCOMING_STREAMS = new Http2Connection.Listener.Companion.REFUSE_INCOMING_STREAMS.1();
        }

        public void onSettings(Http2Connection http2Connection0, Settings settings0) {
            Intrinsics.checkNotNullParameter(http2Connection0, "connection");
            Intrinsics.checkNotNullParameter(settings0, "settings");
        }

        public abstract void onStream(Http2Stream arg1) throws IOException;
    }

    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u000F\b\u0000\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\b\u0010\t\u001A\u00020\u0003H\u0016J8\u0010\n\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\f2\u0006\u0010\u0013\u001A\u00020\u0014H\u0016J\u0016\u0010\u0015\u001A\u00020\u00032\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0019J(\u0010\u001A\u001A\u00020\u00032\u0006\u0010\u001B\u001A\u00020\u00172\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\fH\u0016J \u0010\u001F\u001A\u00020\u00032\u0006\u0010 \u001A\u00020\f2\u0006\u0010!\u001A\u00020\"2\u0006\u0010#\u001A\u00020\u0010H\u0016J.\u0010$\u001A\u00020\u00032\u0006\u0010\u001B\u001A\u00020\u00172\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010%\u001A\u00020\f2\f\u0010&\u001A\b\u0012\u0004\u0012\u00020(0\'H\u0016J\t\u0010)\u001A\u00020\u0003H\u0096\u0002J \u0010*\u001A\u00020\u00032\u0006\u0010+\u001A\u00020\u00172\u0006\u0010,\u001A\u00020\f2\u0006\u0010-\u001A\u00020\fH\u0016J(\u0010.\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010/\u001A\u00020\f2\u0006\u00100\u001A\u00020\f2\u0006\u00101\u001A\u00020\u0017H\u0016J&\u00102\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\f2\u0006\u00103\u001A\u00020\f2\f\u00104\u001A\b\u0012\u0004\u0012\u00020(0\'H\u0016J\u0018\u00105\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010!\u001A\u00020\"H\u0016J\u0018\u0010\u0018\u001A\u00020\u00032\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0019H\u0016J\u0018\u00106\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\f2\u0006\u00107\u001A\u00020\u0014H\u0016R\u0014\u0010\u0004\u001A\u00020\u0005X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b\u00A8\u00068"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "Lokhttp3/internal/http2/Http2Reader$Handler;", "Lkotlin/Function0;", "", "reader", "Lokhttp3/internal/http2/Http2Reader;", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Http2Reader;)V", "getReader$okhttp", "()Lokhttp3/internal/http2/Http2Reader;", "ackSettings", "alternateService", "streamId", "", "origin", "", "protocol", "Lokio/ByteString;", "host", "port", "maxAge", "", "applyAndAckSettings", "clearPrevious", "", "settings", "Lokhttp3/internal/http2/Settings;", "data", "inFinished", "source", "Lokio/BufferedSource;", "length", "goAway", "lastGoodStreamId", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "debugData", "headers", "associatedStreamId", "headerBlock", "", "Lokhttp3/internal/http2/Header;", "invoke", "ping", "ack", "payload1", "payload2", "priority", "streamDependency", "weight", "exclusive", "pushPromise", "promisedStreamId", "requestHeaders", "rstStream", "windowUpdate", "windowSizeIncrement", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class ReaderRunnable implements Function0, Handler {
        private final Http2Reader reader;

        public ReaderRunnable(Http2Reader http2Reader0) {
            Intrinsics.checkNotNullParameter(http2Reader0, "reader");
            Http2Connection.this = http2Connection0;
            super();
            this.reader = http2Reader0;
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void ackSettings() {
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void alternateService(int v, String s, ByteString byteString0, String s1, int v1, long v2) {
            Intrinsics.checkNotNullParameter(s, "origin");
            Intrinsics.checkNotNullParameter(byteString0, "protocol");
            Intrinsics.checkNotNullParameter(s1, "host");
        }

        public final void applyAndAckSettings(boolean z, Settings settings0) {
            long v2;
            Http2Stream[] arr_http2Stream;
            Intrinsics.checkNotNullParameter(settings0, "settings");
            ObjectRef ref$ObjectRef0 = new ObjectRef();
            Http2Writer http2Writer0 = Http2Connection.this.getWriter();
            Http2Connection http2Connection0 = Http2Connection.this;
            synchronized(http2Writer0) {
                synchronized(http2Connection0) {
                    Settings settings1 = http2Connection0.getPeerSettings();
                    if(!z) {
                        Settings settings2 = new Settings();
                        settings2.merge(settings1);
                        settings2.merge(settings0);
                        settings0 = settings2;
                    }
                    ref$ObjectRef0.element = settings0;
                    v2 = ((long)((Settings)ref$ObjectRef0.element).getInitialWindowSize()) - ((long)settings1.getInitialWindowSize());
                    if(v2 == 0L || http2Connection0.getStreams$okhttp().isEmpty()) {
                        arr_http2Stream = null;
                    }
                    else {
                        Object[] arr_object = http2Connection0.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                        Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                        arr_http2Stream = (Http2Stream[])arr_object;
                    }
                    http2Connection0.setPeerSettings(((Settings)ref$ObjectRef0.element));
                    TaskQueue.execute$default(http2Connection0.settingsListenerQueue, http2Connection0.getConnectionName$okhttp() + " onSettings", 0L, false, new Function0(ref$ObjectRef0) {
                        final ObjectRef $newPeerSettings;

                        {
                            Http2Connection.this = http2Connection0;
                            this.$newPeerSettings = ref$ObjectRef0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            this.invoke();
                            return Unit.INSTANCE;
                        }

                        public final void invoke() {
                            Http2Connection.this.getListener$okhttp().onSettings(Http2Connection.this, ((Settings)this.$newPeerSettings.element));
                        }
                    }, 6, null);
                }
                try {
                    http2Connection0.getWriter().applyAndAckSettings(((Settings)ref$ObjectRef0.element));
                }
                catch(IOException iOException0) {
                    http2Connection0.failConnection(iOException0);
                }
            }
            if(arr_http2Stream != null) {
                for(int v3 = 0; v3 < arr_http2Stream.length; ++v3) {
                    Http2Stream http2Stream0 = arr_http2Stream[v3];
                    synchronized(http2Stream0) {
                        http2Stream0.addBytesToWriteWindow(v2);
                    }
                }
            }
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void data(boolean z, int v, BufferedSource bufferedSource0, int v1) throws IOException {
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            if(Http2Connection.this.pushedStream$okhttp(v)) {
                Http2Connection.this.pushDataLater$okhttp(v, bufferedSource0, v1, z);
                return;
            }
            Http2Stream http2Stream0 = Http2Connection.this.getStream(v);
            if(http2Stream0 == null) {
                Http2Connection.this.writeSynResetLater$okhttp(v, ErrorCode.PROTOCOL_ERROR);
                Http2Connection.this.updateConnectionFlowControl$okhttp(((long)v1));
                bufferedSource0.skip(((long)v1));
                return;
            }
            http2Stream0.receiveData(bufferedSource0, v1);
            if(z) {
                http2Stream0.receiveHeaders(_UtilJvmKt.EMPTY_HEADERS, true);
            }
        }

        public final Http2Reader getReader$okhttp() {
            return this.reader;
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void goAway(int v, ErrorCode errorCode0, ByteString byteString0) {
            Object[] arr_object;
            Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
            Intrinsics.checkNotNullParameter(byteString0, "debugData");
            byteString0.size();
            synchronized(Http2Connection.this) {
                arr_object = Http2Connection.this.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                Http2Connection.this.isShutdown = true;
            }
            for(int v2 = 0; v2 < ((Http2Stream[])arr_object).length; ++v2) {
                Http2Stream http2Stream0 = ((Http2Stream[])arr_object)[v2];
                if(http2Stream0.getId() > v && http2Stream0.isLocallyInitiated()) {
                    http2Stream0.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    Http2Connection.this.removeStream$okhttp(http2Stream0.getId());
                }
            }
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void headers(boolean z, int v, int v1, List list0) {
            Http2Stream http2Stream0;
            Intrinsics.checkNotNullParameter(list0, "headerBlock");
            if(Http2Connection.this.pushedStream$okhttp(v)) {
                Http2Connection.this.pushHeadersLater$okhttp(v, list0, z);
                return;
            }
            Http2Connection http2Connection0 = Http2Connection.this;
            synchronized(http2Connection0) {
                http2Stream0 = http2Connection0.getStream(v);
                if(http2Stream0 == null) {
                    if(http2Connection0.isShutdown) {
                        return;
                    }
                    if(v <= http2Connection0.getLastGoodStreamId$okhttp()) {
                        return;
                    }
                    if(v % 2 == http2Connection0.getNextStreamId$okhttp() % 2) {
                        return;
                    }
                    Http2Stream http2Stream1 = new Http2Stream(v, http2Connection0, false, z, _UtilJvmKt.toHeaders(list0));
                    http2Connection0.setLastGoodStreamId$okhttp(v);
                    http2Connection0.getStreams$okhttp().put(v, http2Stream1);
                    TaskQueue.execute$default(http2Connection0.taskRunner.newQueue(), http2Connection0.getConnectionName$okhttp() + '[' + v + "] onStream", 0L, false, new Function0(http2Stream1) {
                        final Http2Stream $newStream;

                        {
                            Http2Connection.this = http2Connection0;
                            this.$newStream = http2Stream0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            this.invoke();
                            return Unit.INSTANCE;
                        }

                        public final void invoke() {
                            try {
                                Http2Connection.this.getListener$okhttp().onStream(this.$newStream);
                            }
                            catch(IOException iOException0) {
                                Platform.Companion.get().log("Http2Connection.Listener failure for " + Http2Connection.this.getConnectionName$okhttp(), 4, iOException0);
                                try {
                                    this.$newStream.close(ErrorCode.PROTOCOL_ERROR, iOException0);
                                }
                                catch(IOException unused_ex) {
                                }
                            }
                        }
                    }, 6, null);
                    return;
                }
            }
            http2Stream0.receiveHeaders(_UtilJvmKt.toHeaders(list0), z);
        }

        @Override  // kotlin.jvm.functions.Function0
        public Object invoke() {
            this.invoke();
            return Unit.INSTANCE;
        }

        public void invoke() {
            ErrorCode errorCode1;
            ErrorCode errorCode0;
            try {
                errorCode0 = ErrorCode.INTERNAL_ERROR;
                errorCode1 = ErrorCode.INTERNAL_ERROR;
                this.reader.readConnectionPreface(this);
                while(this.reader.nextFrame(false, this)) {
                }
            }
            catch(IOException iOException0) {
                Http2Connection.this.close$okhttp(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR, iOException0);
                _UtilCommonKt.closeQuietly(this.reader);
                return;
            }
            catch(Throwable throwable0) {
                Http2Connection.this.close$okhttp(errorCode0, errorCode1, null);
                _UtilCommonKt.closeQuietly(this.reader);
                throw throwable0;
            }
            Http2Connection.this.close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
            _UtilCommonKt.closeQuietly(this.reader);
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void ping(boolean z, int v, int v1) {
            if(z) {
                Http2Connection http2Connection0 = Http2Connection.this;
                synchronized(http2Connection0) {
                    switch(v) {
                        case 1: {
                            ++http2Connection0.intervalPongsReceived;
                            break;
                        }
                        case 2: {
                            ++http2Connection0.degradedPongsReceived;
                            break;
                        }
                        case 3: {
                            ++http2Connection0.awaitPongsReceived;
                            Intrinsics.checkNotNull(http2Connection0, "null cannot be cast to non-null type java.lang.Object");
                            http2Connection0.notifyAll();
                        }
                    }
                }
                return;
            }
            TaskQueue.execute$default(Http2Connection.this.writerQueue, Http2Connection.this.getConnectionName$okhttp() + " ping", 0L, false, new Function0(v, v1) {
                final int $payload1;
                final int $payload2;

                {
                    Http2Connection.this = http2Connection0;
                    this.$payload1 = v;
                    this.$payload2 = v1;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    this.invoke();
                    return Unit.INSTANCE;
                }

                public final void invoke() {
                    Http2Connection.this.writePing(true, this.$payload1, this.$payload2);
                }
            }, 6, null);
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void priority(int v, int v1, int v2, boolean z) {
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void pushPromise(int v, int v1, List list0) {
            Intrinsics.checkNotNullParameter(list0, "requestHeaders");
            Http2Connection.this.pushRequestLater$okhttp(v1, list0);
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void rstStream(int v, ErrorCode errorCode0) {
            Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
            if(Http2Connection.this.pushedStream$okhttp(v)) {
                Http2Connection.this.pushResetLater$okhttp(v, errorCode0);
                return;
            }
            Http2Stream http2Stream0 = Http2Connection.this.removeStream$okhttp(v);
            if(http2Stream0 != null) {
                http2Stream0.receiveRstStream(errorCode0);
            }
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void settings(boolean z, Settings settings0) {
            Intrinsics.checkNotNullParameter(settings0, "settings");
            okhttp3.internal.http2.Http2Connection.ReaderRunnable.settings.1 http2Connection$ReaderRunnable$settings$10 = new Function0(z, settings0) {
                final boolean $clearPrevious;
                final Settings $settings;

                {
                    ReaderRunnable.this = http2Connection$ReaderRunnable0;
                    this.$clearPrevious = z;
                    this.$settings = settings0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    this.invoke();
                    return Unit.INSTANCE;
                }

                public final void invoke() {
                    ReaderRunnable.this.applyAndAckSettings(this.$clearPrevious, this.$settings);
                }
            };
            TaskQueue.execute$default(Http2Connection.this.writerQueue, Http2Connection.this.getConnectionName$okhttp() + " applyAndAckSettings", 0L, false, http2Connection$ReaderRunnable$settings$10, 6, null);
        }

        @Override  // okhttp3.internal.http2.Http2Reader$Handler
        public void windowUpdate(int v, long v1) {
            if(v == 0) {
                synchronized(Http2Connection.this) {
                    Http2Connection.this.writeBytesMaximum = Http2Connection.this.getWriteBytesMaximum() + v1;
                    Intrinsics.checkNotNull(Http2Connection.this, "null cannot be cast to non-null type java.lang.Object");
                    Http2Connection.this.notifyAll();
                }
                return;
            }
            Http2Stream http2Stream0 = Http2Connection.this.getStream(v);
            if(http2Stream0 != null) {
                synchronized(http2Stream0) {
                    http2Stream0.addBytesToWriteWindow(v1);
                }
            }
        }
    }

    public static final int AWAIT_PING = 3;
    public static final Companion Companion = null;
    private static final Settings DEFAULT_SETTINGS = null;
    public static final int DEGRADED_PING = 2;
    public static final int DEGRADED_PONG_TIMEOUT_NS = 1000000000;
    public static final int INTERVAL_PING = 1;
    public static final int OKHTTP_CLIENT_WINDOW_SIZE = 0x1000000;
    private long awaitPingsSent;
    private long awaitPongsReceived;
    private final boolean client;
    private final String connectionName;
    private final Set currentPushRequests;
    private long degradedPingsSent;
    private long degradedPongDeadlineNs;
    private long degradedPongsReceived;
    private long intervalPingsSent;
    private long intervalPongsReceived;
    private boolean isShutdown;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextStreamId;
    private final Settings okHttpSettings;
    private Settings peerSettings;
    private final PushObserver pushObserver;
    private final TaskQueue pushQueue;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final ReaderRunnable readerRunnable;
    private final TaskQueue settingsListenerQueue;
    private final Socket socket;
    private final Map streams;
    private final TaskRunner taskRunner;
    private long writeBytesMaximum;
    private long writeBytesTotal;
    private final Http2Writer writer;
    private final TaskQueue writerQueue;

    static {
        Http2Connection.Companion = new Companion(null);
        Settings settings0 = new Settings();
        settings0.set(7, 0xFFFF);
        settings0.set(5, 0x4000);
        Http2Connection.DEFAULT_SETTINGS = settings0;
    }

    public Http2Connection(Builder http2Connection$Builder0) {
        Intrinsics.checkNotNullParameter(http2Connection$Builder0, "builder");
        super();
        boolean z = http2Connection$Builder0.getClient$okhttp();
        this.client = z;
        this.listener = http2Connection$Builder0.getListener$okhttp();
        this.streams = new LinkedHashMap();
        String s = http2Connection$Builder0.getConnectionName$okhttp();
        this.connectionName = s;
        this.nextStreamId = http2Connection$Builder0.getClient$okhttp() ? 3 : 2;
        TaskRunner taskRunner0 = http2Connection$Builder0.getTaskRunner$okhttp();
        this.taskRunner = taskRunner0;
        TaskQueue taskQueue0 = taskRunner0.newQueue();
        this.writerQueue = taskQueue0;
        this.pushQueue = taskRunner0.newQueue();
        this.settingsListenerQueue = taskRunner0.newQueue();
        this.pushObserver = http2Connection$Builder0.getPushObserver$okhttp();
        Settings settings0 = new Settings();
        if(http2Connection$Builder0.getClient$okhttp()) {
            settings0.set(7, 0x1000000);
        }
        this.okHttpSettings = settings0;
        this.peerSettings = Http2Connection.DEFAULT_SETTINGS;
        this.writeBytesMaximum = (long)Http2Connection.DEFAULT_SETTINGS.getInitialWindowSize();
        this.socket = http2Connection$Builder0.getSocket$okhttp();
        this.writer = new Http2Writer(http2Connection$Builder0.getSink$okhttp(), z);
        this.readerRunnable = new ReaderRunnable(this, new Http2Reader(http2Connection$Builder0.getSource$okhttp(), z));
        this.currentPushRequests = new LinkedHashSet();
        if(http2Connection$Builder0.getPingIntervalMillis$okhttp() != 0) {
            long v = TimeUnit.MILLISECONDS.toNanos(((long)http2Connection$Builder0.getPingIntervalMillis$okhttp()));
            taskQueue0.schedule(s + " ping", v, new Function0(v) {
                final long $pingIntervalNanos;

                {
                    Http2Connection.this = http2Connection0;
                    this.$pingIntervalNanos = v;
                    super(0);
                }

                public final Long invoke() {
                    boolean z;
                    Http2Connection http2Connection0 = Http2Connection.this;
                    synchronized(http2Connection0) {
                        if(Http2Connection.access$getIntervalPongsReceived$p(http2Connection0) < Http2Connection.access$getIntervalPingsSent$p(http2Connection0)) {
                            z = true;
                        }
                        else {
                            Http2Connection.access$setIntervalPingsSent$p(http2Connection0, Http2Connection.access$getIntervalPingsSent$p(http2Connection0) + 1L);
                            z = false;
                        }
                    }
                    if(z) {
                        Http2Connection.access$failConnection(Http2Connection.this, null);
                        return -1L;
                    }
                    Http2Connection.this.writePing(false, 1, 0);
                    return this.$pingIntervalNanos;
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            });
        }
    }

    public static final long access$getIntervalPingsSent$p(Http2Connection http2Connection0) {
        return http2Connection0.intervalPingsSent;
    }

    public static final void access$setIntervalPingsSent$p(Http2Connection http2Connection0, long v) {
        http2Connection0.intervalPingsSent = v;
    }

    public final void awaitPong() throws InterruptedException {
        synchronized(this) {
            while(this.awaitPongsReceived < this.awaitPingsSent) {
                Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                this.wait();
            }
        }
    }

    @Override
    public void close() {
        this.close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    public final void close$okhttp(ErrorCode errorCode0, ErrorCode errorCode1, IOException iOException0) {
        Object[] arr_object;
        Intrinsics.checkNotNullParameter(errorCode0, "connectionCode");
        Intrinsics.checkNotNullParameter(errorCode1, "streamCode");
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13541 MUST NOT hold lock on " + this);
        }
        try {
            this.shutdown(errorCode0);
        }
        catch(IOException unused_ex) {
        }
        synchronized(this) {
            if(this.streams.isEmpty()) {
                arr_object = null;
            }
            else {
                arr_object = this.streams.values().toArray(new Http2Stream[0]);
                Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                this.streams.clear();
            }
        }
        if(((Http2Stream[])arr_object) != null) {
            for(int v = 0; v < ((Http2Stream[])arr_object).length; ++v) {
                Http2Stream http2Stream0 = ((Http2Stream[])arr_object)[v];
                try {
                    http2Stream0.close(errorCode1, iOException0);
                }
                catch(IOException unused_ex) {
                }
            }
        }
        try {
            this.writer.close();
        }
        catch(IOException unused_ex) {
        }
        try {
            this.socket.close();
        }
        catch(IOException unused_ex) {
        }
        this.writerQueue.shutdown();
        this.pushQueue.shutdown();
        this.settingsListenerQueue.shutdown();
    }

    private final void failConnection(IOException iOException0) {
        this.close$okhttp(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR, iOException0);
    }

    public final void flush() throws IOException {
        this.writer.flush();
    }

    public final boolean getClient$okhttp() {
        return this.client;
    }

    public final String getConnectionName$okhttp() {
        return this.connectionName;
    }

    public final int getLastGoodStreamId$okhttp() {
        return this.lastGoodStreamId;
    }

    public final Listener getListener$okhttp() {
        return this.listener;
    }

    public final int getNextStreamId$okhttp() {
        return this.nextStreamId;
    }

    public final Settings getOkHttpSettings() {
        return this.okHttpSettings;
    }

    public final Settings getPeerSettings() {
        return this.peerSettings;
    }

    public final long getReadBytesAcknowledged() {
        return this.readBytesAcknowledged;
    }

    public final long getReadBytesTotal() {
        return this.readBytesTotal;
    }

    public final ReaderRunnable getReaderRunnable() {
        return this.readerRunnable;
    }

    public final Socket getSocket$okhttp() {
        return this.socket;
    }

    public final Http2Stream getStream(int v) {
        synchronized(this) {
            return (Http2Stream)this.streams.get(v);
        }
    }

    public final Map getStreams$okhttp() {
        return this.streams;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final Http2Writer getWriter() {
        return this.writer;
    }

    public final boolean isHealthy(long v) {
        synchronized(this) {
            if(this.isShutdown) {
                return false;
            }
            if(this.degradedPongsReceived < this.degradedPingsSent && v >= this.degradedPongDeadlineNs) {
                return false;
            }
        }
        return true;
    }

    private final Http2Stream newStream(int v, List list0, boolean z) throws IOException {
        Http2Stream http2Stream0;
        int v2;
        Throwable throwable1;
        synchronized(this.writer) {
            __monitor_enter(this);
            try {
                if(this.nextStreamId > 0x3FFFFFFF) {
                    this.shutdown(ErrorCode.REFUSED_STREAM);
                }
            }
            catch(Throwable throwable0) {
                throwable1 = throwable0;
                __monitor_exit(this);
                throw throwable1;
            }
            try {
                if(this.isShutdown) {
                    throw new ConnectionShutdownException();
                }
                v2 = this.nextStreamId;
                this.nextStreamId = v2 + 2;
                http2Stream0 = new Http2Stream(v2, this, !z, false, null);
                boolean z1 = !z || this.writeBytesTotal >= this.writeBytesMaximum || http2Stream0.getWriteBytesTotal() >= http2Stream0.getWriteBytesMaximum();
                if(http2Stream0.isOpen()) {
                    this.streams.put(v2, http2Stream0);
                }
            }
            catch(Throwable throwable2) {
                throwable1 = throwable2;
                __monitor_exit(this);
                throw throwable1;
            }
            __monitor_exit(this);
            boolean z2 = false;
            if(v == 0) {
                z2 = true;
                this.writer.headers(!z, v2, list0);
            }
            else if(!this.client) {
                z2 = true;
                this.writer.pushPromise(v, v2, list0);
            }
            if(z2) {
                if(z1) {
                    this.writer.flush();
                }
                return http2Stream0;
            }
        }
        throw new IllegalArgumentException("client streams shouldn\'t have associated stream IDs");
    }

    public final Http2Stream newStream(List list0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(list0, "requestHeaders");
        return this.newStream(0, list0, z);
    }

    public final int openStreamCount() {
        synchronized(this) {
        }
        return this.streams.size();
    }

    public final void pushDataLater$okhttp(int v, BufferedSource bufferedSource0, int v1, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        Buffer buffer0 = new Buffer();
        bufferedSource0.require(((long)v1));
        bufferedSource0.read(buffer0, ((long)v1));
        okhttp3.internal.http2.Http2Connection.pushDataLater.1 http2Connection$pushDataLater$10 = new Function0(v, buffer0, v1, z) {
            final Buffer $buffer;
            final int $byteCount;
            final boolean $inFinished;
            final int $streamId;

            {
                Http2Connection.this = http2Connection0;
                this.$streamId = v;
                this.$buffer = buffer0;
                this.$byteCount = v1;
                this.$inFinished = z;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                try {
                    Http2Connection http2Connection0 = Http2Connection.this;
                    int v = this.$streamId;
                    boolean z = this.$inFinished;
                    boolean z1 = http2Connection0.pushObserver.onData(v, this.$buffer, this.$byteCount, z);
                    if(z1) {
                        http2Connection0.getWriter().rstStream(v, ErrorCode.CANCEL);
                    }
                    if(z1 || z) {
                        synchronized(http2Connection0) {
                            http2Connection0.currentPushRequests.remove(v);
                        }
                    }
                }
                catch(IOException unused_ex) {
                }
            }
        };
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + v + "] onData", 0L, false, http2Connection$pushDataLater$10, 6, null);
    }

    public final void pushHeadersLater$okhttp(int v, List list0, boolean z) {
        Intrinsics.checkNotNullParameter(list0, "requestHeaders");
        okhttp3.internal.http2.Http2Connection.pushHeadersLater.1 http2Connection$pushHeadersLater$10 = new Function0(v, list0, z) {
            final boolean $inFinished;
            final List $requestHeaders;
            final int $streamId;

            {
                Http2Connection.this = http2Connection0;
                this.$streamId = v;
                this.$requestHeaders = list0;
                this.$inFinished = z;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                boolean z = Http2Connection.this.pushObserver.onHeaders(this.$streamId, this.$requestHeaders, this.$inFinished);
                Http2Connection http2Connection0 = Http2Connection.this;
                int v = this.$streamId;
                boolean z1 = this.$inFinished;
                try {
                    if(z) {
                        http2Connection0.getWriter().rstStream(v, ErrorCode.CANCEL);
                    }
                    if(z || z1) {
                        synchronized(http2Connection0) {
                            http2Connection0.currentPushRequests.remove(v);
                        }
                    }
                }
                catch(IOException unused_ex) {
                }
            }
        };
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + v + "] onHeaders", 0L, false, http2Connection$pushHeadersLater$10, 6, null);
    }

    public final void pushRequestLater$okhttp(int v, List list0) {
        Intrinsics.checkNotNullParameter(list0, "requestHeaders");
        synchronized(this) {
            if(this.currentPushRequests.contains(v)) {
                this.writeSynResetLater$okhttp(v, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(v);
        }
        okhttp3.internal.http2.Http2Connection.pushRequestLater.2 http2Connection$pushRequestLater$20 = new Function0(v, list0) {
            final List $requestHeaders;
            final int $streamId;

            {
                Http2Connection.this = http2Connection0;
                this.$streamId = v;
                this.$requestHeaders = list0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                boolean z = Http2Connection.this.pushObserver.onRequest(this.$streamId, this.$requestHeaders);
                Http2Connection http2Connection0 = Http2Connection.this;
                int v = this.$streamId;
                if(z) {
                    try {
                        http2Connection0.getWriter().rstStream(v, ErrorCode.CANCEL);
                        synchronized(http2Connection0) {
                            http2Connection0.currentPushRequests.remove(v);
                        }
                    }
                    catch(IOException unused_ex) {
                    }
                }
            }
        };
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + v + "] onRequest", 0L, false, http2Connection$pushRequestLater$20, 6, null);
    }

    public final void pushResetLater$okhttp(int v, ErrorCode errorCode0) {
        Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
        okhttp3.internal.http2.Http2Connection.pushResetLater.1 http2Connection$pushResetLater$10 = new Function0(v, errorCode0) {
            final ErrorCode $errorCode;
            final int $streamId;

            {
                Http2Connection.this = http2Connection0;
                this.$streamId = v;
                this.$errorCode = errorCode0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                Http2Connection.this.pushObserver.onReset(this.$streamId, this.$errorCode);
                synchronized(Http2Connection.this) {
                    Http2Connection.this.currentPushRequests.remove(this.$streamId);
                }
            }
        };
        TaskQueue.execute$default(this.pushQueue, this.connectionName + '[' + v + "] onReset", 0L, false, http2Connection$pushResetLater$10, 6, null);
    }

    public final Http2Stream pushStream(int v, List list0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(list0, "requestHeaders");
        if(this.client) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        return this.newStream(v, list0, z);
    }

    public final boolean pushedStream$okhttp(int v) {
        return v != 0 && (v & 1) == 0;
    }

    public final Http2Stream removeStream$okhttp(int v) {
        synchronized(this) {
            Http2Stream http2Stream0 = (Http2Stream)this.streams.remove(v);
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
            this.notifyAll();
            return http2Stream0;
        }
    }

    public final void sendDegradedPingLater$okhttp() {
        synchronized(this) {
            long v = this.degradedPingsSent;
            if(this.degradedPongsReceived < v) {
                return;
            }
            this.degradedPingsSent = v + 1L;
            this.degradedPongDeadlineNs = System.nanoTime() + 1000000000L;
        }
        okhttp3.internal.http2.Http2Connection.sendDegradedPingLater.2 http2Connection$sendDegradedPingLater$20 = new Function0() {
            {
                Http2Connection.this = http2Connection0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                Http2Connection.this.writePing(false, 2, 0);
            }
        };
        TaskQueue.execute$default(this.writerQueue, this.connectionName + " ping", 0L, false, http2Connection$sendDegradedPingLater$20, 6, null);
    }

    public final void setLastGoodStreamId$okhttp(int v) {
        this.lastGoodStreamId = v;
    }

    public final void setNextStreamId$okhttp(int v) {
        this.nextStreamId = v;
    }

    public final void setPeerSettings(Settings settings0) {
        Intrinsics.checkNotNullParameter(settings0, "<set-?>");
        this.peerSettings = settings0;
    }

    public final void setSettings(Settings settings0) throws IOException {
        Intrinsics.checkNotNullParameter(settings0, "settings");
        Http2Writer http2Writer0 = this.writer;
        __monitor_enter(http2Writer0);
        int v = FIN.finallyOpen$NT();
        synchronized(this) {
            if(!this.isShutdown) {
                this.okHttpSettings.merge(settings0);
                this.writer.settings(settings0);
                FIN.finallyCodeBegin$NT(v);
                __monitor_exit(http2Writer0);
                FIN.finallyCodeEnd$NT(v);
                return;
            }
        }
        throw new ConnectionShutdownException();
    }

    public final void shutdown(ErrorCode errorCode0) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode0, "statusCode");
        synchronized(this.writer) {
            IntRef ref$IntRef0 = new IntRef();
            synchronized(this) {
                if(this.isShutdown) {
                    return;
                }
                this.isShutdown = true;
                ref$IntRef0.element = this.lastGoodStreamId;
            }
            this.writer.goAway(ref$IntRef0.element, errorCode0, _UtilCommonKt.EMPTY_BYTE_ARRAY);
        }
    }

    public final void start() throws IOException {
        Http2Connection.start$default(this, false, 1, null);
    }

    public final void start(boolean z) throws IOException {
        if(z) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            int v = this.okHttpSettings.getInitialWindowSize();
            if(v != 0xFFFF) {
                this.writer.windowUpdate(0, ((long)(v - 0xFFFF)));
            }
        }
        TaskQueue.execute$default(this.taskRunner.newQueue(), this.connectionName, 0L, false, this.readerRunnable, 6, null);
    }

    public static void start$default(Http2Connection http2Connection0, boolean z, int v, Object object0) throws IOException {
        if((v & 1) != 0) {
            z = true;
        }
        http2Connection0.start(z);
    }

    public final void updateConnectionFlowControl$okhttp(long v) {
        synchronized(this) {
            long v2 = this.readBytesTotal + v;
            this.readBytesTotal = v2;
            long v3 = v2 - this.readBytesAcknowledged;
            if(v3 >= ((long)(this.okHttpSettings.getInitialWindowSize() / 2))) {
                this.writeWindowUpdateLater$okhttp(0, v3);
                this.readBytesAcknowledged += v3;
            }
        }
    }

    public final void writeData(int v, boolean z, Buffer buffer0, long v1) throws IOException {
        long v4;
        long v3;
        if(v1 == 0L) {
            this.writer.data(z, v, buffer0, 0);
            return;
        }
        while(v1 > 0L) {
            synchronized(this) {
                try {
                    while(true) {
                        v3 = this.writeBytesTotal;
                        v4 = this.writeBytesMaximum;
                        if(v3 < v4) {
                            break;
                        }
                        if(!this.streams.containsKey(v)) {
                            throw new IOException("stream closed");
                        }
                        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type java.lang.Object");
                        this.wait();
                    }
                }
                catch(InterruptedException unused_ex) {
                    Thread.currentThread().interrupt();
                    throw new InterruptedIOException();
                }
            }
            int v5 = Math.min(((int)Math.min(v1, v4 - v3)), this.writer.maxDataLength());
            this.writeBytesTotal += (long)v5;
            v1 -= (long)v5;
            this.writer.data(z && v1 == 0L, v, buffer0, v5);
        }
    }

    public final void writeHeaders$okhttp(int v, boolean z, List list0) throws IOException {
        Intrinsics.checkNotNullParameter(list0, "alternating");
        this.writer.headers(z, v, list0);
    }

    public final void writePing() throws InterruptedException {
        synchronized(this) {
            ++this.awaitPingsSent;
        }
        this.writePing(false, 3, 0x4F4B6F6B);
    }

    public final void writePing(boolean z, int v, int v1) {
        try {
            this.writer.ping(z, v, v1);
        }
        catch(IOException iOException0) {
            this.failConnection(iOException0);
        }
    }

    public final void writePingAndAwaitPong() throws InterruptedException {
        this.writePing();
        this.awaitPong();
    }

    public final void writeSynReset$okhttp(int v, ErrorCode errorCode0) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode0, "statusCode");
        this.writer.rstStream(v, errorCode0);
    }

    public final void writeSynResetLater$okhttp(int v, ErrorCode errorCode0) {
        Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
        okhttp3.internal.http2.Http2Connection.writeSynResetLater.1 http2Connection$writeSynResetLater$10 = new Function0(v, errorCode0) {
            final ErrorCode $errorCode;
            final int $streamId;

            {
                Http2Connection.this = http2Connection0;
                this.$streamId = v;
                this.$errorCode = errorCode0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                try {
                    Http2Connection.this.writeSynReset$okhttp(this.$streamId, this.$errorCode);
                }
                catch(IOException iOException0) {
                    Http2Connection.this.failConnection(iOException0);
                }
            }
        };
        TaskQueue.execute$default(this.writerQueue, this.connectionName + '[' + v + "] writeSynReset", 0L, false, http2Connection$writeSynResetLater$10, 6, null);
    }

    public final void writeWindowUpdateLater$okhttp(int v, long v1) {
        okhttp3.internal.http2.Http2Connection.writeWindowUpdateLater.1 http2Connection$writeWindowUpdateLater$10 = new Function0(v, v1) {
            final int $streamId;
            final long $unacknowledgedBytesRead;

            {
                Http2Connection.this = http2Connection0;
                this.$streamId = v;
                this.$unacknowledgedBytesRead = v1;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                try {
                    Http2Connection.this.getWriter().windowUpdate(this.$streamId, this.$unacknowledgedBytesRead);
                }
                catch(IOException iOException0) {
                    Http2Connection.this.failConnection(iOException0);
                }
            }
        };
        TaskQueue.execute$default(this.writerQueue, this.connectionName + '[' + v + "] windowUpdate", 0L, false, http2Connection$writeWindowUpdateLater$10, 6, null);
    }
}

