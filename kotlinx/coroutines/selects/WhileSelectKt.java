package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A2\u0010\u0000\u001A\u00020\u00012\u001F\b\u0004\u0010\u0002\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0006H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"whileSelect", "", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WhileSelectKt {
    public static final Object whileSelect(Function1 function10, Continuation continuation0) {
        kotlinx.coroutines.selects.WhileSelectKt.whileSelect.1 whileSelectKt$whileSelect$10;
        if(continuation0 instanceof kotlinx.coroutines.selects.WhileSelectKt.whileSelect.1) {
            whileSelectKt$whileSelect$10 = (kotlinx.coroutines.selects.WhileSelectKt.whileSelect.1)continuation0;
            if((whileSelectKt$whileSelect$10.label & 0x80000000) == 0) {
                whileSelectKt$whileSelect$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return WhileSelectKt.whileSelect(null, this);
                    }
                };
            }
            else {
                whileSelectKt$whileSelect$10.label ^= 0x80000000;
            }
        }
        else {
            whileSelectKt$whileSelect$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return WhileSelectKt.whileSelect(null, this);
                }
            };
        }
        Object object0 = whileSelectKt$whileSelect$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(whileSelectKt$whileSelect$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                goto label_18;
            }
            case 1: {
                function10 = (Function1)whileSelectKt$whileSelect$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        do {
            if(!((Boolean)object0).booleanValue()) {
                return Unit.INSTANCE;
            }
        label_18:
            SelectImplementation selectImplementation0 = new SelectImplementation(whileSelectKt$whileSelect$10.getContext());
            function10.invoke(selectImplementation0);
            whileSelectKt$whileSelect$10.L$0 = function10;
            whileSelectKt$whileSelect$10.label = 1;
            object0 = selectImplementation0.doSelect(whileSelectKt$whileSelect$10);
        }
        while(object0 != object1);
        return object1;
    }

    private static final Object whileSelect$$forInline(Function1 function10, Continuation continuation0) {
        throw new NullPointerException();
    }
}

