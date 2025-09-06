package androidx.work.impl;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

final class WorkDatabase_AutoMigration_20_21_Impl extends Migration {
    public WorkDatabase_AutoMigration_20_21_Impl() {
        super(20, 21);
    }

    @Override  // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase0) {
        supportSQLiteDatabase0.execSQL("ALTER TABLE `WorkSpec` ADD COLUMN `required_network_request` BLOB NOT NULL DEFAULT x\'\'");
    }
}

