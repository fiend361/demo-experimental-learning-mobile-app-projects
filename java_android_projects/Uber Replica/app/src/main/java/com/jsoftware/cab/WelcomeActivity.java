package com.jsoftware.cab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button welcomeDriverButton;
    private Button welcomeCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeDriverButton = findViewById(R.id.welcome_driver_btn);
        welcomeCustomerButton = findViewById(R.id.welcome_customer_btn);

        welcomeCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, CustomerLoginRegisterActivity.class);
                startActivity(intent);
            }
        });

        welcomeDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(WelcomeActivity.this, DriverLoginRegisterActivity.class);
                startActivity(intent2);
            }
        });
    }
}
