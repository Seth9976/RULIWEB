package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0016J\u001A\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\'J\u000E\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00070\u000BH\'J\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u0003H\'J\u0010\u0010\u000F\u001A\u00020\r2\u0006\u0010\u0004\u001A\u00020\u0005H\u0016J\u0010\u0010\u000F\u001A\u00020\r2\u0006\u0010\u0006\u001A\u00020\u0007H\'J\u0018\u0010\u000F\u001A\u00020\r2\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/work/impl/model/SystemIdInfoDao;", "", "getSystemIdInfo", "Landroidx/work/impl/model/SystemIdInfo;", "id", "Landroidx/work/impl/model/WorkGenerationalId;", "workSpecId", "", "generation", "", "getWorkSpecIds", "", "insertSystemIdInfo", "", "systemIdInfo", "removeSystemIdInfo", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SystemIdInfoDao {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated
        public static SystemIdInfo getSystemIdInfo(SystemIdInfoDao systemIdInfoDao0, WorkGenerationalId workGenerationalId0) {
            Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
            return SystemIdInfoDao.-CC.$default$getSystemIdInfo(systemIdInfoDao0, workGenerationalId0);
        }

        @Deprecated
        public static void removeSystemIdInfo(SystemIdInfoDao systemIdInfoDao0, WorkGenerationalId workGenerationalId0) {
            Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
            SystemIdInfoDao.-CC.$default$removeSystemIdInfo(systemIdInfoDao0, workGenerationalId0);
        }
    }

    SystemIdInfo getSystemIdInfo(WorkGenerationalId arg1);

    SystemIdInfo getSystemIdInfo(String arg1, int arg2);

    List getWorkSpecIds();

    void insertSystemIdInfo(SystemIdInfo arg1);

    void removeSystemIdInfo(WorkGenerationalId arg1);

    void removeSystemIdInfo(String arg1);

    void removeSystemIdInfo(String arg1, int arg2);
}

