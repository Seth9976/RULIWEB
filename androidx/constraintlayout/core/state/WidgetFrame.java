package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.widgets.ConstraintAnchor.Type;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.HashMap;
import java.util.Set;

public class WidgetFrame {
    public float alpha;
    public int bottom;
    public float interpolatedPos;
    public int left;
    private final HashMap mCustom;
    TypedBundle mMotionProperties;
    public String name;
    public static float phone_orientation = NaNf;
    public float pivotX;
    public float pivotY;
    public int right;
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public float scaleX;
    public float scaleY;
    public int top;
    public float translationX;
    public float translationY;
    public float translationZ;
    public int visibility;
    public ConstraintWidget widget;

    static {
    }

    public WidgetFrame() {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = NaNf;
        this.pivotY = NaNf;
        this.rotationX = NaNf;
        this.rotationY = NaNf;
        this.rotationZ = NaNf;
        this.translationX = NaNf;
        this.translationY = NaNf;
        this.translationZ = NaNf;
        this.scaleX = NaNf;
        this.scaleY = NaNf;
        this.alpha = NaNf;
        this.interpolatedPos = NaNf;
        this.visibility = 0;
        this.mCustom = new HashMap();
        this.name = null;
    }

    public WidgetFrame(WidgetFrame widgetFrame0) {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = NaNf;
        this.pivotY = NaNf;
        this.rotationX = NaNf;
        this.rotationY = NaNf;
        this.rotationZ = NaNf;
        this.translationX = NaNf;
        this.translationY = NaNf;
        this.translationZ = NaNf;
        this.scaleX = NaNf;
        this.scaleY = NaNf;
        this.alpha = NaNf;
        this.interpolatedPos = NaNf;
        this.visibility = 0;
        this.mCustom = new HashMap();
        this.name = null;
        this.widget = widgetFrame0.widget;
        this.left = widgetFrame0.left;
        this.top = widgetFrame0.top;
        this.right = widgetFrame0.right;
        this.bottom = widgetFrame0.bottom;
        this.updateAttributes(widgetFrame0);
    }

    public WidgetFrame(ConstraintWidget constraintWidget0) {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = NaNf;
        this.pivotY = NaNf;
        this.rotationX = NaNf;
        this.rotationY = NaNf;
        this.rotationZ = NaNf;
        this.translationX = NaNf;
        this.translationY = NaNf;
        this.translationZ = NaNf;
        this.scaleX = NaNf;
        this.scaleY = NaNf;
        this.alpha = NaNf;
        this.interpolatedPos = NaNf;
        this.visibility = 0;
        this.mCustom = new HashMap();
        this.name = null;
        this.widget = constraintWidget0;
    }

    private static void add(StringBuilder stringBuilder0, String s, float f) {
        if(Float.isNaN(f)) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(": ");
        stringBuilder0.append(f);
        stringBuilder0.append(",\n");
    }

    private static void add(StringBuilder stringBuilder0, String s, int v) {
        stringBuilder0.append(s);
        stringBuilder0.append(": ");
        stringBuilder0.append(v);
        stringBuilder0.append(",\n");
    }

    public void addCustomColor(String s, int v) {
        this.setCustomAttribute(s, 902, v);
    }

    public void addCustomFloat(String s, float f) {
        this.setCustomAttribute(s, 901, f);
    }

    public float centerX() {
        return ((float)this.left) + ((float)(this.right - this.left)) / 2.0f;
    }

    public float centerY() {
        return ((float)this.top) + ((float)(this.bottom - this.top)) / 2.0f;
    }

    public boolean containsCustom(String s) {
        return this.mCustom.containsKey(s);
    }

    public CustomVariable getCustomAttribute(String s) {
        return (CustomVariable)this.mCustom.get(s);
    }

    public Set getCustomAttributeNames() {
        return this.mCustom.keySet();
    }

    // 去混淆评级： 低(20)
    public int getCustomColor(String s) {
        return this.mCustom.containsKey(s) ? ((CustomVariable)this.mCustom.get(s)).getColorValue() : -21880;
    }

    // 去混淆评级： 低(20)
    public float getCustomFloat(String s) {
        return this.mCustom.containsKey(s) ? ((CustomVariable)this.mCustom.get(s)).getFloatValue() : NaNf;
    }

    public String getId() [...] // 潜在的解密器

    public TypedBundle getMotionProperties() {
        return this.mMotionProperties;
    }

    public int height() {
        return Math.max(0, this.bottom - this.top);
    }

    private static float interpolate(float f, float f1, float f2, float f3) {
        boolean z = Float.isNaN(f);
        boolean z1 = Float.isNaN(f1);
        if(z && z1) {
            return NaNf;
        }
        if(z) {
            f = f2;
        }
        if(z1) {
            f1 = f2;
        }
        return f + f3 * (f1 - f);
    }

    public static void interpolate(int v, int v1, WidgetFrame widgetFrame0, WidgetFrame widgetFrame1, WidgetFrame widgetFrame2, Transition transition0, float f) {
        int v20;
        float f6;
        int v19;
        int v18;
        int v17;
        int v16;
        int v15;
        int v14;
        float f3;
        int v13;
        int v12;
        int v11;
        int v2 = widgetFrame2.left;
        int v3 = widgetFrame2.top;
        int v4 = widgetFrame1.right - widgetFrame1.left;
        int v5 = widgetFrame1.bottom - widgetFrame1.top;
        int v6 = widgetFrame2.right - v2;
        int v7 = widgetFrame2.bottom - v3;
        int v8 = widgetFrame1.left;
        float f1 = widgetFrame1.alpha;
        float f2 = widgetFrame2.alpha;
        int v9 = widgetFrame1.top;
        if(widgetFrame1.visibility == 8) {
            int v10 = v8 - ((int)(((float)v6) / 2.0f));
            v11 = v9 - ((int)(((float)v7) / 2.0f));
            if(Float.isNaN(f1)) {
                v12 = v7;
                v8 = v10;
                v13 = v6;
                f3 = 0.0f;
            }
            else {
                v8 = v10;
                v13 = v6;
                f3 = f1;
                v12 = v7;
            }
        }
        else {
            v12 = v7;
            v7 = v5;
            v13 = v4;
            v11 = v9;
            f3 = f1;
        }
        if(widgetFrame2.visibility == 8) {
            v2 -= (int)(((float)v13) / 2.0f);
            v3 -= (int)(((float)v7) / 2.0f);
            if(Float.isNaN(f2)) {
                v14 = v7;
                v6 = v13;
                f2 = 0.0f;
            }
            else {
                v14 = v7;
                v6 = v13;
            }
        }
        else {
            v14 = v12;
        }
        if(Float.isNaN(f3) && !Float.isNaN(f2)) {
            f3 = 1.0f;
        }
        if(!Float.isNaN(f3) && Float.isNaN(f2)) {
            f2 = 1.0f;
        }
        float f4 = widgetFrame1.visibility == 4 ? 0.0f : f3;
        float f5 = widgetFrame2.visibility == 4 ? 0.0f : f2;
        if(widgetFrame0.widget == null || !transition0.hasPositionKeyframes()) {
            f6 = f;
            v17 = v2;
            v20 = v3;
            v16 = v11;
        }
        else {
            KeyPosition transition$KeyPosition0 = transition0.findPreviousPosition(widgetFrame0.widget.stringId, ((int)(100.0f * f)));
            KeyPosition transition$KeyPosition1 = transition0.findNextPosition(widgetFrame0.widget.stringId, ((int)(100.0f * f)));
            if(transition$KeyPosition0 == transition$KeyPosition1) {
                transition$KeyPosition1 = null;
            }
            if(transition$KeyPosition0 == null) {
                v16 = v11;
                v15 = 0;
            }
            else {
                v8 = (int)(transition$KeyPosition0.mX * ((float)v));
                v15 = transition$KeyPosition0.mFrame;
                v16 = (int)(transition$KeyPosition0.mY * ((float)v1));
            }
            if(transition$KeyPosition1 == null) {
                v19 = 100;
                v17 = v2;
                v18 = v3;
            }
            else {
                v17 = (int)(transition$KeyPosition1.mX * ((float)v));
                v18 = (int)(transition$KeyPosition1.mY * ((float)v1));
                v19 = transition$KeyPosition1.mFrame;
            }
            f6 = (100.0f * f - ((float)v15)) / ((float)(v19 - v15));
            v20 = v18;
        }
        widgetFrame0.widget = widgetFrame1.widget;
        int v21 = (int)(((float)v8) + ((float)(v17 - v8)) * f6);
        widgetFrame0.left = v21;
        int v22 = (int)(((float)v16) + f6 * ((float)(v20 - v16)));
        widgetFrame0.top = v22;
        widgetFrame0.right = v21 + ((int)(((float)v13) * (1.0f - f) + ((float)v6) * f));
        widgetFrame0.bottom = v22 + ((int)((1.0f - f) * ((float)v7) + ((float)v14) * f));
        widgetFrame0.pivotX = WidgetFrame.interpolate(widgetFrame1.pivotX, widgetFrame2.pivotX, 0.5f, f);
        widgetFrame0.pivotY = WidgetFrame.interpolate(widgetFrame1.pivotY, widgetFrame2.pivotY, 0.5f, f);
        widgetFrame0.rotationX = WidgetFrame.interpolate(widgetFrame1.rotationX, widgetFrame2.rotationX, 0.0f, f);
        widgetFrame0.rotationY = WidgetFrame.interpolate(widgetFrame1.rotationY, widgetFrame2.rotationY, 0.0f, f);
        widgetFrame0.rotationZ = WidgetFrame.interpolate(widgetFrame1.rotationZ, widgetFrame2.rotationZ, 0.0f, f);
        widgetFrame0.scaleX = WidgetFrame.interpolate(widgetFrame1.scaleX, widgetFrame2.scaleX, 1.0f, f);
        widgetFrame0.scaleY = WidgetFrame.interpolate(widgetFrame1.scaleY, widgetFrame2.scaleY, 1.0f, f);
        widgetFrame0.translationX = WidgetFrame.interpolate(widgetFrame1.translationX, widgetFrame2.translationX, 0.0f, f);
        widgetFrame0.translationY = WidgetFrame.interpolate(widgetFrame1.translationY, widgetFrame2.translationY, 0.0f, f);
        widgetFrame0.translationZ = WidgetFrame.interpolate(widgetFrame1.translationZ, widgetFrame2.translationZ, 0.0f, f);
        widgetFrame0.alpha = WidgetFrame.interpolate(f4, f5, 1.0f, f);
        Set set0 = widgetFrame2.mCustom.keySet();
        widgetFrame0.mCustom.clear();
        for(Object object0: set0) {
            String s = (String)object0;
            if(widgetFrame1.mCustom.containsKey(s)) {
                CustomVariable customVariable0 = (CustomVariable)widgetFrame1.mCustom.get(s);
                CustomVariable customVariable1 = (CustomVariable)widgetFrame2.mCustom.get(s);
                CustomVariable customVariable2 = new CustomVariable(customVariable0);
                widgetFrame0.mCustom.put(s, customVariable2);
                if(customVariable0.numberOfInterpolatedValues() == 1) {
                    customVariable2.setValue(WidgetFrame.interpolate(customVariable0.getValueToInterpolate(), customVariable1.getValueToInterpolate(), 0.0f, f));
                }
                else {
                    int v23 = customVariable0.numberOfInterpolatedValues();
                    float[] arr_f = new float[v23];
                    float[] arr_f1 = new float[v23];
                    customVariable0.getValuesToInterpolate(arr_f);
                    customVariable1.getValuesToInterpolate(arr_f1);
                    for(int v24 = 0; v24 < v23; ++v24) {
                        arr_f[v24] = WidgetFrame.interpolate(arr_f[v24], arr_f1[v24], 0.0f, f);
                        customVariable2.setValue(arr_f);
                    }
                }
            }
        }
    }

    // 去混淆评级： 中等(90)
    public boolean isDefaultTransform() {
        return Float.isNaN(this.rotationX) && Float.isNaN(this.rotationY) && Float.isNaN(this.rotationZ) && Float.isNaN(this.translationX) && Float.isNaN(this.translationY) && Float.isNaN(this.translationZ) && Float.isNaN(this.scaleX) && Float.isNaN(this.scaleY) && Float.isNaN(this.alpha);
    }

    void logv(String s) {
        StackTraceElement stackTraceElement0 = new Throwable().getStackTrace()[1];
        String s1 = ".(" + stackTraceElement0.getFileName() + ":" + stackTraceElement0.getLineNumber() + ") " + "logv" + " " + this.hashCode() % 1000;
        System.out.println((this.widget == null ? s1 + "/NULL" : s1 + "/" + this.widget.hashCode() % 1000) + " " + s);
    }

    void parseCustom(CLElement cLElement0) throws CLParsingException {
        int v = ((CLObject)cLElement0).size();
        for(int v1 = 0; v1 < v; ++v1) {
            CLElement cLElement1 = ((CLKey)((CLObject)cLElement0).get(v1)).getValue();
            String s = cLElement1.content();
            if(s.matches("#[0-9a-fA-F]+")) {
                int v2 = Integer.parseInt(s.substring(1), 16);
                this.setCustomAttribute(this.name, 902, v2);
            }
            else if(cLElement1 instanceof CLNumber) {
                this.setCustomAttribute(this.name, 901, cLElement1.getFloat());
            }
            else {
                this.setCustomAttribute(this.name, 903, s);
            }
        }
    }

    void printCustomAttributes() {
        StackTraceElement stackTraceElement0 = new Throwable().getStackTrace()[1];
        String s = ".(" + stackTraceElement0.getFileName() + ":" + stackTraceElement0.getLineNumber() + ") " + "printCustomAttributes" + " " + this.hashCode() % 1000;
        String s1 = this.widget == null ? s + "/NULL " : s + "/" + this.widget.hashCode() % 1000 + " ";
        HashMap hashMap0 = this.mCustom;
        if(hashMap0 != null) {
            for(Object object0: hashMap0.keySet()) {
                System.out.println(s1 + ((CustomVariable)this.mCustom.get(((String)object0))).toString());
            }
        }
    }

    public StringBuilder serialize(StringBuilder stringBuilder0) {
        return this.serialize(stringBuilder0, false);
    }

    public StringBuilder serialize(StringBuilder stringBuilder0, boolean z) {
        stringBuilder0.append("{\n");
        WidgetFrame.add(stringBuilder0, "left", this.left);
        WidgetFrame.add(stringBuilder0, "top", this.top);
        WidgetFrame.add(stringBuilder0, "right", this.right);
        WidgetFrame.add(stringBuilder0, "bottom", this.bottom);
        WidgetFrame.add(stringBuilder0, "pivotX", this.pivotX);
        WidgetFrame.add(stringBuilder0, "pivotY", this.pivotY);
        WidgetFrame.add(stringBuilder0, "rotationX", this.rotationX);
        WidgetFrame.add(stringBuilder0, "rotationY", this.rotationY);
        WidgetFrame.add(stringBuilder0, "rotationZ", this.rotationZ);
        WidgetFrame.add(stringBuilder0, "translationX", this.translationX);
        WidgetFrame.add(stringBuilder0, "translationY", this.translationY);
        WidgetFrame.add(stringBuilder0, "translationZ", this.translationZ);
        WidgetFrame.add(stringBuilder0, "scaleX", this.scaleX);
        WidgetFrame.add(stringBuilder0, "scaleY", this.scaleY);
        WidgetFrame.add(stringBuilder0, "alpha", this.alpha);
        WidgetFrame.add(stringBuilder0, "visibility", this.visibility);
        WidgetFrame.add(stringBuilder0, "interpolatedPos", this.interpolatedPos);
        if(this.widget != null) {
            Type[] arr_constraintAnchor$Type = Type.values();
            for(int v = 0; v < arr_constraintAnchor$Type.length; ++v) {
                this.serializeAnchor(stringBuilder0, arr_constraintAnchor$Type[v]);
            }
        }
        if(z) {
            WidgetFrame.add(stringBuilder0, "phone_orientation", WidgetFrame.phone_orientation);
        }
        if(z) {
            WidgetFrame.add(stringBuilder0, "phone_orientation", WidgetFrame.phone_orientation);
        }
        if(this.mCustom.size() != 0) {
            stringBuilder0.append("custom : {\n");
            for(Object object0: this.mCustom.keySet()) {
                CustomVariable customVariable0 = (CustomVariable)this.mCustom.get(((String)object0));
                stringBuilder0.append(((String)object0));
                stringBuilder0.append(": ");
                switch(customVariable0.getType()) {
                    case 900: {
                        stringBuilder0.append(customVariable0.getIntegerValue());
                        stringBuilder0.append(",\n");
                        break;
                    }
                    case 902: {
                        stringBuilder0.append("\'");
                        stringBuilder0.append(CustomVariable.colorString(customVariable0.getIntegerValue()));
                        stringBuilder0.append("\',\n");
                        break;
                    }
                    case 903: {
                        stringBuilder0.append("\'");
                        stringBuilder0.append(customVariable0.getStringValue());
                        stringBuilder0.append("\',\n");
                        break;
                    }
                    case 904: {
                        stringBuilder0.append("\'");
                        stringBuilder0.append(customVariable0.getBooleanValue());
                        stringBuilder0.append("\',\n");
                        break;
                    }
                    case 901: 
                    case 905: {
                        stringBuilder0.append(customVariable0.getFloatValue());
                        stringBuilder0.append(",\n");
                    }
                }
            }
            stringBuilder0.append("}\n");
        }
        stringBuilder0.append("}\n");
        return stringBuilder0;
    }

    private void serializeAnchor(StringBuilder stringBuilder0, Type constraintAnchor$Type0) {
        ConstraintAnchor constraintAnchor0 = this.widget.getAnchor(constraintAnchor$Type0);
        if(constraintAnchor0 != null && constraintAnchor0.mTarget != null) {
            stringBuilder0.append("Anchor");
            stringBuilder0.append(constraintAnchor$Type0.name());
            stringBuilder0.append(": [\'");
            String s = constraintAnchor0.mTarget.getOwner().stringId;
            if(s == null) {
                s = "#PARENT";
            }
            stringBuilder0.append(s);
            stringBuilder0.append("\', \'");
            stringBuilder0.append(constraintAnchor0.mTarget.getType().name());
            stringBuilder0.append("\', \'");
            stringBuilder0.append(constraintAnchor0.mMargin);
            stringBuilder0.append("\'],\n");
        }
    }

    public void setCustomAttribute(String s, int v, float f) {
        if(this.mCustom.containsKey(s)) {
            ((CustomVariable)this.mCustom.get(s)).setFloatValue(f);
            return;
        }
        CustomVariable customVariable0 = new CustomVariable(s, v, f);
        this.mCustom.put(s, customVariable0);
    }

    public void setCustomAttribute(String s, int v, int v1) {
        if(this.mCustom.containsKey(s)) {
            ((CustomVariable)this.mCustom.get(s)).setIntValue(v1);
            return;
        }
        CustomVariable customVariable0 = new CustomVariable(s, v, v1);
        this.mCustom.put(s, customVariable0);
    }

    public void setCustomAttribute(String s, int v, String s1) {
        if(this.mCustom.containsKey(s)) {
            ((CustomVariable)this.mCustom.get(s)).setStringValue(s1);
            return;
        }
        CustomVariable customVariable0 = new CustomVariable(s, v, s1);
        this.mCustom.put(s, customVariable0);
    }

    public void setCustomAttribute(String s, int v, boolean z) {
        if(this.mCustom.containsKey(s)) {
            ((CustomVariable)this.mCustom.get(s)).setBooleanValue(z);
            return;
        }
        CustomVariable customVariable0 = new CustomVariable(s, v, z);
        this.mCustom.put(s, customVariable0);
    }

    public void setCustomValue(CustomAttribute customAttribute0, float[] arr_f) {
    }

    void setMotionAttributes(TypedBundle typedBundle0) {
        this.mMotionProperties = typedBundle0;
    }

    public boolean setValue(String s, CLElement cLElement0) throws CLParsingException {
        s.hashCode();
        switch(s) {
            case "alpha": {
                this.alpha = cLElement0.getFloat();
                return true;
            }
            case "bottom": {
                this.bottom = cLElement0.getInt();
                return true;
            }
            case "custom": {
                this.parseCustom(cLElement0);
                return true;
            }
            case "interpolatedPos": {
                this.interpolatedPos = cLElement0.getFloat();
                return true;
            }
            case "left": {
                this.left = cLElement0.getInt();
                return true;
            }
            case "phone_orientation": {
                WidgetFrame.phone_orientation = cLElement0.getFloat();
                return true;
            }
            case "pivotX": {
                this.pivotX = cLElement0.getFloat();
                return true;
            }
            case "pivotY": {
                this.pivotY = cLElement0.getFloat();
                return true;
            }
            case "right": {
                this.right = cLElement0.getInt();
                return true;
            }
            case "rotationX": {
                this.rotationX = cLElement0.getFloat();
                return true;
            }
            case "rotationY": {
                this.rotationY = cLElement0.getFloat();
                return true;
            }
            case "rotationZ": {
                this.rotationZ = cLElement0.getFloat();
                return true;
            }
            case "scaleX": {
                this.scaleX = cLElement0.getFloat();
                return true;
            }
            case "scaleY": {
                this.scaleY = cLElement0.getFloat();
                return true;
            }
            case "top": {
                this.top = cLElement0.getInt();
                return true;
            }
            case "translationX": {
                this.translationX = cLElement0.getFloat();
                return true;
            }
            case "translationY": {
                this.translationY = cLElement0.getFloat();
                return true;
            }
            case "translationZ": {
                this.translationZ = cLElement0.getFloat();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public WidgetFrame update() {
        ConstraintWidget constraintWidget0 = this.widget;
        if(constraintWidget0 != null) {
            this.left = constraintWidget0.getLeft();
            this.top = this.widget.getTop();
            this.right = this.widget.getRight();
            this.bottom = this.widget.getBottom();
            this.updateAttributes(this.widget.frame);
        }
        return this;
    }

    public WidgetFrame update(ConstraintWidget constraintWidget0) {
        if(constraintWidget0 == null) {
            return this;
        }
        this.widget = constraintWidget0;
        this.update();
        return this;
    }

    public void updateAttributes(WidgetFrame widgetFrame0) {
        if(widgetFrame0 != null) {
            this.pivotX = widgetFrame0.pivotX;
            this.pivotY = widgetFrame0.pivotY;
            this.rotationX = widgetFrame0.rotationX;
            this.rotationY = widgetFrame0.rotationY;
            this.rotationZ = widgetFrame0.rotationZ;
            this.translationX = widgetFrame0.translationX;
            this.translationY = widgetFrame0.translationY;
            this.translationZ = widgetFrame0.translationZ;
            this.scaleX = widgetFrame0.scaleX;
            this.scaleY = widgetFrame0.scaleY;
            this.alpha = widgetFrame0.alpha;
            this.visibility = widgetFrame0.visibility;
            this.setMotionAttributes(widgetFrame0.mMotionProperties);
            this.mCustom.clear();
            for(Object object0: widgetFrame0.mCustom.values()) {
                String s = ((CustomVariable)object0).getName();
                CustomVariable customVariable0 = ((CustomVariable)object0).copy();
                this.mCustom.put(s, customVariable0);
            }
        }
    }

    public int width() {
        return Math.max(0, this.right - this.left);
    }
}

