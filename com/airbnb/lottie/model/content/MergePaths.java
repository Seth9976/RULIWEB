package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.MergePathsContent;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.Logger;

public class MergePaths implements ContentModel {
    public static enum MergePathsMode {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static MergePathsMode forId(int v) {
            switch(v) {
                case 1: {
                    return MergePathsMode.MERGE;
                }
                case 2: {
                    return MergePathsMode.ADD;
                }
                case 3: {
                    return MergePathsMode.SUBTRACT;
                }
                case 4: {
                    return MergePathsMode.INTERSECT;
                }
                case 5: {
                    return MergePathsMode.EXCLUDE_INTERSECTIONS;
                }
                default: {
                    return MergePathsMode.MERGE;
                }
            }
        }
    }

    private final boolean hidden;
    private final MergePathsMode mode;
    private final String name;

    public MergePaths(String s, MergePathsMode mergePaths$MergePathsMode0, boolean z) {
        this.name = s;
        this.mode = mergePaths$MergePathsMode0;
        this.hidden = z;
    }

    public MergePathsMode getMode() {
        return this.mode;
    }

    public String getName() {
        return this.name;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        if(!lottieDrawable0.enableMergePathsForKitKatAndAbove()) {
            Logger.warning("Animation contains merge paths but they are disabled.");
            return null;
        }
        return new MergePathsContent(this);
    }

    @Override
    public String toString() {
        return "MergePaths{mode=" + this.mode + '}';
    }
}

