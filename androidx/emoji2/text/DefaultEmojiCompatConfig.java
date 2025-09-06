package androidx.emoji2.text;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.core.provider.FontRequest;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DefaultEmojiCompatConfig {
    public static class DefaultEmojiCompatConfigFactory {
        private static final String DEFAULT_EMOJI_QUERY = "emojicompat-emoji-font";
        private static final String INTENT_LOAD_EMOJI_FONT = "androidx.content.action.LOAD_EMOJI_FONT";
        private static final String TAG = "emoji2.text.DefaultEmojiConfig";
        private final DefaultEmojiCompatConfigHelper mHelper;

        public DefaultEmojiCompatConfigFactory(DefaultEmojiCompatConfigHelper defaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper0) {
            if(defaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper0 == null) {
                defaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper0 = DefaultEmojiCompatConfigFactory.getHelperForApi();
            }
            this.mHelper = defaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper0;
        }

        private Config configOrNull(Context context0, FontRequest fontRequest0) {
            return fontRequest0 == null ? null : new FontRequestEmojiCompatConfig(context0, fontRequest0);
        }

        private List convertToByteArray(Signature[] arr_signature) {
            ArrayList arrayList0 = new ArrayList();
            for(int v = 0; v < arr_signature.length; ++v) {
                arrayList0.add(arr_signature[v].toByteArray());
            }
            return Collections.singletonList(arrayList0);
        }

        public Config create(Context context0) {
            return this.configOrNull(context0, this.queryForDefaultFontRequest(context0));
        }

        private FontRequest generateFontRequestFrom(ProviderInfo providerInfo0, PackageManager packageManager0) throws PackageManager.NameNotFoundException {
            return new FontRequest(providerInfo0.authority, providerInfo0.packageName, "emojicompat-emoji-font", this.convertToByteArray(this.mHelper.getSigningSignatures(packageManager0, providerInfo0.packageName)));
        }

        private static DefaultEmojiCompatConfigHelper getHelperForApi() {
            return Build.VERSION.SDK_INT >= 28 ? new DefaultEmojiCompatConfigHelper_API28() : new DefaultEmojiCompatConfigHelper_API19();
        }

        private boolean hasFlagSystem(ProviderInfo providerInfo0) {
            return providerInfo0 != null && providerInfo0.applicationInfo != null && (providerInfo0.applicationInfo.flags & 1) == 1;
        }

        private ProviderInfo queryDefaultInstalledContentProvider(PackageManager packageManager0) {
            Intent intent0 = new Intent("androidx.content.action.LOAD_EMOJI_FONT");
            for(Object object0: this.mHelper.queryIntentContentProviders(packageManager0, intent0, 0)) {
                ProviderInfo providerInfo0 = this.mHelper.getProviderInfo(((ResolveInfo)object0));
                if(this.hasFlagSystem(providerInfo0)) {
                    return providerInfo0;
                }
                if(false) {
                    break;
                }
            }
            return null;
        }

        FontRequest queryForDefaultFontRequest(Context context0) {
            PackageManager packageManager0 = context0.getPackageManager();
            Preconditions.checkNotNull(packageManager0, "Package manager required to locate emoji font provider");
            ProviderInfo providerInfo0 = this.queryDefaultInstalledContentProvider(packageManager0);
            if(providerInfo0 == null) {
                return null;
            }
            try {
                return this.generateFontRequestFrom(providerInfo0, packageManager0);
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", packageManager$NameNotFoundException0);
                return null;
            }
        }
    }

    public static class DefaultEmojiCompatConfigHelper {
        public ProviderInfo getProviderInfo(ResolveInfo resolveInfo0) {
            throw new IllegalStateException("Unable to get provider info prior to API 19");
        }

        public Signature[] getSigningSignatures(PackageManager packageManager0, String s) throws PackageManager.NameNotFoundException {
            return packageManager0.getPackageInfo(s, 0x40).signatures;
        }

        public List queryIntentContentProviders(PackageManager packageManager0, Intent intent0, int v) {
            return Collections.EMPTY_LIST;
        }
    }

    public static class DefaultEmojiCompatConfigHelper_API19 extends DefaultEmojiCompatConfigHelper {
        @Override  // androidx.emoji2.text.DefaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper
        public ProviderInfo getProviderInfo(ResolveInfo resolveInfo0) {
            return resolveInfo0.providerInfo;
        }

        @Override  // androidx.emoji2.text.DefaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper
        public List queryIntentContentProviders(PackageManager packageManager0, Intent intent0, int v) {
            return packageManager0.queryIntentContentProviders(intent0, v);
        }
    }

    public static class DefaultEmojiCompatConfigHelper_API28 extends DefaultEmojiCompatConfigHelper_API19 {
        @Override  // androidx.emoji2.text.DefaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper
        public Signature[] getSigningSignatures(PackageManager packageManager0, String s) throws PackageManager.NameNotFoundException {
            return packageManager0.getPackageInfo(s, 0x40).signatures;
        }
    }

    public static FontRequestEmojiCompatConfig create(Context context0) {
        return (FontRequestEmojiCompatConfig)new DefaultEmojiCompatConfigFactory(null).create(context0);
    }
}

