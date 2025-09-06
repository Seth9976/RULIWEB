package kotlin.reflect.jvm.internal;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlin.reflect.KProperty;
import kotlin.reflect.full.IllegalPropertyDelegateAccessException;
import kotlin.reflect.jvm.KCallablesJvm;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.load.java.DescriptorsJvmAbiUtil;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf.JvmMethodSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\b \u0018\u0000 @*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0004?@ABB)\b\u0016\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\u0007\u0012\b\u0010\t\u001A\u0004\u0018\u00010\n\u00A2\u0006\u0002\u0010\u000BB\u0017\b\u0016\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\f\u001A\u00020\r\u00A2\u0006\u0002\u0010\u000EB3\b\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\u0007\u0012\b\u0010\u000F\u001A\u0004\u0018\u00010\r\u0012\b\u0010\u0010\u001A\u0004\u0018\u00010\n\u00A2\u0006\u0002\u0010\u0011J\n\u00104\u001A\u0004\u0018\u000105H\u0004J\u0013\u00106\u001A\u00020)2\b\u00107\u001A\u0004\u0018\u00010\nH\u0096\u0002J(\u00108\u001A\u0004\u0018\u00010\n2\b\u00109\u001A\u0004\u0018\u0001052\b\u0010:\u001A\u0004\u0018\u00010\n2\b\u0010;\u001A\u0004\u0018\u00010\nH\u0004J\b\u0010<\u001A\u00020=H\u0016J\b\u0010>\u001A\u00020\u0007H\u0016R\u001C\u0010\u0012\u001A\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\r0\r0\u0013X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u0016X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0013\u0010\t\u001A\u0004\u0018\u00010\n8F\u00A2\u0006\u0006\u001A\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001A\u001A\u0006\u0012\u0002\b\u00030\u001B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u001DR\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001E\u0010\u001FR\u001A\u0010 \u001A\b\u0012\u0002\b\u0003\u0018\u00010\u001B8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b!\u0010\u001DR\u0014\u0010\f\u001A\u00020\r8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\"\u0010#R\u0018\u0010$\u001A\b\u0012\u0004\u0012\u00028\u00000%X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b&\u0010\'R\u0014\u0010(\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b(\u0010*R\u0014\u0010+\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b+\u0010*R\u0014\u0010,\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b,\u0010*R\u0014\u0010-\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b-\u0010*R\u0013\u0010.\u001A\u0004\u0018\u00010\u00178F\u00A2\u0006\u0006\u001A\u0004\b/\u00100R\u0014\u0010\u0006\u001A\u00020\u0007X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b1\u00102R\u0010\u0010\u0010\u001A\u0004\u0018\u00010\nX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\b\u001A\u00020\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b3\u00102\u00A8\u0006C"}, d2 = {"Lkotlin/reflect/jvm/internal/KPropertyImpl;", "V", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "Lkotlin/reflect/KProperty;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "boundReceiver", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;)V", "descriptorInitialValue", "rawBoundReceiver", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;Ljava/lang/Object;)V", "_descriptor", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "kotlin.jvm.PlatformType", "_javaField", "Lkotlin/Lazy;", "Ljava/lang/reflect/Field;", "getBoundReceiver", "()Ljava/lang/Object;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "getDefaultCaller", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyDescriptor;", "getter", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Getter;", "getGetter", "()Lkotlin/reflect/jvm/internal/KPropertyImpl$Getter;", "isBound", "", "()Z", "isConst", "isLateinit", "isSuspend", "javaField", "getJavaField", "()Ljava/lang/reflect/Field;", "getName", "()Ljava/lang/String;", "getSignature", "computeDelegateSource", "Ljava/lang/reflect/Member;", "equals", "other", "getDelegateImpl", "fieldOrMethod", "receiver1", "receiver2", "hashCode", "", "toString", "Accessor", "Companion", "Getter", "Setter", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class KPropertyImpl extends KCallableImpl implements KProperty {
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u0001*\u0006\b\u0002\u0010\u0002 \u00012\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0007\u001A\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\nR\u001A\u0010\u000B\u001A\b\u0012\u0002\b\u0003\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u0012\u0010\u000F\u001A\u00020\u0010X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001A\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0015R\u0014\u0010\u0016\u001A\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0016\u0010\u0015R\u0014\u0010\u0017\u001A\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0017\u0010\u0015R\u0014\u0010\u0018\u001A\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0018\u0010\u0015R\u0014\u0010\u0019\u001A\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0019\u0010\u0015R\u0014\u0010\u001A\u001A\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001A\u0010\u0015R\u0018\u0010\u001B\u001A\b\u0012\u0004\u0012\u00028\u00010\u001CX¦\u0004¢\u0006\u0006\u001A\u0004\b\u001D\u0010\u001E¨\u0006\u001F"}, d2 = {"Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "PropertyType", "ReturnType", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "Lkotlin/reflect/KProperty$Accessor;", "Lkotlin/reflect/KFunction;", "()V", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getDefaultCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyAccessorDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyAccessorDescriptor;", "isBound", "", "()Z", "isExternal", "isInfix", "isInline", "isOperator", "isSuspend", "property", "Lkotlin/reflect/jvm/internal/KPropertyImpl;", "getProperty", "()Lkotlin/reflect/jvm/internal/KPropertyImpl;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Accessor extends KCallableImpl implements KFunction, kotlin.reflect.KProperty.Accessor {
        @Override  // kotlin.reflect.jvm.internal.KCallableImpl
        public KDeclarationContainerImpl getContainer() {
            return this.getProperty().getContainer();
        }

        @Override  // kotlin.reflect.jvm.internal.KCallableImpl
        public Caller getDefaultCaller() {
            return null;
        }

        public abstract PropertyAccessorDescriptor getDescriptor();

        public abstract KPropertyImpl getProperty();

        @Override  // kotlin.reflect.jvm.internal.KCallableImpl
        public boolean isBound() {
            return this.getProperty().isBound();
        }

        @Override  // kotlin.reflect.KFunction
        public boolean isExternal() {
            return this.getDescriptor().isExternal();
        }

        @Override  // kotlin.reflect.KFunction
        public boolean isInfix() {
            return this.getDescriptor().isInfix();
        }

        @Override  // kotlin.reflect.KFunction
        public boolean isInline() {
            return this.getDescriptor().isInline();
        }

        @Override  // kotlin.reflect.KFunction
        public boolean isOperator() {
            return this.getDescriptor().isOperator();
        }

        @Override  // kotlin.reflect.KFunction, kotlin.reflect.KCallable
        public boolean isSuspend() {
            return this.getDescriptor().isSuspend();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0001¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/reflect/jvm/internal/KPropertyImpl$Companion;", "", "()V", "EXTENSION_PROPERTY_DELEGATE", "getEXTENSION_PROPERTY_DELEGATE", "()Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Object getEXTENSION_PROPERTY_DELEGATE() {
            return KPropertyImpl.EXTENSION_PROPERTY_DELEGATE;
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0015\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001A\u00020\u001AH\u0016J\b\u0010\u001B\u001A\u00020\u0012H\u0016R\u001F\u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u00068VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001A\u0004\b\u0007\u0010\bR\u001B\u0010\u000B\u001A\u00020\f8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000F\u0010\u0010\u001A\u0004\b\r\u0010\u000ER\u0014\u0010\u0011\u001A\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014¨\u0006\u001C"}, d2 = {"Lkotlin/reflect/jvm/internal/KPropertyImpl$Getter;", "V", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "Lkotlin/reflect/KProperty$Getter;", "()V", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyGetterDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "toString", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Getter extends Accessor implements kotlin.reflect.KProperty.Getter {
        static final KProperty[] $$delegatedProperties;
        private final Lazy caller$delegate;
        private final LazySoftVal descriptor$delegate;

        static {
            Getter.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Getter.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;"))};
        }

        public Getter() {
            this.descriptor$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Getter.this = kPropertyImpl$Getter0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final PropertyGetterDescriptor invoke() {
                    PropertyGetterDescriptor propertyGetterDescriptor0 = Getter.this.getProperty().getDescriptor().getGetter();
                    return propertyGetterDescriptor0 == null ? DescriptorFactory.createDefaultGetter(Getter.this.getProperty().getDescriptor(), Annotations.Companion.getEMPTY()) : propertyGetterDescriptor0;
                }
            });
            Function0 function00 = new Function0() {
                {
                    Getter.this = kPropertyImpl$Getter0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Caller invoke() {
                    return KPropertyImplKt.computeCallerForAccessor(Getter.this, true);
                }
            };
            this.caller$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Getter && Intrinsics.areEqual(this.getProperty(), ((Getter)object0).getProperty());
        }

        @Override  // kotlin.reflect.jvm.internal.KCallableImpl
        public Caller getCaller() {
            return (Caller)this.caller$delegate.getValue();
        }

        @Override  // kotlin.reflect.jvm.internal.KCallableImpl
        public CallableMemberDescriptor getDescriptor() {
            return this.getDescriptor();
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public PropertyAccessorDescriptor getDescriptor() {
            return this.getDescriptor();
        }

        public PropertyGetterDescriptor getDescriptor() {
            Object object0 = this.descriptor$delegate.getValue(this, Getter.$$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-descriptor>(...)");
            return (PropertyGetterDescriptor)object0;
        }

        @Override  // kotlin.reflect.KCallable
        public String getName() {
            return "<get-" + this.getProperty().getName() + '>';
        }

        @Override
        public int hashCode() {
            return this.getProperty().hashCode();
        }

        @Override
        public String toString() {
            return "getter of " + this.getProperty();
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0013\u0010\u0016\u001A\u00020\u00172\b\u0010\u0018\u001A\u0004\u0018\u00010\u0019H\u0096\u0002J\b\u0010\u001A\u001A\u00020\u001BH\u0016J\b\u0010\u001C\u001A\u00020\u0013H\u0016R\u001F\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u00078VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000B\u001A\u0004\b\b\u0010\tR\u001B\u0010\f\u001A\u00020\r8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001A\u0004\b\u000E\u0010\u000FR\u0014\u0010\u0012\u001A\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015¨\u0006\u001D"}, d2 = {"Lkotlin/reflect/jvm/internal/KPropertyImpl$Setter;", "V", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "", "Lkotlin/reflect/KMutableProperty$Setter;", "()V", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertySetterDescriptor;", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/PropertySetterDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "name", "", "getName", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "toString", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Setter extends Accessor implements kotlin.reflect.KMutableProperty.Setter {
        static final KProperty[] $$delegatedProperties;
        private final Lazy caller$delegate;
        private final LazySoftVal descriptor$delegate;

        static {
            Setter.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Setter.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertySetterDescriptor;"))};
        }

        public Setter() {
            this.descriptor$delegate = ReflectProperties.lazySoft(new Function0() {
                {
                    Setter.this = kPropertyImpl$Setter0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final PropertySetterDescriptor invoke() {
                    PropertySetterDescriptor propertySetterDescriptor0 = Setter.this.getProperty().getDescriptor().getSetter();
                    return propertySetterDescriptor0 == null ? DescriptorFactory.createDefaultSetter(Setter.this.getProperty().getDescriptor(), Annotations.Companion.getEMPTY(), Annotations.Companion.getEMPTY()) : propertySetterDescriptor0;
                }
            });
            Function0 function00 = new Function0() {
                {
                    Setter.this = kPropertyImpl$Setter0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Caller invoke() {
                    return KPropertyImplKt.access$computeCallerForAccessor(Setter.this, false);
                }
            };
            this.caller$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Setter && Intrinsics.areEqual(this.getProperty(), ((Setter)object0).getProperty());
        }

        @Override  // kotlin.reflect.jvm.internal.KCallableImpl
        public Caller getCaller() {
            return (Caller)this.caller$delegate.getValue();
        }

        @Override  // kotlin.reflect.jvm.internal.KCallableImpl
        public CallableMemberDescriptor getDescriptor() {
            return this.getDescriptor();
        }

        @Override  // kotlin.reflect.jvm.internal.KPropertyImpl$Accessor
        public PropertyAccessorDescriptor getDescriptor() {
            return this.getDescriptor();
        }

        public PropertySetterDescriptor getDescriptor() {
            Object object0 = this.descriptor$delegate.getValue(this, Setter.$$delegatedProperties[0]);
            Intrinsics.checkNotNullExpressionValue(object0, "<get-descriptor>(...)");
            return (PropertySetterDescriptor)object0;
        }

        @Override  // kotlin.reflect.KCallable
        public String getName() {
            return "<set-" + this.getProperty().getName() + '>';
        }

        @Override
        public int hashCode() {
            return this.getProperty().hashCode();
        }

        @Override
        public String toString() {
            return "setter of " + this.getProperty();
        }
    }

    public static final Companion Companion;
    private static final Object EXTENSION_PROPERTY_DELEGATE;
    private final LazySoftVal _descriptor;
    private final Lazy _javaField;
    private final KDeclarationContainerImpl container;
    private final String name;
    private final Object rawBoundReceiver;
    private final String signature;

    static {
        KPropertyImpl.Companion = new Companion(null);
        KPropertyImpl.EXTENSION_PROPERTY_DELEGATE = new Object();
    }

    public KPropertyImpl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, Object object0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        this(kDeclarationContainerImpl0, s, s1, null, object0);
    }

    private KPropertyImpl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, PropertyDescriptor propertyDescriptor0, Object object0) {
        this.container = kDeclarationContainerImpl0;
        this.name = s;
        this.signature = s1;
        this.rawBoundReceiver = object0;
        Function0 function00 = new Function0() {
            {
                KPropertyImpl.this = kPropertyImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Field invoke() {
                Class class0;
                PropertyDescriptor propertyDescriptor0 = KPropertyImpl.this.getDescriptor();
                JvmPropertySignature jvmPropertySignature0 = RuntimeTypeMapper.INSTANCE.mapPropertySignature(propertyDescriptor0);
                if(jvmPropertySignature0 instanceof KotlinProperty) {
                    PropertyDescriptor propertyDescriptor1 = ((KotlinProperty)jvmPropertySignature0).getDescriptor();
                    Property protoBuf$Property0 = ((KotlinProperty)jvmPropertySignature0).getProto();
                    NameResolver nameResolver0 = ((KotlinProperty)jvmPropertySignature0).getNameResolver();
                    TypeTable typeTable0 = ((KotlinProperty)jvmPropertySignature0).getTypeTable();
                    kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature.Field jvmMemberSignature$Field0 = JvmProtoBufUtil.getJvmFieldSignature$default(JvmProtoBufUtil.INSTANCE, protoBuf$Property0, nameResolver0, typeTable0, false, 8, null);
                    if(jvmMemberSignature$Field0 != null) {
                        KPropertyImpl kPropertyImpl0 = KPropertyImpl.this;
                        if(DescriptorsJvmAbiUtil.isPropertyWithBackingFieldInOuterClass(propertyDescriptor1) || JvmProtoBufUtil.isMovedFromInterfaceCompanion(((KotlinProperty)jvmPropertySignature0).getProto())) {
                            class0 = kPropertyImpl0.getContainer().getJClass().getEnclosingClass();
                        }
                        else {
                            DeclarationDescriptor declarationDescriptor0 = propertyDescriptor1.getContainingDeclaration();
                            class0 = declarationDescriptor0 instanceof ClassDescriptor ? UtilKt.toJavaClass(((ClassDescriptor)declarationDescriptor0)) : kPropertyImpl0.getContainer().getJClass();
                        }
                        if(class0 != null) {
                            try {
                                return class0.getDeclaredField(jvmMemberSignature$Field0.getName());
                            }
                            catch(NoSuchFieldException unused_ex) {
                            }
                        }
                    }
                    return null;
                }
                if(jvmPropertySignature0 instanceof JavaField) {
                    return ((JavaField)jvmPropertySignature0).getField();
                }
                if(jvmPropertySignature0 instanceof JavaMethodProperty) {
                    return null;
                }
                if(!(jvmPropertySignature0 instanceof MappedKotlinProperty)) {
                    throw new NoWhenBranchMatchedException();
                }
                return null;
            }
        };
        this._javaField = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        LazySoftVal reflectProperties$LazySoftVal0 = ReflectProperties.lazySoft(propertyDescriptor0, new Function0() {
            {
                KPropertyImpl.this = kPropertyImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final PropertyDescriptor invoke() {
                return KPropertyImpl.this.getContainer().findPropertyDescriptor(KPropertyImpl.this.getName(), KPropertyImpl.this.getSignature());
            }
        });
        Intrinsics.checkNotNullExpressionValue(reflectProperties$LazySoftVal0, "lazySoft(descriptorIniti…or(name, signature)\n    }");
        this._descriptor = reflectProperties$LazySoftVal0;
    }

    public KPropertyImpl(KDeclarationContainerImpl kDeclarationContainerImpl0, PropertyDescriptor propertyDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(propertyDescriptor0, "descriptor");
        String s = propertyDescriptor0.getName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "descriptor.name.asString()");
        this(kDeclarationContainerImpl0, s, RuntimeTypeMapper.INSTANCE.mapPropertySignature(propertyDescriptor0).asString(), propertyDescriptor0, CallableReference.NO_RECEIVER);
    }

    protected final Member computeDelegateSource() {
        if(!this.getDescriptor().isDelegated()) {
            return null;
        }
        PropertyDescriptor propertyDescriptor0 = this.getDescriptor();
        JvmPropertySignature jvmPropertySignature0 = RuntimeTypeMapper.INSTANCE.mapPropertySignature(propertyDescriptor0);
        if(jvmPropertySignature0 instanceof KotlinProperty && ((KotlinProperty)jvmPropertySignature0).getSignature().hasDelegateMethod()) {
            JvmMethodSignature jvmProtoBuf$JvmMethodSignature0 = ((KotlinProperty)jvmPropertySignature0).getSignature().getDelegateMethod();
            return jvmProtoBuf$JvmMethodSignature0.hasName() && jvmProtoBuf$JvmMethodSignature0.hasDesc() ? this.getContainer().findMethodBySignature(((KotlinProperty)jvmPropertySignature0).getNameResolver().getString(jvmProtoBuf$JvmMethodSignature0.getName()), ((KotlinProperty)jvmPropertySignature0).getNameResolver().getString(jvmProtoBuf$JvmMethodSignature0.getDesc())) : null;
        }
        return this.getJavaField();
    }

    // 去混淆评级： 低(25)
    @Override
    public boolean equals(Object object0) {
        KPropertyImpl kPropertyImpl0 = UtilKt.asKPropertyImpl(object0);
        return kPropertyImpl0 == null ? false : Intrinsics.areEqual(this.getContainer(), kPropertyImpl0.getContainer()) && Intrinsics.areEqual(this.getName(), kPropertyImpl0.getName()) && Intrinsics.areEqual(this.signature, kPropertyImpl0.signature) && Intrinsics.areEqual(this.rawBoundReceiver, kPropertyImpl0.rawBoundReceiver);
    }

    public final Object getBoundReceiver() {
        CallableMemberDescriptor callableMemberDescriptor0 = this.getDescriptor();
        return InlineClassAwareCallerKt.coerceToExpectedReceiverType(this.rawBoundReceiver, callableMemberDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getCaller() {
        return this.getGetter().getCaller();
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public KDeclarationContainerImpl getContainer() {
        return this.container;
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getDefaultCaller() {
        return this.getGetter().getDefaultCaller();
    }

    protected final Object getDelegateImpl(Member member0, Object object0, Object object1) {
        try {
            Object object2 = KPropertyImpl.EXTENSION_PROPERTY_DELEGATE;
            if((object0 == object2 || object1 == object2) && this.getDescriptor().getExtensionReceiverParameter() == null) {
                throw new RuntimeException("\'" + this + "\' is not an extension property and thus getExtensionDelegate() is not going to work, use getDelegate() instead");
            }
            Object object3 = this.isBound() ? this.getBoundReceiver() : object0;
            if(object3 == object2) {
                object3 = null;
            }
            if(!this.isBound()) {
                object0 = object1;
            }
            if(object0 == object2) {
                object0 = null;
            }
            AccessibleObject accessibleObject0 = member0 instanceof AccessibleObject ? ((AccessibleObject)member0) : null;
            if(accessibleObject0 != null) {
                accessibleObject0.setAccessible(KCallablesJvm.isAccessible(this));
            }
            if(member0 == null) {
                return null;
            }
            if(member0 instanceof Field) {
                return ((Field)member0).get(object3);
            }
            if(member0 instanceof Method) {
                switch(((Method)member0).getParameterTypes().length) {
                    case 0: {
                        return ((Method)member0).invoke(null, null);
                    }
                    case 1: {
                        if(object3 == null) {
                            Class class0 = ((Method)member0).getParameterTypes()[0];
                            Intrinsics.checkNotNullExpressionValue(class0, "fieldOrMethod.parameterTypes[0]");
                            object3 = UtilKt.defaultPrimitiveValue(class0);
                        }
                        return ((Method)member0).invoke(null, object3);
                    }
                    case 2: {
                        if(object0 == null) {
                            Class class1 = ((Method)member0).getParameterTypes()[1];
                            Intrinsics.checkNotNullExpressionValue(class1, "fieldOrMethod.parameterTypes[1]");
                            object0 = UtilKt.defaultPrimitiveValue(class1);
                        }
                        return ((Method)member0).invoke(null, object3, object0);
                    }
                    default: {
                        throw new AssertionError("delegate method " + member0 + " should take 0, 1, or 2 parameters");
                    }
                }
            }
            throw new AssertionError("delegate field/method " + member0 + " neither field nor method");
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new IllegalPropertyDelegateAccessException(illegalAccessException0);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public CallableMemberDescriptor getDescriptor() {
        return this.getDescriptor();
    }

    public PropertyDescriptor getDescriptor() {
        Object object0 = this._descriptor.invoke();
        Intrinsics.checkNotNullExpressionValue(object0, "_descriptor()");
        return (PropertyDescriptor)object0;
    }

    public abstract Getter getGetter();

    public final Field getJavaField() {
        return (Field)this._javaField.getValue();
    }

    @Override  // kotlin.reflect.KCallable
    public String getName() {
        return this.name;
    }

    public final String getSignature() {
        return this.signature;
    }

    @Override
    public int hashCode() {
        return (this.getContainer().hashCode() * 0x1F + this.getName().hashCode()) * 0x1F + this.signature.hashCode();
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public boolean isBound() {
        return !Intrinsics.areEqual(this.rawBoundReceiver, CallableReference.NO_RECEIVER);
    }

    @Override  // kotlin.reflect.KProperty
    public boolean isConst() {
        return this.getDescriptor().isConst();
    }

    @Override  // kotlin.reflect.KProperty
    public boolean isLateinit() {
        return this.getDescriptor().isLateInit();
    }

    @Override  // kotlin.reflect.KCallable
    public boolean isSuspend() {
        return false;
    }

    @Override
    public String toString() {
        PropertyDescriptor propertyDescriptor0 = this.getDescriptor();
        return ReflectionObjectRenderer.INSTANCE.renderProperty(propertyDescriptor0);
    }
}

