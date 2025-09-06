package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001A\u00020\u0004H\'J\u0016\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001A\u00020\u0004H\'J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u0004H\'J\u0010\u0010\t\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u0004H\'J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000EÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/model/DependencyDao;", "", "getDependentWorkIds", "", "", "id", "getPrerequisites", "hasCompletedAllPrerequisites", "", "hasDependents", "insertDependency", "", "dependency", "Landroidx/work/impl/model/Dependency;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface DependencyDao {
    List getDependentWorkIds(String arg1);

    List getPrerequisites(String arg1);

    boolean hasCompletedAllPrerequisites(String arg1);

    boolean hasDependents(String arg1);

    void insertDependency(Dependency arg1);
}

