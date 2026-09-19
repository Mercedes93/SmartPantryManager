package com.smartpantry.manager.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//This is the main db v1 for future migrations and exportSchema set to false so nothing saves to disk

@Database(
        entities = {PantryItem.class, Recipe.class, RecipeIngredient.class},
        version = 1
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    //In one instance of the db only one thread writes to it  and other threads see the updated value.
    //prevents two threads from creating two separate dbs instance at the same time
    private static volatile AppDatabase INSTANCE;
    //Any insert ,update or delete must be submitted to this executor
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);
    //user can be anywhere in app to call and get the dao to run queries
    public abstract PantryDao pantryDao( );
    public abstract RecipeDao recipeDao ( );
    //Prevent two threads from passing the first null check at the same time and building db at the same time
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "smart_pantry_db"
                    )
                            //runs once when db is first created on device and load recipes so it doesnt block ui
                            .addCallback(new RoomDatabase.Callback()){
                        @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db){
                            super.onCreate(db);
                            databaseWriteExecutor.execute(( ) ->){
                                DatabaseSeeder.seedRecipes(INSTANCE.recipeDao());
                            };
                        }
                    })
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
