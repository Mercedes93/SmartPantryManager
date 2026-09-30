package com.smartpantry.manager.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.smartpantry.manager.R;
import com.smartpantry.manager.database.AppDatabase;

public class SettingsFragment extends Fragment {

    public static final String PREF_EXPIRY_ALERTS = "pref_expiry_alerts";
    public static final String PREF_UNITS         = "pref_units";
    public static final String UNITS_METRIC       = "metric";
    public static final String UNITS_IMPERIAL     = "imperial";

    private SwitchCompat switchExpiryAlerts;
    private RadioGroup rgUnits;
    private SharedPreferences prefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());

        switchExpiryAlerts = view.findViewById(R.id.switchExpiryAlerts);
        rgUnits            = view.findViewById(R.id.rgUnits);
        View btnClearPantry = view.findViewById(R.id.btnClearPantry);
        TextView tvVersion  = view.findViewById(R.id.tvVersion);

        switchExpiryAlerts.setChecked(prefs.getBoolean(PREF_EXPIRY_ALERTS, true));
        String savedUnits = prefs.getString(PREF_UNITS, UNITS_METRIC);
        if (UNITS_IMPERIAL.equals(savedUnits)) {
            rgUnits.check(R.id.rbImperial);
        } else {
            rgUnits.check(R.id.rbMetric);
        }

        tvVersion.setText("Smart Pantry Manager v1.0");

        switchExpiryAlerts.setOnCheckedChangeListener((btn, isChecked) ->
                prefs.edit().putBoolean(PREF_EXPIRY_ALERTS, isChecked).apply());

        rgUnits.setOnCheckedChangeListener((group, checkedId) -> {
            String units = (checkedId == R.id.rbImperial) ? UNITS_IMPERIAL : UNITS_METRIC;
            prefs.edit().putString(PREF_UNITS, units).apply();
            Toast.makeText(requireContext(), "Units set to " + units, Toast.LENGTH_SHORT).show();
        });

        btnClearPantry.setOnClickListener(v -> confirmClearPantry());
    }

    private void confirmClearPantry() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Clear pantry?")
                .setMessage("This will permanently remove all ingredients. This cannot be undone.")
                .setPositiveButton("Clear", (dialog, which) -> {
                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        AppDatabase.getDatabase(requireContext()).pantryDao().deleteAll();
                        requireActivity().runOnUiThread(() ->
                                Toast.makeText(requireContext(), "Pantry cleared.", Toast.LENGTH_SHORT).show());
                    });
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}