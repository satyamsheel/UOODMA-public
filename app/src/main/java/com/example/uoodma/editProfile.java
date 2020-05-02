package com.example.uoodma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class editProfile extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    EditText editProfileEmailText, editProfileFullNameText, editProfilePhoneNumberText, editProfileAlternatePhoneNumberText,
            fullAddressText, editProfileCityText, editProfileStateText, editProfilePinCodeText, userDobText, userAgeText;
    Button editProfileSaveChanges, editProfileUploadData;
    ProgressDialog progressDialog1;
    Toolbar toolbar;
    TextView userVerificationText, verificationText;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final FirebaseUser user = mAuth.getCurrentUser();

    SwipeRefreshLayout swipeRefreshLayout;
    TextInputLayout editProfileEmail;



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
        editProfileEmail = findViewById(R.id.editProfileEmail);
        editProfileUploadData = findViewById(R.id.editProfileUploadData);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        //toolbar implemented
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        watcherConnecter();
        setUserDobnAge();
        updateUserInfoBtn();
//progress bar implemented
        progressDialog1 = new ProgressDialog(this);
        progressDialog1.setMessage("Please Wait");
        progressDialog1.setCanceledOnTouchOutside(true);
    }

    public void watcherConnecter(){
        editProfileEmailText.addTextChangedListener(new updateTextWatcher(editProfileEmailText));
        editProfileFullNameText.addTextChangedListener(new updateTextWatcher(editProfileFullNameText));
        // editProfilePhoneNumberText.addTextChangedListener(new updateTextWatcher(editProfilePhoneNumberText));
        editProfileAlternatePhoneNumberText.addTextChangedListener(new updateTextWatcher(editProfileAlternatePhoneNumberText));
        userDobText.addTextChangedListener(new updateTextWatcher(userDobText));
        userAgeText.addTextChangedListener(new updateTextWatcher(userAgeText));
        fullAddressText.addTextChangedListener(new updateTextWatcher(fullAddressText));
        editProfileCityText.addTextChangedListener(new updateTextWatcher(editProfileCityText));
        editProfileStateText.addTextChangedListener(new updateTextWatcher(editProfileStateText));
        editProfilePinCodeText.addTextChangedListener(new updateTextWatcher(editProfilePinCodeText));
    }

    public void updateUserInfoBtn(){


        editProfileSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUpdateEmail()) {
                    return;
                } else if (!validateUpdateName()) {
                    return;
                } else if (!validateUpdateAlternatePhoneNum()) {
                    return;
                } else if (!validateUpdateDob()) {
                    return;
                } else if (!validateUpdateAge()) {
                    return;
                } else if (!validateUpdateAddress()){
                    return;
                } else if (!validateUpdateCity()){
                    return;
                } else if (!validateUpdateState()){
                    return;
                } else if (!validateUpdatePincode()){
                    return;
                } else {
                    updateUserInfo();
                }
                progressDialog1.show();
            }
        });

        editProfileUploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchUserInfo();
              progressDialog1.show();
            }
        });
    }

    private void fetchUserInfo() {
        String userId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Map<String, Object> user = new HashMap<>();
                    editProfileFullNameText.setText(documentSnapshot.getString("User Full Name"));
                    editProfileAlternatePhoneNumberText.setText(documentSnapshot.getString("Alternate Phone"));
                    userDobText.setText(documentSnapshot.getString("Birth Date"));
                    userAgeText.setText(documentSnapshot.getString("Age"));
                    fullAddressText.setText(documentSnapshot.getString("Full Address"));
                    editProfileCityText.setText(documentSnapshot.getString("City Name"));
                    editProfileStateText.setText(documentSnapshot.getString("State Name"));
                    editProfilePinCodeText.setText(documentSnapshot.getString("Pin Code"));
                } else {
                    Toast.makeText(editProfile.this, "No data Exists, Please update", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void updateUserInfo(){
        String userId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Users").document(userId);
        Map<String, Object> user = new HashMap<>();
        user.put("User Full Name", editProfileFullNameText.getText().toString());
        user.put("Alternate Phone", editProfileAlternatePhoneNumberText.getText().toString());
        user.put("Birth Date", userDobText.getText().toString());
        user.put("Age", userAgeText.getText().toString());
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


    @Override
    protected void onStart() {
        super.onStart();

        user.reload();
        if (user.isEmailVerified()) {
            userVerificationText.setText("Verified User");
            userVerificationText.setTextColor(Color.parseColor("#007600"));
            verificationText.setVisibility(View.GONE);
        } else {
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
                if (user.isEmailVerified()) {
                    userVerificationText.setText("Verified User");
                    userVerificationText.setTextColor(Color.parseColor("#007600"));
                    verificationText.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
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
                if (user.isEmailVerified()) {
                    userVerificationText.setText("Verified User");
                    userVerificationText.setTextColor(Color.parseColor("#007600"));
                    verificationText.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
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
        editProfileEmailText.setText(user.getEmail());
        editProfileEmailText.setEnabled(false);
        editProfilePhoneNumberText.setText(user.getPhoneNumber());
        editProfilePhoneNumberText.setEnabled(false);

    }

    public void setUserDobnAge() {
        userDobText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(editProfile.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                Calendar calendar = Calendar.getInstance();
                int presentYr = calendar.get(Calendar.YEAR);
                int age = presentYr - year;
                userDobText.setText(date);
                if (age == 1 || age == 0) {
                    userAgeText.setText(String.valueOf(age) + " year");
                } else {
                    userAgeText.setText(String.valueOf(age) + " years");
                }
            }
        };
    }

    public boolean validateUpdateEmail() {
        String email = editProfileEmailText.getText().toString();

        if (!email.trim().matches(emailPattern) && email.length() > 0) {

            editProfileEmailText.setError("Please enter correct id");
            editProfileEmailText.requestFocus();
            return false;
        } else if (email.trim().isEmpty()) {
            editProfileEmailText.setError("Please enter correct id");
            editProfileEmailText.requestFocus();
            return false;

        } else {
            return true;
        }

    }

    public boolean validateUpdateName() {
        String userName = editProfileFullNameText.getText().toString().trim();
        if (userName.isEmpty()) {
            editProfileFullNameText.setError("Please enter name");
            editProfileFullNameText.requestFocus();
            return false;
        } else {
            return true;
        }

    }

//    public boolean validateUpdatePhoneNum() {
//        String phoneNum = editProfilePhoneNumberText.getText().toString().trim();
//        if (phoneNum.length() != 13 && phoneNum.length() > 0) {
//            editProfilePhoneNumberText.setError("Please enter correct no");
//            editProfilePhoneNumberText.requestFocus();
//            return false;
//        } else if (phoneNum.isEmpty()) {
//            editProfilePhoneNumberText.setError("Please enter no");
//            editProfilePhoneNumberText.requestFocus();
//            return false;
//        } else {
//            return true;
//        }
//    }

    public boolean validateUpdateAlternatePhoneNum() {
        String altPhoneNum = editProfileAlternatePhoneNumberText.getText().toString().trim();
        if (altPhoneNum.length() != 10 && altPhoneNum.length() > 0) {
            editProfileAlternatePhoneNumberText.setError("Please enter correct no");
            editProfileAlternatePhoneNumberText.requestFocus();
            return false;
        } else if (altPhoneNum.isEmpty()) {
            editProfileAlternatePhoneNumberText.setError("Please enter no");
            editProfileAlternatePhoneNumberText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public boolean validateUpdateDob() {
        String uDOB = userDobText.getText().toString().trim();
        if (uDOB.isEmpty()) {
            userDobText.setError("Please enter DOB");
            userDobText.requestFocus();
            return false;
        } else {
            userDobText.setError(null);
            return true;
        }

    }

    public boolean validateUpdateAge() {
        String uAge = userAgeText.getText().toString().trim();
        if (uAge.isEmpty()) {
            userAgeText.setError("Please enter age");
            userAgeText.requestFocus();
            return false;
        } else {
            return true;
        }

    }
    public boolean validateUpdateState() {
        String userState = editProfileStateText.getText().toString().trim();
        if (userState.isEmpty()) {
            editProfileStateText.setError("Please enter state");
            editProfileStateText.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    public boolean validateUpdateAddress() {
        String userFullAddress = fullAddressText.getText().toString().trim();
        if (userFullAddress.isEmpty()) {
            fullAddressText.setError("Please enter address");
            fullAddressText.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    public boolean validateUpdateCity() {
        String userCity = editProfileCityText.getText().toString().trim();
        if (userCity.isEmpty()) {
            editProfileCityText.setError("Please enter city");
            editProfileCityText.requestFocus();
            return false;
        } else {
            return true;
        }

    }


    public boolean validateUpdatePincode() {
        String pincode = editProfilePinCodeText.getText().toString().trim();
        if (pincode.length() != 6 && pincode.length() > 0) {
            editProfilePinCodeText.setError("Please enter correct pincode");
            editProfilePinCodeText.requestFocus();
            return false;
        } else if (pincode.isEmpty()) {
            editProfilePinCodeText.setError("Please enter pincode");
            editProfilePinCodeText.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private class updateTextWatcher implements TextWatcher{
        private  View view;

        public  updateTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (view.getId()){
                case R.id.editProfileEmailText:
                    validateUpdateEmail();
                    break;
                case R.id.editProfileFullNameText:
                    validateUpdateName();
                    break;

                case R.id.editProfileAlternatePhoneNumberText:
                    validateUpdateAlternatePhoneNum();
                    break;
                case R.id.userDobText:
                    validateUpdateDob();
                    break;
                case R.id.userAgeText:
                    validateUpdateAge();
                    break;
                case R.id.fullAddressText:
                    validateUpdateAddress();
                    break;
                case R.id.editProfileCityText:
                    validateUpdateCity();
                    break;
                case R.id.editProfileStateText:
                    validateUpdateState();
                    break;
                case R.id.editProfilePinCodeText:
                    validateUpdatePincode();
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}