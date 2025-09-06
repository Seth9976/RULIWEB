package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Base64;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.zip.Adler32;

public class JobInfoScheduler implements WorkScheduler {
    static final String ATTEMPT_NUMBER = "attemptNumber";
    static final String BACKEND_NAME = "backendName";
    static final String EVENT_PRIORITY = "priority";
    static final String EXTRAS = "extras";
    private static final String LOG_TAG = "JobInfoScheduler";
    private final SchedulerConfig config;
    private final Context context;
    private final EventStore eventStore;

    public JobInfoScheduler(Context context0, EventStore eventStore0, SchedulerConfig schedulerConfig0) {
        this.context = context0;
        this.eventStore = eventStore0;
        this.config = schedulerConfig0;
    }

    int getJobId(TransportContext transportContext0) {
        Adler32 adler320 = new Adler32();
        adler320.update("com.ruliweb.www.ruliapp".getBytes(Charset.forName("UTF-8")));
        adler320.update(transportContext0.getBackendName().getBytes(Charset.forName("UTF-8")));
        adler320.update(ByteBuffer.allocate(4).putInt(PriorityMapping.toInt(transportContext0.getPriority())).array());
        if(transportContext0.getExtras() != null) {
            adler320.update(transportContext0.getExtras());
        }
        return (int)adler320.getValue();
    }

    private boolean isJobServiceOn(JobScheduler jobScheduler0, int v, int v1) {
        for(Object object0: jobScheduler0.getAllPendingJobs()) {
            int v2 = ((JobInfo)object0).getExtras().getInt("attemptNumber");
            if(((JobInfo)object0).getId() == v) {
                return v2 >= v1;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext transportContext0, int v) {
        this.schedule(transportContext0, v, false);
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext transportContext0, int v, boolean z) {
        ComponentName componentName0 = new ComponentName(this.context, JobInfoSchedulerService.class);
        JobScheduler jobScheduler0 = (JobScheduler)this.context.getSystemService("jobscheduler");
        int v1 = this.getJobId(transportContext0);
        if(!z && this.isJobServiceOn(jobScheduler0, v1, v)) {
            Logging.d("JobInfoScheduler", "Upload for context %s is already scheduled. Returning...", transportContext0);
            return;
        }
        long v2 = this.eventStore.getNextCallTime(transportContext0);
        JobInfo.Builder jobInfo$Builder0 = new JobInfo.Builder(v1, componentName0);
        Priority priority0 = transportContext0.getPriority();
        JobInfo.Builder jobInfo$Builder1 = this.config.configureJob(jobInfo$Builder0, priority0, v2, v);
        PersistableBundle persistableBundle0 = new PersistableBundle();
        persistableBundle0.putInt("attemptNumber", v);
        persistableBundle0.putString("backendName", transportContext0.getBackendName());
        persistableBundle0.putInt("priority", PriorityMapping.toInt(transportContext0.getPriority()));
        if(transportContext0.getExtras() != null) {
            persistableBundle0.putString("extras", Base64.encodeToString(transportContext0.getExtras(), 0));
        }
        jobInfo$Builder1.setExtras(persistableBundle0);
        Priority priority1 = transportContext0.getPriority();
        Logging.d("JobInfoScheduler", "Scheduling upload for context %s with jobId=%d in %dms(Backend next call timestamp %d). Attempt %d", new Object[]{transportContext0, v1, this.config.getScheduleDelay(priority1, v2, v), v2, v});
        jobScheduler0.schedule(jobInfo$Builder1.build());
    }
}

