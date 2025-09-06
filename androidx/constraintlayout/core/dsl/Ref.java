package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;
import java.util.Arrays;

public class Ref {
    private String mId;
    private float mPostMargin;
    private float mPreMargin;
    private float mWeight;

    Ref(String s) {
        this.mWeight = NaNf;
        this.mPreMargin = NaNf;
        this.mPostMargin = NaNf;
        this.mId = s;
    }

    Ref(String s, float f) {
        this.mPreMargin = NaNf;
        this.mPostMargin = NaNf;
        this.mId = s;
        this.mWeight = f;
    }

    Ref(String s, float f, float f1) {
        this.mPostMargin = NaNf;
        this.mId = s;
        this.mWeight = f;
        this.mPreMargin = f1;
    }

    Ref(String s, float f, float f1, float f2) {
        this.mId = s;
        this.mWeight = f;
        this.mPreMargin = f1;
        this.mPostMargin = f2;
    }

    public static void addStringToReferences(String s, ArrayList arrayList0) {
        if(s != null && s.length() != 0) {
            Object[] arr_object = new Object[4];
            StringBuilder stringBuilder0 = new StringBuilder();
            int v1 = 0;
            int v2 = 0;
            for(int v = 0; v < s.length(); ++v) {
                int v3 = s.charAt(v);
                switch(v3) {
                    case 0x20: 
                    case 39: {
                        break;
                    }
                    case 44: {
                        if(v1 < 3) {
                            arr_object[v1] = stringBuilder0.toString();
                            stringBuilder0.setLength(0);
                            ++v1;
                        }
                        if(v2 == 1) {
                            Object object1 = arr_object[0];
                            if(object1 != null) {
                                arrayList0.add(new Ref(object1.toString()));
                                arr_object[0] = null;
                                v1 = 0;
                            }
                        }
                        break;
                    }
                    case 91: {
                        ++v2;
                        break;
                    }
                    case 93: {
                        if(v2 > 0) {
                            --v2;
                            arr_object[v1] = stringBuilder0.toString();
                            stringBuilder0.setLength(0);
                            Object object0 = arr_object[0];
                            if(object0 != null) {
                                arrayList0.add(new Ref(object0.toString(), Ref.parseFloat(arr_object[1]), Ref.parseFloat(arr_object[2]), Ref.parseFloat(arr_object[3])));
                                Arrays.fill(arr_object, null);
                                v1 = 0;
                            }
                        }
                        break;
                    }
                    default: {
                        stringBuilder0.append(((char)v3));
                    }
                }
            }
        }
    }

    public String getId() {
        return this.mId;
    }

    public float getPostMargin() {
        return this.mPostMargin;
    }

    public float getPreMargin() {
        return this.mPreMargin;
    }

    public float getWeight() {
        return this.mWeight;
    }

    public static float parseFloat(Object object0) {
        try {
            return Float.parseFloat(object0.toString());
        }
        catch(Exception unused_ex) {
            return NaNf;
        }
    }

    public static Ref parseStringToRef(String s) {
        String[] arr_s = s.replaceAll("[\\[\\]\\\']", "").split(",");
        if(arr_s.length == 0) {
            return null;
        }
        Object[] arr_object = new Object[4];
        for(int v = 0; v < arr_s.length && v < 4; ++v) {
            arr_object[v] = arr_s[v];
        }
        return new Ref(arr_object[0].toString().replace("\'", ""), Ref.parseFloat(arr_object[1]), Ref.parseFloat(arr_object[2]), Ref.parseFloat(arr_object[3]));
    }

    public void setId(String s) {
        this.mId = s;
    }

    public void setPostMargin(float f) {
        this.mPostMargin = f;
    }

    public void setPreMargin(float f) {
        this.mPreMargin = f;
    }

    public void setWeight(float f) {
        this.mWeight = f;
    }

    @Override
    public String toString() {
        if(this.mId != null && this.mId.length() != 0) {
            StringBuilder stringBuilder0 = new StringBuilder();
            boolean z = !Float.isNaN(this.mWeight) || !Float.isNaN(this.mPreMargin) || !Float.isNaN(this.mPostMargin);
            if(z) {
                stringBuilder0.append("[");
            }
            stringBuilder0.append("\'");
            stringBuilder0.append(this.mId);
            stringBuilder0.append("\'");
            float f = 0.0f;
            if(!Float.isNaN(this.mPostMargin)) {
                stringBuilder0.append(",");
                stringBuilder0.append((Float.isNaN(this.mWeight) ? 0.0f : this.mWeight));
                stringBuilder0.append(",");
                if(!Float.isNaN(this.mPreMargin)) {
                    f = this.mPreMargin;
                }
                stringBuilder0.append(f);
                stringBuilder0.append(",");
                stringBuilder0.append(this.mPostMargin);
            }
            else if(!Float.isNaN(this.mPreMargin)) {
                stringBuilder0.append(",");
                if(!Float.isNaN(this.mWeight)) {
                    f = this.mWeight;
                }
                stringBuilder0.append(f);
                stringBuilder0.append(",");
                stringBuilder0.append(this.mPreMargin);
            }
            else if(!Float.isNaN(this.mWeight)) {
                stringBuilder0.append(",");
                stringBuilder0.append(this.mWeight);
            }
            if(z) {
                stringBuilder0.append("]");
            }
            stringBuilder0.append(",");
            return stringBuilder0.toString();
        }
        return "";
    }
}

