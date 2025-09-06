package com.google.android.material.internal;

import android.os.Build;
import java.util.Locale;

public class ManufacturerUtils {
    private static final String LGE = "lge";
    private static final String MEIZU = "meizu";
    private static final String SAMSUNG = "samsung";

    private static String getManufacturer() {
        return Build.MANUFACTURER == null ? "" : Build.MANUFACTURER.toLowerCase(Locale.ENGLISH);
    }

    // 去混淆评级： 低(20)
    public static boolean isDateInputKeyboardMissingSeparatorCharacters() {
        return ManufacturerUtils.isLGEDevice() || ManufacturerUtils.isSamsungDevice();
    }

    public static boolean isLGEDevice() {
        return ManufacturerUtils.getManufacturer().equals("lge");
    }

    public static boolean isMeizuDevice() {
        return ManufacturerUtils.getManufacturer().equals("meizu");
    }

    public static boolean isSamsungDevice() {
        return ManufacturerUtils.getManufacturer().equals("samsung");
    }
}

