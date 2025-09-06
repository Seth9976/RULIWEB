package androidx.webkit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TracingConfig {
    public static class Builder {
        private final List mCustomIncludedCategories;
        private int mPredefinedCategories;
        private int mTracingMode;

        public Builder() {
            this.mPredefinedCategories = 0;
            this.mCustomIncludedCategories = new ArrayList();
            this.mTracingMode = 1;
        }

        public Builder addCategories(Collection collection0) {
            this.mCustomIncludedCategories.addAll(collection0);
            return this;
        }

        public Builder addCategories(int[] arr_v) {
            for(int v = 0; v < arr_v.length; ++v) {
                this.mPredefinedCategories |= arr_v[v];
            }
            return this;
        }

        public Builder addCategories(String[] arr_s) {
            List list0 = Arrays.asList(arr_s);
            this.mCustomIncludedCategories.addAll(list0);
            return this;
        }

        public TracingConfig build() {
            return new TracingConfig(this.mPredefinedCategories, this.mCustomIncludedCategories, this.mTracingMode);
        }

        public Builder setTracingMode(int v) {
            this.mTracingMode = v;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PredefinedCategories {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TracingMode {
    }

    public static final int CATEGORIES_ALL = 1;
    public static final int CATEGORIES_ANDROID_WEBVIEW = 2;
    public static final int CATEGORIES_FRAME_VIEWER = 0x40;
    public static final int CATEGORIES_INPUT_LATENCY = 8;
    public static final int CATEGORIES_JAVASCRIPT_AND_RENDERING = 0x20;
    public static final int CATEGORIES_NONE = 0;
    public static final int CATEGORIES_RENDERING = 16;
    public static final int CATEGORIES_WEB_DEVELOPER = 4;
    public static final int RECORD_CONTINUOUSLY = 1;
    public static final int RECORD_UNTIL_FULL;
    private final List mCustomIncludedCategories;
    private final int mPredefinedCategories;
    private final int mTracingMode;

    public TracingConfig(int v, List list0, int v1) {
        ArrayList arrayList0 = new ArrayList();
        this.mCustomIncludedCategories = arrayList0;
        this.mPredefinedCategories = v;
        arrayList0.addAll(list0);
        this.mTracingMode = v1;
    }

    public List getCustomIncludedCategories() {
        return this.mCustomIncludedCategories;
    }

    public int getPredefinedCategories() {
        return this.mPredefinedCategories;
    }

    public int getTracingMode() {
        return this.mTracingMode;
    }
}

