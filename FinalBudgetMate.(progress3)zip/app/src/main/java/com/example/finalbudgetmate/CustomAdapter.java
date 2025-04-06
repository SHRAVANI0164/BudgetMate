package com.example.finalbudgetmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myviewholder> {
    ArrayList<Model> dataholder;

    public CustomAdapter(ArrayList<Model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.dnote.setText(dataholder.get(position).getName());
        holder.damount.setText(dataholder.get(position).getContact());
        holder.dcategory.setText(dataholder.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder {
        TextView dnote, damount, dcategory;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            dnote = (TextView) itemView.findViewById(R.id.displayname);
            damount = (TextView) itemView.findViewById(R.id.displaycontact);
            dcategory = (TextView) itemView.findViewById(R.id.displayemail);
        }
    }

}
