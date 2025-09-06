package kotlinx.coroutines;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u0002\u001A=\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001E\u0010\u0003\u001A\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004\"\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001A%\u0010\u0007\u001A\u00020\b2\u0012\u0010\t\u001A\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0004\"\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001A-\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001A\u001B\u0010\u0007\u001A\u00020\b*\b\u0012\u0004\u0012\u00020\n0\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000E"}, d2 = {"awaitAll", "", "T", "deferreds", "", "Lkotlinx/coroutines/Deferred;", "([Lkotlinx/coroutines/Deferred;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "joinAll", "", "jobs", "Lkotlinx/coroutines/Job;", "([Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "(Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class AwaitKt {
    public static final Object awaitAll(Collection collection0, Continuation continuation0) {
        return collection0.isEmpty() ? CollectionsKt.emptyList() : new AwaitAll(((Deferred[])collection0.toArray(new Deferred[0]))).await(continuation0);
    }

    public static final Object awaitAll(Deferred[] arr_deferred, Continuation continuation0) {
        return arr_deferred.length == 0 ? CollectionsKt.emptyList() : new AwaitAll(arr_deferred).await(continuation0);
    }

    public static final Object joinAll(Collection collection0, Continuation continuation0) {
        Iterator iterator0;
        kotlinx.coroutines.AwaitKt.joinAll.3 awaitKt$joinAll$30;
        if(continuation0 instanceof kotlinx.coroutines.AwaitKt.joinAll.3) {
            awaitKt$joinAll$30 = (kotlinx.coroutines.AwaitKt.joinAll.3)continuation0;
            if((awaitKt$joinAll$30.label & 0x80000000) == 0) {
                awaitKt$joinAll$30 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return AwaitKt.joinAll(null, this);
                    }
                };
            }
            else {
                awaitKt$joinAll$30.label ^= 0x80000000;
            }
        }
        else {
            awaitKt$joinAll$30 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return AwaitKt.joinAll(null, this);
                }
            };
        }
        Object object0 = awaitKt$joinAll$30.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(awaitKt$joinAll$30.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                iterator0 = collection0.iterator();
                break;
            }
            case 1: {
                iterator0 = (Iterator)awaitKt$joinAll$30.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        while(iterator0.hasNext()) {
            Object object2 = iterator0.next();
            awaitKt$joinAll$30.L$0 = iterator0;
            awaitKt$joinAll$30.label = 1;
            if(((Job)object2).join(awaitKt$joinAll$30) == object1) {
                return object1;
            }
            if(false) {
                break;
            }
        }
        return Unit.INSTANCE;
    }

    public static final Object joinAll(Job[] arr_job, Continuation continuation0) {
        int v1;
        Job[] arr_job1;
        int v;
        kotlinx.coroutines.AwaitKt.joinAll.1 awaitKt$joinAll$10;
        if(continuation0 instanceof kotlinx.coroutines.AwaitKt.joinAll.1) {
            awaitKt$joinAll$10 = (kotlinx.coroutines.AwaitKt.joinAll.1)continuation0;
            if((awaitKt$joinAll$10.label & 0x80000000) == 0) {
                awaitKt$joinAll$10 = new ContinuationImpl(continuation0) {
                    int I$0;
                    int I$1;
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return AwaitKt.joinAll(null, this);
                    }
                };
            }
            else {
                awaitKt$joinAll$10.label ^= 0x80000000;
            }
        }
        else {
            awaitKt$joinAll$10 = new ContinuationImpl(continuation0) {
                int I$0;
                int I$1;
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return AwaitKt.joinAll(null, this);
                }
            };
        }
        Object object0 = awaitKt$joinAll$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(awaitKt$joinAll$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                v = 0;
                arr_job1 = arr_job;
                v1 = arr_job.length;
                goto label_23;
            }
            case 1: {
                v1 = awaitKt$joinAll$10.I$1;
                v = awaitKt$joinAll$10.I$0;
                Job[] arr_job2 = (Job[])awaitKt$joinAll$10.L$0;
                ResultKt.throwOnFailure(object0);
                arr_job1 = arr_job2;
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        while(true) {
            ++v;
        label_23:
            if(v >= v1) {
                break;
            }
            awaitKt$joinAll$10.L$0 = arr_job1;
            awaitKt$joinAll$10.I$0 = v;
            awaitKt$joinAll$10.I$1 = v1;
            awaitKt$joinAll$10.label = 1;
            if(arr_job1[v].join(awaitKt$joinAll$10) != object1) {
                continue;
            }
            return object1;
        }
        return Unit.INSTANCE;
    }
}

