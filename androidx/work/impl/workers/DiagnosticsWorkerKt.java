package androidx.work.impl.workers;

import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.model.WorkTagDao;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001A/\u0010\u0002\u001A\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00012\b\u0010\u0006\u001A\u0004\u0018\u00010\u00072\u0006\u0010\b\u001A\u00020\u0001H\u0002¢\u0006\u0002\u0010\t\u001A.\u0010\n\u001A\u00020\u00012\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00040\u0012H\u0002\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"TAG", "", "workSpecRow", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "name", "systemId", "", "tags", "(Landroidx/work/impl/model/WorkSpec;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Ljava/lang/String;", "workSpecRows", "workNameDao", "Landroidx/work/impl/model/WorkNameDao;", "workTagDao", "Landroidx/work/impl/model/WorkTagDao;", "systemIdInfoDao", "Landroidx/work/impl/model/SystemIdInfoDao;", "workSpecs", "", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DiagnosticsWorkerKt {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-DiagnosticsWrkr", "tagWithPrefix(\"DiagnosticsWrkr\")");
        DiagnosticsWorkerKt.TAG = "WM-DiagnosticsWrkr";
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器

    public static final String access$workSpecRows(WorkNameDao workNameDao0, WorkTagDao workTagDao0, SystemIdInfoDao systemIdInfoDao0, List list0) {
        return DiagnosticsWorkerKt.workSpecRows(workNameDao0, workTagDao0, systemIdInfoDao0, list0);
    }

    private static final String workSpecRow(WorkSpec workSpec0, String s, Integer integer0, String s1) {
        return "\n" + workSpec0.id + "\t " + workSpec0.workerClassName + "\t " + integer0 + "\t " + workSpec0.state.name() + "\t " + s + "\t " + s1 + '\t';
    }

    private static final String workSpecRows(WorkNameDao workNameDao0, WorkTagDao workTagDao0, SystemIdInfoDao systemIdInfoDao0, List list0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("\n Id \t Class Name\t Job Id\t State\t Unique Name\t Tags\t");
        for(Object object0: list0) {
            SystemIdInfo systemIdInfo0 = systemIdInfoDao0.getSystemIdInfo(WorkSpecKt.generationalId(((WorkSpec)object0)));
            stringBuilder0.append(DiagnosticsWorkerKt.workSpecRow(((WorkSpec)object0), CollectionsKt.joinToString$default(workNameDao0.getNamesForWorkSpecId(((WorkSpec)object0).id), ",", null, null, 0, null, null, 62, null), (systemIdInfo0 == null ? null : systemIdInfo0.systemId), CollectionsKt.joinToString$default(workTagDao0.getTagsForWorkSpecId(((WorkSpec)object0).id), ",", null, null, 0, null, null, 62, null)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }
}

