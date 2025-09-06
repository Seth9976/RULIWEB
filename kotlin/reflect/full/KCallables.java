package kotlin.reflect.full;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter.Kind;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.UtilKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\u001A9\u0010\u000F\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0016\u0010\u0011\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00130\u0012\"\u0004\u0018\u00010\u0013H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001A7\u0010\u0015\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00022\u0014\u0010\u0011\u001A\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0016H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001A\u001A\u0010\u0018\u001A\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0019\u001A\u00020\u001AH\u0007\"$\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001A\u0004\b\u0005\u0010\u0006\"$\u0010\u0007\u001A\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001A\u0004\b\t\u0010\u0006\"(\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00010\u000B*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001A\u0004\b\r\u0010\u000E\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001B"}, d2 = {"extensionReceiverParameter", "Lkotlin/reflect/KParameter;", "Lkotlin/reflect/KCallable;", "getExtensionReceiverParameter$annotations", "(Lkotlin/reflect/KCallable;)V", "getExtensionReceiverParameter", "(Lkotlin/reflect/KCallable;)Lkotlin/reflect/KParameter;", "instanceParameter", "getInstanceParameter$annotations", "getInstanceParameter", "valueParameters", "", "getValueParameters$annotations", "getValueParameters", "(Lkotlin/reflect/KCallable;)Ljava/util/List;", "callSuspend", "R", "args", "", "", "(Lkotlin/reflect/KCallable;[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callSuspendBy", "", "(Lkotlin/reflect/KCallable;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findParameterByName", "name", "", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KCallables {
    public static final Object callSuspend(KCallable kCallable0, Object[] arr_object, Continuation continuation0) {
        kotlin.reflect.full.KCallables.callSuspend.1 kCallables$callSuspend$10;
        if(continuation0 instanceof kotlin.reflect.full.KCallables.callSuspend.1) {
            kCallables$callSuspend$10 = (kotlin.reflect.full.KCallables.callSuspend.1)continuation0;
            if((kCallables$callSuspend$10.label & 0x80000000) == 0) {
                kCallables$callSuspend$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return KCallables.callSuspend(null, null, this);
                    }
                };
            }
            else {
                kCallables$callSuspend$10.label ^= 0x80000000;
            }
        }
        else {
            kCallables$callSuspend$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return KCallables.callSuspend(null, null, this);
                }
            };
        }
        Object object0 = kCallables$callSuspend$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(kCallables$callSuspend$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                if(!kCallable0.isSuspend()) {
                    return kCallable0.call(Arrays.copyOf(arr_object, arr_object.length));
                }
                if(!(kCallable0 instanceof KFunction)) {
                    throw new IllegalArgumentException("Cannot callSuspend on a property " + kCallable0 + ": suspend properties are not supported yet");
                }
                kCallables$callSuspend$10.L$0 = kCallable0;
                kCallables$callSuspend$10.L$1 = arr_object;
                kCallables$callSuspend$10.label = 1;
                SpreadBuilder spreadBuilder0 = new SpreadBuilder(2);
                spreadBuilder0.addSpread(arr_object);
                spreadBuilder0.add(kCallables$callSuspend$10);
                object0 = kCallable0.call(spreadBuilder0.toArray(new Object[spreadBuilder0.size()]));
                if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(kCallables$callSuspend$10);
                }
                if(object0 == object1) {
                    return object1;
                }
                break;
            }
            case 1: {
                Object[] arr_object1 = (Object[])kCallables$callSuspend$10.L$1;
                kCallable0 = (KCallable)kCallables$callSuspend$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        return Intrinsics.areEqual(kCallable0.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(Unit.class)) && !kCallable0.getReturnType().isMarkedNullable() ? Unit.INSTANCE : object0;
    }

    public static final Object callSuspendBy(KCallable kCallable0, Map map0, Continuation continuation0) {
        kotlin.reflect.full.KCallables.callSuspendBy.1 kCallables$callSuspendBy$10;
        if(continuation0 instanceof kotlin.reflect.full.KCallables.callSuspendBy.1) {
            kCallables$callSuspendBy$10 = (kotlin.reflect.full.KCallables.callSuspendBy.1)continuation0;
            if((kCallables$callSuspendBy$10.label & 0x80000000) == 0) {
                kCallables$callSuspendBy$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return KCallables.callSuspendBy(null, null, this);
                    }
                };
            }
            else {
                kCallables$callSuspendBy$10.label ^= 0x80000000;
            }
        }
        else {
            kCallables$callSuspendBy$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return KCallables.callSuspendBy(null, null, this);
                }
            };
        }
        Object object0 = kCallables$callSuspendBy$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(kCallables$callSuspendBy$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                if(!kCallable0.isSuspend()) {
                    return kCallable0.callBy(map0);
                }
                if(!(kCallable0 instanceof KFunction)) {
                    throw new IllegalArgumentException("Cannot callSuspendBy on a property " + kCallable0 + ": suspend properties are not supported yet");
                }
                KCallableImpl kCallableImpl0 = UtilKt.asKCallableImpl(kCallable0);
                if(kCallableImpl0 == null) {
                    throw new KotlinReflectionInternalError("This callable does not support a default call: " + kCallable0);
                }
                kCallables$callSuspendBy$10.L$0 = kCallable0;
                kCallables$callSuspendBy$10.L$1 = map0;
                kCallables$callSuspendBy$10.L$2 = kCallableImpl0;
                kCallables$callSuspendBy$10.label = 1;
                object0 = kCallableImpl0.callDefaultMethod$kotlin_reflection(map0, kCallables$callSuspendBy$10);
                if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(kCallables$callSuspendBy$10);
                }
                if(object0 == object1) {
                    return object1;
                }
                break;
            }
            case 1: {
                KCallableImpl kCallableImpl1 = (KCallableImpl)kCallables$callSuspendBy$10.L$2;
                Map map1 = (Map)kCallables$callSuspendBy$10.L$1;
                kCallable0 = (KCallable)kCallables$callSuspendBy$10.L$0;
                ResultKt.throwOnFailure(object0);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        return Intrinsics.areEqual(kCallable0.getReturnType().getClassifier(), Reflection.getOrCreateKotlinClass(Unit.class)) && !kCallable0.getReturnType().isMarkedNullable() ? Unit.INSTANCE : object0;
    }

    public static final KParameter findParameterByName(KCallable kCallable0, String s) {
        Intrinsics.checkNotNullParameter(kCallable0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Object object0 = null;
        boolean z = false;
        Object object1 = null;
        Iterator iterator0 = kCallable0.getParameters().iterator();
        while(true) {
            if(!iterator0.hasNext()) {
                if(!z) {
                    break;
                }
                object0 = object1;
                break;
            }
            Object object2 = iterator0.next();
            if(Intrinsics.areEqual(((KParameter)object2).getName(), s)) {
                if(z) {
                    break;
                }
                z = true;
                object1 = object2;
            }
        }
        return (KParameter)object0;
    }

    public static final KParameter getExtensionReceiverParameter(KCallable kCallable0) {
        Intrinsics.checkNotNullParameter(kCallable0, "<this>");
        Object object0 = null;
        boolean z = false;
        Object object1 = null;
        Iterator iterator0 = kCallable0.getParameters().iterator();
        while(true) {
            if(!iterator0.hasNext()) {
                if(!z) {
                    break;
                }
                object0 = object1;
                break;
            }
            Object object2 = iterator0.next();
            if(((KParameter)object2).getKind() == Kind.EXTENSION_RECEIVER) {
                if(z) {
                    break;
                }
                z = true;
                object1 = object2;
            }
        }
        return (KParameter)object0;
    }

    public static void getExtensionReceiverParameter$annotations(KCallable kCallable0) {
    }

    public static final KParameter getInstanceParameter(KCallable kCallable0) {
        Intrinsics.checkNotNullParameter(kCallable0, "<this>");
        Object object0 = null;
        boolean z = false;
        Object object1 = null;
        Iterator iterator0 = kCallable0.getParameters().iterator();
        while(true) {
            if(!iterator0.hasNext()) {
                if(!z) {
                    break;
                }
                object0 = object1;
                break;
            }
            Object object2 = iterator0.next();
            if(((KParameter)object2).getKind() == Kind.INSTANCE) {
                if(z) {
                    break;
                }
                z = true;
                object1 = object2;
            }
        }
        return (KParameter)object0;
    }

    public static void getInstanceParameter$annotations(KCallable kCallable0) {
    }

    public static final List getValueParameters(KCallable kCallable0) {
        Intrinsics.checkNotNullParameter(kCallable0, "<this>");
        Iterable iterable0 = kCallable0.getParameters();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(((KParameter)object0).getKind() == Kind.VALUE) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getValueParameters$annotations(KCallable kCallable0) {
    }
}

