// Generated by view binder compiler. Do not edit!
package com.example.pill_box.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityCalendarioBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button AccederFecha;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final Button buttonVolver;

  @NonNull
  public final CalendarView calendarView;

  private ActivityCalendarioBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button AccederFecha, @NonNull BottomNavigationView bottomNavigation,
      @NonNull Button buttonVolver, @NonNull CalendarView calendarView) {
    this.rootView = rootView;
    this.AccederFecha = AccederFecha;
    this.bottomNavigation = bottomNavigation;
    this.buttonVolver = buttonVolver;
    this.calendarView = calendarView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCalendarioBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCalendarioBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_calendario, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCalendarioBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.AccederFecha;
      Button AccederFecha = ViewBindings.findChildViewById(rootView, id);
      if (AccederFecha == null) {
        break missingId;
      }

      id = R.id.bottom_navigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      id = R.id.buttonVolver;
      Button buttonVolver = ViewBindings.findChildViewById(rootView, id);
      if (buttonVolver == null) {
        break missingId;
      }

      id = R.id.calendarView;
      CalendarView calendarView = ViewBindings.findChildViewById(rootView, id);
      if (calendarView == null) {
        break missingId;
      }

      return new ActivityCalendarioBinding((ConstraintLayout) rootView, AccederFecha,
          bottomNavigation, buttonVolver, calendarView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
