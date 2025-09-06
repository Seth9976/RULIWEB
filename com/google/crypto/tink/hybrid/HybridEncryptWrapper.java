package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

public class HybridEncryptWrapper implements PrimitiveWrapper {
    static class WrappedHybridEncrypt implements HybridEncrypt {
        private final Logger encLogger;
        final PrimitiveSet primitives;

        public WrappedHybridEncrypt(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
            if(primitiveSet0.hasAnnotations()) {
                this.encLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0), "hybrid_encrypt", "encrypt");
                return;
            }
            this.encLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override  // com.google.crypto.tink.HybridEncrypt
        public byte[] encrypt(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            if(this.primitives.getPrimary() != null) {
                try {
                    byte[] arr_b2 = Bytes.concat(new byte[][]{this.primitives.getPrimary().getIdentifier(), ((HybridEncrypt)this.primitives.getPrimary().getPrimitive()).encrypt(arr_b, arr_b1)});
                    this.encLogger.log(this.primitives.getPrimary().getKeyId(), ((long)arr_b.length));
                    return arr_b2;
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    this.encLogger.logFailure();
                    throw generalSecurityException0;
                }
            }
            this.encLogger.logFailure();
            throw new GeneralSecurityException("keyset without primary key");
        }
    }

    private static final HybridEncryptWrapper WRAPPER;

    static {
        HybridEncryptWrapper.WRAPPER = new HybridEncryptWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return HybridEncrypt.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return HybridEncrypt.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(HybridEncryptWrapper.WRAPPER);
    }

    public HybridEncrypt wrap(PrimitiveSet primitiveSet0) {
        return new WrappedHybridEncrypt(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

