package com.google.crypto.tink.internal;

import com.google.crypto.tink.KeyStatus;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.monitoring.MonitoringClient.Logger;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo.Builder;
import com.google.crypto.tink.monitoring.MonitoringKeysetInfo;
import com.google.crypto.tink.proto.KeyStatusType;
import java.security.GeneralSecurityException;
import java.util.List;

public final class MonitoringUtil {
    static class DoNothingLogger implements Logger {
        private DoNothingLogger() {
        }

        DoNothingLogger(com.google.crypto.tink.internal.MonitoringUtil.1 monitoringUtil$10) {
        }

        @Override  // com.google.crypto.tink.monitoring.MonitoringClient$Logger
        public void log(int keyId, long numBytesAsInput) {
        }

        @Override  // com.google.crypto.tink.monitoring.MonitoringClient$Logger
        public void logFailure() {
        }
    }

    public static final Logger DO_NOTHING_LOGGER = null;
    private static final String TYPE_URL_PREFIX = "type.googleapis.com/google.crypto.";

    static {
        MonitoringUtil.DO_NOTHING_LOGGER = new DoNothingLogger(null);
    }

    public static MonitoringKeysetInfo getMonitoringKeysetInfo(PrimitiveSet primitiveSet0) {
        Builder monitoringKeysetInfo$Builder0 = MonitoringKeysetInfo.newBuilder();
        monitoringKeysetInfo$Builder0.setAnnotations(primitiveSet0.getAnnotations());
        for(Object object0: primitiveSet0.getAll()) {
            for(Object object1: ((List)object0)) {
                monitoringKeysetInfo$Builder0.addEntry(MonitoringUtil.parseStatus(((Entry)object1).getStatus()), ((Entry)object1).getKeyId(), MonitoringUtil.parseKeyTypeUrl(((Entry)object1).getKeyType()), ((Entry)object1).getOutputPrefixType().name());
            }
        }
        if(primitiveSet0.getPrimary() != null) {
            monitoringKeysetInfo$Builder0.setPrimaryKeyId(primitiveSet0.getPrimary().getKeyId());
        }
        try {
            return monitoringKeysetInfo$Builder0.build();
        }
        catch(GeneralSecurityException generalSecurityException0) {
            throw new IllegalStateException(generalSecurityException0);
        }
    }

    private static String parseKeyTypeUrl(String s) {
        return s.startsWith("type.googleapis.com/google.crypto.") ? s.substring(34) : s;
    }

    private static KeyStatus parseStatus(KeyStatusType keyStatusType0) {
        switch(com.google.crypto.tink.internal.MonitoringUtil.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[keyStatusType0.ordinal()]) {
            case 1: {
                return KeyStatus.ENABLED;
            }
            case 2: {
                return KeyStatus.DISABLED;
            }
            case 3: {
                return KeyStatus.DESTROYED;
            }
            default: {
                throw new IllegalStateException("Unknown key status");
            }
        }
    }

    class com.google.crypto.tink.internal.MonitoringUtil.1 {
        static final int[] $SwitchMap$com$google$crypto$tink$proto$KeyStatusType;

        static {
            int[] arr_v = new int[KeyStatusType.values().length];
            com.google.crypto.tink.internal.MonitoringUtil.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType = arr_v;
            try {
                arr_v[KeyStatusType.ENABLED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.MonitoringUtil.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DISABLED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.google.crypto.tink.internal.MonitoringUtil.1.$SwitchMap$com$google$crypto$tink$proto$KeyStatusType[KeyStatusType.DESTROYED.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

