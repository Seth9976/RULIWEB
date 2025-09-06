package androidx.sqlite.util;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u001F\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000E\u001A\u00020\u000F2\b\b\u0002\u0010\u0006\u001A\u00020\u0007J\u0006\u0010\u0010\u001A\u00020\u000FR\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/sqlite/util/ProcessLock;", "", "name", "", "lockDir", "Ljava/io/File;", "processLock", "", "(Ljava/lang/String;Ljava/io/File;Z)V", "lockChannel", "Ljava/nio/channels/FileChannel;", "lockFile", "threadLock", "Ljava/util/concurrent/locks/Lock;", "lock", "", "unlock", "Companion", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ProcessLock {
    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001A\u00020\u00072\u0006\u0010\t\u001A\u00020\u0004H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/sqlite/util/ProcessLock$Companion;", "", "()V", "TAG", "", "threadLocksMap", "", "Ljava/util/concurrent/locks/Lock;", "getThreadLock", "key", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final Lock access$getThreadLock(Companion processLock$Companion0, String s) {
            return processLock$Companion0.getThreadLock(s);
        }

        private final Lock getThreadLock(String s) {
            synchronized(ProcessLock.threadLocksMap) {
                Map map1 = ProcessLock.threadLocksMap;
                ReentrantLock reentrantLock0 = map1.get(s);
                if(reentrantLock0 == null) {
                    reentrantLock0 = new ReentrantLock();
                    map1.put(s, reentrantLock0);
                }
                return reentrantLock0;
            }
        }
    }

    public static final Companion Companion = null;
    private static final String TAG = "SupportSQLiteLock";
    private FileChannel lockChannel;
    private final File lockFile;
    private final boolean processLock;
    private final Lock threadLock;
    private static final Map threadLocksMap;

    static {
        ProcessLock.Companion = new Companion(null);
        ProcessLock.threadLocksMap = new HashMap();
    }

    public ProcessLock(String s, File file0, boolean z) {
        Intrinsics.checkNotNullParameter(s, "name");
        super();
        this.processLock = z;
        this.lockFile = file0 == null ? null : new File(file0, s + ".lck");
        this.threadLock = Companion.access$getThreadLock(ProcessLock.Companion, s);
    }

    public final void lock(boolean z) {
        this.threadLock.lock();
        if(z) {
            try {
                File file0 = this.lockFile;
                if(file0 == null) {
                    throw new IOException("No lock directory was provided.");
                }
                File file1 = file0.getParentFile();
                if(file1 != null) {
                    file1.mkdirs();
                }
                FileChannel fileChannel0 = new FileOutputStream(this.lockFile).getChannel();
                fileChannel0.lock();
                this.lockChannel = fileChannel0;
            }
            catch(IOException iOException0) {
                this.lockChannel = null;
                Log.w("SupportSQLiteLock", "Unable to grab file lock.", iOException0);
            }
        }
    }

    public static void lock$default(ProcessLock processLock0, boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            z = processLock0.processLock;
        }
        processLock0.lock(z);
    }

    public final void unlock() {
        FileChannel fileChannel0 = this.lockChannel;
        if(fileChannel0 != null) {
            try {
                fileChannel0.close();
            }
            catch(IOException unused_ex) {
            }
        }
        this.threadLock.unlock();
    }
}

