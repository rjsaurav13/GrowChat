package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText user_name, pass_word;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//        getSupportActionBar().hide(); // hide the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        TextView t1 = (TextView) findViewById(R.id.textView6);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,signup.class);
                startActivity(i);
            }
        });
        TextView t2 = (TextView) findViewById(R.id.textView8);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,resetpass.class);
                startActivity(i);
            }
        });
        user_name=findViewById(R.id.rectangle_3);
        pass_word=findViewById(R.id.rectangle_2);
        Button btn_login = findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();
        btn_login.setOnClickListener(v -> {
            String email= user_name.getText().toString().trim();
            String password=pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                user_name.setError("Email is empty");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                user_name.setError("Enter the valid email");
                user_name.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                pass_word.setError("Length of password is more than 6");
                pass_word.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(MainActivity.this, mainchat.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,
                            "Please Check Your login Credentials",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });
    }
}