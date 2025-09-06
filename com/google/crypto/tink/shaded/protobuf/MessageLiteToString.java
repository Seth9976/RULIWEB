package com.google.crypto.tink.shaded.protobuf;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeMap;

final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final char[] INDENT_BUFFER = null;
    private static final String LIST_SUFFIX = "List";
    private static final String MAP_SUFFIX = "Map";

    static {
        char[] arr_c = new char[80];
        MessageLiteToString.INDENT_BUFFER = arr_c;
        Arrays.fill(arr_c, ' ');
    }

    private static void indent(int v, StringBuilder stringBuilder0) {
        while(v > 0) {
            int v1 = v <= MessageLiteToString.INDENT_BUFFER.length ? v : MessageLiteToString.INDENT_BUFFER.length;
            stringBuilder0.append(MessageLiteToString.INDENT_BUFFER, 0, v1);
            v -= v1;
        }
    }

    private static boolean isDefaultValue(Object object0) {
        if(object0 instanceof Boolean) {
            return !((Boolean)object0).booleanValue();
        }
        if(object0 instanceof Integer) {
            return ((int)(((Integer)object0))) == 0;
        }
        if(object0 instanceof Float) {
            return Float.floatToRawIntBits(((float)(((Float)object0)))) == 0;
        }
        if(object0 instanceof Double) {
            return Double.doubleToRawLongBits(((double)(((Double)object0)))) == 0L;
        }
        if(object0 instanceof String) {
            return object0.equals("");
        }
        if(object0 instanceof ByteString) {
            return object0.equals(ByteString.EMPTY);
        }
        return object0 instanceof MessageLite ? object0 == ((MessageLite)object0).getDefaultInstanceForType() : object0 instanceof Enum && ((Enum)object0).ordinal() == 0;
    }

    private static String pascalCaseToSnakeCase(String s) {
        if(s.isEmpty()) {
            return s;
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(Character.toLowerCase(s.charAt(0)));
        for(int v = 1; v < s.length(); ++v) {
            int v1 = s.charAt(v);
            if(Character.isUpperCase(((char)v1))) {
                stringBuilder0.append("_");
            }
            stringBuilder0.append(Character.toLowerCase(((char)v1)));
        }
        return stringBuilder0.toString();
    }

    static void printField(StringBuilder stringBuilder0, int v, String s, Object object0) {
        if(object0 instanceof List) {
            for(Object object1: ((List)object0)) {
                MessageLiteToString.printField(stringBuilder0, v, s, object1);
            }
            return;
        }
        if(object0 instanceof Map) {
            for(Object object2: ((Map)object0).entrySet()) {
                MessageLiteToString.printField(stringBuilder0, v, s, ((Map.Entry)object2));
            }
            return;
        }
        stringBuilder0.append('\n');
        MessageLiteToString.indent(v, stringBuilder0);
        stringBuilder0.append(MessageLiteToString.pascalCaseToSnakeCase(s));
        if(object0 instanceof String) {
            stringBuilder0.append(": \"");
            stringBuilder0.append(TextFormatEscaper.escapeText(((String)object0)));
            stringBuilder0.append('\"');
            return;
        }
        if(object0 instanceof ByteString) {
            stringBuilder0.append(": \"");
            stringBuilder0.append(TextFormatEscaper.escapeBytes(((ByteString)object0)));
            stringBuilder0.append('\"');
            return;
        }
        if(object0 instanceof GeneratedMessageLite) {
            stringBuilder0.append(" {");
            MessageLiteToString.reflectivePrintWithIndent(((GeneratedMessageLite)object0), stringBuilder0, v + 2);
            stringBuilder0.append("\n");
            MessageLiteToString.indent(v, stringBuilder0);
            stringBuilder0.append("}");
            return;
        }
        if(object0 instanceof Map.Entry) {
            stringBuilder0.append(" {");
            MessageLiteToString.printField(stringBuilder0, v + 2, "key", ((Map.Entry)object0).getKey());
            MessageLiteToString.printField(stringBuilder0, v + 2, "value", ((Map.Entry)object0).getValue());
            stringBuilder0.append("\n");
            MessageLiteToString.indent(v, stringBuilder0);
            stringBuilder0.append("}");
            return;
        }
        stringBuilder0.append(": ");
        stringBuilder0.append(object0);
    }

    private static void reflectivePrintWithIndent(MessageLite messageLite0, StringBuilder stringBuilder0, int v) {
        boolean z;
        HashSet hashSet0 = new HashSet();
        HashMap hashMap0 = new HashMap();
        TreeMap treeMap0 = new TreeMap();
        Method[] arr_method = messageLite0.getClass().getDeclaredMethods();
        for(int v1 = 0; v1 < arr_method.length; ++v1) {
            Method method0 = arr_method[v1];
            if(!Modifier.isStatic(method0.getModifiers()) && method0.getName().length() >= 3) {
                if(method0.getName().startsWith("set")) {
                    hashSet0.add(method0.getName());
                }
                else if(Modifier.isPublic(method0.getModifiers()) && method0.getParameterTypes().length == 0) {
                    if(method0.getName().startsWith("has")) {
                        hashMap0.put(method0.getName(), method0);
                    }
                    else if(method0.getName().startsWith("get")) {
                        treeMap0.put(method0.getName(), method0);
                    }
                }
            }
        }
        for(Object object0: treeMap0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            String s = ((String)map$Entry0.getKey()).substring(3);
            if(s.endsWith("List") && !s.endsWith("OrBuilderList") && !s.equals("List")) {
                Method method1 = (Method)map$Entry0.getValue();
                if(method1 != null && method1.getReturnType().equals(List.class)) {
                    MessageLiteToString.printField(stringBuilder0, v, s.substring(0, s.length() - 4), GeneratedMessageLite.invokeOrDie(method1, messageLite0, new Object[0]));
                    continue;
                }
            }
            if(s.endsWith("Map") && !s.equals("Map")) {
                Method method2 = (Method)map$Entry0.getValue();
                if(method2 != null && method2.getReturnType().equals(Map.class) && !method2.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method2.getModifiers())) {
                    MessageLiteToString.printField(stringBuilder0, v, s.substring(0, s.length() - 3), GeneratedMessageLite.invokeOrDie(method2, messageLite0, new Object[0]));
                    continue;
                }
            }
            if(hashSet0.contains("set" + s) && (!s.endsWith("Bytes") || !treeMap0.containsKey("get" + s.substring(0, s.length() - 5)))) {
                Method method3 = (Method)map$Entry0.getValue();
                Method method4 = (Method)hashMap0.get("has" + s);
                if(method3 != null) {
                    Object object1 = GeneratedMessageLite.invokeOrDie(method3, messageLite0, new Object[0]);
                    if(method4 != null) {
                        z = ((Boolean)GeneratedMessageLite.invokeOrDie(method4, messageLite0, new Object[0])).booleanValue();
                    }
                    else if(MessageLiteToString.isDefaultValue(object1)) {
                        z = false;
                    }
                    else {
                        z = true;
                    }
                    if(z) {
                        MessageLiteToString.printField(stringBuilder0, v, s, object1);
                    }
                }
            }
        }
        if(messageLite0 instanceof ExtendableMessage) {
            for(Object object2: ((ExtendableMessage)messageLite0).extensions) {
                MessageLiteToString.printField(stringBuilder0, v, "[" + ((ExtensionDescriptor)((Map.Entry)object2).getKey()).getNumber() + "]", ((Map.Entry)object2).getValue());
            }
        }
        if(((GeneratedMessageLite)messageLite0).unknownFields != null) {
            ((GeneratedMessageLite)messageLite0).unknownFields.printWithIndent(stringBuilder0, v);
        }
    }

    static String toString(MessageLite messageLite0, String s) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("# ");
        stringBuilder0.append(s);
        MessageLiteToString.reflectivePrintWithIndent(messageLite0, stringBuilder0, 0);
        return stringBuilder0.toString();
    }
}

