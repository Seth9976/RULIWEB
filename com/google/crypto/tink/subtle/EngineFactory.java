package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

public final class EngineFactory {
    static class AndroidPolicy implements Policy {
        private final EngineWrapper jceFactory;

        private AndroidPolicy(EngineWrapper engineWrapper0) {
            this.jceFactory = engineWrapper0;
        }

        AndroidPolicy(EngineWrapper engineWrapper0, com.google.crypto.tink.subtle.EngineFactory.1 engineFactory$10) {
            this(engineWrapper0);
        }

        @Override  // com.google.crypto.tink.subtle.EngineFactory$Policy
        public Object getInstance(String s) throws GeneralSecurityException {
            Exception exception0 = null;
            for(Object object0: EngineFactory.toProviderList(new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL"})) {
                Provider provider0 = (Provider)object0;
                try {
                    return this.jceFactory.getInstance(s, provider0);
                }
                catch(Exception exception1) {
                    if(exception0 != null) {
                        continue;
                    }
                    exception0 = exception1;
                }
            }
            return this.jceFactory.getInstance(s, null);
        }

        @Override  // com.google.crypto.tink.subtle.EngineFactory$Policy
        public Object getInstance(String s, List list0) throws GeneralSecurityException {
            return this.getInstance(s);
        }
    }

    static class DefaultPolicy implements Policy {
        private final EngineWrapper jceFactory;

        private DefaultPolicy(EngineWrapper engineWrapper0) {
            this.jceFactory = engineWrapper0;
        }

        DefaultPolicy(EngineWrapper engineWrapper0, com.google.crypto.tink.subtle.EngineFactory.1 engineFactory$10) {
            this(engineWrapper0);
        }

        @Override  // com.google.crypto.tink.subtle.EngineFactory$Policy
        public Object getInstance(String s) throws GeneralSecurityException {
            return this.jceFactory.getInstance(s, null);
        }

        @Override  // com.google.crypto.tink.subtle.EngineFactory$Policy
        public Object getInstance(String s, List list0) throws GeneralSecurityException {
            for(Object object0: list0) {
                Provider provider0 = (Provider)object0;
                try {
                    return this.jceFactory.getInstance(s, provider0);
                }
                catch(Exception unused_ex) {
                }
            }
            return this.getInstance(s);
        }
    }

    static class FipsPolicy implements Policy {
        private final EngineWrapper jceFactory;

        private FipsPolicy(EngineWrapper engineWrapper0) {
            this.jceFactory = engineWrapper0;
        }

        FipsPolicy(EngineWrapper engineWrapper0, com.google.crypto.tink.subtle.EngineFactory.1 engineFactory$10) {
            this(engineWrapper0);
        }

        @Override  // com.google.crypto.tink.subtle.EngineFactory$Policy
        public Object getInstance(String s) throws GeneralSecurityException {
            Throwable throwable0 = null;
            for(Object object0: EngineFactory.toProviderList(new String[]{"GmsCore_OpenSSL", "AndroidOpenSSL", "Conscrypt"})) {
                Provider provider0 = (Provider)object0;
                try {
                    return this.jceFactory.getInstance(s, provider0);
                }
                catch(Exception exception0) {
                    if(throwable0 != null) {
                        continue;
                    }
                    throwable0 = exception0;
                }
            }
            throw new GeneralSecurityException("No good Provider found.", throwable0);
        }

        @Override  // com.google.crypto.tink.subtle.EngineFactory$Policy
        public Object getInstance(String s, List list0) throws GeneralSecurityException {
            return this.getInstance(s);
        }
    }

    interface Policy {
        Object getInstance(String arg1) throws GeneralSecurityException;

        Object getInstance(String arg1, List arg2) throws GeneralSecurityException;
    }

    public static final EngineFactory CIPHER;
    public static final EngineFactory KEY_AGREEMENT;
    public static final EngineFactory KEY_FACTORY;
    public static final EngineFactory KEY_PAIR_GENERATOR;
    public static final EngineFactory MAC;
    public static final EngineFactory MESSAGE_DIGEST;
    public static final EngineFactory SIGNATURE;
    private final Policy policy;

    static {
        EngineFactory.CIPHER = new EngineFactory(new TCipher());
        EngineFactory.MAC = new EngineFactory(new TMac());
        EngineFactory.SIGNATURE = new EngineFactory(new TSignature());
        EngineFactory.MESSAGE_DIGEST = new EngineFactory(new TMessageDigest());
        EngineFactory.KEY_AGREEMENT = new EngineFactory(new TKeyAgreement());
        EngineFactory.KEY_PAIR_GENERATOR = new EngineFactory(new TKeyPairGenerator());
        EngineFactory.KEY_FACTORY = new EngineFactory(new TKeyFactory());
    }

    public EngineFactory(EngineWrapper engineWrapper0) {
        if(TinkFipsUtil.useOnlyFips()) {
            this.policy = new FipsPolicy(engineWrapper0, null);
            return;
        }
        if(SubtleUtil.isAndroid()) {
            this.policy = new AndroidPolicy(engineWrapper0, null);
            return;
        }
        this.policy = new DefaultPolicy(engineWrapper0, null);
    }

    public Object getInstance(String s) throws GeneralSecurityException {
        return this.policy.getInstance(s);
    }

    Object getInstance(String s, List list0) throws GeneralSecurityException {
        return this.policy.getInstance(s, list0);
    }

    public static List toProviderList(String[] arr_s) {
        List list0 = new ArrayList();
        for(int v = 0; v < arr_s.length; ++v) {
            Provider provider0 = Security.getProvider(arr_s[v]);
            if(provider0 != null) {
                list0.add(provider0);
            }
        }
        return list0;
    }

    class com.google.crypto.tink.subtle.EngineFactory.1 {
    }

}

