package androidx.work.impl.model;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\'J\u0016\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\u0004\u001A\u00020\u0005H\'J\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00050\u00072\u0006\u0010\t\u001A\u00020\u0005H\'J\u0010\u0010\n\u001A\u00020\u00032\u0006\u0010\u000B\u001A\u00020\fH\'J\u001E\u0010\r\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00050\u000FH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0003"}, d2 = {"Landroidx/work/impl/model/WorkTagDao;", "", "deleteByWorkSpecId", "", "id", "", "getTagsForWorkSpecId", "", "getWorkSpecIdsWithTag", "tag", "insert", "workTag", "Landroidx/work/impl/model/WorkTag;", "insertTags", "tags", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface WorkTagDao {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated
        public static void insertTags(WorkTagDao workTagDao0, String s, Set set0) {
            Intrinsics.checkNotNullParameter(s, "id");
            Intrinsics.checkNotNullParameter(set0, "tags");
            WorkTagDao.-CC.$default$insertTags(workTagDao0, s, set0);
        }
    }

    void deleteByWorkSpecId(String arg1);

    List getTagsForWorkSpecId(String arg1);

    List getWorkSpecIdsWithTag(String arg1);

    void insert(WorkTag arg1);

    void insertTags(String arg1, Set arg2);
}

