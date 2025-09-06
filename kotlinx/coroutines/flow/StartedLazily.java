package kotlinx.coroutines.flow;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001C\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016J\b\u0010\t\u001A\u00020\nH\u0016¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/flow/StartedLazily;", "Lkotlinx/coroutines/flow/SharingStarted;", "()V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class StartedLazily implements SharingStarted {
    @Override  // kotlinx.coroutines.flow.SharingStarted
    public Flow command(StateFlow stateFlow0) {
        return FlowKt.flow(new Function2(null) {
            final StateFlow $subscriptionCount;
            private Object L$0;
            int label;

            {
                this.$subscriptionCount = stateFlow0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.StartedLazily.command.1 startedLazily$command$10 = new kotlinx.coroutines.flow.StartedLazily.command.1(this.$subscriptionCount, continuation0);
                startedLazily$command$10.L$0 = object0;
                return startedLazily$command$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((FlowCollector)object0), ((Continuation)object1));
            }

            public final Object invoke(FlowCollector flowCollector0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.StartedLazily.command.1)this.create(flowCollector0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        FlowCollector flowCollector0 = (FlowCollector)this.L$0;
                        FlowCollector flowCollector1 = new FlowCollector() {
                            public final Object emit(int v, Continuation continuation0) {
                                kotlinx.coroutines.flow.StartedLazily.command.1.1.emit.1 startedLazily$command$1$1$emit$10;
                                if(continuation0 instanceof kotlinx.coroutines.flow.StartedLazily.command.1.1.emit.1) {
                                    startedLazily$command$1$1$emit$10 = (kotlinx.coroutines.flow.StartedLazily.command.1.1.emit.1)continuation0;
                                    if((startedLazily$command$1$1$emit$10.label & 0x80000000) == 0) {
                                        startedLazily$command$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                            int label;
                                            Object result;

                                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                            public final Object invokeSuspend(Object object0) {
                                                this.result = object0;
                                                this.label |= 0x80000000;
                                                return continuation0.emit(0, this);
                                            }
                                        };
                                    }
                                    else {
                                        startedLazily$command$1$1$emit$10.label ^= 0x80000000;
                                    }
                                }
                                else {
                                    startedLazily$command$1$1$emit$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                        int label;
                                        Object result;

                                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                        public final Object invokeSuspend(Object object0) {
                                            this.result = object0;
                                            this.label |= 0x80000000;
                                            return continuation0.emit(0, this);
                                        }
                                    };
                                }
                                Object object0 = startedLazily$command$1$1$emit$10.result;
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(startedLazily$command$1$1$emit$10.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        if(v > 0 && !flowCollector0.element) {
                                            flowCollector0.element = true;
                                            startedLazily$command$1$1$emit$10.label = 1;
                                            return this.$$this$flow.emit(SharingCommand.START, startedLazily$command$1$1$emit$10) == object1 ? object1 : Unit.INSTANCE;
                                        }
                                        return Unit.INSTANCE;
                                    }
                                    case 1: {
                                        ResultKt.throwOnFailure(object0);
                                        return Unit.INSTANCE;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                            }

                            @Override  // kotlinx.coroutines.flow.FlowCollector
                            public Object emit(Object object0, Continuation continuation0) {
                                return this.emit(((Number)object0).intValue(), continuation0);
                            }
                        };
                        this.label = 1;
                        if(this.$subscriptionCount.collect(flowCollector1, this) == object1) {
                            return object1;
                        }
                        break;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                throw new KotlinNothingValueException();
            }
        });
    }

    @Override
    public String toString() {
        return "SharingStarted.Lazily";
    }
}

