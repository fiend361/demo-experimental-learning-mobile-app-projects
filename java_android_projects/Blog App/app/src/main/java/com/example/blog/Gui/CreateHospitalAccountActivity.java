package com.example.blog.Gui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.blog.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class CreateHospitalAccountActivity extends AppCompatActivity {

    private EditText hosName, hosAddress, hosEmail, hosPassword, hosConfirmPassword;
    private Button createHospitalAccBtn;
    private ImageView hospitalProfileImage;
    private Uri resultUri;
    private DatabaseReference dbReference;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hospital_account);

        hosName = findViewById(R.id.hos_name_ET);
        hosAddress = findViewById(R.id.hos_address_ET);
        hosEmail = findViewById(R.id.hos_email_ET);
        hosPassword = findViewById(R.id.hos_password_ET);
        hosConfirmPassword = findViewById(R.id.hos_confirm_password_ET);

        hospitalProfileImage = findViewById(R.id.hos_profile_image);
        hospitalProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 1);
            }
        });

        createHospitalAccBtn = findViewById(R.id.hos_create_acc_btn);
        createHospitalAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

        progressDialog = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Pictures");
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference().child("hospitals");
        auth = FirebaseAuth.getInstance();
    }

    private void createNewAccount() {
        final String hosNameS, hosAddressS, hosEmailS, hosPasswordS, hosConfirmPasswordS;
        hosNameS = hosName.getText().toString();
        hosAddressS = hosAddress.getText().toString();
        hosEmailS = hosEmail.getText().toString();
        hosPasswordS = hosPassword.getText().toString();
        hosConfirmPasswordS = hosConfirmPassword.getText().toString();

        //check all inputs
        if (!TextUtils.isEmpty(hosNameS) && !TextUtils.isEmpty(hosAddressS)
                && !TextUtils.isEmpty(hosEmailS) && !TextUtils.isEmpty(hosPasswordS) && hosPasswordS.equals(hosConfirmPasswordS)) {
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            auth.createUserWithEmailAndPassword(hosEmailS, hosPasswordS).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    //if (authResult != null) {
                    StorageReference imagePath = storageReference.child(resultUri.getLastPathSegment());
                    imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String userID = auth.getCurrentUser().getUid();
                            DatabaseReference newUser = dbReference.child(userID);
                            newUser.child("hospitalName").setValue(hosNameS);
                            newUser.child("hospitalAddress").setValue(hosAddressS);
                            newUser.child("image").setValue(resultUri.toString());

                            progressDialog.dismiss();
                            Toast.makeText(CreateHospitalAccountActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateHospitalAccountActivity.this, PostsActivity.class));
                            finish();
                        }
                    });
                    //}
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(CreateHospitalAccountActivity.this, "Failed to create account", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri imageURI = data.getData();
            CropImage.activity(imageURI)
                    .setAspectRatio(1, 1)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                hospitalProfileImage.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
