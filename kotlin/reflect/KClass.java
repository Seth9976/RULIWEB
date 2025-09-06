package kotlin.reflect;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001E\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005J\u0013\u0010@\u001A\u00020\f2\b\u0010A\u001A\u0004\u0018\u00010\u0002H\u00A6\u0002J\b\u0010B\u001A\u00020CH&J\u0012\u0010D\u001A\u00020\f2\b\u0010E\u001A\u0004\u0018\u00010\u0002H\'R\u001E\u0010\u0006\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\u001A\u0010\u000B\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\r\u0010\u000E\u001A\u0004\b\u000B\u0010\u000FR\u001A\u0010\u0010\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u0011\u0010\u000E\u001A\u0004\b\u0010\u0010\u000FR\u001A\u0010\u0012\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u0013\u0010\u000E\u001A\u0004\b\u0012\u0010\u000FR\u001A\u0010\u0014\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u0015\u0010\u000E\u001A\u0004\b\u0014\u0010\u000FR\u001A\u0010\u0016\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u0017\u0010\u000E\u001A\u0004\b\u0016\u0010\u000FR\u001A\u0010\u0018\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u0019\u0010\u000E\u001A\u0004\b\u0018\u0010\u000FR\u001A\u0010\u001A\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u001B\u0010\u000E\u001A\u0004\b\u001A\u0010\u000FR\u001A\u0010\u001C\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u001D\u0010\u000E\u001A\u0004\b\u001C\u0010\u000FR\u001A\u0010\u001E\u001A\u00020\f8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b\u001F\u0010\u000E\u001A\u0004\b\u001E\u0010\u000FR\u001C\u0010 \u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0\u0007X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\"\u0010\nR\u001C\u0010#\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u0007X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b$\u0010\nR\u0014\u0010%\u001A\u0004\u0018\u00018\u0000X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b&\u0010\'R\u0014\u0010(\u001A\u0004\u0018\u00010)X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b*\u0010+R(\u0010,\u001A\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00000-8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b.\u0010\u000E\u001A\u0004\b/\u00100R\u0014\u00101\u001A\u0004\u0018\u00010)X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b2\u0010+R \u00103\u001A\b\u0012\u0004\u0012\u0002040-8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b5\u0010\u000E\u001A\u0004\b6\u00100R \u00107\u001A\b\u0012\u0004\u0012\u0002080-8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b9\u0010\u000E\u001A\u0004\b:\u00100R\u001C\u0010;\u001A\u0004\u0018\u00010<8&X\u00A7\u0004\u00A2\u0006\f\u0012\u0004\b=\u0010\u000E\u001A\u0004\b>\u0010?\u00A8\u0006F"}, d2 = {"Lkotlin/reflect/KClass;", "T", "", "Lkotlin/reflect/KDeclarationContainer;", "Lkotlin/reflect/KAnnotatedElement;", "Lkotlin/reflect/KClassifier;", "constructors", "", "Lkotlin/reflect/KFunction;", "getConstructors", "()Ljava/util/Collection;", "isAbstract", "", "isAbstract$annotations", "()V", "()Z", "isCompanion", "isCompanion$annotations", "isData", "isData$annotations", "isFinal", "isFinal$annotations", "isFun", "isFun$annotations", "isInner", "isInner$annotations", "isOpen", "isOpen$annotations", "isSealed", "isSealed$annotations", "isValue", "isValue$annotations", "members", "Lkotlin/reflect/KCallable;", "getMembers", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "qualifiedName", "", "getQualifiedName", "()Ljava/lang/String;", "sealedSubclasses", "", "getSealedSubclasses$annotations", "getSealedSubclasses", "()Ljava/util/List;", "simpleName", "getSimpleName", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "equals", "other", "hashCode", "", "isInstance", "value", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public interface KClass extends KAnnotatedElement, KClassifier, KDeclarationContainer {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static void getSealedSubclasses$annotations() {
        }

        public static void getSupertypes$annotations() {
        }

        public static void getTypeParameters$annotations() {
        }

        public static void getVisibility$annotations() {
        }

        public static void isAbstract$annotations() {
        }

        public static void isCompanion$annotations() {
        }

        public static void isData$annotations() {
        }

        public static void isFinal$annotations() {
        }

        public static void isFun$annotations() {
        }

        public static void isInner$annotations() {
        }

        public static void isOpen$annotations() {
        }

        public static void isSealed$annotations() {
        }

        public static void isValue$annotations() {
        }
    }

    @Override
    boolean equals(Object arg1);

    Collection getConstructors();

    @Override  // kotlin.reflect.KDeclarationContainer
    Collection getMembers();

    Collection getNestedClasses();

    Object getObjectInstance();

    String getQualifiedName();

    List getSealedSubclasses();

    String getSimpleName();

    List getSupertypes();

    List getTypeParameters();

    KVisibility getVisibility();

    @Override
    int hashCode();

    boolean isAbstract();

    boolean isCompanion();

    boolean isData();

    boolean isFinal();

    boolean isFun();

    boolean isInner();

    boolean isInstance(Object arg1);

    boolean isOpen();

    boolean isSealed();

    boolean isValue();
}

