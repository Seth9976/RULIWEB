package androidx.constraintlayout.core.dsl;

import java.util.Arrays;

public class Keys {
    protected void append(StringBuilder stringBuilder0, String s, float f) {
        if(Float.isNaN(f)) {
            return;
        }
        stringBuilder0.append(s);
        stringBuilder0.append(":");
        stringBuilder0.append(f);
        stringBuilder0.append(",\n");
    }

    protected void append(StringBuilder stringBuilder0, String s, int v) {
        if(v != 0x80000000) {
            stringBuilder0.append(s);
            stringBuilder0.append(":\'");
            stringBuilder0.append(v);
            stringBuilder0.append("\',\n");
        }
    }

    protected void append(StringBuilder stringBuilder0, String s, String s1) {
        if(s1 != null) {
            stringBuilder0.append(s);
            stringBuilder0.append(":\'");
            stringBuilder0.append(s1);
            stringBuilder0.append("\',\n");
        }
    }

    protected void append(StringBuilder stringBuilder0, String s, float[] arr_f) {
        if(arr_f != null) {
            stringBuilder0.append(s);
            stringBuilder0.append("percentWidth:");
            stringBuilder0.append(Arrays.toString(arr_f));
            stringBuilder0.append(",\n");
        }
    }

    protected void append(StringBuilder stringBuilder0, String s, String[] arr_s) {
        if(arr_s != null) {
            stringBuilder0.append(s);
            stringBuilder0.append(":");
            stringBuilder0.append(this.unpack(arr_s));
            stringBuilder0.append(",\n");
        }
    }

    protected String unpack(String[] arr_s) {
        StringBuilder stringBuilder0 = new StringBuilder("[");
        for(int v = 0; v < arr_s.length; ++v) {
            stringBuilder0.append((v == 0 ? "\'" : ",\'"));
            stringBuilder0.append(arr_s[v]);
            stringBuilder0.append("\'");
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }
}

