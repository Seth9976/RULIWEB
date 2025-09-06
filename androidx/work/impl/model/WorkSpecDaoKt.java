package androidx.work.impl.model;

import androidx.work.WorkInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A,\u0010\u0005\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006*\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00070\u00062\u0006\u0010\n\u001A\u00020\u000BH\u0000\u001A\u001A\u0010\f\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006*\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F\u001A&\u0010\u0010\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006*\u00020\r2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0011\u001A\u00020\u0001\u001A&\u0010\u0012\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006*\u00020\r2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0013\u001A\u00020\u0001\"\u0010\u0010\u0000\u001A\u00020\u00018\u0002X\u0083T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001A\u00020\u00018\u0002X\u0083T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001A\u00020\u00018\u0002X\u0083T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"WORK_INFO_BY_IDS", "", "WORK_INFO_BY_NAME", "WORK_INFO_BY_TAG", "WORK_INFO_COLUMNS", "dedup", "Lkotlinx/coroutines/flow/Flow;", "", "Landroidx/work/WorkInfo;", "Landroidx/work/impl/model/WorkSpec$WorkInfoPojo;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getWorkStatusPojoFlowDataForIds", "Landroidx/work/impl/model/WorkSpecDao;", "id", "Ljava/util/UUID;", "getWorkStatusPojoFlowForName", "name", "getWorkStatusPojoFlowForTag", "tag", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkSpecDaoKt {
    private static final String WORK_INFO_BY_IDS = "SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (:ids)";
    private static final String WORK_INFO_BY_NAME = "SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=:name)";
    private static final String WORK_INFO_BY_TAG = "SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=:tag)";
    private static final String WORK_INFO_COLUMNS = "id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason";

    public static final Flow dedup(Flow flow0, CoroutineDispatcher coroutineDispatcher0) {
        Intrinsics.checkNotNullParameter(flow0, "<this>");
        Intrinsics.checkNotNullParameter(coroutineDispatcher0, "dispatcher");
        return FlowKt.flowOn(FlowKt.distinctUntilChanged(new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                FlowCollector flowCollector1 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        androidx.work.impl.model.WorkSpecDaoKt.dedup..inlined.map.1.2.1 workSpecDaoKt$dedup$$inlined$map$1$2$10;
                        if(continuation0 instanceof androidx.work.impl.model.WorkSpecDaoKt.dedup..inlined.map.1.2.1) {
                            workSpecDaoKt$dedup$$inlined$map$1$2$10 = (androidx.work.impl.model.WorkSpecDaoKt.dedup..inlined.map.1.2.1)continuation0;
                            if((workSpecDaoKt$dedup$$inlined$map$1$2$10.label & 0x80000000) == 0) {
                                workSpecDaoKt$dedup$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                    Object L$0;
                                    int label;
                                    Object result;

                                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object object0) {
                                        this.result = object0;
                                        this.label |= 0x80000000;
                                        return continuation0.emit(null, this);
                                    }
                                };
                            }
                            else {
                                workSpecDaoKt$dedup$$inlined$map$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            workSpecDaoKt$dedup$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                Object L$0;
                                int label;
                                Object result;

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    this.result = object0;
                                    this.label |= 0x80000000;
                                    return continuation0.emit(null, this);
                                }
                            };
                        }
                        Object object1 = workSpecDaoKt$dedup$$inlined$map$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(workSpecDaoKt$dedup$$inlined$map$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                FlowCollector flowCollector0 = this.$this_unsafeFlow;
                                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)object0), 10));
                                for(Object object3: ((List)object0)) {
                                    arrayList0.add(((WorkInfoPojo)object3).toWorkInfo());
                                }
                                workSpecDaoKt$dedup$$inlined$map$1$2$10.label = 1;
                                return flowCollector0.emit(arrayList0, workSpecDaoKt$dedup$$inlined$map$1$2$10) == object2 ? object2 : Unit.INSTANCE;
                            }
                            case 1: {
                                ResultKt.throwOnFailure(object1);
                                return Unit.INSTANCE;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                    }
                };
                Object object0 = flow0.collect(flowCollector1, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        }), coroutineDispatcher0);
    }

    public static final Flow getWorkStatusPojoFlowDataForIds(WorkSpecDao workSpecDao0, UUID uUID0) {
        Intrinsics.checkNotNullParameter(workSpecDao0, "<this>");
        Intrinsics.checkNotNullParameter(uUID0, "id");
        return FlowKt.distinctUntilChanged(new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                FlowCollector flowCollector1 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        androidx.work.impl.model.WorkSpecDaoKt.getWorkStatusPojoFlowDataForIds..inlined.map.1.2.1 workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10;
                        if(continuation0 instanceof androidx.work.impl.model.WorkSpecDaoKt.getWorkStatusPojoFlowDataForIds..inlined.map.1.2.1) {
                            workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10 = (androidx.work.impl.model.WorkSpecDaoKt.getWorkStatusPojoFlowDataForIds..inlined.map.1.2.1)continuation0;
                            if((workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10.label & 0x80000000) == 0) {
                                workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                    Object L$0;
                                    int label;
                                    Object result;

                                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object object0) {
                                        this.result = object0;
                                        this.label |= 0x80000000;
                                        return continuation0.emit(null, this);
                                    }
                                };
                            }
                            else {
                                workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10.label ^= 0x80000000;
                            }
                        }
                        else {
                            workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                                Object L$0;
                                int label;
                                Object result;

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    this.result = object0;
                                    this.label |= 0x80000000;
                                    return continuation0.emit(null, this);
                                }
                            };
                        }
                        Object object1 = workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10.result;
                        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10.label) {
                            case 0: {
                                ResultKt.throwOnFailure(object1);
                                WorkInfoPojo workSpec$WorkInfoPojo0 = (WorkInfoPojo)CollectionsKt.firstOrNull(((List)object0));
                                WorkInfo workInfo0 = workSpec$WorkInfoPojo0 == null ? null : workSpec$WorkInfoPojo0.toWorkInfo();
                                workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10.label = 1;
                                return this.$this_unsafeFlow.emit(workInfo0, workSpecDaoKt$getWorkStatusPojoFlowDataForIds$$inlined$map$1$2$10) == object2 ? object2 : Unit.INSTANCE;
                            }
                            case 1: {
                                ResultKt.throwOnFailure(object1);
                                return Unit.INSTANCE;
                            }
                            default: {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            }
                        }
                    }
                };
                Object object0 = workSpecDao0.getWorkStatusPojoFlowDataForIds(CollectionsKt.listOf(String.valueOf(uUID0))).collect(flowCollector1, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
            }
        });
    }

    public static final Flow getWorkStatusPojoFlowForName(WorkSpecDao workSpecDao0, CoroutineDispatcher coroutineDispatcher0, String s) {
        Intrinsics.checkNotNullParameter(workSpecDao0, "<this>");
        Intrinsics.checkNotNullParameter(coroutineDispatcher0, "dispatcher");
        Intrinsics.checkNotNullParameter(s, "name");
        return WorkSpecDaoKt.dedup(workSpecDao0.getWorkStatusPojoFlowForName(s), coroutineDispatcher0);
    }

    public static final Flow getWorkStatusPojoFlowForTag(WorkSpecDao workSpecDao0, CoroutineDispatcher coroutineDispatcher0, String s) {
        Intrinsics.checkNotNullParameter(workSpecDao0, "<this>");
        Intrinsics.checkNotNullParameter(coroutineDispatcher0, "dispatcher");
        Intrinsics.checkNotNullParameter(s, "tag");
        return WorkSpecDaoKt.dedup(workSpecDao0.getWorkStatusPojoFlowForTag(s), coroutineDispatcher0);
    }
}

