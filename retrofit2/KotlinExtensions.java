package retrofit2;

import java.lang.reflect.Method;
import kotlin.KotlinNullPointerException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Dispatchers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A%\u0010\u0000\u001A\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001A+\u0010\u0000\u001A\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003H\u0087@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0004\u001A\'\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0007\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001A\u001A\u0010\b\u001A\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\tH\u0086\b¢\u0006\u0002\u0010\n\u001A\u0019\u0010\u000B\u001A\u00020\f*\u00060\rj\u0002`\u000EH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\u000F\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"await", "T", "", "Lretrofit2/Call;", "(Lretrofit2/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitNullable", "awaitResponse", "Lretrofit2/Response;", "create", "Lretrofit2/Retrofit;", "(Lretrofit2/Retrofit;)Ljava/lang/Object;", "suspendAndThrow", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Ljava/lang/Exception;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retrofit"}, k = 2, mv = {1, 1, 15})
public final class KotlinExtensions {
    public static final Object await(Call call0, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(call0) {
            final Call $this_await$inlined;

            {
                this.$this_await$inlined = call0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                this.$this_await$inlined.cancel();
            }
        });
        call0.enqueue(new Callback() {
            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Intrinsics.checkParameterIsNotNull(call0, "call");
                Intrinsics.checkParameterIsNotNull(throwable0, "t");
                Object object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                cancellableContinuationImpl0.resumeWith(object0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                Intrinsics.checkParameterIsNotNull(call0, "call");
                Intrinsics.checkParameterIsNotNull(response0, "response");
                if(response0.isSuccessful()) {
                    Object object0 = response0.body();
                    if(object0 == null) {
                        Object object1 = call0.request().tag(Invocation.class);
                        if(object1 == null) {
                            Intrinsics.throwNpe();
                        }
                        Intrinsics.checkExpressionValueIsNotNull(object1, "call.request().tag(Invocation::class.java)!!");
                        Method method0 = ((Invocation)object1).method();
                        Intrinsics.checkExpressionValueIsNotNull(method0, "method");
                        Class class0 = method0.getDeclaringClass();
                        Intrinsics.checkExpressionValueIsNotNull(class0, "method.declaringClass");
                        Object object2 = Result.constructor-impl(ResultKt.createFailure(new KotlinNullPointerException("Response from " + class0.getName() + '.' + method0.getName() + " was null but response body type was declared as non-null")));
                        cancellableContinuationImpl0.resumeWith(object2);
                        return;
                    }
                    cancellableContinuationImpl0.resumeWith(object0);
                    return;
                }
                Object object3 = Result.constructor-impl(ResultKt.createFailure(new HttpException(response0)));
                cancellableContinuationImpl0.resumeWith(object3);
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object awaitNullable(Call call0, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(call0) {
            final Call $this_await$inlined;

            {
                this.$this_await$inlined = call0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                this.$this_await$inlined.cancel();
            }
        });
        call0.enqueue(new Callback() {
            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Intrinsics.checkParameterIsNotNull(call0, "call");
                Intrinsics.checkParameterIsNotNull(throwable0, "t");
                Object object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                cancellableContinuationImpl0.resumeWith(object0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                Intrinsics.checkParameterIsNotNull(call0, "call");
                Intrinsics.checkParameterIsNotNull(response0, "response");
                if(response0.isSuccessful()) {
                    cancellableContinuationImpl0.resumeWith(response0.body());
                    return;
                }
                Object object0 = Result.constructor-impl(ResultKt.createFailure(new HttpException(response0)));
                cancellableContinuationImpl0.resumeWith(object0);
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object awaitResponse(Call call0, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(call0) {
            final Call $this_awaitResponse$inlined;

            {
                this.$this_awaitResponse$inlined = call0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                this.$this_awaitResponse$inlined.cancel();
            }
        });
        call0.enqueue(new Callback() {
            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Intrinsics.checkParameterIsNotNull(call0, "call");
                Intrinsics.checkParameterIsNotNull(throwable0, "t");
                Object object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                cancellableContinuationImpl0.resumeWith(object0);
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                Intrinsics.checkParameterIsNotNull(call0, "call");
                Intrinsics.checkParameterIsNotNull(response0, "response");
                cancellableContinuationImpl0.resumeWith(response0);
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object create(Retrofit retrofit0) {
        Intrinsics.checkParameterIsNotNull(retrofit0, "$this$create");
        Intrinsics.reifiedOperationMarker(4, "T");
        return retrofit0.create(Object.class);
    }

    public static final Object suspendAndThrow(Exception exception0, Continuation continuation0) {
        retrofit2.KotlinExtensions.suspendAndThrow.1 kotlinExtensions$suspendAndThrow$10;
        if(continuation0 instanceof retrofit2.KotlinExtensions.suspendAndThrow.1) {
            kotlinExtensions$suspendAndThrow$10 = (retrofit2.KotlinExtensions.suspendAndThrow.1)continuation0;
            if((kotlinExtensions$suspendAndThrow$10.label & 0x80000000) == 0) {
                kotlinExtensions$suspendAndThrow$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return KotlinExtensions.suspendAndThrow(null, this);
                    }
                };
            }
            else {
                kotlinExtensions$suspendAndThrow$10.label ^= 0x80000000;
            }
        }
        else {
            kotlinExtensions$suspendAndThrow$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return KotlinExtensions.suspendAndThrow(null, this);
                }
            };
        }
        Object object0 = kotlinExtensions$suspendAndThrow$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(kotlinExtensions$suspendAndThrow$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                kotlinExtensions$suspendAndThrow$10.L$0 = exception0;
                kotlinExtensions$suspendAndThrow$10.label = 1;
                Dispatchers.getDefault().dispatch(kotlinExtensions$suspendAndThrow$10.getContext(), new Runnable() {
                    @Override
                    public final void run() {
                        IntrinsicsKt.intercepted(kotlinExtensions$suspendAndThrow$10).resumeWith(Result.constructor-impl(ResultKt.createFailure(exception0)));
                    }
                });
                Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if(object2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(kotlinExtensions$suspendAndThrow$10);
                }
                return object2 == object1 ? object1 : Unit.INSTANCE;
            }
            case 1: {
                Exception exception1 = (Exception)kotlinExtensions$suspendAndThrow$10.L$0;
                ResultKt.throwOnFailure(object0);
                return Unit.INSTANCE;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }
}

