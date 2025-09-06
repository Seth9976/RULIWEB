package androidx.work.impl;

import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.Logger;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u0010\u0010\b\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0003J\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u001A\u0010\u000B\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\r"}, d2 = {"Landroidx/work/impl/WorkDatabasePathHelper;", "", "()V", "getDatabasePath", "Ljava/io/File;", "context", "Landroid/content/Context;", "getDefaultDatabasePath", "getNoBackupPath", "migrateDatabase", "", "migrationPaths", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkDatabasePathHelper {
    public static final WorkDatabasePathHelper INSTANCE;

    static {
        WorkDatabasePathHelper.INSTANCE = new WorkDatabasePathHelper();
    }

    public final File getDatabasePath(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return Build.VERSION.SDK_INT >= 23 ? this.getNoBackupPath(context0) : this.getDefaultDatabasePath(context0);
    }

    public final File getDefaultDatabasePath(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        File file0 = context0.getDatabasePath("androidx.work.workdb");
        Intrinsics.checkNotNullExpressionValue(file0, "context.getDatabasePath(WORK_DATABASE_NAME)");
        return file0;
    }

    private final File getNoBackupPath(Context context0) {
        return new File(Api21Impl.INSTANCE.getNoBackupFilesDir(context0), "androidx.work.workdb");
    }

    @JvmStatic
    public static final void migrateDatabase(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        WorkDatabasePathHelper workDatabasePathHelper0 = WorkDatabasePathHelper.INSTANCE;
        File file0 = workDatabasePathHelper0.getDefaultDatabasePath(context0);
        if(Build.VERSION.SDK_INT >= 23 && file0.exists()) {
            Logger.get().debug("WM-WrkDbPathHelper", "Migrating WorkDatabase to the no-backup directory");
            for(Object object0: workDatabasePathHelper0.migrationPaths(context0).entrySet()) {
                File file1 = (File)((Map.Entry)object0).getKey();
                File file2 = (File)((Map.Entry)object0).getValue();
                if(file1.exists()) {
                    if(file2.exists()) {
                        Logger.get().warning("WM-WrkDbPathHelper", "Over-writing contents of " + file2);
                    }
                    String s = file1.renameTo(file2) ? "Migrated " + file1 + "to " + file2 : "Renaming " + file1 + " to " + file2 + " failed";
                    Logger.get().debug("WM-WrkDbPathHelper", s);
                }
            }
        }
    }

    public final Map migrationPaths(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        if(Build.VERSION.SDK_INT >= 23) {
            File file0 = this.getDefaultDatabasePath(context0);
            File file1 = this.getDatabasePath(context0);
            String[] arr_s = WorkDatabasePathHelperKt.DATABASE_EXTRA_FILES;
            Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(arr_s.length), 16));
            for(int v = 0; v < arr_s.length; ++v) {
                String s = arr_s[v];
                Pair pair0 = TuplesKt.to(new File(file0.getPath() + s), new File(file1.getPath() + s));
                map0.put(pair0.getFirst(), pair0.getSecond());
            }
            return MapsKt.plus(map0, TuplesKt.to(file0, file1));
        }
        return MapsKt.emptyMap();
    }
}

