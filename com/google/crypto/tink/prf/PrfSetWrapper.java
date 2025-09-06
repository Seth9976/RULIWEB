package com.google.crypto.tink.prf;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.MonitoringUtil;
import com.google.crypto.tink.internal.MutableMonitoringRegistry;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Immutable
public class PrfSetWrapper implements PrimitiveWrapper {
    static class WrappedPrfSet extends PrfSet {
        @Immutable
        static class PrfWithMonitoring implements Prf {
            private final int keyId;
            private final Logger logger;
            private final Prf prf;

            public PrfWithMonitoring(Prf prf0, int v, Logger monitoringClient$Logger0) {
                this.prf = prf0;
                this.keyId = v;
                this.logger = monitoringClient$Logger0;
            }

            @Override  // com.google.crypto.tink.prf.Prf
            public byte[] compute(byte[] arr_b, int v) throws GeneralSecurityException {
                try {
                    byte[] arr_b1 = this.prf.compute(arr_b, v);
                    this.logger.log(this.keyId, ((long)arr_b.length));
                    return arr_b1;
                }
                catch(GeneralSecurityException generalSecurityException0) {
                    this.logger.logFailure();
                    throw generalSecurityException0;
                }
            }
        }

        private final Map keyIdToPrfMap;
        private final int primaryKeyId;

        private WrappedPrfSet(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
            if(primitiveSet0.getRawPrimitives().isEmpty()) {
                throw new GeneralSecurityException("No primitives provided.");
            }
            if(primitiveSet0.getPrimary() == null) {
                throw new GeneralSecurityException("Primary key not set.");
            }
            Logger monitoringClient$Logger0 = primitiveSet0.hasAnnotations() ? MutableMonitoringRegistry.globalInstance().getMonitoringClient().createLogger(MonitoringUtil.getMonitoringKeysetInfo(primitiveSet0), "prf", "compute") : MonitoringUtil.DO_NOTHING_LOGGER;
            this.primaryKeyId = primitiveSet0.getPrimary().getKeyId();
            List list0 = primitiveSet0.getRawPrimitives();
            HashMap hashMap0 = new HashMap();
            for(Object object0: list0) {
                Entry primitiveSet$Entry0 = (Entry)object0;
                if(!primitiveSet$Entry0.getOutputPrefixType().equals(OutputPrefixType.RAW)) {
                    throw new GeneralSecurityException("Key " + primitiveSet$Entry0.getKeyId() + " has non raw prefix type");
                }
                hashMap0.put(primitiveSet$Entry0.getKeyId(), new PrfWithMonitoring(((Prf)primitiveSet$Entry0.getPrimitive()), primitiveSet$Entry0.getKeyId(), monitoringClient$Logger0));
            }
            this.keyIdToPrfMap = Collections.unmodifiableMap(hashMap0);
        }

        WrappedPrfSet(PrimitiveSet primitiveSet0, com.google.crypto.tink.prf.PrfSetWrapper.1 prfSetWrapper$10) throws GeneralSecurityException {
            this(primitiveSet0);
        }

        @Override  // com.google.crypto.tink.prf.PrfSet
        public Map getPrfs() throws GeneralSecurityException {
            return this.keyIdToPrfMap;
        }

        @Override  // com.google.crypto.tink.prf.PrfSet
        public int getPrimaryId() {
            return this.primaryKeyId;
        }
    }

    private static final PrfSetWrapper WRAPPER;

    static {
        PrfSetWrapper.WRAPPER = new PrfSetWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return Prf.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return PrfSet.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(PrfSetWrapper.WRAPPER);
    }

    public PrfSet wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return new WrappedPrfSet(primitiveSet0, null);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }

    class com.google.crypto.tink.prf.PrfSetWrapper.1 {
    }

}

