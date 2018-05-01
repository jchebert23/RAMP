package com.example.gabrielsaruhashi.ramp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.models.Section;
import com.example.gabrielsaruhashi.ramp.models.SubCategory;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by tylershen on 5/1/18.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHolder> {
    ArrayList<Section> sections;

    public SectionAdapter(ArrayList<Section> sections) { this.sections = sections; }

    Context context;
    //Creates a new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_category layout
        View guideIndex = inflater.inflate(R.layout.item_section_title, parent, false);

        return new ViewHolder(guideIndex);

    }

    //create viewHolder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder{

        //track view objects
        TextView sectionTitle;

        public ViewHolder(final View itemView) {
            super(itemView);
            //lookup view objects by id
            sectionTitle = itemView.findViewById(R.id.sectionTitle);
        }
    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the movie data at the specified position
        Section section = sections.get(position);
        //populate the view with the category data
        holder.sectionTitle.setText(section.getTitle());
    }

    //return the size of the entire data set
    @Override
    public int getItemCount() {
        return sections.size();
    }

}
