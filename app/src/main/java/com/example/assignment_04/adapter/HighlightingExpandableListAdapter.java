package com.example.assignment_04.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.assignment_04.R;

import java.util.HashMap;
import java.util.List;

public class HighlightingExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> groupData;
    private HashMap<String, List<String>> childData;
    private String selectedFruit; // The fruit to highlight

    public HighlightingExpandableListAdapter(Context context, List<String> groupData, HashMap<String, List<String>> childData, String selectedFruit) {
        this.context = context;
        this.groupData = groupData;
        this.childData = childData;
        this.selectedFruit = selectedFruit;
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
        String groupText = (String) getGroup(groupPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.groupTextView);
        textView.setText(groupText);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.child_item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.childTextView);
        textView.setText(childText);

        // Highlight the selected fruit
        if (childText.equals(selectedFruit)) {
            textView.setTextColor(Color.RED); // Highlighted color
        } else {
            textView.setTextColor(Color.BLACK); // Default color
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
