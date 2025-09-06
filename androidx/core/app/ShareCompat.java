package androidx.core.app;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import androidx.core.util.Preconditions;
import java.util.ArrayList;

public final class ShareCompat {
    public static class IntentBuilder {
        private ArrayList mBccAddresses;
        private ArrayList mCcAddresses;
        private CharSequence mChooserTitle;
        private final Context mContext;
        private final Intent mIntent;
        private ArrayList mStreams;
        private ArrayList mToAddresses;

        public IntentBuilder(Context context0) {
            Activity activity0;
            this.mContext = (Context)Preconditions.checkNotNull(context0);
            Intent intent0 = new Intent().setAction("android.intent.action.SEND");
            this.mIntent = intent0;
            intent0.putExtra("androidx.core.app.EXTRA_CALLING_PACKAGE", "com.ruliweb.www.ruliapp");
            intent0.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", "com.ruliweb.www.ruliapp");
            intent0.addFlags(0x80000);
            while(true) {
                activity0 = null;
                if(!(context0 instanceof ContextWrapper)) {
                    break;
                }
                if(context0 instanceof Activity) {
                    activity0 = (Activity)context0;
                    break;
                }
                context0 = ((ContextWrapper)context0).getBaseContext();
            }
            if(activity0 != null) {
                ComponentName componentName0 = activity0.getComponentName();
                this.mIntent.putExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY", componentName0);
                this.mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", componentName0);
            }
        }

        public IntentBuilder addEmailBcc(String s) {
            if(this.mBccAddresses == null) {
                this.mBccAddresses = new ArrayList();
            }
            this.mBccAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailBcc(String[] arr_s) {
            this.combineArrayExtra("android.intent.extra.BCC", arr_s);
            return this;
        }

        public IntentBuilder addEmailCc(String s) {
            if(this.mCcAddresses == null) {
                this.mCcAddresses = new ArrayList();
            }
            this.mCcAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailCc(String[] arr_s) {
            this.combineArrayExtra("android.intent.extra.CC", arr_s);
            return this;
        }

        public IntentBuilder addEmailTo(String s) {
            if(this.mToAddresses == null) {
                this.mToAddresses = new ArrayList();
            }
            this.mToAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailTo(String[] arr_s) {
            this.combineArrayExtra("android.intent.extra.EMAIL", arr_s);
            return this;
        }

        public IntentBuilder addStream(Uri uri0) {
            if(this.mStreams == null) {
                this.mStreams = new ArrayList();
            }
            this.mStreams.add(uri0);
            return this;
        }

        private void combineArrayExtra(String s, ArrayList arrayList0) {
            String[] arr_s = this.mIntent.getStringArrayExtra(s);
            int v = arr_s == null ? 0 : arr_s.length;
            String[] arr_s1 = new String[arrayList0.size() + v];
            arrayList0.toArray(arr_s1);
            if(arr_s != null) {
                System.arraycopy(arr_s, 0, arr_s1, arrayList0.size(), v);
            }
            this.mIntent.putExtra(s, arr_s1);
        }

        private void combineArrayExtra(String s, String[] arr_s) {
            Intent intent0 = this.getIntent();
            String[] arr_s1 = intent0.getStringArrayExtra(s);
            int v = arr_s1 == null ? 0 : arr_s1.length;
            String[] arr_s2 = new String[arr_s.length + v];
            if(arr_s1 != null) {
                System.arraycopy(arr_s1, 0, arr_s2, 0, v);
            }
            System.arraycopy(arr_s, 0, arr_s2, v, arr_s.length);
            intent0.putExtra(s, arr_s2);
        }

        public Intent createChooserIntent() {
            return Intent.createChooser(this.getIntent(), this.mChooserTitle);
        }

        @Deprecated
        public static IntentBuilder from(Activity activity0) {
            return new IntentBuilder(activity0);
        }

        Context getContext() {
            return this.mContext;
        }

        public Intent getIntent() {
            ArrayList arrayList0 = this.mToAddresses;
            if(arrayList0 != null) {
                this.combineArrayExtra("android.intent.extra.EMAIL", arrayList0);
                this.mToAddresses = null;
            }
            ArrayList arrayList1 = this.mCcAddresses;
            if(arrayList1 != null) {
                this.combineArrayExtra("android.intent.extra.CC", arrayList1);
                this.mCcAddresses = null;
            }
            ArrayList arrayList2 = this.mBccAddresses;
            if(arrayList2 != null) {
                this.combineArrayExtra("android.intent.extra.BCC", arrayList2);
                this.mBccAddresses = null;
            }
            if(this.mStreams != null && this.mStreams.size() > 1) {
                this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
                this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mStreams);
                ShareCompat.migrateExtraStreamToClipData(this.mIntent, this.mStreams);
                return this.mIntent;
            }
            this.mIntent.setAction("android.intent.action.SEND");
            if(this.mStreams != null && !this.mStreams.isEmpty()) {
                Parcelable parcelable0 = (Parcelable)this.mStreams.get(0);
                this.mIntent.putExtra("android.intent.extra.STREAM", parcelable0);
                ShareCompat.migrateExtraStreamToClipData(this.mIntent, this.mStreams);
                return this.mIntent;
            }
            this.mIntent.removeExtra("android.intent.extra.STREAM");
            this.mIntent.setClipData(null);
            int v = this.mIntent.getFlags();
            this.mIntent.setFlags(v & -2);
            return this.mIntent;
        }

        public IntentBuilder setChooserTitle(int v) {
            return this.setChooserTitle(this.mContext.getText(v));
        }

        public IntentBuilder setChooserTitle(CharSequence charSequence0) {
            this.mChooserTitle = charSequence0;
            return this;
        }

        public IntentBuilder setEmailBcc(String[] arr_s) {
            this.mIntent.putExtra("android.intent.extra.BCC", arr_s);
            return this;
        }

        public IntentBuilder setEmailCc(String[] arr_s) {
            this.mIntent.putExtra("android.intent.extra.CC", arr_s);
            return this;
        }

        public IntentBuilder setEmailTo(String[] arr_s) {
            if(this.mToAddresses != null) {
                this.mToAddresses = null;
            }
            this.mIntent.putExtra("android.intent.extra.EMAIL", arr_s);
            return this;
        }

        public IntentBuilder setHtmlText(String s) {
            this.mIntent.putExtra("android.intent.extra.HTML_TEXT", s);
            if(!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
                this.setText(Html.fromHtml(s));
            }
            return this;
        }

        public IntentBuilder setStream(Uri uri0) {
            this.mStreams = null;
            if(uri0 != null) {
                this.addStream(uri0);
            }
            return this;
        }

        public IntentBuilder setSubject(String s) {
            this.mIntent.putExtra("android.intent.extra.SUBJECT", s);
            return this;
        }

        public IntentBuilder setText(CharSequence charSequence0) {
            this.mIntent.putExtra("android.intent.extra.TEXT", charSequence0);
            return this;
        }

        public IntentBuilder setType(String s) {
            this.mIntent.setType(s);
            return this;
        }

        public void startChooser() {
            Intent intent0 = this.createChooserIntent();
            this.mContext.startActivity(intent0);
        }
    }

    public static class IntentReader {
        private static final String TAG = "IntentReader";
        private final ComponentName mCallingActivity;
        private final String mCallingPackage;
        private final Context mContext;
        private final Intent mIntent;
        private ArrayList mStreams;

        public IntentReader(Activity activity0) {
            this(((Context)Preconditions.checkNotNull(activity0)), activity0.getIntent());
        }

        public IntentReader(Context context0, Intent intent0) {
            this.mContext = (Context)Preconditions.checkNotNull(context0);
            this.mIntent = (Intent)Preconditions.checkNotNull(intent0);
            this.mCallingPackage = ShareCompat.getCallingPackage(intent0);
            this.mCallingActivity = ShareCompat.getCallingActivity(intent0);
        }

        @Deprecated
        public static IntentReader from(Activity activity0) {
            return new IntentReader(activity0);
        }

        public ComponentName getCallingActivity() {
            return this.mCallingActivity;
        }

        public Drawable getCallingActivityIcon() {
            if(this.mCallingActivity == null) {
                return null;
            }
            PackageManager packageManager0 = this.mContext.getPackageManager();
            try {
                return packageManager0.getActivityIcon(this.mCallingActivity);
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.e("IntentReader", "Could not retrieve icon for calling activity", packageManager$NameNotFoundException0);
                return null;
            }
        }

        public Drawable getCallingApplicationIcon() {
            if(this.mCallingPackage == null) {
                return null;
            }
            PackageManager packageManager0 = this.mContext.getPackageManager();
            try {
                return packageManager0.getApplicationIcon(this.mCallingPackage);
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.e("IntentReader", "Could not retrieve icon for calling application", packageManager$NameNotFoundException0);
                return null;
            }
        }

        public CharSequence getCallingApplicationLabel() {
            if(this.mCallingPackage == null) {
                return null;
            }
            PackageManager packageManager0 = this.mContext.getPackageManager();
            try {
                return packageManager0.getApplicationLabel(packageManager0.getApplicationInfo(this.mCallingPackage, 0));
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.e("IntentReader", "Could not retrieve label for calling application", packageManager$NameNotFoundException0);
                return null;
            }
        }

        public String getCallingPackage() {
            return this.mCallingPackage;
        }

        public String[] getEmailBcc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
        }

        public String[] getEmailCc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
        }

        public String[] getEmailTo() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
        }

        public String getHtmlText() {
            String s = this.mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
            if(s == null) {
                CharSequence charSequence0 = this.getText();
                if(charSequence0 instanceof Spanned) {
                    return Html.toHtml(((Spanned)charSequence0));
                }
                return charSequence0 == null ? null : Html.escapeHtml(charSequence0);
            }
            return s;
        }

        public Uri getStream() {
            return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }

        public Uri getStream(int v) {
            if(this.mStreams == null && this.isMultipleShare()) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            ArrayList arrayList0 = this.mStreams;
            if(arrayList0 != null) {
                return (Uri)arrayList0.get(v);
            }
            if(v != 0) {
                throw new IndexOutOfBoundsException("Stream items available: " + this.getStreamCount() + " index requested: " + v);
            }
            return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }

        public int getStreamCount() {
            if(this.mStreams == null && this.isMultipleShare()) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            ArrayList arrayList0 = this.mStreams;
            return arrayList0 != null ? arrayList0.size() : this.mIntent.hasExtra("android.intent.extra.STREAM");
        }

        public String getSubject() {
            return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
        }

        public CharSequence getText() {
            return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
        }

        public String getType() {
            return this.mIntent.getType();
        }

        public boolean isMultipleShare() {
            return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
        }

        public boolean isShareIntent() {
            String s = this.mIntent.getAction();
            return "android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s);
        }

        public boolean isSingleShare() {
            return "android.intent.action.SEND".equals(this.mIntent.getAction());
        }
    }

    public static final String EXTRA_CALLING_ACTIVITY = "androidx.core.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_ACTIVITY_INTEROP = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "androidx.core.app.EXTRA_CALLING_PACKAGE";
    public static final String EXTRA_CALLING_PACKAGE_INTEROP = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
    private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

    @Deprecated
    public static void configureMenuItem(Menu menu0, int v, IntentBuilder shareCompat$IntentBuilder0) {
        MenuItem menuItem0 = menu0.findItem(v);
        if(menuItem0 == null) {
            throw new IllegalArgumentException("Could not find menu item with id " + v + " in the supplied menu");
        }
        ShareCompat.configureMenuItem(menuItem0, shareCompat$IntentBuilder0);
    }

    @Deprecated
    public static void configureMenuItem(MenuItem menuItem0, IntentBuilder shareCompat$IntentBuilder0) {
        ActionProvider actionProvider0 = menuItem0.getActionProvider();
        ShareActionProvider shareActionProvider0 = actionProvider0 instanceof ShareActionProvider ? ((ShareActionProvider)actionProvider0) : new ShareActionProvider(shareCompat$IntentBuilder0.getContext());
        shareActionProvider0.setShareHistoryFileName(".sharecompat_" + shareCompat$IntentBuilder0.getContext().getClass().getName());
        shareActionProvider0.setShareIntent(shareCompat$IntentBuilder0.getIntent());
        menuItem0.setActionProvider(shareActionProvider0);
    }

    public static ComponentName getCallingActivity(Activity activity0) {
        Intent intent0 = activity0.getIntent();
        ComponentName componentName0 = activity0.getCallingActivity();
        return componentName0 == null ? ShareCompat.getCallingActivity(intent0) : componentName0;
    }

    static ComponentName getCallingActivity(Intent intent0) {
        ComponentName componentName0 = (ComponentName)intent0.getParcelableExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY");
        return componentName0 == null ? ((ComponentName)intent0.getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY")) : componentName0;
    }

    public static String getCallingPackage(Activity activity0) {
        Intent intent0 = activity0.getIntent();
        String s = activity0.getCallingPackage();
        return s != null || intent0 == null ? s : ShareCompat.getCallingPackage(intent0);
    }

    static String getCallingPackage(Intent intent0) {
        String s = intent0.getStringExtra("androidx.core.app.EXTRA_CALLING_PACKAGE");
        return s == null ? intent0.getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE") : s;
    }

    static void migrateExtraStreamToClipData(Intent intent0, ArrayList arrayList0) {
        CharSequence charSequence0 = intent0.getCharSequenceExtra("android.intent.extra.TEXT");
        String s = intent0.getStringExtra("android.intent.extra.HTML_TEXT");
        ClipData clipData0 = new ClipData(null, new String[]{intent0.getType()}, new ClipData.Item(charSequence0, s, null, ((Uri)arrayList0.get(0))));
        int v = arrayList0.size();
        for(int v1 = 1; v1 < v; ++v1) {
            clipData0.addItem(new ClipData.Item(((Uri)arrayList0.get(v1))));
        }
        intent0.setClipData(clipData0);
        intent0.addFlags(1);
    }
}

