package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.helpers.Facade;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

public interface Reference {
    void apply();

    ConstraintWidget getConstraintWidget();

    Facade getFacade();

    Object getKey();

    void setConstraintWidget(ConstraintWidget arg1);

    void setKey(Object arg1);
}

