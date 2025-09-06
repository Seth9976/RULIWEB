package androidx.room;

import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Factory;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.File;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u000E\u0010\u0006\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\t\u001A\u00020\u0001¢\u0006\u0002\u0010\nJ\u0010\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016R\u0010\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001A\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/room/SQLiteCopyOpenHelperFactory;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;", "mCopyFromAssetPath", "", "mCopyFromFile", "Ljava/io/File;", "mCopyFromInputStream", "Ljava/util/concurrent/Callable;", "Ljava/io/InputStream;", "mDelegate", "(Ljava/lang/String;Ljava/io/File;Ljava/util/concurrent/Callable;Landroidx/sqlite/db/SupportSQLiteOpenHelper$Factory;)V", "create", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "configuration", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Configuration;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SQLiteCopyOpenHelperFactory implements Factory {
    private final String mCopyFromAssetPath;
    private final File mCopyFromFile;
    private final Callable mCopyFromInputStream;
    private final Factory mDelegate;

    public SQLiteCopyOpenHelperFactory(String s, File file0, Callable callable0, Factory supportSQLiteOpenHelper$Factory0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Factory0, "mDelegate");
        super();
        this.mCopyFromAssetPath = s;
        this.mCopyFromFile = file0;
        this.mCopyFromInputStream = callable0;
        this.mDelegate = supportSQLiteOpenHelper$Factory0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteOpenHelper$Factory
    public SupportSQLiteOpenHelper create(Configuration supportSQLiteOpenHelper$Configuration0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper$Configuration0, "configuration");
        SupportSQLiteOpenHelper supportSQLiteOpenHelper0 = this.mDelegate.create(supportSQLiteOpenHelper$Configuration0);
        return new SQLiteCopyOpenHelper(supportSQLiteOpenHelper$Configuration0.context, this.mCopyFromAssetPath, this.mCopyFromFile, this.mCopyFromInputStream, supportSQLiteOpenHelper$Configuration0.callback.version, supportSQLiteOpenHelper0);
    }
}

