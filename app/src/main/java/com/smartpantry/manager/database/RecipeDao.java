package com.smartpantry.manager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
public interface RecipeDao {
    //Add new recipes to recipes table. Long creates auto id for new
    // inserted recipe which is then linked immediately
    // so ingredients is inserted to ingredients list OnConflictStrat ignores if already exists
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertRecipe(Recipe recipe);
    //add one ingredient to recipe_ingredients table.Linked by id created above and called only once
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertIngredient(RecipeIngredient ingredient);
    //get all recipes sorted A-Z
    @Query("SELECT * FROM recipes ORDER BY name ASC")
    LiveData<List<Recipe>> getAllRecipes( );

    //Same as above but returns plain list
    @Query("SELECT * FROM recipes ORDER BY name ASC")
    List<Recipe> getAllRecipesSync( );
    //Get on full recipe by id which will be used by RecipeDetailActivity to load the name, servings and prep steps
    @Query("SELECT * FROM recipes WHERE id = :id LIMIT 1")
    Recipe getRecipeById(int id);
    //Fetch all ingredients rows from all recipes at once and match against pantry
    @Query("SELECT * FROM recipe_ingredients WHERE recipeId = :recipeId")
    List<RecipeIngredient> getIngredientsByRecipeId(int recipeId);

    @Query("SELECT * FROM recipe_ingredients")
    List<RecipeIngredient> getAllIngredients( );
    //return how many recipes are in db if > 0 skip so no duplicates are loaded
    @Query("SELECT COUNT(*) FROM recipes")
    int getRecipeCount( );
}

