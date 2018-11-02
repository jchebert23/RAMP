package com.example.gabrielsaruhashi.ramp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.activities.TGuideListMapActivity;
import com.example.gabrielsaruhashi.ramp.models.SubCategory;

import java.util.ArrayList;

/**
 * Created by tylershen on 4/12/18.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    ArrayList<SubCategory> subcategories;

    public SubCategoryAdapter(ArrayList<SubCategory> subcategories) { this.subcategories = subcategories; }

    Context context;
    //Creates a new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_category layout
        View categoryView = inflater.inflate(R.layout.item_subcategory, parent, false);

        /*categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SubcategoryView.class);
                context.startActivity(i);
            }
        }); */
        return new ViewHolder(categoryView);

    }

    //create viewHolder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder{

        //track view objects
        ImageView subCategoryImage;
        TextView subCategoryTitle;

        public ViewHolder(final View itemView) {
            super(itemView);
            //lookup view objects by id
            subCategoryImage = itemView.findViewById(R.id.subCategoryImage);
            subCategoryTitle = itemView.findViewById(R.id.subCategoryTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent i = new Intent(itemView.getContext(), SubcategoryView.class);
                    Intent i = new Intent(itemView.getContext(), TGuideListMapActivity.class);
                    itemView.getContext().startActivity(i);
                }
            });;
        }
    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the movie data at the specified position
        SubCategory subcategory = subcategories.get(position);
        //populate the view with the category data
        holder.subCategoryTitle.setText(subcategory.getTitle());

        int r = subcategory.getR();
        int g = subcategory.getG();
        int b = subcategory.getB();
        Log.d("rgb", Integer.toString(b));

        //String hexColor
        String hex = String.format("#%02x%02x%02x", r, g,b);

        holder.subCategoryImage.setBackgroundColor(Color.parseColor(hex));


    }

    //return the size of the entire data set
    @Override
    public int getItemCount() {
        return subcategories.size();
    }

}
