package com.example.bullet.recipesearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletableFuture.AsynchronousCompletionTask;
import java.util.concurrent.ExecutionException;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static java.lang.Math.round;
import com.example.bullet.recipesearch.data.Recipe_contract.recipe_entry;
import com.example.bullet.recipesearch.data.Recipedbhelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.transform.Result;

@SuppressLint("NewApi")
@RequiresApi(api = Build.VERSION_CODES.N)
public class specificrecipe extends AppCompatActivity {

public ArrayList<String> urllll=new ArrayList<>();
private  Bitmap bmp;
byte[] byteimage1;
 private static Elements question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificrecipe);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<String> Ingredients = getIntent().getStringArrayListExtra("ingredients");
        ArrayList<String> Nutrientslabel = getIntent().getStringArrayListExtra("nutrientslabel");
        ArrayList<String> NTR=new ArrayList<>();
        ArrayList<Integer> Nutrientsquantity = getIntent().getIntegerArrayListExtra("nutrientsquantity");
        //Log.e("nutrients label ",Nutrientslabel.);
        final String Both;
        String Label = getIntent().getStringExtra("label");
        Label= Label.toUpperCase();
        setTitle(Label);
        final String[]        DIETLABELS=getIntent().getStringArrayExtra("Dietlabels"),
                HEALTHLABELS=getIntent().getStringArrayExtra("Healthlabels");
        urllll.add(0,getIntent().getStringExtra("URL"));
        Integer pos = getIntent().getIntExtra("position", 0);
        final String url2img = getIntent().getStringExtra("urltoimage");
       // TextView labelr = (TextView) findViewById(R.id.labelr);
        TextView DIETview = (TextView) findViewById(R.id.DIET_labels);
        TextView ingre = (TextView) findViewById(R.id.Ingredients_list);
        TextView nutri_label = (TextView) findViewById(R.id.nutri_label);
        TextView nutri_quantity = (TextView) findViewById(R.id.nutri_quantity);
        ImageView imgview = (ImageView) findViewById(R.id.Image_R);
        Glide.with(imgview.getContext())
                .load(url2img)
                .asBitmap()
                .into(imgview);
        //labelr.setText(Label);

           /* try {
                Document document = Jsoup.connect(urllll.get(0)).get();
                 question = document.getElementsByTag("span");
            }catch (IOException e){e.printStackTrace();}
              for(int i=0;i<question.size();i++) {
               if (question.hasClass("wpurp-recipe-instruction-text")) {
                   String st = question.get(i).text();
                   TextView direc=(TextView) findViewById(R.id.directions);
                   //direc.append(st);
                   //Log.d("recipe instruction",st);
               }

        }
        */

       try {
           URL url = new URL(url2img);
           bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
       }catch (IOException e){e.printStackTrace();}
        byteimage1=getBytes(bmp);
        //imagefromUrl(url2img);
        //SystemClock.sleep(10000);
        // BitmapDrawable bitmapDrawable = ((BitmapDrawable) imgview.getDrawable());
        //Bitmap bitmap = Bitmap.createBitmap(bitmapDrawable.getBitmap());
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        //final byte[] imageInByte = stream.toByteArray();
        //ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
      /*  new AsyncTask<Void, Void,byte[]>() {

            @Override
            protected byte[] doInBackground(Void... voids) {
                Looper.prepare();
                byte[] byteimage = new byte[0];
                Bitmap bmp1;
                try {
                    bmp= Glide.with(specificrecipe.this)
                            .load(url2img)
                            .asBitmap()
                            .into(100,100)
                            .get();
                     byteimage=getBytes(bmp);
                    //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    //bmp.compress(Bitmap.CompressFormat.PNG, 0, stream);//null object refrence -> bmp
                    //byteimage=stream.toByteArray();
                    //byteimage1.add(0,byteimage);
                    // initialize(bmp);
                }catch (final ExecutionException e){e.printStackTrace();}
                catch (final InterruptedException e){e.printStackTrace();}
                return byteimage;
            }

            //@Override
            //protected void onPostExecute(byte[] Bytearray){
            //byteimage1=Bytearray;
             //}
        }.execute();
                   */
        //Bitmap bmp =Bitmap.createBitmap(((BitmapDrawable)imgview.getDrawable()).getBitmap());
//        byteimage=new Byte[];


        //Both=DIETLABELS+" "+HEALTHLABELS;
        for(int i=0;DIETLABELS[i]!=null;i++){
        DIETview.append(DIETLABELS[i]);}
        for(int i=0;HEALTHLABELS[i]!=null;i++){
            DIETview.append("        "+HEALTHLABELS[i]);}
        for(int i=0;i<Nutrientslabel.size();i++){
            NTR.add(i,Nutrientslabel.get(i)+"                "+round(Nutrientsquantity.get(i)));
        }
       // ArrayList arrayList=new ArrayList();
        for(int i=0;i<(Ingredients.size());i++)
        {
            ingre.append(" \n "+Ingredients.get(i));
        }
        for(int i=0;i<(NTR.size());i++)
        {
            nutri_label.append(" \n "+Nutrientslabel.get(i));
        }
        for(int i=0;i<(NTR.size());i++)
        {
            nutri_quantity.append(" \n "+round(Nutrientsquantity.get(i)));
        }
       //ListAdapter ingredientsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Ingredients);
        //ListAdapter nutrilabelAapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NTR);
        //ListAdapter nutriquantityAapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,Nutrientsquantity);
            //ingre.setAdapter(ingredientsAdapter);
            //nutri_label.setAdapter(nutrilabelAapter);
           // nutri_quantity.setAdapter(nutriquantityAapter);

        JSONObject json = new JSONObject();
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        try {
            json.put("ingredients", new JSONArray(Ingredients));
            json1.put("nutrilabel", new JSONArray(Nutrientslabel));
            json2.put("nutriquantity", new JSONArray(Nutrientsquantity));
        }catch (JSONException e){e.printStackTrace();}
        final String arrayingre = json.toString();
        final String arrayNlabel = json1.toString();
        final String arrayNquantity = json2.toString();
        //displayinfo();

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.save_recipe);
        fab1.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.download));
        final String finalLabel = Label;
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                inserttable(finalLabel,arrayingre,convertArrayToString(DIETLABELS),convertArrayToString(HEALTHLABELS),arrayNlabel,arrayNquantity,byteimage1);
                Snackbar.make(view, "Recipie has been saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
    }
        public void howtoprepare(View view){
        String URL=urllll.get(0);
        Log.d("URLLLLLLLLL",URL);
        Intent i=new Intent(this,how2prep.class);
        i.putExtra("URL",URL);
        this.startActivity(i);

          }

        private void displayinfo(){
            Recipedbhelper mdbhelper = new Recipedbhelper(this);
            SQLiteDatabase db=mdbhelper.getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT * FROM "+recipe_entry.Table_name,null);
            try {
                Toast.makeText(this, "Numbers of rows in Recipe database table" + cursor.getCount(), LENGTH_LONG);
            }finally {
                cursor.close();
            }
        }

        private void inserttable(String label , String Ingre , String Hlabel , String Dlabel , String Nlabel , String Nquantity,byte[] byteimage){
            Recipedbhelper mdbhelper=new Recipedbhelper(this);
            SQLiteDatabase db=mdbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(recipe_entry.Column_Recipe_Image,byteimage);
            values.put(recipe_entry.Column_Recipe_label,label);
            values.put(recipe_entry.Column_Recipe_Ingredients,Ingre);
            values.put(recipe_entry.Column_Recipe_Diet_labels,Dlabel);
            values.put(recipe_entry.Column_Recipe_Health_labels,Hlabel);
            values.put(recipe_entry.Column_Recipe_Nutri_label,Nlabel);
            values.put(recipe_entry.Column_Recipe_Nutri_Quantity,Nquantity);
            long newRowId = db.insert(recipe_entry.Table_name, null, values);
            if (newRowId == -1) {
                // If the row ID is -1, then there was an error with insertion.
                Toast.makeText(this, "Error with saving pet", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast with the row ID.
                Toast.makeText(this, "Recipe saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
            }
        }


        @SuppressLint("StaticFieldLeak")
        public void imagefromUrl(final String imageurl){
            new AsyncTask<Void, Void,Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... voids) {
                    Bitmap bmp1 = null;

                    try {
                        bmp1= Glide.with(specificrecipe.this)
                                .load(imageurl)
                                .asBitmap()
                                .into(100,100)
                                .get();
                       // byteimage1.add(0,getBytes(bmp1));
                        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        //bmp.compress(Bitmap.CompressFormat.PNG, 0, stream);//null object refrence -> bmp
                        //byteimage=stream.toByteArray();
                        //byteimage1.add(0,byteimage);
                        // initialize(bmp);
                        if(bmp1==null){
                            Toast.makeText(specificrecipe.this,"Image is not received ",Toast.LENGTH_LONG).show();
                        }
                      // return bmp1;
                    }catch (final ExecutionException e){e.printStackTrace();}
                    catch (final InterruptedException e){e.printStackTrace();}

                    return bmp1;
                }
                protected void onPostExecute(Bitmap bitmap){

                }
            }.execute();

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

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    public void initialize (Bitmap bitmap){
            bmp=bitmap;
    }


}




