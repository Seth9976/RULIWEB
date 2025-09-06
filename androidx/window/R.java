package androidx.window;

public final class R {
    public static final class attr {
        public static final int activityAction = 0x7F040027;  // attr:activityAction
        public static final int activityName = 0x7F040029;  // attr:activityName
        public static final int alwaysExpand = 0x7F040037;  // attr:alwaysExpand
        public static final int clearTop = 0x7F0400E4;  // attr:clearTop
        public static final int finishPrimaryWithSecondary = 0x7F0401F3;  // attr:finishPrimaryWithSecondary
        public static final int finishSecondaryWithPrimary = 0x7F0401F4;  // attr:finishSecondaryWithPrimary
        public static final int placeholderActivityName = 0x7F0403BC;  // attr:placeholderActivityName
        public static final int primaryActivityName = 0x7F0403D6;  // attr:primaryActivityName
        public static final int secondaryActivityAction = 0x7F0403FD;  // attr:secondaryActivityAction
        public static final int secondaryActivityName = 0x7F0403FE;  // attr:secondaryActivityName
        public static final int splitLayoutDirection = 0x7F040435;  // attr:splitLayoutDirection
        public static final int splitMinSmallestWidth = 0x7F040436;  // attr:splitMinSmallestWidth
        public static final int splitMinWidth = 0x7F040437;  // attr:splitMinWidth
        public static final int splitRatio = 0x7F040438;  // attr:splitRatio

    }

    public static final class id {
        public static final int androidx_window_activity_scope = 0x7F090050;  // id:androidx_window_activity_scope
        public static final int locale = 0x7F09010C;  // id:locale
        public static final int ltr = 0x7F09010E;  // id:ltr
        public static final int rtl = 0x7F09019B;  // id:rtl

    }

    public static final class styleable {
        public static final int[] ActivityFilter = null;
        public static final int ActivityFilter_activityAction = 0;
        public static final int ActivityFilter_activityName = 1;
        public static final int[] ActivityRule = null;
        public static final int ActivityRule_alwaysExpand = 0;
        public static final int[] SplitPairFilter = null;
        public static final int SplitPairFilter_primaryActivityName = 0;
        public static final int SplitPairFilter_secondaryActivityAction = 1;
        public static final int SplitPairFilter_secondaryActivityName = 2;
        public static final int[] SplitPairRule = null;
        public static final int SplitPairRule_clearTop = 0;
        public static final int SplitPairRule_finishPrimaryWithSecondary = 1;
        public static final int SplitPairRule_finishSecondaryWithPrimary = 2;
        public static final int SplitPairRule_splitLayoutDirection = 3;
        public static final int SplitPairRule_splitMinSmallestWidth = 4;
        public static final int SplitPairRule_splitMinWidth = 5;
        public static final int SplitPairRule_splitRatio = 6;
        public static final int[] SplitPlaceholderRule = null;
        public static final int SplitPlaceholderRule_placeholderActivityName = 0;
        public static final int SplitPlaceholderRule_splitLayoutDirection = 1;
        public static final int SplitPlaceholderRule_splitMinSmallestWidth = 2;
        public static final int SplitPlaceholderRule_splitMinWidth = 3;
        public static final int SplitPlaceholderRule_splitRatio = 4;

        static {
            styleable.ActivityFilter = new int[]{0x7F040027, 0x7F040029};  // attr:activityAction
            styleable.ActivityRule = new int[]{0x7F040037};  // attr:alwaysExpand
            styleable.SplitPairFilter = new int[]{0x7F0403D6, 0x7F0403FD, 0x7F0403FE};  // attr:primaryActivityName
            styleable.SplitPairRule = new int[]{0x7F0400E4, 0x7F0401F3, 0x7F0401F4, 0x7F040435, 0x7F040436, 0x7F040437, 0x7F040438};  // attr:clearTop
            styleable.SplitPlaceholderRule = new int[]{0x7F0403BC, 0x7F040435, 0x7F040436, 0x7F040437, 0x7F040438};  // attr:placeholderActivityName
        }
    }

}

