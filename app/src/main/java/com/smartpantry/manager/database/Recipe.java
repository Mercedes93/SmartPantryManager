package com.smartpantry.manager.database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String prepSteps;//list of prep instructions
    private int servings;
}
public Recipe(String name, String prepSteps, int servings){
    this.name = name;
    this.prepSteps = prepSteps;
    this.servings = servings;

}
//get and set
public int getId( ) {return id; }
public void setId(int id) {this.id = id; }
public String getName( ) {return name; }
public void setName(String name){this.name = name; }
public String getprepSteps( ) {return prepSteps; }
public void setprepSteps(String prepSteps){this.prepSteps = prepSteps; }
}
