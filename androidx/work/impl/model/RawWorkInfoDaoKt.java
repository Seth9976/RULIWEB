package androidx.work.impl.model;

import androidx.sqlite.db.SupportSQLiteQuery;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A&\u0010\u0000\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b¨\u0006\t"}, d2 = {"getWorkInfoPojosFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Landroidx/work/WorkInfo;", "Landroidx/work/impl/model/RawWorkInfoDao;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "query", "Landroidx/sqlite/db/SupportSQLiteQuery;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class RawWorkInfoDaoKt {
    public static final Flow getWorkInfoPojosFlow(RawWorkInfoDao rawWorkInfoDao0, CoroutineDispatcher coroutineDispatcher0, SupportSQLiteQuery supportSQLiteQuery0) {
        Intrinsics.checkNotNullParameter(rawWorkInfoDao0, "<this>");
        Intrinsics.checkNotNullParameter(coroutineDispatcher0, "dispatcher");
        Intrinsics.checkNotNullParameter(supportSQLiteQuery0, "query");
        return WorkSpecDaoKt.dedup(rawWorkInfoDao0.getWorkInfoPojosFlow(supportSQLiteQuery0), coroutineDispatcher0);
    }
}

