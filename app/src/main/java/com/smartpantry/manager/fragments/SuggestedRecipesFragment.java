package com.smartpantry.manager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smartpantry.manager.R;
import com.smartpantry.manager.activities.RecipeDetailActivity;
import com.smartpantry.manager.adapters.RecipeAdapter;
import com.smartpantry.manager.database.AppDatabase;
import com.smartpantry.manager.database.PantryItem;
import com.smartpantry.manager.database.Recipe;
import com.smartpantry.manager.database.RecipeIngredient;
import com.smartpantry.manager.utils.IngredientMatcher;

import java.util.List;

public class SuggestedRecipesFragment extends Fragment {

    private RecyclerView rvSuggested;
    private RecyclerView rvAlmostThere;
    private RecipeAdapter suggestedAdapter;
    private RecipeAdapter almostThereAdapter;
    private TextView tvAlmostThereHeader;
    private View layoutEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_suggested_recipes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvSuggested         = view.findViewById(R.id.rvSuggestedRecipes);
        rvAlmostThere       = view.findViewById(R.id.rvAlmostThere);
        tvAlmostThereHeader = view.findViewById(R.id.tvAlmostThereHeader);
        layoutEmpty         = view.findViewById(R.id.layoutEmpty);

        rvSuggested.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvAlmostThere.setLayoutManager(new LinearLayoutManager(requireContext()));

        suggestedAdapter    = new RecipeAdapter(recipe -> openDetail(recipe));
        almostThereAdapter  = new RecipeAdapter(recipe -> openDetail(recipe));

        rvSuggested.setAdapter(suggestedAdapter);
        rvAlmostThere.setAdapter(almostThereAdapter);

        AppDatabase.getDatabase(requireContext())
                .pantryDao()
                .getAllItems()
                .observe(getViewLifecycleOwner(), this::runMatching);
    }

    private void runMatching(List<PantryItem> pantryItems) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            AppDatabase db = AppDatabase.getDatabase(requireContext());
            List<Recipe> allRecipes           = db.recipeDao().getAllRecipesSync();
            List<RecipeIngredient> allIngredients = db.recipeDao().getAllIngredients();

            List<Recipe> suggested   = IngredientMatcher.getSuggestedRecipes(
                    allRecipes, allIngredients, pantryItems);
            List<Recipe> almostThere = IngredientMatcher.getAlmostThereRecipes(
                    allRecipes, allIngredients, pantryItems);

            almostThere.removeAll(suggested);

            requireActivity().runOnUiThread(() -> {
                suggestedAdapter.setRecipes(suggested);
                almostThereAdapter.setRecipes(almostThere);

                if (suggested.isEmpty()) {
                    layoutEmpty.setVisibility(View.VISIBLE);
                    rvSuggested.setVisibility(View.GONE);
                } else {
                    layoutEmpty.setVisibility(View.GONE);
                    rvSuggested.setVisibility(View.VISIBLE);
                }

                if (almostThere.isEmpty()) {
                    tvAlmostThereHeader.setVisibility(View.GONE);
                    rvAlmostThere.setVisibility(View.GONE);
                } else {
                    tvAlmostThereHeader.setVisibility(View.VISIBLE);
                    rvAlmostThere.setVisibility(View.VISIBLE);
                }
            });
        });
    }

    private void openDetail(Recipe recipe) {
        Intent intent = new Intent(requireContext(), RecipeDetailActivity.class);
        intent.putExtra(RecipeDetailActivity.EXTRA_RECIPE_ID, recipe.getId());
        startActivity(intent);
    }
}