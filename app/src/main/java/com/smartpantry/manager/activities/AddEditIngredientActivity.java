package com.smartpantry.manager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smartpantry.manager.R;
import com.smartpantry.manager.database.AppDatabase;
import com.smartpantry.manager.database.PantryItem;

/// Handles both Add and Edit for a single pantry item.
/// Pass EXTRA\_ITEM\_ID (int) via Intent to enter edit mode.
public class AddEditIngredientActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_ID = "extra_item_id";
    private static final int NO_ID = -1;

    private EditText etName, etQuantity, etExpiry;
    private Spinner spinnerUnit;

    private int editingId = NO_ID;

    private static final String[] UNITS = {
            "g", "kg", "ml", "l", "cup", "tbsp", "tsp", "pcs"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etName     = findViewById(R.id.etName);
        etQuantity = findViewById(R.id.etQuantity);
        etExpiry   = findViewById(R.id.etExpiry);
        etExpiry.addTextChangedListener(new android.text.TextWatcher() {
            private boolean editing = false;

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(android.text.Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editing) return;
                editing = true;
                String digits = s.toString().replace("-", "");
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < digits.length() && i < 8; i++) {
                    formatted.append(digits.charAt(i));
                    if (i == 3 || i == 5) formatted.append("-");
                }
                etExpiry.setText(formatted.toString());
                etExpiry.setSelection(formatted.length());
                editing = false;
            }
        });
        spinnerUnit = findViewById(R.id.spinnerUnit);
        Button btnSave = findViewById(R.id.btnSave);

        // Populate unit spinner
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, UNITS);
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(unitAdapter);

        // Check for edit mode
        editingId = getIntent().getIntExtra(EXTRA_ITEM_ID, NO_ID);
        if (editingId != NO_ID) {
            setTitle("Edit Ingredient");
            loadExistingItem(editingId);
        } else {
            setTitle("Add Ingredient");
        }

        btnSave.setOnClickListener(v -> saveItem());
    }

    private void loadExistingItem(int id) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            PantryItem item = AppDatabase.getDatabase(this).pantryDao().getItemById(id);
            if (item != null) {
                runOnUiThread(() -> {
                    etName.setText(item.getName());
                    etQuantity.setText(String.valueOf(item.getQuantity()));
                    if (item.getExpiryDate() != null) etExpiry.setText(item.getExpiryDate());

                    // Set spinner to the item's unit
                    for (int i = 0; i < UNITS.length; i++) {
                        if (UNITS[i].equals(item.getUnit())) {
                            spinnerUnit.setSelection(i);
                            break;
                        }
                    }
                });
            }
        });
    }

    private void saveItem() {
        String name    = etName.getText().toString().trim();
        String qtyStr  = etQuantity.getText().toString().trim();
        String expiry  = etExpiry.getText().toString().trim();
        String unit    = spinnerUnit.getSelectedItem().toString();

        // Validation
        boolean valid = true;

        if (TextUtils.isEmpty(name)) {
            etName.setError("Ingredient name is required");
            valid = false;
        }

        double quantity = 0;
        if (TextUtils.isEmpty(qtyStr)) {
            etQuantity.setError("Quantity is required");
            valid = false;
        } else {
            try {
                quantity = Double.parseDouble(qtyStr);
                if (quantity <= 0) {
                    etQuantity.setError("Quantity must be greater than 0");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                etQuantity.setError("Please enter a valid number");
                valid = false;
            }
        }

        // Validate expiry date format if provided (YYYY-MM-DD)
        if (!TextUtils.isEmpty(expiry) && !expiry.matches("\\d{4}-\\d{2}-\\d{2}")) {
            etExpiry.setError("Use format YYYY-MM-DD (e.g. 2025-12-31)");
            valid = false;
        }

        if (!valid) return;

        // Persist
        double finalQuantity = quantity;
        AppDatabase.databaseWriteExecutor.execute(() -> {
            if (editingId == NO_ID) {
                PantryItem newItem = new PantryItem(
                        name, finalQuantity, unit, expiry.isEmpty() ? null : expiry);
                AppDatabase.getDatabase(this).pantryDao().insert(newItem);
            } else {
                PantryItem item = AppDatabase.getDatabase(this).pantryDao().getItemById(editingId);
                if (item != null) {
                    item.setName(name);
                    item.setQuantity(finalQuantity);
                    item.setUnit(unit);
                    item.setExpiryDate(expiry.isEmpty() ? null : expiry);
                    AppDatabase.getDatabase(this).pantryDao().update(item);
                }
            }
            runOnUiThread(() -> {
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}