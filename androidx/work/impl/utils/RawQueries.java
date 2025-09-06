package androidx.work.impl.utils;

import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.WorkInfo.State;
import androidx.work.WorkQuery;
import androidx.work.impl.model.WorkTypeConverters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u001C\u0010\u0000\u001A\u00020\u00012\n\u0010\u0002\u001A\u00060\u0003j\u0002`\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0002\u001A\n\u0010\u0007\u001A\u00020\b*\u00020\t¨\u0006\n"}, d2 = {"bindings", "", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "count", "", "toRawQuery", "Landroidx/sqlite/db/SupportSQLiteQuery;", "Landroidx/work/WorkQuery;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class RawQueries {
    private static final void bindings(StringBuilder stringBuilder0, int v) {
        if(v <= 0) {
            return;
        }
        ArrayList arrayList0 = new ArrayList(v);
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList0.add("?");
        }
        stringBuilder0.append(CollectionsKt.joinToString$default(arrayList0, ",", null, null, 0, null, null, 62, null));
    }

    public static final SupportSQLiteQuery toRawQuery(WorkQuery workQuery0) {
        String s1;
        Intrinsics.checkNotNullParameter(workQuery0, "<this>");
        List list0 = new ArrayList();
        StringBuilder stringBuilder0 = new StringBuilder("SELECT * FROM workspec");
        String s = " AND";
        if(workQuery0.getStates().isEmpty()) {
            s1 = " WHERE";
        }
        else {
            Iterable iterable0 = workQuery0.getStates();
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
            for(Object object0: iterable0) {
                arrayList0.add(WorkTypeConverters.stateToInt(((State)object0)));
            }
            stringBuilder0.append(" WHERE state IN (");
            RawQueries.bindings(stringBuilder0, arrayList0.size());
            stringBuilder0.append(")");
            list0.addAll(arrayList0);
            s1 = " AND";
        }
        if(!workQuery0.getIds().isEmpty()) {
            Iterable iterable1 = workQuery0.getIds();
            ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable1, 10));
            for(Object object1: iterable1) {
                arrayList1.add(((UUID)object1).toString());
            }
            stringBuilder0.append(s1 + " id IN (");
            RawQueries.bindings(stringBuilder0, workQuery0.getIds().size());
            stringBuilder0.append(")");
            list0.addAll(arrayList1);
            s1 = " AND";
        }
        if(workQuery0.getTags().isEmpty()) {
            s = s1;
        }
        else {
            stringBuilder0.append(s1 + " id IN (SELECT work_spec_id FROM worktag WHERE tag IN (");
            RawQueries.bindings(stringBuilder0, workQuery0.getTags().size());
            stringBuilder0.append("))");
            list0.addAll(workQuery0.getTags());
        }
        if(!workQuery0.getUniqueWorkNames().isEmpty()) {
            stringBuilder0.append(s + " id IN (SELECT work_spec_id FROM workname WHERE name IN (");
            RawQueries.bindings(stringBuilder0, workQuery0.getUniqueWorkNames().size());
            stringBuilder0.append("))");
            list0.addAll(workQuery0.getUniqueWorkNames());
        }
        stringBuilder0.append(";");
        String s2 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s2, "builder.toString()");
        return new SimpleSQLiteQuery(s2, list0.toArray(new Object[0]));
    }
}

