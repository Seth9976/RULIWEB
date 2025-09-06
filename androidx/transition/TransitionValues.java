package androidx.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransitionValues {
    final ArrayList mTargetedTransitions;
    public final Map values;
    public View view;

    @Deprecated
    public TransitionValues() {
        this.values = new HashMap();
        this.mTargetedTransitions = new ArrayList();
    }

    public TransitionValues(View view0) {
        this.values = new HashMap();
        this.mTargetedTransitions = new ArrayList();
        this.view = view0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof TransitionValues && this.view == ((TransitionValues)object0).view && this.values.equals(((TransitionValues)object0).values);
    }

    @Override
    public int hashCode() {
        return this.view.hashCode() * 0x1F + this.values.hashCode();
    }

    @Override
    public String toString() {
        String s = "TransitionValues@" + Integer.toHexString(this.hashCode()) + ":\n" + "    view = " + this.view + "\n" + "    values:";
        for(Object object0: this.values.keySet()) {
            s = s + "    " + ((String)object0) + ": " + this.values.get(((String)object0)) + "\n";
        }
        return s;
    }
}

