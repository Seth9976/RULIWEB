package kotlin.reflect.jvm.internal.calls;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0002 !B?\u0012\n\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B\u0012\u000E\b\u0002\u0010\f\u001A\b\u0012\u0004\u0012\u00020\r0\u0006¢\u0006\u0002\u0010\u000EJ\u001B\u0010\u001C\u001A\u0004\u0018\u00010\u00102\n\u0010\u001D\u001A\u0006\u0012\u0002\b\u00030\u001EH\u0016¢\u0006\u0002\u0010\u001FR\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000F\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0003\u001A\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001A\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014R\u0014\u0010\f\u001A\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00160\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001A\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u001A\u0010\u001B¨\u0006\""}, d2 = {"Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller;", "Lkotlin/reflect/jvm/internal/calls/Caller;", "", "jClass", "Ljava/lang/Class;", "parameterNames", "", "", "callMode", "Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$CallMode;", "origin", "Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$Origin;", "methods", "Ljava/lang/reflect/Method;", "(Ljava/lang/Class;Ljava/util/List;Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$CallMode;Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$Origin;Ljava/util/List;)V", "defaultValues", "", "erasedParameterTypes", "member", "getMember", "()Ljava/lang/Void;", "parameterTypes", "Ljava/lang/reflect/Type;", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "()Ljava/lang/reflect/Type;", "call", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "CallMode", "Origin", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AnnotationConstructorCaller implements Caller {
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$CallMode;", "", "(Ljava/lang/String;I)V", "CALL_BY_NAME", "POSITIONAL_CALL", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum CallMode {
        CALL_BY_NAME,
        POSITIONAL_CALL;

        private static final CallMode[] $values() [...] // Inlined contents
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/reflect/jvm/internal/calls/AnnotationConstructorCaller$Origin;", "", "(Ljava/lang/String;I)V", "JAVA", "KOTLIN", "kotlin-reflection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum Origin {
        JAVA,
        KOTLIN;

        private static final Origin[] $values() [...] // Inlined contents
    }

    private final CallMode callMode;
    private final List defaultValues;
    private final List erasedParameterTypes;
    private final Class jClass;
    private final List methods;
    private final List parameterNames;
    private final List parameterTypes;

    public AnnotationConstructorCaller(Class class0, List list0, CallMode annotationConstructorCaller$CallMode0, Origin annotationConstructorCaller$Origin0, List list1) {
        Intrinsics.checkNotNullParameter(class0, "jClass");
        Intrinsics.checkNotNullParameter(list0, "parameterNames");
        Intrinsics.checkNotNullParameter(annotationConstructorCaller$CallMode0, "callMode");
        Intrinsics.checkNotNullParameter(annotationConstructorCaller$Origin0, "origin");
        Intrinsics.checkNotNullParameter(list1, "methods");
        super();
        this.jClass = class0;
        this.parameterNames = list0;
        this.callMode = annotationConstructorCaller$CallMode0;
        this.methods = list1;
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
        for(Object object0: list1) {
            arrayList0.add(((Method)object0).getGenericReturnType());
        }
        this.parameterTypes = arrayList0;
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.methods, 10));
        for(Object object1: this.methods) {
            Class class1 = ((Method)object1).getReturnType();
            Intrinsics.checkNotNullExpressionValue(class1, "it");
            Class class2 = ReflectClassUtilKt.getWrapperByPrimitive(class1);
            if(class2 != null) {
                class1 = class2;
            }
            arrayList1.add(class1);
        }
        this.erasedParameterTypes = arrayList1;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.methods, 10));
        for(Object object2: this.methods) {
            arrayList2.add(((Method)object2).getDefaultValue());
        }
        this.defaultValues = arrayList2;
        if(this.callMode == CallMode.POSITIONAL_CALL && annotationConstructorCaller$Origin0 == Origin.JAVA && !CollectionsKt.minus(this.parameterNames, "value").isEmpty()) {
            throw new UnsupportedOperationException("Positional call of a Java annotation constructor is allowed only if there are no parameters or one parameter named \"value\". This restriction exists because Java annotations (in contrast to Kotlin)do not impose any order on their arguments. Use KCallable#callBy instead.");
        }
    }

    public AnnotationConstructorCaller(Class class0, List list0, CallMode annotationConstructorCaller$CallMode0, Origin annotationConstructorCaller$Origin0, List list1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 16) != 0) {
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                arrayList0.add(class0.getDeclaredMethod(((String)object0), null));
            }
            list1 = arrayList0;
        }
        this(class0, list0, annotationConstructorCaller$CallMode0, annotationConstructorCaller$Origin0, list1);
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Object call(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "args");
        this.checkArguments(arr_object);
        ArrayList arrayList0 = new ArrayList(arr_object.length);
        int v = 0;
        int v1 = 0;
        while(v < arr_object.length) {
            Object object0 = arr_object[v];
            Object object1 = object0 != null || this.callMode != CallMode.CALL_BY_NAME ? AnnotationConstructorCallerKt.access$transformKotlinToJvm(object0, ((Class)this.erasedParameterTypes.get(v1))) : this.defaultValues.get(v1);
            if(object1 != null) {
                arrayList0.add(object1);
                ++v;
                ++v1;
                continue;
            }
            AnnotationConstructorCallerKt.access$throwIllegalArgumentType(v1, ((String)this.parameterNames.get(v1)), ((Class)this.erasedParameterTypes.get(v1)));
            throw null;
        }
        Map map0 = MapsKt.toMap(CollectionsKt.zip(this.parameterNames, arrayList0));
        return AnnotationConstructorCallerKt.createAnnotationInstance(this.jClass, map0, this.methods);
    }

    public void checkArguments(Object[] arr_object) {
        DefaultImpls.checkArguments(this, arr_object);
    }

    public Void getMember() [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Member getMember() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public List getParameterTypes() {
        return this.parameterTypes;
    }

    @Override  // kotlin.reflect.jvm.internal.calls.Caller
    public Type getReturnType() {
        return this.jClass;
    }
}

