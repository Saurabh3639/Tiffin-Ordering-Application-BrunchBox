package com.tiffin.app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.tiffin.app.ReusableCode.ReusableCodeForAll;

public class AdminForgotPassword extends AppCompatActivity {

    TextInputLayout forgetpassword;
    Button Reset;
    FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_forgot_password);

        forgetpassword = (TextInputLayout) findViewById(R.id.Emailid);
        Reset = (Button) findViewById(R.id.button2);

        FAuth = FirebaseAuth.getInstance();
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(AdminForgotPassword.this);
                mDialog.setCancelable(false);
                mDialog.setCanceledOnTouchOutside(false);
                mDialog.setMessage("Logging in...");
                mDialog.show();

                FAuth.sendPasswordResetEmail(forgetpassword.getEditText().getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mDialog.dismiss();
                            ReusableCodeForAll.ShowAlert(AdminForgotPassword.this, "", "Password has been sent to your Email");
                        } else {
                            mDialog.dismiss();
                            ReusableCodeForAll.ShowAlert(AdminForgotPassword.this, "Error", task.getException().getMessage());
                        }
                    }
                });
            }
        });

    }
}
