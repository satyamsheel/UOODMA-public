package com.example.uoodma.UploadDocFrags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uoodma.R;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UploadDocFragmentTwo extends Fragment {
    Button frag2Btn1, frag2Btn2, frag2Btn3, frag2Btn4, frag2Btn5, frag2Btn6, frag2Btn7, frag2Btn8, frag2Btn9, frag2Btn10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.upload_doc_frag_two, container, false);

        frag2Btn1 = view.findViewById(R.id.frag2btn1);
        frag2Btn2 = view.findViewById(R.id.frag2btn2);
        frag2Btn3 = view.findViewById(R.id.frag2btn3);
        frag2Btn4 = view.findViewById(R.id.frag2btn4);
        frag2Btn5 = view.findViewById(R.id.frag2btn5);
        frag2Btn6 = view.findViewById(R.id.frag2btn6);
        frag2Btn7 = view.findViewById(R.id.frag2btn7);
        frag2Btn8 = view.findViewById(R.id.frag2btn8);
        frag2Btn9 = view.findViewById(R.id.frag2btn9);
        frag2Btn10 = view.findViewById(R.id.frag2btn10);

        return view;
    }
}
