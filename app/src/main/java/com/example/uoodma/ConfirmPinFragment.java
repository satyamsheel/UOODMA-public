package com.example.uoodma;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uoodma.MyProfile;
import com.example.uoodma.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class ConfirmPinFragment extends Fragment {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button confirmButton0, confirmButton1, confirmButton2, confirmButton3, confirmButton4, confirmButton5, confirmButton6,
            confirmButton7, confirmButton8, confirmButton9, delBtn, goBtn;
    EditText pinEditText, confirmPinEditText;

    TextView enterPinText, confirmPinText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirm_pin_frag, container,false);

        confirmButton0 =  view.findViewById(R.id.confirmButton0);
        confirmButton1 =  view.findViewById(R.id.confirmButton1);
        confirmButton2 =  view.findViewById(R.id.confirmButton2);
        confirmButton3 =  view.findViewById(R.id.confirmButton3);
        confirmButton4 =  view.findViewById(R.id.confirmButton4);
        confirmButton5 =  view.findViewById(R.id.confirmButton5);
        confirmButton6 =  view.findViewById(R.id.confirmButton6);
        confirmButton7 =  view.findViewById(R.id.confirmButton7);
        confirmButton8 =  view.findViewById(R.id.confirmButton8);
        confirmButton9 =  view.findViewById(R.id.confirmButton9);
        delBtn = view.findViewById(R.id.confirmButtonDel);
        goBtn = view.findViewById(R.id.confirmButtonGo);
        confirmPinText = view.findViewById(R.id.confirmPinText);
        confirmPinEditText = view.findViewById(R.id.confirmPinCodeText);

        Bundle bundle = this.getArguments();
        final String uPin = bundle.getString("userPin", null);
        Log.d("___", "onCreateView: " + uPin);


            confirmButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "1");
                }
            });

            confirmButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "2");
                }
            });

            confirmButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "3");
                }
            });

            confirmButton4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "4");
                }
            });

            confirmButton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "5");
                }
            });

            confirmButton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "6");
                }
            });

            confirmButton7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "7");
                }
            });

            confirmButton8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "8");
                }
            });

            confirmButton9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "9");
                }
            });

            confirmButton0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirmPinEditText.setText(confirmPinEditText.getText() + "0");
                }
            });

            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String textString = confirmPinEditText.getText().toString();
                    if (textString.length() > 0) {
                        confirmPinEditText.setText(textString.substring(0, textString.length() - 1));
                        confirmPinEditText.setSelection(confirmPinEditText.getText().length());
                    }

                }
            });

            goBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String confirmedString = confirmPinEditText.getText().toString();
                    if (confirmedString.hashCode() == uPin.hashCode()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("Users").document(userId);
                        Map<String, Object> pin = new HashMap<>();
                        pin.put("onlinePin", confirmPinEditText.getText().toString());
                        documentReference.update(pin).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                getActivity().finish();


                                Intent intent = new Intent(getActivity(), MyProfile.class);
                                startActivity(intent);
                            }
                        });


                    }else {
                        Toast.makeText(getActivity(), "Pin does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        return view;
    }
}
