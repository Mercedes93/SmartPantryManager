package com.smartpantry.manager.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartpantry.manager.R;
import com.smartpantry.manager.database.PantryItem;

import java.util.ArrayList;
import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    public interface OnItemClickListener {
        void onEditClick(PantryItem item);
        void onDeleteClick(PantryItem item);
    }

    private List<PantryItem> items = new ArrayList<>();
    private final OnItemClickListener listener;

    public PantryAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<PantryItem> newItems) {
        this.items = newItems != null ? newItems : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = items.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //ViewHolder
    static class PantryViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvQuantity;
        private final TextView tvExpiry;
        private final ImageButton btnEdit;
        private final ImageButton btnDelete;

        PantryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName     = itemView.findViewById(R.id.tvIngredientName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvExpiry   = itemView.findViewById(R.id.tvExpiry);
            btnEdit    = itemView.findViewById(R.id.btnEdit);
            btnDelete  = itemView.findViewById(R.id.btnDelete);
        }

        void bind(PantryItem item, OnItemClickListener listener) {
            tvName.setText(capitalize(item.getName()));
            tvQuantity.setText(formatQuantity(item.getQuantity(), item.getUnit()));

            if (item.getExpiryDate() != null && !item.getExpiryDate().isEmpty()) {
                tvExpiry.setVisibility(View.VISIBLE);
                tvExpiry.setText("Expires: " + item.getExpiryDate());
            } else {
                tvExpiry.setVisibility(View.GONE);
            }

            btnEdit.setOnClickListener(v -> listener.onEditClick(item));
            btnDelete.setOnClickListener(v -> listener.onDeleteClick(item));
        }

        private String capitalize(String s) {
            if (s == null || s.isEmpty()) return s;
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }

        private String formatQuantity(double qty, String unit) {
            // Show as integer if whole number, otherwise 2 decimal places
            String qtyStr = (qty == Math.floor(qty))
                    ? String.valueOf((int) qty)
                    : String.format("%.2f", qty);
            return qtyStr + " " + (unit != null ? unit : "");
        }
    }
}
