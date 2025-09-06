package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.SubstitutingScope;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.DefaultTypeAttributeTranslator;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public class LazySubstitutingClassDescriptor extends ModuleAwareClassDescriptor {
    static final boolean $assertionsDisabled;
    private List declaredTypeParameters;
    private TypeSubstitutor newSubstitutor;
    private final ModuleAwareClassDescriptor original;
    private final TypeSubstitutor originalSubstitutor;
    private TypeConstructor typeConstructor;
    private List typeConstructorParameters;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 2: 
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 10: 
            case 13: 
            case 23: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
                break;
            }
            default: {
                s = "@NotNull method %s.%s must not return null";
            }
        }
        switch(v) {
            case 2: 
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 10: 
            case 13: 
            case 23: {
                v1 = 3;
                break;
            }
            default: {
                v1 = 2;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 2: 
            case 8: {
                arr_object[0] = "typeArguments";
                break;
            }
            case 5: 
            case 10: {
                arr_object[0] = "typeSubstitution";
                break;
            }
            case 3: 
            case 6: 
            case 13: {
                arr_object[0] = "kotlinTypeRefiner";
                break;
            }
            case 23: {
                arr_object[0] = "substitutor";
                break;
            }
            default: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazySubstitutingClassDescriptor";
            }
        }
        switch(v) {
            case 4: 
            case 7: 
            case 9: 
            case 11: {
                arr_object[1] = "getMemberScope";
                break;
            }
            case 12: 
            case 14: {
                arr_object[1] = "getUnsubstitutedMemberScope";
                break;
            }
            case 15: {
                arr_object[1] = "getStaticScope";
                break;
            }
            case 16: {
                arr_object[1] = "getDefaultType";
                break;
            }
            case 17: {
                arr_object[1] = "getContextReceivers";
                break;
            }
            case 18: {
                arr_object[1] = "getConstructors";
                break;
            }
            case 19: {
                arr_object[1] = "getAnnotations";
                break;
            }
            case 20: {
                arr_object[1] = "getName";
                break;
            }
            case 21: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 22: {
                arr_object[1] = "getContainingDeclaration";
                break;
            }
            case 2: 
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 10: 
            case 13: 
            case 23: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazySubstitutingClassDescriptor";
                break;
            }
            case 24: {
                arr_object[1] = "substitute";
                break;
            }
            case 25: {
                arr_object[1] = "getKind";
                break;
            }
            case 26: {
                arr_object[1] = "getModality";
                break;
            }
            case 27: {
                arr_object[1] = "getVisibility";
                break;
            }
            case 28: {
                arr_object[1] = "getUnsubstitutedInnerClassesScope";
                break;
            }
            case 29: {
                arr_object[1] = "getSource";
                break;
            }
            case 30: {
                arr_object[1] = "getDeclaredTypeParameters";
                break;
            }
            case 0x1F: {
                arr_object[1] = "getSealedSubclasses";
                break;
            }
            default: {
                arr_object[1] = "getTypeConstructor";
            }
        }
        switch(v) {
            case 2: 
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 10: {
                arr_object[2] = "getMemberScope";
                break;
            }
            case 13: {
                arr_object[2] = "getUnsubstitutedMemberScope";
                break;
            }
            case 23: {
                arr_object[2] = "substitute";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 2: 
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 10: 
            case 13: 
            case 23: {
                illegalArgumentException0 = new IllegalArgumentException(s1);
                break;
            }
            default: {
                illegalArgumentException0 = new IllegalStateException(s1);
            }
        }
        throw illegalArgumentException0;
    }

    static {
    }

    public LazySubstitutingClassDescriptor(ModuleAwareClassDescriptor moduleAwareClassDescriptor0, TypeSubstitutor typeSubstitutor0) {
        this.original = moduleAwareClassDescriptor0;
        this.originalSubstitutor = typeSubstitutor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitClassDescriptor(this, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        Annotations annotations0 = this.original.getAnnotations();
        if(annotations0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(19);
        }
        return annotations0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getCompanionObjectDescriptor() {
        return this.original.getCompanionObjectDescriptor();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        Collection collection0 = this.original.getConstructors();
        Collection collection1 = new ArrayList(collection0.size());
        for(Object object0: collection0) {
            collection1.add(((ClassConstructorDescriptor)((ClassConstructorDescriptor)object0).newCopyBuilder().setOriginal(((ClassConstructorDescriptor)object0).getOriginal()).setModality(((ClassConstructorDescriptor)object0).getModality()).setVisibility(((ClassConstructorDescriptor)object0).getVisibility()).setKind(((ClassConstructorDescriptor)object0).getKind()).setCopyOverrides(false).build()).substitute(this.getSubstitutor()));
        }
        return collection1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        DeclarationDescriptor declarationDescriptor0 = this.original.getContainingDeclaration();
        if(declarationDescriptor0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(22);
        }
        return declarationDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getContextReceivers() {
        List list0 = Collections.EMPTY_LIST;
        if(list0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(17);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getDeclaredTypeParameters() {
        this.getSubstitutor();
        List list0 = this.declaredTypeParameters;
        if(list0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(30);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public SimpleType getDefaultType() {
        List list0 = TypeUtils.getDefaultTypeProjections(this.getTypeConstructor().getParameters());
        Annotations annotations0 = this.getAnnotations();
        SimpleType simpleType0 = KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(DefaultTypeAttributeTranslator.INSTANCE.toAttributes(annotations0, null, null), this.getTypeConstructor(), list0, false, this.getUnsubstitutedMemberScope());
        if(simpleType0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(16);
        }
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        ClassKind classKind0 = this.original.getKind();
        if(classKind0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(25);
        }
        return classKind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getMemberScope(TypeSubstitution typeSubstitution0) {
        if(typeSubstitution0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(10);
        }
        MemberScope memberScope0 = this.getMemberScope(typeSubstitution0, DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtils.getContainingModule(this)));
        if(memberScope0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(11);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getMemberScope(TypeSubstitution typeSubstitution0, KotlinTypeRefiner kotlinTypeRefiner0) {
        if(typeSubstitution0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(5);
        }
        if(kotlinTypeRefiner0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(6);
        }
        MemberScope memberScope0 = this.original.getMemberScope(typeSubstitution0, kotlinTypeRefiner0);
        if(this.originalSubstitutor.isEmpty()) {
            if(memberScope0 == null) {
                LazySubstitutingClassDescriptor.$$$reportNull$$$0(7);
            }
            return memberScope0;
        }
        return new SubstitutingScope(memberScope0, this.getSubstitutor());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Modality getModality() {
        Modality modality0 = this.original.getModality();
        if(modality0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(26);
        }
        return modality0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Named
    public Name getName() {
        Name name0 = this.original.getName();
        if(name0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(20);
        }
        return name0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getOriginal() {
        ClassDescriptor classDescriptor0 = this.original.getOriginal();
        if(classDescriptor0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(21);
        }
        return classDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public ClassifierDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        Collection collection0 = this.original.getSealedSubclasses();
        if(collection0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(0x1F);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        SourceElement sourceElement0 = SourceElement.NO_SOURCE;
        if(sourceElement0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(29);
        }
        return sourceElement0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        MemberScope memberScope0 = this.original.getStaticScope();
        if(memberScope0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(15);
        }
        return memberScope0;
    }

    private TypeSubstitutor getSubstitutor() {
        if(this.newSubstitutor == null) {
            if(this.originalSubstitutor.isEmpty()) {
                this.newSubstitutor = this.originalSubstitutor;
                return this.newSubstitutor;
            }
            List list0 = this.original.getTypeConstructor().getParameters();
            this.typeConstructorParameters = new ArrayList(list0.size());
            this.newSubstitutor = DescriptorSubstitutor.substituteTypeParameters(list0, this.originalSubstitutor.getSubstitution(), this, this.typeConstructorParameters);
            this.declaredTypeParameters = CollectionsKt.filter(this.typeConstructorParameters, new Function1() {
                public Boolean invoke(TypeParameterDescriptor typeParameterDescriptor0) {
                    return Boolean.valueOf(!typeParameterDescriptor0.isCapturedFromOuterDeclaration());
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((TypeParameterDescriptor)object0));
                }
            });
        }
        return this.newSubstitutor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ReceiverParameterDescriptor getThisAsReceiverParameter() {
        throw new UnsupportedOperationException();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor0 = this.original.getTypeConstructor();
        if(this.originalSubstitutor.isEmpty()) {
            if(typeConstructor0 == null) {
                LazySubstitutingClassDescriptor.$$$reportNull$$$0(0);
            }
            return typeConstructor0;
        }
        if(this.typeConstructor == null) {
            TypeSubstitutor typeSubstitutor0 = this.getSubstitutor();
            Collection collection0 = typeConstructor0.getSupertypes();
            ArrayList arrayList0 = new ArrayList(collection0.size());
            for(Object object0: collection0) {
                arrayList0.add(typeSubstitutor0.substitute(((KotlinType)object0), Variance.INVARIANT));
            }
            this.typeConstructor = new ClassTypeConstructorImpl(this, this.typeConstructorParameters, arrayList0, LockBasedStorageManager.NO_LOCKS);
        }
        TypeConstructor typeConstructor1 = this.typeConstructor;
        if(typeConstructor1 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(1);
        }
        return typeConstructor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getUnsubstitutedInnerClassesScope() {
        MemberScope memberScope0 = this.original.getUnsubstitutedInnerClassesScope();
        if(memberScope0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(28);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getUnsubstitutedMemberScope() {
        MemberScope memberScope0 = this.getUnsubstitutedMemberScope(DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtils.getContainingModule(this.original)));
        if(memberScope0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(12);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        if(kotlinTypeRefiner0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(13);
        }
        MemberScope memberScope0 = this.original.getUnsubstitutedMemberScope(kotlinTypeRefiner0);
        if(this.originalSubstitutor.isEmpty()) {
            if(memberScope0 == null) {
                LazySubstitutingClassDescriptor.$$$reportNull$$$0(14);
            }
            return memberScope0;
        }
        return new SubstitutingScope(memberScope0, this.getSubstitutor());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return this.original.getUnsubstitutedPrimaryConstructor();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ValueClassRepresentation getValueClassRepresentation() {
        ValueClassRepresentation valueClassRepresentation0 = this.original.getValueClassRepresentation();
        return valueClassRepresentation0 == null ? null : valueClassRepresentation0.mapUnderlyingType(new Function1() {
            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SimpleType)object0));
            }

            public SimpleType invoke(SimpleType simpleType0) {
                return LazySubstitutingClassDescriptor.this.substituteSimpleType(simpleType0);
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = this.original.getVisibility();
        if(descriptorVisibility0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(27);
        }
        return descriptorVisibility0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return this.original.isActual();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return this.original.isCompanionObject();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        return this.original.isData();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return this.original.isExpect();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return this.original.isExternal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        return this.original.isFun();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        return this.original.isInline();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return this.original.isInner();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return this.original.isValue();
    }

    public ClassDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        if(typeSubstitutor0 == null) {
            LazySubstitutingClassDescriptor.$$$reportNull$$$0(23);
        }
        return typeSubstitutor0.isEmpty() ? this : new LazySubstitutingClassDescriptor(this, TypeSubstitutor.createChainedSubstitutor(typeSubstitutor0.getSubstitution(), this.getSubstitutor().getSubstitution()));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    private SimpleType substituteSimpleType(SimpleType simpleType0) {
        return simpleType0 == null || this.originalSubstitutor.isEmpty() ? simpleType0 : ((SimpleType)this.getSubstitutor().substitute(simpleType0, Variance.INVARIANT));
    }
}

