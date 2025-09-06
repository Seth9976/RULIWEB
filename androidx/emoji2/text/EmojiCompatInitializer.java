package androidx.emoji2.text;

import android.content.Context;
import androidx.core.os.TraceCompat;
import androidx.lifecycle.DefaultLifecycleObserver.-CC;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class EmojiCompatInitializer implements Initializer {
    static class BackgroundDefaultConfig extends Config {
        protected BackgroundDefaultConfig(Context context0) {
            super(new BackgroundDefaultLoader(context0));
            this.setMetadataLoadStrategy(1);
        }
    }

    static class BackgroundDefaultLoader implements MetadataRepoLoader {
        private final Context mContext;

        BackgroundDefaultLoader(Context context0) {
            this.mContext = context0.getApplicationContext();
        }

        void doLoad(MetadataRepoLoaderCallback emojiCompat$MetadataRepoLoaderCallback0, ThreadPoolExecutor threadPoolExecutor0) {
            try {
                FontRequestEmojiCompatConfig fontRequestEmojiCompatConfig0 = DefaultEmojiCompatConfig.create(this.mContext);
                if(fontRequestEmojiCompatConfig0 == null) {
                    throw new RuntimeException("EmojiCompat font provider not available on this device.");
                }
                fontRequestEmojiCompatConfig0.setLoadingExecutor(threadPoolExecutor0);
                fontRequestEmojiCompatConfig0.getMetadataRepoLoader().load(new MetadataRepoLoaderCallback() {
                    @Override  // androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback
                    public void onFailed(Throwable throwable0) {
                        try {
                            emojiCompat$MetadataRepoLoaderCallback0.onFailed(throwable0);
                        }
                        finally {
                            threadPoolExecutor0.shutdown();
                        }
                    }

                    @Override  // androidx.emoji2.text.EmojiCompat$MetadataRepoLoaderCallback
                    public void onLoaded(MetadataRepo metadataRepo0) {
                        try {
                            emojiCompat$MetadataRepoLoaderCallback0.onLoaded(metadataRepo0);
                        }
                        finally {
                            threadPoolExecutor0.shutdown();
                        }
                    }
                });
            }
            catch(Throwable throwable0) {
                emojiCompat$MetadataRepoLoaderCallback0.onFailed(throwable0);
                threadPoolExecutor0.shutdown();
            }
        }

        // 检测为 Lambda 实现
        void lambda$load$0$androidx-emoji2-text-EmojiCompatInitializer$BackgroundDefaultLoader(MetadataRepoLoaderCallback emojiCompat$MetadataRepoLoaderCallback0, ThreadPoolExecutor threadPoolExecutor0) [...]

        @Override  // androidx.emoji2.text.EmojiCompat$MetadataRepoLoader
        public void load(MetadataRepoLoaderCallback emojiCompat$MetadataRepoLoaderCallback0) {
            ThreadPoolExecutor threadPoolExecutor0 = ConcurrencyHelpers.createBackgroundPriorityExecutor("EmojiCompatInitializer");
            threadPoolExecutor0.execute(() -> this.doLoad(emojiCompat$MetadataRepoLoaderCallback0, threadPoolExecutor0));
        }
    }

    static class LoadEmojiCompatRunnable implements Runnable {
        @Override
        public void run() {
            try {
                TraceCompat.beginSection("EmojiCompat.EmojiCompatInitializer.run");
            }
            finally {
                TraceCompat.endSection();
            }
        }
    }

    private static final long STARTUP_THREAD_CREATION_DELAY_MS = 500L;
    private static final String S_INITIALIZER_THREAD_NAME = "EmojiCompatInitializer";

    public Boolean create(Context context0) {
        EmojiCompat.init(new BackgroundDefaultConfig(context0));
        this.delayUntilFirstResume(context0);
        return true;
    }

    @Override  // androidx.startup.Initializer
    public Object create(Context context0) {
        return this.create(context0);
    }

    void delayUntilFirstResume(Context context0) {
        Lifecycle lifecycle0 = ((LifecycleOwner)AppInitializer.getInstance(context0).initializeComponent(ProcessLifecycleInitializer.class)).getLifecycle();
        lifecycle0.addObserver(new DefaultLifecycleObserver() {
            @Override  // androidx.lifecycle.DefaultLifecycleObserver
            public void onCreate(LifecycleOwner lifecycleOwner0) {
                DefaultLifecycleObserver.-CC.$default$onCreate(this, lifecycleOwner0);
            }

            @Override  // androidx.lifecycle.DefaultLifecycleObserver
            public void onDestroy(LifecycleOwner lifecycleOwner0) {
                DefaultLifecycleObserver.-CC.$default$onDestroy(this, lifecycleOwner0);
            }

            @Override  // androidx.lifecycle.DefaultLifecycleObserver
            public void onPause(LifecycleOwner lifecycleOwner0) {
                DefaultLifecycleObserver.-CC.$default$onPause(this, lifecycleOwner0);
            }

            @Override  // androidx.lifecycle.DefaultLifecycleObserver
            public void onResume(LifecycleOwner lifecycleOwner0) {
                EmojiCompatInitializer.this.loadEmojiCompatAfterDelay();
                lifecycle0.removeObserver(this);
            }

            @Override  // androidx.lifecycle.DefaultLifecycleObserver
            public void onStart(LifecycleOwner lifecycleOwner0) {
                DefaultLifecycleObserver.-CC.$default$onStart(this, lifecycleOwner0);
            }

            @Override  // androidx.lifecycle.DefaultLifecycleObserver
            public void onStop(LifecycleOwner lifecycleOwner0) {
                DefaultLifecycleObserver.-CC.$default$onStop(this, lifecycleOwner0);
            }
        });
    }

    @Override  // androidx.startup.Initializer
    public List dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    void loadEmojiCompatAfterDelay() {
        ConcurrencyHelpers.mainHandlerAsync().postDelayed(new LoadEmojiCompatRunnable(), 500L);
    }
}

