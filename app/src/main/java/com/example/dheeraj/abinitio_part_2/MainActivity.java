package com.example.dheeraj.abinitio_part_2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Email,Password;
    private ImageView Logo;
    private TextView SignUp,Login;
    private CardView Card;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUIView();

        firebaseAuth=FirebaseAuth.getInstance();
       /* FirebaseUser user =firebaseAuth.getCurrentUser();

        if (user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this,CoordinatorProfile.class));
        }
*/
       SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });

       Login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               validate(Email.getText().toString(),Password.getText().toString());

           }
       });

    }

    private void setUIView()
    {
        Email=findViewById(R.id.etEmail);
        Password=findViewById(R.id.etPassword);
        Logo=findViewById(R.id.ivLogo);
        Card=findViewById(R.id.card);
        SignUp=findViewById(R.id.tvSignUp);
        Login=findViewById(R.id.tvLogin);


    }
    private void validate(String userEmail,String userPassword) {

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please Enter the data", Toast.LENGTH_SHORT).show();
        } else
            {
            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivity.this, CoordinatorProfile.class));

                    } else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
