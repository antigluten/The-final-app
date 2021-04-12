// Generated by view binder compiler. Do not edit!
package com.example.theapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.theapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout container2;

  @NonNull
  public final EditText profileLogin;

  @NonNull
  public final EditText profileLoginPassword;

  @NonNull
  public final ProgressBar profileProgress;

  @NonNull
  public final TextView profileRegister;

  @NonNull
  public final Button profileRegisterButton;

  @NonNull
  public final TextView textView;

  private ActivityLoginBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout container2, @NonNull EditText profileLogin,
      @NonNull EditText profileLoginPassword, @NonNull ProgressBar profileProgress,
      @NonNull TextView profileRegister, @NonNull Button profileRegisterButton,
      @NonNull TextView textView) {
    this.rootView = rootView;
    this.container2 = container2;
    this.profileLogin = profileLogin;
    this.profileLoginPassword = profileLoginPassword;
    this.profileProgress = profileProgress;
    this.profileRegister = profileRegister;
    this.profileRegisterButton = profileRegisterButton;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.container2;
      ConstraintLayout container2 = rootView.findViewById(id);
      if (container2 == null) {
        break missingId;
      }

      id = R.id.profileLogin;
      EditText profileLogin = rootView.findViewById(id);
      if (profileLogin == null) {
        break missingId;
      }

      id = R.id.profileLoginPassword;
      EditText profileLoginPassword = rootView.findViewById(id);
      if (profileLoginPassword == null) {
        break missingId;
      }

      id = R.id.profileProgress;
      ProgressBar profileProgress = rootView.findViewById(id);
      if (profileProgress == null) {
        break missingId;
      }

      id = R.id.profileRegister;
      TextView profileRegister = rootView.findViewById(id);
      if (profileRegister == null) {
        break missingId;
      }

      id = R.id.profileRegisterButton;
      Button profileRegisterButton = rootView.findViewById(id);
      if (profileRegisterButton == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = rootView.findViewById(id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityLoginBinding((ConstraintLayout) rootView, container2, profileLogin,
          profileLoginPassword, profileProgress, profileRegister, profileRegisterButton, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
