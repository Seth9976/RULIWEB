package kotlin.reflect.jvm.internal;

class Util {
    public static Object getEnumConstantByName(Class class0, String s) {
        return Enum.valueOf(class0, s);
    }
}

