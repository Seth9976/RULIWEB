package androidx.sqlite.db;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B!\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0012\u0010\u0005\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u0010\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0014H\u0016R\u0014\u0010\t\u001A\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u001C\u0010\u0005\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000E\u001A\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010¨\u0006\u0016"}, d2 = {"Landroidx/sqlite/db/SimpleSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteQuery;", "query", "", "(Ljava/lang/String;)V", "bindArgs", "", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "argCount", "", "getArgCount", "()I", "[Ljava/lang/Object;", "sql", "getSql", "()Ljava/lang/String;", "bindTo", "", "statement", "Landroidx/sqlite/db/SupportSQLiteProgram;", "Companion", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0012\u0010\u0007\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\tJ\"\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0001H\u0002¨\u0006\r"}, d2 = {"Landroidx/sqlite/db/SimpleSQLiteQuery$Companion;", "", "()V", "bind", "", "statement", "Landroidx/sqlite/db/SupportSQLiteProgram;", "bindArgs", "", "(Landroidx/sqlite/db/SupportSQLiteProgram;[Ljava/lang/Object;)V", "index", "", "arg", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        private final void bind(SupportSQLiteProgram supportSQLiteProgram0, int v, Object object0) {
            if(object0 == null) {
                supportSQLiteProgram0.bindNull(v);
                return;
            }
            if(object0 instanceof byte[]) {
                supportSQLiteProgram0.bindBlob(v, ((byte[])object0));
                return;
            }
            if(object0 instanceof Float) {
                supportSQLiteProgram0.bindDouble(v, ((double)((Number)object0).floatValue()));
                return;
            }
            if(object0 instanceof Double) {
                supportSQLiteProgram0.bindDouble(v, ((Number)object0).doubleValue());
                return;
            }
            if(object0 instanceof Long) {
                supportSQLiteProgram0.bindLong(v, ((Number)object0).longValue());
                return;
            }
            if(object0 instanceof Integer) {
                supportSQLiteProgram0.bindLong(v, ((long)((Number)object0).intValue()));
                return;
            }
            if(object0 instanceof Short) {
                supportSQLiteProgram0.bindLong(v, ((long)((Number)object0).shortValue()));
                return;
            }
            if(object0 instanceof Byte) {
                supportSQLiteProgram0.bindLong(v, ((long)((Number)object0).byteValue()));
                return;
            }
            if(object0 instanceof String) {
                supportSQLiteProgram0.bindString(v, ((String)object0));
                return;
            }
            if(!(object0 instanceof Boolean)) {
                throw new IllegalArgumentException("Cannot bind " + object0 + " at index " + v + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
            }
            supportSQLiteProgram0.bindLong(v, (((Boolean)object0).booleanValue() ? 1L : 0L));
        }

        @JvmStatic
        public final void bind(SupportSQLiteProgram supportSQLiteProgram0, Object[] arr_object) {
            Intrinsics.checkNotNullParameter(supportSQLiteProgram0, "statement");
            if(arr_object != null) {
                int v = 0;
                while(v < arr_object.length) {
                    Object object0 = arr_object[v];
                    ++v;
                    this.bind(supportSQLiteProgram0, v, object0);
                }
            }
        }
    }

    public static final Companion Companion;
    private final Object[] bindArgs;
    private final String query;

    static {
        SimpleSQLiteQuery.Companion = new Companion(null);
    }

    public SimpleSQLiteQuery(String s) {
        Intrinsics.checkNotNullParameter(s, "query");
        this(s, null);
    }

    public SimpleSQLiteQuery(String s, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(s, "query");
        super();
        this.query = s;
        this.bindArgs = arr_object;
    }

    @JvmStatic
    public static final void bind(SupportSQLiteProgram supportSQLiteProgram0, Object[] arr_object) {
        SimpleSQLiteQuery.Companion.bind(supportSQLiteProgram0, arr_object);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram supportSQLiteProgram0) {
        Intrinsics.checkNotNullParameter(supportSQLiteProgram0, "statement");
        SimpleSQLiteQuery.Companion.bind(supportSQLiteProgram0, this.bindArgs);
    }

    @Override  // androidx.sqlite.db.SupportSQLiteQuery
    public int getArgCount() {
        return this.bindArgs == null ? 0 : this.bindArgs.length;
    }

    @Override  // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.query;
    }
}

