package androidx.core.content.res;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Base64;
import android.util.Xml;
import androidx.core.R.styleable;
import androidx.core.provider.FontRequest;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FontResourcesParserCompat {
    static class Api21Impl {
        static int getType(TypedArray typedArray0, int v) {
            return typedArray0.getType(v);
        }
    }

    public interface FamilyResourceEntry {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FetchStrategy {
    }

    public static final class FontFamilyFilesResourceEntry implements FamilyResourceEntry {
        private final FontFileResourceEntry[] mEntries;

        public FontFamilyFilesResourceEntry(FontFileResourceEntry[] arr_fontResourcesParserCompat$FontFileResourceEntry) {
            this.mEntries = arr_fontResourcesParserCompat$FontFileResourceEntry;
        }

        public FontFileResourceEntry[] getEntries() {
            return this.mEntries;
        }
    }

    public static final class FontFileResourceEntry {
        private final String mFileName;
        private final boolean mItalic;
        private final int mResourceId;
        private final int mTtcIndex;
        private final String mVariationSettings;
        private final int mWeight;

        public FontFileResourceEntry(String s, int v, boolean z, String s1, int v1, int v2) {
            this.mFileName = s;
            this.mWeight = v;
            this.mItalic = z;
            this.mVariationSettings = s1;
            this.mTtcIndex = v1;
            this.mResourceId = v2;
        }

        public String getFileName() {
            return this.mFileName;
        }

        public int getResourceId() {
            return this.mResourceId;
        }

        public int getTtcIndex() {
            return this.mTtcIndex;
        }

        public String getVariationSettings() {
            return this.mVariationSettings;
        }

        public int getWeight() {
            return this.mWeight;
        }

        public boolean isItalic() {
            return this.mItalic;
        }
    }

    public static final class ProviderResourceEntry implements FamilyResourceEntry {
        private final FontRequest mFallbackRequest;
        private final FontRequest mRequest;
        private final int mStrategy;
        private final String mSystemFontFamilyName;
        private final int mTimeoutMs;

        public ProviderResourceEntry(FontRequest fontRequest0, int v, int v1) {
            this(fontRequest0, null, v, v1, null);
        }

        public ProviderResourceEntry(FontRequest fontRequest0, FontRequest fontRequest1, int v, int v1, String s) {
            this.mRequest = fontRequest0;
            this.mFallbackRequest = fontRequest1;
            this.mStrategy = v;
            this.mTimeoutMs = v1;
            this.mSystemFontFamilyName = s;
        }

        public FontRequest getFallbackRequest() {
            return this.mFallbackRequest;
        }

        public int getFetchStrategy() {
            return this.mStrategy;
        }

        public FontRequest getRequest() {
            return this.mRequest;
        }

        public String getSystemFontFamilyName() {
            return this.mSystemFontFamilyName;
        }

        public int getTimeout() {
            return this.mTimeoutMs;
        }
    }

    private static final int DEFAULT_TIMEOUT_MILLIS = 500;
    public static final int FETCH_STRATEGY_ASYNC = 1;
    public static final int FETCH_STRATEGY_BLOCKING = 0;
    public static final int INFINITE_TIMEOUT_VALUE = -1;
    private static final int ITALIC = 1;
    private static final int NORMAL_WEIGHT = 400;

    private static int getType(TypedArray typedArray0, int v) {
        return Api21Impl.getType(typedArray0, v);
    }

    public static FamilyResourceEntry parse(XmlPullParser xmlPullParser0, Resources resources0) throws XmlPullParserException, IOException {
        do {
            int v = xmlPullParser0.next();
        }
        while(v != 1 && v != 2);
        if(v != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        return FontResourcesParserCompat.readFamilies(xmlPullParser0, resources0);
    }

    public static List readCerts(Resources resources0, int v) {
        if(v == 0) {
            return Collections.EMPTY_LIST;
        }
        TypedArray typedArray0 = resources0.obtainTypedArray(v);
        try {
            if(typedArray0.length() == 0) {
                return Collections.EMPTY_LIST;
            }
            List list1 = new ArrayList();
            if(FontResourcesParserCompat.getType(typedArray0, 0) == 1) {
                for(int v2 = 0; v2 < typedArray0.length(); ++v2) {
                    int v3 = typedArray0.getResourceId(v2, 0);
                    if(v3 != 0) {
                        list1.add(FontResourcesParserCompat.toByteArrayList(resources0.getStringArray(v3)));
                    }
                }
            }
            else {
                list1.add(FontResourcesParserCompat.toByteArrayList(resources0.getStringArray(v)));
            }
            return list1;
        }
        finally {
            typedArray0.recycle();
        }
    }

    private static FamilyResourceEntry readFamilies(XmlPullParser xmlPullParser0, Resources resources0) throws XmlPullParserException, IOException {
        xmlPullParser0.require(2, null, "font-family");
        if(xmlPullParser0.getName().equals("font-family")) {
            return FontResourcesParserCompat.readFamily(xmlPullParser0, resources0);
        }
        FontResourcesParserCompat.skip(xmlPullParser0);
        return null;
    }

    private static FamilyResourceEntry readFamily(XmlPullParser xmlPullParser0, Resources resources0) throws XmlPullParserException, IOException {
        TypedArray typedArray0 = resources0.obtainAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.FontFamily);
        String s = typedArray0.getString(styleable.FontFamily_fontProviderAuthority);
        String s1 = typedArray0.getString(styleable.FontFamily_fontProviderPackage);
        String s2 = typedArray0.getString(styleable.FontFamily_fontProviderQuery);
        String s3 = typedArray0.getString(styleable.FontFamily_fontProviderFallbackQuery);
        int v = typedArray0.getResourceId(styleable.FontFamily_fontProviderCerts, 0);
        int v1 = typedArray0.getInteger(styleable.FontFamily_fontProviderFetchStrategy, 1);
        int v2 = typedArray0.getInteger(styleable.FontFamily_fontProviderFetchTimeout, 500);
        String s4 = typedArray0.getString(styleable.FontFamily_fontProviderSystemFontFamily);
        typedArray0.recycle();
        FontRequest fontRequest0 = null;
        if(s != null && s1 != null && s2 != null) {
            while(xmlPullParser0.next() != 3) {
                FontResourcesParserCompat.skip(xmlPullParser0);
            }
            List list0 = FontResourcesParserCompat.readCerts(resources0, v);
            if(s3 != null) {
                fontRequest0 = new FontRequest(s, s1, s3, list0);
            }
            return new ProviderResourceEntry(new FontRequest(s, s1, s2, list0), fontRequest0, v1, v2, s4);
        }
        ArrayList arrayList0 = new ArrayList();
        while(xmlPullParser0.next() != 3) {
            if(xmlPullParser0.getEventType() != 2) {
            }
            else if(xmlPullParser0.getName().equals("font")) {
                arrayList0.add(FontResourcesParserCompat.readFont(xmlPullParser0, resources0));
            }
            else {
                FontResourcesParserCompat.skip(xmlPullParser0);
            }
        }
        return arrayList0.isEmpty() ? null : new FontFamilyFilesResourceEntry(((FontFileResourceEntry[])arrayList0.toArray(new FontFileResourceEntry[0])));
    }

    private static FontFileResourceEntry readFont(XmlPullParser xmlPullParser0, Resources resources0) throws XmlPullParserException, IOException {
        TypedArray typedArray0 = resources0.obtainAttributes(Xml.asAttributeSet(xmlPullParser0), styleable.FontFamilyFont);
        int v = typedArray0.getInt((typedArray0.hasValue(styleable.FontFamilyFont_fontWeight) ? styleable.FontFamilyFont_fontWeight : styleable.FontFamilyFont_android_fontWeight), 400);
        boolean z = 1 == typedArray0.getInt((typedArray0.hasValue(styleable.FontFamilyFont_fontStyle) ? styleable.FontFamilyFont_fontStyle : styleable.FontFamilyFont_android_fontStyle), 0);
        int v1 = typedArray0.hasValue(styleable.FontFamilyFont_ttcIndex) ? styleable.FontFamilyFont_ttcIndex : styleable.FontFamilyFont_android_ttcIndex;
        String s = typedArray0.getString((typedArray0.hasValue(styleable.FontFamilyFont_fontVariationSettings) ? styleable.FontFamilyFont_fontVariationSettings : styleable.FontFamilyFont_android_fontVariationSettings));
        int v2 = typedArray0.getInt(v1, 0);
        int v3 = typedArray0.hasValue(styleable.FontFamilyFont_font) ? styleable.FontFamilyFont_font : styleable.FontFamilyFont_android_font;
        int v4 = typedArray0.getResourceId(v3, 0);
        String s1 = typedArray0.getString(v3);
        typedArray0.recycle();
        while(xmlPullParser0.next() != 3) {
            FontResourcesParserCompat.skip(xmlPullParser0);
        }
        return new FontFileResourceEntry(s1, v, z, s, v2, v4);
    }

    private static void skip(XmlPullParser xmlPullParser0) throws XmlPullParserException, IOException {
        int v = 1;
        while(v > 0) {
            switch(xmlPullParser0.next()) {
                case 2: {
                    ++v;
                    break;
                }
                case 3: {
                    --v;
                }
            }
        }
    }

    private static List toByteArrayList(String[] arr_s) {
        List list0 = new ArrayList();
        for(int v = 0; v < arr_s.length; ++v) {
            list0.add(Base64.decode(arr_s[v], 0));
        }
        return list0;
    }
}

