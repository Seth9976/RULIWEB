package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import org.xmlpull.v1.XmlPullParserException;

public class VectorEnabledTintResources extends ResourcesWrapper {
    public static final int MAX_SDK_WHERE_REQUIRED = 20;
    private final WeakReference mContextRef;
    private static boolean sCompatVectorFromResourcesEnabled = false;

    static {
    }

    public VectorEnabledTintResources(Context context0, Resources resources0) {
        super(resources0);
        this.mContextRef = new WeakReference(context0);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public XmlResourceParser getAnimation(int v) throws Resources.NotFoundException {
        return super.getAnimation(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public boolean getBoolean(int v) throws Resources.NotFoundException {
        return super.getBoolean(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public int getColor(int v) throws Resources.NotFoundException {
        return super.getColor(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public ColorStateList getColorStateList(int v) throws Resources.NotFoundException {
        return super.getColorStateList(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public Configuration getConfiguration() {
        return super.getConfiguration();
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public float getDimension(int v) throws Resources.NotFoundException {
        return super.getDimension(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public int getDimensionPixelOffset(int v) throws Resources.NotFoundException {
        return super.getDimensionPixelOffset(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public int getDimensionPixelSize(int v) throws Resources.NotFoundException {
        return super.getDimensionPixelSize(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public DisplayMetrics getDisplayMetrics() {
        return super.getDisplayMetrics();
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public Drawable getDrawable(int v) throws Resources.NotFoundException {
        Context context0 = (Context)this.mContextRef.get();
        return context0 == null ? this.getDrawableCanonical(v) : ResourceManagerInternal.get().onDrawableLoadedFromResources(context0, this, v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public Drawable getDrawable(int v, Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return super.getDrawable(v, resources$Theme0);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public Drawable getDrawableForDensity(int v, int v1) throws Resources.NotFoundException {
        return super.getDrawableForDensity(v, v1);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public Drawable getDrawableForDensity(int v, int v1, Resources.Theme resources$Theme0) {
        return super.getDrawableForDensity(v, v1, resources$Theme0);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public float getFraction(int v, int v1, int v2) {
        return super.getFraction(v, v1, v2);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public int getIdentifier(String s, String s1, String s2) {
        return super.getIdentifier(s, s1, s2);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public int[] getIntArray(int v) throws Resources.NotFoundException {
        return super.getIntArray(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public int getInteger(int v) throws Resources.NotFoundException {
        return super.getInteger(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public XmlResourceParser getLayout(int v) throws Resources.NotFoundException {
        return super.getLayout(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public Movie getMovie(int v) throws Resources.NotFoundException {
        return super.getMovie(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getQuantityString(int v, int v1) throws Resources.NotFoundException {
        return super.getQuantityString(v, v1);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getQuantityString(int v, int v1, Object[] arr_object) throws Resources.NotFoundException {
        return super.getQuantityString(v, v1, arr_object);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public CharSequence getQuantityText(int v, int v1) throws Resources.NotFoundException {
        return super.getQuantityText(v, v1);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getResourceEntryName(int v) throws Resources.NotFoundException {
        return super.getResourceEntryName(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getResourceName(int v) throws Resources.NotFoundException {
        return super.getResourceName(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getResourcePackageName(int v) throws Resources.NotFoundException {
        return super.getResourcePackageName(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getResourceTypeName(int v) throws Resources.NotFoundException {
        return super.getResourceTypeName(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getString(int v) throws Resources.NotFoundException {
        return super.getString(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String getString(int v, Object[] arr_object) throws Resources.NotFoundException {
        return super.getString(v, arr_object);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public String[] getStringArray(int v) throws Resources.NotFoundException {
        return super.getStringArray(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public CharSequence getText(int v) throws Resources.NotFoundException {
        return super.getText(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public CharSequence getText(int v, CharSequence charSequence0) {
        return super.getText(v, charSequence0);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public CharSequence[] getTextArray(int v) throws Resources.NotFoundException {
        return super.getTextArray(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public void getValue(int v, TypedValue typedValue0, boolean z) throws Resources.NotFoundException {
        super.getValue(v, typedValue0, z);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public void getValue(String s, TypedValue typedValue0, boolean z) throws Resources.NotFoundException {
        super.getValue(s, typedValue0, z);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public void getValueForDensity(int v, int v1, TypedValue typedValue0, boolean z) throws Resources.NotFoundException {
        super.getValueForDensity(v, v1, typedValue0, z);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public XmlResourceParser getXml(int v) throws Resources.NotFoundException {
        return super.getXml(v);
    }

    public static boolean isCompatVectorFromResourcesEnabled() [...] // 潜在的解密器

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public TypedArray obtainAttributes(AttributeSet attributeSet0, int[] arr_v) {
        return super.obtainAttributes(attributeSet0, arr_v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public TypedArray obtainTypedArray(int v) throws Resources.NotFoundException {
        return super.obtainTypedArray(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public InputStream openRawResource(int v) throws Resources.NotFoundException {
        return super.openRawResource(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public InputStream openRawResource(int v, TypedValue typedValue0) throws Resources.NotFoundException {
        return super.openRawResource(v, typedValue0);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public AssetFileDescriptor openRawResourceFd(int v) throws Resources.NotFoundException {
        return super.openRawResourceFd(v);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public void parseBundleExtra(String s, AttributeSet attributeSet0, Bundle bundle0) throws XmlPullParserException {
        super.parseBundleExtra(s, attributeSet0, bundle0);
    }

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public void parseBundleExtras(XmlResourceParser xmlResourceParser0, Bundle bundle0) throws XmlPullParserException, IOException {
        super.parseBundleExtras(xmlResourceParser0, bundle0);
    }

    public static void setCompatVectorFromResourcesEnabled(boolean z) {
        VectorEnabledTintResources.sCompatVectorFromResourcesEnabled = z;
    }

    public static boolean shouldBeUsed() [...] // 潜在的解密器

    @Override  // androidx.appcompat.widget.ResourcesWrapper
    public void updateConfiguration(Configuration configuration0, DisplayMetrics displayMetrics0) {
        super.updateConfiguration(configuration0, displayMetrics0);
    }
}

