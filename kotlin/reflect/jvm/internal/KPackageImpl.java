package kotlin.reflect.jvm.internal;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Package;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmNameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001)B\u0011\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0019\u001A\u00020\u001A2\b\u0010\u001B\u001A\u0004\u0018\u00010\u001CH\u0096\u0002J\u0016\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u001E0\u00062\u0006\u0010\u001F\u001A\u00020 H\u0016J\u0012\u0010!\u001A\u0004\u0018\u00010\"2\u0006\u0010#\u001A\u00020$H\u0016J\u0016\u0010%\u001A\b\u0012\u0004\u0012\u00020\"0\u00062\u0006\u0010\u001F\u001A\u00020 H\u0016J\b\u0010&\u001A\u00020$H\u0016J\b\u0010\'\u001A\u00020(H\u0016R\u001A\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\tR$\u0010\n\u001A\u0018\u0012\u0014\u0012\u0012 \r*\b\u0018\u00010\fR\u00020\u00000\fR\u00020\u00000\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u001E\u0010\u0010\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0012\u0010\tR\u0018\u0010\u0013\u001A\u0006\u0012\u0002\b\u00030\u00038TX\u0094\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u000FR\u0014\u0010\u0015\u001A\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018¨\u0006*"}, d2 = {"Lkotlin/reflect/jvm/internal/KPackageImpl;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "constructorDescriptors", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "()Ljava/util/Collection;", "data", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazyVal;", "Lkotlin/reflect/jvm/internal/KPackageImpl$Data;", "kotlin.jvm.PlatformType", "getJClass", "()Ljava/lang/Class;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "methodOwner", "getMethodOwner", "scope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "getScope", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "equals", "", "other", "", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getLocalProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "index", "", "getProperties", "hashCode", "toString", "", "Data", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KPackageImpl extends KDeclarationContainerImpl {
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003R\u001D\u0010\u0004\u001A\u0004\u0018\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001A\u0004\b\u0006\u0010\u0007R%\u0010\n\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000B8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000F\u0010\t\u001A\u0004\b\r\u0010\u000ER/\u0010\u0010\u001A\u0016\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001A\u0004\b\u0015\u0010\u0016R!\u0010\u0019\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u001A8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001D\u0010\u0018\u001A\u0004\b\u001B\u0010\u001CR\u001B\u0010\u001E\u001A\u00020\u001F8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\t\u001A\u0004\b \u0010!¨\u0006#"}, d2 = {"Lkotlin/reflect/jvm/internal/KPackageImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "(Lkotlin/reflect/jvm/internal/KPackageImpl;)V", "kotlinClass", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/ReflectKotlinClass;", "getKotlinClass", "()Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClass;", "kotlinClass$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "members", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "getMembers", "()Ljava/util/Collection;", "members$delegate", "metadata", "Lkotlin/Triple;", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmNameResolver;", "Lkotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Package;", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/JvmMetadataVersion;", "getMetadata", "()Lkotlin/Triple;", "metadata$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazyVal;", "multifileFacade", "Ljava/lang/Class;", "getMultifileFacade", "()Ljava/lang/Class;", "multifileFacade$delegate", "scope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "getScope", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "scope$delegate", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class Data extends kotlin.reflect.jvm.internal.KDeclarationContainerImpl.Data {
        static final KProperty[] $$delegatedProperties;
        private final LazySoftVal kotlinClass$delegate;
        private final LazySoftVal members$delegate;
        private final LazyVal metadata$delegate;
        private final LazyVal multifileFacade$delegate;
        private final LazySoftVal scope$delegate;

        static {
            Data.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "kotlinClass", "getKotlinClass()Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClass;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "scope", "getScope()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "multifileFacade", "getMultifileFacade()Ljava/lang/Class;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "metadata", "getMetadata()Lkotlin/Triple;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "members", "getMembers()Ljava/util/Collection;"))};
        }

        public Data() {
            this.kotlinClass$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KPackageImpl.this = kPackageImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final ReflectKotlinClass invoke() {
                    return ReflectKotlinClass.Factory.create(KPackageImpl.this.getJClass());
                }
            });
            this.scope$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kPackageImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final MemberScope invoke() {
                    ReflectKotlinClass reflectKotlinClass0 = Data.access$getKotlinClass(Data.this);
                    return reflectKotlinClass0 != null ? Data.this.getModuleData().getPackagePartScopeCache().getPackagePartScope(reflectKotlinClass0) : Empty.INSTANCE;
                }
            });
            this.multifileFacade$delegate = ReflectProperties.lazy(new Function0(kPackageImpl0) {
                {
                    Data.this = kPackageImpl$Data0;
                    KPackageImpl.this = kPackageImpl0;
                    super(0);
                }

                public final Class invoke() {
                    String s;
                    ReflectKotlinClass reflectKotlinClass0 = Data.access$getKotlinClass(Data.this);
                    if(reflectKotlinClass0 == null) {
                        s = null;
                    }
                    else {
                        KotlinClassHeader kotlinClassHeader0 = reflectKotlinClass0.getClassHeader();
                        s = kotlinClassHeader0 == null ? null : kotlinClassHeader0.getMultifileClassName();
                    }
                    if(s != null && s.length() > 0) {
                        String s1 = StringsKt.replace$default(s, '/', '.', false, 4, null);
                        return KPackageImpl.this.getJClass().getClassLoader().loadClass(s1);
                    }
                    return null;
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }
            });
            this.metadata$delegate = ReflectProperties.lazy(new Function0() {
                {
                    Data.this = kPackageImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Triple invoke() {
                    ReflectKotlinClass reflectKotlinClass0 = Data.access$getKotlinClass(Data.this);
                    if(reflectKotlinClass0 != null) {
                        KotlinClassHeader kotlinClassHeader0 = reflectKotlinClass0.getClassHeader();
                        if(kotlinClassHeader0 != null) {
                            String[] arr_s = kotlinClassHeader0.getData();
                            String[] arr_s1 = kotlinClassHeader0.getStrings();
                            if(arr_s != null && arr_s1 != null) {
                                Pair pair0 = JvmProtoBufUtil.readPackageDataFrom(arr_s, arr_s1);
                                return new Triple(((JvmNameResolver)pair0.component1()), ((Package)pair0.component2()), kotlinClassHeader0.getMetadataVersion());
                            }
                        }
                    }
                    return null;
                }
            });
            this.members$delegate = ReflectProperties.lazySoft(new Function0(this) {
                {
                    KPackageImpl.this = kPackageImpl0;
                    Data.this = kPackageImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Collection invoke() {
                    MemberScope memberScope0 = Data.this.getScope();
                    return KPackageImpl.this.getMembers(memberScope0, MemberBelonginess.DECLARED);
                }
            });
        }

        public static final ReflectKotlinClass access$getKotlinClass(Data kPackageImpl$Data0) {
            return kPackageImpl$Data0.getKotlinClass();
        }

        private final ReflectKotlinClass getKotlinClass() {
            return (ReflectKotlinClass)this.kotlinClass$delegate.getValue(this, Data.$$delegatedProperties[0]);
        }

        public final Collection getMembers() {
            Object object0 = this.members$delegate.getValue(this, Data.$$delegatedProperties[4]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-members>(...)");
            return (Collection)object0;
        }

        public final Triple getMetadata() {
            return (Triple)this.metadata$delegate.getValue(this, Data.$$delegatedProperties[3]);
        }

        public final Class getMultifileFacade() {
            return (Class)this.multifileFacade$delegate.getValue(this, Data.$$delegatedProperties[2]);
        }

        public final MemberScope getScope() {
            Object object0 = this.scope$delegate.getValue(this, Data.$$delegatedProperties[1]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-scope>(...)");
            return (MemberScope)object0;
        }
    }

    private final LazyVal data;
    private final Class jClass;

    public KPackageImpl(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "jClass");
        super();
        this.jClass = class0;
        LazyVal reflectProperties$LazyVal0 = ReflectProperties.lazy(new Function0() {
            {
                KPackageImpl.this = kPackageImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Data invoke() {
                return new Data(KPackageImpl.this);
            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazyVal0, "lazy { Data() }");
        this.data = reflectProperties$LazyVal0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof KPackageImpl && Intrinsics.areEqual(this.getJClass(), ((KPackageImpl)object0).getJClass());
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getConstructorDescriptors() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getFunctions(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return this.getScope().getContributedFunctions(name0, NoLookupLocation.FROM_REFLECTION);
    }

    @Override  // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class getJClass() {
        return this.jClass;
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalProperty(int v) {
        Triple triple0 = ((Data)this.data.invoke()).getMetadata();
        if(triple0 != null) {
            JvmNameResolver jvmNameResolver0 = (JvmNameResolver)triple0.component1();
            Package protoBuf$Package0 = (Package)triple0.component2();
            JvmMetadataVersion jvmMetadataVersion0 = (JvmMetadataVersion)triple0.component3();
            Intrinsics.checkNotNullExpressionValue(JvmProtoBuf.packageLocalVariable, "packageLocalVariable");
            Property protoBuf$Property0 = (Property)ProtoBufUtilKt.getExtensionOrNull(protoBuf$Package0, JvmProtoBuf.packageLocalVariable, v);
            if(protoBuf$Property0 != null) {
                TypeTable protoBuf$TypeTable0 = protoBuf$Package0.getTypeTable();
                Intrinsics.checkNotNullExpressionValue(protoBuf$TypeTable0, "packageProto.typeTable");
                return (PropertyDescriptor)UtilKt.deserializeToDescriptor(this.getJClass(), protoBuf$Property0, jvmNameResolver0, new kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable(protoBuf$TypeTable0), jvmMetadataVersion0, kotlin.reflect.jvm.internal.KPackageImpl.getLocalProperty.1.1.1.INSTANCE);
            }
        }
        return null;

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.KPackageImpl.getLocalProperty.1.1.1 extends FunctionReference implements Function2 {
            public static final kotlin.reflect.jvm.internal.KPackageImpl.getLocalProperty.1.1.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.KPackageImpl.getLocalProperty.1.1.1.INSTANCE = new kotlin.reflect.jvm.internal.KPackageImpl.getLocalProperty.1.1.1();
            }

            kotlin.reflect.jvm.internal.KPackageImpl.getLocalProperty.1.1.1() {
                super(2);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "loadProperty";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(MemberDeserializer.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "loadProperty(Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;)Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;";
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((MemberDeserializer)object0), ((Property)object1));
            }

            public final PropertyDescriptor invoke(MemberDeserializer memberDeserializer0, Property protoBuf$Property0) {
                Intrinsics.checkNotNullParameter(memberDeserializer0, "p0");
                Intrinsics.checkNotNullParameter(protoBuf$Property0, "p1");
                return memberDeserializer0.loadProperty(protoBuf$Property0);
            }
        }

    }

    @Override  // kotlin.reflect.KDeclarationContainer
    public Collection getMembers() {
        return ((Data)this.data.invoke()).getMembers();
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    protected Class getMethodOwner() {
        Class class0 = ((Data)this.data.invoke()).getMultifileFacade();
        return class0 == null ? this.getJClass() : class0;
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getProperties(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return this.getScope().getContributedVariables(name0, NoLookupLocation.FROM_REFLECTION);
    }

    private final MemberScope getScope() {
        return ((Data)this.data.invoke()).getScope();
    }

    @Override
    public int hashCode() {
        return this.getJClass().hashCode();
    }

    @Override
    public String toString() {
        return "file class " + ReflectClassUtilKt.getClassId(this.getJClass()).asSingleFqName();
    }
}

