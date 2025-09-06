package androidx.core.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent.ShortcutIconResource;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

public class IconCompat extends CustomVersionedParcelable {
    static class Api23Impl {
        static IconCompat createFromIcon(Context context0, Icon icon0) {
            switch(Api23Impl.getType(icon0)) {
                case 2: {
                    String s = Api23Impl.getResPackage(icon0);
                    try {
                        return IconCompat.createWithResource(IconCompat.getResources(context0, s), s, Api23Impl.getResId(icon0));
                    }
                    catch(Resources.NotFoundException unused_ex) {
                        throw new IllegalArgumentException("Icon resource cannot be found");
                    }
                }
                case 4: {
                    return IconCompat.createWithContentUri(Api23Impl.getUri(icon0));
                }
                case 6: {
                    return IconCompat.createWithAdaptiveBitmapContentUri(Api23Impl.getUri(icon0));
                }
                default: {
                    IconCompat iconCompat0 = new IconCompat(-1);
                    iconCompat0.mObj1 = icon0;
                    return iconCompat0;
                }
            }
        }

        static IconCompat createFromIconInner(Object object0) {
            Preconditions.checkNotNull(object0);
            switch(Api23Impl.getType(object0)) {
                case 2: {
                    return IconCompat.createWithResource(null, Api23Impl.getResPackage(object0), Api23Impl.getResId(object0));
                }
                case 4: {
                    return IconCompat.createWithContentUri(Api23Impl.getUri(object0));
                }
                case 6: {
                    return IconCompat.createWithAdaptiveBitmapContentUri(Api23Impl.getUri(object0));
                }
                default: {
                    IconCompat iconCompat0 = new IconCompat(-1);
                    iconCompat0.mObj1 = object0;
                    return iconCompat0;
                }
            }
        }

        static int getResId(Object object0) {
            if(Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.getResId(object0);
            }
            try {
                return (int)(((Integer)object0.getClass().getMethod("getResId", null).invoke(object0, null)));
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("IconCompat", "Unable to get icon resource", illegalAccessException0);
                return 0;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.e("IconCompat", "Unable to get icon resource", invocationTargetException0);
                return 0;
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.e("IconCompat", "Unable to get icon resource", noSuchMethodException0);
                return 0;
            }
        }

        static String getResPackage(Object object0) {
            if(Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.getResPackage(object0);
            }
            try {
                return (String)object0.getClass().getMethod("getResPackage", null).invoke(object0, null);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("IconCompat", "Unable to get icon package", illegalAccessException0);
                return null;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.e("IconCompat", "Unable to get icon package", invocationTargetException0);
                return null;
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.e("IconCompat", "Unable to get icon package", noSuchMethodException0);
                return null;
            }
        }

        static int getType(Object object0) {
            if(Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.getType(object0);
            }
            try {
                return (int)(((Integer)object0.getClass().getMethod("getType", null).invoke(object0, null)));
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("IconCompat", "Unable to get icon type " + object0, illegalAccessException0);
                return -1;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.e("IconCompat", "Unable to get icon type " + object0, invocationTargetException0);
                return -1;
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.e("IconCompat", "Unable to get icon type " + object0, noSuchMethodException0);
                return -1;
            }
        }

        static Uri getUri(Object object0) {
            if(Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.getUri(object0);
            }
            try {
                return (Uri)object0.getClass().getMethod("getUri", null).invoke(object0, null);
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("IconCompat", "Unable to get icon uri", illegalAccessException0);
                return null;
            }
            catch(InvocationTargetException invocationTargetException0) {
                Log.e("IconCompat", "Unable to get icon uri", invocationTargetException0);
                return null;
            }
            catch(NoSuchMethodException noSuchMethodException0) {
                Log.e("IconCompat", "Unable to get icon uri", noSuchMethodException0);
                return null;
            }
        }

        static Drawable loadDrawable(Icon icon0, Context context0) {
            return icon0.loadDrawable(context0);
        }

        static Icon toIcon(IconCompat iconCompat0, Context context0) {
            Icon icon0;
            switch(iconCompat0.mType) {
                case -1: {
                    return (Icon)iconCompat0.mObj1;
                }
                case 1: {
                    icon0 = Icon.createWithBitmap(((Bitmap)iconCompat0.mObj1));
                    goto label_23;
                }
                case 2: {
                    icon0 = Icon.createWithResource(iconCompat0.getResPackage(), iconCompat0.mInt1);
                    goto label_23;
                }
                case 3: {
                    icon0 = Icon.createWithData(((byte[])iconCompat0.mObj1), iconCompat0.mInt1, iconCompat0.mInt2);
                    goto label_23;
                }
                case 4: {
                    icon0 = Icon.createWithContentUri(((String)iconCompat0.mObj1));
                    goto label_23;
                }
                case 5: {
                    if(Build.VERSION.SDK_INT >= 26) {
                        icon0 = Api26Impl.createWithAdaptiveBitmap(((Bitmap)iconCompat0.mObj1));
                        goto label_23;
                    }
                    icon0 = Icon.createWithBitmap(IconCompat.createLegacyIconFromAdaptiveIcon(((Bitmap)iconCompat0.mObj1), false));
                    goto label_23;
                }
                case 6: {
                    if(Build.VERSION.SDK_INT >= 30) {
                        icon0 = Api30Impl.createWithAdaptiveBitmapContentUri(iconCompat0.getUri());
                    }
                    else {
                        if(context0 == null) {
                            throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + iconCompat0.getUri());
                        }
                        InputStream inputStream0 = iconCompat0.getUriInputStream(context0);
                        if(inputStream0 == null) {
                            throw new IllegalStateException("Cannot load adaptive icon from uri: " + iconCompat0.getUri());
                        }
                        icon0 = Build.VERSION.SDK_INT < 26 ? Icon.createWithBitmap(IconCompat.createLegacyIconFromAdaptiveIcon(BitmapFactory.decodeStream(inputStream0), false)) : Api26Impl.createWithAdaptiveBitmap(BitmapFactory.decodeStream(inputStream0));
                    }
                label_23:
                    if(iconCompat0.mTintList != null) {
                        icon0.setTintList(iconCompat0.mTintList);
                    }
                    if(iconCompat0.mTintMode != IconCompat.DEFAULT_TINT_MODE) {
                        icon0.setTintMode(iconCompat0.mTintMode);
                    }
                    return icon0;
                }
                default: {
                    throw new IllegalArgumentException("Unknown type");
                }
            }
        }
    }

    static class Api26Impl {
        static Drawable createAdaptiveIconDrawable(Drawable drawable0, Drawable drawable1) {
            return new AdaptiveIconDrawable(drawable0, drawable1);
        }

        static Icon createWithAdaptiveBitmap(Bitmap bitmap0) {
            return Icon.createWithAdaptiveBitmap(bitmap0);
        }
    }

    static class Api28Impl {
        static int getResId(Object object0) {
            return ((Icon)object0).getResId();
        }

        static String getResPackage(Object object0) {
            return ((Icon)object0).getResPackage();
        }

        static int getType(Object object0) {
            return ((Icon)object0).getType();
        }

        static Uri getUri(Object object0) {
            return ((Icon)object0).getUri();
        }
    }

    static class Api30Impl {
        static Icon createWithAdaptiveBitmapContentUri(Uri uri0) {
            return Icon.createWithAdaptiveBitmapContentUri(uri0);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IconType {
    }

    private static final float ADAPTIVE_ICON_INSET_FACTOR = 0.25f;
    private static final int AMBIENT_SHADOW_ALPHA = 30;
    private static final float BLUR_FACTOR = 0.010417f;
    static final PorterDuff.Mode DEFAULT_TINT_MODE = null;
    private static final float DEFAULT_VIEW_PORT_SCALE = 0.666667f;
    static final String EXTRA_INT1 = "int1";
    static final String EXTRA_INT2 = "int2";
    static final String EXTRA_OBJ = "obj";
    static final String EXTRA_STRING1 = "string1";
    static final String EXTRA_TINT_LIST = "tint_list";
    static final String EXTRA_TINT_MODE = "tint_mode";
    static final String EXTRA_TYPE = "type";
    private static final float ICON_DIAMETER_FACTOR = 0.916667f;
    private static final int KEY_SHADOW_ALPHA = 61;
    private static final float KEY_SHADOW_OFFSET_FACTOR = 0.020833f;
    private static final String TAG = "IconCompat";
    public static final int TYPE_ADAPTIVE_BITMAP = 5;
    public static final int TYPE_BITMAP = 1;
    public static final int TYPE_DATA = 3;
    public static final int TYPE_RESOURCE = 2;
    public static final int TYPE_UNKNOWN = -1;
    public static final int TYPE_URI = 4;
    public static final int TYPE_URI_ADAPTIVE_BITMAP = 6;
    public byte[] mData;
    public int mInt1;
    public int mInt2;
    Object mObj1;
    public Parcelable mParcelable;
    public String mString1;
    public ColorStateList mTintList;
    PorterDuff.Mode mTintMode;
    public String mTintModeStr;
    public int mType;

    static {
        IconCompat.DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    }

    public IconCompat() {
        this.mType = -1;
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = IconCompat.DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
    }

    IconCompat(int v) {
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = IconCompat.DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
        this.mType = v;
    }

    public void addToShortcutIntent(Intent intent0, Drawable drawable0, Context context0) {
        Bitmap bitmap0;
        this.checkResource(context0);
        switch(this.mType) {
            case 1: {
                bitmap0 = (Bitmap)this.mObj1;
                if(drawable0 != null) {
                    bitmap0 = bitmap0.copy(bitmap0.getConfig(), true);
                }
                break;
            }
            case 2: {
                try {
                    Context context1 = context0.createPackageContext(this.getResPackage(), 0);
                    if(drawable0 == null) {
                        intent0.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(context1, this.mInt1));
                        return;
                    }
                    Drawable drawable1 = ContextCompat.getDrawable(context1, this.mInt1);
                    if(drawable1.getIntrinsicWidth() <= 0 || drawable1.getIntrinsicHeight() <= 0) {
                        int v = ((ActivityManager)context1.getSystemService("activity")).getLauncherLargeIconSize();
                        bitmap0 = Bitmap.createBitmap(v, v, Bitmap.Config.ARGB_8888);
                    }
                    else {
                        bitmap0 = Bitmap.createBitmap(drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    }
                    drawable1.setBounds(0, 0, bitmap0.getWidth(), bitmap0.getHeight());
                    drawable1.draw(new Canvas(bitmap0));
                    break;
                }
                catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                    throw new IllegalArgumentException("Can\'t find package " + this.mObj1, packageManager$NameNotFoundException0);
                }
            }
            case 5: {
                bitmap0 = IconCompat.createLegacyIconFromAdaptiveIcon(((Bitmap)this.mObj1), true);
                break;
            }
            default: {
                throw new IllegalArgumentException("Icon type not supported for intent shortcuts");
            }
        }
        if(drawable0 != null) {
            int v1 = bitmap0.getWidth();
            int v2 = bitmap0.getHeight();
            drawable0.setBounds(v1 / 2, v2 / 2, v1, v2);
            drawable0.draw(new Canvas(bitmap0));
        }
        intent0.putExtra("android.intent.extra.shortcut.ICON", bitmap0);
    }

    public void checkResource(Context context0) {
        if(this.mType == 2) {
            Object object0 = this.mObj1;
            if(object0 != null && ((String)object0).contains(":")) {
                String s = ((String)object0).split(":", -1)[1];
                String s1 = s.split("/", -1)[0];
                String s2 = s.split("/", -1)[1];
                String s3 = ((String)object0).split(":", -1)[0];
                if("0_resource_name_obfuscated".equals(s2)) {
                    Log.i("IconCompat", "Found obfuscated resource, not trying to update resource id for it");
                    return;
                }
                String s4 = this.getResPackage();
                int v = IconCompat.getResources(context0, s4).getIdentifier(s2, s1, s3);
                if(this.mInt1 != v) {
                    Log.i("IconCompat", "Id has changed for " + s4 + " " + ((String)object0));
                    this.mInt1 = v;
                }
            }
        }
    }

    public static IconCompat createFromBundle(Bundle bundle0) {
        int v = bundle0.getInt("type");
        IconCompat iconCompat0 = new IconCompat(v);
        iconCompat0.mInt1 = bundle0.getInt("int1");
        iconCompat0.mInt2 = bundle0.getInt("int2");
        iconCompat0.mString1 = bundle0.getString("string1");
        if(bundle0.containsKey("tint_list")) {
            iconCompat0.mTintList = (ColorStateList)bundle0.getParcelable("tint_list");
        }
        if(bundle0.containsKey("tint_mode")) {
            iconCompat0.mTintMode = PorterDuff.Mode.valueOf(bundle0.getString("tint_mode"));
        }
        switch(v) {
            case 3: {
                iconCompat0.mObj1 = bundle0.getByteArray("obj");
                return iconCompat0;
            }
            case -1: 
            case 1: 
            case 5: {
                iconCompat0.mObj1 = bundle0.getParcelable("obj");
                return iconCompat0;
            }
            case 2: 
            case 4: 
            case 6: {
                iconCompat0.mObj1 = bundle0.getString("obj");
                return iconCompat0;
            }
            default: {
                Log.w("IconCompat", "Unknown type " + v);
                return null;
            }
        }
    }

    public static IconCompat createFromIcon(Context context0, Icon icon0) {
        Preconditions.checkNotNull(icon0);
        return Api23Impl.createFromIcon(context0, icon0);
    }

    public static IconCompat createFromIcon(Icon icon0) {
        return Api23Impl.createFromIconInner(icon0);
    }

    public static IconCompat createFromIconOrNullIfZeroResId(Icon icon0) {
        return Api23Impl.getType(icon0) != 2 || Api23Impl.getResId(icon0) != 0 ? Api23Impl.createFromIconInner(icon0) : null;
    }

    static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap bitmap0, boolean z) {
        int v = (int)(((float)Math.min(bitmap0.getWidth(), bitmap0.getHeight())) * 0.666667f);
        Bitmap bitmap1 = Bitmap.createBitmap(v, v, Bitmap.Config.ARGB_8888);
        Canvas canvas0 = new Canvas(bitmap1);
        Paint paint0 = new Paint(3);
        float f = 0.5f * ((float)v);
        if(z) {
            paint0.setColor(0);
            paint0.setShadowLayer(0.010417f * ((float)v), 0.0f, ((float)v) * 0.020833f, 0x3D000000);
            canvas0.drawCircle(f, f, 0.916667f * f, paint0);
            paint0.setShadowLayer(0.010417f * ((float)v), 0.0f, 0.0f, 0x1E000000);
            canvas0.drawCircle(f, f, 0.916667f * f, paint0);
            paint0.clearShadowLayer();
        }
        paint0.setColor(0xFF000000);
        BitmapShader bitmapShader0 = new BitmapShader(bitmap0, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix0 = new Matrix();
        matrix0.setTranslate(((float)(-(bitmap0.getWidth() - v))) / 2.0f, ((float)(-(bitmap0.getHeight() - v))) / 2.0f);
        bitmapShader0.setLocalMatrix(matrix0);
        paint0.setShader(bitmapShader0);
        canvas0.drawCircle(f, f, 0.916667f * f, paint0);
        canvas0.setBitmap(null);
        return bitmap1;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap bitmap0) {
        ObjectsCompat.requireNonNull(bitmap0);
        IconCompat iconCompat0 = new IconCompat(5);
        iconCompat0.mObj1 = bitmap0;
        return iconCompat0;
    }

    public static IconCompat createWithAdaptiveBitmapContentUri(Uri uri0) {
        ObjectsCompat.requireNonNull(uri0);
        return IconCompat.createWithAdaptiveBitmapContentUri(uri0.toString());
    }

    public static IconCompat createWithAdaptiveBitmapContentUri(String s) {
        ObjectsCompat.requireNonNull(s);
        IconCompat iconCompat0 = new IconCompat(6);
        iconCompat0.mObj1 = s;
        return iconCompat0;
    }

    public static IconCompat createWithBitmap(Bitmap bitmap0) {
        ObjectsCompat.requireNonNull(bitmap0);
        IconCompat iconCompat0 = new IconCompat(1);
        iconCompat0.mObj1 = bitmap0;
        return iconCompat0;
    }

    public static IconCompat createWithContentUri(Uri uri0) {
        ObjectsCompat.requireNonNull(uri0);
        return IconCompat.createWithContentUri(uri0.toString());
    }

    public static IconCompat createWithContentUri(String s) {
        ObjectsCompat.requireNonNull(s);
        IconCompat iconCompat0 = new IconCompat(4);
        iconCompat0.mObj1 = s;
        return iconCompat0;
    }

    public static IconCompat createWithData(byte[] arr_b, int v, int v1) {
        ObjectsCompat.requireNonNull(arr_b);
        IconCompat iconCompat0 = new IconCompat(3);
        iconCompat0.mObj1 = arr_b;
        iconCompat0.mInt1 = v;
        iconCompat0.mInt2 = v1;
        return iconCompat0;
    }

    public static IconCompat createWithResource(Context context0, int v) {
        ObjectsCompat.requireNonNull(context0);
        return IconCompat.createWithResource(context0.getResources(), "com.ruliweb.www.ruliapp", v);
    }

    public static IconCompat createWithResource(Resources resources0, String s, int v) {
        ObjectsCompat.requireNonNull(s);
        if(v == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat0 = new IconCompat(2);
        iconCompat0.mInt1 = v;
        if(resources0 == null) {
            iconCompat0.mObj1 = s;
        }
        else {
            try {
                iconCompat0.mObj1 = resources0.getResourceName(v);
            }
            catch(Resources.NotFoundException unused_ex) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        }
        iconCompat0.mString1 = s;
        return iconCompat0;
    }

    public Bitmap getBitmap() {
        if(this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return this.mObj1 instanceof Bitmap ? ((Bitmap)this.mObj1) : null;
        }
        int v = this.mType;
        if(v == 1) {
            return (Bitmap)this.mObj1;
        }
        if(v != 5) {
            throw new IllegalStateException("called getBitmap() on " + this);
        }
        return IconCompat.createLegacyIconFromAdaptiveIcon(((Bitmap)this.mObj1), true);
    }

    public int getResId() {
        if(this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getResId(this.mObj1);
        }
        if(this.mType != 2) {
            throw new IllegalStateException("called getResId() on " + this);
        }
        return this.mInt1;
    }

    public String getResPackage() {
        if(this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getResPackage(this.mObj1);
        }
        if(this.mType != 2) {
            throw new IllegalStateException("called getResPackage() on " + this);
        }
        return this.mString1 == null || TextUtils.isEmpty(this.mString1) ? ((String)this.mObj1).split(":", -1)[0] : this.mString1;
    }

    static Resources getResources(Context context0, String s) {
        if("android".equals(s)) {
            return Resources.getSystem();
        }
        PackageManager packageManager0 = context0.getPackageManager();
        try {
            ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo(s, 0x2000);
            return applicationInfo0 == null ? null : packageManager0.getResourcesForApplication(applicationInfo0);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", s), packageManager$NameNotFoundException0);
            return null;
        }
    }

    public int getType() {
        return this.mType != -1 || Build.VERSION.SDK_INT < 23 ? this.mType : Api23Impl.getType(this.mObj1);
    }

    public Uri getUri() {
        if(this.mType == -1 && Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.getUri(this.mObj1);
        }
        if(this.mType != 4 && this.mType != 6) {
            throw new IllegalStateException("called getUri() on " + this);
        }
        return Uri.parse(((String)this.mObj1));
    }

    public InputStream getUriInputStream(Context context0) {
        Uri uri0 = this.getUri();
        String s = uri0.getScheme();
        if(!"content".equals(s) && !"file".equals(s)) {
            try {
                return new FileInputStream(new File(((String)this.mObj1)));
            }
            catch(FileNotFoundException fileNotFoundException0) {
                Log.w("IconCompat", "Unable to load image from path: " + uri0, fileNotFoundException0);
                return null;
            }
        }
        try {
            return context0.getContentResolver().openInputStream(uri0);
        }
        catch(Exception exception0) {
            Log.w("IconCompat", "Unable to load image from URI: " + uri0, exception0);
            return null;
        }
    }

    public Drawable loadDrawable(Context context0) {
        this.checkResource(context0);
        if(Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.loadDrawable(this.toIcon(context0), context0);
        }
        Drawable drawable0 = this.loadDrawableInner(context0);
        if(drawable0 != null && (this.mTintList != null || this.mTintMode != IconCompat.DEFAULT_TINT_MODE)) {
            drawable0.mutate();
            DrawableCompat.setTintList(drawable0, this.mTintList);
            DrawableCompat.setTintMode(drawable0, this.mTintMode);
        }
        return drawable0;
    }

    private Drawable loadDrawableInner(Context context0) {
        switch(this.mType) {
            case 1: {
                return new BitmapDrawable(context0.getResources(), ((Bitmap)this.mObj1));
            }
            case 2: {
                String s = this.getResPackage();
                if(TextUtils.isEmpty(s)) {
                    s = "com.ruliweb.www.ruliapp";
                }
                Resources resources0 = IconCompat.getResources(context0, s);
                try {
                    return ResourcesCompat.getDrawable(resources0, this.mInt1, context0.getTheme());
                }
                catch(RuntimeException runtimeException0) {
                    Log.e("IconCompat", String.format("Unable to load resource 0x%08x from pkg=%s", this.mInt1, this.mObj1), runtimeException0);
                    return null;
                }
            }
            case 3: {
                return new BitmapDrawable(context0.getResources(), BitmapFactory.decodeByteArray(((byte[])this.mObj1), this.mInt1, this.mInt2));
            }
            case 4: {
                InputStream inputStream0 = this.getUriInputStream(context0);
                if(inputStream0 != null) {
                    return new BitmapDrawable(context0.getResources(), BitmapFactory.decodeStream(inputStream0));
                }
                break;
            }
            case 5: {
                return new BitmapDrawable(context0.getResources(), IconCompat.createLegacyIconFromAdaptiveIcon(((Bitmap)this.mObj1), false));
            }
            case 6: {
                InputStream inputStream1 = this.getUriInputStream(context0);
                if(inputStream1 != null) {
                    return Build.VERSION.SDK_INT >= 26 ? Api26Impl.createAdaptiveIconDrawable(null, new BitmapDrawable(context0.getResources(), BitmapFactory.decodeStream(inputStream1))) : new BitmapDrawable(context0.getResources(), IconCompat.createLegacyIconFromAdaptiveIcon(BitmapFactory.decodeStream(inputStream1), false));
                }
                break;
            }
            default: {
                return null;
            }
        }
        return null;
    }

    @Override  // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPostParceling() {
        this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
        switch(this.mType) {
            case -1: {
                Parcelable parcelable0 = this.mParcelable;
                if(parcelable0 == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.mObj1 = parcelable0;
                return;
            }
            case 3: {
                this.mObj1 = this.mData;
                return;
            }
            case 1: 
            case 5: {
                Parcelable parcelable1 = this.mParcelable;
                if(parcelable1 != null) {
                    this.mObj1 = parcelable1;
                    return;
                }
                this.mObj1 = this.mData;
                this.mType = 3;
                this.mInt1 = 0;
                this.mInt2 = this.mData.length;
                return;
            }
            case 2: 
            case 4: 
            case 6: {
                String s = new String(this.mData, Charset.forName("UTF-16"));
                this.mObj1 = s;
                if(this.mType == 2 && this.mString1 == null) {
                    this.mString1 = s.split(":", -1)[0];
                }
            }
        }
    }

    @Override  // androidx.versionedparcelable.CustomVersionedParcelable
    public void onPreParceling(boolean z) {
        this.mTintModeStr = this.mTintMode.name();
        switch(this.mType) {
            case -1: {
                if(z) {
                    throw new IllegalArgumentException("Can\'t serialize Icon created with IconCompat#createFromIcon");
                }
                this.mParcelable = (Parcelable)this.mObj1;
                return;
            }
            case 2: {
                this.mData = ((String)this.mObj1).getBytes(Charset.forName("UTF-16"));
                return;
            }
            case 3: {
                this.mData = (byte[])this.mObj1;
                return;
            }
            case 1: 
            case 5: {
                if(z) {
                    Bitmap bitmap0 = (Bitmap)this.mObj1;
                    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
                    bitmap0.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream0);
                    this.mData = byteArrayOutputStream0.toByteArray();
                    return;
                }
                this.mParcelable = (Parcelable)this.mObj1;
                return;
            }
            case 4: 
            case 6: {
                this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
            }
        }
    }

    public IconCompat setTint(int v) {
        return this.setTintList(ColorStateList.valueOf(v));
    }

    public IconCompat setTintList(ColorStateList colorStateList0) {
        this.mTintList = colorStateList0;
        return this;
    }

    public IconCompat setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mTintMode = porterDuff$Mode0;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle0 = new Bundle();
        switch(this.mType) {
            case -1: {
                bundle0.putParcelable("obj", ((Parcelable)this.mObj1));
                break;
            }
            case 3: {
                bundle0.putByteArray("obj", ((byte[])this.mObj1));
                break;
            }
            case 1: 
            case 5: {
                bundle0.putParcelable("obj", ((Bitmap)this.mObj1));
                break;
            }
            case 2: 
            case 4: 
            case 6: {
                bundle0.putString("obj", ((String)this.mObj1));
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid icon");
            }
        }
        bundle0.putInt("type", this.mType);
        bundle0.putInt("int1", this.mInt1);
        bundle0.putInt("int2", this.mInt2);
        bundle0.putString("string1", this.mString1);
        ColorStateList colorStateList0 = this.mTintList;
        if(colorStateList0 != null) {
            bundle0.putParcelable("tint_list", colorStateList0);
        }
        PorterDuff.Mode porterDuff$Mode0 = this.mTintMode;
        if(porterDuff$Mode0 != IconCompat.DEFAULT_TINT_MODE) {
            bundle0.putString("tint_mode", porterDuff$Mode0.name());
        }
        return bundle0;
    }

    @Deprecated
    public Icon toIcon() {
        return this.toIcon(null);
    }

    public Icon toIcon(Context context0) {
        if(Build.VERSION.SDK_INT < 23) {
            throw new UnsupportedOperationException("This method is only supported on API level 23+");
        }
        return Api23Impl.toIcon(this, context0);
    }

    @Override
    public String toString() {
        if(this.mType == -1) {
            return String.valueOf(this.mObj1);
        }
        StringBuilder stringBuilder0 = new StringBuilder("Icon(typ=");
        stringBuilder0.append(IconCompat.typeToString(this.mType));
        switch(this.mType) {
            case 2: {
                stringBuilder0.append(" pkg=");
                stringBuilder0.append(this.mString1);
                stringBuilder0.append(" id=");
                stringBuilder0.append(String.format("0x%08x", this.getResId()));
                break;
            }
            case 3: {
                stringBuilder0.append(" len=");
                stringBuilder0.append(this.mInt1);
                if(this.mInt2 != 0) {
                    stringBuilder0.append(" off=");
                    stringBuilder0.append(this.mInt2);
                }
                break;
            }
            case 1: 
            case 5: {
                stringBuilder0.append(" size=");
                stringBuilder0.append(((Bitmap)this.mObj1).getWidth());
                stringBuilder0.append("x");
                stringBuilder0.append(((Bitmap)this.mObj1).getHeight());
                break;
            }
            case 4: 
            case 6: {
                stringBuilder0.append(" uri=");
                stringBuilder0.append(this.mObj1);
            }
        }
        if(this.mTintList != null) {
            stringBuilder0.append(" tint=");
            stringBuilder0.append(this.mTintList);
        }
        if(this.mTintMode != IconCompat.DEFAULT_TINT_MODE) {
            stringBuilder0.append(" mode=");
            stringBuilder0.append(this.mTintMode);
        }
        stringBuilder0.append(")");
        return stringBuilder0.toString();
    }

    private static String typeToString(int v) {
        switch(v) {
            case 1: {
                return "BITMAP";
            }
            case 2: {
                return "RESOURCE";
            }
            case 3: {
                return "DATA";
            }
            case 4: {
                return "URI";
            }
            case 5: {
                return "BITMAP_MASKABLE";
            }
            case 6: {
                return "URI_MASKABLE";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }
}

