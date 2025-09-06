package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;

public final class JobInfoSchedulerService..ExternalSyntheticLambda0 implements Runnable {
    public final JobInfoSchedulerService f$0;
    public final JobParameters f$1;

    public JobInfoSchedulerService..ExternalSyntheticLambda0(JobInfoSchedulerService jobInfoSchedulerService0, JobParameters jobParameters0) {
        this.f$0 = jobInfoSchedulerService0;
        this.f$1 = jobParameters0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$onStartJob$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-JobInfoSchedulerService(this.f$1);
    }
}

