package com.google.android.material.internal;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckableGroup {
    public interface OnCheckedStateChangeListener {
        void onCheckedStateChanged(Set arg1);
    }

    private final Map checkables;
    private final Set checkedIds;
    private OnCheckedStateChangeListener onCheckedStateChangeListener;
    private boolean selectionRequired;
    private boolean singleSelection;

    public CheckableGroup() {
        this.checkables = new HashMap();
        this.checkedIds = new HashSet();
    }

    public void addCheckable(MaterialCheckable materialCheckable0) {
        Integer integer0 = materialCheckable0.getId();
        this.checkables.put(integer0, materialCheckable0);
        if(materialCheckable0.isChecked()) {
            this.checkInternal(materialCheckable0);
        }
        materialCheckable0.setInternalOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(MaterialCheckable materialCheckable0, boolean z) {
                if(z) {
                    if(CheckableGroup.this.checkInternal(materialCheckable0)) {
                        CheckableGroup.this.onCheckedStateChanged();
                    }
                }
                else if(CheckableGroup.this.uncheckInternal(materialCheckable0, CheckableGroup.this.selectionRequired)) {
                    CheckableGroup.this.onCheckedStateChanged();
                }
            }

            @Override  // com.google.android.material.internal.MaterialCheckable$OnCheckedChangeListener
            public void onCheckedChanged(Object object0, boolean z) {
                this.onCheckedChanged(((MaterialCheckable)object0), z);
            }
        });
    }

    public void check(int v) {
        MaterialCheckable materialCheckable0 = (MaterialCheckable)this.checkables.get(v);
        if(materialCheckable0 != null && this.checkInternal(materialCheckable0)) {
            this.onCheckedStateChanged();
        }
    }

    private boolean checkInternal(MaterialCheckable materialCheckable0) {
        int v = materialCheckable0.getId();
        if(this.checkedIds.contains(v)) {
            return false;
        }
        Integer integer0 = this.getSingleCheckedId();
        MaterialCheckable materialCheckable1 = (MaterialCheckable)this.checkables.get(integer0);
        if(materialCheckable1 != null) {
            this.uncheckInternal(materialCheckable1, false);
        }
        boolean z = this.checkedIds.add(v);
        if(!materialCheckable0.isChecked()) {
            materialCheckable0.setChecked(true);
        }
        return z;
    }

    public void clearCheck() {
        boolean z = this.checkedIds.isEmpty();
        for(Object object0: this.checkables.values()) {
            this.uncheckInternal(((MaterialCheckable)object0), false);
        }
        if(!z) {
            this.onCheckedStateChanged();
        }
    }

    public Set getCheckedIds() {
        return new HashSet(this.checkedIds);
    }

    public List getCheckedIdsSortedByChildOrder(ViewGroup viewGroup0) {
        Set set0 = this.getCheckedIds();
        List list0 = new ArrayList();
        for(int v = 0; v < viewGroup0.getChildCount(); ++v) {
            View view0 = viewGroup0.getChildAt(v);
            if(view0 instanceof MaterialCheckable && set0.contains(view0.getId())) {
                list0.add(view0.getId());
            }
        }
        return list0;
    }

    public int getSingleCheckedId() {
        return this.singleSelection && !this.checkedIds.isEmpty() ? this.checkedIds.iterator().next() : -1;
    }

    public boolean isSelectionRequired() {
        return this.selectionRequired;
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    private void onCheckedStateChanged() {
        OnCheckedStateChangeListener checkableGroup$OnCheckedStateChangeListener0 = this.onCheckedStateChangeListener;
        if(checkableGroup$OnCheckedStateChangeListener0 != null) {
            checkableGroup$OnCheckedStateChangeListener0.onCheckedStateChanged(this.getCheckedIds());
        }
    }

    public void removeCheckable(MaterialCheckable materialCheckable0) {
        materialCheckable0.setInternalOnCheckedChangeListener(null);
        Integer integer0 = materialCheckable0.getId();
        this.checkables.remove(integer0);
        Integer integer1 = materialCheckable0.getId();
        this.checkedIds.remove(integer1);
    }

    public void setOnCheckedStateChangeListener(OnCheckedStateChangeListener checkableGroup$OnCheckedStateChangeListener0) {
        this.onCheckedStateChangeListener = checkableGroup$OnCheckedStateChangeListener0;
    }

    public void setSelectionRequired(boolean z) {
        this.selectionRequired = z;
    }

    public void setSingleSelection(boolean z) {
        if(this.singleSelection != z) {
            this.singleSelection = z;
            this.clearCheck();
        }
    }

    public void uncheck(int v) {
        MaterialCheckable materialCheckable0 = (MaterialCheckable)this.checkables.get(v);
        if(materialCheckable0 != null && this.uncheckInternal(materialCheckable0, this.selectionRequired)) {
            this.onCheckedStateChanged();
        }
    }

    private boolean uncheckInternal(MaterialCheckable materialCheckable0, boolean z) {
        int v = materialCheckable0.getId();
        if(!this.checkedIds.contains(v)) {
            return false;
        }
        if(z && this.checkedIds.size() == 1 && this.checkedIds.contains(v)) {
            materialCheckable0.setChecked(true);
            return false;
        }
        boolean z1 = this.checkedIds.remove(v);
        if(materialCheckable0.isChecked()) {
            materialCheckable0.setChecked(false);
        }
        return z1;
    }
}

