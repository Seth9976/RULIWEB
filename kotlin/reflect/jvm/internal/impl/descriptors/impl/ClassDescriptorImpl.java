package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
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

public class ClassDescriptorImpl extends ClassDescriptorBase {
    static final boolean $assertionsDisabled;
    private Set constructors;
    private final ClassKind kind;
    private final Modality modality;
    private ClassConstructorDescriptor primaryConstructor;
    private final TypeConstructor typeConstructor;
    private MemberScope unsubstitutedMemberScope;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
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
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
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
                arr_object[0] = "name";
                break;
            }
            case 2: {
                arr_object[0] = "modality";
                break;
            }
            case 3: {
                arr_object[0] = "kind";
                break;
            }
            case 4: {
                arr_object[0] = "supertypes";
                break;
            }
            case 5: {
                arr_object[0] = "source";
                break;
            }
            case 6: {
                arr_object[0] = "storageManager";
                break;
            }
            case 7: {
                arr_object[0] = "unsubstitutedMemberScope";
                break;
            }
            case 8: {
                arr_object[0] = "constructors";
                break;
            }
            case 12: {
                arr_object[0] = "kotlinTypeRefiner";
                break;
            }
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 9: {
                arr_object[1] = "getAnnotations";
                break;
            }
            case 10: {
                arr_object[1] = "getTypeConstructor";
                break;
            }
            case 11: {
                arr_object[1] = "getConstructors";
                break;
            }
            case 13: {
                arr_object[1] = "getUnsubstitutedMemberScope";
                break;
            }
            case 14: {
                arr_object[1] = "getStaticScope";
                break;
            }
            case 15: {
                arr_object[1] = "getKind";
                break;
            }
            case 16: {
                arr_object[1] = "getModality";
                break;
            }
            case 17: {
                arr_object[1] = "getVisibility";
                break;
            }
            case 18: {
                arr_object[1] = "getDeclaredTypeParameters";
                break;
            }
            case 19: {
                arr_object[1] = "getSealedSubclasses";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/ClassDescriptorImpl";
            }
        }
        switch(v) {
            case 7: 
            case 8: {
                arr_object[2] = "initialize";
                break;
            }
            case 12: {
                arr_object[2] = "getUnsubstitutedMemberScope";
                break;
            }
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
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
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
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

    public ClassDescriptorImpl(DeclarationDescriptor declarationDescriptor0, Name name0, Modality modality0, ClassKind classKind0, Collection collection0, SourceElement sourceElement0, boolean z, StorageManager storageManager0) {
        if(declarationDescriptor0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(name0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(modality0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(classKind0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(collection0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(5);
        }
        if(storageManager0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(6);
        }
        super(storageManager0, declarationDescriptor0, name0, sourceElement0, z);
        this.modality = modality0;
        this.kind = classKind0;
        this.typeConstructor = new ClassTypeConstructorImpl(this, Collections.EMPTY_LIST, collection0, storageManager0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        Annotations annotations0 = Annotations.Companion.getEMPTY();
        if(annotations0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(9);
        }
        return annotations0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        Collection collection0 = this.constructors;
        if(collection0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(11);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getDeclaredTypeParameters() {
        List list0 = Collections.EMPTY_LIST;
        if(list0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(18);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        ClassKind classKind0 = this.kind;
        if(classKind0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(15);
        }
        return classKind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Modality getModality() {
        Modality modality0 = this.modality;
        if(modality0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(16);
        }
        return modality0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        Collection collection0 = Collections.EMPTY_LIST;
        if(collection0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(19);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        MemberScope memberScope0 = Empty.INSTANCE;
        if(memberScope0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(14);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor0 = this.typeConstructor;
        if(typeConstructor0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(10);
        }
        return typeConstructor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        if(kotlinTypeRefiner0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(12);
        }
        MemberScope memberScope0 = this.unsubstitutedMemberScope;
        if(memberScope0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(13);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return this.primaryConstructor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ValueClassRepresentation getValueClassRepresentation() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = DescriptorVisibilities.PUBLIC;
        if(descriptorVisibility0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(17);
        }
        return descriptorVisibility0;
    }

    public final void initialize(MemberScope memberScope0, Set set0, ClassConstructorDescriptor classConstructorDescriptor0) {
        if(memberScope0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(7);
        }
        if(set0 == null) {
            ClassDescriptorImpl.$$$reportNull$$$0(8);
        }
        this.unsubstitutedMemberScope = memberScope0;
        this.constructors = set0;
        this.primaryConstructor = classConstructorDescriptor0;
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
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return false;
    }

    @Override
    public String toString() {
        return "class " + this.getName();
    }
}

