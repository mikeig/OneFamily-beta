package com.example.chaohan.onefamily;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Dictionary;

public class FriendsActivity extends AppCompatActivity {

    private ListView myFriendList;
    private ArrayList<Friends> friends;

    private Button newEvent, newTask, newFriend;
    private Context c = this;
    Dialog newFriendDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        getSupportActionBar().setTitle("Friends");

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

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().getItem(4).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.popupButtons);
                if (linearLayout.getVisibility() == View.INVISIBLE) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.INVISIBLE);
                }

            }
        });

        String[] names = {"Jojo"};
        String[] address = {"21 Mango Dr."};
        String[] headSets = {"Jojo"};

        Friends.loadFriendListTemp(names, address, headSets);
        getData();
        String[] friendNames = new String[friends.size()];
        String[] friendAddresses = new String[friends.size()];
        int[] images = new int[friends.size()];
        for (int i=0; i<friends.size(); i++) {
            Friends f = friends.get(i);
            friendNames[i] = f.getName();
            friendAddresses[i] = f.getAddress();
            if (!f.getHeadSet().equals("")) {
                continue;
            }
        }

        myFriendList = (ListView) findViewById(R.id.friend_list);
        MyAdapter adapter = new MyAdapter(this, friendNames,friendAddresses,images);
        myFriendList.setAdapter(adapter);
        myFriendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextScreen = new Intent
                        (c, FriendDetailActivity.class);
                startActivity(nextScreen);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

//        myFriendList.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new Lin1earLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        myFriendList.setLayoutManager(linearLayoutManager);

        newFriend = (Button) findViewById(R.id.newFriendButton);
        newEvent = (Button) findViewById(R.id.newEventButton);
        newTask = (Button) findViewById(R.id.newTaskButton);
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

    public void getData() {

        friends = Friends.getStoredFriendList();
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        int[] imgs;
        String[] names;
        String[] addresses;

        MyAdapter(Context c, String[] names, String[] addresses, int[] images) {
            super(c, R.layout.friend_list_layout, names);
            this.context = c;
            this.imgs = images;
            this.addresses = addresses;
            this.names = names;
        }

        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View friendList = layoutInflater.inflate
                    (R.layout.friend_list_layout, parent, false);
//        ImageView images = (ImageView)findViewById(R.id.icon);
            TextView name = (TextView) friendList.findViewById(R.id.friend_name);
            TextView address = (TextView) friendList.findViewById(R.id.friend_address);
            name.setText("Jojo");
            address.setText(addresses[pos]);
            return friendList;
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                    nextScreen = new Intent(c,MainPageActivity.class);
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
