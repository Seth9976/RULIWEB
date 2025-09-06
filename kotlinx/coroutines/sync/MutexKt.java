package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0010\u0010\n\u001A\u00020\u000B2\b\b\u0002\u0010\f\u001A\u00020\r\u001AB\u0010\u000E\u001A\u0002H\u000F\"\u0004\b\u0000\u0010\u000F*\u00020\u000B2\n\b\u0002\u0010\u0010\u001A\u0004\u0018\u00010\u00112\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u0013H\u0086Hø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\u0014\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\b\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\t\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"HOLDS_LOCK_ANOTHER_OWNER", "", "HOLDS_LOCK_UNLOCKED", "HOLDS_LOCK_YES", "NO_OWNER", "Lkotlinx/coroutines/internal/Symbol;", "ON_LOCK_ALREADY_LOCKED_BY_OWNER", "TRY_LOCK_ALREADY_LOCKED_BY_OWNER", "TRY_LOCK_FAILED", "TRY_LOCK_SUCCESS", "Mutex", "Lkotlinx/coroutines/sync/Mutex;", "locked", "", "withLock", "T", "owner", "", "action", "Lkotlin/Function0;", "(Lkotlinx/coroutines/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class MutexKt {
    private static final int HOLDS_LOCK_ANOTHER_OWNER = 2;
    private static final int HOLDS_LOCK_UNLOCKED = 0;
    private static final int HOLDS_LOCK_YES = 1;
    private static final Symbol NO_OWNER = null;
    private static final Symbol ON_LOCK_ALREADY_LOCKED_BY_OWNER = null;
    private static final int TRY_LOCK_ALREADY_LOCKED_BY_OWNER = 2;
    private static final int TRY_LOCK_FAILED = 1;
    private static final int TRY_LOCK_SUCCESS;

    static {
        MutexKt.NO_OWNER = new Symbol("NO_OWNER");
        MutexKt.ON_LOCK_ALREADY_LOCKED_BY_OWNER = new Symbol("ALREADY_LOCKED_BY_OWNER");
    }

    public static final Mutex Mutex(boolean z) {
        return new MutexImpl(z);
    }

    public static Mutex Mutex$default(boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        return MutexKt.Mutex(z);
    }

    public static final Object withLock(Mutex mutex0, Object object0, Function0 function00, Continuation continuation0) {
        kotlinx.coroutines.sync.MutexKt.withLock.1 mutexKt$withLock$10;
        if(continuation0 instanceof kotlinx.coroutines.sync.MutexKt.withLock.1) {
            mutexKt$withLock$10 = (kotlinx.coroutines.sync.MutexKt.withLock.1)continuation0;
            if((mutexKt$withLock$10.label & 0x80000000) == 0) {
                mutexKt$withLock$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return MutexKt.withLock(null, null, null, this);
                    }
                };
            }
            else {
                mutexKt$withLock$10.label ^= 0x80000000;
            }
        }
        else {
            mutexKt$withLock$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return MutexKt.withLock(null, null, null, this);
                }
            };
        }
        Object object1 = mutexKt$withLock$10.result;
        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(mutexKt$withLock$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object1);
                mutexKt$withLock$10.L$0 = mutex0;
                mutexKt$withLock$10.L$1 = object0;
                mutexKt$withLock$10.L$2 = function00;
                mutexKt$withLock$10.label = 1;
                if(mutex0.lock(object0, mutexKt$withLock$10) == object2) {
                    return object2;
                }
                break;
            }
            case 1: {
                function00 = (Function0)mutexKt$withLock$10.L$2;
                object0 = mutexKt$withLock$10.L$1;
                mutex0 = (Mutex)mutexKt$withLock$10.L$0;
                ResultKt.throwOnFailure(object1);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            return function00.invoke();
        }
        finally {
            mutex0.unlock(object0);
        }
    }

    private static final Object withLock$$forInline(Mutex mutex0, Object object0, Function0 function00, Continuation continuation0) {
        mutex0.lock(object0, continuation0);
        try {
            return function00.invoke();
        }
        finally {
            mutex0.unlock(object0);
        }
    }

    public static Object withLock$default(Mutex mutex0, Object object0, Function0 function00, Continuation continuation0, int v, Object object1) {
        if((v & 1) != 0) {
            object0 = null;
        }
        mutex0.lock(object0, continuation0);
        try {
            return function00.invoke();
        }
        finally {
            mutex0.unlock(object0);
        }
    }
}

