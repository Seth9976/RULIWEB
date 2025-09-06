package com.google.android.gms.internal.auth;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeMap;

final class zzfz {
    private static final char[] zza;

    static {
        char[] arr_c = new char[80];
        zzfz.zza = arr_c;
        Arrays.fill(arr_c, ' ');
    }

    static String zza(zzfx zzfx0, String s) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("# ");
        stringBuilder0.append(s);
        zzfz.zzd(zzfx0, stringBuilder0, 0);
        return stringBuilder0.toString();
    }

    static void zzb(StringBuilder stringBuilder0, int v, String s, Object object0) {
        if(object0 instanceof List) {
            for(Object object1: ((List)object0)) {
                zzfz.zzb(stringBuilder0, v, s, object1);
            }
            return;
        }
        if(object0 instanceof Map) {
            for(Object object2: ((Map)object0).entrySet()) {
                zzfz.zzb(stringBuilder0, v, s, ((Map.Entry)object2));
            }
            return;
        }
        stringBuilder0.append('\n');
        zzfz.zzc(v, stringBuilder0);
        if(!s.isEmpty()) {
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append(Character.toLowerCase(s.charAt(0)));
            for(int v1 = 1; v1 < s.length(); ++v1) {
                int v2 = s.charAt(v1);
                if(Character.isUpperCase(((char)v2))) {
                    stringBuilder1.append("_");
                }
                stringBuilder1.append(Character.toLowerCase(((char)v2)));
            }
            s = stringBuilder1.toString();
        }
        stringBuilder0.append(s);
        if(object0 instanceof String) {
            stringBuilder0.append(": \"");
            stringBuilder0.append(zzgx.zza(new zzec(((String)object0).getBytes(zzfa.zzb))));
            stringBuilder0.append('\"');
            return;
        }
        if(object0 instanceof zzef) {
            stringBuilder0.append(": \"");
            stringBuilder0.append(zzgx.zza(((zzef)object0)));
            stringBuilder0.append('\"');
            return;
        }
        if(object0 instanceof zzev) {
            stringBuilder0.append(" {");
            zzfz.zzd(((zzev)object0), stringBuilder0, v + 2);
            stringBuilder0.append("\n");
            zzfz.zzc(v, stringBuilder0);
            stringBuilder0.append("}");
            return;
        }
        if(object0 instanceof Map.Entry) {
            stringBuilder0.append(" {");
            zzfz.zzb(stringBuilder0, v + 2, "key", ((Map.Entry)object0).getKey());
            zzfz.zzb(stringBuilder0, v + 2, "value", ((Map.Entry)object0).getValue());
            stringBuilder0.append("\n");
            zzfz.zzc(v, stringBuilder0);
            stringBuilder0.append("}");
            return;
        }
        stringBuilder0.append(": ");
        stringBuilder0.append(object0);
    }

    private static void zzc(int v, StringBuilder stringBuilder0) {
        while(true) {
            int v1 = 80;
            if(v <= 0) {
                break;
            }
            if(v <= 80) {
                v1 = v;
            }
            stringBuilder0.append(zzfz.zza, 0, v1);
            v -= v1;
        }
    }

    private static void zzd(zzfx zzfx0, StringBuilder stringBuilder0, int v) {
        boolean z;
        HashSet hashSet0 = new HashSet();
        HashMap hashMap0 = new HashMap();
        TreeMap treeMap0 = new TreeMap();
        Method[] arr_method = zzfx0.getClass().getDeclaredMethods();
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
                    zzfz.zzb(stringBuilder0, v, s.substring(0, s.length() - 4), zzev.zzg(method1, zzfx0, new Object[0]));
                    continue;
                }
            }
            if(s.endsWith("Map") && !s.equals("Map")) {
                Method method2 = (Method)map$Entry0.getValue();
                if(method2 != null && method2.getReturnType().equals(Map.class) && !method2.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method2.getModifiers())) {
                    zzfz.zzb(stringBuilder0, v, s.substring(0, s.length() - 3), zzev.zzg(method2, zzfx0, new Object[0]));
                    continue;
                }
            }
            if(hashSet0.contains("set" + s) && (!s.endsWith("Bytes") || !treeMap0.containsKey("get" + s.substring(0, s.length() - 5)))) {
                Method method3 = (Method)map$Entry0.getValue();
                Method method4 = (Method)hashMap0.get("has" + s);
                if(method3 != null) {
                    Object object1 = zzev.zzg(method3, zzfx0, new Object[0]);
                    if(method4 == null) {
                        if(object1 instanceof Boolean) {
                            if(!((Boolean)object1).booleanValue()) {
                                continue;
                            }
                            goto label_65;
                        }
                        else if(object1 instanceof Integer) {
                            if(((int)(((Integer)object1))) == 0) {
                                continue;
                            }
                            goto label_65;
                        }
                        else if(object1 instanceof Float) {
                            if(Float.floatToRawIntBits(((float)(((Float)object1)))) == 0) {
                                continue;
                            }
                            goto label_65;
                        }
                        else if(object1 instanceof Double) {
                            if(Double.doubleToRawLongBits(((double)(((Double)object1)))) == 0L) {
                                continue;
                            }
                            goto label_65;
                        }
                        else if(object1 instanceof String) {
                            z = object1.equals("");
                            goto label_57;
                        }
                        else {
                            if(object1 instanceof zzef) {
                                z = object1.equals(zzef.zzb);
                            label_57:
                                if(z) {
                                    continue;
                                }
                            }
                            else if(object1 instanceof zzfx) {
                                if(object1 == ((zzfx)object1).zze()) {
                                    continue;
                                }
                            }
                            else if(object1 instanceof Enum && ((Enum)object1).ordinal() == 0) {
                                continue;
                            }
                            goto label_65;
                        }
                        goto label_64;
                    }
                    else {
                    label_64:
                        if(((Boolean)zzev.zzg(method4, zzfx0, new Object[0])).booleanValue()) {
                            goto label_65;
                        }
                        continue;
                    }
                label_65:
                    zzfz.zzb(stringBuilder0, v, s, object1);
                }
            }
        }
        if(!(zzfx0 instanceof zzeu)) {
            zzha zzha0 = ((zzev)zzfx0).zzc;
            if(zzha0 != null) {
                zzha0.zzg(stringBuilder0, v);
            }
            return;
        }
        zzeu zzeu0 = (zzeu)zzfx0;
        throw null;
    }
}

