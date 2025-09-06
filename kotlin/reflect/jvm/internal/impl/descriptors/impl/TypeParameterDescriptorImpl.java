package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.Variance;

public class TypeParameterDescriptorImpl extends AbstractTypeParameterDescriptor {
    private boolean initialized;
    private final Function1 reportCycleError;
    private final List upperBounds;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 5 || v == 28 ? 2 : 3)];
        switch(v) {
            case 17: {
                arr_object[0] = "supertypeLoopsResolver";
                break;
            }
            case 1: 
            case 7: 
            case 13: 
            case 20: {
                arr_object[0] = "annotations";
                break;
            }
            case 2: 
            case 8: 
            case 14: 
            case 21: {
                arr_object[0] = "variance";
                break;
            }
            case 3: 
            case 9: 
            case 15: 
            case 22: {
                arr_object[0] = "name";
                break;
            }
            case 10: 
            case 16: 
            case 23: {
                arr_object[0] = "source";
                break;
            }
            case 24: {
                arr_object[0] = "supertypeLoopsChecker";
                break;
            }
            case 4: 
            case 11: 
            case 18: 
            case 25: {
                arr_object[0] = "storageManager";
                break;
            }
            case 26: {
                arr_object[0] = "bound";
                break;
            }
            case 27: {
                arr_object[0] = "type";
                break;
            }
            case 5: 
            case 28: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 5: {
                arr_object[1] = "createWithDefaultBound";
                break;
            }
            case 28: {
                arr_object[1] = "resolveUpperBounds";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/TypeParameterDescriptorImpl";
            }
        }
        switch(v) {
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: {
                arr_object[2] = "createForFurtherModification";
                break;
            }
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: 
            case 24: 
            case 25: {
                arr_object[2] = "<init>";
                break;
            }
            case 26: {
                arr_object[2] = "addUpperBound";
                break;
            }
            case 27: {
                arr_object[2] = "reportSupertypeLoopError";
                break;
            }
            case 5: 
            case 28: {
                break;
            }
            default: {
                arr_object[2] = "createWithDefaultBound";
            }
        }
        String s = String.format((v == 5 || v == 28 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 5 || v == 28 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    private TypeParameterDescriptorImpl(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, boolean z, Variance variance0, Name name0, int v, SourceElement sourceElement0, Function1 function10, SupertypeLoopChecker supertypeLoopChecker0, StorageManager storageManager0) {
        if(declarationDescriptor0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(19);
        }
        if(annotations0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(20);
        }
        if(variance0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(21);
        }
        if(name0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(22);
        }
        if(sourceElement0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(23);
        }
        if(supertypeLoopChecker0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(24);
        }
        if(storageManager0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(25);
        }
        super(storageManager0, declarationDescriptor0, annotations0, name0, variance0, z, v, sourceElement0, supertypeLoopChecker0);
        this.upperBounds = new ArrayList(1);
        this.initialized = false;
        this.reportCycleError = function10;
    }

    public void addUpperBound(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(26);
        }
        this.checkUninitialized();
        this.doAddUpperBound(kotlinType0);
    }

    private void checkInitialized() {
        if(!this.initialized) {
            throw new IllegalStateException("Type parameter descriptor is not initialized: " + this.nameForAssertions());
        }
    }

    private void checkUninitialized() {
        if(this.initialized) {
            throw new IllegalStateException("Type parameter descriptor is already initialized: " + this.nameForAssertions());
        }
    }

    public static TypeParameterDescriptorImpl createForFurtherModification(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, boolean z, Variance variance0, Name name0, int v, SourceElement sourceElement0, Function1 function10, SupertypeLoopChecker supertypeLoopChecker0, StorageManager storageManager0) {
        if(declarationDescriptor0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(12);
        }
        if(annotations0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(13);
        }
        if(variance0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(14);
        }
        if(name0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(15);
        }
        if(sourceElement0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(16);
        }
        if(supertypeLoopChecker0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(17);
        }
        if(storageManager0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(18);
        }
        return new TypeParameterDescriptorImpl(declarationDescriptor0, annotations0, z, variance0, name0, v, sourceElement0, function10, supertypeLoopChecker0, storageManager0);
    }

    public static TypeParameterDescriptorImpl createForFurtherModification(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, boolean z, Variance variance0, Name name0, int v, SourceElement sourceElement0, StorageManager storageManager0) {
        if(declarationDescriptor0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(6);
        }
        if(annotations0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(7);
        }
        if(variance0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(8);
        }
        if(name0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(9);
        }
        if(sourceElement0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(10);
        }
        if(storageManager0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(11);
        }
        return TypeParameterDescriptorImpl.createForFurtherModification(declarationDescriptor0, annotations0, z, variance0, name0, v, sourceElement0, null, EMPTY.INSTANCE, storageManager0);
    }

    public static TypeParameterDescriptor createWithDefaultBound(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, boolean z, Variance variance0, Name name0, int v, StorageManager storageManager0) {
        if(declarationDescriptor0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(variance0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(name0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(storageManager0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(4);
        }
        TypeParameterDescriptor typeParameterDescriptor0 = TypeParameterDescriptorImpl.createForFurtherModification(declarationDescriptor0, annotations0, z, variance0, name0, v, SourceElement.NO_SOURCE, storageManager0);
        ((TypeParameterDescriptorImpl)typeParameterDescriptor0).addUpperBound(DescriptorUtilsKt.getBuiltIns(declarationDescriptor0).getDefaultBound());
        ((TypeParameterDescriptorImpl)typeParameterDescriptor0).setInitialized();
        if(typeParameterDescriptor0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(5);
        }
        return typeParameterDescriptor0;
    }

    private void doAddUpperBound(KotlinType kotlinType0) {
        if(KotlinTypeKt.isError(kotlinType0)) {
            return;
        }
        this.upperBounds.add(kotlinType0);
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    private String nameForAssertions() {
        return this.getName() + " declared in " + DescriptorUtils.getFqName(this.getContainingDeclaration());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected void reportSupertypeLoopError(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(27);
        }
        Function1 function10 = this.reportCycleError;
        if(function10 == null) {
            return;
        }
        function10.invoke(kotlinType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor
    protected List resolveUpperBounds() {
        this.checkInitialized();
        List list0 = this.upperBounds;
        if(list0 == null) {
            TypeParameterDescriptorImpl.$$$reportNull$$$0(28);
        }
        return list0;
    }

    public void setInitialized() {
        this.checkUninitialized();
        this.initialized = true;
    }
}

