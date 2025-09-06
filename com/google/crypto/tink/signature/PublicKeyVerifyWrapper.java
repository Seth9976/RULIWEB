package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class PublicKeyVerifyWrapper implements PrimitiveWrapper {
    static class WrappedPublicKeyVerify implements PublicKeyVerify {
        private final Logger monitoringLogger;
        private final PrimitiveSet primitives;

        public WrappedPublicKeyVerify(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
            if(primitiveSet0.hasAnnotations()) {
                this.monitoringLogger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0), "public_key_verify", "verify");
                return;
            }
            this.monitoringLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override  // com.google.crypto.tink.PublicKeyVerify
        public void verify(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            if(arr_b.length > 5) {
                byte[] arr_b2 = Arrays.copyOf(arr_b, 5);
                byte[] arr_b3 = Arrays.copyOfRange(arr_b, 5, arr_b.length);
                for(Object object0: this.primitives.getPrimitive(arr_b2)) {
                    Entry primitiveSet$Entry0 = (Entry)object0;
                    byte[] arr_b4 = primitiveSet$Entry0.getOutputPrefixType().equals(OutputPrefixType.LEGACY) ? Bytes.concat(new byte[][]{arr_b1, new byte[]{0}}) : arr_b1;
                    try {
                        ((PublicKeyVerify)primitiveSet$Entry0.getPrimitive()).verify(arr_b3, arr_b4);
                        this.monitoringLogger.log(primitiveSet$Entry0.getKeyId(), ((long)arr_b4.length));
                        return;
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        PublicKeyVerifyWrapper.logger.info("signature prefix matches a key, but cannot verify: " + generalSecurityException0);
                    }
                }
                for(Object object1: this.primitives.getRawPrimitives()) {
                    Entry primitiveSet$Entry1 = (Entry)object1;
                    try {
                        ((PublicKeyVerify)primitiveSet$Entry1.getPrimitive()).verify(arr_b, arr_b1);
                        this.monitoringLogger.log(primitiveSet$Entry1.getKeyId(), ((long)arr_b1.length));
                        return;
                    }
                    catch(GeneralSecurityException unused_ex) {
                    }
                }
                this.monitoringLogger.logFailure();
                throw new GeneralSecurityException("invalid signature");
            }
            this.monitoringLogger.logFailure();
            throw new GeneralSecurityException("signature too short");
        }
    }

    private static final byte[] FORMAT_VERSION;
    private static final PublicKeyVerifyWrapper WRAPPER;
    private static final java.util.logging.Logger logger;

    static {
        PublicKeyVerifyWrapper.logger = java.util.logging.Logger.getLogger("com.google.crypto.tink.signature.PublicKeyVerifyWrapper");
        PublicKeyVerifyWrapper.FORMAT_VERSION = new byte[]{0};
        PublicKeyVerifyWrapper.WRAPPER = new PublicKeyVerifyWrapper();
    }

    static byte[] access$000() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return PublicKeyVerify.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(PublicKeyVerifyWrapper.WRAPPER);
    }

    public PublicKeyVerify wrap(PrimitiveSet primitiveSet0) {
        return new WrappedPublicKeyVerify(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

