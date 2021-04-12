// Generated by view binder compiler. Do not edit!
package com.example.theapp.databinding;

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
import com.example.theapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogRenameDeckBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText deckNameDialog;

  @NonNull
  public final Button dialogButtonAdd;

  @NonNull
  public final Button dialogButtonCancel;

  @NonNull
  public final TextView textView4;

  private DialogRenameDeckBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText deckNameDialog, @NonNull Button dialogButtonAdd,
      @NonNull Button dialogButtonCancel, @NonNull TextView textView4) {
    this.rootView = rootView;
    this.deckNameDialog = deckNameDialog;
    this.dialogButtonAdd = dialogButtonAdd;
    this.dialogButtonCancel = dialogButtonCancel;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogRenameDeckBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogRenameDeckBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_rename_deck, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogRenameDeckBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.deckNameDialog;
      EditText deckNameDialog = rootView.findViewById(id);
      if (deckNameDialog == null) {
        break missingId;
      }

      id = R.id.dialogButtonAdd;
      Button dialogButtonAdd = rootView.findViewById(id);
      if (dialogButtonAdd == null) {
        break missingId;
      }

      id = R.id.dialogButtonCancel;
      Button dialogButtonCancel = rootView.findViewById(id);
      if (dialogButtonCancel == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = rootView.findViewById(id);
      if (textView4 == null) {
        break missingId;
      }

      return new DialogRenameDeckBinding((ConstraintLayout) rootView, deckNameDialog,
          dialogButtonAdd, dialogButtonCancel, textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
