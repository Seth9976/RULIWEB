package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo.Builder;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.Builder;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo.ExtraRenderingInfo;
import android.view.accessibility.AccessibilityNodeInfo.RangeInfo;
import android.view.accessibility.AccessibilityNodeInfo.TouchDelegateInfo;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.R.id;
import androidx.core.view.WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0;
import java.lang.ref.WeakReference;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AccessibilityNodeInfoCompat {
    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION = null;
        public static final AccessibilityActionCompat ACTION_CLICK = null;
        public static final AccessibilityActionCompat ACTION_COLLAPSE = null;
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK = null;
        public static final AccessibilityActionCompat ACTION_COPY = null;
        public static final AccessibilityActionCompat ACTION_CUT = null;
        public static final AccessibilityActionCompat ACTION_DISMISS = null;
        public static final AccessibilityActionCompat ACTION_DRAG_CANCEL = null;
        public static final AccessibilityActionCompat ACTION_DRAG_DROP = null;
        public static final AccessibilityActionCompat ACTION_DRAG_START = null;
        public static final AccessibilityActionCompat ACTION_EXPAND = null;
        public static final AccessibilityActionCompat ACTION_FOCUS = null;
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP = null;
        public static final AccessibilityActionCompat ACTION_IME_ENTER = null;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK = null;
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW = null;
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY = null;
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT = null;
        public static final AccessibilityActionCompat ACTION_PAGE_DOWN = null;
        public static final AccessibilityActionCompat ACTION_PAGE_LEFT = null;
        public static final AccessibilityActionCompat ACTION_PAGE_RIGHT = null;
        public static final AccessibilityActionCompat ACTION_PAGE_UP = null;
        public static final AccessibilityActionCompat ACTION_PASTE = null;
        public static final AccessibilityActionCompat ACTION_PRESS_AND_HOLD = null;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = null;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_IN_DIRECTION = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION = null;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP = null;
        public static final AccessibilityActionCompat ACTION_SELECT = null;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS = null;
        public static final AccessibilityActionCompat ACTION_SET_SELECTION = null;
        public static final AccessibilityActionCompat ACTION_SET_TEXT = null;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN = null;
        public static final AccessibilityActionCompat ACTION_SHOW_TEXT_SUGGESTIONS = null;
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP = null;
        private static final String TAG = "A11yActionCompat";
        final Object mAction;
        protected final AccessibilityViewCommand mCommand;
        private final int mId;
        private final Class mViewCommandArgumentClass;

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityNodeInfo$AccessibilityAction0 = null;
            AccessibilityActionCompat.ACTION_FOCUS = new AccessibilityActionCompat(1, null);
            AccessibilityActionCompat.ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
            AccessibilityActionCompat.ACTION_SELECT = new AccessibilityActionCompat(4, null);
            AccessibilityActionCompat.ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
            AccessibilityActionCompat.ACTION_CLICK = new AccessibilityActionCompat(16, null);
            AccessibilityActionCompat.ACTION_LONG_CLICK = new AccessibilityActionCompat(0x20, null);
            AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(0x40, null);
            AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(0x80, null);
            AccessibilityActionCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(0x100, null, MoveAtGranularityArguments.class);
            AccessibilityActionCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(0x200, null, MoveAtGranularityArguments.class);
            AccessibilityActionCompat.ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(0x400, null, MoveHtmlArguments.class);
            AccessibilityActionCompat.ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(0x800, null, MoveHtmlArguments.class);
            AccessibilityActionCompat.ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(0x1000, null);
            AccessibilityActionCompat.ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(0x2000, null);
            AccessibilityActionCompat.ACTION_COPY = new AccessibilityActionCompat(0x4000, null);
            AccessibilityActionCompat.ACTION_PASTE = new AccessibilityActionCompat(0x8000, null);
            AccessibilityActionCompat.ACTION_CUT = new AccessibilityActionCompat(0x10000, null);
            AccessibilityActionCompat.ACTION_SET_SELECTION = new AccessibilityActionCompat(0x20000, null, SetSelectionArguments.class);
            AccessibilityActionCompat.ACTION_EXPAND = new AccessibilityActionCompat(0x40000, null);
            AccessibilityActionCompat.ACTION_COLLAPSE = new AccessibilityActionCompat(0x80000, null);
            AccessibilityActionCompat.ACTION_DISMISS = new AccessibilityActionCompat(0x100000, null);
            AccessibilityActionCompat.ACTION_SET_TEXT = new AccessibilityActionCompat(0x200000, null, SetTextArguments.class);
            AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN), 0x1020036, null, null, null);
            AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION), 0x1020037, null, null, ScrollToPositionArguments.class);
            AccessibilityActionCompat.ACTION_SCROLL_UP = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP), 0x1020038, null, null, null);
            AccessibilityActionCompat.ACTION_SCROLL_LEFT = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT), 0x1020039, null, null, null);
            AccessibilityActionCompat.ACTION_SCROLL_DOWN = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN), 0x102003A, null, null, null);
            AccessibilityActionCompat.ACTION_SCROLL_RIGHT = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT), 0x102003B, null, null, null);
            AccessibilityActionCompat.ACTION_PAGE_UP = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 29 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP), 0x1020046, null, null, null);
            AccessibilityActionCompat.ACTION_PAGE_DOWN = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 29 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN), 0x1020047, null, null, null);
            AccessibilityActionCompat.ACTION_PAGE_LEFT = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 29 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT), 0x1020048, null, null, null);
            AccessibilityActionCompat.ACTION_PAGE_RIGHT = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 29 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT), 0x1020049, null, null, null);
            AccessibilityActionCompat.ACTION_CONTEXT_CLICK = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK), 0x102003C, null, null, null);
            AccessibilityActionCompat.ACTION_SET_PROGRESS = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 24 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS), 0x102003D, null, null, SetProgressArguments.class);
            AccessibilityActionCompat.ACTION_MOVE_WINDOW = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 26 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW), 0x1020042, null, null, MoveWindowArguments.class);
            AccessibilityActionCompat.ACTION_SHOW_TOOLTIP = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 28 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP), 0x1020044, null, null, null);
            AccessibilityActionCompat.ACTION_HIDE_TOOLTIP = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 28 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP), 0x1020045, null, null, null);
            AccessibilityActionCompat.ACTION_PRESS_AND_HOLD = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 30 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD), 0x102004A, null, null, null);
            AccessibilityActionCompat.ACTION_IME_ENTER = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 30 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER), 0x1020054, null, null, null);
            AccessibilityActionCompat.ACTION_DRAG_START = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 0x20 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START), 0x1020055, null, null, null);
            AccessibilityActionCompat.ACTION_DRAG_DROP = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 0x20 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP), 0x1020056, null, null, null);
            AccessibilityActionCompat.ACTION_DRAG_CANCEL = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 0x20 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL), 0x1020057, null, null, null);
            AccessibilityActionCompat.ACTION_SHOW_TEXT_SUGGESTIONS = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 33 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS), 0x1020058, null, null, null);
            if(Build.VERSION.SDK_INT >= 34) {
                accessibilityNodeInfo$AccessibilityAction0 = Api34Impl.getActionScrollInDirection();
            }
            AccessibilityActionCompat.ACTION_SCROLL_IN_DIRECTION = new AccessibilityActionCompat(accessibilityNodeInfo$AccessibilityAction0, 0x102005E, null, null, null);
        }

        public AccessibilityActionCompat(int v, CharSequence charSequence0) {
            this(null, v, charSequence0, null, null);
        }

        public AccessibilityActionCompat(int v, CharSequence charSequence0, AccessibilityViewCommand accessibilityViewCommand0) {
            this(null, v, charSequence0, accessibilityViewCommand0, null);
        }

        private AccessibilityActionCompat(int v, CharSequence charSequence0, Class class0) {
            this(null, v, charSequence0, null, class0);
        }

        AccessibilityActionCompat(Object object0) {
            this(object0, 0, null, null, null);
        }

        AccessibilityActionCompat(Object object0, int v, CharSequence charSequence0, AccessibilityViewCommand accessibilityViewCommand0, Class class0) {
            this.mId = v;
            this.mCommand = accessibilityViewCommand0;
            this.mAction = object0 == null ? new AccessibilityNodeInfo.AccessibilityAction(v, charSequence0) : object0;
            this.mViewCommandArgumentClass = class0;
        }

        public AccessibilityActionCompat createReplacementAction(CharSequence charSequence0, AccessibilityViewCommand accessibilityViewCommand0) {
            return new AccessibilityActionCompat(null, this.mId, charSequence0, accessibilityViewCommand0, this.mViewCommandArgumentClass);
        }

        @Override
        public boolean equals(Object object0) {
            if(!(object0 instanceof AccessibilityActionCompat)) {
                return false;
            }
            return this.mAction == null ? ((AccessibilityActionCompat)object0).mAction == null : this.mAction.equals(((AccessibilityActionCompat)object0).mAction);
        }

        public int getId() {
            return ((AccessibilityNodeInfo.AccessibilityAction)this.mAction).getId();
        }

        public CharSequence getLabel() {
            return ((AccessibilityNodeInfo.AccessibilityAction)this.mAction).getLabel();
        }

        @Override
        public int hashCode() {
            return this.mAction == null ? 0 : this.mAction.hashCode();
        }

        public boolean perform(View view0, Bundle bundle0) {
            CommandArguments accessibilityViewCommand$CommandArguments1;
            if(this.mCommand != null) {
                Class class0 = this.mViewCommandArgumentClass;
                CommandArguments accessibilityViewCommand$CommandArguments0 = null;
                if(class0 != null) {
                    try {
                        accessibilityViewCommand$CommandArguments1 = (CommandArguments)class0.getDeclaredConstructor(null).newInstance(null);
                    }
                    catch(Exception exception0) {
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (this.mViewCommandArgumentClass == null ? "null" : this.mViewCommandArgumentClass.getName()), exception0);
                        return this.mCommand.perform(view0, accessibilityViewCommand$CommandArguments0);
                    }
                    try {
                        accessibilityViewCommand$CommandArguments1.setBundle(bundle0);
                        return this.mCommand.perform(view0, accessibilityViewCommand$CommandArguments1);
                    }
                    catch(Exception exception0) {
                        accessibilityViewCommand$CommandArguments0 = accessibilityViewCommand$CommandArguments1;
                    }
                    Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (this.mViewCommandArgumentClass == null ? "null" : this.mViewCommandArgumentClass.getName()), exception0);
                    return this.mCommand.perform(view0, accessibilityViewCommand$CommandArguments0);
                }
                return this.mCommand.perform(view0, null);
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder("AccessibilityActionCompat: ");
            String s = AccessibilityNodeInfoCompat.getActionSymbolicName(this.mId);
            if(s.equals("ACTION_UNKNOWN") && this.getLabel() != null) {
                s = this.getLabel().toString();
            }
            stringBuilder0.append(s);
            return stringBuilder0.toString();
        }
    }

    static class Api30Impl {
        public static Object createRangeInfo(int v, float f, float f1, float f2) {
            return new AccessibilityNodeInfo.RangeInfo(v, f, f1, f2);
        }

        public static CharSequence getStateDescription(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.getStateDescription();
        }

        public static void setStateDescription(AccessibilityNodeInfo accessibilityNodeInfo0, CharSequence charSequence0) {
            accessibilityNodeInfo0.setStateDescription(charSequence0);
        }
    }

    static class Api33Impl {
        public static CollectionItemInfoCompat buildCollectionItemInfoCompat(boolean z, int v, int v1, int v2, int v3, boolean z1, String s, String s1) {
            return new CollectionItemInfoCompat(new AccessibilityNodeInfo.CollectionItemInfo.Builder().setHeading(z).setColumnIndex(v).setRowIndex(v1).setColumnSpan(v2).setRowSpan(v3).setSelected(z1).setRowTitle(s).setColumnTitle(s1).build());
        }

        public static AccessibilityNodeInfoCompat getChild(AccessibilityNodeInfo accessibilityNodeInfo0, int v, int v1) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(accessibilityNodeInfo0.getChild(v, v1));
        }

        public static String getCollectionItemColumnTitle(Object object0) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object0).getColumnTitle();
        }

        public static String getCollectionItemRowTitle(Object object0) {
            return ((AccessibilityNodeInfo.CollectionItemInfo)object0).getRowTitle();
        }

        public static AccessibilityNodeInfo.ExtraRenderingInfo getExtraRenderingInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.getExtraRenderingInfo();
        }

        public static AccessibilityNodeInfoCompat getParent(AccessibilityNodeInfo accessibilityNodeInfo0, int v) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(accessibilityNodeInfo0.getParent(v));
        }

        public static String getUniqueId(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.getUniqueId();
        }

        public static boolean isTextSelectable(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.isTextSelectable();
        }

        public static void setTextSelectable(AccessibilityNodeInfo accessibilityNodeInfo0, boolean z) {
            accessibilityNodeInfo0.setTextSelectable(z);
        }

        public static void setUniqueId(AccessibilityNodeInfo accessibilityNodeInfo0, String s) {
            accessibilityNodeInfo0.setUniqueId(s);
        }
    }

    static class Api34Impl {
        public static AccessibilityNodeInfo.AccessibilityAction getActionScrollInDirection() {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_IN_DIRECTION;
        }

        public static void getBoundsInWindow(AccessibilityNodeInfo accessibilityNodeInfo0, Rect rect0) {
            accessibilityNodeInfo0.getBoundsInWindow(rect0);
        }

        public static CharSequence getContainerTitle(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.getContainerTitle();
        }

        public static long getMinDurationBetweenContentChangeMillis(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.getMinDurationBetweenContentChanges().toMillis();
        }

        public static boolean hasRequestInitialAccessibilityFocus(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.hasRequestInitialAccessibilityFocus();
        }

        public static boolean isAccessibilityDataSensitive(AccessibilityNodeInfo accessibilityNodeInfo0) {
            return accessibilityNodeInfo0.isAccessibilityDataSensitive();
        }

        public static void setAccessibilityDataSensitive(AccessibilityNodeInfo accessibilityNodeInfo0, boolean z) {
            accessibilityNodeInfo0.setAccessibilityDataSensitive(z);
        }

        public static void setBoundsInWindow(AccessibilityNodeInfo accessibilityNodeInfo0, Rect rect0) {
            accessibilityNodeInfo0.setBoundsInWindow(rect0);
        }

        public static void setContainerTitle(AccessibilityNodeInfo accessibilityNodeInfo0, CharSequence charSequence0) {
            accessibilityNodeInfo0.setContainerTitle(charSequence0);
        }

        public static void setMinDurationBetweenContentChangeMillis(AccessibilityNodeInfo accessibilityNodeInfo0, long v) {
            accessibilityNodeInfo0.setMinDurationBetweenContentChanges(Duration.ofMillis(v));
        }

        public static void setQueryFromAppProcessEnabled(AccessibilityNodeInfo accessibilityNodeInfo0, View view0, boolean z) {
            accessibilityNodeInfo0.setQueryFromAppProcessEnabled(view0, z);
        }

        public static void setRequestInitialAccessibilityFocus(AccessibilityNodeInfo accessibilityNodeInfo0, boolean z) {
            accessibilityNodeInfo0.setRequestInitialAccessibilityFocus(z);
        }
    }

    static class Api35Impl {
        public static CollectionInfoCompat buildCollectionInfoCompat(int v, int v1, boolean z, int v2, int v3, int v4) {
            return new CollectionInfoCompat(new AccessibilityNodeInfo.CollectionInfo.Builder().setRowCount(v).setColumnCount(v1).setHierarchical(z).setSelectionMode(v2).setItemCount(v3).setImportantForAccessibilityItemCount(v4).build());
        }

        public static int getImportantForAccessibilityItemCount(Object object0) {
            return ((AccessibilityNodeInfo.CollectionInfo)object0).getImportantForAccessibilityItemCount();
        }

        public static int getItemCount(Object object0) {
            return ((AccessibilityNodeInfo.CollectionInfo)object0).getItemCount();
        }
    }

    public static class CollectionInfoCompat {
        public static final class Builder {
            private int mColumnCount;
            private boolean mHierarchical;
            private int mImportantForAccessibilityItemCount;
            private int mItemCount;
            private int mRowCount;
            private int mSelectionMode;

            public Builder() {
                this.mRowCount = 0;
                this.mColumnCount = 0;
                this.mHierarchical = false;
                this.mItemCount = -1;
                this.mImportantForAccessibilityItemCount = -1;
            }

            public CollectionInfoCompat build() {
                return Build.VERSION.SDK_INT < 35 ? CollectionInfoCompat.obtain(this.mRowCount, this.mColumnCount, this.mHierarchical, this.mSelectionMode) : Api35Impl.buildCollectionInfoCompat(this.mRowCount, this.mColumnCount, this.mHierarchical, this.mSelectionMode, this.mItemCount, this.mImportantForAccessibilityItemCount);
            }

            public Builder setColumnCount(int v) {
                this.mColumnCount = v;
                return this;
            }

            public Builder setHierarchical(boolean z) {
                this.mHierarchical = z;
                return this;
            }

            public Builder setImportantForAccessibilityItemCount(int v) {
                this.mImportantForAccessibilityItemCount = v;
                return this;
            }

            public Builder setItemCount(int v) {
                this.mItemCount = v;
                return this;
            }

            public Builder setRowCount(int v) {
                this.mRowCount = v;
                return this;
            }

            public Builder setSelectionMode(int v) {
                this.mSelectionMode = v;
                return this;
            }
        }

        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        public static final int UNDEFINED = -1;
        final Object mInfo;

        CollectionInfoCompat(Object object0) {
            this.mInfo = object0;
        }

        public int getColumnCount() {
            return ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).getColumnCount();
        }

        public int getImportantForAccessibilityItemCount() {
            return Build.VERSION.SDK_INT < 35 ? -1 : Api35Impl.getImportantForAccessibilityItemCount(this.mInfo);
        }

        public int getItemCount() {
            return Build.VERSION.SDK_INT < 35 ? -1 : Api35Impl.getItemCount(this.mInfo);
        }

        public int getRowCount() {
            return ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).getRowCount();
        }

        public int getSelectionMode() {
            return ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).getSelectionMode();
        }

        public boolean isHierarchical() {
            return ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).isHierarchical();
        }

        public static CollectionInfoCompat obtain(int v, int v1, boolean z) {
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(v, v1, z));
        }

        public static CollectionInfoCompat obtain(int v, int v1, boolean z, int v2) {
            return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(v, v1, z, v2));
        }
    }

    public static class CollectionItemInfoCompat {
        public static final class androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder {
            private int mColumnIndex;
            private int mColumnSpan;
            private String mColumnTitle;
            private boolean mHeading;
            private int mRowIndex;
            private int mRowSpan;
            private String mRowTitle;
            private boolean mSelected;

            public CollectionItemInfoCompat build() {
                return Build.VERSION.SDK_INT < 33 ? new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(this.mRowIndex, this.mRowSpan, this.mColumnIndex, this.mColumnSpan, this.mHeading, this.mSelected)) : Api33Impl.buildCollectionItemInfoCompat(this.mHeading, this.mColumnIndex, this.mRowIndex, this.mColumnSpan, this.mRowSpan, this.mSelected, this.mRowTitle, this.mColumnTitle);
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setColumnIndex(int v) {
                this.mColumnIndex = v;
                return this;
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setColumnSpan(int v) {
                this.mColumnSpan = v;
                return this;
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setColumnTitle(String s) {
                this.mColumnTitle = s;
                return this;
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setHeading(boolean z) {
                this.mHeading = z;
                return this;
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setRowIndex(int v) {
                this.mRowIndex = v;
                return this;
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setRowSpan(int v) {
                this.mRowSpan = v;
                return this;
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setRowTitle(String s) {
                this.mRowTitle = s;
                return this;
            }

            public androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat.Builder setSelected(boolean z) {
                this.mSelected = z;
                return this;
            }
        }

        final Object mInfo;

        CollectionItemInfoCompat(Object object0) {
            this.mInfo = object0;
        }

        public int getColumnIndex() {
            return ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getColumnIndex();
        }

        public int getColumnSpan() {
            return ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getColumnSpan();
        }

        public String getColumnTitle() {
            return Build.VERSION.SDK_INT < 33 ? null : Api33Impl.getCollectionItemColumnTitle(this.mInfo);
        }

        public int getRowIndex() {
            return ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getRowIndex();
        }

        public int getRowSpan() {
            return ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getRowSpan();
        }

        public String getRowTitle() {
            return Build.VERSION.SDK_INT < 33 ? null : Api33Impl.getCollectionItemRowTitle(this.mInfo);
        }

        @Deprecated
        public boolean isHeading() {
            return ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).isHeading();
        }

        public boolean isSelected() {
            return ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).isSelected();
        }

        public static CollectionItemInfoCompat obtain(int v, int v1, int v2, int v3, boolean z) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(v, v1, v2, v3, z));
        }

        public static CollectionItemInfoCompat obtain(int v, int v1, int v2, int v3, boolean z, boolean z1) {
            return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(v, v1, v2, v3, z, z1));
        }
    }

    public static class RangeInfoCompat {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;

        public RangeInfoCompat(int v, float f, float f1, float f2) {
            if(Build.VERSION.SDK_INT >= 30) {
                this.mInfo = Api30Impl.createRangeInfo(v, f, f1, f2);
                return;
            }
            this.mInfo = AccessibilityNodeInfo.RangeInfo.obtain(v, f, f1, f2);
        }

        RangeInfoCompat(Object object0) {
            this.mInfo = object0;
        }

        public float getCurrent() {
            return ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getCurrent();
        }

        public float getMax() {
            return ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getMax();
        }

        public float getMin() {
            return ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getMin();
        }

        public int getType() {
            return ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getType();
        }

        public static RangeInfoCompat obtain(int v, float f, float f1, float f2) {
            return new RangeInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(v, f, f1, f2));
        }
    }

    public static final class TouchDelegateInfoCompat {
        final AccessibilityNodeInfo.TouchDelegateInfo mInfo;

        TouchDelegateInfoCompat(AccessibilityNodeInfo.TouchDelegateInfo accessibilityNodeInfo$TouchDelegateInfo0) {
            this.mInfo = accessibilityNodeInfo$TouchDelegateInfo0;
        }

        public TouchDelegateInfoCompat(Map map0) {
            if(Build.VERSION.SDK_INT >= 29) {
                this.mInfo = WindowInsetsCompat.Impl28..ExternalSyntheticApiModelOutline0.m(map0);
                return;
            }
            this.mInfo = null;
        }

        public Region getRegionAt(int v) {
            return Build.VERSION.SDK_INT < 29 ? null : this.mInfo.getRegionAt(v);
        }

        public int getRegionCount() {
            return Build.VERSION.SDK_INT < 29 ? 0 : this.mInfo.getRegionCount();
        }

        public AccessibilityNodeInfoCompat getTargetForRegion(Region region0) {
            if(Build.VERSION.SDK_INT >= 29) {
                AccessibilityNodeInfo accessibilityNodeInfo0 = this.mInfo.getTargetForRegion(region0);
                return accessibilityNodeInfo0 == null ? null : AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
            }
            return null;
        }
    }

    public static final int ACTION_ACCESSIBILITY_FOCUS = 0x40;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_DIRECTION_INT = "androidx.core.view.accessibility.action.ARGUMENT_DIRECTION_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final String ACTION_ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT = "android.view.accessibility.action.ARGUMENT_PRESS_AND_HOLD_DURATION_MILLIS_INT";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SCROLL_AMOUNT_FLOAT = "androidx.core.view.accessibility.action.ARGUMENT_SCROLL_AMOUNT_FLOAT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 0x80;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 0x80000;
    public static final int ACTION_COPY = 0x4000;
    public static final int ACTION_CUT = 0x10000;
    public static final int ACTION_DISMISS = 0x100000;
    public static final int ACTION_EXPAND = 0x40000;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 0x20;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 0x100;
    public static final int ACTION_NEXT_HTML_ELEMENT = 0x400;
    public static final int ACTION_PASTE = 0x8000;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 0x200;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 0x800;
    public static final int ACTION_SCROLL_BACKWARD = 0x2000;
    public static final int ACTION_SCROLL_FORWARD = 0x1000;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 0x20000;
    public static final int ACTION_SET_TEXT = 0x200000;
    private static final int BOOLEAN_PROPERTY_ACCESSIBILITY_DATA_SENSITIVE = 0x40;
    private static final int BOOLEAN_PROPERTY_HAS_REQUEST_INITIAL_ACCESSIBILITY_FOCUS = 0x20;
    private static final int BOOLEAN_PROPERTY_IS_HEADING = 2;
    private static final int BOOLEAN_PROPERTY_IS_SHOWING_HINT = 4;
    private static final int BOOLEAN_PROPERTY_IS_TEXT_ENTRY_KEY = 8;
    private static final String BOOLEAN_PROPERTY_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY";
    private static final int BOOLEAN_PROPERTY_SCREEN_READER_FOCUSABLE = 1;
    private static final int BOOLEAN_PROPERTY_SUPPORTS_GRANULAR_SCROLLING = 0x4000000;
    private static final int BOOLEAN_PROPERTY_TEXT_SELECTABLE = 0x800000;
    private static final String BOUNDS_IN_WINDOW_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOUNDS_IN_WINDOW_KEY";
    private static final String CONTAINER_TITLE_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.CONTAINER_TITLE_KEY";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH";
    public static final int EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH = 20000;
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_IN_WINDOW_KEY = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_IN_WINDOW_KEY";
    public static final String EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY";
    public static final int FLAG_PREFETCH_ANCESTORS = 1;
    public static final int FLAG_PREFETCH_DESCENDANTS_BREADTH_FIRST = 16;
    public static final int FLAG_PREFETCH_DESCENDANTS_DEPTH_FIRST = 8;
    public static final int FLAG_PREFETCH_DESCENDANTS_HYBRID = 4;
    public static final int FLAG_PREFETCH_SIBLINGS = 2;
    public static final int FLAG_PREFETCH_UNINTERRUPTIBLE = 0x20;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    private static final String HINT_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY";
    private static final String IS_REQUIRED_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.IS_REQUIRED_KEY";
    public static final int MAX_NUMBER_OF_PREFETCHED_NODES = 50;
    private static final String MIN_DURATION_BETWEEN_CONTENT_CHANGES_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.MIN_DURATION_BETWEEN_CONTENT_CHANGES_KEY";
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private static final String PANE_TITLE_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY";
    private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    private static final String SPANS_ACTION_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY";
    private static final String SPANS_END_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY";
    private static final String SPANS_FLAGS_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY";
    private static final String SPANS_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY";
    private static final String SPANS_START_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY";
    private static final String STATE_DESCRIPTION_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY";
    private static final String TOOLTIP_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY";
    private static final String UNIQUE_ID_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY";
    private final AccessibilityNodeInfo mInfo;
    public int mParentVirtualDescendantId;
    private int mVirtualDescendantId;
    private static int sClickableSpanId;

    static {
    }

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo0) {
        this.mParentVirtualDescendantId = -1;
        this.mVirtualDescendantId = -1;
        this.mInfo = accessibilityNodeInfo0;
    }

    @Deprecated
    public AccessibilityNodeInfoCompat(Object object0) {
        this.mParentVirtualDescendantId = -1;
        this.mVirtualDescendantId = -1;
        this.mInfo = (AccessibilityNodeInfo)object0;
    }

    public void addAction(int v) {
        this.mInfo.addAction(v);
    }

    public void addAction(AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0) {
        this.mInfo.addAction(((AccessibilityNodeInfo.AccessibilityAction)accessibilityNodeInfoCompat$AccessibilityActionCompat0.mAction));
    }

    public void addChild(View view0) {
        this.mInfo.addChild(view0);
    }

    public void addChild(View view0, int v) {
        this.mInfo.addChild(view0, v);
    }

    private void addSpanLocationToExtras(ClickableSpan clickableSpan0, Spanned spanned0, int v) {
        this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(spanned0.getSpanStart(clickableSpan0));
        this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(spanned0.getSpanEnd(clickableSpan0));
        this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(spanned0.getSpanFlags(clickableSpan0));
        this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(v);
    }

    public void addSpansToExtras(CharSequence charSequence0, View view0) {
        if(Build.VERSION.SDK_INT < 26) {
            this.clearExtrasSpans();
            this.removeCollectedSpans(view0);
            ClickableSpan[] arr_clickableSpan = AccessibilityNodeInfoCompat.getClickableSpans(charSequence0);
            if(arr_clickableSpan != null && arr_clickableSpan.length > 0) {
                this.getExtras().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", id.accessibility_action_clickable_span);
                SparseArray sparseArray0 = this.getOrCreateSpansFromViewTags(view0);
                for(int v = 0; arr_clickableSpan != null && v < arr_clickableSpan.length; ++v) {
                    int v1 = this.idForClickableSpan(arr_clickableSpan[v], sparseArray0);
                    sparseArray0.put(v1, new WeakReference(arr_clickableSpan[v]));
                    this.addSpanLocationToExtras(arr_clickableSpan[v], ((Spanned)charSequence0), v1);
                }
            }
        }
    }

    public boolean canOpenPopup() {
        return this.mInfo.canOpenPopup();
    }

    private void clearExtrasSpans() {
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        this.mInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof AccessibilityNodeInfoCompat)) {
            return false;
        }
        AccessibilityNodeInfo accessibilityNodeInfo0 = this.mInfo;
        if(accessibilityNodeInfo0 == null) {
            if(((AccessibilityNodeInfoCompat)object0).mInfo != null) {
                return false;
            }
        }
        else if(!accessibilityNodeInfo0.equals(((AccessibilityNodeInfoCompat)object0).mInfo)) {
            return false;
        }
        return this.mVirtualDescendantId == ((AccessibilityNodeInfoCompat)object0).mVirtualDescendantId ? this.mParentVirtualDescendantId == ((AccessibilityNodeInfoCompat)object0).mParentVirtualDescendantId : false;
    }

    private List extrasIntList(String s) {
        List list0 = this.mInfo.getExtras().getIntegerArrayList(s);
        if(list0 == null) {
            list0 = new ArrayList();
            this.mInfo.getExtras().putIntegerArrayList(s, ((ArrayList)list0));
        }
        return list0;
    }

    public List findAccessibilityNodeInfosByText(String s) {
        List list0 = new ArrayList();
        List list1 = this.mInfo.findAccessibilityNodeInfosByText(s);
        int v = list1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            list0.add(AccessibilityNodeInfoCompat.wrap(((AccessibilityNodeInfo)list1.get(v1))));
        }
        return list0;
    }

    public List findAccessibilityNodeInfosByViewId(String s) {
        List list0 = this.mInfo.findAccessibilityNodeInfosByViewId(s);
        List list1 = new ArrayList();
        for(Object object0: list0) {
            list1.add(AccessibilityNodeInfoCompat.wrap(((AccessibilityNodeInfo)object0)));
        }
        return list1;
    }

    public AccessibilityNodeInfoCompat findFocus(int v) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.findFocus(v));
    }

    public AccessibilityNodeInfoCompat focusSearch(int v) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.focusSearch(v));
    }

    public List getActionList() {
        List list0 = this.mInfo.getActionList();
        List list1 = new ArrayList();
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            list1.add(new AccessibilityActionCompat(list0.get(v1)));
        }
        return list1;
    }

    static String getActionSymbolicName(int v) {
        switch(v) {
            case 1: {
                return "ACTION_FOCUS";
            }
            case 2: {
                return "ACTION_CLEAR_FOCUS";
            }
            case 4: {
                return "ACTION_SELECT";
            }
            case 8: {
                return "ACTION_CLEAR_SELECTION";
            }
            case 16: {
                return "ACTION_CLICK";
            }
            case 0x20: {
                return "ACTION_LONG_CLICK";
            }
            case 0x40: {
                return "ACTION_ACCESSIBILITY_FOCUS";
            }
            case 0x80: {
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            }
            case 0x100: {
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            }
            case 0x200: {
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            }
            case 0x400: {
                return "ACTION_NEXT_HTML_ELEMENT";
            }
            case 0x800: {
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            }
            case 0x1000: {
                return "ACTION_SCROLL_FORWARD";
            }
            case 0x2000: {
                return "ACTION_SCROLL_BACKWARD";
            }
            case 0x4000: {
                return "ACTION_COPY";
            }
            case 0x8000: {
                return "ACTION_PASTE";
            }
            case 0x10000: {
                return "ACTION_CUT";
            }
            case 0x20000: {
                return "ACTION_SET_SELECTION";
            }
            case 0x40000: {
                return "ACTION_EXPAND";
            }
            case 0x80000: {
                return "ACTION_COLLAPSE";
            }
            case 0x200000: {
                return "ACTION_SET_TEXT";
            }
            case 0x1020036: {
                return "ACTION_SHOW_ON_SCREEN";
            }
            case 0x1020037: {
                return "ACTION_SCROLL_TO_POSITION";
            }
            case 0x1020038: {
                return "ACTION_SCROLL_UP";
            }
            case 0x1020039: {
                return "ACTION_SCROLL_LEFT";
            }
            case 0x102003A: {
                return "ACTION_SCROLL_DOWN";
            }
            case 0x102003B: {
                return "ACTION_SCROLL_RIGHT";
            }
            case 0x102003C: {
                return "ACTION_CONTEXT_CLICK";
            }
            case 0x102003D: {
                return "ACTION_SET_PROGRESS";
            }
            case 0x1020042: {
                return "ACTION_MOVE_WINDOW";
            }
            case 0x1020044: {
                return "ACTION_SHOW_TOOLTIP";
            }
            case 0x1020045: {
                return "ACTION_HIDE_TOOLTIP";
            }
            case 0x1020046: {
                return "ACTION_PAGE_UP";
            }
            case 0x1020047: {
                return "ACTION_PAGE_DOWN";
            }
            case 0x1020048: {
                return "ACTION_PAGE_LEFT";
            }
            case 0x1020049: {
                return "ACTION_PAGE_RIGHT";
            }
            case 0x102004A: {
                return "ACTION_PRESS_AND_HOLD";
            }
            case 0x1020054: {
                return "ACTION_IME_ENTER";
            }
            case 0x1020055: {
                return "ACTION_DRAG_START";
            }
            case 0x1020056: {
                return "ACTION_DRAG_DROP";
            }
            case 0x1020057: {
                return "ACTION_DRAG_CANCEL";
            }
            case 0x102005E: {
                return "ACTION_SCROLL_IN_DIRECTION";
            }
            default: {
                return "ACTION_UNKNOWN";
            }
        }
    }

    @Deprecated
    public int getActions() {
        return this.mInfo.getActions();
    }

    public List getAvailableExtraData() {
        return Build.VERSION.SDK_INT < 26 ? Collections.EMPTY_LIST : this.mInfo.getAvailableExtraData();
    }

    private boolean getBooleanProperty(int v) {
        Bundle bundle0 = this.getExtras();
        return bundle0 == null ? false : (bundle0.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & v) == v;
    }

    @Deprecated
    public void getBoundsInParent(Rect rect0) {
        this.mInfo.getBoundsInParent(rect0);
    }

    public void getBoundsInScreen(Rect rect0) {
        this.mInfo.getBoundsInScreen(rect0);
    }

    public void getBoundsInWindow(Rect rect0) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.getBoundsInWindow(this.mInfo, rect0);
            return;
        }
        Rect rect1 = (Rect)this.mInfo.getExtras().getParcelable("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOUNDS_IN_WINDOW_KEY");
        if(rect1 != null) {
            rect0.set(rect1.left, rect1.top, rect1.right, rect1.bottom);
        }
    }

    public AccessibilityNodeInfoCompat getChild(int v) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getChild(v));
    }

    public AccessibilityNodeInfoCompat getChild(int v, int v1) {
        return Build.VERSION.SDK_INT < 33 ? this.getChild(v) : Api33Impl.getChild(this.mInfo, v, v1);
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    // 去混淆评级： 低(20)
    public static ClickableSpan[] getClickableSpans(CharSequence charSequence0) {
        return charSequence0 instanceof Spanned ? ((ClickableSpan[])((Spanned)charSequence0).getSpans(0, charSequence0.length(), ClickableSpan.class)) : null;
    }

    public CollectionInfoCompat getCollectionInfo() {
        AccessibilityNodeInfo.CollectionInfo accessibilityNodeInfo$CollectionInfo0 = this.mInfo.getCollectionInfo();
        return accessibilityNodeInfo$CollectionInfo0 == null ? null : new CollectionInfoCompat(accessibilityNodeInfo$CollectionInfo0);
    }

    public CollectionItemInfoCompat getCollectionItemInfo() {
        AccessibilityNodeInfo.CollectionItemInfo accessibilityNodeInfo$CollectionItemInfo0 = this.mInfo.getCollectionItemInfo();
        return accessibilityNodeInfo$CollectionItemInfo0 == null ? null : new CollectionItemInfoCompat(accessibilityNodeInfo$CollectionItemInfo0);
    }

    public CharSequence getContainerTitle() {
        return Build.VERSION.SDK_INT < 34 ? this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.CONTAINER_TITLE_KEY") : Api34Impl.getContainerTitle(this.mInfo);
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public int getDrawingOrder() {
        return Build.VERSION.SDK_INT < 24 ? 0 : this.mInfo.getDrawingOrder();
    }

    public CharSequence getError() {
        return this.mInfo.getError();
    }

    public AccessibilityNodeInfo.ExtraRenderingInfo getExtraRenderingInfo() {
        return Build.VERSION.SDK_INT < 33 ? null : Api33Impl.getExtraRenderingInfo(this.mInfo);
    }

    public Bundle getExtras() {
        return this.mInfo.getExtras();
    }

    public CharSequence getHintText() {
        return Build.VERSION.SDK_INT < 26 ? this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY") : this.mInfo.getHintText();
    }

    @Deprecated
    public Object getInfo() {
        return this.mInfo;
    }

    public int getInputType() {
        return this.mInfo.getInputType();
    }

    public AccessibilityNodeInfoCompat getLabelFor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getLabelFor());
    }

    public AccessibilityNodeInfoCompat getLabeledBy() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getLabeledBy());
    }

    public int getLiveRegion() {
        return this.mInfo.getLiveRegion();
    }

    public int getMaxTextLength() {
        return this.mInfo.getMaxTextLength();
    }

    public long getMinDurationBetweenContentChangesMillis() {
        return Build.VERSION.SDK_INT < 34 ? this.mInfo.getExtras().getLong("androidx.view.accessibility.AccessibilityNodeInfoCompat.MIN_DURATION_BETWEEN_CONTENT_CHANGES_KEY") : Api34Impl.getMinDurationBetweenContentChangeMillis(this.mInfo);
    }

    public int getMovementGranularities() {
        return this.mInfo.getMovementGranularities();
    }

    private SparseArray getOrCreateSpansFromViewTags(View view0) {
        SparseArray sparseArray0 = this.getSpansFromViewTags(view0);
        if(sparseArray0 == null) {
            sparseArray0 = new SparseArray();
            view0.setTag(id.tag_accessibility_clickable_spans, sparseArray0);
        }
        return sparseArray0;
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    public CharSequence getPaneTitle() {
        return Build.VERSION.SDK_INT < 28 ? this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY") : this.mInfo.getPaneTitle();
    }

    public AccessibilityNodeInfoCompat getParent() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getParent());
    }

    public AccessibilityNodeInfoCompat getParent(int v) {
        return Build.VERSION.SDK_INT < 33 ? this.getParent() : Api33Impl.getParent(this.mInfo, v);
    }

    public RangeInfoCompat getRangeInfo() {
        AccessibilityNodeInfo.RangeInfo accessibilityNodeInfo$RangeInfo0 = this.mInfo.getRangeInfo();
        return accessibilityNodeInfo$RangeInfo0 == null ? null : new RangeInfoCompat(accessibilityNodeInfo$RangeInfo0);
    }

    public CharSequence getRoleDescription() {
        return this.mInfo.getExtras().getCharSequence("AccessibilityNodeInfo.roleDescription");
    }

    private SparseArray getSpansFromViewTags(View view0) {
        return (SparseArray)view0.getTag(id.tag_accessibility_clickable_spans);
    }

    public CharSequence getStateDescription() {
        return Build.VERSION.SDK_INT < 30 ? this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY") : Api30Impl.getStateDescription(this.mInfo);
    }

    public CharSequence getText() {
        if(this.hasSpans()) {
            List list0 = this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            List list1 = this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            List list2 = this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            List list3 = this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            CharSequence charSequence0 = new SpannableString(TextUtils.substring(this.mInfo.getText(), 0, this.mInfo.getText().length()));
            for(int v = 0; v < list0.size(); ++v) {
                ((Spannable)charSequence0).setSpan(new AccessibilityClickableSpanCompat(((int)(((Integer)list3.get(v)))), this, this.getExtras().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), ((int)(((Integer)list0.get(v)))), ((int)(((Integer)list1.get(v)))), ((int)(((Integer)list2.get(v)))));
            }
            return charSequence0;
        }
        return this.mInfo.getText();
    }

    public int getTextSelectionEnd() {
        return this.mInfo.getTextSelectionEnd();
    }

    public int getTextSelectionStart() {
        return this.mInfo.getTextSelectionStart();
    }

    public CharSequence getTooltipText() {
        return Build.VERSION.SDK_INT < 28 ? this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY") : this.mInfo.getTooltipText();
    }

    public TouchDelegateInfoCompat getTouchDelegateInfo() {
        if(Build.VERSION.SDK_INT >= 29) {
            AccessibilityNodeInfo.TouchDelegateInfo accessibilityNodeInfo$TouchDelegateInfo0 = this.mInfo.getTouchDelegateInfo();
            return accessibilityNodeInfo$TouchDelegateInfo0 == null ? null : new TouchDelegateInfoCompat(accessibilityNodeInfo$TouchDelegateInfo0);
        }
        return null;
    }

    public AccessibilityNodeInfoCompat getTraversalAfter() {
        return Build.VERSION.SDK_INT < 22 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getTraversalAfter());
    }

    public AccessibilityNodeInfoCompat getTraversalBefore() {
        return Build.VERSION.SDK_INT < 22 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getTraversalBefore());
    }

    public String getUniqueId() {
        return Build.VERSION.SDK_INT < 33 ? this.mInfo.getExtras().getString("androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY") : Api33Impl.getUniqueId(this.mInfo);
    }

    public String getViewIdResourceName() {
        return this.mInfo.getViewIdResourceName();
    }

    public AccessibilityWindowInfoCompat getWindow() {
        return AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getWindow());
    }

    public int getWindowId() {
        return this.mInfo.getWindowId();
    }

    public boolean hasRequestInitialAccessibilityFocus() {
        return Build.VERSION.SDK_INT < 34 ? this.getBooleanProperty(0x20) : Api34Impl.hasRequestInitialAccessibilityFocus(this.mInfo);
    }

    private boolean hasSpans() {
        return !this.extrasIntList("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    @Override
    public int hashCode() {
        return this.mInfo == null ? 0 : this.mInfo.hashCode();
    }

    private int idForClickableSpan(ClickableSpan clickableSpan0, SparseArray sparseArray0) {
        if(sparseArray0 != null) {
            for(int v = 0; v < sparseArray0.size(); ++v) {
                if(clickableSpan0.equals(((ClickableSpan)((WeakReference)sparseArray0.valueAt(v)).get()))) {
                    return sparseArray0.keyAt(v);
                }
            }
        }
        int v1 = AccessibilityNodeInfoCompat.sClickableSpanId;
        AccessibilityNodeInfoCompat.sClickableSpanId = v1 + 1;
        return v1;
    }

    public boolean isAccessibilityDataSensitive() {
        return Build.VERSION.SDK_INT < 34 ? this.getBooleanProperty(0x40) : Api34Impl.isAccessibilityDataSensitive(this.mInfo);
    }

    public boolean isAccessibilityFocused() {
        return this.mInfo.isAccessibilityFocused();
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public boolean isContentInvalid() {
        return this.mInfo.isContentInvalid();
    }

    public boolean isContextClickable() {
        return Build.VERSION.SDK_INT < 23 ? false : this.mInfo.isContextClickable();
    }

    public boolean isDismissable() {
        return this.mInfo.isDismissable();
    }

    public boolean isEditable() {
        return this.mInfo.isEditable();
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public boolean isFieldRequired() {
        return this.mInfo.getExtras().getBoolean("androidx.view.accessibility.AccessibilityNodeInfoCompat.IS_REQUIRED_KEY");
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public boolean isGranularScrollingSupported() {
        return this.getBooleanProperty(0x4000000);
    }

    public boolean isHeading() {
        if(Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.isHeading();
        }
        if(this.getBooleanProperty(2)) {
            return true;
        }
        CollectionItemInfoCompat accessibilityNodeInfoCompat$CollectionItemInfoCompat0 = this.getCollectionItemInfo();
        return accessibilityNodeInfoCompat$CollectionItemInfoCompat0 != null && accessibilityNodeInfoCompat$CollectionItemInfoCompat0.isHeading();
    }

    public boolean isImportantForAccessibility() {
        return Build.VERSION.SDK_INT < 24 ? true : this.mInfo.isImportantForAccessibility();
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public boolean isMultiLine() {
        return this.mInfo.isMultiLine();
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public boolean isScreenReaderFocusable() {
        return Build.VERSION.SDK_INT < 28 ? this.getBooleanProperty(1) : this.mInfo.isScreenReaderFocusable();
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public boolean isShowingHintText() {
        return Build.VERSION.SDK_INT < 26 ? this.getBooleanProperty(4) : this.mInfo.isShowingHintText();
    }

    public boolean isTextEntryKey() {
        return Build.VERSION.SDK_INT < 29 ? this.getBooleanProperty(8) : this.mInfo.isTextEntryKey();
    }

    public boolean isTextSelectable() {
        return Build.VERSION.SDK_INT < 33 ? this.getBooleanProperty(0x800000) : Api33Impl.isTextSelectable(this.mInfo);
    }

    public boolean isVisibleToUser() {
        return this.mInfo.isVisibleToUser();
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(View view0) {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain(view0));
    }

    public static AccessibilityNodeInfoCompat obtain(View view0, int v) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfo.obtain(view0, v));
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain(accessibilityNodeInfoCompat0.mInfo));
    }

    public boolean performAction(int v) {
        return this.mInfo.performAction(v);
    }

    public boolean performAction(int v, Bundle bundle0) {
        return this.mInfo.performAction(v, bundle0);
    }

    @Deprecated
    public void recycle() {
    }

    public boolean refresh() {
        return this.mInfo.refresh();
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0) {
        return this.mInfo.removeAction(((AccessibilityNodeInfo.AccessibilityAction)accessibilityNodeInfoCompat$AccessibilityActionCompat0.mAction));
    }

    public boolean removeChild(View view0) {
        return this.mInfo.removeChild(view0);
    }

    public boolean removeChild(View view0, int v) {
        return this.mInfo.removeChild(view0, v);
    }

    private void removeCollectedSpans(View view0) {
        SparseArray sparseArray0 = this.getSpansFromViewTags(view0);
        if(sparseArray0 != null) {
            ArrayList arrayList0 = new ArrayList();
            for(int v1 = 0; v1 < sparseArray0.size(); ++v1) {
                if(((WeakReference)sparseArray0.valueAt(v1)).get() == null) {
                    arrayList0.add(v1);
                }
            }
            for(int v = 0; v < arrayList0.size(); ++v) {
                sparseArray0.remove(((int)(((Integer)arrayList0.get(v)))));
            }
        }
    }

    public void setAccessibilityDataSensitive(boolean z) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setAccessibilityDataSensitive(this.mInfo, z);
            return;
        }
        this.setBooleanProperty(0x40, z);
    }

    public void setAccessibilityFocused(boolean z) {
        this.mInfo.setAccessibilityFocused(z);
    }

    public void setAvailableExtraData(List list0) {
        if(Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setAvailableExtraData(list0);
        }
    }

    private void setBooleanProperty(int v, boolean z) {
        Bundle bundle0 = this.getExtras();
        if(bundle0 != null) {
            int v1 = bundle0.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & ~v;
            if(!z) {
                v = 0;
            }
            bundle0.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", v | v1);
        }
    }

    @Deprecated
    public void setBoundsInParent(Rect rect0) {
        this.mInfo.setBoundsInParent(rect0);
    }

    public void setBoundsInScreen(Rect rect0) {
        this.mInfo.setBoundsInScreen(rect0);
    }

    public void setBoundsInWindow(Rect rect0) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setBoundsInWindow(this.mInfo, rect0);
            return;
        }
        this.mInfo.getExtras().putParcelable("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOUNDS_IN_WINDOW_KEY", rect0);
    }

    public void setCanOpenPopup(boolean z) {
        this.mInfo.setCanOpenPopup(z);
    }

    public void setCheckable(boolean z) {
        this.mInfo.setCheckable(z);
    }

    public void setChecked(boolean z) {
        this.mInfo.setChecked(z);
    }

    public void setClassName(CharSequence charSequence0) {
        this.mInfo.setClassName(charSequence0);
    }

    public void setClickable(boolean z) {
        this.mInfo.setClickable(z);
    }

    public void setCollectionInfo(Object object0) {
        this.mInfo.setCollectionInfo((object0 == null ? null : ((AccessibilityNodeInfo.CollectionInfo)((CollectionInfoCompat)object0).mInfo)));
    }

    public void setCollectionItemInfo(Object object0) {
        this.mInfo.setCollectionItemInfo((object0 == null ? null : ((AccessibilityNodeInfo.CollectionItemInfo)((CollectionItemInfoCompat)object0).mInfo)));
    }

    public void setContainerTitle(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setContainerTitle(this.mInfo, charSequence0);
            return;
        }
        this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.CONTAINER_TITLE_KEY", charSequence0);
    }

    public void setContentDescription(CharSequence charSequence0) {
        this.mInfo.setContentDescription(charSequence0);
    }

    public void setContentInvalid(boolean z) {
        this.mInfo.setContentInvalid(z);
    }

    public void setContextClickable(boolean z) {
        if(Build.VERSION.SDK_INT >= 23) {
            this.mInfo.setContextClickable(z);
        }
    }

    public void setDismissable(boolean z) {
        this.mInfo.setDismissable(z);
    }

    public void setDrawingOrder(int v) {
        if(Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setDrawingOrder(v);
        }
    }

    public void setEditable(boolean z) {
        this.mInfo.setEditable(z);
    }

    public void setEnabled(boolean z) {
        this.mInfo.setEnabled(z);
    }

    public void setError(CharSequence charSequence0) {
        this.mInfo.setError(charSequence0);
    }

    public void setFieldRequired(boolean z) {
        this.mInfo.getExtras().putBoolean("androidx.view.accessibility.AccessibilityNodeInfoCompat.IS_REQUIRED_KEY", z);
    }

    public void setFocusable(boolean z) {
        this.mInfo.setFocusable(z);
    }

    public void setFocused(boolean z) {
        this.mInfo.setFocused(z);
    }

    public void setGranularScrollingSupported(boolean z) {
        this.setBooleanProperty(0x4000000, z);
    }

    public void setHeading(boolean z) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setHeading(z);
            return;
        }
        this.setBooleanProperty(2, z);
    }

    public void setHintText(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setHintText(charSequence0);
            return;
        }
        this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence0);
    }

    public void setImportantForAccessibility(boolean z) {
        if(Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setImportantForAccessibility(z);
        }
    }

    public void setInputType(int v) {
        this.mInfo.setInputType(v);
    }

    public void setLabelFor(View view0) {
        this.mInfo.setLabelFor(view0);
    }

    public void setLabelFor(View view0, int v) {
        this.mInfo.setLabelFor(view0, v);
    }

    public void setLabeledBy(View view0) {
        this.mInfo.setLabeledBy(view0);
    }

    public void setLabeledBy(View view0, int v) {
        this.mInfo.setLabeledBy(view0, v);
    }

    public void setLiveRegion(int v) {
        this.mInfo.setLiveRegion(v);
    }

    public void setLongClickable(boolean z) {
        this.mInfo.setLongClickable(z);
    }

    public void setMaxTextLength(int v) {
        this.mInfo.setMaxTextLength(v);
    }

    public void setMinDurationBetweenContentChangesMillis(long v) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setMinDurationBetweenContentChangeMillis(this.mInfo, v);
            return;
        }
        this.mInfo.getExtras().putLong("androidx.view.accessibility.AccessibilityNodeInfoCompat.MIN_DURATION_BETWEEN_CONTENT_CHANGES_KEY", v);
    }

    public void setMovementGranularities(int v) {
        this.mInfo.setMovementGranularities(v);
    }

    public void setMultiLine(boolean z) {
        this.mInfo.setMultiLine(z);
    }

    public void setPackageName(CharSequence charSequence0) {
        this.mInfo.setPackageName(charSequence0);
    }

    public void setPaneTitle(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setPaneTitle(charSequence0);
            return;
        }
        this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence0);
    }

    public void setParent(View view0) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo.setParent(view0);
    }

    public void setParent(View view0, int v) {
        this.mParentVirtualDescendantId = v;
        this.mInfo.setParent(view0, v);
    }

    public void setPassword(boolean z) {
        this.mInfo.setPassword(z);
    }

    public void setQueryFromAppProcessEnabled(View view0, boolean z) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setQueryFromAppProcessEnabled(this.mInfo, view0, z);
        }
    }

    public void setRangeInfo(RangeInfoCompat accessibilityNodeInfoCompat$RangeInfoCompat0) {
        this.mInfo.setRangeInfo(((AccessibilityNodeInfo.RangeInfo)accessibilityNodeInfoCompat$RangeInfoCompat0.mInfo));
    }

    public void setRequestInitialAccessibilityFocus(boolean z) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setRequestInitialAccessibilityFocus(this.mInfo, z);
            return;
        }
        this.setBooleanProperty(0x20, z);
    }

    public void setRoleDescription(CharSequence charSequence0) {
        this.mInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence0);
    }

    public void setScreenReaderFocusable(boolean z) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setScreenReaderFocusable(z);
            return;
        }
        this.setBooleanProperty(1, z);
    }

    public void setScrollable(boolean z) {
        this.mInfo.setScrollable(z);
    }

    public void setSelected(boolean z) {
        this.mInfo.setSelected(z);
    }

    public void setShowingHintText(boolean z) {
        if(Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setShowingHintText(z);
            return;
        }
        this.setBooleanProperty(4, z);
    }

    public void setSource(View view0) {
        this.mVirtualDescendantId = -1;
        this.mInfo.setSource(view0);
    }

    public void setSource(View view0, int v) {
        this.mVirtualDescendantId = v;
        this.mInfo.setSource(view0, v);
    }

    public void setStateDescription(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setStateDescription(this.mInfo, charSequence0);
            return;
        }
        this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence0);
    }

    public void setText(CharSequence charSequence0) {
        this.mInfo.setText(charSequence0);
    }

    public void setTextEntryKey(boolean z) {
        if(Build.VERSION.SDK_INT >= 29) {
            this.mInfo.setTextEntryKey(z);
            return;
        }
        this.setBooleanProperty(8, z);
    }

    public void setTextSelectable(boolean z) {
        if(Build.VERSION.SDK_INT >= 33) {
            Api33Impl.setTextSelectable(this.mInfo, z);
            return;
        }
        this.setBooleanProperty(0x800000, z);
    }

    public void setTextSelection(int v, int v1) {
        this.mInfo.setTextSelection(v, v1);
    }

    public void setTooltipText(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setTooltipText(charSequence0);
            return;
        }
        this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY", charSequence0);
    }

    public void setTouchDelegateInfo(TouchDelegateInfoCompat accessibilityNodeInfoCompat$TouchDelegateInfoCompat0) {
        if(Build.VERSION.SDK_INT >= 29) {
            this.mInfo.setTouchDelegateInfo(accessibilityNodeInfoCompat$TouchDelegateInfoCompat0.mInfo);
        }
    }

    public void setTraversalAfter(View view0) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view0);
        }
    }

    public void setTraversalAfter(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view0, v);
        }
    }

    public void setTraversalBefore(View view0) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view0);
        }
    }

    public void setTraversalBefore(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view0, v);
        }
    }

    public void setUniqueId(String s) {
        if(Build.VERSION.SDK_INT >= 33) {
            Api33Impl.setUniqueId(this.mInfo, s);
            return;
        }
        this.mInfo.getExtras().putString("androidx.view.accessibility.AccessibilityNodeInfoCompat.UNIQUE_ID_KEY", s);
    }

    public void setViewIdResourceName(String s) {
        this.mInfo.setViewIdResourceName(s);
    }

    public void setVisibleToUser(boolean z) {
        this.mInfo.setVisibleToUser(z);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(super.toString());
        Rect rect0 = new Rect();
        this.getBoundsInParent(rect0);
        stringBuilder0.append("; boundsInParent: " + rect0);
        this.getBoundsInScreen(rect0);
        stringBuilder0.append("; boundsInScreen: " + rect0);
        this.getBoundsInWindow(rect0);
        stringBuilder0.append("; boundsInWindow: " + rect0);
        stringBuilder0.append("; packageName: ");
        stringBuilder0.append(this.getPackageName());
        stringBuilder0.append("; className: ");
        stringBuilder0.append(this.getClassName());
        stringBuilder0.append("; text: ");
        stringBuilder0.append(this.getText());
        stringBuilder0.append("; error: ");
        stringBuilder0.append(this.getError());
        stringBuilder0.append("; maxTextLength: ");
        stringBuilder0.append(this.getMaxTextLength());
        stringBuilder0.append("; stateDescription: ");
        stringBuilder0.append(this.getStateDescription());
        stringBuilder0.append("; contentDescription: ");
        stringBuilder0.append(this.getContentDescription());
        stringBuilder0.append("; tooltipText: ");
        stringBuilder0.append(this.getTooltipText());
        stringBuilder0.append("; viewIdResName: ");
        stringBuilder0.append(this.getViewIdResourceName());
        stringBuilder0.append("; uniqueId: ");
        stringBuilder0.append(this.getUniqueId());
        stringBuilder0.append("; checkable: ");
        stringBuilder0.append(this.isCheckable());
        stringBuilder0.append("; checked: ");
        stringBuilder0.append(this.isChecked());
        stringBuilder0.append("; fieldRequired: ");
        stringBuilder0.append(this.isFieldRequired());
        stringBuilder0.append("; focusable: ");
        stringBuilder0.append(this.isFocusable());
        stringBuilder0.append("; focused: ");
        stringBuilder0.append(this.isFocused());
        stringBuilder0.append("; selected: ");
        stringBuilder0.append(this.isSelected());
        stringBuilder0.append("; clickable: ");
        stringBuilder0.append(this.isClickable());
        stringBuilder0.append("; longClickable: ");
        stringBuilder0.append(this.isLongClickable());
        stringBuilder0.append("; contextClickable: ");
        stringBuilder0.append(this.isContextClickable());
        stringBuilder0.append("; enabled: ");
        stringBuilder0.append(this.isEnabled());
        stringBuilder0.append("; password: ");
        stringBuilder0.append(this.isPassword());
        stringBuilder0.append("; scrollable: " + this.isScrollable());
        stringBuilder0.append("; containerTitle: ");
        stringBuilder0.append(this.getContainerTitle());
        stringBuilder0.append("; granularScrollingSupported: ");
        stringBuilder0.append(this.isGranularScrollingSupported());
        stringBuilder0.append("; importantForAccessibility: ");
        stringBuilder0.append(this.isImportantForAccessibility());
        stringBuilder0.append("; visible: ");
        stringBuilder0.append(this.isVisibleToUser());
        stringBuilder0.append("; isTextSelectable: ");
        stringBuilder0.append(this.isTextSelectable());
        stringBuilder0.append("; accessibilityDataSensitive: ");
        stringBuilder0.append(this.isAccessibilityDataSensitive());
        stringBuilder0.append("; [");
        List list0 = this.getActionList();
        for(int v = 0; v < list0.size(); ++v) {
            AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0 = (AccessibilityActionCompat)list0.get(v);
            String s = AccessibilityNodeInfoCompat.getActionSymbolicName(accessibilityNodeInfoCompat$AccessibilityActionCompat0.getId());
            if(s.equals("ACTION_UNKNOWN") && accessibilityNodeInfoCompat$AccessibilityActionCompat0.getLabel() != null) {
                s = accessibilityNodeInfoCompat$AccessibilityActionCompat0.getLabel().toString();
            }
            stringBuilder0.append(s);
            if(v != list0.size() - 1) {
                stringBuilder0.append(", ");
            }
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat wrap(AccessibilityNodeInfo accessibilityNodeInfo0) {
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo0);
    }

    static AccessibilityNodeInfoCompat wrapNonNullInstance(Object object0) {
        return object0 == null ? null : new AccessibilityNodeInfoCompat(object0);
    }
}

