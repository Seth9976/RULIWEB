package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundInstance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundJvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundStatic;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Instance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.JvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Static;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass.Bound;
import kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass.Unbound;
import kotlin.reflect.jvm.internal.calls.ThrowingCaller;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A \u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u0006*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0007\u001A\u00020\bH\u0002\u001A\f\u0010\t\u001A\u00020\b*\u00020\nH\u0002\"\"\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00028@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u000B"}, d2 = {"boundReceiver", "", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "getBoundReceiver", "(Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;)Ljava/lang/Object;", "computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/calls/Caller;", "isGetter", "", "isJvmFieldPropertyInCompanionObject", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KPropertyImplKt {
    private static final Caller computeCallerForAccessor(Accessor kPropertyImpl$Accessor0, boolean z) {
        KotlinFunction jvmFunctionSignature$KotlinFunction0;
        Method method2;
        JvmMethodSignature jvmProtoBuf$JvmMethodSignature0;
        if(KDeclarationContainerImpl.Companion.getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection().matches(kPropertyImpl$Accessor0.getProperty().getSignature())) {
            return ThrowingCaller.INSTANCE;
        }
        PropertyDescriptor propertyDescriptor0 = kPropertyImpl$Accessor0.getProperty().getDescriptor();
        JvmPropertySignature jvmPropertySignature0 = RuntimeTypeMapper.INSTANCE.mapPropertySignature(propertyDescriptor0);
        if(jvmPropertySignature0 instanceof KotlinProperty) {
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmPropertySignature jvmProtoBuf$JvmPropertySignature0 = ((KotlinProperty)jvmPropertySignature0).getSignature();
            if(!z) {
                jvmProtoBuf$JvmMethodSignature0 = jvmProtoBuf$JvmPropertySignature0.hasSetter() ? jvmProtoBuf$JvmPropertySignature0.getSetter() : null;
            }
            else if(jvmProtoBuf$JvmPropertySignature0.hasGetter()) {
                jvmProtoBuf$JvmMethodSignature0 = jvmProtoBuf$JvmPropertySignature0.getGetter();
            }
            else {
                jvmProtoBuf$JvmMethodSignature0 = null;
            }
            Method method0 = jvmProtoBuf$JvmMethodSignature0 == null ? null : kPropertyImpl$Accessor0.getProperty().getContainer().findMethodBySignature(((KotlinProperty)jvmPropertySignature0).getNameResolver().getString(jvmProtoBuf$JvmMethodSignature0.getName()), ((KotlinProperty)jvmPropertySignature0).getNameResolver().getString(jvmProtoBuf$JvmMethodSignature0.getDesc()));
            if(method0 == null) {
                if(InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(kPropertyImpl$Accessor0.getProperty().getDescriptor()) && Intrinsics.areEqual(kPropertyImpl$Accessor0.getProperty().getDescriptor().getVisibility(), DescriptorVisibilities.INTERNAL)) {
                    Class class0 = InlineClassAwareCallerKt.toInlineClass(kPropertyImpl$Accessor0.getProperty().getDescriptor().getContainingDeclaration());
                    if(class0 != null) {
                        Method method1 = InlineClassAwareCallerKt.getUnboxMethod(class0, kPropertyImpl$Accessor0.getProperty().getDescriptor());
                        if(method1 != null) {
                            return kPropertyImpl$Accessor0.isBound() ? InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new Bound(method1, KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null) : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new Unbound(method1), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
                        }
                    }
                    throw new KotlinReflectionInternalError("Underlying property of inline class " + kPropertyImpl$Accessor0.getProperty() + " should have a field");
                }
                Field field0 = kPropertyImpl$Accessor0.getProperty().getJavaField();
                if(field0 == null) {
                    throw new KotlinReflectionInternalError("No accessors or field is found for property " + kPropertyImpl$Accessor0.getProperty());
                }
                return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(KPropertyImplKt.computeCallerForAccessor$computeFieldCaller(kPropertyImpl$Accessor0, z, field0), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
            }
            if(!Modifier.isStatic(method0.getModifiers())) {
                return kPropertyImpl$Accessor0.isBound() ? InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new BoundInstance(method0, KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null) : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new Instance(method0), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
            }
            if(KPropertyImplKt.computeCallerForAccessor$isJvmStaticProperty(kPropertyImpl$Accessor0)) {
                return kPropertyImpl$Accessor0.isBound() ? InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new BoundJvmStaticInObject(method0), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null) : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new JvmStaticInObject(method0), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
            }
            return kPropertyImpl$Accessor0.isBound() ? InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new BoundStatic(method0, KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null) : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new Static(method0), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
        }
        if(jvmPropertySignature0 instanceof JavaField) {
            return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(KPropertyImplKt.computeCallerForAccessor$computeFieldCaller(kPropertyImpl$Accessor0, z, ((JavaField)jvmPropertySignature0).getField()), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
        }
        if(jvmPropertySignature0 instanceof JavaMethodProperty) {
            if(z) {
                method2 = ((JavaMethodProperty)jvmPropertySignature0).getGetterMethod();
                return kPropertyImpl$Accessor0.isBound() ? InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new BoundInstance(method2, KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null) : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new Instance(method2), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
            }
            method2 = ((JavaMethodProperty)jvmPropertySignature0).getSetterMethod();
            if(method2 == null) {
                throw new KotlinReflectionInternalError("No source found for setter of Java method property: " + ((JavaMethodProperty)jvmPropertySignature0).getGetterMethod());
            }
            return kPropertyImpl$Accessor0.isBound() ? InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new BoundInstance(method2, KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null) : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(new Instance(method2), kPropertyImpl$Accessor0.getDescriptor(), false, 2, null);
        }
        if(!(jvmPropertySignature0 instanceof MappedKotlinProperty)) {
            throw new NoWhenBranchMatchedException();
        }
        if(z) {
            jvmFunctionSignature$KotlinFunction0 = ((MappedKotlinProperty)jvmPropertySignature0).getGetterSignature();
        }
        else {
            jvmFunctionSignature$KotlinFunction0 = ((MappedKotlinProperty)jvmPropertySignature0).getSetterSignature();
            if(jvmFunctionSignature$KotlinFunction0 == null) {
                throw new KotlinReflectionInternalError("No setter found for property " + kPropertyImpl$Accessor0.getProperty());
            }
        }
        Method method3 = kPropertyImpl$Accessor0.getProperty().getContainer().findMethodBySignature(jvmFunctionSignature$KotlinFunction0.getMethodName(), jvmFunctionSignature$KotlinFunction0.getMethodDesc());
        if(method3 == null) {
            throw new KotlinReflectionInternalError("No accessor found for property " + kPropertyImpl$Accessor0.getProperty());
        }
        Modifier.isStatic(method3.getModifiers());
        return kPropertyImpl$Accessor0.isBound() ? new BoundInstance(method3, KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)) : new Instance(method3);
    }

    private static final CallerImpl computeCallerForAccessor$computeFieldCaller(Accessor kPropertyImpl$Accessor0, boolean z, Field field0) {
        if(!KPropertyImplKt.isJvmFieldPropertyInCompanionObject(kPropertyImpl$Accessor0.getProperty().getDescriptor()) && Modifier.isStatic(field0.getModifiers())) {
            if(KPropertyImplKt.computeCallerForAccessor$isJvmStaticProperty(kPropertyImpl$Accessor0)) {
                if(z) {
                    return kPropertyImpl$Accessor0.isBound() ? new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldGetter.BoundJvmStaticInObject(field0) : new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldGetter.JvmStaticInObject(field0);
                }
                return kPropertyImpl$Accessor0.isBound() ? new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter.BoundJvmStaticInObject(field0, KPropertyImplKt.computeCallerForAccessor$isNotNullProperty(kPropertyImpl$Accessor0)) : new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter.JvmStaticInObject(field0, KPropertyImplKt.computeCallerForAccessor$isNotNullProperty(kPropertyImpl$Accessor0));
            }
            return z ? new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldGetter.Static(field0) : new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter.Static(field0, KPropertyImplKt.computeCallerForAccessor$isNotNullProperty(kPropertyImpl$Accessor0));
        }
        if(z) {
            return kPropertyImpl$Accessor0.isBound() ? new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldGetter.BoundInstance(field0, KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)) : new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldGetter.Instance(field0);
        }
        return kPropertyImpl$Accessor0.isBound() ? new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter.BoundInstance(field0, KPropertyImplKt.computeCallerForAccessor$isNotNullProperty(kPropertyImpl$Accessor0), KPropertyImplKt.getBoundReceiver(kPropertyImpl$Accessor0)) : new kotlin.reflect.jvm.internal.calls.CallerImpl.FieldSetter.Instance(field0, KPropertyImplKt.computeCallerForAccessor$isNotNullProperty(kPropertyImpl$Accessor0));
    }

    private static final boolean computeCallerForAccessor$isJvmStaticProperty(Accessor kPropertyImpl$Accessor0) {
        return kPropertyImpl$Accessor0.getProperty().getDescriptor().getAnnotations().hasAnnotation(UtilKt.getJVM_STATIC());
    }

    private static final boolean computeCallerForAccessor$isNotNullProperty(Accessor kPropertyImpl$Accessor0) {
        return !TypeUtils.isNullableType(kPropertyImpl$Accessor0.getProperty().getDescriptor().getType());
    }

    public static final Object getBoundReceiver(Accessor kPropertyImpl$Accessor0) {
        Intrinsics.checkNotNullParameter(kPropertyImpl$Accessor0, "<this>");
        return kPropertyImpl$Accessor0.getProperty().getBoundReceiver();
    }

    private static final boolean isJvmFieldPropertyInCompanionObject(PropertyDescriptor propertyDescriptor0) {
        DeclarationDescriptor declarationDescriptor0 = propertyDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "containingDeclaration");
        if(!DescriptorUtils.isCompanionObject(declarationDescriptor0)) {
            return false;
        }
        DeclarationDescriptor declarationDescriptor1 = declarationDescriptor0.getContainingDeclaration();
        return DescriptorUtils.isInterface(declarationDescriptor1) || DescriptorUtils.isAnnotationClass(declarationDescriptor1) ? propertyDescriptor0 instanceof DeserializedPropertyDescriptor && JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor)propertyDescriptor0).getProto()) : true;
    }
}

