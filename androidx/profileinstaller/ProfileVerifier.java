package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager.PackageInfoFlags;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.os.Build.VERSION;
import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public final class ProfileVerifier {
    static class Api33Impl {
        static PackageInfo getPackageInfo(PackageManager packageManager0, Context context0) throws PackageManager.NameNotFoundException {
            return packageManager0.getPackageInfo("com.ruliweb.www.ruliapp", PackageManager.PackageInfoFlags.of(0L));
        }
    }

    static class Cache {
        private static final int SCHEMA = 1;
        final long mInstalledCurrentProfileSize;
        final long mPackageLastUpdateTime;
        final int mResultCode;
        final int mSchema;

        Cache(int v, int v1, long v2, long v3) {
            this.mSchema = v;
            this.mResultCode = v1;
            this.mPackageLastUpdateTime = v2;
            this.mInstalledCurrentProfileSize = v3;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return this == object0 ? true : object0 != null && object0 instanceof Cache && this.mResultCode == ((Cache)object0).mResultCode && this.mPackageLastUpdateTime == ((Cache)object0).mPackageLastUpdateTime && this.mSchema == ((Cache)object0).mSchema && this.mInstalledCurrentProfileSize == ((Cache)object0).mInstalledCurrentProfileSize;
        }

        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.mResultCode, this.mPackageLastUpdateTime, this.mSchema, this.mInstalledCurrentProfileSize});
        }

        static Cache readFromFile(File file0) throws IOException {
            try(DataInputStream dataInputStream0 = new DataInputStream(new FileInputStream(file0))) {
                return new Cache(dataInputStream0.readInt(), dataInputStream0.readInt(), dataInputStream0.readLong(), dataInputStream0.readLong());
            }
        }

        void writeOnFile(File file0) throws IOException {
            file0.delete();
            try(DataOutputStream dataOutputStream0 = new DataOutputStream(new FileOutputStream(file0))) {
                dataOutputStream0.writeInt(this.mSchema);
                dataOutputStream0.writeInt(this.mResultCode);
                dataOutputStream0.writeLong(this.mPackageLastUpdateTime);
                dataOutputStream0.writeLong(this.mInstalledCurrentProfileSize);
            }
        }
    }

    public static class CompilationStatus {
        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        public static final int RESULT_CODE_COMPILED_WITH_PROFILE = 1;
        public static final int RESULT_CODE_COMPILED_WITH_PROFILE_NON_MATCHING = 3;
        public static final int RESULT_CODE_ERROR_CACHE_FILE_EXISTS_BUT_CANNOT_BE_READ = 0x20000;
        public static final int RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE = 0x30000;
        private static final int RESULT_CODE_ERROR_CODE_BIT_SHIFT = 16;
        public static final int RESULT_CODE_ERROR_NO_PROFILE_EMBEDDED = 0x50000;
        public static final int RESULT_CODE_ERROR_PACKAGE_NAME_DOES_NOT_EXIST = 0x10000;
        public static final int RESULT_CODE_ERROR_UNSUPPORTED_API_VERSION = 0x40000;
        @Deprecated
        public static final int RESULT_CODE_NO_PROFILE = 0;
        public static final int RESULT_CODE_NO_PROFILE_INSTALLED = 0;
        public static final int RESULT_CODE_PROFILE_ENQUEUED_FOR_COMPILATION = 2;
        private final boolean mHasCurrentProfile;
        private final boolean mHasEmbeddedProfile;
        private final boolean mHasReferenceProfile;
        final int mResultCode;

        CompilationStatus(int v, boolean z, boolean z1, boolean z2) {
            this.mResultCode = v;
            this.mHasCurrentProfile = z1;
            this.mHasReferenceProfile = z;
            this.mHasEmbeddedProfile = z2;
        }

        public boolean appApkHasEmbeddedProfile() {
            return this.mHasEmbeddedProfile;
        }

        public int getProfileInstallResultCode() {
            return this.mResultCode;
        }

        public boolean hasProfileEnqueuedForCompilation() {
            return this.mHasCurrentProfile;
        }

        public boolean isCompiledWithProfile() {
            return this.mHasReferenceProfile;
        }
    }

    private static final String CUR_PROFILES_BASE_DIR = "/data/misc/profiles/cur/0/";
    private static final String PROFILE_FILE_NAME = "primary.prof";
    private static final String PROFILE_INSTALLED_CACHE_FILE_NAME = "profileInstalled";
    private static final String REF_PROFILES_BASE_DIR = "/data/misc/profiles/ref/";
    private static final Object SYNC_OBJ = null;
    private static final String TAG = "ProfileVerifier";
    private static CompilationStatus sCompilationStatus;
    private static final ResolvableFuture sFuture;

    static {
        ProfileVerifier.sFuture = ResolvableFuture.create();
        ProfileVerifier.SYNC_OBJ = new Object();
        ProfileVerifier.sCompilationStatus = null;
    }

    public static ListenableFuture getCompilationStatusAsync() {
        return ProfileVerifier.sFuture;
    }

    private static long getPackageLastUpdateTime(Context context0) throws PackageManager.NameNotFoundException {
        PackageManager packageManager0 = context0.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT < 33 ? packageManager0.getPackageInfo("com.ruliweb.www.ruliapp", 0).lastUpdateTime : Api33Impl.getPackageInfo(packageManager0, context0).lastUpdateTime;
    }

    private static CompilationStatus setCompilationStatus(int v, boolean z, boolean z1, boolean z2) {
        CompilationStatus profileVerifier$CompilationStatus0 = new CompilationStatus(v, z, z1, z2);
        ProfileVerifier.sCompilationStatus = profileVerifier$CompilationStatus0;
        ProfileVerifier.sFuture.set(profileVerifier$CompilationStatus0);
        return ProfileVerifier.sCompilationStatus;
    }

    public static CompilationStatus writeProfileVerification(Context context0) {
        return ProfileVerifier.writeProfileVerification(context0, false);
    }

    static CompilationStatus writeProfileVerification(Context context0, boolean z) {
        Cache profileVerifier$Cache0;
        long v4;
        boolean z1;
        int v1;
        if(!z) {
            CompilationStatus profileVerifier$CompilationStatus0 = ProfileVerifier.sCompilationStatus;
            if(profileVerifier$CompilationStatus0 != null) {
                return profileVerifier$CompilationStatus0;
            }
        }
        synchronized(ProfileVerifier.SYNC_OBJ) {
            if(!z) {
                CompilationStatus profileVerifier$CompilationStatus1 = ProfileVerifier.sCompilationStatus;
                if(profileVerifier$CompilationStatus1 != null) {
                    return profileVerifier$CompilationStatus1;
                }
            }
            try {
                v1 = 0;
                try(AssetFileDescriptor assetFileDescriptor0 = context0.getAssets().openFd("dexopt/baseline.prof")) {
                    z1 = assetFileDescriptor0.getLength() <= 0L ? false : true;
                }
            }
            catch(IOException unused_ex) {
                z1 = false;
            }
            if(Build.VERSION.SDK_INT >= 28 && Build.VERSION.SDK_INT != 30) {
                File file0 = new File(new File("/data/misc/profiles/ref/", "com.ruliweb.www.ruliapp"), "primary.prof");
                long v2 = file0.length();
                boolean z2 = file0.exists() && v2 > 0L;
                File file1 = new File(new File("/data/misc/profiles/cur/0/", "com.ruliweb.www.ruliapp"), "primary.prof");
                long v3 = file1.length();
                boolean z3 = file1.exists() && v3 > 0L;
                try {
                    v4 = ProfileVerifier.getPackageLastUpdateTime(context0);
                }
                catch(PackageManager.NameNotFoundException unused_ex) {
                    return ProfileVerifier.setCompilationStatus(0x10000, z2, z3, z1);
                }
                File file2 = new File(context0.getFilesDir(), "profileInstalled");
                if(file2.exists()) {
                    try {
                        profileVerifier$Cache0 = Cache.readFromFile(file2);
                    }
                    catch(IOException unused_ex) {
                        return ProfileVerifier.setCompilationStatus(0x20000, z2, z3, z1);
                    }
                }
                else {
                    profileVerifier$Cache0 = null;
                }
                if(profileVerifier$Cache0 != null && profileVerifier$Cache0.mPackageLastUpdateTime == v4 && profileVerifier$Cache0.mResultCode != 2) {
                    v1 = profileVerifier$Cache0.mResultCode;
                }
                else if(!z1) {
                    v1 = 0x50000;
                }
                else if(z2) {
                    v1 = 1;
                }
                else if(z3) {
                    v1 = 2;
                }
                if(z && z3 && v1 != 1) {
                    v1 = 2;
                }
                int v5 = profileVerifier$Cache0 == null || profileVerifier$Cache0.mResultCode != 2 || v1 != 1 || v2 >= profileVerifier$Cache0.mInstalledCurrentProfileSize ? v1 : 3;
                Cache profileVerifier$Cache1 = new Cache(1, v5, v4, v3);
                if(profileVerifier$Cache0 == null || !profileVerifier$Cache0.equals(profileVerifier$Cache1)) {
                    try {
                        profileVerifier$Cache1.writeOnFile(file2);
                    }
                    catch(IOException unused_ex) {
                        v5 = 0x30000;
                    }
                }
                return ProfileVerifier.setCompilationStatus(v5, z2, z3, z1);
            }
        }
        return ProfileVerifier.setCompilationStatus(0x40000, false, false, z1);
    }
}

