package androidx.room;

import android.content.Context;
import android.content.Intent;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Factory;
import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001Bi\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u00A2\u0006\u0002\u0010\u0017B\u0081\u0001\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0018\u001A\u00020\u0012\u0012\u0006\u0010\u0019\u001A\u00020\u000E\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u0006\u0010\u001A\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u00A2\u0006\u0002\u0010\u001BB\u0095\u0001\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0018\u001A\u00020\u0012\u0012\u0006\u0010\u0019\u001A\u00020\u000E\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u0006\u0010\u001A\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\b\u0010\u001C\u001A\u0004\u0018\u00010\u0005\u0012\b\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u00A2\u0006\u0002\u0010\u001FB\u00A5\u0001\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0018\u001A\u00020\u0012\u0012\u0006\u0010\u0019\u001A\u00020\u000E\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u0006\u0010\u001A\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\b\u0010\u001C\u001A\u0004\u0018\u00010\u0005\u0012\b\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u0012\u000E\u0010 \u001A\n\u0012\u0004\u0012\u00020\"\u0018\u00010!\u00A2\u0006\u0002\u0010#B\u00AF\u0001\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0018\u001A\u00020\u0012\u0012\u0006\u0010\u0019\u001A\u00020\u000E\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u0006\u0010\u001A\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\b\u0010\u001C\u001A\u0004\u0018\u00010\u0005\u0012\b\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u0012\u000E\u0010 \u001A\n\u0012\u0004\u0012\u00020\"\u0018\u00010!\u0012\b\u0010$\u001A\u0004\u0018\u00010%\u00A2\u0006\u0002\u0010&B\u00BD\u0001\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0018\u001A\u00020\u0012\u0012\u0006\u0010\u0019\u001A\u00020\u000E\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u0006\u0010\u001A\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\b\u0010\u001C\u001A\u0004\u0018\u00010\u0005\u0012\b\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u0012\u000E\u0010 \u001A\n\u0012\u0004\u0012\u00020\"\u0018\u00010!\u0012\b\u0010$\u001A\u0004\u0018\u00010%\u0012\f\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00010\u000B\u00A2\u0006\u0002\u0010(B\u00CB\u0001\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0018\u001A\u00020\u0012\u0012\u0006\u0010\u0019\u001A\u00020\u000E\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u0006\u0010\u001A\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\b\u0010\u001C\u001A\u0004\u0018\u00010\u0005\u0012\b\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u0012\u000E\u0010 \u001A\n\u0012\u0004\u0012\u00020\"\u0018\u00010!\u0012\b\u0010$\u001A\u0004\u0018\u00010%\u0012\f\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\f\u0010)\u001A\b\u0012\u0004\u0012\u00020*0\u000B\u00A2\u0006\u0002\u0010+B\u00CD\u0001\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u000E\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u0012\u0012\u0006\u0010\u0018\u001A\u00020\u0012\u0012\b\u0010,\u001A\u0004\u0018\u00010-\u0012\u0006\u0010\u0013\u001A\u00020\u000E\u0012\u0006\u0010\u001A\u001A\u00020\u000E\u0012\u000E\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015\u0012\b\u0010\u001C\u001A\u0004\u0018\u00010\u0005\u0012\b\u0010\u001D\u001A\u0004\u0018\u00010\u001E\u0012\u000E\u0010 \u001A\n\u0012\u0004\u0012\u00020\"\u0018\u00010!\u0012\b\u0010$\u001A\u0004\u0018\u00010%\u0012\f\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00010\u000B\u0012\f\u0010)\u001A\b\u0012\u0004\u0012\u00020*0\u000B\u00A2\u0006\u0002\u0010.J\u0018\u0010/\u001A\u00020\u000E2\u0006\u00100\u001A\u00020\u00162\u0006\u00101\u001A\u00020\u0016H\u0016J\u0010\u00102\u001A\u00020\u000E2\u0006\u00103\u001A\u00020\u0016H\u0017R\u0010\u0010\u001A\u001A\u00020\u000E8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u00020\u000E8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010)\u001A\b\u0012\u0004\u0012\u00020*0\u000B8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0018\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u001C\u001A\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u001D\u001A\u0004\u0018\u00010\u001E8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0018\u0010 \u001A\n\u0012\u0004\u0012\u00020\"\u0018\u00010!8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u00020\u00108\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00020\t8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001A\u00020\u000E8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010,\u001A\u0004\u0018\u00010-8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001A\u0004\u0018\u00010\u00058\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010$\u001A\u0004\u0018\u00010%8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001A\u00020\u00128\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001A\u00020\u000E8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00078\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001A\u00020\u00128\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010\'\u001A\b\u0012\u0004\u0012\u00020\u00010\u000B8\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u00064"}, d2 = {"Landroidx/room/DatabaseConfiguration;", "", "context", "Landroid/content/Context;", "name", "", "sqliteOpenHelperFactory", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "migrationContainer", "Landroidx/room/RoomDatabase$MigrationContainer;", "callbacks", "", "Landroidx/room/RoomDatabase$Callback;", "allowMainThreadQueries", "", "journalMode", "Landroidx/room/RoomDatabase$JournalMode;", "queryExecutor", "Ljava/util/concurrent/Executor;", "requireMigration", "migrationNotRequiredFrom", "", "", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;ZLjava/util/Set;)V", "transactionExecutor", "multiInstanceInvalidation", "allowDestructiveMigrationOnDowngrade", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZZLjava/util/Set;)V", "copyFromAssetPath", "copyFromFile", "Ljava/io/File;", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;)V", "copyFromInputStream", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;)V", "prepackagedDatabaseCallback", "Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;)V", "typeConverters", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;Ljava/util/List;)V", "autoMigrationSpecs", "Landroidx/room/migration/AutoMigrationSpec;", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;ZZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;Ljava/util/List;Ljava/util/List;)V", "multiInstanceInvalidationServiceIntent", "Landroid/content/Intent;", "(Landroid/content/Context;Ljava/lang/String;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;Landroidx/room/RoomDatabase$MigrationContainer;Ljava/util/List;ZLandroidx/room/RoomDatabase$JournalMode;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Landroid/content/Intent;ZZLjava/util/Set;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;Ljava/util/List;Ljava/util/List;)V", "isMigrationRequired", "fromVersion", "toVersion", "isMigrationRequiredFrom", "version", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class DatabaseConfiguration {
    public final boolean allowDestructiveMigrationOnDowngrade;
    public final boolean allowMainThreadQueries;
    public final List autoMigrationSpecs;
    public final List callbacks;
    public final Context context;
    public final String copyFromAssetPath;
    public final File copyFromFile;
    public final Callable copyFromInputStream;
    public final JournalMode journalMode;
    public final MigrationContainer migrationContainer;
    private final Set migrationNotRequiredFrom;
    public final boolean multiInstanceInvalidation;
    public final Intent multiInstanceInvalidationServiceIntent;
    public final String name;
    public final PrepackagedDatabaseCallback prepackagedDatabaseCallback;
    public final Executor queryExecutor;
    public final boolean requireMigration;
    public final Factory sqliteOpenHelperFactory;
    public final Executor transactionExecutor;
    public final List typeConverters;

    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, Executor executor1, Intent intent0, boolean z1, boolean z2, Set set0, String s1, File file0, Callable callable0, PrepackagedDatabaseCallback roomDatabase$PrepackagedDatabaseCallback0, List list1, List list2) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor1, "transactionExecutor");
        Intrinsics.checkNotNullParameter(list1, "typeConverters");
        Intrinsics.checkNotNullParameter(list2, "autoMigrationSpecs");
        super();
        this.context = context0;
        this.name = s;
        this.sqliteOpenHelperFactory = supportSQLiteOpenHelper$Factory0;
        this.migrationContainer = roomDatabase$MigrationContainer0;
        this.callbacks = list0;
        this.allowMainThreadQueries = z;
        this.journalMode = roomDatabase$JournalMode0;
        this.queryExecutor = executor0;
        this.transactionExecutor = executor1;
        this.multiInstanceInvalidationServiceIntent = intent0;
        this.requireMigration = z1;
        this.allowDestructiveMigrationOnDowngrade = z2;
        this.migrationNotRequiredFrom = set0;
        this.copyFromAssetPath = s1;
        this.copyFromFile = file0;
        this.copyFromInputStream = callable0;
        this.prepackagedDatabaseCallback = roomDatabase$PrepackagedDatabaseCallback0;
        this.typeConverters = list1;
        this.autoMigrationSpecs = list2;
        this.multiInstanceInvalidation = intent0 != null;
    }

    @Deprecated(message = "This constructor is deprecated.", replaceWith = @ReplaceWith(expression = "DatabaseConfiguration(Context, String, SupportSQLiteOpenHelper.Factory, RoomDatabase.MigrationContainer, List, boolean, RoomDatabase.JournalMode, Executor, Executor, Intent, boolean, boolean, Set, String, File, Callable, RoomDatabase.PrepackagedDatabaseCallback, List, List)", imports = {}))
    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, Executor executor1, boolean z1, boolean z2, boolean z3, Set set0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor1, "transactionExecutor");
        this(context0, s, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor0, executor1, (z1 ? new Intent(context0, MultiInstanceInvalidationService.class) : null), z2, z3, set0, null, null, null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList());
    }

    @Deprecated(message = "This constructor is deprecated.", replaceWith = @ReplaceWith(expression = "DatabaseConfiguration(Context, String, SupportSQLiteOpenHelper.Factory, RoomDatabase.MigrationContainer, List, boolean, RoomDatabase.JournalMode, Executor, Executor, Intent, boolean, boolean, Set, String, File, Callable, RoomDatabase.PrepackagedDatabaseCallback, List, List)", imports = {}))
    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, Executor executor1, boolean z1, boolean z2, boolean z3, Set set0, String s1, File file0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor1, "transactionExecutor");
        this(context0, s, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor0, executor1, (z1 ? new Intent(context0, MultiInstanceInvalidationService.class) : null), z2, z3, set0, s1, file0, null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList());
    }

    @Deprecated(message = "This constructor is deprecated.", replaceWith = @ReplaceWith(expression = "DatabaseConfiguration(Context, String, SupportSQLiteOpenHelper.Factory, RoomDatabase.MigrationContainer, List, boolean, RoomDatabase.JournalMode, Executor, Executor, Intent, boolean, boolean, Set, String, File, Callable, RoomDatabase.PrepackagedDatabaseCallback, List, List)", imports = {}))
    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, Executor executor1, boolean z1, boolean z2, boolean z3, Set set0, String s1, File file0, Callable callable0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor1, "transactionExecutor");
        this(context0, s, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor0, executor1, (z1 ? new Intent(context0, MultiInstanceInvalidationService.class) : null), z2, z3, set0, s1, file0, callable0, null, CollectionsKt.emptyList(), CollectionsKt.emptyList());
    }

    @Deprecated(message = "This constructor is deprecated.", replaceWith = @ReplaceWith(expression = "DatabaseConfiguration(Context, String, SupportSQLiteOpenHelper.Factory, RoomDatabase.MigrationContainer, List, boolean, RoomDatabase.JournalMode, Executor, Executor, Intent, boolean, boolean, Set, String, File, Callable, RoomDatabase.PrepackagedDatabaseCallback, List, List)", imports = {}))
    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, Executor executor1, boolean z1, boolean z2, boolean z3, Set set0, String s1, File file0, Callable callable0, PrepackagedDatabaseCallback roomDatabase$PrepackagedDatabaseCallback0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor1, "transactionExecutor");
        this(context0, s, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor0, executor1, (z1 ? new Intent(context0, MultiInstanceInvalidationService.class) : null), z2, z3, set0, s1, file0, callable0, roomDatabase$PrepackagedDatabaseCallback0, CollectionsKt.emptyList(), CollectionsKt.emptyList());
    }

    @Deprecated(message = "This constructor is deprecated.", replaceWith = @ReplaceWith(expression = "DatabaseConfiguration(Context, String, SupportSQLiteOpenHelper.Factory, RoomDatabase.MigrationContainer, List, boolean, RoomDatabase.JournalMode, Executor, Executor, Intent, boolean, boolean, Set, String, File, Callable, RoomDatabase.PrepackagedDatabaseCallback, List, List)", imports = {}))
    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, Executor executor1, boolean z1, boolean z2, boolean z3, Set set0, String s1, File file0, Callable callable0, PrepackagedDatabaseCallback roomDatabase$PrepackagedDatabaseCallback0, List list1) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor1, "transactionExecutor");
        Intrinsics.checkNotNullParameter(list1, "typeConverters");
        this(context0, s, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor0, executor1, (z1 ? new Intent(context0, MultiInstanceInvalidationService.class) : null), z2, z3, set0, s1, file0, callable0, roomDatabase$PrepackagedDatabaseCallback0, list1, CollectionsKt.emptyList());
    }

    @Deprecated(message = "This constructor is deprecated.", replaceWith = @ReplaceWith(expression = "DatabaseConfiguration(Context, String, SupportSQLiteOpenHelper.Factory, RoomDatabase.MigrationContainer, List, boolean, RoomDatabase.JournalMode, Executor, Executor, Intent, boolean, boolean, Set, String, File, Callable, RoomDatabase.PrepackagedDatabaseCallback, List, List)", imports = {}))
    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, Executor executor1, boolean z1, boolean z2, boolean z3, Set set0, String s1, File file0, Callable callable0, PrepackagedDatabaseCallback roomDatabase$PrepackagedDatabaseCallback0, List list1, List list2) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        Intrinsics.checkNotNullParameter(executor1, "transactionExecutor");
        Intrinsics.checkNotNullParameter(list1, "typeConverters");
        Intrinsics.checkNotNullParameter(list2, "autoMigrationSpecs");
        this(context0, s, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor0, executor1, (z1 ? new Intent(context0, MultiInstanceInvalidationService.class) : null), z2, z3, set0, s1, file0, callable0, null, list1, list2);
    }

    @Deprecated(message = "This constructor is deprecated.", replaceWith = @ReplaceWith(expression = "DatabaseConfiguration(Context, String, SupportSQLiteOpenHelper.Factory, RoomDatabase.MigrationContainer, List, boolean, RoomDatabase.JournalMode, Executor, Executor, Intent, boolean, boolean, Set, String, File, Callable, RoomDatabase.PrepackagedDatabaseCallback, List, List)", imports = {}))
    public DatabaseConfiguration(Context context0, String s, Factory supportSQLiteOpenHelper$Factory0, MigrationContainer roomDatabase$MigrationContainer0, List list0, boolean z, JournalMode roomDatabase$JournalMode0, Executor executor0, boolean z1, Set set0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "sqliteOpenHelperFactory");
        Intrinsics.checkNotNullParameter(roomDatabase$MigrationContainer0, "migrationContainer");
        Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
        Intrinsics.checkNotNullParameter(executor0, "queryExecutor");
        this(context0, s, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor0, executor0, null, z1, false, set0, null, null, null, null, CollectionsKt.emptyList(), CollectionsKt.emptyList());
    }

    // 去混淆评级： 低(40)
    public boolean isMigrationRequired(int v, int v1) {
        return v <= v1 || !this.allowDestructiveMigrationOnDowngrade ? this.requireMigration && (this.migrationNotRequiredFrom == null || !this.migrationNotRequiredFrom.contains(v)) : false;
    }

    @Deprecated(message = "Use [isMigrationRequired(int, int)] which takes\n      [allowDestructiveMigrationOnDowngrade] into account.", replaceWith = @ReplaceWith(expression = "isMigrationRequired(version, version + 1)", imports = {}))
    public boolean isMigrationRequiredFrom(int v) {
        return this.isMigrationRequired(v, v + 1);
    }
}

