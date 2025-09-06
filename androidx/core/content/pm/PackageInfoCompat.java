package androidx.core.content.pm;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build.VERSION;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class PackageInfoCompat {
    static class Api28Impl {
        static Signature[] getApkContentsSigners(SigningInfo signingInfo0) {
            return signingInfo0.getApkContentsSigners();
        }

        static long getLongVersionCode(PackageInfo packageInfo0) {
            return packageInfo0.getLongVersionCode();
        }

        static Signature[] getSigningCertificateHistory(SigningInfo signingInfo0) {
            return signingInfo0.getSigningCertificateHistory();
        }

        static boolean hasMultipleSigners(SigningInfo signingInfo0) {
            return signingInfo0.hasMultipleSigners();
        }

        static boolean hasSigningCertificate(PackageManager packageManager0, String s, byte[] arr_b, int v) {
            return packageManager0.hasSigningCertificate(s, arr_b, v);
        }
    }

    private static boolean byteArrayContains(byte[][] arr2_b, byte[] arr_b) {
        for(int v = 0; v < arr2_b.length; ++v) {
            if(Arrays.equals(arr_b, arr2_b[v])) {
                return true;
            }
        }
        return false;
    }

    private static byte[] computeSHA256Digest(byte[] arr_b) {
        try {
            return MessageDigest.getInstance("SHA256").digest(arr_b);
        }
        catch(NoSuchAlgorithmException noSuchAlgorithmException0) {
            throw new RuntimeException("Device doesn\'t support SHA256 cert checking", noSuchAlgorithmException0);
        }
    }

    public static long getLongVersionCode(PackageInfo packageInfo0) {
        return Build.VERSION.SDK_INT < 28 ? ((long)packageInfo0.versionCode) : Api28Impl.getLongVersionCode(packageInfo0);
    }

    public static List getSignatures(PackageManager packageManager0, String s) throws PackageManager.NameNotFoundException {
        Signature[] arr_signature;
        if(Build.VERSION.SDK_INT >= 28) {
            SigningInfo signingInfo0 = packageManager0.getPackageInfo(s, 0x8000000).signingInfo;
            if(Api28Impl.hasMultipleSigners(signingInfo0)) {
                arr_signature = Api28Impl.getApkContentsSigners(signingInfo0);
                return arr_signature == null ? Collections.EMPTY_LIST : Arrays.asList(arr_signature);
            }
            arr_signature = Api28Impl.getSigningCertificateHistory(signingInfo0);
            return arr_signature == null ? Collections.EMPTY_LIST : Arrays.asList(arr_signature);
        }
        arr_signature = packageManager0.getPackageInfo(s, 0x40).signatures;
        return arr_signature == null ? Collections.EMPTY_LIST : Arrays.asList(arr_signature);
    }

    public static boolean hasSignatures(PackageManager packageManager0, String s, Map map0, boolean z) throws PackageManager.NameNotFoundException {
        byte[][] arr2_b;
        if(map0.isEmpty()) {
            return false;
        }
        Set set0 = map0.keySet();
        Iterator iterator0 = set0.iterator();
    label_4:
        if(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            if(((byte[])object0) == null) {
                throw new IllegalArgumentException("Cert byte array cannot be null when verifying " + s);
            }
            Integer integer0 = (Integer)map0.get(((byte[])object0));
            if(integer0 != null) {
                switch(((int)integer0)) {
                    case 0: 
                    case 1: {
                        goto label_4;
                    }
                    default: {
                        throw new IllegalArgumentException("Unsupported certificate type " + integer0 + " when verifying " + s);
                    }
                }
            }
            throw new IllegalArgumentException("Type must be specified for cert when verifying " + s);
        }
        List list0 = PackageInfoCompat.getSignatures(packageManager0, s);
        if(!z && Build.VERSION.SDK_INT >= 28) {
            for(Object object1: set0) {
                if(!Api28Impl.hasSigningCertificate(packageManager0, s, ((byte[])object1), ((int)(((Integer)map0.get(((byte[])object1))))))) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }
        if(list0.size() != 0 && map0.size() <= list0.size() && (!z || map0.size() == list0.size())) {
            if(map0.containsValue(1)) {
                arr2_b = new byte[list0.size()][];
                for(int v = 0; v < list0.size(); ++v) {
                    arr2_b[v] = PackageInfoCompat.computeSHA256Digest(((Signature)list0.get(v)).toByteArray());
                }
            }
            else {
                arr2_b = null;
            }
            Iterator iterator2 = set0.iterator();
            if(iterator2.hasNext()) {
                Object object2 = iterator2.next();
                Integer integer1 = (Integer)map0.get(((byte[])object2));
                switch(((int)integer1)) {
                    case 0: {
                        return list0.contains(new Signature(((byte[])object2)));
                    }
                    case 1: {
                        return PackageInfoCompat.byteArrayContains(arr2_b, ((byte[])object2));
                    }
                    default: {
                        throw new IllegalArgumentException("Unsupported certificate type " + integer1);
                    }
                }
            }
        }
        return false;
    }
}

