package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface.CustomFallbackBuilder;
import android.graphics.Typeface;
import android.graphics.fonts.Font.Builder;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily.Builder;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import androidx.core.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import androidx.core.provider.FontsContractCompat.FontInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi29Impl";

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context0, FontFamilyFilesResourceEntry fontResourcesParserCompat$FontFamilyFilesResourceEntry0, Resources resources0, int v) {
        try {
            FontFileResourceEntry[] arr_fontResourcesParserCompat$FontFileResourceEntry = fontResourcesParserCompat$FontFamilyFilesResourceEntry0.getEntries();
            FontFamily.Builder fontFamily$Builder0 = null;
            for(int v1 = 0; true; ++v1) {
                if(v1 >= arr_fontResourcesParserCompat$FontFileResourceEntry.length) {
                    if(fontFamily$Builder0 == null) {
                        return null;
                    }
                    FontFamily fontFamily0 = fontFamily$Builder0.build();
                    return new Typeface.CustomFallbackBuilder(fontFamily0).setStyle(this.findBaseFont(fontFamily0, v).getStyle()).build();
                }
                FontFileResourceEntry fontResourcesParserCompat$FontFileResourceEntry0 = arr_fontResourcesParserCompat$FontFileResourceEntry[v1];
                try {
                    Font font0 = new Font.Builder(resources0, fontResourcesParserCompat$FontFileResourceEntry0.getResourceId()).setWeight(fontResourcesParserCompat$FontFileResourceEntry0.getWeight()).setSlant(((int)fontResourcesParserCompat$FontFileResourceEntry0.isItalic())).setTtcIndex(fontResourcesParserCompat$FontFileResourceEntry0.getTtcIndex()).setFontVariationSettings(fontResourcesParserCompat$FontFileResourceEntry0.getVariationSettings()).build();
                    if(fontFamily$Builder0 == null) {
                        fontFamily$Builder0 = new FontFamily.Builder(font0);
                    }
                    else {
                        fontFamily$Builder0.addFont(font0);
                    }
                }
                catch(IOException unused_ex) {
                }
            }
        }
        catch(Exception exception0) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", exception0);
            return null;
        }
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context0, CancellationSignal cancellationSignal0, FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        ContentResolver contentResolver0 = context0.getContentResolver();
        try {
            FontFamily fontFamily0 = TypefaceCompatApi29Impl.getFontFamily(cancellationSignal0, arr_fontsContractCompat$FontInfo, contentResolver0);
            return fontFamily0 == null ? null : new Typeface.CustomFallbackBuilder(fontFamily0).setStyle(this.findBaseFont(fontFamily0, v).getStyle()).build();
        }
        catch(Exception exception0) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", exception0);
            return null;
        }
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfoWithFallback(Context context0, CancellationSignal cancellationSignal0, List list0, int v) {
        ContentResolver contentResolver0 = context0.getContentResolver();
        try {
            FontFamily fontFamily0 = TypefaceCompatApi29Impl.getFontFamily(cancellationSignal0, ((FontInfo[])list0.get(0)), contentResolver0);
            if(fontFamily0 == null) {
                return null;
            }
            Typeface.CustomFallbackBuilder typeface$CustomFallbackBuilder0 = new Typeface.CustomFallbackBuilder(fontFamily0);
            for(int v1 = 1; v1 < list0.size(); ++v1) {
                FontFamily fontFamily1 = TypefaceCompatApi29Impl.getFontFamily(cancellationSignal0, ((FontInfo[])list0.get(v1)), contentResolver0);
                if(fontFamily1 != null) {
                    typeface$CustomFallbackBuilder0.addCustomFallback(fontFamily1);
                }
            }
            return typeface$CustomFallbackBuilder0.setStyle(this.findBaseFont(fontFamily0, v).getStyle()).build();
        }
        catch(Exception exception0) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", exception0);
            return null;
        }
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    protected Typeface createFromInputStream(Context context0, InputStream inputStream0) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromResourcesFontFile(Context context0, Resources resources0, int v, String s, int v1) {
        try {
            Font font0 = new Font.Builder(resources0, v).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(font0).build()).setStyle(font0.getStyle()).build();
        }
        catch(Exception exception0) {
            Log.w("TypefaceCompatApi29Impl", "Font load failed", exception0);
            return null;
        }
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    Typeface createWeightStyle(Context context0, Typeface typeface0, int v, boolean z) {
        return Typeface.create(typeface0, v, z);
    }

    private Font findBaseFont(FontFamily fontFamily0, int v) {
        FontStyle fontStyle0 = new FontStyle(((v & 1) == 0 ? 400 : 700), ((v & 2) == 0 ? 0 : 1));
        Font font0 = fontFamily0.getFont(0);
        int v2 = TypefaceCompatApi29Impl.getMatchScore(fontStyle0, font0.getStyle());
        for(int v1 = 1; v1 < fontFamily0.getSize(); ++v1) {
            Font font1 = fontFamily0.getFont(v1);
            int v3 = TypefaceCompatApi29Impl.getMatchScore(fontStyle0, font1.getStyle());
            if(v3 < v2) {
                font0 = font1;
                v2 = v3;
            }
        }
        return font0;
    }

    @Override  // androidx.core.graphics.TypefaceCompatBaseImpl
    protected FontInfo findBestInfo(FontInfo[] arr_fontsContractCompat$FontInfo, int v) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    private static FontFamily getFontFamily(CancellationSignal cancellationSignal0, FontInfo[] arr_fontsContractCompat$FontInfo, ContentResolver contentResolver0) {
        FontFamily.Builder fontFamily$Builder0 = null;
        for(int v = 0; v < arr_fontsContractCompat$FontInfo.length; ++v) {
            FontInfo fontsContractCompat$FontInfo0 = arr_fontsContractCompat$FontInfo[v];
            try(ParcelFileDescriptor parcelFileDescriptor0 = contentResolver0.openFileDescriptor(fontsContractCompat$FontInfo0.getUri(), "r", cancellationSignal0)) {
                if(parcelFileDescriptor0 != null) {
                    Font font0 = new Font.Builder(parcelFileDescriptor0).setWeight(fontsContractCompat$FontInfo0.getWeight()).setSlant(((int)fontsContractCompat$FontInfo0.isItalic())).setTtcIndex(fontsContractCompat$FontInfo0.getTtcIndex()).build();
                    if(fontFamily$Builder0 == null) {
                        fontFamily$Builder0 = new FontFamily.Builder(font0);
                    }
                    else {
                        fontFamily$Builder0.addFont(font0);
                    }
                }
            }
            catch(IOException iOException0) {
                Log.w("TypefaceCompatApi29Impl", "Font load failed", iOException0);
            }
        }
        return fontFamily$Builder0 == null ? null : fontFamily$Builder0.build();
    }

    private static int getMatchScore(FontStyle fontStyle0, FontStyle fontStyle1) {
        int v = Math.abs(fontStyle0.getWeight() - fontStyle1.getWeight());
        return fontStyle0.getSlant() == fontStyle1.getSlant() ? v / 100 : v / 100 + 2;
    }
}

