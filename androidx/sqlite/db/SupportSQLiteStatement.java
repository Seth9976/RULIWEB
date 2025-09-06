package androidx.sqlite.db;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&J\b\u0010\u0004\u001A\u00020\u0005H&J\b\u0010\u0006\u001A\u00020\u0007H&J\b\u0010\b\u001A\u00020\u0005H&J\n\u0010\t\u001A\u0004\u0018\u00010\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000BÀ\u0006\u0001"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteStatement;", "Landroidx/sqlite/db/SupportSQLiteProgram;", "execute", "", "executeInsert", "", "executeUpdateDelete", "", "simpleQueryForLong", "simpleQueryForString", "", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SupportSQLiteStatement extends SupportSQLiteProgram {
    void execute();

    long executeInsert();

    int executeUpdateDelete();

    long simpleQueryForLong();

    String simpleQueryForString();
}

