package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.CloneableClassScope;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaClass;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaConstructor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaField;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectJavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Constructor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Method;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0002J\u0012\u0010\u000E\u001A\u00020\u00042\n\u0010\u000F\u001A\u0006\u0012\u0002\b\u00030\u0007J\u0010\u0010\u0010\u001A\u00020\u00112\u0006\u0010\f\u001A\u00020\rH\u0002J\u0010\u0010\u0012\u001A\u00020\u00132\u0006\u0010\f\u001A\u00020\u0014H\u0002J\u000E\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018J\u000E\u0010\u0019\u001A\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\rR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001E\u0010\u0005\u001A\u0004\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u00078BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u001C"}, d2 = {"Lkotlin/reflect/jvm/internal/RuntimeTypeMapper;", "", "()V", "JAVA_LANG_VOID", "Lkotlin/reflect/jvm/internal/impl/name/ClassId;", "primitiveType", "Lkotlin/reflect/jvm/internal/impl/builtins/PrimitiveType;", "Ljava/lang/Class;", "getPrimitiveType", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/builtins/PrimitiveType;", "isKnownBuiltInFunction", "", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "mapJvmClassToKotlinClassId", "klass", "mapJvmFunctionSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "mapName", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "mapPropertySignature", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "possiblyOverriddenProperty", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "mapSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature;", "possiblySubstitutedFunction", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class RuntimeTypeMapper {
    public static final RuntimeTypeMapper INSTANCE;
    private static final ClassId JAVA_LANG_VOID;

    static {
        RuntimeTypeMapper.INSTANCE = new RuntimeTypeMapper();
        ClassId classId0 = ClassId.topLevel(new FqName("java.lang.Void"));
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(FqName(\"java.lang.Void\"))");
        RuntimeTypeMapper.JAVA_LANG_VOID = classId0;
    }

    // 去混淆评级： 低(20)
    private final PrimitiveType getPrimitiveType(Class class0) {
        return class0.isPrimitive() ? JvmPrimitiveType.get(class0.getSimpleName()).getPrimitiveType() : null;
    }

    // 去混淆评级： 低(40)
    private final boolean isKnownBuiltInFunction(FunctionDescriptor functionDescriptor0) {
        return DescriptorFactory.isEnumValueOfMethod(functionDescriptor0) || DescriptorFactory.isEnumValuesMethod(functionDescriptor0) || Intrinsics.areEqual(functionDescriptor0.getName(), CloneableClassScope.Companion.getCLONE_NAME()) && functionDescriptor0.getValueParameters().isEmpty();
    }

    public final ClassId mapJvmClassToKotlinClassId(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "klass");
        if(class0.isArray()) {
            Class class1 = class0.getComponentType();
            Intrinsics.checkNotNullExpressionValue(class1, "klass.componentType");
            PrimitiveType primitiveType0 = this.getPrimitiveType(class1);
            if(primitiveType0 != null) {
                return new ClassId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, primitiveType0.getArrayTypeName());
            }
            ClassId classId0 = ClassId.topLevel(FqNames.array.toSafe());
            Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(StandardNames.FqNames.array.toSafe())");
            return classId0;
        }
        if(Intrinsics.areEqual(class0, Void.TYPE)) {
            return RuntimeTypeMapper.JAVA_LANG_VOID;
        }
        PrimitiveType primitiveType1 = this.getPrimitiveType(class0);
        if(primitiveType1 != null) {
            return new ClassId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, primitiveType1.getTypeName());
        }
        ClassId classId1 = ReflectClassUtilKt.getClassId(class0);
        if(!classId1.isLocal()) {
            FqName fqName0 = classId1.asSingleFqName();
            Intrinsics.checkNotNullExpressionValue(fqName0, "classId.asSingleFqName()");
            ClassId classId2 = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(fqName0);
            return classId2 == null ? classId1 : classId2;
        }
        return classId1;
    }

    private final KotlinFunction mapJvmFunctionSignature(FunctionDescriptor functionDescriptor0) {
        return new KotlinFunction(new Method(this.mapName(functionDescriptor0), MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor0, false, false, 1, null)));
    }

    private final String mapName(CallableMemberDescriptor callableMemberDescriptor0) {
        String s = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(callableMemberDescriptor0);
        if(s == null) {
            if(callableMemberDescriptor0 instanceof PropertyGetterDescriptor) {
                String s1 = DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor0).getName().asString();
                Intrinsics.checkNotNullExpressionValue(s1, "descriptor.propertyIfAccessor.name.asString()");
                return JvmAbi.getterName(s1);
            }
            if(callableMemberDescriptor0 instanceof PropertySetterDescriptor) {
                String s2 = DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor0).getName().asString();
                Intrinsics.checkNotNullExpressionValue(s2, "descriptor.propertyIfAccessor.name.asString()");
                return JvmAbi.setterName(s2);
            }
            String s3 = callableMemberDescriptor0.getName().asString();
            Intrinsics.checkNotNullExpressionValue(s3, "descriptor.name.asString()");
            return s3;
        }
        return s;
    }

    public final JvmPropertySignature mapPropertySignature(PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "possiblyOverriddenProperty");
        PropertyDescriptor propertyDescriptor1 = ((PropertyDescriptor)DescriptorUtils.unwrapFakeOverride(propertyDescriptor0)).getOriginal();
        Intrinsics.checkNotNullExpressionValue(propertyDescriptor1, "unwrapFakeOverride(possi…rriddenProperty).original");
        KotlinFunction jvmFunctionSignature$KotlinFunction0 = null;
        if(propertyDescriptor1 instanceof DeserializedPropertyDescriptor) {
            Property protoBuf$Property0 = ((DeserializedPropertyDescriptor)propertyDescriptor1).getProto();
            Intrinsics.checkNotNullExpressionValue(JvmProtoBuf.propertySignature, "propertySignature");
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature jvmProtoBuf$JvmPropertySignature0 = (kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature)ProtoBufUtilKt.getExtensionOrNull(protoBuf$Property0, JvmProtoBuf.propertySignature);
            if(jvmProtoBuf$JvmPropertySignature0 != null) {
                return new KotlinProperty(propertyDescriptor1, protoBuf$Property0, jvmProtoBuf$JvmPropertySignature0, ((DeserializedPropertyDescriptor)propertyDescriptor1).getNameResolver(), ((DeserializedPropertyDescriptor)propertyDescriptor1).getTypeTable());
            }
        }
        else if(propertyDescriptor1 instanceof JavaPropertyDescriptor) {
            SourceElement sourceElement0 = ((JavaPropertyDescriptor)propertyDescriptor1).getSource();
            JavaSourceElement javaSourceElement0 = sourceElement0 instanceof JavaSourceElement ? ((JavaSourceElement)sourceElement0) : null;
            JavaElement javaElement0 = javaSourceElement0 == null ? null : javaSourceElement0.getJavaElement();
            if(javaElement0 instanceof ReflectJavaField) {
                return new JavaField(((ReflectJavaField)javaElement0).getMember());
            }
            if(!(javaElement0 instanceof ReflectJavaMethod)) {
                throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java field " + propertyDescriptor1 + " (source = " + javaElement0 + ')');
            }
            java.lang.reflect.Method method0 = ((ReflectJavaMethod)javaElement0).getMember();
            PropertySetterDescriptor propertySetterDescriptor0 = propertyDescriptor1.getSetter();
            SourceElement sourceElement1 = propertySetterDescriptor0 == null ? null : propertySetterDescriptor0.getSource();
            JavaSourceElement javaSourceElement1 = sourceElement1 instanceof JavaSourceElement ? ((JavaSourceElement)sourceElement1) : null;
            JavaElement javaElement1 = javaSourceElement1 == null ? null : javaSourceElement1.getJavaElement();
            ReflectJavaMethod reflectJavaMethod0 = javaElement1 instanceof ReflectJavaMethod ? ((ReflectJavaMethod)javaElement1) : null;
            if(reflectJavaMethod0 != null) {
                jvmFunctionSignature$KotlinFunction0 = reflectJavaMethod0.getMember();
            }
            return new JavaMethodProperty(method0, ((java.lang.reflect.Method)jvmFunctionSignature$KotlinFunction0));
        }
        PropertyGetterDescriptor propertyGetterDescriptor0 = propertyDescriptor1.getGetter();
        Intrinsics.checkNotNull(propertyGetterDescriptor0);
        KotlinFunction jvmFunctionSignature$KotlinFunction1 = this.mapJvmFunctionSignature(propertyGetterDescriptor0);
        PropertySetterDescriptor propertySetterDescriptor1 = propertyDescriptor1.getSetter();
        if(propertySetterDescriptor1 != null) {
            jvmFunctionSignature$KotlinFunction0 = this.mapJvmFunctionSignature(propertySetterDescriptor1);
        }
        return new MappedKotlinProperty(jvmFunctionSignature$KotlinFunction1, jvmFunctionSignature$KotlinFunction0);
    }

    public final JvmFunctionSignature mapSignature(FunctionDescriptor functionDescriptor0) {
        JavaElement javaElement0 = null;
        Intrinsics.checkNotNullParameter(functionDescriptor0, "possiblySubstitutedFunction");
        FunctionDescriptor functionDescriptor1 = ((FunctionDescriptor)DescriptorUtils.unwrapFakeOverride(functionDescriptor0)).getOriginal();
        Intrinsics.checkNotNullExpressionValue(functionDescriptor1, "unwrapFakeOverride(possi…titutedFunction).original");
        if(functionDescriptor1 instanceof DeserializedCallableMemberDescriptor) {
            MessageLite messageLite0 = ((DeserializedCallableMemberDescriptor)functionDescriptor1).getProto();
            if(messageLite0 instanceof Function) {
                NameResolver nameResolver0 = ((DeserializedCallableMemberDescriptor)functionDescriptor1).getNameResolver();
                TypeTable typeTable0 = ((DeserializedCallableMemberDescriptor)functionDescriptor1).getTypeTable();
                Method jvmMemberSignature$Method0 = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature(((Function)messageLite0), nameResolver0, typeTable0);
                if(jvmMemberSignature$Method0 != null) {
                    return new KotlinFunction(jvmMemberSignature$Method0);
                }
            }
            if(messageLite0 instanceof Constructor) {
                NameResolver nameResolver1 = ((DeserializedCallableMemberDescriptor)functionDescriptor1).getNameResolver();
                TypeTable typeTable1 = ((DeserializedCallableMemberDescriptor)functionDescriptor1).getTypeTable();
                Method jvmMemberSignature$Method1 = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature(((Constructor)messageLite0), nameResolver1, typeTable1);
                if(jvmMemberSignature$Method1 != null) {
                    DeclarationDescriptor declarationDescriptor0 = functionDescriptor0.getContainingDeclaration();
                    Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "possiblySubstitutedFunction.containingDeclaration");
                    return InlineClassesUtilsKt.isInlineClass(declarationDescriptor0) ? new KotlinFunction(jvmMemberSignature$Method1) : new KotlinConstructor(jvmMemberSignature$Method1);
                }
            }
            return this.mapJvmFunctionSignature(functionDescriptor1);
        }
        if(functionDescriptor1 instanceof JavaMethodDescriptor) {
            SourceElement sourceElement0 = ((JavaMethodDescriptor)functionDescriptor1).getSource();
            JavaSourceElement javaSourceElement0 = sourceElement0 instanceof JavaSourceElement ? ((JavaSourceElement)sourceElement0) : null;
            JavaElement javaElement1 = javaSourceElement0 == null ? null : javaSourceElement0.getJavaElement();
            if(javaElement1 instanceof ReflectJavaMethod) {
                javaElement0 = (ReflectJavaMethod)javaElement1;
            }
            if(javaElement0 != null) {
                java.lang.reflect.Method method0 = ((ReflectJavaMethod)javaElement0).getMember();
                if(method0 != null) {
                    return new JavaMethod(method0);
                }
            }
            throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java method " + functionDescriptor1);
        }
        if(functionDescriptor1 instanceof JavaClassConstructorDescriptor) {
            SourceElement sourceElement1 = ((JavaClassConstructorDescriptor)functionDescriptor1).getSource();
            JavaSourceElement javaSourceElement1 = sourceElement1 instanceof JavaSourceElement ? ((JavaSourceElement)sourceElement1) : null;
            if(javaSourceElement1 != null) {
                javaElement0 = javaSourceElement1.getJavaElement();
            }
            if(javaElement0 instanceof ReflectJavaConstructor) {
                return new JavaConstructor(((ReflectJavaConstructor)javaElement0).getMember());
            }
            if(!(javaElement0 instanceof ReflectJavaClass) || !((ReflectJavaClass)javaElement0).isAnnotationType()) {
                throw new KotlinReflectionInternalError("Incorrect resolution sequence for Java constructor " + functionDescriptor1 + " (" + javaElement0 + ')');
            }
            return new FakeJavaAnnotationConstructor(((ReflectJavaClass)javaElement0).getElement());
        }
        if(!this.isKnownBuiltInFunction(functionDescriptor1)) {
            throw new KotlinReflectionInternalError("Unknown origin of " + functionDescriptor1 + " (" + functionDescriptor1.getClass() + ')');
        }
        return this.mapJvmFunctionSignature(functionDescriptor1);
    }
}

