package com.example.bullet.recipesearch.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.bullet.recipesearch.data.Recipe_contract.recipe_entry;
/**
 * Created by Bharat on 12/19/17.
 */

public class    Recipedbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="ExoticRecipes.db";
    private static final int DATABAE_VERSION= 1;
    public Recipedbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABAE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String SQL_CREATE_RECIPE_TABLE="CREATE TABLE "+recipe_entry.Table_name+"("
            +recipe_entry._id+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +recipe_entry.Column_Recipe_Image+" BLOB,       "
            +recipe_entry.Column_Recipe_label+" TEXT NOT NULL, "
            +recipe_entry.Column_Recipe_Ingredients+" TEXT NOT NULL, "
            +recipe_entry.Column_Recipe_Diet_labels+" TEXT, "
            +recipe_entry.Column_Recipe_Health_labels+" TEXT, "
            +recipe_entry.Column_Recipe_Nutri_label+" TEXT NOT NULL, "
            +recipe_entry.Column_Recipe_Nutri_Quantity+" TEXT NOT NULL);";
           db.execSQL(SQL_CREATE_RECIPE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
