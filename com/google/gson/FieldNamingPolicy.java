package com.google.gson;

import java.lang.reflect.Field;
import java.util.Locale;

// 部分失败：枚举糖化
// 枚举按原样呈现，而不是糖化为Java 5枚举。
public abstract class FieldNamingPolicy extends Enum implements FieldNamingStrategy {
    private static final FieldNamingPolicy[] $VALUES;
    public static final enum FieldNamingPolicy IDENTITY;
    public static final enum FieldNamingPolicy LOWER_CASE_WITH_DASHES;
    public static final enum FieldNamingPolicy LOWER_CASE_WITH_DOTS;
    public static final enum FieldNamingPolicy LOWER_CASE_WITH_UNDERSCORES;
    public static final enum FieldNamingPolicy UPPER_CAMEL_CASE;
    public static final enum FieldNamingPolicy UPPER_CAMEL_CASE_WITH_SPACES;

    static {
        com.google.gson.FieldNamingPolicy.1 fieldNamingPolicy$10 = new FieldNamingPolicy("IDENTITY", 0) {
            @Override  // com.google.gson.FieldNamingStrategy
            public String translateName(Field field0) {
                return field0.getName();
            }
        };
        FieldNamingPolicy.IDENTITY = fieldNamingPolicy$10;
        com.google.gson.FieldNamingPolicy.2 fieldNamingPolicy$20 = new FieldNamingPolicy("UPPER_CAMEL_CASE", 1) {
            @Override  // com.google.gson.FieldNamingStrategy
            public String translateName(Field field0) {
                return com.google.gson.FieldNamingPolicy.2.upperCaseFirstLetter(field0.getName());
            }
        };
        FieldNamingPolicy.UPPER_CAMEL_CASE = fieldNamingPolicy$20;
        com.google.gson.FieldNamingPolicy.3 fieldNamingPolicy$30 = new FieldNamingPolicy("UPPER_CAMEL_CASE_WITH_SPACES", 2) {
            @Override  // com.google.gson.FieldNamingStrategy
            public String translateName(Field field0) {
                return com.google.gson.FieldNamingPolicy.3.upperCaseFirstLetter(com.google.gson.FieldNamingPolicy.3.separateCamelCase(field0.getName(), " "));
            }
        };
        FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES = fieldNamingPolicy$30;
        com.google.gson.FieldNamingPolicy.4 fieldNamingPolicy$40 = new FieldNamingPolicy("LOWER_CASE_WITH_UNDERSCORES", 3) {
            @Override  // com.google.gson.FieldNamingStrategy
            public String translateName(Field field0) {
                return com.google.gson.FieldNamingPolicy.4.separateCamelCase(field0.getName(), "_").toLowerCase(Locale.ENGLISH);
            }
        };
        FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES = fieldNamingPolicy$40;
        com.google.gson.FieldNamingPolicy.5 fieldNamingPolicy$50 = new FieldNamingPolicy("LOWER_CASE_WITH_DASHES", 4) {
            @Override  // com.google.gson.FieldNamingStrategy
            public String translateName(Field field0) {
                return com.google.gson.FieldNamingPolicy.5.separateCamelCase(field0.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        };
        FieldNamingPolicy.LOWER_CASE_WITH_DASHES = fieldNamingPolicy$50;
        com.google.gson.FieldNamingPolicy.6 fieldNamingPolicy$60 = new FieldNamingPolicy("LOWER_CASE_WITH_DOTS", 5) {
            @Override  // com.google.gson.FieldNamingStrategy
            public String translateName(Field field0) {
                return com.google.gson.FieldNamingPolicy.6.separateCamelCase(field0.getName(), ".").toLowerCase(Locale.ENGLISH);
            }
        };
        FieldNamingPolicy.LOWER_CASE_WITH_DOTS = fieldNamingPolicy$60;
        FieldNamingPolicy.$VALUES = new FieldNamingPolicy[]{fieldNamingPolicy$10, fieldNamingPolicy$20, fieldNamingPolicy$30, fieldNamingPolicy$40, fieldNamingPolicy$50, fieldNamingPolicy$60};
    }

    private FieldNamingPolicy(String s, int v) {
        super(s, v);
    }

    FieldNamingPolicy(String s, int v, com.google.gson.FieldNamingPolicy.1 fieldNamingPolicy$10) {
        this(s, v);
    }

    static String separateCamelCase(String s, String s1) {
        StringBuilder stringBuilder0 = new StringBuilder();
        int v = s.length();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = s.charAt(v1);
            if(Character.isUpperCase(((char)v2)) && stringBuilder0.length() != 0) {
                stringBuilder0.append(s1);
            }
            stringBuilder0.append(((char)v2));
        }
        return stringBuilder0.toString();
    }

    static String upperCaseFirstLetter(String s) {
        int v = s.length();
        int v1;
        for(v1 = 0; !Character.isLetter(s.charAt(v1)) && v1 < v - 1; ++v1) {
        }
        int v2 = s.charAt(v1);
        if(Character.isUpperCase(((char)v2))) {
            return s;
        }
        int v3 = Character.toUpperCase(((char)v2));
        return v1 == 0 ? ((char)v3) + s.substring(1) : s.substring(0, v1) + ((char)v3) + s.substring(v1 + 1);
    }

    public static FieldNamingPolicy valueOf(String s) {
        return (FieldNamingPolicy)Enum.valueOf(FieldNamingPolicy.class, s);
    }

    public static FieldNamingPolicy[] values() {
        return (FieldNamingPolicy[])FieldNamingPolicy.$VALUES.clone();
    }
}

