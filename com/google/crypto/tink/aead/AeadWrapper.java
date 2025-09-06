package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class AeadWrapper implements PrimitiveWrapper {
    static class WrappedAead implements Aead {
        private final Logger decLogger;
        private final Logger encLogger;
        private final PrimitiveSet pSet;

        private WrappedAead(PrimitiveSet primitiveSet0) {
            this.pSet = primitiveSet0;
            if(primitiveSet0.hasAnnotations()) {
                MonitoringClient monitoringClient0 = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo0 = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0);
                this.encLogger = monitoringClient0.createLogger(monitoringKeysetInfo0, "aead", "encrypt");
                this.decLogger = monitoringClient0.createLogger(monitoringKeysetInfo0, "aead", "decrypt");
                return;
            }
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        WrappedAead(PrimitiveSet primitiveSet0, com.google.crypto.tink.aead.AeadWrapper.1 aeadWrapper$10) {
            this(primitiveSet0);
        }

        @Override  // com.google.crypto.tink.Aead
        public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            if(arr_b.length > 5) {
                byte[] arr_b2 = Arrays.copyOf(arr_b, 5);
                byte[] arr_b3 = Arrays.copyOfRange(arr_b, 5, arr_b.length);
                for(Object object0: this.pSet.getPrimitive(arr_b2)) {
                    Entry primitiveSet$Entry0 = (Entry)object0;
                    try {
                        byte[] arr_b4 = ((Aead)primitiveSet$Entry0.getPrimitive()).decrypt(arr_b3, arr_b1);
                        this.decLogger.log(primitiveSet$Entry0.getKeyId(), ((long)arr_b3.length));
                        return arr_b4;
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        AeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + generalSecurityException0);
                    }
                }
            }
            for(Object object1: this.pSet.getRawPrimitives()) {
                Entry primitiveSet$Entry1 = (Entry)object1;
                try {
                    byte[] arr_b5 = ((Aead)primitiveSet$Entry1.getPrimitive()).decrypt(arr_b, arr_b1);
                    this.decLogger.log(primitiveSet$Entry1.getKeyId(), ((long)arr_b.length));
                    return arr_b5;
                }
                catch(GeneralSecurityException unused_ex) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }

        @Override  // com.google.crypto.tink.Aead
        public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            try {
                byte[] arr_b2 = Bytes.concat(new byte[][]{this.pSet.getPrimary().getIdentifier(), ((Aead)this.pSet.getPrimary().getPrimitive()).encrypt(arr_b, arr_b1)});
                int v = this.pSet.getPrimary().getKeyId();
                this.encLogger.log(v, ((long)arr_b.length));
                return arr_b2;
            }
            catch(GeneralSecurityException generalSecurityException0) {
                this.encLogger.logFailure();
                throw generalSecurityException0;
            }
        }
    }

    private static final AeadWrapper WRAPPER;
    private static final java.util.logging.Logger logger;

    static {
        AeadWrapper.logger = java.util.logging.Logger.getLogger("com.google.crypto.tink.aead.AeadWrapper");
        AeadWrapper.WRAPPER = new AeadWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return Aead.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return Aead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(AeadWrapper.WRAPPER);
    }

    public Aead wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return new WrappedAead(primitiveSet0, null);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }

    class com.google.crypto.tink.aead.AeadWrapper.1 {
    }

}

