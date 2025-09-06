package androidx.core.app;

import android.app.RemoteInput.Builder;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class RemoteInput {
    static class Api20Impl {
        static void addResultsToIntent(Object object0, Intent intent0, Bundle bundle0) {
            android.app.RemoteInput.addResultsToIntent(((android.app.RemoteInput[])object0), intent0, bundle0);
        }

        public static android.app.RemoteInput fromCompat(RemoteInput remoteInput0) {
            RemoteInput.Builder remoteInput$Builder0 = new RemoteInput.Builder(remoteInput0.getResultKey()).setLabel(remoteInput0.getLabel()).setChoices(remoteInput0.getChoices()).setAllowFreeFormInput(remoteInput0.getAllowFreeFormInput()).addExtras(remoteInput0.getExtras());
            if(Build.VERSION.SDK_INT >= 26) {
                Set set0 = remoteInput0.getAllowedDataTypes();
                if(set0 != null) {
                    for(Object object0: set0) {
                        Api26Impl.setAllowDataType(remoteInput$Builder0, ((String)object0), true);
                    }
                }
            }
            if(Build.VERSION.SDK_INT >= 29) {
                Api29Impl.setEditChoicesBeforeSending(remoteInput$Builder0, remoteInput0.getEditChoicesBeforeSending());
            }
            return remoteInput$Builder0.build();
        }

        static RemoteInput fromPlatform(Object object0) {
            Builder remoteInput$Builder0 = new Builder(((android.app.RemoteInput)object0).getResultKey()).setLabel(((android.app.RemoteInput)object0).getLabel()).setChoices(((android.app.RemoteInput)object0).getChoices()).setAllowFreeFormInput(((android.app.RemoteInput)object0).getAllowFreeFormInput()).addExtras(((android.app.RemoteInput)object0).getExtras());
            if(Build.VERSION.SDK_INT >= 26) {
                Set set0 = Api26Impl.getAllowedDataTypes(((android.app.RemoteInput)object0));
                if(set0 != null) {
                    for(Object object1: set0) {
                        remoteInput$Builder0.setAllowDataType(((String)object1), true);
                    }
                }
            }
            if(Build.VERSION.SDK_INT >= 29) {
                remoteInput$Builder0.setEditChoicesBeforeSending(Api29Impl.getEditChoicesBeforeSending(((android.app.RemoteInput)object0)));
            }
            return remoteInput$Builder0.build();
        }

        static Bundle getResultsFromIntent(Intent intent0) {
            return android.app.RemoteInput.getResultsFromIntent(intent0);
        }
    }

    static class Api26Impl {
        static void addDataResultToIntent(RemoteInput remoteInput0, Intent intent0, Map map0) {
            android.app.RemoteInput.addDataResultToIntent(RemoteInput.fromCompat(remoteInput0), intent0, map0);
        }

        static Set getAllowedDataTypes(Object object0) {
            return ((android.app.RemoteInput)object0).getAllowedDataTypes();
        }

        static Map getDataResultsFromIntent(Intent intent0, String s) {
            return android.app.RemoteInput.getDataResultsFromIntent(intent0, s);
        }

        static RemoteInput.Builder setAllowDataType(RemoteInput.Builder remoteInput$Builder0, String s, boolean z) {
            return remoteInput$Builder0.setAllowDataType(s, z);
        }
    }

    static class Api28Impl {
        static int getResultsSource(Intent intent0) {
            return android.app.RemoteInput.getResultsSource(intent0);
        }

        static void setResultsSource(Intent intent0, int v) {
            android.app.RemoteInput.setResultsSource(intent0, v);
        }
    }

    static class Api29Impl {
        static int getEditChoicesBeforeSending(Object object0) {
            return ((android.app.RemoteInput)object0).getEditChoicesBeforeSending();
        }

        static RemoteInput.Builder setEditChoicesBeforeSending(RemoteInput.Builder remoteInput$Builder0, int v) {
            return remoteInput$Builder0.setEditChoicesBeforeSending(v);
        }
    }

    public static final class Builder {
        private boolean mAllowFreeFormTextInput;
        private final Set mAllowedDataTypes;
        private CharSequence[] mChoices;
        private int mEditChoicesBeforeSending;
        private final Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(String s) {
            this.mAllowedDataTypes = new HashSet();
            this.mExtras = new Bundle();
            this.mAllowFreeFormTextInput = true;
            this.mEditChoicesBeforeSending = 0;
            if(s == null) {
                throw new IllegalArgumentException("Result key can\'t be null");
            }
            this.mResultKey = s;
        }

        public Builder addExtras(Bundle bundle0) {
            if(bundle0 != null) {
                this.mExtras.putAll(bundle0);
            }
            return this;
        }

        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mEditChoicesBeforeSending, this.mExtras, this.mAllowedDataTypes);
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public Builder setAllowDataType(String s, boolean z) {
            if(z) {
                this.mAllowedDataTypes.add(s);
                return this;
            }
            this.mAllowedDataTypes.remove(s);
            return this;
        }

        public Builder setAllowFreeFormInput(boolean z) {
            this.mAllowFreeFormTextInput = z;
            return this;
        }

        public Builder setChoices(CharSequence[] arr_charSequence) {
            this.mChoices = arr_charSequence;
            return this;
        }

        public Builder setEditChoicesBeforeSending(int v) {
            this.mEditChoicesBeforeSending = v;
            return this;
        }

        public Builder setLabel(CharSequence charSequence0) {
            this.mLabel = charSequence0;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EditChoicesBeforeSending {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Source {
    }

    public static final int EDIT_CHOICES_BEFORE_SENDING_AUTO = 0;
    public static final int EDIT_CHOICES_BEFORE_SENDING_DISABLED = 1;
    public static final int EDIT_CHOICES_BEFORE_SENDING_ENABLED = 2;
    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    private static final String EXTRA_RESULTS_SOURCE = "android.remoteinput.resultsSource";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    public static final int SOURCE_CHOICE = 1;
    public static final int SOURCE_FREE_FORM_INPUT;
    private final boolean mAllowFreeFormTextInput;
    private final Set mAllowedDataTypes;
    private final CharSequence[] mChoices;
    private final int mEditChoicesBeforeSending;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    RemoteInput(String s, CharSequence charSequence0, CharSequence[] arr_charSequence, boolean z, int v, Bundle bundle0, Set set0) {
        this.mResultKey = s;
        this.mLabel = charSequence0;
        this.mChoices = arr_charSequence;
        this.mAllowFreeFormTextInput = z;
        this.mEditChoicesBeforeSending = v;
        this.mExtras = bundle0;
        this.mAllowedDataTypes = set0;
        if(this.getEditChoicesBeforeSending() == 2 && !this.getAllowFreeFormInput()) {
            throw new IllegalArgumentException("setEditChoicesBeforeSending requires setAllowFreeFormInput");
        }
    }

    public static void addDataResultToIntent(RemoteInput remoteInput0, Intent intent0, Map map0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.addDataResultToIntent(remoteInput0, intent0, map0);
            return;
        }
        Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
        if(intent1 == null) {
            intent1 = new Intent();
        }
        for(Object object0: map0.entrySet()) {
            String s = (String)((Map.Entry)object0).getKey();
            Uri uri0 = (Uri)((Map.Entry)object0).getValue();
            if(s != null) {
                Bundle bundle0 = intent1.getBundleExtra("android.remoteinput.dataTypeResultsData" + s);
                if(bundle0 == null) {
                    bundle0 = new Bundle();
                }
                bundle0.putString(remoteInput0.getResultKey(), uri0.toString());
                intent1.putExtra("android.remoteinput.dataTypeResultsData" + s, bundle0);
            }
        }
        intent0.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
    }

    public static void addResultsToIntent(RemoteInput[] arr_remoteInput, Intent intent0, Bundle bundle0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api20Impl.addResultsToIntent(RemoteInput.fromCompat(arr_remoteInput), intent0, bundle0);
            return;
        }
        Bundle bundle1 = RemoteInput.getResultsFromIntent(intent0);
        int v = RemoteInput.getResultsSource(intent0);
        if(bundle1 != null) {
            bundle1.putAll(bundle0);
            bundle0 = bundle1;
        }
        for(int v1 = 0; v1 < arr_remoteInput.length; ++v1) {
            RemoteInput remoteInput0 = arr_remoteInput[v1];
            Map map0 = RemoteInput.getDataResultsFromIntent(intent0, remoteInput0.getResultKey());
            Api20Impl.addResultsToIntent(RemoteInput.fromCompat(new RemoteInput[]{remoteInput0}), intent0, bundle0);
            if(map0 != null) {
                RemoteInput.addDataResultToIntent(remoteInput0, intent0, map0);
            }
        }
        RemoteInput.setResultsSource(intent0, v);
    }

    static android.app.RemoteInput fromCompat(RemoteInput remoteInput0) {
        return Api20Impl.fromCompat(remoteInput0);
    }

    static android.app.RemoteInput[] fromCompat(RemoteInput[] arr_remoteInput) {
        if(arr_remoteInput == null) {
            return null;
        }
        android.app.RemoteInput[] arr_remoteInput1 = new android.app.RemoteInput[arr_remoteInput.length];
        for(int v = 0; v < arr_remoteInput.length; ++v) {
            arr_remoteInput1[v] = RemoteInput.fromCompat(arr_remoteInput[v]);
        }
        return arr_remoteInput1;
    }

    static RemoteInput fromPlatform(android.app.RemoteInput remoteInput0) {
        return Api20Impl.fromPlatform(remoteInput0);
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormTextInput;
    }

    public Set getAllowedDataTypes() {
        return this.mAllowedDataTypes;
    }

    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    private static Intent getClipDataIntentFromIntent(Intent intent0) {
        ClipData clipData0 = intent0.getClipData();
        if(clipData0 == null) {
            return null;
        }
        ClipDescription clipDescription0 = clipData0.getDescription();
        if(!clipDescription0.hasMimeType("text/vnd.android.intent")) {
            return null;
        }
        return clipDescription0.getLabel().toString().contentEquals("android.remoteinput.results") ? clipData0.getItemAt(0).getIntent() : null;
    }

    public static Map getDataResultsFromIntent(Intent intent0, String s) {
        if(Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getDataResultsFromIntent(intent0, s);
        }
        Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
        if(intent1 == null) {
            return null;
        }
        Map map0 = new HashMap();
        for(Object object0: intent1.getExtras().keySet()) {
            String s1 = (String)object0;
            if(s1.startsWith("android.remoteinput.dataTypeResultsData")) {
                String s2 = s1.substring(39);
                if(!s2.isEmpty()) {
                    String s3 = intent1.getBundleExtra(s1).getString(s);
                    if(s3 != null && !s3.isEmpty()) {
                        map0.put(s2, Uri.parse(s3));
                    }
                }
            }
        }
        return map0.isEmpty() ? null : map0;
    }

    public int getEditChoicesBeforeSending() {
        return this.mEditChoicesBeforeSending;
    }

    private static String getExtraResultsKeyForData(String s) [...] // Inlined contents

    public Bundle getExtras() {
        return this.mExtras;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public String getResultKey() {
        return this.mResultKey;
    }

    public static Bundle getResultsFromIntent(Intent intent0) {
        return Api20Impl.getResultsFromIntent(intent0);
    }

    public static int getResultsSource(Intent intent0) {
        if(Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getResultsSource(intent0);
        }
        Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
        return intent1 == null ? 0 : intent1.getExtras().getInt("android.remoteinput.resultsSource", 0);
    }

    // 去混淆评级： 低(30)
    public boolean isDataOnly() {
        return !this.getAllowFreeFormInput() && (this.getChoices() == null || this.getChoices().length == 0) && this.getAllowedDataTypes() != null && !this.getAllowedDataTypes().isEmpty();
    }

    public static void setResultsSource(Intent intent0, int v) {
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setResultsSource(intent0, v);
            return;
        }
        Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
        if(intent1 == null) {
            intent1 = new Intent();
        }
        intent1.putExtra("android.remoteinput.resultsSource", v);
        intent0.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
    }
}

