// Generated by view binder compiler. Do not edit!
package com.example.theapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.theapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDeckBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final Button buttonAddDeck;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final RecyclerView recyclerViewDeck;

  private FragmentDeckBinding(@NonNull FrameLayout rootView, @NonNull Button buttonAddDeck,
      @NonNull LinearLayout linearLayout, @NonNull RecyclerView recyclerViewDeck) {
    this.rootView = rootView;
    this.buttonAddDeck = buttonAddDeck;
    this.linearLayout = linearLayout;
    this.recyclerViewDeck = recyclerViewDeck;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDeckBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDeckBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_deck, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDeckBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonAddDeck;
      Button buttonAddDeck = rootView.findViewById(id);
      if (buttonAddDeck == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = rootView.findViewById(id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.recyclerViewDeck;
      RecyclerView recyclerViewDeck = rootView.findViewById(id);
      if (recyclerViewDeck == null) {
        break missingId;
      }

      return new FragmentDeckBinding((FrameLayout) rootView, buttonAddDeck, linearLayout,
          recyclerViewDeck);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}