package com.example.blog.Gui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.blog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;

public class AddRequestActivity extends AppCompatActivity {

    private String requestedBloodType;
    private EditText reqDescription, reqQuantity, reqPoints;
    private Button postRequestBtn;

    private DatabaseReference dbReference, dbReferenceInitial;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        reqDescription =findViewById(R.id.req_description_ET);
        reqQuantity =findViewById(R.id.req_quantity_ET);
        reqPoints =findViewById(R.id.req_points);

        postRequestBtn = findViewById(R.id.post_request_btn);
        postRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest();
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Pictures");
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference().child("donors");
        dbReferenceInitial = firebaseDatabase.getReference().child("hospitals");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

    }

    private void postRequest() {
        final String description = reqDescription.getText().toString();
        final String quantity = reqQuantity.getText().toString();
        final String points = reqPoints.getText().toString();
        final String hosName = dbReferenceInitial.child(user.getUid()).child("hospitalName").toString();
        final String hosAddress = dbReferenceInitial.child(user.getUid()).child("hospitalAddress").toString();
        final String hosImage = dbReferenceInitial.child(user.getUid()).child("image").toString();

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.child("bloodType").getValue().toString().equals(requestedBloodType)){
                        ds.child("donationRequests").child(user.getUid()).child("requestDescription").getRef().setValue(description);
                        ds.child("donationRequests").child(user.getUid()).child("requestQuantity").getRef().setValue(quantity);
                        ds.child("donationRequests").child(user.getUid()).child("requestPoints").getRef().setValue(points);
                        ds.child("donationRequests").child(user.getUid()).child("hospitalId").getRef().setValue(user.getUid());
                        ds.child("donationRequests").child(user.getUid()).child("hospitalName").getRef().setValue(hosName);
                        ds.child("donationRequests").child(user.getUid()).child("hospitalAddress").getRef().setValue(hosAddress);
                        ds.child("donationRequests").child(user.getUid()).child("hospitalImage").getRef().setValue(hosImage);
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.APositive:
                if (checked)
                    requestedBloodType = "A+";
                break;
            case R.id.ANegative:
                if (checked)
                    requestedBloodType = "A-";
                break;
            case R.id.BPositive:
                if (checked)
                    requestedBloodType = "B+";
                break;
            case R.id.BNegative:
                if (checked)
                    requestedBloodType = "B-";
                break;
            case R.id.ABPositive:
                if (checked)
                    requestedBloodType = "AB+";
                break;
            case R.id.ABNegative:
                if (checked)
                    requestedBloodType = "AB-";
                break;
            case R.id.OPositive:
                if (checked)
                    requestedBloodType = "O+";
                break;
            case R.id.ONegative:
                if (checked)
                    requestedBloodType = "O-";
                break;
        }
    }
}
