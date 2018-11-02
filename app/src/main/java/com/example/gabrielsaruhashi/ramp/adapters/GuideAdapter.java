package com.example.gabrielsaruhashi.ramp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.activities.TGuideListMapActivity;
import com.example.gabrielsaruhashi.ramp.models.Guide;

import java.util.ArrayList;

/**
 * Created by Masayuki on 4/12/18.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {
    ArrayList<Guide> guides;

    public GuideAdapter(ArrayList<Guide> guides) {
        this.guides = guides;
    }

    int color;
    //Creates a new view
    @Override
    public GuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_category layout
        View subCategoryView = inflater.inflate(R.layout.item_guide, parent, false);

        //color = context.getString(R.integer.subCategoryDiffR)
        subCategoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, TGuideListMapActivity.class);
                context.startActivity(i);
            }
        });
        return new GuideAdapter.ViewHolder(subCategoryView);

    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(GuideAdapter.ViewHolder holder, int position) {
        //get the movie data at the specified position
        Guide guide = guides.get(position);
        //populate the view with the category data
        String title = guide.getName();
        holder.TvGeneralTitle.setText(title);

        //int color = ContextCompat.getColor(R.color.);
        //int color_rgb =
//        int r = 166 - 7 * position;
//        int g = 201 - 12 * position;
//        int b = 234 - 17 * position;
        //This gives us a gradient design on the rows of the recycler view
        //We set our rgb values and then per row, it is changed by a set value
        int r = 224 - 12 * position;
        int g = 238 - 12 * position;
        int b = 251 - 12 * position;

        //String hexColor
        String hex = String.format("#%02x%02x%02x", r, g,b);

        holder.llBanner.setBackgroundColor(Color.parseColor(hex));
        //.setBackground(backgroundColor);
        //holder.set
    }

    //return the size of the entire data set
    @Override
    public int getItemCount() {
        return guides.size();
    }

    //create viewHolder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder{

        //track view objects
        TextView TvGeneralTitle;
        LinearLayout llBanner;

        public ViewHolder(View itemView) {
            super(itemView);
            TvGeneralTitle = itemView.findViewById(R.id.generalTitle);
            llBanner = itemView.findViewById(R.id.llBanner);
        }
    }
}
