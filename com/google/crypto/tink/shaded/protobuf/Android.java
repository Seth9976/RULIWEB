package com.google.crypto.tink.shaded.protobuf;

final class Android {
    private static boolean ASSUME_ANDROID;
    private static final boolean IS_ROBOLECTRIC;
    private static final Class MEMORY_CLASS;

    static {
        Android.MEMORY_CLASS = Android.getClassForName("libcore.io.Memory");
        Android.IS_ROBOLECTRIC = Android.getClassForName("org.robolectric.Robolectric") != null;
    }

    private static Class getClassForName(String s) {
        try {
            return Class.forName(s);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    static Class getMemoryClass() {
        return Android.MEMORY_CLASS;
    }

    // 去混淆评级： 低(20)
    static boolean isOnAndroidDevice() [...] // 潜在的解密器
}

