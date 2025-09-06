package androidx.room;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u000E\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u0000 ?2\u00020\u0001:\u0001?B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u00A2\u0006\u0002\u0010\bJ\u0006\u00103\u001A\u000204J\u0006\u00105\u001A\u000204J%\u00106\u001A\u0002H7\"\u0004\b\u0000\u001072\u0012\u00108\u001A\u000E\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u0002H709\u00A2\u0006\u0002\u0010:J\u0006\u0010;\u001A\u00020\rJ\u000E\u0010<\u001A\u0002042\u0006\u0010\u0012\u001A\u00020\u0013J\u000E\u0010=\u001A\u0002042\u0006\u0010>\u001A\u00020\u000BR\u000E\u0010\t\u001A\u00020\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004\u00A2\u0006\u0002\n\u0000R \u0010\f\u001A\u0004\u0018\u00010\r8\u0000@\u0000X\u0081\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u000F\"\u0004\b\u0010\u0010\u0011R\u001A\u0010\u0012\u001A\u00020\u0013X\u0086.\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000E\u0010\u0018\u001A\u00020\u000BX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0019\u001A\u00020\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001A\u001A\u00020\u001BX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u001C\u001A\u00020\u001D8F\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u001ER\u001E\u0010\u001F\u001A\u00020\u00038\u0000@\u0000X\u0081\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000E\u0010$\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010%\u001A\u00020\u001DX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001C\u0010&\u001A\u0004\u0018\u00010\u000BX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001E\u0010+\u001A\u00020,8\u0000@\u0000X\u0081\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b-\u0010.\"\u0004\b/\u00100R\u0014\u00101\u001A\u00020,8AX\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b2\u0010.\u00A8\u0006@"}, d2 = {"Landroidx/room/AutoCloser;", "", "autoCloseTimeoutAmount", "", "autoCloseTimeUnit", "Ljava/util/concurrent/TimeUnit;", "autoCloseExecutor", "Ljava/util/concurrent/Executor;", "(JLjava/util/concurrent/TimeUnit;Ljava/util/concurrent/Executor;)V", "autoCloseTimeoutInMs", "autoCloser", "Ljava/lang/Runnable;", "delegateDatabase", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "getDelegateDatabase$room_runtime_release", "()Landroidx/sqlite/db/SupportSQLiteDatabase;", "setDelegateDatabase$room_runtime_release", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)V", "delegateOpenHelper", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "getDelegateOpenHelper", "()Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "setDelegateOpenHelper", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;)V", "executeAutoCloser", "executor", "handler", "Landroid/os/Handler;", "isActive", "", "()Z", "lastDecrementRefCountTimeStamp", "getLastDecrementRefCountTimeStamp$room_runtime_release", "()J", "setLastDecrementRefCountTimeStamp$room_runtime_release", "(J)V", "lock", "manuallyClosed", "onAutoCloseCallback", "getOnAutoCloseCallback$room_runtime_release", "()Ljava/lang/Runnable;", "setOnAutoCloseCallback$room_runtime_release", "(Ljava/lang/Runnable;)V", "refCount", "", "getRefCount$room_runtime_release", "()I", "setRefCount$room_runtime_release", "(I)V", "refCountForTest", "getRefCountForTest$room_runtime_release", "closeDatabaseIfOpen", "", "decrementCountAndScheduleClose", "executeRefCountingFunction", "V", "block", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "incrementCountAndEnsureDbIsOpen", "init", "setAutoCloseCallback", "onAutoClose", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AutoCloser {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/room/AutoCloser$Companion;", "", "()V", "autoCloseBug", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final String autoCloseBug = "https://issuetracker.google.com/issues/new?component=413107&template=1096568";
    private long autoCloseTimeoutInMs;
    private final Runnable autoCloser;
    private SupportSQLiteDatabase delegateDatabase;
    public SupportSQLiteOpenHelper delegateOpenHelper;
    private final Runnable executeAutoCloser;
    private final Executor executor;
    private final Handler handler;
    private long lastDecrementRefCountTimeStamp;
    private final Object lock;
    private boolean manuallyClosed;
    private Runnable onAutoCloseCallback;
    private int refCount;

    // 检测为 Lambda 实现
    public static void $r8$lambda$2bqU2AY0pACLcZVHaA6vI_33RZM(AutoCloser autoCloser0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$G2ZrHH8pFF9fWfG-_cMVTmkERIo(AutoCloser autoCloser0) [...]

    static {
        AutoCloser.Companion = new Companion(null);
    }

    public AutoCloser(long v, TimeUnit timeUnit0, Executor executor0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "autoCloseTimeUnit");
        Intrinsics.checkNotNullParameter(executor0, "autoCloseExecutor");
        super();
        this.handler = new Handler(Looper.getMainLooper());
        this.lock = new Object();
        this.autoCloseTimeoutInMs = timeUnit0.toMillis(v);
        this.executor = executor0;
        this.lastDecrementRefCountTimeStamp = SystemClock.uptimeMillis();
        this.executeAutoCloser = () -> AutoCloser.executeAutoCloser$lambda$0(this);
        this.autoCloser = () -> AutoCloser.autoCloser$lambda$3(this);
    }

    private static final void autoCloser$lambda$3(AutoCloser autoCloser0) {
        Unit unit0;
        Intrinsics.checkNotNullParameter(autoCloser0, "this$0");
        synchronized(autoCloser0.lock) {
            if(SystemClock.uptimeMillis() - autoCloser0.lastDecrementRefCountTimeStamp < autoCloser0.autoCloseTimeoutInMs) {
                return;
            }
            if(autoCloser0.refCount != 0) {
                return;
            }
            Runnable runnable0 = autoCloser0.onAutoCloseCallback;
            if(runnable0 == null) {
                unit0 = null;
            }
            else {
                runnable0.run();
                unit0 = Unit.INSTANCE;
            }
            if(unit0 != null) {
                SupportSQLiteDatabase supportSQLiteDatabase0 = autoCloser0.delegateDatabase;
                if(supportSQLiteDatabase0 != null && supportSQLiteDatabase0.isOpen()) {
                    supportSQLiteDatabase0.close();
                }
                autoCloser0.delegateDatabase = null;
                return;
            }
        }
        throw new IllegalStateException("onAutoCloseCallback is null but it should have been set before use. Please file a bug against Room at: https://issuetracker.google.com/issues/new?component=413107&template=1096568");
    }

    public final void closeDatabaseIfOpen() throws IOException {
        synchronized(this.lock) {
            this.manuallyClosed = true;
            SupportSQLiteDatabase supportSQLiteDatabase0 = this.delegateDatabase;
            if(supportSQLiteDatabase0 != null) {
                supportSQLiteDatabase0.close();
            }
            this.delegateDatabase = null;
        }
    }

    public final void decrementCountAndScheduleClose() {
        synchronized(this.lock) {
            int v1 = this.refCount;
            if(v1 > 0) {
                this.refCount = v1 - 1;
                if(v1 - 1 == 0) {
                    if(this.delegateDatabase == null) {
                        return;
                    }
                    this.handler.postDelayed(this.executeAutoCloser, this.autoCloseTimeoutInMs);
                }
                return;
            }
        }
        throw new IllegalStateException("ref count is 0 or lower but we\'re supposed to decrement");
    }

    private static final void executeAutoCloser$lambda$0(AutoCloser autoCloser0) {
        Intrinsics.checkNotNullParameter(autoCloser0, "this$0");
        autoCloser0.executor.execute(autoCloser0.autoCloser);
    }

    public final Object executeRefCountingFunction(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        try {
            return function10.invoke(this.incrementCountAndEnsureDbIsOpen());
        }
        finally {
            this.decrementCountAndScheduleClose();
        }
    }

    public final SupportSQLiteDatabase getDelegateDatabase$room_runtime_release() {
        return this.delegateDatabase;
    }

    public final SupportSQLiteOpenHelper getDelegateOpenHelper() {
        SupportSQLiteOpenHelper supportSQLiteOpenHelper0 = this.delegateOpenHelper;
        if(supportSQLiteOpenHelper0 != null) {
            return supportSQLiteOpenHelper0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("delegateOpenHelper");
        return null;
    }

    public final long getLastDecrementRefCountTimeStamp$room_runtime_release() {
        return this.lastDecrementRefCountTimeStamp;
    }

    public final Runnable getOnAutoCloseCallback$room_runtime_release() {
        return this.onAutoCloseCallback;
    }

    public final int getRefCount$room_runtime_release() {
        return this.refCount;
    }

    public final int getRefCountForTest$room_runtime_release() {
        synchronized(this.lock) {
        }
        return this.refCount;
    }

    public final SupportSQLiteDatabase incrementCountAndEnsureDbIsOpen() {
        synchronized(this.lock) {
            this.handler.removeCallbacks(this.executeAutoCloser);
            ++this.refCount;
            if(!this.manuallyClosed) {
                SupportSQLiteDatabase supportSQLiteDatabase0 = this.delegateDatabase;
                if(supportSQLiteDatabase0 != null && supportSQLiteDatabase0.isOpen()) {
                    return supportSQLiteDatabase0;
                }
                SupportSQLiteDatabase supportSQLiteDatabase1 = this.getDelegateOpenHelper().getWritableDatabase();
                this.delegateDatabase = supportSQLiteDatabase1;
                return supportSQLiteDatabase1;
            }
        }
        throw new IllegalStateException("Attempting to open already closed database.");
    }

    public final void init(SupportSQLiteOpenHelper supportSQLiteOpenHelper0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper0, "delegateOpenHelper");
        this.setDelegateOpenHelper(supportSQLiteOpenHelper0);
    }

    public final boolean isActive() {
        return !this.manuallyClosed;
    }

    public final void setAutoCloseCallback(Runnable runnable0) {
        Intrinsics.checkNotNullParameter(runnable0, "onAutoClose");
        this.onAutoCloseCallback = runnable0;
    }

    public final void setDelegateDatabase$room_runtime_release(SupportSQLiteDatabase supportSQLiteDatabase0) {
        this.delegateDatabase = supportSQLiteDatabase0;
    }

    public final void setDelegateOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper0) {
        Intrinsics.checkNotNullParameter(supportSQLiteOpenHelper0, "<set-?>");
        this.delegateOpenHelper = supportSQLiteOpenHelper0;
    }

    public final void setLastDecrementRefCountTimeStamp$room_runtime_release(long v) {
        this.lastDecrementRefCountTimeStamp = v;
    }

    public final void setOnAutoCloseCallback$room_runtime_release(Runnable runnable0) {
        this.onAutoCloseCallback = runnable0;
    }

    public final void setRefCount$room_runtime_release(int v) {
        this.refCount = v;
    }
}

