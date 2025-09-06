package okhttp3.internal.ws;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(d1 = {"\u0000\u00B6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001C\u0018\u0000 `2\u00020\u00012\u00020\u0002:\u0005_`abcB?\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u0006\u0010\t\u001A\u00020\n\u0012\u0006\u0010\u000B\u001A\u00020\f\u0012\b\u0010\r\u001A\u0004\u0018\u00010\u000E\u0012\u0006\u0010\u000F\u001A\u00020\f\u00A2\u0006\u0002\u0010\u0010J\u0016\u00102\u001A\u0002032\u0006\u00104\u001A\u00020\f2\u0006\u00105\u001A\u000206J\b\u00107\u001A\u000203H\u0016J\u001F\u00108\u001A\u0002032\u0006\u00109\u001A\u00020:2\b\u0010;\u001A\u0004\u0018\u00010<H\u0000\u00A2\u0006\u0002\b=J\u001A\u0010>\u001A\u00020\u00122\u0006\u0010?\u001A\u00020%2\b\u0010@\u001A\u0004\u0018\u00010\u0018H\u0016J \u0010>\u001A\u00020\u00122\u0006\u0010?\u001A\u00020%2\b\u0010@\u001A\u0004\u0018\u00010\u00182\u0006\u0010A\u001A\u00020\fJ\u000E\u0010B\u001A\u0002032\u0006\u0010C\u001A\u00020DJ\u001C\u0010E\u001A\u0002032\n\u0010F\u001A\u00060Gj\u0002`H2\b\u00109\u001A\u0004\u0018\u00010:J\u0016\u0010I\u001A\u0002032\u0006\u0010\u001E\u001A\u00020\u00182\u0006\u0010*\u001A\u00020+J\u0006\u0010J\u001A\u000203J\u0018\u0010K\u001A\u0002032\u0006\u0010?\u001A\u00020%2\u0006\u0010@\u001A\u00020\u0018H\u0016J\u0010\u0010L\u001A\u0002032\u0006\u0010M\u001A\u00020\u0018H\u0016J\u0010\u0010L\u001A\u0002032\u0006\u0010N\u001A\u00020 H\u0016J\u0010\u0010O\u001A\u0002032\u0006\u0010P\u001A\u00020 H\u0016J\u0010\u0010Q\u001A\u0002032\u0006\u0010P\u001A\u00020 H\u0016J\u000E\u0010R\u001A\u00020\u00122\u0006\u0010P\u001A\u00020 J\u0006\u0010S\u001A\u00020\u0012J\b\u0010!\u001A\u00020\fH\u0016J\u0006\u0010\'\u001A\u00020%J\u0006\u0010(\u001A\u00020%J\b\u0010T\u001A\u00020\u0006H\u0016J\b\u0010U\u001A\u000203H\u0002J\u0010\u0010V\u001A\u00020\u00122\u0006\u0010M\u001A\u00020\u0018H\u0016J\u0010\u0010V\u001A\u00020\u00122\u0006\u0010N\u001A\u00020 H\u0016J\u0018\u0010V\u001A\u00020\u00122\u0006\u0010W\u001A\u00020 2\u0006\u0010X\u001A\u00020%H\u0002J\u0006\u0010)\u001A\u00020%J\u0006\u0010Y\u001A\u000203J\r\u0010Z\u001A\u00020\u0012H\u0000\u00A2\u0006\u0002\b[J\r\u0010\\\u001A\u000203H\u0000\u00A2\u0006\u0002\b]J\f\u0010^\u001A\u00020\u0012*\u00020\u000EH\u0002R\u000E\u0010\u0011\u001A\u00020\u0012X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001A\u0004\u0018\u00010\u0014X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0012X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u0012X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0017\u001A\u00020\u0018X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001A\u00020\bX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001AR\u0014\u0010\u001B\u001A\b\u0012\u0004\u0012\u00020\u001D0\u001CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\fX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001E\u001A\u0004\u0018\u00010\u0018X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001F\u001A\b\u0012\u0004\u0012\u00020 0\u001CX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010!\u001A\u00020\fX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\"\u001A\u0004\u0018\u00010#X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010$\u001A\u00020%X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010&\u001A\u0004\u0018\u00010\u0018X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\'\u001A\u00020%X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010(\u001A\u00020%X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010)\u001A\u00020%X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010*\u001A\u0004\u0018\u00010+X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010,\u001A\u00020-X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010.\u001A\u0004\u0018\u00010/X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u00100\u001A\u0004\u0018\u000101X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006d"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "originalRequest", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "random", "Ljava/util/Random;", "pingIntervalMillis", "", "extensions", "Lokhttp3/internal/ws/WebSocketExtensions;", "minimumDeflateSize", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;JLokhttp3/internal/ws/WebSocketExtensions;J)V", "awaitingPong", "", "call", "Lokhttp3/Call;", "enqueuedClose", "failed", "key", "", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", "messageAndCloseQueue", "Ljava/util/ArrayDeque;", "", "name", "pongQueue", "Lokio/ByteString;", "queueSize", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "receivedCloseCode", "", "receivedCloseReason", "receivedPingCount", "receivedPongCount", "sentPingCount", "streams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "taskQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "writerTask", "Lokhttp3/internal/concurrent/Task;", "awaitTermination", "", "timeout", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "cancel", "checkUpgradeSuccess", "response", "Lokhttp3/Response;", "exchange", "Lokhttp3/internal/connection/Exchange;", "checkUpgradeSuccess$okhttp", "close", "code", "reason", "cancelAfterCloseMillis", "connect", "client", "Lokhttp3/OkHttpClient;", "failWebSocket", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "initReaderAndWriter", "loopReader", "onReadClose", "onReadMessage", "text", "bytes", "onReadPing", "payload", "onReadPong", "pong", "processNextFrame", "request", "runWriter", "send", "data", "formatOpcode", "tearDown", "writeOneFrame", "writeOneFrame$okhttp", "writePingFrame", "writePingFrame$okhttp", "isValid", "Close", "Companion", "Message", "Streams", "WriterTask", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RealWebSocket implements WebSocket, FrameCallback {
    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001F\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u000F"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Close;", "", "code", "", "reason", "Lokio/ByteString;", "cancelAfterCloseMillis", "", "(ILokio/ByteString;J)V", "getCancelAfterCloseMillis", "()J", "getCode", "()I", "getReason", "()Lokio/ByteString;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Close {
        private final long cancelAfterCloseMillis;
        private final int code;
        private final ByteString reason;

        public Close(int v, ByteString byteString0, long v1) {
            this.code = v;
            this.reason = byteString0;
            this.cancelAfterCloseMillis = v1;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }

        public final int getCode() {
            return this.code;
        }

        public final ByteString getReason() {
            return this.reason;
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Companion;", "", "()V", "CANCEL_AFTER_CLOSE_MILLIS", "", "DEFAULT_MINIMUM_DEFLATE_SIZE", "MAX_QUEUE_SIZE", "ONLY_HTTP1", "", "Lokhttp3/Protocol;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u000B"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Message;", "", "formatOpcode", "", "data", "Lokio/ByteString;", "(ILokio/ByteString;)V", "getData", "()Lokio/ByteString;", "getFormatOpcode", "()I", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Message {
        private final ByteString data;
        private final int formatOpcode;

        public Message(int v, ByteString byteString0) {
            Intrinsics.checkNotNullParameter(byteString0, "data");
            super();
            this.formatOpcode = v;
            this.data = byteString0;
        }

        public final ByteString getData() {
            return this.data;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u000F"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Streams;", "Ljava/io/Closeable;", "client", "", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(ZLokio/BufferedSource;Lokio/BufferedSink;)V", "getClient", "()Z", "getSink", "()Lokio/BufferedSink;", "getSource", "()Lokio/BufferedSource;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static abstract class Streams implements Closeable {
        private final boolean client;
        private final BufferedSink sink;
        private final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource0, BufferedSink bufferedSink0) {
            Intrinsics.checkNotNullParameter(bufferedSource0, "source");
            Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
            super();
            this.client = z;
            this.source = bufferedSource0;
            this.sink = bufferedSink0;
        }

        public final boolean getClient() {
            return this.client;
        }

        public final BufferedSink getSink() {
            return this.sink;
        }

        public final BufferedSource getSource() {
            return this.source;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$WriterTask;", "Lokhttp3/internal/concurrent/Task;", "(Lokhttp3/internal/ws/RealWebSocket;)V", "runOnce", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class WriterTask extends Task {
        public WriterTask() {
            super(realWebSocket0.name + " writer", false, 2, null);
        }

        @Override  // okhttp3.internal.concurrent.Task
        public long runOnce() {
            try {
                if(RealWebSocket.this.writeOneFrame$okhttp()) {
                    return 0L;
                }
            }
            catch(IOException iOException0) {
                RealWebSocket.this.failWebSocket(iOException0, null);
            }
            return -1L;
        }
    }

    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000L;
    public static final Companion Companion = null;
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 0x400L;
    private static final long MAX_QUEUE_SIZE = 0x1000000L;
    private static final List ONLY_HTTP1;
    private boolean awaitingPong;
    private Call call;
    private boolean enqueuedClose;
    private WebSocketExtensions extensions;
    private boolean failed;
    private final String key;
    private final WebSocketListener listener;
    private final ArrayDeque messageAndCloseQueue;
    private long minimumDeflateSize;
    private String name;
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque pongQueue;
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private TaskQueue taskQueue;
    private WebSocketWriter writer;
    private Task writerTask;

    static {
        RealWebSocket.Companion = new Companion(null);
        RealWebSocket.ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
    }

    public RealWebSocket(TaskRunner taskRunner0, Request request0, WebSocketListener webSocketListener0, Random random0, long v, WebSocketExtensions webSocketExtensions0, long v1) {
        Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
        Intrinsics.checkNotNullParameter(request0, "originalRequest");
        Intrinsics.checkNotNullParameter(webSocketListener0, "listener");
        Intrinsics.checkNotNullParameter(random0, "random");
        super();
        this.originalRequest = request0;
        this.listener = webSocketListener0;
        this.random = random0;
        this.pingIntervalMillis = v;
        this.extensions = webSocketExtensions0;
        this.minimumDeflateSize = v1;
        this.taskQueue = taskRunner0.newQueue();
        this.pongQueue = new ArrayDeque();
        this.messageAndCloseQueue = new ArrayDeque();
        this.receivedCloseCode = -1;
        if(!Intrinsics.areEqual("GET", request0.method())) {
            throw new IllegalArgumentException(("Request must be GET: " + request0.method()).toString());
        }
        byte[] arr_b = new byte[16];
        random0.nextBytes(arr_b);
        this.key = okio.ByteString.Companion.of$default(ByteString.Companion, arr_b, 0, 0, 3, null).base64();
    }

    public final void awaitTermination(long v, TimeUnit timeUnit0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
        this.taskQueue.idleLatch().await(v, timeUnit0);
    }

    @Override  // okhttp3.WebSocket
    public void cancel() {
        Call call0 = this.call;
        Intrinsics.checkNotNull(call0);
        call0.cancel();
    }

    public final void checkUpgradeSuccess$okhttp(Response response0, Exchange exchange0) throws IOException {
        Intrinsics.checkNotNullParameter(response0, "response");
        if(response0.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was \'" + response0.code() + ' ' + response0.message() + '\'');
        }
        String s = Response.header$default(response0, "Connection", null, 2, null);
        if(!StringsKt.equals("Upgrade", s, true)) {
            throw new ProtocolException("Expected \'Connection\' header value \'Upgrade\' but was \'" + s + '\'');
        }
        String s1 = Response.header$default(response0, "Upgrade", null, 2, null);
        if(!StringsKt.equals("websocket", s1, true)) {
            throw new ProtocolException("Expected \'Upgrade\' header value \'websocket\' but was \'" + s1 + '\'');
        }
        String s2 = Response.header$default(response0, "Sec-WebSocket-Accept", null, 2, null);
        String s3 = ByteString.Companion.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
        if(!Intrinsics.areEqual(s3, s2)) {
            throw new ProtocolException("Expected \'Sec-WebSocket-Accept\' header value \'" + s3 + "\' but was \'" + s2 + '\'');
        }
        if(exchange0 == null) {
            throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
        }
    }

    @Override  // okhttp3.WebSocket
    public boolean close(int v, String s) {
        return this.close(v, s, 60000L);
    }

    public final boolean close(int v, String s, long v1) {
        ByteString byteString0;
        synchronized(this) {
            WebSocketProtocol.INSTANCE.validateCloseCode(v);
            if(s == null) {
                byteString0 = null;
            }
            else {
                byteString0 = ByteString.Companion.encodeUtf8(s);
                if(((long)byteString0.size()) > 0x7BL) {
                    throw new IllegalArgumentException(("reason.size() > 123: " + s).toString());
                }
            }
            if(!this.failed && !this.enqueuedClose) {
                this.enqueuedClose = true;
                Close realWebSocket$Close0 = new Close(v, byteString0, v1);
                this.messageAndCloseQueue.add(realWebSocket$Close0);
                this.runWriter();
                return true;
            }
            return false;
        }
    }

    public final void connect(OkHttpClient okHttpClient0) {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        if(this.originalRequest.header("Sec-WebSocket-Extensions") != null) {
            this.failWebSocket(new ProtocolException("Request header not permitted: \'Sec-WebSocket-Extensions\'"), null);
            return;
        }
        OkHttpClient okHttpClient1 = okHttpClient0.newBuilder().eventListener(EventListener.NONE).protocols(RealWebSocket.ONLY_HTTP1).build();
        Request request0 = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build();
        RealCall realCall0 = new RealCall(okHttpClient1, request0, true);
        this.call = realCall0;
        Intrinsics.checkNotNull(realCall0);
        realCall0.enqueue(new Callback() {
            @Override  // okhttp3.Callback
            public void onFailure(Call call0, IOException iOException0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(iOException0, "e");
                request0.failWebSocket(iOException0, null);
            }

            @Override  // okhttp3.Callback
            public void onResponse(Call call0, Response response0) {
                Streams realWebSocket$Streams0;
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(response0, "response");
                Exchange exchange0 = response0.exchange();
                try {
                    request0.checkUpgradeSuccess$okhttp(response0, exchange0);
                    Intrinsics.checkNotNull(exchange0);
                    realWebSocket$Streams0 = exchange0.newWebSocketStreams();
                }
                catch(IOException iOException0) {
                    if(exchange0 != null) {
                        exchange0.webSocketUpgradeFailed();
                    }
                    request0.failWebSocket(iOException0, response0);
                    _UtilCommonKt.closeQuietly(response0);
                    return;
                }
                WebSocketExtensions webSocketExtensions0 = WebSocketExtensions.Companion.parse(response0.headers());
                request0.extensions = webSocketExtensions0;
                if(!request0.isValid(webSocketExtensions0)) {
                    synchronized(request0) {
                        request0.messageAndCloseQueue.clear();
                        request0.close(1010, "unexpected Sec-WebSocket-Extensions in response header");
                    }
                }
                try {
                    request0.initReaderAndWriter(_UtilJvmKt.okHttpName + " WebSocket " + this.$request.url().redact(), realWebSocket$Streams0);
                    request0.getListener$okhttp().onOpen(request0, response0);
                    request0.loopReader();
                }
                catch(Exception exception0) {
                    request0.failWebSocket(exception0, null);
                }
            }
        });
    }

    public final void failWebSocket(Exception exception0, Response response0) {
        WebSocketWriter webSocketWriter0;
        WebSocketReader webSocketReader0;
        Streams realWebSocket$Streams0;
        Intrinsics.checkNotNullParameter(exception0, "e");
        synchronized(this) {
            if(this.failed) {
                return;
            }
            this.failed = true;
            realWebSocket$Streams0 = this.streams;
            this.streams = null;
            webSocketReader0 = this.reader;
            this.reader = null;
            webSocketWriter0 = this.writer;
            this.writer = null;
            this.taskQueue.shutdown();
        }
        try {
            this.listener.onFailure(this, exception0, response0);
        }
        catch(Throwable throwable0) {
            if(realWebSocket$Streams0 != null) {
                _UtilCommonKt.closeQuietly(realWebSocket$Streams0);
            }
            if(webSocketReader0 != null) {
                _UtilCommonKt.closeQuietly(webSocketReader0);
            }
            if(webSocketWriter0 != null) {
                _UtilCommonKt.closeQuietly(webSocketWriter0);
            }
            throw throwable0;
        }
        if(realWebSocket$Streams0 != null) {
            _UtilCommonKt.closeQuietly(realWebSocket$Streams0);
        }
        if(webSocketReader0 != null) {
            _UtilCommonKt.closeQuietly(webSocketReader0);
        }
        if(webSocketWriter0 != null) {
            _UtilCommonKt.closeQuietly(webSocketWriter0);
        }
    }

    public final WebSocketListener getListener$okhttp() {
        return this.listener;
    }

    public final void initReaderAndWriter(String s, Streams realWebSocket$Streams0) throws IOException {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(realWebSocket$Streams0, "streams");
        WebSocketExtensions webSocketExtensions0 = this.extensions;
        Intrinsics.checkNotNull(webSocketExtensions0);
        synchronized(this) {
            this.name = s;
            this.streams = realWebSocket$Streams0;
            boolean z = webSocketExtensions0.noContextTakeover(realWebSocket$Streams0.getClient());
            this.writer = new WebSocketWriter(realWebSocket$Streams0.getClient(), realWebSocket$Streams0.getSink(), this.random, webSocketExtensions0.perMessageDeflate, z, this.minimumDeflateSize);
            this.writerTask = new WriterTask(this);
            if(this.pingIntervalMillis != 0L) {
                long v1 = TimeUnit.MILLISECONDS.toNanos(this.pingIntervalMillis);
                this.taskQueue.schedule(s + " ping", v1, new Function0(v1) {
                    final long $pingIntervalNanos;

                    {
                        RealWebSocket.this = realWebSocket0;
                        this.$pingIntervalNanos = v;
                        super(0);
                    }

                    public final Long invoke() {
                        RealWebSocket.this.writePingFrame$okhttp();
                        return this.$pingIntervalNanos;
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return this.invoke();
                    }
                });
            }
            if(!this.messageAndCloseQueue.isEmpty()) {
                this.runWriter();
            }
        }
        boolean z1 = webSocketExtensions0.noContextTakeover(!realWebSocket$Streams0.getClient());
        this.reader = new WebSocketReader(realWebSocket$Streams0.getClient(), realWebSocket$Streams0.getSource(), this, webSocketExtensions0.perMessageDeflate, z1);
    }

    private final boolean isValid(WebSocketExtensions webSocketExtensions0) {
        if(webSocketExtensions0.unknownValues) {
            return false;
        }
        return webSocketExtensions0.clientMaxWindowBits == null ? webSocketExtensions0.serverMaxWindowBits == null || new IntRange(8, 15).contains(((int)webSocketExtensions0.serverMaxWindowBits)) : false;
    }

    public final void loopReader() throws IOException {
        while(this.receivedCloseCode == -1) {
            WebSocketReader webSocketReader0 = this.reader;
            Intrinsics.checkNotNull(webSocketReader0);
            webSocketReader0.processNextFrame();
        }
    }

    @Override  // okhttp3.internal.ws.WebSocketReader$FrameCallback
    public void onReadClose(int v, String s) {
        WebSocketWriter webSocketWriter0;
        WebSocketReader webSocketReader0;
        Intrinsics.checkNotNullParameter(s, "reason");
        if(v == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        synchronized(this) {
            if(this.receivedCloseCode == -1) {
                this.receivedCloseCode = v;
                this.receivedCloseReason = s;
                Streams realWebSocket$Streams0 = null;
                if(!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                    webSocketReader0 = null;
                    webSocketWriter0 = null;
                }
                else {
                    Streams realWebSocket$Streams1 = this.streams;
                    this.streams = null;
                    webSocketReader0 = this.reader;
                    this.reader = null;
                    webSocketWriter0 = this.writer;
                    this.writer = null;
                    this.taskQueue.shutdown();
                    realWebSocket$Streams0 = realWebSocket$Streams1;
                }
                try {
                    this.listener.onClosing(this, v, s);
                    if(realWebSocket$Streams0 != null) {
                        this.listener.onClosed(this, v, s);
                    }
                }
                finally {
                    if(realWebSocket$Streams0 != null) {
                        _UtilCommonKt.closeQuietly(realWebSocket$Streams0);
                    }
                    if(webSocketReader0 != null) {
                        _UtilCommonKt.closeQuietly(webSocketReader0);
                    }
                    if(webSocketWriter0 != null) {
                        _UtilCommonKt.closeQuietly(webSocketWriter0);
                    }
                }
                return;
            }
        }
        throw new IllegalStateException("already closed");
    }

    @Override  // okhttp3.internal.ws.WebSocketReader$FrameCallback
    public void onReadMessage(String s) throws IOException {
        Intrinsics.checkNotNullParameter(s, "text");
        this.listener.onMessage(this, s);
    }

    @Override  // okhttp3.internal.ws.WebSocketReader$FrameCallback
    public void onReadMessage(ByteString byteString0) throws IOException {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        this.listener.onMessage(this, byteString0);
    }

    @Override  // okhttp3.internal.ws.WebSocketReader$FrameCallback
    public void onReadPing(ByteString byteString0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(byteString0, "payload");
            if(!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(byteString0);
                this.runWriter();
                ++this.receivedPingCount;
            }
        }
    }

    @Override  // okhttp3.internal.ws.WebSocketReader$FrameCallback
    public void onReadPong(ByteString byteString0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(byteString0, "payload");
            ++this.receivedPongCount;
            this.awaitingPong = false;
        }
    }

    public final boolean pong(ByteString byteString0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(byteString0, "payload");
            if(!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(byteString0);
                this.runWriter();
                return true;
            }
            return false;
        }
    }

    public final boolean processNextFrame() throws IOException {
        try {
            WebSocketReader webSocketReader0 = this.reader;
            Intrinsics.checkNotNull(webSocketReader0);
            webSocketReader0.processNextFrame();
            return this.receivedCloseCode == -1;
        }
        catch(Exception exception0) {
            this.failWebSocket(exception0, null);
            return false;
        }
    }

    @Override  // okhttp3.WebSocket
    public long queueSize() {
        synchronized(this) {
        }
        return this.queueSize;
    }

    public final int receivedPingCount() {
        synchronized(this) {
        }
        return this.receivedPingCount;
    }

    public final int receivedPongCount() {
        synchronized(this) {
        }
        return this.receivedPongCount;
    }

    @Override  // okhttp3.WebSocket
    public Request request() {
        return this.originalRequest;
    }

    private final void runWriter() {
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13304 MUST hold lock on " + this);
        }
        Task task0 = this.writerTask;
        if(task0 != null) {
            TaskQueue.schedule$default(this.taskQueue, task0, 0L, 2, null);
        }
    }

    private final boolean send(ByteString byteString0, int v) {
        synchronized(this) {
            if(!this.failed && !this.enqueuedClose) {
                if(this.queueSize + ((long)byteString0.size()) > 0x1000000L) {
                    this.close(1001, null);
                    return false;
                }
                this.queueSize += (long)byteString0.size();
                Message realWebSocket$Message0 = new Message(v, byteString0);
                this.messageAndCloseQueue.add(realWebSocket$Message0);
                this.runWriter();
                return true;
            }
            return false;
        }
    }

    @Override  // okhttp3.WebSocket
    public boolean send(String s) {
        Intrinsics.checkNotNullParameter(s, "text");
        return this.send(ByteString.Companion.encodeUtf8(s), 1);
    }

    @Override  // okhttp3.WebSocket
    public boolean send(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        return this.send(byteString0, 2);
    }

    public final int sentPingCount() {
        synchronized(this) {
        }
        return this.sentPingCount;
    }

    public final void tearDown() throws InterruptedException {
        this.taskQueue.shutdown();
        this.taskQueue.idleLatch().await(10L, TimeUnit.SECONDS);
    }

    public final boolean writeOneFrame$okhttp() throws IOException {
        Object object1;
        Object object0;
        WebSocketWriter webSocketWriter0;
        WebSocketWriter webSocketWriter1;
        WebSocketReader webSocketReader0;
        Streams realWebSocket$Streams0;
        String s;
        int v1;
        synchronized(this) {
            if(this.failed) {
                return false;
            }
            webSocketWriter0 = this.writer;
            object0 = this.pongQueue.poll();
            object1 = null;
            if(object0 == null) {
                Object object2 = this.messageAndCloseQueue.poll();
                if(object2 instanceof Close) {
                    v1 = this.receivedCloseCode;
                    s = this.receivedCloseReason;
                    if(v1 == -1) {
                        long v2 = ((Close)object2).getCancelAfterCloseMillis();
                        TaskQueue.execute$default(this.taskQueue, this.name + " cancel", TimeUnit.MILLISECONDS.toNanos(v2), false, new Function0() {
                            {
                                RealWebSocket.this = realWebSocket0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                this.invoke();
                                return Unit.INSTANCE;
                            }

                            public final void invoke() {
                                RealWebSocket.this.cancel();
                            }
                        }, 4, null);
                        realWebSocket$Streams0 = null;
                        webSocketReader0 = null;
                        webSocketWriter1 = null;
                    }
                    else {
                        realWebSocket$Streams0 = this.streams;
                        this.streams = null;
                        webSocketReader0 = this.reader;
                        this.reader = null;
                        webSocketWriter1 = this.writer;
                        this.writer = null;
                        this.taskQueue.shutdown();
                    }
                    object1 = object2;
                }
                else {
                    if(object2 == null) {
                        return false;
                    }
                    realWebSocket$Streams0 = null;
                    s = null;
                    webSocketReader0 = null;
                    webSocketWriter1 = null;
                    object1 = object2;
                    v1 = -1;
                }
            }
            else {
                realWebSocket$Streams0 = null;
                s = null;
                webSocketReader0 = null;
                webSocketWriter1 = null;
                v1 = -1;
            }
        }
        try {
            if(object0 != null) {
                Intrinsics.checkNotNull(webSocketWriter0);
                webSocketWriter0.writePong(((ByteString)object0));
                return true;
            }
            if(object1 instanceof Message) {
                Intrinsics.checkNotNull(webSocketWriter0);
                webSocketWriter0.writeMessageFrame(((Message)object1).getFormatOpcode(), ((Message)object1).getData());
                synchronized(this) {
                    this.queueSize -= (long)((Message)object1).getData().size();
                    return true;
                }
            }
            if(object1 instanceof Close) {
                Intrinsics.checkNotNull(webSocketWriter0);
                webSocketWriter0.writeClose(((Close)object1).getCode(), ((Close)object1).getReason());
                if(realWebSocket$Streams0 != null) {
                    Intrinsics.checkNotNull(s);
                    this.listener.onClosed(this, v1, s);
                }
                return true;
            }
        }
        finally {
            if(realWebSocket$Streams0 != null) {
                _UtilCommonKt.closeQuietly(realWebSocket$Streams0);
            }
            if(webSocketReader0 != null) {
                _UtilCommonKt.closeQuietly(webSocketReader0);
            }
            if(webSocketWriter1 != null) {
                _UtilCommonKt.closeQuietly(webSocketWriter1);
            }
        }
        throw new AssertionError();
    }

    public final void writePingFrame$okhttp() {
        int v;
        WebSocketWriter webSocketWriter0;
        synchronized(this) {
            if(this.failed) {
                return;
            }
            webSocketWriter0 = this.writer;
            if(webSocketWriter0 == null) {
                return;
            }
            v = this.awaitingPong ? this.sentPingCount : -1;
            ++this.sentPingCount;
            this.awaitingPong = true;
        }
        if(v != -1) {
            this.failWebSocket(new SocketTimeoutException("sent ping but didn\'t receive pong within " + this.pingIntervalMillis + "ms (after " + (v - 1) + " successful ping/pongs)"), null);
            return;
        }
        try {
            webSocketWriter0.writePing(ByteString.EMPTY);
        }
        catch(IOException iOException0) {
            this.failWebSocket(iOException0, null);
        }
    }
}

