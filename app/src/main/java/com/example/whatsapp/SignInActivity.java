package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");

        getSupportActionBar().hide();

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.etEmail.getText().toString().isEmpty()){
                    binding.etEmail.setError("Enter your Email");
                    return;
                }
                if (binding.etPassword.getText().toString().isEmpty()){
                    binding.etPassword.setError("Enter your Password");
                    return;
                }

//                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        if (auth.getCurrentUser() != null) {
            Intent i = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(i);
        }

    }
}