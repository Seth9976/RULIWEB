package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

public class ProfileInstaller {
    @Retention(RetentionPolicy.SOURCE)
    public @interface DiagnosticCode {
    }

    public interface DiagnosticsCallback {
        void onDiagnosticReceived(int arg1, Object arg2);

        void onResultReceived(int arg1, Object arg2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResultCode {
    }

    public static final int DIAGNOSTIC_CURRENT_PROFILE_DOES_NOT_EXIST = 2;
    public static final int DIAGNOSTIC_CURRENT_PROFILE_EXISTS = 1;
    public static final int DIAGNOSTIC_PROFILE_IS_COMPRESSED = 5;
    public static final int DIAGNOSTIC_REF_PROFILE_DOES_NOT_EXIST = 4;
    public static final int DIAGNOSTIC_REF_PROFILE_EXISTS = 3;
    private static final DiagnosticsCallback EMPTY_DIAGNOSTICS = null;
    static final DiagnosticsCallback LOG_DIAGNOSTICS = null;
    private static final String PROFILE_BASE_DIR = "/data/misc/profiles/cur/0";
    private static final String PROFILE_FILE = "primary.prof";
    private static final String PROFILE_INSTALLER_SKIP_FILE_NAME = "profileinstaller_profileWrittenFor_lastUpdateTime.dat";
    private static final String PROFILE_META_LOCATION = "dexopt/baseline.profm";
    static final String PROFILE_SOURCE_LOCATION = "dexopt/baseline.prof";
    public static final int RESULT_ALREADY_INSTALLED = 2;
    public static final int RESULT_BASELINE_PROFILE_NOT_FOUND = 6;
    public static final int RESULT_BENCHMARK_OPERATION_FAILURE = 15;
    public static final int RESULT_BENCHMARK_OPERATION_SUCCESS = 14;
    public static final int RESULT_BENCHMARK_OPERATION_UNKNOWN = 16;
    public static final int RESULT_DELETE_SKIP_FILE_SUCCESS = 11;
    public static final int RESULT_DESIRED_FORMAT_UNSUPPORTED = 5;
    public static final int RESULT_INSTALL_SKIP_FILE_SUCCESS = 10;
    public static final int RESULT_INSTALL_SUCCESS = 1;
    public static final int RESULT_IO_EXCEPTION = 7;
    public static final int RESULT_META_FILE_REQUIRED_BUT_NOT_FOUND = 9;
    public static final int RESULT_NOT_WRITABLE = 4;
    public static final int RESULT_PARSE_EXCEPTION = 8;
    public static final int RESULT_SAVE_PROFILE_SIGNALLED = 12;
    public static final int RESULT_SAVE_PROFILE_SKIPPED = 13;
    public static final int RESULT_UNSUPPORTED_ART_VERSION = 3;
    private static final String TAG = "ProfileInstaller";

    static {
        ProfileInstaller.EMPTY_DIAGNOSTICS = new DiagnosticsCallback() {
            @Override  // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
            public void onDiagnosticReceived(int v, Object object0) {
            }

            @Override  // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
            public void onResultReceived(int v, Object object0) {
            }
        };
        ProfileInstaller.LOG_DIAGNOSTICS = new DiagnosticsCallback() {
            static final String TAG = "ProfileInstaller";

            @Override  // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
            public void onDiagnosticReceived(int v, Object object0) {
                String s;
                switch(v) {
                    case 1: {
                        s = "DIAGNOSTIC_CURRENT_PROFILE_EXISTS";
                        break;
                    }
                    case 2: {
                        s = "DIAGNOSTIC_CURRENT_PROFILE_DOES_NOT_EXIST";
                        break;
                    }
                    case 3: {
                        s = "DIAGNOSTIC_REF_PROFILE_EXISTS";
                        break;
                    }
                    case 4: {
                        s = "DIAGNOSTIC_REF_PROFILE_DOES_NOT_EXIST";
                        break;
                    }
                    case 5: {
                        s = "DIAGNOSTIC_PROFILE_IS_COMPRESSED";
                        break;
                    }
                    default: {
                        s = "";
                    }
                }
                Log.d("ProfileInstaller", s);
            }

            @Override  // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
            public void onResultReceived(int v, Object object0) {
                String s;
                switch(v) {
                    case 1: {
                        s = "RESULT_INSTALL_SUCCESS";
                        break;
                    }
                    case 2: {
                        s = "RESULT_ALREADY_INSTALLED";
                        break;
                    }
                    case 3: {
                        s = "RESULT_UNSUPPORTED_ART_VERSION";
                        break;
                    }
                    case 4: {
                        s = "RESULT_NOT_WRITABLE";
                        break;
                    }
                    case 5: {
                        s = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                        break;
                    }
                    case 6: {
                        s = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                        break;
                    }
                    case 7: {
                        s = "RESULT_IO_EXCEPTION";
                        break;
                    }
                    case 8: {
                        s = "RESULT_PARSE_EXCEPTION";
                        break;
                    }
                    case 10: {
                        s = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                        break;
                    }
                    case 11: {
                        s = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                        break;
                    }
                    default: {
                        s = "";
                    }
                }
                if(v != 6 && v != 7 && v != 8) {
                    Log.d("ProfileInstaller", s);
                    return;
                }
                Log.e("ProfileInstaller", s, ((Throwable)object0));
            }
        };
    }

    static boolean deleteProfileWrittenFor(File file0) {
        return new File(file0, "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
    }

    static void deleteSkipFile(Context context0, Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0) {
        ProfileInstaller.deleteProfileWrittenFor(context0.getFilesDir());
        ProfileInstaller.result(executor0, profileInstaller$DiagnosticsCallback0, 11, null);
    }

    static void diagnostic(Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0, int v, Object object0) {
        executor0.execute(() -> profileInstaller$DiagnosticsCallback0.onDiagnosticReceived(v, object0));
    }

    static boolean hasAlreadyWrittenProfileForThisInstall(PackageInfo packageInfo0, File file0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0) {
        File file1 = new File(file0, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
        boolean z = false;
        if(!file1.exists()) {
            return false;
        }
        try(DataInputStream dataInputStream0 = new DataInputStream(new FileInputStream(file1))) {
            if(dataInputStream0.readLong() == packageInfo0.lastUpdateTime) {
                goto label_10;
            }
            return z;
        }
        catch(IOException unused_ex) {
            return false;
        }
    label_10:
        profileInstaller$DiagnosticsCallback0.onResultReceived(2, null);
        return true;
    }

    // 检测为 Lambda 实现
    static void lambda$diagnostic$1(DiagnosticsCallback profileInstaller$DiagnosticsCallback0, int v, Object object0) [...]

    // 检测为 Lambda 实现
    static void lambda$result$0(DiagnosticsCallback profileInstaller$DiagnosticsCallback0, int v, Object object0) [...]

    static void noteProfileWrittenFor(PackageInfo packageInfo0, File file0) {
        File file1 = new File(file0, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
        try(DataOutputStream dataOutputStream0 = new DataOutputStream(new FileOutputStream(file1))) {
            dataOutputStream0.writeLong(packageInfo0.lastUpdateTime);
        }
        catch(IOException unused_ex) {
        }
    }

    static void result(Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0, int v, Object object0) {
        executor0.execute(() -> profileInstaller$DiagnosticsCallback0.onResultReceived(v, object0));
    }

    private static boolean transcodeAndWrite(AssetManager assetManager0, String s, PackageInfo packageInfo0, File file0, String s1, Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0) {
        DeviceProfileWriter deviceProfileWriter0 = new DeviceProfileWriter(assetManager0, executor0, profileInstaller$DiagnosticsCallback0, s1, "dexopt/baseline.prof", "dexopt/baseline.profm", new File(new File("/data/misc/profiles/cur/0", s), "primary.prof"));
        if(!deviceProfileWriter0.deviceAllowsProfileInstallerAotWrites()) {
            return false;
        }
        boolean z = deviceProfileWriter0.read().transcodeIfNeeded().write();
        if(z) {
            ProfileInstaller.noteProfileWrittenFor(packageInfo0, file0);
        }
        return z;
    }

    public static void writeProfile(Context context0) {
        ProfileInstaller.writeProfile(context0, new ProfileInstallReceiver..ExternalSyntheticLambda0(), ProfileInstaller.EMPTY_DIAGNOSTICS);
    }

    public static void writeProfile(Context context0, Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0) {
        ProfileInstaller.writeProfile(context0, executor0, profileInstaller$DiagnosticsCallback0, false);
    }

    static void writeProfile(Context context0, Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0, boolean z) {
        Context context1 = context0.getApplicationContext();
        ApplicationInfo applicationInfo0 = context1.getApplicationInfo();
        AssetManager assetManager0 = context1.getAssets();
        String s = new File(applicationInfo0.sourceDir).getName();
        PackageManager packageManager0 = context0.getPackageManager();
        boolean z1 = false;
        try {
            PackageInfo packageInfo0 = packageManager0.getPackageInfo("com.ruliweb.www.ruliapp", 0);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            profileInstaller$DiagnosticsCallback0.onResultReceived(7, packageManager$NameNotFoundException0);
            ProfileVerifier.writeProfileVerification(context0, false);
            return;
        }
        File file0 = context0.getFilesDir();
        if(!z && ProfileInstaller.hasAlreadyWrittenProfileForThisInstall(packageInfo0, file0, profileInstaller$DiagnosticsCallback0)) {
            Log.d("ProfileInstaller", "Skipping profile installation for com.ruliweb.www.ruliapp");
            ProfileVerifier.writeProfileVerification(context0, false);
            return;
        }
        Log.d("ProfileInstaller", "Installing profile for com.ruliweb.www.ruliapp");
        if(ProfileInstaller.transcodeAndWrite(assetManager0, "com.ruliweb.www.ruliapp", packageInfo0, file0, s, executor0, profileInstaller$DiagnosticsCallback0) && z) {
            z1 = true;
        }
        ProfileVerifier.writeProfileVerification(context0, z1);
    }

    static void writeSkipFile(Context context0, Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0) {
        PackageInfo packageInfo0;
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            packageInfo0 = packageManager0.getPackageInfo("com.ruliweb.www.ruliapp", 0);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            ProfileInstaller.result(executor0, profileInstaller$DiagnosticsCallback0, 7, packageManager$NameNotFoundException0);
            return;
        }
        ProfileInstaller.noteProfileWrittenFor(packageInfo0, context0.getFilesDir());
        ProfileInstaller.result(executor0, profileInstaller$DiagnosticsCallback0, 10, null);
    }
}

