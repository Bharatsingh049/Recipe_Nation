package com.example.bullet.recipesearch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bullet.recipesearch.data.Recipe_contract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Math.round;

public class OFLINE_RECIPE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofline__recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        byte[] savedimg = getIntent().getByteArrayExtra("img");
        String savedlabel = getIntent().getStringExtra("saved label");
        String savedINGRE = getIntent().getStringExtra("saved Ingre");
        String savedDlabel = getIntent().getStringExtra("saved Dietlabel");
        String savedHlabel = getIntent().getStringExtra("saved Healthlabel");
        String savedNlabel = getIntent().getStringExtra("saved Nutrilabel");
        String savedNquantity = getIntent().getStringExtra("saved Nutriquantity");
        setTitle(savedlabel);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ArrayList<String> Ingredients=new ArrayList<>(),Nutrientslabel=new ArrayList<>();
        ArrayList<Integer> Nutrientsquantity=new ArrayList<>();
        try {
            JSONObject json = new JSONObject(savedINGRE),json1=new JSONObject(savedNlabel),json2=new JSONObject(savedNquantity);
            JSONArray items = json.optJSONArray("ingredients"),items1 = json1.optJSONArray("nutrilabel"),items2 = json2.optJSONArray("nutriquantity");
            for (int j = 0; j < items.length(); j++){
                try {
                    //String jsonString = ingredients1.get(j).toString();
                    //ingredients[j] = jsonString;
                    Ingredients.add(items.get(j).toString());
                }
                catch (JSONException e){e.printStackTrace();}
            }
            for (int j = 0; j < items1.length(); j++){
                try {
                    //String jsonString = ingredients1.get(j).toString();
                    //ingredients[j] = jsonString;
                    Nutrientslabel.add(items1.get(j).toString());
                }
                catch (JSONException e){e.printStackTrace();}
            }
            for (int j = 0; j < items2.length(); j++){
                try {
                    //String jsonString = ingredients1.get(j).toString();
                    //ingredients[j] = jsonString;
                    Nutrientsquantity.add(Integer.parseInt(items2.get(j).toString()));
                }
                catch (JSONException e){e.printStackTrace();}
            }
        }catch (JSONException e){e.printStackTrace();}
        TextView offlineNlabel=(TextView) findViewById(R.id.offline_nutri_label);
        TextView offlineNquantity=(TextView) findViewById(R.id.offline_nutri_quantity);
        TextView ingre=(TextView) findViewById(R.id.offlineIngredients);
        //TextView offline_label=(TextView) findViewById(R.id.offline_label);
        TextView DIETview=(TextView) findViewById(R.id.offline_DIET_labels);
        ImageView offline_image=(ImageView) findViewById(R.id.offline_image);
        Bitmap bitmapimage= BitmapFactory.decodeByteArray(savedimg,0,savedimg.length);
        offline_image.setImageBitmap(bitmapimage);
        //offline_label.setText(savedlabel);
       String[] DIETLABELS,HEALTHLABELS;
        HEALTHLABELS=convertStringToArray(savedHlabel);
        DIETLABELS=convertStringToArray(savedDlabel);
      //fzor(int i=0;i<=DIETLABELS.length;i++){DIETLABELS[i].replace("_","");}
       //for(int i=0;DIETLABELS[i]!=null;i++){DIETview.append(DIETLABELS[i]);}
        //for(int i=0;HEALTHLABELS[i]!=null;i++){DIETview.append("        "+HEALTHLABELS[i]);}

        for(int i=0;i<(Ingredients.size());i++)
        {
            ingre.append(" \n "+Ingredients.get(i));
        }
        for(int i=0;i<(Nutrientslabel.size());i++)
        {
            offlineNlabel.append(" \n "+Nutrientslabel.get(i));
        }
        for(int i=0;i<(Nutrientsquantity.size());i++)
        {
            offlineNquantity.append(" \n "+round(Nutrientsquantity.get(i)));
        }
    }

    public static String strSeparator = "__,__";

    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }

    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }



}
