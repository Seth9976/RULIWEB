package kotlin.jvm.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.KVariance;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001B\n\u0002\b\u000E\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B%\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tB/\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u000B\u001A\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\"\u001A\u00020\u001E2\u0006\u0010#\u001A\u00020\bH\u0002J\u0013\u0010$\u001A\u00020\b2\b\u0010%\u001A\u0004\u0018\u00010&H\u0096\u0002J\b\u0010\'\u001A\u00020\fH\u0016J\b\u0010(\u001A\u00020\u001EH\u0016J\f\u0010\"\u001A\u00020\u001E*\u00020\u0006H\u0002R\u001A\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u000F0\u00058VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u001A\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0002\u001A\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u001C\u0010\u000B\u001A\u00020\f8\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001A\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0007\u001A\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\u0019R\u001E\u0010\n\u001A\u0004\u0018\u00010\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u001A\u0010\u0016\u001A\u0004\b\u001B\u0010\u001CR\u001C\u0010\u001D\u001A\u00020\u001E*\u0006\u0012\u0002\b\u00030\u001F8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b \u0010!¨\u0006*"}, d2 = {"Lkotlin/jvm/internal/TypeReference;", "Lkotlin/reflect/KType;", "classifier", "Lkotlin/reflect/KClassifier;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "isMarkedNullable", "", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Z)V", "platformTypeUpperBound", "flags", "", "(Lkotlin/reflect/KClassifier;Ljava/util/List;Lkotlin/reflect/KType;I)V", "annotations", "", "getAnnotations", "()Ljava/util/List;", "getArguments", "getClassifier", "()Lkotlin/reflect/KClassifier;", "getFlags$kotlin_stdlib$annotations", "()V", "getFlags$kotlin_stdlib", "()I", "()Z", "getPlatformTypeUpperBound$kotlin_stdlib$annotations", "getPlatformTypeUpperBound$kotlin_stdlib", "()Lkotlin/reflect/KType;", "arrayClassName", "", "Ljava/lang/Class;", "getArrayClassName", "(Ljava/lang/Class;)Ljava/lang/String;", "asString", "convertPrimitiveToWrapper", "equals", "other", "", "hashCode", "toString", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class TypeReference implements KType {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/jvm/internal/TypeReference$Companion;", "", "()V", "IS_MARKED_NULLABLE", "", "IS_MUTABLE_COLLECTION_TYPE", "IS_NOTHING_TYPE", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[KVariance.values().length];
            try {
                arr_v[KVariance.INVARIANT.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[KVariance.IN.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[KVariance.OUT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Companion Companion = null;
    public static final int IS_MARKED_NULLABLE = 1;
    public static final int IS_MUTABLE_COLLECTION_TYPE = 2;
    public static final int IS_NOTHING_TYPE = 4;
    private final List arguments;
    private final KClassifier classifier;
    private final int flags;
    private final KType platformTypeUpperBound;

    static {
        TypeReference.Companion = new Companion(null);
    }

    public TypeReference(KClassifier kClassifier0, List list0, KType kType0, int v) {
        Intrinsics.checkNotNullParameter(kClassifier0, "classifier");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        super();
        this.classifier = kClassifier0;
        this.arguments = list0;
        this.platformTypeUpperBound = kType0;
        this.flags = v;
    }

    public TypeReference(KClassifier kClassifier0, List list0, boolean z) {
        Intrinsics.checkNotNullParameter(kClassifier0, "classifier");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        this(kClassifier0, list0, null, ((int)z));
    }

    private final String asString(KTypeProjection kTypeProjection0) {
        String s;
        if(kTypeProjection0.getVariance() == null) {
            return "*";
        }
        KType kType0 = kTypeProjection0.getType();
        TypeReference typeReference0 = kType0 instanceof TypeReference ? ((TypeReference)kType0) : null;
        if(typeReference0 == null) {
            s = String.valueOf(kTypeProjection0.getType());
        }
        else {
            s = typeReference0.asString(true);
            if(s == null) {
                s = String.valueOf(kTypeProjection0.getType());
            }
        }
        switch(WhenMappings.$EnumSwitchMapping$0[kTypeProjection0.getVariance().ordinal()]) {
            case 1: {
                return s;
            }
            case 2: {
                return "in " + s;
            }
            case 3: {
                return "out " + s;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private final String asString(boolean z) {
        String s;
        KClassifier kClassifier0 = this.getClassifier();
        Class class0 = null;
        KClass kClass0 = kClassifier0 instanceof KClass ? ((KClass)kClassifier0) : null;
        if(kClass0 != null) {
            class0 = JvmClassMappingKt.getJavaClass(kClass0);
        }
        if(class0 == null) {
            s = this.getClassifier().toString();
        }
        else if((this.flags & 4) != 0) {
            s = "kotlin.Nothing";
        }
        else if(class0.isArray()) {
            s = this.getArrayClassName(class0);
        }
        else if(!z || !class0.isPrimitive()) {
            s = class0.getName();
        }
        else {
            KClassifier kClassifier1 = this.getClassifier();
            Intrinsics.checkNotNull(kClassifier1, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
            s = JvmClassMappingKt.getJavaObjectType(((KClass)kClassifier1)).getName();
        }
        String s1 = "";
        String s2 = this.getArguments().isEmpty() ? "" : CollectionsKt.joinToString$default(this.getArguments(), ", ", "<", ">", 0, null, new Function1() {
            {
                TypeReference.this = typeReference0;
                super(1);
            }

            public final CharSequence invoke(KTypeProjection kTypeProjection0) {
                Intrinsics.checkNotNullParameter(kTypeProjection0, "it");
                return TypeReference.this.asString(kTypeProjection0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KTypeProjection)object0));
            }
        }, 24, null);
        if(this.isMarkedNullable()) {
            s1 = "?";
        }
        String s3 = s + s2 + s1;
        KType kType0 = this.platformTypeUpperBound;
        if(kType0 instanceof TypeReference) {
            String s4 = ((TypeReference)kType0).asString(true);
            if(!Intrinsics.areEqual(s4, s3)) {
                return Intrinsics.areEqual(s4, s3 + '?') ? s3 + '!' : "(" + s3 + ".." + s4 + ')';
            }
        }
        return s3;
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof TypeReference && Intrinsics.areEqual(this.getClassifier(), ((TypeReference)object0).getClassifier()) && Intrinsics.areEqual(this.getArguments(), ((TypeReference)object0).getArguments()) && Intrinsics.areEqual(this.platformTypeUpperBound, ((TypeReference)object0).platformTypeUpperBound) && this.flags == ((TypeReference)object0).flags;
    }

    @Override  // kotlin.reflect.KAnnotatedElement
    public List getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.KType
    public List getArguments() {
        return this.arguments;
    }

    private final String getArrayClassName(Class class0) {
        if(Intrinsics.areEqual(class0, boolean[].class)) {
            return "kotlin.BooleanArray";
        }
        if(Intrinsics.areEqual(class0, char[].class)) {
            return "kotlin.CharArray";
        }
        if(Intrinsics.areEqual(class0, byte[].class)) {
            return "kotlin.ByteArray";
        }
        if(Intrinsics.areEqual(class0, short[].class)) {
            return "kotlin.ShortArray";
        }
        if(Intrinsics.areEqual(class0, int[].class)) {
            return "kotlin.IntArray";
        }
        if(Intrinsics.areEqual(class0, float[].class)) {
            return "kotlin.FloatArray";
        }
        if(Intrinsics.areEqual(class0, long[].class)) {
            return "kotlin.LongArray";
        }
        return Intrinsics.areEqual(class0, double[].class) ? "kotlin.DoubleArray" : "kotlin.Array";
    }

    @Override  // kotlin.reflect.KType
    public KClassifier getClassifier() {
        return this.classifier;
    }

    public final int getFlags$kotlin_stdlib() {
        return this.flags;
    }

    public static void getFlags$kotlin_stdlib$annotations() {
    }

    public final KType getPlatformTypeUpperBound$kotlin_stdlib() {
        return this.platformTypeUpperBound;
    }

    public static void getPlatformTypeUpperBound$kotlin_stdlib$annotations() {
    }

    @Override
    public int hashCode() {
        return (this.getClassifier().hashCode() * 0x1F + this.getArguments().hashCode()) * 0x1F + this.flags;
    }

    @Override  // kotlin.reflect.KType
    public boolean isMarkedNullable() {
        return (this.flags & 1) != 0;
    }

    @Override
    public String toString() {
        return this.asString(false) + " (Kotlin reflection is not available)";
    }
}

