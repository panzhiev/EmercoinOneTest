package com.example.tim.emercoinonetest.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tim.emercoinonetest.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Holder> {

    private List<String> listCurrencyPairs;

    public MainAdapter(List<String> listCourencyPairs) {
        this.listCurrencyPairs = listCourencyPairs;
    }

    @Override
    public MainAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rec_view, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.Holder holder, int position) {
        final String pair = listCurrencyPairs.get(holder.getAdapterPosition());
        holder.tvPair.setText(pair);
    }

    @Override
    public int getItemCount() {
        return listCurrencyPairs.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvPair;

        public Holder(View itemView) {
            super(itemView);
            tvPair = (TextView) itemView.findViewById(R.id.tv_item_rv_main);
        }
    }
}
