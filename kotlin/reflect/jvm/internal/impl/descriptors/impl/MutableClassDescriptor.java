package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public class MutableClassDescriptor extends ClassDescriptorBase {
    static final boolean $assertionsDisabled;
    private final boolean isInner;
    private final ClassKind kind;
    private Modality modality;
    private final StorageManager storageManager;
    private final Collection supertypes;
    private TypeConstructor typeConstructor;
    private List typeParameters;
    private DescriptorVisibility visibility;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 5: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 13: 
            case 15: 
            case 17: 
            case 18: 
            case 19: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 5: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 13: 
            case 15: 
            case 17: 
            case 18: 
            case 19: {
                v1 = 2;
                break;
            }
            default: {
                v1 = 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 1: {
                arr_object[0] = "kind";
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
            case 4: {
                arr_object[0] = "storageManager";
                break;
            }
            case 6: {
                arr_object[0] = "modality";
                break;
            }
            case 9: {
                arr_object[0] = "visibility";
                break;
            }
            case 12: {
                arr_object[0] = "supertype";
                break;
            }
            case 14: {
                arr_object[0] = "typeParameters";
                break;
            }
            case 16: {
                arr_object[0] = "kotlinTypeRefiner";
                break;
            }
            case 5: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 13: 
            case 15: 
            case 17: 
            case 18: 
            case 19: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 5: {
                arr_object[1] = "getAnnotations";
                break;
            }
            case 7: {
                arr_object[1] = "getModality";
                break;
            }
            case 8: {
                arr_object[1] = "getKind";
                break;
            }
            case 10: {
                arr_object[1] = "getVisibility";
                break;
            }
            case 11: {
                arr_object[1] = "getTypeConstructor";
                break;
            }
            case 13: {
                arr_object[1] = "getConstructors";
                break;
            }
            case 15: {
                arr_object[1] = "getDeclaredTypeParameters";
                break;
            }
            case 17: {
                arr_object[1] = "getUnsubstitutedMemberScope";
                break;
            }
            case 18: {
                arr_object[1] = "getStaticScope";
                break;
            }
            case 19: {
                arr_object[1] = "getSealedSubclasses";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/MutableClassDescriptor";
            }
        }
        switch(v) {
            case 6: {
                arr_object[2] = "setModality";
                break;
            }
            case 9: {
                arr_object[2] = "setVisibility";
                break;
            }
            case 12: {
                arr_object[2] = "addSupertype";
                break;
            }
            case 14: {
                arr_object[2] = "setTypeParameterDescriptors";
                break;
            }
            case 16: {
                arr_object[2] = "getUnsubstitutedMemberScope";
                break;
            }
            case 5: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 13: 
            case 15: 
            case 17: 
            case 18: 
            case 19: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 5: 
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 13: 
            case 15: 
            case 17: 
            case 18: 
            case 19: {
                illegalArgumentException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalArgumentException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalArgumentException0;
    }

    static {
    }

    public MutableClassDescriptor(DeclarationDescriptor declarationDescriptor0, ClassKind classKind0, boolean z, boolean z1, Name name0, SourceElement sourceElement0, StorageManager storageManager0) {
        if(declarationDescriptor0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(0);
        }
        if(classKind0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(2);
        }
        if(sourceElement0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(3);
        }
        if(storageManager0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(4);
        }
        super(storageManager0, declarationDescriptor0, name0, sourceElement0, z1);
        this.supertypes = new ArrayList();
        this.storageManager = storageManager0;
        this.kind = classKind0;
        this.isInner = z;
    }

    public void createTypeConstructor() {
        this.typeConstructor = new ClassTypeConstructorImpl(this, this.typeParameters, this.supertypes, this.storageManager);
        for(Object object0: this.getConstructors()) {
            ((ClassConstructorDescriptorImpl)(((FunctionDescriptor)object0))).setReturnType(this.getDefaultType());
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        Annotations annotations0 = Annotations.Companion.getEMPTY();
        if(annotations0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(5);
        }
        return annotations0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        return this.getConstructors();
    }

    public Set getConstructors() {
        Set set0 = Collections.EMPTY_SET;
        if(set0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(13);
        }
        return set0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getDeclaredTypeParameters() {
        List list0 = this.typeParameters;
        if(list0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(15);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        ClassKind classKind0 = this.kind;
        if(classKind0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(8);
        }
        return classKind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Modality getModality() {
        Modality modality0 = this.modality;
        if(modality0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(7);
        }
        return modality0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        Collection collection0 = Collections.EMPTY_LIST;
        if(collection0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(19);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        MemberScope memberScope0 = Empty.INSTANCE;
        if(memberScope0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(18);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor0 = this.typeConstructor;
        if(typeConstructor0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(11);
        }
        return typeConstructor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        if(kotlinTypeRefiner0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(16);
        }
        MemberScope memberScope0 = Empty.INSTANCE;
        if(memberScope0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(17);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ValueClassRepresentation getValueClassRepresentation() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = this.visibility;
        if(descriptorVisibility0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(10);
        }
        return descriptorVisibility0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return this.isInner;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return false;
    }

    public void setModality(Modality modality0) {
        if(modality0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(6);
        }
        this.modality = modality0;
    }

    public void setTypeParameterDescriptors(List list0) {
        if(list0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(14);
        }
        if(this.typeParameters != null) {
            throw new IllegalStateException("Type parameters are already set for " + this.getName());
        }
        this.typeParameters = new ArrayList(list0);
    }

    public void setVisibility(DescriptorVisibility descriptorVisibility0) {
        if(descriptorVisibility0 == null) {
            MutableClassDescriptor.$$$reportNull$$$0(9);
        }
        this.visibility = descriptorVisibility0;
    }

    @Override
    public String toString() {
        return DeclarationDescriptorImpl.toString(this);
    }
}

