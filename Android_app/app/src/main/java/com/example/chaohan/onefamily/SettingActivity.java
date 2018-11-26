package com.example.chaohan.onefamily;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private ListView appSetList,aboutSetList;

    private String appSettings[] = new String [] {
            "Notifications",
             "Visible for Neighbors",
             "Privacy Setting",
             "My Profile Setting"};
    private String aboutSettings[] = new String [] {
            "Terms of Service",
            "Privacy Policy",
            "Check For Update",
            "Connect With Us"};

    private String popupText = "Sorry, still in development";

//    int[] images = new int[appSettings.length+aboutSettings.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setTitle("Settings");
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


        appSetList = (ListView) findViewById(R.id.app_setting_list);
        aboutSetList = (ListView) findViewById(R.id.about_setting_list);
        ArrayAdapter<String> adapterApp = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, appSettings);
        ArrayAdapter<String> adapterAbout = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, aboutSettings);
        appSetList.setAdapter(adapterApp);
        aboutSetList.setAdapter(adapterAbout);
//        MyAdapter adapter = new MyAdapter()

        appSetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (appSettings[position]) {
                    case "Notifications":
                        Toast.makeText(SettingActivity.this,
                                popupText, Toast.LENGTH_SHORT).show();
                        break;
                    case "Visible for Neighbors":
                        break;
                    case "Privacy Setting":
                        Intent nextScreen = new Intent(SettingActivity.this, PrivacySetActivity.class);
                        startActivity(nextScreen);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        break;
                    case "My Profile Setting":
                        break;
                }
            }
        });

        aboutSetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (aboutSettings[position]) {
                    case "Terms of Service":
                        Toast.makeText(SettingActivity.this,
                                popupText, Toast.LENGTH_LONG).show();
                        break;
                    case "Privacy Policy":
                        break;
                    case "Check For Update":
                        Toast.makeText(SettingActivity.this,
                                "It's Up-to-date", Toast.LENGTH_LONG).show();
                        break;
                    case "Connect With Us":
                        break;
                }
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
