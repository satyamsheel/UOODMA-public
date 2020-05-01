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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UploadDocFragmentFour extends Fragment {

    Button frag4Btn1, frag4Btn2, frag4Btn3, frag4Btn4, frag4Btn5, frag4Btn6, frag4Btn7, frag4Btn8, frag4Btn9, frag4Btn10;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upload_doc_frag_four, container, false);


        frag4Btn1 = view.findViewById(R.id.frag4btn1);
        frag4Btn2 = view.findViewById(R.id.frag4btn2);
        frag4Btn3 = view.findViewById(R.id.frag4btn3);
        frag4Btn4 = view.findViewById(R.id.frag4btn4);
        frag4Btn5 = view.findViewById(R.id.frag4btn5);
        frag4Btn6 = view.findViewById(R.id.frag4btn6);
        frag4Btn7 = view.findViewById(R.id.frag4btn7);
        frag4Btn8 = view.findViewById(R.id.frag4btn8);
        frag4Btn9 = view.findViewById(R.id.frag4btn9);
        frag4Btn10 = view.findViewById(R.id.frag4btn10);


        return view;
    }
}
