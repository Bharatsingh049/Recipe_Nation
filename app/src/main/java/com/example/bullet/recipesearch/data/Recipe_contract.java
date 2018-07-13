package com.example.bullet.recipesearch.data;

import android.provider.BaseColumns;


import java.util.ArrayList;

/**
 * Created by Bharat on 12/19/17.
 */

public final class Recipe_contract {

    private Recipe_contract(){}

    public static final class recipe_entry implements BaseColumns {

        public final static String Table_name="Recipes";

        public final static String _id=BaseColumns._ID;
        public final static String Column_Recipe_label="LABEL";
        public final static String Column_Recipe_Ingredients="Ingredients_list";
        public final static String Column_Recipe_Nutri_label="Nutrients_label";
        public final static String Column_Recipe_Nutri_Quantity="Nutrients_quatity";
        public final static String Column_Recipe_Diet_labels="Diet_label";
        public final static String Column_Recipe_Health_labels="Health_label";
        public final static String Column_Recipe_Image="Recipe_image";      }
}
