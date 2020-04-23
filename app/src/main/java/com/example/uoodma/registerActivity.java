package com.example.uoodma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {

     private EditText registerEmailText,registerFirstNameText,registerLastNameText,
            registerPasswordText,registerRePasswordText,registerPhoneText;
    private Button button6;
    private Spinner registerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmailText=findViewById(R.id.registerEmailText);
        registerFirstNameText=findViewById(R.id.registerFirstNameText);
        registerLastNameText=findViewById(R.id.registerLastNameText);
        registerPasswordText=findViewById(R.id.registerPasswordText);
        registerRePasswordText=findViewById(R.id.registerRePasswordText);
        registerPhoneText=findViewById(R.id.registerPhoneText);
        button6=findViewById(R.id.button6);
        registerSpinner=findViewById(R.id.registerSpinner);

        registerSpinner.setAdapter(new ArrayAdapter<String>(registerActivity.this,android.R.layout.
                simple_spinner_dropdown_item,countryData.countryNames));

    }

    public void onRegistrationRequest(View view) {
        if(registerPasswordText.getText().toString().equals(registerRePasswordText.getText().toString()))
        {
            if(registerPhoneText.length() == 10) {
                Intent intent = new Intent(registerActivity.this, phoneVerificationActivity.class);
                String code=countryData.countryAreaCodes[registerSpinner.getSelectedItemPosition()];
                String phone =registerPhoneText.getText().toString().trim();
                String phoneNumber= "+"+code+phone;
                intent.putExtra("Email",registerEmailText.getText().toString().trim());
                intent.putExtra("First Name",registerFirstNameText.getText().toString().trim());
                intent.putExtra("Last Name",registerLastNameText.getText().toString().trim());
                intent.putExtra("Password",registerPasswordText.getText().toString().trim());
                intent.putExtra("Phone Number",phoneNumber );
                startActivity(intent);
            }
            else{
                registerPhoneText.requestFocus();
                Toast.makeText(registerActivity.this,"Phone number issue",Toast.LENGTH_LONG).show();
                //put Text watcher for 10 Digit phone number
            }

        }
        else{
            registerPasswordText.requestFocus();
            Toast.makeText(registerActivity.this,"Password Dosent match",Toast.LENGTH_LONG).show();
            //Put text watcher for non matching passwords
        }
    }
}
