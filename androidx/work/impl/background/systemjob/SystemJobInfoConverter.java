package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo.Builder;
import android.app.job.JobInfo.TriggerContentUri;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.net.NetworkRequest.Builder;
import android.net.NetworkRequest;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import androidx.work.BackoffPolicy;
import androidx.work.Clock;
import androidx.work.Constraints.ContentUriTrigger;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.NetworkType;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;

class SystemJobInfoConverter {
    static final String EXTRA_IS_PERIODIC = "EXTRA_IS_PERIODIC";
    static final String EXTRA_WORK_SPEC_GENERATION = "EXTRA_WORK_SPEC_GENERATION";
    static final String EXTRA_WORK_SPEC_ID = "EXTRA_WORK_SPEC_ID";
    private static final String TAG;
    private final Clock mClock;
    private final boolean mMarkImportantWhileForeground;
    private final ComponentName mWorkServiceComponent;

    static {
        SystemJobInfoConverter.TAG = "WM-SystemJobInfoConvert";
    }

    SystemJobInfoConverter(Context context0, Clock clock0, boolean z) {
        this.mClock = clock0;
        this.mWorkServiceComponent = new ComponentName(context0.getApplicationContext(), SystemJobService.class);
        this.mMarkImportantWhileForeground = z;
    }

    JobInfo convert(WorkSpec workSpec0, int v) {
        Constraints constraints0 = workSpec0.constraints;
        PersistableBundle persistableBundle0 = new PersistableBundle();
        persistableBundle0.putString("EXTRA_WORK_SPEC_ID", workSpec0.id);
        persistableBundle0.putInt("EXTRA_WORK_SPEC_GENERATION", workSpec0.getGeneration());
        persistableBundle0.putBoolean("EXTRA_IS_PERIODIC", workSpec0.isPeriodic());
        JobInfo.Builder jobInfo$Builder0 = new JobInfo.Builder(v, this.mWorkServiceComponent).setRequiresCharging(constraints0.requiresCharging()).setRequiresDeviceIdle(constraints0.requiresDeviceIdle()).setExtras(persistableBundle0);
        NetworkRequest networkRequest0 = constraints0.getRequiredNetworkRequest();
        if(Build.VERSION.SDK_INT < 28 || networkRequest0 == null) {
            SystemJobInfoConverter.setRequiredNetwork(jobInfo$Builder0, constraints0.getRequiredNetworkType());
        }
        else {
            SystemJobInfoConverterExtKt.setRequiredNetworkRequest(jobInfo$Builder0, networkRequest0);
        }
        boolean z = false;
        if(!constraints0.requiresDeviceIdle()) {
            jobInfo$Builder0.setBackoffCriteria(workSpec0.backoffDelayDuration, (workSpec0.backoffPolicy == BackoffPolicy.LINEAR ? 0 : 1));
        }
        long v1 = Math.max(workSpec0.calculateNextRunTime() - this.mClock.currentTimeMillis(), 0L);
        if(Build.VERSION.SDK_INT <= 28) {
            jobInfo$Builder0.setMinimumLatency(v1);
        }
        else if(v1 > 0L) {
            jobInfo$Builder0.setMinimumLatency(v1);
        }
        else if(!workSpec0.expedited && this.mMarkImportantWhileForeground) {
            NetworkApi23..ExternalSyntheticApiModelOutline0.m$2(jobInfo$Builder0, true);
        }
        if(Build.VERSION.SDK_INT >= 24 && constraints0.hasContentUriTriggers()) {
            for(Object object0: constraints0.getContentUriTriggers()) {
                NetworkApi23..ExternalSyntheticApiModelOutline0.m(jobInfo$Builder0, SystemJobInfoConverter.convertContentUriTrigger(((ContentUriTrigger)object0)));
            }
            NetworkApi23..ExternalSyntheticApiModelOutline0.m(jobInfo$Builder0, constraints0.getContentTriggerUpdateDelayMillis());
            NetworkApi23..ExternalSyntheticApiModelOutline0.m$1(jobInfo$Builder0, constraints0.getContentTriggerMaxDelayMillis());
        }
        jobInfo$Builder0.setPersisted(false);
        if(Build.VERSION.SDK_INT >= 26) {
            NetworkApi23..ExternalSyntheticApiModelOutline0.m$3(jobInfo$Builder0, constraints0.requiresBatteryNotLow());
            NetworkApi23..ExternalSyntheticApiModelOutline0.m(jobInfo$Builder0, constraints0.requiresStorageNotLow());
        }
        boolean z1 = workSpec0.runAttemptCount > 0;
        if(v1 > 0L) {
            z = true;
        }
        if(Build.VERSION.SDK_INT >= 0x1F && workSpec0.expedited && !z1 && !z) {
            NetworkApi23..ExternalSyntheticApiModelOutline0.m$1(jobInfo$Builder0, true);
        }
        if(Build.VERSION.SDK_INT >= 35) {
            String s = workSpec0.getTraceTag();
            if(s != null) {
                NetworkApi23..ExternalSyntheticApiModelOutline0.m(jobInfo$Builder0, s);
            }
        }
        return jobInfo$Builder0.build();
    }

    private static JobInfo.TriggerContentUri convertContentUriTrigger(ContentUriTrigger constraints$ContentUriTrigger0) {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(constraints$ContentUriTrigger0.getUri(), ((int)constraints$ContentUriTrigger0.isTriggeredForDescendants()));
    }

    static int convertNetworkType(NetworkType networkType0) {
        switch(androidx.work.impl.background.systemjob.SystemJobInfoConverter.1.$SwitchMap$androidx$work$NetworkType[networkType0.ordinal()]) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                if(Build.VERSION.SDK_INT >= 24) {
                    return 3;
                }
                break;
            }
            case 5: {
                if(Build.VERSION.SDK_INT >= 26) {
                    return 4;
                }
            }
        }
        Logger.get().debug("WM-SystemJobInfoConvert", "API version too low. Cannot convert network type value " + networkType0);
        return 1;
    }

    static void setRequiredNetwork(JobInfo.Builder jobInfo$Builder0, NetworkType networkType0) {
        if(Build.VERSION.SDK_INT >= 30 && networkType0 == NetworkType.TEMPORARILY_UNMETERED) {
            NetworkApi23..ExternalSyntheticApiModelOutline0.m(jobInfo$Builder0, new NetworkRequest.Builder().addCapability(25).build());
            return;
        }
        jobInfo$Builder0.setRequiredNetworkType(SystemJobInfoConverter.convertNetworkType(networkType0));
    }
}

