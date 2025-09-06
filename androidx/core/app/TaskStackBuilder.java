package androidx.core.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder implements Iterable {
    public interface SupportParentable {
        Intent getSupportParentActivityIntent();
    }

    private static final String TAG = "TaskStackBuilder";
    private final ArrayList mIntents;
    private final Context mSourceContext;

    private TaskStackBuilder(Context context0) {
        this.mIntents = new ArrayList();
        this.mSourceContext = context0;
    }

    public TaskStackBuilder addNextIntent(Intent intent0) {
        this.mIntents.add(intent0);
        return this;
    }

    public TaskStackBuilder addNextIntentWithParentStack(Intent intent0) {
        ComponentName componentName0 = intent0.getComponent();
        if(componentName0 == null) {
            componentName0 = intent0.resolveActivity(this.mSourceContext.getPackageManager());
        }
        if(componentName0 != null) {
            this.addParentStack(componentName0);
        }
        this.addNextIntent(intent0);
        return this;
    }

    public TaskStackBuilder addParentStack(Activity activity0) {
        Intent intent0 = activity0 instanceof SupportParentable ? ((SupportParentable)activity0).getSupportParentActivityIntent() : null;
        if(intent0 == null) {
            intent0 = NavUtils.getParentActivityIntent(activity0);
        }
        if(intent0 != null) {
            ComponentName componentName0 = intent0.getComponent();
            if(componentName0 == null) {
                componentName0 = intent0.resolveActivity(this.mSourceContext.getPackageManager());
            }
            this.addParentStack(componentName0);
            this.addNextIntent(intent0);
        }
        return this;
    }

    public TaskStackBuilder addParentStack(ComponentName componentName0) {
        int v = this.mIntents.size();
        try {
            for(Intent intent0 = NavUtils.getParentActivityIntent(this.mSourceContext, componentName0); intent0 != null; intent0 = NavUtils.getParentActivityIntent(this.mSourceContext, componentName1)) {
                this.mIntents.add(v, intent0);
                ComponentName componentName1 = intent0.getComponent();
            }
            return this;
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(packageManager$NameNotFoundException0);
        }
    }

    public TaskStackBuilder addParentStack(Class class0) {
        return this.addParentStack(new ComponentName(this.mSourceContext, class0));
    }

    public static TaskStackBuilder create(Context context0) {
        return new TaskStackBuilder(context0);
    }

    public Intent editIntentAt(int v) {
        return (Intent)this.mIntents.get(v);
    }

    @Deprecated
    public static TaskStackBuilder from(Context context0) {
        return TaskStackBuilder.create(context0);
    }

    @Deprecated
    public Intent getIntent(int v) {
        return this.editIntentAt(v);
    }

    public int getIntentCount() {
        return this.mIntents.size();
    }

    public Intent[] getIntents() {
        int v = this.mIntents.size();
        Intent[] arr_intent = new Intent[v];
        if(v != 0) {
            arr_intent[0] = new Intent(((Intent)this.mIntents.get(0))).addFlags(0x1000C000);
            for(int v1 = 1; v1 < v; ++v1) {
                arr_intent[v1] = new Intent(((Intent)this.mIntents.get(v1)));
            }
        }
        return arr_intent;
    }

    public PendingIntent getPendingIntent(int v, int v1) {
        return this.getPendingIntent(v, v1, null);
    }

    public PendingIntent getPendingIntent(int v, int v1, Bundle bundle0) {
        if(this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        }
        Intent[] arr_intent = (Intent[])this.mIntents.toArray(new Intent[0]);
        arr_intent[0] = new Intent(arr_intent[0]).addFlags(0x1000C000);
        return PendingIntent.getActivities(this.mSourceContext, v, arr_intent, v1, bundle0);
    }

    public PendingIntent getPendingIntent(int v, int v1, Bundle bundle0, boolean z) {
        return this.getPendingIntent(v, PendingIntentCompat.addMutabilityFlags(z, v1), bundle0);
    }

    public PendingIntent getPendingIntent(int v, int v1, boolean z) {
        return this.getPendingIntent(v, PendingIntentCompat.addMutabilityFlags(z, v1));
    }

    @Override
    @Deprecated
    public Iterator iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        this.startActivities(null);
    }

    public void startActivities(Bundle bundle0) {
        if(this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] arr_intent = (Intent[])this.mIntents.toArray(new Intent[0]);
        arr_intent[0] = new Intent(arr_intent[0]).addFlags(0x1000C000);
        if(!ContextCompat.startActivities(this.mSourceContext, arr_intent, bundle0)) {
            Intent intent0 = new Intent(arr_intent[arr_intent.length - 1]);
            intent0.addFlags(0x10000000);
            this.mSourceContext.startActivity(intent0);
        }
    }
}

