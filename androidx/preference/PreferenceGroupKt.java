package androidx.preference;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0003\u001A\u0015\u0010\n\u001A\u00020\u000B*\u00020\u00032\u0006\u0010\f\u001A\u00020\u0002H\u0086\u0002\u001A3\u0010\r\u001A\u00020\u000E*\u00020\u00032!\u0010\u000F\u001A\u001D\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000E0\u0010H\u0086\bø\u0001\u0000\u001AH\u0010\u0013\u001A\u00020\u000E*\u00020\u000326\u0010\u000F\u001A2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000E0\u0014H\u0086\bø\u0001\u0000\u001A&\u0010\u0016\u001A\u0004\u0018\u0001H\u0017\"\b\b\u0000\u0010\u0017*\u00020\u0002*\u00020\u00032\u0006\u0010\u0018\u001A\u00020\u0019H\u0086\n¢\u0006\u0002\u0010\u001A\u001A\u0015\u0010\u0016\u001A\u00020\u0002*\u00020\u00032\u0006\u0010\u0015\u001A\u00020\u0007H\u0086\u0002\u001A\r\u0010\u001B\u001A\u00020\u000B*\u00020\u0003H\u0086\b\u001A\r\u0010\u001C\u001A\u00020\u000B*\u00020\u0003H\u0086\b\u001A\u0013\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00020\u001E*\u00020\u0003H\u0086\u0002\u001A\u0015\u0010\u001F\u001A\u00020\u000E*\u00020\u00032\u0006\u0010\f\u001A\u00020\u0002H\u0086\n\u001A\u0015\u0010 \u001A\u00020\u000E*\u00020\u00032\u0006\u0010\f\u001A\u00020\u0002H\u0086\n\"\u001B\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005\"\u0016\u0010\u0006\u001A\u00020\u0007*\u00020\u00038Æ\u0002¢\u0006\u0006\u001A\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006!"}, d2 = {"children", "Lkotlin/sequences/Sequence;", "Landroidx/preference/Preference;", "Landroidx/preference/PreferenceGroup;", "getChildren", "(Landroidx/preference/PreferenceGroup;)Lkotlin/sequences/Sequence;", "size", "", "getSize", "(Landroidx/preference/PreferenceGroup;)I", "contains", "", "preference", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function2;", "index", "get", "T", "key", "", "(Landroidx/preference/PreferenceGroup;Ljava/lang/CharSequence;)Landroidx/preference/Preference;", "isEmpty", "isNotEmpty", "iterator", "", "minusAssign", "plusAssign", "preference-ktx_release"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class PreferenceGroupKt {
    public static final boolean contains(PreferenceGroup preferenceGroup0, Preference preference0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        Intrinsics.checkNotNullParameter(preference0, "preference");
        int v = preferenceGroup0.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            if(Intrinsics.areEqual(preferenceGroup0.getPreference(v1), preference0)) {
                return true;
            }
        }
        return false;
    }

    public static final void forEach(PreferenceGroup preferenceGroup0, Function1 function10) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "action");
        int v = preferenceGroup0.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            function10.invoke(PreferenceGroupKt.get(preferenceGroup0, v1));
        }
    }

    public static final void forEachIndexed(PreferenceGroup preferenceGroup0, Function2 function20) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "action");
        int v = preferenceGroup0.getPreferenceCount();
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(v1, PreferenceGroupKt.get(preferenceGroup0, v1));
        }
    }

    public static final Preference get(PreferenceGroup preferenceGroup0, int v) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        Preference preference0 = preferenceGroup0.getPreference(v);
        Intrinsics.checkNotNullExpressionValue(preference0, "getPreference(index)");
        return preference0;
    }

    public static final Preference get(PreferenceGroup preferenceGroup0, CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "key");
        return preferenceGroup0.findPreference(charSequence0);
    }

    public static final Sequence getChildren(PreferenceGroup preferenceGroup0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        return () -> {
            Intrinsics.checkNotNullParameter(this.$this_children, "<this>");
            return new Object() {
                private int index;

                @Override
                public boolean hasNext() {
                    return this.index < this.$this_children.getPreferenceCount();
                }

                public Preference next() {
                    int v = this.index;
                    this.index = v + 1;
                    Preference preference0 = this.$this_children.getPreference(v);
                    Intrinsics.checkNotNullExpressionValue(preference0, "getPreference(index++)");
                    return preference0;
                }

                @Override
                public Object next() {
                    return this.next();
                }

                @Override
                public void remove() {
                    int v = this.index - 1;
                    this.index = v;
                    Preference preference0 = this.$this_children.getPreference(v);
                    this.$this_children.removePreference(preference0);
                }
            };
        };

        @Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u000F\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0096\u0002¨\u0006\u0005"}, d2 = {"androidx/preference/PreferenceGroupKt$children$1", "Lkotlin/sequences/Sequence;", "Landroidx/preference/Preference;", "iterator", "", "preference-ktx_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
        public final class androidx.preference.PreferenceGroupKt.children.1 implements Sequence {
            androidx.preference.PreferenceGroupKt.children.1(PreferenceGroup preferenceGroup0) {
            }

            @Override  // kotlin.sequences.Sequence
            public Iterator iterator() {
                return PreferenceGroupKt.iterator(this.$this_children);
            }
        }

    }

    public static final int getSize(PreferenceGroup preferenceGroup0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        return preferenceGroup0.getPreferenceCount();
    }

    public static final boolean isEmpty(PreferenceGroup preferenceGroup0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        return preferenceGroup0.getPreferenceCount() == 0;
    }

    public static final boolean isNotEmpty(PreferenceGroup preferenceGroup0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        return preferenceGroup0.getPreferenceCount() != 0;
    }

    // 检测为 Lambda 实现
    public static final Iterator iterator(PreferenceGroup preferenceGroup0) [...]

    public static final void minusAssign(PreferenceGroup preferenceGroup0, Preference preference0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        Intrinsics.checkNotNullParameter(preference0, "preference");
        preferenceGroup0.removePreference(preference0);
    }

    public static final void plusAssign(PreferenceGroup preferenceGroup0, Preference preference0) {
        Intrinsics.checkNotNullParameter(preferenceGroup0, "<this>");
        Intrinsics.checkNotNullParameter(preference0, "preference");
        preferenceGroup0.addPreference(preference0);
    }
}

