package kotlin.reflect.jvm.internal.impl.types;

import java.util.HashMap;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public class DescriptorSubstitutor {
    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 4 ? 2 : 3)];
        switch(v) {
            case 4: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
                break;
            }
            case 1: 
            case 6: {
                arr_object[0] = "originalSubstitution";
                break;
            }
            case 2: 
            case 7: {
                arr_object[0] = "newContainingDeclaration";
                break;
            }
            case 3: 
            case 8: {
                arr_object[0] = "result";
                break;
            }
            default: {
                arr_object[0] = "typeParameters";
            }
        }
        arr_object[1] = v == 4 ? "substituteTypeParameters" : "kotlin/reflect/jvm/internal/impl/types/DescriptorSubstitutor";
        if(v != 4) {
            arr_object[2] = "substituteTypeParameters";
        }
        String s = String.format((v == 4 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalArgumentException illegalArgumentException0 = v == 4 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalArgumentException0;
    }

    public static TypeSubstitutor substituteTypeParameters(List list0, TypeSubstitution typeSubstitution0, DeclarationDescriptor declarationDescriptor0, List list1) {
        if(list0 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(0);
        }
        if(typeSubstitution0 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(1);
        }
        if(declarationDescriptor0 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(2);
        }
        if(list1 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(3);
        }
        TypeSubstitutor typeSubstitutor0 = DescriptorSubstitutor.substituteTypeParameters(list0, typeSubstitution0, declarationDescriptor0, list1, null);
        if(typeSubstitutor0 == null) {
            throw new AssertionError("Substitution failed");
        }
        if(typeSubstitutor0 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(4);
        }
        return typeSubstitutor0;
    }

    public static TypeSubstitutor substituteTypeParameters(List list0, TypeSubstitution typeSubstitution0, DeclarationDescriptor declarationDescriptor0, List list1, boolean[] arr_z) {
        if(list0 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(5);
        }
        if(typeSubstitution0 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(6);
        }
        if(declarationDescriptor0 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(7);
        }
        if(list1 == null) {
            DescriptorSubstitutor.$$$reportNull$$$0(8);
        }
        HashMap hashMap0 = new HashMap();
        HashMap hashMap1 = new HashMap();
        int v = 0;
        for(Object object0: list0) {
            Annotations annotations0 = ((TypeParameterDescriptor)object0).getAnnotations();
            boolean z = ((TypeParameterDescriptor)object0).isReified();
            Variance variance0 = ((TypeParameterDescriptor)object0).getVariance();
            Name name0 = ((TypeParameterDescriptor)object0).getName();
            StorageManager storageManager0 = ((TypeParameterDescriptor)object0).getStorageManager();
            TypeParameterDescriptorImpl typeParameterDescriptorImpl0 = TypeParameterDescriptorImpl.createForFurtherModification(declarationDescriptor0, annotations0, z, variance0, name0, v, SourceElement.NO_SOURCE, storageManager0);
            hashMap0.put(((TypeParameterDescriptor)object0).getTypeConstructor(), new TypeProjectionImpl(typeParameterDescriptorImpl0.getDefaultType()));
            hashMap1.put(((TypeParameterDescriptor)object0), typeParameterDescriptorImpl0);
            list1.add(typeParameterDescriptorImpl0);
            ++v;
        }
        TypeConstructorSubstitution typeConstructorSubstitution0 = TypeConstructorSubstitution.createByConstructorsMap(hashMap0);
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.createChainedSubstitutor(typeSubstitution0, typeConstructorSubstitution0);
        TypeSubstitutor typeSubstitutor1 = TypeSubstitutor.createChainedSubstitutor(typeSubstitution0.replaceWithNonApproximating(), typeConstructorSubstitution0);
        for(Object object1: list0) {
            TypeParameterDescriptorImpl typeParameterDescriptorImpl1 = (TypeParameterDescriptorImpl)hashMap1.get(((TypeParameterDescriptor)object1));
            for(Object object2: ((TypeParameterDescriptor)object1).getUpperBounds()) {
                ClassifierDescriptor classifierDescriptor0 = ((KotlinType)object2).getConstructor().getDeclarationDescriptor();
                KotlinType kotlinType0 = (!(classifierDescriptor0 instanceof TypeParameterDescriptor) || !TypeUtilsKt.hasTypeParameterRecursiveBounds(((TypeParameterDescriptor)classifierDescriptor0)) ? typeSubstitutor1 : typeSubstitutor0).substitute(((KotlinType)object2), Variance.OUT_VARIANCE);
                if(kotlinType0 == null) {
                    return null;
                }
                if(kotlinType0 != ((KotlinType)object2) && arr_z != null) {
                    arr_z[0] = true;
                }
                typeParameterDescriptorImpl1.addUpperBound(kotlinType0);
            }
            typeParameterDescriptorImpl1.setInitialized();
        }
        return typeSubstitutor0;
    }
}

