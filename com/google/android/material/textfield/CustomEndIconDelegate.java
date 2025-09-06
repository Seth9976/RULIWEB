package com.google.android.material.textfield;

class CustomEndIconDelegate extends EndIconDelegate {
    CustomEndIconDelegate(EndCompoundLayout endCompoundLayout0) {
        super(endCompoundLayout0);
    }

    @Override  // com.google.android.material.textfield.EndIconDelegate
    void setUp() {
        this.endLayout.setEndIconOnLongClickListener(null);
    }
}

