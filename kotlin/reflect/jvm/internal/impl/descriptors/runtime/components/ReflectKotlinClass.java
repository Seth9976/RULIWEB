package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.MemberVisitor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.ReadKotlinClassHeaderAnnotationVisitor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.text.StringsKt;

public final class ReflectKotlinClass implements KotlinJvmBinaryClass {
    public static final class Factory {
        private Factory() {
        }

        public Factory(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ReflectKotlinClass create(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "klass");
            ReadKotlinClassHeaderAnnotationVisitor readKotlinClassHeaderAnnotationVisitor0 = new ReadKotlinClassHeaderAnnotationVisitor();
            ReflectClassStructure.INSTANCE.loadClassAnnotations(class0, readKotlinClassHeaderAnnotationVisitor0);
            KotlinClassHeader kotlinClassHeader0 = readKotlinClassHeaderAnnotationVisitor0.createHeaderWithDefaultMetadataVersion();
            return kotlinClassHeader0 == null ? null : new ReflectKotlinClass(class0, kotlinClassHeader0, null);
        }
    }

    public static final Factory Factory;
    private final KotlinClassHeader classHeader;
    private final Class klass;

    static {
        ReflectKotlinClass.Factory = new Factory(null);
    }

    private ReflectKotlinClass(Class class0, KotlinClassHeader kotlinClassHeader0) {
        this.klass = class0;
        this.classHeader = kotlinClassHeader0;
    }

    public ReflectKotlinClass(Class class0, KotlinClassHeader kotlinClassHeader0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(class0, kotlinClassHeader0);
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ReflectKotlinClass && Intrinsics.areEqual(this.klass, ((ReflectKotlinClass)object0).klass);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public KotlinClassHeader getClassHeader() {
        return this.classHeader;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public ClassId getClassId() {
        return ReflectClassUtilKt.getClassId(this.klass);
    }

    public final Class getKlass() {
        return this.klass;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public String getLocation() {
        String s = this.klass.getName();
        Intrinsics.checkNotNullExpressionValue(s, "klass.name");
        return StringsKt.replace$default(s, '.', '/', false, 4, null) + ".class";
    }

    @Override
    public int hashCode() {
        return this.klass.hashCode();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public void loadClassAnnotations(AnnotationVisitor kotlinJvmBinaryClass$AnnotationVisitor0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass$AnnotationVisitor0, "visitor");
        ReflectClassStructure.INSTANCE.loadClassAnnotations(this.klass, kotlinJvmBinaryClass$AnnotationVisitor0);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + this.klass;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass
    public void visitMembers(MemberVisitor kotlinJvmBinaryClass$MemberVisitor0, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass$MemberVisitor0, "visitor");
        ReflectClassStructure.INSTANCE.visitMembers(this.klass, kotlinJvmBinaryClass$MemberVisitor0);
    }
}

