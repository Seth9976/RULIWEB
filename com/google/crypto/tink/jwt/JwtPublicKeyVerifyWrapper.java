package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.List;

class JwtPublicKeyVerifyWrapper implements PrimitiveWrapper {
    @Immutable
    static class WrappedJwtPublicKeyVerify implements JwtPublicKeyVerify {
        private final PrimitiveSet primitives;

        public WrappedJwtPublicKeyVerify(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
        }

        @Override  // com.google.crypto.tink.jwt.JwtPublicKeyVerify
        public VerifiedJwt verifyAndDecode(String s, JwtValidator jwtValidator0) throws GeneralSecurityException {
            Throwable throwable0 = null;
            for(Object object0: this.primitives.getAll()) {
                for(Object object1: ((List)object0)) {
                    Entry primitiveSet$Entry0 = (Entry)object1;
                    try {
                        return ((JwtPublicKeyVerifyInternal)primitiveSet$Entry0.getPrimitive()).verifyAndDecodeWithKid(s, jwtValidator0, JwtFormat.getKid(primitiveSet$Entry0.getKeyId(), primitiveSet$Entry0.getOutputPrefixType()));
                    }
                    catch(GeneralSecurityException generalSecurityException0) {
                        if(!(generalSecurityException0 instanceof JwtInvalidException)) {
                            continue;
                        }
                        throwable0 = generalSecurityException0;
                    }
                }
            }
            if(throwable0 == null) {
                throw new GeneralSecurityException("invalid JWT");
            }
            throw throwable0;
        }
    }

    private static final JwtPublicKeyVerifyWrapper WRAPPER;

    static {
        JwtPublicKeyVerifyWrapper.WRAPPER = new JwtPublicKeyVerifyWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return JwtPublicKeyVerifyInternal.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return JwtPublicKeyVerify.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(JwtPublicKeyVerifyWrapper.WRAPPER);
    }

    private static void validate(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        for(Object object0: primitiveSet0.getAll()) {
            for(Object object1: ((List)object0)) {
                if(((Entry)object1).getOutputPrefixType() != OutputPrefixType.RAW && ((Entry)object1).getOutputPrefixType() != OutputPrefixType.TINK) {
                    throw new GeneralSecurityException("unsupported OutputPrefixType");
                }
                if(false) {
                    break;
                }
            }
            if(false) {
                break;
            }
        }
    }

    public JwtPublicKeyVerify wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        JwtPublicKeyVerifyWrapper.validate(primitiveSet0);
        return new WrappedJwtPublicKeyVerify(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

