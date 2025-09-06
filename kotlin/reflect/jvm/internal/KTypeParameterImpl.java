package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeParameterReference;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectKotlinClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmPackagePartSource;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedContainerSource;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.Variance;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u001C\u001A\u00020\u000B2\b\u0010\u001D\u001A\u0004\u0018\u00010\u001EH\u0096\u0002J\b\u0010\u001F\u001A\u00020 H\u0016J\b\u0010!\u001A\u00020\u000EH\u0016J\u0010\u0010\"\u001A\u0006\u0012\u0002\b\u00030#*\u00020$H\u0002J\u0010\u0010%\u001A\u0006\u0012\u0002\b\u00030&*\u00020\'H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0014\u0010\n\u001A\u00020\u000B8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\fR\u0014\u0010\r\u001A\u00020\u000E8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R!\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001A\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001A\u00020\u00198VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001A\u0010\u001B¨\u0006("}, d2 = {"Lkotlin/reflect/jvm/internal/KTypeParameterImpl;", "Lkotlin/reflect/KTypeParameter;", "Lkotlin/reflect/jvm/internal/KClassifierImpl;", "container", "Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/TypeParameterDescriptor;", "(Lkotlin/reflect/jvm/internal/KTypeParameterOwnerImpl;Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;)V", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/TypeParameterDescriptor;", "isReified", "", "()Z", "name", "", "getName", "()Ljava/lang/String;", "upperBounds", "", "Lkotlin/reflect/KType;", "getUpperBounds", "()Ljava/util/List;", "upperBounds$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "variance", "Lkotlin/reflect/KVariance;", "getVariance", "()Lkotlin/reflect/KVariance;", "equals", "other", "", "hashCode", "", "toString", "getContainerClass", "Ljava/lang/Class;", "Lkotlin/reflect/jvm/internal/impl/serialization/deserialization/descriptors/DeserializedMemberDescriptor;", "toKClassImpl", "Lkotlin/reflect/jvm/internal/KClassImpl;", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KTypeParameterImpl implements KTypeParameter, KClassifierImpl {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Variance.values().length];
            try {
                arr_v[Variance.INVARIANT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Variance.IN_VARIANCE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Variance.OUT_VARIANCE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    static final KProperty[] $$delegatedProperties;
    private final KTypeParameterOwnerImpl container;
    private final TypeParameterDescriptor descriptor;
    private final LazySoftVal upperBounds$delegate;

    static {
        KTypeParameterImpl.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KTypeParameterImpl.class), "upperBounds", "getUpperBounds()Ljava/util/List;"))};
    }

    public KTypeParameterImpl(KTypeParameterOwnerImpl kTypeParameterOwnerImpl0, TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "descriptor");
        KClassImpl kClassImpl0;
        super();
        this.descriptor = typeParameterDescriptor0;
        this.upperBounds$delegate = ReflectProperties.lazySoft(new Function0() {
            {
                KTypeParameterImpl.this = kTypeParameterImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                List list0 = KTypeParameterImpl.this.getDescriptor().getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(list0, "descriptor.upperBounds");
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    arrayList0.add(new KTypeImpl(((KotlinType)object0), null, 2, null));
                }
                return arrayList0;
            }
        });
        if(kTypeParameterOwnerImpl0 == null) {
            DeclarationDescriptor declarationDescriptor0 = this.getDescriptor().getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "descriptor.containingDeclaration");
            if(declarationDescriptor0 instanceof ClassDescriptor) {
                kClassImpl0 = this.toKClassImpl(((ClassDescriptor)declarationDescriptor0));
            }
            else {
                if(!(declarationDescriptor0 instanceof CallableMemberDescriptor)) {
                    throw new KotlinReflectionInternalError("Unknown type parameter container: " + declarationDescriptor0);
                }
                DeclarationDescriptor declarationDescriptor1 = ((CallableMemberDescriptor)declarationDescriptor0).getContainingDeclaration();
                Intrinsics.checkNotNullExpressionValue(declarationDescriptor1, "declaration.containingDeclaration");
                if(declarationDescriptor1 instanceof ClassDescriptor) {
                    kClassImpl0 = declarationDescriptor0.accept(new CreateKCallableVisitor(this.toKClassImpl(((ClassDescriptor)declarationDescriptor1))), Unit.INSTANCE);
                }
                else {
                    DeserializedMemberDescriptor deserializedMemberDescriptor0 = declarationDescriptor0 instanceof DeserializedMemberDescriptor ? ((DeserializedMemberDescriptor)declarationDescriptor0) : null;
                    if(deserializedMemberDescriptor0 == null) {
                        throw new KotlinReflectionInternalError("Non-class callable descriptor must be deserialized: " + declarationDescriptor0);
                    }
                    KClass kClass0 = JvmClassMappingKt.getKotlinClass(this.getContainerClass(deserializedMemberDescriptor0));
                    Intrinsics.checkNotNull(kClass0, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
                    kClassImpl0 = declarationDescriptor0.accept(new CreateKCallableVisitor(((KClassImpl)kClass0)), Unit.INSTANCE);
                }
            }
            Intrinsics.checkNotNullExpressionValue(kClassImpl0, "when (val declaration = … $declaration\")\n        }");
            kTypeParameterOwnerImpl0 = kClassImpl0;
        }
        this.container = kTypeParameterOwnerImpl0;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof KTypeParameterImpl && Intrinsics.areEqual(this.container, ((KTypeParameterImpl)object0).container) && Intrinsics.areEqual(this.getName(), ((KTypeParameterImpl)object0).getName());
    }

    private final Class getContainerClass(DeserializedMemberDescriptor deserializedMemberDescriptor0) {
        DeserializedContainerSource deserializedContainerSource0 = deserializedMemberDescriptor0.getContainerSource();
        ReflectKotlinClass reflectKotlinClass0 = null;
        JvmPackagePartSource jvmPackagePartSource0 = deserializedContainerSource0 instanceof JvmPackagePartSource ? ((JvmPackagePartSource)deserializedContainerSource0) : null;
        KotlinJvmBinaryClass kotlinJvmBinaryClass0 = jvmPackagePartSource0 == null ? null : jvmPackagePartSource0.getKnownJvmBinaryClass();
        if(kotlinJvmBinaryClass0 instanceof ReflectKotlinClass) {
            reflectKotlinClass0 = (ReflectKotlinClass)kotlinJvmBinaryClass0;
        }
        if(reflectKotlinClass0 != null) {
            Class class0 = reflectKotlinClass0.getKlass();
            if(class0 != null) {
                return class0;
            }
        }
        throw new KotlinReflectionInternalError("Container of deserialized member is not resolved: " + deserializedMemberDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.KClassifierImpl
    public ClassifierDescriptor getDescriptor() {
        return this.getDescriptor();
    }

    public TypeParameterDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override  // kotlin.reflect.KTypeParameter
    public String getName() {
        String s = this.getDescriptor().getName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "descriptor.name.asString()");
        return s;
    }

    @Override  // kotlin.reflect.KTypeParameter
    public List getUpperBounds() {
        Object object0 = this.upperBounds$delegate.getValue(this, KTypeParameterImpl.$$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(object0, "<get-upperBounds>(...)");
        return (List)object0;
    }

    @Override  // kotlin.reflect.KTypeParameter
    public KVariance getVariance() {
        switch(WhenMappings.$EnumSwitchMapping$0[this.getDescriptor().getVariance().ordinal()]) {
            case 1: {
                return KVariance.INVARIANT;
            }
            case 2: {
                return KVariance.IN;
            }
            case 3: {
                return KVariance.OUT;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Override
    public int hashCode() {
        int v = this.getName().hashCode();
        return this.container.hashCode() * 0x1F + v;
    }

    @Override  // kotlin.reflect.KTypeParameter
    public boolean isReified() {
        return this.getDescriptor().isReified();
    }

    private final KClassImpl toKClassImpl(ClassDescriptor classDescriptor0) {
        Class class0 = UtilKt.toJavaClass(classDescriptor0);
        KClass kClass0 = class0 == null ? null : JvmClassMappingKt.getKotlinClass(class0);
        if(((KClassImpl)kClass0) == null) {
            throw new KotlinReflectionInternalError("Type parameter container is not resolved: " + classDescriptor0.getContainingDeclaration());
        }
        return (KClassImpl)kClass0;
    }

    @Override
    public String toString() {
        return TypeParameterReference.Companion.toString(this);
    }
}

