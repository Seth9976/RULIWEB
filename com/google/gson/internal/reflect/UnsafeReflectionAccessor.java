package com.google.gson.internal.reflect;

import com.google.gson.JsonIOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

final class UnsafeReflectionAccessor extends ReflectionAccessor {
    private final Field overrideField;
    private final Object theUnsafe;
    private static Class unsafeClass;

    UnsafeReflectionAccessor() {
        this.theUnsafe = UnsafeReflectionAccessor.getUnsafeInstance();
        this.overrideField = UnsafeReflectionAccessor.getOverrideField();
    }

    private static Field getOverrideField() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    private static Object getUnsafeInstance() {
        try {
            Class class0 = Class.forName("sun.misc.Unsafe");
            UnsafeReflectionAccessor.unsafeClass = class0;
            Field field0 = class0.getDeclaredField("theUnsafe");
            field0.setAccessible(true);
            return field0.get(null);
        }
        catch(Exception unused_ex) {
            return null;
        }
    }

    @Override  // com.google.gson.internal.reflect.ReflectionAccessor
    public void makeAccessible(AccessibleObject accessibleObject0) {
        if(!this.makeAccessibleWithUnsafe(accessibleObject0)) {
            try {
                accessibleObject0.setAccessible(true);
            }
            catch(SecurityException securityException0) {
                throw new JsonIOException("Gson couldn\'t modify fields for " + accessibleObject0 + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", securityException0);
            }
        }
    }

    boolean makeAccessibleWithUnsafe(AccessibleObject accessibleObject0) {
        if(this.theUnsafe != null && this.overrideField != null) {
            try {
                Long long0 = (Long)UnsafeReflectionAccessor.unsafeClass.getMethod("objectFieldOffset", Field.class).invoke(this.theUnsafe, this.overrideField);
                long0.longValue();
                UnsafeReflectionAccessor.unsafeClass.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.theUnsafe, accessibleObject0, long0, Boolean.TRUE);
                return true;
            }
            catch(Exception unused_ex) {
            }
        }
        return false;
    }
}

