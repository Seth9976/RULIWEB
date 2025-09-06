package androidx.constraintlayout.core.dsl;

import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static final class HelperType {
        final String mName;

        public HelperType(String s) {
            this.mName = s;
        }

        @Override
        public String toString() {
            return this.mName;
        }
    }

    public static enum Type {
        VERTICAL_GUIDELINE,
        HORIZONTAL_GUIDELINE,
        VERTICAL_CHAIN,
        HORIZONTAL_CHAIN,
        BARRIER;

        private static Type[] $values() [...] // Inlined contents
    }

    protected String config;
    protected Map configMap;
    protected final String name;
    protected static final Map sideMap;
    protected HelperType type;
    protected static final Map typeMap;

    static {
        HashMap hashMap0 = new HashMap();
        Helper.sideMap = hashMap0;
        hashMap0.put(Side.LEFT, "\'left\'");
        hashMap0.put(Side.RIGHT, "\'right\'");
        hashMap0.put(Side.TOP, "\'top\'");
        hashMap0.put(Side.BOTTOM, "\'bottom\'");
        hashMap0.put(Side.START, "\'start\'");
        hashMap0.put(Side.END, "\'end\'");
        hashMap0.put(Side.BASELINE, "\'baseline\'");
        HashMap hashMap1 = new HashMap();
        Helper.typeMap = hashMap1;
        hashMap1.put(Type.VERTICAL_GUIDELINE, "vGuideline");
        hashMap1.put(Type.HORIZONTAL_GUIDELINE, "hGuideline");
        hashMap1.put(Type.VERTICAL_CHAIN, "vChain");
        hashMap1.put(Type.HORIZONTAL_CHAIN, "hChain");
        hashMap1.put(Type.BARRIER, "barrier");
    }

    public Helper(String s, HelperType helper$HelperType0) {
        this.type = null;
        this.configMap = new HashMap();
        this.name = s;
        this.type = helper$HelperType0;
    }

    public Helper(String s, HelperType helper$HelperType0, String s1) {
        this.type = null;
        this.configMap = new HashMap();
        this.name = s;
        this.type = helper$HelperType0;
        this.config = s1;
        this.configMap = this.convertConfigToMap();
    }

    public void append(Map map0, StringBuilder stringBuilder0) {
        if(!map0.isEmpty()) {
            for(Object object0: map0.keySet()) {
                stringBuilder0.append(((String)object0));
                stringBuilder0.append(":");
                stringBuilder0.append(((String)map0.get(((String)object0))));
                stringBuilder0.append(",\n");
            }
        }
    }

    public Map convertConfigToMap() {
        if(this.config != null && this.config.length() != 0) {
            Map map0 = new HashMap();
            StringBuilder stringBuilder0 = new StringBuilder();
            String s = "";
            int v1 = 0;
            int v2 = 0;
            for(int v = 0; v < this.config.length(); ++v) {
                int v3 = this.config.charAt(v);
                switch(v3) {
                    case 0x20: {
                        break;
                    }
                    case 44: {
                        if(v1 != 0 || v2 != 0) {
                            stringBuilder0.append(',');
                        }
                        else {
                            map0.put(s, stringBuilder0.toString());
                            stringBuilder0.setLength(0);
                            s = "";
                        }
                        break;
                    }
                    case 58: {
                        s = stringBuilder0.toString();
                        stringBuilder0.setLength(0);
                        break;
                    }
                    case 91: {
                        ++v1;
                        stringBuilder0.append('[');
                        break;
                    }
                    case 93: {
                        --v1;
                        stringBuilder0.append(']');
                        break;
                    }
                    case 0x7B: {
                        ++v2;
                        stringBuilder0.append('{');
                        break;
                    }
                    case 0x7D: {
                        --v2;
                        stringBuilder0.append('}');
                        break;
                    }
                    default: {
                        stringBuilder0.append(((char)v3));
                    }
                }
            }
            map0.put(s, stringBuilder0.toString());
            return map0;
        }
        return null;
    }

    public String getConfig() {
        return this.config;
    }

    public String getId() {
        return this.name;
    }

    public HelperType getType() {
        return this.type;
    }

    public static void main(String[] arr_s) {
        new Barrier("abc", "[\'a1\', \'b2\']");
        System.out.println("abc:{\ntype:\'barrier\',\n:[\'a1\',\'b2\'],\n},\n");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(this.name + ":{\n");
        if(this.type != null) {
            stringBuilder0.append("type:\'");
            stringBuilder0.append(this.type.toString());
            stringBuilder0.append("\',\n");
        }
        Map map0 = this.configMap;
        if(map0 != null) {
            this.append(map0, stringBuilder0);
        }
        stringBuilder0.append("},\n");
        return stringBuilder0.toString();
    }
}

