package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorEquivalenceForOverrides;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.LazyScopeAdapter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.TypeIntersectionScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public abstract class AbstractTypeParameterDescriptor extends DeclarationDescriptorNonRootImpl implements TypeParameterDescriptor {
    class TypeParameterTypeConstructor extends AbstractTypeConstructor {
        private final SupertypeLoopChecker supertypeLoopChecker;

        private static void $$$reportNull$$$0(int v) {
            IllegalStateException illegalStateException0;
            int v1;
            String s;
            switch(v) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 8: {
                    s = "@NotNull method %s.%s must not return null";
                    break;
                }
                default: {
                    s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
                }
            }
            switch(v) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 8: {
                    v1 = 2;
                    break;
                }
                default: {
                    v1 = 3;
                }
            }
            Object[] arr_object = new Object[v1];
            switch(v) {
                case 6: {
                    arr_object[0] = "type";
                    break;
                }
                case 7: {
                    arr_object[0] = "supertypes";
                    break;
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 8: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
                    break;
                }
                case 9: {
                    arr_object[0] = "classifier";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            switch(v) {
                case 1: {
                    arr_object[1] = "computeSupertypes";
                    break;
                }
                case 2: {
                    arr_object[1] = "getParameters";
                    break;
                }
                case 3: {
                    arr_object[1] = "getDeclarationDescriptor";
                    break;
                }
                case 4: {
                    arr_object[1] = "getBuiltIns";
                    break;
                }
                case 5: {
                    arr_object[1] = "getSupertypeLoopChecker";
                    break;
                }
                case 8: {
                    arr_object[1] = "processSupertypesWithoutCycles";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor$TypeParameterTypeConstructor";
                }
            }
            switch(v) {
                case 6: {
                    arr_object[2] = "reportSupertypeLoopError";
                    break;
                }
                case 7: {
                    arr_object[2] = "processSupertypesWithoutCycles";
                    break;
                }
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 8: {
                    break;
                }
                case 9: {
                    arr_object[2] = "isSameClassifier";
                    break;
                }
                default: {
                    arr_object[2] = "<init>";
                }
            }
            String s1 = String.format(s, arr_object);
            switch(v) {
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 8: {
                    illegalStateException0 = new IllegalStateException(s1);
                    break;
                }
                default: {
                    illegalStateException0 = new IllegalArgumentException(s1);
                }
            }
            throw illegalStateException0;
        }

        public TypeParameterTypeConstructor(StorageManager storageManager0, SupertypeLoopChecker supertypeLoopChecker0) {
            if(storageManager0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(0);
            }
            AbstractTypeParameterDescriptor.this = abstractTypeParameterDescriptor0;
            super(storageManager0);
            this.supertypeLoopChecker = supertypeLoopChecker0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected Collection computeSupertypes() {
            Collection collection0 = AbstractTypeParameterDescriptor.this.resolveUpperBounds();
            if(collection0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(1);
            }
            return collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected KotlinType defaultSupertypeIfEmpty() {
            return ErrorUtils.createErrorType(ErrorTypeKind.CYCLIC_UPPER_BOUNDS, new String[0]);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public KotlinBuiltIns getBuiltIns() {
            KotlinBuiltIns kotlinBuiltIns0 = DescriptorUtilsKt.getBuiltIns(AbstractTypeParameterDescriptor.this);
            if(kotlinBuiltIns0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(4);
            }
            return kotlinBuiltIns0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.ClassifierBasedTypeConstructor
        public ClassifierDescriptor getDeclarationDescriptor() {
            ClassifierDescriptor classifierDescriptor0 = AbstractTypeParameterDescriptor.this;
            if(classifierDescriptor0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(3);
            }
            return classifierDescriptor0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List getParameters() {
            List list0 = Collections.EMPTY_LIST;
            if(list0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(2);
            }
            return list0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            SupertypeLoopChecker supertypeLoopChecker0 = this.supertypeLoopChecker;
            if(supertypeLoopChecker0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(5);
            }
            return supertypeLoopChecker0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.ClassifierBasedTypeConstructor
        protected boolean isSameClassifier(ClassifierDescriptor classifierDescriptor0) {
            if(classifierDescriptor0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(9);
            }
            return classifierDescriptor0 instanceof TypeParameterDescriptor && DescriptorEquivalenceForOverrides.INSTANCE.areTypeParametersEquivalent(AbstractTypeParameterDescriptor.this, ((TypeParameterDescriptor)classifierDescriptor0), true);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected List processSupertypesWithoutCycles(List list0) {
            if(list0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(7);
            }
            List list1 = AbstractTypeParameterDescriptor.this.processBoundsWithoutCycles(list0);
            if(list1 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(8);
            }
            return list1;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected void reportSupertypeLoopError(KotlinType kotlinType0) {
            if(kotlinType0 == null) {
                TypeParameterTypeConstructor.$$$reportNull$$$0(6);
            }
            AbstractTypeParameterDescriptor.this.reportSupertypeLoopError(kotlinType0);
        }

        @Override
        public String toString() {
            return AbstractTypeParameterDescriptor.this.getName().toString();
        }
    }

    private final NotNullLazyValue defaultType;
    private final int index;
    private final boolean reified;
    private final StorageManager storageManager;
    private final NotNullLazyValue typeConstructor;
    private final Variance variance;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: {
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
            case 12: {
                arr_object[0] = "bounds";
                break;
            }
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
                break;
            }
            default: {
                arr_object[0] = "storageManager";
            }
        }
        switch(v) {
            case 7: {
                arr_object[1] = "getVariance";
                break;
            }
            case 8: {
                arr_object[1] = "getUpperBounds";
                break;
            }
            case 9: {
                arr_object[1] = "getTypeConstructor";
                break;
            }
            case 10: {
                arr_object[1] = "getDefaultType";
                break;
            }
            case 11: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 13: {
                arr_object[1] = "processBoundsWithoutCycles";
                break;
            }
            case 14: {
                arr_object[1] = "getStorageManager";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/AbstractTypeParameterDescriptor";
            }
        }
        switch(v) {
            case 12: {
                arr_object[2] = "processBoundsWithoutCycles";
                break;
            }
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: {
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 13: 
            case 14: {
                illegalArgumentException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalArgumentException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalArgumentException0;
    }

    protected AbstractTypeParameterDescriptor(StorageManager storageManager0, DeclarationDescriptor declarationDescriptor0, Annotations annotations0, Name name0, Variance variance0, boolean z, int v, SourceElement sourceElement0, SupertypeLoopChecker supertypeLoopChecker0) {
        if(storageManager0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(0);
        }
        if(declarationDescriptor0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(1);
        }
        if(annotations0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(2);
        }
        if(name0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(3);
        }
        if(variance0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(5);
        }
        if(supertypeLoopChecker0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(6);
        }
        super(declarationDescriptor0, annotations0, name0, sourceElement0);
        this.variance = variance0;
        this.reified = z;
        this.index = v;
        this.typeConstructor = storageManager0.createLazyValue(new Function0() {
            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public TypeConstructor invoke() {
                return new TypeParameterTypeConstructor(AbstractTypeParameterDescriptor.this, storageManager0, supertypeLoopChecker0);
            }
        });
        this.defaultType = storageManager0.createLazyValue(new Function0() {
            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public SimpleType invoke() {
                return KotlinTypeFactory.simpleTypeWithNonTrivialMemberScope(TypeAttributes.Companion.getEmpty(), AbstractTypeParameterDescriptor.this.getTypeConstructor(), Collections.EMPTY_LIST, false, new LazyScopeAdapter(new Function0() {
                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return this.invoke();
                    }

                    public MemberScope invoke() {
                        return TypeIntersectionScope.create(("Scope for type parameter " + kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractTypeParameterDescriptor.2.this.val$name.asString()), AbstractTypeParameterDescriptor.this.getUpperBounds());
                    }
                }));
            }
        });
        this.storageManager = storageManager0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitTypeParameterDescriptor(this, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public SimpleType getDefaultType() {
        SimpleType simpleType0 = (SimpleType)this.defaultType.invoke();
        if(simpleType0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(10);
        }
        return simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public int getIndex() {
        return this.index;
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

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public TypeParameterDescriptor getOriginal() {
        TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)super.getOriginal();
        if(typeParameterDescriptor0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(11);
        }
        return typeParameterDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public StorageManager getStorageManager() {
        StorageManager storageManager0 = this.storageManager;
        if(storageManager0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(14);
        }
        return storageManager0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public final TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor0 = (TypeConstructor)this.typeConstructor.invoke();
        if(typeConstructor0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(9);
        }
        return typeConstructor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public List getUpperBounds() {
        List list0 = ((TypeParameterTypeConstructor)this.getTypeConstructor()).getSupertypes();
        if(list0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(8);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public Variance getVariance() {
        Variance variance0 = this.variance;
        if(variance0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(7);
        }
        return variance0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public boolean isCapturedFromOuterDeclaration() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor
    public boolean isReified() {
        return this.reified;
    }

    protected List processBoundsWithoutCycles(List list0) {
        if(list0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(12);
        }
        if(list0 == null) {
            AbstractTypeParameterDescriptor.$$$reportNull$$$0(13);
        }
        return list0;
    }

    protected abstract void reportSupertypeLoopError(KotlinType arg1);

    protected abstract List resolveUpperBounds();
}

