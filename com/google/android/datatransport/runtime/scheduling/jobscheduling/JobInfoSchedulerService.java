package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Base64;
import com.google.android.datatransport.runtime.TransportContext.Builder;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.util.PriorityMapping;

public class JobInfoSchedulerService extends JobService {
    // 检测为 Lambda 实现
    void lambda$onStartJob$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-JobInfoSchedulerService(JobParameters jobParameters0) [...]

    @Override  // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters0) {
        String s = jobParameters0.getExtras().getString("backendName");
        String s1 = jobParameters0.getExtras().getString("extras");
        int v = jobParameters0.getExtras().getInt("priority");
        int v1 = jobParameters0.getExtras().getInt("attemptNumber");
        TransportRuntime.initialize(this.getApplicationContext());
        Builder transportContext$Builder0 = TransportContext.builder().setBackendName(s).setPriority(PriorityMapping.valueOf(v));
        if(s1 != null) {
            transportContext$Builder0.setExtras(Base64.decode(s1, 0));
        }
        TransportRuntime.getInstance().getUploader().upload(transportContext$Builder0.build(), v1, () -> this.jobFinished(jobParameters0, false));
        return true;
    }

    @Override  // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters0) {
        return true;
    }
}

