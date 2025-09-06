package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.util.Base64;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;

public class AlarmManagerScheduler implements WorkScheduler {
    static final String ATTEMPT_NUMBER = "attemptNumber";
    static final String BACKEND_NAME = "backendName";
    static final String EVENT_PRIORITY = "priority";
    static final String EXTRAS = "extras";
    private static final String LOG_TAG = "AlarmManagerScheduler";
    private AlarmManager alarmManager;
    private final Clock clock;
    private final SchedulerConfig config;
    private final Context context;
    private final EventStore eventStore;

    AlarmManagerScheduler(Context context0, EventStore eventStore0, AlarmManager alarmManager0, Clock clock0, SchedulerConfig schedulerConfig0) {
        this.context = context0;
        this.eventStore = eventStore0;
        this.alarmManager = alarmManager0;
        this.clock = clock0;
        this.config = schedulerConfig0;
    }

    public AlarmManagerScheduler(Context context0, EventStore eventStore0, Clock clock0, SchedulerConfig schedulerConfig0) {
        this(context0, eventStore0, ((AlarmManager)context0.getSystemService("alarm")), clock0, schedulerConfig0);
    }

    boolean isJobServiceOn(Intent intent0) {
        return PendingIntent.getBroadcast(this.context, 0, intent0, (Build.VERSION.SDK_INT < 23 ? 0x20000000 : 0x24000000)) != null;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext transportContext0, int v) {
        this.schedule(transportContext0, v, false);
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext transportContext0, int v, boolean z) {
        Uri.Builder uri$Builder0 = new Uri.Builder();
        uri$Builder0.appendQueryParameter("backendName", transportContext0.getBackendName());
        uri$Builder0.appendQueryParameter("priority", String.valueOf(PriorityMapping.toInt(transportContext0.getPriority())));
        if(transportContext0.getExtras() != null) {
            uri$Builder0.appendQueryParameter("extras", Base64.encodeToString(transportContext0.getExtras(), 0));
        }
        Intent intent0 = new Intent(this.context, AlarmManagerSchedulerBroadcastReceiver.class);
        intent0.setData(uri$Builder0.build());
        intent0.putExtra("attemptNumber", v);
        if(!z && this.isJobServiceOn(intent0)) {
            Logging.d("AlarmManagerScheduler", "Upload for context %s is already scheduled. Returning...", transportContext0);
            return;
        }
        long v1 = this.eventStore.getNextCallTime(transportContext0);
        Priority priority0 = transportContext0.getPriority();
        long v2 = this.config.getScheduleDelay(priority0, v1, v);
        Logging.d("AlarmManagerScheduler", "Scheduling upload for context %s in %dms(Backend next call timestamp %d). Attempt %d", new Object[]{transportContext0, v2, v1, v});
        PendingIntent pendingIntent0 = PendingIntent.getBroadcast(this.context, 0, intent0, (Build.VERSION.SDK_INT < 23 ? 0 : 0x4000000));
        this.alarmManager.set(3, this.clock.getTime() + v2, pendingIntent0);
    }
}

