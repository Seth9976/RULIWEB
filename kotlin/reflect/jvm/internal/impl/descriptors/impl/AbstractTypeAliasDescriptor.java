package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public abstract class AbstractTypeAliasDescriptor extends DeclarationDescriptorNonRootImpl implements TypeAliasDescriptor {
    private List declaredTypeParametersImpl;
    private final kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeAliasDescriptor.typeConstructor.1 typeConstructor;
    private final DescriptorVisibility visibilityImpl;

    public AbstractTypeAliasDescriptor(DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, SourceElement sourceElement0, DescriptorVisibility descriptorVisibility0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(sourceElement0, "sourceElement");
        Intrinsics.checkNotNullParameter(descriptorVisibility0, "visibilityImpl");
        super(declarationDescriptor0, annotations0, name0, sourceElement0);
        this.visibilityImpl = descriptorVisibility0;
        this.typeConstructor = new TypeConstructor() {
            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public KotlinBuiltIns getBuiltIns() {
                return DescriptorUtilsKt.getBuiltIns(this.getDeclarationDescriptor());
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public ClassifierDescriptor getDeclarationDescriptor() {
                return this.getDeclarationDescriptor();
            }

            public TypeAliasDescriptor getDeclarationDescriptor() {
                return AbstractTypeAliasDescriptor.this;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public List getParameters() {
                return AbstractTypeAliasDescriptor.this.getTypeConstructorTypeParameters();
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public Collection getSupertypes() {
                Collection collection0 = this.getDeclarationDescriptor().getUnderlyingType().getConstructor().getSupertypes();
                Intrinsics.checkNotNullExpressionValue(collection0, "declarationDescriptor.un…pe.constructor.supertypes");
                return collection0;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public boolean isDenotable() {
                return true;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
            public TypeConstructor refine(KotlinTypeRefiner kotlinTypeRefiner0) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
                return this;
            }

            @Override
            public String toString() {
                return "[typealias " + this.getDeclarationDescriptor().getName().asString() + ']';
            }
        };
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        Intrinsics.checkNotNullParameter(declarationDescriptorVisitor0, "visitor");
        return declarationDescriptorVisitor0.visitTypeAliasDescriptor(this, object0);
    }

    protected final SimpleType computeDefaultType() {
        MemberScope memberScope0;
        ClassDescriptor classDescriptor0 = this.getClassDescriptor();
        if(classDescriptor0 == null) {
            memberScope0 = Empty.INSTANCE;
        }
        else {
            memberScope0 = classDescriptor0.getUnsubstitutedMemberScope();
            if(memberScope0 == null) {
                memberScope0 = Empty.INSTANCE;
            }
        }
        SimpleType simpleType0 = TypeUtils.makeUnsubstitutedType(this, memberScope0, new Function1() {
            {
                AbstractTypeAliasDescriptor.this = abstractTypeAliasDescriptor0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeRefiner)object0));
            }

            public final SimpleType invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                ClassifierDescriptor classifierDescriptor0 = kotlinTypeRefiner0.refineDescriptor(AbstractTypeAliasDescriptor.this);
                return classifierDescriptor0 == null ? null : classifierDescriptor0.getDefaultType();
            }
        });
        Intrinsics.checkNotNullExpressionValue(simpleType0, "@OptIn(TypeRefinement::c…s)?.defaultType\n        }");
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public List getDeclaredTypeParameters() {
        List list0 = this.declaredTypeParametersImpl;
        if(list0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("declaredTypeParametersImpl");
            return null;
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        return Modality.FINAL;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public ClassifierDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl
    public DeclarationDescriptorWithSource getOriginal() {
        return this.getOriginal();
    }

    public TypeAliasDescriptor getOriginal() {
        DeclarationDescriptorWithSource declarationDescriptorWithSource0 = super.getOriginal();
        Intrinsics.checkNotNull(declarationDescriptorWithSource0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeAliasDescriptor");
        return (TypeAliasDescriptor)declarationDescriptorWithSource0;
    }

    protected abstract StorageManager getStorageManager();

    public final Collection getTypeAliasConstructors() {
        ClassDescriptor classDescriptor0 = this.getClassDescriptor();
        if(classDescriptor0 == null) {
            return CollectionsKt.emptyList();
        }
        Collection collection0 = classDescriptor0.getConstructors();
        Intrinsics.checkNotNullExpressionValue(collection0, "classDescriptor.constructors");
        Collection collection1 = new ArrayList();
        for(Object object0: collection0) {
            StorageManager storageManager0 = this.getStorageManager();
            Intrinsics.checkNotNullExpressionValue(((ClassConstructorDescriptor)object0), "it");
            TypeAliasConstructorDescriptor typeAliasConstructorDescriptor0 = TypeAliasConstructorDescriptorImpl.Companion.createIfAvailable(storageManager0, this, ((ClassConstructorDescriptor)object0));
            if(typeAliasConstructorDescriptor0 != null) {
                collection1.add(typeAliasConstructorDescriptor0);
            }
        }
        return (List)collection1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    protected abstract List getTypeConstructorTypeParameters();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility, kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public DescriptorVisibility getVisibility() {
        return this.visibilityImpl;
    }

    public final void initialize(List list0) {
        Intrinsics.checkNotNullParameter(list0, "declaredTypeParameters");
        this.declaredTypeParametersImpl = list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return TypeUtils.contains(this.getUnderlyingType(), new Function1() {
            {
                AbstractTypeAliasDescriptor.this = abstractTypeAliasDescriptor0;
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullExpressionValue(unwrappedType0, "type");
                if(!KotlinTypeKt.isError(unwrappedType0)) {
                    ClassifierDescriptor classifierDescriptor0 = unwrappedType0.getConstructor().getDeclarationDescriptor();
                    return !(classifierDescriptor0 instanceof TypeParameterDescriptor) || Intrinsics.areEqual(((TypeParameterDescriptor)classifierDescriptor0).getContainingDeclaration(), AbstractTypeAliasDescriptor.this) ? false : true;
                }
                return false;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        return "typealias " + this.getName().asString();
    }
}

