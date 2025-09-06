package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;

public class MotionScene {
    ArrayList mConstraintSets;
    ArrayList mTransitions;

    public MotionScene() {
        this.mTransitions = new ArrayList();
        this.mConstraintSets = new ArrayList();
    }

    public void addConstraintSet(ConstraintSet constraintSet0) {
        this.mConstraintSets.add(constraintSet0);
    }

    public void addTransition(Transition transition0) {
        this.mTransitions.add(transition0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("{\n");
        if(!this.mTransitions.isEmpty()) {
            stringBuilder0.append("Transitions:{\n");
            for(Object object0: this.mTransitions) {
                stringBuilder0.append(((Transition)object0).toString());
            }
            stringBuilder0.append("},\n");
        }
        if(!this.mConstraintSets.isEmpty()) {
            stringBuilder0.append("ConstraintSets:{\n");
            for(Object object1: this.mConstraintSets) {
                stringBuilder0.append(((ConstraintSet)object1).toString());
            }
            stringBuilder0.append("},\n");
        }
        stringBuilder0.append("}\n");
        return stringBuilder0.toString();
    }
}

