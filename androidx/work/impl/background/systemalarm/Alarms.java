package androidx.work.impl.background.systemalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.IdGenerator;

class Alarms {
    private static final String TAG;

    static {
        Alarms.TAG = "WM-Alarms";
    }

    public static void cancelAlarm(Context context0, WorkDatabase workDatabase0, WorkGenerationalId workGenerationalId0) {
        SystemIdInfoDao systemIdInfoDao0 = workDatabase0.systemIdInfoDao();
        SystemIdInfo systemIdInfo0 = systemIdInfoDao0.getSystemIdInfo(workGenerationalId0);
        if(systemIdInfo0 != null) {
            Alarms.cancelExactAlarm(context0, workGenerationalId0, systemIdInfo0.systemId);
            Logger.get().debug("WM-Alarms", "Removing SystemIdInfo for workSpecId (" + workGenerationalId0 + ")");
            systemIdInfoDao0.removeSystemIdInfo(workGenerationalId0);
        }
    }

    private static void cancelExactAlarm(Context context0, WorkGenerationalId workGenerationalId0, int v) {
        AlarmManager alarmManager0 = (AlarmManager)context0.getSystemService("alarm");
        PendingIntent pendingIntent0 = PendingIntent.getService(context0, v, CommandHandler.createDelayMetIntent(context0, workGenerationalId0), (Build.VERSION.SDK_INT < 23 ? 0x20000000 : 0x24000000));
        if(pendingIntent0 != null && alarmManager0 != null) {
            Logger.get().debug("WM-Alarms", "Cancelling existing alarm with (workSpecId, systemId) (" + workGenerationalId0 + ", " + v + ")");
            alarmManager0.cancel(pendingIntent0);
        }
    }

    public static void setAlarm(Context context0, WorkDatabase workDatabase0, WorkGenerationalId workGenerationalId0, long v) {
        SystemIdInfoDao systemIdInfoDao0 = workDatabase0.systemIdInfoDao();
        SystemIdInfo systemIdInfo0 = systemIdInfoDao0.getSystemIdInfo(workGenerationalId0);
        if(systemIdInfo0 != null) {
            Alarms.cancelExactAlarm(context0, workGenerationalId0, systemIdInfo0.systemId);
            Alarms.setExactAlarm(context0, workGenerationalId0, systemIdInfo0.systemId, v);
            return;
        }
        int v1 = new IdGenerator(workDatabase0).nextAlarmManagerId();
        systemIdInfoDao0.insertSystemIdInfo(SystemIdInfoKt.systemIdInfo(workGenerationalId0, v1));
        Alarms.setExactAlarm(context0, workGenerationalId0, v1, v);
    }

    private static void setExactAlarm(Context context0, WorkGenerationalId workGenerationalId0, int v, long v1) {
        AlarmManager alarmManager0 = (AlarmManager)context0.getSystemService("alarm");
        int v2 = Build.VERSION.SDK_INT < 23 ? 0x8000000 : 0xC000000;
        PendingIntent pendingIntent0 = PendingIntent.getService(context0, v, CommandHandler.createDelayMetIntent(context0, workGenerationalId0), v2);
        if(alarmManager0 != null) {
            alarmManager0.setExact(0, v1, pendingIntent0);
        }
    }
}

