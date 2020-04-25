package com.example.uoodma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "loginActivity";
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";

    private EditText emailText,passwordText;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        emailText=findViewById(R.id.emailText);
        passwordText=findViewById(R.id.passwordText);
        button5=findViewById(R.id.button5);

        emailText.addTextChangedListener(new gTextWatcher(emailText));
        passwordText.addTextChangedListener(new gTextWatcher(passwordText));

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (emailText.getText().toString().isEmpty()){
//                    emailText.setError("Please Enter Email Id");
//                }
//                if (passwordText.getText().toString().isEmpty()){
//                    passwordText.setError("Please Enter Password");
//                }
                if (!validateEmail()){
                        return;
                }
                else if (!validatePassword()){
                    return;
                }
                else{
                    firebaseLogin();
                }
            }
        });

    }

    public void firebaseLogin() {
        mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           Toast.makeText(loginActivity.this,"Welcome Back",Toast.LENGTH_LONG).show();
                           Intent intent =new Intent(loginActivity.this,mainDashboard.class);
                           startActivity(intent);
                           finish();

                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(loginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public boolean validateEmail() {

        String email = emailText.getText().toString();

        if (!email.trim().matches(emailPattern) && email.length() > 0 ) {

            emailText.setError("Please enter correct id");
            emailText.requestFocus();
            return false;
        }
        else if (email.trim().isEmpty()){
            emailText.setError("Please enter id");
            emailText.requestFocus();
            return false;

        }
        else {
            return true;
        }

    }

    public boolean validatePassword(){
        String password = passwordText.getText().toString().trim();
        if (password.isEmpty()){
            passwordText.setError("Enter valid password");
            passwordText.requestFocus();
            return false;
        }
        else {
            return true;
        }

    }

    private class gTextWatcher implements TextWatcher {

        private View view;

        public gTextWatcher(View view){
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (view.getId()){

                case R.id.emailText:
                    validateEmail();
                    break;

                case  R.id.passwordText:
                    validatePassword();
                    break;
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
