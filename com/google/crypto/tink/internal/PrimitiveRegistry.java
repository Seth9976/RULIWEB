package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveWrapper;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PrimitiveRegistry {
    public static final class Builder {
        private final Map primitiveConstructorMap;
        private final Map primitiveWrapperMap;

        public Builder() {
            this.primitiveConstructorMap = new HashMap();
            this.primitiveWrapperMap = new HashMap();
        }

        public Builder(PrimitiveRegistry primitiveRegistry0) {
            this.primitiveConstructorMap = new HashMap(primitiveRegistry0.primitiveConstructorMap);
            this.primitiveWrapperMap = new HashMap(primitiveRegistry0.primitiveWrapperMap);
        }

        static Map access$400(Builder primitiveRegistry$Builder0) {
            return primitiveRegistry$Builder0.primitiveConstructorMap;
        }

        static Map access$500(Builder primitiveRegistry$Builder0) {
            return primitiveRegistry$Builder0.primitiveWrapperMap;
        }

        PrimitiveRegistry build() {
            return new PrimitiveRegistry(this, null);
        }

        public Builder registerPrimitiveConstructor(PrimitiveConstructor primitiveConstructor0) throws GeneralSecurityException {
            if(primitiveConstructor0 == null) {
                throw new NullPointerException("primitive constructor must be non-null");
            }
            PrimitiveConstructorIndex primitiveRegistry$PrimitiveConstructorIndex0 = new PrimitiveConstructorIndex(primitiveConstructor0.getKeyClass(), primitiveConstructor0.getPrimitiveClass(), null);
            if(this.primitiveConstructorMap.containsKey(primitiveRegistry$PrimitiveConstructorIndex0)) {
                PrimitiveConstructor primitiveConstructor1 = (PrimitiveConstructor)this.primitiveConstructorMap.get(primitiveRegistry$PrimitiveConstructorIndex0);
                if(!primitiveConstructor1.equals(primitiveConstructor0) || !primitiveConstructor0.equals(primitiveConstructor1)) {
                    throw new GeneralSecurityException("Attempt to register non-equal PrimitiveConstructor object for already existing object of type: " + primitiveRegistry$PrimitiveConstructorIndex0);
                }
                return this;
            }
            this.primitiveConstructorMap.put(primitiveRegistry$PrimitiveConstructorIndex0, primitiveConstructor0);
            return this;
        }

        public Builder registerPrimitiveWrapper(PrimitiveWrapper primitiveWrapper0) throws GeneralSecurityException {
            if(primitiveWrapper0 == null) {
                throw new NullPointerException("wrapper must be non-null");
            }
            Class class0 = primitiveWrapper0.getPrimitiveClass();
            if(this.primitiveWrapperMap.containsKey(class0)) {
                PrimitiveWrapper primitiveWrapper1 = (PrimitiveWrapper)this.primitiveWrapperMap.get(class0);
                if(!primitiveWrapper1.equals(primitiveWrapper0) || !primitiveWrapper0.equals(primitiveWrapper1)) {
                    throw new GeneralSecurityException("Attempt to register non-equal PrimitiveWrapper object or input class object for already existing object of type" + class0);
                }
                return this;
            }
            this.primitiveWrapperMap.put(class0, primitiveWrapper0);
            return this;
        }
    }

    static final class PrimitiveConstructorIndex {
        private final Class keyClass;
        private final Class primitiveClass;

        private PrimitiveConstructorIndex(Class class0, Class class1) {
            this.keyClass = class0;
            this.primitiveClass = class1;
        }

        PrimitiveConstructorIndex(Class class0, Class class1, com.google.crypto.tink.internal.PrimitiveRegistry.1 primitiveRegistry$10) {
            this(class0, class1);
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof PrimitiveConstructorIndex ? ((PrimitiveConstructorIndex)object0).keyClass.equals(this.keyClass) && ((PrimitiveConstructorIndex)object0).primitiveClass.equals(this.primitiveClass) : false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.keyClass, this.primitiveClass});
        }

        @Override
        public String toString() {
            return this.keyClass.getSimpleName() + " with primitive type: " + this.primitiveClass.getSimpleName();
        }
    }

    private final Map primitiveConstructorMap;
    private final Map primitiveWrapperMap;

    private PrimitiveRegistry(Builder primitiveRegistry$Builder0) {
        this.primitiveConstructorMap = new HashMap(Builder.access$400(primitiveRegistry$Builder0));
        this.primitiveWrapperMap = new HashMap(Builder.access$500(primitiveRegistry$Builder0));
    }

    PrimitiveRegistry(Builder primitiveRegistry$Builder0, com.google.crypto.tink.internal.PrimitiveRegistry.1 primitiveRegistry$10) {
        this(primitiveRegistry$Builder0);
    }

    public Class getInputPrimitiveClass(Class class0) throws GeneralSecurityException {
        if(!this.primitiveWrapperMap.containsKey(class0)) {
            throw new GeneralSecurityException("No input primitive class for " + class0 + " available");
        }
        return ((PrimitiveWrapper)this.primitiveWrapperMap.get(class0)).getInputPrimitiveClass();
    }

    public Object getPrimitive(Key key0, Class class0) throws GeneralSecurityException {
        PrimitiveConstructorIndex primitiveRegistry$PrimitiveConstructorIndex0 = new PrimitiveConstructorIndex(key0.getClass(), class0, null);
        if(!this.primitiveConstructorMap.containsKey(primitiveRegistry$PrimitiveConstructorIndex0)) {
            throw new GeneralSecurityException("No PrimitiveConstructor for " + primitiveRegistry$PrimitiveConstructorIndex0 + " available");
        }
        return ((PrimitiveConstructor)this.primitiveConstructorMap.get(primitiveRegistry$PrimitiveConstructorIndex0)).constructPrimitive(key0);
    }

    public Object wrap(PrimitiveSet primitiveSet0, Class class0) throws GeneralSecurityException {
        if(!this.primitiveWrapperMap.containsKey(class0)) {
            throw new GeneralSecurityException("No wrapper found for " + class0);
        }
        PrimitiveWrapper primitiveWrapper0 = (PrimitiveWrapper)this.primitiveWrapperMap.get(class0);
        if(!primitiveSet0.getPrimitiveClass().equals(primitiveWrapper0.getInputPrimitiveClass()) || !primitiveWrapper0.getInputPrimitiveClass().equals(primitiveSet0.getPrimitiveClass())) {
            throw new GeneralSecurityException("Input primitive type of the wrapper doesn\'t match the type of primitives in the provided PrimitiveSet");
        }
        return primitiveWrapper0.wrap(primitiveSet0);
    }

    class com.google.crypto.tink.internal.PrimitiveRegistry.1 {
    }

}

