package com.example.gabrielsaruhashi.ramp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.activities.SubcategoryViewActivity;
import com.example.gabrielsaruhashi.ramp.models.Category;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Masayuki on 3/18/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<Category> categories;
    Context context;
    public CategoryAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    //Creates a new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_category layout
        View categoryView = inflater.inflate(R.layout.item_category, parent, false);


//        categoryView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(context, SubcategoryViewActivity.class);
//                context.startActivity(i);
//            }
//        });
        return new ViewHolder(categoryView);

    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the movie data at the specified position
        Category category = categories.get(position);
        //populate the view with the category data
        holder.categoryTitle.setText(category.getStatus());
        holder.categoryImage.setImageResource(category.getCategory_image());
    }

    //return the size of the entire data set
    @Override
    public int getItemCount() {
        return categories.size();
    }

    //create viewHolder as a static inner class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //track view objects
        ImageView categoryImage;
        TextView categoryTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            //lookup view objects by id
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick (View v) {
            int position = getAdapterPosition();
            Category category = categories.get(position);
            Intent i = new Intent(context, SubcategoryViewActivity.class);
            i.putExtra(Category.class.getSimpleName(), Parcels.wrap(category));
            context.startActivity(i);
        }
    }
}
