package androidx.appcompat.widget;

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
import androidx.core.content.res.ResourcesCompat;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

class ResourcesWrapper extends Resources {
    private final Resources mResources;

    public ResourcesWrapper(Resources resources0) {
        super(resources0.getAssets(), resources0.getDisplayMetrics(), resources0.getConfiguration());
        this.mResources = resources0;
    }

    @Override  // android.content.res.Resources
    public XmlResourceParser getAnimation(int v) throws Resources.NotFoundException {
        return this.mResources.getAnimation(v);
    }

    @Override  // android.content.res.Resources
    public boolean getBoolean(int v) throws Resources.NotFoundException {
        return this.mResources.getBoolean(v);
    }

    @Override  // android.content.res.Resources
    public int getColor(int v) throws Resources.NotFoundException {
        return this.mResources.getColor(v);
    }

    @Override  // android.content.res.Resources
    public ColorStateList getColorStateList(int v) throws Resources.NotFoundException {
        return this.mResources.getColorStateList(v);
    }

    @Override  // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.mResources.getConfiguration();
    }

    @Override  // android.content.res.Resources
    public float getDimension(int v) throws Resources.NotFoundException {
        return this.mResources.getDimension(v);
    }

    @Override  // android.content.res.Resources
    public int getDimensionPixelOffset(int v) throws Resources.NotFoundException {
        return this.mResources.getDimensionPixelOffset(v);
    }

    @Override  // android.content.res.Resources
    public int getDimensionPixelSize(int v) throws Resources.NotFoundException {
        return this.mResources.getDimensionPixelSize(v);
    }

    @Override  // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.mResources.getDisplayMetrics();
    }

    @Override  // android.content.res.Resources
    public Drawable getDrawable(int v) throws Resources.NotFoundException {
        return this.mResources.getDrawable(v);
    }

    @Override  // android.content.res.Resources
    public Drawable getDrawable(int v, Resources.Theme resources$Theme0) throws Resources.NotFoundException {
        return ResourcesCompat.getDrawable(this.mResources, v, resources$Theme0);
    }

    final Drawable getDrawableCanonical(int v) throws Resources.NotFoundException {
        return super.getDrawable(v);
    }

    @Override  // android.content.res.Resources
    public Drawable getDrawableForDensity(int v, int v1) throws Resources.NotFoundException {
        return ResourcesCompat.getDrawableForDensity(this.mResources, v, v1, null);
    }

    @Override  // android.content.res.Resources
    public Drawable getDrawableForDensity(int v, int v1, Resources.Theme resources$Theme0) {
        return ResourcesCompat.getDrawableForDensity(this.mResources, v, v1, resources$Theme0);
    }

    @Override  // android.content.res.Resources
    public float getFraction(int v, int v1, int v2) {
        return this.mResources.getFraction(v, v1, v2);
    }

    @Override  // android.content.res.Resources
    public int getIdentifier(String s, String s1, String s2) {
        return this.mResources.getIdentifier(s, s1, s2);
    }

    @Override  // android.content.res.Resources
    public int[] getIntArray(int v) throws Resources.NotFoundException {
        return this.mResources.getIntArray(v);
    }

    @Override  // android.content.res.Resources
    public int getInteger(int v) throws Resources.NotFoundException {
        return this.mResources.getInteger(v);
    }

    @Override  // android.content.res.Resources
    public XmlResourceParser getLayout(int v) throws Resources.NotFoundException {
        return this.mResources.getLayout(v);
    }

    @Override  // android.content.res.Resources
    public Movie getMovie(int v) throws Resources.NotFoundException {
        return this.mResources.getMovie(v);
    }

    @Override  // android.content.res.Resources
    public String getQuantityString(int v, int v1) throws Resources.NotFoundException {
        return this.mResources.getQuantityString(v, v1);
    }

    @Override  // android.content.res.Resources
    public String getQuantityString(int v, int v1, Object[] arr_object) throws Resources.NotFoundException {
        return this.mResources.getQuantityString(v, v1, arr_object);
    }

    @Override  // android.content.res.Resources
    public CharSequence getQuantityText(int v, int v1) throws Resources.NotFoundException {
        return this.mResources.getQuantityText(v, v1);
    }

    @Override  // android.content.res.Resources
    public String getResourceEntryName(int v) throws Resources.NotFoundException {
        return this.mResources.getResourceEntryName(v);
    }

    @Override  // android.content.res.Resources
    public String getResourceName(int v) throws Resources.NotFoundException {
        return this.mResources.getResourceName(v);
    }

    @Override  // android.content.res.Resources
    public String getResourcePackageName(int v) throws Resources.NotFoundException {
        return this.mResources.getResourcePackageName(v);
    }

    @Override  // android.content.res.Resources
    public String getResourceTypeName(int v) throws Resources.NotFoundException {
        return this.mResources.getResourceTypeName(v);
    }

    @Override  // android.content.res.Resources
    public String getString(int v) throws Resources.NotFoundException {
        return this.mResources.getString(v);
    }

    @Override  // android.content.res.Resources
    public String getString(int v, Object[] arr_object) throws Resources.NotFoundException {
        return this.mResources.getString(v, arr_object);
    }

    @Override  // android.content.res.Resources
    public String[] getStringArray(int v) throws Resources.NotFoundException {
        return this.mResources.getStringArray(v);
    }

    @Override  // android.content.res.Resources
    public CharSequence getText(int v) throws Resources.NotFoundException {
        return this.mResources.getText(v);
    }

    @Override  // android.content.res.Resources
    public CharSequence getText(int v, CharSequence charSequence0) {
        return this.mResources.getText(v, charSequence0);
    }

    @Override  // android.content.res.Resources
    public CharSequence[] getTextArray(int v) throws Resources.NotFoundException {
        return this.mResources.getTextArray(v);
    }

    @Override  // android.content.res.Resources
    public void getValue(int v, TypedValue typedValue0, boolean z) throws Resources.NotFoundException {
        this.mResources.getValue(v, typedValue0, z);
    }

    @Override  // android.content.res.Resources
    public void getValue(String s, TypedValue typedValue0, boolean z) throws Resources.NotFoundException {
        this.mResources.getValue(s, typedValue0, z);
    }

    @Override  // android.content.res.Resources
    public void getValueForDensity(int v, int v1, TypedValue typedValue0, boolean z) throws Resources.NotFoundException {
        this.mResources.getValueForDensity(v, v1, typedValue0, z);
    }

    @Override  // android.content.res.Resources
    public XmlResourceParser getXml(int v) throws Resources.NotFoundException {
        return this.mResources.getXml(v);
    }

    @Override  // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet0, int[] arr_v) {
        return this.mResources.obtainAttributes(attributeSet0, arr_v);
    }

    @Override  // android.content.res.Resources
    public TypedArray obtainTypedArray(int v) throws Resources.NotFoundException {
        return this.mResources.obtainTypedArray(v);
    }

    @Override  // android.content.res.Resources
    public InputStream openRawResource(int v) throws Resources.NotFoundException {
        return this.mResources.openRawResource(v);
    }

    @Override  // android.content.res.Resources
    public InputStream openRawResource(int v, TypedValue typedValue0) throws Resources.NotFoundException {
        return this.mResources.openRawResource(v, typedValue0);
    }

    @Override  // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int v) throws Resources.NotFoundException {
        return this.mResources.openRawResourceFd(v);
    }

    @Override  // android.content.res.Resources
    public void parseBundleExtra(String s, AttributeSet attributeSet0, Bundle bundle0) throws XmlPullParserException {
        this.mResources.parseBundleExtra(s, attributeSet0, bundle0);
    }

    @Override  // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser0, Bundle bundle0) throws XmlPullParserException, IOException {
        this.mResources.parseBundleExtras(xmlResourceParser0, bundle0);
    }

    @Override  // android.content.res.Resources
    public void updateConfiguration(Configuration configuration0, DisplayMetrics displayMetrics0) {
        super.updateConfiguration(configuration0, displayMetrics0);
        Resources resources0 = this.mResources;
        if(resources0 != null) {
            resources0.updateConfiguration(configuration0, displayMetrics0);
        }
    }
}

