package kotlin.reflect.full;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.KCallableImpl;
import kotlin.reflect.jvm.internal.KClassImpl.Data;
import kotlin.reflect.jvm.internal.KClassImpl;
import kotlin.reflect.jvm.internal.KFunctionImpl;
import kotlin.reflect.jvm.internal.KTypeImpl;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler;
import kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandlerWithListResult;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Visited;
import kotlin.reflect.jvm.internal.impl.utils.DFS.VisitedWithSet;
import kotlin.reflect.jvm.internal.impl.utils.DFS;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\r\u001A+\u0010S\u001A\u0002H\u001D\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u00022\b\u0010T\u001A\u0004\u0018\u00010\u0010H\u0007\u00A2\u0006\u0002\u0010U\u001A!\u0010V\u001A\u0002H\u001D\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u0002H\u0007\u00A2\u0006\u0002\u0010\u0013\u001A\u001C\u0010W\u001A\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010X\u001A\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001A\u001C\u0010Y\u001A\u000203*\u0006\u0012\u0002\b\u00030\u00022\n\u0010Z\u001A\u0006\u0012\u0002\b\u00030\u0002H\u0007\u001A-\u0010[\u001A\u0004\u0018\u0001H\u001D\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u00022\b\u0010T\u001A\u0004\u0018\u00010\u0010H\u0007\u00A2\u0006\u0002\u0010U\",\u0010\u0000\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001A\u0004\b\u0005\u0010\u0006\"(\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\b0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\t\u0010\u0004\u001A\u0004\b\n\u0010\u0006\"(\u0010\u000B\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0002*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\f\u0010\u0004\u001A\u0004\b\r\u0010\u000E\"$\u0010\u000F\u001A\u0004\u0018\u00010\u0010*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001A\u0004\b\u0012\u0010\u0013\",\u0010\u0014\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0016\u0010\u0004\u001A\u0004\b\u0017\u0010\u0006\",\u0010\u0018\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0019\u0010\u0004\u001A\u0004\b\u001A\u0010\u0006\"B\u0010\u001B\u001A\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001D\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001C0\u0001\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u001E\u0010\u0004\u001A\u0004\b\u001F\u0010\u0006\",\u0010 \u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b!\u0010\u0004\u001A\u0004\b\"\u0010\u0006\">\u0010#\u001A\u0012\u0012\u000E\u0012\f\u0012\u0004\u0012\u0002H\u001D\u0012\u0002\b\u00030$0\u0001\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b%\u0010\u0004\u001A\u0004\b&\u0010\u0006\",\u0010\'\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030(0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b)\u0010\u0004\u001A\u0004\b*\u0010\u0006\"\"\u0010+\u001A\u00020\b*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b,\u0010\u0004\u001A\u0004\b-\u0010.\",\u0010/\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b0\u0010\u0004\u001A\u0004\b1\u0010\u0006\"\u001C\u00102\u001A\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b2\u00105\"\u001C\u00106\u001A\u000203*\u0006\u0012\u0002\b\u0003048BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b6\u00105\",\u00107\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b8\u0010\u0004\u001A\u0004\b9\u0010\u0006\"B\u0010:\u001A\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H\u001D\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001C0\u0001\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b;\u0010\u0004\u001A\u0004\b<\u0010\u0006\",\u0010=\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b>\u0010\u0004\u001A\u0004\b?\u0010\u0006\">\u0010@\u001A\u0012\u0012\u000E\u0012\f\u0012\u0004\u0012\u0002H\u001D\u0012\u0002\b\u00030$0\u0001\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\bA\u0010\u0004\u001A\u0004\bB\u0010\u0006\"6\u0010C\u001A\n\u0012\u0004\u0012\u0002H\u001D\u0018\u00010\u0015\"\b\b\u0000\u0010\u001D*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u001D0\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\bD\u0010\u0004\u001A\u0004\bE\u0010F\",\u0010G\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\bH\u0010\u0004\u001A\u0004\bI\u0010\u0006\",\u0010J\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030K0\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\bL\u0010\u0004\u001A\u0004\bM\u0010\u0006\",\u0010N\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020O*\u0006\u0012\u0002\b\u00030\u00028FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\bP\u0010\u0004\u001A\u0004\bQ\u0010R\u00A8\u0006\\"}, d2 = {"allSuperclasses", "", "Lkotlin/reflect/KClass;", "getAllSuperclasses$annotations", "(Lkotlin/reflect/KClass;)V", "getAllSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/Collection;", "allSupertypes", "Lkotlin/reflect/KType;", "getAllSupertypes$annotations", "getAllSupertypes", "companionObject", "getCompanionObject$annotations", "getCompanionObject", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KClass;", "companionObjectInstance", "", "getCompanionObjectInstance$annotations", "getCompanionObjectInstance", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "declaredFunctions", "Lkotlin/reflect/KFunction;", "getDeclaredFunctions$annotations", "getDeclaredFunctions", "declaredMemberExtensionFunctions", "getDeclaredMemberExtensionFunctions$annotations", "getDeclaredMemberExtensionFunctions", "declaredMemberExtensionProperties", "Lkotlin/reflect/KProperty2;", "T", "getDeclaredMemberExtensionProperties$annotations", "getDeclaredMemberExtensionProperties", "declaredMemberFunctions", "getDeclaredMemberFunctions$annotations", "getDeclaredMemberFunctions", "declaredMemberProperties", "Lkotlin/reflect/KProperty1;", "getDeclaredMemberProperties$annotations", "getDeclaredMemberProperties", "declaredMembers", "Lkotlin/reflect/KCallable;", "getDeclaredMembers$annotations", "getDeclaredMembers", "defaultType", "getDefaultType$annotations", "getDefaultType", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KType;", "functions", "getFunctions$annotations", "getFunctions", "isExtension", "", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "(Lkotlin/reflect/jvm/internal/KCallableImpl;)Z", "isNotExtension", "memberExtensionFunctions", "getMemberExtensionFunctions$annotations", "getMemberExtensionFunctions", "memberExtensionProperties", "getMemberExtensionProperties$annotations", "getMemberExtensionProperties", "memberFunctions", "getMemberFunctions$annotations", "getMemberFunctions", "memberProperties", "getMemberProperties$annotations", "getMemberProperties", "primaryConstructor", "getPrimaryConstructor$annotations", "getPrimaryConstructor", "(Lkotlin/reflect/KClass;)Lkotlin/reflect/KFunction;", "staticFunctions", "getStaticFunctions$annotations", "getStaticFunctions", "staticProperties", "Lkotlin/reflect/KProperty0;", "getStaticProperties$annotations", "getStaticProperties", "superclasses", "", "getSuperclasses$annotations", "getSuperclasses", "(Lkotlin/reflect/KClass;)Ljava/util/List;", "cast", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "createInstance", "isSubclassOf", "base", "isSuperclassOf", "derived", "safeCast", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KClasses {
    private static final Iterable _get_allSupertypes_$lambda$14(KType kType0) {
        KClassifier kClassifier0 = kType0.getClassifier();
        KClass kClass0 = kClassifier0 instanceof KClass ? ((KClass)kClassifier0) : null;
        if(kClass0 == null) {
            throw new KotlinReflectionInternalError("Supertype not a class: " + kType0);
        }
        List list0 = kClass0.getSupertypes();
        if(kType0.getArguments().isEmpty()) {
            return list0;
        }
        Intrinsics.checkNotNull(kType0, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
        TypeSubstitutor typeSubstitutor0 = TypeSubstitutor.create(((KTypeImpl)kType0).getType());
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            KType kType1 = (KType)object0;
            Intrinsics.checkNotNull(kType1, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KTypeImpl");
            KotlinType kotlinType0 = typeSubstitutor0.substitute(((KTypeImpl)kType1).getType(), Variance.INVARIANT);
            if(kotlinType0 == null) {
                throw new KotlinReflectionInternalError("Type substitution failed: " + kType1 + " (" + kType0 + ')');
            }
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "substitutor.substitute((…: $supertype ($current)\")");
            arrayList0.add(new KTypeImpl(kotlinType0, null, 2, null));
        }
        return arrayList0;
    }

    // 检测为 Lambda 实现
    static Iterable accessor$KClasses$lambda0(KType kType0) [...]

    // 检测为 Lambda 实现
    static Iterable accessor$KClasses$lambda1(KProperty1 kProperty10, KClass kClass0) [...]

    public static final Object cast(KClass kClass0, Object object0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        if(!kClass0.isInstance(object0)) {
            throw new TypeCastException("Value cannot be cast to " + kClass0.getQualifiedName());
        }
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type T of kotlin.reflect.full.KClasses.cast");
        return object0;
    }

    public static final Object createInstance(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Object object0 = null;
        boolean z = false;
        Object object1 = null;
        Iterator iterator0 = kClass0.getConstructors().iterator();
    alab1:
        while(true) {
            if(!iterator0.hasNext()) {
                goto label_18;
            }
            Object object2 = iterator0.next();
            Iterable iterable0 = ((KFunction)object2).getParameters();
            if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                for(Object object3: iterable0) {
                    if(!((KParameter)object3).isOptional()) {
                        continue alab1;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            if(!z) {
                z = true;
                object1 = object2;
                continue;
            label_18:
                if(z) {
                    object0 = object1;
                }
            }
            break;
        }
        if(((KFunction)object0) == null) {
            throw new IllegalArgumentException("Class should have a single no-arg constructor: " + kClass0);
        }
        return ((KFunction)object0).callBy(MapsKt.emptyMap());
    }

    public static final Collection getAllSuperclasses(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = KClasses.getAllSupertypes(kClass0);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            KType kType0 = (KType)object0;
            KClassifier kClassifier0 = kType0.getClassifier();
            KClass kClass1 = kClassifier0 instanceof KClass ? ((KClass)kClassifier0) : null;
            if(kClass1 == null) {
                throw new KotlinReflectionInternalError("Supertype not a class: " + kType0);
            }
            arrayList0.add(kClass1);
        }
        return arrayList0;
    }

    public static void getAllSuperclasses$annotations(KClass kClass0) {
    }

    public static final Collection getAllSupertypes(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Collection collection0 = kClass0.getSupertypes();
        Visited dFS$Visited0 = new VisitedWithSet();
        NodeHandler dFS$NodeHandler0 = new NodeHandlerWithListResult() {
            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$AbstractNodeHandler
            public boolean beforeChildren(Object object0) {
                return this.beforeChildren(((KType)object0));
            }

            public boolean beforeChildren(KType kType0) {
                Intrinsics.checkNotNullParameter(kType0, "current");
                ((LinkedList)this.result).add(kType0);
                return true;
            }
        };
        Object object0 = DFS.dfs(collection0, (KType kType0) -> KClasses._get_allSupertypes_$lambda$14(kType0), dFS$Visited0, dFS$NodeHandler0);
        Intrinsics.checkNotNullExpressionValue(object0, "dfs(\n        supertypes,…        }\n        }\n    )");
        return (Collection)object0;
    }

    public static void getAllSupertypes$annotations(KClass kClass0) {
    }

    public static final KClass getCompanionObject(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        for(Object object0: kClass0.getNestedClasses()) {
            Intrinsics.checkNotNull(((KClass)object0), "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<*>");
            if(((KClassImpl)(((KClass)object0))).getDescriptor().isCompanionObject()) {
                return (KClass)object0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public static void getCompanionObject$annotations(KClass kClass0) {
    }

    public static final Object getCompanionObjectInstance(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        KClass kClass1 = KClasses.getCompanionObject(kClass0);
        return kClass1 == null ? null : kClass1.getObjectInstance();
    }

    public static void getCompanionObjectInstance$annotations(KClass kClass0) {
    }

    public static final Collection getDeclaredFunctions(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getDeclaredMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(object0 instanceof KFunction) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getDeclaredFunctions$annotations(KClass kClass0) {
    }

    public static final Collection getDeclaredMemberExtensionFunctions(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getDeclaredNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KFunction) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getDeclaredMemberExtensionFunctions$annotations(KClass kClass0) {
    }

    public static final Collection getDeclaredMemberExtensionProperties(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getDeclaredNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KProperty2) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getDeclaredMemberExtensionProperties$annotations(KClass kClass0) {
    }

    public static final Collection getDeclaredMemberFunctions(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getDeclaredNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isNotExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KFunction) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getDeclaredMemberFunctions$annotations(KClass kClass0) {
    }

    public static final Collection getDeclaredMemberProperties(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getDeclaredNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isNotExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KProperty1) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getDeclaredMemberProperties$annotations(KClass kClass0) {
    }

    public static final Collection getDeclaredMembers(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        return ((Data)((KClassImpl)kClass0).getData().invoke()).getDeclaredMembers();
    }

    public static void getDeclaredMembers$annotations(KClass kClass0) {
    }

    public static final KType getDefaultType(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        SimpleType simpleType0 = ((KClassImpl)kClass0).getDescriptor().getDefaultType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "this as KClassImpl<*>).descriptor.defaultType");
        return new KTypeImpl(simpleType0, new Function0(kClass0) {
            final KClass $this_defaultType;

            {
                this.$this_defaultType = kClass0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Type invoke() {
                return ((KClassImpl)this.$this_defaultType).getJClass();
            }
        });
    }

    @Deprecated(message = "This function creates a type which rarely makes sense for generic classes. For example, such type can only be used in signatures of members of that class. Use starProjectedType or createType() for clearer semantics.")
    public static void getDefaultType$annotations(KClass kClass0) {
    }

    public static final Collection getFunctions(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = kClass0.getMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(object0 instanceof KFunction) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getFunctions$annotations(KClass kClass0) {
    }

    public static final Collection getMemberExtensionFunctions(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getAllNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KFunction) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getMemberExtensionFunctions$annotations(KClass kClass0) {
    }

    public static final Collection getMemberExtensionProperties(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getAllNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KProperty2) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getMemberExtensionProperties$annotations(KClass kClass0) {
    }

    public static final Collection getMemberFunctions(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getAllNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isNotExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KFunction) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getMemberFunctions$annotations(KClass kClass0) {
    }

    public static final Collection getMemberProperties(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getAllNonStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isNotExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KProperty1) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getMemberProperties$annotations(KClass kClass0) {
    }

    public static final KFunction getPrimaryConstructor(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        for(Object object0: ((KClassImpl)kClass0).getConstructors()) {
            Intrinsics.checkNotNull(((KFunction)object0), "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KFunctionImpl");
            FunctionDescriptor functionDescriptor0 = ((KFunctionImpl)(((KFunction)object0))).getDescriptor();
            Intrinsics.checkNotNull(functionDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ConstructorDescriptor");
            if(((ConstructorDescriptor)functionDescriptor0).isPrimary()) {
                return (KFunction)object0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public static void getPrimaryConstructor$annotations(KClass kClass0) {
    }

    public static final Collection getStaticFunctions(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getAllStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(object0 instanceof KFunction) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getStaticFunctions$annotations(KClass kClass0) {
    }

    public static final Collection getStaticProperties(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = ((Data)((KClassImpl)kClass0).getData().invoke()).getAllStaticMembers();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(KClasses.isNotExtension(((KCallableImpl)object0)) && ((KCallableImpl)object0) instanceof KProperty0) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    public static void getStaticProperties$annotations(KClass kClass0) {
    }

    public static final List getSuperclasses(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Iterable iterable0 = kClass0.getSupertypes();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            KClassifier kClassifier0 = ((KType)object0).getClassifier();
            KClass kClass1 = kClassifier0 instanceof KClass ? ((KClass)kClassifier0) : null;
            if(kClass1 != null) {
                collection0.add(kClass1);
            }
        }
        return (List)collection0;
    }

    public static void getSuperclasses$annotations(KClass kClass0) {
    }

    private static final boolean isExtension(KCallableImpl kCallableImpl0) {
        return kCallableImpl0.getDescriptor().getExtensionReceiverParameter() != null;
    }

    private static final boolean isNotExtension(KCallableImpl kCallableImpl0) {
        return !KClasses.isExtension(kCallableImpl0);
    }

    public static final boolean isSubclassOf(KClass kClass0, KClass kClass1) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Intrinsics.checkNotNullParameter(kClass1, "base");
        if(!Intrinsics.areEqual(kClass0, kClass1)) {
            Boolean boolean0 = DFS.ifAny(CollectionsKt.listOf(kClass0), (KClass kClass0) -> KClasses.isSubclassOf$lambda$16(kotlin.reflect.full.KClasses.isSubclassOf.1.INSTANCE, kClass0), new Function1(kClass1) {
                final KClass $base;

                {
                    this.$base = kClass0;
                    super(1);
                }

                public final Boolean invoke(KClass kClass0) {
                    return Boolean.valueOf(Intrinsics.areEqual(kClass0, this.$base));
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((KClass)object0));
                }
            });
            Intrinsics.checkNotNullExpressionValue(boolean0, "base: KClass<*>): Boolea…erclasses) { it == base }");
            return boolean0.booleanValue();
        }
        return true;

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlin.reflect.full.KClasses.isSubclassOf.1 extends PropertyReference1 {
            public static final kotlin.reflect.full.KClasses.isSubclassOf.1 INSTANCE;

            static {
                kotlin.reflect.full.KClasses.isSubclassOf.1.INSTANCE = new kotlin.reflect.full.KClasses.isSubclassOf.1();
            }

            kotlin.reflect.full.KClasses.isSubclassOf.1() {
                super();
            }

            @Override  // kotlin.reflect.KProperty1
            public Object get(Object object0) {
                return KClasses.getSuperclasses(((KClass)object0));
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public String getName() {
                return "superclasses";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinPackage(KClasses.class, "kotlin-reflection");
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public String getSignature() {
                return "getSuperclasses(Lkotlin/reflect/KClass;)Ljava/util/List;";
            }
        }

    }

    private static final Iterable isSubclassOf$lambda$16(KProperty1 kProperty10, KClass kClass0) {
        Intrinsics.checkNotNullParameter(kProperty10, "$tmp0");
        return (Iterable)kProperty10.invoke(kClass0);
    }

    public static final boolean isSuperclassOf(KClass kClass0, KClass kClass1) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        Intrinsics.checkNotNullParameter(kClass1, "derived");
        return KClasses.isSubclassOf(kClass1, kClass0);
    }

    public static final Object safeCast(KClass kClass0, Object object0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        if(kClass0.isInstance(object0)) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type T of kotlin.reflect.full.KClasses.safeCast");
            return object0;
        }
        return null;
    }
}

