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

public class UploadDocFragmentFive extends Fragment {

    Button frag5Btn1, frag5Btn2, frag5Btn3, frag5Btn4, frag5Btn5, frag5Btn6, frag5Btn7, frag5Btn8, frag5Btn9, frag5Btn10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.upload_doc_frag_five, container, false);
       
        frag5Btn1 = view.findViewById(R.id.frag5btn1);
        frag5Btn2 = view.findViewById(R.id.frag5btn2);
        frag5Btn3 = view.findViewById(R.id.frag5btn3);
        frag5Btn4 = view.findViewById(R.id.frag5btn4);
        frag5Btn5 = view.findViewById(R.id.frag5btn5);
        frag5Btn6 = view.findViewById(R.id.frag5btn6);
        frag5Btn7 = view.findViewById(R.id.frag5btn7);
        frag5Btn8 = view.findViewById(R.id.frag5btn8);
        frag5Btn9 = view.findViewById(R.id.frag5btn9);
        frag5Btn10 = view.findViewById(R.id.frag5btn10);
        
        return view;

    }
}
