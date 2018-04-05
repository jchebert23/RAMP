package com.example.gabrielsaruhashi.ramp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.activities.QualificationsQuestionsActivity;
import com.example.gabrielsaruhashi.ramp.models.Category;

import java.util.ArrayList;

/**
 * Created by Masayuki on 3/18/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<Category> categories;

    public CategoryAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    //Creates a new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_movie layout
        View categoryView = inflater.inflate(R.layout.item_movie, parent, false);

        categoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, QualificationsQuestionsActivity.class);
                context.startActivity(i);
            }
        });
        return new ViewHolder(categoryView);

    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the movie data at the specified position
        Category category = categories.get(position);
        //populate the view with the category data
        holder.categoryTitle.setText(category.getStatus());
    }

    //return the size of the entire data set
    @Override
    public int getItemCount() {
        return categories.size();
    }

    //create viewHolder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder{

        //track view objects
        ImageView categoryImage;
        TextView categoryTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            //lookup view objects by id
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }
}
