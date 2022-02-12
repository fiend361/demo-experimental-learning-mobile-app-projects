package com.jsoftware.cab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    private Button driverRegisterButton;
    private Button driverLoginButton;
    private TextView driverRegisterLink;
    private EditText EmailDriver;
    private EditText PasswordDriver;

    private ProgressDialog progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        progressBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        driverRegisterButton = findViewById(R.id.driver_register_btn);
        driverLoginButton = findViewById(R.id.driver_login_btn);
        driverRegisterLink = findViewById(R.id.driver_register_link);
        EmailDriver = findViewById(R.id.email_driver);
        PasswordDriver = findViewById(R.id.password_driver);

        driverRegisterButton.setVisibility(View.INVISIBLE);
        driverRegisterButton.setEnabled(false);

        driverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverLoginButton.setVisibility(View.INVISIBLE);
                driverRegisterLink.setVisibility(View.INVISIBLE);
                driverRegisterButton.setVisibility(View.VISIBLE);
                driverRegisterButton.setEnabled(true);
            }
        });

        driverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailDriver.getText().toString();
                String Password = PasswordDriver.getText().toString();

                RegisterDriver(Email,Password);
            }
        });

        driverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailDriver.getText().toString();
                String Password = PasswordDriver.getText().toString();

                SignInDriver(Email,Password);
            }
        });
    }

    private void SignInDriver(String email, String password) {
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

        }else {
            progressBar.setTitle("Logging in");
            progressBar.setMessage("Please Wait");
            progressBar.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(DriverLoginRegisterActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                                progressBar.dismiss();

                                Intent intent = new Intent(DriverLoginRegisterActivity.this, DriversMapActivity.class);
                                startActivity(intent);
                            }
                            progressBar.dismiss();
                        }
                    });
        }
    }

    private void RegisterDriver(String email, String password) {
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

        }else {
            progressBar.setTitle("Registering");
            progressBar.setMessage("Please Wait.");
            progressBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(DriverLoginRegisterActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                                progressBar.dismiss();

                                Intent intent = new Intent(DriverLoginRegisterActivity.this, DriversMapActivity.class);
                                startActivity(intent);
                            }
                            progressBar.dismiss();
                        }
                    });
        }
    }


}
