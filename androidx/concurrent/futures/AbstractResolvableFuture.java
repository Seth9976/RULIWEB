package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractResolvableFuture implements ListenableFuture {
    static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        AtomicHelper(androidx.concurrent.futures.AbstractResolvableFuture.1 abstractResolvableFuture$10) {
        }

        abstract boolean casListeners(AbstractResolvableFuture arg1, Listener arg2, Listener arg3);

        abstract boolean casValue(AbstractResolvableFuture arg1, Object arg2, Object arg3);

        abstract boolean casWaiters(AbstractResolvableFuture arg1, Waiter arg2, Waiter arg3);

        abstract void putNext(Waiter arg1, Waiter arg2);

        abstract void putThread(Waiter arg1, Thread arg2);
    }

    static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED;
        static final Cancellation CAUSELESS_INTERRUPTED;
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if(AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES) {
                Cancellation.CAUSELESS_CANCELLED = null;
                Cancellation.CAUSELESS_INTERRUPTED = null;
                return;
            }
            Cancellation.CAUSELESS_CANCELLED = new Cancellation(false, null);
            Cancellation.CAUSELESS_INTERRUPTED = new Cancellation(true, null);
        }

        Cancellation(boolean z, Throwable throwable0) {
            this.wasInterrupted = z;
            this.cause = throwable0;
        }
    }

    static final class Failure {
        static final Failure FALLBACK_INSTANCE;
        final Throwable exception;

        static {
            Failure.FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") {
                @Override
                public Throwable fillInStackTrace() {
                    synchronized(this) {
                    }
                    return this;
                }
            });
        }

        Failure(Throwable throwable0) {
            this.exception = (Throwable)AbstractResolvableFuture.checkNotNull(throwable0);
        }
    }

    static final class Listener {
        static final Listener TOMBSTONE;
        final Executor executor;
        Listener next;
        final Runnable task;

        static {
            Listener.TOMBSTONE = new Listener(null, null);
        }

        Listener(Runnable runnable0, Executor executor0) {
            this.task = runnable0;
            this.executor = executor0;
        }
    }

    static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater listenersUpdater;
        final AtomicReferenceFieldUpdater valueUpdater;
        final AtomicReferenceFieldUpdater waiterNextUpdater;
        final AtomicReferenceFieldUpdater waiterThreadUpdater;
        final AtomicReferenceFieldUpdater waitersUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater1, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4) {
            super(null);
            this.waiterThreadUpdater = atomicReferenceFieldUpdater0;
            this.waiterNextUpdater = atomicReferenceFieldUpdater1;
            this.waitersUpdater = atomicReferenceFieldUpdater2;
            this.listenersUpdater = atomicReferenceFieldUpdater3;
            this.valueUpdater = atomicReferenceFieldUpdater4;
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        boolean casListeners(AbstractResolvableFuture abstractResolvableFuture0, Listener abstractResolvableFuture$Listener0, Listener abstractResolvableFuture$Listener1) {
            return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(this.listenersUpdater, abstractResolvableFuture0, abstractResolvableFuture$Listener0, abstractResolvableFuture$Listener1);
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        boolean casValue(AbstractResolvableFuture abstractResolvableFuture0, Object object0, Object object1) {
            return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(this.valueUpdater, abstractResolvableFuture0, object0, object1);
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        boolean casWaiters(AbstractResolvableFuture abstractResolvableFuture0, Waiter abstractResolvableFuture$Waiter0, Waiter abstractResolvableFuture$Waiter1) {
            return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(this.waitersUpdater, abstractResolvableFuture0, abstractResolvableFuture$Waiter0, abstractResolvableFuture$Waiter1);
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        void putNext(Waiter abstractResolvableFuture$Waiter0, Waiter abstractResolvableFuture$Waiter1) {
            this.waiterNextUpdater.lazySet(abstractResolvableFuture$Waiter0, abstractResolvableFuture$Waiter1);
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        void putThread(Waiter abstractResolvableFuture$Waiter0, Thread thread0) {
            this.waiterThreadUpdater.lazySet(abstractResolvableFuture$Waiter0, thread0);
        }
    }

    static final class SetFuture implements Runnable {
        final ListenableFuture future;
        final AbstractResolvableFuture owner;

        SetFuture(AbstractResolvableFuture abstractResolvableFuture0, ListenableFuture listenableFuture0) {
            this.owner = abstractResolvableFuture0;
            this.future = listenableFuture0;
        }

        @Override
        public void run() {
            if(this.owner.value == this) {
                Object object0 = AbstractResolvableFuture.getFutureValue(this.future);
                if(AbstractResolvableFuture.ATOMIC_HELPER.casValue(this.owner, this, object0)) {
                    AbstractResolvableFuture.complete(this.owner);
                }
            }
        }
    }

    static final class SynchronizedHelper extends AtomicHelper {
        SynchronizedHelper() {
            super(null);
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        boolean casListeners(AbstractResolvableFuture abstractResolvableFuture0, Listener abstractResolvableFuture$Listener0, Listener abstractResolvableFuture$Listener1) {
            synchronized(abstractResolvableFuture0) {
                if(abstractResolvableFuture0.listeners == abstractResolvableFuture$Listener0) {
                    abstractResolvableFuture0.listeners = abstractResolvableFuture$Listener1;
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        boolean casValue(AbstractResolvableFuture abstractResolvableFuture0, Object object0, Object object1) {
            synchronized(abstractResolvableFuture0) {
                if(abstractResolvableFuture0.value == object0) {
                    abstractResolvableFuture0.value = object1;
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        boolean casWaiters(AbstractResolvableFuture abstractResolvableFuture0, Waiter abstractResolvableFuture$Waiter0, Waiter abstractResolvableFuture$Waiter1) {
            synchronized(abstractResolvableFuture0) {
                if(abstractResolvableFuture0.waiters == abstractResolvableFuture$Waiter0) {
                    abstractResolvableFuture0.waiters = abstractResolvableFuture$Waiter1;
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        void putNext(Waiter abstractResolvableFuture$Waiter0, Waiter abstractResolvableFuture$Waiter1) {
            abstractResolvableFuture$Waiter0.next = abstractResolvableFuture$Waiter1;
        }

        @Override  // androidx.concurrent.futures.AbstractResolvableFuture$AtomicHelper
        void putThread(Waiter abstractResolvableFuture$Waiter0, Thread thread0) {
            abstractResolvableFuture$Waiter0.thread = thread0;
        }
    }

    static final class Waiter {
        static final Waiter TOMBSTONE;
        volatile Waiter next;
        volatile Thread thread;

        static {
            Waiter.TOMBSTONE = new Waiter(false);
        }

        Waiter() {
            AbstractResolvableFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }

        Waiter(boolean z) {
        }

        void setNext(Waiter abstractResolvableFuture$Waiter0) {
            AbstractResolvableFuture.ATOMIC_HELPER.putNext(this, abstractResolvableFuture$Waiter0);
        }

        void unpark() {
            Thread thread0 = this.thread;
            if(thread0 != null) {
                this.thread = null;
                LockSupport.unpark(thread0);
            }
        }
    }

    static final AtomicHelper ATOMIC_HELPER = null;
    static final boolean GENERATE_CANCELLATION_CAUSES = false;
    private static final Object NULL = null;
    private static final long SPIN_THRESHOLD_NANOS = 1000L;
    volatile Listener listeners;
    private static final Logger log;
    volatile Object value;
    volatile Waiter waiters;

    static {
        SafeAtomicHelper abstractResolvableFuture$SafeAtomicHelper0;
        AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES = false;
        AbstractResolvableFuture.log = Logger.getLogger("androidx.concurrent.futures.AbstractResolvableFuture");
        try {
            abstractResolvableFuture$SafeAtomicHelper0 = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Waiter.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractResolvableFuture.class, Object.class, "value"));
            throwable0 = null;
        }
        catch(Throwable throwable0) {
            abstractResolvableFuture$SafeAtomicHelper0 = new SynchronizedHelper();
        }
        AbstractResolvableFuture.ATOMIC_HELPER = abstractResolvableFuture$SafeAtomicHelper0;
        if(throwable0 != null) {
            AbstractResolvableFuture.log.log(Level.SEVERE, "SafeAtomicHelper is broken!", throwable0);
        }
        AbstractResolvableFuture.NULL = new Object();
    }

    private void addDoneString(StringBuilder stringBuilder0) {
        try {
            Object object0 = AbstractResolvableFuture.getUninterruptibly(this);
            stringBuilder0.append("SUCCESS, result=[");
            stringBuilder0.append(this.userObjectToString(object0));
            stringBuilder0.append("]");
        }
        catch(ExecutionException executionException0) {
            stringBuilder0.append("FAILURE, cause=[");
            stringBuilder0.append(executionException0.getCause());
            stringBuilder0.append("]");
        }
        catch(CancellationException unused_ex) {
            stringBuilder0.append("CANCELLED");
        }
        catch(RuntimeException runtimeException0) {
            stringBuilder0.append("UNKNOWN, cause=[");
            stringBuilder0.append(runtimeException0.getClass());
            stringBuilder0.append(" thrown from get()]");
        }
    }

    @Override  // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable0, Executor executor0) {
        AbstractResolvableFuture.checkNotNull(runnable0);
        AbstractResolvableFuture.checkNotNull(executor0);
        Listener abstractResolvableFuture$Listener0 = this.listeners;
        if(abstractResolvableFuture$Listener0 != Listener.TOMBSTONE) {
            Listener abstractResolvableFuture$Listener1 = new Listener(runnable0, executor0);
            while(true) {
                abstractResolvableFuture$Listener1.next = abstractResolvableFuture$Listener0;
                if(AbstractResolvableFuture.ATOMIC_HELPER.casListeners(this, abstractResolvableFuture$Listener0, abstractResolvableFuture$Listener1)) {
                    return;
                }
                abstractResolvableFuture$Listener0 = this.listeners;
                if(abstractResolvableFuture$Listener0 == Listener.TOMBSTONE) {
                    break;
                }
            }
        }
        AbstractResolvableFuture.executeListener(runnable0, executor0);
    }

    protected void afterDone() {
    }

    @Override
    public final boolean cancel(boolean z) {
        Cancellation abstractResolvableFuture$Cancellation0;
        Object object0 = this.value;
        if((object0 == null | object0 instanceof SetFuture) != 0) {
            if(AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES) {
                abstractResolvableFuture$Cancellation0 = new Cancellation(z, new CancellationException("Future.cancel() was called."));
            }
            else {
                abstractResolvableFuture$Cancellation0 = z ? Cancellation.CAUSELESS_INTERRUPTED : Cancellation.CAUSELESS_CANCELLED;
            }
            boolean z1 = false;
            AbstractResolvableFuture abstractResolvableFuture0 = this;
            while(true) {
                if(AbstractResolvableFuture.ATOMIC_HELPER.casValue(abstractResolvableFuture0, object0, abstractResolvableFuture$Cancellation0)) {
                    AbstractResolvableFuture.complete(abstractResolvableFuture0);
                    if(object0 instanceof SetFuture) {
                        ListenableFuture listenableFuture0 = ((SetFuture)object0).future;
                        if(listenableFuture0 instanceof AbstractResolvableFuture) {
                            abstractResolvableFuture0 = (AbstractResolvableFuture)listenableFuture0;
                            object0 = abstractResolvableFuture0.value;
                            if((object0 == null | object0 instanceof SetFuture) != 0) {
                                z1 = true;
                                continue;
                            }
                        }
                        else {
                            listenableFuture0.cancel(z);
                        }
                    }
                    return true;
                }
                object0 = abstractResolvableFuture0.value;
                if(!(object0 instanceof SetFuture)) {
                    break;
                }
            }
            return z1;
        }
        return false;
    }

    private static CancellationException cancellationExceptionWithCause(String s, Throwable throwable0) {
        CancellationException cancellationException0 = new CancellationException(s);
        cancellationException0.initCause(throwable0);
        return cancellationException0;
    }

    static Object checkNotNull(Object object0) {
        object0.getClass();
        return object0;
    }

    private Listener clearListeners(Listener abstractResolvableFuture$Listener0) {
        do {
            Listener abstractResolvableFuture$Listener1 = this.listeners;
        }
        while(!AbstractResolvableFuture.ATOMIC_HELPER.casListeners(this, abstractResolvableFuture$Listener1, Listener.TOMBSTONE));
        Listener abstractResolvableFuture$Listener2 = abstractResolvableFuture$Listener0;
        for(Listener abstractResolvableFuture$Listener3 = abstractResolvableFuture$Listener1; abstractResolvableFuture$Listener3 != null; abstractResolvableFuture$Listener3 = abstractResolvableFuture$Listener4) {
            Listener abstractResolvableFuture$Listener4 = abstractResolvableFuture$Listener3.next;
            abstractResolvableFuture$Listener3.next = abstractResolvableFuture$Listener2;
            abstractResolvableFuture$Listener2 = abstractResolvableFuture$Listener3;
        }
        return abstractResolvableFuture$Listener2;
    }

    static void complete(AbstractResolvableFuture abstractResolvableFuture0) {
        Runnable runnable0;
        Listener abstractResolvableFuture$Listener1;
        Listener abstractResolvableFuture$Listener0 = null;
        while(true) {
            abstractResolvableFuture0.releaseWaiters();
            abstractResolvableFuture0.afterDone();
            abstractResolvableFuture$Listener1 = abstractResolvableFuture0.clearListeners(abstractResolvableFuture$Listener0);
        label_4:
            if(abstractResolvableFuture$Listener1 == null) {
                return;
            }
            abstractResolvableFuture$Listener0 = abstractResolvableFuture$Listener1.next;
            runnable0 = abstractResolvableFuture$Listener1.task;
            if(!(runnable0 instanceof SetFuture)) {
                break;
            }
            abstractResolvableFuture0 = ((SetFuture)runnable0).owner;
            if(abstractResolvableFuture0.value != ((SetFuture)runnable0)) {
                abstractResolvableFuture$Listener1 = abstractResolvableFuture$Listener0;
                goto label_4;
            }
            Object object0 = AbstractResolvableFuture.getFutureValue(((SetFuture)runnable0).future);
            if(!AbstractResolvableFuture.ATOMIC_HELPER.casValue(abstractResolvableFuture0, ((SetFuture)runnable0), object0)) {
                abstractResolvableFuture$Listener1 = abstractResolvableFuture$Listener0;
                goto label_4;
            }
        }
        AbstractResolvableFuture.executeListener(runnable0, abstractResolvableFuture$Listener1.executor);
        abstractResolvableFuture$Listener1 = abstractResolvableFuture$Listener0;
        goto label_4;
    }

    private static void executeListener(Runnable runnable0, Executor executor0) {
        try {
            executor0.execute(runnable0);
        }
        catch(RuntimeException runtimeException0) {
            AbstractResolvableFuture.log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable0 + " with executor " + executor0, runtimeException0);
        }
    }

    @Override
    public final Object get() throws InterruptedException, ExecutionException {
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object object0 = this.value;
        if(((object0 == null ? 0 : 1) & !(object0 instanceof SetFuture)) != 0) {
            return this.getDoneValue(object0);
        }
        Waiter abstractResolvableFuture$Waiter0 = this.waiters;
        if(abstractResolvableFuture$Waiter0 != Waiter.TOMBSTONE) {
            Waiter abstractResolvableFuture$Waiter1 = new Waiter();
            while(true) {
                abstractResolvableFuture$Waiter1.setNext(abstractResolvableFuture$Waiter0);
                if(AbstractResolvableFuture.ATOMIC_HELPER.casWaiters(this, abstractResolvableFuture$Waiter0, abstractResolvableFuture$Waiter1)) {
                    while(true) {
                        LockSupport.park(this);
                        if(Thread.interrupted()) {
                            break;
                        }
                        Object object1 = this.value;
                        if(((object1 == null ? 0 : 1) & !(object1 instanceof SetFuture)) != 0) {
                            return this.getDoneValue(object1);
                        }
                    }
                    this.removeWaiter(abstractResolvableFuture$Waiter1);
                    throw new InterruptedException();
                }
                abstractResolvableFuture$Waiter0 = this.waiters;
                if(abstractResolvableFuture$Waiter0 == Waiter.TOMBSTONE) {
                    break;
                }
            }
        }
        return this.getDoneValue(this.value);
    }

    @Override
    public final Object get(long v, TimeUnit timeUnit0) throws InterruptedException, TimeoutException, ExecutionException {
        long v1 = timeUnit0.toNanos(v);
        if(Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object object0 = this.value;
        if(((object0 == null ? 0 : 1) & !(object0 instanceof SetFuture)) != 0) {
            return this.getDoneValue(object0);
        }
        long v2 = v1 <= 0L ? 0L : System.nanoTime() + v1;
        if(v1 >= 1000L) {
            Waiter abstractResolvableFuture$Waiter0 = this.waiters;
            if(abstractResolvableFuture$Waiter0 != Waiter.TOMBSTONE) {
                Waiter abstractResolvableFuture$Waiter1 = new Waiter();
                while(true) {
                    abstractResolvableFuture$Waiter1.setNext(abstractResolvableFuture$Waiter0);
                    if(AbstractResolvableFuture.ATOMIC_HELPER.casWaiters(this, abstractResolvableFuture$Waiter0, abstractResolvableFuture$Waiter1)) {
                        do {
                            LockSupport.parkNanos(this, v1);
                            if(Thread.interrupted()) {
                                this.removeWaiter(abstractResolvableFuture$Waiter1);
                                throw new InterruptedException();
                            }
                            Object object1 = this.value;
                            if(((object1 == null ? 0 : 1) & !(object1 instanceof SetFuture)) != 0) {
                                return this.getDoneValue(object1);
                            }
                            v1 = v2 - System.nanoTime();
                        }
                        while(v1 >= 1000L);
                        this.removeWaiter(abstractResolvableFuture$Waiter1);
                        goto label_26;
                    }
                    abstractResolvableFuture$Waiter0 = this.waiters;
                    if(abstractResolvableFuture$Waiter0 == Waiter.TOMBSTONE) {
                        break;
                    }
                }
            }
            return this.getDoneValue(this.value);
        }
    label_26:
        while(v1 > 0L) {
            Object object2 = this.value;
            if(((object2 == null ? 0 : 1) & !(object2 instanceof SetFuture)) != 0) {
                return this.getDoneValue(object2);
            }
            if(Thread.interrupted()) {
                throw new InterruptedException();
            }
            v1 = v2 - System.nanoTime();
        }
        String s = timeUnit0.toString().toLowerCase(Locale.ROOT);
        String s1 = "Waited " + v + " " + timeUnit0.toString().toLowerCase(Locale.ROOT);
        if(v1 + 1000L < 0L) {
            String s2 = s1 + " (plus ";
            long v3 = timeUnit0.convert(-v1, TimeUnit.NANOSECONDS);
            long v4 = -v1 - timeUnit0.toNanos(v3);
            int v5 = Long.compare(v3, 0L);
            boolean z = v5 == 0 || v4 > 1000L;
            if(v5 > 0) {
                s2 = (z ? s2 + v3 + " " + s + "," : s2 + v3 + " " + s) + " ";
            }
            if(z) {
                s2 = s2 + v4 + " nanoseconds ";
            }
            s1 = s2 + "delay)";
        }
        throw this.isDone() ? new TimeoutException(s1 + " but future completed as timeout expired") : new TimeoutException(s1 + " for " + "jebdyn.dexdec.irsb.Object_a5e85e71@1ed34012[status=PENDING]");
    }

    private Object getDoneValue(Object object0) throws ExecutionException {
        if(object0 instanceof Cancellation) {
            throw AbstractResolvableFuture.cancellationExceptionWithCause("Task was cancelled.", ((Cancellation)object0).cause);
        }
        if(object0 instanceof Failure) {
            throw new ExecutionException(((Failure)object0).exception);
        }
        return object0 == AbstractResolvableFuture.NULL ? null : object0;
    }

    static Object getFutureValue(ListenableFuture listenableFuture0) {
        if(listenableFuture0 instanceof AbstractResolvableFuture) {
            Cancellation abstractResolvableFuture$Cancellation0 = ((AbstractResolvableFuture)listenableFuture0).value;
            if(abstractResolvableFuture$Cancellation0 instanceof Cancellation && abstractResolvableFuture$Cancellation0.wasInterrupted) {
                return abstractResolvableFuture$Cancellation0.cause == null ? Cancellation.CAUSELESS_CANCELLED : new Cancellation(false, abstractResolvableFuture$Cancellation0.cause);
            }
            return abstractResolvableFuture$Cancellation0;
        }
        boolean z = listenableFuture0.isCancelled();
        if((!AbstractResolvableFuture.GENERATE_CANCELLATION_CAUSES & z) != 0) {
            return Cancellation.CAUSELESS_CANCELLED;
        }
        try {
            Object object0 = AbstractResolvableFuture.getUninterruptibly(listenableFuture0);
            return object0 == null ? AbstractResolvableFuture.NULL : object0;
        }
        catch(ExecutionException executionException0) {
            return new Failure(executionException0.getCause());
        }
        catch(CancellationException cancellationException0) {
            return !z ? new Failure(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + listenableFuture0, cancellationException0)) : new Cancellation(false, cancellationException0);
        }
        catch(Throwable throwable0) {
            return new Failure(throwable0);
        }
    }

    static Object getUninterruptibly(Future future0) throws ExecutionException {
        Object object0;
        boolean z = false;
        while(true) {
            try {
                object0 = future0.get();
                break;
            }
            catch(InterruptedException unused_ex) {
                z = true;
            }
            catch(Throwable throwable0) {
                if(z) {
                    Thread.currentThread().interrupt();
                }
                throw throwable0;
            }
        }
        if(z) {
            Thread.currentThread().interrupt();
        }
        return object0;
    }

    protected void interruptTask() {
    }

    @Override
    public final boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    @Override
    public final boolean isDone() {
        return this.value == null ? 0 : !(this.value instanceof SetFuture) & 1;
    }

    final void maybePropagateCancellationTo(Future future0) {
        if((future0 != null & this.isCancelled()) != 0) {
            future0.cancel(this.wasInterrupted());
        }
    }

    protected String pendingToString() {
        Object object0 = this.value;
        if(object0 instanceof SetFuture) {
            return "setFuture=[" + this.userObjectToString(((SetFuture)object0).future) + "]";
        }
        return this instanceof ScheduledFuture ? "remaining delay=[" + ((ScheduledFuture)this).getDelay(TimeUnit.MILLISECONDS) + " ms]" : null;
    }

    private void releaseWaiters() {
        Waiter abstractResolvableFuture$Waiter0;
        do {
            abstractResolvableFuture$Waiter0 = this.waiters;
        }
        while(!AbstractResolvableFuture.ATOMIC_HELPER.casWaiters(this, abstractResolvableFuture$Waiter0, Waiter.TOMBSTONE));
        while(abstractResolvableFuture$Waiter0 != null) {
            abstractResolvableFuture$Waiter0.unpark();
            abstractResolvableFuture$Waiter0 = abstractResolvableFuture$Waiter0.next;
        }
    }

    private void removeWaiter(Waiter abstractResolvableFuture$Waiter0) {
        abstractResolvableFuture$Waiter0.thread = null;
    alab1:
        Waiter abstractResolvableFuture$Waiter1;
        while((abstractResolvableFuture$Waiter1 = this.waiters) != Waiter.TOMBSTONE) {
            Waiter abstractResolvableFuture$Waiter2 = null;
            while(true) {
                if(abstractResolvableFuture$Waiter1 == null) {
                    break alab1;
                }
                Waiter abstractResolvableFuture$Waiter3 = abstractResolvableFuture$Waiter1.next;
                if(abstractResolvableFuture$Waiter1.thread != null) {
                    abstractResolvableFuture$Waiter2 = abstractResolvableFuture$Waiter1;
                }
                else if(abstractResolvableFuture$Waiter2 == null) {
                    if(AbstractResolvableFuture.ATOMIC_HELPER.casWaiters(this, abstractResolvableFuture$Waiter1, abstractResolvableFuture$Waiter3)) {
                        abstractResolvableFuture$Waiter1 = abstractResolvableFuture$Waiter3;
                        continue;
                    }
                    break;
                }
                else {
                    abstractResolvableFuture$Waiter2.next = abstractResolvableFuture$Waiter3;
                    if(abstractResolvableFuture$Waiter2.thread == null) {
                        break;
                    }
                }
                abstractResolvableFuture$Waiter1 = abstractResolvableFuture$Waiter3;
            }
        }
    }

    protected boolean set(Object object0) {
        if(object0 == null) {
            object0 = AbstractResolvableFuture.NULL;
        }
        if(AbstractResolvableFuture.ATOMIC_HELPER.casValue(this, null, object0)) {
            AbstractResolvableFuture.complete(this);
            return true;
        }
        return false;
    }

    protected boolean setException(Throwable throwable0) {
        Failure abstractResolvableFuture$Failure0 = new Failure(((Throwable)AbstractResolvableFuture.checkNotNull(throwable0)));
        if(AbstractResolvableFuture.ATOMIC_HELPER.casValue(this, null, abstractResolvableFuture$Failure0)) {
            AbstractResolvableFuture.complete(this);
            return true;
        }
        return false;
    }

    protected boolean setFuture(ListenableFuture listenableFuture0) {
        Failure abstractResolvableFuture$Failure0;
        AbstractResolvableFuture.checkNotNull(listenableFuture0);
        Object object0 = this.value;
        if(object0 == null) {
            if(listenableFuture0.isDone()) {
                Object object1 = AbstractResolvableFuture.getFutureValue(listenableFuture0);
                if(AbstractResolvableFuture.ATOMIC_HELPER.casValue(this, null, object1)) {
                    AbstractResolvableFuture.complete(this);
                    return true;
                }
                return false;
            }
            SetFuture abstractResolvableFuture$SetFuture0 = new SetFuture(this, listenableFuture0);
            if(AbstractResolvableFuture.ATOMIC_HELPER.casValue(this, null, abstractResolvableFuture$SetFuture0)) {
                try {
                    listenableFuture0.addListener(abstractResolvableFuture$SetFuture0, DirectExecutor.INSTANCE);
                }
                catch(Throwable throwable0) {
                    try {
                        abstractResolvableFuture$Failure0 = new Failure(throwable0);
                    }
                    catch(Throwable unused_ex) {
                        abstractResolvableFuture$Failure0 = Failure.FALLBACK_INSTANCE;
                    }
                    AbstractResolvableFuture.ATOMIC_HELPER.casValue(this, abstractResolvableFuture$SetFuture0, abstractResolvableFuture$Failure0);
                }
                return true;
            }
            object0 = this.value;
        }
        if(object0 instanceof Cancellation) {
            listenableFuture0.cancel(((Cancellation)object0).wasInterrupted);
        }
        return false;
    }

    @Override
    public String toString() [...] // 潜在的解密器

    private String userObjectToString(Object object0) {
        return object0 == this ? "this future" : String.valueOf(object0);
    }

    // 去混淆评级： 低(20)
    protected final boolean wasInterrupted() {
        return this.value instanceof Cancellation && ((Cancellation)this.value).wasInterrupted;
    }

    class androidx.concurrent.futures.AbstractResolvableFuture.1 {
    }

}

