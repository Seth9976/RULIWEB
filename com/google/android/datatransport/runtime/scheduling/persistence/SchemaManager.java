package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

final class SchemaManager extends SQLiteOpenHelper {
    public interface Migration {
        void upgrade(SQLiteDatabase arg1);
    }

    private static final String CREATE_CONTEXTS_SQL_V1 = "CREATE TABLE transport_contexts (_id INTEGER PRIMARY KEY, backend_name TEXT NOT NULL, priority INTEGER NOT NULL, next_request_ms INTEGER NOT NULL)";
    private static final String CREATE_CONTEXT_BACKEND_PRIORITY_INDEX_V1 = "CREATE UNIQUE INDEX contexts_backend_priority on transport_contexts(backend_name, priority)";
    private static final String CREATE_EVENTS_SQL_V1 = "CREATE TABLE events (_id INTEGER PRIMARY KEY, context_id INTEGER NOT NULL, transport_name TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, uptime_ms INTEGER NOT NULL, payload BLOB NOT NULL, code INTEGER, num_attempts INTEGER NOT NULL,FOREIGN KEY (context_id) REFERENCES transport_contexts(_id) ON DELETE CASCADE)";
    private static final String CREATE_EVENT_BACKEND_INDEX_V1 = "CREATE INDEX events_backend_id on events(context_id)";
    private static final String CREATE_EVENT_METADATA_SQL_V1 = "CREATE TABLE event_metadata (_id INTEGER PRIMARY KEY, event_id INTEGER NOT NULL, name TEXT NOT NULL, value TEXT NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE)";
    private static final String CREATE_GLOBAL_LOG_EVENT_STATE_TABLE = "CREATE TABLE global_log_event_state (last_metrics_upload_ms BIGINT PRIMARY KEY)";
    private static final String CREATE_INITIAL_GLOBAL_LOG_EVENT_STATE_VALUE_SQL = null;
    private static final String CREATE_LOG_EVENT_DROPPED_TABLE = "CREATE TABLE log_event_dropped (log_source VARCHAR(45) NOT NULL,reason INTEGER NOT NULL,events_dropped_count BIGINT NOT NULL,PRIMARY KEY(log_source, reason))";
    private static final String CREATE_PAYLOADS_TABLE_V4 = "CREATE TABLE event_payloads (sequence_num INTEGER NOT NULL, event_id INTEGER NOT NULL, bytes BLOB NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE,PRIMARY KEY (sequence_num, event_id))";
    static final String DB_NAME = "com.google.android.datatransport.events";
    private static final String DROP_CONTEXTS_SQL = "DROP TABLE transport_contexts";
    private static final String DROP_EVENTS_SQL = "DROP TABLE events";
    private static final String DROP_EVENT_METADATA_SQL = "DROP TABLE event_metadata";
    private static final String DROP_GLOBAL_LOG_EVENT_STATE_SQL = "DROP TABLE IF EXISTS global_log_event_state";
    private static final String DROP_LOG_EVENT_DROPPED_SQL = "DROP TABLE IF EXISTS log_event_dropped";
    private static final String DROP_PAYLOADS_SQL = "DROP TABLE IF EXISTS event_payloads";
    private static final List INCREMENTAL_MIGRATIONS;
    private static final Migration MIGRATE_TO_V1;
    private static final Migration MIGRATE_TO_V2;
    private static final Migration MIGRATE_TO_V3;
    private static final Migration MIGRATE_TO_V4;
    private static final Migration MIGRATION_TO_V5;
    static int SCHEMA_VERSION;
    private boolean configured;
    private final int schemaVersion;

    static {
        SchemaManager.CREATE_INITIAL_GLOBAL_LOG_EVENT_STATE_VALUE_SQL = "INSERT INTO global_log_event_state VALUES (" + System.currentTimeMillis() + ")";
        SchemaManager.SCHEMA_VERSION = 5;
        SchemaManager..ExternalSyntheticLambda0 schemaManager$$ExternalSyntheticLambda00 = (SQLiteDatabase sQLiteDatabase0) -> {
            sQLiteDatabase0.execSQL("CREATE TABLE events (_id INTEGER PRIMARY KEY, context_id INTEGER NOT NULL, transport_name TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, uptime_ms INTEGER NOT NULL, payload BLOB NOT NULL, code INTEGER, num_attempts INTEGER NOT NULL,FOREIGN KEY (context_id) REFERENCES transport_contexts(_id) ON DELETE CASCADE)");
            sQLiteDatabase0.execSQL("CREATE TABLE event_metadata (_id INTEGER PRIMARY KEY, event_id INTEGER NOT NULL, name TEXT NOT NULL, value TEXT NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE)");
            sQLiteDatabase0.execSQL("CREATE TABLE transport_contexts (_id INTEGER PRIMARY KEY, backend_name TEXT NOT NULL, priority INTEGER NOT NULL, next_request_ms INTEGER NOT NULL)");
            sQLiteDatabase0.execSQL("CREATE INDEX events_backend_id on events(context_id)");
            sQLiteDatabase0.execSQL("CREATE UNIQUE INDEX contexts_backend_priority on transport_contexts(backend_name, priority)");
        };
        SchemaManager.MIGRATE_TO_V1 = schemaManager$$ExternalSyntheticLambda00;
        SchemaManager..ExternalSyntheticLambda1 schemaManager$$ExternalSyntheticLambda10 = (SQLiteDatabase sQLiteDatabase0) -> {
            sQLiteDatabase0.execSQL("ALTER TABLE transport_contexts ADD COLUMN extras BLOB");
            sQLiteDatabase0.execSQL("CREATE UNIQUE INDEX contexts_backend_priority_extras on transport_contexts(backend_name, priority, extras)");
            sQLiteDatabase0.execSQL("DROP INDEX contexts_backend_priority");
        };
        SchemaManager.MIGRATE_TO_V2 = schemaManager$$ExternalSyntheticLambda10;
        SchemaManager..ExternalSyntheticLambda2 schemaManager$$ExternalSyntheticLambda20 = (SQLiteDatabase sQLiteDatabase0) -> sQLiteDatabase0.execSQL("ALTER TABLE events ADD COLUMN payload_encoding TEXT");
        SchemaManager.MIGRATE_TO_V3 = schemaManager$$ExternalSyntheticLambda20;
        SchemaManager..ExternalSyntheticLambda3 schemaManager$$ExternalSyntheticLambda30 = (SQLiteDatabase sQLiteDatabase0) -> {
            sQLiteDatabase0.execSQL("ALTER TABLE events ADD COLUMN inline BOOLEAN NOT NULL DEFAULT 1");
            sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS event_payloads");
            sQLiteDatabase0.execSQL("CREATE TABLE event_payloads (sequence_num INTEGER NOT NULL, event_id INTEGER NOT NULL, bytes BLOB NOT NULL,FOREIGN KEY (event_id) REFERENCES events(_id) ON DELETE CASCADE,PRIMARY KEY (sequence_num, event_id))");
        };
        SchemaManager.MIGRATE_TO_V4 = schemaManager$$ExternalSyntheticLambda30;
        SchemaManager..ExternalSyntheticLambda4 schemaManager$$ExternalSyntheticLambda40 = (SQLiteDatabase sQLiteDatabase0) -> {
            sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS log_event_dropped");
            sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS global_log_event_state");
            sQLiteDatabase0.execSQL("CREATE TABLE log_event_dropped (log_source VARCHAR(45) NOT NULL,reason INTEGER NOT NULL,events_dropped_count BIGINT NOT NULL,PRIMARY KEY(log_source, reason))");
            sQLiteDatabase0.execSQL("CREATE TABLE global_log_event_state (last_metrics_upload_ms BIGINT PRIMARY KEY)");
            sQLiteDatabase0.execSQL("INSERT INTO global_log_event_state VALUES (1757104737219)");
        };
        SchemaManager.MIGRATION_TO_V5 = schemaManager$$ExternalSyntheticLambda40;
        SchemaManager.INCREMENTAL_MIGRATIONS = Arrays.asList(new Migration[]{schemaManager$$ExternalSyntheticLambda00, schemaManager$$ExternalSyntheticLambda10, schemaManager$$ExternalSyntheticLambda20, schemaManager$$ExternalSyntheticLambda30, schemaManager$$ExternalSyntheticLambda40});
    }

    @Inject
    SchemaManager(Context context0, @Named("SQLITE_DB_NAME") String s, @Named("SCHEMA_VERSION") int v) {
        super(context0, s, null, v);
        this.configured = false;
        this.schemaVersion = v;
    }

    private void ensureConfigured(SQLiteDatabase sQLiteDatabase0) {
        if(!this.configured) {
            this.onConfigure(sQLiteDatabase0);
        }
    }

    // 检测为 Lambda 实现
    static void lambda$static$0(SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static void lambda$static$1(SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static void lambda$static$2(SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static void lambda$static$3(SQLiteDatabase sQLiteDatabase0) [...]

    // 检测为 Lambda 实现
    static void lambda$static$4(SQLiteDatabase sQLiteDatabase0) [...]

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public void onConfigure(SQLiteDatabase sQLiteDatabase0) {
        this.configured = true;
        sQLiteDatabase0.rawQuery("PRAGMA busy_timeout=0;", new String[0]).close();
        sQLiteDatabase0.setForeignKeyConstraintsEnabled(true);
    }

    private void onCreate(SQLiteDatabase sQLiteDatabase0, int v) {
        this.ensureConfigured(sQLiteDatabase0);
        this.upgrade(sQLiteDatabase0, 0, v);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase0) {
        this.onCreate(sQLiteDatabase0, this.schemaVersion);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
        sQLiteDatabase0.execSQL("DROP TABLE events");
        sQLiteDatabase0.execSQL("DROP TABLE event_metadata");
        sQLiteDatabase0.execSQL("DROP TABLE transport_contexts");
        sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS event_payloads");
        sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS log_event_dropped");
        sQLiteDatabase0.execSQL("DROP TABLE IF EXISTS global_log_event_state");
        this.onCreate(sQLiteDatabase0, v1);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase0) {
        this.ensureConfigured(sQLiteDatabase0);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
        this.ensureConfigured(sQLiteDatabase0);
        this.upgrade(sQLiteDatabase0, v, v1);
    }

    private void upgrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
        List list0 = SchemaManager.INCREMENTAL_MIGRATIONS;
        if(v1 > list0.size()) {
            throw new IllegalArgumentException("Migration from " + v + " to " + v1 + " was requested, but cannot be performed. Only " + list0.size() + " migrations are provided");
        }
        while(v < v1) {
            ((Migration)SchemaManager.INCREMENTAL_MIGRATIONS.get(v)).upgrade(sQLiteDatabase0);
            ++v;
        }
    }
}

