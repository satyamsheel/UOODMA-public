package com.example.uoodma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uoodma.login_register.MainActivity;
import com.example.uoodma.login_register.phoneVerificationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class editProfile extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    EditText editProfileEmailText, editProfileFullNameText, editProfilePhoneNumberText, editProfileAlternatePhoneNumberText,
            fullAddressText, editProfileCityText, editProfileStateText, editProfilePinCodeText, userDobText,  userAgeText;
    Button editProfileSaveChanges;
    TextView userVerificationText, verificationText;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    final FirebaseUser user = mAuth.getCurrentUser();

    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        editProfileEmailText = findViewById(R.id.editProfileEmailText);
        editProfileFullNameText = findViewById(R.id.editProfileFullNameText);
        editProfilePhoneNumberText = findViewById(R.id.editProfilePhoneNumberText);
        editProfileAlternatePhoneNumberText = findViewById(R.id.editProfileAlternatePhoneNumberText);
        fullAddressText = findViewById(R.id.fullAddressText);
        editProfileCityText = findViewById(R.id.editProfileCityText);
        editProfileStateText = findViewById(R.id.editProfileStateText);
        editProfilePinCodeText = findViewById(R.id.editProfilePinCodeText);
        editProfileSaveChanges = findViewById(R.id.editProfileSaveChanges);
        userVerificationText = findViewById(R.id.verifyUserText);
        verificationText = findViewById(R.id.verificationText);
        userDobText = findViewById(R.id.userDobText);
        userAgeText = findViewById(R.id.userAgeText);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);

        editProfileSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("Users").document(userId);
                Map<String, Object> user = new HashMap<>();
                user.put("Alternate Phone", editProfileAlternatePhoneNumberText.getText().toString());
                user.put("Full Address", fullAddressText.getText().toString());
                user.put("City Name", editProfileCityText.getText().toString());
                user.put("State Name", editProfileStateText.getText().toString());
                user.put("Pin Code", editProfilePinCodeText.getText().toString());
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(editProfile.this, "Success", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

                user.reload();
                if (user.isEmailVerified()){
                    userVerificationText.setText("Verified User");
                    userVerificationText.setTextColor(Color.parseColor("#007600"));
                    verificationText.setVisibility(View.GONE);
                }
                else {
                    userVerificationText.setText("Email is not verified");
                    userVerificationText.setTextColor(Color.parseColor("#ff0000"));
                    verificationText.setVisibility(View.VISIBLE);
                    verificationText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(editProfile.this, "Verification link has been sent", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        user.reload();
                        if (user.isEmailVerified()){
                            userVerificationText.setText("Verified User");
                            userVerificationText.setTextColor(Color.parseColor("#007600"));
                            verificationText.setVisibility(View.GONE);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        else {
                            userVerificationText.setText("Email is not verified");
                            userVerificationText.setTextColor(Color.parseColor("#ff0000"));
                            verificationText.setVisibility(View.VISIBLE);
                            swipeRefreshLayout.setRefreshing(false);
                            verificationText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(editProfile.this, "Verification link has been sent", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });

                        }

                    }
                }, 1000);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        user.reload();
                        if (user.isEmailVerified()){
                            userVerificationText.setText("Verified User");
                            userVerificationText.setTextColor(Color.parseColor("#007600"));
                            verificationText.setVisibility(View.GONE);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                        else {
                            userVerificationText.setText("Email is not verified");
                            userVerificationText.setTextColor(Color.parseColor("#ff0000"));
                            verificationText.setVisibility(View.VISIBLE);
                            swipeRefreshLayout.setRefreshing(false);
                            verificationText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(editProfile.this, "Verification link has been sent", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });

                        }

                    }
                });

            }
            
}



