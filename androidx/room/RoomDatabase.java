package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteCompat.Api19Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Factory;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u00C4\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b&\u0018\u0000 q2\u00020\u0001:\u0007opqrstuB\u0005\u00A2\u0006\u0002\u0010\u0002J\b\u0010;\u001A\u00020<H\u0017J\b\u0010=\u001A\u00020<H\u0017J\b\u0010>\u001A\u00020<H\u0017J\b\u0010?\u001A\u00020<H\'J\b\u0010@\u001A\u00020<H\u0016J\u0010\u0010A\u001A\u00020B2\u0006\u0010C\u001A\u00020\u0011H\u0016J\b\u0010D\u001A\u00020\u0019H$J\u0010\u0010E\u001A\u00020\u00142\u0006\u0010F\u001A\u00020GH$J\b\u0010H\u001A\u00020<H\u0017J*\u0010I\u001A\b\u0012\u0004\u0012\u00020J0$2\u001A\u0010\u000B\u001A\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\n0KH\u0017J\r\u0010L\u001A\u00020MH\u0000\u00A2\u0006\u0002\bNJ\u0016\u0010O\u001A\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\n0\t0PH\u0017J\"\u0010Q\u001A\u001C\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u000E\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0$0KH\u0015J#\u0010R\u001A\u0004\u0018\u0001HS\"\u0004\b\u0000\u0010S2\f\u0010T\u001A\b\u0012\u0004\u0012\u0002HS0\tH\u0016\u00A2\u0006\u0002\u0010UJ\b\u0010V\u001A\u00020\u0004H\u0016J\u0010\u0010W\u001A\u00020<2\u0006\u0010X\u001A\u00020GH\u0017J\b\u0010Y\u001A\u00020<H\u0002J\b\u0010Z\u001A\u00020<H\u0002J\u0010\u0010[\u001A\u00020<2\u0006\u0010\\\u001A\u00020(H\u0014J\u001C\u0010]\u001A\u00020^2\u0006\u0010]\u001A\u00020_2\n\b\u0002\u0010`\u001A\u0004\u0018\u00010aH\u0017J)\u0010]\u001A\u00020^2\u0006\u0010]\u001A\u00020\u00112\u0012\u0010b\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010cH\u0016\u00A2\u0006\u0002\u0010dJ\u0010\u0010e\u001A\u00020<2\u0006\u0010f\u001A\u00020gH\u0016J!\u0010e\u001A\u0002Hh\"\u0004\b\u0000\u0010h2\f\u0010f\u001A\b\u0012\u0004\u0012\u0002Hh0iH\u0016\u00A2\u0006\u0002\u0010jJ\b\u0010k\u001A\u00020<H\u0017J+\u0010l\u001A\u0004\u0018\u0001HS\"\u0004\b\u0000\u0010S2\f\u0010m\u001A\b\u0012\u0004\u0012\u0002HS0\t2\u0006\u0010*\u001A\u00020\u0014H\u0002\u00A2\u0006\u0002\u0010nR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000RN\u0010\u000B\u001A\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\n0\b2\u001A\u0010\u0007\u001A\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\n0\b8E@EX\u0084\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\f\u0010\r\"\u0004\b\u000E\u0010\u000FR\u001F\u0010\u0010\u001A\u000E\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\b8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\rR\u000E\u0010\u0013\u001A\u00020\u0014X\u0082.\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0016X\u0082.\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0017\u001A\u00020\u0016X\u0082.\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001A\u00020\u0019X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001BR\u0014\u0010\u001C\u001A\u00020\u00048@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001D\u0010\u001ER\u001A\u0010\u001F\u001A\u00020\u00048VX\u0096\u0004\u00A2\u0006\f\u0012\u0004\b \u0010\u0002\u001A\u0004\b\u001F\u0010\u001ER\u0017\u0010!\u001A\u00020\u00048G\u00A2\u0006\f\u0012\u0004\b\"\u0010\u0002\u001A\u0004\b!\u0010\u001ER \u0010#\u001A\n\u0012\u0004\u0012\u00020%\u0018\u00010$8\u0004@\u0004X\u0085\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b&\u0010\u0002R\u001A\u0010\'\u001A\u0004\u0018\u00010(8\u0004@\u0004X\u0085\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b)\u0010\u0002R\u0014\u0010*\u001A\u00020\u00148VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b+\u0010,R\u0014\u0010-\u001A\u00020\u00168VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b.\u0010/R\u000E\u00100\u001A\u000201X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0019\u00102\u001A\b\u0012\u0004\u0012\u000204038G\u00A2\u0006\b\n\u0000\u001A\u0004\b5\u00106R\u0014\u00107\u001A\u00020\u00168VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b8\u0010/R\u001E\u00109\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t\u0012\u0004\u0012\u00020\u00010\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010:\u001A\u00020\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006v"}, d2 = {"Landroidx/room/RoomDatabase;", "", "()V", "allowMainThreadQueries", "", "autoCloser", "Landroidx/room/AutoCloser;", "<set-?>", "", "Ljava/lang/Class;", "Landroidx/room/migration/AutoMigrationSpec;", "autoMigrationSpecs", "getAutoMigrationSpecs", "()Ljava/util/Map;", "setAutoMigrationSpecs", "(Ljava/util/Map;)V", "backingFieldMap", "", "getBackingFieldMap", "internalOpenHelper", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "internalQueryExecutor", "Ljava/util/concurrent/Executor;", "internalTransactionExecutor", "invalidationTracker", "Landroidx/room/InvalidationTracker;", "getInvalidationTracker", "()Landroidx/room/InvalidationTracker;", "isMainThread", "isMainThread$room_runtime_release", "()Z", "isOpen", "isOpen$annotations", "isOpenInternal", "isOpenInternal$annotations", "mCallbacks", "", "Landroidx/room/RoomDatabase$Callback;", "getMCallbacks$annotations", "mDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getMDatabase$annotations", "openHelper", "getOpenHelper", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "queryExecutor", "getQueryExecutor", "()Ljava/util/concurrent/Executor;", "readWriteLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "suspendingTransactionId", "Ljava/lang/ThreadLocal;", "", "getSuspendingTransactionId", "()Ljava/lang/ThreadLocal;", "transactionExecutor", "getTransactionExecutor", "typeConverters", "writeAheadLoggingEnabled", "assertNotMainThread", "", "assertNotSuspendingTransaction", "beginTransaction", "clearAllTables", "close", "compileStatement", "Landroidx/sqlite/db/SupportSQLiteStatement;", "sql", "createInvalidationTracker", "createOpenHelper", "config", "Landroidx/room/DatabaseConfiguration;", "endTransaction", "getAutoMigrations", "Landroidx/room/migration/Migration;", "", "getCloseLock", "Ljava/util/concurrent/locks/Lock;", "getCloseLock$room_runtime_release", "getRequiredAutoMigrationSpecs", "", "getRequiredTypeConverters", "getTypeConverter", "T", "klass", "(Ljava/lang/Class;)Ljava/lang/Object;", "inTransaction", "init", "configuration", "internalBeginTransaction", "internalEndTransaction", "internalInitInvalidationTracker", "db", "query", "Landroid/database/Cursor;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "signal", "Landroid/os/CancellationSignal;", "args", "", "(Ljava/lang/String;[Ljava/lang/Object;)Landroid/database/Cursor;", "runInTransaction", "body", "Ljava/lang/Runnable;", "V", "Ljava/util/concurrent/Callable;", "(Ljava/util/concurrent/Callable;)Ljava/lang/Object;", "setTransactionSuccessful", "unwrapOpenHelper", "clazz", "(Ljava/lang/Class;Landroidx/sqlite/db/SupportSQLiteOpenHelper;)Ljava/lang/Object;", "Builder", "Callback", "Companion", "JournalMode", "MigrationContainer", "PrepackagedDatabaseCallback", "QueryCallback", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class RoomDatabase {
    @Metadata(d1 = {"\u0000\u00A6\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0010\u0015\n\u0002\b\u000B\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\'\b\u0000\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\b\u0010\b\u001A\u0004\u0018\u00010\t\u00A2\u0006\u0002\u0010\nJ\u0016\u00103\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00104\u001A\u00020\u0014H\u0016J\u0016\u00105\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00106\u001A\u00020\u0016H\u0016J\'\u00107\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u00108\u001A\n\u0012\u0006\b\u0001\u0012\u00020:09\"\u00020:H\u0016\u00A2\u0006\u0002\u0010;J\u0016\u0010<\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010=\u001A\u00020\u0003H\u0016J\u000E\u0010\r\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\r\u0010>\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010?J\u0016\u0010@\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010A\u001A\u00020\tH\u0016J\u001E\u0010@\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010A\u001A\u00020\t2\u0006\u00106\u001A\u00020*H\u0017J\u0016\u0010B\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010C\u001A\u00020\u0019H\u0016J\u001E\u0010B\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010C\u001A\u00020\u00192\u0006\u00106\u001A\u00020*H\u0017J\u001C\u0010D\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010E\u001A\b\u0012\u0004\u0012\u00020\u001C0\u001BH\u0017J$\u0010D\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010E\u001A\b\u0012\u0004\u0012\u00020\u001C0\u001B2\u0006\u00106\u001A\u00020*H\u0017J\u000E\u0010F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u000E\u0010G\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u001A\u0010H\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\n\u0010I\u001A\u00020J\"\u00020%H\u0016J\u000E\u0010K\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0018\u0010L\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\u001D\u001A\u0004\u0018\u00010\u001EH\u0016J \u0010M\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0001\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u000E\u001A\u00020\u000FH\u0017J\u0016\u0010N\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001F\u001A\u00020 H\u0016J\u0016\u0010O\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010P\u001A\u00020(H\u0017J\u001E\u0010Q\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010+\u001A\u00020,2\u0006\u0010R\u001A\u00020.H\u0016J\u0016\u0010S\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010R\u001A\u00020.H\u0016J\u0016\u0010T\u001A\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010R\u001A\u00020.H\u0016R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\fX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000E\u001A\u0004\u0018\u00010\u000FX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0011X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00160\u0013X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001A\u0004\u0018\u00010\tX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001A\u0004\u0018\u00010\u0019X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u001A\u001A\n\u0012\u0004\u0012\u00020\u001C\u0018\u00010\u001BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001D\u001A\u0004\u0018\u00010\u001EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001F\u001A\u00020 X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010!\u001A\u00020\"X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010#\u001A\n\u0012\u0004\u0012\u00020%\u0018\u00010$X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010&\u001A\b\u0012\u0004\u0012\u00020%0$X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\'\u001A\u0004\u0018\u00010(X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u0004\u0018\u00010\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010)\u001A\u0004\u0018\u00010*X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010+\u001A\u0004\u0018\u00010,X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010-\u001A\u0004\u0018\u00010.X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010/\u001A\u0004\u0018\u00010.X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u00100\u001A\u00020\fX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u00101\u001A\u0004\u0018\u00010.X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u00102\u001A\b\u0012\u0004\u0012\u00020\u00030\u0013X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006U"}, d2 = {"Landroidx/room/RoomDatabase$Builder;", "T", "Landroidx/room/RoomDatabase;", "", "context", "Landroid/content/Context;", "klass", "Ljava/lang/Class;", "name", "", "(Landroid/content/Context;Ljava/lang/Class;Ljava/lang/String;)V", "allowDestructiveMigrationOnDowngrade", "", "allowMainThreadQueries", "autoCloseTimeUnit", "Ljava/util/concurrent/TimeUnit;", "autoCloseTimeout", "", "autoMigrationSpecs", "", "Landroidx/room/migration/AutoMigrationSpec;", "callbacks", "Landroidx/room/RoomDatabase$Callback;", "copyFromAssetPath", "copyFromFile", "Ljava/io/File;", "copyFromInputStream", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "factory", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "journalMode", "Landroidx/room/RoomDatabase$JournalMode;", "migrationContainer", "Landroidx/room/RoomDatabase$MigrationContainer;", "migrationStartAndEndVersions", "", "", "migrationsNotRequiredFrom", "multiInstanceInvalidationIntent", "Landroid/content/Intent;", "prepackagedDatabaseCallback", "Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "queryCallback", "Landroidx/room/RoomDatabase$QueryCallback;", "queryCallbackExecutor", "Ljava/util/concurrent/Executor;", "queryExecutor", "requireMigration", "transactionExecutor", "typeConverters", "addAutoMigrationSpec", "autoMigrationSpec", "addCallback", "callback", "addMigrations", "migrations", "", "Landroidx/room/migration/Migration;", "([Landroidx/room/migration/Migration;)Landroidx/room/RoomDatabase$Builder;", "addTypeConverter", "typeConverter", "build", "()Landroidx/room/RoomDatabase;", "createFromAsset", "databaseFilePath", "createFromFile", "databaseFile", "createFromInputStream", "inputStreamCallable", "enableMultiInstanceInvalidation", "fallbackToDestructiveMigration", "fallbackToDestructiveMigrationFrom", "startVersions", "", "fallbackToDestructiveMigrationOnDowngrade", "openHelperFactory", "setAutoCloseTimeout", "setJournalMode", "setMultiInstanceInvalidationServiceIntent", "invalidationServiceIntent", "setQueryCallback", "executor", "setQueryExecutor", "setTransactionExecutor", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class Builder {
        private boolean allowDestructiveMigrationOnDowngrade;
        private boolean allowMainThreadQueries;
        private TimeUnit autoCloseTimeUnit;
        private long autoCloseTimeout;
        private List autoMigrationSpecs;
        private final List callbacks;
        private final Context context;
        private String copyFromAssetPath;
        private File copyFromFile;
        private Callable copyFromInputStream;
        private Factory factory;
        private JournalMode journalMode;
        private final Class klass;
        private final MigrationContainer migrationContainer;
        private Set migrationStartAndEndVersions;
        private Set migrationsNotRequiredFrom;
        private Intent multiInstanceInvalidationIntent;
        private final String name;
        private PrepackagedDatabaseCallback prepackagedDatabaseCallback;
        private QueryCallback queryCallback;
        private Executor queryCallbackExecutor;
        private Executor queryExecutor;
        private boolean requireMigration;
        private Executor transactionExecutor;
        private final List typeConverters;

        public Builder(Context context0, Class class0, String s) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(class0, "klass");
            super();
            this.context = context0;
            this.klass = class0;
            this.name = s;
            this.callbacks = new ArrayList();
            this.typeConverters = new ArrayList();
            this.autoMigrationSpecs = new ArrayList();
            this.journalMode = JournalMode.AUTOMATIC;
            this.requireMigration = true;
            this.autoCloseTimeout = -1L;
            this.migrationContainer = new MigrationContainer();
            this.migrationsNotRequiredFrom = new LinkedHashSet();
        }

        public Builder addAutoMigrationSpec(AutoMigrationSpec autoMigrationSpec0) {
            Intrinsics.checkNotNullParameter(autoMigrationSpec0, "autoMigrationSpec");
            this.autoMigrationSpecs.add(autoMigrationSpec0);
            return this;
        }

        public Builder addCallback(Callback roomDatabase$Callback0) {
            Intrinsics.checkNotNullParameter(roomDatabase$Callback0, "callback");
            this.callbacks.add(roomDatabase$Callback0);
            return this;
        }

        public Builder addMigrations(Migration[] arr_migration) {
            Intrinsics.checkNotNullParameter(arr_migration, "migrations");
            if(this.migrationStartAndEndVersions == null) {
                this.migrationStartAndEndVersions = new HashSet();
            }
            for(int v = 0; v < arr_migration.length; ++v) {
                Migration migration0 = arr_migration[v];
                Set set0 = this.migrationStartAndEndVersions;
                Intrinsics.checkNotNull(set0);
                set0.add(migration0.startVersion);
                Set set1 = this.migrationStartAndEndVersions;
                Intrinsics.checkNotNull(set1);
                set1.add(migration0.endVersion);
            }
            Migration[] arr_migration1 = (Migration[])Arrays.copyOf(arr_migration, arr_migration.length);
            this.migrationContainer.addMigrations(arr_migration1);
            return this;
        }

        public Builder addTypeConverter(Object object0) {
            Intrinsics.checkNotNullParameter(object0, "typeConverter");
            this.typeConverters.add(object0);
            return this;
        }

        public Builder allowMainThreadQueries() {
            this.allowMainThreadQueries = true;
            return this;
        }

        public RoomDatabase build() {
            Executor executor0 = this.queryExecutor;
            if(executor0 == null && this.transactionExecutor == null) {
                Executor executor1 = ArchTaskExecutor.getIOThreadExecutor();
                this.transactionExecutor = executor1;
                this.queryExecutor = executor1;
            }
            else if(executor0 != null && this.transactionExecutor == null) {
                this.transactionExecutor = executor0;
            }
            else if(executor0 == null) {
                this.queryExecutor = this.transactionExecutor;
            }
            Set set0 = this.migrationStartAndEndVersions;
            if(set0 != null) {
                Intrinsics.checkNotNull(set0);
                for(Object object0: set0) {
                    int v = ((Number)object0).intValue();
                    if(this.migrationsNotRequiredFrom.contains(v)) {
                        throw new IllegalArgumentException(("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + v).toString());
                    }
                    if(false) {
                        break;
                    }
                }
            }
            Factory supportSQLiteOpenHelper$Factory0 = this.factory;
            if(supportSQLiteOpenHelper$Factory0 == null) {
                supportSQLiteOpenHelper$Factory0 = new FrameworkSQLiteOpenHelperFactory();
            }
            if(supportSQLiteOpenHelper$Factory0 == null) {
                supportSQLiteOpenHelper$Factory0 = null;
            }
            else {
                if(this.autoCloseTimeout > 0L) {
                    if(this.name == null) {
                        throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
                    }
                    long v1 = this.autoCloseTimeout;
                    TimeUnit timeUnit0 = this.autoCloseTimeUnit;
                    if(timeUnit0 == null) {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                    Executor executor2 = this.queryExecutor;
                    if(executor2 == null) {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                    supportSQLiteOpenHelper$Factory0 = new AutoClosingRoomOpenHelperFactory(supportSQLiteOpenHelper$Factory0, new AutoCloser(v1, timeUnit0, executor2));
                }
                int v2 = 0;
                String s = this.copyFromAssetPath;
                if(s != null || this.copyFromFile != null || this.copyFromInputStream != null) {
                    if(this.name == null) {
                        throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                    }
                    int v3 = this.copyFromFile == null ? 0 : 1;
                    if(this.copyFromInputStream != null) {
                        v2 = 1;
                    }
                    if((s == null ? 0 : 1) + v3 + v2 != 1) {
                        throw new IllegalArgumentException("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.");
                    }
                    supportSQLiteOpenHelper$Factory0 = new SQLiteCopyOpenHelperFactory(this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, supportSQLiteOpenHelper$Factory0);
                }
            }
            if(supportSQLiteOpenHelper$Factory0 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            if(this.queryCallback != null) {
                Executor executor3 = this.queryCallbackExecutor;
                if(executor3 == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                QueryCallback roomDatabase$QueryCallback0 = this.queryCallback;
                if(roomDatabase$QueryCallback0 == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                supportSQLiteOpenHelper$Factory0 = new QueryInterceptorOpenHelperFactory(supportSQLiteOpenHelper$Factory0, executor3, roomDatabase$QueryCallback0);
            }
            Context context0 = this.context;
            String s1 = this.name;
            MigrationContainer roomDatabase$MigrationContainer0 = this.migrationContainer;
            List list0 = this.callbacks;
            boolean z = this.allowMainThreadQueries;
            JournalMode roomDatabase$JournalMode0 = this.journalMode.resolve$room_runtime_release(context0);
            Executor executor4 = this.queryExecutor;
            if(executor4 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            Executor executor5 = this.transactionExecutor;
            if(executor5 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            DatabaseConfiguration databaseConfiguration0 = new DatabaseConfiguration(context0, s1, supportSQLiteOpenHelper$Factory0, roomDatabase$MigrationContainer0, list0, z, roomDatabase$JournalMode0, executor4, executor5, this.multiInstanceInvalidationIntent, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, this.prepackagedDatabaseCallback, this.typeConverters, this.autoMigrationSpecs);
            RoomDatabase roomDatabase0 = (RoomDatabase)Room.getGeneratedImplementation(this.klass, "_Impl");
            roomDatabase0.init(databaseConfiguration0);
            return roomDatabase0;
        }

        public Builder createFromAsset(String s) {
            Intrinsics.checkNotNullParameter(s, "databaseFilePath");
            this.copyFromAssetPath = s;
            return this;
        }

        public Builder createFromAsset(String s, PrepackagedDatabaseCallback roomDatabase$PrepackagedDatabaseCallback0) {
            Intrinsics.checkNotNullParameter(s, "databaseFilePath");
            Intrinsics.checkNotNullParameter(roomDatabase$PrepackagedDatabaseCallback0, "callback");
            this.prepackagedDatabaseCallback = roomDatabase$PrepackagedDatabaseCallback0;
            this.copyFromAssetPath = s;
            return this;
        }

        public Builder createFromFile(File file0) {
            Intrinsics.checkNotNullParameter(file0, "databaseFile");
            this.copyFromFile = file0;
            return this;
        }

        public Builder createFromFile(File file0, PrepackagedDatabaseCallback roomDatabase$PrepackagedDatabaseCallback0) {
            Intrinsics.checkNotNullParameter(file0, "databaseFile");
            Intrinsics.checkNotNullParameter(roomDatabase$PrepackagedDatabaseCallback0, "callback");
            this.prepackagedDatabaseCallback = roomDatabase$PrepackagedDatabaseCallback0;
            this.copyFromFile = file0;
            return this;
        }

        public Builder createFromInputStream(Callable callable0) {
            Intrinsics.checkNotNullParameter(callable0, "inputStreamCallable");
            this.copyFromInputStream = callable0;
            return this;
        }

        public Builder createFromInputStream(Callable callable0, PrepackagedDatabaseCallback roomDatabase$PrepackagedDatabaseCallback0) {
            Intrinsics.checkNotNullParameter(callable0, "inputStreamCallable");
            Intrinsics.checkNotNullParameter(roomDatabase$PrepackagedDatabaseCallback0, "callback");
            this.prepackagedDatabaseCallback = roomDatabase$PrepackagedDatabaseCallback0;
            this.copyFromInputStream = callable0;
            return this;
        }

        public Builder enableMultiInstanceInvalidation() {
            this.multiInstanceInvalidationIntent = this.name == null ? null : new Intent(this.context, MultiInstanceInvalidationService.class);
            return this;
        }

        public Builder fallbackToDestructiveMigration() {
            this.requireMigration = false;
            this.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder fallbackToDestructiveMigrationFrom(int[] arr_v) {
            Intrinsics.checkNotNullParameter(arr_v, "startVersions");
            for(int v = 0; v < arr_v.length; ++v) {
                this.migrationsNotRequiredFrom.add(((int)arr_v[v]));
            }
            return this;
        }

        public Builder fallbackToDestructiveMigrationOnDowngrade() {
            this.requireMigration = true;
            this.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder openHelperFactory(Factory supportSQLiteOpenHelper$Factory0) {
            this.factory = supportSQLiteOpenHelper$Factory0;
            return this;
        }

        public Builder setAutoCloseTimeout(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "autoCloseTimeUnit");
            if(v < 0L) {
                throw new IllegalArgumentException("autoCloseTimeout must be >= 0");
            }
            this.autoCloseTimeout = v;
            this.autoCloseTimeUnit = timeUnit0;
            return this;
        }

        public Builder setJournalMode(JournalMode roomDatabase$JournalMode0) {
            Intrinsics.checkNotNullParameter(roomDatabase$JournalMode0, "journalMode");
            this.journalMode = roomDatabase$JournalMode0;
            return this;
        }

        public Builder setMultiInstanceInvalidationServiceIntent(Intent intent0) {
            Intrinsics.checkNotNullParameter(intent0, "invalidationServiceIntent");
            if(this.name == null) {
                intent0 = null;
            }
            this.multiInstanceInvalidationIntent = intent0;
            return this;
        }

        public Builder setQueryCallback(QueryCallback roomDatabase$QueryCallback0, Executor executor0) {
            Intrinsics.checkNotNullParameter(roomDatabase$QueryCallback0, "queryCallback");
            Intrinsics.checkNotNullParameter(executor0, "executor");
            this.queryCallback = roomDatabase$QueryCallback0;
            this.queryCallbackExecutor = executor0;
            return this;
        }

        public Builder setQueryExecutor(Executor executor0) {
            Intrinsics.checkNotNullParameter(executor0, "executor");
            this.queryExecutor = executor0;
            return this;
        }

        public Builder setTransactionExecutor(Executor executor0) {
            Intrinsics.checkNotNullParameter(executor0, "executor");
            this.transactionExecutor = executor0;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\b\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\t"}, d2 = {"Landroidx/room/RoomDatabase$Callback;", "", "()V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "onDestructiveMigration", "onOpen", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }

        public void onDestructiveMigration(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/room/RoomDatabase$Companion;", "", "()V", "MAX_BIND_PARAMETER_CNT", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0002J\u0015\u0010\u0007\u001A\u00020\u00002\u0006\u0010\b\u001A\u00020\tH\u0000¢\u0006\u0002\b\nj\u0002\b\u000Bj\u0002\b\fj\u0002\b\r¨\u0006\u000E"}, d2 = {"Landroidx/room/RoomDatabase$JournalMode;", "", "(Ljava/lang/String;I)V", "isLowRamDevice", "", "activityManager", "Landroid/app/ActivityManager;", "resolve", "context", "Landroid/content/Context;", "resolve$room_runtime_release", "AUTOMATIC", "TRUNCATE", "WRITE_AHEAD_LOGGING", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        private static final JournalMode[] $values() [...] // Inlined contents

        private final boolean isLowRamDevice(ActivityManager activityManager0) {
            return Api19Impl.isLowRamDevice(activityManager0);
        }

        public final JournalMode resolve$room_runtime_release(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            if(this != JournalMode.AUTOMATIC) {
                return this;
            }
            Object object0 = context0.getSystemService("activity");
            ActivityManager activityManager0 = object0 instanceof ActivityManager ? ((ActivityManager)object0) : null;
            return activityManager0 == null || this.isLowRamDevice(activityManager0) ? JournalMode.TRUNCATE : JournalMode.WRITE_AHEAD_LOGGING;
        }
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u0007H\u0002J!\u0010\u000B\u001A\u00020\t2\u0012\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\f\"\u00020\u0007H\u0016¢\u0006\u0002\u0010\rJ\u0016\u0010\u000B\u001A\u00020\t2\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00070\u000EH\u0016J\u0016\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00052\u0006\u0010\u0012\u001A\u00020\u0005J \u0010\u0013\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000E2\u0006\u0010\u0014\u001A\u00020\u00052\u0006\u0010\u0015\u001A\u00020\u0005H\u0016J6\u0010\u0016\u001A\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000E2\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00070\u00182\u0006\u0010\u0019\u001A\u00020\u00102\u0006\u0010\u0014\u001A\u00020\u00052\u0006\u0010\u0015\u001A\u00020\u0005H\u0002J \u0010\u001A\u001A\u001A\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u001B0\u001BH\u0016R&\u0010\u0003\u001A\u001A\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Landroidx/room/RoomDatabase$MigrationContainer;", "", "()V", "migrations", "", "", "Ljava/util/TreeMap;", "Landroidx/room/migration/Migration;", "addMigration", "", "migration", "addMigrations", "", "([Landroidx/room/migration/Migration;)V", "", "contains", "", "startVersion", "endVersion", "findMigrationPath", "start", "end", "findUpMigrationPath", "result", "", "upgrade", "getMigrations", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class MigrationContainer {
        private final Map migrations;

        public MigrationContainer() {
            this.migrations = new LinkedHashMap();
        }

        private final void addMigration(Migration migration0) {
            int v = migration0.endVersion;
            Map map0 = this.migrations;
            Integer integer0 = migration0.startVersion;
            TreeMap treeMap0 = map0.get(integer0);
            if(treeMap0 == null) {
                treeMap0 = new TreeMap();
                map0.put(integer0, treeMap0);
            }
            if(treeMap0.containsKey(v)) {
                Log.w("ROOM", "Overriding migration " + treeMap0.get(v) + " with " + migration0);
            }
            treeMap0.put(v, migration0);
        }

        public void addMigrations(List list0) {
            Intrinsics.checkNotNullParameter(list0, "migrations");
            for(Object object0: list0) {
                this.addMigration(((Migration)object0));
            }
        }

        public void addMigrations(Migration[] arr_migration) {
            Intrinsics.checkNotNullParameter(arr_migration, "migrations");
            for(int v = 0; v < arr_migration.length; ++v) {
                this.addMigration(arr_migration[v]);
            }
        }

        public final boolean contains(int v, int v1) {
            Map map0 = this.getMigrations();
            if(map0.containsKey(v)) {
                Map map1 = (Map)map0.get(v);
                if(map1 == null) {
                    map1 = MapsKt.emptyMap();
                }
                return map1.containsKey(v1);
            }
            return false;
        }

        public List findMigrationPath(int v, int v1) {
            if(v == v1) {
                return CollectionsKt.emptyList();
            }
            return v1 <= v ? this.findUpMigrationPath(new ArrayList(), false, v, v1) : this.findUpMigrationPath(new ArrayList(), true, v, v1);
        }

        // This method was un-flattened
        private final List findUpMigrationPath(List list0, boolean z, int v, int v1) {
            while(true) {
                if(!z) {
                    if(v > v1) {
                        goto label_4;
                    }
                    return list0;
                }
                else if(v >= v1) {
                    return list0;
                }
            label_4:
                TreeMap treeMap0 = (TreeMap)this.migrations.get(v);
                if(treeMap0 == null) {
                    return null;
                }
                Set set0 = z ? treeMap0.descendingKeySet() : treeMap0.keySet();
                Iterator iterator0 = set0.iterator();
            label_12:
                if(!iterator0.hasNext()) {
                    return null;
                }
                Object object0 = iterator0.next();
                Integer integer0 = (Integer)object0;
                if(z) {
                    Intrinsics.checkNotNullExpressionValue(integer0, "targetVersion");
                    int v2 = (int)integer0;
                    if(v + 1 > v2 || v2 > v1) {
                        break;
                    }
                }
                else {
                    Intrinsics.checkNotNullExpressionValue(integer0, "targetVersion");
                    int v3 = (int)integer0;
                    if(v1 <= v3 && v3 < v) {
                        goto label_23;
                    }
                    break;
                }
            label_23:
                Object object1 = treeMap0.get(integer0);
                Intrinsics.checkNotNull(object1);
                list0.add(object1);
                v = (int)integer0;
            }
            goto label_12;
        }

        public Map getMigrations() {
            return this.migrations;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/room/RoomDatabase$PrepackagedDatabaseCallback;", "", "()V", "onOpenPrepackagedDatabase", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class PrepackagedDatabaseCallback {
        public void onOpenPrepackagedDatabase(SupportSQLiteDatabase supportSQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        }
    }

    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u000E\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/room/RoomDatabase$QueryCallback;", "", "onQuery", "", "sqlQuery", "", "bindArgs", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface QueryCallback {
        void onQuery(String arg1, List arg2);
    }

    public static final Companion Companion = null;
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean allowMainThreadQueries;
    private AutoCloser autoCloser;
    private Map autoMigrationSpecs;
    private final Map backingFieldMap;
    private SupportSQLiteOpenHelper internalOpenHelper;
    private Executor internalQueryExecutor;
    private Executor internalTransactionExecutor;
    private final InvalidationTracker invalidationTracker;
    protected List mCallbacks;
    protected volatile SupportSQLiteDatabase mDatabase;
    private final ReentrantReadWriteLock readWriteLock;
    private final ThreadLocal suspendingTransactionId;
    private final Map typeConverters;
    private boolean writeAheadLoggingEnabled;

    static {
        RoomDatabase.Companion = new Companion(null);
    }

    public RoomDatabase() {
        this.invalidationTracker = this.createInvalidationTracker();
        this.autoMigrationSpecs = new LinkedHashMap();
        this.readWriteLock = new ReentrantReadWriteLock();
        this.suspendingTransactionId = new ThreadLocal();
        Map map0 = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNullExpressionValue(map0, "synchronizedMap(mutableMapOf())");
        this.backingFieldMap = map0;
        this.typeConverters = new LinkedHashMap();
    }

    public void assertNotMainThread() {
        if(!this.allowMainThreadQueries && this.isMainThread$room_runtime_release()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void assertNotSuspendingTransaction() {
        if(!this.inTransaction() && this.suspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    @Deprecated(message = "beginTransaction() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void beginTransaction() {
        this.assertNotMainThread();
        AutoCloser autoCloser0 = this.autoCloser;
        if(autoCloser0 == null) {
            this.internalBeginTransaction();
            return;
        }
        autoCloser0.executeRefCountingFunction(new Function1() {
            {
                RoomDatabase.this = roomDatabase0;
                super(1);
            }

            public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "it");
                RoomDatabase.this.internalBeginTransaction();
                return null;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SupportSQLiteDatabase)object0));
            }
        });
    }

    public abstract void clearAllTables();

    public void close() {
        if(this.isOpen()) {
            ReentrantReadWriteLock.WriteLock reentrantReadWriteLock$WriteLock0 = this.readWriteLock.writeLock();
            Intrinsics.checkNotNullExpressionValue(reentrantReadWriteLock$WriteLock0, "readWriteLock.writeLock()");
            reentrantReadWriteLock$WriteLock0.lock();
            try {
                this.getInvalidationTracker().stopMultiInstanceInvalidation$room_runtime_release();
                this.getOpenHelper().close();
            }
            finally {
                reentrantReadWriteLock$WriteLock0.unlock();
            }
        }
    }

    public SupportSQLiteStatement compileStatement(String s) {
        Intrinsics.checkNotNullParameter(s, "sql");
        this.assertNotMainThread();
        this.assertNotSuspendingTransaction();
        return this.getOpenHelper().getWritableDatabase().compileStatement(s);
    }

    protected abstract InvalidationTracker createInvalidationTracker();

    protected abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration arg1);

    @Deprecated(message = "endTransaction() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void endTransaction() {
        AutoCloser autoCloser0 = this.autoCloser;
        if(autoCloser0 == null) {
            this.internalEndTransaction();
            return;
        }
        autoCloser0.executeRefCountingFunction(new Function1() {
            {
                RoomDatabase.this = roomDatabase0;
                super(1);
            }

            public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase0) {
                Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "it");
                RoomDatabase.this.internalEndTransaction();
                return null;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SupportSQLiteDatabase)object0));
            }
        });
    }

    protected final Map getAutoMigrationSpecs() {
        return this.autoMigrationSpecs;
    }

    public List getAutoMigrations(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "autoMigrationSpecs");
        return CollectionsKt.emptyList();
    }

    public final Map getBackingFieldMap() {
        return this.backingFieldMap;
    }

    public final Lock getCloseLock$room_runtime_release() {
        ReentrantReadWriteLock.ReadLock reentrantReadWriteLock$ReadLock0 = this.readWriteLock.readLock();
        Intrinsics.checkNotNullExpressionValue(reentrantReadWriteLock$ReadLock0, "readWriteLock.readLock()");
        return reentrantReadWriteLock$ReadLock0;
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    @Deprecated(message = "Will be hidden in a future release.")
    protected static void getMCallbacks$annotations() {
    }

    @Deprecated(message = "Will be hidden in the next release.")
    protected static void getMDatabase$annotations() {
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        SupportSQLiteOpenHelper supportSQLiteOpenHelper0 = this.internalOpenHelper;
        if(supportSQLiteOpenHelper0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalOpenHelper");
            return null;
        }
        return supportSQLiteOpenHelper0;
    }

    public Executor getQueryExecutor() {
        Executor executor0 = this.internalQueryExecutor;
        if(executor0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalQueryExecutor");
            return null;
        }
        return executor0;
    }

    public Set getRequiredAutoMigrationSpecs() {
        return SetsKt.emptySet();
    }

    protected Map getRequiredTypeConverters() {
        return MapsKt.emptyMap();
    }

    public final ThreadLocal getSuspendingTransactionId() {
        return this.suspendingTransactionId;
    }

    public Executor getTransactionExecutor() {
        Executor executor0 = this.internalTransactionExecutor;
        if(executor0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalTransactionExecutor");
            return null;
        }
        return executor0;
    }

    public Object getTypeConverter(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        return this.typeConverters.get(class0);
    }

    public boolean inTransaction() {
        return this.getOpenHelper().getWritableDatabase().inTransaction();
    }

    public void init(DatabaseConfiguration databaseConfiguration0) {
        boolean z;
        Intrinsics.checkNotNullParameter(databaseConfiguration0, "configuration");
        this.internalOpenHelper = this.createOpenHelper(databaseConfiguration0);
        Set set0 = this.getRequiredAutoMigrationSpecs();
        BitSet bitSet0 = new BitSet();
        Iterator iterator0 = set0.iterator();
        while(true) {
            int v = -1;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            Class class0 = (Class)object0;
            int v1 = databaseConfiguration0.autoMigrationSpecs.size() - 1;
            if(v1 >= 0) {
                while(true) {
                    if(class0.isAssignableFrom(databaseConfiguration0.autoMigrationSpecs.get(v1).getClass())) {
                        bitSet0.set(v1);
                        v = v1;
                        break;
                    }
                    if(v1 - 1 < 0) {
                        break;
                    }
                    --v1;
                }
            }
            if(v < 0) {
                throw new IllegalArgumentException(("A required auto migration spec (" + class0.getCanonicalName() + ") is missing in the database configuration.").toString());
            }
            this.autoMigrationSpecs.put(class0, databaseConfiguration0.autoMigrationSpecs.get(v));
        }
        int v2 = databaseConfiguration0.autoMigrationSpecs.size() - 1;
        if(v2 >= 0) {
            while(bitSet0.get(v2)) {
                if(v2 - 1 < 0) {
                    goto label_29;
                }
                --v2;
            }
            throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
        }
    label_29:
        Iterator iterator1 = this.getAutoMigrations(this.autoMigrationSpecs).iterator();
        while(true) {
            z = false;
            if(!iterator1.hasNext()) {
                break;
            }
            Object object1 = iterator1.next();
            Migration migration0 = (Migration)object1;
            if(!databaseConfiguration0.migrationContainer.contains(migration0.startVersion, migration0.endVersion)) {
                databaseConfiguration0.migrationContainer.addMigrations(new Migration[]{migration0});
            }
        }
        SupportSQLiteOpenHelper supportSQLiteOpenHelper0 = this.getOpenHelper();
        SQLiteCopyOpenHelper sQLiteCopyOpenHelper0 = (SQLiteCopyOpenHelper)this.unwrapOpenHelper(SQLiteCopyOpenHelper.class, supportSQLiteOpenHelper0);
        if(sQLiteCopyOpenHelper0 != null) {
            sQLiteCopyOpenHelper0.setDatabaseConfiguration(databaseConfiguration0);
        }
        SupportSQLiteOpenHelper supportSQLiteOpenHelper1 = this.getOpenHelper();
        AutoClosingRoomOpenHelper autoClosingRoomOpenHelper0 = (AutoClosingRoomOpenHelper)this.unwrapOpenHelper(AutoClosingRoomOpenHelper.class, supportSQLiteOpenHelper1);
        if(autoClosingRoomOpenHelper0 != null) {
            this.autoCloser = autoClosingRoomOpenHelper0.autoCloser;
            this.getInvalidationTracker().setAutoCloser$room_runtime_release(autoClosingRoomOpenHelper0.autoCloser);
        }
        if(databaseConfiguration0.journalMode == JournalMode.WRITE_AHEAD_LOGGING) {
            z = true;
        }
        this.getOpenHelper().setWriteAheadLoggingEnabled(z);
        this.mCallbacks = databaseConfiguration0.callbacks;
        this.internalQueryExecutor = databaseConfiguration0.queryExecutor;
        this.internalTransactionExecutor = new TransactionExecutor(databaseConfiguration0.transactionExecutor);
        this.allowMainThreadQueries = databaseConfiguration0.allowMainThreadQueries;
        this.writeAheadLoggingEnabled = z;
        if(databaseConfiguration0.multiInstanceInvalidationServiceIntent != null) {
            if(databaseConfiguration0.name == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            this.getInvalidationTracker().startMultiInstanceInvalidation$room_runtime_release(databaseConfiguration0.context, databaseConfiguration0.name, databaseConfiguration0.multiInstanceInvalidationServiceIntent);
        }
        Map map0 = this.getRequiredTypeConverters();
        BitSet bitSet1 = new BitSet();
        for(Object object2: map0.entrySet()) {
            Class class1 = (Class)((Map.Entry)object2).getKey();
            for(Object object3: ((List)((Map.Entry)object2).getValue())) {
                Class class2 = (Class)object3;
                int v3 = databaseConfiguration0.typeConverters.size() - 1;
                if(v3 >= 0) {
                    while(true) {
                        if(class2.isAssignableFrom(databaseConfiguration0.typeConverters.get(v3).getClass())) {
                            bitSet1.set(v3);
                            goto label_78;
                        }
                        if(v3 - 1 < 0) {
                            break;
                        }
                        --v3;
                    }
                }
                v3 = -1;
            label_78:
                if(v3 < 0) {
                    throw new IllegalArgumentException(("A required type converter (" + class2 + ") for " + class1.getCanonicalName() + " is missing in the database configuration.").toString());
                }
                Object object4 = databaseConfiguration0.typeConverters.get(v3);
                this.typeConverters.put(class2, object4);
            }
        }
        int v4 = databaseConfiguration0.typeConverters.size() - 1;
        if(v4 >= 0) {
            while(bitSet1.get(v4)) {
                if(v4 - 1 < 0) {
                    return;
                }
                --v4;
            }
            throw new IllegalArgumentException("Unexpected type converter " + databaseConfiguration0.typeConverters.get(v4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
        }
    }

    private final void internalBeginTransaction() {
        this.assertNotMainThread();
        SupportSQLiteDatabase supportSQLiteDatabase0 = this.getOpenHelper().getWritableDatabase();
        this.getInvalidationTracker().syncTriggers$room_runtime_release(supportSQLiteDatabase0);
        if(supportSQLiteDatabase0.isWriteAheadLoggingEnabled()) {
            supportSQLiteDatabase0.beginTransactionNonExclusive();
            return;
        }
        supportSQLiteDatabase0.beginTransaction();
    }

    private final void internalEndTransaction() {
        this.getOpenHelper().getWritableDatabase().endTransaction();
        if(!this.inTransaction()) {
            this.getInvalidationTracker().refreshVersionsAsync();
        }
    }

    protected void internalInitInvalidationTracker(SupportSQLiteDatabase supportSQLiteDatabase0) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
        this.getInvalidationTracker().internalInit$room_runtime_release(supportSQLiteDatabase0);
    }

    public final boolean isMainThread$room_runtime_release() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public boolean isOpen() {
        AutoCloser autoCloser0 = this.autoCloser;
        if(autoCloser0 != null) {
            return Intrinsics.areEqual(Boolean.valueOf(autoCloser0.isActive()), Boolean.TRUE);
        }
        SupportSQLiteDatabase supportSQLiteDatabase0 = this.mDatabase;
        return supportSQLiteDatabase0 == null ? Intrinsics.areEqual(null, Boolean.TRUE) : Intrinsics.areEqual(Boolean.valueOf(supportSQLiteDatabase0.isOpen()), Boolean.TRUE);
    }

    public static void isOpen$annotations() {
    }

    public final boolean isOpenInternal() {
        return this.mDatabase != null && this.mDatabase.isOpen();
    }

    public static void isOpenInternal$annotations() {
    }

    public final Cursor query(SupportSQLiteQuery supportSQLiteQuery0) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "query");
        return RoomDatabase.query$default(this, supportSQLiteQuery0, null, 2, null);
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery0, CancellationSignal cancellationSignal0) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "query");
        this.assertNotMainThread();
        this.assertNotSuspendingTransaction();
        return cancellationSignal0 == null ? this.getOpenHelper().getWritableDatabase().query(supportSQLiteQuery0) : this.getOpenHelper().getWritableDatabase().query(supportSQLiteQuery0, cancellationSignal0);
    }

    public Cursor query(String s, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(s, "query");
        return this.getOpenHelper().getWritableDatabase().query(new SimpleSQLiteQuery(s, arr_object));
    }

    public static Cursor query$default(RoomDatabase roomDatabase0, SupportSQLiteQuery supportSQLiteQuery0, CancellationSignal cancellationSignal0, int v, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
        }
        if((v & 2) != 0) {
            cancellationSignal0 = null;
        }
        return roomDatabase0.query(supportSQLiteQuery0, cancellationSignal0);
    }

    public Object runInTransaction(Callable callable0) {
        Intrinsics.checkNotNullParameter(callable0, "body");
        this.beginTransaction();
        try {
            Object object0 = callable0.call();
            this.setTransactionSuccessful();
            return object0;
        }
        finally {
            this.endTransaction();
        }
    }

    public void runInTransaction(Runnable runnable0) {
        Intrinsics.checkNotNullParameter(runnable0, "body");
        this.beginTransaction();
        try {
            runnable0.run();
            this.setTransactionSuccessful();
        }
        finally {
            this.endTransaction();
        }
    }

    protected final void setAutoMigrationSpecs(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<set-?>");
        this.autoMigrationSpecs = map0;
    }

    @Deprecated(message = "setTransactionSuccessful() is deprecated", replaceWith = @ReplaceWith(expression = "runInTransaction(Runnable)", imports = {}))
    public void setTransactionSuccessful() {
        this.getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    private final Object unwrapOpenHelper(Class class0, SupportSQLiteOpenHelper supportSQLiteOpenHelper0) {
        if(class0.isInstance(supportSQLiteOpenHelper0)) {
            return supportSQLiteOpenHelper0;
        }
        return supportSQLiteOpenHelper0 instanceof DelegatingOpenHelper ? this.unwrapOpenHelper(class0, ((DelegatingOpenHelper)supportSQLiteOpenHelper0).getDelegate()) : null;
    }
}

