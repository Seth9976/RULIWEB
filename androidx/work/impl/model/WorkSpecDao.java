package androidx.work.impl.model;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.WorkInfo.State;
import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0002\b\u0019\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H\'J\u0010\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000B\u001A\u00020\u0003H\'J\u000E\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u00070\tH\'J\u000E\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u00070\tH\'J\u0014\u0010\u000E\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t0\u000FH\'J\u0016\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0011\u001A\u00020\u0003H\'J\u000E\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\n0\tH\'J\u0016\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00140\t2\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0016\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0016\u001A\u00020\u0017H\'J\u000E\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\n0\tH\'J\u0018\u0010\u0019\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u000F2\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u000E\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\n0\tH\'J\u0012\u0010\u001B\u001A\u0004\u0018\u00010\u001C2\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0016\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\u001E\u001A\u00020\u0007H\'J\u0016\u0010\u001F\u001A\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010 \u001A\u00020\u0007H\'J\u0012\u0010!\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0016\u0010\"\u001A\b\u0012\u0004\u0012\u00020#0\t2\u0006\u0010\u001E\u001A\u00020\u0007H\'J\"\u0010$\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t0%2\f\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00070\tH\'J\u001C\u0010(\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t0%2\u0006\u0010\u001E\u001A\u00020\u0007H\'J\u001C\u0010)\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t0%2\u0006\u0010 \u001A\u00020\u0007H\'J\u0012\u0010*\u001A\u0004\u0018\u00010&2\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u001C\u0010+\u001A\b\u0012\u0004\u0012\u00020&0\t2\f\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00070\tH\'J\u0016\u0010,\u001A\b\u0012\u0004\u0012\u00020&0\t2\u0006\u0010\u001E\u001A\u00020\u0007H\'J\u0016\u0010-\u001A\b\u0012\u0004\u0012\u00020&0\t2\u0006\u0010 \u001A\u00020\u0007H\'J\"\u0010.\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t0\u000F2\f\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00070\tH\'J\u001C\u0010/\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t0\u000F2\u0006\u0010\u001E\u001A\u00020\u0007H\'J\u001C\u00100\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t0\u000F2\u0006\u0010 \u001A\u00020\u0007H\'J\u000E\u00101\u001A\b\u0012\u0004\u0012\u0002020%H\'J\u0010\u00103\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0010\u00104\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0010\u00105\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0010\u00106\u001A\u00020\u00052\u0006\u00107\u001A\u00020\nH\'J\u0018\u00108\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u00109\u001A\u00020\u0017H\'J\b\u0010:\u001A\u00020\u0005H\'J\b\u0010;\u001A\u00020\u0003H\'J\u0018\u0010<\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010=\u001A\u00020\u0003H\'J\u0010\u0010>\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0010\u0010?\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0018\u0010@\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010A\u001A\u00020\u0017H\'J\u0018\u0010B\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010C\u001A\u00020\u0017H\'J\u0018\u0010D\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010E\u001A\u00020\u0014H\'J\u0018\u0010F\u001A\u00020\u00032\u0006\u0010G\u001A\u00020\u001C2\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0018\u0010H\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010I\u001A\u00020\u0003H\'J\u0010\u0010J\u001A\u00020\u00052\u0006\u00107\u001A\u00020\nH\'\u00F8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00A8\u0006K\u00C0\u0006\u0001"}, d2 = {"Landroidx/work/impl/model/WorkSpecDao;", "", "countNonFinishedContentUriTriggerWorkers", "", "delete", "", "id", "", "getAllEligibleWorkSpecsForScheduling", "", "Landroidx/work/impl/model/WorkSpec;", "maxLimit", "getAllUnfinishedWork", "getAllWorkSpecIds", "getAllWorkSpecIdsLiveData", "Landroidx/lifecycle/LiveData;", "getEligibleWorkForScheduling", "schedulerLimit", "getEligibleWorkForSchedulingWithContentUris", "getInputsFromPrerequisites", "Landroidx/work/Data;", "getRecentlyCompletedWork", "startingAt", "", "getRunningWork", "getScheduleRequestedAtLiveData", "getScheduledWork", "getState", "Landroidx/work/WorkInfo$State;", "getUnfinishedWorkWithName", "name", "getUnfinishedWorkWithTag", "tag", "getWorkSpec", "getWorkSpecIdAndStatesForName", "Landroidx/work/impl/model/WorkSpec$IdAndState;", "getWorkStatusPojoFlowDataForIds", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/work/impl/model/WorkSpec$WorkInfoPojo;", "ids", "getWorkStatusPojoFlowForName", "getWorkStatusPojoFlowForTag", "getWorkStatusPojoForId", "getWorkStatusPojoForIds", "getWorkStatusPojoForName", "getWorkStatusPojoForTag", "getWorkStatusPojoLiveDataForIds", "getWorkStatusPojoLiveDataForName", "getWorkStatusPojoLiveDataForTag", "hasUnfinishedWorkFlow", "", "incrementGeneration", "incrementPeriodCount", "incrementWorkSpecRunAttemptCount", "insertWorkSpec", "workSpec", "markWorkSpecScheduled", "startTime", "pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast", "resetScheduledState", "resetWorkSpecNextScheduleTimeOverride", "overrideGeneration", "resetWorkSpecRunAttemptCount", "setCancelledState", "setLastEnqueueTime", "enqueueTime", "setNextScheduleTimeOverride", "nextScheduleTimeOverrideMillis", "setOutput", "output", "setState", "state", "setStopReason", "stopReason", "updateWorkSpec", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface WorkSpecDao {
    int countNonFinishedContentUriTriggerWorkers();

    void delete(String arg1);

    List getAllEligibleWorkSpecsForScheduling(int arg1);

    List getAllUnfinishedWork();

    List getAllWorkSpecIds();

    LiveData getAllWorkSpecIdsLiveData();

    List getEligibleWorkForScheduling(int arg1);

    List getEligibleWorkForSchedulingWithContentUris();

    List getInputsFromPrerequisites(String arg1);

    List getRecentlyCompletedWork(long arg1);

    List getRunningWork();

    LiveData getScheduleRequestedAtLiveData(String arg1);

    List getScheduledWork();

    State getState(String arg1);

    List getUnfinishedWorkWithName(String arg1);

    List getUnfinishedWorkWithTag(String arg1);

    WorkSpec getWorkSpec(String arg1);

    List getWorkSpecIdAndStatesForName(String arg1);

    Flow getWorkStatusPojoFlowDataForIds(List arg1);

    Flow getWorkStatusPojoFlowForName(String arg1);

    Flow getWorkStatusPojoFlowForTag(String arg1);

    WorkInfoPojo getWorkStatusPojoForId(String arg1);

    List getWorkStatusPojoForIds(List arg1);

    List getWorkStatusPojoForName(String arg1);

    List getWorkStatusPojoForTag(String arg1);

    LiveData getWorkStatusPojoLiveDataForIds(List arg1);

    LiveData getWorkStatusPojoLiveDataForName(String arg1);

    LiveData getWorkStatusPojoLiveDataForTag(String arg1);

    Flow hasUnfinishedWorkFlow();

    void incrementGeneration(String arg1);

    void incrementPeriodCount(String arg1);

    int incrementWorkSpecRunAttemptCount(String arg1);

    void insertWorkSpec(WorkSpec arg1);

    int markWorkSpecScheduled(String arg1, long arg2);

    void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();

    int resetScheduledState();

    void resetWorkSpecNextScheduleTimeOverride(String arg1, int arg2);

    int resetWorkSpecRunAttemptCount(String arg1);

    int setCancelledState(String arg1);

    void setLastEnqueueTime(String arg1, long arg2);

    void setNextScheduleTimeOverride(String arg1, long arg2);

    void setOutput(String arg1, Data arg2);

    int setState(State arg1, String arg2);

    void setStopReason(String arg1, int arg2);

    void updateWorkSpec(WorkSpec arg1);
}

