package androidx.work.impl.utils.futures;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
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

public abstract class AbstractFuture implements ListenableFuture {
    static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        AtomicHelper(androidx.work.impl.utils.futures.AbstractFuture.1 abstractFuture$10) {
        }

        abstract boolean casListeners(AbstractFuture arg1, Listener arg2, Listener arg3);

        abstract boolean casValue(AbstractFuture arg1, Object arg2, Object arg3);

        abstract boolean casWaiters(AbstractFuture arg1, Waiter arg2, Waiter arg3);

        abstract void putNext(Waiter arg1, Waiter arg2);

        abstract void putThread(Waiter arg1, Thread arg2);
    }

    static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED;
        static final Cancellation CAUSELESS_INTERRUPTED;
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if(AbstractFuture.GENERATE_CANCELLATION_CAUSES) {
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
            this.exception = (Throwable)AbstractFuture.checkNotNull(throwable0);
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

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        boolean casListeners(AbstractFuture abstractFuture0, Listener abstractFuture$Listener0, Listener abstractFuture$Listener1) {
            return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(this.listenersUpdater, abstractFuture0, abstractFuture$Listener0, abstractFuture$Listener1);
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        boolean casValue(AbstractFuture abstractFuture0, Object object0, Object object1) {
            return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(this.valueUpdater, abstractFuture0, object0, object1);
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        boolean casWaiters(AbstractFuture abstractFuture0, Waiter abstractFuture$Waiter0, Waiter abstractFuture$Waiter1) {
            return AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(this.waitersUpdater, abstractFuture0, abstractFuture$Waiter0, abstractFuture$Waiter1);
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        void putNext(Waiter abstractFuture$Waiter0, Waiter abstractFuture$Waiter1) {
            this.waiterNextUpdater.lazySet(abstractFuture$Waiter0, abstractFuture$Waiter1);
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        void putThread(Waiter abstractFuture$Waiter0, Thread thread0) {
            this.waiterThreadUpdater.lazySet(abstractFuture$Waiter0, thread0);
        }
    }

    static final class SetFuture implements Runnable {
        final ListenableFuture future;
        final AbstractFuture owner;

        SetFuture(AbstractFuture abstractFuture0, ListenableFuture listenableFuture0) {
            this.owner = abstractFuture0;
            this.future = listenableFuture0;
        }

        @Override
        public void run() {
            if(this.owner.value == this) {
                Object object0 = AbstractFuture.getFutureValue(this.future);
                if(AbstractFuture.ATOMIC_HELPER.casValue(this.owner, this, object0)) {
                    AbstractFuture.complete(this.owner);
                }
            }
        }
    }

    static final class SynchronizedHelper extends AtomicHelper {
        SynchronizedHelper() {
            super(null);
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        boolean casListeners(AbstractFuture abstractFuture0, Listener abstractFuture$Listener0, Listener abstractFuture$Listener1) {
            synchronized(abstractFuture0) {
                if(abstractFuture0.listeners == abstractFuture$Listener0) {
                    abstractFuture0.listeners = abstractFuture$Listener1;
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        boolean casValue(AbstractFuture abstractFuture0, Object object0, Object object1) {
            synchronized(abstractFuture0) {
                if(abstractFuture0.value == object0) {
                    abstractFuture0.value = object1;
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        boolean casWaiters(AbstractFuture abstractFuture0, Waiter abstractFuture$Waiter0, Waiter abstractFuture$Waiter1) {
            synchronized(abstractFuture0) {
                if(abstractFuture0.waiters == abstractFuture$Waiter0) {
                    abstractFuture0.waiters = abstractFuture$Waiter1;
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        void putNext(Waiter abstractFuture$Waiter0, Waiter abstractFuture$Waiter1) {
            abstractFuture$Waiter0.next = abstractFuture$Waiter1;
        }

        @Override  // androidx.work.impl.utils.futures.AbstractFuture$AtomicHelper
        void putThread(Waiter abstractFuture$Waiter0, Thread thread0) {
            abstractFuture$Waiter0.thread = thread0;
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
            AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }

        Waiter(boolean z) {
        }

        void setNext(Waiter abstractFuture$Waiter0) {
            AbstractFuture.ATOMIC_HELPER.putNext(this, abstractFuture$Waiter0);
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
        SafeAtomicHelper abstractFuture$SafeAtomicHelper0;
        AbstractFuture.GENERATE_CANCELLATION_CAUSES = false;
        AbstractFuture.log = Logger.getLogger("androidx.work.impl.utils.futures.AbstractFuture");
        try {
            abstractFuture$SafeAtomicHelper0 = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Thread.class, "thread"), AtomicReferenceFieldUpdater.newUpdater(Waiter.class, Waiter.class, "next"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Waiter.class, "waiters"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Listener.class, "listeners"), AtomicReferenceFieldUpdater.newUpdater(AbstractFuture.class, Object.class, "value"));
            throwable0 = null;
        }
        catch(Throwable throwable0) {
            abstractFuture$SafeAtomicHelper0 = new SynchronizedHelper();
        }
        AbstractFuture.ATOMIC_HELPER = abstractFuture$SafeAtomicHelper0;
        if(throwable0 != null) {
            AbstractFuture.log.log(Level.SEVERE, "SafeAtomicHelper is broken!", throwable0);
        }
        AbstractFuture.NULL = new Object();
    }

    private void addDoneString(StringBuilder stringBuilder0) {
        try {
            Object object0 = AbstractFuture.getUninterruptibly(this);
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
        AbstractFuture.checkNotNull(runnable0);
        AbstractFuture.checkNotNull(executor0);
        Listener abstractFuture$Listener0 = this.listeners;
        if(abstractFuture$Listener0 != Listener.TOMBSTONE) {
            Listener abstractFuture$Listener1 = new Listener(runnable0, executor0);
            while(true) {
                abstractFuture$Listener1.next = abstractFuture$Listener0;
                if(AbstractFuture.ATOMIC_HELPER.casListeners(this, abstractFuture$Listener0, abstractFuture$Listener1)) {
                    return;
                }
                abstractFuture$Listener0 = this.listeners;
                if(abstractFuture$Listener0 == Listener.TOMBSTONE) {
                    break;
                }
            }
        }
        AbstractFuture.executeListener(runnable0, executor0);
    }

    protected void afterDone() {
    }

    @Override
    public final boolean cancel(boolean z) {
        Cancellation abstractFuture$Cancellation0;
        Object object0 = this.value;
        if((object0 == null | object0 instanceof SetFuture) != 0) {
            if(AbstractFuture.GENERATE_CANCELLATION_CAUSES) {
                abstractFuture$Cancellation0 = new Cancellation(z, new CancellationException("Future.cancel() was called."));
            }
            else {
                abstractFuture$Cancellation0 = z ? Cancellation.CAUSELESS_INTERRUPTED : Cancellation.CAUSELESS_CANCELLED;
            }
            boolean z1 = false;
            AbstractFuture abstractFuture0 = this;
            while(true) {
                if(AbstractFuture.ATOMIC_HELPER.casValue(abstractFuture0, object0, abstractFuture$Cancellation0)) {
                    AbstractFuture.complete(abstractFuture0);
                    if(object0 instanceof SetFuture) {
                        ListenableFuture listenableFuture0 = ((SetFuture)object0).future;
                        if(listenableFuture0 instanceof AbstractFuture) {
                            abstractFuture0 = (AbstractFuture)listenableFuture0;
                            object0 = abstractFuture0.value;
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
                object0 = abstractFuture0.value;
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

    private Listener clearListeners(Listener abstractFuture$Listener0) {
        do {
            Listener abstractFuture$Listener1 = this.listeners;
        }
        while(!AbstractFuture.ATOMIC_HELPER.casListeners(this, abstractFuture$Listener1, Listener.TOMBSTONE));
        Listener abstractFuture$Listener2 = abstractFuture$Listener0;
        for(Listener abstractFuture$Listener3 = abstractFuture$Listener1; abstractFuture$Listener3 != null; abstractFuture$Listener3 = abstractFuture$Listener4) {
            Listener abstractFuture$Listener4 = abstractFuture$Listener3.next;
            abstractFuture$Listener3.next = abstractFuture$Listener2;
            abstractFuture$Listener2 = abstractFuture$Listener3;
        }
        return abstractFuture$Listener2;
    }

    static void complete(AbstractFuture abstractFuture0) {
        Runnable runnable0;
        Listener abstractFuture$Listener1;
        Listener abstractFuture$Listener0 = null;
        while(true) {
            abstractFuture0.releaseWaiters();
            abstractFuture$Listener1 = abstractFuture0.clearListeners(abstractFuture$Listener0);
        label_3:
            if(abstractFuture$Listener1 == null) {
                return;
            }
            abstractFuture$Listener0 = abstractFuture$Listener1.next;
            runnable0 = abstractFuture$Listener1.task;
            if(!(runnable0 instanceof SetFuture)) {
                break;
            }
            abstractFuture0 = ((SetFuture)runnable0).owner;
            if(abstractFuture0.value != ((SetFuture)runnable0)) {
                abstractFuture$Listener1 = abstractFuture$Listener0;
                goto label_3;
            }
            Object object0 = AbstractFuture.getFutureValue(((SetFuture)runnable0).future);
            if(!AbstractFuture.ATOMIC_HELPER.casValue(abstractFuture0, ((SetFuture)runnable0), object0)) {
                abstractFuture$Listener1 = abstractFuture$Listener0;
                goto label_3;
            }
        }
        AbstractFuture.executeListener(runnable0, abstractFuture$Listener1.executor);
        abstractFuture$Listener1 = abstractFuture$Listener0;
        goto label_3;
    }

    private static void executeListener(Runnable runnable0, Executor executor0) {
        try {
            executor0.execute(runnable0);
        }
        catch(RuntimeException runtimeException0) {
            AbstractFuture.log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable0 + " with executor " + executor0, runtimeException0);
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
        Waiter abstractFuture$Waiter0 = this.waiters;
        if(abstractFuture$Waiter0 != Waiter.TOMBSTONE) {
            Waiter abstractFuture$Waiter1 = new Waiter();
            while(true) {
                abstractFuture$Waiter1.setNext(abstractFuture$Waiter0);
                if(AbstractFuture.ATOMIC_HELPER.casWaiters(this, abstractFuture$Waiter0, abstractFuture$Waiter1)) {
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
                    this.removeWaiter(abstractFuture$Waiter1);
                    throw new InterruptedException();
                }
                abstractFuture$Waiter0 = this.waiters;
                if(abstractFuture$Waiter0 == Waiter.TOMBSTONE) {
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
            Waiter abstractFuture$Waiter0 = this.waiters;
            if(abstractFuture$Waiter0 != Waiter.TOMBSTONE) {
                Waiter abstractFuture$Waiter1 = new Waiter();
                while(true) {
                    abstractFuture$Waiter1.setNext(abstractFuture$Waiter0);
                    if(AbstractFuture.ATOMIC_HELPER.casWaiters(this, abstractFuture$Waiter0, abstractFuture$Waiter1)) {
                        do {
                            LockSupport.parkNanos(this, v1);
                            if(Thread.interrupted()) {
                                this.removeWaiter(abstractFuture$Waiter1);
                                throw new InterruptedException();
                            }
                            Object object1 = this.value;
                            if(((object1 == null ? 0 : 1) & !(object1 instanceof SetFuture)) != 0) {
                                return this.getDoneValue(object1);
                            }
                            v1 = v2 - System.nanoTime();
                        }
                        while(v1 >= 1000L);
                        this.removeWaiter(abstractFuture$Waiter1);
                        goto label_26;
                    }
                    abstractFuture$Waiter0 = this.waiters;
                    if(abstractFuture$Waiter0 == Waiter.TOMBSTONE) {
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
        throw this.isDone() ? new TimeoutException(s1 + " but future completed as timeout expired") : new TimeoutException(s1 + " for " + "jebdyn.dexdec.irsb.Object_e2416401@13d15c9a[status=PENDING]");
    }

    private Object getDoneValue(Object object0) throws ExecutionException {
        if(object0 instanceof Cancellation) {
            throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", ((Cancellation)object0).cause);
        }
        if(object0 instanceof Failure) {
            throw new ExecutionException(((Failure)object0).exception);
        }
        return object0 == AbstractFuture.NULL ? null : object0;
    }

    static Object getFutureValue(ListenableFuture listenableFuture0) {
        if(listenableFuture0 instanceof AbstractFuture) {
            Cancellation abstractFuture$Cancellation0 = ((AbstractFuture)listenableFuture0).value;
            if(abstractFuture$Cancellation0 instanceof Cancellation && abstractFuture$Cancellation0.wasInterrupted) {
                return abstractFuture$Cancellation0.cause == null ? Cancellation.CAUSELESS_CANCELLED : new Cancellation(false, abstractFuture$Cancellation0.cause);
            }
            return abstractFuture$Cancellation0;
        }
        boolean z = listenableFuture0.isCancelled();
        if((!AbstractFuture.GENERATE_CANCELLATION_CAUSES & z) != 0) {
            return Cancellation.CAUSELESS_CANCELLED;
        }
        try {
            Object object0 = AbstractFuture.getUninterruptibly(listenableFuture0);
            return object0 == null ? AbstractFuture.NULL : object0;
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

    private static Object getUninterruptibly(Future future0) throws ExecutionException {
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
        Waiter abstractFuture$Waiter0;
        do {
            abstractFuture$Waiter0 = this.waiters;
        }
        while(!AbstractFuture.ATOMIC_HELPER.casWaiters(this, abstractFuture$Waiter0, Waiter.TOMBSTONE));
        while(abstractFuture$Waiter0 != null) {
            abstractFuture$Waiter0.unpark();
            abstractFuture$Waiter0 = abstractFuture$Waiter0.next;
        }
    }

    private void removeWaiter(Waiter abstractFuture$Waiter0) {
        abstractFuture$Waiter0.thread = null;
    alab1:
        Waiter abstractFuture$Waiter1;
        while((abstractFuture$Waiter1 = this.waiters) != Waiter.TOMBSTONE) {
            Waiter abstractFuture$Waiter2 = null;
            while(true) {
                if(abstractFuture$Waiter1 == null) {
                    break alab1;
                }
                Waiter abstractFuture$Waiter3 = abstractFuture$Waiter1.next;
                if(abstractFuture$Waiter1.thread != null) {
                    abstractFuture$Waiter2 = abstractFuture$Waiter1;
                }
                else if(abstractFuture$Waiter2 == null) {
                    if(AbstractFuture.ATOMIC_HELPER.casWaiters(this, abstractFuture$Waiter1, abstractFuture$Waiter3)) {
                        abstractFuture$Waiter1 = abstractFuture$Waiter3;
                        continue;
                    }
                    break;
                }
                else {
                    abstractFuture$Waiter2.next = abstractFuture$Waiter3;
                    if(abstractFuture$Waiter2.thread == null) {
                        break;
                    }
                }
                abstractFuture$Waiter1 = abstractFuture$Waiter3;
            }
        }
    }

    protected boolean set(Object object0) {
        if(object0 == null) {
            object0 = AbstractFuture.NULL;
        }
        if(AbstractFuture.ATOMIC_HELPER.casValue(this, null, object0)) {
            AbstractFuture.complete(this);
            return true;
        }
        return false;
    }

    protected boolean setException(Throwable throwable0) {
        Failure abstractFuture$Failure0 = new Failure(((Throwable)AbstractFuture.checkNotNull(throwable0)));
        if(AbstractFuture.ATOMIC_HELPER.casValue(this, null, abstractFuture$Failure0)) {
            AbstractFuture.complete(this);
            return true;
        }
        return false;
    }

    protected boolean setFuture(ListenableFuture listenableFuture0) {
        Failure abstractFuture$Failure0;
        AbstractFuture.checkNotNull(listenableFuture0);
        Object object0 = this.value;
        if(object0 == null) {
            if(listenableFuture0.isDone()) {
                Object object1 = AbstractFuture.getFutureValue(listenableFuture0);
                if(AbstractFuture.ATOMIC_HELPER.casValue(this, null, object1)) {
                    AbstractFuture.complete(this);
                    return true;
                }
                return false;
            }
            SetFuture abstractFuture$SetFuture0 = new SetFuture(this, listenableFuture0);
            if(AbstractFuture.ATOMIC_HELPER.casValue(this, null, abstractFuture$SetFuture0)) {
                try {
                    listenableFuture0.addListener(abstractFuture$SetFuture0, DirectExecutor.INSTANCE);
                }
                catch(Throwable throwable0) {
                    try {
                        abstractFuture$Failure0 = new Failure(throwable0);
                    }
                    catch(Throwable unused_ex) {
                        abstractFuture$Failure0 = Failure.FALLBACK_INSTANCE;
                    }
                    AbstractFuture.ATOMIC_HELPER.casValue(this, abstractFuture$SetFuture0, abstractFuture$Failure0);
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

    class androidx.work.impl.utils.futures.AbstractFuture.1 {
    }

}

