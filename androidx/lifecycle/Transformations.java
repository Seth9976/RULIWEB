package androidx.lifecycle;

import androidx.arch.core.util.Function;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u001E\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0007\u001AB\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001C\u0010\u0005\u001A\u0018\u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u0007\u0012\t\u0012\u0007H\u0004¢\u0006\u0002\b\u00070\u0006H\u0007\u001A8\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\b\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\tH\u0007\u001AJ\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00012$\u0010\u0005\u001A \u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u0007\u0012\u0011\u0012\u000F\u0012\u0004\u0012\u0002H\u0004\u0018\u00010\u0001¢\u0006\u0002\b\u00070\u0006H\u0007\u001A>\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0018\u0010\u000B\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00010\tH\u0007¨\u0006\f"}, d2 = {"distinctUntilChanged", "Landroidx/lifecycle/LiveData;", "X", "map", "Y", "transform", "Lkotlin/Function1;", "Lkotlin/jvm/JvmSuppressWildcards;", "mapFunction", "Landroidx/arch/core/util/Function;", "switchMap", "switchMapFunction", "lifecycle-livedata_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class Transformations {
    public static final LiveData distinctUntilChanged(LiveData liveData0) {
        Intrinsics.checkNotNullParameter(liveData0, "<this>");
        MediatorLiveData mediatorLiveData0 = new MediatorLiveData();
        BooleanRef ref$BooleanRef0 = new BooleanRef();
        ref$BooleanRef0.element = true;
        if(liveData0.isInitialized()) {
            mediatorLiveData0.setValue(liveData0.getValue());
            ref$BooleanRef0.element = false;
        }
        mediatorLiveData0.addSource(liveData0, new Transformations.sam.androidx_lifecycle_Observer.0(new Function1(mediatorLiveData0, ref$BooleanRef0) {
            final BooleanRef $firstTime;
            final MediatorLiveData $outputLiveData;

            {
                this.$outputLiveData = mediatorLiveData0;
                this.$firstTime = ref$BooleanRef0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(object0);
                return Unit.INSTANCE;
            }

            public final void invoke(Object object0) {
                Object object1 = this.$outputLiveData.getValue();
                if(!this.$firstTime.element && (object1 != null || object0 == null) && (object1 == null || Intrinsics.areEqual(object1, object0))) {
                    return;
                }
                this.$firstTime.element = false;
                this.$outputLiveData.setValue(object0);
            }
        }));
        return mediatorLiveData0;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    public static final LiveData map(LiveData liveData0, Function function0) {
        Intrinsics.checkNotNullParameter(liveData0, "<this>");
        Intrinsics.checkNotNullParameter(function0, "mapFunction");
        MediatorLiveData mediatorLiveData0 = new MediatorLiveData();
        mediatorLiveData0.addSource(liveData0, new Transformations.sam.androidx_lifecycle_Observer.0(new Function1(mediatorLiveData0, function0) {
            final Function $mapFunction;
            final MediatorLiveData $result;

            {
                this.$result = mediatorLiveData0;
                this.$mapFunction = function0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(object0);
                return Unit.INSTANCE;
            }

            public final void invoke(Object object0) {
                Object object1 = this.$mapFunction.apply(object0);
                this.$result.setValue(object1);
            }
        }));
        return mediatorLiveData0;
    }

    public static final LiveData map(LiveData liveData0, Function1 function10) {
        Intrinsics.checkNotNullParameter(liveData0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        MediatorLiveData mediatorLiveData0 = new MediatorLiveData();
        mediatorLiveData0.addSource(liveData0, new Transformations.sam.androidx_lifecycle_Observer.0(new Function1(mediatorLiveData0, function10) {
            final MediatorLiveData $result;
            final Function1 $transform;

            {
                this.$result = mediatorLiveData0;
                this.$transform = function10;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(object0);
                return Unit.INSTANCE;
            }

            public final void invoke(Object object0) {
                Object object1 = this.$transform.invoke(object0);
                this.$result.setValue(object1);
            }
        }));
        return mediatorLiveData0;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use kotlin functions, instead of outdated arch core Functions")
    public static final LiveData switchMap(LiveData liveData0, Function function0) {
        Intrinsics.checkNotNullParameter(liveData0, "<this>");
        Intrinsics.checkNotNullParameter(function0, "switchMapFunction");
        MediatorLiveData mediatorLiveData0 = new MediatorLiveData();
        mediatorLiveData0.addSource(liveData0, new Observer() {
            private LiveData liveData;

            public final LiveData getLiveData() {
                return this.liveData;
            }

            @Override  // androidx.lifecycle.Observer
            public void onChanged(Object object0) {
                LiveData liveData0 = (LiveData)function0.apply(object0);
                LiveData liveData1 = this.liveData;
                if(liveData1 != liveData0) {
                    if(liveData1 != null) {
                        Intrinsics.checkNotNull(liveData1);
                        mediatorLiveData0.removeSource(liveData1);
                    }
                    this.liveData = liveData0;
                    if(liveData0 != null) {
                        Intrinsics.checkNotNull(liveData0);
                        Observer observer0 = new Transformations.sam.androidx_lifecycle_Observer.0(new Function1() {
                            final MediatorLiveData $result;

                            {
                                this.$result = mediatorLiveData0;
                                super(1);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                this.invoke(object0);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Object object0) {
                                this.$result.setValue(object0);
                            }
                        });
                        mediatorLiveData0.addSource(liveData0, observer0);
                    }
                }
            }

            public final void setLiveData(LiveData liveData0) {
                this.liveData = liveData0;
            }
        });
        return mediatorLiveData0;
    }

    public static final LiveData switchMap(LiveData liveData0, Function1 function10) {
        Intrinsics.checkNotNullParameter(liveData0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "transform");
        MediatorLiveData mediatorLiveData0 = new MediatorLiveData();
        mediatorLiveData0.addSource(liveData0, new Observer() {
            private LiveData liveData;

            public final LiveData getLiveData() {
                return this.liveData;
            }

            @Override  // androidx.lifecycle.Observer
            public void onChanged(Object object0) {
                LiveData liveData0 = (LiveData)function10.invoke(object0);
                LiveData liveData1 = this.liveData;
                if(liveData1 != liveData0) {
                    if(liveData1 != null) {
                        Intrinsics.checkNotNull(liveData1);
                        mediatorLiveData0.removeSource(liveData1);
                    }
                    this.liveData = liveData0;
                    if(liveData0 != null) {
                        Intrinsics.checkNotNull(liveData0);
                        Observer observer0 = new Transformations.sam.androidx_lifecycle_Observer.0(new Function1() {
                            final MediatorLiveData $result;

                            {
                                this.$result = mediatorLiveData0;
                                super(1);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                this.invoke(object0);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Object object0) {
                                this.$result.setValue(object0);
                            }
                        });
                        mediatorLiveData0.addSource(liveData0, observer0);
                    }
                }
            }

            public final void setLiveData(LiveData liveData0) {
                this.liveData = liveData0;
            }
        });
        return mediatorLiveData0;
    }
}

