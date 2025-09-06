package androidx.room;

import android.content.Context;
import android.util.Log;
import androidx.room.util.DBUtil;
import androidx.room.util.FileUtil;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.sqlite.util.ProcessLock;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000B\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002BA\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001A\u0004\u0018\u00010\b\u0012\u000E\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\n\u0012\u0006\u0010\f\u001A\u00020\r\u0012\u0006\u0010\u000E\u001A\u00020\u0001\u00A2\u0006\u0002\u0010\u000FJ\b\u0010\u001F\u001A\u00020 H\u0016J\u0018\u0010!\u001A\u00020 2\u0006\u0010\"\u001A\u00020\b2\u0006\u0010#\u001A\u00020\u001CH\u0002J\u0010\u0010$\u001A\u00020\u00012\u0006\u0010%\u001A\u00020\bH\u0002J\u0018\u0010&\u001A\u00020 2\u0006\u0010%\u001A\u00020\b2\u0006\u0010#\u001A\u00020\u001CH\u0002J\u000E\u0010\'\u001A\u00020 2\u0006\u0010\u0010\u001A\u00020\u0011J\u0010\u0010(\u001A\u00020 2\u0006\u0010)\u001A\u00020\u001CH\u0017J\u0010\u0010*\u001A\u00020 2\u0006\u0010#\u001A\u00020\u001CH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u0004\u0018\u00010\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u000B\u0018\u00010\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0011X\u0082.\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001A\u0004\u0018\u00010\u00068VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014R\u000E\u0010\f\u001A\u00020\rX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u000E\u001A\u00020\u0001X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001A\u00020\u00188VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u001AR\u000E\u0010\u001B\u001A\u00020\u001CX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001D\u001A\u00020\u00188VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001E\u0010\u001A\u00A8\u0006+"}, d2 = {"Landroidx/room/SQLiteCopyOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "Landroidx/room/DelegatingOpenHelper;", "context", "Landroid/content/Context;", "copyFromAssetPath", "", "copyFromFile", "Ljava/io/File;", "copyFromInputStream", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "databaseVersion", "", "delegate", "(Landroid/content/Context;Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;ILandroidx/sqlite/db/SupportSQLiteOpenHelper;)V", "databaseConfiguration", "Landroidx/room/DatabaseConfiguration;", "databaseName", "getDatabaseName", "()Ljava/lang/String;", "getDelegate", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "readableDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getReadableDatabase", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "verified", "", "writableDatabase", "getWritableDatabase", "close", "", "copyDatabaseFile", "destinationFile", "writable", "createFrameworkOpenHelper", "databaseFile", "dispatchOnOpenPrepackagedDatabase", "setDatabaseConfiguration", "setWriteAheadLoggingEnabled", "enabled", "verifyDatabaseFile", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SQLiteCopyOpenHelper implements DelegatingOpenHelper, SupportSQLiteOpenHelper, AutoCloseable {
    private final Context context;
    private final String copyFromAssetPath;
    private final File copyFromFile;
    private final Callable copyFromInputStream;
    private DatabaseConfiguration databaseConfiguration;
    private final int databaseVersion;
    private final SupportSQLiteOpenHelper delegate;
    private boolean verified;

    public SQLiteCopyOpenHelper(Context context0, String s, File file0, Callable callable0, int v, SupportSQLiteOpenHelper supportSQLiteOpenHelper0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper0, "delegate");
        super();
        this.context = context0;
        this.copyFromAssetPath = s;
        this.copyFromFile = file0;
        this.copyFromInputStream = callable0;
        this.databaseVersion = v;
        this.delegate = supportSQLiteOpenHelper0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void close() {
        synchronized(this) {
            this.getDelegate().close();
            this.verified = false;
        }
    }

    private final void copyDatabaseFile(File file0, boolean z) throws IOException {
        InputStream inputStream0;
        ReadableByteChannel readableByteChannel0;
        if(this.copyFromAssetPath == null) {
            if(this.copyFromFile != null) {
                FileChannel fileChannel0 = new FileInputStream(this.copyFromFile).getChannel();
                Intrinsics.checkNotNullExpressionValue(fileChannel0, "FileInputStream(copyFromFile).channel");
                readableByteChannel0 = fileChannel0;
                goto label_17;
            }
            Callable callable0 = this.copyFromInputStream;
            if(callable0 == null) {
                throw new IllegalStateException("copyFromAssetPath, copyFromFile and copyFromInputStream are all null!");
            }
            try {
                inputStream0 = (InputStream)callable0.call();
            }
            catch(Exception exception0) {
                throw new IOException("inputStreamCallable exception on call", exception0);
            }
            readableByteChannel0 = Channels.newChannel(inputStream0);
            Intrinsics.checkNotNullExpressionValue(readableByteChannel0, "newChannel(inputStream)");
        }
        else {
            readableByteChannel0 = Channels.newChannel(this.context.getAssets().open(this.copyFromAssetPath));
            Intrinsics.checkNotNullExpressionValue(readableByteChannel0, "newChannel(context.assets.open(copyFromAssetPath))");
        }
    label_17:
        File file1 = File.createTempFile("room-copy-helper", ".tmp", this.context.getCacheDir());
        file1.deleteOnExit();
        FileChannel fileChannel1 = new FileOutputStream(file1).getChannel();
        Intrinsics.checkNotNullExpressionValue(fileChannel1, "output");
        FileUtil.copy(readableByteChannel0, fileChannel1);
        File file2 = file0.getParentFile();
        if(file2 != null && !file2.exists() && !file2.mkdirs()) {
            throw new IOException("Failed to create directories for " + file0.getAbsolutePath());
        }
        Intrinsics.checkNotNullExpressionValue(file1, "intermediateFile");
        this.dispatchOnOpenPrepackagedDatabase(file1, z);
        if(!file1.renameTo(file0)) {
            throw new IOException("Failed to move intermediate file (" + file1.getAbsolutePath() + ") to destination (" + file0.getAbsolutePath() + ").");
        }
    }

    private final SupportSQLiteOpenHelper createFrameworkOpenHelper(File file0) {
        try {
            int v = DBUtil.readVersion(file0);
            return new FrameworkSQLiteOpenHelperFactory().create(Configuration.Companion.builder(this.context).name(file0.getAbsolutePath()).callback(new Callback(/*ERROR_MISSING_ARG_1/*) {
                @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
                public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                }

                @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
                public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase0) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                    int v = RangesKt.coerceAtLeast(v, 1);
                    if(v < 1) {
                        supportSQLiteDatabase0.setVersion(v);
                    }
                }

                @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Callback
                public void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase0, int v, int v1) {
                    Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "db");
                }
            }).build());
        }
        catch(IOException iOException0) {
            throw new RuntimeException("Malformed database file, unable to read version.", iOException0);
        }
    }

    private final void dispatchOnOpenPrepackagedDatabase(File file0, boolean z) {
        DatabaseConfiguration databaseConfiguration0 = this.databaseConfiguration;
        if(databaseConfiguration0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseConfiguration");
            databaseConfiguration0 = null;
        }
        if(databaseConfiguration0.prepackagedDatabaseCallback == null) {
            return;
        }
        Closeable closeable0 = this.createFrameworkOpenHelper(file0);
        try {
            SupportSQLiteDatabase supportSQLiteDatabase0 = z ? ((SupportSQLiteOpenHelper)closeable0).getWritableDatabase() : ((SupportSQLiteOpenHelper)closeable0).getReadableDatabase();
            DatabaseConfiguration databaseConfiguration1 = this.databaseConfiguration;
            if(databaseConfiguration1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("databaseConfiguration");
                databaseConfiguration1 = null;
            }
            Intrinsics.checkNotNull(databaseConfiguration1.prepackagedDatabaseCallback);
            databaseConfiguration1.prepackagedDatabaseCallback.onOpenPrepackagedDatabase(supportSQLiteDatabase0);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public String getDatabaseName() {
        return this.getDelegate().getDatabaseName();
    }

    @Override  // androidx.room.DelegatingOpenHelper
    public SupportSQLiteOpenHelper getDelegate() {
        return this.delegate;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getReadableDatabase() {
        if(!this.verified) {
            this.verifyDatabaseFile(false);
            this.verified = true;
        }
        return this.getDelegate().getReadableDatabase();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public SupportSQLiteDatabase getWritableDatabase() {
        if(!this.verified) {
            this.verifyDatabaseFile(true);
            this.verified = true;
        }
        return this.getDelegate().getWritableDatabase();
    }

    public final void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration0) {
        Intrinsics.checkNotNullParameter(databaseConfiguration0, "databaseConfiguration");
        this.databaseConfiguration = databaseConfiguration0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper
    public void setWriteAheadLoggingEnabled(boolean z) {
        this.getDelegate().setWriteAheadLoggingEnabled(z);
    }

    private final void verifyDatabaseFile(boolean z) {
        String s = this.getDatabaseName();
        if(s == null) {
            throw new IllegalStateException("Required value was null.");
        }
        File file0 = this.context.getDatabasePath(s);
        DatabaseConfiguration databaseConfiguration0 = this.databaseConfiguration;
        DatabaseConfiguration databaseConfiguration1 = null;
        if(databaseConfiguration0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseConfiguration");
            databaseConfiguration0 = null;
        }
        ProcessLock processLock0 = new ProcessLock(s, this.context.getFilesDir(), databaseConfiguration0.multiInstanceInvalidation);
        try {
            ProcessLock.lock$default(processLock0, false, 1, null);
            if(!file0.exists()) {
                try {
                    Intrinsics.checkNotNullExpressionValue(file0, "databaseFile");
                    this.copyDatabaseFile(file0, z);
                }
                catch(IOException iOException0) {
                    throw new RuntimeException("Unable to copy database file.", iOException0);
                }
                return;
            }
            try {
                Intrinsics.checkNotNullExpressionValue(file0, "databaseFile");
                int v1 = DBUtil.readVersion(file0);
            }
            catch(IOException iOException1) {
                Log.w("ROOM", "Unable to read database version.", iOException1);
                return;
            }
            if(v1 == this.databaseVersion) {
                return;
            }
            DatabaseConfiguration databaseConfiguration2 = this.databaseConfiguration;
            if(databaseConfiguration2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("databaseConfiguration");
            }
            else {
                databaseConfiguration1 = databaseConfiguration2;
            }
            if(databaseConfiguration1.isMigrationRequired(v1, this.databaseVersion)) {
                return;
            }
            if(this.context.deleteDatabase(s)) {
                try {
                    this.copyDatabaseFile(file0, z);
                }
                catch(IOException iOException2) {
                    Log.w("ROOM", "Unable to copy database file.", iOException2);
                }
            }
            else {
                Log.w("ROOM", "Failed to delete database file (" + s + ") for a copy destructive migration.");
            }
        }
        finally {
            processLock0.unlock();
        }
    }
}

