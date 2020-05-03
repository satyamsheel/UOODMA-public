package com.example.uoodma.helperClass;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uoodma.R;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.Viewholder> {
private String[] data;
    public recyclerAdapter(String[] data){
   this.data=data;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard,parent,false);
     return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
     String text=data[position];
     holder.item.setText(text);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView item;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView4);
            item = itemView.findViewById(R.id.dashboardText);
        }
    }
}
