package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;

public final class JvmBuiltInsSignatures {
    private static final Set DROP_LIST_METHOD_SIGNATURES;
    private static final Set HIDDEN_CONSTRUCTOR_SIGNATURES;
    private static final Set HIDDEN_METHOD_SIGNATURES;
    public static final JvmBuiltInsSignatures INSTANCE;
    private static final Set MUTABLE_METHOD_SIGNATURES;
    private static final Set VISIBLE_CONSTRUCTOR_SIGNATURES;
    private static final Set VISIBLE_METHOD_SIGNATURES;

    static {
        JvmBuiltInsSignatures jvmBuiltInsSignatures0 = new JvmBuiltInsSignatures();
        JvmBuiltInsSignatures.INSTANCE = jvmBuiltInsSignatures0;
        JvmBuiltInsSignatures.DROP_LIST_METHOD_SIGNATURES = SetsKt.plus(SignatureBuildingComponents.INSTANCE.inJavaUtil("Collection", new String[]{"toArray()[Ljava/lang/Object;", "toArray([Ljava/lang/Object;)[Ljava/lang/Object;"}), "java/lang/annotation/Annotation.annotationType()Ljava/lang/Class;");
        JvmBuiltInsSignatures.HIDDEN_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(jvmBuiltInsSignatures0.buildPrimitiveValueMethodsSet(), SignatureBuildingComponents.INSTANCE.inJavaUtil("List", new String[]{"sort(Ljava/util/Comparator;)V"})), SignatureBuildingComponents.INSTANCE.inJavaLang("String", new String[]{"codePointAt(I)I", "codePointBefore(I)I", "codePointCount(II)I", "compareToIgnoreCase(Ljava/lang/String;)I", "concat(Ljava/lang/String;)Ljava/lang/String;", "contains(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/CharSequence;)Z", "contentEquals(Ljava/lang/StringBuffer;)Z", "endsWith(Ljava/lang/String;)Z", "equalsIgnoreCase(Ljava/lang/String;)Z", "getBytes()[B", "getBytes(II[BI)V", "getBytes(Ljava/lang/String;)[B", "getBytes(Ljava/nio/charset/Charset;)[B", "getChars(II[CI)V", "indexOf(I)I", "indexOf(II)I", "indexOf(Ljava/lang/String;)I", "indexOf(Ljava/lang/String;I)I", "intern()Ljava/lang/String;", "isEmpty()Z", "lastIndexOf(I)I", "lastIndexOf(II)I", "lastIndexOf(Ljava/lang/String;)I", "lastIndexOf(Ljava/lang/String;I)I", "matches(Ljava/lang/String;)Z", "offsetByCodePoints(II)I", "regionMatches(ILjava/lang/String;II)Z", "regionMatches(ZILjava/lang/String;II)Z", "replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(CC)Ljava/lang/String;", "replaceFirst(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;", "split(Ljava/lang/String;I)[Ljava/lang/String;", "split(Ljava/lang/String;)[Ljava/lang/String;", "startsWith(Ljava/lang/String;I)Z", "startsWith(Ljava/lang/String;)Z", "substring(II)Ljava/lang/String;", "substring(I)Ljava/lang/String;", "toCharArray()[C", "toLowerCase()Ljava/lang/String;", "toLowerCase(Ljava/util/Locale;)Ljava/lang/String;", "toUpperCase()Ljava/lang/String;", "toUpperCase(Ljava/util/Locale;)Ljava/lang/String;", "trim()Ljava/lang/String;", "isBlank()Z", "lines()Ljava/util/stream/Stream;", "repeat(I)Ljava/lang/String;"})), SignatureBuildingComponents.INSTANCE.inJavaLang("Double", new String[]{"isInfinite()Z", "isNaN()Z"})), SignatureBuildingComponents.INSTANCE.inJavaLang("Float", new String[]{"isInfinite()Z", "isNaN()Z"})), SignatureBuildingComponents.INSTANCE.inJavaLang("Enum", new String[]{"getDeclaringClass()Ljava/lang/Class;", "finalize()V"})), SignatureBuildingComponents.INSTANCE.inJavaLang("CharSequence", new String[]{"isEmpty()Z"}));
        JvmBuiltInsSignatures.VISIBLE_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(SignatureBuildingComponents.INSTANCE.inJavaLang("CharSequence", new String[]{"codePoints()Ljava/util/stream/IntStream;", "chars()Ljava/util/stream/IntStream;"}), SignatureBuildingComponents.INSTANCE.inJavaUtil("Iterator", new String[]{"forEachRemaining(Ljava/util/function/Consumer;)V"})), SignatureBuildingComponents.INSTANCE.inJavaLang("Iterable", new String[]{"forEach(Ljava/util/function/Consumer;)V", "spliterator()Ljava/util/Spliterator;"})), SignatureBuildingComponents.INSTANCE.inJavaLang("Throwable", new String[]{"setStackTrace([Ljava/lang/StackTraceElement;)V", "fillInStackTrace()Ljava/lang/Throwable;", "getLocalizedMessage()Ljava/lang/String;", "printStackTrace()V", "printStackTrace(Ljava/io/PrintStream;)V", "printStackTrace(Ljava/io/PrintWriter;)V", "getStackTrace()[Ljava/lang/StackTraceElement;", "initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;", "getSuppressed()[Ljava/lang/Throwable;", "addSuppressed(Ljava/lang/Throwable;)V"})), SignatureBuildingComponents.INSTANCE.inJavaUtil("Collection", new String[]{"spliterator()Ljava/util/Spliterator;", "parallelStream()Ljava/util/stream/Stream;", "stream()Ljava/util/stream/Stream;", "removeIf(Ljava/util/function/Predicate;)Z"})), SignatureBuildingComponents.INSTANCE.inJavaUtil("List", new String[]{"replaceAll(Ljava/util/function/UnaryOperator;)V"})), SignatureBuildingComponents.INSTANCE.inJavaUtil("Map", new String[]{"getOrDefault(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "forEach(Ljava/util/function/BiConsumer;)V", "replaceAll(Ljava/util/function/BiFunction;)V", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;"}));
        JvmBuiltInsSignatures.MUTABLE_METHOD_SIGNATURES = SetsKt.plus(SetsKt.plus(SignatureBuildingComponents.INSTANCE.inJavaUtil("Collection", new String[]{"removeIf(Ljava/util/function/Predicate;)Z"}), SignatureBuildingComponents.INSTANCE.inJavaUtil("List", new String[]{"replaceAll(Ljava/util/function/UnaryOperator;)V", "sort(Ljava/util/Comparator;)V"})), SignatureBuildingComponents.INSTANCE.inJavaUtil("Map", new String[]{"computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;", "computeIfPresent(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "compute(Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "merge(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/function/BiFunction;)Ljava/lang/Object;", "putIfAbsent(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "remove(Ljava/lang/Object;Ljava/lang/Object;)Z", "replaceAll(Ljava/util/function/BiFunction;)V", "replace(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "replace(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z"}));
        Set set0 = jvmBuiltInsSignatures0.buildPrimitiveStringConstructorsSet();
        String[] arr_s = SignatureBuildingComponents.INSTANCE.constructors(new String[]{"D"});
        String[] arr_s1 = (String[])Arrays.copyOf(arr_s, arr_s.length);
        Set set1 = SetsKt.plus(set0, SignatureBuildingComponents.INSTANCE.inJavaLang("Float", arr_s1));
        String[] arr_s2 = SignatureBuildingComponents.INSTANCE.constructors(new String[]{"[C", "[CII", "[III", "[BIILjava/lang/String;", "[BIILjava/nio/charset/Charset;", "[BLjava/lang/String;", "[BLjava/nio/charset/Charset;", "[BII", "[B", "Ljava/lang/StringBuffer;", "Ljava/lang/StringBuilder;"});
        String[] arr_s3 = (String[])Arrays.copyOf(arr_s2, arr_s2.length);
        JvmBuiltInsSignatures.HIDDEN_CONSTRUCTOR_SIGNATURES = SetsKt.plus(set1, SignatureBuildingComponents.INSTANCE.inJavaLang("String", arr_s3));
        String[] arr_s4 = SignatureBuildingComponents.INSTANCE.constructors(new String[]{"Ljava/lang/String;Ljava/lang/Throwable;ZZ"});
        String[] arr_s5 = (String[])Arrays.copyOf(arr_s4, arr_s4.length);
        JvmBuiltInsSignatures.VISIBLE_CONSTRUCTOR_SIGNATURES = SignatureBuildingComponents.INSTANCE.inJavaLang("Throwable", arr_s5);
    }

    private final Set buildPrimitiveStringConstructorsSet() {
        SignatureBuildingComponents signatureBuildingComponents0 = SignatureBuildingComponents.INSTANCE;
        Iterable iterable0 = CollectionsKt.listOf(new JvmPrimitiveType[]{JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.BYTE, JvmPrimitiveType.DOUBLE, JvmPrimitiveType.FLOAT, JvmPrimitiveType.BYTE, JvmPrimitiveType.INT, JvmPrimitiveType.LONG, JvmPrimitiveType.SHORT});
        Collection collection0 = new LinkedHashSet();
        for(Object object0: iterable0) {
            String s = ((JvmPrimitiveType)object0).getWrapperFqName().shortName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "it.wrapperFqName.shortName().asString()");
            String[] arr_s = signatureBuildingComponents0.constructors(new String[]{"Ljava/lang/String;"});
            CollectionsKt.addAll(collection0, signatureBuildingComponents0.inJavaLang(s, ((String[])Arrays.copyOf(arr_s, arr_s.length))));
        }
        return (LinkedHashSet)collection0;
    }

    private final Set buildPrimitiveValueMethodsSet() {
        SignatureBuildingComponents signatureBuildingComponents0 = SignatureBuildingComponents.INSTANCE;
        Iterable iterable0 = CollectionsKt.listOf(new JvmPrimitiveType[]{JvmPrimitiveType.BOOLEAN, JvmPrimitiveType.CHAR});
        Collection collection0 = new LinkedHashSet();
        for(Object object0: iterable0) {
            String s = ((JvmPrimitiveType)object0).getWrapperFqName().shortName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "it.wrapperFqName.shortName().asString()");
            CollectionsKt.addAll(collection0, signatureBuildingComponents0.inJavaLang(s, new String[]{((JvmPrimitiveType)object0).getJavaKeywordName() + "Value()" + ((JvmPrimitiveType)object0).getDesc()}));
        }
        return (LinkedHashSet)collection0;
    }

    public final Set getDROP_LIST_METHOD_SIGNATURES() {
        return JvmBuiltInsSignatures.DROP_LIST_METHOD_SIGNATURES;
    }

    public final Set getHIDDEN_CONSTRUCTOR_SIGNATURES() {
        return JvmBuiltInsSignatures.HIDDEN_CONSTRUCTOR_SIGNATURES;
    }

    public final Set getHIDDEN_METHOD_SIGNATURES() {
        return JvmBuiltInsSignatures.HIDDEN_METHOD_SIGNATURES;
    }

    public final Set getMUTABLE_METHOD_SIGNATURES() {
        return JvmBuiltInsSignatures.MUTABLE_METHOD_SIGNATURES;
    }

    public final Set getVISIBLE_CONSTRUCTOR_SIGNATURES() {
        return JvmBuiltInsSignatures.VISIBLE_CONSTRUCTOR_SIGNATURES;
    }

    public final Set getVISIBLE_METHOD_SIGNATURES() {
        return JvmBuiltInsSignatures.VISIBLE_METHOD_SIGNATURES;
    }

    public final boolean isArrayOrPrimitiveArray(FqNameUnsafe fqNameUnsafe0) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe0, "fqName");
        return Intrinsics.areEqual(fqNameUnsafe0, FqNames.array) || StandardNames.isPrimitiveArray(fqNameUnsafe0);
    }

    public final boolean isSerializableInJava(FqNameUnsafe fqNameUnsafe0) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe0, "fqName");
        if(this.isArrayOrPrimitiveArray(fqNameUnsafe0)) {
            return true;
        }
        ClassId classId0 = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqNameUnsafe0);
        if(classId0 == null) {
            return false;
        }
        try {
            Class class0 = Class.forName(classId0.asSingleFqName().asString());
            return Serializable.class.isAssignableFrom(class0);
        }
        catch(ClassNotFoundException unused_ex) {
            return false;
        }
    }
}

