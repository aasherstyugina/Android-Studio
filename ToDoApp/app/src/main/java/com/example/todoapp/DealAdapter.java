package com.example.todoapp;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.ViewHolder>
        implements ItemTouchHelperAdapter {
    private final LayoutInflater inflater;
    private final List<Deal> deals;

    public DealAdapter(Context context, List<Deal> deals) {
        this.deals = deals;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Deal deal = deals.get(position);
        String text = deal.getText();
        String date = "";
        if(deal.isDateSet()){
            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
            date = formatter.format(deal.getDate().getTime());
            text += "\nВыполнить до " + date;
        }
        holder.textView.setText(text);
        switch (deal.getImportance()){
            case 0:
                holder.layout.setBackgroundResource(R.drawable.item_background_green);
                break;
            case 1:
                holder.layout.setBackgroundResource(R.drawable.item_background_orange);
                break;
            case 2:
                holder.layout.setBackgroundResource(R.drawable.item_background_red);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    @Override
    public void onItemDismiss(int position) {
        MainActivity.deals.remove(position);
        this.notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final LinearLayout layout;

        ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            layout = view.findViewById(R.id.linearLayout);
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(MainActivity.deals, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(MainActivity.deals, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}
