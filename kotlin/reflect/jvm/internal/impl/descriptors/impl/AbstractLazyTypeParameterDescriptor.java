package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public abstract class AbstractLazyTypeParameterDescriptor extends AbstractTypeParameterDescriptor {
    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[3];
        switch(v) {
            case 1: {
                arr_object[0] = "containingDeclaration";
                break;
            }
            case 2: {
                arr_object[0] = "annotations";
                break;
            }
            case 3: {
                arr_object[0] = "name";
                break;
            }
            case 4: {
                arr_object[0] = "variance";
                break;
            }
            case 5: {
                arr_object[0] = "source";
                break;
            }
            case 6: {
                arr_object[0] = "supertypeLoopChecker";
                break;
            }
            default: {
                arr_object[0] = "storageManager";
            }
        }
        arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractLazyTypeParameterDescriptor";
        arr_object[2] = "<init>";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
    }

    public AbstractLazyTypeParameterDescriptor(StorageManager storageManager0, DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, Variance variance0, boolean z, int v, SourceElement sourceElement0, SupertypeLoopChecker supertypeLoopChecker0) {
        if(storageManager0 == null) {
            AbstractLazyTypeParameterDescriptor.$$$reportNull$$$0(0);
        }
        if(declarationDescriptor0 == null) {
            AbstractLazyTypeParameterDescriptor.$$$reportNull$$$0(1);
        }
        if(annotations0 == null) {
            AbstractLazyTypeParameterDescriptor.$$$reportNull$$$0(2);
        }
        if(name0 == null) {
            AbstractLazyTypeParameterDescriptor.$$$reportNull$$$0(3);
        }
        if(variance0 == null) {
            AbstractLazyTypeParameterDescriptor.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            AbstractLazyTypeParameterDescriptor.$$$reportNull$$$0(5);
        }
        if(supertypeLoopChecker0 == null) {
            AbstractLazyTypeParameterDescriptor.$$$reportNull$$$0(6);
        }
        super(storageManager0, declarationDescriptor0, annotations0, name0, variance0, z, v, sourceElement0, supertypeLoopChecker0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        String s = "";
        String s1 = this.isReified() ? "reified " : "";
        if(this.getVariance() != Variance.INVARIANT) {
            s = this.getVariance() + " ";
        }
        return String.format("%s%s%s", s1, s, this.getName());
    }
}

