package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.ClassTypeConstructorImpl;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public class EnumEntrySyntheticClassDescriptor extends ClassDescriptorBase {
    class EnumEntryScope extends MemberScopeImpl {
        static final boolean $assertionsDisabled;
        private final NotNullLazyValue allDescriptors;
        private final MemoizedFunctionToNotNull functions;
        private final MemoizedFunctionToNotNull properties;

        private static void $$$reportNull$$$0(int v) {
            Object[] arr_object = new Object[(v == 3 || v == 7 || v == 9 || v == 12 || (v == 15 || v == 16 || v == 17 || v == 18 || v == 19) ? 2 : 3)];
            switch(v) {
                case 2: 
                case 6: {
                    arr_object[0] = "location";
                    break;
                }
                case 1: 
                case 4: 
                case 5: 
                case 8: 
                case 10: {
                    arr_object[0] = "name";
                    break;
                }
                case 11: {
                    arr_object[0] = "fromSupertypes";
                    break;
                }
                case 13: {
                    arr_object[0] = "kindFilter";
                    break;
                }
                case 14: {
                    arr_object[0] = "nameFilter";
                    break;
                }
                case 3: 
                case 7: 
                case 9: 
                case 12: 
                case 15: 
                case 16: 
                case 17: 
                case 18: 
                case 19: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope";
                    break;
                }
                case 20: {
                    arr_object[0] = "p";
                    break;
                }
                default: {
                    arr_object[0] = "storageManager";
                }
            }
            switch(v) {
                case 3: {
                    arr_object[1] = "getContributedVariables";
                    break;
                }
                case 7: {
                    arr_object[1] = "getContributedFunctions";
                    break;
                }
                case 9: {
                    arr_object[1] = "getSupertypeScope";
                    break;
                }
                case 12: {
                    arr_object[1] = "resolveFakeOverrides";
                    break;
                }
                case 15: {
                    arr_object[1] = "getContributedDescriptors";
                    break;
                }
                case 16: {
                    arr_object[1] = "computeAllDeclarations";
                    break;
                }
                case 17: {
                    arr_object[1] = "getFunctionNames";
                    break;
                }
                case 18: {
                    arr_object[1] = "getClassifierNames";
                    break;
                }
                case 19: {
                    arr_object[1] = "getVariableNames";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope";
                }
            }
            switch(v) {
                case 1: 
                case 2: {
                    arr_object[2] = "getContributedVariables";
                    break;
                }
                case 4: {
                    arr_object[2] = "computeProperties";
                    break;
                }
                case 5: 
                case 6: {
                    arr_object[2] = "getContributedFunctions";
                    break;
                }
                case 8: {
                    arr_object[2] = "computeFunctions";
                    break;
                }
                case 10: 
                case 11: {
                    arr_object[2] = "resolveFakeOverrides";
                    break;
                }
                case 13: 
                case 14: {
                    arr_object[2] = "getContributedDescriptors";
                    break;
                }
                case 3: 
                case 7: 
                case 9: 
                case 12: 
                case 15: 
                case 16: 
                case 17: 
                case 18: 
                case 19: {
                    break;
                }
                case 20: {
                    arr_object[2] = "printScopeStructure";
                    break;
                }
                default: {
                    arr_object[2] = "<init>";
                }
            }
            String s = String.format((v == 3 || v == 7 || v == 9 || v == 12 || (v == 15 || v == 16 || v == 17 || v == 18 || v == 19) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter \'%s\' of %s.%s must not be null"), arr_object);
            IllegalStateException illegalStateException0 = v == 3 || v == 7 || v == 9 || v == 12 || (v == 15 || v == 16 || v == 17 || v == 18 || v == 19) ? new IllegalStateException(s) : new IllegalArgumentException(s);
            throw illegalStateException0;
        }

        static {
        }

        public EnumEntryScope(StorageManager storageManager0) {
            if(storageManager0 == null) {
                EnumEntryScope.$$$reportNull$$$0(0);
            }
            EnumEntrySyntheticClassDescriptor.this = enumEntrySyntheticClassDescriptor0;
            super();
            this.functions = storageManager0.createMemoizedFunction(new Function1() {
                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Name)object0));
                }

                public Collection invoke(Name name0) {
                    return EnumEntryScope.access$000(EnumEntryScope.this, name0);
                }
            });
            this.properties = storageManager0.createMemoizedFunction(new Function1() {
                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Name)object0));
                }

                public Collection invoke(Name name0) {
                    return EnumEntryScope.access$100(EnumEntryScope.this, name0);
                }
            });
            this.allDescriptors = storageManager0.createLazyValue(new Function0() {
                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public Collection invoke() {
                    return EnumEntryScope.access$200(EnumEntryScope.this);
                }
            });
        }

        static Collection access$000(EnumEntryScope enumEntrySyntheticClassDescriptor$EnumEntryScope0, Name name0) {
            return enumEntrySyntheticClassDescriptor$EnumEntryScope0.computeFunctions(name0);
        }

        static Collection access$100(EnumEntryScope enumEntrySyntheticClassDescriptor$EnumEntryScope0, Name name0) {
            return enumEntrySyntheticClassDescriptor$EnumEntryScope0.computeProperties(name0);
        }

        static Collection access$200(EnumEntryScope enumEntrySyntheticClassDescriptor$EnumEntryScope0) {
            return enumEntrySyntheticClassDescriptor$EnumEntryScope0.computeAllDeclarations();
        }

        private Collection computeAllDeclarations() {
            Collection collection0 = new HashSet();
            for(Object object0: ((Set)EnumEntrySyntheticClassDescriptor.this.enumMemberNames.invoke())) {
                collection0.addAll(this.getContributedFunctions(((Name)object0), NoLookupLocation.FOR_NON_TRACKED_SCOPE));
                collection0.addAll(this.getContributedVariables(((Name)object0), NoLookupLocation.FOR_NON_TRACKED_SCOPE));
            }
            return collection0;
        }

        private Collection computeFunctions(Name name0) {
            if(name0 == null) {
                EnumEntryScope.$$$reportNull$$$0(8);
            }
            return this.resolveFakeOverrides(name0, this.getSupertypeScope().getContributedFunctions(name0, NoLookupLocation.FOR_NON_TRACKED_SCOPE));
        }

        private Collection computeProperties(Name name0) {
            if(name0 == null) {
                EnumEntryScope.$$$reportNull$$$0(4);
            }
            return this.resolveFakeOverrides(name0, this.getSupertypeScope().getContributedVariables(name0, NoLookupLocation.FOR_NON_TRACKED_SCOPE));
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Set getClassifierNames() {
            Set set0 = Collections.EMPTY_SET;
            if(set0 == null) {
                EnumEntryScope.$$$reportNull$$$0(18);
            }
            return set0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
            if(descriptorKindFilter0 == null) {
                EnumEntryScope.$$$reportNull$$$0(13);
            }
            if(function10 == null) {
                EnumEntryScope.$$$reportNull$$$0(14);
            }
            Collection collection0 = (Collection)this.allDescriptors.invoke();
            if(collection0 == null) {
                EnumEntryScope.$$$reportNull$$$0(15);
            }
            return collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
            if(name0 == null) {
                EnumEntryScope.$$$reportNull$$$0(5);
            }
            if(lookupLocation0 == null) {
                EnumEntryScope.$$$reportNull$$$0(6);
            }
            Collection collection0 = (Collection)this.functions.invoke(name0);
            if(collection0 == null) {
                EnumEntryScope.$$$reportNull$$$0(7);
            }
            return collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
            if(name0 == null) {
                EnumEntryScope.$$$reportNull$$$0(1);
            }
            if(lookupLocation0 == null) {
                EnumEntryScope.$$$reportNull$$$0(2);
            }
            Collection collection0 = (Collection)this.properties.invoke(name0);
            if(collection0 == null) {
                EnumEntryScope.$$$reportNull$$$0(3);
            }
            return collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Set getFunctionNames() {
            Set set0 = (Set)EnumEntrySyntheticClassDescriptor.this.enumMemberNames.invoke();
            if(set0 == null) {
                EnumEntryScope.$$$reportNull$$$0(17);
            }
            return set0;
        }

        private MemberScope getSupertypeScope() {
            Object object0 = EnumEntrySyntheticClassDescriptor.this.getTypeConstructor().getSupertypes().iterator().next();
            MemberScope memberScope0 = ((KotlinType)object0).getMemberScope();
            if(memberScope0 == null) {
                EnumEntryScope.$$$reportNull$$$0(9);
            }
            return memberScope0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Set getVariableNames() {
            Set set0 = (Set)EnumEntrySyntheticClassDescriptor.this.enumMemberNames.invoke();
            if(set0 == null) {
                EnumEntryScope.$$$reportNull$$$0(19);
            }
            return set0;
        }

        private Collection resolveFakeOverrides(Name name0, Collection collection0) {
            if(name0 == null) {
                EnumEntryScope.$$$reportNull$$$0(10);
            }
            if(collection0 == null) {
                EnumEntryScope.$$$reportNull$$$0(11);
            }
            Collection collection1 = new LinkedHashSet();
            Set set0 = Collections.EMPTY_SET;
            kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor.EnumEntryScope.4 enumEntrySyntheticClassDescriptor$EnumEntryScope$40 = new NonReportingOverrideStrategy() {
                private static void $$$reportNull$$$0(int v) {
                    Object[] arr_object = new Object[3];
                    switch(v) {
                        case 1: {
                            arr_object[0] = "fromSuper";
                            break;
                        }
                        case 2: {
                            arr_object[0] = "fromCurrent";
                            break;
                        }
                        default: {
                            arr_object[0] = "fakeOverride";
                        }
                    }
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope$4";
                    arr_object[2] = v == 1 || v == 2 ? "conflict" : "addFakeOverride";
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
                }

                @Override  // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                public void addFakeOverride(CallableMemberDescriptor callableMemberDescriptor0) {
                    if(callableMemberDescriptor0 == null) {
                        kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor.EnumEntryScope.4.$$$reportNull$$$0(0);
                    }
                    OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor0, null);
                    ((Set)collection1).add(callableMemberDescriptor0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                protected void conflict(CallableMemberDescriptor callableMemberDescriptor0, CallableMemberDescriptor callableMemberDescriptor1) {
                    if(callableMemberDescriptor0 == null) {
                        kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor.EnumEntryScope.4.$$$reportNull$$$0(1);
                    }
                    if(callableMemberDescriptor1 == null) {
                        kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor.EnumEntryScope.4.$$$reportNull$$$0(2);
                    }
                }
            };
            OverridingUtil.DEFAULT.generateOverridesInFunctionGroup(name0, collection0, set0, EnumEntrySyntheticClassDescriptor.this, enumEntrySyntheticClassDescriptor$EnumEntryScope$40);
            return collection1;
        }
    }

    static final boolean $assertionsDisabled;
    private final Annotations annotations;
    private final NotNullLazyValue enumMemberNames;
    private final MemberScope scope;
    private final TypeConstructor typeConstructor;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: {
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
                arr_object[0] = "enumClass";
                break;
            }
            case 7: {
                arr_object[0] = "containingClass";
                break;
            }
            case 8: {
                arr_object[0] = "supertype";
                break;
            }
            case 2: 
            case 9: {
                arr_object[0] = "name";
                break;
            }
            case 3: 
            case 10: {
                arr_object[0] = "enumMemberNames";
                break;
            }
            case 4: 
            case 11: {
                arr_object[0] = "annotations";
                break;
            }
            case 5: 
            case 12: {
                arr_object[0] = "source";
                break;
            }
            case 13: {
                arr_object[0] = "kotlinTypeRefiner";
                break;
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
                break;
            }
            default: {
                arr_object[0] = "storageManager";
            }
        }
        switch(v) {
            case 14: {
                arr_object[1] = "getUnsubstitutedMemberScope";
                break;
            }
            case 15: {
                arr_object[1] = "getStaticScope";
                break;
            }
            case 16: {
                arr_object[1] = "getConstructors";
                break;
            }
            case 17: {
                arr_object[1] = "getTypeConstructor";
                break;
            }
            case 18: {
                arr_object[1] = "getKind";
                break;
            }
            case 19: {
                arr_object[1] = "getModality";
                break;
            }
            case 20: {
                arr_object[1] = "getVisibility";
                break;
            }
            case 21: {
                arr_object[1] = "getAnnotations";
                break;
            }
            case 22: {
                arr_object[1] = "getDeclaredTypeParameters";
                break;
            }
            case 23: {
                arr_object[1] = "getSealedSubclasses";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor";
            }
        }
        switch(v) {
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: {
                arr_object[2] = "<init>";
                break;
            }
            case 13: {
                arr_object[2] = "getUnsubstitutedMemberScope";
                break;
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: {
                break;
            }
            default: {
                arr_object[2] = "create";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 22: 
            case 23: {
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

    private EnumEntrySyntheticClassDescriptor(StorageManager storageManager0, ClassDescriptor classDescriptor0, KotlinType kotlinType0, Name name0, NotNullLazyValue notNullLazyValue0, Annotations annotations0, SourceElement sourceElement0) {
        if(storageManager0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(6);
        }
        if(classDescriptor0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(7);
        }
        if(kotlinType0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(8);
        }
        if(name0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(9);
        }
        if(notNullLazyValue0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(10);
        }
        if(annotations0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(11);
        }
        if(sourceElement0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(12);
        }
        super(storageManager0, classDescriptor0, name0, sourceElement0, false);
        this.annotations = annotations0;
        this.typeConstructor = new ClassTypeConstructorImpl(this, Collections.EMPTY_LIST, Collections.singleton(kotlinType0), storageManager0);
        this.scope = new EnumEntryScope(this, storageManager0);
        this.enumMemberNames = notNullLazyValue0;
    }

    public static EnumEntrySyntheticClassDescriptor create(StorageManager storageManager0, ClassDescriptor classDescriptor0, Name name0, NotNullLazyValue notNullLazyValue0, Annotations annotations0, SourceElement sourceElement0) {
        if(storageManager0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(0);
        }
        if(classDescriptor0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(2);
        }
        if(notNullLazyValue0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(3);
        }
        if(annotations0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(4);
        }
        if(sourceElement0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(5);
        }
        return new EnumEntrySyntheticClassDescriptor(storageManager0, classDescriptor0, classDescriptor0.getDefaultType(), name0, notNullLazyValue0, annotations0, sourceElement0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        Annotations annotations0 = this.annotations;
        if(annotations0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(21);
        }
        return annotations0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        Collection collection0 = Collections.EMPTY_LIST;
        if(collection0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(16);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getDeclaredTypeParameters() {
        List list0 = Collections.EMPTY_LIST;
        if(list0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(22);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        ClassKind classKind0 = ClassKind.ENUM_ENTRY;
        if(classKind0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(18);
        }
        return classKind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Modality getModality() {
        Modality modality0 = Modality.FINAL;
        if(modality0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(19);
        }
        return modality0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        Collection collection0 = Collections.EMPTY_LIST;
        if(collection0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(23);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        MemberScope memberScope0 = Empty.INSTANCE;
        if(memberScope0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(15);
        }
        return memberScope0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        TypeConstructor typeConstructor0 = this.typeConstructor;
        if(typeConstructor0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(17);
        }
        return typeConstructor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        if(kotlinTypeRefiner0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(13);
        }
        MemberScope memberScope0 = this.scope;
        if(memberScope0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(14);
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
        DescriptorVisibility descriptorVisibility0 = DescriptorVisibilities.PUBLIC;
        if(descriptorVisibility0 == null) {
            EnumEntrySyntheticClassDescriptor.$$$reportNull$$$0(20);
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
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return false;
    }

    @Override
    public String toString() {
        return "enum entry " + this.getName();
    }
}

