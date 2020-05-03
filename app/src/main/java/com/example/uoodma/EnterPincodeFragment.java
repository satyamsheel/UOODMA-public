package com.example.uoodma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static android.content.Context.MODE_PRIVATE;


public class EnterPincodeFragment extends Fragment {



    Button enterButton0, enterButton1, enterButton2, enterButton3, enterButton4, enterButton5, enterButton6,
            enterButton7, enterButton8, enterButton9, delBtn, goBtn;
    EditText enterPinEditText;
    
    TextView pinMsg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_pincode, container, false);

        enterButton0 =  view.findViewById(R.id.enterButton0);
        enterButton1 =  view.findViewById(R.id.enterButton1);
        enterButton2 =  view.findViewById(R.id.enterButton2);
        enterButton3 =  view.findViewById(R.id.enterButton3);
        enterButton4 =  view.findViewById(R.id.enterButton4);
        enterButton5 =  view.findViewById(R.id.enterButton5);
        enterButton6 =  view.findViewById(R.id.enterButton6);
        enterButton7 =  view.findViewById(R.id.enterButton7);
        enterButton8 =  view.findViewById(R.id.enterButton8);
        enterButton9 =  view.findViewById(R.id.enterButton9);
        delBtn = view.findViewById(R.id.enterButtonDel);
        goBtn = view.findViewById(R.id.enterButtonGo);
        enterPinEditText = view.findViewById(R.id.loginPinCode);
        pinMsg = view.findViewById(R.id.pinMsg);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREFS", MODE_PRIVATE);
        final String upin = sharedPreferences.getString("KEY", null);
        Log.d("____", "onCreate: enter spref" + upin);

        enterButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "1");
            }
        });

        enterButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "2");
            }
        });

        enterButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "3");
            }
        });

        enterButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "4");
            }
        });

        enterButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "5");
            }
        });

        enterButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "6");
            }
        });

        enterButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "7");
            }
        });

        enterButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "8");
            }
        });

        enterButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "9");
            }
        });

        enterButton0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterPinEditText.setText(enterPinEditText.getText() + "0");
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textString = enterPinEditText.getText().toString();
                if( textString.length() > 0 ) {
                    enterPinEditText.setText(textString.substring(0, textString.length() - 1 ));
                    enterPinEditText.setSelection(enterPinEditText.getText().length());
                }

            }
        });

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = enterPinEditText.getText().toString();
                if (pin != null && pin.length() ==4 && pin.hashCode() == upin.hashCode() ){
                    getActivity().finish();
                    Intent intent = new Intent(getActivity(), MyProfile.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getActivity(), "Incorrect pin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
