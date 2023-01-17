package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.Models.Users;
import com.example.whatsapp.databinding.ActivitySignUpBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
//    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Configure Google SignIn
//        GoogleSignInOptions gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        googleSignInClient = GoogleSignIn.getClient(this,gso);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are Creating Your Account");
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
//                            progressDialog.dismiss();
                                if (task.isSuccessful()){
                                    Users user = new Users(binding.etUserName.getText().toString(),
                                            binding.etEmail.getText().toString(),binding.etPassword.getText().toString());
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);
                                    Toast.makeText(SignUpActivity.this, "User Is Created", Toast.LENGTH_SHORT).show();
//                                    Intent i = new Intent(SignUpActivity.this,MainActivity.class);
//                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });
binding.tvAlreadyAccount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(i);
    }
});

    }
}