package androidx.sqlite.db;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\b"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat;", "", "()V", "Api16Impl", "Api19Impl", "Api21Impl", "Api23Impl", "Api29Impl", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SupportSQLiteCompat {
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\b\u0010\u0007\u001A\u00020\u0006H\u0007J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0007J\u0010\u0010\f\u001A\u00020\u00042\u0006\u0010\r\u001A\u00020\u000EH\u0007J\u0010\u0010\u000F\u001A\u00020\t2\u0006\u0010\r\u001A\u00020\u000EH\u0007JI\u0010\u0010\u001A\u00020\u00112\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\u00132\u0010\u0010\u0014\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u00152\b\u0010\u0016\u001A\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019J\u0018\u0010\u001A\u001A\u00020\u00042\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u001B\u001A\u00020\tH\u0007J\u0018\u0010\u001C\u001A\u00020\u00042\u0006\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\tH\u0007¨\u0006 "}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api16Impl;", "", "()V", "cancel", "", "cancellationSignal", "Landroid/os/CancellationSignal;", "createCancellationSignal", "deleteDatabase", "", "file", "Ljava/io/File;", "disableWriteAheadLogging", "sQLiteDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "isWriteAheadLoggingEnabled", "rawQueryWithFactory", "Landroid/database/Cursor;", "sql", "", "selectionArgs", "", "editTable", "cursorFactory", "Landroid/database/sqlite/SQLiteDatabase$CursorFactory;", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;)Landroid/database/Cursor;", "setForeignKeyConstraintsEnabled", "enable", "setWriteAheadLoggingEnabled", "sQLiteOpenHelper", "Landroid/database/sqlite/SQLiteOpenHelper;", "enabled", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api16Impl {
        public static final Api16Impl INSTANCE;

        static {
            Api16Impl.INSTANCE = new Api16Impl();
        }

        @JvmStatic
        public static final void cancel(CancellationSignal cancellationSignal0) {
            Intrinsics.checkNotNullParameter(cancellationSignal0, "cancellationSignal");
            cancellationSignal0.cancel();
        }

        @JvmStatic
        public static final CancellationSignal createCancellationSignal() {
            return new CancellationSignal();
        }

        @JvmStatic
        public static final boolean deleteDatabase(File file0) {
            Intrinsics.checkNotNullParameter(file0, "file");
            return SQLiteDatabase.deleteDatabase(file0);
        }

        @JvmStatic
        public static final void disableWriteAheadLogging(SQLiteDatabase sQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sQLiteDatabase");
            sQLiteDatabase0.disableWriteAheadLogging();
        }

        @JvmStatic
        public static final boolean isWriteAheadLoggingEnabled(SQLiteDatabase sQLiteDatabase0) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sQLiteDatabase");
            return sQLiteDatabase0.isWriteAheadLoggingEnabled();
        }

        @JvmStatic
        public static final Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase0, String s, String[] arr_s, String s1, CancellationSignal cancellationSignal0, SQLiteDatabase.CursorFactory sQLiteDatabase$CursorFactory0) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sQLiteDatabase");
            Intrinsics.checkNotNullParameter(s, "sql");
            Intrinsics.checkNotNullParameter(arr_s, "selectionArgs");
            Intrinsics.checkNotNullParameter(cancellationSignal0, "cancellationSignal");
            Intrinsics.checkNotNullParameter(sQLiteDatabase$CursorFactory0, "cursorFactory");
            Cursor cursor0 = sQLiteDatabase0.rawQueryWithFactory(sQLiteDatabase$CursorFactory0, s, arr_s, s1, cancellationSignal0);
            Intrinsics.checkNotNullExpressionValue(cursor0, "sQLiteDatabase.rawQueryW…ationSignal\n            )");
            return cursor0;
        }

        @JvmStatic
        public static final void setForeignKeyConstraintsEnabled(SQLiteDatabase sQLiteDatabase0, boolean z) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase0, "sQLiteDatabase");
            sQLiteDatabase0.setForeignKeyConstraintsEnabled(z);
        }

        @JvmStatic
        public static final void setWriteAheadLoggingEnabled(SQLiteOpenHelper sQLiteOpenHelper0, boolean z) {
            Intrinsics.checkNotNullParameter(sQLiteOpenHelper0, "sQLiteOpenHelper");
            sQLiteOpenHelper0.setWriteAheadLoggingEnabled(z);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0007¨\u0006\u000B"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api19Impl;", "", "()V", "getNotificationUri", "Landroid/net/Uri;", "cursor", "Landroid/database/Cursor;", "isLowRamDevice", "", "activityManager", "Landroid/app/ActivityManager;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api19Impl {
        public static final Api19Impl INSTANCE;

        static {
            Api19Impl.INSTANCE = new Api19Impl();
        }

        @JvmStatic
        public static final Uri getNotificationUri(Cursor cursor0) {
            Intrinsics.checkNotNullParameter(cursor0, "cursor");
            Uri uri0 = cursor0.getNotificationUri();
            Intrinsics.checkNotNullExpressionValue(uri0, "cursor.notificationUri");
            return uri0;
        }

        @JvmStatic
        public static final boolean isLowRamDevice(ActivityManager activityManager0) {
            Intrinsics.checkNotNullParameter(activityManager0, "activityManager");
            return activityManager0.isLowRamDevice();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api21Impl;", "", "()V", "getNoBackupFilesDir", "Ljava/io/File;", "context", "Landroid/content/Context;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api21Impl {
        public static final Api21Impl INSTANCE;

        static {
            Api21Impl.INSTANCE = new Api21Impl();
        }

        @JvmStatic
        public static final File getNoBackupFilesDir(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            File file0 = context0.getNoBackupFilesDir();
            Intrinsics.checkNotNullExpressionValue(file0, "context.noBackupFilesDir");
            return file0;
        }
    }

    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api23Impl;", "", "()V", "setExtras", "", "cursor", "Landroid/database/Cursor;", "extras", "Landroid/os/Bundle;", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api23Impl {
        public static final Api23Impl INSTANCE;

        static {
            Api23Impl.INSTANCE = new Api23Impl();
        }

        @JvmStatic
        public static final void setExtras(Cursor cursor0, Bundle bundle0) {
            Intrinsics.checkNotNullParameter(cursor0, "cursor");
            Intrinsics.checkNotNullParameter(bundle0, "extras");
            cursor0.setExtras(bundle0);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001A\u00020\u0007H\u0007J(\u0010\b\u001A\u00020\t2\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u000B2\u000E\u0010\f\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0007¨\u0006\r"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteCompat$Api29Impl;", "", "()V", "getNotificationUris", "", "Landroid/net/Uri;", "cursor", "Landroid/database/Cursor;", "setNotificationUris", "", "cr", "Landroid/content/ContentResolver;", "uris", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Api29Impl {
        public static final Api29Impl INSTANCE;

        static {
            Api29Impl.INSTANCE = new Api29Impl();
        }

        @JvmStatic
        public static final List getNotificationUris(Cursor cursor0) {
            Intrinsics.checkNotNullParameter(cursor0, "cursor");
            List list0 = cursor0.getNotificationUris();
            Intrinsics.checkNotNull(list0);
            return list0;
        }

        @JvmStatic
        public static final void setNotificationUris(Cursor cursor0, ContentResolver contentResolver0, List list0) {
            Intrinsics.checkNotNullParameter(cursor0, "cursor");
            Intrinsics.checkNotNullParameter(contentResolver0, "cr");
            Intrinsics.checkNotNullParameter(list0, "uris");
            cursor0.setNotificationUris(contentResolver0, list0);
        }
    }

}

