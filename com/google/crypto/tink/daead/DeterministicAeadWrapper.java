package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
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

public class DeterministicAeadWrapper implements PrimitiveWrapper {
    static class WrappedDeterministicAead implements DeterministicAead {
        private final Logger decLogger;
        private final Logger encLogger;
        private final PrimitiveSet primitives;

        public WrappedDeterministicAead(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
            if(primitiveSet0.hasAnnotations()) {
                MonitoringClient monitoringClient0 = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo0 = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0);
                this.encLogger = monitoringClient0.createLogger(monitoringKeysetInfo0, "daead", "encrypt");
                this.decLogger = monitoringClient0.createLogger(monitoringKeysetInfo0, "daead", "decrypt");
                return;
            }
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override  // com.google.crypto.tink.DeterministicAead
        public byte[] decryptDeterministically(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            if(arr_b.length > 5) {
                byte[] arr_b2 = Arrays.copyOf(arr_b, 5);
                byte[] arr_b3 = Arrays.copyOfRange(arr_b, 5, arr_b.length);
                for(Object object0: this.primitives.getPrimitive(arr_b2)) {
                    Entry primitiveSet$Entry0 = (Entry)object0;
                    try {
                        byte[] arr_b4 = ((DeterministicAead)primitiveSet$Entry0.getPrimitive()).decryptDeterministically(arr_b3, arr_b1);
                        this.decLogger.log(primitiveSet$Entry0.getKeyId(), ((long)arr_b3.length));
                        return arr_b4;
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        DeterministicAeadWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + generalSecurityException0);
                    }
                }
            }
            for(Object object1: this.primitives.getRawPrimitives()) {
                Entry primitiveSet$Entry1 = (Entry)object1;
                try {
                    byte[] arr_b5 = ((DeterministicAead)primitiveSet$Entry1.getPrimitive()).decryptDeterministically(arr_b, arr_b1);
                    this.decLogger.log(primitiveSet$Entry1.getKeyId(), ((long)arr_b.length));
                    return arr_b5;
                }
                catch(GeneralSecurityException unused_ex) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }

        @Override  // com.google.crypto.tink.DeterministicAead
        public byte[] encryptDeterministically(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            try {
                byte[] arr_b2 = Bytes.concat(new byte[][]{this.primitives.getPrimary().getIdentifier(), ((DeterministicAead)this.primitives.getPrimary().getPrimitive()).encryptDeterministically(arr_b, arr_b1)});
                this.encLogger.log(this.primitives.getPrimary().getKeyId(), ((long)arr_b.length));
                return arr_b2;
            }
            catch(GeneralSecurityException generalSecurityException0) {
                this.encLogger.logFailure();
                throw generalSecurityException0;
            }
        }
    }

    private static final DeterministicAeadWrapper WRAPPER;
    private static final java.util.logging.Logger logger;

    static {
        DeterministicAeadWrapper.logger = java.util.logging.Logger.getLogger("com.google.crypto.tink.daead.DeterministicAeadWrapper");
        DeterministicAeadWrapper.WRAPPER = new DeterministicAeadWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return DeterministicAead.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return DeterministicAead.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(DeterministicAeadWrapper.WRAPPER);
    }

    public DeterministicAead wrap(PrimitiveSet primitiveSet0) {
        return new WrappedDeterministicAead(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

