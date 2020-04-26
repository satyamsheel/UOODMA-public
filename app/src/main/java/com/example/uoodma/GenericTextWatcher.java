package com.example.uoodma;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class GenericTextWatcher implements TextWatcher {

    private View view;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
    loginActivity loginActivity;

    public  GenericTextWatcher(View view){
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        switch (view.getId()){
//            case R.id.emailText:
//                if (!charSequence.toString().trim().matches(emailPattern) && charSequence.length() > 0) {
//                    EditText emailText = view.findViewById(R.id.emailText);
//                    emailText.setError("Please enter correct id");
//                    emailText.requestFocus();
//                  }
//
//                break;
//
//            case R.id.registerEmailText:
//                if (!charSequence.toString().trim().matches(emailPattern) && charSequence.length() > 0) {
//                    EditText registeredEmailText = view.findViewById(R.id.registerEmailText);
//                    registeredEmailText.setError("Please enter correct id");
//                    registeredEmailText.requestFocus();
//                }
//                break;
//            case R.id.registerPasswordText:
//                EditText registerPasswordText = view.findViewById(R.id.registerPasswordText);
//                if (!charSequence.toString().matches(passwordPattern )&& charSequence.length() > 0){
//                    registerPasswordText.setError("should contain A-Z, a-z, 0-9, $/@/&");
//                    registerPasswordText.requestFocus();
//                }
//                break;
//
//            case R.id.registerPhoneText:
//                if(charSequence.length() !=10 && charSequence.length() > 0){
//                    EditText registeredPhoneText = view.findViewById(R.id.registerPhoneText);
//                    registeredPhoneText.setError("Please enter correct no");
//                    registeredPhoneText.requestFocus();
//
//                }
//
//
//
//
//        }

    }

    @Override
    public void afterTextChanged(Editable editable) {



    }
}
