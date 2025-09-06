package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ClassLiteralValue;

public interface KotlinJvmBinaryClass {
    public interface AnnotationArgumentVisitor {
        void visit(Name arg1, Object arg2);

        AnnotationArgumentVisitor visitAnnotation(Name arg1, ClassId arg2);

        AnnotationArrayArgumentVisitor visitArray(Name arg1);

        void visitClassLiteral(Name arg1, ClassLiteralValue arg2);

        void visitEnd();

        void visitEnum(Name arg1, ClassId arg2, Name arg3);
    }

    public interface AnnotationArrayArgumentVisitor {
        void visit(Object arg1);

        AnnotationArgumentVisitor visitAnnotation(ClassId arg1);

        void visitClassLiteral(ClassLiteralValue arg1);

        void visitEnd();

        void visitEnum(ClassId arg1, Name arg2);
    }

    public interface AnnotationVisitor {
        AnnotationArgumentVisitor visitAnnotation(ClassId arg1, SourceElement arg2);

        void visitEnd();
    }

    public interface MemberVisitor {
        AnnotationVisitor visitField(Name arg1, String arg2, Object arg3);

        MethodAnnotationVisitor visitMethod(Name arg1, String arg2);
    }

    public interface MethodAnnotationVisitor extends AnnotationVisitor {
        AnnotationArgumentVisitor visitParameterAnnotation(int arg1, ClassId arg2, SourceElement arg3);
    }

    KotlinClassHeader getClassHeader();

    ClassId getClassId();

    String getLocation();

    void loadClassAnnotations(AnnotationVisitor arg1, byte[] arg2);

    void visitMembers(MemberVisitor arg1, byte[] arg2);
}

