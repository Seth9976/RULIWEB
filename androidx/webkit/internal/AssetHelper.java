package androidx.webkit.internal;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.util.TypedValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class AssetHelper {
    public static final String DEFAULT_MIME_TYPE = "text/plain";
    private final Context mContext;

    public AssetHelper(Context context0) {
        this.mContext = context0;
    }

    public static String getCanonicalDirPath(File file0) throws IOException {
        String s = file0.getCanonicalPath();
        return s.endsWith("/") ? s : s + "/";
    }

    public static File getCanonicalFileIfChild(File file0, String s) throws IOException {
        String s1 = AssetHelper.getCanonicalDirPath(file0);
        String s2 = new File(file0, s).getCanonicalPath();
        return s2.startsWith(s1) ? new File(s2) : null;
    }

    public static File getDataDir(Context context0) {
        return Build.VERSION.SDK_INT < 24 ? context0.getCacheDir().getParentFile() : ApiHelperForN.getDataDir(context0);
    }

    private int getFieldId(String s, String s1) {
        return this.mContext.getResources().getIdentifier(s1, s, "com.ruliweb.www.ruliapp");
    }

    private int getValueType(int v) {
        TypedValue typedValue0 = new TypedValue();
        this.mContext.getResources().getValue(v, typedValue0, true);
        return typedValue0.type;
    }

    public static String guessMimeType(String s) {
        String s1 = MimeUtil.getMimeFromFileName(s);
        return s1 == null ? "text/plain" : s1;
    }

    private static InputStream handleSvgzStream(String s, InputStream inputStream0) throws IOException {
        return s.endsWith(".svgz") ? new GZIPInputStream(inputStream0) : inputStream0;
    }

    public InputStream openAsset(String s) throws IOException {
        String s1 = AssetHelper.removeLeadingSlash(s);
        return AssetHelper.handleSvgzStream(s1, this.mContext.getAssets().open(s1, 2));
    }

    public static InputStream openFile(File file0) throws FileNotFoundException, IOException {
        FileInputStream fileInputStream0 = new FileInputStream(file0);
        return AssetHelper.handleSvgzStream(file0.getPath(), fileInputStream0);
    }

    public InputStream openResource(String s) throws Resources.NotFoundException, IOException {
        String s1 = AssetHelper.removeLeadingSlash(s);
        String[] arr_s = s1.split("/", -1);
        if(arr_s.length != 2) {
            throw new IllegalArgumentException("Incorrect resource path: " + s1);
        }
        String s2 = arr_s[0];
        String s3 = arr_s[1];
        int v = s3.lastIndexOf(46);
        if(v != -1) {
            s3 = s3.substring(0, v);
        }
        int v1 = this.getFieldId(s2, s3);
        int v2 = this.getValueType(v1);
        if(v2 != 3) {
            throw new IOException(String.format("Expected %s resource to be of TYPE_STRING but was %d", s1, v2));
        }
        return AssetHelper.handleSvgzStream(s1, this.mContext.getResources().openRawResource(v1));
    }

    private static String removeLeadingSlash(String s) {
        return s.length() <= 1 || s.charAt(0) != 0x2F ? s : s.substring(1);
    }
}

