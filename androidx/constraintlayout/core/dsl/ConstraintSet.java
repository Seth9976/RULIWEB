package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;

public class ConstraintSet {
    ArrayList mConstraints;
    ArrayList mHelpers;
    private final String mName;

    public ConstraintSet(String s) {
        this.mConstraints = new ArrayList();
        this.mHelpers = new ArrayList();
        this.mName = s;
    }

    public void add(Constraint constraint0) {
        this.mConstraints.add(constraint0);
    }

    public void add(Helper helper0) {
        this.mHelpers.add(helper0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(this.mName + ":{\n");
        if(!this.mConstraints.isEmpty()) {
            for(Object object0: this.mConstraints) {
                stringBuilder0.append(((Constraint)object0).toString());
            }
        }
        if(!this.mHelpers.isEmpty()) {
            for(Object object1: this.mHelpers) {
                stringBuilder0.append(((Helper)object1).toString());
            }
        }
        stringBuilder0.append("},\n");
        return stringBuilder0.toString();
    }
}

