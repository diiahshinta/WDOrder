package com.example.warehousedeliveryorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.warehousedeliveryorder.R;
import com.example.warehousedeliveryorder.ResponseGudang;

import java.util.ArrayList;

public class MutasiAdapter extends RecyclerView.Adapter<MutasiAdapter.ViewHolder> {
    private static ClickListener clickListener;
    ArrayList<ResponseGudang> mList;
    Context context;
    private LayoutInflater mInflater;

    public MutasiAdapter(Context context, ArrayList<ResponseGudang> mList){
        this.mList = mList;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_mutasi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(mList.get(position).getId_barang_masuk_gudang());
        holder.tanggal.setText(mList.get(position).getKeterangan_barang_masuk() + " Kode : " + mList.get(position).getKode_sertem_produksi());
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
        MutasiAdapter.clickListener = clickListener;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id, tanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_tgl);
            tanggal = itemView.findViewById(R.id.tanggal);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), itemView);
        }
    }

    public void setFilter(ArrayList<ResponseGudang> filter){
        mList = new ArrayList<>();
        mList.addAll(filter);
        notifyDataSetChanged();
    }

}
