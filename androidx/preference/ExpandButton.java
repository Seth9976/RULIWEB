package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

final class ExpandButton extends Preference {
    private long mId;

    ExpandButton(Context context0, List list0, long v) {
        super(context0);
        this.initLayout();
        this.setSummary(list0);
        this.mId = v + 1000000L;
    }

    @Override  // androidx.preference.Preference
    long getId() {
        return this.mId;
    }

    private void initLayout() {
        this.setLayoutResource(layout.expand_button);
        this.setIcon(drawable.ic_arrow_down_24dp);
        this.setTitle(string.expand_button_title);
        this.setOrder(999);
    }

    @Override  // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder0) {
        super.onBindViewHolder(preferenceViewHolder0);
        preferenceViewHolder0.setDividerAllowedAbove(false);
    }

    private void setSummary(List list0) {
        ArrayList arrayList0 = new ArrayList();
        CharSequence charSequence0 = null;
        for(Object object0: list0) {
            Preference preference0 = (Preference)object0;
            CharSequence charSequence1 = preference0.getTitle();
            boolean z = preference0 instanceof PreferenceGroup;
            if(z && !TextUtils.isEmpty(charSequence1)) {
                arrayList0.add(((PreferenceGroup)preference0));
            }
            if(arrayList0.contains(preference0.getParent())) {
                if(!z) {
                    continue;
                }
                arrayList0.add(((PreferenceGroup)preference0));
            }
            else if(!TextUtils.isEmpty(charSequence1)) {
                charSequence0 = charSequence0 == null ? charSequence1 : this.getContext().getString(string.summary_collapsed_preference_list, new Object[]{charSequence0, charSequence1});
            }
        }
        this.setSummary(charSequence0);
    }
}

