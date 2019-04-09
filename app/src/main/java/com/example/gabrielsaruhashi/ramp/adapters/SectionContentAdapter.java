package com.example.gabrielsaruhashi.ramp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabrielsaruhashi.ramp.R;
import com.example.gabrielsaruhashi.ramp.models.Section;

import java.util.ArrayList;

import ru.noties.markwon.Markwon;

/**
 * Created by tylershen on 5/1/18.
 */

public class SectionContentAdapter extends RecyclerView.Adapter<SectionContentAdapter.ViewHolder> {
    ArrayList<Section> sections;

    public SectionContentAdapter(ArrayList<Section> sections) { this.sections = sections; }

    Context context;
    //Creates a new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the view using the item_category layout
        View guideIndex = inflater.inflate(R.layout.item_section, parent, false);

        return new ViewHolder(guideIndex);

    }

    //create viewHolder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder{

        //track view objects
        TextView sectionTitle;
        TextView sectionContent;

        public ViewHolder(final View itemView) {
            super(itemView);
            //lookup view objects by id
            sectionContent = itemView.findViewById(R.id.sectionContentView);
        }
    }

    //binds an inflated view to a new line
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the movie data at the specified position
        Section section = sections.get(position);
        // obtain an instance of Markwon
        final Markwon markwon = Markwon.create(context);
//
//// set markdown
        markwon.setMarkdown(holder.sectionContent, "#### How can I get help with rental payments?\n**RAP (Rental Assistance Program) VOUCHERS:** RAP is a state-funded program administered by the Connecticut Department of Housing. Link(s) on Mary Wade:[ https://www.211ct.org/search/17322547](http://www.marywade.org/) [www.marywade.org](http://www.marywade.org/). \n\n The Department of Housing monitors the Section 8 Housing Choice Voucher (HCV) Program and State Rental Assistance Program (RAP) waiting lists statewide, and may open up the program periodically for a lottery, to be placed on the waiting list.");
//        //populate the view with the category data
        // holder.sectionContent.setText(section.getContents());

    }

    //return the size of the entire data set
    @Override
    public int getItemCount() {
        return sections.size();
    }

}
