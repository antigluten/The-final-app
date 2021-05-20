package com.example.theapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;

import com.example.theapp.data.DatabaseHelper;
import com.example.theapp.data.Deck;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginDialogFragment extends DialogFragment {
    private DialogInterface.OnDismissListener onDismissListener;

    private EditText login;
    private EditText password;
    private FirebaseUser user;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.ThemeOverlay_App_MaterialAlertDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_login, null);

        builder.setView(view);

        login = view.findViewById(R.id.dialogEmailLogin);
        password = view.findViewById(R.id.dialogPasswordLogin);

        Button logIn = view.findViewById(R.id.dialogButtonLogin);
        Button register = view.findViewById(R.id.dialogButtonRegister);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        logIn.setOnClickListener(v -> {
            String mail = login.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (mail.isEmpty()) {
                login.setError("Please fill it");
                login.requestFocus();
                return;
            }

            if (pass.isEmpty()) {
                password.setError("Invalid password");
                password.requestFocus();
                return;
            }

            if (pass.length() < 8) {
                password.setError("Invalid length of password, min is 8");
                password.requestFocus();

            }

            mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    dismiss();
                }
                else {
                    Toast.makeText(getContext(), "Fail to sign in", Toast.LENGTH_LONG).show();
                }
            });
        });

        register.setOnClickListener(v -> {
            dismiss();
        });

        return builder.create();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }
}
