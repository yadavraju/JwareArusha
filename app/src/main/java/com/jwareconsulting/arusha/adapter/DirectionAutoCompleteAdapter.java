package com.jwareconsulting.arusha.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.jwareconsulting.arusha.R;
import com.jwareconsulting.arusha.model.AutoCompleteModel;

import java.util.ArrayList;

/**
 * Created by anjulkhanal  on 9/27/16.
 */

public class DirectionAutoCompleteAdapter extends BaseAdapter implements Filterable {
    private ArrayList<AutoCompleteModel> orginalList;
    private ArrayList<AutoCompleteModel> suggestions;
    private CustomFilter filter;
    private final Context context;

    public DirectionAutoCompleteAdapter(Context context, ArrayList<AutoCompleteModel> orginalList) {
        this.context = context;
        this.orginalList = orginalList;
        this.filter= new CustomFilter();
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_adapter_autocomplete, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.tv_adapter);
        textView.setText(orginalList.get(position).getTitle());

        return convertView;
    }

    public void populateView(ArrayList<AutoCompleteModel> dataList) {
        this.orginalList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return orginalList.size();
    }

    @Nullable
    @Override
    public AutoCompleteModel getItem(int position) {
        return orginalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public Filter getFilter() {
        return filter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggestions.clear();

            if (orginalList != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                for (int i = 0; i < orginalList.size(); i++) {
                    if (orginalList.get(i).getTitle().toLowerCase().contains(constraint)) { // Compare item in original list if it contains constraints.
                        suggestions.add(orginalList.get(i)); // If TRUE add item in Suggestions.
                    }
                }
            }
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }

        }

    }
}
