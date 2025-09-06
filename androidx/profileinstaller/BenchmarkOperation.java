package androidx.profileinstaller;

import android.content.Context;
import android.os.Build.VERSION;
import java.io.File;

class BenchmarkOperation {
    static class Api21ContextHelper {
        static File getCodeCacheDir(Context context0) {
            return context0.getCodeCacheDir();
        }
    }

    static class Api24ContextHelper {
        static Context createDeviceProtectedStorageContext(Context context0) {
            return context0.createDeviceProtectedStorageContext();
        }
    }

    static boolean deleteFilesRecursively(File file0) {
        if(file0.isDirectory()) {
            File[] arr_file = file0.listFiles();
            if(arr_file == null) {
                return false;
            }
            boolean z = true;
            for(int v = 0; v < arr_file.length; ++v) {
                z = BenchmarkOperation.deleteFilesRecursively(arr_file[v]) && z;
            }
            return z;
        }
        file0.delete();
        return true;
    }

    static void dropShaderCache(Context context0, ResultDiagnostics profileInstallReceiver$ResultDiagnostics0) {
        File file0;
        if(Build.VERSION.SDK_INT >= 34) {
            file0 = Api24ContextHelper.createDeviceProtectedStorageContext(context0).getCacheDir();
        }
        else if(Build.VERSION.SDK_INT >= 24) {
            file0 = Api21ContextHelper.getCodeCacheDir(Api24ContextHelper.createDeviceProtectedStorageContext(context0));
        }
        else {
            file0 = Build.VERSION.SDK_INT == 23 ? Api21ContextHelper.getCodeCacheDir(context0) : context0.getCacheDir();
        }
        if(BenchmarkOperation.deleteFilesRecursively(file0)) {
            profileInstallReceiver$ResultDiagnostics0.onResultReceived(14, null);
            return;
        }
        profileInstallReceiver$ResultDiagnostics0.onResultReceived(15, null);
    }
}

