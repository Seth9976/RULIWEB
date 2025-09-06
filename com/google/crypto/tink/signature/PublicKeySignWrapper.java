package com.google.crypto.tink.signature;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;

public class PublicKeySignWrapper implements PrimitiveWrapper {
    static class WrappedPublicKeySign implements PublicKeySign {
        private final Logger logger;
        private final PrimitiveSet primitives;

        public WrappedPublicKeySign(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
            if(primitiveSet0.hasAnnotations()) {
                this.logger = MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0), "public_key_sign", "sign");
                return;
            }
            this.logger = MonitoringUtil.DO_NOTHING_LOGGER;
        }

        @Override  // com.google.crypto.tink.PublicKeySign
        public byte[] sign(byte[] arr_b) throws GeneralSecurityException {
            if(this.primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                arr_b = Bytes.concat(new byte[][]{arr_b, new byte[]{0}});
            }
            try {
                byte[] arr_b1 = Bytes.concat(new byte[][]{this.primitives.getPrimary().getIdentifier(), ((PublicKeySign)this.primitives.getPrimary().getPrimitive()).sign(arr_b)});
                this.logger.log(this.primitives.getPrimary().getKeyId(), ((long)arr_b.length));
                return arr_b1;
            }
            catch(GeneralSecurityException generalSecurityException0) {
                this.logger.logFailure();
                throw generalSecurityException0;
            }
        }
    }

    private static final byte[] FORMAT_VERSION;
    private static final PublicKeySignWrapper WRAPPER;

    static {
        PublicKeySignWrapper.FORMAT_VERSION = new byte[]{0};
        PublicKeySignWrapper.WRAPPER = new PublicKeySignWrapper();
    }

    static byte[] access$000() [...] // 潜在的解密器

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return PublicKeySign.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return PublicKeySign.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(PublicKeySignWrapper.WRAPPER);
    }

    public PublicKeySign wrap(PrimitiveSet primitiveSet0) {
        return new WrappedPublicKeySign(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

