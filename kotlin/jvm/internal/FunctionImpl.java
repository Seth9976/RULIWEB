package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.DeprecationLevel;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function10;
import kotlin.jvm.functions.Function11;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.functions.Function13;
import kotlin.jvm.functions.Function14;
import kotlin.jvm.functions.Function15;
import kotlin.jvm.functions.Function16;
import kotlin.jvm.functions.Function17;
import kotlin.jvm.functions.Function18;
import kotlin.jvm.functions.Function19;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function20;
import kotlin.jvm.functions.Function21;
import kotlin.jvm.functions.Function22;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;

@Deprecated
@kotlin.Deprecated(level = DeprecationLevel.ERROR, message = "This class is no longer supported, do not use it.")
public abstract class FunctionImpl implements Serializable, Function, Function0, Function10, Function11, Function12, Function13, Function14, Function15, Function16, Function17, Function18, Function19, Function1, Function20, Function21, Function22, Function2, Function3, Function4, Function5, Function6, Function7, Function8, Function9 {
    private void checkArity(int v) {
        if(this.getArity() != v) {
            this.throwWrongArity(v);
        }
    }

    public abstract int getArity();

    @Override  // kotlin.jvm.functions.Function0
    public Object invoke() {
        this.checkArity(0);
        return this.invokeVararg(new Object[0]);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        this.checkArity(1);
        return this.invokeVararg(new Object[]{object0});
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        this.checkArity(2);
        return this.invokeVararg(new Object[]{object0, object1});
    }

    @Override  // kotlin.jvm.functions.Function3
    public Object invoke(Object object0, Object object1, Object object2) {
        this.checkArity(3);
        return this.invokeVararg(new Object[]{object0, object1, object2});
    }

    @Override  // kotlin.jvm.functions.Function4
    public Object invoke(Object object0, Object object1, Object object2, Object object3) {
        this.checkArity(4);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3});
    }

    @Override  // kotlin.jvm.functions.Function5
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4) {
        this.checkArity(5);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4});
    }

    @Override  // kotlin.jvm.functions.Function6
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5) {
        this.checkArity(6);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5});
    }

    @Override  // kotlin.jvm.functions.Function7
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6) {
        this.checkArity(7);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6});
    }

    @Override  // kotlin.jvm.functions.Function8
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7) {
        this.checkArity(8);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7});
    }

    @Override  // kotlin.jvm.functions.Function9
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8) {
        this.checkArity(9);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8});
    }

    @Override  // kotlin.jvm.functions.Function10
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9) {
        this.checkArity(10);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9});
    }

    @Override  // kotlin.jvm.functions.Function11
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10) {
        this.checkArity(11);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10});
    }

    @Override  // kotlin.jvm.functions.Function12
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11) {
        this.checkArity(12);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11});
    }

    @Override  // kotlin.jvm.functions.Function13
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12) {
        this.checkArity(13);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12});
    }

    @Override  // kotlin.jvm.functions.Function14
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13) {
        this.checkArity(14);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13});
    }

    @Override  // kotlin.jvm.functions.Function15
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14) {
        this.checkArity(15);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14});
    }

    @Override  // kotlin.jvm.functions.Function16
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15) {
        this.checkArity(16);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15});
    }

    @Override  // kotlin.jvm.functions.Function17
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16) {
        this.checkArity(17);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16});
    }

    @Override  // kotlin.jvm.functions.Function18
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17) {
        this.checkArity(18);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17});
    }

    @Override  // kotlin.jvm.functions.Function19
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18) {
        this.checkArity(19);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18});
    }

    @Override  // kotlin.jvm.functions.Function20
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19) {
        this.checkArity(20);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19});
    }

    @Override  // kotlin.jvm.functions.Function21
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19, Object object20) {
        this.checkArity(21);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19, object20});
    }

    @Override  // kotlin.jvm.functions.Function22
    public Object invoke(Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6, Object object7, Object object8, Object object9, Object object10, Object object11, Object object12, Object object13, Object object14, Object object15, Object object16, Object object17, Object object18, Object object19, Object object20, Object object21) {
        this.checkArity(22);
        return this.invokeVararg(new Object[]{object0, object1, object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14, object15, object16, object17, object18, object19, object20, object21});
    }

    public Object invokeVararg(Object[] arr_object) {
        throw new UnsupportedOperationException();
    }

    private void throwWrongArity(int v) {
        throw new IllegalStateException("Wrong function arity, expected: " + v + ", actual: " + this.getArity());
    }
}

