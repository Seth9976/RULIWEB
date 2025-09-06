package com.google.firebase.components;

public final class Dependency {
    private final Qualified anInterface;
    private final int injection;
    private final int type;

    private Dependency(Qualified qualified0, int v, int v1) {
        this.anInterface = (Qualified)Preconditions.checkNotNull(qualified0, "Null dependency anInterface.");
        this.type = v;
        this.injection = v1;
    }

    private Dependency(Class class0, int v, int v1) {
        this(Qualified.unqualified(class0), v, v1);
    }

    public static Dependency deferred(Qualified qualified0) {
        return new Dependency(qualified0, 0, 2);
    }

    public static Dependency deferred(Class class0) {
        return new Dependency(class0, 0, 2);
    }

    private static String describeInjection(int v) {
        switch(v) {
            case 0: {
                return "direct";
            }
            case 1: {
                return "provider";
            }
            case 2: {
                return "deferred";
            }
            default: {
                throw new AssertionError("Unsupported injection: " + v);
            }
        }
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Dependency && this.anInterface.equals(((Dependency)object0).anInterface) && this.type == ((Dependency)object0).type && this.injection == ((Dependency)object0).injection;
    }

    public Qualified getInterface() {
        return this.anInterface;
    }

    @Override
    public int hashCode() {
        return ((this.anInterface.hashCode() ^ 1000003) * 1000003 ^ this.type) * 1000003 ^ this.injection;
    }

    public boolean isDeferred() {
        return this.injection == 2;
    }

    public boolean isDirectInjection() {
        return this.injection == 0;
    }

    public boolean isRequired() {
        return this.type == 1;
    }

    public boolean isSet() {
        return this.type == 2;
    }

    @Deprecated
    public static Dependency optional(Class class0) {
        return new Dependency(class0, 0, 0);
    }

    public static Dependency optionalProvider(Qualified qualified0) {
        return new Dependency(qualified0, 0, 1);
    }

    public static Dependency optionalProvider(Class class0) {
        return new Dependency(class0, 0, 1);
    }

    public static Dependency required(Qualified qualified0) {
        return new Dependency(qualified0, 1, 0);
    }

    public static Dependency required(Class class0) {
        return new Dependency(class0, 1, 0);
    }

    public static Dependency requiredProvider(Qualified qualified0) {
        return new Dependency(qualified0, 1, 1);
    }

    public static Dependency requiredProvider(Class class0) {
        return new Dependency(class0, 1, 1);
    }

    public static Dependency setOf(Qualified qualified0) {
        return new Dependency(qualified0, 2, 0);
    }

    public static Dependency setOf(Class class0) {
        return new Dependency(class0, 2, 0);
    }

    public static Dependency setOfProvider(Qualified qualified0) {
        return new Dependency(qualified0, 2, 1);
    }

    public static Dependency setOfProvider(Class class0) {
        return new Dependency(class0, 2, 1);
    }

    @Override
    public String toString() {
        String s;
        StringBuilder stringBuilder0 = new StringBuilder("Dependency{anInterface=");
        stringBuilder0.append(this.anInterface);
        stringBuilder0.append(", type=");
        int v = this.type;
        if(v == 1) {
            s = "required";
        }
        else {
            s = v == 0 ? "optional" : "set";
        }
        stringBuilder0.append(s);
        stringBuilder0.append(", injection=");
        stringBuilder0.append(Dependency.describeInjection(this.injection));
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }
}

