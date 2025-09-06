package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.parser.CLArray;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParser;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.parser.CLString;
import androidx.constraintlayout.core.state.helpers.BarrierReference;
import androidx.constraintlayout.core.state.helpers.FlowReference;
import androidx.constraintlayout.core.state.helpers.GridReference;
import androidx.constraintlayout.core.state.helpers.GuidelineReference;
import androidx.constraintlayout.core.state.helpers.HorizontalChainReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ConstraintSetParser {
    public static class DesignElement {
        String mId;
        HashMap mParams;
        String mType;

        DesignElement(String s, String s1, HashMap hashMap0) {
            this.mId = s;
            this.mType = s1;
            this.mParams = hashMap0;
        }

        public String getId() {
            return this.mId;
        }

        public HashMap getParams() {
            return this.mParams;
        }

        public String getType() {
            return this.mType;
        }
    }

    static class FiniteGenerator implements GeneratedValue {
        float mCurrent;
        float mFrom;
        float mInitial;
        float mMax;
        String mPostfix;
        String mPrefix;
        float mStep;
        boolean mStop;
        float mTo;

        FiniteGenerator(float f, float f1, float f2, String s, String s1) {
            this.mStop = false;
            this.mCurrent = 0.0f;
            this.mFrom = f;
            this.mTo = f1;
            this.mStep = f2;
            if(s == null) {
                s = "";
            }
            this.mPrefix = s;
            if(s1 == null) {
                s1 = "";
            }
            this.mPostfix = s1;
            this.mMax = f1;
            this.mInitial = f;
        }

        public ArrayList array() {
            ArrayList arrayList0 = new ArrayList();
            int v = (int)this.mInitial;
            int v1 = (int)this.mMax;
            int v2 = v;
            while(v <= v1) {
                arrayList0.add(this.mPrefix + v2 + this.mPostfix);
                v2 += (int)this.mStep;
                ++v;
            }
            return arrayList0;
        }

        @Override  // androidx.constraintlayout.core.state.ConstraintSetParser$GeneratedValue
        public float value() {
            float f = this.mCurrent;
            if(f >= this.mMax) {
                this.mStop = true;
            }
            if(!this.mStop) {
                this.mCurrent = f + this.mStep;
            }
            return this.mCurrent;
        }
    }

    interface GeneratedValue {
        float value();
    }

    static class Generator implements GeneratedValue {
        float mCurrent;
        float mIncrementBy;
        float mStart;
        boolean mStop;

        Generator(float f, float f1) {
            this.mStop = false;
            this.mStart = f;
            this.mIncrementBy = f1;
            this.mCurrent = f;
        }

        @Override  // androidx.constraintlayout.core.state.ConstraintSetParser$GeneratedValue
        public float value() {
            if(!this.mStop) {
                this.mCurrent += this.mIncrementBy;
            }
            return this.mCurrent;
        }
    }

    public static class LayoutVariables {
        HashMap mArrayIds;
        HashMap mGenerators;
        HashMap mMargins;

        public LayoutVariables() {
            this.mMargins = new HashMap();
            this.mGenerators = new HashMap();
            this.mArrayIds = new HashMap();
        }

        float get(Object object0) {
            if(object0 instanceof CLString) {
                String s = ((CLString)object0).content();
                if(this.mGenerators.containsKey(s)) {
                    return ((GeneratedValue)this.mGenerators.get(s)).value();
                }
                return this.mMargins.containsKey(s) ? ((Integer)this.mMargins.get(s)).floatValue() : 0.0f;
            }
            return object0 instanceof CLNumber ? ((CLNumber)object0).getFloat() : 0.0f;
        }

        // 去混淆评级： 低(20)
        ArrayList getList(String s) {
            return this.mArrayIds.containsKey(s) ? ((ArrayList)this.mArrayIds.get(s)) : null;
        }

        void put(String s, float f, float f1) {
            if(this.mGenerators.containsKey(s) && this.mGenerators.get(s) instanceof OverrideValue) {
                return;
            }
            this.mGenerators.put(s, new Generator(f, f1));
        }

        void put(String s, float f, float f1, float f2, String s1, String s2) {
            if(this.mGenerators.containsKey(s) && this.mGenerators.get(s) instanceof OverrideValue) {
                return;
            }
            FiniteGenerator constraintSetParser$FiniteGenerator0 = new FiniteGenerator(f, f1, f2, s1, s2);
            this.mGenerators.put(s, constraintSetParser$FiniteGenerator0);
            this.mArrayIds.put(s, constraintSetParser$FiniteGenerator0.array());
        }

        void put(String s, int v) {
            this.mMargins.put(s, v);
        }

        void put(String s, ArrayList arrayList0) {
            this.mArrayIds.put(s, arrayList0);
        }

        public void putOverride(String s, float f) {
            OverrideValue constraintSetParser$OverrideValue0 = new OverrideValue(f);
            this.mGenerators.put(s, constraintSetParser$OverrideValue0);
        }
    }

    public static enum MotionLayoutDebugFlags {
        NONE,
        SHOW_ALL,
        UNKNOWN;

        private static MotionLayoutDebugFlags[] $values() [...] // Inlined contents
    }

    static class OverrideValue implements GeneratedValue {
        float mValue;

        OverrideValue(float f) {
            this.mValue = f;
        }

        @Override  // androidx.constraintlayout.core.state.ConstraintSetParser$GeneratedValue
        public float value() {
            return this.mValue;
        }
    }

    private static final boolean PARSER_DEBUG = false;

    static void applyAttribute(State state0, LayoutVariables constraintSetParser$LayoutVariables0, ConstraintReference constraintReference0, CLObject cLObject0, String s) throws CLParsingException {
        s.hashCode();
        switch(s) {
            case "alpha": {
                constraintReference0.alpha(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "center": {
                String s2 = cLObject0.getString(s);
                ConstraintReference constraintReference2 = s2.equals("parent") ? state0.constraints(State.PARENT) : state0.constraints(s2);
                constraintReference0.startToStart(constraintReference2);
                constraintReference0.endToEnd(constraintReference2);
                constraintReference0.topToTop(constraintReference2);
                constraintReference0.bottomToBottom(constraintReference2);
                return;
            }
            case "centerHorizontally": {
                String s3 = cLObject0.getString(s);
                if(s3.equals("parent")) {
                    s3 = State.PARENT;
                }
                ConstraintReference constraintReference3 = state0.constraints(s3);
                constraintReference0.startToStart(constraintReference3);
                constraintReference0.endToEnd(constraintReference3);
                return;
            }
            case "centerVertically": {
                String s1 = cLObject0.getString(s);
                if(s1.equals("parent")) {
                    s1 = State.PARENT;
                }
                ConstraintReference constraintReference1 = state0.constraints(s1);
                constraintReference0.topToTop(constraintReference1);
                constraintReference0.bottomToBottom(constraintReference1);
                return;
            }
            case "custom": {
                ConstraintSetParser.parseCustomProperties(cLObject0, constraintReference0, s);
                return;
            }
            case "hBias": {
                constraintReference0.horizontalBias(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "hRtlBias": {
                float f = constraintSetParser$LayoutVariables0.get(cLObject0.get(s));
                if(state0.isRtl()) {
                    f = 1.0f - f;
                }
                constraintReference0.horizontalBias(f);
                return;
            }
            case "hWeight": {
                constraintReference0.setHorizontalChainWeight(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "height": {
                constraintReference0.setHeight(ConstraintSetParser.parseDimension(cLObject0, s, state0, state0.getDpToPixel()));
                return;
            }
            case "motion": {
                ConstraintSetParser.parseMotionProperties(cLObject0.get(s), constraintReference0);
                return;
            }
            case "pivotX": {
                constraintReference0.pivotX(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "pivotY": {
                constraintReference0.pivotY(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "rotationX": {
                constraintReference0.rotationX(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "rotationY": {
                constraintReference0.rotationY(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "rotationZ": {
                constraintReference0.rotationZ(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "scaleX": {
                constraintReference0.scaleX(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "scaleY": {
                constraintReference0.scaleY(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "translationX": {
                constraintReference0.translationX(ConstraintSetParser.toPix(state0, constraintSetParser$LayoutVariables0.get(cLObject0.get(s))));
                return;
            }
            case "translationY": {
                constraintReference0.translationY(ConstraintSetParser.toPix(state0, constraintSetParser$LayoutVariables0.get(cLObject0.get(s))));
                return;
            }
            case "translationZ": {
                constraintReference0.translationZ(ConstraintSetParser.toPix(state0, constraintSetParser$LayoutVariables0.get(cLObject0.get(s))));
                return;
            }
            case "vBias": {
                constraintReference0.verticalBias(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "vWeight": {
                constraintReference0.setVerticalChainWeight(constraintSetParser$LayoutVariables0.get(cLObject0.get(s)));
                return;
            }
            case "visibility": {
                String s4 = cLObject0.getString(s);
                s4.hashCode();
                switch(s4) {
                    case "gone": {
                        constraintReference0.visibility(8);
                        return;
                    }
                    case "invisible": {
                        constraintReference0.visibility(4);
                        constraintReference0.alpha(0.0f);
                        return;
                    }
                    case "visible": {
                        constraintReference0.visibility(0);
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            case "width": {
                constraintReference0.setWidth(ConstraintSetParser.parseDimension(cLObject0, s, state0, state0.getDpToPixel()));
                return;
            }
            default: {
                ConstraintSetParser.parseConstraint(state0, constraintSetParser$LayoutVariables0, cLObject0, constraintReference0, s);
            }
        }
    }

    private static int indexOf(String s, String[] arr_s) {
        for(int v = 0; v < arr_s.length; ++v) {
            if(arr_s[v].equals(s)) {
                return v;
            }
        }
        return -1;
    }

    static String lookForType(CLObject cLObject0) throws CLParsingException {
        for(Object object0: cLObject0.names()) {
            if(((String)object0).equals("type")) {
                return cLObject0.getString("type");
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    static void override(CLObject cLObject0, String s, CLObject cLObject1) throws CLParsingException {
        if(!cLObject0.has(s)) {
            cLObject0.put(s, cLObject1);
            return;
        }
        CLObject cLObject2 = cLObject0.getObject(s);
        for(Object object0: cLObject1.names()) {
            String s1 = (String)object0;
            if(s1.equals("clear")) {
                CLArray cLArray0 = cLObject1.getArray("clear");
                for(int v = 0; v < cLArray0.size(); ++v) {
                    String s2 = cLArray0.getStringOrNull(v);
                    if(s2 != null) {
                        s2.hashCode();
                        switch(s2) {
                            case "constraints": {
                                cLObject2.remove("start");
                                cLObject2.remove("end");
                                cLObject2.remove("top");
                                cLObject2.remove("bottom");
                                cLObject2.remove("baseline");
                                cLObject2.remove("center");
                                cLObject2.remove("centerHorizontally");
                                cLObject2.remove("centerVertically");
                                break;
                            }
                            case "dimensions": {
                                cLObject2.remove("width");
                                cLObject2.remove("height");
                                break;
                            }
                            case "transforms": {
                                cLObject2.remove("visibility");
                                cLObject2.remove("alpha");
                                cLObject2.remove("pivotX");
                                cLObject2.remove("pivotY");
                                cLObject2.remove("rotationX");
                                cLObject2.remove("rotationY");
                                cLObject2.remove("rotationZ");
                                cLObject2.remove("scaleX");
                                cLObject2.remove("scaleY");
                                cLObject2.remove("translationX");
                                cLObject2.remove("translationY");
                                break;
                            }
                            default: {
                                cLObject2.remove(s2);
                            }
                        }
                    }
                }
            }
            else {
                cLObject2.put(s1, cLObject1.get(s1));
            }
        }
    }

    // This method was un-flattened
    static void parseBarrier(State state0, String s, CLObject cLObject0) throws CLParsingException {
        boolean z = state0.isRtl();
        BarrierReference barrierReference0 = state0.barrier(s, Direction.END);
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                String s1 = (String)object0;
                s1.hashCode();
            alab1:
                switch(s1) {
                    case "contains": {
                        CLArray cLArray0 = cLObject0.getArrayOrNull(s1);
                        if(cLArray0 == null) {
                            continue;
                        }
                        for(int v = 0; true; ++v) {
                            if(v >= cLArray0.size()) {
                                break alab1;
                            }
                            barrierReference0.add(new Object[]{state0.constraints(cLArray0.get(v).content())});
                        }
                    }
                    case "direction": {
                        String s2 = cLObject0.getString(s1);
                        s2.hashCode();
                        switch(s2) {
                            case "bottom": {
                                barrierReference0.setBarrierDirection(Direction.BOTTOM);
                                break;
                            }
                            case "end": {
                                if(z) {
                                    barrierReference0.setBarrierDirection(Direction.LEFT);
                                }
                                else {
                                    barrierReference0.setBarrierDirection(Direction.RIGHT);
                                }
                                break;
                            }
                            case "left": {
                                barrierReference0.setBarrierDirection(Direction.LEFT);
                                break;
                            }
                            case "right": {
                                barrierReference0.setBarrierDirection(Direction.RIGHT);
                                break;
                            }
                            case "start": {
                                if(z) {
                                    barrierReference0.setBarrierDirection(Direction.RIGHT);
                                }
                                else {
                                    barrierReference0.setBarrierDirection(Direction.LEFT);
                                }
                                break;
                            }
                            case "top": {
                                barrierReference0.setBarrierDirection(Direction.TOP);
                            }
                        }
                        continue;
                    }
                    case "margin": {
                        float f = cLObject0.getFloatOrNaN(s1);
                        if(Float.isNaN(f)) {
                            continue;
                        }
                        barrierReference0.margin(ConstraintSetParser.toPix(state0, f));
                    }
                }
            }
        }
    }

    static void parseChain(int v, State state0, LayoutVariables constraintSetParser$LayoutVariables0, CLArray cLArray0) throws CLParsingException {
        String s1;
        HorizontalChainReference horizontalChainReference0 = v == 0 ? state0.horizontalChain() : state0.verticalChain();
        CLElement cLElement0 = cLArray0.get(1);
        if(cLElement0 instanceof CLArray && ((CLArray)cLElement0).size() >= 1) {
            for(int v1 = 0; v1 < ((CLArray)cLElement0).size(); ++v1) {
                horizontalChainReference0.add(new Object[]{((CLArray)cLElement0).getString(v1)});
            }
            if(cLArray0.size() > 2) {
                CLElement cLElement1 = cLArray0.get(2);
                if(cLElement1 instanceof CLObject) {
                    for(Object object0: ((CLObject)cLElement1).names()) {
                        String s = (String)object0;
                        s.hashCode();
                        if(s.equals("style")) {
                            CLElement cLElement2 = ((CLObject)cLElement1).get(s);
                            if(!(cLElement2 instanceof CLArray) || ((CLArray)cLElement2).size() <= 1) {
                                s1 = cLElement2.content();
                            }
                            else {
                                s1 = ((CLArray)cLElement2).getString(0);
                                horizontalChainReference0.bias(((CLArray)cLElement2).getFloat(1));
                            }
                            s1.hashCode();
                            if(s1.equals("packed")) {
                                horizontalChainReference0.style(Chain.PACKED);
                            }
                            else if(s1.equals("spread_inside")) {
                                horizontalChainReference0.style(Chain.SPREAD_INSIDE);
                            }
                            else {
                                horizontalChainReference0.style(Chain.SPREAD);
                            }
                        }
                        else {
                            ConstraintSetParser.parseConstraint(state0, constraintSetParser$LayoutVariables0, ((CLObject)cLElement1), horizontalChainReference0, s);
                        }
                    }
                }
            }
        }
    }

    private static void parseChainType(String s, State state0, String s1, LayoutVariables constraintSetParser$LayoutVariables0, CLObject cLObject0) throws CLParsingException {
        String s5;
        float f4;
        float f3;
        float f2;
        float f1;
        float f;
        String s4;
        CLArray cLArray1;
        int v1;
        HorizontalChainReference horizontalChainReference0 = s.charAt(0) == 104 ? state0.horizontalChain() : state0.verticalChain();
        horizontalChainReference0.setKey(s1);
        for(Object object0: cLObject0.names()) {
            String s2 = (String)object0;
            s2.hashCode();
            switch(s2) {
                case "bottom": {
                    ConstraintSetParser.parseConstraint(state0, constraintSetParser$LayoutVariables0, cLObject0, horizontalChainReference0, s2);
                    continue;
                }
                case "contains": {
                    CLElement cLElement0 = cLObject0.get(s2);
                    if(cLElement0 instanceof CLArray) {
                        CLArray cLArray0 = (CLArray)cLElement0;
                        if(cLArray0.size() >= 1) {
                            int v = 0;
                            while(v < cLArray0.size()) {
                                CLElement cLElement1 = cLArray0.get(v);
                                if(!(cLElement1 instanceof CLArray)) {
                                    v1 = v;
                                    cLArray1 = cLArray0;
                                    horizontalChainReference0.add(new Object[]{cLElement1.content()});
                                }
                                else if(((CLArray)cLElement1).size() > 0) {
                                    String s3 = ((CLArray)cLElement1).get(0).content();
                                    switch(((CLArray)cLElement1).size()) {
                                        case 2: {
                                            f = ((CLArray)cLElement1).getFloat(1);
                                            cLArray1 = cLArray0;
                                            v1 = v;
                                            s4 = s3;
                                            f1 = NaNf;
                                            f2 = NaNf;
                                            f3 = NaNf;
                                            f4 = NaNf;
                                            break;
                                        }
                                        case 3: {
                                            f = ((CLArray)cLElement1).getFloat(1);
                                            cLArray1 = cLArray0;
                                            v1 = v;
                                            f1 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement1).getFloat(2));
                                            f2 = f1;
                                            s4 = s3;
                                            f3 = NaNf;
                                            f4 = NaNf;
                                            break;
                                        }
                                        case 4: {
                                            float f5 = ((CLArray)cLElement1).getFloat(1);
                                            float f6 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement1).getFloat(2));
                                            f = f5;
                                            cLArray1 = cLArray0;
                                            f2 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement1).getFloat(3));
                                            f1 = f6;
                                            f3 = NaNf;
                                            v1 = v;
                                            s4 = s3;
                                            f4 = NaNf;
                                            break;
                                        }
                                        case 6: {
                                            float f7 = ((CLArray)cLElement1).getFloat(1);
                                            float f8 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement1).getFloat(2));
                                            float f9 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement1).getFloat(3));
                                            float f10 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement1).getFloat(4));
                                            f1 = f8;
                                            cLArray1 = cLArray0;
                                            f2 = f9;
                                            f = f7;
                                            v1 = v;
                                            s4 = s3;
                                            f4 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement1).getFloat(5));
                                            f3 = f10;
                                            break;
                                        }
                                        default: {
                                            v1 = v;
                                            cLArray1 = cLArray0;
                                            s4 = s3;
                                            f = NaNf;
                                            f1 = NaNf;
                                            f2 = NaNf;
                                            f3 = NaNf;
                                            f4 = NaNf;
                                        }
                                    }
                                    horizontalChainReference0.addChainElement(s4, f, f1, f2, f3, f4);
                                }
                                else {
                                    v1 = v;
                                    cLArray1 = cLArray0;
                                }
                                v = v1 + 1;
                                cLArray0 = cLArray1;
                            }
                            continue;
                        }
                    }
                    System.err.println(s1 + " contains should be an array \"" + cLElement0.content() + "\"");
                    return;
                }
                case "end": 
                case "left": 
                case "right": 
                case "start": 
                case "top": {
                    ConstraintSetParser.parseConstraint(state0, constraintSetParser$LayoutVariables0, cLObject0, horizontalChainReference0, s2);
                    continue;
                }
                case "style": {
                    break;
                }
                default: {
                    continue;
                }
            }
            CLElement cLElement2 = cLObject0.get(s2);
            if(!(cLElement2 instanceof CLArray) || ((CLArray)cLElement2).size() <= 1) {
                s5 = cLElement2.content();
            }
            else {
                s5 = ((CLArray)cLElement2).getString(0);
                horizontalChainReference0.bias(((CLArray)cLElement2).getFloat(1));
            }
            s5.hashCode();
            if(s5.equals("packed")) {
                horizontalChainReference0.style(Chain.PACKED);
            }
            else if(s5.equals("spread_inside")) {
                horizontalChainReference0.style(Chain.SPREAD_INSIDE);
            }
            else {
                horizontalChainReference0.style(Chain.SPREAD);
            }
        }
    }

    static long parseColorString(String s) {
        if(s.startsWith("#")) {
            String s1 = s.substring(1);
            return Long.parseLong((s1.length() == 6 ? "FF" + s1 : s.substring(1)), 16);
        }
        return -1L;
    }

    static void parseConstraint(State state0, LayoutVariables constraintSetParser$LayoutVariables0, CLObject cLObject0, ConstraintReference constraintReference0, String s) throws CLParsingException {
        boolean z3;
        boolean z2;
        boolean z1;
        boolean z = state0.isRtl();
        CLArray cLArray0 = cLObject0.getArrayOrNull(s);
        if(cLArray0 != null && cLArray0.size() > 1) {
            String s1 = cLArray0.getString(0);
            String s2 = cLArray0.getStringOrNull(1);
            float f = cLArray0.size() <= 2 ? 0.0f : ConstraintSetParser.toPix(state0, constraintSetParser$LayoutVariables0.get(cLArray0.getOrNull(2)));
            float f1 = cLArray0.size() <= 3 ? 0.0f : ConstraintSetParser.toPix(state0, constraintSetParser$LayoutVariables0.get(cLArray0.getOrNull(3)));
            ConstraintReference constraintReference1 = s1.equals("parent") ? state0.constraints(State.PARENT) : state0.constraints(s1);
            s.hashCode();
            switch(s) {
                case "baseline": {
                    s2.hashCode();
                    switch(s2) {
                        case "baseline": {
                            state0.baselineNeededFor(constraintReference0.getKey());
                            state0.baselineNeededFor(constraintReference1.getKey());
                            constraintReference0.baselineToBaseline(constraintReference1);
                            goto label_50;
                        }
                        case "bottom": {
                            state0.baselineNeededFor(constraintReference0.getKey());
                            constraintReference0.baselineToBottom(constraintReference1);
                            goto label_50;
                        }
                        case "top": {
                            state0.baselineNeededFor(constraintReference0.getKey());
                            constraintReference0.baselineToTop(constraintReference1);
                            goto label_50;
                        }
                        default: {
                            goto label_50;
                        }
                    }
                }
                case "bottom": {
                    s2.hashCode();
                    switch(s2) {
                        case "baseline": {
                            state0.baselineNeededFor(constraintReference1.getKey());
                            constraintReference0.bottomToBaseline(constraintReference1);
                            goto label_50;
                        }
                        case "bottom": {
                            constraintReference0.bottomToBottom(constraintReference1);
                            goto label_50;
                        }
                        case "top": {
                            constraintReference0.bottomToTop(constraintReference1);
                            goto label_50;
                        }
                        default: {
                            goto label_50;
                        }
                    }
                }
                case "circular": {
                    constraintReference0.circularConstraint(constraintReference1, constraintSetParser$LayoutVariables0.get(cLArray0.get(1)), (cLArray0.size() <= 2 ? 0.0f : ConstraintSetParser.toPix(state0, constraintSetParser$LayoutVariables0.get(cLArray0.getOrNull(2)))));
                    z1 = true;
                    z2 = false;
                    break;
                }
                case "end": {
                    z1 = z;
                    z2 = true;
                    break;
                }
                case "left": {
                    z1 = true;
                    z2 = true;
                    break;
                }
                case "right": {
                    z1 = false;
                    z2 = true;
                    break;
                }
                case "start": {
                    z1 = !z;
                    z2 = true;
                    break;
                }
                case "top": {
                    s2.hashCode();
                    switch(s2) {
                        case "baseline": {
                            state0.baselineNeededFor(constraintReference1.getKey());
                            constraintReference0.topToBaseline(constraintReference1);
                            break;
                        }
                        case "bottom": {
                            constraintReference0.topToBottom(constraintReference1);
                            break;
                        }
                        case "top": {
                            constraintReference0.topToTop(constraintReference1);
                        }
                    }
                label_50:
                    z1 = true;
                    z2 = false;
                    break;
                }
                default: {
                    goto label_50;
                }
            }
            if(z2) {
                s2.hashCode();
                switch(s2) {
                    case "end": {
                        z3 = z;
                        break;
                    }
                    case "left": {
                        z3 = true;
                        break;
                    }
                    case "right": {
                        z3 = false;
                        break;
                    }
                    case "start": {
                        z3 = !z;
                        break;
                    }
                    default: {
                        z3 = true;
                    }
                }
                if(!z1) {
                    if(z3) {
                        constraintReference0.rightToLeft(constraintReference1);
                    }
                    else {
                        constraintReference0.rightToRight(constraintReference1);
                    }
                }
                else if(z3) {
                    constraintReference0.leftToLeft(constraintReference1);
                }
                else {
                    constraintReference0.leftToRight(constraintReference1);
                }
            }
            constraintReference0.margin(f).marginGone(f1);
            return;
        }
        String s3 = cLObject0.getStringOrNull(s);
        if(s3 != null) {
            ConstraintReference constraintReference2 = s3.equals("parent") ? state0.constraints(State.PARENT) : state0.constraints(s3);
            s.hashCode();
            switch(s) {
                case "baseline": {
                    state0.baselineNeededFor(constraintReference0.getKey());
                    state0.baselineNeededFor(constraintReference2.getKey());
                    constraintReference0.baselineToBaseline(constraintReference2);
                    break;
                }
                case "bottom": {
                    constraintReference0.bottomToBottom(constraintReference2);
                    return;
                }
                case "end": {
                    if(!z) {
                        constraintReference0.rightToRight(constraintReference2);
                        return;
                    }
                    constraintReference0.leftToLeft(constraintReference2);
                    return;
                }
                case "start": {
                    if(!z) {
                        constraintReference0.leftToLeft(constraintReference2);
                        return;
                    }
                    constraintReference0.rightToRight(constraintReference2);
                    return;
                }
                case "top": {
                    constraintReference0.topToTop(constraintReference2);
                }
            }
        }
    }

    static void parseConstraintSets(CoreMotionScene coreMotionScene0, CLObject cLObject0) throws CLParsingException {
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                String s = (String)object0;
                CLObject cLObject1 = cLObject0.getObject(s);
                String s1 = cLObject1.getStringOrNull("Extends");
                if(s1 != null && !s1.isEmpty()) {
                    String s2 = coreMotionScene0.getConstraintSet(s1);
                    if(s2 == null) {
                        continue;
                    }
                    CLObject cLObject2 = CLParser.parse(s2);
                    ArrayList arrayList1 = cLObject1.names();
                    if(arrayList1 == null) {
                        continue;
                    }
                    for(Object object1: arrayList1) {
                        String s3 = (String)object1;
                        CLElement cLElement0 = cLObject1.get(s3);
                        if(cLElement0 instanceof CLObject) {
                            ConstraintSetParser.override(cLObject2, s3, ((CLObject)cLElement0));
                        }
                    }
                    coreMotionScene0.setConstraintSetContent(s, cLObject2.toJSON());
                }
                else {
                    coreMotionScene0.setConstraintSetContent(s, cLObject1.toJSON());
                }
            }
        }
    }

    static void parseCustomProperties(CLObject cLObject0, ConstraintReference constraintReference0, String s) throws CLParsingException {
        CLObject cLObject1 = cLObject0.getObjectOrNull(s);
        if(cLObject1 != null) {
            ArrayList arrayList0 = cLObject1.names();
            if(arrayList0 != null) {
                for(Object object0: arrayList0) {
                    String s1 = (String)object0;
                    CLElement cLElement0 = cLObject1.get(s1);
                    if(cLElement0 instanceof CLNumber) {
                        constraintReference0.addCustomFloat(s1, cLElement0.getFloat());
                    }
                    else if(cLElement0 instanceof CLString) {
                        long v = ConstraintSetParser.parseColorString(cLElement0.content());
                        if(v != -1L) {
                            constraintReference0.addCustomColor(s1, ((int)v));
                        }
                    }
                }
            }
        }
    }

    public static void parseDesignElementsJSON(String s, ArrayList arrayList0) throws CLParsingException {
        CLObject cLObject0 = CLParser.parse(s);
        ArrayList arrayList1 = cLObject0.names();
        if(arrayList1 != null && arrayList1.size() > 0) {
            String s1 = (String)arrayList1.get(0);
            CLElement cLElement0 = cLObject0.get(s1);
            s1.hashCode();
            if(s1.equals("Design") && cLElement0 instanceof CLObject) {
                ArrayList arrayList2 = ((CLObject)cLElement0).names();
                for(int v = 0; v < arrayList2.size(); ++v) {
                    String s2 = (String)arrayList2.get(v);
                    CLObject cLObject1 = (CLObject)((CLObject)cLElement0).get(s2);
                    System.out.printf("element found " + s2 + "", new Object[0]);
                    String s3 = cLObject1.getStringOrNull("type");
                    if(s3 != null) {
                        HashMap hashMap0 = new HashMap();
                        int v1 = cLObject1.size();
                        for(int v2 = 0; v2 < v1; ++v2) {
                            CLKey cLKey0 = (CLKey)cLObject1.get(v);
                            String s4 = cLKey0.content();
                            String s5 = cLKey0.getValue().content();
                            if(s5 != null) {
                                hashMap0.put(s4, s5);
                            }
                        }
                        arrayList0.add(new DesignElement(s1, s3, hashMap0));
                    }
                }
            }
        }
    }

    static Dimension parseDimension(CLObject cLObject0, String s, State state0, CorePixelDp corePixelDp0) throws CLParsingException {
        CLElement cLElement0 = cLObject0.get(s);
        Dimension dimension0 = Dimension.createFixed(0);
        if(cLElement0 instanceof CLString) {
            return ConstraintSetParser.parseDimensionMode(cLElement0.content());
        }
        if(cLElement0 instanceof CLNumber) {
            return Dimension.createFixed(state0.convertDimension(corePixelDp0.toPixels(cLObject0.getFloat(s))));
        }
        if(cLElement0 instanceof CLObject) {
            String s1 = ((CLObject)cLElement0).getStringOrNull("value");
            if(s1 != null) {
                dimension0 = ConstraintSetParser.parseDimensionMode(s1);
            }
            CLElement cLElement1 = ((CLObject)cLElement0).getOrNull("min");
            if(cLElement1 != null) {
                if(cLElement1 instanceof CLNumber) {
                    dimension0.min(state0.convertDimension(corePixelDp0.toPixels(((CLNumber)cLElement1).getFloat())));
                }
                else if(cLElement1 instanceof CLString) {
                    dimension0.min(Dimension.WRAP_DIMENSION);
                }
            }
            CLElement cLElement2 = ((CLObject)cLElement0).getOrNull("max");
            if(cLElement2 != null) {
                if(cLElement2 instanceof CLNumber) {
                    dimension0.max(state0.convertDimension(corePixelDp0.toPixels(((CLNumber)cLElement2).getFloat())));
                    return dimension0;
                }
                if(cLElement2 instanceof CLString) {
                    dimension0.max(Dimension.WRAP_DIMENSION);
                }
            }
        }
        return dimension0;
    }

    static Dimension parseDimensionMode(String s) {
        Dimension dimension0 = Dimension.createFixed(0);
        s.hashCode();
        switch(s) {
            case "parent": {
                return Dimension.createParent();
            }
            case "preferWrap": {
                return Dimension.createSuggested(Dimension.WRAP_DIMENSION);
            }
            case "spread": {
                return Dimension.createSuggested(Dimension.SPREAD_DIMENSION);
            }
            case "wrap": {
                return Dimension.createWrap();
            }
            default: {
                if(s.endsWith("%")) {
                    return Dimension.createPercent(0, Float.parseFloat(s.substring(0, s.indexOf(37))) / 100.0f).suggested(0);
                }
                return s.contains(":") ? Dimension.createRatio(s).suggested(Dimension.SPREAD_DIMENSION) : dimension0;
            }
        }
    }

    // This method was un-flattened
    private static void parseFlowType(String s, State state0, String s1, LayoutVariables constraintSetParser$LayoutVariables0, CLObject cLObject0) throws CLParsingException {
        String s10;
        String s9;
        String s8;
        Float float5;
        float f3;
        float f2;
        float f1;
        float f;
        String s6;
        String s5;
        String s4;
        Float float2;
        float f6;
        float f5;
        float f4;
        float f7;
        FlowReference flowReference0 = state0.getFlow(s1, s.charAt(0) == 0x76);
        for(Object object0: cLObject0.names()) {
            String s2 = (String)object0;
            s2.hashCode();
            switch(s2) {
                case "contains": {
                label_124:
                    CLElement cLElement5 = cLObject0.get(s2);
                    if(cLElement5 instanceof CLArray) {
                        CLArray cLArray0 = (CLArray)cLElement5;
                        if(cLArray0.size() >= 1) {
                            for(int v = 0; v < cLArray0.size(); ++v) {
                                CLElement cLElement6 = cLArray0.get(v);
                                if(!(cLElement6 instanceof CLArray)) {
                                    flowReference0.add(new Object[]{cLElement6.content()});
                                }
                                else if(((CLArray)cLElement6).size() > 0) {
                                    String s11 = ((CLArray)cLElement6).get(0).content();
                                    switch(((CLArray)cLElement6).size()) {
                                        case 2: {
                                            f5 = ((CLArray)cLElement6).getFloat(1);
                                            f4 = NaNf;
                                            f6 = NaNf;
                                            break;
                                        }
                                        case 3: {
                                            f7 = ((CLArray)cLElement6).getFloat(1);
                                            f4 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement6).getFloat(2));
                                            f6 = f4;
                                            f5 = f7;
                                            break;
                                        }
                                        case 4: {
                                            f7 = ((CLArray)cLElement6).getFloat(1);
                                            f6 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement6).getFloat(2));
                                            f4 = ConstraintSetParser.toPix(state0, ((CLArray)cLElement6).getFloat(3));
                                            f5 = f7;
                                            break;
                                        }
                                        default: {
                                            f4 = NaNf;
                                            f5 = NaNf;
                                            f6 = NaNf;
                                        }
                                    }
                                    flowReference0.addFlowElement(s11, f5, f6, f4);
                                }
                            }
                            continue;
                        }
                    }
                    System.err.println(s1 + " contains should be an array \"" + cLElement5.content() + "\"");
                    return;
                }
                case "hAlign": {
                    String s3 = cLObject0.get(s2).content();
                    s3.hashCode();
                    if(s3.equals("end")) {
                        flowReference0.setHorizontalAlign(1);
                        break;
                    }
                    else {
                        if(s3.equals("start")) {
                            flowReference0.setHorizontalAlign(0);
                        }
                        else {
                            flowReference0.setHorizontalAlign(2);
                        }
                        continue;
                    }
                    goto label_19;
                }
                case "hFlowBias": {
                    CLElement cLElement1 = cLObject0.get(s2);
                    Float float0 = 0.5f;
                    Float float1 = 0.5f;
                    if(!(cLElement1 instanceof CLArray) || ((CLArray)cLElement1).size() <= 1) {
                        float2 = cLElement1.getFloat();
                    }
                    else {
                        float0 = ((CLArray)cLElement1).getFloat(0);
                        float2 = ((CLArray)cLElement1).getFloat(1);
                        if(((CLArray)cLElement1).size() > 2) {
                            float1 = ((CLArray)cLElement1).getFloat(2);
                        }
                    }
                    try {
                        flowReference0.horizontalBias(((float)float2));
                        if(((float)float0) != 0.5f) {
                            flowReference0.setFirstHorizontalBias(((float)float0));
                        }
                        if(((float)float1) == 0.5f) {
                            continue;
                        }
                        flowReference0.setLastHorizontalBias(((float)float1));
                        break;
                    }
                    catch(NumberFormatException unused_ex) {
                        goto label_123;
                    }
                }
                case "hGap": {
                    flowReference0.setHorizontalGap(cLObject0.get(s2).getInt());
                    break;
                }
                case "hStyle": {
                label_19:
                    CLElement cLElement0 = cLObject0.get(s2);
                    if(!(cLElement0 instanceof CLArray) || ((CLArray)cLElement0).size() <= 1) {
                        s5 = cLElement0.content();
                        s4 = "";
                        s6 = "";
                    }
                    else {
                        s4 = ((CLArray)cLElement0).getString(0);
                        s5 = ((CLArray)cLElement0).getString(1);
                        s6 = ((CLArray)cLElement0).size() > 2 ? ((CLArray)cLElement0).getString(2) : "";
                    }
                    if(!s5.equals("")) {
                        flowReference0.setHorizontalStyle(Chain.getValueByString(s5));
                    }
                    if(!s4.equals("")) {
                        flowReference0.setFirstHorizontalStyle(Chain.getValueByString(s4));
                    }
                    if(s6.equals("")) {
                        continue;
                    }
                    flowReference0.setLastHorizontalStyle(Chain.getValueByString(s6));
                    break;
                }
                case "maxElement": {
                    flowReference0.setMaxElementsWrap(cLObject0.get(s2).getInt());
                    break;
                }
                case "padding": {
                    CLElement cLElement3 = cLObject0.get(s2);
                    if(!(cLElement3 instanceof CLArray) || ((CLArray)cLElement3).size() <= 1) {
                        f = (float)cLElement3.getInt();
                        f3 = f;
                        f2 = f3;
                        f1 = f2;
                    }
                    else {
                        f = (float)((CLArray)cLElement3).getInt(0);
                        f1 = (float)((CLArray)cLElement3).getInt(1);
                        if(((CLArray)cLElement3).size() > 2) {
                            f2 = (float)((CLArray)cLElement3).getInt(2);
                            try {
                                f3 = (float)((CLArray)cLElement3).getInt(3);
                            }
                            catch(ArrayIndexOutOfBoundsException unused_ex) {
                                f3 = 0.0f;
                            }
                        }
                        else {
                            f2 = f;
                            f3 = f1;
                        }
                    }
                    flowReference0.setPaddingLeft(Math.round(ConstraintSetParser.toPix(state0, f)));
                    flowReference0.setPaddingTop(Math.round(ConstraintSetParser.toPix(state0, f1)));
                    flowReference0.setPaddingRight(Math.round(ConstraintSetParser.toPix(state0, f2)));
                    flowReference0.setPaddingBottom(Math.round(ConstraintSetParser.toPix(state0, f3)));
                    break;
                }
                case "type": {
                    if(cLObject0.get(s2).content().equals("hFlow")) {
                        flowReference0.setOrientation(0);
                        continue;
                    }
                    else {
                        flowReference0.setOrientation(1);
                        break;
                    }
                    flowReference0.setVerticalGap(cLObject0.get(s2).getInt());
                    break;
                }
                case "vAlign": {
                    String s7 = cLObject0.get(s2).content();
                    s7.hashCode();
                    switch(s7) {
                        case "baseline": {
                            flowReference0.setVerticalAlign(3);
                            break;
                        }
                        case "bottom": {
                            flowReference0.setVerticalAlign(1);
                            break;
                        }
                        case "top": {
                            flowReference0.setVerticalAlign(0);
                            break;
                        }
                        default: {
                            flowReference0.setVerticalAlign(2);
                        }
                    }
                    break;
                }
                case "vFlowBias": {
                    CLElement cLElement4 = cLObject0.get(s2);
                    Float float3 = 0.5f;
                    Float float4 = 0.5f;
                    if(!(cLElement4 instanceof CLArray) || ((CLArray)cLElement4).size() <= 1) {
                        float5 = cLElement4.getFloat();
                    }
                    else {
                        float3 = ((CLArray)cLElement4).getFloat(0);
                        float5 = ((CLArray)cLElement4).getFloat(1);
                        if(((CLArray)cLElement4).size() > 2) {
                            float4 = ((CLArray)cLElement4).getFloat(2);
                        }
                    }
                    try {
                        flowReference0.verticalBias(((float)float5));
                        if(((float)float3) != 0.5f) {
                            flowReference0.setFirstVerticalBias(((float)float3));
                        }
                        if(((float)float4) == 0.5f) {
                            continue;
                        }
                        flowReference0.setLastVerticalBias(((float)float4));
                        goto label_123;
                    }
                    catch(NumberFormatException unused_ex) {
                    label_123:
                        break;
                    }
                    goto label_124;
                }
                case "vGap": {
                    flowReference0.setVerticalGap(cLObject0.get(s2).getInt());
                    break;
                }
                case "vStyle": {
                    CLElement cLElement2 = cLObject0.get(s2);
                    if(!(cLElement2 instanceof CLArray) || ((CLArray)cLElement2).size() <= 1) {
                        s9 = cLElement2.content();
                        s8 = "";
                        s10 = "";
                    }
                    else {
                        s8 = ((CLArray)cLElement2).getString(0);
                        s9 = ((CLArray)cLElement2).getString(1);
                        s10 = ((CLArray)cLElement2).size() > 2 ? ((CLArray)cLElement2).getString(2) : "";
                    }
                    if(!s9.equals("")) {
                        flowReference0.setVerticalStyle(Chain.getValueByString(s9));
                    }
                    if(!s8.equals("")) {
                        flowReference0.setFirstVerticalStyle(Chain.getValueByString(s8));
                    }
                    if(s10.equals("")) {
                        continue;
                    }
                    flowReference0.setLastVerticalStyle(Chain.getValueByString(s10));
                    break;
                }
                case "wrap": {
                    flowReference0.setWrapMode(Wrap.getValueByString(cLObject0.get(s2).content()));
                    break;
                }
                default: {
                    ConstraintSetParser.applyAttribute(state0, constraintSetParser$LayoutVariables0, state0.constraints(s1), cLObject0, s2);
                }
            }
        }
    }

    static void parseGenerate(State state0, LayoutVariables constraintSetParser$LayoutVariables0, CLObject cLObject0) throws CLParsingException {
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                CLElement cLElement0 = cLObject0.get(((String)object0));
                ArrayList arrayList1 = constraintSetParser$LayoutVariables0.getList(((String)object0));
                if(arrayList1 != null && cLElement0 instanceof CLObject) {
                    for(Object object1: arrayList1) {
                        ConstraintSetParser.parseWidget(state0, constraintSetParser$LayoutVariables0, ((String)object1), ((CLObject)cLElement0));
                    }
                }
            }
        }
    }

    private static void parseGridType(String s, State state0, String s1, LayoutVariables constraintSetParser$LayoutVariables0, CLObject cLObject0) throws CLParsingException {
        float f3;
        float f2;
        float f1;
        float f;
        String s3;
        String s7;
        GridReference gridReference0 = state0.getGrid(s1, s);
        for(Object object0: cLObject0.names()) {
            String s2 = (String)object0;
            s2.hashCode();
            int v = 0;
            switch(s2) {
                case "columnWeights": {
                    s7 = cLObject0.get(s2).content();
                    if(s7 != null && s7.contains(",")) {
                        break;
                    }
                    continue;
                }
                case "columns": {
                    int v3 = cLObject0.get(s2).getInt();
                    if(v3 <= 0) {
                        continue;
                    }
                    gridReference0.setColumnsSet(v3);
                    continue;
                }
                case "contains": {
                    CLArray cLArray0 = cLObject0.getArrayOrNull(s2);
                    if(cLArray0 == null) {
                        continue;
                    }
                    for(int v1 = 0; v1 < cLArray0.size(); ++v1) {
                        gridReference0.add(new Object[]{state0.constraints(cLArray0.get(v1).content())});
                    }
                    continue;
                }
                case "flags": {
                    try {
                        s3 = "";
                        CLElement cLElement1 = cLObject0.get(s2);
                        if(cLElement1 instanceof CLNumber) {
                            v = cLElement1.getInt();
                        }
                        else {
                            s3 = cLElement1.content();
                        }
                    }
                    catch(Exception exception0) {
                        System.err.println("Error parsing grid flags " + exception0);
                    }
                    if(s3 == null || s3.isEmpty()) {
                        gridReference0.setFlags(v);
                    }
                    else {
                        gridReference0.setFlags(s3);
                    }
                    continue;
                }
                case "hGap": {
                    gridReference0.setHorizontalGaps(ConstraintSetParser.toPix(state0, cLObject0.get(s2).getFloat()));
                    continue;
                }
                case "orientation": {
                    gridReference0.setOrientation(cLObject0.get(s2).getInt());
                    continue;
                }
                case "padding": {
                    CLElement cLElement0 = cLObject0.get(s2);
                    if(!(cLElement0 instanceof CLArray) || ((CLArray)cLElement0).size() <= 1) {
                        f = (float)cLElement0.getInt();
                        f3 = f;
                        f2 = f3;
                        f1 = f2;
                    }
                    else {
                        f = (float)((CLArray)cLElement0).getInt(0);
                        f1 = (float)((CLArray)cLElement0).getInt(1);
                        if(((CLArray)cLElement0).size() > 2) {
                            f2 = (float)((CLArray)cLElement0).getInt(2);
                            try {
                                f3 = (float)((CLArray)cLElement0).getInt(3);
                            }
                            catch(ArrayIndexOutOfBoundsException unused_ex) {
                                f3 = 0.0f;
                            }
                        }
                        else {
                            f3 = f1;
                            f2 = f;
                        }
                    }
                    gridReference0.setPaddingStart(Math.round(ConstraintSetParser.toPix(state0, f)));
                    gridReference0.setPaddingTop(Math.round(ConstraintSetParser.toPix(state0, f1)));
                    gridReference0.setPaddingEnd(Math.round(ConstraintSetParser.toPix(state0, f2)));
                    gridReference0.setPaddingBottom(Math.round(ConstraintSetParser.toPix(state0, f3)));
                    continue;
                }
                case "rowWeights": {
                    String s6 = cLObject0.get(s2).content();
                    if(s6 == null || !s6.contains(",")) {
                        continue;
                    }
                    gridReference0.setRowWeights(s6);
                    continue;
                }
                case "rows": {
                    int v2 = cLObject0.get(s2).getInt();
                    if(v2 <= 0) {
                        continue;
                    }
                    gridReference0.setRowsSet(v2);
                    continue;
                }
                case "skips": {
                    String s4 = cLObject0.get(s2).content();
                    if(s4 == null || !s4.contains(":")) {
                        continue;
                    }
                    gridReference0.setSkips(s4);
                    continue;
                }
                case "spans": {
                    String s5 = cLObject0.get(s2).content();
                    if(s5 == null || !s5.contains(":")) {
                        continue;
                    }
                    gridReference0.setSpans(s5);
                    continue;
                }
                case "vGap": {
                    gridReference0.setVerticalGaps(ConstraintSetParser.toPix(state0, cLObject0.get(s2).getFloat()));
                    continue;
                }
                default: {
                    ConstraintSetParser.applyAttribute(state0, constraintSetParser$LayoutVariables0, state0.constraints(s1), cLObject0, s2);
                    continue;
                }
            }
            gridReference0.setColumnWeights(s7);
        }
    }

    static void parseGuideline(int v, State state0, CLArray cLArray0) throws CLParsingException {
        CLElement cLElement0 = cLArray0.get(1);
        if(cLElement0 instanceof CLObject) {
            String s = ((CLObject)cLElement0).getStringOrNull("id");
            if(s != null) {
                ConstraintSetParser.parseGuidelineParams(v, state0, s, ((CLObject)cLElement0));
            }
        }
    }

    // This method was un-flattened
    static void parseGuidelineParams(int v, State state0, String s, CLObject cLObject0) throws CLParsingException {
        String s1;
        int v2;
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 == null) {
            return;
        }
        ConstraintReference constraintReference0 = state0.constraints(s);
        if(v == 0) {
            state0.horizontalGuideline(s);
        }
        else {
            state0.verticalGuideline(s);
        }
        int v1 = !state0.isRtl() || v == 0 ? 1 : 0;
        GuidelineReference guidelineReference0 = (GuidelineReference)constraintReference0.getFacade();
        Iterator iterator0 = arrayList0.iterator();
        float f = 0.0f;
        boolean z = false;
    alab2:
        while(true) {
            v2 = 1;
        label_14:
            if(!iterator0.hasNext()) {
                goto label_56;
            }
            Object object0 = iterator0.next();
            s1 = (String)object0;
            s1.hashCode();
        alab1:
            switch(s1) {
                case "end": {
                    f = ConstraintSetParser.toPix(state0, cLObject0.getFloat(s1));
                    v2 = v1 ^ 1;
                    goto label_14;
                }
                case "left": {
                    f = ConstraintSetParser.toPix(state0, cLObject0.getFloat(s1));
                    break;
                }
                case "percent": {
                    CLArray cLArray0 = cLObject0.getArrayOrNull(s1);
                    if(cLArray0 == null) {
                        f = cLObject0.getFloat(s1);
                        z = true;
                        break;
                    }
                    else {
                        if(cLArray0.size() > 1) {
                            String s2 = cLArray0.getString(0);
                            float f1 = cLArray0.getFloat(1);
                            s2.hashCode();
                            switch(s2) {
                                case "end": {
                                    v2 = v1 ^ 1;
                                    f = f1;
                                    break;
                                }
                                case "left": {
                                    f = f1;
                                    z = true;
                                    break alab1;
                                }
                                case "right": {
                                    f = f1;
                                    z = true;
                                    v2 = 0;
                                    goto label_14;
                                }
                                case "start": {
                                    v2 = v1;
                                    f = f1;
                                    break;
                                }
                                default: {
                                    f = f1;
                                }
                            }
                        }
                        z = true;
                        goto label_14;
                    }
                    f = ConstraintSetParser.toPix(state0, cLObject0.getFloat(s1));
                    v2 = v1 ^ 1;
                    goto label_14;
                }
                case "right": {
                    break alab2;
                }
                case "start": {
                    f = ConstraintSetParser.toPix(state0, cLObject0.getFloat(s1));
                    v2 = v1;
                    goto label_14;
                }
                default: {
                    goto label_14;
                }
            }
        }
        f = ConstraintSetParser.toPix(state0, cLObject0.getFloat(s1));
        v2 = 0;
        goto label_14;
    label_56:
        if(z) {
            if(v2 != 0) {
                guidelineReference0.percent(f);
                return;
            }
            guidelineReference0.percent(1.0f - f);
            return;
        }
        if(v2 != 0) {
            guidelineReference0.start(f);
            return;
        }
        guidelineReference0.end(f);
    }

    static void parseHeader(CoreMotionScene coreMotionScene0, CLObject cLObject0) {
        String s = cLObject0.getStringOrNull("export");
        if(s != null) {
            coreMotionScene0.setDebugName(s);
        }
    }

    static void parseHelpers(State state0, LayoutVariables constraintSetParser$LayoutVariables0, CLArray cLArray0) throws CLParsingException {
        for(int v = 0; v < cLArray0.size(); ++v) {
            CLElement cLElement0 = cLArray0.get(v);
            if(cLElement0 instanceof CLArray && ((CLArray)cLElement0).size() > 1) {
                String s = ((CLArray)cLElement0).getString(0);
                s.hashCode();
                switch(s) {
                    case "hChain": {
                        ConstraintSetParser.parseChain(0, state0, constraintSetParser$LayoutVariables0, ((CLArray)cLElement0));
                        break;
                    }
                    case "hGuideline": {
                        ConstraintSetParser.parseGuideline(0, state0, ((CLArray)cLElement0));
                        break;
                    }
                    case "vChain": {
                        ConstraintSetParser.parseChain(1, state0, constraintSetParser$LayoutVariables0, ((CLArray)cLElement0));
                        break;
                    }
                    case "vGuideline": {
                        ConstraintSetParser.parseGuideline(1, state0, ((CLArray)cLElement0));
                    }
                }
            }
        }
    }

    public static void parseJSON(String s, State state0, LayoutVariables constraintSetParser$LayoutVariables0) throws CLParsingException {
        try {
            ConstraintSetParser.populateState(CLParser.parse(s), state0, constraintSetParser$LayoutVariables0);
        }
        catch(CLParsingException cLParsingException0) {
            System.err.println("Error parsing JSON " + cLParsingException0);
        }
    }

    public static void parseJSON(String s, Transition transition0, int v) {
        try {
            CLObject cLObject0 = CLParser.parse(s);
            ArrayList arrayList0 = cLObject0.names();
            if(arrayList0 != null) {
                for(Object object0: arrayList0) {
                    String s1 = (String)object0;
                    CLElement cLElement0 = cLObject0.get(s1);
                    if(cLElement0 instanceof CLObject) {
                        CLObject cLObject1 = ((CLObject)cLElement0).getObjectOrNull("custom");
                        if(cLObject1 != null) {
                            for(Object object1: cLObject1.names()) {
                                String s2 = (String)object1;
                                CLElement cLElement1 = cLObject1.get(s2);
                                if(cLElement1 instanceof CLNumber) {
                                    transition0.addCustomFloat(v, s1, s2, cLElement1.getFloat());
                                }
                                else if(cLElement1 instanceof CLString) {
                                    long v1 = ConstraintSetParser.parseColorString(cLElement1.content());
                                    if(v1 != -1L) {
                                        transition0.addCustomColor(v, s1, s2, ((int)v1));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(CLParsingException cLParsingException0) {
            System.err.println("Error parsing JSON " + cLParsingException0);
        }
    }

    // This method was un-flattened
    private static void parseMotionProperties(CLElement cLElement0, ConstraintReference constraintReference0) throws CLParsingException {
        if(cLElement0 instanceof CLObject) {
            TypedBundle typedBundle0 = new TypedBundle();
            ArrayList arrayList0 = ((CLObject)cLElement0).names();
            if(arrayList0 != null) {
                for(Object object0: arrayList0) {
                    String s = (String)object0;
                    s.hashCode();
                    switch(s) {
                        case "easing": {
                            typedBundle0.add(603, ((CLObject)cLElement0).getString(s));
                            break;
                        }
                        case "pathArc": {
                        label_27:
                            String s1 = ((CLObject)cLElement0).getString(s);
                            int v1 = ConstraintSetParser.indexOf(s1, new String[]{"none", "startVertical", "startHorizontal", "flip", "below", "above"});
                            if(v1 == -1) {
                                System.err.println(((CLObject)cLElement0).getLine() + " pathArc = \'" + s1 + "\'");
                                continue;
                            }
                            else {
                                typedBundle0.add(607, v1);
                                break;
                            }
                            typedBundle0.add(605, ((CLObject)cLElement0).getString(s));
                            break;
                        }
                        case "quantize": {
                            CLElement cLElement1 = ((CLObject)cLElement0).get(s);
                            if(cLElement1 instanceof CLArray) {
                                int v = ((CLArray)cLElement1).size();
                                if(v <= 0) {
                                    continue;
                                }
                                typedBundle0.add(610, ((CLArray)cLElement1).getInt(0));
                                if(v <= 1) {
                                    continue;
                                }
                                typedBundle0.add(611, ((CLArray)cLElement1).getString(1));
                                if(v <= 2) {
                                    continue;
                                }
                                typedBundle0.add(602, ((CLArray)cLElement1).getFloat(2));
                                continue;
                            }
                            else {
                                typedBundle0.add(610, ((CLObject)cLElement0).getInt(s));
                                break;
                            }
                            goto label_27;
                        }
                        case "relativeTo": {
                            typedBundle0.add(605, ((CLObject)cLElement0).getString(s));
                            break;
                        }
                        case "stagger": {
                            typedBundle0.add(600, ((CLObject)cLElement0).getFloat(s));
                        }
                    }
                }
                constraintReference0.mMotionProperties = typedBundle0;
            }
        }
    }

    // This method was un-flattened
    public static void parseMotionSceneJSON(CoreMotionScene coreMotionScene0, String s) {
        try {
            CLObject cLObject0 = CLParser.parse(s);
            ArrayList arrayList0 = cLObject0.names();
            if(arrayList0 != null) {
                Iterator iterator0 = arrayList0.iterator();
                while(true) {
                    if(!iterator0.hasNext()) {
                        break;
                    }
                    Object object0 = iterator0.next();
                    String s1 = (String)object0;
                    CLElement cLElement0 = cLObject0.get(s1);
                    if(cLElement0 instanceof CLObject) {
                        switch(s1) {
                            case "ConstraintSets": {
                                ConstraintSetParser.parseConstraintSets(coreMotionScene0, ((CLObject)cLElement0));
                                break;
                            }
                            case "Header": {
                                ConstraintSetParser.parseHeader(coreMotionScene0, ((CLObject)cLElement0));
                                break;
                            }
                            case "Transitions": {
                                ConstraintSetParser.parseTransitions(coreMotionScene0, ((CLObject)cLElement0));
                            }
                        }
                    }
                }
            }
        }
        catch(CLParsingException cLParsingException0) {
            System.err.println("Error parsing JSON " + cLParsingException0);
        }
    }

    static void parseTransitions(CoreMotionScene coreMotionScene0, CLObject cLObject0) throws CLParsingException {
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                coreMotionScene0.setTransitionContent(((String)object0), cLObject0.getObject(((String)object0)).toJSON());
            }
        }
    }

    private static void parseVariables(State state0, LayoutVariables constraintSetParser$LayoutVariables0, CLObject cLObject0) throws CLParsingException {
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                String s = (String)object0;
                CLElement cLElement0 = cLObject0.get(s);
                if(cLElement0 instanceof CLNumber) {
                    constraintSetParser$LayoutVariables0.put(s, cLElement0.getInt());
                }
                else if(!(cLElement0 instanceof CLObject)) {
                }
                else if(((CLObject)cLElement0).has("from") && ((CLObject)cLElement0).has("to")) {
                    constraintSetParser$LayoutVariables0.put(s, constraintSetParser$LayoutVariables0.get(((CLObject)cLElement0).get("from")), constraintSetParser$LayoutVariables0.get(((CLObject)cLElement0).get("to")), 1.0f, ((CLObject)cLElement0).getStringOrNull("prefix"), ((CLObject)cLElement0).getStringOrNull("postfix"));
                }
                else if(((CLObject)cLElement0).has("from") && ((CLObject)cLElement0).has("step")) {
                    constraintSetParser$LayoutVariables0.put(s, constraintSetParser$LayoutVariables0.get(((CLObject)cLElement0).get("from")), constraintSetParser$LayoutVariables0.get(((CLObject)cLElement0).get("step")));
                }
                else if(((CLObject)cLElement0).has("ids")) {
                    CLArray cLArray0 = ((CLObject)cLElement0).getArray("ids");
                    ArrayList arrayList1 = new ArrayList();
                    for(int v = 0; v < cLArray0.size(); ++v) {
                        arrayList1.add(cLArray0.getString(v));
                    }
                    constraintSetParser$LayoutVariables0.put(s, arrayList1);
                }
                else if(((CLObject)cLElement0).has("tag")) {
                    constraintSetParser$LayoutVariables0.put(s, state0.getIdsForTag(((CLObject)cLElement0).getString("tag")));
                }
            }
        }
    }

    static void parseWidget(State state0, LayoutVariables constraintSetParser$LayoutVariables0, ConstraintReference constraintReference0, CLObject cLObject0) throws CLParsingException {
        if(constraintReference0.getWidth() == null) {
            constraintReference0.setWidth(Dimension.createWrap());
        }
        if(constraintReference0.getHeight() == null) {
            constraintReference0.setHeight(Dimension.createWrap());
        }
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                ConstraintSetParser.applyAttribute(state0, constraintSetParser$LayoutVariables0, constraintReference0, cLObject0, ((String)object0));
            }
        }
    }

    static void parseWidget(State state0, LayoutVariables constraintSetParser$LayoutVariables0, String s, CLObject cLObject0) throws CLParsingException {
        ConstraintSetParser.parseWidget(state0, constraintSetParser$LayoutVariables0, state0.constraints(s), cLObject0);
    }

    // This method was un-flattened
    public static void populateState(CLObject cLObject0, State state0, LayoutVariables constraintSetParser$LayoutVariables0) throws CLParsingException {
        ArrayList arrayList0 = cLObject0.names();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                String s = (String)object0;
                CLElement cLElement0 = cLObject0.get(s);
                s.hashCode();
                switch(s) {
                    case "Generate": {
                        if(!(cLElement0 instanceof CLObject)) {
                            continue;
                        }
                        ConstraintSetParser.parseGenerate(state0, constraintSetParser$LayoutVariables0, ((CLObject)cLElement0));
                        continue;
                    }
                    case "Helpers": {
                        if(!(cLElement0 instanceof CLArray)) {
                            continue;
                        }
                        ConstraintSetParser.parseHelpers(state0, constraintSetParser$LayoutVariables0, ((CLArray)cLElement0));
                        continue;
                    }
                    case "Variables": {
                        if(cLElement0 instanceof CLObject) {
                            break;
                        }
                        continue;
                    }
                    default: {
                        if(cLElement0 instanceof CLObject) {
                            String s1 = ConstraintSetParser.lookForType(((CLObject)cLElement0));
                            if(s1 == null) {
                                ConstraintSetParser.parseWidget(state0, constraintSetParser$LayoutVariables0, s, ((CLObject)cLElement0));
                            }
                            else {
                                s1.hashCode();
                                switch(s1) {
                                    case "barrier": {
                                        ConstraintSetParser.parseBarrier(state0, s, ((CLObject)cLElement0));
                                        break;
                                    }
                                    case "column": {
                                        ConstraintSetParser.parseGridType(s1, state0, s, constraintSetParser$LayoutVariables0, ((CLObject)cLElement0));
                                        break;
                                    }
                                    case "grid": {
                                        ConstraintSetParser.parseGridType(s1, state0, s, constraintSetParser$LayoutVariables0, ((CLObject)cLElement0));
                                        break;
                                    }
                                    case "hChain": 
                                    case "vChain": {
                                        ConstraintSetParser.parseChainType(s1, state0, s, constraintSetParser$LayoutVariables0, ((CLObject)cLElement0));
                                        break;
                                    }
                                    case "hFlow": 
                                    case "vFlow": {
                                        ConstraintSetParser.parseFlowType(s1, state0, s, constraintSetParser$LayoutVariables0, ((CLObject)cLElement0));
                                        break;
                                    }
                                    case "hGuideline": {
                                        ConstraintSetParser.parseGuidelineParams(0, state0, s, ((CLObject)cLElement0));
                                        break;
                                    }
                                    case "row": {
                                        ConstraintSetParser.parseGridType(s1, state0, s, constraintSetParser$LayoutVariables0, ((CLObject)cLElement0));
                                        break;
                                    }
                                    case "vGuideline": {
                                        ConstraintSetParser.parseGuidelineParams(1, state0, s, ((CLObject)cLElement0));
                                    }
                                }
                            }
                        }
                        else {
                            if(!(cLElement0 instanceof CLNumber)) {
                                continue;
                            }
                            constraintSetParser$LayoutVariables0.put(s, cLElement0.getInt());
                        }
                        continue;
                    }
                }
                ConstraintSetParser.parseVariables(state0, constraintSetParser$LayoutVariables0, ((CLObject)cLElement0));
            }
        }
    }

    private static float toPix(State state0, float f) {
        return state0.getDpToPixel().toPixels(f);
    }
}

