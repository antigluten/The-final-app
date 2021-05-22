package com.example.theapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignDialogFragment extends DialogFragment {
    private DialogInterface.OnDismissListener onDismissListener;


    private FirebaseAuth mAuth;
    private String TAG = "ANTIGLUTEN";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity(), R.style.ThemeOverlay_App_MaterialAlertDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_register, null);

        builder.setView(view);

        mAuth = FirebaseAuth.getInstance();

        EditText login = view.findViewById(R.id.dialogUsernameSignUp);
        EditText password = view.findViewById(R.id.dialogPasswordSignUp);
        EditText email = view.findViewById(R.id.dialogEmailSignUp);
        Button submit = view.findViewById(R.id.dialogCardButtonAdd);
        Button cancel = view.findViewById(R.id.dialogCardButtonCancel);

        submit.setOnClickListener(v -> {

            String name = login.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String mail = email.getText().toString().trim();

            if (name.isEmpty()) {
                login.setError("Invalid login");
                login.requestFocus();
                return;
            }

            if (pass.isEmpty()) {
                password.setError("Invalid password");
                password.requestFocus();
                return;
            }

            if (mail.isEmpty()) {
                email.setError("Please fill it");
                email.requestFocus();
                return;
            }

            if (pass.length() < 8) {
                password.setError("Invalid length of password, min is 8");
                password.requestFocus();
                return;
            }

            mAuth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(task -> {
//                        Log.d(TAG, "onCreateDialog: " + mAuth.getCurrentUser().getUid());
                        if (task.isSuccessful()) {
                            User user = new User(name, pass, mail);
                            FirebaseDatabase.getInstance().getReference()
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(getContext(), "User was registered", Toast.LENGTH_SHORT).show();
                                            dismiss();
                                        } else {
                                            Toast.makeText(getContext(), "Fail to register, try again!!!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        } else {
                            Toast.makeText(getContext(), "Fail to register!!!", Toast.LENGTH_SHORT).show();
                        }

                    });
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
