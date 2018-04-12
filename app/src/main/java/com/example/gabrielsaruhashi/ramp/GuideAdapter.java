package com.example.gabrielsaruhashi.ramp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.activities.QuestionsIntroActivity;
import com.example.gabrielsaruhashi.ramp.models.SubCategory;

import java.util.ArrayList;

/**
 * Created by Masayuki on 4/12/18.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.ViewHolder> {
    ArrayList<String> guides;

    public GuideAdapter(ArrayList<String> guides) {
        this.guides = guides;
    }

    //Creates a new view
    @Override
    public GuideAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_movie layout
        View subCategoryView = inflater.inflate(R.layout.item_guide, parent, false);

        subCategoryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, QuestionsIntroActivity.class);
                context.startActivity(i);
            }
        });
        return new GuideAdapter.ViewHolder(subCategoryView);

    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(GuideAdapter.ViewHolder holder, int position) {
        //get the movie data at the specified position
        String guide = guides.get(position);
        //populate the view with the category data
        holder.TvGeneralTitle.setText(guide);
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

        public ViewHolder(View itemView) {
            super(itemView);
            TvGeneralTitle = itemView.findViewById(R.id.generalTitle);
        }
    }
}
