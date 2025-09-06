package kotlinx.coroutines.internal;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001AI\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004*\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001j\b\u0012\u0004\u0012\u0002H\u0004`\u00052\u0006\u0010\u0006\u001A\u0002H\u00042\u0006\u0010\u0007\u001A\u00020\bH\u0000¢\u0006\u0002\u0010\t\u001A=\u0010\n\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001j\b\u0012\u0004\u0012\u0002H\u0004`\u00052\u0006\u0010\u0006\u001A\u0002H\u00042\u0006\u0010\u0007\u001A\u00020\bH\u0000¢\u0006\u0002\u0010\u000B\u001AC\u0010\f\u001A\u0004\u0018\u00010\r\"\u0004\b\u0000\u0010\u0004*\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001j\b\u0012\u0004\u0012\u0002H\u0004`\u00052\u0006\u0010\u0006\u001A\u0002H\u00042\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\rH\u0000¢\u0006\u0002\u0010\u000F**\b\u0000\u0010\u0010\u001A\u0004\b\u0000\u0010\u0004\"\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u00012\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\u0011"}, d2 = {"bindCancellationFun", "Lkotlin/Function1;", "", "", "E", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "element", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)Lkotlin/jvm/functions/Function1;", "callUndeliveredElement", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;)V", "callUndeliveredElementCatchingException", "Lkotlinx/coroutines/internal/UndeliveredElementException;", "undeliveredElementException", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlinx/coroutines/internal/UndeliveredElementException;)Lkotlinx/coroutines/internal/UndeliveredElementException;", "OnUndeliveredElement", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class OnUndeliveredElementKt {
    public static final Function1 bindCancellationFun(Function1 function10, Object object0, CoroutineContext coroutineContext0) {
        return new Function1(function10, object0, coroutineContext0) {
            final CoroutineContext $context;
            final Object $element;
            final Function1 $this_bindCancellationFun;

            {
                this.$this_bindCancellationFun = function10;
                this.$element = object0;
                this.$context = coroutineContext0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                OnUndeliveredElementKt.callUndeliveredElement(this.$this_bindCancellationFun, this.$element, this.$context);
            }
        };
    }

    public static final void callUndeliveredElement(Function1 function10, Object object0, CoroutineContext coroutineContext0) {
        UndeliveredElementException undeliveredElementException0 = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function10, object0, null);
        if(undeliveredElementException0 != null) {
            CoroutineExceptionHandlerKt.handleCoroutineException(coroutineContext0, undeliveredElementException0);
        }
    }

    public static final UndeliveredElementException callUndeliveredElementCatchingException(Function1 function10, Object object0, UndeliveredElementException undeliveredElementException0) {
        try {
            function10.invoke(object0);
            return undeliveredElementException0;
        }
        catch(Throwable throwable0) {
            if(undeliveredElementException0 != null && undeliveredElementException0.getCause() != throwable0) {
                ExceptionsKt.addSuppressed(undeliveredElementException0, throwable0);
                return undeliveredElementException0;
            }
            return new UndeliveredElementException("Exception in undelivered element handler for " + object0, throwable0);
        }
    }

    public static UndeliveredElementException callUndeliveredElementCatchingException$default(Function1 function10, Object object0, UndeliveredElementException undeliveredElementException0, int v, Object object1) {
        if((v & 2) != 0) {
            undeliveredElementException0 = null;
        }
        return OnUndeliveredElementKt.callUndeliveredElementCatchingException(function10, object0, undeliveredElementException0);
    }
}

