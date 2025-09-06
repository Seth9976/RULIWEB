package androidx.work.impl;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase.Callback;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.DependencyDao_Impl;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.PreferenceDao_Impl;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.RawWorkInfoDao_Impl;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoDao_Impl;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkNameDao_Impl;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkProgressDao_Impl;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.model.WorkTagDao_Impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class WorkDatabase_Impl extends WorkDatabase {
    private volatile DependencyDao _dependencyDao;
    private volatile PreferenceDao _preferenceDao;
    private volatile RawWorkInfoDao _rawWorkInfoDao;
    private volatile SystemIdInfoDao _systemIdInfoDao;
    private volatile WorkNameDao _workNameDao;
    private volatile WorkProgressDao _workProgressDao;
    private volatile WorkSpecDao _workSpecDao;
    private volatile WorkTagDao _workTagDao;

    @Override  // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase supportSQLiteDatabase0 = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            supportSQLiteDatabase0.execSQL("PRAGMA defer_foreign_keys = TRUE");
            supportSQLiteDatabase0.execSQL("DELETE FROM `Dependency`");
            supportSQLiteDatabase0.execSQL("DELETE FROM `WorkSpec`");
            supportSQLiteDatabase0.execSQL("DELETE FROM `WorkTag`");
            supportSQLiteDatabase0.execSQL("DELETE FROM `SystemIdInfo`");
            supportSQLiteDatabase0.execSQL("DELETE FROM `WorkName`");
            supportSQLiteDatabase0.execSQL("DELETE FROM `WorkProgress`");
            supportSQLiteDatabase0.execSQL("DELETE FROM `Preference`");
            super.setTransactionSuccessful();
        }
        finally {
            super.endTransaction();
            supportSQLiteDatabase0.query("PRAGMA wal_checkpoint(FULL)").close();
            if(!supportSQLiteDatabase0.inTransaction()) {
                supportSQLiteDatabase0.execSQL("VACUUM");
            }
        }
    }

    @Override  // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), new String[]{"Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference"});
    }

    @Override  // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration0) {
        RoomOpenHelper roomOpenHelper0 = new RoomOpenHelper(databaseConfiguration0, new Delegate(23) {
            @Override  // androidx.room.RoomOpenHelper$Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase0) {
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase0.execSQL("CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
                supportSQLiteDatabase0.execSQL("CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT NOT NULL, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `last_enqueue_time` INTEGER NOT NULL DEFAULT -1, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `period_count` INTEGER NOT NULL DEFAULT 0, `generation` INTEGER NOT NULL DEFAULT 0, `next_schedule_time_override` INTEGER NOT NULL DEFAULT 9223372036854775807, `next_schedule_time_override_generation` INTEGER NOT NULL DEFAULT 0, `stop_reason` INTEGER NOT NULL DEFAULT -256, `trace_tag` TEXT, `required_network_type` INTEGER NOT NULL, `required_network_request` BLOB NOT NULL DEFAULT x\'\', `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase0.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
                supportSQLiteDatabase0.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_last_enqueue_time` ON `WorkSpec` (`last_enqueue_time`)");
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase0.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `generation` INTEGER NOT NULL DEFAULT 0, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`, `generation`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase0.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
                supportSQLiteDatabase0.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                supportSQLiteDatabase0.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \'86254750241babac4b8d52996a675549\')");
            }

            @Override  // androidx.room.RoomOpenHelper$Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase0) {
                supportSQLiteDatabase0.execSQL("DROP TABLE IF EXISTS `Dependency`");
                supportSQLiteDatabase0.execSQL("DROP TABLE IF EXISTS `WorkSpec`");
                supportSQLiteDatabase0.execSQL("DROP TABLE IF EXISTS `WorkTag`");
                supportSQLiteDatabase0.execSQL("DROP TABLE IF EXISTS `SystemIdInfo`");
                supportSQLiteDatabase0.execSQL("DROP TABLE IF EXISTS `WorkName`");
                supportSQLiteDatabase0.execSQL("DROP TABLE IF EXISTS `WorkProgress`");
                supportSQLiteDatabase0.execSQL("DROP TABLE IF EXISTS `Preference`");
                List list0 = WorkDatabase_Impl.this.mCallbacks;
                if(list0 != null) {
                    for(Object object0: list0) {
                        ((Callback)object0).onDestructiveMigration(supportSQLiteDatabase0);
                    }
                }
            }

            @Override  // androidx.room.RoomOpenHelper$Delegate
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase0) {
                List list0 = WorkDatabase_Impl.this.mCallbacks;
                if(list0 != null) {
                    for(Object object0: list0) {
                        ((Callback)object0).onCreate(supportSQLiteDatabase0);
                    }
                }
            }

            @Override  // androidx.room.RoomOpenHelper$Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase0) {
                WorkDatabase_Impl.this.mDatabase = supportSQLiteDatabase0;
                supportSQLiteDatabase0.execSQL("PRAGMA foreign_keys = ON");
                WorkDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase0);
                List list0 = WorkDatabase_Impl.this.mCallbacks;
                if(list0 != null) {
                    for(Object object0: list0) {
                        ((Callback)object0).onOpen(supportSQLiteDatabase0);
                    }
                }
            }

            @Override  // androidx.room.RoomOpenHelper$Delegate
            public void onPostMigrate(SupportSQLiteDatabase db) {
            }

            @Override  // androidx.room.RoomOpenHelper$Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase0) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase0);
            }

            @Override  // androidx.room.RoomOpenHelper$Delegate
            public ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase0) {
                HashMap hashMap0 = new HashMap(2);
                hashMap0.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 1, null, 1));
                hashMap0.put("prerequisite_id", new Column("prerequisite_id", "TEXT", true, 2, null, 1));
                HashSet hashSet0 = new HashSet(2);
                hashSet0.add(new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                hashSet0.add(new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"prerequisite_id"}), Arrays.asList(new String[]{"id"})));
                HashSet hashSet1 = new HashSet(2);
                hashSet1.add(new Index("index_Dependency_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"ASC"})));
                hashSet1.add(new Index("index_Dependency_prerequisite_id", false, Arrays.asList(new String[]{"prerequisite_id"}), Arrays.asList(new String[]{"ASC"})));
                TableInfo tableInfo0 = new TableInfo("Dependency", hashMap0, hashSet0, hashSet1);
                TableInfo tableInfo1 = TableInfo.read(supportSQLiteDatabase0, "Dependency");
                if(!tableInfo0.equals(tableInfo1)) {
                    return new ValidationResult(false, "Dependency(androidx.work.impl.model.Dependency).\n Expected:\n" + tableInfo0 + "\n Found:\n" + tableInfo1);
                }
                HashMap hashMap1 = new HashMap(0x20);
                hashMap1.put("id", new Column("id", "TEXT", true, 1, null, 1));
                hashMap1.put("state", new Column("state", "INTEGER", true, 0, null, 1));
                hashMap1.put("worker_class_name", new Column("worker_class_name", "TEXT", true, 0, null, 1));
                hashMap1.put("input_merger_class_name", new Column("input_merger_class_name", "TEXT", true, 0, null, 1));
                hashMap1.put("input", new Column("input", "BLOB", true, 0, null, 1));
                hashMap1.put("output", new Column("output", "BLOB", true, 0, null, 1));
                hashMap1.put("initial_delay", new Column("initial_delay", "INTEGER", true, 0, null, 1));
                hashMap1.put("interval_duration", new Column("interval_duration", "INTEGER", true, 0, null, 1));
                hashMap1.put("flex_duration", new Column("flex_duration", "INTEGER", true, 0, null, 1));
                hashMap1.put("run_attempt_count", new Column("run_attempt_count", "INTEGER", true, 0, null, 1));
                hashMap1.put("backoff_policy", new Column("backoff_policy", "INTEGER", true, 0, null, 1));
                hashMap1.put("backoff_delay_duration", new Column("backoff_delay_duration", "INTEGER", true, 0, null, 1));
                hashMap1.put("last_enqueue_time", new Column("last_enqueue_time", "INTEGER", true, 0, "-1", 1));
                hashMap1.put("minimum_retention_duration", new Column("minimum_retention_duration", "INTEGER", true, 0, null, 1));
                hashMap1.put("schedule_requested_at", new Column("schedule_requested_at", "INTEGER", true, 0, null, 1));
                hashMap1.put("run_in_foreground", new Column("run_in_foreground", "INTEGER", true, 0, null, 1));
                hashMap1.put("out_of_quota_policy", new Column("out_of_quota_policy", "INTEGER", true, 0, null, 1));
                hashMap1.put("period_count", new Column("period_count", "INTEGER", true, 0, "0", 1));
                hashMap1.put("generation", new Column("generation", "INTEGER", true, 0, "0", 1));
                hashMap1.put("next_schedule_time_override", new Column("next_schedule_time_override", "INTEGER", true, 0, "9223372036854775807", 1));
                hashMap1.put("next_schedule_time_override_generation", new Column("next_schedule_time_override_generation", "INTEGER", true, 0, "0", 1));
                hashMap1.put("stop_reason", new Column("stop_reason", "INTEGER", true, 0, "-256", 1));
                hashMap1.put("trace_tag", new Column("trace_tag", "TEXT", false, 0, null, 1));
                hashMap1.put("required_network_type", new Column("required_network_type", "INTEGER", true, 0, null, 1));
                hashMap1.put("required_network_request", new Column("required_network_request", "BLOB", true, 0, "x\'\'", 1));
                hashMap1.put("requires_charging", new Column("requires_charging", "INTEGER", true, 0, null, 1));
                hashMap1.put("requires_device_idle", new Column("requires_device_idle", "INTEGER", true, 0, null, 1));
                hashMap1.put("requires_battery_not_low", new Column("requires_battery_not_low", "INTEGER", true, 0, null, 1));
                hashMap1.put("requires_storage_not_low", new Column("requires_storage_not_low", "INTEGER", true, 0, null, 1));
                hashMap1.put("trigger_content_update_delay", new Column("trigger_content_update_delay", "INTEGER", true, 0, null, 1));
                hashMap1.put("trigger_max_content_delay", new Column("trigger_max_content_delay", "INTEGER", true, 0, null, 1));
                hashMap1.put("content_uri_triggers", new Column("content_uri_triggers", "BLOB", true, 0, null, 1));
                HashSet hashSet2 = new HashSet(0);
                HashSet hashSet3 = new HashSet(2);
                hashSet3.add(new Index("index_WorkSpec_schedule_requested_at", false, Arrays.asList(new String[]{"schedule_requested_at"}), Arrays.asList(new String[]{"ASC"})));
                hashSet3.add(new Index("index_WorkSpec_last_enqueue_time", false, Arrays.asList(new String[]{"last_enqueue_time"}), Arrays.asList(new String[]{"ASC"})));
                TableInfo tableInfo2 = new TableInfo("WorkSpec", hashMap1, hashSet2, hashSet3);
                TableInfo tableInfo3 = TableInfo.read(supportSQLiteDatabase0, "WorkSpec");
                if(!tableInfo2.equals(tableInfo3)) {
                    return new ValidationResult(false, "WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n" + tableInfo2 + "\n Found:\n" + tableInfo3);
                }
                HashMap hashMap2 = new HashMap(2);
                hashMap2.put("tag", new Column("tag", "TEXT", true, 1, null, 1));
                hashMap2.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 2, null, 1));
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                HashSet hashSet5 = new HashSet(1);
                hashSet5.add(new Index("index_WorkTag_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"ASC"})));
                TableInfo tableInfo4 = new TableInfo("WorkTag", hashMap2, hashSet4, hashSet5);
                TableInfo tableInfo5 = TableInfo.read(supportSQLiteDatabase0, "WorkTag");
                if(!tableInfo4.equals(tableInfo5)) {
                    return new ValidationResult(false, "WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n" + tableInfo4 + "\n Found:\n" + tableInfo5);
                }
                HashMap hashMap3 = new HashMap(3);
                hashMap3.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 1, null, 1));
                hashMap3.put("generation", new Column("generation", "INTEGER", true, 2, "0", 1));
                hashMap3.put("system_id", new Column("system_id", "INTEGER", true, 0, null, 1));
                HashSet hashSet6 = new HashSet(1);
                hashSet6.add(new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                TableInfo tableInfo6 = new TableInfo("SystemIdInfo", hashMap3, hashSet6, new HashSet(0));
                TableInfo tableInfo7 = TableInfo.read(supportSQLiteDatabase0, "SystemIdInfo");
                if(!tableInfo6.equals(tableInfo7)) {
                    return new ValidationResult(false, "SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n" + tableInfo6 + "\n Found:\n" + tableInfo7);
                }
                HashMap hashMap4 = new HashMap(2);
                hashMap4.put("name", new Column("name", "TEXT", true, 1, null, 1));
                hashMap4.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 2, null, 1));
                HashSet hashSet7 = new HashSet(1);
                hashSet7.add(new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                HashSet hashSet8 = new HashSet(1);
                hashSet8.add(new Index("index_WorkName_work_spec_id", false, Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"ASC"})));
                TableInfo tableInfo8 = new TableInfo("WorkName", hashMap4, hashSet7, hashSet8);
                TableInfo tableInfo9 = TableInfo.read(supportSQLiteDatabase0, "WorkName");
                if(!tableInfo8.equals(tableInfo9)) {
                    return new ValidationResult(false, "WorkName(androidx.work.impl.model.WorkName).\n Expected:\n" + tableInfo8 + "\n Found:\n" + tableInfo9);
                }
                HashMap hashMap5 = new HashMap(2);
                hashMap5.put("work_spec_id", new Column("work_spec_id", "TEXT", true, 1, null, 1));
                hashMap5.put("progress", new Column("progress", "BLOB", true, 0, null, 1));
                HashSet hashSet9 = new HashSet(1);
                hashSet9.add(new ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList(new String[]{"work_spec_id"}), Arrays.asList(new String[]{"id"})));
                TableInfo tableInfo10 = new TableInfo("WorkProgress", hashMap5, hashSet9, new HashSet(0));
                TableInfo tableInfo11 = TableInfo.read(supportSQLiteDatabase0, "WorkProgress");
                if(!tableInfo10.equals(tableInfo11)) {
                    return new ValidationResult(false, "WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n" + tableInfo10 + "\n Found:\n" + tableInfo11);
                }
                HashMap hashMap6 = new HashMap(2);
                hashMap6.put("key", new Column("key", "TEXT", true, 1, null, 1));
                hashMap6.put("long_value", new Column("long_value", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo12 = new TableInfo("Preference", hashMap6, new HashSet(0), new HashSet(0));
                TableInfo tableInfo13 = TableInfo.read(supportSQLiteDatabase0, "Preference");
                return tableInfo12.equals(tableInfo13) ? new ValidationResult(true, null) : new ValidationResult(false, "Preference(androidx.work.impl.model.Preference).\n Expected:\n" + tableInfo12 + "\n Found:\n" + tableInfo13);
            }
        }, "86254750241babac4b8d52996a675549", "1cbd3130fa23b59692c061c594c16cc0");
        Configuration supportSQLiteOpenHelper$Configuration0 = Configuration.builder(databaseConfiguration0.context).name(databaseConfiguration0.name).callback(roomOpenHelper0).build();
        return databaseConfiguration0.sqliteOpenHelperFactory.create(supportSQLiteOpenHelper$Configuration0);
    }

    @Override  // androidx.work.impl.WorkDatabase
    public DependencyDao dependencyDao() {
        if(this._dependencyDao != null) {
            return this._dependencyDao;
        }
        synchronized(this) {
            if(this._dependencyDao == null) {
                this._dependencyDao = new DependencyDao_Impl(this);
            }
            return this._dependencyDao;
        }
    }

    @Override  // androidx.room.RoomDatabase
    public List getAutoMigrations(Map map0) {
        List list0 = new ArrayList();
        list0.add(new WorkDatabase_AutoMigration_13_14_Impl());
        list0.add(new WorkDatabase_AutoMigration_14_15_Impl());
        list0.add(new WorkDatabase_AutoMigration_16_17_Impl());
        list0.add(new WorkDatabase_AutoMigration_17_18_Impl());
        list0.add(new WorkDatabase_AutoMigration_18_19_Impl());
        list0.add(new WorkDatabase_AutoMigration_19_20_Impl());
        list0.add(new WorkDatabase_AutoMigration_20_21_Impl());
        list0.add(new WorkDatabase_AutoMigration_22_23_Impl());
        return list0;
    }

    @Override  // androidx.room.RoomDatabase
    public Set getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override  // androidx.room.RoomDatabase
    protected Map getRequiredTypeConverters() {
        Map map0 = new HashMap();
        List list0 = WorkSpecDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(WorkSpecDao.class, list0);
        List list1 = DependencyDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(DependencyDao.class, list1);
        List list2 = WorkTagDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(WorkTagDao.class, list2);
        List list3 = SystemIdInfoDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(SystemIdInfoDao.class, list3);
        List list4 = WorkNameDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(WorkNameDao.class, list4);
        List list5 = WorkProgressDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(WorkProgressDao.class, list5);
        List list6 = PreferenceDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(PreferenceDao.class, list6);
        List list7 = RawWorkInfoDao_Impl.getRequiredConverters();
        ((HashMap)map0).put(RawWorkInfoDao.class, list7);
        return map0;
    }

    @Override  // androidx.work.impl.WorkDatabase
    public PreferenceDao preferenceDao() {
        if(this._preferenceDao != null) {
            return this._preferenceDao;
        }
        synchronized(this) {
            if(this._preferenceDao == null) {
                this._preferenceDao = new PreferenceDao_Impl(this);
            }
            return this._preferenceDao;
        }
    }

    @Override  // androidx.work.impl.WorkDatabase
    public RawWorkInfoDao rawWorkInfoDao() {
        if(this._rawWorkInfoDao != null) {
            return this._rawWorkInfoDao;
        }
        synchronized(this) {
            if(this._rawWorkInfoDao == null) {
                this._rawWorkInfoDao = new RawWorkInfoDao_Impl(this);
            }
            return this._rawWorkInfoDao;
        }
    }

    @Override  // androidx.work.impl.WorkDatabase
    public SystemIdInfoDao systemIdInfoDao() {
        if(this._systemIdInfoDao != null) {
            return this._systemIdInfoDao;
        }
        synchronized(this) {
            if(this._systemIdInfoDao == null) {
                this._systemIdInfoDao = new SystemIdInfoDao_Impl(this);
            }
            return this._systemIdInfoDao;
        }
    }

    @Override  // androidx.work.impl.WorkDatabase
    public WorkNameDao workNameDao() {
        if(this._workNameDao != null) {
            return this._workNameDao;
        }
        synchronized(this) {
            if(this._workNameDao == null) {
                this._workNameDao = new WorkNameDao_Impl(this);
            }
            return this._workNameDao;
        }
    }

    @Override  // androidx.work.impl.WorkDatabase
    public WorkProgressDao workProgressDao() {
        if(this._workProgressDao != null) {
            return this._workProgressDao;
        }
        synchronized(this) {
            if(this._workProgressDao == null) {
                this._workProgressDao = new WorkProgressDao_Impl(this);
            }
            return this._workProgressDao;
        }
    }

    @Override  // androidx.work.impl.WorkDatabase
    public WorkSpecDao workSpecDao() {
        if(this._workSpecDao != null) {
            return this._workSpecDao;
        }
        synchronized(this) {
            if(this._workSpecDao == null) {
                this._workSpecDao = new WorkSpecDao_Impl(this);
            }
            return this._workSpecDao;
        }
    }

    @Override  // androidx.work.impl.WorkDatabase
    public WorkTagDao workTagDao() {
        if(this._workTagDao != null) {
            return this._workTagDao;
        }
        synchronized(this) {
            if(this._workTagDao == null) {
                this._workTagDao = new WorkTagDao_Impl(this);
            }
            return this._workTagDao;
        }
    }
}

