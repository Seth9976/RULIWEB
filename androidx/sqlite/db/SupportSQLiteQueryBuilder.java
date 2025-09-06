package androidx.sqlite.db;

import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001D2\u00020\u0001:\u0001\u001DB\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u001D\u0010\b\u001A\u00020\u00002\u0010\u0010\b\u001A\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0006\u00A2\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001A\u00020\u0013J\u0006\u0010\n\u001A\u00020\u0000J\u0010\u0010\f\u001A\u00020\u00002\b\u0010\f\u001A\u0004\u0018\u00010\u0003J\u0010\u0010\r\u001A\u00020\u00002\b\u0010\r\u001A\u0004\u0018\u00010\u0003J\u000E\u0010\u000E\u001A\u00020\u00002\u0006\u0010\u000E\u001A\u00020\u0003J\u0010\u0010\u000F\u001A\u00020\u00002\b\u0010\u000F\u001A\u0004\u0018\u00010\u0003J)\u0010\u0010\u001A\u00020\u00002\b\u0010\u0010\u001A\u0004\u0018\u00010\u00032\u0012\u0010\u0005\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006\u00A2\u0006\u0002\u0010\u0014J\"\u0010\u0015\u001A\u00020\u0016*\u00060\u0017j\u0002`\u00182\u0006\u0010\u0019\u001A\u00020\u00032\b\u0010\u001A\u001A\u0004\u0018\u00010\u0003H\u0002J%\u0010\u001B\u001A\u00020\u0016*\u00060\u0017j\u0002`\u00182\u000E\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006H\u0002\u00A2\u0006\u0002\u0010\u001CR\u001C\u0010\u0005\u001A\u000E\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\u0007R\u001A\u0010\b\u001A\f\u0012\u0006\b\u0001\u0012\u00020\u0003\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\tR\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\f\u001A\u0004\u0018\u00010\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u0004\u0018\u00010\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000E\u001A\u0004\u0018\u00010\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001A\u0004\u0018\u00010\u0003X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001E"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "", "table", "", "(Ljava/lang/String;)V", "bindArgs", "", "[Ljava/lang/Object;", "columns", "[Ljava/lang/String;", "distinct", "", "groupBy", "having", "limit", "orderBy", "selection", "([Ljava/lang/String;)Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "create", "Landroidx/sqlite/db/SupportSQLiteQuery;", "(Ljava/lang/String;[Ljava/lang/Object;)Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "appendClause", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "name", "clause", "appendColumns", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)V", "Companion", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SupportSQLiteQueryBuilder {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0007R\u0016\u0010\u0003\u001A\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/sqlite/db/SupportSQLiteQueryBuilder$Companion;", "", "()V", "limitPattern", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "builder", "Landroidx/sqlite/db/SupportSQLiteQueryBuilder;", "tableName", "", "sqlite_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final SupportSQLiteQueryBuilder builder(String s) {
            Intrinsics.checkNotNullParameter(s, "tableName");
            return new SupportSQLiteQueryBuilder(s, null);
        }
    }

    public static final Companion Companion;
    private Object[] bindArgs;
    private String[] columns;
    private boolean distinct;
    private String groupBy;
    private String having;
    private String limit;
    private static final Pattern limitPattern;
    private String orderBy;
    private String selection;
    private final String table;

    static {
        SupportSQLiteQueryBuilder.Companion = new Companion(null);
        SupportSQLiteQueryBuilder.limitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");
    }

    private SupportSQLiteQueryBuilder(String s) {
        this.table = s;
    }

    public SupportSQLiteQueryBuilder(String s, DefaultConstructorMarker defaultConstructorMarker0) {
        this(s);
    }

    private final void appendClause(StringBuilder stringBuilder0, String s, String s1) {
        if(s1 != null && s1.length() != 0) {
            stringBuilder0.append(s);
            stringBuilder0.append(s1);
        }
    }

    private final void appendColumns(StringBuilder stringBuilder0, String[] arr_s) {
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            if(v > 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(s);
        }
        stringBuilder0.append(' ');
    }

    @JvmStatic
    public static final SupportSQLiteQueryBuilder builder(String s) {
        return SupportSQLiteQueryBuilder.Companion.builder(s);
    }

    public final SupportSQLiteQueryBuilder columns(String[] arr_s) {
        this.columns = arr_s;
        return this;
    }

    public final SupportSQLiteQuery create() {
        if((this.groupBy == null || this.groupBy.length() == 0) && (this.having != null && this.having.length() != 0)) {
            throw new IllegalArgumentException("HAVING clauses are only permitted when using a groupBy clause");
        }
        StringBuilder stringBuilder0 = new StringBuilder(120);
        stringBuilder0.append("SELECT ");
        if(this.distinct) {
            stringBuilder0.append("DISTINCT ");
        }
        String[] arr_s = this.columns;
        if(arr_s == null || arr_s.length == 0) {
            stringBuilder0.append("* ");
        }
        else {
            Intrinsics.checkNotNull(arr_s);
            this.appendColumns(stringBuilder0, arr_s);
        }
        stringBuilder0.append("FROM ");
        stringBuilder0.append(this.table);
        this.appendClause(stringBuilder0, " WHERE ", this.selection);
        this.appendClause(stringBuilder0, " GROUP BY ", this.groupBy);
        this.appendClause(stringBuilder0, " HAVING ", this.having);
        this.appendClause(stringBuilder0, " ORDER BY ", this.orderBy);
        this.appendClause(stringBuilder0, " LIMIT ", this.limit);
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder(capacity).…builderAction).toString()");
        return new SimpleSQLiteQuery(s, this.bindArgs);
    }

    public final SupportSQLiteQueryBuilder distinct() {
        this.distinct = true;
        return this;
    }

    public final SupportSQLiteQueryBuilder groupBy(String s) {
        this.groupBy = s;
        return this;
    }

    public final SupportSQLiteQueryBuilder having(String s) {
        this.having = s;
        return this;
    }

    public final SupportSQLiteQueryBuilder limit(String s) {
        Intrinsics.checkNotNullParameter(s, "limit");
        boolean z = SupportSQLiteQueryBuilder.limitPattern.matcher(s).matches();
        if(s.length() != 0 && !z) {
            throw new IllegalArgumentException(("invalid LIMIT clauses:" + s).toString());
        }
        this.limit = s;
        return this;
    }

    public final SupportSQLiteQueryBuilder orderBy(String s) {
        this.orderBy = s;
        return this;
    }

    public final SupportSQLiteQueryBuilder selection(String s, Object[] arr_object) {
        this.selection = s;
        this.bindArgs = arr_object;
        return this;
    }
}

