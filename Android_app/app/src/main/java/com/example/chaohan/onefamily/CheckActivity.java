package com.example.chaohan.onefamily;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckActivity extends AppCompatActivity {

    GridLayout gridView;
    SearchView searchView;
    private Button newEvent, newTask, newFriend;
    private CardView dailyTask;
    private Context c = this;
    Dialog newFriendDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

//        gridView = (GridLayout) findViewById(R.id.task_event_grid);
        searchView = (SearchView) findViewById(R.id.search_TE);

//        final SearchAdapter adapter = new SearchAdapter();
//        gridView.setAdapter(adapter);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });

        getSupportActionBar().setTitle("Tasks & Events");

        View decorView = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

        BottomNavigationView navigation = (BottomNavigationView) CheckActivity.this.findViewById(R.id.navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) CheckActivity.this.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) CheckActivity.this.findViewById(R.id.popupButtons);
                if (linearLayout.getVisibility() == View.INVISIBLE) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.INVISIBLE);
                }

            }
        });
        newFriend = (Button) CheckActivity.this.findViewById(R.id.newFriendButton);
        newEvent = (Button) CheckActivity.this.findViewById(R.id.newEventButton);
        newTask = (Button) CheckActivity.this.findViewById(R.id.newTaskButton);
        newFriendDialog = new Dialog(this);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent
                        (c, PostActivity.class);
                startActivity(nextScreen);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        newEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent
                        (c, PostActivity.class);
                startActivity(nextScreen);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        newFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        dailyTask = (CardView) findViewById(R.id.daily_task_card);

        dailyTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen;
                nextScreen = new Intent(c,TaskAndEventActivity.class);
                startActivity(nextScreen);
            }
        });

    }

    public void showPopup(View v) {
        TextView name, tags;
        Button accept, decline, detect;
        newFriendDialog.setContentView(R.layout.add_new_friend_layout);
        name = (TextView) newFriendDialog.findViewById(R.id.newFriendaddName);
        tags = (TextView) newFriendDialog.findViewById(R.id.newFriendTagAdd);

        accept = (Button) newFriendDialog.findViewById(R.id.acceptFButton);
        decline = (Button) newFriendDialog.findViewById(R.id.declineFButton);


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFriendDialog.dismiss();
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFriendDialog.dismiss();
            }
        });

        newFriendDialog.show();
    }

    private int[] getEachCount(){
        int[] eachCount = new int[6];

        return eachCount;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            LayoutInflater layoutInflater = (LayoutInflater)
//                    mainFrame.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view;
            Intent nextScreen;
            switch (item.getItemId()) {
                case R.id.navigation_feeds:
                    nextScreen = new Intent(c,FeedsActivity.class);
                    startActivity(nextScreen);
                    return true;
                case R.id.navigation_task_and_event:
                    nextScreen = new Intent(c,CheckActivity.class);
                    startActivity(nextScreen);
                    return true;
                case R.id.navigation_my_neighbor:
                    nextScreen = new Intent(c,MainPageActivity .class);
                    startActivity(nextScreen);
                    return true;
                case R.id.navigation_friends:
                    nextScreen = new Intent(c,FriendsActivity.class);
                    startActivity(nextScreen);
                    return true;
            }
            return false;
        }
    };
}
