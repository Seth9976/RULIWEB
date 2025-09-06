package androidx.constraintlayout.core.parser;

import java.util.ArrayList;
import java.util.Objects;

public class CLContainer extends CLElement {
    ArrayList mElements;

    public CLContainer(char[] arr_c) {
        super(arr_c);
        this.mElements = new ArrayList();
    }

    public void add(CLElement cLElement0) {
        this.mElements.add(cLElement0);
        if(CLParser.sDebug) {
            System.out.println("added element " + cLElement0 + " to " + this);
        }
    }

    public static CLElement allocate(char[] arr_c) {
        return new CLContainer(arr_c);
    }

    public void clear() {
        this.mElements.clear();
    }

    public CLContainer clone() {
        CLContainer cLContainer0 = (CLContainer)super.clone();
        ArrayList arrayList0 = new ArrayList(this.mElements.size());
        for(Object object0: this.mElements) {
            CLElement cLElement0 = ((CLElement)object0).clone();
            cLElement0.setContainer(cLContainer0);
            arrayList0.add(cLElement0);
        }
        cLContainer0.mElements = arrayList0;
        return cLContainer0;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public CLElement clone() {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public Object clone() throws CloneNotSupportedException {
        return this.clone();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof CLContainer ? this.mElements.equals(((CLContainer)object0).mElements) : false;
    }

    public CLElement get(int v) throws CLParsingException {
        if(v < 0 || v >= this.mElements.size()) {
            throw new CLParsingException("no element at index " + v, this);
        }
        return (CLElement)this.mElements.get(v);
    }

    public CLElement get(String s) throws CLParsingException {
        for(Object object0: this.mElements) {
            CLKey cLKey0 = (CLKey)(((CLElement)object0));
            if(cLKey0.content().equals(s)) {
                return cLKey0.getValue();
            }
            if(false) {
                break;
            }
        }
        throw new CLParsingException("no element for key <" + s + ">", this);
    }

    public CLArray getArray(int v) throws CLParsingException {
        CLElement cLElement0 = this.get(v);
        if(!(cLElement0 instanceof CLArray)) {
            throw new CLParsingException("no array at index " + v, this);
        }
        return (CLArray)cLElement0;
    }

    public CLArray getArray(String s) throws CLParsingException {
        CLElement cLElement0 = this.get(s);
        if(!(cLElement0 instanceof CLArray)) {
            throw new CLParsingException("no array found for key <" + s + ">, found [" + cLElement0.getStrClass() + "] : " + cLElement0, this);
        }
        return (CLArray)cLElement0;
    }

    public CLArray getArrayOrCreate(String s) {
        CLArray cLArray0 = this.getArrayOrNull(s);
        if(cLArray0 != null) {
            return cLArray0;
        }
        CLArray cLArray1 = new CLArray(new char[0]);
        this.put(s, cLArray1);
        return cLArray1;
    }

    public CLArray getArrayOrNull(String s) {
        CLElement cLElement0 = this.getOrNull(s);
        return cLElement0 instanceof CLArray ? ((CLArray)cLElement0) : null;
    }

    public boolean getBoolean(int v) throws CLParsingException {
        CLElement cLElement0 = this.get(v);
        if(!(cLElement0 instanceof CLToken)) {
            throw new CLParsingException("no boolean at index " + v, this);
        }
        return ((CLToken)cLElement0).getBoolean();
    }

    public boolean getBoolean(String s) throws CLParsingException {
        CLElement cLElement0 = this.get(s);
        if(!(cLElement0 instanceof CLToken)) {
            throw new CLParsingException("no boolean found for key <" + s + ">, found [" + cLElement0.getStrClass() + "] : " + cLElement0, this);
        }
        return ((CLToken)cLElement0).getBoolean();
    }

    public float getFloat(int v) throws CLParsingException {
        CLElement cLElement0 = this.get(v);
        if(cLElement0 == null) {
            throw new CLParsingException("no float at index " + v, this);
        }
        return cLElement0.getFloat();
    }

    public float getFloat(String s) throws CLParsingException {
        CLElement cLElement0 = this.get(s);
        if(cLElement0 == null) {
            throw new NullPointerException();
        }
        return cLElement0.getFloat();
    }

    public float getFloatOrNaN(String s) {
        CLElement cLElement0 = this.getOrNull(s);
        return cLElement0 instanceof CLNumber ? cLElement0.getFloat() : NaNf;
    }

    public int getInt(int v) throws CLParsingException {
        CLElement cLElement0 = this.get(v);
        if(cLElement0 == null) {
            throw new CLParsingException("no int at index " + v, this);
        }
        return cLElement0.getInt();
    }

    public int getInt(String s) throws CLParsingException {
        CLElement cLElement0 = this.get(s);
        if(cLElement0 == null) {
            throw new NullPointerException();
        }
        return cLElement0.getInt();
    }

    public CLObject getObject(int v) throws CLParsingException {
        CLElement cLElement0 = this.get(v);
        if(!(cLElement0 instanceof CLObject)) {
            throw new CLParsingException("no object at index " + v, this);
        }
        return (CLObject)cLElement0;
    }

    public CLObject getObject(String s) throws CLParsingException {
        CLElement cLElement0 = this.get(s);
        if(!(cLElement0 instanceof CLObject)) {
            throw new CLParsingException("no object found for key <" + s + ">, found [" + cLElement0.getStrClass() + "] : " + cLElement0, this);
        }
        return (CLObject)cLElement0;
    }

    public CLObject getObjectOrNull(String s) {
        CLElement cLElement0 = this.getOrNull(s);
        return cLElement0 instanceof CLObject ? ((CLObject)cLElement0) : null;
    }

    public CLElement getOrNull(int v) {
        return v < 0 || v >= this.mElements.size() ? null : ((CLElement)this.mElements.get(v));
    }

    public CLElement getOrNull(String s) {
        for(Object object0: this.mElements) {
            CLKey cLKey0 = (CLKey)(((CLElement)object0));
            if(cLKey0.content().equals(s)) {
                return cLKey0.getValue();
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public String getString(int v) throws CLParsingException {
        CLElement cLElement0 = this.get(v);
        if(!(cLElement0 instanceof CLString)) {
            throw new CLParsingException("no string at index " + v, this);
        }
        return cLElement0.content();
    }

    public String getString(String s) throws CLParsingException {
        CLElement cLElement0 = this.get(s);
        if(!(cLElement0 instanceof CLString)) {
            throw new CLParsingException("no string found for key <" + s + ">, found [" + (cLElement0 == null ? null : cLElement0.getStrClass()) + "] : " + cLElement0, this);
        }
        return cLElement0.content();
    }

    public String getStringOrNull(int v) {
        CLElement cLElement0 = this.getOrNull(v);
        return cLElement0 instanceof CLString ? cLElement0.content() : null;
    }

    public String getStringOrNull(String s) {
        CLElement cLElement0 = this.getOrNull(s);
        return cLElement0 instanceof CLString ? cLElement0.content() : null;
    }

    public boolean has(String s) {
        for(Object object0: this.mElements) {
            if(((CLElement)object0) instanceof CLKey && ((CLKey)(((CLElement)object0))).content().equals(s)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        return Objects.hash(new Object[]{this.mElements, super.hashCode()});
    }

    public ArrayList names() {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.mElements) {
            CLElement cLElement0 = (CLElement)object0;
            if(cLElement0 instanceof CLKey) {
                arrayList0.add(((CLKey)cLElement0).content());
            }
        }
        return arrayList0;
    }

    public void put(String s, CLElement cLElement0) {
        for(Object object0: this.mElements) {
            CLKey cLKey0 = (CLKey)(((CLElement)object0));
            if(cLKey0.content().equals(s)) {
                cLKey0.set(cLElement0);
                return;
            }
            if(false) {
                break;
            }
        }
        CLKey cLKey1 = (CLKey)CLKey.allocate(s, cLElement0);
        this.mElements.add(cLKey1);
    }

    public void putNumber(String s, float f) {
        this.put(s, new CLNumber(f));
    }

    public void putString(String s, String s1) {
        CLString cLString0 = new CLString(s1.toCharArray());
        cLString0.setStart(0L);
        cLString0.setEnd(((long)(s1.length() - 1)));
        this.put(s, cLString0);
    }

    public void remove(String s) {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.mElements) {
            CLElement cLElement0 = (CLElement)object0;
            if(((CLKey)cLElement0).content().equals(s)) {
                arrayList0.add(cLElement0);
            }
        }
        for(Object object1: arrayList0) {
            this.mElements.remove(((CLElement)object1));
        }
    }

    public int size() {
        return this.mElements.size();
    }

    @Override  // androidx.constraintlayout.core.parser.CLElement
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object0: this.mElements) {
            if(stringBuilder0.length() > 0) {
                stringBuilder0.append("; ");
            }
            stringBuilder0.append(((CLElement)object0));
        }
        return super.toString() + " = <" + stringBuilder0 + " >";
    }
}

