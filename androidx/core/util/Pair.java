package androidx.core.util;

public class Pair {
    public final Object first;
    public final Object second;

    public Pair(Object object0, Object object1) {
        this.first = object0;
        this.second = object1;
    }

    public static Pair create(Object object0, Object object1) {
        return new Pair(object0, object1);
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Pair ? ObjectsCompat.equals(((Pair)object0).first, this.first) && ObjectsCompat.equals(((Pair)object0).second, this.second) : false;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.first == null ? 0 : this.first.hashCode();
        Object object0 = this.second;
        if(object0 != null) {
            v = object0.hashCode();
        }
        return v1 ^ v;
    }

    @Override
    public String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }
}

