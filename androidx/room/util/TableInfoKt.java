package androidx.room.util;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001A$\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0002H\u0002\u001A\u0016\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001A\u00020\u000BH\u0002\u001A\u001E\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\r2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0002H\u0002\u001A\"\u0010\u000F\u001A\u0004\u0018\u00010\u00102\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0011\u001A\u00020\u00022\u0006\u0010\u0012\u001A\u00020\u0013H\u0002\u001A \u0010\u0014\u001A\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r2\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0002H\u0002\u001A\u0018\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0002H\u0000¨\u0006\u0017"}, d2 = {"readColumns", "", "", "Landroidx/room/util/TableInfo$Column;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "readForeignKeyFieldMappings", "", "Landroidx/room/util/TableInfo$ForeignKeyWithSequence;", "cursor", "Landroid/database/Cursor;", "readForeignKeys", "", "Landroidx/room/util/TableInfo$ForeignKey;", "readIndex", "Landroidx/room/util/TableInfo$Index;", "name", "unique", "", "readIndices", "readTableInfo", "Landroidx/room/util/TableInfo;", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TableInfoKt {
    private static final Map readColumns(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
        Map map2;
        Map map1;
        Closeable closeable0 = supportSQLiteDatabase0.query("PRAGMA table_info(`" + s + "`)");
        try {
            if(((Cursor)closeable0).getColumnCount() > 0) {
                int v = ((Cursor)closeable0).getColumnIndex("name");
                int v1 = ((Cursor)closeable0).getColumnIndex("type");
                int v2 = ((Cursor)closeable0).getColumnIndex("notnull");
                int v3 = ((Cursor)closeable0).getColumnIndex("pk");
                int v4 = ((Cursor)closeable0).getColumnIndex("dflt_value");
                Map map0 = MapsKt.createMapBuilder();
                while(((Cursor)closeable0).moveToNext()) {
                    String s1 = ((Cursor)closeable0).getString(v);
                    String s2 = ((Cursor)closeable0).getString(v1);
                    boolean z = ((Cursor)closeable0).getInt(v2) != 0;
                    int v5 = ((Cursor)closeable0).getInt(v3);
                    String s3 = ((Cursor)closeable0).getString(v4);
                    Intrinsics.checkNotNullExpressionValue(s1, "name");
                    Intrinsics.checkNotNullExpressionValue(s2, "type");
                    map0.put(s1, new Column(s1, s2, z, v5, s3, 2));
                }
                map1 = MapsKt.build(map0);
                goto label_19;
            }
            goto label_21;
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
    label_19:
        CloseableKt.closeFinally(closeable0, null);
        return map1;
        try {
        label_21:
            map2 = MapsKt.emptyMap();
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return map2;
    }

    private static final List readForeignKeyFieldMappings(Cursor cursor0) {
        int v = cursor0.getColumnIndex("id");
        int v1 = cursor0.getColumnIndex("seq");
        int v2 = cursor0.getColumnIndex("from");
        int v3 = cursor0.getColumnIndex("to");
        List list0 = CollectionsKt.createListBuilder();
        while(cursor0.moveToNext()) {
            int v4 = cursor0.getInt(v);
            int v5 = cursor0.getInt(v1);
            String s = cursor0.getString(v2);
            Intrinsics.checkNotNullExpressionValue(s, "cursor.getString(fromColumnIndex)");
            String s1 = cursor0.getString(v3);
            Intrinsics.checkNotNullExpressionValue(s1, "cursor.getString(toColumnIndex)");
            list0.add(new ForeignKeyWithSequence(v4, v5, s, s1));
        }
        return CollectionsKt.sorted(CollectionsKt.build(list0));
    }

    private static final Set readForeignKeys(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
        Set set1;
        Closeable closeable0 = supportSQLiteDatabase0.query("PRAGMA foreign_key_list(`" + s + "`)");
        try {
            int v = ((Cursor)closeable0).getColumnIndex("id");
            int v1 = ((Cursor)closeable0).getColumnIndex("seq");
            int v2 = ((Cursor)closeable0).getColumnIndex("table");
            int v3 = ((Cursor)closeable0).getColumnIndex("on_delete");
            int v4 = ((Cursor)closeable0).getColumnIndex("on_update");
            List list0 = TableInfoKt.readForeignKeyFieldMappings(((Cursor)closeable0));
            ((Cursor)closeable0).moveToPosition(-1);
            Set set0 = SetsKt.createSetBuilder();
            while(((Cursor)closeable0).moveToNext()) {
                if(((Cursor)closeable0).getInt(v1) == 0) {
                    int v5 = ((Cursor)closeable0).getInt(v);
                    List list1 = new ArrayList();
                    List list2 = new ArrayList();
                    Collection collection0 = new ArrayList();
                    for(Object object0: list0) {
                        if(((ForeignKeyWithSequence)object0).getId() == v5) {
                            collection0.add(object0);
                        }
                    }
                    for(Object object1: ((List)collection0)) {
                        list1.add(((ForeignKeyWithSequence)object1).getFrom());
                        list2.add(((ForeignKeyWithSequence)object1).getTo());
                    }
                    String s1 = ((Cursor)closeable0).getString(v2);
                    Intrinsics.checkNotNullExpressionValue(s1, "cursor.getString(tableColumnIndex)");
                    String s2 = ((Cursor)closeable0).getString(v3);
                    Intrinsics.checkNotNullExpressionValue(s2, "cursor.getString(onDeleteColumnIndex)");
                    String s3 = ((Cursor)closeable0).getString(v4);
                    Intrinsics.checkNotNullExpressionValue(s3, "cursor.getString(onUpdateColumnIndex)");
                    set0.add(new ForeignKey(s1, s2, s3, list1, list2));
                }
            }
            set1 = SetsKt.build(set0);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return set1;
    }

    private static final Index readIndex(SupportSQLiteDatabase supportSQLiteDatabase0, String s, boolean z) {
        Index tableInfo$Index0;
        Closeable closeable0 = supportSQLiteDatabase0.query("PRAGMA index_xinfo(`" + s + "`)");
        try {
            int v = ((Cursor)closeable0).getColumnIndex("seqno");
            int v1 = ((Cursor)closeable0).getColumnIndex("cid");
            int v2 = ((Cursor)closeable0).getColumnIndex("name");
            int v3 = ((Cursor)closeable0).getColumnIndex("desc");
            if(v != -1 && v1 != -1 && v2 != -1 && v3 != -1) {
                TreeMap treeMap0 = new TreeMap();
                TreeMap treeMap1 = new TreeMap();
                while(((Cursor)closeable0).moveToNext()) {
                    if(((Cursor)closeable0).getInt(v1) >= 0) {
                        int v4 = ((Cursor)closeable0).getInt(v);
                        String s1 = ((Cursor)closeable0).getString(v2);
                        String s2 = ((Cursor)closeable0).getInt(v3) <= 0 ? "ASC" : "DESC";
                        Intrinsics.checkNotNullExpressionValue(s1, "columnName");
                        treeMap0.put(v4, s1);
                        treeMap1.put(v4, s2);
                    }
                }
                Collection collection0 = treeMap0.values();
                Intrinsics.checkNotNullExpressionValue(collection0, "columnsMap.values");
                List list0 = CollectionsKt.toList(collection0);
                Collection collection1 = treeMap1.values();
                Intrinsics.checkNotNullExpressionValue(collection1, "ordersMap.values");
                tableInfo$Index0 = new Index(s, z, list0, CollectionsKt.toList(collection1));
                goto label_27;
            }
            goto label_29;
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
    label_27:
        CloseableKt.closeFinally(closeable0, null);
        return tableInfo$Index0;
    label_29:
        CloseableKt.closeFinally(closeable0, null);
        return null;
    }

    private static final Set readIndices(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
        Set set1;
        Set set0;
        Closeable closeable0 = supportSQLiteDatabase0.query("PRAGMA index_list(`" + s + "`)");
        try {
            int v = ((Cursor)closeable0).getColumnIndex("name");
            int v1 = ((Cursor)closeable0).getColumnIndex("origin");
            int v2 = ((Cursor)closeable0).getColumnIndex("unique");
            if(v != -1 && v1 != -1 && v2 != -1) {
                set0 = SetsKt.createSetBuilder();
                while(true) {
                    if(!((Cursor)closeable0).moveToNext()) {
                        goto label_17;
                    }
                    if(Intrinsics.areEqual("c", ((Cursor)closeable0).getString(v1))) {
                        String s1 = ((Cursor)closeable0).getString(v);
                        boolean z = ((Cursor)closeable0).getInt(v2) == 1;
                        Intrinsics.checkNotNullExpressionValue(s1, "name");
                        Index tableInfo$Index0 = TableInfoKt.readIndex(supportSQLiteDatabase0, s1, z);
                        if(tableInfo$Index0 == null) {
                            goto label_15;
                        }
                        set0.add(tableInfo$Index0);
                    }
                }
            }
            goto label_24;
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
    label_15:
        CloseableKt.closeFinally(closeable0, null);
        return null;
        try {
        label_17:
            set1 = SetsKt.build(set0);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return set1;
    label_24:
        CloseableKt.closeFinally(closeable0, null);
        return null;
    }

    public static final TableInfo readTableInfo(SupportSQLiteDatabase supportSQLiteDatabase0, String s) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase0, "database");
        Intrinsics.checkNotNullParameter(s, "tableName");
        return new TableInfo(s, TableInfoKt.readColumns(supportSQLiteDatabase0, s), TableInfoKt.readForeignKeys(supportSQLiteDatabase0, s), TableInfoKt.readIndices(supportSQLiteDatabase0, s));
    }
}

