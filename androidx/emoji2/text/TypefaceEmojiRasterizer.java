package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TypefaceEmojiRasterizer {
    @Retention(RetentionPolicy.SOURCE)
    public @interface HasGlyph {
    }

    static final int HAS_GLYPH_ABSENT = 1;
    static final int HAS_GLYPH_EXISTS = 2;
    static final int HAS_GLYPH_UNKNOWN;
    private volatile int mCache;
    private final int mIndex;
    private final MetadataRepo mMetadataRepo;
    private static final ThreadLocal sMetadataItem;

    static {
        TypefaceEmojiRasterizer.sMetadataItem = new ThreadLocal();
    }

    TypefaceEmojiRasterizer(MetadataRepo metadataRepo0, int v) {
        this.mCache = 0;
        this.mMetadataRepo = metadataRepo0;
        this.mIndex = v;
    }

    public void draw(Canvas canvas0, float f, float f1, Paint paint0) {
        Typeface typeface0 = paint0.getTypeface();
        paint0.setTypeface(this.mMetadataRepo.getTypeface());
        canvas0.drawText(this.mMetadataRepo.getEmojiCharArray(), this.mIndex * 2, 2, f, f1, paint0);
        paint0.setTypeface(typeface0);
    }

    public int getCodepointAt(int v) {
        return this.getMetadataItem().codepoints(v);
    }

    public int getCodepointsLength() {
        return this.getMetadataItem().codepointsLength();
    }

    public short getCompatAdded() {
        return this.getMetadataItem().compatAdded();
    }

    public int getHasGlyph() {
        return this.mCache & 3;
    }

    public int getHeight() {
        return this.getMetadataItem().height();
    }

    public int getId() {
        return this.getMetadataItem().id();
    }

    private MetadataItem getMetadataItem() {
        ThreadLocal threadLocal0 = TypefaceEmojiRasterizer.sMetadataItem;
        MetadataItem metadataItem0 = (MetadataItem)threadLocal0.get();
        if(metadataItem0 == null) {
            metadataItem0 = new MetadataItem();
            threadLocal0.set(metadataItem0);
        }
        this.mMetadataRepo.getMetadataList().list(metadataItem0, this.mIndex);
        return metadataItem0;
    }

    public short getSdkAdded() {
        return this.getMetadataItem().sdkAdded();
    }

    public Typeface getTypeface() {
        return this.mMetadataRepo.getTypeface();
    }

    public int getWidth() {
        return this.getMetadataItem().width();
    }

    public boolean isDefaultEmoji() {
        return this.getMetadataItem().emojiStyle();
    }

    public boolean isPreferredSystemRender() {
        return (this.mCache & 4) > 0;
    }

    public void resetHasGlyphCache() {
        if(this.isPreferredSystemRender()) {
            this.mCache = 4;
            return;
        }
        this.mCache = 0;
    }

    public void setExclusion(boolean z) {
        int v = this.getHasGlyph();
        if(z) {
            this.mCache = v | 4;
            return;
        }
        this.mCache = v;
    }

    public void setHasGlyph(boolean z) {
        int v = this.mCache & 4;
        this.mCache = z ? v | 2 : v | 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(super.toString());
        stringBuilder0.append(", id:");
        stringBuilder0.append(Integer.toHexString(this.getId()));
        stringBuilder0.append(", codepoints:");
        int v = this.getCodepointsLength();
        for(int v1 = 0; v1 < v; ++v1) {
            stringBuilder0.append(Integer.toHexString(this.getCodepointAt(v1)));
            stringBuilder0.append(" ");
        }
        return stringBuilder0.toString();
    }
}

