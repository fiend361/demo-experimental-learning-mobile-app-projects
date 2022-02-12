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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    private Button customerRegisterButton;
    private Button customerLoginButton;
    private TextView customerregisterLink;
    private EditText EmailCustomer;
    private EditText PasswordCustomer;

    private ProgressDialog progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        progressBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        customerRegisterButton = findViewById(R.id.customer_register_btn);
        customerLoginButton = findViewById(R.id.customer_login_btn);
        customerregisterLink = findViewById(R.id.register_customer_link);
        PasswordCustomer = findViewById(R.id.password_customer);
        EmailCustomer = findViewById(R.id.email_customer);

        customerRegisterButton.setVisibility(View.INVISIBLE);
        customerRegisterButton.setEnabled(false);

        customerregisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerLoginButton.setVisibility(View.INVISIBLE);
                customerregisterLink.setVisibility(View.INVISIBLE);
                customerRegisterButton.setVisibility(View.VISIBLE);
                customerRegisterButton.setEnabled(true);
            }
        });

        customerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailCustomer.getText().toString();
                String Password = PasswordCustomer.getText().toString();

                RegisterCustomer(Email,Password);
            }
        });

        customerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = EmailCustomer.getText().toString();
                String Password = PasswordCustomer.getText().toString();

                SignInCustomer(Email, Password);
            }
        });
    }

    private void SignInCustomer(String email, String password) {
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

        }else {
            progressBar.setTitle("Logging in");
            progressBar.setMessage("Please Wait.");
            progressBar.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                                progressBar.dismiss();

                                Intent intent = new Intent(CustomerLoginRegisterActivity.this, CustomersMapActivity.class);
                                startActivity(intent);
                            }
                            progressBar.dismiss();
                        }
                    });
        }
    }

    private void RegisterCustomer(String email, String password) {
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
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Success!!", Toast.LENGTH_SHORT).show();
                                progressBar.dismiss();

                                Intent intent = new Intent(CustomerLoginRegisterActivity.this, CustomersMapActivity.class);
                                startActivity(intent);
                            }
                            progressBar.dismiss();
                        }
                    });
        }
    }
}
