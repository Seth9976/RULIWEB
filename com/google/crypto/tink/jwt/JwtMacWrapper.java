package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.List;

class JwtMacWrapper implements PrimitiveWrapper {
    @Immutable
    static class WrappedJwtMac implements JwtMac {
        private final PrimitiveSet primitives;

        private WrappedJwtMac(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
        }

        WrappedJwtMac(PrimitiveSet primitiveSet0, com.google.crypto.tink.jwt.JwtMacWrapper.1 jwtMacWrapper$10) {
            this(primitiveSet0);
        }

        @Override  // com.google.crypto.tink.jwt.JwtMac
        public String computeMacAndEncode(RawJwt rawJwt0) throws GeneralSecurityException {
            Entry primitiveSet$Entry0 = this.primitives.getPrimary();
            return ((JwtMacInternal)primitiveSet$Entry0.getPrimitive()).computeMacAndEncodeWithKid(rawJwt0, JwtFormat.getKid(primitiveSet$Entry0.getKeyId(), primitiveSet$Entry0.getOutputPrefixType()));
        }

        @Override  // com.google.crypto.tink.jwt.JwtMac
        public VerifiedJwt verifyMacAndDecode(String s, JwtValidator jwtValidator0) throws GeneralSecurityException {
            Throwable throwable0 = null;
            for(Object object0: this.primitives.getAll()) {
                for(Object object1: ((List)object0)) {
                    Entry primitiveSet$Entry0 = (Entry)object1;
                    try {
                        return ((JwtMacInternal)primitiveSet$Entry0.getPrimitive()).verifyMacAndDecodeWithKid(s, jwtValidator0, JwtFormat.getKid(primitiveSet$Entry0.getKeyId(), primitiveSet$Entry0.getOutputPrefixType()));
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
                throw new GeneralSecurityException("invalid MAC");
            }
            throw throwable0;
        }
    }

    private static final JwtMacWrapper WRAPPER;

    static {
        JwtMacWrapper.WRAPPER = new JwtMacWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return JwtMacInternal.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return JwtMac.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(JwtMacWrapper.WRAPPER);
    }

    private static void validate(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        if(primitiveSet0.getPrimary() == null) {
            throw new GeneralSecurityException("Primitive set has no primary.");
        }
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

    public JwtMac wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        JwtMacWrapper.validate(primitiveSet0);
        return new WrappedJwtMac(primitiveSet0, null);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }

    class com.google.crypto.tink.jwt.JwtMacWrapper.1 {
    }

}

