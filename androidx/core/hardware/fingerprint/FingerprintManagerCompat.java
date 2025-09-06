package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager.AuthenticationCallback;
import android.hardware.fingerprint.FingerprintManager.AuthenticationResult;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.os.Handler;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

@Deprecated
public class FingerprintManagerCompat {
    static class Api23Impl {
        static void authenticate(Object object0, Object object1, CancellationSignal cancellationSignal0, int v, Object object2, Handler handler0) {
            ((FingerprintManager)object0).authenticate(((FingerprintManager.CryptoObject)object1), cancellationSignal0, v, ((FingerprintManager.AuthenticationCallback)object2), handler0);
        }

        static FingerprintManager.CryptoObject getCryptoObject(Object object0) {
            return ((FingerprintManager.AuthenticationResult)object0).getCryptoObject();
        }

        public static FingerprintManager getFingerprintManagerOrNull(Context context0) {
            if(Build.VERSION.SDK_INT == 23) {
                return (FingerprintManager)context0.getSystemService(FingerprintManager.class);
            }
            return Build.VERSION.SDK_INT <= 23 || !context0.getPackageManager().hasSystemFeature("android.hardware.fingerprint") ? null : ((FingerprintManager)context0.getSystemService(FingerprintManager.class));
        }

        static boolean hasEnrolledFingerprints(Object object0) {
            return ((FingerprintManager)object0).hasEnrolledFingerprints();
        }

        static boolean isHardwareDetected(Object object0) {
            return ((FingerprintManager)object0).isHardwareDetected();
        }

        public static CryptoObject unwrapCryptoObject(Object object0) {
            if(((FingerprintManager.CryptoObject)object0) == null) {
                return null;
            }
            if(((FingerprintManager.CryptoObject)object0).getCipher() != null) {
                return new CryptoObject(((FingerprintManager.CryptoObject)object0).getCipher());
            }
            if(((FingerprintManager.CryptoObject)object0).getSignature() != null) {
                return new CryptoObject(((FingerprintManager.CryptoObject)object0).getSignature());
            }
            return ((FingerprintManager.CryptoObject)object0).getMac() == null ? null : new CryptoObject(((FingerprintManager.CryptoObject)object0).getMac());
        }

        public static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject fingerprintManagerCompat$CryptoObject0) {
            if(fingerprintManagerCompat$CryptoObject0 == null) {
                return null;
            }
            if(fingerprintManagerCompat$CryptoObject0.getCipher() != null) {
                return new FingerprintManager.CryptoObject(fingerprintManagerCompat$CryptoObject0.getCipher());
            }
            if(fingerprintManagerCompat$CryptoObject0.getSignature() != null) {
                return new FingerprintManager.CryptoObject(fingerprintManagerCompat$CryptoObject0.getSignature());
            }
            return fingerprintManagerCompat$CryptoObject0.getMac() == null ? null : new FingerprintManager.CryptoObject(fingerprintManagerCompat$CryptoObject0.getMac());
        }
    }

    public static abstract class AuthenticationCallback {
        public void onAuthenticationError(int v, CharSequence charSequence0) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int v, CharSequence charSequence0) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult fingerprintManagerCompat$AuthenticationResult0) {
        }
    }

    public static final class AuthenticationResult {
        private final CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject fingerprintManagerCompat$CryptoObject0) {
            this.mCryptoObject = fingerprintManagerCompat$CryptoObject0;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    public static class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(Signature signature0) {
            this.mSignature = signature0;
            this.mCipher = null;
            this.mMac = null;
        }

        public CryptoObject(Cipher cipher0) {
            this.mCipher = cipher0;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(Mac mac0) {
            this.mMac = mac0;
            this.mCipher = null;
            this.mSignature = null;
        }

        public Cipher getCipher() {
            return this.mCipher;
        }

        public Mac getMac() {
            return this.mMac;
        }

        public Signature getSignature() {
            return this.mSignature;
        }
    }

    private final Context mContext;

    private FingerprintManagerCompat(Context context0) {
        this.mContext = context0;
    }

    public void authenticate(CryptoObject fingerprintManagerCompat$CryptoObject0, int v, CancellationSignal cancellationSignal0, AuthenticationCallback fingerprintManagerCompat$AuthenticationCallback0, Handler handler0) {
        if(Build.VERSION.SDK_INT >= 23) {
            FingerprintManager fingerprintManager0 = FingerprintManagerCompat.getFingerprintManagerOrNull(this.mContext);
            if(fingerprintManager0 != null) {
                Api23Impl.authenticate(fingerprintManager0, FingerprintManagerCompat.wrapCryptoObject(fingerprintManagerCompat$CryptoObject0), cancellationSignal0, v, FingerprintManagerCompat.wrapCallback(fingerprintManagerCompat$AuthenticationCallback0), handler0);
            }
        }
    }

    @Deprecated
    public void authenticate(CryptoObject fingerprintManagerCompat$CryptoObject0, int v, androidx.core.os.CancellationSignal cancellationSignal0, AuthenticationCallback fingerprintManagerCompat$AuthenticationCallback0, Handler handler0) {
        this.authenticate(fingerprintManagerCompat$CryptoObject0, v, (cancellationSignal0 == null ? null : ((CancellationSignal)cancellationSignal0.getCancellationSignalObject())), fingerprintManagerCompat$AuthenticationCallback0, handler0);
    }

    public static FingerprintManagerCompat from(Context context0) {
        return new FingerprintManagerCompat(context0);
    }

    private static FingerprintManager getFingerprintManagerOrNull(Context context0) {
        return Api23Impl.getFingerprintManagerOrNull(context0);
    }

    public boolean hasEnrolledFingerprints() {
        if(Build.VERSION.SDK_INT >= 23) {
            FingerprintManager fingerprintManager0 = FingerprintManagerCompat.getFingerprintManagerOrNull(this.mContext);
            return fingerprintManager0 != null && Api23Impl.hasEnrolledFingerprints(fingerprintManager0);
        }
        return false;
    }

    public boolean isHardwareDetected() {
        if(Build.VERSION.SDK_INT >= 23) {
            FingerprintManager fingerprintManager0 = FingerprintManagerCompat.getFingerprintManagerOrNull(this.mContext);
            return fingerprintManager0 != null && Api23Impl.isHardwareDetected(fingerprintManager0);
        }
        return false;
    }

    static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject fingerprintManager$CryptoObject0) {
        return Api23Impl.unwrapCryptoObject(fingerprintManager$CryptoObject0);
    }

    private static FingerprintManager.AuthenticationCallback wrapCallback(AuthenticationCallback fingerprintManagerCompat$AuthenticationCallback0) {
        return new FingerprintManager.AuthenticationCallback() {
            @Override  // android.hardware.fingerprint.FingerprintManager$AuthenticationCallback
            public void onAuthenticationError(int v, CharSequence charSequence0) {
            }

            @Override  // android.hardware.fingerprint.FingerprintManager$AuthenticationCallback
            public void onAuthenticationFailed() {
            }

            @Override  // android.hardware.fingerprint.FingerprintManager$AuthenticationCallback
            public void onAuthenticationHelp(int v, CharSequence charSequence0) {
            }

            @Override  // android.hardware.fingerprint.FingerprintManager$AuthenticationCallback
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult fingerprintManager$AuthenticationResult0) {
                new AuthenticationResult(FingerprintManagerCompat.unwrapCryptoObject(Api23Impl.getCryptoObject(fingerprintManager$AuthenticationResult0)));
            }
        };
    }

    private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject fingerprintManagerCompat$CryptoObject0) {
        return Api23Impl.wrapCryptoObject(fingerprintManagerCompat$CryptoObject0);
    }
}

