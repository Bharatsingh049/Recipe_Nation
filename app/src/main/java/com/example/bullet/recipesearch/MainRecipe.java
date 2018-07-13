package com.example.bullet.recipesearch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONStringer;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.RunnableFuture;

public class MainRecipe extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

     //public Bitmap recipeimage;
     //public TextView label;
     //public String url = "https://api.edamam.com/search?&app_id=4b3ad477&app_key=a1c4e3af9ec55d5029ead5a65559ba64&from=0&calories=gte%20591,%20lte%20722&health=alcohol-free";

    //private Integer ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AutoCompleteTextView textView11 = (AutoCompleteTextView) findViewById(R.id.complete_diet);
        final AutoCompleteTextView textView12 = (AutoCompleteTextView) findViewById(R.id.health_item);
        final EditText calfrom11 = (EditText) findViewById(R.id.Calories_from);
        final EditText calto11 = (EditText) findViewById(R.id.Calories_to);
        Switch toggle = (Switch) findViewById(R.id.filters);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textView11.setVisibility(View.VISIBLE);
                    calfrom11.setVisibility(View.VISIBLE);
                    calto11.setVisibility(View.VISIBLE);
                    textView12.setVisibility(View.VISIBLE);
                    // The toggle is enabled
                } else {
                    textView11.setVisibility(View.GONE);
                    calfrom11.setVisibility(View.GONE);
                    calto11.setVisibility(View.GONE);
                    textView12.setVisibility(View.GONE);
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final String[] COUNTRIES = new String[] {
                "balanced", "high-protein", "high-fiber", "low-fat", "low-carb", "low-sodium"};
        final String[] noofrecipes = new String[]{"5","10","20","30","40","50"};
        final String[] healthitem = new String[] {
                "alcohol-free", "egg-free", "low-sugar", "vegetarian", "pork-free", "gluten-free"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.complete_diet);
        textView.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, noofrecipes);
        AutoCompleteTextView textView1 = (AutoCompleteTextView)
                findViewById(R.id.complete_no_of_recipes);
        textView1.setAdapter(adapter1);
        ArrayAdapter<String> healthadapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,healthitem);
        AutoCompleteTextView health = (AutoCompleteTextView) findViewById(R.id.health_item);
        health.setAdapter(healthadapter);
        DrawerLayout drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle1= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle1);
        toggle1.syncState();
        NavigationView navigationView=(NavigationView)findViewById(R.id.Nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }



   @Override
   public void onBackPressed(){
    DrawerLayout drawerLayout1= (DrawerLayout) findViewById(R.id.drawer_layout);
       if (drawerLayout1.isDrawerOpen(GravityCompat.START)) {
           drawerLayout1.closeDrawer(GravityCompat.START);
       }else {
           super.onBackPressed();
       }
   }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //public void items(int NOR,String DIET,String Q){
      //  if(DIET!=null){
        //    url=url+"&diet=" + DIET;
        //}
    //}

    public void search(final View view) {
        //String app_key ="a1c4e3af9ec55d5029ead5a65559ba64",app_id="4b3ad477";
      thread.run();
    }

    Thread thread=new Thread(){
        @Override
    public void run(){

            View focus=null;
            Boolean cancel=false;
            String app_key ="a1c4e3af9ec55d5029ead5a65559ba64",app_id="4b3ad477";
            String q,from="",to="",str;
            Integer noofrecipe;
            String health,diet="bharat";
            EditText searchItem = (EditText) findViewById(R.id.Search_item) ;
            q = searchItem.getText().toString();
            q= q.replaceAll("\\s+","+");
            //String url;

            //char[] newtemp=q.toCharArray();
            //for(int i=0;i<newtemp.length;i++){
            //  if(newtemp[i]==' '){
            //    newtemp[i]='+';
            //}
            //}
            AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.complete_no_of_recipes);
            str=textView1.getText().toString();
            final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.complete_diet);
            final AutoCompleteTextView textView2 = (AutoCompleteTextView) findViewById(R.id.health_item);
            final EditText calfrom = (EditText) findViewById(R.id.Calories_from);
            final EditText calto = (EditText) findViewById(R.id.Calories_to);
            health=textView2.getText().toString();
            from=calfrom.getText().toString();
            diet=textView.getText().toString();
            to=calto.getText().toString();
            if(TextUtils.isEmpty(q)){
                searchItem.setError("Required Field");
                focus=searchItem;
                cancel=true;
            }
            if(TextUtils.isEmpty(str)){
                textView1.setError("Required Field");
                focus=textView1;
                cancel=true;
            }
            //items(noofrecipe,diet,q);
            if (cancel==true){
                focus.requestFocus();
            }else{
                noofrecipe=Integer.parseInt(str);
                String url = "https://api.edamam.com/search?q=" + q + "&app_id=" + app_id + "&app_key=" + app_key + "&from=0&to=" + noofrecipe;

                if (diet.equals("")) {
                    url = url + "";
                } else {
                    url = url + "&diet=" + diet + "";
                }
                if (health.equals("")) {
                    url = url + "";
                } else {
                    url = url + "&health=" + health + "";
                }
                if (!(from.equals("")) && !(to.equals(""))) {
                    url = url + "&calories=gte%20" + from + ",%20lte%20" + to;
                }

                Log.d("from", from);
                Log.d("to", to);
                Log.d("url", url);
        /* if(diet!=null) {
            url = "https://api.edamam.com/search?q=" + q + "&app_id=" + app_id + "&app_key=" + app_key + "&from=0&to=" + noofrecipe + "&diet=" + diet + "&calories=gte%20591,%20lte%20722&health=alcohol-free";
        }
        else {
            url = "https://api.edamam.com/search?q=" + q + "&app_id=" + app_id + "&app_key=" + app_key + "&from=0&to=" + noofrecipe + "&calories=gte%20591,%20lte%20722&health=alcohol-free";
        }*/
                //url = "https://api.edamam.com/search?q=" + q + "&app_id=" + app_id + "&app_key=" + app_key + "&from=0&to=" + noofrecipe + "&calories=gte%20591,%20lte%20722&health=alcohol-free&diet=" + diet ;
                Intent i = new Intent(getApplicationContext(), RecipeActivity.class);
                i.putExtra("URL", url);
                startActivity(i);
            }



        }};

    public int parsing(String str){
        int temp;
        return temp=Integer.parseInt(str);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.Offline_recipes_new){
            Intent i =new Intent(this,Saved_Recipes.class);
            startActivity(i);
        }
        DrawerLayout drawerLayout2=(DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout2.closeDrawer(GravityCompat.START);
        return true;
    }
}
