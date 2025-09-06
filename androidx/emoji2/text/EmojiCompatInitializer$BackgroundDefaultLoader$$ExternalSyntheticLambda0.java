package androidx.emoji2.text;

import java.util.concurrent.ThreadPoolExecutor;

public final class EmojiCompatInitializer.BackgroundDefaultLoader..ExternalSyntheticLambda0 implements Runnable {
    public final BackgroundDefaultLoader f$0;
    public final MetadataRepoLoaderCallback f$1;
    public final ThreadPoolExecutor f$2;

    public EmojiCompatInitializer.BackgroundDefaultLoader..ExternalSyntheticLambda0(BackgroundDefaultLoader emojiCompatInitializer$BackgroundDefaultLoader0, MetadataRepoLoaderCallback emojiCompat$MetadataRepoLoaderCallback0, ThreadPoolExecutor threadPoolExecutor0) {
        this.f$0 = emojiCompatInitializer$BackgroundDefaultLoader0;
        this.f$1 = emojiCompat$MetadataRepoLoaderCallback0;
        this.f$2 = threadPoolExecutor0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$load$0$androidx-emoji2-text-EmojiCompatInitializer$BackgroundDefaultLoader(this.f$1, this.f$2);
    }
}

