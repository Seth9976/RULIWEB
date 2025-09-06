package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.ArrayList;

public final class ArgumentList extends ArrayList implements TypeArgumentListMarker {
    public ArgumentList(int v) {
        super(v);
    }

    @Override
    public final boolean contains(Object object0) {
        return object0 instanceof TypeArgumentMarker ? this.contains(((TypeArgumentMarker)object0)) : false;
    }

    public boolean contains(TypeArgumentMarker typeArgumentMarker0) {
        return super.contains(typeArgumentMarker0);
    }

    public int getSize() {
        return super.size();
    }

    @Override
    public final int indexOf(Object object0) {
        return object0 instanceof TypeArgumentMarker ? this.indexOf(((TypeArgumentMarker)object0)) : -1;
    }

    public int indexOf(TypeArgumentMarker typeArgumentMarker0) {
        return super.indexOf(typeArgumentMarker0);
    }

    @Override
    public final int lastIndexOf(Object object0) {
        return object0 instanceof TypeArgumentMarker ? this.lastIndexOf(((TypeArgumentMarker)object0)) : -1;
    }

    public int lastIndexOf(TypeArgumentMarker typeArgumentMarker0) {
        return super.lastIndexOf(typeArgumentMarker0);
    }

    @Override
    public final boolean remove(Object object0) {
        return object0 instanceof TypeArgumentMarker ? this.remove(((TypeArgumentMarker)object0)) : false;
    }

    public boolean remove(TypeArgumentMarker typeArgumentMarker0) {
        return super.remove(typeArgumentMarker0);
    }

    @Override
    public final int size() {
        return this.getSize();
    }
}

