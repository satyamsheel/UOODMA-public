package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class editProfile extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    EditText editProfileEmailText, editProfileFullNameText, editProfilePhoneNumberText, editProfileAlternatePhoneNumberText,
            fullAddressText, editProfileCityText, editProfileStateText, editProfilePinCodeText;
    Button editProfileSaveChanges;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        documentReference = db.collection("User").document(mAuth.getCurrentUser().getUid());

        editProfileEmailText = findViewById(R.id.editProfileEmailText);
        editProfileFullNameText = findViewById(R.id.editProfileFullNameText);
        editProfilePhoneNumberText = findViewById(R.id.editProfilePhoneNumberText);
        editProfileAlternatePhoneNumberText = findViewById(R.id.editProfileAlternatePhoneNumberText);
        fullAddressText = findViewById(R.id.fullAddressText);
        editProfileCityText = findViewById(R.id.editProfileCityText);
        editProfileStateText = findViewById(R.id.editProfileStateText);
        editProfilePinCodeText = findViewById(R.id.editProfilePinCodeText);
        editProfileSaveChanges = findViewById(R.id.editProfileSaveChanges);
    }

    @Override
    protected void onStart() {

        super.onStart();
    }
}
