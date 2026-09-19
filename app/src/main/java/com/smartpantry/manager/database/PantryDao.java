package com.smartpantry.manager.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PantryDao {
    @insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PantryItem item);

    @Update
    void update(PantryItem item);

    @Delete
    void delete(PantryItem item);
    //return all panty items sorted A-Z in live data so that UI automatically refresh whenever pantry changes
    @Query("SELECT * FROM pantry_items ORDER BY name ASC")
    LiveData<List<PantryItem>> getAllItems( );
    //same as above but return plain list instead of livedata.only run once in background not observing continuously
    @Query("SELECT * FROM pantry_items ORDER BY name ASC")
    List<PantryItem> getAllItemsSync( );
    //Fetch one specific item by id for edit on item
    @Query("SELECT * FROM pantry_items WHERE id= :id LIMIT 1")
    PantryItem getItemById(int id);
    //Clears all pantry items for clear all button
    @Query("DELETE FROM pantry_items")
    void deleteAll( );
}
