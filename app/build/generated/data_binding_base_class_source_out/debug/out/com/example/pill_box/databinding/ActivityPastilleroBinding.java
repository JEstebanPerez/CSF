// Generated by view binder compiler. Do not edit!
package com.example.pill_box.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pill_box.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPastilleroBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button botonAAdirPastilla;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final ListView listaPastillas;

  @NonNull
  public final RelativeLayout relativeLayout;

  private ActivityPastilleroBinding(@NonNull RelativeLayout rootView,
      @NonNull Button botonAAdirPastilla, @NonNull BottomNavigationView bottomNavigation,
      @NonNull ListView listaPastillas, @NonNull RelativeLayout relativeLayout) {
    this.rootView = rootView;
    this.botonAAdirPastilla = botonAAdirPastilla;
    this.bottomNavigation = bottomNavigation;
    this.listaPastillas = listaPastillas;
    this.relativeLayout = relativeLayout;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPastilleroBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPastilleroBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_pastillero, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPastilleroBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.botonAñadirPastilla;
      Button botonAAdirPastilla = ViewBindings.findChildViewById(rootView, id);
      if (botonAAdirPastilla == null) {
        break missingId;
      }

      id = R.id.bottom_navigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      id = R.id.listaPastillas;
      ListView listaPastillas = ViewBindings.findChildViewById(rootView, id);
      if (listaPastillas == null) {
        break missingId;
      }

      RelativeLayout relativeLayout = (RelativeLayout) rootView;

      return new ActivityPastilleroBinding((RelativeLayout) rootView, botonAAdirPastilla,
          bottomNavigation, listaPastillas, relativeLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
