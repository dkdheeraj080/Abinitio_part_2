package com.example.dheeraj.abinitio_part_2;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText Name, Email, Password;
    public Spinner Department, Event;
    private ImageView Logo;
    private Button SignUp;
    private TextView Signup2;
    private ArrayAdapter arrayAdapter;
    private FirebaseAuth firebaseAuth;
    String name, email, department, event, password;
    int DepartmentID;
    String[] Branches={"Automobile","Civil","Computer","EnTC","Instrumentation","Mechnical"};

    String[] Automobile={"Automobile 1","Automobile 2","Automobile 3","Automobile 4"};
    String[] Civil={"civil 1","civil 2","civil 3","civil 4"};
    String[] Computer={"Computer 1","Computer 2","Computer 3","Computer 4"};
    String[] EnTC={"EnTC 1","EnTC 2","EnTC 3","EnTC 4"};
    String[] Instrumentation={"Instrumentation 1","Instrumentation 2","Instrumentation 3","Instrumentation 4"};
    String[] Mechanical={"Mechanical 1","Mechanical 2","Mechanical 3","Mechanica 4"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();

        Name = findViewById(R.id.etName);
        Email = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);
        SignUp = findViewById(R.id.btnSignUp);
        Signup2 = findViewById(R.id.tvSignUp);
        Department = (Spinner) findViewById(R.id.spDepartments);
        Event=(Spinner)findViewById(R.id.spEvents);


        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Branches);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Department.setAdapter(arrayAdapter);
        Department.setOnItemSelectedListener(this);



        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(Registration.this,"SignUp clicked",Toast.LENGTH_SHORT).show();
                if (validate()) {
                    String useremail = Email.getText().toString().trim();
                    String password = Password.getText().toString().trim();
//                    Toast.makeText(Registration.this,"SignUp clicked",Toast.LENGTH_SHORT).show();

                    firebaseAuth.createUserWithEmailAndPassword(useremail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //  sendEmailVerification();
                                sendUserData();
                                Toast.makeText(Registration.this, "Registration successfull your data has been stored", Toast.LENGTH_SHORT).show();
                                firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(Registration.this, MainActivity.class));

                            } else {
                                Toast.makeText(Registration.this, "registration UnSuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }


    private Boolean validate()
    {

       Boolean result=false;
       name=Name.getText().toString();
       email=Email.getText().toString();

       if (name.isEmpty() || email.isEmpty() ||department.isEmpty() ||event.isEmpty())
       {
           Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
       }
       else
       {
           result=true;

       }

       return result;

    }
    private void sendUserData()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile=new UserProfile(name,email,department,event);
        databaseReference.setValue(userProfile);



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.spDepartments:
                DepartmentID=i;
                department=adapterView.getItemAtPosition(i).toString();
                switch(DepartmentID)
                {
                    case 0:
                        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,Automobile);
                        break;

                    case 1:
                        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,Civil);
                        break;

                    case 2:
                        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,Computer);
                        break;

                    case 3:
                        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,EnTC);
                        break;

                    case 4:
                        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,Instrumentation);
                        break;

                    case 5:
                        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,Mechanical);
                        break;
                }

                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                Event.setAdapter(arrayAdapter);
                Event.setOnItemSelectedListener(this);
                break;

            case R.id.spEvents:
                event=adapterView.getItemAtPosition(i).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}

