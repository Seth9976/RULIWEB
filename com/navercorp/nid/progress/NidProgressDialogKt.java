package com.navercorp.nid.progress;

import android.app.Activity;
import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\u001A\n\u0010\u0000\u001A\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"isFinishing", "", "Landroid/content/Context;", "Nid-OAuth_release"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class NidProgressDialogKt {
    public static final boolean isFinishing(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "<this>");
        Activity activity0 = context0 instanceof Activity ? ((Activity)context0) : null;
        return activity0 != null && activity0.isFinishing();
    }
}

