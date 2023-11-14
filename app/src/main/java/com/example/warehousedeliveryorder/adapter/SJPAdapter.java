package com.example.warehousedeliveryorder.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousedeliveryorder.R;
import com.example.warehousedeliveryorder.ScannerActivity;
import com.example.warehousedeliveryorder.get_set.Item;

import java.util.ArrayList;
import java.util.List;

public class SJPAdapter extends RecyclerView.Adapter<SJPAdapter.ViewHolder> {
    private static ClickListener clickListener;
    List<Item> mList;
    Context context;
    private LayoutInflater mInflater;

    public SJPAdapter(Context context, List<Item> mList){
        this.mList = mList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_data_sjp, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.no.setText(mList.get(position).getNomor_sjp());
        holder.id.setText(mList.get(position).getId_sjp());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScannerActivity.class);
                intent.putExtra("idSjp", mList.get(position).getId_sjp());
                context.startActivity(intent);
            }
        });
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface ClickListener {
        void onItemClick(int position, View v);
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        SJPAdapter.clickListener = clickListener;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView no, id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            no = itemView.findViewById(R.id.no_sjp);
            id = itemView.findViewById(R.id.id_sjp);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), itemView);
        }
    }

    public void setFilter(ArrayList<Item> filter){
        mList = new ArrayList<>();
        mList.addAll(filter);
        notifyDataSetChanged();
    }

}
