package com.google.crypto.tink.jwt;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

class JwtPublicKeySignWrapper implements PrimitiveWrapper {
    @Immutable
    static class WrappedJwtPublicKeySign implements JwtPublicKeySign {
        private final PrimitiveSet primitives;

        public WrappedJwtPublicKeySign(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
        }

        @Override  // com.google.crypto.tink.jwt.JwtPublicKeySign
        public String signAndEncode(RawJwt rawJwt0) throws GeneralSecurityException {
            Entry primitiveSet$Entry0 = this.primitives.getPrimary();
            Optional optional0 = JwtFormat.getKid(primitiveSet$Entry0.getKeyId(), primitiveSet$Entry0.getOutputPrefixType());
            return ((JwtPublicKeySignInternal)this.primitives.getPrimary().getPrimitive()).signAndEncodeWithKid(rawJwt0, optional0);
        }
    }

    private static final JwtPublicKeySignWrapper WRAPPER;

    static {
        JwtPublicKeySignWrapper.WRAPPER = new JwtPublicKeySignWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return JwtPublicKeySignInternal.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return JwtPublicKeySign.class;
    }

    public static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(JwtPublicKeySignWrapper.WRAPPER);
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

    public JwtPublicKeySign wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        JwtPublicKeySignWrapper.validate(primitiveSet0);
        return new WrappedJwtPublicKeySign(primitiveSet0);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }
}

