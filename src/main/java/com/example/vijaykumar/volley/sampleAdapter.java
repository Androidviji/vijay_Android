package com.example.vijaykumar.volley;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class sampleAdapter extends RecyclerView.Adapter<sampleAdapter.MyViewHolder> {

    private ArrayList<sampleinfo> sampleList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView jsonData,jsondat1,jsondata2;


        public MyViewHolder(View view) {
            super(view);
            jsonData = (TextView) view.findViewById(R.id.json1);
            jsondat1 = (TextView) view.findViewById(R.id.json2);
            jsondata2 = (TextView) view.findViewById(R.id.json3);


        }
    }


    public sampleAdapter(ArrayList<sampleinfo> sampleList) {
        this.sampleList = sampleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_test , parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        sampleinfo data = sampleList.get(position);
        holder.jsonData.setText(data.getmId());
        holder.jsondat1.setText(data.getName());
        holder.jsondata2.setText(data.getNumber());

    }

    @Override
    public int getItemCount() {
        return sampleList.size();
    }
}