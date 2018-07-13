            package com.example.bullet.recipesearch;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    String url;
    ProgressDialog mProgressDialog;
    Bitmap recipeimage;
    List<RecipeList> newlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recipeCycle= (RecyclerView) findViewById(R.id.recipecycle);
        recipeCycle.setLayoutManager(new LinearLayoutManager(this));
        url=getIntent().getStringExtra("URL");
        newlist=new ArrayList<>();
        Log.d( "onCreate: ",url);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
        fetchingfromlist(url,recipeCycle);

    }
    public void fetchingfromlist(String url, final RecyclerView recipeCycle){
        StringRequest req=new StringRequest(Request.Method.GET,url,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Our Recipe: ",response);
                response.toString();


                try {
                    mProgressDialog = new ProgressDialog(RecipeActivity.this);
                    mProgressDialog.setMessage("Loading...");
                    mProgressDialog.setIndeterminate(false);
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.show();
                    JSONObject jobject = new JSONObject(response);
                    JSONArray jarray = jobject.getJSONArray("hits");
                    if(jarray.length()>0) {
                        for (int i = 0; i < jarray.length(); i++) {
                            JSONObject J2object = jarray.getJSONObject(i);
                            JSONObject J3object = J2object.getJSONObject("recipe");
                            RecipeList recipeList = new RecipeList();
                            recipeList.setLABEL(J3object.getString("label"));
                            recipeList.setUrltoimage(J3object.getString("image"));
                            JSONArray temp=J3object.getJSONArray("dietLabels");
                            JSONArray healthtemp=J3object.getJSONArray("healthLabels");
                            JSONArray ingrelines = J3object.getJSONArray("ingredientLines");
                            //JSONArray DietLabels = J3object.getJSONArray("dietLabels");
                            //JSONArray HealthLabels = J3object.getJSONArray("healthLabels");
                            String how2prep=J3object.getString("url");
                            JSONObject  totalnutri=J3object.getJSONObject("totalNutrients");
                           // Log.d( "onResponse: ",totalnutri.getString(1).toString());
                           // String[] ing;
                            //for (int j = 0, count = ingrelines.length(); j < count; j++){
                              //  try {
                                    //ing[j] = ingrelines.getString(j);
                                //    String jsonString = ingrelines.getString(i).toString();
                                  //  ing[i] = jsonString.toString();
                                //}catch (JSONException e){e.printStackTrace();}
                            //}
                            recipeList.setprepareurl(how2prep);
                            recipeList.setdiethealthlabels(temp,healthtemp);
                            recipeList.setTotalNutrients(totalnutri);
                            recipeList.setIngredients(ingrelines);

                            newlist.add(i, recipeList);

                        }
                        recipeCycle.setAdapter(new RecipeAdapter(RecipeActivity.this, newlist));
                        mProgressDialog.dismiss();
                        //String temp=newlist.get(2).LABEL;
                        //Log.i("label",temp);

                        //Log.i("list label",newlist.get(0).LABEL);
                        //label.setText(LABEL);
                        //IMview.setImageBitmap(recipeimage);
                    }else {
                        mProgressDialog.dismiss();
                        Toast.makeText(RecipeActivity.this,"Sorry Please Try With Different Options",Toast.LENGTH_LONG).show();
                    }
                    }catch (JSONException e)
                {
                    Log.i("josn exception ",""+e.toString());
                }

                //fetchingfromlist(recipeList);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipeActivity.this,"Sorry you entered a wrong food item",Toast.LENGTH_LONG).show();

            }
        });

        RequestQueue queue= Volley.newRequestQueue(RecipeActivity.this);
        queue.add(req);

    }

}
