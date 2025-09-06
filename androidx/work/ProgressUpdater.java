package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

public interface ProgressUpdater {
    ListenableFuture updateProgress(Context arg1, UUID arg2, Data arg3);
}

