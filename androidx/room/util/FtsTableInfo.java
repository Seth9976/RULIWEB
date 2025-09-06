package androidx.room.util;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.io.Closeable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B%\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0003¢\u0006\u0002\u0010\u0007B)\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\tJ\u0013\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\u0003H\u0016R\u0016\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00030\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/room/util/FtsTableInfo;", "", "name", "", "columns", "", "createSql", "(Ljava/lang/String;Ljava/util/Set;Ljava/lang/String;)V", "options", "(Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;)V", "equals", "", "other", "hashCode", "", "toString", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class FtsTableInfo {
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\t\u001A\u00020\u0005H\u0007J\u0018\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0005H\u0007J\u001E\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0005H\u0002J\u001E\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0005H\u0002R\u0016\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0011"}, d2 = {"Landroidx/room/util/FtsTableInfo$Companion;", "", "()V", "FTS_OPTIONS", "", "", "[Ljava/lang/String;", "parseOptions", "", "createStatement", "read", "Landroidx/room/util/FtsTableInfo;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "readColumns", "readOptions", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final Set parseOptions(String s) {
            Intrinsics.checkNotNullParameter(s, "createStatement");
            if(s.length() == 0) {
                return SetsKt.emptySet();
            }
            String s1 = s.substring(StringsKt.indexOf$default(s, '(', 0, false, 6, null) + 1, StringsKt.lastIndexOf$default(s, ')', 0, false, 6, null));
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
            List list0 = new ArrayList();
            ArrayDeque arrayDeque0 = new ArrayDeque();
            int v = -1;
            int v1 = 0;
            for(int v2 = 0; v1 < s1.length(); ++v2) {
                int v3 = s1.charAt(v1);
                switch(v3) {
                    case 44: {
                        if(arrayDeque0.isEmpty()) {
                            String s2 = s1.substring(v + 1, v2);
                            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
                            CharSequence charSequence0 = s2;
                            int v4 = charSequence0.length() - 1;
                            int v5 = 0;
                            boolean z = false;
                            while(v5 <= v4) {
                                boolean z1 = Intrinsics.compare(charSequence0.charAt((z ? v4 : v5)), 0x20) <= 0;
                                if(z) {
                                    if(!z1) {
                                        break;
                                    }
                                    --v4;
                                }
                                else if(z1) {
                                    ++v5;
                                }
                                else {
                                    z = true;
                                }
                            }
                            list0.add(charSequence0.subSequence(v5, v4 + 1).toString());
                            v = v2;
                        }
                        break;
                    }
                    case 91: {
                        if(arrayDeque0.isEmpty()) {
                            arrayDeque0.push(Character.valueOf('['));
                        }
                        break;
                    }
                    case 93: {
                        if(!arrayDeque0.isEmpty()) {
                            Character character1 = (Character)arrayDeque0.peek();
                            if(character1 != null && character1.charValue() == 91) {
                                arrayDeque0.pop();
                            }
                        }
                        break;
                    }
                    case 34: 
                    case 39: 
                    case 0x60: {
                        if(arrayDeque0.isEmpty()) {
                            arrayDeque0.push(Character.valueOf(((char)v3)));
                        }
                        else {
                            Character character0 = (Character)arrayDeque0.peek();
                            if(character0 != null && character0.charValue() == v3) {
                                arrayDeque0.pop();
                            }
                        }
                    }
                }
                ++v1;
            }
            String s3 = s1.substring(v + 1);
            Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).substring(startIndex)");
            list0.add(StringsKt.trim(s3).toString());
            Collection collection0 = new ArrayList();
            for(Object object0: list0) {
                String s4 = (String)object0;
                String[] arr_s = FtsTableInfo.FTS_OPTIONS;
                for(int v6 = 0; v6 < arr_s.length; ++v6) {
                    if(StringsKt.startsWith$default(s4, arr_s[v6], false, 2, null)) {
                        collection0.add(object0);
                        break;
                    }
                }
            }
            return CollectionsKt.toSet(((List)collection0));
        }

        @JvmStatic
        public final FtsTableInfo read(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "database");
            Intrinsics.checkNotNullParameter(s, "tableName");
            return new FtsTableInfo(s, this.readColumns(supportSQLiteDatabase0, s), this.readOptions(supportSQLiteDatabase0, s));
        }

        private final Set readColumns(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
            Set set0 = SetsKt.createSetBuilder();
            Closeable closeable0 = supportSQLiteDatabase0.query("PRAGMA table_info(`" + s + "`)");
            try {
                if(((Cursor)closeable0).getColumnCount() > 0) {
                    int v = ((Cursor)closeable0).getColumnIndex("name");
                    while(((Cursor)closeable0).moveToNext()) {
                        String s1 = ((Cursor)closeable0).getString(v);
                        Intrinsics.checkNotNullExpressionValue(s1, "cursor.getString(nameIndex)");
                        set0.add(s1);
                    }
                }
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
            return SetsKt.build(set0);
        }

        private final Set readOptions(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
            String s1;
            Closeable closeable0 = supportSQLiteDatabase0.query("SELECT * FROM sqlite_master WHERE `name` = \'" + s + '\'');
            try {
                s1 = ((Cursor)closeable0).moveToFirst() ? ((Cursor)closeable0).getString(((Cursor)closeable0).getColumnIndexOrThrow("sql")) : "";
            }
            catch(Throwable throwable0) {
                CloseableKt.closeFinally(closeable0, throwable0);
                throw throwable0;
            }
            CloseableKt.closeFinally(closeable0, null);
            Intrinsics.checkNotNullExpressionValue(s1, "sql");
            return this.parseOptions(s1);
        }
    }

    public static final Companion Companion;
    private static final String[] FTS_OPTIONS;
    public final Set columns;
    public final String name;
    public final Set options;

    static {
        FtsTableInfo.Companion = new Companion(null);
        FtsTableInfo.FTS_OPTIONS = new String[]{"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    }

    public FtsTableInfo(String s, Set set0, String s1) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(set0, "columns");
        Intrinsics.checkNotNullParameter(s1, "createSql");
        this(s, set0, FtsTableInfo.Companion.parseOptions(s1));
    }

    public FtsTableInfo(String s, Set set0, Set set1) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(set0, "columns");
        Intrinsics.checkNotNullParameter(set1, "options");
        super();
        this.name = s;
        this.columns = set0;
        this.options = set1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof FtsTableInfo)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.name, ((FtsTableInfo)object0).name)) {
            return false;
        }
        return Intrinsics.areEqual(this.columns, ((FtsTableInfo)object0).columns) ? Intrinsics.areEqual(this.options, ((FtsTableInfo)object0).options) : false;
    }

    @Override
    public int hashCode() {
        return (this.name.hashCode() * 0x1F + this.columns.hashCode()) * 0x1F + this.options.hashCode();
    }

    @JvmStatic
    public static final Set parseOptions(String s) {
        return FtsTableInfo.Companion.parseOptions(s);
    }

    @JvmStatic
    public static final FtsTableInfo read(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
        return FtsTableInfo.Companion.read(supportSQLiteDatabase0, s);
    }

    @Override
    public String toString() {
        return "FtsTableInfo{name=\'" + this.name + "\', columns=" + this.columns + ", options=" + this.options + "\'}";
    }
}

