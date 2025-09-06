package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001A\u00020\u0007H\u0016J\b\u0010\b\u001A\u00020\tH\u0016J\b\u0010\n\u001A\u00020\u000BH\u0016J\b\u0010\f\u001A\u00020\tH\u0016J\n\u0010\r\u001A\u0004\u0018\u00010\u000EH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/sqlite/db/framework/FrameworkSQLiteStatement;", "Landroidx/sqlite/db/framework/FrameworkSQLiteProgram;", "Landroidx/sqlite/db/SupportSQLiteStatement;", "delegate", "Landroid/database/sqlite/SQLiteStatement;", "(Landroid/database/sqlite/SQLiteStatement;)V", "execute", "", "executeInsert", "", "executeUpdateDelete", "", "simpleQueryForLong", "simpleQueryForString", "", "sqlite-framework_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class FrameworkSQLiteStatement extends FrameworkSQLiteProgram implements SupportSQLiteStatement {
    private final SQLiteStatement delegate;

    public FrameworkSQLiteStatement(SQLiteStatement sQLiteStatement0) {
        Intrinsics.checkNotNullParameter(sQLiteStatement0, "delegate");
        super(sQLiteStatement0);
        this.delegate = sQLiteStatement0;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public void execute() {
        this.delegate.execute();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public long executeInsert() {
        return this.delegate.executeInsert();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public int executeUpdateDelete() {
        return this.delegate.executeUpdateDelete();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public long simpleQueryForLong() {
        return this.delegate.simpleQueryForLong();
    }

    @Override  // androidx.sqlite.db.SupportSQLiteStatement
    public String simpleQueryForString() {
        return this.delegate.simpleQueryForString();
    }
}

