package com.example.assignment_04.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment_04.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupData;
    private HashMap<String, List<String>> childData;
    private HashMap<String, String> fruitQuotes;
    private HashMap<Integer, Boolean> childVisibility;

    public ExpandableListAdapter(Context context, List<String> groupData, HashMap<String, List<String>> childData, HashMap<String, String> fruitQuotes) {
        this.context = context;
        this.groupData = groupData;
        this.childData = childData;
        this.fruitQuotes = fruitQuotes;
        this.childVisibility = new HashMap<>();
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData.get(groupData.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupData.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.groupTextView);
        textView.setText(groupData.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);
        }

        String childText = (String) getChild(groupPosition, childPosition);
        TextView childTextView = convertView.findViewById(R.id.childTextView);
        TextView quoteTextView = convertView.findViewById(R.id.quoteTextView);
        ImageView arrowImageView = convertView.findViewById(R.id.arrowImageView);

        childTextView.setText(childText);

        String quote = fruitQuotes.get(childText);
        quoteTextView.setText(quote != null ? quote : "");
        quoteTextView.setVisibility(View.GONE);

        arrowImageView.setOnClickListener(v -> {
            boolean isVisible = quoteTextView.getVisibility() == View.VISIBLE;
            quoteTextView.setVisibility(isVisible ? View.GONE : View.VISIBLE);
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
