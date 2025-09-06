package androidx.work.impl.model;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.WorkInfo.State;
import androidx.work.impl.utils.NetworkRequestCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlinx.coroutines.flow.Flow;

public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase roomDatabase0) {
        this.__db = roomDatabase0;
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

    public static List getRequiredConverters() {
        return Collections.EMPTY_LIST;
    }

    @Override  // androidx.work.impl.model.RawWorkInfoDao
    public List getWorkInfoPojos(SupportSQLiteQuery supportSQLiteQuery0) {
        Set set0;
        long v34;
        boolean z7;
        boolean z6;
        boolean z5;
        boolean z4;
        boolean z3;
        boolean z2;
        boolean z1;
        NetworkRequestCompat networkRequestCompat0;
        NetworkType networkType0;
        int v33;
        long v32;
        int v31;
        int v30;
        long v29;
        long v28;
        BackoffPolicy backoffPolicy0;
        int v27;
        long v26;
        long v25;
        long v24;
        Data data0;
        State workInfo$State0;
        String s2;
        try {
            this.__db.assertNotSuspendingTransaction();
            try(Cursor cursor0 = DBUtil.query(this.__db, supportSQLiteQuery0, true, null)) {
                int v = CursorUtil.getColumnIndex(cursor0, "id");
                int v1 = CursorUtil.getColumnIndex(cursor0, "state");
                int v2 = CursorUtil.getColumnIndex(cursor0, "output");
                int v3 = CursorUtil.getColumnIndex(cursor0, "initial_delay");
                int v4 = CursorUtil.getColumnIndex(cursor0, "interval_duration");
                int v5 = CursorUtil.getColumnIndex(cursor0, "flex_duration");
                int v6 = CursorUtil.getColumnIndex(cursor0, "run_attempt_count");
                int v7 = CursorUtil.getColumnIndex(cursor0, "backoff_policy");
                int v8 = CursorUtil.getColumnIndex(cursor0, "backoff_delay_duration");
                int v9 = CursorUtil.getColumnIndex(cursor0, "last_enqueue_time");
                int v10 = CursorUtil.getColumnIndex(cursor0, "period_count");
                int v11 = CursorUtil.getColumnIndex(cursor0, "generation");
                int v12 = CursorUtil.getColumnIndex(cursor0, "next_schedule_time_override");
                int v13 = CursorUtil.getColumnIndex(cursor0, "stop_reason");
                int v14 = CursorUtil.getColumnIndex(cursor0, "required_network_type");
                int v15 = CursorUtil.getColumnIndex(cursor0, "required_network_request");
                int v16 = CursorUtil.getColumnIndex(cursor0, "requires_charging");
                int v17 = CursorUtil.getColumnIndex(cursor0, "requires_device_idle");
                int v18 = CursorUtil.getColumnIndex(cursor0, "requires_battery_not_low");
                int v19 = CursorUtil.getColumnIndex(cursor0, "requires_storage_not_low");
                int v20 = CursorUtil.getColumnIndex(cursor0, "trigger_content_update_delay");
                int v21 = CursorUtil.getColumnIndex(cursor0, "trigger_max_content_delay");
                int v22 = CursorUtil.getColumnIndex(cursor0, "content_uri_triggers");
                HashMap hashMap0 = new HashMap();
                HashMap hashMap1 = new HashMap();
                while(true) {
                    if(!cursor0.moveToNext()) {
                        break;
                    }
                    String s = cursor0.getString(v);
                    if(!hashMap0.containsKey(s)) {
                        hashMap0.put(s, new ArrayList());
                    }
                    String s1 = cursor0.getString(v);
                    if(!hashMap1.containsKey(s1)) {
                        hashMap1.put(s1, new ArrayList());
                    }
                }
                cursor0.moveToPosition(-1);
                this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                List list0 = new ArrayList(cursor0.getCount());
                while(true) {
                    if(!cursor0.moveToNext()) {
                        break;
                    }
                    if(v == -1) {
                        s2 = null;
                    }
                    else {
                        s2 = cursor0.getString(v);
                    }
                    if(v1 == -1) {
                        workInfo$State0 = null;
                    }
                    else {
                        workInfo$State0 = WorkTypeConverters.intToState(cursor0.getInt(v1));
                    }
                    if(v2 == -1) {
                        data0 = null;
                    }
                    else {
                        data0 = Data.fromByteArray(cursor0.getBlob(v2));
                    }
                    long v23 = 0L;
                    if(v3 == -1) {
                        v24 = 0L;
                    }
                    else {
                        v24 = cursor0.getLong(v3);
                    }
                    if(v4 == -1) {
                        v25 = 0L;
                    }
                    else {
                        v25 = cursor0.getLong(v4);
                    }
                    if(v5 == -1) {
                        v26 = 0L;
                    }
                    else {
                        v26 = cursor0.getLong(v5);
                    }
                    boolean z = false;
                    if(v6 == -1) {
                        v27 = 0;
                    }
                    else {
                        v27 = cursor0.getInt(v6);
                    }
                    if(v7 == -1) {
                        backoffPolicy0 = null;
                    }
                    else {
                        backoffPolicy0 = WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v7));
                    }
                    if(v8 == -1) {
                        v28 = 0L;
                    }
                    else {
                        v28 = cursor0.getLong(v8);
                    }
                    if(v9 == -1) {
                        v29 = 0L;
                    }
                    else {
                        v29 = cursor0.getLong(v9);
                    }
                    if(v10 == -1) {
                        v30 = 0;
                    }
                    else {
                        v30 = cursor0.getInt(v10);
                    }
                    if(v11 == -1) {
                        v31 = 0;
                    }
                    else {
                        v31 = cursor0.getInt(v11);
                    }
                    if(v12 == -1) {
                        v32 = 0L;
                    }
                    else {
                        v32 = cursor0.getLong(v12);
                    }
                    if(v13 == -1) {
                        v33 = 0;
                    }
                    else {
                        v33 = cursor0.getInt(v13);
                    }
                    if(v14 == -1) {
                        networkType0 = null;
                    }
                    else {
                        networkType0 = WorkTypeConverters.intToNetworkType(cursor0.getInt(v14));
                    }
                    if(v15 == -1) {
                        networkRequestCompat0 = null;
                    }
                    else {
                        networkRequestCompat0 = WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v15));
                    }
                    if(v16 == -1) {
                        z2 = false;
                    }
                    else {
                        if(cursor0.getInt(v16) == 0) {
                            z1 = false;
                        }
                        else {
                            z1 = true;
                        }
                        z2 = z1;
                    }
                    if(v17 == -1) {
                        z4 = false;
                    }
                    else {
                        if(cursor0.getInt(v17) == 0) {
                            z3 = false;
                        }
                        else {
                            z3 = true;
                        }
                        z4 = z3;
                    }
                    if(v18 == -1) {
                        z6 = false;
                    }
                    else {
                        if(cursor0.getInt(v18) == 0) {
                            z5 = false;
                        }
                        else {
                            z5 = true;
                        }
                        z6 = z5;
                    }
                    if(v19 == -1) {
                        z7 = false;
                    }
                    else {
                        if(cursor0.getInt(v19) != 0) {
                            z = true;
                        }
                        z7 = z;
                    }
                    if(v20 == -1) {
                        v34 = 0L;
                    }
                    else {
                        v34 = cursor0.getLong(v20);
                    }
                    if(v21 != -1) {
                        v23 = cursor0.getLong(v21);
                    }
                    if(v22 == -1) {
                        set0 = null;
                    }
                    else {
                        set0 = WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v22));
                    }
                    list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v24, v25, v26, new Constraints(networkRequestCompat0, networkType0, z2, z4, z6, z7, v34, v23, set0), v27, backoffPolicy0, v28, v29, v30, v31, v32, v33, ((ArrayList)hashMap0.get(cursor0.getString(v))), ((ArrayList)hashMap1.get(cursor0.getString(v)))));
                }
                return list0;
            }
        }
        catch(Throwable throwable0) {
            throw throwable0;
        }
    }

    @Override  // androidx.work.impl.model.RawWorkInfoDao
    public Flow getWorkInfoPojosFlow(SupportSQLiteQuery supportSQLiteQuery0) {
        androidx.work.impl.model.RawWorkInfoDao_Impl.2 rawWorkInfoDao_Impl$20 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                Set set0;
                long v34;
                boolean z7;
                boolean z6;
                boolean z5;
                boolean z4;
                boolean z3;
                boolean z2;
                boolean z1;
                NetworkRequestCompat networkRequestCompat0;
                NetworkType networkType0;
                int v33;
                long v32;
                int v31;
                int v30;
                long v29;
                long v28;
                BackoffPolicy backoffPolicy0;
                int v27;
                long v26;
                long v25;
                long v24;
                Data data0;
                State workInfo$State0;
                String s2;
                try(Cursor cursor0 = DBUtil.query(RawWorkInfoDao_Impl.this.__db, supportSQLiteQuery0, true, null)) {
                    int v = CursorUtil.getColumnIndex(cursor0, "id");
                    int v1 = CursorUtil.getColumnIndex(cursor0, "state");
                    int v2 = CursorUtil.getColumnIndex(cursor0, "output");
                    int v3 = CursorUtil.getColumnIndex(cursor0, "initial_delay");
                    int v4 = CursorUtil.getColumnIndex(cursor0, "interval_duration");
                    int v5 = CursorUtil.getColumnIndex(cursor0, "flex_duration");
                    int v6 = CursorUtil.getColumnIndex(cursor0, "run_attempt_count");
                    int v7 = CursorUtil.getColumnIndex(cursor0, "backoff_policy");
                    int v8 = CursorUtil.getColumnIndex(cursor0, "backoff_delay_duration");
                    int v9 = CursorUtil.getColumnIndex(cursor0, "last_enqueue_time");
                    int v10 = CursorUtil.getColumnIndex(cursor0, "period_count");
                    int v11 = CursorUtil.getColumnIndex(cursor0, "generation");
                    int v12 = CursorUtil.getColumnIndex(cursor0, "next_schedule_time_override");
                    int v13 = CursorUtil.getColumnIndex(cursor0, "stop_reason");
                    int v14 = CursorUtil.getColumnIndex(cursor0, "required_network_type");
                    int v15 = CursorUtil.getColumnIndex(cursor0, "required_network_request");
                    int v16 = CursorUtil.getColumnIndex(cursor0, "requires_charging");
                    int v17 = CursorUtil.getColumnIndex(cursor0, "requires_device_idle");
                    int v18 = CursorUtil.getColumnIndex(cursor0, "requires_battery_not_low");
                    int v19 = CursorUtil.getColumnIndex(cursor0, "requires_storage_not_low");
                    int v20 = CursorUtil.getColumnIndex(cursor0, "trigger_content_update_delay");
                    int v21 = CursorUtil.getColumnIndex(cursor0, "trigger_max_content_delay");
                    int v22 = CursorUtil.getColumnIndex(cursor0, "content_uri_triggers");
                    HashMap hashMap0 = new HashMap();
                    HashMap hashMap1 = new HashMap();
                    while(true) {
                        if(!cursor0.moveToNext()) {
                            break;
                        }
                        String s = cursor0.getString(v);
                        if(!hashMap0.containsKey(s)) {
                            hashMap0.put(s, new ArrayList());
                        }
                        String s1 = cursor0.getString(v);
                        if(!hashMap1.containsKey(s1)) {
                            hashMap1.put(s1, new ArrayList());
                        }
                    }
                    cursor0.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                    List list0 = new ArrayList(cursor0.getCount());
                    while(true) {
                        if(!cursor0.moveToNext()) {
                            break;
                        }
                        s2 = v == -1 ? null : cursor0.getString(v);
                        workInfo$State0 = v1 == -1 ? null : WorkTypeConverters.intToState(cursor0.getInt(v1));
                        data0 = v2 == -1 ? null : Data.fromByteArray(cursor0.getBlob(v2));
                        long v23 = 0L;
                        v24 = v3 == -1 ? 0L : cursor0.getLong(v3);
                        v25 = v4 == -1 ? 0L : cursor0.getLong(v4);
                        v26 = v5 == -1 ? 0L : cursor0.getLong(v5);
                        boolean z = false;
                        v27 = v6 == -1 ? 0 : cursor0.getInt(v6);
                        backoffPolicy0 = v7 == -1 ? null : WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v7));
                        v28 = v8 == -1 ? 0L : cursor0.getLong(v8);
                        v29 = v9 == -1 ? 0L : cursor0.getLong(v9);
                        v30 = v10 == -1 ? 0 : cursor0.getInt(v10);
                        v31 = v11 == -1 ? 0 : cursor0.getInt(v11);
                        v32 = v12 == -1 ? 0L : cursor0.getLong(v12);
                        v33 = v13 == -1 ? 0 : cursor0.getInt(v13);
                        networkType0 = v14 == -1 ? null : WorkTypeConverters.intToNetworkType(cursor0.getInt(v14));
                        networkRequestCompat0 = v15 == -1 ? null : WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v15));
                        if(v16 == -1) {
                            z2 = false;
                        }
                        else {
                            z1 = cursor0.getInt(v16) == 0 ? false : true;
                            z2 = z1;
                        }
                        if(v17 == -1) {
                            z4 = false;
                        }
                        else {
                            z3 = cursor0.getInt(v17) == 0 ? false : true;
                            z4 = z3;
                        }
                        if(v18 == -1) {
                            z6 = false;
                        }
                        else {
                            z5 = cursor0.getInt(v18) == 0 ? false : true;
                            z6 = z5;
                        }
                        if(v19 == -1) {
                            z7 = false;
                        }
                        else {
                            if(cursor0.getInt(v19) != 0) {
                                z = true;
                            }
                            z7 = z;
                        }
                        v34 = v20 == -1 ? 0L : cursor0.getLong(v20);
                        if(v21 != -1) {
                            v23 = cursor0.getLong(v21);
                        }
                        set0 = v22 == -1 ? null : WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v22));
                        list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v24, v25, v26, new Constraints(networkRequestCompat0, networkType0, z2, z4, z6, z7, v34, v23, set0), v27, backoffPolicy0, v28, v29, v30, v31, v32, v33, ((ArrayList)hashMap0.get(cursor0.getString(v))), ((ArrayList)hashMap1.get(cursor0.getString(v)))));
                    }
                    return list0;
                }
            }
        };
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, rawWorkInfoDao_Impl$20);
    }

    @Override  // androidx.work.impl.model.RawWorkInfoDao
    public LiveData getWorkInfoPojosLiveData(SupportSQLiteQuery supportSQLiteQuery0) {
        androidx.work.impl.model.RawWorkInfoDao_Impl.1 rawWorkInfoDao_Impl$10 = new Callable() {
            @Override
            public Object call() throws Exception {
                return this.call();
            }

            public List call() throws Exception {
                Set set0;
                long v34;
                boolean z7;
                boolean z6;
                boolean z5;
                boolean z4;
                boolean z3;
                boolean z2;
                boolean z1;
                NetworkRequestCompat networkRequestCompat0;
                NetworkType networkType0;
                int v33;
                long v32;
                int v31;
                int v30;
                long v29;
                long v28;
                BackoffPolicy backoffPolicy0;
                int v27;
                long v26;
                long v25;
                long v24;
                Data data0;
                State workInfo$State0;
                String s2;
                try(Cursor cursor0 = DBUtil.query(RawWorkInfoDao_Impl.this.__db, supportSQLiteQuery0, true, null)) {
                    int v = CursorUtil.getColumnIndex(cursor0, "id");
                    int v1 = CursorUtil.getColumnIndex(cursor0, "state");
                    int v2 = CursorUtil.getColumnIndex(cursor0, "output");
                    int v3 = CursorUtil.getColumnIndex(cursor0, "initial_delay");
                    int v4 = CursorUtil.getColumnIndex(cursor0, "interval_duration");
                    int v5 = CursorUtil.getColumnIndex(cursor0, "flex_duration");
                    int v6 = CursorUtil.getColumnIndex(cursor0, "run_attempt_count");
                    int v7 = CursorUtil.getColumnIndex(cursor0, "backoff_policy");
                    int v8 = CursorUtil.getColumnIndex(cursor0, "backoff_delay_duration");
                    int v9 = CursorUtil.getColumnIndex(cursor0, "last_enqueue_time");
                    int v10 = CursorUtil.getColumnIndex(cursor0, "period_count");
                    int v11 = CursorUtil.getColumnIndex(cursor0, "generation");
                    int v12 = CursorUtil.getColumnIndex(cursor0, "next_schedule_time_override");
                    int v13 = CursorUtil.getColumnIndex(cursor0, "stop_reason");
                    int v14 = CursorUtil.getColumnIndex(cursor0, "required_network_type");
                    int v15 = CursorUtil.getColumnIndex(cursor0, "required_network_request");
                    int v16 = CursorUtil.getColumnIndex(cursor0, "requires_charging");
                    int v17 = CursorUtil.getColumnIndex(cursor0, "requires_device_idle");
                    int v18 = CursorUtil.getColumnIndex(cursor0, "requires_battery_not_low");
                    int v19 = CursorUtil.getColumnIndex(cursor0, "requires_storage_not_low");
                    int v20 = CursorUtil.getColumnIndex(cursor0, "trigger_content_update_delay");
                    int v21 = CursorUtil.getColumnIndex(cursor0, "trigger_max_content_delay");
                    int v22 = CursorUtil.getColumnIndex(cursor0, "content_uri_triggers");
                    HashMap hashMap0 = new HashMap();
                    HashMap hashMap1 = new HashMap();
                    while(true) {
                        if(!cursor0.moveToNext()) {
                            break;
                        }
                        String s = cursor0.getString(v);
                        if(!hashMap0.containsKey(s)) {
                            hashMap0.put(s, new ArrayList());
                        }
                        String s1 = cursor0.getString(v);
                        if(!hashMap1.containsKey(s1)) {
                            hashMap1.put(s1, new ArrayList());
                        }
                    }
                    cursor0.moveToPosition(-1);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkTagAsjavaLangString(hashMap0);
                    RawWorkInfoDao_Impl.this.__fetchRelationshipWorkProgressAsandroidxWorkData(hashMap1);
                    List list0 = new ArrayList(cursor0.getCount());
                    while(true) {
                        if(!cursor0.moveToNext()) {
                            break;
                        }
                        s2 = v == -1 ? null : cursor0.getString(v);
                        workInfo$State0 = v1 == -1 ? null : WorkTypeConverters.intToState(cursor0.getInt(v1));
                        data0 = v2 == -1 ? null : Data.fromByteArray(cursor0.getBlob(v2));
                        long v23 = 0L;
                        v24 = v3 == -1 ? 0L : cursor0.getLong(v3);
                        v25 = v4 == -1 ? 0L : cursor0.getLong(v4);
                        v26 = v5 == -1 ? 0L : cursor0.getLong(v5);
                        boolean z = false;
                        v27 = v6 == -1 ? 0 : cursor0.getInt(v6);
                        backoffPolicy0 = v7 == -1 ? null : WorkTypeConverters.intToBackoffPolicy(cursor0.getInt(v7));
                        v28 = v8 == -1 ? 0L : cursor0.getLong(v8);
                        v29 = v9 == -1 ? 0L : cursor0.getLong(v9);
                        v30 = v10 == -1 ? 0 : cursor0.getInt(v10);
                        v31 = v11 == -1 ? 0 : cursor0.getInt(v11);
                        v32 = v12 == -1 ? 0L : cursor0.getLong(v12);
                        v33 = v13 == -1 ? 0 : cursor0.getInt(v13);
                        networkType0 = v14 == -1 ? null : WorkTypeConverters.intToNetworkType(cursor0.getInt(v14));
                        networkRequestCompat0 = v15 == -1 ? null : WorkTypeConverters.toNetworkRequest$work_runtime_release(cursor0.getBlob(v15));
                        if(v16 == -1) {
                            z2 = false;
                        }
                        else {
                            z1 = cursor0.getInt(v16) == 0 ? false : true;
                            z2 = z1;
                        }
                        if(v17 == -1) {
                            z4 = false;
                        }
                        else {
                            z3 = cursor0.getInt(v17) == 0 ? false : true;
                            z4 = z3;
                        }
                        if(v18 == -1) {
                            z6 = false;
                        }
                        else {
                            z5 = cursor0.getInt(v18) == 0 ? false : true;
                            z6 = z5;
                        }
                        if(v19 == -1) {
                            z7 = false;
                        }
                        else {
                            if(cursor0.getInt(v19) != 0) {
                                z = true;
                            }
                            z7 = z;
                        }
                        v34 = v20 == -1 ? 0L : cursor0.getLong(v20);
                        if(v21 != -1) {
                            v23 = cursor0.getLong(v21);
                        }
                        set0 = v22 == -1 ? null : WorkTypeConverters.byteArrayToSetOfTriggers(cursor0.getBlob(v22));
                        list0.add(new WorkInfoPojo(s2, workInfo$State0, data0, v24, v25, v26, new Constraints(networkRequestCompat0, networkType0, z2, z4, z6, z7, v34, v23, set0), v27, backoffPolicy0, v28, v29, v30, v31, v32, v33, ((ArrayList)hashMap0.get(cursor0.getString(v))), ((ArrayList)hashMap1.get(cursor0.getString(v)))));
                    }
                    return list0;
                }
            }
        };
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, rawWorkInfoDao_Impl$10);
    }

    // 检测为 Lambda 实现
    Unit lambda$__fetchRelationshipWorkProgressAsandroidxWorkData$1$androidx-work-impl-model-RawWorkInfoDao_Impl(HashMap hashMap0) [...]

    // 检测为 Lambda 实现
    Unit lambda$__fetchRelationshipWorkTagAsjavaLangString$0$androidx-work-impl-model-RawWorkInfoDao_Impl(HashMap hashMap0) [...]
}

