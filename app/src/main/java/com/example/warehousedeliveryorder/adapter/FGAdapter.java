package com.example.warehousedeliveryorder.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.warehousedeliveryorder.R;
import com.example.warehousedeliveryorder.get_set.data_scan;

import java.util.ArrayList;

public class FGAdapter extends RecyclerView.Adapter<FGAdapter.ViewHolder> {
    private ArrayList<data_scan> data_scanArrayList;
    private Context context;
    String json,data_kirim;
    SharedPreferences sharedPreferences;
    private OnItemListener onItemListener;

    public FGAdapter(ArrayList<data_scan> data_scanArrayList, Context context) {
        this.data_scanArrayList = data_scanArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_scan, parent, false);
        view.setOnLongClickListener(new RV_ItemListener());
        return new ViewHolder(view);
    }

    public void clear() {
        int size = data_scanArrayList.size();
        data_scanArrayList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public  interface OnItemListener{
        void OnItemLongClickListener(View view, int position);
    }

    class RV_ItemListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view) {
            if (onItemListener != null){
                onItemListener.OnItemLongClickListener(view,view.getId());
            }
            return true;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bundle extras = ((Activity) context).getIntent().getExtras();
        data_kirim= extras.getString("kirim");
        sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
        json = sharedPreferences.getString("datafg", null);

        data_scan modal = data_scanArrayList.get(position);
        holder.txtscan.setText(modal.getDatascan());
        holder.itemView.setId(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getString("datafg" , modal.getDatascan()) !=null){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("datafg" + position);
                    editor.commit();
                    clear();
                }
                sharedPreferences.edit().remove(modal.getDatascan()).commit();
                clear();
                Toast.makeText(context.getApplicationContext(),modal.getDatascan(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setOnItemListenerListener(OnItemListener listener){
        this.onItemListener = listener;
    }

    @Override
    public int getItemCount() {
        return data_scanArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtscan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtscan = itemView.findViewById(R.id.scan_qrcode);


//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, txtscan.getText().toString(), Toast.LENGTH_LONG).show();
//                }
//            });
        }

//            notifyDataSetChanged();
//            Toast.makeText(context,"The Item Clicked is: "+getPosition(),Toast.LENGTH_SHORT).show();
    }
}
