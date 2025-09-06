package com.google.crypto.tink.mac;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.monitoring.MonitoringClient;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

class MacWrapper implements PrimitiveWrapper {
    static class WrappedMac implements Mac {
        private final Logger computeLogger;
        private final PrimitiveSet primitives;
        private final Logger verifyLogger;

        private WrappedMac(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
            if(primitiveSet0.hasAnnotations()) {
                MonitoringClient monitoringClient0 = MutableMonitoringRegistry.globalInstance().getMonitoringClient();
                MonitoringKeysetInfo monitoringKeysetInfo0 = MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0);
                this.computeLogger = monitoringClient0.createLogger(monitoringKeysetInfo0, "mac", "compute");
                this.verifyLogger = monitoringClient0.createLogger(monitoringKeysetInfo0, "mac", "verify");
                return;
            }
            this.computeLogger = MonitoringUtil.DO_NOTHING_LOGGER;
            this.verifyLogger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        WrappedMac(PrimitiveSet primitiveSet0, com.google.crypto.tink.mac.MacWrapper.1 macWrapper$10) {
            this(primitiveSet0);
        }

        @Override  // com.google.crypto.tink.Mac
        public byte[] computeMac(byte[] arr_b) throws GeneralSecurityException {
            if(this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                arr_b = Bytes.concat(new byte[][]{arr_b, new byte[]{0}});
            }
            try {
                byte[] arr_b1 = Bytes.concat(new byte[][]{this.primitives.getPrimary().getIdentifier(), ((Mac)this.primitives.getPrimary().getPrimitive()).computeMac(arr_b)});
                this.computeLogger.log(this.primitives.getPrimary().getKeyId(), ((long)arr_b.length));
                return arr_b1;
            }
            catch(GeneralSecurityException generalSecurityException0) {
                this.computeLogger.logFailure();
                throw generalSecurityException0;
            }
        }

        @Override  // com.google.crypto.tink.Mac
        public void verifyMac(byte[] arr_b, byte[] arr_b1) throws GeneralSecurityException {
            if(arr_b.length > 5) {
                byte[] arr_b2 = Arrays.copyOf(arr_b, 5);
                byte[] arr_b3 = Arrays.copyOfRange(arr_b, 5, arr_b.length);
                for(Object object0: this.primitives.getPrimitive(arr_b2)) {
                    Entry primitiveSet$Entry0 = (Entry)object0;
                    byte[] arr_b4 = primitiveSet$Entry0.getOutputPrefixType().equals(OutputPrefixType.LEGACY) ? Bytes.concat(new byte[][]{arr_b1, new byte[]{0}}) : arr_b1;
                    try {
                        ((Mac)primitiveSet$Entry0.getPrimitive()).verifyMac(arr_b3, arr_b4);
                        this.verifyLogger.log(primitiveSet$Entry0.getKeyId(), ((long)arr_b4.length));
                        return;
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        MacWrapper.logger.info("tag prefix matches a key, but cannot verify: " + generalSecurityException0);
                    }
                }
                for(Object object1: this.primitives.getRawPrimitives()) {
                    Entry primitiveSet$Entry1 = (Entry)object1;
                    try {
                        ((Mac)primitiveSet$Entry1.getPrimitive()).verifyMac(arr_b, arr_b1);
                        this.verifyLogger.log(primitiveSet$Entry1.getKeyId(), ((long)arr_b1.length));
                        return;
                    }
                    catch(GeneralSecurityException unused_ex) {
                    }
                }
                this.verifyLogger.logFailure();
                throw new GeneralSecurityException("invalid MAC");
            }
            this.verifyLogger.logFailure();
            throw new GeneralSecurityException("tag too short");
        }
    }

    private static final byte[] FORMAT_VERSION;
    private static final MacWrapper WRAPPER;
    private static final java.util.logging.Logger logger;

    static {
        MacWrapper.logger = java.util.logging.Logger.getLogger("com.google.crypto.tink.mac.MacWrapper");
        MacWrapper.FORMAT_VERSION = new byte[]{0};
        MacWrapper.WRAPPER = new MacWrapper();
    }

    static byte[] access$000() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return Mac.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return Mac.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(MacWrapper.WRAPPER);
    }

    private void validateMacKeyPrefixes(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        for(Object object0: primitiveSet0.getAll()) {
            for(Object object1: ((List)object0)) {
                Entry primitiveSet$Entry0 = (Entry)object1;
                if(primitiveSet$Entry0.getKey() instanceof MacKey) {
                    MacKey macKey0 = (MacKey)primitiveSet$Entry0.getKey();
                    com.google.crypto.tink.util.Bytes bytes0 = com.google.crypto.tink.util.Bytes.copyFrom(primitiveSet$Entry0.getIdentifier());
                    if(!bytes0.equals(macKey0.getOutputPrefix())) {
                        throw new GeneralSecurityException("Mac Key with parameters " + macKey0.getParameters() + " has wrong output prefix (" + macKey0.getOutputPrefix() + ") instead of (" + bytes0 + ")");
                    }
                    if(false) {
                        break;
                    }
                }
            }
            if(false) {
                break;
            }
        }
    }

    public Mac wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        this.validateMacKeyPrefixes(primitiveSet0);
        return new WrappedMac(primitiveSet0, null);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }

    class com.google.crypto.tink.mac.MacWrapper.1 {
    }

}

