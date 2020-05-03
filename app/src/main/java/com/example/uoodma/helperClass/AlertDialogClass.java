package com.example.uoodma.helperClass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.uoodma.R;

public class AlertDialogClass extends AppCompatDialogFragment {

    private TextView alertDialogContentRecieved;
    private EditText alertDialogUIDText;
    private AlertDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.alert_dialog, null);
        builder.setView(view)
                .setTitle("Scan Succesfull")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String recivingUID = alertDialogUIDText.getText().toString();
                        listener.applyTexts(recivingUID);
                    }
                });

        alertDialogUIDText = view.findViewById(R.id.alertDialogUIDText);
        alertDialogContentRecieved = view.findViewById(R.id.alertDialogContentRecieved);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AlertDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "its a must");
        }
    }

    public interface AlertDialogListener {
        void applyTexts(String alertDialogUIDText);
    }
}
