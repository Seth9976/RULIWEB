package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.internal.cloudmessaging.zza;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rpc {
    private static int zza;
    private static PendingIntent zzb;
    private static final Executor zzc;
    private static final Pattern zzd;
    private final SimpleArrayMap zze;
    private final Context zzf;
    private final zzt zzg;
    private final ScheduledExecutorService zzh;
    private Messenger zzi;
    private Messenger zzj;
    private zzd zzk;

    static {
        Rpc.zzc = zzz.zza;
        Rpc.zzd = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)");
    }

    public Rpc(Context context0) {
        this.zze = new SimpleArrayMap();
        this.zzf = context0;
        this.zzg = new zzt(context0);
        this.zzi = new Messenger(new zzaa(this, Looper.getMainLooper()));
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor0 = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor0.setKeepAliveTime(60L, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor0.allowCoreThreadTimeOut(true);
        this.zzh = scheduledThreadPoolExecutor0;
    }

    public Task send(Bundle bundle0) {
        if(this.zzg.zza() < 12000000) {
            if(this.zzg.zzb() != 0) {
                Task task0 = this.zze(bundle0);
                zzu zzu0 = new zzu(this, bundle0);
                return task0.continueWithTask(Rpc.zzc, zzu0);
            }
            return Tasks.forException(new IOException("MISSING_INSTANCEID_SERVICE"));
        }
        return zzs.zzb(this.zzf).zzd(1, bundle0).continueWith(Rpc.zzc, zzv.zza);
    }

    // 去混淆评级： 低(20)
    static Task zza(Bundle bundle0) throws Exception {
        return Rpc.zzi(bundle0) ? Tasks.forResult(null) : Tasks.forResult(bundle0);
    }

    // 去混淆评级： 低(20)
    public final Task zzb(Bundle bundle0, Task task0) throws Exception {
        return task0.isSuccessful() && Rpc.zzi(((Bundle)task0.getResult())) ? this.zze(bundle0).onSuccessTask(Rpc.zzc, zzx.zza) : task0;
    }

    static void zzc(Rpc rpc0, Message message0) {
        if(message0 != null && message0.obj instanceof Intent) {
            Intent intent0 = (Intent)message0.obj;
            intent0.setExtrasClassLoader(new zzc());
            if(intent0.hasExtra("google.messenger")) {
                Parcelable parcelable0 = intent0.getParcelableExtra("google.messenger");
                if(parcelable0 instanceof zzd) {
                    rpc0.zzk = (zzd)parcelable0;
                }
                if(parcelable0 instanceof Messenger) {
                    rpc0.zzj = (Messenger)parcelable0;
                }
            }
            Intent intent1 = (Intent)message0.obj;
            String s = intent1.getAction();
            if("com.google.android.c2dm.intent.REGISTRATION".equals(s)) {
                String s2 = intent1.getStringExtra("registration_id");
                if(s2 == null) {
                    s2 = intent1.getStringExtra("unregistered");
                }
                if(s2 == null) {
                    String s3 = intent1.getStringExtra("error");
                    if(s3 == null) {
                        Log.w("Rpc", "Unexpected response, no error or registration id " + intent1.getExtras());
                        return;
                    }
                    if(Log.isLoggable("Rpc", 3)) {
                        Log.d("Rpc", (s3.length() == 0 ? new String("Received InstanceID error ") : "Received InstanceID error " + s3));
                    }
                    if(s3.startsWith("|")) {
                        String[] arr_s = s3.split("\\|");
                        if(arr_s.length > 2 && "ID".equals(arr_s[1])) {
                            String s4 = arr_s[2];
                            String s5 = arr_s[3];
                            if(s5.startsWith(":")) {
                                s5 = s5.substring(1);
                            }
                            rpc0.zzh(s4, intent1.putExtra("error", s5).getExtras());
                            return;
                        }
                        Log.w("Rpc", (s3.length() == 0 ? new String("Unexpected structured response ") : "Unexpected structured response " + s3));
                        return;
                    }
                    SimpleArrayMap simpleArrayMap0 = rpc0.zze;
                    synchronized(simpleArrayMap0) {
                        for(int v1 = 0; v1 < rpc0.zze.size(); ++v1) {
                            rpc0.zzh(((String)rpc0.zze.keyAt(v1)), intent1.getExtras());
                        }
                    }
                    return;
                }
                Matcher matcher0 = Rpc.zzd.matcher(s2);
                if(matcher0.matches()) {
                    String s6 = matcher0.group(1);
                    String s7 = matcher0.group(2);
                    if(s6 != null) {
                        Bundle bundle0 = intent1.getExtras();
                        bundle0.putString("registration_id", s7);
                        rpc0.zzh(s6, bundle0);
                    }
                }
                else if(Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", (s2.length() == 0 ? new String("Unexpected response string: ") : "Unexpected response string: " + s2));
                    return;
                }
            }
            else if(Log.isLoggable("Rpc", 3)) {
                String s1 = String.valueOf(s);
                Log.d("Rpc", (s1.length() == 0 ? new String("Unexpected response action: ") : "Unexpected response action: " + s1));
                return;
            }
            return;
        }
        Log.w("Rpc", "Dropping invalid message");
    }

    public final void zzd(String s, ScheduledFuture scheduledFuture0, Task task0) {
        synchronized(this.zze) {
            this.zze.remove(s);
        }
        scheduledFuture0.cancel(false);
    }

    private final Task zze(Bundle bundle0) {
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        synchronized(this.zze) {
            this.zze.put("0", taskCompletionSource0);
        }
        Intent intent0 = new Intent();
        intent0.setPackage("com.google.android.gms");
        if(this.zzg.zzb() == 2) {
            intent0.setAction("com.google.iid.TOKEN_REQUEST");
        }
        else {
            intent0.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent0.putExtras(bundle0);
        Rpc.zzg(this.zzf, intent0);
        intent0.putExtra("kid", "|ID|0|");
        if(Log.isLoggable("Rpc", 3)) {
            Log.d("Rpc", "Sending " + intent0.getExtras());
        }
        intent0.putExtra("google.messenger", this.zzi);
        if(this.zzj == null && this.zzk == null) {
        label_30:
            if(this.zzg.zzb() == 2) {
                this.zzf.sendBroadcast(intent0);
            }
            else {
                this.zzf.startService(intent0);
            }
        }
        else {
            Message message0 = Message.obtain();
            message0.obj = intent0;
            try {
                Messenger messenger0 = this.zzj;
                if(messenger0 == null) {
                    this.zzk.zzb(message0);
                }
                else {
                    messenger0.send(message0);
                }
            }
            catch(RemoteException unused_ex) {
                if(Log.isLoggable("Rpc", 3)) {
                    Log.d("Rpc", "Messenger failed, fallback to startService");
                }
                goto label_30;
            }
        }
        zzy zzy0 = new zzy(taskCompletionSource0);
        zzw zzw0 = new zzw(this, "0", this.zzh.schedule(zzy0, 30L, TimeUnit.SECONDS));
        taskCompletionSource0.getTask().addOnCompleteListener(Rpc.zzc, zzw0);
        return taskCompletionSource0.getTask();
    }

    private static String zzf() [...] // 潜在的解密器

    private static void zzg(Context context0, Intent intent0) {
        synchronized(Rpc.class) {
            if(Rpc.zzb == null) {
                Intent intent1 = new Intent();
                intent1.setPackage("com.google.example.invalidpackage");
                Rpc.zzb = zza.zza(context0, 0, intent1, zza.zza);
            }
            intent0.putExtra("app", Rpc.zzb);
        }
    }

    private final void zzh(String s, Bundle bundle0) {
        synchronized(this.zze) {
            TaskCompletionSource taskCompletionSource0 = (TaskCompletionSource)this.zze.remove(s);
            if(taskCompletionSource0 == null) {
                String s1 = String.valueOf(s);
                Log.w("Rpc", (s1.length() == 0 ? new String("Missing callback for ") : "Missing callback for " + s1));
                return;
            }
            taskCompletionSource0.setResult(bundle0);
        }
    }

    private static boolean zzi(Bundle bundle0) {
        return bundle0 != null && bundle0.containsKey("google.messenger");
    }
}

