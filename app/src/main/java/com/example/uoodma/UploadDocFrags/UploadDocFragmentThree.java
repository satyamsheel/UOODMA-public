package com.example.uoodma.UploadDocFrags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uoodma.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UploadDocFragmentThree extends Fragment {

    Button frag3Btn1, frag3Btn2, frag3Btn3, frag3Btn4, frag3Btn5, frag3Btn6, frag3Btn7, frag3Btn8, frag3Btn9, frag3Btn10;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upload_doc_frag_three, container, false);

        frag3Btn1 = view.findViewById(R.id.frag3btn1);
        frag3Btn2 = view.findViewById(R.id.frag3btn2);
        frag3Btn3 = view.findViewById(R.id.frag3btn3);
        frag3Btn4 = view.findViewById(R.id.frag3btn4);
        frag3Btn5 = view.findViewById(R.id.frag3btn5);
        frag3Btn6 = view.findViewById(R.id.frag3btn6);
        frag3Btn7 = view.findViewById(R.id.frag3btn7);
        frag3Btn8 = view.findViewById(R.id.frag3btn8);
        frag3Btn9 = view.findViewById(R.id.frag3btn9);
        frag3Btn10 = view.findViewById(R.id.frag3btn10);
        
       return view;
    }
}
