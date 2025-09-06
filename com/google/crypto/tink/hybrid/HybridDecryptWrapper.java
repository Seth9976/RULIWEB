package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class HybridDecryptWrapper implements PrimitiveWrapper {
    static class WrappedHybridDecrypt implements HybridDecrypt {
        private final Logger decLogger;
        private final PrimitiveSet primitives;

        public WrappedHybridDecrypt(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
            if(primitiveSet0.hasAnnotations()) {
                this.decLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0), "hybrid_decrypt", "decrypt");
                return;
            }
            this.decLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override  // com.google.crypto.tink.HybridDecrypt
        public byte[] decrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            if(arr_b.length > 5) {
                byte[] arr_b2 = Arrays.copyOfRange(arr_b, 0, 5);
                byte[] arr_b3 = Arrays.copyOfRange(arr_b, 5, arr_b.length);
                for(Object object0: this.primitives.getPrimitive(arr_b2)) {
                    Entry primitiveSet$Entry0 = (Entry)object0;
                    try {
                        byte[] arr_b4 = ((HybridDecrypt)primitiveSet$Entry0.getPrimitive()).decrypt(arr_b3, arr_b1);
                        this.decLogger.log(primitiveSet$Entry0.getKeyId(), ((long)arr_b3.length));
                        return arr_b4;
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        HybridDecryptWrapper.logger.info("ciphertext prefix matches a key, but cannot decrypt: " + generalSecurityException0.toString());
                    }
                }
            }
            for(Object object1: this.primitives.getRawPrimitives()) {
                Entry primitiveSet$Entry1 = (Entry)object1;
                try {
                    byte[] arr_b5 = ((HybridDecrypt)primitiveSet$Entry1.getPrimitive()).decrypt(arr_b, arr_b1);
                    this.decLogger.log(primitiveSet$Entry1.getKeyId(), ((long)arr_b.length));
                    return arr_b5;
                }
                catch(GeneralSecurityException unused_ex) {
                }
            }
            this.decLogger.logFailure();
            throw new GeneralSecurityException("decryption failed");
        }
    }

    private static final HybridDecryptWrapper WRAPPER;
    private static final java.util.logging.Logger logger;

    static {
        HybridDecryptWrapper.logger = java.util.logging.Logger.getLogger("com.google.crypto.tink.hybrid.HybridDecryptWrapper");
        HybridDecryptWrapper.WRAPPER = new HybridDecryptWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return HybridDecrypt.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return HybridDecrypt.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(HybridDecryptWrapper.WRAPPER);
    }

    public HybridDecrypt wrap(PrimitiveSet primitiveSet0) {
        return new WrappedHybridDecrypt(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

