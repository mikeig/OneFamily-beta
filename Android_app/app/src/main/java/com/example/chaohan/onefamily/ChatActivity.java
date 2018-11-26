package com.example.chaohan.onefamily;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private String friendName;

    private Chatting curChat;
    private Friends curFriend;

    private RecyclerView recyclerView;
    private ImageButton sendButton, shotButton;
    private EditText sendText;
    MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        curFriend = Friends.getStoredFriendList().get(0);
        getSupportActionBar().setTitle(curFriend.getName());
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

        curChat = Chatting.getChatStore();

        recyclerView = (RecyclerView) findViewById(R.id.chat_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        messageAdapter = new MessageAdapter(ChatActivity.this, curChat);
        recyclerView.setAdapter(messageAdapter);

//        layout = (LinearLayout) findViewById(R.id.signupLayout);
//
//
//        layout.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View view, MotionEvent ev)
//            {
//                hideKeyboard(view);
//                return false;
//            }
//        });


        sendButton = (ImageButton) findViewById(R.id.sendMesButton);
        shotButton = (ImageButton) findViewById(R.id.shotButton);

//        mImmigrant = (Button) findViewById(R.id.button_immigrant);
        sendText = (EditText) findViewById(R.id.sendText);

        //Onclick Listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = sendText.getText().toString();
                if (!msg.equals("")) {
                    sendMessage(msg);
                } else {
                    Toast.makeText(ChatActivity.this,
                            "Empty contents", Toast.LENGTH_SHORT).show();
                }
                sendText.setText("");
            }
        });
//
//        mImmigrant.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

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

    private void sendMessage(String msg) {
        curChat.addMyMessage(msg);
        messageAdapter = new MessageAdapter(ChatActivity.this, curChat);
        recyclerView.setAdapter(messageAdapter);
    }

    private void displayMessage(Chatting curChat) {


//        recyclerView.setAdapter();
    }
}
