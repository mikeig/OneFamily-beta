package com.example.chaohan.onefamily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SearchView;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {

    GridView gridView;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        gridView = (GridView) findViewById(R.id.task_event_grid);
        searchView = (SearchView) findViewById(R.id.search_TE);

        final SearchAdapter adapter = new SearchAdapter();
        gridView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private int[] getEachCount(){
        int[] eachCount = new int[6];

        return eachCount;
    }
}
