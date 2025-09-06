package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

public interface EngineWrapper {
    public static class TCipher implements EngineWrapper {
        @Override  // com.google.crypto.tink.subtle.EngineWrapper
        public Object getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return this.getInstance(s, provider0);
        }

        public Cipher getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return provider0 == null ? Cipher.getInstance(s) : Cipher.getInstance(s, provider0);
        }
    }

    public static class TKeyAgreement implements EngineWrapper {
        @Override  // com.google.crypto.tink.subtle.EngineWrapper
        public Object getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return this.getInstance(s, provider0);
        }

        public KeyAgreement getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return provider0 == null ? KeyAgreement.getInstance(s) : KeyAgreement.getInstance(s, provider0);
        }
    }

    public static class TKeyFactory implements EngineWrapper {
        @Override  // com.google.crypto.tink.subtle.EngineWrapper
        public Object getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return this.getInstance(s, provider0);
        }

        public KeyFactory getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return provider0 == null ? KeyFactory.getInstance(s) : KeyFactory.getInstance(s, provider0);
        }
    }

    public static class TKeyPairGenerator implements EngineWrapper {
        @Override  // com.google.crypto.tink.subtle.EngineWrapper
        public Object getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return this.getInstance(s, provider0);
        }

        public KeyPairGenerator getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return provider0 == null ? KeyPairGenerator.getInstance(s) : KeyPairGenerator.getInstance(s, provider0);
        }
    }

    public static class TMac implements EngineWrapper {
        @Override  // com.google.crypto.tink.subtle.EngineWrapper
        public Object getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return this.getInstance(s, provider0);
        }

        public Mac getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return provider0 == null ? Mac.getInstance(s) : Mac.getInstance(s, provider0);
        }
    }

    public static class TMessageDigest implements EngineWrapper {
        @Override  // com.google.crypto.tink.subtle.EngineWrapper
        public Object getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return this.getInstance(s, provider0);
        }

        public MessageDigest getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return provider0 == null ? MessageDigest.getInstance(s) : MessageDigest.getInstance(s, provider0);
        }
    }

    public static class TSignature implements EngineWrapper {
        @Override  // com.google.crypto.tink.subtle.EngineWrapper
        public Object getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return this.getInstance(s, provider0);
        }

        public Signature getInstance(String s, Provider provider0) throws GeneralSecurityException {
            return provider0 == null ? Signature.getInstance(s) : Signature.getInstance(s, provider0);
        }
    }

    Object getInstance(String arg1, Provider arg2) throws GeneralSecurityException;
}

