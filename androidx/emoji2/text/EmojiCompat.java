package androidx.emoji2.text;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.collection.ArraySet;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EmojiCompat {
    @Retention(RetentionPolicy.SOURCE)
    public @interface CodepointSequenceMatchResult {
    }

    static final class CompatInternal19 extends CompatInternal {
        private volatile MetadataRepo mMetadataRepo;
        private volatile EmojiProcessor mProcessor;

        CompatInternal19(EmojiCompat emojiCompat0) {
            super(emojiCompat0);
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        String getAssetSignature() {
            String s = this.mMetadataRepo.getMetadataList().sourceSha();
            return s == null ? "" : s;
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        int getEmojiEnd(CharSequence charSequence0, int v) {
            return this.mProcessor.getEmojiEnd(charSequence0, v);
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        public int getEmojiMatch(CharSequence charSequence0, int v) {
            return this.mProcessor.getEmojiMatch(charSequence0, v);
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        int getEmojiStart(CharSequence charSequence0, int v) {
            return this.mProcessor.getEmojiStart(charSequence0, v);
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        boolean hasEmojiGlyph(CharSequence charSequence0) {
            return this.mProcessor.getEmojiMatch(charSequence0) == 1;
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        boolean hasEmojiGlyph(CharSequence charSequence0, int v) {
            return this.mProcessor.getEmojiMatch(charSequence0, v) == 1;
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        void loadMetadata() {
            try {
                androidx.emoji2.text.EmojiCompat.CompatInternal19.1 emojiCompat$CompatInternal19$10 = new MetadataRepoLoaderCallback() {
                    @Override  // androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback
                    public void onFailed(Throwable throwable0) {
                        CompatInternal19.this.mEmojiCompat.onMetadataLoadFailed(throwable0);
                    }

                    @Override  // androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback
                    public void onLoaded(MetadataRepo metadataRepo0) {
                        CompatInternal19.this.onMetadataLoadSuccess(metadataRepo0);
                    }
                };
                this.mEmojiCompat.mMetadataLoader.load(emojiCompat$CompatInternal19$10);
            }
            catch(Throwable throwable0) {
                this.mEmojiCompat.onMetadataLoadFailed(throwable0);
            }
        }

        void onMetadataLoadSuccess(MetadataRepo metadataRepo0) {
            if(metadataRepo0 == null) {
                this.mEmojiCompat.onMetadataLoadFailed(new IllegalArgumentException("metadataRepo cannot be null"));
                return;
            }
            this.mMetadataRepo = metadataRepo0;
            this.mProcessor = new EmojiProcessor(this.mMetadataRepo, this.mEmojiCompat.mSpanFactory, this.mEmojiCompat.mGlyphChecker, this.mEmojiCompat.mUseEmojiAsDefaultStyle, this.mEmojiCompat.mEmojiAsDefaultStyleExceptions, EmojiExclusions.getEmojiExclusions());
            this.mEmojiCompat.onMetadataLoadSuccess();
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        CharSequence process(CharSequence charSequence0, int v, int v1, int v2, boolean z) {
            return this.mProcessor.process(charSequence0, v, v1, v2, z);
        }

        @Override  // androidx.emoji2.text.EmojiCompat$CompatInternal
        void updateEditorInfoAttrs(EditorInfo editorInfo0) {
            editorInfo0.extras.putInt("android.support.text.emoji.emojiCompat_metadataVersion", this.mMetadataRepo.getMetadataVersion());
            editorInfo0.extras.putBoolean("android.support.text.emoji.emojiCompat_replaceAll", this.mEmojiCompat.mReplaceAll);
        }
    }

    static class CompatInternal {
        final EmojiCompat mEmojiCompat;

        CompatInternal(EmojiCompat emojiCompat0) {
            this.mEmojiCompat = emojiCompat0;
        }

        String getAssetSignature() [...] // 潜在的解密器

        int getEmojiEnd(CharSequence charSequence0, int v) {
            return -1;
        }

        public int getEmojiMatch(CharSequence charSequence0, int v) {
            return 0;
        }

        int getEmojiStart(CharSequence charSequence0, int v) {
            return -1;
        }

        boolean hasEmojiGlyph(CharSequence charSequence0) {
            return false;
        }

        boolean hasEmojiGlyph(CharSequence charSequence0, int v) {
            return false;
        }

        void loadMetadata() {
            this.mEmojiCompat.onMetadataLoadSuccess();
        }

        CharSequence process(CharSequence charSequence0, int v, int v1, int v2, boolean z) {
            return charSequence0;
        }

        void updateEditorInfoAttrs(EditorInfo editorInfo0) {
        }
    }

    public static abstract class Config {
        int[] mEmojiAsDefaultStyleExceptions;
        int mEmojiSpanIndicatorColor;
        boolean mEmojiSpanIndicatorEnabled;
        GlyphChecker mGlyphChecker;
        Set mInitCallbacks;
        int mMetadataLoadStrategy;
        final MetadataRepoLoader mMetadataLoader;
        boolean mReplaceAll;
        SpanFactory mSpanFactory;
        boolean mUseEmojiAsDefaultStyle;

        protected Config(MetadataRepoLoader emojiCompat$MetadataRepoLoader0) {
            this.mEmojiSpanIndicatorColor = 0xFF00FF00;
            this.mMetadataLoadStrategy = 0;
            this.mGlyphChecker = new DefaultGlyphChecker();
            Preconditions.checkNotNull(emojiCompat$MetadataRepoLoader0, "metadataLoader cannot be null.");
            this.mMetadataLoader = emojiCompat$MetadataRepoLoader0;
        }

        protected final MetadataRepoLoader getMetadataRepoLoader() {
            return this.mMetadataLoader;
        }

        public Config registerInitCallback(InitCallback emojiCompat$InitCallback0) {
            Preconditions.checkNotNull(emojiCompat$InitCallback0, "initCallback cannot be null");
            if(this.mInitCallbacks == null) {
                this.mInitCallbacks = new ArraySet();
            }
            this.mInitCallbacks.add(emojiCompat$InitCallback0);
            return this;
        }

        public Config setEmojiSpanIndicatorColor(int v) {
            this.mEmojiSpanIndicatorColor = v;
            return this;
        }

        public Config setEmojiSpanIndicatorEnabled(boolean z) {
            this.mEmojiSpanIndicatorEnabled = z;
            return this;
        }

        public Config setGlyphChecker(GlyphChecker emojiCompat$GlyphChecker0) {
            Preconditions.checkNotNull(emojiCompat$GlyphChecker0, "GlyphChecker cannot be null");
            this.mGlyphChecker = emojiCompat$GlyphChecker0;
            return this;
        }

        public Config setMetadataLoadStrategy(int v) {
            this.mMetadataLoadStrategy = v;
            return this;
        }

        public Config setReplaceAll(boolean z) {
            this.mReplaceAll = z;
            return this;
        }

        public Config setSpanFactory(SpanFactory emojiCompat$SpanFactory0) {
            this.mSpanFactory = emojiCompat$SpanFactory0;
            return this;
        }

        public Config setUseEmojiAsDefaultStyle(boolean z) {
            return this.setUseEmojiAsDefaultStyle(z, null);
        }

        public Config setUseEmojiAsDefaultStyle(boolean z, List list0) {
            this.mUseEmojiAsDefaultStyle = z;
            if(z && list0 != null) {
                this.mEmojiAsDefaultStyleExceptions = new int[list0.size()];
                int v = 0;
                for(Object object0: list0) {
                    this.mEmojiAsDefaultStyleExceptions[v] = (int)(((Integer)object0));
                    ++v;
                }
                Arrays.sort(this.mEmojiAsDefaultStyleExceptions);
                return this;
            }
            this.mEmojiAsDefaultStyleExceptions = null;
            return this;
        }

        public Config unregisterInitCallback(InitCallback emojiCompat$InitCallback0) {
            Preconditions.checkNotNull(emojiCompat$InitCallback0, "initCallback cannot be null");
            Set set0 = this.mInitCallbacks;
            if(set0 != null) {
                set0.remove(emojiCompat$InitCallback0);
            }
            return this;
        }
    }

    public static class DefaultSpanFactory implements SpanFactory {
        @Override  // androidx.emoji2.text.EmojiCompat$SpanFactory
        public EmojiSpan createSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
            return new TypefaceEmojiSpan(typefaceEmojiRasterizer0);
        }
    }

    public interface GlyphChecker {
        boolean hasGlyph(CharSequence arg1, int arg2, int arg3, int arg4);
    }

    public static abstract class InitCallback {
        public void onFailed(Throwable throwable0) {
        }

        public void onInitialized() {
        }
    }

    static class ListenerDispatcher implements Runnable {
        private final List mInitCallbacks;
        private final int mLoadState;
        private final Throwable mThrowable;

        ListenerDispatcher(InitCallback emojiCompat$InitCallback0, int v) {
            this(Arrays.asList(new InitCallback[]{((InitCallback)Preconditions.checkNotNull(emojiCompat$InitCallback0, "initCallback cannot be null"))}), v, null);
        }

        ListenerDispatcher(Collection collection0, int v) {
            this(collection0, v, null);
        }

        ListenerDispatcher(Collection collection0, int v, Throwable throwable0) {
            Preconditions.checkNotNull(collection0, "initCallbacks cannot be null");
            this.mInitCallbacks = new ArrayList(collection0);
            this.mLoadState = v;
            this.mThrowable = throwable0;
        }

        @Override
        public void run() {
            int v = this.mInitCallbacks.size();
            int v1 = 0;
            if(this.mLoadState != 1) {
                while(v1 < v) {
                    ((InitCallback)this.mInitCallbacks.get(v1)).onFailed(this.mThrowable);
                    ++v1;
                }
                return;
            }
            while(v1 < v) {
                ((InitCallback)this.mInitCallbacks.get(v1)).onInitialized();
                ++v1;
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoadStrategy {
    }

    public interface MetadataRepoLoader {
        void load(MetadataRepoLoaderCallback arg1);
    }

    public static abstract class MetadataRepoLoaderCallback {
        public abstract void onFailed(Throwable arg1);

        public abstract void onLoaded(MetadataRepo arg1);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReplaceStrategy {
    }

    public interface SpanFactory {
        EmojiSpan createSpan(TypefaceEmojiRasterizer arg1);
    }

    private static final Object CONFIG_LOCK = null;
    public static final String EDITOR_INFO_METAVERSION_KEY = "android.support.text.emoji.emojiCompat_metadataVersion";
    public static final String EDITOR_INFO_REPLACE_ALL_KEY = "android.support.text.emoji.emojiCompat_replaceAll";
    static final int EMOJI_COUNT_UNLIMITED = 0x7FFFFFFF;
    public static final int EMOJI_FALLBACK = 2;
    public static final int EMOJI_SUPPORTED = 1;
    public static final int EMOJI_UNSUPPORTED = 0;
    private static final Object INSTANCE_LOCK = null;
    public static final int LOAD_STATE_DEFAULT = 3;
    public static final int LOAD_STATE_FAILED = 2;
    public static final int LOAD_STATE_LOADING = 0;
    public static final int LOAD_STATE_SUCCEEDED = 1;
    public static final int LOAD_STRATEGY_DEFAULT = 0;
    public static final int LOAD_STRATEGY_MANUAL = 1;
    private static final String NOT_INITIALIZED_ERROR_TEXT = "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK\'s manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.";
    public static final int REPLACE_STRATEGY_ALL = 1;
    public static final int REPLACE_STRATEGY_DEFAULT = 0;
    public static final int REPLACE_STRATEGY_NON_EXISTENT = 2;
    final int[] mEmojiAsDefaultStyleExceptions;
    private final int mEmojiSpanIndicatorColor;
    private final boolean mEmojiSpanIndicatorEnabled;
    private final GlyphChecker mGlyphChecker;
    private final CompatInternal mHelper;
    private final Set mInitCallbacks;
    private final ReadWriteLock mInitLock;
    private volatile int mLoadState;
    private final Handler mMainHandler;
    private final int mMetadataLoadStrategy;
    final MetadataRepoLoader mMetadataLoader;
    final boolean mReplaceAll;
    private final SpanFactory mSpanFactory;
    final boolean mUseEmojiAsDefaultStyle;
    private static volatile boolean sHasDoneDefaultConfigLookup;
    private static volatile EmojiCompat sInstance;

    static {
        EmojiCompat.INSTANCE_LOCK = new Object();
        EmojiCompat.CONFIG_LOCK = new Object();
    }

    private EmojiCompat(Config emojiCompat$Config0) {
        this.mInitLock = new ReentrantReadWriteLock();
        this.mLoadState = 3;
        this.mReplaceAll = emojiCompat$Config0.mReplaceAll;
        this.mUseEmojiAsDefaultStyle = emojiCompat$Config0.mUseEmojiAsDefaultStyle;
        this.mEmojiAsDefaultStyleExceptions = emojiCompat$Config0.mEmojiAsDefaultStyleExceptions;
        this.mEmojiSpanIndicatorEnabled = emojiCompat$Config0.mEmojiSpanIndicatorEnabled;
        this.mEmojiSpanIndicatorColor = emojiCompat$Config0.mEmojiSpanIndicatorColor;
        this.mMetadataLoader = emojiCompat$Config0.mMetadataLoader;
        this.mMetadataLoadStrategy = emojiCompat$Config0.mMetadataLoadStrategy;
        this.mGlyphChecker = emojiCompat$Config0.mGlyphChecker;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        ArraySet arraySet0 = new ArraySet();
        this.mInitCallbacks = arraySet0;
        SpanFactory emojiCompat$SpanFactory0 = emojiCompat$Config0.mSpanFactory;
        if(emojiCompat$SpanFactory0 == null) {
            emojiCompat$SpanFactory0 = new DefaultSpanFactory();
        }
        this.mSpanFactory = emojiCompat$SpanFactory0;
        if(emojiCompat$Config0.mInitCallbacks != null && !emojiCompat$Config0.mInitCallbacks.isEmpty()) {
            arraySet0.addAll(emojiCompat$Config0.mInitCallbacks);
        }
        this.mHelper = new CompatInternal19(this);
        this.loadMetadata();
    }

    public static EmojiCompat get() {
        synchronized(EmojiCompat.INSTANCE_LOCK) {
            EmojiCompat emojiCompat0 = EmojiCompat.sInstance;
            Preconditions.checkState(emojiCompat0 != null, "EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK\'s manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
            return emojiCompat0;
        }
    }

    public String getAssetSignature() {
        Preconditions.checkState(this.isInitialized(), "Not initialized yet");
        return "";
    }

    public int getEmojiEnd(CharSequence charSequence0, int v) {
        return this.mHelper.getEmojiEnd(charSequence0, v);
    }

    public int getEmojiMatch(CharSequence charSequence0, int v) {
        Preconditions.checkState(this.isInitialized(), "Not initialized yet");
        Preconditions.checkNotNull(charSequence0, "sequence cannot be null");
        return this.mHelper.getEmojiMatch(charSequence0, v);
    }

    public int getEmojiSpanIndicatorColor() {
        return this.mEmojiSpanIndicatorColor;
    }

    public int getEmojiStart(CharSequence charSequence0, int v) {
        return this.mHelper.getEmojiStart(charSequence0, v);
    }

    public int getLoadState() {
        this.mInitLock.readLock().lock();
        int v = this.mLoadState;
        this.mInitLock.readLock().unlock();
        return v;
    }

    public static boolean handleDeleteSurroundingText(InputConnection inputConnection0, Editable editable0, int v, int v1, boolean z) {
        return EmojiProcessor.handleDeleteSurroundingText(inputConnection0, editable0, v, v1, z);
    }

    public static boolean handleOnKeyDown(Editable editable0, int v, KeyEvent keyEvent0) {
        return EmojiProcessor.handleOnKeyDown(editable0, v, keyEvent0);
    }

    @Deprecated
    public boolean hasEmojiGlyph(CharSequence charSequence0) {
        Preconditions.checkState(this.isInitialized(), "Not initialized yet");
        Preconditions.checkNotNull(charSequence0, "sequence cannot be null");
        return this.mHelper.hasEmojiGlyph(charSequence0);
    }

    @Deprecated
    public boolean hasEmojiGlyph(CharSequence charSequence0, int v) {
        Preconditions.checkState(this.isInitialized(), "Not initialized yet");
        Preconditions.checkNotNull(charSequence0, "sequence cannot be null");
        return this.mHelper.hasEmojiGlyph(charSequence0, v);
    }

    public static EmojiCompat init(Context context0) {
        return EmojiCompat.init(context0, null);
    }

    public static EmojiCompat init(Context context0, DefaultEmojiCompatConfigFactory defaultEmojiCompatConfig$DefaultEmojiCompatConfigFactory0) {
        if(EmojiCompat.sHasDoneDefaultConfigLookup) {
            return EmojiCompat.sInstance;
        }
        if(defaultEmojiCompatConfig$DefaultEmojiCompatConfigFactory0 == null) {
            defaultEmojiCompatConfig$DefaultEmojiCompatConfigFactory0 = new DefaultEmojiCompatConfigFactory(null);
        }
        Config emojiCompat$Config0 = defaultEmojiCompatConfig$DefaultEmojiCompatConfigFactory0.create(context0);
        synchronized(EmojiCompat.CONFIG_LOCK) {
            if(!EmojiCompat.sHasDoneDefaultConfigLookup) {
                if(emojiCompat$Config0 != null) {
                    EmojiCompat.init(emojiCompat$Config0);
                }
                EmojiCompat.sHasDoneDefaultConfigLookup = true;
            }
            return EmojiCompat.sInstance;
        }
    }

    public static EmojiCompat init(Config emojiCompat$Config0) {
        EmojiCompat emojiCompat0 = EmojiCompat.sInstance;
        if(emojiCompat0 == null) {
            synchronized(EmojiCompat.INSTANCE_LOCK) {
                EmojiCompat emojiCompat1 = EmojiCompat.sInstance;
                if(emojiCompat1 == null) {
                    emojiCompat1 = new EmojiCompat(emojiCompat$Config0);
                    EmojiCompat.sInstance = emojiCompat1;
                }
                return emojiCompat1;
            }
        }
        return emojiCompat0;
    }

    public static boolean isConfigured() [...] // 潜在的解密器

    public boolean isEmojiSpanIndicatorEnabled() {
        return this.mEmojiSpanIndicatorEnabled;
    }

    private boolean isInitialized() {
        return this.getLoadState() == 1;
    }

    public void load() {
        Preconditions.checkState(this.mMetadataLoadStrategy == 1, "Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
        if(this.isInitialized()) {
            return;
        }
        this.mInitLock.writeLock().lock();
        if(this.mLoadState == 0) {
            this.mInitLock.writeLock().unlock();
            return;
        }
        this.mLoadState = 0;
        this.mInitLock.writeLock().unlock();
        this.mHelper.loadMetadata();
    }

    private void loadMetadata() {
        this.mInitLock.writeLock().lock();
        if(this.mMetadataLoadStrategy == 0) {
            this.mLoadState = 0;
        }
        this.mInitLock.writeLock().unlock();
        if(this.getLoadState() == 0) {
            this.mHelper.loadMetadata();
        }
    }

    void onMetadataLoadFailed(Throwable throwable0) {
        ArrayList arrayList0 = new ArrayList();
        this.mInitLock.writeLock().lock();
        try {
            this.mLoadState = 2;
            arrayList0.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
        }
        finally {
            this.mInitLock.writeLock().unlock();
        }
        ListenerDispatcher emojiCompat$ListenerDispatcher0 = new ListenerDispatcher(arrayList0, this.mLoadState, throwable0);
        this.mMainHandler.post(emojiCompat$ListenerDispatcher0);
    }

    void onMetadataLoadSuccess() {
        ArrayList arrayList0 = new ArrayList();
        this.mInitLock.writeLock().lock();
        try {
            this.mLoadState = 1;
            arrayList0.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
        }
        finally {
            this.mInitLock.writeLock().unlock();
        }
        ListenerDispatcher emojiCompat$ListenerDispatcher0 = new ListenerDispatcher(arrayList0, this.mLoadState);
        this.mMainHandler.post(emojiCompat$ListenerDispatcher0);
    }

    public CharSequence process(CharSequence charSequence0) {
        return charSequence0 == null ? this.process(null, 0, 0) : this.process(charSequence0, 0, charSequence0.length());
    }

    public CharSequence process(CharSequence charSequence0, int v, int v1) {
        return this.process(charSequence0, v, v1, 0x7FFFFFFF);
    }

    public CharSequence process(CharSequence charSequence0, int v, int v1, int v2) {
        return this.process(charSequence0, v, v1, v2, 0);
    }

    public CharSequence process(CharSequence charSequence0, int v, int v1, int v2, int v3) {
        Preconditions.checkState(this.isInitialized(), "Not initialized yet");
        Preconditions.checkArgumentNonnegative(v, "start cannot be negative");
        Preconditions.checkArgumentNonnegative(v1, "end cannot be negative");
        Preconditions.checkArgumentNonnegative(v2, "maxEmojiCount cannot be negative");
        Preconditions.checkArgument(v <= v1, "start should be <= than end");
        if(charSequence0 == null) {
            return null;
        }
        Preconditions.checkArgument(v <= charSequence0.length(), "start should be < than charSequence length");
        Preconditions.checkArgument(v1 <= charSequence0.length(), "end should be < than charSequence length");
        if(charSequence0.length() != 0 && v != v1) {
            switch(v3) {
                case 1: {
                    return this.mHelper.process(charSequence0, v, v1, v2, true);
                }
                case 2: {
                    return this.mHelper.process(charSequence0, v, v1, v2, false);
                }
                default: {
                    return this.mHelper.process(charSequence0, v, v1, v2, this.mReplaceAll);
                }
            }
        }
        return charSequence0;
    }

    public void registerInitCallback(InitCallback emojiCompat$InitCallback0) {
        Preconditions.checkNotNull(emojiCompat$InitCallback0, "initCallback cannot be null");
        this.mInitLock.writeLock().lock();
        try {
            if(this.mLoadState == 1 || this.mLoadState == 2) {
                ListenerDispatcher emojiCompat$ListenerDispatcher0 = new ListenerDispatcher(emojiCompat$InitCallback0, this.mLoadState);
                this.mMainHandler.post(emojiCompat$ListenerDispatcher0);
            }
            else {
                this.mInitCallbacks.add(emojiCompat$InitCallback0);
            }
        }
        finally {
            this.mInitLock.writeLock().unlock();
        }
    }

    public static EmojiCompat reset(Config emojiCompat$Config0) {
        synchronized(EmojiCompat.INSTANCE_LOCK) {
            EmojiCompat emojiCompat0 = new EmojiCompat(emojiCompat$Config0);
            EmojiCompat.sInstance = emojiCompat0;
            return emojiCompat0;
        }
    }

    public static EmojiCompat reset(EmojiCompat emojiCompat0) {
        synchronized(EmojiCompat.INSTANCE_LOCK) {
            EmojiCompat.sInstance = emojiCompat0;
        }
        return EmojiCompat.sInstance;
    }

    public static void skipDefaultConfigurationLookup(boolean z) {
        synchronized(EmojiCompat.CONFIG_LOCK) {
            EmojiCompat.sHasDoneDefaultConfigLookup = z;
        }
    }

    public void unregisterInitCallback(InitCallback emojiCompat$InitCallback0) {
        Preconditions.checkNotNull(emojiCompat$InitCallback0, "initCallback cannot be null");
        this.mInitLock.writeLock().lock();
        try {
            this.mInitCallbacks.remove(emojiCompat$InitCallback0);
        }
        finally {
            this.mInitLock.writeLock().unlock();
        }
    }

    public void updateEditorInfo(EditorInfo editorInfo0) {
        if(this.isInitialized() && editorInfo0 != null) {
            if(editorInfo0.extras == null) {
                editorInfo0.extras = new Bundle();
            }
            this.mHelper.updateEditorInfoAttrs(editorInfo0);
        }
    }
}

