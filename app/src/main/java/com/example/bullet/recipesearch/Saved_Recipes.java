package com.example.bullet.recipesearch;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.bullet.recipesearch.data.Recipe_contract.recipe_entry;
import com.example.bullet.recipesearch.data.Recipedbhelper;

import java.util.ArrayList;

public class Saved_Recipes extends AppCompatActivity {

    private Recipedbhelper mDbHelper ;
    private ListView displayView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved__recipes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayView = (ListView) findViewById(R.id.downloaded_Recipes);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mDbHelper=new Recipedbhelper(this);
        displayDatabaseInfo(mDbHelper);
     displayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 DisplaySelectedItemInfo(position);
            }
        });

    }
    private void DisplaySelectedItemInfo(int _id){
       SQLiteDatabase selected= mDbHelper.getReadableDatabase();
        String[] projection = {
                recipe_entry._ID,
                recipe_entry.Column_Recipe_Image,
                recipe_entry.Column_Recipe_label,
                recipe_entry.Column_Recipe_Ingredients,
                recipe_entry.Column_Recipe_Diet_labels,
                recipe_entry.Column_Recipe_Health_labels,
                recipe_entry.Column_Recipe_Nutri_label,
                recipe_entry.Column_Recipe_Nutri_Quantity};

        Cursor cursor = selected.query(
                recipe_entry.Table_name,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        cursor.moveToPosition(_id);
        byte[] img=cursor.getBlob(cursor.getColumnIndex(recipe_entry.Column_Recipe_Image));
        String savedlabel= cursor.getString(cursor.getColumnIndex(recipe_entry.Column_Recipe_label));
        String savedINGRE= cursor.getString(cursor.getColumnIndex(recipe_entry.Column_Recipe_Ingredients));
        String savedDlabel= cursor.getString(cursor.getColumnIndex(recipe_entry.Column_Recipe_Diet_labels));
        String savedHlabel= cursor.getString(cursor.getColumnIndex(recipe_entry.Column_Recipe_Health_labels));
        String savedNlabel= cursor.getString(cursor.getColumnIndex(recipe_entry.Column_Recipe_Nutri_label));
        String savedNquantity= cursor.getString(cursor.getColumnIndex(recipe_entry.Column_Recipe_Nutri_Quantity));
        Intent i=new Intent(this,OFLINE_RECIPE.class);
        i.putExtra("img",img);
        i.putExtra("saved label",savedlabel);
        i.putExtra("saved Ingre",savedINGRE);
        i.putExtra("saved Dietlabel",savedDlabel);
        i.putExtra("saved Healthlabel",savedHlabel);
        i.putExtra("saved Nutrilabel",savedNlabel);
        i.putExtra("saved Nutriquantity",savedNquantity);
        this.startActivity(i);
    }


    private void displayDatabaseInfo(Recipedbhelper mDbHelper) {
        // Create and/or open a database to read from it
        SQLiteDatabase db1 = mDbHelper.getReadableDatabase();
        final  ArrayList<String> labellist=new ArrayList<>();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                recipe_entry._ID,
                recipe_entry.Column_Recipe_Image,
                recipe_entry.Column_Recipe_label,
                recipe_entry.Column_Recipe_Ingredients,
                recipe_entry.Column_Recipe_Diet_labels,
                recipe_entry.Column_Recipe_Health_labels,
                recipe_entry.Column_Recipe_Nutri_label,
                recipe_entry.Column_Recipe_Nutri_Quantity};

        //Cursor cursor=db1.rawQuery("SELECT * FROM "+recipe_entry.Table_name,null);
        // Perform a query on the pets table
       Cursor cursor = db1.query(
                recipe_entry.Table_name,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order



        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
           // Log.d( "displayDatabaseInfo: ",cursor.getString(cursor.getColumnIndex(recipe_entry.Column_Recipe_label)));
           /* displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(recipe_entry._ID + " - " +
                    recipe_entry.Column_Recipe_label + " - " +
                    recipe_entry.Column_Recipe_Diet_labels + " - " +
                    recipe_entry.Column_Recipe_Health_labels + "\n");
             */
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(recipe_entry._ID);
            int nameColumnIndex = cursor.getColumnIndex(recipe_entry.Column_Recipe_label);
            int breedColumnIndex = cursor.getColumnIndex(recipe_entry.Column_Recipe_Ingredients);
            int genderColumnIndex = cursor.getColumnIndex(recipe_entry.Column_Recipe_Diet_labels);
            int weightColumnIndex = cursor.getColumnIndex(recipe_entry.Column_Recipe_Health_labels);
            cursor.moveToFirst();
            // Iterate through all the returned rows in the cursor
            while (!cursor.isAfterLast()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentlabel = cursor.getString(nameColumnIndex);
                //Log.d( "current label ",currentName);
               // String currentBreed = cursor.getString(breedColumnIndex);
                String currentGender = cursor.getString(genderColumnIndex);
                String currentWeight = cursor.getString(weightColumnIndex);
                labellist.add(currentlabel);
                // Display the values from each column of the current row in the cursor in the TextView
                /*displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        //currentBreed + " - " +
                        currentGender + " - " +
                        currentWeight));
                */
                cursor.moveToNext();

            }
            ListAdapter labeladapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, labellist);
            displayView.setAdapter(labeladapter);
        } catch (Exception e){e.printStackTrace();}
        finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
