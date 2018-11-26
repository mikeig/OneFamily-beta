package com.example.chaohan.onefamily;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FriendDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        TextView mName = (TextView) findViewById(R.id.friendDetailName);
        TextView mAddress = (TextView) findViewById(R.id.friendDetailAddress);
        TextView mEmail = (TextView) findViewById(R.id.friendDetailEmail);
        TextView mTags = (TextView) findViewById(R.id.friendDetailTags);

        Button mMessage = (Button) findViewById(R.id.friendDetaiMsgButton);
        Button mInvite= (Button) findViewById(R.id.inviteFriendButton);
        Button mProfile = (Button) findViewById(R.id.friendProfileButton);
        Button mTandE = (Button) findViewById(R.id.friendTEButton);
        Button mBlock = (Button) findViewById(R.id.blockFriendButton);

        mName.setText(R.string.friend_temp_name);
        mAddress.setText(R.string.friend_temp_address);
        mEmail.setText(R.string.friend_temp_email);
        StringBuilder tags = new StringBuilder();
        for (String s : getResources().getStringArray(R.array.friend_temp_tags)) {
            tags.append(s);
            tags.append(" ");
        }
        mTags.setText(tags);

        //Onclick Listener
        mTandE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FriendDetailActivity.this,
                        R.string.popup_notAvail, Toast.LENGTH_LONG).show();
            }
        });

        mInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FriendDetailActivity.this,
                        R.string.popup_notAvail, Toast.LENGTH_LONG).show();
            }
        });

        mMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(FriendDetailActivity.this, ChatActivity.class);
                startActivity(nextScreen);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        mBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FriendDetailActivity.this,
                        "You have blocked this user!", Toast.LENGTH_LONG).show();
            }
        });

        mProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(FriendDetailActivity.this, ProfileActivity.class);
                startActivity(nextScreen);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
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
}
