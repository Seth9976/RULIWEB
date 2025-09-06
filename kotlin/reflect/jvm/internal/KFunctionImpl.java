package kotlin.reflect.jvm.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller.CallMode;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller.Origin;
import kotlin.reflect.jvm.internal.calls.AnnotationConstructorCaller;
import kotlin.reflect.jvm.internal.calls.Caller;
import kotlin.reflect.jvm.internal.calls.CallerImpl.AccessorForHiddenBoundConstructor;
import kotlin.reflect.jvm.internal.calls.CallerImpl.AccessorForHiddenConstructor;
import kotlin.reflect.jvm.internal.calls.CallerImpl.BoundConstructor;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundInstance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundJvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.BoundStatic;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Instance;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.JvmStaticInObject;
import kotlin.reflect.jvm.internal.calls.CallerImpl.Method.Static;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.calls.CallerKt;
import kotlin.reflect.jvm.internal.calls.InlineClassAwareCallerKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.InlineClassManglingRulesKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00032\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00042\u00020\u0005B)\b\u0016\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\t\u0012\b\u0010\u000B\u001A\u0004\u0018\u00010\u0002\u00A2\u0006\u0002\u0010\fB\u0017\b\u0016\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\r\u001A\u00020\u000E\u00A2\u0006\u0002\u0010\u000FB5\b\u0002\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\t\u0012\b\u0010\u0010\u001A\u0004\u0018\u00010\u000E\u0012\n\b\u0002\u0010\u0011\u001A\u0004\u0018\u00010\u0002\u00A2\u0006\u0002\u0010\u0012J.\u00102\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u000304032\n\u00105\u001A\u0006\u0012\u0002\b\u0003042\u0006\u0010\r\u001A\u00020\u000E2\u0006\u00106\u001A\u00020)H\u0002J\u0010\u00107\u001A\u0002082\u0006\u00105\u001A\u000209H\u0002J\u0010\u0010:\u001A\u0002082\u0006\u00105\u001A\u000209H\u0002J\u0010\u0010;\u001A\u0002082\u0006\u00105\u001A\u000209H\u0002J\u0013\u0010<\u001A\u00020)2\b\u0010=\u001A\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010>\u001A\u00020\u0014H\u0016J\b\u0010?\u001A\u00020\tH\u0016R\u0014\u0010\u0013\u001A\u00020\u00148VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0015\u0010\u0016R\u0016\u0010\u000B\u001A\u0004\u0018\u00010\u00028BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018R\u001F\u0010\u0019\u001A\u0006\u0012\u0002\b\u00030\u001A8VX\u0096\u0084\u0002\u00A2\u0006\f\n\u0004\b\u001D\u0010\u001E\u001A\u0004\b\u001B\u0010\u001CR\u0014\u0010\u0006\u001A\u00020\u0007X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 R!\u0010!\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u001A8VX\u0096\u0084\u0002\u00A2\u0006\f\n\u0004\b#\u0010\u001E\u001A\u0004\b\"\u0010\u001CR\u001B\u0010\r\u001A\u00020\u000E8VX\u0096\u0084\u0002\u00A2\u0006\f\n\u0004\b&\u0010\'\u001A\u0004\b$\u0010%R\u0014\u0010(\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b(\u0010*R\u0014\u0010+\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b+\u0010*R\u0014\u0010,\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b,\u0010*R\u0014\u0010-\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b-\u0010*R\u0014\u0010.\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b.\u0010*R\u0014\u0010/\u001A\u00020)8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b/\u0010*R\u0014\u0010\b\u001A\u00020\t8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b0\u00101R\u0010\u0010\u0011\u001A\u0004\u0018\u00010\u0002X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006@"}, d2 = {"Lkotlin/reflect/jvm/internal/KFunctionImpl;", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "", "Lkotlin/reflect/KFunction;", "Lkotlin/jvm/internal/FunctionBase;", "Lkotlin/reflect/jvm/internal/FunctionWithAllInvokes;", "container", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "name", "", "signature", "boundReceiver", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "descriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;)V", "descriptorInitialValue", "rawBoundReceiver", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;Ljava/lang/String;Ljava/lang/String;Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;Ljava/lang/Object;)V", "arity", "", "getArity", "()I", "getBoundReceiver", "()Ljava/lang/Object;", "caller", "Lkotlin/reflect/jvm/internal/calls/Caller;", "getCaller", "()Lkotlin/reflect/jvm/internal/calls/Caller;", "caller$delegate", "Lkotlin/Lazy;", "getContainer", "()Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "defaultCaller", "getDefaultCaller", "defaultCaller$delegate", "getDescriptor", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "descriptor$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "isBound", "", "()Z", "isExternal", "isInfix", "isInline", "isOperator", "isSuspend", "getName", "()Ljava/lang/String;", "createConstructorCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl;", "Ljava/lang/reflect/Constructor;", "member", "isDefault", "createInstanceMethodCaller", "Lkotlin/reflect/jvm/internal/calls/CallerImpl$Method;", "Ljava/lang/reflect/Method;", "createJvmStaticInObjectCaller", "createStaticMethodCaller", "equals", "other", "hashCode", "toString", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class KFunctionImpl extends KCallableImpl implements FunctionBase, KFunction, FunctionWithAllInvokes {
    static final KProperty[] $$delegatedProperties;
    private final Lazy caller$delegate;
    private final KDeclarationContainerImpl container;
    private final Lazy defaultCaller$delegate;
    private final LazySoftVal descriptor$delegate;
    private final Object rawBoundReceiver;
    private final String signature;

    static {
        KFunctionImpl.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(KFunctionImpl.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;"))};
    }

    public KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, Object object0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "signature");
        this(kDeclarationContainerImpl0, s, s1, null, object0);
    }

    private KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, FunctionDescriptor functionDescriptor0, Object object0) {
        this.container = kDeclarationContainerImpl0;
        this.signature = s1;
        this.rawBoundReceiver = object0;
        this.descriptor$delegate = ReflectProperties.lazySoft(functionDescriptor0, new Function0(s) {
            final String $name;

            {
                KFunctionImpl.this = kFunctionImpl0;
                this.$name = s;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final FunctionDescriptor invoke() {
                KDeclarationContainerImpl kDeclarationContainerImpl0 = KFunctionImpl.this.getContainer();
                String s = KFunctionImpl.access$getSignature$p(KFunctionImpl.this);
                return kDeclarationContainerImpl0.findFunctionDescriptor(this.$name, s);
            }
        });
        Function0 function00 = new Function0() {
            {
                KFunctionImpl.this = kFunctionImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Caller invoke() {
                Member member0;
                FunctionDescriptor functionDescriptor0 = KFunctionImpl.this.getDescriptor();
                JvmFunctionSignature jvmFunctionSignature0 = RuntimeTypeMapper.INSTANCE.mapSignature(functionDescriptor0);
                if(jvmFunctionSignature0 instanceof KotlinConstructor) {
                    if(KFunctionImpl.this.isAnnotationConstructor()) {
                        Class class0 = KFunctionImpl.this.getContainer().getJClass();
                        Iterable iterable0 = KFunctionImpl.this.getParameters();
                        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                        for(Object object0: iterable0) {
                            String s = ((KParameter)object0).getName();
                            Intrinsics.checkNotNull(s);
                            arrayList0.add(s);
                        }
                        return new AnnotationConstructorCaller(class0, arrayList0, CallMode.POSITIONAL_CALL, Origin.KOTLIN, null, 16, null);
                    }
                    String s1 = ((KotlinConstructor)jvmFunctionSignature0).getConstructorDesc();
                    member0 = KFunctionImpl.this.getContainer().findConstructorBySignature(s1);
                    goto label_28;
                }
                if(jvmFunctionSignature0 instanceof KotlinFunction) {
                    String s2 = ((KotlinFunction)jvmFunctionSignature0).getMethodName();
                    String s3 = ((KotlinFunction)jvmFunctionSignature0).getMethodDesc();
                    member0 = KFunctionImpl.this.getContainer().findMethodBySignature(s2, s3);
                    goto label_28;
                }
                boolean z = false;
                if(jvmFunctionSignature0 instanceof JavaMethod) {
                    z = true;
                    member0 = ((JavaMethod)jvmFunctionSignature0).getMethod();
                }
                else if(jvmFunctionSignature0 instanceof JavaConstructor) {
                    z = true;
                    member0 = ((JavaConstructor)jvmFunctionSignature0).getConstructor();
                }
                if(z) {
                label_28:
                    if(member0 instanceof Constructor) {
                        FunctionDescriptor functionDescriptor1 = KFunctionImpl.this.getDescriptor();
                        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(KFunctionImpl.this.createConstructorCaller(((Constructor)member0), functionDescriptor1, false), KFunctionImpl.this.getDescriptor(), false, 2, null);
                    }
                    if(!(member0 instanceof Method)) {
                        throw new KotlinReflectionInternalError("Could not compute caller for function: " + KFunctionImpl.this.getDescriptor() + " (member = " + member0 + ')');
                    }
                    if(!Modifier.isStatic(((Method)member0).getModifiers())) {
                        return InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(KFunctionImpl.this.createInstanceMethodCaller(((Method)member0)), KFunctionImpl.this.getDescriptor(), false, 2, null);
                    }
                    return KFunctionImpl.this.getDescriptor().getAnnotations().findAnnotation(UtilKt.getJVM_STATIC()) == null ? InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(KFunctionImpl.access$createStaticMethodCaller(KFunctionImpl.this, ((Method)member0)), KFunctionImpl.this.getDescriptor(), false, 2, null) : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded$default(KFunctionImpl.access$createJvmStaticInObjectCaller(KFunctionImpl.this, ((Method)member0)), KFunctionImpl.this.getDescriptor(), false, 2, null);
                }
                if(!(jvmFunctionSignature0 instanceof FakeJavaAnnotationConstructor)) {
                    throw new NoWhenBranchMatchedException();
                }
                List list0 = ((FakeJavaAnnotationConstructor)jvmFunctionSignature0).getMethods();
                Class class1 = KFunctionImpl.this.getContainer().getJClass();
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object1: list0) {
                    arrayList1.add(((Method)object1).getName());
                }
                return new AnnotationConstructorCaller(class1, arrayList1, CallMode.POSITIONAL_CALL, Origin.JAVA, list0);
            }
        };
        this.caller$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function00);
        Function0 function01 = new Function0() {
            {
                KFunctionImpl.this = kFunctionImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Caller invoke() {
                kotlin.reflect.jvm.internal.calls.CallerImpl.Method callerImpl$Method0;
                Member member1;
                FunctionDescriptor functionDescriptor0 = KFunctionImpl.this.getDescriptor();
                JvmFunctionSignature jvmFunctionSignature0 = RuntimeTypeMapper.INSTANCE.mapSignature(functionDescriptor0);
                if(jvmFunctionSignature0 instanceof KotlinFunction) {
                    String s = ((KotlinFunction)jvmFunctionSignature0).getMethodName();
                    String s1 = ((KotlinFunction)jvmFunctionSignature0).getMethodDesc();
                    Member member0 = KFunctionImpl.this.getCaller().getMember();
                    Intrinsics.checkNotNull(member0);
                    boolean z = Modifier.isStatic(member0.getModifiers());
                    member1 = KFunctionImpl.this.getContainer().findDefaultMethod(s, s1, !z);
                }
                else if(jvmFunctionSignature0 instanceof KotlinConstructor) {
                    if(KFunctionImpl.this.isAnnotationConstructor()) {
                        Class class0 = KFunctionImpl.this.getContainer().getJClass();
                        Iterable iterable0 = KFunctionImpl.this.getParameters();
                        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                        for(Object object0: iterable0) {
                            String s2 = ((KParameter)object0).getName();
                            Intrinsics.checkNotNull(s2);
                            arrayList0.add(s2);
                        }
                        return new AnnotationConstructorCaller(class0, arrayList0, CallMode.CALL_BY_NAME, Origin.KOTLIN, null, 16, null);
                    }
                    String s3 = ((KotlinConstructor)jvmFunctionSignature0).getConstructorDesc();
                    member1 = KFunctionImpl.this.getContainer().findDefaultConstructor(s3);
                }
                else {
                    if(jvmFunctionSignature0 instanceof FakeJavaAnnotationConstructor) {
                        List list0 = ((FakeJavaAnnotationConstructor)jvmFunctionSignature0).getMethods();
                        Class class1 = KFunctionImpl.this.getContainer().getJClass();
                        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                        for(Object object1: list0) {
                            arrayList1.add(((Method)object1).getName());
                        }
                        return new AnnotationConstructorCaller(class1, arrayList1, CallMode.CALL_BY_NAME, Origin.JAVA, list0);
                    }
                    member1 = null;
                }
                if(member1 instanceof Constructor) {
                    FunctionDescriptor functionDescriptor1 = KFunctionImpl.this.getDescriptor();
                    CallerImpl callerImpl0 = KFunctionImpl.this.createConstructorCaller(((Constructor)member1), functionDescriptor1, true);
                    return callerImpl0 == null ? null : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(callerImpl0, KFunctionImpl.this.getDescriptor(), true);
                }
                if(member1 instanceof Method) {
                    if(KFunctionImpl.this.getDescriptor().getAnnotations().findAnnotation(UtilKt.getJVM_STATIC()) != null) {
                        DeclarationDescriptor declarationDescriptor0 = KFunctionImpl.this.getDescriptor().getContainingDeclaration();
                        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                        if(!((ClassDescriptor)declarationDescriptor0).isCompanionObject()) {
                            callerImpl$Method0 = KFunctionImpl.this.createJvmStaticInObjectCaller(((Method)member1));
                            return callerImpl$Method0 == null ? null : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(callerImpl$Method0, KFunctionImpl.this.getDescriptor(), true);
                        }
                    }
                    callerImpl$Method0 = KFunctionImpl.this.createStaticMethodCaller(((Method)member1));
                    return callerImpl$Method0 == null ? null : InlineClassAwareCallerKt.createInlineClassAwareCallerIfNeeded(callerImpl$Method0, KFunctionImpl.this.getDescriptor(), true);
                }
                return null;
            }
        };
        this.defaultCaller$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, function01);
    }

    KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl0, String s, String s1, FunctionDescriptor functionDescriptor0, Object object0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 16) != 0) {
            object0 = CallableReference.NO_RECEIVER;
        }
        this(kDeclarationContainerImpl0, s, s1, functionDescriptor0, object0);
    }

    public KFunctionImpl(KDeclarationContainerImpl kDeclarationContainerImpl0, FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(kDeclarationContainerImpl0, "container");
        Intrinsics.checkNotNullParameter(functionDescriptor0, "descriptor");
        String s = functionDescriptor0.getName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "descriptor.name.asString()");
        this(kDeclarationContainerImpl0, s, RuntimeTypeMapper.INSTANCE.mapSignature(functionDescriptor0).asString(), functionDescriptor0, null, 16, null);
    }

    public static final String access$getSignature$p(KFunctionImpl kFunctionImpl0) {
        return kFunctionImpl0.signature;
    }

    private final CallerImpl createConstructorCaller(Constructor constructor0, FunctionDescriptor functionDescriptor0, boolean z) {
        if(!z && InlineClassManglingRulesKt.shouldHideConstructorDueToInlineClassTypeValueParameters(functionDescriptor0)) {
            return this.isBound() ? new AccessorForHiddenBoundConstructor(constructor0, this.getBoundReceiver()) : new AccessorForHiddenConstructor(constructor0);
        }
        return this.isBound() ? new BoundConstructor(constructor0, this.getBoundReceiver()) : new kotlin.reflect.jvm.internal.calls.CallerImpl.Constructor(constructor0);
    }

    private final kotlin.reflect.jvm.internal.calls.CallerImpl.Method createInstanceMethodCaller(Method method0) {
        return this.isBound() ? new BoundInstance(method0, this.getBoundReceiver()) : new Instance(method0);
    }

    private final kotlin.reflect.jvm.internal.calls.CallerImpl.Method createJvmStaticInObjectCaller(Method method0) {
        return this.isBound() ? new BoundJvmStaticInObject(method0) : new JvmStaticInObject(method0);
    }

    private final kotlin.reflect.jvm.internal.calls.CallerImpl.Method createStaticMethodCaller(Method method0) {
        return this.isBound() ? new BoundStatic(method0, this.getBoundReceiver()) : new Static(method0);
    }

    // 去混淆评级： 低(25)
    @Override
    public boolean equals(Object object0) {
        KFunctionImpl kFunctionImpl0 = UtilKt.asKFunctionImpl(object0);
        return kFunctionImpl0 == null ? false : Intrinsics.areEqual(this.getContainer(), kFunctionImpl0.getContainer()) && Intrinsics.areEqual(this.getName(), kFunctionImpl0.getName()) && Intrinsics.areEqual(this.signature, kFunctionImpl0.signature) && Intrinsics.areEqual(this.rawBoundReceiver, kFunctionImpl0.rawBoundReceiver);
    }

    @Override  // kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return CallerKt.getArity(this.getCaller());
    }

    private final Object getBoundReceiver() {
        CallableMemberDescriptor callableMemberDescriptor0 = this.getDescriptor();
        return InlineClassAwareCallerKt.coerceToExpectedReceiverType(this.rawBoundReceiver, callableMemberDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getCaller() {
        return (Caller)this.caller$delegate.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public KDeclarationContainerImpl getContainer() {
        return this.container;
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public Caller getDefaultCaller() {
        return (Caller)this.defaultCaller$delegate.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public CallableMemberDescriptor getDescriptor() {
        return this.getDescriptor();
    }

    public FunctionDescriptor getDescriptor() {
        Object object0 = this.descriptor$delegate.getValue(this, KFunctionImpl.$$delegatedProperties[0]);
        Intrinsics.checkNotNullExpressionValue(object0, "<get-descriptor>(...)");
        return (FunctionDescriptor)object0;
    }

    @Override  // kotlin.reflect.KCallable
    public String getName() {
        String s = this.getDescriptor().getName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "descriptor.name.asString()");
        return s;
    }

    @Override
    public int hashCode() {
        return (this.getContainer().hashCode() * 0x1F + this.getName().hashCode()) * 0x1F + this.signature.hashCode();
    }

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        return DefaultImpls.invoke(this);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return DefaultImpls.invoke(this, object0);
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return DefaultImpls.invoke(this, object0, object1);
    }

    @Override  // kotlin.jvm.functions.Function3
    public Object invoke(Object object0, Object object1, Object object2) {
        return DefaultImpls.invoke(this, object0, object1, object2);
    }

    @Override  // kotlin.jvm.functions.Function4
    public Object invoke(Object object0, Object object1, Object object2, Object object3) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3);
    }

    @Override  // kotlin.jvm.functions.Function5
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4);
    }

    @Override  // kotlin.jvm.functions.Function6
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5);
    }

    @Override  // kotlin.jvm.functions.Function7
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6);
    }

    @Override  // kotlin.jvm.functions.Function8
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7);
    }

    @Override  // kotlin.jvm.functions.Function9
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8);
    }

    @Override  // kotlin.jvm.functions.Function10
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9);
    }

    @Override  // kotlin.jvm.functions.Function11
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10);
    }

    @Override  // kotlin.jvm.functions.Function12
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11);
    }

    @Override  // kotlin.jvm.functions.Function13
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12);
    }

    @Override  // kotlin.jvm.functions.Function14
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13);
    }

    @Override  // kotlin.jvm.functions.Function15
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14);
    }

    @Override  // kotlin.jvm.functions.Function16
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15);
    }

    @Override  // kotlin.jvm.functions.Function17
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16);
    }

    @Override  // kotlin.jvm.functions.Function18
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17);
    }

    @Override  // kotlin.jvm.functions.Function19
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18);
    }

    @Override  // kotlin.jvm.functions.Function20
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19);
    }

    @Override  // kotlin.jvm.functions.Function21
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19, Object object20) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19, object20);
    }

    @Override  // kotlin.jvm.functions.Function22
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19, Object object20, Object object21) {
        return DefaultImpls.invoke(this, object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19, object20, object21);
    }

    @Override  // kotlin.reflect.jvm.internal.KCallableImpl
    public boolean isBound() {
        return !Intrinsics.areEqual(this.rawBoundReceiver, CallableReference.NO_RECEIVER);
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

    @Override
    public String toString() {
        FunctionDescriptor functionDescriptor0 = this.getDescriptor();
        return ReflectionObjectRenderer.INSTANCE.renderFunction(functionDescriptor0);
    }
}

