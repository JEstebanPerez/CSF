// Generated by view binder compiler. Do not edit!
package com.example.pill_box.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public final class ActivityCitasCreatorBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final BottomNavigationView bottomNavigation;

  @NonNull
  public final Button button2;

  @NonNull
  public final Button button4;

  @NonNull
  public final Button buttonFecha;

  @NonNull
  public final EditText descripcionCita;

  @NonNull
  public final TextView fechaView;

  @NonNull
  public final TextView horaCita;

  @NonNull
  public final EditText nombreCita;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textoFecha;

  private ActivityCitasCreatorBinding(@NonNull ConstraintLayout rootView,
      @NonNull BottomNavigationView bottomNavigation, @NonNull Button button2,
      @NonNull Button button4, @NonNull Button buttonFecha, @NonNull EditText descripcionCita,
      @NonNull TextView fechaView, @NonNull TextView horaCita, @NonNull EditText nombreCita,
      @NonNull TextView textView2, @NonNull TextView textoFecha) {
    this.rootView = rootView;
    this.bottomNavigation = bottomNavigation;
    this.button2 = button2;
    this.button4 = button4;
    this.buttonFecha = buttonFecha;
    this.descripcionCita = descripcionCita;
    this.fechaView = fechaView;
    this.horaCita = horaCita;
    this.nombreCita = nombreCita;
    this.textView2 = textView2;
    this.textoFecha = textoFecha;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityCitasCreatorBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityCitasCreatorBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_citas_creator, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityCitasCreatorBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottom_navigation;
      BottomNavigationView bottomNavigation = ViewBindings.findChildViewById(rootView, id);
      if (bottomNavigation == null) {
        break missingId;
      }

      id = R.id.button2;
      Button button2 = ViewBindings.findChildViewById(rootView, id);
      if (button2 == null) {
        break missingId;
      }

      id = R.id.button4;
      Button button4 = ViewBindings.findChildViewById(rootView, id);
      if (button4 == null) {
        break missingId;
      }

      id = R.id.buttonFecha;
      Button buttonFecha = ViewBindings.findChildViewById(rootView, id);
      if (buttonFecha == null) {
        break missingId;
      }

      id = R.id.descripcionCita;
      EditText descripcionCita = ViewBindings.findChildViewById(rootView, id);
      if (descripcionCita == null) {
        break missingId;
      }

      id = R.id.fechaView;
      TextView fechaView = ViewBindings.findChildViewById(rootView, id);
      if (fechaView == null) {
        break missingId;
      }

      id = R.id.horaCita;
      TextView horaCita = ViewBindings.findChildViewById(rootView, id);
      if (horaCita == null) {
        break missingId;
      }

      id = R.id.nombreCita;
      EditText nombreCita = ViewBindings.findChildViewById(rootView, id);
      if (nombreCita == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textoFecha;
      TextView textoFecha = ViewBindings.findChildViewById(rootView, id);
      if (textoFecha == null) {
        break missingId;
      }

      return new ActivityCitasCreatorBinding((ConstraintLayout) rootView, bottomNavigation, button2,
          button4, buttonFecha, descripcionCita, fechaView, horaCita, nombreCita, textView2,
          textoFecha);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
