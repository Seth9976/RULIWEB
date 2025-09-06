package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.NameUtils;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite.ExtendableMessage;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0004\u0005\u0006\u0007\bB\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H&\u0082\u0001\u0004\t\n\u000B\f¨\u0006\r"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "", "()V", "asString", "", "JavaField", "JavaMethodProperty", "KotlinProperty", "MappedKotlinProperty", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaField;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaMethodProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$KotlinProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature$MappedKotlinProperty;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class JvmPropertySignature {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001A\u00020\bH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaField;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "field", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)V", "getField", "()Ljava/lang/reflect/Field;", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class JavaField extends JvmPropertySignature {
        private final Field field;

        public JavaField(Field field0) {
            Intrinsics.checkNotNullParameter(field0, "field");
            super(null);
            this.field = field0;
        }

        @Override  // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            String s = this.field.getName();
            Intrinsics.checkNotNullExpressionValue(s, "field.name");
            Class class0 = this.field.getType();
            Intrinsics.checkNotNullExpressionValue(class0, "field.type");
            return JvmAbi.getterName(s) + "()" + ReflectClassUtilKt.getDesc(class0);
        }

        public final Field getField() {
            return this.field;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001A\u00020\nH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0007¨\u0006\u000B"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$JavaMethodProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "getterMethod", "Ljava/lang/reflect/Method;", "setterMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "getGetterMethod", "()Ljava/lang/reflect/Method;", "getSetterMethod", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class JavaMethodProperty extends JvmPropertySignature {
        private final Method getterMethod;
        private final Method setterMethod;

        public JavaMethodProperty(Method method0, Method method1) {
            Intrinsics.checkNotNullParameter(method0, "getterMethod");
            super(null);
            this.getterMethod = method0;
            this.setterMethod = method1;
        }

        @Override  // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            return RuntimeTypeMapperKt.access$getSignature(this.getterMethod);
        }

        public final Method getGetterMethod() {
            return this.getterMethod;
        }

        public final Method getSetterMethod() {
            return this.setterMethod;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000E\n\u0002\b\u0005\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\b\u0010\u0019\u001A\u00020\u0016H\u0016J\b\u0010\u001A\u001A\u00020\u0016H\u0002R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u000E\u0010\u0015\u001A\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001A\u00020\u000B¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018¨\u0006\u001B"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$KotlinProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "proto", "Lkotlin/reflect/jvm/internal/impl/metadata/ProtoBuf$Property;", "signature", "Lkotlin/reflect/jvm/internal/impl/metadata/jvm/JvmProtoBuf$JvmPropertySignature;", "nameResolver", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/NameResolver;", "typeTable", "Lkotlin/reflect/jvm/internal/impl/metadata/deserialization/TypeTable;", "(Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;Lorg/jetbrains/kotlin/metadata/jvm/JvmProtoBuf$JvmPropertySignature;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getNameResolver", "()Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "getProto", "()Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "getSignature", "()Lorg/jetbrains/kotlin/metadata/jvm/JvmProtoBuf$JvmPropertySignature;", "string", "", "getTypeTable", "()Lorg/jetbrains/kotlin/metadata/deserialization/TypeTable;", "asString", "getManglingSuffix", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class KotlinProperty extends JvmPropertySignature {
        private final PropertyDescriptor descriptor;
        private final NameResolver nameResolver;
        private final Property proto;
        private final kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature signature;
        private final String string;
        private final TypeTable typeTable;

        public KotlinProperty(PropertyDescriptor propertyDescriptor0, Property protoBuf$Property0, kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature jvmProtoBuf$JvmPropertySignature0, NameResolver nameResolver0, TypeTable typeTable0) {
            Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
            String s;
            Intrinsics.checkNotNullParameter(protoBuf$Property0, "proto");
            Intrinsics.checkNotNullParameter(jvmProtoBuf$JvmPropertySignature0, "signature");
            Intrinsics.checkNotNullParameter(nameResolver0, "nameResolver");
            Intrinsics.checkNotNullParameter(typeTable0, "typeTable");
            super(null);
            this.descriptor = propertyDescriptor0;
            this.proto = protoBuf$Property0;
            this.signature = jvmProtoBuf$JvmPropertySignature0;
            this.nameResolver = nameResolver0;
            this.typeTable = typeTable0;
            if(jvmProtoBuf$JvmPropertySignature0.hasGetter()) {
                s = nameResolver0.getString(jvmProtoBuf$JvmPropertySignature0.getGetter().getName()) + nameResolver0.getString(jvmProtoBuf$JvmPropertySignature0.getGetter().getDesc());
            }
            else {
                kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field jvmMemberSignature$Field0 = JvmProtoBufUtil.getJvmFieldSignature$default(JvmProtoBufUtil.INSTANCE, protoBuf$Property0, nameResolver0, typeTable0, false, 8, null);
                if(jvmMemberSignature$Field0 == null) {
                    throw new KotlinReflectionInternalError("No field signature for property: " + propertyDescriptor0);
                }
                s = JvmAbi.getterName(jvmMemberSignature$Field0.component1()) + this.getManglingSuffix() + "()" + jvmMemberSignature$Field0.component2();
            }
            this.string = s;
        }

        @Override  // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            return this.string;
        }

        public final PropertyDescriptor getDescriptor() {
            return this.descriptor;
        }

        private final String getManglingSuffix() {
            DeclarationDescriptor declarationDescriptor0 = this.descriptor.getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "descriptor.containingDeclaration");
            if(Intrinsics.areEqual(this.descriptor.getVisibility(), DescriptorVisibilities.INTERNAL) && declarationDescriptor0 instanceof DeserializedClassDescriptor) {
                ExtendableMessage generatedMessageLite$ExtendableMessage0 = ((DeserializedClassDescriptor)declarationDescriptor0).getClassProto();
                Intrinsics.checkNotNullExpressionValue(JvmProtoBuf.classModuleName, "classModuleName");
                Integer integer0 = (Integer)ProtoBufUtilKt.getExtensionOrNull(generatedMessageLite$ExtendableMessage0, JvmProtoBuf.classModuleName);
                if(integer0 != null) {
                    String s = this.nameResolver.getString(integer0.intValue());
                    return s == null ? "$" + NameUtils.sanitizeAsJavaIdentifier("main") : "$" + NameUtils.sanitizeAsJavaIdentifier(s);
                }
                return "$" + NameUtils.sanitizeAsJavaIdentifier("main");
            }
            if(Intrinsics.areEqual(this.descriptor.getVisibility(), DescriptorVisibilities.PRIVATE) && declarationDescriptor0 instanceof PackageFragmentDescriptor) {
                Intrinsics.checkNotNull(this.descriptor, "null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedPropertyDescriptor");
                DeserializedContainerSource deserializedContainerSource0 = ((DeserializedPropertyDescriptor)this.descriptor).getContainerSource();
                return !(deserializedContainerSource0 instanceof JvmPackagePartSource) || ((JvmPackagePartSource)deserializedContainerSource0).getFacadeClassName() == null ? "" : "$" + ((JvmPackagePartSource)deserializedContainerSource0).getSimpleName().asString();
            }
            return "";
        }

        public final NameResolver getNameResolver() {
            return this.nameResolver;
        }

        public final Property getProto() {
            return this.proto;
        }

        public final kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature getSignature() {
            return this.signature;
        }

        public final TypeTable getTypeTable() {
            return this.typeTable;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001A\u00020\nH\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0007¨\u0006\u000B"}, d2 = {"Lkotlin/reflect/jvm/internal/JvmPropertySignature$MappedKotlinProperty;", "Lkotlin/reflect/jvm/internal/JvmPropertySignature;", "getterSignature", "Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "setterSignature", "(Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;)V", "getGetterSignature", "()Lkotlin/reflect/jvm/internal/JvmFunctionSignature$KotlinFunction;", "getSetterSignature", "asString", "", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class MappedKotlinProperty extends JvmPropertySignature {
        private final KotlinFunction getterSignature;
        private final KotlinFunction setterSignature;

        public MappedKotlinProperty(KotlinFunction jvmFunctionSignature$KotlinFunction0, KotlinFunction jvmFunctionSignature$KotlinFunction1) {
            Intrinsics.checkNotNullParameter(jvmFunctionSignature$KotlinFunction0, "getterSignature");
            super(null);
            this.getterSignature = jvmFunctionSignature$KotlinFunction0;
            this.setterSignature = jvmFunctionSignature$KotlinFunction1;
        }

        @Override  // kotlin.reflect.jvm.internal.JvmPropertySignature
        public String asString() {
            return this.getterSignature.asString();
        }

        public final KotlinFunction getGetterSignature() {
            return this.getterSignature;
        }

        public final KotlinFunction getSetterSignature() {
            return this.setterSignature;
        }
    }

    private JvmPropertySignature() {
    }

    public JvmPropertySignature(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public abstract String asString();
}

