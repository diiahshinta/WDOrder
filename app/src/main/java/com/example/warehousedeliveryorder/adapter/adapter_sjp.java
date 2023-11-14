package com.example.warehousedeliveryorder.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.warehousedeliveryorder.R;
import com.example.warehousedeliveryorder.get_set.Item;

import java.util.ArrayList;
import java.util.List;

public class adapter_sjp extends RecyclerView.Adapter<adapter_sjp.Holder> {

    private static final String TAG = adapter_sjp.class.getSimpleName();
    private List<Item> mtr = new ArrayList<>();
    private List<Item> mtrFiltered;
    private final data_kirim mListen;
    ProgressDialog loading;
    private Context context;

    public adapter_sjp(data_kirim listener) {
        mtr = new ArrayList<>();
        mListen = listener;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_sjp, parent, false);
        context = parent.getContext();
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Item daftar = mtr.get(position);
        holder.id_sjp.setText(daftar.getId_sjp());
        holder.no_sjp.setText(daftar.getNomor_sjp());
    }

    @Override
    public int getItemCount() {
        return mtr.size();
    }


    public void adddata(Item daftar_sjp) {
        mtr.add(daftar_sjp);
        notifyDataSetChanged();
    }

    public void clear() {
        mtr.clear();
        notifyDataSetChanged();
    }

    public Item getAmbildata(int position) {
        return mtr.get(position);
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id_sjp,no_sjp;
        public Holder(View itemView) {
            super(itemView);
            id_sjp = itemView.findViewById(R.id.id_sjp);
            no_sjp = itemView.findViewById(R.id.no_sjp);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListen.onClick(getLayoutPosition());
        }
    }

    public interface data_kirim {
        void onClick(int position);
    }

    public void setFilter(ArrayList<Item> filterModel) {
        mtr = new ArrayList<>();
        mtr.addAll(filterModel);
        notifyDataSetChanged();
    }


}
