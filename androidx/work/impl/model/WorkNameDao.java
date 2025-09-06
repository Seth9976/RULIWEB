package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001A\u00020\u0004H\'J\u0016\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0007\u001A\u00020\u0004H\'J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/model/WorkNameDao;", "", "getNamesForWorkSpecId", "", "", "workSpecId", "getWorkSpecIdsWithName", "name", "insert", "", "workName", "Landroidx/work/impl/model/WorkName;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface WorkNameDao {
    List getNamesForWorkSpecId(String arg1);

    List getWorkSpecIdsWithName(String arg1);

    void insert(WorkName arg1);
}

