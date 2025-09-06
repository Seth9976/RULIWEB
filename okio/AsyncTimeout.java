package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001B2\u00020\u0001:\u0002\u001B\u001CB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001A\u00020\t2\b\u0010\n\u001A\u0004\u0018\u00010\tH\u0001J\u0006\u0010\u000B\u001A\u00020\fJ\u0006\u0010\r\u001A\u00020\u0004J\u0012\u0010\u000E\u001A\u00020\t2\b\u0010\n\u001A\u0004\u0018\u00010\tH\u0014J\u0010\u0010\u000F\u001A\u00020\u00072\u0006\u0010\u0010\u001A\u00020\u0007H\u0002J\u000E\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0011\u001A\u00020\u0012J\u000E\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0013\u001A\u00020\u0014J\b\u0010\u0015\u001A\u00020\fH\u0014J%\u0010\u0016\u001A\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00170\u0019H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u001AR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0000X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001D"}, d2 = {"Lokio/AsyncTimeout;", "Lokio/Timeout;", "()V", "inQueue", "", "next", "timeoutAt", "", "access$newTimeoutException", "Ljava/io/IOException;", "cause", "enter", "", "exit", "newTimeoutException", "remainingNanos", "now", "sink", "Lokio/Sink;", "source", "Lokio/Source;", "timedOut", "withTimeout", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Companion", "Watchdog", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public class AsyncTimeout extends Timeout {
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000F\u0010\n\u001A\u0004\u0018\u00010\tH\u0000¢\u0006\u0002\b\u000BJ\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\tH\u0002J \u0010\u000F\u001A\u00020\u00102\u0006\u0010\u000E\u001A\u00020\t2\u0006\u0010\u0011\u001A\u00020\u00042\u0006\u0010\u0012\u001A\u00020\rH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lokio/AsyncTimeout$Companion;", "", "()V", "IDLE_TIMEOUT_MILLIS", "", "IDLE_TIMEOUT_NANOS", "TIMEOUT_WRITE_SIZE", "", "head", "Lokio/AsyncTimeout;", "awaitTimeout", "awaitTimeout$okio", "cancelScheduledTimeout", "", "node", "scheduleTimeout", "", "timeoutNanos", "hasDeadline", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final boolean access$cancelScheduledTimeout(Companion asyncTimeout$Companion0, AsyncTimeout asyncTimeout0) {
            return asyncTimeout$Companion0.cancelScheduledTimeout(asyncTimeout0);
        }

        public static final void access$scheduleTimeout(Companion asyncTimeout$Companion0, AsyncTimeout asyncTimeout0, long v, boolean z) {
            asyncTimeout$Companion0.scheduleTimeout(asyncTimeout0, v, z);
        }

        public final AsyncTimeout awaitTimeout$okio() throws InterruptedException {
            AsyncTimeout asyncTimeout0 = AsyncTimeout.head;
            Intrinsics.checkNotNull(asyncTimeout0);
            AsyncTimeout asyncTimeout1 = asyncTimeout0.next;
            if(asyncTimeout1 == null) {
                AsyncTimeout.class.wait(60000L);
                Intrinsics.checkNotNull(AsyncTimeout.head);
                return null;
            }
            long v = asyncTimeout1.remainingNanos(System.nanoTime());
            if(v > 0L) {
                AsyncTimeout.class.wait(v / 1000000L, ((int)(v - 1000000L * (v / 1000000L))));
                return null;
            }
            AsyncTimeout asyncTimeout2 = AsyncTimeout.head;
            Intrinsics.checkNotNull(asyncTimeout2);
            asyncTimeout2.next = asyncTimeout1.next;
            asyncTimeout1.next = null;
            return asyncTimeout1;
        }

        private final boolean cancelScheduledTimeout(AsyncTimeout asyncTimeout0) {
            synchronized(AsyncTimeout.class) {
                if(!asyncTimeout0.inQueue) {
                    return false;
                }
                asyncTimeout0.inQueue = false;
                for(AsyncTimeout asyncTimeout1 = AsyncTimeout.head; asyncTimeout1 != null; asyncTimeout1 = asyncTimeout1.next) {
                    if(asyncTimeout1.next == asyncTimeout0) {
                        asyncTimeout1.next = asyncTimeout0.next;
                        asyncTimeout0.next = null;
                        return false;
                    }
                }
                return true;
            }
        }

        private final void scheduleTimeout(AsyncTimeout asyncTimeout0, long v, boolean z) {
            synchronized(AsyncTimeout.class) {
                if(!asyncTimeout0.inQueue) {
                    asyncTimeout0.inQueue = true;
                    if(AsyncTimeout.head == null) {
                        AsyncTimeout.head = new AsyncTimeout();
                        new Watchdog().start();
                    }
                    long v2 = System.nanoTime();
                    int v3 = Long.compare(v, 0L);
                    if(v3 != 0 && z) {
                        asyncTimeout0.timeoutAt = Math.min(v, asyncTimeout0.deadlineNanoTime() - v2) + v2;
                    }
                    else if(v3 != 0) {
                        asyncTimeout0.timeoutAt = v + v2;
                    }
                    else if(z) {
                        asyncTimeout0.timeoutAt = asyncTimeout0.deadlineNanoTime();
                    }
                    else {
                        throw new AssertionError();
                    }
                    long v4 = asyncTimeout0.remainingNanos(v2);
                    AsyncTimeout asyncTimeout1 = AsyncTimeout.head;
                    Intrinsics.checkNotNull(asyncTimeout1);
                    while(asyncTimeout1.next != null) {
                        AsyncTimeout asyncTimeout2 = asyncTimeout1.next;
                        Intrinsics.checkNotNull(asyncTimeout2);
                        if(v4 < asyncTimeout2.remainingNanos(v2)) {
                            break;
                        }
                        asyncTimeout1 = asyncTimeout1.next;
                        Intrinsics.checkNotNull(asyncTimeout1);
                    }
                    asyncTimeout0.next = asyncTimeout1.next;
                    asyncTimeout1.next = asyncTimeout0;
                    if(asyncTimeout1 == AsyncTimeout.head) {
                        AsyncTimeout.class.notify();
                    }
                    return;
                }
            }
            throw new IllegalStateException("Unbalanced enter/exit");
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lokio/AsyncTimeout$Watchdog;", "Ljava/lang/Thread;", "()V", "run", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            this.setDaemon(true);
        }

        @Override
        public void run() {
            AsyncTimeout asyncTimeout0;
            while(true) {
                try {
                    Class class0 = AsyncTimeout.class;
                    synchronized(class0) {
                        asyncTimeout0 = AsyncTimeout.Companion.awaitTimeout$okio();
                        if(asyncTimeout0 == AsyncTimeout.head) {
                            AsyncTimeout.head = null;
                            return;
                        }
                    }
                    if(asyncTimeout0 != null) {
                        asyncTimeout0.timedOut();
                    }
                }
                catch(InterruptedException unused_ex) {
                }
            }
        }
    }

    public static final Companion Companion = null;
    private static final long IDLE_TIMEOUT_MILLIS = 0L;
    private static final long IDLE_TIMEOUT_NANOS = 0L;
    private static final int TIMEOUT_WRITE_SIZE = 0x10000;
    private static AsyncTimeout head;
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;

    static {
        AsyncTimeout.Companion = new Companion(null);
        long v = TimeUnit.SECONDS.toMillis(60L);
        AsyncTimeout.IDLE_TIMEOUT_MILLIS = v;
        AsyncTimeout.IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(v);
    }

    public static final long access$getIDLE_TIMEOUT_MILLIS$cp() [...] // 潜在的解密器

    public static final long access$getIDLE_TIMEOUT_NANOS$cp() [...] // 潜在的解密器

    public final IOException access$newTimeoutException(IOException iOException0) {
        return this.newTimeoutException(iOException0);
    }

    public final void enter() {
        long v = this.timeoutNanos();
        boolean z = this.hasDeadline();
        if(v == 0L && !z) {
            return;
        }
        Companion.access$scheduleTimeout(AsyncTimeout.Companion, this, v, z);
    }

    public final boolean exit() {
        return Companion.access$cancelScheduledTimeout(AsyncTimeout.Companion, this);
    }

    protected IOException newTimeoutException(IOException iOException0) {
        InterruptedIOException interruptedIOException0 = new InterruptedIOException("timeout");
        if(iOException0 != null) {
            interruptedIOException0.initCause(iOException0);
        }
        return interruptedIOException0;
    }

    private final long remainingNanos(long v) {
        return this.timeoutAt - v;
    }

    public final Sink sink(Sink sink0) {
        Intrinsics.checkNotNullParameter(sink0, "sink");
        return new Object() {
            @Override  // okio.Sink
            public void close() {
                AsyncTimeout asyncTimeout0 = sink0;
                asyncTimeout0.enter();
                try {
                    try {
                        this.$sink.close();
                    }
                    catch(IOException iOException0) {
                        if(asyncTimeout0.exit()) {
                            iOException0 = asyncTimeout0.access$newTimeoutException(iOException0);
                        }
                        throw iOException0;
                    }
                }
                catch(Throwable throwable0) {
                    asyncTimeout0.exit();
                    throw throwable0;
                }
                if(asyncTimeout0.exit()) {
                    throw asyncTimeout0.access$newTimeoutException(null);
                }
            }

            @Override  // okio.Sink
            public void flush() {
                AsyncTimeout asyncTimeout0 = sink0;
                asyncTimeout0.enter();
                try {
                    try {
                        this.$sink.flush();
                    }
                    catch(IOException iOException0) {
                        if(asyncTimeout0.exit()) {
                            iOException0 = asyncTimeout0.access$newTimeoutException(iOException0);
                        }
                        throw iOException0;
                    }
                }
                catch(Throwable throwable0) {
                    asyncTimeout0.exit();
                    throw throwable0;
                }
                if(asyncTimeout0.exit()) {
                    throw asyncTimeout0.access$newTimeoutException(null);
                }
            }

            public AsyncTimeout timeout() {
                return sink0;
            }

            @Override  // okio.Sink
            public Timeout timeout() {
                return this.timeout();
            }

            @Override
            public String toString() {
                return "AsyncTimeout.sink(" + this.$sink + ')';
            }

            @Override  // okio.Sink
            public void write(Buffer buffer0, long v) {
                Intrinsics.checkNotNullParameter(buffer0, "source");
                _UtilKt.checkOffsetAndCount(buffer0.size(), 0L, v);
                while(true) {
                    long v1 = 0L;
                    if(v <= 0L) {
                        break;
                    }
                    Segment segment0 = buffer0.head;
                    Intrinsics.checkNotNull(segment0);
                    while(v1 < 0x10000L) {
                        v1 += (long)(segment0.limit - segment0.pos);
                        if(v1 >= v) {
                            v1 = v;
                            break;
                        }
                        segment0 = segment0.next;
                        Intrinsics.checkNotNull(segment0);
                    }
                    AsyncTimeout asyncTimeout0 = sink0;
                    asyncTimeout0.enter();
                    try {
                        try {
                            this.$sink.write(buffer0, v1);
                        }
                        catch(IOException iOException0) {
                            throw asyncTimeout0.exit() ? asyncTimeout0.access$newTimeoutException(iOException0) : iOException0;
                        }
                    }
                    catch(Throwable throwable0) {
                        asyncTimeout0.exit();
                        throw throwable0;
                    }
                    if(asyncTimeout0.exit()) {
                        throw asyncTimeout0.access$newTimeoutException(null);
                    }
                    v -= v1;
                }
            }
        };
    }

    public final Source source(Source source0) {
        Intrinsics.checkNotNullParameter(source0, "source");
        return new Object() {
            @Override  // okio.Source
            public void close() {
                AsyncTimeout asyncTimeout0 = source0;
                asyncTimeout0.enter();
                try {
                    try {
                        this.$source.close();
                    }
                    catch(IOException iOException0) {
                        if(asyncTimeout0.exit()) {
                            iOException0 = asyncTimeout0.access$newTimeoutException(iOException0);
                        }
                        throw iOException0;
                    }
                }
                catch(Throwable throwable0) {
                    asyncTimeout0.exit();
                    throw throwable0;
                }
                if(asyncTimeout0.exit()) {
                    throw asyncTimeout0.access$newTimeoutException(null);
                }
            }

            @Override  // okio.Source
            public long read(Buffer buffer0, long v) {
                long v1;
                Intrinsics.checkNotNullParameter(buffer0, "sink");
                AsyncTimeout asyncTimeout0 = source0;
                asyncTimeout0.enter();
                try {
                    try {
                        v1 = this.$source.read(buffer0, v);
                    }
                    catch(IOException iOException0) {
                        if(asyncTimeout0.exit()) {
                            iOException0 = asyncTimeout0.access$newTimeoutException(iOException0);
                        }
                        throw iOException0;
                    }
                }
                catch(Throwable throwable0) {
                    asyncTimeout0.exit();
                    throw throwable0;
                }
                if(asyncTimeout0.exit()) {
                    throw asyncTimeout0.access$newTimeoutException(null);
                }
                return v1;
            }

            public AsyncTimeout timeout() {
                return source0;
            }

            @Override  // okio.Source
            public Timeout timeout() {
                return this.timeout();
            }

            @Override
            public String toString() {
                return "AsyncTimeout.source(" + this.$source + ')';
            }
        };
    }

    protected void timedOut() {
    }

    public final Object withTimeout(Function0 function00) {
        Object object0;
        Intrinsics.checkNotNullParameter(function00, "block");
        this.enter();
        try {
            try {
                object0 = function00.invoke();
            }
            catch(IOException iOException0) {
                if(this.exit()) {
                    iOException0 = this.access$newTimeoutException(iOException0);
                }
                throw iOException0;
            }
        }
        catch(Throwable throwable0) {
            this.exit();
            throw throwable0;
        }
        if(this.exit()) {
            throw this.access$newTimeoutException(null);
        }
        return object0;
    }
}

