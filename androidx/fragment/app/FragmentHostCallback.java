package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback extends FragmentContainer {
    private final Activity mActivity;
    private final Context mContext;
    final FragmentManager mFragmentManager;
    private final Handler mHandler;
    private final int mWindowAnimations;

    FragmentHostCallback(Activity activity0, Context context0, Handler handler0, int v) {
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = activity0;
        this.mContext = (Context)Preconditions.checkNotNull(context0, "context == null");
        this.mHandler = (Handler)Preconditions.checkNotNull(handler0, "handler == null");
        this.mWindowAnimations = v;
    }

    public FragmentHostCallback(Context context0, Handler handler0, int v) {
        this((context0 instanceof Activity ? ((Activity)context0) : null), context0, handler0, v);
    }

    FragmentHostCallback(FragmentActivity fragmentActivity0) {
        this(fragmentActivity0, fragmentActivity0, new Handler(), 0);
    }

    Activity getActivity() {
        return this.mActivity;
    }

    Context getContext() {
        return this.mContext;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public void onDump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
    }

    @Override  // androidx.fragment.app.FragmentContainer
    public View onFindViewById(int v) {
        return null;
    }

    public abstract Object onGetHost();

    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }

    @Override  // androidx.fragment.app.FragmentContainer
    public boolean onHasView() {
        return true;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    @Deprecated
    public void onRequestPermissionsFromFragment(Fragment fragment0, String[] arr_s, int v) {
    }

    public boolean onShouldSaveFragmentState(Fragment fragment0) {
        return true;
    }

    public boolean onShouldShowRequestPermissionRationale(String s) {
        return false;
    }

    public void onStartActivityFromFragment(Fragment fragment0, Intent intent0, int v) {
        this.onStartActivityFromFragment(fragment0, intent0, v, null);
    }

    public void onStartActivityFromFragment(Fragment fragment0, Intent intent0, int v, Bundle bundle0) {
        if(v != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        ContextCompat.startActivity(this.mContext, intent0, bundle0);
    }

    @Deprecated
    public void onStartIntentSenderFromFragment(Fragment fragment0, IntentSender intentSender0, int v, Intent intent0, int v1, int v2, int v3, Bundle bundle0) throws IntentSender.SendIntentException {
        if(v != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.startIntentSenderForResult(this.mActivity, intentSender0, -1, intent0, v1, v2, v3, bundle0);
    }

    public void onSupportInvalidateOptionsMenu() {
    }
}

