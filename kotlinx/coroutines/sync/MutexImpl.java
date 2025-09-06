package kotlinx.coroutines.sync;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.selects.SelectClause2;
import kotlinx.coroutines.selects.SelectClause2Impl;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectInstanceInternal;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002:\u0002+,B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0010\u0010\u001D\u001A\u00020\u00042\u0006\u0010\u001B\u001A\u00020\nH\u0016J\u0012\u0010\u001E\u001A\u00020\u001F2\b\u0010\u001B\u001A\u0004\u0018\u00010\nH\u0002J\u001B\u0010 \u001A\u00020\u00192\b\u0010\u001B\u001A\u0004\u0018\u00010\nH\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010!J\u001B\u0010\"\u001A\u00020\u00192\b\u0010\u001B\u001A\u0004\u0018\u00010\nH\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010!J\u001E\u0010#\u001A\u0004\u0018\u00010\n2\b\u0010\u001B\u001A\u0004\u0018\u00010\n2\b\u0010$\u001A\u0004\u0018\u00010\nH\u0014J\u001E\u0010%\u001A\u00020\u00192\n\u0010\u0014\u001A\u0006\u0012\u0002\b\u00030\u00112\b\u0010\u001B\u001A\u0004\u0018\u00010\nH\u0014J\b\u0010&\u001A\u00020\'H\u0016J\u0012\u0010(\u001A\u00020\u00042\b\u0010\u001B\u001A\u0004\u0018\u00010\nH\u0016J\u0012\u0010)\u001A\u00020\u001F2\b\u0010\u001B\u001A\u0004\u0018\u00010\nH\u0002J\u0012\u0010*\u001A\u00020\u00192\b\u0010\u001B\u001A\u0004\u0018\u00010\nH\u0016R\u0014\u0010\u0006\u001A\u00020\u00048VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R(\u0010\b\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u00020\t8VX\u0096\u0004\u00A2\u0006\f\u0012\u0004\b\u000B\u0010\f\u001A\u0004\b\r\u0010\u000ERk\u0010\u000F\u001A_\u0012\u0017\u0012\u0015\u0012\u0002\b\u00030\u0011\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\n\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0015\u0012\u0013\u0018\u00010\n\u00A2\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u00170\u0010j\u0002`\u001AX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u001B\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001CX\u0082\u0004\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006-"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl;", "Lkotlinx/coroutines/sync/SemaphoreImpl;", "Lkotlinx/coroutines/sync/Mutex;", "locked", "", "(Z)V", "isLocked", "()Z", "onLock", "Lkotlinx/coroutines/selects/SelectClause2;", "", "getOnLock$annotations", "()V", "getOnLock", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSelectCancellationUnlockConstructor", "Lkotlin/Function3;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/ParameterName;", "name", "select", "param", "internalResult", "Lkotlin/Function1;", "", "", "Lkotlinx/coroutines/selects/OnCancellationConstructor;", "owner", "Lkotlinx/atomicfu/AtomicRef;", "holdsLock", "holdsLockImpl", "", "lock", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lockSuspend", "onLockProcessResult", "result", "onLockRegFunction", "toString", "", "tryLock", "tryLockImpl", "unlock", "CancellableContinuationWithOwner", "SelectInstanceWithOwner", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class MutexImpl extends SemaphoreImpl implements Mutex {
    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u001D\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007\u00A2\u0006\u0002\u0010\bJ\u0015\u0010\u0012\u001A\u00020\u000E2\n\b\u0002\u0010\u0013\u001A\u0004\u0018\u00010\u0014H\u0096\u0001J\u0011\u0010\u0015\u001A\u00020\u00022\u0006\u0010\u0016\u001A\u00020\u0007H\u0097\u0001J\t\u0010\u0017\u001A\u00020\u0002H\u0097\u0001J2\u0010\u0018\u001A\u00020\u00022\'\u0010\u0019\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\u0014\u00A2\u0006\f\b\u001B\u0012\b\b\u001C\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00020\u001Aj\u0002`\u001DH\u0096\u0001J\u001D\u0010\u0018\u001A\u00020\u00022\n\u0010\u001E\u001A\u0006\u0012\u0002\b\u00030\u001F2\u0006\u0010 \u001A\u00020!H\u0096\u0001J:\u0010\"\u001A\u00020\u00022\u0006\u0010#\u001A\u00020\u00022#\u0010$\u001A\u001F\u0012\u0013\u0012\u00110\u0014\u00A2\u0006\f\b\u001B\u0012\b\b\u001C\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001AH\u0016\u00A2\u0006\u0002\u0010%J\u001F\u0010&\u001A\u00020\u00022\f\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00020(H\u0096\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010)J$\u0010*\u001A\u0004\u0018\u00010\u00072\u0006\u0010#\u001A\u00020\u00022\n\b\u0002\u0010+\u001A\u0004\u0018\u00010\u0007H\u0097\u0001\u00A2\u0006\u0002\u0010,JF\u0010*\u001A\u0004\u0018\u00010\u00072\u0006\u0010#\u001A\u00020\u00022\b\u0010+\u001A\u0004\u0018\u00010\u00072#\u0010$\u001A\u001F\u0012\u0013\u0012\u00110\u0014\u00A2\u0006\f\b\u001B\u0012\b\b\u001C\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u001AH\u0016\u00A2\u0006\u0002\u0010-J\u0013\u0010.\u001A\u0004\u0018\u00010\u00072\u0006\u0010/\u001A\u00020\u0014H\u0097\u0001J\u001A\u00100\u001A\u00020\u0002*\u0002012\u0006\u0010#\u001A\u00020\u0002H\u0097\u0001\u00A2\u0006\u0002\u00102J\u0015\u00103\u001A\u00020\u0002*\u0002012\u0006\u0010/\u001A\u00020\u0014H\u0097\u0001R\u0016\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00020\u00058\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\t\u001A\u00020\nX\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u0012\u0010\r\u001A\u00020\u000EX\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\r\u0010\u000FR\u0012\u0010\u0010\u001A\u00020\u000EX\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\u000FR\u0012\u0010\u0011\u001A\u00020\u000EX\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u000FR\u0012\u0010\u0006\u001A\u0004\u0018\u00010\u00078\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u00064"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$CancellableContinuationWithOwner;", "Lkotlinx/coroutines/CancellableContinuation;", "", "Lkotlinx/coroutines/Waiter;", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "owner", "", "(Lkotlinx/coroutines/sync/MutexImpl;Lkotlinx/coroutines/CancellableContinuationImpl;Ljava/lang/Object;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "isActive", "", "()Z", "isCancelled", "isCompleted", "cancel", "cause", "", "completeResume", "token", "initCancellability", "invokeOnCancellation", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "segment", "Lkotlinx/coroutines/internal/Segment;", "index", "", "resume", "value", "onCancellation", "(Lkotlin/Unit;Lkotlin/jvm/functions/Function1;)V", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "tryResume", "idempotent", "(Lkotlin/Unit;Ljava/lang/Object;)Ljava/lang/Object;", "(Lkotlin/Unit;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "tryResumeWithException", "exception", "resumeUndispatched", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/Unit;)V", "resumeUndispatchedWithException", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class CancellableContinuationWithOwner implements CancellableContinuation, Waiter {
        public final CancellableContinuationImpl cont;
        public final Object owner;

        public CancellableContinuationWithOwner(CancellableContinuationImpl cancellableContinuationImpl0, Object object0) {
            this.cont = cancellableContinuationImpl0;
            this.owner = object0;
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public boolean cancel(Throwable throwable0) {
            return this.cont.cancel(throwable0);
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public void completeResume(Object object0) {
            this.cont.completeResume(object0);
        }

        @Override  // kotlin.coroutines.Continuation
        public CoroutineContext getContext() {
            return this.cont.getContext();
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public void initCancellability() {
            this.cont.initCancellability();
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public void invokeOnCancellation(Function1 function10) {
            this.cont.invokeOnCancellation(function10);
        }

        @Override  // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment segment0, int v) {
            this.cont.invokeOnCancellation(segment0, v);
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public boolean isActive() {
            return this.cont.isActive();
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public boolean isCancelled() {
            return this.cont.isCancelled();
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public boolean isCompleted() {
            return this.cont.isCompleted();
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public void resume(Object object0, Function1 function10) {
            this.resume(((Unit)object0), function10);
        }

        public void resume(Unit unit0, Function1 function10) {
            MutexImpl.owner$FU.set(MutexImpl.this, this.owner);
            kotlinx.coroutines.sync.MutexImpl.CancellableContinuationWithOwner.resume.2 mutexImpl$CancellableContinuationWithOwner$resume$20 = new Function1(this) {
                {
                    MutexImpl.this = mutexImpl0;
                    CancellableContinuationWithOwner.this = mutexImpl$CancellableContinuationWithOwner0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    this.invoke(((Throwable)object0));
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable throwable0) {
                    MutexImpl.this.unlock(CancellableContinuationWithOwner.this.owner);
                }
            };
            this.cont.resume(unit0, mutexImpl$CancellableContinuationWithOwner$resume$20);
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public void resumeUndispatched(CoroutineDispatcher coroutineDispatcher0, Object object0) {
            this.resumeUndispatched(coroutineDispatcher0, ((Unit)object0));
        }

        public void resumeUndispatched(CoroutineDispatcher coroutineDispatcher0, Unit unit0) {
            this.cont.resumeUndispatched(coroutineDispatcher0, unit0);
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public void resumeUndispatchedWithException(CoroutineDispatcher coroutineDispatcher0, Throwable throwable0) {
            this.cont.resumeUndispatchedWithException(coroutineDispatcher0, throwable0);
        }

        @Override  // kotlin.coroutines.Continuation
        public void resumeWith(Object object0) {
            this.cont.resumeWith(object0);
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public Object tryResume(Object object0, Object object1) {
            return this.tryResume(((Unit)object0), object1);
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public Object tryResume(Object object0, Object object1, Function1 function10) {
            return this.tryResume(((Unit)object0), object1, function10);
        }

        public Object tryResume(Unit unit0, Object object0) {
            return this.cont.tryResume(unit0, object0);
        }

        public Object tryResume(Unit unit0, Object object0, Function1 function10) {
            kotlinx.coroutines.sync.MutexImpl.CancellableContinuationWithOwner.tryResume.token.1 mutexImpl$CancellableContinuationWithOwner$tryResume$token$10 = new Function1(this) {
                {
                    MutexImpl.this = mutexImpl0;
                    CancellableContinuationWithOwner.this = mutexImpl$CancellableContinuationWithOwner0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    this.invoke(((Throwable)object0));
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable throwable0) {
                    MutexImpl.owner$FU.set(MutexImpl.this, CancellableContinuationWithOwner.this.owner);
                    MutexImpl.this.unlock(CancellableContinuationWithOwner.this.owner);
                }
            };
            Object object1 = this.cont.tryResume(unit0, object0, mutexImpl$CancellableContinuationWithOwner$tryResume$token$10);
            if(object1 != null) {
                MutexImpl.owner$FU.set(MutexImpl.this, this.owner);
            }
            return object1;
        }

        @Override  // kotlinx.coroutines.CancellableContinuation
        public Object tryResumeWithException(Throwable throwable0) {
            return this.cont.tryResumeWithException(throwable0);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001D\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0096\u0001J\u001D\u0010\u000F\u001A\u00020\f2\n\u0010\u0010\u001A\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0096\u0001J\u0012\u0010\u0014\u001A\u00020\f2\b\u0010\u0015\u001A\u0004\u0018\u00010\u0005H\u0016J\u001A\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00052\b\u0010\u0019\u001A\u0004\u0018\u00010\u0005H\u0016R\u0012\u0010\u0007\u001A\u00020\bX\u0096\u0005¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u0012\u0010\u0004\u001A\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Lkotlinx/coroutines/sync/MutexImpl$SelectInstanceWithOwner;", "Q", "Lkotlinx/coroutines/selects/SelectInstanceInternal;", "select", "owner", "", "(Lkotlinx/coroutines/sync/MutexImpl;Lkotlinx/coroutines/selects/SelectInstanceInternal;Ljava/lang/Object;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "disposeOnCompletion", "", "disposableHandle", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCancellation", "segment", "Lkotlinx/coroutines/internal/Segment;", "index", "", "selectInRegistrationPhase", "internalResult", "trySelect", "", "clauseObject", "result", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class SelectInstanceWithOwner implements SelectInstanceInternal {
        public final Object owner;
        public final SelectInstanceInternal select;

        public SelectInstanceWithOwner(SelectInstanceInternal selectInstanceInternal0, Object object0) {
            this.select = selectInstanceInternal0;
            this.owner = object0;
        }

        @Override  // kotlinx.coroutines.selects.SelectInstance
        public void disposeOnCompletion(DisposableHandle disposableHandle0) {
            this.select.disposeOnCompletion(disposableHandle0);
        }

        @Override  // kotlinx.coroutines.selects.SelectInstance
        public CoroutineContext getContext() {
            return this.select.getContext();
        }

        @Override  // kotlinx.coroutines.Waiter
        public void invokeOnCancellation(Segment segment0, int v) {
            this.select.invokeOnCancellation(segment0, v);
        }

        @Override  // kotlinx.coroutines.selects.SelectInstance
        public void selectInRegistrationPhase(Object object0) {
            MutexImpl.owner$FU.set(MutexImpl.this, this.owner);
            this.select.selectInRegistrationPhase(object0);
        }

        @Override  // kotlinx.coroutines.selects.SelectInstance
        public boolean trySelect(Object object0, Object object1) {
            boolean z = this.select.trySelect(object0, object1);
            MutexImpl mutexImpl0 = MutexImpl.this;
            if(z) {
                MutexImpl.owner$FU.set(mutexImpl0, this.owner);
            }
            return z;
        }
    }

    private final Function3 onSelectCancellationUnlockConstructor;
    @Volatile
    private volatile Object owner;
    private static final AtomicReferenceFieldUpdater owner$FU;

    static {
        MutexImpl.owner$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "owner");
    }

    public MutexImpl(boolean z) {
        super(1, ((int)z));
        this.owner = z ? null : MutexKt.access$getNO_OWNER$p();
        this.onSelectCancellationUnlockConstructor = new Function3() {
            {
                MutexImpl.this = mutexImpl0;
                super(3);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((SelectInstance)object0), object1, object2);
            }

            public final Function1 invoke(SelectInstance selectInstance0, Object object0, Object object1) {
                return new Function1(object0) {
                    final Object $owner;

                    {
                        MutexImpl.this = mutexImpl0;
                        this.$owner = object0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((Throwable)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable throwable0) {
                        MutexImpl.this.unlock(this.$owner);
                    }
                };
            }
        };
    }

    public static final Object access$lockSuspend(MutexImpl mutexImpl0, Object object0, Continuation continuation0) {
        return mutexImpl0.lockSuspend(object0, continuation0);
    }

    @Override  // kotlinx.coroutines.sync.Mutex
    public SelectClause2 getOnLock() {
        Intrinsics.checkNotNull(kotlinx.coroutines.sync.MutexImpl.onLock.1.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'select\')] kotlinx.coroutines.selects.SelectInstance<*>, @[ParameterName(name = \'param\')] kotlin.Any?, kotlin.Unit>{ kotlinx.coroutines.selects.SelectKt.RegistrationFunction }");
        Function3 function30 = (Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.sync.MutexImpl.onLock.1.INSTANCE, 3);
        Intrinsics.checkNotNull(kotlinx.coroutines.sync.MutexImpl.onLock.2.INSTANCE, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = \'clauseObject\')] kotlin.Any, @[ParameterName(name = \'param\')] kotlin.Any?, @[ParameterName(name = \'clauseResult\')] kotlin.Any?, kotlin.Any?>{ kotlinx.coroutines.selects.SelectKt.ProcessResultFunction }");
        return new SelectClause2Impl(this, function30, ((Function3)TypeIntrinsics.beforeCheckcastToFunctionOfArity(kotlinx.coroutines.sync.MutexImpl.onLock.2.INSTANCE, 3)), this.onSelectCancellationUnlockConstructor);

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.sync.MutexImpl.onLock.1 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.sync.MutexImpl.onLock.1 INSTANCE;

            static {
                kotlinx.coroutines.sync.MutexImpl.onLock.1.INSTANCE = new kotlinx.coroutines.sync.MutexImpl.onLock.1();
            }

            kotlinx.coroutines.sync.MutexImpl.onLock.1() {
                super(3, MutexImpl.class, "onLockRegFunction", "onLockRegFunction(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                this.invoke(((MutexImpl)object0), ((SelectInstance)object1), object2);
                return Unit.INSTANCE;
            }

            public final void invoke(MutexImpl mutexImpl0, SelectInstance selectInstance0, Object object0) {
                mutexImpl0.onLockRegFunction(selectInstance0, object0);
            }
        }


        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.sync.MutexImpl.onLock.2 extends FunctionReferenceImpl implements Function3 {
            public static final kotlinx.coroutines.sync.MutexImpl.onLock.2 INSTANCE;

            static {
                kotlinx.coroutines.sync.MutexImpl.onLock.2.INSTANCE = new kotlinx.coroutines.sync.MutexImpl.onLock.2();
            }

            kotlinx.coroutines.sync.MutexImpl.onLock.2() {
                super(3, MutexImpl.class, "onLockProcessResult", "onLockProcessResult(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((MutexImpl)object0), object1, object2);
            }

            public final Object invoke(MutexImpl mutexImpl0, Object object0, Object object1) {
                return mutexImpl0.onLockProcessResult(object0, object1);
            }
        }

    }

    public static void getOnLock$annotations() {
    }

    @Override  // kotlinx.coroutines.sync.Mutex
    public boolean holdsLock(Object object0) {
        return this.holdsLockImpl(object0) == 1;
    }

    private final int holdsLockImpl(Object object0) {
        Object object1;
        do {
            if(!this.isLocked()) {
                return 0;
            }
            object1 = MutexImpl.owner$FU.get(this);
        }
        while(object1 == MutexKt.access$getNO_OWNER$p());
        return object1 == object0 ? 1 : 2;
    }

    @Override  // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        return this.getAvailablePermits() == 0;
    }

    @Override  // kotlinx.coroutines.sync.Mutex
    public Object lock(Object object0, Continuation continuation0) {
        return MutexImpl.lock$suspendImpl(this, object0, continuation0);
    }

    static Object lock$suspendImpl(MutexImpl mutexImpl0, Object object0, Continuation continuation0) {
        if(mutexImpl0.tryLock(object0)) {
            return Unit.INSTANCE;
        }
        Object object1 = mutexImpl0.lockSuspend(object0, continuation0);
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    private final Object lockSuspend(Object object0, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(continuation0));
        try {
            this.acquire(new CancellableContinuationWithOwner(this, cancellableContinuationImpl0, object0));
        }
        catch(Throwable throwable0) {
            cancellableContinuationImpl0.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
            throw throwable0;
        }
        Object object1 = cancellableContinuationImpl0.getResult();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    protected Object onLockProcessResult(Object object0, Object object1) {
        if(Intrinsics.areEqual(object1, MutexKt.ON_LOCK_ALREADY_LOCKED_BY_OWNER)) {
            throw new IllegalStateException(("This mutex is already locked by the specified owner: " + object0).toString());
        }
        return this;
    }

    protected void onLockRegFunction(SelectInstance selectInstance0, Object object0) {
        if(object0 != null && this.holdsLock(object0)) {
            selectInstance0.selectInRegistrationPhase(MutexKt.ON_LOCK_ALREADY_LOCKED_BY_OWNER);
            return;
        }
        Intrinsics.checkNotNull(selectInstance0, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectInstanceInternal<*>");
        this.onAcquireRegFunction(new SelectInstanceWithOwner(this, ((SelectInstanceInternal)selectInstance0), object0), object0);
    }

    @Override
    public String toString() {
        return "Mutex@" + DebugStringsKt.getHexAddress(this) + "[isLocked=" + this.isLocked() + ",owner=" + MutexImpl.owner$FU.get(this) + ']';
    }

    @Override  // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(Object object0) {
        int v = this.tryLockImpl(object0);
        switch(v) {
            case 0: {
                return true;
            }
            case 1: {
                return false;
            }
            default: {
                throw v == 2 ? new IllegalStateException(("This mutex is already locked by the specified owner: " + object0).toString()) : new IllegalStateException("unexpected");
            }
        }
    }

    private final int tryLockImpl(Object object0) {
    alab1:
        while(true) {
            if(this.tryAcquire()) {
                MutexImpl.owner$FU.set(this, object0);
                return 0;
            }
            if(object0 == null) {
                return 1;
            }
            switch(this.holdsLockImpl(object0)) {
                case 1: {
                    return 2;
                }
                case 2: {
                    break alab1;
                }
            }
        }
        return 1;
    }

    @Override  // kotlinx.coroutines.sync.Mutex
    public void unlock(Object object0) {
        while(this.isLocked()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = MutexImpl.owner$FU;
            Object object1 = atomicReferenceFieldUpdater0.get(this);
            if(object1 != MutexKt.NO_OWNER) {
                if(object1 != object0 && object0 != null) {
                    throw new IllegalStateException(("This mutex is locked by " + object1 + ", but " + object0 + " is expected").toString());
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, this, object1, MutexKt.NO_OWNER)) {
                    this.release();
                    return;
                }
                if(false) {
                    break;
                }
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }
}

