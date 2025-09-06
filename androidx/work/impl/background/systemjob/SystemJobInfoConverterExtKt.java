package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo.Builder;
import android.net.NetworkRequest;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0018\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¨\u0006\u0006"}, d2 = {"setRequiredNetworkRequest", "", "builder", "Landroid/app/job/JobInfo$Builder;", "networkRequest", "Landroid/net/NetworkRequest;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SystemJobInfoConverterExtKt {
    public static final void setRequiredNetworkRequest(JobInfo.Builder jobInfo$Builder0, NetworkRequest networkRequest0) {
        Intrinsics.checkNotNullParameter(jobInfo$Builder0, "builder");
        NetworkApi23..ExternalSyntheticApiModelOutline0.m(jobInfo$Builder0, networkRequest0);
    }
}

