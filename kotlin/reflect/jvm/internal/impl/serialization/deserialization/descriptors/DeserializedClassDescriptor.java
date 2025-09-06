package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics.Kotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DeserializedDeclarationsFromSupertypeConflictDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.DeserializedDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.InlineClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker.EMPTY;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ReceiverParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.UtilsKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.EnumEntry;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Visibility;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.BinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.CliSealedClassInheritorsProvider;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.StaticScopeForKotlinEnum;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ContextClassReceiver;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlags;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoEnumFlagsUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.TypeDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ValueClassUtilKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class DeserializedClassDescriptor extends AbstractClassDescriptor implements DeserializedDescriptor {
    final class DeserializedClassMemberScope extends DeserializedMemberScope {
        private final NotNullLazyValue allDescriptors;
        private final KotlinTypeRefiner kotlinTypeRefiner;
        private final NotNullLazyValue refinedSupertypes;

        public DeserializedClassMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
            DeserializedClassDescriptor.this = deserializedClassDescriptor0;
            DeserializationContext deserializationContext0 = deserializedClassDescriptor0.getC();
            List list0 = deserializedClassDescriptor0.getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(list0, "classProto.functionList");
            List list1 = deserializedClassDescriptor0.getClassProto().getPropertyList();
            Intrinsics.checkNotNullExpressionValue(list1, "classProto.propertyList");
            List list2 = deserializedClassDescriptor0.getClassProto().getTypeAliasList();
            Intrinsics.checkNotNullExpressionValue(list2, "classProto.typeAliasList");
            List list3 = deserializedClassDescriptor0.getClassProto().getNestedClassNameList();
            Intrinsics.checkNotNullExpressionValue(list3, "classProto.nestedClassNameList");
            NameResolver nameResolver0 = deserializedClassDescriptor0.getC().getNameResolver();
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            for(Object object0: list3) {
                arrayList0.add(NameResolverUtilKt.getName(nameResolver0, ((Number)object0).intValue()));
            }
            super(deserializationContext0, list0, list1, list2, new Function0() {
                final List $it;

                {
                    this.$it = list0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return this.$it;
                }
            });
            this.kotlinTypeRefiner = kotlinTypeRefiner0;
            this.allDescriptors = this.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    DeserializedClassMemberScope.this = deserializedClassDescriptor$DeserializedClassMemberScope0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Collection invoke() {
                    return DeserializedClassMemberScope.this.computeDescriptors(DescriptorKindFilter.ALL, MemberScope.Companion.getALL_NAME_FILTER(), NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS);
                }
            });
            this.refinedSupertypes = this.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    DeserializedClassMemberScope.this = deserializedClassDescriptor$DeserializedClassMemberScope0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Collection invoke() {
                    return DeserializedClassMemberScope.access$getKotlinTypeRefiner$p(DeserializedClassMemberScope.this).refineSupertypes(DeserializedClassMemberScope.access$getClassDescriptor(DeserializedClassMemberScope.this));
                }
            });
        }

        public static final DeserializedClassDescriptor access$getClassDescriptor(DeserializedClassMemberScope deserializedClassDescriptor$DeserializedClassMemberScope0) {
            return deserializedClassDescriptor$DeserializedClassMemberScope0.getClassDescriptor();
        }

        public static final KotlinTypeRefiner access$getKotlinTypeRefiner$p(DeserializedClassMemberScope deserializedClassDescriptor$DeserializedClassMemberScope0) {
            return deserializedClassDescriptor$DeserializedClassMemberScope0.kotlinTypeRefiner;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void addEnumEntryDescriptors(Collection collection0, Function1 function10) {
            Intrinsics.checkNotNullParameter(collection0, "result");
            Intrinsics.checkNotNullParameter(function10, "nameFilter");
            EnumEntryClassDescriptors deserializedClassDescriptor$EnumEntryClassDescriptors0 = this.getClassDescriptor().enumEntries;
            Collection collection1 = deserializedClassDescriptor$EnumEntryClassDescriptors0 == null ? null : deserializedClassDescriptor$EnumEntryClassDescriptors0.all();
            if(collection1 == null) {
                collection1 = CollectionsKt.emptyList();
            }
            collection0.addAll(collection1);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredFunctions(Name name0, List list0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(list0, "functions");
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: ((Collection)this.refinedSupertypes.invoke())) {
                arrayList0.addAll(((KotlinType)object0).getMemberScope().getContributedFunctions(name0, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            list0.addAll(this.getC().getComponents().getAdditionalClassPartsProvider().getFunctions(name0, DeserializedClassDescriptor.this));
            this.generateFakeOverrides(name0, arrayList0, list0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected void computeNonDeclaredProperties(Name name0, List list0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(list0, "descriptors");
            ArrayList arrayList0 = new ArrayList();
            for(Object object0: ((Collection)this.refinedSupertypes.invoke())) {
                arrayList0.addAll(((KotlinType)object0).getMemberScope().getContributedVariables(name0, NoLookupLocation.FOR_ALREADY_TRACKED));
            }
            this.generateFakeOverrides(name0, arrayList0, list0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected ClassId createClassId(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            ClassId classId0 = DeserializedClassDescriptor.this.classId.createNestedClassId(name0);
            Intrinsics.checkNotNullExpressionValue(classId0, "classId.createNestedClassId(name)");
            return classId0;
        }

        private final void generateFakeOverrides(Name name0, Collection collection0, List list0) {
            ArrayList arrayList0 = new ArrayList(list0);
            this.getC().getComponents().getKotlinTypeChecker().getOverridingUtil().generateOverridesInFunctionGroup(name0, collection0, arrayList0, this.getClassDescriptor(), new NonReportingOverrideStrategy() {
                @Override  // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
                public void addFakeOverride(CallableMemberDescriptor callableMemberDescriptor0) {
                    Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "fakeOverride");
                    OverridingUtil.resolveUnknownVisibilityForMember(callableMemberDescriptor0, null);
                    this.$result.add(callableMemberDescriptor0);
                }

                @Override  // kotlin.reflect.jvm.internal.impl.resolve.NonReportingOverrideStrategy
                protected void conflict(CallableMemberDescriptor callableMemberDescriptor0, CallableMemberDescriptor callableMemberDescriptor1) {
                    Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "fromSuper");
                    Intrinsics.checkNotNullParameter(callableMemberDescriptor1, "fromCurrent");
                    if(callableMemberDescriptor1 instanceof FunctionDescriptorImpl) {
                        ((FunctionDescriptorImpl)callableMemberDescriptor1).putInUserDataMap(DeserializedDeclarationsFromSupertypeConflictDataKey.INSTANCE, callableMemberDescriptor0);
                    }
                }
            });
        }

        private final DeserializedClassDescriptor getClassDescriptor() {
            return DeserializedClassDescriptor.this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            this.recordLookup(name0, lookupLocation0);
            EnumEntryClassDescriptors deserializedClassDescriptor$EnumEntryClassDescriptors0 = this.getClassDescriptor().enumEntries;
            if(deserializedClassDescriptor$EnumEntryClassDescriptors0 != null) {
                ClassDescriptor classDescriptor0 = deserializedClassDescriptor$EnumEntryClassDescriptors0.findEnumEntry(name0);
                if(classDescriptor0 != null) {
                    return classDescriptor0;
                }
            }
            return super.getContributedClassifier(name0, lookupLocation0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
            Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
            Intrinsics.checkNotNullParameter(function10, "nameFilter");
            return (Collection)this.allDescriptors.invoke();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            this.recordLookup(name0, lookupLocation0);
            return super.getContributedFunctions(name0, lookupLocation0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            this.recordLookup(name0, lookupLocation0);
            return super.getContributedVariables(name0, lookupLocation0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected Set getNonDeclaredClassifierNames() {
            Iterable iterable0 = this.getClassDescriptor().typeConstructor.getSupertypes();
            Collection collection0 = new LinkedHashSet();
            for(Object object0: iterable0) {
                Iterable iterable1 = ((KotlinType)object0).getMemberScope().getClassifierNames();
                if(iterable1 == null) {
                    return null;
                }
                CollectionsKt.addAll(collection0, iterable1);
            }
            return (Set)collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected Set getNonDeclaredFunctionNames() {
            Iterable iterable0 = this.getClassDescriptor().typeConstructor.getSupertypes();
            Collection collection0 = new LinkedHashSet();
            for(Object object0: iterable0) {
                CollectionsKt.addAll(collection0, ((KotlinType)object0).getMemberScope().getFunctionNames());
            }
            ((LinkedHashSet)collection0).addAll(this.getC().getComponents().getAdditionalClassPartsProvider().getFunctionsNames(DeserializedClassDescriptor.this));
            return (Set)collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected Set getNonDeclaredVariableNames() {
            Iterable iterable0 = this.getClassDescriptor().typeConstructor.getSupertypes();
            Collection collection0 = new LinkedHashSet();
            for(Object object0: iterable0) {
                CollectionsKt.addAll(collection0, ((KotlinType)object0).getMemberScope().getVariableNames());
            }
            return (Set)collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope
        protected boolean isDeclaredFunctionAvailable(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
            Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "function");
            return this.getC().getComponents().getPlatformDependentDeclarationFilter().isFunctionAvailable(DeserializedClassDescriptor.this, simpleFunctionDescriptor0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
        public void recordLookup(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            UtilsKt.record(this.getC().getComponents().getLookupTracker(), lookupLocation0, this.getClassDescriptor(), name0);
        }
    }

    final class DeserializedClassTypeConstructor extends AbstractClassTypeConstructor {
        private final NotNullLazyValue parameters;

        public DeserializedClassTypeConstructor() {
            super(deserializedClassDescriptor0.getC().getStorageManager());
            this.parameters = deserializedClassDescriptor0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return TypeParameterUtilsKt.computeConstructorTypeParameters(DeserializedClassDescriptor.this);
                }
            });
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected Collection computeSupertypes() {
            String s1;
            Iterable iterable0 = ProtoTypeTableUtilKt.supertypes(DeserializedClassDescriptor.this.getClassProto(), DeserializedClassDescriptor.this.getC().getTypeTable());
            DeserializedClassDescriptor deserializedClassDescriptor0 = DeserializedClassDescriptor.this;
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
            for(Object object0: iterable0) {
                arrayList0.add(deserializedClassDescriptor0.getC().getTypeDeserializer().type(((Type)object0)));
            }
            Iterable iterable1 = CollectionsKt.plus(arrayList0, DeserializedClassDescriptor.this.getC().getComponents().getAdditionalClassPartsProvider().getSupertypes(DeserializedClassDescriptor.this));
            Collection collection0 = new ArrayList();
            for(Object object1: iterable1) {
                ClassifierDescriptor classifierDescriptor0 = ((KotlinType)object1).getConstructor().getDeclarationDescriptor();
                MockClassDescriptor notFoundClasses$MockClassDescriptor0 = classifierDescriptor0 instanceof MockClassDescriptor ? ((MockClassDescriptor)classifierDescriptor0) : null;
                if(notFoundClasses$MockClassDescriptor0 != null) {
                    collection0.add(notFoundClasses$MockClassDescriptor0);
                }
            }
            if(!((List)collection0).isEmpty()) {
                ErrorReporter errorReporter0 = DeserializedClassDescriptor.this.getC().getComponents().getErrorReporter();
                ClassDescriptor classDescriptor0 = DeserializedClassDescriptor.this;
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
                for(Object object2: ((List)collection0)) {
                    MockClassDescriptor notFoundClasses$MockClassDescriptor1 = (MockClassDescriptor)object2;
                    ClassId classId0 = DescriptorUtilsKt.getClassId(notFoundClasses$MockClassDescriptor1);
                    if(classId0 == null) {
                        s1 = notFoundClasses$MockClassDescriptor1.getName().asString();
                    }
                    else {
                        FqName fqName0 = classId0.asSingleFqName();
                        if(fqName0 != null) {
                            String s = fqName0.asString();
                            if(s != null) {
                                s1 = s;
                            }
                        }
                    }
                    arrayList1.add(s1);
                }
                errorReporter0.reportIncompleteHierarchy(classDescriptor0, arrayList1);
            }
            return CollectionsKt.toList(iterable1);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor
        public ClassDescriptor getDeclarationDescriptor() {
            return this.getDeclarationDescriptor();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor
        public ClassifierDescriptor getDeclarationDescriptor() {
            return this.getDeclarationDescriptor();
        }

        public DeserializedClassDescriptor getDeclarationDescriptor() {
            return DeserializedClassDescriptor.this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List getParameters() {
            return (List)this.parameters.invoke();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return EMPTY.INSTANCE;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @Override
        public String toString() {
            String s = DeserializedClassDescriptor.this.getName().toString();
            Intrinsics.checkNotNullExpressionValue(s, "name.toString()");
            return s;
        }
    }

    final class EnumEntryClassDescriptors {
        private final MemoizedFunctionToNullable enumEntryByName;
        private final Map enumEntryProtos;
        private final NotNullLazyValue enumMemberNames;

        public EnumEntryClassDescriptors() {
            List list0 = deserializedClassDescriptor0.getClassProto().getEnumEntryList();
            Intrinsics.checkNotNullExpressionValue(list0, "classProto.enumEntryList");
            Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list0, 10)), 16));
            for(Object object0: list0) {
                map0.put(NameResolverUtilKt.getName(deserializedClassDescriptor0.getC().getNameResolver(), ((EnumEntry)object0).getName()), object0);
            }
            this.enumEntryProtos = map0;
            this.enumEntryByName = DeserializedClassDescriptor.this.getC().getStorageManager().createMemoizedFunctionWithNullableValues(new Function1(DeserializedClassDescriptor.this) {
                {
                    EnumEntryClassDescriptors.this = deserializedClassDescriptor$EnumEntryClassDescriptors0;
                    DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Name)object0));
                }

                public final ClassDescriptor invoke(Name name0) {
                    Intrinsics.checkNotNullParameter(name0, "name");
                    EnumEntry protoBuf$EnumEntry0 = (EnumEntry)EnumEntryClassDescriptors.access$getEnumEntryProtos$p(EnumEntryClassDescriptors.this).get(name0);
                    if(protoBuf$EnumEntry0 != null) {
                        StorageManager storageManager0 = DeserializedClassDescriptor.this.getC().getStorageManager();
                        DeserializedAnnotations deserializedAnnotations0 = new DeserializedAnnotations(DeserializedClassDescriptor.this.getC().getStorageManager(), new Function0(protoBuf$EnumEntry0) {
                            final EnumEntry $proto;

                            {
                                DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                                this.$proto = protoBuf$EnumEntry0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                return this.invoke();
                            }

                            public final List invoke() {
                                return CollectionsKt.toList(DeserializedClassDescriptor.this.getC().getComponents().getAnnotationAndConstantLoader().loadEnumEntryAnnotations(DeserializedClassDescriptor.this.getThisAsProtoContainer$deserialization(), this.$proto));
                            }
                        });
                        return EnumEntrySyntheticClassDescriptor.create(storageManager0, DeserializedClassDescriptor.this, name0, EnumEntryClassDescriptors.access$getEnumMemberNames$p(EnumEntryClassDescriptors.this), deserializedAnnotations0, SourceElement.NO_SOURCE);
                    }
                    return null;
                }
            });
            this.enumMemberNames = DeserializedClassDescriptor.this.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    EnumEntryClassDescriptors.this = deserializedClassDescriptor$EnumEntryClassDescriptors0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Set invoke() {
                    return EnumEntryClassDescriptors.this.computeEnumMemberNames();
                }
            });
        }

        public static final Map access$getEnumEntryProtos$p(EnumEntryClassDescriptors deserializedClassDescriptor$EnumEntryClassDescriptors0) {
            return deserializedClassDescriptor$EnumEntryClassDescriptors0.enumEntryProtos;
        }

        public static final NotNullLazyValue access$getEnumMemberNames$p(EnumEntryClassDescriptors deserializedClassDescriptor$EnumEntryClassDescriptors0) {
            return deserializedClassDescriptor$EnumEntryClassDescriptors0.enumMemberNames;
        }

        public final Collection all() {
            Iterable iterable0 = this.enumEntryProtos.keySet();
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                ClassDescriptor classDescriptor0 = this.findEnumEntry(((Name)object0));
                if(classDescriptor0 != null) {
                    collection0.add(classDescriptor0);
                }
            }
            return (List)collection0;
        }

        private final Set computeEnumMemberNames() {
            HashSet hashSet0 = new HashSet();
            for(Object object0: DeserializedClassDescriptor.this.getTypeConstructor().getSupertypes()) {
                for(Object object1: DefaultImpls.getContributedDescriptors$default(((KotlinType)object0).getMemberScope(), null, null, 3, null)) {
                    DeclarationDescriptor declarationDescriptor0 = (DeclarationDescriptor)object1;
                    if(declarationDescriptor0 instanceof SimpleFunctionDescriptor || declarationDescriptor0 instanceof PropertyDescriptor) {
                        hashSet0.add(declarationDescriptor0.getName());
                    }
                }
            }
            List list0 = DeserializedClassDescriptor.this.getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(list0, "classProto.functionList");
            DeserializedClassDescriptor deserializedClassDescriptor0 = DeserializedClassDescriptor.this;
            for(Object object2: list0) {
                hashSet0.add(NameResolverUtilKt.getName(deserializedClassDescriptor0.getC().getNameResolver(), ((Function)object2).getName()));
            }
            List list1 = DeserializedClassDescriptor.this.getClassProto().getPropertyList();
            Intrinsics.checkNotNullExpressionValue(list1, "classProto.propertyList");
            DeserializedClassDescriptor deserializedClassDescriptor1 = DeserializedClassDescriptor.this;
            for(Object object3: list1) {
                hashSet0.add(NameResolverUtilKt.getName(deserializedClassDescriptor1.getC().getNameResolver(), ((Property)object3).getName()));
            }
            return SetsKt.plus(hashSet0, hashSet0);
        }

        public final ClassDescriptor findEnumEntry(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            return (ClassDescriptor)this.enumEntryByName.invoke(name0);
        }
    }

    private final Annotations annotations;
    private final DeserializationContext c;
    private final ClassId classId;
    private final Class classProto;
    private final NullableLazyValue companionObjectDescriptor;
    private final NotNullLazyValue constructors;
    private final DeclarationDescriptor containingDeclaration;
    private final EnumEntryClassDescriptors enumEntries;
    private final ClassKind kind;
    private final ScopesHolderForClass memberScopeHolder;
    private final BinaryVersion metadataVersion;
    private final Modality modality;
    private final NullableLazyValue primaryConstructor;
    private final NotNullLazyValue sealedSubclasses;
    private final SourceElement sourceElement;
    private final MemberScopeImpl staticScope;
    private final kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class thisAsProtoContainer;
    private final DeserializedClassTypeConstructor typeConstructor;
    private final NullableLazyValue valueClassRepresentation;
    private final DescriptorVisibility visibility;

    public DeserializedClassDescriptor(DeserializationContext deserializationContext0, Class protoBuf$Class0, NameResolver nameResolver0, BinaryVersion binaryVersion0, SourceElement sourceElement0) {
        Intrinsics.checkNotNullParameter(deserializationContext0, "outerContext");
        Intrinsics.checkNotNullParameter(protoBuf$Class0, "classProto");
        Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
        Intrinsics.checkNotNullParameter(binaryVersion0, "metadataVersion");
        Intrinsics.checkNotNullParameter(sourceElement0, "sourceElement");
        super(deserializationContext0.getStorageManager(), NameResolverUtilKt.getClassId(nameResolver0, protoBuf$Class0.getFqName()).getShortClassName());
        this.classProto = protoBuf$Class0;
        this.metadataVersion = binaryVersion0;
        this.sourceElement = sourceElement0;
        this.classId = NameResolverUtilKt.getClassId(nameResolver0, protoBuf$Class0.getFqName());
        kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality protoBuf$Modality0 = (kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Modality)Flags.MODALITY.get(protoBuf$Class0.getFlags());
        this.modality = ProtoEnumFlags.INSTANCE.modality(protoBuf$Modality0);
        Visibility protoBuf$Visibility0 = (Visibility)Flags.VISIBILITY.get(protoBuf$Class0.getFlags());
        this.visibility = ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, protoBuf$Visibility0);
        Kind protoBuf$Class$Kind0 = (Kind)Flags.CLASS_KIND.get(protoBuf$Class0.getFlags());
        ClassKind classKind0 = ProtoEnumFlags.INSTANCE.classKind(protoBuf$Class$Kind0);
        this.kind = classKind0;
        List list0 = protoBuf$Class0.getTypeParameterList();
        Intrinsics.checkNotNullExpressionValue(list0, "classProto.typeParameterList");
        TypeTable protoBuf$TypeTable0 = protoBuf$Class0.getTypeTable();
        Intrinsics.checkNotNullExpressionValue(protoBuf$TypeTable0, "classProto.typeTable");
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable0 = new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(protoBuf$TypeTable0);
        VersionRequirementTable protoBuf$VersionRequirementTable0 = protoBuf$Class0.getVersionRequirementTable();
        Intrinsics.checkNotNullExpressionValue(protoBuf$VersionRequirementTable0, "classProto.versionRequirementTable");
        DeserializationContext deserializationContext1 = deserializationContext0.childContext(this, list0, nameResolver0, typeTable0, kotlin.reflect.jvm.internal.impl.metadata.deserialization.VersionRequirementTable.Companion.create(protoBuf$VersionRequirementTable0), binaryVersion0);
        this.c = deserializationContext1;
        MemberScopeImpl memberScopeImpl0 = classKind0 == ClassKind.ENUM_CLASS ? new StaticScopeForKotlinEnum(deserializationContext1.getStorageManager(), this) : Empty.INSTANCE;
        this.staticScope = memberScopeImpl0;
        this.typeConstructor = new DeserializedClassTypeConstructor(this);
        StorageManager storageManager0 = deserializationContext1.getStorageManager();
        KotlinTypeRefiner kotlinTypeRefiner0 = deserializationContext1.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner();
        Function1 function10 = new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "<init>";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(DeserializedClassMemberScope.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "<init>(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedClassDescriptor;Lorg/jetbrains/kotlin/types/checker/KotlinTypeRefiner;)V";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeRefiner)object0));
            }

            public final DeserializedClassMemberScope invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "p0");
                return new DeserializedClassMemberScope(((DeserializedClassDescriptor)this.receiver), kotlinTypeRefiner0);
            }
        };
        this.memberScopeHolder = ScopesHolderForClass.Companion.create(this, storageManager0, kotlinTypeRefiner0, function10);
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class protoContainer$Class0 = null;
        this.enumEntries = classKind0 == ClassKind.ENUM_CLASS ? new EnumEntryClassDescriptors(this) : null;
        DeclarationDescriptor declarationDescriptor0 = deserializationContext0.getContainingDeclaration();
        this.containingDeclaration = declarationDescriptor0;
        this.primaryConstructor = deserializationContext1.getStorageManager().createNullableLazyValue(new Function0() {
            {
                DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final ClassConstructorDescriptor invoke() {
                return DeserializedClassDescriptor.access$computePrimaryConstructor(DeserializedClassDescriptor.this);
            }
        });
        this.constructors = deserializationContext1.getStorageManager().createLazyValue(new Function0() {
            {
                DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Collection invoke() {
                return DeserializedClassDescriptor.access$computeConstructors(DeserializedClassDescriptor.this);
            }
        });
        this.companionObjectDescriptor = deserializationContext1.getStorageManager().createNullableLazyValue(new Function0() {
            {
                DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final ClassDescriptor invoke() {
                return DeserializedClassDescriptor.access$computeCompanionObjectDescriptor(DeserializedClassDescriptor.this);
            }
        });
        this.sealedSubclasses = deserializationContext1.getStorageManager().createLazyValue(new Function0() {
            {
                DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Collection invoke() {
                return DeserializedClassDescriptor.access$computeSubclassesForSealedClass(DeserializedClassDescriptor.this);
            }
        });
        this.valueClassRepresentation = deserializationContext1.getStorageManager().createNullableLazyValue(new Function0() {
            {
                DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final ValueClassRepresentation invoke() {
                return DeserializedClassDescriptor.access$computeValueClassRepresentation(DeserializedClassDescriptor.this);
            }
        });
        NameResolver nameResolver1 = deserializationContext1.getNameResolver();
        kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable typeTable1 = deserializationContext1.getTypeTable();
        DeserializedClassDescriptor deserializedClassDescriptor0 = declarationDescriptor0 instanceof DeserializedClassDescriptor ? ((DeserializedClassDescriptor)declarationDescriptor0) : null;
        if(deserializedClassDescriptor0 != null) {
            protoContainer$Class0 = deserializedClassDescriptor0.thisAsProtoContainer;
        }
        this.thisAsProtoContainer = new kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class(protoBuf$Class0, nameResolver1, typeTable1, sourceElement0, protoContainer$Class0);
        Annotations annotations0 = Flags.HAS_ANNOTATIONS.get(protoBuf$Class0.getFlags()).booleanValue() ? new NonEmptyDeserializedAnnotations(deserializationContext1.getStorageManager(), new Function0() {
            {
                DeserializedClassDescriptor.this = deserializedClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return CollectionsKt.toList(DeserializedClassDescriptor.this.getC().getComponents().getAnnotationAndConstantLoader().loadClassAnnotations(DeserializedClassDescriptor.this.getThisAsProtoContainer$deserialization()));
            }
        }) : Annotations.Companion.getEMPTY();
        this.annotations = annotations0;
    }

    public static final ClassDescriptor access$computeCompanionObjectDescriptor(DeserializedClassDescriptor deserializedClassDescriptor0) {
        return deserializedClassDescriptor0.computeCompanionObjectDescriptor();
    }

    public static final Collection access$computeConstructors(DeserializedClassDescriptor deserializedClassDescriptor0) {
        return deserializedClassDescriptor0.computeConstructors();
    }

    public static final ClassConstructorDescriptor access$computePrimaryConstructor(DeserializedClassDescriptor deserializedClassDescriptor0) {
        return deserializedClassDescriptor0.computePrimaryConstructor();
    }

    public static final Collection access$computeSubclassesForSealedClass(DeserializedClassDescriptor deserializedClassDescriptor0) {
        return deserializedClassDescriptor0.computeSubclassesForSealedClass();
    }

    public static final ValueClassRepresentation access$computeValueClassRepresentation(DeserializedClassDescriptor deserializedClassDescriptor0) {
        return deserializedClassDescriptor0.computeValueClassRepresentation();
    }

    private final ClassDescriptor computeCompanionObjectDescriptor() {
        if(!this.classProto.hasCompanionObjectName()) {
            return null;
        }
        Name name0 = NameResolverUtilKt.getName(this.c.getNameResolver(), this.classProto.getCompanionObjectName());
        ClassifierDescriptor classifierDescriptor0 = this.getMemberScope().getContributedClassifier(name0, NoLookupLocation.FROM_DESERIALIZATION);
        return classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
    }

    private final Collection computeConstructors() {
        return CollectionsKt.plus(CollectionsKt.plus(this.computeSecondaryConstructors(), CollectionsKt.listOfNotNull(this.getUnsubstitutedPrimaryConstructor())), this.c.getComponents().getAdditionalClassPartsProvider().getConstructors(this));
    }

    private final ClassConstructorDescriptor computePrimaryConstructor() {
        if(this.kind.isSingleton()) {
            ClassConstructorDescriptorImpl classConstructorDescriptorImpl0 = DescriptorFactory.createPrimaryConstructorForObject(this, SourceElement.NO_SOURCE);
            classConstructorDescriptorImpl0.setReturnType(this.getDefaultType());
            return classConstructorDescriptorImpl0;
        }
        List list0 = this.classProto.getConstructorList();
        Intrinsics.checkNotNullExpressionValue(list0, "classProto.constructorList");
        for(Object object0: list0) {
            if(!Flags.IS_SECONDARY.get(((Constructor)object0).getFlags()).booleanValue()) {
                return ((Constructor)object0) == null ? null : this.c.getMemberDeserializer().loadConstructor(((Constructor)object0), true);
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    private final List computeSecondaryConstructors() {
        List list0 = this.classProto.getConstructorList();
        Intrinsics.checkNotNullExpressionValue(list0, "classProto.constructorList");
        Collection collection0 = new ArrayList();
        for(Object object0: list0) {
            Boolean boolean0 = Flags.IS_SECONDARY.get(((Constructor)object0).getFlags());
            Intrinsics.checkNotNullExpressionValue(boolean0, "IS_SECONDARY.get(it.flags)");
            if(boolean0.booleanValue()) {
                collection0.add(object0);
            }
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
        for(Object object1: ((List)collection0)) {
            Intrinsics.checkNotNullExpressionValue(((Constructor)object1), "it");
            arrayList0.add(this.c.getMemberDeserializer().loadConstructor(((Constructor)object1), false));
        }
        return arrayList0;
    }

    private final Collection computeSubclassesForSealedClass() {
        if(this.modality != Modality.SEALED) {
            return CollectionsKt.emptyList();
        }
        List list0 = this.classProto.getSealedSubclassFqNameList();
        Intrinsics.checkNotNullExpressionValue(list0, "fqNames");
        if(!list0.isEmpty()) {
            Collection collection0 = new ArrayList();
            for(Object object0: list0) {
                Intrinsics.checkNotNullExpressionValue(((Integer)object0), "index");
                ClassId classId0 = NameResolverUtilKt.getClassId(this.c.getNameResolver(), ((int)(((Integer)object0))));
                ClassDescriptor classDescriptor0 = this.c.getComponents().deserializeClass(classId0);
                if(classDescriptor0 != null) {
                    collection0.add(classDescriptor0);
                }
            }
            return (List)collection0;
        }
        return CliSealedClassInheritorsProvider.INSTANCE.computeSealedSubclasses(this, false);
    }

    private final ValueClassRepresentation computeValueClassRepresentation() {
        if(!this.isInline() && !this.isValue()) {
            return null;
        }
        kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor.computeValueClassRepresentation.1 deserializedClassDescriptor$computeValueClassRepresentation$10 = new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "simpleType";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(Kotlin.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "computeValueClassRepresentation$simpleType(Lorg/jetbrains/kotlin/serialization/deserialization/TypeDeserializer;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Type;)Lorg/jetbrains/kotlin/types/SimpleType;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Type)object0));
            }

            public final SimpleType invoke(Type protoBuf$Type0) {
                Intrinsics.checkNotNullParameter(protoBuf$Type0, "p0");
                return TypeDeserializer.simpleType$default(((TypeDeserializer)this.receiver), protoBuf$Type0, false, 2, null);
            }
        };
        Function1 function10 = new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "getValueClassPropertyType";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(DeserializedClassDescriptor.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "getValueClassPropertyType(Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/types/SimpleType;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final SimpleType invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "p0");
                return ((DeserializedClassDescriptor)this.receiver).getValueClassPropertyType(name0);
            }
        };
        ValueClassRepresentation valueClassRepresentation0 = ValueClassUtilKt.loadValueClassRepresentation(this.classProto, this.c.getNameResolver(), this.c.getTypeTable(), deserializedClassDescriptor$computeValueClassRepresentation$10, function10);
        if(valueClassRepresentation0 != null) {
            return valueClassRepresentation0;
        }
        if(!this.metadataVersion.isAtLeast(1, 5, 1)) {
            ClassConstructorDescriptor classConstructorDescriptor0 = this.getUnsubstitutedPrimaryConstructor();
            if(classConstructorDescriptor0 == null) {
                throw new IllegalStateException(("Inline class has no primary constructor: " + this).toString());
            }
            List list0 = classConstructorDescriptor0.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "constructor.valueParameters");
            Name name0 = ((ValueParameterDescriptor)CollectionsKt.first(list0)).getName();
            Intrinsics.checkNotNullExpressionValue(name0, "constructor.valueParameters.first().name");
            SimpleType simpleType0 = this.getValueClassPropertyType(name0);
            if(simpleType0 == null) {
                throw new IllegalStateException(("Value class has no underlying property: " + this).toString());
            }
            return new InlineClassRepresentation(name0, simpleType0);
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.annotations;
    }

    public final DeserializationContext getC() {
        return this.c;
    }

    public final Class getClassProto() {
        return this.classProto;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getCompanionObjectDescriptor() {
        return (ClassDescriptor)this.companionObjectDescriptor.invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        return (Collection)this.constructors.invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return this.containingDeclaration;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor
    public List getContextReceivers() {
        Iterable iterable0 = ProtoTypeTableUtilKt.contextReceiverTypes(this.classProto, this.c.getTypeTable());
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            KotlinType kotlinType0 = this.c.getTypeDeserializer().type(((Type)object0));
            arrayList0.add(new ReceiverParameterDescriptorImpl(this.getThisAsReceiverParameter(), new ContextClassReceiver(this, kotlinType0, null, null), Annotations.Companion.getEMPTY()));
        }
        return arrayList0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getDeclaredTypeParameters() {
        return this.c.getTypeDeserializer().getOwnTypeParameters();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        return this.kind;
    }

    private final DeserializedClassMemberScope getMemberScope() {
        KotlinTypeRefiner kotlinTypeRefiner0 = this.c.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner();
        return (DeserializedClassMemberScope)this.memberScopeHolder.getScope(kotlinTypeRefiner0);
    }

    public final BinaryVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Modality getModality() {
        return this.modality;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        return (Collection)this.sealedSubclasses.invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource
    public SourceElement getSource() {
        return this.sourceElement;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        return this.getStaticScope();
    }

    public MemberScopeImpl getStaticScope() {
        return this.staticScope;
    }

    public final kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer.Class getThisAsProtoContainer$deserialization() {
        return this.thisAsProtoContainer;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    protected MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this.memberScopeHolder.getScope(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return (ClassConstructorDescriptor)this.primaryConstructor.invoke();
    }

    private final SimpleType getValueClassPropertyType(Name name0) {
        KotlinType kotlinType0 = null;
        boolean z = false;
        Object object0 = null;
        for(Object object1: this.getMemberScope().getContributedVariables(name0, NoLookupLocation.FROM_DESERIALIZATION)) {
            if(((PropertyDescriptor)object1).getExtensionReceiverParameter() != null) {
                continue;
            }
            if(!z) {
                z = true;
                object0 = object1;
                continue;
            }
            object0 = null;
            goto label_15;
        }
        if(!z) {
            object0 = null;
        }
    label_15:
        if(((PropertyDescriptor)object0) != null) {
            kotlinType0 = ((PropertyDescriptor)object0).getType();
        }
        return (SimpleType)kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ValueClassRepresentation getValueClassRepresentation() {
        return (ValueClassRepresentation)this.valueClassRepresentation.invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DescriptorVisibility getVisibility() {
        return this.visibility;
    }

    public final boolean hasNestedClass$deserialization(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return this.getMemberScope().getClassNames$deserialization().contains(name0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return Flags.CLASS_KIND.get(this.classProto.getFlags()) == Kind.COMPANION_OBJECT;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        Boolean boolean0 = Flags.IS_DATA.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_DATA.get(classProto.flags)");
        return boolean0.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        Boolean boolean0 = Flags.IS_EXPECT_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_EXPECT_CLASS.get(classProto.flags)");
        return boolean0.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        Boolean boolean0 = Flags.IS_EXTERNAL_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_EXTERNAL_CLASS.get(classProto.flags)");
        return boolean0.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        Boolean boolean0 = Flags.IS_FUN_INTERFACE.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_FUN_INTERFACE.get(classProto.flags)");
        return boolean0.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        Boolean boolean0 = Flags.IS_VALUE_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_VALUE_CLASS.get(classProto.flags)");
        return boolean0.booleanValue() && this.metadataVersion.isAtMost(1, 4, 1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        Boolean boolean0 = Flags.IS_INNER.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_INNER.get(classProto.flags)");
        return boolean0.booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        Boolean boolean0 = Flags.IS_VALUE_CLASS.get(this.classProto.getFlags());
        Intrinsics.checkNotNullExpressionValue(boolean0, "IS_VALUE_CLASS.get(classProto.flags)");
        return boolean0.booleanValue() && this.metadataVersion.isAtLeast(1, 4, 2);
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return "deserialized " + (this.isExpect() ? "expect " : "") + "class " + this.getName();
    }
}

