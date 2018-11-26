package com.example.chaohan.onefamily;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter implements Filterable {
    
    Context c;
//    ArrayList<OneTask> tasks;
//    ArrayList<OneEvent> events;
//    ArrayList<OneTask> filterTasks;
//    ArrayList<OneEvent> filterEvents;
    int filterTaskCount, filterEventCount;
    CustomFilter filter;
        
    
//    public SearchAdapter(Context ctx, ArrayList<OneTask> tasks, ArrayList<OneEvent> events){
//        this.c = ctx;
//        this.tasks = tasks;
//        this.events = events;
//        this.filterTasks = tasks;
//        this.filterEvents = events;

//    }
    
    @Override
    public int getCount() {
//        return tasks.size() + events.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) 
                c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.search_tasks_events, null);
        }

//        TextView eventText = (TextView) convertView.findViewById(id.);
//        ImageView eventImage = (ImageView) convertView.findViewById(id.)
//
//        eventText.setText();
//        eventImage.setImageResource();
        
        return convertView;
    }

    @Override
    public Filter getFilter() {

        if(filter == null) {
            filter=new CustomFilter();
        }
        return filter;
    }
    
    
    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
//            filterEventCount = filterTaskCount = 0;
//            if(constraint != null && constraint.length() > 0) {
//                constraint = constraint.toString().toUpperCase();
//                ArrayList<OneEvent> filterEs = new ArrayList<OneEvent>();
//                ArrayList<OneTask> filterTs = new ArrayList<OneTask>();
//
//                for (int i=0; i<filterTasks.size();i++) {
//                    if(fileterTasks.get(i).getName().toUpperCase().contains(constraint)) {
//                        OneTask t = new OneTask(filterTasks.get(i).getName(),
//                                filterTasks.get(i).getID());
//                        filterTs.add(t);
//                    }
//                }
//
//                for (int i=0; i<filterEvents.size();i++) {
//                    if(fileterEvents.get(i).getName().toUpperCase().contains(constraint)) {
//                        OneEvent t = new OneEvent(filterEvents.get(i).getName(),
//                                filterEvents.get(i).getID());
//                        filterEs.add(t);
//                    }
//                }
//
//                filterTaskCount = filterTs.size();
//                filterEventCount = filterEs.size();
//                results.count = filterTaskCount + filterEventCount;
//                ArrayList<Object> all = new ArrayList<Object>(filterTs);
//                all.addAll(filterEs);
//                results.values = all;
//            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
//            tasks = (ArrayList<OneTask>)((ArrayList<?>) results.values).
//                    subList(0,filterTaskCount-1);
//            events = (ArrayList<OneEvent>)((ArrayList<?>) results.values).
//                    subList(filterTaskCount, results.count-1);
            notifyDataSetChanged();
        }
    }
}
