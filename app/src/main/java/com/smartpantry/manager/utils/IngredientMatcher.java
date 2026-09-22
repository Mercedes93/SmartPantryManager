package com.smartpantry.manager.utils;

import com.smartpantry.manager.database.PantryItem;
import com.smartpantry.manager.database.Recipe;
import com.smartpantry.manager.database.RecipeIngredient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientMatcher {
    public static List<Recipe> getSuggestedRecipes(
            List<Recipe> allRecipes,
            List<RecipeIngredient>allIngredients,
            List<PantryItem>pantryItems ){
        //build recipe id ingredient list map
        Map<Integer, List<RecipeIngredient>> ingredientMap = buildIngredientMap(allIngredients);
        //build normal name with quantities ,unit types for pantry
        Map<String, double[]> pantryMap = buildPantryMap(pantryItems);

        List<Recipe> suggested = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (canMakeRecipe(recipe.getId(), ingredientMap, pantryMap)){
                suggested.add(recipe);
            }
        }
        return suggested;
    }
public static List<Recipe> getAlmostThereRecipes(
        List<Recipe> allRecipes,
        List<RecipeIngredient> allIngredients,
        List<PantryItem> pantryItems) {
        Map<Integer, list<RecipeIngredient>> ingredientMap = buildIngredientMap(allIngredients);
        Map<String, double[]> pantryMap = buildPantryMap(pantryItems);

        List<Recipe> almostThere = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            int missing = countMissingIngredients(recipe.getId(), ingredientMap, pantryMap);
            if (missing == 1) {
                almostthere.add(recipe);
            }
        }
        return almostThere;
}
public static boolean canMakeRecipe(
        int recipeId,
        Map<Integer, List<RecipeIngredient>> ingredientMap,
        Map<String, double[]> pantryMap){
        List<RecipeIngredient> required = ingredientMap.get(recipeId);
        if (required == null || required.isEmpty()) return false;

        for (RecipeIngredient ri : required) {
            String key = normaliseName(ri.getIngredientName());
            double[] pantryEntry = pantryMap.get(key);

            if (pantryEntry == null) return false;

            double requiredBase = toBaseUnit(ri.getQuantity(),ri.getUnit());
            double pantryBase = pantryEntry[0];

            if (pantryBase < requiredBase) return false;
        }
        return true;
}
public static int countMissingIngredients(
        int recipeId,
        Map<Integer, List<RecipeIngredient>> ingredientMap,
        Map<String double[]> pantryMap) {

        List<RecipeIngredient> required = ingredientMap.get(recipeId);
        if (required == null) return Integer.MAX_VALUE;

        int missing = 0;
        for (RecipeIngredient ri: required){
            String key = normaliseName(ri.getIngredientName());
            double [] pantryEntry = pantryMap.get(key);
            if (pantryEntry ==  null) {
                missing++;
                continue;
            }
            double requiredBase = toBaseUnit(ri.getQuantity(), ri.getUnit());
            double pantryBase = pantryEntry[0];
            if (pantryBase < requiredBase) missing++;
        }
        return missing;
}

private static Map<Integer, List <RecipeIngredient>> buildIngredientMap(
        List<RecipeIngredient> allIngredients) {
        Map<Integer , List<RecipeIngredient>> map = new HashMap<>();
        for (RecipeIngredient ri: allIngredients){
            map.computeIfAbsent(ri.getRecipeId(), k -> new ArrayList<>()).add(ri);
        }
        return map;
    }
private static Map<String, double []> buildPantryMap(List<PantryItem> pantryItems) {
        Map<String, double []> map = new HashMap<>();
        for (PantryItem item : pantryItems){
            String key = normaliseName(item.getName());
            double base = toBaseUnit(item.getQuantity(), item.getUnit());
            if (map.containsKey()){
                map.get(key)[0] += base;
            } else {
                map.put(key, new double[] {base});
            }
        }
        return map;
}

public  static String normaliseName(String name) {
        if (name == null) return "";
        String s = name.toLowerCase().trim();

        if (s.endsWith("oes")) && s.length()> 4)
        return s.substring(0, s.length()-2);
 
}


)
}
