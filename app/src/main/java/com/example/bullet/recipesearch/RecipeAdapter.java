package com.example.bullet.recipesearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bullet on 13-10-2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.recipeViewHolder> {
    Context context;
    List<RecipeList> list;
    public RecipeAdapter(Context context, List<RecipeList> list){
    this.list=list;
    this.context=context;
    }

    @Override
    public recipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recipe_layout,parent,false);
        return new recipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(recipeViewHolder holder,  int position) {
        final String lab=list.get(position).getLABEL();
        final int temp_position=position;
        final ArrayList<String> ing=list.get(position).getIngredients();
        final ArrayList<String> nutrilabel=list.get(position).getNutrientsLabel();
        final ArrayList<Integer> nutriquantity=list.get(position).getNutrientsquantity();
        final String url2img=list.get(position).geturl2image();
              final String[]  DIEtlabels=list.get(position).getDietlabels();
               final String[] HEAlthlabels=list.get(position).getHealthlabels();
              final String  prepurl=list.get(position).getprepareurl();
        holder.txt.setText(lab);
        Glide.with(holder.img.getContext()).load(url2img).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,specificrecipe.class);
                i.setAction(Intent.ACTION_SEND);
                i.setType("text/plain");
                 i.putExtra("label",lab);
                 i.putExtra("ingredients",ing);
                 i.putExtra("position",temp_position);
                 i.putExtra("urltoimage",url2img);
                 i.putExtra("nutrientslabel",nutrilabel);
                 i.putExtra("nutrientsquantity",nutriquantity);
                 i.putExtra("Dietlabels",DIEtlabels);
                i.putExtra("Healthlabels",HEAlthlabels);
                i.putExtra("URL",prepurl);
                 context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class recipeViewHolder extends RecyclerView.ViewHolder{
        TextView txt;
        ImageView img;
        public recipeViewHolder(View itemView) {
            super(itemView);
            txt= (TextView) itemView.findViewById(R.id.recipelabel);
            img= (ImageView) itemView.findViewById(R.id.imgrecipe);
        }
    }
}
