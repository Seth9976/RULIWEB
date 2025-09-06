package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Parser;
import kotlin.reflect.jvm.internal.impl.resolve.MemberComparator.NameAndTypeMemberComparator;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializationContext;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.sequences.SequencesKt;

public abstract class DeserializedMemberScope extends MemberScopeImpl {
    interface Implementation {
        void addFunctionsAndPropertiesTo(Collection arg1, DescriptorKindFilter arg2, Function1 arg3, LookupLocation arg4);

        Collection getContributedFunctions(Name arg1, LookupLocation arg2);

        Collection getContributedVariables(Name arg1, LookupLocation arg2);

        Set getFunctionNames();

        TypeAliasDescriptor getTypeAliasByName(Name arg1);

        Set getTypeAliasNames();

        Set getVariableNames();
    }

    final class NoReorderImplementation implements Implementation {
        static final KProperty[] $$delegatedProperties;
        private final NotNullLazyValue allFunctions$delegate;
        private final NotNullLazyValue allProperties$delegate;
        private final NotNullLazyValue allTypeAliases$delegate;
        private final NotNullLazyValue declaredFunctions$delegate;
        private final NotNullLazyValue declaredProperties$delegate;
        private final List functionList;
        private final NotNullLazyValue functionNames$delegate;
        private final NotNullLazyValue functionsByName$delegate;
        private final NotNullLazyValue propertiesByName$delegate;
        private final List propertyList;
        private final List typeAliasList;
        private final NotNullLazyValue typeAliasesByName$delegate;
        private final NotNullLazyValue variableNames$delegate;

        static {
            NoReorderImplementation.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "declaredFunctions", "getDeclaredFunctions()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "declaredProperties", "getDeclaredProperties()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "allTypeAliases", "getAllTypeAliases()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "allFunctions", "getAllFunctions()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "allProperties", "getAllProperties()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "typeAliasesByName", "getTypeAliasesByName()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "functionsByName", "getFunctionsByName()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "propertiesByName", "getPropertiesByName()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "functionNames", "getFunctionNames()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(NoReorderImplementation.class), "variableNames", "getVariableNames()Ljava/util/Set;"))};
        }

        public NoReorderImplementation(List list0, List list1, List list2) {
            Intrinsics.checkNotNullParameter(list0, "functionList");
            Intrinsics.checkNotNullParameter(list1, "propertyList");
            Intrinsics.checkNotNullParameter(list2, "typeAliasList");
            DeserializedMemberScope.this = deserializedMemberScope0;
            super();
            this.functionList = list0;
            this.propertyList = list1;
            if(!deserializedMemberScope0.getC().getComponents().getConfiguration().getTypeAliasesAllowed()) {
                list2 = CollectionsKt.emptyList();
            }
            this.typeAliasList = list2;
            this.declaredFunctions$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return NoReorderImplementation.access$computeFunctions(NoReorderImplementation.this);
                }
            });
            this.declaredProperties$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return NoReorderImplementation.access$computeProperties(NoReorderImplementation.this);
                }
            });
            this.allTypeAliases$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return NoReorderImplementation.access$computeTypeAliases(NoReorderImplementation.this);
                }
            });
            this.allFunctions$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return CollectionsKt.plus(NoReorderImplementation.access$getDeclaredFunctions(NoReorderImplementation.this), NoReorderImplementation.access$computeAllNonDeclaredFunctions(NoReorderImplementation.this));
                }
            });
            this.allProperties$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return CollectionsKt.plus(NoReorderImplementation.access$getDeclaredProperties(NoReorderImplementation.this), NoReorderImplementation.access$computeAllNonDeclaredProperties(NoReorderImplementation.this));
                }
            });
            this.typeAliasesByName$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Map invoke() {
                    Iterable iterable0 = NoReorderImplementation.access$getAllTypeAliases(NoReorderImplementation.this);
                    Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable0, 10)), 16));
                    for(Object object0: iterable0) {
                        Name name0 = ((TypeAliasDescriptor)object0).getName();
                        Intrinsics.checkNotNullExpressionValue(name0, "it.name");
                        map0.put(name0, object0);
                    }
                    return map0;
                }
            });
            this.functionsByName$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Map invoke() {
                    Iterable iterable0 = NoReorderImplementation.access$getAllFunctions(NoReorderImplementation.this);
                    Map map0 = new LinkedHashMap();
                    for(Object object0: iterable0) {
                        Name name0 = ((SimpleFunctionDescriptor)object0).getName();
                        Intrinsics.checkNotNullExpressionValue(name0, "it.name");
                        ArrayList arrayList0 = map0.get(name0);
                        if(arrayList0 == null) {
                            arrayList0 = new ArrayList();
                            map0.put(name0, arrayList0);
                        }
                        arrayList0.add(object0);
                    }
                    return map0;
                }
            });
            this.propertiesByName$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0() {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Map invoke() {
                    Iterable iterable0 = NoReorderImplementation.access$getAllProperties(NoReorderImplementation.this);
                    Map map0 = new LinkedHashMap();
                    for(Object object0: iterable0) {
                        Name name0 = ((PropertyDescriptor)object0).getName();
                        Intrinsics.checkNotNullExpressionValue(name0, "it.name");
                        ArrayList arrayList0 = map0.get(name0);
                        if(arrayList0 == null) {
                            arrayList0 = new ArrayList();
                            map0.put(name0, arrayList0);
                        }
                        arrayList0.add(object0);
                    }
                    return map0;
                }
            });
            this.functionNames$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0(deserializedMemberScope0) {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    DeserializedMemberScope.this = deserializedMemberScope0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Set invoke() {
                    Collection collection0 = new LinkedHashSet();
                    DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
                    for(Object object0: NoReorderImplementation.access$getFunctionList$p(NoReorderImplementation.this)) {
                        collection0.add(NameResolverUtilKt.getName(deserializedMemberScope0.getC().getNameResolver(), ((Function)(((MessageLite)object0))).getName()));
                    }
                    return SetsKt.plus(((Set)collection0), DeserializedMemberScope.this.getNonDeclaredFunctionNames());
                }
            });
            this.variableNames$delegate = deserializedMemberScope0.getC().getStorageManager().createLazyValue(new Function0(deserializedMemberScope0) {
                {
                    NoReorderImplementation.this = deserializedMemberScope$NoReorderImplementation0;
                    DeserializedMemberScope.this = deserializedMemberScope0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Set invoke() {
                    Collection collection0 = new LinkedHashSet();
                    DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
                    for(Object object0: NoReorderImplementation.access$getPropertyList$p(NoReorderImplementation.this)) {
                        collection0.add(NameResolverUtilKt.getName(deserializedMemberScope0.getC().getNameResolver(), ((Property)(((MessageLite)object0))).getName()));
                    }
                    return SetsKt.plus(((Set)collection0), DeserializedMemberScope.this.getNonDeclaredVariableNames());
                }
            });
        }

        public static final List access$computeAllNonDeclaredFunctions(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.computeAllNonDeclaredFunctions();
        }

        public static final List access$computeAllNonDeclaredProperties(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.computeAllNonDeclaredProperties();
        }

        public static final List access$computeFunctions(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.computeFunctions();
        }

        public static final List access$computeProperties(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.computeProperties();
        }

        public static final List access$computeTypeAliases(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.computeTypeAliases();
        }

        public static final List access$getAllFunctions(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.getAllFunctions();
        }

        public static final List access$getAllProperties(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.getAllProperties();
        }

        public static final List access$getAllTypeAliases(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.getAllTypeAliases();
        }

        public static final List access$getDeclaredFunctions(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.getDeclaredFunctions();
        }

        public static final List access$getDeclaredProperties(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.getDeclaredProperties();
        }

        public static final List access$getFunctionList$p(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.functionList;
        }

        public static final List access$getPropertyList$p(NoReorderImplementation deserializedMemberScope$NoReorderImplementation0) {
            return deserializedMemberScope$NoReorderImplementation0.propertyList;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public void addFunctionsAndPropertiesTo(Collection collection0, DescriptorKindFilter descriptorKindFilter0, Function1 function10, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(collection0, "result");
            Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
            Intrinsics.checkNotNullParameter(function10, "nameFilter");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            if(descriptorKindFilter0.acceptsKinds(0x20)) {
                for(Object object0: this.getAllProperties()) {
                    Name name0 = ((PropertyDescriptor)object0).getName();
                    Intrinsics.checkNotNullExpressionValue(name0, "it.name");
                    if(((Boolean)function10.invoke(name0)).booleanValue()) {
                        collection0.add(object0);
                    }
                }
            }
            if(descriptorKindFilter0.acceptsKinds(16)) {
                for(Object object1: this.getAllFunctions()) {
                    Name name1 = ((SimpleFunctionDescriptor)object1).getName();
                    Intrinsics.checkNotNullExpressionValue(name1, "it.name");
                    if(((Boolean)function10.invoke(name1)).booleanValue()) {
                        collection0.add(object1);
                    }
                }
            }
        }

        private final List computeAllNonDeclaredFunctions() {
            Iterable iterable0 = DeserializedMemberScope.this.getNonDeclaredFunctionNames();
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                CollectionsKt.addAll(collection0, this.computeNonDeclaredFunctionsForName(((Name)object0)));
            }
            return (List)collection0;
        }

        private final List computeAllNonDeclaredProperties() {
            Iterable iterable0 = DeserializedMemberScope.this.getNonDeclaredVariableNames();
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                CollectionsKt.addAll(collection0, this.computeNonDeclaredPropertiesForName(((Name)object0)));
            }
            return (List)collection0;
        }

        private final List computeFunctions() {
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            Collection collection0 = new ArrayList();
            for(Object object0: this.functionList) {
                SimpleFunctionDescriptor simpleFunctionDescriptor0 = deserializedMemberScope0.getC().getMemberDeserializer().loadFunction(((Function)(((MessageLite)object0))));
                if(!deserializedMemberScope0.isDeclaredFunctionAvailable(simpleFunctionDescriptor0)) {
                    simpleFunctionDescriptor0 = null;
                }
                if(simpleFunctionDescriptor0 != null) {
                    collection0.add(simpleFunctionDescriptor0);
                }
            }
            return (List)collection0;
        }

        private final List computeNonDeclaredFunctionsForName(Name name0) {
            List list0 = this.getDeclaredFunctions();
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            Collection collection0 = new ArrayList();
            for(Object object0: list0) {
                if(Intrinsics.areEqual(((DeclarationDescriptor)object0).getName(), name0)) {
                    collection0.add(object0);
                }
            }
            deserializedMemberScope0.computeNonDeclaredFunctions(name0, ((List)collection0));
            return ((List)collection0).subList(((List)collection0).size(), ((List)collection0).size());
        }

        private final List computeNonDeclaredPropertiesForName(Name name0) {
            List list0 = this.getDeclaredProperties();
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            Collection collection0 = new ArrayList();
            for(Object object0: list0) {
                if(Intrinsics.areEqual(((DeclarationDescriptor)object0).getName(), name0)) {
                    collection0.add(object0);
                }
            }
            deserializedMemberScope0.computeNonDeclaredProperties(name0, ((List)collection0));
            return ((List)collection0).subList(((List)collection0).size(), ((List)collection0).size());
        }

        private final List computeProperties() {
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            Collection collection0 = new ArrayList();
            for(Object object0: this.propertyList) {
                MemberDescriptor memberDescriptor0 = deserializedMemberScope0.getC().getMemberDeserializer().loadProperty(((Property)(((MessageLite)object0))));
                if(memberDescriptor0 != null) {
                    collection0.add(memberDescriptor0);
                }
            }
            return (List)collection0;
        }

        private final List computeTypeAliases() {
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            Collection collection0 = new ArrayList();
            for(Object object0: this.typeAliasList) {
                MemberDescriptor memberDescriptor0 = deserializedMemberScope0.getC().getMemberDeserializer().loadTypeAlias(((TypeAlias)(((MessageLite)object0))));
                if(memberDescriptor0 != null) {
                    collection0.add(memberDescriptor0);
                }
            }
            return (List)collection0;
        }

        private final List getAllFunctions() {
            return (List)StorageKt.getValue(this.allFunctions$delegate, this, NoReorderImplementation.$$delegatedProperties[3]);
        }

        private final List getAllProperties() {
            return (List)StorageKt.getValue(this.allProperties$delegate, this, NoReorderImplementation.$$delegatedProperties[4]);
        }

        private final List getAllTypeAliases() {
            return (List)StorageKt.getValue(this.allTypeAliases$delegate, this, NoReorderImplementation.$$delegatedProperties[2]);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            if(!this.getFunctionNames().contains(name0)) {
                return CollectionsKt.emptyList();
            }
            Collection collection0 = (Collection)this.getFunctionsByName().get(name0);
            return collection0 == null ? CollectionsKt.emptyList() : collection0;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            if(!this.getVariableNames().contains(name0)) {
                return CollectionsKt.emptyList();
            }
            Collection collection0 = (Collection)this.getPropertiesByName().get(name0);
            return collection0 == null ? CollectionsKt.emptyList() : collection0;
        }

        private final List getDeclaredFunctions() {
            return (List)StorageKt.getValue(this.declaredFunctions$delegate, this, NoReorderImplementation.$$delegatedProperties[0]);
        }

        private final List getDeclaredProperties() {
            return (List)StorageKt.getValue(this.declaredProperties$delegate, this, NoReorderImplementation.$$delegatedProperties[1]);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Set getFunctionNames() {
            return (Set)StorageKt.getValue(this.functionNames$delegate, this, NoReorderImplementation.$$delegatedProperties[8]);
        }

        private final Map getFunctionsByName() {
            return (Map)StorageKt.getValue(this.functionsByName$delegate, this, NoReorderImplementation.$$delegatedProperties[6]);
        }

        private final Map getPropertiesByName() {
            return (Map)StorageKt.getValue(this.propertiesByName$delegate, this, NoReorderImplementation.$$delegatedProperties[7]);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public TypeAliasDescriptor getTypeAliasByName(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            return (TypeAliasDescriptor)this.getTypeAliasesByName().get(name0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Set getTypeAliasNames() {
            Collection collection0 = new LinkedHashSet();
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            for(Object object0: this.typeAliasList) {
                collection0.add(NameResolverUtilKt.getName(deserializedMemberScope0.getC().getNameResolver(), ((TypeAlias)(((MessageLite)object0))).getName()));
            }
            return (Set)collection0;
        }

        private final Map getTypeAliasesByName() {
            return (Map)StorageKt.getValue(this.typeAliasesByName$delegate, this, NoReorderImplementation.$$delegatedProperties[5]);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Set getVariableNames() {
            return (Set)StorageKt.getValue(this.variableNames$delegate, this, NoReorderImplementation.$$delegatedProperties[9]);
        }
    }

    final class OptimizedImplementation implements Implementation {
        static final KProperty[] $$delegatedProperties;
        private final NotNullLazyValue functionNames$delegate;
        private final Map functionProtosBytes;
        private final MemoizedFunctionToNotNull functions;
        private final MemoizedFunctionToNotNull properties;
        private final Map propertyProtosBytes;
        private final MemoizedFunctionToNullable typeAliasByName;
        private final Map typeAliasBytes;
        private final NotNullLazyValue variableNames$delegate;

        static {
            OptimizedImplementation.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OptimizedImplementation.class), "functionNames", "getFunctionNames()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(OptimizedImplementation.class), "variableNames", "getVariableNames()Ljava/util/Set;"))};
        }

        public OptimizedImplementation(List list0, List list1, List list2) {
            Intrinsics.checkNotNullParameter(list0, "functionList");
            Map map3;
            Intrinsics.checkNotNullParameter(list1, "propertyList");
            Intrinsics.checkNotNullParameter(list2, "typeAliasList");
            DeserializedMemberScope.this = deserializedMemberScope0;
            super();
            Map map0 = new LinkedHashMap();
            for(Object object0: list0) {
                Name name0 = NameResolverUtilKt.getName(deserializedMemberScope0.getC().getNameResolver(), ((Function)(((MessageLite)object0))).getName());
                ArrayList arrayList0 = map0.get(name0);
                if(arrayList0 == null) {
                    arrayList0 = new ArrayList();
                    map0.put(name0, arrayList0);
                }
                arrayList0.add(object0);
            }
            this.functionProtosBytes = this.packToByteArray(map0);
            DeserializedMemberScope deserializedMemberScope1 = DeserializedMemberScope.this;
            Map map1 = new LinkedHashMap();
            for(Object object1: list1) {
                Name name1 = NameResolverUtilKt.getName(deserializedMemberScope1.getC().getNameResolver(), ((Property)(((MessageLite)object1))).getName());
                ArrayList arrayList1 = map1.get(name1);
                if(arrayList1 == null) {
                    arrayList1 = new ArrayList();
                    map1.put(name1, arrayList1);
                }
                arrayList1.add(object1);
            }
            this.propertyProtosBytes = this.packToByteArray(map1);
            if(DeserializedMemberScope.this.getC().getComponents().getConfiguration().getTypeAliasesAllowed()) {
                DeserializedMemberScope deserializedMemberScope2 = DeserializedMemberScope.this;
                Map map2 = new LinkedHashMap();
                for(Object object2: list2) {
                    Name name2 = NameResolverUtilKt.getName(deserializedMemberScope2.getC().getNameResolver(), ((TypeAlias)(((MessageLite)object2))).getName());
                    ArrayList arrayList2 = map2.get(name2);
                    if(arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        map2.put(name2, arrayList2);
                    }
                    arrayList2.add(object2);
                }
                map3 = this.packToByteArray(map2);
            }
            else {
                map3 = MapsKt.emptyMap();
            }
            this.typeAliasBytes = map3;
            this.functions = DeserializedMemberScope.this.getC().getStorageManager().createMemoizedFunction(new Function1() {
                {
                    OptimizedImplementation.this = deserializedMemberScope$OptimizedImplementation0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Name)object0));
                }

                public final Collection invoke(Name name0) {
                    Intrinsics.checkNotNullParameter(name0, "it");
                    return OptimizedImplementation.access$computeFunctions(OptimizedImplementation.this, name0);
                }
            });
            this.properties = DeserializedMemberScope.this.getC().getStorageManager().createMemoizedFunction(new Function1() {
                {
                    OptimizedImplementation.this = deserializedMemberScope$OptimizedImplementation0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Name)object0));
                }

                public final Collection invoke(Name name0) {
                    Intrinsics.checkNotNullParameter(name0, "it");
                    return OptimizedImplementation.access$computeProperties(OptimizedImplementation.this, name0);
                }
            });
            this.typeAliasByName = DeserializedMemberScope.this.getC().getStorageManager().createMemoizedFunctionWithNullableValues(new Function1() {
                {
                    OptimizedImplementation.this = deserializedMemberScope$OptimizedImplementation0;
                    super(1);
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((Name)object0));
                }

                public final TypeAliasDescriptor invoke(Name name0) {
                    Intrinsics.checkNotNullParameter(name0, "it");
                    return OptimizedImplementation.access$createTypeAlias(OptimizedImplementation.this, name0);
                }
            });
            this.functionNames$delegate = DeserializedMemberScope.this.getC().getStorageManager().createLazyValue(new Function0(DeserializedMemberScope.this) {
                {
                    OptimizedImplementation.this = deserializedMemberScope$OptimizedImplementation0;
                    DeserializedMemberScope.this = deserializedMemberScope0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Set invoke() {
                    return SetsKt.plus(OptimizedImplementation.access$getFunctionProtosBytes$p(OptimizedImplementation.this).keySet(), DeserializedMemberScope.this.getNonDeclaredFunctionNames());
                }
            });
            this.variableNames$delegate = DeserializedMemberScope.this.getC().getStorageManager().createLazyValue(new Function0(DeserializedMemberScope.this) {
                {
                    OptimizedImplementation.this = deserializedMemberScope$OptimizedImplementation0;
                    DeserializedMemberScope.this = deserializedMemberScope0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Set invoke() {
                    return SetsKt.plus(OptimizedImplementation.access$getPropertyProtosBytes$p(OptimizedImplementation.this).keySet(), DeserializedMemberScope.this.getNonDeclaredVariableNames());
                }
            });
        }

        public static final Collection access$computeFunctions(OptimizedImplementation deserializedMemberScope$OptimizedImplementation0, Name name0) {
            return deserializedMemberScope$OptimizedImplementation0.computeFunctions(name0);
        }

        public static final Collection access$computeProperties(OptimizedImplementation deserializedMemberScope$OptimizedImplementation0, Name name0) {
            return deserializedMemberScope$OptimizedImplementation0.computeProperties(name0);
        }

        public static final TypeAliasDescriptor access$createTypeAlias(OptimizedImplementation deserializedMemberScope$OptimizedImplementation0, Name name0) {
            return deserializedMemberScope$OptimizedImplementation0.createTypeAlias(name0);
        }

        public static final Map access$getFunctionProtosBytes$p(OptimizedImplementation deserializedMemberScope$OptimizedImplementation0) {
            return deserializedMemberScope$OptimizedImplementation0.functionProtosBytes;
        }

        public static final Map access$getPropertyProtosBytes$p(OptimizedImplementation deserializedMemberScope$OptimizedImplementation0) {
            return deserializedMemberScope$OptimizedImplementation0.propertyProtosBytes;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public void addFunctionsAndPropertiesTo(Collection collection0, DescriptorKindFilter descriptorKindFilter0, Function1 function10, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(collection0, "result");
            Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
            Intrinsics.checkNotNullParameter(function10, "nameFilter");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            if(descriptorKindFilter0.acceptsKinds(0x20)) {
                Collection collection1 = this.getVariableNames();
                ArrayList arrayList0 = new ArrayList();
                for(Object object0: collection1) {
                    Name name0 = (Name)object0;
                    if(((Boolean)function10.invoke(name0)).booleanValue()) {
                        arrayList0.addAll(this.getContributedVariables(name0, lookupLocation0));
                    }
                }
                Intrinsics.checkNotNullExpressionValue(NameAndTypeMemberComparator.INSTANCE, "INSTANCE");
                CollectionsKt.sortWith(arrayList0, NameAndTypeMemberComparator.INSTANCE);
                collection0.addAll(arrayList0);
            }
            if(descriptorKindFilter0.acceptsKinds(16)) {
                Collection collection2 = this.getFunctionNames();
                ArrayList arrayList1 = new ArrayList();
                for(Object object1: collection2) {
                    Name name1 = (Name)object1;
                    if(((Boolean)function10.invoke(name1)).booleanValue()) {
                        arrayList1.addAll(this.getContributedFunctions(name1, lookupLocation0));
                    }
                }
                Intrinsics.checkNotNullExpressionValue(NameAndTypeMemberComparator.INSTANCE, "INSTANCE");
                CollectionsKt.sortWith(arrayList1, NameAndTypeMemberComparator.INSTANCE);
                collection0.addAll(arrayList1);
            }
        }

        private final Collection computeFunctions(Name name0) {
            Collection collection0;
            Parser parser0 = Function.PARSER;
            Intrinsics.checkNotNullExpressionValue(parser0, "PARSER");
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            byte[] arr_b = (byte[])this.functionProtosBytes.get(name0);
            if(arr_b == null) {
                collection0 = CollectionsKt.emptyList();
            }
            else {
                List list0 = SequencesKt.toList(SequencesKt.generateSequence(new DeserializedMemberScope.OptimizedImplementation.computeDescriptors.1.1(parser0, new ByteArrayInputStream(arr_b), DeserializedMemberScope.this)));
                collection0 = list0 == null ? CollectionsKt.emptyList() : list0;
            }
            ArrayList arrayList0 = new ArrayList(collection0.size());
            for(Object object0: collection0) {
                Intrinsics.checkNotNullExpressionValue(((Function)object0), "it");
                SimpleFunctionDescriptor simpleFunctionDescriptor0 = deserializedMemberScope0.getC().getMemberDeserializer().loadFunction(((Function)object0));
                if(!deserializedMemberScope0.isDeclaredFunctionAvailable(simpleFunctionDescriptor0)) {
                    simpleFunctionDescriptor0 = null;
                }
                if(simpleFunctionDescriptor0 != null) {
                    arrayList0.add(simpleFunctionDescriptor0);
                }
            }
            deserializedMemberScope0.computeNonDeclaredFunctions(name0, arrayList0);
            return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList0);
        }

        private final Collection computeProperties(Name name0) {
            Collection collection0;
            Parser parser0 = Property.PARSER;
            Intrinsics.checkNotNullExpressionValue(parser0, "PARSER");
            DeserializedMemberScope deserializedMemberScope0 = DeserializedMemberScope.this;
            byte[] arr_b = (byte[])this.propertyProtosBytes.get(name0);
            if(arr_b == null) {
                collection0 = CollectionsKt.emptyList();
            }
            else {
                List list0 = SequencesKt.toList(SequencesKt.generateSequence(new DeserializedMemberScope.OptimizedImplementation.computeDescriptors.1.1(parser0, new ByteArrayInputStream(arr_b), DeserializedMemberScope.this)));
                collection0 = list0 == null ? CollectionsKt.emptyList() : list0;
            }
            ArrayList arrayList0 = new ArrayList(collection0.size());
            for(Object object0: collection0) {
                Intrinsics.checkNotNullExpressionValue(((Property)object0), "it");
                PropertyDescriptor propertyDescriptor0 = deserializedMemberScope0.getC().getMemberDeserializer().loadProperty(((Property)object0));
                if(propertyDescriptor0 != null) {
                    arrayList0.add(propertyDescriptor0);
                }
            }
            deserializedMemberScope0.computeNonDeclaredProperties(name0, arrayList0);
            return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList0);
        }

        private final TypeAliasDescriptor createTypeAlias(Name name0) {
            byte[] arr_b = (byte[])this.typeAliasBytes.get(name0);
            if(arr_b == null) {
                return null;
            }
            TypeAlias protoBuf$TypeAlias0 = TypeAlias.parseDelimitedFrom(new ByteArrayInputStream(arr_b), DeserializedMemberScope.this.getC().getComponents().getExtensionRegistryLite());
            return protoBuf$TypeAlias0 == null ? null : DeserializedMemberScope.this.getC().getMemberDeserializer().loadTypeAlias(protoBuf$TypeAlias0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            return !this.getFunctionNames().contains(name0) ? CollectionsKt.emptyList() : ((Collection)this.functions.invoke(name0));
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            Intrinsics.checkNotNullParameter(lookupLocation0, "location");
            return !this.getVariableNames().contains(name0) ? CollectionsKt.emptyList() : ((Collection)this.properties.invoke(name0));
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Set getFunctionNames() {
            return (Set)StorageKt.getValue(this.functionNames$delegate, this, OptimizedImplementation.$$delegatedProperties[0]);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public TypeAliasDescriptor getTypeAliasByName(Name name0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            return (TypeAliasDescriptor)this.typeAliasByName.invoke(name0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Set getTypeAliasNames() {
            return this.typeAliasBytes.keySet();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope$Implementation
        public Set getVariableNames() {
            return (Set)StorageKt.getValue(this.variableNames$delegate, this, OptimizedImplementation.$$delegatedProperties[1]);
        }

        private final Map packToByteArray(Map map0) {
            LinkedHashMap linkedHashMap0 = new LinkedHashMap(MapsKt.mapCapacity(map0.size()));
            for(Object object0: map0.entrySet()) {
                Object object1 = ((Map.Entry)object0).getKey();
                ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
                Iterable iterable0 = (Iterable)((Map.Entry)object0).getValue();
                Collection collection0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object2: iterable0) {
                    ((AbstractMessageLite)object2).writeDelimitedTo(byteArrayOutputStream0);
                    collection0.add(Unit.INSTANCE);
                }
                List list0 = (List)collection0;
                linkedHashMap0.put(object1, byteArrayOutputStream0.toByteArray());
            }
            return linkedHashMap0;
        }
    }

    static final KProperty[] $$delegatedProperties;
    private final DeserializationContext c;
    private final NotNullLazyValue classNames$delegate;
    private final NullableLazyValue classifierNamesLazy$delegate;
    private final Implementation impl;

    static {
        DeserializedMemberScope.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "classNames", "getClassNames$deserialization()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(DeserializedMemberScope.class), "classifierNamesLazy", "getClassifierNamesLazy()Ljava/util/Set;"))};
    }

    protected DeserializedMemberScope(DeserializationContext deserializationContext0, List list0, List list1, List list2, Function0 function00) {
        Intrinsics.checkNotNullParameter(deserializationContext0, "c");
        Intrinsics.checkNotNullParameter(list0, "functionList");
        Intrinsics.checkNotNullParameter(list1, "propertyList");
        Intrinsics.checkNotNullParameter(list2, "typeAliasList");
        Intrinsics.checkNotNullParameter(function00, "classNames");
        super();
        this.c = deserializationContext0;
        this.impl = this.createImplementation(list0, list1, list2);
        this.classNames$delegate = deserializationContext0.getStorageManager().createLazyValue(new Function0() {
            final Function0 $classNames;

            {
                this.$classNames = function00;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                return CollectionsKt.toSet(((Iterable)this.$classNames.invoke()));
            }
        });
        this.classifierNamesLazy$delegate = deserializationContext0.getStorageManager().createNullableLazyValue(new Function0() {
            {
                DeserializedMemberScope.this = deserializedMemberScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                Set set0 = DeserializedMemberScope.this.getNonDeclaredClassifierNames();
                return set0 == null ? null : SetsKt.plus(SetsKt.plus(DeserializedMemberScope.this.getClassNames$deserialization(), DeserializedMemberScope.this.impl.getTypeAliasNames()), set0);
            }
        });
    }

    protected abstract void addEnumEntryDescriptors(Collection arg1, Function1 arg2);

    protected final Collection computeDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        ArrayList arrayList0 = new ArrayList(0);
        if(descriptorKindFilter0.acceptsKinds(2)) {
            this.addEnumEntryDescriptors(arrayList0, function10);
        }
        this.impl.addFunctionsAndPropertiesTo(arrayList0, descriptorKindFilter0, function10, lookupLocation0);
        if(descriptorKindFilter0.acceptsKinds(7)) {
            for(Object object0: this.getClassNames$deserialization()) {
                Name name0 = (Name)object0;
                if(((Boolean)function10.invoke(name0)).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList0, this.deserializeClass(name0));
                }
            }
        }
        if(descriptorKindFilter0.acceptsKinds(4)) {
            for(Object object1: this.impl.getTypeAliasNames()) {
                Name name1 = (Name)object1;
                if(((Boolean)function10.invoke(name1)).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList0, this.impl.getTypeAliasByName(name1));
                }
            }
        }
        return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList0);
    }

    protected void computeNonDeclaredFunctions(Name name0, List list0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(list0, "functions");
    }

    protected void computeNonDeclaredProperties(Name name0, List list0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(list0, "descriptors");
    }

    protected abstract ClassId createClassId(Name arg1);

    private final Implementation createImplementation(List list0, List list1, List list2) {
        return this.c.getComponents().getConfiguration().getPreserveDeclarationsOrdering() ? new NoReorderImplementation(this, list0, list1, list2) : new OptimizedImplementation(this, list0, list1, list2);
    }

    private final ClassDescriptor deserializeClass(Name name0) {
        ClassId classId0 = this.createClassId(name0);
        return this.c.getComponents().deserializeClass(classId0);
    }

    protected final DeserializationContext getC() {
        return this.c;
    }

    public final Set getClassNames$deserialization() {
        return (Set)StorageKt.getValue(this.classNames$delegate, this, DeserializedMemberScope.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getClassifierNames() {
        return this.getClassifierNamesLazy();
    }

    private final Set getClassifierNamesLazy() {
        return (Set)StorageKt.getValue(this.classifierNamesLazy$delegate, this, DeserializedMemberScope.$$delegatedProperties[1]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        if(this.hasClass(name0)) {
            return this.deserializeClass(name0);
        }
        return this.impl.getTypeAliasNames().contains(name0) ? this.getTypeAliasByName(name0) : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.impl.getContributedFunctions(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.impl.getContributedVariables(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getFunctionNames() {
        return this.impl.getFunctionNames();
    }

    protected abstract Set getNonDeclaredClassifierNames();

    protected abstract Set getNonDeclaredFunctionNames();

    protected abstract Set getNonDeclaredVariableNames();

    private final TypeAliasDescriptor getTypeAliasByName(Name name0) {
        return this.impl.getTypeAliasByName(name0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getVariableNames() {
        return this.impl.getVariableNames();
    }

    protected boolean hasClass(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return this.getClassNames$deserialization().contains(name0);
    }

    protected boolean isDeclaredFunctionAvailable(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "function");
        return true;
    }
}

