package androidx.work.impl.diagnostics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.impl.workers.DiagnosticsWorker;

public class DiagnosticsReceiver extends BroadcastReceiver {
    private static final String TAG;

    static {
        DiagnosticsReceiver.TAG = "WM-DiagnosticsRcvr";
    }

    @Override  // android.content.BroadcastReceiver
    public void onReceive(Context context0, Intent intent0) {
        if(intent0 != null) {
            Logger.get().debug("WM-DiagnosticsRcvr", "Requesting diagnostics");
            try {
                WorkManager.getInstance(context0).enqueue(OneTimeWorkRequest.from(DiagnosticsWorker.class));
            }
            catch(IllegalStateException illegalStateException0) {
                Logger.get().error("WM-DiagnosticsRcvr", "WorkManager is not initialized", illegalStateException0);
            }
        }
    }
}

