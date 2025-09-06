package androidx.work.impl.model;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlinx.coroutines.flow.Flow;

public final class WorkSpecDao_Impl implements WorkSpecDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final SharedSQLiteStatement __preparedStmtOfIncrementGeneration;
    private final SharedSQLiteStatement __preparedStmtOfIncrementPeriodCount;
    private final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;
    private final SharedSQLiteStatement __preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast;
    private final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecNextScheduleTimeOverride;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfSetCancelledState;
    private final SharedSQLiteStatement __preparedStmtOfSetLastEnqueueTime;
    private final SharedSQLiteStatement __preparedStmtOfSetNextScheduleTimeOverride;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetState;
    private final SharedSQLiteStatement __preparedStmtOfSetStopReason;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfWorkSpec;

    public WorkSpecDao_Impl(RoomDatabase roomDatabase0) {
        this.__db = roomDatabase0;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter(roomDatabase0) {
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, WorkSpec workSpec0) {
                supportSQLiteStatement0.bindString(1, workSpec0.id);
                supportSQLiteStatement0.bindLong(2, ((long)WorkTypeConverters.stateToInt(workSpec0.state)));
                supportSQLiteStatement0.bindString(3, workSpec0.workerClassName);
                supportSQLiteStatement0.bindString(4, workSpec0.inputMergerClassName);
                supportSQLiteStatement0.bindBlob(5, Data.toByteArrayInternalV1(workSpec0.input));
                supportSQLiteStatement0.bindBlob(6, Data.toByteArrayInternalV1(workSpec0.output));
                supportSQLiteStatement0.bindLong(7, workSpec0.initialDelay);
                supportSQLiteStatement0.bindLong(8, workSpec0.intervalDuration);
                supportSQLiteStatement0.bindLong(9, workSpec0.flexDuration);
                supportSQLiteStatement0.bindLong(10, ((long)workSpec0.runAttemptCount));
                supportSQLiteStatement0.bindLong(11, ((long)WorkTypeConverters.backoffPolicyToInt(workSpec0.backoffPolicy)));
                supportSQLiteStatement0.bindLong(12, workSpec0.backoffDelayDuration);
                supportSQLiteStatement0.bindLong(13, workSpec0.lastEnqueueTime);
                supportSQLiteStatement0.bindLong(14, workSpec0.minimumRetentionDuration);
                supportSQLiteStatement0.bindLong(15, workSpec0.scheduleRequestedAt);
                supportSQLiteStatement0.bindLong(16, ((long)workSpec0.expedited));
                supportSQLiteStatement0.bindLong(17, ((long)WorkTypeConverters.outOfQuotaPolicyToInt(workSpec0.outOfQuotaPolicy)));
                supportSQLiteStatement0.bindLong(18, ((long)workSpec0.getPeriodCount()));
                supportSQLiteStatement0.bindLong(19, ((long)workSpec0.getGeneration()));
                supportSQLiteStatement0.bindLong(20, workSpec0.getNextScheduleTimeOverride());
                supportSQLiteStatement0.bindLong(21, ((long)workSpec0.getNextScheduleTimeOverrideGeneration()));
                supportSQLiteStatement0.bindLong(22, ((long)workSpec0.getStopReason()));
                if(workSpec0.getTraceTag() == null) {
                    supportSQLiteStatement0.bindNull(23);
                }
                else {
                    supportSQLiteStatement0.bindString(23, workSpec0.getTraceTag());
                }
                Constraints constraints0 = workSpec0.constraints;
                supportSQLiteStatement0.bindLong(24, ((long)WorkTypeConverters.networkTypeToInt(constraints0.getRequiredNetworkType())));
                supportSQLiteStatement0.bindBlob(25, WorkTypeConverters.fromNetworkRequest$work_runtime_release(constraints0.getRequiredNetworkRequestCompat$work_runtime_release()));
                supportSQLiteStatement0.bindLong(26, ((long)constraints0.requiresCharging()));
                supportSQLiteStatement0.bindLong(27, ((long)constraints0.requiresDeviceIdle()));
                supportSQLiteStatement0.bindLong(28, ((long)constraints0.requiresBatteryNotLow()));
                supportSQLiteStatement0.bindLong(29, ((long)constraints0.requiresStorageNotLow()));
                supportSQLiteStatement0.bindLong(30, constraints0.getContentTriggerUpdateDelayMillis());
                supportSQLiteStatement0.bindLong(0x1F, constraints0.getContentTriggerMaxDelayMillis());
                supportSQLiteStatement0.bindBlob(0x20, WorkTypeConverters.setOfTriggersToByteArray(constraints0.getContentUriTriggers()));
            }

            @Override  // androidx.room.EntityInsertionAdapter
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Object object0) {
                this.bind(supportSQLiteStatement0, ((WorkSpec)object0));
            }

            @Override  // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`next_schedule_time_override`,`next_schedule_time_override_generation`,`stop_reason`,`trace_tag`,`required_network_type`,`required_network_request`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
        };
        this.__updateAdapterOfWorkSpec = new EntityDeletionOrUpdateAdapter(roomDatabase0) {
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, WorkSpec workSpec0) {
                supportSQLiteStatement0.bindString(1, workSpec0.id);
                supportSQLiteStatement0.bindLong(2, ((long)WorkTypeConverters.stateToInt(workSpec0.state)));
                supportSQLiteStatement0.bindString(3, workSpec0.workerClassName);
                supportSQLiteStatement0.bindString(4, workSpec0.inputMergerClassName);
                supportSQLiteStatement0.bindBlob(5, Data.toByteArrayInternalV1(workSpec0.input));
                supportSQLiteStatement0.bindBlob(6, Data.toByteArrayInternalV1(workSpec0.output));
                supportSQLiteStatement0.bindLong(7, workSpec0.initialDelay);
                supportSQLiteStatement0.bindLong(8, workSpec0.intervalDuration);
                supportSQLiteStatement0.bindLong(9, workSpec0.flexDuration);
                supportSQLiteStatement0.bindLong(10, ((long)workSpec0.runAttemptCount));
                supportSQLiteStatement0.bindLong(11, ((long)WorkTypeConverters.backoffPolicyToInt(workSpec0.backoffPolicy)));
                supportSQLiteStatement0.bindLong(12, workSpec0.backoffDelayDuration);
                supportSQLiteStatement0.bindLong(13, workSpec0.lastEnqueueTime);
                supportSQLiteStatement0.bindLong(14, workSpec0.minimumRetentionDuration);
                supportSQLiteStatement0.bindLong(15, workSpec0.scheduleRequestedAt);
                supportSQLiteStatement0.bindLong(16, ((long)workSpec0.expedited));
                supportSQLiteStatement0.bindLong(17, ((long)WorkTypeConverters.outOfQuotaPolicyToInt(workSpec0.outOfQuotaPolicy)));
                supportSQLiteStatement0.bindLong(18, ((long)workSpec0.getPeriodCount()));
                supportSQLiteStatement0.bindLong(19, ((long)workSpec0.getGeneration()));
                supportSQLiteStatement0.bindLong(20, workSpec0.getNextScheduleTimeOverride());
                supportSQLiteStatement0.bindLong(21, ((long)workSpec0.getNextScheduleTimeOverrideGeneration()));
                supportSQLiteStatement0.bindLong(22, ((long)workSpec0.getStopReason()));
                if(workSpec0.getTraceTag() == null) {
                    supportSQLiteStatement0.bindNull(23);
                }
                else {
                    supportSQLiteStatement0.bindString(23, workSpec0.getTraceTag());
                }
                Constraints constraints0 = workSpec0.constraints;
                supportSQLiteStatement0.bindLong(24, ((long)WorkTypeConverters.networkTypeToInt(constraints0.getRequiredNetworkType())));
                supportSQLiteStatement0.bindBlob(25, WorkTypeConverters.fromNetworkRequest$work_runtime_release(constraints0.getRequiredNetworkRequestCompat$work_runtime_release()));
                supportSQLiteStatement0.bindLong(26, ((long)constraints0.requiresCharging()));
                supportSQLiteStatement0.bindLong(27, ((long)constraints0.requiresDeviceIdle()));
                supportSQLiteStatement0.bindLong(28, ((long)constraints0.requiresBatteryNotLow()));
                supportSQLiteStatement0.bindLong(29, ((long)constraints0.requiresStorageNotLow()));
                supportSQLiteStatement0.bindLong(30, constraints0.getContentTriggerUpdateDelayMillis());
                supportSQLiteStatement0.bindLong(0x1F, constraints0.getContentTriggerMaxDelayMillis());
                supportSQLiteStatement0.bindBlob(0x20, WorkTypeConverters.setOfTriggersToByteArray(constraints0.getContentUriTriggers()));
                supportSQLiteStatement0.bindString(33, workSpec0.id);
            }

            @Override  // androidx.room.EntityDeletionOrUpdateAdapter
            protected void bind(SupportSQLiteStatement supportSQLiteStatement0, Object object0) {
                this.bind(supportSQLiteStatement0, ((WorkSpec)object0));
            }

            @Override  // androidx.room.EntityDeletionOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `WorkSpec` SET `id` = ?,`state` = ?,`worker_class_name` = ?,`input_merger_class_name` = ?,`input` = ?,`output` = ?,`initial_delay` = ?,`interval_duration` = ?,`flex_duration` = ?,`run_attempt_count` = ?,`backoff_policy` = ?,`backoff_delay_duration` = ?,`last_enqueue_time` = ?,`minimum_retention_duration` = ?,`schedule_requested_at` = ?,`run_in_foreground` = ?,`out_of_quota_policy` = ?,`period_count` = ?,`generation` = ?,`next_schedule_time_override` = ?,`next_schedule_time_override_generation` = ?,`stop_reason` = ?,`trace_tag` = ?,`required_network_type` = ?,`required_network_request` = ?,`requires_charging` = ?,`requires_device_idle` = ?,`requires_battery_not_low` = ?,`requires_storage_not_low` = ?,`trigger_content_update_delay` = ?,`trigger_max_content_delay` = ?,`content_uri_triggers` = ? WHERE `id` = ?";
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetState = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET state=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetCancelledState = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET stop_reason = CASE WHEN state=1 THEN 1 ELSE -256 END, state=5 WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementPeriodCount = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET period_count=period_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetLastEnqueueTime = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET last_enqueue_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetNextScheduleTimeOverride = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET next_schedule_time_override=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET next_schedule_time_override=9223372036854775807 WHERE (id=? AND next_schedule_time_override_generation=?)";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
        this.__preparedStmtOfIncrementGeneration = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET generation=generation+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetStopReason = new SharedSQLiteStatement(roomDatabase0) {
            @Override  // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE workspec SET stop_reason=? WHERE id=?";
            }
        };
    }

    private void __fetchRelationshipWorkProgressAsandroidxWorkData(HashMap hashMap0) {
        Set set0 = hashMap0.keySet();
        if(set0.isEmpty()) {
            return;
        }
        if(hashMap0.size() > 999) {
            RelationUtil.recursiveFetchHashMap(hashMap0, true, (HashMap hashMap0) -> {
                this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap0);
                return Unit.INSTANCE;
            });
            return;
        }
        StringBuilder stringBuilder0 = StringUtil.newStringBuilder();
        stringBuilder0.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int v = set0.size();
        StringUtil.appendPlaceholders(stringBuilder0, v);
        stringBuilder0.append(")");
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire(stringBuilder0.toString(), v);
        int v1 = 1;
        for(Object object0: set0) {
            roomSQLiteQuery0.bindString(v1, ((String)object0));
            ++v1;
        }
        try(Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null)) {
            int v2 = CursorUtil.getColumnIndex(cursor0, "work_spec_id");
            if(v2 != -1) {
                while(true) {
                    if(!cursor0.moveToNext()) {
                        break;
                    }
                    ArrayList arrayList0 = (ArrayList)hashMap0.get(cursor0.getString(v2));
                    if(arrayList0 != null) {
                        arrayList0.add(Data.fromByteArray(cursor0.getBlob(0)));
                    }
                }
            }
        }
    }

    private void __fetchRelationshipWorkTagAsjavaLangString(HashMap hashMap0) {
        Set set0 = hashMap0.keySet();
        if(set0.isEmpty()) {
            return;
        }
        if(hashMap0.size() > 999) {
            RelationUtil.recursiveFetchHashMap(hashMap0, true, (HashMap hashMap0) -> {
                this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                return Unit.INSTANCE;
            });
            return;
        }
        StringBuilder stringBuilder0 = StringUtil.newStringBuilder();
        stringBuilder0.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int v = set0.size();
        StringUtil.appendPlaceholders(stringBuilder0, v);
        stringBuilder0.append(")");
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire(stringBuilder0.toString(), v);
        int v1 = 1;
        for(Object object0: set0) {
            roomSQLiteQuery0.bindString(v1, ((String)object0));
            ++v1;
        }
        try(Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null)) {
            int v2 = CursorUtil.getColumnIndex(cursor0, "work_spec_id");
            if(v2 != -1) {
                while(true) {
                    if(!cursor0.moveToNext()) {
                        break;
                    }
                    ArrayList arrayList0 = (ArrayList)hashMap0.get(cursor0.getString(v2));
                    if(arrayList0 != null) {
                        arrayList0.add(cursor0.getString(0));
                    }
                }
            }
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public int countNonFinishedContentUriTriggerWorkers() {
        int v = 0;
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("Select COUNT(*) FROM workspec WHERE LENGTH(content_uri_triggers)<>0 AND state NOT IN (2, 3, 5)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            if(cursor0.moveToFirst()) {
                v = cursor0.getInt(0);
            }
            return v;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void delete(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfDelete.acquire();
        supportSQLiteStatement0.bindString(1, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfDelete.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getAllEligibleWorkSpecsForScheduling(int v) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?", 1);
        roomSQLiteQuery0.bindLong(1, ((long)v));
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "id");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "state");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "worker_class_name");
            int v5 = CursorUtil.getColumnIndexOrThrow(cursor0, "input_merger_class_name");
            int v6 = CursorUtil.getColumnIndexOrThrow(cursor0, "input");
            int v7 = CursorUtil.getColumnIndexOrThrow(cursor0, "output");
            int v8 = CursorUtil.getColumnIndexOrThrow(cursor0, "initial_delay");
            int v9 = CursorUtil.getColumnIndexOrThrow(cursor0, "interval_duration");
            int v10 = CursorUtil.getColumnIndexOrThrow(cursor0, "flex_duration");
            int v11 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_attempt_count");
            int v12 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_policy");
            int v13 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_delay_duration");
            int v14 = CursorUtil.getColumnIndexOrThrow(cursor0, "last_enqueue_time");
            int v15 = CursorUtil.getColumnIndexOrThrow(cursor0, "minimum_retention_duration");
            int v16 = CursorUtil.getColumnIndexOrThrow(cursor0, "schedule_requested_at");
            int v17 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_in_foreground");
            int v18 = CursorUtil.getColumnIndexOrThrow(cursor0, "out_of_quota_policy");
            int v19 = CursorUtil.getColumnIndexOrThrow(cursor0, "period_count");
            int v20 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v21 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override");
            int v22 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override_generation");
            int v23 = CursorUtil.getColumnIndexOrThrow(cursor0, "stop_reason");
            int v24 = CursorUtil.getColumnIndexOrThrow(cursor0, "trace_tag");
            int v25 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_type");
            int v26 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_request");
            int v27 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_charging");
            int v28 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_device_idle");
            int v29 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_battery_not_low");
            int v30 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_storage_not_low");
            int v31 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_content_update_delay");
            int v32 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_max_content_delay");
            int v33 = CursorUtil.getColumnIndexOrThrow(cursor0, "content_uri_triggers");
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(v2);
                State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v3));
                String s1 = cursor0.getString(v4);
                String s2 = cursor0.getString(v5);
                Data data0 = Data.fromByteArray(cursor0.getBlob(v6));
                Data data1 = Data.fromByteArray(cursor0.getBlob(v7));
                long v34 = cursor0.getLong(v8);
                long v35 = cursor0.getLong(v9);
                long v36 = cursor0.getLong(v10);
                int v37 = cursor0.getInt(v11);
                BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v12));
                long v38 = cursor0.getLong(v13);
                long v39 = cursor0.getLong(v14);
                long v40 = cursor0.getLong(v15);
                long v41 = cursor0.getLong(v16);
                boolean z = cursor0.getInt(v17) != 0;
                OutOfQuotaPolicy outOfQuotaPolicy0 = WorkTypeConverters.intToOutOfQuotaPolicy(cursor0.getInt(v18));
                int v42 = cursor0.getInt(v19);
                int v43 = cursor0.getInt(v20);
                long v44 = cursor0.getLong(v21);
                int v45 = cursor0.getInt(v22);
                int v46 = cursor0.getInt(v23);
                String s3 = cursor0.isNull(v24) ? null : cursor0.getString(v24);
                NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v25));
                list0.add(new WorkSpec(s, workInfo$State0, s1, s2, data0, data1, v34, v35, v36, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v26)), networkType0, cursor0.getInt(v27) != 0, cursor0.getInt(v28) != 0, cursor0.getInt(v29) != 0, cursor0.getInt(v30) != 0, cursor0.getLong(v31), cursor0.getLong(v32), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v33))), v37, backoffPolicy0, v38, v39, v40, v41, z, outOfQuotaPolicy0, v42, v43, v44, v45, v46, s3));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getAllUnfinishedWork() {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5)", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                list0.add(cursor0.getString(0));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getAllWorkSpecIds() {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                list0.add(cursor0.getString(0));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public LiveData getAllWorkSpecIdsLiveData() {
        androidx.work.impl.model.WorkSpecDao_Impl.18 workSpecDao_Impl$180 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0), false, null);
                    try {
                        List list0 = new ArrayList(cursor0.getCount());
                        while(cursor0.moveToNext()) {
                            list0.add(cursor0.getString(0));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        return list0;
                    }
                    finally {
                        cursor0.close();
                    }
                }
                finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                RoomSQLiteQuery.acquire("SELECT id FROM workspec", 0).release();
            }
        };
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, true, workSpecDao_Impl$180);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getEligibleWorkForScheduling(int v) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND LENGTH(content_uri_triggers)=0 AND state NOT IN (2, 3, 5))", 1);
        roomSQLiteQuery0.bindLong(1, ((long)v));
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "id");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "state");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "worker_class_name");
            int v5 = CursorUtil.getColumnIndexOrThrow(cursor0, "input_merger_class_name");
            int v6 = CursorUtil.getColumnIndexOrThrow(cursor0, "input");
            int v7 = CursorUtil.getColumnIndexOrThrow(cursor0, "output");
            int v8 = CursorUtil.getColumnIndexOrThrow(cursor0, "initial_delay");
            int v9 = CursorUtil.getColumnIndexOrThrow(cursor0, "interval_duration");
            int v10 = CursorUtil.getColumnIndexOrThrow(cursor0, "flex_duration");
            int v11 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_attempt_count");
            int v12 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_policy");
            int v13 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_delay_duration");
            int v14 = CursorUtil.getColumnIndexOrThrow(cursor0, "last_enqueue_time");
            int v15 = CursorUtil.getColumnIndexOrThrow(cursor0, "minimum_retention_duration");
            int v16 = CursorUtil.getColumnIndexOrThrow(cursor0, "schedule_requested_at");
            int v17 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_in_foreground");
            int v18 = CursorUtil.getColumnIndexOrThrow(cursor0, "out_of_quota_policy");
            int v19 = CursorUtil.getColumnIndexOrThrow(cursor0, "period_count");
            int v20 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v21 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override");
            int v22 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override_generation");
            int v23 = CursorUtil.getColumnIndexOrThrow(cursor0, "stop_reason");
            int v24 = CursorUtil.getColumnIndexOrThrow(cursor0, "trace_tag");
            int v25 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_type");
            int v26 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_request");
            int v27 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_charging");
            int v28 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_device_idle");
            int v29 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_battery_not_low");
            int v30 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_storage_not_low");
            int v31 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_content_update_delay");
            int v32 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_max_content_delay");
            int v33 = CursorUtil.getColumnIndexOrThrow(cursor0, "content_uri_triggers");
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(v2);
                State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v3));
                String s1 = cursor0.getString(v4);
                String s2 = cursor0.getString(v5);
                Data data0 = Data.fromByteArray(cursor0.getBlob(v6));
                Data data1 = Data.fromByteArray(cursor0.getBlob(v7));
                long v34 = cursor0.getLong(v8);
                long v35 = cursor0.getLong(v9);
                long v36 = cursor0.getLong(v10);
                int v37 = cursor0.getInt(v11);
                BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v12));
                long v38 = cursor0.getLong(v13);
                long v39 = cursor0.getLong(v14);
                long v40 = cursor0.getLong(v15);
                long v41 = cursor0.getLong(v16);
                boolean z = cursor0.getInt(v17) != 0;
                OutOfQuotaPolicy outOfQuotaPolicy0 = WorkTypeConverters.intToOutOfQuotaPolicy(cursor0.getInt(v18));
                int v42 = cursor0.getInt(v19);
                int v43 = cursor0.getInt(v20);
                long v44 = cursor0.getLong(v21);
                int v45 = cursor0.getInt(v22);
                int v46 = cursor0.getInt(v23);
                String s3 = cursor0.isNull(v24) ? null : cursor0.getString(v24);
                NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v25));
                list0.add(new WorkSpec(s, workInfo$State0, s1, s2, data0, data1, v34, v35, v36, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v26)), networkType0, cursor0.getInt(v27) != 0, cursor0.getInt(v28) != 0, cursor0.getInt(v29) != 0, cursor0.getInt(v30) != 0, cursor0.getLong(v31), cursor0.getLong(v32), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v33))), v37, backoffPolicy0, v38, v39, v40, v41, z, outOfQuotaPolicy0, v42, v43, v44, v45, v46, s3));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getEligibleWorkForSchedulingWithContentUris() {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 AND LENGTH(content_uri_triggers)<>0 ORDER BY last_enqueue_time", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v1 = CursorUtil.getColumnIndexOrThrow(cursor0, "id");
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "state");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "worker_class_name");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "input_merger_class_name");
            int v5 = CursorUtil.getColumnIndexOrThrow(cursor0, "input");
            int v6 = CursorUtil.getColumnIndexOrThrow(cursor0, "output");
            int v7 = CursorUtil.getColumnIndexOrThrow(cursor0, "initial_delay");
            int v8 = CursorUtil.getColumnIndexOrThrow(cursor0, "interval_duration");
            int v9 = CursorUtil.getColumnIndexOrThrow(cursor0, "flex_duration");
            int v10 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_attempt_count");
            int v11 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_policy");
            int v12 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_delay_duration");
            int v13 = CursorUtil.getColumnIndexOrThrow(cursor0, "last_enqueue_time");
            int v14 = CursorUtil.getColumnIndexOrThrow(cursor0, "minimum_retention_duration");
            int v15 = CursorUtil.getColumnIndexOrThrow(cursor0, "schedule_requested_at");
            int v16 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_in_foreground");
            int v17 = CursorUtil.getColumnIndexOrThrow(cursor0, "out_of_quota_policy");
            int v18 = CursorUtil.getColumnIndexOrThrow(cursor0, "period_count");
            int v19 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v20 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override");
            int v21 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override_generation");
            int v22 = CursorUtil.getColumnIndexOrThrow(cursor0, "stop_reason");
            int v23 = CursorUtil.getColumnIndexOrThrow(cursor0, "trace_tag");
            int v24 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_type");
            int v25 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_request");
            int v26 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_charging");
            int v27 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_device_idle");
            int v28 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_battery_not_low");
            int v29 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_storage_not_low");
            int v30 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_content_update_delay");
            int v31 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_max_content_delay");
            int v32 = CursorUtil.getColumnIndexOrThrow(cursor0, "content_uri_triggers");
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(v1);
                State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v2));
                String s1 = cursor0.getString(v3);
                String s2 = cursor0.getString(v4);
                Data data0 = Data.fromByteArray(cursor0.getBlob(v5));
                Data data1 = Data.fromByteArray(cursor0.getBlob(v6));
                long v33 = cursor0.getLong(v7);
                long v34 = cursor0.getLong(v8);
                long v35 = cursor0.getLong(v9);
                int v36 = cursor0.getInt(v10);
                BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v11));
                long v37 = cursor0.getLong(v12);
                long v38 = cursor0.getLong(v13);
                long v39 = cursor0.getLong(v14);
                long v40 = cursor0.getLong(v15);
                boolean z = cursor0.getInt(v16) != 0;
                OutOfQuotaPolicy outOfQuotaPolicy0 = WorkTypeConverters.intToOutOfQuotaPolicy(cursor0.getInt(v17));
                int v41 = cursor0.getInt(v18);
                int v42 = cursor0.getInt(v19);
                long v43 = cursor0.getLong(v20);
                int v44 = cursor0.getInt(v21);
                int v45 = cursor0.getInt(v22);
                String s3 = cursor0.isNull(v23) ? null : cursor0.getString(v23);
                NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v24));
                list0.add(new WorkSpec(s, workInfo$State0, s1, s2, data0, data1, v33, v34, v35, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v25)), networkType0, cursor0.getInt(v26) != 0, cursor0.getInt(v27) != 0, cursor0.getInt(v28) != 0, cursor0.getInt(v29) != 0, cursor0.getLong(v30), cursor0.getLong(v31), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v32))), v36, backoffPolicy0, v37, v38, v39, v40, z, outOfQuotaPolicy0, v41, v42, v43, v44, v45, s3));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getInputsFromPrerequisites(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                list0.add(Data.fromByteArray(cursor0.getBlob(0)));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getRecentlyCompletedWork(long v) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC", 1);
        roomSQLiteQuery0.bindLong(1, v);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "id");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "state");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "worker_class_name");
            int v5 = CursorUtil.getColumnIndexOrThrow(cursor0, "input_merger_class_name");
            int v6 = CursorUtil.getColumnIndexOrThrow(cursor0, "input");
            int v7 = CursorUtil.getColumnIndexOrThrow(cursor0, "output");
            int v8 = CursorUtil.getColumnIndexOrThrow(cursor0, "initial_delay");
            int v9 = CursorUtil.getColumnIndexOrThrow(cursor0, "interval_duration");
            int v10 = CursorUtil.getColumnIndexOrThrow(cursor0, "flex_duration");
            int v11 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_attempt_count");
            int v12 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_policy");
            int v13 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_delay_duration");
            int v14 = CursorUtil.getColumnIndexOrThrow(cursor0, "last_enqueue_time");
            int v15 = CursorUtil.getColumnIndexOrThrow(cursor0, "minimum_retention_duration");
            int v16 = CursorUtil.getColumnIndexOrThrow(cursor0, "schedule_requested_at");
            int v17 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_in_foreground");
            int v18 = CursorUtil.getColumnIndexOrThrow(cursor0, "out_of_quota_policy");
            int v19 = CursorUtil.getColumnIndexOrThrow(cursor0, "period_count");
            int v20 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v21 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override");
            int v22 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override_generation");
            int v23 = CursorUtil.getColumnIndexOrThrow(cursor0, "stop_reason");
            int v24 = CursorUtil.getColumnIndexOrThrow(cursor0, "trace_tag");
            int v25 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_type");
            int v26 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_request");
            int v27 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_charging");
            int v28 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_device_idle");
            int v29 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_battery_not_low");
            int v30 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_storage_not_low");
            int v31 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_content_update_delay");
            int v32 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_max_content_delay");
            int v33 = CursorUtil.getColumnIndexOrThrow(cursor0, "content_uri_triggers");
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(v2);
                State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v3));
                String s1 = cursor0.getString(v4);
                String s2 = cursor0.getString(v5);
                Data data0 = Data.fromByteArray(cursor0.getBlob(v6));
                Data data1 = Data.fromByteArray(cursor0.getBlob(v7));
                long v34 = cursor0.getLong(v8);
                long v35 = cursor0.getLong(v9);
                long v36 = cursor0.getLong(v10);
                int v37 = cursor0.getInt(v11);
                BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v12));
                long v38 = cursor0.getLong(v13);
                long v39 = cursor0.getLong(v14);
                long v40 = cursor0.getLong(v15);
                long v41 = cursor0.getLong(v16);
                boolean z = cursor0.getInt(v17) != 0;
                OutOfQuotaPolicy outOfQuotaPolicy0 = WorkTypeConverters.intToOutOfQuotaPolicy(cursor0.getInt(v18));
                int v42 = cursor0.getInt(v19);
                int v43 = cursor0.getInt(v20);
                long v44 = cursor0.getLong(v21);
                int v45 = cursor0.getInt(v22);
                int v46 = cursor0.getInt(v23);
                String s3 = cursor0.isNull(v24) ? null : cursor0.getString(v24);
                NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v25));
                list0.add(new WorkSpec(s, workInfo$State0, s1, s2, data0, data1, v34, v35, v36, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v26)), networkType0, cursor0.getInt(v27) != 0, cursor0.getInt(v28) != 0, cursor0.getInt(v29) != 0, cursor0.getInt(v30) != 0, cursor0.getLong(v31), cursor0.getLong(v32), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v33))), v37, backoffPolicy0, v38, v39, v40, v41, z, outOfQuotaPolicy0, v42, v43, v44, v45, v46, s3));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    public static List getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getRunningWork() {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v1 = CursorUtil.getColumnIndexOrThrow(cursor0, "id");
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "state");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "worker_class_name");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "input_merger_class_name");
            int v5 = CursorUtil.getColumnIndexOrThrow(cursor0, "input");
            int v6 = CursorUtil.getColumnIndexOrThrow(cursor0, "output");
            int v7 = CursorUtil.getColumnIndexOrThrow(cursor0, "initial_delay");
            int v8 = CursorUtil.getColumnIndexOrThrow(cursor0, "interval_duration");
            int v9 = CursorUtil.getColumnIndexOrThrow(cursor0, "flex_duration");
            int v10 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_attempt_count");
            int v11 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_policy");
            int v12 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_delay_duration");
            int v13 = CursorUtil.getColumnIndexOrThrow(cursor0, "last_enqueue_time");
            int v14 = CursorUtil.getColumnIndexOrThrow(cursor0, "minimum_retention_duration");
            int v15 = CursorUtil.getColumnIndexOrThrow(cursor0, "schedule_requested_at");
            int v16 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_in_foreground");
            int v17 = CursorUtil.getColumnIndexOrThrow(cursor0, "out_of_quota_policy");
            int v18 = CursorUtil.getColumnIndexOrThrow(cursor0, "period_count");
            int v19 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v20 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override");
            int v21 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override_generation");
            int v22 = CursorUtil.getColumnIndexOrThrow(cursor0, "stop_reason");
            int v23 = CursorUtil.getColumnIndexOrThrow(cursor0, "trace_tag");
            int v24 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_type");
            int v25 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_request");
            int v26 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_charging");
            int v27 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_device_idle");
            int v28 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_battery_not_low");
            int v29 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_storage_not_low");
            int v30 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_content_update_delay");
            int v31 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_max_content_delay");
            int v32 = CursorUtil.getColumnIndexOrThrow(cursor0, "content_uri_triggers");
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(v1);
                State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v2));
                String s1 = cursor0.getString(v3);
                String s2 = cursor0.getString(v4);
                Data data0 = Data.fromByteArray(cursor0.getBlob(v5));
                Data data1 = Data.fromByteArray(cursor0.getBlob(v6));
                long v33 = cursor0.getLong(v7);
                long v34 = cursor0.getLong(v8);
                long v35 = cursor0.getLong(v9);
                int v36 = cursor0.getInt(v10);
                BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v11));
                long v37 = cursor0.getLong(v12);
                long v38 = cursor0.getLong(v13);
                long v39 = cursor0.getLong(v14);
                long v40 = cursor0.getLong(v15);
                boolean z = cursor0.getInt(v16) != 0;
                OutOfQuotaPolicy outOfQuotaPolicy0 = WorkTypeConverters.intToOutOfQuotaPolicy(cursor0.getInt(v17));
                int v41 = cursor0.getInt(v18);
                int v42 = cursor0.getInt(v19);
                long v43 = cursor0.getLong(v20);
                int v44 = cursor0.getInt(v21);
                int v45 = cursor0.getInt(v22);
                String s3 = cursor0.isNull(v23) ? null : cursor0.getString(v23);
                NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v24));
                list0.add(new WorkSpec(s, workInfo$State0, s1, s2, data0, data1, v33, v34, v35, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v25)), networkType0, cursor0.getInt(v26) != 0, cursor0.getInt(v27) != 0, cursor0.getInt(v28) != 0, cursor0.getInt(v29) != 0, cursor0.getLong(v30), cursor0.getLong(v31), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v32))), v36, backoffPolicy0, v37, v38, v39, v40, z, outOfQuotaPolicy0, v41, v42, v43, v44, v45, s3));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public LiveData getScheduleRequestedAtLiveData(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT schedule_requested_at FROM workspec WHERE id=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        androidx.work.impl.model.WorkSpecDao_Impl.26 workSpecDao_Impl$260 = new Callable() {
            public Long call() throws Exception {
                try(Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, roomSQLiteQuery0, false, null)) {
                    return cursor0.moveToFirst() && !cursor0.isNull(0) ? cursor0.getLong(0) : null;
                }
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"workspec"}, false, workSpecDao_Impl$260);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getScheduledWork() {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v1 = CursorUtil.getColumnIndexOrThrow(cursor0, "id");
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "state");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "worker_class_name");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "input_merger_class_name");
            int v5 = CursorUtil.getColumnIndexOrThrow(cursor0, "input");
            int v6 = CursorUtil.getColumnIndexOrThrow(cursor0, "output");
            int v7 = CursorUtil.getColumnIndexOrThrow(cursor0, "initial_delay");
            int v8 = CursorUtil.getColumnIndexOrThrow(cursor0, "interval_duration");
            int v9 = CursorUtil.getColumnIndexOrThrow(cursor0, "flex_duration");
            int v10 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_attempt_count");
            int v11 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_policy");
            int v12 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_delay_duration");
            int v13 = CursorUtil.getColumnIndexOrThrow(cursor0, "last_enqueue_time");
            int v14 = CursorUtil.getColumnIndexOrThrow(cursor0, "minimum_retention_duration");
            int v15 = CursorUtil.getColumnIndexOrThrow(cursor0, "schedule_requested_at");
            int v16 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_in_foreground");
            int v17 = CursorUtil.getColumnIndexOrThrow(cursor0, "out_of_quota_policy");
            int v18 = CursorUtil.getColumnIndexOrThrow(cursor0, "period_count");
            int v19 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v20 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override");
            int v21 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override_generation");
            int v22 = CursorUtil.getColumnIndexOrThrow(cursor0, "stop_reason");
            int v23 = CursorUtil.getColumnIndexOrThrow(cursor0, "trace_tag");
            int v24 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_type");
            int v25 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_request");
            int v26 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_charging");
            int v27 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_device_idle");
            int v28 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_battery_not_low");
            int v29 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_storage_not_low");
            int v30 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_content_update_delay");
            int v31 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_max_content_delay");
            int v32 = CursorUtil.getColumnIndexOrThrow(cursor0, "content_uri_triggers");
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                String s = cursor0.getString(v1);
                State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v2));
                String s1 = cursor0.getString(v3);
                String s2 = cursor0.getString(v4);
                Data data0 = Data.fromByteArray(cursor0.getBlob(v5));
                Data data1 = Data.fromByteArray(cursor0.getBlob(v6));
                long v33 = cursor0.getLong(v7);
                long v34 = cursor0.getLong(v8);
                long v35 = cursor0.getLong(v9);
                int v36 = cursor0.getInt(v10);
                BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v11));
                long v37 = cursor0.getLong(v12);
                long v38 = cursor0.getLong(v13);
                long v39 = cursor0.getLong(v14);
                long v40 = cursor0.getLong(v15);
                boolean z = cursor0.getInt(v16) != 0;
                OutOfQuotaPolicy outOfQuotaPolicy0 = WorkTypeConverters.intToOutOfQuotaPolicy(cursor0.getInt(v17));
                int v41 = cursor0.getInt(v18);
                int v42 = cursor0.getInt(v19);
                long v43 = cursor0.getLong(v20);
                int v44 = cursor0.getInt(v21);
                int v45 = cursor0.getInt(v22);
                String s3 = cursor0.isNull(v23) ? null : cursor0.getString(v23);
                NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v24));
                list0.add(new WorkSpec(s, workInfo$State0, s1, s2, data0, data1, v33, v34, v35, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v25)), networkType0, cursor0.getInt(v26) != 0, cursor0.getInt(v27) != 0, cursor0.getInt(v28) != 0, cursor0.getInt(v29) != 0, cursor0.getLong(v30), cursor0.getLong(v31), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v32))), v36, backoffPolicy0, v37, v38, v39, v40, z, outOfQuotaPolicy0, v41, v42, v43, v44, v45, s3));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public State getState(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        State workInfo$State0 = null;
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            if(cursor0.moveToFirst()) {
                Integer integer0 = cursor0.isNull(0) ? null : cursor0.getInt(0);
                if(integer0 != null) {
                    workInfo$State0 = WorkTypeConverters.intToState(((int)integer0));
                }
            }
            return workInfo$State0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getUnfinishedWorkWithName(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                list0.add(cursor0.getString(0));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getUnfinishedWorkWithTag(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                list0.add(cursor0.getString(0));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public WorkSpec getWorkSpec(String s) {
        WorkSpec workSpec0;
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE id=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            int v1 = CursorUtil.getColumnIndexOrThrow(cursor0, "id");
            int v2 = CursorUtil.getColumnIndexOrThrow(cursor0, "state");
            int v3 = CursorUtil.getColumnIndexOrThrow(cursor0, "worker_class_name");
            int v4 = CursorUtil.getColumnIndexOrThrow(cursor0, "input_merger_class_name");
            int v5 = CursorUtil.getColumnIndexOrThrow(cursor0, "input");
            int v6 = CursorUtil.getColumnIndexOrThrow(cursor0, "output");
            int v7 = CursorUtil.getColumnIndexOrThrow(cursor0, "initial_delay");
            int v8 = CursorUtil.getColumnIndexOrThrow(cursor0, "interval_duration");
            int v9 = CursorUtil.getColumnIndexOrThrow(cursor0, "flex_duration");
            int v10 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_attempt_count");
            int v11 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_policy");
            int v12 = CursorUtil.getColumnIndexOrThrow(cursor0, "backoff_delay_duration");
            int v13 = CursorUtil.getColumnIndexOrThrow(cursor0, "last_enqueue_time");
            int v14 = CursorUtil.getColumnIndexOrThrow(cursor0, "minimum_retention_duration");
            int v15 = CursorUtil.getColumnIndexOrThrow(cursor0, "schedule_requested_at");
            int v16 = CursorUtil.getColumnIndexOrThrow(cursor0, "run_in_foreground");
            int v17 = CursorUtil.getColumnIndexOrThrow(cursor0, "out_of_quota_policy");
            int v18 = CursorUtil.getColumnIndexOrThrow(cursor0, "period_count");
            int v19 = CursorUtil.getColumnIndexOrThrow(cursor0, "generation");
            int v20 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override");
            int v21 = CursorUtil.getColumnIndexOrThrow(cursor0, "next_schedule_time_override_generation");
            int v22 = CursorUtil.getColumnIndexOrThrow(cursor0, "stop_reason");
            int v23 = CursorUtil.getColumnIndexOrThrow(cursor0, "trace_tag");
            int v24 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_type");
            int v25 = CursorUtil.getColumnIndexOrThrow(cursor0, "required_network_request");
            int v26 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_charging");
            int v27 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_device_idle");
            int v28 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_battery_not_low");
            int v29 = CursorUtil.getColumnIndexOrThrow(cursor0, "requires_storage_not_low");
            int v30 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_content_update_delay");
            int v31 = CursorUtil.getColumnIndexOrThrow(cursor0, "trigger_max_content_delay");
            int v32 = CursorUtil.getColumnIndexOrThrow(cursor0, "content_uri_triggers");
            if(cursor0.moveToFirst()) {
                String s1 = cursor0.getString(v1);
                State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v2));
                String s2 = cursor0.getString(v3);
                String s3 = cursor0.getString(v4);
                Data data0 = Data.fromByteArray(cursor0.getBlob(v5));
                Data data1 = Data.fromByteArray(cursor0.getBlob(v6));
                long v33 = cursor0.getLong(v7);
                long v34 = cursor0.getLong(v8);
                long v35 = cursor0.getLong(v9);
                int v36 = cursor0.getInt(v10);
                BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v11));
                long v37 = cursor0.getLong(v12);
                long v38 = cursor0.getLong(v13);
                long v39 = cursor0.getLong(v14);
                long v40 = cursor0.getLong(v15);
                boolean z = cursor0.getInt(v16) != 0;
                OutOfQuotaPolicy outOfQuotaPolicy0 = WorkTypeConverters.intToOutOfQuotaPolicy(cursor0.getInt(v17));
                int v41 = cursor0.getInt(v18);
                int v42 = cursor0.getInt(v19);
                long v43 = cursor0.getLong(v20);
                int v44 = cursor0.getInt(v21);
                int v45 = cursor0.getInt(v22);
                String s4 = cursor0.isNull(v23) ? null : cursor0.getString(v23);
                NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v24));
                workSpec0 = new WorkSpec(s1, workInfo$State0, s2, s3, data0, data1, v33, v34, v35, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v25)), networkType0, cursor0.getInt(v26) != 0, cursor0.getInt(v27) != 0, cursor0.getInt(v28) != 0, cursor0.getInt(v29) != 0, cursor0.getLong(v30), cursor0.getLong(v31), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v32))), v36, backoffPolicy0, v37, v38, v39, v40, z, outOfQuotaPolicy0, v41, v42, v43, v44, v45, s4);
            }
            else {
                workSpec0 = null;
            }
            return workSpec0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getWorkSpecIdAndStatesForName(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, false, null);
        try {
            List list0 = new ArrayList(cursor0.getCount());
            while(cursor0.moveToNext()) {
                list0.add(new IdAndState(cursor0.getString(0), WorkTypeConverters.intToState(cursor0.getInt(1))));
            }
            return list0;
        }
        finally {
            cursor0.close();
            roomSQLiteQuery0.release();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public Flow getWorkStatusPojoFlowDataForIds(List list0) {
        StringBuilder stringBuilder0 = StringUtil.newStringBuilder();
        stringBuilder0.append("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (");
        int v = list0.size();
        StringUtil.appendPlaceholders(stringBuilder0, v);
        stringBuilder0.append(")");
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire(stringBuilder0.toString(), v);
        int v1 = 1;
        for(Object object0: list0) {
            roomSQLiteQuery0.bindString(v1, ((String)object0));
            ++v1;
        }
        androidx.work.impl.model.WorkSpecDao_Impl.20 workSpecDao_Impl$200 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, roomSQLiteQuery0, true, null);
                    try {
                        HashMap hashMap0 = new HashMap();
                        HashMap hashMap1 = new HashMap();
                        while(cursor0.moveToNext()) {
                            String s = cursor0.getString(0);
                            if(!hashMap0.containsKey(s)) {
                                hashMap0.put(s, new ArrayList());
                            }
                            String s1 = cursor0.getString(0);
                            if(!hashMap1.containsKey(s1)) {
                                hashMap1.put(s1, new ArrayList());
                            }
                        }
                        cursor0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                        List list0 = new ArrayList(cursor0.getCount());
                        while(cursor0.moveToNext()) {
                            String s2 = cursor0.getString(0);
                            State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                            Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                            int v2 = cursor0.getInt(3);
                            int v3 = cursor0.getInt(4);
                            long v4 = cursor0.getLong(14);
                            long v5 = cursor0.getLong(15);
                            long v6 = cursor0.getLong(16);
                            BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                            long v7 = cursor0.getLong(18);
                            long v8 = cursor0.getLong(19);
                            int v9 = cursor0.getInt(20);
                            long v10 = cursor0.getLong(21);
                            int v11 = cursor0.getInt(22);
                            NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                            list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        return list0;
                    }
                    finally {
                        cursor0.close();
                    }
                }
                finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return CoroutinesRoom.createFlow(this.__db, true, new String[]{"WorkTag", "WorkProgress", "workspec"}, workSpecDao_Impl$200);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public Flow getWorkStatusPojoFlowForName(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        androidx.work.impl.model.WorkSpecDao_Impl.24 workSpecDao_Impl$240 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, roomSQLiteQuery0, true, null);
                    try {
                        HashMap hashMap0 = new HashMap();
                        HashMap hashMap1 = new HashMap();
                        while(cursor0.moveToNext()) {
                            String s = cursor0.getString(0);
                            if(!hashMap0.containsKey(s)) {
                                hashMap0.put(s, new ArrayList());
                            }
                            String s1 = cursor0.getString(0);
                            if(!hashMap1.containsKey(s1)) {
                                hashMap1.put(s1, new ArrayList());
                            }
                        }
                        cursor0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                        List list0 = new ArrayList(cursor0.getCount());
                        while(cursor0.moveToNext()) {
                            String s2 = cursor0.getString(0);
                            State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                            Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                            int v2 = cursor0.getInt(3);
                            int v3 = cursor0.getInt(4);
                            long v4 = cursor0.getLong(14);
                            long v5 = cursor0.getLong(15);
                            long v6 = cursor0.getLong(16);
                            BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                            long v7 = cursor0.getLong(18);
                            long v8 = cursor0.getLong(19);
                            int v9 = cursor0.getInt(20);
                            long v10 = cursor0.getLong(21);
                            int v11 = cursor0.getInt(22);
                            NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                            list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        return list0;
                    }
                    finally {
                        cursor0.close();
                    }
                }
                finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return CoroutinesRoom.createFlow(this.__db, true, new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, workSpecDao_Impl$240);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public Flow getWorkStatusPojoFlowForTag(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        androidx.work.impl.model.WorkSpecDao_Impl.21 workSpecDao_Impl$210 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, roomSQLiteQuery0, true, null);
                    try {
                        HashMap hashMap0 = new HashMap();
                        HashMap hashMap1 = new HashMap();
                        while(cursor0.moveToNext()) {
                            String s = cursor0.getString(0);
                            if(!hashMap0.containsKey(s)) {
                                hashMap0.put(s, new ArrayList());
                            }
                            String s1 = cursor0.getString(0);
                            if(!hashMap1.containsKey(s1)) {
                                hashMap1.put(s1, new ArrayList());
                            }
                        }
                        cursor0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                        List list0 = new ArrayList(cursor0.getCount());
                        while(cursor0.moveToNext()) {
                            String s2 = cursor0.getString(0);
                            State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                            Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                            int v2 = cursor0.getInt(3);
                            int v3 = cursor0.getInt(4);
                            long v4 = cursor0.getLong(14);
                            long v5 = cursor0.getLong(15);
                            long v6 = cursor0.getLong(16);
                            BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                            long v7 = cursor0.getLong(18);
                            long v8 = cursor0.getLong(19);
                            int v9 = cursor0.getInt(20);
                            long v10 = cursor0.getLong(21);
                            int v11 = cursor0.getInt(22);
                            NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                            list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        return list0;
                    }
                    finally {
                        cursor0.close();
                    }
                }
                finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return CoroutinesRoom.createFlow(this.__db, true, new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, workSpecDao_Impl$210);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public WorkInfoPojo getWorkStatusPojoForId(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id=?", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            WorkInfoPojo workSpec$WorkInfoPojo0 = null;
            Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, true, null);
            try {
                HashMap hashMap0 = new HashMap();
                HashMap hashMap1 = new HashMap();
                while(cursor0.moveToNext()) {
                    String s1 = cursor0.getString(0);
                    if(!hashMap0.containsKey(s1)) {
                        hashMap0.put(s1, new ArrayList());
                    }
                    String s2 = cursor0.getString(0);
                    if(!hashMap1.containsKey(s2)) {
                        hashMap1.put(s2, new ArrayList());
                    }
                }
                cursor0.moveToPosition(-1);
                this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                if(cursor0.moveToFirst()) {
                    String s3 = cursor0.getString(0);
                    State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                    Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                    int v2 = cursor0.getInt(3);
                    int v3 = cursor0.getInt(4);
                    long v4 = cursor0.getLong(14);
                    long v5 = cursor0.getLong(15);
                    long v6 = cursor0.getLong(16);
                    BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                    long v7 = cursor0.getLong(18);
                    long v8 = cursor0.getLong(19);
                    int v9 = cursor0.getInt(20);
                    long v10 = cursor0.getLong(21);
                    int v11 = cursor0.getInt(22);
                    NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                    workSpec$WorkInfoPojo0 = new WorkInfoPojo(s3, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0))));
                }
                this.__db.setTransactionSuccessful();
                return workSpec$WorkInfoPojo0;
            }
            finally {
                cursor0.close();
                roomSQLiteQuery0.release();
            }
        }
        finally {
            this.__db.endTransaction();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getWorkStatusPojoForIds(List list0) {
        StringBuilder stringBuilder0 = StringUtil.newStringBuilder();
        stringBuilder0.append("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (");
        int v = list0.size();
        StringUtil.appendPlaceholders(stringBuilder0, v);
        stringBuilder0.append(")");
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire(stringBuilder0.toString(), v);
        int v1 = 1;
        for(Object object0: list0) {
            roomSQLiteQuery0.bindString(v1, ((String)object0));
            ++v1;
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, true, null);
            try {
                HashMap hashMap0 = new HashMap();
                HashMap hashMap1 = new HashMap();
                while(cursor0.moveToNext()) {
                    String s = cursor0.getString(0);
                    if(!hashMap0.containsKey(s)) {
                        hashMap0.put(s, new ArrayList());
                    }
                    String s1 = cursor0.getString(0);
                    if(!hashMap1.containsKey(s1)) {
                        hashMap1.put(s1, new ArrayList());
                    }
                }
                cursor0.moveToPosition(-1);
                this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                List list1 = new ArrayList(cursor0.getCount());
                while(cursor0.moveToNext()) {
                    String s2 = cursor0.getString(0);
                    State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                    Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                    int v4 = cursor0.getInt(3);
                    int v5 = cursor0.getInt(4);
                    long v6 = cursor0.getLong(14);
                    long v7 = cursor0.getLong(15);
                    long v8 = cursor0.getLong(16);
                    BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                    long v9 = cursor0.getLong(18);
                    long v10 = cursor0.getLong(19);
                    int v11 = cursor0.getInt(20);
                    long v12 = cursor0.getLong(21);
                    int v13 = cursor0.getInt(22);
                    NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                    list1.add(new WorkInfoPojo(s2, workInfo$State0, data0, v6, v7, v8, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v4, backoffPolicy0, v9, v10, v11, v5, v12, v13, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                }
                this.__db.setTransactionSuccessful();
                return list1;
            }
            finally {
                cursor0.close();
                roomSQLiteQuery0.release();
            }
        }
        finally {
            this.__db.endTransaction();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getWorkStatusPojoForName(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, true, null);
            try {
                HashMap hashMap0 = new HashMap();
                HashMap hashMap1 = new HashMap();
                while(cursor0.moveToNext()) {
                    String s1 = cursor0.getString(0);
                    if(!hashMap0.containsKey(s1)) {
                        hashMap0.put(s1, new ArrayList());
                    }
                    String s2 = cursor0.getString(0);
                    if(!hashMap1.containsKey(s2)) {
                        hashMap1.put(s2, new ArrayList());
                    }
                }
                cursor0.moveToPosition(-1);
                this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                List list0 = new ArrayList(cursor0.getCount());
                while(cursor0.moveToNext()) {
                    String s3 = cursor0.getString(0);
                    State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                    Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                    int v2 = cursor0.getInt(3);
                    int v3 = cursor0.getInt(4);
                    long v4 = cursor0.getLong(14);
                    long v5 = cursor0.getLong(15);
                    long v6 = cursor0.getLong(16);
                    BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                    long v7 = cursor0.getLong(18);
                    long v8 = cursor0.getLong(19);
                    int v9 = cursor0.getInt(20);
                    long v10 = cursor0.getLong(21);
                    int v11 = cursor0.getInt(22);
                    NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                    list0.add(new WorkInfoPojo(s3, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                }
                this.__db.setTransactionSuccessful();
                return list0;
            }
            finally {
                cursor0.close();
                roomSQLiteQuery0.release();
            }
        }
        finally {
            this.__db.endTransaction();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public List getWorkStatusPojoForTag(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursor0 = DBUtil.query(this.__db, roomSQLiteQuery0, true, null);
            try {
                HashMap hashMap0 = new HashMap();
                HashMap hashMap1 = new HashMap();
                while(cursor0.moveToNext()) {
                    String s1 = cursor0.getString(0);
                    if(!hashMap0.containsKey(s1)) {
                        hashMap0.put(s1, new ArrayList());
                    }
                    String s2 = cursor0.getString(0);
                    if(!hashMap1.containsKey(s2)) {
                        hashMap1.put(s2, new ArrayList());
                    }
                }
                cursor0.moveToPosition(-1);
                this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                List list0 = new ArrayList(cursor0.getCount());
                while(cursor0.moveToNext()) {
                    String s3 = cursor0.getString(0);
                    State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                    Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                    int v2 = cursor0.getInt(3);
                    int v3 = cursor0.getInt(4);
                    long v4 = cursor0.getLong(14);
                    long v5 = cursor0.getLong(15);
                    long v6 = cursor0.getLong(16);
                    BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                    long v7 = cursor0.getLong(18);
                    long v8 = cursor0.getLong(19);
                    int v9 = cursor0.getInt(20);
                    long v10 = cursor0.getLong(21);
                    int v11 = cursor0.getInt(22);
                    NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                    list0.add(new WorkInfoPojo(s3, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                }
                this.__db.setTransactionSuccessful();
                return list0;
            }
            finally {
                cursor0.close();
                roomSQLiteQuery0.release();
            }
        }
        finally {
            this.__db.endTransaction();
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public LiveData getWorkStatusPojoLiveDataForIds(List list0) {
        StringBuilder stringBuilder0 = StringUtil.newStringBuilder();
        stringBuilder0.append("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (");
        int v = list0.size();
        StringUtil.appendPlaceholders(stringBuilder0, v);
        stringBuilder0.append(")");
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire(stringBuilder0.toString(), v);
        int v1 = 1;
        for(Object object0: list0) {
            roomSQLiteQuery0.bindString(v1, ((String)object0));
            ++v1;
        }
        androidx.work.impl.model.WorkSpecDao_Impl.19 workSpecDao_Impl$190 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, roomSQLiteQuery0, true, null);
                    try {
                        HashMap hashMap0 = new HashMap();
                        HashMap hashMap1 = new HashMap();
                        while(cursor0.moveToNext()) {
                            String s = cursor0.getString(0);
                            if(!hashMap0.containsKey(s)) {
                                hashMap0.put(s, new ArrayList());
                            }
                            String s1 = cursor0.getString(0);
                            if(!hashMap1.containsKey(s1)) {
                                hashMap1.put(s1, new ArrayList());
                            }
                        }
                        cursor0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                        List list0 = new ArrayList(cursor0.getCount());
                        while(cursor0.moveToNext()) {
                            String s2 = cursor0.getString(0);
                            State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                            Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                            int v2 = cursor0.getInt(3);
                            int v3 = cursor0.getInt(4);
                            long v4 = cursor0.getLong(14);
                            long v5 = cursor0.getLong(15);
                            long v6 = cursor0.getLong(16);
                            BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                            long v7 = cursor0.getLong(18);
                            long v8 = cursor0.getLong(19);
                            int v9 = cursor0.getInt(20);
                            long v10 = cursor0.getLong(21);
                            int v11 = cursor0.getInt(22);
                            NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                            list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        return list0;
                    }
                    finally {
                        cursor0.close();
                    }
                }
                finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec"}, true, workSpecDao_Impl$190);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public LiveData getWorkStatusPojoLiveDataForName(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        androidx.work.impl.model.WorkSpecDao_Impl.23 workSpecDao_Impl$230 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, roomSQLiteQuery0, true, null);
                    try {
                        HashMap hashMap0 = new HashMap();
                        HashMap hashMap1 = new HashMap();
                        while(cursor0.moveToNext()) {
                            String s = cursor0.getString(0);
                            if(!hashMap0.containsKey(s)) {
                                hashMap0.put(s, new ArrayList());
                            }
                            String s1 = cursor0.getString(0);
                            if(!hashMap1.containsKey(s1)) {
                                hashMap1.put(s1, new ArrayList());
                            }
                        }
                        cursor0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                        List list0 = new ArrayList(cursor0.getCount());
                        while(cursor0.moveToNext()) {
                            String s2 = cursor0.getString(0);
                            State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                            Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                            int v2 = cursor0.getInt(3);
                            int v3 = cursor0.getInt(4);
                            long v4 = cursor0.getLong(14);
                            long v5 = cursor0.getLong(15);
                            long v6 = cursor0.getLong(16);
                            BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                            long v7 = cursor0.getLong(18);
                            long v8 = cursor0.getLong(19);
                            int v9 = cursor0.getInt(20);
                            long v10 = cursor0.getLong(21);
                            int v11 = cursor0.getInt(22);
                            NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                            list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        return list0;
                    }
                    finally {
                        cursor0.close();
                    }
                }
                finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "workname"}, true, workSpecDao_Impl$230);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public LiveData getWorkStatusPojoLiveDataForTag(String s) {
        RoomSQLiteQuery roomSQLiteQuery0 = RoomSQLiteQuery.acquire("SELECT id, state, output, run_attempt_count, generation, required_network_type, required_network_request, requires_charging, requires_device_idle, requires_battery_not_low, requires_storage_not_low, trigger_content_update_delay, trigger_max_content_delay, content_uri_triggers, initial_delay, interval_duration, flex_duration, backoff_policy, backoff_delay_duration, last_enqueue_time, period_count, next_schedule_time_override, stop_reason FROM workspec WHERE id IN\n            (SELECT work_spec_id FROM worktag WHERE tag=?)", 1);
        roomSQLiteQuery0.bindString(1, s);
        androidx.work.impl.model.WorkSpecDao_Impl.22 workSpecDao_Impl$220 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                WorkSpecDao_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, roomSQLiteQuery0, true, null);
                    try {
                        HashMap hashMap0 = new HashMap();
                        HashMap hashMap1 = new HashMap();
                        while(cursor0.moveToNext()) {
                            String s = cursor0.getString(0);
                            if(!hashMap0.containsKey(s)) {
                                hashMap0.put(s, new ArrayList());
                            }
                            String s1 = cursor0.getString(0);
                            if(!hashMap1.containsKey(s1)) {
                                hashMap1.put(s1, new ArrayList());
                            }
                        }
                        cursor0.moveToPosition(-1);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                        WorkSpecDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                        List list0 = new ArrayList(cursor0.getCount());
                        while(cursor0.moveToNext()) {
                            String s2 = cursor0.getString(0);
                            State workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(1));
                            Data data0 = Data.fromByteArray(cursor0.getBlob(2));
                            int v2 = cursor0.getInt(3);
                            int v3 = cursor0.getInt(4);
                            long v4 = cursor0.getLong(14);
                            long v5 = cursor0.getLong(15);
                            long v6 = cursor0.getLong(16);
                            BackoffPolicy backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(17));
                            long v7 = cursor0.getLong(18);
                            long v8 = cursor0.getLong(19);
                            int v9 = cursor0.getInt(20);
                            long v10 = cursor0.getLong(21);
                            int v11 = cursor0.getInt(22);
                            NetworkType networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(5));
                            list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v4, v5, v6, new Constraints(WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(6)), networkType0, cursor0.getInt(7) != 0, cursor0.getInt(8) != 0, cursor0.getInt(9) != 0, cursor0.getInt(10) != 0, cursor0.getLong(11), cursor0.getLong(12), WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(13))), v2, backoffPolicy0, v7, v8, v9, v3, v10, v11, ((ArrayList)hashMap0.get(cursor0.getString(0))), ((ArrayList)hashMap1.get(cursor0.getString(0)))));
                        }
                        WorkSpecDao_Impl.this.__db.setTransactionSuccessful();
                        return list0;
                    }
                    finally {
                        cursor0.close();
                    }
                }
                finally {
                    WorkSpecDao_Impl.this.__db.endTransaction();
                }
            }

            @Override
            protected void finalize() {
                roomSQLiteQuery0.release();
            }
        };
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "workspec", "worktag"}, true, workSpecDao_Impl$220);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public Flow hasUnfinishedWorkFlow() {
        androidx.work.impl.model.WorkSpecDao_Impl.25 workSpecDao_Impl$250 = new Callable() {
            public Boolean call() throws Exception {
                try {
                    boolean z = false;
                    try(Cursor cursor0 = DBUtil.query(WorkSpecDao_Impl.this.__db, RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0), false, null)) {
                        if(cursor0.moveToFirst()) {
                            if(cursor0.getInt(0) != 0) {
                                z = true;
                            }
                            return Boolean.valueOf(z);
                        }
                    }
                    return false;
                }
                catch(Throwable throwable0) {
                    throw throwable0;
                }
            }

            @Override
            public Object call() throws Exception {
                return this.call();
            }

            @Override
            protected void finalize() {
                RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0).release();
            }
        };
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"workspec"}, workSpecDao_Impl$250);
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void incrementGeneration(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfIncrementGeneration.acquire();
        supportSQLiteStatement0.bindString(1, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfIncrementGeneration.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void incrementPeriodCount(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfIncrementPeriodCount.acquire();
        supportSQLiteStatement0.bindString(1, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfIncrementPeriodCount.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public int incrementWorkSpecRunAttemptCount(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.acquire();
        supportSQLiteStatement0.bindString(1, s);
        try {
            this.__db.beginTransaction();
            try {
                int v2 = supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
                return v2;
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfIncrementWorkSpecRunAttemptCount.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void insertWorkSpec(WorkSpec workSpec0) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWorkSpec.insert(workSpec0);
            this.__db.setTransactionSuccessful();
        }
        finally {
            this.__db.endTransaction();
        }
    }

    // 检测为 Lambda 实现
    Unit lambda$__fetchRelationshipWorkProgressAsandroidxWorkData$1$androidx-work-impl-model-WorkSpecDao_Impl(HashMap hashMap0) [...]

    // 检测为 Lambda 实现
    Unit lambda$__fetchRelationshipWorkTagAsjavaLangString$0$androidx-work-impl-model-WorkSpecDao_Impl(HashMap hashMap0) [...]

    @Override  // androidx.work.impl.model.WorkSpecDao
    public int markWorkSpecScheduled(String s, long v) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfMarkWorkSpecScheduled.acquire();
        supportSQLiteStatement0.bindLong(1, v);
        supportSQLiteStatement0.bindString(2, s);
        try {
            this.__db.beginTransaction();
            try {
                int v3 = supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
                return v3;
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfMarkWorkSpecScheduled.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.acquire();
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfPruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public int resetScheduledState() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfResetScheduledState.acquire();
        try {
            this.__db.beginTransaction();
            try {
                int v2 = supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
                return v2;
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfResetScheduledState.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void resetWorkSpecNextScheduleTimeOverride(String s, int v) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride.acquire();
        supportSQLiteStatement0.bindString(1, s);
        supportSQLiteStatement0.bindLong(2, ((long)v));
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public int resetWorkSpecRunAttemptCount(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire();
        supportSQLiteStatement0.bindString(1, s);
        try {
            this.__db.beginTransaction();
            try {
                int v2 = supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
                return v2;
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfResetWorkSpecRunAttemptCount.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public int setCancelledState(String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfSetCancelledState.acquire();
        supportSQLiteStatement0.bindString(1, s);
        try {
            this.__db.beginTransaction();
            try {
                int v2 = supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
                return v2;
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfSetCancelledState.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void setLastEnqueueTime(String s, long v) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfSetLastEnqueueTime.acquire();
        supportSQLiteStatement0.bindLong(1, v);
        supportSQLiteStatement0.bindString(2, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfSetLastEnqueueTime.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void setNextScheduleTimeOverride(String s, long v) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfSetNextScheduleTimeOverride.acquire();
        supportSQLiteStatement0.bindLong(1, v);
        supportSQLiteStatement0.bindString(2, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfSetNextScheduleTimeOverride.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void setOutput(String s, Data data0) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfSetOutput.acquire();
        supportSQLiteStatement0.bindBlob(1, Data.toByteArrayInternalV1(data0));
        supportSQLiteStatement0.bindString(2, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfSetOutput.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public int setState(State workInfo$State0, String s) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfSetState.acquire();
        supportSQLiteStatement0.bindLong(1, ((long)WorkTypeConverters.stateToInt(workInfo$State0)));
        supportSQLiteStatement0.bindString(2, s);
        try {
            this.__db.beginTransaction();
            try {
                int v2 = supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
                return v2;
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfSetState.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void setStopReason(String s, int v) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatement0 = this.__preparedStmtOfSetStopReason.acquire();
        supportSQLiteStatement0.bindLong(1, ((long)v));
        supportSQLiteStatement0.bindString(2, s);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatement0.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            }
            finally {
                this.__db.endTransaction();
            }
        }
        finally {
            this.__preparedStmtOfSetStopReason.release(supportSQLiteStatement0);
        }
    }

    @Override  // androidx.work.impl.model.WorkSpecDao
    public void updateWorkSpec(WorkSpec workSpec0) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWorkSpec.handle(workSpec0);
            this.__db.setTransactionSuccessful();
        }
        finally {
            this.__db.endTransaction();
        }
    }
}

