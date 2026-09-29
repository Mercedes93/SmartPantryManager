package com.smartpantry.manager.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smartpantry.manager.R;
import com.smartpantry.manager.database.AppDatabase;
import com.smartpantry.manager.database.Recipe;
import com.smartpantry.manager.database.RecipeIngredient;

import java.util.List;

/// Displays the full details of one recipe:
///   \- Name and servings
///   \- Full ingredient list with quantities
///   \- Step-by-step preparation instructions
/// Receives EXTRA\_RECIPE\_ID (int) from the calling fragment via Intent.
public class RecipeDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RECIPE_ID = "extra_recipe_id";

    private TextView tvRecipeName;
    private TextView tvServings;
    private TextView tvIngredients;
    private TextView tvSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvRecipeName  = findViewById(R.id.tvRecipeName);
        tvServings    = findViewById(R.id.tvServings);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvSteps       = findViewById(R.id.tvSteps);

        int recipeId = getIntent().getIntExtra(EXTRA_RECIPE_ID, -1);
        if (recipeId == -1) {
            finish();
            return;
        }

        loadRecipe(recipeId);
    }

    private void loadRecipe(int recipeId) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(this);
            Recipe recipe = db.recipeDao().getRecipeById(recipeId);
            List<RecipeIngredient> ingredients = db.recipeDao().getIngredientsByRecipeId(recipeId);

            if (recipe == null) {
                runOnUiThread(this::finish);
                return;
            }

            // Build ingredient list string
            StringBuilder sb = new StringBuilder();
            for (RecipeIngredient ing : ingredients) {
                double qty = ing.getQuantity();
                String qtyStr = (qty == Math.floor(qty))
                        ? String.valueOf((int) qty)
                        : String.format("%.1f", qty);
                sb.append("• ")
                        .append(qtyStr)
                        .append(" ")
                        .append(ing.getUnit())
                        .append("  ")
                        .append(capitalize(ing.getIngredientName()))
                        .append("\n");
            }

            String ingredientText = sb.toString().trim();

            runOnUiThread(() -> {
                setTitle(recipe.getName());
                tvRecipeName.setText(recipe.getName());
                tvServings.setText("Serves " + recipe.getServings());
                tvIngredients.setText(ingredientText);
                tvSteps.setText(recipe.getPrepSteps());
            });
        });
    }

    private String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
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
