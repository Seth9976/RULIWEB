package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.InnerClassesScopeWrapper;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.SubstitutingScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public abstract class AbstractClassDescriptor extends ModuleAwareClassDescriptor {
    static final boolean $assertionsDisabled;
    protected final NotNullLazyValue defaultType;
    private final Name name;
    private final NotNullLazyValue thisAsReceiverParameter;
    private final NotNullLazyValue unsubstitutedInnerClassesScope;

    private static void $$$reportNull$$$0(int v) {
        IllegalStateException illegalStateException0;
        int v1;
        String s;
        switch(v) {
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 9: 
            case 12: 
            case 14: 
            case 16: 
            case 17: 
            case 19: 
            case 20: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 9: 
            case 12: 
            case 14: 
            case 16: 
            case 17: 
            case 19: 
            case 20: {
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
            case 8: 
            case 11: {
                arr_object[0] = "kotlinTypeRefiner";
                break;
            }
            case 7: 
            case 13: {
                arr_object[0] = "typeArguments";
                break;
            }
            case 10: 
            case 15: {
                arr_object[0] = "typeSubstitution";
                break;
            }
            case 18: {
                arr_object[0] = "substitutor";
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 9: 
            case 12: 
            case 14: 
            case 16: 
            case 17: 
            case 19: 
            case 20: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
                break;
            }
            default: {
                arr_object[0] = "storageManager";
            }
        }
        switch(v) {
            case 2: {
                arr_object[1] = "getName";
                break;
            }
            case 3: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 4: {
                arr_object[1] = "getUnsubstitutedInnerClassesScope";
                break;
            }
            case 5: {
                arr_object[1] = "getThisAsReceiverParameter";
                break;
            }
            case 6: {
                arr_object[1] = "getContextReceivers";
                break;
            }
            case 9: 
            case 12: 
            case 14: 
            case 16: {
                arr_object[1] = "getMemberScope";
                break;
            }
            case 17: {
                arr_object[1] = "getUnsubstitutedMemberScope";
                break;
            }
            case 19: {
                arr_object[1] = "substitute";
                break;
            }
            case 20: {
                arr_object[1] = "getDefaultType";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractClassDescriptor";
            }
        }
        switch(v) {
            case 7: 
            case 8: 
            case 10: 
            case 11: 
            case 13: 
            case 15: {
                arr_object[2] = "getMemberScope";
                break;
            }
            case 18: {
                arr_object[2] = "substitute";
                break;
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 9: 
            case 12: 
            case 14: 
            case 16: 
            case 17: 
            case 19: 
            case 20: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 9: 
            case 12: 
            case 14: 
            case 16: 
            case 17: 
            case 19: 
            case 20: {
                illegalStateException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalStateException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalStateException0;
    }

    static {
    }

    public AbstractClassDescriptor(StorageManager storageManager0, Name name0) {
        if(storageManager0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(0);
        }
        if(name0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(1);
        }
        super();
        this.name = name0;
        this.defaultType = storageManager0.createLazyValue(new Function0() {
            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public SimpleType invoke() {
                MemberScope memberScope0 = AbstractClassDescriptor.this.getUnsubstitutedMemberScope();
                kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor.1.1 abstractClassDescriptor$1$10 = new Function1() {
                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        return this.invoke(((KotlinTypeRefiner)object0));
                    }

                    public SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                        ClassifierDescriptor classifierDescriptor0 = kotlinTypeRefiner0.refineDescriptor(AbstractClassDescriptor.this);
                        if(classifierDescriptor0 == null) {
                            return (SimpleType)AbstractClassDescriptor.this.defaultType.invoke();
                        }
                        if(classifierDescriptor0 instanceof TypeAliasDescriptor) {
                            return KotlinTypeFactory.computeExpandedType(((TypeAliasDescriptor)classifierDescriptor0), TypeUtils.getDefaultTypeProjections(classifierDescriptor0.getTypeConstructor().getParameters()));
                        }
                        return classifierDescriptor0 instanceof ModuleAwareClassDescriptor ? TypeUtils.makeUnsubstitutedType(classifierDescriptor0.getTypeConstructor().refine(kotlinTypeRefiner0), ((ModuleAwareClassDescriptor)classifierDescriptor0).getUnsubstitutedMemberScope(kotlinTypeRefiner0), this) : classifierDescriptor0.getDefaultType();
                    }
                };
                return TypeUtils.makeUnsubstitutedType(AbstractClassDescriptor.this, memberScope0, abstractClassDescriptor$1$10);
            }
        });
        this.unsubstitutedInnerClassesScope = storageManager0.createLazyValue(new Function0() {
            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public MemberScope invoke() {
                return new InnerClassesScopeWrapper(AbstractClassDescriptor.this.getUnsubstitutedMemberScope());
            }
        });
        this.thisAsReceiverParameter = storageManager0.createLazyValue(new Function0() {
            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public ReceiverParameterDescriptor invoke() {
                return new LazyClassReceiverParameterDescriptor(AbstractClassDescriptor.this);
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitClassDescriptor(this, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getContextReceivers() {
        List list0 = Collections.EMPTY_LIST;
        if(list0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(6);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public SimpleType getDefaultType() {
        SimpleType simpleType0 = (SimpleType)this.defaultType.invoke();
        if(simpleType0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(20);
        }
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getMemberScope(TypeSubstitution typeSubstitution0) {
        if(typeSubstitution0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(15);
        }
        MemberScope memberScope0 = this.getMemberScope(typeSubstitution0, DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtils.getContainingModule(this)));
        if(memberScope0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(16);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getMemberScope(TypeSubstitution typeSubstitution0, KotlinTypeRefiner kotlinTypeRefiner0) {
        if(typeSubstitution0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(10);
        }
        if(kotlinTypeRefiner0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(11);
        }
        if(typeSubstitution0.isEmpty()) {
            MemberScope memberScope0 = this.getUnsubstitutedMemberScope(kotlinTypeRefiner0);
            if(memberScope0 == null) {
                AbstractClassDescriptor.$$$reportNull$$$0(12);
            }
            return memberScope0;
        }
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.create(typeSubstitution0);
        return new SubstitutingScope(this.getUnsubstitutedMemberScope(kotlinTypeRefiner0), typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Named
    public Name getName() {
        Name name0 = this.name;
        if(name0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(2);
        }
        return name0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getOriginal() [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public ClassifierDescriptor getOriginal() {
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public DeclarationDescriptor getOriginal() {
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ReceiverParameterDescriptor getThisAsReceiverParameter() {
        ReceiverParameterDescriptor receiverParameterDescriptor0 = (ReceiverParameterDescriptor)this.thisAsReceiverParameter.invoke();
        if(receiverParameterDescriptor0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(5);
        }
        return receiverParameterDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getUnsubstitutedInnerClassesScope() {
        MemberScope memberScope0 = (MemberScope)this.unsubstitutedInnerClassesScope.invoke();
        if(memberScope0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(4);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getUnsubstitutedMemberScope() {
        MemberScope memberScope0 = this.getUnsubstitutedMemberScope(DescriptorUtilsKt.getKotlinTypeRefiner(DescriptorUtils.getContainingModule(this)));
        if(memberScope0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(17);
        }
        return memberScope0;
    }

    public ClassDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        if(typeSubstitutor0 == null) {
            AbstractClassDescriptor.$$$reportNull$$$0(18);
        }
        return typeSubstitutor0.isEmpty() ? this : new LazySubstitutingClassDescriptor(this, typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }
}

