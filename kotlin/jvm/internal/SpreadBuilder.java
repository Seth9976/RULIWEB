package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class SpreadBuilder {
    private final ArrayList list;

    public SpreadBuilder(int v) {
        this.list = new ArrayList(v);
    }

    public void add(Object object0) {
        this.list.add(object0);
    }

    public void addSpread(Object object0) {
        if(object0 != null) {
            if(!(object0 instanceof Object[])) {
                if(object0 instanceof Collection) {
                    this.list.addAll(((Collection)object0));
                    return;
                }
                if(object0 instanceof Iterable) {
                    for(Object object1: ((Iterable)object0)) {
                        this.list.add(object1);
                    }
                    return;
                }
                if(!(object0 instanceof Iterator)) {
                    throw new UnsupportedOperationException("Don\'t know how to spread " + object0.getClass());
                }
                while(((Iterator)object0).hasNext()) {
                    Object object2 = ((Iterator)object0).next();
                    this.list.add(object2);
                }
            }
            else if(((Object[])object0).length > 0) {
                this.list.ensureCapacity(this.list.size() + ((Object[])object0).length);
                Collections.addAll(this.list, ((Object[])object0));
            }
        }
    }

    public int size() {
        return this.list.size();
    }

    public Object[] toArray(Object[] arr_object) {
        return this.list.toArray(arr_object);
    }
}

