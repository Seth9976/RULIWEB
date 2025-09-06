package com.google.android.gms.common;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Map;

public final class zaa implements SuccessContinuation {
    public static final zaa zaa;

    static {
        zaa.zaa = new zaa();
    }

    @Override  // com.google.android.gms.tasks.SuccessContinuation
    public final Task then(Object object0) {
        Map map0 = (Map)object0;
        return Tasks.forResult(null);
    }
}

