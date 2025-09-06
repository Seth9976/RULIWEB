package androidx.work.impl;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration.Builder;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.work.Clock;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H&J\b\u0010\u0005\u001A\u00020\u0006H&J\b\u0010\u0007\u001A\u00020\bH&J\b\u0010\t\u001A\u00020\nH&J\b\u0010\u000B\u001A\u00020\fH&J\b\u0010\r\u001A\u00020\u000EH&J\b\u0010\u000F\u001A\u00020\u0010H&J\b\u0010\u0011\u001A\u00020\u0012H&¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/WorkDatabase;", "Landroidx/room/RoomDatabase;", "()V", "dependencyDao", "Landroidx/work/impl/model/DependencyDao;", "preferenceDao", "Landroidx/work/impl/model/PreferenceDao;", "rawWorkInfoDao", "Landroidx/work/impl/model/RawWorkInfoDao;", "systemIdInfoDao", "Landroidx/work/impl/model/SystemIdInfoDao;", "workNameDao", "Landroidx/work/impl/model/WorkNameDao;", "workProgressDao", "Landroidx/work/impl/model/WorkProgressDao;", "workSpecDao", "Landroidx/work/impl/model/WorkSpecDao;", "workTagDao", "Landroidx/work/impl/model/WorkTagDao;", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class WorkDatabase extends RoomDatabase {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0007¨\u0006\r"}, d2 = {"Landroidx/work/impl/WorkDatabase$Companion;", "", "()V", "create", "Landroidx/work/impl/WorkDatabase;", "context", "Landroid/content/Context;", "queryExecutor", "Ljava/util/concurrent/Executor;", "clock", "Landroidx/work/Clock;", "useTestDatabase", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        // 检测为 Lambda 实现
        public static SupportSQLiteOpenHelper $r8$lambda$d9uKx9JAMiDvrmcMenix4D379lU(Context context0, Configuration supportSQLiteOpenHelper$Configuration0) [...]

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final WorkDatabase create(Context context0, Executor executor0, Clock clock0, boolean z) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
            Intrinsics.checkNotNullParameter(clock0, "clock");
            return z ? ((WorkDatabase)Room.inMemoryDatabaseBuilder(context0, WorkDatabase.class).allowMainThreadQueries().setQueryExecutor(executor0).addCallback(new CleanupCallback(clock0)).addMigrations(new Migration[]{Migration_1_2.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(context0, 2, 3)}).addMigrations(new Migration[]{Migration_3_4.INSTANCE}).addMigrations(new Migration[]{Migration_4_5.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(context0, 5, 6)}).addMigrations(new Migration[]{Migration_6_7.INSTANCE}).addMigrations(new Migration[]{Migration_7_8.INSTANCE}).addMigrations(new Migration[]{Migration_8_9.INSTANCE}).addMigrations(new Migration[]{new WorkMigration9To10(context0)}).addMigrations(new Migration[]{new RescheduleMigration(context0, 10, 11)}).addMigrations(new Migration[]{Migration_11_12.INSTANCE}).addMigrations(new Migration[]{Migration_12_13.INSTANCE}).addMigrations(new Migration[]{Migration_15_16.INSTANCE}).addMigrations(new Migration[]{Migration_16_17.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(context0, 21, 22)}).fallbackToDestructiveMigration().build()) : ((WorkDatabase)Room.databaseBuilder(context0, WorkDatabase.class, "androidx.work.workdb").openHelperFactory((Configuration supportSQLiteOpenHelper$Configuration0) -> Companion.create$lambda$0(context0, supportSQLiteOpenHelper$Configuration0)).setQueryExecutor(executor0).addCallback(new CleanupCallback(clock0)).addMigrations(new Migration[]{Migration_1_2.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(context0, 2, 3)}).addMigrations(new Migration[]{Migration_3_4.INSTANCE}).addMigrations(new Migration[]{Migration_4_5.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(context0, 5, 6)}).addMigrations(new Migration[]{Migration_6_7.INSTANCE}).addMigrations(new Migration[]{Migration_7_8.INSTANCE}).addMigrations(new Migration[]{Migration_8_9.INSTANCE}).addMigrations(new Migration[]{new WorkMigration9To10(context0)}).addMigrations(new Migration[]{new RescheduleMigration(context0, 10, 11)}).addMigrations(new Migration[]{Migration_11_12.INSTANCE}).addMigrations(new Migration[]{Migration_12_13.INSTANCE}).addMigrations(new Migration[]{Migration_15_16.INSTANCE}).addMigrations(new Migration[]{Migration_16_17.INSTANCE}).addMigrations(new Migration[]{new RescheduleMigration(context0, 21, 22)}).fallbackToDestructiveMigration().build());
        }

        private static final SupportSQLiteOpenHelper create$lambda$0(Context context0, Configuration supportSQLiteOpenHelper$Configuration0) {
            Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Configuration0, "configuration");
            Builder supportSQLiteOpenHelper$Configuration$Builder0 = Configuration.Companion.builder(context0);
            supportSQLiteOpenHelper$Configuration$Builder0.name(supportSQLiteOpenHelper$Configuration0.name).callback(supportSQLiteOpenHelper$Configuration0.callback).noBackupDirectory(true).allowDataLossOnRecovery(true);
            return new FrameworkSQLiteOpenHelperFactory().create(supportSQLiteOpenHelper$Configuration$Builder0.build());
        }
    }

    public static final Companion Companion;

    static {
        WorkDatabase.Companion = new Companion(null);
    }

    @JvmStatic
    public static final WorkDatabase create(Context context0, Executor executor0, Clock clock0, boolean z) {
        return WorkDatabase.Companion.create(context0, executor0, clock0, z);
    }

    public abstract DependencyDao dependencyDao();

    public abstract PreferenceDao preferenceDao();

    public abstract RawWorkInfoDao rawWorkInfoDao();

    public abstract SystemIdInfoDao systemIdInfoDao();

    public abstract WorkNameDao workNameDao();

    public abstract WorkProgressDao workProgressDao();

    public abstract WorkSpecDao workSpecDao();

    public abstract WorkTagDao workTagDao();
}

