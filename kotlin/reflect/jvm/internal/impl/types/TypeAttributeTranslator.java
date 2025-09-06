package kotlin.reflect.jvm.internal.impl.types;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;

public interface TypeAttributeTranslator {
    public static final class DefaultImpls {
        public static TypeAttributes toAttributes$default(TypeAttributeTranslator typeAttributeTranslator0, Annotations annotations0, TypeConstructor typeConstructor0, DeclarationDescriptor declarationDescriptor0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toAttributes");
            }
            if((v & 2) != 0) {
                typeConstructor0 = null;
            }
            if((v & 4) != 0) {
                declarationDescriptor0 = null;
            }
            return typeAttributeTranslator0.toAttributes(annotations0, typeConstructor0, declarationDescriptor0);
        }
    }

    TypeAttributes toAttributes(Annotations arg1, TypeConstructor arg2, DeclarationDescriptor arg3);
}

