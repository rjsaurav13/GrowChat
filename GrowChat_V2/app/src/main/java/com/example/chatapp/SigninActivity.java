package com.example.chatapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import com.bumptech.glide.Glide;
import com.example.chatapp.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SigninActivity extends AppCompatActivity {


    FirebaseAuth myAuth;
    ActivitySigninBinding activitySigninBinding;
    SharedPreferences sharedPreferences;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySigninBinding = ActivitySigninBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(activitySigninBinding.getRoot());

//        sharedPreferences = getSharedPreferences("SavedToken",MODE_PRIVATE);
            Glide.with(this).load(R.drawable.egif);


        myAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();







        activitySigninBinding.signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = activitySigninBinding.signinMail.getText().toString().trim();
                String password = activitySigninBinding.signinPassword.getText().toString().trim();


                if(!email.isEmpty() && !password.isEmpty()) {

                    myAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        String id =  task.getResult().getUser().getUid();


                                            sharedPreferences = getSharedPreferences("SavedToken",MODE_PRIVATE);
                                            String tokenInMain =  sharedPreferences.getString("ntoken","mynull");
                                            firebaseDatabase.getReference("Users").child(id).child("token").setValue(tokenInMain);




                                        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(SigninActivity.this, "Try again - " + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(SigninActivity.this, "Enter details", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}