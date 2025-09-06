package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.parser.CLArray;
import androidx.constraintlayout.core.parser.CLContainer;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;

public class TransitionParser {
    private static int map(String s, String[] arr_s) {
        for(int v = 0; v < arr_s.length; ++v) {
            if(arr_s[v].equals(s)) {
                return v;
            }
        }
        return 0;
    }

    private static void map(TypedBundle typedBundle0, int v, String s, String[] arr_s) {
        for(int v1 = 0; v1 < arr_s.length; ++v1) {
            if(arr_s[v1].equals(s)) {
                typedBundle0.add(v, v1);
            }
        }
    }

    public static void parse(CLObject cLObject0, Transition transition0) throws CLParsingException {
        transition0.resetProperties();
        String s = cLObject0.getStringOrNull("pathMotionArc");
        TypedBundle typedBundle0 = new TypedBundle();
        int v = 1;
        int v1 = 0;
        if(s != null) {
            s.hashCode();
            switch(s) {
                case "above": {
                    typedBundle0.add(509, 5);
                    break;
                }
                case "below": {
                    typedBundle0.add(509, 4);
                    break;
                }
                case "flip": {
                    typedBundle0.add(509, 3);
                    break;
                }
                case "none": {
                    typedBundle0.add(509, 0);
                    break;
                }
                case "startHorizontal": {
                    typedBundle0.add(509, 2);
                    break;
                }
                case "startVertical": {
                    typedBundle0.add(509, 1);
                }
            }
            v1 = 1;
        }
        String s1 = cLObject0.getStringOrNull("interpolator");
        if(s1 != null) {
            typedBundle0.add(705, s1);
            v1 = 1;
        }
        float f = cLObject0.getFloatOrNaN("staggered");
        if(Float.isNaN(f)) {
            v = v1;
        }
        else {
            typedBundle0.add(706, f);
        }
        if(v != 0) {
            transition0.setTransitionProperties(typedBundle0);
        }
        CLObject cLObject1 = cLObject0.getObjectOrNull("onSwipe");
        if(cLObject1 != null) {
            TransitionParser.parseOnSwipe(cLObject1, transition0);
        }
        TransitionParser.parseKeyFrames(cLObject0, transition0);
    }

    @Deprecated
    public static void parse(CLObject cLObject0, Transition transition0, CorePixelDp corePixelDp0) throws CLParsingException {
        TransitionParser.parse(cLObject0, transition0);
    }

    private static void parseKeyAttribute(CLObject cLObject0, Transition transition0) throws CLParsingException {
        int v12;
        CLObject cLObject2;
        CustomVariable[][] arr2_customVariable;
        String[] arr_s1;
        CLArray cLArray0 = cLObject0.getArrayOrNull("target");
        if(cLArray0 != null) {
            CLArray cLArray1 = cLObject0.getArrayOrNull("frames");
            if(cLArray1 != null) {
                String s = cLObject0.getStringOrNull("transitionEasing");
                String[] arr_s = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha"};
                int v = cLArray1.size();
                TypedBundle[] arr_typedBundle = new TypedBundle[v];
                for(int v1 = 0; v1 < cLArray1.size(); ++v1) {
                    arr_typedBundle[v1] = new TypedBundle();
                }
                int v2 = 0;
                while(v2 < 9) {
                    String s1 = arr_s[v2];
                    int v3 = new int[]{311, 312, 304, 305, 306, 308, 309, 310, 303}[v2];
                    boolean z = new boolean[]{false, false, true, true, true, false, false, false, false}[v2];
                    CLArray cLArray2 = cLObject0.getArrayOrNull(s1);
                    if(cLArray2 != null && cLArray2.size() != v) {
                        throw new CLParsingException("incorrect size for " + s1 + " array, not matching targets array!", cLObject0);
                    }
                    if(cLArray2 == null) {
                        arr_s1 = arr_s;
                        float f1 = cLObject0.getFloatOrNaN(s1);
                        if(!Float.isNaN(f1)) {
                            if(z) {
                                f1 = transition0.mToPixel.toPixels(f1);
                            }
                            for(int v5 = 0; v5 < v; ++v5) {
                                arr_typedBundle[v5].add(v3, f1);
                            }
                        }
                    }
                    else {
                        for(int v4 = 0; v4 < v; ++v4) {
                            float f = cLArray2.getFloat(v4);
                            if(z) {
                                f = transition0.mToPixel.toPixels(f);
                            }
                            arr_typedBundle[v4].add(v3, f);
                        }
                        arr_s1 = arr_s;
                    }
                    ++v2;
                    arr_s = arr_s1;
                }
                CLElement cLElement0 = cLObject0.getOrNull("custom");
                if(cLElement0 == null || !(cLElement0 instanceof CLObject)) {
                    arr2_customVariable = null;
                }
                else {
                    CLObject cLObject1 = (CLObject)cLElement0;
                    int v6 = cLObject1.size();
                    arr2_customVariable = new CustomVariable[cLArray1.size()][v6];
                    int v7 = 0;
                    while(v7 < v6) {
                        CLKey cLKey0 = (CLKey)cLObject1.get(v7);
                        String s2 = cLKey0.content();
                        if(cLKey0.getValue() instanceof CLArray) {
                            CLArray cLArray3 = (CLArray)cLKey0.getValue();
                            int v8 = cLArray3.size();
                            if(v8 == v && v8 > 0) {
                                if(cLArray3.get(0) instanceof CLNumber) {
                                    for(int v9 = 0; v9 < v; ++v9) {
                                        CustomVariable[] arr_customVariable = arr2_customVariable[v9];
                                        arr_customVariable[v7] = new CustomVariable(s2, 901, cLArray3.get(v9).getFloat());
                                    }
                                }
                                else {
                                    cLObject2 = cLObject1;
                                    for(int v10 = 0; v10 < v; v10 = v12 + 1) {
                                        long v11 = ConstraintSetParser.parseColorString(cLArray3.get(v10).content());
                                        if(v11 == -1L) {
                                            v12 = v10;
                                        }
                                        else {
                                            CustomVariable[] arr_customVariable1 = arr2_customVariable[v10];
                                            v12 = v10;
                                            arr_customVariable1[v7] = new CustomVariable(s2, 902, ((int)v11));
                                        }
                                    }
                                    goto label_100;
                                }
                            }
                            cLObject2 = cLObject1;
                        }
                        else {
                            cLObject2 = cLObject1;
                            CLElement cLElement1 = cLKey0.getValue();
                            if(cLElement1 instanceof CLNumber) {
                                float f2 = cLElement1.getFloat();
                                for(int v13 = 0; v13 < v; ++v13) {
                                    CustomVariable[] arr_customVariable2 = arr2_customVariable[v13];
                                    arr_customVariable2[v7] = new CustomVariable(s2, 901, f2);
                                }
                            }
                            else {
                                long v14 = ConstraintSetParser.parseColorString(cLElement1.content());
                                if(v14 != -1L) {
                                    for(int v15 = 0; v15 < v; ++v15) {
                                        CustomVariable[] arr_customVariable3 = arr2_customVariable[v15];
                                        arr_customVariable3[v7] = new CustomVariable(s2, 902, ((int)v14));
                                    }
                                }
                            }
                        }
                    label_100:
                        ++v7;
                        cLObject1 = cLObject2;
                    }
                }
                String s3 = cLObject0.getStringOrNull("curveFit");
                for(int v16 = 0; v16 < cLArray0.size(); ++v16) {
                    for(int v17 = 0; v17 < v; ++v17) {
                        String s4 = cLArray0.getString(v16);
                        TypedBundle typedBundle0 = arr_typedBundle[v17];
                        if(s3 != null) {
                            typedBundle0.add(508, TransitionParser.map(s3, new String[]{"spline", "linear"}));
                        }
                        typedBundle0.addIfNotNull(501, s);
                        typedBundle0.add(100, cLArray1.getInt(v17));
                        transition0.addKeyAttribute(s4, typedBundle0, (arr2_customVariable == null ? null : arr2_customVariable[v17]));
                    }
                }
            }
        }
    }

    private static void parseKeyCycle(CLObject cLObject0, Transition transition0) throws CLParsingException {
        CLArray cLArray0 = cLObject0.getArray("target");
        CLArray cLArray1 = cLObject0.getArray("frames");
        String s = cLObject0.getStringOrNull("transitionEasing");
        String[] arr_s = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha", "period", "offset", "phase"};
        int[] arr_v = {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0};
        int v = cLArray1.size();
        TypedBundle[] arr_typedBundle = new TypedBundle[v];
        for(int v1 = 0; v1 < v; ++v1) {
            arr_typedBundle[v1] = new TypedBundle();
        }
        boolean z = false;
        for(int v2 = 0; v2 < 12; ++v2) {
            if(cLObject0.has(arr_s[v2]) && arr_v[v2] == 1) {
                z = true;
            }
        }
        for(int v3 = 0; v3 < 12; ++v3) {
            String s1 = arr_s[v3];
            int v4 = new int[]{311, 312, 304, 305, 306, 308, 309, 310, 403, 423, 424, 425}[v3];
            int v5 = arr_v[v3];
            CLArray cLArray2 = cLObject0.getArrayOrNull(s1);
            if(cLArray2 != null && cLArray2.size() != v) {
                throw new CLParsingException("incorrect size for $attrName array, not matching targets array!", cLObject0);
            }
            if(cLArray2 == null) {
                float f1 = cLObject0.getFloatOrNaN(s1);
                if(!Float.isNaN(f1)) {
                    switch(v5) {
                        case 1: {
                            f1 = transition0.mToPixel.toPixels(f1);
                            break;
                        }
                        case 2: {
                            if(z) {
                                f1 = transition0.mToPixel.toPixels(f1);
                            }
                        }
                    }
                    for(int v7 = 0; v7 < v; ++v7) {
                        arr_typedBundle[v7].add(v4, f1);
                    }
                }
            }
            else {
                for(int v6 = 0; v6 < v; ++v6) {
                    float f = cLArray2.getFloat(v6);
                    if(v5 == 1) {
                        f = transition0.mToPixel.toPixels(f);
                    }
                    else if(v5 == 2 && z) {
                        f = transition0.mToPixel.toPixels(f);
                    }
                    arr_typedBundle[v6].add(v4, f);
                }
            }
        }
        String s2 = cLObject0.getStringOrNull("curveFit");
        String s3 = cLObject0.getStringOrNull("easing");
        String s4 = cLObject0.getStringOrNull("waveShape");
        String s5 = cLObject0.getStringOrNull("customWave");
        for(int v8 = 0; v8 < cLArray0.size(); ++v8) {
            for(int v9 = 0; v9 < v; ++v9) {
                String s6 = cLArray0.getString(v8);
                TypedBundle typedBundle0 = arr_typedBundle[v9];
                if(s2 != null) {
                    s2.hashCode();
                    if(s2.equals("linear")) {
                        typedBundle0.add(401, 1);
                    }
                    else if(s2.equals("spline")) {
                        typedBundle0.add(401, 0);
                    }
                }
                typedBundle0.addIfNotNull(501, s);
                if(s3 != null) {
                    typedBundle0.add(420, s3);
                }
                if(s4 != null) {
                    typedBundle0.add(421, s4);
                }
                if(s5 != null) {
                    typedBundle0.add(422, s5);
                }
                typedBundle0.add(100, cLArray1.getInt(v9));
                transition0.addKeyCycle(s6, typedBundle0);
            }
        }
    }

    public static void parseKeyFrames(CLObject cLObject0, Transition transition0) throws CLParsingException {
        CLObject cLObject1 = cLObject0.getObjectOrNull("KeyFrames");
        if(cLObject1 != null) {
            CLArray cLArray0 = cLObject1.getArrayOrNull("KeyPositions");
            if(cLArray0 != null) {
                for(int v1 = 0; v1 < cLArray0.size(); ++v1) {
                    CLElement cLElement0 = cLArray0.get(v1);
                    if(cLElement0 instanceof CLObject) {
                        TransitionParser.parseKeyPosition(((CLObject)cLElement0), transition0);
                    }
                }
            }
            CLArray cLArray1 = cLObject1.getArrayOrNull("KeyAttributes");
            if(cLArray1 != null) {
                for(int v2 = 0; v2 < cLArray1.size(); ++v2) {
                    CLElement cLElement1 = cLArray1.get(v2);
                    if(cLElement1 instanceof CLObject) {
                        TransitionParser.parseKeyAttribute(((CLObject)cLElement1), transition0);
                    }
                }
            }
            CLArray cLArray2 = cLObject1.getArrayOrNull("KeyCycles");
            if(cLArray2 != null) {
                for(int v = 0; v < cLArray2.size(); ++v) {
                    CLElement cLElement2 = cLArray2.get(v);
                    if(cLElement2 instanceof CLObject) {
                        TransitionParser.parseKeyCycle(((CLObject)cLElement2), transition0);
                    }
                }
            }
        }
    }

    private static void parseKeyPosition(CLObject cLObject0, Transition transition0) throws CLParsingException {
        TypedBundle typedBundle0 = new TypedBundle();
        CLArray cLArray0 = cLObject0.getArray("target");
        CLArray cLArray1 = cLObject0.getArray("frames");
        CLArray cLArray2 = cLObject0.getArrayOrNull("percentX");
        CLArray cLArray3 = cLObject0.getArrayOrNull("percentY");
        CLArray cLArray4 = cLObject0.getArrayOrNull("percentWidth");
        CLArray cLArray5 = cLObject0.getArrayOrNull("percentHeight");
        String s = cLObject0.getStringOrNull("pathMotionArc");
        String s1 = cLObject0.getStringOrNull("transitionEasing");
        String s2 = cLObject0.getStringOrNull("curveFit");
        String s3 = cLObject0.getStringOrNull("type");
        if(s3 == null) {
            s3 = "parentRelative";
        }
        if((cLArray2 == null || cLArray1.size() == cLArray2.size()) && (cLArray3 == null || cLArray1.size() == cLArray3.size())) {
            for(int v = 0; v < cLArray0.size(); ++v) {
                String s4 = cLArray0.getString(v);
                int v1 = TransitionParser.map(s3, new String[]{"deltaRelative", "pathRelative", "parentRelative"});
                typedBundle0.clear();
                typedBundle0.add(510, v1);
                if(s2 != null) {
                    TransitionParser.map(typedBundle0, 508, s2, new String[]{"spline", "linear"});
                }
                typedBundle0.addIfNotNull(501, s1);
                if(s != null) {
                    TransitionParser.map(typedBundle0, 509, s, new String[]{"none", "startVertical", "startHorizontal", "flip", "below", "above"});
                }
                for(int v2 = 0; v2 < cLArray1.size(); ++v2) {
                    typedBundle0.add(100, cLArray1.getInt(v2));
                    TransitionParser.set(typedBundle0, 506, cLArray2, v2);
                    TransitionParser.set(typedBundle0, 507, cLArray3, v2);
                    TransitionParser.set(typedBundle0, 503, cLArray4, v2);
                    TransitionParser.set(typedBundle0, 504, cLArray5, v2);
                    transition0.addKeyPosition(s4, typedBundle0);
                }
            }
        }
    }

    private static void parseOnSwipe(CLContainer cLContainer0, Transition transition0) {
        String s = cLContainer0.getStringOrNull("anchor");
        int v = TransitionParser.map(cLContainer0.getStringOrNull("side"), OnSwipe.SIDES);
        int v1 = TransitionParser.map(cLContainer0.getStringOrNull("direction"), OnSwipe.DIRECTIONS);
        float f = cLContainer0.getFloatOrNaN("scale");
        float f1 = cLContainer0.getFloatOrNaN("threshold");
        float f2 = cLContainer0.getFloatOrNaN("maxVelocity");
        float f3 = cLContainer0.getFloatOrNaN("maxAccel");
        String s1 = cLContainer0.getStringOrNull("limitBounds");
        int v2 = TransitionParser.map(cLContainer0.getStringOrNull("mode"), OnSwipe.MODE);
        int v3 = TransitionParser.map(cLContainer0.getStringOrNull("touchUp"), OnSwipe.TOUCH_UP);
        float f4 = cLContainer0.getFloatOrNaN("springMass");
        float f5 = cLContainer0.getFloatOrNaN("springStiffness");
        float f6 = cLContainer0.getFloatOrNaN("springDamping");
        float f7 = cLContainer0.getFloatOrNaN("stopThreshold");
        int v4 = TransitionParser.map(cLContainer0.getStringOrNull("springBoundary"), OnSwipe.BOUNDARY);
        String s2 = cLContainer0.getStringOrNull("around");
        OnSwipe transition$OnSwipe0 = transition0.createOnSwipe();
        transition$OnSwipe0.setAnchorId(s);
        transition$OnSwipe0.setAnchorSide(v);
        transition$OnSwipe0.setDragDirection(v1);
        transition$OnSwipe0.setDragScale(f);
        transition$OnSwipe0.setDragThreshold(f1);
        transition$OnSwipe0.setMaxVelocity(f2);
        transition$OnSwipe0.setMaxAcceleration(f3);
        transition$OnSwipe0.setLimitBoundsTo(s1);
        transition$OnSwipe0.setAutoCompleteMode(v2);
        transition$OnSwipe0.setOnTouchUp(v3);
        transition$OnSwipe0.setSpringMass(f4);
        transition$OnSwipe0.setSpringStiffness(f5);
        transition$OnSwipe0.setSpringDamping(f6);
        transition$OnSwipe0.setSpringStopThreshold(f7);
        transition$OnSwipe0.setSpringBoundary(v4);
        transition$OnSwipe0.setRotationCenterId(s2);
    }

    private static void set(TypedBundle typedBundle0, int v, CLArray cLArray0, int v1) throws CLParsingException {
        if(cLArray0 != null) {
            typedBundle0.add(v, cLArray0.getFloat(v1));
        }
    }
}

