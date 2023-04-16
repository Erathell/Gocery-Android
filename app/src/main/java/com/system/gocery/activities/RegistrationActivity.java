package com.system.gocery.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.system.gocery.R;

public class RegistrationActivity extends AppCompatActivity {


    EditText firstName, lastName, email, password, address, contactNumber;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        contactNumber = findViewById(R.id.contactNumber);

    }

    public void signup(View view){
        String userFirstName = firstName.getText().toString();
        String userLastName = lastName.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userAddress = address.getText().toString();
        String userContact = contactNumber.getText().toString();

        if(TextUtils.isEmpty(userFirstName)){
            Toast.makeText(this, "Enter First Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userLastName)){
            Toast.makeText(this, "Enter Last Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length() < 6 ){
            Toast.makeText(this, "Password too short! Enter minimum of 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userAddress)){
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userContact)){
            Toast.makeText(this, "Enter Contact Number", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void signin(View view){
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }
}