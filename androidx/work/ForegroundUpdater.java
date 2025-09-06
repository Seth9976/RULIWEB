package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

public interface ForegroundUpdater {
    ListenableFuture setForegroundAsync(Context arg1, UUID arg2, ForegroundInfo arg3);
}

