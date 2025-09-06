package kotlin.reflect.jvm.internal;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.MutablePropertyReference1;
import kotlin.jvm.internal.MutablePropertyReference2;
import kotlin.jvm.internal.PropertyReference0;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference2;
import kotlin.jvm.internal.ReflectionFactory;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;
import kotlin.reflect.full.KClassifiers;
import kotlin.reflect.jvm.ReflectLambdaKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;

public class ReflectionFactoryImpl extends ReflectionFactory {
    public static void clearCaches() {
        CachesKt.clearCaches();
        ModuleByClassLoaderKt.clearModuleByClassLoaderCache();
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KClass createKotlinClass(Class class0) {
        return new KClassImpl(class0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KClass createKotlinClass(Class class0, String s) {
        return new KClassImpl(class0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KFunction function(FunctionReference functionReference0) {
        return new KFunctionImpl(ReflectionFactoryImpl.getOwner(functionReference0), functionReference0.getName(), functionReference0.getSignature(), functionReference0.getBoundReceiver());
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KClass getOrCreateKotlinClass(Class class0) {
        return CachesKt.getOrCreateKotlinClass(class0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KClass getOrCreateKotlinClass(Class class0, String s) {
        return CachesKt.getOrCreateKotlinClass(class0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KDeclarationContainer getOrCreateKotlinPackage(Class class0, String s) {
        return CachesKt.getOrCreateKotlinPackage(class0);
    }

    private static KDeclarationContainerImpl getOwner(CallableReference callableReference0) {
        KDeclarationContainer kDeclarationContainer0 = callableReference0.getOwner();
        return kDeclarationContainer0 instanceof KDeclarationContainerImpl ? ((KDeclarationContainerImpl)kDeclarationContainer0) : EmptyContainerForLocal.INSTANCE;
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KType mutableCollectionType(KType kType0) {
        return TypeOfImplKt.createMutableCollectionKType(kType0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KMutableProperty0 mutableProperty0(MutablePropertyReference0 mutablePropertyReference00) {
        return new KMutableProperty0Impl(ReflectionFactoryImpl.getOwner(mutablePropertyReference00), mutablePropertyReference00.getName(), mutablePropertyReference00.getSignature(), mutablePropertyReference00.getBoundReceiver());
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KMutableProperty1 mutableProperty1(MutablePropertyReference1 mutablePropertyReference10) {
        return new KMutableProperty1Impl(ReflectionFactoryImpl.getOwner(mutablePropertyReference10), mutablePropertyReference10.getName(), mutablePropertyReference10.getSignature(), mutablePropertyReference10.getBoundReceiver());
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KMutableProperty2 mutableProperty2(MutablePropertyReference2 mutablePropertyReference20) {
        return new KMutableProperty2Impl(ReflectionFactoryImpl.getOwner(mutablePropertyReference20), mutablePropertyReference20.getName(), mutablePropertyReference20.getSignature());
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KType nothingType(KType kType0) {
        return TypeOfImplKt.createNothingType(kType0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KType platformType(KType kType0, KType kType1) {
        return TypeOfImplKt.createPlatformKType(kType0, kType1);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KProperty0 property0(PropertyReference0 propertyReference00) {
        return new KProperty0Impl(ReflectionFactoryImpl.getOwner(propertyReference00), propertyReference00.getName(), propertyReference00.getSignature(), propertyReference00.getBoundReceiver());
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KProperty1 property1(PropertyReference1 propertyReference10) {
        return new KProperty1Impl(ReflectionFactoryImpl.getOwner(propertyReference10), propertyReference10.getName(), propertyReference10.getSignature(), propertyReference10.getBoundReceiver());
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KProperty2 property2(PropertyReference2 propertyReference20) {
        return new KProperty2Impl(ReflectionFactoryImpl.getOwner(propertyReference20), propertyReference20.getName(), propertyReference20.getSignature());
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public String renderLambdaToString(FunctionBase functionBase0) {
        KFunction kFunction0 = ReflectLambdaKt.reflect(functionBase0);
        if(kFunction0 != null) {
            KFunctionImpl kFunctionImpl0 = UtilKt.asKFunctionImpl(kFunction0);
            if(kFunctionImpl0 != null) {
                FunctionDescriptor functionDescriptor0 = kFunctionImpl0.getDescriptor();
                return ReflectionObjectRenderer.INSTANCE.renderLambda(functionDescriptor0);
            }
        }
        return super.renderLambdaToString(functionBase0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public String renderLambdaToString(Lambda lambda0) {
        return this.renderLambdaToString(lambda0);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public void setUpperBounds(KTypeParameter kTypeParameter0, List list0) {
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KType typeOf(KClassifier kClassifier0, List list0, boolean z) {
        return kClassifier0 instanceof ClassBasedDeclarationContainer ? CachesKt.getOrCreateKType(((ClassBasedDeclarationContainer)kClassifier0).getJClass(), list0, z) : KClassifiers.createType(kClassifier0, list0, z, Collections.EMPTY_LIST);
    }

    @Override  // kotlin.jvm.internal.ReflectionFactory
    public KTypeParameter typeParameter(Object object0, String s, KVariance kVariance0, boolean z) {
        List list0;
        if(object0 instanceof KClass) {
            list0 = ((KClass)object0).getTypeParameters();
        }
        else if(object0 instanceof KCallable) {
            list0 = ((KCallable)object0).getTypeParameters();
        }
        else {
            throw new IllegalArgumentException("Type parameter container must be a class or a callable: " + object0);
        }
        for(Object object1: list0) {
            KTypeParameter kTypeParameter0 = (KTypeParameter)object1;
            if(kTypeParameter0.getName().equals(s)) {
                return kTypeParameter0;
            }
            if(false) {
                break;
            }
        }
        throw new IllegalArgumentException("Type parameter " + s + " is not found in container: " + object0);
    }
}

