package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLSocketFactory;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import okio.Timeout;

@Metadata(d1 = {"\u0000\u00B3\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001A\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u00010\u0018\u00002\u00020\u00012\u00020\u0002:\u0002fgB\u001D\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u00A2\u0006\u0002\u0010\tJ\u000E\u00103\u001A\u0002042\u0006\u0010\u0011\u001A\u00020\u0010J!\u00105\u001A\u0002H6\"\n\b\u0000\u00106*\u0004\u0018\u0001072\u0006\u00108\u001A\u0002H6H\u0002\u00A2\u0006\u0002\u00109J\b\u0010:\u001A\u000204H\u0002J\b\u0010;\u001A\u000204H\u0016J\b\u0010<\u001A\u00020\u0001H\u0016J\u0010\u0010=\u001A\u00020>2\u0006\u0010?\u001A\u00020@H\u0002J\u0010\u0010A\u001A\u0002042\u0006\u0010B\u001A\u00020CH\u0016J\u001E\u0010D\u001A\u0002042\u0006\u0010E\u001A\u00020\u00062\u0006\u0010F\u001A\u00020\b2\u0006\u0010G\u001A\u00020HJ\b\u0010I\u001A\u00020JH\u0016J\u0015\u0010K\u001A\u0002042\u0006\u0010L\u001A\u00020\bH\u0000\u00A2\u0006\u0002\bMJ\r\u0010N\u001A\u00020JH\u0000\u00A2\u0006\u0002\bOJ\u0015\u0010P\u001A\u00020\u001B2\u0006\u0010G\u001A\u00020HH\u0000\u00A2\u0006\u0002\bQJ\b\u0010R\u001A\u00020\bH\u0016J\b\u0010S\u001A\u00020\bH\u0016J;\u0010T\u001A\u0002H6\"\n\b\u0000\u00106*\u0004\u0018\u0001072\u0006\u0010\u001A\u001A\u00020\u001B2\u0006\u0010U\u001A\u00020\b2\u0006\u0010V\u001A\u00020\b2\u0006\u00108\u001A\u0002H6H\u0000\u00A2\u0006\u0004\bW\u0010XJ\u0019\u0010Y\u001A\u0004\u0018\u0001072\b\u00108\u001A\u0004\u0018\u000107H\u0000\u00A2\u0006\u0002\bZJ\r\u0010[\u001A\u00020\\H\u0000\u00A2\u0006\u0002\b]J\u000F\u0010^\u001A\u0004\u0018\u00010_H\u0000\u00A2\u0006\u0002\b`J\b\u0010E\u001A\u00020\u0006H\u0016J\u0006\u0010a\u001A\u00020\bJ\b\u0010/\u001A\u00020bH\u0016J\u0006\u00102\u001A\u000204J!\u0010c\u001A\u0002H6\"\n\b\u0000\u00106*\u0004\u0018\u0001072\u0006\u0010d\u001A\u0002H6H\u0002\u00A2\u0006\u0002\u00109J\b\u0010e\u001A\u00020\\H\u0002R\u0010\u0010\n\u001A\u0004\u0018\u00010\u000BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\"\u0010\u0011\u001A\u0004\u0018\u00010\u00102\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010@BX\u0086\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013R\u000E\u0010\u0014\u001A\u00020\u0015X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001A\u00020\u0017X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001A\u001A\u0004\u0018\u00010\u001BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001C\u001A\u0004\u0018\u00010\u001DX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001E\u001A\u00020\u001FX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010 \u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001A\u00020\b\u00A2\u0006\b\n\u0000\u001A\u0004\b!\u0010\"R\"\u0010#\u001A\u0004\u0018\u00010\u001B2\b\u0010\u000F\u001A\u0004\u0018\u00010\u001B@BX\u0080\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b$\u0010%R\u0011\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\b\n\u0000\u001A\u0004\b&\u0010\'R\u001A\u0010(\u001A\b\u0012\u0004\u0012\u00020*0)X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b+\u0010,R\u000E\u0010-\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010.\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010/\u001A\u000200X\u0082\u0004\u00A2\u0006\u0004\n\u0002\u00101R\u000E\u00102\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006h"}, d2 = {"Lokhttp3/internal/connection/RealCall;", "Lokhttp3/Call;", "", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "callStackTrace", "", "canceled", "getClient", "()Lokhttp3/OkHttpClient;", "<set-?>", "Lokhttp3/internal/connection/RealConnection;", "connection", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "eventListener", "Lokhttp3/EventListener;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "exchange", "Lokhttp3/internal/connection/Exchange;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "executed", "Ljava/util/concurrent/atomic/AtomicBoolean;", "expectMoreExchanges", "getForWebSocket", "()Z", "interceptorScopedExchange", "getInterceptorScopedExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getOriginalRequest", "()Lokhttp3/Request;", "plansToCancel", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "getPlansToCancel$okhttp", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "requestBodyOpen", "responseBodyOpen", "timeout", "okhttp3/internal/connection/RealCall$timeout$1", "Lokhttp3/internal/connection/RealCall$timeout$1;", "timeoutEarlyExit", "acquireConnectionNoEvents", "", "callDone", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "callStart", "cancel", "clone", "createAddress", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "enqueue", "responseCallback", "Lokhttp3/Callback;", "enterNetworkInterceptorExchange", "request", "newRoutePlanner", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "execute", "Lokhttp3/Response;", "exitNetworkInterceptorExchange", "closeExchange", "exitNetworkInterceptorExchange$okhttp", "getResponseWithInterceptorChain", "getResponseWithInterceptorChain$okhttp", "initExchange", "initExchange$okhttp", "isCanceled", "isExecuted", "messageDone", "requestDone", "responseDone", "messageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "noMoreExchanges", "noMoreExchanges$okhttp", "redactedUrl", "", "redactedUrl$okhttp", "releaseConnectionNoEvents", "Ljava/net/Socket;", "releaseConnectionNoEvents$okhttp", "retryAfterFailure", "Lokio/Timeout;", "timeoutExit", "cause", "toLoggableString", "AsyncCall", "CallReference", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RealCall implements Cloneable, Call {
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000E\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u0019J\u0019\u0010\u001A\u001A\u00020\u00172\n\b\u0002\u0010\u001B\u001A\u0004\u0018\u00010\u001CH\u0000¢\u0006\u0002\b\u001DJ\u0012\u0010\u001E\u001A\u00020\u00172\n\u0010\u001F\u001A\u00060\u0000R\u00020\u0006J\b\u0010 \u001A\u00020\u0017H\u0016R\u0011\u0010\u0005\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\u0007\u0010\bR\u001E\u0010\u000B\u001A\u00020\n2\u0006\u0010\t\u001A\u00020\n@BX\u0086\u000E¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u0011\u0010\u000E\u001A\u00020\u000F8F¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001A\u00020\u00138F¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lokhttp3/internal/connection/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/Callback;)V", "call", "Lokhttp3/internal/connection/RealCall;", "getCall", "()Lokhttp3/internal/connection/RealCall;", "<set-?>", "Ljava/util/concurrent/atomic/AtomicInteger;", "callsPerHost", "getCallsPerHost", "()Ljava/util/concurrent/atomic/AtomicInteger;", "host", "", "getHost", "()Ljava/lang/String;", "request", "Lokhttp3/Request;", "getRequest", "()Lokhttp3/Request;", "executeOn", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "failRejected", "e", "Ljava/util/concurrent/RejectedExecutionException;", "failRejected$okhttp", "reuseCallsPerHostFrom", "other", "run", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class AsyncCall implements Runnable {
        private volatile AtomicInteger callsPerHost;
        private final Callback responseCallback;

        public AsyncCall(Callback callback0) {
            Intrinsics.checkNotNullParameter(callback0, "responseCallback");
            RealCall.this = realCall0;
            super();
            this.responseCallback = callback0;
            this.callsPerHost = new AtomicInteger(0);
        }

        public final void executeOn(ExecutorService executorService0) {
            Intrinsics.checkNotNullParameter(executorService0, "executorService");
            Dispatcher dispatcher0 = RealCall.this.getClient().dispatcher();
            if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(dispatcher0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13443 MUST NOT hold lock on " + dispatcher0);
            }
            try {
                executorService0.execute(this);
            }
            catch(RejectedExecutionException rejectedExecutionException0) {
                this.failRejected$okhttp(rejectedExecutionException0);
            }
            finally {
                RealCall.this.getClient().dispatcher().finished$okhttp(this);
            }
        }

        public final void failRejected$okhttp(RejectedExecutionException rejectedExecutionException0) {
            InterruptedIOException interruptedIOException0 = new InterruptedIOException("executor rejected");
            interruptedIOException0.initCause(rejectedExecutionException0);
            RealCall.this.noMoreExchanges$okhttp(interruptedIOException0);
            this.responseCallback.onFailure(RealCall.this, interruptedIOException0);
        }

        public static void failRejected$okhttp$default(AsyncCall realCall$AsyncCall0, RejectedExecutionException rejectedExecutionException0, int v, Object object0) {
            if((v & 1) != 0) {
                rejectedExecutionException0 = null;
            }
            realCall$AsyncCall0.failRejected$okhttp(rejectedExecutionException0);
        }

        public final RealCall getCall() {
            return RealCall.this;
        }

        public final AtomicInteger getCallsPerHost() {
            return this.callsPerHost;
        }

        public final String getHost() {
            return RealCall.this.getOriginalRequest().url().host();
        }

        public final Request getRequest() {
            return RealCall.this.getOriginalRequest();
        }

        public final void reuseCallsPerHostFrom(AsyncCall realCall$AsyncCall0) {
            Intrinsics.checkNotNullParameter(realCall$AsyncCall0, "other");
            this.callsPerHost = realCall$AsyncCall0.callsPerHost;
        }

        @Override
        public void run() {
            Response response0;
            RealCall realCall0 = RealCall.this;
            Thread thread0 = Thread.currentThread();
            thread0.setName("OkHttp " + RealCall.this.redactedUrl$okhttp());
            try {
                realCall0.timeout.enter();
                boolean z = false;
                try {
                    response0 = realCall0.getResponseWithInterceptorChain$okhttp();
                }
                catch(IOException iOException0) {
                    goto label_17;
                }
                catch(Throwable throwable0) {
                    goto label_24;
                }
                finally {
                    realCall0.getClient().dispatcher().finished$okhttp(this);
                }
                try {
                    this.responseCallback.onResponse(realCall0, response0);
                    return;
                }
                catch(IOException iOException0) {
                    z = true;
                label_17:
                    if(z) {
                        Platform.Companion.get().log("Callback failure for " + realCall0.toLoggableString(), 4, iOException0);
                    }
                    else {
                        this.responseCallback.onFailure(realCall0, iOException0);
                    }
                    return;
                }
                catch(Throwable throwable0) {
                    z = true;
                }
            label_24:
                realCall0.cancel();
                if(!z) {
                    IOException iOException1 = new IOException("canceled due to " + throwable0);
                    ExceptionsKt.addSuppressed(iOException1, throwable0);
                    this.responseCallback.onFailure(realCall0, iOException1);
                }
                throw throwable0;
            }
            finally {
                thread0.setName("jeb-dexdec-sb-st-13420");
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/RealCall$CallReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/RealCall;", "referent", "callStackTrace", "", "(Lokhttp3/internal/connection/RealCall;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class CallReference extends WeakReference {
        private final Object callStackTrace;

        public CallReference(RealCall realCall0, Object object0) {
            Intrinsics.checkNotNullParameter(realCall0, "referent");
            super(realCall0);
            this.callStackTrace = object0;
        }

        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }
    }

    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private RealConnection connection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private volatile Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private final AtomicBoolean executed;
    private boolean expectMoreExchanges;
    private final boolean forWebSocket;
    private Exchange interceptorScopedExchange;
    private final Request originalRequest;
    private final CopyOnWriteArrayList plansToCancel;
    private boolean requestBodyOpen;
    private boolean responseBodyOpen;
    private final okhttp3.internal.connection.RealCall.timeout.1 timeout;
    private boolean timeoutEarlyExit;

    public RealCall(OkHttpClient okHttpClient0, Request request0, boolean z) {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        Intrinsics.checkNotNullParameter(request0, "originalRequest");
        super();
        this.client = okHttpClient0;
        this.originalRequest = request0;
        this.forWebSocket = z;
        this.connectionPool = okHttpClient0.connectionPool().getDelegate$okhttp();
        this.eventListener = okHttpClient0.eventListenerFactory().create(this);
        okhttp3.internal.connection.RealCall.timeout.1 realCall$timeout$10 = new AsyncTimeout() {
            @Override  // okio.AsyncTimeout
            protected void timedOut() {
                RealCall.this.cancel();
            }
        };
        realCall$timeout$10.timeout(((long)okHttpClient0.callTimeoutMillis()), TimeUnit.MILLISECONDS);
        this.timeout = realCall$timeout$10;
        this.executed = new AtomicBoolean();
        this.expectMoreExchanges = true;
        this.plansToCancel = new CopyOnWriteArrayList();
    }

    public final void acquireConnectionNoEvents(RealConnection realConnection0) {
        Intrinsics.checkNotNullParameter(realConnection0, "connection");
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(realConnection0)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13304 MUST hold lock on " + realConnection0);
        }
        if(this.connection != null) {
            throw new IllegalStateException("Check failed.");
        }
        this.connection = realConnection0;
        realConnection0.getCalls().add(new CallReference(this, this.callStackTrace));
    }

    private final IOException callDone(IOException iOException0) {
        Socket socket0;
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13207 MUST NOT hold lock on " + this);
        }
        RealConnection realConnection0 = this.connection;
        if(realConnection0 != null) {
            if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(realConnection0)) {
                throw new AssertionError("Thread jeb-dexdec-sb-st-13207 MUST NOT hold lock on " + realConnection0);
            }
            synchronized(realConnection0) {
                socket0 = this.releaseConnectionNoEvents$okhttp();
            }
            if(this.connection == null) {
                if(socket0 != null) {
                    _UtilJvmKt.closeQuietly(socket0);
                }
                this.eventListener.connectionReleased(this, realConnection0);
            }
            else if(socket0 != null) {
                throw new IllegalStateException("Check failed.");
            }
        }
        IOException iOException1 = this.timeoutExit(iOException0);
        if(iOException0 != null) {
            Intrinsics.checkNotNull(iOException1);
            this.eventListener.callFailed(this, iOException1);
            return iOException1;
        }
        this.eventListener.callEnd(this);
        return iOException1;
    }

    private final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this);
    }

    @Override  // okhttp3.Call
    public void cancel() {
        if(this.canceled) {
            return;
        }
        this.canceled = true;
        Exchange exchange0 = this.exchange;
        if(exchange0 != null) {
            exchange0.cancel();
        }
        for(Object object0: this.plansToCancel) {
            ((Plan)object0).cancel();
        }
        this.eventListener.canceled(this);
    }

    @Override
    public Object clone() {
        return this.clone();
    }

    @Override  // okhttp3.Call
    public Call clone() {
        return new RealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    private final Address createAddress(HttpUrl httpUrl0) {
        if(httpUrl0.isHttps()) {
            SSLSocketFactory sSLSocketFactory0 = this.client.sslSocketFactory();
            return new Address(httpUrl0.host(), httpUrl0.port(), this.client.dns(), this.client.socketFactory(), sSLSocketFactory0, this.client.hostnameVerifier(), this.client.certificatePinner(), this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
        }
        return new Address(httpUrl0.host(), httpUrl0.port(), this.client.dns(), this.client.socketFactory(), null, null, null, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    @Override  // okhttp3.Call
    public void enqueue(Callback callback0) {
        Intrinsics.checkNotNullParameter(callback0, "responseCallback");
        if(!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        this.callStart();
        AsyncCall realCall$AsyncCall0 = new AsyncCall(this, callback0);
        this.client.dispatcher().enqueue$okhttp(realCall$AsyncCall0);
    }

    public final void enterNetworkInterceptorExchange(Request request0, boolean z, RealInterceptorChain realInterceptorChain0) {
        Intrinsics.checkNotNullParameter(request0, "request");
        Intrinsics.checkNotNullParameter(realInterceptorChain0, "chain");
        if(this.interceptorScopedExchange != null) {
            throw new IllegalStateException("Check failed.");
        }
        synchronized(this) {
            if(!this.responseBodyOpen) {
                if(this.requestBodyOpen) {
                    throw new IllegalStateException("Check failed.");
                }
                if(z) {
                    Address address0 = this.createAddress(request0.url());
                    RealRoutePlanner realRoutePlanner0 = new RealRoutePlanner(this.client, address0, this, realInterceptorChain0);
                    ExchangeFinder exchangeFinder0 = this.client.fastFallback() ? new FastFallbackExchangeFinder(realRoutePlanner0, this.client.getTaskRunner$okhttp()) : new SequentialExchangeFinder(realRoutePlanner0);
                    this.exchangeFinder = exchangeFinder0;
                }
                return;
            }
        }
        throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
    }

    @Override  // okhttp3.Call
    public Response execute() {
        if(this.executed.compareAndSet(false, true)) {
            this.timeout.enter();
            this.callStart();
            try {
                this.client.dispatcher().executed$okhttp(this);
                return this.getResponseWithInterceptorChain$okhttp();
            }
            finally {
                this.client.dispatcher().finished$okhttp(this);
            }
        }
        throw new IllegalStateException("Already Executed");
    }

    public final void exitNetworkInterceptorExchange$okhttp(boolean z) {
        synchronized(this) {
            if(this.expectMoreExchanges) {
                if(z) {
                    Exchange exchange0 = this.exchange;
                    if(exchange0 != null) {
                        exchange0.detachWithViolence();
                    }
                }
                this.interceptorScopedExchange = null;
                return;
            }
        }
        throw new IllegalStateException("released");
    }

    public final OkHttpClient getClient() {
        return this.client;
    }

    public final RealConnection getConnection() {
        return this.connection;
    }

    public final EventListener getEventListener$okhttp() {
        return this.eventListener;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    public final Exchange getInterceptorScopedExchange$okhttp() {
        return this.interceptorScopedExchange;
    }

    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    public final CopyOnWriteArrayList getPlansToCancel$okhttp() {
        return this.plansToCancel;
    }

    public final Response getResponseWithInterceptorChain$okhttp() throws IOException {
        Response response0;
        ArrayList arrayList0 = new ArrayList();
        CollectionsKt.addAll(arrayList0, this.client.interceptors());
        arrayList0.add(new RetryAndFollowUpInterceptor(this.client));
        arrayList0.add(new BridgeInterceptor(this.client.cookieJar()));
        arrayList0.add(new CacheInterceptor(this.client.cache()));
        arrayList0.add(ConnectInterceptor.INSTANCE);
        if(!this.forWebSocket) {
            CollectionsKt.addAll(arrayList0, this.client.networkInterceptors());
        }
        arrayList0.add(new CallServerInterceptor(this.forWebSocket));
        RealInterceptorChain realInterceptorChain0 = new RealInterceptorChain(this, arrayList0, 0, null, this.originalRequest, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        boolean z = false;
        try {
            try {
                response0 = realInterceptorChain0.proceed(this.originalRequest);
                if(this.isCanceled()) {
                    _UtilCommonKt.closeQuietly(response0);
                    throw new IOException("Canceled");
                }
            }
            catch(IOException iOException0) {
                z = true;
                IOException iOException1 = this.noMoreExchanges$okhttp(iOException0);
                Intrinsics.checkNotNull(iOException1, "null cannot be cast to non-null type kotlin.Throwable");
                throw iOException1;
            }
        }
        catch(Throwable throwable0) {
            if(!z) {
                this.noMoreExchanges$okhttp(null);
            }
            throw throwable0;
        }
        this.noMoreExchanges$okhttp(null);
        return response0;
    }

    public final Exchange initExchange$okhttp(RealInterceptorChain realInterceptorChain0) {
        Intrinsics.checkNotNullParameter(realInterceptorChain0, "chain");
        synchronized(this) {
            if(this.expectMoreExchanges) {
                if(this.responseBodyOpen || this.requestBodyOpen) {
                    throw new IllegalStateException("Check failed.");
                }
                ExchangeFinder exchangeFinder0 = this.exchangeFinder;
                Intrinsics.checkNotNull(exchangeFinder0);
                ExchangeCodec exchangeCodec0 = exchangeFinder0.find().newCodec$okhttp(this.client, realInterceptorChain0);
                Exchange exchange0 = new Exchange(this, this.eventListener, exchangeFinder0, exchangeCodec0);
                this.interceptorScopedExchange = exchange0;
                this.exchange = exchange0;
                synchronized(this) {
                    this.requestBodyOpen = true;
                    this.responseBodyOpen = true;
                }
                if(this.canceled) {
                    throw new IOException("Canceled");
                }
                return exchange0;
            }
        }
        throw new IllegalStateException("released");
    }

    @Override  // okhttp3.Call
    public boolean isCanceled() {
        return this.canceled;
    }

    @Override  // okhttp3.Call
    public boolean isExecuted() {
        return this.executed.get();
    }

    public final IOException messageDone$okhttp(Exchange exchange0, boolean z, boolean z1, IOException iOException0) {
        int v2;
        Intrinsics.checkNotNullParameter(exchange0, "exchange");
        if(Intrinsics.areEqual(exchange0, this.exchange)) {
            synchronized(this) {
                int v = 0;
                if((!z || !this.requestBodyOpen) && (!z1 || !this.responseBodyOpen)) {
                    v2 = 0;
                }
                else {
                    if(z) {
                        this.requestBodyOpen = false;
                    }
                    if(z1) {
                        this.responseBodyOpen = false;
                    }
                    int v1 = this.requestBodyOpen || this.responseBodyOpen ? 0 : 1;
                    if(!this.requestBodyOpen && !this.responseBodyOpen && !this.expectMoreExchanges) {
                        v = 1;
                    }
                    v2 = v;
                    v = v1;
                }
            }
            if(v != 0) {
                this.exchange = null;
                RealConnection realConnection0 = this.connection;
                if(realConnection0 != null) {
                    realConnection0.incrementSuccessCount$okhttp();
                }
            }
            return v2 == 0 ? iOException0 : this.callDone(iOException0);
        }
        return iOException0;
    }

    public final IOException noMoreExchanges$okhttp(IOException iOException0) {
        boolean z;
        synchronized(this) {
            z = false;
            if(this.expectMoreExchanges) {
                this.expectMoreExchanges = false;
                if(!this.requestBodyOpen && !this.responseBodyOpen) {
                    z = true;
                }
            }
        }
        return z ? this.callDone(iOException0) : iOException0;
    }

    public final String redactedUrl$okhttp() {
        return this.originalRequest.url().redact();
    }

    public final Socket releaseConnectionNoEvents$okhttp() {
        RealConnection realConnection0 = this.connection;
        Intrinsics.checkNotNull(realConnection0);
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(realConnection0)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13428 MUST hold lock on " + realConnection0);
        }
        List list0 = realConnection0.getCalls();
        int v = 0;
        Iterator iterator0 = list0.iterator();
        while(true) {
            if(!iterator0.hasNext()) {
                v = -1;
                break;
            }
            Object object0 = iterator0.next();
            if(Intrinsics.areEqual(((Reference)object0).get(), this)) {
                break;
            }
            ++v;
        }
        if(v == -1) {
            throw new IllegalStateException("Check failed.");
        }
        list0.remove(v);
        this.connection = null;
        if(list0.isEmpty()) {
            realConnection0.setIdleAtNs(System.nanoTime());
            return this.connectionPool.connectionBecameIdle(realConnection0) ? realConnection0.socket() : null;
        }
        return null;
    }

    @Override  // okhttp3.Call
    public Request request() {
        return this.originalRequest;
    }

    public final boolean retryAfterFailure() {
        if(this.exchange != null && this.exchange.getHasFailure$okhttp()) {
            ExchangeFinder exchangeFinder0 = this.exchangeFinder;
            Intrinsics.checkNotNull(exchangeFinder0);
            return exchangeFinder0.getRoutePlanner().hasNext((this.exchange == null ? null : this.exchange.getConnection$okhttp()));
        }
        return false;
    }

    @Override  // okhttp3.Call
    public Timeout timeout() {
        return this.timeout;
    }

    public final void timeoutEarlyExit() {
        if(this.timeoutEarlyExit) {
            throw new IllegalStateException("Check failed.");
        }
        this.timeoutEarlyExit = true;
        this.timeout.exit();
    }

    private final IOException timeoutExit(IOException iOException0) {
        if(this.timeoutEarlyExit || !this.timeout.exit()) {
            return iOException0;
        }
        InterruptedIOException interruptedIOException0 = new InterruptedIOException("timeout");
        if(iOException0 != null) {
            interruptedIOException0.initCause(iOException0);
        }
        return interruptedIOException0;
    }

    // 去混淆评级： 低(40)
    private final String toLoggableString() {
        return (this.isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : "call") + " to " + this.redactedUrl$okhttp();
    }
}

