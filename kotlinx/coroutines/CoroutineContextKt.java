package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A \u0010\u0006\u001A\u00020\u00032\u0006\u0010\u0007\u001A\u00020\u00032\u0006\u0010\b\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\nH\u0002\u001A8\u0010\u000B\u001A\u0002H\f\"\u0004\b\u0000\u0010\f2\n\u0010\r\u001A\u0006\u0012\u0002\b\u00030\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\f0\u0012H\u0080\b¢\u0006\u0002\u0010\u0013\u001A4\u0010\u0014\u001A\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\u0015\u001A\u00020\u00032\b\u0010\u000F\u001A\u0004\u0018\u00010\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\f0\u0012H\u0080\b¢\u0006\u0002\u0010\u0016\u001A\f\u0010\u0017\u001A\u00020\n*\u00020\u0003H\u0002\u001A\u0014\u0010\u0018\u001A\u00020\u0003*\u00020\u00032\u0006\u0010\u0019\u001A\u00020\u0003H\u0007\u001A\u0014\u0010\u0018\u001A\u00020\u0003*\u00020\u001A2\u0006\u0010\u0015\u001A\u00020\u0003H\u0007\u001A\u0013\u0010\u001B\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u001C*\u00020\u001DH\u0080\u0010\u001A(\u0010\u001E\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u001C*\u0006\u0012\u0002\b\u00030\u000E2\u0006\u0010\u0015\u001A\u00020\u00032\b\u0010\u001F\u001A\u0004\u0018\u00010\u0010H\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001A\u0010\u0002\u001A\u0004\u0018\u00010\u0001*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005¨\u0006 "}, d2 = {"DEBUG_THREAD_NAME_SEPARATOR", "", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "foldCopies", "originalContext", "appendContext", "isNewCoroutine", "", "withContinuationContext", "T", "continuation", "Lkotlin/coroutines/Continuation;", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withCoroutineContext", "context", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "hasCopyableElements", "newCoroutineContext", "addedContext", "Lkotlinx/coroutines/CoroutineScope;", "undispatchedCompletion", "Lkotlinx/coroutines/UndispatchedCoroutine;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateUndispatchedCompletion", "oldValue", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutineContextKt {
    private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";

    private static final CoroutineContext foldCopies(CoroutineContext coroutineContext0, CoroutineContext coroutineContext1, boolean z) {
        boolean z1 = CoroutineContextKt.hasCopyableElements(coroutineContext0);
        boolean z2 = CoroutineContextKt.hasCopyableElements(coroutineContext1);
        if(!z1 && !z2) {
            return coroutineContext0.plus(coroutineContext1);
        }
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        ref$ObjectRef0.element = coroutineContext1;
        Function2 function20 = new Function2(ref$ObjectRef0, z) {
            final boolean $isNewCoroutine;
            final ObjectRef $leftoverContext;

            {
                this.$leftoverContext = ref$ObjectRef0;
                this.$isNewCoroutine = z;
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineContext)object0), ((Element)object1));
            }

            public final CoroutineContext invoke(CoroutineContext coroutineContext0, Element coroutineContext$Element0) {
                if(!(coroutineContext$Element0 instanceof CopyableThreadContextElement)) {
                    return coroutineContext0.plus(coroutineContext$Element0);
                }
                Element coroutineContext$Element1 = ((CoroutineContext)this.$leftoverContext.element).get(coroutineContext$Element0.getKey());
                if(coroutineContext$Element1 == null) {
                    CopyableThreadContextElement copyableThreadContextElement0 = (CopyableThreadContextElement)coroutineContext$Element0;
                    if(this.$isNewCoroutine) {
                        copyableThreadContextElement0 = copyableThreadContextElement0.copyForChild();
                    }
                    return coroutineContext0.plus(copyableThreadContextElement0);
                }
                this.$leftoverContext.element = ((CoroutineContext)this.$leftoverContext.element).minusKey(coroutineContext$Element0.getKey());
                return coroutineContext0.plus(((CopyableThreadContextElement)coroutineContext$Element0).mergeForChild(coroutineContext$Element1));
            }
        };
        CoroutineContext coroutineContext2 = (CoroutineContext)coroutineContext0.fold(EmptyCoroutineContext.INSTANCE, function20);
        if(z2) {
            ref$ObjectRef0.element = ((CoroutineContext)ref$ObjectRef0.element).fold(EmptyCoroutineContext.INSTANCE, kotlinx.coroutines.CoroutineContextKt.foldCopies.1.INSTANCE);
        }
        return coroutineContext2.plus(((CoroutineContext)ref$ObjectRef0.element));

        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/CoroutineContext;", "result", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.CoroutineContextKt.foldCopies.1 extends Lambda implements Function2 {
            public static final kotlinx.coroutines.CoroutineContextKt.foldCopies.1 INSTANCE;

            static {
                kotlinx.coroutines.CoroutineContextKt.foldCopies.1.INSTANCE = new kotlinx.coroutines.CoroutineContextKt.foldCopies.1();
            }

            kotlinx.coroutines.CoroutineContextKt.foldCopies.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineContext)object0), ((Element)object1));
            }

            // 去混淆评级： 低(20)
            public final CoroutineContext invoke(CoroutineContext coroutineContext0, Element coroutineContext$Element0) {
                return coroutineContext$Element0 instanceof CopyableThreadContextElement ? coroutineContext0.plus(((CopyableThreadContextElement)coroutineContext$Element0).copyForChild()) : coroutineContext0.plus(coroutineContext$Element0);
            }
        }

    }

    // 去混淆评级： 低(30)
    public static final String getCoroutineName(CoroutineContext coroutineContext0) {
        return null;
    }

    private static final boolean hasCopyableElements(CoroutineContext coroutineContext0) {
        return ((Boolean)coroutineContext0.fold(Boolean.FALSE, kotlinx.coroutines.CoroutineContextKt.hasCopyableElements.1.INSTANCE)).booleanValue();

        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "result", "it", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke", "(ZLkotlin/coroutines/CoroutineContext$Element;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.CoroutineContextKt.hasCopyableElements.1 extends Lambda implements Function2 {
            public static final kotlinx.coroutines.CoroutineContextKt.hasCopyableElements.1 INSTANCE;

            static {
                kotlinx.coroutines.CoroutineContextKt.hasCopyableElements.1.INSTANCE = new kotlinx.coroutines.CoroutineContextKt.hasCopyableElements.1();
            }

            kotlinx.coroutines.CoroutineContextKt.hasCopyableElements.1() {
                super(2);
            }

            // 去混淆评级： 低(20)
            public final Boolean invoke(boolean z, Element coroutineContext$Element0) {
                return z || coroutineContext$Element0 instanceof CopyableThreadContextElement;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((Boolean)object0).booleanValue(), ((Element)object1));
            }
        }

    }

    public static final CoroutineContext newCoroutineContext(CoroutineContext coroutineContext0, CoroutineContext coroutineContext1) {
        return CoroutineContextKt.hasCopyableElements(coroutineContext1) ? CoroutineContextKt.foldCopies(coroutineContext0, coroutineContext1, false) : coroutineContext0.plus(coroutineContext1);
    }

    public static final CoroutineContext newCoroutineContext(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0) {
        CoroutineContext coroutineContext1 = CoroutineContextKt.foldCopies(coroutineScope0.getCoroutineContext(), coroutineContext0, true);
        return coroutineContext1 == Dispatchers.getDefault() || coroutineContext1.get(ContinuationInterceptor.Key) != null ? coroutineContext1 : coroutineContext1.plus(Dispatchers.getDefault());
    }

    public static final UndispatchedCoroutine undispatchedCompletion(CoroutineStackFrame coroutineStackFrame0) {
        do {
            if(coroutineStackFrame0 instanceof DispatchedCoroutine) {
                return null;
            }
            coroutineStackFrame0 = coroutineStackFrame0.getCallerFrame();
            if(coroutineStackFrame0 == null) {
                return null;
            }
        }
        while(!(coroutineStackFrame0 instanceof UndispatchedCoroutine));
        return (UndispatchedCoroutine)coroutineStackFrame0;
    }

    public static final UndispatchedCoroutine updateUndispatchedCompletion(Continuation continuation0, CoroutineContext coroutineContext0, Object object0) {
        if(!(continuation0 instanceof CoroutineStackFrame)) {
            return null;
        }
        if(coroutineContext0.get(UndispatchedMarker.INSTANCE) != null) {
            UndispatchedCoroutine undispatchedCoroutine0 = CoroutineContextKt.undispatchedCompletion(((CoroutineStackFrame)continuation0));
            if(undispatchedCoroutine0 != null) {
                undispatchedCoroutine0.saveThreadContext(coroutineContext0, object0);
            }
            return undispatchedCoroutine0;
        }
        return null;
    }

    public static final Object withContinuationContext(Continuation continuation0, Object object0, Function0 function00) {
        CoroutineContext coroutineContext0 = continuation0.getContext();
        Object object1 = ThreadContextKt.updateThreadContext(coroutineContext0, object0);
        UndispatchedCoroutine undispatchedCoroutine0 = object1 == ThreadContextKt.NO_THREAD_ELEMENTS ? null : CoroutineContextKt.updateUndispatchedCompletion(continuation0, coroutineContext0, object1);
        try {
            return function00.invoke();
        }
        finally {
            if(undispatchedCoroutine0 == null || undispatchedCoroutine0.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(coroutineContext0, object1);
            }
        }
    }

    public static final Object withCoroutineContext(CoroutineContext coroutineContext0, Object object0, Function0 function00) {
        Object object1 = ThreadContextKt.updateThreadContext(coroutineContext0, object0);
        try {
            return function00.invoke();
        }
        finally {
            ThreadContextKt.restoreThreadContext(coroutineContext0, object1);
        }
    }
}

