package com.google.crypto.tink.mac;

import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import com.google.crypto.tink.Registry;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChunkedMacWrapper implements PrimitiveWrapper {
    @Immutable
    static class WrappedChunkedMac implements ChunkedMac {
        private final PrimitiveSet primitives;

        private WrappedChunkedMac(PrimitiveSet primitiveSet0) {
            this.primitives = primitiveSet0;
        }

        WrappedChunkedMac(PrimitiveSet primitiveSet0, com.google.crypto.tink.mac.ChunkedMacWrapper.1 chunkedMacWrapper$10) {
            this(primitiveSet0);
        }

        @Override  // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacComputation createComputation() throws GeneralSecurityException {
            return this.getChunkedMac(this.primitives.getPrimary()).createComputation();
        }

        @Override  // com.google.crypto.tink.mac.ChunkedMac
        public ChunkedMacVerification createVerification(byte[] arr_b) throws GeneralSecurityException {
            byte[] arr_b1 = Arrays.copyOf(arr_b, 5);
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: this.primitives.getPrimitive(arr_b1)) {
                arrayList0.add(this.getChunkedMac(((Entry)object0)).createVerification(arr_b));
            }
            for(Object object1: this.primitives.getRawPrimitives()) {
                arrayList0.add(this.getChunkedMac(((Entry)object1)).createVerification(arr_b));
            }
            return new WrappedChunkedMacVerification(arrayList0, null);
        }

        private ChunkedMac getChunkedMac(Entry primitiveSet$Entry0) {
            return (ChunkedMac)primitiveSet$Entry0.getFullPrimitive();
        }
    }

    static class WrappedChunkedMacVerification implements ChunkedMacVerification {
        private final List verifications;

        private WrappedChunkedMacVerification(List list0) {
            this.verifications = list0;
        }

        WrappedChunkedMacVerification(List list0, com.google.crypto.tink.mac.ChunkedMacWrapper.1 chunkedMacWrapper$10) {
            this(list0);
        }

        @Override  // com.google.crypto.tink.mac.ChunkedMacVerification
        public void update(ByteBuffer byteBuffer0) throws GeneralSecurityException {
            ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
            byteBuffer1.mark();
            for(Object object0: this.verifications) {
                byteBuffer1.reset();
                ((ChunkedMacVerification)object0).update(byteBuffer1);
            }
            byteBuffer0.position(byteBuffer0.limit());
        }

        @Override  // com.google.crypto.tink.mac.ChunkedMacVerification
        public void verifyMac() throws GeneralSecurityException {
            GeneralSecurityException generalSecurityException0 = new GeneralSecurityException("MAC verification failed for all suitable keys in keyset");
            for(Object object0: this.verifications) {
                ChunkedMacVerification chunkedMacVerification0 = (ChunkedMacVerification)object0;
                try {
                    chunkedMacVerification0.verifyMac();
                    return;
                }
                catch(GeneralSecurityException generalSecurityException1) {
                    generalSecurityException0.addSuppressed(generalSecurityException1);
                }
            }
            throw generalSecurityException0;
        }
    }

    private static final ChunkedMacWrapper WRAPPER;

    static {
        ChunkedMacWrapper.WRAPPER = new ChunkedMacWrapper();
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getInputPrimitiveClass() {
        return ChunkedMac.class;
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Class getPrimitiveClass() {
        return ChunkedMac.class;
    }

    static void register() throws GeneralSecurityException {
        Registry.registerPrimitiveWrapper(ChunkedMacWrapper.WRAPPER);
    }

    public ChunkedMac wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        if(primitiveSet0 == null) {
            throw new GeneralSecurityException("primitive set must be non-null");
        }
        if(primitiveSet0.getPrimary() == null) {
            throw new GeneralSecurityException("no primary in primitive set");
        }
        for(Object object0: primitiveSet0.getAll()) {
            for(Object object1: ((List)object0)) {
                ChunkedMac chunkedMac0 = (ChunkedMac)((Entry)object1).getFullPrimitive();
            }
        }
        return new WrappedChunkedMac(primitiveSet0, null);
    }

    @Override  // com.google.crypto.tink.PrimitiveWrapper
    public Object wrap(PrimitiveSet primitiveSet0) throws GeneralSecurityException {
        return this.wrap(primitiveSet0);
    }

    class com.google.crypto.tink.mac.ChunkedMacWrapper.1 {
    }

}

