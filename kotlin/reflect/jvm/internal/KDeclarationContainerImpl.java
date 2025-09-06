package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\n\b \u0018\u0000 <2\u00020\u0001:\u0003<=>B\u0005\u00A2\u0006\u0002\u0010\u0002J*\u0010\f\u001A\u00020\r2\u0010\u0010\u000E\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0002J\u0014\u0010\u0014\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u00152\u0006\u0010\u0010\u001A\u00020\u0011J\u0014\u0010\u0016\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u00152\u0006\u0010\u0010\u001A\u00020\u0011J \u0010\u0017\u001A\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001A\u00020\u00112\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u001A\u001A\u00020\u0013J\u0016\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u0019\u001A\u00020\u00112\u0006\u0010\u001D\u001A\u00020\u0011J\u0018\u0010\u001E\u001A\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001A\u00020\u00112\u0006\u0010\u0010\u001A\u00020\u0011J\u0016\u0010\u001F\u001A\u00020 2\u0006\u0010\u0019\u001A\u00020\u00112\u0006\u0010\u001D\u001A\u00020\u0011J\u0016\u0010!\u001A\b\u0012\u0004\u0012\u00020\u001C0\u00042\u0006\u0010\u0019\u001A\u00020\"H&J\u0012\u0010#\u001A\u0004\u0018\u00010 2\u0006\u0010$\u001A\u00020%H&J\"\u0010&\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\'0\u00042\u0006\u0010(\u001A\u00020)2\u0006\u0010*\u001A\u00020+H\u0004J\u0016\u0010,\u001A\b\u0012\u0004\u0012\u00020 0\u00042\u0006\u0010\u0019\u001A\u00020\"H&J\u001A\u0010-\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0.2\u0006\u0010\u0010\u001A\u00020\u0011H\u0002J\u0014\u0010/\u001A\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001A\u00020\u0011H\u0002J$\u00100\u001A\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u00101\u001A\u00020%2\u0006\u00102\u001A\u00020%H\u0002JE\u00103\u001A\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0019\u001A\u00020\u00112\u0010\u00104\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t052\n\u00106\u001A\u0006\u0012\u0002\b\u00030\t2\u0006\u00107\u001A\u00020\u0013H\u0002\u00A2\u0006\u0002\u00108J(\u00109\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0015*\u0006\u0012\u0002\b\u00030\t2\u0010\u00104\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0.H\u0002J=\u0010:\u001A\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0019\u001A\u00020\u00112\u0010\u00104\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t052\n\u00106\u001A\u0006\u0012\u0002\b\u00030\tH\u0002\u00A2\u0006\u0002\u0010;R\u0018\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001A\u0006\u0012\u0002\b\u00030\t8TX\u0094\u0004\u00A2\u0006\u0006\u001A\u0004\b\n\u0010\u000B\u00A8\u0006?"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "()V", "constructorDescriptors", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "()Ljava/util/Collection;", "methodOwner", "Ljava/lang/Class;", "getMethodOwner", "()Ljava/lang/Class;", "addParametersAndMasks", "", "result", "", "desc", "", "isConstructor", "", "findConstructorBySignature", "Ljava/lang/reflect/Constructor;", "findDefaultConstructor", "findDefaultMethod", "Ljava/lang/reflect/Method;", "name", "isMember", "findFunctionDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "signature", "findMethodBySignature", "findPropertyDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getLocalProperty", "index", "", "getMembers", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "scope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "belonginess", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "getProperties", "loadParameterTypes", "", "loadReturnType", "parseType", "begin", "end", "lookupMethod", "parameterTypes", "", "returnType", "isStaticDefault", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;Z)Ljava/lang/reflect/Method;", "tryGetConstructor", "tryGetMethod", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/reflect/Method;", "Companion", "Data", "MemberBelonginess", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class KDeclarationContainerImpl implements ClassBasedDeclarationContainer {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001E\u0010\u0003\u001A\u0012\u0012\u0002\b\u0003 \u0005*\b\u0012\u0002\b\u0003\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u00020\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Companion;", "", "()V", "DEFAULT_CONSTRUCTOR_MARKER", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "LOCAL_PROPERTY_SIGNATURE", "Lkotlin/text/Regex;", "getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection", "()Lkotlin/text/Regex;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Regex getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection() {
            return KDeclarationContainerImpl.LOCAL_PROPERTY_SIGNATURE;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b¦\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001B\u0010\u0003\u001A\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;)V", "moduleData", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;", "moduleData$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public abstract class Data {
        static final KProperty[] $$delegatedProperties;
        private final LazySoftVal moduleData$delegate;

        static {
            Data.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "moduleData", "getModuleData()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;"))};
        }

        public Data() {
            this.moduleData$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KDeclarationContainerImpl.this = kDeclarationContainerImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final RuntimeModuleData invoke() {
                    return ModuleByClassLoaderKt.getOrCreateModule(KDeclarationContainerImpl.this.getJClass());
                }
            });
        }

        public final RuntimeModuleData getModuleData() {
            Object object0 = this.moduleData$delegate.getValue(this, Data.$$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-moduleData>(...)");
            return (RuntimeModuleData)object0;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0084\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "", "(Ljava/lang/String;I)V", "accept", "", "member", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "DECLARED", "INHERITED", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum MemberBelonginess {
        DECLARED,
        INHERITED;

        private static final MemberBelonginess[] $values() [...] // Inlined contents

        public final boolean accept(CallableMemberDescriptor callableMemberDescriptor0) {
            Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "member");
            return callableMemberDescriptor0.getKind().isReal() == (this == MemberBelonginess.DECLARED);
        }
    }

    public static final Companion Companion;
    private static final Class DEFAULT_CONSTRUCTOR_MARKER;
    private static final Regex LOCAL_PROPERTY_SIGNATURE;

    static {
        KDeclarationContainerImpl.Companion = new Companion(null);
        KDeclarationContainerImpl.DEFAULT_CONSTRUCTOR_MARKER = DefaultConstructorMarker.class;
        KDeclarationContainerImpl.LOCAL_PROPERTY_SIGNATURE = new Regex("<v#(\\d+)>");
    }

    private final void addParametersAndMasks(List list0, String s, boolean z) {
        List list1 = this.loadParameterTypes(s);
        list0.addAll(list1);
        int v = list1.size();
        for(int v1 = 0; v1 < (v + 0x1F) / 0x20; ++v1) {
            Class class0 = Integer.TYPE;
            Intrinsics.checkNotNullExpressionValue(class0, "TYPE");
            list0.add(class0);
        }
        if(z) {
            list0.remove(KDeclarationContainerImpl.DEFAULT_CONSTRUCTOR_MARKER);
            Intrinsics.checkNotNullExpressionValue(KDeclarationContainerImpl.DEFAULT_CONSTRUCTOR_MARKER, "DEFAULT_CONSTRUCTOR_MARKER");
            list0.add(KDeclarationContainerImpl.DEFAULT_CONSTRUCTOR_MARKER);
            return;
        }
        list0.add(Object.class);
    }

    public final Constructor findConstructorBySignature(String s) {
        Intrinsics.checkNotNullParameter(s, "desc");
        return this.tryGetConstructor(this.getJClass(), this.loadParameterTypes(s));
    }

    public final Constructor findDefaultConstructor(String s) {
        Intrinsics.checkNotNullParameter(s, "desc");
        Class class0 = this.getJClass();
        List list0 = new ArrayList();
        this.addParametersAndMasks(list0, s, true);
        return this.tryGetConstructor(class0, list0);
    }

    public final Method findDefaultMethod(String s, String s1, boolean z) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "desc");
        if(Intrinsics.areEqual(s, "<init>")) {
            return null;
        }
        ArrayList arrayList0 = new ArrayList();
        if(z) {
            arrayList0.add(this.getJClass());
        }
        this.addParametersAndMasks(arrayList0, s1, false);
        return this.lookupMethod(this.getMethodOwner(), s + "$default", ((Class[])arrayList0.toArray(new Class[0])), this.loadReturnType(s1), z);
    }

    public final FunctionDescriptor findFunctionDescriptor(String s, String s1) {
        Collection collection0;
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        if(Intrinsics.areEqual(s, "<init>")) {
            collection0 = CollectionsKt.toList(this.getConstructorDescriptors());
        }
        else {
            Name name0 = Name.identifier(s);
            Intrinsics.checkNotNullExpressionValue(name0, "identifier(name)");
            collection0 = this.getFunctions(name0);
        }
        Collection collection1 = new ArrayList();
        for(Object object0: collection0) {
            if(Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapSignature(((FunctionDescriptor)object0)).asString(), s1)) {
                collection1.add(object0);
            }
        }
        if(((List)collection1).size() != 1) {
            String s2 = CollectionsKt.joinToString$default(collection0, "\n", null, null, 0, null, kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findFunctionDescriptor.allMembers.1.INSTANCE, 30, null);
            throw new KotlinReflectionInternalError("Function \'" + s + "\' (JVM signature: " + s1 + ") not resolved in " + this + ':' + (s2.length() == 0 ? " no members found" : "\n" + s2));
        }
        return (FunctionDescriptor)CollectionsKt.single(((List)collection1));

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findFunctionDescriptor.allMembers.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findFunctionDescriptor.allMembers.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findFunctionDescriptor.allMembers.1.INSTANCE = new kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findFunctionDescriptor.allMembers.1();
            }

            kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findFunctionDescriptor.allMembers.1() {
                super(1);
            }

            public final CharSequence invoke(FunctionDescriptor functionDescriptor0) {
                Intrinsics.checkNotNullParameter(functionDescriptor0, "descriptor");
                return DescriptorRenderer.DEBUG_TEXT.render(functionDescriptor0) + " | " + RuntimeTypeMapper.INSTANCE.mapSignature(functionDescriptor0).asString();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FunctionDescriptor)object0));
            }
        }

    }

    public final Method findMethodBySignature(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "desc");
        if(Intrinsics.areEqual(s, "<init>")) {
            return null;
        }
        Object[] arr_object = this.loadParameterTypes(s1).toArray(new Class[0]);
        Class class0 = this.loadReturnType(s1);
        Method method0 = this.lookupMethod(this.getMethodOwner(), s, ((Class[])arr_object), class0, false);
        if(method0 != null) {
            return method0;
        }
        if(this.getMethodOwner().isInterface()) {
            Method method1 = this.lookupMethod(Object.class, s, ((Class[])arr_object), class0, false);
            return method1 == null ? null : method1;
        }
        return null;
    }

    public final PropertyDescriptor findPropertyDescriptor(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        MatchResult matchResult0 = KDeclarationContainerImpl.LOCAL_PROPERTY_SIGNATURE.matchEntire(s1);
        if(matchResult0 != null) {
            String s2 = (String)matchResult0.getDestructured().getMatch().getGroupValues().get(1);
            PropertyDescriptor propertyDescriptor0 = this.getLocalProperty(Integer.parseInt(s2));
            if(propertyDescriptor0 == null) {
                throw new KotlinReflectionInternalError("Local property #" + s2 + " not found in " + this.getJClass());
            }
            return propertyDescriptor0;
        }
        Name name0 = Name.identifier(s);
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(name)");
        Iterable iterable0 = this.getProperties(name0);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapPropertySignature(((PropertyDescriptor)object0)).asString(), s1)) {
                collection0.add(object0);
            }
        }
        if(((List)collection0).isEmpty()) {
            throw new KotlinReflectionInternalError("Property \'" + s + "\' (JVM signature: " + s1 + ") not resolved in " + this);
        }
        if(((List)collection0).size() != 1) {
            Map map0 = new LinkedHashMap();
            for(Object object1: ((List)collection0)) {
                DescriptorVisibility descriptorVisibility0 = ((PropertyDescriptor)object1).getVisibility();
                ArrayList arrayList0 = map0.get(descriptorVisibility0);
                if(arrayList0 == null) {
                    arrayList0 = new ArrayList();
                    map0.put(descriptorVisibility0, arrayList0);
                }
                arrayList0.add(object1);
            }
            Collection collection1 = MapsKt.toSortedMap(map0, (Object object0, Object object1) -> {
                Intrinsics.checkNotNullParameter(kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.mostVisibleProperties.2.INSTANCE, "$tmp0");
                return ((Number)kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.mostVisibleProperties.2.INSTANCE.invoke(object0, object1)).intValue();
            }).values();
            Intrinsics.checkNotNullExpressionValue(collection1, "properties\n             …\n                }.values");
            List list0 = (List)CollectionsKt.last(collection1);
            if(list0.size() == 1) {
                Intrinsics.checkNotNullExpressionValue(list0, "mostVisibleProperties");
                return (PropertyDescriptor)CollectionsKt.first(list0);
            }
            Name name1 = Name.identifier(s);
            Intrinsics.checkNotNullExpressionValue(name1, "identifier(name)");
            String s3 = CollectionsKt.joinToString$default(this.getProperties(name1), "\n", null, null, 0, null, kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.allMembers.1.INSTANCE, 30, null);
            throw new KotlinReflectionInternalError("Property \'" + s + "\' (JVM signature: " + s1 + ") not resolved in " + this + ':' + (s3.length() == 0 ? " no members found" : "\n" + s3));
        }
        return (PropertyDescriptor)CollectionsKt.single(((List)collection0));

        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.allMembers.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.allMembers.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.allMembers.1.INSTANCE = new kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.allMembers.1();
            }

            kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.allMembers.1() {
                super(1);
            }

            public final CharSequence invoke(PropertyDescriptor propertyDescriptor0) {
                Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
                return DescriptorRenderer.DEBUG_TEXT.render(propertyDescriptor0) + " | " + RuntimeTypeMapper.INSTANCE.mapPropertySignature(propertyDescriptor0).asString();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((PropertyDescriptor)object0));
            }
        }


        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001A\u00020\u00012\u000E\u0010\u0002\u001A\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000E\u0010\u0005\u001A\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "first", "Lkotlin/reflect/jvm/internal/impl/descriptors/DescriptorVisibility;", "kotlin.jvm.PlatformType", "second", "invoke", "(Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.mostVisibleProperties.2 extends Lambda implements Function2 {
            public static final kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.mostVisibleProperties.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.mostVisibleProperties.2.INSTANCE = new kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.mostVisibleProperties.2();
            }

            kotlin.reflect.jvm.internal.KDeclarationContainerImpl.findPropertyDescriptor.mostVisibleProperties.2() {
                super(2);
            }

            public final Integer invoke(DescriptorVisibility descriptorVisibility0, DescriptorVisibility descriptorVisibility1) {
                Integer integer0 = DescriptorVisibilities.compare(descriptorVisibility0, descriptorVisibility1);
                return integer0 == null ? 0 : ((int)integer0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((DescriptorVisibility)object0), ((DescriptorVisibility)object1));
            }
        }

    }

    // 检测为 Lambda 实现
    private static final int findPropertyDescriptor$lambda$3(Function2 function20, Object object0, Object object1) [...]

    public abstract Collection getConstructorDescriptors();

    public abstract Collection getFunctions(Name arg1);

    public abstract PropertyDescriptor getLocalProperty(int arg1);

    protected final Collection getMembers(MemberScope memberScope0, MemberBelonginess kDeclarationContainerImpl$MemberBelonginess0) {
        Intrinsics.checkNotNullParameter(memberScope0, "scope");
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl$MemberBelonginess0, "belonginess");
        kotlin.reflect.jvm.internal.KDeclarationContainerImpl.getMembers.visitor.1 kDeclarationContainerImpl$getMembers$visitor$10 = new CreateKCallableVisitor(/*ERROR_MISSING_ARG_0/*) {
            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies
            public Object visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor0, Object object0) {
                return this.visitConstructorDescriptor(constructorDescriptor0, ((Unit)object0));
            }

            public KCallableImpl visitConstructorDescriptor(ConstructorDescriptor constructorDescriptor0, Unit unit0) {
                Intrinsics.checkNotNullParameter(constructorDescriptor0, "descriptor");
                Intrinsics.checkNotNullParameter(unit0, "data");
                throw new IllegalStateException("No constructors should appear here: " + constructorDescriptor0);
            }
        };
        Iterable iterable0 = DefaultImpls.getContributedDescriptors$default(memberScope0, null, null, 3, null);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            DeclarationDescriptor declarationDescriptor0 = (DeclarationDescriptor)object0;
            KCallableImpl kCallableImpl0 = !(declarationDescriptor0 instanceof CallableMemberDescriptor) || Intrinsics.areEqual(((CallableMemberDescriptor)declarationDescriptor0).getVisibility(), DescriptorVisibilities.INVISIBLE_FAKE) || !kDeclarationContainerImpl$MemberBelonginess0.accept(((CallableMemberDescriptor)declarationDescriptor0)) ? null : ((KCallableImpl)declarationDescriptor0.accept(kDeclarationContainerImpl$getMembers$visitor$10, Unit.INSTANCE));
            if(kCallableImpl0 != null) {
                collection0.add(kCallableImpl0);
            }
        }
        return CollectionsKt.toList(((List)collection0));
    }

    protected Class getMethodOwner() {
        Class class0 = ReflectClassUtilKt.getWrapperByPrimitive(this.getJClass());
        return class0 == null ? this.getJClass() : class0;
    }

    public abstract Collection getProperties(Name arg1);

    private final List loadParameterTypes(String s) {
        int v3;
        ArrayList arrayList0 = new ArrayList();
        for(int v = 1; s.charAt(v) != 41; v = v3) {
            int v1;
            for(v1 = v; s.charAt(v1) == 91; ++v1) {
            }
            int v2 = s.charAt(v1);
            if(StringsKt.contains$default("VZCBSIFJD", ((char)v2), false, 2, null)) {
                v3 = v1 + 1;
            }
            else {
                if(v2 != 76) {
                    throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + s);
                }
                v3 = StringsKt.indexOf$default(s, ';', v, false, 4, null) + 1;
            }
            arrayList0.add(this.parseType(s, v, v3));
        }
        return arrayList0;
    }

    private final Class loadReturnType(String s) {
        return this.parseType(s, StringsKt.indexOf$default(s, ')', 0, false, 6, null) + 1, s.length());
    }

    private final Method lookupMethod(Class class0, String s, Class[] arr_class, Class class1, boolean z) {
        if(z) {
            arr_class[0] = class0;
        }
        Method method0 = this.tryGetMethod(class0, s, arr_class, class1);
        if(method0 != null) {
            return method0;
        }
        Class class2 = class0.getSuperclass();
        if(class2 != null) {
            Method method1 = this.lookupMethod(class2, s, arr_class, class1, z);
            if(method1 != null) {
                return method1;
            }
        }
        Class[] arr_class1 = class0.getInterfaces();
        Intrinsics.checkNotNullExpressionValue(arr_class1, "interfaces");
        for(int v = 0; v < arr_class1.length; ++v) {
            Class class3 = arr_class1[v];
            Intrinsics.checkNotNullExpressionValue(class3, "superInterface");
            Method method2 = this.lookupMethod(class3, s, arr_class, class1, z);
            if(method2 != null) {
                return method2;
            }
            if(z) {
                Class class4 = ReflectJavaClassFinderKt.tryLoadClass(ReflectClassUtilKt.getSafeClassLoader(class3), class3.getName() + "$DefaultImpls");
                if(class4 != null) {
                    arr_class[0] = class3;
                    Method method3 = this.tryGetMethod(class4, s, arr_class, class1);
                    if(method3 != null) {
                        return method3;
                    }
                }
            }
        }
        return null;
    }

    private final Class parseType(String s, int v, int v1) {
        switch(s.charAt(v)) {
            case 66: {
                return Byte.TYPE;
            }
            case 67: {
                return Character.TYPE;
            }
            case 68: {
                return Double.TYPE;
            }
            case 70: {
                return Float.TYPE;
            }
            case 73: {
                return Integer.TYPE;
            }
            case 74: {
                return Long.TYPE;
            }
            case 76: {
                ClassLoader classLoader0 = ReflectClassUtilKt.getSafeClassLoader(this.getJClass());
                String s1 = s.substring(v + 1, v1 - 1);
                Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
                Class class0 = classLoader0.loadClass(StringsKt.replace$default(s1, '/', '.', false, 4, null));
                Intrinsics.checkNotNullExpressionValue(class0, "jClass.safeClassLoader.l…d - 1).replace(\'/\', \'.\'))");
                return class0;
            }
            case 83: {
                return Short.TYPE;
            }
            case 86: {
                Class class1 = Void.TYPE;
                Intrinsics.checkNotNullExpressionValue(class1, "TYPE");
                return class1;
            }
            case 90: {
                return Boolean.TYPE;
            }
            case 91: {
                return UtilKt.createArrayType(this.parseType(s, v + 1, v1));
            }
            default: {
                throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + s);
            }
        }
    }

    private final Constructor tryGetConstructor(Class class0, List list0) {
        try {
            Class[] arr_class = (Class[])list0.toArray(new Class[0]);
            return class0.getDeclaredConstructor(((Class[])Arrays.copyOf(arr_class, arr_class.length)));
        }
        catch(NoSuchMethodException unused_ex) {
            return null;
        }
    }

    private final Method tryGetMethod(Class class0, String s, Class[] arr_class, Class class1) {
        Object object0;
        try {
            Method method0 = class0.getDeclaredMethod(s, ((Class[])Arrays.copyOf(arr_class, arr_class.length)));
            if(Intrinsics.areEqual(method0.getReturnType(), class1)) {
                return method0;
            }
            Method[] arr_method = class0.getDeclaredMethods();
            Intrinsics.checkNotNullExpressionValue(arr_method, "declaredMethods");
            for(int v = 0; true; ++v) {
                object0 = null;
                if(v >= arr_method.length) {
                    break;
                }
                object0 = arr_method[v];
                Method method1 = (Method)object0;
                if(Intrinsics.areEqual(method1.getName(), s) && Intrinsics.areEqual(method1.getReturnType(), class1) && Arrays.equals(method1.getParameterTypes(), arr_class)) {
                    break;
                }
            }
            return (Method)object0;
        }
        catch(NoSuchMethodException unused_ex) {
            return null;
        }
    }
}

