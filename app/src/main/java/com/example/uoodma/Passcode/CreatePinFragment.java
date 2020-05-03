package com.example.uoodma.Passcode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uoodma.Passcode.ConfirmPinFragment;
import com.example.uoodma.R;


public class CreatePinFragment extends Fragment {

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, delBtn, goBtn;
    EditText pinEditText;

    TextView enterPinText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_create_pin, container, false);

        button0 =  view.findViewById(R.id.button0);
        button1 =  view.findViewById(R.id.button1);
        button2 =  view.findViewById(R.id.button2);
        button3 =  view.findViewById(R.id.button3);
        button4 =  view.findViewById(R.id.button4);
        button5 =  view.findViewById(R.id.button5);
        button6 =  view.findViewById(R.id.button6);
        button7 =  view.findViewById(R.id.button7);
        button8 =  view.findViewById(R.id.button8);
        button9 =  view.findViewById(R.id.button9);
        delBtn = view.findViewById(R.id.buttonDel);
        goBtn = view.findViewById(R.id.buttonGo);
        enterPinText = view.findViewById(R.id.enterPinText);
        pinEditText = view.findViewById(R.id.enterPinCodeText);




            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "1");
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "2");
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "3");
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "4");
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "5");
                }
            });

            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "6");
                }
            });

            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "7");
                }
            });

            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "8");
                }
            });

            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "9");
                }
            });

            button0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pinEditText.setText(pinEditText.getText() + "0");
                }
            });

            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String textString = pinEditText.getText().toString();
                    if (textString.length() > 0) {
                        pinEditText.setText(textString.substring(0, textString.length() - 1));
                        pinEditText.setSelection(pinEditText.getText().length());
                    }

                }
            });





        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = pinEditText.getText().toString().trim();
                if(pin != null && pin.length() == 4){
                    Log.d("___", "onClick: pin entered");
                    Bundle bundle = new Bundle();
                    bundle.putString("userPin", pinEditText.getText().toString());
                    Fragment fragment = new ConfirmPinFragment();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.mainframe, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }else {
                    Toast.makeText(getActivity(), "Enter correct pin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
        
    }
}
