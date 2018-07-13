package com.example.bullet.recipesearch;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by bullet on 11-10-2017.
 */

public class RecipeList {
    public String LABEL,url2image,URL;
    public String[] dietlabels=new String[100],healthlabels=new String[100];
    public ArrayList<String> ingredients = new ArrayList<>();
    public RecipeList(){}
    public void setprepareurl(String url){URL=url;}
    public String getprepareurl(){return URL;}
    public void setdiethealthlabels(JSONArray DIETlabels,JSONArray HEALTHlabels){
        try {
            for(int i=0;i<DIETlabels.length();i++)
            {
                dietlabels[i]= DIETlabels.get(i).toString();
                dietlabels[i].replace(","," ");
                dietlabels[i].replace(" \"","");
                dietlabels[i].replace("[","");
                dietlabels[i].replace("]","");
            }
        }catch (JSONException e){e.printStackTrace();}

        try{
            for(int i=0;i<HEALTHlabels.length();i++)
        {
            healthlabels[i]=HEALTHlabels.get(i).toString();

            healthlabels[i].replace(",","   ");
            healthlabels[i].replace(" \"","");
            healthlabels[i].replace("[","");
            healthlabels[i].replace("]","");
        }
    }catch (JSONException e){e.printStackTrace();}

    }
    public String[] getDietlabels(){return dietlabels;}
    public String[] getHealthlabels(){return healthlabels;}
    public String[] nutrients={"ENERC_KCAL","FAT","FASAT","FATRN","FAMS","FAPU","CHOCDF",
    "FIBTG","SUGAR","PROCNT","CHOLE","NA","CA","MG","K","FE","ZN","P","VITA_RAE","VITC",
    "THIA","RIBF","NIA","VITB6A","FOLDFE","FOLFD","VITB12","VITD","TOCPHA","VITK1"};
    public ArrayList<String> nutrientsLabel=new ArrayList<>();
    public ArrayList<Integer> nutrientsquantity=new ArrayList<>();
    public void setLABEL(String label) {LABEL=label;}
    public void setUrltoimage(String url2image1){url2image=url2image1;}
    public void setIngredients(JSONArray ingredients1){
        //String[] ing =new String[ingredients1.length()];
        for (int j = 0; j < ingredients1.length(); j++){
              try {
                  //String jsonString = ingredients1.get(j).toString();
                  //ingredients[j] = jsonString;
                  ingredients.add(ingredients1.get(j).toString());
              }
            catch (JSONException e){e.printStackTrace();}
        }
        //for (int i = 0; i < ingredients1.length(); i++) {
          //  ingredients[i] = ing[i];
        //}
        //Log.d( "setIngredients: ",);
    }
    public void setTotalNutrients(JSONObject totalnutri){
   for(int i=0;i<nutrients.length;i++){
       try {JSONObject J2object = totalnutri.getJSONObject(nutrients[i]);
            nutrientsquantity.add(J2object.getInt("quantity"));
            nutrientsLabel.add(J2object.getString("label"));
       }catch (JSONException e) {e.printStackTrace();}
   }

    }
    public String getLABEL(){return LABEL;}
    public String geturl2image(){return url2image;}
    public ArrayList<String> getIngredients(){return ingredients;}
    public ArrayList<Integer> getNutrientsquantity(){return nutrientsquantity;}
    public ArrayList<String> getNutrientsLabel(){return nutrientsLabel;}

}
