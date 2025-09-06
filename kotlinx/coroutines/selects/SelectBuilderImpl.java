package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\n\u0010\b\u001A\u0004\u0018\u00010\tH\u0001J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0001R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/selects/SelectBuilderImpl;", "R", "Lkotlinx/coroutines/selects/SelectImplementation;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "cont", "Lkotlinx/coroutines/CancellableContinuationImpl;", "getResult", "", "handleBuilderException", "", "e", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SelectBuilderImpl extends SelectImplementation {
    private final CancellableContinuationImpl cont;

    public SelectBuilderImpl(Continuation continuation0) {
        super(continuation0.getContext());
        this.cont = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
    }

    public final Object getResult() {
        if(this.cont.isCompleted()) {
            return this.cont.getResult();
        }
        CoroutineScope coroutineScope0 = CoroutineScopeKt.CoroutineScope(this.getContext());
        kotlinx.coroutines.selects.SelectBuilderImpl.getResult.1 selectBuilderImpl$getResult$10 = new Function2(null) {
            int label;

            {
                SelectBuilderImpl.this = selectBuilderImpl0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.selects.SelectBuilderImpl.getResult.1(SelectBuilderImpl.this, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.selects.SelectBuilderImpl.getResult.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        try {
                            this.label = 1;
                            object0 = SelectBuilderImpl.this.doSelect(this);
                            if(object0 == object1) {
                                return object1;
                            label_8:
                                ResultKt.throwOnFailure(object0);
                            }
                            break;
                        }
                        catch(Throwable throwable0) {
                            SelectOldKt.access$resumeUndispatchedWithException(SelectBuilderImpl.this.cont, throwable0);
                            return Unit.INSTANCE;
                        }
                    }
                    case 1: {
                        goto label_8;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                SelectOldKt.access$resumeUndispatched(SelectBuilderImpl.this.cont, object0);
                return Unit.INSTANCE;
            }
        };
        BuildersKt__Builders_commonKt.launch$default(coroutineScope0, null, CoroutineStart.UNDISPATCHED, selectBuilderImpl$getResult$10, 1, null);
        return this.cont.getResult();
    }

    public final void handleBuilderException(Throwable throwable0) {
        Object object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
        this.cont.resumeWith(object0);
    }
}

