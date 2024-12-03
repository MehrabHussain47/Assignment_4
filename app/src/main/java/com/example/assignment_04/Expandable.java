package com.example.assignment_04;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Expandable extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<String> groupData;
    private HashMap<String, List<String>> childData;
    private HashMap<String, String> fruitQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        String selectedFruit = getIntent().getStringExtra("selectedFruit");

        expandableListView = findViewById(R.id.expandableListView);
        initializeData();
        initializeQuotes();

        ExpandableListAdapter adapter = new com.example.assignment_04.adapter.ExpandableListAdapter(
                this, groupData, childData, fruitQuotes);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            String childText = childData.get(groupData.get(groupPosition)).get(childPosition);
            Toast.makeText(this, "Selected: " + childText, Toast.LENGTH_SHORT).show();
            return false;
        });
    }

    private void initializeData() {
        String[] headers = getResources().getStringArray(R.array.fruit_groups);
        String[][] children = {
                getResources().getStringArray(R.array.citrus_fruits),
                getResources().getStringArray(R.array.berry_fruits),
                getResources().getStringArray(R.array.tropical_fruits)
        };

        groupData = new ArrayList<>();
        childData = new HashMap<>();

        for (int i = 0; i < headers.length; i++) {
            groupData.add(headers[i]);
            List<String> childList = new ArrayList<>();
            for (String child : children[i]) {
                childList.add(child);
            }
            childData.put(headers[i], childList);
        }
    }

    private void initializeQuotes() {
        fruitQuotes = new HashMap<>();
        fruitQuotes.put("Apple", "Apple is a healthy fruit.");
        fruitQuotes.put("Banana", "Banana is a great source of energy.");
        fruitQuotes.put("Grape", "Grapes are small and sweet.");
        fruitQuotes.put("Mango", "Mango is the delicious fruit.");
        fruitQuotes.put("Pineapple", "Pineapple is a tropical delight.");
    }
}
