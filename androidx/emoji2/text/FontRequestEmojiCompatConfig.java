package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import androidx.core.graphics.TypefaceCompatUtil;
import androidx.core.os.TraceCompat;
import androidx.core.provider.FontRequest;
import androidx.core.provider.FontsContractCompat.FontFamilyResult;
import androidx.core.provider.FontsContractCompat.FontInfo;
import androidx.core.provider.FontsContractCompat;
import androidx.core.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class FontRequestEmojiCompatConfig extends Config {
    public static class ExponentialBackoffRetryPolicy extends RetryPolicy {
        private long mRetryOrigin;
        private final long mTotalMs;

        public ExponentialBackoffRetryPolicy(long v) {
            this.mTotalMs = v;
        }

        @Override  // androidx.emoji2.text.FontRequestEmojiCompatConfig$RetryPolicy
        public long getRetryDelay() {
            if(this.mRetryOrigin == 0L) {
                this.mRetryOrigin = SystemClock.uptimeMillis();
                return 0L;
            }
            long v = SystemClock.uptimeMillis() - this.mRetryOrigin;
            return v <= this.mTotalMs ? Math.min(Math.max(v, 1000L), this.mTotalMs - v) : -1L;
        }
    }

    public static class FontProviderHelper {
        public Typeface buildTypeface(Context context0, FontInfo fontsContractCompat$FontInfo0) throws PackageManager.NameNotFoundException {
            return FontsContractCompat.buildTypeface(context0, null, new FontInfo[]{fontsContractCompat$FontInfo0});
        }

        public FontFamilyResult fetchFonts(Context context0, FontRequest fontRequest0) throws PackageManager.NameNotFoundException {
            return FontsContractCompat.fetchFonts(context0, null, fontRequest0);
        }

        public void registerObserver(Context context0, Uri uri0, ContentObserver contentObserver0) {
            context0.getContentResolver().registerContentObserver(uri0, false, contentObserver0);
        }

        public void unregisterObserver(Context context0, ContentObserver contentObserver0) {
            context0.getContentResolver().unregisterContentObserver(contentObserver0);
        }
    }

    static class FontRequestMetadataLoader implements MetadataRepoLoader {
        private static final String S_TRACE_BUILD_TYPEFACE = "EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface";
        MetadataRepoLoaderCallback mCallback;
        private final Context mContext;
        private Executor mExecutor;
        private final FontProviderHelper mFontProviderHelper;
        private final Object mLock;
        private Handler mMainHandler;
        private Runnable mMainHandlerLoadCallback;
        private ThreadPoolExecutor mMyThreadPoolExecutor;
        private ContentObserver mObserver;
        private final FontRequest mRequest;
        private RetryPolicy mRetryPolicy;

        FontRequestMetadataLoader(Context context0, FontRequest fontRequest0, FontProviderHelper fontRequestEmojiCompatConfig$FontProviderHelper0) {
            this.mLock = new Object();
            Preconditions.checkNotNull(context0, "Context cannot be null");
            Preconditions.checkNotNull(fontRequest0, "FontRequest cannot be null");
            this.mContext = context0.getApplicationContext();
            this.mRequest = fontRequest0;
            this.mFontProviderHelper = fontRequestEmojiCompatConfig$FontProviderHelper0;
        }

        private void cleanUp() {
            synchronized(this.mLock) {
                this.mCallback = null;
                ContentObserver contentObserver0 = this.mObserver;
                if(contentObserver0 != null) {
                    this.mFontProviderHelper.unregisterObserver(this.mContext, contentObserver0);
                    this.mObserver = null;
                }
                Handler handler0 = this.mMainHandler;
                if(handler0 != null) {
                    handler0.removeCallbacks(this.mMainHandlerLoadCallback);
                }
                this.mMainHandler = null;
                ThreadPoolExecutor threadPoolExecutor0 = this.mMyThreadPoolExecutor;
                if(threadPoolExecutor0 != null) {
                    threadPoolExecutor0.shutdown();
                }
                this.mExecutor = null;
                this.mMyThreadPoolExecutor = null;
            }
        }

        // 检测为 Lambda 实现
        void createMetadata() [...]

        @Override  // androidx.emoji2.text.EmojiCompat$MetadataRepoLoader
        public void load(MetadataRepoLoaderCallback emojiCompat$MetadataRepoLoaderCallback0) {
            Preconditions.checkNotNull(emojiCompat$MetadataRepoLoaderCallback0, "LoaderCallback cannot be null");
            synchronized(this.mLock) {
                this.mCallback = emojiCompat$MetadataRepoLoaderCallback0;
            }
            this.loadInternal();
        }

        // 检测为 Lambda 实现
        void loadInternal() [...]

        private FontInfo retrieveFontInfo() {
            FontFamilyResult fontsContractCompat$FontFamilyResult0;
            try {
                fontsContractCompat$FontFamilyResult0 = this.mFontProviderHelper.fetchFonts(this.mContext, this.mRequest);
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                throw new RuntimeException("provider not found", packageManager$NameNotFoundException0);
            }
            if(fontsContractCompat$FontFamilyResult0.getStatusCode() != 0) {
                throw new RuntimeException("fetchFonts failed (" + fontsContractCompat$FontFamilyResult0.getStatusCode() + ")");
            }
            FontInfo[] arr_fontsContractCompat$FontInfo = fontsContractCompat$FontFamilyResult0.getFonts();
            if(arr_fontsContractCompat$FontInfo == null || arr_fontsContractCompat$FontInfo.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return arr_fontsContractCompat$FontInfo[0];
        }

        private void scheduleRetry(Uri uri0, long v) {
            synchronized(this.mLock) {
                Handler handler0 = this.mMainHandler;
                if(handler0 == null) {
                    handler0 = ConcurrencyHelpers.mainHandlerAsync();
                    this.mMainHandler = handler0;
                }
                if(this.mObserver == null) {
                    androidx.emoji2.text.FontRequestEmojiCompatConfig.FontRequestMetadataLoader.1 fontRequestEmojiCompatConfig$FontRequestMetadataLoader$10 = new ContentObserver(handler0) {
                        @Override  // android.database.ContentObserver
                        public void onChange(boolean z, Uri uri0) {
                            FontRequestMetadataLoader.this.loadInternal();
                        }
                    };
                    this.mObserver = fontRequestEmojiCompatConfig$FontRequestMetadataLoader$10;
                    this.mFontProviderHelper.registerObserver(this.mContext, uri0, fontRequestEmojiCompatConfig$FontRequestMetadataLoader$10);
                }
                if(this.mMainHandlerLoadCallback == null) {
                    this.mMainHandlerLoadCallback = () -> synchronized(this.mLock) {
                        if(this.mCallback == null) {
                            return;
                        }
                        if(this.mExecutor == null) {
                            ThreadPoolExecutor threadPoolExecutor0 = ConcurrencyHelpers.createBackgroundPriorityExecutor("emojiCompat");
                            this.mMyThreadPoolExecutor = threadPoolExecutor0;
                            this.mExecutor = threadPoolExecutor0;
                        }
                        this.mExecutor.execute(() -> {
                            synchronized(this.mLock) {
                                if(this.mCallback == null) {
                                    return;
                                }
                            }
                            try {
                                FontInfo fontsContractCompat$FontInfo0 = this.retrieveFontInfo();
                                int v = fontsContractCompat$FontInfo0.getResultCode();
                                if(v == 2) {
                                    synchronized(this.mLock) {
                                        RetryPolicy fontRequestEmojiCompatConfig$RetryPolicy0 = this.mRetryPolicy;
                                        if(fontRequestEmojiCompatConfig$RetryPolicy0 != null) {
                                            long v2 = fontRequestEmojiCompatConfig$RetryPolicy0.getRetryDelay();
                                            if(v2 >= 0L) {
                                                this.scheduleRetry(fontsContractCompat$FontInfo0.getUri(), v2);
                                                return;
                                            }
                                        }
                                    }
                                }
                                if(v != 0) {
                                    throw new RuntimeException("fetchFonts result is not OK. (" + v + ")");
                                }
                                try {
                                    TraceCompat.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                    Typeface typeface0 = this.mFontProviderHelper.buildTypeface(this.mContext, fontsContractCompat$FontInfo0);
                                    ByteBuffer byteBuffer0 = TypefaceCompatUtil.mmap(this.mContext, null, fontsContractCompat$FontInfo0.getUri());
                                    if(byteBuffer0 != null && typeface0 != null) {
                                        MetadataRepo metadataRepo0 = MetadataRepo.create(typeface0, byteBuffer0);
                                        synchronized(this.mLock) {
                                            MetadataRepoLoaderCallback emojiCompat$MetadataRepoLoaderCallback0 = this.mCallback;
                                            if(emojiCompat$MetadataRepoLoaderCallback0 != null) {
                                                emojiCompat$MetadataRepoLoaderCallback0.onLoaded(metadataRepo0);
                                            }
                                        }
                                        this.cleanUp();
                                        return;
                                    }
                                }
                                finally {
                                    TraceCompat.endSection();
                                }
                                throw new RuntimeException("Unable to open file.");
                            }
                            catch(Throwable throwable0) {
                                synchronized(this.mLock) {
                                    MetadataRepoLoaderCallback emojiCompat$MetadataRepoLoaderCallback1 = this.mCallback;
                                    if(emojiCompat$MetadataRepoLoaderCallback1 != null) {
                                        emojiCompat$MetadataRepoLoaderCallback1.onFailed(throwable0);
                                    }
                                }
                                this.cleanUp();
                            }
                        });
                    };
                }
                handler0.postDelayed(this.mMainHandlerLoadCallback, v);
            }
        }

        public void setExecutor(Executor executor0) {
            synchronized(this.mLock) {
                this.mExecutor = executor0;
            }
        }

        public void setRetryPolicy(RetryPolicy fontRequestEmojiCompatConfig$RetryPolicy0) {
            synchronized(this.mLock) {
                this.mRetryPolicy = fontRequestEmojiCompatConfig$RetryPolicy0;
            }
        }
    }

    public static abstract class RetryPolicy {
        public abstract long getRetryDelay();
    }

    private static final FontProviderHelper DEFAULT_FONTS_CONTRACT;

    static {
        FontRequestEmojiCompatConfig.DEFAULT_FONTS_CONTRACT = new FontProviderHelper();
    }

    public FontRequestEmojiCompatConfig(Context context0, FontRequest fontRequest0) {
        super(new FontRequestMetadataLoader(context0, fontRequest0, FontRequestEmojiCompatConfig.DEFAULT_FONTS_CONTRACT));
    }

    public FontRequestEmojiCompatConfig(Context context0, FontRequest fontRequest0, FontProviderHelper fontRequestEmojiCompatConfig$FontProviderHelper0) {
        super(new FontRequestMetadataLoader(context0, fontRequest0, fontRequestEmojiCompatConfig$FontProviderHelper0));
    }

    @Deprecated
    public FontRequestEmojiCompatConfig setHandler(Handler handler0) {
        if(handler0 == null) {
            return this;
        }
        this.setLoadingExecutor(ConcurrencyHelpers.convertHandlerToExecutor(handler0));
        return this;
    }

    public FontRequestEmojiCompatConfig setLoadingExecutor(Executor executor0) {
        ((FontRequestMetadataLoader)this.getMetadataRepoLoader()).setExecutor(executor0);
        return this;
    }

    public FontRequestEmojiCompatConfig setRetryPolicy(RetryPolicy fontRequestEmojiCompatConfig$RetryPolicy0) {
        ((FontRequestMetadataLoader)this.getMetadataRepoLoader()).setRetryPolicy(fontRequestEmojiCompatConfig$RetryPolicy0);
        return this;
    }
}

