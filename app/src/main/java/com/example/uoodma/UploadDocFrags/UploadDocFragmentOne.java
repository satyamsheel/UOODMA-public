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

public class UploadDocFragmentOne extends Fragment {

    Button frag1Btn1, frag1Btn2, frag1Btn3, frag1Btn4, frag1Btn5, frag1Btn6, frag1Btn7, frag1Btn8, frag1Btn9, frag1Btn10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upload_doc_frag_one, container, false);

        frag1Btn1 = view.findViewById(R.id.frag1btn1);
        frag1Btn2 = view.findViewById(R.id.frag1btn2);
        frag1Btn3 = view.findViewById(R.id.frag1btn3);
        frag1Btn4 = view.findViewById(R.id.frag1btn4);
        frag1Btn5 = view.findViewById(R.id.frag1btn5);
        frag1Btn6 = view.findViewById(R.id.frag1btn6);
        frag1Btn7 = view.findViewById(R.id.frag1btn7);
        frag1Btn8 = view.findViewById(R.id.frag1btn8);
        frag1Btn9 = view.findViewById(R.id.frag1btn9);
        frag1Btn10 = view.findViewById(R.id.frag1btn10);


        return view;
    }
}
