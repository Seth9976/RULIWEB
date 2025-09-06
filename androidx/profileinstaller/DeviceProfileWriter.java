package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build.VERSION;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.Executor;

public class DeviceProfileWriter {
    private final String mApkName;
    private final AssetManager mAssetManager;
    private final File mCurProfile;
    private final byte[] mDesiredVersion;
    private boolean mDeviceSupportsAotProfile;
    private final DiagnosticsCallback mDiagnostics;
    private final Executor mExecutor;
    private DexProfileData[] mProfile;
    private final String mProfileMetaSourceLocation;
    private final String mProfileSourceLocation;
    private byte[] mTranscodedProfile;

    public DeviceProfileWriter(AssetManager assetManager0, Executor executor0, DiagnosticsCallback profileInstaller$DiagnosticsCallback0, String s, String s1, String s2, File file0) {
        this.mDeviceSupportsAotProfile = false;
        this.mAssetManager = assetManager0;
        this.mExecutor = executor0;
        this.mDiagnostics = profileInstaller$DiagnosticsCallback0;
        this.mApkName = s;
        this.mProfileSourceLocation = s1;
        this.mProfileMetaSourceLocation = s2;
        this.mCurProfile = file0;
        this.mDesiredVersion = new byte[]{0x30, 49, 53, 0};
    }

    private DeviceProfileWriter addMetadata(DexProfileData[] arr_dexProfileData, byte[] arr_b) {
        try(InputStream inputStream0 = this.openStreamFromAssets(this.mAssetManager, this.mProfileMetaSourceLocation)) {
            if(inputStream0 != null) {
                this.mProfile = ProfileTranscoder.readMeta(inputStream0, ProfileTranscoder.readHeader(inputStream0, ProfileTranscoder.MAGIC_PROFM), arr_b, arr_dexProfileData);
                return this;
            }
        }
        catch(FileNotFoundException fileNotFoundException0) {
            this.mDiagnostics.onResultReceived(9, fileNotFoundException0);
        }
        catch(IOException iOException0) {
            this.mDiagnostics.onResultReceived(7, iOException0);
        }
        catch(IllegalStateException illegalStateException0) {
            this.mProfile = null;
            this.mDiagnostics.onResultReceived(8, illegalStateException0);
        }
        return null;
    }

    private void assertDeviceAllowsProfileInstallerAotWritesCalled() {
        if(!this.mDeviceSupportsAotProfile) {
            throw new IllegalStateException("This device doesn\'t support aot. Did you call deviceSupportsAotProfile()?");
        }
    }

    private static byte[] desiredVersion() [...] // 潜在的解密器

    public boolean deviceAllowsProfileInstallerAotWrites() {
        if(this.mDesiredVersion == null) {
            this.result(3, Build.VERSION.SDK_INT);
            return false;
        }
        if(!this.mCurProfile.exists()) {
            try {
                if(!this.mCurProfile.createNewFile()) {
                    this.result(4, null);
                    return false;
                }
                this.mDeviceSupportsAotProfile = true;
                return true;
            }
            catch(IOException unused_ex) {
            }
        }
        else if(!this.mCurProfile.canWrite()) {
            this.result(4, null);
            return false;
        }
        else {
            this.mDeviceSupportsAotProfile = true;
            return true;
        }
        this.result(4, null);
        return false;
    }

    private InputStream getProfileInputStream(AssetManager assetManager0) {
        try {
            return this.openStreamFromAssets(assetManager0, this.mProfileSourceLocation);
        }
        catch(FileNotFoundException fileNotFoundException0) {
            this.mDiagnostics.onResultReceived(6, fileNotFoundException0);
            return null;
        }
        catch(IOException iOException0) {
            this.mDiagnostics.onResultReceived(7, iOException0);
            return null;
        }
    }

    // 检测为 Lambda 实现
    void lambda$result$0$androidx-profileinstaller-DeviceProfileWriter(int v, Object object0) [...]

    private InputStream openStreamFromAssets(AssetManager assetManager0, String s) throws IOException {
        try {
            return assetManager0.openFd(s).createInputStream();
        }
        catch(FileNotFoundException fileNotFoundException0) {
            String s1 = fileNotFoundException0.getMessage();
            if(s1 != null && s1.contains("compressed")) {
                this.mDiagnostics.onDiagnosticReceived(5, null);
            }
            return null;
        }
    }

    public DeviceProfileWriter read() {
        this.assertDeviceAllowsProfileInstallerAotWritesCalled();
        if(this.mDesiredVersion != null) {
            InputStream inputStream0 = this.getProfileInputStream(this.mAssetManager);
            if(inputStream0 != null) {
                this.mProfile = this.readProfileInternal(inputStream0);
            }
            DexProfileData[] arr_dexProfileData = this.mProfile;
            if(arr_dexProfileData != null) {
                DeviceProfileWriter deviceProfileWriter0 = this.addMetadata(arr_dexProfileData, this.mDesiredVersion);
                return deviceProfileWriter0 == null ? this : deviceProfileWriter0;
            }
        }
        return this;
    }

    private DexProfileData[] readProfileInternal(InputStream inputStream0) {
        DexProfileData[] arr_dexProfileData;
        try {
            try {
                arr_dexProfileData = ProfileTranscoder.readProfile(inputStream0, ProfileTranscoder.readHeader(inputStream0, ProfileTranscoder.MAGIC_PROF), this.mApkName);
                goto label_19;
            }
            catch(IOException iOException0) {
            }
            catch(IllegalStateException illegalStateException0) {
                goto label_7;
            }
            this.mDiagnostics.onResultReceived(7, iOException0);
        }
        catch(Throwable throwable0) {
            goto label_14;
        }
        try {
            inputStream0.close();
        }
        catch(IOException iOException1) {
            this.mDiagnostics.onResultReceived(7, iOException1);
        }
        return null;
        try {
        label_7:
            this.mDiagnostics.onResultReceived(8, illegalStateException0);
        }
        catch(Throwable throwable0) {
            goto label_14;
        }
        try {
            inputStream0.close();
        }
        catch(IOException iOException1) {
            this.mDiagnostics.onResultReceived(7, iOException1);
        }
        return null;
        try {
        label_14:
            inputStream0.close();
        }
        catch(IOException iOException2) {
            this.mDiagnostics.onResultReceived(7, iOException2);
        }
        throw throwable0;
        try {
        label_19:
            inputStream0.close();
        }
        catch(IOException iOException3) {
            this.mDiagnostics.onResultReceived(7, iOException3);
        }
        return arr_dexProfileData;
    }

    private static boolean requiresMetadata() [...] // 潜在的解密器

    private void result(int v, Object object0) {
        DeviceProfileWriter..ExternalSyntheticLambda0 deviceProfileWriter$$ExternalSyntheticLambda00 = () -> this.mDiagnostics.onResultReceived(v, object0);
        this.mExecutor.execute(deviceProfileWriter$$ExternalSyntheticLambda00);
    }

    public DeviceProfileWriter transcodeIfNeeded() {
        DexProfileData[] arr_dexProfileData = this.mProfile;
        byte[] arr_b = this.mDesiredVersion;
        if(arr_dexProfileData != null && arr_b != null) {
            this.assertDeviceAllowsProfileInstallerAotWritesCalled();
            try(ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream()) {
                ProfileTranscoder.writeHeader(byteArrayOutputStream0, arr_b);
                if(!ProfileTranscoder.transcodeAndWriteBody(byteArrayOutputStream0, arr_b, arr_dexProfileData)) {
                    this.mDiagnostics.onResultReceived(5, null);
                    this.mProfile = null;
                    return this;
                }
                this.mTranscodedProfile = byteArrayOutputStream0.toByteArray();
            }
            catch(IOException iOException0) {
                this.mDiagnostics.onResultReceived(7, iOException0);
            }
            catch(IllegalStateException illegalStateException0) {
                this.mDiagnostics.onResultReceived(8, illegalStateException0);
            }
            this.mProfile = null;
        }
        return this;
    }

    public boolean write() {
        byte[] arr_b = this.mTranscodedProfile;
        if(arr_b == null) {
            return false;
        }
        this.assertDeviceAllowsProfileInstallerAotWritesCalled();
        try {
            try(ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(arr_b); FileOutputStream fileOutputStream0 = new FileOutputStream(this.mCurProfile); FileChannel fileChannel0 = fileOutputStream0.getChannel(); FileLock fileLock0 = fileChannel0.tryLock()) {
                Encoding.writeAll(byteArrayInputStream0, fileOutputStream0, fileLock0);
                this.result(1, null);
                if(fileLock0 != null) {
                }
                this.mTranscodedProfile = null;
                this.mProfile = null;
                return true;
            }
            catch(FileNotFoundException fileNotFoundException0) {
                this.result(6, fileNotFoundException0);
            }
            catch(IOException iOException0) {
                this.result(7, iOException0);
            }
            this.mTranscodedProfile = null;
            this.mProfile = null;
            return false;
        }
        catch(Throwable throwable0) {
            this.mTranscodedProfile = null;
            this.mProfile = null;
            throw throwable0;
        }
    }
}

