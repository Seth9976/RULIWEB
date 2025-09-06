package com.airbnb.lottie.model;

import androidx.core.util.Pair;

public class MutablePair {
    Object first;
    Object second;

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Pair ? MutablePair.objectsEqual(((Pair)object0).first, this.first) && MutablePair.objectsEqual(((Pair)object0).second, this.second) : false;
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

    // 去混淆评级： 低(20)
    private static boolean objectsEqual(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }

    public void set(Object object0, Object object1) {
        this.first = object0;
        this.second = object1;
    }

    @Override
    public String toString() {
        return "Pair{" + this.first + " " + this.second + "}";
    }
}

