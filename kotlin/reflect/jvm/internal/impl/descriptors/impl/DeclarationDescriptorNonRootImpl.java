package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;

public abstract class DeclarationDescriptorNonRootImpl extends DeclarationDescriptorImpl implements DeclarationDescriptorNonRoot {
    private final DeclarationDescriptor containingDeclaration;
    private final SourceElement source;

    private static void $$$reportNull$$$0(int v) {
        Object[] arr_object = new Object[(v == 4 || v == 5 || v == 6 ? 2 : 3)];
        switch(v) {
            case 1: {
                arr_object[0] = "annotations";
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
            case 5: 
            case 6: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 4: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 5: {
                arr_object[1] = "getContainingDeclaration";
                break;
            }
            case 6: {
                arr_object[1] = "getSource";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/DeclarationDescriptorNonRootImpl";
            }
        }
        if(v != 4 && v != 5 && v != 6) {
            arr_object[2] = "<init>";
        }
        String s = String.format((v == 4 || v == 5 || v == 6 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
        IllegalStateException illegalStateException0 = v == 4 || v == 5 || v == 6 ? new IllegalStateException(s) : new IllegalArgumentException(s);
        throw illegalStateException0;
    }

    protected DeclarationDescriptorNonRootImpl(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            DeclarationDescriptorNonRootImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            DeclarationDescriptorNonRootImpl.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            DeclarationDescriptorNonRootImpl.$$$reportNull$$$0(2);
        }
        if(sourceElement0 == null) {
            DeclarationDescriptorNonRootImpl.$$$reportNull$$$0(3);
        }
        super(annotations0, name0);
        this.containingDeclaration = declarationDescriptor0;
        this.source = sourceElement0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor0 = this.containingDeclaration;
        if(declarationDescriptor0 == null) {
            DeclarationDescriptorNonRootImpl.$$$reportNull$$$0(5);
        }
        return declarationDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    public DeclarationDescriptorWithSource getOriginal() {
        DeclarationDescriptorWithSource declarationDescriptorWithSource0 = (DeclarationDescriptorWithSource)super.getOriginal();
        if(declarationDescriptorWithSource0 == null) {
            DeclarationDescriptorNonRootImpl.$$$reportNull$$$0(4);
        }
        return declarationDescriptorWithSource0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        SourceElement sourceElement0 = this.source;
        if(sourceElement0 == null) {
            DeclarationDescriptorNonRootImpl.$$$reportNull$$$0(6);
        }
        return sourceElement0;
    }
}

