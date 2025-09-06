package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;

public class ProfileInstallReceiver extends BroadcastReceiver {
    class ResultDiagnostics implements DiagnosticsCallback {
        @Override  // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
        public void onDiagnosticReceived(int v, Object object0) {
            ProfileInstaller.LOG_DIAGNOSTICS.onDiagnosticReceived(v, object0);
        }

        @Override  // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
        public void onResultReceived(int v, Object object0) {
            ProfileInstaller.LOG_DIAGNOSTICS.onResultReceived(v, object0);
            ProfileInstallReceiver.this.setResultCode(v);
        }
    }

    public static final String ACTION_BENCHMARK_OPERATION = "androidx.profileinstaller.action.BENCHMARK_OPERATION";
    public static final String ACTION_INSTALL_PROFILE = "androidx.profileinstaller.action.INSTALL_PROFILE";
    public static final String ACTION_SAVE_PROFILE = "androidx.profileinstaller.action.SAVE_PROFILE";
    public static final String ACTION_SKIP_FILE = "androidx.profileinstaller.action.SKIP_FILE";
    private static final String EXTRA_BENCHMARK_OPERATION = "EXTRA_BENCHMARK_OPERATION";
    private static final String EXTRA_BENCHMARK_OPERATION_DROP_SHADER_CACHE = "DROP_SHADER_CACHE";
    private static final String EXTRA_SKIP_FILE_OPERATION = "EXTRA_SKIP_FILE_OPERATION";
    private static final String EXTRA_SKIP_FILE_OPERATION_DELETE = "DELETE_SKIP_FILE";
    private static final String EXTRA_SKIP_FILE_OPERATION_WRITE = "WRITE_SKIP_FILE";

    @Override  // android.content.BroadcastReceiver
    public void onReceive(Context context0, Intent intent0) {
        if(intent0 != null) {
            String s = intent0.getAction();
            if("androidx.profileinstaller.action.INSTALL_PROFILE".equals(s)) {
                ProfileInstaller.writeProfile(context0, new ProfileInstallReceiver..ExternalSyntheticLambda0(), new ResultDiagnostics(this), true);
                return;
            }
            if("androidx.profileinstaller.action.SKIP_FILE".equals(s)) {
                Bundle bundle0 = intent0.getExtras();
                if(bundle0 != null) {
                    String s1 = bundle0.getString("EXTRA_SKIP_FILE_OPERATION");
                    if("WRITE_SKIP_FILE".equals(s1)) {
                        ProfileInstaller.writeSkipFile(context0, new ProfileInstallReceiver..ExternalSyntheticLambda0(), new ResultDiagnostics(this));
                        return;
                    }
                    if("DELETE_SKIP_FILE".equals(s1)) {
                        ProfileInstaller.deleteSkipFile(context0, new ProfileInstallReceiver..ExternalSyntheticLambda0(), new ResultDiagnostics(this));
                    }
                }
            }
            else {
                if("androidx.profileinstaller.action.SAVE_PROFILE".equals(s)) {
                    ProfileInstallReceiver.saveProfile(new ResultDiagnostics(this));
                    return;
                }
                if("androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(s)) {
                    Bundle bundle1 = intent0.getExtras();
                    if(bundle1 != null) {
                        String s2 = bundle1.getString("EXTRA_BENCHMARK_OPERATION");
                        ResultDiagnostics profileInstallReceiver$ResultDiagnostics0 = new ResultDiagnostics(this);
                        if("DROP_SHADER_CACHE".equals(s2)) {
                            BenchmarkOperation.dropShaderCache(context0, profileInstallReceiver$ResultDiagnostics0);
                            return;
                        }
                        profileInstallReceiver$ResultDiagnostics0.onResultReceived(16, null);
                    }
                }
            }
        }
    }

    static void saveProfile(DiagnosticsCallback profileInstaller$DiagnosticsCallback0) {
        if(Build.VERSION.SDK_INT >= 24) {
            Process.sendSignal(Process.myPid(), 10);
            profileInstaller$DiagnosticsCallback0.onResultReceived(12, null);
            return;
        }
        profileInstaller$DiagnosticsCallback0.onResultReceived(13, null);
    }
}

