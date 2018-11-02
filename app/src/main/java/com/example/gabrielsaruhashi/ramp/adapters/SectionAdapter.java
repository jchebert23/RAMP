package com.example.gabrielsaruhashi.ramp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.activities.RGuideViewActivity;
import com.example.gabrielsaruhashi.ramp.models.Section;

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
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //track view objects
        TextView sectionTitle;
        TextView sectionNumber;

        public ViewHolder(final View itemView) {
            super(itemView);
            //lookup view objects by id
            sectionTitle = itemView.findViewById(R.id.sectionTitle);
            sectionNumber = itemView.findViewById(R.id.tvSectionNumber);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            Log.d("SectionAdapter", "got into on click");
                Section section = sections.get(position);
                // We can access the data within the views
                Toast.makeText(context, section.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the movie data at the specified position
        final Section section = sections.get(position);
        //populate the view with the category data
        holder.sectionTitle.setText(section.getTitle());
        int num = section.getNumber() + 1;
        holder.sectionNumber.setText(Integer.toString(num));

        holder.sectionTitle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // when user clicks on section
                Intent intent = new Intent(context, RGuideViewActivity.class);
                intent.putExtra("sectionTitle", section.getTitle());
                intent.putExtra("sectionContent", section.getContents());
                intent.putExtra("sectionNumber", section.getNumber());
                context.startActivity(intent);
                Log.d("SectionAdapter", "launched guide view activity");
            }
        });
    }

    //return the size of the entire data set
    @Override
    public int getItemCount() {
        return sections.size();
    }

}
