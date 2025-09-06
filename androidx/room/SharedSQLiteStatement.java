package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\b\'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001A\u00020\bH\u0016J\b\u0010\u000E\u001A\u00020\u000FH\u0014J\b\u0010\u0010\u001A\u00020\bH\u0002J\b\u0010\u0011\u001A\u00020\u0012H$J\u0010\u0010\t\u001A\u00020\b2\u0006\u0010\u0013\u001A\u00020\u0014H\u0002J\u0010\u0010\u0015\u001A\u00020\u000F2\u0006\u0010\u0016\u001A\u00020\bH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001B\u0010\u0007\u001A\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000B\u0010\f\u001A\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Landroidx/room/SharedSQLiteStatement;", "", "database", "Landroidx/room/RoomDatabase;", "(Landroidx/room/RoomDatabase;)V", "lock", "Ljava/util/concurrent/atomic/AtomicBoolean;", "stmt", "Landroidx/sqlite/db/SupportSQLiteStatement;", "getStmt", "()Landroidx/sqlite/db/SupportSQLiteStatement;", "stmt$delegate", "Lkotlin/Lazy;", "acquire", "assertNotMainThread", "", "createNewStatement", "createQuery", "", "canUseCached", "", "release", "statement", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class SharedSQLiteStatement {
    private final RoomDatabase database;
    private final AtomicBoolean lock;
    private final Lazy stmt$delegate;

    public SharedSQLiteStatement(RoomDatabase roomDatabase0) {
        Intrinsics.checkNotNullParameter(roomDatabase0, "database");
        super();
        this.database = roomDatabase0;
        this.lock = new AtomicBoolean(false);
        this.stmt$delegate = LazyKt.lazy(new Function0() {
            {
                SharedSQLiteStatement.this = sharedSQLiteStatement0;
                super(0);
            }

            public final SupportSQLiteStatement invoke() {
                return SharedSQLiteStatement.access$createNewStatement(SharedSQLiteStatement.this);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    public static final SupportSQLiteStatement access$createNewStatement(SharedSQLiteStatement sharedSQLiteStatement0) {
        return sharedSQLiteStatement0.createNewStatement();
    }

    public SupportSQLiteStatement acquire() {
        this.assertNotMainThread();
        return this.getStmt(this.lock.compareAndSet(false, true));
    }

    protected void assertNotMainThread() {
        this.database.assertNotMainThread();
    }

    private final SupportSQLiteStatement createNewStatement() {
        String s = this.createQuery();
        return this.database.compileStatement(s);
    }

    protected abstract String createQuery();

    private final SupportSQLiteStatement getStmt() {
        return (SupportSQLiteStatement)this.stmt$delegate.getValue();
    }

    // 去混淆评级： 低(20)
    private final SupportSQLiteStatement getStmt(boolean z) {
        return z ? this.getStmt() : this.createNewStatement();
    }

    public void release(SupportSQLiteStatement supportSQLiteStatement0) {
        Intrinsics.checkNotNullParameter(supportSQLiteStatement0, "statement");
        if(supportSQLiteStatement0 == this.getStmt()) {
            this.lock.set(false);
        }
    }
}

