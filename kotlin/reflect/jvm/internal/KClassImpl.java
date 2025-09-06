package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty;
import kotlin.reflect.KVisibility;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMapping;
import kotlin.reflect.jvm.internal.impl.builtins.CompanionObjectMappingUtilsKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.MemberDeserializer;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u00BA\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u00052\u00020\u0006:\u0001eB\u0013\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\b\u00A2\u0006\u0002\u0010\tJ\u0013\u0010T\u001A\u00020&2\b\u0010U\u001A\u0004\u0018\u00010\u0002H\u0096\u0002J\u0016\u0010V\u001A\b\u0012\u0004\u0012\u00020W0\u00142\u0006\u0010X\u001A\u00020YH\u0016J\u0012\u0010Z\u001A\u0004\u0018\u00010[2\u0006\u0010\\\u001A\u00020]H\u0016J\u0016\u0010^\u001A\b\u0012\u0004\u0012\u00020[0\u00142\u0006\u0010X\u001A\u00020YH\u0016J\b\u0010_\u001A\u00020]H\u0016J\u0012\u0010`\u001A\u00020&2\b\u0010a\u001A\u0004\u0018\u00010\u0002H\u0016J\b\u0010b\u001A\u00020cH\u0002J\b\u0010d\u001A\u00020AH\u0016R\u001A\u0010\n\u001A\b\u0012\u0004\u0012\u00020\f0\u000B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u0014\u0010\u000F\u001A\u00020\u00108BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u001A\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00150\u00148VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017R \u0010\u0018\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00190\u00148VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001A\u0010\u0017R3\u0010\u001B\u001A$\u0012 \u0012\u001E \u001E*\u000E\u0018\u00010\u001DR\b\u0012\u0004\u0012\u00028\u00000\u00000\u001DR\b\u0012\u0004\u0012\u00028\u00000\u00000\u001C\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 R\u0014\u0010!\u001A\u00020\"8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b#\u0010$R\u0014\u0010%\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b%\u0010\'R\u0014\u0010(\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b(\u0010\'R\u0014\u0010)\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b)\u0010\'R\u0014\u0010*\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b*\u0010\'R\u0014\u0010+\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b+\u0010\'R\u0014\u0010,\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b,\u0010\'R\u0014\u0010-\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b-\u0010\'R\u0014\u0010.\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b.\u0010\'R\u001A\u0010/\u001A\u00020&8VX\u0096\u0004\u00A2\u0006\f\u0012\u0004\b0\u00101\u001A\u0004\b/\u0010\'R\u001A\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\bX\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b2\u00103R\u0014\u00104\u001A\u0002058@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b6\u00107R\u001E\u00108\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003090\u00148VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b:\u0010\u0017R\u001E\u0010;\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u00148VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b<\u0010\u0017R\u0016\u0010=\u001A\u0004\u0018\u00018\u00008VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b>\u0010?R\u0016\u0010@\u001A\u0004\u0018\u00010A8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\bB\u0010CR\"\u0010D\u001A\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00040\u000B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\bE\u0010\u000ER\u0016\u0010F\u001A\u0004\u0018\u00010A8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\bG\u0010CR\u0014\u0010H\u001A\u0002058@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\bI\u00107R\u001A\u0010J\u001A\b\u0012\u0004\u0012\u00020K0\u000B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\bL\u0010\u000ER\u001A\u0010M\u001A\b\u0012\u0004\u0012\u00020N0\u000B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\bO\u0010\u000ER\u0016\u0010P\u001A\u0004\u0018\u00010Q8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\bR\u0010S\u00A8\u0006f"}, d2 = {"Lkotlin/reflect/jvm/internal/KClassImpl;", "T", "", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "Lkotlin/reflect/KClass;", "Lkotlin/reflect/jvm/internal/KClassifierImpl;", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "jClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "classId", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "constructorDescriptors", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "()Ljava/util/Collection;", "constructors", "Lkotlin/reflect/KFunction;", "getConstructors", "data", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazyVal;", "Lkotlin/reflect/jvm/internal/KClassImpl$Data;", "kotlin.jvm.PlatformType", "getData", "()Lkotlin/reflect/jvm/internal/ReflectProperties$LazyVal;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "isAbstract", "", "()Z", "isCompanion", "isData", "isFinal", "isFun", "isInner", "isOpen", "isSealed", "isValue", "isValue$annotations", "()V", "getJClass", "()Ljava/lang/Class;", "memberScope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "getMemberScope$kotlin_reflection", "()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "getSealedSubclasses", "simpleName", "getSimpleName", "staticScope", "getStaticScope$kotlin_reflection", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "name", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getLocalProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "index", "", "getProperties", "hashCode", "isInstance", "value", "reportUnresolvedClass", "", "toString", "Data", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KClassImpl extends KDeclarationContainerImpl implements KClass, KClassifierImpl, KTypeParameterOwnerImpl {
    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010 \n\u0002\u0010\u001B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000E\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005\u00A2\u0006\u0002\u0010\u0003J\u0014\u0010N\u001A\u00020<2\n\u0010O\u001A\u0006\u0012\u0002\b\u00030PH\u0002R%\u0010\u0004\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b\t\u0010\n\u001A\u0004\b\u0007\u0010\bR%\u0010\u000B\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b\r\u0010\n\u001A\u0004\b\f\u0010\bR%\u0010\u000E\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b\u0010\u0010\n\u001A\u0004\b\u000F\u0010\bR!\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00130\u00128FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b\u0016\u0010\n\u001A\u0004\b\u0014\u0010\u0015R-\u0010\u0017\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00180\u00058FX\u0086\u0084\u0002\u00A2\u0006\u0012\n\u0004\b\u001C\u0010\n\u0012\u0004\b\u0019\u0010\u001A\u001A\u0004\b\u001B\u0010\bR%\u0010\u001D\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b\u001F\u0010\n\u001A\u0004\b\u001E\u0010\bR%\u0010 \u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b\"\u0010\n\u001A\u0004\b!\u0010\bR%\u0010#\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058BX\u0082\u0084\u0002\u00A2\u0006\f\n\u0004\b%\u0010\n\u001A\u0004\b$\u0010\bR\u001B\u0010&\u001A\u00020\'8FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b*\u0010\n\u001A\u0004\b(\u0010)R%\u0010+\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058BX\u0082\u0084\u0002\u00A2\u0006\f\n\u0004\b-\u0010\n\u001A\u0004\b,\u0010\bR%\u0010.\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00058BX\u0082\u0084\u0002\u00A2\u0006\f\n\u0004\b0\u0010\n\u001A\u0004\b/\u0010\bR%\u00101\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u0003020\u00058FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b4\u0010\n\u001A\u0004\b3\u0010\bR#\u00105\u001A\u0004\u0018\u00018\u00008FX\u0086\u0084\u0002\u00A2\u0006\u0012\n\u0004\b9\u0010:\u0012\u0004\b6\u0010\u001A\u001A\u0004\b7\u00108R\u001D\u0010;\u001A\u0004\u0018\u00010<8FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\b?\u0010\n\u001A\u0004\b=\u0010>R)\u0010@\u001A\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u0000020\u00128FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\bB\u0010\n\u001A\u0004\bA\u0010\u0015R\u001D\u0010C\u001A\u0004\u0018\u00010<8FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\bE\u0010\n\u001A\u0004\bD\u0010>R!\u0010F\u001A\b\u0012\u0004\u0012\u00020G0\u00128FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\bI\u0010\n\u001A\u0004\bH\u0010\u0015R!\u0010J\u001A\b\u0012\u0004\u0012\u00020K0\u00128FX\u0086\u0084\u0002\u00A2\u0006\f\n\u0004\bM\u0010\n\u001A\u0004\bL\u0010\u0015\u00A8\u0006Q"}, d2 = {"Lkotlin/reflect/jvm/internal/KClassImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "(Lkotlin/reflect/jvm/internal/KClassImpl;)V", "allMembers", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "getAllMembers", "()Ljava/util/Collection;", "allMembers$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "allNonStaticMembers", "getAllNonStaticMembers", "allNonStaticMembers$delegate", "allStaticMembers", "getAllStaticMembers", "allStaticMembers$delegate", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "annotations$delegate", "constructors", "Lkotlin/reflect/KFunction;", "getConstructors$annotations", "()V", "getConstructors", "constructors$delegate", "declaredMembers", "getDeclaredMembers", "declaredMembers$delegate", "declaredNonStaticMembers", "getDeclaredNonStaticMembers", "declaredNonStaticMembers$delegate", "declaredStaticMembers", "getDeclaredStaticMembers", "declaredStaticMembers$delegate", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "descriptor$delegate", "inheritedNonStaticMembers", "getInheritedNonStaticMembers", "inheritedNonStaticMembers$delegate", "inheritedStaticMembers", "getInheritedStaticMembers", "inheritedStaticMembers$delegate", "nestedClasses", "Lkotlin/reflect/KClass;", "getNestedClasses", "nestedClasses$delegate", "objectInstance", "getObjectInstance$annotations", "getObjectInstance", "()Ljava/lang/Object;", "objectInstance$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazyVal;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "qualifiedName$delegate", "sealedSubclasses", "getSealedSubclasses", "sealedSubclasses$delegate", "simpleName", "getSimpleName", "simpleName$delegate", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes", "supertypes$delegate", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", "typeParameters$delegate", "calculateLocalClassName", "jClass", "Ljava/lang/Class;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public final class Data extends kotlin.reflect.jvm.internal.KDeclarationContainerImpl.Data {
        static final KProperty[] $$delegatedProperties;
        private final LazySoftVal allMembers$delegate;
        private final LazySoftVal allNonStaticMembers$delegate;
        private final LazySoftVal allStaticMembers$delegate;
        private final LazySoftVal annotations$delegate;
        private final LazySoftVal constructors$delegate;
        private final LazySoftVal declaredMembers$delegate;
        private final LazySoftVal declaredNonStaticMembers$delegate;
        private final LazySoftVal declaredStaticMembers$delegate;
        private final LazySoftVal descriptor$delegate;
        private final LazySoftVal inheritedNonStaticMembers$delegate;
        private final LazySoftVal inheritedStaticMembers$delegate;
        private final LazySoftVal nestedClasses$delegate;
        private final LazyVal objectInstance$delegate;
        private final LazySoftVal qualifiedName$delegate;
        private final LazySoftVal sealedSubclasses$delegate;
        private final LazySoftVal simpleName$delegate;
        private final LazySoftVal supertypes$delegate;
        private final LazySoftVal typeParameters$delegate;

        static {
            Data.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "annotations", "getAnnotations()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "simpleName", "getSimpleName()Ljava/lang/String;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "qualifiedName", "getQualifiedName()Ljava/lang/String;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "constructors", "getConstructors()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "nestedClasses", "getNestedClasses()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "objectInstance", "getObjectInstance()Ljava/lang/Object;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "typeParameters", "getTypeParameters()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "supertypes", "getSupertypes()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "sealedSubclasses", "getSealedSubclasses()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "declaredNonStaticMembers", "getDeclaredNonStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "declaredStaticMembers", "getDeclaredStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "inheritedNonStaticMembers", "getInheritedNonStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "inheritedStaticMembers", "getInheritedStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "allNonStaticMembers", "getAllNonStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "allStaticMembers", "getAllStaticMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "declaredMembers", "getDeclaredMembers()Ljava/util/Collection;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "allMembers", "getAllMembers()Ljava/util/Collection;"))};
        }

        public Data() {
            this.descriptor$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final ClassDescriptor invoke() {
                    ClassId classId0 = KClassImpl.this.getClassId();
                    RuntimeModuleData runtimeModuleData0 = ((Data)KClassImpl.this.getData().invoke()).getModuleData();
                    ClassDescriptor classDescriptor0 = classId0.isLocal() ? runtimeModuleData0.getDeserialization().deserializeClass(classId0) : FindClassInModuleKt.findClassAcrossModuleDependencies(runtimeModuleData0.getModule(), classId0);
                    if(classDescriptor0 != null) {
                        return classDescriptor0;
                    }
                    KClassImpl.this.reportUnresolvedClass();
                    throw null;
                }
            });
            this.annotations$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return UtilKt.computeAnnotations(Data.this.getDescriptor());
                }
            });
            this.simpleName$delegate = ReflectProperties.lazySoft(new Function0(this) {
                {
                    KClassImpl.this = kClassImpl0;
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final String invoke() {
                    if(KClassImpl.this.getJClass().isAnonymousClass()) {
                        return null;
                    }
                    ClassId classId0 = KClassImpl.this.getClassId();
                    if(classId0.isLocal()) {
                        return Data.access$calculateLocalClassName(Data.this, KClassImpl.this.getJClass());
                    }
                    String s = classId0.getShortClassName().asString();
                    Intrinsics.checkNotNullExpressionValue(s, "classId.shortClassName.asString()");
                    return s;
                }
            });
            this.qualifiedName$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final String invoke() {
                    if(KClassImpl.this.getJClass().isAnonymousClass()) {
                        return null;
                    }
                    ClassId classId0 = KClassImpl.this.getClassId();
                    return classId0.isLocal() ? null : classId0.asSingleFqName().asString();
                }
            });
            this.constructors$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    Iterable iterable0 = KClassImpl.this.getConstructorDescriptors();
                    KClassImpl kClassImpl0 = KClassImpl.this;
                    ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                    for(Object object0: iterable0) {
                        arrayList0.add(new KFunctionImpl(kClassImpl0, ((ConstructorDescriptor)object0)));
                    }
                    return arrayList0;
                }
            });
            this.nestedClasses$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    MemberScope memberScope0 = Data.this.getDescriptor().getUnsubstitutedInnerClassesScope();
                    Intrinsics.checkNotNullExpressionValue(memberScope0, "descriptor.unsubstitutedInnerClassesScope");
                    Iterable iterable0 = DefaultImpls.getContributedDescriptors$default(memberScope0, null, null, 3, null);
                    Collection collection0 = new ArrayList();
                    for(Object object0: iterable0) {
                        if(!DescriptorUtils.isEnumEntry(((DeclarationDescriptor)object0))) {
                            collection0.add(object0);
                        }
                    }
                    Collection collection1 = new ArrayList();
                    for(Object object1: ((List)collection0)) {
                        DeclarationDescriptor declarationDescriptor0 = (DeclarationDescriptor)object1;
                        ClassDescriptor classDescriptor0 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
                        Class class0 = classDescriptor0 == null ? null : UtilKt.toJavaClass(classDescriptor0);
                        KClassImpl kClassImpl0 = class0 == null ? null : new KClassImpl(class0);
                        if(kClassImpl0 != null) {
                            collection1.add(kClassImpl0);
                        }
                    }
                    return (List)collection1;
                }
            });
            this.objectInstance$delegate = ReflectProperties.lazy(new Function0(kClassImpl0) {
                {
                    Data.this = kClassImpl$Data0;
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Field field0;
                    ClassDescriptor classDescriptor0 = Data.this.getDescriptor();
                    if(classDescriptor0.getKind() != ClassKind.OBJECT) {
                        return null;
                    }
                    if(!classDescriptor0.isCompanionObject() || CompanionObjectMappingUtilsKt.isMappedIntrinsicCompanionObject(CompanionObjectMapping.INSTANCE, classDescriptor0)) {
                        field0 = KClassImpl.this.getJClass().getDeclaredField("INSTANCE");
                    }
                    else {
                        String s = classDescriptor0.getName().asString();
                        field0 = KClassImpl.this.getJClass().getEnclosingClass().getDeclaredField(s);
                    }
                    Object object0 = field0.get(null);
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.KClassImpl");
                    return object0;
                }
            });
            this.typeParameters$delegate = ReflectProperties.lazySoft(new Function0(kClassImpl0) {
                {
                    Data.this = kClassImpl$Data0;
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    List list0 = Data.this.getDescriptor().getDeclaredTypeParameters();
                    Intrinsics.checkNotNullExpressionValue(list0, "descriptor.declaredTypeParameters");
                    KClassImpl kClassImpl0 = KClassImpl.this;
                    ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                    for(Object object0: list0) {
                        Intrinsics.checkNotNullExpressionValue(((TypeParameterDescriptor)object0), "descriptor");
                        arrayList0.add(new KTypeParameterImpl(kClassImpl0, ((TypeParameterDescriptor)object0)));
                    }
                    return arrayList0;
                }
            });
            this.supertypes$delegate = ReflectProperties.lazySoft(new Function0(kClassImpl0) {
                {
                    Data.this = kClassImpl$Data0;
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    Collection collection0 = Data.this.getDescriptor().getTypeConstructor().getSupertypes();
                    Intrinsics.checkNotNullExpressionValue(collection0, "descriptor.typeConstructor.supertypes");
                    ArrayList arrayList0 = new ArrayList(collection0.size());
                    Data kClassImpl$Data0 = Data.this;
                    KClassImpl kClassImpl0 = KClassImpl.this;
                    for(Object object0: collection0) {
                        Intrinsics.checkNotNullExpressionValue(((KotlinType)object0), "kotlinType");
                        arrayList0.add(new KTypeImpl(((KotlinType)object0), new Function0(kClassImpl$Data0, kClassImpl0) {
                            final KotlinType $kotlinType;

                            {
                                this.$kotlinType = kotlinType0;
                                Data.this = kClassImpl$Data0;
                                KClassImpl.this = kClassImpl0;
                                super(0);
                            }

                            @Override  // kotlin.jvm.functions.Function0
                            public Object invoke() {
                                return this.invoke();
                            }

                            public final Type invoke() {
                                ClassifierDescriptor classifierDescriptor0 = this.$kotlinType.getConstructor().getDeclarationDescriptor();
                                if(!(classifierDescriptor0 instanceof ClassDescriptor)) {
                                    throw new KotlinReflectionInternalError("Supertype not a class: " + classifierDescriptor0);
                                }
                                Class class0 = UtilKt.toJavaClass(((ClassDescriptor)classifierDescriptor0));
                                if(class0 == null) {
                                    throw new KotlinReflectionInternalError("Unsupported superclass of " + Data.this + ": " + classifierDescriptor0);
                                }
                                if(Intrinsics.areEqual(KClassImpl.this.getJClass().getSuperclass(), class0)) {
                                    Type type0 = KClassImpl.this.getJClass().getGenericSuperclass();
                                    Intrinsics.checkNotNullExpressionValue(type0, "{\n                      …ass\n                    }");
                                    return type0;
                                }
                                Class[] arr_class = KClassImpl.this.getJClass().getInterfaces();
                                Intrinsics.checkNotNullExpressionValue(arr_class, "jClass.interfaces");
                                int v = ArraysKt.indexOf(arr_class, class0);
                                if(v < 0) {
                                    throw new KotlinReflectionInternalError("No superclass of " + Data.this + " in Java reflection for " + classifierDescriptor0);
                                }
                                Type type1 = KClassImpl.this.getJClass().getGenericInterfaces()[v];
                                Intrinsics.checkNotNullExpressionValue(type1, "{\n                      …ex]\n                    }");
                                return type1;
                            }
                        }));
                    }
                    if(!KotlinBuiltIns.isSpecialClassWithNoSupertypes(Data.this.getDescriptor())) {
                        if(!(arrayList0 instanceof Collection) || !arrayList0.isEmpty()) {
                            for(Object object1: arrayList0) {
                                ClassKind classKind0 = DescriptorUtils.getClassDescriptorForType(((KTypeImpl)object1).getType()).getKind();
                                Intrinsics.checkNotNullExpressionValue(classKind0, "getClassDescriptorForType(it.type).kind");
                                if(classKind0 != ClassKind.INTERFACE && classKind0 != ClassKind.ANNOTATION_CLASS) {
                                    return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList0);
                                }
                            }
                        }
                        SimpleType simpleType0 = DescriptorUtilsKt.getBuiltIns(Data.this.getDescriptor()).getAnyType();
                        Intrinsics.checkNotNullExpressionValue(simpleType0, "descriptor.builtIns.anyType");
                        arrayList0.add(new KTypeImpl(simpleType0, kotlin.reflect.jvm.internal.KClassImpl.Data.supertypes.2.3.INSTANCE));
                    }
                    return kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.compact(arrayList0);

                    @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001A\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Ljava/lang/reflect/Type;", "T", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
                    final class kotlin.reflect.jvm.internal.KClassImpl.Data.supertypes.2.3 extends Lambda implements Function0 {
                        public static final kotlin.reflect.jvm.internal.KClassImpl.Data.supertypes.2.3 INSTANCE;

                        static {
                            kotlin.reflect.jvm.internal.KClassImpl.Data.supertypes.2.3.INSTANCE = new kotlin.reflect.jvm.internal.KClassImpl.Data.supertypes.2.3();
                        }

                        kotlin.reflect.jvm.internal.KClassImpl.Data.supertypes.2.3() {
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final Type invoke() {
                            return Object.class;
                        }
                    }

                }
            });
            this.sealedSubclasses$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    Collection collection0 = Data.this.getDescriptor().getSealedSubclasses();
                    Intrinsics.checkNotNullExpressionValue(collection0, "descriptor.sealedSubclasses");
                    Collection collection1 = new ArrayList();
                    for(Object object0: collection0) {
                        Intrinsics.checkNotNull(((ClassDescriptor)object0), "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                        Class class0 = UtilKt.toJavaClass(((ClassDescriptor)object0));
                        KClassImpl kClassImpl0 = class0 == null ? null : new KClassImpl(class0);
                        if(kClassImpl0 != null) {
                            collection1.add(kClassImpl0);
                        }
                    }
                    return (List)collection1;
                }
            });
            this.declaredNonStaticMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Collection invoke() {
                    MemberScope memberScope0 = KClassImpl.this.getMemberScope$kotlin_reflection();
                    return KClassImpl.this.getMembers(memberScope0, MemberBelonginess.DECLARED);
                }
            });
            this.declaredStaticMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Collection invoke() {
                    MemberScope memberScope0 = KClassImpl.this.getStaticScope$kotlin_reflection();
                    return KClassImpl.this.getMembers(memberScope0, MemberBelonginess.DECLARED);
                }
            });
            this.inheritedNonStaticMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Collection invoke() {
                    MemberScope memberScope0 = KClassImpl.this.getMemberScope$kotlin_reflection();
                    return KClassImpl.this.getMembers(memberScope0, MemberBelonginess.INHERITED);
                }
            });
            this.inheritedStaticMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    KClassImpl.this = kClassImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Collection invoke() {
                    MemberScope memberScope0 = KClassImpl.this.getStaticScope$kotlin_reflection();
                    return KClassImpl.this.getMembers(memberScope0, MemberBelonginess.INHERITED);
                }
            });
            this.allNonStaticMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return CollectionsKt.plus(Data.this.getDeclaredNonStaticMembers(), Data.access$getInheritedNonStaticMembers(Data.this));
                }
            });
            this.allStaticMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return CollectionsKt.plus(Data.access$getDeclaredStaticMembers(Data.this), Data.access$getInheritedStaticMembers(Data.this));
                }
            });
            this.declaredMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return CollectionsKt.plus(Data.this.getDeclaredNonStaticMembers(), Data.access$getDeclaredStaticMembers(Data.this));
                }
            });
            this.allMembers$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Data.this = kClassImpl$Data0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return CollectionsKt.plus(Data.this.getAllNonStaticMembers(), Data.this.getAllStaticMembers());
                }
            });
        }

        public static final String access$calculateLocalClassName(Data kClassImpl$Data0, Class class0) {
            return kClassImpl$Data0.calculateLocalClassName(class0);
        }

        public static final Collection access$getDeclaredStaticMembers(Data kClassImpl$Data0) {
            return kClassImpl$Data0.getDeclaredStaticMembers();
        }

        public static final Collection access$getInheritedNonStaticMembers(Data kClassImpl$Data0) {
            return kClassImpl$Data0.getInheritedNonStaticMembers();
        }

        public static final Collection access$getInheritedStaticMembers(Data kClassImpl$Data0) {
            return kClassImpl$Data0.getInheritedStaticMembers();
        }

        private final String calculateLocalClassName(Class class0) {
            String s = class0.getSimpleName();
            Method method0 = class0.getEnclosingMethod();
            if(method0 != null) {
                Intrinsics.checkNotNullExpressionValue(s, "name");
                return StringsKt.substringAfter$default(s, method0.getName() + '$', null, 2, null);
            }
            Constructor constructor0 = class0.getEnclosingConstructor();
            if(constructor0 != null) {
                Intrinsics.checkNotNullExpressionValue(s, "name");
                return StringsKt.substringAfter$default(s, constructor0.getName() + '$', null, 2, null);
            }
            Intrinsics.checkNotNullExpressionValue(s, "name");
            return StringsKt.substringAfter$default(s, '$', null, 2, null);
        }

        public final Collection getAllMembers() {
            Object object0 = this.allMembers$delegate.getValue(this, Data.$$delegatedProperties[17]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-allMembers>(...)");
            return (Collection)object0;
        }

        public final Collection getAllNonStaticMembers() {
            Object object0 = this.allNonStaticMembers$delegate.getValue(this, Data.$$delegatedProperties[14]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-allNonStaticMembers>(...)");
            return (Collection)object0;
        }

        public final Collection getAllStaticMembers() {
            Object object0 = this.allStaticMembers$delegate.getValue(this, Data.$$delegatedProperties[15]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-allStaticMembers>(...)");
            return (Collection)object0;
        }

        public final List getAnnotations() {
            Object object0 = this.annotations$delegate.getValue(this, Data.$$delegatedProperties[1]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-annotations>(...)");
            return (List)object0;
        }

        public final Collection getConstructors() {
            Object object0 = this.constructors$delegate.getValue(this, Data.$$delegatedProperties[4]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-constructors>(...)");
            return (Collection)object0;
        }

        public final Collection getDeclaredMembers() {
            Object object0 = this.declaredMembers$delegate.getValue(this, Data.$$delegatedProperties[16]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-declaredMembers>(...)");
            return (Collection)object0;
        }

        public final Collection getDeclaredNonStaticMembers() {
            Object object0 = this.declaredNonStaticMembers$delegate.getValue(this, Data.$$delegatedProperties[10]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-declaredNonStaticMembers>(...)");
            return (Collection)object0;
        }

        private final Collection getDeclaredStaticMembers() {
            Object object0 = this.declaredStaticMembers$delegate.getValue(this, Data.$$delegatedProperties[11]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-declaredStaticMembers>(...)");
            return (Collection)object0;
        }

        public final ClassDescriptor getDescriptor() {
            Object object0 = this.descriptor$delegate.getValue(this, Data.$$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-descriptor>(...)");
            return (ClassDescriptor)object0;
        }

        private final Collection getInheritedNonStaticMembers() {
            Object object0 = this.inheritedNonStaticMembers$delegate.getValue(this, Data.$$delegatedProperties[12]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-inheritedNonStaticMembers>(...)");
            return (Collection)object0;
        }

        private final Collection getInheritedStaticMembers() {
            Object object0 = this.inheritedStaticMembers$delegate.getValue(this, Data.$$delegatedProperties[13]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-inheritedStaticMembers>(...)");
            return (Collection)object0;
        }

        public final Collection getNestedClasses() {
            Object object0 = this.nestedClasses$delegate.getValue(this, Data.$$delegatedProperties[5]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-nestedClasses>(...)");
            return (Collection)object0;
        }

        public final Object getObjectInstance() {
            return this.objectInstance$delegate.getValue(this, Data.$$delegatedProperties[6]);
        }

        public final String getQualifiedName() {
            return (String)this.qualifiedName$delegate.getValue(this, Data.$$delegatedProperties[3]);
        }

        public final List getSealedSubclasses() {
            Object object0 = this.sealedSubclasses$delegate.getValue(this, Data.$$delegatedProperties[9]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-sealedSubclasses>(...)");
            return (List)object0;
        }

        public final String getSimpleName() {
            return (String)this.simpleName$delegate.getValue(this, Data.$$delegatedProperties[2]);
        }

        public final List getSupertypes() {
            Object object0 = this.supertypes$delegate.getValue(this, Data.$$delegatedProperties[8]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-supertypes>(...)");
            return (List)object0;
        }

        public final List getTypeParameters() {
            Object object0 = this.typeParameters$delegate.getValue(this, Data.$$delegatedProperties[7]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-typeParameters>(...)");
            return (List)object0;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Kind.values().length];
            try {
                arr_v[Kind.FILE_FACADE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.MULTIFILE_CLASS.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.MULTIFILE_CLASS_PART.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.SYNTHETIC_CLASS.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.UNKNOWN.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.CLASS.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final LazyVal data;
    private final Class jClass;

    public KClassImpl(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "jClass");
        super();
        this.jClass = class0;
        LazyVal reflectProperties$LazyVal0 = ReflectProperties.lazy(new Function0() {
            {
                KClassImpl.this = kClassImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Data invoke() {
                return new Data(KClassImpl.this);
            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazyVal0, "lazy { Data() }");
        this.data = reflectProperties$LazyVal0;
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.KClass
    public boolean equals(Object object0) {
        return object0 instanceof KClassImpl && Intrinsics.areEqual(JvmClassMappingKt.getJavaObjectType(this), JvmClassMappingKt.getJavaObjectType(((KClass)object0)));
    }

    @Override  // kotlin.reflect.KAnnotatedElement
    public List getAnnotations() {
        return ((Data)this.data.invoke()).getAnnotations();
    }

    private final ClassId getClassId() {
        return RuntimeTypeMapper.INSTANCE.mapJvmClassToKotlinClassId(this.getJClass());
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getConstructorDescriptors() {
        ClassDescriptor classDescriptor0 = this.getDescriptor();
        if(classDescriptor0.getKind() != ClassKind.INTERFACE && classDescriptor0.getKind() != ClassKind.OBJECT) {
            Collection collection0 = classDescriptor0.getConstructors();
            Intrinsics.checkNotNullExpressionValue(collection0, "descriptor.constructors");
            return collection0;
        }
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.KClass
    public Collection getConstructors() {
        return ((Data)this.data.invoke()).getConstructors();
    }

    public final LazyVal getData() {
        return this.data;
    }

    public ClassDescriptor getDescriptor() {
        return ((Data)this.data.invoke()).getDescriptor();
    }

    @Override  // kotlin.reflect.jvm.internal.KClassifierImpl
    public ClassifierDescriptor getDescriptor() {
        return this.getDescriptor();
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getFunctions(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return CollectionsKt.plus(this.getMemberScope$kotlin_reflection().getContributedFunctions(name0, NoLookupLocation.FROM_REFLECTION), this.getStaticScope$kotlin_reflection().getContributedFunctions(name0, NoLookupLocation.FROM_REFLECTION));
    }

    @Override  // kotlin.jvm.internal.ClassBasedDeclarationContainer
    public Class getJClass() {
        return this.jClass;
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public PropertyDescriptor getLocalProperty(int v) {
        if(Intrinsics.areEqual(this.getJClass().getSimpleName(), "DefaultImpls")) {
            Class class0 = this.getJClass().getDeclaringClass();
            if(class0 != null && class0.isInterface()) {
                KClass kClass0 = JvmClassMappingKt.getKotlinClass(class0);
                Intrinsics.checkNotNull(kClass0, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
                return ((KClassImpl)kClass0).getLocalProperty(v);
            }
        }
        ClassDescriptor classDescriptor0 = this.getDescriptor();
        DeserializedClassDescriptor deserializedClassDescriptor0 = classDescriptor0 instanceof DeserializedClassDescriptor ? ((DeserializedClassDescriptor)classDescriptor0) : null;
        if(deserializedClassDescriptor0 != null) {
            Intrinsics.checkNotNullExpressionValue(JvmProtoBuf.classLocalVariable, "classLocalVariable");
            Property protoBuf$Property0 = (Property)ProtoBufUtilKt.getExtensionOrNull(deserializedClassDescriptor0.getClassProto(), JvmProtoBuf.classLocalVariable, v);
            return protoBuf$Property0 == null ? null : ((PropertyDescriptor)UtilKt.deserializeToDescriptor(this.getJClass(), protoBuf$Property0, deserializedClassDescriptor0.getC().getNameResolver(), deserializedClassDescriptor0.getC().getTypeTable(), deserializedClassDescriptor0.getMetadataVersion(), kotlin.reflect.jvm.internal.KClassImpl.getLocalProperty.2.1.1.INSTANCE));
        }
        return null;

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.jvm.internal.KClassImpl.getLocalProperty.2.1.1 extends FunctionReference implements Function2 {
            public static final kotlin.reflect.jvm.internal.KClassImpl.getLocalProperty.2.1.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.KClassImpl.getLocalProperty.2.1.1.INSTANCE = new kotlin.reflect.jvm.internal.KClassImpl.getLocalProperty.2.1.1();
            }

            kotlin.reflect.jvm.internal.KClassImpl.getLocalProperty.2.1.1() {
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

    public final MemberScope getMemberScope$kotlin_reflection() {
        return this.getDescriptor().getDefaultType().getMemberScope();
    }

    @Override  // kotlin.reflect.KClass, kotlin.reflect.KDeclarationContainer
    public Collection getMembers() {
        return ((Data)this.data.invoke()).getAllMembers();
    }

    @Override  // kotlin.reflect.KClass
    public Collection getNestedClasses() {
        return ((Data)this.data.invoke()).getNestedClasses();
    }

    @Override  // kotlin.reflect.KClass
    public Object getObjectInstance() {
        return ((Data)this.data.invoke()).getObjectInstance();
    }

    @Override  // kotlin.reflect.jvm.internal.KDeclarationContainerImpl
    public Collection getProperties(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return CollectionsKt.plus(this.getMemberScope$kotlin_reflection().getContributedVariables(name0, NoLookupLocation.FROM_REFLECTION), this.getStaticScope$kotlin_reflection().getContributedVariables(name0, NoLookupLocation.FROM_REFLECTION));
    }

    @Override  // kotlin.reflect.KClass
    public String getQualifiedName() {
        return ((Data)this.data.invoke()).getQualifiedName();
    }

    @Override  // kotlin.reflect.KClass
    public List getSealedSubclasses() {
        return ((Data)this.data.invoke()).getSealedSubclasses();
    }

    @Override  // kotlin.reflect.KClass
    public String getSimpleName() {
        return ((Data)this.data.invoke()).getSimpleName();
    }

    public final MemberScope getStaticScope$kotlin_reflection() {
        MemberScope memberScope0 = this.getDescriptor().getStaticScope();
        Intrinsics.checkNotNullExpressionValue(memberScope0, "descriptor.staticScope");
        return memberScope0;
    }

    @Override  // kotlin.reflect.KClass
    public List getSupertypes() {
        return ((Data)this.data.invoke()).getSupertypes();
    }

    @Override  // kotlin.reflect.KClass
    public List getTypeParameters() {
        return ((Data)this.data.invoke()).getTypeParameters();
    }

    @Override  // kotlin.reflect.KClass
    public KVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = this.getDescriptor().getVisibility();
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "descriptor.visibility");
        return UtilKt.toKVisibility(descriptorVisibility0);
    }

    @Override  // kotlin.reflect.KClass
    public int hashCode() {
        return JvmClassMappingKt.getJavaObjectType(this).hashCode();
    }

    @Override  // kotlin.reflect.KClass
    public boolean isAbstract() {
        return this.getDescriptor().getModality() == Modality.ABSTRACT;
    }

    @Override  // kotlin.reflect.KClass
    public boolean isCompanion() {
        return this.getDescriptor().isCompanionObject();
    }

    @Override  // kotlin.reflect.KClass
    public boolean isData() {
        return this.getDescriptor().isData();
    }

    @Override  // kotlin.reflect.KClass
    public boolean isFinal() {
        return this.getDescriptor().getModality() == Modality.FINAL;
    }

    @Override  // kotlin.reflect.KClass
    public boolean isFun() {
        return this.getDescriptor().isFun();
    }

    @Override  // kotlin.reflect.KClass
    public boolean isInner() {
        return this.getDescriptor().isInner();
    }

    @Override  // kotlin.reflect.KClass
    public boolean isInstance(Object object0) {
        Integer integer0 = ReflectClassUtilKt.getFunctionClassArity(this.getJClass());
        if(integer0 != null) {
            return TypeIntrinsics.isFunctionOfArity(object0, integer0.intValue());
        }
        Class class0 = ReflectClassUtilKt.getWrapperByPrimitive(this.getJClass());
        if(class0 == null) {
            class0 = this.getJClass();
        }
        return class0.isInstance(object0);
    }

    @Override  // kotlin.reflect.KClass
    public boolean isOpen() {
        return this.getDescriptor().getModality() == Modality.OPEN;
    }

    @Override  // kotlin.reflect.KClass
    public boolean isSealed() {
        return this.getDescriptor().getModality() == Modality.SEALED;
    }

    @Override  // kotlin.reflect.KClass
    public boolean isValue() {
        return this.getDescriptor().isValue();
    }

    private final Void reportUnresolvedClass() {
        Kind kotlinClassHeader$Kind0;
        ReflectKotlinClass reflectKotlinClass0 = ReflectKotlinClass.Factory.create(this.getJClass());
        if(reflectKotlinClass0 == null) {
            kotlinClassHeader$Kind0 = null;
        }
        else {
            KotlinClassHeader kotlinClassHeader0 = reflectKotlinClass0.getClassHeader();
            kotlinClassHeader$Kind0 = kotlinClassHeader0 == null ? null : kotlinClassHeader0.getKind();
        }
        switch((kotlinClassHeader$Kind0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[kotlinClassHeader$Kind0.ordinal()])) {
            case 1: 
            case 2: 
            case 3: {
                throw new UnsupportedOperationException("Packages and file facades are not yet supported in Kotlin reflection. Meanwhile please use Java reflection to inspect this class: " + this.getJClass());
            }
            case 4: {
                throw new UnsupportedOperationException("This class is an internal synthetic class generated by the Kotlin compiler, such as an anonymous class for a lambda, a SAM wrapper, a callable reference, etc. It\'s not a Kotlin class or interface, so the reflection library has no idea what declarations it has. Please use Java reflection to inspect this class: " + this.getJClass());
            }
            case 5: {
                throw new KotlinReflectionInternalError("Unknown class: " + this.getJClass() + " (kind = " + kotlinClassHeader$Kind0 + ')');
            }
            case -1: 
            case 6: {
                throw new KotlinReflectionInternalError("Unresolved class: " + this.getJClass());
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Override
    public String toString() {
        ClassId classId0 = this.getClassId();
        FqName fqName0 = classId0.getPackageFqName();
        Intrinsics.checkNotNullExpressionValue(fqName0, "classId.packageFqName");
        String s = fqName0.isRoot() ? "" : fqName0.asString() + '.';
        String s1 = classId0.getRelativeClassName().asString();
        Intrinsics.checkNotNullExpressionValue(s1, "classId.relativeClassName.asString()");
        return "class " + (s + StringsKt.replace$default(s1, '.', '$', false, 4, null));
    }
}

