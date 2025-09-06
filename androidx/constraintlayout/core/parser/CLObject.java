package androidx.constraintlayout.core.parser;

import java.util.Iterator;

public class CLObject extends CLContainer implements Iterable {
    static class CLObjectIterator implements Iterator {
        int mIndex;
        CLObject mObject;

        CLObjectIterator(CLObject cLObject0) {
            this.mIndex = 0;
            this.mObject = cLObject0;
        }

        @Override
        public boolean hasNext() {
            return this.mIndex < this.mObject.size();
        }

        public CLKey next() {
            CLKey cLKey0 = (CLKey)this.mObject.mElements.get(this.mIndex);
            ++this.mIndex;
            return cLKey0;
        }

        @Override
        public Object next() {
            return this.next();
        }
    }

    public CLObject(char[] arr_c) {
        super(arr_c);
    }

    public static CLObject allocate(char[] arr_c) {
        return new CLObject(arr_c);
    }

    @Override  // androidx.constraintlayout.core.parser.CLContainer
    public CLContainer clone() {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.core.parser.CLContainer
    public CLElement clone() {
        return this.clone();
    }

    public CLObject clone() {
        return (CLObject)super.clone();
    }

    @Override  // androidx.constraintlayout.core.parser.CLContainer
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override
    public Iterator iterator() {
        return new CLObjectIterator(this);
    }

    public String toFormattedJSON() {
        return this.toFormattedJSON(0, 0);
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int v, int v1) {
        StringBuilder stringBuilder0 = new StringBuilder(this.getDebugName());
        stringBuilder0.append("{\n");
        boolean z = true;
        for(Object object0: this.mElements) {
            if(z) {
                z = false;
            }
            else {
                stringBuilder0.append(",\n");
            }
            stringBuilder0.append(((CLElement)object0).toFormattedJSON(CLObject.sBaseIndent + v, v1 - 1));
        }
        stringBuilder0.append("\n");
        this.addIndent(stringBuilder0, v);
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        StringBuilder stringBuilder0 = new StringBuilder(this.getDebugName() + "{ ");
        boolean z = true;
        Iterator iterator0 = this.mElements.iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
            if(z) {
                z = false;
            }
            else {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append("");
        }
        stringBuilder0.append(" }");
        return stringBuilder0.toString();
    }
}

