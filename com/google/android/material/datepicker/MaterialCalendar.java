package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.integer;
import com.google.android.material.R.layout;
import com.google.android.material.R.string;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;

public final class MaterialCalendar extends PickerFragment {
    static enum CalendarSelector {
        DAY,
        YEAR;

    }

    interface OnDayClickListener {
        void onDayClick(long arg1);
    }

    private static final String CALENDAR_CONSTRAINTS_KEY = "CALENDAR_CONSTRAINTS_KEY";
    private static final String CURRENT_MONTH_KEY = "CURRENT_MONTH_KEY";
    private static final String DAY_VIEW_DECORATOR_KEY = "DAY_VIEW_DECORATOR_KEY";
    private static final String GRID_SELECTOR_KEY = "GRID_SELECTOR_KEY";
    static final Object MONTHS_VIEW_GROUP_TAG = null;
    static final Object NAVIGATION_NEXT_TAG = null;
    static final Object NAVIGATION_PREV_TAG = null;
    static final Object SELECTOR_TOGGLE_TAG = null;
    private static final int SMOOTH_SCROLL_MAX = 3;
    private static final String THEME_RES_ID_KEY = "THEME_RES_ID_KEY";
    private CalendarConstraints calendarConstraints;
    private CalendarSelector calendarSelector;
    private CalendarStyle calendarStyle;
    private Month current;
    private DateSelector dateSelector;
    private View dayFrame;
    private DayViewDecorator dayViewDecorator;
    private View monthNext;
    private View monthPrev;
    private RecyclerView recyclerView;
    private int themeResId;
    private View yearFrame;
    private RecyclerView yearSelector;

    static {
        MaterialCalendar.MONTHS_VIEW_GROUP_TAG = "MONTHS_VIEW_GROUP_TAG";
        MaterialCalendar.NAVIGATION_PREV_TAG = "NAVIGATION_PREV_TAG";
        MaterialCalendar.NAVIGATION_NEXT_TAG = "NAVIGATION_NEXT_TAG";
        MaterialCalendar.SELECTOR_TOGGLE_TAG = "SELECTOR_TOGGLE_TAG";
    }

    private void addActionsToMonthNavigation(View view0, MonthsPagerAdapter monthsPagerAdapter0) {
        MaterialButton materialButton0 = (MaterialButton)view0.findViewById(id.month_navigation_fragment_toggle);
        materialButton0.setTag(MaterialCalendar.SELECTOR_TOGGLE_TAG);
        ViewCompat.setAccessibilityDelegate(materialButton0, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setHintText((MaterialCalendar.this.dayFrame.getVisibility() == 0 ? MaterialCalendar.this.getString(string.mtrl_picker_toggle_to_year_selection) : MaterialCalendar.this.getString(string.mtrl_picker_toggle_to_day_selection)));
            }
        });
        View view1 = view0.findViewById(id.month_navigation_previous);
        this.monthPrev = view1;
        view1.setTag(MaterialCalendar.NAVIGATION_PREV_TAG);
        View view2 = view0.findViewById(id.month_navigation_next);
        this.monthNext = view2;
        view2.setTag(MaterialCalendar.NAVIGATION_NEXT_TAG);
        this.yearFrame = view0.findViewById(id.mtrl_calendar_year_selector_frame);
        this.dayFrame = view0.findViewById(id.mtrl_calendar_day_selector_frame);
        this.setSelector(CalendarSelector.DAY);
        materialButton0.setText(this.current.getLongName());
        this.recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView0, int v) {
                if(v == 0) {
                    recyclerView0.announceForAccessibility(materialButton0.getText());
                }
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
            public void onScrolled(RecyclerView recyclerView0, int v, int v1) {
                int v2 = v >= 0 ? MaterialCalendar.this.getLayoutManager().findLastVisibleItemPosition() : MaterialCalendar.this.getLayoutManager().findFirstVisibleItemPosition();
                Month month0 = monthsPagerAdapter0.getPageMonth(v2);
                MaterialCalendar.this.current = month0;
                CharSequence charSequence0 = monthsPagerAdapter0.getPageTitle(v2);
                materialButton0.setText(charSequence0);
            }
        });
        materialButton0.setOnClickListener((/* 缺少LAMBDA参数 */) -> {
            if(MaterialCalendar.this.calendarSelector == CalendarSelector.YEAR) {
                MaterialCalendar.this.setSelector(CalendarSelector.DAY);
                return;
            }
            if(MaterialCalendar.this.calendarSelector == CalendarSelector.DAY) {
                MaterialCalendar.this.setSelector(CalendarSelector.YEAR);
            }
        });
        this.monthNext.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                int v = MaterialCalendar.this.getLayoutManager().findFirstVisibleItemPosition();
                if(v + 1 < MaterialCalendar.this.recyclerView.getAdapter().getItemCount()) {
                    Month month0 = monthsPagerAdapter0.getPageMonth(v + 1);
                    MaterialCalendar.this.setCurrentMonth(month0);
                }
            }
        });
        this.monthPrev.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                int v = MaterialCalendar.this.getLayoutManager().findLastVisibleItemPosition();
                if(v - 1 >= 0) {
                    Month month0 = monthsPagerAdapter0.getPageMonth(v - 1);
                    MaterialCalendar.this.setCurrentMonth(month0);
                }
            }
        });

        class com.google.android.material.datepicker.MaterialCalendar.8 implements View.OnClickListener {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                MaterialCalendar.this.toggleVisibleSelector();
            }
        }

    }

    @Override  // com.google.android.material.datepicker.PickerFragment
    public boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener0) {
        return super.addOnSelectionChangedListener(onSelectionChangedListener0);
    }

    private ItemDecoration createItemDecoration() {
        return new ItemDecoration() {
            private final Calendar endItem;
            private final Calendar startItem;

            {
                this.startItem = UtcDates.getUtcCalendar();
                this.endItem = UtcDates.getUtcCalendar();
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
            public void onDraw(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
                if(recyclerView0.getAdapter() instanceof YearGridAdapter && recyclerView0.getLayoutManager() instanceof GridLayoutManager) {
                    YearGridAdapter yearGridAdapter0 = (YearGridAdapter)recyclerView0.getAdapter();
                    GridLayoutManager gridLayoutManager0 = (GridLayoutManager)recyclerView0.getLayoutManager();
                    for(Object object0: MaterialCalendar.this.dateSelector.getSelectedRanges()) {
                        Pair pair0 = (Pair)object0;
                        if(pair0.first != null && pair0.second != null) {
                            this.startItem.setTimeInMillis(((long)(((Long)pair0.first))));
                            this.endItem.setTimeInMillis(((long)(((Long)pair0.second))));
                            int v = yearGridAdapter0.getPositionForYear(this.startItem.get(1));
                            int v1 = yearGridAdapter0.getPositionForYear(this.endItem.get(1));
                            View view0 = gridLayoutManager0.findViewByPosition(v);
                            View view1 = gridLayoutManager0.findViewByPosition(v1);
                            int v2 = v / gridLayoutManager0.getSpanCount();
                            int v3 = v1 / gridLayoutManager0.getSpanCount();
                            for(int v4 = v2; v4 <= v3; ++v4) {
                                View view2 = gridLayoutManager0.findViewByPosition(gridLayoutManager0.getSpanCount() * v4);
                                if(view2 != null) {
                                    int v5 = view2.getTop();
                                    int v6 = view2.getBottom();
                                    canvas0.drawRect(((float)(v4 != v2 || view0 == null ? 0 : view0.getLeft() + view0.getWidth() / 2)), ((float)(v5 + MaterialCalendar.this.calendarStyle.year.getTopInset())), ((float)(v4 != v3 || view1 == null ? recyclerView0.getWidth() : view1.getLeft() + view1.getWidth() / 2)), ((float)(v6 - MaterialCalendar.this.calendarStyle.year.getBottomInset())), MaterialCalendar.this.calendarStyle.rangeFill);
                                }
                            }
                        }
                    }
                }
            }
        };
    }

    CalendarConstraints getCalendarConstraints() {
        return this.calendarConstraints;
    }

    CalendarStyle getCalendarStyle() {
        return this.calendarStyle;
    }

    Month getCurrentMonth() {
        return this.current;
    }

    @Override  // com.google.android.material.datepicker.PickerFragment
    public DateSelector getDateSelector() {
        return this.dateSelector;
    }

    static int getDayHeight(Context context0) {
        return context0.getResources().getDimensionPixelSize(dimen.mtrl_calendar_day_height);
    }

    private static int getDialogPickerHeight(Context context0) {
        Resources resources0 = context0.getResources();
        int v = resources0.getDimensionPixelSize(dimen.mtrl_calendar_navigation_height);
        int v1 = resources0.getDimensionPixelOffset(dimen.mtrl_calendar_navigation_top_padding);
        int v2 = resources0.getDimensionPixelOffset(dimen.mtrl_calendar_navigation_bottom_padding);
        int v3 = resources0.getDimensionPixelSize(dimen.mtrl_calendar_days_of_week_height);
        int v4 = resources0.getDimensionPixelSize(dimen.mtrl_calendar_day_height);
        int v5 = resources0.getDimensionPixelOffset(dimen.mtrl_calendar_month_vertical_padding);
        int v6 = resources0.getDimensionPixelOffset(dimen.mtrl_calendar_bottom_padding);
        return v + v1 + v2 + v3 + (MonthAdapter.MAXIMUM_WEEKS * v4 + (MonthAdapter.MAXIMUM_WEEKS - 1) * v5) + v6;
    }

    LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager)this.recyclerView.getLayoutManager();
    }

    public static MaterialCalendar newInstance(DateSelector dateSelector0, int v, CalendarConstraints calendarConstraints0) {
        return MaterialCalendar.newInstance(dateSelector0, v, calendarConstraints0, null);
    }

    public static MaterialCalendar newInstance(DateSelector dateSelector0, int v, CalendarConstraints calendarConstraints0, DayViewDecorator dayViewDecorator0) {
        MaterialCalendar materialCalendar0 = new MaterialCalendar();
        Bundle bundle0 = new Bundle();
        bundle0.putInt("THEME_RES_ID_KEY", v);
        bundle0.putParcelable("GRID_SELECTOR_KEY", dateSelector0);
        bundle0.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints0);
        bundle0.putParcelable("DAY_VIEW_DECORATOR_KEY", dayViewDecorator0);
        bundle0.putParcelable("CURRENT_MONTH_KEY", calendarConstraints0.getOpenAt());
        materialCalendar0.setArguments(bundle0);
        return materialCalendar0;
    }

    @Override  // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        if(bundle0 == null) {
            bundle0 = this.getArguments();
        }
        this.themeResId = bundle0.getInt("THEME_RES_ID_KEY");
        this.dateSelector = (DateSelector)bundle0.getParcelable("GRID_SELECTOR_KEY");
        this.calendarConstraints = (CalendarConstraints)bundle0.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.dayViewDecorator = (DayViewDecorator)bundle0.getParcelable("DAY_VIEW_DECORATOR_KEY");
        this.current = (Month)bundle0.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override  // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        int v1;
        int v;
        ContextThemeWrapper contextThemeWrapper0 = new ContextThemeWrapper(this.getContext(), this.themeResId);
        this.calendarStyle = new CalendarStyle(contextThemeWrapper0);
        LayoutInflater layoutInflater1 = layoutInflater0.cloneInContext(contextThemeWrapper0);
        Month month0 = this.calendarConstraints.getStart();
        if(MaterialDatePicker.isFullscreen(contextThemeWrapper0)) {
            v = layout.mtrl_calendar_vertical;
            v1 = 1;
        }
        else {
            v = layout.mtrl_calendar_horizontal;
            v1 = 0;
        }
        View view0 = layoutInflater1.inflate(v, viewGroup0, false);
        view0.setMinimumHeight(MaterialCalendar.getDialogPickerHeight(this.requireContext()));
        GridView gridView0 = (GridView)view0.findViewById(id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(gridView0, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setCollectionInfo(null);
            }
        });
        int v2 = this.calendarConstraints.getFirstDayOfWeek();
        gridView0.setAdapter((v2 <= 0 ? new DaysOfWeekAdapter() : new DaysOfWeekAdapter(v2)));
        gridView0.setNumColumns(month0.daysInWeek);
        gridView0.setEnabled(false);
        this.recyclerView = (RecyclerView)view0.findViewById(id.mtrl_calendar_months);
        com.google.android.material.datepicker.MaterialCalendar.2 materialCalendar$20 = new SmoothCalendarLayoutManager(this.getContext(), v1, false) {
            @Override  // androidx.recyclerview.widget.LinearLayoutManager
            protected void calculateExtraLayoutSpace(State recyclerView$State0, int[] arr_v) {
                if(v1 == 0) {
                    arr_v[0] = MaterialCalendar.this.recyclerView.getWidth();
                    arr_v[1] = MaterialCalendar.this.recyclerView.getWidth();
                    return;
                }
                arr_v[0] = MaterialCalendar.this.recyclerView.getHeight();
                arr_v[1] = MaterialCalendar.this.recyclerView.getHeight();
            }
        };
        this.recyclerView.setLayoutManager(materialCalendar$20);
        this.recyclerView.setTag(MaterialCalendar.MONTHS_VIEW_GROUP_TAG);
        MonthsPagerAdapter monthsPagerAdapter0 = new MonthsPagerAdapter(contextThemeWrapper0, this.dateSelector, this.calendarConstraints, this.dayViewDecorator, new OnDayClickListener() {
            @Override  // com.google.android.material.datepicker.MaterialCalendar$OnDayClickListener
            public void onDayClick(long v) {
                if(MaterialCalendar.this.calendarConstraints.getDateValidator().isValid(v)) {
                    MaterialCalendar.this.dateSelector.select(v);
                    for(Object object0: MaterialCalendar.this.onSelectionChangedListeners) {
                        ((OnSelectionChangedListener)object0).onSelectionChanged(MaterialCalendar.this.dateSelector.getSelection());
                    }
                    MaterialCalendar.this.recyclerView.getAdapter().notifyDataSetChanged();
                    if(MaterialCalendar.this.yearSelector != null) {
                        MaterialCalendar.this.yearSelector.getAdapter().notifyDataSetChanged();
                    }
                }
            }
        });
        this.recyclerView.setAdapter(monthsPagerAdapter0);
        int v3 = contextThemeWrapper0.getResources().getInteger(integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView0 = (RecyclerView)view0.findViewById(id.mtrl_calendar_year_selector_frame);
        this.yearSelector = recyclerView0;
        if(recyclerView0 != null) {
            recyclerView0.setHasFixedSize(true);
            this.yearSelector.setLayoutManager(new GridLayoutManager(contextThemeWrapper0, v3, 1, false));
            this.yearSelector.setAdapter(new YearGridAdapter(this));
            this.yearSelector.addItemDecoration(this.createItemDecoration());
        }
        if(view0.findViewById(id.month_navigation_fragment_toggle) != null) {
            this.addActionsToMonthNavigation(view0, monthsPagerAdapter0);
        }
        if(!MaterialDatePicker.isFullscreen(contextThemeWrapper0)) {
            new PagerSnapHelper().attachToRecyclerView(this.recyclerView);
        }
        this.recyclerView.scrollToPosition(monthsPagerAdapter0.getPosition(this.current));
        this.setUpForAccessibility();
        return view0;
    }

    @Override  // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        bundle0.putInt("THEME_RES_ID_KEY", this.themeResId);
        bundle0.putParcelable("GRID_SELECTOR_KEY", this.dateSelector);
        bundle0.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.calendarConstraints);
        bundle0.putParcelable("DAY_VIEW_DECORATOR_KEY", this.dayViewDecorator);
        bundle0.putParcelable("CURRENT_MONTH_KEY", this.current);
    }

    private void postSmoothRecyclerViewScroll(int v) {
        this.recyclerView.post(new Runnable() {
            @Override
            public void run() {
                MaterialCalendar.this.recyclerView.smoothScrollToPosition(v);
            }
        });
    }

    void setCurrentMonth(Month month0) {
        MonthsPagerAdapter monthsPagerAdapter0 = (MonthsPagerAdapter)this.recyclerView.getAdapter();
        int v = monthsPagerAdapter0.getPosition(month0);
        int v1 = v - monthsPagerAdapter0.getPosition(this.current);
        boolean z = true;
        boolean z1 = Math.abs(v1) > 3;
        if(v1 <= 0) {
            z = false;
        }
        this.current = month0;
        if(z1 && z) {
            this.recyclerView.scrollToPosition(v - 3);
            this.postSmoothRecyclerViewScroll(v);
            return;
        }
        if(z1) {
            this.recyclerView.scrollToPosition(v + 3);
            this.postSmoothRecyclerViewScroll(v);
            return;
        }
        this.postSmoothRecyclerViewScroll(v);
    }

    void setSelector(CalendarSelector materialCalendar$CalendarSelector0) {
        this.calendarSelector = materialCalendar$CalendarSelector0;
        if(materialCalendar$CalendarSelector0 == CalendarSelector.YEAR) {
            this.yearSelector.getLayoutManager().scrollToPosition(((YearGridAdapter)this.yearSelector.getAdapter()).getPositionForYear(this.current.year));
            this.yearFrame.setVisibility(0);
            this.dayFrame.setVisibility(8);
            this.monthPrev.setVisibility(8);
            this.monthNext.setVisibility(8);
            return;
        }
        if(materialCalendar$CalendarSelector0 == CalendarSelector.DAY) {
            this.yearFrame.setVisibility(8);
            this.dayFrame.setVisibility(0);
            this.monthPrev.setVisibility(0);
            this.monthNext.setVisibility(0);
            this.setCurrentMonth(this.current);
        }
    }

    private void setUpForAccessibility() {
        ViewCompat.setAccessibilityDelegate(this.recyclerView, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setScrollable(false);
            }
        });
    }

    // 检测为 Lambda 实现
    void toggleVisibleSelector() [...]
}

