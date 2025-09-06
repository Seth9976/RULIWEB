package androidx.room.util;

import androidx.sqlite.db.SupportSQLiteDatabase;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0006\u0014\u0015\u0016\u0017\u0018\u0019B1\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nBA\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0010\b\u0002\u0010\u000B\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b¢\u0006\u0002\u0010\rJ\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\b\u0010\u0013\u001A\u00020\u0003H\u0016R\u001C\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000B\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Landroidx/room/util/TableInfo;", "", "name", "", "columns", "", "Landroidx/room/util/TableInfo$Column;", "foreignKeys", "", "Landroidx/room/util/TableInfo$ForeignKey;", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Set;)V", "indices", "Landroidx/room/util/TableInfo$Index;", "(Ljava/lang/String;Ljava/util/Map;Ljava/util/Set;Ljava/util/Set;)V", "equals", "", "other", "hashCode", "", "toString", "Column", "Companion", "CreatedFrom", "ForeignKey", "ForeignKeyWithSequence", "Index", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class TableInfo {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\'\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tB7\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000B\u001A\u00020\b¢\u0006\u0002\u0010\fJ\u0013\u0010\u0012\u001A\u00020\u00062\b\u0010\u0013\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u0012\u0010\u0014\u001A\u00020\b2\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003H\u0003J\b\u0010\u0015\u001A\u00020\bH\u0016J\b\u0010\u0016\u001A\u00020\u0003H\u0016R\u0016\u0010\r\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000E\u0010\u000FR\u0010\u0010\u000B\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001A\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/room/util/TableInfo$Column;", "", "name", "", "type", "notNull", "", "primaryKeyPosition", "", "(Ljava/lang/String;Ljava/lang/String;ZI)V", "defaultValue", "createdFrom", "(Ljava/lang/String;Ljava/lang/String;ZILjava/lang/String;I)V", "affinity", "getAffinity$annotations", "()V", "isPrimaryKey", "()Z", "equals", "other", "findAffinity", "hashCode", "toString", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Column {
        @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0002J\u001A\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\b\u001A\u0004\u0018\u00010\u0006H\u0007¨\u0006\t"}, d2 = {"Landroidx/room/util/TableInfo$Column$Companion;", "", "()V", "containsSurroundingParenthesis", "", "current", "", "defaultValueEquals", "other", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            private final boolean containsSurroundingParenthesis(String s) {
                if(s.length() == 0) {
                    return false;
                }
                int v = 0;
                int v1 = 0;
                for(int v2 = 0; v < s.length(); ++v2) {
                    int v3 = s.charAt(v);
                    if(v2 == 0 && v3 != 40) {
                        return false;
                    }
                    if(v3 == 40) {
                        ++v1;
                    }
                    else if(v3 == 41) {
                        --v1;
                        if(v1 == 0 && v2 != s.length() - 1) {
                            return false;
                        }
                    }
                    ++v;
                }
                return v1 == 0;
            }

            @JvmStatic
            public final boolean defaultValueEquals(String s, String s1) {
                Intrinsics.checkNotNullParameter(s, "current");
                if(Intrinsics.areEqual(s, s1)) {
                    return true;
                }
                if(this.containsSurroundingParenthesis(s)) {
                    String s2 = s.substring(1, s.length() - 1);
                    Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
                    return Intrinsics.areEqual(StringsKt.trim(s2).toString(), s1);
                }
                return false;
            }
        }

        public static final Companion Companion;
        public final int affinity;
        public final int createdFrom;
        public final String defaultValue;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        static {
            Column.Companion = new Companion(null);
        }

        @Deprecated(message = "Use {@link Column#Column(String, String, boolean, int, String, int)} instead.")
        public Column(String s, String s1, boolean z, int v) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "type");
            this(s, s1, z, v, null, 0);
        }

        public Column(String s, String s1, boolean z, int v, String s2, int v1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "type");
            super();
            this.name = s;
            this.type = s1;
            this.notNull = z;
            this.primaryKeyPosition = v;
            this.defaultValue = s2;
            this.createdFrom = v1;
            this.affinity = this.findAffinity(s1);
        }

        @JvmStatic
        public static final boolean defaultValueEquals(String s, String s1) {
            return Column.Companion.defaultValueEquals(s, s1);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof Column)) {
                return false;
            }
            if(this.primaryKeyPosition != ((Column)object0).primaryKeyPosition) {
                return false;
            }
            if(!Intrinsics.areEqual(this.name, ((Column)object0).name)) {
                return false;
            }
            if(this.notNull != ((Column)object0).notNull) {
                return false;
            }
            if(this.createdFrom == 1 && ((Column)object0).createdFrom == 2 && (this.defaultValue != null && !Column.Companion.defaultValueEquals(this.defaultValue, ((Column)object0).defaultValue))) {
                return false;
            }
            if(this.createdFrom == 2 && ((Column)object0).createdFrom == 1) {
                String s = ((Column)object0).defaultValue;
                if(s != null && !Column.Companion.defaultValueEquals(s, this.defaultValue)) {
                    return false;
                }
            }
            if(this.createdFrom != 0 && this.createdFrom == ((Column)object0).createdFrom) {
                String s1 = this.defaultValue;
                if(s1 != null) {
                    return Column.Companion.defaultValueEquals(s1, ((Column)object0).defaultValue) ? this.affinity == ((Column)object0).affinity : false;
                }
                return ((Column)object0).defaultValue == null ? this.affinity == ((Column)object0).affinity : false;
            }
            return this.affinity == ((Column)object0).affinity;
        }

        private final int findAffinity(String s) {
            if(s == null) {
                return 5;
            }
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s1 = s.toUpperCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toUpperCase(locale)");
            if(StringsKt.contains$default(s1, "INT", false, 2, null)) {
                return 3;
            }
            if(!StringsKt.contains$default(s1, "CHAR", false, 2, null) && !StringsKt.contains$default(s1, "CLOB", false, 2, null) && !StringsKt.contains$default(s1, "TEXT", false, 2, null)) {
                if(StringsKt.contains$default(s1, "BLOB", false, 2, null)) {
                    return 5;
                }
                return StringsKt.contains$default(s1, "REAL", false, 2, null) || StringsKt.contains$default(s1, "FLOA", false, 2, null) || StringsKt.contains$default(s1, "DOUB", false, 2, null) ? 4 : 1;
            }
            return 2;
        }

        public static void getAffinity$annotations() {
        }

        @Override
        public int hashCode() {
            int v = (this.name.hashCode() * 0x1F + this.affinity) * 0x1F;
            return this.notNull ? (v + 0x4CF) * 0x1F + this.primaryKeyPosition : (v + 0x4D5) * 0x1F + this.primaryKeyPosition;
        }

        public final boolean isPrimaryKey() {
            return this.primaryKeyPosition > 0;
        }

        @Override
        public String toString() {
            return "Column{name=\'" + this.name + "\', type=\'" + this.type + "\', affinity=\'" + this.affinity + "\', notNull=" + this.notNull + ", primaryKeyPosition=" + this.primaryKeyPosition + ", defaultValue=\'" + (this.defaultValue == null ? "undefined" : this.defaultValue) + "\'}";
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/room/util/TableInfo$Companion;", "", "()V", "CREATED_FROM_DATABASE", "", "CREATED_FROM_ENTITY", "CREATED_FROM_UNKNOWN", "read", "Landroidx/room/util/TableInfo;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class androidx.room.util.TableInfo.Companion {
        private androidx.room.util.TableInfo.Companion() {
        }

        public androidx.room.util.TableInfo.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final TableInfo read(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "database");
            Intrinsics.checkNotNullParameter(s, "tableName");
            return TableInfoKt.readTableInfo(supportSQLiteDatabase0, s);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\b\u0081\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/room/util/TableInfo$CreatedFrom;", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface CreatedFrom {
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0003\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\tJ\u0013\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\u0003H\u0016R\u0016\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/room/util/TableInfo$ForeignKey;", "", "referenceTable", "", "onDelete", "onUpdate", "columnNames", "", "referenceColumnNames", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "equals", "", "other", "hashCode", "", "toString", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ForeignKey {
        public final List columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String s, String s1, String s2, List list0, List list1) {
            Intrinsics.checkNotNullParameter(s, "referenceTable");
            Intrinsics.checkNotNullParameter(s1, "onDelete");
            Intrinsics.checkNotNullParameter(s2, "onUpdate");
            Intrinsics.checkNotNullParameter(list0, "columnNames");
            Intrinsics.checkNotNullParameter(list1, "referenceColumnNames");
            super();
            this.referenceTable = s;
            this.onDelete = s1;
            this.onUpdate = s2;
            this.columnNames = list0;
            this.referenceColumnNames = list1;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof ForeignKey)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.referenceTable, ((ForeignKey)object0).referenceTable)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.onDelete, ((ForeignKey)object0).onDelete)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.onUpdate, ((ForeignKey)object0).onUpdate)) {
                return false;
            }
            return Intrinsics.areEqual(this.columnNames, ((ForeignKey)object0).columnNames) ? Intrinsics.areEqual(this.referenceColumnNames, ((ForeignKey)object0).referenceColumnNames) : false;
        }

        @Override
        public int hashCode() {
            return (((this.referenceTable.hashCode() * 0x1F + this.onDelete.hashCode()) * 0x1F + this.onUpdate.hashCode()) * 0x1F + this.columnNames.hashCode()) * 0x1F + this.referenceColumnNames.hashCode();
        }

        @Override
        public String toString() {
            return "ForeignKey{referenceTable=\'" + this.referenceTable + "\', onDelete=\'" + this.onDelete + " +\', onUpdate=\'" + this.onUpdate + "\', columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + '}';
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u000B\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006¢\u0006\u0002\u0010\bJ\u0011\u0010\u000F\u001A\u00020\u00032\u0006\u0010\u0010\u001A\u00020\u0000H\u0096\u0002R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\n¨\u0006\u0011"}, d2 = {"Landroidx/room/util/TableInfo$ForeignKeyWithSequence;", "", "id", "", "sequence", "from", "", "to", "(IILjava/lang/String;Ljava/lang/String;)V", "getFrom", "()Ljava/lang/String;", "getId", "()I", "getSequence", "getTo", "compareTo", "other", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ForeignKeyWithSequence implements Comparable {
        private final String from;
        private final int id;
        private final int sequence;
        private final String to;

        public ForeignKeyWithSequence(int v, int v1, String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "from");
            Intrinsics.checkNotNullParameter(s1, "to");
            super();
            this.id = v;
            this.sequence = v1;
            this.from = s;
            this.to = s1;
        }

        public int compareTo(ForeignKeyWithSequence tableInfo$ForeignKeyWithSequence0) {
            Intrinsics.checkNotNullParameter(tableInfo$ForeignKeyWithSequence0, "other");
            int v = this.id - tableInfo$ForeignKeyWithSequence0.id;
            return v == 0 ? this.sequence - tableInfo$ForeignKeyWithSequence0.sequence : v;
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((ForeignKeyWithSequence)object0));
        }

        public final String getFrom() {
            return this.from;
        }

        public final int getId() {
            return this.id;
        }

        public final int getSequence() {
            return this.sequence;
        }

        public final String getTo() {
            return this.to;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B%\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bB1\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\nJ\u0013\u0010\u000B\u001A\u00020\u00052\b\u0010\f\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\u0003H\u0016R\u0016\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00030\u00078\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001A\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/room/util/TableInfo$Index;", "", "name", "", "unique", "", "columns", "", "(Ljava/lang/String;ZLjava/util/List;)V", "orders", "(Ljava/lang/String;ZLjava/util/List;Ljava/util/List;)V", "equals", "other", "hashCode", "", "toString", "Companion", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Index {
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/room/util/TableInfo$Index$Companion;", "", "()V", "DEFAULT_PREFIX", "", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class androidx.room.util.TableInfo.Index.Companion {
            private androidx.room.util.TableInfo.Index.Companion() {
            }

            public androidx.room.util.TableInfo.Index.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }
        }

        public static final androidx.room.util.TableInfo.Index.Companion Companion = null;
        public static final String DEFAULT_PREFIX = "index_";
        public final List columns;
        public final String name;
        public List orders;
        public final boolean unique;

        static {
            Index.Companion = new androidx.room.util.TableInfo.Index.Companion(null);
        }

        @Deprecated(message = "Use {@link #Index(String, boolean, List, List)}")
        public Index(String s, boolean z, List list0) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(list0, "columns");
            int v = list0.size();
            ArrayList arrayList0 = new ArrayList(v);
            for(int v1 = 0; v1 < v; ++v1) {
                arrayList0.add("ASC");
            }
            this(s, z, list0, arrayList0);
        }

        public Index(String s, boolean z, List list0, List list1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(list0, "columns");
            Intrinsics.checkNotNullParameter(list1, "orders");
            super();
            this.name = s;
            this.unique = z;
            this.columns = list0;
            this.orders = list1;
            Collection collection0 = list1;
            if(collection0.isEmpty()) {
                int v = list0.size();
                ArrayList arrayList0 = new ArrayList(v);
                for(int v1 = 0; v1 < v; ++v1) {
                    arrayList0.add("ASC");
                }
                collection0 = arrayList0;
            }
            this.orders = (List)collection0;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof Index)) {
                return false;
            }
            if(this.unique != ((Index)object0).unique) {
                return false;
            }
            if(!Intrinsics.areEqual(this.columns, ((Index)object0).columns)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.orders, ((Index)object0).orders)) {
                return false;
            }
            return StringsKt.startsWith$default(this.name, "index_", false, 2, null) ? StringsKt.startsWith$default(((Index)object0).name, "index_", false, 2, null) : Intrinsics.areEqual(this.name, ((Index)object0).name);
        }

        // 去混淆评级： 低(20)
        @Override
        public int hashCode() {
            return StringsKt.startsWith$default(this.name, "index_", false, 2, null) ? ((this.unique + 0x73D447D3) * 0x1F + this.columns.hashCode()) * 0x1F + this.orders.hashCode() : ((this.name.hashCode() * 0x1F + this.unique) * 0x1F + this.columns.hashCode()) * 0x1F + this.orders.hashCode();
        }

        @Override
        public String toString() {
            return "Index{name=\'" + this.name + "\', unique=" + this.unique + ", columns=" + this.columns + ", orders=" + this.orders + "\'}";
        }
    }

    public static final int CREATED_FROM_DATABASE = 2;
    public static final int CREATED_FROM_ENTITY = 1;
    public static final int CREATED_FROM_UNKNOWN;
    public static final androidx.room.util.TableInfo.Companion Companion;
    public final Map columns;
    public final Set foreignKeys;
    public final Set indices;
    public final String name;

    static {
        TableInfo.Companion = new androidx.room.util.TableInfo.Companion(null);
    }

    public TableInfo(String s, Map map0, Set set0) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(map0, "columns");
        Intrinsics.checkNotNullParameter(set0, "foreignKeys");
        this(s, map0, set0, SetsKt.emptySet());
    }

    public TableInfo(String s, Map map0, Set set0, Set set1) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(map0, "columns");
        Intrinsics.checkNotNullParameter(set0, "foreignKeys");
        super();
        this.name = s;
        this.columns = map0;
        this.foreignKeys = set0;
        this.indices = set1;
    }

    public TableInfo(String s, Map map0, Set set0, Set set1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            set1 = null;
        }
        this(s, map0, set0, set1);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof TableInfo)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.name, ((TableInfo)object0).name)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.columns, ((TableInfo)object0).columns)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.foreignKeys, ((TableInfo)object0).foreignKeys)) {
            return false;
        }
        Set set0 = this.indices;
        if(set0 != null) {
            Set set1 = ((TableInfo)object0).indices;
            return set1 == null ? true : Intrinsics.areEqual(set0, set1);
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (this.name.hashCode() * 0x1F + this.columns.hashCode()) * 0x1F + this.foreignKeys.hashCode();
    }

    @JvmStatic
    public static final TableInfo read(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
        return TableInfo.Companion.read(supportSQLiteDatabase0, s);
    }

    @Override
    public String toString() {
        return "TableInfo{name=\'" + this.name + "\', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + '}';
    }
}

