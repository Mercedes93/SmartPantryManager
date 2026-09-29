package com.smartpantry.manager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smartpantry.manager.R;
import com.smartpantry.manager.activities.AddEditIngredientActivity;
import com.smartpantry.manager.adapters.PantryAdapter;
import com.smartpantry.manager.database.AppDatabase;
import com.smartpantry.manager.database.PantryItem;

public class PantryListFragment extends Fragment {

    private RecyclerView recyclerView;
    private PantryAdapter adapter;
    private TextView tvEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pantry_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewPantry);
        tvEmpty      = view.findViewById(R.id.tvEmptyPantry);
        FloatingActionButton fab = view.findViewById(R.id.fabAddIngredient);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new PantryAdapter(new PantryAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(PantryItem item) {
                Intent intent = new Intent(requireContext(), AddEditIngredientActivity.class);
                intent.putExtra(AddEditIngredientActivity.EXTRA_ITEM_ID, item.getId());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(PantryItem item) {
                confirmDelete(item);
            }
        });

        recyclerView.setAdapter(adapter);

        // Observe LiveData allows UI to update automatically when the database changes
        AppDatabase.getDatabase(requireContext())
                .pantryDao()
                .getAllItems()
                .observe(getViewLifecycleOwner(), items -> {
                    adapter.setItems(items);
                    if (items == null || items.isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        tvEmpty.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        tvEmpty.setVisibility(View.GONE);
                    }
                });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddEditIngredientActivity.class);
            startActivity(intent);
        });
    }

    private void confirmDelete(PantryItem item) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Remove ingredient?")
                .setMessage("Remove \"" + item.getName() + "\" from your pantry?")
                .setPositiveButton("Remove", (dialog, which) -> {
                    AppDatabase.databaseWriteExecutor.execute(() ->
                            AppDatabase.getDatabase(requireContext()).pantryDao().delete(item));
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
