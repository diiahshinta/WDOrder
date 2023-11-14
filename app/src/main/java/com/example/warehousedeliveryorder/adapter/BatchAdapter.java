package com.example.warehousedeliveryorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.warehousedeliveryorder.R;
import com.example.warehousedeliveryorder.ResponseKonfirmasi;

import java.util.ArrayList;

public class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.ViewHolder> {
    ArrayList<ResponseKonfirmasi> mList;
    Context context;
    private LayoutInflater mInflater;

    public BatchAdapter(Context context, ArrayList<ResponseKonfirmasi> mList){
        this.mList = mList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_batch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.batch.setText("No. Batch = " + mList.get(position).getBatch());
        holder.ed.setText("Expired Date = " + mList.get(position).getEd());
        holder.total.setText(mList.get(position).getSubtotal() + " Pcs");
          }


    // total number of rows
    @Override
    public int getItemCount() {
        return mList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView batch, ed, total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            batch = itemView.findViewById(R.id.batch);
            ed = itemView.findViewById(R.id.ed);
            total = itemView.findViewById(R.id.total);
        }
    }

}
