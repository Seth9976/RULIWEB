package com.airbnb.lottie.animation.content;

import android.graphics.Path.Op;
import android.graphics.Path;
import com.airbnb.lottie.model.content.MergePaths.MergePathsMode;
import com.airbnb.lottie.model.content.MergePaths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MergePathsContent implements GreedyContent, PathContent {
    private final Path firstPath;
    private final MergePaths mergePaths;
    private final String name;
    private final Path path;
    private final List pathContents;
    private final Path remainderPath;

    public MergePathsContent(MergePaths mergePaths0) {
        this.firstPath = new Path();
        this.remainderPath = new Path();
        this.path = new Path();
        this.pathContents = new ArrayList();
        this.name = mergePaths0.getName();
        this.mergePaths = mergePaths0;
    }

    @Override  // com.airbnb.lottie.animation.content.GreedyContent
    public void absorbContent(ListIterator listIterator0) {
        while(listIterator0.hasPrevious() && listIterator0.previous() != this) {
        }
        while(listIterator0.hasPrevious()) {
            Content content0 = (Content)listIterator0.previous();
            if(content0 instanceof PathContent) {
                this.pathContents.add(((PathContent)content0));
                listIterator0.remove();
            }
        }
    }

    private void addPaths() {
        for(int v = 0; v < this.pathContents.size(); ++v) {
            Path path0 = ((PathContent)this.pathContents.get(v)).getPath();
            this.path.addPath(path0);
        }
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public String getName() {
        return this.name;
    }

    @Override  // com.airbnb.lottie.animation.content.PathContent
    public Path getPath() {
        this.path.reset();
        if(this.mergePaths.isHidden()) {
            return this.path;
        }
        switch(com.airbnb.lottie.animation.content.MergePathsContent.1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[this.mergePaths.getMode().ordinal()]) {
            case 1: {
                this.addPaths();
                return this.path;
            }
            case 2: {
                this.opFirstPathWithRest(Path.Op.UNION);
                return this.path;
            }
            case 3: {
                this.opFirstPathWithRest(Path.Op.REVERSE_DIFFERENCE);
                return this.path;
            }
            case 4: {
                this.opFirstPathWithRest(Path.Op.INTERSECT);
                return this.path;
            }
            case 5: {
                this.opFirstPathWithRest(Path.Op.XOR);
                return this.path;
            }
            default: {
                return this.path;
            }
        }
    }

    private void opFirstPathWithRest(Path.Op path$Op0) {
        this.remainderPath.reset();
        this.firstPath.reset();
        for(int v = this.pathContents.size() - 1; v >= 1; --v) {
            PathContent pathContent0 = (PathContent)this.pathContents.get(v);
            if(pathContent0 instanceof ContentGroup) {
                ContentGroup contentGroup0 = (ContentGroup)pathContent0;
                List list0 = contentGroup0.getPathList();
                for(int v1 = list0.size() - 1; v1 >= 0; --v1) {
                    Path path0 = ((PathContent)list0.get(v1)).getPath();
                    path0.transform(contentGroup0.getTransformationMatrix());
                    this.remainderPath.addPath(path0);
                }
            }
            else {
                Path path1 = pathContent0.getPath();
                this.remainderPath.addPath(path1);
            }
        }
        PathContent pathContent1 = (PathContent)this.pathContents.get(0);
        if(pathContent1 instanceof ContentGroup) {
            List list1 = ((ContentGroup)pathContent1).getPathList();
            for(int v2 = 0; v2 < list1.size(); ++v2) {
                Path path2 = ((PathContent)list1.get(v2)).getPath();
                path2.transform(((ContentGroup)pathContent1).getTransformationMatrix());
                this.firstPath.addPath(path2);
            }
        }
        else {
            Path path3 = pathContent1.getPath();
            this.firstPath.set(path3);
        }
        this.path.op(this.firstPath, this.remainderPath, path$Op0);
    }

    @Override  // com.airbnb.lottie.animation.content.Content
    public void setContents(List list0, List list1) {
        for(int v = 0; v < this.pathContents.size(); ++v) {
            ((PathContent)this.pathContents.get(v)).setContents(list0, list1);
        }
    }

    class com.airbnb.lottie.animation.content.MergePathsContent.1 {
        static final int[] $SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode;

        static {
            int[] arr_v = new int[MergePathsMode.values().length];
            com.airbnb.lottie.animation.content.MergePathsContent.1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode = arr_v;
            try {
                arr_v[MergePathsMode.MERGE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.animation.content.MergePathsContent.1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePathsMode.ADD.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.animation.content.MergePathsContent.1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePathsMode.SUBTRACT.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.animation.content.MergePathsContent.1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePathsMode.INTERSECT.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                com.airbnb.lottie.animation.content.MergePathsContent.1.$SwitchMap$com$airbnb$lottie$model$content$MergePaths$MergePathsMode[MergePathsMode.EXCLUDE_INTERSECTIONS.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
        }
    }

}

