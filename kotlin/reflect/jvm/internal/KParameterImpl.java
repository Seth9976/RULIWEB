package kotlin.reflect.jvm.internal;

import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001B\n\u0002\b\r\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B/\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000BJ\u0013\u0010)\u001A\u00020\u001C2\b\u0010*\u001A\u0004\u0018\u00010+H\u0096\u0002J\b\u0010,\u001A\u00020\u0005H\u0016J\b\u0010-\u001A\u00020\"H\u0016R!\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\r8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001A\u0004\b\u000F\u0010\u0010R\u0015\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u001B\u0010\u0015\u001A\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0012\u001A\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001AR\u0014\u0010\u001B\u001A\u00020\u001C8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001B\u0010\u001DR\u0014\u0010\u001E\u001A\u00020\u001C8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001E\u0010\u001DR\u0014\u0010\u0006\u001A\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 R\u0016\u0010!\u001A\u0004\u0018\u00010\"8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b#\u0010$R\u0014\u0010%\u001A\u00020&8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\'\u0010(¨\u0006."}, d2 = {"Lkotlin/reflect/jvm/internal/KParameterImpl;", "Lkotlin/reflect/KParameter;", "callable", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "index", "", "kind", "Lkotlin/reflect/KParameter$Kind;", "computeDescriptor", "Lkotlin/Function0;", "Lkotlin/reflect/jvm/internal/impl/descriptors/ParameterDescriptor;", "(Lkotlin/reflect/jvm/internal/KCallableImpl;ILkotlin/reflect/KParameter$Kind;Lkotlin/jvm/functions/Function0;)V", "annotations", "", "", "getAnnotations", "()Ljava/util/List;", "annotations$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "getCallable", "()Lkotlin/reflect/jvm/internal/KCallableImpl;", "descriptor", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;", "descriptor$delegate", "getIndex", "()I", "isOptional", "", "()Z", "isVararg", "getKind", "()Lkotlin/reflect/KParameter$Kind;", "name", "", "getName", "()Ljava/lang/String;", "type", "Lkotlin/reflect/KType;", "getType", "()Lkotlin/reflect/KType;", "equals", "other", "", "hashCode", "toString", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KParameterImpl implements KParameter {
    static final KProperty[] $$delegatedProperties;
    private final LazySoftVal annotations$delegate;
    private final KCallableImpl callable;
    private final LazySoftVal descriptor$delegate;
    private final int index;
    private final Kind kind;

    static {
        KParameterImpl.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KParameterImpl.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KParameterImpl.class), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    public KParameterImpl(KCallableImpl kCallableImpl0, int v, Kind kParameter$Kind0, Function0 function00) {
        Intrinsics.checkNotNullParameter(kCallableImpl0, "callable");
        Intrinsics.checkNotNullParameter(kParameter$Kind0, "kind");
        Intrinsics.checkNotNullParameter(function00, "computeDescriptor");
        super();
        this.callable = kCallableImpl0;
        this.index = v;
        this.kind = kParameter$Kind0;
        this.descriptor$delegate = ReflectProperties.lazySoft(function00);
        this.annotations$delegate = ReflectProperties.lazySoft(new Function0() {
            {
                KParameterImpl.this = kParameterImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return UtilKt.computeAnnotations(KParameterImpl.access$getDescriptor(KParameterImpl.this));
            }
        });
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof KParameterImpl && Intrinsics.areEqual(this.callable, ((KParameterImpl)object0).callable) && this.getIndex() == ((KParameterImpl)object0).getIndex();
    }

    @Override  // kotlin.reflect.KAnnotatedElement
    public List getAnnotations() {
        Object object0 = this.annotations$delegate.getValue(this, KParameterImpl.$$delegatedProperties[1]);
        Intrinsics.checkNotNullExpressionValue(object0, "<get-annotations>(...)");
        return (List)object0;
    }

    public final KCallableImpl getCallable() {
        return this.callable;
    }

    private final ParameterDescriptor getDescriptor() {
        Object object0 = this.descriptor$delegate.getValue(this, KParameterImpl.$$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(object0, "<get-descriptor>(...)");
        return (ParameterDescriptor)object0;
    }

    @Override  // kotlin.reflect.KParameter
    public int getIndex() {
        return this.index;
    }

    @Override  // kotlin.reflect.KParameter
    public Kind getKind() {
        return this.kind;
    }

    @Override  // kotlin.reflect.KParameter
    public String getName() {
        ParameterDescriptor parameterDescriptor0 = this.getDescriptor();
        ValueParameterDescriptor valueParameterDescriptor0 = parameterDescriptor0 instanceof ValueParameterDescriptor ? ((ValueParameterDescriptor)parameterDescriptor0) : null;
        if(valueParameterDescriptor0 == null) {
            return null;
        }
        if(valueParameterDescriptor0.getContainingDeclaration().hasSynthesizedParameterNames()) {
            return null;
        }
        Name name0 = valueParameterDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "valueParameter.name");
        return name0.isSpecial() ? null : name0.asString();
    }

    @Override  // kotlin.reflect.KParameter
    public KType getType() {
        KotlinType kotlinType0 = this.getDescriptor().getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "descriptor.type");
        return new KTypeImpl(kotlinType0, new Function0() {
            {
                KParameterImpl.this = kParameterImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Type invoke() {
                ParameterDescriptor parameterDescriptor0 = KParameterImpl.this.getDescriptor();
                if(parameterDescriptor0 instanceof ReceiverParameterDescriptor && Intrinsics.areEqual(UtilKt.getInstanceReceiverParameter(KParameterImpl.this.getCallable().getDescriptor()), parameterDescriptor0) && KParameterImpl.this.getCallable().getDescriptor().getKind() == kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
                    DeclarationDescriptor declarationDescriptor0 = KParameterImpl.this.getCallable().getDescriptor().getContainingDeclaration();
                    Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    Class class0 = UtilKt.toJavaClass(((ClassDescriptor)declarationDescriptor0));
                    if(class0 == null) {
                        throw new KotlinReflectionInternalError("Cannot determine receiver Java type of inherited declaration: " + parameterDescriptor0);
                    }
                    return class0;
                }
                return (Type)KParameterImpl.this.getCallable().getCaller().getParameterTypes().get(KParameterImpl.this.getIndex());
            }
        });
    }

    @Override
    public int hashCode() {
        return this.callable.hashCode() * 0x1F + this.getIndex();
    }

    @Override  // kotlin.reflect.KParameter
    public boolean isOptional() {
        ParameterDescriptor parameterDescriptor0 = this.getDescriptor();
        ValueParameterDescriptor valueParameterDescriptor0 = parameterDescriptor0 instanceof ValueParameterDescriptor ? ((ValueParameterDescriptor)parameterDescriptor0) : null;
        return valueParameterDescriptor0 == null ? false : DescriptorUtilsKt.declaresOrInheritsDefaultValue(valueParameterDescriptor0);
    }

    @Override  // kotlin.reflect.KParameter
    public boolean isVararg() {
        ParameterDescriptor parameterDescriptor0 = this.getDescriptor();
        return parameterDescriptor0 instanceof ValueParameterDescriptor && ((ValueParameterDescriptor)parameterDescriptor0).getVarargElementType() != null;
    }

    @Override
    public String toString() {
        return ReflectionObjectRenderer.INSTANCE.renderParameter(this);
    }
}

