package androidx.work;

import android.content.Context;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;

public final class WorkManagerInitializer implements Initializer {
    private static final String TAG;

    static {
        WorkManagerInitializer.TAG = "WM-WrkMgrInitializer";
    }

    public WorkManager create(Context context0) {
        Logger.get().debug("WM-WrkMgrInitializer", "Initializing WorkManager with default configuration.");
        WorkManager.initialize(context0, new Builder().build());
        return WorkManager.getInstance(context0);
    }

    @Override  // androidx.startup.Initializer
    public Object create(Context context0) {
        return this.create(context0);
    }

    @Override  // androidx.startup.Initializer
    public List dependencies() {
        return Collections.EMPTY_LIST;
    }
}

