package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public abstract class ClassDescriptorBase extends AbstractClassDescriptor {
    private final DeclarationDescriptor containingDeclaration;
    private final boolean isExternal;
    private final SourceElement source;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 4 || v == 5 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "containingDeclaration";
                break;
            }
            case 2: {
                arr_object[0] = "name";
                break;
            }
            case 3: {
                arr_object[0] = "source";
                break;
            }
            case 4: 
            case 5: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
                break;
            }
            default: {
                arr_object[0] = "storageManager";
            }
        }
        switch(v) {
            case 4: {
                arr_object[1] = "getContainingDeclaration";
                break;
            }
            case 5: {
                arr_object[1] = "getSource";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorBase";
            }
        }
        if(v != 4 && v != 5) {
            arr_object[2] = "<init>";
        }
        String s = String.format((v == 4 || v == 5 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 4 || v == 5 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    protected ClassDescriptorBase(StorageManager storageManager0, DeclarationDescriptor declarationDescriptor0, Name name0, SourceElement sourceElement0, boolean z) {
        if(storageManager0 == null) {
            ClassDescriptorBase.$$$reportNull$$$0(0);
        }
        if(declarationDescriptor0 == null) {
            ClassDescriptorBase.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            ClassDescriptorBase.$$$reportNull$$$0(2);
        }
        if(sourceElement0 == null) {
            ClassDescriptorBase.$$$reportNull$$$0(3);
        }
        super(storageManager0, name0);
        this.containingDeclaration = declarationDescriptor0;
        this.source = sourceElement0;
        this.isExternal = z;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor0 = this.containingDeclaration;
        if(declarationDescriptor0 == null) {
            ClassDescriptorBase.$$$reportNull$$$0(4);
        }
        return declarationDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        SourceElement sourceElement0 = this.source;
        if(sourceElement0 == null) {
            ClassDescriptorBase.$$$reportNull$$$0(5);
        }
        return sourceElement0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return this.isExternal;
    }
}

